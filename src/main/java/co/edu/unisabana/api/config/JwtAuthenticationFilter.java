package co.edu.unisabana.api.config;

import co.edu.unisabana.api.controller.dto.UserResponseDTO;
import co.edu.unisabana.api.logica.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null && header.toLowerCase().startsWith("bearer ")) {
            String token = header.substring(7);
            Optional<UserResponseDTO> optUser = authService.validarToken(token);
            if (optUser.isPresent()) {
                UserResponseDTO user = optUser.get();
                // Aquí podrías crear una lista de autoridades a partir del rol
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.role()));
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(user.username(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
