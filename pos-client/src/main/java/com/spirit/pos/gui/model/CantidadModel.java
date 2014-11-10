package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.helper.StockException;
import com.spirit.pos.gui.panel.JDCantidadProducto;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;

public class CantidadModel extends JDCantidadProducto{
	String cantidadDialog="1"; 
	boolean firsttyped=false;
	String idProducto="";
	Long idBodega=0L;
	boolean isGiftcard;
	String productoRegalo="N";

	//bodega, producto,cantidad
	//chequearStock
	public CantidadModel(Frame owner, String txtupcProducto,String idProducto,Long idBodega,boolean isGiftcard) {
		super(owner);
		this.idProducto=idProducto;
		this.idBodega=idBodega;
		this.isGiftcard = isGiftcard;
		initKeyListeners();
		setName("Cantidad Productos");	 

		addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				getTextField1().requestFocus();
			}
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});		
	}

	public void initKeyListeners(){
		getTextField1().setText("1");
		getTextField1().selectAll();
		getTextField1().addKeyListener(new TextChecker(4));	
		getTextField1().addKeyListener(new NumberTextField());
		getTextField1().addKeyListener(oKeyAdapterAnadirProducto);
	}

	KeyListener oKeyAdapterAnadirProducto = new KeyAdapter() { 
		public void keyTyped(KeyEvent e) {			
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {				
				cantidadDialog=getTextField1().getText();

				if(cantidadDialog.equals(""))
					cantidadDialog = "1";
				if (isGiftcard && Double.parseDouble(cantidadDialog) > 1) {
					SpiritAlert.createAlert("Cantidad permitida para giftcards es 1", SpiritAlert.WARNING);
					cantidadDialog = "1";
					getTextField1().setText("1");
				}
					
				try{
					ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(Long.parseLong(idProducto));
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
					if (generico.getUsaLote().equals("S"))
						SessionServiceLocator.getStockSessionService().chequearStock(new Long(idBodega), new Long(idProducto), new BigDecimal(cantidadDialog));
				}
				catch(StockException e2){
					SpiritAlert.createAlert(" " + e2.getMessage(), SpiritAlert.INFORMATION);
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if(getChkProductoRegalo().isSelected()) setProductoRegalo("S");
				if(!getChkProductoRegalo().isSelected()) setProductoRegalo("N");

				dispose();

			}
		}
	};

	public String getCantiSeleccionada() {
		return cantidadDialog;
	}

	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 
			cantidadDialog=getTextField1().getText();
		}
	};

	public String getCantidadDialog() {
		return cantidadDialog;
	}

	public void setCantidadDialog(String cantidadDialog) {
		this.cantidadDialog = cantidadDialog;
	}

	public String getProductoRegalo() {
		return productoRegalo;
	}

	public void setProductoRegalo(String productoRegalo) {
		this.productoRegalo = productoRegalo;
	}
}
