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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import com.spirit.general.entity.ParametroEmpresaData;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPParametroEmpresa;
import com.spirit.general.session.ParametroEmpresaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ParametroEmpresaModel extends JPParametroEmpresa {

	private static final long serialVersionUID = 427702946939302608L;
	
	private Vector<ParametroEmpresaIf> parametroEmpresaVector = new Vector<ParametroEmpresaIf>();
	private DefaultTableModel tableModel;
	private int parametroEmpresaSeleccionado;
	private ParametroEmpresaIf parametroEmpresaIf;
	private String mascara = "";
	private KeyListener actualKeyListener;
	private boolean banderaEditar = true;
	
	private static final int MAX_LONGITUD_MASCARA_CARACTER = 1;
	private static final int MAX_LONGITUD_MASCARA_STRING = 50;
	private static final int MAX_LONGITUD_CODIGO = 30;
	private static final int MAX_LONGITUD_DESCRIPCION = 100;
	
	private Map<Long,TipoParametroIf> mapaTipoParametro = null;
		
	public ParametroEmpresaModel(){
		anchoColumnasTabla();
		initKeyListeners();
		cargarMapaTipoParametros();
		this.showSaveMode();
		getTblParametroEmpresaDetalles().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getCmbTipoParametro().addActionListener(comboTipoParametroListener);
		getTblParametroEmpresaDetalles().addMouseListener(oMouseAdapterTblParametroEmpresa);
		getTblParametroEmpresaDetalles().addKeyListener(oKeyAdapterTblParametroEmpresa);
		setSorterTable(getTblParametroEmpresaDetalles());
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblParametroEmpresaDetalles().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		
		anchoColumna = getTblParametroEmpresaDetalles().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		    
		anchoColumna = getTblParametroEmpresaDetalles().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
		    
		anchoColumna = getTblParametroEmpresaDetalles().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
	}
	
	ActionListener comboTipoParametroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			mascara = ((TipoParametroIf)getCmbTipoParametro().getSelectedItem()).getMascara();
			getTxtMascara().setText(mascara);
			getTxtMascara().setEnabled(false);
			
			//Formateo el campo valor para que solo reciba este tipo de parametro
			String tipo = ((TipoParametroIf)getCmbTipoParametro().getSelectedItem()).getTipo();
			if(!tipo.equals("F")){
				getTxtValor().removeKeyListener(actualKeyListener);
				if (!banderaEditar) 
					getTxtValor().setText("");
				getLblValor().setVisible(true);
				getTxtValor().setVisible(true);
				getLblValor1().setVisible(false);
				getCmbFecha().setVisible(false);
				if(tipo.equals("C")) actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_CARACTER);
				else if(tipo.equals("S")) actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_STRING, 0);
				else if(tipo.equals("N")) actualKeyListener = new NumberTextFieldDecimal();
				getTxtValor().addKeyListener(actualKeyListener);
			}
			else if(tipo.equals("F")){
				getTxtValor().removeKeyListener(actualKeyListener);
				if (!banderaEditar) 
					getTxtValor().setText("");
				getLblValor().setVisible(false);
				getTxtValor().setVisible(false);
				getLblValor1().setVisible(true);
				getCmbFecha().setVisible(true);
				Calendar calendar = new GregorianCalendar();
				Calendar hoy = Calendar.getInstance();
				calendar.setTime(hoy.getTime());
				getCmbFecha().setCalendar(calendar);
			}
			banderaEditar = false;
		}
	};	
	
	MouseListener oMouseAdapterTblParametroEmpresa = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblParametroEmpresa = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			//Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setParametroEmpresaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
				parametroEmpresaIf = (ParametroEmpresaIf)  getParametroEmpresaVector().get(getParametroEmpresaSeleccionado());		
				getTxtCodigo().setText(parametroEmpresaIf.getCodigo());
				getTxtDescripcion().setText(parametroEmpresaIf.getDescripcion());
				TipoParametroIf tp = mapaTipoParametro.get(parametroEmpresaIf.getTipoparametroId());
				getCmbTipoParametro().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoParametro(), tp.getId()));
				
				if (getCmbTipoParametro().getSelectedIndex() >= 0)
					banderaEditar = true;
				
				getCmbTipoParametro().repaint();
				
				if(getTxtValor().isVisible()){
					getTxtValor().setText(parametroEmpresaIf.getValor());
					getTxtValor().repaint();
				} else {
					Calendar calendar = new GregorianCalendar();
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(parametroEmpresaIf.getValor());
					calendar.setTime(date);
					getCmbFecha().setCalendar(calendar);
					getCmbFecha().repaint();
				}
				
				showUpdateMode();
			}
		} catch (ParseException ep) {
			ep.printStackTrace();
			SpiritAlert.createAlert("Error general al seleccionar Parameto de Empresa !!", SpiritAlert.ERROR);
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				ParametroEmpresaData parametroEmpresa = new ParametroEmpresaData();
				
				parametroEmpresa.setCodigo(getTxtCodigo().getText());
				parametroEmpresa.setDescripcion(getTxtDescripcion().getText());
				
				if(getTxtValor().isVisible()) parametroEmpresa.setValor(getTxtValor().getText());
				else parametroEmpresa.setValor(Utilitarios.getStringDateFromDate(getCmbFecha().getDate()));
								
				TipoParametroIf tipoParametroIf = (TipoParametroIf) getCmbTipoParametro().getSelectedItem();
				parametroEmpresa.setTipoparametroId(tipoParametroIf.getId());
				
				parametroEmpresa.setEmpresaId(Parametros.getIdEmpresa());
					
				SessionServiceLocator.getParametroEmpresaSessionService().addParametroEmpresa(parametroEmpresa);
				SpiritAlert.createAlert("Parámetro de Empresa guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("parametroEmpresa",null);
				
				if (tipoParametroIf.getCodigo().equals("DISCOMEDIO"))
					Parametros.setUrlCarpetaServidor(parametroEmpresa.getValor());
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		String strValor = getTxtValor().getText();
		
		Collection parametroEmpresa = null;
		boolean codigoRepetido = false;
		
		try {
			parametroEmpresa = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator parametroEmpresaIt = parametroEmpresa.iterator();
		
		while (parametroEmpresaIt.hasNext()) {
			ParametroEmpresaIf parametroEmpresaIf = (ParametroEmpresaIf) parametroEmpresaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(parametroEmpresaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(parametroEmpresaIf.getCodigo())) 
					if (getParametroEmpresaActualizadoIf().getId().equals(parametroEmpresaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Par\u00e1metro Empresa est\u00e1 duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if (("".equals(getTxtCodigo().getText())) || (getTxtCodigo().getText() == null)) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Par\u00e1metro de Empresa !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if (("".equals(getTxtDescripcion().getText())) || (getTxtDescripcion().getText() == null)) {
			SpiritAlert.createAlert("Debe ingresar una Descripci\u00f3n para el Par\u00e1metro de Empresa !!",
					SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}
		if (getCmbTipoParametro().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Tipo Par\u00e1metro para el Par\u00e1metro de Empresa !!",SpiritAlert.WARNING);
			getCmbTipoParametro().grabFocus();
			return false;
		}
		if ((("".equals(strValor)) || (strValor == null)) && getTxtValor().isVisible()) {
			SpiritAlert.createAlert("Debe ingresar un Valor para el Par\u00e1metro de Empresa !!",
					SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
				
		return true;
	}

	public void delete() {
		try {
			ParametroEmpresaIf parametroEmpresaIf = (ParametroEmpresaIf) getParametroEmpresaVector().get(getParametroEmpresaSeleccionado());
			SessionServiceLocator.getParametroEmpresaSessionService().deleteParametroEmpresa(parametroEmpresaIf.getId());
			SpiritAlert.createAlert("Parámetro de Empresa eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("parametroEmpresa",null);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("El Parámetro de Empresa tiene datos referenciados y no puede ser eliminado!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setParametroEmpresaActualizadoIf((ParametroEmpresaIf) getParametroEmpresaVector().get(getParametroEmpresaSeleccionado()));
				
				getParametroEmpresaActualizadoIf().setCodigo(getTxtCodigo().getText());
				getParametroEmpresaActualizadoIf().setDescripcion(getTxtDescripcion().getText());
				
				if(getTxtValor().isVisible()) getParametroEmpresaActualizadoIf().setValor(getTxtValor().getText());
				else getParametroEmpresaActualizadoIf().setValor(Utilitarios.getStringDateFromDate(getCmbFecha().getDate()));
				
				TipoParametroIf tipoParametroIf = (TipoParametroIf) getCmbTipoParametro().getSelectedItem();
				getParametroEmpresaActualizadoIf().setTipoparametroId(tipoParametroIf.getId());
				getParametroEmpresaActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getParametroEmpresaSessionService().saveParametroEmpresa(getParametroEmpresaActualizadoIf());
				getParametroEmpresaVector().setElementAt(getParametroEmpresaActualizadoIf(), getParametroEmpresaSeleccionado());
				
				if (tipoParametroIf.getCodigo().equals("DISCOMEDIO"))
					Parametros.setUrlCarpetaServidor(getParametroEmpresaActualizadoIf().getValor());
				
				SpiritAlert.createAlert("Parámetro de Empresa actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("parametroEmpresa",null);
				
				setParametroEmpresaActualizadoIf(null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtDescripcion().setText("");
		getTxtValor().setText("");
		getCmbTipoParametro().removeAllItems();
		
		//Vacio la tabla
		limpiarTabla(getTblParametroEmpresaDetalles());
		
	}

	public void showFindMode() {
		showSaveMode();	
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEditable(true);
		clean();
		
		//cargo combo de tipo parametro
		cargarComboTipoParametro();
		
		//Cargo la máscara del tipo parametro escogido 
		if ( getCmbTipoParametro().getSelectedItem() != null ){
			mascara = ((TipoParametroIf)getCmbTipoParametro().getSelectedItem()).getMascara();
			getTxtMascara().setText(mascara);
			getTxtMascara().setEnabled(false);
		}
		
		//Seteo formato de combo de fecha
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFecha().setEditable(false);
		
		//Formateo el campo valor para que solo reciba este tipo de parametro
		if ( getCmbTipoParametro().getSelectedItem() != null ){
			String tipo = ((TipoParametroIf)getCmbTipoParametro().getSelectedItem()).getTipo();
			if(!tipo.equals("F")){
				getTxtValor().removeKeyListener(actualKeyListener);
				getTxtValor().setText("");
				getLblValor().setVisible(true);
				getTxtValor().setVisible(true);
				getLblValor1().setVisible(false);
				getCmbFecha().setVisible(false);
				if(tipo.equals("C")) actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_CARACTER);
				else if(tipo.equals("S")) actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_STRING, 0);
				else if(tipo.equals("N")) actualKeyListener = new NumberTextFieldDecimal();
				getTxtValor().addKeyListener(actualKeyListener);
			}
			else if(tipo.equals("F")){
				getTxtValor().removeKeyListener(actualKeyListener);
				getTxtValor().setText("");
				getTxtValor().setText("");
				getLblValor().setVisible(false);
				getTxtValor().setVisible(false);
				getLblValor1().setVisible(true);
				getCmbFecha().setVisible(true);
				Calendar calendar = new GregorianCalendar();
				Calendar hoy = Calendar.getInstance();
				calendar.setTime(hoy.getTime());
				getCmbFecha().setCalendar(calendar);
			}
		}
		
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarComboTipoParametro(){
		SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(GeneralFinder.findTipoParametro(Parametros.getIdEmpresa()));
		getCmbTipoParametro().setModel(cmbModel);
		if (getCmbTipoParametro().getItemCount() > 0)
			getCmbTipoParametro().setSelectedIndex(0);
	}
	
	private void cargarTabla() {
		try {
			Collection<ParametroEmpresaIf> parametroEmpresa = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByEmpresaId(Parametros.getIdEmpresa());
			
			Collections.sort((ArrayList<ParametroEmpresaIf>)parametroEmpresa, ordenadorParametros);
			
			if(!getParametroEmpresaVector().isEmpty()){
				getParametroEmpresaVector().removeAllElements();
			}
			tableModel = (DefaultTableModel) getTblParametroEmpresaDetalles().getModel();
			for (ParametroEmpresaIf parametroEmpresaIf : parametroEmpresa) {
				
				Vector<String> fila = new Vector<String>();
				getParametroEmpresaVector().add(parametroEmpresaIf);
				
				agregarColumnasTabla(parametroEmpresaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblParametroEmpresaDetalles(), parametroEmpresa, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<ParametroEmpresaIf> ordenadorParametros = new Comparator<ParametroEmpresaIf>(){
		public int compare(ParametroEmpresaIf pe1, ParametroEmpresaIf pe2) {
			String tp1 = mapaTipoParametro.get(pe1.getTipoparametroId()).getNombre();
			String tp2 = mapaTipoParametro.get(pe2.getTipoparametroId()).getNombre();
			return tp1.compareTo(tp2) ;
		}
	};
	
	private void agregarColumnasTabla(ParametroEmpresaIf parametroEmpresaIf, Vector<String> fila){

		TipoParametroIf tipoParametroIf = mapaTipoParametro.get(parametroEmpresaIf.getTipoparametroId());
		fila.add(parametroEmpresaIf.getCodigo());
		fila.add(parametroEmpresaIf.getDescripcion());
		fila.add(tipoParametroIf!=null?tipoParametroIf.getNombre():"");
		fila.add(parametroEmpresaIf.getValor());

	}
	
	private void cargarMapaTipoParametros(){
		
		Collection<TipoParametroIf> tiposParametros;
		try {
			tiposParametros = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(Parametros.getIdEmpresa());
			mapaTipoParametro = null;
			mapaTipoParametro = new HashMap<Long, TipoParametroIf>();
			for ( TipoParametroIf tp : tiposParametros ){
				mapaTipoParametro.put(tp.getId(), tp);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Tipos de Parametros !!", SpiritAlert.ERROR);
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEditable(false);
	}
	
	public void refresh(){
		cargarMapaTipoParametros();
		cargarComboTipoParametro();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getParametroEmpresaVector() {
		return parametroEmpresaVector;
	}
	
	public void setParametroEmpresaVector(Vector parametroEmpresaVec) {
		parametroEmpresaVector = parametroEmpresaVec;
	}
	
	public int getParametroEmpresaSeleccionado() {
		return parametroEmpresaSeleccionado;
	}
	
	public void setParametroEmpresaSeleccionado(int selectedParametroEmpresa) {
		parametroEmpresaSeleccionado = selectedParametroEmpresa;
	}
	
	public ParametroEmpresaIf getParametroEmpresaActualizadoIf() {
		return parametroEmpresaIf;
	}
	
	public void setParametroEmpresaActualizadoIf(ParametroEmpresaIf parametroEmpresaIf) {
		this.parametroEmpresaIf = parametroEmpresaIf;
	}
 
}
