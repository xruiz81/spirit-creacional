package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.gui.panel.JPChequesEmitidos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.handler.OrigenCheque;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class ChequesEmitidosModel extends JPChequesEmitidos {

	private static final long serialVersionUID = 8184860267986734949L;
	
	private static final int COLUMNA_EMITIDO = 0;
	private static final int COLUMNA_COBRADO = 10;

	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private List<ChequeEmitidoIf> chequesColeccion = new ArrayList<ChequeEmitidoIf>();
	private List<ChequeEmitidoIf> chequesSeleccionadosColeccion = new ArrayList<ChequeEmitidoIf>();
	private DefaultTableModel tableModel;
	private Calendar[] fechasColeccion = new Calendar[]{};
	private Map<Long, CuentaBancariaIf> cuentasBancariasMap;
	
	public ChequesEmitidosModel(){
		anchoColumnasTabla();
		alineacionColumnasTabla();
		initKeyListeners();
		cargarComboEstado();
		cargarComboCuentasBancarias();
		initListeners();
		showUpdateMode();
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblChequesEmitidos());
		getTblChequesEmitidos().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblChequesEmitidos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblChequesEmitidos().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(60);
	}
	
	private void alineacionColumnasTabla() {
		TableCellRendererHorizontalCenterAlignment horizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblChequesEmitidos().getColumnModel().getColumn(1).setCellRenderer(horizontalCenterAlignment);
		getTblChequesEmitidos().getColumnModel().getColumn(2).setCellRenderer(horizontalCenterAlignment);
		getTblChequesEmitidos().getColumnModel().getColumn(3).setCellRenderer(horizontalCenterAlignment);
		getTblChequesEmitidos().getColumnModel().getColumn(5).setCellRenderer(horizontalCenterAlignment);
		getTblChequesEmitidos().getColumnModel().getColumn(6).setCellRenderer(horizontalCenterAlignment);
		getTblChequesEmitidos().getColumnModel().getColumn(9).setCellRenderer(horizontalCenterAlignment);
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		getTblChequesEmitidos().getColumnModel().getColumn(4).setCellRenderer(horizontalRightAlignment);
	}
	
	private void initKeyListeners() {
		getTxtBanco().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaCobro().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCobro().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaCobro().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	private void setearNombreBanco() {
		try {
			CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf)getCmbCuentaBancaria().getSelectedItem();
			BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
			getTxtBanco().setText(banco.getNombre());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	ActionListener oActionListenerBtnFechaCobro = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getTblChequesEmitidos().getSelectedRow() != -1) {				
				int row = getTblChequesEmitidos().getSelectedRow();
				Calendar fechaCobro = getCmbFechaCobro().getCalendar();
				int indiceReordenado = getTblChequesEmitidos().convertRowIndexToModel(row);
				Date fechaEmision = chequesColeccion.get(indiceReordenado).getFechaEmision();
				if(Utilitarios.compararFechas(fechaEmision, new Date(fechaCobro.getTime().getTime())) == 1){
					SpiritAlert.createAlert("La fecha de Cobro no puede ser antes que la fecha de emisión!", SpiritAlert.WARNING);	
				}else{
					fechasColeccion[indiceReordenado] = fechaCobro;
					if(fechaCobro != null){
						getTblChequesEmitidos().setValueAt(Utilitarios.getFechaCortaUppercase(fechaCobro.getTime()), row, 6);
					}else{
						getTblChequesEmitidos().setValueAt("", row, 6);
					}
				}
			}else{
				SpiritAlert.createAlert("Debe seleccionar primero una fila de la tabla!", SpiritAlert.WARNING);
			}
		}
	};
	
	private void initListeners() {
		getBtnFechaCobro().addActionListener(oActionListenerBtnFechaCobro);
		
		getCmbCuentaBancaria().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setearNombreBanco();				
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				cargarTabla();
			}
		});
		
		getBtnSeleccionarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblChequesEmitidos().getRowCount() > 0) {
					if ((Boolean)getTblChequesEmitidos().getValueAt(0,0) == false) {
						for(int i=0; i<getTblChequesEmitidos().getRowCount(); i++){					
							getTblChequesEmitidos().setValueAt(true, i, 0);
						}
					} else {
						for(int i=0; i<getTblChequesEmitidos().getRowCount(); i++){					
							getTblChequesEmitidos().setValueAt(false, i, 0);
						}
					}									
				}else{
					SpiritAlert.createAlert("No existe ninguna fila en la tabla !",SpiritAlert.INFORMATION);
				}
			}
		});
	}
	
	private void cargarComboEstado(){
		EstadoChequeEmitido estados[] = EstadoChequeEmitido.values();
		List lista = Arrays.asList(estados);
		refreshCombo(getCmbEstado(), lista);
	}
	
	private void cargarComboCuentasBancarias(){
		try {
			Map queryMap = new HashMap();
			queryMap.put("empresaId", Parametros.getIdEmpresa());
			queryMap.put("cuentaCliente", "N");
			List cuentasBancarias = (List) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(queryMap);
			refreshCombo(getCmbCuentaBancaria(), cuentasBancarias);
			if(cuentasBancarias.size()>0)setearNombreBanco();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void clean() {
		fechasColeccion = new Calendar[]{};
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCmbFechaCobro().setCalendar(new GregorianCalendar());
		getCmbEstado().setSelectedItem(EstadoChequeEmitido.TODOS);
		cuentasBancariasMap = new HashMap<Long, CuentaBancariaIf>();
		cuentasBancariasMap.clear();
		cleanTable();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblChequesEmitidos().getModel();
		for(int i= this.getTblChequesEmitidos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	@Override
	public void delete() {
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
	
	public void generarChequesSeleccionados(){
		chequesSeleccionadosColeccion.clear();
		int indiceReordenado = 0;
		for(int i=0; i< chequesColeccion.size(); i++){
			indiceReordenado = getTblChequesEmitidos().convertRowIndexToModel(i);
			ChequeEmitidoIf chequeEmitido = chequesColeccion.get(indiceReordenado);
			boolean seleccionadoEmitido = (Boolean)getTblChequesEmitidos().getModel().getValueAt(indiceReordenado,COLUMNA_EMITIDO);
			boolean seleccionadoCobrado = (Boolean)getTblChequesEmitidos().getModel().getValueAt(indiceReordenado,COLUMNA_COBRADO);
			String estado = chequeEmitido.getEstado();
			//if((Boolean)getTblChequesEmitidos().getModel().getValueAt(indiceReordenado,0) && chequeEmitido.getEstado().equals(ESTADO_EMITIDO)){
			if( seleccionadoEmitido && EstadoChequeEmitido.EMITIDO.getLetra().equals(estado)){
				chequeEmitido.setEstado(EstadoChequeEmitido.COBRADO.getLetra());
				chequeEmitido.setFechaCobro(new Date(fechasColeccion[indiceReordenado].getTime().getTime()));
				chequesSeleccionadosColeccion.add(chequeEmitido);
			//}else if( seleccionadoCobrado && estado.equals(ESTADO_COBRADO)){
			}else if( seleccionadoCobrado && EstadoChequeEmitido.COBRADO.getLetra().equals(estado)){
				chequeEmitido.setEstado(EstadoChequeEmitido.EMITIDO.getLetra());
				chequeEmitido.setFechaCobro(null);
				chequesSeleccionadosColeccion.add(chequeEmitido);
			}
		}
	}

	public void save() {
		
	}

	public void update() {
		try {
			if(validateFields()){
				generarChequesSeleccionados();
				if(chequesSeleccionadosColeccion.size()>0){
					SessionServiceLocator.getChequeEmitidoSessionService().cambiarEstadoCheques(chequesSeleccionadosColeccion);
					SpiritAlert.createAlert("El Estado de los cheques se ha cambiado exitosamente!", SpiritAlert.INFORMATION);
					cleanTable();
					cargarTabla();
				}else{
					SpiritAlert.createAlert("Debe seleccionar al menos un cheque!", SpiritAlert.INFORMATION);
				}
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		int indiceReordenado = 0;
		for(int i=0; i< chequesColeccion.size(); i++){
			indiceReordenado = getTblChequesEmitidos().convertRowIndexToModel(i);
			if((Boolean)getTblChequesEmitidos().getModel().getValueAt(indiceReordenado,COLUMNA_EMITIDO) && (fechasColeccion[indiceReordenado] == null)){
				SpiritAlert.createAlert("Uno de los cheques seleccionados no tiene fecha de Cobro!", SpiritAlert.WARNING);
				getTblChequesEmitidos().getSelectionModel().setSelectionInterval(i, i);
				return false;
			}
		}
		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void showFindMode() {
		showUpdateMode();
	}

	public void showSaveMode() {
		showUpdateMode();
	}
	
	private void loadMaps() {
		cuentasBancariasMap = mapearCuentasBancarias();
	}
	
	private Map<Long, CuentaBancariaIf> mapearCuentasBancarias() {
		Map<Long, CuentaBancariaIf> cuentasBancariasMap = new HashMap<Long, CuentaBancariaIf>();
		try {
			Iterator<CuentaBancariaIf> it = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				CuentaBancariaIf cuentaBancaria = it.next();
				cuentasBancariasMap.put(cuentaBancaria.getId(), cuentaBancaria);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear cuentas bancarias", SpiritAlert.ERROR);
		}
		return cuentasBancariasMap;
	}

	private void cargarTabla() {
		setCursor(SpiritCursor.hourglassCursor);
		try {
			chequesColeccion.clear();
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			EstadoChequeEmitido estadoCheque = (EstadoChequeEmitido)getCmbEstado().getSelectedItem();
			Map chequeMap = new HashMap();
			chequeMap.put("cuentaBancariaId", ((CuentaBancariaIf)getCmbCuentaBancaria().getSelectedItem()).getId());
			if ( estadoCheque != EstadoChequeEmitido.TODOS )
				chequeMap.put("estado", estadoCheque.getLetra());
			/*if(estadoCheque.equals(NOMBRE_ESTADO_EMITIDO)){
				chequeMap.put("estado", ESTADO_EMITIDO);
			}else if(estadoCheque.equals(NOMBRE_ESTADO_COBRADO)){
				chequeMap.put("estado", ESTADO_COBRADO);
			}else if(estadoCheque.equals(NOMBRE_ESTADO_ANULADO)){
				chequeMap.put("estado", ESTADO_ANULADO);
			}*/
			
			chequesColeccion = (ArrayList)SessionServiceLocator.getChequeEmitidoSessionService().findChequeEmitidoByQueryByFechaInicioAndByFechaFin(chequeMap, fechaInicio, fechaFin);
						
			if(chequesColeccion.size()>0){
				Iterator chequesColeccionIterator = chequesColeccion.iterator();
				
				while (chequesColeccionIterator.hasNext()) {
					ChequeEmitidoIf chequeEmitidoIf = (ChequeEmitidoIf) chequesColeccionIterator.next();
					
					tableModel = (DefaultTableModel) getTblChequesEmitidos().getModel();
					Vector<Object> fila = new Vector<Object>();
					agregarFilaTabla(chequeEmitidoIf, fila);					
					tableModel.addRow(fila);
				}
				fechasColeccion = new Calendar[chequesColeccion.size()];
			}else{
				SpiritAlert.createAlert("No existe ningún registro que presentar !",SpiritAlert.INFORMATION);
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
		setCursor(SpiritCursor.normalCursor);
	}
	
	private void agregarFilaTabla(ChequeEmitidoIf chequeEmitidoIf, Vector<Object> fila){
		try {
			fila.add(false);
			fila.add(Utilitarios.getFechaCortaUppercase(chequeEmitidoIf.getFechaEmision()));
			CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(chequeEmitidoIf.getCuentaBancariaId());
			fila.add(cuentaBancaria.getCuenta());
			fila.add(chequeEmitidoIf.getNumero());
			fila.add(formatoDecimal.format(chequeEmitidoIf.getValor()));
			String letraEstado = chequeEmitidoIf.getEstado();
			EstadoChequeEmitido estado = EstadoChequeEmitido.getEstadoChequeEmitido(letraEstado);
			fila.add(estado.toString());
			fila.add(chequeEmitidoIf.getFechaCobro() != null ? Utilitarios.getFechaCortaUppercase(chequeEmitidoIf.getFechaCobro()) : "");
			fila.add(chequeEmitidoIf.getBeneficiario());
			fila.add(chequeEmitidoIf.getDetalle());
			fila.add(OrigenCheque.getOrigenChequeByLetra(chequeEmitidoIf.getOrigen()));
			fila.add(false);
									
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		clean();
		loadMaps();
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		
	}
}
