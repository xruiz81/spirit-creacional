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
import com.spirit.client.SpiritConstants;
import com.spirit.client.SpiritCursor;
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
import com.spirit.inventario.gui.panel.JPNuevoGenerico;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class NuevoGenericoModel extends JPNuevoGenerico {
	private static final long serialVersionUID = -311953778880431196L;
	protected int mode;
	java.util.Date fechaCreacion = new java.util.Date();
	protected ClienteIf proveedor;
	protected ClienteOficinaIf proveedorOficina;
	protected CiudadIf ciudad;
	DefaultTableModel modelProducto;
	Vector<ProductoIf> productoColeccion = new Vector<ProductoIf>();
	Vector<ProductoIf> productoRemovidoColeccion = new Vector<ProductoIf>();
	final JPopupMenu popupMenuProducto = new JPopupMenu();
	JMenuItem itemEliminarProducto;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 7;
	private static final int MAX_LONGITUD_ABREVIADO = 20;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_SUBPROVEEDOR = 300;
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
	
	public NuevoGenericoModel() {
		initKeyListeners();	
		initListeners();
		initVariables();
		setColumnWidthTable();
		showSaveMode();
		// Popup para borrar registros de la tabla de productos
		itemEliminarProducto = new JMenuItem("Eliminar");
		itemEliminarProducto.addActionListener(oActionListenerEliminarProducto);
		popupMenuProducto.add(itemEliminarProducto);
		getTblProductos().addMouseListener(oMouseAdapterTblProducto);
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
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblProductos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblProductos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
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
		
		// Validaciones para los campos de generico
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtAbreviado().addKeyListener(new TextChecker(MAX_LONGITUD_ABREVIADO));
		getTxtOtroImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_OTRO_IMPUESTO));
		getTxtOtroImpuesto().addKeyListener (new NumberTextFieldDecimal());
		// Validaciones para los campos de producto
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
						proveedorOficina = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						proveedor = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
						ciudad = (CiudadIf) SessionServiceLocator.getCiudadSessionService().getCiudad(proveedorOficina.getCiudadId());
						//getTxtProveedor().setText(ciudad.getCodigo() + " - " + proveedorOficina.getDescripcion());
						getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial() + " // " + ciudad.getNombre());
						
						if (getRbServicioNo().isSelected()) {
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
		
		getRbServicioSi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbServicioSi().isSelected()) {
					validateCmbMedida();
					validateCmbMarca();
					validateCmbColor();
					getRbLlevaInventarioNo().setSelected(true);
					getRbUsaLoteNo().setSelected(true);
					getRbTieneSerieNo().setSelected(true);
				}
			}
		});
		
		getRbServicioNo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbServicioNo().isSelected()) {
					validateCmbMedida();
					validateCmbMarca();
					validateCmbColor();
				}
			}
		});
		
		getRbLlevaInventarioSi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbLlevaInventarioSi().isSelected()) {
					getRbServicioNo().setSelected(true);
					validateCmbMedida();
					validateCmbMarca();
					validateCmbColor();
				}
			}
		});
		
		getRbUsaLoteSi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbUsaLoteSi().isSelected()) {
					getRbServicioNo().setSelected(true);
					validateCmbMedida();
					validateCmbMarca();
					validateCmbColor();
				}
			}
		});
		
		getRbTieneSerieSi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbTieneSerieSi().isSelected()) {
					getRbServicioNo().setSelected(true);
					validateCmbMedida();
					validateCmbMarca();
					validateCmbColor();
				}
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
				proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
				proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(proveedorOficina.getCiudadId());
				getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial() + " // " + ciudad.getNombre());
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
					getRbAceptaPromocionSi().setSelected(true);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getPermiteventa()))
					getRbPermiteVentaSi().setSelected(true);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getAceptadevolucion()))
					getRbAceptaDevolucionSi().setSelected(true);
				
				if((OPCION_SI.substring(0, 1)).equals(productoIf.getCambioprecio()))
					getRbCambioPrecioSi().setSelected(true);
				
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
		
		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", getTxtNombre().getText() + "%");
		else
			aMap.put("nombre", "%");
		
		if (getCmbEstadoGenerico().getSelectedItem() != null)
			aMap.put("estado", getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		
		if (getCmbTipo().getSelectedItem() != null)
			aMap.put("mediosProduccion", getCmbTipo().getSelectedItem().toString().substring(0, 1));
		
		//aMap.put("servicio", getRbServicioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		
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
		data.setNombre(this.getTxtNombre().getText());
		data.setAbreviado(this.getTxtAbreviado().getText());
		data.setNombreGenerico(this.getTxtNombre().getText());
		data.setCambioDescripcion(getRbCambioDescripcionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setEmpresaId(Parametros.getIdEmpresa());
		data.setTipoproductoId(((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId());
		if(getRbServicioNo().isSelected())
			data.setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
		data.setUsaLote(getRbUsaLoteSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setLlevaInventario(getRbLlevaInventarioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setLineaId(((LineaIf) this.getCmbLinea().getSelectedItem()).getId());		
		data.setIva(BigDecimal.valueOf(Double.valueOf("0")));
		data.setIce(BigDecimal.valueOf(Double.valueOf("0")));
		if(SpiritConstants.getEmptyCharacter().equals(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText())))
			data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf("0")));
		else
			data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText()))));		
		data.setServicio(getRbServicioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setFamiliaGenericoId(((FamiliaGenericoIf) this.getCmbFamiliaGenerico().getSelectedItem()).getId());
		data.setSerie(getRbTieneSerieSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setAfectastock(getRbLlevaInventarioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setEstado(this.getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		data.setMediosProduccion(this.getCmbTipo().getSelectedItem().toString().substring(0, 1));
		data.setCobraIva(getRbCobraIvaProveedorSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setCobraIvaCliente(getRbCobraIvaClienteSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		data.setCobraIce(getRbCobraIceSi().isSelected()?OPCION_SI.substring(0, 1):OPCION_NO.substring(0,1));
		data.setAceptaDescuento(getRbAceptaDescuentoSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));		
		return data;
	}
	
	public void find() {
		setCursor(SpiritCursor.hourglassCursor);
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
				setCursor(SpiritCursor.normalCursor);
				SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			setCursor(SpiritCursor.normalCursor);
			SpiritAlert.createAlert("Error en la búsqueda de información",SpiritAlert.ERROR);
		}
		setCursor(SpiritCursor.normalCursor);
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
		genericoIf.setNombreGenerico(this.getTxtNombre().getText());
		genericoIf.setCambioDescripcion(getRbCambioDescripcionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setEmpresaId(Parametros.getIdEmpresa());
		genericoIf.setTipoproductoId(((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId());
		if(getRbServicioNo().isSelected())
			genericoIf.setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
		genericoIf.setUsaLote(getRbUsaLoteSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setLlevaInventario(getRbLlevaInventarioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setLineaId(((LineaIf) this.getCmbLinea().getSelectedItem()).getId());
		genericoIf.setIva(BigDecimal.valueOf(Double.valueOf("0")));
		genericoIf.setIce(BigDecimal.valueOf(Double.valueOf("0")));
		if("".equals(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText())))
			genericoIf.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf("0")));
		else
			genericoIf.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText()))));
		genericoIf.setServicio(getRbServicioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setFamiliaGenericoId(((FamiliaGenericoIf) this.getCmbFamiliaGenerico().getSelectedItem()).getId());
		genericoIf.setSerie(getRbTieneSerieSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setAfectastock(getRbLlevaInventarioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setEstado(this.getCmbEstadoGenerico().getSelectedItem().toString().substring(0, 1));
		genericoIf.setMediosProduccion(this.getCmbTipo().getSelectedItem().toString().substring(0, 1));
		genericoIf.setCobraIva(getRbCobraIvaProveedorSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setCobraIvaCliente(getRbCobraIvaClienteSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setCobraIce(getRbCobraIceSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
		genericoIf.setAceptaDescuento(getRbAceptaDescuentoSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
	}
	
	public boolean isEmpty() {
		if (SpiritConstants.getEmptyCharacter().equals(this.getTxtNombre().getText())
				&& (this.getCmbLinea().getSelectedItem() == null)
				&& (this.getCmbFamiliaGenerico().getSelectedItem() == null)
				&& (this.getCmbEstadoGenerico().getSelectedItem() == null))
			return true;
		else
			return false;
	}
	
	public void clean() {
		//Clean de los campos de texto de generico
		this.getTxtNombre().setText("");
		this.getTxtAbreviado().setText("");
		this.getTxtOtroImpuesto().setText("");
		//Clean de los combos de generico
		this.getCmbTipoProducto().setSelectedItem(null);
		this.getCmbTipoProducto().removeAllItems();
		this.getCmbFamiliaGenerico().setSelectedItem(null);
		this.getCmbFamiliaGenerico().removeAllItems();
		this.getCmbLinea().setSelectedItem(null);
		this.getCmbLinea().removeAllItems();
		this.getCmbMedida().setSelectedItem(null);
		this.getCmbMedida().removeAllItems();
		this.getRbCambioDescripcionSi().setSelected(true);
		this.getRbServicioSi().setSelected(true);
		this.getRbLlevaInventarioNo().setSelected(true);
		this.getRbUsaLoteNo().setSelected(true);
		this.getRbTieneSerieNo().setSelected(true);
		this.getCmbEstadoGenerico().setSelectedItem(null);
		this.getCmbEstadoGenerico().removeAllItems();
		this.getRbCobraIvaProveedorSi().setSelected(true);
		this.getRbCobraIvaClienteSi().setSelected(true);
		this.getRbCobraIceNo().setSelected(true);
		this.getRbAceptaDescuentoSi().setSelected(true);
		this.getCmbMarca().setSelectedItem(null);
		this.getCmbMarca().removeAllItems();
		this.getCmbModelo().setSelectedItem(null);
		this.getCmbModelo().removeAllItems();
		cleanProducto();
		DefaultTableModel model = (DefaultTableModel) this.getTblProductos().getModel();
		for(int i= this.getTblProductos().getRowCount();i>0;--i)
			model.removeRow(i-1);
		productoColeccion = new Vector<ProductoIf>();
		productoRemovidoColeccion = new Vector<ProductoIf>();
		this.repaint();
	}
	
	private void cleanProducto() {
		//clean de los campos de texto de producto
		this.getTxtProveedor().setText("");
		this.getTxtSubproveedor().setText("");
		this.getTxtCodigoBarras().setText("");
		this.getTxtCosto().setText("");
		this.getTxtMargenMinimo().setText("");
		this.getTxtRebate().setText("");
		this.getTxtPesoBruto().setText("");
		this.getTxtPesoNeto().setText("");
		//clean de los combos de producto
		this.getCmbPresentacionId().setSelectedItem(null);
		this.getCmbPresentacionId().removeAllItems();
		this.getRbAceptaPromocionSi().setSelected(true);
		this.getRbAceptaDevolucionSi().setSelected(true);
		this.getRbPermiteVentaSi().setSelected(true);
		this.getRbCambioPrecioSi().setSelected(true);
		this.getCmbColor().setSelectedItem(null);
		this.getCmbColor().removeAllItems();
		this.getCbGenerarCodigoBarras().setSelected(false);
	}
	
	public void showFindMode() {
		getTxtNombre().setBackground(Parametros.findColor);
		getCmbEstadoGenerico().setBackground(Parametros.findColor);
		getCmbTipo().setBackground(Parametros.findColor);
		getCmbLinea().setBackground(Parametros.findColor);
		getCmbFamiliaGenerico().setBackground(Parametros.findColor);
		getCmbTipoProducto().setBackground(Parametros.findColor);
		
		getCmbTipo().setSelectedIndex(-1);
		
		//habilito los campos de texto de generico
		getTxtNombre().setEnabled(true);
		getTxtAbreviado().setEnabled(false);
		getTxtOtroImpuesto().setEnabled(false);
		
		//habilito los campos de texto de producto
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
		getRbCambioDescripcionSi().setEnabled(false);
		getRbCambioDescripcionNo().setEnabled(false);
		getRbUsaLoteSi().setEnabled(false);
		getRbUsaLoteNo().setEnabled(false);
		getRbLlevaInventarioSi().setEnabled(false);
		getRbLlevaInventarioNo().setEnabled(false);
		getRbTieneSerieSi().setEnabled(false);
		getRbTieneSerieNo().setEnabled(false);
		getRbServicioSi().setEnabled(false);
		getRbServicioNo().setEnabled(false);
		getCmbEstadoGenerico().setEnabled(true);
		getRbCobraIvaProveedorSi().setEnabled(false);
		getRbCobraIvaProveedorNo().setEnabled(false);
		getRbCobraIvaClienteSi().setEnabled(false);
		getRbCobraIvaClienteNo().setEnabled(false);
		getRbCobraIceSi().setEnabled(false);
		getRbCobraIceNo().setEnabled(false);
		getRbAceptaDescuentoSi().setEnabled(false);
		getRbAceptaDescuentoNo().setEnabled(false);
		getCmbMarca().setEnabled(true);
		getCmbModelo().setEnabled(true);
		
		//habilito los combos de producto
		getCmbPresentacionId().setEnabled(false);
		getCmbOrigenProducto().setEnabled(false);
		getRbAceptaPromocionSi().setSelected(false);
		getRbAceptaPromocionNo().setSelected(false);
		getRbPermiteVentaSi().setSelected(false);
		getRbPermiteVentaNo().setSelected(false);
		getRbAceptaDevolucionSi().setSelected(false);
		getRbAceptaDevolucionNo().setSelected(false);
		getRbCambioPrecioSi().setSelected(false);
		getRbCambioPrecioNo().setSelected(false);
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
		
		//seteo a null los combos
		getCmbEstadoGenerico().setSelectedItem(null);
		getCmbEstadoProducto().setSelectedItem(null);
		getCmbLinea().setSelectedItem(null);
		getCmbFamiliaGenerico().setSelectedItem(null);
		getCmbTipoProducto().setSelectedItem(null);
		getCmbMarca().setSelectedItem(null);
		getCmbModelo().setSelectedItem(null);
		
		getTxtNombre().grabFocus();
		this.getJtpGenerico().setSelectedIndex(0);
		
	}
	
	public void showSaveMode() {
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getCmbEstadoGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setBackground(Parametros.saveUpdateColor);
		getCmbLinea().setBackground(Parametros.saveUpdateColor);
		getCmbFamiliaGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipoProducto().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setSelectedIndex(0);
		setSaveMode();
		clean();
		//habilito los campos de texto de generico
		getTxtNombre().setEnabled(true);
		getTxtAbreviado().setEnabled(true);
		getTxtOtroImpuesto().setEnabled(true);
		getCmbMedida().setEnabled(true);
		//habilito los campos de texto de producto
		getTxtProveedor().setEnabled(true);
		getTxtSubproveedor().setEditable(true);
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
		getRbCobraIvaProveedorSi().setEnabled(true);
		getRbCobraIvaProveedorNo().setEnabled(true);
		getRbCobraIvaProveedorSi().setSelected(true);
		getRbCobraIvaClienteSi().setEnabled(true);
		getRbCobraIvaClienteNo().setEnabled(true);
		getRbCobraIvaClienteSi().setSelected(true);
		getRbCobraIceSi().setEnabled(true);
		getRbCobraIceNo().setEnabled(true);
		getRbCobraIceNo().setSelected(true);
		getRbAceptaDescuentoSi().setEnabled(true);
		getRbAceptaDescuentoNo().setEnabled(true);
		getRbAceptaDescuentoSi().setSelected(true);
		getRbCambioDescripcionSi().setEnabled(true);
		getRbCambioDescripcionNo().setEnabled(true);
		getRbCambioDescripcionSi().setSelected(true);
		getRbServicioSi().setEnabled(true);
		getRbServicioNo().setEnabled(true);
		getRbServicioSi().setSelected(true);
		getRbLlevaInventarioSi().setEnabled(true);
		getRbLlevaInventarioNo().setEnabled(true);
		getRbLlevaInventarioNo().setSelected(true);
		getRbUsaLoteSi().setEnabled(true);
		getRbUsaLoteNo().setEnabled(true);
		getRbUsaLoteNo().setSelected(true);
		getRbTieneSerieSi().setEnabled(true);
		getRbTieneSerieNo().setEnabled(true);
		getRbTieneSerieNo().setSelected(true);
		getCmbEstadoGenerico().setEnabled(true);
		//habilito los combos de producto
		getCmbMarca().setEnabled(true);
		getCmbModelo().setEnabled(true);
		getCmbPresentacionId().setEnabled(true);
		getCmbColor().setEnabled(true);
		getCmbOrigenProducto().setEnabled(true);
		getCbGenerarCodigoBarras().setEnabled(true);
		getRbAceptaPromocionSi().setEnabled(true);
		getRbAceptaPromocionNo().setEnabled(true);
		getRbAceptaPromocionSi().setSelected(true);
		getRbAceptaDevolucionSi().setEnabled(true);
		getRbAceptaDevolucionNo().setEnabled(true);
		getRbAceptaDevolucionSi().setSelected(true);
		getRbPermiteVentaSi().setEnabled(true);
		getRbPermiteVentaNo().setEnabled(true);
		getRbPermiteVentaSi().setSelected(true);
		getRbCambioPrecioSi().setEnabled(true);
		getRbCambioPrecioNo().setEnabled(true);
		getRbCambioPrecioSi().setSelected(true);
		getCmbEstadoProducto().setEnabled(true);
		//cargo el listener para la tabla
		getTblProductos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Manejo los eventos de Buscar Proveedor
		cargarCombos();
		validateCmbMedida();
		validateCmbMarca();
		validateCmbColor();
		getJtpGenerico().setSelectedIndex(0);
		getTxtNombre().grabFocus();
	}
	
	public void showUpdateMode() {
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getCmbEstadoGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipo().setBackground(Parametros.saveUpdateColor);
		getCmbLinea().setBackground(Parametros.saveUpdateColor);
		getCmbFamiliaGenerico().setBackground(Parametros.saveUpdateColor);
		getCmbTipoProducto().setBackground(Parametros.saveUpdateColor);
		setUpdateMode();
		//habilito los campos de texto de generico
		getTxtNombre().setEnabled(true);
		getTxtAbreviado().setEnabled(true);
		getTxtOtroImpuesto().setEnabled(true);
		//habilito los campos de texto de producto
		getTxtProveedor().setEnabled(true);
		getTxtProveedor().setEditable(false);
		getTxtSubproveedor().setEditable(true);
		getTxtCodigoBarras().setEnabled(true);
		if ("N".equals(genericoIf.getUsaLote()))
			this.getTxtCodigoBarras().setEnabled(false);
		else
			this.getTxtCodigoBarras().setEnabled(true);
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
		getRbCobraIvaProveedorSi().setEnabled(true);
		getRbCobraIvaProveedorNo().setEnabled(true);
		getRbCobraIvaClienteSi().setEnabled(true);
		getRbCobraIvaClienteNo().setEnabled(true);
		getRbCobraIceSi().setEnabled(true);
		getRbCobraIceNo().setEnabled(true);
		getRbAceptaDescuentoSi().setEnabled(true);
		getRbAceptaDescuentoNo().setEnabled(true);
		getRbCambioDescripcionSi().setEnabled(true);
		getRbCambioDescripcionNo().setEnabled(true);
		getRbServicioSi().setEnabled(true);
		getRbServicioNo().setEnabled(true);
		getRbLlevaInventarioSi().setEnabled(true);
		getRbLlevaInventarioNo().setEnabled(true);
		getRbUsaLoteSi().setEnabled(true);
		getRbUsaLoteNo().setEnabled(true);
		getRbTieneSerieSi().setEnabled(true);
		getRbTieneSerieNo().setEnabled(true);
		getCmbEstadoGenerico().setEnabled(true);
		//habilito los combos de producto
		getCmbColor().setEnabled(true);
		getCbGenerarCodigoBarras().setEnabled(true);
		getCmbOrigenProducto().setEnabled(true);
		getRbAceptaPromocionSi().setEnabled(true);
		getRbAceptaPromocionNo().setEnabled(true);
		getRbAceptaDevolucionSi().setEnabled(true);
		getRbAceptaDevolucionNo().setEnabled(true);
		getRbPermiteVentaSi().setEnabled(true);
		getRbPermiteVentaNo().setEnabled(true);
		getRbCambioPrecioSi().setEnabled(true);
		getRbCambioPrecioNo().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		this.getBtnBuscarProveedor().grabFocus();
		this.getJtpGenerico().setSelectedIndex(0);
	}
	
	private void validateCmbMedida() {
		if (getRbServicioSi().isSelected()) {
			getCmbMedida().setSelectedIndex(-1);
			getCmbMedida().setEnabled(false);
			cleanProducto();
			cargarCombosProducto();
			getCmbPresentacionId().setSelectedIndex(-1);
			getCmbPresentacionId().setEnabled(false);
		} else {
			getCmbMedida().setEnabled(true);
			getCmbPresentacionId().setEnabled(true);
		}
	}
	
	private void validateCmbMarca() {
		if (getRbServicioSi().isSelected()) {
			getCmbMarca().setSelectedIndex(-1);
			getCmbMarca().setEnabled(false);
			cleanProducto();
			cargarCombosProducto();
			getCmbModelo().setSelectedIndex(-1);
			getCmbModelo().setEnabled(false);
		} else {
			getCmbMarca().setEnabled(true);
			getCmbModelo().setEnabled(true);
		}
	}
	
	private void validateCmbColor() {
		if (getRbServicioSi().isSelected()) {
			getCmbColor().setSelectedIndex(-1);
			getCmbColor().setEnabled(false);
			cleanProducto();
		} else {
			getCmbColor().setEnabled(true);
		}
	}
	
	public void  getSelectedObject(Object objeto) {
		setUpdateMode();
		genericoIf = (GenericoIf) objeto;
		getCmbEstadoGenerico().removeAllItems();
		getCmbEstadoProducto().removeAllItems();
		getCmbLinea().removeAllItems();
		getCmbFamiliaGenerico().removeAllItems();
		cargarCombos();
		
		try {
			getTxtNombre().setText(genericoIf.getNombre());
			getTxtAbreviado().setText(genericoIf.getAbreviado());
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

			if ((OPCION_SI.substring(0, 1)).equals(genericoIf.getServicio()))
				getRbServicioSi().setSelected(true);
			else
				getRbServicioNo().setSelected(true);
			
			if (getRbServicioNo().isSelected()) {
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
				getRbCobraIvaProveedorSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCobraIvaCliente()))
				getRbCobraIvaClienteSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getAceptaDescuento()))
				getRbAceptaDescuentoSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getUsaLote()))
				getRbUsaLoteSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getLlevaInventario()))
				getRbLlevaInventarioSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCobraIce()))
				getRbCobraIceSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getCambioDescripcion()))
				getRbCambioDescripcionSi().setSelected(true);
			
			if((OPCION_SI.substring(0, 1)).equals(genericoIf.getSerie()))
				getRbTieneSerieSi().setSelected(true);
			
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
			Map<Long, ClienteIf> mapaProveedor = new HashMap<Long, ClienteIf>();
			Map<Long, ClienteOficinaIf> mapaProveedorOficina = new HashMap<Long, ClienteOficinaIf>();
			Map<Long, CiudadIf> mapaCiudad = new HashMap<Long, CiudadIf>();
			Map<Long, MarcaProductoIf> mapaMarcaProducto = new HashMap<Long, MarcaProductoIf>();
			Map<Long, ModeloIf> mapaModelo = new HashMap<Long, ModeloIf>();
			Map<Long, PresentacionIf> mapaPresentacion = new HashMap<Long, PresentacionIf>();
			Map<Long, ColorIf> mapaColor = new HashMap<Long, ColorIf>();
			while(it.hasNext()){
				ProductoIf productoIf = (ProductoIf) it.next();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				MarcaProductoIf marcaProducto = productoIf.getMarcaId()!=null?GeneralUtil.verificarMapaMarcaProducto(mapaMarcaProducto,productoIf.getMarcaId()):null;
				ModeloIf modelo = productoIf.getModeloId()!=null?GeneralUtil.verificarMapaModelo(mapaModelo, productoIf.getModeloId()):null;
				PresentacionIf presentacion = productoIf.getPresentacionId()!=null?GeneralUtil.verificarMapaPresentacion(mapaPresentacion,productoIf.getPresentacionId()):null;
				ColorIf color = productoIf.getColorId()!=null?GeneralUtil.verificarMapaColor(mapaColor,productoIf.getColorId()):null;
				productoColeccion.add(productoIf);				
				if (productoIf.getProveedorId() != null) {
					ClienteOficinaIf proveedorOficina  = GeneralUtil.verificarMapaClienteOficina(mapaProveedorOficina, productoIf.getProveedorId());
					ClienteIf proveedor = GeneralUtil.verificarMapaCliente(mapaProveedor, proveedorOficina.getClienteId());
					CiudadIf ciudad = GeneralUtil.verificarMapaCiudad(mapaCiudad, proveedorOficina.getCiudadId());
					//filaPlantillaDetalle.add(proveedor.getCodigo() + " - " + ((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
					//filaPlantillaDetalle.add(proveedorOficina.getCodigo() + " - " + proveedorOficina.getDescripcion() );
					filaPlantillaDetalle.add(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial() + " // " + ciudad.getNombre());
				} else
					filaPlantillaDetalle.add("NO TIENE PROVEEDOR");
				if (getRbServicioSi().isSelected()) {
					filaPlantillaDetalle.add(getTxtNombre().getText());
				} else if (getRbServicioNo().isSelected()) {
					String detalle = "";
					if (modelo != null)
						detalle += modelo.getNombre() + " / ";
					if (presentacion != null)
						detalle += presentacion.getNombre() + " / ";
					if (color != null)
						detalle += color.getNombre() + " / ";
					filaPlantillaDetalle.add((getTxtNombre().getText() + " / " + detalle.trim()).trim());
				}
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
		getCmbEstadoGenerico().addItem(ESTADO_ACTIVO);
		getCmbEstadoGenerico().addItem(ESTADO_INACTIVO);
		cargarCombosProducto();
	}
	
	private void cargarCombosProducto() {
		cargarComboPresentacionId();
		cargarComboColor();
		if (getCmbEstadoProducto().getItemCount() <= 0) {
			getCmbEstadoProducto().addItem(ESTADO_ACTIVO);
			getCmbEstadoProducto().addItem(ESTADO_INACTIVO);
		}
		if (getCmbOrigenProducto().getItemCount() <= 0) {
			getCmbOrigenProducto().addItem(ORIGEN_LOCAL);
			getCmbOrigenProducto().addItem(ORIGEN_IMPORTADO);
		}
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
			if (proveedorOficina != null) {
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
		String strOtroImpuesto = Utilitarios.removeDecimalFormat(this.getTxtOtroImpuesto().getText());
		
		if ("".equals(this.getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar el nombre del genérico", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if(getCmbTipo().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el tipo", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbTipo().grabFocus();
			return false;
		}
		
		if (this.getCmbFamiliaGenerico().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la familia del genérico", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbFamiliaGenerico().grabFocus();
			return false;
		}
				
		if (this.getCmbTipoProducto().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de producto", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbTipoProducto().grabFocus();
			return false;
		}
				
		if (this.getCmbLinea().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la línea del genérico", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbLinea().grabFocus();
			return false;
		}
		
		if(getRbServicioNo().isSelected()){
			if (this.getCmbMedida().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la unidad de medida del genérico", SpiritAlert.WARNING);
				this.getJtpGenerico().setSelectedIndex(0);
				getCmbMedida().grabFocus();
				return false;
			}
		}
		
		if(!strOtroImpuesto.equals("")){
			if (Double.parseDouble(strOtroImpuesto) > 100D || Double.parseDouble(strOtroImpuesto) < 0D) {
				SpiritAlert.createAlert("El porcentaje por concepto de otros impuestos debe estar entre 0 y 100", SpiritAlert.WARNING);
				getTxtOtroImpuesto().grabFocus();
				return false;
			}
		}
		
		if (this.getCmbEstadoGenerico().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar estado del genérico", SpiritAlert.WARNING);
			this.getJtpGenerico().setSelectedIndex(0);
			getCmbEstadoGenerico().grabFocus();
			return false;
		}

		
		return true;
	}
	
	public boolean validateFieldsDetalle(){
		if (proveedorOficina == null) {
			SpiritAlert.createAlert("Debe seleccionar un proveedor", SpiritAlert.WARNING);
			getBtnBuscarProveedor().grabFocus();
			return false;
		}
		
		if (getRbServicioSi().isSelected()) {
			getCmbPresentacionId().setSelectedIndex(-1);
			getCmbPresentacionId().setEnabled(false);
		} else {
			if (getCmbPresentacionId().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la presentación del producto", SpiritAlert.WARNING);
				getCmbPresentacionId().grabFocus();
				return false;
			}
		}	
		
		return true;
	}
	
	public void agregarDetalle(){
		try{
			if (proveedorOficina != null){
				modelProducto = (DefaultTableModel) getTblProductos().getModel();
				Vector<String> filaDetalle = new Vector<String>();
				
				if (validateFieldsDetalle()){
					ProductoData data = new ProductoData();
					MarcaProductoIf marcaProducto = getCmbMarca().getSelectedItem()!=null?(MarcaProductoIf) getCmbMarca().getSelectedItem():null;
					ModeloIf modelo = getCmbModelo().getSelectedItem()!=null?(ModeloIf) getCmbModelo().getSelectedItem():null;
					PresentacionIf presentacion = getCmbPresentacionId().getSelectedItem()!=null?(PresentacionIf) this.getCmbPresentacionId().getSelectedItem():null;
					ColorIf color = getCmbColor().getSelectedItem()!=null?(ColorIf) getCmbColor().getSelectedItem():null;
					if(presentacion != null)
						data.setPresentacionId(presentacion.getId());
					if (marcaProducto != null)
						data.setMarcaId(marcaProducto.getId());
					if (modelo != null)
						data.setModeloId(modelo.getId());				
					if (color != null)
						data.setColorId(color.getId());

					data.setProveedorId(proveedorOficina.getId());
					//data.setFechaCreacion(new java.sql.Date(fechaCreacion.getYear(),fechaCreacion.getMonth(),fechaCreacion.getDate()));
					data.setFechaCreacion( DateHelperClient.getTimeStamp(fechaCreacion) );
					data.setOrigenProducto(this.getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
					if (!getCbGenerarCodigoBarras().isSelected())
						data.setCodigoBarras(this.getTxtCodigoBarras().getText());
					
					if(!Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()).equals(""))
						data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtCosto().getText()))));
					else
						data.setCosto(BigDecimal.valueOf(Double.parseDouble("0")));
					
					data.setAceptapromocion(getRbAceptaPromocionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
					data.setPermiteventa(getRbPermiteVentaSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
					data.setAceptadevolucion(getRbAceptaDevolucionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
					
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
					
					data.setCambioprecio(getRbCambioPrecioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
					data.setEstado(this.getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
					data.setSubproveedor(getTxtSubproveedor().getText());				
					if (getCbGenerarCodigoBarras().isSelected())
						data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
					else
						data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
					
					// Agregar a la coleccion de PresupuestoColeccion para grabar al final toda la coleccion
					productoColeccion.add(data);
					// Agrega los valores al registro que va  ser añadido  a la tabla.
					//agrega informacion  visual a la tabla de detalle para el usuario.
					
					//filaDetalle.add(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial() + " // " + ciudad.getNombre());
					filaDetalle.add(getTxtProveedor().getText());
					if (getRbServicioSi().isSelected()) {
						filaDetalle.add(getTxtNombre().getText());
					} else if (getRbServicioNo().isSelected()) {
						String detalle = "";
						if (modelo != null)
							detalle += modelo.getNombre() + " / ";
						if (presentacion != null)
							detalle += presentacion.getNombre() + " / ";
						if (color != null)
							detalle += color.getNombre() + " / ";
						filaDetalle.add((getTxtNombre().getText() + " / " + detalle.trim()).trim());
					}
					filaDetalle.add(this.getCmbEstadoProducto().getSelectedItem().toString());
					modelProducto.addRow(filaDetalle);
					cleanProducto();
					cargarCombosProducto();
					getBtnBuscarProveedor().grabFocus();
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar proveedor", SpiritAlert.WARNING);
				getBtnBuscarProveedor().grabFocus();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo agregar producto a la lista",SpiritAlert.ERROR);
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
					//cargo los nuevos datos al objeto
					ProductoData data = new ProductoData();
					//String fecha;
					//DateFormat dateFormatter;
					//dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
					//fecha = dateFormatter.format(productoIf.getFechaCreacion());
					String fecha = Utilitarios.getFechaUppercase(productoIf.getFechaCreacion());
					MarcaProductoIf marcaProducto = getCmbMarca().getSelectedItem()!=null?(MarcaProductoIf) getCmbMarca().getSelectedItem():null;
					ModeloIf modelo = getCmbModelo().getSelectedItem()!=null?(ModeloIf) getCmbModelo().getSelectedItem():null;
					PresentacionIf presentacion = getCmbPresentacionId().getSelectedItem()!=null?(PresentacionIf) this.getCmbPresentacionId().getSelectedItem():null;
					ColorIf color = getCmbColor().getSelectedItem()!=null?(ColorIf) getCmbColor().getSelectedItem():null;
					if(!(productoIf.getId()!= null)){
						//seteo con los nuevos valores al objeto del producto
						if (marcaProducto != null)
							data.setMarcaId(marcaProducto.getId());
						if (modelo != null)
							data.setModeloId(modelo.getId());
						if(presentacion != null)
							data.setPresentacionId(presentacion.getId());
						if (color != null)
							data.setColorId(color.getId());
						data.setProveedorId(proveedorOficina.getId());
						data.setFechaCreacion(productoIf.getFechaCreacion());
						data.setOrigenProducto(getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
						data.setCodigoBarras(getTxtCodigoBarras().getText());
						if(!Utilitarios.removeDecimalFormat(getTxtCosto().getText()).equals(""))
							data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))));
						else
							data.setCosto(BigDecimal.valueOf(Double.parseDouble("0")));
						data.setAceptapromocion(getRbAceptaPromocionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setPermiteventa(getRbPermiteVentaSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setAceptadevolucion(getRbAceptaDevolucionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
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
						data.setCambioprecio(getRbCambioPrecioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
						data.setSubproveedor(getTxtSubproveedor().getText());
						if (getCbGenerarCodigoBarras().isSelected())
							data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
						else
							data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
						productoColeccion.set(i,data);
						//Actualizo en la tabla
						modelProducto.setValueAt(getTxtProveedor().getText(),getTblProductos().getSelectedRow(),0);
						if (getRbServicioSi().isSelected()) {
							modelProducto.setValueAt(getTxtNombre().getText(),getTblProductos().getSelectedRow(),1);
						} else if (getRbServicioNo().isSelected()) {
							String detalle = "";
							if (modelo != null)
								detalle += modelo.getNombre() + " / ";
							if (presentacion != null)
								detalle += presentacion.getNombre() + " / ";
							if (color != null)
								detalle += color.getNombre() + " / ";
							modelProducto.setValueAt((getTxtNombre().getText() + " / " + detalle.trim()).trim(),getTblProductos().getSelectedRow(), 1);
						}
						modelProducto.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getTblProductos().getSelectedRow(),2);
					}

					if (productoIf.getId()!= null) {
						//seteo con los nuevos valores al objeto del producto
						data.setId(productoIf.getId());
						if (marcaProducto != null)
							data.setMarcaId(marcaProducto.getId());
						if (modelo != null)
							data.setModeloId(modelo.getId());
						if(presentacion != null)
							data.setPresentacionId(presentacion.getId());
						if (color != null)
							data.setColorId(color.getId());
						data.setProveedorId(proveedorOficina.getId());
						data.setFechaCreacion(productoIf.getFechaCreacion());
						data.setOrigenProducto(getCmbOrigenProducto().getSelectedItem().toString().substring(0, 1));
						data.setCodigoBarras(getTxtCodigoBarras().getText());
						if(!Utilitarios.removeDecimalFormat(getTxtCosto().getText()).equals(""))
							data.setCosto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtCosto().getText()))));
						data.setAceptapromocion(getRbAceptaPromocionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setPermiteventa(getRbPermiteVentaSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setAceptadevolucion(getRbAceptaDevolucionSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						if(!Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()).equals(""))
							data.setMargenminimo(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtMargenMinimo().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtRebate().getText()).equals(""))
							data.setRebate(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRebate().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()).equals(""))
							data.setPesoBruto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoBruto().getText()))));
						if(!Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()).equals(""))
							data.setPesoNeto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPesoNeto().getText()))));
						data.setCambioprecio(getRbCambioPrecioSi().isSelected()?OPCION_SI.substring(0,1):OPCION_NO.substring(0,1));
						data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
						data.setGenericoId(productoIf.getGenericoId());
						data.setSubproveedor(getTxtSubproveedor().getText());
						if (getCbGenerarCodigoBarras().isSelected())
							data.setGenerarCodigoBarras(OPCION_SI.substring(0,1));
						else
							data.setGenerarCodigoBarras(OPCION_NO.substring(0,1));
						productoColeccion.set(i,data);
						//Actualizo en la tabla
						modelProducto.setValueAt(getTxtProveedor().getText(),getTblProductos().getSelectedRow(),0);
						if (getRbServicioSi().isSelected()) {
							modelProducto.setValueAt(getTxtNombre().getText(),getTblProductos().getSelectedRow(),1);
						} else if (getRbServicioNo().isSelected()) {
							String detalle = "";
							if (modelo != null)
								detalle += modelo.getNombre() + " / ";
							if (presentacion != null)
								detalle += presentacion.getNombre() + " / ";
							if (color != null)
								detalle += color.getNombre() + " / ";
							modelProducto.setValueAt((getTxtNombre().getText() + " / " + detalle.trim()).trim(),getTblProductos().getSelectedRow(), 1);
						}
						modelProducto.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getTblProductos().getSelectedRow(),2);
					}
				}
			}
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de Producto a actualizar !!!",SpiritAlert.WARNING);
		}
		
		getTxtProveedor().setText("");
		proveedorOficina = null;
		getTxtSubproveedor().setText("");
		getTxtCodigoBarras().setText("");
		getTxtCosto().setText("");	
		getTxtRebate().setText("");
		getTxtMargenMinimo().setText("");
		getTxtPesoBruto().setText("");
		getTxtPesoNeto().setText("");
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