package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.xavi.springfood.entity.role.Role;
import tech.xavi.springfood.entity.role.WorkerAuthority;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@PrimaryKeyJoinColumn
public class Worker extends Account{

    @ElementCollection
    @CollectionTable(name = "worker_authority", joinColumns = @JoinColumn(name = "worker_id"))
    List<WorkerAuthority> workerAuthorities;

    @Override
    public String getEntityPrefix(){
        return "WRK_";
    }

    @Override
    public String getRole(){
        return super.getRole() + Role.WORKER;
    }
}
