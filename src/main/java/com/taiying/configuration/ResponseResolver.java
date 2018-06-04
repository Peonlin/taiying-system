package com.taiying.configuration;

import com.taiying.common.entity.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@ControllerAdvice
@ResponseBody
public class ResponseResolver implements ResponseBodyAdvice<Object> {

    /**
     * 判断支持的类型
     */
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 过滤
     *
     * 此处获取到request 是为了取到在拦截器里面设置的一个对象
     */
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        ResponseEntity e = new ResponseEntity();
        if (body instanceof Map) {
            Map<String, Object> bodyMap = (Map) body;
            if (bodyMap.containsKey("status")) {
                e.setCode("fail");
                e.setMsg((String)bodyMap.get("message"));
            } else {
                return body;
            }
        } else {
            e.setCode("success");
            e.setData(body);
        }
        return e;
    }
}
