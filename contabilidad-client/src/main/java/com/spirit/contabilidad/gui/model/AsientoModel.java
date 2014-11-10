package com.spirit.contabilidad.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.AsientoCriteria;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.panel.JPAsiento;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.CentroGastoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class AsientoModel extends JPAsiento {
	
	private static final int MAX_LONGITUD_NUMERO = 30;
	private static final int MAX_LONGITUD_NOTA = 500;
	private static final String CUENTA_HABER = "H";
	private static final String CUENTA_DEBE = "D";
	private AsientoHandler handler;
	private double totalDebe;
	private double totalHaber;
	private CuentaIf cuenta;
	private PlanCuentaIf planCuenta;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Map asientoDetallesToActualizar = new HashMap();
	private ArrayList<CuentaIf> cuentasLocal = new ArrayList<CuentaIf>();
	private static final long serialVersionUID = 4744961401738737145L;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	protected ArrayList lista;
	private AsientoIf asiento;
	private List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
	private List<AsientoDetalleIf> asientoDetalleAnteriorColeccion = new ArrayList<AsientoDetalleIf>();
	private List<AsientoDetalleIf> asientoDetalleRemovidoColeccion = new ArrayList<AsientoDetalleIf>();
	private AsientoDetalleIf asientoDetalleForUpdate;
	Long idCuentaTemp = 0L;
	String codigoCuentaTemp = "";
	private int selectedRow = -1;
	boolean isSaldoCuentaSuficientePasado = true;
	boolean isSaldoCuentaCuadradoFuturo = true;
	PeriodoIf periodo;
	//private Vector<String[]> cheques = new Vector<String[]>();
	private ArrayList<String[]> cheques = new ArrayList<String[]>();
	private JDCheque jdCheque;
	private List<BigDecimal> valoresChequeColeccion = new ArrayList<BigDecimal>();
	private Vector<ComprobanteEgresoData> comprobanteEgresoColeccion = new Vector<ComprobanteEgresoData>();
			
	private static final String NOMBRE_ESTADO_PREASIENTO = "PRE-ASIENTO";
	private static final String NOMBRE_ESTADO_ASIENTO = "ASIENTO";
	public static final String ESTADO_PREASIENTO = NOMBRE_ESTADO_PREASIENTO.substring(0, 1);
	public static final String ESTADO_ASIENTO = NOMBRE_ESTADO_ASIENTO.substring(0, 1);
	private static final String ESTADO_CUENTA_ACTIVA = "A";
	private static final int ADD = 0;
	private static final int UPDATE = 1;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 	
	private String valorDebe = "";
	private String valorHaber = "";
	private String notaAsiento = "";
	
	public AsientoModel() {
		handler = new AsientoHandler(this);
		anadirPopupMenu();
		getTblAsiento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		handler.registrarControlesCampos();
		initKeyListeners();
		initListeners();
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		clean();
		showSaveMode();
		setColumnsWidth();
		new HotKeyComponent(this);
	}
	
	public AsientoModel(AsientoIf asiento) {
		handler = new AsientoHandler(this);
		anadirPopupMenu();
		getTblAsiento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		handler.registrarControlesCampos();
		initKeyListeners();
		initListeners();
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		showSaveMode();
		setColumnsWidth();
		new HotKeyComponent(this);
		this.asiento = asiento;
		getSelectedObject();
	}
	
	private void initKeyListeners(){
		getTxtNumero().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO));
		
		getBtnBuscarCuenta().setToolTipText("Buscar cuenta");
		getBtnBuscarCuenta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregar().setToolTipText("Agrega el Asiento a la Tabla");
		getBtnAgregar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizar().setToolTipText("Actualizar asiento en la tabla");
		getBtnActualizar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminar().setToolTipText("Eliminar asiento de la tabla");
		getBtnEliminar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblAsiento().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblAsiento().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
	}
	
	private void initListeners() {
		getBtnMostrarPanelCentrosGasto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getJpCentrosGasto().isVisible()) {
					getJpCentrosGasto().setVisible(false);
					getBtnMostrarPanelCentrosGasto().setText("Centros de Gasto | Ver más >>");
				} else {
					getJpCentrosGasto().setVisible(true);
					getBtnMostrarPanelCentrosGasto().setText("Centros de Gasto | Ver menos <<");
				}
			}			
		});
		
		getBtnAgregarNota().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarNotaModel agregarNotaModel = new AgregarNotaModel(Parametros.getMainFrame(), notaAsiento, MAX_LONGITUD_NOTA);
				notaAsiento = agregarNotaModel.getNotaAsiento();
				if (notaAsiento != null && !notaAsiento.trim().equals(""))
					getBtnAgregarNota().setText("Editar Nota");
				else
					getBtnAgregarNota().setText("Agregar Nota");
			}
		});
		
		getCmbPeriodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbPeriodo().getSelectedItem() != null)
					periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
				else
					periodo = null;
			}
		});
		
		getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarAsientoDetalle();
			}
		});
		
		getBtnActualizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarAsientoDetalle();
				getTxtValorDebe().setEnabled(true);
				getTxtValorDebe().setText("");
				getTxtValorHaber().setEnabled(true);
				getTxtValorHaber().setText("");
				getTxtCuenta().grabFocus();
			}
		});
		
		getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarAsientoDetalle();
			}
		});
		
		getTxtReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				anadirOActualizarAsiento();
			}
		});
		
		getTxtGlosa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				anadirOActualizarAsiento();
			}
		});

		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbPlanCuenta().getSelectedItem() != null) {
					planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
				}
			}
		});

		getCbEfectivo().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (getCbEfectivo().isSelected()) {
					getCmbTipoAsiento().setEnabled(true);
					cargarComboTipoAsiento();
				} else {
					getCmbTipoAsiento().setSelectedItem(null);
					getCmbTipoAsiento().setEnabled(false);
					getCmbSubtipoAsiento().setSelectedItem(null);
					getCmbSubtipoAsiento().setEnabled(false);
				}
			}
		});

		getCmbTipoAsiento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbSubtipoAsiento().setEnabled(true);
				cargarComboSubtipoAsiento();
			}
		});

		getTblAsiento().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				getDetailTblAsiento(evt);
			}
		});
		
		getTblAsiento().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				getDetailTblAsiento(evt);
			}
		});
		
		getTxtCuenta().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				try {
					if (e.getKeyChar() == KeyEvent.VK_ENTER) {
						Map parameterMap = new HashMap();
						String strCuenta = getTxtCuenta().getText().trim();
						String strCodigoNombreCuenta = "";
						
						if (strCuenta.contains(" - "))
							strCodigoNombreCuenta = strCuenta.split(" - ")[0] + "%";
						else 
							strCodigoNombreCuenta = strCuenta + "%";
						
						parameterMap.put("codigoNombreCuenta", strCodigoNombreCuenta);
						if (getCmbPlanCuenta().getSelectedItem() != null)
							parameterMap.put("plancuentaId", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
						parameterMap.put("imputable", "S");
						parameterMap.put("estado", ESTADO_CUENTA_ACTIVA);

						int tamanoLista = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
						CuentaIf cuentaIf = null;
						if (tamanoLista == 1) {
							Iterator cuentaIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombre(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId()).iterator();
							if (cuentaIterator.hasNext()) {
								cuentaIf = (CuentaIf) cuentaIterator.next();
								getSelectedCuenta(cuentaIf);
							}
						} else if (tamanoLista > 1) {
							CuentaCriteria cuentaCriteria = new CuentaCriteria();
							cuentaCriteria.setIdUsuario(((UsuarioIf) Parametros.getUsuarioIf()).getId());
							cuentaCriteria.setResultListSize(tamanoLista);
							cuentaCriteria.setQueryBuilded(parameterMap);
							popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
							if ( popupFinder.getElementoSeleccionado() != null ) {
								cuentaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
								getTxtCuenta().setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
							}
						} 
						
						if (cuentaIf == null) {
							cuenta = null;
							if (tamanoLista <= 0)
								SpiritAlert.createAlert("No se halló la cuenta deseada en el plan de cuentas seleccionado", SpiritAlert.WARNING);
						} else {
							cuenta = cuentaIf;
							automaticSetValues();
						}
						
						getTxtValorDebe().setEnabled(true);
						getTxtValorHaber().setEnabled(true);
					}
				} catch (GenericBusinessException ex) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					ex.printStackTrace();
				}
			}
		});
		
		getTxtValorDebe().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					agregarAsientoDetalle();
				}
			}
		});
		
		getTxtValorHaber().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					agregarAsientoDetalle();
				}
			}
		});

		getBtnBuscarCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbPlanCuenta().getSelectedItem() != null) {
					PlanCuentaIf planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
					cuentaCriteria = new CuentaCriteria("Plan de Cuentas", "S", planCuenta.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId(), ESTADO_CUENTA_ACTIVA);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),cuentaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if (popupFinder.getElementoSeleccionado() != null) {
						cuenta = (CuentaIf) popupFinder.getElementoSeleccionado();
						getCmbPlanCuenta().setEnabled(false);
						handler.enableCombos(cuenta);
						getTxtValorDebe().setEnabled(true);
						getTxtValorHaber().setEnabled(true);
						getTxtCuenta().setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
						automaticSetValues();
					}
				} else
					SpiritAlert.createAlert("Debe seleccionar un Plan de Cuentas", SpiritAlert.WARNING);
			}
		});
	}

	private void getDetailTblAsiento(ComponentEvent evt) {
		if (getTblAsiento().getSelectedRow() != -1) {
			setSelectedRow(((JTable) evt.getSource()).getSelectedRow());
			asientoDetalleForUpdate = (AsientoDetalleIf) asientoDetalleColeccion.get(getSelectedRow());
			enableAsientoDetalleForUpdate(asientoDetalleForUpdate);
			getTxtValorDebe().setEnabled(true);
			getTxtValorHaber().setEnabled(true);
		}
	}
	
	private void setColumnsWidth() {
		TableColumn anchoColumna = getTblAsiento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblAsiento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblAsiento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblAsiento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblAsiento().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
	}
	
	private void automaticSetValues() {
		if (asientoDetalleColeccion.size() > 0) {
			try {
				TipoCuentaIf tipoCuenta = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuenta(cuenta.getTipocuentaId());
				double totalDebe = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTotalDebe().getText()));
				double totalHaber = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTotalHaber().getText()));
				double diferencia = Math.abs(totalDebe - totalHaber);
				if (tipoCuenta.getDebehaber().equals(CUENTA_DEBE) && totalDebe < totalHaber)
					getTxtValorDebe().setText(formatoDecimal.format(diferencia));
				else if (tipoCuenta.getDebehaber().equals(CUENTA_HABER) && totalHaber < totalDebe)
					getTxtValorHaber().setText(formatoDecimal.format(diferencia));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void anadirPopupMenu() {
		menuItem = new JMenuItem("<html><font color=red>Eliminar Asiento Detalle</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				eliminarAsientoDetalle();
			}
		});
		popup.add(menuItem);

		getTblAsiento().add(popup);
		getTblAsiento().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private void anadirOActualizarAsiento() {
		if (getBtnAnadir().getLabel().equals("Agregar Asiento"))
			agregarAsientoDetalle();
		else if (getBtnAnadir().getLabel().equals("Actualizar Asiento")) {
			actualizarAsientoDetalle();
			getTxtValorDebe().setEnabled(true);
			getTxtValorDebe().setText("");
			getTxtValorHaber().setEnabled(true);
			getTxtValorHaber().setText("");
			getTxtCuenta().grabFocus();
		}
	}

	private boolean validarAsientoDetalle(int operacion) {
		if (!getTxtCuenta().getText().contains("-") || cuenta == null) {
			SpiritAlert.createAlert("Debe ingresar una cuenta primero !!!", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}
		
		/*boolean cuentaRepetida = false;
		
		for (int i=0; i<cuentasLocal.size(); i++) {
			CuentaIf cuentaIf = cuentasLocal.get(i);

			if (operacion == ADD)
				if (cuenta.getId().compareTo(cuentaIf.getId()) == 0)
					cuentaRepetida = true;
			if (operacion == UPDATE)
				if (cuenta.getId().compareTo(cuentaIf.getId()) == 0)
					if (getSelectedRow() != i)
						cuentaRepetida = true;
		}
		
		if (cuentaRepetida) {
			SpiritAlert.createAlert("La cuenta ya se encuentra en el detalle", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}*/
		
		if (getTxtGlosa().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar una glosa para el detalle del asiento!!!", SpiritAlert.WARNING);
			getTxtGlosa().grabFocus();
			return false;
		}
		
		if ("".equals(valorDebe.trim()) && "".equals(valorHaber.trim())) {
			SpiritAlert.createAlert("Debe ingresar un valor para el detalle del asiento!!!", SpiritAlert.WARNING);
			getTxtValorDebe().grabFocus();
			return false;
		}
		
		/*if ((!valorDebe.equals("") && BigDecimal.valueOf(Double.valueOf(valorDebe)).compareTo(BigDecimal.ZERO) != 0) && (!valorHaber.equals("") && BigDecimal.valueOf(Double.valueOf(valorHaber)).compareTo(BigDecimal.ZERO) != 0)) {
			SpiritAlert.createAlert("Solo uno de los dos campos Debe o Haber puede tener valor !!!", SpiritAlert.WARNING);
			getTxtValorDebe().grabFocus();
			return false;
		}*/
		
		if(!valorDebe.equals("") && BigDecimal.valueOf(Double.valueOf(valorDebe)).compareTo(BigDecimal.ZERO) == 0){
			getTxtValorDebe().setText("");
			//valorDebe = "";
		}
		if(!valorHaber.equals("") && BigDecimal.valueOf(Double.valueOf(valorHaber)).compareTo(BigDecimal.ZERO) == 0){
			getTxtValorHaber().setText("");
			//valorHaber = "";
		}
		
		if (!valorDebe.equals("") && !valorHaber.equals("")) {
			SpiritAlert.createAlert("Solo uno de los dos campos Debe o Haber puede tener valor !!!", SpiritAlert.WARNING);
			getTxtValorDebe().grabFocus();
			return false;
		}
		
		if (getCmbCentroGasto().isEnabled() && getCmbCentroGasto().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar información de Centro de Gasto", SpiritAlert.WARNING);
			getCmbCentroGasto().grabFocus();
			return false;
		}
		
		if (getCmbCliente().isEnabled() && getCmbCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar información de Cliente", SpiritAlert.WARNING);
			getCmbCliente().grabFocus();
			return false;
		}
		
		if (getCmbDepartamento().isEnabled() && getCmbDepartamento().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar información de Departamento", SpiritAlert.WARNING);
			getCmbDepartamento().grabFocus();
			return false;
		}
		
		if (getCmbEmpleado().isEnabled() && getCmbEmpleado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar información de Empleado", SpiritAlert.WARNING);
			getCmbEmpleado().grabFocus();
			return false;
		}
		
		if (getCmbLinea().isEnabled() && getCmbLinea().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar información de Línea", SpiritAlert.WARNING);
			getCmbLinea().grabFocus();
			return false;
		}

		return true;
	}
	
	private String getTextoValorSinFormato(String textoValorFormateado) {
		int textoLongitud = textoValorFormateado.length();
		if (textoLongitud > 0) {
			if (textoValorFormateado.contains("$"))
				return Utilitarios.removeDecimalFormat(textoValorFormateado.substring(1,textoLongitud));
			else
				return Utilitarios.removeDecimalFormat(textoValorFormateado);
		}
		
		return textoValorFormateado;
	}

	private void agregarAsientoDetalle() {
		try {
			Vector<String> fila = new Vector<String>();
			valorDebe = getTextoValorSinFormato(getTxtValorDebe().getText());
			valorHaber = getTextoValorSinFormato(getTxtValorHaber().getText());

			if (validarAsientoDetalle(ADD)) {
				AsientoDetalleData data = new AsientoDetalleData();
				data.setCuentaId(cuenta.getId());
				data.setGlosa(getTxtGlosa().getText());
				data.setReferencia(getTxtReferencia().getText());
				
				DepartamentoIf selectedDepartamento = (DepartamentoIf) getCmbDepartamento().getSelectedItem();
				if (selectedDepartamento != null)
					data.setDepartamentoId(selectedDepartamento.getId());
				
				LineaIf selectedLinea = (LineaIf) getCmbLinea().getSelectedItem();
				if (selectedLinea != null)
					data.setLineaId(selectedLinea.getId());
				
				CentroGastoIf selectedCentroGasto = (CentroGastoIf) getCmbCentroGasto().getSelectedItem();
				if (selectedCentroGasto != null)
					data.setCentrogastoId(selectedCentroGasto.getId());
				
				EmpleadoIf selectedEmpleado = (EmpleadoIf) getCmbEmpleado().getSelectedItem();
				if (selectedEmpleado != null)
					data.setEmpleadoId(selectedEmpleado.getId());
				
				ClienteIf selectedCliente = (ClienteIf) getCmbCliente().getSelectedItem();
				if (selectedCliente != null)
					data.setClienteId(selectedCliente.getId());
				
				asientoDetalleColeccion.add(data);
				cuentasLocal.add(cuenta);
				fila.add(cuenta.getCodigo());
				fila.add(cuenta.getNombre());
				fila.add(getTxtGlosa().getText());
				if (!"".equals(valorDebe.trim())) {
					try {
						data.setDebe(new BigDecimal(valorDebe));
					} catch(java.lang.NumberFormatException nfe) {
						getTxtValorDebe().grabFocus();
						throw new java.lang.NumberFormatException("El formato del valor del debe ingresado no es correcto");
					}
					//fila.add(NumberTextFieldDecimal.formatearTexto(valorDebe, NumberTextFieldDecimal.VIEW_MODE));
					fila.add(formatoDecimal.format(Double.valueOf(valorDebe)));
					data.setHaber(new BigDecimal(0));
					fila.add("");// se añade un vacio al campo
				}
				
				if (!"".equals(valorHaber.trim())) {
					try {
						data.setHaber(new BigDecimal(valorHaber));
					} catch(java.lang.NumberFormatException nfe) {
						getTxtValorHaber().grabFocus();
						throw new java.lang.NumberFormatException("El formato del valor del haber ingresado no es correcto");
					}
					fila.add("");// se añade un vacio al campo
					//fila.add(NumberTextFieldDecimal.formatearTexto(valorHaber, NumberTextFieldDecimal.VIEW_MODE));
					fila.add(formatoDecimal.format(Double.valueOf(valorHaber)));
					data.setDebe(new BigDecimal(0));
				}

				DefaultTableModel tableModel = (DefaultTableModel) getTblAsiento().getModel();
				tableModel.addRow(fila);
				cleanAsientoDetalle();
				actualizarTotales();
				getCmbCentroGasto().setEnabled(false);
				getCmbDepartamento().setEnabled(false);
				getCmbCliente().setEnabled(false);
				getCmbEmpleado().setEnabled(false);
				getCmbLinea().setEnabled(false);
				getTxtValorDebe().setText("");
				getTxtValorHaber().setText("");
				getTxtCuenta().grabFocus();
			}
		} catch(java.lang.NumberFormatException nfe) {
			SpiritAlert.createAlert(nfe.getMessage(), SpiritAlert.WARNING);
			nfe.printStackTrace();
		} catch (Exception e) {
			SpiritAlert.createAlert("Ocurrió un error al agregar el detalle del asiento !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cleanAsientoDetalle() {
		getTxtValorDebe().setEnabled(true);
		getTxtValorDebe().setText("");
		getTxtValorHaber().setEnabled(true);
		getTxtValorHaber().setText("");
		getTxtCuenta().setText("");
		getTxtReferencia().setText("");
		getTxtGlosa().setText("");
	}

	private void actualizarAsientoDetalle() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblAsiento().getModel();
		AsientoDetalleIf data = asientoDetalleForUpdate;
		valorDebe = getTextoValorSinFormato(getTxtValorDebe().getText());
		valorHaber = getTextoValorSinFormato(getTxtValorHaber().getText());

		if (getTblAsiento().getSelectedRow() == -1)
			SpiritAlert.createAlert("Por favor, elija la información que desea actualizar ", SpiritAlert.WARNING);
		else {
			if (validarAsientoDetalle(UPDATE)) {
				data.setCuentaId(cuenta.getId());
				data.setGlosa(getTxtGlosa().getText());
				data.setReferencia(getTxtReferencia().getText());
				DepartamentoIf selectedDepartamento = (DepartamentoIf) getCmbDepartamento().getSelectedItem();
				
				if (selectedDepartamento != null)
					data.setDepartamentoId(selectedDepartamento.getId());
				
				LineaIf selectedLinea = (LineaIf) getCmbLinea().getSelectedItem();
				if (selectedLinea != null)
					data.setLineaId(selectedLinea.getId());

				CentroGastoIf selectedCentroGasto = (CentroGastoIf) getCmbCentroGasto().getSelectedItem();
				if (selectedCentroGasto != null)
					data.setCentrogastoId(selectedCentroGasto.getId());
				
				EmpleadoIf selectedEmpleado = (EmpleadoIf) getCmbEmpleado().getSelectedItem();
				if (selectedEmpleado != null)
					data.setEmpleadoId(selectedEmpleado.getId());
				
				if (!"".equals(valorDebe.trim())) {
					data.setDebe(new BigDecimal(valorDebe));
					data.setHaber(new BigDecimal(0));
				}
				
				if (!"".equals(valorHaber.trim())) {
					data.setDebe(new BigDecimal(0));
					data.setHaber(new BigDecimal(valorHaber));
				}
				
				asientoDetalleColeccion.set(getTblAsiento().getSelectedRow(), data);
				tableModel.setValueAt(cuenta.getCodigo(), getTblAsiento().getSelectedRow(), 0);
				tableModel.setValueAt(cuenta.getNombre(), getTblAsiento().getSelectedRow(), 1);
				tableModel.setValueAt(getTxtGlosa().getText(), getTblAsiento().getSelectedRow(), 2);
				if (!getTxtValorDebe().getText().equals("")){
					//tableModel.setValueAt(NumberTextFieldDecimal.formatearTexto(valorDebe, NumberTextFieldDecimal.VIEW_MODE), getTblAsiento().getSelectedRow(), 3);
					tableModel.setValueAt(formatoDecimal.format(Double.valueOf(valorDebe)), getTblAsiento().getSelectedRow(), 3);
				}else{
					tableModel.setValueAt("", getTblAsiento().getSelectedRow(), 3);
				}
				
				if (!getTxtValorHaber().getText().equals("")){
					//tableModel.setValueAt(NumberTextFieldDecimal.formatearTexto(valorHaber, NumberTextFieldDecimal.VIEW_MODE), getTblAsiento().getSelectedRow(), 4);
					tableModel.setValueAt(formatoDecimal.format(Double.valueOf(valorHaber)), getTblAsiento().getSelectedRow(), 4);
				}else{
					tableModel.setValueAt("", getTblAsiento().getSelectedRow(), 4);
				}
				
				//cuentasLocal.setElementAt(cuenta, getSelectedRow());
				cuentasLocal.set(getSelectedRow(),cuenta);
				actualizarTotales();
				getCmbCentroGasto().setSelectedItem(null);
				getCmbCentroGasto().setEnabled(false);
				getCmbDepartamento().setSelectedItem(null);
				getCmbDepartamento().setEnabled(false);
				getCmbCliente().setSelectedItem(null);
				getCmbCliente().setEnabled(false);
				getCmbEmpleado().setSelectedItem(null);
				getCmbEmpleado().setEnabled(false);
				getCmbLinea().setSelectedItem(null);
				getCmbLinea().setEnabled(false);
				getTxtCuenta().setText("");
				getTxtReferencia().setText("");
				getTxtGlosa().setText("");
				getTxtValorDebe().setText("");
				getTxtValorHaber().setText("");
				getTxtCuenta().grabFocus();
			}
		}
	}

	public void removeAllAsientoDetalle() {
		DefaultTableModel model = (DefaultTableModel) getTblAsiento().getModel();
		for (int i = model.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void eliminarAsientoDetalle() {
		if (getTblAsiento().getSelectedRow() != -1) {
			DefaultTableModel model = (DefaultTableModel) getTblAsiento().getModel();
			AsientoDetalleIf asientoDetalleTemp = (AsientoDetalleIf) asientoDetalleColeccion.get(getTblAsiento().getSelectedRow());

			if (asientoDetalleTemp.getId() != null) {
				asientoDetalleColeccion.remove(getTblAsiento().getSelectedRow());
				if (asientoDetalleTemp.getPrimaryKey() != null && !asientoDetalleRemovidoColeccion.contains(asientoDetalleTemp))
					asientoDetalleRemovidoColeccion.add(asientoDetalleTemp);
				model.removeRow(getTblAsiento().getSelectedRow());
				removerCuenta(cuentasLocal, cuenta);
			} else {
				asientoDetalleColeccion.remove(getTblAsiento().getSelectedRow());
				model.removeRow(getTblAsiento().getSelectedRow());
				removerCuenta(cuentasLocal, cuenta);
			}
		} else
			SpiritAlert.createAlert("Debe elegir el detalle a eliminar !!!", SpiritAlert.WARNING);
		
		cleanAsientoDetalle();
		actualizarTotales();
	}
	
	private void removerCuenta(ArrayList<CuentaIf> cuentasLocal, CuentaIf cuenta) {
		Iterator cuentasLocalIterator = cuentasLocal.iterator();
		
		while (cuentasLocalIterator.hasNext()) {
			CuentaIf cuentaIf = (CuentaIf) cuentasLocalIterator.next();
			if (cuentaIf.getId().compareTo(cuenta.getId()) == 0)
				cuentasLocalIterator.remove();
		}
	}

	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblAsiento().getModel();
		double totalDebe = 0.00;
		double totalHaber = 0.00;

		try {
			for (int fila = 0; fila < getTblAsiento().getRowCount(); fila++) {
				String strDebe = getTextoValorSinFormato(tableModel.getValueAt(fila, 3)!=null?tableModel.getValueAt(fila, 3).toString().trim():"");
				String strHaber = getTextoValorSinFormato(tableModel.getValueAt(fila, 4)!=null?tableModel.getValueAt(fila, 4).toString().trim():"");
				if ((!strDebe.equals("0")) && (!strDebe.equals("0.00")) && (!strDebe.equals("")) && (strDebe != null))
					totalDebe += Double.parseDouble(strDebe);
				if ((!strHaber.equals("0")) && (!strHaber.equals("0.00")) && (!strHaber.equals("")) && (strHaber != null))
					totalHaber += Double.parseDouble(strHaber);
			}
			//getTxtTotalDebe().setText(NumberTextFieldDecimal.formatearTexto(String.valueOf(totalDebe), NumberTextFieldDecimal.VIEW_MODE));
			getTxtTotalDebe().setText(formatoDecimal.format(totalDebe));
			getTxtTotalHaber().setText(formatoDecimal.format(totalHaber));
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurrió un error al actualizar los totales del Debe y Haber !", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		valoresChequeColeccion.clear();
		getTxtValorDebe().setEnabled(true);
		getTxtValorDebe().setText("0.00");
		getTxtValorHaber().setEnabled(true);
		getTxtValorHaber().setText("0.00");
		getTxtCuenta().setText("");
		getTxtGlosa().setText("");
		getTxtReferencia().setText("");
		removeAllAsientoDetalle();
		getCmbPlanCuenta().setEnabled(true);
		cuenta = null;
		DefaultTableModel model = (DefaultTableModel) getTblAsiento().getModel();

		for (int i = getTblAsiento().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		asientoDetalleColeccion = null;
		asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
		asientoDetalleAnteriorColeccion = null;
		asientoDetalleAnteriorColeccion = new ArrayList<AsientoDetalleIf>();
		asientoDetalleRemovidoColeccion = null;
		asientoDetalleRemovidoColeccion = new ArrayList<AsientoDetalleIf>();
		cuentasLocal = null;
		cuentasLocal = new ArrayList<CuentaIf>();
		isSaldoCuentaSuficientePasado = true;
		isSaldoCuentaCuadradoFuturo = true;
		notaAsiento = "";
	}

	public boolean isEmpty() {
		if (this.getCmbPlanCuenta().getSelectedItem() == null && this.getCmbPeriodo().getSelectedItem() == null)
			return true;
		else
			return false;
	}
	
	private ComprobanteEgresoData agregarDetalleComprobanteAnticipo(AsientoIf asiento, AsientoDetalleIf asientoDetalle, boolean anulado) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
			Map cuentaEntidadMapa = new HashMap();
			cuentaEntidadMapa.put("tipoEntidad", "B");
			cuentaEntidadMapa.put("cuentaId",cuenta.getId());
			//CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf)SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(cuentaEntidadMapa).iterator().next();
			CuentaEntidadIf cuentaEntidad = null;
			Iterator itCE = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(cuentaEntidadMapa).iterator();
			if(itCE.hasNext()) cuentaEntidad= (CuentaEntidadIf)itCE.next();
			
			CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(cuentaEntidad.getEntidadId());
			data.setBanco(cuenta.getNombre());
			data.setNumeroCuenta(cuentaBancaria.getCuenta());
			
			if((asientoDetalle.getReferencia()==null) || asientoDetalle.getReferencia().trim().equals("")){
				data.setNumeroCheque("D/B");
			}else{
				data.setNumeroCheque(asientoDetalle.getReferencia());
			}
			
			data.setFechaCompra("N/A");
			data.setCodigoCompra("");
			
			if(anulado){
				data.setPreimpresoFactura(" ANULADO");
				data.setDetalle("");
				data.setValor("0.00");
				data.setSaldo("0.00");
			}else{
				data.setPreimpresoFactura("N/A\n");
				data.setDetalle(asientoDetalle.getGlosa().length()>52?asientoDetalle.getGlosa().substring(0,52):asientoDetalle.getGlosa());
				data.setValor((anulado || (asientoDetalle.getHaber() == null))?formatoDecimal.format(new Double(0)):formatoDecimal.format(asientoDetalle.getHaber()));
				data.setSaldo(formatoDecimal.format(new Double(0)));
			}			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	private void generarReporte(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion, boolean anulado) {
		try {
			comprobanteEgresoColeccion.clear();
			Double totalCheque = 0D;
			if(!anulado){
				for(BigDecimal valor : valoresChequeColeccion){
					totalCheque = totalCheque + valor.doubleValue();
				}
			}			
			//String valorComprobante = String.valueOf(totalCheque);
			String valorComprobante = formatoDecimal.format(Double.valueOf(totalCheque));
			String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
			Double dParteDecimal = 0.0;
			if (!parteDecimal.isEmpty())
				dParteDecimal = Double.valueOf(parteDecimal);
			String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, Parametros.getMonedaPredeterminada());
			String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
			
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber() != null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteAnticipo(asiento, asientoDetalle, anulado);
					String fecha = asiento.getFecha().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					comprobanteEgresoData.setFechaEmision(fechaEmision);
					comprobanteEgresoData.setProveedor(asiento.getObservacion());
					comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
					comprobanteEgresoData.setConcepto(asiento.getObservacion());
					comprobanteEgresoData.setValorTotal(valorComprobante);
					comprobanteEgresoData.setCodigo(asiento.getNumero());
					comprobanteEgresoColeccion.add(comprobanteEgresoData);
				}				
			}			
			
			if (comprobanteEgresoColeccion.size() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Comprobante de Egreso?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/cartera/RPComprobanteEgreso.jasper";
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
					
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					
					String fecha = asiento.getFecha().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("emitido", fechaEmision);
					ReportModelImpl.processReport(fileName, parametrosMap, comprobanteEgresoColeccion, true);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	public void generarChequeData(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion) {
		//cheques.clear();
		cheques = null;
		cheques = new ArrayList<String[]>();
		int contador = 0;
		for(BigDecimal valor : valoresChequeColeccion){
			Double totalCheque = valor.doubleValue();
			
			if (totalCheque.compareTo(0D) != 0) {
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				String valorCheque = formatoDecimal.format(Double.valueOf(totalCheque));
				String parteDecimal = valorCheque.substring(valorCheque.indexOf('.'), valorCheque.length());
				Double dParteDecimal = 0.0;
				if (!parteDecimal.isEmpty())
					dParteDecimal = Double.valueOf(parteDecimal);
					
				String pagueseA = asiento.getObservacion();
				String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorCheque, dParteDecimal, new int[] { 70, 90 }, false, Parametros.getMonedaPredeterminada());
				String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
				String cantidadLetrasSegundaLinea = cantidadLetras[1].replaceAll("  ","");
				//String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(asiento.getFecha());
				//String lugarFechaPrimerReemplazo = lugarFecha.replaceFirst("-","DE");
				//lugarFecha = lugarFechaPrimerReemplazo.replaceAll("-","DEL");
				
				String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaNuevosCheques(asiento.getFecha());
				
				String[] datosCheque = new String[5];
				datosCheque[0] = valorCheque;
				datosCheque[1] = pagueseA;
				datosCheque[2] = cantidadLetrasPrimeraLinea;
				datosCheque[3] = cantidadLetrasSegundaLinea;
				datosCheque[4] = lugarFecha;
				cheques.add(datosCheque);
			}
			contador++;
		}			
		
		if (cheques.size() > 0) {
			jdCheque = new JDCheque(Parametros.getMainFrame(), cheques);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdCheque.setLocation(x, y);
			jdCheque.pack();
			jdCheque.setVisible(true);
		}
	}
	
	public boolean verificarImpresionCheque(List<AsientoDetalleIf> asientoDetalleColeccion){
		try {
			valoresChequeColeccion.clear();
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber()!=null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
					if(!cuenta.getCuentaBanco().equals("S")){
						return false;
					}
				}				
			}
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber()!=null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					valoresChequeColeccion.add(asientoDetalle.getHaber());
				}				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void save() {
		if (validateFields()) {
			try {
				AsientoIf asiento = registrarAsiento();
				String numero = SessionServiceLocator.getAsientoSessionService().procesarAsiento(asiento, asientoDetalleColeccion, true);
				asiento.setNumero(numero);
				SpiritAlert.createAlert("Asiento guardado con éxito", SpiritAlert.INFORMATION);
				if(verificarImpresionCheque(asientoDetalleColeccion)){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea imprimir los cheques?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
					if (opcion == JOptionPane.YES_OPTION) {
						generarChequeData(asiento, asientoDetalleColeccion);
					}
					boolean anulado = false;
					generarReporte(asiento, asientoDetalleColeccion, anulado);
				}
				this.clean();
				System.gc();
				this.showSaveMode();
			} catch(GenericBusinessException e){
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				e.printStackTrace();
			} catch (Exception e) {
				SpiritAlert.createAlert("Ocurrió un error al guardar el asiento", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (asiento != null && (asiento.getStatus().equals(ESTADO_PREASIENTO) || (asiento.getStatus().equals(this.ESTADO_ASIENTO) && MainFrameModel.get_btnAuthorize().isEnabled()))) {
			if (validateFields()) {
				try {
					// Cuando se actualiza un asiento previamente guardado con estado ASIENTO,
					// antes de afectar los saldos nuevamente se deben reversar los saldos afectados anteriormente por este asiento
					boolean reversarSaldos = false;
					AsientoIf asientoAnterior = null;
					if (asiento != null) 
						asientoAnterior = (AsientoIf) DeepCopy.copy(asiento);
					if (asiento.getStatus().equals(ESTADO_ASIENTO) && getCmbEstado().getSelectedItem().toString().substring(0,1).equals(ESTADO_ASIENTO))
						reversarSaldos = true;						
					
					boolean actualizarNumeroAsiento = true;
					java.sql.Date fechaAsientoAnterior = new java.sql.Date(asientoAnterior.getFecha().getTime());
					int yearAsientoAnterior = fechaAsientoAnterior.getYear() + 1900;
					int monthAsientoAnterior = fechaAsientoAnterior.getMonth() + 1;
					AsientoIf asiento = registrarAsientoForUpdate();
					java.sql.Date fechaAsiento = new java.sql.Date(asiento.getFecha().getTime());
					int yearAsiento = fechaAsiento.getYear() + 1900;
					int monthAsiento = fechaAsiento.getMonth() + 1;
					if (yearAsientoAnterior == yearAsiento && monthAsientoAnterior == monthAsiento) {
						asiento.setNumero(asientoAnterior.getNumero());
						actualizarNumeroAsiento = false;
					}
					Map periodosAutorizarMap = new HashMap();
					periodosAutorizarMap.put(this.periodo.getId(), this.periodo);
					Map saldosCuentasMap = SessionServiceLocator.getSaldoCuentaSessionService().mapearSaldosCuentasByPeriodosMap(periodosAutorizarMap);
					SessionServiceLocator.getAsientoSessionService().actualizarAsiento(asiento, asientoDetalleColeccion, asientoAnterior, asientoDetalleAnteriorColeccion, asientoDetalleRemovidoColeccion, reversarSaldos, actualizarNumeroAsiento, (UsuarioIf) Parametros.getUsuarioIf(), new HashMap(), new HashMap(), saldosCuentasMap, new HashMap(), false);
					SpiritAlert.createAlert("Asiento actualizado con éxito", SpiritAlert.INFORMATION);
					if(verificarImpresionCheque(asientoDetalleColeccion)){
						int opcion = JOptionPane.showOptionDialog(null, "¿Desea imprimir los cheques?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
						if (opcion == JOptionPane.YES_OPTION) {
							generarChequeData(asiento, asientoDetalleColeccion);
						}
						boolean anulado = false;
						generarReporte(asiento, asientoDetalleColeccion, anulado);
					}
					this.clean();
					this.showSaveMode();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					SpiritAlert.createAlert("Ocurrió un error al actualizar el asiento", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		} else {
			SpiritAlert.createAlert("Usted no tiene permiso para actualizar asientos autorizados", SpiritAlert.INFORMATION);
		}
	}
	
	public void delete() {
		try {
			String log = "ASIENTO ELIMINADO MANUALMENTE";
			SessionServiceLocator.getAsientoSessionService().procesarEliminacionAsiento(asiento, Parametros.getUsuario(), log, false);
			SpiritAlert.createAlert("Asiento eliminado con éxito",SpiritAlert.INFORMATION);
			if(verificarImpresionCheque(asientoDetalleColeccion)){
				boolean anulado = true;
				generarReporte(asiento, asientoDetalleColeccion, anulado);
			}
			this.clean();
			this.showSaveMode();
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar el asiento. " + e.getMessage(), SpiritAlert.WARNING);
		}
	}

	private AsientoIf registrarAsiento() throws GenericBusinessException {
		String strEfectivo = "N";
		PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
		java.sql.Date fechaAsiento = new java.sql.Date(getCmbFecha().getDate().getTime());

		if (getCbEfectivo().isSelected())
			strEfectivo = "S";

		String unNumeroAsiento = getNumeroAsiento(fechaAsiento);
		SubtipoAsientoIf subtipoAsiento = (SubtipoAsientoIf) this.getCmbSubtipoAsiento().getSelectedItem();
		AsientoData data = new AsientoData();
		data.setEmpresaId(Parametros.getIdEmpresa());
		data.setNumero(unNumeroAsiento);
		data.setStatus(getCmbEstado().getSelectedItem().toString().substring(0,1));
		data.setEfectivo(strEfectivo);
		data.setPeriodoId(periodo.getId());
		data.setPlancuentaId(planCuenta.getId());
		data.setObservacion(getTxtConcepto().getText());
		data.setFecha(fechaAsiento);
		data.setElaboradoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_ASIENTO))
			data.setAutorizadoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		if (getCbEfectivo().isSelected() && subtipoAsiento != null)
			data.setSubtipoasientoId(subtipoAsiento.getId());
		data.setOficinaId(Parametros.getIdOficina());
		if (notaAsiento != null && !notaAsiento.equals(""))
			data.setUsarNota(SpiritConstants.getOptionYes().substring(0,1));
		else {
			data.setUsarNota(SpiritConstants.getOptionNo().substring(0,1));
			notaAsiento = "";
		}
		data.setNota(notaAsiento);
		return data;
	}

	private boolean verificarCuentaEfectivo() {
		if (getCbEfectivo().isSelected()) {
			for (AsientoDetalleIf asientoDetalle : asientoDetalleColeccion) {
				try {
					CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
					if ("S".equals(cuenta.getEfectivo()))
						return true;
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	private String getNumeroAsiento(java.sql.Date fechaAsiento) {
		String codigo = "";

		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		//PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
		String mesAsiento = Utilitarios.getMonthFromDate(fechaAsiento);
		String anioAsiento = Utilitarios.getYearFromDate(fechaAsiento);
		codigo = empresa.getCodigo() + "-";
		codigo += planCuenta.getCodigo() + "-";
		//codigo += periodo.getCodigo() + "-";
		codigo += mesAsiento + "-";
		codigo += anioAsiento + "-";
		return codigo;

	}

	private Map buildQuery() {
		Map aMap = new HashMap();

		if (!getTxtNumero().getText().equals(""))
			aMap.put("numero", getTxtNumero().getText() + "%");
		else
			aMap.put("numero","%");
		
		if (!getTxtConcepto().getText().equals(""))
			aMap.put("observacion", "%" + getTxtConcepto().getText() + "%");
				
		if (this.getCmbPlanCuenta().getSelectedItem() != null)
			aMap.put("plancuentaId", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
		
		if (this.getCmbPeriodo().getSelectedItem() != null)
			aMap.put("periodoId", ((PeriodoIf) getCmbPeriodo().getSelectedItem()).getId());
		
		if (getCmbFecha().getSelectedItem() != null)
			aMap.put("fecha", new java.sql.Date(getCmbFecha().getDate().getYear(), getCmbFecha().getDate().getMonth(), getCmbFecha().getDate().getDate()));
	
		if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
			aMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));

		return aMap;
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista =  SessionServiceLocator.getAsientoSessionService().getAsientoListSize(mapa);
			if (tamanoLista > 0) {
				AsientoCriteria asientoCriteria = new AsientoCriteria();
				asientoCriteria.setResultListSize(tamanoLista);
				asientoCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(100);
				anchoColumnas.addElement(60);
				anchoColumnas.addElement(220);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), asientoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					asiento = (AsientoIf) popupFinder.getElementoSeleccionado();
					getSelectedObject();
				}
			} else
				SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información",SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		String strTotalHaber = getTxtTotalHaber().getText();
		strTotalHaber = Utilitarios.removeDecimalFormat(strTotalHaber);
		String strTotalDebe = getTxtTotalDebe().getText();
		strTotalDebe = Utilitarios.removeDecimalFormat(strTotalDebe);
		Date fechaAsiento = getCmbFecha().getDate();
		PeriodoIf periodo = null;
		
		if (getCmbPeriodo().getSelectedItem() != null)
			periodo = (PeriodoIf) this.getCmbPeriodo().getSelectedItem();
		else
			periodo = null;
	
		if (periodo == null) {
			SpiritAlert.createAlert("Debe seleccionar un período!", SpiritAlert.WARNING);
			getCmbPeriodo().grabFocus();
			return false;
		}
		
		if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_ASIENTO) && !(MainFrameModel.get_btnAuthorize().isEnabled())) {
			SpiritAlert.createAlert("Usted sólo tiene permitido guardar PRE-ASIENTOS", SpiritAlert.WARNING);
			getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PREASIENTO);
			getCmbEstado().grabFocus();
			return false;
		}
		
		if (getMode() == SpiritMode.UPDATE_MODE) {
			if (asiento.getStatus().equals(ESTADO_ASIENTO) && getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_PREASIENTO)) {
				SpiritAlert.createAlert("Un asiento autorizado no puede cambiar su estado a PRE-ASIENTO", SpiritAlert.WARNING);
				getCmbEstado().grabFocus();
				return false;
			}
		}
		
		if (fechaAsiento == null) {
			SpiritAlert.createAlert("Debe seleccionar una fecha de Asiento!", SpiritAlert.WARNING);
			getCmbFecha().grabFocus();
			return false;
		}

		if (fechaAsiento.before(periodo.getFechaini()) || fechaAsiento.after(periodo.getFechafin())) {
			SpiritAlert.createAlert("La fecha del asiento debe estar dentro del período!", SpiritAlert.WARNING);
			getCmbFecha().grabFocus();
			return false;
		}
		
		int monthAsiento = fechaAsiento.getMonth() + 1;
		String monthAsientoString = String.valueOf(monthAsiento);
		if (monthAsiento <= 9)
			monthAsientoString = "0" + monthAsientoString;
		int yearAsiento = fechaAsiento.getYear() + 1900;
		String yearAsientoString = String.valueOf(yearAsiento);
		Map parameterMap = new HashMap();
		parameterMap.put("periodoId", periodo.getId());
		parameterMap.put("mes", monthAsientoString);
		parameterMap.put("anio", yearAsientoString);
		try {
			Iterator periodoDetalleAsientoIterator = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByQueryAndEstadoActivoOrParcial(parameterMap).iterator();
			if (!periodoDetalleAsientoIterator.hasNext()) {
				SpiritAlert.createAlert("El Período-Mes del asiento está Cerrado o Inactivo.\n" +
						                 "Verifique que el período y fecha seleccionados correspondan\n" +
						                 "a un Período-Mes Activo o Parcial!", SpiritAlert.WARNING);
				getCmbPeriodo().grabFocus();
				return false;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		if (getTxtConcepto().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar un concepto para el asiento", SpiritAlert.WARNING);
			getTxtConcepto().grabFocus();
			return false;
		}
		
		if ((!(Double.parseDouble(strTotalHaber) > 0)) && (!(Double.parseDouble(strTotalDebe) > 0))) {
			SpiritAlert.createAlert("Los valores totales de Debe y Haber deben ser mayores que 0!!", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}
		
		if (!(strTotalHaber.equals(strTotalDebe))) {
			SpiritAlert.createAlert("El total de Haber no es igual al total del Debe !!", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}
		
		/*if (!verificarCuentaEfectivo() && getCbEfectivo().isSelected()) {
			SpiritAlert.createAlert("La opción de efectivo está seleccionada.\n" + 
					"Al menos una de la cuentas del asiento debe ser de efectivo!\n", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}*/
		
		if (getCbEfectivo().isSelected() && getCmbTipoAsiento().getSelectedItem() == null) {
			SpiritAlert.createAlert("La opción de efectivo está seleccionada.\n" + "Debe escoger un tipo de asiento!", SpiritAlert.WARNING);
			getCmbTipoAsiento().grabFocus();
			return false;
			
		}
		
		if (getCbEfectivo().isSelected() && getCmbSubtipoAsiento().getSelectedItem() == null) {
			SpiritAlert.createAlert("La opción de efectivo está seleccionada.\n" + "Debe escoger un subtipo de asiento!", SpiritAlert.WARNING);
			getCmbSubtipoAsiento().grabFocus();
			return false;
			
		}
		
		if (getTblAsiento().getRowCount() < 2) {
			SpiritAlert.createAlert("Debe existir Detalle Debe/Haber para guardar el Asiento !!", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}

		return true;
	}

	private AsientoIf registrarAsientoForUpdate() {
		SubtipoAsientoIf subtipoAsiento = null;
		String unNumeroAsiento = getNumeroAsiento(new java.sql.Date(getCmbFecha().getDate().getTime()));
		asiento.setNumero(unNumeroAsiento);
		asiento.setEmpresaId(Parametros.getIdEmpresa());
		asiento.setPeriodoId(((PeriodoIf) this.getCmbPeriodo().getSelectedItem()).getId());
		if (asiento.getStatus().equals(ESTADO_PREASIENTO) && getCmbEstado().getSelectedItem().toString().substring(0,1).equals(ESTADO_ASIENTO))
			asiento.setAutorizadoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		asiento.setStatus(getCmbEstado().getSelectedItem().toString().substring(0,1));
		asiento.setObservacion(getTxtConcepto().getText()); //Concepto reemplaza a observaciones
		asiento.setFecha(new java.sql.Date(getCmbFecha().getDate().getTime()));		
		if (!getCbEfectivo().isSelected())
			asiento.setEfectivo("N");
		else {
			asiento.setEfectivo("S");
			subtipoAsiento = (SubtipoAsientoIf) this.getCmbSubtipoAsiento().getSelectedItem();
			if (subtipoAsiento != null)
				asiento.setSubtipoasientoId(subtipoAsiento.getId());
		}
		if (notaAsiento != null && !notaAsiento.equals(""))
			asiento.setUsarNota(SpiritConstants.getOptionYes().substring(0,1));
		else {
			asiento.setUsarNota(SpiritConstants.getOptionNo().substring(0,1));
			notaAsiento = "";
		}
		asiento.setNota(notaAsiento);
		return asiento;
	}

	public void authorize() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			if (!asiento.getStatus().equals(ESTADO_ASIENTO)) {
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ASIENTO);
				update();
			} else {
				SpiritAlert.createAlert("No se puede autorizar nuevamente un asiento previamente autorizado", SpiritAlert.INFORMATION);
			}
		}
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				if(verificarImpresionCheque(asientoDetalleColeccion)){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea imprimir los cheques?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
					if (opcion == JOptionPane.YES_OPTION) {
						generarChequeData(asiento, asientoDetalleColeccion);
					}
					boolean anulado = false;
					generarReporte(asiento, asientoDetalleColeccion, anulado);
				}
				
				DefaultTableModel tblModelReporte = (DefaultTableModel) getTblAsiento().getModel();
				if (tblModelReporte.getRowCount() > 0) {
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
	    			if(opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/contabilidad/RPAsiento.jasper";
	    				if (asiento.getTipoDocumentoId() != null) {
		    				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(asiento.getTipoDocumentoId());
	    					if (tipoDocumento.getTipoTroqueladoId() != null) {
	    						TipoTroqueladoIf tipoTroquelado = SessionServiceLocator.getTipoTroqueladoSessionService().getTipoTroquelado(tipoDocumento.getTipoTroqueladoId());
	    						if (tipoTroquelado.getNumeroSeccionesHoja() == 2)
	    							fileName = "jaspers/contabilidad/RPAsientoDoble.jasper";
	    						else if (tipoTroquelado.getNumeroSeccionesHoja() == 4)
	    							fileName = "jaspers/contabilidad/RPAsientoCuadruple.jasper";
	    					}
	    				}
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator().next();
						MenuIf menu = null;
						Iterator menuIT = SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator();
						if(menuIT.hasNext()) menu= (MenuIf) menuIT.next();
						
						
						parametrosMap.put("codigoReporte", menu.getCodigo());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", empresa.getNombre());
						parametrosMap.put("ruc", empresa.getRuc());
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						parametrosMap.put("ciudad", ciudad.getNombre());
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
						parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
						parametrosMap.put("numero", asiento.getNumero());
						parametrosMap.put("fechaMovimiento", asiento.getFecha().toString());
						parametrosMap.put("mes", Utilitarios.getNombreMes(asiento.getFecha().getMonth() + 1).substring(0,3));
						parametrosMap.put("totalDebe", getTxtTotalDebe().getText());
						parametrosMap.put("totalHaber", getTxtTotalHaber().getText());
						String elaboradoPor = "";
						String autorizadoPor = "";
						if (asiento.getElaboradoPorId() != null) {
							UsuarioIf elaboradoPorIf = SessionServiceLocator.getUsuarioSessionService().getUsuario(asiento.getElaboradoPorId());
							EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(elaboradoPorIf.getEmpleadoId());
							elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos();
						}
						
						if (asiento.getAutorizadoPorId() != null) {
							UsuarioIf autorizadoPorIf = SessionServiceLocator.getUsuarioSessionService().getUsuario(asiento.getAutorizadoPorId());
							EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(autorizadoPorIf.getEmpleadoId());
							autorizadoPor = empleado.getNombres() + " " + empleado.getApellidos();
						}
						parametrosMap.put("elaboradoPor", elaboradoPor);
						parametrosMap.put("autorizadoPor", autorizadoPor);
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
					}
				} else
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.WARNING);
			}
			catch (ParseException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.WARNING);
			}
		}
	}
	
	public void refresh() {
		cargarComboPeriodo();
		cargarComboPlanCuenta();
		if (getCbEfectivo().isSelected()) {
			getCmbTipoAsiento().setEnabled(true);
			cargarComboTipoAsiento();
		}
	}
	
	public void duplicate() {
		try {
			if (getMode() == SpiritMode.UPDATE_MODE){
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea hacer una copia del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					AsientoIf asientoDuplicado = (AsientoIf)DeepCopy.copy(asiento);
					
					java.sql.Date fechaAsiento = new java.sql.Date(getCmbFecha().getDate().getTime());
					String unNumeroAsiento = getNumeroAsiento(fechaAsiento);
					asientoDuplicado.setId(null);
					asientoDuplicado.setNumero(unNumeroAsiento);
					asientoDuplicado.setStatus(ESTADO_PREASIENTO);
					asientoDuplicado.setElaboradoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
					asientoDuplicado.setTipoDocumentoId(null);
					asientoDuplicado.setTransaccionId(null);
					
					for(int i=0; i < asientoDetalleColeccion.size(); i++){
						asientoDetalleColeccion.get(i).setId(null);
						asientoDetalleColeccion.get(i).setAsientoId(null);
					}
					
					String numero = SessionServiceLocator.getAsientoSessionService().procesarAsiento(asientoDuplicado, asientoDetalleColeccion, true);
					SpiritAlert.createAlert("Asiento copiado con éxito!, el número es: " + numero, SpiritAlert.INFORMATION);
					this.clean();
					System.gc();
					this.showSaveMode();
				}	
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		getBtnAnadir().setLabel("Agregar Asiento");
		anadirOActualizarAsiento();
	}

	public void updateDetail() {
		getBtnAnadir().setLabel("Actualizar Asiento");
		anadirOActualizarAsiento();
	}
	
	public void deleteDetail() {
		eliminarAsientoDetalle();
	}

	public void showFindMode() {
		getTxtNumero().setBackground(Parametros.findColor);
		getCmbPlanCuenta().setBackground(Parametros.findColor);
		getCmbPeriodo().setBackground(Parametros.findColor);
		getCmbFecha().setBackground(Parametros.findColor);
		getTxtConcepto().setBackground(Parametros.findColor);
		
		planCuenta = null;
		this.getTxtNumero().setText("");
		this.getTxtNumero().setEditable(true);
		this.getTxtNumero().setEnabled(true);
		this.getCmbPeriodo().setEnabled(true);
		this.getCmbFecha().setEnabled(true);
		this.getTxtCuenta().setEnabled(false);
		this.getTxtGlosa().setEnabled(false);
		this.getTxtReferencia().setEnabled(false);
		this.getCmbEstado().setEnabled(false);
		this.getTxtValorDebe().setEnabled(false);
		this.getTxtValorHaber().setEnabled(false);
		totalDebe = 0.0;
		totalHaber = 0.0;
		getTxtTotalHaber().setText(formatoDecimal.format(totalHaber));
		getTxtTotalDebe().setText(formatoDecimal.format(totalDebe));
		this.getTxtTotalDebe().setEnabled(false);
		this.getTxtTotalHaber().setEnabled(false);
		this.getBtnBuscarCuenta().setEnabled(false);
		this.getBtnAgregar().setEnabled(false);
		this.getBtnActualizar().setEnabled(false);
		this.getBtnEliminar().setEnabled(false);
		this.getCbEfectivo().setEnabled(false);
		this.getTxtConcepto().setText("");
		this.getTxtConcepto().setEnabled(true);
		this.getCmbPeriodo().setSelectedItem(null);
		this.getCmbFecha().setSelectedItem(null);
		this.getCmbPlanCuenta().setSelectedItem(null);
		this.getCmbEstado().setSelectedItem(null);
		cargarComboPeriodo();
		getTxtConcepto().grabFocus();
		getTxtNumero().grabFocus();
	}
	
	public void showSaveMode() {
		getTxtNumero().setBackground(Parametros.saveUpdateColor);
		getCmbPlanCuenta().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodo().setBackground(Parametros.saveUpdateColor);
		getCmbFecha().setBackground(Parametros.saveUpdateColor);
		getTxtConcepto().setBackground(Parametros.saveUpdateColor);

		setSaveMode();
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFecha().setEnabled(true);
		getCmbCentroGasto().setEnabled(false);
		getCmbDepartamento().setEnabled(false);
		getCmbCliente().setEnabled(false);
		getCmbEmpleado().setEnabled(false);
		getCmbLinea().setEnabled(false);
		cargarComboPeriodo();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		getCmbFecha().setCalendar(calendar);
		cargarComboPlanCuenta();
		this.getTxtConcepto().setText("");
		this.getTxtConcepto().setEnabled(true);
		this.getTxtNumero().setText("");
		this.getTxtNumero().setEditable(false);
		this.getCmbTipoAsiento().setEnabled(false);
		this.getCmbSubtipoAsiento().setEnabled(false);
		this.getCmbPeriodo().setEnabled(true);
		this.getCmbFecha().setEnabled(true);
		this.getTxtCuenta().setEnabled(true);
		this.getTxtGlosa().setEnabled(true);
		this.getTxtReferencia().setEnabled(true);
		this.getCmbEstado().setEnabled(true);
		getCmbEstado().setSelectedItem("PRE-ASIENTO");
		this.getTxtValorDebe().setEnabled(true);
		this.getTxtValorHaber().setEnabled(true);
		this.getTxtTotalDebe().setEnabled(false);
		this.getTxtTotalHaber().setEnabled(false);
		this.getBtnBuscarCuenta().setEnabled(true);
		this.getBtnAgregar().setEnabled(true);
		this.getBtnActualizar().setEnabled(true);
		this.getBtnEliminar().setEnabled(true);
		this.getCbEfectivo().setEnabled(true);
		this.getCbEfectivo().setSelected(false);
		this.getBtnAnadir().setLabel("Agregar Asiento");
		getTxtValorDebe().setText("");
		getTxtValorHaber().setText("");
		totalDebe = 0.0;
		totalHaber = 0.0;
		getTxtTotalHaber().setText(formatoDecimal.format(totalHaber));
		getTxtTotalDebe().setText(formatoDecimal.format(totalDebe));
		getCmbDepartamento().removeAllItems();
		getCmbDepartamento().setSelectedItem(null);
		getCmbDepartamento().setEnabled(false);
		getCmbLinea().removeAllItems();
		getCmbLinea().setSelectedItem(null);
		getCmbLinea().setEnabled(false);
		getCmbEmpleado().removeAllItems();
		getCmbEmpleado().setSelectedItem(null);
		getCmbEmpleado().setEnabled(false);
		getCmbCentroGasto().removeAllItems();
		getCmbCentroGasto().setSelectedItem(null);
		getCmbCentroGasto().setEnabled(false);
		getCmbCliente().removeAllItems();
		getCmbCliente().setSelectedItem(null);
		getCmbCliente().setEnabled(false);
		getTxtConcepto().grabFocus();
		getJpCentrosGasto().setVisible(false);
		fixAsientosAnticipos();
	}

	public void showUpdateMode() {
		getTxtNumero().setBackground(Parametros.saveUpdateColor);
		getCmbPlanCuenta().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodo().setBackground(Parametros.saveUpdateColor);
		getCmbFecha().setBackground(Parametros.saveUpdateColor);
		getTxtConcepto().setBackground(Parametros.saveUpdateColor);
		
		setUpdateMode();
		getCbEfectivo().setEnabled(true);
		this.getTxtNumero().setEnabled(false);
		this.getCmbEstado().setEnabled(true);
		this.getTxtCuenta().setEnabled(true);
		this.getBtnBuscarCuenta().setEnabled(true);
		this.getBtnAgregar().setEnabled(true);
		this.getBtnActualizar().setEnabled(true);
		this.getBtnEliminar().setEnabled(true);
		this.getTxtReferencia().setEnabled(true);
		this.getTxtGlosa().setEnabled(true);
		this.getTxtConcepto().setEnabled(true);
		this.getTxtValorDebe().setEnabled(true);
		this.getTxtValorHaber().setEnabled(true);
		this.getBtnAnadir().setLabel("Actualizar Asiento");

		/*if (asiento.getStatus().equals(ESTADO_ASIENTO))
			this.getCmbFecha().setEnabled(false);
		else
			this.getCmbFecha().setEnabled(true);*/
		
		this.getCmbFecha().setEnabled(true);
	}

	private void cargarComboPeriodo(){
		try {
			//List periodos = ContabilidadFinder.findPeriodosActivos(Parametros.getIdEmpresa());
			//List periodos = (ArrayList)SessionServiceLocator.getPeriodoSessionService().getPeriodoList();
			List periodos = (ArrayList)SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbPeriodo(),periodos);
			PeriodoModel.seleccionarPeriodoActivo(getCmbPeriodo());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuentas = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuentas);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}
	
	private void cargarComboTipoAsiento() {
		List tiposAsiento = ContabilidadFinder.findTipoAsientoByEmpresa(Parametros.getIdEmpresa());
		refreshCombo(getCmbTipoAsiento(), tiposAsiento);
	}
	
	private void cargarComboSubtipoAsiento() {
		if (getCmbTipoAsiento().getSelectedItem() != null) {
			List subtiposAsiento = ContabilidadFinder.findSubtipoAsientoByTipoAsiento(((TipoAsientoIf) getCmbTipoAsiento().getSelectedItem()).getId());
			refreshCombo(getCmbSubtipoAsiento(), subtiposAsiento);
		}
	}


	private void enableAsientoDetalleForUpdate(AsientoDetalleIf asientoDetalle) {
		try {
			cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
			idCuentaTemp = cuenta.getId();
			codigoCuentaTemp = cuenta.getCodigo();

			getTxtCuenta().setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
			String glosa = asientoDetalle.getGlosa();
			if (glosa.length() >= 150)
				glosa = glosa.substring(0,150);
			getTxtGlosa().setText(glosa);
			getTxtReferencia().setText(asientoDetalle.getReferencia());
			BigDecimal zero = new BigDecimal(0);
			if (asientoDetalle.getDebe() != null && asientoDetalle.getDebe().compareTo(zero) != 0) {
				//getTxtValorDebe().setText(NumberTextFieldDecimal.formatearTexto(asientoDetalle.getDebe().toString(), NumberTextFieldDecimal.VIEW_MODE));
				getTxtValorDebe().setText(formatoDecimal.format(asientoDetalle.getDebe().doubleValue()));
				getTxtValorHaber().setText("");
			} else if (asientoDetalle.getHaber() != null && asientoDetalle.getHaber().compareTo(zero) != 0) {
				//getTxtValorHaber().setText(NumberTextFieldDecimal.formatearTexto(asientoDetalle.getHaber().toString(), NumberTextFieldDecimal.VIEW_MODE));
				getTxtValorHaber().setText(formatoDecimal.format(asientoDetalle.getHaber().doubleValue()));				
				getTxtValorDebe().setText("");
			}

			if (asientoDetalle.getDepartamentoId() != null) {
				getCmbDepartamento().setEnabled(true);
				List departamentoList = (ArrayList) SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
				SpiritComboBoxModel cmbDepartamentoModel = new SpiritComboBoxModel(departamentoList);
				getCmbDepartamento().setModel(cmbDepartamentoModel);

				for (Object o : departamentoList) {
					DepartamentoIf departamento = (DepartamentoIf) o;
					if (departamento.getId().equals(asientoDetalle.getDepartamentoId()))
						getCmbDepartamento().getModel().setSelectedItem(departamento);
				}
			} else {
				getCmbDepartamento().removeAllItems();
				getCmbDepartamento().setSelectedItem(null);
				getCmbDepartamento().setEnabled(false);
			}

			if (asientoDetalle.getLineaId() != null) {
				getCmbLinea().setEnabled(true);
				List lineaList = (ArrayList) SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
				SpiritComboBoxModel cmbLineaModel = new SpiritComboBoxModel(lineaList);
				getCmbLinea().setModel(cmbLineaModel);

				for (Object o : lineaList) {
					LineaIf linea = (LineaIf) o;
					if (linea.getId().equals(asientoDetalle.getLineaId()))
						getCmbLinea().getModel().setSelectedItem(linea);
				}
			} else {
				getCmbLinea().removeAllItems();
				getCmbLinea().setSelectedItem(null);
				getCmbLinea().setEnabled(false);
			}

			if (asientoDetalle.getEmpleadoId() != null) {
				getCmbEmpleado().setEnabled(true);
				List empleadoList = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
				SpiritComboBoxModel cmbEmpleadoModel = new SpiritComboBoxModel(empleadoList);
				getCmbEmpleado().setModel(cmbEmpleadoModel);

				for (Object o : empleadoList) {
					EmpleadoIf empleado = (EmpleadoIf) o;
					if (empleado.getId().equals(asientoDetalle.getEmpleadoId()))
						getCmbEmpleado().getModel().setSelectedItem(empleado);
				}
			} else {
				getCmbEmpleado().removeAllItems();
				getCmbEmpleado().setSelectedItem(null);
				getCmbEmpleado().setEnabled(false);
			}

			if (asientoDetalle.getCentrogastoId() != null) {
				getCmbCentroGasto().setEnabled(true);
				List centroGastoList = (ArrayList) SessionServiceLocator.getCentroGastoSessionService().findCentroGastoByEmpresaId(Parametros.getIdEmpresa());
				SpiritComboBoxModel cmbCentroGastoModel = new SpiritComboBoxModel(centroGastoList);
				getCmbCentroGasto().setModel(cmbCentroGastoModel);

				for (Object o : centroGastoList) {
					CentroGastoIf centroGasto = (CentroGastoIf) o;
					if (centroGasto.getId().equals(asientoDetalle.getCentrogastoId()))
						getCmbCentroGasto().getModel().setSelectedItem(centroGasto);
				}
			} else {
				getCmbCentroGasto().removeAllItems();
				getCmbCentroGasto().setSelectedItem(null);
				getCmbCentroGasto().setEnabled(false);
			}

			if (asientoDetalle.getClienteId() != null) {
				getCmbCliente().setEnabled(true);
				List clientesList = new ArrayList();
				Collection corporacionCollection = SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(Parametros.getIdEmpresa());
				Iterator itCorporacionCollection = corporacionCollection.iterator();

				while (itCorporacionCollection.hasNext()) {
					CorporacionIf corporacionIf = (CorporacionIf) itCorporacionCollection.next();
					Collection clienteCollection = SessionServiceLocator.getClienteSessionService().findClienteByCorporacionId(corporacionIf.getId());
					Iterator itClienteCollection = clienteCollection.iterator();

					while (itClienteCollection.hasNext()) {
						ClienteIf clienteIf = (ClienteIf) itClienteCollection.next();
						getCmbCliente().addItem(clienteIf);
						clientesList.add(clienteIf);
					}
				}
				
				getCmbCliente().setSelectedItem(null);

				for (Object o : clientesList) {
					ClienteIf cliente = (ClienteIf) o;
					if (cliente.getId().equals(asientoDetalle.getClienteId()))
						getCmbCliente().getModel().setSelectedItem(cliente);
				}
			} else {
				getCmbCliente().removeAllItems();
				getCmbCliente().setSelectedItem(null);
				getCmbCliente().setEnabled(false);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

	}
	
	private void getSelectedCuenta(CuentaIf cuentaIf) {
		cuenta = cuentaIf;
		getTxtCuenta().setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
		handler.enableCombos(cuenta);
	}

	private void getSelectedObject(){
		this.showUpdateMode();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(asiento.getFecha());
		getCmbFecha().setCalendar(calendar);
		getTxtNumero().setText(asiento.getNumero());
		getTxtConcepto().setText(asiento.getObservacion());
		getCmbFecha().setDate(asiento.getFecha());
		List planCuentaList = ContabilidadFinder.findPlanCuentaActivo(asiento.getEmpresaId());
		refreshCombo(getCmbPlanCuenta(),planCuentaList);

		for (Object o : planCuentaList) {
			PlanCuentaIf planCuenta = (PlanCuentaIf) o;
			if (planCuenta.getId().equals(asiento.getPlancuentaId()))
				getCmbPlanCuenta().getModel().setSelectedItem(planCuenta);
		}

		List periodoList = ContabilidadFinder.findPeriodo(asiento.getEmpresaId());
		refreshCombo(getCmbPeriodo(),periodoList);

		for (Object o : periodoList) {
			PeriodoIf periodo = (PeriodoIf) o;
			if (periodo.getId().equals(asiento.getPeriodoId()))
				getCmbPeriodo().getModel().setSelectedItem(periodo);
		}

		if (asiento.getEfectivo().equals("S"))
			getCbEfectivo().setSelected(true);
		else
			getCbEfectivo().setSelected(false);

		if (asiento.getStatus().equals("P"))
			getCmbEstado().setSelectedItem("PRE-ASIENTO");
		else
			getCmbEstado().setSelectedItem("ASIENTO");
		notaAsiento = asiento.getNota();
		if (notaAsiento != null && !notaAsiento.trim().equals(""))
			getBtnAgregarNota().setText("Editar Nota");
		else
			getBtnAgregarNota().setText("Agregar Nota");
			
		try {
			if (asiento.getSubtipoasientoId() != null) {
				SubtipoAsientoIf subTipoAsientoIf = SessionServiceLocator.getSubTipoAsientoSessionService().getSubtipoAsiento(asiento.getSubtipoasientoId());
				if (subTipoAsientoIf != null) {
					TipoAsientoIf tipoAsientoIf = SessionServiceLocator.getTipoAsientoSessionService().getTipoAsiento(subTipoAsientoIf.getTipoId());
					getCmbTipoAsiento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoAsiento(), tipoAsientoIf.getId()));
					getCmbSubtipoAsiento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSubtipoAsiento(), subTipoAsientoIf.getId()));
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		try {
			DefaultTableModel tableModel = (DefaultTableModel) getTblAsiento().getModel();
			ArrayList asientoDetalleArrayList = new ArrayList();
			ArrayList asientoDetalleAnteriorArrayList = new ArrayList();
			asientoDetalleArrayList = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoIdOrderedByCodigoCuenta(asiento.getId());
			asientoDetalleAnteriorArrayList = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoIdOrderedByCodigoCuenta(asiento.getId());
			asientoDetalleColeccion = getAsientoDetalleListListFromArrayList(asientoDetalleArrayList);
			asientoDetalleAnteriorColeccion = getAsientoDetalleListListFromArrayList(asientoDetalleAnteriorArrayList);

			Iterator it = asientoDetalleColeccion.iterator();
			Map<Long, CuentaIf> cuentasMap = mapearCuentas();
			while (it.hasNext()) {
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) it.next();
				CuentaIf cuentaIf = cuentasMap.get(asientoDetalle.getCuentaId());
				cuentasLocal.add(cuentaIf);

				Vector<String> fila = new Vector<String>();
				fila.add(cuentaIf.getCodigo());
				fila.add(cuentaIf.getNombre());
				fila.add(asientoDetalle.getGlosa());
				
				if ((asientoDetalle.getDebe() != null) && !asientoDetalle.getDebe().toString().equals("0")){
					//fila.add(NumberTextFieldDecimal.formatearTexto(asientoDetalle.getDebe().toString(), NumberTextFieldDecimal.VIEW_MODE));
					fila.add(formatoDecimal.format(asientoDetalle.getDebe().doubleValue()));
				}else{
					fila.add("");
				}
				
				if ((asientoDetalle.getHaber() != null) && !asientoDetalle.getHaber().toString().equals("0")){
					//fila.add(NumberTextFieldDecimal.formatearTexto(asientoDetalle.getHaber().toString(), NumberTextFieldDecimal.VIEW_MODE));
					fila.add(formatoDecimal.format(asientoDetalle.getHaber().doubleValue()));
				}else{
					fila.add("");
				}

				tableModel.addRow(fila);
				// Creo una instancia del objeto viejo
				AsientoDetalleData asientoDetalleViejo = new AsientoDetalleData(asientoDetalle);
				// Mando a guardar en el mapa los asientos detalles encontrados para este asiento
				asientoDetallesToActualizar.put(cuentaIf.getId(), asientoDetalleViejo);
			}
			actualizarTotales();
			getTxtConcepto().grabFocus();
		} catch (UnknownServiceException e) {
			e.printStackTrace();
		} catch (ServiceInstantiationException e) {
			e.printStackTrace();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private Map<Long, CuentaIf> mapearCuentas() {
		Map<Long, CuentaIf> cuentasMap = new HashMap<Long, CuentaIf>();
		try {
			Iterator<CuentaIf> it = SessionServiceLocator.getCuentaSessionService().findCuentaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				CuentaIf cuenta = it.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear cuentas contables", SpiritAlert.ERROR);
		}
		return cuentasMap;
	}
	
	private List<AsientoDetalleIf> getAsientoDetalleListListFromArrayList(ArrayList arrayList) {
		List<AsientoDetalleIf> list = new ArrayList<AsientoDetalleIf>();
		Iterator it = arrayList.iterator();
		while (it.hasNext()) {
			Object[] asientoDetalle = (Object[]) it.next();
			list.add((AsientoDetalleIf) asientoDetalle[0]);
		}
		return list;
	}
	
	private int getSelectedRow() {
		return this.selectedRow;
	}
	
	private void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	private void fixAsientosAnticipos() {
		/*try {
			SessionServiceLocator.getCarteraSessionService().fixAsientosAnticipos();
			SpiritAlert.createAlert("Proceso realizado exitosamente", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}*/
	}
}
