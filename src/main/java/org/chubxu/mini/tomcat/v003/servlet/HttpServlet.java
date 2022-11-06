package org.chubxu.mini.tomcat.v003.servlet;

import org.chubxu.mini.tomcat.v003.domain.HttpRequest;
import org.chubxu.mini.tomcat.v003.domain.HttpResponse;

/**
 * @ClassName HttpServlet
 * @Description Servlet抽象类
 * @Since 0.0.3
 * @Date 2022/11/6 19:53
 * @Author chubxu
 */
public abstract class HttpServlet implements Servlet {
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        if (GET_METHOD.equals(request.getMethod())) {
            doGet(request, response);
        } else if (POST_METHOD.equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    public abstract void doGet(HttpRequest request, HttpResponse response);
    public abstract void doPost(HttpRequest request, HttpResponse response);
}
