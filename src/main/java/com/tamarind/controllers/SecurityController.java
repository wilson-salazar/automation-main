/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.ejb.PaisFacade;
import com.tamarind.entites.Pais;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Developer
 */
@Named(value = "securityController")
@ApplicationScoped
public class SecurityController implements Serializable {

    private List<Pais> listaPaises;

    
    private String valor = "SecurityController";

    @PostConstruct
    public void inicializar() {
        System.err.println("Entrando al SecurityController >>>>>>>>>>>>>>>>>>>>>>>>> ");
    }

    public SecurityController() {
    }

    public void optenerListaPaises() {
        //listaPaises = paisFacade.findAll();
        listaPaises = null;
    }

    public List<Pais> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
