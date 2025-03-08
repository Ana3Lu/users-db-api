package co.edu.unisabana.api.controller;

import co.edu.unisabana.api.controller.dto.UserResponseDTO;
import co.edu.unisabana.api.db.orm.UserORM;
import co.edu.unisabana.api.logica.AuthService;
import co.edu.unisabana.api.logica.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private final AuthService authService;

    @GetMapping("/common/info")
    public ResponseEntity<?> getCommonInfo(@RequestHeader("Authorization") String token) {
        Optional<UserResponseDTO> tokenOpt = authService.validarToken(token);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido o faltante"));
        }

        UserResponseDTO tokenData = tokenOpt.get();

        Optional<UserORM> userOpt = userService.findByUsername(tokenData.username());
        return userOpt.map(user -> ResponseEntity.ok(userService.convertToResponse(user)))
                .orElse(ResponseEntity.status(401).body(new UserResponseDTO(0L,"error", "Usuario no encontrado")));
    }

    @GetMapping("/user/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        Optional<UserResponseDTO> tokenOpt = authService.validarToken(token);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido o faltante"));
        }

        UserResponseDTO tokenData = tokenOpt.get();
        if (!"APP".equalsIgnoreCase(tokenData.role())) {
            return ResponseEntity.status(403).body(Map.of("error", "Acceso denegado"));
        }
        Optional<UserORM> userOpt = userService.findByUsername(tokenData.username());
        return userOpt.map(user -> ResponseEntity.ok(userService.convertToResponse(user)))
                .orElse(ResponseEntity.status(401).body(new UserResponseDTO(0L,"error", "Usuario no encontrado")));

    }

    @GetMapping("/admin/info")
    public ResponseEntity<?> getAdminInfo(@RequestHeader("Authorization") String token) {
        Optional<UserResponseDTO> tokenOpt = authService.validarToken(token);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido o faltante"));
        }

        UserResponseDTO tokenData = tokenOpt.get();
        if (!"ADMIN".equalsIgnoreCase(tokenData.role())) {
            return ResponseEntity.status(403).body(Map.of("error", "Acceso denegado"));
        }
        Optional<UserORM> userOpt = userService.findByUsername(tokenData.username());
        return userOpt.map(user -> ResponseEntity.ok(userService.convertToResponse(user)))
                .orElse(ResponseEntity.status(401).body(new UserResponseDTO(0L,"error", "Usuario no encontrado")));
    }
}
