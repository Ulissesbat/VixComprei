package com.vixcomprei.services;

import com.vixcomprei.dto.AuthUserDTO;
import com.vixcomprei.entities.AuthUser;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;

public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    protected Usuario authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            AuthUser user = authUserRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return user.getUsuario(); // pega o cliente do usuário autenticado
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Transactional(readOnly = true)
    public AuthUserDTO getMe() {
        Usuario usuario = authenticated();
        return new AuthUserDTO(usuario.getAuthUser());
    }

    public void validationSelfOrAdmin(Long usuarioId) {
        Usuario usuario = authenticated();

        // se não for admin e não for o dono do recurso → erro
        boolean isAdmin = usuario.getAuthUser().getRoles()
                .stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !usuario.getId().equals(usuarioId)) {
            throw new AccessDeniedException("Acesso negado");
        }
    }
}
