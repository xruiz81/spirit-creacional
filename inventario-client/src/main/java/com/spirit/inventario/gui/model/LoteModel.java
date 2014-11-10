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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.util.DateHelperClient;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteData;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.panel.JPLote;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class LoteModel extends JPLote {
	private static int MAX_LONGITUD_CODIGO = 20;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static Date fechaInicioCalculo;
	private static Date fechaFinCalculo;
	private Calendar calendarFechaElaboracion = new GregorianCalendar();
	private Calendar calendarFechaVencimiento = new GregorianCalendar();
	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	private ProductoIf productoIf;
	private Vector loteVector = new Vector();
	private int loteSeleccionado;
	private LoteIf loteActualizadoIf;
	private DefaultTableModel tableModel;
	Map<Long, ProductoIf> productosMap = new HashMap<Long, ProductoIf>();
	Map<Long, GenericoIf> genericosMap = new HashMap<Long, GenericoIf>();
	Map<Long, PresentacionIf> presentacionesMap = new HashMap<Long, PresentacionIf>();
	
	public LoteModel(){
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblLote());
		cargarCombos();
		cargarListenersComponents();
		showSaveMode();
		getTblLote().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblLote().addMouseListener(oMouseAdapterTblLote);
		getTblLote().addKeyListener(oKeyAdapterTblLote);
		
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblLote().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblLote().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(280);
		anchoColumna = getTblLote().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblLote().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblLote().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtProducto().setEditable(false);
				
		reiniciarCombosFecha();
		getCmbFechaElaboracion().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaElaboracion().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaElaboracion().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
	}

	private void reiniciarCombosFecha() {
		Calendar calendar = new GregorianCalendar();
		getCmbFechaElaboracion().setCalendar(calendar);
		getCmbFechaVencimiento().setCalendar(calendar);
	}
	
	public void cargarCombos() {
		getCmbFechaElaboracion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
	}
	
	private void cargarListenersComponents() {
		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String mmpg = "";
				productoCriteria = new ProductoCriteria("Producto", 0L, "", null, "C",true, mmpg);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
					try {
						GenericoIf generico = SessionServiceLocator.getGenericoSessionService()
								.getGenerico(productoIf.getGenericoId());
						
						if ("S".equals(generico.getUsaLote())) {
							PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService()
								.getPresentacion(productoIf.getPresentacionId());
							getTxtProducto().setText(generico.getNombre()+ " - "
								+ presentacion.getNombre());
						} else
							getTxtProducto().setText(generico.getNombre());
		
						if(getMode() == SpiritMode.SAVE_MODE)
							cargarTabla();
						
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblLote = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblLote = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setLoteSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			loteActualizadoIf = (LoteIf) getLoteVector().get(getLoteSeleccionado());
			
			try {
				getTxtCodigo().setText(loteActualizadoIf.getCodigo());
				productoIf = SessionServiceLocator.getProductoSessionService().getProducto(loteActualizadoIf.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				if ("S".equals(generico.getUsaLote())) {
					PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					getTxtProducto().setText(generico.getNombre() + " - " + presentacion.getNombre());
				} else
					getTxtProducto().setText(generico.getNombre());
				
				calendarFechaElaboracion.setTime(loteActualizadoIf.getFechaElaboracion());
				getCmbFechaElaboracion().setCalendar(calendarFechaElaboracion);
				getCmbFechaElaboracion().repaint();
				if (loteActualizadoIf.getFechaVencimiento() != null) {
					calendarFechaVencimiento.setTime(loteActualizadoIf.getFechaVencimiento());
					getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
					getCmbFechaVencimiento().repaint();
				}
				
				if (loteActualizadoIf.getEstado().equals(ESTADO_ACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if (loteActualizadoIf.getEstado().equals(ESTADO_INACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
				showUpdateMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				LoteData data = new LoteData();
				
				data.setCodigo(getTxtCodigo().getText());
				data.setProductoId(productoIf.getId());
				
				try {
					data.setFechaCreacion(DateHelperClient.getTimeStampNow());
				} catch (Exception e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
				
				data.setFechaElaboracion(DateHelperClient.getTimeStamp(getCmbFechaElaboracion().getDate()));
				data.setFechaVencimiento(DateHelperClient.getTimeStamp(getCmbFechaVencimiento().getDate()));
				
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
					data.setEstado(ESTADO_ACTIVO);
				else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
					data.setEstado(ESTADO_INACTIVO);
				
				SessionServiceLocator.getLoteSessionService().addLote(data);
				showSaveMode();
				SpiritAlert.createAlert("Lote guardado con éxito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			LoteIf loteIf = (LoteIf) getLoteVector().get(getLoteSeleccionado());
			SessionServiceLocator.getLoteSessionService().deleteLote(loteIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Lote eliminado con éxito !", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setLoteActualizadoIf((LoteIf) getLoteVector().get(getLoteSeleccionado()));				
				
				getLoteActualizadoIf().setCodigo(getTxtCodigo().getText());
				getLoteActualizadoIf().setProductoId(productoIf.getId());
				getLoteActualizadoIf().setFechaElaboracion(DateHelperClient.getTimeStamp(getCmbFechaElaboracion().getDate()));
				getLoteActualizadoIf().setFechaVencimiento(DateHelperClient.getTimeStamp(getCmbFechaVencimiento().getDate()));
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
					getLoteActualizadoIf().setEstado(ESTADO_ACTIVO);
				else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
					getLoteActualizadoIf().setEstado(ESTADO_INACTIVO);
				
				getLoteVector().setElementAt(getLoteActualizadoIf(), getLoteSeleccionado());
				SessionServiceLocator.getLoteSessionService().saveLote(getLoteActualizadoIf());
				setLoteActualizadoIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Lote actualizado con éxito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		productoIf = null;
		getTxtProducto().setText("");
		reiniciarCombosFecha();
		getCmbEstado().setSelectedIndex(0);
		cleanTablaLote();
	}
	
	public void cleanTablaLote() {
		DefaultTableModel modelPrecio = (DefaultTableModel) getTblLote().getModel();
		for (int i = this.getTblLote().getRowCount(); i > 0; --i)
			modelPrecio.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarMapas();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarMapas() {
		try {
			productosMap = mapearProductos();
			genericosMap = mapearGenericos();
			presentacionesMap = mapearPresentaciones();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar mapas de datos", SpiritAlert.ERROR);
		}
	}
	
	private Map<Long, ProductoIf> mapearProductos() throws GenericBusinessException {
		Map<Long, ProductoIf> productosMap = new HashMap<Long, ProductoIf>();
		Map queryMap = new HashMap();
		//queryMap.put("estado", "A");
		//queryMap.put("permiteventa", "S");
		Iterator<ProductoIf> it = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaId(Parametros.getIdEmpresa()).iterator();
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
	
	private void cargarTabla() {
		cleanTablaLote();
		try {
			Collection lotes = null;
			
			if (productoIf != null)
				lotes = SessionServiceLocator.getLoteSessionService().findLoteByProductoId(productoIf.getId());
			else
				lotes = SessionServiceLocator.getLoteSessionService().findLoteByEmpresaId(Parametros.getIdEmpresa());
			
			Iterator lotesIterator = lotes.iterator();

			if (!getLoteVector().isEmpty())
				getLoteVector().removeAllElements();

			while (lotesIterator.hasNext()) {
				LoteIf lotesIf = (LoteIf) lotesIterator.next();
				
				tableModel = (DefaultTableModel) getTblLote().getModel();
				Vector<String> fila = new Vector<String>();
				getLoteVector().add(lotesIf);
				
				agregarColumnasTabla(lotesIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblLote(), lotes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(LoteIf lotesIf, Vector<String> fila) {
		fila.add(lotesIf.getCodigo());
		
		ProductoIf producto = productosMap.get(lotesIf.getProductoId());
		GenericoIf generico = genericosMap.get(producto.getGenericoId());
		if ("S".equals(generico.getUsaLote())) {
			PresentacionIf presentacion = presentacionesMap.get(producto.getPresentacionId());
			fila.add(generico.getNombre()+ " - " + presentacion.getNombre());
		} else
			fila.add(generico.getNombre());
		
		fila.add(Utilitarios.getFechaCortaUppercase(lotesIf.getFechaElaboracion()));
		if (lotesIf.getFechaVencimiento() != null)
			fila.add(Utilitarios.getFechaCortaUppercase(lotesIf.getFechaVencimiento()));
		else
			fila.add("");
		
		if(lotesIf.getEstado().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else if(lotesIf.getEstado().equals(ESTADO_INACTIVO))
			fila.add(NOMBRE_ESTADO_INACTIVO);
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		fechaInicioCalculo = getCmbFechaElaboracion().getDate();
		fechaFinCalculo = getCmbFechaVencimiento().getDate();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(getTxtProducto().getText())) || (getTxtProducto().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un producto !!", SpiritAlert.WARNING);
			getBtnProducto().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaElaboracion().getSelectedItem())) || (getCmbFechaElaboracion().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger una Fecha de Elaboración !!",	SpiritAlert.WARNING);
			getCmbFechaElaboracion().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaVencimiento().getSelectedItem())) || (getCmbFechaVencimiento().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger una Fecha de Vencimiento !!", SpiritAlert.WARNING);
			getCmbFechaVencimiento().grabFocus();
			return false;
		}
		if (fechaInicioCalculo.after(fechaFinCalculo)) {
			SpiritAlert.createAlert("La Fecha de Elaboración no puede estar después de la Fecha de Vencimiento!", SpiritAlert.WARNING);
			getCmbFechaElaboracion().grabFocus();
			return false;
		}
		if ((("".equals(getCmbEstado().getSelectedItem())) || (getCmbEstado().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger un Estado !!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
				
		Collection lotes = null;
		boolean codigoRepetido = false;

		try {
			lotes = SessionServiceLocator.getLoteSessionService().findLoteByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator lotesIt = lotes.iterator();

		while (lotesIt.hasNext()) {
			LoteIf lotesIf = (LoteIf) lotesIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(lotesIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(lotesIf.getCodigo()))
					if (getLoteActualizadoIf().getId().equals(loteActualizadoIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert(
					"El código del Lote está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		return true;
	}
	 

	public LoteIf getLoteActualizadoIf() {
		return loteActualizadoIf;
	}

	public void setLoteActualizadoIf(LoteIf loteActualizadoIf) {
		this.loteActualizadoIf = loteActualizadoIf;
	}

	public int getLoteSeleccionado() {
		return loteSeleccionado;
	}

	public void setLoteSeleccionado(int loteSeleccionado) {
		this.loteSeleccionado = loteSeleccionado;
	}

	public Vector getLoteVector() {
		return loteVector;
	}

	public void setLoteVector(Vector loteVector) {
		this.loteVector = loteVector;
	}
}
