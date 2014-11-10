package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.gui.criteria.BodegaCriteria;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPStockValorizado;
import com.spirit.inventario.handler.StockValorizadoData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class StockValorizadoModel extends JPStockValorizado {
	private JDPopupFinderModel popupFinder;
	private BodegaIf bodegaIf;
	private HashMap<String, Object> parametrosMap = new HashMap<String, Object>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private BigDecimal totalStockValorizado = BigDecimal.ZERO;

	public StockValorizadoModel() {
		// anchoColumnasTabla();
		iniciarComponentes();
		// initKeyListeners();
		initListeners();
		// cargarCombos();
		// this.showSaveMode();
		iniciarTabla();
		new HotKeyComponent(this);
	}

	private void iniciarTabla() {
		getTblStockValorizado().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(0));
		anchoColumna.setMinWidth(100);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(1));
		anchoColumna.setMinWidth(200);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(2));
		anchoColumna.setMinWidth(100);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(3));
		anchoColumna.setMinWidth(100);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(4));
		anchoColumna.setMinWidth(50);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(5));
		anchoColumna.setMinWidth(100);
		anchoColumna = getTblStockValorizado().getColumn(getTblStockValorizado().getColumnName(6));
		anchoColumna.setMinWidth(200);
		getTblStockValorizado().getTableHeader().setReorderingAllowed(false);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblStockValorizado().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblStockValorizado().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblStockValorizado().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}

	private void buscarBodega() {
		try {
			BodegaCriteria bodegaCriteria = new BodegaCriteria();
			Map queryBuilded = new HashMap();
			queryBuilded.put("estado", "A");
			bodegaCriteria.setQueryBuilded(queryBuilded);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), bodegaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				bodegaIf = (BodegaIf) popupFinder.getElementoSeleccionado();
				getTxtBodega().setText(bodegaIf.getNombre());
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

		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				totalStockValorizado = BigDecimal.ZERO;
				Vector<StockValorizadoData> stockValorizadoVector = consultarStockValorizado();
				DefaultTableModel tableModel = (DefaultTableModel) getTblStockValorizado().getModel();
				for (int i=0; i<stockValorizadoVector.size(); i++) {
					StockValorizadoData stockValorizado = stockValorizadoVector.get(i);
					Vector<String> fila = new Vector<String>();
					fila.add(stockValorizado.getSku());
					fila.add(stockValorizado.getModelo());
					fila.add(stockValorizado.getPresentacion());
					fila.add(stockValorizado.getColor());
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(stockValorizado.getStock())));
					fila.add(String.valueOf(Utilitarios.redondeoValor(stockValorizado.getPromedioUnitario(), 6)));
					fila.add(String.valueOf(Utilitarios.redondeoValor(stockValorizado.getStockValorizado(), 6)));
					totalStockValorizado = totalStockValorizado.add(stockValorizado.getStockValorizado());
					tableModel.addRow(fila);
				}
				setCursor(SpiritCursor.normalCursor);
			}
		});

	}

	private void setIconoBusqueda(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	private void iniciarComponentes() {
		setIconoBusqueda("Buscar Bodega", getBtnBuscarBodega());
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		getCmbFechaCorte().setLocale(Utilitarios.esLocale);
		getCmbFechaCorte().setEditable(false);
		getCmbFechaCorte().setShowNoneButton(false);
		getCmbFechaCorte().setDate(cal.getTime());
		parametrosMap.put("codigoReporte", "CSV");
		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		parametrosMap.put("empresa", empresa.getNombre());
		parametrosMap.put("ruc", empresa.getRuc());
		parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
		parametrosMap.put("usuario", Parametros.getUsuario());
	}

	private Vector<StockValorizadoData> consultarStockValorizado() {
		Vector<StockValorizadoData> stockValorizado = new Vector<StockValorizadoData>();
		try {
			Long idBodega = bodegaIf != null?bodegaIf.getId():null;
			return SessionServiceLocator.getStockSessionService().getStockValorizado(Parametros.getIdEmpresa(), idBodega, getCmbFechaCorte().getDate());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar stock valorizado", SpiritAlert.ERROR);
		}
		return stockValorizado;
	}

	/*private void generarKardex() {
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

	}*/

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblStockValorizado().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/inventario/RPStockValorizado.jasper";
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator().next();
					MenuIf menu = null;
					Iterator menuIT = SessionServiceLocator.getMenuSessionService().findMenuByNombre("STOCK VALORIZADO").iterator();
					if(menuIT.hasNext()) menu= (MenuIf) menuIT.next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
					parametrosMap.put("fechaCorte", getCmbFechaCorte().getDate().toString());
					parametrosMap.put("totalStockValorizado", Utilitarios.redondeoValor(totalStockValorizado,6).toString());
					ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		} catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}

	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

}
