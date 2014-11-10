package com.spirit.general.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaData;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.NumeradoresData;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.TipoClienteData;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.exceptions.SinPermisoPanelException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPEmpresa;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.general.session.FileManagerSessionService;
import com.spirit.general.session.TipoIdentificacionSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;
import com.spirit.util.ValidarIdentificacion;

public class EmpresaModel extends JPEmpresa {
	
	JDPopupFinderModel popupFinder;
	EmpresaIf empresa;
	ArrayList lista;
		
	private Vector<EmpresaIf> empresaVector = new Vector<EmpresaIf>();
	private int empresaSeleccionada;
	private EmpresaIf empresaActualizadaIf;
	private DefaultTableModel tableModel;

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_RUC = 13;
	private static final int MAX_LONGITUD_WEB = 30;
	private static final int MAX_LONGITUD_EMAIL_CONTADOR = 60;
	private static final int MAX_LONGITUD_NUMERO_IDENTIFICACION = 13;
	private static final int MAX_LONGITUD_RUC_CONTADOR = 13;
	private static final long serialVersionUID = 3544390326983602484L;
	private static final String PATH_MICROSOFT_PAINT = "C:\\WINDOWS\\system32\\MSPAINT.exe \"";
	private static final String PATH_MICROSOFT_POWERPOINT = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\POWERPNT.exe \"";
	private static final String PATH_MICROSOFT_EXCEL = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\EXCEL.exe \"";
	private static final String PATH_MICROSOFT_WORD = "C:\\Archivos de programa\\Microsoft Office\\OFFICE11\\WINWORD.exe \"";
	
	private static String[] tablasNumeradores = {"FACTURA","ORDEN TRABAJO","PEDIDO","CARTERA","PRE IMPRESO","ASIENTO","ORDEN COMPRA","COMPRA"};
	private static String[] tiposOperadoresNegocio = {"CLIENTE","PROVEEDOR","AMBOS"};
	Vector<NumeradoresIf> numeradoresColeccion = new Vector<NumeradoresIf>();
	Vector<TipoClienteIf> operadoresNegocioColeccion = new Vector<TipoClienteIf>();
	UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();

	public EmpresaModel() throws GenericBusinessException {
		if (usuario.getTipousuario().equals("S") || usuario.getTipousuario().equals("A") ){
			initKeyListeners();
			crearPopUp();
			setSorterTable(getTblEmpresa());
			getBtnCargarLogo().addActionListener(oActionListenerAgregarArchivo);
			anchoColumnasTabla();
			getTblEmpresa().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cargarCombos();
			showSaveMode();
			getTblEmpresa().addMouseListener(oMouseAdapterTblEmpresa);
			getTblEmpresa().addKeyListener(oKeyAdapterTblEmpresa);
			new HotKeyComponent(this);
		} else{
			throw new SinPermisoPanelException("Panel disponible solo para Administrador o Super Usuario");
			//SpiritAlert.createAlert("Panel disponible solo para Administrador o Super Usuario", SpiritAlert.WARNING);
		}
	}
	
	private void crearPopUp(){
		JPopupMenu popup = new JPopupMenu();
		JMenuItem item = new JMenuItem("Visualizar Archivo");
		item.addActionListener(visualizarListener);
		popup.add(item);
		getTblEmpresa().setComponentPopupMenu(popup);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtRuc().addKeyListener(new TextChecker(MAX_LONGITUD_RUC));
		getTxtWeb().addKeyListener(new TextChecker(MAX_LONGITUD_WEB, 0));
		getTxtEmailContador().addKeyListener(new TextChecker(MAX_LONGITUD_EMAIL_CONTADOR, 0));
		getTxtNumeroIdentificacion().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO_IDENTIFICACION));
		getTxtRucContador().addKeyListener(new TextChecker(MAX_LONGITUD_RUC_CONTADOR));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblEmpresa().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblEmpresa().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblEmpresa().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
	}
	
	private void cargarCombos() {
		cargarComboTipoIdentificacionRepresentante();
	}
	
	private void cargarComboTipoIdentificacionRepresentante() {
		try {
			Collection tiposIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			Iterator it = tiposIdentificacion.iterator();
			while (it.hasNext()) {
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) it.next();
				if (tipoIdentificacion.getCodigoSri() == null || tipoIdentificacion.getCodigoSri().equals("R"))
					it.remove();
			}
			SpiritComboBoxModel cmbModelTipoIdentificacion = new SpiritComboBoxModel((ArrayList) tiposIdentificacion);
			getCmbTipoIdentificacionRepresentante().setModel(cmbModelTipoIdentificacion);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		if (usuario.getTipousuario().equals("S") || usuario.getTipousuario().equals("A") )
			cargarComboTipoIdentificacionRepresentante();
	}
	
	ActionListener visualizarListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			int fila = getTblEmpresa().getSelectedRow();
			if (fila >= 0){
				try {
					EmpresaIf empresa = empresaActualizadaIf;
					if (empresa.getLogo()!=null && !empresa.getLogo().equals(""))
						abrirArchivo(Runtime.getRuntime(), empresa.getLogo());
					else
						SpiritAlert.createAlert("No tiene logo asociado", SpiritAlert.INFORMATION);
				} catch (IOException e1) {
					SpiritAlert.createAlert("No se puede abrir el archivo", SpiritAlert.WARNING);
				}
			}
		}
	};
	
	MouseListener oMouseAdapterTblEmpresa = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblEmpresa = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setEmpresaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			empresaActualizadaIf = (EmpresaIf)  getEmpresaVector().get(getEmpresaSeleccionada());
			getTxtCodigo().setText(getEmpresaActualizadaIf().getCodigo());
			getTxtNombre().setText(getEmpresaActualizadaIf().getNombre());
			getTxtRuc().setText(getEmpresaActualizadaIf().getRuc());
			getTxtLogo().setText(getEmpresaActualizadaIf().getLogo()!=null?getEmpresaActualizadaIf().getLogo():"");
			getTxtWeb().setText(getEmpresaActualizadaIf().getWeb());
			getTxtEmailContador().setText(getEmpresaActualizadaIf().getEmailContador());
			getTxtRucContador().setText(getEmpresaActualizadaIf().getRucContador());
			if (getEmpresaActualizadaIf().getTipoIdRepresentante() != null) {
				getCmbTipoIdentificacionRepresentante().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacionRepresentante(), getEmpresaActualizadaIf().getTipoIdRepresentante()));
				getCmbTipoIdentificacionRepresentante().validate();
				getCmbTipoIdentificacionRepresentante().repaint();
			}
			getTxtNumeroIdentificacion().setText(getEmpresaActualizadaIf().getNumeroIdentificacion());
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (usuario.getTipousuario().equals("S")) {
				if (validateFields()) {
					EmpresaData data = new EmpresaData();
					data.setCodigo(this.getTxtCodigo().getText());
					data.setNombre(this.getTxtNombre().getText());
					data.setRuc(this.getTxtRuc().getText());
					
					if (!usuario.getTipousuario().equals("S") && getTxtLogo().getText() != null && !getTxtLogo().getText().equals("")) {
						if (Parametros.getUrlCarpetaServidor()!=null && !Parametros.getUrlCarpetaServidor().equals("")) {
							File archivo = new File(getTxtLogo().getText());
							FileInputStreamSerializable archivoSerial = new FileInputStreamSerializable(archivo);
							SessionServiceLocator.getFileManagerSessionService().guardarArchivo(archivoSerial, Parametros.getUrlCarpetaServidor(), archivo.getName());
							data.setLogo(Parametros.getUrlCarpetaServidor() + archivo.getName());
						} else
							SpiritAlert.createAlert("No est\u00e1 definida carpeta de destino de archivos en el servidor, no se puede guardar el archivo.", SpiritAlert.INFORMATION);
					} else
						data.setLogo(null);
					
					if (!this.getTxtWeb().getText().equals(""))
						data.setWeb(this.getTxtWeb().getText());
					
					data.setEmailContador(getTxtEmailContador().getText());
					data.setRucContador(getTxtRucContador().getText());
					data.setTipoIdRepresentante(((TipoIdentificacionIf) getCmbTipoIdentificacionRepresentante().getSelectedItem()).getId());
					data.setNumeroIdentificacion(getTxtNumeroIdentificacion().getText());
					
					//Guardo el registro en la base
					numeradoresColeccion = generarColeccionNumeradores();
					operadoresNegocioColeccion = generarColeccionOperadoresNegocio();
					//EmpresaModel.getEmpresaSessionService().addEmpresa(data);
					SessionServiceLocator.getEmpresaSessionService().procesarEmpresa(data, numeradoresColeccion, operadoresNegocioColeccion);
					SpiritAlert.createAlert("Empresa guardada con \u00e9xito!",SpiritAlert.INFORMATION);
					SpiritCache.setObject("empresas",null);
					showSaveMode();
				}				
			} else
				SpiritAlert.createAlert("Usted no tiene permiso para crear nuevas empresas", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",SpiritAlert.ERROR);
		}
	}
	
	public void update() {	
		if (usuario.getTipousuario().equals("S") || usuario.getTipousuario().equals("A") ){
			try {	
				if (validateFields()) {
					setEmpresaActualizadaIf((EmpresaIf) getEmpresaVector().get(getEmpresaSeleccionada()));
	
					getEmpresaActualizadaIf().setCodigo(getTxtCodigo().getText());
					getEmpresaActualizadaIf().setNombre(getTxtNombre().getText());
					getEmpresaActualizadaIf().setRuc(getTxtRuc().getText());
					
					if (!usuario.getTipousuario().equals("S") && getTxtLogo().getText() != null && !getTxtLogo().getText().equals("")){
						if (Parametros.getUrlCarpetaServidor()!=null && !Parametros.getUrlCarpetaServidor().equals("")){
							File archivo = new File(getTxtLogo().getText());
							FileInputStreamSerializable archivoSerial = new FileInputStreamSerializable(archivo);
							SessionServiceLocator.getFileManagerSessionService().guardarArchivo(archivoSerial, Parametros.getUrlCarpetaServidor(), archivo.getName());
							getEmpresaActualizadaIf().setLogo(Parametros.getUrlCarpetaServidor() + archivo.getName());
						} else {
							SpiritAlert.createAlert("No est\u00e1 definida carpeta de destino de archivos en el servidor, no se puede guardar el archivo.", SpiritAlert.INFORMATION);
						}
					} else
						getEmpresaActualizadaIf().setLogo(null);
					
					if (!this.getTxtWeb().getText().equals(""))
						getEmpresaActualizadaIf().setWeb(this.getTxtWeb().getText());
					
					getEmpresaActualizadaIf().setEmailContador(getTxtEmailContador().getText());
					getEmpresaActualizadaIf().setRucContador(getTxtRucContador().getText());
					getEmpresaActualizadaIf().setTipoIdRepresentante(((TipoIdentificacionIf) getCmbTipoIdentificacionRepresentante().getSelectedItem()).getId());
					getEmpresaActualizadaIf().setNumeroIdentificacion(getTxtNumeroIdentificacion().getText());
	
					SessionServiceLocator.getEmpresaSessionService().saveEmpresa(getEmpresaActualizadaIf());
					getEmpresaVector().setElementAt(getEmpresaActualizadaIf(), getEmpresaSeleccionada());
					SpiritCache.setObject("empresas",null);
					setEmpresaActualizadaIf(null);
					
					SpiritAlert.createAlert("Empresa actualizada con \u00e9xito", SpiritAlert.INFORMATION);
					showSaveMode();
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!", SpiritAlert.ERROR);
			}
		} else{
			SpiritAlert.createAlert("Panel disponible solo para Administrador o Super Usuario", SpiritAlert.WARNING);
		}
	}

	public void delete() {		
		try {
			EmpresaIf empresaIf = (EmpresaIf) getEmpresaVector().get(getEmpresaSeleccionada());
			SessionServiceLocator.getEmpresaSessionService().eliminarEmpresa(empresaIf.getId());
			SpiritAlert.createAlert("Empresa eliminada con \u00e9xito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("empresas",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

    private Vector<NumeradoresIf> generarColeccionNumeradores() {
    	Vector<NumeradoresIf> numeradores = new Vector<NumeradoresIf>();
    	
    	for (int i=0; i<EmpresaModel.tablasNumeradores.length; i++) {
    		NumeradoresIf numerador = new NumeradoresData();
    		numerador.setNombreTabla(EmpresaModel.tablasNumeradores[i]);
    		numerador.setUltimoValor(BigDecimal.ZERO);
    		numeradores.add(numerador);
    	}
    	
    	return numeradores;
    }
    
    private Vector<TipoClienteIf> generarColeccionOperadoresNegocio() {
    	Vector<TipoClienteIf> operadores = new Vector<TipoClienteIf>();
    	
    	for (int i=0; i<EmpresaModel.tiposOperadoresNegocio.length; i++) {
    		TipoClienteIf tipoOperador = new TipoClienteData();
    		tipoOperador.setCodigo(EmpresaModel.tiposOperadoresNegocio[i].toString().substring(0,2));
    		tipoOperador.setNombre(EmpresaModel.tiposOperadoresNegocio[i].toString());
    		tipoOperador.setEmpresaId(Parametros.getIdEmpresa());
    		operadores.add(tipoOperador);
    	}
    	
    	return operadores;
    }
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strRuc = this.getTxtRuc().getText();
		String strEmailContador = this.getTxtEmailContador().getText();
		String strNumeroIdentificacion = this.getTxtNumeroIdentificacion().getText();
		String strRucContador = this.getTxtRucContador().getText();
		Collection empresa = null;
		boolean codigoRepetido = false;
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para la Empresa !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		try {
			empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresaList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator empresaIt = empresa.iterator();
		
		while (empresaIt.hasNext()) {
			EmpresaIf empresaIf = (EmpresaIf) empresaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(empresaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(empresaIf.getCodigo())) 
					if (this.empresaActualizadaIf.getId().equals(empresaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo de la Empresa est\u00e1 duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre para la Empresa !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if ( ("".equals(strRuc)) || (strRuc == null) ) {
			SpiritAlert.createAlert("Debe ingresar el RUC de la Empresa !!",SpiritAlert.WARNING);
			getTxtRuc().grabFocus();
			return false;
		}
		
		if (strRuc.trim().length() < MAX_LONGITUD_RUC) {
			SpiritAlert.createAlert("El RUC de la empresa debe tener " + MAX_LONGITUD_RUC + " d\u00edgitos !!", SpiritAlert.WARNING);
			getTxtRuc().grabFocus();
			return false;
		}
		
		if (!ValidarIdentificacion.esNumeroIdentificacionValido("RU", getTxtRuc().getText())) {
			SpiritAlert.createAlert("El RUC ingresado no es v\u00e1lido!!!", SpiritAlert.WARNING);
			getTxtRuc().grabFocus();
			return false;
		}
		
		if ("".equals(strEmailContador) || strEmailContador == null) {
			SpiritAlert.createAlert("Debe ingresar el email del contador !!", SpiritAlert.WARNING);
			getTxtEmailContador().grabFocus();
			return false;
		}
		
		if (!Utilitarios.validarCorreoElectronico(strEmailContador)) {
			SpiritAlert.createAlert("El email del contador est\u00e1 mal escrito !!", SpiritAlert.WARNING);
			getTxtEmailContador().grabFocus();
			return false;
		}
		
		if ("".equals(strRucContador) || strRucContador == null) {
			SpiritAlert.createAlert("Debe ingresar el ruc del contador !!", SpiritAlert.WARNING);
			getTxtRucContador().grabFocus();
			return false;
		}
		
		if (strRucContador.trim().length() < MAX_LONGITUD_RUC_CONTADOR) {
			SpiritAlert.createAlert("El RUC del contador debe tener " + MAX_LONGITUD_RUC_CONTADOR + " d\u00edgitos !!", SpiritAlert.WARNING);
			getTxtRucContador().grabFocus();
			return false;
		}
		
		if (!ValidarIdentificacion.esNumeroIdentificacionValido("RU", strRucContador)) {
			SpiritAlert.createAlert("El número de identificaci\u00f3n del representante legal no es v\u00e1lido!!!", SpiritAlert.WARNING);
			getTxtRucContador().grabFocus();
			return false;
		}
		
		if (getCmbTipoIdentificacionRepresentante().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de identificaci\u00f3n del representante legal !!", SpiritAlert.WARNING);
			getCmbTipoIdentificacionRepresentante().grabFocus();
			return false;
		}
		
		if ("".equals(strNumeroIdentificacion) || strNumeroIdentificacion == null) {
			SpiritAlert.createAlert("Debe ingresar el n\u00famero de identificaci\u00f3n del representante legal !!", SpiritAlert.WARNING);
			getTxtNumeroIdentificacion().grabFocus();
			return false;
		}
		
		TipoIdentificacionIf tipoIdentificacionIf = (TipoIdentificacionIf) getCmbTipoIdentificacionRepresentante().getSelectedItem();
		
		if (tipoIdentificacionIf.getCodigo().equals("CE")) {
			if (!ValidarIdentificacion.esNumeroIdentificacionValido("CE", strNumeroIdentificacion)) {
				SpiritAlert.createAlert("El n\u00famero de identificaci\u00f3n del representante legal no es v\u00e1lido!!!", SpiritAlert.WARNING);
				getTxtNumeroIdentificacion().grabFocus();
				return false;
			}
		}
		
		return true;
	}
	
	private void cargarTabla() {
		try {			
			Collection empresa = null;
			
			if (usuario.getTipousuario().equals("S"))
				empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresaList();
			else if (usuario.getTipousuario().equals("A"))
				empresa = SessionServiceLocator.getEmpresaSessionService().findEmpresaById(Parametros.getIdEmpresa());
			Iterator empresaIterator = empresa.iterator();
			
			if (!getEmpresaVector().isEmpty())
				getEmpresaVector().removeAllElements();
			
			while (empresaIterator.hasNext()) {
				EmpresaIf empresaIf = (EmpresaIf) empresaIterator.next();
				tableModel = (DefaultTableModel) getTblEmpresa().getModel();
				Vector<String> fila = new Vector<String>();
				getEmpresaVector().add(empresaIf);
				agregarColumnasTabla(empresaIf, fila);
				tableModel.addRow(fila);
			}
			
			Utilitarios.scrollToCenter(getTblEmpresa(), empresa, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(EmpresaIf empresaIf, Vector<String> fila){
		fila.add(empresaIf.getCodigo());
		fila.add(empresaIf.getNombre());
		fila.add(empresaIf.getRuc());
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtRuc().setText("");
		this.getTxtLogo().setText("");
		this.getTxtWeb().setText("");
		this.getTxtEmailContador().setText("");
		this.getTxtRucContador().setText("");
		this.getCmbTipoIdentificacionRepresentante().setSelectedItem(null);
		this.getTxtNumeroIdentificacion().setText("");
		
		// Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblEmpresa().getModel();
		
		for(int i= this.getTblEmpresa().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtRuc().setEnabled(true);
		getTxtLogo().setEditable(false);
		getTxtWeb().setEnabled(true);
		if (usuario.getTipousuario().equals("S"))
			getBtnCargarLogo().setEnabled(false);
		else if (usuario.getTipousuario().equals("A"))
			getBtnCargarLogo().setEnabled(true);
		getTxtCodigo().grabFocus();
		clean();
		cargarTabla();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtRuc().setEnabled(true);
		getTxtWeb().setEnabled(true);
		if (usuario.getTipousuario().equals("S"))
			getBtnCargarLogo().setEnabled(false);
		else if (usuario.getTipousuario().equals("A"))
			getBtnCargarLogo().setEnabled(true);
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			FileFilter filterJPG = new ExtensionFileFilter("jpg", new String[] {"JPG", "JPEG"});
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif", new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);

			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("fileChooser.getSelectedFile():" + fileChooser.getSelectedFile());
				
				/**
				 * valido que bot\u00f3n ha sido presionado y le asigno al
				 * correspondiente textbox el path del archivo que haya
				 * seleccionado
				 */

				if ((actionEvent.getSource() == getBtnCargarLogo()))
		        	  getTxtLogo().setText(fileChooser.getSelectedFile().toString());

				/**
				 * ejecuto el archivo con su respectivo programa para poder ser
				 * previsualizado
				 */
				try {
					Runtime rt = Runtime.getRuntime();
					String filename = selectedFile.getName().toLowerCase();
					filename.replaceAll("\\\\", "\\\\\\\\");
					String si = "Si"; 
	    	        String no = "No"; 
	    	        Object[] options ={si,no}; 
	    			int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmaci\u00f3n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION)
						abrirArchivo(rt, filename);
				} catch (IOException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			} else if (status == JFileChooser.CANCEL_OPTION) {

			}
	     }		
	};
	
	private void abrirArchivo(Runtime rt, String filename) throws IOException {
		if (filename.endsWith(".doc"))
			rt.exec(PATH_MICROSOFT_WORD + filename + "\" ");
		else if (filename.endsWith(".xls"))
			rt.exec(PATH_MICROSOFT_EXCEL + filename + "\" ");
		else if (filename.endsWith(".ppt"))
			rt.exec(PATH_MICROSOFT_POWERPOINT + filename+ "\" ");
		else if (filename.endsWith(".gif")|| filename.endsWith(".jpg")
				|| filename.endsWith(".jpeg") || filename.endsWith(".bmp")
				|| filename.endsWith(".tif") || filename.endsWith(".png"))
			rt.exec(PATH_MICROSOFT_PAINT + filename + "\" ");
		else 
			SpiritAlert.createAlert("No hay aplicaci\u00f3n asociada para abrir el archivo", SpiritAlert.WARNING);
	}

	public Vector<EmpresaIf> getEmpresaVector() {
		return this.empresaVector;
	}
	
	public void setEmpresaVector(Vector<EmpresaIf> empresaVector) {
		this.empresaVector = empresaVector;
	}
	
	public int getEmpresaSeleccionada() {
		return this.empresaSeleccionada;
	}
	
	public void setEmpresaSeleccionada(int empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	
	public EmpresaIf getEmpresaActualizadaIf() {
		return this.empresaActualizadaIf;
	}
	
	public void setEmpresaActualizadaIf(EmpresaIf empresaActualizadaIf) {
		this.empresaActualizadaIf = empresaActualizadaIf;
	}
	
	 
	 
}