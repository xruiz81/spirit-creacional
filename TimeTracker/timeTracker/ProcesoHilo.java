
package timeTracker;

import javax.swing.JTable;

import static com.spirit.timeTracker.gui.model.PanelProcesosModel.COLUMNA_TIEMPO_PROCESO;
import javax.swing.table.DefaultTableModel;

public class ProcesoHilo extends Thread{
    //private String idHilo ="";
    private int contador=0;
    private boolean contar=true;
    private int fila;
    private JTable tablaProcesos;
    private DefaultTableModel modeloTablaProcesos = null;
    
    /*public ProcesoHilo(String idHilo) {
        this.setIdHilo(idHilo);
    }*/
    public ProcesoHilo(Proceso proceso, JTable tablaProcesos) {
        this.setFila(proceso.getFilaTabla());
        this.setContador(proceso.getContador());
        this.setTablaProcesos(tablaProcesos);
        if ( getTablaProcesos() != null )
            setModeloTablaProcesos((DefaultTableModel) getTablaProcesos().getModel());
    }
    
    public ProcesoHilo(int filaTabla,int contador,JTable tablaProcesos) {
        this.setFila(filaTabla);
        this.setContador(contador);
        this.setTablaProcesos(tablaProcesos);
        if ( getTablaProcesos() != null )
            setModeloTablaProcesos((DefaultTableModel) getTablaProcesos().getModel());
    }
    
    public void run() {
        try {
            //System.out.println("hilo :"+getIdHilo()+" esta vivo");
            while(isContar()){
                Thread.sleep(1000);
                if ( isContar() ){
                    setContador(getContador() + 1);
                    if ( getTablaProcesos() != null && 
                            ( getFila() >= 0 && getFila() < getModeloTablaProcesos().getRowCount() ) ){
                        getTablaProcesos().setValueAt(getContador(),getFila(), COLUMNA_TIEMPO_PROCESO);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Error en run: "+e);
            return;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void parar(){
        this.setContar(false);
    }
    
    public boolean contando(){
        return this.isContar();
    }
    
    public int getContador() {
        return contador;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public boolean isContar() {
        return contar;
    }
    
    public void setContar(boolean contar) {
        this.contar = contar;
    }
    
    public int getFila() {
        return fila;
    }
    
    public void setFila(int fila) {
        this.fila = fila;
    }
    
    public JTable getTablaProcesos() {
        return tablaProcesos;
    }
    
    public void setTablaProcesos(JTable tablaProcesos) {
        this.tablaProcesos = tablaProcesos;
    }

    public DefaultTableModel getModeloTablaProcesos() {
        return modeloTablaProcesos;
    }

    public void setModeloTablaProcesos(DefaultTableModel modeloTablaProcesos) {
        this.modeloTablaProcesos = modeloTablaProcesos;
    }
    
}
