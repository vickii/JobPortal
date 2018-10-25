package com.risksense.jobportal.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestLoggerFilter implements Filter {

    private final Logger LOG = LoggerFactory.getLogger(HttpRequestLoggerFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String longLine = "**==================================================================================================================**";
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOG.info(longLine);
        LOG.info("Request Recieved");
        LOG.info("Request-URI : " + httpServletRequest.getRequestURI());
        LOG.info("Method : " + httpServletRequest.getMethod());
        LOG.info("Content-Type : " + httpServletRequest.getContentType());
        // LOG.info("Body : "+new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream())).lines().filter(e -> e!=null).map(e -> e.toString()).collect(Collectors.joining( "," )));
        chain.doFilter(httpServletRequest, response);
        LOG.info("Response status : " + ((HttpServletResponse) response).getStatus());
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}