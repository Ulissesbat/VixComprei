package com.vixcomprei.dto;

import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Produto;
import com.vixcomprei.entities.Restaurante;
import com.vixcomprei.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDto {

    private Long id;
    private String nome;
    private String descricao;
    private Long categoriaId;
    private String endereco;
    private String telefone;
    private Integer tempoMedioEntrega;
    private Long usuarioResponsavelId;
    private List<ProdutoDto> produtos;

    // ✅ Construtor que converte Entidade → DTO
    public RestauranteDto(Restaurante entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.categoriaId = entity.getCategoria() != null ? entity.getCategoria().getId() : null;
        this.endereco = entity.getEndereco();
        this.telefone = entity.getTelefone();
        this.tempoMedioEntrega = entity.getTempoMedioEntrega();
        this.usuarioResponsavelId = entity.getUsuarioResponsavel() != null ? entity.getUsuarioResponsavel().getId() : null;
        this.produtos = entity.getProdutos() != null
                ? entity.getProdutos().stream().map(ProdutoDto::new).collect(Collectors.toList())
                : null;
    }

    // ✅ Método para converter DTO → Entidade
    public Restaurante toEntity() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(this.id);
        restaurante.setNome(this.nome);
        restaurante.setDescricao(this.descricao);
        restaurante.setEndereco(this.endereco);
        restaurante.setTelefone(this.telefone);
        restaurante.setTempoMedioEntrega(this.tempoMedioEntrega);

        // ⚙️ Referências por ID (service validará com repositórios)
        if (this.categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(this.categoriaId);
            restaurante.setCategoria(categoria);
        }

        if (this.usuarioResponsavelId != null) {
            Usuario usuario = new Usuario();
            usuario.setId(this.usuarioResponsavelId);
            restaurante.setUsuarioResponsavel(usuario);
        }

        // ⚙️ Lista de produtos (se quiser persistir em cascata)
        if (this.produtos != null && !this.produtos.isEmpty()) {
            List<Produto> produtosEntity = this.produtos.stream()
                    .map(ProdutoDto::toEntity)
                    .peek(p -> p.setRestaurante(restaurante)) // mantém relação bidirecional
                    .collect(Collectors.toList());
            restaurante.setProdutos(produtosEntity);
        }

        return restaurante;
    }
}
