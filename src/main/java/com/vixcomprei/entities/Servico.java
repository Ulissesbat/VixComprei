package com.vixcomprei.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_servico")
public class Servico {
    private Long id;
    private String titulo;
    private String descricao;
    private Categoria categoria;
    private Double precoBase;
    private Usuario usuarioPrestador;
    private boolean disponivel;

}
