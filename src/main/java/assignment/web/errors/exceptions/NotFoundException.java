package assignment.web.errors.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kek5 on 4/22/18.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class NotFoundException extends RuntimeException {
    @Getter
    @Setter
    private String resourceName;
    @Getter
    @Setter
    private String fieldName;
    @Getter
    @Setter
    private Object fieldValue;

    public NotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super();
        this.setResourceName(resourceName);
        this.setFieldName(fieldName);
        this.setFieldValue(fieldValue);

    }
}
