package com.hongtaiyang.common.encrypt;

import com.alibaba.fastjson.JSONObject;
import com.hongtaiyang.common.annotation.Secret;
import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import com.hongtaiyang.common.utils.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/5/8 10:47
 */
@Slf4j
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<HttpResponse> {

    private static final String RESPONSE_SUCCESS_CODE = "200";

    @Value("${spring.encrypt.rsaPublicKey}")
    private String rsaPublicKey;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 对加了@Secret注解并且encrypt=true的方法响应数据进行加密处理
        return Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(Secret.class) && methodParameter.getMethod().getAnnotation(Secret.class).encrypt();
    }

    @Override
    public HttpResponse beforeBodyWrite(HttpResponse httpResponse, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 仅当请求成功时加密
        if (httpResponse != null && RESPONSE_SUCCESS_CODE.equals(httpResponse.getCode())) {
            String data = JSONObject.toJSONString(httpResponse.getData());
            byte[] bytes = data.getBytes();
            try {
                String encryptStr = Base64.encodeBase64String(RSAUtils.encryptByPublicKey(bytes, rsaPublicKey));
                httpResponse.setData(encryptStr);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw SysException.asException(SystemCode.ENCRYPT_ERROR, e.getMessage());
            }
        }
        return httpResponse;
    }
}
