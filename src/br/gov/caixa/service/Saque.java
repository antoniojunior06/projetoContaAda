package br.gov.caixa.service;

import br.gov.caixa.cliente.Cliente;
import java.math.BigDecimal;
import br.gov.caixa.contas.Conta;


public interface Saque {

    void sacar(Cliente cliente, Integer numeroConta, BigDecimal valor);

}
