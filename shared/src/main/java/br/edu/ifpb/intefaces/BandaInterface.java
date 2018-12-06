package br.edu.ifpb.intefaces;

import br.edu.ifpb.model.Banda;
import java.util.List;

/**
 *
 * @author fernanda
 */
public interface BandaInterface {
    
    boolean salvar (Banda banda);
    boolean excluir (int id);
    boolean atualizar (Banda banda);
    List<Banda> listarBandas ();
}
