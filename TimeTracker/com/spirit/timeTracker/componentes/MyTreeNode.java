/*
 * MyTreeNode.java
 *
 * Created on June 26, 2007, 12:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.spirit.timeTracker.componentes;

/**
 *
 * @author lmunoz
 */
public class MyTreeNode{
    String nombre;
    char tipo;
    
    public MyTreeNode(String nombre,char tipo){
        this.nombre=nombre;
        this.tipo = tipo;
    }
    
    public char getTipo(){
        return this.tipo;
    }
    
    public String toString(){
        return this.nombre;
    }
    
}