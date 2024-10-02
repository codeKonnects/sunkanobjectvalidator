package sunkan.validator.interfaces;

import java.lang.annotation.Annotation;

public interface FieldValidator <A extends Annotation> {
    boolean isValid(Object value, A annotation);
    String getErrorMessage(String fieldName, A annotation);
}
