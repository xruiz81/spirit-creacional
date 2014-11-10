/*
 * Hora.java
 *
 * Created on June 21, 2007, 11:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker.tiempo;

import static timeTracker.tiempo.Utiles.df;
import static timeTracker.tiempo.Utiles.mes;

import java.io.Serializable;

public class Fecha implements Serializable {
    
	private static final long serialVersionUID = 1L;
	int anio=0;
    int mes=0;
    int dia=0;
    
    public Fecha(int[] fecha) {
        this.dia = fecha[0];
        this.mes = fecha[1];
        this.anio = fecha[2]+1900;
    }
    
    public Fecha(int dia,int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio+1900;
    }
    
    public String toString(){
        return df.format(dia)+" / "+mes(mes)+" / "+anio;
    }   
}