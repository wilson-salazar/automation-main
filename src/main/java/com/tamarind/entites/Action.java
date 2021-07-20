/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.entites;

/**
 *
 * @author Developer
 */
public class Action {
    
    private String url;
    private String name;
    private String icon;
    
    public Action(){
        
    }

    public Action(String url, String name, String icon) {
        this.url = url;
        this.name = name;
        this.icon = icon;
    }
    
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}
