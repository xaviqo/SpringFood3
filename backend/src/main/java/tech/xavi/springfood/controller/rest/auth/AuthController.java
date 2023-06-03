package tech.xavi.springfood.controller.rest.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.xavi.springfood.configuration.constants.EndPoints;
import tech.xavi.springfood.model.auth.SignUpReq;
import tech.xavi.springfood.model.auth.SignInRes;
import tech.xavi.springfood.service.auth.AuthService;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping(EndPoints.EP_SIGN_UP)
    public ResponseEntity<SignInRes> signUp(@RequestBody SignUpReq request){
        return new ResponseEntity<>(
                authService.signUpClient(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(EndPoints.EP_SIGN_IN)
    public ResponseEntity<?> signIn(){
        return ResponseEntity.ok("valeps");
    }
}
