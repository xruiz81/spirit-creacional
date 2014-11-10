package com.spirit.client.model;



/**
 * @author Leonardo Reyes
 *
 */
public interface SpiritModel {
	
	public abstract int getMode();
	public abstract void setMode(int mode);
	public abstract void clean();
	public abstract void save();
	public abstract void delete();
	public abstract void update();
	public abstract void report();
	public abstract void authorize();
	public abstract boolean usingDeleteHandler();
	
	
	public abstract void pingresardatos();
	public abstract void cobroEfectivo();
	public abstract void cobroCheque();
	public abstract void cobroGiftCard();
	public abstract void cobroTarjetaCredito();
	public abstract void cobroTarjetaDebito();
	public abstract void cobroCredito();
 
		
	public abstract void borrarItem(); 
	
	public abstract void historialVentas();
	public abstract void donacionElegir();	
	public abstract void tarjetaAfiliado();
	public abstract void bloquearCaja();
	public abstract void cancelarVenta();
	public abstract void creditoCliente();
	
	public abstract void abrirCajon();
	
	public abstract void t_facturar();
	public abstract void t_anticipo();
	public abstract void t_nventa();
	public abstract void t_devolucion();
	
	
	public abstract void find();
	public abstract boolean isEmpty();
	public abstract boolean validateFields();
	public abstract void showSaveMode();
	public abstract void showFindMode();
	public abstract void showUpdateMode();
	public abstract void addDetail();
	public abstract void updateDetail();
	public abstract void deleteDetail();
	public abstract void refresh();
	public abstract void duplicate();
	/*
	
	public abstract FinderTable getFinderTable();

	public abstract void setFinderTable(FinderTable table);
	
	public abstract boolean isFinderTableVisible();
	
	public abstract ArrayList find(int startIndex, int endIndex);
	
	public abstract ArrayList find(int startIndex, int endIndex, Map aMap);
	
	public abstract int getListSize();
	
	public abstract ArrayList getDataForTable(ArrayList list);
	
	//Nuevas para el popup panel
	public abstract void find(Long idFiltroBusqueda);
	
	public abstract TableModel getTableModel();
	
	public abstract ArrayList getListModel();
	
	public abstract void setComponentsOfPopup(int rowTablePopup);
	
	//Nuevas para el popup finder
	
	public abstract ArrayList find(int startIndex, int endIndex,Long idFiltroBusqueda);
	
	public abstract ArrayList find(int startIndex, int endIndex, Map aMap,Long idFiltroBusqueda);
	
	public abstract int getListSize(Map aMap);
	
	public abstract int getListSize(Long idFiltroBusqueda);
	
	public abstract int getListSize(Map aMap,Long idFiltroBusqueda);
	
	// Nuevas para el popup finder cliente
	
	public abstract ArrayList find(int startIndex, int endIndex, Map aMap, String tipoCliente);
	
	public abstract ArrayList find(int startIndex, int endIndex, Map aMap, Long idFiltroBusqueda, String tipoCliente);
	
	public abstract ArrayList find(int startIndex, int endIndex, String tipoCliente);
	
	public abstract ArrayList find(int startIndex, int endIndex, Long idFiltroBusqueda, String tipoCliente);
	
	*/
	
	//public abstract List find(Criteria c);
}
