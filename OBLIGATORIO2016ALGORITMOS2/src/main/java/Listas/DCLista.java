/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Modelo.data_center;

/**
 *
 * @author franc
 */
public class DCLista {
    
    DCNodo inicio;
    DCNodo sig;
    
    
    public void agregarNodoAlFinal(data_center dc){
        DCNodo aux = inicio;
        
        if(inicio!=null){
            
        
        while(aux.sig != null){
            aux = aux.sig;
        }
        DCNodo nuevo = new DCNodo();
        nuevo.unDC = dc;
        aux.sig = nuevo;
        nuevo.sig = null;
        }else{
            DCNodo nuevo = new DCNodo(dc);
            inicio = nuevo;
        }
        
        
    }
    
 public void eliminarDC(String nombre)
    {
       if(inicio != null){
            
            DCNodo anterior = null;

            DCNodo aux = inicio;
            while(aux != null && !aux.unDC.getNombre().equals(nombre)){
                anterior = aux;
                aux = aux.sig;
            }
            if(aux==null){
                //no existe
            }
            if(anterior==null){
                //elimina
                inicio=inicio.sig;
                
            }else{
                //elimina
                anterior.sig = aux.sig;
            }
        }
        else{
            //no hay elementos en la lista
        }
    
    
    }
}
