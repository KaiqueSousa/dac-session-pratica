package br.edu.ifpb.gerenciador;

import br.edu.ifpb.intefaces.PlaylistInterface;
import br.edu.ifpb.model.Banda;
import java.util.ArrayList;
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
    }

}