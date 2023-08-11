package com.wyx.toolbox.xss.filter;

import com.wyx.toolbox.xss.config.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter 过滤器，拦截请求转换为新的请求
 * @author wyx
 */
@Slf4j
public class XssFilter implements Filter {
    /**
     * 初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig){
        log.info("----XssFilter come in-----");
    }
    /**
     * 过滤方法
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest wrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            wrapper = new XssHttpServletRequestWrapper(servletRequest);
        }

        if (null == wrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(wrapper, response);
        }
    }
}
