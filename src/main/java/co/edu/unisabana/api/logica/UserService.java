package co.edu.unisabana.api.logica;

import co.edu.unisabana.api.controller.dto.UserResponseDTO;
import co.edu.unisabana.api.db.jpa.UserJPA;
import co.edu.unisabana.api.db.orm.UserORM;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserJPA userJPA;

    @Autowired
    public UserService(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    public Optional<UserORM> findByUsername(String username) {
        return userJPA.findByUsername(username);
    }

    public UserResponseDTO convertToResponse(UserORM user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole().getDescription());
    }
}