package br.gov.caixa.contas;

import br.gov.caixa.acao.Acao;
import br.gov.caixa.status.Status;
import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Conta {
    private Integer numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private Status status;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numero = new Random().nextInt();
        this.dataCriacao = LocalDate.now();
        this.saldo = BigDecimal.ZERO;
        this.status = Status.ATIVO;
    }

    public Integer getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
