package tech.xavi.springfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.xavi.springfood.entity.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Optional<Staff> findStaffByEmail(String email);

}
