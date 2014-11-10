package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.gui.panel.JPConsultaGeneralContratos;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.handler.DatoGeneralContrato;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class ConsultaGeneralContratosModel extends JPConsultaGeneralContratos{

	private static final long serialVersionUID = 6422474694618969256L;
	private final int COLUMNA_DETALLE_SELECCION = 0;
	private final int COLUMNA_EMPLEADO = 1;
	private final int COLUMNA_TIPO_CONTRATO = 2;
	private final int COLUMNA_FECHA_INGRESO = 3;
	
	Collection<DatoGeneralContrato> datosContratos = null;
	
	private DefaultTableModel modelo = (DefaultTableModel) getTblConsulta().getModel();
	
	public ConsultaGeneralContratosModel() {
		initKeyListeners();
		showSaveMode();
		cargarComboTipoContrato();
		iniciarComponentes();
		initListener();
	}
	
	private void initKeyListeners(){		
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
	}
	
	private void iniciarComponentes(){
		
		getCmbTipoContrato().setSelectedItem(null);
		
		//COMBOS DE FECHA
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(true);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(true);
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);
		
		//Tabla
		TableColumnModel modeloCol = getTblConsulta().getColumnModel();
		TableColumn columna = modeloCol.getColumn(COLUMNA_DETALLE_SELECCION);
		columna.setPreferredWidth(50);
		columna.setMaxWidth(50);
		columna = modeloCol.getColumn(COLUMNA_EMPLEADO);
		columna.setPreferredWidth(180);
		columna = modeloCol.getColumn(COLUMNA_TIPO_CONTRATO);
		columna.setPreferredWidth(120);
		columna = modeloCol.getColumn(COLUMNA_FECHA_INGRESO);
		columna.setPreferredWidth(120);
		
	}
	
	private void initListener(){
		getBtnConsultar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				consultar();
			}
		});
		
		getBtnSeleccionarTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionarTodo();
			}
		});
	}
	
	private void seleccionarTodo(){
		DefaultTableModel modelo  = (DefaultTableModel) getTblConsulta().getModel();
		for ( int i = 0 ; i < modelo.getRowCount() ; i++  ){
			modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
		}
	}
	
	private void consultar(){
		datosContratos = null;
		limpiarTabla(getTblConsulta());
		
		Date fechaInicio = getCmbFechaInicio().getDate();
		java.sql.Date fechaInicioSql = fechaInicio!=null ? new java.sql.Date(fechaInicio.getTime()) : null;
		
		Date fechaFin = getCmbFechaFin().getDate();
		java.sql.Date fechaFinSql = fechaFin!=null ? new java.sql.Date(fechaFin.getTime()) : null;
		
		TipoContratoIf tipoContrato = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
		
		Map<String,Object> mapa = new HashMap<String, Object>();
		if ( tipoContrato != null )
			mapa.put("tipocontratoId", tipoContrato.getId());
		
		datosContratos = SessionServiceLocator.getContratoSessionService()
			.getContratosByQueryByFechaInicioFechaFinContrato(mapa,fechaInicioSql,fechaFinSql);
		
		for ( DatoGeneralContrato dgc : datosContratos ){
			crearFila(dgc);
		}
		
	}
	
	private void crearFila(DatoGeneralContrato dgc){
		Object[] fila = new Object[4];
		fila[0] = false;
		fila[1] = dgc.getNombreEmpleado();
		fila[2] = dgc.getTipoContrato();
		dgc.setFechaIngresoTexto(Utilitarios.getFechaUppercase(dgc.getFechaIngreso()));
		fila[3] = dgc.getFechaIngresoTexto();
		modelo.addRow(fila);
	}
	
	public void refresh() {
		cargarComboTipoContrato();
		clean();
	}
	
	private void cargarComboTipoContrato(){
		ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());
	}
	
	public void clean() {
		datosContratos = null;
		limpiarTabla(getTblConsulta());
		getCmbTipoContrato().setSelectedItem(null);
		getCmbTipoContrato().repaint();
	}

	public void report() {
		String fileName = "jaspers/nomina/RPConsultaGeneralContratos.jasper";
		
		HashMap<String,Object> parametrosMap = new HashMap<String,Object>();
		
		MenuIf menu;
		try {
			menu = (MenuIf) SessionServiceLocator.getMenuSessionService()
				.findMenuByNombre(getName().toUpperCase()).iterator().next();
			parametrosMap.put("codigoReporte", menu.getCodigo());
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			parametrosMap.put("empresa", empresa.getNombre());
			parametrosMap.put("ruc", empresa.getRuc());
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
			parametrosMap.put("ciudad", ciudad.getNombre());
			parametrosMap.put("usuario", Parametros.getUsuario());
			
			Collection<DatoGeneralContrato> seleccionados = filtrarSeleccionados();
			
			JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(seleccionados);
			ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en reporte !!", SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error General en reporte !!", SpiritAlert.ERROR);
		}
		
	}

	private Collection<DatoGeneralContrato> filtrarSeleccionados() {
		Collection<DatoGeneralContrato> seleccionados = new ArrayList<DatoGeneralContrato>();
		ArrayList<DatoGeneralContrato> datos = (ArrayList<DatoGeneralContrato>) datosContratos;
		for ( int i = 0 ; i < modelo.getRowCount() ; i++ ){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, COLUMNA_DETALLE_SELECCION);
			if ( seleccionado ){
				seleccionados.add(datos.get(i));
			}
		}
		return seleccionados;
	}

	public void showSaveMode() {
		clean();
	}

}
