/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.ejb;

import com.tamarind.entites.CotizacionDetalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SALAZAR
 */
@Stateless
public class CotizacionDetalleFacade extends AbstractFacade<CotizacionDetalle> {

    //@PersistenceContext(unitName = "tamarindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionDetalleFacade() {
        super(CotizacionDetalle.class);
    }
    
}
