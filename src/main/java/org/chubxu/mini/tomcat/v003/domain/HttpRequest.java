package org.chubxu.mini.tomcat.v003.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @ClassName HttpRequest
 * @Description Http请求类
 * @Since 0.0.2
 * @Date 2022/11/6 17:15
 * @Author chubxu
 */
public class HttpRequest {
    private String method;
    private String url;
    private InputStream inputStream;

    public HttpRequest(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        int len = inputStream.available();
        byte[] bytes = new byte[len];
        inputStream.read(bytes);
        String requestString = new String(bytes);

        String requestLine = requestString.split("\\n")[0];
        String[] requestLineSplits = requestLine.split(" ");

        if (Objects.isNull(requestLineSplits) || requestLineSplits.length < 2) {
            return;
        }

        this.method = requestLineSplits[0];
        this.url = requestLineSplits[1];
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
