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
public class data_center extends Punto {
    private String nombre;
    private empresa empresa;
    private int capacidadCPUenHoras;
    private int costoCPUporHora;
    private int cpuUsado = 0;

    public data_center() {
        
    }

    public void setCpuUsado(int pCpuUsadoo) {
            cpuUsado = cpuUsado+pCpuUsadoo;
    }

    public int getCpuUsado() {
        return cpuUsado;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(empresa empresa) {
        this.empresa = empresa;
    }

    public int getCapacidadCPUenHoras() {
        return capacidadCPUenHoras;
    }

    public void setCapacidadCPUenHoras(int capacidadCPUenHoras) {
        this.capacidadCPUenHoras = capacidadCPUenHoras;
    }

    public int getCostoCPUporHora() {
        return costoCPUporHora;
    }

    public void setCostoCPUporHora(int costoCPUporHora) {
        this.costoCPUporHora = costoCPUporHora;
    }

    public data_center(String nombre, Double coordX, Double coordY, empresa empresa, int capacidadCPUenHoras, int costoCPUporHora, String color) {
        super(coordX, coordY, color);
        this.nombre = nombre;
        this.empresa = empresa;
        this.capacidadCPUenHoras = capacidadCPUenHoras;
        this.costoCPUporHora = costoCPUporHora;
    }
    
}
