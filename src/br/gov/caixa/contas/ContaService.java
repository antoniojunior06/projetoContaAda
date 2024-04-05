package br.gov.caixa.contas;

import br.gov.caixa.Banco;
import br.gov.caixa.acao.Acao;
import br.gov.caixa.acao.TipoAcao;
import br.gov.caixa.usuario.Classificacao;

public class ContaService {
    public void sacar(Conta conta, double valor) {
        conta.verificarStatusConta();
        if (conta.getClassificacao() == Classificacao.PJ) {
            double valorComTaxa = valor + calcularTaxa(valor);
            if(valorComTaxa > conta.getSaldo()) {
                conta.getHistorico().add(new Acao(conta.getDataAtualizacao(), TipoAcao.SAQUE, valor, 0));
                System.out.println("Saldo insuficiente");
            } else {
                conta.setSaldo(conta.getSaldo() - valorComTaxa);
                conta.getHistorico().add(new Acao(conta.getDataAtualizacao(), TipoAcao.SAQUE, valor, valor));
            }
            return;
        }
        if (valor <= conta.consultaSaldo()) {
            conta.setSaldo(conta.getSaldo() - valor);
            conta.getHistorico().add(new Acao(conta.getDataAtualizacao(), TipoAcao.SAQUE, valor, valor));
        } else {
            conta.getHistorico().add(new Acao(conta.getDataAtualizacao(), TipoAcao.SAQUE, valor, 0));
            System.out.println("Saldo insuficiente");
        }
    }

    public void depositar(Conta conta, double valor) {
        conta.verificarStatusConta();
        conta.setSaldo(conta.getSaldo() + valor);
        conta.getHistorico().add(new Acao(conta.getDataAtualizacao(), TipoAcao.DEPOSITO, valor));
    }

    public <T extends Conta> void transferir(T contaOrigem, double valor, T contaDestino, Banco banco) {
        contaOrigem.verificarStatusConta();
        if (valor <= contaOrigem.consultaSaldo()) {
            if(banco.temUsuario(contaDestino.getUsuarioId())) {
                contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
                contaDestino.depositar(valor);
                if (contaDestino instanceof ContaInvestimento) {
                    contaOrigem.getHistorico().add(new Acao(contaOrigem.getDataAtualizacao(), TipoAcao.INVESTIMENTO, valor, valor, contaOrigem.getUsuarioId(), contaDestino.getUsuarioId()));
                } else {
                    contaOrigem.getHistorico().add(new Acao(contaOrigem.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, valor, contaOrigem.getUsuarioId(), contaDestino.getUsuarioId()));
                }
                System.out.println("Transferência realizada com sucesso");
            } else {
                System.out.println("Conta inválida");
            }
        } else {
            contaOrigem.getHistorico().add(new Acao(contaOrigem.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, 0, contaOrigem.getUsuarioId(), contaDestino.getUsuarioId()));
            System.out.println("Saldo insuficiente");
        }
    }

    private double calcularTaxa(double valor) {
        return valor * 0.005;
    }
}
