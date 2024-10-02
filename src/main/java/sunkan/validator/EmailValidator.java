package sunkan.validator;

import sunkan.validator.annotation.Email;
import sunkan.validator.interfaces.FieldValidator;

import java.util.regex.Pattern;

public class EmailValidator implements FieldValidator<Email>{
@Override
    public boolean isValid(Object value, Email annotation) {
        if (value == null) return true;
        return Pattern.compile(annotation.regexp()).matcher(value.toString()).matches();
    }

    @Override
    public String getErrorMessage(String fieldName, Email annotation) {
        return annotation.message().replace("{fieldName}", fieldName);
    }
}
