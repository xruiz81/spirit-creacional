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
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.StockData;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.panel.JPStock;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class StockModel extends JPStock {

	private static final int MAX_LONGITUD_CANTIDAD = 10;
	private static final int MAX_LONGITUD_RESERVA = 10;
	private static final int MAX_LONGITUD_TRANSITO = 10;
	private static final int MAX_LONGITUD_PISO = 10;
	private static final int MAX_LONGITUD_FILA = 255;
	private static final int MAX_LONGITUD_PERCHA = 255;
	private static final int MAX_LONGITUD_NIVEL_PERCHA = 255;

	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	protected ProductoIf productoIf;
	private Vector stockVector = new Vector();
	private DefaultTableModel tableModel;
	protected StockIf stockIf;
	private int stockSeleccionado;

	public StockModel() {
		anchoColumnasTabla();
		initKeyListeners();
		initListeners();
		cargarCombos();
		showSaveMode();
		getTblStock().addMouseListener(oMouseAdapterTblStock);
		getTblStock().addKeyListener(oKeyAdapterTblStock);
		new HotKeyComponent(this);
	}

	private void anchoColumnasTabla() {
		getTblStock().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn anchoColumna = getTblStock().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblStock().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblStock().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblStock().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblStock().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}

	public void cargarCombos() {
		cargarComboBodega();
		cargarComboLote();
	}

	private void cargarComboBodega() {
		try {
			List bodegas = (List) getBodegaSessionService()
					.findBodegaByOficinaId(Parametros.getIdOficina());
			refreshCombo(getCmbBodega(), bodegas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	private void cargarComboLote() {
		try {
			List lotes = (List) getLoteSessionService().findLoteByEmpresaId(
					Parametros.getIdEmpresa());
			refreshCombo(getCmbLote(), lotes);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	MouseListener oMouseAdapterTblStock = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblStock = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			try {
				int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
				setStockSeleccionado(((JTable) evt.getSource())
						.convertRowIndexToModel(selectedRow));
				stockIf = (StockIf) getStockVector()
						.get(getStockSeleccionado());
				getCmbBodega().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(getCmbBodega(),
								stockIf.getBodegaId()));
				getCmbBodega().repaint();
				if (stockIf.getProductoId() != null) {
					productoIf = getProductoSessionService().getProducto(
							stockIf.getProductoId());
					GenericoIf generico = getGenericoSessionService()
							.getGenerico(productoIf.getGenericoId());
					getTxtProducto().setText(
							productoIf.getCodigo() + " - "
									+ generico.getNombre());
				} else
					productoIf = null;

				getCmbLote().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(getCmbLote(),
								stockIf.getLoteId()));
				getCmbLote().repaint();
				int anio = Integer.valueOf(stockIf.getAnio()) - 1900;
				int mes = Integer.valueOf(stockIf.getMes()) - 1;
				getCmbFecha().setDate(Utilitarios.fechaHoy(mes, anio));
				getTxtCantidad().setText(stockIf.getCantidad().toString());
				if (stockIf.getReserva() != null)
					getTxtReserva().setText(stockIf.getReserva().toString());
				if (stockIf.getTransito() != null)
				getTxtTransito().setText(stockIf.getTransito().toString());
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void initListeners() {
		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					String mmpg = "";
					productoCriteria = new ProductoCriteria("Producto", 0L, "",
							null, "C", true, mmpg);
					popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), productoCriteria,
							JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if (popupFinder.getElementoSeleccionado() != null) {
						productoIf = (ProductoIf) popupFinder
								.getElementoSeleccionado();
						GenericoIf generico = getGenericoSessionService()
								.getGenerico(productoIf.getGenericoId());
						getTxtProducto().setText(
								productoIf.getCodigo() + " - "
										+ generico.getNombre());
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initKeyListeners() {
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setShowNoneButton(false);
		getCmbFecha().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFecha().setEditable(false);

		getTxtProducto().setEditable(false);
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtReserva().addKeyListener(new TextChecker(MAX_LONGITUD_RESERVA));
		getTxtTransito().addKeyListener(new TextChecker(MAX_LONGITUD_TRANSITO));
		getTxtPiso().addKeyListener(new TextChecker(MAX_LONGITUD_PISO));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtReserva().addKeyListener(new NumberTextField());
		getTxtTransito().addKeyListener(new NumberTextField());
		getTxtPiso().addKeyListener(new NumberTextField());
		getTxtFila().addKeyListener(new TextChecker(MAX_LONGITUD_FILA));
		getTxtPercha().addKeyListener(new TextChecker(MAX_LONGITUD_PERCHA));
		getTxtNivelPercha().addKeyListener(
				new TextChecker(MAX_LONGITUD_NIVEL_PERCHA));
	}

	public void save() {
		try {
			if (validateFields()) {
				StockData data = new StockData();
				data.setBodegaId(((BodegaIf) getCmbBodega().getSelectedItem())
						.getId());
				data.setProductoId(productoIf.getId());
				data.setLoteId(((LoteIf) getCmbLote().getSelectedItem())
						.getId());

				int anio = getCmbFecha().getDate().getYear() + 1900;
				int mes = getCmbFecha().getDate().getMonth() + 1;
				data.setAnio(String.valueOf(anio));
				data.setMes(String.valueOf(mes));

				data.setCantidad(BigDecimal.valueOf(Double
						.parseDouble(getTxtCantidad().getText())));
				data.setReserva(BigDecimal.valueOf(Double
						.parseDouble(getTxtReserva().getText())));
				data.setTransito(BigDecimal.valueOf(Double
						.parseDouble(getTxtTransito().getText())));
				getStockSessionService().addStock(data);
				SpiritAlert.createAlert("Stock guardado con éxito",
						SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",
					SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			StockIf stockIf = (StockIf) getStockVector().get(
					getStockSeleccionado());
			getStockSessionService().deleteStock(stockIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Stock eliminado con éxito",
					SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!",
					SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				stockIf.setBodegaId(((BodegaIf) getCmbBodega()
						.getSelectedItem()).getId());
				stockIf.setProductoId(productoIf.getId());
				stockIf.setLoteId(((LoteIf) getCmbLote().getSelectedItem())
						.getId());

				int anio = getCmbFecha().getDate().getYear() + 1900;
				int mes = getCmbFecha().getDate().getMonth() + 1;
				stockIf.setAnio(String.valueOf(anio));
				stockIf.setMes(String.valueOf(mes));

				stockIf.setCantidad(BigDecimal.valueOf(Double
						.parseDouble(getTxtCantidad().getText())));
				stockIf.setReserva(BigDecimal.valueOf(Double
						.parseDouble(getTxtReserva().getText())));
				stockIf.setTransito(BigDecimal.valueOf(Double
						.parseDouble(getTxtTransito().getText())));
				getStockSessionService().saveStock(stockIf);
				getStockVector().setElementAt(stockIf, getStockSeleccionado());
				setStockIf(null);
				SpiritAlert.createAlert("Stock actualizado con éxito",
						SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!",
					SpiritAlert.ERROR);
		}
	}

	public void clean() {
		Calendar calendar = new GregorianCalendar();
		getCmbFecha().setCalendar(calendar);

		productoIf = null;
		stockVector.clear();
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getTxtReserva().setText("");
		getTxtTransito().setText("");
		getTxtPiso().setText("");
		getTxtFila().setText("");
		getTxtPercha().setText("");
		getTxtNivelPercha().setText("");

		cleanTabla();
	}
	
	private void cleanTabla()
	{
		DefaultTableModel model = (DefaultTableModel) getTblStock().getModel();
		for (int i = this.getTblStock().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getCmbBodega().grabFocus();
	}

	private void cargarTabla() {
		try {
			Collection stocks = getStockSessionService().getStockList();
			Iterator stocksIterator = stocks.iterator();

			if (!getStockVector().isEmpty()) {
				getStockVector().removeAllElements();
			}

			while (stocksIterator.hasNext()) {
				StockIf stockIf = (StockIf) stocksIterator.next();

				tableModel = (DefaultTableModel) getTblStock().getModel();
				Vector<String> fila = new Vector<String>();
				getStockVector().add(stockIf);

				agregarColumnasTabla(stockIf, fila);

				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblStock(), stocks, 0);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(StockIf stockIf, Vector<String> fila) {
		try {
			BodegaIf bodega = getBodegaSessionService().getBodega(
					stockIf.getBodegaId());
			fila.add(bodega.getNombre());

			if (stockIf.getProductoId() != null) {
				ProductoIf producto = getProductoSessionService().getProducto(
						stockIf.getProductoId());
				GenericoIf generico = getGenericoSessionService().getGenerico(
						producto.getGenericoId());
				fila.add(producto.getCodigo() + " - " + generico.getNombre());
			} else
				fila.add("-");

			LoteIf lote = getLoteSessionService().getLote(stockIf.getLoteId());
			fila.add(lote.getCodigo());

			fila.add(Utilitarios.getNombreMes(Integer
					.parseInt(stockIf.getMes()))
					+ " - " + stockIf.getAnio());
			fila.add(stockIf.getCantidad().toString());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if (getCmbBodega().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una Bodega !",
					SpiritAlert.WARNING);
			getCmbBodega().grabFocus();
			return false;
		}
		if (productoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un Producto !",
					SpiritAlert.WARNING);
			getBtnProducto().grabFocus();
			return false;
		}
		if (getCmbLote().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Lote !",
					SpiritAlert.WARNING);
			getCmbLote().grabFocus();
			return false;
		}
		if (getCmbFecha().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Año y Mes !",
					SpiritAlert.WARNING);
			getCmbFecha().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCantidad().getText())) || (getTxtCantidad()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Cantidad !",
					SpiritAlert.WARNING);
			getTxtCantidad().grabFocus();
			return false;
		}
		if ((("".equals(getTxtReserva().getText())) || (getTxtReserva()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Reserva !",
					SpiritAlert.WARNING);
			getTxtReserva().grabFocus();
			return false;
		}
		if ((("".equals(getTxtTransito().getText())) || (getTxtTransito()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el Transito !",
					SpiritAlert.WARNING);
			getTxtTransito().grabFocus();
			return false;
		}
		if ((("".equals(getTxtPiso().getText())) || (getTxtPiso().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un Piso !",
					SpiritAlert.WARNING);
			getTxtPiso().grabFocus();
			return false;
		}
		if ((("".equals(getTxtFila().getText())) || (getTxtFila().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Fila !",
					SpiritAlert.WARNING);
			getTxtFila().grabFocus();
			return false;
		}
		if ((("".equals(getTxtPercha().getText())) || (getTxtPercha().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Percha !",
					SpiritAlert.WARNING);
			getTxtPercha().grabFocus();
			return false;
		}
		if ((("".equals(getTxtNivelPercha().getText())) || (getTxtNivelPercha()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nivel de Percha !",
					SpiritAlert.WARNING);
			getTxtNivelPercha().grabFocus();
			return false;
		}
		return true;
	}

	public void refresh() {
		cargarComboBodega();
		cargarComboLote();
		cleanTabla();
		cargarTabla();
	}

	public static BodegaSessionService getBodegaSessionService() {
		try {
			return (BodegaSessionService) ServiceLocator
					.getService(ServiceLocator.BODEGASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LoteSessionService getLoteSessionService() {
		try {
			return (LoteSessionService) ServiceLocator
					.getService(ServiceLocator.LOTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static StockSessionService getStockSessionService() {
		try {
			return (StockSessionService) ServiceLocator
					.getService(ServiceLocator.STOCKSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoSessionService getProductoSessionService() {
		try {
			return (ProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static GenericoSessionService getGenericoSessionService() {
		try {
			return (GenericoSessionService) ServiceLocator
					.getService(ServiceLocator.GENERICOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public StockIf getStockIf() {
		return stockIf;
	}

	public void setStockIf(StockIf stockIf) {
		this.stockIf = stockIf;
	}

	public int getStockSeleccionado() {
		return stockSeleccionado;
	}

	public void setStockSeleccionado(int stockSeleccionado) {
		this.stockSeleccionado = stockSeleccionado;
	}

	public Vector getStockVector() {
		return stockVector;
	}

	public void setStockVector(Vector stockVector) {
		this.stockVector = stockVector;
	}
}
