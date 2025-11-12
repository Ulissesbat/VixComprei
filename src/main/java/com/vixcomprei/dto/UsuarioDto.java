package com.vixcomprei.dto;

import com.vixcomprei.entities.Usuario;
import com.vixcomprei.enums.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataCadastro;

    private List<ProdutoDto> produtos;
    private List<PedidoDto> pedidos;
    private List<RestauranteDto> restaurantes;
    private List<ServicoDto> servicos;

    public UsuarioDto(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
        this.endereco = entity.getEndereco();
        this.tipoUsuario = entity.getTipoUsuario();
        this.dataCadastro = entity.getDataCadastro();

        this.produtos = entity.getProdutos().stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());

        this.pedidos = entity.getPedido().stream()
                .map(PedidoDto::new)
                .collect(Collectors.toList());

        this.restaurantes = entity.getRestaurantes().stream()
                .map(RestauranteDto::new)
                .collect(Collectors.toList());

        this.servicos = entity.getServicos().stream()
                .map(ServicoDto::new)
                .collect(Collectors.toList());
    }

}
