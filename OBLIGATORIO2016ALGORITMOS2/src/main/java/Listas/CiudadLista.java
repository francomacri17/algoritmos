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
public class CiudadLista {

    CiudadNodo inicio;
    CiudadNodo sig;

    public void agregarNodoAlFinal(ciudad c) {
        CiudadNodo aux = inicio;

        if (inicio != null) {

            while (aux.sig != null) {
                aux = aux.sig;
            }
            CiudadNodo nuevo = new CiudadNodo();
            nuevo.unaCiudad = c;
            aux.sig = nuevo;
            nuevo.sig = null;
        } else {
            CiudadNodo nuevo = new CiudadNodo(c);
            inicio = nuevo;
        }

    }

    public void eliminarCiudad(String nombre) {
        if (inicio != null) {

            CiudadNodo anterior = null;

            CiudadNodo aux = inicio;
            while (aux != null && !aux.unaCiudad.getNombre().equals(nombre)) {
                anterior = aux;
                aux = aux.sig;
            }
            if (aux == null) {
                //no existe
            }
            if (anterior == null) {
                //elimina
                inicio = inicio.sig;

            } else {
                //elimina
                anterior.sig = aux.sig;
            }
        } else {
            //no hay elementos en la lista
        }
    }

    public ciudad darCiudad(Double x, Double y) {
        ciudad c = null;
        CiudadNodo aux = inicio;
        int ok = 0;
        if (inicio != null) {

            while (aux.sig != null && ok == 0) {
                if (aux.unaCiudad.getCoordX().equals(x) && aux.unaCiudad.getCoordY().equals(y)) {
                    c = aux.unaCiudad;
                    ok = 1;
                }
                aux = aux.sig;
            }
            if (aux.unaCiudad.getCoordX().equals(x) && aux.unaCiudad.getCoordY().equals(y)) {
                c = aux.unaCiudad;
            }
        }
        return c;
    }
    public ciudad darCiudad(String nombre) {
        ciudad c = null;
        CiudadNodo aux = inicio;
        int ok = 0;
        if (inicio != null) {

            while (aux.sig != null && ok == 0) {
                if (aux.unaCiudad.getNombre().equals(nombre)) {
                    c = aux.unaCiudad;
                    ok = 1;
                }
                aux = aux.sig;
            }
            if (aux.unaCiudad.getNombre().equals(nombre)) {
                c = aux.unaCiudad;
            }
        }
        return c;
    }
}
