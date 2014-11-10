package com.spirit.facturacion.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.WindowConstants;

import com.spirit.client.Parametros;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.facturacion.gui.panel.JDFacturacionPresupuesto;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;

public class FacturacionPresupuestoModel extends JDFacturacionPresupuesto {

	private static final long serialVersionUID = 1736248399797600031L;
	
	private HashMap<Long,ClienteOficinaIf> proveedoresNegociacionDirectaMap = new HashMap<Long,ClienteOficinaIf>();
	private HashMap<Long,ClienteOficinaIf> proveedoresComisionPuraMap = new HashMap<Long,ClienteOficinaIf>();
	private boolean habilitarFacturacionCliente = false;
	private ArrayList<Object> presupuestoDetallesSeleccionados = new ArrayList<Object>(); 
	private Long presupuestoId = 0L;
	private Map<Long,Long> presupuestoDetallesFacturadosMap = new HashMap<Long,Long>();
	
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ProductoIf> mapaProductoProveedor = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	
	
	public FacturacionPresupuestoModel(Frame owner, Long idPresupuesto, 
			HashMap<Long,ClienteOficinaIf> proveedoresNegociacionDirectaMap
			, HashMap<Long,ClienteOficinaIf> proveedoresComisionPuraMap, 
			boolean habilitarFacturacionCliente, Map<Long,Long> presupuestoDetallesFacturadosMap, 
			Map<Long, ClienteOficinaIf> mapaClienteOficina,	Map<Long, GenericoIf> mapaGenerico, 
			Map<Long, ProductoIf> mapaProductoProveedor) {
		super(owner);
		
		this.presupuestoId = idPresupuesto;
		this.proveedoresNegociacionDirectaMap = proveedoresNegociacionDirectaMap;
		this.proveedoresComisionPuraMap = proveedoresComisionPuraMap;
		this.habilitarFacturacionCliente = habilitarFacturacionCliente;
		this.presupuestoDetallesFacturadosMap = presupuestoDetallesFacturadosMap;
		
		this.mapaClienteOficina = mapaClienteOficina;
		this.mapaGenerico = mapaGenerico;
		this.mapaProductoProveedor = mapaProductoProveedor;
		
		llenarComboNegociacionDirecta();
		llenarComboComisionPura();
		habilitarOpcionesFacturacion();
		initListeners();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void habilitarOpcionesFacturacion(){
		
		if(habilitarFacturacionCliente){
			getRbFacturarCliente().setEnabled(true);
		}else{
			getRbFacturarCliente().setEnabled(false);
		}		
		
		if(!proveedoresNegociacionDirectaMap.isEmpty()){
			getRbFacturarNegociacionDirecta().setEnabled(true);
			getCmbMedioNegociacionDirecta().setEnabled(true);
		}else{
			getRbFacturarNegociacionDirecta().setEnabled(false);
			getCmbMedioNegociacionDirecta().setEnabled(false);
		}
		
		if(!proveedoresComisionPuraMap.isEmpty()){
			getRbFacturarComisionPura().setEnabled(true);
			getCmbMedioComisionPura().setEnabled(true);
		}else{
			getRbFacturarComisionPura().setEnabled(false);
			getCmbMedioComisionPura().setEnabled(false);
		}
		
		if(habilitarFacturacionCliente){
			getRbFacturarCliente().setSelected(true);
		}else if(!proveedoresNegociacionDirectaMap.isEmpty()){
			getRbFacturarNegociacionDirecta().setSelected(true);
		}else if(!proveedoresComisionPuraMap.isEmpty()){
			getRbFacturarComisionPura().setSelected(true);
		}
	}
	
	private void initListeners() {
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				aceptar();
			}
		});		
	}
	
	public void aceptar(){
		
		if(getRbFacturacionParcial().isSelected()){
			FacturacionParcialModel jdFacturacionParcialModel = new FacturacionParcialModel(Parametros.getMainFrame(), 
					"P", presupuestoId, presupuestoDetallesFacturadosMap, mapaClienteOficina, mapaGenerico, mapaProductoProveedor);
			setPresupuestoDetallesSeleccionados(jdFacturacionParcialModel.getPresupuestoDetallesSeleccionados());
			this.dispose();
		}else{
			this.dispose();
		}
	}
	
	public void llenarComboNegociacionDirecta(){
		try{
			getCmbMedioNegociacionDirecta().removeAllItems();
			Iterator proveedoresNegociacionDirectaMapIt = proveedoresNegociacionDirectaMap.keySet().iterator();
			while(proveedoresNegociacionDirectaMapIt.hasNext()){
				Long idClienteOficinaKey = (Long)proveedoresNegociacionDirectaMapIt.next();
				getCmbMedioNegociacionDirecta().addItem(proveedoresNegociacionDirectaMap.get(idClienteOficinaKey));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void llenarComboComisionPura(){
		try{
			getCmbMedioComisionPura().removeAllItems();
			Iterator proveedoresComisionPuraMapIt = proveedoresComisionPuraMap.keySet().iterator();
			while(proveedoresComisionPuraMapIt.hasNext()){
				Long idClienteOficinaKey = (Long)proveedoresComisionPuraMapIt.next();
				getCmbMedioComisionPura().addItem(proveedoresComisionPuraMap.get(idClienteOficinaKey));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Object> getPresupuestoDetallesSeleccionados() {
		return presupuestoDetallesSeleccionados;
	}

	public void setPresupuestoDetallesSeleccionados(
			ArrayList<Object> presupuestoDetallesSeleccionados) {
		this.presupuestoDetallesSeleccionados = presupuestoDetallesSeleccionados;
	}	
	
}
