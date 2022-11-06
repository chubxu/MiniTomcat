package org.chubxu.mini.tomcat.v003.servlet;

import org.chubxu.mini.tomcat.v003.domain.HttpRequest;
import org.chubxu.mini.tomcat.v003.domain.HttpResponse;

/**
 * @ClassName Servlet
 * @Description Servlet接口，定义相关规范
 * @Since 0.0.3
 * @Date 2022/11/6 19:55
 * @Author chubxu
 */
public interface Servlet {
    void init() throws Exception;

    void destroy() throws Exception;

    void service(HttpRequest request, HttpResponse response) throws Exception;
}
