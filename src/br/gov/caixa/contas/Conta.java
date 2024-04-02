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
        this.classificacao = usuarioId.length() == 11 ? Classificacao.PF : Classificacao.PJ;
    }

    public void sacar(double valor) {
        verificarStatusConta();
        if (classificacao == Classificacao.PJ) {
            double valorComTaxa = valor + (valor * 0.005);
            if(valorComTaxa > getSaldo()) {
                historico.add(new Acao(this.dataAtualizacao, TipoAcao.SAQUE, valor, 0));
                System.out.println("Saldo insuficiente");
            } else {
                this.saldo -= valorComTaxa;
                historico.add(new Acao(this.dataAtualizacao, TipoAcao.SAQUE, valor, valor));
            }
            return;
        }
        if (valor <= consultaSaldo()) {
            this.saldo -= valor;
            historico.add(new Acao(this.dataAtualizacao, TipoAcao.SAQUE, valor, valor));
        } else {
            historico.add(new Acao(this.dataAtualizacao, TipoAcao.SAQUE, valor, 0));
            System.out.println("Saldo insuficiente");
        }

    }

    public void depositar(double valor) {
        verificarStatusConta();
        this.saldo += valor;
        historico.add(new Acao(this.dataAtualizacao, TipoAcao.DEPOSITO, valor));
    }

    public <T extends Conta> void transferir(double valor, T contaDestino, Banco banco) {
        verificarStatusConta();
        if (valor <= consultaSaldo()) {
           if(banco.temUsuario(contaDestino.getUsuarioId())) {
               this.saldo -= valor;
               contaDestino.depositar(valor);
               if (contaDestino instanceof ContaInvestimento) {
                   historico.add(new Acao(this.dataAtualizacao, TipoAcao.INVESTIMENTO, valor, valor, this.usuarioId, contaDestino.getUsuarioId()));
               }else {
                   historico.add(new Acao(this.dataAtualizacao, TipoAcao.TRANSFERENCIA, valor, valor, this.usuarioId, contaDestino.getUsuarioId()));
               }
               System.out.println("Transferência realizada com sucesso");
           } else {
               System.out.println("Conta inválida");
           }
        }else {
            historico.add(new Acao(this.dataAtualizacao, TipoAcao.TRANSFERENCIA, valor, 0, this.usuarioId, contaDestino.getUsuarioId()));
            System.out.println("Saldo insuficiente");
        }
    }

    public void verificarStatusConta() {
        if (getStatus() == Status.INATIVO) {
            throw new IllegalArgumentException("A conta está inativa");
        }
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
