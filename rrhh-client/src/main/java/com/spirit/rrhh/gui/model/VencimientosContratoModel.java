package com.spirit.rrhh.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.rrhh.gui.panel.JPVencimientosContrato;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.Utilitarios;

public class VencimientosContratoModel extends JPVencimientosContrato{

	private DefaultTableModel modelTblVencimientos;
	
	public VencimientosContratoModel(){
		initKeyListeners();
		initListeners();
		anchoColumnasTabla();
		showSaveMode();
	}
	
	private void initKeyListeners() {
		getLblEmpleado().setVisible(false);
		getTxtEmpleado().setVisible(false);
		getBtnEmpleado().setVisible(false);
		getBtnLimpiarEmpleado().setVisible(false);
		getCbAnioCompleto().setVisible(false);
		
		getTxtEmpleado().setEditable(false);
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEmpleado().setToolTipText("Buscar Empleado");		
		getBtnLimpiarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnLimpiarEmpleado().setToolTipText("Limpiar Empleado");		
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");		
		
		getCmbMes().setLocale(Utilitarios.esLocale);
		getCmbMes().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMes().setEditable(false);
		getCmbMes().setShowNoneButton(false);

	}

	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTabla();
			}
		});
		
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblVencimientos().getModel();
		for(int i= this.getTblVencimientos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	Comparator<Object[]> ordenadorFechaInicioContrato = new Comparator<Object[]>(){
		public int compare(Object[] c1, Object[] c2) {
			ContratoIf contrato1 = (ContratoIf)c1[2];
			ContratoIf contrato2 = (ContratoIf)c2[2];
			Long contratoFechaInicio1 = contrato1.getFechaInicio().getTime();
			Long contratoFechaInicio2 = contrato2.getFechaInicio().getTime();
			return (contratoFechaInicio1.compareTo(contratoFechaInicio2));
		}		
	};	
	
	public void cargarTabla(){
		cleanTable();
		modelTblVencimientos = (DefaultTableModel) getTblVencimientos().getModel();
		java.sql.Date fechaSeleccionada = new java.sql.Date(getCmbMes().getDate().getTime());
		int mes = fechaSeleccionada.getMonth()+1;
		int anio = fechaSeleccionada.getYear()+1900;
		
		try {
			List vencimientosContratos = (ArrayList)SessionServiceLocator.getEmpleadoPersonalSessionService().findEmpleadoAndEmpleadoPersonalActivos();
			Collections.sort(vencimientosContratos,ordenadorFechaInicioContrato);
			Iterator vencimientosContratosIt = vencimientosContratos.iterator();
			while (vencimientosContratosIt.hasNext()){
				Object[] empleadoContratoO = (Object[])vencimientosContratosIt.next();
				EmpleadoIf empleado = (EmpleadoIf)empleadoContratoO[0];
				ContratoIf contrato = (ContratoIf)empleadoContratoO[2];
				
				java.sql.Date fechaInicioContrato = contrato.getFechaInicio();
												
				Calendar fechaInicio90 = new GregorianCalendar();
				fechaInicio90.setTimeInMillis(fechaInicioContrato.getTime());
				fechaInicio90.add(Calendar.DATE, 90);
				java.sql.Date fechaInicio90Date = new java.sql.Date(fechaInicio90.getTimeInMillis());  
				
				int mes90 = fechaInicio90Date.getMonth()+1;
				int anio90 = fechaInicio90Date.getYear()+1900;
										
				Calendar fechaInicio360 = new GregorianCalendar();
				fechaInicio360.setTimeInMillis(fechaInicioContrato.getTime());
				fechaInicio360.add(Calendar.DATE, 360);
				java.sql.Date fechaInicio360Date = new java.sql.Date(fechaInicio360.getTimeInMillis());
				
				int mes360 = fechaInicio360Date.getMonth()+1;
				int anio360 = fechaInicio360Date.getYear()+1900;
													
				Vector<String> fila = new Vector <String>();
				
				OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(empleado.getOficinaId());
				DepartamentoIf departamento = SessionServiceLocator.getDepartamentoSessionService().getDepartamento(empleado.getDepartamentoId());
							
				if(mes==mes90 && anio==anio90){	
					if(oficina.getNombre().equals("CREACIONAL/GUAYAQUIL")){
						fila.add("GUAYAQUIL");
					}else{
						fila.add("QUITO");
					}
					fila.add(departamento.getNombre());
					fila.add(empleado.getApellidos()+ " " + empleado.getNombres());
					fila.add(Utilitarios.getFechaUppercase(fechaInicioContrato));
					fila.add(Utilitarios.getFechaUppercase(fechaInicio90Date));	
					fila.add("");	
								
					modelTblVencimientos.addRow(fila); 
														
				}else if(mes==mes360 && anio==anio360){
					if(oficina.getNombre().equals("CREACIONAL/GUAYAQUIL")){
						fila.add("GUAYAQUIL");
					}else{
						fila.add("QUITO");
					}
					fila.add(departamento.getNombre());
				    fila.add(empleado.getApellidos()+ " " + empleado.getNombres());
				    fila.add(Utilitarios.getFechaUppercase(fechaInicioContrato));
					fila.add("");		
					fila.add(Utilitarios.getFechaUppercase(fechaInicio360Date));	
										
					modelTblVencimientos.addRow(fila);
				}	
				
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblVencimientos());
		getTblVencimientos().getTableHeader().setReorderingAllowed(false);
			
		TableColumn anchoColumna = getTblVencimientos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVencimientos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVencimientos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblVencimientos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVencimientos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblVencimientos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(30);
						
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		//getTblVencimientos().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		getTblVencimientos().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		//getTblVencimientos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblVencimientos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblVencimientos().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblVencimientos().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		
	}
		
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
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
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	public void report() {
		HashMap parametrosMap = new HashMap();

		String fileName = "jaspers/rrhh/RPVencimientosContratos.jasper";
		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		parametrosMap.put("urlLogoEmpresa", empresa.getLogo());

		java.sql.Date fechaSeleccionada = new java.sql.Date(getCmbMes().getDate().getTime());
		int mes = fechaSeleccionada.getMonth();
		int anio = fechaSeleccionada.getYear() + 1900;

		parametrosMap.put("mes", "VENCIMIENTOS DE CONTRATOS\n" + Utilitarios.getMesesMayusculas()[mes] + " " + String.valueOf(anio));

		ReportModelImpl.processReport(fileName, parametrosMap,(DefaultTableModel) getTblVencimientos().getModel(), true);
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
}
