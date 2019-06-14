package mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Qiwen on 2019/6/1.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";
}
