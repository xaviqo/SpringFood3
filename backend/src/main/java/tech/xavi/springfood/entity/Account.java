package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

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

}
