package tech.xavi.springfood.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"order\"")
public class Order {

    @Id @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @GenericGenerator(name = "account-id-generator", strategy = "tech.xavi.springfood.OrderIdGenerator")
    String id;
    @ManyToOne
    Client client;
    @Column(nullable = false)
    String stripeId;
    @Column(nullable = false)
    LocalDateTime createdAt;
    @Column(nullable = false)
    long orderTotal;
    @Column(nullable = false)
    boolean homeDelivery;
    @Column(nullable = false)
    boolean delivered;
    @ManyToOne
    Staff deliveryWorker;
    @ManyToOne
    Address deliveryAddress;
    @Column(nullable = false)
    LocalDateTime deliveryDateTime;
    @OneToMany(mappedBy="mainOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JsonManagedReference
    private List<OrderLine> orderLines;

    public String getEntityPrefix(){
        return "SF_";
    }

}
