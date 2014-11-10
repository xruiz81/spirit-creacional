/*
 * Utiles.java
 *
 * Created on June 27, 2007, 9:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker;

/**
 *
 * @author lmunoz
 */
public class Utiles {
    
    /** Creates a new instance of Utiles */
    public Utiles() {
    }
    
    static String stringToString(String palabra){
        StringBuilder slinea = new StringBuilder();
        byte[] bytes = palabra.getBytes();
        for ( int i=0;i<bytes.length;i++ ){
            if ( isStringTildado(bytes[i]) ){
                switch (bytes[i]) {
                    case -96: slinea.append("á"); break;
                    case -126: slinea.append("é"); break;
                    case -95: slinea.append("í"); break;
                    case -94: slinea.append("ó"); break;
                    case -93: slinea.append("ú"); break;
                    case -92: slinea.append("ñ"); break;
                    case -91: slinea.append("Ñ"); break;
                }
            } else
                slinea.append((char)bytes[i]);
        }
        return slinea.toString();
    }
    
    static boolean isStringTildado(byte letra){
        if ( letra==-96 || letra==-126 || letra==-95 || letra==-94
                || letra==-93 || letra==-92 || letra==-91 )
            return true;
        return false;
    }  
    
    static String lineaToString(byte[] linea){
        StringBuilder slinea = new StringBuilder();
        for ( int i=0;i<linea.length;i++ ){
            if ( linea[i]!=0 ){
                if ( isByteTildado(linea[i]) ){
                    switch (linea[i]) {
                        case -31: slinea.append("á"); break;
                        case -23: slinea.append("é"); break;
                        case -19: slinea.append("í"); break;
                        case -13: slinea.append("ó"); break;
                        case -6: slinea.append("ú"); break;
                        case -15: slinea.append("ñ"); break;
                        case -47: slinea.append("Ñ"); break;
                    }
                } else
                    slinea.append((char)linea[i]);
            }
        }
        return slinea.toString();
    }
    
    static boolean isByteTildado(byte letra){
        if ( letra==-31 || letra==-23 || letra==-19 || letra==-13
                || letra==-6 || letra==-15 || letra==-47 )
            return true;
        return false;
    }
    
}
