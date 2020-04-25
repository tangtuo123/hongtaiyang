package com.hongtaiyang.common.exception.handler;

import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@ControllerAdvice
public class SystemExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ExceptionHandler(value = {SysException.class})
    public HttpResponse handleSysException(SysException e) {
        logger.error(e.getMessage(), e);
        return HttpResponse.error(e);
    }

    @ExceptionHandler(value = {Exception.class})
    public HttpResponse handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return HttpResponse.error(e);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public HttpResponse handleException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> errorMsgs = new LinkedList<>();
        allErrors.forEach(error -> errorMsgs.add(error.getDefaultMessage()));
        String errorMsg = StringUtils.join(errorMsgs);
        return HttpResponse.error(SystemCode.PARAMETER_ERROR, errorMsg);
    }
}
