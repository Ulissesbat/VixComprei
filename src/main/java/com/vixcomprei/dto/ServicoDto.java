package com.vixcomprei.dto;

import com.vixcomprei.entities.Categoria;
import com.vixcomprei.entities.Servico;
import com.vixcomprei.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Double precoBase;
    private Long categoriaId;
    private Long usuarioPrestadorId;
    private boolean disponivel;

    // ✅ Construtor que converte Entidade → DTO
    public ServicoDto(Servico entity) {
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.precoBase = entity.getPrecoBase();
        this.categoriaId = entity.getCategoria() != null ? entity.getCategoria().getId() : null;
        this.usuarioPrestadorId = entity.getUsuarioPrestador() != null ? entity.getUsuarioPrestador().getId() : null;
        this.disponivel = entity.isDisponivel();
    }

    // ✅ Método que converte DTO → Entidade
    public Servico toEntity() {
        Servico servico = new Servico();
        servico.setId(this.id);
        servico.setTitulo(this.titulo);
        servico.setDescricao(this.descricao);
        servico.setPrecoBase(this.precoBase);
        servico.setDisponivel(this.disponivel);

        // ⚙️ Configura a Categoria pelo ID (service deve validar e buscar a entidade completa)
        if (this.categoriaId != null) {
            Categoria categoria = new Categoria();
            categoria.setId(this.categoriaId);
            servico.setCategoria(categoria);
        }

        // ⚙️ Configura o Usuário Prestador pelo ID
        if (this.usuarioPrestadorId != null) {
            Usuario usuario = new Usuario();
            usuario.setId(this.usuarioPrestadorId);
            servico.setUsuarioPrestador(usuario);
        }

        return servico;
    }
}
