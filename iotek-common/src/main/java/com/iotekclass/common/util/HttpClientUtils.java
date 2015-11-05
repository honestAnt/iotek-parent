package com.iotekclass.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class HttpClientUtils {

    private static final int STATUSCODE_200 = 200;
    private static final int TIME = 30;
    private static final int THOUSAND = 1000;

    private HttpClientUtils() {
    }

    static class GzipDecompressingEntity extends HttpEntityWrapper {
        public GzipDecompressingEntity(final HttpEntity entity) {
            super(entity);
        }

        public InputStream getContent() throws IOException {
            InputStream wrappedin = wrappedEntity.getContent();
            return new GZIPInputStream(wrappedin);
        }

        public long getContentLength() {
            return -1;
        }
    }

    public static DefaultHttpClient getHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setUserAgent(params, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);
        HttpConnectionParams.setConnectionTimeout(params, TIME * THOUSAND);
        HttpConnectionParams.setSoTimeout(params, TIME * THOUSAND);
        DefaultHttpClient httpclient = new DefaultHttpClient(params);
        httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }

        });
        httpclient.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                Header ceheader = entity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                            return;
                        }
                    }
                }
            }

        });
        return httpclient;
    }

    public static String getHtml(HttpResponse res, String encode, Boolean breakLine) throws IOException {
        breakLine = (breakLine == null) ? false : breakLine;
        InputStream input = null;

        StatusLine status = res.getStatusLine();
        if (status.getStatusCode() != STATUSCODE_200) {
            throw new RuntimeException("50001");
        }
        if (res.getEntity() == null) {
            return "";
        }
        input = res.getEntity().getContent();
        InputStreamReader reader = new InputStreamReader(input, encode);
        BufferedReader bufReader = new BufferedReader(reader);
        String tmp = null, html = "";
        while ((tmp = bufReader.readLine()) != null) {
            html += tmp + (breakLine ? "\r\n" : "");
        }
        if (input != null) {
            input.close();
        }
        return html;
    }

    public static String getHtml(DefaultHttpClient httpclient, String url, String encode) throws IOException {
        InputStream input = null;

        HttpGet get = new HttpGet(url);
        HttpResponse res = httpclient.execute(get);
        StatusLine status = res.getStatusLine();
        if (status.getStatusCode() != STATUSCODE_200) {
            throw new RuntimeException("50001");
        }
        if (res.getEntity() == null) {
            return "";
        }
        input = res.getEntity().getContent();
        InputStreamReader reader = new InputStreamReader(input, encode);
        BufferedReader bufReader = new BufferedReader(reader);
        String tmp = null, html = "";
        while ((tmp = bufReader.readLine()) != null) {
            html += tmp;
        }
        if (input != null) {
            input.close();
        }
        return html;
    }
}
