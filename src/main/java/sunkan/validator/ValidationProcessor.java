package sunkan.validator;

import sunkan.validator.annotation.*;
import sunkan.validator.interfaces.FieldValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationProcessor {
    private static final Map<Class<? extends Annotation>, FieldValidator<?>> VALIDATORS = new HashMap<>();
    static {
        VALIDATORS.put(NotNull.class, new NotNullValidator());
        VALIDATORS.put(Size.class, new SizeValidator());
        VALIDATORS.put(Pattern.class, new PatternValidator());
        VALIDATORS.put(Min.class, new MinValidator());
        VALIDATORS.put(Max.class, new MaxValidator());
        VALIDATORS.put(Email.class, new EmailValidator());
        VALIDATORS.put(Future.class, new FutureValidator());
        VALIDATORS.put(Past.class, new PastValidator());
    }

    public String validate(Object obj) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);

            for (Annotation annotation : field.getAnnotations()) {
                FieldValidator validator = VALIDATORS.get(annotation.annotationType());
                if (validator != null && !validator.isValid(value, annotation)) {
                    errors.add(validator.getErrorMessage(fieldName, annotation));
                }
            }
        }
       return String.join(", ", errors);
    }
}
