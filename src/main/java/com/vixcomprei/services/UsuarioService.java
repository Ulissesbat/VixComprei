package com.vixcomprei.services;

import com.vixcomprei.dto.UsuarioDto;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioDto> findAllPaged(Pageable pageable) {
        Page<Usuario> list = usuarioRepository.findAll(pageable);
        return list.map(UsuarioDto::new);
    }


    public UsuarioDto insert(UsuarioDto dto) {

        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity = usuarioRepository.save(entity);
        return new UsuarioDto (entity);
    }

    private void copyDtoToEntity(UsuarioDto dto, Usuario entity) {

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        entity.setTipoUsuario(dto.getTipoUsuario());
        entity.setDataCadastro(dto.getDataCadastro());

    }


}
