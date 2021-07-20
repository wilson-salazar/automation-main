/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.ejb;

import com.tamarind.entites.ClienteDestinoTarifa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SALAZAR
 */
@Stateless
public class ClienteDestinoTarifaFacade extends AbstractFacade<ClienteDestinoTarifa> {

    //@PersistenceContext(unitName = "tamarindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteDestinoTarifaFacade() {
        super(ClienteDestinoTarifa.class);
    }
    
}
