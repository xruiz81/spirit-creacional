package com.spirit.compras.gui.model;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraAsociadaGastoData;
import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.gui.panel.JDCompraAsociadaGasto;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.util.Utilitarios;

public class CompraAsociadaGastoModel extends JDCompraAsociadaGasto{

	private static final long serialVersionUID = 5292470173174763896L;

	private Map<Long,CompraIf> mapaCompras = new HashMap<Long,CompraIf>();
	private ArrayList<CompraAsociadaGastoIf> comprasAsociadas = null;
	private ArrayList<CompraAsociadaGastoIf> listaEliminacionComprasAsociadas = null;
	CompraAsociadaGastoIf compraAsociada = null;
	private CompraIf compraIf = null; 
	private CompraGastoIf compraGastoIf = null;
	private int filaSeleccionada = -1;
	
	final int AGREGAR = 0, ACTUALIZAR = 1; 
	
	public CompraAsociadaGastoModel(Frame owner,CompraGastoClase compraGastoClase,GastoIf gastoIf,CompraGastoIf compraGastoIf) {
		super(owner);
		
		this.comprasAsociadas = compraGastoClase.getMapaComprasAsociadas().get(gastoIf.getId()).getDetalle();
		this.listaEliminacionComprasAsociadas = compraGastoClase.getListaEliminacionComprassAsociadas();
		this.compraGastoIf = compraGastoIf;	
		
		iniciarComponentes();
		initListeners();
		cargarTabla();
		
		pack();
		centrarVentana();
		setModal(true);
		setVisible(true);
	}
	
	
	private void iniciarComponentes(){
		getBtnBuscarCompra().setText("");
		getBtnBuscarCompra().setToolTipText("Buscar producto");
		getBtnBuscarCompra().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
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
	
	private void initListeners(){
		getBtnAgregarDetalle().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				agregarCompraAsociada();
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actualizarCompraAsociada();
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eliminarCompraAsociada(filaSeleccionada);
			}			
		});
		
		getTblDetalleGasto().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				filaSeleccionada = seleccionarCompra();
			}
		});
		
		getTblDetalleGasto().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
				filaSeleccionada = seleccionarCompra();
			}
		});
		
		getBtnBuscarCompra().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CompraCriteria cc = new CompraCriteria(true);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cc, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					compraIf = (CompraIf)popupFinder.getElementoSeleccionado(); 
					getTxtCompra().setText(compraIf.getCodigo()+" - "+compraIf.getObservacion());
				}
				popupFinder = null;
			}
		});
	}
	
	private void agregarCompraAsociada(){
		if (validateField( AGREGAR)){
			CompraAsociadaGastoIf cdg = new CompraAsociadaGastoData();
			cdg.setCompraId(compraIf.getId());
			cdg.setCompraGastoId(compraGastoIf.getId());
			comprasAsociadas.add(cdg);
			clean();
			cargarTabla();
		}
	}
	
	private void actualizarCompraAsociada(){
		if (validateField(ACTUALIZAR)){
			compraAsociada.setCompraId(compraIf.getId());
			clean();
			cargarTabla();
		}
	}
	
	private void eliminarCompraAsociada(int filaSeleccionada) {
		if ( validateField(null) && filaSeleccionada >= 0 ){
			DefaultTableModel modelo = (DefaultTableModel) getTblDetalleGasto().getModel();
			filaSeleccionada = getTblDetalleGasto().convertRowIndexToModel(filaSeleccionada);
			CompraAsociadaGastoIf cdg = comprasAsociadas.get(filaSeleccionada);
			if ( cdg.getId() != null ){
				listaEliminacionComprasAsociadas.add(cdg);
			}
			comprasAsociadas.remove(cdg);
			clean();
			cargarTabla();
		} else {
			
		}
	}
	
	private int seleccionarCompra() {
		int filaSeleccionada = getTblDetalleGasto().getSelectedRow();
		if ( filaSeleccionada >= 0 ){
			filaSeleccionada = getTblDetalleGasto().convertRowIndexToModel(filaSeleccionada);
			compraAsociada = comprasAsociadas.get(filaSeleccionada);
			try {
				compraIf = verificarMapaCompras(compraAsociada.getCompraId());
				getTxtCompra().setText(compraIf.getCodigo()+" - "+compraIf.getObservacion());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				return filaSeleccionada;
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al seleccionar compra !!", SpiritAlert.ERROR);
				return filaSeleccionada;
			}
		}
		return filaSeleccionada;
	}
	
	private boolean validateField(Integer operacion){
		if ( compraIf == null ){
			SpiritAlert.createAlert("Debe selecionar una compra !!", SpiritAlert.WARNING);
			getBtnBuscarCompra().grabFocus();
			return false;
		}
		if ( operacion != null && (operacion == AGREGAR || operacion == ACTUALIZAR) ){
			for ( CompraAsociadaGastoIf cag: comprasAsociadas ){
				if ( operacion == AGREGAR ){
					if ( compraIf.getId().equals(cag.getCompraId()) ){
						SpiritAlert.createAlert("Compra seleccionada ya esta en lista !!", SpiritAlert.WARNING);
						return false;
					}
				} else if ( operacion == ACTUALIZAR ){
					Long compraIdSeleccionada = compraAsociada.getCompraId();
					if ( compraIf.getId().equals(cag.getCompraId()) && !compraIdSeleccionada.equals(compraIf.getId())  ){
						SpiritAlert.createAlert("Compra seleccionada ya esta en lista !!", SpiritAlert.WARNING);
						return false;
					}
				}
				
			}
		}
		return true;
	}
	
	private void cargarTabla(){
		limpiarTabla(getTblDetalleGasto());
		try{
			DefaultTableModel modelo = (DefaultTableModel)getTblDetalleGasto().getModel();
			for ( CompraAsociadaGastoIf cag : comprasAsociadas ){
				CompraIf compra = verificarMapaCompras(cag.getCompraId());
				Double total = Utilitarios.redondeoValor(compra.getValor().doubleValue()+compra.getIva().doubleValue()-compra.getDescuento().doubleValue());
				Object[] fila = new Object[]{
					compra.getCodigo(),compra.getPreimpreso()!=null?compra.getPreimpreso():"",
					total,compra.getObservacion()};
				modelo.addRow(fila);
			}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar la tabla !!", SpiritAlert.ERROR);
		}
	}
	
	private void clean(){
		getTxtCompra().setText("");
		compraIf = null;
		
	}
	
	private CompraIf verificarMapaCompras(Long compraId) throws GenericBusinessException {
		try{
			CompraIf compra = null;
			if ( !mapaCompras.containsKey(compraId) ){
				compra = SessionServiceLocator.getCompraSessionService().getCompra(compraId);
				mapaCompras.put(compra.getId(), compra);
			} else {
				compra = mapaCompras.get(compraId);
			}
			return compra;
		} catch(Exception e){
			throw new GenericBusinessException("Error en la consulta de compras !!");
		}
	}
	
	private void centrarVentana(){
		setLocation(
	    		(Toolkit.getDefaultToolkit().getScreenSize().width/4), //- getWidth()) / 6, 
	    		(Toolkit.getDefaultToolkit().getScreenSize().height ) / 7
	    		);
		
		int maxAncho = (Toolkit.getDefaultToolkit().getScreenSize().width * 2 ) / 4;
		int maxAlto = (Toolkit.getDefaultToolkit().getScreenSize().height * 4 ) / 7;
		setMaximumSize(new Dimension(maxAncho,maxAlto));
	}
	
	
}
