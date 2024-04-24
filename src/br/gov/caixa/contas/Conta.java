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
    private List<Acao> historico = new ArrayList<>();
    private LocalDate dataCriacao;
    private Cliente cliente;
    private Status status;


    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numero = new Random().nextInt();
        this.dataCriacao = LocalDate.now();
        this.saldo = BigDecimal.ZERO;
        this.status = Status.ATIVO;
//        this.classificacao = Classificacao.verificarClassificacao(usuarioId);
    }


//    public void sacar(double valor) {
//        ValidadorStatusConta.validarStatusConta(this);
//        double valorComTaxa = valor + CalculoTaxaConta.calcularTaxa(valor, this);
//        if(valorComTaxa > this.getSaldo()) {
//            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.SAQUE, valor, Constantes.VALOR_ZERADO, "Saldo insuficiente"));
//        } else {
//            this.setSaldo(this.getSaldo() - valorComTaxa);
//            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.SAQUE, valor, valor, "Saque efetivado"));
//        }
//    }
//
//
//    public void depositar(double valor) {
//        ValidadorStatusConta.validarStatusConta(this);
//        this.setSaldo(getSaldo() + valor);
//        this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.DEPOSITO, valor));
//    }
//
//
//    public void transferir(double valor, Conta contaDestino, Banco banco) {
//        ValidadorStatusConta.validarStatusConta(this);
//        if (valor <= this.getSaldo()) {
//            if(banco.temUsuario(contaDestino.getUsuarioId())) {
//                this.setSaldo(this.getSaldo() - valor);
//                contaDestino.depositar(valor);
//                if (contaDestino.getTipo().equals(TipoConta.INVESTIMENTO)) {
//                    this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.INVESTIMENTO, valor, valor, this.getUsuarioId(), contaDestino.getUsuarioId()));
//                } else {
//                    this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, valor, this.getUsuarioId(), contaDestino.getUsuarioId()));
//                }
//                System.out.println("Transferência realizada com sucesso");
//            } else {
//                System.out.println("Conta inválida");
//            }
//        } else {
//            this.getHistorico().add(new Acao(this.getDataAtualizacao(), TipoAcao.TRANSFERENCIA, valor, Constantes.VALOR_ZERADO, this.getUsuarioId(), contaDestino.getUsuarioId()));
//            System.out.println("Saldo insuficiente");
//        }
//    }

//    public abstract TipoConta getTipo();


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
