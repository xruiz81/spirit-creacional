package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.gui.helper.NumericDocument;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.inventario.gui.panel.JPStockOperativo;
import com.spirit.inventario.gui.tblmodel.StockOperativoTableModel;
import com.spirit.inventario.helper.StockOperativoDataModel;
import com.spirit.util.Utilitarios;

public class StockOperativoModel extends JPStockOperativo {
	private HashMap<String, Object> mapaQueryBodega = new HashMap<String, Object>();
	private StockOperativoTableModel stockOperativoTableModel = new StockOperativoTableModel();

	public StockOperativoModel() {
		iniciarComponentes();
		initListeners();
		anchoColumnasTabla();
		cargarComboBodega();

		getCmbMesAnio().setLocale(Utilitarios.esLocale);
		getCmbMesAnio().setShowNoneButton(false);
		//getCmbMesAnio().setFormat(Utilitarios.setFechaSinDiaUppercase());
		getCmbMesAnio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesAnio().setEditable(false);
		getCmbMesAnio().setDate(new Date());

		getCmbCopiarDe().setLocale(Utilitarios.esLocale);
		getCmbCopiarDe().setShowNoneButton(false);
		//getCmbCopiarDe().setFormat(Utilitarios.setFechaSinDiaUppercase());
		getCmbCopiarDe().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbCopiarDe().setEditable(false);

		new HotKeyComponent(this);
	}

	private void iniciarComponentes() {
		getBtnConsultar().setText(null);
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/refresh_record_16.png"));

		getBtnActualizarTodos().setText(null);
		getBtnActualizarTodos().setToolTipText("Actualizar Todos..");
		getBtnActualizarTodos()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/selectElement.png"));

		getTblStockOperativo().setModel(stockOperativoTableModel);
	}

	private void initListeners() {

		getBtnActualizarTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String campo = (String) getCmbParametro().getSelectedItem();
				String valor = getTxtParametro().getText();
				if (valor == null || valor.trim().equalsIgnoreCase("")) {
					SpiritAlert.createAlert("Debe especificar un valor" + "",
							SpiritAlert.ERROR);
					getTxtParametro().requestFocus();
					return;
				}
				List<StockOperativoDataModel> lista = stockOperativoTableModel
						.getListaStockOperativoDataModel();

				if (lista.isEmpty()) {
					SpiritAlert.createAlert("Debe cargar datos del mes",
							SpiritAlert.ERROR);
					getBtnConsultar().requestFocus();
					return;
				}

				for (StockOperativoDataModel stockOperativoDataModel : lista) {
					if (campo.equalsIgnoreCase("MIN")) {
						stockOperativoDataModel.setMin(new BigDecimal(valor));
					} else if (campo.equalsIgnoreCase("MAX")) {
						stockOperativoDataModel.setMax(new BigDecimal(valor));
					} else if (campo.equalsIgnoreCase("T.M.R.")) {
						stockOperativoDataModel
								.setTiempoMinimoReposision(new Long(valor));
					}
				}
				getTblStockOperativo().repaint();
			}

		});

		getBtnCopiarDe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				BodegaIf bodegaSeleccionada = (BodegaIf) getCmbBodega()
						.getSelectedItem();
				try {
					List<StockOperativoDataModel> stockOperativoDataModeList = com.spirit.comun.util.SessionServiceLocator
							.getStockOperativoSessionService()
							.consultaStockOperativo(bodegaSeleccionada.getId(),
									getCmbCopiarDe().getDate());
					stockOperativoTableModel
							.refresh(stockOperativoDataModeList);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});

		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				BodegaIf bodegaSeleccionada = (BodegaIf) getCmbBodega()
						.getSelectedItem();
				try {
					List<StockOperativoDataModel> stockOperativoDataModeList = com.spirit.comun.util.SessionServiceLocator
							.getStockOperativoSessionService()
							.consultaStockOperativo(bodegaSeleccionada.getId(),
									getCmbMesAnio().getDate());
					stockOperativoTableModel
							.refresh(stockOperativoDataModeList);
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void anchoColumnasTabla() {
		getTblStockOperativo().getTableHeader().setReorderingAllowed(false);
		getTblStockOperativo()
				.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumn anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(0));
		anchoColumna.setMaxWidth(500);

		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(1));
		anchoColumna.setMaxWidth(250);
		JTextField txtMin = new JTextField();
		txtMin.setDocument(new NumericDocument(12, 2));
		DefaultCellEditor minCellEditor = new DefaultCellEditor(txtMin);
		anchoColumna.setCellEditor(minCellEditor);

		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(2));
		anchoColumna.setMaxWidth(250);
		JTextField txtMax = new JTextField();
		txtMax.setDocument(new NumericDocument(12, 2));
		DefaultCellEditor maxCellEditor = new DefaultCellEditor(txtMin);
		anchoColumna.setCellEditor(maxCellEditor);

		anchoColumna = getTblStockOperativo().getColumn(
				getTblStockOperativo().getColumnName(3));
		anchoColumna.setMaxWidth(250);
		JTextField txtTMin = new JTextField();
		txtTMin.setDocument(new NumericDocument(12, 2));
		DefaultCellEditor txtTMinCellEditor = new DefaultCellEditor(txtTMin);
		anchoColumna.setCellEditor(txtTMinCellEditor);

	}

	private void cargarComboBodega() {
		try {
			List bodegas = (List) SessionServiceLocator
					.getBodegaSessionService().findBodegaByEmpresaId(
							Parametros.getIdEmpresa());
			getCmbBodega().removeAllItems();
			refreshCombo(getCmbBodega(), bodegas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		BodegaIf bodegaSeleccionada = (BodegaIf) getCmbBodega()
				.getSelectedItem();
		try {

			com.spirit.comun.util.SessionServiceLocator
					.getStockOperativoSessionService().procesar(
							stockOperativoTableModel
									.getListaStockOperativoDataModel(),
							getCmbMesAnio().getDate(),
							bodegaSeleccionada.getId());
			SpiritAlert.createAlert("Registros actualizados con exito",
					SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Ocurrio un error al intentar guardar los cambios",
					SpiritAlert.INFORMATION);
		}

	}

	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub

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

}
