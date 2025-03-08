package co.edu.unisabana.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponseDTO(Long id, String username, String role){
}

