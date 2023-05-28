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
public class Address {

    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "City is mandatory")
    @Size(min = 2, max = 40)
    String city;
    @Column(length = 40, nullable = false)
    @NotBlank(message = "Street is mandatory")
    @Size(min = 4, max = 40)
    String street;
    @Column(length = 6, nullable = false)
    @NotBlank(message = "Postal Code is mandatory")
    @Size(min = 4, max = 6)
    int postal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    Account owner;
    @Column
    boolean main;

}
