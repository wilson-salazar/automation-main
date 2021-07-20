/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.ejb;

import com.tamarind.entites.Destino;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SALAZAR
 */
@Stateless
public class DestinoFacade extends AbstractFacade<Destino> {

    //@PersistenceContext(unitName = "tamarindPU")
    private EntityManager em;

    Query q;

    public List<Object[]> filtrar(int first, int pageSize, String filtro) {
        List<Object[]> destinos;
        q = em.createNativeQuery("select des.id, des.descripcion, des.codigo, des.activo\n"
                + "FROM destino des WHERE upper(des.descripcion) LIKE ?1\n"
                + "order by des.descripcion\n"
                + "offset ?2 limit ?3 ");
        q.setParameter(1, "%" + filtro + "%");
        q.setParameter(2, first);
        q.setParameter(3, pageSize);

        destinos = q.getResultList();
        return destinos;
    }

    public long filtrarcont(String filtro) {
        q = em.createNativeQuery("select count(des.id) FROM destino des WHERE upper(des.descripcion) LIKE ?1 ");
        q.setParameter(1, "%" + filtro + "%");
        return (long) q.getSingleResult();
    }

    public List<Destino> consultaDestinos() {
        List<Destino> destinos = new ArrayList<>();
        q = em.createNativeQuery("SELECT * FROM destino");

        destinos = q.getResultList();
        return destinos;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinoFacade() {
        super(Destino.class);
    }

}
