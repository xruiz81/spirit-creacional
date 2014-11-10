package com.spirit.rrhh.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.handler.EstadoContrato;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.rrhh.gui.panel.JPHojaDeValores;
import com.spirit.rrhh.gui.reporteData.DatosBeneficios;
import com.spirit.rrhh.gui.reporteData.DatosDeudas;
import com.spirit.rrhh.gui.reporteData.DatosSueldo;
import com.spirit.rrhh.gui.reporteData.DatosVacaciones;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class HojaDeValoresModel extends JPHojaDeValores {

	private static final long serialVersionUID = -1720316086940698847L;

	final int COLUMNA_SUELDO_VALOR = 2;
	final int COLUMNA_DEUDA_VALOR = 5;
	
	private EmpleadoCriteria empleadoCriteria;
	private EmpleadoIf empleadoIf;
	private ContratoIf contratoIf;
	
	Map<Long,RubroIf> mapaRubros = null;
	Map<Long,TipoRolIf> mapaTipoRol = null;
	
	private Collection<DatosSueldo> sueldos = null;
	private Collection<DatosDeudas> deudas = null;
	private Collection<DatosVacaciones> vacaciones = null;
	private Collection<DatosBeneficios> beneficios = null;
	private Double totalDeudas = 0D;

	private Map<BigDecimal,String> mapaSueldoFecha = new HashMap<BigDecimal,String>();
	
	public HojaDeValoresModel(){
		initListeners();
		iniciarComponentes();
		refresh();
		try {
			cargarTiposRol();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Tipos de Roles de Pago !!", SpiritAlert.ERROR);
			
		}
		clean();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		
		getBtnImprimirSueldos().setText("");
		getBtnImprimirSueldos().setToolTipText("Imprimir Historial de Sueldos");
		getBtnImprimirSueldos().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));

		getBtnImprimirDeudas().setText("");
		getBtnImprimirDeudas().setToolTipText("Imprimir Deudas");
		getBtnImprimirDeudas().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));

		getBtnImprimirVac().setText("");
		getBtnImprimirVac().setToolTipText("Imprimir Vacaciones");
		getBtnImprimirVac().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));

		getBtnImprimirBeneficios().setText("");
		getBtnImprimirBeneficios().setToolTipText("Imprimir Vacaciones");
		getBtnImprimirBeneficios().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));

		getBtnBuscarEmpleado().setText("");	
		getBtnBuscarEmpleado().setToolTipText("Buscar empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnBuscarContrato().setText("");	
		getBtnBuscarContrato().setToolTipText("Buscar contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		String formatoValores = "#####0.00"; 
		
		getTblSueldos().getColumnModel().getColumn(COLUMNA_SUELDO_VALOR).setCellRenderer(
				new NumberCellRenderer(formatoValores,NumberCellRenderer.DERECHA) );
		
		getTblDeudas().getColumnModel().getColumn(COLUMNA_DEUDA_VALOR).setCellRenderer(
				new NumberCellRenderer(formatoValores,NumberCellRenderer.DERECHA) );
			
	}

	private void initListeners() {

		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarEmpleado();        
			}
		});
		
		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					buscarContrato(true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			}
		});

		getCbUnirContratos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if ( getCbUnirContratos().isSelected() ){
					contratoIf = null;
					getTxtContrato().setText("");
					habilitarBotones(false);
					limpiarTodasTablas();
					try {
						cargarTablaDeudas();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
					}
				} else {
					habilitarBotones(true);
					limpiarTodasTablas();
				}
			}
		});
		
		getCbFechaTipoRol().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla(getTblBeneficios());
				try {
					cargarTablaBeneficios();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			}
		});
		
		getBtnImprimirSueldos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if ( !isGenerarReporte(true) )
						return;
					String fileName = "jaspers/rrhh/RPDatosSueldos.jasper";
					HashMap parametrosMap = reportDatosBasicos();
					reportDatosSueldos(parametrosMap,true);
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getBtnImprimirDeudas().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean conMensaje = contratoIf==null && getCbUnirContratos().isSelected();
					if ( !isGenerarReporte(conMensaje) )
						return;
					String fileName = "jaspers/rrhh/RPDatosDeudas.jasper";
					HashMap parametrosMap = reportDatosBasicos();
					reportDatosDeudas(parametrosMap,conMensaje);
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getBtnImprimirBeneficios().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if ( !isGenerarReporte(true) )
						return;
					String fileName = "jaspers/rrhh/RPDatosBeneficios.jasper";
					HashMap parametrosMap = reportDatosBasicos();
					reportDatosBeneficios(parametrosMap,true);
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getBtnImprimirVac().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if ( !isGenerarReporte(true) )
						return;
					String fileName = "jaspers/rrhh/RPDatosVacaciones.jasper";
					HashMap parametrosMap = reportDatosBasicos();
					reportDatosVacaciones(parametrosMap,true);
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getCbDeudaDetallada().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					limpiarTabla(getTblDeudas());
					cargarTablaDeudas();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			}
		});
		
	}

	private void habilitarBotones(boolean habilitar) {
		getTxtContrato().setEnabled(habilitar);
		getBtnBuscarContrato().setEnabled(habilitar);
		
		getBtnImprimirSueldos().setEnabled(habilitar);
		getBtnImprimirVac().setEnabled(habilitar);
		getBtnImprimirBeneficios().setEnabled(habilitar);
	}

	private void limpiarTodasTablas() {
		limpiarTabla(getTblSueldos());
		limpiarTabla(getTblDeudas());
		limpiarTabla(getTblVacaciones());
		limpiarTabla(getTblBeneficios());
	}
	
	private void buscarEmpleado() {
		empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				empleadoCriteria,
				JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			getCbUnirContratos().setSelected(false);
			habilitarBotones(true);
			clean();
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			try {
				getTxtEmpleado().setText(empleadoIf.getNombres()+" "+empleadoIf.getApellidos());
				buscarContrato(false);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
	}
	
	private void buscarContrato(boolean conBusqueda) throws GenericBusinessException{
		
		if ( empleadoIf == null ){
			SpiritAlert.createAlert("Elegir un empleado !!", SpiritAlert.WARNING);
			return;
		}
		
		if (conBusqueda){
			Map<String,Object> mapaEmpleado = new HashMap<String, Object>();
			mapaEmpleado.put("empleadoId", empleadoIf.getId());
			int tamanoLista= SessionServiceLocator.getContratoSessionService().getContratoListSize(mapaEmpleado);
			if ( tamanoLista > 0 ){
				
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapaEmpleado);
				contratoCriteria.setResultListSize(tamanoLista);
	
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					
					cleanTablas();
					
					contratoIf = (ContratoIf)popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
					
				}
				
			}else
				SpiritAlert.createAlert("No existen contratos activos para el empleado !!",	SpiritAlert.INFORMATION);
		} else {
			Collection<ContratoIf> contratos = SessionServiceLocator.getContratoSessionService().findContratoByEmpleadoId(empleadoIf.getId());
			if ( contratos.size() == 1 ){
				contratoIf = contratos.iterator().next();
				getTxtContrato().setText(contratoIf.getCodigo());
			} else {
				contratoIf = null;
				getTxtContrato().setText("");
			}
		}
		
		if ( contratoIf != null ){
			cargarTablaSueldos();
			cargarTablaDeudas();
			cargarTablaBeneficios();
		} else if ( contratoIf == null && getCbUnirContratos().isSelected() ) {
			cargarTablaDeudas();
		}
	}
	
	private void cargarTablaSueldos() throws GenericBusinessException{
		if (contratoIf != null){
			
			sueldos = null;
			sueldos = new ArrayList<DatosSueldo>();
			
			Collection<Object[]> historicoSueldos = SessionServiceLocator
				.getRolPagoDetalleSessionService().findRolPagoDetalleByRubroCodigoEmpleadoId(
						contratoIf.getId(),"SB");
			
			mapaSueldoFecha.clear();
	
			DefaultTableModel model = (DefaultTableModel) getTblSueldos().getModel();
	
			BigDecimal sueldoAnterior=BigDecimal.ZERO;
			for (Object[] historicoObject : historicoSueldos){
				RolPagoDetalleIf detalleIf = (RolPagoDetalleIf) historicoObject[0];				
				RolPagoIf rolpagoIf = (RolPagoIf) historicoObject[1];				
				BigDecimal sueldo=detalleIf.getValor();
				//ir agregando a un map (sueldo, fecha)... tomar cmo clave el sueldo para q no se repita
				DatosSueldo datoSueldo = new DatosSueldo();
				if(mapaSueldoFecha.get(sueldo)==null)
				{
					String fecha=Utilitarios.getFechaMesAnioUppercase(rolpagoIf.getFecha());
					Vector<Object> filaIngAsiento = new Vector<Object>();
					if(mapaSueldoFecha.size()==0){
						String incremento = "Sueldo Inicial";
						filaIngAsiento.add(incremento);
						datoSueldo.setIncremento(incremento);
					} else {
						BigDecimal incremento=sueldo.subtract(sueldoAnterior);						
						filaIngAsiento.add(incremento.toString());	
						datoSueldo.setIncremento(incremento.toString());
					}
	
					filaIngAsiento.add(fecha);
					filaIngAsiento.add(Utilitarios.redondeoValor(sueldo.doubleValue()));
					sueldoAnterior=sueldo;
					model.addRow(filaIngAsiento);
					mapaSueldoFecha.put(sueldo,fecha);
					
					datoSueldo.setFecha(fecha);
					datoSueldo.setValor(Utilitarios.redondeoValor(sueldo.doubleValue()));
					sueldos.add(datoSueldo);
				}
			}
		}
	}
	
	private void cargarTablaDeudas() throws GenericBusinessException{
		
		deudas = null;
		deudas = new ArrayList<DatosDeudas>();
		
		Map<String, Object> mapaRubrosEventuales = new HashMap<String, Object>();
		
		Calendar gci = new GregorianCalendar();
		gci.set(Calendar.DATE, 1);
		Date fci = gci.getTime();
		
		Collection<Long> contratosId = null;
		
		if ( contratoIf != null ){
			mapaRubrosEventuales.put("contratoId", contratoIf.getId());
		} else if ( contratoIf == null && getCbUnirContratos().isSelected()){
			Map<String,Object> mapaContrato = new HashMap<String, Object>();
			mapaContrato.put("empleadoId", empleadoIf.getId());
			mapaContrato.put("estado", EstadoContrato.ACTIVO.getLetra());
			Collection<ContratoIf> contratos = SessionServiceLocator.getContratoSessionService().findContratoByQuery(mapaContrato);
			if ( contratos.size() > 0 ){
				contratosId =  new ArrayList<Long>();
				for ( ContratoIf c : contratos ){
					contratosId.add(c.getId());
				}
			}
		}
		
		Map<String,java.sql.Date> mapaFechas = new HashMap<String,java.sql.Date>();
		mapaFechas.put("fechaCobroInicio",new java.sql.Date(fci.getTime()));
		mapaFechas.put("fechaCobroFin",null);
		
		Collection<Object> rubrosEventualesColleccion =  
			SessionServiceLocator.getRubroEventualSessionService()
				.findRubroEventualByQueryByEstados(
						mapaRubrosEventuales,mapaFechas,contratosId,
						getCbDeudaDetallada().isSelected(),null,
						(String[])null );
		
		totalDeudas = 0D; 
		
		DefaultTableModel modelo = (DefaultTableModel) getTblDeudas().getModel();
		Double total = 0.0;
		//Boolean detallado = getCbDeudaDetallada().isSelected();
		
		for ( Object oFila : rubrosEventualesColleccion ){
			Long rubroId = null; 
			Double valor = null;
			RubroEventualIf re = null;
			
			if ( oFila instanceof RubroEventualIf ){
				re = (RubroEventualIf)oFila;
				rubroId = re.getRubroId();
				valor = Utilitarios.redondeoValor(re.getValor().doubleValue());
			} else {
				Object[] fila = (Object[]) oFila;
				rubroId = (Long) fila[0];
				valor = Utilitarios.redondeoValor( ((BigDecimal)fila[2]).doubleValue() );
			}
			
			RubroIf r = buscarRubro(mapaRubros, rubroId);
			Object[] fila = new Object[6];
			
			fila[0] = r.getNombre();
			if ( re != null ) {
				fila[1] = re.getObservacion();
				TipoRolIf tr = mapaTipoRol.get(re.getTipoRolIdCobro());
				fila[2] = tr.getNombre();
				String fechaCobro = Utilitarios.getFechaMesAnioUppercase(re.getFechaCobro());; 
				fila[3] = fechaCobro;
				String estado = EstadoRubroEventual.getRubroEventualByLetra(re.getEstado()).toString(); 
				fila[4] = estado;
			} else {
				fila[1] = "";
				fila[2] = "";
				fila[3] = "";
				fila[4] = "";
			}
			fila[5] = valor;

			total += valor.doubleValue();
			modelo.addRow(fila);
			
			DatosDeudas dd = new DatosDeudas();
			dd.setNombre((String)fila[0]);
			dd.setTipoRol((String)fila[2]);
			dd.setFechaCobro((String)fila[3]);
			dd.setEstado((String)fila[4]);
			dd.setValor((Double)fila[5]);
			deudas.add(dd);
		}
		
		total = Utilitarios.redondeoValor(total);
		totalDeudas = total;
		Object[] fila = new Object[6];
		fila[0] = "";
		fila[1] = "";
		fila[2] = "";
		fila[3] = "";
		fila[4] = "<html><b>TOTAL</b></html>";
		fila[5] = total;
		modelo.addRow(fila);
	}
	
	private void cargarTablaBeneficios() throws GenericBusinessException{
		
		beneficios = null;
		beneficios = new ArrayList<DatosBeneficios>();
		
		/*Map<String, Object> mapaRubrosEventuales = new HashMap<String, Object>();
		mapaRubrosEventuales.put("contratoId", contratoIf.getId());
		
		Map<String,java.sql.Date> mapaFechas = new HashMap<String,java.sql.Date>();*/
		
		Collection<Object[]> beneficiosCollection =  
			SessionServiceLocator.getRolPagoSessionService().findBeneficiosByRolPagoByContratoByEmpresaId(
					contratoIf,getCbFechaTipoRol().isSelected(), Parametros.getIdEmpresa() );
		
		DefaultTableModel modelo = (DefaultTableModel) getTblBeneficios().getModel();
		
		for ( Object[] fila : beneficiosCollection ){
			Object[] filaNueva = new Object[5];
	
			String nombre = (String)fila[0];
			String fechaInicio = (String)fila[1];
			String fechaFin = (String)fila[2];
			String estado = (String)fila[3];
			Double total = (Double)fila[4];
			
			filaNueva[0] = nombre;
			filaNueva[1] = fechaInicio;
			filaNueva[2] = fechaFin;
			filaNueva[3] = estado;
			filaNueva[4] = total;
			
			DatosBeneficios bn = new DatosBeneficios();
			bn.setNombre(nombre);
			bn.setFechaInicio(fechaInicio);
			bn.setFechaFin(fechaFin);
			bn.setEstado(estado);
			bn.setValor(total);
			beneficios.add(bn);
			
			modelo.addRow(filaNueva);
		}
	}
	
	private RubroIf buscarRubro(Map<Long,RubroIf> mapaRubros,Long idRubro)
	throws GenericBusinessException {
		RubroIf rubroIf = null;
		if ( !mapaRubros.containsKey(idRubro) ){
			rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(idRubro);
			mapaRubros.put(rubroIf.getId(), rubroIf);
		} else {
			rubroIf = mapaRubros.get(idRubro);
		}
		return rubroIf;
	}
	
	private void cargarTiposRol() throws GenericBusinessException{
		Collection<TipoRolIf> tiposRol = SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
		for (TipoRolIf tr : tiposRol){
			mapaTipoRol.put(tr.getId(), tr);
		}
	}
	
	public void clean() {
		
		empleadoIf = null;
		getTxtEmpleado().setText("");
		
		contratoIf = null;
		getTxtContrato().setText("");
		
		sueldos = null;
		sueldos = new ArrayList<DatosSueldo>();
		deudas = null;
		deudas = new ArrayList<DatosDeudas>();
		beneficios = null;
		beneficios = new ArrayList<DatosBeneficios>();
		vacaciones = null;
		vacaciones = new ArrayList<DatosVacaciones>();
		
		totalDeudas = 0D;
		
		cleanTablas();
		
	}
	
	public void refresh() {
		mapaRubros = null;
		mapaRubros = new HashMap<Long, RubroIf>();
		
		mapaTipoRol = null;
		mapaTipoRol = new HashMap<Long, TipoRolIf>();
		try {
			cargarTiposRol();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar Tipos de Roles de Pago !!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cleanTablas(){
		limpiarTabla(getTblBeneficios());
		limpiarTabla(getTblDeudas());
		limpiarTabla(getTblSueldos());
		limpiarTabla(getTblVacaciones());
	}

	@Override
	public void report() {
		try {
			if ( !isGenerarReporte(true) )
				return;

			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Hoja de Vida?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
			if (opcion == JOptionPane.YES_OPTION) {

				String fileName = "jaspers/rrhh/RPHojaDeValores.jasper";

				HashMap parametrosMap = reportDatosBasicos();

				boolean conMensaje = contratoIf==null && getCbUnirContratos().isSelected();
				
				reportDatosSueldos(parametrosMap,!conMensaje);

				reportDatosDeudas(parametrosMap,!conMensaje);

				reportDatosVacaciones(parametrosMap,!conMensaje);

				reportDatosBeneficios(parametrosMap,!conMensaje);

				ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
			}


		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
	}

	private boolean isGenerarReporte(boolean conMensaje) {
		return  (contratoIf == null && getCbUnirContratos().isSelected()) || 
				(isEmpleadoSeleccionado(conMensaje) && isContratoSeleccionado(conMensaje));
	}

	private boolean isEmpleadoSeleccionado(boolean conMensaje) {
		if (empleadoIf == null ){
			if (conMensaje) SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	private boolean isContratoSeleccionado(boolean conMensaje) {
		if (contratoIf == null ){
			if (conMensaje) SpiritAlert.createAlert("Debe elegir un contrato !!", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	private void reportDatosBeneficios(HashMap parametrosMap,boolean conMensaje) {
		JRDataSource jrBeneficios = null;
		if ( isEmpleadoSeleccionado(conMensaje) && isContratoSeleccionado(conMensaje) )
			jrBeneficios = new JRBeanCollectionDataSource(beneficios);
		else 
			jrBeneficios = new JREmptyDataSource();
		parametrosMap.put("pathSubreportBeneficios", 
				Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesBeneficios.jasper");
		parametrosMap.put("beneficiosDetalle", jrBeneficios);
	}

	private void reportDatosVacaciones(HashMap parametrosMap,boolean conMensaje) {
		JRDataSource jrVacaciones = null;
		if ( isEmpleadoSeleccionado(conMensaje) && isContratoSeleccionado(conMensaje) )
			jrVacaciones = new JREmptyDataSource();
		else 
			jrVacaciones = new JREmptyDataSource();
		parametrosMap.put("pathSubreportVacaciones", 
				Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesVacaciones.jasper");
		parametrosMap.put("vacacionesDetalle", jrVacaciones);
	}

	private void reportDatosDeudas(HashMap parametrosMap,boolean conMensaje) {
		parametrosMap.put("totalDeudas", totalDeudas);
		JRDataSource jrDeudas = new JRBeanCollectionDataSource(deudas);
		parametrosMap.put("pathSubreportDeudas", 
				Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesDeudas.jasper");
		parametrosMap.put("deudasDetalle", jrDeudas);
	}

	private void reportDatosSueldos(HashMap parametrosMap,boolean conMensaje) {
		
		JRDataSource jrSueldos = null;
		if ( isEmpleadoSeleccionado(conMensaje) && isContratoSeleccionado(conMensaje) )
			jrSueldos = new JRBeanCollectionDataSource(sueldos);
		else 
			jrSueldos = new JREmptyDataSource();
		
		parametrosMap.put("pathSubreportSueldos", 
				Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesSueldos.jasper");
		parametrosMap.put("sueldosDetalle", jrSueldos);
	}

	private HashMap reportDatosBasicos() throws GenericBusinessException,
			ParseException {
		HashMap parametrosMap = new HashMap();
		MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();

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
		
		parametrosMap.put("empleado", empleadoIf.getNombres()+" "+empleadoIf.getApellidos());
		
		parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
		parametrosMap.put("usuario", Parametros.getUsuario());
		parametrosMap.put("emitido", fechaEmision);
		return parametrosMap;
	}

	@Override
	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
}