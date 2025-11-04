package com.vixcomprei.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    @ManyToOne
    @MapsId("produto")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @MapsId("pedido")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private Integer quantidade;
    private Double precoUnitario;
    private Double subTotal;

}
