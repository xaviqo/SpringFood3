package tech.xavi.springfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {

    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne @JsonBackReference
    Order mainOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;
    @Column(nullable = false)
    int quantity;
    @Column(nullable = false)
    private long lineTotal;
}
