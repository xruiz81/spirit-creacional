package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoDetalleData;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPCierrePeriodo;
import com.spirit.contabilidad.handler.EstadoPeriodo;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.EachRowEditor;
import com.spirit.util.Utilitarios;

public class CierrePeriodoModel extends JPCierrePeriodo {

	private static final long serialVersionUID = -4906518593531711022L;
	
	private DefaultTableModel tableModel;
	private static PeriodoIf periodo;
	Vector<String> statusNuevos = new Vector<String>();
	Vector<Object> statusOriginales = new Vector<Object>();
	private List<PeriodoDetalleIf> periodoDetalleColeccion = new ArrayList<PeriodoDetalleIf>();
	Vector<Integer> cuentaStatus = new Vector<Integer>();
	String mesHoy;
	String anioHoy;
	Vector<Object> activado = new Vector<Object>();	
	Map cuentasMap = new HashMap();
	Map tiposCuentaMap = new HashMap();
	
	public CierrePeriodoModel(){
		getTxtPeriodo().setEditable(false);
		getTblPeriodoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showUpdateMode();
		getCmbPeriodo().addActionListener(comboPeriodoListener);
		new HotKeyComponent(this);
	}

	public void showSaveMode() {
		setUpdateMode();
		clean();
		cargarComboPeriodo();
		cuentasMap = mapearPlanCuentas();
		tiposCuentaMap = mapearTiposCuenta();
		getCmbPeriodo().setSelectedIndex(-1);
		getCmbPeriodo().repaint();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		clean();
		cargarComboPeriodo();
		cuentasMap = mapearPlanCuentas();
		tiposCuentaMap = mapearTiposCuenta();
		getCmbPeriodo().setSelectedIndex(-1);
		getCmbPeriodo().repaint();
	}
	
	private void cargarComboPeriodo(){
		List periodos = ContabilidadFinder.findPeriodo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPeriodo(),periodos);
		PeriodoModel.seleccionarPeriodoActivo(getCmbPeriodo());
	}
	
	ActionListener comboPeriodoListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			clean();
			periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
			if ( periodo != null ){
				/*if(periodo.getStatus().equals("I"))
					getTxtPeriodo().setText("INACTIVO");
				if(periodo.getStatus().equals("A"))
					getTxtPeriodo().setText("ACTIVO");
				if(periodo.getStatus().equals("C"))
					getTxtPeriodo().setText("CERRADO");
				if(periodo.getStatus().equals("P"))
					getTxtPeriodo().setText("PARCIAL");*/
				try {
					EstadoPeriodo estado = EstadoPeriodo.getEstadoPeriodoByLetra(periodo.getStatus());
					getTxtPeriodo().setText(estado.toString());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					getTxtPeriodo().setText("");
				}
			
				cargarTabla();
			}
		}
	};
	
	
	private void cargarTabla() {
		getTblPeriodoDetalle().setRowHeight(21);
		int numeroFila = 0;
		
		EachRowEditor rowEditor = new EachRowEditor(getTblPeriodoDetalle());
				
		try {
			periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
			
			Collection periodosDetalle = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(periodo.getId());
			Iterator periodosDetalleIterator = periodosDetalle.iterator();
			
			if(!getStatusOriginales().isEmpty()){
				getStatusOriginales().removeAllElements();
			}
			
			while (periodosDetalleIterator.hasNext()) {
				PeriodoDetalleIf periodoDetalleIf = (PeriodoDetalleIf) periodosDetalleIterator.next();
				
				getStatusOriginales().add(periodoDetalleIf);
				tableModel = (DefaultTableModel) getTblPeriodoDetalle().getModel();
				Vector<Object> fila = new Vector<Object>();
				
				fila.add(periodoDetalleIf.getAnio());
				
				if(periodoDetalleIf.getMes().equals("01") || periodoDetalleIf.getMes().equals("1"))
					fila.add("ENERO");
				if(periodoDetalleIf.getMes().equals("02") || periodoDetalleIf.getMes().equals("2"))
					fila.add("FEBRERO");
				if(periodoDetalleIf.getMes().equals("03") || periodoDetalleIf.getMes().equals("3"))
					fila.add("MARZO");
				if(periodoDetalleIf.getMes().equals("04") || periodoDetalleIf.getMes().equals("4"))
					fila.add("ABRIL");
				if(periodoDetalleIf.getMes().equals("05") || periodoDetalleIf.getMes().equals("5"))
					fila.add("MAYO");
				if(periodoDetalleIf.getMes().equals("06") || periodoDetalleIf.getMes().equals("6"))
					fila.add("JUNIO");
				if(periodoDetalleIf.getMes().equals("07") || periodoDetalleIf.getMes().equals("7"))
					fila.add("JULIO");
				if(periodoDetalleIf.getMes().equals("08") || periodoDetalleIf.getMes().equals("8"))
					fila.add("AGOSTO");
				if(periodoDetalleIf.getMes().equals("09") || periodoDetalleIf.getMes().equals("9"))
					fila.add("SEPTIEMPRE");
				if(periodoDetalleIf.getMes().equals("10"))
					fila.add("OCTUBRE");
				if(periodoDetalleIf.getMes().equals("11"))
					fila.add("NOVIEMBRE");
				if(periodoDetalleIf.getMes().equals("12"))
					fila.add("DICIEMBRE");
				
				
				DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoPeriodo.values());
				JComboBox comboBox = new JComboBox(modelo);
				
				/*comboBox.addItem("INACTIVO");
				comboBox.addItem("ACTIVO");
				comboBox.addItem("CERRADO");
				comboBox.addItem("PARCIAL");*/
				
				/*if(periodoDetalleIf.getStatus().equals("I"))
					fila.add("INACTIVO");
				if(periodoDetalleIf.getStatus().equals("A"))
					fila.add("ACTIVO");
				if(periodoDetalleIf.getStatus().equals("C"))
					fila.add("CERRADO");
				if(periodoDetalleIf.getStatus().equals("P"))
					fila.add("PARCIAL");*/
				String letraEstado = periodoDetalleIf.getStatus();
				EstadoPeriodo estado = EstadoPeriodo.getEstadoPeriodoByLetra(letraEstado);
				fila.add(estado);

				rowEditor.setEditorAt(numeroFila, new DefaultCellEditor(comboBox));
				tableModel.addRow(fila);
				numeroFila = numeroFila + 1;
			}
					
			getTblPeriodoDetalle().getColumn("Status").setCellEditor(rowEditor);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
		
	}
	
	/*private void agregarFilasTabla(PeriodoDetalleIf periodoDetalleIf, Vector<Object> fila, JComboBox cmbStatus){
		fila.add(periodoDetalleIf.getAnio());
		fila.add(periodoDetalleIf.getMes());
	}*/
	
	public Map mapearPlanCuentas() {
		Map cuentasMap = new HashMap();
		try {
			Collection cuentasColeccion = SessionServiceLocator.getCuentaSessionService().getCuentaList();
			Iterator cuentasIterator = cuentasColeccion.iterator();
			while (cuentasIterator.hasNext()) {
				CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasMap;
	}
	
	public Map mapearTiposCuenta() {
		Map tiposCuentaMap = new HashMap();
		try {
			Collection tiposCuentaColeccion = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
			Iterator tiposCuentaIterator = tiposCuentaColeccion.iterator();
			while (tiposCuentaIterator.hasNext()) {
				TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaIterator.next();
				tiposCuentaMap.put(tipoCuenta.getId(), tipoCuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposCuentaMap;
	}

	public void showFindMode() {
		setUpdateMode();
		clean();
		cargarComboPeriodo();
		getCmbPeriodo().setSelectedIndex(-1);
		getCmbPeriodo().repaint();
	}

	public void find() {
		setUpdateMode();
		getTxtPeriodo().setText("");
	}

	public void save() {
		setUpdateMode();
		getTxtPeriodo().setText("");
	}
	
	public Vector<Integer> contarStatus() throws GenericBusinessException{
		
		int activos = 0;
		int parciales = 0;
		int cerrados = 0;
		getCuentaStatus().removeAllElements();
		
		for(int i=0; i<getStatusNuevos().size(); i++){
			String letraEstado = getStatusNuevos().get(i);
			EstadoPeriodo estado = EstadoPeriodo.getEstadoPeriodoByLetra(letraEstado);
			//if(getStatusNuevos().get(i).equals("A")){
			if(estado == EstadoPeriodo.ACTIVO){
				activos = activos + 1;
			}
			//if(getStatusNuevos().get(i).equals("P")){
			if(estado == EstadoPeriodo.PARCIAL){
				parciales = parciales + 1;
			}
			//if(getStatusNuevos().get(i).equals("C")){
			if(estado == EstadoPeriodo.CERRADO){
				cerrados = cerrados + 1;
			}
		}
		getCuentaStatus().add(activos);
		getCuentaStatus().add(parciales);
		getCuentaStatus().add(cerrados);
		
		return getCuentaStatus();
	}
	
	/*private PeriodoIf registrarPeriodo() throws GenericBusinessException {
		
		PeriodoData data = new PeriodoData();
		
		periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
		
		data.setCodigo(periodo.getCodigo());
		data.setEmpresaId(periodo.getEmpresaId());
		data.setFechaini(periodo.getFechaini());
		data.setFechafin(periodo.getFechafin());
		data.setOrden(periodo.getOrden());
		
		if(contarStatus().get(0) > 0)
			data.setStatus("A");
		else if(contarStatus().get(1) > 0)
			data.setStatus("P");
		else if(contarStatus().get(2) > 0)
			data.setStatus("C");
		else 
			data.setStatus("I");
							
		return data;
	}*/
	
	private void generarPeriodoDetalleColleccionUpdate() {
		String anio = "";
		String mes = "";
		int detalle = 0;
		periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
		
		try {
			Collection periodoDetalle = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(periodo.getId());
			Iterator periodoDetalleIt = periodoDetalle.iterator();
			
			while(periodoDetalleIt.hasNext()){
				PeriodoDetalleIf periodoDetalleIf = (PeriodoDetalleIf) periodoDetalleIt.next();
				
				PeriodoDetalleData dataDetalle = new PeriodoDetalleData();
				anio = ((PeriodoDetalleIf)getStatusOriginales().get(detalle)).getAnio();
				mes = ((PeriodoDetalleIf)getStatusOriginales().get(detalle)).getMes();
				
				dataDetalle.setId(periodoDetalleIf.getId());
				dataDetalle.setAnio(anio);
				dataDetalle.setMes(mes);
				//dataDetalle.setStatus((!(getStatusNuevos().get(detalle).equals("A")) && isAutorizar())?getStatusNuevos().get(detalle):periodoDetalleIf.getStatus());
				dataDetalle.setStatus(getStatusNuevos().get(detalle));
				periodoDetalleColeccion.add(dataDetalle);
				detalle = detalle + 1;
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		SpiritAlert.createAlert("Para cambiar el status de los períodos, debe usar el botón Autorizar", SpiritAlert.WARNING);
	}
	
	private PeriodoIf registrarPeriodoForUpdate() throws GenericBusinessException {

		PeriodoIf periodoIf = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
		
		periodo.setCodigo(periodoIf.getCodigo());
		periodo.setEmpresaId(periodoIf.getEmpresaId());
		periodo.setFechaini(periodoIf.getFechaini());
		periodo.setFechafin(periodoIf.getFechafin());
		periodo.setOrden(periodoIf.getOrden());				
		
		if(contarStatus().get(0) > 0)
			periodo.setStatus(EstadoPeriodo.ACTIVO.getLetra());
			//periodo.setStatus("A");
		else if(contarStatus().get(1) > 0)
			periodo.setStatus(EstadoPeriodo.PARCIAL.getLetra());
			//periodo.setStatus("P");
		else if(contarStatus().get(2) > 0)
			periodo.setStatus(EstadoPeriodo.CERRADO.getLetra());
			//periodo.setStatus("C");
		else 
			periodo.setStatus(EstadoPeriodo.INACTIVO.getLetra());
			//periodo.setStatus("I");
		
		return periodo;
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		cargarComboPeriodo();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void authorize() {
		//PeriodoIf periodoIf = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
		if(!getStatusNuevos().isEmpty()){
			getStatusNuevos().removeAllElements();
		}
		DefaultTableModel model = (DefaultTableModel) getTblPeriodoDetalle().getModel();
		for(int i=0; i<getTblPeriodoDetalle().getRowCount(); i++){
			/*if(model.getValueAt(i,2).equals("INACTIVO"))
				getStatusNuevos().add("I");
			if(model.getValueAt(i,2).equals("ACTIVO"))
				getStatusNuevos().add("A");
			if(model.getValueAt(i,2).equals("CERRADO"))
				getStatusNuevos().add("C");
			if(model.getValueAt(i,2).equals("PARCIAL"))
				getStatusNuevos().add("P");*/
			EstadoPeriodo estado = (EstadoPeriodo) model.getValueAt(i,2);
			getStatusNuevos().add(estado.getLetra());
		}
		
 		if (validateFields()) {
			try {

				PeriodoIf periodo = registrarPeriodoForUpdate();
				generarPeriodoDetalleColleccionUpdate();
				/*SessionServiceLocator.getPeriodoSessionService().actualizarPeriodo(periodo, periodoDetalleColeccion);
				if(activado.size() > 0){
					if(activado.get(0).equals("ACTIVAR")) {
						Long idPeriodo = (Long)activado.get(1);
						String anioAnterior = (String)activado.get(2);
						String mesAnterior = (String)activado.get(3);
						String anioActivado = (String)activado.get(4);
						String mesActivado = (String)activado.get(5);
						// Aquí hay que traer el periodo detalle inicial del periodo
						Iterator periodoDetalleIterator = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleInicialByPeriodoId(periodo.getId()).iterator();
						PeriodoDetalleIf periodoDetalleInicial = (periodoDetalleIterator.hasNext())?(PeriodoDetalleIf) periodoDetalleIterator.next():null;
						String anioInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getAnio():"";
						String mesInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getMes():"";
						Collection saldosCuenta = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByPeriodoIdByAnioAndByMes(idPeriodo, anioAnterior, mesAnterior);
						Iterator saldosCuentaIt = saldosCuenta.iterator();
						double resultado = 0D;
						double ingresos = 0D;
						double egresos = 0D;
						while(saldosCuentaIt.hasNext()){
							SaldoCuentaIf saldoCuentaIf = (SaldoCuentaIf) saldosCuentaIt.next();
							CuentaIf cuenta = (CuentaIf) cuentasMap.get(saldoCuentaIf.getCuentaId());
							TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId());
							if (anioActivado.equals(anioInicial) && mesActivado.equals(mesInicial) && cuenta.getTiporesultadoId()!=null) {
								if (tipoCuenta.getCodigo().equals("I") || tipoCuenta.getCodigo().equals("OI"))
									ingresos += saldoCuentaIf.getValor().doubleValue();
								else if (tipoCuenta.getCodigo().equals("G") || tipoCuenta.getCodigo().equals("OO"))
									egresos += saldoCuentaIf.getValor().doubleValue();
							} else {
								SaldoCuentaData data = new SaldoCuentaData();
								data.setCuentaId(saldoCuentaIf.getCuentaId());
								data.setPeriodoId(periodoIf.getId());
								data.setAnio(anioActivado);
								data.setMes(mesActivado);
								data.setValordebe(saldoCuentaIf.getValordebe());
								data.setValorhaber(saldoCuentaIf.getValorhaber());
								data.setValor(saldoCuentaIf.getValor());						
								SessionServiceLocator.getSaldoCuentaSessionService().addSaldoCuenta(data);
							}
						}
						
						resultado = Utilitarios.redondeoValor(ingresos - egresos);
						Map parameterMap = new HashMap();
						parameterMap.put("codigo", resultado>0D?"UTILIDAD":"PERDIDA");
						parameterMap.put("empresaId", Parametros.getIdEmpresa());
						Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
						if (resultado != 0D && it.hasNext()) {
							ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
							parameterMap.clear();
							parameterMap.put("codigo", parametroEmpresa.getValor());
							Iterator cuentaIt = SessionServiceLocator.getCuentaSessionService().findCuentaByEmpresaIdAndQuery(Parametros.getIdEmpresa(), parameterMap).iterator();
							if (cuentaIt.hasNext()) {
								CuentaIf cuentaResultadoEjercicioAnterior = (CuentaIf) cuentaIt.next();
								SaldoCuentaData resultadoData = new SaldoCuentaData();
								resultadoData.setCuentaId(cuentaResultadoEjercicioAnterior.getId());
								resultadoData.setPeriodoId(periodoIf.getId());
								resultadoData.setAnio(anioActivado);
								resultadoData.setMes(mesActivado);
								resultadoData.setValordebe((resultado > 0)?BigDecimal.ZERO:BigDecimal.valueOf(resultado));
								resultadoData.setValorhaber((resultado) > 0?BigDecimal.valueOf(resultado):BigDecimal.ZERO);
								resultadoData.setValor(new BigDecimal(resultado));						
								SessionServiceLocator.getSaldoCuentaSessionService().addSaldoCuenta(resultadoData);
							}
						}
					}
				}*/
				Map queryMap = new HashMap();
				queryMap.put("periodoId", periodo.getId());
				queryMap.put("asientoCierre", "S");
				Iterator<AsientoIf> it = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(queryMap).iterator();
				AsientoIf asientoCierre = it.hasNext()?it.next():null;
				Map<String, Object> beansMap = new HashMap<String, Object>();
				queryMap.clear();
				queryMap.put("empresaId", Parametros.getIdEmpresa());
				queryMap.put("predeterminado", "S");
				beansMap.put("PLAN_CUENTA", (PlanCuentaIf) SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByQuery(queryMap).iterator().next());
				beansMap.put("EMPRESA", (EmpresaIf) Parametros.getEmpresa());
				beansMap.put("OFICINA", (OficinaIf) Parametros.getOficina());
				beansMap.put("USUARIO", (UsuarioIf) Parametros.getUsuarioIf());
				SessionServiceLocator.getAsientoSessionService().actualizarPeriodoServer(periodo, periodoDetalleColeccion, activado, asientoCierre, beansMap, true);
				SpiritAlert.createAlert("Periodo actualizado con éxito", SpiritAlert.INFORMATION);
				periodoDetalleColeccion.clear();
				showSaveMode();
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al actualizar el periodo", SpiritAlert.ERROR);
			} catch (Exception e) {
				SpiritAlert.createAlert("Se ha producido un error al actualizar el periodo", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			
			clean();
			if (periodo != null) {
				periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
				String letraEstado = periodo.getStatus();
				try {
					EstadoPeriodo estado = EstadoPeriodo.getEstadoPeriodoByLetra(letraEstado);
					getTxtPeriodo().setText(estado.toString());
				} catch (GenericBusinessException e) {
					getTxtPeriodo().setText("-ERROR-");
				}
				
				/*if(periodo.getStatus().equals("I"))
					getTxtPeriodo().setText("INACTIVO");
				if(periodo.getStatus().equals("A"))
					getTxtPeriodo().setText("ACTIVO");
				if(periodo.getStatus().equals("C"))
					getTxtPeriodo().setText("CERRADO");
				if(periodo.getStatus().equals("P"))
					getTxtPeriodo().setText("PARCIAL");*/

				cargarTabla();
			}
		}
	}

	public void clean() {
		DefaultTableModel model = (DefaultTableModel) getTblPeriodoDetalle().getModel();
		for(int i= this.getTblPeriodoDetalle().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public boolean validateFields() {
		try {
			activado.removeAllElements();
			mesHoy = Utilitarios.getMonthFromDate(Utilitarios.dateHoy());
			anioHoy = Utilitarios.getYearFromDate(Utilitarios.dateHoy());
			PeriodoIf periodoIf = (PeriodoIf) getCmbPeriodo().getSelectedItem();
			
			if ((("".equals(periodoIf)) || (periodoIf == null))) {
				SpiritAlert.createAlert("Debe escoger un Periodo !!", SpiritAlert.WARNING);
				getCmbPeriodo().grabFocus();
				return false;
			}			
			
			for(int i=0; i<getStatusOriginales().size(); i++){	
				
				String letraStatusOriginal = ((PeriodoDetalleIf)getStatusOriginales().get(i)).getStatus();
				EstadoPeriodo estadoOriginal = EstadoPeriodo.getEstadoPeriodoByLetra(letraStatusOriginal);
				
				String mes = ((PeriodoDetalleIf)getStatusOriginales().get(i)).getMes();
				String anio = ((PeriodoDetalleIf)getStatusOriginales().get(i)).getAnio();
				
				String letraStatusNuevo = getStatusNuevos().get(i);
				EstadoPeriodo estadoNuevo = EstadoPeriodo.getEstadoPeriodoByLetra(letraStatusNuevo);
				
				//if(!letraStatusOriginal.equals(getStatusNuevos().get(i))){
				if( estadoOriginal != estadoNuevo ){
					//if(letraStatusOriginal.equals("I") && !getStatusNuevos().get(i).equals("A")){
					if(estadoOriginal == EstadoPeriodo.INACTIVO && estadoNuevo != EstadoPeriodo.ACTIVO){
						SpiritAlert.createAlert("Un mes Inactivo sólo puede pasar a Activo !!", SpiritAlert.WARNING);
						return false;
					}
					//if(!letraStatusOriginal.equals("I") && getStatusNuevos().get(i).equals("I")){
					if(estadoOriginal != EstadoPeriodo.INACTIVO && estadoNuevo == EstadoPeriodo.INACTIVO){
						SpiritAlert.createAlert("Un mes no puede volver a estar Inactivo !!", SpiritAlert.WARNING);
						return false;
					}
					//if(letraStatusOriginal.equals("A") && !getStatusNuevos().get(i).equals("C")){
					if(estadoOriginal == EstadoPeriodo.ACTIVO && estadoNuevo != EstadoPeriodo.CERRADO){
						SpiritAlert.createAlert("Un mes Activo sólo puede pasar a Cerrado !!", SpiritAlert.WARNING);
						return false;
					}
					//if(letraStatusOriginal.equals("C") && !getStatusNuevos().get(i).equals("P")){
					if(estadoOriginal == EstadoPeriodo.CERRADO && estadoNuevo != EstadoPeriodo.PARCIAL){
						SpiritAlert.createAlert("Un mes Cerrado sólo puede pasar a Parcial !!", SpiritAlert.WARNING);
						return false;
					}
					/*if(statusOriginal.equals("C") && getStatusNuevos().get(i).equals("P") && !isAutorizar()){
						SpiritAlert.createAlert("Para cambiar el status de un periodo a Parcial, debe usar el botón Autorizar!", SpiritAlert.WARNING);
						return false;
					}*/
					//if(letraStatusOriginal.equals("P") && !getStatusNuevos().get(i).equals("C")){
					if( estadoOriginal == EstadoPeriodo.PARCIAL && estadoNuevo != EstadoPeriodo.CERRADO ){
						SpiritAlert.createAlert("Un mes en status Parcial sólo puede pasar a Cerrado !!", SpiritAlert.WARNING);
						return false;
					}
					//if(getStatusNuevos().get(i).equals("A") && !(mes.equals(mesHoy) && anio.equals(anioHoy))){
					if(estadoNuevo == EstadoPeriodo.ACTIVO && !(mes.equals(mesHoy) && anio.equals(anioHoy))){
						SpiritAlert.createAlert("Sólo puede Activar el mes actual !!", SpiritAlert.WARNING);
						return false;
					}
					//if(getStatusNuevos().get(i).equals("A")) {
					if(estadoNuevo == EstadoPeriodo.ACTIVO) {
						if (i>0){
							if(((PeriodoDetalleIf)getStatusOriginales().get(i-1)).getStatus().equals("I")) {
								SpiritAlert.createAlert("Meses inactivos no pueden preceder al mes que se desea activar !!", SpiritAlert.WARNING);
								return false;
							}
						}
					}
					
					//if(getStatusNuevos().get(i).equals("A") && (mes.equals(mesHoy) && anio.equals(anioHoy))){
					if(estadoNuevo == EstadoPeriodo.ACTIVO && (mes.equals(mesHoy) && anio.equals(anioHoy))){
						if(i>0){
							activado.add("ACTIVAR");
							activado.add(((PeriodoDetalleIf)getStatusOriginales().get(i-1)).getPeriodoId());
							activado.add(((PeriodoDetalleIf)getStatusOriginales().get(i-1)).getAnio());
							activado.add(((PeriodoDetalleIf)getStatusOriginales().get(i-1)).getMes());
							activado.add(anio);
							activado.add(mes);
						}
						else if (i==0){
							int anioAnterior = Integer.parseInt(anio);
							int mesAnterior = Integer.parseInt(mes);
							
							if((mesAnterior-1) <= 0){
								anioAnterior--;
								mesAnterior = 12;
							}
							else
								mesAnterior--;
							
							java.sql.Date fechaAnterior = Utilitarios.fechaHoy((mesAnterior-1), (anioAnterior-1900));
							PeriodoIf periodoAnterior = null;
							
							if(!SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaIdAndByFechaFin(Parametros.getIdEmpresa(), fechaAnterior).isEmpty()){
								periodoAnterior = (PeriodoIf) SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaIdAndByFechaFin(Parametros.getIdEmpresa(), fechaAnterior).iterator().next();
							}		
							
							if(periodoAnterior != null){
								activado.add("ACTIVAR");
								activado.add(periodoAnterior.getId());
								activado.add(((Integer)anioAnterior).toString());
								
								if(mesAnterior > 9)
									activado.add(((Integer)mesAnterior).toString());
								else
									activado.add("0"+((Integer)mesAnterior).toString());
								
								activado.add(anio);
								activado.add(mes);
							}
						}
					}
					
					//if(getStatusNuevos().get(i).equals("C") && letraStatusOriginal.equals("A") && (mes.equals(mesHoy) && anio.equals(anioHoy))) {
					if(estadoNuevo == EstadoPeriodo.CERRADO && estadoOriginal == EstadoPeriodo.ACTIVO && 
						(mes.equals(mesHoy) && anio.equals(anioHoy))) {
						SpiritAlert.createAlert("No puede Cerrar el mes actual !!", SpiritAlert.WARNING);
						return false;
					}
					
					if(contarStatus().get(0) > 1){					
						SpiritAlert.createAlert("Sólo un mes puede estar Activo !!", SpiritAlert.WARNING);
						return false;
					}
				}
			}
			
			if(contarStatus().get(0) > 1){
				SpiritAlert.createAlert("Sólo un Periodo puede estar Activo !!", SpiritAlert.WARNING);
				return false;			
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return true;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public Vector<Object> getStatusOriginales() {
		return statusOriginales;
	}

	public void setStatusOriginales(Vector<Object> statusOriginales) {
		this.statusOriginales = statusOriginales;
	}

	public Vector<String> getStatusNuevos() {
		return statusNuevos;
	}

	public void setStatusNuevos(Vector<String> statusNuevos) {
		this.statusNuevos = statusNuevos;
	}

	public Vector<Integer> getCuentaStatus() {
		return cuentaStatus;
	}

	public void setCuentaStatus(Vector<Integer> cuentaStatus) {
		this.cuentaStatus = cuentaStatus;
	}
}