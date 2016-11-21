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
public class tramo {
    private Double coordXi;
    private Double coordYi;
    private Double coordXf;
    private Double coordYf;
    private int peso;

    public Double getCoordXi() {
        return coordXi;
    }

    public void setCoordXi(Double coordXi) {
        this.coordXi = coordXi;
    }

    public Double getCoordYi() {
        return coordYi;
    }

    public void setCoordYi(Double coordYi) {
        this.coordYi = coordYi;
    }

    public Double getCoordXf() {
        return coordXf;
    }

    public void setCoordXf(Double coordXf) {
        this.coordXf = coordXf;
    }

    public Double getCoordYf() {
        return coordYf;
    }

    public void setCoordYf(Double coordYf) {
        this.coordYf = coordYf;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public tramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        this.coordXi = coordXi;
        this.coordYi = coordYi;
        this.coordXf = coordXf;
        this.coordYf = coordYf;
        this.peso = peso;
    }
    
}
