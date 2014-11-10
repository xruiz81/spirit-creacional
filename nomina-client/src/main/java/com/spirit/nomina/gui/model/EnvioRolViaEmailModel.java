package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.datos.RolPagoContratoContinuoDatos;
import com.spirit.nomina.gui.panel.JPEnvioRolViaEmail;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.gui.util.TipoContratoUtil;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.OperacionNomina;
import com.spirit.nomina.handler.RolPagoContratoDatos;
import com.spirit.nomina.handler.RubroContratoDatos;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.UtilesNomina;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.Utilitarios;

public class EnvioRolViaEmailModel extends JPEnvioRolViaEmail {
	private static final long serialVersionUID = -3051594005158289725L;
	private Map mapaTiposRol = new HashMap();
	private Map mapaTiposContrato = new HashMap();
	private ArrayList<RolPagoIf> rolPagoVector;
	private RolPagoIf rolPagoIf;
	private TipoRolIf tipoRolIf;
	private TipoContratoIf tipoContratoIf;
	private ArrayList<Map<String, Object>> rolPagoDetalleList = null;
	private Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList = null;
	private DecimalFormat formatoMes = new DecimalFormat("00");
	private Map<Long, ContratoIf> contratosMap;
	private Map<Long, EmpleadoIf> empleadosMap;
	private Collection<Map<String, Object>> rolPagoDetalleCollection = null;
	private Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo = null;
	private Collection<RolPagoContratoContinuoDatos> rolPagoDetalleCollectionTodo = null;
	private Double totalIngresos = 0.0;
	private Double totalEgresos = 0.0;
	private Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
	private Vector<ContratoIf> contratosVector;

	public EnvioRolViaEmailModel(){
		cargarComboEstado();
		initKeyListeners();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void cargarComboEstado(){
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoRolPagoDetalle.values());
		getCmbEstado().setModel(modelo);
		getCmbEstado().setSelectedItem(EstadoRolPagoDetalle.PAGADO);
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setToolTipText("Consultar Ordenes");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getBtnEnviarRolMail().setToolTipText("Enviar Email");
		getBtnEnviarRolMail().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/enviar.png"));
	}
	
	private void initListeners() {
		getBtnEnviarRolMail().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursorEspera();
				rolPagoDetalleCollectionTodo = null;
				rolPagoDetalleMapaTodo = null;
				
				consultarRolPagoPorContrato();
				
				JTable tblDetalleRolPago = getTblDetalleRolPago();
				
				Vector<ContratoIf> contratosSeleccionados = new Vector<ContratoIf>();
				
				for (int i=0; i<contratosVector.size(); i++) {
					int fila = tblDetalleRolPago.convertRowIndexToModel(i);
					if ((Boolean) tblDetalleRolPago.getValueAt(fila, 0)) {
						ContratoIf contrato = contratosVector.get(fila);
						contratosSeleccionados.add(contrato);
					}
				}
				
				String mensaje = enviarRolViaEmail(contratosSeleccionados);
				
				setCursorNormal();
				SpiritAlert.createAlert(mensaje, SpiritAlert.INFORMATION);	
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaRolPago();
			}			
		});
		
		getTblRolPago().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				seleccionarFila();
			}			
		});
		
		getTblRolPago().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent arg0) {
				seleccionarFila();
			}
		});
		
		getBtnSeleccionarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarTodo();				
			}
		});
	}
	
	public String enviarRolViaEmail(Vector<ContratoIf> contratosSeleccionados) {
		
		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
		//String usuario = Parametros.getUsuario();
		String mes = getCmbMonth().getSelectedItem().toString();
		String rutaCarpetaReportes = Parametros.getRutaCarpetaReportes();
		String anio = getCmbYear().getSelectedItem().toString();				
						
		String mensaje = "No ha sido posible el envio de correo.";
		
		try {
			Map queryMap = new HashMap();
			queryMap.put("empresaId", empresa.getId());
			queryMap.put("codigo", "PDF_TMP_DIR");
			Iterator<ParametroEmpresaIf> it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(queryMap).iterator();
			if (it.hasNext()) {
				String directorioTemporalPDF = it.next().getValor();
				if ( rolPagoIf != null) {			
					if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
						mensaje = "Rol de pago no tiene detalle.";
						return mensaje;
					}
					String fileName = "jaspers/nomina/RPRolPagoContratoPorPagina.jasper";
					HashMap parametrosMap = new HashMap();
					parametrosMap.put("codigoReporte", "NOMI.T.REM");
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("ciudad", ciudad.getNombre());
					//parametrosMap.put("usuario", usuario);
					parametrosMap.put("usuario", "RRHH");
					parametrosMap.put("mes", mes);
					parametrosMap.put("anio", rolPagoIf.getAnio());
					if ( tipoContratoIf.getNombre().contains("PROFESIONAL") ){
						parametrosMap.put("tituloRol","L I Q U I D A C I Ó N");
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre().replace("ROL", "").replace("DE", ""));
					}
					else if ( tipoContratoIf.getNombre().contains("DEPENDENCIA") ){
						parametrosMap.put("tituloRol","R O L   D E   P A G O   P O R   C O N T R A T O");
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre());
					}

					if (rutaCarpetaReportes != null && !"".equals(rutaCarpetaReportes) ){
						parametrosMap.put("pathSubreportEgresos", rutaCarpetaReportes+"/"+"jaspers/nomina/RPRolPagoContratoDetalleEgresos.jasper");
						parametrosMap.put("pathSubreportIngresos", rutaCarpetaReportes+"/"+"jaspers/nomina/RPRolPagoContratoDetalleIngresos.jasper");
					}				
					else
						SpiritAlert.createAlert("La ruta del directorio de Reportes no está establecida en Parametros de Empresa.", SpiritAlert.ERROR);
						
					
					for(ContratoIf contrato : contratosSeleccionados){
						Collection contratoCollection = transformarContratoCollection(contrato, rolPagoDetalleMapaTodo);
						JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(contratoCollection);
						EmpleadoIf empleado = empleadosMap.get(contrato.getEmpleadoId());
						File directorio = new File(directorioTemporalPDF + mes + "-" + anio);
						directorio.mkdir();
						System.out.println("uno");
						ReportModelImpl.processReportToPDF(fileName, parametrosMap, dataSourceDetail, directorio.getAbsolutePath()+"/"+empleado.getCodigo()+".pdf");
						System.out.println("seis");
						contratoCollection = null;
						if (empleado.getEmailOficina() != null && !empleado.getEmailOficina().equals("")) {
							
							//File attachment = new File(directorio.getAbsolutePath()+"/"+empleado.getCodigo()+".pdf");
							//attachment.createNewFile();
							
							File archivo = new File(directorio.getAbsolutePath()+"/"+empleado.getCodigo()+".pdf");
							FileInputStreamSerializable attachment = new FileInputStreamSerializable(archivo, archivo.getName());
													
							SessionServiceLocator.getRolPagoSessionService().enviarEmail(empresa.getId(), empleado, mes, anio, 
									Parametros.getUrlCarpetaServidor(), attachment);
													
						} else {
							SpiritAlert.createAlert("No se ha podido enviar email a: " + empleado.getNombres() + " " + empleado.getApellidos() +"", SpiritAlert.INFORMATION);
						}
					}					
					mensaje = "Envío realizado exitosamente";
				}
			} else {
				mensaje = "No se ha definido directorio temporal para archivos PDF";
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return mensaje;
	}
	
	private Collection transformarContratoCollection(ContratoIf contrato, Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo) {
		Collection<RolPagoContratoDatos> coleccion = new ArrayList<RolPagoContratoDatos>();
		if (rolPagoDetalleMapaTodo!=null){
			RolPagoContratoDatos datoContrato = rolPagoDetalleMapaTodo.get(contrato.getId());
			if (datoContrato != null) {
				Collection<RubroContratoDatos> ingresos = datoContrato.getIngresosRubro();
				JRBeanCollectionDataSource ingresosBeans = new JRBeanCollectionDataSource(ingresos);
				datoContrato.setIngresos(ingresosBeans);
				ingresos = null;
				Collection<RubroContratoDatos> egresos = datoContrato.getEgresosRubro();
				JRBeanCollectionDataSource egresosBeans = new JRBeanCollectionDataSource(egresos);
				datoContrato.setEgresos(egresosBeans);
				egresos = null;
				
				//cargo del empleado
				String cargoEmpleado = "";
				try {
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contrato.getEmpleadoId());
					if(empleado.getTipoempleadoId() != null){
						TipoEmpleadoIf tipoEmpleado = SessionServiceLocator.getTipoEmpleadoSessionService().getTipoEmpleado(empleado.getTipoempleadoId());
						cargoEmpleado = tipoEmpleado!=null? tipoEmpleado.getNombre() : "";
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}					
				datoContrato.setCargoEmpleado(cargoEmpleado);
				
				coleccion.add(datoContrato);
				Collections.sort((ArrayList<RolPagoContratoDatos>)coleccion, comparadorNombre);
			}
		}
		return coleccion;
	}
	
	Comparator<RolPagoContratoDatos> comparadorNombre = new Comparator<RolPagoContratoDatos>(){
		public int compare(RolPagoContratoDatos o1, RolPagoContratoDatos o2) {
			return o1.getNombreEmpleado().compareTo(o2.getNombreEmpleado());
		}
	};
	
	
	private void seleccionarFila() {
		rolPagoIf = null;
		getBtnSeleccionarTodos().setText("Seleccionar Todos");
		setCursorEspera();
		int filaSeleccionada = getTblRolPago().getSelectedRow();
		if (filaSeleccionada >= 0) {
			try {
				int fila = getTblRolPago().convertRowIndexToModel(filaSeleccionada);
				rolPagoIf = rolPagoVector.get(fila);
				tipoRolIf = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
				tipoContratoIf = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato,rolPagoIf.getTipocontratoId());
				limpiarTabla(getTblDetalleRolPago());
				cargarTablaRolPagoDetalle();
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error general Rol de Pago", SpiritAlert.ERROR);
			}
			setCursorNormal();
		}
	}
	
	private void seleccionarTodo(){
		DefaultTableModel modelo  = (DefaultTableModel) getTblDetalleRolPago().getModel();
		if (getBtnSeleccionarTodos().getText().equals("Seleccionar Todos")) {
			for ( int i=0 ; i < modelo.getRowCount() ; i++ )
				modelo.setValueAt(true, i, 0);
			getBtnSeleccionarTodos().setText("Deseleccionar Todos");
		} else {
			for ( int i=0 ; i < modelo.getRowCount() ; i++ )
				modelo.setValueAt(false, i, 0);
			getBtnSeleccionarTodos().setText("Seleccionar Todos");
		}
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long, ContratoIf> mapearContratos() {
		Map<Long, ContratoIf> contratosMap = new HashMap<Long, ContratoIf>();
		try {
			Iterator<ContratoIf> it = SessionServiceLocator.getContratoSessionService().getContratoList().iterator();
			while (it.hasNext()) {
				ContratoIf contrato = it.next();
				contratosMap.put(contrato.getId(), contrato);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear contratos", SpiritAlert.ERROR);
		}
		return contratosMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long, EmpleadoIf> mapearEmpleados() {
		Map<Long, EmpleadoIf> empleadosMap = new HashMap<Long, EmpleadoIf>();
		try {
			Iterator<EmpleadoIf> it = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				EmpleadoIf empleado = it.next();
				empleadosMap.put(empleado.getId(), empleado);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear empleados", SpiritAlert.ERROR);
		}
		return empleadosMap;
	}
	
	private void cargarTablaRolPagoDetalle() throws GenericBusinessException {
		contratosMap = mapearContratos();
		empleadosMap = mapearEmpleados();
		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		//Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra());
		EstadoRolPagoDetalle estado = (EstadoRolPagoDetalle)getCmbEstado().getSelectedItem();
		Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,estado.getLetra());
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = (DefaultTableModel) getTblDetalleRolPago().getModel();
		
		if (modelo != null) {
			//rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoSessionService().crearColeccionContratos(contratosIdCollection,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra(),true);
			rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoSessionService().crearColeccionContratos(contratosIdCollection,rolPagoIf,estado.getLetra(),true,false,false,"",false);
			cargarTablaRolPagoDetalleQyM(rolPagoDetalleList, modelo);
			getTblDetalleRolPago().validate();
			getTblDetalleRolPago().repaint();
		} else 
			SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en la tabla !!", SpiritAlert.WARNING);
	}

	private void cargarTablaRolPagoDetalleQyM(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo) throws GenericBusinessException {
		contratosVector = new Vector<ContratoIf>();
		for ( Map<String,Object> mapa : rolPagoContratoCollection ){
			Vector<Object> fila = crearFilaTablaRolPagoDetalleQyM(rolPagoIf,mapa);
			modelo.addRow(fila);
		}
	}
	
	private Vector<Object> crearFilaTablaRolPagoDetalleQyM(RolPagoIf rolPagoIf,Map<String, Object> mapa) throws GenericBusinessException {
		Long contratoId = (Long)mapa.get("contratoId");
		ContratoIf contrato = contratosMap.get(contratoId);
		EmpleadoIf empleado = empleadosMap.get(contrato.getEmpleadoId());
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double totalIngresos = Utilitarios.redondeoValor( (Double)mapa.get("totalIngresos") );
		Double totalEgresos = Utilitarios.redondeoValor( (Double)mapa.get("totalDescuentos") );
		Double total = Utilitarios.redondeoValor( totalIngresos - totalEgresos );
		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(empleado.getEmailOficina()!=null?empleado.getEmailOficina():"");
		fila.add(totalIngresos);
		fila.add(totalEgresos);
		fila.add(total);
		contratosVector.add(contrato);
		return fila;
	}
	
	private void cargarTablaRolPago() {
		try {
				cleanTable(getTblRolPago());
				TipoRolIf tipoRol = (TipoRolIf) getCmbTipoRol().getSelectedItem();
				if (tipoRol == null) {
					SpiritAlert.createAlert("Debe seleccionar tipo de rol", SpiritAlert.WARNING);
					return;
				}
				
				//solo Relacion de Dependencia
				Long idTipoContratoRelacionDependencia = 0L;
				Collection relacionDependenciaC = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByCodigo("RD");
				if(relacionDependenciaC.size() > 0){
					TipoContratoIf TCrelacionDependencia = (TipoContratoIf)relacionDependenciaC.iterator().next();
					idTipoContratoRelacionDependencia = TCrelacionDependencia.getId();
				}
				
				int mes = Utilitarios.getMesInt(getCmbMonth().getSelectedItem().toString()) + 1;
				
				Map queryMap = new HashMap();
				queryMap.put("tiporolId", tipoRol.getId());
				queryMap.put("mes", formatoMes.format(mes));
				
				if(idTipoContratoRelacionDependencia.compareTo(0L) == 1)
					queryMap.put("tipocontratoId", idTipoContratoRelacionDependencia);
				
				if (getCmbYear().getSelectedItem() != null)
					queryMap.put("anio", getCmbYear().getSelectedItem().toString());
				
				rolPagoVector = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(queryMap);
				DefaultTableModel tableModel = (DefaultTableModel) getTblRolPago().getModel();
				for (RolPagoIf rolPagoIf : rolPagoVector) {
					Vector<Object> fila = crearFilaTablaRolPago(rolPagoIf);
					tableModel.addRow(fila);
				}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar tabla de roles de pago", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cleanTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = table.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);	
	}
	
	private Vector<Object> crearFilaTablaRolPago(RolPagoIf rolPagoIf) throws GenericBusinessException {
		TipoRolIf tipoRol = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol, rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		TipoContratoIf tipoContrato = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato,rolPagoIf.getTipocontratoId());
		String nombreTipoContrato = tipoContrato.getNombre();
		int mesInteger = Integer.parseInt(rolPagoIf.getMes());
		String mesString = Utilitarios.getNombreMes(mesInteger);
		Integer anio = Integer.valueOf(rolPagoIf.getAnio());
		String estado = rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())?EstadoRolPago.GENERADO.toString():EstadoRolPago.CERRADO.toString();
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
		fila.add(nombreTipoContrato);
		fila.add(mesString);
		fila.add(anio);
		fila.add(estado);
		return fila;
	}
	
	private void consultarRolPagoPorContrato() {
		try {				
			if ( rolPagoIf != null ){
				//PARA RUBROS NO EVENTUALES 
				Collection<Long> contratosIdCollection = null;
				TipoRol tipoRol = TipoRol.obtenerTipoRol((TipoRolIf) getCmbTipoRol().getSelectedItem());
				
				//contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra());
				//rolPagoDetalleCollection = (ArrayList) SessionServiceLocator.getRolPagoSessionService().crearColeccionContratos(contratosIdCollection,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra(),true);
				EstadoRolPagoDetalle estado = (EstadoRolPagoDetalle)getCmbEstado().getSelectedItem();				
				contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,estado.getLetra());
				rolPagoDetalleCollection = (ArrayList) SessionServiceLocator.getRolPagoSessionService().crearColeccionContratos(contratosIdCollection,rolPagoIf,estado.getLetra(),true,false,false,"",false);
								
				rolPagoDetalleCollectionTodo = new ArrayList<RolPagoContratoContinuoDatos>();
				rolPagoDetalleMapaTodo = new HashMap<Long, RolPagoContratoDatos>();
				if ( tipoRol == TipoRol.MENSUAL || tipoRol == TipoRol.QUINCENAL ){
					crearColeccionDetalleContinuoIngresoEgresoNormales(rolPagoDetalleCollection);
					crearColeccionDetalleIngresoEgresoNormales(rolPagoDetalleCollection);
				} else if ( tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA 
						|| tipoRol == TipoRol.VACACIONES  
						|| tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO	)
					crearColeccionDetalleIngresoEgresoProvisionados(rolPagoDetalleCollection);
				else 
					SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla !!", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al consultar Rol por Contrato", SpiritAlert.ERROR);
		}	
	}
	
	private void crearColeccionDetalleIngresoEgresoNormales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		//INGRESOS Y EGRESOS NORMALES
		
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoId); 
			datosContrato.setContratoId(contratoId);
			
			Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
			Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
			
			Double totalIngresosContrato = (Double)mapa.get("totalIngresos");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			Double totalEgresosContrato = (Double)mapa.get("totalDescuentos");
			totalEgresos = Utilitarios.redondeoValor(totalEgresosContrato);
			
			String nombre = (String)mapa.get("nombreEmpleado");
			datosContrato.setNombreEmpleado(nombre);
			
			Collection<RolPagoDetalleIf> rolPagoDetalles = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
			
			for ( RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles ){
				RubroContratoDatos datos = new RubroContratoDatos();
				Double valor = Utilitarios.redondeoValor(rolPagoDetalle.getValor().doubleValue());
				datos.setValor(valor);
				datos.setEstado(rolPagoDetalle.getEstado());
				datos.setObservacion(rolPagoDetalle.getObservacion());
				datos.setAsientoId(rolPagoDetalle.getAsientoId());
				
				RubroIf rubro = null;
				try {
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datos.setEventual(false);
					}
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datos.setValor( rolPagoDetalle.getValor().doubleValue() );
						datos.setEventual(true);
						datos.setObservacion(rubroEventual.getObservacion());
					}
					datos.setNombreRubro(rubro.getNombre());
					
					OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);
	
					if ( ingresoEgreso == OperacionNomina.INGRESO ){
						ingresos.add(datos);
					}
					else{
						egresos.add(datos);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			}
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
		}
	}
	
	private void crearColeccionDetalleContinuoIngresoEgresoNormales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		//INGRESOS Y EGRESOS NORMALES
		int contadorDetalleTodos = 1;
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			boolean esPar = contadorDetalleTodos % 2 == 0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			
			
			Collection<RolPagoDetalleIf> rolPagoDetalles = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
			String nombre = (String)mapa.get("nombreEmpleado");
			
			Double totalIngresosContrato = (Double)mapa.get("totalIngresos");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			Double totalEgresosContrato = (Double)mapa.get("totalDescuentos");
			totalEgresos = Utilitarios.redondeoValor(totalEgresosContrato);
			
			
			ArrayList<RolPagoContratoContinuoDatos> ingresosDetalle = new ArrayList<RolPagoContratoContinuoDatos>();
			ArrayList<RolPagoContratoContinuoDatos> egresosDetalle = new ArrayList<RolPagoContratoContinuoDatos>();
			
			
			
			for ( RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles ){
				
				RolPagoContratoContinuoDatos datosContrato = new RolPagoContratoContinuoDatos(); 
					
				datosContrato.setContratoId(contratoId);
				datosContrato.setTotalIngresos(totalIngresos);
				datosContrato.setTotalEgresos(totalEgresos);
				datosContrato.setNombreEmpleado(nombre);
				datosContrato.setObservacion(rolPagoDetalle.getObservacion());
				try {
					RubroIf rubro = null;
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datosContrato.setEventual(false);
					}
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datosContrato.setEventual(true);
						datosContrato.setObservacion(rubroEventual.getObservacion());
					}
					
					OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);
					Double valor = Utilitarios.redondeoValor(rolPagoDetalle.getValor().doubleValue());
					
					datosContrato.setEstado(rolPagoDetalle.getEstado());
						
					if ( ingresoEgreso == OperacionNomina.INGRESO ){
						datosContrato.setValorIngreso(valor);
						datosContrato.setNombreRubroIngreso(rubro.getNombre());
						ingresosDetalle.add(datosContrato);
					}
					else{
						datosContrato.setValorEgreso(valor);
						datosContrato.setNombreRubroEgreso(rubro.getNombre());
						egresosDetalle.add(datosContrato);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			
			}
			
			int maximo = Math.max(ingresosDetalle.size(), egresosDetalle.size());
			
			for ( int i = 0 ; i < maximo ; i++ ){
				RolPagoContratoContinuoDatos ingreso = null;
				RolPagoContratoContinuoDatos egreso = null;
				
				RolPagoContratoContinuoDatos nuevo = new RolPagoContratoContinuoDatos();
				nuevo.setContratoId(contratoId);
				nuevo.setTotalIngresos(totalIngresos);
				nuevo.setTotalEgresos(totalEgresos);
				nuevo.setNombreEmpleado(nombre);
				
				if ( i < ingresosDetalle.size() ){
					ingreso = ingresosDetalle.get(i);
					nuevo.setValorIngreso(ingreso.getValorIngreso());
					nuevo.setNombreRubroIngreso(ingreso.getNombreRubroIngreso());
				} else {
					nuevo.setValorIngreso(null);
					nuevo.setNombreRubroIngreso(null);
				}
				if ( i < egresosDetalle.size() ){
					egreso = egresosDetalle.get(i);
					nuevo.setValorEgreso(egreso.getValorEgreso());
					nuevo.setNombreRubroEgreso(egreso.getNombreRubroEgreso());
				} else {
					nuevo.setValorEgreso(null);
					nuevo.setNombreRubroEgreso(null);
				}
				
				rolPagoDetalleCollectionTodo.add(nuevo);
				
			}
			
			int numeroMinimoDetalle = 8; 
			if ( maximo <= numeroMinimoDetalle ){
				aumentarDetalleEnBlanco(contratoId, nombre, maximo,
						numeroMinimoDetalle);
				contadorDetalleTodos++;
			} /*else if ( maximo == numeroMinimoDetalle  ){
				aumentarDetalleEnBlanco(contratoId, nombre, maximo,
						numeroMinimoDetalle);
				contadorDetalleTodos++;
			}*/ else {
				//if  ( maximo > 7 && maximo <= 10 ) {
				//	contadorDetalleTodos += 2;
				//} else 
				if ( maximo >= 10 && maximo <= 25 ){
					if ( !esPar )
						aumentarDetalleEnBlanco(contratoId, nombre, 0,5);
					else 
						aumentarDetalleEnBlanco(contratoId, nombre, 0,33-maximo);
					contadorDetalleTodos += 2;
				} else {
					contadorDetalleTodos++;
				}
			}
		}
	}
	
	private void crearColeccionDetalleIngresoEgresoProvisionados(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		//INGRESOS Y EGRESOS NORMALES
		
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoId); 
			datosContrato.setContratoId(contratoId);
			
			Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
			//Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
			
			Double totalIngresosContrato = (Double)mapa.get("total");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			
			String nombre = (String)mapa.get("nombreEmpleado");
			datosContrato.setNombreEmpleado(nombre);
			
			Collection<RolPagoDetalleIf> rolPagoDetalles = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
			
			for ( RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles ){
				RubroContratoDatos datos = new RubroContratoDatos();
				Double valor = Utilitarios.redondeoValor(rolPagoDetalle.getValor().doubleValue());
				datos.setValor(valor);
				datos.setEstado(rolPagoDetalle.getEstado());
				RubroIf rubro = null;
				try {
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datos.setEventual(false);
					}
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datos.setEventual(true);
						datos.setObservacion(rubroEventual.getObservacion());
					}
					datos.setNombreRubro(rubro.getNombre());
					
					ingresos.add(datos);
					
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			}
			
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
		}
	}
	
	private RubroIf verificarRubrosEnMapa(Long idRubro)throws GenericBusinessException {
		RubroIf rubroIf = null;
		if ( !mapaRubros.containsKey(idRubro) ){
			rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(idRubro);
			mapaRubros.put(rubroIf.getId(), rubroIf);
		} else {
			rubroIf = mapaRubros.get(idRubro);
		}
		return rubroIf;
	}
	
	private RolPagoContratoDatos verificarMapaContrato(Long contratoId) {

		RolPagoContratoDatos datosContrato = rolPagoDetalleMapaTodo.get(contratoId);

		if ( datosContrato == null ){

			datosContrato = new RolPagoContratoDatos();
			Collection<RubroContratoDatos> ingresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setIngresosRubro(ingresos);

			Collection<RubroContratoDatos> egresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setEgresosRubro(egresos);
			rolPagoDetalleMapaTodo.put(contratoId, datosContrato);
		}

		return datosContrato;
	}
	
	private void aumentarDetalleEnBlanco(Long contratoId, String nombre,
			int base, int cantidadAAumentar) {
		for ( int i = base ; i <= cantidadAAumentar ; i++ ){
			RolPagoContratoContinuoDatos nuevo = new RolPagoContratoContinuoDatos();
			nuevo.setContratoId(contratoId);
			nuevo.setTotalIngresos(totalIngresos);
			nuevo.setTotalEgresos(totalEgresos);
			nuevo.setNombreEmpleado(nombre);
			nuevo.setValorIngreso(null);
			nuevo.setNombreRubroIngreso(null);
			nuevo.setValorEgreso(null);
			nuevo.setNombreRubroEgreso(null);
			rolPagoDetalleCollectionTodo.add(nuevo);
		}
	}	
		
	/*public void enviarRolEmail(){
		//johy
		
		int rowCount=-1;
		DefaultTableModel modelo = null;
		
		int columnaTipoPago = -1;
		if ( getRbNormal().isSelected() ){
			if ( tablaQuincenalMensualSeleccionada ){
				rowCount = getTblRolPagoDetalleQyM().getRowCount();
				modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();				 
				columnaTipoPago = COLUMNA_DETALLE_QYM_TIPO_PAGO;			 
			}
			else{
				rowCount = getTblRolPagoDetalleAportesDecimos().getRowCount();
				modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();				
				columnaTipoPago = COLUMNA_DETALLE_AYD_TIPO_PAGO;				
			}
		} else if ( getRbAnticipos().isSelected() ){
			rowCount = getTblRolPagoDetalleAnticipos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();			
			columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;			
		}
		
		String identificador=new Long(new java.util.Date().getTime()).toString();
		
		for ( int i = 0 ; i < rowCount ; i++){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, 0);			
			if (seleccionado  ){				
				String nombre = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				String email = (String) modelo.getValueAt(i, columnaTipoPago);				
				//TipoPagoIf tipoPagoIf = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);
				Map<String, Object> rolPagoDetalleSeleccionadosMapa = new HashMap<String, Object>();
				Map<String, Object> mapa = rolPagoDetalleList.get(i);
				
				Long contratoId = (Long) mapa.get("contratoId");
				ContratoIf contratoIf = null;				 
				try {
					
					
					String identificacion=(String)identificacionEmpleadosMap.get(nombre);					
					contratoIf =SessionServiceLocator.getContratoSessionService().getContrato(contratoId);					
					SpiritModel panelRolPagoPorContrato = (SpiritModel) new ConsultaRolContratoModel(tipoRolIf,rolPagoIf,contratoIf,true,identificador);
					
					String mesSeleccionadoStringNumero = rolPagoIf.getMes();
					int mesSeleccionadoInt = Integer.parseInt( mesSeleccionadoStringNumero );
					String mesSeleccionado = Utilitarios.getNombreMes(mesSeleccionadoInt);

					String anioSeleccionado = rolPagoIf.getAnio();	

					String archivoPdf=identificador+"_"+identificacion+"_"+mesSeleccionado+"_"+anioSeleccionado;
					
					enviarMailEmpleadosMap.put(email,archivoPdf);//asocia el email con el nombre del archivo
			        
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		if(enviarMailEmpleadosMap!=null){
			
			
			try{
			jdAutorizacionRequeridaModel = new AutorizacionRequeridaModel(Parametros.getMainFrame());
			jdAutorizacionRequeridaModel.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdAutorizacionRequeridaModel.setLocation(x, y);
			jdAutorizacionRequeridaModel.pack();
			jdAutorizacionRequeridaModel.setModal(true);
			jdAutorizacionRequeridaModel.setVisible(true);
			if(jdAutorizacionRequeridaModel.correctousr)			
			{
				boolean ok=true;
				String from="tecnico@creacional.com";
				String clave="tecnico";
				
				from=jdAutorizacionRequeridaModel.getUsername()+"@creacional.com";
				clave=jdAutorizacionRequeridaModel.getPassword();
				
				Iterator mapaMesValorIt = enviarMailEmpleadosMap.keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String emailTo = (String)mapaMesValorIt.next();
					String pdfNombre= (String)enviarMailEmpleadosMap.get(emailTo);										
					System.out.println(pdfNombre+"MAIL ENVIADO A :"+emailTo);
					emailTo="johy_gf@hotmail.com";	
					
					try{
						enviarEmailRolesAdjuntoVarios(emailTo,pdfNombre,from,clave);	
					}
					catch (Exception e)
			        {
						ok=false;
			            e.printStackTrace();            
			        }
					
				}
				if(ok)
					SpiritAlert.createAlert("Mails enviados con éxito!!", SpiritAlert.INFORMATION);
				else
					SpiritAlert.createAlert("Surgió un error, revise su conexion a Internet", SpiritAlert.INFORMATION);
			}	
			else{
				SpiritAlert.createAlert("Verifique datos de usuario y contraseña", SpiritAlert.INFORMATION);		
			}
			
			}
			catch (Exception e)
	        {
	            e.printStackTrace();            
	        }
			
			
		}
		
		enviarMailEmpleadosMap.clear();
	}
	
	
	public void enviarEmailRolesAdjuntoVarios(String emailTo,String pdfNombre,String from,String clave)
    {    try
        {
            // Propiedades de la conexión
            Properties props = new Properties();//mail
            props.clear();
            props.setProperty("mail.smtp.host", "mail.creacional.com");
            props.setProperty("mail.smtp.auth", "FALSE");          
            props.setProperty("mail.smtp.port", "25");            
            props.setProperty("mail.smtp.user", from);
            props.setProperty("mail.smtp.pass", clave);
            props.setProperty("mail.smtps.password", clave);
          
             // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
        
         // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText("Texto del mensaje");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();            //
            adjunto.setDataHandler(new DataHandler(new FileDataSource("c:\\rolesEnviadosMail\\"+pdfNombre+".pdf")));
            adjunto.setFileName(pdfNombre+".pdf");
            adjunto.setHeader( "Content-Type", "application/pdf;" );
            //adjunto.setHeader( "Content-Transfer-Encoding", "base64;");
            
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
           

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			message.setSubject("Envio de Rol por mail");
			message.setContent(multiParte);
			
			// Lo enviamos.
			Transport t = session.getTransport("smtp");//t.connect("sistemas@creacional.com","sistemas");
			t.connect();
			t.sendMessage(message, message.getAllRecipients());
			// Cierre.
			t.close();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();            
        }
    } 
	
	 
	private boolean esNumeroPositivo(String numeroString ){
		try{
			Integer numero = Integer.valueOf(numeroString);
			if ( numero <= 0 )
				return false;
			numero = null;
		} catch(Exception e ){
			return false;			
		}
		return true;
	}
*/
	@Override
	public void clean() {
		/*rolPagoIfVector = null;
		rolPagoIf = null;
		rolPagoDetalleList = null;
		rolPagoDetalleSeleccionadosList = null;

		mapaTiposContrato = null;
		mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
		
		mapaTiposRol = null;
		mapaTiposRol = new HashMap<Long,TipoRolIf>();

		calInicio = null;
		calFin = null;
		
		ocultarTablasDetalle();
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaInicio().repaint();
		getCmbFechaFin().setDate(null);
		getCmbFechaFin().repaint();
		
		limpiarTabla(getTblRolPago());
		limpiarTabla(getTblRolPagoDetalleQyM());
		limpiarTabla(getTblRolPagoDetalleAnticipos());
		limpiarTabla(getTblRolPagoDetalleAportesDecimos());*/
	}
/*
	private void ocultarTablasDetalle() {
		getSpTblRolPagoDetalleQyM().setVisible(false);
		getSpTblRolPagoDetalleAyD().setVisible(false);
		getSpTblRolPagoDetalleAnticipos().setVisible(false);
	}
*/
	@Override
	public void delete() {
	}

	public void find() {
	}

	public void report() {
		
	}

	public void save() {
	}

	public void update() {
		 
	}
	
	 
	  
	public void authorize() {
		//super.authorize();
	}

	@Override
	public boolean validateFields() {
		return false;
	}

	public void addDetail() {
	}

	public boolean isEmpty() {
		return false;
	}

	public void refresh() {
		showSaveMode();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void showFindMode() {
		clean();
		//cargarTablaRolPago(false);
	}

	public void showSaveMode() {
		setSaveMode();
		showFindMode();
		loadCombos();
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}
	
	private void loadCombos() {
		cargarComboTipoRol();
		cargarComboAnio();
		ModelUtil.establecerCmbAnio(getCmbYear());
	}
	
	private void cargarComboTipoRol() {
		try {
			List<Object> tiposRol = (ArrayList<Object>) SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoRol(), tiposRol);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar tipos de rol", SpiritAlert.ERROR);
		}
	}
	
	private void cargarComboAnio() {
		try {
			List<Object> rolesPago = (ArrayList<Object>) SessionServiceLocator.getRolPagoSessionService().getYearList();
			refreshCombo(getCmbYear(), rolesPago);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar tipos de rol", SpiritAlert.ERROR);
		}
	}
}
