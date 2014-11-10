package com.spirit.general.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CruceDocumentoData;
import com.spirit.general.entity.CruceDocumentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPCruceDocumento;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;

public class CruceDocumentoModel extends JPCruceDocumento {

	private static final String NOMBRE_MODULO_FACTURA = "FACTURACION";
	private static final String NOMBRE_MODULO_INVENTARIO = "INVENTARIO";
	private static final String NOMBRE_MODULO_CARTERA = "CARTERA";
	private static final long serialVersionUID = 1243370382562521349L;
	private DefaultTableModel tableModel;
	private Vector cruceDocumentoVector = new Vector();
	private int cruceDocumentoSeleccionado;
	private CruceDocumentoIf cruceDocumentoActualizadoIf;
	private static Long empresaId = Long.valueOf(Parametros.getIdEmpresa());
	
	public CruceDocumentoModel() {
		showSaveMode();
		setSorterTable(getTblCruceDocumento());
		getTblCruceDocumento().addMouseListener(oMouseAdapterTblCruceDocumento);
		getTblCruceDocumento().addKeyListener(oKeyAdapterTblCruceDocumento);
		cargarCombos();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTipoDocumento().getSelectedIndex() != -1){
					cargarComboDocumento();
				}
			}
		});
		
		getCmbTipoDocumentoAplica().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTipoDocumentoAplica().getSelectedIndex() != -1){
					cargarComboDocumentoAplica();
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblCruceDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblCruceDocumento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setCruceDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			cruceDocumentoActualizadoIf = (CruceDocumentoIf)  getCruceDocumentoVector().get(getCruceDocumentoSeleccionado());
			setSelectedItemsCombos(cruceDocumentoActualizadoIf);
			showUpdateMode();
		}
	}
	
	private void setSelectedItemsCombos(CruceDocumentoIf crucesDocumentosIf) {		
		try {			
			DocumentoIf documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(crucesDocumentosIf.getDocumentoId());
			DocumentoIf documentoAplica = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(crucesDocumentosIf.getDocumentoaplId());
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
			TipoDocumentoIf tipoDocumentoAplica = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documentoAplica.getTipoDocumentoId());
			
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), tipoDocumento.getId()));
			getCmbTipoDocumento().repaint();
			getCmbTipoDocumentoAplica().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumentoAplica(), tipoDocumentoAplica.getId()));
			getCmbTipoDocumentoAplica().repaint();
			getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), documento.getId()));
			getCmbDocumento().repaint();
			getCmbDocumentoAplica().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumentoAplica(), documentoAplica.getId()));
			getCmbDocumentoAplica().repaint();
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
		
	public int getCruceDocumentoSeleccionado() {
		return this.cruceDocumentoSeleccionado;
	}
	
	public void setCruceDocumentoSeleccionado(int cruceDocumentoSeleccionado) {
		this.cruceDocumentoSeleccionado = cruceDocumentoSeleccionado;
	}
	
	public CruceDocumentoIf getCruceDocumentoActualizadoIf() {
		return this.cruceDocumentoActualizadoIf;
	}
	
	public void setCruceDocumentoActualizadoIf(CruceDocumentoIf cruceDocumentoActualizadoIf) {
		this.cruceDocumentoActualizadoIf = cruceDocumentoActualizadoIf;
	}
	
	public Vector getCruceDocumentoVector() {
		return cruceDocumentoVector;
	}
	
	public void setCruceDocumentoVector(Vector cruceDocumentoVector) {
		this.cruceDocumentoVector = cruceDocumentoVector;
	}

	public void save() {		
		try {
			if (validateFields()) {				
				CruceDocumentoData cruceDocumento = new CruceDocumentoData();
				
				cruceDocumento.setDocumentoId(((DocumentoIf) getCmbDocumento().getSelectedItem()).getId());
				cruceDocumento.setDocumentoaplId(((DocumentoIf) getCmbDocumentoAplica().getSelectedItem()).getId());
					
				SessionServiceLocator.getCruceDocumentoSessionService().addCruceDocumento(cruceDocumento);
				SpiritAlert.createAlert("Cruce de Documento guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("cruceDocumento",null);
				showSaveMode();
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",SpiritAlert.ERROR);
		}
	}

	public void delete() {		
		try {
			CruceDocumentoIf cruceDocumentoIf = (CruceDocumentoIf) getCruceDocumentoVector().get(getCruceDocumentoSeleccionado());
			SessionServiceLocator.getCruceDocumentoSessionService().deleteCruceDocumento(cruceDocumentoIf.getId());
			SpiritAlert.createAlert("Cruce de Documento eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("cruceDocumento",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setCruceDocumentoActualizadoIf((CruceDocumentoIf) getCruceDocumentoVector().get(getCruceDocumentoSeleccionado()));
				
				getCruceDocumentoActualizadoIf().setDocumentoId(((DocumentoIf) getCmbDocumento().getSelectedItem()).getId());
				getCruceDocumentoActualizadoIf().setDocumentoaplId(((DocumentoIf) getCmbDocumentoAplica().getSelectedItem()).getId());
				
				SessionServiceLocator.getCruceDocumentoSessionService().saveCruceDocumento(getCruceDocumentoActualizadoIf());
				getCruceDocumentoVector().setElementAt(getCruceDocumentoActualizadoIf(), getCruceDocumentoSeleccionado());
				setCruceDocumentoActualizadoIf(null);
				
				SpiritAlert.createAlert("Cruce de Documento actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("cruceDocumento",null);
				showSaveMode();
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",SpiritAlert.ERROR);
		}		
	}

	public void clean() {	
		DefaultTableModel model = (DefaultTableModel) getTblCruceDocumento().getModel();
		
		for(int i= getTblCruceDocumento().getRowCount();i>0;--i){
			model.removeRow(i-1);		
		}
	}

	public void showFindMode() {
	
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getCmbTipoDocumento().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection crucesDocumentos = SessionServiceLocator.getCruceDocumentoSessionService().findCruceDocumentoByEmpresaId(CruceDocumentoModel.empresaId);
			Iterator crucesDocumentosIterator = crucesDocumentos.iterator();
			
			if(!getCruceDocumentoVector().isEmpty()){
				getCruceDocumentoVector().removeAllElements();
			}
			
			while (crucesDocumentosIterator.hasNext()) {
				CruceDocumentoIf cruceDocumentoIf = (CruceDocumentoIf) crucesDocumentosIterator.next();
				
				tableModel = (DefaultTableModel) getTblCruceDocumento().getModel();
				Vector<String> fila = new Vector<String>();
				getCruceDocumentoVector().add(cruceDocumentoIf);
								
				addColumnsToRow(cruceDocumentoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblCruceDocumento(), crucesDocumentos, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void addColumnsToRow(CruceDocumentoIf cruceDocumentoIf, Vector<String> fila) throws GenericBusinessException {
		fila.add(SessionServiceLocator.getDocumentoSessionService().getDocumento(cruceDocumentoIf.getDocumentoId()).getNombre());
		fila.add(SessionServiceLocator.getDocumentoSessionService().getDocumento(cruceDocumentoIf.getDocumentoaplId()).getNombre());
	}
	
	public boolean validateFields() {
		
		if (getCmbDocumento().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar el Documento involucrado en el Cruce de Documento !!", SpiritAlert.WARNING);
			getCmbDocumento().grabFocus();
			return false;
		}
		
		if (getCmbDocumentoAplica().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar el Documento al que Aplica el Cruce de Documento !!",	SpiritAlert.WARNING);
			getCmbDocumentoAplica().grabFocus();
			return false;
		}
		
		for (int i=0; i < getCruceDocumentoVector().size(); i++) {
			if (getCmbDocumento().getSelectedItem() != null && getCmbDocumentoAplica().getSelectedItem() != null) {
				if ((((DocumentoIf) getCmbDocumento().getSelectedItem()).getId().compareTo(((CruceDocumentoIf) getCruceDocumentoVector().get(i)).getDocumentoId()) == 0) 
						&& (((DocumentoIf) getCmbDocumentoAplica().getSelectedItem()).getId().compareTo(((CruceDocumentoIf) getCruceDocumentoVector().get(i)).getDocumentoaplId()) == 0)) {
					SpiritAlert.createAlert("Cruce de Documento repetido !!",
							SpiritAlert.WARNING);
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void cargarCombos() {
		
		SpiritComboBoxModel cmbModel = null;
		
		try {
			Map aMap = new HashMap();
			aMap.put("empresaId",Parametros.getIdEmpresa());
			aMap.put("signocartera","-");
			Collection tipoDocumentos = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(aMap);
			cmbModel = new SpiritComboBoxModel((List) tipoDocumentos);
			getCmbTipoDocumento().setModel(cmbModel);
			
			if (getCmbTipoDocumento().getItemCount()>0) {
				getCmbTipoDocumento().setSelectedIndex(0);
			}
			
			Map aMapAplica = new HashMap();
			aMapAplica.put("empresaId",Parametros.getIdEmpresa());
			aMapAplica.put("signocartera","+");
			Collection tipoDocumentosAplica = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(aMapAplica);
			cmbModel = new SpiritComboBoxModel((List) tipoDocumentosAplica);
			getCmbTipoDocumentoAplica().setModel(cmbModel);
			
			if (getCmbTipoDocumentoAplica().getItemCount()>0) {
				getCmbTipoDocumentoAplica().setSelectedIndex(0);
			}
			
			cargarComboDocumento();
			cargarComboDocumentoAplica();	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboDocumento(){
		try {
			SpiritComboBoxModel cmbModel;
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem();
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumento.getId());
			cmbModel = new SpiritComboBoxModel((List) documentos);
			getCmbDocumento().setModel(cmbModel);
			
			if (getCmbDocumento().getItemCount()>0) {
				getCmbDocumento().setSelectedIndex(0);
				getCmbDocumento().setEnabled(true);
			} else {
				getCmbDocumento().setEnabled(false);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboDocumentoAplica(){
		try {
			SpiritComboBoxModel cmbModel;
			TipoDocumentoIf tipoDocumentoAplica = (TipoDocumentoIf)getCmbTipoDocumentoAplica().getSelectedItem();
			Collection documentosAplica = SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoAplica.getId());
			cmbModel = new SpiritComboBoxModel((List) documentosAplica);
			getCmbDocumentoAplica().setModel(cmbModel);
			
			if (getCmbDocumentoAplica().getItemCount()>0) {
				getCmbDocumentoAplica().setSelectedIndex(0);
				getCmbDocumentoAplica().setEnabled(true);
			} else {
				getCmbDocumentoAplica().setEnabled(false);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();		
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
}
