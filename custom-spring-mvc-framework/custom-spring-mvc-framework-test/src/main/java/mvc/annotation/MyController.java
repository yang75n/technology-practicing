package mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Qiwen on 2019/6/1.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {

    String value() default "";
}
