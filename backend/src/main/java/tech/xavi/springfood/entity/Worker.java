package tech.xavi.springfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class Worker extends Account{

    boolean deliveryManager;
    boolean orderManager;
    boolean productManager;
    boolean adminAccess;
    boolean springFoodConfiguration;

    @Override
    public String getEntityPrefix(){
        return "WRK_";
    }
}
