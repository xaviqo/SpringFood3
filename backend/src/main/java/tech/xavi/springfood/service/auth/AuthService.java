package tech.xavi.springfood.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.xavi.springfood.configuration.constants.SuccessMessage;
import tech.xavi.springfood.configuration.exception.SpringFoodError;
import tech.xavi.springfood.configuration.exception.SpringFoodException;
import tech.xavi.springfood.entity.Client;
import tech.xavi.springfood.entity.RefreshToken;
import tech.xavi.springfood.model.auth.payload.SignInReq;
import tech.xavi.springfood.model.auth.payload.SignInRes;
import tech.xavi.springfood.model.auth.payload.SignUpReq;
import tech.xavi.springfood.repository.AccountRepository;
import tech.xavi.springfood.repository.ClientRepository;
import tech.xavi.springfood.repository.RefreshTokenRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public SignInRes signUpClient(SignUpReq signUpReq){

        if (accountRepository.findAccountByEmail(signUpReq.email()).isPresent()) {
            throw new SpringFoodException(
                    SpringFoodError.EmailAlreadyExists,
                    HttpStatus.BAD_REQUEST
            );
        }

        clientRepository.save(
                Client.builder()
                        .email(signUpReq.email().toLowerCase())
                        .name(signUpReq.name())
                        .phone(signUpReq.phone())
                        .password(passwordEncoder.encode(signUpReq.password()))
                        .enabled(true)
                        .build()
        );

        return getSignInPayload(signUpReq.email());
    }

    public SignInRes signIn(SignInReq signInReq) {
        return accountRepository.findSignInProjectionByEmail(signInReq.email())
                .filter(storedUser -> passwordEncoder.matches(signInReq.password(), storedUser.getPassword()))
                .map(storedUser -> getSignInPayload(signInReq.email()))
                .orElseThrow(() -> new SpringFoodException(
                        SpringFoodError.AccountMismatch,
                        HttpStatus.NOT_FOUND
                ));
    }


    private SignInRes getSignInPayload(String email) {
        return accountRepository.findAccountByEmail(email.toLowerCase())
                .map(account -> {

                    List<String> roles = account.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList();

                    RefreshToken refreshToken = RefreshToken.builder()
                            .token(jwtService.generateRefreshToken(account))
                            .owner(account)
                            .revoked(false)
                            .issuedAt(LocalDateTime.now())
                            .build();

                    refreshTokenRepository.save(refreshToken);

                    return SignInRes.builder()
                            .payload(SignInRes.Payload.builder()
                                    .name(account.getName())
                                    .email(account.getEmail())
                                    .accessToken(jwtService.generateAccessToken(account, roles))
                                    .refreshToken(refreshToken.getToken())
                                    .roles(roles)
                                    .build()
                            )
                            .message(SuccessMessage.SIGN_UP)
                            .build();

                })
                .orElseThrow(() -> new SpringFoodException(
                        SpringFoodError.ErrorCreatingSignInPayload,
                        HttpStatus.INTERNAL_SERVER_ERROR
                ));
    }

}
