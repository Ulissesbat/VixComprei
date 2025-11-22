package com.vixcomprei.services;

import com.vixcomprei.dto.ServicoDto;
import com.vixcomprei.dto.UsuarioDto;
import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Servico;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.repositories.CategoriaRepository;
import com.vixcomprei.repositories.ServicoRepository;
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

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<ServicoDto> findAllPaged(Pageable pageable) {
        Page<Servico> list = servicoRepository.findAll(pageable);
        return list.map(ServicoDto::new);
    }

    @Transactional(readOnly = true)
    public ServicoDto findById(Long id) {
        Optional<Servico> obj = servicoRepository.findById(id);
        Servico entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new ServicoDto(entity);
    }

    public ServicoDto insert(ServicoDto dto) {

        Servico entity = new Servico();
        copyDtoToEntity(dto, entity);
        entity = servicoRepository.save(entity);
        return new ServicoDto (entity);
    }
    @Transactional
    public ServicoDto update (Long id, ServicoDto dto) throws ResourceNotFoundException {

        try {
            Servico entity = servicoRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = servicoRepository.save(entity);
            return new ServicoDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            servicoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }


    private void copyDtoToEntity(ServicoDto dto, Servico entity) {

        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setPrecoBase(dto.getPrecoBase());
        entity.setDisponivel(dto.isDisponivel());

        // Categoria (ManyToOne)
        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + dto.getCategoriaId()));
            entity.setCategoria(categoria);
        }

        // Usuário prestador (ManyToOne)
        if (dto.getUsuarioPrestadorId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioPrestadorId())
                    .orElseThrow(() -> new RuntimeException("Usuário prestador não encontrado: " + dto.getUsuarioPrestadorId()));
            entity.setUsuarioPrestador(usuario);
        }
    }

}
