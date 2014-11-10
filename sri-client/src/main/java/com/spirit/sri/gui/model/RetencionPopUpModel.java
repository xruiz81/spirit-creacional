package com.spirit.sri.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.spirit.sri.gui.panel.JDRetencionPopUp;
import com.spirit.sri.reoc.DetalleCompras;

public class RetencionPopUpModel extends JDRetencionPopUp {

	private static final long serialVersionUID = 911721073398468576L;

	DetalleCompras detalleCompra = null;
	int filaSeleccionada = -1;

	public RetencionPopUpModel(Frame owner,DetalleCompras detalleCompra) {
		super(owner);
		setearEscKey();
		setModal(true);
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
		);
		this.pack();
		this.detalleCompra = detalleCompra;
		initListener();
		cargarTabla();
		setVisible(true);
	}	

	private void cargarTabla(){
		clean();
		DefaultTableModel modeloTabla = (DefaultTableModel)getTblRetencionPopUp().getModel();
		if (detalleCompra.getEstabRet()!=null){
			String serie = detalleCompra.getEstabRet()+" - "+detalleCompra.getPtoEmiRet();
			String secuencial = String.valueOf(detalleCompra.getSecRet());
			String autorizacion = detalleCompra.getAutRet();
			String fechaEmision = detalleCompra.getFechaEmiRet();
			Object[] fila = new Object[]{serie,secuencial,autorizacion,fechaEmision};
			modeloTabla.addRow(fila);
		}
		if (detalleCompra.getEstabRet1()!=null){
			String serie = detalleCompra.getEstabRet1()+" - "+detalleCompra.getPtoEmiRet1();
			String secuencial = String.valueOf(detalleCompra.getSecRet1());
			String autorizacion = detalleCompra.getAutRet1();
			String fechaEmision = detalleCompra.getFechaEmiRet1();
			Object[] fila = new Object[]{serie,secuencial,autorizacion,fechaEmision};
			modeloTabla.addRow(fila);
		}
	}

	private void clean(){
		getTxtEstablecimiento().setText("");
		getTxtPuntoEmision().setText("");
		getTxtSecuencial().setText("");
		getTxtAutorizacion().setText("");
		getTxtFechaEmision().setText("");

		DefaultTableModel modeloTabla = (DefaultTableModel) getTblRetencionPopUp().getModel(); 
		for (int i=getTblRetencionPopUp().getRowCount()-1;i>=0;i--){
			modeloTabla.removeRow(i);
		}
	}

	private void initListener(){
		getTblRetencionPopUp().addMouseListener(tblDetalleAirMouseListener);
		getTblRetencionPopUp().addKeyListener(tblRetencionesClienteKeyListener);
	}

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
		if (getTblRetencionPopUp().getSelectedRow() != -1) {
			int selectedRow = getTblRetencionPopUp().getSelectedRow();
			filaSeleccionada =  getTblRetencionPopUp().convertRowIndexToModel(selectedRow); 
			if (filaSeleccionada == 0){
				if (detalleCompra.getEstabRet()!=null){
					getTxtEstablecimiento().setText(detalleCompra.getEstabRet());
					getTxtPuntoEmision().setText(detalleCompra.getPtoEmiRet());
					getTxtSecuencial().setText(String.valueOf(detalleCompra.getSecRet()));
					getTxtAutorizacion().setText(detalleCompra.getAutRet());
					getTxtFechaEmision().setText(detalleCompra.getFechaEmiRet());
				}
			}
			if (filaSeleccionada == 1){
				if (detalleCompra.getEstabRet1()!=null){
					getTxtEstablecimiento().setText(detalleCompra.getEstabRet1());
					getTxtPuntoEmision().setText(detalleCompra.getPtoEmiRet1());
					getTxtSecuencial().setText(String.valueOf(detalleCompra.getSecRet1()));
					getTxtAutorizacion().setText(detalleCompra.getAutRet1());
					getTxtFechaEmision().setText(detalleCompra.getFechaEmiRet1());
				}
			}
		}
	}


	//	Se implementa la accion de salir con ESC
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
