package br.gov.caixa.usuario;

import br.gov.caixa.status.Status;

import java.time.LocalDate;

public class Usuario {
    private String id;
    private Classificacao classificacao;
    private String nome;
    private LocalDate dataCadastro = LocalDate.now();
    private Status status = Status.ATIVO;

    public Usuario(String id, String nome) {
        this.id = id;
        this.classificacao = Classificacao.verificarClassificacao(id);
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
