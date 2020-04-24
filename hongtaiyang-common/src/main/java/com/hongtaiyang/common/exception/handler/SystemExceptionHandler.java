package com.hongtaiyang.common.exception.handler;

import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.exception.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class SystemExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ExceptionHandler(value = {SysException.class})
    public HttpResponse handleSysException(SysException e) {
        logger.error(e.getMessage(),e);
        return HttpResponse.error(e);
    }

    @ExceptionHandler(value = {Exception.class})
    public HttpResponse handleException(Exception e) {
        logger.error(e.getMessage(),e);
        return HttpResponse.error(e);
    }
}
