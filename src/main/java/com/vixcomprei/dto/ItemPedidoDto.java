package com.vixcomprei.dto;

import com.vixcomprei.entities.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDto {

    private Long produtoId;
    private Long pedidoId;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subTotal;

    public ItemPedidoDto(Long produtoId, Long pedidoId, Integer quantidade, Double precoUnitario, Double subTotal) {
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subTotal = subTotal;
    }

        public ItemPedidoDto(ItemPedido entity) {
            this.produtoId = (entity.getProduto() != null) ? entity.getProduto().getId() : null;
            this.pedidoId = (entity.getPedido() != null) ? entity.getPedido().getId() : null;
            this.quantidade = entity.getQuantidade();
            this.precoUnitario = entity.getPrecoUnitario();
            this.subTotal = entity.getSubTotal();

    }
}
