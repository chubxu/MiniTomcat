package org.chubxu.mini.tomcat.v002.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @ClassName StaticResourceUtil
 * @Description 静态资源工具类
 * @Since 0.0.2
 * @Date 2022/11/6 17:28
 * @Author chubxu
 */
public class StaticResourceUtil {
    public static String getAbsolutePath(String path) {
        String rootResourcePath = Objects.requireNonNull(StaticResourceUtil.class.getResource("/")).getPath();
        return rootResourcePath.replaceAll("\\\\", "/") + path;
    }

    public static void outPutStaticFile(OutputStream outputStream, File staticFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(staticFile);
        int fileLength = fileInputStream.available();
        byte[] bytes = new byte[fileLength];
        fileInputStream.read(bytes);

        String str = new String(bytes);

        String fileOutput = HttpProtocolUtil.contactHttpProtocol(str);
        outputStream.write(fileOutput.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
