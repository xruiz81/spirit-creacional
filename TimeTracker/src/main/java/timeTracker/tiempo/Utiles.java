
package timeTracker.tiempo;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utiles {
    
    static DecimalFormat df = new DecimalFormat("00");
    
    public Utiles() {
    }
    
    public static long[] convertirSegundos(long totalSegundos){
        //[HORAS,MINUTOS,SEGUNDOS]
    	long[] tiempo = new long[3];
    	long residuo=0;
        if ( totalSegundos > 0 ){
            tiempo[0]= totalSegundos/3600;
            residuo = totalSegundos%3600;
            tiempo[1]= residuo/60;
            tiempo[2] = residuo%60;
        }
        return tiempo;
    }
    
    public static long[] sumarSegundos(long[] base,long[] sumando){
    	long segundos=0,minutos=0,horas=0;
        segundos = base[2] + sumando[2];
        minutos = base[1] + sumando[1];
        horas = base[0] + sumando[0];
        long tiempo[] = new long[3];
        if ( segundos >= 60  ){
            tiempo[2] = segundos - 60;
            tiempo[1] += 1;
        }
        else
            tiempo[2] = segundos;
        if ( minutos >= 60  ){
            tiempo[1] += (minutos - 60);
            tiempo[0] += 1;
        }
        else
            tiempo[1] += minutos;
        tiempo[0] += horas;
        return tiempo;
    }
    
    public static String getTiempoCompleto(long segundos){
        
    	long[] tiempo=convertirSegundos(segundos);
        return df.format(tiempo[0])+":"+df.format(tiempo[1])+":"+df.format(tiempo[2]);
    };
    
    public static int[] getHoraActual(){
        //Date hoy = new Date();
        Calendar hoy = new GregorianCalendar();
        int[] hora = {
            hoy.get(Calendar.HOUR_OF_DAY),
            hoy.get(Calendar.MINUTE),
            hoy.get(Calendar.SECOND)
        };
        return hora;
    }
    
    public static int[] getFechaActual(){
        //Date hoy = new Date();
        Calendar fechaHoy = new GregorianCalendar();
        int[] hora = {
            fechaHoy.get(Calendar.DAY_OF_MONTH),
            fechaHoy.get(Calendar.MONTH),
            fechaHoy.get(Calendar.YEAR)
        };
        return hora;
    }
    
    public static String mes(int imes){
        switch(imes){
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Noviembre";
            case 11: return "Diciembre";
            default: return "";
        }
    }
    
    public static Fecha convertirFecha(long fecha){
    	if (fecha>0){
    		Date fechac = new Date(fecha);
    		return new Fecha(fechac.getDate(),fechac.getMonth(),fechac.getYear());
    	}
    	return new Fecha(0,0,0);
    }
    
    public static Hora convertirHora(long hora){
    	if (hora>0){
    		Date fecha = new Date(hora);
    		return new Hora(fecha.getHours(),fecha.getMinutes(),fecha.getSeconds());
    	}
    	return new Hora(0,0,0);
    }
    
    /*public static void main(String[] s){
        System.out.println("tiempo: "+getTiempoCompleto(59));
        int [] hora = getHoraActual();
        System.out.println("hora "+hora[0]+":"+hora[1]+":"+hora[2]);
    }*/
}
