package tech.xavi.springfood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class Pack {

    @EmbeddedId
    PackProductId id;

    public static class PackProductId implements Serializable {
        @Column(name = "pack_id")
        String packId;
        @Column(name = "product_id")
        String productId;

    }
}
