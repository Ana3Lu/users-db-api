package co.edu.unisabana.api.db.jpa;

import co.edu.unisabana.api.db.orm.PermissionORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJPA extends JpaRepository<PermissionORM, Long> {
}
