package sunkan.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Email {
    String regexp() default "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    String message() default "{fieldName} must be a valid email address";
}
