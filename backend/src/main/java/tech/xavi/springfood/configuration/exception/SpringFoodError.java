package tech.xavi.springfood.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpringFoodError {

    EmailAlreadyExists(1001,"This email address is already registered"),
    AccountNotFound(1002,"This email address is not registered")
    ;

    private final int code;
    private final String message;
}
