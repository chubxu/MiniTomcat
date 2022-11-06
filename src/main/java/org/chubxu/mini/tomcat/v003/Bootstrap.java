package org.chubxu.mini.tomcat.v003;

import org.chubxu.mini.tomcat.v003.domain.HttpRequest;
import org.chubxu.mini.tomcat.v003.domain.HttpResponse;
import org.chubxu.mini.tomcat.v003.servlet.Servlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private Map<String, Servlet> container = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.loadServlet();
            bootstrap.start();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e);
            System.exit(-1);
        }
    }

    public void start() throws Exception {
        if (started.compareAndSet(false, true)) {
            ServerSocket serverSocket = new ServerSocket(port);
            while (started.get()) {
                Socket socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(is);

                OutputStream os = socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse(os);

                String requestUrl = httpRequest.getUrl();

                if (requestUrl != null && container.containsKey(requestUrl)) {
                    Servlet servlet = container.get(requestUrl);
                    servlet.service(httpRequest, httpResponse);
                } else {
                    httpResponse.outputStaticFile(httpRequest.getUrl());
                }

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

    private void loadServlet() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("web.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(is);
            Element rootElement = document.getRootElement();
            List<Element> servletNodes = rootElement.selectNodes("//servlet");
            for (Element servletNode : servletNodes) {
                String servletName = servletNode.selectSingleNode("servlet-name").getStringValue();
                String servletClass = servletNode.selectSingleNode("servlet-class").getStringValue();

                Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']'");
                String servletPath = servletMapping.selectSingleNode("servlet-path").getStringValue();

                container.put(servletPath, (Servlet) Class.forName(servletClass).getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
