/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Modelo.Punto;

/**
 *
 * @author franc
 */
public class Node implements Comparable<Node> {

    Punto detino;
    int peso;

    Node(Punto d, int p) {                          //constructor
        this.detino = d;
        this.peso = p;
    }

   public int compareTo(Node other) {              //es necesario definir un comparador para el correcto funcionamiento del PriorityQueue
        if (peso > other.peso) {
            return 1;
        }
        if (peso == other.peso) {
            return 0;
        }
        return -1;
    }
}
