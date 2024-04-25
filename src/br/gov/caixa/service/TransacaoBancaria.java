package br.gov.caixa.service;

import br.gov.caixa.cliente.Classificacao;
import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.contas.Conta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;

public class TransacaoBancaria implements Deposito, Saque, Transferencia, Saldo, Investimento {
    private static final BigDecimal TAXA_SAQUE_PJ = BigDecimal.valueOf(1.005);
    private static final BigDecimal TAXA_SAQUE_PF = BigDecimal.valueOf(1);
    private static final BigDecimal TAXA_RENDIMENTO_PF = BigDecimal.valueOf(1.01);
    private static final BigDecimal TAXA_RENDIMENTO_PJ = BigDecimal.valueOf(1.02);

    @Override
    public void depositar(Cliente cliente, Integer numeroConta, BigDecimal valor) {
        Conta conta = getContaCliente(cliente, numeroConta);
        conta.setSaldo(conta.getSaldo().add(valor));
    }

    @Override
    public void investir(Cliente cliente, BigDecimal valor) {

    }

    @Override
    public BigDecimal consultarSaldo(Cliente cliente, Integer numeroConta) {
        Conta conta = getContaCliente(cliente, numeroConta);
        return conta.getSaldo().setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void sacar(Cliente cliente, Integer numeroConta, BigDecimal valor) {
        Conta conta = getContaCliente(cliente, numeroConta);
        conta.setSaldo(conta.getSaldo().subtract(valor));
    }

    @Override
    public void transferir(Cliente cliente, Integer numeroContaOrigem, Conta destino, BigDecimal valor) {
        sacar(cliente, numeroContaOrigem, valor);
        depositar(destino.getCliente(), destino.getNumero(), valor);

    }

    private static Conta getContaCliente(Cliente cliente, Integer numeroConta) {
        return cliente.getContas().stream()
                .filter(conta -> conta.getNumero().equals(numeroConta))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Conta não encontrada"));
    }

    public static BigDecimal calcularTaxa(BigDecimal valor, Cliente cliente) {
        if (cliente.getClassificacao() == Classificacao.PJ) {
            return valor.multiply(TAXA_SAQUE_PJ);
        }
        return valor.multiply(TAXA_SAQUE_PF);
    }
}
