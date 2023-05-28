package tech.xavi.springfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Account{

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @Override
    public String getEntityPrefix(){
        return "CLI_";
    }
}
