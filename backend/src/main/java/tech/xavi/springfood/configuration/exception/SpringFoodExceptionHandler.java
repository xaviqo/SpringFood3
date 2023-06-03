package tech.xavi.springfood.configuration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SpringFoodExceptionHandler {

    @ExceptionHandler(value = SpringFoodException.class)
    public ResponseEntity<Map<String,Object>> springFoodExceptionHandler(SpringFoodException sfException){
        Map<String,Object> errorPayload = new HashMap<>();
        errorPayload.put("code",sfException.getSpringFoodError().getCode());
        errorPayload.put("message",sfException.getSpringFoodError().getMessage());
        errorPayload.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(errorPayload,sfException.getHttpStatus());
    }

}
