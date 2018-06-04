package assignment.web.errors.handlers;

import assignment.web.errors.ApiError;
import assignment.web.errors.exceptions.AlreadyExistsException;
import assignment.web.errors.exceptions.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kek5 on 5/13/18.
 */
@ControllerAdvice
public class MyCustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ResponseEntity<ApiError> handleInvalidInput(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> field_errors = result.getFieldErrors();

        List<String> errors = field_errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(new ApiError(errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public final ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        String message = String.format("%s with %s = %s does not exist!", e.getResourceName(), e.getFieldName(), e.getFieldValue());

        return new ResponseEntity<>(new ApiError(message, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public final ResponseEntity<ApiError> handleAlreadyExistsException(AlreadyExistsException e) {
        String message = String.format("%s with %s : %s already exists", e.getResourceName(), e.getFieldName(), e.getFieldValue());

        return new ResponseEntity<>(new ApiError(message, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }


}
