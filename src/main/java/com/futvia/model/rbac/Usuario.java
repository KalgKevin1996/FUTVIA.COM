package com.futvia.model.rbac;

import com.futvia.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "usuarios",
        indexes = @Index(name = "ix_usuarios_email", columnList = "email", unique = true)
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@DynamicUpdate
public class Usuario extends BaseEntity implements UserDetails {

    @EqualsAndHashCode.Include
    @Email
    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @NotBlank @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nombre;

    @NotBlank @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String apellido;

    @JsonIgnore
    @NotBlank
    @Size(min = 60, max = 72) // típicamente 60 para BCrypt
    @Column(name = "password_hash", nullable = false, length = 72)
    private String password;

    @Column(nullable = false)
    private boolean estado = true; // activo/inactivo

    @ManyToMany(fetch = FetchType.EAGER) // considera LAZY + @EntityGraph en prod
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = @UniqueConstraint(name = "uk_usuario_rol", columnNames = {"usuario_id","rol_id"})
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Rol> roles = new HashSet<>();

    // ========= UserDetails =========

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si Rol.nombre es enum RolNombre:
        // return roles.stream()
        //        .map(r -> "ROLE_" + r.getNombre().name())
        //        .map(SimpleGrantedAuthority::new)
        //        .collect(Collectors.toSet());

        // Si Rol.nombre es String:
        return roles.stream()
                .map(r -> "ROLE_" + r.getNombre())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override @JsonIgnore
    public String getUsername() { return email; }

    @Override @JsonIgnore
    public String getPassword() { return password; }

    @Override @JsonIgnore
    public boolean isAccountNonExpired() { return true; }

    @Override @JsonIgnore
    public boolean isAccountNonLocked() { return true; }

    @Override @JsonIgnore
    public boolean isCredentialsNonExpired() { return true; }

    @Override @JsonIgnore
    public boolean isEnabled() { return estado; }
}
