package tech.xavi.springfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.xavi.springfood.entity.Account;
import tech.xavi.springfood.model.auth.projection.SignInProjection;

import java.util.Optional;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByEmail(String email);
    Optional<SignInProjection> findSignInProjectionByEmail(String email);
    @Modifying
    @Query("UPDATE Account a SET a.id = :newId WHERE a.id = :oldId")
    void updateAccountId(@Param("oldId") String oldId, @Param("newId") String newId);

}
