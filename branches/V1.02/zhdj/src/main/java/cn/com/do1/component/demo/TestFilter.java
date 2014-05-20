package cn.com.do1.component.demo;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 13-1-22
 * Time: 下午5:17
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TestFilter implements Filter {
    Logger logger = Logger.getLogger(this.getClass());

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        logger.info("-----------------------------------------------cookie start----------------------------------------------------------------------");
//        HttpServletRequest request = (HttpServletRequest) req;
//        for (Cookie cookie : request.getCookies()) {
//            logger.info(cookie.getName()+" : "+cookie.getValue());
//        }
//        logger.info("###"+ CookieUtil.getCookie(request,"iPlanetDirectoryPro"));
////        logger.info(new ReflectionToStringBuilder(request.getCookies()).toString());
//        logger.info("-------------------------------------------------cookie  end--------------------------------------------------------------------");
//        logger.info("-----------------------------------------------head start----------------------------------------------------------------------");
//        logger.info(new ReflectionToStringBuilder(request.getHeaderNames()).toString());
//        logger.info("-------------------------------------------------head  end--------------------------------------------------------------------");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
