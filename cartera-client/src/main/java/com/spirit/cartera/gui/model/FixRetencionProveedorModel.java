package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.gui.panel.JPFixRetencionProveedor;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.comun.util.Retencion;
import com.spirit.comun.util.RetencionProveedorData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class FixRetencionProveedorModel extends JPFixRetencionProveedor {
	
	private static final long serialVersionUID = -602912413049272638L;
	
	private int selectedRowTblCompras = -1;
	private int selectedRowTblRetenciones = -1;
	private ArrayList<CompraIf> comprasColeccion = new ArrayList<CompraIf>();
	ArrayList<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
	ArrayList<CarteraAfectaIf> carteraAfectaColeccion = new ArrayList<CarteraAfectaIf>();
	private CompraIf compra;
	private ArrayList< ArrayList<String> > listaTablaFilasRenta = new ArrayList< ArrayList<String> >();
	private ArrayList< ArrayList<String> > listaTablaFilasIva = new ArrayList< ArrayList<String> >();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoSerie = new DecimalFormat("000");
	private DecimalFormat formatoSecuencial = new DecimalFormat("0000000");
	private DecimalFormat formatoAutorizacion = new DecimalFormat("0000000000");
	private static final int MAX_LONGITUD_ESTABLECIMIENTO = 3;
	private static final int MAX_LONGITUD_PUNTO_EMISION = 3;
	private static final int MAX_LONGITUD_SECUENCIAL = 7;
	private static final int MAX_LONGITUD_AUTORIZACION_RETENCION = 10;
	private Map<Long,ArrayList<Retencion>> retencionesMap = new HashMap<Long,ArrayList<Retencion>>();
	private Map fechasRetencionesMap = new HashMap();
	private static final String PERSONA_NATURAL = "N";
	
	private StringBuilder numeroEstablecimiento = null;
	private StringBuilder numeroPuntoEmision = null;
	private StringBuilder numeroAutorizacion = null;
	Calendar emisionCalendar = new GregorianCalendar();
	Map sriAirMap = new HashMap();
	Map sriIvaRetencionMap = new HashMap();
	
	public FixRetencionProveedorModel() {
		initKeyListeners();
		getTblCompras().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRetenciones().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initListeners();
		showSaveMode();
		setColumnsWidth();
		sriAirMap = mapearSriAir();
		sriIvaRetencionMap = mapearSriIvaRetencion();
		new HotKeyComponent(this);
	}
	
	private void obtenerInformacionPapeletaRetencion(){
		try {
			numeroEstablecimiento = null;
			numeroPuntoEmision = null;
			numeroAutorizacion = null;
			Collection<TipoParametroIf> tiposParametros = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByCodigo("PAPELRETPR");
			for ( TipoParametroIf tipoParametroIf : tiposParametros ){
				Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByTipoparametroId(tipoParametroIf.getId());
				for ( ParametroEmpresaIf parametro : parametros ){
					if ( "NUMEROESTABLECIMIENTO".equals(parametro.getCodigo()) ){
						numeroEstablecimiento = new StringBuilder(parametro.getValor());
					} else if ( "NUMEROPUNTOEMISION".equals(parametro.getCodigo()) ){
						numeroPuntoEmision = new StringBuilder(parametro.getValor());
					} else if ( "NUMEROAUTORIZACION".equals(parametro.getCodigo()) ){
						numeroAutorizacion = new StringBuilder(parametro.getValor());
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setColumnsWidth() {
		// tblCompras
		TableColumn anchoColumna = getTblCompras().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblCompras().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblCompras().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblCompras().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblCompras().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
		
		// tblAutorizarAsiento
		/*anchoColumna = getTblRetenciones().getColumnModel().getColumn(0);
		 anchoColumna.setPreferredWidth(30);
		 anchoColumna = getTblRetenciones().getColumnModel().getColumn(1);
		 anchoColumna.setPreferredWidth(50);
		 anchoColumna = getTblRetenciones().getColumnModel().getColumn(2);
		 anchoColumna.setPreferredWidth(300);
		 anchoColumna = getTblRetenciones().getColumnModel().getColumn(3);
		 anchoColumna.setPreferredWidth(20);
		 anchoColumna = getTblRetenciones().getColumnModel().getColumn(4);
		 anchoColumna.setPreferredWidth(20);*/
	}
	
	private void initKeyListeners() {
		getCmbFechaEmision().setLocale(Utilitarios.esLocale);
		getCmbFechaEmision().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaEmision().setEditable(false);
		getCmbFechaEmision().setShowNoneButton(false);
				
		getTxtEstablecimiento().addKeyListener(new TextChecker(MAX_LONGITUD_ESTABLECIMIENTO));
		getTxtEstablecimiento().addKeyListener(new NumberTextField());
		getTxtPuntoEmision().addKeyListener(new TextChecker(MAX_LONGITUD_PUNTO_EMISION));
		getTxtPuntoEmision().addKeyListener(new NumberTextField());
		getTxtSecuencial().addKeyListener(new TextChecker(MAX_LONGITUD_SECUENCIAL));
		getTxtSecuencial().addKeyListener(new NumberTextField());
		getTxtAutorizacion().addKeyListener(new TextChecker(MAX_LONGITUD_AUTORIZACION_RETENCION));
		getTxtAutorizacion().addKeyListener(new NumberTextField());
	}
	
	private Map mapearSriAir() {
		Map sriAirMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getSriAirSessionService().getSriAirList().iterator();
			while (it.hasNext()) {
				SriAirIf sriAir = (SriAirIf) it.next();
				sriAirMap.put(sriAir.getId(), sriAir);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return sriAirMap;
	}
	
	private Map mapearSriIvaRetencion() {
		Map sriIvaRetencionMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencionList().iterator();
			while (it.hasNext()) {
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) it.next();
				sriIvaRetencionMap.put(sriIvaRetencion.getId(), sriIvaRetencion);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return sriIvaRetencionMap;
	}
	
	private void initListeners() {
		getTblCompras().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowTblCompras(evt);
			}
		});
		
		getTblCompras().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowTblCompras(evt);
			}
		});
		
		getTblRetenciones().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowTblRetenciones(evt);
			}
		});
		
		getTblRetenciones().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowTblRetenciones(evt);
			}
		});
		
		getBtnActualizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getSelectedRowTblRetenciones() != -1) {
					int filaSeleccionada = getSelectedRowTblRetenciones();
					
					if (validateFields()) {
						DefaultTableModel modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
						actualizarRetencion(filaSeleccionada, modelTblRetenciones);
						clean();
					}
				}else{
					SpiritAlert.createAlert("Debe seleccionar una fila de la tabla de retenciones!", SpiritAlert.INFORMATION);
				}
			}
		});
		
		getBtnActualizarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getTblRetenciones().getModel().getRowCount() > 0){
					if (validateFields()) {
						DefaultTableModel modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
						int numeroFilas = modelTblRetenciones.getRowCount();
						if (numeroFilas > 0) {
							for (int fila = 0; fila < numeroFilas; fila++)
								actualizarRetencion(fila, modelTblRetenciones);
							
							clean();
						}
					}	
				}else{
					SpiritAlert.createAlert("Debe seleccionar una Compra!", SpiritAlert.INFORMATION);
				}
			}
		});
	}
	
	private void actualizarRetencion(int filaSeleccionada, DefaultTableModel modelTblRetenciones) {
		int ejercicio = Integer.parseInt(modelTblRetenciones.getValueAt(filaSeleccionada, 0).toString());
		String establecimiento = formatoSerie.format(Integer.parseInt(getTxtEstablecimiento().getText()));
		String puntoEmision = formatoSerie.format(Integer.parseInt(getTxtPuntoEmision().getText()));
		String secuencial = formatoSecuencial.format(Long.parseLong(getTxtSecuencial().getText()));
		String preimpreso =  establecimiento + "-" + puntoEmision + "-" + secuencial;
		String autorizacion = formatoAutorizacion.format(Long.parseLong(getTxtAutorizacion().getText()));
		double baseImponible = Double.parseDouble(Utilitarios.removeDecimalFormat(modelTblRetenciones.getValueAt(filaSeleccionada, 3).toString()));
		String impuesto = modelTblRetenciones.getValueAt(filaSeleccionada, 4).toString();
		String codigoImpuesto = modelTblRetenciones.getValueAt(filaSeleccionada, 5).toString();
		double porcentajeRetencion = Double.parseDouble(modelTblRetenciones.getValueAt(filaSeleccionada, 6).toString().replaceAll("%",""));
		double valorRetenido = Double.parseDouble(Utilitarios.removeDecimalFormat(modelTblRetenciones.getValueAt(filaSeleccionada, 7).toString()));
		Long cuentaId = Long.parseLong(modelTblRetenciones.getValueAt(filaSeleccionada, 8).toString());
		modelTblRetenciones.setValueAt(preimpreso, filaSeleccionada, 1);
		modelTblRetenciones.setValueAt(autorizacion, filaSeleccionada, 2);
		Retencion retencion = new Retencion();
		retencion.setEjercicio(ejercicio);
		retencion.setEstablecimiento(establecimiento);
		retencion.setPuntoEmision(puntoEmision);
		retencion.setSecuencial(secuencial);
		retencion.setAutorizacion(autorizacion);
		retencion.setBaseImponible(baseImponible);
		retencion.setImpuesto(impuesto);
		retencion.setCodigoImpuesto(codigoImpuesto);
		retencion.setPorcentajeRetencion(porcentajeRetencion);
		retencion.setValorRetenido(valorRetenido);
		retencion.setCuentaId(cuentaId);

		ArrayList<Retencion> retencionVector = new ArrayList<Retencion>();
		
		if (retencionesMap.get(compra.getId()) != null)
			retencionVector = (ArrayList<Retencion>) retencionesMap.get(compra.getId());
		
		if (retencionVector.size() > filaSeleccionada){
			//retencionVector.setElementAt(retencion, filaSeleccionada);
			retencionVector.set(filaSeleccionada,retencion);
		} else
			retencionVector.add(retencion);
		
		retencionesMap.put(compra.getId(), retencionVector);
		fechasRetencionesMap.put(compra.getId(), new java.sql.Date(this.getCmbFechaEmision().getDate().getTime()));
	}
	
	private void enableSelectedRowTblCompras(ComponentEvent evt) {
		if (getTblCompras().getSelectedRow() != -1) {
			setSelectedRowTblCompras(((JTable) evt.getSource()).getSelectedRow());
			compra = (CompraIf) comprasColeccion.get(getSelectedRowTblCompras());
			listaTablaFilasRenta.clear();
			listaTablaFilasIva.clear();
			llenarTxtRetencion();
			cleanTable(getTblRetenciones());
			try {
				cargarTablaRetenciones(compra);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private void llenarTxtRetencion(){
		getTxtEstablecimiento().setText(numeroEstablecimiento.toString());
		getTxtPuntoEmision().setText(numeroPuntoEmision.toString());
		getTxtAutorizacion().setText(numeroAutorizacion.toString());
		getTxtSecuencial().setText("");
	}
	
	private void enableSelectedRowTblRetenciones(ComponentEvent evt) {
		if (getTblRetenciones().getSelectedRow() != -1) {
			llenarTxtRetencion();
			setSelectedRowTblRetenciones(((JTable) evt.getSource()).getSelectedRow());
			DefaultTableModel modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
			String preimpreso = modelTblRetenciones.getValueAt(getSelectedRowTblRetenciones(), 1).toString();
			String autorizacion = modelTblRetenciones.getValueAt(getSelectedRowTblRetenciones(), 2).toString();
			if (!preimpreso.equals("")) {
				getTxtEstablecimiento().setText(preimpreso.substring(0,3));
				getTxtPuntoEmision().setText(preimpreso.substring(4,7));
				getTxtSecuencial().setText(preimpreso.substring(8,preimpreso.length()));
			} else {
				//getTxtEstablecimiento().setText("");
				//getTxtPuntoEmision().setText("");
				getTxtSecuencial().setText("");
			}
			if ( !"".equals(autorizacion) )
				getTxtAutorizacion().setText(autorizacion);
		}
	}
	
	private void cargarTablaCompras() {
		try {
			DefaultTableModel tableModel = (DefaultTableModel) getTblCompras().getModel();
			Map<String,String> parameterMap = new HashMap<String,String>();
			parameterMap.put("estado", "X");
			Iterator comprasIt = ((ArrayList) SessionServiceLocator.getCompraSessionService().findCompraFixByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa())).iterator();
			//Collection comprasRetencionesColeccion = getCompraRetencionSessionService().findCompraRetencionByEmpresaId(Parametros.getIdEmpresa());
			while (comprasIt.hasNext()) {
				Object[] compraObject = (Object[]) comprasIt.next();
				CompraIf compra = (CompraIf) compraObject[0];
				//if (!existenRetenciones(comprasRetencionesColeccion, compra.getId())
				//		&& fechaValida(compra.getFecha())) {
					Vector<String> fila = new Vector<String>();
					fila.add(compra.getCodigo());
					//fila.add("id: " + compra.getId());
					DateFormat dateFormatter = Utilitarios.setFechaCortaUppercase();
					fila.add(dateFormatter.format(compra.getFecha()));
					ClienteIf proveedor = (ClienteIf) compraObject[1];
					fila.add(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial());
					fila.add(compra.getPreimpreso());
					fila.add(compra.getAutorizacion());
					tableModel.addRow(fila);
					comprasColeccion.add(compra);
				//}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public boolean fechaValida(Date compraFecha){
		/*if (compraFecha.getYear() + 1900 < 2008 || (compraFecha.getYear() + 1900 == 2008 && compraFecha.getMonth() + 1 <= 8)) {
			return false;
		}*/
		
		return true;
	}
	
	private boolean existenRetenciones(Collection comprasRetencionesColeccion, Long compraId) {
		Iterator comprasRetencionesIterator = comprasRetencionesColeccion.iterator();
		
		while (comprasRetencionesIterator.hasNext()) {
			CompraRetencionIf compraRetencion = (CompraRetencionIf) comprasRetencionesIterator.next();
			if (compraRetencion.getCompraId().compareTo(compraId) == 0)
				return true;
		}
		
		return false;
	}
	
	private Map mapearProveedoresOficinas() {
		Map proveedoresOficinasMap = new HashMap();
		
		try {
			Iterator proveedoresOficinasIterator = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (proveedoresOficinasIterator.hasNext()) {
				ClienteOficinaIf proveedorOficina = (ClienteOficinaIf) proveedoresOficinasIterator.next();
				proveedoresOficinasMap.put(proveedorOficina.getId(), proveedorOficina);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return proveedoresOficinasMap;
	}
	
	private Map mapearProveedores() {
		Map proveedoresMap = new HashMap();
		
		try {
			Iterator proveedoresIterator = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (proveedoresIterator.hasNext()) {
				ClienteIf proveedor = (ClienteIf) proveedoresIterator.next();
				proveedoresMap.put(proveedor.getId(), proveedor);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return proveedoresMap;
	}
	
	private void cargarTablaRetenciones(CompraIf compra) throws GenericBusinessException {
		DefaultTableModel tableModel = (DefaultTableModel) getTblRetenciones().getModel();
		
		if (retencionesMap.get(compra.getId()) == null) {
			Iterator detallesCompraIt = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId()).iterator();
			
			while (detallesCompraIt.hasNext()) {
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				CompraDetalleIf compraDetalle = (CompraDetalleIf) detallesCompraIt.next();
				Double descuento = Double.valueOf(compraDetalle.getDescuento().doubleValue());
				Double iva = Double.valueOf(compraDetalle.getIva().doubleValue());
				Double valor = Double.valueOf(compraDetalle.getValor().doubleValue());
				double cantidad = compraDetalle.getCantidad().doubleValue();
				double subtotal = cantidad * valor;
				//double descuentoPor = (descuento * 100) / (cantidad);
				//double descuentoSubtotal = subtotal * cantidad * (descuentoPor / 100);
				double descuentoSubtotal = descuento;
				
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				if (generico.getServicio().equals("S"))
					parameterMap.put("bienServicio", "S");
				else if (generico.getServicio().equals("N"))
					parameterMap.put("bienServicio", "B");
				
				ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				parameterMap.put("tipoPersona", proveedor.getTipoPersona());
				parameterMap.put("llevaContabilidad", proveedor.getLlevaContabilidad());
				//if (proveedor.getTipoPersona().equals(PERSONA_NATURAL))
					//parameterMap.put("profesional", proveedor.getTipoPersona());
				parameterMap.put("estado", "A");
				java.sql.Date fechaCompra = compra.getFecha();
				SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(compraDetalle.getIdSriAir());
				SriIvaRetencionIf sriIvaRetencion = null;
				if (compraDetalle.getSriIvaRetencionId() != null)
					sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(compraDetalle.getSriIvaRetencionId());
				Iterator sriProveedorRetencionIt = SessionServiceLocator.getSriProveedorRetencionSessionService().findSriProveedorRetencionByQueryAndFecha(parameterMap, fechaCompra).iterator();
				parameterMap.clear();
				parameterMap.put("entidadId", sriAir.getId());
				parameterMap.put("tipoEntidad", "S");
				parameterMap.put("nemonico", "RETERENTA");
				Iterator cuentaReterentaIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(parameterMap).iterator();
				CuentaEntidadIf cuentaReterenta = (cuentaReterentaIterator.hasNext())?(CuentaEntidadIf)cuentaReterentaIterator.next():null;
				SriProveedorRetencionIf spr = null;
				double porcentaje_retencion_fuente = 0D;
				double porcentaje_retencion_iva = 0D;
				while (sriProveedorRetencionIt.hasNext()) {
					SriProveedorRetencionIf bean = (SriProveedorRetencionIf) sriProveedorRetencionIt.next(); 
					if (bean.getRetefuente().compareTo(sriAir.getPorcentaje()) == 0 && (sriIvaRetencion == null || bean.getReteiva().compareTo(sriIvaRetencion.getPorcentaje()) == 0)) {
						spr = bean;
						porcentaje_retencion_fuente = spr.getRetefuente().doubleValue();
						porcentaje_retencion_iva = spr.getReteiva().doubleValue();
						break;
					}
				}
				
				//double retencion_renta = Utilitarios.redondeoValor((subtotal - descuentoSubtotal) * porcentaje_retencion_fuente / 100);
				//double retencion_iva = Utilitarios.redondeoValor(iva * porcentaje_retencion_iva / 100);
				double retencion_renta = (subtotal - descuentoSubtotal) * porcentaje_retencion_fuente / 100;
				double retencion_iva = iva * porcentaje_retencion_iva / 100;
				int ejercicio = compra.getFecha().getYear() + 1900;
				
				if (retencion_renta > 0) {
					ArrayList<String> filaRenta = new ArrayList<String>();
					filaRenta.add(String.valueOf(ejercicio));
					filaRenta.add("");
					filaRenta.add("");
					filaRenta.add(String.valueOf(subtotal - descuentoSubtotal));
					filaRenta.add("RENTA");
					filaRenta.add(((SriAirIf) sriAirMap.get(compraDetalle.getIdSriAir())).getCodigo());
					filaRenta.add(String.valueOf(Double.valueOf(porcentaje_retencion_fuente).doubleValue()) + "%");
					//filaRenta.add(formatoDecimal.format(retencion_renta));
					filaRenta.add(String.valueOf(retencion_renta));
					filaRenta.add((cuentaReterenta!=null)?String.valueOf(cuentaReterenta.getCuentaId()):String.valueOf(spr.getIdCuentaRetefuente()));
					
					listaTablaFilasRenta.add(filaRenta);
				}
				
				if (retencion_iva > 0) {
					ArrayList<String> filaIva = new ArrayList<String>();
					filaIva.add(String.valueOf(ejercicio));
					filaIva.add("");
					filaIva.add("");
					filaIva.add(String.valueOf(iva));
					filaIva.add("IVA");
					if (compraDetalle.getSriIvaRetencionId() != null)
						filaIva.add(((SriIvaRetencionIf) sriIvaRetencionMap.get(compraDetalle.getSriIvaRetencionId())).getCodigo());
					else
						filaIva.add("");
					filaIva.add(String.valueOf(Double.valueOf(porcentaje_retencion_iva).intValue()) + "%");
					//filaIva.add(formatoDecimal.format(retencion_iva));
					filaIva.add(String.valueOf(retencion_iva));
					filaIva.add(String.valueOf(spr.getIdCuentaReteiva()));
					
					listaTablaFilasIva.add(filaIva);
				}
			}
			
			//Agrupar filas
			List<ArrayList<String>> listaFilasRenta = agruparRetenciones(listaTablaFilasRenta);
			List<ArrayList<String>> listaFilasIva = agruparRetenciones(listaTablaFilasIva);
			
			//Agregar filas a la tabla
			Iterator<ArrayList<String>> listaFilasRentaIterator = listaFilasRenta.iterator();
			
			while (listaFilasRentaIterator.hasNext()) {
				List<String> fila = listaFilasRentaIterator.next();
				double valorRetenido = Double.valueOf(Utilitarios.removeDecimalFormat(fila.get(7).toString()));
				if (valorRetenido > 0D)
					tableModel.addRow(new Vector<String>(fila));
			}
			
			Iterator<ArrayList<String>> listaFilasIvaIterator = listaFilasIva.iterator();
			
			while (listaFilasIvaIterator.hasNext()) {
				List<String> fila = listaFilasIvaIterator.next();
				double valorRetenido = Double.valueOf(Utilitarios.removeDecimalFormat(fila.get(7).toString()));
				if (valorRetenido > 0D)
					tableModel.addRow(new Vector<String>(fila));
			}
		} else {
			ArrayList<Retencion> retencionVector = retencionesMap.get(compra.getId());
			
			for (int i=0; i<retencionVector.size(); i++) {
				Vector<String> fila = new Vector<String>();
				Retencion retencion = retencionVector.get(i);
				int ejercicio = retencion.getEjercicio();
				String establecimiento = retencion.getEstablecimiento();
				String puntoEmision = retencion.getPuntoEmision();
				String secuencial = retencion.getSecuencial();
				String preimpreso = establecimiento + "-" + puntoEmision + "-" + secuencial;
				String autorizacion = retencion.getAutorizacion();
				double baseImponible = retencion.getBaseImponible();
				String impuesto = retencion.getImpuesto();
				String codigoImpuesto = retencion.getCodigoImpuesto();
				double porcentajeRetencion = retencion.getPorcentajeRetencion();
				double valorRetenido = retencion.getValorRetenido();
				Long cuentaId = retencion.getCuentaId();
				fila.add(String.valueOf(ejercicio));
				fila.add(preimpreso);
				fila.add(autorizacion);
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(baseImponible)));
				fila.add(impuesto);
				fila.add(codigoImpuesto);
				fila.add(String.valueOf(porcentajeRetencion) + "%");
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(valorRetenido)));
				fila.add(String.valueOf(cuentaId));
				tableModel.addRow(fila);
			}
		}
	}
	
	private List<ArrayList<String>> agruparRetenciones(List <ArrayList<String> > retencionesColeccion) {
		ArrayList< ArrayList<String> > retencionesAgrupadas = new ArrayList<ArrayList<String>>();
		Iterator< ArrayList<String> > retencionesIterator = retencionesColeccion.iterator();
		
		while(retencionesIterator.hasNext()) {
			ArrayList<String> fila = retencionesIterator.next();
			Iterator< ArrayList<String> > retencionesAgrupadasIterator = retencionesAgrupadas.iterator();
			boolean agrupada = false;
			while (retencionesAgrupadasIterator.hasNext()) {
				ArrayList<String> retencionAgrupada = retencionesAgrupadasIterator.next();
				if (retencionAgrupada.size() > 0 && fila.get(6).compareTo(retencionAgrupada.get(6)) == 0 && fila.get(5).compareTo(retencionAgrupada.get(5)) == 0) {
					double baseImponible = 0D;
					double valorRetenido = 0D;
					baseImponible = Double.valueOf(Utilitarios.removeDecimalFormat(retencionAgrupada.get(3)));
					baseImponible += Double.valueOf(Utilitarios.removeDecimalFormat(fila.get(3)));
					valorRetenido = Double.valueOf(Utilitarios.removeDecimalFormat(retencionAgrupada.get(7)));
					valorRetenido += Double.valueOf(Utilitarios.removeDecimalFormat(fila.get(7)));
						
					if (valorRetenido >= 0.0) {
						retencionAgrupada.set(3, String.valueOf(baseImponible));
						retencionAgrupada.set(7, String.valueOf(valorRetenido));
					}
					
					agrupada = true;
				}
			}
			
			if (!agrupada)
				retencionesAgrupadas.add(fila);
		}
		retencionesColeccion = new ArrayList();
		Iterator it = retencionesAgrupadas.iterator();
		while (it.hasNext()) {
			ArrayList<String> retencion = (ArrayList<String>) it.next();
			double valor = Double.valueOf(Utilitarios.removeDecimalFormat(retencion.get(7)));
			retencion.set(7, String.valueOf(valor));
			retencionesColeccion.add(retencion);
		}
		return retencionesColeccion;
	}
	
	/*private List<Retencion> agruparRetencionesPorComprobante(List<Retencion> retencionesColeccion) {
		List<Retencion> retencionesAgrupadas = new ArrayList<Retencion>();
		Iterator retencionesIterator = retencionesColeccion.iterator();
		
		while(retencionesIterator.hasNext()) {
			Retencion retencion = (Retencion) retencionesIterator.next();
			Iterator retencionesAgrupadasIterator = retencionesAgrupadas.iterator();
			boolean agrupada = false;
			while (retencionesAgrupadasIterator.hasNext()) {
				Retencion retencionAgrupada = (Retencion) retencionesAgrupadasIterator.next();
				if (retencion.getEstablecimiento().equals(retencionAgrupada.getEstablecimiento()) &&
					retencion.getPuntoEmision().equals(retencionAgrupada.getPuntoEmision()) &&
					retencion.getSecuencial().equals(retencionAgrupada.getSecuencial()) &&
					retencion.getAutorizacion().equals(retencion.getAutorizacion())) {
					double baseImponible = 0D;
					double valorRetenido = 0D;
					baseImponible = Double.valueOf(retencionAgrupada.getBaseImponible());
					baseImponible += Double.valueOf(retencion.getBaseImponible());
					valorRetenido = Double.valueOf(retencionAgrupada.getValorRetenido());
					valorRetenido += Double.valueOf(retencion.getValorRetenido());
						
					if (valorRetenido >= 0.0) {
						retencionAgrupada.setBaseImponible(baseImponible);
						retencionAgrupada.setValorRetenido(valorRetenido);
					}
					
					agrupada = true;
				}
			}
			
			if (!agrupada)
				retencionesAgrupadas.add(retencion);
		}
		return retencionesAgrupadas;
		//retencionesColeccion = retencionesAgrupadas;
		//return retencionesColeccion;
	}*/
	
	private void cleanTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (int i = table.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);	
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		selectedRowTblCompras = -1;
		selectedRowTblRetenciones = -1;
		//comprasColeccion.clear();
		comprasColeccion = null;
		comprasColeccion = new ArrayList<CompraIf>();
		//carteraDetalleColeccion.clear();
		carteraDetalleColeccion = null; 
		carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
		//carteraAfectaColeccion.clear();
		carteraAfectaColeccion = null;
		carteraAfectaColeccion = new ArrayList<CarteraAfectaIf>();
		compra = null;
		//listaTablaFilasRenta.clear();
		listaTablaFilasRenta = null;
		listaTablaFilasRenta = new ArrayList< ArrayList<String> >();
		//listaTablaFilasIva.clear(); 
		listaTablaFilasIva = null;
		listaTablaFilasIva = new ArrayList< ArrayList<String> >();
		//retencionesMap.clear();
		retencionesMap = null;
		retencionesMap = new HashMap<Long,ArrayList<Retencion>>();
		
		emisionCalendar = new GregorianCalendar();
		getCmbFechaEmision().setCalendar(emisionCalendar);
		
		cleanTable(getTblCompras());
		cleanTable(getTblRetenciones());
		cargarTablaCompras();
		obtenerInformacionPapeletaRetencion();
	}
	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}
	
	private void generarReporte(List<RetencionProveedorData> retencionReportList) {
		if (retencionReportList.size() > 0) {
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				String fileName = "jaspers/cartera/RPComprobanteRetencion.jasper";
				HashMap parametrosMap = new HashMap();
				ReportModelImpl.processReport(fileName, parametrosMap, retencionReportList, true);
			}
		} 
	}
	
	public void save() {
		try {
			Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
			parametrosEmpresa.put("idEmpresa", Parametros.getIdEmpresa());
			parametrosEmpresa.put("idOficina", Parametros.getIdOficina());
			parametrosEmpresa.put("IVA", Parametros.getIVA());
			UsuarioIf usuarioIf = (UsuarioIf)Parametros.getUsuarioIf();
			parametrosEmpresa.put("idUsuario", usuarioIf.getId());
			parametrosEmpresa.put("codMoneda", Parametros.getCodMoneda());
			List<RetencionProveedorData> retencionReportList = SessionServiceLocator.getCarteraSessionService().procesarFixRetencionProveedores(comprasColeccion, retencionesMap, fechasRetencionesMap, parametrosEmpresa, mapearProveedores(), mapearProveedoresOficinas());
			SpiritAlert.createAlert("Información guardada con éxito", SpiritAlert.INFORMATION);
			generarReporte(retencionReportList);
			showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception ev) {
			SpiritAlert.createAlert("Se ha producido un error al guardar los datos!!!", SpiritAlert.ERROR);
			ev.printStackTrace();
	    }
	}
	
	/*public void save() {
		
		
		try {
			//1) Agrupar detalles de la tabla de retenciones de acuerdo a preimpreso y autorización
			Iterator comprasIterator = comprasColeccion.iterator();
			List<Retencion> retencionList = new ArrayList<Retencion>();
			
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				Vector<Retencion> retencionVector = (Vector<Retencion>) retencionesMap.get(compra.getId());
				if ( retencionVector != null ){
					//Vector<Retencion> retencionVectorDesagrupado = (Vector<Retencion>)(ObjectCloner.deepCopy(retencionVector));
					Vector<Retencion> retencionVectorDesagrupado = (Vector<Retencion>)(DeepCopy.copy(retencionVector));
					retencionList = agruparRetencionesPorComprobante(retencionVectorDesagrupado);
					//agruparRetencionesPorComprobante(retencionList,retencionVectorDesagrupado);
					Iterator retencionListIt = retencionList.iterator();
					CarteraIf cartera = registrarCartera(compra, retencionList);
					carteraDetalleColeccion.clear();
					//2) Guardar los datos en tabla Compra_Retencion
					while (retencionListIt.hasNext()) {
						Retencion retencion = (Retencion) retencionListIt.next();
						CompraRetencionData compraRetencion = new CompraRetencionData();
						compraRetencion.setEstablecimiento(retencion.getEstablecimiento());
						compraRetencion.setPuntoEmision(retencion.getPuntoEmision());
						compraRetencion.setSecuencial(retencion.getSecuencial());
						compraRetencion.setAutorizacion(retencion.getAutorizacion());
						java.util.Date fechaEmision = new java.util.Date();
						compraRetencion.setFechaEmision(new java.sql.Date(fechaEmision.getYear(), fechaEmision.getMonth(), fechaEmision.getDate()));
						compraRetencion.setCompraId(compra.getId());
						getCompraRetencionSessionService().saveCompraRetencion(compraRetencion);
						//3) Generar cartera por concepto de retención
						CarteraDetalleIf carteraDetalle = registrarCarteraDetalle(cartera, retencion);
						carteraDetalleColeccion.add(carteraDetalle);
					}
					
					//int year = compra.getFecha().getYear() + 1900;
					String codigoCompra = compra.getCodigo();
					//String codigoCarteraAfecta = "COM-" + String.valueOf(year) + "-" + codigoCompra;
					String codigoCarteraAfecta = "COM-" + codigoCompra;
					Map<String,String> parameterMap = new HashMap<String,String>();
					parameterMap.put("codigo", codigoCarteraAfecta);
					Iterator carteraCompraIt = getCarteraSessionService().findCarteraByQuery(parameterMap, Parametros.getIdEmpresa()).iterator();
	
					CarteraIf carteraCompra = null;
					Vector<CarteraIf> carteraCompraColeccion = new Vector<CarteraIf>();
					
					if (carteraCompraIt.hasNext()) {
						carteraCompra = (CarteraIf) carteraCompraIt.next();
						carteraCompraColeccion.add(carteraCompra);
					}
					
					CarteraIf carteraIf = getCarteraSessionService().procesarCartera(cartera, carteraDetalleColeccion, carteraCompraColeccion, Parametros.getIdEmpresa());
					
					//4) Generar asiento automático
					RetencionProveedorAsientoAutomaticoHandler.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
					for (int i = 0; i < retencionVector.size(); i++) {
						Retencion retencion = (Retencion) retencionVector.get(i);
						if (i != retencionVector.size() - 1)
							generarAsientoAutomatico(compra, carteraIf, retencion, false);
						else if (i == retencionVector.size() - 1)
							generarAsientoAutomatico(compra, carteraIf, retencion, true);
					}
				}
			}
			
			SpiritAlert.createAlert("Datos guardados con éxito",SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al guardar los datos!!!", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception ev) {
			SpiritAlert.createAlert("Se ha producido un error al guardar los datos!!!", SpiritAlert.ERROR);
			ev.printStackTrace();
	    }
		
		//5) Generar reportes con detalles agrupados de acuerdo a preimpreso y autorizacion
		
		showSaveMode();
	}*/
	
	/*private CarteraIf registrarCartera(CompraIf compra, List retencionList) {
		CarteraData cartera = new CarteraData();
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		Iterator it;
		
		try {
			cartera.setTipo("P");
			cartera.setOficinaId(Parametros.getIdOficina());
			parameterMap.put("codigo", "CRE");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("estado", "A");
			it = getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				cartera.setTipodocumentoId(tipoDocumento.getId());
			}
			
			cartera.setClienteoficinaId(compra.getProveedorId());
			cartera.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
			it = getMonedaSessionService().findMonedaByCodigo(Parametros.getCodMoneda()).iterator();
			if (it.hasNext()) {
				MonedaIf moneda = (MonedaIf) it.next();
				cartera.setMonedaId(moneda.getId());
			}
			java.util.Date fechaEmision = new java.util.Date();
			cartera.setFechaEmision(new java.sql.Date(fechaEmision.getYear(), fechaEmision.getMonth(), fechaEmision.getDate()));
			double valorRetenido = 0D;
			for (int i=0; i<retencionList.size(); i++) {
				Retencion retencion = (Retencion) retencionList.get(i);
				valorRetenido += retencion.getValorRetenido();
			}
			cartera.setValor(BigDecimal.valueOf(Double.valueOf(valorRetenido)));
			cartera.setSaldo(BigDecimal.valueOf(Double.valueOf(valorRetenido)));
			cartera.setFechacambioestado(new java.sql.Date(fechaEmision.getYear(), fechaEmision.getMonth(), fechaEmision.getDate()));
			cartera.setEstado("N");
			cartera.setComentario("RETENCION EN FACTURA DE PROVEEDOR # " + compra.getPreimpreso());
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al registrar la cartera!!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return cartera;
	}*/
	
	/*private CarteraDetalleIf registrarCarteraDetalle(CarteraIf cartera, Retencion retencion) {
		CarteraDetalleData carteraDetalle = new CarteraDetalleData();
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		String codigoDocumento = "";
		
		try {
			if (retencion.getImpuesto().equals("RENTA"))
				codigoDocumento = "RERP";
			else
				codigoDocumento = "REIP";
			
			parameterMap.put("codigo", codigoDocumento);
			Iterator documentoIt = getDocumentoSessionService().findDocumentoByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa()).iterator();
			
			if (documentoIt.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentoIt.next();
				carteraDetalle.setDocumentoId(documento.getId());
			}
			
			//carteraDetalle.setSecuencial(); //Secuenciador de los detalles de la cartera
			//carteraDetalle.setLineaId(); //La misma linea
			//carteraDetalle.setTipopagoId(); //Pago al contado
			//carteraDetalle.setCuentaBancariaId();
			//carteraDetalle.setReferencia();
			carteraDetalle.setPreimpreso(retencion.getEstablecimiento() + "-" + retencion.getPuntoEmision() + "-" + retencion.getSecuencial());
			carteraDetalle.setAutorizacion(retencion.getAutorizacion());
			//carteraDetalle.setDepositoId();
			carteraDetalle.setFechaCreacion(cartera.getFechaEmision());
			carteraDetalle.setFechaCartera(cartera.getFechaEmision());
			carteraDetalle.setValor(BigDecimal.valueOf(Double.valueOf(retencion.getValorRetenido())));
			carteraDetalle.setSaldo(BigDecimal.valueOf(Double.valueOf(retencion.getValorRetenido())));
			carteraDetalle.setCartera("S");
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al registrar el detalle de la cartera!!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return carteraDetalle;
	}*/
		
	/*private void generarAsientoAutomatico(CompraIf compra, CarteraIf cartera, Retencion retencion, boolean procesarAsiento) throws GenericBusinessException {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		String codigoDocumento = "";
		
		if (retencion.getImpuesto().equals("RENTA"))
			codigoDocumento = "RERP";
		else
			codigoDocumento = "REIP";
		
		parameterMap.put("codigo", codigoDocumento);
		Iterator documentoIt = getDocumentoSessionService().findDocumentoByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa()).iterator();
		
		if (documentoIt.hasNext()) {
			DocumentoIf documento = (DocumentoIf) documentoIt.next();
			
			if (documento != null) {
				Iterator eventoContableIterator = getEventoContableSessionService().findEventoContableByDocumentoId(documento.getId()).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
					if (eventoContable != null) {
						parameterMap.clear();
						parameterMap.put("COMPRA_BEAN", compra);
						parameterMap.put("CARTERA_BEAN", cartera);
						parameterMap.put("OBSERVACION", compra.getObservacion());
						parameterMap.put("CTAXPAG", retencion.getValorRetenido());
						if (retencion.getImpuesto().equals("RENTA")) {
							parameterMap.put("RETERENTA", retencion.getValorRetenido());
							parameterMap.put("ID_CUENTA_RETERENTA", retencion.getCuentaId());
						} else {
							parameterMap.put("RETEIVA", retencion.getValorRetenido());
							parameterMap.put("ID_CUENTA_RETEIVA", retencion.getCuentaId());
						}
						parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId());
						new RetencionProveedorAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento);
					}
				}
			}
		}
	}*/
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	public void delete() {
		/*try {
		 AsientoModel.getAsientoSessionService().eliminarAsiento(asientoForAuthorize.getId());
		 SpiritAlert.createAlert("Asiento eliminado con éxito", SpiritAlert.INFORMATION);				
		 this.clean();
		 this.showSaveMode();
		 } catch (GenericBusinessException e) {
		 SpiritAlert.createAlert("Error al eliminar el asiento", SpiritAlert.WARNING);
		 }*/
	}
	
	public void authorize() {
		/*try {
		 // Cuando se actualiza un asiento previamente guardado con estado ASIENTO,
		  // antes de afectar los saldos nuevamente se deben reversar los saldos afectados anteriormente por este asiento
		   boolean reversarSaldos = false;
		   AsientoIf asiento = asientoForAuthorize;
		   asiento.setStatus("A");
		   AsientoModel.getAsientoSessionService().actualizarAsiento(asiento, asientoDetalleColeccion, null, null, null, reversarSaldos);
		   SpiritAlert.createAlert("Asiento autorizado con éxito", SpiritAlert.INFORMATION);
		   this.showSaveMode();
		   } catch (SaldoCuentaNegativoException ex) {
		   SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
		   ex.printStackTrace();
		   } catch (javax.ejb.EJBException ejbex) {
		   if (ejbex.getMessage().contains("java.lang.IllegalStateException"))
		   SpiritAlert.createAlert("Saldo de cuenta negativo.\n" + "No se ha podido autorizar el asiento", SpiritAlert.INFORMATION);
		   else
		   SpiritAlert.createAlert("Ocurrió un error al autorizar el asiento", SpiritAlert.ERROR);
		   ejbex.printStackTrace();
		   } catch (GenericBusinessException e) {
		   SpiritAlert.createAlert("Ocurrió un error al autorizar el asiento", SpiritAlert.ERROR);
		   }*/
	}
	
	public void clean() {
		getTxtEstablecimiento().setText("");
		getTxtPuntoEmision().setText("");
		getTxtSecuencial().setText("");
		getTxtAutorizacion().setText("");
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean validateFields() {
		if (getTxtEstablecimiento().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el preimpreso del comprobante de retención!!!", SpiritAlert.WARNING);
			getTxtEstablecimiento().grabFocus();
			return false;
		}
		
		if (getTxtPuntoEmision().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el preimpreso del comprobante de retención!!!", SpiritAlert.WARNING);
			getTxtPuntoEmision().grabFocus();
			return false;
		}
		
		if (getTxtSecuencial().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el preimpreso del comprobante de retención!!!", SpiritAlert.WARNING);
			getTxtSecuencial().grabFocus();
			return false;
		}
		
		if (getTxtAutorizacion().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar la autorización del comprobante de retención!!!", SpiritAlert.WARNING);
			getTxtAutorizacion().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void refresh() {
		showSaveMode();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	private int getSelectedRowTblCompras() {
		return this.selectedRowTblCompras;
	}
	
	private void setSelectedRowTblCompras(int selectedRowTblCompras) {
		this.selectedRowTblCompras = selectedRowTblCompras;
	}
	
	private int getSelectedRowTblRetenciones() {
		return this.selectedRowTblRetenciones;
	}
	
	private void setSelectedRowTblRetenciones(int selectedRowTblRetenciones) {
		this.selectedRowTblRetenciones = selectedRowTblRetenciones;
	}
}
