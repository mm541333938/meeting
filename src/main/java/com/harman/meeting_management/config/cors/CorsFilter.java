package com.harman.meeting_management.config.cors;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 自定应过滤器，跨域请求操作
 *
 * @author L.Willian
 * @date 11/18/2019 3:35 PM
 */
@Component
public class CorsFilter implements Filter {
    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    //通过拦截request请求，得到请求Header中的参数
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    /**
     * 通过CORS技术实现AJAX跨域访问, 只要将CORS响应头写入response对象中即可
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String currentOrigin = request.getHeader("Origin");
        if (allowOrigin != null && allowOrigin.length() != 0) {
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            if (allowOriginList != null && allowOriginList.size() != 0) {
                if (allowOriginList.contains(currentOrigin)) {
                    response.setHeader("Access-Control-Allow-Origin",
                            currentOrigin);
                }
            }
        }
        if (allowMethods != null && allowMethods.length() != 0) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (allowCredentials != null && allowCredentials.length() != 0) {
            response.setHeader("Access-Control-Allow-Credentials",
                    allowCredentials);
        }
        if (allowHeaders != null && allowHeaders.length() != 0) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (exposeHeaders != null && exposeHeaders.length() != 0) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
