package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.panel.JPOrdenesEstado;
import com.spirit.util.EachRowEditor;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class OrdenesEstadoModel extends JPOrdenesEstado {
	
	private DefaultTableModel tableModel;
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<OrdenCompraIf> ordenesCompraVector = new Vector<OrdenCompraIf>();
	private Vector<OrdenMedioIf> ordenesMedioVector = new Vector<OrdenMedioIf>();
	
	
	public OrdenesEstadoModel(){
		initKeyListeners();
		anchoColumnasTabla();
		cargarMapas();
		initListeners();
		showSaveMode();
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setToolTipText("Consultar Ordenes");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblOrdenesEstado());
		getTblOrdenesEstado().getTableHeader().setReorderingAllowed(false);
		//getTblOrdenesEstado().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblOrdenesEstado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesEstado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblOrdenesEstado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesEstado().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesEstado().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
				
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblOrdenesEstado().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		getTblOrdenesEstado().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblOrdenesEstado().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblOrdenesEstado().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
	}
	
	public void cargarMapas(){
		try {
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	public void clean() {
		getTxtPresupuesto().setText("");
		cleanTable();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblOrdenesEstado().getModel();
		for(int i= this.getTblOrdenesEstado().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(!getTxtPresupuesto().getText().equals("")){
					cleanTable();
					cargarTabla();
				}else{
					SpiritAlert.createAlert("Debe ingresar un código de Presupuesto", SpiritAlert.INFORMATION);
				}			
			}
		});
	}
	
	private void cargarTabla() {
		try {
			ordenesCompraVector = new Vector<OrdenCompraIf>();
			ordenesMedioVector = new Vector<OrdenMedioIf>();
			
			EachRowEditor rowEditor = new EachRowEditor(getTblOrdenesEstado());
			int numeroFila = 0;
			
			tableModel = (DefaultTableModel) getTblOrdenesEstado().getModel();
			
			String codigoPresupuesto = getTxtPresupuesto().getText();
			
			//////PRESUPUESTOS////////////////			
			Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
			Iterator presupuestosIt = presupuestos.iterator();
			while(presupuestosIt.hasNext()){
				PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestosIt.next();
				Collection ordenesCompra = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByPresupuestoId(presupuestoIf.getId());
				Iterator ordenesCompraIt = ordenesCompra.iterator();
				while(ordenesCompraIt.hasNext()){
					OrdenCompraIf ordenCompraIf = (OrdenCompraIf)ordenesCompraIt.next();
					
					if(ordenCompraIf.getEstado().equals(EstadoOrdenCompra.EMITIDA.getLetra())
							|| ordenCompraIf.getEstado().equals(EstadoOrdenCompra.ORDENADA.getLetra())
							|| ordenCompraIf.getEstado().equals(EstadoOrdenCompra.ENVIADA.getLetra())
							|| ordenCompraIf.getEstado().equals(EstadoOrdenCompra.PREPAGADA.getLetra())){
						
						//reviso tambien si no tiene asociada una compra
						Map ordenAsociadaMap = new HashMap();
						ordenAsociadaMap.put("tipoOrden", "OC");
						ordenAsociadaMap.put("ordenId", ordenCompraIf.getId());
						Collection ordenAsociada = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(ordenAsociadaMap);
						if(ordenAsociada.size() == 0){
							Vector<Object> fila = new Vector<Object>();
							fila.add(ordenCompraIf.getCodigo());
							
							ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenCompraIf.getProveedorId());
							fila.add(proveedor.getDescripcion());
							
							fila.add(Utilitarios.getFechaCortaUppercase(ordenCompraIf.getFecha()));
							
							fila.add(formatoDecimal.format(ordenCompraIf.getValor()));
							
							Object[] estados = new Object[]{EstadoOrdenCompra.EMITIDA, EstadoOrdenCompra.ORDENADA, EstadoOrdenCompra.ENVIADA, EstadoOrdenCompra.PREPAGADA};
							DefaultComboBoxModel modelo = new DefaultComboBoxModel(estados);
							JComboBox comboBox = new JComboBox(modelo);
							
							fila.add(EstadoOrdenCompra.getEstadoOrdenCompraByLetra(ordenCompraIf.getEstado()));
							
							rowEditor.setEditorAt(numeroFila, new DefaultCellEditor(comboBox));
							numeroFila++;
							
							tableModel.addRow(fila);
							ordenesCompraVector.add(ordenCompraIf);
						}						
					}
				}				
			}
			
			//////PLAN DE MEDIOS////////////////
			Collection planesMedios = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigo(codigoPresupuesto);
			Iterator planesMediosIt = planesMedios.iterator();
			while(planesMediosIt.hasNext()){
				PlanMedioIf planMedioIf = (PlanMedioIf)planesMediosIt.next();
				Collection ordenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());
				Iterator ordenesMedioIt = ordenesMedio.iterator();
				while(ordenesMedioIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioIt.next();
					
					if(ordenMedioIf.getEstado().equals(EstadoOrdenCompra.EMITIDA.getLetra())
							|| ordenMedioIf.getEstado().equals(EstadoOrdenCompra.ORDENADA.getLetra())
							|| ordenMedioIf.getEstado().equals(EstadoOrdenCompra.ENVIADA.getLetra())
							|| ordenMedioIf.getEstado().equals(EstadoOrdenCompra.PREPAGADA.getLetra())){
						
						//reviso tambien si no tiene asociada una compra
						Map ordenAsociadaMap = new HashMap();
						ordenAsociadaMap.put("tipoOrden", "OM");
						ordenAsociadaMap.put("ordenId", ordenMedioIf.getId());
						Collection ordenAsociada = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(ordenAsociadaMap);
						if(ordenAsociada.size() == 0){
							Vector<Object> fila = new Vector<Object>();
							fila.add(ordenMedioIf.getCodigo());
							
							ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
							fila.add(proveedor.getDescripcion());
							
							fila.add(Utilitarios.getFechaCortaUppercase(ordenMedioIf.getFechaOrden()));
							
							fila.add(formatoDecimal.format(ordenMedioIf.getValorSubtotal()));
							
							Object[] estados = new Object[]{EstadoOrdenCompra.EMITIDA, EstadoOrdenCompra.ORDENADA, EstadoOrdenCompra.ENVIADA, EstadoOrdenCompra.PREPAGADA};
							DefaultComboBoxModel modelo = new DefaultComboBoxModel(estados);
							JComboBox comboBox = new JComboBox(modelo);
							
							fila.add(EstadoOrdenCompra.getEstadoOrdenCompraByLetra(ordenMedioIf.getEstado()));
							
							rowEditor.setEditorAt(numeroFila, new DefaultCellEditor(comboBox));
							numeroFila++;
							
							tableModel.addRow(fila);
							ordenesMedioVector.add(ordenMedioIf);
						}						
					}
				}				
			}
			
			getTblOrdenesEstado().getColumn("Estado").setCellEditor(rowEditor);
			
			report();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
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

	public void report() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		try {
			DefaultTableModel model = (DefaultTableModel) getTblOrdenesEstado().getModel();
			
			for(int i = 0; i < ordenesCompraVector.size(); i++){
				OrdenCompraIf ordenCompra = ordenesCompraVector.get(i);
				EstadoOrdenCompra estado = (EstadoOrdenCompra)model.getValueAt(i,4);
				if(!ordenCompra.getEstado().equals(estado.getLetra())){
					ordenCompra.setEstado(estado.getLetra());
					SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompra);
				}
			}
			
			for(int i = 0; i < ordenesMedioVector.size(); i++){
				OrdenMedioIf ordenMedio = ordenesMedioVector.get(i);
				EstadoOrdenCompra estado = (EstadoOrdenCompra)model.getValueAt(i,4);
				if(!ordenMedio.getEstado().equals(estado.getLetra())){
					ordenMedio.setEstado(estado.getLetra());
					SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedio);
				}
			}
			
			SpiritAlert.createAlert("Estados actualizados con éxito.", SpiritAlert.INFORMATION);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
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
		clean();
		setSaveMode();
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
