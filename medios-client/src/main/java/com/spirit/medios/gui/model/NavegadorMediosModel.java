package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.OrdenMedioData;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.gui.panel.JPNavegadorMedios;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;
import com.spirit.util.tree.BrowserEvent;
import com.spirit.util.tree.BrowserListener;
import com.spirit.util.tree.TreeNode;

public class NavegadorMediosModel extends JPNavegadorMedios {

	private static final long serialVersionUID = -8916530406951682932L;

	protected TipoProveedorIf tipoProveedorIf;
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private CorporacionCriteria corporacionCriteria;
	java.util.Date fechaInicialMes = new java.util.Date();
	java.util.Date fechaFinalMes = new java.util.Date();
	java.util.Date fechaCreacion = new java.util.Date();
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
	private static Date fechaInicio;
	private static Date fechaFin;
	private CorporacionIf porCorporacionIf;
	private ClienteIf porClienteIf;
	Vector OrdenMedioDetalleColeccion = new Vector();
	private boolean hayNodo = true;
	int frecuencias = 0;
	Map proveedoresMap = new HashMap();
	Map clientesOficinasMap = new HashMap();

	private boolean existeNodo = false;
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	private static final String NOMBRE_ESTADO_ENVIADO = "ENVIADO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String CLIENTE_OFICINA_LOCAL = "LOCAL";
	private static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO.substring(0, 1);
	private static final String ESTADO_ENVIADO = NOMBRE_ESTADO_ENVIADO.substring(0, 1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	private static final String CODIGO_TIPO_CLIENTE = "CL";

	public NavegadorMediosModel() {
		showSaveMode();
		loadCombos();
		initListeners();
		new HotKeyComponent(this);
	}

	private void loadCombos() {
		try {
			SpiritComboBoxModel cmbModelPorTipoProveedor = new SpiritComboBoxModel((ArrayList) 
					SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaIdAndClase(Parametros.getIdEmpresa(),"M"));
			getCmbPorTipoProveedor().setModel(cmbModelPorTipoProveedor);
			getCmbPorTipoProveedor().addActionListener(oActionListenerCmbTipoProveedor);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getCbPorCliente().setSelected(false);
		getCbPorProveedor().setSelected(false);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getBtnBuscar().setEnabled(true);
		getCmbPorFechaInicio().setEditable(false);
		getCmbPorFechaFin().setEditable(false);
		loadDateComboBoxes();
	}

	public void clean() {
		porCorporacionIf = null;
		porClienteIf = null;
		cleanPanelCliente();
		cleanPanelProveedor();
		cleanPanelOrdenMedios();
	}

	private void cleanPanelOrdenMedios() {
		getTxtCodigoOrden().setText("");
		getTxtFechaInicioOrden().setText("");
		getTxtFechaFinOrden().setText("");
		getTxtProveedorOrden().setText("");
		getTxtCorporacionOrden().setText("");
		getTxtClienteOrden().setText("");
		getTxtOficinaOrden().setText("");
		getTxtPlanMedioOrden().setText("");
		getTxtEstadoOrden().setText("");
		getTxtValorOrden().setText("");
	}

	private void cleanPanelProveedor() {
		getTxtCodigoClienteOficina().setText("");
		getTxtEstadoClienteOficina().setText("");
		getTxtDireccionClienteOficina().setText("");
		getTxtTelefonoClienteOficina().setText("");
		getTxtEmailClienteOficina().setText(CLIENTE_OFICINA_LOCAL);
		getTxtFaxClienteOficina().setText("");
		getTxtFechaCreacionClienteOficina().setText("");
		getTxtMontoCreditoClienteOficina().setText("");
		getTxtCalificacionClienteOficina().setText("");
		getTxtMontoGarantiaClienteOficina().setText("");
		getTxtObservacionClienteOficina().setText("");
		getTxtDescripcionClienteOficina().setText("");
	}

	private void cleanPanelCliente() {
		getTxtTipoIdentificacionCliente().setText("");
		getTxtIdentificacionCliente().setText("");
		getTxtNombreLegalCliente().setText("");
		getTxtRazonSocialCliente().setText("");
		getTxtRepresentanteCliente().setText("");
		getTxtCorporacionCliente().setText("");
		getTxtFechaCreacionCliente().setText("");
		getTxtEstadoCliente().setText("");
		getTxtTipoCliente().setText("");
		getTxtTipoNegocioCliente().setText("");
		getTxtObservacionesCliente().setText("");
		getTxtCtaContableCliente().setText("");
	}

	private void loadDateComboBoxes() {
		Calendar calendarInicio = new GregorianCalendar();
		Calendar calendarFin = new GregorianCalendar();

		Calendar now = Calendar.getInstance();

		int diaSemana = now.get(Calendar.DAY_OF_WEEK);

		switch (diaSemana) {
		case 1:
			fechaInicialMes.setDate(now.getTime().getDate() + 1);
			break;
		case 2:
			fechaInicialMes.setDate(now.getTime().getDate());
			break;
		case 3:
			fechaInicialMes.setDate(now.getTime().getDate() - 1);
			break;
		case 4:
			fechaInicialMes.setDate(now.getTime().getDate() - 2);
			break;
		case 5:
			fechaInicialMes.setDate(now.getTime().getDate() - 3);
			break;
		case 6:
			fechaInicialMes.setDate(now.getTime().getDate() - 4);
			break;
		case 7:
			fechaInicialMes.setDate(now.getTime().getDate() - 5);
			break;
		}

		fechaInicialMes.setMonth(now.getTime().getMonth());
		fechaInicialMes.setYear(now.getTime().getYear());

		fechaFinalMes.setDate(fechaInicialMes.getDate() + 6);
		fechaFinalMes.setMonth(now.getTime().getMonth());
		fechaFinalMes.setYear(now.getTime().getYear());

		calendarInicio.setTime(fechaInicialMes);
		calendarFin.setTime(fechaFinalMes);

		// seteo las variables globales
		fechaInicio = fechaInicialMes;
		fechaFin = fechaFinalMes;

		// Seteo formato de combos de fecha
		getCmbPorFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbPorFechaFin().setFormat(Utilitarios.setFechaUppercase());

		getCmbPorFechaInicio().setCalendar(calendarInicio);
		getCmbPorFechaFin().setCalendar(calendarFin);
	}

	public void report() {
		// TODO Auto-generated method stub

	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	// Action Listener del combo de Tipo Proveedor
	ActionListener oActionListenerCmbTipoProveedor = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				tipoProveedorIf = (TipoProveedorIf) getCmbPorTipoProveedor()
						.getModel().getSelectedItem();
				// Cada vez que escojo un tipo de proveedor diferente cargo el
				// combo de proveedores
				getCmbPorProveedor().removeAllItems();
				SpiritComboBoxModel cmbModelPorProveedor = new SpiritComboBoxModel(
						(ArrayList) SessionServiceLocator.getClienteOficinaSessionService()
								.findClienteOficinaByTipoProveedorIdAndEmpresaId(
										tipoProveedorIf.getId(),
										Parametros.getIdEmpresa()));
				getCmbPorProveedor().setModel(cmbModelPorProveedor);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	};

	public void initListeners() {

		this.getCmbPorFechaInicio().addActionListener(oActionListenerCmbFechaInicio);
		this.getCmbPorFechaFin().addActionListener(oActionListenerCmbFechaFin);

		// Manejo los eventos de Buscar Ordenes
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				// Mando a vaciar el arbol
				getTreeOrdenMedios().removeRootNodeChildren();
				// Vacio el mapa de nodos raices (CLientes)
				proveedoresMap.clear();
				// Mando a buscar por todas las campañas
				if (getCbTodasOrdenes().isSelected() && getCbPorTipoProveedor().isSelected() && (getCmbPorTipoProveedor().getSelectedIndex() >= 0))
					insertarNodosProveedores();
				else if (getCbPorTipoProveedor().isSelected() && (getCmbPorTipoProveedor().getSelectedIndex() >= 0))
					insertarNodosProveedores();
				// Mando a bsucar todas las campanas por el proveedor
				else if (getCbPorProveedor().isSelected() && !getCbPorCliente().isSelected() && getCmbPorProveedor().getSelectedItem() != null) {
					insertarNodosProveedoresByProveedor();
				}
				// Mando a buscar las campañas solo por cliente y que el cliente
				// no sea en blanco
				else if (getCbPorCliente().isSelected()	&& !"".equals(getTxtPorCliente().getText())	&& getCbPorTipoProveedor().isSelected() && (getCmbPorTipoProveedor().getSelectedIndex() >= 0)) {
					// Añado los nodos campana del cliente seleccionado
					insertarNodosProveedoresByCliente();
				}
				// Mando a buscar las campañas solo por cliente y que el cliente
				// no sea en blanco
				else if (getCbPorCliente().isSelected()	&& !"".equals(getTxtPorCliente().getText())	&& getCbPorProveedor().isSelected()	&& getCmbPorProveedor().getSelectedItem() != null) {
					// Añado los nodos campana del cliente seleccionado
					insertarNodosProveedoresByClienteAndProveedor();
				} else {
					if (!getCbPorTipoProveedor().isSelected() || (getCbPorTipoProveedor().isSelected() && (getCmbPorTipoProveedor().getSelectedIndex() == -1)))
						SpiritAlert.createAlert("Debe escoger el Tipo de Proveedor !!",	SpiritAlert.WARNING);
					else
						SpiritAlert.createAlert("Criterio de búsqueda inválido !!",	SpiritAlert.WARNING);
				}

				// Mando a repintar el arbol
				getTreeOrdenMedios().repaint();
			}
		});

		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
									corporacionCriteria,
									JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					porCorporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtPorCorporacion().setText(porCorporacionIf.getNombre());
					getTxtPorCliente().setText("");
				}
			}
		});

		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;

				if (porCorporacionIf != null)
					idCorporacion = porCorporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
									clienteCriteria,
									JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					porClienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtPorCliente().setText(porClienteIf.getNombreLegal());
					if (porCorporacionIf == null) {
						try {
							porCorporacionIf = SessionServiceLocator.getCorporacionSessionService()
									.getCorporacion(porClienteIf.getCorporacionId());
							getTxtPorCorporacion().setText(porCorporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
				}
			}
		});

		// Agrego el listener para cada checkbox
		getCbTodasOrdenes().addActionListener(new CheckBoxTodasOrdenes());
		getCbPorCliente().addActionListener(new CheckBoxPorCliente());
		getCbPorProveedor().addActionListener(new CheckBoxPorProveedor());
		getTreeOrdenMedios().addBrowserListener(oBrowserListener);

	}

	ActionListener oActionListenerCmbFechaInicio = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent eventoInicio) {

			fechaInicio = (Date) ((DateComboBox) eventoInicio.getSource())
					.getDate();

			Calendar calendarInicio = new GregorianCalendar();

			Calendar calendarFin = new GregorianCalendar();

			if ((fechaInicio.getDate() + 6) > Utilitarios.getLastDayOfMonth(
					fechaInicio.getMonth(), fechaInicio.getYear())) {
				fechaFinalMes.setDate(Utilitarios.getLastDayOfMonth(fechaInicio
						.getMonth(), fechaInicio.getYear()));
				fechaFinalMes.setMonth(fechaInicio.getMonth());
				fechaFinalMes.setYear(fechaInicio.getYear());
			} else {
				fechaFinalMes.setDate(fechaInicio.getDate() + 6);
				fechaFinalMes.setMonth(fechaInicio.getMonth());
				fechaFinalMes.setYear(fechaInicio.getYear());
			}

			calendarInicio.setTime(fechaInicialMes);
			calendarFin.setTime(fechaFinalMes);

			getCmbPorFechaFin().setCalendar(calendarFin);
			getCmbPorFechaFin().getDateModel().setMaxDate(calendarFin);
		}
	};

	ActionListener oActionListenerCmbFechaFin = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {

			fechaFin = (Date) ((DateComboBox) eventoInicio.getSource())
					.getDate();
			if (fechaInicio.after(fechaFin)) {
				SpiritAlert.createAlert("Por favor seleccione una fecha de fin válida!", SpiritAlert.INFORMATION);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(fechaFinalMes);
				fechaFin = fechaFinalMes;
				getCmbPorFechaFin().setCalendar(calendar);
			}
		}
	};

	private class CheckBoxTodasOrdenes implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Veo si el checkbox esta seleccionado
			if (getCbTodasOrdenes().isSelected()) {
				getCbPorCliente().setSelected(false);
				getCbPorProveedor().setSelected(false);
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getTxtPorCorporacion().setText("");
				getTxtPorCliente().setText("");
				getCmbPorProveedor().setEnabled(false);
				getCmbPorProveedor().setSelectedItem(null);
			}
		}
	}

	private class CheckBoxPorCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Veo si el checkbox esta seleccionado
			if (getCbPorCliente().isSelected()) {
				getCbTodasOrdenes().setSelected(false);
				getBtnBuscarCorporacion().setEnabled(true);
				getBtnBuscarCliente().setEnabled(true);
				getCmbPorProveedor().setEnabled(false);
			} else {
				porCorporacionIf = null;
				porClienteIf = null;
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getTxtPorCorporacion().setText("");
				getTxtPorCliente().setText("");
				getCmbPorProveedor().setEnabled(false);
			}
		}
	}

	private class CheckBoxPorProveedor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Veo si el checkbox esta seleccionado
			if (getCbPorProveedor().isSelected()) {
				getCbTodasOrdenes().setSelected(false);
				getBtnBuscarCorporacion().setEnabled(false);
				getCmbPorProveedor().setEnabled(true);
				getBtnBuscarCliente().setEnabled(false);
			} else {
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getTxtPorCorporacion().setText("");
				getTxtPorCliente().setText("");
				getCmbPorProveedor().setEnabled(false);
			}
		}
	}

	public void insertarNodosProveedores() {
		int contador = 0;

		try {

			/*Collection proveedorCollection = getClienteOficinaSessionService()
					.findClienteOficinaByTipoProveedorIdAndEmpresaId(
							((TipoProveedorIf) this.getCmbPorTipoProveedor()
									.getSelectedItem()).getId(),
							Parametros.getIdEmpresa());*/
			Collection proveedorCollection = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator itProveedores = proveedorCollection.iterator();

			if (!proveedorCollection.isEmpty()) {
				hayNodo = true;
				// Recorro todas los proveedores encontrados en la base
				while (itProveedores.hasNext()) {

					ClienteOficinaIf proveedorIf = (ClienteOficinaIf) itProveedores
							.next();
					/*Collection clienteOficinasByProveedorCollection = OrdenMedioModel
							.getOrdenMedioSessionService()
							.findOrdenMedioByProveedorId(proveedorIf.getId());*/
					Collection clienteOficinasByProveedorCollection = SessionServiceLocator
					.getOrdenMedioSessionService().getOrdenMedioList();
					Iterator itClienteOficinasByProveedorCollection = clienteOficinasByProveedorCollection
							.iterator();
					Object nodoProveedor = null;
					// Sino hay registros al hacer la busqueda muestro un
					// mensaje

					if (itClienteOficinasByProveedorCollection.hasNext()) {

						String labelNodoProveedor = ((ClienteIf) SessionServiceLocator.getClienteSessionService()
								.findClienteByClienteOficinaId(proveedorIf.getId()).iterator().next())
								.getNombreLegal();
						// Creo el nodo proveedor
						nodoProveedor = getTreeOrdenMedios().insertNode(
								getRootNodeTreeOrdenMedios(),
								labelNodoProveedor,
								"images/icons/entidad/Cliente.png",
								"images/icons/entidad/Cliente.png", 2,
								proveedorIf);
					}

					if (!clienteOficinasByProveedorCollection.isEmpty()) {
						contador++;

						// Recorro todas los clientes oficinas encontradas en la
						// base
						while (itClienteOficinasByProveedorCollection.hasNext()) {
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf) itClienteOficinasByProveedorCollection
									.next();
							ClienteOficinaIf clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService()
									.getClienteOficina(ordenMedioIf.getClienteOficinaId());

							if (clientesOficinasMap.get(clienteOficinaIf
									.getId()) == null) {
								String labelNodoClienteOficina = clienteOficinaIf
										.getCodigo()
										+ " - "
										+ clienteOficinaIf.getDescripcion()
										+ " ("
										+ clienteOficinaIf.getEstado()
										+ ")";
								// Creo el nodo campana
								Object nodoClienteOficina = getTreeOrdenMedios()
										.insertNode(
												nodoProveedor,
												labelNodoClienteOficina,
												"images/icons/entidad/Campana.png",
												"images/icons/entidad/Campana.png",
												2, clienteOficinaIf);
								// añado al mapa

								clientesOficinasMap.put(clienteOficinaIf
										.getId(), nodoClienteOficina);
								// Añado los nodos hijos de este nodo
								insertarNodosOrdenMedio(nodoClienteOficina,
										clienteOficinaIf.getId(),
										nodoProveedor, proveedorIf.getId());
							}
						}
					} else if (contador == 0)
						SpiritAlert
								.createAlert(
										"No se encontraron registros de acuerdo al criterio de búsqueda!",
										SpiritAlert.WARNING);

					// despues de cada iteración seteo a null el mapa de
					// oficinas paravalidar en la próxima iteracion
					clientesOficinasMap = new HashMap();
				}
				if(!hayNodo){
					SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda!", SpiritAlert.WARNING);
				}
			} else
				SpiritAlert
						.createAlert(
								"No se encontraron registros de acuerdo al criterio de búsqueda!",
								SpiritAlert.WARNING);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void insertarNodosProveedoresByProveedor() {
		int contador = 0;

		try {

			Collection proveedorCollection = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaById(
							((ClienteOficinaIf) this.getCmbPorProveedor()
									.getSelectedItem()).getId());
			Iterator itProveedores = proveedorCollection.iterator();

			if (!proveedorCollection.isEmpty()) {
				hayNodo = true;
				// Recorro todas los proveedores encontrados en la base
				while (itProveedores.hasNext()) {

					ClienteOficinaIf proveedorIf = (ClienteOficinaIf) itProveedores
							.next();
					Collection clienteOficinasByProveedorCollection = SessionServiceLocator
							.getOrdenMedioSessionService()
							.findOrdenMedioByProveedorId(proveedorIf.getId());
					Iterator itClienteOficinasByProveedorCollection = clienteOficinasByProveedorCollection
							.iterator();
					Object nodoProveedor = null;
					// Sino hay registros al hacer la busqueda muestro un
					// mensaje
					if (itClienteOficinasByProveedorCollection.hasNext()) {

						String labelNodoProveedor = ((ClienteIf) SessionServiceLocator.getClienteSessionService()
								.findClienteByClienteOficinaId(proveedorIf.getId()).iterator().next())
								.getNombreLegal();
						// Creo el nodo proveedor
						nodoProveedor = getTreeOrdenMedios().insertNode(
								getRootNodeTreeOrdenMedios(),
								labelNodoProveedor,
								"images/icons/entidad/Cliente.png",
								"images/icons/entidad/Cliente.png", 2,
								proveedorIf);
					}

					if (!clienteOficinasByProveedorCollection.isEmpty()) {
						contador++;
						
						// Recorro todas los clientes oficinas encontradas en la
						// base
						while (itClienteOficinasByProveedorCollection.hasNext()) {
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf) itClienteOficinasByProveedorCollection
									.next();
							ClienteOficinaIf clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService()
									.getClienteOficina(ordenMedioIf.getClienteOficinaId());

							if (clientesOficinasMap.get(clienteOficinaIf
									.getId()) == null) {
								String labelNodoClienteOficina = clienteOficinaIf
										.getCodigo()
										+ " - "
										+ clienteOficinaIf.getDescripcion()
										+ " ("
										+ clienteOficinaIf.getEstado()
										+ ")";
								// Creo el nodo campana
								Object nodoClienteOficina = getTreeOrdenMedios()
										.insertNode(
												nodoProveedor,
												labelNodoClienteOficina,
												"images/icons/entidad/Campana.png",
												"images/icons/entidad/Campana.png",
												2, clienteOficinaIf);
								// añado al mapa
								clientesOficinasMap.put(clienteOficinaIf
										.getId(), nodoClienteOficina);

								// Añado los nodos hijos de este nodo
								insertarNodosOrdenMedio(nodoClienteOficina,
										clienteOficinaIf.getId(),
										nodoProveedor, proveedorIf.getId());
							}
						}
						
					} else if (contador == 0)
						SpiritAlert
								.createAlert(
										"No se encontraron registros de acuerdo al criterio de búsqueda!",
										SpiritAlert.WARNING);

					// despues de cada iteración seteo a null el mapa de
					// oficinas paravalidar en la próxima iteracion
					clientesOficinasMap = new HashMap();
				}
				if(!hayNodo){
					SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda!", SpiritAlert.WARNING);
				}
			} else
				SpiritAlert
						.createAlert(
								"No se encontraron registros de acuerdo al criterio de búsqueda!",
								SpiritAlert.WARNING);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void insertarNodosProveedoresByCliente() {
		int contador = 0;

		try {

			Collection proveedorCollection = SessionServiceLocator.getClienteOficinaSessionService()
					.findClienteOficinaByTipoProveedorIdAndEmpresaId(
							((TipoProveedorIf) this.getCmbPorTipoProveedor().getSelectedItem()).getId(),
							Parametros.getIdEmpresa());
			Iterator itProveedores = proveedorCollection.iterator();

			String fechaInicial = "", fechaFinal = "";

			DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			fechaInicial = dateFormatter.format(getCmbPorFechaInicio().getDate());
			fechaFinal = dateFormatter.format(getCmbPorFechaFin().getDate());

			if (!proveedorCollection.isEmpty()) {
				hayNodo = true;
				// Recorro todas los proveedores encontrados en la base
				while (itProveedores.hasNext()) {
					ClienteOficinaIf proveedorIf = (ClienteOficinaIf) itProveedores.next();
					Map parameterMap = new HashMap();
					parameterMap.put("proveedorId", proveedorIf.getId());
					Collection clienteOficinasByProveedorCollection = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByFechasAndByClienteId(parameterMap, fechaInicial, fechaFinal, porClienteIf.getId(), Parametros.getIdEmpresa());;
					Iterator itClienteOficinasByProveedorCollection = clienteOficinasByProveedorCollection.iterator();
					Object nodoProveedor = null;
					// Sino hay registros al hacer la busqueda muestro un
					// mensaje
					if (itClienteOficinasByProveedorCollection.hasNext()) {

						String labelNodoProveedor = ((ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(proveedorIf.getId()).iterator().next()).getNombreLegal();
						// Creo el nodo proveedor
						nodoProveedor = getTreeOrdenMedios().insertNode(getRootNodeTreeOrdenMedios(),labelNodoProveedor,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png", 2,proveedorIf);

					}

					if (!clienteOficinasByProveedorCollection.isEmpty()) {
						contador++;
						
						// Recorro todas los clientes oficinas encontradas en la
						// base
						while (itClienteOficinasByProveedorCollection.hasNext()) {
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf) itClienteOficinasByProveedorCollection
									.next();
							ClienteOficinaIf clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService()
									.getClienteOficina(ordenMedioIf.getClienteOficinaId());

							if (clientesOficinasMap.get(clienteOficinaIf
									.getId()) == null) {
								String labelNodoClienteOficina = clienteOficinaIf
										.getCodigo()
										+ " - "
										+ clienteOficinaIf.getDescripcion()
										+ " ("
										+ clienteOficinaIf.getEstado()
										+ ")";
								// Creo el nodo campana
								Object nodoClienteOficina = getTreeOrdenMedios()
										.insertNode(
												nodoProveedor,
												labelNodoClienteOficina,
												"images/icons/entidad/Campana.png",
												"images/icons/entidad/Campana.png",
												2, clienteOficinaIf);
								// añado al mapa
								clientesOficinasMap.put(clienteOficinaIf
										.getId(), nodoClienteOficina);
								// Añado los nodos hijos de este nodo
								insertarNodosOrdenMedio(nodoClienteOficina,
										clienteOficinaIf.getId(),
										nodoProveedor, proveedorIf.getId());
							}
						}
						
					} else if (contador == 0)
						SpiritAlert
								.createAlert(
										"No se encontraron registros de acuerdo al criterio de búsqueda!",
										SpiritAlert.WARNING);

					// despues de cada iteración seteo a null el mapa de
					// oficinas para validar en la próxima iteracion
					clientesOficinasMap = new HashMap();
				}
				if(!hayNodo){
					SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda!", SpiritAlert.WARNING);
				}
			} else
				SpiritAlert
						.createAlert(
								"No se encontraron registros de acuerdo al criterio de búsqueda!",
								SpiritAlert.WARNING);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void insertarNodosProveedoresByClienteAndProveedor() {

		try {

			String fechaInicial = "", fechaFinal = "";

			DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			fechaInicial = dateFormatter.format(getCmbPorFechaInicio().getDate());
			fechaFinal = dateFormatter.format(getCmbPorFechaFin().getDate());

			ClienteOficinaIf proveedorIf = ((ClienteOficinaIf) this.getCmbPorProveedor().getSelectedItem());
			Map parameterMap = new HashMap();
			parameterMap.put("proveedorId", proveedorIf.getId());
			Collection clienteOficinasByProveedorCollection = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByFechasAndByClienteId(parameterMap, fechaInicial, fechaFinal, porClienteIf.getId(), Parametros.getIdEmpresa());
			Iterator itClienteOficinasByProveedorCollection = clienteOficinasByProveedorCollection.iterator();
			Object nodoProveedor = null;

			if (!clienteOficinasByProveedorCollection.isEmpty()) {

				// Sino hay registros al hacer la busqueda muestro un mensaje
				if (itClienteOficinasByProveedorCollection.hasNext()) {

					if (proveedoresMap.get(proveedorIf.getId()) != null) {
						// Obtengo el nodo campana que se encuentra guardado en
						// el mapa
						nodoProveedor = (Object) proveedoresMap.get(proveedorIf
								.getId());
					}
					// Sino existe el nodo cliente lo creo para luego insertar
					// el nodo campana
					else {
						String labelNodoProveedor = ((ClienteIf) SessionServiceLocator.getClienteSessionService()
								.findClienteByClienteOficinaId(proveedorIf.getId()).iterator().next())
								.getNombreLegal();
						// Creo el nodo proveedor
						nodoProveedor = getTreeOrdenMedios().insertNode(
								getRootNodeTreeOrdenMedios(),
								labelNodoProveedor,
								"images/icons/entidad/Cliente.png",
								"images/icons/entidad/Cliente.png", 2,
								proveedorIf);
						proveedoresMap.put(proveedorIf.getId(), nodoProveedor);
					}
				}
				
				hayNodo = true;
				
				// Recorro todas los clientes oficinas encontradas en la base
				while (itClienteOficinasByProveedorCollection.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) itClienteOficinasByProveedorCollection
							.next();
					ClienteOficinaIf clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService()
							.getClienteOficina(ordenMedioIf.getClienteOficinaId());

					if (clientesOficinasMap.get(clienteOficinaIf.getId()) == null) {
						String labelNodoClienteOficina = clienteOficinaIf
								.getCodigo()
								+ " - "
								+ clienteOficinaIf.getDescripcion()
								+ " (" + clienteOficinaIf.getEstado() + ")";
						// Creo el nodo campana
						Object nodoClienteOficina = getTreeOrdenMedios()
								.insertNode(nodoProveedor,
										labelNodoClienteOficina,
										"images/icons/entidad/Campana.png",
										"images/icons/entidad/Campana.png", 2,
										clienteOficinaIf);

						// añado al mapa
						clientesOficinasMap.put(clienteOficinaIf.getId(),
								nodoClienteOficina);
						// Añado los nodos hijos de este nodo
						insertarNodosOrdenMedio(nodoClienteOficina,
								clienteOficinaIf.getId(), nodoProveedor,
								proveedorIf.getId());
					}
				}
				if(!hayNodo){
					SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda!", SpiritAlert.WARNING);
				}
				// despues de cada iteración seteo a null el mapa de oficinas
				// paravalidar en la próxima iteracion
				clientesOficinasMap = new HashMap();
			} else
				SpiritAlert
						.createAlert(
								"No se encontraron registros de acuerdo al criterio de búsqueda!",
								SpiritAlert.WARNING);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean insertarNodosOrdenMedio(Object nodoClienteOficina, Long idClienteOficina, Object nodoProveedor, Long idProveedor) {
		setExisteNodo(false);
		try {

			String fechaInicial = "", fechaFinal = "";

			DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			fechaInicial = dateFormatter.format(getCmbPorFechaInicio().getDate());
			fechaFinal = dateFormatter.format(getCmbPorFechaFin().getDate());

			setExisteNodo(false);
			Map parameterMap = new HashMap();
			parameterMap.put("clienteoficinaId", idClienteOficina);
			parameterMap.put("proveedorId", idProveedor);
			Collection ordenMedioByClienteOficinaCollection = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByFechas(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());
			Iterator itOrdenMedioByClienteOficinaCollection = ordenMedioByClienteOficinaCollection.iterator();

			// si no encuentra registros en la base boorro todo el nodo que no
			// contiene ordenes
			if (ordenMedioByClienteOficinaCollection.size() == 0) {
				// Mando a vaciar el arbol
				getTreeOrdenMedios().removeRootNodeChildren();
			}

			if (!ordenMedioByClienteOficinaCollection.isEmpty()) {
				// Recorro todas las ordenes de medio encontradas de la base
				while (itOrdenMedioByClienteOficinaCollection.hasNext()) {
					setExisteNodo(true);
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) itOrdenMedioByClienteOficinaCollection
							.next();
					// Extraiogo el texto a mostrar en el Nodo
					String labelNodoOrdenMedio = ordenMedioIf.getCodigo()
							+ " (" + ordenMedioIf.getEstado() + ")";
					// Creo el nodo OrdenMedio
					Object nodoOrdenMedio = getTreeOrdenMedios().insertNode(
							nodoClienteOficina, labelNodoOrdenMedio,
							"images/icons/entidad/OrdenTrabajo.png",
							"images/icons/entidad/OrdenTrabajo.png", 2,
							ordenMedioIf);

				}
			} 	else if (!isExisteNodo()){
					hayNodo = false;
					return hayNodo;
				}	

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		hayNodo = true;
		return hayNodo;
	}

	// Clase que controla el evento de seleccion del arbol
	BrowserListener oBrowserListener = new BrowserListener() {
		public void Select(BrowserEvent e) {

			// Vacio los textfieldsde todos los paneles
			clean();
			try {
				// Extraigo el nodo seleccionado
				TreeNode nodoSeleccionado = (TreeNode) e.getSource();
				// Creo instancias de todos los tipos de nodos que hay en el
				// arbol}
				ClienteOficinaIf clienteOficina = new ClienteOficinaData();
				OrdenMedioIf ordenMedio = new OrdenMedioData();
				ClienteOficinaIf proveedor = new ClienteOficinaData();

				// Veo si el nodo seleccionado es de Cliente
				if (clienteOficina.getClass().equals(
						nodoSeleccionado.getObject().getClass())) {
					// Activo el tab de Campaña
					getJideTabbedPane().setSelectedIndex(0);

					// Creo la instancia del nodo cliente seleccionado
					clienteOficina = (ClienteOficinaIf) nodoSeleccionado
							.getObject();

					ClienteIf cliente = SessionServiceLocator.getClienteSessionService()
							.getCliente(clienteOficina.getClienteId());

					// LLeno los campos de la campana seleccionada en el panel
					TipoIdentificacionIf tipoIdentificacionTemp = SessionServiceLocator.getTipoIdentificacionSessionService()
							.getTipoIdentificacion(cliente.getTipoidentificacionId());
					getTxtTipoIdentificacionCliente().setText(tipoIdentificacionTemp.getNombre());

					getTxtIdentificacionCliente().setText(cliente.getIdentificacion());
					getTxtNombreLegalCliente().setText(cliente.getNombreLegal());
					getTxtRazonSocialCliente().setText(cliente.getRazonSocial());
					getTxtRepresentanteCliente().setText(cliente.getRepresentante());

					CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(
									cliente.getCorporacionId());
					getTxtCorporacionCliente().setText(
							corporacionTemp.getNombre());

					getTxtFechaCreacionCliente().setText(
							Utilitarios.getFechaUppercase(cliente
									.getFechaCreacion()));

					if (ESTADO_ACTIVO.equals(cliente.getEstado().toString()))
						getTxtEstadoCliente().setText(NOMBRE_ESTADO_ACTIVO);
					else if (ESTADO_INACTIVO.equals(cliente.getEstado()
							.toString()))
						getTxtEstadoCliente().setText(NOMBRE_ESTADO_INACTIVO);

					TipoClienteIf tipoClienteTemp = SessionServiceLocator.getTipoClienteSessionService().getTipoCliente(
									cliente.getTipoclienteId());
					getTxtTipoCliente().setText(tipoClienteTemp.getNombre());

					TipoNegocioIf tipoNegocioTemp = SessionServiceLocator.getTipoNegocioSessionService().getTipoNegocio(
									cliente.getTiponegocioId());
					getTxtTipoNegocioCliente().setText(
							tipoNegocioTemp.getNombre());

					getTxtObservacionesCliente().setText(
							cliente.getObservacion());

					if (cliente.getCuentaId() != null) {
						CuentaIf cuentaTemp = SessionServiceLocator.getCuentaSessionService().getCuenta(
										cliente.getCuentaId());
						getTxtCtaContableCliente().setText(
								cuentaTemp.getCodigo() + " - "
										+ cuentaTemp.getNombre());
					}

				}

				if (proveedor.getClass().equals(
						nodoSeleccionado.getObject().getClass())) {
					// Activo el tab de Campaña
					getJideTabbedPane().setSelectedIndex(1);

					// Creo la instancia del nodo cliente seleccionado
					proveedor = (ClienteOficinaIf) nodoSeleccionado
							.getObject();

					getTxtCodigoClienteOficina().setText(proveedor.getCodigo());
					getTxtDireccionClienteOficina().setText(
							proveedor.getDireccion());
					getTxtTelefonoClienteOficina().setText(
							proveedor.getTelefono());
					getTxtEmailClienteOficina().setText(proveedor.getEmail());
					getTxtFaxClienteOficina().setText(proveedor.getFax());

					getTxtFechaCreacionClienteOficina().setText(
							Utilitarios.getFechaUppercase(proveedor
									.getFechaCreacion()));
					getTxtMontoCreditoClienteOficina().setText(
							proveedor.getMontoCredito().toString());
					getTxtCalificacionClienteOficina().setText(
							proveedor.getCalificacion());
					getTxtMontoGarantiaClienteOficina().setText(
							proveedor.getMontoGarantia().toString());
					getTxtObservacionClienteOficina().setText(
							proveedor.getObservacion());
					getTxtDescripcionClienteOficina().setText(
							proveedor.getDescripcion());

					if (ESTADO_ACTIVO.equals(proveedor.getEstado().toString()))
						getTxtEstadoClienteOficina().setText(
								NOMBRE_ESTADO_ACTIVO);
					else if (ESTADO_INACTIVO.equals(proveedor.getEstado()
							.toString()))
						getTxtEstadoClienteOficina().setText(
								NOMBRE_ESTADO_INACTIVO);
				}

				if (ordenMedio.getClass().equals(
						nodoSeleccionado.getObject().getClass())) {
					// Activo el tab de Campaña
					getJideTabbedPane().setSelectedIndex(2);

					// Creo la instancia del nodo cliente seleccionado
					ordenMedio = (OrdenMedioIf) nodoSeleccionado.getObject();

					// LLeno los campos de la campana seleccionada en el panel
					ClienteOficinaIf proveedorTemp = SessionServiceLocator.getClienteOficinaSessionService()
							.getClienteOficina(ordenMedio.getProveedorId());
					ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService()
							.getClienteOficina(ordenMedio.getClienteOficinaId());
					ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(
									clienteOficinaTemp.getClienteId());
					CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(
									clienteTemp.getCorporacionId());
					PlanMedioIf planMediosTemp = SessionServiceLocator
							.getPlanMedioSessionService().getPlanMedio(
									ordenMedio.getPlanMedioId());

					getTxtCodigoOrden().setText(ordenMedio.getCodigo());

					getTxtFechaInicioOrden().setText("no existe");
					getTxtFechaFinOrden().setText("no existe");
					getTxtProveedorOrden().setText(
							((ClienteIf) SessionServiceLocator.getClienteSessionService()
									.findClienteByClienteOficinaId(proveedorTemp.getId()).iterator()
									.next()).getNombreLegal());
					getTxtCorporacionOrden().setText(
							corporacionTemp.getNombre());
					getTxtClienteOrden().setText(clienteTemp.getNombreLegal());
					getTxtOficinaOrden().setText(
							clienteOficinaTemp.getDescripcion());
					getTxtPlanMedioOrden().setText(
							"PLAN MEDIO - " + planMediosTemp.getCodigo());

					if (ESTADO_PENDIENTE.equals(ordenMedio.getEstado()))
						getTxtEstadoOrden().setText(NOMBRE_ESTADO_PENDIENTE);
					else if (ESTADO_ENVIADO.equals(ordenMedio.getEstado()))
						getTxtEstadoOrden().setText(NOMBRE_ESTADO_ENVIADO);
					else if (ESTADO_FACTURADO.equals(ordenMedio.getEstado()))
						getTxtEstadoOrden().setText(NOMBRE_ESTADO_FACTURADO);

					//getValor
					if (ordenMedio.getValorTotal() != null)
						getTxtValorOrden().setText(
								ordenMedio.getValorTotal().toString());
					else
						getTxtValorOrden().setText("0.0");
				}
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
		}

		public void Deselect(BrowserEvent e) {
		}

		public void Activate(BrowserEvent e) {
		}

		public void Deactivate(BrowserEvent e) {
		}
	};

	public boolean isExisteNodo() {
		return existeNodo;
	}

	public void setExisteNodo(boolean existeNodo) {
		this.existeNodo = existeNodo;
	}
	
}
