package com.vixcomprei.entities;

import com.vixcomprei.enums.StatusPedido;
import com.vixcomprei.enums.TipoOrigemPedido;
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
@Table(name = "tb_pedido")
public class Pedido {

    private Long id;
    private Usuario cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private Double total;
    private TipoOrigemPedido tipoOrigem;
    private Pagamento pagamento;
}
