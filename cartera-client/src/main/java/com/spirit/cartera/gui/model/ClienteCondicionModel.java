package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.cartera.entity.ClienteCondicionData;
import com.spirit.cartera.entity.ClienteCondicionIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.cartera.gui.panel.JPClienteCondicion;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class ClienteCondicionModel extends JPClienteCondicion {

	private static final long serialVersionUID = -3261934736931793310L;
	private Vector clientesCondicionesVector = new Vector();
	private DefaultTableModel tableModel;
	private int clienteCondicionSeleccionada;
	private ClienteCondicionIf clienteCondicionIf;
	private ClienteIf clienteIf;
	private TipoOrdenIf tipoOrdenIf;
	private SubtipoOrdenIf subtipoOrdenIf;
	private FormaPagoIf formaPagoIf;
	private static int MAX_LONGITUD_OBSERVACIONES = 100;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	Calendar now = Calendar.getInstance();

	public ClienteCondicionModel() {
		initKeyListeners();
		setSorterTable(getTblClienteCondicion());
		initListeners();
		addPopupMenu();
		showSaveMode();
		new HotKeyComponent(this);
		getTblClienteCondicion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void initKeyListeners() {
		getTxtObservaciones().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACIONES));
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setEditable(false);
		getCmbFechaFinal().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setShowNoneButton(false);
	}

	private void addPopupMenu() {
		menuItem = new JMenuItem("Eliminar");
		popup.add(menuItem);
		this.getTblClienteCondicion().addMouseListener(oMouseAdapterTblClienteCondicion);
		this.getTblClienteCondicion().addKeyListener(oKeyAdapterTblClienteCondicion);
	}
	
	MouseListener oMouseAdapterTblClienteCondicion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblClienteCondicion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void initListeners() {
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					//if (getMode() == SpiritMode.SAVE_MODE)
					cargarTabla();
				} else
					clienteIf = null;
			}
		});
		
		getCmbFormaPago().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbFormaPago().getSelectedItem() != null) {
					formaPagoIf = (FormaPagoIf) getCmbFormaPago().getSelectedItem();
				} else
					formaPagoIf = null;
			}
		});
		
		getCmbTipoOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoOrden().getSelectedItem() != null) {
					tipoOrdenIf = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();

					try {
						getCmbSubtipoOrden().removeAllItems();
						SpiritComboBoxModel cmbModelSubtipoOrden = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(tipoOrdenIf.getId()));
						getCmbSubtipoOrden().setModel(cmbModelSubtipoOrden);
						if (getCmbSubtipoOrden().getItemCount() > 0) {
							getCmbSubtipoOrden().setEnabled(true);
							getCmbSubtipoOrden().setSelectedItem(null);
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					tipoOrdenIf = null;
					getCmbSubtipoOrden().setSelectedItem(null);
					getCmbSubtipoOrden().setEnabled(false);
				}
			}
		});
		
		getCmbSubtipoOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbSubtipoOrden().getSelectedItem() != null) {
					subtipoOrdenIf = (SubtipoOrdenIf) getCmbSubtipoOrden().getSelectedItem();
				} else
					subtipoOrdenIf = null;
			}
		});
	}

	public void save() {
		try {
			if (validateFields()) {
				if (validateFields(false)) {
					ClienteCondicionData clienteCondicion = new ClienteCondicionData();
					clienteCondicion.setClienteId(clienteIf.getId());
					clienteCondicion.setSubtipoordenId(subtipoOrdenIf.getId());
					clienteCondicion.setFormapagoId(formaPagoIf.getId());
					clienteCondicion.setObservaciones(getTxtObservaciones().getText());
					clienteCondicion.setFechaini(new java.sql.Date(getCmbFechaInicial().getDate().getTime()));
					clienteCondicion.setFechafin(new java.sql.Date(getCmbFechaFinal().getDate().getTime()));
					SessionServiceLocator.getClienteCondicionSessionService().addClienteCondicion(clienteCondicion);
					showSaveMode();
					SpiritAlert.createAlert("Condición del cliente guardada con éxito !", SpiritAlert.INFORMATION);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				if (validateFields(true)) {
					setClienteCondicionActualizadaIf((ClienteCondicionIf) getClientesCondicionesVector().get(getClienteCondicionSeleccionada()));				
					getClienteCondicionActualizadaIf().setClienteId(clienteIf.getId());
					getClienteCondicionActualizadaIf().setSubtipoordenId(subtipoOrdenIf.getId());
					getClienteCondicionActualizadaIf().setFormapagoId(formaPagoIf.getId());
					getClienteCondicionActualizadaIf().setObservaciones(getTxtObservaciones().getText());
					getClienteCondicionActualizadaIf().setFechaini(new java.sql.Date(getCmbFechaInicial().getDate().getTime()));
					getClienteCondicionActualizadaIf().setFechafin(new java.sql.Date(getCmbFechaFinal().getDate().getTime()));
					getClientesCondicionesVector().setElementAt(getClienteCondicionActualizadaIf(), getClienteCondicionSeleccionada());
					SessionServiceLocator.getClienteCondicionSessionService().saveClienteCondicion(getClienteCondicionActualizadaIf());
					setClienteCondicionActualizadaIf(null);
					showSaveMode();
					SpiritAlert.createAlert("Condición del cliente actualizada con éxito !", SpiritAlert.INFORMATION);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			ClienteCondicionIf clienteCondicionIf = (ClienteCondicionIf) getClientesCondicionesVector().get(getClienteCondicionSeleccionada());
			SessionServiceLocator.getClienteCondicionSessionService().deleteClienteCondicion(clienteCondicionIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Condición del cliente eliminada con éxito !", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	public void refresh(){
		cargarComboFormaPago();
		cargarComboTipoOrden();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public boolean isEmpty() {
		return false;
	}

	public void clean() {
		cleanTable();
		clientesCondicionesVector = new Vector();
		clienteIf = null;
		this.getTxtCliente().setText("");
		formaPagoIf = null;
		this.getCmbFormaPago().setSelectedItem(null);
		tipoOrdenIf = null;
		this.getCmbTipoOrden().setSelectedItem(null);
		subtipoOrdenIf = null;
		this.getCmbSubtipoOrden().setSelectedItem(null);
		this.getCmbFechaInicial().setSelectedItem(null);
		this.getCmbFechaFinal().setSelectedItem(null);
		this.repaint();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) this.getTblClienteCondicion().getModel();

		for (int i = this.getTblClienteCondicion().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void cargarCombos() {
		cargarComboFormaPago();
		cargarComboTipoOrden();
		cargarDateCombos();
	}

	private void cargarDateCombos() {
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());

		if (getMode() == SpiritMode.SAVE_MODE) {
			Calendar calendarInicio = new GregorianCalendar();
			Calendar now = Calendar.getInstance();
			calendarInicio.setTime(now.getTime());

			getCmbFechaInicial().setCalendar(calendarInicio);
			getCmbFechaInicial().getDateModel().setMinDate(now);
			getCmbFechaFinal().setCalendar(calendarInicio);
			getCmbFechaFinal().getDateModel().setMinDate(now);
		}
	}
	
	private void cargarComboFormaPago() {
		try {
			List formasPago = (List) SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbFormaPago(), formasPago);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboTipoOrden() {
		try {
			List tiposOrden = (List) SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoOrden(), tiposOrden);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCliente().setEditable(false);
		getBtnBuscarCliente().setEnabled(true);
		getCmbFormaPago().setEnabled(true);
		getCmbTipoOrden().setEnabled(true);
		getCmbSubtipoOrden().setEnabled(false);
		getCmbFechaInicial().setEnabled(true);
		getCmbFechaFinal().setEnabled(true);
		clean();
		cargarTabla();
		cargarCombos();
		getCmbFormaPago().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCliente().setEditable(false);
		getBtnBuscarCliente().setEnabled(true);
		getCmbFormaPago().setEnabled(true);
		getCmbTipoOrden().setEnabled(true);
		getCmbSubtipoOrden().setEnabled(true);
		getCmbFechaInicial().setEnabled(true);
		getCmbFechaFinal().setEnabled(true);
	}

	private void cargarTabla() {
		try {
			Collection clientesCondiciones = null;
			
			if (clienteIf != null)
				clientesCondiciones = SessionServiceLocator.getClienteCondicionSessionService().findClienteCondicionByClienteId(clienteIf.getId());
			else
				clientesCondiciones = SessionServiceLocator.getClienteCondicionSessionService().getClienteCondicionList();
			
			Iterator clientesCondicionesIterator = clientesCondiciones.iterator();

			if (!getClientesCondicionesVector().isEmpty())
				getClientesCondicionesVector().removeAllElements();

			cleanTable();
			
			int i=0;
			
			while (clientesCondicionesIterator.hasNext()) {
				ClienteCondicionIf clienteCondicionIf = (ClienteCondicionIf) clientesCondicionesIterator.next();
				tableModel = (DefaultTableModel) getTblClienteCondicion().getModel();
				Vector<String> fila = new Vector<String>();
				getClientesCondicionesVector().add(clienteCondicionIf);
				/*if (this.clienteCondicionIf != null) {
					if (this.clienteCondicionIf.getId().compareTo(clienteCondicionIf.getId()) == 0)
						setClienteCondicionSeleccionada(i);
				}*/
				agregarColumnasTabla(clienteCondicionIf, fila);
				tableModel.addRow(fila);
				i++;
			}
			Utilitarios.scrollToCenter(getTblClienteCondicion(), clientesCondiciones, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(ClienteCondicionIf clientesCondicionesIf, Vector<String> fila) {
		try {
			ClienteIf clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clientesCondicionesIf.getClienteId());
			FormaPagoIf formaPagoIf = SessionServiceLocator.getFormaPagoSessionService().getFormaPago(clientesCondicionesIf.getFormapagoId());
			SubtipoOrdenIf subtipoOrdenIf = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(clientesCondicionesIf.getSubtipoordenId());
			TipoOrdenIf tipoOrdenIf = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subtipoOrdenIf.getTipoordenId());
			fila.add(formaPagoIf.getNombre());
			fila.add(clienteIf.getNombreLegal());
			fila.add(tipoOrdenIf.getNombre());
			fila.add(subtipoOrdenIf.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean clienteCondicionRepetido(ClienteIf cliente, SubtipoOrdenIf subtipoOrden, FormaPagoIf formaPago) {
		DateFormat dateFormatterFechas = DateFormat.getDateInstance(DateFormat.DEFAULT);
		String fechaICC = dateFormatterFechas.format(getCmbFechaInicial().getDate().getTime());
		String fechaFCC = dateFormatterFechas.format(getCmbFechaFinal().getDate().getTime());
		
		if (getClientesCondicionesVector().size() != 0) {
			for(int i=0;i<getClientesCondicionesVector().size();i++) {
				ClienteCondicionIf clienteCondicion = (ClienteCondicionIf) getClientesCondicionesVector().get(i);
				String fechaICCTemp = dateFormatterFechas.format(clienteCondicion.getFechaini());
				String fechaFCCTemp = dateFormatterFechas.format(clienteCondicion.getFechafin());

				if(clienteCondicion.getClienteId().equals(cliente.getId()) &&
				   clienteCondicion.getSubtipoordenId().equals(subtipoOrden.getId()) &&
				   clienteCondicion.getFormapagoId().equals(formaPago.getId()) &&	
				   fechaICC.equals(fechaICCTemp) && fechaFCC.equals(fechaFCCTemp)) {
				
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean validateFields() {
		String strObservaciones = this.getTxtObservaciones().getText();
		Date fechaInicio = getCmbFechaInicial().getDate();
		Date fechaFin = getCmbFechaFinal().getDate();
		
		if (clienteIf == null) {
			SpiritAlert.createAlert("Debe ingresar un cliente !!", SpiritAlert.WARNING);
			this.getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if (getCmbFormaPago().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una forma de pago !!", SpiritAlert.WARNING);
			this.getCmbFormaPago().grabFocus();
			return false;
		}

		if (this.getCmbTipoOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de orden !!", SpiritAlert.WARNING);
			this.getCmbTipoOrden().grabFocus();
			return false;
		}
		
		if (this.getCmbSubtipoOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un subtipo de orden !!", SpiritAlert.WARNING);
			this.getCmbSubtipoOrden().grabFocus();
			return false;
		}
		
		if (this.getCmbFechaInicial().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una fecha inicial !!", SpiritAlert.WARNING);
			this.getCmbFechaInicial().grabFocus();
			return false;
		}
		
		if (this.getCmbFechaFinal().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una fecha final !!", SpiritAlert.WARNING);
			this.getCmbFechaFinal().grabFocus();
			return false;
		}
		
		if (fechaInicio.after(fechaFin)) {
			SpiritAlert.createAlert("La Fecha Inicial no puede estar después de la Fecha Final!", SpiritAlert.WARNING);
			getCmbFechaInicial().grabFocus();
			return false;
		}

		if ((("".equals(strObservaciones)) || (strObservaciones == null))) {
			SpiritAlert.createAlert("Debe ingresar las observaciones para la condición del cliente !!", SpiritAlert.WARNING);
			this.getTxtObservaciones().grabFocus();
			return false;
		}

		return true;
	}
	
	private boolean validateFields(boolean esActualizacion) {
		boolean estaClienteCondicionRepetido = clienteCondicionRepetido(clienteIf, subtipoOrdenIf, formaPagoIf);
		
		if (estaClienteCondicionRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Condición de cliente ya se encuentra agregada!!!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setClienteCondicionSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				clienteCondicionIf = (ClienteCondicionIf) getClientesCondicionesVector().get(getClienteCondicionSeleccionada());
				clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteCondicionIf.getClienteId());
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				getCmbFormaPago().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFormaPago(),clienteCondicionIf.getFormapagoId()));
				getCmbFormaPago().repaint();
				subtipoOrdenIf = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(clienteCondicionIf.getSubtipoordenId());
				tipoOrdenIf = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subtipoOrdenIf.getTipoordenId());
				getCmbTipoOrden().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(),tipoOrdenIf.getId()));
				getCmbTipoOrden().repaint();
				getCmbSubtipoOrden().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSubtipoOrden(),clienteCondicionIf.getSubtipoordenId()));
				getCmbSubtipoOrden().repaint();
				getCmbFechaInicial().setDate(clienteCondicionIf.getFechaini());
				getCmbFechaFinal().setDate(clienteCondicionIf.getFechafin());
				getTxtObservaciones().setText(clienteCondicionIf.getObservaciones());
				showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se produjo un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public Vector getClientesCondicionesVector() {
		return clientesCondicionesVector;
	}

	public void setClientesCondicionesVector(Vector clientesCondicionesVec) {
		clientesCondicionesVector = clientesCondicionesVec;
	}

	public int getClienteCondicionSeleccionada() {
		return clienteCondicionSeleccionada;
	}

	public void setClienteCondicionSeleccionada(int selectedClienteCondicion) {
		clienteCondicionSeleccionada = selectedClienteCondicion;
	}

	public ClienteCondicionIf getClienteCondicionActualizadaIf() {
		return clienteCondicionIf;
	}

	public void setClienteCondicionActualizadaIf(ClienteCondicionIf clienteCondicionIf) {
		this.clienteCondicionIf = clienteCondicionIf;
	}
}
