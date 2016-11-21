/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Modelo.ciudad;

/**
 *
 * @author franc
 */
public class CiudadNodo {
    
    public CiudadNodo sig;
      
    public ciudad unaCiudad;
      
    public CiudadNodo(ciudad unaCiudad)
    {
        this.unaCiudad = unaCiudad;
    }
    public CiudadNodo(){
        
    }
}
