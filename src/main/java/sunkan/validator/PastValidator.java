package sunkan.validator;

import sunkan.validator.annotation.Past;
import sunkan.validator.interfaces.FieldValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;

public class PastValidator implements FieldValidator<Past> {
    @Override
    public boolean isValid(Object value, Past annotation) {
        if (value == null) return true;
        Temporal now = LocalDateTime.now();
        if (value instanceof Date) {
            return ((Date) value).toInstant().isBefore(((LocalDateTime) now).atZone(ZoneId.systemDefault()).toInstant());
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).isBefore(((LocalDateTime) now).atZone(ZoneId.systemDefault()).toLocalDate());
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).isBefore((LocalDateTime) now);
        }
        return false;
    }

    @Override
    public String getErrorMessage(String fieldName, Past annotation) {
        return annotation.message().replace("{fieldName}", fieldName);
    }
}
