package sunkan.validator;

import sunkan.validator.annotation.Pattern;
import sunkan.validator.interfaces.FieldValidator;

public class PatternValidator implements FieldValidator<Pattern> {
    @Override
    public boolean isValid(Object value, Pattern annotation) {
        if (value == null) return true;
        return java.util.regex.Pattern.matches(annotation.regexp(), value.toString());
    }

    @Override
    public String getErrorMessage(String fieldName, Pattern annotation) {
        return annotation.message()
                .replace("{fieldName}", fieldName)
                .replace("{regexp}", annotation.regexp());
    }
}
