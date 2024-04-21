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
        for (ContaPoupanca cp: contasPoupanca) {
            if(cp.getUsuarioId().equals(usuarioId)) {
                return;
            }
        }

        ContaPoupanca cp = new ContaPoupanca(usuarioId);
        contasPoupanca.add(cp);
        cp.setId(contaPoupancaId++);

    }

    public void criarContaInvestimento(String usuarioId) {
        for (ContaInvestimento ci: contasInvestimento) {
            if(ci.getUsuarioId().equals(usuarioId)) {
                return;
            }
        }

        ContaInvestimento ci = new ContaInvestimento(usuarioId);
        contasInvestimento.add(ci);
        ci.setId(contaInvestimentoId++);
    }

    public boolean temUsuario(String usuarioId) {
        for (Usuario usuario : usuarios) {
            if(usuario.getId().contains(usuarioId)) {
                return true;
            }
        }
        return false;
    }

    public <T extends Conta> T buscarContaPorId(long id, List<T> contas) {
        for (T conta: contas) {
            if(conta.getId() == id) {
                return conta;
            }
        }
        return null;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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

    public void setContasCorrente(List<ContaCorrente> contasCorrente) {
        this.contasCorrente = contasCorrente;
    }

    public List<ContaPoupanca> getContasPoupanca() {
        return contasPoupanca;
    }

    public void setContasPoupanca(List<ContaPoupanca> contasPoupanca) {
        this.contasPoupanca = contasPoupanca;
    }

    public List<ContaInvestimento> getContasInvestimento() {
        return contasInvestimento;
    }

    public void setContasInvestimento(List<ContaInvestimento> contasInvestimento) {
        this.contasInvestimento = contasInvestimento;
    }

    public long getContaCorrenteId() {
        return contaCorrenteId;
    }

    public void setContaCorrenteId(long contaCorrenteId) {
        this.contaCorrenteId = contaCorrenteId;
    }

    public long getContaInvestimentoId() {
        return contaInvestimentoId;
    }

    public void setContaInvestimentoId(long contaInvestimentoId) {
        this.contaInvestimentoId = contaInvestimentoId;
    }

    public long getContaPoupancaId() {
        return contaPoupancaId;
    }

    public void setContaPoupancaId(long contaPoupancaId) {
        this.contaPoupancaId = contaPoupancaId;
    }


}
