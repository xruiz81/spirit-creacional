/*
 * Proceso.java
 *
 * Created on June 11, 2007, 4:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker;

import javax.swing.ImageIcon;
import static timeTracker.Utiles.stringToString;
import static com.spirit.timeTracker.gui.model.PanelProcesosModel.FILA_STAND_BY_PROCESO;
/**
 *
 * @author lmunoz
 */
public class Proceso {
    private String nombreImagen;
    private String nombreSesion;
    private String usoMemoria;
    private String estado;
    private String nombreUsuario;
    private String tiempoCPU;
    private String tituloVentana;
    private String pid;
    private String numeroSesion;
    
    private ImageIcon icono;
    
    private ProcesoHilo hiloContador;
    private int filaTabla=0;
    private int contador=0;
    
    public Proceso(String tituloVentana){
        this.setPid("_"+tituloVentana+"_");
        this.setIcono(null);
        this.setTituloVentana(tituloVentana);
        this.setUsoMemoria("");
        this.setNombreImagen("");
        this.setContador(0);
        this.setFilaTabla(FILA_STAND_BY_PROCESO);
    }
    
    public Proceso(ImageIcon icono,String[] parametros){
        if (parametros.length==9){
            this.setNombreImagen(parametros[0]);
            this.setPid(parametros[1]);
            this.setNombreSesion(parametros[2]);
            this.setNumeroSesion(parametros[3]);
            this.setUsoMemoria(parametros[4]);
            this.setEstado(parametros[5]);
            this.setNombreUsuario(parametros[6]);
            this.setTiempoCPU(parametros[7]);
            this.setTituloVentana(stringToString(parametros[8]));
            this.setContador(0);
            this.setFilaTabla(-1);
            this.setIcono(icono);
            //this.setHiloContador(new ProcesoHilo(parametros[1]));
        } else{
            System.err.println("Creacion de Proceso Defectuoso");
        }
    }
    
    public Object[] getFilaDeTabla(){
        /* NOMBRE APLICACION - TIEMPO - USO MEMORIA - NOMBRE IMAGEN  */
        Object[] objetos =
        {
            this.getIcono(),
            this.getTituloVentana(),
            Integer.valueOf( this.getContador() ),
            this.getUsoMemoria(),this.getNombreImagen()
        };
        return objetos ;
    }
    
    public String toString(){
        //return this.getNombreImagen();
        return (this.getFilaTabla() + " | " +this.getNombreImagen()+" | "+this.getPid()+" | "+this.getNombreSesion()+" | "
                +this.getNumeroSesion()+" | "+this.getUsoMemoria()+" | "+this.getEstado()+" | "+this.getNombreUsuario()+" | "
                +this.getTiempoCPU()+" | "+this.getTituloVentana());
        
    }
    
    public String getNombreImagen() {
        return nombreImagen;
    }
    
    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
    
    public String getNombreSesion() {
        return nombreSesion;
    }
    
    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
    }
    
    public String getUsoMemoria() {
        return usoMemoria;
    }
    
    public void setUsoMemoria(String usoMemoria) {
        this.usoMemoria = usoMemoria;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getTiempoCPU() {
        return tiempoCPU;
    }
    
    public void setTiempoCPU(String tiempoCPU) {
        this.tiempoCPU = tiempoCPU;
    }
    
    public String getTituloVentana() {
        return tituloVentana;
    }
    
    public void setTituloVentana(String tituloVentana) {
        this.tituloVentana = tituloVentana;
    }
    
    public String getPid() {
        return pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    public String getNumeroSesion() {
        return numeroSesion;
    }
    
    public void setNumeroSesion(String numeroSesion) {
        this.numeroSesion = numeroSesion;
    }
    
    public int getContador() {
        return contador;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public void setFilaTabla(int filaTabla) {
        this.filaTabla = filaTabla;
    }
    
    public int getFilaTabla() {
        return filaTabla;
    }
    
    public ProcesoHilo getHiloContador() {
        return hiloContador;
    }
    
    public void setHiloContador(ProcesoHilo hiloContador) {
        this.hiloContador = hiloContador;
    }
    
    public ImageIcon getIcono() {
        return icono;
    }
    
    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }
    
}
