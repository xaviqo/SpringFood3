package tech.xavi.springfood.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.xavi.springfood.entity.Account;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
