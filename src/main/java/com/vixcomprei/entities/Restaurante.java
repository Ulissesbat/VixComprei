package com.vixcomprei.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_restaurante")
public class Restaurante {

    private Long id;
    private String nome;
    private String descricao;
    private Categoria categoria;
    private String endereco;
    private String telefone;
    private Integer tempoMedioEntrega;
    private Usuario usuarioResponsavel;
    private List<Produto> produtos;
}
