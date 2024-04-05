package br.gov.caixa.contas;

import br.gov.caixa.*;
import br.gov.caixa.acao.Acao;
import br.gov.caixa.acao.TipoAcao;
import br.gov.caixa.usuario.Classificacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private long id;
    private double saldo;
    private List<Acao> historico = new ArrayList<>();
    private LocalDate dataAtualizacao = LocalDate.now();;
    private Status status = Status.ATIVO;
    private String usuarioId;
    private Classificacao classificacao;


    public Conta(String usuarioId) {
        this.usuarioId = usuarioId;
        this.classificacao = verificaClassificacao(usuarioId);
    }

    public void sacar(double valor) {
        ContaService contaService = new ContaService();
        contaService.sacar(this, valor);
    }

    public void depositar(double valor) {
        ContaService contaService = new ContaService();
        contaService.depositar(this, valor);
    }

    public <T extends Conta> void transferir(double valor, T contaDestino, Banco banco) {
        ContaService contaService = new ContaService();
        contaService.transferir(this, valor, contaDestino, banco);
    }

    public void verificarStatusConta() {
        if (getStatus() == Status.INATIVO) {
            throw new IllegalArgumentException("A conta está inativa");
        }
    }

    private Classificacao verificaClassificacao(String usuarioId) {
        return usuarioId.length() == 11 ? Classificacao.PF : Classificacao.PJ;
    }

    public double consultaSaldo() {
        return this.saldo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Acao> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Acao> historico) {
        this.historico = historico;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

}
