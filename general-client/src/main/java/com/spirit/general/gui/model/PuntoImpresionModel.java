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
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.PuntoImpresionData;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.panel.JPPuntoImpresion;
import com.spirit.general.session.CajaSessionService;
import com.spirit.general.session.PuntoImpresionSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PuntoImpresionModel extends JPPuntoImpresion{
	
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = "I";

	private static final int MAX_LONGITUD_IMPRESION = 100;
	private static final int MAX_LONGITUD_SERIE = 3;
	
	private Vector puntoImpresionVector = new Vector();
	private DefaultTableModel tableModel;
	protected PuntoImpresionIf puntoImpresionIf;
	private int puntoImpresionSeleccionado;
	
	
	public PuntoImpresionModel(){
		anchoColumnasTabla();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		getTblPuntoImpresion().addMouseListener(oMouseAdapterTblPuntoImpresion);
		getTblPuntoImpresion().addKeyListener(oKeyAdapterTblPuntoImpresion);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		getTblPuntoImpresion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		TableColumn anchoColumna = getTblPuntoImpresion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblPuntoImpresion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPuntoImpresion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblPuntoImpresion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblPuntoImpresion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);		
	}
	
	private void initKeyListeners() {
		getTxtImpresora().addKeyListener(new TextChecker(MAX_LONGITUD_IMPRESION));
		getTxtSerie().addKeyListener(new TextChecker(MAX_LONGITUD_SERIE));
	}
	
	public void cargarCombos(){
		cargarComboTipoDocumento();
		cargarComboCaja();		
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
	}
	
	private void cargarComboTipoDocumento() {
		try {
			List tiposDocumento = (List) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoDocumento(), tiposDocumento);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboCaja() {
		try {
			List cajas = (List) SessionServiceLocator.getCajaSessionService().findCajaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbCaja(), cajas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblPuntoImpresion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblPuntoImpresion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPuntoImpresionSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			puntoImpresionIf = (PuntoImpresionIf)  getPuntoImpresionVector().get(getPuntoImpresionSeleccionado());
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), puntoImpresionIf.getTipodocumentoId()));
			getCmbTipoDocumento().repaint();
			getCmbCaja().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCaja(), puntoImpresionIf.getCajaId()));
			getCmbCaja().repaint();
			getTxtImpresora().setText(puntoImpresionIf.getImpresora());
			getTxtSerie().setText(puntoImpresionIf.getSerie());
			if(puntoImpresionIf.getEstado().equals(ESTADO_ACTIVO)){
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			}else if(puntoImpresionIf.getEstado().equals(ESTADO_INACTIVO)){
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			}
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				PuntoImpresionData data = new PuntoImpresionData();
				data.setTipodocumentoId(((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
				data.setCajaId(((CajaIf) getCmbCaja().getSelectedItem()).getId());
				data.setImpresora(getTxtImpresora().getText());
				data.setSerie(getTxtSerie().getText());
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
					data.setEstado(ESTADO_ACTIVO);
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO)){
					data.setEstado(ESTADO_INACTIVO);
				}
				SessionServiceLocator.getPuntoImpresionSessionService().addPuntoImpresion(data);
				SpiritAlert.createAlert("Punto de Impresin guardado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			PuntoImpresionIf puntoImpresionIf = (PuntoImpresionIf) getPuntoImpresionVector().get(getPuntoImpresionSeleccionado());
			SessionServiceLocator.getPuntoImpresionSessionService().deletePuntoImpresion(puntoImpresionIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Punto de Impresin eliminado con éxito", SpiritAlert.INFORMATION);
			} 
		catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				puntoImpresionIf.setTipodocumentoId(((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
				puntoImpresionIf.setCajaId(((CajaIf) getCmbCaja().getSelectedItem()).getId());
				puntoImpresionIf.setImpresora(getTxtImpresora().getText());
				puntoImpresionIf.setSerie(getTxtSerie().getText());
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
					puntoImpresionIf.setEstado(ESTADO_ACTIVO);
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO)){
					puntoImpresionIf.setEstado(ESTADO_INACTIVO);
				}
				SessionServiceLocator.getPuntoImpresionSessionService().savePuntoImpresion(puntoImpresionIf);
				getPuntoImpresionVector().setElementAt(puntoImpresionIf, getPuntoImpresionSeleccionado());
				setPuntoImpresionIf(null);
				SpiritAlert.createAlert("Punto de Impresin actualizado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtImpresora().setText("");
		getTxtSerie().setText(""); 
		
		DefaultTableModel model = (DefaultTableModel) getTblPuntoImpresion().getModel();
		for(int i= this.getTblPuntoImpresion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getCmbTipoDocumento().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection puntosImpresion = SessionServiceLocator.getPuntoImpresionSessionService().findPuntoImpresionByEmpresaId(Parametros.getIdEmpresa());
			Iterator puntosImpresionIterator = puntosImpresion.iterator();
			
			if(!getPuntoImpresionVector().isEmpty()){
				getPuntoImpresionVector().removeAllElements();
			}
			
			while (puntosImpresionIterator.hasNext()) {
				PuntoImpresionIf puntoImpresionIf = (PuntoImpresionIf) puntosImpresionIterator.next();
				
				tableModel = (DefaultTableModel) getTblPuntoImpresion().getModel();
				Vector<String> fila = new Vector<String>();
				getPuntoImpresionVector().add(puntoImpresionIf);
				
				agregarColumnasTabla(puntoImpresionIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblPuntoImpresion(), puntosImpresion, 0);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(PuntoImpresionIf puntoImpresionIf, Vector<String> fila){
		try {
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(puntoImpresionIf.getTipodocumentoId());
			fila.add(tipoDocumento.getNombre());
			
			CajaIf caja = SessionServiceLocator.getCajaSessionService().getCaja(puntoImpresionIf.getCajaId());
			fila.add(caja.getNombre());
			
			fila.add(puntoImpresionIf.getImpresora());
			fila.add(puntoImpresionIf.getSerie());
			
			if(puntoImpresionIf.getEstado().equals(ESTADO_ACTIVO)){
				fila.add(NOMBRE_ESTADO_ACTIVO);
			}else if(puntoImpresionIf.getEstado().equals(ESTADO_INACTIVO)){
				fila.add(NOMBRE_ESTADO_INACTIVO);
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if (getCmbTipoDocumento().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Documento !", SpiritAlert.WARNING);
			getCmbTipoDocumento().grabFocus();
			return false;
		}
		if (getCmbCaja().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una Caja !", SpiritAlert.WARNING);
			getCmbCaja().grabFocus();
			return false;
		}
		if ((("".equals(getTxtImpresora().getText())) || (getTxtImpresora().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Impresora !", SpiritAlert.WARNING);
			getTxtImpresora().grabFocus();
			return false;
		}		
		if ((("".equals(getTxtSerie().getText())) || (getTxtSerie().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Serie !", SpiritAlert.WARNING);
			getTxtSerie().grabFocus();
			return false;
		}		
		if (getCmbEstado().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Estado !", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		return true;
	}
	
	public void refresh() {
		cargarComboTipoDocumento();
		cargarComboCaja();
	}
	 
	public PuntoImpresionIf getPuntoImpresionIf() {
		return puntoImpresionIf;
	}

	public void setPuntoImpresionIf(PuntoImpresionIf puntoImpresionIf) {
		this.puntoImpresionIf = puntoImpresionIf;
	}

	public int getPuntoImpresionSeleccionado() {
		return puntoImpresionSeleccionado;
	}

	public void setPuntoImpresionSeleccionado(int puntoImpresionSeleccionado) {
		this.puntoImpresionSeleccionado = puntoImpresionSeleccionado;
	}

	public Vector getPuntoImpresionVector() {
		return puntoImpresionVector;
	}

	public void setPuntoImpresionVector(Vector puntoImpresionVector) {
		this.puntoImpresionVector = puntoImpresionVector;
	}
}
