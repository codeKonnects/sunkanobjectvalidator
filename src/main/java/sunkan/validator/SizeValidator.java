package sunkan.validator;

import sunkan.validator.annotation.Size;
import sunkan.validator.interfaces.FieldValidator;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class SizeValidator implements FieldValidator<Size> {
    @Override
    public boolean isValid(Object value, Size annotation) {
        if (value == null) return true;
        int length = getLength(value);
        return length >= annotation.min() && length <= annotation.max();
    }

    @Override
    public String getErrorMessage(String fieldName, Size annotation) {
        return annotation.message()
                .replace("{fieldName}", fieldName)
                .replace("{min}", String.valueOf(annotation.min()))
                .replace("{max}", String.valueOf(annotation.max()));
    }

    private int getLength(Object value) {
        if (value instanceof String) return ((String) value).length();
        if (value instanceof Collection) return ((Collection<?>) value).size();
        if (value instanceof Map) return ((Map<?, ?>) value).size();
        if (value.getClass().isArray()) return Array.getLength(value);
        return 0;
    }
}
