package com.spirit.nomina.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.RolPagoDocumentoData;
import com.spirit.nomina.entity.RolPagoDocumentoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPRolPagoDocumento;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class RolPagoDocumentoModel extends JPRolPagoDocumento {

	private static final long serialVersionUID = 2892961420475683592L;
	
	private ArrayList<RolPagoDocumentoIf> relacionesCollection = new ArrayList<RolPagoDocumentoIf>();
	private int filaGlobalSeleccionada;
	private RolPagoDocumentoIf rolPagoDocumentoIf;
	private DefaultTableModel tableModel;

	private Map<Long,String> mapaTipoContrato =  null;
	private Map<Long,String> mapaTipoRol =  null;
	private Map<Long,String> mapaDocumento =  null;
	
	public RolPagoDocumentoModel() {
		initKeyListeners();
		iniciarComponentes();
		setSorterTable(getTblTipoContrato());
		anchoColumnasTabla();
		getTblTipoContrato().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblTipoContrato().addMouseListener(oMouseAdapterTblTipoContrato);
		getTblTipoContrato().addKeyListener(oKeyAdapterTblTipoContrato);
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		
	}
	
	private void initKeyListeners() {
		
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoContrato().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoContrato().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblTipoContrato = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoContrato = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		int filaSeleccionada = getTblTipoContrato().getSelectedRow();
		if (filaSeleccionada != -1) {
			filaGlobalSeleccionada = getTblTipoContrato().convertRowIndexToModel(filaSeleccionada); 
			rolPagoDocumentoIf = relacionesCollection.get(filaGlobalSeleccionada);
			
			if ( rolPagoDocumentoIf.getTipoRolId() != null ){
				try {
					TipoRolIf tp = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoDocumentoIf.getTipoRolId());
					getCmbTipoRol().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoRol(),tp.getId()));
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error al cargar Tipos de Rol", SpiritAlert.WARNING);
				}
			}
			getCmbTipoRol().repaint();
			
			if ( rolPagoDocumentoIf.getTipoContratoId() != null ){
				try {
					TipoContratoIf tc = SessionServiceLocator.getTipoContratoSessionService().getTipoContrato(rolPagoDocumentoIf.getTipoContratoId());
					getCmbTipoContrato().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoContrato(),tc.getId()));
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error al cargar Tipos de Contrato", SpiritAlert.WARNING);
				}
			}
			getCmbTipoContrato().repaint();
			
			if ( rolPagoDocumentoIf.getDocumentoId() != null ){
				try {
					DocumentoIf documentoIf = SessionServiceLocator.getDocumentoSessionService().getDocumento(rolPagoDocumentoIf.getDocumentoId());
					getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), documentoIf.getId()));
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error al cargar Documentos", SpiritAlert.WARNING);
				}
			} else{
				getCmbDocumento().setSelectedItem(null);
			}
			getCmbDocumento().repaint();
			
			Long usuarioId = rolPagoDocumentoIf.getCreacionUsuarioId();
			if ( usuarioId != null ){
				try {
					UsuarioIf u = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuarioId);
					getTxtUsuarioCreador().setText(u.getUsuario());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
			
			Date fecha = rolPagoDocumentoIf.getFechaCreacion();
			if ( fecha != null )
				getTxtFechaCreacion().setText(Utilitarios.getFechaCortaUppercase(fecha));
			
			usuarioId = rolPagoDocumentoIf.getActualizacionUsuarioId();
			if ( usuarioId != null ){
				try {
					UsuarioIf u = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuarioId);
					getTxtUsuarioActualizador().setText(u.getUsuario());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			} else 
				getTxtUsuarioActualizador().setText("");
			
			fecha = rolPagoDocumentoIf.getFechaActualizacion();
			if ( fecha != null )
				getTxtFechaActualizacion().setText(Utilitarios.getFechaCortaUppercase(fecha));
			else 
				getTxtFechaActualizacion().setText("");
			
			setUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarCmbTipoContrato();
		cargarCmbTipoRol();
		cargarCmbDocumentos();
		cargarTabla();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarMapa(Map<Long,String> mapa,Collection lista) throws GenericBusinessException{
		mapa.clear();
		Iterator<Long> itLista = lista.iterator();
		while(itLista.hasNext()){
			Object o = itLista.next();
			if ( o instanceof TipoRolIf ){
				TipoRolIf tr = (TipoRolIf)o;
				mapa.put(tr.getId(),tr.getNombre());
			} else if ( o instanceof TipoContratoIf ){
				TipoContratoIf tr = (TipoContratoIf)o;
				mapa.put(tr.getId(),tr.getNombre());
			} else if ( o instanceof DocumentoIf ){
				DocumentoIf tr = (DocumentoIf)o;
				mapa.put(tr.getId(),tr.getNombre());
			} else {
				throw new GenericBusinessException("Tipo no consiederado para llenar mapa !!");
			}
		}
	}
	
	private void cargarCmbTipoRol(){
		
		ArrayList<TipoRolIf> tipos = new ArrayList<TipoRolIf>();
		try {
			Map<String,Object> mapaTipoRol = new HashMap<String,Object>();
			mapaTipoRol.put("empresaId",Parametros.getIdEmpresa());
			tipos = (ArrayList<TipoRolIf>) SessionServiceLocator.getTipoRolSessionService().findTipoRolByQuery(mapaTipoRol);
			cargarMapa(this.mapaTipoRol,tipos);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al cargar Tipo de Rol !!",SpiritAlert.ERROR);
		}
		refreshCombo(getCmbTipoRol(), tipos);
		getCmbTipoRol().setSelectedItem(null);
		getCmbTipoRol().repaint();
	}
	
	private void cargarCmbTipoContrato(){
		ArrayList<TipoRolIf> tipos = new ArrayList<TipoRolIf>();
		try {
			Map<String,Object> mapaTipoContrato = new HashMap<String,Object>();
			mapaTipoContrato.put("empresaId",Parametros.getIdEmpresa());
			tipos = (ArrayList<TipoRolIf>) SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByQuery(mapaTipoContrato);
			cargarMapa(this.mapaTipoContrato,tipos);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al cargar Tipo de Rol !!",SpiritAlert.ERROR);
		}
		refreshCombo(getCmbTipoContrato(), tipos);
		getCmbTipoContrato().setSelectedItem(null);
		getCmbTipoContrato().repaint();
	}
	
	private void cargarCmbDocumentos(){
		try{
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("nombre", "%NOMINA%");
			Collection<ModuloIf> modulos = SessionServiceLocator.getModuloSessionService().findModuloByQuery(mapa); 
			if ( modulos.size() == 1 ){
				ModuloIf modulo = modulos.iterator().next();
				UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
				ArrayList<DocumentoIf> documentos = (ArrayList<DocumentoIf>)SessionServiceLocator.getDocumentoSessionService()
					.findDocumentoByUsuarioIdAndModuloId( usuarioIf.getId(),modulo.getId() );
				refreshCombo(getCmbDocumento(), documentos);
				cargarMapa(this.mapaDocumento,documentos);
			} else 
				SpiritAlert.createAlert("No se encuentra el Modulo con nombre Nomina", SpiritAlert.ERROR);
			
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al actualizar lista de documentos", SpiritAlert.ERROR);
		}
		getCmbDocumento().setSelectedItem(null);
		getCmbDocumento().repaint();
	}
	
	private void cargarTabla() {
		try {			
			
			relacionesCollection = null;
			relacionesCollection = (ArrayList<RolPagoDocumentoIf>) SessionServiceLocator.getRolPagoDocumentoSessionService().findRolPagoDocumentoByEmpresaId(Parametros.getIdEmpresa()); 
			
			for ( RolPagoDocumentoIf rpd : relacionesCollection ) {
				tableModel = (DefaultTableModel) getTblTipoContrato().getModel();
				Vector<String> fila = new Vector<String>();
				
				agregarFilaTabla(rpd, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoContrato(), relacionesCollection, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarFilaTabla(RolPagoDocumentoIf rpd, Vector<String> fila){
		fila.add(mapaTipoContrato.get(rpd.getTipoContratoId()));		
		fila.add(mapaTipoRol.get(rpd.getTipoRolId()));
		fila.add(mapaDocumento.get(rpd.getDocumentoId()));
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void save() {
		try {
			if (validateFields()) {
				RolPagoDocumentoIf data = new RolPagoDocumentoData();
				
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
				if ( tc != null )
					data.setTipoContratoId(tc.getId());
				
				TipoRolIf tr = (TipoRolIf) getCmbTipoRol().getSelectedItem();
				if ( tr != null )
					data.setTipoRolId(tr.getId());
					
				DocumentoIf doc = (DocumentoIf)getCmbDocumento().getSelectedItem();
				if ( doc != null )
					data.setDocumentoId( doc.getId() );
				
				data.setEmpresaId(Parametros.getIdEmpresa());
				
				UsuarioIf u = (UsuarioIf) Parametros.getUsuarioIf();
				data.setCreacionUsuarioId(u.getId());
				data.setFechaCreacion(new Date(Calendar.getInstance().getTime().getTime()));
				
				SessionServiceLocator.getRolPagoDocumentoSessionService().addRolPagoDocumento(data);
				SpiritAlert.createAlert("Tipo Contrato guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoContratos",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}

	public void refresh() {
		clean();
		cargarCmbTipoContrato();
		cargarCmbTipoRol();
		cargarCmbDocumentos();
		cargarTabla();
	}

	public void update() {
		try {	
			if (validateFields()) {
				
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
				rolPagoDocumentoIf.setTipoContratoId(tc.getId());
				TipoRolIf tr = (TipoRolIf) getCmbTipoRol().getSelectedItem();
				rolPagoDocumentoIf.setTipoRolId(tr.getId());
				DocumentoIf doc = (DocumentoIf)getCmbDocumento().getSelectedItem();
				rolPagoDocumentoIf.setDocumentoId(doc.getId());
				
				rolPagoDocumentoIf.setFechaActualizacion(
					new Date(Calendar.getInstance().getTime().getTime() ) );
				
				UsuarioIf u = (UsuarioIf) Parametros.getUsuarioIf();
				rolPagoDocumentoIf.setActualizacionUsuarioId(u.getId());
				
				SessionServiceLocator.getRolPagoDocumentoSessionService().saveRolPagoDocumento(rolPagoDocumentoIf);
				
				rolPagoDocumentoIf = null;
				
				SpiritAlert.createAlert("Relación actualizada con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomación!",
					SpiritAlert.ERROR);
		}	
	}


	public void delete() {
		try {
			SessionServiceLocator.getRolPagoDocumentoSessionService().deleteRolPagoDocumento(rolPagoDocumentoIf.getId());
			SpiritAlert.createAlert("Relación eliminada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		
		getCmbTipoRol().setSelectedItem(null);
		getCmbTipoContrato().setSelectedItem(null);
		getCmbDocumento().setSelectedItem(null);
		
		getTxtFechaCreacion().setText("");
		getTxtUsuarioCreador().setText("");
		
		getTxtFechaActualizacion().setText("");
		getTxtUsuarioActualizador().setText("");
		
		mapaDocumento = new HashMap<Long,String>();
		mapaTipoContrato = new HashMap<Long,String>();
		mapaTipoRol = new HashMap<Long,String>();
		
		limpiarTabla(getTblTipoContrato());

	}

	public boolean validateFields() {
		
		TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
		if ( tc == null ){
			SpiritAlert.createAlert("Debe seleccionar Tipo de Contrato !!",SpiritAlert.WARNING);
			return false;
		}	
		
		TipoRolIf tr = (TipoRolIf) getCmbTipoRol().getSelectedItem();
		if ( tr == null ){
			SpiritAlert.createAlert("Debe seleccionar Tipo de Rol !!",SpiritAlert.WARNING);
			return false;
		}
			
		DocumentoIf doc = (DocumentoIf)getCmbDocumento().getSelectedItem();
		if ( doc == null ){
			SpiritAlert.createAlert("Debe seleccionar un Documento !!",SpiritAlert.WARNING);
			return false;
		}
		
		Collection<RolPagoDocumentoIf> relaciones = null;
		
		try {
			relaciones = SessionServiceLocator.getRolPagoDocumentoSessionService().findRolPagoDocumentoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		for ( RolPagoDocumentoIf rp : relaciones ) {
			
			if ( tc.getId().equals(rp.getTipoContratoId()) && 
				 tr.getId().equals(rp.getTipoRolId()) &&
				 doc.getId().equals(rp.getDocumentoId()) )
			{
				SpiritAlert.createAlert("Ya existe documento asociado !!",SpiritAlert.WARNING);
				return false;
			}
			
		}
		
		return true;
	}
}
