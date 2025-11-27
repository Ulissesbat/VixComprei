package com.vixcomprei.services;

import com.vixcomprei.dto.CategoriaDto;
import com.vixcomprei.dto.ServicoDto;
import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Servico;
import com.vixcomprei.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<CategoriaDto> findAllPaged(Pageable pageable) {
        Page<Categoria> list = categoriaRepository.findAll(pageable);
        return list.map(CategoriaDto::new);
    }

    @Transactional(readOnly = true)
    public CategoriaDto findById(Long id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new CategoriaDto(entity);
    }

    public CategoriaDto insert(CategoriaDto dto) {

        Categoria entity = new Categoria();
        copyDtoToEntity(dto, entity);
        entity = categoriaRepository.save(entity);
        return new CategoriaDto (entity);
    }

    @Transactional
    public CategoriaDto update (Long id, CategoriaDto dto) throws ResourceNotFoundException {

        try {
            Categoria entity = categoriaRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = categoriaRepository.save(entity);
            return new CategoriaDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(CategoriaDto dto, Categoria entity) {

        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
    }

}
