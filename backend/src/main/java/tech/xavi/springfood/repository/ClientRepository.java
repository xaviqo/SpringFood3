package tech.xavi.springfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.xavi.springfood.entity.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findClientByEmail(String email);

}
