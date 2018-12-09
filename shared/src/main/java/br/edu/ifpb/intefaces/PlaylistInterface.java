/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.intefaces;

import br.edu.ifpb.model.Banda;
import java.util.List;

/**
 *
 * @author kaique
 */
public interface PlaylistInterface {
    
    void salvarBanda (Banda banda);
    void excluirBanda(Banda banda);
    void atualizarBanda (Banda banda);
    List<Banda> listarBandas ();
    
}
