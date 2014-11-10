package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.client.model.SwingWorker;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.util.DateHelperClient;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoData;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.gui.criteria.MovimientoCriteria;
import com.spirit.inventario.gui.criteria.OrdenCompraInventarioCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.criteria.SolicitudTransferenciaCriteria;
import com.spirit.inventario.gui.helper.CatalogoLocator;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.QueryHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.helper.ValueHelper;
import com.spirit.inventario.gui.panel.JPMovimiento;
import com.spirit.inventario.gui.tblmodel.MovimientoDetalleRowModel;
import com.spirit.inventario.gui.tblmodel.MovimientoDetalleTableModel;
import com.spirit.inventario.gui.tblmodel.MovimientoTableModel;
import com.spirit.inventario.helper.GuiaRemision;
import com.spirit.inventario.helper.StockException;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class MovimientoModel extends JPMovimiento {

	private static final long serialVersionUID = -8986810298253264907L;

	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_COSTO_DETALLE = 8;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private static final int MAX_LONGITUD_REFERENCIA = 20;

	private static final int MAX_CANTIDAD_DETALLE = 6;
	private static final int MAX_COSTO_DETALLE = 8;
	private static final int MAX_PRECIO_DETALLE = 8;

	private OrdenCompraIf ordenCompraIf;
	private SolicitudTransferenciaIf solicitudTransferenciaIf;

	private MovimientoIf data;
	private ProductoIf productoIf;

	private HashMap<String, Object> mapaQuery = new HashMap<String, Object>();

	private JDPopupFinderModel popupFinder;

	private MovimientoDetalleTableModel movimientoDetalleTableModel = null;

	private MovimientoTableModel movimientoTableModel = null;

	public MovimientoModel() {	
		iniciarComponentes();
		initKeyListeners();
		initListeners();
		this.cargarCombos();
		this.showSaveMode();
		new HotKeyComponent(this);
		anchoColumnasTabla();
	}

	private void anchoColumnasTabla() {
		getTblMovimientoDetalle().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblMovimientoDetalle().getColumn(getTblMovimientoDetalle().getColumnName(0));
		getTblMovimientoDetalle().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		anchoColumna.setMinWidth(150);
		anchoColumna = getTblMovimientoDetalle().getColumn(getTblMovimientoDetalle().getColumnName(1));
		anchoColumna.setMinWidth(150);
		anchoColumna = getTblMovimientoDetalle().getColumn(getTblMovimientoDetalle().getColumnName(2));
		anchoColumna.setMinWidth(450);
		anchoColumna = getTblMovimientoDetalle().getColumn(getTblMovimientoDetalle().getColumnName(3));
		anchoColumna.setMinWidth(200);
		anchoColumna = getTblMovimientoDetalle().getColumn(getTblMovimientoDetalle().getColumnName(4));
		anchoColumna.setMinWidth(200);
		

		getTblPorAprobar().getTableHeader().setReorderingAllowed(false);
		getTblPorAprobar().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(0));
		anchoColumna.setMinWidth(100);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(1));
		anchoColumna.setMinWidth(170);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(2));
		anchoColumna.setMinWidth(120);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(3));
		anchoColumna.setMinWidth(170);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(4));
		anchoColumna.setMinWidth(170);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(5));
		anchoColumna.setMinWidth(280);
		anchoColumna = getTblPorAprobar().getColumn(getTblPorAprobar().getColumnName(6));
		anchoColumna.setMinWidth(50);


		
	}

	private void iniciarComponentes() {
		getTxtOrdenCompra().setEditable(false);
		getTxtSolicitudTransferencia().setEditable(false);
		getTxtProducto().setEditable(false);

		getCmbFechaDocumento().setLocale(Utilitarios.esLocale);
		getCmbFechaDocumento().setShowNoneButton(false);
		getCmbFechaDocumento().setEditable(false);

		getCmbFechaDocumento().setFormat(Utilitarios.setFechaUppercase());

		/** *************************TABLA DETALLE**************************** */

		movimientoDetalleTableModel = new MovimientoDetalleTableModel();
		getTblMovimientoDetalle().setModel(movimientoDetalleTableModel);

		movimientoTableModel = new MovimientoTableModel();
		getTblPorAprobar().setModel(movimientoTableModel);
		new SwingWorker()
		{
			@Override
			public Object construct() {
				movimientoTableModel.refresh(SessionServiceLocator
						.getMovimientoSessionService().getMovimientosPorAprobar());
				return null;
			}
		};
		getTblMovimientoDetalle().getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int index = getTblMovimientoDetalle().getSelectedRow();
						if (!e.getValueIsAdjusting() && index >= 0) {
							setMovimientoDetalle(movimientoDetalleTableModel
									.getRow(index));
						}

					}
				});

		getTblPorAprobar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = getTblPorAprobar().getSelectedRow();
					MovimientoIf movimientoIf = null;
					try {
						movimientoIf = SessionServiceLocator
								.getMovimientoSessionService().getMovimiento(
										movimientoTableModel.getRow(index)
												.getId());
						setMovimiento(movimientoIf);
						abrirPanelCabecera();
						showUpdateMode();
					} catch (GenericBusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		/** *************************LISTENERS**************************** */

		getBtnBuscarOrdenCompra().setToolTipText("Buscar Orden de Compra");
		getBtnBuscarOrdenCompra().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEliminarOrdenCompra().setToolTipText("Eliminar Orden de Compra");
		getBtnEliminarOrdenCompra()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));

		getBtnBuscarSolicitudTransferencia().setToolTipText(
				"Buscar Solicitud Transferencia");
		getBtnBuscarSolicitudTransferencia().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));

		getBtnEliminarSolicitudTransferencia().setToolTipText(
				"Eliminar Solicitud Transferencia");
		getBtnEliminarSolicitudTransferencia()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));

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
		getTxtObservacion().addKeyListener(new TextChecker(255));
		getTxtReferencia().addKeyListener(
				new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtReferencia().addKeyListener(
				new TextChecker(MAX_LONGITUD_REFERENCIA));
	}

	private void initListeners() {
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarDocumento();
			}
		});

		getBtnBuscarSolicitudTransferencia().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent evento) {
						buscarSolicitudTransferencia();
					}
				});

		getBtnEliminarSolicitudTransferencia().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent evento) {
						// TODO:ELIMINAR
						eliminarSolicitudTransferencia();
					}
				});

		getBtnBuscarOrdenCompra().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOrdenCompra();
			}
		});

		getBtnEliminarOrdenCompra().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarOrdenCompra();
			}
		});

		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});

		/** **************DETALLE********************* */
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				completarMovimientoDetalleIf(true);
				validarStock();
			}
		});

		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				completarMovimientoDetalleIf(false);
			}
		});

		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int index = getTblMovimientoDetalle().getSelectedRow();
				if (index >= 0) {
					movimientoDetalleTableModel.remove(index);
				}
			}
		});
	}

	private void validarStock() {

	}

	/** ************************ COMBOS ************************************ */

	private void cargarCombos() {
		ValueHelper.fillCombo(getCmbEstado(), CatalogoLocator.CAT_ESTADO
				.getArray());

		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFechaDocumento().setSelectedItem(null);
		}
		cargarComboTipoDocumento();
		cargarComboBodega();
	}

	@SuppressWarnings("unchecked")
	private void cargarComboTipoDocumento() {
		Long idModulo;
		try {
			idModulo = ((ModuloIf) SessionServiceLocator
					.getModuloSessionService().findModuloByNombre("INVENTARIO")
					.iterator().next()).getId();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("moduloId", idModulo);
			parameterMap.put("empresaId", Long.valueOf(Parametros
					.getIdEmpresa()));
			parameterMap.put("estado", "A");
			List tiposDocumentos = (List) SessionServiceLocator
					.getTipoDocumentoSessionService().findTipoDocumentoByQuery(
							parameterMap);
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBodega() {
		try {
			getCmbBodega().addItem("Cargando Items..");
			List bodegas = null;
			if (getMode() == SpiritMode.SAVE_MODE)
				bodegas = (List) SessionServiceLocator.getBodegaSessionService().findBodegaByOficinaId(Parametros.getIdOficina());
			else
				bodegas = (List) SessionServiceLocator.getBodegaSessionService().findBodegaByEmpresaId(Parametros.getIdEmpresa());
			getCmbBodega().removeAllItems();
			refreshCombo(getCmbBodega(), bodegas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBodegaTransferencia() {
		try {
			getCmbBodegaTransferencia().setEnabled(true);
			List bodegasTransferencia = (List) SessionServiceLocator
					.getBodegaSessionService().getBodegaList();
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

	private void buscarDocumento() {
		if (getCmbTipoDocumento().getSelectedItem() != null) {
			TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento()
					.getSelectedItem();

			if (tipoDocumentoIf != null) {
				try {
					getCmbDocumento().removeAllItems();
					SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel(
							(List) SessionServiceLocator
									.getDocumentoSessionService()
									.findDocumentoByTipodocumentoIdAndUsuarioId(
											tipoDocumentoIf.getId(),
											((UsuarioIf) Parametros
													.getUsuarioIf()).getId()));
					getCmbDocumento().setModel(cmbModelDocumento);
					if (getCmbDocumento().getItemCount() > 0) {
						if (getMode() != SpiritMode.FIND_MODE) {
							getCmbDocumento().setEnabled(true);
							if (getCmbDocumento().getItemCount() > 0)
								getCmbDocumento().setSelectedIndex(0);
						}
					}
					// Si el Tipo de Documento es Transfencia de Mercaderia, se
					// carga el combo Bodega Transferencia
					if (tipoDocumentoIf.getCodigo().equals("ETR")
							|| tipoDocumentoIf.getCodigo().equals("ITR")) {
						cargarComboBodegaTransferencia();
					} else {
						getCmbBodegaTransferencia().setSelectedIndex(-1);
						getCmbBodegaTransferencia().setEnabled(false);
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		}
	}

	private void buscarOrdenCompra() {
		OrdenCompraInventarioCriteria ordenCompraCriteria = new OrdenCompraInventarioCriteria();
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				ordenCompraCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			setOrdenCompra((OrdenCompraIf) popupFinder
					.getElementoSeleccionado());
		}
	}

	private static HashMap<String, Object> querySolicitudTranferencia = new HashMap<String, Object>();

	static {
		querySolicitudTranferencia.put("codigo", "%");
	}

	private void buscarSolicitudTransferencia() {
		SolicitudTransferenciaCriteria solicitudTransferenciaCriteria = new SolicitudTransferenciaCriteria();
		solicitudTransferenciaCriteria
				.setQueryBuilded(querySolicitudTranferencia);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				solicitudTransferenciaCriteria,
				JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			setSolicitudTransferencia((SolicitudTransferenciaIf) popupFinder
					.getElementoSeleccionado());
		}
	}

	private void setSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf) {
		this.solicitudTransferenciaIf = solicitudTransferenciaIf;
		// TODO: PRESENTAR MEJOR
		String refSolicitudTransferencia = solicitudTransferenciaIf.getCodigo();

		getTxtSolicitudTransferencia().setText(refSolicitudTransferencia);
		ValueHelper.setValue(getCmbTipoDocumento(), ConsultasHelper
				.getTipoDocumento("ETR").getId());
		ValueHelper.setValue(getCmbBodega(), solicitudTransferenciaIf
				.getBodegaDesdeId());
		cargarComboBodegaTransferencia();
		ValueHelper.setValue(getCmbBodegaTransferencia(),
				solicitudTransferenciaIf.getBodegaHaciaId());
		ValueHelper.setValue(getTxtObservacion(),
				"AUTOGENERADO A PARTIR DE SOLICITUD DE TRANSFERENCIA: "
						+ refSolicitudTransferencia);

		// DETALLE
		movimientoDetalleTableModel.clean();
		try {
			List<SolicitudTransferenciaDetalleIf> listaDetalle = (List<SolicitudTransferenciaDetalleIf>) SessionServiceLocator
					.getSolicitudTransferenciaDetalleSessionService()
					.findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(
							solicitudTransferenciaIf.getId());
			for (SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf : listaDetalle) {
				movimientoDetalleTableModel
						.addSolicitudTransferenciaDetalle(solicitudTransferenciaDetalleIf);
			}

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setOrdenCompra(OrdenCompraIf ordenCompraIf) {
		try {
			this.ordenCompraIf = ordenCompraIf;
			getTxtOrdenCompra().setText(ordenCompraIf.getCodigo());
			movimientoDetalleTableModel.clean();

			List<OrdenCompraDetalleIf> listaDetalleOrdenCompra = (List<OrdenCompraDetalleIf>) SessionServiceLocator
					.getOrdenCompraDetalleSessionService()
					.findOrdenCompraDetalleByOrdencompraId(
							ordenCompraIf.getId());

			for (OrdenCompraDetalleIf ordenCompraDetalleIf : listaDetalleOrdenCompra) {
				movimientoDetalleTableModel
						.addOrdenCompraDetalle(ordenCompraDetalleIf);
			}
			// TODO: SELECCIONAR EL PRIMER ELEMENTO Y MOSTRAR EL PRODUCTO
			// getTblMovimientoDetalle().getSelectionModel().setSelectionInterval(0,0);

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setDetalleMovimiento(
			List<MovimientoDetalleIf> listaMovimientoDetalleIf) {
		movimientoDetalleTableModel.clean();
		getTblMovimientoDetalle().repaint();
		for (MovimientoDetalleIf movimientoDetalleIf : listaMovimientoDetalleIf) {
			movimientoDetalleTableModel
					.addMovimientoDetalle(movimientoDetalleIf);
		}
	}

	/** ************************************************************************* */
	private void eliminarOrdenCompra() {
		ordenCompraIf = null;
		getTxtOrdenCompra().setText("");
		cleanAllDetalleMovimiento();
	}

	private void eliminarSolicitudTransferencia() {
		solicitudTransferenciaIf = null;
		getTxtSolicitudTransferencia().setText("");
		cleanAllDetalleMovimiento();
	}

	private void setMovimiento(MovimientoIf movimientoIf) {
		data = movimientoIf;
		ValueHelper.setValue(getTxtCodigo(), movimientoIf.getCodigo());
		ValueHelper.setValue(getCmbEstado(), CatalogoLocator.CAT_ESTADO
				.getCatalogoByCodigo(movimientoIf.getEstado()));
		ValueHelper.setValue(getCmbFechaDocumento(), movimientoIf
				.getFechaDocumento());
		ValueHelper
				.setValue(getTxtObservacion(), movimientoIf.getObservacion());
		//ValueHelper.setValue(getTxtReferencia(), movimientoIf.getReferencia());
		ValueHelper.setValue(getCmbTipoDocumento(), movimientoIf
				.getTipodocumentoId());
		ValueHelper.setValue(getCmbBodega(), movimientoIf.getBodegaId());
		if (movimientoIf.getBodegarefId() != null) {
			ValueHelper.setValue(getCmbBodegaTransferencia(), movimientoIf
					.getBodegarefId());
		}
		try {

			/*
			 * setOrdenCompra(SessionServiceLocator.getOrdenCompraSessionService()
			 * .getOrdenCompra(movimientoIf.getOrdencompraId()));
			 */
			if (movimientoIf.getOrdencompraId() != null) {
				getTxtOrdenCompra()
						.setText(
								SessionServiceLocator
										.getOrdenCompraSessionService()
										.getOrdenCompra(
												movimientoIf.getOrdencompraId())
										.toString());
			}
			setDetalleMovimiento((List) SessionServiceLocator
					.getMovimientoDetalleSessionService()
					.findMovimientoDetalleByMovimientoId(movimientoIf.getId()));
		} catch (GenericBusinessException e) {
			// TODO: ALERT
			e.printStackTrace();
		}
	}

	private void setMovimientoDetalle(MovimientoDetalleRowModel row) {
		if (row == null)
			return;
		// ValueHelper.getValue(getCmbDocumento(),
		// row.getDocumentoMovimiento());
		// ((SpiritComboBoxModel)getCmbDocumento().getModel())
		getCmbDocumento().setSelectedIndex(
				ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), row
						.getDocumentoMovimiento().getId()));
		getCmbDocumento().repaint();

		ValueHelper.getValue(getTxtCantidad(), row.getCantidad());
		if (row.getProducto() != null) {
			productoIf = row.getProducto();
			getTxtProducto().setText(
					ConsultasHelper.getTxtProducto(row.getProducto()));
			cargarComboLote(row.getProducto().getId());
		}
	}

	private void completarMovimientoDetalleIf(boolean nuevo) {
		if (validarIngresoMovimientoDetalle()) {
			MovimientoDetalleRowModel movimientoDetalleRowModel = null;

			if (nuevo) {
				movimientoDetalleRowModel = new MovimientoDetalleRowModel();
			} else {
				movimientoDetalleRowModel = movimientoDetalleTableModel.getRow(getTblMovimientoDetalle().getSelectedRow());
			}
			TipoDocumentoIf tipoDocumento = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem());
			if (tipoDocumento.getSignostock().equalsIgnoreCase("-")) {
				BodegaIf bodega = (BodegaIf) getCmbBodega().getSelectedItem();
				try {
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					if (generico.getUsaLote().equals("S"))
						SessionServiceLocator.getStockSessionService().chequearStock(bodega.getId(), productoIf.getId(), ValueHelper.convertValueToBigDecimal(getTxtCantidad()));
				} catch (StockException e) {
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			movimientoDetalleRowModel.setDocumentoMovimiento((DocumentoIf) getCmbDocumento().getSelectedItem());
			movimientoDetalleRowModel.setLoteIf((LoteIf) getCmbLote().getSelectedItem());
			movimientoDetalleRowModel.setCantidad(ValueHelper.convertValueToBigDecimal(getTxtCantidad()));
			movimientoDetalleRowModel.setProducto(productoIf);

			if (nuevo) {
				movimientoDetalleTableModel.addRow(movimientoDetalleRowModel);
			}
			getTblMovimientoDetalle().repaint();

			// cleanMovimientoDetalle();
		}
	}

	private boolean validarIngresoMovimientoDetalle() {
		if (getCmbDocumento().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Documento!",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			getCmbDocumento().grabFocus();
			return false;
		}
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
				MovimientoIf movimiento = registrarMovimiento(true);
				List<MovimientoDetalleIf> listaMovimientoDetalle = movimientoDetalleTableModel
						.getAllMovimientoDetalleIf();
				if (validateDetalleCompleto(listaMovimientoDetalle)) {
					SessionServiceLocator.getMovimientoSessionService()
							.procesarMovimiento(movimiento,
									listaMovimientoDetalle,
									(UsuarioIf) Parametros.getUsuarioIf());
					SpiritAlert.createAlert("Movimiento guardado con éxito",
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
		// if (!ValidateHelper.validateTextField(getTxtCodigo())) {
		// SpiritAlert.createAlert(
		// "Debe ingresar un código para el movimiento",
		// SpiritAlert.WARNING);
		// abrirPanelCabecera();
		//
		// return false;
		// }

		TipoDocumentoIf tipoDocumento = ((TipoDocumentoIf) getCmbTipoDocumento()
				.getSelectedItem());

		if (tipoDocumento.getCodigo().equalsIgnoreCase("ITR")) {
			SpiritAlert.createAlert("No pueden ingresarse tipo de movimiento: "
					+ tipoDocumento.getNombre(), SpiritAlert.WARNING);
			abrirPanelCabecera();

			return false;
		}

		String estado = CatalogoLocator.CAT_ESTADO
				.getCodigoSeleccionado(getCmbEstado());
		if (estado.equalsIgnoreCase("P")) {
			SpiritAlert.createAlert(
					"No pueden ingresarse movimoentos con estado Pendiente",
					SpiritAlert.WARNING);
			abrirPanelCabecera();
			return false;
		}

		if (tipoDocumento.getCodigo().equalsIgnoreCase("ITR")) {
			SpiritAlert.createAlert("No pueden ingresarse tipo de movimiento: "
					+ tipoDocumento.getNombre(), SpiritAlert.WARNING);
			abrirPanelCabecera();

			return false;
		}

		BodegaIf bodega = (BodegaIf) getCmbBodega().getSelectedItem();
		if (bodega.getOficinaId().compareTo(Parametros.getIdOficina()) != 0) {
			SpiritAlert.createAlert(
					"La bodega de origen no puede pertenecer a otra oficina",
					SpiritAlert.WARNING);
			abrirPanelCabecera();

			return false;
		}

		if (movimientoDetalleTableModel.getAllMovimientoDetalleIf().size() <= 0) {
			SpiritAlert.createAlert(
					"Debe ingresar el detalle para el movimiento",
					SpiritAlert.WARNING);
			abrirPanelDetalle();
			getCmbDocumento().grabFocus();
			return false;
		}

		return true;
	}

	private boolean validateDetalleCompleto(
			List<MovimientoDetalleIf> listaMovimientoDetalleIf) {
		for (int i = 0; i < listaMovimientoDetalleIf.size(); i++) {
			if (!validateDetalle(listaMovimientoDetalleIf.get(i))) {
				getTblMovimientoDetalle().getSelectionModel()
						.setLeadSelectionIndex(i);
				return false;
			}
		}
		return true;
	}

	private boolean validateDetalle(MovimientoDetalleIf movimientoDetalleIf) {
		if (movimientoDetalleIf.getLoteId() == null) {
			SpiritAlert.createAlert("Debe ingresar el lote para el movimiento",
					SpiritAlert.WARNING);
			return false;
		}
		if (movimientoDetalleIf.getDocumentoId() == null) {
			SpiritAlert.createAlert(
					"Debe ingresar el documento para el movimiento",
					SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	private MovimientoIf registrarMovimiento(boolean nuevo) throws GenericBusinessException {

		if (nuevo)
			data = new MovimientoData();
		else {
			java.sql.Timestamp fechaDocumento = new java.sql.Timestamp(getCmbFechaDocumento().getDate().getTime());
			data.setFechaDocumento(fechaDocumento);
		}
		TipoDocumentoIf tipoDocumento = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem());
		BodegaIf bodega = (BodegaIf) getCmbBodega().getSelectedItem();

		data.setFechaDocumento((getCmbFechaDocumento().getSelectedItem() != null)?DateHelperClient.getTimeStamp(getCmbFechaDocumento().getDate()):data.getFechaDocumento());
		data.setTipodocumentoId(tipoDocumento.getId());
		data.setBodegaId(bodega.getId());

		BodegaIf bodegaRef = (BodegaIf) getCmbBodegaTransferencia().getSelectedItem();
		if (bodegaRef != null)
			data.setBodegarefId(bodegaRef.getId());
		if (ordenCompraIf != null)
			data.setOrdencompraId(ordenCompraIf.getId());
		//data.setReferencia(getTxtReferencia().getText());
		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		// ?? data.setUsuarioautId(usuarioAutoriza.getId());
		data.setObservacion(getTxtObservacion().getText());
		data.setEstado(CatalogoLocator.CAT_ESTADO.getCodigoSeleccionado(getCmbEstado()));
		return data;
	}

	public void delete() {
		try {
			if (!data.getEstado().equalsIgnoreCase("I")) {
				SpiritAlert
						.createAlert(
								"No se puede eliminar un movimiento ya procesado, realice un ajuste de inventario",
								SpiritAlert.ERROR);
			}
			SessionServiceLocator.getMovimientoSessionService()
					.eliminarMovimiento(data);
			SpiritAlert.createAlert("Movimiento eliminado con exito..",
					SpiritAlert.INFORMATION);
			setSaveMode();
			clean();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al eliminar el movimiento..",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void completarGuiaRemision(HashMap<String, Object> mapa)
			throws GenericBusinessException {
		GuiaRemision guia = SessionServiceLocator
				.getMovimientoSessionService()
				.generarGuiaRemision(
						data,
						movimientoDetalleTableModel.getAllMovimientoDetalleIf(),
						null);

		guia.setFechaInicioTraslado((Date) mapa.get("fechaInicio"));
		guia.setFechaFinTraslado((Date) mapa.get("fechaFin"));
		guia.setTransportistaRucCi((String) mapa.get("transportistaRucCI"));
		guia.setTransportistaPlacaVehiculo((String) mapa
				.get("transportistaPlacaVehiculo"));
		guia.setTransportistaRazonSocial((String) mapa
				.get("transportistaRazonSocial"));
		guia.setTransportistaTelefono((String) mapa
				.get("transportistaTelefono"));
		guia.setComprobanteVenta((String) mapa.get("comprobanteVenta"));

		List<GuiaRemision> listaGuias = new ArrayList<GuiaRemision>();

		JRBeanCollectionDataSource dataSourceDetail = new JRBeanCollectionDataSource(
				guia.getGuiaRemisiondetalle());

		guia.setBeanCollection(dataSourceDetail);
		listaGuias.add(guia);

		JRDataSource dataSource = new JRBeanCollectionDataSource(listaGuias);

		HashMap<String, Object> mapareport = new HashMap<String, Object>();
		mapareport.put("SUBREPORT_DIR", Parametros.getRutaCarpetaReportes()
				+ "/jaspers/inventario/RPGuiaRemisionDetalle.jasper");
		ReportModelImpl.processReport(
				"jaspers/inventario/RPGuiaRemision.jasper", mapareport,
				dataSource, true);
	}

	public void report() {
		try {
			if (data == null) {
				SpiritAlert.createAlert(
						"Debe eligir un movimiento de Egreso de Transferencia",
						"Error", SpiritAlert.ERROR);
				abrirPanelCabecera();
				return;
			}
			if (!SessionServiceLocator.getTipoDocumentoSessionService()
					.getTipoDocumento(data.getTipodocumentoId()).getCodigo()
					.equalsIgnoreCase("ETR")) {
				SpiritAlert
						.createAlert(
								"Las guias de remision solo pueden generarse por movimientos de Egreso de Transferencia",
								"Error", SpiritAlert.ERROR);
				abrirPanelCabecera();
				return;
			}

			/*
			 * ParametrosGuiaRemisionDialog parametrosGuiaRemisionDialog = new
			 * ParametrosGuiaRemisionDialog( Parametros.getMainFrame());
			 * parametrosGuiaRemisionDialog .setOkCancelListener(new
			 * OkCancelListener() { public void cancel() { // TODO
			 * Auto-generated method stub
			 *  }
			 * 
			 * public void ok(Object objetoDevuelto) { try {
			 * completarGuiaRemision((HashMap<String, Object>)objetoDevuelto); }
			 * catch (GenericBusinessException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } }; });
			 * parametrosGuiaRemisionDialog.setVisible(true);
			 */
			completarGuiaRemision(new HashMap<String, Object>());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refresh() {
		cargarCombos();
		movimientoTableModel.refresh(SessionServiceLocator
				.getMovimientoSessionService().getMovimientosPorAprobar());
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
		showSaveMode();
		/*if (validateFields()) {
			try {
				MovimientoIf movimiento = registrarMovimiento(false);
				List<MovimientoDetalleIf> listaMovimientoDetalle = movimientoDetalleTableModel
						.getAllMovimientoDetalleIf();
				if (validateDetalleCompleto(listaMovimientoDetalle)) {
					SessionServiceLocator
							.getMovimientoSessionService()
							.actualizarMovimiento(
									movimiento,
									listaMovimientoDetalle,
									movimientoDetalleTableModel
											.getAllMovimientoDetalleIfEiminados());
					SpiritAlert.createAlert("Movimiento actualizado con éxito",
							SpiritAlert.INFORMATION);
					movimientoDetalleTableModel.cleanEliminados();
					showSaveMode();
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}*/
	}

	public void find() {
		try {
			buildQuery();
			MovimientoCriteria movimientoCriteria = new MovimientoCriteria();
			// compraCriteria.setResultListSize(tamanoLista);
			movimientoCriteria.setQueryBuilded(mapaQuery);
			/*
			 * List<MovimientoIf> listaMovimiento = (List<MovimientoIf>)
			 * SessionServiceLocator
			 * .getMovimientoSessionService().findMovimientoByQuery( mapaQuery);
			 */
			int tamanoLista = movimientoCriteria.getResultListSize();

			/*
			 * if (tamanoLista == 1) { setMovimiento(listaMovimiento.get(0));
			 * showUpdateMode(); } else
			 */
			if (tamanoLista > 0) {

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(
						Parametros.getMainFrame(), movimientoCriteria,
						JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					setMovimiento((MovimientoIf) popupFinder
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
		BodegaIf bodega=(BodegaIf)getCmbBodega().getSelectedItem();
		if(bodega!=null)
			mapaQuery.put("bodegaId", bodega.getId());
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
		if (tipoDocumento!=null)
			mapaQuery.put("tipodocumentoId", tipoDocumento.getId());
	}

	public boolean isEmpty() {
		return false;
	}

	/** **********************************Cleans*************************** */
	private void cleanMovimiento() {
		getTxtCodigo().setText("");
		getCmbFechaDocumento().setSelectedItem(null);
		getTxtObservacion().setText("");
		getTxtReferencia().setText("");
		getTxtOrdenCompra().setText("");
		getTxtSolicitudTransferencia().setText("");
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
		movimientoDetalleTableModel.clean();
		// getTblMovimientoDetalle().repaint();
	}

	public void clean() {
		ordenCompraIf = null;
		cleanMovimiento();
		cleanAllDetalleMovimiento();
	}

	public void showFindMode() {
		setFindMode();
		cargarComboBodega();
		getTxtCodigo().setEnabled(true);
		getTxtCodigo().setText("");
		getTxtCodigo().setBackground(Parametros.findColor);
		getCmbBodega().setEnabled(true);
		getCmbBodega().setSelectedItem(null);
		getCmbBodega().setBackground(Parametros.findColor);
		getCmbTipoDocumento().setEnabled(true);
		getCmbTipoDocumento().setSelectedItem(null);
		getCmbTipoDocumento().setBackground(Parametros.findColor);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEnabled(false);
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getCmbBodega().setEnabled(true);
		getCmbBodega().setBackground(Parametros.saveUpdateColor);
		getCmbTipoDocumento().setEnabled(true);
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);

		getCmbLote().setEnabled(false);
		getCmbLote().repaint();

		getCmbFechaDocumento().setEnabled(false);

		getCmbTipoDocumento().setEnabled(true);
		getCmbTipoDocumento().setSelectedIndex(0);
		getCmbTipoDocumento().repaint();

		getCmbBodegaTransferencia().setEnabled(false);
		getCmbBodegaTransferencia().repaint();

		getBtnEliminarOrdenCompra().setEnabled(true);
		getBtnBuscarOrdenCompra().setEnabled(true);

		getBtnEliminarSolicitudTransferencia().setEnabled(true);
		getBtnBuscarSolicitudTransferencia().setEnabled(true);

		getCmbEstado().setEnabled(true);

		getCmbBodega().setEnabled(true);
		getTxtReferencia().setEnabled(true);
		
		cargarComboBodega();

		habilitarDetalle(true);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getCmbBodega().setBackground(Parametros.saveUpdateColor);
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		
		getBtnEliminarOrdenCompra().setEnabled(false);

		getCmbTipoDocumento().setEnabled(false);

		getCmbBodegaTransferencia().setEnabled(false);

		getBtnEliminarOrdenCompra().setEnabled(false);
		getBtnBuscarOrdenCompra().setEnabled(false);

		getBtnEliminarSolicitudTransferencia().setEnabled(false);
		getBtnBuscarSolicitudTransferencia().setEnabled(false);

		getCmbEstado().setEnabled(false);
		getCmbBodega().setEnabled(false);
		getTxtReferencia().setEnabled(false);

		getCmbFechaDocumento().setEnabled(true);
		habilitarDetalle(false);
	}

	private void habilitarDetalle(boolean habilitar) {
		getCmbDocumento().setEnabled(habilitar);
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
					"Solo pueden aprobarse movimientos guardados..",
					"No puede aprobar el movimiento", SpiritAlert.WARNING);
			return;
		}
		try {
			if (data.getEstado().equalsIgnoreCase("A")) {
				SpiritAlert.createAlert("El movimiento ya está aprobado.",
						"Movimiento aprobado", SpiritAlert.WARNING);
				return;
			}
			SessionServiceLocator.getMovimientoSessionService()
					.aprobarMovimiento(
							data,
							movimientoDetalleTableModel
									.getAllMovimientoDetalleIf(),
							(UsuarioIf) Parametros.getUsuarioIf());

			SpiritAlert.createAlert("El movimiento aprobado con exito",
					"Mensaje", SpiritAlert.WARNING);
			movimientoTableModel.refresh(SessionServiceLocator
					.getMovimientoSessionService().getMovimientosPorAprobar());
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al aprobar el movimiento",
					"No puede aprobar el movimiento", SpiritAlert.ERROR);
		}
	}
}
