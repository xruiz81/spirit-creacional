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
import java.util.Iterator;
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
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.ProductoRetencionData;
import com.spirit.inventario.entity.ProductoRetencionIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.panel.JPProductoRetencion;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.inventario.session.ProductoRetencionSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ProductoRetencionModel extends JPProductoRetencion {
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private static final int MAX_LONGITUD_RETENCION = 3;
	private static Date fechaInicioCalculo;
	private static Date fechaFinCalculo;
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	private ProductoIf productoIf;
	private Vector productoRetencionVector = new Vector();
	private int productoRetencionSeleccionado;
	private ProductoRetencionIf productoRetencionActualizadoIf;
	private DefaultTableModel tableModel;
	
	public ProductoRetencionModel(){
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblProductoRetencion());
		cargarCombos();
		cargarListenersComponents();
		showSaveMode();
		getTblProductoRetencion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblProductoRetencion().addMouseListener(oMouseAdapterTblProductoRetencion);
		getTblProductoRetencion().addKeyListener(oKeyAdapterTblProductoRetencion);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblProductoRetencion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblProductoRetencion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblProductoRetencion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblProductoRetencion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblProductoRetencion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void initKeyListeners() {
		getTxtProducto().setEditable(false);
		reiniciarCombosFecha();
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getTxtRetencion().addKeyListener(new TextChecker(MAX_LONGITUD_RETENCION));
		getTxtRetencion().addKeyListener(new NumberTextField());
	}

	private void reiniciarCombosFecha() {
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
	}
	
	public void cargarCombos() {
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
	}
	
	private void cargarListenersComponents() {
		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String mmpg = "";
				productoCriteria = new ProductoCriteria("Producto", 0L, "", null, "A", true, mmpg);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
					try {
						GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
						
						if ("S".equals(generico.getUsaLote())) {
							PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
							getTxtProducto().setText(generico.getNombre() + " - " + presentacion.getNombre());
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
	
	MouseListener oMouseAdapterTblProductoRetencion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblProductoRetencion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setProductoRetencionSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			productoRetencionActualizadoIf = (ProductoRetencionIf) getProductoRetencionVector().get(getProductoRetencionSeleccionado());
			
			try {
				productoIf = SessionServiceLocator.getProductoSessionService().getProducto(productoRetencionActualizadoIf.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				if ("S".equals(generico.getUsaLote())) {
					PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					getTxtProducto().setText(generico.getNombre() + " - " + presentacion.getNombre());
				} else
					getTxtProducto().setText(generico.getNombre());
				
				getTxtRetencion().setText(productoRetencionActualizadoIf.getRetencion().toString());
				calendarFechaInicio.setTime(productoRetencionActualizadoIf.getFechaInicio());
				getCmbFechaInicio().setCalendar(calendarFechaInicio);
				getCmbFechaInicio().repaint();
				calendarFechaFin.setTime(productoRetencionActualizadoIf.getFechaFin());
				getCmbFechaFin().setCalendar(calendarFechaFin);
				getCmbFechaFin().repaint();
				
				if (productoRetencionActualizadoIf.getEstado().equals(ESTADO_ACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if (productoRetencionActualizadoIf.getEstado().equals(ESTADO_INACTIVO))
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
				ProductoRetencionData data = new ProductoRetencionData();
				data.setProductoId(productoIf.getId());		
				data.setRetencion(Long.parseLong(getTxtRetencion().getText()));
				data.setFechaInicio(DateHelperClient.getTimeStamp(getCmbFechaInicio().getDate()));
				data.setFechaFin(DateHelperClient.getTimeStamp(getCmbFechaFin().getDate()));
				
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
					data.setEstado(ESTADO_ACTIVO);
				else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
					data.setEstado(ESTADO_INACTIVO);
				
				SessionServiceLocator.getProductoRetencionSessionService().addProductoRetencion(data);
				showSaveMode();
				SpiritAlert.createAlert("Información guardada con éxito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			ProductoRetencionIf productoRetencionIf = (ProductoRetencionIf) getProductoRetencionVector().get(getProductoRetencionSeleccionado());
			SessionServiceLocator.getProductoRetencionSessionService().deleteProductoRetencion(productoRetencionIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Información eliminada con éxito !", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setProductoRetencionActualizadoIf((ProductoRetencionIf) getProductoRetencionVector().get(getProductoRetencionSeleccionado()));				
				getProductoRetencionActualizadoIf().setProductoId(productoIf.getId());
				getProductoRetencionActualizadoIf().setRetencion(Long.parseLong(getTxtRetencion().getText()));
				getProductoRetencionActualizadoIf().setFechaInicio(DateHelperClient.getTimeStamp(getCmbFechaInicio().getDate()));
				getProductoRetencionActualizadoIf().setFechaFin(DateHelperClient.getTimeStamp(getCmbFechaFin().getDate()));
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
					getProductoRetencionActualizadoIf().setEstado(ESTADO_ACTIVO);
				else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
					getProductoRetencionActualizadoIf().setEstado(ESTADO_INACTIVO);
				
				getProductoRetencionVector().setElementAt(getProductoRetencionActualizadoIf(), getProductoRetencionSeleccionado());
				SessionServiceLocator.getProductoRetencionSessionService().saveProductoRetencion(getProductoRetencionActualizadoIf());
				setProductoRetencionActualizadoIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Información actualizada con éxito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		productoIf = null;
		getTxtProducto().setText("");
		getTxtRetencion().setText("");
		reiniciarCombosFecha();
		getCmbEstado().setSelectedIndex(0);
		cleanTablaProductoRetencion();
	}
	
	public void cleanTablaProductoRetencion() {
		DefaultTableModel modelPrecio = (DefaultTableModel) getTblProductoRetencion().getModel();
		for (int i = this.getTblProductoRetencion().getRowCount(); i > 0; --i)
			modelPrecio.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getBtnBuscarProducto().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		cleanTablaProductoRetencion();
		try {
			Collection productoRetenciones = null;
			
			if (productoIf != null)
				productoRetenciones = SessionServiceLocator.getProductoRetencionSessionService().findProductoRetencionByProductoId(productoIf.getId());
			else
				productoRetenciones = SessionServiceLocator.getProductoRetencionSessionService().findProductoRetencionByEmpresaId(Parametros.getIdEmpresa());
			
			Iterator productoRetencionesIterator = productoRetenciones.iterator();

			if (!getProductoRetencionVector().isEmpty())
				getProductoRetencionVector().removeAllElements();

			while (productoRetencionesIterator.hasNext()) {
				ProductoRetencionIf productoRetencionIf = (ProductoRetencionIf) productoRetencionesIterator.next();
				
				tableModel = (DefaultTableModel) getTblProductoRetencion().getModel();
				Vector<String> fila = new Vector<String>();
				getProductoRetencionVector().add(productoRetencionIf);
				
				agregarColumnasTabla(productoRetencionIf, fila);
				
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(ProductoRetencionIf productoRetencionIf, Vector<String> fila) {
		try {
			ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(productoRetencionIf.getProductoId());
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
			if ("S".equals(generico.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(producto.getPresentacionId());
				fila.add(generico.getNombre()+ " - " + presentacion.getNombre());
			} else
				fila.add(generico.getNombre());
			
			fila.add(productoRetencionIf.getRetencion().toString());
			fila.add(Utilitarios.getFechaCortaUppercase(productoRetencionIf.getFechaInicio()));
			fila.add(Utilitarios.getFechaCortaUppercase(productoRetencionIf.getFechaFin()));
			
			if(productoRetencionIf.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(productoRetencionIf.getEstado().equals(ESTADO_INACTIVO))
				fila.add(NOMBRE_ESTADO_INACTIVO);
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		fechaInicioCalculo = getCmbFechaInicio().getDate();
		fechaFinCalculo = getCmbFechaFin().getDate();
	
		if ((("".equals(getTxtProducto().getText())) || (getTxtProducto().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un producto !!", SpiritAlert.WARNING);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		if (getTxtRetencion().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el porcentaje de retención !!", SpiritAlert.WARNING);
			getTxtRetencion().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaInicio().getSelectedItem())) || (getCmbFechaInicio().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar una fecha de inicio !!",	SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if (fechaInicioCalculo.after(fechaFinCalculo)) {
			SpiritAlert.createAlert("La fecha de inicio no puede ser posterior a la fecha final!", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if ((("".equals(getCmbEstado().getSelectedItem())) || (getCmbEstado().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar un estado !!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}

		return true;
	}
	 

	public ProductoRetencionIf getProductoRetencionActualizadoIf() {
		return productoRetencionActualizadoIf;
	}

	public void setProductoRetencionActualizadoIf(ProductoRetencionIf productoRetencionActualizadoIf) {
		this.productoRetencionActualizadoIf = productoRetencionActualizadoIf;
	}

	public int getProductoRetencionSeleccionado() {
		return productoRetencionSeleccionado;
	}

	public void setProductoRetencionSeleccionado(int productoRetencionSeleccionado) {
		this.productoRetencionSeleccionado = productoRetencionSeleccionado;
	}

	public Vector getProductoRetencionVector() {
		return productoRetencionVector;
	}

	public void setProductoRetencionVector(Vector productoRetencionVector) {
		this.productoRetencionVector = productoRetencionVector;
	}
}
