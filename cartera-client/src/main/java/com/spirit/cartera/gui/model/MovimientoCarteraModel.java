package com.spirit.cartera.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.panel.JPMovimientoCartera;
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
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class MovimientoCarteraModel extends JPMovimientoCartera {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "MOVIMIENTO DE CARTERA";
	private static final String NOMBRE_TIPO_CARTERA_CLIENTE = "CLIENTE";
	private static final String TIPO_CARTERA_CLIENTE = NOMBRE_TIPO_CARTERA_CLIENTE.substring(0, 1);
	private static final String NOMBRE_TIPO_CARTERA_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_CARTERA_PROVEEDOR = NOMBRE_TIPO_CARTERA_PROVEEDOR.substring(0, 1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private DefaultTableModel tableModel;
	ClienteIf operadorNegocio = null;
	ClienteOficinaIf clienteOficinaIf = null;
	double totalDebitos;
	double totalCreditos;
	double saldo;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	boolean isTotal = false;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	
	
	public MovimientoCarteraModel() {
		getTblMovimientoCartera().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		iniciarComponentes();
		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponent(this);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		getBtnBuscarOperadorNegocio().addKeyListener(oKeyAdapterBtnBuscarOperador);
		getBtnResetearFechas().addKeyListener(oKeyAdapterBtnResetearFechas);
		getCbMostrarTodos().addKeyListener(oKeyAdapterBtnCbTodos);
	}
	
	KeyListener oKeyAdapterBtnCbTodos = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				 
				if(getCbMostrarTodos().isSelected()) getCbMostrarTodos().setSelected(false); 
				else getCbMostrarTodos().setSelected(true);
				
				 
				
				 
			}
		}
	};
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		}
	};
	
	KeyListener oKeyAdapterBtnBuscarOperador = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				Long idCorporacion = 0L;
				String tipoCliente = "";
				String tituloVentanaBusqueda = "";
				
				if (getCmbTipoCartera().getSelectedItem() != null) {
					if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
						tipoCliente = "CL";
						tituloVentanaBusqueda = "Clientes";
					} else {
						tipoCliente = "PR";
						tituloVentanaBusqueda = "Proveedores";
					}
				} else {
					tipoCliente = "AM";
					tituloVentanaBusqueda = "Operadores de Negocio";
				}
				
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cleanTable();
					operadorNegocio = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtOperadorNegocio().setText(operadorNegocio.toString());
				}
			}
		}
	};
	
	KeyListener oKeyAdapterBtnResetearFechas = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				getCmbFechaFinal().setSelectedItem(null);
				getCmbFechaFinal().validate();
				getCmbFechaFinal().repaint();
			}
		}
	};
	
	private void iniciarComponentes() {
		operadorNegocio = null;
		clienteOficinaIf = null;
		
		getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_CLIENTE);
		
		getBtnCliente().setEnabled(false);
		getCbTodosClientes().setSelected(false);
		getCbTodosClientes().setEnabled(false);		
				
		getBtnBuscarOperadorNegocio().setToolTipText("Buscar Operador de Negocio");
		getBtnBuscarOperadorNegocio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar movimiento de cartera");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblMovimientoCartera().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenterAlignment);
		getTblMovimientoCartera().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenterAlignment);
		getTblMovimientoCartera().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenterAlignment);
		TableCellRendererHorizontalRightAlignment tableCellRendererRightAlignment = new TableCellRendererHorizontalRightAlignment();
		getTblMovimientoCartera().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererRightAlignment);
		getTblMovimientoCartera().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererRightAlignment);
		getTblMovimientoCartera().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererRightAlignment);
		
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);
	}
		
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(135);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(275);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblMovimientoCartera().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(80);
	}
	
	private void initListeners(){
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "";
				String tituloVentanaBusqueda = "";
				
				if (getCmbTipoCartera().getSelectedItem() != null) {
					if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
						tipoCliente = "CL";
						tituloVentanaBusqueda = "Clientes";
					} else {
						tipoCliente = "PR";
						tituloVentanaBusqueda = "Proveedores";
					}
				} else {
					tipoCliente = "AM";
					tituloVentanaBusqueda = "Operadores de Negocio";
				}
				
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cleanTable();
					operadorNegocio = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtOperadorNegocio().setText(operadorNegocio.toString());
					getCbTodosOperadores().setSelected(false);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		});
		
		getBtnResetearFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				getCmbFechaFinal().setSelectedItem(null);
				getCmbFechaFinal().validate();
				getCmbFechaFinal().repaint();
			}
		});
		
		getCmbTipoCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_PROVEEDOR)) {
					getBtnCliente().setEnabled(true);
					getCbTodosClientes().setEnabled(true);	
				}else{
					clienteOficinaIf = null;
					getTxtCliente().setText("");
					getBtnCliente().setEnabled(false);
					getCbTodosClientes().setSelected(false);
					getCbTodosClientes().setEnabled(false);	
				}
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
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
		
		getCbTodosOperadores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosOperadores().isSelected()){
					operadorNegocio = null;
					getTxtOperadorNegocio().setText("");
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
			if (getTblMovimientoCartera().getModel().getRowCount() > 0) {
				
				String fileName = "jaspers/cartera/RPMovimientoCartera.jasper";
								
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte sin Cabeceras?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
								
				if (opcion == JOptionPane.YES_OPTION) {
					fileName = "jaspers/cartera/RPMovimientoCarteraExcel.jasper";
				}
				
				HashMap parametrosMap = new HashMap();
				
				//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MOVIMIENTO_CARTERA).iterator().next();
				MenuIf menu = null;
				Iterator itmenu= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MOVIMIENTO_CARTERA).iterator();
				if(itmenu.hasNext())  menu = (MenuIf) itmenu.next();
				
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				String fechaActual = Utilitarios.dateHoraHoy();
				String year = fechaActual.substring(0,4);
				String month = fechaActual.substring(5,7);
				String day = fechaActual.substring(8,10);
				String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario());
				parametrosMap.put("emitido", fechaEmision);
				String fechaInicial = "";
				if (getCmbFechaInicial().getSelectedItem() != null)
					fechaInicial = (new java.sql.Date(((Date) getCmbFechaInicial().getDate()).getYear(), ((Date) getCmbFechaInicial().getDate()).getMonth(), ((Date) getCmbFechaInicial().getDate()).getDate())).toString();
				if (!fechaInicial.equals(""))
					parametrosMap.put("fechaInicial", fechaInicial.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaInicial.substring(5,7))).substring(0,3) + "-" + fechaInicial.substring(0,4));
				else
					parametrosMap.put("fechaInicial", fechaInicial);
				String fechaFinal = fechaActual;
				if (getCmbFechaFinal().getSelectedItem() != null)
					fechaFinal = (new java.sql.Date(((Date) getCmbFechaFinal().getDate()).getYear(), ((Date) getCmbFechaFinal().getDate()).getMonth(), ((Date) getCmbFechaFinal().getDate()).getDate())).toString();
				parametrosMap.put("fechaFinal", fechaFinal.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaFinal.substring(5,7))).substring(0,3) + "-" + fechaFinal.substring(0,4));
				parametrosMap.put("rucOperadorNegocio", "1790598012001 - "/*operadorNegocio.getIdentificacion()*/);
				parametrosMap.put("operadorNegocio",  "GENERAL MOTORS DEL ECUADOR S.A."/*operadorNegocio.getRazonSocial()*/);
				parametrosMap.put("totalDebitos", totalDebitos);
				parametrosMap.put("totalCreditos", (totalCreditos * -1));
				parametrosMap.put("saldo", saldo);
				ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblMovimientoCartera().getModel(), true);
			
			}else{
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void refresh() {

	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void clean() {
		totalDebitos = 0D;
		totalCreditos = 0D;
		saldo = 0D;
		getTxtTotalDebitos().setText("");
		getTxtTotalCreditos().setText("");
		getTxtSaldo().setText("");
		cleanTable();
	}
	
	public void cleanTable() {
		//getMovimientoCarteraColeccion().clear();
		DefaultTableModel model = (DefaultTableModel) getTblMovimientoCartera().getModel();
		
		for(int i= this.getTblMovimientoCartera().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		
		getCmbTipoCartera().grabFocus();
	}

	private void cargarTabla() {
		try {
			if(operadorNegocio != null){
				procesoCargarTabla(operadorNegocio); 
			}
			else if(getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_PROVEEDOR) && clienteOficinaIf != null){
				Collection proveedores = SessionServiceLocator.getClienteSessionService().getClienteList();
				Iterator proveedoresIt = proveedores.iterator();
				while(proveedoresIt.hasNext()){
					ClienteIf proveedor = (ClienteIf)proveedoresIt.next();
					
					if(proveedor.getTipoclienteId().compareTo(2L) == 0 
							|| proveedor.getTipoclienteId().compareTo(3L) == 0 ){
						
						procesoCargarTabla(proveedor); 
					}
				}
			}else{
				SpiritAlert.createAlert("Por favor seleccione el " + getCmbTipoCartera().getSelectedItem().toString().toLowerCase() + " que se desea consultar!", SpiritAlert.INFORMATION);
				getBtnBuscarOperadorNegocio().grabFocus();
			}			
			
			/*if(operadorNegocio != null) {
				Map tiposDocumentosMap = mapearTiposDocumentos();
				Map documentosMap = mapearDocumentos();
				Map modulosMap = mapearModulos();
				Map cuentasBancariasMap = mapearCuentasBancarias();
				Map bancosMap = mapearBancos();
				Map planCuentaMap = mapearPlanCuenta();
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				ArrayList movimientosPositivosCarteraList = new ArrayList();
				ArrayList movimientosPositivosCarteraAfectadosList = new ArrayList();
				ArrayList movimientosNegativosCarteraList = new ArrayList();
				Long idOperadorNegocio = 0L;
				String tipoCartera = getCmbTipoCartera().getSelectedItem().toString().substring(0,1);
				idOperadorNegocio = operadorNegocio.getId();
				totalDebitos = 0D;
				totalCreditos = 0D;
				saldo = 0D;
				
				java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
				java.sql.Date fechaInicial = null;
				
				if (getCmbFechaInicial().getSelectedItem() != null)
					fechaInicialSeleccionada = new java.sql.Date(((Date) getCmbFechaInicial().getDate()).getYear(), ((Date) getCmbFechaInicial().getDate()).getMonth(), ((Date) getCmbFechaInicial().getDate()).getDate());
				else
					fechaInicialSeleccionada = null;
				
				if (fecha != null)
					fechaInicial = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fecha.getTime())));
				
				fecha = (Date) getCmbFechaFinal().getDate();
				java.sql.Date fechaFinal = null;
				if (getCmbFechaFinal().getSelectedItem() != null)
					fechaFinalSeleccionada = new java.sql.Date(((Date) getCmbFechaFinal().getDate()).getYear(), ((Date) getCmbFechaFinal().getDate()).getMonth(), ((Date) getCmbFechaFinal().getDate()).getDate());
				else
					fechaFinalSeleccionada = null;
				
				if (fecha != null)
					fechaFinal = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fecha.getTime())));
				
				movimientosPositivosCarteraList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosPositivosCartera(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
				movimientosPositivosCarteraAfectadosList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosPositivosCarteraAfectados(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
				movimientosNegativosCarteraList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosNegativosCartera(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
				int numero = 1;
				
				if (movimientosPositivosCarteraList.size()>0) {
					
					Iterator movimientosPositivosCarteraIterator = movimientosPositivosCarteraList.iterator();	
					while (movimientosPositivosCarteraIterator.hasNext()) {
						Object[] o = (Object[]) movimientosPositivosCarteraIterator.next();
						CarteraIf movimientoPositivoCartera = (CarteraIf) o[0];
						UsuarioIf usuarioMovimientoPositivoCartera = (UsuarioIf) o[1];
						tableModel = (DefaultTableModel) getTblMovimientoCartera().getModel();
						
						if (agregarFilasTabla(numero, movimientoPositivoCartera, movimientosPositivosCarteraAfectadosList, movimientosNegativosCarteraList, tiposDocumentosMap, documentosMap, modulosMap, cuentasBancariasMap, bancosMap, planCuentaMap, empresa, usuarioMovimientoPositivoCartera)){
							numero++;
						}					
					}
					
					for (int i = 0; i < getTblMovimientoCartera().getColumnCount(); i++)
						getTblMovimientoCartera().getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

					getTblMovimientoCartera().validate();
					getTblMovimientoCartera().repaint();
					getTxtTotalDebitos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDebitos)));
					getTxtTotalCreditos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalCreditos)));
					saldo = totalDebitos - totalCreditos;
					getTxtSaldo().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));
				} else {
					SpiritAlert.createAlert("No se hallaron datos para los parámetros de consulta seleccionados", SpiritAlert.INFORMATION);
					clean();
				}			
			} else {
				SpiritAlert.createAlert("Por favor seleccione el " + getCmbTipoCartera().getSelectedItem().toString().toLowerCase() + " que se desea consultar!", SpiritAlert.INFORMATION);
				getBtnBuscarOperadorNegocio().grabFocus();
			}*/
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void procesoCargarTabla(ClienteIf proveedor)
			throws GenericBusinessException {
		
		Map tiposDocumentosMap = mapearTiposDocumentos();
		Map documentosMap = mapearDocumentos();
		Map modulosMap = mapearModulos();
		Map cuentasBancariasMap = mapearCuentasBancarias();
		Map bancosMap = mapearBancos();
		Map planCuentaMap = mapearPlanCuenta();
		EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
		ArrayList movimientosPositivosCarteraList = new ArrayList();
		ArrayList movimientosPositivosCarteraAfectadosList = new ArrayList();
		ArrayList movimientosNegativosCarteraList = new ArrayList();
		Long idOperadorNegocio = 0L;
		String tipoCartera = getCmbTipoCartera().getSelectedItem().toString().substring(0,1);
		idOperadorNegocio = proveedor.getId();
		totalDebitos = 0D;
		totalCreditos = 0D;
		saldo = 0D;
		
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		java.sql.Date fechaInicial = null;
		
		if (getCmbFechaInicial().getSelectedItem() != null)
			fechaInicialSeleccionada = new java.sql.Date(((Date) getCmbFechaInicial().getDate()).getYear(), ((Date) getCmbFechaInicial().getDate()).getMonth(), ((Date) getCmbFechaInicial().getDate()).getDate());
		else
			fechaInicialSeleccionada = null;
		
		if (fecha != null)
			fechaInicial = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fecha.getTime())));
		
		fecha = (Date) getCmbFechaFinal().getDate();
		java.sql.Date fechaFinal = null;
		if (getCmbFechaFinal().getSelectedItem() != null)
			fechaFinalSeleccionada = new java.sql.Date(((Date) getCmbFechaFinal().getDate()).getYear(), ((Date) getCmbFechaFinal().getDate()).getMonth(), ((Date) getCmbFechaFinal().getDate()).getDate());
		else
			fechaFinalSeleccionada = null;
		
		if (fecha != null)
			fechaFinal = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fecha.getTime())));
		
		movimientosPositivosCarteraList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosPositivosCartera(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
		int numero = 1;
		
		if (movimientosPositivosCarteraList.size()>0) {
			
			movimientosPositivosCarteraAfectadosList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosPositivosCarteraAfectados(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
			movimientosNegativosCarteraList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findMovimientosNegativosCartera(Parametros.getIdEmpresa(), tipoCartera, idOperadorNegocio, fechaInicial, fechaFinal);
			
			Iterator movimientosPositivosCarteraIterator = movimientosPositivosCarteraList.iterator();	
			while (movimientosPositivosCarteraIterator.hasNext()) {
				Object[] o = (Object[]) movimientosPositivosCarteraIterator.next();
				CarteraIf movimientoPositivoCartera = (CarteraIf) o[0];
				
				//filtrado por cliente
				boolean compraDelCliente = false;
				
				if(clienteOficinaIf != null){
					Collection ordenesAsociada = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(movimientoPositivoCartera.getReferenciaId());
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
									if(presupuesto.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) == 0){
										compraDelCliente = true;
									}
								}else{
									OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
									OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
									if(ordenTrabajo.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0){
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
												if(presupuesto.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) == 0){
													compraDelCliente = true;
												}
											}else{
												OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
												OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
												if(ordenTrabajo.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0){
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
							if(ordenMedio.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) == 0){
								compraDelCliente = true;
							}
						}
					}
				}			
											
				if(clienteOficinaIf == null || (clienteOficinaIf != null && compraDelCliente)){								
					UsuarioIf usuarioMovimientoPositivoCartera = (UsuarioIf) o[1];
					tableModel = (DefaultTableModel) getTblMovimientoCartera().getModel();
					
					if (agregarFilasTabla(numero, movimientoPositivoCartera, movimientosPositivosCarteraAfectadosList, movimientosNegativosCarteraList, tiposDocumentosMap, documentosMap, modulosMap, cuentasBancariasMap, bancosMap, planCuentaMap, empresa, usuarioMovimientoPositivoCartera)){
						numero++;
					}	
				}										
			}
			
			for (int i = 0; i < getTblMovimientoCartera().getColumnCount(); i++)
				getTblMovimientoCartera().getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

			getTblMovimientoCartera().validate();
			getTblMovimientoCartera().repaint();
			getTxtTotalDebitos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDebitos)));
			getTxtTotalCreditos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalCreditos)));
			saldo = totalDebitos - totalCreditos;
			getTxtSaldo().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));
		}
	}

	private boolean agregarFilasTabla(int numero, CarteraIf movimientoPositivoCartera, ArrayList movimientosPositivosCarteraAfectados, ArrayList movimientosNegativosCartera, Map tiposDocumentosMap, Map documentosMap, Map modulosMap, Map cuentasBancariasMap, Map bancosMap, Map planCuentaMap, EmpresaIf empresa, UsuarioIf usuarioMovimientoPositivoCartera) {
		Vector<Object> fila = new Vector<Object>();
		double totalDebitosMovimiento = 0D;
		double totalCreditosMovimiento = 0D;
		double saldoMovimiento = 0D;
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		String fecha = movimientoPositivoCartera.getFechaEmision().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaEmision = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		fila.add(fechaEmision);
		
		/*---------------*/
		
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosMap.get(movimientoPositivoCartera.getTipodocumentoId());
		ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
		Map parameterMap = new HashMap();
		parameterMap.put("tipoDocumentoId", movimientoPositivoCartera.getTipodocumentoId());
		if (modulo.getCodigo().equals("CART"))
			parameterMap.put("transaccionId", movimientoPositivoCartera.getId());
		else
			parameterMap.put("transaccionId", movimientoPositivoCartera.getReferenciaId());
		AsientoIf asiento = null;
		try {
			Iterator it = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				asiento = (AsientoIf) it.next();
				PlanCuentaIf planCuenta = (PlanCuentaIf) planCuentaMap.get(asiento.getPlancuentaId());
				fila.add(asiento.getNumero().replaceAll(empresa.getCodigo() + "-" + planCuenta.getCodigo() + "-",""));
			} else
				fila.add("");
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		/*---------------*/
		
		if (asiento == null || (asiento != null && (asiento.getStatus().equals("A") || getCbMostrarTodos().isSelected()))) {
			String transaccion = movimientoPositivoCartera.getPreimpreso();
			if (tipoDocumento.getCodigo().equals("NPC"))
				System.out.println("Este es");
			if (transaccion != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAR") || tipoDocumento.getCodigo().equals("FAE") || tipoDocumento.getCodigo().equals("FCO") || tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("SAE")))
				transaccion = "F: " + transaccion;
			if (transaccion != null && tipoDocumento.getCodigo().equals("VTA") || tipoDocumento.getCodigo().equals("CNV"))
				transaccion = "N/V: " + transaccion;
			if (transaccion != null && tipoDocumento.getCodigo().equals("LIC"))
				transaccion = "L: " + transaccion;
			fila.add((transaccion != null && !transaccion.equals(""))?transaccion:movimientoPositivoCartera.getCodigo());
			String detalle = movimientoPositivoCartera.getComentario();
			if (detalle == null || detalle.equals(""))
				detalle = tipoDocumento.getNombre();
			fila.add(usuarioMovimientoPositivoCartera.getUsuario());
			fila.add((detalle != null && !detalle.equals(""))?detalle:"");
			if (movimientoPositivoCartera.getTipo().equals("C")) {
				totalDebitos += movimientoPositivoCartera.getValor().doubleValue();
				totalDebitosMovimiento += movimientoPositivoCartera.getValor().doubleValue();
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(movimientoPositivoCartera.getValor().doubleValue())));
				fila.add("0");
			} else {
				totalCreditos += movimientoPositivoCartera.getValor().doubleValue();
				totalCreditosMovimiento += movimientoPositivoCartera.getValor().doubleValue();
				fila.add("0");
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(movimientoPositivoCartera.getValor().doubleValue())));
			}

			tableModel.addRow(fila);
		}
		
		// Agregamos a la tabla todos los movimientos que se encuentran afectando al movimiento de cartera positivo actual
		Iterator movimientosPositivosCarteraAfectadosIterator = movimientosPositivosCarteraAfectados.iterator();
		while (movimientosPositivosCarteraAfectadosIterator.hasNext()) {
			Object[] movimientoPositivoCarteraAfectado = (Object[]) movimientosPositivosCarteraAfectadosIterator.next();
			CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) movimientoPositivoCarteraAfectado[0];
			CarteraIf carteraAfectada = (CarteraIf) movimientoPositivoCarteraAfectado[1];
			if (carteraAfectada.getId().compareTo(movimientoPositivoCartera.getId()) == 0) {
				Iterator movimientosNegativosCarteraIterator = movimientosNegativosCartera.iterator();
				while (movimientosNegativosCarteraIterator.hasNext()) {
					Object[] movimientoNegativoCartera = (Object[]) movimientosNegativosCarteraIterator.next();
					CarteraDetalleIf carteraDetalleNegativo = (CarteraDetalleIf) movimientoNegativoCartera[0];
					CarteraIf carteraNegativa = (CarteraIf) movimientoNegativoCartera[1];
					UsuarioIf usuarioMovimientoNegativoCartera = (UsuarioIf) movimientoNegativoCartera[3];
					if (carteraAfecta.getCarteradetalleId().compareTo(carteraDetalleNegativo.getId()) == 0) {					
						fila = new Vector<Object>();
						fila.add("");
						fecha = carteraNegativa.getFechaEmision().toString();
						//fecha = carteraAfecta.getFechaAplicacion().toString();
						year = fecha.substring(0,4);
						month = fecha.substring(5,7);
						day = fecha.substring(8,10);
						fechaEmision = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
						fila.add(fechaEmision);
						
						/*----------------------------*/
						
						tipoDocumento = (TipoDocumentoIf) tiposDocumentosMap.get(carteraNegativa.getTipodocumentoId());
						modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
						parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", carteraNegativa.getTipodocumentoId());
						if (modulo.getCodigo().equals("CART") && !tipoDocumento.getCodigo().equals("NCC") && !tipoDocumento.getCodigo().equals("NCP"))
							parameterMap.put("transaccionId", carteraNegativa.getId());
						else
							parameterMap.put("transaccionId", carteraNegativa.getReferenciaId());
						try {
							Iterator it = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
							if (it.hasNext()) {
								//AsientoIf asiento = (AsientoIf) it.next();
								asiento = (AsientoIf) it.next();
								PlanCuentaIf planCuenta = (PlanCuentaIf) planCuentaMap.get(asiento.getPlancuentaId());
								fila.add(asiento.getNumero().replaceAll(empresa.getCodigo() + "-" + planCuenta.getCodigo() + "-",""));
							} else
								fila.add("");
						} catch(GenericBusinessException e) {
							e.printStackTrace();
							SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
						}
						
						/*-----------------------------*/
						
						if (asiento == null || (asiento != null && (asiento.getStatus().equals("A") || getCbMostrarTodos().isSelected()))) {
							String transaccion = carteraNegativa.getCodigo();
							fila.add(transaccion);
							String detalle = carteraNegativa.getComentario();
							DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalleNegativo.getDocumentoId());
							if (documento.getRetencionRenta().equals("S") || documento.getRetencionIva().equals("S")) {
								if (carteraNegativa.getActivarRetrocompatibilidad() == null || carteraNegativa.getActivarRetrocompatibilidad().equals("S")) {
									detalle = documento.getNombre().replaceAll(" CLIENTE","").replaceAll(" PROVEEDOR","").replaceAll("RETENCION", "RET.");
									detalle += " " + carteraDetalleNegativo.getPreimpreso();
								} else
									detalle = carteraDetalleNegativo.getObservacion();
							} else if (documento.getCheque().equals("S") && carteraNegativa.getTipo().equals("C")) {
								if (carteraNegativa.getActivarRetrocompatibilidad() == null || carteraNegativa.getActivarRetrocompatibilidad().equals("S"))
									detalle = "COBRO CON CHEQUE " + carteraDetalleNegativo.getReferencia() + " #" + carteraDetalleNegativo.getPreimpreso();
								else {
									BancoIf banco = (BancoIf) bancosMap.get(carteraDetalleNegativo.getChequeBancoId());
									CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleNegativo.getChequeCuentaBancariaId());
									detalle = "COBRO CON CHEQUE " + banco.getNombre() + " CTA.# " + cuentaBancaria.getCuenta() + " CH. " + carteraDetalleNegativo.getChequeNumero(); 
								}
							} else if (documento.getCheque().equals("S") && carteraNegativa.getTipo().equals("P")) {
								if (carteraDetalleNegativo.getCuentaBancariaId() != null) {
									if (carteraNegativa.getActivarRetrocompatibilidad() == null || carteraNegativa.getActivarRetrocompatibilidad().equals("S")) {
										CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleNegativo.getCuentaBancariaId());
										BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
										detalle = "PAGO CON CHEQUE " + banco.getNombre() + " CTA.# " + cuentaBancaria.getCuenta() + " CH. " + carteraDetalleNegativo.getPreimpreso();
									} else {
										BancoIf banco = (BancoIf) bancosMap.get(carteraDetalleNegativo.getChequeBancoId());
										CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleNegativo.getChequeCuentaBancariaId());
										detalle = "PAGO CON CHEQUE " + banco.getNombre() + " CTA.# " + cuentaBancaria.getCuenta() + " CH. " + carteraDetalleNegativo.getChequeNumero();	
									}
								}
							} else if (documento.getDebitoBancario().equals("S") && carteraNegativa.getTipo().equals("P")) {
								if (carteraDetalleNegativo.getCuentaBancariaId() != null) {
									if (carteraNegativa.getActivarRetrocompatibilidad() == null || carteraNegativa.getActivarRetrocompatibilidad().equals("S")) {
										CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleNegativo.getCuentaBancariaId());
										BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
										detalle = "PAGO CON D/BANCARIO " + banco.getNombre() + " CTA.# " + cuentaBancaria.getCuenta();
									} else {
										BancoIf banco = (BancoIf) bancosMap.get(carteraDetalleNegativo.getDebitoBancoId());
										CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleNegativo.getDebitoCuentaBancariaId());
										detalle = "PAGO CON CHEQUE " + banco.getNombre() + " CTA.# " + cuentaBancaria.getCuenta();
									}
								}
							} else if (detalle == null || detalle.equals("")) {
								if (carteraNegativa.getActivarRetrocompatibilidad() == null || carteraNegativa.getActivarRetrocompatibilidad().equals("S"))
									detalle = documento.getNombre() + " " + carteraDetalleNegativo.getPreimpreso();
								else 
									detalle = carteraDetalleNegativo.getObservacion();
							}
							fila.add(usuarioMovimientoNegativoCartera.getUsuario());
							fila.add((detalle != null && !detalle.equals(""))?detalle:"");
							if (carteraNegativa.getTipo().equals("C")) {
								totalCreditos += carteraAfecta.getValor().doubleValue();
								totalCreditosMovimiento += carteraAfecta.getValor().doubleValue();
								fila.add("0");
								fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carteraAfecta.getValor().doubleValue())));
							} else {
								totalDebitos += carteraAfecta.getValor().doubleValue();
								totalDebitosMovimiento += carteraAfecta.getValor().doubleValue();
								fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carteraAfecta.getValor().doubleValue())));
								fila.add("0");
							}

							tableModel.addRow(fila);
							break;
						}
					}
				}
			}
		}
		
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("<html><b>T O T A L E S :</b></html>");
		if (totalDebitosMovimiento != 0)
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalDebitosMovimiento)) + "</b></html>");
		else
			fila.add("<html><b>0</b></html>");
		if (totalCreditosMovimiento != 0)
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalCreditosMovimiento)) + "</b></html>");
		else
			fila.add("<html><b>0</b></html>");
		tableModel.addRow(fila);
		
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("<html><b>S A L D O :</b></html>");
		saldoMovimiento = totalDebitosMovimiento - totalCreditosMovimiento;
		if (saldoMovimiento > 0) {
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoMovimiento)) + "</b></html>");
			fila.add("<html><b>" + "0" + "</b></html>");
		} else if (saldoMovimiento < 0) {
			fila.add("<html><b>" + "0" + "</b></html>");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoMovimiento)) + "</b></html>");
		} else {
			fila.add("<html><b>" + "0" + "</b></html>");
			fila.add("<html><b>" + "0" + "</b></html>");
		}
		tableModel.addRow(fila);
		
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		tableModel.addRow(fila);
		
		return true;
	}
	
	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color whiteColor = new Color(255, 255, 255);
		private Color grayColor = new Color(204, 204, 204);
		private Color blackColor = new Color(0, 0, 0);
        
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        	c.setForeground(blackColor);
        	
        	if(column == 0)
        		setHorizontalAlignment(JLabel.RIGHT);
        	if(column == 1)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 2)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 3)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 4)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 5)
        		setHorizontalAlignment(JLabel.LEFT);
        	if(column == 6)
        		setHorizontalAlignment(JLabel.RIGHT);
        	if(column == 7)
        		setHorizontalAlignment(JLabel.RIGHT);
        	
        	if(((String) value).equals("<html><b>T O T A L E S :</b></html>") || ((String) value).equals("<html><b>S A L D O :</b></html>")) {
        		c.setBackground(grayColor);
        		setHorizontalAlignment(JLabel.RIGHT);
        	} else {
        		c.setBackground(whiteColor);
        	}
        	
        	String detalle = (String) table.getValueAt(row, 3);
        	if ((column >= 0 && column <= 6) && (detalle.equals("<html><b>T O T A L E S :</b></html>") || detalle.equals("<html><b>S A L D O :</b></html>"))) {
        		c.setBackground(grayColor);
        	}
        	
        	return c;
       }
    };
	
	private Map mapearTiposDocumentos() {
		Map tiposDocumentosMap = new HashMap();
		
		try {
			Iterator tiposDocumentosIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentosIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosIterator.next();
				tiposDocumentosMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentosMap;
	}
	
	private Map mapearDocumentos() {
		Map documentosMap = new HashMap();
		
		try {
			Iterator documentosIterator = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (documentosIterator.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIterator.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	private Map mapearModulos() {
		Map modulosMap = new HashMap();
		
		try {
			Iterator modulosIterator = SessionServiceLocator.getModuloSessionService().getModuloList().iterator();
			while (modulosIterator.hasNext()) {
				ModuloIf modulo = (ModuloIf) modulosIterator.next();
				modulosMap.put(modulo.getId(), modulo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return modulosMap;
	}
	
	private Map mapearCuentasBancarias() {
		Map cuentasBancariasMap = new HashMap();
		
		try {
			Iterator cuentasBancariasIterator = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (cuentasBancariasIterator.hasNext()) {
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasIterator.next();
				cuentasBancariasMap.put(cuentaBancaria.getId(), cuentaBancaria);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasBancariasMap;
	}
	
	private Map mapearBancos() {
		Map bancosMap = new HashMap();
		
		try {
			Iterator bancosIterator = SessionServiceLocator.getBancoSessionService().getBancoList().iterator();
			while (bancosIterator.hasNext()) {
				BancoIf banco = (BancoIf) bancosIterator.next();
				bancosMap.put(banco.getId(), banco);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return bancosMap;
	}
	
	private Map mapearPlanCuenta() {
		Map planCuentaMap = new HashMap();
		
		try {
			Iterator planCuentaIterator = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (planCuentaIterator.hasNext()) {
				PlanCuentaIf planCuenta = (PlanCuentaIf) planCuentaIterator.next();
				planCuentaMap.put(planCuenta.getId(), planCuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return planCuentaMap;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
}