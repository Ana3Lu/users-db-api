package co.edu.unisabana.api.logica;

import co.edu.unisabana.api.db.jpa.RolePermissionJPA;
import co.edu.unisabana.api.db.orm.RoleORM;
import co.edu.unisabana.api.db.orm.RolePermissionORM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final RolePermissionJPA rolePermissionJPA;

    public PermissionService(RolePermissionJPA rolePermissionJPA) {
        this.rolePermissionJPA = rolePermissionJPA;
    }

    public boolean hasAccess(RoleORM role, String endpoint) {
        // Obtiene las relaciones de permiso para el rol
        List<RolePermissionORM> rolePermissions = rolePermissionJPA.findByRole(role);
        // Comprueba si alguna de ellas tiene el permiso (almacenado en el campo "type") que coincide con el endpoint
        return rolePermissions.stream()
                .anyMatch(rp -> rp.getPermission().getType().equalsIgnoreCase(endpoint));
    }
}
