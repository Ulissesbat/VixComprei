package com.vixcomprei.services;

import com.vixcomprei.dto.UsuarioDto;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.repositories.UsuarioRepository;
import com.vixcomprei.services.exception.DatabaseException;
import com.vixcomprei.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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

    @Transactional(readOnly = true)
    public UsuarioDto findById(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new UsuarioDto(entity);
    }


    public UsuarioDto insert(UsuarioDto dto) {

        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity = usuarioRepository.save(entity);
        return new UsuarioDto (entity);
    }

    @Transactional
    public UsuarioDto update (Long id, UsuarioDto dto) throws ResourceNotFoundException {

        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = usuarioRepository.save(entity);
            return new UsuarioDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
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
