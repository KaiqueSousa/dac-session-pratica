package br.edu.ifpb.gerenciador;



public class GerenciadorDeBanda implements Playlist {

    private List<Banda> banda = new ArrayList<>();

    public boolean salvarBanda (Banda banda) {
        return bandaDao.salvar(banda);
    }


    public boolean  excluirBanda(int id) {
        return bandaDao.excluir(id);
    }

    public boolean atualizarBanda (Banda banda) {
        return bandaDao.atualizar(banda);
    }

    public List<Banda> listarBandas () {
        return bandaDao.listarBandas();
    }

}