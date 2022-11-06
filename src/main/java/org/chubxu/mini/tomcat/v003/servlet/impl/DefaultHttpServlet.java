package org.chubxu.mini.tomcat.v003.servlet.impl;

import org.chubxu.mini.tomcat.v003.domain.HttpRequest;
import org.chubxu.mini.tomcat.v003.domain.HttpResponse;
import org.chubxu.mini.tomcat.v003.servlet.HttpServlet;

/**
 * @ClassName DefaultHttpServlet
 * @Description HttpServlet默认实现类
 * @Since 0.0.3
 * @Date 2022/11/6 19:59
 * @Author chubxu
 */
public class DefaultHttpServlet extends HttpServlet {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void destroy() throws Exception {
        super.destroy();
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            String content = "<h1>DefaultServlet get</h1>";
            response.outputSpecifiedContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            String content = "<h1>DefaultServlet post</h1>";
            response.outputSpecifiedContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
