package com.vixcomprei.entities;

import com.vixcomprei.enums.StatusPedido;
import com.vixcomprei.enums.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    private Long id;
    private TipoPagamento tipoPagamento;
    private StatusPedido status;
    private Double valor;
    private LocalDateTime dataPagamento;
}
