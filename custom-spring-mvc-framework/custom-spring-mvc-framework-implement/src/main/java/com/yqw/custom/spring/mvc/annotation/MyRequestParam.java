package com.yqw.custom.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Qiwen on 2019/6/1.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {

    String value() default "";
}
