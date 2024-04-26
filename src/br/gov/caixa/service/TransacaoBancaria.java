package br.gov.caixa.service;

import br.gov.caixa.acao.Acao;
import br.gov.caixa.acao.TipoAcao;
import br.gov.caixa.cliente.Classificacao;
import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.contas.Conta;
import br.gov.caixa.contas.ContaCorrente;
import br.gov.caixa.contas.ContaInvestimento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TransacaoBancaria implements Deposito, Saque, Transferencia, Saldo, Investimento {
    private static final BigDecimal TAXA_SAQUE_PJ = BigDecimal.valueOf(1.005);
    private static final BigDecimal TAXA_SAQUE_PF = BigDecimal.valueOf(1);
    private static final BigDecimal TAXA_RENDIMENTO_PF = BigDecimal.valueOf(1.01);
    private static final BigDecimal TAXA_RENDIMENTO_PJ = BigDecimal.valueOf(1.02);
    private List<Acao> historico = new ArrayList<>();

    @Override
    public void depositar(Cliente cliente, Integer numeroConta, BigDecimal valor) {
        Conta conta = getContaCliente(cliente, numeroConta);
        conta.setSaldo(conta.getSaldo().add(valor));
        historicoAcaoGenerico(TipoAcao.DEPOSITO, valor, "Depósito efetivado");
    }

    @Override
    public void investir(Cliente cliente, Integer numeroConta, BigDecimal valor) {
        Conta contaCorrente = getContaCliente(cliente, numeroConta);
        Conta contaInvestimento = getContaInvestimento(cliente);
        transferir(cliente, contaCorrente.getNumero(), contaInvestimento, valor);
        historicoAcaoGenerico(TipoAcao.INVESTIMENTO, valor, "Investimento efetivado");
    }

    @Override
    public BigDecimal consultarSaldo(Cliente cliente, Integer numeroConta) {
        Conta conta = getContaCliente(cliente, numeroConta);
        return conta.getSaldo().setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void sacar(Cliente cliente, Integer numeroConta, BigDecimal valor) {
        Conta conta = getContaCliente(cliente, numeroConta);
        BigDecimal valorComTaxa = calcularTaxa(valor, cliente);
        if (valorComTaxa.compareTo(conta.getSaldo()) > 0) {
            historicoAcaoGenerico(TipoAcao.SAQUE, valor, "Saldo insuficiente");
            throw new RuntimeException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));
        historicoAcaoGenerico(TipoAcao.SAQUE, valor, "Saque efetivado");
    }

    @Override
    public void transferir(Cliente cliente, Integer numeroContaOrigem, Conta destino, BigDecimal valor) {
        sacar(cliente, numeroContaOrigem, valor);
        depositar(destino.getCliente(), destino.getNumero(), valor);
        historicoAcaoGenerico(TipoAcao.TRANSFERENCIA, valor, "Transferência efetivada");

    }

    private static Conta getContaCliente(Cliente cliente, Integer numeroConta) {
        return cliente.getContas().stream()
                .filter(conta -> conta.getNumero().equals(numeroConta))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Conta não encontrada"));
    }

    private static Conta getContaInvestimento(Cliente cliente) {
        Conta contaInvestimento = cliente.getContas().stream()
                .filter(conta -> conta instanceof ContaInvestimento)
                .findFirst()
                .orElse(new ContaInvestimento(cliente));

        cliente.getContas().add(contaInvestimento);

        return contaInvestimento;
    }

    private static BigDecimal calcularTaxa(BigDecimal valor, Cliente cliente) {
        if (cliente.getClassificacao() == Classificacao.PJ) {
            return valor.multiply(TAXA_SAQUE_PJ);
        }
        return valor.multiply(TAXA_SAQUE_PF);
    }

    private void historicoAcaoGenerico(TipoAcao deposito, BigDecimal valor, String observacao) {
        this.getHistorico().add(new Acao(deposito, valor, observacao));
    }

    public List<Acao> getHistorico() {
        return historico;
    }
}
