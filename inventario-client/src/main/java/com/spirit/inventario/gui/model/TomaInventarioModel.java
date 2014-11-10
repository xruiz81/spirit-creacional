package com.spirit.inventario.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.client.model.SwingWorker;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
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
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPTomaInventario;
import com.spirit.inventario.gui.tblmodel.TomaInventarioTableModel;
import com.spirit.inventario.helper.StockInventarioData;

public class TomaInventarioModel extends JPTomaInventario {

	private JDPopupFinderModel popupFinder;
	private BodegaIf bodegaIf;
	private ProductoIf productoIf;
	private GenericoIf generico;
	private ModeloIf modeloIf;
	private PresentacionIf tallaIf;
	private ColorIf colorIf;
	private boolean cambios;

	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();

	private TomaInventarioTableModel tomaInventarioTableModel = new TomaInventarioTableModel();

	public TomaInventarioModel() {
		iniciarComponentes();
		initListeners();
		anchoColumnasTabla();
		setBodegaDefault();
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

	private void anchoColumnasTabla() {
		getTblStock().getTableHeader().setReorderingAllowed(false);

		getTblStock().setCellSelectionEnabled(true);
		getTblStock().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumn anchoColumna = getTblStock().getColumn(
				getTblStock().getColumnName(0));
		anchoColumna.setMinWidth(130);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(1));
		anchoColumna.setMinWidth(350);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(2));
		anchoColumna.setMinWidth(80);

		TableColumn txtColumna = getTblStock().getColumn(
				getTblStock().getColumnName(3));

		JTextField txtCell = new JTextField();
		txtCell.setDocument(new NumericDocument(12, 2));
		DefaultCellEditor defaultCellEditor = new DefaultCellEditor(txtCell);
		txtColumna.setCellEditor(defaultCellEditor);
		((DefaultCellEditor) getTblStock().getCellEditor(0, 3))
				.setClickCountToStart(1);
		getTblStock().setDefaultRenderer(Object.class, cellRenderer);
		getTblStock().setSurrendersFocusOnKeystroke(true);

	}

	private void buildQueryBodega() {
		mapaQueryBodega.clear();
		boolean criterioSeleccionado = false;
		if (!criterioSeleccionado)
			mapaQueryBodega.put("codigo", "%");
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
				BodegaIf bodegaIfTMP = (BodegaIf) popupFinder.getElementoSeleccionado();
				if (bodegaIfTMP.getOficinaId().compareTo(Parametros.getIdOficina()) != 0) {
					SpiritAlert.createAlert(
							"La bodega no puede pertenecer a otra oficina",
							SpiritAlert.WARNING);
					return;
				}
				bodegaIf = bodegaIfTMP;
				getTxtBodega().setText(bodegaIf.getNombre());
				// fillTable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					criteria, JDPopupFinderModel.BUSQUEDA_TODOS);
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
				colorIf = (ColorIf) popupFinder
						.getElementoSeleccionado();
				getTxtColor().setText(colorIf.getNombre());
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

		getBtnEliminarBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				bodegaIf = null;
				getTxtBodega().setText(null);
			}
		});

		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				productoIf = null;
				getTxtProducto().setText(null);
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
		

	}


	private boolean validateSeleccion() {
		if (bodegaIf == null && productoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar al menos un criterio",
					SpiritAlert.INFORMATION);
			getBtnBuscarBodega().requestFocus();
			return false;
		}
		if (cambios) {
			if (SpiritAlert.confirmDialog(this,
					"¿Desechar los cambios realizados?")) {
				cambios = false;
				return true;
			} else
				return false;
		}

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
		setIconoEliminar("Eliminar Bodega", getBtnEliminarBodega());
		setIconoEliminar("Eliminar Generico", getBtnEliminarGenerico());
		setIconoEliminar("Eliminar Modelo", getBtnEliminarModelo());
		setIconoEliminar("Eliminar Talla", getBtnEliminarTalla());
		setIconoEliminar("Eliminar Producto", getBtnEliminarProducto());
		setIconoEliminar("Eliminar Color", getBtnEliminarColor());
		
		
		getBtnEliminarBodega().setVisible(false);
		getTblStock().setModel(tomaInventarioTableModel);
	}

	private List consultaStock() throws GenericBusinessException {
		Long idBodega = null;
		Long idProducto = null;
		Long idModelo = null;
		Long idGenerico = null;
		Long idPresentacion = null;
		Long idColor =null;
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
		return (List<StockInventarioData>) SessionServiceLocator
				.getStockSessionService().getConsultaCierreStock(idBodega,
						idProducto, idGenerico, idPresentacion, idModelo,idColor);
	}

	private void fillTable() {
		try {
			if (!validateSeleccion())
				return;
			List listaKardex = consultaStock();

			if (listaKardex != null && listaKardex.size() > 0) {
				tomaInventarioTableModel.refresh(listaKardex);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tomaInventarioTableModel
					.refresh(new ArrayList<StockInventarioData>());
			SpiritAlert.createAlert(e.getMessage(), "", SpiritAlert.WARNING);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tomaInventarioTableModel
					.refresh(new ArrayList<StockInventarioData>());
			SpiritAlert.createAlert("Error general", "", SpiritAlert.WARNING);
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
	}

	@Override
	public void report() {

	}

	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		List<StockInventarioData> listaAjustesPositivos = new ArrayList<StockInventarioData>();
		List<StockInventarioData> listaAjustesNegativos = new ArrayList<StockInventarioData>();
		String mensajeConfirmacion = "Los siguientes productos van a ser ajustados: \n";
		for (StockInventarioData stockInventarioData : tomaInventarioTableModel
				.getListaStock()) {
			BigDecimal cantidad = stockInventarioData.getCantidad();
			BigDecimal stockFisico = stockInventarioData.getCantidadFisica();
			int compare = cantidad.compareTo(stockFisico);
			// TODO: PILAS CON EL REDONDEO
			if (compare != 0) {
				BigDecimal diferencia = stockFisico.subtract(cantidad);
				mensajeConfirmacion += stockInventarioData.getProducto()
						+ " en: " + diferencia + "\n";
			}
			if (compare > 0) {
				listaAjustesNegativos.add(stockInventarioData);
			} else if (compare < 0) {
				listaAjustesPositivos.add(stockInventarioData);
			}
		}
		int cantidadAjustes = listaAjustesPositivos.size()
				+ listaAjustesNegativos.size();
		if (cantidadAjustes == 0) {
			SpiritAlert.createAlert(
					"No hay cambios en inventario por realizar",
					SpiritAlert.INFORMATION);
			return;
		}
		mensajeConfirmacion += "¿Esta seguro que desea realizar ["
				+ cantidadAjustes + "] ajuste(s)?";
		if (SpiritAlert.confirmDialog(this, mensajeConfirmacion)) {
			try {
				SessionServiceLocator.getMovimientoSessionService()
						.generarAjustes((UsuarioIf) Parametros.getUsuarioIf(),
								listaAjustesPositivos, listaAjustesNegativos);
				SpiritAlert.createAlert("Ajustes realizados con exito",
						SpiritAlert.INFORMATION);
				cambios=false;
				tomaInventarioTableModel
					.refresh(new ArrayList<StockInventarioData>());

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al crear los ajustes",
						SpiritAlert.ERROR);
			}

		}
	}

	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			StockInventarioData stockInventarioData = tomaInventarioTableModel
					.getListaStock().get(row);

			BigDecimal cantidad = stockInventarioData.getCantidad();
			BigDecimal stockFisico = stockInventarioData.getCantidadFisica();
			// TODO: PILAS CON EL REDONDEO
			if (cantidad.compareTo(stockFisico) > 0) {
				cambios = true;
				setBackground(Color.CYAN);
			} else if (cantidad.compareTo(stockFisico) < 0) {
				cambios = true;
				setBackground(Color.MAGENTA);
			} else {
				setBackground(null);
			}
			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
		}
	};

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showFindMode() {
		// TODO Auto-generated method stub

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}

}
