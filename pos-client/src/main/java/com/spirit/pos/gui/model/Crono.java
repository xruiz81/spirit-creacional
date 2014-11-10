package com.spirit.pos.gui.model;

public class Crono { 
	   private java.util.Date t0, t1;    

	   public void inicializa() { 
	      t0 = new java.util.Date(); 
	      System.out.println("EMPEZANDO.........>"+t0);
	   } 

	   public long tiempo() { 
	      t1 = new java.util.Date(); 
	      return (t1.getTime() - t0.getTime());    
	   } 
	} 
