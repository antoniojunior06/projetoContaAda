package br.gov.caixa;

import br.gov.caixa.acao.Acao;
import br.gov.caixa.contas.ContaCorrente;
import br.gov.caixa.contas.ContaInvestimento;
import br.gov.caixa.contas.ContaPoupanca;

import java.util.List;

public class AplicacaoConta {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.criarUsuario("12345678901", "João");
        banco.criarUsuario("12345678901234", "Maria e cia");
        banco.criarContaPoupanca("12345678901");
        banco.criarContaInvestimento("12345678901");
        banco.criarContaInvestimento("12345678901234");
        banco.criarContaPoupanca("12345678902");

        List<ContaCorrente> ccs = banco.getContasCorrente();
        for (ContaCorrente cc: ccs) {
            System.out.printf("%s, %s, %d, %s\n", cc.getClassificacao(), cc.getTipo(), cc.getId(), cc.getUsuarioId());
        }
        List<ContaPoupanca> cps = banco.getContasPoupanca();
        for (ContaPoupanca cp: cps) {
            System.out.printf("%s, %s, %d, %s\n", cp.getClassificacao(), cp.getTipo(), cp.getId(), cp.getUsuarioId());
        }
        List<ContaInvestimento> cis = banco.getContasInvestimento();
        for (ContaInvestimento ci: cis) {
            System.out.printf("%s, %s, %d, %s\n", ci.getClassificacao(), ci.getTipo(), ci.getId(), ci.getUsuarioId());
        }

        System.out.println("--------------------");


        ContaCorrente cc = banco.buscarContaPorId(1, ccs);
        ContaCorrente cc1 = banco.buscarContaPorId(2, ccs);
        ContaInvestimento ci1 = banco.buscarContaPorId(1, cis);
        ContaInvestimento ci2 = banco.buscarContaPorId(2, cis);
        cc.depositar(500);
        cc1.depositar(200);
        cc.transferir(100, cc1, banco);
        cc.investir(200, ci1, banco);
        System.out.println("...");
        System.out.println("Saldo: " + cc.consultaSaldo());
        System.out.println("Saldo: " + cc1.consultaSaldo());
        System.out.println("Saldo: " + ci1.consultaSaldo());
        System.out.println("...");


        List<Acao> historico = cc.getHistorico();
        for (Acao acao: historico) {
            System.out.printf("%s, %s, %f, %f, %s, %s, %s\n", acao.getData(), acao.getTipo(), acao.getValorPretendido(), acao.getValorReal(), acao.getUsuarioOrigem(), acao.getUsuarioDestino(), acao.getObservacao());
        }


        // Saque
//        ContaCorrente cc = banco.buscarContaPorId(1, ccs);
//        cc.deposito(500);
//        System.out.println(cc.getSaldo());
//        cc.saque(500);
//        System.out.println(cc.getSaldo());


//        Conta conta = banco.buscarContaPorId(2, ccs);
//        System.out.println(conta.getClassificacao());

//        banco.criarUsuario("125", "Maria e cia");
//        List<Usuario> usuarios = banco.getUsuarios();
//        for (Usuario usuario : usuarios) {
//            System.out.println(usuario.getId() + " - " +
//                    usuario.getClassificacao() + " - " +
//                    usuario.getNome() + " - " +
//                    usuario.getDataCadastro() + " - " +
//                    usuario.getStatus());
//        }



//        Conta c = banco.buscarContaPorId(2);
////        c.deposito(500);
////        c.saque(250);

//        banco.criarContaInvestimento("111");
//        banco.criarContaPoupanca("111", Classificacao.PF);
//
//        ContaInvestimento cInvest = banco.buscarContaInvestimentoPorId(1);
//
//        ContaCorrente cc = banco.buscarContaCorrentePorId(1);
//        cc.deposito(500);
//        cc.transferencia(100, cInvest, banco);
//
//
//        List<ContaCorrente> contas = banco.getContasCorrente();
//        for (ContaCorrente conta : contas) {
//            System.out.println(conta.getId() + " - " +
//                    conta.getStatus() + " - " +
//                    conta.getDataAtualizacao() + " - " +
//                    conta.getUsuarioId() + " - " +
//                    conta.consultaSaldo());
//            System.out.println(conta.getClassificacao() + " - " + conta.getTipo());
//        }
//
//        System.out.println("-------------------------");
//
//        List<ContaInvestimento> cis = banco.getContasInvestimento();
//        for (ContaInvestimento ci : cis) {
//            System.out.println(ci.getId() + " - " +
//                    ci.getStatus() + " - " +
//                    ci.getDataAtualizacao() + " - " +
//                    ci.getUsuarioId() + " - " +
//                    ci.consultaSaldo());
//            System.out.println(ci.getClassificacao() + " - " + ci.getTipo());
//        }
//


    }
}
