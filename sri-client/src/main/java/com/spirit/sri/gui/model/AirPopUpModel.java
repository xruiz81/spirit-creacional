package com.spirit.sri.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.sri.at.DetalleVentas;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.gui.panel.JDAirPopUp;
import com.spirit.sri.reoc.DetalleAir;
import com.spirit.sri.reoc.DetalleCompras;
import com.spirit.util.Utilitarios;

public class AirPopUpModel extends JDAirPopUp{

	private static final long serialVersionUID = 1L;
	DetalleVentas detalleVenta = null;
	DetalleCompras detalleCompra = null;
	Collection porcentajesAir = null;
	private DecimalFormat formatoDosDecimales = new DecimalFormat("#,###,###.00");
	
	private SriAirIf airCmbSeleccionado = null;
	private int filaSeleccionada = -1;
	private DetalleAir detalleAirSeleccionado = null;
	private Vector<DetalleAir> detalleAirVector = new Vector<DetalleAir>();

	public AirPopUpModel(Frame owner,boolean modificar,DetalleVentas detalleVenta,Collection porcentajesAir) {
		super(owner);
		setearEscKey();
		setModal(true);
		initImagenes();
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
		);
		this.pack();

		this.detalleVenta = detalleVenta;
		this.porcentajesAir = porcentajesAir;
		initListener();
		iniciarComponentes();
		iniciarModificacion(modificar);
		cargarTabla();
		getTxtValor().setText(this.detalleVenta.getBaseImpGrav().toString());
		obtenerValoresCalculados();

		setVisible(true);
	}
	
	public AirPopUpModel(Frame owner,boolean modificar,DetalleCompras detalleCompra,Collection porcentajesAir) {
		super(owner);
		setearEscKey();
		setModal(true);
		initImagenes();
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 4, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 4
		);
		this.pack();

		this.detalleCompra = detalleCompra;
		this.porcentajesAir = porcentajesAir;
		initListener();
		iniciarComponentes();
		iniciarModificacion(modificar);
		cargarTabla();
		//getTxtValor().setText(this.detalleCompra.getBaseImpGrav().toString());
		obtenerValoresCalculados();

		setVisible(true);
	}
	
	private void initImagenes(){
		getBtnAgregarDetalle().setText("");
		getBtnAgregarDetalle().setToolTipText("Agregar detalle");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setText("");
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}

	private void initListener(){
		//getTxtValor().addKeyListener(new TextChecker(7));
		//getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		//getTxtValor().addKeyListener(valorKeyListener);
		
		getBtnAgregarDetalle().addActionListener(agregarActionListener);
		getBtnActualizarDetalle().addActionListener(actualizarActionListener);
		getBtnEliminarDetalle().addActionListener(borrarActionListener);
		
		getTblAirPopUp().addMouseListener(tblDetalleAirMouseListener);
		getTblAirPopUp().addKeyListener(tblRetencionesClienteKeyListener);
	}
	
	ActionListener actualizarActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			update();
		}
	};
	
	ActionListener borrarActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			delete();
		}
	};

	ActionListener agregarActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if (validar()){
				DetalleAir detalleAir = new DetalleAir();
				detalleAir.setCodRetAir(airCmbSeleccionado.getCodigo());
				detalleAir.setBaseGrav(new BigDecimal(getTxtValor().getText()).setScale(2,BigDecimal.ROUND_HALF_UP));
				detalleAir.setPorcentaje(new BigDecimal(getTxtPorcentaje().getText()).setScale(2,BigDecimal.ROUND_HALF_UP));
				detalleAir.setValRetAir(new BigDecimal(getTxtValorRetenido().getText()).setScale(2,BigDecimal.ROUND_HALF_UP));
				if (detalleVenta!=null){
					//detalleVenta.getAir().addDetalleAir(detalleAir);
				}
				else{
					detalleCompra.getAir().addDetalleAir(detalleAir);
				}
				//FALTA AGREGAR A LA BASE
				//DefaultTableModel modelo = (DefaultTableModel) getTblAirPopUp().getModel();
				//modelo.addRow(getFila(detalleAir));
				cargarTabla();
			}
		}
	};
	
	MouseListener tblDetalleAirMouseListener = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener tblRetencionesClienteKeyListener = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblAirPopUp().getSelectedRow() != -1) {
			int selectedRow = getTblAirPopUp().getSelectedRow();
			filaSeleccionada =  getTblAirPopUp().convertRowIndexToModel(selectedRow); 
			detalleAirSeleccionado = detalleAirVector.get(filaSeleccionada);
			getTxtValor().setText( formatoDosDecimales.format(detalleAirSeleccionado.getBaseGrav()) );
			getTxtPorcentaje().setText(formatoDosDecimales.format(detalleAirSeleccionado.getPorcentaje()));
			getTxtValorRetenido().setText(formatoDosDecimales.format(detalleAirSeleccionado.getValRetAir()));
		}
	}
	
	KeyListener valorKeyListener = new KeyAdapter(){
		public void keyReleased(KeyEvent e) {
			obtenerValoresCalculados();
		}
	};
	
	public void update() {
		try {	
			if (validar()) {
				SriAirIf airSeleccionado = (SriAirIf) getCmbCodigo().getSelectedItem();
				detalleAirSeleccionado.setCodRetAir(airSeleccionado.getCodigo());
				detalleAirSeleccionado.setBaseGrav(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtValor().getText())).setScale(2,BigDecimal.ROUND_HALF_UP));
				detalleAirSeleccionado.setPorcentaje(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPorcentaje().getText())).setScale(2,BigDecimal.ROUND_HALF_UP));
				detalleAirSeleccionado.setValRetAir(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtValorRetenido().getText())).setScale(2,BigDecimal.ROUND_HALF_UP));
				//SE TIENE QUE ACTUALIZAR EN LA BASEM PERO AHORA SOLO SE LO HACE EN LA TABLA
				//getTipoComrobanteSessionService().saveSriTipoComprobante(tipoComprobanteIf);
				SpiritAlert.createAlert("Tipo de Comprobante actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				cargarTabla();
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",SpiritAlert.ERROR);
		}		
	}
	
	public void delete() {	
		try {
			//DetalleAir detalle = detalleAirVector.get(filaSeleccionada);
			/*
			if (detalleVenta!=null)
				detalleVenta.getAir().removeDetalleAir(filaSeleccionada);
			else
				detalleCompra.getAir().removeDetalleAir(detalleAirSeleccionado);
			*/
			
			//SE BORRA DE LA BASE, PERO AHORA SOLO DE LA TABLA
			//getTipoComrobanteSessionService().deleteSriTipoComprobante(tipoComprobante.getId());
			DefaultTableModel modelo = (DefaultTableModel) getTblAirPopUp().getModel();
			modelo.removeRow(filaSeleccionada);
			SpiritAlert.createAlert("Detalle Air eliminado con \u00e9xito", SpiritAlert.INFORMATION);
			cargarTabla();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	private boolean validar(){
		String txtValor = getTxtValor().getText();
		String txtPorcentaje = getTxtPorcentaje().getText();
		String txtRetenido = getTxtValorRetenido().getText();
		if (txtValor==null || "".equals(txtValor)){
			SpiritAlert.createAlert("El valor no puede estar vacio", SpiritAlert.WARNING);
			return false;
		}
		BigDecimal valor = new BigDecimal(Utilitarios.removeDecimalFormat(txtValor));
		if ( 0.0 == valor.doubleValue() ){
			SpiritAlert.createAlert("El valor retenido no puede ser cero", SpiritAlert.WARNING);
			return false;
		}
			
		if (txtPorcentaje==null || "".equals(txtPorcentaje)){
			SpiritAlert.createAlert("No se ha definido el porcentaje de retenci\u00f3n", SpiritAlert.WARNING);
			return false;
		}
		if (txtRetenido==null || "".equals(txtRetenido)){
			SpiritAlert.createAlert("No se ha definido el valor a retenerse", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	private void iniciarComponentes(){
		getTxtPorcentaje().setEditable(false);
		//getTxtValor().setEditable(false);
		getTxtValorRetenido().setEditable(false);
		llenarPorcentajesAir();
		
		//SE DESHABILITA AGREGAR Y ELIMINAR
		getBtnAgregarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
	}
	
	private void cargarTabla(){
		clean();
		
		if(!detalleAirVector.isEmpty()){
			detalleAirVector.removeAllElements();
		}
		
		DefaultTableModel modeloTabla = (DefaultTableModel)getTblAirPopUp().getModel();
		List listaDetalleAir = null;
		if (detalleVenta!=null){
			//listaDetalleAir = Arrays.asList(detalleVenta.getAir().getDetalleAir());
			listaDetalleAir = new ArrayList();
		} else {
			listaDetalleAir = Arrays.asList(detalleCompra.getAir().getDetalleAir());
		}
		
		for(Iterator itAir = listaDetalleAir.iterator();itAir.hasNext();){
			DetalleAir detalle = (DetalleAir)itAir.next();
			detalleAirVector.add(detalle);
			modeloTabla.addRow(getFila(detalle));
		}
	} 
	
	private String[] getFila(DetalleAir detalle){
		String[] fila = new String[4];
		fila[0] = detalle.getCodRetAir();
		fila[1] = detalle.getBaseGrav()!=null?formatoDosDecimales.format(detalle.getBaseGrav().doubleValue()):"";
		fila[2] = detalle.getPorcentaje()!=null?formatoDosDecimales.format(detalle.getPorcentaje().doubleValue()):"";
		fila[3] = detalle.getValRetAir()!=null?formatoDosDecimales.format(detalle.getValRetAir().doubleValue()):"";
		return fila;
	}
	
	private void clean(){
		getTxtValor().setText("");
		getTxtPorcentaje().setText("");
		getTxtValorRetenido().setText("");
		
		DefaultTableModel modeloTabla = (DefaultTableModel) getTblAirPopUp().getModel(); 
		for (int i=getTblAirPopUp().getRowCount()-1;i>=0;i--){
			modeloTabla.removeRow(i);
		}
	}

	private void obtenerValoresCalculados(){
		SriAirIf air = (SriAirIf)getCmbCodigo().getSelectedItem();
		if (air != null){
			BigDecimal valorPorcentaje = air.getPorcentaje();
			getTxtPorcentaje().setText(valorPorcentaje.toString());
			if ( getTxtValor().getText()!=null && !"".equals(getTxtValor().getText()) ){
				BigDecimal valor = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtValor().getText()));
				double totalRetenido = valor.doubleValue()*valorPorcentaje.doubleValue()/100;
				getTxtValorRetenido().setText(formatoDosDecimales.format(totalRetenido));
			} else {
				getTxtPorcentaje().setText("");
				getTxtValorRetenido().setText("");
			}
		} else{
			getTxtPorcentaje().setText("");
			getTxtValorRetenido().setText("");
		}
	}

	private void llenarPorcentajesAir(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (Iterator itPorcentajes = porcentajesAir.iterator();itPorcentajes.hasNext();){
			SriAirIf air = (SriAirIf)itPorcentajes.next();
			model.addElement(air);
		}
		getCmbCodigo().setModel(model);
		if (model.getSize()>0){
			getCmbCodigo().setSelectedIndex(0);
			airCmbSeleccionado = (SriAirIf) getCmbCodigo().getSelectedItem();
		}
	}

	private void iniciarModificacion(boolean modificar){
		getTxtValor().setEditable(modificar);
	}

	//Se implementa la accion de salir con ESC
	private void setearEscKey(){
		ActionMap am = getRootPane().getActionMap();
		InputMap im = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		//InputMap im1 = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		Object windowCloseKey = new Object();
		//Object windowCloseKey1 = new Object();
		KeyStroke windowCloseStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action windowCloseAction = new AbstractAction() {
			private static final long serialVersionUID = -1003206414355095876L;
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		};
		im.put(windowCloseStroke, windowCloseKey);
		am.put(windowCloseKey, windowCloseAction);
	}   
}
