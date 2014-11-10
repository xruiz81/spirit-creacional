package com.spirit.facturacion.gui.model;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.ListaPrecioData;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PrecioData;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.facturacion.gui.panel.JPListaPrecio;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class ListaPrecioModel extends JPListaPrecio {

	private static final String NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_PRECIO_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_PRECIO_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_CAMBIAR_PRECIO_SI = "SI";
	private static final String NOMBRE_CAMBIAR_PRECIO_NO = "NO";
	private static final String LISTA_PRECIO_ESTADO_ACTIVO = "A";
	private static final String LISTA_PRECIO_ESTADO_INACTIVO = "I";
	private static final String PRECIO_ESTADO_ACTIVO = "A";
	private static final String PRECIO_ESTADO_INACTIVO = "I";
	private static final String CAMBIAR_PRECIO_SI = "S";
	private static final String CAMBIAR_PRECIO_NO = "N";
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_REFERENCIA = 8;
	private static final int MAX_LONGITUD_PRECIO = 9;

	private Vector listaPrecioVector = new Vector();
	private DefaultTableModel tableListaModel;
	private int listaPrecioSeleccionada;
	protected ListaPrecioIf listaPrecioIf;
	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	private ProductoIf productoIf;
	private PrecioIf precioIf;
	private Vector<PrecioIf> vectorPrecioIf = new Vector<PrecioIf>();
	private PrecioIf precio;
	private int precioSeleccionado;
	private DefaultTableModel tablePrecioModel;
	private List<PrecioIf> precioColeccion = new ArrayList<PrecioIf>();
	private List<PrecioIf> preciosRemovidos = new ArrayList<PrecioIf>();
	Calendar calendarFechaInicio = new GregorianCalendar();
	Calendar calendarFechaFinal = new GregorianCalendar();
	private boolean actualizarPrecio = true;
	Map<Long, ProductoIf> productosMap = new HashMap<Long, ProductoIf>();
	Map<Long, GenericoIf> genericosMap = new HashMap<Long, GenericoIf>();
	Map<Long, PresentacionIf> presentacionesMap = new HashMap<Long, PresentacionIf>();
	Map<Long, ClienteOficinaIf> proveedoresMap = new HashMap<Long, ClienteOficinaIf>();
	
	public ListaPrecioModel() {
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblListaPrecio());
		setSorterTable(getTblPrecio());
		anchoColumnasTabla();
		initListeners();
		getTblListaPrecio().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblListaPrecio().addMouseListener(oMouseAdapterTblListaPrecio);
		getTblListaPrecio().addKeyListener(oKeyAdapterTblListaPrecio);
		getTblPrecio().addMouseListener(oMouseAdapterTblPrecio);
		getTblPrecio().addKeyListener(oKeyAdapterTblPrecio);
		getBtnAgregarPrecio().addActionListener(btnAgregarPrecioListener);
		getBtnActualizarPrecio().addActionListener(btnActualizarPrecioListener);
		getBtnRemoverPrecio().addActionListener(btnRemoverPrecioListener);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtPrecio().addKeyListener(new TextChecker(MAX_LONGITUD_PRECIO));
		getTxtPrecio().addKeyListener(new NumberTextFieldDecimal());
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblListaPrecio().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblListaPrecio().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblListaPrecio().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblListaPrecio().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblListaPrecio().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblListaPrecio().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(30);
		TableColumn anchoColumnaPrecio = getTblPrecio().getColumnModel().getColumn(0);
		anchoColumnaPrecio.setPreferredWidth(220);
		anchoColumnaPrecio = getTblPrecio().getColumnModel().getColumn(1);
		anchoColumnaPrecio.setPreferredWidth(220);
		anchoColumnaPrecio = getTblPrecio().getColumnModel().getColumn(2);
		anchoColumnaPrecio.setPreferredWidth(60);
		anchoColumnaPrecio = getTblPrecio().getColumnModel().getColumn(3);
		anchoColumnaPrecio.setPreferredWidth(30);
		anchoColumnaPrecio = getTblPrecio().getColumnModel().getColumn(4);
		anchoColumnaPrecio.setPreferredWidth(30);
	}

	private void initListeners() {

		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String mmpg = "";
				productoCriteria = new ProductoCriteria("Producto", 0L, "", null, "A",true,mmpg);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
					try {
						GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
						if ("S".equals(generico.getUsaLote())) {
							PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService()
								.getPresentacion(productoIf.getPresentacionId());
							getTxtProducto().setText(generico.getNombre()+ " - "
								+ presentacion.getNombre());
						} else
							getTxtProducto().setText(generico.getNombre());
		
						if (productoIf.getProveedorId() != null) {
							ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
							ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
							getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal() + " - " + proveedorOficina.getDescripcion());
						} else
							getTxtProveedor().setText("NO TIENE PROVEEDOR");
						getTxtPrecio().setText("");
		
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		});
	}

	MouseListener oMouseAdapterTblListaPrecio = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblListaPrecio = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			setCursorEspera();
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setListaPrecioSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			listaPrecioIf = (ListaPrecioIf) getListaPrecioVector().get(getListaPrecioSeleccionada());

			getTxtCodigo().setText(listaPrecioIf.getCodigo());
			getTxtNombre().setText(listaPrecioIf.getNombre());
			getTxtReferencia().setText(listaPrecioIf.getReferenciaFisica());

			calendarFechaInicio.setTime(listaPrecioIf.getFechaInicio());
			getCmbFechaInicio().setCalendar(calendarFechaInicio);
			getCmbFechaInicio().repaint();
			if(listaPrecioIf.getFechaFinal() != null){
				calendarFechaFinal.setTime(listaPrecioIf.getFechaFinal());
				getCmbFechaFin().setCalendar(calendarFechaFinal);
			}else{
				getCmbFechaFin().setCalendar(null);
			}			
			getCmbFechaFin().repaint();
			
			if(listaPrecioIf.getEstado()!=null)
			{
			if (listaPrecioIf.getEstado().equals(LISTA_PRECIO_ESTADO_ACTIVO))
				getCmbEstadoLista().setSelectedItem(NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO);
			else if (listaPrecioIf.getEstado().equals(LISTA_PRECIO_ESTADO_INACTIVO))
				getCmbEstadoLista().setSelectedItem(NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO);
			}
			
			cleanPrecio();
			cleanTablaPrecio();
			cargarTablaPrecio();
			showUpdateMode();
			setCursorNormal();
		}
	}

	private void cargarTablaPrecio() {
		try {
			Collection precios = SessionServiceLocator.getPrecioSessionService().findPrecioProductosActivosByListaPrecioId(listaPrecioIf.getId());
			Iterator precioIterator = precios.iterator();

			if (!vectorPrecioIf.isEmpty()) {
				vectorPrecioIf.removeAllElements();
			}

			while (precioIterator.hasNext()) {
				PrecioIf precioIf = (PrecioIf) precioIterator.next();

				tablePrecioModel = (DefaultTableModel) getTblPrecio()
						.getModel();
				Vector<String> fila = new Vector<String>();
				vectorPrecioIf.add(precioIf);

				agregarColumnasTablaPrecio(precioIf, fila);

				tablePrecioModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map<Long, ProductoIf> mapearProductos() throws GenericBusinessException {
		Map<Long, ProductoIf> productosMap = new HashMap<Long, ProductoIf>();
		Map queryMap = new HashMap();
		queryMap.put("estado", "A");
		queryMap.put("permiteventa", "S");
		Iterator<ProductoIf> it = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaIdAndByQuery(Parametros.getIdEmpresa(), queryMap).iterator();
		while (it.hasNext()) {
			ProductoIf producto = it.next();
			productosMap.put(producto.getId(), producto);
		}
		return productosMap;
	}
	
	private Map<Long, GenericoIf> mapearGenericos() throws GenericBusinessException {
		Map<Long, GenericoIf> genericosMap = new HashMap<Long, GenericoIf>();
		Iterator<GenericoIf> it = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(Parametros.getIdEmpresa()).iterator();
		while (it.hasNext()) {
			GenericoIf generico = it.next();
			genericosMap.put(generico.getId(), generico);
		}
		return genericosMap;
	}
	
	private Map<Long, PresentacionIf> mapearPresentaciones() throws GenericBusinessException {
		Map<Long, PresentacionIf> presentacionesMap = new HashMap<Long, PresentacionIf>();
		Iterator<PresentacionIf> it = SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa()).iterator();
		while (it.hasNext()) {
			PresentacionIf presentacion = it.next();
			presentacionesMap.put(presentacion.getId(), presentacion);
		}
		return presentacionesMap;
	}
	
	private Map<Long, ClienteOficinaIf> mapearProveedores() throws GenericBusinessException {
		Map<Long, ClienteOficinaIf> proveedoresMap = new HashMap<Long, ClienteOficinaIf>();
		Iterator<ClienteOficinaIf> it = SessionServiceLocator.getClienteOficinaSessionService().findProveedoresProductosVenta(Parametros.getIdEmpresa()).iterator();
		while (it.hasNext()) {
			ClienteOficinaIf proveedor = it.next();
			proveedoresMap.put(proveedor.getId(), proveedor);
		}
		return proveedoresMap;
	}

	private void agregarColumnasTablaPrecio(PrecioIf precioIf, Vector<String> fila) {
		ProductoIf producto = productosMap.get(precioIf.getProductoId());
		if (producto == null)
			System.out.println("AQUI");
		if (producto.getGenericoId() == null)
			System.out.println("AQUI");
		GenericoIf generico = genericosMap.get(producto.getGenericoId());
		if ("S".equals(generico.getUsaLote())) {
			PresentacionIf presentacion = presentacionesMap.get(producto.getPresentacionId());
			fila.add(generico.getNombre() + " - " + presentacion.getNombre());
		} else
			fila.add(generico.getNombre());

		if (producto.getProveedorId() != null) {
			ClienteOficinaIf proveedor = proveedoresMap.get(producto.getProveedorId());
			fila.add(proveedor.getDescripcion());
		} else
			fila.add("NO TIENE PROVEEDOR");
		
		fila.add(precioIf.getPrecio().toString());

		if (precioIf.getCambiarPrecio().equals(CAMBIAR_PRECIO_SI))
			fila.add(NOMBRE_CAMBIAR_PRECIO_SI);
		else
			fila.add(NOMBRE_CAMBIAR_PRECIO_NO);

		if (precioIf.getEstado().equals(PRECIO_ESTADO_ACTIVO))
			fila.add(NOMBRE_PRECIO_ESTADO_ACTIVO);
		else
			fila.add(NOMBRE_PRECIO_ESTADO_INACTIVO);
	}

	MouseListener oMouseAdapterTblPrecio = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedPrecioRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblPrecio = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedPrecioRowForUpdate(evt);
		}
	};

	private void enableSelectedPrecioRowForUpdate(ComponentEvent evt) {
		try {
			// Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setPrecioSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				precio = (PrecioIf) getVectorPrecioIf().get(getPrecioSeleccionado());
				productoIf = SessionServiceLocator.getProductoSessionService().getProducto(precio.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				Long proveedorId = productoIf.getProveedorId();
				if (proveedorId != null){
					ClienteOficinaIf proveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(proveedorId);
	
					if ("S".equals(generico.getUsaLote())) {
						PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
						getTxtProducto().setText(generico.getNombre() + " - " + presentacion.getNombre());
						getTxtProveedor().setText(proveedor.getCodigo() + " - "	+ proveedor.getDescripcion());
					} else
						getTxtProducto().setText(generico.getNombre());
	
					getTxtProveedor().setText(proveedor.getCodigo() + " - "	+ proveedor.getDescripcion());
				} else{
					getTxtProducto().setText(generico.getNombre());
					getTxtProveedor().setText("NO TIENE PROVEEDOR");
				}
				getTxtPrecio().setText(precio.getPrecio().toString());

				if (precio.getCambiarPrecio().equals(CAMBIAR_PRECIO_SI))
					getCmbCambiarPrecio().setSelectedItem(NOMBRE_CAMBIAR_PRECIO_SI);
				else
					getCmbCambiarPrecio().setSelectedItem(NOMBRE_CAMBIAR_PRECIO_NO);

				if (precio.getEstado().equals(PRECIO_ESTADO_ACTIVO))
					getCmbEstadoPrecio().setSelectedItem(NOMBRE_PRECIO_ESTADO_ACTIVO);
				else
					getCmbEstadoPrecio().setSelectedItem(NOMBRE_PRECIO_ESTADO_INACTIVO);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	ActionListener btnAgregarPrecioListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			agregarPrecio();
		}
	};

	private void agregarPrecio() {
		setActualizarPrecio(false);
		if (validatePrecioFields()) {
			tablePrecioModel = (DefaultTableModel) getTblPrecio().getModel();

			// Vector precios para luego guardar en la base
			PrecioData bean = new PrecioData();
			setPrecioIf(bean);

			getPrecioIf().setProductoId(productoIf.getId());
			getPrecioIf().setPrecio(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()))));

			if (getCmbCambiarPrecio().getSelectedItem().equals(
					NOMBRE_CAMBIAR_PRECIO_SI))
				getPrecioIf().setCambiarPrecio(CAMBIAR_PRECIO_SI);
			else
				getPrecioIf().setCambiarPrecio(CAMBIAR_PRECIO_NO);

			if (getCmbEstadoPrecio().getSelectedItem().equals(
					NOMBRE_PRECIO_ESTADO_ACTIVO))
				getPrecioIf().setEstado(PRECIO_ESTADO_ACTIVO);
			else
				getPrecioIf().setEstado(PRECIO_ESTADO_INACTIVO);

			vectorPrecioIf.add(getPrecioIf());

			// Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();

			fila.add(getTxtProducto().getText());
			fila.add(getTxtProveedor().getText());
			fila.add(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()));
			fila.add(getCmbCambiarPrecio().getSelectedItem().toString());
			fila.add(getCmbEstadoPrecio().getSelectedItem().toString());

			tablePrecioModel.addRow(fila);
			cleanPrecio();
		}
	}

	ActionListener btnActualizarPrecioListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			actualizarPrecio();
		}
	};

	private void actualizarPrecio() {
		setActualizarPrecio(true);
		int filaSeleccionada = getTblPrecio().getSelectedRow();
		if (filaSeleccionada >= 0) {
			if (validatePrecioFields()) {
				int opcion = JOptionPane
						.showConfirmDialog(
								null,
								"¿Está seguro que desea actualizar la fila seleccionada?!",
								"Confirmación", JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {

					PrecioData bean = new PrecioData();
					setPrecioIf(bean);

					getPrecioIf().setId(precio.getId());
					getPrecioIf().setProductoId(productoIf.getId());
					getPrecioIf().setPrecio(
							BigDecimal.valueOf(Double
									.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()))));

					if (getCmbCambiarPrecio().getSelectedItem().equals(
							NOMBRE_CAMBIAR_PRECIO_SI))
						getPrecioIf().setCambiarPrecio(CAMBIAR_PRECIO_SI);
					else
						getPrecioIf().setCambiarPrecio(CAMBIAR_PRECIO_NO);

					if (getCmbEstadoPrecio().getSelectedItem().equals(
							NOMBRE_PRECIO_ESTADO_ACTIVO))
						getPrecioIf().setEstado(PRECIO_ESTADO_ACTIVO);
					else
						getPrecioIf().setEstado(PRECIO_ESTADO_INACTIVO);

					vectorPrecioIf.add(filaSeleccionada, getPrecioIf());
					vectorPrecioIf.remove(filaSeleccionada + 1);

					Vector<String> fila = new Vector<String>();

					fila.add(getTxtProducto().getText());
					fila.add(getTxtProveedor().getText());
					fila.add(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()));
					fila.add(getCmbCambiarPrecio().getSelectedItem().toString());
					fila.add(getCmbEstadoPrecio().getSelectedItem().toString());

					tablePrecioModel.insertRow(filaSeleccionada, fila);
					tablePrecioModel.removeRow(filaSeleccionada + 1);

					cleanPrecio();
				}
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}

	ActionListener btnRemoverPrecioListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			removerPrecio();
		}
	};
	
	private void removerPrecio() {
		setActualizarPrecio(false);
		int filaSeleccionada = getTblPrecio().getSelectedRow();
		if (filaSeleccionada >= 0) {
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				PrecioIf precio = (PrecioIf)vectorPrecioIf.get(filaSeleccionada); 
				if (precio.getId()!=null)
					preciosRemovidos.add(precio);
				vectorPrecioIf.remove(filaSeleccionada);
				tablePrecioModel.removeRow(filaSeleccionada);
			}
			cleanPrecio();
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}

	public boolean validatePrecioFields() {

		String strProducto = getTxtProducto().getText();
		String strPrecio = Utilitarios.removeDecimalFormat(getTxtPrecio().getText());

		if ((("".equals(strProducto)) || (strProducto == null))) {
			SpiritAlert.createAlert("Debe escoger un Producto !!",
					SpiritAlert.WARNING);
			getBtnProducto().grabFocus();
			return false;
		}
		if ((("".equals(strPrecio)) || (strPrecio == null))) {
			SpiritAlert.createAlert("Debe ingresar un Precio !!",
					SpiritAlert.WARNING);
			getTxtPrecio().grabFocus();
			return false;
		}

		boolean productoRepetido = false;
		PrecioIf precioIf;
		ProductoIf productosIf;

		for (int i = 0; i < vectorPrecioIf.size(); i++) {
			precioIf = vectorPrecioIf.get(i);
			productosIf = productosMap.get(precioIf.getProductoId());

			if (!isActualizarPrecio()) {
				if (productoIf.getId().equals(productosIf.getId()))
					productoRepetido = true;
			} else if (isActualizarPrecio()) {
				if (productoIf.getId().equals(productosIf.getId())
						&& i != precioSeleccionado)
					productoRepetido = true;
			}
		}

		if (productoRepetido) {
			SpiritAlert.createAlert(
					"El Producto ya tiene Precio en la Lista !!",
					SpiritAlert.WARNING);
			cleanPrecio();
			getBtnProducto().grabFocus();
			return false;
		}
		return true;

	}

	public void showSaveMode() {
		setSaveMode();
		getTxtProducto().setEditable(false);
		getTxtProveedor().setEditable(false);
		cargarCombos();
		clean();
		cleanPrecio();
		cleanTablaPrecio();
		cargarTabla();
		cargarMapas();
		vectorPrecioIf.clear();
		precioColeccion.clear();
		preciosRemovidos.clear();
		setActualizarPrecio(false);
		getJtpListaPrecio().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
	}
	
	private void cargarMapas() {
		try {
			productosMap = mapearProductos();
			genericosMap = mapearGenericos();
			presentacionesMap = mapearPresentaciones();
			proveedoresMap = mapearProveedores();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar mapas de datos", SpiritAlert.ERROR);
		}
	}
	
	public void showUpdateMode() {
		setUpdateMode();
	}
	
	public void showFindMode() {
		showSaveMode();
	}

	public void cargarCombos() {
		// seteo formato de combos de fecha
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());

		getCmbEstadoLista().removeAllItems();
		getCmbEstadoLista().addItem(NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO);
		getCmbEstadoLista().addItem(NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO);

		getCmbEstadoPrecio().removeAllItems();
		getCmbEstadoPrecio().addItem(NOMBRE_PRECIO_ESTADO_ACTIVO);
		getCmbEstadoPrecio().addItem(NOMBRE_PRECIO_ESTADO_INACTIVO);

		getCmbCambiarPrecio().removeAllItems();
		getCmbCambiarPrecio().addItem(NOMBRE_CAMBIAR_PRECIO_SI);
		getCmbCambiarPrecio().addItem(NOMBRE_CAMBIAR_PRECIO_NO);
	}

	private void cargarTabla() {
		try {
			Collection listaPrecio = SessionServiceLocator.getListaPrecioSessionService().findListaPrecioByEmpresaId(Parametros.getIdEmpresa());
			Iterator listaPrecioIterator = listaPrecio.iterator();

			if (!getListaPrecioVector().isEmpty()) {
				getListaPrecioVector().removeAllElements();
			}

			while (listaPrecioIterator.hasNext()) {
				ListaPrecioIf listaPrecioIf = (ListaPrecioIf) listaPrecioIterator.next();

				tableListaModel = (DefaultTableModel) getTblListaPrecio().getModel();
				Vector<String> fila = new Vector<String>();
				getListaPrecioVector().add(listaPrecioIf);

				agregarColumnasTabla(listaPrecioIf, fila);

				tableListaModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblListaPrecio(), listaPrecio, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(ListaPrecioIf listaPrecioIf,
			Vector<String> fila) {

		fila.add(listaPrecioIf.getCodigo());
		fila.add(listaPrecioIf.getNombre());
		fila.add(listaPrecioIf.getReferenciaFisica());
		fila.add(Utilitarios.getFechaCortaUppercase(listaPrecioIf.getFechaInicio()));
		if(listaPrecioIf.getFechaFinal() != null){
			fila.add(Utilitarios.getFechaCortaUppercase(listaPrecioIf.getFechaFinal()));
		}else{
			fila.add("");
		}

		
		if(listaPrecioIf.getEstado()!=null)
		{
		if (listaPrecioIf.getEstado().equals(LISTA_PRECIO_ESTADO_ACTIVO))
			fila.add(NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO);
		else if (listaPrecioIf.getEstado().equals(LISTA_PRECIO_ESTADO_INACTIVO))
			fila.add(NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO);
		}

	}

	public void find() {

	}

	public void save() {
		try {
			if (validateFields()) {
				setCursor(SpiritCursor.hourglassCursor);
				ListaPrecioIf listaPrecio = registrarListaPrecio();
				generarPrecioColleccion();
				SessionServiceLocator.getListaPrecioSessionService().procesarListaPrecio(listaPrecio,precioColeccion);
				SpiritAlert.createAlert("Lista de Precios guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			
		}
		setCursor(SpiritCursor.normalCursor);
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setCursor(SpiritCursor.hourglassCursor);
				registrarListaPrecioForUpdate();
				generarPrecioColleccionForUpdate();

				getListaPrecioVector().setElementAt(
						getListaPrecioActualizadaIf(),
						getListaPrecioSeleccionada());

				SessionServiceLocator.getListaPrecioSessionService().actualizarListaPrecio(
						getListaPrecioActualizadaIf(), precioColeccion,
						preciosRemovidos);
				setListaPrecioActualizadaIf(null);

				SpiritAlert.createAlert(
						"Lista de Precios actualizada con éxito",
						SpiritAlert.INFORMATION);
				showSaveMode();
			}

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",
					SpiritAlert.ERROR);
		}
		setCursor(SpiritCursor.normalCursor);
	}
	
	public boolean isEmpty() {
		return false;
	}

	private ListaPrecioIf registrarListaPrecio(){

		ListaPrecioData data = new ListaPrecioData();

		data.setCodigo(getTxtCodigo().getText());
		data.setNombre(getTxtNombre().getText());
		data.setEmpresaId(Parametros.getIdEmpresa());
		if (getTxtReferencia().getText() != null)
			data.setReferenciaFisica(getTxtReferencia().getText());

		try {
			data.setFechaCreacion(new java.sql.Date(Utilitarios.dateHoy()
					.getTime()));
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		data.setFechaInicio(new java.sql.Date(getCmbFechaInicio().getDate()
				.getTime()));
		data.setFechaFinal(new java.sql.Date(getCmbFechaFin().getDate()
				.getTime()));

		if (getCmbEstadoLista().getSelectedItem().equals(
				NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO))
			data.setEstado(LISTA_PRECIO_ESTADO_ACTIVO);
		else if (getCmbEstadoLista().getSelectedItem().equals(
				NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO))
			data.setEstado(LISTA_PRECIO_ESTADO_INACTIVO);

		return data;
	}

	private void generarPrecioColleccion() {

		precioColeccion = new ArrayList<PrecioIf>();

		for (int i = 0; i < vectorPrecioIf.size(); i++) {
			PrecioData precio = new PrecioData();

			precio.setProductoId(vectorPrecioIf.get(i).getProductoId());
			precio.setPrecio(vectorPrecioIf.get(i).getPrecio());
			precio.setCambiarPrecio(vectorPrecioIf.get(i).getCambiarPrecio());
			precio.setEstado(vectorPrecioIf.get(i).getEstado());

			precioColeccion.add(precio);
		}
	}

	private void registrarListaPrecioForUpdate(){

		setListaPrecioActualizadaIf((ListaPrecioIf) getListaPrecioVector().get(
				getListaPrecioSeleccionada()));

		getListaPrecioActualizadaIf().setCodigo(getTxtCodigo().getText());
		getListaPrecioActualizadaIf().setNombre(getTxtNombre().getText());
		getListaPrecioActualizadaIf().setEmpresaId(Parametros.getIdEmpresa());
		getListaPrecioActualizadaIf().setReferenciaFisica(
				getTxtReferencia().getText());
		getListaPrecioActualizadaIf().setFechaCreacion(
				listaPrecioIf.getFechaCreacion());
		getListaPrecioActualizadaIf().setFechaInicio(
				new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
		getListaPrecioActualizadaIf().setFechaFinal(
				new java.sql.Date(getCmbFechaFin().getDate().getTime()));

		if (getCmbEstadoLista().getSelectedItem().equals(
				NOMBRE_LISTA_PRECIO_ESTADO_ACTIVO))
			getListaPrecioActualizadaIf().setEstado(LISTA_PRECIO_ESTADO_ACTIVO);
		else if (getCmbEstadoLista().getSelectedItem().equals(
				NOMBRE_LISTA_PRECIO_ESTADO_INACTIVO))
			getListaPrecioActualizadaIf().setEstado(
					LISTA_PRECIO_ESTADO_INACTIVO);
	}

	private void generarPrecioColleccionForUpdate() {

		precioColeccion = new ArrayList<PrecioIf>();

		for (int i = 0; i < vectorPrecioIf.size(); i++) {
			PrecioData precio = new PrecioData();

			precio.setId(vectorPrecioIf.get(i).getId());
			precio.setListaprecioId(listaPrecioIf.getId());
			precio.setProductoId(vectorPrecioIf.get(i).getProductoId());
			precio.setPrecio(vectorPrecioIf.get(i).getPrecio());
			precio.setCambiarPrecio(vectorPrecioIf.get(i).getCambiarPrecio());
			precio.setEstado(vectorPrecioIf.get(i).getEstado());

			precioColeccion.add(precio);
		}
	}

	public void delete() {
		try {
			setCursor(SpiritCursor.hourglassCursor);
			ListaPrecioIf listaPrecioIf = (ListaPrecioIf) getListaPrecioVector().get(getListaPrecioSeleccionada());
			SessionServiceLocator.getListaPrecioSessionService().eliminarListaPrecio(listaPrecioIf.getId());
			SpiritAlert.createAlert("Lista de Precio eliminada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
		setCursor(SpiritCursor.normalCursor);
	}

	public void clean() {
		Calendar calendar = new GregorianCalendar();
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtReferencia().setText("");
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);

		DefaultTableModel modelListaPrecio = (DefaultTableModel) getTblListaPrecio()
				.getModel();
		for (int i = this.getTblListaPrecio().getRowCount(); i > 0; --i)
			modelListaPrecio.removeRow(i - 1);

		cleanTablaPrecio();
	}

	public void cleanPrecio() {
		getTxtProducto().setText("");
		getTxtProveedor().setText("");
		getTxtPrecio().setText("");
	}

	public void cleanTablaPrecio() {
		DefaultTableModel modelPrecio = (DefaultTableModel) getTblPrecio()
				.getModel();
		for (int i = this.getTblPrecio().getRowCount(); i > 0; --i)
			modelPrecio.removeRow(i - 1);
	}

	public void report() {

	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpListaPrecio().getSelectedIndex();
		int tabCount = this.getJtpListaPrecio().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpListaPrecio().setSelectedIndex(selectedTab);
	}

	public void addDetail() {
		if (this.getJtpListaPrecio().getSelectedIndex() == 1)
			agregarPrecio();
	}

	public void updateDetail() {
		if (this.getJtpListaPrecio().getSelectedIndex() == 1)
			actualizarPrecio();
	}
	
	public void deleteDetail() {
		if (this.getJtpListaPrecio().getSelectedIndex() == 1)
			removerPrecio();
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		Date fechaInicio = getCmbFechaInicio().getDate();
		Date fechaFin = getCmbFechaFin().getDate();

		Collection listaPrecios = null;
		boolean codigoRepetido = false;

		try {
			listaPrecios = SessionServiceLocator.getListaPrecioSessionService()
					.findListaPrecioByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator listaPreciosIt = listaPrecios.iterator();

		while (listaPreciosIt.hasNext()) {
			ListaPrecioIf listaPreciosIf = (ListaPrecioIf) listaPreciosIt
					.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(listaPreciosIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(listaPreciosIf.getCodigo()))
					if (getListaPrecioActualizadaIf().getId().equals(
							listaPreciosIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert(
					"El código de la Lista de Precios está duplicado !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(0);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(0);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(0);
			getTxtNombre().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaInicio().getSelectedItem())) || (getCmbFechaInicio()
				.getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger una Fecha Inicio !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(0);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaFin().getSelectedItem())) || (getCmbFechaFin()
				.getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger una Fecha Fin !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(0);
			getCmbFechaFin().grabFocus();
			return false;
		}
		if (fechaInicio.after(fechaFin)) {
			SpiritAlert.createAlert("La Fecha Inicio no puede estar después de la Fecha Fin!", SpiritAlert.INFORMATION);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if (vectorPrecioIf.isEmpty()) {
			SpiritAlert.createAlert("Debe agregar por lo menos un Precio !!",
					SpiritAlert.INFORMATION);
			getJtpListaPrecio().setSelectedIndex(1);
			getBtnProducto().grabFocus();
			return false;
		}

		return true;
	}

	public Vector getListaPrecioVector() {
		return listaPrecioVector;
	}

	public void setListaPrecioVector(Vector listaPrecioVector) {
		this.listaPrecioVector = listaPrecioVector;
	}

	public int getListaPrecioSeleccionada() {
		return listaPrecioSeleccionada;
	}

	public void setListaPrecioSeleccionada(int listaPrecioSeleccionada) {
		this.listaPrecioSeleccionada = listaPrecioSeleccionada;
	}

	public ListaPrecioIf getListaPrecioActualizadaIf() {
		return listaPrecioIf;
	}

	public void setListaPrecioActualizadaIf(
			ListaPrecioIf listaPrecioActualizadaIf) {
		this.listaPrecioIf = listaPrecioActualizadaIf;
	}

	public PrecioIf getPrecioIf() {
		return precioIf;
	}

	public void setPrecioIf(PrecioIf precioIf) {
		this.precioIf = precioIf;
	}

	public Vector<PrecioIf> getVectorPrecioIf() {
		return vectorPrecioIf;
	}

	public void setVectorPrecioIf(Vector<PrecioIf> vectorPrecioIf) {
		this.vectorPrecioIf = vectorPrecioIf;
	}

	public int getPrecioSeleccionado() {
		return precioSeleccionado;
	}

	public void setPrecioSeleccionado(int precioSeleccionado) {
		this.precioSeleccionado = precioSeleccionado;
	}

	public boolean isActualizarPrecio() {
		return actualizarPrecio;
	}

	public void setActualizarPrecio(boolean actualizarPrecio) {
		this.actualizarPrecio = actualizarPrecio;
	}
	 
}
