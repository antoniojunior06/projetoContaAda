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

}
