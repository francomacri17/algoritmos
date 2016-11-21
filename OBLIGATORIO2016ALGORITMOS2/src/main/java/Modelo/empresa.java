/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author franc
 */
public class empresa {
    private String nombre;
    private String direccion;
    private String pais;
    private String email_contacto;
    private String color;

    public empresa() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail_contacto() {
        return email_contacto;
    }

    public void setEmail_contacto(String email_contacto) {
        this.email_contacto = email_contacto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public empresa(String nombre, String direccion, String pais, String email_contacto, String color) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pais = pais;
        this.email_contacto = email_contacto;
        this.color = color;
    } 
    
}
