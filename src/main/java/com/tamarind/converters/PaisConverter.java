/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.converters;

import com.tamarind.ejb.PaisFacade;
import com.tamarind.entites.Pais;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author SALAZAR
 */
@Named
@RequestScoped
@FacesConverter("paisConverter")
public class PaisConverter implements Converter{

    @EJB
    private PaisFacade paisFacade;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }
            return paisFacade.find(Integer.parseInt(value));
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Pais) {
            Pais o = (Pais) value;
            return o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + value +
                    " is of type " + value.getClass().getName() +
                    "; expected type: " + Pais.class.getName());
        }
    }
    
    
    
}
