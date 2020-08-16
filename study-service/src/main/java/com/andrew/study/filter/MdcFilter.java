package com.andrew.study.filter;

import com.andrew.study.constant.MdcConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author bo.fang
 * @Description
 * @Date 3:03 下午 2020/8/2
 */
//@WebFilter(urlPatterns = "/*")
public class MdcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String traceId = request.getHeader(MdcConstant.TRANCE_ID);
        traceId = StringUtils.isBlank(traceId) ? UUID.randomUUID().toString().replaceAll("-", "") : traceId;
        MDC.put(MdcConstant.TRANCE_ID, traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
