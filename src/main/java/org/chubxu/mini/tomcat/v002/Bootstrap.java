package org.chubxu.mini.tomcat.v002;

import org.chubxu.mini.tomcat.v002.domain.HttpRequest;
import org.chubxu.mini.tomcat.v002.domain.HttpResponse;
import org.chubxu.mini.tomcat.v002.utils.HttpProtocolUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName Bootstrap
 * @Description mini tomcat 启动入口
 * @Since 0.0.1
 * @Date 2022/11/6 11:37
 * @Author chubxu
 */
public class Bootstrap {
    private int port = 8080;

    private AtomicBoolean started = new AtomicBoolean(false);

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.start();
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e);
            System.exit(-1);
        }
    }

    public void start() throws IOException {
        if (started.compareAndSet(false, true)) {
            ServerSocket serverSocket = new ServerSocket(port);
            while (started.get()) {
                Socket socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(is);

                OutputStream os = socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse(os);

                httpResponse.outputHtml(httpRequest.getUrl());

                os.close();
                is.close();
                socket.close();
            }
        }
    }

    private void readAndPrintInputData(InputStream inputStream) throws IOException {
        int available = inputStream.available();
        byte[] buffer = new byte[available];
        inputStream.read(buffer);
        System.out.println(new String(buffer));
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
