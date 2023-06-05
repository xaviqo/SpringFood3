package tech.xavi.springfood.configuration.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.xavi.springfood.model.error.ApiErrorPayload;

import java.time.LocalDateTime;

@RestControllerAdvice
public class SpringFoodExceptionHandler {

    @ExceptionHandler(value = SpringFoodException.class)
    public ResponseEntity<ApiErrorPayload> springFoodExceptionHandler(
            SpringFoodException sfException, HttpServletRequest request)
    {
        return new ResponseEntity<>(
                ApiErrorPayload.builder()
                        .error(sfException.getSpringFoodError().name())
                        .code(sfException.getSpringFoodError().getCode())
                        .message(sfException.getSpringFoodError().getMessage())
                        .time(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .method(request.getMethod())
                        .build(),
                sfException.getHttpStatus()
        );
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ApiErrorPayload> constrainViolationExceptionHandler(
            ConstraintViolationException cvException, HttpServletRequest request)
    {
        String message = cvException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Value validation error");

        return new ResponseEntity<>(
                ApiErrorPayload.builder()
                        .error("VALIDATION")
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .time(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .method(request.getMethod())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
    

}
