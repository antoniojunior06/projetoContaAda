package br.gov.caixa.acao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Acao {

    private LocalDate data;
    private TipoAcao tipo;
    private BigDecimal valor;
    private String usuarioOrigem;
    private String usuarioDestino;
    private String observacao;

    public Acao(TipoAcao tipo, BigDecimal valor, String observacao) {
        this.data = LocalDate.now();
        this.tipo = tipo;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Acao(TipoAcao tipo, BigDecimal valor, String usuarioOrigem, String usuarioDestino) {
        this.data = LocalDate.now();
        this.tipo = tipo;
        this.valor = valor;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.observacao = observacao;
    }

    public LocalDate getData() {
        return data;
    }
    public TipoAcao getTipo() {
        return tipo;
    }
    public String getUsuarioOrigem() {
        return usuarioOrigem;
    }
    public String getUsuarioDestino() {
        return usuarioDestino;
    }
    public String getObservacao() {
        return observacao;
    }
    public BigDecimal getValor() {
        return valor;
    }
}
