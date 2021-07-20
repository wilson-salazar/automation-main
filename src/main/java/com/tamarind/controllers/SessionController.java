/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.utils.Utilities;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Developer
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

    private String usuario;
    
    
    @Inject
    private SecurityController securityController;
    
    @PostConstruct
    public void init(){
        securityController.optenerListaPaises();
        usuario = "Wilson Enrique";
    }
    
    public SessionController() {
        
    }
    
    public String irAlManuPrincipal(){
        return Utilities.redirect("/secure/home.xhtml");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    

}
