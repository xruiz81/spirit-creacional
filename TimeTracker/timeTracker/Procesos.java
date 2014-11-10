package timeTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static com.spirit.timeTracker.gui.model.PanelGeneralModel.mapaProcesos;
import static timeTracker.Utiles.stringToString;
import java.io.File;
import javax.swing.ImageIcon;

public class Procesos {
    private String idProcesoActivo;
    
    public Procesos() {
    }
    
    public static HashMap crearListaProcesos(){
        HashMap mapaProcesosInt=null;
        try {
            Process process = Runtime.getRuntime().exec("tasklist /v /fo csv /nh");
            MyStreamReader msr = new MyStreamReader( process.getInputStream() );
            msr.start();
            int salida = process.waitFor();
            msr.join();
            mapaProcesosInt = msr.getMapa();
            
            /************************************************/
             /*   Iterator iterador = mapaProcesosInt.keySet().iterator();
                    while ( iterador.hasNext() ){
                        try {
                            Proceso proceso = (Proceso)mapaProcesosInt.get(iterador.next());
                            System.out.println(
                                    "Nombre: "+
                                    proceso.getNombreImagen()+
                                    "ID: "+
                                    proceso.getPid()+
                                    " titulo ventana: "+
                                    proceso.getTituloVentana()
                                    );
                        } catch (Exception e1) {
                            System.err.println("error mostrar: "+e1);
                        }
              
                    }*/
            /************************************************/
            
            System.out.print("Tamano: "+mapaProcesosInt.size()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
        return mapaProcesosInt;
    }
    
    public static Proceso getProcesoDeListaProcesos(String idProceso){
        try {
            Process process = Runtime.getRuntime().exec("tasklist /v /fo csv /nh");
            MyStreamReader2 msr = new MyStreamReader2( process.getInputStream(),idProceso );
            msr.start();
            int salida = process.waitFor();
            msr.join();
            return msr.getProceso();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
        return null;
    }
    
    static class MyStreamReader2 extends Thread {
        private BufferedReader br;
        private String linea;
        private String idProcesoBuscado="";
        private Proceso proceso=null;
        
        MyStreamReader2(InputStream is,String idProcesoBuscado) {
            this.setBr(new BufferedReader(new InputStreamReader(is)));
            this.setIdProcesoBuscado(idProcesoBuscado);
        }
        
        public void run() {
            try {
                String linea;
                //System.out.println("Creacion de Procesos desde Lista de Procesos");
                while ( ((linea = getBr().readLine()) != null) ) {
                    if ( linea.length() > 10 ){
                        String [] palabras = linea.trim().substring(1,linea.length()-1).replaceAll("\",\"",";;_").split(";;_");
                        if ( palabras.length >  1){
                            //Se pone el nombre de Imagen como key
                            String idProceso = palabras[1];
                            String tituloAplicacion = stringToString(palabras[8]);
                            String nombreImagen = palabras[0];
                            if ( !"N/D".equalsIgnoreCase( tituloAplicacion) && !"tasklist.exe".equalsIgnoreCase( nombreImagen ) ){
                                String pid = palabras[1];
                                if ( idProceso.equals(getIdProcesoBuscado()) ){
                                    setProceso(new Proceso(null, palabras));
                                }
                            }
                        }
                    }
                }
                
            } catch (Exception e){
                System.out.println("- error : "+e);
                e.printStackTrace();
            }
        }

        public BufferedReader getBr() {
            return br;
        }

        public void setBr(BufferedReader br) {
            this.br = br;
        }

        public String getLinea() {
            return linea;
        }

        public void setLinea(String linea) {
            this.linea = linea;
        }

        public String getIdProcesoBuscado() {
            return idProcesoBuscado;
        }

        public void setIdProcesoBuscado(String idProcesoBuscado) {
            this.idProcesoBuscado = idProcesoBuscado;
        }

        public Proceso getProceso() {
            return proceso;
        }

        public void setProceso(Proceso proceso) {
            this.proceso = proceso;
        }
    }
    
    static class MyStreamReader extends Thread {
        private BufferedReader br;
        String linea;
        HashMap<String,Object> mapa;
        
        MyStreamReader(InputStream is) {
            this.br = new BufferedReader( new InputStreamReader(is) );
            if ( mapaProcesos==null )
                this.mapa = new LinkedHashMap<String,Object>();
            else
                this.mapa = mapaProcesos;
        }
        
        public void run() {
            try {
                String linea;
                //System.out.println("Creacion de mapa de procesos - ");
                while ( ((linea = br.readLine()) != null) ) {
                    if ( linea.length() > 10 ){
                        String [] palabras = linea.trim().substring(1,linea.length()-1).replaceAll("\",\"",";;_").split(";;_");
                        if ( palabras.length >  1){
                            //Se pone el nombre de Imagen como key
                            String tituloAplicacion = stringToString(palabras[8]);
                            String nombreImagen = palabras[0];
                            if ( !"N/D".equalsIgnoreCase( tituloAplicacion) && !"tasklist.exe".equalsIgnoreCase( nombreImagen ) ){
                                String pid = palabras[1];
                                //System.out.println("Se Revisa - titulo: "+tituloAplicacion);
                                if ( contieneNombreImagen_PID(nombreImagen,pid) ){
                                    //System.out.println("Se va a actualizar - imagen: "+nombreImagen+" pid: "+pid);
                                    //System.out.println("                   - TITULO: "+tituloAplicacion);
                                    //Proceso proceso = (Proceso)mapa.get(pid);
                                    Proceso proceso = getProcesoByNombreImagen(nombreImagen);
                                    if (proceso!=null){
                                        proceso.setTituloVentana(tituloAplicacion);
                                        System.out.println("->"+proceso);
                                        //System.out.println("Se actualizo - imagen: "+nombreImagen+" pid: "+pid);
                                    }
                                } else {//if (  !contieneNombreImagen_PID(nombreImagen,palabras[1]) ){
                                    //System.out.println("mapa no contiene: "+nombreImagen+" id: "+pid);
                                    ImageIcon icono = null;
                                    //File fileIcono = new File("images/middle.gif");
                                    //if ( fileIcono.exists() )
                                    //    icono = new ImageIcon("images/middle.gif");
                                    mapa.put(pid,new Proceso(icono,palabras));
                                    //System.out.println("*********Se AGREGO - imagen: "+nombreImagen+" pid: "+pid);
                                }
                            }
                        }
                    }
                }
                
            } catch (Exception e){
                System.out.println("- error : "+e);
                e.printStackTrace();
            }
        }
        
        private Proceso getProcesoByNombreImagen(String nombreImagen){
            Iterator it = mapa.keySet().iterator();
            while( it.hasNext() ){
                Proceso proceso = (Proceso) mapa.get(it.next());
                if ( nombreImagen.equalsIgnoreCase(proceso.getNombreImagen() ) ) {
                    return proceso;
                }
            }
            return null;
        }
       
        private boolean contieneNombreImagen_PID(String nombreImagen,String PID){
            Iterator it = mapa.keySet().iterator();
            while( it.hasNext() ){
                Proceso proceso = (Proceso) mapa.get(it.next());
                if ( nombreImagen.equalsIgnoreCase("java.exe") ){
                    if ( nombreImagen.equals( proceso.getNombreImagen() ) && PID.equals( proceso.getPid() ) ){
                        //System.out.println("---->Contiene IMG:"+proceso.getNombreImagen()+" ID: "+proceso.getPid());
                        return true;
                    }
                } else {
                    if ( nombreImagen.equals( proceso.getNombreImagen() ) ){
                        //System.out.println("---->Contiene IMG:"+proceso.getNombreImagen());
                        return true;
                    }
                }
         
            }
            return false;
        }
        
        HashMap getMapa(){
            return mapa;
        }
    }
    
    public String getIdProcesoActivo() {
        return idProcesoActivo;
    }
    
    public void setIdProcesoActivo(String idProcesoActivo) {
        this.idProcesoActivo = idProcesoActivo;
    }
    
    
}
