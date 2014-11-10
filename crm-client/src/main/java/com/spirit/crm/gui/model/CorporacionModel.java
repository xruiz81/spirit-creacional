package com.spirit.crm.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.CorporacionData;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.panel.JPCorporacion;
import com.spirit.crm.session.CorporacionSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.util.DateHelperClient;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CorporacionModel extends JPCorporacion {
	
	private static final long serialVersionUID = 7325466531465406749L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final int MAX_LONGITUD_REPRESENTANTE = 40;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private Vector corporacionVector = new Vector();
	private int corporacionSeleccionada;
	private CorporacionIf corporacionActualizadaIf;
	private DefaultTableModel tableModel;
	
	
	public CorporacionModel() {
		initKeyListeners();
		setSorterTable(getTblCorporacion());
		anchoColumnasTabla();
		getTblCorporacion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblCorporacion().addMouseListener(oMouseAdapterTblCorporacion);
		getTblCorporacion().addKeyListener(oKeyAdapterTblCorporacion);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtRepresentante().addKeyListener(new TextChecker(MAX_LONGITUD_REPRESENTANTE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblCorporacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCorporacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblCorporacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblCorporacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblCorporacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblCorporacion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setCorporacionSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			corporacionActualizadaIf = (CorporacionIf) getCorporacionVector().get(getCorporacionSeleccionada());
			getTxtCodigo().setText(getCorporacionActualizadaIf().getCodigo());
			getTxtNombre().setText(getCorporacionActualizadaIf().getNombre());
			getTxtRepresentante().setText(getCorporacionActualizadaIf().getRepresentante());
			getTxtFechaCreacion().setText(Utilitarios.getFechaUppercase(getCorporacionActualizadaIf().getFechaCreacion()));
			
			if (ESTADO_ACTIVO.equals(getCorporacionActualizadaIf().getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}
	
	public void save() {		
		try {
			if (validateFields()) {
				CorporacionData data = new CorporacionData();
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setRepresentante(this.getTxtRepresentante().getText());
				data.setFechaCreacion(DateHelperClient.getTimeStampNow());
				data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
				SessionServiceLocator.getCorporacionSessionService().addCorporacion(data);
				SpiritAlert.createAlert("Corporación guardada con éxito", SpiritAlert.INFORMATION);
				clean();
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setCorporacionActualizadaIf((CorporacionIf) getCorporacionVector().get(getCorporacionSeleccionada()));
				getCorporacionActualizadaIf().setNombre(getTxtNombre().getText());
				getCorporacionActualizadaIf().setRepresentante(getTxtRepresentante().getText());
				getCorporacionActualizadaIf().setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
				this.clean();
				getCorporacionVector().setElementAt(getCorporacionActualizadaIf(), getCorporacionSeleccionada());
				SessionServiceLocator.getCorporacionSessionService().saveCorporacion(getCorporacionActualizadaIf());
				setCorporacionActualizadaIf(null);
				SpiritAlert.createAlert("Corporación actualizada con éxito", SpiritAlert.INFORMATION);
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}		
	}
	
	public void delete() {
		try {
			CorporacionIf corporacionIf = (CorporacionIf) getCorporacionVector().get(getCorporacionSeleccionada());
			SessionServiceLocator.getCorporacionSessionService().deleteCorporacion(corporacionIf.getId());
			SpiritAlert.createAlert("Corporación eliminada con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
		}
	}

	private void agregarColumnasTabla(CorporacionIf corporacionIf, Vector<String> fila){
		
		fila.add(corporacionIf.getCodigo());
		fila.add(corporacionIf.getNombre());
		fila.add(corporacionIf.getRepresentante());
		
		if(ESTADO_ACTIVO.equals(corporacionIf.getEstado()))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else if(ESTADO_INACTIVO.equals(corporacionIf.getEstado()))
			fila.add(NOMBRE_ESTADO_INACTIVO);
		
	}
	
	private void cargarTabla() {
		try {
			if(!SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(Parametros.getIdEmpresa()).isEmpty()){
				Collection corporacion = SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator corporacionIterator = corporacion.iterator();
				
				if(!getCorporacionVector().isEmpty()){
					getCorporacionVector().removeAllElements();
				}
				
				while (corporacionIterator.hasNext()) {
					CorporacionIf corporacionIf = (CorporacionIf) corporacionIterator.next();
					
					tableModel = (DefaultTableModel) getTblCorporacion().getModel();
					Vector<String> fila = new Vector<String>();
					getCorporacionVector().add(corporacionIf);
					
					agregarColumnasTabla(corporacionIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblCorporacion(), corporacion, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strRepresentante = this.getTxtRepresentante().getText();
		String strEstado = (String) this.getCmbEstado().getSelectedItem();

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para la Corporación !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Corporación !!",
					SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}

		if ((("".equals(strRepresentante)) || (strRepresentante == null))) {
			SpiritAlert.createAlert("Debe ingresar un representante para la Corporación !!",
					SpiritAlert.WARNING);
			getTxtRepresentante().grabFocus();
			return false;
		}

		if ((("".equals(strEstado)) || (strEstado == null))) {
			SpiritAlert.createAlert("Debe ingresar un estado para la Corporación !!",
					SpiritAlert.WARNING);
			getTxtRepresentante().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtRepresentante().setText("");
		this.getTxtFechaCreacion().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblCorporacion().getModel();
		
		for(int i= this.getTblCorporacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {

		setSaveMode();
		
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtRepresentante().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTxtFechaCreacion().setEditable(false);
		getTxtFechaCreacion().setText(Utilitarios.fechaAhora());
		
		getCmbEstado().setEnabled(true);
		if (getCmbEstado().getItemCount()>0)
			getCmbEstado().removeAllItems();
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
		
		clean();
		getTxtFechaCreacion().setText(Utilitarios.fechaAhora());
		cargarTabla();
		
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtRepresentante().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTblCorporacion().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getCorporacionVector() {
		return this.corporacionVector;
	}
	
	public void setCorporacionVector(Vector corporacionVector) {
		this.corporacionVector = corporacionVector;
	}
	
	public int getCorporacionSeleccionada() {
		return this.corporacionSeleccionada;
	}
	
	public void setCorporacionSeleccionada(int corporacionSeleccionada) {
		this.corporacionSeleccionada = corporacionSeleccionada;
	}
	
	public CorporacionIf getCorporacionActualizadaIf() {
		return this.corporacionActualizadaIf;
	}
	
	public void setCorporacionActualizadaIf(CorporacionIf corporacionActualizadaIf) {
		this.corporacionActualizadaIf = corporacionActualizadaIf;
	}

	 
}
