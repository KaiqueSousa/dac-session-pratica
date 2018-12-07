package br.edu.ifpb.gerenciador;

import br.edu.ifpb.intefaces.PlaylistInterface;



public class GerenciadorDeBanda implements PlaylistInterface {

    private List<Banda> banda = new ArrayList<>();

    public boolean salvarBanda (Banda banda) {
        return bandaDao.salvar(banda);
    }


    public boolean excluirBanda(int id) {
        return bandaDao.excluir(id);
    }

    public boolean atualizarBanda (Banda banda) {
        return bandaDao.atualizar(banda);
    }

    public List<Banda> listarBandas () {
        return bandaDao.listarBandas();
    }

}