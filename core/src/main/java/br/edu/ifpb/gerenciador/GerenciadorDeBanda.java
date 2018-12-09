package br.edu.ifpb.gerenciador;

import br.edu.ifpb.intefaces.PlaylistInterface;
import br.edu.ifpb.model.Banda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(PlaylistInterface.class)
public class GerenciadorBanda implements PlaylistInterface {

    private List<Banda> bandas = new ArrayList<>();
    
    @Override
    public void salvarBanda (Banda banda) {
        this.bandas.add(banda);
    }

    @Override
    public void excluirBanda(Banda banda) {
        this.bandas.remove(banda);
    }

    @Override
    public void atualizarBanda (Banda banda) {
        this.bandas.remove(banda.getId());
        this.bandas.add(banda);
    }

    @Override
    public List<Banda> listarBandas() {
        return Collections.unmodifiableList(this.bandas);
=======
import java.util.List;



public class GerenciadorDeBanda implements PlaylistInterface {

    private List<Banda> banda = new ArrayList<>();
    @Override
    public boolean salvarBanda (Banda banda) {
        return bandaDao.salvar(banda);
    }

    @Override
    public boolean excluirBanda(int id) {
        return bandaDao.excluir(id);
    }
    @Override
    public boolean atualizarBanda (Banda banda) {
        return bandaDao.atualizar(banda);
    }
    @Override
    public List<Banda> listarBandas () {
        return bandaDao.listarBandas();
>>>>>>> ff5545947d622c21d59c3d007bac001f5ebfda1e:core/src/main/java/br/edu/ifpb/gerenciador/GerenciadorDeBanda.java
    }

}