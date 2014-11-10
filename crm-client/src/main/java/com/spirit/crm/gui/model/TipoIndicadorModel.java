package com.spirit.crm.gui.model;

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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.TipoIndicadorData;
import com.spirit.crm.entity.TipoIndicadorIf;
import com.spirit.crm.gui.panel.JPTipoIndicador;
import com.spirit.crm.session.TipoIndicadorSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoIndicadorModel extends JPTipoIndicador {

	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	
	private static final String NOMBRE_OPCION_SI = "SI";
	private static final String OPCION_SI = NOMBRE_OPCION_SI.substring(0,1);
	private static final String NOMBRE_OPCION_NO = "NO";
	private static final String OPCION_NO = NOMBRE_OPCION_NO.substring(0,1);
	private DefaultTableModel tableModel;
	private Vector tipoIndicadorVector = new Vector();
	private int tipoIndicadorSeleccionado;
	private TipoIndicadorIf tipoIndicadorActualizadoIf;
	
	public TipoIndicadorModel() {
		initKeyListeners();
		setSorterTable(getTblTipoIndicador());
		anchoColumnasTabla();
		getTblTipoIndicador().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblTipoIndicador().addMouseListener(oMouseAdapterTblTipoIndicador);
		getTblTipoIndicador().addKeyListener(oKeyAdapterTblTipoIndicador);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoIndicador().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoIndicador().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblTipoIndicador().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblTipoIndicador = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoIndicador = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoIndicadorSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoIndicadorActualizadoIf = (TipoIndicadorIf) getTipoIndicadorVector().get(getTipoIndicadorSeleccionado());
			getTxtCodigo().setText(getTipoIndicadorActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoIndicadorActualizadoIf().getNombre());
			getTxtCodigo().setEnabled(false);
			if (tipoIndicadorActualizadoIf.getAcumulativo().equals(OPCION_SI))
				getCmbAcumulativo().setSelectedItem(NOMBRE_OPCION_SI);
			else if (tipoIndicadorActualizadoIf.getAcumulativo().equals(OPCION_NO))
				getCmbAcumulativo().setSelectedItem(NOMBRE_OPCION_NO);
			
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoIndicadorData data = new TipoIndicadorData();

				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setAcumulativo(this.getCmbAcumulativo().getSelectedItem().toString().substring(0,1));
				data.setEmpresaId(Parametros.getIdEmpresa());

				SessionServiceLocator.getTipoIndicadorSessionService().addTipoIndicador(data);
				SpiritAlert.createAlert("Tipo de indicador guardado con éxito", SpiritAlert.INFORMATION);
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setTipoIndicadorActualizadoIf((TipoIndicadorIf) getTipoIndicadorVector().get(getTipoIndicadorSeleccionado()));
				getTipoIndicadorActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoIndicadorActualizadoIf().setAcumulativo(getCmbAcumulativo().getSelectedItem().toString().substring(0,1));
				this.clean();
				getTipoIndicadorVector().setElementAt(getTipoIndicadorActualizadoIf(), getTipoIndicadorSeleccionado());
				SessionServiceLocator.getTipoIndicadorSessionService().saveTipoIndicador(getTipoIndicadorActualizadoIf());
				setTipoIndicadorActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de indicador actualizado con éxito", SpiritAlert.INFORMATION);

				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			TipoIndicadorIf tipoIndicadorIf = (TipoIndicadorIf) getTipoIndicadorVector().get(getTipoIndicadorSeleccionado());
			SessionServiceLocator.getTipoIndicadorSessionService().deleteTipoIndicador(tipoIndicadorIf.getId());
			SpiritAlert.createAlert("Tipo de indicador eliminado con éxito", SpiritAlert.INFORMATION);
			this.showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbAcumulativo().setSelectedItem(NOMBRE_OPCION_SI);

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoIndicador().getModel();
		
		for(int i= this.getTblTipoIndicador().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbAcumulativo().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			if (!SessionServiceLocator.getTipoIndicadorSessionService().findTipoIndicadorByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection tiposIndicadores = SessionServiceLocator.getTipoIndicadorSessionService().findTipoIndicadorByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator tiposIndicadoresIterator = tiposIndicadores.iterator();
				
				if(!getTipoIndicadorVector().isEmpty()){
					getTipoIndicadorVector().removeAllElements();
				}
				
				while (tiposIndicadoresIterator.hasNext()) {
					TipoIndicadorIf tipoIndicadorIf = (TipoIndicadorIf) tiposIndicadoresIterator.next();
					
					tableModel = (DefaultTableModel) getTblTipoIndicador().getModel();
					Vector<String> fila = new Vector<String>();
					getTipoIndicadorVector().add(tipoIndicadorIf);
					
					agregarColumnasTabla(tipoIndicadorIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblTipoIndicador(), tiposIndicadores, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoIndicadorIf tipoIndicadorIf, Vector<String> fila) {	
		fila.add(tipoIndicadorIf.getCodigo());
		fila.add(tipoIndicadorIf.getNombre());
		if (tipoIndicadorIf.getAcumulativo().equals(OPCION_SI))
			fila.add(NOMBRE_OPCION_SI);
		else if (tipoIndicadorIf.getAcumulativo().equals(OPCION_NO))
			fila.add(NOMBRE_OPCION_NO);
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection tiposIndicadores = null;
		boolean codigoRepetido = false;
		
		try {
			tiposIndicadores = SessionServiceLocator.getTipoIndicadorSessionService().findTipoIndicadorByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tiposIndicadoresIt = tiposIndicadores.iterator();
		
		while (tiposIndicadoresIt.hasNext()) {
			TipoIndicadorIf tipoIndicadorIf = (TipoIndicadorIf) tiposIndicadoresIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoIndicadorIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoIndicadorIf.getCodigo())) 
					if (tipoIndicadorActualizadoIf.getId().equals(tipoIndicadorIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Indicador está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Indicador !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Indicador !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbAcumulativo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el Tipo de Indicador es acumulativo o no !!", SpiritAlert.WARNING);
			getCmbAcumulativo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getTipoIndicadorVector() {
		return this.tipoIndicadorVector;
	}
	
	public void setTipoIndicadorVector(Vector tipoIndicadorVector) {
		this.tipoIndicadorVector = tipoIndicadorVector;
	}
	
	public int getTipoIndicadorSeleccionado() {
		return this.tipoIndicadorSeleccionado;
	}
	
	public void setTipoIndicadorSeleccionado(int tipoIndicadorSeleccionado) {
		this.tipoIndicadorSeleccionado = tipoIndicadorSeleccionado;
	}
	
	public TipoIndicadorIf getTipoIndicadorActualizadoIf() {
		return this.tipoIndicadorActualizadoIf;
	}
	
	public void setTipoIndicadorActualizadoIf(TipoIndicadorIf tipoIndicadorActualizadoIf) {
		this.tipoIndicadorActualizadoIf = tipoIndicadorActualizadoIf;
	}
	
	 
}
