package assignment.web.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kek5 on 5/10/18.
 */
public class ApiError {
    @Getter
    @Setter
    private List<String> errorMessages;
    @Getter
    @Setter
    private HttpStatus httpStatus;

    public ApiError(String errorMessage, HttpStatus httpStatus) {
        this.setErrorMessages(new ArrayList<>());
        this.errorMessages.add(errorMessage);
        this.setHttpStatus(httpStatus);
    }
    public ApiError(List<String> errors, HttpStatus httpStatus) {
        this.setErrorMessages(errors);
        this.setHttpStatus(httpStatus);
    }

    public void addError(String error) {
        this.errorMessages.add(error);
    }
}
