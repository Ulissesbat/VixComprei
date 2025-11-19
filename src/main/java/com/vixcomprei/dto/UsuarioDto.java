package com.vixcomprei.dto;

import com.vixcomprei.entities.*;
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
    private String senha;
    private String telefone;
    private String endereco;
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataCadastro;

    private List<PedidoMinDto> pedidos;


    public UsuarioDto(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
        this.telefone = entity.getTelefone();
        this.endereco = entity.getEndereco();
        this.tipoUsuario = entity.getTipoUsuario();
        this.dataCadastro = entity.getDataCadastro();

        pedidos = entity.getPedidos()
                .stream()
                .map(PedidoMinDto::new)
                .toList();

    }
}
