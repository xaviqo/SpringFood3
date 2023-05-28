package tech.xavi.springfood.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.xavi.springfood.entity.Account;
import tech.xavi.springfood.repository.AccountRepository;

@Service
@AllArgsConstructor
public class ChatUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Account user = accountRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("USER ID NOT FOUND"));

        return User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities(user.getAuthorities())
                .build();

    }

}