package com.andrew.study.common.starter.handler;

import com.andrew.common.annotation.GlobalResponseBody;
import com.andrew.common.base.BaseResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:59 下午 2020/9/17
 */
@Slf4j
@ConditionalOnProperty(name = {"global.response.handler.enable"}, havingValue = "true")
@Component
public class GlobalResponseHandler implements HandlerMethodReturnValueHandler, InitializingBean, ApplicationContextAware {

    private static ApplicationContext CTX;

    public GlobalResponseHandler() {
        log.info("-----global response handler enable ------");
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Objects.nonNull(returnType.getMethod().getDeclaredAnnotation(GlobalResponseBody.class)) ||
                Objects.nonNull(returnType.getDeclaringClass().getAnnotation(GlobalResponseBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Gson gson = new Gson();
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(gson.toJson(BaseResponse.success(returnValue))).flush();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestMappingHandlerAdapter handlerAdapter = CTX.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers =
                handlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
        returnValueHandlers.add(new GlobalResponseHandler());
        if (!CollectionUtils.isEmpty(handlerMethodReturnValueHandlers)) {
            returnValueHandlers.addAll(handlerMethodReturnValueHandlers);
        }
        handlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CTX = applicationContext;
    }
}
