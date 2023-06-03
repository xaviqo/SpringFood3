package tech.xavi.springfood.model.auth;

public record SignUpReq(String name, String phone, String email, String password) {
}
