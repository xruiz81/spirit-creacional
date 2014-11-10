package com.spirit.general.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoEmpleadoData;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoEmpleado;
import com.spirit.general.session.TipoEmpleadoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoEmpleadoModel extends JPTipoEmpleado {
	
	private static final long serialVersionUID = 3258689909907664953L;
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_VENDEDOR_SI = "SI";
	private static final String NOMBRE_VENDEDOR_NO = "NO";
	
	private Vector tipoEmpleadoVector = new Vector();
	private int tipoEmpleadoSeleccionado;
	private TipoEmpleadoIf tipoEmpleadoActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoEmpleadoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoEmpleado());
		anchoColumnasTabla();
		getTblTipoEmpleado().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		showSaveMode();
		
		getTblTipoEmpleado().addMouseListener(oMouseAdapterTblTipoEmpleado);
		getTblTipoEmpleado().addKeyListener(oKeyAdapterTblTipoEmpleado);
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoEmpleado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoEmpleado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblTipoEmpleado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
	}

	MouseListener oMouseAdapterTblTipoEmpleado = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	 KeyListener oKeyAdapterTblTipoEmpleado = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoEmpleadoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			tipoEmpleadoActualizadoIf = (TipoEmpleadoIf)  getTipoEmpleadoVector().get(getTipoEmpleadoSeleccionado());
			getTxtCodigo().setText(getTipoEmpleadoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoEmpleadoActualizadoIf().getNombre());
			if(getTipoEmpleadoActualizadoIf().getVendedor() == null || getTipoEmpleadoActualizadoIf().getVendedor().equals(NOMBRE_VENDEDOR_NO.substring(0,1))){
				getCmbVendedor().setSelectedItem(NOMBRE_VENDEDOR_NO);
			}else{
				getCmbVendedor().setSelectedItem(NOMBRE_VENDEDOR_SI);
			}
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoEmpleadoData data = new TipoEmpleadoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				if(getCmbVendedor().getSelectedItem().equals(NOMBRE_VENDEDOR_NO)){
					data.setVendedor(NOMBRE_VENDEDOR_NO.substring(0,1));
				}else{
					data.setVendedor(NOMBRE_VENDEDOR_SI.substring(0,1));
				}

				SessionServiceLocator.getTipoEmpleadoSessionService().addTipoEmpleado(data);
				SpiritAlert.createAlert("Tipo de Empleado guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoEmpleado",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomacin!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoEmpleadoActualizadoIf((TipoEmpleadoIf) getTipoEmpleadoVector().get(getTipoEmpleadoSeleccionado()));

				getTipoEmpleadoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoEmpleadoActualizadoIf().setNombre(getTxtNombre().getText());
				if(getCmbVendedor().getSelectedItem().equals(NOMBRE_VENDEDOR_NO)){
					getTipoEmpleadoActualizadoIf().setVendedor(NOMBRE_VENDEDOR_NO.substring(0,1));
				}else{
					getTipoEmpleadoActualizadoIf().setVendedor(NOMBRE_VENDEDOR_SI.substring(0,1));
				}
								
				SessionServiceLocator.getTipoEmpleadoSessionService().saveTipoEmpleado(getTipoEmpleadoActualizadoIf());
				getTipoEmpleadoVector().setElementAt(getTipoEmpleadoActualizadoIf(), getTipoEmpleadoSeleccionado());
				setTipoEmpleadoActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de Empleado actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoEmpleado",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la infomacin!", SpiritAlert.ERROR);
		}		

	}

	public void delete() {
		try {
			TipoEmpleadoIf tipoEmpleadoIf = (TipoEmpleadoIf) getTipoEmpleadoVector().get(getTipoEmpleadoSeleccionado());
			SessionServiceLocator.getTipoEmpleadoSessionService().deleteTipoEmpleado(tipoEmpleadoIf.getId());
			SpiritAlert.createAlert("Tipo de Empleado eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoEmpleado",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoEmpleado = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoEmpleadoIterator = tipoEmpleado.iterator();
			
			if(!getTipoEmpleadoVector().isEmpty()){
				getTipoEmpleadoVector().removeAllElements();
			}
			
			while (tipoEmpleadoIterator.hasNext()) {
				TipoEmpleadoIf tipoEmpleadoIf = (TipoEmpleadoIf) tipoEmpleadoIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoEmpleado().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoEmpleadoVector().add(tipoEmpleadoIf);
				
				agregarColumnasTabla(tipoEmpleadoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoEmpleado(), tipoEmpleado, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoEmpleadoIf tipoEmpleadoIf, Vector<String> fila){		
		fila.add(tipoEmpleadoIf.getCodigo());
		fila.add(tipoEmpleadoIf.getNombre());
		if(tipoEmpleadoIf.getVendedor() == null || tipoEmpleadoIf.getVendedor().equals(NOMBRE_VENDEDOR_NO.substring(0,1))){
			fila.add(NOMBRE_VENDEDOR_NO);
		}else{
			fila.add(NOMBRE_VENDEDOR_SI);
		}
	}

	public boolean isEmpty() {

		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection tipoEmpleado = null;
		boolean codigoRepetido = false;
		
		try {
			tipoEmpleado = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoEmpleadoIt = tipoEmpleado.iterator();
		
		while (tipoEmpleadoIt.hasNext()) {
			TipoEmpleadoIf tipoEmpleadoIf = (TipoEmpleadoIf) tipoEmpleadoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoEmpleadoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoEmpleadoIf.getCodigo())) 
					if (getTipoEmpleadoActualizadoIf().getId().equals(tipoEmpleadoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Tipo Empleado está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un codigo para el Tipo Empleado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo Empleado !!",
					SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbVendedor().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar Si es vendedor o No!", SpiritAlert.WARNING);
			getCmbVendedor().grabFocus();
			return false;
		}
		
		return true;

	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoEmpleado().getModel();
		
		for(int i= this.getTblTipoEmpleado().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);

		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();

	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblTipoEmpleado().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getTipoEmpleadoVector() {
		return this.tipoEmpleadoVector;
	}
	
	public void setTipoEmpleadoVector(Vector tipoEmpleadoVector) {
		this.tipoEmpleadoVector = tipoEmpleadoVector;
	}
	
	public int getTipoEmpleadoSeleccionado() {
		return this.tipoEmpleadoSeleccionado;
	}
	
	public void setTipoEmpleadoSeleccionado(int tipoEmpleadoSeleccionado) {
		this.tipoEmpleadoSeleccionado = tipoEmpleadoSeleccionado;
	}
	
	public TipoEmpleadoIf getTipoEmpleadoActualizadoIf() {
		return this.tipoEmpleadoActualizadoIf;
	}
	
	public void setTipoEmpleadoActualizadoIf(TipoEmpleadoIf tipoEmpleadoActualizadoIf) {
		this.tipoEmpleadoActualizadoIf = tipoEmpleadoActualizadoIf;
	}
 
}

