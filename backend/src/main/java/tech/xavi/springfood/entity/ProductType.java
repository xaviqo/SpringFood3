package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductType {

    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 40, nullable = false)
    @Size(min = 2, max = 40)
    @NotBlank(message = "Name is mandatory")
    String name;
    @Column(length = 250)
    String description;

}
