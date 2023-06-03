package tech.xavi.springfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.xavi.springfood.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
}
