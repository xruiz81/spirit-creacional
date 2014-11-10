/*
 * Hora.java
 *
 * Created on June 21, 2007, 11:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker.tiempo;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author lmunoz
 */
public class Hora implements Serializable  {
    
	private static final long serialVersionUID = 1L;
	int hora=0;
    int minutos=0;
    int segundos=0;
    
    public Hora(int[] horaTiempo) {
        this.hora = horaTiempo[0];
        this.minutos = horaTiempo[1];
        this.segundos = horaTiempo[2];
    }
    
    public Hora(int hora,int minutos, int segundos) {
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public String getHora(){
        DecimalFormat df =new DecimalFormat("00");
        return df.format(hora)+":"+df.format(minutos)+":"+df.format(segundos);
    }
    
    public String toString(){
        DecimalFormat df =new DecimalFormat("00");
        return df.format(hora)+":"+df.format(minutos)+":"+df.format(segundos);
    }
    
}
