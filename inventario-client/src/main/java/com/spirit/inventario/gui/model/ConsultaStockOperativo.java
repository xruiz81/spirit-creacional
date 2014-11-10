package com.spirit.inventario.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.l2fprod.common.swing.renderer.DefaultCellRenderer;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.client.model.SwingWorker;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.OficinaCriteria;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.BodegaCriteria;
import com.spirit.inventario.gui.criteria.ColorCriteria;
import com.spirit.inventario.gui.criteria.GenericoCriteria;
import com.spirit.inventario.gui.criteria.ModeloCriteria;
import com.spirit.inventario.gui.criteria.PresentacionCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.NumericDocument;
import com.spirit.inventario.gui.helper.QueryHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPCierreInventario;
import com.spirit.inventario.gui.panel.JPConsultaInventario;
import com.spirit.inventario.gui.panel.JPConsultaStockOperativo;
import com.spirit.inventario.gui.panel.JPKardex;
import com.spirit.inventario.gui.tblmodel.CierreInventarioTableModel;
import com.spirit.inventario.gui.tblmodel.ConsultaStockOperativoTableModel;
import com.spirit.inventario.gui.tblmodel.KardexTableModel;
import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.helper.EnumSemaforo;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.helper.StockOperativoDataModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class ConsultaStockOperativo extends JPConsultaStockOperativo {

	private JDPopupFinderModel popupFinder;
	private BodegaIf bodegaIf;
	private ProductoIf productoIf;
	private GenericoIf generico;
	private ModeloIf modeloIf;
	private PresentacionIf tallaIf;
	private ColorIf colorIf;

	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();

	private ConsultaStockOperativoTableModel cierreInventarioTableModel = new ConsultaStockOperativoTableModel();

	public ConsultaStockOperativo() {
		iniciarComponentes();
		initListeners();
		anchoColumnasTabla();
		setBodegaDefault();
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setShowNoneButton(false);
		//getCmbFechaInicial().setFormat(Utilitarios.setFechaSinDiaUppercase());
		getCmbFechaInicial().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setDate(new Date());

		new HotKeyComponent(this);
	}

	private void setBodegaDefault() {
		new SwingWorker() {
			@Override
			public Object construct() {
				List<BodegaIf> listaBodegas;
				try {
					listaBodegas = (ArrayList) SessionServiceLocator
							.getBodegaSessionService().findBodegaByOficinaId(
									Parametros.getIdOficina());
					bodegaIf = (BodegaIf) listaBodegas.get(0);
					getTxtBodega().setText(bodegaIf.getNombre());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			ConsultaStockOperativoData stockInventarioData = cierreInventarioTableModel
					.getDataList().get(row);

			if (column == 8) {
				EnumSemaforo enumSemaforoStock = stockInventarioData
						.getSemaforoStock();
				if (EnumSemaforo.AMARILLO.equals(enumSemaforoStock)) {
					setBackground(Color.YELLOW);
				} else if (EnumSemaforo.ROJO.equals(enumSemaforoStock)) {
					setBackground(Color.RED);
				} else if (EnumSemaforo.VERDE.equals(enumSemaforoStock)) {
					setBackground(Color.GREEN);
				}
			} else if (column == 9) {
				EnumSemaforo enumSemaforoStock = stockInventarioData
						.getSemaforoRotacion();
				if (EnumSemaforo.AMARILLO.equals(enumSemaforoStock)) {
					setBackground(Color.YELLOW);
				} else if (EnumSemaforo.ROJO.equals(enumSemaforoStock)) {
					setBackground(Color.RED);
				} else if (EnumSemaforo.VERDE.equals(enumSemaforoStock)) {
					setBackground(Color.GREEN);
				}
			} else {
				setBackground(null);
			}

			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
		}
	};

	private void anchoColumnasTabla() {
		getTblStockOperativo().getTableHeader().setReorderingAllowed(false);
		getTblStockOperativo()
				.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumn anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(0));
		// JCheckBox txtCell = new JCheckBox();
		// DefaultRenderer defaultCellEditor = new DefaultCellRenderer();
		// anchoColumna.setCellRenderer(defaultCellEditor);
		// anchoColumna.setMinWidth(5);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(1));
		anchoColumna.setMinWidth(130);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(2));
		anchoColumna.setMinWidth(350);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(3));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(4));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(5));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(6));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(7));
		anchoColumna.setMinWidth(80);

		getTblStockOperativo().setDefaultRenderer(Object.class, cellRenderer);
		getTblStockOperativo().setSurrendersFocusOnKeystroke(true);

	}

	private void buildQueryBodega() {
		mapaQueryBodega.clear();
		boolean criterioSeleccionado = false;
		if (!criterioSeleccionado)
			mapaQueryBodega.put("codigo", "%");
	}

	private void eliminarBodega() {
		bodegaIf = null;
		getTxtBodega().setText(null);
	}

	private void eliminarProducto() {
		productoIf = null;
		getTxtProducto().setText(null);
	}

	private void buscarBodega() {

		try {
			String mmpg = "";
			BodegaCriteria productoCriteria = new BodegaCriteria();
			buildQueryBodega();
			productoCriteria.setQueryBuilded(mapaQueryBodega);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			if (popupFinder.getElementoSeleccionado() != null) {
				bodegaIf = (BodegaIf) popupFinder.getElementoSeleccionado();
				getTxtBodega().setText(bodegaIf.getNombre());
				// fillTable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initListeners() {

		getBtnBuscarBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarBodega();
			}

		});

		getBtnBuscarGenerico().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarGenerico();
			}

		});

		getBtnBuscarModelo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarModelo();
			}

		});
		getBtnBuscarTalla().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarPresentacion();
			}

		});

		getBtnBuscarColor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarColor();
			}

		});

		getBtnRefresh().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fillTable();
			}

		});

		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});

		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProducto();
			}
		});

		getBtnEliminarGenerico().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				generico = null;
				getTxtGenerico().setText(null);
			}
		});

		getBtnEliminarModelo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				modeloIf = null;
				getTxtModelo().setText(null);
			}
		});

		getBtnEliminarTalla().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tallaIf = null;
				getTxtTalla().setText(null);
			}
		});

		getBtnEliminarColor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				colorIf = null;
				getTxtColor().setText(null);
			}
		});

		getBtnGenSolicitudTransferencia().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent evento) {
						List<ConsultaStockOperativoData> generarSolicituList = new ArrayList<ConsultaStockOperativoData>();
						for (ConsultaStockOperativoData consultaStockOperativoData : cierreInventarioTableModel
								.getDataList()) {
							if (!consultaStockOperativoData.isSelected() || consultaStockOperativoData.getCantidadSolicitada()==null || consultaStockOperativoData.getCantidadSolicitada().compareTo(BigDecimal.ZERO)<=0)
								continue;
							// TODO: MENSAJE DE CONFIRMACION
							generarSolicituList.add(consultaStockOperativoData);
						}
						if (generarSolicituList.isEmpty()) {
							SpiritAlert
									.createAlert(
											"Debe elegir al menos un producto para generar una solicitud de transferencia",
											SpiritAlert.ERROR);
							return;
						}
						try {
							UsuarioIf usuario=(UsuarioIf)Parametros.getUsuarioIf();
							com.spirit.comun.util.SessionServiceLocator
									.getSolicitudTransferenciaSessionService()
									.generarSolicitudTransferencia(
											generarSolicituList,usuario.getId());
							SpiritAlert
							.createAlert(
									"Solicitud de Transferencia generada, debe editarla y enviarla",
									SpiritAlert.INFORMATION);
					
						} catch (GenericBusinessException e) {
							e.printStackTrace();
							
						}
					}
				});

	}

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarModelo() {
		try {
			String mmpg = "";
			ModeloCriteria criteria = new ModeloCriteria();
			
			mapaQueryBodega.clear();		
			mapaQueryBodega.put("empresaId", Parametros.getIdEmpresa());
			criteria.setQueryBuilded(mapaQueryBodega);
			
			criteria.getResultListSize();
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),criteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				modeloIf = (ModeloIf) popupFinder.getElementoSeleccionado();
				getTxtModelo().setText(modeloIf.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarGenerico() {
		try {
			String mmpg = "";
			GenericoCriteria criteria = new GenericoCriteria();
			mapaQueryBodega.clear();
			mapaQueryBodega.put("codigo", "%");
			mapaQueryBodega.put("empresaId", Parametros.getIdEmpresa());
			criteria.setQueryBuilded(mapaQueryBodega);
			criteria.getResultListSize();
			
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),criteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				generico = (GenericoIf) popupFinder.getElementoSeleccionado();
				getTxtGenerico().setText(generico.getNombre());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarPresentacion() {
		try {
			String mmpg = "";
			PresentacionCriteria criteria = new PresentacionCriteria();

			criteria.getResultListSize();
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					criteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			if (popupFinder.getElementoSeleccionado() != null) {
				tallaIf = (PresentacionIf) popupFinder
						.getElementoSeleccionado();
				getTxtTalla().setText(tallaIf.getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarColor() {
		try {
			String mmpg = "";
			ColorCriteria criteria = new ColorCriteria();

			criteria.getResultListSize();
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					criteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			if (popupFinder.getElementoSeleccionado() != null) {
				colorIf = (ColorIf) popupFinder.getElementoSeleccionado();
				getTxtColor().setText(colorIf.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validateSeleccion() {
		if (bodegaIf == null && productoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar al menos un criterio",
					SpiritAlert.INFORMATION);
			getBtnBuscarBodega().requestFocus();
			return false;
		} else
			return true;
	}

	public void refresh() {
		fillTable();
	}

	private void setIconoBusqueda(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager
				.getImageIcon("images/icons/funcion/findElement.png"));
	}

	private void setIconoEliminar(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager
				.getImageIcon("images/icons/funcion/deleteElement.png"));
	}

	private void setIconoRefresh(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager
				.getImageIcon("images/icons/funcion/refresh_record_16.png"));
	}

	private void iniciarComponentes() {
		setIconoBusqueda("Buscar Bodega", getBtnBuscarBodega());
		setIconoBusqueda("Buscar Producto", getBtnProducto());
		setIconoBusqueda("Buscar Generico", getBtnBuscarGenerico());
		setIconoBusqueda("Buscar Modelo", getBtnBuscarModelo());
		setIconoBusqueda("Buscar Talla", getBtnBuscarTalla());
		setIconoBusqueda("Buscar Color", getBtnBuscarColor());
		setIconoRefresh("Refrescar", getBtnRefresh());
		setIconoEliminar("Eliminar Generico", getBtnEliminarGenerico());
		setIconoEliminar("Eliminar Modelo", getBtnEliminarModelo());
		setIconoEliminar("Eliminar Talla", getBtnEliminarTalla());
		setIconoEliminar("Eliminar Producto", getBtnEliminarProducto());
		setIconoEliminar("Eliminar Color", getBtnEliminarColor());
		getTblStockOperativo().setModel(cierreInventarioTableModel);
	}

	private List consultaStock() throws GenericBusinessException {
		Long idBodega = null;
		Long idProducto = null;
		Long idModelo = null;
		Long idGenerico = null;
		Long idPresentacion = null;
		Long idColor = null;
		if (bodegaIf != null) {
			idBodega = bodegaIf.getId();
		}
		if (productoIf != null) {
			idProducto = productoIf.getId();
		}
		if (tallaIf != null) {
			idPresentacion = tallaIf.getId();
		}
		if (modeloIf != null) {
			idModelo = modeloIf.getId();
		}
		if (generico != null) {
			idGenerico = generico.getId();
		}
		if (colorIf != null) {
			idColor = colorIf.getId();
		}
		return com.spirit.comun.util.SessionServiceLocator
				.getStockOperativoSessionService().consultaStockOperativo(null,
						idBodega, idGenerico, idModelo, idPresentacion,
						idColor, idProducto, getCmbFechaInicial().getDate(),
						new Date());

	}

	private void fillTable() {
		try {
			if (!validateSeleccion())
				return;
			List listaKardex = consultaStock();

			if (listaKardex != null && listaKardex.size() > 0) {
				cierreInventarioTableModel.refresh(listaKardex);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cierreInventarioTableModel
					.refresh(new ArrayList<ConsultaStockOperativoData>());
			SpiritAlert.createAlert(e.getMessage(), "", SpiritAlert.WARNING);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cierreInventarioTableModel
					.refresh(new ArrayList<ConsultaStockOperativoData>());
			SpiritAlert.createAlert("Error general", "", SpiritAlert.WARNING);
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
	}

	@Override
	public void report() {
		try {
			if (!validateSeleccion())
				return;

			List listaKardex = cierreInventarioTableModel.getDataList();
			if (listaKardex.size() > 0) {
				if (SpiritAlert.confirmDialog(this,
						"¿Volver a realizar consulta?")) {
					listaKardex = consultaStock();
				}
			} else {
				listaKardex = consultaStock();
			}

			if (listaKardex != null && listaKardex.size() > 0) {
				HashMap<String, Object> parametrosMap = new HashMap<String, Object>();

				parametrosMap.put("codigoReporte", "Stock Actual");
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("fechaHoy", new Date());
				parametrosMap.put("fechaInicio", new Date());
				parametrosMap.put("usuario", Parametros.getUsuario());

				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(
						listaKardex);
				ReportModelImpl.processReport(
						"jaspers/inventario/RPStockOperativo.jasper",
						parametrosMap, dataSourceDetail, true);
			} else {
				SpiritAlert.createAlert("No existen datos para reporte",
						SpiritAlert.INFORMATION);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SpiritAlert.createAlert("Error al generar reporte",
					SpiritAlert.ERROR);
		}

	}

	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

}
