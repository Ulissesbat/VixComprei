package com.vixcomprei.dto;

import com.vixcomprei.entities.Pedido;
import com.vixcomprei.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoMinDto {

    private Long id;
    private LocalDateTime data;
    private StatusPedido status;


    public PedidoMinDto(Pedido entity) {
        id = entity.getId();
        data = entity.getDataPedido();
        status = entity.getStatus();
    }
}
