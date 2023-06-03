package tech.xavi.springfood.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.xavi.springfood.configuration.exception.SpringFoodError;
import tech.xavi.springfood.configuration.exception.SpringFoodException;
import tech.xavi.springfood.entity.Client;
import tech.xavi.springfood.entity.RefreshToken;
import tech.xavi.springfood.model.auth.SignInRes;
import tech.xavi.springfood.model.auth.SignUpReq;
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

        return signIn(signUpReq.email());
    }

    public SignInRes signIn(String email) {
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
                            .name(account.getName())
                            .email(account.getEmail())
                            .accessToken(jwtService.generateAccessToken(account, roles))
                            .refreshToken(refreshToken.getToken())
                            .roles(roles)
                            .build();

                })
                .orElseThrow(() -> new SpringFoodException(
                        SpringFoodError.AccountNotFound,
                        HttpStatus.NOT_FOUND
                ));
    }

}
