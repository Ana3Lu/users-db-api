package co.edu.unisabana.api.db.jpa;

import co.edu.unisabana.api.db.orm.RoleORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPA extends JpaRepository<RoleORM, Long> {
}
