package com.spirit.general.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.TipoDocumentoData;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoDocumento;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoDocumentoModel extends JPTipoDocumento {

	private static final String NOMBRE_TIPO_CARTERA_PROVEEDOR = "PROVEEDOR";
	private static final String NOMBRE_TIPO_CARTERA_CLIENTE = "CLIENTE";
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final long serialVersionUID = -6475454097637527027L;

	protected JDPopupFinderModel popupFinder;
	ArrayList lista;
	
    private static final int MAX_LONGITUD_CODIGO = 3; 
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final int MAX_LONGITUD_MASCARA = 20;
	private static final int MAX_LONGITUD_LINEAS = 3;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String OPCION_SI = "SI";
	private static final String OPCION_NO = "NO";
	private static final String NOMBRE_SIGNO_NEGATIVO = "NEGATIVO";
	private static final String NOMBRE_SIGNO_POSITIVO = "POSITIVO";
	private static final String SIGNO_NEGATIVO = "-";
	private static final String SIGNO_POSITIVO = "+";
	private static final String TIPO_COMPROBANTE_NO_APLICA = "NO APLICA";
	
	TipoDocumentoIf tipoDocumentoActualizadoIf;
	private Vector tipoDocumentosVector = new Vector();
	private DefaultTableModel tableModel;
	private int tipoDocumentoSeleccionado;
	
	
	public TipoDocumentoModel(){
		initKeyListeners();
		setSorterTable(getTblTipoDocumento());
		anchoColumnasTabla();
		getTblTipoDocumento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarCombos();
		showSaveMode();
		getTblTipoDocumento().addMouseListener(oMouseAdapterTblTipoDocumento);
		getTblTipoDocumento().addKeyListener(oKeyAdapterTblTipoDocumento);
		
		new HotKeyComponent(this);
	}
	
	public TipoDocumentoModel(boolean isPopup){
		initKeyListeners();
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoDocumento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTipoDocumento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblTipoDocumento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblTipoDocumento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblTipoDocumento().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtMascara().addKeyListener(new TextChecker(MAX_LONGITUD_MASCARA));
		getTxtNumeroLineas().addKeyListener(new TextChecker(MAX_LONGITUD_LINEAS));
		getTxtNumeroLineas().addKeyListener (new NumberTextField());
	}
	
	MouseListener oMouseAdapterTblTipoDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoDocumento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			setTipoDocumentoActualizadoIf((TipoDocumentoIf)  getTipoDocumentosVector().get(getTipoDocumentoSeleccionado()));
			
			getTxtCodigo().setText(tipoDocumentoActualizadoIf.getCodigo());
			getTxtNombre().setText(tipoDocumentoActualizadoIf.getNombre());
			getTxtMascara().setText(tipoDocumentoActualizadoIf.getMascara());
			if(tipoDocumentoActualizadoIf.getNumerolineas()!=null)
				getTxtNumeroLineas().setText(tipoDocumentoActualizadoIf.getNumerolineas().toString());
			
			getCmbModulo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModulo(), tipoDocumentoActualizadoIf.getModuloId()));
			getCmbModulo().repaint();
			if(tipoDocumentoActualizadoIf.getIdSriTipoComprobante() != null){
				getCmbTipoComprobante().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoComprobante(), tipoDocumentoActualizadoIf.getIdSriTipoComprobante()));
				getCmbTipoComprobante().repaint();
			}else{
				getCmbTipoComprobante().setSelectedItem(TIPO_COMPROBANTE_NO_APLICA);
				getCmbTipoComprobante().repaint();
			}
			
			if (tipoDocumentoActualizadoIf.getTipoTroqueladoId() != null) {
				getCmbTipoTroquelado().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoTroquelado(), tipoDocumentoActualizadoIf.getTipoTroqueladoId()));
				getCmbTipoTroquelado().repaint();
			} else {
				getCmbTipoTroquelado().setSelectedItem(null);
				getCmbTipoTroquelado().repaint();
			}
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getAfectacartera()))
				getCmbAfectaCartera().setSelectedItem(OPCION_SI);
			else
				getCmbAfectaCartera().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getAfectastock()))
				getCmbAfectaStock().setSelectedItem(OPCION_SI);
			else
				getCmbAfectaStock().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getAfectaventa()))
				getCmbAfectaVenta().setSelectedItem(OPCION_SI);
			else
				getCmbAfectaVenta().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getExigemotivo()))
				getCmbExigeMotivo().setSelectedItem(OPCION_SI);
			else
				getCmbExigeMotivo().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getFormapago()))
				getCmbFormaPago().setSelectedItem(OPCION_SI);
			else
				getCmbFormaPago().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getCliente()))
				getCmbCliente().setSelectedItem(OPCION_SI);
			else
				getCmbCliente().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getCaja()))
				getCmbCaja().setSelectedItem(OPCION_SI);
			else
				getCmbCaja().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getPermiteeliminacion()))
				getCmbPermiteEliminacion().setSelectedItem(OPCION_SI);
			else
				getCmbPermiteEliminacion().setSelectedItem(OPCION_NO);
			
			if((ESTADO_ACTIVO).equals(tipoDocumentoActualizadoIf.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			getCmbEstado().repaint();
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getReembolso()))
				getCmbReembolso().setSelectedItem(OPCION_SI);
			else
				getCmbReembolso().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getDescuentoespecial()))
				getCmbDescuentoEspecial().setSelectedItem(OPCION_SI);
			else
				getCmbDescuentoEspecial().setSelectedItem(OPCION_NO);
			
			if((NOMBRE_TIPO_CARTERA_CLIENTE.substring(0, 1)).equals(tipoDocumentoActualizadoIf.getTipocartera()))
				getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_CLIENTE);
			else
				getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_PROVEEDOR);
			
			if(tipoDocumentoActualizadoIf.getSignocartera().equals(SIGNO_NEGATIVO))
				getCmbSignoCartera().setSelectedItem(NOMBRE_SIGNO_NEGATIVO);
			else
				getCmbSignoCartera().setSelectedItem(NOMBRE_SIGNO_POSITIVO);
			
			if(tipoDocumentoActualizadoIf.getSignostock().equals(SIGNO_NEGATIVO))
				getCmbSignoStock().setSelectedItem(NOMBRE_SIGNO_NEGATIVO);
			else
				getCmbSignoStock().setSelectedItem(NOMBRE_SIGNO_POSITIVO);
			
			if(tipoDocumentoActualizadoIf.getSignoventa().equals(SIGNO_NEGATIVO))
				getCmbSignoVenta().setSelectedItem(NOMBRE_SIGNO_NEGATIVO);
			else
				getCmbSignoVenta().setSelectedItem(NOMBRE_SIGNO_POSITIVO);
			
			showUpdateMode();
		}
	}

	public void save() {
		if (validateFields()) {
			TipoDocumentoData data = new TipoDocumentoData();
			
			data.setEmpresaId(Parametros.getIdEmpresa());
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setModuloId(((ModuloIf) this.getCmbModulo().getSelectedItem()).getId());
			data.setMascara(this.getTxtMascara().getText());
			data.setNumerolineas(Double.valueOf(this.getTxtNumeroLineas().getText()).intValue());
			data.setAfectacartera(this.getCmbAfectaCartera().getSelectedItem().toString().substring(0, 1));
			data.setAfectastock(this.getCmbAfectaStock().getSelectedItem().toString().substring(0, 1));
			data.setAfectaventa(this.getCmbAfectaVenta().getSelectedItem().toString().substring(0, 1));
			data.setExigemotivo(this.getCmbExigeMotivo().getSelectedItem().toString().substring(0, 1));
			data.setFormapago(this.getCmbFormaPago().getSelectedItem().toString().substring(0, 1));
			data.setCliente(this.getCmbCliente().getSelectedItem().toString().substring(0, 1));
			data.setCaja(this.getCmbCaja().getSelectedItem().toString().substring(0, 1));
			data.setPermiteeliminacion(this.getCmbPermiteEliminacion().getSelectedItem().toString().substring(0, 1));
			data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			data.setReembolso(this.getCmbReembolso().getSelectedItem().toString().substring(0,1));
			data.setDescuentoespecial(this.getCmbDescuentoEspecial().getSelectedItem().toString().substring(0,1));
			data.setTipocartera(this.getCmbTipoCartera().getSelectedItem().toString().substring(0,1));
			if(!getCmbTipoComprobante().getSelectedItem().equals(TIPO_COMPROBANTE_NO_APLICA)){
				SriTipoComprobanteIf tipoComprobanteIf = (SriTipoComprobanteIf)getCmbTipoComprobante().getSelectedItem();
				data.setIdSriTipoComprobante(tipoComprobanteIf.getId());
			}	
			
			if(getCmbTipoTroquelado().getSelectedItem() != null){
				TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf)getCmbTipoTroquelado().getSelectedItem();
				data.setTipoTroqueladoId(tipoTroquelado.getId());
			}
			
			if(getCmbSignoCartera().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				data.setSignocartera(SIGNO_NEGATIVO);
			else
				data.setSignocartera(SIGNO_POSITIVO);
			
			if(getCmbSignoStock().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				data.setSignostock(SIGNO_NEGATIVO);
			else
				data.setSignostock(SIGNO_POSITIVO);
			
			if(getCmbSignoVenta().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				data.setSignoventa(SIGNO_NEGATIVO);
			else
				data.setSignoventa(SIGNO_POSITIVO);
			
			try {
				SessionServiceLocator.getTipoDocumentoSessionService().addTipoDocumento(data);
				SpiritAlert.createAlert("Tipo de Documento guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoDocumento",null);
				clean();
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);	
			}
		} 
	}

	public void update() {
		if (validateFields()) {
		
			tipoDocumentoActualizadoIf.setNombre(this.getTxtNombre().getText());
			tipoDocumentoActualizadoIf.setModuloId(((ModuloIf) this.getCmbModulo().getSelectedItem()).getId());
			tipoDocumentoActualizadoIf.setMascara(this.getTxtMascara().getText());
			tipoDocumentoActualizadoIf.setNumerolineas(Double.valueOf(this.getTxtNumeroLineas().getText()).intValue());
			tipoDocumentoActualizadoIf.setAfectacartera(this.getCmbAfectaCartera().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setAfectastock(this.getCmbAfectaStock().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setAfectaventa(this.getCmbAfectaVenta().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setExigemotivo(this.getCmbExigeMotivo().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setFormapago(this.getCmbFormaPago().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setCliente(this.getCmbCliente().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setCaja(this.getCmbCaja().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setPermiteeliminacion(this.getCmbPermiteEliminacion().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			tipoDocumentoActualizadoIf.setReembolso(this.getCmbReembolso().getSelectedItem().toString().substring(0,1));
			tipoDocumentoActualizadoIf.setDescuentoespecial(this.getCmbDescuentoEspecial().getSelectedItem().toString().substring(0,1));
			tipoDocumentoActualizadoIf.setTipocartera(this.getCmbTipoCartera().getSelectedItem().toString().substring(0,1));
			if(!getCmbTipoComprobante().getSelectedItem().equals(TIPO_COMPROBANTE_NO_APLICA)){
				SriTipoComprobanteIf tipoComprobanteIf = (SriTipoComprobanteIf)getCmbTipoComprobante().getSelectedItem();
				tipoDocumentoActualizadoIf.setIdSriTipoComprobante(tipoComprobanteIf.getId());
			}
			if(getCmbTipoTroquelado().getSelectedItem() != null){
				TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf)getCmbTipoTroquelado().getSelectedItem();
				tipoDocumentoActualizadoIf.setTipoTroqueladoId(tipoTroquelado.getId());
			}
			
			if(getCmbSignoCartera().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				tipoDocumentoActualizadoIf.setSignocartera(SIGNO_NEGATIVO);
			else
				tipoDocumentoActualizadoIf.setSignocartera(SIGNO_POSITIVO);
			
			if(getCmbSignoStock().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				tipoDocumentoActualizadoIf.setSignostock(SIGNO_NEGATIVO);
			else
				tipoDocumentoActualizadoIf.setSignostock(SIGNO_POSITIVO);
			
			if(getCmbSignoVenta().getSelectedItem().equals(NOMBRE_SIGNO_NEGATIVO))
				tipoDocumentoActualizadoIf.setSignoventa(SIGNO_NEGATIVO);
			else
				tipoDocumentoActualizadoIf.setSignoventa(SIGNO_POSITIVO);
						
			try {
				SessionServiceLocator.getTipoDocumentoSessionService().saveTipoDocumento(tipoDocumentoActualizadoIf);
				SpiritAlert.createAlert("Tipo de Documento actualizado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoDocumento",null);
				clean();
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
			}
		}
		
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getTipoDocumentoSessionService().deleteTipoDocumento(tipoDocumentoActualizadoIf.getId());
			SpiritAlert.createAlert("Tipo de Documento eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoDocumento",null);
			clean();
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("El Tipo de Documento tiene datos referenciados y no puede ser eliminado!", SpiritAlert.ERROR);
			clean();
			showSaveMode();
		}
	}

	public boolean validateFields() {	
		try {
			String strCodigo = this.getTxtCodigo().getText();
			String strNombre = this.getTxtNombre().getText();
			String strNumeroLineas = this.getTxtNumeroLineas().getText();
			boolean codigoRepetido = false;
			boolean tipoComprobanteRepetido = false;
			
			Collection tipoDocumentos = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoDocumentoIt = tipoDocumentos.iterator();
			//Collection tipoDocumentos2 = tipoDocumentos;
			//Iterator tipoDocumentoIt2 = tipoDocumentos2.iterator();
						
			while (tipoDocumentoIt.hasNext()) {
				TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf) tipoDocumentoIt.next();
				
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(tipoDocumentoIf.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(tipoDocumentoIf.getCodigo())) 
						if (tipoDocumentoActualizadoIf.getId().equals(tipoDocumentoIf.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código del Tipo de Documento está duplicado !!",
						SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}	
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Documento !!",
						SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}

			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Documento !!",
						SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
			
			if(getCmbModulo().getSelectedIndex() == -1){
				SpiritAlert.createAlert("Debe seleccionar un módulo para el Tipo de Documento !!", SpiritAlert.WARNING);
				getCmbModulo().grabFocus();
				return false;
			}
			
			if ((("".equals(strNumeroLineas)) || (strNumeroLineas == null))) {
				SpiritAlert.createAlert("Debe ingresar un número de líneas para el Tipo de Documento !!",
						SpiritAlert.WARNING);
				getTxtNumeroLineas().grabFocus();
				return false;
			}
			
			if (getCmbTipoComprobante().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar un Tipo de Comprobante para el Tipo de Documento !!", SpiritAlert.WARNING);
				getCmbTipoComprobante().grabFocus();
				return false;
			}
						
			/*if(!getCmbTipoComprobante().getSelectedItem().equals(TIPO_COMPROBANTE_NO_APLICA)){
				SriTipoComprobanteIf tipoComprobante = (SriTipoComprobanteIf)getCmbTipoComprobante().getSelectedItem();
				
				while (tipoDocumentoIt2.hasNext()) {
					TipoDocumentoIf tipoDocumentoIf2 = (TipoDocumentoIf) tipoDocumentoIt2.next();
					if(tipoDocumentoIf2.getIdSriTipoComprobante().compareTo(tipoComprobante.getId()) == 0){
						SpiritAlert.createAlert("El Tipo de Comprobante seleccionado ya está relacionado con otro tipo de Documento!", SpiritAlert.WARNING);
						getCmbTipoComprobante().grabFocus();
						return false;
					}
				}		
			}*/
			
			/*if(!getCmbTipoComprobante().getSelectedItem().equals(TIPO_COMPROBANTE_NO_APLICA)){
				tipoDocumentoIt = tipoDocumentos.iterator();
				while (tipoDocumentoIt.hasNext()) {
					TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf) tipoDocumentoIt.next();
					SriTipoComprobanteIf tipoComprobante = (SriTipoComprobanteIf)getCmbTipoComprobante().getSelectedItem();
					if(tipoDocumentoIf.getIdSriTipoComprobante() != null){
						if (this.getMode() == SpiritMode.SAVE_MODE)
							if (tipoComprobante.getId().compareTo(tipoDocumentoIf.getIdSriTipoComprobante()) == 0)				
								tipoComprobanteRepetido = true;
						
						if (this.getMode() == SpiritMode.UPDATE_MODE)
							if (tipoComprobante.getId().compareTo(tipoDocumentoIf.getIdSriTipoComprobante()) == 0) 
								if (tipoDocumentoActualizadoIf.getId().equals(tipoDocumentoIf.getId()) == false)
									tipoComprobanteRepetido = true;
					}					
				}
			}
			
			if (tipoComprobanteRepetido) {
				SpiritAlert.createAlert("El Tipo de Comprobante seleccionado ya está relacionado con otro tipo de Documento!", SpiritAlert.WARNING);
				getCmbTipoComprobante().grabFocus();
				return false;
			}*/	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtMascara().setText("");
		this.getTxtNumeroLineas().setText("");
		
		getCmbModulo().setSelectedIndex(0);
		getCmbAfectaCartera().setSelectedIndex(0);
		getCmbAfectaStock().setSelectedIndex(0);
		getCmbAfectaVenta().setSelectedIndex(0);
		getCmbExigeMotivo().setSelectedIndex(0);
		getCmbFormaPago().setSelectedIndex(0);
		getCmbCliente().setSelectedIndex(0);
		getCmbCaja().setSelectedIndex(0);
		getCmbPermiteEliminacion().setSelectedIndex(0);
		getCmbEstado().setSelectedIndex(0);
		getCmbReembolso().setSelectedIndex(0);
		getCmbDescuentoEspecial().setSelectedIndex(0);
		getCmbTipoCartera().setSelectedIndex(0);
		getCmbSignoCartera().setSelectedIndex(0);
		getCmbSignoStock().setSelectedIndex(0);
		getCmbSignoVenta().setSelectedIndex(0);
		getCmbTipoTroquelado().setSelectedItem(null);
					
		DefaultTableModel model = (DefaultTableModel) getTblTipoDocumento().getModel();
		for(int i= this.getTblTipoDocumento().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void cargarCombos(){
		cargarComboModulos();
		cargarComboTiposComprobante();
		cargarComboTipoTroquelado();
		
		getCmbSignoCartera().addItem(NOMBRE_SIGNO_POSITIVO);
		getCmbSignoCartera().addItem(NOMBRE_SIGNO_NEGATIVO);
		getCmbSignoStock().addItem(NOMBRE_SIGNO_POSITIVO);
		getCmbSignoStock().addItem(NOMBRE_SIGNO_NEGATIVO);
		getCmbSignoVenta().addItem(NOMBRE_SIGNO_POSITIVO);
		getCmbSignoVenta().addItem(NOMBRE_SIGNO_NEGATIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
		getCmbAfectaCartera().addItem(OPCION_SI);
		getCmbAfectaCartera().addItem(OPCION_NO);
		getCmbAfectaStock().addItem(OPCION_SI);
		getCmbAfectaStock().addItem(OPCION_NO);
		getCmbAfectaVenta().addItem(OPCION_SI);
		getCmbAfectaVenta().addItem(OPCION_NO);
		getCmbExigeMotivo().addItem(OPCION_SI);
		getCmbExigeMotivo().addItem(OPCION_NO);
		getCmbFormaPago().addItem(OPCION_SI);
		getCmbFormaPago().addItem(OPCION_NO);
		getCmbCliente().addItem(OPCION_SI);
		getCmbCliente().addItem(OPCION_NO);
		getCmbCaja().addItem(OPCION_SI);
		getCmbCaja().addItem(OPCION_NO);
		getCmbPermiteEliminacion().addItem(OPCION_SI);
		getCmbPermiteEliminacion().addItem(OPCION_NO);
		getCmbReembolso().addItem(OPCION_SI);
		getCmbReembolso().addItem(OPCION_NO);
		getCmbDescuentoEspecial().addItem(OPCION_SI);
		getCmbDescuentoEspecial().addItem(OPCION_NO);
		getCmbTipoCartera().addItem(NOMBRE_TIPO_CARTERA_CLIENTE);
		getCmbTipoCartera().addItem(NOMBRE_TIPO_CARTERA_PROVEEDOR);
	}
	
	private void cargarComboModulos(){
		try {
			List modulos = (List) SessionServiceLocator.getModuloSessionService().getModuloList();
			refreshCombo(getCmbModulo(),modulos);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboTiposComprobante(){
		try {
			Collection tiposComprobante = SessionServiceLocator.getSriTipoComprobanteSessionService().getSriTipoComprobanteList();
			tiposComprobante.add(TIPO_COMPROBANTE_NO_APLICA);
			List nombresComprobantes = (List)tiposComprobante;
			refreshCombo(getCmbTipoComprobante(),nombresComprobantes);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboTipoTroquelado(){
		try {
			List tiposTroquelado = (ArrayList) SessionServiceLocator.getTipoTroqueladoSessionService().getTipoTroqueladoList();
			refreshCombo(getCmbTipoTroquelado(), tiposTroquelado);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEnabled(true);
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection tipoDocumentos = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoDocumentosIterator = tipoDocumentos.iterator();
			
			if(!getTipoDocumentosVector().isEmpty()){
				getTipoDocumentosVector().removeAllElements();
			}
			
			while (tipoDocumentosIterator.hasNext()) {
				TipoDocumentoIf tipoDocumentosIf = (TipoDocumentoIf) tipoDocumentosIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoDocumento().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoDocumentosVector().add(tipoDocumentosIf);
				
				agregarFilasTabla(tipoDocumentosIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoDocumento(), tipoDocumentos, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarFilasTabla(TipoDocumentoIf tipoDocumentosIf, Vector<String> fila){
		fila.add(tipoDocumentosIf.getCodigo());
		fila.add(tipoDocumentosIf.getNombre());
		
		try {
			ModuloIf modulo = SessionServiceLocator.getModuloSessionService().getModulo(tipoDocumentosIf.getModuloId());
			fila.add(modulo.getCodigo() + " - " + modulo.getNombre());
			
			if (tipoDocumentosIf.getTipocartera() != null) {
				if(tipoDocumentosIf.getTipocartera().equals(TIPO_CARTERA_CLIENTE))
					fila.add(NOMBRE_TIPO_CARTERA_CLIENTE);
				else if(tipoDocumentosIf.getTipocartera().equals(TIPO_CARTERA_PROVEEDOR))
					fila.add(NOMBRE_TIPO_CARTERA_PROVEEDOR);
			} else {
				fila.add("");
			}
						
			if(tipoDocumentosIf.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(tipoDocumentosIf.getEstado().equals(ESTADO_INACTIVO))
				fila.add(NOMBRE_ESTADO_INACTIVO);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
	}

	public void refresh() {
		cargarComboModulos();
		cargarComboTiposComprobante();
		cargarComboTipoTroquelado();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public TipoDocumentoIf getTipoDocumentoActualizadoIf() {
		return tipoDocumentoActualizadoIf;
	}

	public void setTipoDocumentoActualizadoIf(
			TipoDocumentoIf tipoDocumentoActualizadoIf) {
		this.tipoDocumentoActualizadoIf = tipoDocumentoActualizadoIf;
	}

	public int getTipoDocumentoSeleccionado() {
		return tipoDocumentoSeleccionado;
	}

	public void setTipoDocumentoSeleccionado(int tipoDocumentoSeleccionado) {
		this.tipoDocumentoSeleccionado = tipoDocumentoSeleccionado;
	}

	public Vector getTipoDocumentosVector() {
		return tipoDocumentosVector;
	}

	public void setTipoDocumentosVector(Vector tipoDocumentosVector) {
		this.tipoDocumentosVector = tipoDocumentosVector;
	}
}
