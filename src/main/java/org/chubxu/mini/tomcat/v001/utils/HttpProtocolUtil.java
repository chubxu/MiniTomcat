package org.chubxu.mini.tomcat.v001.utils;

/**
 * @ClassName HttpProtocolUtil
 * @Description 用于处理HTTP协议的工具类
 * @Since 0.0.1
 * @Date 2022/11/6 11:58
 * @Author chubxu
 */
public class HttpProtocolUtil {
    public static String contactHttpProtocol(String body) {
        String header = "HTTP/1.1 200 OK \n";
        String line = "Content-Type: text/html \n" +
                "Content-Length: " + body.getBytes().length + "\n";
        String blank = "\r\n";

        StringBuilder sb = new StringBuilder();
        sb.append(header).append(line).append(blank).append(body);
        return sb.toString();
    }
}
