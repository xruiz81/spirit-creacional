package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.spirit.cartera.handler.FormatoPagoRolData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.ObservacionesModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.entity.RolPagoData;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPRolPago;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.gui.util.RolPagoUtil;
import com.spirit.nomina.gui.util.TipoContratoUtil;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.DatoObservacion;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class RolPagoModel extends JPRolPago {

	private static final long serialVersionUID = -7292916188285266727L;
	
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	private TipoRolIf tipoRolIf = null;
	private TipoContratoIf tipoContratoIf = null;
	private String mesSeleccionado = "";
	private String anioSeleccionado = "";
	private Map<String,Object> mapaParametros = null;
	private Boolean rolGenerado = false;
	private RolPagoIf rolPagoIf = null;
	private ArrayList<RolPagoIf> rolPagoCollection = null;
	private Collection<Long> contratosIdCollection = null;	
	private Map<Long,TipoContratoIf> mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
	private Map<Long,TipoRolIf> mapaTiposRol = new HashMap<Long,TipoRolIf>();	
	private TipoRol tipoRol; 	
	private Calendar calInicio = null;
	private Calendar calFin = null;	
	Map<String,Object> mapaResultado = null;
	private static final String TODOS = "TODOS";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	
	public RolPagoModel(){
		cargarComboOficina();
		cargarComboDepartamento();
		iniciarComponentes();
		setSorterTable(getTblRolPago());
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
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
	
	private void initListeners(){
		
		getCmbTipoRol().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//verificarTiporolMesAnioReporte();
				tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
				if(tipoRolIf != null){
					tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
					
					if(tipoRol == TipoRol.MENSUAL){
						getCbReporteCostes().setEnabled(true);
					}else{
						getCbReporteCostes().setSelected(false);
						getCbReporteCostes().setEnabled(false);
					}
					
					getBtnGenerarReporte().setEnabled(true);
					getBtnCerrarRol().setEnabled(false);
					setSaveMode();
				}				
			}
		});
		
		getCmbTipoContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
				getBtnGenerarReporte().setEnabled(true);
				getBtnCerrarRol().setEnabled(false);
				setSaveMode();
			}
		});
		
		getCmbMes().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//verificarTiporolMesAnioReporte();
				mesSeleccionado = (String)getCmbMes().getSelectedItem();
				getBtnGenerarReporte().setEnabled(true);
				getBtnCerrarRol().setEnabled(false);
				setSaveMode();
			}
		});
		
		getCmbAnio().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//verificarTiporolMesAnioReporte();
				anioSeleccionado = (String) getCmbAnio().getSelectedItem();
				getBtnGenerarReporte().setEnabled(true);
				getBtnCerrarRol().setEnabled(false);
				setSaveMode();
			}
		});
		
		getBtnGenerarReporte().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				accionGenerarReporte();
			}
		});
		
		getBtnCerrarRol().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				accionCerrarReporte();
			}
		});
		
		getTblRolPago().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
				seleccionarFilaTabla();
			}
		});
		
		getTblRolPago().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				if ( arg0.getClickCount() != 2 )
					seleccionarFilaTabla();
			}

			public void mouseClicked(MouseEvent arg0) {
				if ( arg0.getClickCount() == 2 ){
					seleccionarFilaTabla();
					report();
				}
			}			
		});
		
		getBtnFiltrar().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				filtrarBusquedaRolPago();
			}			
		});
		
	}
	
	private void filtrarBusquedaRolPago() {
		try{
			calInicio = null;
			calFin = null;
			
			tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
			
			tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
			
			if ( getCmbFechaInicio().getDate() != null ){
				calInicio = new GregorianCalendar();
				calInicio.setTime( getCmbFechaInicio().getDate() );
				calInicio.set(Calendar.DATE, 1);
				if ( getCmbFechaFin().getDate() != null ){
					calFin = new GregorianCalendar();
					calFin.setTime( getCmbFechaFin().getDate() );
					calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
					
				} else {
					calFin = new GregorianCalendar();
					calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
					if ( calFin.compareTo(calInicio) < 0 ){
						calFin.setTime(calInicio.getTime());
						calFin.set(Calendar.YEAR, calFin.get(Calendar.YEAR)+1);
					}
				}
			} else {
				String mesS = (String)getCmbMes().getSelectedItem();
				int mes = Utilitarios.getMesInt(mesS) ;
				if ( mesSeleccionado == null ){
					SpiritAlert.createAlert("Debe seleccionar un mes !!", SpiritAlert.WARNING);
					return;
				}
				String anioS = (String) getCmbAnio().getSelectedItem();
				int anio = Integer.valueOf(anioS);
			
				calInicio = new GregorianCalendar();
				calInicio.set(Calendar.DATE, 1);
				calInicio.set(Calendar.MONTH, mes);
				calInicio.set(Calendar.YEAR, anio);
				
				calFin = new GregorianCalendar();
				calFin.set(Calendar.MONTH, mes);
				calFin.set(Calendar.YEAR, anio);
				calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
			}
			
			limpiarTabla(getTblRolPago());
			cargarTablaRolPago(true);
			
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al filtar informacion para busqueda !!", SpiritAlert.ERROR);
		}
	}
	
	private void seleccionarFilaTabla() {
		int fila = getTblRolPago().getSelectedRow();
		if ( fila >= 0 ){
			int filaSeleccionada = getTblRolPago().convertRowIndexToModel(fila);
			try {
				getBtnGenerarReporte().setEnabled(true);
				getBtnCerrarRol().setEnabled(false);
				
				rolPagoIf = rolPagoCollection.get(filaSeleccionada);
				
				tipoRolIf = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
				
				tipoContratoIf = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato,rolPagoIf.getTipocontratoId());
				
				int mesInteger = Integer.parseInt( rolPagoIf.getMes() );
				String mesString = Utilitarios.getNombreMes(mesInteger);
				String anio = rolPagoIf.getAnio();
				String estado = rolPagoIf.getEstado();
				if ( estado.equals(EstadoRolPago.GENERADO.getLetra()) )
					estado = EstadoRolPago.GENERADO.toString();
				else if ( estado.equals(EstadoRolPago.CERRADO.getLetra()) ) 
					estado = EstadoRolPago.CERRADO.toString();
				
				getCmbTipoRol().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoRol(), tipoRolIf.getId()));
				getCmbTipoRol().repaint();
				
				getCmbTipoContrato().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoContrato(), tipoContratoIf.getId()));
				getCmbTipoContrato().repaint();
				
				getCmbMes().setSelectedItem(mesString);
				getCmbAnio().setSelectedItem(anio);
				getTxtEstado().setText(estado);
				
				if ( rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra()) )
					getBtnCerrarRol().setEnabled(true);
				if ( rolPagoIf.getEstado().equals(EstadoRolPago.CERRADO.getLetra()) )
					getBtnCerrarRol().setEnabled(false);
				
				verificarTiporolMesAnioReporte();
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e1) {
				e1.printStackTrace();
				SpiritAlert.createAlert("Error general en la obtencion de información de Rol de Pago !!", SpiritAlert.ERROR);
			}
		}
	}
	
	private void accionCerrarReporte() {
		try{
			if ( confirmarCerrarRol() && verificarCerrarRolPago() ){
				cerrarRolpago();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al Generar Rol de Pago.", SpiritAlert.ERROR);
		}
	}
	
	private void accionGenerarReporte() {
		try{
			if ( verificarGenerarReporteRolPago() ){
				generarRolpago();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al Generar Rol de Pago.", SpiritAlert.ERROR);
		}
	}
	
	private boolean confirmarCerrarRol(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Desea Cerrar el Rol de Pago?", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		return opcion == JOptionPane.YES_OPTION ? true : false;
	}
	
	private void iniciarComponentes(){
		
		getCbReporteCostes().setSelected(false);
		getCbReporteCostes().setEnabled(false);
		
		//COMBOS DE FECHA
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(true);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(true);
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);
		
		
		//COMBO TIPO ROL
		ModelUtil.cargarCmbTipoRol(getCmbTipoRol(),null,null);
		getCmbTipoRol().setSelectedItem(null);
		
		//COMBO TIPO DE CONTRATO
		getCmbTipoContrato().setSelectedItem(null);
		ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());

		//COMBO ANIO
		ModelUtil.establecerCmbAnio(getCmbAnio());
		
		//TABLA
		limpiarTabla(getTblRolPago());
		
	}
	
	/*private void cargarCmbTipoContrato(){
		try {
			Collection<TipoContratoIf> tiposContratoCollection =  SessionServiceLocator.getTipoContratoSessionService().findTiposContratosUsados(Parametros.getIdEmpresa());
			Vector<TipoContratoIf> tiposContrato = new Vector<TipoContratoIf>(tiposContratoCollection);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposContrato);
			getCmbTipoContrato().setModel(comboModel);
			refreshCombo(getCmbTipoContrato(), (List)tiposContratoCollection);
			for (int i=0 ; i<comboModel.getSize() ; i++){
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getItemAt(i);
				if ( "RD".equals(tc.getCodigo()) ){
					tipoContratoIf = tc;
					getCmbTipoContrato().setSelectedIndex(i);
					break;
				}
			}	
			getCmbTipoContrato().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}*/
	
	private void verificarTiporolMesAnioReporte(){
		try {
			mapaParametros = null;
			rolGenerado = false;
			rolPagoIf = null;
			contratosIdCollection = null;
			tipoRol = null;
			
			tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null )
				return;
			
			tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
			if ( tipoRolIf == null )
				return;
			else{
				tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
			}
			
			mesSeleccionado = (String)getCmbMes().getSelectedItem();
			String mes = formatoDosEnteros.format( Utilitarios.getMesInt(mesSeleccionado)+ 1 );
			if ( mesSeleccionado == null )
				return;

			anioSeleccionado = (String) getCmbAnio().getSelectedItem();
			if ( anioSeleccionado == null )
				return;
		
			mapaParametros =  new HashMap<String, Object>();
			mapaParametros.put("tipocontratoId", tipoContratoIf.getId());
			mapaParametros.put("tiporolId", tipoRolIf.getId());
			mapaParametros.put("mes", mes);
			mapaParametros.put("anio", anioSeleccionado);
			
			ArrayList<RolPagoIf> rolPagoCollection = (ArrayList<RolPagoIf>) 
				SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(mapaParametros);
			if ( rolPagoCollection.size() == 1 ){
				rolPagoIf = (RolPagoIf) rolPagoCollection.iterator().next();
				Collection<Long> contratosId = null;
				
				contratosId = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,null);
				
				/*for(Long contratoId : contratosId){					
					ContratoIf contratoRevision = SessionServiceLocator.getContratoSessionService().getContrato(contratoId);
					if(contratoRevision.getCodigo().equals("AAUZ")){
						System.out.println("a");
					}else if(contratoRevision.getCodigo().equals("RJXJ")){
						System.out.println("a");
					}
				}*/
				
				if ( contratosId!=null && contratosId.size() > 0 ){
					contratosIdCollection = contratosId;
					rolGenerado = true;
					
					if ( tipoRol == TipoRol.UTILIDADES ){
						Calendar calDiaFinMesRolPago = new GregorianCalendar();
						calDiaFinMesRolPago.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()));
						calDiaFinMesRolPago.set(Calendar.DAY_OF_MONTH, Integer.valueOf(rolPagoIf.getMes()));
						calDiaFinMesRolPago.set(Calendar.DAY_OF_MONTH, calDiaFinMesRolPago.getActualMaximum(Calendar.DAY_OF_MONTH));
						
						mapaResultado = SessionServiceLocator.getContratoUtilidadSessionService().getInformacionUtilidades(
							Parametros.getIdEmpresa(),new Date(calDiaFinMesRolPago.getTime().getTime()));
					}
					showUpdateMode();
				}else{
					setSaveMode();
				}
			}
			else{
				setSaveMode();
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarTablaRolPago(boolean filtrar) {
		try {
			if ( filtrar && calInicio != null ){
				
				if ( calFin != null && calFin.getTime().compareTo(calInicio.getTime()) < 0 ){
					SpiritAlert.createAlert("Fecha Fin tiene que ser mayor que Fecha Inicio !!",SpiritAlert.ERROR);
					return;
				}
				
				rolPagoCollection = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
					.getRolesPagoByFechaInicioByFechaFin(
						tipoContratoIf != null ? tipoContratoIf.getId():null , 
						tipoRolIf != null ? tipoRolIf.getId() : null , 
						calInicio, calFin );
			} else {
				rolPagoCollection = (ArrayList<RolPagoIf>) SessionServiceLocator
					.getRolPagoSessionService().getRolPagoListOrdenado();
			}
		
			DefaultTableModel modelo = (DefaultTableModel) getTblRolPago().getModel();
			for ( RolPagoIf rolPagoIf : rolPagoCollection ){
				Vector<Object> fila = crearFilaTabla(rolPagoIf);
				modelo.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar tabla de Roles de Pago", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Vector<Object> crearFilaTabla(RolPagoIf rolPagoIf)
			throws GenericBusinessException {
		TipoRolIf tipoRol = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		TipoContratoIf tipoContrato = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato,rolPagoIf.getTipocontratoId());
		String nombreTipoContrato = tipoContrato.getNombre();
		int mesInteger = Integer.parseInt( rolPagoIf.getMes() );
		String mesString = Utilitarios.getNombreMes(mesInteger);
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		String estado = rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())
						?EstadoRolPago.GENERADO.toString():EstadoRolPago.CERRADO.toString();
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
		fila.add(nombreTipoContrato);
		fila.add(mesString);
		fila.add(anio);
		fila.add(estado);
		return fila;
	}
	
	@Override
	public boolean validateFields() {
		
		if ( tipoContratoIf == null ){
			SpiritAlert.createAlert("Seleccionar Tipo de Contrato !!", SpiritAlert.INFORMATION);
			getCmbTipoContrato().grabFocus();
			return false;
		}
		
		if ( tipoRolIf == null ){
			SpiritAlert.createAlert("Seleccionar Tipo de Rol !!", SpiritAlert.INFORMATION);
			getCmbTipoRol().grabFocus();
			return false;
		}
		
		if ( mesSeleccionado == null ){
			SpiritAlert.createAlert("Seleccionar Mes !!", SpiritAlert.INFORMATION);
			getCmbMes().grabFocus();
			return false;
		}

		if ( anioSeleccionado == null ){
			SpiritAlert.createAlert("Seleccionar A\u00f1o !!", SpiritAlert.INFORMATION);
			getCmbAnio().grabFocus();
			return false;
		}
		return true;
	}
	
	private boolean verificarGenerarReporteRolPago() throws GenericBusinessException{
		if (validateFields()){
			verificarTiporolMesAnioReporte();
			if ( rolGenerado ){
				if ( !rolPagoIf.getEstado().equals(EstadoRolPago.CERRADO.getLetra()) ){
					int opcion = confirmarReemplazarRol();
					if (opcion == JOptionPane.NO_OPTION)
						return false;
				} else {
					SpiritAlert.createAlert("No se puede reemplazar un rol cerrado", SpiritAlert.WARNING);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean verificarCerrarRolPago() throws GenericBusinessException{
		if (validateFields()){
			verificarTiporolMesAnioReporte();
			if ( rolGenerado ){
				if ( !rolPagoIf.getEstado().equals(EstadoRolPago.CERRADO.getLetra()) ){
					return true;
				} else {
					SpiritAlert.createAlert("Rol de Pago ya está cerrado", SpiritAlert.WARNING);
				}
			} else{
				SpiritAlert.createAlert("Rol no ha sido generado", SpiritAlert.WARNING);
			}
		}
		return false;
	}
	
	private int confirmarReemplazarRol(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Desea reemplazar el Rol de Pago existente?", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		return opcion;
	}
	
	private void cerrarRolpago() throws GenericBusinessException{
		if(rolPagoIf != null){
			rolPagoIf.setEstado(EstadoRolPago.CERRADO.getLetra());
			SessionServiceLocator.getRolPagoSessionService().saveRolPago(rolPagoIf);
			SpiritAlert.createAlert("Rol de Pago Cerrado con éxito.", SpiritAlert.INFORMATION);
			showSaveMode();
		}
		
		//RolPagoData rolPago = crearRolPagoCabecera();
		//getRolPagoSessionService().cerrarRolPago(rolPago);
		//SpiritAlert.createAlert("Rol de Pago Cerrado con éxito !!", SpiritAlert.INFORMATION);
		//showSaveMode();
		
	}
	
	public void authorize() {
		try {
			if(rolPagoIf != null){
				Object[] options = {"Si","No"}; 
				int opcion = JOptionPane.showOptionDialog(
						null,"¿Autoriza que se pueda modificar el Rol?", "Confirmación"
						,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
						,null,options,"No");
				
				if(opcion == JOptionPane.YES_OPTION){
					rolPagoIf.setEstado(EstadoRolPago.GENERADO.getLetra());
					SessionServiceLocator.getRolPagoSessionService().saveRolPago(rolPagoIf);
					SpiritAlert.createAlert("Rol de Pago ya puede ser Generado nuevamente.", SpiritAlert.INFORMATION);
					showSaveMode();
				}				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void generarRolpago() throws GenericBusinessException{
		Runnable runnable = new Runnable(){
			public void run() {
				try{
					setCursorEspera();
					getBtnGenerarReporte().setEnabled(false);
					if (contratosIdCollection != null )
						RolPagoUtil.verificarRubroFondoReserva(contratosIdCollection);
					RolPagoData rolPago = crearRolPagoCabecera();
					mapaResultado = SessionServiceLocator.getRolPagoSessionService().procesarRolPago(
							rolPago,tipoContratoIf,null,Parametros.getIdEmpresa());
					SpiritAlert.createAlert("Rol de Pago Generado con éxito !!", SpiritAlert.INFORMATION);
					
					cargarObservaciones(mapaResultado);
					
					showSaveMode();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch(Exception e){
					e.printStackTrace();
					SpiritAlert.createAlert("Error general al Generar Rol de Pago.", SpiritAlert.ERROR);
				} finally{
					getBtnGenerarReporte().setEnabled(true);
					setCursorNormal();
				}
			}

			private void cargarObservaciones(Map<String, Object> mapaResultado) {
				Object oObservaciones = mapaResultado.get("observaciones");
				if ( oObservaciones != null && oObservaciones instanceof Collection ){
					Collection<DatoObservacion> observaciones = (Collection<DatoObservacion>) oObservaciones;
					if ( observaciones.size() > 0 )
						new ObservacionesModel(Parametros.getMainFrame(),"Observaciones",observaciones);
				}
			}
		};
		Thread t = new Thread(runnable);
		t.start();
		t = null;
	}
	
	

	private RolPagoData crearRolPagoCabecera() throws GenericBusinessException {
		RolPagoData rolPago =  new RolPagoData();
		rolPago.setId(rolPagoIf!=null?rolPagoIf.getId():null);
		rolPago.setTiporolId((Long)mapaParametros.get("tiporolId"));
		rolPago.setTipocontratoId((Long)mapaParametros.get("tipocontratoId"));
		rolPago.setMes((String)mapaParametros.get("mes"));
		rolPago.setAnio((String)mapaParametros.get("anio"));
		rolPago.setEstado(EstadoRolPago.GENERADO.getLetra());
		
		if(rolPagoIf != null && rolPagoIf.getAsientoGenerado() != null)
			rolPago.setAsientoGenerado(rolPagoIf.getAsientoGenerado());
		else
			rolPago.setAsientoGenerado("N");
		
		java.sql.Date firstDate = new java.sql.Date(Integer.parseInt(rolPago.getAnio()) - 1900, Integer.parseInt(rolPago.getMes()) -1, 1);
		int lastDay = Utilitarios.getLastDayOfMonth(Integer.parseInt(rolPago.getMes()) - 1, Integer.parseInt(rolPago.getAnio()) - 1900);
		java.sql.Date lastDate = new java.sql.Date(Integer.parseInt(rolPago.getAnio()) - 1900, Integer.parseInt(rolPago.getMes()) -1, lastDay);
		java.sql.Date today = Utilitarios.fromUtilDateToSqlDate(new java.util.Date());
		if (today.compareTo(firstDate) >=0 && today.compareTo(lastDate) <= 0)
			rolPago.setFecha(today);
		else
			rolPago.setFecha(lastDate);
		return rolPago;
	}
	
	public void clean() {
		rolPagoIf = null;
		rolPagoCollection = null;
		contratosIdCollection = null;
		tipoContratoIf = null;
		
		calInicio = null;
		calFin = null;
		
		mapaTiposContrato = null;
		mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
		
		mapaTiposRol = null;
		mapaTiposRol = new HashMap<Long,TipoRolIf>();
		
		mapaResultado = null;
		
		getCmbTipoRol().setSelectedItem(null);
		getCmbTipoRol().repaint();
		getCmbTipoContrato().setSelectedItem(null);
		getCmbTipoContrato().repaint(); 
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaInicio().repaint();
		getCmbFechaFin().setDate(null);
		getCmbFechaFin().repaint();
		
		
		getBtnGenerarReporte().setEnabled(true);
		getBtnCerrarRol().setEnabled(false);
		limpiarTabla(getTblRolPago());
	}
	
	public void delete() {
		/*try {
			if ( validateFields() ){
				if ( rolPagoIf != null ){
					if ( !rolPagoIf.getEstado().equals(RolPago.getLetraEstadoRolPago(EstadoRolPago.CERRADO)) ){
						getRolPagoSessionService().eliminarRolPago(rolPagoIf);
						SpiritAlert.createAlert("Rol eliminado con éxito !!", SpiritAlert.INFORMATION);
						showSaveMode();
					} else
						SpiritAlert.createAlert("No se puede eliminar un Rol de Pago Cerrado", SpiritAlert.WARNING);
				} else
					SpiritAlert.createAlert("Seleccionar un Rol de Pago para la eliminación !!", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		}*/
	}

	public void save() {
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTablaRolPago(false);
	}

	public void update() {
	}
	
	@Override
	public void refresh() {
		showSaveMode();
	}
	
	public void report() {
		String si = "Sí";
		String no = "No";
		Object[] options = { si, no };
		
		setCursorEspera();
		try {
			//setCursorEspera();
			if ( validateFields() ){
				
				if ( !rolGenerado ){
					//SI NO EXISTE ROL, SE PREGUNTA PARA GENERARLO
					int opcion = confirmarGenerarReporte();
					if ( opcion == JOptionPane.YES_OPTION ){
						generarRolpago();
						return;
					} else{
						setCursorNormal();
						return;
					}
				}
				
				boolean verValoresRolEnR13Rv = false;
				boolean verValoresRol12EnR13Rv = false;
				boolean verValoresPagoR13Rv = false;
				
				String decimoCuarto = " PROVISION";
				
				boolean generarExcelPagoRol = false;
				String quincena = "";
				
				String fileName ="";
				if ( tipoRol == TipoRol.MENSUAL ){
					fileName = "jaspers/nomina/RPRolPagoMensual.jasper";
					
					String rolMensual = "Rol Mensual";
					String excelPagoRol = "Crear Excel para Pago de Rol";
					
					Object[] opciones = {rolMensual, excelPagoRol};

					int opcion = JOptionPane.showOptionDialog(null,
							"¿Qué informe desea ver?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, si);

					int opcionPagoRol = 1;
					
					if (opcion == opcionPagoRol) {
						generarExcelPagoRol = true;
						quincena = "2da 15na";
					} 
				}
				else if ( tipoRol == TipoRol.QUINCENAL ){
					fileName = "jaspers/nomina/RPRolPagoQuincenal.jasper";
					
					String rolMensual = "Rol Quincenal";
					String excelPagoRol = "Crear Excel para Pago de Rol";
					
					Object[] opciones = {rolMensual, excelPagoRol};

					int opcion = JOptionPane.showOptionDialog(null,
							"¿Qué informe desea ver?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, si);

					int opcionPagoRol = 1;
					
					if (opcion == opcionPagoRol) {
						generarExcelPagoRol = true;
						quincena = "1ra 15na";
					} 
				}
				else if ( tipoRol == TipoRol.DECIMO_TERCERO ){
					fileName = "jaspers/nomina/RPRolPagoDecimoTercero2.jasper";
					
					String provisionR13 = "Provisión Decimo Tercero";
					String valoresRM =  "Rol Mensual";
					String valoresRM12 = "Rol Mensual / 12(meses)";
					String pagoR13 =  "Pago de Decimo Tercero";
					
					Object[] opciones = {provisionR13, valoresRM, valoresRM12, pagoR13};
										
					int opcion = JOptionPane.showOptionDialog(null,
							"¿Qué valores desea ver en el informe?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, si);
					
					int opcionProvisionR13 = 0;
					int opcionValoresRM = 1;
					int opcionvaloresRM12 = 2;
					int opcionPagoR13 = 3;
					
					if(opcion == opcionProvisionR13){
						verValoresRolEnR13Rv = false;
						verValoresRol12EnR13Rv = false;
					}else if(opcion == opcionValoresRM) {
						verValoresRolEnR13Rv = true;
						verValoresRol12EnR13Rv = false;
					}else if(opcion == opcionvaloresRM12){
						verValoresRolEnR13Rv = true;
						verValoresRol12EnR13Rv = true;
					}else if(opcion == opcionPagoR13){
						verValoresRolEnR13Rv = true;
						verValoresPagoR13Rv = true;
						fileName = "jaspers/nomina/RPPagoDecimoterceraRemuneracion.jasper";
					}
				}				
				else if ( tipoRol == TipoRol.DECIMO_CUARTO ) {
					fileName = "jaspers/nomina/RPRolPagoDecimoCuarto.jasper";
					
					String provisionDecimoCuarto = "Provisión Decimo Cuarto";
					String decimoCuartoCosta = "Decimo Cuarto Costa";
					String decimoCuartoSierra = "Decimo Cuarto Sierra";
					
					Object[] opciones = { provisionDecimoCuarto, decimoCuartoCosta, decimoCuartoSierra };

					int opcion = JOptionPane.showOptionDialog(null,
							"¿Qué informe desea ver?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, si);

					int opcionR14Costa = 1;
					int opcionR14Sierra = 2;
					
					if (opcion == opcionR14Costa) {
						decimoCuarto = " COSTA";
						fileName = "jaspers/nomina/RPRolPagoDecimoCuartoCosta.jasper";
					}else if (opcion == opcionR14Sierra) {
						decimoCuarto = " SIERRA";
						fileName = "jaspers/nomina/RPRolPagoDecimoCuartoSierra.jasper";
					}					
					
				}else if (tipoRol == TipoRol.FONDO_RESERVA)
					fileName = "jaspers/nomina/RPRolPagoFondoReserva.jasper";
				else if (tipoRol == TipoRol.APORTE_PATRONAL)
					fileName = "jaspers/nomina/RPRolPagoAportePatronal.jasper";
				else if (tipoRol == TipoRol.VACACIONES){
					//INI_CAMBIO_20140610 Se añaden las opciones para generar el reporte de Vacaciones
					fileName = "jaspers/nomina/RPRolPagoVacaciones.jasper";
					
					String provisionRV = "Provisión Vacaciones";
					String valoresRMV =  "Rol Mensual";
					String valoresRMV24 = "Rol Mensual / 24";
					String pagoRV =  "Pago de Vacaciones";
					
					Object[] opciones = {provisionRV, valoresRMV, valoresRMV24, pagoRV};
										
					int opcion = JOptionPane.showOptionDialog(null,
							"¿Qué valores desea ver en el informe?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, si);
					
					int opcionProvisionRV = 0;
					int opcionValoresRMV = 1;
					int opcionvaloresRMV24 = 2;
					int opcionPagoRV = 3;
					
					if(opcion == opcionProvisionRV){
						verValoresRolEnR13Rv = false;
						verValoresRol12EnR13Rv = false;
					}else if(opcion == opcionValoresRMV) {
						verValoresRolEnR13Rv = true;
						verValoresRol12EnR13Rv = false;
					}else if(opcion == opcionvaloresRMV24){
						verValoresRolEnR13Rv = true;
						verValoresRol12EnR13Rv = true;
					}else if(opcion == opcionPagoRV){
						verValoresRolEnR13Rv = true;
						verValoresPagoR13Rv = true;
						fileName = "jaspers/nomina/RPPagoVacacionesRemuneracion.jasper";
					}
					//FIN_CAMBIO_20140610
					
				}else if (tipoRol == TipoRol.UTILIDADES)
					fileName = "jaspers/nomina/RPUtilidadesContratos.jasper";			

				if (!"".equals(fileName)){
					HashMap<String,Object> parametrosMap = new HashMap<String,Object>();
					
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE CUENTA").iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("usuario", Parametros.getUsuario());
					
					parametrosMap.put("mes", mesSeleccionado);
					parametrosMap.put("anio", anioSeleccionado);
					
					if ( tipoContratoIf.getNombre().contains("PROFESIONAL") ){
						parametrosMap.put("tituloRol","L I Q U I D A C I Ó N");
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre().replace("ROL", "").replace("DE", ""));
						parametrosMap.put("nombreTipoContrato", tipoContratoIf.getNombre());
					}
					else if ( tipoContratoIf.getNombre().contains("DEPENDENCIA") ){
						parametrosMap.put("tituloRol","R O L   D E   P A G O  P O R   C O N T R A T O");
						
						if(tipoRol == TipoRol.DECIMO_CUARTO){
							parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre() + decimoCuarto);
						}else{
							parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre());
						}
					}
					
					if ( tipoRol == TipoRol.UTILIDADES ){
						ContratoUtilidadIf contratoUtilidad = (ContratoUtilidadIf) mapaResultado.get("contratoUtilidadIf");
						parametrosMap.put("utilidad", contratoUtilidad.getUtilidad());
						parametrosMap.put("porcentajeContratos", contratoUtilidad.getPorcentajeContrato() );
						parametrosMap.put("porcentajeCargas", contratoUtilidad.getPorcentajeCarga() );
						
						parametrosMap.put("fechaInicioPeriodo", mapaResultado.get("fechaInicioPeriodo") );
						parametrosMap.put("fechaFinPeriodo", mapaResultado.get("fechaFinPeriodo") );
	
					}
					
					//COLECCION DE DATOS
					Collection rolPagoContratoCollection = SessionServiceLocator
						.getRolPagoSessionService().crearColeccionContratos(contratosIdCollection,rolPagoIf,null,false,
								verValoresRolEnR13Rv, verValoresPagoR13Rv,decimoCuarto, true);
									 							
					//GENERAR EXCEL ROL DE PAGO
					if(generarExcelPagoRol){
						
						Vector<FormatoPagoRolData> pagoRolColeccion = new Vector<FormatoPagoRolData>();
						
						Iterator rolPagoContratoCollectionIt = rolPagoContratoCollection.iterator();
						while(rolPagoContratoCollectionIt.hasNext()){
							Map<String,Object> datosRolPagoContrato = (Map<String,Object>)rolPagoContratoCollectionIt.next();
							
							if(datosRolPagoContrato.get("contratoId") != null){
								
								Long idContrato = (Long)datosRolPagoContrato.get("contratoId");
								ContratoIf contratoIf = SessionServiceLocator.getContratoSessionService().getContrato(idContrato);
								EmpleadoIf empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId());
								TipoIdentificacionIf tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(empleadoIf.getTipoidentificacionId());
								
								if(empleadoIf.getTipoCuenta() != null && !empleadoIf.getTipoCuenta().equals("")){
									FormatoPagoRolData formatoPagoRolData = new FormatoPagoRolData();
									
									formatoPagoRolData.setTipo(tipoIdentificacion.getCodigoSri());
									formatoPagoRolData.setIdentificacion(empleadoIf.getIdentificacion());
									formatoPagoRolData.setNombre(empleadoIf.getApellidos() + " " + empleadoIf.getNombres());
									formatoPagoRolData.setDetalle(quincena);
									
									if(empleadoIf.getTipoCuenta().equals("E")){
										formatoPagoRolData.setTipoCuenta("EFE");
										formatoPagoRolData.setCodigo("");
										formatoPagoRolData.setNumeroCuenta("");
									}else{
										formatoPagoRolData.setTipoCuenta("CUE");
										formatoPagoRolData.setCodigo("34");
										
										if(empleadoIf.getTipoCuenta().equals("C")){
											formatoPagoRolData.setCodigoCuenta("03");
										}else if(empleadoIf.getTipoCuenta().equals("A")){
											formatoPagoRolData.setCodigoCuenta("04");
										}								
										
										formatoPagoRolData.setNumeroCuenta(empleadoIf.getNumeroCuenta());										
									}
									
									String valor = formatoDecimal.format((Double)datosRolPagoContrato.get("netoPagar"));
									formatoPagoRolData.setValor(String.valueOf((Double)datosRolPagoContrato.get("netoPagar")));
									
									pagoRolColeccion.add(formatoPagoRolData);
								}							
							}
						}
						
						generarArchivoPagoRol(pagoRolColeccion);
					}					
					
					//REPORTE DE COSTES
					Collection reporteCostes = new ArrayList();
					if(getCbReporteCostes().isSelected() && (tipoRol == TipoRol.MENSUAL)){
						fileName = "jaspers/nomina/RPCostes.jasper";
						parametrosMap.put("tituloRol", "REPORTE DE COSTES");
						
						int opcion = JOptionPane.showOptionDialog(null,
								"¿Desea generar el reporte sin cabeceras?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, si);
						
						
						if (opcion == JOptionPane.YES_OPTION) {
							fileName = "jaspers/nomina/RPCostesSinCabecera.jasper";
						}						
						
						Iterator rolPagoContratoCollectionIt = rolPagoContratoCollection.iterator();
						while(rolPagoContratoCollectionIt.hasNext()){
							Map<String,Object> datosRolPagoContrato = (Map<String,Object>)rolPagoContratoCollectionIt.next();
							
							if(datosRolPagoContrato.get("contratoId") != null){
								Map<String,Object> datosCostes = new HashMap<String,Object>();
								
								Long idContrato = (Long)datosRolPagoContrato.get("contratoId");
								ContratoIf contratoIf = SessionServiceLocator.getContratoSessionService().getContrato(idContrato);
								EmpleadoIf empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId());
								OficinaIf oficinaEmpleado = SessionServiceLocator.getOficinaSessionService().getOficina(empleadoIf.getOficinaId());
								CiudadIf ciudadEmpleado = SessionServiceLocator.getCiudadSessionService().getCiudad(oficinaEmpleado.getCiudadId());
								DepartamentoIf departamentoEmpleado = SessionServiceLocator.getDepartamentoSessionService().getDepartamento(empleadoIf.getDepartamentoId());
								
								datosCostes.put("nombreEmpleado", datosRolPagoContrato.get("nombreEmpleado"));
								datosCostes.put("oficina", ciudadEmpleado.getNombre());
								datosCostes.put("departamento", departamentoEmpleado.getNombre());
								datosCostes.put("sueldo", datosRolPagoContrato.get("sueldo"));
								datosCostes.put("fondoReservaBeneficio", datosRolPagoContrato.get("fondoReservaBeneficio"));
								datosCostes.put("otrosIngresos", datosRolPagoContrato.get("otrosIngresos"));
								datosCostes.put("totalIngresos", (Double)datosRolPagoContrato.get("sueldo") + (Double)datosRolPagoContrato.get("otrosIngresos"));
								
								OficinaIf oficinaIf = null;
								DepartamentoIf departamentoIf = null;
								if(!getCmbOficina().getSelectedItem().equals(TODOS)){
									oficinaIf = (OficinaIf)getCmbOficina().getSelectedItem();
								}
								if(!getCmbDepartamento().getSelectedItem().equals(TODOS)){
									departamentoIf = (DepartamentoIf)getCmbDepartamento().getSelectedItem();
								}
								
								if(oficinaIf == null && departamentoIf == null){
									reporteCostes.add(datosCostes);
								}else if (oficinaIf != null && oficinaIf.getId().compareTo(oficinaEmpleado.getId()) == 0
										&& departamentoIf != null && departamentoIf.getId().compareTo(departamentoEmpleado.getId()) == 0){
									parametrosMap.put("nombreTipoContrato", "OFICINA: " + ciudadEmpleado.getNombre() + " / DEPARTAMENTO: " + departamentoEmpleado.getNombre());
									reporteCostes.add(datosCostes);
								}else if (oficinaIf != null && oficinaIf.getId().compareTo(oficinaEmpleado.getId()) == 0
										&& departamentoIf == null){
									parametrosMap.put("nombreTipoContrato", "OFICINA: " + ciudadEmpleado.getNombre());
									reporteCostes.add(datosCostes);
								}else if (departamentoIf != null && departamentoIf.getId().compareTo(departamentoEmpleado.getId()) == 0
										&& oficinaIf == null){
									parametrosMap.put("nombreTipoContrato", "DEPARTAMENTO: " + departamentoEmpleado.getNombre());
									reporteCostes.add(datosCostes);
								}
							}
						}
						rolPagoContratoCollection = reporteCostes;
					}					
					
					//Opciones de los reportes de Decimo tercero y vacaciones
					//INI_CAMBIO_20140616 Se añade variable nombreTipoRol24 para generar las opciones del reporte
					//de Vacaciones
					if(verValoresRolEnR13Rv && !verValoresRol12EnR13Rv){
						parametrosMap.put("dividirPara12", new Boolean(false));
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre()+" (RM)");
						parametrosMap.put("nombreTipoRol24", tipoRolIf.getNombre()+" (RM) ");
					}else if(verValoresRolEnR13Rv && verValoresRol12EnR13Rv){
						parametrosMap.put("dividirPara12", new Boolean(true));
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre()+" (RM/12)");
						parametrosMap.put("nombreTipoRol24", tipoRolIf.getNombre()+" (RM/24) ");
					}else{
						parametrosMap.put("dividirPara12", new Boolean(false));
						parametrosMap.put("nombreTipoRol24", tipoRolIf.getNombre());
					//FIN_CAMBIO_20140616
					}
					
					JRDataSource dataSourceDetail = new JRMapCollectionDataSource(rolPagoContratoCollection);
					
					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);					
					rolPagoContratoCollection = null;
					parametrosMap = null;
					System.gc();
				} else{
					SpiritAlert.createAlert("Reporte para Tipo de Rol "+tipoRolIf.getNombre()+" no implementado !!", SpiritAlert.INFORMATION);
				}
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar Reporte !!", SpiritAlert.WARNING);
		} 
		setCursorNormal();
	}
	
	public void generarArchivoPagoRol(Vector<FormatoPagoRolData> pagoRolColeccion){
		try {
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("ROL_PAGO");
			
			//creo un objecto de estito de celda, y seteo font con negrita
			HSSFCellStyle cellStyle = libro.createCellStyle();
			HSSFFont font = libro.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
						
			//seteo el borde
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			//primera fila, nombre de las columnas
			HSSFRow filaNumeros = hoja.createRow(0);
			for(int i=0; i<9; i++){
				HSSFCell celda = filaNumeros.createCell(i);
				HSSFRichTextString texto = new HSSFRichTextString(pagoRolColeccion.get(0).getNombreAtributo(i+1));
				celda.setCellValue(texto);
				
				celda.setCellStyle(cellStyle);
			}
			
			//reseteo el estilo para que no haga negrillas pero si quiere que siga los bordes
			cellStyle = libro.createCellStyle();
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			//lleno desde la segunda fila la información de los 28 campos			
			for(int j = 0; j < pagoRolColeccion.size(); j++){
				FormatoPagoRolData formatoPagoRolData = pagoRolColeccion.get(j);
				HSSFRow filaCampos = hoja.createRow(j+1);
				
				for(int i=0; i<9; i++){
					HSSFCell celda = filaCampos.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(formatoPagoRolData.getCampo(i+1));
					celda.setCellValue(texto);
					
					celda.setCellStyle(cellStyle);
				}				
			}
			
			//indico que las columnas tengan el ancho de su texto
			for(int columnIndex = 0; columnIndex < 9; columnIndex++) {
				hoja.autoSizeColumn(columnIndex);
			}	
			
			FileOutputStream archivo = new FileOutputStream("C:\\Temp\\ROL_PAGO.xls");
			libro.write(archivo);
			archivo.close();
			System.out.println("Archivo creado con éxito!");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: File");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: IO");
		}
	}

	private int confirmarGenerarReporte(){
		String si = "Si"; 
        String no = "No"; 
        Object[] options ={si,no}; 
		int opcion = JOptionPane.showOptionDialog(null, "¿Rol de Pago no existe. ¿ Desea generar el Rol de Pago?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		return opcion;
	}
}