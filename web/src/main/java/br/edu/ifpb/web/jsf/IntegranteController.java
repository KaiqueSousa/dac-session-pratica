package br.edu.ifpb.web.jsf;

import br.edu.ifpb.intefaces.IntegrantesInterface;
import br.edu.ifpb.model.Integrante;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author murillo
 */
@Named
@SessionScoped
public class IntegranteController implements Serializable {

    @EJB
    IntegrantesInterface integrantesI;
    Integrante integrante = new Integrante();

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public List<Integrante> listar() {
        return this.integrantesI.listarIntegrantes();
    }

    public String salvar() {
        this.integrante.setDataDeNascimento(LocalDate.now());
        this.integrantesI.salvar(integrante);
        integrante = new Integrante();
        return null;
    }

    public String atualizar() {
        this.integrantesI.atualizar(integrante);
        this.integrante = new Integrante();
        return null;
    }

    public String deletar() {
        this.integrantesI.excluir(integrante.getId());
        return null;
    }

}
