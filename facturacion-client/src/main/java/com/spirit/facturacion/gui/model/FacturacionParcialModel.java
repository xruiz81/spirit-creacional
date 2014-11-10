package com.spirit.facturacion.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.panel.JDFacturacionParcial;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public class FacturacionParcialModel extends JDFacturacionParcial{
		
	private DefaultTableModel tablePresupuestoDetallesModel;
	private Long presupuestoId = 0L;
	private String tipoPresupuesto = "";
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ProductoIf> mapaProductoProveedor = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	private Map<Long, OrdenCompraIf> mapaOrdenCompra = new HashMap<Long, OrdenCompraIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ArrayList<Object> presupuestoDetalles = new ArrayList<Object>();
	private ArrayList<Object> presupuestoDetallesSeleccionados = new ArrayList<Object>();
	private Map<Long, Long> detallesFacturados = new HashMap<Long, Long>();
	
	
	public FacturacionParcialModel(Dialog owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public FacturacionParcialModel(Frame owner, String tipoPresupuesto, 
			Long idPresupuesto, Map<Long, Long> detallesFacturados, Map<Long, ClienteOficinaIf> mapaClienteOficina,
			Map<Long, GenericoIf> mapaGenerico, Map<Long, ProductoIf> mapaProductoProveedor) {
		super(owner);
		
		this.presupuestoId = idPresupuesto;
		this.tipoPresupuesto = tipoPresupuesto;
		this.detallesFacturados = detallesFacturados;
		
		this.mapaClienteOficina = mapaClienteOficina;
		this.mapaGenerico = mapaGenerico;
		this.mapaProductoProveedor = mapaProductoProveedor;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		cargarMapas();
		anchoColumnasTabla();
		llenarTabla();
		initKeyListeners();
		initListeners();
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblPresupuestoDetalles());
		getTblPresupuestoDetalles().getTableHeader().setReorderingAllowed(false);
		getTblPresupuestoDetalles().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblPresupuestoDetalles().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblPresupuestoDetalles().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblPresupuestoDetalles().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblPresupuestoDetalles().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);		
		anchoColumna = getTblPresupuestoDetalles().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		
		TableCellRendererHorizontalCenterAlignment tableCellCenterRenderer = new TableCellRendererHorizontalCenterAlignment();
		getTblPresupuestoDetalles().getColumnModel().getColumn(3).setCellRenderer(tableCellCenterRenderer);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblPresupuestoDetalles().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		
	}
	
	public void cargarMapas() {
		try {			
			mapaOrdenCompra.clear();
			if(tipoPresupuesto.equals("P")){
				Map solicitudesMapa = new HashMap();
				solicitudesMapa.put("tipoReferencia", "P");
				PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoId);
				solicitudesMapa.put("referencia", presupuesto.getCodigo());			
				Collection solicitudesCompra = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(solicitudesMapa);
				Iterator solicitudesCompraIt = solicitudesCompra.iterator();
				while(solicitudesCompraIt.hasNext()){
					SolicitudCompraIf solicitudCompraIf = (SolicitudCompraIf)solicitudesCompraIt.next();
					
					Collection ordenes = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompraIf.getId());
					Iterator ordenesIt = ordenes.iterator();
					while(ordenesIt.hasNext()){
						OrdenCompraIf ordeCompraIf = (OrdenCompraIf)ordenesIt.next();
						mapaOrdenCompra.put(ordeCompraIf.getId(), ordeCompraIf);						
					}
				}
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void llenarTabla(){
		try {
			cleanTable();
			tablePresupuestoDetallesModel = (DefaultTableModel) getTblPresupuestoDetalles().getModel();
			//si es de plan de medios
			if(tipoPresupuesto.equals("M")){	
				Collection ordenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(presupuestoId);
				Iterator ordenesMedioIt = ordenesMedio.iterator();
				while(ordenesMedioIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioIt.next();
					//si la orden no esta anulada y no ha sido facturada previamente y el canje no es del 100%
					if(!ordenMedioIf.getEstado().equals("A") && detallesFacturados.get(ordenMedioIf.getId()) == null &&
							ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(100)) == -1){
						Vector fila = new Vector();	
						fila.add(false);
						
						ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
						fila.add(proveedor.getDescripcion());
						
						ProductoIf producto = mapaProductoProveedor.get(ordenMedioIf.getProductoProveedorId());
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
						fila.add(generico.getNombre());
						
						fila.add(ordenMedioIf.getCodigo());
						
						fila.add(formatoDecimal.format(ordenMedioIf.getValorSubtotal()));
						
						tablePresupuestoDetallesModel.addRow(fila);
						presupuestoDetalles.add(ordenMedioIf);
					}
				}
			}
			
			//si es de presupuesto
			else if(tipoPresupuesto.equals("P")){	
				Collection detallesPresupuesto = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoId);
				Iterator detallesPresupuestoIt = detallesPresupuesto.iterator();
				while(detallesPresupuestoIt.hasNext()){
					PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)detallesPresupuestoIt.next();
					//si el detalle no es de reporte, y no ha sido facturado y el porcentaje de negociacion es menor al 100%
					if(presupuestoDetalleIf.getReporte().equals("N") && detallesFacturados.get(presupuestoDetalleIf.getId()) == null &&
							presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1){
						Vector fila = new Vector();	
						fila.add(false);
						
						ClienteOficinaIf proveedor = mapaClienteOficina.get(presupuestoDetalleIf.getProveedorId());
						fila.add(proveedor.getDescripcion());
						
						ProductoIf producto = mapaProductoProveedor.get(presupuestoDetalleIf.getProductoId());
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
						fila.add(generico.getNombre());
						
						OrdenCompraIf ordenCompra = presupuestoDetalleIf.getOrdenCompraId()!=null? mapaOrdenCompra.get(presupuestoDetalleIf.getOrdenCompraId()) : null;
						if(ordenCompra != null){
							fila.add(ordenCompra.getCodigo());
						}else{
							fila.add("");
						}
						
						double subTotal = presupuestoDetalleIf.getPrecioventa().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
						fila.add(formatoDecimal.format(subTotal));
						
						tablePresupuestoDetallesModel.addRow(fila);
						presupuestoDetalles.add(presupuestoDetalleIf);
					}
				}			
			}			
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblPresupuestoDetalles().getModel();
		for(int i= this.getTblPresupuestoDetalles().getRowCount();i>0;--i)
			model.removeRow(i-1);
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
	
	private void initKeyListeners() {
				
	}
		
	private void aceptar()throws CloneNotSupportedException{	
		
		if(validateFields()){			
			for(int i = 0; i < getTblPresupuestoDetalles().getModel().getRowCount(); i++){
				if((Boolean)getTblPresupuestoDetalles().getModel().getValueAt(i, 0)){
					presupuestoDetallesSeleccionados.add(presupuestoDetalles.get(i));
				}
			}			
			
			this.dispose();				
		}	
	}
	
	private void cancelar()throws CloneNotSupportedException{		
		this.dispose();
	}
	
	private boolean validateFields(){
		
		for(int i = 0; i < getTblPresupuestoDetalles().getModel().getRowCount(); i++){
			if((Boolean)getTblPresupuestoDetalles().getModel().getValueAt(i, 0)){
				return true;
			}
		}
		
		SpiritAlert.createAlert("Debe seleccionar al menos un detalle.", SpiritAlert.WARNING);
		return false;
	}

	public DefaultTableModel getTablePresupuestoDetallesModel() {
		return tablePresupuestoDetallesModel;
	}
	
	public ArrayList<Object> getPresupuestoDetallesSeleccionados() {
		return presupuestoDetallesSeleccionados;
	}
}
