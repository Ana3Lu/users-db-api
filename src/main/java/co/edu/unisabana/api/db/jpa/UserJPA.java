package co.edu.unisabana.api.db.jpa;

import co.edu.unisabana.api.db.orm.UserORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPA extends JpaRepository<UserORM, Long> {
    Optional<UserORM> findByUsername(String username);
}
