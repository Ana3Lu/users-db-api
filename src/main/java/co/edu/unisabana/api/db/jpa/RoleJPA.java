package co.edu.unisabana.api.db.jpa;

import co.edu.unisabana.api.db.orm.RoleORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJPA extends JpaRepository<RoleORM, Long> {
}
