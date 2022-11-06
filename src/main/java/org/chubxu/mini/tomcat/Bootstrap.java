package org.chubxu.mini.tomcat;

import org.chubxu.mini.tomcat.utils.HttpProtocolUtil;

import java.io.IOException;
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
                OutputStream os = socket.getOutputStream();
                os.write(HttpProtocolUtil.contactHttpProtocol("Hello MiniTomcat").getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                socket.close();
            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
