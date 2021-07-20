/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author SALAZAR
 */

@Named
@RequestScoped
public class testContoller {

    private UploadedFile file;


    public void upload() {
        if (file != null) {
            System.out.println("Nombre del file: >>>>>>>>>>>>>>>>>" + file.getFileName());
        }else{
            System.out.println("No llego :( ");
        }
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
  
}
