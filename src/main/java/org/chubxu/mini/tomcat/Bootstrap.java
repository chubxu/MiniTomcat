package org.chubxu.mini.tomcat;

/**
 * @ClassName Bootstrap
 * @Description mini tomcat 启动入口
 * @Since 1.0.0
 * @Date 2022/11/6 11:37
 * @Author chubxu
 */
public class Bootstrap {
    private int port = 8080;


    public static void main(String[] args) {

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
