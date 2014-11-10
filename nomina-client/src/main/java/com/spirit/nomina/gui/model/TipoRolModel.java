package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.TipoRolData;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPTipoRol;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.nomina.handler.TipoRolProvisionado;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoRolModel extends JPTipoRol {
	
	private static final long serialVersionUID = -5352647749640089270L;
	
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private static final int MAX_LONGITUD_NEMONICO = 10;
	private static final String RUBRO_EVENTUAL_SI = "SI";
	private static final String RUBRO_EVENTUAL_NO = "NO";
	
	private ArrayList<TipoRolIf> tipoRolVector = new ArrayList<TipoRolIf>();
	private DefaultTableModel tableModel;
	private TipoRolIf tipoRolIf;
	
	public TipoRolModel(){
		initListeners();
		iniciarComponentes();
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblTipoRol());
		getTblTipoRol().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblTipoRol().addMouseListener(oMouseAdapterTblTipoRol);
		getTblTipoRol().addKeyListener(oKeyAdapterTblTipoRol);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoRol().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoRol().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtNemonico().addKeyListener(new TextChecker(MAX_LONGITUD_NEMONICO));
	}
	
	private void initListeners(){

		getCmbFormaPago().addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				TipoRolFormaPago formaPago = (TipoRolFormaPago) 
				getCmbFormaPago().getSelectedItem();
				if ( formaPago == TipoRolFormaPago.PERIODO ){
					getCmbFechaInicio().setSelectedItem(null);
					getCmbFechaInicio().setEnabled(false);
					getCmbFechaFin().setSelectedItem(null);
					getCmbFechaFin().setEnabled(false);
				} else {
					getCmbFechaInicio().setEnabled(true);
					getCmbFechaFin().setEnabled(true);
				}
			}

		});


	}
	
	private void iniciarComponentes(){
		//getCmbDocumento().setSelectedItem(null);
		//getCmbDocumento().repaint();
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(true);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(true);
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);
		getCmbFechaFin().setEditable(false);
		
		cargarComboFormaPago();
		cargarComboProvisionado();
	}
	
	MouseListener oMouseAdapterTblTipoRol = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoRol = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		int tipoRolSeleccionado = getTblTipoRol().getSelectedRow(); 
		if ( tipoRolSeleccionado != -1) {
			tipoRolSeleccionado = getTblTipoRol().convertRowIndexToModel(tipoRolSeleccionado); 
			tipoRolIf = (TipoRolIf)  tipoRolVector.get(tipoRolSeleccionado);
			getTxtCodigo().setText(tipoRolIf.getCodigo());
			getTxtNombre().setText(tipoRolIf.getNombre());
			getTxtNemonico().setText(tipoRolIf.getNemonico());
			
			getCmbFechaInicio().setDate(tipoRolIf.getFechaInicio());
			getCmbFechaInicio().repaint();
			
			getCmbFechaFin().setDate(tipoRolIf.getFechaFin());
			getCmbFechaFin().repaint();
			
			String rubroEventual = tipoRolIf.getRubroEventual(); 
			if ( rubroEventual != null ){
				if( RUBRO_EVENTUAL_SI.substring(0,1).equals(rubroEventual) ){
					getCmbRubroEventual().setSelectedItem(RUBRO_EVENTUAL_SI);
				}else if( RUBRO_EVENTUAL_NO.substring(0,1).equals(rubroEventual) ){
					getCmbRubroEventual().setSelectedItem(RUBRO_EVENTUAL_NO);
				}
			} else {
				getCmbRubroEventual().setSelectedItem(null);
			}
			
			String provisionado = tipoRolIf.getRubroProvisionado();
			if ( provisionado != null ){
				if( TipoRolProvisionado.SI.getLetra().equals(provisionado) ){
					getCmbRubroProvisionado().setSelectedItem(TipoRolProvisionado.SI);
				}else if( TipoRolProvisionado.NO.getLetra().equals(provisionado) ){
					getCmbRubroProvisionado().setSelectedItem(TipoRolProvisionado.NO);
				}
			} else {
				getCmbRubroProvisionado().setSelectedItem(null);
			}
			getCmbRubroProvisionado().repaint();
			
			String formaPagoLetra = tipoRolIf.getFormaPago();
			if (formaPagoLetra != null){
				try {
					TipoRolFormaPago formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(formaPagoLetra);
					getCmbFormaPago().setSelectedItem(formaPago);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			} else 
				getCmbFormaPago().setSelectedItem(null);
			getCmbFormaPago().repaint();
			
			setUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		//cargarCmbDocumentos();
		cargarTabla();
		iniciarComponentes();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection<TipoRolIf> tiposRol = SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			
			tableModel = (DefaultTableModel) getTblTipoRol().getModel();
			for ( TipoRolIf tipoRolIf : tiposRol ) {
				
				Vector<String> fila = new Vector<String>();
				tipoRolVector.add(tipoRolIf);
				crearFilaTabla(tipoRolIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoRol(), tiposRol, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void crearFilaTabla(TipoRolIf tipoRolIf, Vector<String> fila){
		fila.add(tipoRolIf.getCodigo());
		fila.add(tipoRolIf.getNombre());
		fila.add(tipoRolIf.getNemonico());
		
		String rubroEventual = tipoRolIf.getRubroEventual(); 
		if( rubroEventual.equals(RUBRO_EVENTUAL_SI.substring(0,1))){
			fila.add(RUBRO_EVENTUAL_SI);
		}else if( rubroEventual.equals(RUBRO_EVENTUAL_NO.substring(0,1))){
			fila.add(RUBRO_EVENTUAL_NO);
		}
		
		String rubroProvisionado = tipoRolIf.getRubroProvisionado();
		if ( rubroProvisionado != null ){
			if( rubroProvisionado.equals(TipoRolProvisionado.SI.getLetra()) ){
				fila.add(TipoRolProvisionado.SI.toString());
			}else if( rubroProvisionado.equals(TipoRolProvisionado.NO.getLetra()) ){
				fila.add(TipoRolProvisionado.NO.toString());
			}
		} else {
			fila.add("");
		}
		
		try {
			String formaPagoLetra = tipoRolIf.getFormaPago();
			TipoRolFormaPago formaPago = null;
			if ( formaPagoLetra != null )
				formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(formaPagoLetra);
			fila.add(formaPago != null? formaPago.toString() : "");
			
				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		}
		
	}
	
	@Override
	public void refresh() {
		//cargarCmbDocumentos();
	}
	
	private void cargarComboFormaPago(){
		TipoRolFormaPago[] formas = TipoRolFormaPago.values();
		List listaFomas = Arrays.asList(formas);
		refreshCombo(getCmbFormaPago(), listaFomas);
	}
	
	private void cargarComboProvisionado(){
		TipoRolProvisionado[] tipos = TipoRolProvisionado.values();
		List listaTipos = Arrays.asList(tipos);
		refreshCombo(getCmbRubroProvisionado(), listaTipos);
	}

	public void showFindMode() {		
		showSaveMode();
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoRolData data = new TipoRolData();

				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				/*if ( getCmbDocumento().getSelectedItem() != null )
					data.setDocumentoId( ((DocumentoIf)getCmbDocumento().getSelectedItem()).getId() );*/
				data.setNemonico(getTxtNemonico().getText());
				
				String rubroEventual = (String) getCmbRubroEventual().getSelectedItem(); 
				if(rubroEventual.equals(RUBRO_EVENTUAL_SI)){
					data.setRubroEventual(RUBRO_EVENTUAL_SI.substring(0,1));
				}else if(rubroEventual.equals(RUBRO_EVENTUAL_NO)){
					data.setRubroEventual(RUBRO_EVENTUAL_NO.substring(0,1));
				}
				
				TipoRolProvisionado rolProvisionado = (TipoRolProvisionado) getCmbRubroProvisionado().getSelectedItem();
				data.setRubroProvisionado(rolProvisionado.getLetra());
				
				java.sql.Date fecha = null;
				if ( getCmbFechaInicio().getDate() != null ){
					fecha = new java.sql.Date(getCmbFechaInicio().getDate().getTime());
					data.setFechaInicio(fecha);
				} else
					data.setFechaInicio(null);
				if ( getCmbFechaFin().getDate() != null ){
					fecha = new java.sql.Date(getCmbFechaFin().getDate().getTime());
					data.setFechaFin(fecha);
				} else 
					data.setFechaFin(null);
				
				TipoRolFormaPago formaPago = (TipoRolFormaPago) getCmbFormaPago().getSelectedItem();
				data.setFormaPago(formaPago.getLetra());
				
				SessionServiceLocator.getTipoRolSessionService().addTipoRol(data);
				SpiritAlert.createAlert("Tipo Rol grabado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				//tipoRolIf = tipoRolVector.get(tipo);
				
				tipoRolIf.setCodigo(getTxtCodigo().getText());
				tipoRolIf.setNombre(getTxtNombre().getText());
				tipoRolIf.setEmpresaId(Parametros.getIdEmpresa());
				tipoRolIf.setNemonico(getTxtNemonico().getText());
				
				String rubroEventual = (String)getCmbRubroEventual().getSelectedItem();
				if( rubroEventual.equals(RUBRO_EVENTUAL_SI) ){
					tipoRolIf.setRubroEventual(RUBRO_EVENTUAL_SI.substring(0,1));
				}else if( rubroEventual.equals(RUBRO_EVENTUAL_NO) ){
					tipoRolIf.setRubroEventual(RUBRO_EVENTUAL_NO.substring(0,1));
				}
				
				/*String rubroProvisionado = (String)getCmbRubroProvisionado().getSelectedItem();
				if( rubroProvisionado.equals(RUBRO_PROVISIONADO_SI) ){
					tipoRolIf.setRubroProvisionado(RUBRO_PROVISIONADO_SI.substring(0,1));
				}else if( rubroProvisionado.equals(RUBRO_PROVISIONADO_NO) ){
					tipoRolIf.setRubroProvisionado(RUBRO_PROVISIONADO_NO.substring(0,1));
				}*/
				TipoRolProvisionado rolProvisionado = (TipoRolProvisionado) getCmbRubroProvisionado().getSelectedItem();
				tipoRolIf.setRubroProvisionado(rolProvisionado.getLetra());
				
				java.sql.Date fecha = null;
				if ( getCmbFechaInicio().getDate() != null ){
					fecha = new java.sql.Date(getCmbFechaInicio().getDate().getTime());
					tipoRolIf.setFechaInicio(fecha);
				} else
					tipoRolIf.setFechaInicio(null);
				
				if ( getCmbFechaFin().getDate() != null ){
					fecha = new java.sql.Date(getCmbFechaFin().getDate().getTime());
					tipoRolIf.setFechaFin(fecha);
				} else 
					tipoRolIf.setFechaFin(null);
				
				TipoRolFormaPago formaPago = (TipoRolFormaPago) getCmbFormaPago().getSelectedItem();
				tipoRolIf.setFormaPago(formaPago.getLetra());
				
				SessionServiceLocator.getTipoRolSessionService().saveTipoRol(tipoRolIf);
				tipoRolIf = null;
				
				SpiritAlert.createAlert("Tipo Rol actualizado con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			//TipoRolIf tipoRolIf = (TipoRolIf) tipoRolVector.get(getTipoRolSeleccionado());
			SessionServiceLocator.getTipoRolSessionService().deleteTipoRol(tipoRolIf.getId());
			SpiritAlert.createAlert("Tipo Rol eliminado con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
				"No se puede eliminar el registro, puede tener datos referenciados!"
				,SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtNemonico().setText("");
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
		getCmbFormaPago().setSelectedIndex(0);
	
		tipoRolVector = null;
		tipoRolVector = new ArrayList<TipoRolIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblTipoRol());
	}

	public boolean validateFields() {
		
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		
		boolean codigoRepetido = false;
		
		try {
			Collection<TipoRolIf> tiposRol = SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
		
			for (TipoRolIf tipoRolIf : tiposRol){
				
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(tipoRolIf.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(tipoRolIf.getCodigo())) 
						if (this.tipoRolIf.getId().equals(tipoRolIf.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código del Tipo Rol está duplicado !!",SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar un Código !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un Nombre !!", SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
			if ((("".equals(getTxtNemonico().getText())) || (getTxtNemonico().getText() == null))) {
				SpiritAlert.createAlert("Debe ingresar un Nemónico !!", SpiritAlert.WARNING);
				getTxtNemonico().grabFocus();
				return false;
			}
			if(getCmbRubroEventual().getSelectedIndex() == -1){
				SpiritAlert.createAlert("Debe seleccionar si se puede usar en el Rubro Eventual !!", SpiritAlert.WARNING);
				getCmbRubroEventual().grabFocus();
				return false;
			}
			if(getCmbRubroProvisionado().getSelectedIndex() == -1){
				SpiritAlert.createAlert("Debe seleccionar si es Rubro Provisionado !!", SpiritAlert.WARNING);
				getCmbRubroProvisionado().grabFocus();
				return false;
			}
			if ( getCmbFormaPago().getSelectedItem() == null ){
				SpiritAlert.createAlert("Debe seleccionar una Forma de Pago !!", SpiritAlert.WARNING);
				getCmbFormaPago().grabFocus();
				return false;
			}
			
			return true;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return false;
	}
	
	public void showUpdateMode() {		
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}	
}
