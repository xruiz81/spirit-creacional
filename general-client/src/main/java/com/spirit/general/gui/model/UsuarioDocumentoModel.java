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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioDocumentoData;
import com.spirit.general.entity.UsuarioDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPUsuarioDocumento;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.general.session.ModuloSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.general.session.UsuarioDocumentoSessionService;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;


public class UsuarioDocumentoModel extends JPUsuarioDocumento {

	private static final long serialVersionUID = -3261934736931793310L;
	private Vector<UsuarioDocumentoIf> usuariosDocumentosVector = null;
	private DefaultTableModel tableModel;
	private int usuarioDocumentoSeleccionado;
	private UsuarioDocumentoIf usuarioDocumentoIf;
	private ModuloIf moduloIf;
	private DocumentoIf documentoIf;
	private static final String NOMBRE_OPCION_SI = "SI";
	private static final String OPCION_SI = NOMBRE_OPCION_SI.substring(0,1);
	private static final String NOMBRE_OPCION_NO = "NO";
	private static final String OPCION_NO = NOMBRE_OPCION_NO.substring(0,1);
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private Vector<UsuarioIf> usuariosVector = new Vector<UsuarioIf>();
	private Vector<UsuarioIf> usuariosColeccion = new Vector<UsuarioIf>();
	private Vector<UsuarioDocumentoIf> usuariosDocumentoColeccion = new Vector<UsuarioDocumentoIf>();
	private Map<Long,Vector<UsuarioDocumentoIf>> mapaUsuarioDocumento = new HashMap<Long, Vector<UsuarioDocumentoIf>>();
	private Map<Long,Vector<UsuarioDocumentoIf>> mapaEliminadosUsuarioDocumento = new HashMap<Long, Vector<UsuarioDocumentoIf>>();
	private int usuarioSeleccionado;
	private UsuarioIf usuarioIf;

	public UsuarioDocumentoModel() {
		iniciarComponentes();
		initListeners();
		setSorterTable(getTblUsuarioDocumento());
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		anchoColumnasTabla();
		iniciarBotones();
	}
	
	private void iniciarBotones(){
		getBtnAgregarDetalle().setText("");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar detalle al pedido");
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle del pedido");
		getBtnEliminarDetalle().setText("");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle del pedido");
	}
	
	private void anchoColumnasTabla() {
		getTblUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblUsuarioDocumento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblUsuarios().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblUsuarios().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(320);
		anchoColumna = getTblUsuarioDocumento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblUsuarioDocumento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblUsuarioDocumento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(350);
	}
	
	ActionListener oListenerAgregarDetalle = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if ( verificarValoresCombos() ) {
				if(usuarioIf!=null){
				if ( !usuarioDocumentoRepetido(usuarioIf, documentoIf) ){
					modificarMapaEliminadosUsuarioDocumento(usuarioIf,documentoIf.getId());
					UsuarioDocumentoData usuarioDocumento = new UsuarioDocumentoData();
					usuarioDocumento.setUsuarioId(usuarioIf.getId());
					usuarioDocumento.setDocumentoId(documentoIf.getId());
					usuarioDocumento.setPermisoImpresion(getCmbPermisoImprimir().getSelectedItem().toString().substring(0,1));
					usuarioDocumento.setPermisoRegistro(getCmbPermisoGuardar().getSelectedItem().toString().substring(0,1));
					usuarioDocumento.setPermisoBorrado(getCmbPermisoBorrar().getSelectedItem().toString().substring(0,1));
					usuarioDocumento.setPermisoAutorizar(getCmbPermisoAutorizar().getSelectedItem().toString().substring(0,1));
					usuarioDocumento.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
					usuariosDocumentosVector = mapaUsuarioDocumento.get(usuarioIf.getId());
					usuariosDocumentosVector.add(usuarioDocumento);
					cargarTabla();
				} else{
					SpiritAlert.createAlert("Usuario ya tiene documento asociado !!", SpiritAlert.INFORMATION);
				}
			}
				else{
					SpiritAlert.createAlert("Debe escoger un usuario para poder hacer la relación con los documentos!!", SpiritAlert.INFORMATION);
				}
			}
		}
	};
	
	ActionListener oListenerActualizarDetalle = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if ( usuarioDocumentoSeleccionado != -1 ){
				if ( verificarValoresCombos() ){
					modificarMapaEliminadosUsuarioDocumento(usuarioIf,documentoIf.getId());
					UsuarioDocumentoIf usuarioDocumento = usuariosDocumentosVector.get(usuarioDocumentoSeleccionado);
					usuarioDocumento.setPermisoRegistro( getCmbPermisoGuardar().getSelectedItem().toString().substring(0,1) );
					usuarioDocumento.setPermisoBorrado( getCmbPermisoBorrar().getSelectedItem().toString().substring(0,1) );
					usuarioDocumento.setPermisoImpresion( getCmbPermisoImprimir().getSelectedItem().toString().substring(0,1) );
					usuarioDocumento.setPermisoAutorizar( getCmbPermisoAutorizar().getSelectedItem().toString().substring(0, 1) );
					usuarioDocumento.setEstado( getCmbEstado().getSelectedItem().toString().substring(0, 1) );
					cargarTabla();
				} 
			} else {
				SpiritAlert.createAlert("Debe seleccionar el documento para modificar los permisos !!", SpiritAlert.INFORMATION);
			}
		}
	};
	
	ActionListener oListenerEliminarDetalle = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if ( usuarioIf != null ){
				if ( usuarioDocumentoSeleccionado != -1 ) {
					Vector<UsuarioDocumentoIf> usuarioDocumentoVector = mapaUsuarioDocumento.get(usuarioIf.getId());
					Vector<UsuarioDocumentoIf> usuarioDocumentoVectorEliminados = mapaEliminadosUsuarioDocumento.get(usuarioIf.getId());
					UsuarioDocumentoIf udt = usuarioDocumentoVector.get(usuarioDocumentoSeleccionado);
					if ( usuarioDocumentoVectorEliminados != null ){
						usuarioDocumentoVectorEliminados.add( udt );
					} else {
						usuarioDocumentoVectorEliminados = new Vector<UsuarioDocumentoIf>();
						usuarioDocumentoVectorEliminados.add( udt );
						mapaEliminadosUsuarioDocumento.put(usuarioIf.getId(), usuarioDocumentoVectorEliminados);
					}
					modificarMapaUsuarioDocumento(usuarioIf,udt.getDocumentoId());
					cargarTabla();
				}
			}
		}
	};
	
	private void modificarMapaEliminadosUsuarioDocumento(UsuarioIf usuarioIf,Long documentoId){
		
		System.out.println("USUARIO"+usuarioIf);
		if(usuarioIf!=null){
		Vector<UsuarioDocumentoIf> usuarioDocumentoVectorTmp = mapaEliminadosUsuarioDocumento.get(usuarioIf.getId());
		if ( usuarioDocumentoVectorTmp != null ){
			for ( int i=0; i< usuarioDocumentoVectorTmp.size() ;i++ ){
				UsuarioDocumentoIf usuarioDocumentoTmp = usuarioDocumentoVectorTmp.get(i);
				if ( usuarioDocumentoTmp.getDocumentoId().longValue() == documentoId.longValue() ){
					usuarioDocumentoVectorTmp.remove(i);
					return;
				}
			}
		}
		}
		
	}
	
	private void modificarMapaUsuarioDocumento(UsuarioIf usuarioIf,Long documentoId){
		Vector<UsuarioDocumentoIf> usuarioDocumentoVectorTmp = mapaUsuarioDocumento.get(usuarioIf.getId());
		if ( usuarioDocumentoVectorTmp != null ){
			for ( int i=0; i< usuarioDocumentoVectorTmp.size() ;i++ ){
				UsuarioDocumentoIf usuarioDocumentoTmp = usuarioDocumentoVectorTmp.get(i);
				if ( usuarioDocumentoTmp.getDocumentoId().longValue() == documentoId.longValue() ){
					usuarioDocumentoVectorTmp.remove(i);
					return;
				}
			}
		}
		
	}

	private boolean verificarValoresCombos() {
		if ( getCmbModulo().getSelectedItem()==null ){
			SpiritAlert.createAlert("Selecionar un m\u00f3dulo !!", SpiritAlert.WARNING);
			return false;
		}
		if ( getCmbDocumento().getSelectedItem()==null ){
			SpiritAlert.createAlert("Seleccionar un documento !!", SpiritAlert.WARNING);
			return false;
		}
		if ( getCmbPermisoGuardar().getSelectedItem()==null ||  getCmbPermisoBorrar().getSelectedItem() == null
				|| getCmbPermisoImprimir().getSelectedItem()==null || getCmbPermisoAutorizar().getSelectedItem()==null
				|| getCmbEstado().getSelectedItem()==null ){
			SpiritAlert.createAlert("Tiene que establecer un valor para todas las opciones !!", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	MouseListener oMouseAdapterTblUsuarios = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowUsuariosForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblUsuarios = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowUsuariosForUpdate(evt);
		}
	};
	
	private void enableSelectedRowUsuariosForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setUsuarioSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			usuarioIf = (UsuarioIf) getUsuariosVector().get(getUsuarioSeleccionado());
			usuarioDocumentoSeleccionado = -1;
			cargarTabla();
			cleanDocumento();
			showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblUsuarioDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblUsuarioDocumento = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setUsuarioDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				usuarioDocumentoIf = (UsuarioDocumentoIf) getUsuariosDocumentosVector().get(getUsuarioDocumentoSeleccionado());
				usuarioIf = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuarioDocumentoIf.getUsuarioId());
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(usuarioDocumentoIf.getDocumentoId());
				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
				ModuloIf modulo = SessionServiceLocator.getModuloSessionService().getModulo(tipoDocumento.getModuloId());
				getCmbModulo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModulo(), modulo.getId()));
				getCmbModulo().repaint();
				getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), usuarioDocumentoIf.getDocumentoId()));
				getCmbDocumento().repaint();
								
				if (usuarioDocumentoIf.getPermisoRegistro().equals(OPCION_SI))
					getCmbPermisoGuardar().setSelectedItem(NOMBRE_OPCION_SI);
				else if (usuarioDocumentoIf.getPermisoRegistro().equals(OPCION_NO))
					getCmbPermisoGuardar().setSelectedItem(NOMBRE_OPCION_NO);
				
				if (usuarioDocumentoIf.getPermisoBorrado().equals(OPCION_SI))
					getCmbPermisoBorrar().setSelectedItem(NOMBRE_OPCION_SI);
				else if (usuarioDocumentoIf.getPermisoBorrado().equals(OPCION_NO))
					getCmbPermisoBorrar().setSelectedItem(NOMBRE_OPCION_NO);
				
				if (usuarioDocumentoIf.getPermisoImpresion().equals(OPCION_SI))
					getCmbPermisoImprimir().setSelectedItem(NOMBRE_OPCION_SI);
				else if (usuarioDocumentoIf.getPermisoImpresion().equals(OPCION_NO))
					getCmbPermisoImprimir().setSelectedItem(NOMBRE_OPCION_NO);
				
				if (usuarioDocumentoIf.getPermisoAutorizar().equals(OPCION_SI))
					getCmbPermisoAutorizar().setSelectedItem(NOMBRE_OPCION_SI);
				else if (usuarioDocumentoIf.getPermisoAutorizar().equals(OPCION_NO))
					getCmbPermisoAutorizar().setSelectedItem(NOMBRE_OPCION_NO);
				
				if (usuarioDocumentoIf.getEstado().equals(ESTADO_ACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if (usuarioDocumentoIf.getEstado().equals(ESTADO_INACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
				//showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se produjo un error de comunicacin con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void initListeners() {

		getTblUsuarios().addMouseListener(oMouseAdapterTblUsuarios);
		getTblUsuarios().addKeyListener(oKeyAdapterTblUsuarios);
		getTblUsuarioDocumento().addMouseListener(oMouseAdapterTblUsuarioDocumento);
		getTblUsuarioDocumento().addKeyListener(oKeyAdapterTblUsuarioDocumento);
		
		getBtnAgregarDetalle().addActionListener(oListenerAgregarDetalle);
		getBtnActualizarDetalle().addActionListener(oListenerActualizarDetalle);
		getBtnEliminarDetalle().addActionListener(oListenerEliminarDetalle);
		
		/*getBtnBuscarUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Map mapa = buildQuery();
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(300);
				anchoColumnas.addElement(30);
				usuarioCriteria = new UsuarioCriteria();
				usuarioCriteria.setQueryBuilded(mapa);
				usuarioCriteria.setNombrePanel("de Usuarios");
				if (usuarioCriteria.getResultListSize() > 0) {
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), usuarioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						usuarioIf = (UsuarioIf) popupFinder.getElementoSeleccionado();
						cargarTablaPorUsuario();
					} else
						usuarioIf = null;
				}
			}
		});*/
		
		getCmbModulo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbModulo().getSelectedItem() != null)
					moduloIf = (ModuloIf) getCmbModulo().getSelectedItem();
				else
					moduloIf = null;
				
				cargarComboDocumento();
			}
		});
		
		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbDocumento().getSelectedItem() != null)
					documentoIf = (DocumentoIf) getCmbDocumento().getSelectedItem();
				else
					documentoIf = null;
			}
		});		
	}
	
	public void generarColeccionUsuariosSeleccionados(){
		usuariosColeccion.clear();
		boolean usuarioSeleccionado = false;
		for(int i=0; i<usuariosVector.size(); i++){
			usuarioSeleccionado = (Boolean)getTblUsuarios().getModel().getValueAt(i,0);
			if(usuarioSeleccionado){
				usuariosColeccion.add(usuariosVector.get(i));
			}
		}
	}
	
	public void generarColeccionUsuariosDocumento(){
		usuariosDocumentoColeccion.clear();
		for(int i=0; i<usuariosColeccion.size(); i++){
			UsuarioIf usuarioIf = usuariosColeccion.get(i);
			UsuarioDocumentoData usuarioDocumento = new UsuarioDocumentoData();
			usuarioDocumento.setUsuarioId(usuarioIf.getId());
			usuarioDocumento.setDocumentoId(documentoIf.getId());
			usuarioDocumento.setPermisoImpresion(getCmbPermisoImprimir().getSelectedItem().toString().substring(0,1));
			usuarioDocumento.setPermisoRegistro(getCmbPermisoGuardar().getSelectedItem().toString().substring(0,1));
			usuarioDocumento.setPermisoBorrado(getCmbPermisoBorrar().getSelectedItem().toString().substring(0,1));
			usuarioDocumento.setPermisoAutorizar(getCmbPermisoAutorizar().getSelectedItem().toString().substring(0,1));
			usuarioDocumento.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
			usuariosDocumentoColeccion.add(usuarioDocumento);
		}
	}

	public void save() {
		try {
			generarColeccionUsuariosSeleccionados();
			if (validateFields()) {
				filtrarMapaUsuarioDocumentos();
				filtrarMapaEliminadosUsuarioDocumentos();
				SessionServiceLocator.getUsuarioDocumentoSessionService().procesarUsuarioDocumento(mapaUsuarioDocumento);
				showSaveMode();
				SpiritAlert.createAlert("Informaci\u00f3n guardada con \u00e9xito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			generarColeccionUsuariosSeleccionados();
			if (validateFields()) {
				filtrarMapaUsuarioDocumentos();
				filtrarMapaEliminadosUsuarioDocumentos();
				SessionServiceLocator.getUsuarioDocumentoSessionService().actualizarUsuarioDocumento(mapaUsuarioDocumento, mapaEliminadosUsuarioDocumento);
				SpiritAlert.createAlert("Informaci\u00f3n actualizada con \u00e9xito !", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			generarColeccionUsuariosSeleccionados();
			if(usuariosColeccion.size()>1){
				SpiritAlert.createAlert("Solo se puede eliminar un Usuario-Documento a la vez!", SpiritAlert.WARNING);
			}else if (usuariosColeccion.size()==1){
				UsuarioDocumentoIf usuarioDocumentoIf = (UsuarioDocumentoIf) getUsuariosDocumentosVector().get(getUsuarioDocumentoSeleccionado());
				SessionServiceLocator.getUsuarioDocumentoSessionService().deleteUsuarioDocumento(usuarioDocumentoIf.getId());
				showSaveMode();
				SpiritAlert.createAlert("Informaci\u00f3n eliminada con \u00e9xito !", SpiritAlert.INFORMATION);
			}else{
				SpiritAlert.createAlert("Debe seleccionar al menos un Usuario !", SpiritAlert.WARNING);
			}
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	private void filtrarMapaUsuarioDocumentos(){
		Iterator itMapa = mapaUsuarioDocumento.keySet().iterator();
		while( itMapa.hasNext() ){
			Long idUsuario = (Long) itMapa.next();
			boolean estaSeleccionado = false;
			for ( int i=0; i < usuariosColeccion.size(); i++ ){
				UsuarioIf usuario = usuariosColeccion.get(i);
				if ( usuario.getId().longValue() == idUsuario ){
					estaSeleccionado = true;
					break;
				}
			}
			if ( !estaSeleccionado )
				itMapa.remove();
		}
	}
	
	private void filtrarMapaEliminadosUsuarioDocumentos(){
		Iterator itMapa = mapaEliminadosUsuarioDocumento.keySet().iterator();
		while( itMapa.hasNext() ){
			Long idUsuario = (Long) itMapa.next();
			Vector<UsuarioDocumentoIf> vector = mapaEliminadosUsuarioDocumento.get( idUsuario );
			if ( vector.size() == 0 )
				itMapa.remove();
			
			boolean estaSeleccionado = false;
			for ( int i=0; i < usuariosColeccion.size(); i++ ){
				UsuarioIf usuario = usuariosColeccion.get(i);
				if ( usuario.getId().longValue() == idUsuario ){
					estaSeleccionado = true;
					break;
				}
			}
			if ( !estaSeleccionado )
				itMapa.remove();
		}
		
		
	}
	
	public void refresh(){
		cargarComboDocumento();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public boolean isEmpty() {
		return false;
	}

	public void clean() {
		mapaUsuarioDocumento.clear();
		mapaEliminadosUsuarioDocumento.clear();
		usuariosColeccion.clear();
		usuarioIf = null;
		cleanTableDocumentos();
		cleanTableUsuarios();
		usuariosDocumentosVector = null;
		documentoIf = null;
		cleanDocumento();
		this.repaint();
	}

	private void cleanDocumento() {
		this.getCmbDocumento().setSelectedItem(null);
		this.getCmbDocumento().repaint();
	}

	private void cleanTableDocumentos() {
		DefaultTableModel model = (DefaultTableModel) this.getTblUsuarioDocumento().getModel();
		for (int i = this.getTblUsuarioDocumento().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	private void cleanTableUsuarios() {		
		DefaultTableModel model = (DefaultTableModel) this.getTblUsuarios().getModel();
		for (int i = this.getTblUsuarios().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void cargarCombos() {
		cargarComboModulo();
		cargarComboDocumento();
		this.getCmbPermisoImprimir().setSelectedIndex(0);
		this.getCmbPermisoGuardar().setSelectedIndex(0);
		this.getCmbPermisoBorrar().setSelectedIndex(0);
		this.getCmbPermisoAutorizar().setSelectedIndex(0);
		this.getCmbEstado().setSelectedIndex(0);
	}
	
	private void cargarComboModulo() {
		try {
			List modulos = (List) SessionServiceLocator.getModuloSessionService().findModuloByStatus("A");
			refreshCombo(getCmbModulo(), modulos);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboDocumento() {
		try {
			List documentos = null;
			
			if (moduloIf != null)
				documentos = (List) SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaIdAndModuloId(Parametros.getIdEmpresa(), moduloIf.getId());
			else
				documentos = (List) SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			
			refreshCombo(getCmbDocumento(), documentos);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarCombos();
		cargarTablaUsuarios();
		getCmbDocumento().setEnabled(true);
		getCmbPermisoImprimir().setEnabled(true);
		getCmbPermisoGuardar().setEnabled(true);
		getCmbPermisoBorrar().setEnabled(true);
		getCmbPermisoAutorizar().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTblUsuarios().grabFocus();
		
	}
	
	private void cargarTablaUsuarios() {
		try {
			Map parameterMap = buildQuery();
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByQuery(parameterMap);
			Iterator itUsuarios = usuarios.iterator();
			
			if(!getUsuariosVector().isEmpty()){
				getUsuariosVector().removeAllElements();
			}
			
			while (itUsuarios.hasNext()) {
				UsuarioIf usuarioIf = (UsuarioIf) itUsuarios.next();
				Collection<UsuarioDocumentoIf> usuariosDocumentos = SessionServiceLocator.getUsuarioDocumentoSessionService().findUsuarioDocumentoByUsuarioId(usuarioIf.getId());
				mapaUsuarioDocumento.put( usuarioIf.getId() , new Vector<UsuarioDocumentoIf>(usuariosDocumentos) );	
				
				tableModel = (DefaultTableModel) getTblUsuarios().getModel();
				Vector<Object> fila = new Vector<Object>();
				getUsuariosVector().add(usuarioIf);
				
				agregarColumnasTabla(usuarioIf, fila);
				
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(UsuarioIf usuarioIf, Vector<Object> fila){
		try {
			fila.add(new Boolean(false));
			EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuarioIf.getEmpleadoId());
			fila.add(usuarioIf + " - " + empleado.getNombres() + " " + empleado.getApellidos());		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		getCmbDocumento().setEnabled(true);
		getCmbPermisoImprimir().setEnabled(true);
		getCmbPermisoGuardar().setEnabled(true);
		getCmbPermisoBorrar().setEnabled(true);
		getCmbPermisoAutorizar().setEnabled(true);
		getCmbEstado().setEnabled(true);
	}
	
	private void cargarTabla() {
		try {
			usuariosDocumentosVector = null;
			if (usuarioIf != null) {
				usuariosDocumentosVector = mapaUsuarioDocumento.get( usuarioIf.getId() );
				
				/*if(!usuariosDocumentosVector.isEmpty()){
					usuariosDocumentosVector.removeAllElements();
				}*/
				cleanTableDocumentos();
				
				int i=0;
				Iterator usuariosDocumentosIterator = usuariosDocumentosVector.iterator();
				while (usuariosDocumentosIterator.hasNext()) {
					UsuarioDocumentoIf usuarioDocumentoIf = (UsuarioDocumentoIf) usuariosDocumentosIterator.next();
					tableModel = (DefaultTableModel) getTblUsuarioDocumento().getModel();
					Vector<String> fila = new Vector<String>();
					//getUsuariosDocumentosVector().add(usuarioDocumentoIf);
					/*if (this.usuarioDocumentoIf != null) {
					 if (this.usuarioDocumentoIf.getId().compareTo(usuarioDocumentoIf.getId()) == 0)
					 setUsuarioDocumentoSeleccionado(i);
					 }*/
					agregarColumnasTabla(usuarioDocumentoIf, fila);
					tableModel.addRow(fila);
					i++;
				}
				Utilitarios.scrollToCenter(getTblUsuarioDocumento(), usuariosDocumentosVector, 0);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar los documentos por usuario", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(UsuarioDocumentoIf usuariosDocumentosIf, Vector<String> fila) {
		try {
			UsuarioIf usuarioIf = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuariosDocumentosIf.getUsuarioId());
			DocumentoIf documentoIf = SessionServiceLocator.getDocumentoSessionService().getDocumento(usuariosDocumentosIf.getDocumentoId());
			TipoDocumentoIf tipoDocumentoIf = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documentoIf.getTipoDocumentoId());
			ModuloIf moduloIf = SessionServiceLocator.getModuloSessionService().getModulo(tipoDocumentoIf.getModuloId());
			fila.add(usuarioIf.getUsuario());
			fila.add(moduloIf.getNombre());
			fila.add(documentoIf.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean usuarioDocumentoRepetido(UsuarioIf usuario, DocumentoIf documento) {
		System.out.println("getUsuariosDocumentosVector:"+getUsuariosDocumentosVector());
		if(getUsuariosDocumentosVector()!=null)
		{
			int sizeUsuariosDocumentosVector = getUsuariosDocumentosVector().size(); 
			if ( sizeUsuariosDocumentosVector != 0) {
				for(int i=0;i<sizeUsuariosDocumentosVector;i++) {
					UsuarioDocumentoIf usuarioDocumento = (UsuarioDocumentoIf) getUsuariosDocumentosVector().get(i);

					if(usuarioDocumento.getUsuarioId().equals(usuario.getId()) &&
					   usuarioDocumento.getDocumentoId().equals(documento.getId())) {
					
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean validateFields() {
		if(usuariosColeccion.isEmpty()){
			SpiritAlert.createAlert("Debe seleccionar al menos un Usuario !!", SpiritAlert.WARNING);
			this.getTblUsuarios().grabFocus();
			return false;
		}
		
		/*if (getCmbDocumento().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un documento !!", SpiritAlert.WARNING);
			this.getCmbDocumento().grabFocus();
			return false;
		}

		if (this.getCmbPermisoGuardar().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el usuario tiene o no permiso de guardado !!", SpiritAlert.WARNING);
			this.getCmbPermisoGuardar().grabFocus();
			return false;
		}
		
		if (this.getCmbPermisoBorrar().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el usuario tiene o no permiso de borrado !!", SpiritAlert.WARNING);
			this.getCmbPermisoBorrar().grabFocus();
			return false;
		}
		
		if (this.getCmbPermisoImprimir().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el usuario tiene o no permiso de impresi\u00f3n !!", SpiritAlert.WARNING);
			this.getCmbPermisoImprimir().grabFocus();
			return false;
		}
		
		if (this.getCmbPermisoAutorizar().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el usuario tiene o no permiso de autorizaci\u00f3n !!", SpiritAlert.WARNING);
			this.getCmbPermisoAutorizar().grabFocus();
			return false;
		}
		
		if (this.getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado !!", SpiritAlert.WARNING);
			this.getCmbEstado().grabFocus();
			return false;
		}*/
		
		return true;
	}
	
	private boolean validateFields(boolean esActualizacion) {
		boolean estaUsuarioDocumentoRepetido = usuarioDocumentoRepetido(usuarioIf, documentoIf);
		
		if (estaUsuarioDocumentoRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Asociaci\u00f3n Usuario-Documento ya se encuentra agregada!!!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();		
		aMap.put("tipousuario", "U");
		aMap.put("empresaId", Parametros.getIdEmpresa());
		return aMap;
	}

	public Vector getUsuariosDocumentosVector() {
		return usuariosDocumentosVector;
	}

	public void setUsuariosDocumentosVector(Vector usuariosDocumentosVec) {
		usuariosDocumentosVector = usuariosDocumentosVec;
	}

	public int getUsuarioDocumentoSeleccionado() {
		return usuarioDocumentoSeleccionado;
	}

	public void setUsuarioDocumentoSeleccionado(int selectedUsuarioDocumento) {
		usuarioDocumentoSeleccionado = selectedUsuarioDocumento;
	}

	public UsuarioDocumentoIf getUsuarioDocumentoActualizadoIf() {
		return usuarioDocumentoIf;
	}

	public void setUsuarioDocumentoActualizadoIf(UsuarioDocumentoIf usuarioDocumentoIf) {
		this.usuarioDocumentoIf = usuarioDocumentoIf;
	}
	 

	public Vector<UsuarioIf> getUsuariosVector() {
		return usuariosVector;
	}

	public void setUsuariosVector(Vector<UsuarioIf> usuariosVector) {
		this.usuariosVector = usuariosVector;
	}

	public int getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(int usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
}
