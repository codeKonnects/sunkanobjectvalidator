package sunkan.validator;

import sunkan.validator.annotation.MinLength;
import sunkan.validator.interfaces.FieldValidator;

public class MinLengthValidator implements FieldValidator<MinLength> {
    @Override
    public boolean isValid(Object value, MinLength annotation) {
        if (value == null) return false;
        return value.toString().length() >= annotation.value();
    }

    @Override
    public String getErrorMessage(String fieldName, MinLength annotation) {
        return fieldName + " must be at least " + annotation.value() + " characters long";
    }

}
