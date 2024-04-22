package br.gov.caixa;

import br.gov.caixa.contas.Conta;
import br.gov.caixa.contas.ContaCorrente;
import br.gov.caixa.contas.ContaInvestimento;
import br.gov.caixa.contas.ContaPoupanca;
import br.gov.caixa.status.Status;
import br.gov.caixa.usuario.Usuario;
import br.gov.caixa.validador.Validador;

import java.util.ArrayList;
import java.util.List;

public class Banco implements Validador {

    private List<Usuario> usuarios;
    private List<Conta> contas;
    private List<ContaCorrente> contasCorrente = new ArrayList<>();
    private List<ContaPoupanca> contasPoupanca = new ArrayList<>();
    private List<ContaInvestimento> contasInvestimento = new ArrayList<>();
    private long contaCorrenteId = 1;
    private long contaInvestimentoId = 1;
    private long contaPoupancaId = 1;

    public Banco() {
        this.usuarios = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public void criarUsuario(String usuarioId, String nome) {
        if (!validadorId(usuarioId)) {
            throw new IllegalArgumentException("O ID deve ter 11 ou 14 dígitos");
        }

        for (Usuario u : usuarios) {
            if (u.getId().equals(usuarioId)) {
                return;
            }
        }

        Usuario usuario = new Usuario(usuarioId, nome);
        usuarios.add(usuario);

        ContaCorrente cc = new ContaCorrente(usuario);
        contasCorrente.add(cc);
        cc.setId(contaCorrenteId++);

    }

    public void criarContaPoupanca(String usuarioId) {
        if (!temUsuario(usuarioId)) {
            for (ContaPoupanca cp: contasPoupanca) {
                if(cp.getUsuarioId().equals(usuarioId)) {
                    return;
                }
            }

            ContaPoupanca cp = new ContaPoupanca(usuarioId);
            contasPoupanca.add(cp);
            cp.setId(contaPoupancaId++);
        }


    }

    public void criarContaInvestimento(String usuarioId) {
        ContaInvestimento ci = new ContaInvestimento(usuarioId);
        contasInvestimento.add(ci);
        ci.setId(contaInvestimentoId++);
    }

    public <T extends Conta> T buscarContaPorId(long id, List<T> contas) {
        return contas.stream().filter(conta -> conta.getId() == id).findFirst().orElse(null);

    }

    public <T extends Conta> void alterarStatusConta(T conta) {
        if (conta.getStatus() == Status.ATIVO) {
            conta.setStatus(Status.INATIVO);
        }else {
            conta.setStatus(Status.ATIVO);
        }
    }

    @Override
    public boolean validadorId(String id) {
        return id.matches("\\d{11}|\\d{14}");
    }

    @Override
    public boolean temUsuario(String id) {
        return usuarios.stream().anyMatch(usuario -> usuario.getId().contains(id));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<ContaCorrente> getContasCorrente() {
        return contasCorrente;
    }

    public List<ContaPoupanca> getContasPoupanca() {
        return contasPoupanca;
    }

    public List<ContaInvestimento> getContasInvestimento() {
        return contasInvestimento;
    }

}
