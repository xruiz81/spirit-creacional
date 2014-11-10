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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

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
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.BodegaCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.QueryHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.helper.NumericDocument;
import com.spirit.inventario.gui.panel.JPCierreInventario;
import com.spirit.inventario.gui.panel.JPConsultaInventario;
import com.spirit.inventario.gui.panel.JPConsultaTomaInventario;
import com.spirit.inventario.gui.panel.JPKardex;
import com.spirit.inventario.gui.panel.JPTomaInventario;
import com.spirit.inventario.gui.tblmodel.CierreInventarioTableModel;
import com.spirit.inventario.gui.tblmodel.ConsultaTomaInventarioTableModel;
import com.spirit.inventario.gui.tblmodel.KardexTableModel;
import com.spirit.inventario.gui.tblmodel.TomaInventarioTableModel;
import com.spirit.inventario.helper.KardexData;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;

public class ConsultaTomaInventarioModel extends JPConsultaTomaInventario {

	private JDPopupFinderModel popupFinder;
	private BodegaIf bodegaIf;
	private ProductoIf productoIf;
	private boolean cambios;

	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();

	private ConsultaTomaInventarioTableModel tomaInventarioTableModel = new ConsultaTomaInventarioTableModel();

	public ConsultaTomaInventarioModel() {
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

		getTblStock().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumn anchoColumna = getTblStock().getColumn(
				getTblStock().getColumnName(0));
		anchoColumna.setMaxWidth(130);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(1));
		anchoColumna.setMaxWidth(80);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(2));
		anchoColumna.setMaxWidth(350);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(3));
		anchoColumna.setMaxWidth(130);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(4));
		anchoColumna.setMaxWidth(200);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(5));
		anchoColumna.setMaxWidth(80);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(6));
		anchoColumna.setMaxWidth(80);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(7));
		anchoColumna.setMaxWidth(80);

		
		
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
				BodegaIf bodegaIfTMP = (BodegaIf) popupFinder.getElementoSeleccionado();
				bodegaIf = bodegaIfTMP;
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
				eliminarBodega();
			}
		});

		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProducto();
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

	private boolean validateSeleccion() {
		if (bodegaIf == null && productoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar al menos un criterio",
					SpiritAlert.INFORMATION);
			getBtnBuscarBodega().requestFocus();
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
		setIconoRefresh("Refrescar", getBtnRefresh());
		setIconoEliminar("Eliminar Bodega", getBtnEliminarBodega());
		setIconoEliminar("Eliminar Producto", getBtnEliminarProducto());
		getBtnEliminarBodega().setVisible(false);
		getTblStock().setModel(tomaInventarioTableModel);
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		getCmbFechaFinal().setDate(cal.getTime());
		
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DAY_OF_MONTH, -dia+1);
		Date primerDiaMes = cal.getTime();

		getCmbFechaInicial().setDate(primerDiaMes);

	}

	private List consultaStock() throws GenericBusinessException {
		Long idBodega = null;
		Long idProducto = null;
		Long idSucursal=null;
		if (bodegaIf != null) {
			idBodega = bodegaIf.getId();
			idSucursal=bodegaIf.getOficinaId();
		}
		if (productoIf != null) {
			idProducto = productoIf.getId();
		}
		return SessionServiceLocator.getMovimientoSessionService().getTomasInventario(Parametros.getIdEmpresa(),idSucursal,idBodega, idProducto, getCmbFechaInicial().getDate(), getCmbFechaFinal().getDate());
	}

	private void fillTable() {
		try {
			if (!validateSeleccion())
				return;
			List listaKardex = consultaStock();

			if (listaKardex != null && listaKardex.size() > 0) {
				tomaInventarioTableModel.refresh(listaKardex);
			}else
			{
				tomaInventarioTableModel
				.refresh(new ArrayList<KardexData>());
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tomaInventarioTableModel
					.refresh(new ArrayList<KardexData>());
			SpiritAlert.createAlert(e.getMessage(), "", SpiritAlert.WARNING);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tomaInventarioTableModel
					.refresh(new ArrayList<KardexData>());
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

			List listaKardex = tomaInventarioTableModel.getListaKardex();
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
				parametrosMap.put("usuario", Parametros.getUsuario());

				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(
						listaKardex);
				ReportModelImpl.processReport(
						"jaspers/inventario/RPTomaInventario.jasper",
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
	}


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

}
