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
public class DCNodo {
    public DCNodo sig;
      
    public data_center unDC;
      
    public DCNodo(data_center punDC)
    {
        this.unDC = punDC;
    }
    public DCNodo(){
        
    }
}
