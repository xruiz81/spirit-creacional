package com.spirit.general.gui.model;

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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaData;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.panel.JPCuentasBancarias;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CuentasBancariasModel extends JPCuentasBancarias {

	private static final long serialVersionUID = -3261934736931793310L;
	private Vector cuentasBancariasVector = new Vector();
	private DefaultTableModel tableModel;
	private int cuentaBancariaSeleccionada;
	private CuentaBancariaIf cuentaBancariaIf;
	private static final String TIPOCUENTA_AHORROS = "A - AHORROS";
	private static final String TIPOCUENTA_CORRIENTE = "C - CORRIENTE";
	private static final int MAX_LONGITUD_CUENTA = 20;
	Vector cuentasCollection = new Vector();
	Calendar now = Calendar.getInstance();
	ClienteIf cliente;

	public CuentasBancariasModel() {
		initKeyListeners();
		initListeners();
		setSorterTable(getTblCuentasDetalles());
		addPopupMenu();
		showSaveMode();
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTblCuentasDetalles().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTxtCuenta().addKeyListener(new TextChecker(MAX_LONGITUD_CUENTA));
	}

	private void initListeners() {
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "CL";
				String tituloVentanaBusqueda = "";
					
				ClienteCriteria clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cliente = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(cliente.getIdentificacion() + " - " + cliente.getRazonSocial());
				}
			}
		});
		
		getCbCuentaCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCbCuentaCliente().isSelected())
					getBtnBuscarCliente().setEnabled(true);
				else {
					getBtnBuscarCliente().setEnabled(false);
					getTxtCliente().setText("");
					cliente = null;
				}
			}			
		});
	}
	
	private void addPopupMenu() {
		// popup para borrar registros de la tabla de cuenta
		menuItem = new JMenuItem("Eliminar");
		popup.add(menuItem);
		this.getTblCuentasDetalles().addMouseListener(oMouseAdapterTblCuenta);
		this.getTblCuentasDetalles().addKeyListener(oKeyAdapterTblCuenta);
	}

	public void save() {

		try {
			if (validateFields()) {
				CuentaBancariaData cuentaBancaria = new CuentaBancariaData();
				BancoIf banco = (BancoIf) getCmbBanco().getSelectedItem();
				cuentaBancaria.setBancoId(banco.getId());
				String tipoCuenta = (String) getCmbTipoCuenta().getSelectedItem();
				cuentaBancaria.setTipocuenta(tipoCuenta.substring(0, 1));
				cuentaBancaria.setCuenta(getTxtCuenta().getText());
				cuentaBancaria.setEmpresaId(Parametros.getIdEmpresa());
				cuentaBancaria.setNumeroCheque(getTxtNumeroCheque().getText());
				if (getCbCuentaCliente().isSelected()) {
					cuentaBancaria.setCuentaCliente(SpiritConstants.getOptionYes().substring(0,1));
					cuentaBancaria.setClienteId(cliente.getId());
				} else {
					cuentaBancaria.setCuentaCliente(SpiritConstants.getOptionNo().substring(0,1));
					cuentaBancaria.setClienteId(null);
				}
				SessionServiceLocator.getCuentaBancariaSessionService().addCuentaBancaria(cuentaBancaria);
				SpiritAlert.createAlert("Registro guardado con éxito !", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomacion!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			CuentaBancariaIf cuentaBancariaIf = (CuentaBancariaIf) getCuentasBancariasVector().get(getCuentaBancariaSeleccionada());
			SessionServiceLocator.getCuentaBancariaSessionService().deleteCuentaBancaria(cuentaBancariaIf.getId());
			SpiritAlert.createAlert("Registro eliminado con éxito !", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setCuentaBancariaActualizadaIf((CuentaBancariaIf) getCuentasBancariasVector().get(getCuentaBancariaSeleccionada()));
				BancoIf banco = (BancoIf) getCmbBanco().getSelectedItem();
				getCuentaBancariaActualizadaIf().setBancoId(banco.getId());
				String tipoCuenta = (String) getCmbTipoCuenta().getSelectedItem();
				getCuentaBancariaActualizadaIf().setTipocuenta(tipoCuenta.substring(0, 1));
				getCuentaBancariaActualizadaIf().setCuenta(getTxtCuenta().getText());
				getCuentaBancariaActualizadaIf().setEmpresaId(Parametros.getIdEmpresa());
				getCuentaBancariaActualizadaIf().setNumeroCheque(getTxtNumeroCheque().getText());
				if (getCbCuentaCliente().isSelected()) {
					getCuentaBancariaActualizadaIf().setCuentaCliente(SpiritConstants.getOptionYes().substring(0,1));
					getCuentaBancariaActualizadaIf().setClienteId(cliente.getId());
				} else {
					getCuentaBancariaActualizadaIf().setCuentaCliente(SpiritConstants.getOptionNo().substring(0,1));
					getCuentaBancariaActualizadaIf().setClienteId(null);
				}
				getCuentasBancariasVector().setElementAt(getCuentaBancariaActualizadaIf(), getCuentaBancariaSeleccionada());
				SessionServiceLocator.getCuentaBancariaSessionService().saveCuentaBancaria(getCuentaBancariaActualizadaIf());
				setCuentaBancariaActualizadaIf(null);
				SpiritAlert.createAlert("Registro actualizado con éxito !", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomaci\u00f3n!", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCuenta().getText()) && (this.getCmbBanco().getSelectedItem() == null) && (this.getCmbTipoCuenta().getSelectedItem() == null))
			return true;
		else
			return false;
	}

	public void clean() {
		// Vacio las tablas
		DefaultTableModel model = (DefaultTableModel) this.getTblCuentasDetalles().getModel();

		for (int i = this.getTblCuentasDetalles().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		cuentasCollection = new Vector();
		cliente = null;
		this.getTxtCuenta().setText("");
		this.getCmbBanco().setSelectedItem(null);
		this.getCmbBanco().removeAllItems();
		this.getCmbTipoCuenta().setSelectedItem(null);
		this.getCmbTipoCuenta().removeAllItems();
		this.getTxtCliente().setText("");
		this.repaint();
	}

	public void cargarCombos() {
		getCmbTipoCuenta().addItem(TIPOCUENTA_CORRIENTE);
		getCmbTipoCuenta().addItem(TIPOCUENTA_AHORROS);
		cargarComboBanco();
	}
	
	private void cargarComboBanco(){
		try {
			List bancos = (List) SessionServiceLocator.getBancoSessionService().getBancoList();
			refreshCombo(getCmbBanco(), bancos);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCuenta().setEnabled(true);
		getCmbBanco().setEnabled(true);
		getCmbTipoCuenta().setEnabled(true);
		getCbCuentaCliente().setSelected(false);
		getBtnBuscarCliente().setEnabled(false);
		clean();
		cargarCombos();
		cargarTabla();		
		getCmbBanco().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		//getTxtCuenta().setEnabled(false);
		getTblCuentasDetalles().grabFocus();
	}
	
	public void refresh(){
		cargarComboBanco();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			Collection cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa());
			Iterator cuentasBancariasIterator = cuentasBancarias.iterator();

			if (!getCuentasBancariasVector().isEmpty()) {
				getCuentasBancariasVector().removeAllElements();
			}

			while (cuentasBancariasIterator.hasNext()) {
				CuentaBancariaIf cuentasBancariasIf = (CuentaBancariaIf) cuentasBancariasIterator.next();
				tableModel = (DefaultTableModel) getTblCuentasDetalles().getModel();
				Vector<String> fila = new Vector<String>();
				getCuentasBancariasVector().add(cuentasBancariasIf);
				agregarColumnasTabla(cuentasBancariasIf, fila);
				tableModel.addRow(fila);
			}
			
			Utilitarios.scrollToCenter(getTblCuentasDetalles(), cuentasBancarias, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(CuentaBancariaIf cuentasBancariasIf, Vector<String> fila) {
		try {
			BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentasBancariasIf.getBancoId());
			fila.add(banco.getNombre());

			if ((TIPOCUENTA_AHORROS.substring(0, 1)).equals(cuentasBancariasIf.getTipocuenta()))
				fila.add(TIPOCUENTA_AHORROS);
			else
				fila.add(TIPOCUENTA_CORRIENTE);

			fila.add(cuentasBancariasIf.getCuenta());
			fila.add(cuentasBancariasIf.getNumeroCheque());

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		String strCuenta = this.getTxtCuenta().getText();

		if (getCmbBanco().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Banco !!", SpiritAlert.WARNING);
			this.getCmbBanco().grabFocus();
			return false;
		}

		if (this.getCmbTipoCuenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Cuenta !!", SpiritAlert.WARNING);
			this.getCmbTipoCuenta().grabFocus();
			return false;
		}

		if ((("".equals(strCuenta)) || (strCuenta == null))) {
			SpiritAlert.createAlert("Debe ingresar un Número de Cuenta !!", SpiritAlert.WARNING);
			this.getTxtCuenta().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtNumeroCheque().getText())) || (getTxtNumeroCheque().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un Número de Cheque !!", SpiritAlert.WARNING);
			this.getTxtNumeroCheque().grabFocus();
			return false;
		}
		
		if (getCbCuentaCliente().isSelected() && cliente == null) {
			SpiritAlert.createAlert("Debe seleccionar cliente", SpiritAlert.WARNING);
			this.getBtnBuscarCliente().grabFocus();
			return false;
		}

		return true;
	}

	MouseListener oMouseAdapterTblCuenta = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblCuenta = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setCuentaBancariaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			cuentaBancariaIf = (CuentaBancariaIf) getCuentasBancariasVector().get(getCuentaBancariaSeleccionada());
			getCmbBanco().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBanco(),cuentaBancariaIf.getBancoId()));
			getCmbBanco().repaint();
			// Seteo la Cuenta
			for (int i = 0; i < getCmbTipoCuenta().getItemCount(); i++) {
				String bean = (String) getCmbTipoCuenta().getItemAt(i);
				if ((bean.substring(0, 1)).compareTo(cuentaBancariaIf.getTipocuenta()) == 0)
					getCmbTipoCuenta().setSelectedItem(bean);
			}
			getTxtCuenta().setText(cuentaBancariaIf.getCuenta());
			getTxtNumeroCheque().setText(cuentaBancariaIf.getNumeroCheque());
			if (cuentaBancariaIf.getCuentaCliente().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				getCbCuentaCliente().setSelected(true);
				try {
					cliente = SessionServiceLocator.getClienteSessionService().getCliente(cuentaBancariaIf.getClienteId());
					getTxtCliente().setText(cliente.getIdentificacion() + " - " + cliente.getRazonSocial());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al cargar los datos", SpiritAlert.ERROR);
				}
			} else {
				getCbCuentaCliente().setSelected(false);
				cliente = null;
				getTxtCliente().setText("");
			}
			showUpdateMode();
		}
	}

	public Vector getCuentasBancariasVector() {
		return cuentasBancariasVector;
	}

	public void setCuentasBancariasVector(Vector cuentasBancariasVec) {
		cuentasBancariasVector = cuentasBancariasVec;
	}

	public int getCuentaBancariaSeleccionada() {
		return cuentaBancariaSeleccionada;
	}

	public void setCuentaBancariaSeleccionada(int selectedCuentaBancaria) {
		cuentaBancariaSeleccionada = selectedCuentaBancaria;
	}

	public CuentaBancariaIf getCuentaBancariaActualizadaIf() {
		return cuentaBancariaIf;
	}

	public void setCuentaBancariaActualizadaIf(CuentaBancariaIf cuentaBancariaIf) {
		this.cuentaBancariaIf = cuentaBancariaIf;
	}
	
	 
}