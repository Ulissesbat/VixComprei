package com.vixcomprei.services;

import com.vixcomprei.dto.RestauranteDto;
import com.vixcomprei.dto.ServicoDto;
import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Restaurante;
import com.vixcomprei.entities.Servico;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.repositories.CategoriaRepository;
import com.vixcomprei.repositories.RestauranteRepository;
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
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<RestauranteDto> findAllPaged(Pageable pageable) {
        Page<Restaurante> list = restauranteRepository.findAll(pageable);
        return list.map(RestauranteDto::new);
    }

    @Transactional(readOnly = true)
    public RestauranteDto findById(Long id) {
        Optional<Restaurante> obj = restauranteRepository.findById(id);
        Restaurante entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new RestauranteDto(entity);
    }
    public RestauranteDto insert(RestauranteDto dto) {

        Restaurante entity = new Restaurante();
        copyDtoToEntity(dto, entity);
        entity = restauranteRepository.save(entity);
        return new RestauranteDto (entity);
    }

    @Transactional
    public RestauranteDto update (Long id, RestauranteDto dto) throws ResourceNotFoundException {

        try {
            Restaurante entity = restauranteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = restauranteRepository.save(entity);
            return new RestauranteDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!restauranteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            restauranteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(RestauranteDto dto, Restaurante entity) {

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity.setTempoMedioEntrega(dto.getTempoMedioEntrega());

        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + dto.getCategoriaId()));
            entity.setCategoria(categoria);
        }

        // Usuário prestador (ManyToOne)
        if (dto.getUsuarioResponsavelId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioResponsavelId())
                    .orElseThrow(() -> new RuntimeException("Usuário prestador não encontrado: " + dto.getUsuarioResponsavelId()));
            entity.setUsuarioResponsavel(usuario);
        }
    }
}
