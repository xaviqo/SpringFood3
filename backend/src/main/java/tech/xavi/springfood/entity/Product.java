package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    String id;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "Product name is mandatory")
    @Size(min = 2, max = 40)
    String name;
    @Column(length = 250)
    String description;
    @Column
    long stock;
    @NotBlank(message = "Price is mandatory")
    @Column(nullable = false)
    long price;
    @NotBlank(message = "At least one category must be assigned")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_type_relation",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_type_id")
    )
    List<ProductType> productTypes;
    @Column(nullable = false)
    boolean trackStock;
    @Column(nullable = false)
    boolean active;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id")
    List<Pack> pack;
}
