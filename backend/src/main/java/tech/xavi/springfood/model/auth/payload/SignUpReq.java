package tech.xavi.springfood.model.auth.payload;

public record SignUpReq(String name, String phone, String email, String password) {
}
