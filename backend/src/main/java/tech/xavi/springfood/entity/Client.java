package tech.xavi.springfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tech.xavi.springfood.entity.role.Role;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Client extends Account{

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @Override
    public String getEntityPrefix(){
        return "CLI_";
    }

    @Override
    public String getRole(){
        return super.getRole() + Role.CLIENT;
    }
}
