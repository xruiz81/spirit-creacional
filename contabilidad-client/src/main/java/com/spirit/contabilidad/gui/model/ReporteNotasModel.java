package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.gui.panel.JPReporteNotas;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;
 
public class ReporteNotasModel extends JPReporteNotas {

	public ReporteNotasModel() {
		initComponents();
		initListeners();
	}
	
	private void initComponents(){
		Date fechaHoy = new Date();
		getCmbFechaInicio().setDate(fechaHoy);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		loadCombos();
	}
	
	private void loadCombos() {
		loadComboEjercicioContable();
	}
	
	private void loadComboEjercicioContable() {
		try {
			List periodos = (ArrayList) SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbEjercicioContable(),periodos);
			PeriodoModel.seleccionarPeriodoActivo(getCmbEjercicioContable());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initListeners(){
		getBtnConsultar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//consultar();
			}
		});
	}
	
	public void clean() {
		//cleanTable(getTblNotas());
	}
	
	public boolean validateFields() {
		Date fechaInicio = getCmbFechaInicio().getDate();
		if ( fechaInicio == null ){
			SpiritAlert.createAlert("Debe seleccionar una fecha inicial", SpiritAlert.WARNING);
			return false;
		}
		Date fechaFin = getCmbFechaFin().getDate();
		if ( fechaFin == null ){
			SpiritAlert.createAlert("Debe seleccionar una fecha final", SpiritAlert.WARNING);
			return false;
		}
		if ( fechaInicio.compareTo(fechaFin) > 0 ){
			SpiritAlert.createAlert("La fecha final debe ser posterior a la fecha inicial seleccionada", SpiritAlert.WARNING);
			return false;	
		}
		return true;
	}

	public void report() {
		try {
			if (validateFields()) {
				String si = "Si"; 
		        String no = "No"; 
		        Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/contabilidad/RPReporteNotas.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("REPORTE DE NOTAS").iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					parametrosMap.put("emitido", fechaEmision);
					Date fechaInicio = getCmbFechaInicio().getDate(); 
					Date fechaFin = getCmbFechaFin().getDate();
					parametrosMap.put("fechaInicial", Utilitarios.getFechaUppercase(fechaInicio) );
					parametrosMap.put("fechaFinal", Utilitarios.getFechaUppercase(fechaFin));
					
					//ReportModelImpl.processReport(fileName, parametrosMap, cuentasContables, true);
					System.gc();
				}
			} 
		} catch (GenericBusinessException e) {
			setCursorNormal();
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (ParseException e) {
			setCursorNormal();
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}

	public void showSaveMode() {
		clean();
		setSaveMode();
		getCmbFechaInicio().grabFocus();
	}
}
