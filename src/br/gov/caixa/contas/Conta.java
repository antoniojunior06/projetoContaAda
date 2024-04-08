package br.gov.caixa.contas;

import br.gov.caixa.*;
import br.gov.caixa.acao.Acao;
import br.gov.caixa.acao.TipoAcao;
import br.gov.caixa.status.Constantes;
import br.gov.caixa.status.Status;
import br.gov.caixa.usuario.Classificacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conta implements ContaService{
    private long id;
    private double saldo;
    private List<Acao> historico = new ArrayList<>();
    private LocalDate dataAtualizacao = LocalDate.now();;
    private Status status = Status.ATIVO;
    private String usuarioId;
    private Classificacao classificacao;


    public Conta(String usuarioId) {
        this.usuarioId = usuarioId;
        this.classificacao = Classificacao.verificarClassificacao(usuarioId);
    }

    @Override
    public void sacar(double valor) {
        this.verificarStatusConta();
        double valorComTaxa = valor + calcularTaxa(valor, this);
        if(valorComTaxa > this.getSaldo()) {
            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.SAQUE, valor, Constantes.VALOR_ZERADO));
            System.out.println("Saldo insuficiente");
        } else {
            this.setSaldo(this.getSaldo() - valorComTaxa);
            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.SAQUE, valor, valor));
        }
    }

    @Override
    public void depositar(double valor) {
        this.verificarStatusConta();
        this.setSaldo(getSaldo() + valor);
        this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.DEPOSITO, valor));
    }

    @Override
    public void transferir(double valor, Conta contaDestino, Banco banco) {
        this.verificarStatusConta();
        if (valor <= this.getSaldo()) {
            if(banco.temUsuario(contaDestino.getUsuarioId())) {
                this.setSaldo(this.getSaldo() - valor);
                contaDestino.depositar(valor);
                if (contaDestino instanceof ContaInvestimento) {
                    this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.INVESTIMENTO, valor, valor, this.getUsuarioId(), contaDestino.getUsuarioId()));
                } else {
                    this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, valor, this.getUsuarioId(), contaDestino.getUsuarioId()));
                }
                System.out.println("Transferência realizada com sucesso");
            } else {
                System.out.println("Conta inválida");
            }
        } else {
            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, 0, this.getUsuarioId(), contaDestino.getUsuarioId()));
            System.out.println("Saldo insuficiente");
        }
    }

    @Override
    public double consultaSaldo() {
        this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.DEPOSITO, this.saldo));
        return this.saldo;
    }

    public void verificarStatusConta() {
        if (getStatus() == Status.INATIVO) {
            throw new IllegalArgumentException("A conta está inativa");
        }
    }

    private double calcularTaxa(double valor, Conta conta) {
        if (conta.getClassificacao() == Classificacao.PJ) {
            return valor * Constantes.TAXA_SAQUE_PJ;
        }
        return valor * Constantes.TAXA_SAQUE_PF;
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
