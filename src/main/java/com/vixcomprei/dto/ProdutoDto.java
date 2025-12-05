package com.vixcomprei.dto;

import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Produto;
import com.vixcomprei.entities.Restaurante;
import com.vixcomprei.entities.Usuario;
import com.vixcomprei.enums.CondicaoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private CondicaoProduto condicaoProduto;
    private Long categoriaId;
    private Long usuarioId;
    private List<String> imagens;
    private LocalDateTime dataPublicacao;
    private boolean ativo;

    // ✅ Construtor que converte a entidade Produto → DTO
    public ProdutoDto(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
        this.condicaoProduto = entity.getCondicaoProduto();
        this.categoriaId = entity.getCategoria() != null ? entity.getCategoria().getId() : null;
        this.usuarioId = entity.getUsuario() != null ? entity.getUsuario().getId() : null;
        this.imagens = entity.getImagens() != null
                ? List.copyOf(entity.getImagens())
                : List.of();

        this.dataPublicacao = entity.getDataPublicacao();
        this.ativo = entity.isAtivo();
    }

    // ✅ Método para converter o DTO → Entidade Produto
    public Produto toEntity() {
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setCondicaoProduto(this.condicaoProduto);

        // ⚠️ Apenas setamos as referências com IDs, o restante o Service deve buscar no banco
        if (this.categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(this.categoriaId);
            produto.setCategoria(categoria);
        }

        if (this.usuarioId != null) {
            Usuario usuario = new Usuario();
            usuario.setId(this.usuarioId);
            produto.setUsuario(usuario);
        }

        produto.setImagens(this.imagens);
        produto.setDataPublicacao(this.dataPublicacao != null ? this.dataPublicacao : LocalDateTime.now());
        produto.setAtivo(this.ativo);

        return produto;
    }
}
