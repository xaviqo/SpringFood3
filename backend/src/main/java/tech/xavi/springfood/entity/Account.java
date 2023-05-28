package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements UserDetails {

    @Id @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @GenericGenerator(name = "account-id-generator", strategy = "tech.xavi.springfood.AccountIdGenerator")
    String id;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 40)
    String name;
    @Column(length = 12, nullable = false)
    @NotBlank(message = "Phone is mandatory")
    @Size(min = 8, max = 12)
    String phone;
    @Column(length = 40, nullable = false)
    @Email(message = "Invalid email")
    String email;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, max = 40)
    String password;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Address> addresses;

    public String getEntityPrefix(){
        return "ACC_";
    }

    @Override
    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    public String getUsername() {
        return null;
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
        return false;
    }
}
