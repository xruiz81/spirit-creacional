package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.ColorData;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloData;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionData;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoData;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.entity.FacturastipoproductodetalleIf;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.gui.panel.JPVentasTipoProducto;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;



public class VentasTipoProductoModel extends JPVentasTipoProducto {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "SALDO ACTUAL CLIENTE"; 
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	ClienteIf operadorNegocio = null;

	BigDecimal totalVentasBrutas;
	BigDecimal totalDevoluciones;
	BigDecimal totalVentasNetas;

	int numVendidos=0;
	int numDevueltos=0;
	
	java.sql.Date fechaInicial;
	java.sql.Date fechaFinal;
	boolean isTotal = false;

	Map productosMap = new HashMap();
	Map genericosMap = new HashMap();

	public VentasTipoProductoModel() {
		getTblVentas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponentPOS(this);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblVentas().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(28);
		anchoColumna = getTblVentas().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentas().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentas().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentas().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentas().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentas().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblVentas().getColumnModel().getColumn(7);	
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblVentas().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(80);
		
	}

	private void initListeners(){


		cargarComboColor();
		cargarComboPresentacionId();
		cargarComboModelo();		
		cargarComboTipoProducto();



		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				llenar_datos();
				//cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		});

		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);

		getBtnResetearFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				getCmbFechaFinal().setSelectedItem(null);
				getCmbFechaFinal().validate();
				getCmbFechaFinal().repaint();
			}
		});


	}



	private void cargarComboPresentacionId(){
		try {
			List presentacionesId = (List)SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa());
			PresentacionData tallaTodos = new PresentacionData();
			tallaTodos.setNombre("TODOS");	
			tallaTodos.setCodigo(" - ");
			tallaTodos.setId(null);
			presentacionesId.add(0,tallaTodos);
			refreshCombo(getCmbTalla(), presentacionesId);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboModelo(){
		try {
			List modelos = (List)SessionServiceLocator.getModeloSessionService().getModeloList();
			ModeloData modeloTodos = new ModeloData();
			modeloTodos.setNombre("TODOS");			
			modeloTodos.setId(null);
			modelos.add(0,modeloTodos);
			refreshCombo(getCmbModelo(), modelos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboColor(){
		try {
			List colores = (List)SessionServiceLocator.getColorSessionService().getColorList();
			ColorData colorTodos = new ColorData();
			colorTodos.setNombre("TODOS");			
			colorTodos.setId(null);
			colores.add(0,colorTodos);
			refreshCombo(getCmbColor(), colores);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboTipoProducto(){
		try {
			List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
			TipoProductoData tipoTodos = new TipoProductoData();
			tipoTodos.setNombre("TODOS");
			tipoTodos.setCodigo("  ");
			tipoTodos.setId(null);
			tiposProducto.add(0,tipoTodos);

			refreshCombo(getCmbTipoProducto(), tiposProducto);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}


	public void report() {
		try {				
			if (getTblVentas().getModel().getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de ventas por tipo de producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPVentasTipoProductoDetalles.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MOVIMIENTO_CARTERA).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);
					String fini="";
					if(fechaInicial!=null) fini=fechaInicial.toString();					
					parametrosMap.put("fechaIni",fini);					
					String ffin="";
					if(fechaFinal!=null) ffin=fechaFinal.toString();					
					parametrosMap.put("fechaFin", ffin);	
					
					System.out.println(getTxtNumDevueltos().getText()+"HEIP<"+getTxtNumVendidos().getText());
				 	parametrosMap.put("CANTVEND", getTxtNumVendidos().getText().toString());
					parametrosMap.put("CANTDEV", getTxtNumDevueltos().getText().toString());

					
					
					
					parametrosMap.put("totalVentas", new Double(getTxtTotalVentas().getText().toString().replace(",","")));
					//parametrosMap.put("totalDscto", new Double(getTxtTotalDescuentos().getText().toString().replace(",","")));
					parametrosMap.put("totalDev", new Double(getTxtTotalDev().getText().toString().replace(",","")));
					parametrosMap.put("totalFinal", new Double(getTxtTotal().getText().toString().replace(",","")));
 
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblVentas().getModel(), true);
							
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void refresh() {

	}

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {	

		totalVentasBrutas = BigDecimal.ZERO;
	 
		totalDevoluciones = BigDecimal.ZERO;
		totalVentasNetas = BigDecimal.ZERO;
		 numVendidos=0;
		 numDevueltos=0;
		getTxtTotalVentas().setText("");
		cleanTable();
	}

	public void cleanTable() {
		//getMovimientoCarteraColeccion().clear();
		DefaultTableModel model = (DefaultTableModel) getTblVentas().getModel();
		for(int i= this.getTblVentas().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	private Map mapearProductos() {
		Map productosMap = new HashMap();
		Map parameterMap = new HashMap();
		parameterMap.put("estado", "A");
		parameterMap.put("permiteventa", "S");
		try {
			Iterator it = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaIdAndByQuery(Parametros.getIdEmpresa(), parameterMap).iterator();
			while (it.hasNext()) {
				ProductoIf producto = (ProductoIf) it.next();
				productosMap.put(producto.getId(), producto);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return productosMap;
	}
	
	private Map mapearGenericos() {
		Map genericosMap = new HashMap();
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("estado", "A");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			Iterator it = SessionServiceLocator.getGenericoSessionService().findGenericoByQuery(parameterMap).iterator();
			while (it.hasNext()) {
				GenericoIf generico = (GenericoIf) it.next();
				genericosMap.put(generico.getId(), generico);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return genericosMap;
	}
	
	public void llenar_datos(){
		productosMap = mapearProductos();
		genericosMap = mapearGenericos();
		totalVentasBrutas = BigDecimal.ZERO;
		totalDevoluciones = BigDecimal.ZERO;
		totalVentasNetas = BigDecimal.ZERO;					
		Long modeloId,colorId,tallaId,tipoProductoId=0L;
		//MODELO,COLOR,TALLA,TIPOPRODUCTO
		ModeloIf modelo = ((ModeloIf) this.getCmbModelo().getSelectedItem());
		PresentacionIf talla = ((PresentacionIf) this.getCmbTalla().getSelectedItem());
		ColorIf color = ((ColorIf) this.getCmbColor().getSelectedItem());
		TipoProductoIf tipoProducto = ((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem());
		//FECHA INI
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		fechaInicial = null;	 
		if (fecha != null)	fechaInicial = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());
		//FECHA FIN
		fecha = (Date) getCmbFechaFinal().getDate();
		fechaFinal = null;
		if (fecha != null)		fechaFinal = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());

		VentasPagosIf vt;
		try {
			Iterator ventasPagosIterator;
			ArrayList ventaspagosArray = new ArrayList();

			if(modelo.getNombre().equals("TODOS")) modeloId=null; 
			else modeloId=modelo.getId();
			if(color.getNombre().equals("TODOS")) colorId=null;
			else colorId=color.getId();
			if(talla.getNombre().equals("TODOS")) tallaId=null; 
			else tallaId=talla.getId();
			if(tipoProducto.getNombre().equals("TODOS")) tipoProductoId=null; 
			else tipoProductoId=tipoProducto.getId();			

			ventaspagosArray = (ArrayList)SessionServiceLocator.getFacturaSessionService().consultaFacturasCantidadProducto((fechaInicial != null)?new java.sql.Timestamp(fechaInicial.getTime()):null, (fechaFinal != null)?new java.sql.Timestamp(fechaFinal.getTime()):null,colorId,modeloId,tallaId,tipoProductoId);

			numVendidos=0;
			numDevueltos=0;
			
			if(ventaspagosArray.size()>0){
				ventasPagosIterator = ventaspagosArray.iterator();
				int numero = 1;
				while (ventasPagosIterator.hasNext()) {
					FacturastipoproductodetalleIf facturastipoproductoIf = (FacturastipoproductodetalleIf) ventasPagosIterator.next();					 	 
					tableModel = (DefaultTableModel) getTblVentas().getModel();
					if (agregarDetalles(numero,facturastipoproductoIf))	numero++;		
					 
				}
			}

			getTblVentas().validate();
			getTblVentas().repaint();
			getTxtTotalVentas().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalVentasBrutas.doubleValue())));
			getTxtTotalDev().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDevoluciones.doubleValue())));
			totalVentasNetas=totalVentasBrutas.subtract(totalDevoluciones);
			getTxtTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalVentasNetas.doubleValue())));
			getTxtNumDevueltos().setText(new Integer(numDevueltos).toString());
			getTxtNumVendidos().setText(new Integer(numVendidos).toString());			

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	/*sum(fd.cantidad)-sum(fd.cantidad_devuelta) as cant,ok
	sum(fd.cantidad_devuelta) as dev,ok
	(sum(fd.cantidad)-2*sum(fd.cantidad_devuelta)) as cantFinal,---borrar!
	valuni.precio as preciouni,ok
	((sum(fd.cantidad)-sum(fd.cantidad_devuelta))*valuni.precio) as valorsinivaVentas,--borrar!
	SUM(fd.descuento) as descuentoVentas, ok
	(((sum(fd.cantidad)-sum(fd.cantidad_devuelta))*valuni.precio)-SUM(fd.descuento))*0.12 as ivaVentas,-- borrar!
	(valorsinviva + ivaventas -SUM(fd.descuento) as totalVentas,--borrar!
	((sum(fd.cantidad)-sum(fd.cantidad_devuelta))*valuni.precio) +((((sum(fd.cantidad)-sum(fd.cantidad_devuelta))*valuni.precio)-SUM(fd.descuento))*0.12)-SUM(fd.descuento) as totalVentas,--borrar!	
	(sum(fd.cantidad_devuelta)*valuni.precio) as valorsinivaDev,
	(sum(fd.cantidad_devuelta)*valuni.precio)*0.12 as ivaDev,
	(sum(fd.cantidad_devuelta)*valuni.precio)+(sum(fd.cantidad_devuelta)*valuni.precio)*0.12 as totalDev,
	m.id as modelo_id,co.id as color_id,tp.id as tipoProducto,pre.id as talla_id*/
	
	private boolean agregarDetalles(int numero,FacturastipoproductodetalleIf facturastipoproductoIf) {
		Vector<Object> fila = new Vector<Object>();
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		fila.add(facturastipoproductoIf.getModelo());
		fila.add(facturastipoproductoIf.getColor());
		fila.add(facturastipoproductoIf.getTalla());		
		fila.add(facturastipoproductoIf.getTipo());
		String fecha=facturastipoproductoIf.getFecha().toString();
		fecha=fecha.substring(0,10);
		fila.add(fecha);
		fila.add(facturastipoproductoIf.getCant().toString());
		
		numVendidos+=facturastipoproductoIf.getCant().intValue();
		numDevueltos+=facturastipoproductoIf.getDev().intValue();
		
		ProductoIf producto = (ProductoIf) productosMap.get(facturastipoproductoIf.getProducto());
		GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
		
		///////VALOR SIN IVA VENTAS
		BigDecimal valorSinIvaVentas = (facturastipoproductoIf.getCant().compareTo(BigDecimal.ZERO) != 0)?facturastipoproductoIf.getValorsinivaventas():BigDecimal.ZERO;
		facturastipoproductoIf.setValorsinivaventas(valorSinIvaVentas);
        //----------fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getValorsinivaventas().doubleValue())));
		fila.add(String.valueOf(facturastipoproductoIf.getValorsinivaventas().doubleValue()));
		///////DESCUENTO VENTAS
        BigDecimal descuentoVentas = (facturastipoproductoIf.getCant().compareTo(BigDecimal.ZERO) != 0)?facturastipoproductoIf.getDescuentoventas():BigDecimal.ZERO;
        facturastipoproductoIf.setDescuentoventas(descuentoVentas);
		//----------fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getDescuentoventas().doubleValue())));
        fila.add(String.valueOf(facturastipoproductoIf.getDescuentoventas().doubleValue()));
		///////IVA VENTAS
		BigDecimal ivaVentas=(facturastipoproductoIf.getCant().compareTo(BigDecimal.ZERO) != 0 && generico.getCobraIva().equals("S"))?facturastipoproductoIf.getIvaventas():BigDecimal.ZERO;
		BigDecimal iva = new BigDecimal(facturastipoproductoIf.getIvaventas().doubleValue());
		facturastipoproductoIf.setIvaventas(ivaVentas);
		//----------fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getIvaventas().doubleValue())));
		fila.add(String.valueOf(facturastipoproductoIf.getIvaventas().doubleValue()));
		///////TOTAL VENTAS		
		BigDecimal totalVentas=(facturastipoproductoIf.getCant().compareTo(BigDecimal.ZERO) != 0)?facturastipoproductoIf.getTotalventas():BigDecimal.ZERO;
		if (facturastipoproductoIf.getCant().compareTo(BigDecimal.ZERO) != 0 && !generico.getCobraIva().equals("S"))
			totalVentas = totalVentas.subtract(iva);
		facturastipoproductoIf.setTotalventas(totalVentas);
		//----------fila.add("<html><b>" +formatoDecimal.format(facturastipoproductoIf.getTotalventas().doubleValue())+ "</b></html>");
		fila.add("<html><b>" +String.valueOf(facturastipoproductoIf.getTotalventas().doubleValue())+ "</b></html>");
		fila.add(facturastipoproductoIf.getDev().toString());
		///////VALOR SIN IVA DEV		
		BigDecimal valorsinIvaDev=(facturastipoproductoIf.getDev().compareTo(BigDecimal.ZERO) != 0)?facturastipoproductoIf.getValorsinivadev():BigDecimal.ZERO;	     
        facturastipoproductoIf.setValorsinivadev(valorsinIvaDev);
		//----------fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getValorsinivadev().doubleValue())));
        fila.add(String.valueOf(facturastipoproductoIf.getValorsinivadev().doubleValue()));
		///////IVA DEVOLUCION		
		BigDecimal ivaDev=(facturastipoproductoIf.getDev().compareTo(BigDecimal.ZERO) != 0 && generico.getCobraIva().equals("S"))?facturastipoproductoIf.getIvadev():BigDecimal.ZERO;
		iva = new BigDecimal(facturastipoproductoIf.getIvadev().doubleValue());
		facturastipoproductoIf.setIvadev(ivaDev);
		//----------fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getIvadev().doubleValue())));
		fila.add(String.valueOf(facturastipoproductoIf.getIvadev().doubleValue()));
		///////TOTAL DEVOLUCION		
		BigDecimal totalDev=(facturastipoproductoIf.getDev().compareTo(BigDecimal.ZERO) != 0)?facturastipoproductoIf.getTotaldev():BigDecimal.ZERO;
		if (facturastipoproductoIf.getDev().compareTo(BigDecimal.ZERO) != 0 && !generico.getCobraIva().equals("S"))
			totalDev = totalDev.subtract(iva);
		facturastipoproductoIf.setTotaldev(totalDev);
		fila.add("<html><b>" +String.valueOf(facturastipoproductoIf.getTotaldev().doubleValue())+ "</b></html>");
	 	/*totalVentasBrutas = totalVentasBrutas.add(new BigDecimal(Utilitarios.redondeoValor(facturastipoproductoIf.getTotalventas().doubleValue())));
		totalDevoluciones = totalDevoluciones.add(new BigDecimal(Utilitarios.redondeoValor(facturastipoproductoIf.getTotaldev().doubleValue())));*/
		totalVentasBrutas = totalVentasBrutas.add(new BigDecimal(facturastipoproductoIf.getTotalventas().doubleValue()));
		totalDevoluciones = totalDevoluciones.add(new BigDecimal(facturastipoproductoIf.getTotaldev().doubleValue()));
		tableModel.addRow(fila);		

		fila = new Vector<Object>();
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");	
		fila.add("");
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");	
		fila.add("");
		fila.add("");
		tableModel.addRow(fila);

		return true;
	}

	/*
	private boolean agregarDetalles(int numero,FacturastipoproductodetalleIf facturastipoproductoIf)
 	{
		Vector<Object> fila = new Vector<Object>();
		double totalDebitosMovimiento = 0D;
		double totalCreditosMovimiento = 0D;
		double saldoMovimiento = 0D;
		Long idDev=0L;
		Long idFactura=0L;
		Long idNotaVenta=0L;
		TipoDocumentoIf tipoDocumento = null;
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		fila.add(facturastipoproductoIf.getModelo());
		fila.add(facturastipoproductoIf.getColor());
		fila.add(facturastipoproductoIf.getTalla());		
		fila.add(facturastipoproductoIf.getTipo());
		String fecha=facturastipoproductoIf.getFecha().toString();
		fecha=fecha.substring(0,10);
		fila.add(fecha);
		fila.add(facturastipoproductoIf.getCant().toString());
		
		numVendidos+=facturastipoproductoIf.getCant().intValue();
		numDevueltos+=facturastipoproductoIf.getDev().intValue();
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getValorsinivaventas().doubleValue())));
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getDescuentoventas().doubleValue())));
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getIvaventas().doubleValue())));
	 	
		fila.add("<html><b>" +formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getTotalventas().doubleValue()))+ "</b></html>");
		fila.add(facturastipoproductoIf.getDev().toString());
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getValorsinivadev().doubleValue())));
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getIvadev().doubleValue())));
		fila.add("<html><b>" +formatoDecimal.format(Utilitarios.redondeoValor(facturastipoproductoIf.getTotaldev().doubleValue()))+ "</b></html>");

	 	totalVentasBrutas =totalVentasBrutas.add(new BigDecimal(Utilitarios.redondeoValor(facturastipoproductoIf.getTotalventas().doubleValue())));
		totalDevoluciones =totalDevoluciones.add(new BigDecimal(Utilitarios.redondeoValor(facturastipoproductoIf.getTotaldev().doubleValue())));
		 
		tableModel.addRow(fila);		

		fila = new Vector<Object>();
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");	
		fila.add("");
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");	
		fila.add("");
		fila.add("");
		tableModel.addRow(fila);

		return true;
	}*/


	private TipoDocumentoIf findTipoDocumentoByCodigo(String codigo) throws GenericBusinessException {
		TipoDocumentoIf tipoDocumento = null;
		Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(codigo).iterator();
		if (it.hasNext())
			tipoDocumento = (TipoDocumentoIf) it.next();
		return tipoDocumento;
	}

	private String findClienteByOficina(Long idOficina) throws GenericBusinessException {
		ClienteIf tipoDocumento = null;
		String nombreCliente="";
		Iterator it = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(idOficina).iterator();
		if (it.hasNext())
		{tipoDocumento = (ClienteIf) it.next();
		nombreCliente=tipoDocumento.getNombreLegal();			 
		}
		return nombreCliente;
	}

	private String findClienteById(Long id) throws GenericBusinessException {
		ClienteIf tipoDocumento = null;
		String nombreFundacion="";
		Iterator it = SessionServiceLocator.getClienteSessionService().findClienteById(id).iterator();
		if (it.hasNext())
		{tipoDocumento = (ClienteIf) it.next();
		nombreFundacion=tipoDocumento.getNombreLegal();			 
		}
		return nombreFundacion;
	}


	private Long findTipoPagoIf(String codigo) throws GenericBusinessException {
		TipoPagoIf tipoPago = null;
		Long idtipoPago=0L;
		Iterator it = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo("DO").iterator();
		if (it.hasNext())
		{tipoPago = (TipoPagoIf) it.next();
		idtipoPago=tipoPago.getId();
		}


		return idtipoPago;
	}





	public String nombreVendedor(Long idVendedor){
		String nombre="SIN VENDEDOR";		
		Iterator vendedorIt;
		try {
			vendedorIt=SessionServiceLocator.getEmpleadoSessionService().findEmpleadoById(idVendedor).iterator();

			if(vendedorIt.hasNext()){
				EmpleadoIf empleado_vendedor = (EmpleadoIf)vendedorIt.next();	
				nombre=empleado_vendedor.getNombres()+" "+empleado_vendedor.getApellidos();
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nombre;
	}



	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color whiteColor = new Color(255, 255, 255);
		private Color grayColor = new Color(204, 204, 204);
		private Color blackColor = new Color(0, 0, 0);

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			c.setForeground(blackColor);

			if(column == 0)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 1)
				setHorizontalAlignment(JLabel.CENTER);        
			if(column == 2)
				setHorizontalAlignment(JLabel.CENTER);
			if(column == 3)
				setHorizontalAlignment(JLabel.LEFT);
			if(column == 4)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 5)
				setHorizontalAlignment(JLabel.RIGHT);

			if(((String) value).equals("<html><b>T O T A L E S :</b></html>") || ((String) value).equals("<html><b>S A L D O :</b></html>")) {
				c.setBackground(grayColor);
				setHorizontalAlignment(JLabel.RIGHT);
			} else {
				c.setBackground(whiteColor);
			}

			String detalle = (String) table.getValueAt(row, 2);
			if ((column >= 0 && column <= 4) && (detalle.equals("<html><b>T O T A L E S :</b></html>") || detalle.equals("<html><b>S A L D O :</b></html>"))) {
				c.setBackground(grayColor);
			}

			return c;
		}
	};


	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
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

	@Override
	public void save() {
		// TODO Auto-generated method stub

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


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public void showFindMode() {
		// TODO Auto-generated method stub

	}


	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}


	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}
}