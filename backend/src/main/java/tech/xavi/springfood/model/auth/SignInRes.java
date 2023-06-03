package tech.xavi.springfood.model.auth;

import lombok.Builder;

import java.util.List;

@Builder
public record SignInRes(String accessToken, String refreshToken, String name, String email, List<String> roles) {
}
