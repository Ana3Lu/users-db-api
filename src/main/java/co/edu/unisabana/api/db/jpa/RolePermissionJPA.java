package co.edu.unisabana.api.db.jpa;

import co.edu.unisabana.api.db.orm.PermissionORM;
import co.edu.unisabana.api.db.orm.RoleORM;
import co.edu.unisabana.api.db.orm.RolePermissionORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionJPA extends JpaRepository<RolePermissionORM, Long> {
    List<RolePermissionORM> findByRole(RoleORM role);
    List<RolePermissionORM> findByPermission(PermissionORM permission);
}
