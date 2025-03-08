package co.edu.unisabana.api.controller.dto;

public record UserDTO(Long id, String username, String fullName, Long role_id, String password) {
}
