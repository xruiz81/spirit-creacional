/*
 * Aplicaciones.java
 *
 * Created on June 11, 2007, 5:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker;

import com.sun.jna.Pointer;
import java.util.Iterator;
import javax.swing.JLabel;

import static com.spirit.timeTracker.gui.model.PanelGeneralModel.mapaProcesos;
import static com.spirit.timeTracker.gui.model.PanelProcesosModel.COLUMNA_TITULO_APLICACION;
import static timeTracker.Procesos.getProcesoDeListaProcesos;
import static timeTracker.Utiles.lineaToString;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lmunoz
 */
public class Aplicaciones extends Thread{
    private User32 user32 = User32.INSTANCE;
    private Pointer apuntador;
    private byte[] linea = new byte[300];
    private int[] idProcesoVentanaActiva = new int[1];
    private String aplicacionEnUso="";
    private String tituloVentana="";
    private JLabel lugarImpresion=null;
    private ProcesoHilo procesoHilo;
    private JTable tablaProcesos;
    private Proceso procesoCorriendo = null;
    boolean procesoVerificado = false;
    private boolean sensar=true;
    private boolean inactivo = false;
    
    public Aplicaciones() {
    }
    
    public Aplicaciones(JLabel lugarImpresion,JTable tablaProcesos){
        this.setLugarImpresion(lugarImpresion);
        this.setTablaProcesos(tablaProcesos);
    }
    
    public void run() {
        try {
            while(isSensar()){
                encerarIdProcesoVentanaActiva();
                encerarLineaTituloVentana();
                setApuntador(getUser32().GetForegroundWindow());
                getUser32().GetWindowTextA(getApuntador(),getLinea(), 300);
                String tituloVentana = lineaToString(getLinea());
                setTituloVentana(tituloVentana);
                //Proceso procesoActivo = getProcesoDeListaProcesos(idProcesoAplicacion);
                Proceso procesoExistente = null;
                Proceso procesoActivo = getProcesoByTituloVentana(tituloVentana);
                if ( procesoActivo !=null ){
                    if ( procesoActivo != getProcesoCorriendo() ){
                        //System.out.println("-cambio de aplicacion - Esta en Memoria-");
                        pararProcesoCorriendo();
                        actualizaTituloVentanaTabla(procesoActivo);
                        setProcesoHilo(
                                new ProcesoHilo(procesoActivo,getTablaProcesos()));
                        procesoActivo.setHiloContador(getProcesoHilo());
                        getProcesoHilo().start();
                        setProcesoCorriendo(procesoActivo);
                        //System.out.println("Corriendo: "+procesoActivo);
                        inactivo=false;
                        procesoVerificado = false;
                    }
                } else if ( "program manager".equalsIgnoreCase(getTituloVentana()) ){
                    if ( !inactivo ){
                        pararProcesoCorriendo();
                        Proceso procesoStandBy = (Proceso)mapaProcesos.get("_STAND BY_");
                        setProcesoHilo(
                                new ProcesoHilo(procesoStandBy,getTablaProcesos()));
                        procesoStandBy.setHiloContador(getProcesoHilo());
                        getProcesoHilo().start();
                        setProcesoCorriendo(procesoStandBy);
                        actualizaTituloVentanaTabla(procesoStandBy);
                        //System.out.println("no se esta haciendo nada...");
                        inactivo=true;
                        procesoVerificado = false;
                    }
                } else if ( procesoActivo == null ){
                    //if ( !procesoVerificado ){
                    //System.out.println("-cambio de aplicacion - No esta en Memoria -");
                    getUser32().GetWindowThreadProcessId(getApuntador(), getIdProcesoVentanaActiva());
                    String idProcesoAplicacion = String.valueOf(getIdProcesoVentanaActiva()[0]);
                    //System.out.println("id: "+idProcesoAplicacion);
                    pararProcesoCorriendo();
                    Thread.sleep(300);
                    procesoExistente = getProcesoDeListaProcesos(idProcesoAplicacion);
                    if ( procesoExistente != null ){
                        //System.out.println("-   proceso existe...");
                        String nombreImagenActiva = procesoExistente.getNombreImagen();
                        String pid = procesoExistente.getPid();
                        procesoActivo = getProcesoByNombreImagen_PID(nombreImagenActiva,pid);
                        if ( procesoActivo != null ) {
                            procesoActivo.setTituloVentana(procesoExistente.getTituloVentana());
                            //System.out.println(" - > Se cambia de nombre a proceso...");
                            //System.out.println(procesoExistente);
                        } else{
                            int filaTabla = getTablaProcesos().getRowCount();
                            procesoExistente.setFilaTabla(filaTabla);
                            mapaProcesos.put(procesoExistente.getPid(),procesoExistente);
                            agregarFilaProcesoTabla(procesoExistente);
                            //System.out.println("Se Agrega: "+procesoExistente);
                        }
                        inactivo=false;
                    }
                    procesoVerificado = true;
                    //}
                } 
                getLugarImpresion().setText(getTituloVentana());
            }
            pararProcesoCorriendo();
        } /*catch (InterruptedException e) {
            return;
        }*/ catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void agregarFilaProcesoTabla(Proceso proceso){
        ((DefaultTableModel)getTablaProcesos().getModel()).addRow(proceso.getFilaDeTabla());
    }
    
    private void actualizaTituloVentanaTabla(Proceso proceso){
        this.getTablaProcesos().setValueAt(
                proceso.getTituloVentana(),proceso.getFilaTabla(),COLUMNA_TITULO_APLICACION);
    }
    
    private void pararProcesoCorriendo(){
        if (getProcesoCorriendo()!=null){
            //if (  )
            getProcesoCorriendo().getHiloContador().parar();
            getProcesoCorriendo().setContador(getProcesoHilo().getContador());
            setProcesoHilo(null);
            setProcesoCorriendo(null);
            //System.out.println("***SE PARO: "+getProcesoCorriendo());
        }
    }
    
    private void encerarIdProcesoVentanaActiva(){
        getIdProcesoVentanaActiva()[0] = 0;
    }
    
    private void encerarLineaTituloVentana(){
        for ( int i=0;i<getLinea().length;i++ )
            getLinea()[i] = 0;
    }
    
    private Proceso getProcesoById(String idProceso){
        Iterator it = mapaProcesos.keySet().iterator();
        while( it.hasNext() ){
            Proceso proceso = (Proceso) mapaProcesos.get(it.next());
            if ( idProceso.equals( proceso.getPid() ) ){
                return proceso;
            }
        }
        return null;
    }
    
    private boolean contieneNombreImagen_PID(String nombreImagen, String pid){
        Iterator it = mapaProcesos.keySet().iterator();
        while( it.hasNext() ){
            Proceso proceso = (Proceso) mapaProcesos.get(it.next());
            if ( !nombreImagen.equalsIgnoreCase("java.exe") ){
                if ( nombreImagen.equals( proceso.getNombreImagen() ) ){
                    return true;
                }
            } else {
                if ( nombreImagen.equals( proceso.getNombreImagen() )
                && pid.equalsIgnoreCase(proceso.getPid())){
                    return true;
                }
            }
        }
        return false;
    }
    
    private Proceso getProcesoByNombreImagen_PID(String nombreImagen,String pid){
        Iterator it = mapaProcesos.keySet().iterator();
        while( it.hasNext() ){
            Proceso proceso = (Proceso) mapaProcesos.get(it.next());
            if ( !"java.exe".equalsIgnoreCase(nombreImagen) ){
                if ( nombreImagen.equalsIgnoreCase(proceso.getNombreImagen() ) )
                    return proceso;
            } else {
                if ( nombreImagen.equalsIgnoreCase(proceso.getNombreImagen() )
                && pid.equals(proceso.getPid()))
                    return proceso;
            }
        }
        return null;
    }
    
    private Proceso getProcesoByTituloVentana(String tituloVentana){
        Proceso proceso = null;
        Iterator it = mapaProcesos.keySet().iterator();
        while( it.hasNext() ){
            proceso = (Proceso) mapaProcesos.get(it.next());
            if ( tituloVentana.equals( proceso.getTituloVentana() ) ){
                //System.out.println("Retorna comparacion: -"+tituloVentana+"- con -"+proceso.getTituloVentana()+"-");
                return proceso;
            }
        }
        //System.out.println("RETORNA PROCESO NULL");
        return null;
    }
    
    private boolean contieneTituloProceso(String nombreAplicacion){
        Iterator it = mapaProcesos.keySet().iterator();
        while( it.hasNext() ){
            Proceso proceso = (Proceso) mapaProcesos.get(it.next());
            if ( nombreAplicacion.equals( proceso.getTituloVentana() ) ){
                return true;
            }
        }
        return false;
    }
    
    public User32 getUser32() {
        return user32;
    }
    
    public void setUser32(User32 user32) {
        this.user32 = user32;
    }
    
    public Pointer getApuntador() {
        return apuntador;
    }
    
    public void setApuntador(Pointer apuntador) {
        this.apuntador = apuntador;
    }
    
    public JLabel getLugarImpresion() {
        return lugarImpresion;
    }
    
    public void setLugarImpresion(JLabel lugarImpresion) {
        this.lugarImpresion = lugarImpresion;
    }
    
    public ProcesoHilo getProcesoHilo() {
        return procesoHilo;
    }
    
    public void setProcesoHilo(ProcesoHilo procesoHilo) {
        this.procesoHilo = procesoHilo;
    }
    
    public JTable getTablaProcesos() {
        return tablaProcesos;
    }
    
    public void setTablaProcesos(JTable tablaProcesos) {
        this.tablaProcesos = tablaProcesos;
    }
    
    public int[] getIdProcesoVentanaActiva() {
        return idProcesoVentanaActiva;
    }
    
    public void setIdProcesoVentanaActiva(int[] idProcesoVentanaActiva) {
        this.idProcesoVentanaActiva = idProcesoVentanaActiva;
    }
    
    public String getTituloVentana() {
        return tituloVentana;
    }
    
    public void setTituloVentana(String tituloVentana) {
        this.tituloVentana = tituloVentana;
    }
    
    public byte[] getLinea() {
        return linea;
    }
    
    public void setLinea(byte[] linea) {
        this.linea = linea;
    }
    
    public String getAplicacionEnUso() {
        return aplicacionEnUso;
    }
    
    public void setAplicacionEnUso(String aplicacionEnUso) {
        this.aplicacionEnUso = aplicacionEnUso;
    }
    
    public boolean isSensar() {
        return sensar;
    }
    
    public void setSensar(boolean sensar) {
        this.sensar = sensar;
    }
    
    public Proceso getProcesoCorriendo() {
        return procesoCorriendo;
    }
    
    public void setProcesoCorriendo(Proceso procesoCorriendo) {
        this.procesoCorriendo = procesoCorriendo;
    }
    
}
