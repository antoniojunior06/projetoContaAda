package br.gov.caixa;

import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.contas.Conta;
import br.gov.caixa.contas.ContaCorrente;
import br.gov.caixa.contas.ContaInvestimento;
import br.gov.caixa.contas.ContaPoupanca;
import br.gov.caixa.service.TransacaoBancaria;

import java.math.BigDecimal;

public class AplicacaoConta {
    public static void main(String[] args) {

        Cliente cliente = new Cliente("12345678901", "João");
        Cliente cliente2 = new Cliente("12345678901234", "Maria e Cia");
        System.out.println(cliente.getClassificacao());
        System.out.println(cliente.getNome());
        Integer contaCliente = cliente.getContas().getFirst().getNumero();
        System.out.println(contaCliente);
        System.out.println("----------------");
        System.out.println(cliente2.getClassificacao());
        System.out.println(cliente2.getNome());
        Integer contaCliente2 = cliente2.getContas().getFirst().getNumero();
        System.out.println(contaCliente2);
        System.out.println("----------------");


        TransacaoBancaria tb = new TransacaoBancaria();
        tb.depositar(cliente, contaCliente, new BigDecimal(1000));
        tb.depositar(cliente2, contaCliente2, new BigDecimal(1000));
        tb.sacar(cliente, contaCliente, new BigDecimal(500));
        tb.sacar(cliente2, contaCliente2, new BigDecimal("990.01"));

        Conta cp = new ContaPoupanca(cliente);
        System.out.println(cp.getNumero());

        tb.investir(cliente, contaCliente, new BigDecimal(220));

        Integer contaInvestimentoCliente = cliente.getContas().get(1).getNumero();

        System.out.println(tb.consultarSaldo(cliente, contaCliente));
        System.out.println(tb.consultarSaldo(cliente2, contaCliente2));
        System.out.println(tb.consultarSaldo(cliente, contaInvestimentoCliente));


    }
}
