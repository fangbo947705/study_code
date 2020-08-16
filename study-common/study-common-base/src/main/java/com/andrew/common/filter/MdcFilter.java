package com.andrew.common.filter;

import com.andrew.common.constant.MdcConstant;
import com.andrew.common.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author bo.fang
 * @Description
 * @Date 2:47 下午 2020/8/16
 */
public class MdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String traceId = httpServletRequest.getHeader(MdcConstant.TRACE_ID);
        String version = httpServletRequest.getHeader(MdcConstant.VERSION);
        String appSource = httpServletRequest.getHeader(MdcConstant.APP_SOURCE);
        if (StringUtils.isBlank(traceId)) {
            traceId = UuidUtil.createUuidWithout();
        }
        MDC.put(MdcConstant.TRACE_ID, traceId);
        MDC.put(MdcConstant.APP_SOURCE, appSource);
        MDC.put(MdcConstant.VERSION, version);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
