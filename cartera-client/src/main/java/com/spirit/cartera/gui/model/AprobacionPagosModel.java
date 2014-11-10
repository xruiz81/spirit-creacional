package com.spirit.cartera.gui.model;

import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraData;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CarteraPagoIf;
import com.spirit.cartera.entity.CuentasPorPagarEJB;
import com.spirit.cartera.gui.panel.JDObservacionesPagos;
import com.spirit.cartera.gui.panel.JPAprobacionPagos;
import com.spirit.cartera.handler.EstadoCarteraPago;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class AprobacionPagosModel extends JPAprobacionPagos {

	private static final long serialVersionUID = -8259574627788937057L;
	
	private static final int MAX_LONGITUD_TXTCELL = 100;
	private static final String TIPO_PROVEEDOR = "P";
	private static final String CODIGO_COMPROBANTE_EGRESO = "CEG";
	private static final String APROBADO_SI = "S";
	private static final String ESTADO_NORMAL = "N";
	private static final String CARTERA_NO = "N";
	private static final String CODIGO_PUBLICIDAD = "PUBL";
	private static final int MAX_LONGITUD_OBSERVACION = 300;

	private Vector<CarteraIf> carteraColeccion = new Vector<CarteraIf>();
	private DefaultTableModel tableModel;
	private Vector<Boolean> aprobadosColeccion = new Vector<Boolean>();
	private Vector<Boolean> desaprobadosColeccion = new Vector<Boolean>();
	private Vector<BigDecimal> abonosColeccion = new Vector<BigDecimal>();
	private Vector<CarteraIf> carterasDesaprobadasColeccion = new Vector<CarteraIf>();
	private ClienteOficinaIf clienteOficina;
	private ClienteIf cliente;
	private CompraIf compra;
	//private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.###");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	//private NumberFormat formatoDecimal = NumberFormat.getInstance(Locale.getDefault());
	private List<CarteraDetalleIf> carteraPagoColeccion = new ArrayList<CarteraDetalleIf>();
	private List<CarteraDetalleIf> carteraDetalleAuxiliarColeccion = new ArrayList<CarteraDetalleIf>();
	private TipoDocumentoIf tipoDocumento;
	private BigDecimal totalCarteraPago;
	protected JDPopupFinderModel popupFinder;
	protected ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteOficinaIf proveedorIf;
	private Map<Long, ClienteIf> clientesMap;
	private Map<Long, ClienteOficinaIf> clienteOficinasMap;
	private Map comprasMap;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	private BigDecimal totalAprobado = new BigDecimal(0);
	
	
	public AprobacionPagosModel() {
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getCbFiltrarAprobados().setSelected(true);
		
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar");
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblAprobacionPagos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblAprobacionPagos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblAprobacionPagos().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);		
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblAprobacionPagos().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblAprobacionPagos().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblAprobacionPagos().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblAprobacionPagos().getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblAprobacionPagos().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
	}

	private void initListeners() {
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbProveedor().setSelected(false);
			}
		});
		getCbProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbTodos().setSelected(false);
			}
		});
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProveedor();
			}
		});
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbProveedor().isSelected()) {
					initVariablesAndMapas();
					clean();
					proveedorIf = null;
					getTxtProveedor().setText("");
					cargarTabla();
				} else if (proveedorIf != null) {
					initVariablesAndMapas();
					clean();
					cargarTabla();
				} else {
					SpiritAlert.createAlert("Debe buscar un proveedor!",
							SpiritAlert.INFORMATION);
				}
			}
		});
	}

	private void initVariablesAndMapas() {
		try {
			//tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(CODIGO_COMPROBANTE_EGRESO).iterator().next();
			tipoDocumento=null;
			Iterator itap = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(CODIGO_COMPROBANTE_EGRESO).iterator();			
			if(itap.hasNext())	tipoDocumento = (TipoDocumentoIf)itap.next();
				 	
			
			clientesMap = mapearClientes();
			clienteOficinasMap = mapearClienteOficinas();
			comprasMap = mapearCompras();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!",
					SpiritAlert.ERROR);
		}
	}

	public Map<Long, ClienteIf> mapearClientes() {
		Map<Long, ClienteIf> clientesMap = new HashMap<Long, ClienteIf>();

		try {
			Collection clientesColeccion = SessionServiceLocator.getClienteSessionService()
			.findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIterator = clientesColeccion.iterator();
			while (clientesIterator.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIterator.next();
				clientesMap.put(cliente.getId(), cliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!",
					SpiritAlert.ERROR);
		}

		return clientesMap;
	}

	public Map<Long, ClienteOficinaIf> mapearClienteOficinas() {
		Map<Long, ClienteOficinaIf> clienteOficinasMap = new HashMap<Long, ClienteOficinaIf>();

		try {
			Collection clienteOficinasColeccion = SessionServiceLocator.getClienteOficinaSessionService()
			.findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clienteOficinasIterator = clienteOficinasColeccion
			.iterator();
			while (clienteOficinasIterator.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clienteOficinasIterator
				.next();
				clienteOficinasMap.put(clienteOficina.getId(), clienteOficina);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!",
					SpiritAlert.ERROR);
		}

		return clienteOficinasMap;
	}

	public Map mapearCompras() {
		Map comprasMap = new HashMap();

		try {
			Collection comprasColeccion = SessionServiceLocator.getCompraSessionService()
			.findComprasPorPagarByEmpresaId(Parametros.getIdEmpresa(),
					false);
			Iterator comprasIterator = comprasColeccion.iterator();
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				comprasMap.put(compra.getId(), compra);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!",
					SpiritAlert.ERROR);
		}

		return comprasMap;
	}

	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";

		clienteOficinaCriteria = new ClienteOficinaCriteria(
				tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,
		"", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				clienteOficinaCriteria,
				JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null)
			setProveedor(popupFinder.getElementoSeleccionado());
	}

	private void setProveedor(Object proveedorObjeto) {
		clean();
		proveedorIf = (ClienteOficinaIf) proveedorObjeto;

		try {
			ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService()
			.getClienteOficina(proveedorIf.getId());
			ClienteIf proveedor = (ClienteIf) SessionServiceLocator.getClienteSessionService()
			.getCliente(clienteOficina.getClienteId());
			getTxtProveedor().setText(
					proveedor.getIdentificacion() + " - "
					+ proveedor.getNombreLegal() + " - "
					+ proveedorIf.getDescripcion());
			cargarTabla();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(
					"Se ha producido un error al setear el proveedor",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void anchoColumnasTabla() {
		getTblAprobacionPagos().getTableHeader().setReorderingAllowed(false);
		getTblAprobacionPagos().setCellSelectionEnabled(true);
		getTblAprobacionPagos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTblAprobacionPagos().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		getTblAprobacionPagos().addMouseListener(
				oMouseAdapterTblAprobacionPagos);
		getTblAprobacionPagos().addKeyListener(oKeyAdapterTblAprobacionPagos);

		TableColumn anchoColumna = getTblAprobacionPagos().getColumnModel()
		.getColumn(0);
		anchoColumna.setPreferredWidth(56);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblAprobacionPagos().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(75);

		TableColumn txtColumna = getTblAprobacionPagos().getColumnModel()
		.getColumn(7);
		JTextField txtCell = new JTextField();
		txtCell.addKeyListener(new TextChecker(MAX_LONGITUD_TXTCELL));
		txtCell.addKeyListener(new NumberTextFieldDecimal());
		txtColumna.setCellEditor(new DefaultCellEditor(txtCell));
		((DefaultCellEditor) getTblAprobacionPagos().getCellEditor(0, 7))
		.setClickCountToStart(1);
		getTblAprobacionPagos().setSurrendersFocusOnKeystroke(true);
	}

	MouseListener oMouseAdapterTblAprobacionPagos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblAprobacionPagos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			int row = ((JTable) evt.getSource()).getSelectedRow();
			int column = ((JTable) evt.getSource()).getSelectedColumn();
			double abonoDiferencia;
			try {
				getTblAprobacionPagos().getSelectionModel()
				.setSelectionInterval(row, row);
				getTblAprobacionPagos().getColumnModel().getSelectionModel()
				.setSelectionInterval(column, column);

				if (column == 0) {
					carteraDetalleAuxiliarColeccion = (List) SessionServiceLocator.getCarteraDetalleSessionService()
					.findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoAndEmpresaId(
							"P", tipoDocumento.getCodigo(),
							Parametros.getIdEmpresa());
					calcularTotalCarteraPago(carteraColeccion.get(row));
					abonoDiferencia = Utilitarios
					.redondeoValor(carteraColeccion.get(row).getSaldo()
							.doubleValue())
							- (Utilitarios.redondeoValor(totalCarteraPago
									.doubleValue()));

					if (((Boolean) getTblAprobacionPagos().getModel().getValueAt(row, 0))) {
						//CAMBIO FORMATO DECIMAL
						getTblAprobacionPagos().getModel().setValueAt(formatoDecimal.format(abonoDiferencia).toString(), row, 7);
						//getTblAprobacionPagos().getModel().setValueAt(String.valueOf(abonoDiferencia), row, 7);
					} else {
						getTblAprobacionPagos().getModel().setValueAt("", row,
								7);
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean esAbonoMayorAlSaldo() {
		String abonoS, preAbonoS;
		BigDecimal abono, preAbono;
		BigDecimal abonoDiferencia;
		Double valor;
		try {
			carteraDetalleAuxiliarColeccion = (List) SessionServiceLocator.getCarteraDetalleSessionService()
			.findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoAndEmpresaId(
					"P", tipoDocumento.getCodigo(),
					Parametros.getIdEmpresa());

			for (int i = 0; i < carteraColeccion.size(); i++) {
				if ((Boolean) getTblAprobacionPagos().getModel().getValueAt(i,
						0)) {
					if (getTblAprobacionPagos().getModel().getValueAt(i, 7) == null
							|| getTblAprobacionPagos().getModel().getValueAt(i,
									7).equals("")) {
						getTblAprobacionPagos().setRowSelectionInterval(i, i);
						SpiritAlert.createAlert("Debe ingresar un abono!",
								SpiritAlert.WARNING);
						return true;
					}

					abonoS = (String) getTblAprobacionPagos().getModel().getValueAt(i, 7);
					//CAMBIO REPLACE ALL
					abono = (!abonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(abonoS))): new BigDecimal(0));
					//abono = (!abonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(abonoS)): new BigDecimal(0));

					calcularTotalCarteraPago(carteraColeccion.get(i));
					valor = carteraColeccion.get(i).getSaldo().subtract(
							totalCarteraPago).doubleValue();
					abonoDiferencia = BigDecimal.valueOf(Utilitarios
							.redondeoValor(valor));

					preAbonoS = (String) getTblAprobacionPagos().getModel().getValueAt(i, 8);
					//CAMBIO REPLACE ALL
					preAbono = (!preAbonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(preAbonoS))) : new BigDecimal(0));
					//preAbono = (!preAbonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(preAbonoS)) : new BigDecimal(0));

					if (abono.compareTo(new BigDecimal(0)) == -1) {
						getTblAprobacionPagos().setRowSelectionInterval(i, i);
						SpiritAlert.createAlert("El abono es menor que cero!!",
								SpiritAlert.WARNING);
						return true;
					}

					if (abono.compareTo(abonoDiferencia) == 1) {
						getTblAprobacionPagos().setRowSelectionInterval(i, i);
						SpiritAlert
						.createAlert(
								"El abono es mayor a la diferencia: Saldo - Pre-Abono!!",
								SpiritAlert.WARNING);
						return true;
					} else if (abonoDiferencia.compareTo(BigDecimal.ZERO) == 0) {
						getTblAprobacionPagos().setRowSelectionInterval(i, i);
						SpiritAlert.createAlert(
								"Ya se aprobo el total del saldo!!",
								SpiritAlert.WARNING);
						return true;
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean esPagoAprobado() {
		for (int i = 0; i < carteraColeccion.size(); i++) {
			if ((Boolean) getTblAprobacionPagos().getModel().getValueAt(i, 10)) {
				//CAMBIO REPLACE ALL
				if (Double.valueOf(Utilitarios.removeDecimalFormat((String) getTblAprobacionPagos().getModel().getValueAt(i, 8))) <= 0) {
				//if (Double.valueOf(((String) getTblAprobacionPagos().getModel().getValueAt(i, 8))) <= 0) {
					getTblAprobacionPagos().setRowSelectionInterval(i, i);
					getTblAprobacionPagos().getColumnModel()
					.getSelectionModel().setSelectionInterval(10, 10);
					SpiritAlert
					.createAlert(
							"No es posible desaprobar un pago que no ha sido previamente aprobado!",
							SpiritAlert.WARNING);
					return false;
				}
			}
		}
		return true;
	}

	public void generarColeccionesAprobadosDesaprobados() {
		aprobadosColeccion.clear();
		desaprobadosColeccion.clear();
		abonosColeccion.clear();
		Boolean existeUnAprobado = false;
		Boolean existeUnDesaprobado = false;
		BigDecimal abono = new BigDecimal(0);
		String abonoS = "";

		if (!getCarteraColeccion().isEmpty()) {
			for (int i = 0; i < carteraColeccion.size(); i++) {
				if ((Boolean) getTblAprobacionPagos().getModel().getValueAt(i,
						0)) {
					existeUnAprobado = true;
				}
				if ((Boolean) getTblAprobacionPagos().getModel().getValueAt(i,
						10)) {
					existeUnDesaprobado = true;
				}
			}
			if (existeUnAprobado && existeUnDesaprobado) {
				SpiritAlert
				.createAlert(
						"No es posible aprobar y desaprobar pagos al mismo tiempo!",
						SpiritAlert.WARNING);
			} else {
				if (existeUnAprobado) {
					if (!esAbonoMayorAlSaldo()) {
						for (int i = 0; i < carteraColeccion.size(); i++) {
							abonoS = (String) getTblAprobacionPagos()
							.getModel().getValueAt(i, 7);
							//CAMBIO REPLACE ALL
							abono = (!abonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(abonoS))) : new BigDecimal(0));
							//abono = (!abonoS.equals("") ? BigDecimal.valueOf(Double.valueOf(abonoS)) : new BigDecimal(0));
							aprobadosColeccion
							.add((Boolean) getTblAprobacionPagos()
									.getModel().getValueAt(i, 0));
							abonosColeccion.add(abono);
						}
					}
				} else if (existeUnDesaprobado) {
					if (esPagoAprobado()) {
						for (int i = 0; i < carteraColeccion.size(); i++) {
							desaprobadosColeccion
							.add((Boolean) getTblAprobacionPagos()
									.getModel().getValueAt(i, 10));
						}
					}
				} else {
					SpiritAlert.createAlert(
							"No existe ninguna cartera aprobada !",
							SpiritAlert.WARNING);
				}
			}
		} else {
			SpiritAlert.createAlert("No existe ninguna cartera!",
					SpiritAlert.WARNING);
		}
	}

	private CarteraIf registrarCartera(Long idProveedorOficina) {
		CarteraData data = new CarteraData();
		try {
			data.setTipo(TIPO_PROVEEDOR);
			data.setOficinaId(Parametros.getIdOficina());
			data.setTipodocumentoId(tipoDocumento.getId());
			data.setClienteoficinaId(idProveedorOficina);
			// PREIMPRESO = null
			data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
			// VENDEDOR_ID = null
			String codigoMonedaLocal = Parametros.getCodMoneda();
			Iterator monedaLocalIterator = SessionServiceLocator.getMonedaSessionService()
			.findMonedaByCodigo(codigoMonedaLocal).iterator();
			if (monedaLocalIterator.hasNext()) {
				MonedaIf monedaLocal = (MonedaIf) monedaLocalIterator.next();
				data.setMonedaId(monedaLocal.getId());
			}
			java.sql.Date fechaEmision = new java.sql.Date(Utilitarios
					.dateHoy().getTime());
			data.setFechaEmision(Utilitarios.fromSqlDateToTimestamp(fechaEmision));
			// String unNumeroCartera = getNumeroCartera(fechaEmision,
			// tipoDocumento.getCodigo());
			// data.setCodigo(unNumeroCartera);
			data.setCodigo(" ");//
			double valor = 0D;
			for (int i = 0; i < aprobadosColeccion.size(); i++) {
				if (aprobadosColeccion.get(i)) {
					if (carteraColeccion.get(i).getClienteoficinaId()
							.compareTo(idProveedorOficina) == 0) {
						if (abonosColeccion.get(i).compareTo(new BigDecimal(0)) != 0)
							valor += abonosColeccion.get(i).doubleValue();
						else
							valor += carteraColeccion.get(i).getSaldo()
							.doubleValue();
					}
				}
			}

			data.setValor(BigDecimal.valueOf(valor));
			data.setSaldo(BigDecimal.valueOf(valor));
			data.setEstado(ESTADO_NORMAL);
			// COMENTARIO = null
			data.setAprobado(APROBADO_SI);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al registrar cartera!",
					SpiritAlert.ERROR);
		} catch (ParseException ev) {
			ev.printStackTrace();
		}
		return data;
	}

	private CarteraDetalleIf registrarCarteraDetalle(int i, int secuencial) {
		CarteraDetalleData data = new CarteraDetalleData();
		try {
			data.setSecuencial(secuencial);
			/*
			 * LineaIf linea =
			 * (LineaIf)getLineaSessionService().findLineaByCodigo(CODIGO_PUBLICIDAD).iterator().next();
			 * data.setLineaId(linea.getId());
			 */
			data.setFechaCreacion(new java.sql.Date(Utilitarios.dateHoy()
					.getTime()));
			if (abonosColeccion.get(i).compareTo(new BigDecimal(0)) != 0) {
				data.setValor(abonosColeccion.get(i));
				data.setSaldo(abonosColeccion.get(i));
			} else {
				data.setValor(carteraColeccion.get(i).getSaldo());
				data.setSaldo(carteraColeccion.get(i).getSaldo());
			}
			data.setCartera(CARTERA_NO);
			data.setReferencia(String.valueOf(carteraColeccion.get(i).getId()));
			if (getTblAprobacionPagos().getModel().getValueAt(i, 9) != null) {
				data.setObservacion(((String) getTblAprobacionPagos()
						.getModel().getValueAt(i, 9)).trim());
			}
		} catch (ParseException ev) {
			ev.printStackTrace();
		}
		return data;
	}

	public void save() {
		boolean cancelar = false;
		try {
			if (validarFormatoAbonos()) {
				generarColeccionesAprobadosDesaprobados();
				Map mapaProveedores = generarMapaProveedores();
				
				Map<CarteraIf, Map<List<CarteraDetalleIf>, List<CarteraIf>>> carteraCarteraDetalleCarteraCompraMap = new HashMap<CarteraIf, Map<List<CarteraDetalleIf>, List<CarteraIf>>>();
								
				//mapa de carteras de pago
				Map<Long, CarteraIf> carterasPagoMapa = new HashMap<Long, CarteraIf>();
				
				if (aprobadosColeccion.contains(true)) {
					String comprasRetencionSinPagar = "";
					boolean aprobarConRetencion = false;
					boolean detalleConRetencion = false;
					
					if (validateFields()) {
						for (int i = 0; i < aprobadosColeccion.size(); i++) {
							if (aprobadosColeccion.get(i)) {
								Long idProveedorOficina = carteraColeccion.get(i).getClienteoficinaId();
								if (!(Boolean) mapaProveedores.get(idProveedorOficina)) {
									mapaProveedores.put(idProveedorOficina,	true);
									
									List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
									List<CarteraIf> carteraCompraColeccion = new ArrayList<CarteraIf>();
									Map<List<CarteraDetalleIf>, List<CarteraIf>> carteraDetalleCarteraCompraMap = new HashMap<List<CarteraDetalleIf>, List<CarteraIf>>();
				
									CarteraIf carteraIf = registrarCartera(idProveedorOficina);
									generarColeccionesCarteraPago(idProveedorOficina, carteraDetalleColeccion, carteraCompraColeccion);
									
									//Reviso si ha la compra se le ha hecho la retencion
									if (!carteraCompraColeccion.isEmpty()) {
										for(CarteraIf carteraCompra : carteraCompraColeccion){
											Map aMap = new HashMap();
											aMap.put("id", carteraCompra.getReferenciaId());
											aMap.put("preimpreso", carteraCompra.getPreimpreso());
											aMap.put("tipodocumentoId", carteraCompra.getTipodocumentoId());
											aMap.put("oficinaId", carteraCompra.getOficinaId());											
											Iterator compraColeccionIt = SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMap).iterator();
											while(compraColeccionIt.hasNext()){
												CompraIf compra = (CompraIf)compraColeccionIt.next();
												detalleConRetencion = false;
												Iterator compraDetalleColeccionIt = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId()).iterator();
												while(compraDetalleColeccionIt.hasNext()){
													CompraDetalleIf compraDetalle = (CompraDetalleIf)compraDetalleColeccionIt.next();
													SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(compraDetalle.getIdSriAir());
													SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(compraDetalle.getSriIvaRetencionId());
													
													if(carteraCompra.getValor().compareTo(carteraCompra.getSaldo()) == 0 &&
															(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) != 0 || sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) != 0)){
														
														detalleConRetencion = true;														
													}
												}
												if(detalleConRetencion){
													if(comprasRetencionSinPagar.equals("")){
														comprasRetencionSinPagar = compra.getPreimpreso(); 
													}else{
														comprasRetencionSinPagar = comprasRetencionSinPagar + ", " + compra.getPreimpreso();
													}
												}
											}
										}
										
										if(!comprasRetencionSinPagar.equals("")){
											int opcion = JOptionPane.showOptionDialog(null, "A las Compras " + comprasRetencionSinPagar + " aún no se les hace la Retención ¿Desea aprobar de todas formas?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
											if (opcion == JOptionPane.YES_OPTION) {
												aprobarConRetencion = true;
											}
										}else{
											aprobarConRetencion = true;
										}
									}	
									
									if(aprobarConRetencion){
										List listaObservaciones = SessionServiceLocator.getCarteraSessionService().getObservacionesPago(carteraIf.getClienteoficinaId());
										if (!listaObservaciones.isEmpty()) {
											ClienteOficinaIf clienteOficinaIf=SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraIf.getClienteoficinaId());
											ClienteIf cliente=SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
											Object[] options={"Ver detalle","Aprobar","Cancelar"};
											int opt = JOptionPane.showOptionDialog(
													this,
													"El proveedor "+cliente.getRazonSocial()+" tiene saldos pendientes",
													"",
													JOptionPane.YES_NO_CANCEL_OPTION,
													JOptionPane.WARNING_MESSAGE,
													null,
													options,"Aprobar");
											if (opt == JOptionPane.NO_OPTION) {
												//SessionServiceLocator.getCarteraSessionService().procesarCartera(carteraIf, carteraDetalleColeccion, carteraCompraColeccion, Parametros.getIdEmpresa());
												carteraDetalleCarteraCompraMap.put(carteraDetalleColeccion, carteraCompraColeccion);
																							
											} else if (opt == JOptionPane.YES_OPTION) {
												JDObservacionesPagos jdObservacionesPagos = new JDObservacionesPagos(Parametros.getMainFrame());
												jdObservacionesPagos.setObservaciones(listaObservaciones);
												jdObservacionesPagos.setLocation(250, 200);
												jdObservacionesPagos.setPreferredSize(new Dimension(700, 200));
												jdObservacionesPagos.setModalityType(ModalityType.APPLICATION_MODAL);
												jdObservacionesPagos.pack();
												jdObservacionesPagos.setVisible(true);
												int opcionElegida = jdObservacionesPagos.OPCION_ELEGIDA;
												if (opcionElegida == JDObservacionesPagos.CANCELAR) {
													continue;
												} else if (opcionElegida == JDObservacionesPagos.CONTINUAR) {
													//SessionServiceLocator.getCarteraSessionService().procesarCartera(carteraIf, carteraDetalleColeccion, carteraCompraColeccion,Parametros.getIdEmpresa());
													carteraDetalleCarteraCompraMap.put(carteraDetalleColeccion, carteraCompraColeccion);
												}
											}
											else{
												cancelar = true;
												continue;
											}
										} 
										else {
											//SessionServiceLocator.getCarteraSessionService().procesarCartera(carteraIf, carteraDetalleColeccion, carteraCompraColeccion,Parametros.getIdEmpresa());
											carteraDetalleCarteraCompraMap.put(carteraDetalleColeccion, carteraCompraColeccion);
										}
										carteraCarteraDetalleCarteraCompraMap.put(carteraIf, carteraDetalleCarteraCompraMap);
										
										//lleno el mapa de carteras de pago
										carterasPagoMapa.put(carteraIf.getId(), carteraIf);										
									}
								}
							}
						}

						if (!cancelar && !carteraCarteraDetalleCarteraCompraMap.isEmpty()){
							SessionServiceLocator.getCarteraSessionService().procesarAprobacionPago(carteraCarteraDetalleCarteraCompraMap, Parametros.getIdEmpresa());
							SpiritAlert.createAlert("Cartera de Pago guardada con éxito",SpiritAlert.INFORMATION);
							carteraCarteraDetalleCarteraCompraMap.clear();
							showSaveMode();
							cargarTabla();
						}
					}
				} else if (desaprobadosColeccion.contains(true)) {
					List<CarteraIf> carteraCompraColeccion = new ArrayList<CarteraIf>();
					
					generarCarteraCompraColeccion(carteraCompraColeccion);
					String referencia = "";
					carterasDesaprobadasColeccion.clear();
					for (CarteraIf cartera : carteraCompraColeccion) {
						referencia = cartera.getId().toString();
						Collection detallesCarteraPago = SessionServiceLocator.getCarteraDetalleSessionService()
						.findCarteraDetalleByReferenciaByDocumentoNullAndByCarteraNo(referencia);
						Iterator detallesCarteraPagoIt = detallesCarteraPago.iterator();
						while (detallesCarteraPagoIt.hasNext()) {
							CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) detallesCarteraPagoIt.next();
							CarteraIf carteraDesaprobada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleIf.getCarteraId());
							carterasDesaprobadasColeccion.add(carteraDesaprobada);
						}
					}
					carterasDesaprobadasColeccion = removerCarterasRepetidas(carterasDesaprobadasColeccion);
					SessionServiceLocator.getCarteraSessionService().desaprobarPagos(carterasDesaprobadasColeccion);
					SpiritAlert.createAlert("Pago(s) desaprobado(s) con éxito", SpiritAlert.INFORMATION);
					carteraCompraColeccion.clear();
					showSaveMode();
					cargarTabla();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public Vector<CarteraIf> removerCarterasRepetidas(
			Vector<CarteraIf> carterasColeccion) {
		Map<Long, CarteraIf> carterasMapa = new HashMap<Long, CarteraIf>();
		for (CarteraIf carteraRepetida : carterasColeccion) {
			carterasMapa.put(carteraRepetida.getId(), carteraRepetida);
		}
		carterasColeccion.clear();
		Iterator carterasMapaIt = carterasMapa.keySet().iterator();
		while (carterasMapaIt.hasNext()) {
			Long key = (Long) carterasMapaIt.next();
			carterasColeccion.add(carterasMapa.get(key));
		}

		return carterasColeccion;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	public void update() {
		save();
	}
	
	public void authorize() {
		try {
			for (int i = 0; i < carteraColeccion.size(); i++) {
				String preAbono = (String) getTblAprobacionPagos().getModel().getValueAt(i, 8);
				boolean autorizado = (Boolean) getTblAprobacionPagos().getModel().getValueAt(i,0);
				if(preAbono != null && !preAbono.equals("") && !preAbono.equals("0.00") && autorizado){
					CarteraIf carteraPreAprobada = carteraColeccion.get(i);
					String referencia = String.valueOf(carteraPreAprobada.getId());
					Collection carterasDetalleEgreso = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByReferencia(referencia);
					Iterator carterasDetalleEgresoIt = carterasDetalleEgreso.iterator();
					
					boolean carteraPagoActualizada = false;
					
					while(carterasDetalleEgresoIt.hasNext()){
						CarteraDetalleIf carteraDetalleEgreso = (CarteraDetalleIf)carterasDetalleEgresoIt.next();
						CarteraIf carteraEgreso = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleEgreso.getCarteraId());
						
						Map carteraPagoMap = new HashMap();
						carteraPagoMap.put("carteraPagoId", carteraEgreso.getId());
						carteraPagoMap.put("empresaId", Parametros.getIdEmpresa());						
						Collection carterasPago = SessionServiceLocator.getCarteraPagoSessionService().findCarteraPagoByQuery(carteraPagoMap);
						
						Iterator carterasPagoIt = carterasPago.iterator();
						while(carterasPagoIt.hasNext()){
							CarteraPagoIf carteraPagoIf = (CarteraPagoIf)carterasPagoIt.next();
							if(carteraPagoIf.getEstado().equals("E")){
								Timestamp fecha = new Timestamp(new GregorianCalendar().getTimeInMillis());
								carteraPagoIf.setFechaAprobacion(fecha);
								carteraPagoIf.setUsuarioAprobacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
								carteraPagoIf.setEstado(EstadoCarteraPago.APROBADO.getLetra());
								SessionServiceLocator.getCarteraPagoSessionService().saveCarteraPago(carteraPagoIf);
								carteraPagoActualizada = true;
							}else{								
								SpiritAlert.createAlert("La cartera de pago de la compra: " + carteraPreAprobada.getComentario() + ". Tiene estado: " + EstadoCarteraPago.getEstadoCarteraPagoByLetra(carteraPagoIf.getEstado()), SpiritAlert.INFORMATION);
							}
						}
					}
					if(carteraPagoActualizada){
						SpiritAlert.createAlert("La cartera de pago fue autorizada correctamente.", SpiritAlert.INFORMATION);
					}
				}			
			}
					
			//System.out.println("autorizado");
			showSaveMode();

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		carteraColeccion.clear();
		aprobadosColeccion.clear();
		desaprobadosColeccion.clear();
		abonosColeccion.clear();
		carteraPagoColeccion.clear();
		carterasDesaprobadasColeccion.clear();
		carteraDetalleAuxiliarColeccion.clear();
		
		getTxtTotalAprobado().setText("");
		totalAprobado = new BigDecimal(0);

		DefaultTableModel model = (DefaultTableModel) getTblAprobacionPagos()
		.getModel();
		for (int i = this.getTblAprobacionPagos().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void showSaveMode() {
		//tiene que estar en modo Update para que funcione el metodo authorize
		setUpdateMode();
		getTxtProveedor().setEditable(false);
		initVariablesAndMapas();
		clean();		
		//cargarTabla();
	}
	
	//chequeo si el usuario tiene la opcion de autorizar
	//de ser asi selecciono el check box filtrar aprobados para que solo vea los pagos pre-aprobados
	/*public void chequearUsuarioAutorizado(){
		if(MainFrameModel.get_btnAuthorize().isEnabled()){
			getCbFiltrarAprobados().setSelected(true);
		}
		try {			
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			Collection rolesUsuario = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByUsuarioId(usuario.getId());
			Iterator rolesUsuarioIt = rolesUsuario.iterator();
			while(rolesUsuarioIt.hasNext()){
				RolUsuarioIf rolUsuarioIf = (RolUsuarioIf)rolesUsuarioIt.next();
				RolIf rol = SessionServiceLocator.getRolSessionService().getRol(rolUsuarioIf.getRolId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}*/

	private void cargarTabla() {
		Runnable runnable = new Runnable() {
			public void run() {
				setCursor(SpiritCursor.hourglassCursor);
				try {
					totalAprobado = new BigDecimal(0);
					ArrayList cartera = new ArrayList();
					java.util.Date fecha = new java.util.Date();
					java.sql.Date fechaCorte = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());
					if (proveedorIf != null) {
						cartera = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagaryCartera(Parametros.getIdEmpresa(), proveedorIf.getClienteId(), 0L, fechaCorte, false);
						/*cartera = (ArrayList) SessionServiceLocator.getCarteraSessionService()
								.findCarteraByTipoBySaldoByModuloCodigoByEmpresaIdAndByClienteOficinaId(
										"P", "COMP", Parametros.getIdEmpresa(),
										proveedorIf.getId());*/
					} else {
						cartera = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagaryCartera(Parametros.getIdEmpresa(), 0L, 0L, fechaCorte, false);
					}

					if (cartera.size() > 0) {
						Iterator carteraIterator = cartera.iterator();
						
						if (!getCarteraColeccion().isEmpty()) {
							getCarteraColeccion().removeAllElements();
						}

						carteraDetalleAuxiliarColeccion = (List) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoAndEmpresaId("P", tipoDocumento.getCodigo(),Parametros.getIdEmpresa());
						List<CarteraDetalleIf> carteraDetalleObsColeccion = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByTipoBySaldoAndEmpresaId("P", Parametros.getIdEmpresa());
						
						while (carteraIterator.hasNext()) {
							Object[] carteraArreglo = (Object[]) carteraIterator.next();
							CuentasPorPagarEJB cuentaPorPagar = (CuentasPorPagarEJB)carteraArreglo[0];
							CarteraIf carteraIf = (CarteraIf)carteraArreglo[1];							
							
							if (carteraIf.getSaldo().doubleValue() > 0D) {
								tableModel = (DefaultTableModel) getTblAprobacionPagos().getModel();
								Vector<Object> fila = new Vector<Object>();
								agregarFilaTabla(cuentaPorPagar, carteraIf, fila, carteraDetalleObsColeccion);
								
								if(getCbFiltrarAprobados().isSelected()){									
									String preaabono = (String)fila.get(8);
									if(preaabono != null && !preaabono.equals("") && !preaabono.equals("0.00")){
										getCarteraColeccion().add(carteraIf);
										tableModel.addRow(fila);
									}										
								}else{
									getCarteraColeccion().add(carteraIf);									
									tableModel.addRow(fila);
								}
							}
						}
						//CAMBIO FORMATO DECIMAL
						getTxtTotalAprobado().setText(formatoDecimal.format(totalAprobado));
						//getTxtTotalAprobado().setText(String.valueOf(totalAprobado));
						
					} else {
						SpiritAlert.createAlert("No existe ningún registro que presentar !", SpiritAlert.INFORMATION);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
				setCursor(SpiritCursor.normalCursor);
			}
		};

		Thread t = new Thread(runnable);
		t.start();

	}

	private void agregarFilaTabla(CuentasPorPagarEJB cuentaPorPagar, CarteraIf carteraIf, Vector<Object> fila, List<CarteraDetalleIf> carteraDetalleList) {
		try {
			carteraPagoColeccion.clear();
			clienteOficina = (ClienteOficinaIf) clienteOficinasMap.get(cuentaPorPagar.getProveedorOficinaId());
			cliente = (ClienteIf) clientesMap.get(clienteOficina.getClienteId());
			if (cuentaPorPagar.getCompraId() != null)
				compra = (CompraIf) comprasMap.get(cuentaPorPagar.getCompraId());
			else
				compra = null;
			calcularTotalCarteraPago(carteraIf);
			fila.add(new Boolean(false));
			fila.add(cliente.getRazonSocial());
			fila.add(cuentaPorPagar.getPreimpreso());
			fila.add(Utilitarios.getFechaCortaUppercase((compra!=null)?compra.getFecha():cuentaPorPagar.getFechaEmision()));
			fila.add((compra!=null)?Utilitarios.getFechaCortaUppercase(compra.getFecha()):null);
			//CAMBIO FORMATO DECIMAL
			fila.add(formatoDecimal.format(cuentaPorPagar.getValor()).toString());
			//fila.add(String.valueOf(cuentaPorPagar.getValor()));
			//CAMBIO FORMATO DECIMAL
			fila.add(formatoDecimal.format(cuentaPorPagar.getSaldo()).toString());
			//fila.add(String.valueOf(cuentaPorPagar.getSaldo()));
			fila.add("");
			//CAMBIO FORMATO DECIMAL
			fila.add(formatoDecimal.format(totalCarteraPago).toString());
			//fila.add(String.valueOf(totalCarteraPago));
			totalAprobado = totalAprobado.add(totalCarteraPago);
			
			//REVISAR SI CLIENTE TIENE CUENTA BANCARIA
			String observacionBanco = "";
			if(cliente.getBancoId() == null  || cliente.getTipoCuenta() == null || cliente.getNumeroCuenta() == null){
				observacionBanco = "FALTA INFORMACION BANCARIA";
			}

			List<CarteraDetalleIf> carteraDetalleObsColeccion = getCarteraDetalleObsColeccion(carteraDetalleList, "N", cuentaPorPagar.getId().toString());
			if (carteraDetalleObsColeccion.size() > 0) {
				int sizeColeccion = carteraDetalleObsColeccion.size() - 1;
				CarteraDetalleIf carteraDetalleObs = carteraDetalleObsColeccion.get(sizeColeccion);
				if (carteraDetalleObs.getObservacion() != null && !carteraDetalleObs.getObservacion().equals("FALTA INFORMACION BANCARIA")){
					fila.add(observacionBanco + " " + carteraDetalleObs.getObservacion());
				}else{
					fila.add(observacionBanco);
				}
			} else{
				fila.add(observacionBanco);
			}

			fila.add(new Boolean(false));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private List<CarteraDetalleIf> getCarteraDetalleObsColeccion(List<CarteraDetalleIf> carteraDetalleList, String cartera, String referencia) {
		List<CarteraDetalleIf> carteraDetalleObsColeccion = new ArrayList<CarteraDetalleIf>();
		Iterator it = carteraDetalleList.iterator();
		while (it.hasNext()) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) it.next();
			if (carteraDetalle.getCartera() != null
					&& carteraDetalle.getReferencia() != null
					&& carteraDetalle.getCartera().equals(cartera)
					&& carteraDetalle.getReferencia().equals(referencia)) {
				carteraDetalleObsColeccion.add(carteraDetalle);
				break;
			}
		}

		return carteraDetalleObsColeccion;
	}

	private void calcularTotalCarteraPago(CarteraIf carteraIf) throws GenericBusinessException {
		totalCarteraPago = new BigDecimal(0);
		carteraPagoColeccion = getCarteraDetallesPagoList(carteraIf.getId());
		if (!carteraPagoColeccion.isEmpty()) {
			for (CarteraDetalleIf carteraDetalle : carteraPagoColeccion) {
				totalCarteraPago = totalCarteraPago.add(carteraDetalle.getSaldo());
			}
		}
	}

	private List<CarteraDetalleIf> getCarteraDetallesPagoList(Long carteraId) {
		List<CarteraDetalleIf> carteraDetallesList = new ArrayList<CarteraDetalleIf>();
		Iterator carteraDetallesAuxiliarIterator = carteraDetalleAuxiliarColeccion.iterator();

		while (carteraDetallesAuxiliarIterator.hasNext()) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetallesAuxiliarIterator.next();
			if (carteraDetalle.getReferencia() != null && carteraDetalle.getReferencia().equals(String.valueOf(carteraId)))
				carteraDetallesList.add(carteraDetalle);
		}
		return carteraDetallesList;
	}

	private Map generarMapaProveedores() {
		Map mapaProveedores = new HashMap();

		for (int i = 0; i < aprobadosColeccion.size(); i++) {
			if (aprobadosColeccion.get(i)) {
				Long idProveedor = carteraColeccion.get(i).getClienteoficinaId();
				if (!mapaProveedores.containsKey(idProveedor))
					mapaProveedores.put(idProveedor, false);
			}
		}

		return mapaProveedores;
	}

	private void generarColeccionesCarteraPago(Long idProveedorOficina, List<CarteraDetalleIf> carteraDetalleColeccion,	List<CarteraIf> carteraCompraColeccion) {
		int secuencial = 0;
		for (int i = 0; i < aprobadosColeccion.size(); i++) {
			if (aprobadosColeccion.get(i)) {
				if (idProveedorOficina.compareTo(carteraColeccion.get(i).getClienteoficinaId()) == 0) {
					secuencial++;
					carteraCompraColeccion.add(carteraColeccion.get(i));
					CarteraDetalleIf carteraDetalleIf = registrarCarteraDetalle(i, secuencial);
					carteraDetalleColeccion.add(carteraDetalleIf);
				}
			}
		}
	}

	public void generarCarteraCompraColeccion(List<CarteraIf> carteraCompraColeccion) {
		carteraCompraColeccion.clear();
		for (int i = 0; i < desaprobadosColeccion.size(); i++) {
			if (desaprobadosColeccion.get(i)) {
				carteraCompraColeccion.add(carteraColeccion.get(i));
			}
		}
	}

	public boolean validarFormatoAbonos() {
		BigDecimal abono = new BigDecimal(0);
		for (int i = 0; i < carteraColeccion.size(); i++) {
			try {
				if ((Boolean) getTblAprobacionPagos().getModel().getValueAt(i,0)) {
					//CAMBIO REPLACE ALL
					abono = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat((String) getTblAprobacionPagos().getModel().getValueAt(i, 7))));
					//abono = BigDecimal.valueOf(Double.valueOf(((String) getTblAprobacionPagos().getModel().getValueAt(i, 7))));
				}
			} catch (NumberFormatException e) {
				SpiritAlert.createAlert("El formato de uno de los abonos puede ser incorrecto!",SpiritAlert.WARNING);
				getTblAprobacionPagos().getSelectionModel().setSelectionInterval(i, i);
				getTblAprobacionPagos().getColumnModel().getSelectionModel().setSelectionInterval(7, 7);
				return false;
			}
		}
		return true;
	}

	public boolean validateFields() {
		String observacion = "";
		for (int j = 0; j < aprobadosColeccion.size(); j++) {
			if (getTblAprobacionPagos().getModel().getValueAt(j, 9) != null) {
				observacion = (String) getTblAprobacionPagos().getModel().getValueAt(j, 9);
				if (observacion.length() > MAX_LONGITUD_OBSERVACION) {
					SpiritAlert.createAlert("La Observación puede tener máximo 300 caracteres",SpiritAlert.WARNING);
					getTblAprobacionPagos().getSelectionModel().setSelectionInterval(j, j);
					getTblAprobacionPagos().getColumnModel().getSelectionModel().setSelectionInterval(9, 9);
					return false;
				}
			}
		}
		return true;
	}

	public void refresh() {
		initVariablesAndMapas();
		clean();
		cargarTabla();
	}

	public Vector<CarteraIf> getCarteraColeccion() {
		return carteraColeccion;
	}

	public void setCarteraColeccion(Vector<CarteraIf> carteraColeccion) {
		this.carteraColeccion = carteraColeccion;
	}
}
