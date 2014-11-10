package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.client.model.SwingWorker;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaData;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.gui.criteria.MovimientoCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.criteria.SolicitudTransferenciaCriteria;
import com.spirit.inventario.gui.helper.CatalogoLocator;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.QueryHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.helper.ValidateHelper;
import com.spirit.inventario.gui.helper.ValueHelper;
import com.spirit.inventario.gui.panel.JPSolicitudTransferencia;
import com.spirit.inventario.gui.tblmodel.SolicitudTranferenciaDetalleTableModel;
import com.spirit.inventario.gui.tblmodel.SolicitudTransferenciaDetalleRowModel;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class SolicitudTransferenciaModel extends JPSolicitudTransferencia {

	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_CANTIDAD_DETALLE = 6;

	private SolicitudTransferenciaIf data;
	private ProductoIf productoIf;

	private HashMap<String, Object> mapaQuery = new HashMap<String, Object>();

	private JDPopupFinderModel popupFinder;

	private SolicitudTranferenciaDetalleTableModel solicitudTranferenciaDetalleTableModel = null;

	public SolicitudTransferenciaModel() {
		anchoColumnasTabla();
		iniciarComponentes();
		initKeyListeners();
		initListeners();
		this.cargarCombos();
		this.showSaveMode();
		new HotKeyComponent(this);
	}

	private void anchoColumnasTabla() {
		getTblMovimientoDetalle().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblMovimientoDetalle().getColumnModel()
				.getColumn(0);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblMovimientoDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblMovimientoDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
	}

	private void iniciarComponentes() {
		getTxtProducto().setEditable(false);
		getCmbFechaDocumento().setLocale(Utilitarios.esLocale);
		getCmbFechaDocumento().setShowNoneButton(false);
		getCmbFechaDocumento().setEditable(false);

		getCmbFechaDocumento().setFormat(Utilitarios.setFechaUppercase());

		/** *************************TABLA DETALLE**************************** */

		solicitudTranferenciaDetalleTableModel = new SolicitudTranferenciaDetalleTableModel();
		getTblMovimientoDetalle().setModel(
				solicitudTranferenciaDetalleTableModel);

		getTblMovimientoDetalle().getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int index = getTblMovimientoDetalle().getSelectedRow();
						if (!e.getValueIsAdjusting() && index >= 0) {
							setSolicitudTransferenciaDetalle(solicitudTranferenciaDetalleTableModel
									.getRow(index));
						}

					}
				});

		/** *************************LISTENERS**************************** */

		getBtnBuscarProducto().setToolTipText("Buscar Producto");
		getBtnBuscarProducto().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");
		getBtnAgregarDetalle().setText("");
		getBtnAgregarDetalle().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");
		getBtnEliminarDetalle().setText("");
		getBtnEliminarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));
	}

	private void initKeyListeners() {
		getTxtCantidad().addKeyListener(new TextChecker(MAX_CANTIDAD_DETALLE));
		getTxtCantidad().addKeyListener(new NumberTextFieldDecimal());

		getTxtObservacion().addKeyListener(
				new TextChecker(MAX_LONGITUD_OBSERVACION));
	}

	private void initListeners() {
		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});

		/** **************DETALLE********************* */
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				completarSolicitudTranferenciaDetalleIf(true);
			}
		});

		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				completarSolicitudTranferenciaDetalleIf(false);
			}
		});

		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int index = getTblMovimientoDetalle().getSelectedRow();
				if (index >= 0) {
					solicitudTranferenciaDetalleTableModel.remove(index);
				}
			}
		});
	}

	/** ************************ COMBOS ************************************ */

	private void cargarCombos() {
		new SwingWorker()
		{
			public Object construct() {
				ValueHelper.fillCombo(getCmbEstado(),
						CatalogoLocator.CAT_ESTADO_SOLICITUD_TRF.getArray());

				if (getMode() == SpiritMode.FIND_MODE) {
					getCmbFechaDocumento().setSelectedItem(null);
				}
				cargarComboBodega();
				cargarComboBodegaTransferencia();

				return null;
			}
		};
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBodega() {
		try {
			getCmbBodega().addItem("Cargando Items..");
			List bodegas = (List) SessionServiceLocator
					.getBodegaSessionService().getBodegaList();
			getCmbBodega().removeAllItems();
			refreshCombo(getCmbBodega(), bodegas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBodegaTransferencia() {
		try {
			getCmbBodegaTransferencia().addItem("Cargando Items..");
			getCmbBodegaTransferencia().setEnabled(true);
			List bodegasTransferencia = (List) SessionServiceLocator
					.getBodegaSessionService().getBodegaList();
			getCmbBodegaTransferencia().removeAllItems();
			refreshCombo(getCmbBodegaTransferencia(), bodegasTransferencia);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboLote(Long productoId) {
		try {
			List lotes = (List) SessionServiceLocator.getLoteSessionService()
					.findLoteByProductoId(productoId);
			if (lotes.size() > 0) {
				refreshCombo(getCmbLote(), lotes);
				if (getMode() != SpiritMode.UPDATE_MODE)
					getCmbLote().setEnabled(true);
				else
					getCmbLote().setEnabled(false);
			} else {
				getCmbLote().setSelectedIndex(-1);
				getCmbLote().setEnabled(false);
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	/** ************************ BUSQUEDAS ************************************ */
	private void buscarProducto() {
		try {
			String mmpg = "";
			ProductoCriteria productoCriteria = new ProductoCriteria(
					"Producto", 0L, "", null, "C", true, mmpg);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
				getTxtProducto().setText(
						ConsultasHelper.getTxtProducto(productoIf));
				cargarComboLote(productoIf.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setDetalleSolicitudTranferencia(
			List<SolicitudTransferenciaDetalleIf> listaSolicitudTranferenciaDetalle) {
		for (SolicitudTransferenciaDetalleIf movimientoDetalleIf : listaSolicitudTranferenciaDetalle) {
			solicitudTranferenciaDetalleTableModel
					.addSolicitudTransferenciaDetalle(movimientoDetalleIf);
		}
	}

	/** ************************************************************************* */

	private void setSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf) {
		data = solicitudTransferenciaIf;
		ValueHelper.setValue(getCmbEstado(),
				CatalogoLocator.CAT_ESTADO_SOLICITUD_TRF
						.getCatalogoByCodigo(solicitudTransferenciaIf
								.getEstado()));
		ValueHelper.setValue(getTxtCodigo(), solicitudTransferenciaIf
				.getCodigo());
		ValueHelper.setValue(getCmbFechaDocumento(), solicitudTransferenciaIf
				.getFechaDocumento());

		ValueHelper.setValue(getCmbBodega(), solicitudTransferenciaIf
				.getBodegaDesdeId());
		if (solicitudTransferenciaIf.getBodegaHaciaId() != null) {
			ValueHelper.setValue(getCmbBodegaTransferencia(),
					solicitudTransferenciaIf.getBodegaHaciaId());
		}
		try {
			setDetalleSolicitudTranferencia((List) SessionServiceLocator
					.getSolicitudTransferenciaDetalleSessionService()
					.findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(
							data.getId()));
		} catch (GenericBusinessException e) {
			// TODO: ALERT
			e.printStackTrace();
		}
	}

	private void setSolicitudTransferenciaDetalle(
			SolicitudTransferenciaDetalleRowModel row) {
		if (row == null)
			return;

		ValueHelper.getValue(getTxtCantidad(), row.getCantidad());

		if (row.getProducto() != null) {
			productoIf = row.getProducto();
			getTxtProducto().setText(
					ConsultasHelper.getTxtProducto(row.getProducto()));
			cargarComboLote(row.getProducto().getId());
		}
	}

	private void completarSolicitudTranferenciaDetalleIf(boolean nuevo) {
		if (validarIngresoDetalle()) {
			SolicitudTransferenciaDetalleRowModel solicitudTransferenciaRowModel = null;

			if (nuevo) {
				solicitudTransferenciaRowModel = new SolicitudTransferenciaDetalleRowModel();
			} else {
				solicitudTransferenciaRowModel = solicitudTranferenciaDetalleTableModel
						.getRow(getTblMovimientoDetalle().getSelectedRow());
			}
			solicitudTransferenciaRowModel.setLoteIf((LoteIf) getCmbLote()
					.getSelectedItem());

			solicitudTransferenciaRowModel.setCantidad(ValueHelper
					.convertValueToBigDecimal(getTxtCantidad()));
			solicitudTransferenciaRowModel.setProducto(productoIf);

			if (nuevo) {
				solicitudTranferenciaDetalleTableModel
						.addRow(solicitudTransferenciaRowModel);
			}
			getTblMovimientoDetalle().repaint();

			// cleanMovimientoDetalle();
		}
	}

	private boolean validarIngresoDetalle() {
		if (getTxtProducto().getText() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Producto!",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		if (getCmbLote().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Lote!",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			getCmbLote().grabFocus();
			return false;
		}
		if (getTxtCantidad().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar una Cantidad!",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			getTxtCantidad().grabFocus();
			return false;
		}
		return true;
	}

	public void save() {
		if (validateFields()) {
			try {
				SolicitudTransferenciaIf solicitudTransferencia = registrarSolicitudTransferencia(true);
				List<SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalle = solicitudTranferenciaDetalleTableModel
						.getAllSolicitudTransferenciaDetalleIf();
				if (validateDetalleCompleto(listaSolicitudTransferenciaDetalle)) {
					SessionServiceLocator
							.getSolicitudTransferenciaSessionService()
							.procesarSolicitudTransferencia(
									solicitudTransferencia,
									listaSolicitudTransferenciaDetalle);
					SpiritAlert.createAlert("Solicitud guardada con éxito",
							SpiritAlert.INFORMATION);
					showSaveMode();
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	/** ***********************************VALIDATE**************************** */

	public boolean validateFields() {
		String estado = CatalogoLocator.CAT_ESTADO_SOLICITUD_TRF
				.getCodigoSeleccionado(getCmbEstado());
		if (estado.equalsIgnoreCase("A")) {
			SpiritAlert.createAlert(
					"No pueden ingresarse solicitudes con estado Atendido",
					SpiritAlert.WARNING);
			abrirPanelCabecera();
			return false;
		}

		BodegaIf bodegaDesde = (BodegaIf) getCmbBodega().getSelectedItem();
		BodegaIf bodegaHacia = (BodegaIf) getCmbBodegaTransferencia()
				.getSelectedItem();
		if (bodegaDesde.getCodigo().equalsIgnoreCase(bodegaHacia.getCodigo())) {
			SpiritAlert.createAlert(
					"No pueden realizarse solicitudes sobre la misma bodega",
					SpiritAlert.WARNING);
			abrirPanelCabecera();

			return false;
		}

		if (solicitudTranferenciaDetalleTableModel
				.getAllSolicitudTransferenciaDetalleIf().size() <= 0) {
			SpiritAlert.createAlert(
					"Debe ingresar el detalle para la solicitud",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			return false;
		}

		return true;
	}

	private boolean validateDetalleCompleto(
			List<SolicitudTransferenciaDetalleIf> listaSolicitudTransferencia) {
		for (int i = 0; i < listaSolicitudTransferencia.size(); i++) {
			if (!validateDetalle(listaSolicitudTransferencia.get(i))) {
				getTblMovimientoDetalle().getSelectionModel()
						.setLeadSelectionIndex(i);
				return false;
			}
		}
		return true;
	}

	private boolean validateDetalle(
			SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf) {
		if (solicitudTransferenciaDetalleIf.getLoteId() == null) {
			SpiritAlert.createAlert("Debe ingresar el lote",
					SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	private SolicitudTransferenciaIf registrarSolicitudTransferencia(
			boolean nuevo) throws GenericBusinessException {

		if (nuevo) {
			data = new SolicitudTransferenciaData();
		}

		BodegaIf bodega = (BodegaIf) getCmbBodega().getSelectedItem();
		data.setBodegaDesdeId(bodega.getId());

		BodegaIf bodegaRef = (BodegaIf) getCmbBodegaTransferencia()
				.getSelectedItem();
		if (bodegaRef != null)
			data.setBodegaHaciaId(bodegaRef.getId());

		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		data.setObservacion(getTxtObservacion().getText());
		data.setEstado(CatalogoLocator.CAT_ESTADO_SOLICITUD_TRF
				.getCodigoSeleccionado(getCmbEstado()));
		return data;
	}

	public void delete() {
		try {
			if (data.getEstado().equalsIgnoreCase("P")) {
				SpiritAlert.createAlert(
						"Solo se permite eliminar solicitudes pendientes..",
						SpiritAlert.ERROR);
				return;
			}
			SessionServiceLocator.getSolicitudTransferenciaSessionService()
					.eliminarSolicitudTransferencia(data);

			SpiritAlert.createAlert("Solicitud eliminada con exito..",
					SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			SpiritAlert.createAlert("Error al eliminar la solicitud..",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void report() {
	}

	public void refresh() {
		// TODO: Cualquier huevada..
	}

	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void abrirPanelDetalle() {
		this.getJtpMovimiento().setSelectedIndex(1);
	}

	private void abrirPanelCabecera() {
		this.getJtpMovimiento().setSelectedIndex(0);
	}

	public void switchTab() {
		int selectedTab = this.getJtpMovimiento().getSelectedIndex();
		int tabCount = this.getJtpMovimiento().getTabCount();

		selectedTab++;

		if (selectedTab >= tabCount)
			selectedTab = 0;

		this.getJtpMovimiento().setSelectedIndex(selectedTab);
	}

	public void update() {
		if (validateFields()) {
			try {
				SolicitudTransferenciaIf movimiento = registrarSolicitudTransferencia(false);
				List<SolicitudTransferenciaDetalleIf> listaMovimientoDetalle = solicitudTranferenciaDetalleTableModel
						.getAllSolicitudTransferenciaDetalleIf();
				if (validateDetalleCompleto(listaMovimientoDetalle)) {
					/*
					 * SessionServiceLocator
					 * .getSolicitudTransferenciaSessionService()
					 * .actualizarMovimiento( movimiento,
					 * listaMovimientoDetalle, movimientoDetalleTableModel
					 * .getAllMovimientoDetalleIfEiminados());
					 */
					SpiritAlert.createAlert("Solicitud actualizada con éxito",
							SpiritAlert.INFORMATION);
					// showSaveMode();
					solicitudTranferenciaDetalleTableModel.cleanEliminados();
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public void find() {
		try {
			buildQuery();
			SolicitudTransferenciaCriteria solicitudTransferenciaCriteria = new SolicitudTransferenciaCriteria();
			solicitudTransferenciaCriteria.setQueryBuilded(mapaQuery);

			int tamanoLista = solicitudTransferenciaCriteria
					.getResultListSize();

			if (tamanoLista > 0) {

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(
						Parametros.getMainFrame(),
						solicitudTransferenciaCriteria,
						JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					setSolicitudTransferencia((SolicitudTransferenciaIf) popupFinder
							.getElementoSeleccionado());
				showUpdateMode();
			} else {
				SpiritAlert.createAlert("No se encontraron registros",
						SpiritAlert.WARNING);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error general en la búsqueda de información",
					SpiritAlert.ERROR);
		}

	}

	private void buildQuery() {
		mapaQuery.clear();
		mapaQuery.put("codigo", QueryHelper.getParameter(getTxtCodigo()));
		mapaQuery.put("estado", "P");
	}

	public boolean isEmpty() {
		return false;
	}

	/** **********************************Cleans*************************** */
	private void cleanMovimiento() {
		getTxtCodigo().setText("");
		getCmbFechaDocumento().setSelectedItem(null);
		getTxtObservacion().setText("");
	}

	private void cleanDetalleCabecera() {
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getCmbLote().setSelectedIndex(-1);
	}

	private void cleanAllDetalleMovimiento() {
		cleanDetalleCabecera();
		cleanTablaDetalle();
	}

	private void cleanTablaDetalle() {
		solicitudTranferenciaDetalleTableModel.clean();
		// getTblMovimientoDetalle().repaint();
	}

	public void clean() {
		cleanMovimiento();
		cleanAllDetalleMovimiento();
	}

	public void showFindMode() {
		setFindMode();
		getTxtCodigo().setEditable(true);
		getTxtCodigo().setBackground(Parametros.findColor);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEditable(false);
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);

		getCmbLote().setEnabled(false);
		getCmbLote().repaint();

		getCmbFechaDocumento().setEnabled(false);

		getCmbBodegaTransferencia().setEnabled(true);
		getCmbBodegaTransferencia().repaint();

		getCmbEstado().setEnabled(true);
		getCmbBodega().setEnabled(true);

		habilitarDetalle(true);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEditable(false);
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);

		getCmbBodegaTransferencia().setEnabled(false);

		getCmbEstado().setEnabled(false);
		getCmbBodega().setEnabled(false);

		getCmbFechaDocumento().setEnabled(false);
		habilitarDetalle(false);

	}

	private void habilitarDetalle(boolean habilitar) {
		getBtnBuscarProducto().setEnabled(habilitar);
		getCmbLote().setEnabled(habilitar);
		getTxtCantidad().setEnabled(habilitar);

		getBtnAgregarDetalle().setEnabled(habilitar);
		getBtnActualizarDetalle().setEnabled(habilitar);
		getBtnEliminarDetalle().setEnabled(habilitar);
	}

	public void addDetail() {
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}

	@Override
	public void authorize() {
		if (getMode() != SpiritMode.UPDATE_MODE) {
			SpiritAlert.createAlert(
					"Solo pueden aprobarse solicitudes guardadas..",
					"No puede aprobar la solicitud", SpiritAlert.WARNING);
			return;
		}
		try {

			if (data.getEstado().equalsIgnoreCase("E")) {
				SpiritAlert.createAlert("La solicitud ya fue enviada..",
						"Solicitud aprobada", SpiritAlert.WARNING);
				return;
			}

			SessionServiceLocator.getSolicitudTransferenciaSessionService()
					.autorizarSolicitudTransferencia(data);

			SpiritAlert.createAlert("Solicitud aprobada con exito", "Mensaje",
					SpiritAlert.WARNING);
			showSaveMode();

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al aprobar la solicitud",
					"No puede aprobar la solicitud", SpiritAlert.ERROR);
		}
	}
}
