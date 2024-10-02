package sunkan.validator;

import sunkan.validator.annotation.Future;
import sunkan.validator.interfaces.FieldValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;

public class FutureValidator implements FieldValidator<Future> {
    @Override
    public boolean isValid(Object value, Future annotation) {
        if (value == null) return true;
        Temporal now = LocalDateTime.now();
        if (value instanceof Date) {
            return ((Date) value).toInstant().isAfter(((LocalDateTime) now).atZone(ZoneId.systemDefault()).toInstant());
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).isAfter(((LocalDateTime) now).atZone(ZoneId.systemDefault()).toLocalDate());
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).isAfter((LocalDateTime) now);
        }
        return false;
    }

    @Override
    public String getErrorMessage(String fieldName, Future annotation) {
        return annotation.message().replace("{fieldName}", fieldName);
    }
}
