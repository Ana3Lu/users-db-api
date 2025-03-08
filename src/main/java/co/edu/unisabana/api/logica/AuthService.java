package co.edu.unisabana.api.logica;

import co.edu.unisabana.api.controller.dto.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService {

    public String generarToken(UserResponseDTO user) {
        try {
            // Header
            String header = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString("{\"alg\":\"none\",\"typ\":\"JWT\"}".getBytes("UTF-8"));

            // Payload
            String payloadJson = String.format(
                    "{\"id\":%d,\"username\":\"%s\",\"role\":\"%s\",\"iat\":%d,\"exp\":%d}",
                    user.id(),
                    user.username(),
                    user.role(),
                    System.currentTimeMillis() / 1000,
                    (System.currentTimeMillis() + 3600000) / 1000  // Token con 1 hora de validez
            );
            String payload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes("UTF-8"));

            // Firma vacía ("." al final)
            String jwt = header + "." + payload + ".";
            return jwt;
        } catch (Exception e) {
            throw new RuntimeException("Error generando token", e);
        }
    }

    public Optional<UserResponseDTO> validarToken(String token) {
        try {
            // Remover prefijo "Bearer " si está presente
            if (token.toLowerCase().startsWith("bearer ")) {
                token = token.substring(7);
            }

            // Un JWT tiene tres partes: header, payload y firma.
            String[] parts = token.split("\\.");
            if (parts.length < 2) return Optional.empty();

            // La segunda parte es el payload; se decodifica de Base64
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), "UTF-8");

            // Usamos Jackson para convertir el JSON en un objeto UserORM
            ObjectMapper mapper = new ObjectMapper();
            UserResponseDTO user = mapper.readValue(payloadJson, UserResponseDTO.class);

            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error validando token: " + e.getMessage());
            return Optional.empty();
        }
    }
}