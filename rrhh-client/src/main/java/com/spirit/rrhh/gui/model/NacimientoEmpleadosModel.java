package com.spirit.rrhh.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.spirit.client.Parametros;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.rrhh.entity.EmpleadoPersonalIf;
import com.spirit.rrhh.gui.panel.JPNacimientoEmpleados;
import com.spirit.util.Utilitarios;


public class NacimientoEmpleadosModel extends JPNacimientoEmpleados{
	 
	protected EmpleadoIf empleadoIf;
	private DefaultTableModel modelTblNacimiento;
	
	public NacimientoEmpleadosModel(){
		showSaveMode();
		initKeyListeners();
		initListeners();
	}
	
	private void initKeyListeners() {
		getCbTelefono().setVisible(false);
		getLblEmpleado().setVisible(false);
		getTxtEmpleado().setVisible(false);
		getBtnEmpleado().setVisible(false);
		getBtnLimpiarEmpleado().setVisible(false);
		
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
	
	Comparator<Object[]> ordenadorDiasNacimiento = new Comparator<Object[]>(){
		public int compare(Object[] te1, Object[] te2) {
			EmpleadoPersonalIf empleadoPersonal1 = (EmpleadoPersonalIf)te1[1];
			EmpleadoPersonalIf empleadoPersonal2 = (EmpleadoPersonalIf)te2[1];
			Integer fecha1 = empleadoPersonal1.getFechaNacimiento().getDate();
			Integer fecha2 = empleadoPersonal2.getFechaNacimiento().getDate();
			return (fecha1.compareTo(fecha2));
		}		
	};
	
	Comparator<Object[]> ordenadorMesesNacimiento = new Comparator<Object[]>(){
		public int compare(Object[] te1, Object[] te2) {
			EmpleadoPersonalIf empleadoPersonal1 = (EmpleadoPersonalIf)te1[1];
			EmpleadoPersonalIf empleadoPersonal2 = (EmpleadoPersonalIf)te2[1];
			Integer fecha1 = empleadoPersonal1.getFechaNacimiento().getMonth();
			Integer fecha2 = empleadoPersonal2.getFechaNacimiento().getMonth();
			return (fecha1.compareTo(fecha2));
		}		
	};
	
	public void cargarTabla(){
		cleanTable();
		modelTblNacimiento = (DefaultTableModel) getTblNacimiento().getModel();
		java.sql.Date fechaSeleccionada = new java.sql.Date(getCmbMes().getDate().getTime());
		//int anio = fechaSeleccionada.getYear();
		int mes = fechaSeleccionada.getMonth();
		/*int diaFin = Utilitarios.getLastDayOfMonth(mes, anio);
				
		java.sql.Date fechaInicioDate = new java.sql.Date(1,mes,1);
		java.sql.Date fechaFinDate = new java.sql.Date(3000,mes,diaFin);
		Timestamp fechaInicio=new Timestamp(fechaInicioDate.getTime());
		Timestamp fechaFin=new Timestamp(fechaFinDate.getTime());*/
		
		try {
			//Collection fechasNacimiento = SessionServiceLocator.getEmpleadoPersonalSessionService().findEmpleadoPersonalByFechaInicioAndFechaFin(fechaInicio, fechaFin);
			List fechasNacimiento = (ArrayList)SessionServiceLocator.getEmpleadoPersonalSessionService().findEmpleadoAndEmpleadoPersonalActivos();
			Collections.sort(fechasNacimiento,ordenadorDiasNacimiento);
			if(getCbAnioCompleto().isSelected()){
				Collections.sort(fechasNacimiento,ordenadorMesesNacimiento);
			}
			Iterator fechasNacimientoIt = fechasNacimiento.iterator();
			while (fechasNacimientoIt.hasNext()){
				Object[] empleadoPersonalO = (Object[])fechasNacimientoIt.next();
				EmpleadoIf empleado = (EmpleadoIf)empleadoPersonalO[0];
				EmpleadoPersonalIf empleadoPersonal = (EmpleadoPersonalIf)empleadoPersonalO[1];
			    if(getCbAnioCompleto().isSelected() || 
			    		(!getCbAnioCompleto().isSelected() && empleadoPersonal.getFechaNacimiento().getMonth() == mes)){
			    	
			    	Vector<String> fila = new Vector <String>();
				    
			    	fila.add(empleado.getNombres()+ " "+ empleado.getApellidos());
				    int mesCelebracion = empleadoPersonal.getFechaNacimiento().getMonth();
				    if (getCbFechaCompleta().isSelected()){
				    	fila.add(Utilitarios.getFechaUppercase(empleadoPersonal.getFechaNacimiento()));
				    	if(getCbAnioCompleto().isSelected()){
				    		fila.add(empleado.getCelular());
				    	}
				    }
				    else{
				    	if(getCbAnioCompleto().isSelected()){
				    		fila.add(Utilitarios.getMesesMayusculas()[mesCelebracion] + " / " + String.valueOf(empleadoPersonal.getFechaNacimiento().getDate()));
				    		fila.add(empleado.getCelular());
				    	}else{
				    		fila.add(String.valueOf(empleadoPersonal.getFechaNacimiento().getDate()));
				    	}
				    }
				    
				    modelTblNacimiento.addRow(fila);
			    }				
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTabla();
			}
		});
		
		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoIf = null;
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clean();
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				}
			}
		});
		getBtnLimpiarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoIf = null;
				getTxtEmpleado().setText("");
			}
		});
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
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

	public void clean() {
		getCmbMes().setCalendar(new GregorianCalendar());	
		cleanTable();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblNacimiento().getModel();
		for(int i= this.getTblNacimiento().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void report() {
		HashMap parametrosMap = new HashMap();
		
		try {
			String fileName = "jaspers/rrhh/RPNacimientoEmpleados.jasper";
			if(getCbAnioCompleto().isSelected()){
				fileName = "jaspers/rrhh/RPNacimientoEmpleadosAnio.jasper";
			}
			CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
			parametrosMap.put("ciudad", ciudad.getNombre());
			String fechaActual = Utilitarios.dateHoraHoy();
			String year = fechaActual.substring(0,4);
			String month = fechaActual.substring(5,7);
			String day = fechaActual.substring(8,10);
			String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
			parametrosMap.put("usuario", Parametros.getUsuario());
			parametrosMap.put("emitido", fechaEmision);
			EmpresaIf empresa = (EmpresaIf)Parametros.getEmpresa();
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());	
			
			java.sql.Date fechaSeleccionada = new java.sql.Date(getCmbMes().getDate().getTime());
			int mes = fechaSeleccionada.getMonth();
			
			if(getCbAnioCompleto().isSelected()){
				parametrosMap.put("mes", "CUMPLEAÑOS");	
			}else{
				parametrosMap.put("mes", "CUMPLEAÑOS DE\n" + Utilitarios.getMesesMayusculas()[mes]);	
			}
			
			ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel)getTblNacimiento().getModel(), true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
