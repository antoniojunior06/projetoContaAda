package br.gov.caixa.cliente;

import br.gov.caixa.contas.Conta;
import br.gov.caixa.contas.ContaCorrente;
import br.gov.caixa.status.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private Classificacao classificacao;
    private String nome;
    private LocalDate dataCadastro;
    private Status status;
    private List<Conta> contas;
    private Integer numeroConta;

    public Cliente(String id, String nome) {
        this.id = id;
        this.classificacao = Classificacao.verificarClassificacao(id);
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = Status.ATIVO;
        this.contas = new ArrayList<>();
        this.contas.add(new ContaCorrente(this));

    }

    public String getId() {
        return id;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Status getStatus() {
        return status;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public Integer getNumeroConta() {
        return this.getContas().getFirst().getNumero();
    }
}
