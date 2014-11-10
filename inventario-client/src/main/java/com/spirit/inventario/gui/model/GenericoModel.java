package com.spirit.inventario.gui.model;

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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.util.DateHelperClient;
import com.spirit.general.util.GeneralUtil;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.FamiliaGenericoIf;
import com.spirit.inventario.entity.GenericoData;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.MedidaIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoData;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.gui.criteria.GenericoCriteria;
import com.spirit.inventario.gui.panel.JPGenerico;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class GenericoModel extends JPGenerico {
	private static final long serialVersionUID = -311953778880431196L;
	protected int mode;
	java.util.Date fechaCreacion = new java.util.Date();
	protected ClienteOficinaIf proveedorIf;
	DefaultTableModel modelProducto;
	Vector<ProductoIf> productoColeccion = new Vector<ProductoIf>();
	Vector<ProductoIf> productoRemovidoColeccion = new Vector<ProductoIf>();
	final JPopupMenu popupMenuProducto = new JPopupMenu();
	JMenuItem itemEliminarProducto;
	private static final int MAX_LONGITUD_CODIGO = 20;
	private static final int MAX_LONGITUD_CODIGO_PRODUCTO = 10;
	private static final int MAX_LONGITUD_IVA = 7;
	private static final int MAX_LONGITUD_ICE = 7;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 7;
	private static final int MAX_LONGITUD_REFERENCIA = 20;
	private static final int MAX_LONGITUD_ABREVIADO = 20;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_NOMBRE_GENERICO = 30;
	private static final int MAX_LONGITUD_SUBPROVEEDOR = 300;
	private static final int MAX_LONGITUD_PARTIDA_ARANCELARIA = 30;
	private static final int MAX_LONGITUD_CODIGO_BARRAS = 30;
	private static final int MAX_LONGITUD_MARGEN_MINIMO = 10;
	private static final int MAX_LONGITUD_COSTO = 10;
	private static final int MAX_LONGITUD_REBATE = 10;
	private static final int MAX_LONGITUD_PESO = 10;
	private static final String ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_INACTIVO = "INACTIVO";
	private static final String OPCION_SI = "SI";
	private static final String OPCION_NO = "NO";
	private static final String ORIGEN_LOCAL = "LOCAL";
	private static final String ORIGEN_IMPORTADO = "IMPORTADO";
	private DecimalFormat formatoDecimal=new DecimalFormat("#,##0.00");
	protected ArrayList lista;
	protected GenericoIf genericoIf;
	private static final String NOMBRE_TIPO_MEDIOS = "MEDIOS";
	private static final String NOMBRE_TIPO_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_PROVEEDOR = NOMBRE_TIPO_PROVEEDOR.substring(0,1);
	private static final String TIPO_MEDIOS = "M";
	private static final String NOMBRE_TIPO_PRODUCCION = "PRODUCCION";
	private static final String TIPO_PRODUCCION = "P";
	private static final String NOMBRE_TIPO_GASTO = "GASTO";
	private static final String TIPO_GASTO = "G";
	private static final String NOMBRE_TIPO_OTRO = "OTRO";
	private static final String TIPO_OTRO = "O";
	private Long idTipoProductoNinguno = 0L;
	private Long idLineaNinguno = 0L;
	private Long idPresentacionNinguno = 0L;
	private Long idColorNinguno = 0L;
	private Long idModeloNinguno = 0L;
	
	private Map<Long, PresentacionIf> mapaPresentacion = null;
	
	public GenericoModel() {
		initKeyListeners();	
		initListeners();
		initVariables();
		setColumnWidthTable();
		showSaveMode();
		//popup para borrar registros de la tabla de productos
		itemEliminarProducto = new JMenuItem("Eliminar");
		itemEliminarProducto.addActionListener(oActionListenerEliminarProducto);
		popupMenuProducto.add(itemEliminarProducto);
		getTblProductos().addMouseListener(oMouseAdapterTblProducto);
		//getTblProductos().addMouseListener(oMouseReleasedTblProducto);
		getTblProductos().addKeyListener(oKeyAdapterTblProducto);
		new HotKeyComponent(this);
	}
	
	private void initVariables() {
		try {
		Map parameterMap = new HashMap();
		parameterMap.put("empresaId", Parametros.getIdEmpresa());
		parameterMap.put("codigo", "NIN");
		Iterator it = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByQuery(parameterMap).iterator();
		if (it.hasNext())
			idTipoProductoNinguno = ((TipoProductoIf) it.next()).getId();
		it = SessionServiceLocator.getLineaSessionService().findLineaByQuery(parameterMap).iterator();
		if (it.hasNext())
			idLineaNinguno = ((LineaIf) it.next()).getId();
		it = SessionServiceLocator.getPresentacionSessionService().findPresentacionByQuery(parameterMap).iterator();
		if (it.hasNext())
			idPresentacionNinguno = ((PresentacionIf) it.next()).getId();
		parameterMap.clear();
		parameterMap.put("nombre", "NINGUNO");
		it = SessionServiceLocator.getColorSessionService().findColorByQuery(parameterMap).iterator();
		if (it.hasNext())
			idColorNinguno = ((ColorIf) it.next()).getId();
		it = SessionServiceLocator.getModeloSessionService().findModeloByQuery(parameterMap).iterator();
		if (it.hasNext())
			idModeloNinguno = ((ModeloIf) it.next()).getId();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void setColumnWidthTable() {
		TableColumn anchoColumna = getTblProductos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblProductos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblProductos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblProductos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblProductos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
	}
	
	private void initKeyListeners() {
		getBtnBuscarProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProveedor().setToolTipText("Buscar Proveedor");
		getBtnAgregarProducto().setToolTipText("Agregar producto");
		getBtnAgregarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarProducto().setText("");
		getBtnActualizarProducto().setToolTipText("Actualizar producto");
		getBtnActualizarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarProducto().setText("");
		getBtnEliminarProducto().setToolTipText("Eliminar producto");
		getBtnEliminarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarProducto().setText("");
		
		//validaciones para los campos de generico
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtNombreGenerico().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_GENERICO));
		getTxtAbreviado().addKeyListener(new TextChecker(MAX_LONGITUD_ABREVIADO));
		getTxtPartidaArancelaria().addKeyListener(new TextChecker(MAX_LONGITUD_PARTIDA_ARANCELARIA));
		getTxtIva().addKeyListener(new TextChecker(MAX_LONGITUD_IVA));
		getTxtIva().addKeyListener (new NumberTextFieldDecimal());
		getTxtIce().addKeyListener(new TextChecker(MAX_LONGITUD_ICE));
		getTxtIce().addKeyListener (new NumberTextFieldDecimal());
		getTxtOtroImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_OTRO_IMPUESTO));
		getTxtOtroImpuesto().addKeyListener (new NumberTextFieldDecimal());
		//validaciones para los campos de producto
		getTxtCodigoProducto().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_PRODUCTO));
		getTxtCodigoBarras().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_BARRAS));
		getTxtCosto().addKeyListener(new TextChecker(MAX_LONGITUD_COSTO));
		getTxtCosto().addKeyListener(new NumberTextFieldDecimal());
		getTxtMargenMinimo().addKeyListener(new TextChecker(MAX_LONGITUD_MARGEN_MINIMO));
		getTxtMargenMinimo().addKeyListener(new NumberTextFieldDecimal());
		getTxtRebate().addKeyListener(new TextChecker(MAX_LONGITUD_REBATE));
		getTxtRebate().addKeyListener(new NumberTextFieldDecimal());
		getTxtPesoBruto().addKeyListener(new TextChecker(MAX_LONGITUD_PESO));
		getTxtPesoBruto().addKeyListener(new NumberTextFieldDecimal());
		getTxtPesoNeto().addKeyListener(new TextChecker(MAX_LONGITUD_PESO));
		getTxtPesoNeto().addKeyListener(new NumberTextFieldDecimal());
		getTxtSubproveedor().addKeyListener(new TextChecker(MAX_LONGITUD_SUBPROVEEDOR));
	}
	
	private void initListeners() {
		//agrego el listener al botón de actyualizar producto
		getBtnActualizarProducto().addActionListener(oActionListenerActualizarProducto);
		
		getBtnAgregarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalle();
			}
		});
		
		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalle();
			}
		});
		
		getBtnBuscarProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = "PR";
					String tituloVentanaBusqueda = "Proveedores";
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(10);
					anchoColumnas.addElement(350);
					String mmpg = "MP";
					if (getCmbTipo().getSelectedItem() != null){
						if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_GASTO)){
							mmpg = "";
						}
						else{
							mmpg = mmpg.replaceAll(getCmbTipo().getSelectedItem().toString().substring(0,1),"");
						}
					}
						
					ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, mmpg, false);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if ( popupFinder.getElementoSeleccionado() != null ){
						proveedorIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						//try {
						//getTxtProveedor().setText(proveedorIf.getCodigo() + " - " + ((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedorIf.getId()).iterator().next()).getNombreLegal());
						CiudadIf ciudad = (CiudadIf) SessionServiceLocator.getCiudadSessionService().getCiudad(proveedorIf.getCiudadId());
						getTxtProveedor().setText(ciudad.getCodigo() + " - " + proveedorIf.getDescripcion());
						//} catch (GenericBusinessException e) {
						//	SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						//	e.printStackTrace();
						//}
						
						if (getCmbServicio().getSelectedItem().equals(OPCION_NO)) {
							cargarComboMarca();
						} else {
							getCmbMarca().setSelectedIndex(-1);
							getCmbMarca().setEnabled(false);
						}
					}
				} catch(GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
				}
			}
		});
		
		getCmbServicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				validateCmbMedida();
				validateCmbMarca();
			}
		});
		
		getCmbMarca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbMarca().getSelectedItem()!=null) {
					MarcaProductoIf marca = (MarcaProductoIf) getCmbMarca().getSelectedItem();
					cargarComboModelo(marca.getId());
				} else {
					getCmbModelo().setSelectedItem(null);
					getCmbModelo().removeAllItems();
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblProducto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblProductos().getModel().getRowCount()>0)
				popupMenuProducto.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblProducto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			ProductoIf productoIf = (ProductoIf) productoColeccion.get(((JTable) evt.getSource()).getSelectedRow());
			
			try {
				getTxtCodigoProducto().setText(productoIf.getCodigo());
				/*if(getMode()==SpiritMode.SAVE_MODE)
				 getTxtCodigoProducto().setEnabled(true);
				 if(getMode()==SpiritMode.UPDATE_MODE)
				 getTxtCodigoProducto().setEnabled(false);*/
				
				String fecha = Utilitarios.getFechaUppercase(productoIf.getFechaCreacion());
				getTxtFechaCreacionProducto().setText(fecha);
				
				proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
				getTxtProveedor().setText(proveedorIf.getCodigo() + " - " + ((ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(productoIf.getProveedorId()).iterator().next()).getNombreLegal());
				getTxtSubproveedor().setText(productoIf.getSubproveedor());
				getTxtCodigoBarras().setText(productoIf.getCodigoBarras());
				
				if(productoIf.getPresentacionId() != null){
					getCmbPresentacionId().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPresentacionId(), productoIf.getPresentacionId()));
					getCmbPresentacionId().repaint();
				} else {
					getCmbPresentacionId().setSelectedItem(null);
				}
				
				if(productoIf.getMarcaId() != null){
					getCmbMarca().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMarca(), productoIf.getMarcaId()));
					getCmbMarca().repaint();
				} else {
					getCmbMarca().setSelectedItem(null);
				}
				
				if(productoIf.getModeloId() != null){
					getCmbModelo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModelo(), productoIf.getModeloId()));
					getCmbModelo().repaint();
				} else {
					getCmbModelo().setSelectedItem(null);
				}
				
				if (productoIf.getColorId() != null) {
					getCmbColor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbColor(), productoIf.getColorId()));
					getCmbColor().repaint();
				} else {
					getCmbColor().setSelectedItem(null);
				}
				
				if (productoIf.getGenerarCodigoBarras() != null) {
					if (productoIf.getGenerarCodigoBarras().equals("S"))
						getCbGenerarCodigoBarras().setSelected(true);
					else
						getCbGenerarCodigoBarras().setSelected(false);
				}
				
				if(productoIf.getCosto()!=null)
					getTxtCosto().setText(formatoDecimal.format(productoIf.getCosto().doubleValue()));
				else
					getTxtCosto().setText("0");
				
				if(productoIf.getMargenminimo()!=null)
					getTxtMargenMinimo().setText(formatoDecimal.format(productoIf.getMargenminimo().doubleValue()));
				else
					getTxtMargenMinimo().setText("0");
				
				if(productoIf.getRebate()!=null)
					getTxtRebate().setText(formatoDecimal.format(productoIf.getRebate().doubleValue()));
				else
					getTxtRebate().setText("0");
				
				if (productoIf.getPesoBruto()!=null)
					getTxtPesoBruto().setText(formatoDecimal.format(productoIf.getPesoBruto().doubleValue()));
				else
					getTxtPesoBruto().setText("0");
				
				if (productoIf.getPesoNeto()!=null)
					getTxtPesoNeto().setText(formatoDecimal.format(productoIf.getPesoNeto().doubleValue()));
				else
					getTxtPesoNeto().setText("0");
				
				if((ORIGEN_LOCAL.substring(0, 1)).equals(productoIf.getOrigenProducto()))
					getCmbOrigenProducto().setSelectedItem(ORIGEN_LOCAL);
				else
					getCmbOrigenProducto().setSelectedItem(ORIGEN_IMPORTADO);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getAceptapromocion()))
					getCmbAceptaPromocion().setSelectedItem(OPCION_SI);
				else
					getCmbAceptaPromocion().setSelectedItem(OPCION_NO);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getPermiteventa()))
					getCmbPermiteVenta().setSelectedItem(OPCION_SI);
				else
					getCmbPermiteVenta().setSelectedItem(OPCION_NO);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getAceptadevolucion()))
					getCmbAceptaDevolucion().setSelectedItem(OPCION_SI);
				else
					getCmbAceptaDevolucion().setSelectedItem(OPCION_NO);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getCambioprecio()))
					getCmbCambioPrecio().setSelectedItem(OPCION_SI);
				else
					getCmbCambioPrecio().setSelectedItem(OPCION_NO);
				
				if((ESTADO_ACTIVO.substring(0, 1)).equals(productoIf.getEstado()))
					getCmbEstadoProducto().setSelectedItem(ESTADO_ACTIVO);
				else
					getCmbEstadoProducto().setSelectedItem(ESTADO_INACTIVO);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private Map buildQuery() {
		Map<String,Object> aMap = new HashMap<String,Object>();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", getTxtNombre().getText() + "%");
		else
			aMap.put("nombre", "%");
		
		if (getCmbEstadoGenerico().getSelectedItem() != null)
			aMap.put("estado", getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		
		if (getCmbTipo().getSelectedItem() != null)
			aMap.put("mediosProduccion", getCmbTipo().getSelectedItem().toString().substring(0, 1));
		
		if (getCmbServicio().getSelectedItem() != null)
			aMap.put("servicio", getCmbServicio().getSelectedItem().toString().substring(0, 1));
		
		if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
			aMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
		
		if (getCmbLinea().getSelectedItem() != null)
			aMap.put("lineaId", ((LineaIf) getCmbLinea().getSelectedItem()).getId());
		
		if (getCmbFamiliaGenerico().getSelectedItem() != null)
			aMap.put("familiaGenericoId", ((FamiliaGenericoIf) getCmbFamiliaGenerico().getSelectedItem()).getId());
		
		if (getCmbTipoProducto().getSelectedItem() != null)
			aMap.put("tipoproductoId", ((TipoProductoIf) getCmbTipoProducto().getSelectedItem()).getId());
				
		return aMap;
	}
	
	public void save() {
		if (validateFields()) {
			try {
				GenericoIf generico = registrarGenerico();
				SessionServiceLocator.getGenericoSessionService().procesarGenerico(generico, productoColeccion);
				SpiritAlert.createAlert("Genérico guardado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private GenericoIf registrarGenerico() {
		GenericoData data = new GenericoData();
		
		//data.setCodigo(this.getTxtCodigo().getText());
		//data.setReferencia(this.getTxtReferencia().getText());
		data.setNombre(this.getTxtNombre().getText());
		data.setAbreviado(this.getTxtAbreviado().getText());
		data.setNombreGenerico(this.getTxtNombreGenerico().getText());
		data.setCambioDescripcion(this.getCmbCambiaDescripcion().getSelectedItem().toString().substring(0, 1));
		data.setEmpresaId(Parametros.getIdEmpresa());
		data.setTipoproductoId(((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId());
		if(getCmbServicio().getSelectedItem().equals(OPCION_NO))
			data.setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
		data.setPartidaArancelaria(this.getTxtPartidaArancelaria().getText());
		//data.setFechaCreacion(new java.sql.Date(fechaCreacion.getYear(),fechaCreacion.getMonth(),fechaCreacion.getDate()));
		data.setFechaCreacion(DateHelperClient.getTimeStamp(fechaCreacion));
		data.setUsaLote(this.getCmbUsaLote().getSelectedItem().toString().substring(0, 1));
		data.setLlevaInventario(this.getCmbEsInventario().getSelectedItem().toString().substring(0, 1));
		data.setLineaId(((LineaIf) this.getCmbLinea().getSelectedItem()).getId());
		
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtIva().getText())))
			data.setIva(BigDecimal.valueOf(Double.valueOf("0")));
		else
			data.setIva(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtIva().getText()))));
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtIce().getText())))
			data.setIce(BigDecimal.valueOf(Double.valueOf("0")));
		else
			data.setIce(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtIce().getText()))));
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText())))
			data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf("0")));
		else
			data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText()))));
		
		data.setServicio(this.getCmbServicio().getSelectedItem().toString().substring(0, 1));
		data.setFamiliaGenericoId(((FamiliaGenericoIf) this.getCmbFamiliaGenerico().getSelectedItem()).getId());
		data.setSerie(this.getCmbSerie().getSelectedItem().toString().substring(0, 1));
		data.setAfectastock(this.getCmbAfectaStock().getSelectedItem().toString().substring(0, 1));
		data.setEstado(this.getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		data.setMediosProduccion(this.getCmbTipo().getSelectedItem().toString().substring(0, 1));
		data.setCobraIva(this.getCmbCobraIva().getSelectedItem().toString().substring(0, 1));
		data.setCobraIvaCliente(this.getCmbCobraIvaCliente().getSelectedItem().toString().substring(0, 1));
		data.setCobraIce(this.getCmbCobraIce().getSelectedItem().toString().substring(0, 1));
		data.setAceptaDescuento(this.getCmbAceptaDescuento().getSelectedItem().toString().substring(0,1));
		
		return data;
	}
	
	public void find() {
		
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getGenericoSessionService().getGenericoListSize(mapa);
			if (tamanoLista > 0) {
				GenericoCriteria genericoCriteria = new GenericoCriteria(Parametros.getIdEmpresa());
				genericoCriteria.setResultListSize(tamanoLista);
				genericoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						genericoCriteria,
						JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject(popupFinder.getElementoSeleccionado());
				
			} else {
				SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información",SpiritAlert.ERROR);
		}
		
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getGenericoSessionService().eliminarGenerico(genericoIf.getId());
			SpiritAlert.createAlert("Genérico eliminado con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	public void report() {
		
	}
	
	public void refresh() {
		mapaPresentacion = null;
		mapaPresentacion = new HashMap<Long, PresentacionIf>();
		
		cargarComboLinea();
		cargarComboFamiliaGenerico();
		cargarComboTipoProducto();
		cargarComboMedida();
		cargarComboPresentacionId();
		cargarComboMarca();
		cargarComboColor();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpGenerico().getSelectedIndex();
		int tabCount = this.getJtpGenerico().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpGenerico().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (this.getJtpGenerico().getSelectedIndex() == 1)
			agregarDetalle();
	}
	
	public void updateDetail() {
		if (this.getJtpGenerico().getSelectedIndex() == 1)
			actualizarDetalle();
	}
	
	public void deleteDetail() {
		if (this.getJtpGenerico().getSelectedIndex() == 1)
			eliminarDetalle();
	}
	
	public void update() {
		if (validateFields()) {
			try {
				registrarGenericoForUpdate();
				SessionServiceLocator.getGenericoSessionService().actualizarGenerico(genericoIf, productoColeccion,productoRemovidoColeccion);
				SpiritAlert.createAlert("Genérico actualizado con éxito !!!", SpiritAlert.INFORMATION);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			}	
		} 
	}
	
	private void registrarGenericoForUpdate() {
		genericoIf.setNombre(this.getTxtNombre().getText());
		genericoIf.setAbreviado(this.getTxtAbreviado().getText());
		genericoIf.setNombreGenerico(this.getTxtNombreGenerico().getText());
		genericoIf.setCambioDescripcion(this.getCmbCambiaDescripcion().getSelectedItem().toString().substring(0, 1));
		genericoIf.setEmpresaId(Parametros.getIdEmpresa());
		genericoIf.setTipoproductoId(((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId());
		if(getCmbServicio().getSelectedItem().equals(OPCION_NO))
			genericoIf.setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
		genericoIf.setPartidaArancelaria(this.getTxtPartidaArancelaria().getText());
		genericoIf.setReferencia(this.getTxtReferencia().getText());
		genericoIf.setUsaLote(this.getCmbUsaLote().getSelectedItem().toString().substring(0, 1));
		genericoIf.setLlevaInventario(this.getCmbEsInventario().getSelectedItem().toString().substring(0, 1));
		genericoIf.setLineaId(((LineaIf) this.getCmbLinea().getSelectedItem()).getId());
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtIva().getText())))
			genericoIf.setIva(BigDecimal.valueOf(Double.valueOf("0")));
		else
			genericoIf.setIva(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtIva().getText()))));
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtIce().getText())))
			genericoIf.setIce(BigDecimal.valueOf(Double.valueOf("0")));
		else
			genericoIf.setIce(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtIce().getText()))));
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText())))
			genericoIf.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf("0")));
		else
			genericoIf.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText()))));
		genericoIf.setServicio(this.getCmbServicio().getSelectedItem().toString().substring(0, 1));
		genericoIf.setFamiliaGenericoId(((FamiliaGenericoIf) this.getCmbFamiliaGenerico().getSelectedItem()).getId());
		genericoIf.setSerie(this.getCmbSerie().getSelectedItem().toString().substring(0, 1));
		genericoIf.setAfectastock(this.getCmbAfectaStock().getSelectedItem().toString().substring(0, 1));
		genericoIf.setEstado(this.getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		genericoIf.setMediosProduccion(this.getCmbTipo().getSelectedItem().toString().substring(0, 1));
		genericoIf.setCobraIva(this.getCmbCobraIva().getSelectedItem().toString().substring(0, 1));
		genericoIf.setCobraIvaCliente(this.getCmbCobraIvaCliente().getSelectedItem().toString().substring(0, 1));
		genericoIf.setCobraIce(this.getCmbCobraIce().getSelectedItem().toString().substring(0, 1));
		genericoIf.setAceptaDescuento(this.getCmbAceptaDescuento().getSelectedItem().toString().substring(0,1));
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtReferencia().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& "".equals(this.getTxtFechaCreacionGenerico().getText())
				&& (this.getCmbUsaLote().getSelectedItem() == null)
				&& (this.getCmbEsInventario().getSelectedItem() == null)
				&& (this.getCmbLinea().getSelectedItem() == null)
				&& (this.getCmbCobraIva().getSelectedItem() == null)
				&& (this.getCmbCobraIvaCliente().getSelectedItem() == null)
				&& (this.getCmbCobraIce().getSelectedItem() == null)
				&& (this.getCmbServicio().getSelectedItem() == null)
				&& (this.getCmbFamiliaGenerico().getSelectedItem() == null)
				&& (this.getCmbEstadoGenerico().getSelectedItem() == null)
				&& (this.getCmbAceptaDescuento().getSelectedItem() == null)) 
		{
			return true;
		} else {
			return false;
		}
	}
	
	public void clean() {
		//clean de los campos de texto de generico
		this.getTxtCodigo().setText("");
		this.getTxtFechaCreacionGenerico().setText("");
		this.getTxtReferencia().setText("");
		this.getTxtNombre().setText("");
		this.getTxtNombreGenerico().setText("");
		this.getTxtAbreviado().setText("");
		this.getTxtPartidaArancelaria().setText("");
		this.getTxtIva().setText("");
		this.getTxtIce().setText("");
		this.getTxtOtroImpuesto().setText("");
		
		
		//clean de los combos de generico
		this.getCmbTipoProducto().setSelectedItem(null);
		this.getCmbTipoProducto().removeAllItems();
		this.getCmbFamiliaGenerico().setSelectedItem(null);
		this.getCmbFamiliaGenerico().removeAllItems();
		this.getCmbLinea().setSelectedItem(null);
		this.getCmbLinea().removeAllItems();
		this.getCmbMedida().setSelectedItem(null);
		this.getCmbMedida().removeAllItems();
		this.getCmbCambiaDescripcion().setSelectedItem(null);
		this.getCmbCambiaDescripcion().removeAllItems();
		this.getCmbAfectaStock().setSelectedItem(null);
		this.getCmbAfectaStock().removeAllItems();
		this.getCmbServicio().setSelectedItem(null);
		this.getCmbServicio().removeAllItems();
		this.getCmbUsaLote().setSelectedItem(null);
		this.getCmbUsaLote().removeAllItems();
		this.getCmbEsInventario().setSelectedItem(null);
		this.getCmbEsInventario().removeAllItems();
		this.getCmbSerie().setSelectedItem(null);
		this.getCmbSerie().removeAllItems();
		this.getCmbEstadoGenerico().setSelectedItem(null);
		this.getCmbEstadoGenerico().removeAllItems();
		this.getCmbCobraIva().setSelectedItem(null);
		this.getCmbCobraIva().removeAllItems();
		this.getCmbCobraIvaCliente().setSelectedItem(null);
		this.getCmbCobraIvaCliente().removeAllItems();
		this.getCmbCobraIce().setSelectedItem(null);
		this.getCmbCobraIce().removeAllItems();
		this.getCmbAceptaDescuento().setSelectedItem(null);
		this.getCmbAceptaDescuento().removeAllItems();
		this.getCmbMarca().setSelectedItem(null);
		this.getCmbMarca().removeAllItems();
		this.getCmbModelo().setSelectedItem(null);
		this.getCmbModelo().removeAllItems();
		
		cleanProducto();
		
		//Vacio la tabla de productos
		DefaultTableModel model = (DefaultTableModel) this.getTblProductos().getModel();
		
		for(int i= this.getTblProductos().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		productoColeccion = new Vector<ProductoIf>();
		productoRemovidoColeccion = new Vector<ProductoIf>();
		
		mapaPresentacion = null;
		mapaPresentacion = new HashMap<Long, PresentacionIf>();
		
		this.repaint();
	}
	
	private void cleanProducto() {
		//clean de los campos de texto de producto
		this.getTxtFechaCreacionProducto().setText("");
		this.getTxtProveedor().setText("");
		this.getTxtSubproveedor().setText("");
		this.getTxtCodigoProducto().setText("");
		this.getTxtCodigoBarras().setText("");
		this.getTxtCosto().setText("");
		this.getTxtMargenMinimo().setText("");
		this.getTxtRebate().setText("");
		this.getTxtPesoBruto().setText("");
		this.getTxtPesoNeto().setText("");
		//clean de los combos de producto
		this.getCmbPresentacionId().setSelectedItem(null);
		this.getCmbPresentacionId().removeAllItems();
		this.getCmbOrigenProducto().setSelectedItem(null);
		this.getCmbOrigenProducto().removeAllItems();
		this.getCmbAceptaPromocion().setSelectedItem(null);
		this.getCmbAceptaPromocion().removeAllItems();
		this.getCmbPermiteVenta().setSelectedItem(null);
		this.getCmbPermiteVenta().removeAllItems();
		this.getCmbAceptaDevolucion().setSelectedItem(null);
		this.getCmbAceptaDevolucion().removeAllItems();
		this.getCmbCambioPrecio().setSelectedItem(null);
		this.getCmbCambioPrecio().removeAllItems();
		this.getCmbEstadoProducto().setSelectedItem(null);
		this.getCmbEstadoProducto().removeAllItems();
		this.getCmbColor().setSelectedItem(null);
		this.getCmbColor().removeAllItems();
		this.getCbGenerarCodigoBarras().setSelected(false);
	}
	
	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtNombre().setBackground(Parametros.findColor);
		getCmbEstadoGenerico().setBackground(Parametros.findColor);
		getCmbTipo().setBackground(Parametros.findColor);
		getCmbServicio().setBackground(Parametros.findColor);
		getCmbLinea().setBackground(Parametros.findColor);
		getCmbFamiliaGenerico().setBackground(Parametros.findColor);
		getCmbTipoProducto().setBackground(Parametros.findColor);
		
		getCmbTipo().setSelectedIndex(-1);
		
		//habilito los campos de texto de generico
		getTxtCodigo().setEnabled(true);
		getTxtFechaCreacionGenerico().setEnabled(false);
		getTxtReferencia().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtNombreGenerico().setEnabled(false);
		getTxtAbreviado().setEnabled(false);
		getTxtPartidaArancelaria().setEnabled(false);
		getTxtIva().setEnabled(false);
		getTxtIce().setEnabled(false);
		getTxtOtroImpuesto().setEnabled(false);
		
		//habilito los campos de texto de producto
		getTxtFechaCreacionProducto().setEnabled(false);
		getTxtProveedor().setEnabled(false);
		getTxtProveedor().setEditable(false);
		getTxtSubproveedor().setEditable(false);
		getTxtCodigoBarras().setEnabled(false);
		getTxtCosto().setEnabled(false);
		getTxtMargenMinimo().setEnabled(false);
		getTxtRebate().setEnabled(false);
		getTxtPesoBruto().setEnabled(false);
		getTxtPesoNeto().setEnabled(false);
		
		//habilito los combos de generico
		getCmbFamiliaGenerico().setEnabled(true);
		getCmbLinea().setEnabled(true);
		getCmbMedida().setEnabled(false);
		getCmbCambiaDescripcion().setEnabled(false);
		getCmbAfectaStock().setEnabled(false);
		getCmbUsaLote().setEnabled(false);
		getCmbEsInventario().setEnabled(false);
		getCmbSerie().setEnabled(false);
		getCmbServicio().setEnabled(true);
		getCmbEstadoGenerico().setEnabled(true);
		getCmbCobraIva().setEnabled(false);
		getCmbCobraIvaCliente().setEnabled(false);
		getCmbCobraIce().setEnabled(false);
		getCmbAceptaDescuento().setEnabled(false);
		getCmbMarca().setEnabled(true);
		getCmbModelo().setEnabled(true);
		
		//habilito los combos de producto
		getCmbPresentacionId().setEnabled(false);
		getCmbOrigenProducto().setEnabled(false);
		getCmbAceptaPromocion().setEnabled(false);
		getCmbPermiteVenta().setEnabled(false);
		getCmbAceptaDevolucion().setEnabled(false);
		getCmbCambioPrecio().setEnabled(false);
		getCmbEstadoProducto().setEnabled(false);
		getCmbColor().setEnabled(false);
		getCbGenerarCodigoBarras().setEnabled(false);
		
		//cargo los combos por los caules se va a realizar la búsqueda
		getCmbEstadoGenerico().addItem(ESTADO_ACTIVO);
		getCmbEstadoGenerico().addItem(ESTADO_INACTIVO);
		
		getCmbEstadoProducto().addItem(ESTADO_ACTIVO);
		getCmbEstadoProducto().addItem(ESTADO_INACTIVO);
		
		cargarComboLinea();
		cargarComboFamiliaGenerico();
		cargarComboTipoProducto();
		cargarComboMarca();
		
		getCmbServicio().addItem(OPCION_NO);
		getCmbServicio().addItem(OPCION_SI);
		
		//seteo a null los combos
		getCmbEstadoGenerico().setSelectedItem(null);
		getCmbEstadoProducto().setSelectedItem(null);
		getCmbLinea().setSelectedItem(null);
		getCmbFamiliaGenerico().setSelectedItem(null);
		getCmbTipoProducto().setSelectedItem(null);
		getCmbServicio().setSelectedItem(null);
		getCmbMarca().setSelectedItem(null);
		getCmbModelo().setSelectedItem(null);
		
		getTxtCodigo().grabFocus();
		this.getJtpGenerico().setSelectedIndex(0);
		
	}
	
	public void showSaveMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getCmbEstadoGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setBackground(Parametros.saveUpdateColor);
		getCmbServicio().setBackground(Parametros.saveUpdateColor);
		getCmbLinea().setBackground(Parametros.saveUpdateColor);
		getCmbFamiliaGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipoProducto().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setSelectedIndex(0);
		setSaveMode();
		clean();
		//initListeners();
		//habilito los campos de texto de generico
		getTxtCodigo().setEnabled(true);
		getTxtFechaCreacionGenerico().setText(Utilitarios.fechaAhora());
		getTxtFechaCreacionGenerico().setEditable(false);
		getTxtFechaCreacionGenerico().setEnabled(true);
		getTxtReferencia().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtNombreGenerico().setEnabled(true);
		getTxtAbreviado().setEnabled(true);
		getTxtPartidaArancelaria().setEnabled(true);
		getTxtIva().setEnabled(true);
		getTxtIce().setEnabled(true);
		getTxtOtroImpuesto().setEnabled(true);
		getCmbMedida().setEnabled(true);
		
		//habilito los campos de texto de producto
		getTxtFechaCreacionProducto().setText(Utilitarios.fechaAhora());
		getTxtFechaCreacionProducto().setEditable(false);
		getTxtFechaCreacionProducto().setEnabled(true);
		getTxtProveedor().setEnabled(true);
		getTxtSubproveedor().setEditable(true);
		getTxtCodigoProducto().setEditable(false);
		getTxtProveedor().setEditable(false);
		getTxtCodigoBarras().setEnabled(true);
		getTxtCosto().setEnabled(true);
		getTxtMargenMinimo().setEnabled(true);
		getTxtRebate().setEnabled(true);
		getTxtPesoBruto().setEnabled(true);
		getTxtPesoNeto().setEnabled(false);
		
		//habilito los combos de generico
		getCmbTipoProducto().setEnabled(true);
		getCmbFamiliaGenerico().setEnabled(true);
		getCmbLinea().setEnabled(true);
		getCmbMedida().setEnabled(true);
		getCmbCambiaDescripcion().setEnabled(true);
		getCmbAfectaStock().setEnabled(true);
		getCmbUsaLote().setEnabled(true);
		getCmbEsInventario().setEnabled(true);
		getCmbSerie().setEnabled(true);
		getCmbServicio().setEnabled(true);
		getCmbEstadoGenerico().setEnabled(true);
		getCmbCobraIva().setEnabled(true);
		getCmbCobraIvaCliente().setEnabled(true);
		getCmbCobraIce().setEnabled(true);
		getCmbAceptaDescuento().setEnabled(true);
		getCmbMarca().setEnabled(true);
		getCmbModelo().setEnabled(true);
		
		//habilito los combos de producto
		getCmbPresentacionId().setEnabled(true);
		getCmbOrigenProducto().setEnabled(true);
		getCmbAceptaPromocion().setEnabled(true);
		getCmbPermiteVenta().setEnabled(true);
		getCmbAceptaDevolucion().setEnabled(true);
		getCmbCambioPrecio().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getCmbColor().setEnabled(true);
		getCbGenerarCodigoBarras().setEnabled(true);
		
		//cargo el listener para la tabla
		getTblProductos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Manejo los eventos de Buscar Proveedor
		cargarCombos();
		
		getJtpGenerico().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getCmbEstadoGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setBackground(Parametros.saveUpdateColor);
		getCmbServicio().setBackground(Parametros.saveUpdateColor);
		getCmbLinea().setBackground(Parametros.saveUpdateColor);
		getCmbFamiliaGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipoProducto().setBackground(Parametros.saveUpdateColor);
		
		setUpdateMode();
		//habilito los campos de texto de generico
		getTxtCodigo().setEnabled(false);
		getTxtFechaCreacionGenerico().setEditable(false);
		getTxtFechaCreacionGenerico().setEnabled(true);
		getTxtReferencia().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtNombreGenerico().setEnabled(true);
		getTxtAbreviado().setEnabled(true);
		getTxtPartidaArancelaria().setEnabled(true);
		getTxtIva().setEnabled(true);
		getTxtIce().setEnabled(true);
		getTxtOtroImpuesto().setEnabled(true);
		
		//habilito los campos de texto de producto
		getTxtFechaCreacionProducto().setEditable(false);
		getTxtFechaCreacionProducto().setEnabled(true);
		getTxtProveedor().setEnabled(true);
		getTxtProveedor().setEditable(false);
		getTxtSubproveedor().setEditable(true);
		getTxtCodigoBarras().setEnabled(true);
		
		if("N".equals(genericoIf.getUsaLote())){
			this.getTxtCodigoBarras().setEnabled(false);
		}
		else{
			this.getTxtCodigoBarras().setEnabled(true);
		}
		
		getTxtCosto().setEnabled(true);
		getTxtMargenMinimo().setEnabled(true);
		getTxtRebate().setEnabled(true);
		getTxtPesoBruto().setEnabled(true);
		getTxtPesoNeto().setEnabled(true);
		
		//habilito los combos de generico
		getCmbTipoProducto().setEnabled(true);
		getCmbFamiliaGenerico().setEnabled(true);
		getCmbLinea().setEnabled(true);
		
		validateCmbMedida();
		validateCmbMarca();
		
		getCmbCambiaDescripcion().setEnabled(true);
		getCmbAfectaStock().setEnabled(true);
		getCmbUsaLote().setEnabled(true);
		getCmbEsInventario().setEnabled(true);
		getCmbSerie().setEnabled(true);
		getCmbServicio().setEnabled(true);
		getCmbEstadoGenerico().setEnabled(true);
		getCmbCobraIva().setEnabled(true);
		getCmbCobraIvaCliente().setEnabled(true);
		getCmbCobraIce().setEnabled(true);
		getCmbAceptaDescuento().setEnabled(true);
		//getCmbMarca().setEnabled(true);
		//getCmbModelo().setEnabled(true);
		
		//habilito los combos de producto
		getCmbOrigenProducto().setEnabled(true);
		getCmbAceptaPromocion().setEnabled(true);
		getCmbPermiteVenta().setEnabled(true);
		getCmbAceptaDevolucion().setEnabled(true);
		getCmbCambioPrecio().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getCmbColor().setEnabled(true);
		getCbGenerarCodigoBarras().setEnabled(true);
		
		this.getBtnBuscarProveedor().grabFocus();
		this.getJtpGenerico().setSelectedIndex(0);
	}
	
	private void validateCmbMedida() {
		if(getCmbServicio().getSelectedIndex() >= 0){
			if(getCmbServicio().getSelectedItem().equals(OPCION_SI)){
				getCmbMedida().setSelectedIndex(-1);
				getCmbMedida().setEnabled(false);
				cleanProducto();
				cargarCombosProducto();
				getCmbPresentacionId().setSelectedIndex(-1);
				getCmbPresentacionId().setEnabled(false);
			}else{
				getCmbMedida().setEnabled(true);
				getCmbPresentacionId().setEnabled(true);
			}
		}
	}
	
	private void validateCmbMarca() {
		if(getCmbServicio().getSelectedIndex() >= 0){
			if(getCmbServicio().getSelectedItem().equals(OPCION_SI)){
				getCmbMarca().setSelectedIndex(-1);
				getCmbMarca().setEnabled(false);
				cleanProducto();
				cargarCombosProducto();
				getCmbModelo().setSelectedIndex(-1);
				getCmbModelo().setEnabled(false);
			}else{
				getCmbMarca().setEnabled(true);
				getCmbModelo().setEnabled(true);
			}
		}
	}
	
	public void  getSelectedObject(Object objeto) {
		setUpdateMode();
		genericoIf = (GenericoIf) objeto;
		getCmbEstadoGenerico().removeAllItems();
		getCmbEstadoProducto().removeAllItems();
		getCmbLinea().removeAllItems();
		getCmbFamiliaGenerico().removeAllItems();
		getCmbServicio().removeAllItems();
		cargarCombos();
		
		try {
			if (genericoIf.getFechaCreacion() != null)
				getTxtFechaCreacionGenerico().setText(Utilitarios.getFechaUppercase(genericoIf.getFechaCreacion()));
			getTxtCodigo().setText(genericoIf.getCodigo());
			getTxtReferencia().setText(genericoIf.getReferencia());
			getTxtNombre().setText(genericoIf.getNombre());
			getTxtNombreGenerico().setText(genericoIf.getNombreGenerico());
			getTxtAbreviado().setText(genericoIf.getAbreviado());
			getTxtPartidaArancelaria().setText(genericoIf.getPartidaArancelaria());
			getTxtIva().setText(genericoIf.getIva().toString());
			getTxtIce().setText(genericoIf.getIce().toString());
			getTxtOtroImpuesto().setText(genericoIf.getOtroImpuesto().toString());
			
			if (genericoIf.getLineaId() != null) {
				LineaIf lineaIf = SessionServiceLocator.getLineaSessionService().getLinea(genericoIf.getLineaId());
				getCmbLinea().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbLinea(), lineaIf.getId()));
			}
			
			if (genericoIf.getFamiliaGenericoId() != null) {
				FamiliaGenericoIf familiaGenericoIf = SessionServiceLocator.getFamiliaGenericoSessionService().getFamiliaGenerico(genericoIf.getFamiliaGenericoId());
				getCmbFamiliaGenerico().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFamiliaGenerico(), familiaGenericoIf.getId()));
			}
			
			if (genericoIf.getTipoproductoId() != null) {
				TipoProductoIf tipoProductoIf = SessionServiceLocator.getTipoProductoSessionService().getTipoProducto(genericoIf.getTipoproductoId());
				getCmbTipoProducto().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProducto(), tipoProductoIf.getId()));
			}
			
			/*if (genericoIf.getMarcaId() != null) {
				MarcaProductoIf marca = getMarcaProductoSessionService().getMarcaProducto(genericoIf.getMarcaId());
				getCmbMarca().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMarca(), marca.getId()));
			}
			
			if (genericoIf.getModeloId() != null) {
				ModeloIf modelo = getModeloSessionService().getModelo(genericoIf.getModeloId());
				getCmbModelo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModelo(), modelo.getId()));
			}*/
			
			if ((OPCION_SI.substring(0, 1)).equals(genericoIf.getServicio()))
				getCmbServicio().setSelectedItem(OPCION_SI);
			else
				getCmbServicio().setSelectedItem(OPCION_NO);
			
			if (getCmbServicio().getSelectedItem().equals(OPCION_NO)) {
				MedidaIf medidaIf = SessionServiceLocator.getMedidaSessionService().getMedida(genericoIf.getMedidaId());
				getCmbMedida().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMedida(), medidaIf.getId()));
				getCmbMedida().setEnabled(true);
				getCmbPresentacionId().setEnabled(true);
			} else {
				getCmbMedida().setSelectedIndex(-1);
				getCmbMedida().setEnabled(false);
				getCmbPresentacionId().setSelectedIndex(-1);
				getCmbPresentacionId().setEnabled(false);
			}
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCobraIva()))
				getCmbCobraIva().setSelectedItem(OPCION_SI);
			else
				getCmbCobraIva().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCobraIvaCliente()))
				getCmbCobraIvaCliente().setSelectedItem(OPCION_SI);
			else
				getCmbCobraIvaCliente().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getAceptaDescuento()))
				getCmbAceptaDescuento().setSelectedItem(OPCION_SI);
			else
				getCmbAceptaDescuento().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getUsaLote()))
				getCmbUsaLote().setSelectedItem(OPCION_SI);
			else
				getCmbUsaLote().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getLlevaInventario()))
				getCmbEsInventario().setSelectedItem(OPCION_SI);
			else
				getCmbEsInventario().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCobraIce()))
				getCmbCobraIce().setSelectedItem(OPCION_SI);
			else
				getCmbCobraIce().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCambioDescripcion()))
				getCmbCambiaDescripcion().setSelectedItem(OPCION_SI);
			else
				getCmbCambiaDescripcion().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getAfectastock()))
				getCmbAfectaStock().setSelectedItem(OPCION_SI);
			else
				getCmbAfectaStock().setSelectedItem(OPCION_NO);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getSerie()))
				getCmbSerie().setSelectedItem(OPCION_SI);
			else
				getCmbSerie().setSelectedItem(OPCION_NO);
			
			if((ESTADO_ACTIVO.substring(0, 1)).equals(genericoIf.getEstado()))
				getCmbEstadoGenerico().setSelectedItem(ESTADO_ACTIVO);
			else
				getCmbEstadoGenerico().setSelectedItem(ESTADO_INACTIVO);
			
			if(genericoIf.getMediosProduccion().equals(TIPO_MEDIOS))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_MEDIOS);
			else if(genericoIf.getMediosProduccion().equals(TIPO_PRODUCCION))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PRODUCCION);
			else if(genericoIf.getMediosProduccion().equals(TIPO_GASTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_GASTO);
			else if (genericoIf.getMediosProduccion().equals(TIPO_OTRO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_OTRO);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		try {
			modelProducto = (DefaultTableModel) getTblProductos().getModel();
			Collection listaPlantillaDetalle = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(genericoIf.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			
			while(it.hasNext()){
				ProductoIf productoIf = (ProductoIf) it.next();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				PresentacionIf presentacion = null;
				if(productoIf.getPresentacionId() != null)
					presentacion = GeneralUtil.verificarMapaPresentacion(mapaPresentacion,productoIf.getPresentacionId());
				
				String fecha = Utilitarios.getFechaUppercase(productoIf.getFechaCreacion());
				getTxtFechaCreacionGenerico().setText(fecha);
				productoColeccion.add(productoIf);
				
				if (productoIf.getProveedorId() != null) {
					ClienteOficinaIf proveedor  = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
					//filaPlantillaDetalle.add(proveedor.getCodigo() + " - " + ((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
					filaPlantillaDetalle.add(proveedor.getCodigo() + " - " + proveedor.getDescripcion() );
				} else
					filaPlantillaDetalle.add("NO TIENE PROVEEDOR");
				
				if(productoIf.getPresentacionId() != null)
					filaPlantillaDetalle.add(presentacion.getNombre());
				else
					filaPlantillaDetalle.add("");
				
				if(productoIf.getCosto()!=null)
					filaPlantillaDetalle.add(formatoDecimal.format(productoIf.getCosto().doubleValue()));
				else
					filaPlantillaDetalle.add("0");
				//**********
				filaPlantillaDetalle.add(fecha);
				if ((ESTADO_ACTIVO.substring(0, 1)).equals(productoIf.getEstado()))
					filaPlantillaDetalle.add(ESTADO_ACTIVO);
				else
					filaPlantillaDetalle.add(ESTADO_INACTIVO);
				modelProducto.addRow(filaPlantillaDetalle);
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		this.showUpdateMode();
	}
	
	public void cargarCombos(){
		cargarComboLinea();
		cargarComboFamiliaGenerico();
		cargarComboTipoProducto();
		cargarComboMedida();
		getCmbCambiaDescripcion().addItem(OPCION_SI);
		getCmbCambiaDescripcion().addItem(OPCION_NO);
		getCmbAfectaStock().addItem(OPCION_SI);
		getCmbAfectaStock().addItem(OPCION_NO);
		getCmbSerie().addItem(OPCION_SI);
		getCmbSerie().addItem(OPCION_NO);
		getCmbUsaLote().addItem(OPCION_SI);
		getCmbUsaLote().addItem(OPCION_NO);
		getCmbEsInventario().addItem(OPCION_SI);
		getCmbEsInventario().addItem(OPCION_NO);
		getCmbCobraIva().addItem(OPCION_SI);
		getCmbCobraIva().addItem(OPCION_NO);
		getCmbCobraIvaCliente().addItem(OPCION_SI);
		getCmbCobraIvaCliente().addItem(OPCION_NO);
		getCmbCobraIce().addItem(OPCION_SI);
		getCmbCobraIce().addItem(OPCION_NO);
		getCmbAceptaDescuento().addItem(OPCION_SI);
		getCmbAceptaDescuento().addItem(OPCION_NO);
		getCmbServicio().addItem(OPCION_NO);
		getCmbServicio().addItem(OPCION_SI);
		getCmbEstadoGenerico().addItem(ESTADO_ACTIVO);
		getCmbEstadoGenerico().addItem(ESTADO_INACTIVO);
		cargarCombosProducto();
	}
	
	private void cargarCombosProducto() {
		cargarComboPresentacionId();
		cargarComboColor();
		getCmbOrigenProducto().addItem(ORIGEN_LOCAL);
		getCmbOrigenProducto().addItem(ORIGEN_IMPORTADO);
		getCmbAceptaPromocion().addItem(OPCION_SI);
		getCmbAceptaPromocion().addItem(OPCION_NO);
		getCmbPermiteVenta().addItem(OPCION_SI);
		getCmbPermiteVenta().addItem(OPCION_NO);
		getCmbAceptaDevolucion().addItem(OPCION_SI);
		getCmbAceptaDevolucion().addItem(OPCION_NO);
		getCmbCambioPrecio().addItem(OPCION_SI);
		getCmbCambioPrecio().addItem(OPCION_NO);
		getCmbEstadoProducto().addItem(ESTADO_ACTIVO);
		getCmbEstadoProducto().addItem(ESTADO_INACTIVO);
	}
	
	private void cargarComboLinea(){
		try {
			List lineas = (List)SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbLinea(), lineas);
			if (idLineaNinguno != null && idLineaNinguno.compareTo(0L) != 0) {
				getCmbLinea().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbLinea(),idLineaNinguno));
				getCmbLinea().validate();
				getCmbLinea().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<FamiliaGenericoIf> ordenadorFamiliaGenericoPorNombre = new Comparator<FamiliaGenericoIf>(){
		public int compare(FamiliaGenericoIf o1, FamiliaGenericoIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboFamiliaGenerico(){
		try {
			List familiasGenerico = (List) SessionServiceLocator.getFamiliaGenericoSessionService().findFamiliaGenericoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort((ArrayList)familiasGenerico, ordenadorFamiliaGenericoPorNombre);
			refreshCombo(getCmbFamiliaGenerico(), familiasGenerico);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<TipoProductoIf> ordenadorTipoProductoPorNombre = new Comparator<TipoProductoIf>(){
		public int compare(TipoProductoIf o1, TipoProductoIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboTipoProducto(){
		try {
			List tiposProducto = (List) SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort((ArrayList)tiposProducto, ordenadorTipoProductoPorNombre);
			refreshCombo(getCmbTipoProducto(), tiposProducto);
			if (idTipoProductoNinguno != null && idTipoProductoNinguno.compareTo(0L) != 0) {
				getCmbTipoProducto().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProducto(),idTipoProductoNinguno));
				getCmbTipoProducto().validate();
				getCmbTipoProducto().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMedida(){
		try {
			List medidas = (List) (ArrayList) SessionServiceLocator.getMedidaSessionService().findMedidaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbMedida(), medidas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboPresentacionId(){
		try {
			List presentacionesId = (List) SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbPresentacionId(), presentacionesId);
			if (idPresentacionNinguno != null && idPresentacionNinguno.compareTo(0L) != 0) {
				getCmbPresentacionId().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPresentacionId(),idPresentacionNinguno));
				getCmbPresentacionId().validate();
				getCmbPresentacionId().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMarca(){
		try {
			if (proveedorIf != null) {
				Map parameterMap = new HashMap();
				//parameterMap.put("clienteId", proveedorIf.getClienteId());
				parameterMap.put("empresaId", Parametros.getIdEmpresa());
				parameterMap.put("tipo", TIPO_PROVEEDOR);
				List marcas = (List) SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByQuery(parameterMap);
				refreshCombo(getCmbMarca(), marcas);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboModelo(Long marcaId){
		try {
			List modelos = (List) SessionServiceLocator.getModeloSessionService().findModeloByMarcaId(marcaId);
			if (idModeloNinguno != null && idModeloNinguno.compareTo(0L) != 0)
				modelos.add(SessionServiceLocator.getModeloSessionService().getModelo(idModeloNinguno));
			refreshCombo(getCmbModelo(), modelos);
			if (idModeloNinguno != null && idModeloNinguno.compareTo(0L) != 0) {
				getCmbModelo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModelo(),idModeloNinguno));
				getCmbModelo().validate();
				getCmbModelo().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboColor(){
		try {
			List colores = (List) SessionServiceLocator.getColorSessionService().getColorList();
			refreshCombo(getCmbColor(), colores);
			if (idColorNinguno != null && idColorNinguno.compareTo(0L) != 0) {
				getCmbColor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbColor(),idColorNinguno));
				getCmbColor().validate();
				getCmbColor().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean validateFields() {
		String strIva = Utilitarios.removeDecimalFormat(this.getTxtIva().getText());
		String strIce = Utilitarios.removeDecimalFormat(this.getTxtIce().getText());
		String strOtroImpuesto = Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText());
		
		/*if ("".equals(this.getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar el Código del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtReferencia().getText())) {
			SpiritAlert.createAlert("Debe ingresar la Referencia del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getTxtReferencia().grabFocus();
			return false;
		}*/
		
		if ("".equals(this.getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar el Nombre del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if(getCmbTipo().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbTipo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtFechaCreacionGenerico().getText())) {
			SpiritAlert.createAlert("Debe ingresar la Fecha de Creación del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getTxtFechaCreacionGenerico().grabFocus();
			return false;
		}
		
		if (this.getCmbEstadoGenerico().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Estado del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbEstadoGenerico().grabFocus();
			return false;
		}
		
		if (this.getCmbUsaLote().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe especificar si el genérico Usa o no Lote !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbUsaLote().grabFocus();
			return false;
		}
		
		if (this.getCmbEsInventario().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe especificar si el genérico lleva o no inventario !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbEsInventario().grabFocus();
			return false;
		}
		
		if (this.getCmbTipoProducto().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Tipo de Producto del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbTipoProducto().grabFocus();
			return false;
		}
		
		if (this.getCmbFamiliaGenerico().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la Familia del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbFamiliaGenerico().grabFocus();
			return false;
		}
		
		if (this.getCmbLinea().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la Línea del genérico !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbLinea().grabFocus();
			return false;
		}
		
		if(getCmbServicio().getSelectedItem().equals(OPCION_NO)){
			if (this.getCmbMedida().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la Medida del genérico !!!", SpiritAlert.WARNING);
				this.getJtpGenerico().setSelectedIndex(0);
				getCmbMedida().grabFocus();
				return false;
			}
		}
		
		if (this.getCmbCobraIva().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe especificar si el genérico Cobra o no IVA !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbCobraIva().grabFocus();
			return false;
		}
		
		if (this.getCmbCobraIvaCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe especificar si el genérico Cobra o no IVA para el Cliente !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbCobraIvaCliente().grabFocus();
			return false;
		}
		
		if (this.getCmbServicio().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe especificar si el genérico Es o no de Servicio !!!", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbServicio().grabFocus();
			return false;
		}
		
		if(!strIva.equals("")){
			if (Double.parseDouble(strIva) > 100) {
				SpiritAlert.createAlert("El valor del IVA debe ser menor o igual a 100 !!", SpiritAlert.WARNING);
				getTxtIva().grabFocus();
				return false;
			}
		}
		if(!strIce.equals("")){
			if (Double.parseDouble(strIce) > 100) {
				SpiritAlert.createAlert("El valor del ICE debe ser menor o igual a 100 !!", SpiritAlert.WARNING);
				getTxtIce().grabFocus();
				return false;
			}
		}
		if(!strOtroImpuesto.equals("")){
			if (Double.parseDouble(strOtroImpuesto) > 100) {
				SpiritAlert.createAlert("El valor de Otro Impuesto debe ser menor o igual a 100 !!", SpiritAlert.WARNING);
				getTxtOtroImpuesto().grabFocus();
				return false;
			}
		}
		
		return true;
	}
	
	public boolean validateFieldsDetalle(){
		if (proveedorIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un proveedor", SpiritAlert.WARNING);
			getBtnBuscarProveedor().grabFocus();
			return false;
		}
		
		try {
			if(genericoIf.getServicio().equals("S")){
				ClienteOficinaIf proveedorSeleccionado = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(proveedorIf.getId());
				Long proveedorSeleccionadoId = proveedorSeleccionado.getId();
				
				for(int i=0; i<productoColeccion.size(); i++){
					ProductoIf producto = productoColeccion.get(i);
					if(proveedorSeleccionadoId.compareTo(producto.getProveedorId()) == 0){
						SpiritAlert.createAlert("El Proveedor ya se encuentra ingresado !", SpiritAlert.WARNING);
						return false;
					}
				}
			}
			
			/*ClienteOficinaIf proveedorSeleccionado = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(proveedorIf.getId());
			Long proveedorSeleccionadoId = proveedorSeleccionado.getId();
			
			for(int i=0; i<productoColeccion.size(); i++){
				ProductoIf producto = productoColeccion.get(i);
				if(proveedorSeleccionadoId.compareTo(producto.getProveedorId()) == 0){
					if((subProveedorSeleccionado.equals("") && producto.getSubproveedor() == null) || subProveedorSeleccionado.equals(producto.getSubproveedor())){
						if((presentacionSeleccionadoId == null && producto.getPresentacionId() == null) || ((presentacionSeleccionadoId != null) && presentacionSeleccionadoId.equals(producto.getPresentacionId()))){
							SpiritAlert.createAlert("El Proveedor ya se encuentra ingresado !", SpiritAlert.WARNING);
							return false;
						}
					}
				}
			}
			String subProveedorSeleccionado = getTxtSubproveedor().getText();
			PresentacionIf presentacionSeleccionado = null;
			if(getCmbPresentacionId().isEnabled()){
				presentacionSeleccionado = (PresentacionIf) getCmbPresentacionId().getSelectedItem();
			}
			Long presentacionSeleccionadoId = null;
			if(presentacionSeleccionado != null){
				presentacionSeleccionadoId = presentacionSeleccionado.getId();
			}
			
			for(int i=0; i<productoColeccion.size(); i++){
				ProductoIf producto = productoColeccion.get(i);
				if(proveedorSeleccionadoId.compareTo(producto.getProveedorId()) == 0){
					if((subProveedorSeleccionado.equals("") && producto.getSubproveedor() == null) || subProveedorSeleccionado.equals(producto.getSubproveedor())){
						if((presentacionSeleccionadoId == null && producto.getPresentacionId() == null) || ((presentacionSeleccionadoId != null) && presentacionSeleccionadoId.equals(producto.getPresentacionId()))){
							SpiritAlert.createAlert("El Proveedor ya se encuentra ingresado !", SpiritAlert.WARNING);
							return false;
						}
					}
				}
			}
			*/
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} 			
		
		/*for(int i=0; i<productoColeccion.size(); i++){
			Long proveedorTempId = productoColeccion.get(i).getProveedorId(); 
			if(proveedorIf.getId().compareTo(proveedorTempId) == 0){
				parametrizar
				SpiritAlert.createAlert("El Proveedor ya se encuentra asociado al producto", SpiritAlert.WARNING);
				return false;
			}
		}*/
		
		if(getCmbServicio().getSelectedItem().equals(OPCION_SI)) {
			getCmbPresentacionId().setSelectedIndex(-1);
			getCmbPresentacionId().setEnabled(false);
		}else{
			if (getCmbPresentacionId().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar una Presentación para el producto !!!", SpiritAlert.WARNING);
				getCmbPresentacionId().grabFocus();
				return false;
			}
		}	
		
		return true;
	}
	
	public void agregarDetalle(){
		try{
			if (proveedorIf != null){
				modelProducto = (DefaultTableModel) getTblProductos().getModel();
				Vector<String> filaDetalle = new Vector<String>();
				
				if (validateFieldsDetalle()){
					ProductoData data = new ProductoData();
					
					data.setCodigo(this.getTxtCodigoProducto().getText());
					
					if(this.getCmbPresentacionId().getSelectedItem()!=null)
						data.setPresentacionId(((PresentacionIf) this.getCmbPresentacionId().getSelectedItem()).getId());
					if (getCmbMarca().getSelectedItem()!=null) {
						MarcaProductoIf marcaProducto = (MarcaProductoIf) getCmbMarca().getSelectedItem();
						data.setMarcaId(marcaProducto.getId());
					}
						
					if (getCmbModelo().getSelectedItem()!=null) {
						ModeloIf modelo = (ModeloIf) getCmbModelo().getSelectedItem();
						data.setModeloId(modelo.getId());
					}
									
					data.setProveedorId(proveedorIf.getId());
					//data.setFechaCreacion(new java.sql.Date(fechaCreacion.getYear(),fechaCreacion.getMonth(),fechaCreacion.getDate()));
					data.setFechaCreacion( DateHelperClient.getTimeStamp(fechaCreacion) );
					data.setOrigenProducto(this.getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
					if (!getCbGenerarCodigoBarras().isSelected())
						data.setCodigoBarras(this.getTxtCodigoBarras().getText());
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()).equals(""))
						data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()))));
					else
						data.setCosto(BigDecimal.valueOf(Double.parseDouble("0")));
					
					data.setAceptapromocion(this.getCmbAceptaPromocion().getSelectedItem().toString().substring(0, 1));
					data.setPermiteventa(this.getCmbPermiteVenta().getSelectedItem().toString().substring(0, 1));
					data.setAceptadevolucion(this.getCmbAceptaDevolucion().getSelectedItem().toString().substring(0, 1));
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtMargenMinimo().getText()).equals(""))
						data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtMargenMinimo().getText()))));
					else
						data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble("0")));
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtRebate().getText()).equals(""))
						data.setRebate(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtRebate().getText()))));
					else
						data.setRebate(BigDecimal.valueOf(Double.parseDouble("0")));
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtPesoBruto().getText()).equals(""))
						data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPesoBruto().getText()))));
					else
						data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble("0")));
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtPesoNeto().getText()).equals(""))
						data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPesoNeto().getText()))));
					else
						data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble("0")));
					
					data.setCambioprecio(this.getCmbCambioPrecio().getSelectedItem().toString().substring(0, 1));
					data.setEstado(this.getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
					data.setSubproveedor(getTxtSubproveedor().getText());
					
					if (getCmbColor().getSelectedItem()!=null) {
						ColorIf color = (ColorIf) getCmbColor().getSelectedItem();
						data.setColorId(color.getId());
					}
					
					if (getCbGenerarCodigoBarras().isSelected())
						data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
					else
						data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
					
					// Agregar a la coleccion de PresupuestoColeccion para grabar al final toda la coleccion
					productoColeccion.add(data);
					// Agrega los valores al registro que va  ser añadido  a la tabla.
					//agrega informacion  visual a la tabla de detalle para el usuario.
					
					filaDetalle.add(getTxtProveedor().getText());
					
					if (getCmbPresentacionId().getSelectedItem() != null) 
						filaDetalle.add(((PresentacionIf) this.getCmbPresentacionId().getSelectedItem()).getNombre());
					else 
						filaDetalle.add("");
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()).equals(""))
						filaDetalle.add(formatoDecimal.format(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()))));
					else
						filaDetalle.add("0.00");
					
					filaDetalle.add(Utilitarios.getFechaUppercase(fechaCreacion));
					filaDetalle.add(this.getCmbEstadoProducto().getSelectedItem().toString());
					
					modelProducto.addRow(filaDetalle);
					cleanProducto();
					cargarCombosProducto();
					getTxtCodigoProducto().setEditable(false);
					getTxtCodigoProducto().grabFocus();
				}
			}else{
				SpiritAlert.createAlert("Debe seleccionar un Proveedor!", SpiritAlert.WARNING);
				getBtnBuscarProveedor().grabFocus();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo agregar el Detalle del Producto a la lista !!!",SpiritAlert.ERROR);
		}
	}
	
	private void actualizarDetalle() {
		if (getTblProductos().getSelectedRow()!=-1) {
			int i = getTblProductos().getSelectedRow();
			//si esta en modo update lo actualiza directamente de la base
			if(getMode()==SpiritMode.UPDATE_MODE || getMode()==SpiritMode.SAVE_MODE ){
				if (validateFieldsDetalle()){
					/*int filaSeleccionada = getTblProductos().getSelectedRow();
					ProductoIf productoSeleccionado = productoColeccion.get(filaSeleccionada);
					for (ProductoIf producto: productoColeccion){
						if ( getTxtCodigoProducto().getText().equals(producto.getCodigo())
								&& producto!=productoSeleccionado){
							SpiritAlert.createAlert("C\u00f3digo repetido", SpiritAlert.WARNING);
							return;
						}
					}*/
					
					ProductoIf productoIf = (ProductoIf) productoColeccion.get(i);
					//seteo los valores con los caules voy a realizar la validación del elemento de la colección
					BigDecimal costoB = null ;
					String costoT = null ;
					BigDecimal costo = null;

					if(getMode()==SpiritMode.SAVE_MODE){
						//en save todos tiene el formato decimal
						costoB = productoIf.getCosto();
						costoT = (String)getTblProductos().getModel().getValueAt(getTblProductos().getSelectedRow(),2);
						costo = BigDecimal.valueOf(Double.valueOf(costoT));
					}

					if(getMode()==SpiritMode.UPDATE_MODE){
						//en update el formato de los valores del objeto no son decimal, los paso al formato decimal
						//String costoTemp = formatoDecimal.format(productoIf.getCosto());
						costoB = productoIf.getCosto();
						costoT = (String)getTblProductos().getModel().getValueAt(getTblProductos().getSelectedRow(),2);
						costo = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(costoT)));
					}

					//cargo los nuevos datos al objeto
					ProductoData data = new ProductoData();
					//String fecha;
					//DateFormat dateFormatter;
					//dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
					//fecha = dateFormatter.format(productoIf.getFechaCreacion());
					String fecha = Utilitarios.getFechaUppercase(productoIf.getFechaCreacion());

					if(!(productoIf.getId()!= null)){
						//seteo con los nuevos valores al objeto del producto
						data.setCodigo(getTxtCodigoProducto().getText());            					
						if(getCmbPresentacionId().getSelectedItem()!=null)
							data.setPresentacionId(((PresentacionIf) getCmbPresentacionId().getSelectedItem()).getId());
						if (getCmbMarca().getSelectedItem()!=null) {
							MarcaProductoIf marcaProducto = (MarcaProductoIf) getCmbMarca().getSelectedItem();
							data.setMarcaId(marcaProducto.getId());
						}
						if (getCmbModelo().getSelectedItem()!=null) {
							ModeloIf modelo = (ModeloIf) getCmbModelo().getSelectedItem();
							data.setModeloId(modelo.getId());
						}
						data.setProveedorId(proveedorIf.getId());
						data.setFechaCreacion(productoIf.getFechaCreacion());
						data.setOrigenProducto(getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
						data.setCodigoBarras(getTxtCodigoBarras().getText());
						if(!Utilitarios.removeDecimalFormat(getTxtCosto().getText()).equals(""))
							data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))));
						else
							data.setCosto(BigDecimal.valueOf(Double.parseDouble("0")));
						data.setAceptapromocion(getCmbAceptaPromocion().getSelectedItem().toString().substring(0, 1));
						data.setPermiteventa(getCmbPermiteVenta().getSelectedItem().toString().substring(0, 1));
						data.setAceptadevolucion(getCmbAceptaDevolucion().getSelectedItem().toString().substring(0, 1));
						if(!Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()).equals(""))
							data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()))));
						else
							data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble("0")));
						if(!Utilitarios.removeDecimalFormat(getTxtRebate().getText()).equals(""))
							data.setRebate(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRebate().getText()))));
						else
							data.setRebate(BigDecimal.valueOf(Double.parseDouble("0")));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()).equals(""))
							data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()))));
						else
							data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble("0")));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()).equals(""))
							data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()))));
						else
							data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble("0")));
						data.setCambioprecio(getCmbCambioPrecio().getSelectedItem().toString().substring(0, 1));
						data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
						data.setSubproveedor(getTxtSubproveedor().getText());
						if (getCmbColor().getSelectedItem()!=null) {
							ColorIf color = (ColorIf) getCmbColor().getSelectedItem();
							data.setColorId(color.getId());
						}
						if (getCbGenerarCodigoBarras().isSelected())
							data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
						else
							data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
						productoColeccion.set(i,data);
						//Actualizo en la tabla
						modelProducto.setValueAt(getTxtProveedor().getText(),getTblProductos().getSelectedRow(),0);
						if (getCmbPresentacionId().getSelectedItem() != null)
							modelProducto.setValueAt(((PresentacionIf) getCmbPresentacionId().getSelectedItem()).getNombre(),getTblProductos().getSelectedRow(),1);
						if("".equals(Utilitarios.removeDecimalFormat(getTxtCosto().getText())))
							modelProducto.setValueAt("0.00",getTblProductos().getSelectedRow(),2);
						else
							modelProducto.setValueAt(formatoDecimal.format(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))),getTblProductos().getSelectedRow(),2);
						modelProducto.setValueAt(fecha,getTblProductos().getSelectedRow(),3);
						modelProducto.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getTblProductos().getSelectedRow(),4);
					}

					if (productoIf.getId()!= null) {
						//seteo con los nuevos valores al objeto del producto
						data.setId(productoIf.getId());
						data.setCodigo(getTxtCodigoProducto().getText());
						if(getCmbPresentacionId().getSelectedItem()!=null)
							data.setPresentacionId(((PresentacionIf) getCmbPresentacionId().getSelectedItem()).getId());
						if (getCmbMarca().getSelectedItem()!=null) {
							MarcaProductoIf marcaProducto = (MarcaProductoIf) getCmbMarca().getSelectedItem();
							data.setMarcaId(marcaProducto.getId());
						}
						if (getCmbModelo().getSelectedItem()!=null) {
							ModeloIf modelo = (ModeloIf) getCmbModelo().getSelectedItem();
							data.setModeloId(modelo.getId());
						}
						data.setProveedorId(proveedorIf.getId());
						data.setFechaCreacion(productoIf.getFechaCreacion());
						data.setOrigenProducto(getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
						data.setCodigoBarras(getTxtCodigoBarras().getText());
						if(!Utilitarios.removeDecimalFormat(getTxtCosto().getText()).equals(""))
							data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))));
						data.setAceptapromocion(getCmbAceptaPromocion().getSelectedItem().toString().substring(0, 1));
						data.setPermiteventa(getCmbPermiteVenta().getSelectedItem().toString().substring(0, 1));
						data.setAceptadevolucion(getCmbAceptaDevolucion().getSelectedItem().toString().substring(0, 1));
						if(!Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()).equals(""))
							data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtRebate().getText()).equals(""))
							data.setRebate(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRebate().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()).equals(""))
							data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()).equals(""))
							data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()))));
						data.setCambioprecio(getCmbCambioPrecio().getSelectedItem().toString().substring(0, 1));
						data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
						data.setGenericoId(productoIf.getGenericoId());
						data.setSubproveedor(getTxtSubproveedor().getText());
						if (getCmbColor().getSelectedItem()!=null) {
							ColorIf color = (ColorIf) getCmbColor().getSelectedItem();
							data.setColorId(color.getId());
						}
						if (getCbGenerarCodigoBarras().isSelected())
							data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
						else
							data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
						productoColeccion.set(i,data);
						//Actualizo en la tabla
						modelProducto.setValueAt(getTxtProveedor().getText(),getTblProductos().getSelectedRow(),0);
						if (getCmbPresentacionId().getSelectedItem() != null) 
							modelProducto.setValueAt(((PresentacionIf) getCmbPresentacionId().getSelectedItem()).getNombre(),getTblProductos().getSelectedRow(),1);
						if("".equals(Utilitarios.removeDecimalFormat(getTxtCosto().getText())))
							modelProducto.setValueAt("0.00",getTblProductos().getSelectedRow(),2);
						else
							modelProducto.setValueAt(formatoDecimal.format(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))),getTblProductos().getSelectedRow(),2);
						modelProducto.setValueAt(fecha,getTblProductos().getSelectedRow(),3);
						modelProducto.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getTblProductos().getSelectedRow(),4);
					}
				}
			}
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de Producto a actualizar !!!",SpiritAlert.WARNING);
		}
		
		getTxtCodigoProducto().setText("");
		getTxtProveedor().setText("");
		proveedorIf = null;
		getTxtSubproveedor().setText("");
		getTxtCodigoBarras().setText("");
		getTxtCosto().setText("");	
		getTxtRebate().setText("");
		getTxtMargenMinimo().setText("");
		getTxtPesoBruto().setText("");
		getTxtPesoNeto().setText("");
		getTxtCodigoProducto().setEditable(false);
	}
	
	private void eliminarDetalle() {
		if (getTblProductos().getSelectedRow()!=-1) {
			if(getMode()==SpiritMode.UPDATE_MODE || getMode()==SpiritMode.SAVE_MODE ) {
				ProductoIf productoIf = (ProductoIf) productoColeccion.get(getTblProductos().getSelectedRow());
				if (productoIf.getId()!=null)
					productoRemovidoColeccion.add(productoIf);
				productoColeccion.remove(getTblProductos().getSelectedRow());
				modelProducto.removeRow(getTblProductos().getSelectedRow());
				/*if(productoIf.getId()!= null){
				 try {
				 getProductoSessionService().deleteProducto(productoIf.getId());
				 productoColeccion.remove(getTblProductos().getSelectedRow());
				 modelProducto.removeRow(getTblProductos().getSelectedRow());
				 } catch (Exception e) {
				 SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
				 }
				 } else {
				 productoColeccion.remove(getTblProductos().getSelectedRow());
				 modelProducto.removeRow(getTblProductos().getSelectedRow());
				 }*/
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de Producto a eliminar !!!",SpiritAlert.WARNING);
		}
		
		getTxtProveedor().setText("");
		getTxtCosto().setText("");	
		getTxtRebate().setText("");
		getTxtMargenMinimo().setText("");
		getTxtPesoBruto().setText("");
		getTxtPesoNeto().setText("");
		getTxtCodigoProducto().setEditable(false);
	}
	
	/*MouseListener oMouseReleasedTblProducto = new MouseAdapter() {
	 public void mouseReleased(MouseEvent evt) {
	 if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblProductos().getModel().getRowCount()>0) {
	 popupMenuProducto.show(evt.getComponent(), evt.getX(), evt.getY());
	 }
	 }
	 };*/
	
	//Action Listener del Boton Actualizar Presupuesto Detalle
	ActionListener oActionListenerActualizarProducto = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			actualizarDetalle();
		}
	};
	
	ActionListener oActionListenerEliminarProducto = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {		
			eliminarDetalle();
		}
	};
}