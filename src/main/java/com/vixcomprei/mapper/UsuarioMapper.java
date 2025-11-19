package com.vixcomprei.mapper;

import com.vixcomprei.dto.UsuarioDto;
import com.vixcomprei.entities.Usuario;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final ModelMapper mapper;

    public UsuarioMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UsuarioDto toDto(Usuario entity) {
        return mapper.map(entity, UsuarioDto.class);
    }

    public Usuario toEntity(UsuarioDto dto) {
        return mapper.map(dto, Usuario.class);
    }
}
