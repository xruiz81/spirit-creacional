package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPMayorizacion;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.util.Utilitarios;

public class MayorizacionModel extends JPMayorizacion {

	private static final long serialVersionUID = -8947315109977229522L;

	Map<String,PeriodoDetalleIf> mapaPeriodoDetalle = new HashMap<String,PeriodoDetalleIf>();
	
	public MayorizacionModel() {
		initListeners();
		showUpdateMode();
	}
	
	private void initListeners(){
		getCmbPeriodo().addActionListener(alPeriodo);
	}
	
	ActionListener alPeriodo = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
			cargarComboPeriodoDetalle(periodo);
		}
		
	};
	
	@Override
	public void clean() {
		getCmbPeriodo().setSelectedItem(null);
		getTxtEventos().setText("");
	}

	@Override
	public void delete() {
		
	}
	
	public void save() {
		
	}

	@Override
	public void update() {
		if ( validateFields() ){
			try {
				setCursor(SpiritCursor.hourglassCursor);
				PeriodoIf periodoIf = (PeriodoIf) getCmbPeriodo().getSelectedItem();
				String periodoDetalleString = (String) getCmbMesInicial().getSelectedItem();
				PeriodoDetalleIf periodoDetalleIf = mapaPeriodoDetalle.get(periodoDetalleString);
				Iterator it = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(periodoIf.getId(), periodoDetalleIf.getMes(), periodoDetalleIf.getAnio(), true).iterator();
				getTxtEventos().setText("");
				Map<String, Object> beansMap = new HashMap<String, Object>();
				Map queryMap = new HashMap();
				queryMap.put("empresaId", Parametros.getIdEmpresa());
				queryMap.put("predeterminado", "S");
				beansMap.put("PLAN_CUENTA", (PlanCuentaIf) SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByQuery(queryMap).iterator().next());
				beansMap.put("EMPRESA", (EmpresaIf) Parametros.getEmpresa());
				beansMap.put("OFICINA", (OficinaIf) Parametros.getOficina());
				beansMap.put("USUARIO", (UsuarioIf) Parametros.getUsuarioIf());
				while (it.hasNext()) {
					PeriodoDetalleIf pd = (PeriodoDetalleIf) it.next();
					String eventos = getTxtEventos().getText();
					getTxtEventos().setText(eventos + "\n" + "Iniciando mayorización " + Utilitarios.getNombreMes(Integer.valueOf(pd.getMes())) + " " + pd.getAnio() + "...");
					SessionServiceLocator.getAsientoSessionService().mayorizarPeriodos(periodoIf, pd, false, beansMap, false);
					eventos = getTxtEventos().getText();
					getTxtEventos().setText(eventos + "\n" + "Finalizado");
				}
				setCursor(SpiritCursor.normalCursor);
				SpiritAlert.createAlert("Proceso realizado con éxito", SpiritAlert.INFORMATION);
				showUpdateMode();
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
	}

	@Override
	public void showSaveMode() {
		
	}
	
	@Override
	public void showUpdateMode() {
		setUpdateMode();
		clean();
		refresh();
		getCmbPeriodo().grabFocus();
	}
	
	@Override
	public void refresh() {
		cargarComboPeriodo();
	}

	private void cargarComboPeriodo() {
		List periodos = ContabilidadFinder.findPeriodo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPeriodo(),periodos);
		PeriodoModel.seleccionarPeriodoActivo(getCmbPeriodo());
		getCmbMesInicial().setSelectedItem(null);
	}
	
	private void cargarComboPeriodoDetalle(PeriodoIf periodo) {
		ArrayList<PeriodoDetalleIf> detalles = new ArrayList<PeriodoDetalleIf>();
		mapaPeriodoDetalle = new LinkedHashMap<String, PeriodoDetalleIf>();
		if ( periodo != null ){
			try {
				detalles = (ArrayList<PeriodoDetalleIf>) SessionServiceLocator
					.getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(periodo.getId());
				
				Collections.sort(detalles, cPeriodoDetalle);
				
				for (PeriodoDetalleIf periodoDetalle : detalles){
					String mes = Utilitarios.getNombreMes(Integer.valueOf(periodoDetalle.getMes()));
					String anio = periodoDetalle.getAnio();
					mapaPeriodoDetalle.put(mes+" / "+anio, periodoDetalle);
				}
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
		Vector<String> detallesString = new Vector<String>(mapaPeriodoDetalle.keySet());
		DefaultComboBoxModel modelo =  new DefaultComboBoxModel(detallesString);
		getCmbMesInicial().setModel(modelo);
		//refreshCombo(getCmbPeriodoDetalle(),detalles);
		
	}
	
	private Comparator<PeriodoDetalleIf> cPeriodoDetalle = new Comparator<PeriodoDetalleIf>() {

		@Override
		public int compare(PeriodoDetalleIf uno, PeriodoDetalleIf dos) {
			Integer pd1 = Integer.valueOf(uno.getMes())+Integer.valueOf(uno.getAnio());
			Integer pd2 = Integer.valueOf(dos.getMes())+Integer.valueOf(dos.getAnio());
			return pd1.compareTo(pd2);
		}
		
	};

	@Override
	public boolean validateFields() {
		if ( getCmbPeriodo().getSelectedItem() == null ){
			SpiritAlert.createAlert("Debe seleccionar un periodo !!", SpiritAlert.INFORMATION);
			getCmbPeriodo().grabFocus();
			return false;
		}
		return true;
	}

}
