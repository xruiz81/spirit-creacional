package com.spirit.facturacion.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.panel.JDFacturacionMultiple;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;


public class FacturacionMultipleModel extends JDFacturacionMultiple {
	
	private DefaultTableModel tablePresupuestosModel;
	private Long clienteOficinaId = 0L;
	private String tipoPresupuesto = "";
	private static final String NOMBRE_ESTADO_EN_PROCESO = "EN PROCESO";
	private static final String NOMBRE_ESTADO_PENDIENTE  = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO   = "APROBADO";
	private static final String NOMBRE_ESTADO_FACTURADO  = "FACTURADO";
	private static final String NOMBRE_ESTADO_HISTORICO  = "HISTORICO";
	private static final String NOMBRE_ESTADO_PEDIDO 	 = "PEDIDO";
	private static final String NOMBRE_ESTADO_PREPAGADO  = "PREPAGADO";
	private static final String NOMBRE_ESTADO_COTIZADO 	 = "COTIZADO";
	private static final String NOMBRE_ESTADO_CANCELADO  = "CANCELADO";
	private static final String ESTADO_EN_PROCESO = "N";
	private static final String ESTADO_PENDIENTE  = "P";
	private static final String ESTADO_APROBADO   = "A";
	private static final String ESTADO_FACTURADO  = "F";
	private static final String ESTADO_HISTORICO  = "H";
	private static final String ESTADO_PEDIDO 	  = "D";
	private static final String ESTADO_PREPAGADO  = "R";
	private static final String ESTADO_COTIZADO   = "C";
	private static final String ESTADO_CANCELADO  = "E";
	private static final String TIPO_REFERENCIA_PRESUPUESTO  = "P";
	private static final String TIPO_REFERENCIA_PLAN_MEDIOS  = "M";
	private static final String TIPO_REFERENCIA_COMBINADO  = "C";
	private ArrayList<Object> presupuestoDetalles = new ArrayList<Object>();
	private ArrayList<Object> presupuestosSeleccionados = new ArrayList<Object>();
		
	
	public FacturacionMultipleModel(Dialog owner){
		super(owner);
	}
	
	public FacturacionMultipleModel(Frame owner, Long idClienteOficina, String tipoPresupuesto) {
		super(owner);	
		clienteOficinaId = idClienteOficina;
		this.tipoPresupuesto = tipoPresupuesto;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		anchoColumnasTabla();
		llenarTabla();
		initListeners();
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblPresupuestoDetalles());
		getTblPresupuestos().getTableHeader().setReorderingAllowed(false);
		//getTblPresupuestos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblPresupuestos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(220);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);		
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
		
		TableCellRendererHorizontalCenterAlignment tableCellCenterRenderer = new TableCellRendererHorizontalCenterAlignment();
		getTblPresupuestos().getColumnModel().getColumn(1).setCellRenderer(tableCellCenterRenderer);
		getTblPresupuestos().getColumnModel().getColumn(3).setCellRenderer(tableCellCenterRenderer);
		getTblPresupuestos().getColumnModel().getColumn(4).setCellRenderer(tableCellCenterRenderer);		
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblPresupuestos().getModel();
		for(int i= this.getTblPresupuestos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void llenarTabla(){
		try {
			cleanTable();
			tablePresupuestosModel = (DefaultTableModel) getTblPresupuestos().getModel();
						
			Map mapaPlanMedios = new HashMap();
			Map mapaPresupuestos = new HashMap();
			
			//se busca los Planes de Medio o Presupuesto x cliente id		
			//SOLO ESTADO APROBADO
			int tamanoLista = 0;	
			//busco dependiendo si es plan de medio (M), o presupuesto (P)
			if(tipoPresupuesto.equals(TIPO_REFERENCIA_PLAN_MEDIOS)){
				tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapaPlanMedios, clienteOficinaId, Parametros.getIdEmpresa(), ESTADO_APROBADO); //,"D"
			}
			else if(tipoPresupuesto.equals(TIPO_REFERENCIA_PRESUPUESTO)){
				mapaPresupuestos.put("estado", ESTADO_APROBADO);
				mapaPresupuestos.put("clienteOficinaId", clienteOficinaId);
				tamanoLista = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQueryAndEmpresaIdListSize(mapaPresupuestos, Parametros.getIdEmpresa());
			}
			else if(tipoPresupuesto.equals(TIPO_REFERENCIA_COMBINADO)){
				//ES LA SUMA DE PRESUPUESTO Y PLAN DE MEDIOS
				int tamanoListaM = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapaPlanMedios, clienteOficinaId, Parametros.getIdEmpresa(), ESTADO_APROBADO); //,"D"
				mapaPresupuestos.put("estado", ESTADO_APROBADO);
				mapaPresupuestos.put("clienteOficinaId", clienteOficinaId);
				int tamanoListaP = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQueryAndEmpresaIdListSize(mapaPresupuestos, Parametros.getIdEmpresa());
				tamanoLista = tamanoListaP +  tamanoListaM;
			}
			
			if (tamanoLista > 0) {
				
				if(tipoPresupuesto.equals(TIPO_REFERENCIA_PLAN_MEDIOS)){
					agregarPlanesMedio(mapaPlanMedios);					
				}else if(tipoPresupuesto.equals(TIPO_REFERENCIA_PRESUPUESTO)){
					agregarPresupuestos(mapaPresupuestos);
				}else if(tipoPresupuesto.equals(TIPO_REFERENCIA_COMBINADO)){
					agregarPlanesMedio(mapaPlanMedios);
					agregarPresupuestos(mapaPresupuestos);
				}			
			}		
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarPlanesMedio(Map mapa){
		try {
			ArrayList planesMedioList = (ArrayList) SessionServiceLocator.getPlanMedioSessionService()
			.findPlanMedioByQueryAndByIdClienteOficina(0,100,mapa, clienteOficinaId, Parametros.getIdEmpresa(),ESTADO_APROBADO);
			
			Iterator planesMedioListIt = planesMedioList.iterator();
			
			while (planesMedioListIt.hasNext()) {
				PlanMedioIf planMedio = (PlanMedioIf) planesMedioListIt.next();

				Vector fila = new Vector();
									
				fila.add(false);
				fila.add(planMedio.getCodigo());
				fila.add(planMedio.getConcepto());
				
				if (ESTADO_EN_PROCESO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_EN_PROCESO);
				else if (ESTADO_PENDIENTE.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if (ESTADO_APROBADO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_APROBADO);
				else if (ESTADO_FACTURADO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_FACTURADO);
				else if (ESTADO_HISTORICO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_HISTORICO);
				else if (ESTADO_PEDIDO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_PEDIDO);
				else if (ESTADO_PREPAGADO.equals(planMedio.getEstado()))
					fila.add(NOMBRE_ESTADO_PREPAGADO);
				
				fila.add(planMedio.getRevision());

				tablePresupuestosModel.addRow(fila);
				presupuestoDetalles.add(planMedio);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}
	
	public void agregarPresupuestos(Map mapa){
		try {
			Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQueryAndEmpresaId(0, 100, mapa, Parametros.getIdEmpresa());
			Iterator presupuestosIt = presupuestos.iterator();
			while(presupuestosIt.hasNext()){
				PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
				
				Vector fila = new Vector();
				
				fila.add(false);
				fila.add(presupuesto.getCodigo());
				fila.add(presupuesto.getConcepto());
				
				if (ESTADO_COTIZADO.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_COTIZADO);
				else if (ESTADO_PENDIENTE.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if (ESTADO_APROBADO.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_APROBADO);
				else if (ESTADO_FACTURADO.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_FACTURADO);
				else if (ESTADO_CANCELADO.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_CANCELADO);
				else if (ESTADO_PREPAGADO.equals(presupuesto.getEstado()))
					fila.add(NOMBRE_ESTADO_PREPAGADO);
				
				fila.add("");

				tablePresupuestosModel.addRow(fila);
				presupuestoDetalles.add(presupuesto);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}
	
	private void initListeners() {
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					aceptar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});	
		
		getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					cancelar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
private void aceptar()throws CloneNotSupportedException{	
		
		if(validateFields()){			
			for(int i = 0; i < getTblPresupuestos().getModel().getRowCount(); i++){
				if((Boolean)getTblPresupuestos().getModel().getValueAt(i, 0)){
					presupuestosSeleccionados.add(presupuestoDetalles.get(i));
				}
			}			
			
			this.dispose();				
		}	
	}
	
	private void cancelar()throws CloneNotSupportedException{		
		this.dispose();
	}
	
	private boolean validateFields(){
		
		for(int i = 0; i < getTblPresupuestos().getModel().getRowCount(); i++){
			if((Boolean)getTblPresupuestos().getModel().getValueAt(i, 0)){
				return true;
			}
		}
		
		SpiritAlert.createAlert("Debe seleccionar al menos un detalle.", SpiritAlert.WARNING);
		return false;
	}
	
	public ArrayList<Object> getPresupuestosSeleccionados() {
		return presupuestosSeleccionados;
	}

	public void setPresupuestosSeleccionados(
			ArrayList<Object> presupuestosSeleccionados) {
		this.presupuestosSeleccionados = presupuestosSeleccionados;
	}
}
