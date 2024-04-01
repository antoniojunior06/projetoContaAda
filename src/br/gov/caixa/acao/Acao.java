package br.gov.caixa.acao;

import java.time.LocalDate;

public class Acao {

    private LocalDate data;
    private TipoAcao tipo;
    private double valorPretendido;
    private double valorReal;
    private String usuarioOrigem;
    private String usuarioDestino;
    private String observacao;

    public Acao(LocalDate data, TipoAcao tipo, double valorPretendido, double valorReal) {
        this.data = data;
        this.tipo = tipo;
        this.valorPretendido = valorPretendido;
        this.valorReal = valorReal;
    }

    public Acao(LocalDate data, TipoAcao tipo, double valorReal) {
        this.data = data;
        this.tipo = tipo;
        this.valorReal = valorReal;
    }

    public Acao(LocalDate data, TipoAcao tipo, double valorPretendido, double valorReal, String usuarioOrigem, String usuarioDestino) {
        this.data = data;
        this.tipo = tipo;
        this.valorPretendido = valorPretendido;
        this.valorReal = valorReal;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoAcao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcao tipo) {
        this.tipo = tipo;
    }

    public double getValorPretendido() {
        return valorPretendido;
    }

    public void setValorPretendido(double valorPretendido) {
        this.valorPretendido = valorPretendido;
    }

    public double getValorReal() {
        return valorReal;
    }

    public void setValorReal(double valorReal) {
        this.valorReal = valorReal;
    }

    public String getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public void setUsuarioOrigem(String usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public String getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
