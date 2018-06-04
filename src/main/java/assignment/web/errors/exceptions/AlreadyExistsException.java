package assignment.web.errors.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kek5 on 5/6/18.
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    @Getter
    @Setter
    private String resourceName;
    @Getter
    @Setter
    private String fieldName;
    @Getter
    @Setter
    private Object fieldValue;

    public AlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super();
        this.setResourceName(resourceName);
        this.setFieldName(fieldName);
        this.setFieldValue(fieldValue);
    }
}
