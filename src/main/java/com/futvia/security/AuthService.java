package com.futvia.security;

import com.futvia.model.common.enums.RolNombre;
import com.futvia.model.rbac.Rol;
import com.futvia.model.rbac.Usuario;
import com.futvia.repository.rbac.RolRepository;
import com.futvia.repository.rbac.UsuarioRepository;
import com.futvia.security.jwt.JwtService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // ===== DTOs (records) =====
    public record RegisterRequest(
            @NotBlank String nombre,
            @NotBlank String apellido,
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}

    public record AuthRequest(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}

    public record AuthResponse(String token) {}

    // ===== Registro (rol por defecto OPERADOR) =====
    public AuthResponse register(RegisterRequest req) {
        if (usuarioRepository.existsByEmailIgnoreCase(req.email())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Rol rolBase = rolRepository.findByNombre(RolNombre.OPERADOR)
                .orElseThrow(() -> new IllegalStateException("Rol OPERADOR no configurado"));

        Usuario u = new Usuario();
        u.setNombre(req.nombre());
        u.setApellido(req.apellido());
        u.setEmail(req.email());
        u.setEstado(true);
        u.setPassword(passwordEncoder.encode(req.password()));
        // **Asegúrate** que tu entidad Usuario tenga relación (Set<Rol> roles)
        u.getRoles().add(rolBase);

        Usuario saved = usuarioRepository.save(u);
        String token = jwtService.generateToken(saved);
        return new AuthResponse(token);
    }

    // ===== Login =====
    public AuthResponse login(AuthRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        Usuario user = usuarioRepository.findByEmailIgnoreCase(req.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
