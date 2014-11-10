package com.spirit.client.model;

import java.awt.Cursor;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.spirit.client.ChangeModeImpl;
import com.spirit.client.SpiritAlert;
import com.spirit.util.SpiritComboBoxModel;

public abstract class SpiritModelImpl extends JPanel implements SpiritModel {

	private int mode;

	//public abstract void showSaveMode();

	//public abstract void showFindMode();

	public abstract void find();

	public abstract void save();

	public abstract void update();

	public abstract void delete();

	public abstract void clean();

	public abstract void report();
	
	public abstract void duplicate();

	public abstract boolean validateFields();
	
	public boolean usingDeleteHandler() {
		return true;
	}
	
	public void switchTab() {}
	
	public void quickSearch() {
		System.out.println("Quick Search desde Implement Model");
	}
	
	public  void authorize(){}
	
	
	public void pingresardatos(){}
	public void cobroEfectivo(){}
	public void cobroCheque(){}
	public void cobroGiftCard(){}
	public void cobroTarjetaCredito(){}
	public void cobroTarjetaDebito(){}	
	public void cobroCredito(){}
	
	public void borrarItem(){} 
	
	public void historialVentas(){}
	public void donacionElegir(){}
	public void tarjetaAfiliado(){}
	public void bloquearCaja(){}
	public void cancelarVenta(){}
	public void creditoCliente(){}
	
	public void abrirCajon(){}
	
	
	
	public void t_facturar(){}
	public void t_anticipo(){}
	public void t_nventa(){}
	public void t_devolucion(){}
	
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;

	}

	protected void setSaveMode() {
		this.setMode(SpiritMode.SAVE_MODE);
		new ChangeModeImpl().fireChangeModeEvent("MODO: SAVE");

	}

	protected void setUpdateMode() {
		this.setMode(SpiritMode.UPDATE_MODE);
		new ChangeModeImpl().fireChangeModeEvent("MODO: UPDATE");
	}
	
	protected void setFindMode(){
		this.setMode(SpiritMode.FIND_MODE);
		new ChangeModeImpl().fireChangeModeEvent("MODO: FIND");
	}
	
	protected static void refreshCombo(JComboBox combo,List<? extends Object> lista ){
		refreshComboByIndex(combo,lista, 0);
	}
	
	protected static void refreshComboByIndex(JComboBox combo,List<? extends Object> lista,int index){
		try {
			SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(lista);
			int itemSeleccionado = combo.getSelectedIndex(); 
			combo.setModel(cmbModel);
			int numeroItems = combo.getItemCount();
			if (combo.getItemCount() > 0) {
				if ( itemSeleccionado >= 0 && numeroItems == combo.getItemCount() && itemSeleccionado <= combo.getItemCount())
					combo.setSelectedIndex(itemSeleccionado);
				else
					combo.setSelectedIndex(index);
			}
		} catch (java.lang.IllegalArgumentException ae) {
			ae.printStackTrace();
			combo.setSelectedIndex(index);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al refrescar la pantalla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void setSorterTable(JTable table){
		RowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tableRowSorter);
	}

	protected void limpiarTabla(JTable tabla){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		for (int i = tabla.getRowCount(); i > 0; --i)
			modelo.removeRow(i - 1);
		tabla.repaint();
	}
	
	public void setCursorEspera(){
		Cursor cursor  = getCursor();
		setCursor(cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public void setCursorNormal(){
		Cursor cursor  = getCursor();
		setCursor(cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/*
	 * 
	 * public void find(Long idFiltroBusqueda) {
	 *  }
	 * 
	 * public ArrayList find(int startIndex, int endIndex) { return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Map aMap) { return
	 * null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Long
	 * idFiltroBusqueda) { return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Map aMap, Long
	 * idFiltroBusqueda) { return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Map aMap, String
	 * tipoCliente) { return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Map aMap, Long
	 * idFiltroBusqueda, String tipoCliente) { return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, String tipoCliente) {
	 * return null; }
	 * 
	 * public ArrayList find(int startIndex, int endIndex, Long
	 * idFiltroBusqueda, String tipoCliente) { return null; }
	 */

}
