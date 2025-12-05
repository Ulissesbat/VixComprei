package com.vixcomprei.dto;

import com.vixcomprei.entities.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserDTO {

    private Long id;
    private String email;
    private String password;

    private UsuarioDto usuarioDto;

    private List<String> roles = new ArrayList<>();

    public AuthUserDTO(AuthUser entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();

        if (entity.getUsuario() != null) {
            this.usuarioDto = new UsuarioDto(entity.getUsuario());
        }

        entity.getRoles().forEach(role -> this.roles.add(role.getAuthority()));
    }


}