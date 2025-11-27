package com.vixcomprei.dto;

import com.vixcomprei.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDto {

    private Long id;
    private String nome;
    private String tipo;
    private  String descricao;


    public CategoriaDto(Categoria entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.tipo = entity.getTipo();
        this.descricao = entity.getDescricao();
    }

    public Categoria toEntity() {
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNome(this.nome);
        categoria.setTipo(this.tipo);
        categoria.setDescricao(this.descricao);
        return categoria;
    }

}
