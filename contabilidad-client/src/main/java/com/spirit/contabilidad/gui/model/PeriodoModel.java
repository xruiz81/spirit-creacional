package com.spirit.contabilidad.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.PeriodoData;
import com.spirit.contabilidad.entity.PeriodoDetalleData;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.gui.panel.JPPeriodo;
import com.spirit.contabilidad.handler.EstadoPeriodo;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PeriodoModel extends JPPeriodo {
	private static final long serialVersionUID = 4304540185017678944L;
	PeriodoIf periodoIf;
	private Vector periodoVector = new Vector();
	private DefaultTableModel tableModel;
	private int periodoSeleccionado;
	int mesInicioDateSpinner;
	int anioInicioDateSpinner;
	int mesFinDateSpinner;
	int anioFinDateSpinner;
	private List<PeriodoDetalleIf> periodoDetalleColeccion = new ArrayList<PeriodoDetalleIf>();
	int totalMesesPeriodo;
	int mes = 0;
	int anio = 0;
	PeriodoDetalleIf periodoDetalleActivo;
	private static PeriodoIf periodo;
	Date fechaHoy = new Date();
	
	/*private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_ESTADO_PARCIAL = "PARCIAL";
	private static final String NOMBRE_ESTADO_CERRADO = "CERRADO";
	public static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	public static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);
	public static final String ESTADO_PARCIAL = NOMBRE_ESTADO_PARCIAL.substring(0, 1);
	public static final String ESTADO_CERRADO = NOMBRE_ESTADO_CERRADO.substring(0, 1);*/
	private static final int MAX_LONGITUD_CODIGO = 8;

	public PeriodoModel() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		//getCmbFechaInicio().setFormat(Utilitarios.setFechaSinDiaUppercase());
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		//getCmbFechaFin().setFormat(Utilitarios.setFechaSinDiaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);
		getTblPeriodos().getColumnModel().setColumnSelectionAllowed(false);
		showSaveMode();
		setSorterTable(getTblPeriodos());
		getTblPeriodos().addMouseListener(oMouseAdapterTblPeriodos);
		getTblPeriodos().addKeyListener(oKeyAdapterTblPeriodos);
		new HotKeyComponent(this);
	}
	
	MouseListener oMouseAdapterTblPeriodos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enabledSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblPeriodos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enabledSelectedRowForUpdate(evt);
		}
    };
    
    private void enabledSelectedRowForUpdate(ComponentEvent evt) {
		try {
			//Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setPeriodoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				periodoIf = (PeriodoIf)  getPeriodoVector().get(getPeriodoSeleccionado());
				Long ultimoPeriodoOrden = getOrdenUltimoPeriodo();
				
				if (periodoIf.getOrden().equals(ultimoPeriodoOrden)) {
					getTxtCodigo().setText(periodoIf.getCodigo());
					getCmbFechaInicio().setDate(periodoIf.getFechaini());
					getCmbFechaFin().setDate(periodoIf.getFechafin());
					setUpdateMode();
				} else {
					SpiritAlert.createAlert("Sólo puede editar el último ejercicio contable!", SpiritAlert.WARNING);
					showSaveMode();
				}
				
				Collection periodosDetalle = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(periodoIf.getId());
				Iterator periodosDetalleIt = periodosDetalle.iterator();
				
				while(periodosDetalleIt.hasNext()){
					PeriodoDetalleIf periodoDetalleIf = (PeriodoDetalleIf) periodosDetalleIt.next();
					//if (!periodoDetalleIf.getStatus().equals(ESTADO_INACTIVO))
					if (!periodoDetalleIf.getStatus().equals(EstadoPeriodo.INACTIVO.getLetra()))
						setPeriodoDetalleActivo(periodoDetalleIf);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
    
    private Long getOrdenUltimoPeriodo() throws GenericBusinessException {
    	Long ultimoPeriodoOrden = 0L;
    	try {
    		Collection ultimoPeriodo = SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(Parametros.getIdEmpresa());
    		Iterator ultimoPeriodoIt = ultimoPeriodo.iterator();
    		
    		while(ultimoPeriodoIt.hasNext()){
    			PeriodoIf ultimoPeriodoIf = (PeriodoIf) ultimoPeriodoIt.next();
    			if(ultimoPeriodoIf.getOrden() > ultimoPeriodoOrden)
    				ultimoPeriodoOrden = ultimoPeriodoIf.getOrden();				
    		}
    	} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return ultimoPeriodoOrden;
	}

	private ArrayList getModel(ArrayList listaPeriodo) {
		ArrayList data = new ArrayList();
		Iterator it = listaPeriodo.iterator();
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PeriodoIf bean = (PeriodoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getStatus());
			data.add(fila);
		}
		return data;
	}

	public void save() {
		try {
			if (validateFields()) {
				PeriodoIf periodo = registrarPeriodo();
				generarPeriodoDetalleColleccion();
				SessionServiceLocator.getPeriodoSessionService().procesarPeriodo(periodo, periodoDetalleColeccion);
				periodoDetalleColeccion.clear();
				showSaveMode();
				SpiritAlert.createAlert("Ejercicio Contable guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (Exception e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Ocurrió un error al guardar el Ejercicio Contable", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				PeriodoIf periodoIf = (PeriodoIf) getPeriodoVector().get(getPeriodoSeleccionado());
				SessionServiceLocator.getPeriodoSessionService().eliminarPeriodo(periodoIf.getId());
				PeriodoIf periodo = registrarPeriodo();
				generarPeriodoDetalleColleccion();
				SessionServiceLocator.getPeriodoSessionService().procesarPeriodo(periodo, periodoDetalleColeccion);
				periodoDetalleColeccion.clear();
				showSaveMode();
				SpiritAlert.createAlert("Ejercicio Contable actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (Exception e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Ocurrio un error al actualizar el Ejercicio Contable", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			PeriodoIf periodoIf = (PeriodoIf) getPeriodoVector().get(getPeriodoSeleccionado());
			SessionServiceLocator.getPeriodoSessionService().eliminarPeriodo(periodoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Ejercicio Contable eliminado con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("No se puede eliminar el Ejercicio Contable", SpiritAlert.ERROR);
			e.printStackTrace();
			showSaveMode();
			
		}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void generarPeriodoDetalleColleccion() {
		int mesActual = fechaHoy.getMonth() + 1;
		int anioActual = fechaHoy.getYear() + 1900;
		mes = mesInicioDateSpinner+1;
		anio = anioInicioDateSpinner+1900;
		periodoDetalleColeccion = new ArrayList<PeriodoDetalleIf>();
						
		for(int i=0; i < getTotalMesesPeriodo(); i++){
			PeriodoDetalleData dataDetalle = new PeriodoDetalleData();
			
			dataDetalle.setAnio(((Integer)anio).toString());
			if(mes > 9)
				dataDetalle.setMes(((Integer)mes).toString());
			else
				dataDetalle.setMes("0"+((Integer)mes).toString());
			
			if (mes < mesActual && anio <= anioActual)
				dataDetalle.setStatus(EstadoPeriodo.CERRADO.getLetra());
				//dataDetalle.setStatus(ESTADO_CERRADO);
			else
				dataDetalle.setStatus(EstadoPeriodo.INACTIVO.getLetra());
				//dataDetalle.setStatus(ESTADO_INACTIVO);
			
			periodoDetalleColeccion.add(dataDetalle);
			
			if(mes == 12){
				mes = 0;
				anio = anioFinDateSpinner+1900;
			}
			mes = mes + 1;
		}
	}
	
	private PeriodoIf registrarPeriodo() throws GenericBusinessException {
		PeriodoData data = new PeriodoData();
		mesInicioDateSpinner = getCmbFechaInicio().getDate().getMonth();
		anioInicioDateSpinner = getCmbFechaInicio().getDate().getYear();
		mesFinDateSpinner = getCmbFechaFin().getDate().getMonth();
		anioFinDateSpinner = getCmbFechaFin().getDate().getYear();
		
		data.setCodigo(this.getTxtCodigo().getText());
		data.setEmpresaId(Parametros.getIdEmpresa());
		data.setFechaini(new java.sql.Date(anioInicioDateSpinner, mesInicioDateSpinner, 1));
		
		try {
			data.setFechafin(Utilitarios.fechaHoy(mesFinDateSpinner, anioFinDateSpinner));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//data.setStatus(ESTADO_INACTIVO);
		data.setStatus(EstadoPeriodo.INACTIVO.getLetra());
		Long ultimoOrden = getOrdenUltimoPeriodo() + 1L;
		data.setOrden(ultimoOrden);
		return data;
	}
	
	private PeriodoIf registrarPeriodoForUpdate() {
		PeriodoIf periodoIf = (PeriodoIf) getPeriodoVector().get(getPeriodoSeleccionado());
		//Establezco una instancia de java.util.date para cada combobox
		mesInicioDateSpinner = getCmbFechaInicio().getDate().getMonth();
		anioInicioDateSpinner = getCmbFechaInicio().getDate().getYear();
		mesFinDateSpinner = getCmbFechaFin().getDate().getMonth();
		anioFinDateSpinner = getCmbFechaFin().getDate().getYear();
		periodo.setCodigo(getTxtCodigo().getText());
		periodo.setEmpresaId(Parametros.getIdEmpresa());
		periodo.setFechaini(new java.sql.Date(anioInicioDateSpinner, mesInicioDateSpinner, 1));
				
		try {
			periodo.setFechafin(Utilitarios.fechaHoy(mesFinDateSpinner, anioFinDateSpinner));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//periodo.setStatus(ESTADO_INACTIVO);
		periodo.setStatus(EstadoPeriodo.INACTIVO.getLetra());
		periodo.setOrden(periodoIf.getOrden());
		
		return periodo;
	}
	
	private void generarPeriodoDetalleColleccionUpdate() {
		int detalle = 0;
		periodo = (PeriodoIf) getPeriodoVector().get(getPeriodoSeleccionado());
		
		try {
			Collection periodoDetalle = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(periodo.getId());
			Iterator periodoDetalleIt = periodoDetalle.iterator();
			
			while(periodoDetalleIt.hasNext()){
				PeriodoDetalleIf periodoDetalleIf = (PeriodoDetalleIf) periodoDetalleIt.next();
				PeriodoDetalleData dataDetalle = new PeriodoDetalleData();
				dataDetalle.setId(periodoDetalleIf.getId());
				dataDetalle.setAnio(periodoDetalleIf.getAnio());
				dataDetalle.setMes(periodoDetalleIf.getMes());
				dataDetalle.setStatus(periodoDetalleIf.getStatus());
				periodoDetalleColeccion.add(dataDetalle);
				detalle = detalle + 1;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		Collection periodos = null;
		boolean codigoRepetido = false;
		
		try {
			periodos = SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator periodosIt = periodos.iterator();
		
		while (periodosIt.hasNext()) {
			PeriodoIf periodosIf = (PeriodoIf) periodosIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(periodosIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(periodosIf.getCodigo())) 
					if (periodoIf.getId().equals(periodosIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Ejercicio Contable está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		int maxAnioGuardado = 0;
		int maxMesGuardado = 0;
		Long ultimoOrdenPeriodo = 0L;
		
		try {
			ultimoOrdenPeriodo = getOrdenUltimoPeriodo();
			if (ultimoOrdenPeriodo > 0L) {
				if (getMode() == 2) {					
					//original: PeriodoIf ultimoPeriodo = (PeriodoIf)SessionServiceLocator.getPeriodoSessionService().findPeriodoByOrden(ultimoOrdenPeriodo-1L).iterator().next();
					PeriodoIf ultimoPeriodo=null;				 
					Iterator periodosColecIt = SessionServiceLocator.getPeriodoSessionService().findPeriodoByOrden(ultimoOrdenPeriodo-1L).iterator();
					if(periodosIt.hasNext()){
						 ultimoPeriodo = (PeriodoIf) periodosColecIt.next();
					}
					if(ultimoPeriodo!=null)
					{					
					maxAnioGuardado = ultimoPeriodo.getFechafin().getYear();
					maxMesGuardado = ultimoPeriodo.getFechafin().getMonth();
					}					
				} else {
					PeriodoIf ultimoPeriodo = (PeriodoIf)SessionServiceLocator.getPeriodoSessionService().findPeriodoByOrden(ultimoOrdenPeriodo).iterator().next();
					maxAnioGuardado = ultimoPeriodo.getFechafin().getYear();
					maxMesGuardado = ultimoPeriodo.getFechafin().getMonth();
				}
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mesInicioDateSpinner = getCmbFechaInicio().getDate().getMonth();
		anioInicioDateSpinner = getCmbFechaInicio().getDate().getYear();
		mesFinDateSpinner = getCmbFechaFin().getDate().getMonth();
		anioFinDateSpinner = getCmbFechaFin().getDate().getYear();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un codigo para el Ejercicio Contable !!", SpiritAlert.WARNING);
			return false;
		}
		
		if ((anioInicioDateSpinner > anioFinDateSpinner) || (anioInicioDateSpinner == anioFinDateSpinner && mesInicioDateSpinner > mesFinDateSpinner)) {
			SpiritAlert.createAlert("La fecha de inicio no puede ser mayor a la fecha de fin del Ejercicio Contable !!", SpiritAlert.WARNING);
			return false;
		}
		
		if (maxAnioGuardado > 0 && maxMesGuardado > 0) {
			if ((anioInicioDateSpinner < maxAnioGuardado) || (anioInicioDateSpinner == maxAnioGuardado && mesInicioDateSpinner <= maxMesGuardado)) {
				SpiritAlert.createAlert("El nuevo Ejercicio Contable no puede estar antes o cruzarse con el último !!", SpiritAlert.WARNING);
				return false;
			}
			
			if ((anioInicioDateSpinner > (maxAnioGuardado+1)) || (anioInicioDateSpinner == maxAnioGuardado && mesInicioDateSpinner > (maxMesGuardado+1))) {
				SpiritAlert.createAlert("El nuevo Ejercicio Contable debe comenzar a continuación del último !!", SpiritAlert.WARNING);
				return false;
			}
			
			if (anioInicioDateSpinner == (maxAnioGuardado+1) && mesInicioDateSpinner > 0) {
				SpiritAlert.createAlert("El nuevo Ejercicio Contable debe comenzar a continuación del último !!", SpiritAlert.WARNING);
				return false;
			}
		}
		
		int cuentaMesesInicio = 0;
		int cuentaMesesFin = 0;
		
		if(anioInicioDateSpinner == anioFinDateSpinner){
			setTotalMesesPeriodo(mesFinDateSpinner - mesInicioDateSpinner + 1);
		} else {
			cuentaMesesInicio = 12 - mesInicioDateSpinner;
			cuentaMesesFin = mesFinDateSpinner + 1;
			setTotalMesesPeriodo(cuentaMesesInicio + cuentaMesesFin);
		}
				
		if(anioFinDateSpinner > (anioInicioDateSpinner+1) || ((anioFinDateSpinner == anioInicioDateSpinner || anioFinDateSpinner == (anioInicioDateSpinner+1)) && getTotalMesesPeriodo() > 12)){
			SpiritAlert.createAlert("Un Ejercicio Contable máximo puede tener 12 meses !!", SpiritAlert.WARNING);
			return false;
		}
		
		if(getMode() == 2){
			if(getPeriodoDetalleActivo() != null){
				int anioActivo = Integer.parseInt(getPeriodoDetalleActivo().getAnio()) - 1900;
				int mesActivo = Integer.parseInt(getPeriodoDetalleActivo().getMes()) - 1;
				if((anioFinDateSpinner < anioActivo) || (anioFinDateSpinner == anioActivo && (mesFinDateSpinner < mesActivo || mesFinDateSpinner == mesActivo))){
					SpiritAlert.createAlert("No se puede actualizar, porque se eliminaría un mes ya Activado !!", SpiritAlert.WARNING);
					return false;
				}
			}
		}
		return true;
	}

	public void clean() {
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
		
		this.getTxtCodigo().setText("");
		
		DefaultTableModel model = (DefaultTableModel) getTblPeriodos().getModel();
		for(int i= this.getTblPeriodos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void refresh() {
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection periodo = SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(Parametros.getIdEmpresa());
			Iterator periodoIterator = periodo.iterator();
			if (!getPeriodoVector().isEmpty())
				getPeriodoVector().removeAllElements();
			
			while (periodoIterator.hasNext()) {
				PeriodoIf periodoIf = (PeriodoIf) periodoIterator.next();
				tableModel = (DefaultTableModel) getTblPeriodos().getModel();
				Vector<String> fila = new Vector<String>();
				getPeriodoVector().add(periodoIf);
				agregarColumnasTabla(periodoIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblPeriodos(), periodo, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(PeriodoIf periodoIf, Vector<String> fila){
		fila.add(periodoIf.getCodigo());
		fila.add(Utilitarios.getFechaUppercase(periodoIf.getFechaini()));
		fila.add(Utilitarios.getFechaUppercase(periodoIf.getFechafin()));
		
		/*if(periodoIf.getStatus().equals(ESTADO_ACTIVO))
			fila.add("ACTIVO");
		else if(periodoIf.getStatus().equals(ESTADO_PARCIAL))
			fila.add("PARCIAL");
		else if(periodoIf.getStatus().equals(ESTADO_CERRADO))
			fila.add("CERRADO");
		else if(periodoIf.getStatus().equals(ESTADO_INACTIVO))
			fila.add("INACTIVO");*/
		
		String letraEstado = periodoIf.getStatus();
		try {
			EstadoPeriodo periodo = EstadoPeriodo.getEstadoPeriodoByLetra(letraEstado);
			fila.add(periodo.toString());
		} catch (GenericBusinessException e) {
			fila.add("");
		}
	}

	public Vector getPeriodoVector() {
		return periodoVector;
	}
	
	public void setPeriodoVector(Vector periodoVec) {
		periodoVector = periodoVec;
	}
	
	public int getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}
	
	public void setPeriodoSeleccionado(int selectedPeriodo) {
		periodoSeleccionado = selectedPeriodo;
	}
	
	public PeriodoIf getPeriodoActualizadoIf() {
		return periodoIf;
	}
	
	public void setPeriodoActualizadoIf(PeriodoIf periodoIf) {
		this.periodoIf = periodoIf;
	}

	public int getTotalMesesPeriodo() {
		return totalMesesPeriodo;
	}

	public void setTotalMesesPeriodo(int totalMesesPeriodo) {
		this.totalMesesPeriodo = totalMesesPeriodo;
	}

	public PeriodoDetalleIf getPeriodoDetalleActivo() {
		return periodoDetalleActivo;
	}

	public void setPeriodoDetalleActivo(PeriodoDetalleIf periodoDetalleActivo) {
		this.periodoDetalleActivo = periodoDetalleActivo;
	}
	
	/*public int seleccionarPeriodoActivo(JComboBox cmbPeriodo) {
		for (int i=0; i<cmbPeriodo.getItemCount(); i++) {
			PeriodoIf periodoIf = (PeriodoIf) cmbPeriodo.getItemAt(i);
			if (periodoIf.getStatus().equals(ESTADO_ACTIVO))
				return i;
		}
		
		return -1;
	}*/
	
	public static void seleccionarPeriodoActivo(JComboBox cmbPeriodo) {
		for (int i=0; i<cmbPeriodo.getItemCount(); i++) {
			PeriodoIf periodoIf = (PeriodoIf) cmbPeriodo.getItemAt(i);
			//if (periodoIf.getStatus().equals(ESTADO_ACTIVO)) {
			if (periodoIf.getStatus().equals(EstadoPeriodo.ACTIVO.getLetra())) {
				cmbPeriodo.setSelectedIndex(i);
				cmbPeriodo.validate();
				cmbPeriodo.repaint();
				break;
			}
		}
	}
	 
}
