package tech.xavi.springfood.model.error;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiErrorPayload (
        String error,
        int code,
        String message,
        LocalDateTime time,
        String path,
        String method
        ){
}
