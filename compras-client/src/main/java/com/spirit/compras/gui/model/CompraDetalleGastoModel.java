package com.spirit.compras.gui.model;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.spirit.client.NumberCellRenderer;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraDetalleGastoData;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.gastos.CompraDetalleGastoClase;
import com.spirit.compras.gui.panel.JDCompraDetalleGasto;
import com.spirit.compras.gui.util.TextFieldCellEditor;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.LineaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CompraDetalleGastoModel extends JDCompraDetalleGasto{

	private static final long serialVersionUID = -3505926410382166257L;
	
	public static final int COLUMNA_DETALLE_NOMBRE_PRODUCTO = 0;
	final int COLUMNA_DETALLE_GASTO_VALOR = 1;
	
	private final int MAX_LONGITUD_VALOR = 6;
	
	TextFieldCellEditor tfce = new TextFieldCellEditor(TextFieldCellEditor.DETALLE,TextFieldCellEditor.NUMERICO,10);
	
	DecimalFormat dosDecimales = new DecimalFormat("'$ '######0.00");
	GastoIf gastoIf = null;
	CompraGastoIf compraGasto = null;
	Map<Long,CompraDetalleGastoClase> mapaDetallesGastos = null;
	List<CompraDetalleIf> comprasDetalles = null;
	Map<Long,String> mapaProductoNombre = null;
	
	//mapa para relacionar compraDetalle y GastoDetalle
	Map<CompraDetalleIf,CompraDetalleGastoIf> mapaDetalleGasto = null;
	
	//mapa para relacionar la fial con la compraDetalle
	Map<Integer, CompraDetalleIf> mapaFilaCompraDetalle = new HashMap<Integer, CompraDetalleIf>();
	Vector valoresVector = new Vector();
	

	private Map<Long,GenericoIf> genericosMap = new HashMap<Long,GenericoIf>();
	private Map<Long,ProductoIf> productosMap = new HashMap<Long,ProductoIf>();
	private Map<Long,PresentacionIf> presentacionesMap = new HashMap<Long,PresentacionIf>();
	private Map<Long,ModeloIf> modelosMap = new HashMap<Long,ModeloIf>();
	private Map<Long,LineaIf> lineasMap = new HashMap<Long,LineaIf>();
	private Map<Long,ColorIf> coloresMap = new HashMap<Long,ColorIf>();
	
	public CompraDetalleGastoModel(Frame owner,GastoIf gastoIf,
			CompraGastoIf compraGasto,
			Map<Long,CompraDetalleGastoClase>  mapaDetallesGastos, 
			List<CompraDetalleIf> comprasDetalles,
			Map<Long,String> mapaProductoNombre ,
			Map<Long,GenericoIf> genericosMap,Map<Long,ProductoIf> productosMap,
			Map<Long,PresentacionIf> presentacionesMap,Map<Long,LineaIf> lineasMap,
			Map<Long,ModeloIf> modelosMap,Map<Long,ColorIf> coloresMap	) {
		
		super(owner);
		
		this.gastoIf = gastoIf;
		this.compraGasto = compraGasto;
		this.mapaDetallesGastos = mapaDetallesGastos;
		this.comprasDetalles = comprasDetalles;
		this.mapaProductoNombre = mapaProductoNombre;
		
		this.genericosMap = genericosMap;
		this.productosMap = productosMap;
		this.presentacionesMap = presentacionesMap;
		this.lineasMap = lineasMap;
		this.modelosMap = modelosMap;
		this.coloresMap = coloresMap;
		
		CompraDetalleGastoClase comprasDetalleClase = mapaDetallesGastos.get(gastoIf.getId());
		mapaDetalleGasto = comprasDetalleClase.getDetalle();
		tfce.setMapaCompraDetalleGasto(mapaDetalleGasto);
		tfce.setMapaProductoNombre(mapaProductoNombre);
		
		iniciarComponentes();
		initListeners();
		cargarTabla();
		
		pack();
		centrarVentana();
		setModal(true);
		setVisible(true);
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
		
	
	private void iniciarComponentes(){
		getLblGasto().setText(this.gastoIf.getNombre());
		Double totalD = compraGasto.getValor().doubleValue();
		String total = dosDecimales.format( totalD );
		getLblTotal().setText(total);
		getTxtValor().setText(totalD.toString());
		
		NumberCellRenderer ncr = new NumberCellRenderer("######0.000000",NumberCellRenderer.DERECHA);
		getTblDetalleGasto().getColumnModel().getColumn(COLUMNA_DETALLE_GASTO_VALOR).setCellRenderer(ncr);
		
		TableColumnModel tableModelo = getTblDetalleGasto().getColumnModel();
		//TableColumn anchoColumna = tableModelo.getColumn(0);
		//anchoColumna.setPreferredWidth(40);
		TableColumn anchoColumna = tableModelo.getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna.setMaxWidth(60);
	}
	
	private void initListeners(){
		
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		
		getBtnAplicarTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				aplicarTodos();
			}
		});
		
		getBtnProrratear().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				prorratear();
			}
		});
		
		getBtnGuardar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		
		getBtnCancelar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void cargarTabla(){
		valoresVector.clear();
		limpiarTabla(getTblDetalleGasto());
		DefaultTableModel modelo = (DefaultTableModel)getTblDetalleGasto().getModel();
		int i = 0;
		//Se cargan los que existen  
		for ( CompraDetalleIf cd : mapaDetalleGasto.keySet() ){
			CompraDetalleGastoIf cdg = mapaDetalleGasto.get(cd); 
			//CompraDetalleIf cd = comprasDetalles.get(i);
			agregarFila(modelo, cd, cdg);
			mapaFilaCompraDetalle.put(i, cd);
			valoresVector.add(i,cdg.getValor().doubleValue());
			i++;
		}
		for (CompraDetalleIf cd : comprasDetalles){
			if ( !contieneCompraDetalle(mapaDetalleGasto, cd.getId()) && !mapaDetalleGasto.containsKey(cd) ){
				CompraDetalleGastoIf cdg = new CompraDetalleGastoData();
				cdg.setValor(BigDecimal.ZERO);
				mapaDetalleGasto.put(cd,cdg);
				mapaFilaCompraDetalle.put(i, cd);
				agregarFila(modelo, cd, cdg);
				valoresVector.add(i,0D);
				i++;
			}
		}
	}

	private boolean contieneCompraDetalle(Map<CompraDetalleIf,CompraDetalleGastoIf> mapaDetalleGasto,Long compraDetalleId){
		if ( compraDetalleId != null )
			for ( CompraDetalleIf cd : mapaDetalleGasto.keySet() ){
				if (compraDetalleId.equals(cd.getId())){
					return true;
				}
			}
		return false;
	}
	
	private void agregarFila(DefaultTableModel modelo, CompraDetalleIf cd, CompraDetalleGastoIf cdg) {
		//String nombreProducto = mapaProductoNombre.get(cd.getProductoId());
		ProductoIf productoIf = productosMap.get(cd.getProductoId());
		String descripcion = "";
		try {
			descripcion = CompraModel.getDescripcionProducto(genericosMap, presentacionesMap, lineasMap, modelosMap, coloresMap, productoIf);
		} catch (GenericBusinessException e) {
			System.out.println("--ERROR : "+e.getMessage());
			//e.printStackTrace();
		}
		BigDecimal valor = cdg.getValor();
		Double valorDecimal = valor.doubleValue();
		//Object[] fila = new Object[]{nombreProducto,valorDecimal};
		Object[] fila = new Object[]{descripcion,valorDecimal};
		modelo.addRow(fila);
	}
	
	private double redondeoValor(double valor){
		double valorR = 0.0;
		Double decimales = 6D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
	private void aplicarTodos(){
		//DefaultTableModel modelo = (DefaultTableModel) getTblDetalleGasto().getModel();
		if ( validateField() ){
			double valor = Double.parseDouble( getTxtValor().getText() );
			//valor = Utilitarios.redondeoValor(valor);
			for (int i = 0 ; i < getTblDetalleGasto().getRowCount() ; i++ ){
				valoresVector.add(i, valor);
				getTblDetalleGasto().setValueAt(valor, i, COLUMNA_DETALLE_GASTO_VALOR); 
			}
		}
	}
	
	private void prorratear(){
		//DefaultTableModel modelo = (DefaultTableModel) getTblDetalleGasto().getModel();
		
		long sumatoriaCantidadesDetalle = sumarCantidadesDetalleCompra(mapaFilaCompraDetalle);
		if ( validateField() ){
			//double valor = Double.parseDouble( getTxtValor().getText() )/getTblDetalleGasto().getRowCount();
			//valor = Utilitarios.redondeoValor(valor);
			double valorGasto = Double.parseDouble( getTxtValor().getText() );
			for (int i = 0 ; i < getTblDetalleGasto().getRowCount() ; i++ ){
				CompraDetalleIf cd = mapaFilaCompraDetalle.get(i);
				double valor = valorGasto * ( (double)(cd.getCantidad()) / ((double)(sumatoriaCantidadesDetalle)) );
				valoresVector.add(i, valor);
				getTblDetalleGasto().setValueAt(valor, i, COLUMNA_DETALLE_GASTO_VALOR);
			}
		}
	}
	
	private long sumarCantidadesDetalleCompra(Map<Integer, CompraDetalleIf> mapaFilaCompraDetalle){
		long totalCantidades = 0L;
		for ( Iterator<Integer> itMapa = mapaFilaCompraDetalle.keySet().iterator() ; itMapa.hasNext() ; ){
			Integer fila = itMapa.next();
			CompraDetalleIf cd = mapaFilaCompraDetalle.get(fila);
			totalCantidades += cd.getCantidad();
		}
		return totalCantidades;
	}

	private boolean validateField(){

		String valor = getTxtValor().getText();
		if ( valor== null || "".equals(valor) ){
			SpiritAlert.createAlert("Debe establecer un valor para producto !!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}

		return true;
	}
	
	private boolean verificarTotalValores(){
		Double sumaValores = 0.0;
		for ( int i = 0 ; i < getTblDetalleGasto().getRowCount() ; i++ ){
			//Double valor = (Double) getTblDetalleGasto().getValueAt(i, COLUMNA_DETALLE_GASTO_VALOR);
			//valor = Utilitarios.redondeoValor(valor);
			Double valor = (Double) valoresVector.get(i);
			sumaValores += valor;
		}
		//sumaValores = redondeoValor(sumaValores);
		Double total = compraGasto.getValor().doubleValue();
		if ( Utilitarios.redondeoValor(sumaValores) != Utilitarios.redondeoValor(total) ){
			SpiritAlert.createAlert("El total detallado tiene que ser igual al valor TOTAL del Gasto !!", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}
	
	private void guardar(){
		if (verificarTotalValores()){
			int numeroFilas = getTblDetalleGasto().getRowCount();
			for ( int i = 0; i < numeroFilas ; i++ ){
				CompraDetalleIf cd = mapaFilaCompraDetalle.get(i);
				CompraDetalleGastoIf cdg = mapaDetalleGasto.get(cd);
				//Double valor = (Double) getTblDetalleGasto().getValueAt(i, COLUMNA_DETALLE_GASTO_VALOR);
				Double valor = (Double) valoresVector.get(i);
				valor = redondeoValor(valor);
				cdg.setValor(new BigDecimal(valor));
			}
			dispose();
		}
	}
	
	
}
