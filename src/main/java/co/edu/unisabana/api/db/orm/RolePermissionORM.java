package co.edu.unisabana.api.db.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_permissions")
public class RolePermissionORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleORM role;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionORM permission;
}
