package br.gov.caixa;

import br.gov.caixa.cliente.Cliente;

public class AplicacaoConta {
    public static void main(String[] args) {
//        Banco banco = new Banco();
//        banco.criarUsuario("12345678901", "João");
//        banco.criarUsuario("12345678901234", "Maria e cia");
//        banco.criarContaPoupanca("12345678901");
//        banco.criarContaInvestimento("12345678901");
//        banco.criarContaInvestimento("12345678901234");
//        banco.criarContaPoupanca("12345678902");

        Cliente cliente = new Cliente("12345678901", "João");
        System.out.println(cliente.getClassificacao());
        System.out.println(cliente.getStatus());
        System.out.println(cliente.getContas().get(0).getNumero());



    }
}
