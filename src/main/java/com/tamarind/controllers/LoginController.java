/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import java.io.IOException;
import java.io.Serializable;
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
@Named(value = "loginController")
@ViewAccessScoped
public class LoginController implements Serializable {

    private List<String> acciones;

    public LoginController() {
    }

    @PostConstruct
    public void init() {
        acciones = Arrays.asList("Destionations", "Clients", "Rates", "Files", "Config");
        System.out.println("POST CONSTRUCT");
    }

    public String goToHome() {
        return "/index.xhtml";
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Pre destroy login");
    }

    public List<String> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<String> acciones) {
        this.acciones = acciones;
    }

}
