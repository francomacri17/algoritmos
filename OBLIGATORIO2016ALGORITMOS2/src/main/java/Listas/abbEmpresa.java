/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Modelo.empresa;
import java.util.ArrayList;


/**
 *
 * @author franc
 */
public class abbEmpresa{ 
    private static ArrayList<empresa> aux = new ArrayList<empresa>();

    public ArrayList<empresa> getAux() {
        return aux;
    }
    
    private class nodoArbol {
        private abbEmpresa hd;
        private abbEmpresa hi;
        private empresa dato;
        
        
        private void nodoArbol(){
            hd = null;
            hi = null;
            dato = null;
        }
    }
 
    public nodoArbol raiz;
 
    public void abb(){
        nodoArbol raiz = new nodoArbol();
    }
 
    public boolean esVacio(){
        return (raiz == null);
    }
    
    public void insertar(empresa e){
        if (esVacio()) {
            nodoArbol nuevo = new nodoArbol();
            nuevo.dato = e;
            nuevo.hd = new abbEmpresa();
            nuevo.hi = new abbEmpresa();
            raiz = nuevo;
        }
        else {
            if (e.getNombre().compareTo(raiz.dato.getNombre()) > 0) {
                (raiz.hd).insertar(e);
            }
            if (e.getNombre().compareTo(raiz.dato.getNombre()) < 0){
                (raiz.hi).insertar(e);
            }
        }
    }
 
    public void preOrder(){
        if (!esVacio()) {
            System.out.print( raiz.dato.getNombre() + ", "  );
            raiz.hi.preOrder();
            raiz.hd.preOrder();
        }
    }
 
    public StringBuilder inOrder(StringBuilder string) {
        if(!esVacio()){
            raiz.hi.inOrder(string);
            
            string.append(raiz.dato.getNombre() + " ; " + raiz.dato.getEmail_contacto() +" | ");  
            
            raiz.hd.inOrder(string);
        }
        
        return string;
}
 
    public void posOrder(){
        if (!esVacio()) {
            raiz.hd.posOrder();
            raiz.hi.posOrder();
            System.out.print( raiz.dato.getNombre() + ", "  );
 
        }
    }
    public ArrayList<empresa> listarEmpresa(){
        if (!esVacio()) {
           aux.add(raiz.hi.raiz.dato);
           raiz.hi.posOrder();
           raiz.hd.posOrder();
           aux.add(raiz.hd.raiz.dato);
            
        }
        return aux;
    } 
    public abbEmpresa buscar(String nombre){
        abbEmpresa buscada = null;
        if (!esVacio()) {
            if (nombre.equals(raiz.dato.getNombre())) {
            return this;
            }
            else {
                if (nombre.compareTo(raiz.dato.getNombre()) < 0) {
                    buscada = raiz.hi.buscar(nombre);
                }
                else {
                    buscada = raiz.hd.buscar(nombre);
                }
            }
        }
        return buscada;
    }
    public empresa buscarEmpresa(String nombre){
        empresa buscada = null;
        if (!esVacio()) {
            if (nombre.equals(raiz.dato.getNombre())) {
            return this.raiz.dato;
            }
            else {
                if (nombre.compareTo(raiz.dato.getNombre()) < 0) {
                    buscada = raiz.hi.buscarEmpresa(nombre);
                }
                else {
                    buscada = raiz.hd.buscarEmpresa(nombre);
                }
            }
        }
        return buscada;
    }
    public boolean existe(empresa e){
    if (!esVacio()) {
            if (e.getNombre().equals(raiz.dato.getNombre())) {
            return true;
            }
            else {
                if (e.getNombre().compareTo(raiz.dato.getNombre()) < 0) {
                    raiz.hi.existe(e);
                }
                else {
                    raiz.hd.existe(e);
                }
            }
        }
        return false;
    }
 
    public int cantidad(){
        if (esVacio()) {
            return 0;
        }
        else {
            return (1 + raiz.hd.cantidad() + raiz.hi.cantidad());
        }
    }
 
    public int altura() {
        if (esVacio()) {
            return 0;
        }
        else {
            return (1 + Math.max(((raiz.hi).altura()), ((raiz.hd).altura())));
        }
    }
 
    public empresa buscarMin() {
        abbEmpresa arbolActual = this;
        while( !arbolActual.raiz.hi.esVacio() ) {
            arbolActual = arbolActual.raiz.hi;
        }
        empresa devuelvo= arbolActual.raiz.dato;
        arbolActual.raiz=null;
        return devuelvo;
    }
 
    public empresa buscarMan() {
        abbEmpresa arbolActual = this;
        while( !arbolActual.raiz.hd.esVacio() ) {
            arbolActual = arbolActual.raiz.hd;
        }
        empresa devuelvo= arbolActual.raiz.dato;
            arbolActual.raiz=null;
        return devuelvo;
    }
 
    public boolean esHoja() {
        boolean hoja = false;
        if( (raiz.hi).esVacio() && (raiz.hd).esVacio() ) {
            hoja = true;
        }
        return hoja;
    }
 
    public void eliminar(String e) {
        abbEmpresa paraEliminar = buscar(e);
        if (!paraEliminar.esVacio()) {
            if (paraEliminar.esHoja()) {
                paraEliminar.raiz = null;
            }
            else {
                if (!paraEliminar.raiz.hi.esVacio() && !paraEliminar.raiz.hd.esVacio()) {
                    paraEliminar.raiz.dato = paraEliminar.raiz.hd.buscarMin();
                }
                else {
                    if (paraEliminar.raiz.hi.esVacio()) {
                        paraEliminar.raiz = paraEliminar.raiz.hd.raiz;
                    }else{
                        paraEliminar.raiz = paraEliminar.raiz.hi.raiz;
                    }
                }
            }
        }
    }
}