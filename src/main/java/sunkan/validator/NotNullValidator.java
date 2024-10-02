package sunkan.validator;

import sunkan.validator.annotation.NotNull;
import sunkan.validator.interfaces.FieldValidator;

public class NotNullValidator implements FieldValidator<NotNull> {
    @Override
    public boolean isValid(Object value, NotNull annotation) {
        if (value == null) return false;
        if (value instanceof String) return !((String) value).trim().isEmpty();
        return true;
    }

    @Override
    public String getErrorMessage(String fieldName, NotNull annotation) {
        return annotation.message().replace("{fieldName}", fieldName);
    }
}
