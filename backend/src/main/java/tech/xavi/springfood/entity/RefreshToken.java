package tech.xavi.springfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RefreshToken {

    @Id
    private String token;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Account owner;
    @Column(nullable = false)
    private boolean revoked;
    @Column(nullable = false)
    private LocalDateTime issuedAt;
}
