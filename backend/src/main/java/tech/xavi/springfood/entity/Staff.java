package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tech.xavi.springfood.entity.role.Role;
import tech.xavi.springfood.entity.role.StaffAuthority;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn
public class Staff extends Account{

    //@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "staff_authority", joinColumns = @JoinColumn(name = "staff_id"))
    List<StaffAuthority> staffAuthorities;

    @Override
    public String getEntityPrefix(){
        return Role.STAFF.PREFIX;
    }

    @Override
    public String getRole(){
        return super.getRole() + Role.STAFF;
    }
}
