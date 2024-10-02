package sunkan.validator;

import sunkan.validator.annotation.Max;
import sunkan.validator.interfaces.FieldValidator;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class MaxValidator implements FieldValidator<Max> {
    @Override
    public boolean isValid(Object value, Max annotation) {
        if (value == null) return true;
        if (value instanceof Number) {
            return ((Number) value).doubleValue() <= annotation.value();
        } else if (value instanceof String) {
            return ((String) value).length() <= annotation.value();
        } else if (value instanceof Collection) {
            return ((Collection<?>) value).size() <= annotation.value();
        } else if (value instanceof Map) {
            return ((Map<?, ?>) value).size() <= annotation.value();
        } else if (value.getClass().isArray()) {
            return Array.getLength(value) <= annotation.value();
        }
        return false;
    }

    @Override
    public String getErrorMessage(String fieldName, Max annotation) {
        return annotation.message()
                .replace("{fieldName}", fieldName)
                .replace("{value}", String.valueOf(annotation.value()));
    }
}
