/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.gerenciador;

import br.edu.ifpb.intefaces.Recomendado;
import br.edu.ifpb.model.Banda;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;

/**
 *
 * @author caio
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
@Remote(Recomendado.class)
public class GerenciadorRecomendado implements Recomendado{
    
    private List<Banda> recomendados = new CopyOnWriteArrayList<>();

    @Override
    public void addBanda(Banda banda) {
        this.recomendados.add(banda);
    }

    @Override
    public void removeBanda(Banda banda) {
        this.recomendados.remove(banda);
    }

    @Override
    public void updateBanda(Banda banda) {
        this.recomendados.remove(banda.getId());
        this.recomendados.add(banda);
    }
    
    @Lock(LockType.READ)
    @Override
    public List<Banda> listarRecomendado() {
        return Collections.unmodifiableList(this.recomendados);
    }
    
}
