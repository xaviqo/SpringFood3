package tech.xavi.springfood.model.auth.payload;

import lombok.Builder;

import java.util.List;

@Builder
public record SignInRes(
        Payload payload,
        String message
) {
    @Builder
    public record Payload(
            String accessToken,
            String refreshToken,
            String name,
            String email,
            List<String> roles
    ){

    }
}
