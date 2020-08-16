package com.andrew.study.common.starter.interceptor;

import com.andrew.common.constant.MdcConstant;
import com.andrew.common.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Author bo.fang
 * @Description
 * @Date 5:37 下午 2020/8/16
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        checkAndAddHeader(request.getHeaders());
        return execution.execute(request, body);
    }

    private void checkAndAddHeader(HttpHeaders httpHeaders) {
        List<String> traceIds = httpHeaders.get(MdcConstant.TRACE_ID);
        if (CollectionUtils.isEmpty(traceIds)) {
            String traceId = MDC.get(MdcConstant.TRACE_ID);
            httpHeaders.add(MdcConstant.TRACE_ID, StringUtils.isBlank(traceId) ? UuidUtil.createUuidWithout() : traceId);
        }
    }
}
