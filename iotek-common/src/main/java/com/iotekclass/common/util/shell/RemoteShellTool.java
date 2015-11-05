package com.iotekclass.common.util.shell;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.apache.log4j.Logger;

/**
 * @Description 执行远程shell脚本工具类
 * @author wangfengbao
 * @date 2015年9月14日 下午13:40:12
 *
 */
public class RemoteShellTool {

    Logger logger = Logger.getLogger(RemoteShellTool.class);
    //链接对象
    private Connection conn;
    //远程地址
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    //服务器用户
    private String userName;
    //服务器密码
    private String password;

    /**
     * 初始化链接工具对象
     * @param ipAddr
     * @param userName
     * @param password
     * @param charset
     */
    public RemoteShellTool(String ipAddr, String userName, String password,
                           String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        if (charset != null) {
            this.charset = charset;
        }
    }

    //链接认证
    public boolean login() throws IOException {
        conn = new Connection(ipAddr);
        conn.connect(); // 连接
        return conn.authenticateWithPassword(userName, password); // 认证
    }

    /**
     * 执行shell脚本
     * @param cmds
     * @return
     */
    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                Session session = conn.openSession(); // 打开一个会话
                session.execCommand(cmds);

                in = session.getStdout();
                result = this.processStdout(in, this.charset);
                session.close();
                conn.close();
            } else {
                logger.debug("ssh2登录认证失败!");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    /**
     * 获取执行信息
     * @param in
     * @param charset
     * @return
     */
    public String processStdout(InputStream in, String charset) {

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        RemoteShellTool tool = new RemoteShellTool("192.168.8.51", "root",
                "iotek%a123", "utf-8");
//        RemoteShellTool tool = new RemoteShellTool("42.62.67.233", "root",
//                "iotek+tomcat", "utf-8");

//        String result = tool.exec("/script/reboot.sh");
                String result = tool.exec("/root/test1.sh");
        System.out.println("============"+result+"++++++++++++++");
        System.out.print("....");
    }

}
