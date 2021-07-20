/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.ejb;

import com.tamarind.entites.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> {

    //@PersistenceContext(unitName = "tamarindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public List<Object[]> filtrar(int first, int pageSize, String filtro) {
        List<Object[]> clientes;
        Query q = em.createNativeQuery("select des.id, des.nombre, des.codigo, des.activo, "
                + "des.telefono, des.correo, des.direccion\n"
                + "FROM cliente des WHERE upper(des.nombre) LIKE ?1\n"
                + "order by des.nombre\n"
                + "offset ?2 limit ?3 ");
        q.setParameter(1, "%" + filtro + "%");
        q.setParameter(2, first);
        q.setParameter(3, pageSize);

        clientes = q.getResultList();
        return clientes;
    }

    public long filtrarcont(String filtro) {
        Query q = em.createNativeQuery("select count(des.id) FROM cliente des WHERE upper(des.nombre) LIKE ?1 ");
        q.setParameter(1, "%" + filtro + "%");
        return (long) q.getSingleResult();
    }

    public List<Cliente> consultaClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Query q = em.createNativeQuery("SELECT * FROM cliente");

        clientes = q.getResultList();
        return clientes;
    }

}
