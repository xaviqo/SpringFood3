package tech.xavi.springfood.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpringFoodError {

    // ACCOUNT ERRORS
    EmailAlreadyExists(1001,"This email address is already registered"),
    AccountNotFound(1002,"This email address is not registered"),
    AccountMismatch(1003,"Incorrect email or password"),
    InvalidToken(1004,"Corrupted or invalid token"),
    // FATAL ERRORS
    ErrorCreatingSignInPayload(1901,"A fatal error has occurred creating the user connection payload")
    ;

    private final int code;
    private final String message;
}
