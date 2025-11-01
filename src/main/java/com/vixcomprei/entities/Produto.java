package com.vixcomprei.entities;

import com.vixcomprei.enums.CondicaoProduto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private CondicaoProduto condicaoProduto;
    private Categoria categoria;
    private List<String> imagens;
    private Usuario usuario;
    private LocalDateTime dataPublicacao;
    private boolean ativo;
}
