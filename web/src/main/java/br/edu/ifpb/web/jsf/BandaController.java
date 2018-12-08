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
public class BandaController implements Serializable{
    
    @EJB
    private BandaInterface bandaI;
    private Banda banda;

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
    
    public List<Banda> listar(){
        return this.bandaI.listarBandas();      
    }
    
    public String salvar(){
        this.bandaI.salvar(this.banda);
        return null;
    }
    
    public String atualizar(){
        this.bandaI.atualizar(this.banda);
        return null;
    }
    
    public String deletar(){
        
        this.bandaI.excluir(banda.getId());
        return null;
    }
    
}
