package com.hongtaiyang.common.annotation;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
public @interface Authentication {

}
