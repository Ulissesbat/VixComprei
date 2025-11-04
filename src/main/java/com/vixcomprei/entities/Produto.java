package com.vixcomprei.entities;

import com.vixcomprei.enums.CondicaoProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    @Enumerated(EnumType.STRING)
    private CondicaoProduto condicaoProduto;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Categoria categoria;

    @ElementCollection
    @CollectionTable(name = "tb_produto_imagem", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "imagem_url")
    private List<String> imagens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    private LocalDateTime dataPublicacao;
    private boolean ativo;
}
