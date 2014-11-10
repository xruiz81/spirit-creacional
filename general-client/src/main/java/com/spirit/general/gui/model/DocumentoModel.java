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
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoData;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPDocumento;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;

public class DocumentoModel extends JPDocumento {

	private static final long serialVersionUID = -6760073973127874982L;
	JDPopupFinderModel popupFinder;
  	private static final int MAX_LONGITUD_CODIGO = 4; 
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private static final int MAX_LONGITUD_ABREVIADO = 50;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String OPCION_SI = "SI";
	private static final String OPCION_NO = "NO";
	private DocumentoIf documentoActualizadoIf;
	
	private Vector documentosVector = new Vector();
	private DefaultTableModel tableModel;
	private int documentoSeleccionado;
	
	
	public DocumentoModel(){
		anchoColumnasTabla();
		getTblDocumento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initKeyListeners();
		setSorterTable(getTblDocumento());
		cargarCombos();
		showSaveMode();
		getTblDocumento().addMouseListener(oMouseAdapterTblDocumento);
		getTblDocumento().addKeyListener(oKeyAdapterTblDocumento);
		
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDocumento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblDocumento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblDocumento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblDocumento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtAbreviado().addKeyListener(new TextChecker(MAX_LONGITUD_ABREVIADO));
	}
	
	MouseListener oMouseAdapterTblDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblDocumento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			setDocumentoActualizadoIf((DocumentoIf)  getDocumentosVector().get(getDocumentoSeleccionado()));
			
			getTxtCodigo().setText(documentoActualizadoIf.getCodigo());
			getTxtNombre().setText(documentoActualizadoIf.getNombre());
			getTxtAbreviado().setText(documentoActualizadoIf.getAbreviado());
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getPideAutorizacion()))
				getCmbPideAutorizacion().setSelectedItem(OPCION_SI);
			else
				getCmbPideAutorizacion().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getBonificacion()))
				getCmbBonificacion().setSelectedItem(OPCION_SI);
			else
				getCmbBonificacion().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getDiferido()))
				getCmbDiferido().setSelectedItem(OPCION_SI);
			else
				getCmbDiferido().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getPrecioEspecial()))
				getCmbPrecioEspecial().setSelectedItem(OPCION_SI);
			else
				getCmbPrecioEspecial().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getDescuentoEspecial()))
				getCmbDescuentoEspecial().setSelectedItem(OPCION_SI);
			else
				getCmbDescuentoEspecial().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getMulta()))
				getCmbMulta().setSelectedItem(OPCION_SI);
			else
				getCmbMulta().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getInteres()))
				getCmbInteres().setSelectedItem(OPCION_SI);
			else
				getCmbInteres().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getProtesto()))
				getCmbProtesto().setSelectedItem(OPCION_SI);
			else
				getCmbProtesto().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getCheque()))
				getCmbCheque().setSelectedItem(OPCION_SI);
			else
				getCmbCheque().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getRetencionRenta())) {
				getCmbRetencionRenta().setSelectedItem(OPCION_SI);
				getCmbRetencionIva().setSelectedItem(OPCION_NO);
			} if((OPCION_SI.substring(0, 1)).equals(documentoActualizadoIf.getRetencionIva())) {
				getCmbRetencionIva().setSelectedItem(OPCION_SI);
				getCmbRetencionRenta().setSelectedItem(OPCION_NO);
			} else {
				getCmbRetencionRenta().setSelectedItem(OPCION_NO);
				getCmbRetencionIva().setSelectedItem(OPCION_NO);
			}
			
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), documentoActualizadoIf.getTipoDocumentoId()));
			getCmbTipoDocumento().repaint();
			
			if (ESTADO_ACTIVO.equals(documentoActualizadoIf.getEstado().toString()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}

	public void save() {
		if (validateFields()) {
			DocumentoData data = new DocumentoData();
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setAbreviado(this.getTxtAbreviado().getText());
			data.setTipoDocumentoId(((TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem()).getId());
			data.setPideAutorizacion(this.getCmbPideAutorizacion().getSelectedItem().toString().substring(0, 1));
			data.setBonificacion(this.getCmbBonificacion().getSelectedItem().toString().substring(0, 1));
			data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			data.setDiferido(this.getCmbDiferido().getSelectedItem().toString().substring(0,1));
			data.setPrecioEspecial(this.getCmbPrecioEspecial().getSelectedItem().toString().substring(0,1));
			data.setDescuentoEspecial(this.getCmbDescuentoEspecial().getSelectedItem().toString().substring(0,1));
			data.setMulta(this.getCmbMulta().getSelectedItem().toString().substring(0,1));
			data.setInteres(this.getCmbInteres().getSelectedItem().toString().substring(0,1));
			data.setProtesto(this.getCmbProtesto().getSelectedItem().toString().substring(0,1));
			data.setCheque(this.getCmbCheque().getSelectedItem().toString().substring(0,1));
			data.setRetencionRenta(this.getCmbRetencionRenta().getSelectedItem().toString().substring(0,1));
			data.setRetencionIva(this.getCmbRetencionIva().getSelectedItem().toString().substring(0,1));
			
			try {
				SessionServiceLocator.getDocumentoSessionService().addDocumento(data);
				SpiritAlert.createAlert("Documento guardado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("documento",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",SpiritAlert.ERROR);
			}
		}	
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getDocumentoSessionService().deleteDocumento(documentoActualizadoIf.getId());
			SpiritAlert.createAlert("Documento eliminado con éxito!",SpiritAlert.INFORMATION);
			SpiritCache.setObject("documento",null);
			showSaveMode();
		} catch (Exception e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
		
	}

	public void update() {
		
		if (validateFields()) {
			
			documentoActualizadoIf.setNombre(this.getTxtNombre().getText());
			documentoActualizadoIf.setAbreviado(this.getTxtAbreviado().getText());
			documentoActualizadoIf.setTipoDocumentoId(((TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem()).getId());
			documentoActualizadoIf.setPideAutorizacion(this.getCmbPideAutorizacion().getSelectedItem().toString().substring(0, 1));
			documentoActualizadoIf.setBonificacion(this.getCmbBonificacion().getSelectedItem().toString().substring(0, 1));
			documentoActualizadoIf.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			documentoActualizadoIf.setDiferido(this.getCmbDiferido().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setPrecioEspecial(this.getCmbPrecioEspecial().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setDescuentoEspecial(this.getCmbDescuentoEspecial().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setMulta(this.getCmbMulta().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setInteres(this.getCmbInteres().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setProtesto(this.getCmbProtesto().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setCheque(this.getCmbCheque().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setRetencionRenta(this.getCmbRetencionRenta().getSelectedItem().toString().substring(0,1));
			documentoActualizadoIf.setRetencionIva(this.getCmbRetencionIva().getSelectedItem().toString().substring(0,1));
			try {
				SessionServiceLocator.getDocumentoSessionService().saveDocumento(documentoActualizadoIf);
				SpiritAlert.createAlert("Documento actualizado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("documento",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar informaci\u00f3n!",SpiritAlert.ERROR);
			}
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtAbreviado().setText("");
		
		getCmbBonificacion().setSelectedIndex(0);
		getCmbPideAutorizacion().setSelectedIndex(0);
		getCmbEstado().setSelectedIndex(0);
		getCmbDiferido().setSelectedIndex(0);
		getCmbPrecioEspecial().setSelectedIndex(0);
		getCmbDescuentoEspecial().setSelectedIndex(0);
		getCmbMulta().setSelectedIndex(0);
		getCmbInteres().setSelectedIndex(0);
		getCmbProtesto().setSelectedIndex(0);
		getCmbCheque().setSelectedIndex(0);
		getCmbRetencionRenta().setSelectedIndex(0);
		getCmbRetencionIva().setSelectedIndex(0);
		
		DefaultTableModel model = (DefaultTableModel) getTblDocumento().getModel();
		for(int i= this.getTblDocumento().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void cargarCombos(){
			getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
			getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
			
			getCmbPideAutorizacion().addItem(OPCION_SI);
			getCmbPideAutorizacion().addItem(OPCION_NO);
			getCmbBonificacion().addItem(OPCION_SI);
			getCmbBonificacion().addItem(OPCION_NO);
			getCmbDiferido().addItem(OPCION_SI);
			getCmbDiferido().addItem(OPCION_NO);
			getCmbPrecioEspecial().addItem(OPCION_SI);
			getCmbPrecioEspecial().addItem(OPCION_NO);
			getCmbDescuentoEspecial().addItem(OPCION_SI);
			getCmbDescuentoEspecial().addItem(OPCION_NO);
			getCmbMulta().addItem(OPCION_SI);
			getCmbMulta().addItem(OPCION_NO);
			getCmbInteres().addItem(OPCION_SI);
			getCmbInteres().addItem(OPCION_NO);
			getCmbProtesto().addItem(OPCION_SI);
			getCmbProtesto().addItem(OPCION_NO);
			getCmbCheque().addItem(OPCION_SI);
			getCmbCheque().addItem(OPCION_NO);
			getCmbRetencionRenta().addItem(OPCION_SI);
			getCmbRetencionRenta().addItem(OPCION_NO);
			getCmbRetencionIva().addItem(OPCION_SI);
			getCmbRetencionIva().addItem(OPCION_NO);
			cargarComboTipoDocumento();
	}
	
	private void cargarComboTipoDocumento(){
		try{
			List tiposDocumentos = (List)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().setEnabled(true);
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator documentosIterator = documentos.iterator();
			
			if (!getDocumentosVector().isEmpty())
				getDocumentosVector().removeAllElements();
			
			while (documentosIterator.hasNext()) {
				DocumentoIf documentosIf = (DocumentoIf) documentosIterator.next();
				
				tableModel = (DefaultTableModel) getTblDocumento().getModel();
				Vector<String> fila = new Vector<String>();
				getDocumentosVector().add(documentosIf);
				
				agregarColumnasTabla(documentosIf, fila);
				
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(DocumentoIf documentosIf, Vector<String> fila){
		fila.add(documentosIf.getCodigo());
		fila.add(documentosIf.getNombre());
		
		try {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documentosIf.getTipoDocumentoId());
			fila.add(tipoDocumento.getCodigo() + " - " + tipoDocumento.getNombre());
		
			if(documentosIf.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(documentosIf.getEstado().equals(ESTADO_INACTIVO))
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

	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strAbreviado = this.getTxtAbreviado().getText();
		
		Collection documento = null;
		boolean codigoRepetido = false;
		
		try {
			//documento = SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
			documento = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator documentoIt = documento.iterator();
		
		while (documentoIt.hasNext()) {
			DocumentoIf documentoIf = (DocumentoIf) documentoIt.next();			
			//Long tipoDocumento_id=((TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem()).getId();			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(documentoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(documentoIf.getCodigo())) 
					if (this.documentoActualizadoIf.getId().equals(documentoIf.getId()) == false)
						codigoRepetido = true;
		}
	 
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Documento está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Documento !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Documento !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}

		if ((("".equals(strAbreviado)) || (strAbreviado == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre abreviado para el Documento !!",SpiritAlert.WARNING);
			getTxtAbreviado().grabFocus();
			return false;
		}
		
		if(getCmbTipoDocumento().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo Documento para el Documento !!",SpiritAlert.WARNING);
			getCmbTipoDocumento().grabFocus();
			return false;
		}
		
		return true;
	}

	public void refresh() {
		cargarComboTipoDocumento();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public DocumentoIf getDocumentoActualizadoIf() {
		return documentoActualizadoIf;
	}

	public void setDocumentoActualizadoIf(DocumentoIf documentoActualizadoIf) {
		this.documentoActualizadoIf = documentoActualizadoIf;
	}

	public int getDocumentoSeleccionado() {
		return documentoSeleccionado;
	}

	public void setDocumentoSeleccionado(int documentoSeleccionado) {
		this.documentoSeleccionado = documentoSeleccionado;
	}

	public Vector getDocumentosVector() {
		return documentosVector;
	}

	public void setDocumentosVector(Vector documentosVector) {
		this.documentosVector = documentosVector;
	}
}
