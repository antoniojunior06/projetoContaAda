package br.gov.caixa.contas;

import br.gov.caixa.Banco;

public interface ContaService {

    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, Conta contaDestino, Banco banco);
    double consultaSaldo();
}
