/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.entites.Action;
import com.tamarind.utils.Utilities;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author Developer
 */
@Named(value = "homeController")
@ViewAccessScoped
public class HomeController implements Serializable {

    private List<Action> acciones;
    private String user; 

    public HomeController() {
        
    }

    @PostConstruct
    public void init() {
        acciones = new ArrayList<>();
        
        acciones.add(new Action("/secure/ftl/listar.xhtml", "FTL", "img/destination.png"));
        acciones.add(new Action("/secure/client/listar.xhtml", "Drayage", "img/client.png"));
        acciones.add(new Action("/secure/quotation/listar.xhtml", "Payment", "img/quotation.png"));
    }
    
    public String redirect(String url){
        return Utilities.redirect(url);
    }

    public String goToLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/secure/home.xhtml");
        return "/secure/home.xhtml";
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Pre destroy login");
    }

    public List<Action> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Action> acciones) {
        this.acciones = acciones;
    }

}
