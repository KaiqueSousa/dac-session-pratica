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
 * @author caio
 */
public interface Recomendado {
    
    void addBanda (Banda banda);
    void removeBanda (Banda banda);
    void updateBanda (Banda banda);
    List<Banda> listarRecomendado ();
    
}
