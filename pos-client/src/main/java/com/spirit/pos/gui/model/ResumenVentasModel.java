package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDResumenVentas;
import com.spirit.util.Utilitarios;

public class ResumenVentasModel extends JDResumenVentas{
	private static final long serialVersionUID = 1L;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	Long cajaAbiertaID=new Long("0"); 
	Vector<PedidoDetalleIf> detalles_venta = new Vector<PedidoDetalleIf>();
	private DefaultTableModel tableModel;	
	private JTable tableModelOriginal;
	Vector<String> totales = new Vector<String>();
	public Collection colec=null;
	Vector<Vector> detalles_pagos = new Vector<Vector>();
	private DefaultTableModel tableModelPagos;	
	String estado=null;	
	private boolean limpiar=false;
	private boolean ejecutar=false;	
	String tipoproductouno="";
	String tipoRP="";
	Double sumaPuntosDinero=0.00;

	public ResumenVentasModel(Frame owner,Vector<PedidoDetalleIf> ProductosidReunionColeccion,JTable tableModelOriginaluno,Vector<String> totalesuno,Vector<Vector> pagoscoleccion,String estado_uno,String tipouno,boolean bloqueado,String Referido,Double puntosDineroGanado) {		
		super(owner);
		initListeners();
		
		estado=estado_uno;//cuando estado ="A" entonces si puede procesar, si estado="C" entonces el estado es cancelado
		tableModelOriginal=tableModelOriginaluno;
		detalles_venta=ProductosidReunionColeccion;		
		totales=totalesuno;	//	pagos=pagoscoleccion;		
		tipoproductouno=tipouno;
		detalles_pagos=pagoscoleccion;
		tipoRP=Referido;
		sumaPuntosDinero=puntosDineroGanado;
		iniciarcomp();
		
		if(bloqueado)
			getBtnRegresar().setEnabled(false);
		else
			getBtnRegresar().setEnabled(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				getBtnProcesarCancelar().requestFocus();
			}
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});	
	}

	private void initListeners() {
		getBtnProcesarCancelar().addActionListener(oActionListenerBotonProcesarCancelarVenta);		
		getBtnRegresar().addActionListener(oActionListenerBotonRegresar);			
	}

	private void iniciarcomp(){			 
		Caja_abierta_id();	 
		DefaultTableModel model = (DefaultTableModel) getTblDetalles().getModel();
		for(int i= this.getTblDetalles().getRowCount();i>0;--i)	model.removeRow(i-1);	
		tableModel = (DefaultTableModel) getTblDetalles().getModel();

		DefaultTableModel modelPagos = (DefaultTableModel) getTblFormaPago().getModel();
		for(int i= this.getTblFormaPago().getRowCount();i>0;--i)	modelPagos.removeRow(i-1);	
		tableModelPagos = (DefaultTableModel) getTblFormaPago().getModel();

		Border xy=null;
		getTxtTitulo().setBorder(xy);
		llenar_tabla();

		if(estado.equals("C"))					
			getBtnProcesarCancelar().setText("Cancelar Venta");
		else
			getBtnProcesarCancelar().setText("Procesar Venta");	 
		
		//ES REFERIFO
		if(tipoRP.equals("S")) getLblPuntos().setText("Puntos dados al referido:");
		if(tipoRP.equals("N")) getLblPuntos().setText("Puntos Ganados:");
		
		getLblPuntosDineroSumados().setText(sumaPuntosDinero.toString());
		
		
	}

	ActionListener oActionListenerBotonProcesarCancelarVenta= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			//aceptar();
			//AQUI GUARDAR Y MANDAR A CREAR PEDIDO, FACTURA, ETC ETC.
			ejecutar=true;
			dispose(); 
		}
	};

	ActionListener oActionListenerBotonRegresar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			ejecutar=false;
			dispose(); 
		}
	};

	public void Caja_abierta_id(){
		Long caja_id=new Long("0");
		Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();		
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "A");
		aMap.put("fechafin",null);
		cajaAbiertaID=new Long("0");
		Iterator cajavalorIt;
		try {
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {
				CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor=valor_actual.getValorInicial();				 	 
				caja_id=valor_actual.getId();
			} else {
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);				 
				dispose(); 				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		this.cajaAbiertaID=caja_id;
	}

	public Long getId_tipoproducto(String tipoproducto) {
		// String nombre_gc="GIFT CARD";/="PERSONALIZACION WEB";
		String nombre_gc = tipoproducto;
		Long id_giftcard = 0L;
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("nombre", nombre_gc);
		TipoProductoIf tipoProductoIf = null;
		try {			
			Iterator itTipo=null;
			itTipo = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByQuery(aMap).iterator();
			if (itTipo.hasNext()) {
				tipoProductoIf = (TipoProductoIf)itTipo.next();
				id_giftcard=tipoProductoIf.getId();
			} 
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		return (id_giftcard);
	}

	public void llenar_tabla(){	
		TableColumn anchoColumna = getTblDetalles().getColumnModel().getColumn(0);		
		anchoColumna.setMinWidth(45);
		anchoColumna = getTblDetalles().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(45);		
		anchoColumna = getTblDetalles().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(150);		
		Vector<PedidoDetalleIf> ProductosidReunionColeccion = detalles_venta;	
		
		System.out.println("detalles_venta>>>"+detalles_venta.size());
		if (ProductosidReunionColeccion.size() != 0) {
			for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
				System.out.println("detalles_venta--"+ProductosidReunionColeccion.size()+"*****size l***"+l);
				PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);				
				String codigo= (String)tableModelOriginal.getModel().getValueAt(l,0);					
				String tipo_producto= (String)tableModelOriginal.getModel().getValueAt(l,8);					

				if(!tipo_producto.equals(getId_tipoproducto("GIFT CARD").toString()) && !tipo_producto.equals(getId_tipoproducto("PERSONALIZACION WEB").toString())) {						
					tipo_producto=tipoproductouno;
				}

				if(tipo_producto.equals(getId_tipoproducto("GIFT CARD").toString())) tipo_producto="GIFT CARD";
				if(tipo_producto.equals(getId_tipoproducto("PERSONALIZACION WEB").toString())) tipo_producto="PERS. WEB";
				Vector<String> fila = new Vector<String>();				
				fila.add(tipo_producto);
				fila.add(codigo);
				fila.add(temporal.getDescripcion());
				fila.add(temporal.getCantidad().toString());
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(temporal.getPrecio().doubleValue())));	
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(temporal.getDescuento().doubleValue())));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(temporal.getIva().doubleValue())));
				tableModel.addRow(fila); 					
			}				
		}

		System.out.println("totales--->"+totales.size());
		System.out.println("get descuento!*--->"+totales.get(1));
		
		getLblsubtotal().setText(totales.get(0));
		getLbldescuento().setText(totales.get(1));
		getLblimpuestos().setText(totales.get(2));		
		getLbltotal().setText(totales.get(3));
		System.out.println("formas de pago--->"+tableModelPagos.getRowCount());

		if (detalles_pagos.size() != 0) {
			for (int l = 0; l < detalles_pagos.size(); l++) {
				Vector<String> fila = new Vector<String>();				
				fila.add(((Vector)detalles_pagos.get(l)).get(0).toString());
				fila.add(((Vector)detalles_pagos.get(l)).get(1).toString());
				fila.add(((Vector)detalles_pagos.get(l)).get(2).toString());
				fila.add(((Vector)detalles_pagos.get(l)).get(3).toString());
				tableModelPagos.addRow(fila);
			}
		}
	}

	public boolean isEjecutar() {
		return ejecutar;
	}

	public void setEjecutar(boolean ejecutar) {
		this.ejecutar = ejecutar;
	}

 
}
