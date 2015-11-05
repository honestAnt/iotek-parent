<%@tag pageEncoding="UTF-8" import="com.iotekclass.common.util.security.CipherHelper"%>
<%@ attribute name="source" type="java.lang.Integer" required="true"%>

<%=CipherHelper.cipher(String.valueOf(source),CipherHelper.GLOBAL_SECRETKEY)%>

