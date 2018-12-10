package br.edu.ifpb.web.jsf;

import br.edu.ifpb.intefaces.BandaInterface;
import br.edu.ifpb.model.Banda;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class BandaController implements Serializable {

    @EJB
    private BandaInterface bandaI;
    private Banda banda = new Banda();

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public List<Banda> listar() {
        return this.bandaI.listarBandas();
    }

    public String salvar() {
        this.bandaI.salvar(
                this.banda
        );
        return null;
    }

    public String atualizar() {
        this.bandaI.atualizar(this.banda);
        this.banda = new Banda();
        return null;
    }

    public void atualizar(Banda banda) {
        this.banda = banda;
    }

    public String deletar(Banda banda) {
        boolean excluir = this.bandaI.excluir(banda.getId());
        return null;
    }

}
