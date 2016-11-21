/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author franc
 */
public class ciudad extends Punto {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ciudad(String nombre, Double coordX, Double coordY) {

        super(coordX, coordY, "#FFFF00");
        this.nombre = nombre;
    }

}
