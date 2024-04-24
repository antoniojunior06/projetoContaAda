package br.gov.caixa.service;

import br.gov.caixa.contas.Conta;
import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;

public interface Transferencia {

    void transferir(Cliente cliente, Integer numeroContaOrigem, Conta destino, BigDecimal valor);

}
