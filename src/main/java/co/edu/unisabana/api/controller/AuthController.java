package co.edu.unisabana.api.controller;

import co.edu.unisabana.api.controller.dto.UserDTO;
import co.edu.unisabana.api.controller.dto.UserResponseDTO;
import co.edu.unisabana.api.db.orm.UserORM;
import co.edu.unisabana.api.logica.AuthService;
import co.edu.unisabana.api.logica.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<UserORM> useropt = userService.findByUsername(username);
        if (useropt.isPresent()) {
            UserORM user = useropt.get();

            if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
            }

            UserResponseDTO responseDTO = userService.convertToResponse(user);
            String token = authService.generarToken(responseDTO);
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}
