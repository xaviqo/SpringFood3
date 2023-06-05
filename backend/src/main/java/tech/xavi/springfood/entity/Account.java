package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.xavi.springfood.entity.role.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements UserDetails {

    @Id @Column
    @GeneratedValue(generator = "account-id-generator")
    @GenericGenerator(name = "account-id-generator", strategy = "tech.xavi.springfood.configuration.idgenerator.AccountIdGenerator")
    String id;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 4 characters")
    String name;
    @Column(length = 12, nullable = false)
    @NotBlank(message = "Phone is mandatory")
    @Size(min = 6, max = 12, message = "Phone number must be between 6 and 12 characters")
    String phone;
    @Column(length = 40, nullable = false)
    @Email(message = "Invalid email")
    String email;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    String password;
    @Column
    boolean enabled;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Address> addresses;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<RefreshToken> refreshTokens;

    public String getEntityPrefix(){
        return Role.ACCOUNT.PREFIX;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(this::getRole);
        if (this instanceof Staff) {
            authorities.addAll(((Staff) this)
                    .getStaffAuthorities()
                    .stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.name()))
                    .toList());
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getRole(){
        return "ROLE_";
    }
}
