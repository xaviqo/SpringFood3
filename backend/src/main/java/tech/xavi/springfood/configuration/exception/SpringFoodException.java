package tech.xavi.springfood.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SpringFoodException extends RuntimeException {
    private final SpringFoodError springFoodError;
    private final HttpStatus httpStatus;
}
