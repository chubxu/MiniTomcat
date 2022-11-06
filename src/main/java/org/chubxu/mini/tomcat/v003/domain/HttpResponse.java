package org.chubxu.mini.tomcat.v003.domain;

import org.chubxu.mini.tomcat.v003.utils.HttpProtocolUtil;
import org.chubxu.mini.tomcat.v003.utils.StaticResourceUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @ClassName HttpResponse
 * @Description Http响应封装类
 * @Since 0.0.2
 * @Date 2022/11/6 17:20
 * @Author chubxu
 */
public class HttpResponse {
    private OutputStream outputStream;

    public HttpResponse() {
    }

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void outputStaticFile(String staticFilePath) throws IOException {
        String staticFileAbsolutePath = StaticResourceUtil.getAbsolutePath(staticFilePath);
        File file = new File(staticFileAbsolutePath);
        if (file.exists() && file.isFile() && Objects.nonNull(staticFilePath) && staticFilePath.length() > 0) {
            StaticResourceUtil.outPutStaticFile(this.outputStream, file);
        } else {
            String error = HttpProtocolUtil.contact404HttpProtocol();
            this.outputStream.write(error.getBytes(StandardCharsets.UTF_8));
            this.outputStream.flush();
        }
    }

    public void outputSpecifiedContent(String content) throws IOException {
        String contactedContent = HttpProtocolUtil.contactHttpProtocol(content);
        this.outputStream.write(contactedContent.getBytes(StandardCharsets.UTF_8));
        this.outputStream.flush();
    }
}
