package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.OficinaCriteria;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.BodegaCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPKardex;
import com.spirit.inventario.gui.tblmodel.KardexTableModel;

public class KardexModel extends JPKardex {

	private JDPopupFinderModel popupFinder;
	private ProductoIf productoIf;
	private OficinaIf oficinaIf;
	private BodegaIf bodegaIf;

	private HashMap<String, Object> mapaQueryOficina = new HashMap<String, Object>();
	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();
	private HashMap<String, Object> mapaQueryProducto = new HashMap<String, Object>();

	private KardexTableModel kardexTableModel = new KardexTableModel();

	public KardexModel() {
		// anchoColumnasTabla();
		iniciarComponentes();
		// initKeyListeners();
		initListeners();
		// cargarCombos();
		// this.showSaveMode();
		anchoColumnasTabla();
		new HotKeyComponent(this);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblKardex().getColumn(
				getTblKardex().getColumnName(0));
		getTblKardex().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		anchoColumna.setMinWidth(130);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(1));
		anchoColumna.setMinWidth(150);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(2));
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(3));
		anchoColumna.setMinWidth(350);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(4));
		anchoColumna.setMinWidth(120);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(5));
		anchoColumna.setMinWidth(250);
		anchoColumna = getTblKardex()
				.getColumn(getTblKardex().getColumnName(6));
		anchoColumna.setMinWidth(80);
	}

	private void buildQueryOficina() {
		mapaQueryOficina.clear();
		mapaQueryOficina.put("codigo", "%");
	}

	private void buildQueryBodega() {
		mapaQueryBodega.clear();
		boolean criterioSeleccionado = false;
		if (oficinaIf != null) {
			criterioSeleccionado = true;
			mapaQueryBodega.put("oficinaId", oficinaIf.getId());
		}
		if (!criterioSeleccionado)
			mapaQueryBodega.put("codigo", "%");
	}

	private void buildQueryProducto() {
		mapaQueryProducto.clear();
		boolean criterioSeleccionado = true;
		if (oficinaIf != null) {
			criterioSeleccionado = true;
			mapaQueryProducto.put("oficinaId", oficinaIf.getId());
		}
		if (!criterioSeleccionado)
			mapaQueryProducto.put("codigo", "%");
	}

	private void buscarOficina() {
		try {
			String mmpg = "";
			OficinaCriteria productoCriteria = new OficinaCriteria();
			buildQueryOficina();
			productoCriteria.setQueryBuilded(mapaQueryOficina);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				oficinaIf = (OficinaIf) popupFinder.getElementoSeleccionado();
				getTxtOficina().setText(oficinaIf.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buscarBodega() {

		try {
			String mmpg = "";
			BodegaCriteria productoCriteria = new BodegaCriteria();
			buildQueryBodega();
			productoCriteria.setQueryBuilded(mapaQueryBodega);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					productoCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				bodegaIf = (BodegaIf) popupFinder.getElementoSeleccionado();
				getTxtBodega().setText(bodegaIf.getNombre());
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
			buildQueryProducto();
			productoCriteria.setQueryBuilded(mapaQueryProducto);
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

	private void initListeners() {

		getBtnBuscarOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOficina();
			}
		});

		getBtnBuscarBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarBodega();
			}

		});

		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});

		getBtnGenerarKardex().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				generarKardex();
			}
		});

		getBtnEliminarBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarBodega();
			}

		});

		getBtnEliminarOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarOficina();
			}
		});

		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProducto();
			}
		});

		getBtnConsulta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fillTable();
			}
		});

	}

	private void eliminarOficina() {
		this.oficinaIf = null;
		this.getTxtOficina().setText(null);
	}

	private void eliminarProducto() {
		this.productoIf = null;
		this.getTxtProducto().setText(null);
	}

	private void eliminarBodega() {
		this.bodegaIf = null;
		this.getTxtBodega().setText(null);
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

	private void iniciarComponentes() {
		setIconoBusqueda("Buscar Oficina", getBtnBuscarOficina());
		setIconoBusqueda("Buscar Bodega", getBtnBuscarBodega());
		setIconoBusqueda("Buscar Producto", getBtnBuscarProducto());

		setIconoEliminar("Eliminar Oficina", getBtnEliminarOficina());
		setIconoEliminar("Eliminar Bodega", getBtnEliminarBodega());
		setIconoEliminar("Eliminar Producto", getBtnEliminarProducto());

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		getCmbFechaFinal().setDate(cal.getTime());
		
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DAY_OF_MONTH, -dia+1);
		Date primerDiaMes = cal.getTime();

		getCmbFechaInicial().setDate(primerDiaMes);

		getTblKardex().setModel(kardexTableModel);
		
		parametrosMap.put("codigoReporte", "KARDEX");
		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		parametrosMap.put("empresa", empresa.getNombre());
		parametrosMap.put("ruc", empresa.getRuc());
		parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
		parametrosMap.put("usuario", Parametros.getUsuario());
	}

	private List consultarKardex() {
		try {
			if (getCmbFechaInicial().getDate().compareTo(
					getCmbFechaFinal().getDate()) >= 0) {
				SpiritAlert.createAlert(
						"La fecha inicial debe ser menor a la final",
						SpiritAlert.ERROR);
				return null;
			}

			Long idOficina = null;
			Long idBodega = null;
			Long idProducto = null;
			if (oficinaIf != null)
				idOficina = oficinaIf.getId();
			if (bodegaIf != null)
				idBodega = bodegaIf.getId();
			if (productoIf != null)
				idProducto = productoIf.getId();

			return SessionServiceLocator.getMovimientoSessionService()
					.generarKardex(Parametros.getIdEmpresa(), idOficina,
							idBodega, idProducto,
							getCmbFechaInicial().getDate(),
							getCmbFechaFinal().getDate());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private void fillTable() {
		setCursor(SpiritCursor.hourglassCursor);
		kardexTableModel.refresh(consultarKardex());
		setCursor(SpiritCursor.normalCursor);
	}

	private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
	private HashMap<String, Object> parametrosMap = new HashMap<String, Object>();
	private void generarKardex() {
		try {
			setCursor(SpiritCursor.hourglassCursor);
			List listaKardex = kardexTableModel.getListaKardex();
			if (listaKardex.size() > 0) {
				if (SpiritAlert.confirmDialog(this,
						"¿Volver a realizar consulta?")) {
					listaKardex = consultarKardex();
				}
			} else {
				listaKardex = consultarKardex();
			}
			setCursor(SpiritCursor.normalCursor);
			if (listaKardex != null && listaKardex.size() > 0) {
				parametrosMap.put("fechaHoy", new Date());
				parametrosMap.put("fechaInicio", formatoSinHora
						.format(getCmbFechaInicial().getDate()));
				parametrosMap.put("fechaFin", formatoSinHora
						.format(getCmbFechaFinal().getDate()));
				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(
						listaKardex);
				ReportModelImpl.processReport(
						"jaspers/inventario/RPKardex.jasper", parametrosMap,
						dataSourceDetail, true);
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

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		generarKardex();
	}

	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

}
