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
public class Punto {
     private Double coordX;
    
     private Double coordY;
    
     private String color;

    public Punto() {
        
    }
     
     public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Punto(Double coordX, Double coordY, String color) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.color = color;
    }
    
}
