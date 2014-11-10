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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CuentasPorCobrarEJB;
import com.spirit.cartera.gui.panel.JPCuentasPorCobrar;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class CuentasPorCobrarModel extends JPCuentasPorCobrar {
	
	private static final long serialVersionUID = 6237773984344363476L;
	private static String NOMBRE_MENU_CUENTAS_POR_COBRAR = "CUENTAS POR COBRAR";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<CuentaPorCobrarData> cuentasPorCobrarColeccion = new Vector<CuentaPorCobrarData>();
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private DefaultTableModel tableModel;
	ClienteIf cliente = null;
	double totalSaldoCuentasPorCobrar;
	//java.sql.Date fechaCorteSeleccionada;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	private Map documentosMap = new HashMap();
	private Map tiposDocumentosMap = new HashMap();
	
	private Double saldoInicialPorCobrar = 0D;
	
	public CuentasPorCobrarModel() {
		//getTblCuentasPorCobrar().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		iniciarComponentes();
		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponent(this);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);		
		getBtnBuscarCliente().addKeyListener(oKeyAdapterBtnBuscar);
		getCmbFechaFinal().setNextFocusableComponent(getBtnConsultar());		
		getCbEstadoCuenta().addKeyListener(oKeyAdapterBtnEstadoCuenta);
		getCbTodos().addKeyListener(oKeyAdapterBtnCbTodos);
	}
	 
	KeyListener oKeyAdapterBtnEstadoCuenta = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				
				if (getCbEstadoCuenta().isSelected()) getCbEstadoCuenta().setSelected(false);
				else getCbEstadoCuenta().setSelected(true);

				if (getCbEstadoCuenta().isSelected()) {
					getCbTodos().setSelected(false);
					getCbTodos().setEnabled(false);
				} else {
					getCbTodos().setEnabled(true);
				}
			}
		}
	};
	
	KeyListener oKeyAdapterBtnCbTodos = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				 
				if(getCbTodos().isSelected()) getCbTodos().setSelected(false); 
				else getCbTodos().setSelected(true);
				
				if (getCbTodos().isSelected()) {
					cliente = null;
					getTxtCliente().setText("");
					getCbEstadoCuenta().setSelected(false);
					getCbEstadoCuenta().setEnabled(false);
				} else {
					getCbEstadoCuenta().setEnabled(true);
				}
				
				 
			}
		}
	};
	
	KeyListener oKeyAdapterBtnBuscar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				 
				
					Long idCorporacion = 0L;
					String tipoCliente = "CL";
					String tituloVentanaBusqueda = "Clientes";
					clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(300);
					anchoColumnas.add(300);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						cliente = (ClienteIf) popupFinder.getElementoSeleccionado();
						getTxtCliente().setText(cliente.getNombreLegal());
						getCbTodos().setSelected(false);
					}
				 
			}
		}
	};
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				System.out.println("kipppppppppppppppppppppppppppppppppp");
				
				Runnable runnable =  new Runnable(){
					public void run() {
						setCursor(SpiritCursor.hourglassCursor);
						clean();
						long start=System.currentTimeMillis();					
						calcularSaldoInicialPorCobrar();
						cargarTabla();
						setCursor(SpiritCursor.normalCursor);
						
						long fin=System.currentTimeMillis();
						System.out.println("---------------------tiempo de todo proceso: "+(fin-start)/1000+" seg");
						
					}
				};
				
				Thread thread =  new Thread(runnable);
				thread.start();
		}
		}
		
	};
	
	
	
	
	 
	
	private void iniciarComponentes() {
		getBtnBuscarCliente().setText("");
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar Cuentas por Cobrar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblCuentasPorCobrar().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblCuentasPorCobrar().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblCuentasPorCobrar().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblCuentasPorCobrar().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblCuentasPorCobrar().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblCuentasPorCobrar().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblCuentasPorCobrar().getColumnModel().getColumn(0).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		getTblCuentasPorCobrar().getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		getTblCuentasPorCobrar().getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);
		getCmbFechaCorte().setLocale(Utilitarios.esLocale);
		getCmbFechaCorte().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCorte().setEditable(false);
		getCmbFechaCorte().setShowNoneButton(false);
		getCmbFechaCorte().setCalendar(Calendar.getInstance());
	}
		
	private void anchoColumnasTabla() {
		getTblCuentasPorCobrar().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(30);
	}
	
	//Cargo los listeners de los combos
	private void initListeners(){
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "CL";
				String tituloVentanaBusqueda = "Clientes";
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cliente = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(cliente.getNombreLegal());
					getCbTodos().setSelected(false);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			Runnable runnable =  new Runnable(){
				public void run() {
					setCursor(SpiritCursor.hourglassCursor);
					clean();
					long start=System.currentTimeMillis();					
					calcularSaldoInicialPorCobrar();
					cargarTabla();
					setCursor(SpiritCursor.normalCursor);
					
					long fin=System.currentTimeMillis();
					System.out.println("---------------------tiempo de todo proceso: "+(fin-start)/1000+" seg");
					
				}
			};
			
			public void actionPerformed(ActionEvent evento) {
				Thread thread =  new Thread(runnable);
				thread.start();
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCbTodos().isSelected()) {
					cliente = null;
					getTxtCliente().setText("");
					getCbEstadoCuenta().setSelected(false);
					getCbEstadoCuenta().setEnabled(false);
				} else {
					getCbEstadoCuenta().setEnabled(true);
				}
			}
		});
		
		getCbEstadoCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCbEstadoCuenta().isSelected()) {
					getCbTodos().setSelected(false);
					getCbTodos().setEnabled(false);
				} else {
					getCbTodos().setEnabled(true);
				}
			}
		});
	}
	
	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblCuentasPorCobrar().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Cuentas por Cobrar?";
				if (getCbEstadoCuenta().isSelected())
					mensaje = "¿Desea generar el Estado de Cuenta del cliente?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/cartera/RPCuentasPorCobrar.jasper";
					if (getCbEstadoCuenta().isSelected())
						fileName = "jaspers/cartera/RPEstadoCuentasPorCobrar.jasper";
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_CUENTAS_POR_COBRAR).iterator().next();
					MenuIf menu = null;
					Iterator itmenu= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_CUENTAS_POR_COBRAR).iterator();
					if(itmenu.hasNext())  menu = (MenuIf) itmenu.next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase()));
					parametrosMap.put("usuario", Parametros.getUsuario());
					EmpleadoIf empleado = null;
					TipoEmpleadoIf tipoEmpleado = null;
					Map parameterMap = new HashMap();
					parameterMap.put("empresaId", Parametros.getIdEmpresa());
					parameterMap.put("codigo", "FURECC");
					Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						ParametroEmpresaIf usuarioFirma = (ParametroEmpresaIf) it.next();
						parameterMap.remove("codigo");
						parameterMap.put("usuario", usuarioFirma.getValor());
						it = SessionServiceLocator.getUsuarioSessionService().findUsuarioByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							UsuarioIf usuario = (UsuarioIf) it.next();
							empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
							tipoEmpleado = SessionServiceLocator.getTipoEmpleadoSessionService().getTipoEmpleado(empleado.getTipoempleadoId());
						}
					}
					
					parametrosMap.put("firma", empleado!=null?empleado.getNombres() + " " + empleado.getApellidos():"");
					parametrosMap.put("cargoFirma", tipoEmpleado!=null?tipoEmpleado.getNombre():"");
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					String fechaInicial = "";
					String fechaFinal = (String) DeepCopy.copy(fechaActual);
					
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
					String fechaCorte = Utilitarios.getFechaCortaUppercase(getCmbFechaCorte().getDate());
					parametrosMap.put("emitido", fechaEmision);
					parametrosMap.put("fechaInicial", fechaInicial);
					parametrosMap.put("fechaFinal", fechaFinal);
					parametrosMap.put("fechaCorte", fechaCorte);
					parametrosMap.put("totalCuentasPorCobrar", formatoDecimal.format(totalSaldoCuentasPorCobrar));
					parametrosMap.put("saldoInicialPorCobrar", saldoInicialPorCobrar);
					ReportModelImpl.processReport(fileName, parametrosMap, getCuentasPorCobrarColeccion(), true);
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
	
	public void refresh() {

	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void clean() {
		getCuentasPorCobrarColeccion().clear();
		
		saldoInicialPorCobrar = 0D;
		
		limpiarTabla(getTblCuentasPorCobrar());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getBtnBuscarCliente().grabFocus();
	}

	private void cargarTabla() {
		try {
			documentosMap = mapearDocumentos();
			tiposDocumentosMap = mapearTiposDocumentos();
			ArrayList cuentasPorCobrarList = new ArrayList();
			ArrayList cuentasPorCobrarAdicionalesList = new ArrayList();
			ArrayList carteraAfectaCuentasPorCobrarList = new ArrayList();
			Long idCliente = 0L;
			java.util.Date fechaInicialSeleccionada = (Date) getCmbFechaInicial().getDate();
			java.util.Date fechaFinalSeleccionada = (Date) getCmbFechaFinal().getDate();
			java.sql.Date fechaInicial = null;
			java.sql.Date fechaFinal = null;
			totalSaldoCuentasPorCobrar = 0D;
			
			if (fechaFinalSeleccionada == null)
				fechaFinalSeleccionada = new java.util.Date();
			
			if (fechaInicialSeleccionada != null) {
				this.fechaInicialSeleccionada = new java.sql.Date(fechaInicialSeleccionada.getYear(), fechaInicialSeleccionada.getMonth(), fechaInicialSeleccionada.getDate());
				fechaInicial = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fechaInicialSeleccionada.getTime())));
			}
			
			this.fechaFinalSeleccionada = new java.sql.Date(fechaFinalSeleccionada.getYear(), fechaFinalSeleccionada.getMonth(), fechaFinalSeleccionada.getDate());
			fechaFinal = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fechaFinalSeleccionada.getTime())));
			
			if(cliente != null)
				idCliente = cliente.getId();

			cuentasPorCobrarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorCobrar(Parametros.getIdEmpresa(), idCliente, fechaInicial, fechaFinal);
			Iterator moduloIt = SessionServiceLocator.getModuloSessionService().findModuloByCodigo("CART").iterator();
			Long idModulo = 0L;
			if (moduloIt.hasNext())
				idModulo = ((ModuloIf) moduloIt.next()).getId();
			cuentasPorCobrarAdicionalesList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorCobrarAdicionales(Parametros.getIdEmpresa(), idCliente, idModulo, fechaInicial, fechaFinal);
			cuentasPorCobrarList = agregarCuentasPorCobrarAdicionales(cuentasPorCobrarList, cuentasPorCobrarAdicionalesList);
			//Map carteraDetallesCuentasPorCobrarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);
			
			Map<Long,Long[]> carteraDetallesCuentasPorCobrarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);
			
			/*
			Map parameterMap = new HashMap();
			parameterMap.put("cartera", "S");
			carteraAfectaCuentasPorCobrarList = (ArrayList) SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());
			*/
			
			Map parameterMap = new HashMap();
			parameterMap.put("cartera", "S");
			Map mapArray = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());		
			
			
			if(cuentasPorCobrarList.size()>0){
				Iterator cuentasPorCobrarIterator = cuentasPorCobrarList.iterator();	
				while (cuentasPorCobrarIterator.hasNext()) {
					CuentasPorCobrarEJB cuentaPorCobrar = (CuentasPorCobrarEJB) cuentasPorCobrarIterator.next();
					tableModel = (DefaultTableModel) getTblCuentasPorCobrar().getModel();
					Vector<Object> fila = new Vector<Object>();
					if (agregarFilasTabla(cuentaPorCobrar, fila, mapArray, carteraDetallesCuentasPorCobrarMap))
						tableModel.addRow(fila);
				}
				
				getTxtTotalSaldoCuentasPorCobrar().setText(formatoDecimal.format(totalSaldoCuentasPorCobrar));
			} 
			
			if (getTblCuentasPorCobrar().getRowCount() <= 0)
				SpiritAlert.createAlert("No existen cuentas pendientes de cobro!", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en consulta !!", SpiritAlert.ERROR);
		}
	}
	
	private void calcularSaldoInicialPorCobrar(){
		try {
		
			 
			
			Long idCliente = 0L;
			java.sql.Date fechaInicial = null;
			java.sql.Date fechaFinal = null;
			
			if(cliente != null)				idCliente = cliente.getId();
			
			Date fechaComboInicial = getCmbFechaInicial().getDate();
			Calendar calFechaInicial = new GregorianCalendar();
			calFechaInicial.setTime(fechaComboInicial!=null ? fechaComboInicial : new Date() );
			calFechaInicial.add(Calendar.DATE, -1);
			fechaFinal = new java.sql.Date(calFechaInicial.getTime().getTime());
			
			ArrayList cuentasPorCobrarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorCobrar(Parametros.getIdEmpresa(), idCliente, fechaInicial, fechaFinal);
			
			Iterator moduloIt = SessionServiceLocator.getModuloSessionService().findModuloByCodigo("CART").iterator();
			Long idModulo = 0L;
			if (moduloIt.hasNext())
				idModulo = ((ModuloIf) moduloIt.next()).getId();
			ArrayList cuentasPorCobrarAdicionalesList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorCobrarAdicionales(Parametros.getIdEmpresa(), idCliente, idModulo, fechaInicial, fechaFinal);
			cuentasPorCobrarList = agregarCuentasPorCobrarAdicionales(cuentasPorCobrarList, cuentasPorCobrarAdicionalesList);
			
			//Map carteraDetallesCuentasPorCobrarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);
			Map<Long,Long[]> carteraDetallesCuentasPorCobrarMap = mapearCarteraDetallesByFechaInicialAndFechaFinal(null, fechaFinal);
			
			
		    //t::1seg
			Map parameterMap = new HashMap();
			parameterMap = new HashMap();
			parameterMap.put("cartera", "S");
			Map mapArray = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());		
			
			
			// 4.0 segundos
			//parameterMap.put("cartera", "S");
			//ArrayList carteraAfectaCuentasPorCobrarList = (ArrayList) SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByQueryByFechaInicialByFechaFinalAndEmpresaId(parameterMap, fechaInicial, fechaFinal, Parametros.getIdEmpresa());
		 
			
			if(cuentasPorCobrarList.size()>0){
				Iterator cuentasPorCobrarIterator = cuentasPorCobrarList.iterator();	
				while (cuentasPorCobrarIterator.hasNext()) {
					CuentasPorCobrarEJB cuentaPorCobrar = (CuentasPorCobrarEJB) cuentasPorCobrarIterator.next();
					double saldo = calcularSoloSaldoCartera(cuentaPorCobrar, mapArray, carteraDetallesCuentasPorCobrarMap);
					saldoInicialPorCobrar += saldo;
				}
			}
			
			saldoInicialPorCobrar = Utilitarios.redondeoValor(saldoInicialPorCobrar);


			
		} catch( Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en consulta !!", SpiritAlert.ERROR);
		}
		
	}
	
	private ArrayList agregarCuentasPorCobrarAdicionales(ArrayList cuentasPorCobrarList, ArrayList cuentasPorCobrarAdicionalesList) {
		Iterator cuentasPorCobrarAdicionalesIt = cuentasPorCobrarAdicionalesList.iterator();
		while (cuentasPorCobrarAdicionalesIt.hasNext()) {
			boolean agregado = false;
			Object[] cuentaPorCobrarAdicionalObject = (Object[]) cuentasPorCobrarAdicionalesIt.next();
			CarteraIf cartera = (CarteraIf) cuentaPorCobrarAdicionalObject[0];
			ClienteIf cliente = (ClienteIf) cuentaPorCobrarAdicionalObject[1];
			CuentasPorCobrarEJB cuentaPorCobrarAdicional = setCuentaPorCobrarAdicional(cartera, cliente);
			for (int i=0; i<cuentasPorCobrarList.size(); i++) {
				CuentasPorCobrarEJB cuentaPorCobrar = (CuentasPorCobrarEJB) cuentasPorCobrarList.get(i);
				if (cuentaPorCobrar.getClienteId().compareTo(cliente.getId()) == 0) {
					cuentasPorCobrarList.add(i, cuentaPorCobrarAdicional);
					agregado = true;
					break;
				}
			}
			
			if (!agregado)
				cuentasPorCobrarList.add(cuentaPorCobrarAdicional);
		}
		
		return cuentasPorCobrarList;
	}
	
	private CuentasPorCobrarEJB setCuentaPorCobrarAdicional(CarteraIf cartera, ClienteIf cliente) {
		CuentasPorCobrarEJB cuentaPorCobrarAdicional = new CuentasPorCobrarEJB();
		cuentaPorCobrarAdicional.setId(cartera.getId());
		cuentaPorCobrarAdicional.setCarteraId(cartera.getId());
		cuentaPorCobrarAdicional.setCodigo(cartera.getCodigo());
		cuentaPorCobrarAdicional.setOficinaId(cartera.getOficinaId());
		cuentaPorCobrarAdicional.setFechaFactura(new java.sql.Timestamp(cartera.getFechaEmision().getTime()));
		cuentaPorCobrarAdicional.setFechaEmision(new java.sql.Timestamp(cartera.getFechaEmision().getTime()));
		cuentaPorCobrarAdicional.setTipodocumentoId(cartera.getTipodocumentoId());
		cuentaPorCobrarAdicional.setFacturaId(cartera.getReferenciaId());
		cuentaPorCobrarAdicional.setReferenciaId(cartera.getReferenciaId());
		cuentaPorCobrarAdicional.setPreimpreso(cartera.getPreimpreso());
		cuentaPorCobrarAdicional.setIdentificacion(cliente.getIdentificacion());
		cuentaPorCobrarAdicional.setClienteId(cartera.getClienteoficinaId());
		cuentaPorCobrarAdicional.setRazonSocial(cliente.getRazonSocial());
		cuentaPorCobrarAdicional.setComentario(cartera.getComentario());
		cuentaPorCobrarAdicional.setObservacion(cartera.getComentario());
		cuentaPorCobrarAdicional.setValor(cartera.getValor());
		cuentaPorCobrarAdicional.setSaldo(cartera.getSaldo());
		return cuentaPorCobrarAdicional;
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
			Iterator documentosIterator = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (documentosIterator.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIterator.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	private Map mapearTiposDocumentos() {
		Map tiposDocumentosMap = new HashMap();
		
		try {
			Iterator tiposDocumentosIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentosIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosIterator.next();
				tiposDocumentosMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentosMap;
	}
	
	private boolean agregarFilasTabla(CuentasPorCobrarEJB cuentaPorCobrarObject, Vector<Object> fila, Map mapArray, Map<Long,Long[]> carteraDetallesCuentasPorCobrarMap){
		/*CarteraIf cartera = (CarteraIf) cuentaPorCobrarObject[0];
		FacturaIf factura = (FacturaIf) cuentaPorCobrarObject[1];
		ClienteIf cliente = (ClienteIf) cuentaPorCobrarObject[2];*/
		//Calendar now = Calendar.getInstance();
		//java.util.Date fechaActual = now.getTime();
		Calendar now = getCmbFechaCorte().getCalendar();
		java.util.Date fechaActual = now.getTime();
		long diasVencidos = Utilitarios.obtenerDiferenciaDias(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), new java.sql.Date(fechaActual.getYear(), fechaActual.getMonth(), fechaActual.getDate()));
		CuentaPorCobrarData cuentaPorCobrar = new CuentaPorCobrarData();
		String razonSocial = cuentaPorCobrarObject.getRazonSocial();
		cuentaPorCobrar.setCliente(razonSocial);
		String ruc = cuentaPorCobrarObject.getIdentificacion();
		cuentaPorCobrar.setRuc(ruc);
		String numeroFactura = cuentaPorCobrarObject.getPreimpreso();
		if (numeroFactura != null && numeroFactura.length() >= 15)
			numeroFactura = numeroFactura.substring(8, numeroFactura.length());
		cuentaPorCobrar.setNumeroFactura(numeroFactura);
		fila.add(numeroFactura);
		String fecha = cuentaPorCobrarObject.getFechaEmision().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaFactura = Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		cuentaPorCobrar.setFechaFactura(fechaFactura);
		fecha = cuentaPorCobrarObject.getFechaEmision().toString();
		year = fecha.substring(0,4);
		month = fecha.substring(5,7);
		day = fecha.substring(8,10);
		String fechaEmision = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		cuentaPorCobrar.setFechaEmision(fechaEmision);
		fila.add(fechaEmision);
		String xVencer = "0";
		String _30Dias = "0";
		String _60Dias = "0";
		String _90Dias = "0";
		String _120Dias = "0";
		fechaActual.setDate(now.getTime().getDate());
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha0 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 29);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha30 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 30);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha31 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 59);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha60 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 60);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha61 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 89);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha90 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 90);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha91 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 119);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha120 = new java.sql.Date(fechaActual.getTime());
		fechaActual.setDate(now.getTime().getDate() - 120);
		fechaActual.setMonth(now.getTime().getMonth());
		fechaActual.setYear(now.getTime().getYear());
		java.sql.Date fecha121 = new java.sql.Date(fechaActual.getTime());
		
		Map resultadosMap = calcularSaldoCartera(cuentaPorCobrarObject, mapArray, carteraDetallesCuentasPorCobrarMap);
		//double saldoCuentaPorCobrar = calcularSaldoCartera(cartera, carteraAfectaCuentasPorCobrarList, carteraDetallesCuentasPorCobrarMap);
		double saldoCuentaPorCobrar = ((Double) resultadosMap.get("SALDO")).doubleValue();
		boolean retencionRegistrada = ((Boolean) resultadosMap.get("RETENCION_REGISTRADA")).booleanValue();
		boolean sujetaRetencion = ((Boolean) resultadosMap.get("SUJETA_RETENCION")).booleanValue();
		
		//CASO ESPECIAL: PONER MES DE INVERSION EN LUGAR DE RETENCION
		/*String infoPresupuesto = "";
		try {
			Long facturaId = 0L;
			Long pedidoId = 0L;
			if(cuentaPorCobrarObject.getFacturaId() != null){
				facturaId = cuentaPorCobrarObject.getFacturaId();
				FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(facturaId);
				PedidoIf pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(facturaIf.getPedidoId());
				pedidoId = pedidoIf.getId();
			}else{
				Map facturaMap = new HashMap();
				facturaMap.put("preimpresoNumero", cuentaPorCobrarObject.getPreimpreso());
				Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(facturaMap);
				if(facturas.size() > 0){
					FacturaIf facturaIf = (FacturaIf)facturas.iterator().next();
					facturaId = facturaIf.getId();
					PedidoIf pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(facturaIf.getPedidoId());
					pedidoId = pedidoIf.getId();
				}
			}
			PedidoIf pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(pedidoId);
			if(pedidoIf != null){
				String tipoReferencia = pedidoIf.getTiporeferencia() != null? pedidoIf.getTiporeferencia() : "";
				if(tipoReferencia.equals("P")){
					Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(facturaId);
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
						PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
						//if(presupuestoIf.getConcepto().contains("JINGLE") || presupuestoDetalleIf.getConcepto().contains("JINGLE")){
							int mes = presupuestoIf.getFecha().getMonth()+1;
							infoPresupuesto = String.valueOf(mes);
							//infoPresupuesto = presupuestoIf.getCodigo();
						//}						
					}
				}				
				else if(tipoReferencia.equals("I")){
					Collection planMedioFormasPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedidoIf.getId());
					Iterator planMedioFormasPagoIt = planMedioFormasPago.iterator();
					while(planMedioFormasPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormasPagoIt.next();
						PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
						int mes = planMedioIf.getFechaInicio().getMonth()+1;
						infoPresupuesto = String.valueOf(mes);
						//infoPresupuesto = planMedioIf.getCodigo();
					}
				}
				else if(tipoReferencia.equals("C")){
					Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(facturaId);
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
						PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
						//if(presupuestoIf.getConcepto().contains("JINGLE") || presupuestoDetalleIf.getConcepto().contains("JINGLE")){
							int mes = presupuestoIf.getFecha().getMonth()+1;
							infoPresupuesto = String.valueOf(mes);
							//infoPresupuesto = presupuestoIf.getCodigo();
						//}						
					}
					Collection planMedioFormasPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedidoIf.getId());
					Iterator planMedioFormasPagoIt = planMedioFormasPago.iterator();
					while(planMedioFormasPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormasPagoIt.next();
						PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
						int mes = planMedioIf.getFechaInicio().getMonth()+1;
						infoPresupuesto = String.valueOf(mes);
						//infoPresupuesto = planMedioIf.getCodigo();
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		fila.add(infoPresupuesto);*/
		
		if (retencionRegistrada || !sujetaRetencion) {
			fila.add("");
			cuentaPorCobrar.setRetencionRegistrada("");
		} else if ((Integer.parseInt(year) > 2008 || (Integer.parseInt(year) == 2008 && Integer.parseInt(month) >= 9))) {
			fila.add("X");
			cuentaPorCobrar.setRetencionRegistrada("X");
		} else {
			fila.add("");
			cuentaPorCobrar.setRetencionRegistrada("");
		}
		
		
		String detalle = cuentaPorCobrarObject.getComentario();
		if (detalle == null)
			detalle = "";
		fila.add(detalle);
		if (detalle.length() > 33)
			detalle = detalle.substring(0, 33);
		cuentaPorCobrar.setDetalle(detalle);
		String saldo = formatoDecimal.format(saldoCuentaPorCobrar);
		totalSaldoCuentasPorCobrar += saldoCuentaPorCobrar;
		
		if (Utilitarios.dateBetween(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), fecha30, fecha0))
			xVencer = saldo;
		else if (Utilitarios.dateBetween(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), fecha60, fecha31))
			_30Dias = saldo;
		else if (Utilitarios.dateBetween(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), fecha90, fecha61))
			_60Dias = saldo;
		else if (Utilitarios.dateBetween(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), fecha120, fecha91))
			_90Dias = saldo;
		else if (Utilitarios.dateBefore(new java.sql.Date(cuentaPorCobrarObject.getFechaEmision().getTime()), fecha121))
			_120Dias = saldo;
		
		cuentaPorCobrar.setxVencer(xVencer);
		cuentaPorCobrar.set_30Dias(_30Dias);
		cuentaPorCobrar.set_60Dias(_60Dias);
		cuentaPorCobrar.set_90Dias(_90Dias);
		cuentaPorCobrar.set_120Dias(_120Dias);
		fila.add(xVencer);
		fila.add(_30Dias);
		fila.add(_60Dias);
		fila.add(_90Dias);
		fila.add(_120Dias);
		fila.add(String.valueOf(diasVencidos));
		cuentaPorCobrar.setDiasVencidos(String.valueOf(diasVencidos));
		cuentaPorCobrar.setClienteId(String.valueOf(cuentaPorCobrarObject.getClienteId()));
		if (Utilitarios.redondeoValor(saldoCuentaPorCobrar) > 0D)
			getCuentasPorCobrarColeccion().add(cuentaPorCobrar);
		else
			return false;
		
		return true;
	}
	 	
	private Map calcularSaldoCartera(CuentasPorCobrarEJB cartera, Map mapArray, Map<Long,Long[]> carteraDetallesCuentasPorCobrarMap) {
		Map resultadosMap = new HashMap();
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosMap.get(cartera.getTipodocumentoId());
		double saldo = cartera.getValor().doubleValue();
		boolean retencionRegistrada = false;
		boolean sujetaRetencion = (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FCO"))?true:false;
		 
		Iterator mapaMesValorIt2 = mapArray.keySet().iterator();
		while(mapaMesValorIt2.hasNext()){
			Long idd = (Long)mapaMesValorIt2.next();
			Object[] rentaBases = (Object[])mapArray.get(idd);	
			
			Long[] idDetalledoc= carteraDetallesCuentasPorCobrarMap.get(rentaBases[0]); 
			Long[] idAfectaDoc= carteraDetallesCuentasPorCobrarMap.get(rentaBases[1]);
			boolean flag=false;
			if(idDetalledoc!=null && idDetalledoc[0]!=null && idDetalledoc[0].compareTo(cartera.getId())== 0)  
			{
				BigDecimal bd= (BigDecimal)rentaBases[2];
				saldo -= bd.doubleValue();
				 
				DocumentoIf documentoAfectante = null;
				if(idAfectaDoc!=null && idAfectaDoc[1]!=null)	documentoAfectante = (DocumentoIf) documentosMap.get(idAfectaDoc[1]);
				
				if(documentoAfectante == null){
					System.out.println("error");
				}				
				
				if (documentoAfectante != null && 
						(documentoAfectante.getRetencionRenta().equals("S") || documentoAfectante.getRetencionIva().equals("S"))){
					retencionRegistrada = true;
				}
			}			 
		}

		resultadosMap.put("SALDO", saldo);
		resultadosMap.put("RETENCION_REGISTRADA", retencionRegistrada);
		resultadosMap.put("SUJETA_RETENCION", sujetaRetencion);
		return resultadosMap;
	}
	
	
	private double calcularSoloSaldoCartera(CuentasPorCobrarEJB cartera, Map mapArray, Map<Long,Long[]> carteraDetallesCuentasPorCobrarMap) {
		 Map resultadosMap = new HashMap();
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosMap.get(cartera.getTipodocumentoId());
		double saldo = cartera.getValor().doubleValue();

		Iterator mapaMesValorIt2 = mapArray.keySet().iterator();
		while(mapaMesValorIt2.hasNext()){
			Long idd = (Long)mapaMesValorIt2.next();
			Object[] rentaBases = (Object[])mapArray.get(idd);
			
			Long[] idDetalledoc= carteraDetallesCuentasPorCobrarMap.get(rentaBases[0]);
			
			boolean flag=false;
			if(idDetalledoc!=null && idDetalledoc[0]!=null && idDetalledoc[0].compareTo(cartera.getId())== 0) flag=true;
			 if(flag)
				{
					BigDecimal bd= (BigDecimal)rentaBases[2];
					saldo -= bd.doubleValue();
				 //saldo -= carteraAfecta.getValor().doubleValue();			
				}
		}
		saldo = Utilitarios.redondeoValor(saldo);
		 
		  
		return saldo;
		
		
		
		
	}
	
	public Vector<CuentaPorCobrarData> getCuentasPorCobrarColeccion() {
		return cuentasPorCobrarColeccion;
	}

	public void setCuentasPorCobrarColeccion(Vector<CuentaPorCobrarData> cuentasPorCobrarColeccion) {
		this.cuentasPorCobrarColeccion = cuentasPorCobrarColeccion;
	}
}