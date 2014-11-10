package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.nomina.gui.panel.JPCalcularUtilidades;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CalcularUtilidadesModel extends JPCalcularUtilidades {

	private static final String TODOS = "TODOS";
	private static final int MAX_LONGITUD_VALOR = 15;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	
	public CalcularUtilidadesModel(){
		cargarComboOficina();
		cargarComboDepartamento();
		initKeyListeners();
		showSaveMode();
		initListeners();
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");		
		getBtnCalcularUtilidades().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnCalcularUtilidades().setToolTipText("Calcular Utilidades");
		
		getCmbAnio().setLocale(Utilitarios.esLocale);
		getCmbAnio().setShowNoneButton(false);
		getCmbAnio().setFormat(Utilitarios.setFechaAnio());
		getCmbAnio().setEditable(false);
		
		getTxtIngresarUtilidad().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtIngresarUtilidad().addKeyListener(new NumberTextFieldDecimal());
		
		getTblCalcularUtilidades().getTableHeader().setReorderingAllowed(false);
		getTblCalcularUtilidades().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(160);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCalcularUtilidades().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(110);
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (ArrayList)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			oficinas.add(TODOS);
			refreshCombo(getCmbOficina(),oficinas);
			getCmbOficina().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<DepartamentoIf> ordenadorDepartamentoNombre = new Comparator<DepartamentoIf>(){
		public int compare(DepartamentoIf te1, DepartamentoIf te2) {
			if(te1.getNombre() == null){
				return -1;
			}else if(te2.getNombre() == null){
				return 1;
			}else{
				return (te1.getNombre().compareTo(te2.getNombre()));
			}
		}		
	};
	
	private void cargarComboDepartamento(){
		try {
			List departamentos = (ArrayList)SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(departamentos,ordenadorDepartamentoNombre);
			departamentos.add(TODOS);
			refreshCombo(getCmbDepartamento(),departamentos);
			getCmbDepartamento().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initListeners(){
		getTxtIngresarUtilidad().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				double utilidad = 0D;
				
				if (!getTxtIngresarUtilidad().getText().equals(""))
					utilidad = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtIngresarUtilidad().getText()));

				double porcentaje75 = utilidad * 0.75;
				double porcentaje25 = utilidad * 0.25;
				
				getTxt75Porciento().setText(formatoDecimal.format(porcentaje75));
				getTxt25Porciento().setText(formatoDecimal.format(porcentaje25));
			}
		});
		
		getBtnCalcularUtilidades().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//Collection contratosEnAnio = SessionServiceLocator.getContratoSessionService().findContratoByFechaRolPagoByTipoContratoIdByEstado()
			}
		});
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
