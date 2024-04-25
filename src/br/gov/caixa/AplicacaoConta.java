package br.gov.caixa;

import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.contas.Conta;
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
        System.out.println(cliente.getContas().getFirst().getNumero());
        System.out.println("----------------");
        System.out.println(cliente2.getClassificacao());
        System.out.println(cliente2.getNome());
        System.out.println(cliente2.getContas().getFirst().getNumero());
        System.out.println("----------------");


        TransacaoBancaria tb = new TransacaoBancaria();
        tb.depositar(cliente, cliente.getContas().getFirst().getNumero(), new BigDecimal(100));

        tb.transferir(cliente, cliente.getContas().getFirst().getNumero(), cliente2.getContas().getFirst(), new BigDecimal(70) );

        System.out.println(cliente.getContas().getFirst().getSaldo());
        System.out.println(cliente2.getContas().getFirst().getSaldo());




    }
}
