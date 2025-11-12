package com.vixcomprei.dto;

import com.vixcomprei.entities.Pagamento;
import com.vixcomprei.enums.StatusPedido;
import com.vixcomprei.enums.TipoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoDto {

    private Long id;
    private TipoPagamento tipoPagamento;
    private StatusPedido status;
    private Double valor;
    private Instant momento;
    private Long pedidoId;

    public PagamentoDto(Long id, TipoPagamento tipoPagamento, StatusPedido status, Double valor, Instant momento, Long pedidoId) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.status = status;
        this.valor = valor;
        this.momento = momento;
        this.pedidoId = pedidoId;
    }
    public PagamentoDto(Pagamento entity) {
        this.id = entity.getId();
        this.tipoPagamento = entity.getTipoPagamento();
        this.status = entity.getStatus();
        this.valor = entity.getValor();
        this.momento = entity.getMomento();
        this.pedidoId = (entity.getPedido() != null) ? entity.getPedido().getId() : null;
    }


}
