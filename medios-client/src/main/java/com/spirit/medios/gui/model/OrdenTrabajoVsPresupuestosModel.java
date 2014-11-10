package com.spirit.medios.gui.model;

import java.util.GregorianCalendar;

import com.spirit.client.model.SpiritResourceManager;
import com.spirit.medios.gui.panel.JPOrdenTrabajoVsPresupuestos;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class OrdenTrabajoVsPresupuestosModel extends JPOrdenTrabajoVsPresupuestos {
	
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_EN_CURSO = "EN CURSO";
	private static final String NOMBRE_ESTADO_REALIZADO = "REALIZADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_SUSPENDIDO = "SUSPENDIDO";
	public static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	public static final String ESTADO_EN_CURSO = NOMBRE_ESTADO_EN_CURSO.substring(0, 1);
	public static final String ESTADO_REALIZADO = NOMBRE_ESTADO_REALIZADO.substring(0, 1);
	public static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0, 1);
	public static final String ESTADO_SUSPENDIDO = NOMBRE_ESTADO_SUSPENDIDO.substring(0, 1);
	private static final String NOMBRE_ESTADO_TODOS = "TODOS";
	
	
	
	public OrdenTrabajoVsPresupuestosModel(){
		initKeyListeners();
		cargarComboEstado();
		showSaveMode();
	}
	
	public void initKeyListeners(){
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar Balance General");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblPresupuestos().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblPresupuestos().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
				
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	private void cargarComboEstado(){
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_EN_CURSO);
		getCmbEstado().addItem(NOMBRE_ESTADO_REALIZADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_CANCELADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_SUSPENDIDO);
		getCmbEstado().addItem(NOMBRE_ESTADO_TODOS);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_TODOS);
		getCmbEstado().repaint();
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
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}
}