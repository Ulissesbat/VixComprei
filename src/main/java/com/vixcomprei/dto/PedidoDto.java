package com.vixcomprei.dto;

import com.vixcomprei.entities.ItemPedido;
import com.vixcomprei.entities.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDto {

    private Long id;
    private LocalDateTime dataPedido;
    private String status;
    private Double total;
    private String tipoOrigem;
    private PagamentoDto pagamento;
    private Set<ItemPedidoDto> itens;
    private Long usuarioId;

    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus() == null ? null : pedido.getStatus().name();
        this.total = pedido.getTotal();
        this.tipoOrigem = pedido.getTipoOrigem() == null ? null : pedido.getTipoOrigem().name();
        this.usuarioId = pedido.getUsuario() == null ? null : pedido.getUsuario().getId();

        if (pedido.getPagamento() != null) {
            this.pagamento = new PagamentoDto(pedido.getPagamento());
        }

        this.itens = pedido.getItens() == null
                ? null
                : pedido.getItens().stream()
                .map(this::mapItem)
                .collect(Collectors.toSet());
    }

    private ItemPedidoDto mapItem(ItemPedido ip) {
        Long produtoId = null;
        Long pedidoId = null;
        Integer quantidade = null;
        Double precoUnitario = null;
        Double subTotal = null;

        try {
            if (ip.getProduto() != null) {
                produtoId = ip.getProduto().getId();
            } else if (ip.getId() != null && ip.getId().getProduto() != null) {
                produtoId = ip.getId().getProduto().getId();
            }

            if (ip.getPedido() != null) {
                pedidoId = ip.getPedido().getId();
            } else if (ip.getId() != null && ip.getId().getPedido() != null) {
                pedidoId = ip.getId().getPedido().getId();
            }
        } catch (Exception ignore) {
        }

        try {
            quantidade = ip.getQuantidade();
        } catch (Exception ignore) {
        }
        try {
            precoUnitario = ip.getPrecoUnitario();
        } catch (Exception ignore) {
        }

        try {
            subTotal = ip.getSubTotal();
        } catch (Exception e) {
            subTotal = (precoUnitario == null || quantidade == null)
                    ? 0.0
                    : precoUnitario * quantidade;
        }

        return new ItemPedidoDto(
                produtoId,
                pedidoId,
                quantidade != null ? quantidade : 0,
                precoUnitario != null ? precoUnitario : 0.0,
                subTotal != null ? subTotal : 0.0
        );
    }

}
