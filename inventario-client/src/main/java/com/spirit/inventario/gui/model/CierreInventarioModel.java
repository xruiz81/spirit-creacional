package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.gui.criteria.BodegaCriteria;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPCierreInventario;
import com.spirit.inventario.gui.tblmodel.CierreInventarioTableModel;
import com.spirit.inventario.helper.StockInventarioData;

public class CierreInventarioModel extends JPCierreInventario {
	private static final long serialVersionUID = -9181526743759769481L;
	private JDPopupFinderModel popupFinder;
	private BodegaIf bodegaIf;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options = {si,no}; 
	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();

	private CierreInventarioTableModel cierreInventarioTableModel = new CierreInventarioTableModel();

	public CierreInventarioModel() {
		iniciarComponentes();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		getTblStock().getTableHeader().setReorderingAllowed(false);
		getTblStock().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumn anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(0));
		anchoColumna.setMinWidth(130);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(1));
		anchoColumna.setMinWidth(350);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(2));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(3));
		anchoColumna.setMinWidth(80);
		anchoColumna = getTblStock().getColumn(getTblStock().getColumnName(4));
		//anchoColumna.setMinWidth(200);
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
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			if (popupFinder.getElementoSeleccionado() != null) {
				bodegaIf = (BodegaIf) popupFinder.getElementoSeleccionado();
				getTxtBodega().setText(bodegaIf.getNombre());
				fillTable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initListeners() {
		getBtnBuscarBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				buscarBodega();
				setCursor(SpiritCursor.normalCursor);
			}

		});

		getBtnRefresh().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				fillTable();
				setCursor(SpiritCursor.normalCursor);
			}
		});

		getBtnCerrarInventario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				cerrarInventario();
				setCursor(SpiritCursor.normalCursor);
			}
		});

	}

	private boolean validateSeleccionBodega() {
		if (bodegaIf == null) {
			SpiritAlert.createAlert("Debe seleccionar la Bodega",
					SpiritAlert.INFORMATION);
			getBtnBuscarBodega().requestFocus();
			return false;
		} else
			return true;
	}

	private void cerrarInventario() {
		try {
			if(!validateSeleccionBodega())
				return;
			SessionServiceLocator.getStockSessionService().cerrarStock(bodegaIf.getId());
			//int opcion = JOptionPane.showOptionDialog(null, "¿Desea realizar un recálculo del stock actual? (Recomendado)", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
			//if(opcion == JOptionPane.YES_OPTION)
			SessionServiceLocator.getMovimientoSessionService().recalcularStockPorCierre(bodegaIf.getId());
			fillTable();
			SpiritAlert.createAlert("Inventario de bodega: " + bodegaIf.toString() + " cerrada con exito", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cerrar el inventario", SpiritAlert.ERROR);
		}
	}

	public void refresh() {
		setCursor(SpiritCursor.hourglassCursor);
		fillTable();
		setCursor(SpiritCursor.normalCursor);
	}

	private void setIconoBusqueda(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	private void setIconoRefresh(String toolTip, JButton boton) {
		boton.setText(null);
		boton.setToolTipText(toolTip);
		boton.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/refresh_record_16.png"));
	}

	private void iniciarComponentes() {
		setIconoBusqueda("Buscar Bodega", getBtnBuscarBodega());
		setIconoRefresh("Refrescar", getBtnRefresh());
		//getTblStock().get
		getTblStock().setModel(cierreInventarioTableModel);
	}

	private void fillTable() {
		try {
			if(!validateSeleccionBodega())
				return;
			cierreInventarioTableModel.refresh(SessionServiceLocator.getStockSessionService().getConsultaCierreStock(bodegaIf.getId(),null,null,null,null,null));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			cierreInventarioTableModel.refresh(new ArrayList<StockInventarioData>());
			SpiritAlert.createAlert(e.getMessage(), "", SpiritAlert.WARNING);
		} catch (Exception e) {
			e.printStackTrace();
			cierreInventarioTableModel.refresh(new ArrayList<StockInventarioData>());
			SpiritAlert.createAlert("Error general", "", SpiritAlert.WARNING);
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void report() {
		// TODO Auto-generated method stub

	}

	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

}
