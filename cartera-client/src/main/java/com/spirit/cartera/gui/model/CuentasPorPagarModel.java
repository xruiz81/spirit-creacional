package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CuentasPorPagarEJB;
import com.spirit.cartera.gui.panel.JPCuentasPorPagar;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class CuentasPorPagarModel extends JPCuentasPorPagar {
	private static String NOMBRE_MENU_CUENTAS_POR_PAGAR = "CUENTAS POR PAGAR";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private List<CuentaPorPagarData> cuentasPorPagarColeccion = new ArrayList<CuentaPorPagarData>();
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria proveedorCriteria;
	private DefaultTableModel tableModel;
	ClienteIf proveedor = null;
	double totalSaldoCuentasPorPagar;
	//java.sql.Date fechaCorteSeleccionada;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	Map documentosMap = new HashMap();
	TipoDocumentoIf tipoDocumentoCompraPorReembolso = null;
	Double saldoInicialPorPagar = 0D;
	protected ClienteOficinaIf clienteOficinaIf;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	
	
	public CuentasPorPagarModel() {
		getTblCuentasPorPagar().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initKeyListeners();
		showSaveMode();
		initListeners();
		cargarCombos();
		anchoColumnasTabla();
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getCbCalcularSaldoInicial().setSelected(false);
		getRbPorDiasVencidos().setSelected(true);
		
		getBtnBuscarProveedor().setText("");
		getBtnBuscarProveedor().setToolTipText("Buscar Proveedor");
		getBtnBuscarProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar Cuentas por Pagar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblCuentasPorPagar().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblCuentasPorPagar().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblCuentasPorPagar().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblCuentasPorPagar().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblCuentasPorPagar().getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		getTblCuentasPorPagar().getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		getTblCuentasPorPagar().getColumnModel().getColumn(6).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);		
		getBtnBuscarProveedor().addKeyListener(oKeyAdapterBuscarProveedores);		
		getCbTodosProveedores().addKeyListener(oKeyAdapterBtnCbTodosProveedores);
		
		getCmbFechaFinal().setNextFocusableComponent(getBtnConsultar());		
	}	
	
	
	KeyListener oKeyAdapterBtnCbTodosProveedores = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				
				if(getCbTodosProveedores().isSelected()) getCbTodosProveedores().setSelected(false); 
				else getCbTodosProveedores().setSelected(true);
				if (getCbTodosProveedores().isSelected()) {
					proveedor = null;
					getTxtProveedor().setText("");
					getCmbTipoProveedor().setSelectedItem("TODOS");
					getCmbTipoProveedor().validate();
					getCmbTipoProveedor().repaint();
				}			
			}
		}
	};	
			
			
			
			
	KeyListener oKeyAdapterBuscarProveedores = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				Long idCorporacion = 0L;
				String tipoCliente = "PR";
				String tituloVentanaBusqueda = "Proveedores";
				proveedorCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), proveedorCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					proveedor = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtProveedor().setText(proveedor.getNombreLegal());
					getCbTodosProveedores().setSelected(false);
					getCmbTipoProveedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProveedor(), proveedor.getTipoproveedorId()));
					getCmbTipoProveedor().validate();
					getCmbTipoProveedor().repaint();
				}
			}
		}
	};
	
	
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				Runnable runnable = new Runnable(){
					public void run() {
						setCursor(SpiritCursor.hourglassCursor);
						clean();		
						
						long start=System.currentTimeMillis();
						calcularSaldoPorPagarInicial();
						 //tt:3 segundos:
						cargarTabla();
					 
						long fin=System.currentTimeMillis();
						System.out.println("---------------------tiempo de CUENTAS POR PAGA122R!: "+(fin-start)/1000+" seg");
						
						
						setCursor(SpiritCursor.normalCursor);
					}
				};
				 
					Thread thread = new Thread(runnable);
					thread.start();
			 
		}
		}
		
	};
		
	private void anchoColumnasTabla() {
		getTblCuentasPorPagar().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(20);
	}
	
	//Cargo los listeners de los combos
	private void initListeners(){
		getBtnBuscarProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "PR";
				String tituloVentanaBusqueda = "Proveedores";
				proveedorCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), proveedorCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					proveedor = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtProveedor().setText(proveedor.getNombreLegal());
					getCbTodosProveedores().setSelected(false);
					getCmbTipoProveedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProveedor(), proveedor.getTipoproveedorId()));
					getCmbTipoProveedor().validate();
					getCmbTipoProveedor().repaint();
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			
			Runnable runnable = new Runnable(){
				public void run() {
					setCursor(SpiritCursor.hourglassCursor);
					clean();		
					
					long start=System.currentTimeMillis();
					
					if(getCbCalcularSaldoInicial().isSelected()){
						calcularSaldoPorPagarInicial();
					}
					cargarTabla();
				 
					long fin=System.currentTimeMillis();
					System.out.println("---------------------tiempo de CUENTAS POR PAGA122R!: "+(fin-start)/1000+" seg");
					
					
					setCursor(SpiritCursor.normalCursor);
				}
			};
			
			public void actionPerformed(ActionEvent evento) {
				Thread thread = new Thread(runnable);
				thread.start();
			}
		});
		
		getCbTodosProveedores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCbTodosProveedores().isSelected()) {
					proveedor = null;
					getTxtProveedor().setText("");
					getCmbTipoProveedor().setSelectedItem("TODOS");
					getCmbTipoProveedor().validate();
					getCmbTipoProveedor().repaint();
				}
			}
		});
		
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteOficinaIf.getDescripcion());
					getCbTodosClientes().setSelected(false);
				}
			}
		});
		
		getCbTodosClientes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosClientes().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
	}
	
	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblCuentasPorPagar().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Cuentas por Pagar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/cartera/RPCuentasPorPagarPorProveedor.jasper";					
					if(getRbPorTipoProveedor().isSelected()){
						fileName = "jaspers/cartera/RPCuentasPorPagar.jasper";
					}else if(getRbPorDiasVencidos().isSelected()){
						fileName = "jaspers/cartera/RPCuentasPorPagarPorProveedor.jasper";
					}
					
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_CUENTAS_POR_PAGAR).iterator().next();
					MenuIf menu = null;
					Iterator itmenu= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_CUENTAS_POR_PAGAR).iterator();
					if(itmenu.hasNext())  menu = (MenuIf) itmenu.next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("usuario", Parametros.getUsuario());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					//String fechaCorte = fechaActual;
					//String fechaInicial = (String) DeepCopy.copy(fechaActual);
					String fechaInicial = "";
					String fechaFinal = (String) DeepCopy.copy(fechaActual);
					
					/*if (fechaCorteSeleccionada != null) {
						String fecha = fechaCorteSeleccionada.toString();
						fechaCorte = fecha;
					}
					
					fechaCorte = fechaCorte.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaCorte.substring(5,7))).substring(0,3) + "-" + fechaCorte.substring(0,4);*/
					
					if (fechaInicialSeleccionada != null) {
						String fecha = fechaInicialSeleccionada.toString();
						fechaInicial = fecha;
					}
					
					if (fechaFinalSeleccionada != null) {
						String fecha = fechaFinalSeleccionada.toString();
						fechaFinal = fecha;
					}

					if (!fechaInicial.equals(""))
						fechaInicial = fechaInicial.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaInicial.substring(5,7))).substring(0,3) + "-" + fechaInicial.substring(0,4);
					
					fechaFinal = fechaFinal.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaFinal.substring(5,7))).substring(0,3) + "-" + fechaFinal.substring(0,4);
					parametrosMap.put("emitido", fechaEmision);
					parametrosMap.put("fechaInicial", fechaInicial);
					parametrosMap.put("fechaFinal", fechaFinal);
					parametrosMap.put("totalCuentasPorPagar", formatoDecimal.format(totalSaldoCuentasPorPagar));
					parametrosMap.put("saldoInicialPorPagar", saldoInicialPorPagar);
					
					if(clienteOficinaIf != null){
						parametrosMap.put("cliente", clienteOficinaIf.getDescripcion());
					}else{
						parametrosMap.put("cliente", "TODOS");
					}
					
					//ordeno cuentasPorPagarColeccion
					if(getRbPorDiasVencidos().isSelected()){
						Collections.sort((ArrayList)cuentasPorPagarColeccion, ordenadorCuentasPorPagarPorDiasVencidos);
					}					
					
					ReportModelImpl.processReport(fileName, parametrosMap, getCuentasPorPagarColeccion(), true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	Comparator<CuentaPorPagarData> ordenadorCuentasPorPagarPorDiasVencidos = new Comparator<CuentaPorPagarData>(){
		public int compare(CuentaPorPagarData o1, CuentaPorPagarData o2) {
			Integer dia1 = Integer.valueOf(o1.getDiasVencidos());
			Integer dia2 = Integer.valueOf(o2.getDiasVencidos());
			//return o1.getDiasVencidos().compareTo(o2.getDiasVencidos());
			return dia2.compareTo(dia1);
		}		
	};
	
	public void refresh() {
		cargarComboTipoProveedor();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void clean() {
		getCuentasPorPagarColeccion().clear();
		
		saldoInicialPorPagar = 0D;		
		limpiarTabla(getTblCuentasPorPagar());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getBtnBuscarProveedor().grabFocus();
	}

	private void cargarCombos() {
		cargarComboTipoProveedor();
	}
		
	private void cargarComboTipoProveedor() {
		try {
			Map parameterMap = new HashMap();
			List tiposProveedorList = (List) SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			tiposProveedorList.add("TODOS");
			refreshCombo(getCmbTipoProveedor(), tiposProveedorList);
			getCmbTipoProveedor().setSelectedItem("TODOS");
			getCmbTipoProveedor().validate();
			getCmbTipoProveedor().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarTabla() {
		try {
			HashMap<Long, Long> clientesOficinaMap = new HashMap<Long, Long>();
			//mapa de clientes oficina de este cliente
			if(clienteOficinaIf != null){
				Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteOficinaIf.getClienteId());
				Iterator clientesOficinaIt = clientesOficina.iterator();
				while(clientesOficinaIt.hasNext()){
					ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf)clientesOficinaIt.next();						
					clientesOficinaMap.put(clienteOficinaIf.getId(), clienteOficinaIf.getId());
				}
			}
			
			ArrayList cuentasPorPagarList = new ArrayList();
			ArrayList cuentasPorPagarAdicionalesList = new ArrayList();
			ArrayList carteraAfectaCuentasPorPagarList = new ArrayList();
			Long idProveedor = 0L;
			Long idTipoProveedor = 0L;
			//java.util.Date fecha = (Date) getCmbFechaCorte().getDate();
			java.util.Date fechaInicialCombo = (Date) getCmbFechaInicial().getDate();
			java.util.Date fechaFinalCombo = (Date) getCmbFechaFinal().getDate();
			//java.sql.Date fechaCorte = null;
			java.sql.Date fechaInicial = null;
			java.sql.Date fechaFinal = null;
			totalSaldoCuentasPorPagar = 0D;
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "COR");
			Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap).iterator();
			if (it.hasNext())	tipoDocumentoCompraPorReembolso = (TipoDocumentoIf) it.next();
			
			if (fechaFinalCombo == null)				
				fechaFinalCombo = new java.util.Date();
			
			if (fechaInicialCombo != null) {
				this.fechaInicialSeleccionada = new java.sql.Date(fechaInicialCombo.getTime());
				fechaInicial = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fechaInicialCombo.getTime())));
			}
			
			this.fechaFinalSeleccionada = new java.sql.Date(fechaFinalCombo.getTime());
			fechaFinal = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fechaFinalCombo.getTime())));
			
			if(proveedor != null)				
				idProveedor = proveedor.getId();
			
			if (getCmbTipoProveedor().getSelectedItem().toString().compareTo("TODOS") != 0)
				idTipoProveedor = ((TipoProveedorIf) getCmbTipoProveedor().getSelectedItem()).getId();
			
			cuentasPorPagarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagar(Parametros.getIdEmpresa(), idProveedor, idTipoProveedor, fechaInicial, fechaFinal, true);
			Iterator moduloIt = SessionServiceLocator.getModuloSessionService().findModuloByCodigo("CART").iterator();
			Long idModulo = 0L;
			
			if (moduloIt.hasNext())
				idModulo = ((ModuloIf) moduloIt.next()).getId();
			
			cuentasPorPagarAdicionalesList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagarAdicionales(Parametros.getIdEmpresa(), idProveedor, idTipoProveedor, idModulo, fechaInicial, fechaFinal);
			cuentasPorPagarList = agregarCuentasPorPagarAdicionales(cuentasPorPagarList, cuentasPorPagarAdicionalesList);
			
			//System.out.println("en cargaR TABLA: carteraDetallesCuentasPorPagarMap:"+fechaFinal);
			
			Map<Long,Long[]> carteraDetallesCuentasPorPagarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);			
			documentosMap = mapearDocumentos();
						 
			parameterMap = new HashMap();
			parameterMap.put("cartera", "S");
			Map mapArray = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());		
			
			  
			tableModel = (DefaultTableModel) getTblCuentasPorPagar().getModel();
			if(cuentasPorPagarList.size()>0){
				Iterator cuentasPorPagarIterator = cuentasPorPagarList.iterator();	
				while (cuentasPorPagarIterator.hasNext()) {
					CuentasPorPagarEJB cuentaPorPagar = (CuentasPorPagarEJB) cuentasPorPagarIterator.next();
					
					//filtrado por cliente			
					
					boolean compraDelCliente = false;
					
					if(clienteOficinaIf != null){
						
						if(getCbCompararTodasOficinas().isSelected()){
							Iterator clientesOficinaMapIt = clientesOficinaMap.keySet().iterator();
							while(clientesOficinaMapIt.hasNext()){
								Long clienteOficinaId = (Long)clientesOficinaMapIt.next();
								
								if(compraDelCliente == false){
									compraDelCliente = compararCliente(clienteOficinaId, cuentaPorPagar.getCompraId());
								}
							}
						}else{
							compraDelCliente = compararCliente(clienteOficinaIf.getId(), cuentaPorPagar.getCompraId());
						}
						
						if(compraDelCliente == true){
							System.out.println("a");
						}
					}
					//si no hay cliente por el cual filtrar entonces compraDelCliente va a ser true
					else{
						compraDelCliente = true;
					}			
					
					Vector<Object> fila = new Vector<Object>();
					
					if(compraDelCliente && agregarFilasTabla(cuentaPorPagar, fila, mapArray, carteraDetallesCuentasPorPagarMap, compraDelCliente))
						tableModel.addRow(fila);
				
				}				
				getTxtTotalSaldoCuentasPorPagar().setText(formatoDecimal.format(totalSaldoCuentasPorPagar));
			} 
			
			if (getTblCuentasPorPagar().getRowCount() <= 0)
				SpiritAlert.createAlert("No existen cuentas pendientes de pago !!", SpiritAlert.INFORMATION);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la consulta !!", SpiritAlert.ERROR);
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la consulta !!", SpiritAlert.ERROR);
		}
	}
	
	private boolean compararCliente(Long clienteOficinaId, Long compraId){
		
		boolean compraDelCliente = false;
		
		try{
			Collection ordenesAsociada = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compraId);
			Iterator ordenesAsociadaIt = ordenesAsociada.iterator();
			while(ordenesAsociadaIt.hasNext()){
				OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadaIt.next();
				//si la compra viene de una orden de compra
				if(ordenAsociada.getTipoOrden().equals("OC")){
					//busco por el presupuesto detalle
					Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenAsociada.getOrdenId());
					Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
					while(presupuestosDetalleIt.hasNext()){
						PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
						PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
						if(presupuesto.getClienteOficinaId() != null){
							if(presupuesto.getClienteOficinaId().compareTo(clienteOficinaId) == 0){
								compraDelCliente = true;
							}
						}else{
							OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
							OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
							if(ordenTrabajo.getClienteoficinaId().compareTo(clienteOficinaId) == 0){
								compraDelCliente = true;
							}
						}									
					}
					//si no lo encontre busco por solicitud de compra
					if(!compraDelCliente){
						OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
						if(ordenCompra != null){
							SolicitudCompraIf solicitudCompra = SessionServiceLocator.getSolicitudCompraSessionService().getSolicitudCompra(ordenCompra.getSolicitudcompraId());
							if(solicitudCompra.getTipoReferencia().equals("P")){
								Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(solicitudCompra.getReferencia());
								Iterator presupuestosIt = presupuestos.iterator();
								while(presupuestosIt.hasNext()){
									PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
									if(presupuesto.getClienteOficinaId() != null){
										if(presupuesto.getClienteOficinaId().compareTo(clienteOficinaId) == 0){
											compraDelCliente = true;
										}
									}else{
										OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
										OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
										if(ordenTrabajo.getClienteoficinaId().compareTo(clienteOficinaId) == 0){
											compraDelCliente = true;
										}
									}												
								}
							}
						}								
					}
				}
				//si la compra viene de una orden de medio
				else if(ordenAsociada.getTipoOrden().equals("OM")){
					OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
					if(ordenMedio.getClienteOficinaId().compareTo(clienteOficinaId) == 0){
						compraDelCliente = true;
					}
				}
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la consulta !!", SpiritAlert.ERROR);
		}	
		
		return compraDelCliente;
	}
	
	private void calcularSaldoPorPagarInicial() {
		try {
			
			Long idProveedor = 0L;
			java.sql.Date fechaInicial = null;
			java.sql.Date fechaFinal = null;
			Long idTipoProveedor = 0L;
			
			if(proveedor != null)idProveedor = proveedor.getId();
			
			java.util.Date fechaInicialCombo = (Date) getCmbFechaInicial().getDate();
			Calendar calFechaInicial = new GregorianCalendar();
			calFechaInicial.setTime(fechaInicialCombo!=null ? fechaInicialCombo : new Date());
			calFechaInicial.add(Calendar.DATE, -1);
			fechaFinal =  new java.sql.Date(calFechaInicial.getTime().getTime());
			
			if (getCmbTipoProveedor().getSelectedItem().toString().compareTo("TODOS") != 0)
				idTipoProveedor = ((TipoProveedorIf) getCmbTipoProveedor().getSelectedItem()).getId();
			
			ArrayList cuentasPorPagarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagar(Parametros.getIdEmpresa(), idProveedor, idTipoProveedor, fechaInicial, fechaFinal, true);
			
			Iterator moduloIt = SessionServiceLocator.getModuloSessionService().findModuloByCodigo("CART").iterator();
			Long idModulo = 0L;
			if (moduloIt.hasNext())				idModulo = ((ModuloIf) moduloIt.next()).getId();
			
			ArrayList cuentasPorPagarAdicionalesList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagarAdicionales(Parametros.getIdEmpresa(), idProveedor, idTipoProveedor, idModulo, fechaInicial, fechaFinal);						
			cuentasPorPagarList = agregarCuentasPorPagarAdicionales(cuentasPorPagarList, cuentasPorPagarAdicionalesList);
		 
			Map parameterMap = new HashMap();
			parameterMap.put("cartera", "S");
			Map mapArray = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());		
			
			Map carteraDetallesCuentasPorPagarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);
		
			if(cuentasPorPagarList.size()>0){
				Iterator cuentasPorPagarIterator = cuentasPorPagarList.iterator();	
				while (cuentasPorPagarIterator.hasNext()) {
					CuentasPorPagarEJB cuentaPorPagar = (CuentasPorPagarEJB) cuentasPorPagarIterator.next();
					//CarteraIf cartera = (CarteraIf) cuentaPorPagar[0];
					double saldoCuentaPorPagar = Utilitarios.redondeoValor(calcularSaldoCartera(cuentaPorPagar, mapArray, carteraDetallesCuentasPorPagarMap));
					saldoInicialPorPagar += saldoCuentaPorPagar;
				}
			}
			saldoInicialPorPagar = Utilitarios.redondeoValor(saldoInicialPorPagar);			
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al obtener Saldo Inicial !!", SpiritAlert.ERROR);
		}
	}
	
	private ArrayList agregarCuentasPorPagarAdicionales(ArrayList cuentasPorPagarList, ArrayList cuentasPorPagarAdicionalesList) {
		Iterator cuentasPorPagarAdicionalesIt = cuentasPorPagarAdicionalesList.iterator();
		while (cuentasPorPagarAdicionalesIt.hasNext()) {
			boolean agregado = false;
			Object[] cuentaPorPagarAdicionalObject = (Object[]) cuentasPorPagarAdicionalesIt.next();
			CarteraIf cartera = (CarteraIf) cuentaPorPagarAdicionalObject[0];
			ClienteIf proveedor = (ClienteIf) cuentaPorPagarAdicionalObject[1];
			TipoProveedorIf tipoProveedor = (TipoProveedorIf) cuentaPorPagarAdicionalObject[2];
			CuentasPorPagarEJB cuentaPorPagarAdicional = setCuentaPorPagarAdicional(cartera, proveedor, tipoProveedor);
			for (int i=0; i<cuentasPorPagarList.size(); i++) {
				CuentasPorPagarEJB cuentaPorPagar = (CuentasPorPagarEJB) cuentasPorPagarList.get(i);
				if (cuentaPorPagar.getTipoProveedorId().compareTo(tipoProveedor.getId()) == 0 && cuentaPorPagar.getProveedorId().compareTo(proveedor.getId()) == 0) {
					cuentasPorPagarList.add(i, cuentaPorPagarAdicional);
					agregado = true;
					break;
				}
			}
			
			if (!agregado)
				cuentasPorPagarList.add(cuentaPorPagarAdicional);
		}		
		return cuentasPorPagarList;
	}

	private CuentasPorPagarEJB setCuentaPorPagarAdicional(CarteraIf cartera, ClienteIf proveedor, TipoProveedorIf tipoProveedor) {
		CuentasPorPagarEJB cuentaPorPagarAdicional = new CuentasPorPagarEJB();
		cuentaPorPagarAdicional.setId(cartera.getId());
		cuentaPorPagarAdicional.setCarteraId(cartera.getId());
		cuentaPorPagarAdicional.setCodigo(cartera.getCodigo());
		cuentaPorPagarAdicional.setOficinaId(cartera.getOficinaId());
		cuentaPorPagarAdicional.setFechaCompra(new java.sql.Timestamp(cartera.getFechaEmision().getTime()));
		cuentaPorPagarAdicional.setFechaEmision(new java.sql.Timestamp(cartera.getFechaEmision().getTime()));
		cuentaPorPagarAdicional.setTipodocumentoId(cartera.getTipodocumentoId());
		cuentaPorPagarAdicional.setCompraId(cartera.getReferenciaId());
		cuentaPorPagarAdicional.setReferenciaId(cartera.getReferenciaId());
		cuentaPorPagarAdicional.setPreimpreso(cartera.getPreimpreso());
		cuentaPorPagarAdicional.setTipoProveedorId(tipoProveedor.getId());
		cuentaPorPagarAdicional.setCodigoTipoProveedor(tipoProveedor.getCodigo());
		cuentaPorPagarAdicional.setTipoProveedor(tipoProveedor.getNombre());
		cuentaPorPagarAdicional.setIdentificacion(proveedor.getIdentificacion());
		cuentaPorPagarAdicional.setProveedorId(proveedor.getId());
		cuentaPorPagarAdicional.setProveedorOficinaId(cartera.getClienteoficinaId());
		cuentaPorPagarAdicional.setRazonSocial(proveedor.getRazonSocial());
		cuentaPorPagarAdicional.setComentario(cartera.getComentario());
		cuentaPorPagarAdicional.setObservacion(cartera.getComentario());
		cuentaPorPagarAdicional.setValor(cartera.getValor());
		cuentaPorPagarAdicional.setSaldo(cartera.getSaldo());
		return cuentaPorPagarAdicional;
	}
	
	private Map mapearCarteraDetallesByFechaInicialAndFechaFinal(java.sql.Date fechaInicial, java.sql.Date fechaFinal) {
		Map carteraDetallesMap = new HashMap();
		try {
	
			carteraDetallesMap=SessionServiceLocator.getCarteraDetalleSessionService().datosMapa(fechaInicial, fechaFinal, Parametros.getIdEmpresa());
			 
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return carteraDetallesMap;
	}
	
	private Map mapearDocumentos() {
		Map documentosMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			
			while (it.hasNext()) {
				DocumentoIf documento = (DocumentoIf) it.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	private boolean agregarFilasTabla(CuentasPorPagarEJB cuentaPorPagarObject, Vector<Object> fila, Map carteraAfectaCuentasPorPagarList, Map<Long,Long[]> carteraDetallesCuentasPorPagarMap, boolean compraDelCliente){
		/*CarteraIf cartera = (CarteraIf) cuentaPorPagarObject[0];
		CompraIf compra = (CompraIf) cuentaPorPagarObject[1];
		ClienteIf proveedor = (ClienteIf) cuentaPorPagarObject[2];*/
		Calendar now = Calendar.getInstance();
		java.util.Date fechaActual = now.getTime();
		//long diasVencidos = Utilitarios.obtenerDiferenciaDias((compra != null)?compra.getFecha():cartera.getFechaEmision(), new java.sql.Date(fechaActual.getYear(), fechaActual.getMonth(), fechaActual.getDate()));
		long diasVencidos = Utilitarios.obtenerDiferenciaDias(new java.sql.Date(cuentaPorPagarObject.getFechaEmision().getTime()), new java.sql.Date(fechaActual.getTime()));
		//TipoProveedorIf tipoProveedor = (TipoProveedorIf) cuentaPorPagarObject[3];
		CuentaPorPagarData cuentaPorPagar = new CuentaPorPagarData();
		String razonSocial = cuentaPorPagarObject.getRazonSocial();
		fila.add(razonSocial);
		if (razonSocial.length() > 40)
			razonSocial = razonSocial.substring(0, 40);
		cuentaPorPagar.setRazonSocial(razonSocial);
		String ruc = cuentaPorPagarObject.getIdentificacion();
		cuentaPorPagar.setRuc(ruc);
		fila.add(ruc);
		String numeroFactura = cuentaPorPagarObject.getPreimpreso();
		cuentaPorPagar.setNumeroFactura(numeroFactura);
		fila.add(numeroFactura);
		double valor = cuentaPorPagarObject.getValor().doubleValue();
		String valorTotal = formatoDecimal.format(valor);
		cuentaPorPagar.setValorTotal(valorTotal);
		fila.add(valorTotal);
		double totalRetencion = calcularTotalRetencion(cuentaPorPagarObject, carteraAfectaCuentasPorPagarList, carteraDetallesCuentasPorPagarMap);
		String retencion = formatoDecimal.format(totalRetencion);
		cuentaPorPagar.setRetefuente(retencion);
		fila.add(retencion);
		double saldoCuentaPorPagar = Utilitarios.redondeoValor(calcularSaldoCartera(cuentaPorPagarObject, carteraAfectaCuentasPorPagarList, carteraDetallesCuentasPorPagarMap));
		String saldo = formatoDecimal.format(saldoCuentaPorPagar);
		
		/*if (cartera.getTipodocumentoId().compareTo(tipoDocumentoCompraPorReembolso.getId()) == 0 && saldoCuentaPorPagar != 0D && tipoProveedor.getCodigo().equals("PR"))
			totalSaldoCuentasPorPagar += saldoCuentaPorPagar - valor;
		else*/
			totalSaldoCuentasPorPagar += saldoCuentaPorPagar;
		
		cuentaPorPagar.setSaldo(saldo);
		fila.add(saldo);
		String fecha = cuentaPorPagarObject.getFechaEmision().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaCompra = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		cuentaPorPagar.setFecha(fechaCompra);
		fila.add(fechaCompra);
		cuentaPorPagar.setTipoProveedor(cuentaPorPagarObject.getTipoProveedor());
		cuentaPorPagar.setCodigoTipoProveedor(cuentaPorPagarObject.getCodigoTipoProveedor());
		cuentaPorPagar.setTipoProveedorId(String.valueOf(cuentaPorPagarObject.getTipoProveedorId()));
		cuentaPorPagar.setProveedorId(String.valueOf(cuentaPorPagarObject.getProveedorId()));
		String detalle = "";
		if(cuentaPorPagarObject != null && cuentaPorPagarObject.getObservacion() != null){
			detalle = cuentaPorPagarObject.getObservacion();
		}else if(cuentaPorPagarObject != null && cuentaPorPagarObject.getComentario() != null){
			detalle = cuentaPorPagarObject.getComentario();
		}
		
		if (detalle.length() > 38)
			detalle = detalle.substring(0, 38);
		
		//cuando el reporte es por dias vencidos en detalle solo aparece el nombre del proveedor
		if(getRbPorDiasVencidos().isSelected()){
			detalle = cuentaPorPagarObject.getRazonSocial();
		}
		
		cuentaPorPagar.setDetalle(detalle);
		if (cuentaPorPagarObject.getTipodocumentoId().compareTo(tipoDocumentoCompraPorReembolso.getId()) == 0)
			cuentaPorPagar.setReembolso("R");
		 
		
		fila.add(String.valueOf(diasVencidos));
		cuentaPorPagar.setDiasVencidos(String.valueOf(diasVencidos));
		
		if (compraDelCliente && Utilitarios.redondeoValor(saldoCuentaPorPagar) != 0D)
			getCuentasPorPagarColeccion().add(cuentaPorPagar);
		else
			return false;
		
		return true;
	}
	
	private double calcularSaldoCartera(CuentasPorPagarEJB cartera, Map mapArray, Map<Long,Long[]> carteraDetallesCuentasPorPagarMap) {
		double saldo = cartera.getValor().doubleValue();
		
		 
		Iterator mapaMesValorIt2 = mapArray.keySet().iterator();
		while(mapaMesValorIt2.hasNext()){
			Long idd = (Long)mapaMesValorIt2.next();
			Object[] rentaBases = (Object[])mapArray.get(idd);
			
			Long[] idDetalledoc= carteraDetallesCuentasPorPagarMap.get(rentaBases[0]);
			
			boolean flag=false;
			if(idDetalledoc!=null && idDetalledoc[0]!=null && idDetalledoc[0].compareTo(cartera.getId())== 0) flag=true;
			
			BigDecimal bd= (BigDecimal)rentaBases[2];
			if (flag)	saldo -= bd.doubleValue();
			
		}
		
		
		
		/*
		 Iterator it = carteraAfectaCuentasPorPagarList.iterator();
		 while (it.hasNext()) {
			CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) it.next();
			  
			Long[] idDetalledoc= carteraDetallesCuentasPorPagarMap.get(carteraAfecta.getCarteraafectaId()); 
			
			boolean flag=false;
			if(idDetalledoc!=null && idDetalledoc[0]!=null && idDetalledoc[0].compareTo(cartera.getId())== 0) flag=true;
			 	
			if (flag)
				saldo -= carteraAfecta.getValor().doubleValue();
		}*/
		
		return saldo;
	}
	
	private double calcularTotalRetencion(CuentasPorPagarEJB cartera, Map mapArray, Map<Long,Long[]> carteraDetallesCuentasPorPagarMap) {
		double retencion = 0D;  
		
		Iterator mapaMesValorIt2 = mapArray.keySet().iterator();
		while(mapaMesValorIt2.hasNext()){
			Long idd = (Long)mapaMesValorIt2.next();
			Object[] rentaBases = (Object[])mapArray.get(idd);
			 
			 						
			
			Long[] idDetalledoc= carteraDetallesCuentasPorPagarMap.get(rentaBases[0]); 
			Long[] idAfectaDoc= carteraDetallesCuentasPorPagarMap.get(rentaBases[1]);
			
			DocumentoIf documento = null;
			if(idAfectaDoc!=null && idAfectaDoc[1]!=null)	documento = (DocumentoIf) documentosMap.get(idAfectaDoc[1]);		
				
			boolean flag=false;
			if(idDetalledoc!=null && idDetalledoc[0]!=null && idDetalledoc[0].compareTo(cartera.getId())== 0) flag=true;
						
			if (documento != null && (documento.getRetencionRenta().equals("S") || documento.getRetencionIva().equals("S")) && flag)
				{
				BigDecimal bd= (BigDecimal)rentaBases[2];
				retencion += bd.doubleValue();
				
				}
		}
		
		return retencion;
	}

	public List<CuentaPorPagarData> getCuentasPorPagarColeccion() {
		return cuentasPorPagarColeccion;
	}

	public void setCuentasPorPagarColeccion(
			List<CuentaPorPagarData> cuentasPorPagarColeccion) {
		this.cuentasPorPagarColeccion = cuentasPorPagarColeccion;
	}
}