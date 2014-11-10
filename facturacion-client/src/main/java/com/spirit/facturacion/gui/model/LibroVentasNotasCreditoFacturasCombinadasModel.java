package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.panel.JPLibroVentasNotasCredito;
import com.spirit.facturacion.gui.reporteData.LibroVentasNotasCreditoData;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class LibroVentasNotasCreditoFacturasCombinadasModel extends JPLibroVentasNotasCredito {
	
	private ClienteCriteria clienteCriteria;
	private static final String CODIGO_TIPO_CLIENTE   = "CL";
	protected ClienteIf clienteIf = null;
	protected ClienteOficinaIf clienteOficinaIf = null;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteCriteria medioCriteria;
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	protected ClienteIf medioIf = null;
	protected ClienteOficinaIf medioOficinaIf = null;
	private ClienteOficinaCriteria medioOficinaCriteria;
	private DefaultTableModel tableModel;
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,CorporacionIf> mapaCorporacion = new HashMap<Long,CorporacionIf>();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,DocumentoIf> mapaDocumento = new HashMap<Long,DocumentoIf>();
	private Map<Long,TipoProveedorIf> mapaTipoProveedor = new HashMap<Long,TipoProveedorIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<LibroVentasNotasCreditoData> libroVentasNotasCreditoColeccion = new Vector<LibroVentasNotasCreditoData>();
	private long start = 0L;
	private Map<Long,Map<Long,Object>> facturasPresupuestosMap = new HashMap<Long,Map<Long,Object>>();
	
	//solo una vez por cada presupuesto se ponen valores de inversion
	Map<Long,Object> contadorPresupuestosMap = new HashMap<Long,Object>();
	//solo una vez por cada cartera se ponen valores de ingreso
	Map<Long,Object> contadorCarteraAfectaMap = new HashMap<Long,Object>();
	
	public LibroVentasNotasCreditoFacturasCombinadasModel(){
		cargarMapas();
		initKeyListeners();
		showSaveMode();
		anchoColumnasTabla();
		initListeners();
	}
	
	public void cargarMapas(){
		try {
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaCorporacion.clear();
			Collection corporaciones = SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(Parametros.getIdEmpresa());
			Iterator corporacionesIt = corporaciones.iterator();
			while(corporacionesIt.hasNext()){
				CorporacionIf corporacion = (CorporacionIf)corporacionesIt.next();
				mapaCorporacion.put(corporacion.getId(), corporacion);
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator documentosIt = documentos.iterator();
			while(documentosIt.hasNext()){
				DocumentoIf documento = (DocumentoIf)documentosIt.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
			mapaTipoProveedor.clear();
			Collection tiposProveedor = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposProveedorIt = tiposProveedor.iterator();
			while(tiposProveedorIt.hasNext()){
				TipoProveedorIf tipoProveedor = (TipoProveedorIf)tiposProveedorIt.next();
				mapaTipoProveedor.put(tipoProveedor.getId(), tipoProveedor);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblFacturacion());
		getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(280);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(130);		
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(180);		
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(17);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(18);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(19);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(20);
		anchoColumna.setPreferredWidth(120);		
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(21);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(22);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(23);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(24);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(25);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(26);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(27);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(28);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(29);
		anchoColumna.setPreferredWidth(160);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(30);
		anchoColumna.setPreferredWidth(170);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(31);
		anchoColumna.setPreferredWidth(200);		
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblFacturacion().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(10).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(12).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(13).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(16).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(17).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(18).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(25).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(26).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(27).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(28).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(29).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblFacturacion().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(14).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(19).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(20).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(21).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(22).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(23).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(24).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(30).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(31).setCellRenderer(tableCellRenderer);
	}
	
	public void initKeyListeners(){
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnClienteOficina().setToolTipText("Buscar Cliente Oficina");
		getBtnProveedor() .setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnProveedorOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedorOficina().setToolTipText("Buscar Proveedor Oficina");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
	}
	
	public void initListeners(){
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTabla();
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tituloVentanaBusqueda = "Clientes";
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getNombreLegal());
					getCbTodosClientes().setSelected(false);					
					clienteOficinaIf = null;
					getTxtClienteOficina().setText("");										
				}			
			}
		});
		
		getBtnClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas de Clientes";
									
				if (clienteIf != null){
					idCliente = clienteIf.getId();
					idCorporacion = clienteIf.getCorporacionId();
				}
				
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria,	JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if(popupFinder.getElementoSeleccionado() != null){
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					
					if (clienteIf == null) {
						try {
							clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
							getTxtCliente().setText(clienteIf.getNombreLegal());
							getCbTodosClientes().setSelected(false);
							} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
					getCbTodosClientesOficina().setSelected(false);
				}
			}		
		});
		
		getCbTodosClientes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbTodosClientes().isSelected() || getBtnCliente().isEnabled()){
					if (clienteIf != null){
						clienteIf = null;
						getTxtCliente().setText(null);						
					}
					if (clienteOficinaIf != null || getBtnClienteOficina().isEnabled()){
						clienteOficinaIf = null;
						getTxtClienteOficina().setText(null);	
						getCbTodosClientesOficina().setSelected(true);
					}			
				}
			}
		});
		
		getCbTodosClientesOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbTodosClientesOficina().isSelected()){
					if (clienteOficinaIf != null){
						clienteOficinaIf = null;
						getTxtClienteOficina().setText(null);						
					}
				}
			}
		});
		
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idTipoProveedor = 0L;
				String tituloVentanaBusqueda = "Proveedores";
				medioCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, CODIGO_TIPO_PROVEEDOR);
				
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), medioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					medioIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtProveedor().setText(medioIf.getNombreLegal());
					getCbTodosProveedores().setSelected(false);
					
					medioOficinaIf = null;
					getTxtProveedorOficina().setText("");										
										
				}			
			}			
		});
		
		getCbTodosProveedores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosProveedores().isSelected()){
					medioIf = null;
					getTxtProveedor().setText("");
				}
			}
		});
		
		getBtnProveedorOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				Long idTipoProveedor = 0L;
				
				String tituloVentanaBusqueda = "Oficinas de Proveedores";
									
				if (medioIf != null){
					idCliente = medioIf.getId();
					idCorporacion = medioIf.getCorporacionId();					
				}
				
				medioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, "", false);
								
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), medioOficinaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if(popupFinder.getElementoSeleccionado() != null){
					medioOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					
					if (medioIf == null) {
						try {
							medioIf = SessionServiceLocator.getClienteSessionService().getCliente(medioOficinaIf.getClienteId());
							getTxtProveedor().setText(medioIf.getNombreLegal());
							getCbTodosProveedores().setSelected(false);
							
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getTxtProveedorOficina().setText(medioOficinaIf.getDescripcion());
					getCbTodosProveedoresOficina().setSelected(false);
				}
			}		
		});
				
		getCbTodosProveedoresOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosProveedoresOficina().isSelected()){
					medioOficinaIf = null;
					getTxtProveedorOficina().setText("");
				}
			}
		});		
	}
	

	Comparator<FacturaIf> ordenadorFacturasPorPreimpreso = new Comparator<FacturaIf>(){
		public int compare(FacturaIf o1, FacturaIf o2) {
			if(o1.getPreimpresoNumero() != null && o2.getPreimpresoNumero() != null){
				return o1.getPreimpresoNumero().compareTo(o2.getPreimpresoNumero());
			}
			return 0;						
		}		
	};
	
	private void cargarTabla() {
		try {
			libroVentasNotasCreditoColeccion.clear();
			cleanTable();
			
			//solo una vez por cada presupuesto se ponen valores de inversion
			contadorPresupuestosMap = new HashMap<Long,Object>();
			
			//solo una vez por cada cartera se ponen valores de ingreso
			contadorCarteraAfectaMap = new HashMap<Long,Object>();
			
			//tipo documento comprobante de retencion de compras
			TipoDocumentoIf tipoDocumentoCRE = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("CRE").iterator().next();
			
			Collection<Object[]> facturasPedidos = generarColeccionFacturasPedidos();
			Iterator facturasPedidosIt = facturasPedidos.iterator();
			
			//mapa facturaId - factura
			Map<Long, FacturaIf> facturaIdFacturaMap = new HashMap<Long, FacturaIf>();
			
			//armo un mapa de facturaId con su pedido, me va a servir para saber tipo referencia 
			//que indique de donde viene la factura, de un presupuesto o de un plan de medios
			//P(Presupuesto), I(Plan de Medios), N(Ninguno)
			Map<Long, PedidoIf> facturaPedidoMap = new HashMap<Long, PedidoIf>();
			
			//armo un mapa con la factura y sus ingresos (cheques, retenciones)
			Map<Long, Map<Long, CarteraIf>> facturasIngresos = new HashMap<Long, Map<Long, CarteraIf>>();
			
			//mapa factura carteras afecta
			Map<Long, Map<Long, Map<Long, CarteraAfectaIf>>> facturaCarterasAfectaMap = new HashMap<Long, Map<Long, Map<Long, CarteraAfectaIf>>>();
			
			//mapa facturas presupuestos
			facturasPresupuestosMap = new HashMap<Long,Map<Long,Object>>();
			
			//mapa factura ProductoCliente
			Map<Long, Map<Long, ProductoClienteIf>> facturaProductosClienteMap = new HashMap<Long, Map<Long, ProductoClienteIf>>();
			
			//mapa ordenCompraId, presupuestoId
			Map<Long,Long> ordenIdPresupuestoIdMap = new HashMap<Long,Long>();
			
			//mapa factura ordenes compra
			Map<Long, Map<Long, OrdenCompraIf>> facturaOrdenesCompraMap = new HashMap<Long, Map<Long, OrdenCompraIf>>();
			
			//mapa factura ordenes medio
			Map<Long, Map<Long, OrdenMedioIf>> facturaOrdenesMedioMap = new HashMap<Long, Map<Long, OrdenMedioIf>>();
			
			//mapa plan medioo ordenes medio
			Map<Long, Map<Long, OrdenMedioIf>> planMedioOrdenesMedioMap = new HashMap<Long, Map<Long, OrdenMedioIf>>();
						
			//vector FacturaDetalleIf
			Vector<FacturaDetalleIf> facturasDetalleVector = new Vector<FacturaDetalleIf>();
			
			//mapa Proveedores en Factura
			//Hay casos donde un solo pedidoIf esta relacionado a mas de una factura
			//entonces cuando es un plan de medios no hay como saber que medios son con cada factura
			//la idea es comparar el producto de la factura detalle con el producto del medio
			Map<Long,Long> productosProveedorIdMap = new HashMap<Long,Long>();
			Map<Long,Map<Long,Long>> facturaProductosProveedorIdMap = new HashMap<Long,Map<Long,Long>>();
			
			//mapa facturaId, vector FacturaDetalleIf
			Map<Long, Vector<FacturaDetalleIf>> facturaFacturasDetalleMap = new HashMap<Long, Vector<FacturaDetalleIf>>();
						
			while(facturasPedidosIt.hasNext()){
				Object[] facturaPedido = (Object[])facturasPedidosIt.next();			
				FacturaIf facturaIf = (FacturaIf)facturaPedido[0];
				PedidoIf pedidoIf = (PedidoIf)facturaPedido[1];
				FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf)facturaPedido[2];
				
				//MAPA FACTURA ID - FACTURA
				facturaIdFacturaMap.put(facturaIf.getId(), facturaIf);
				
				//MAPA FACTURA - PEDIDO
				facturaPedidoMap.put(facturaIf.getId(), pedidoIf);
				
				//mapa facturaId, vector FacturaDetalleIf				
				if(facturaFacturasDetalleMap.get(facturaIf.getId()) == null){
					facturasDetalleVector = new Vector<FacturaDetalleIf>();
					facturasDetalleVector.add(facturaDetalleIf);
					facturaFacturasDetalleMap.put(facturaIf.getId(), facturasDetalleVector);
				}else{
					facturasDetalleVector = facturaFacturasDetalleMap.get(facturaIf.getId());
					facturasDetalleVector.add(facturaDetalleIf);
					facturaFacturasDetalleMap.put(facturaIf.getId(), facturasDetalleVector);
				}	
				
				//mapa facturaId, mapa productosProveedorIdMap
				if(facturaProductosProveedorIdMap.get(facturaIf.getId()) == null){
					productosProveedorIdMap = new HashMap<Long,Long>();
					productosProveedorIdMap.put(facturaDetalleIf.getProductoId(), facturaDetalleIf.getProductoId());
					facturaProductosProveedorIdMap.put(facturaIf.getId(), productosProveedorIdMap);
				}else{
					productosProveedorIdMap = facturaProductosProveedorIdMap.get(facturaIf.getId());
					productosProveedorIdMap.put(facturaDetalleIf.getProductoId(), facturaDetalleIf.getProductoId());
					facturaProductosProveedorIdMap.put(facturaIf.getId(), productosProveedorIdMap);
				}
			}
			
			Iterator facturaIdFacturaMapIt1 = facturaIdFacturaMap.keySet().iterator();
			while(facturaIdFacturaMapIt1.hasNext()){
				Long facturaId = (Long)facturaIdFacturaMapIt1.next();
				FacturaIf facturaIf = facturaIdFacturaMap.get(facturaId);
				PedidoIf pedidoIf = facturaPedidoMap.get(facturaId);
				
				if(facturaIf == null){
					System.out.println("ver");
				}
								
				//mapa ingresoId ingresoIf
				Map<Long, CarteraIf> carteraIngresoMap = new HashMap<Long, CarteraIf>();
								
				//MAPA FACTURA - INGRESOS
				Collection<Object[]> ingresos = SessionServiceLocator.getCarteraSessionService().findCarterasCarterasAfectaQueCruzanReferenciaId(facturaIf.getId(), facturaIf.getTipodocumentoId());
				Iterator<Object[]> ingresosIt = ingresos.iterator();	
				
				//Mapa carteras afecta
				Map<Long, CarteraAfectaIf> carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
				
				//mapa del ingreso que cruza con vector de sus carteras afecta
				Map<Long, Map<Long, CarteraAfectaIf>> carteraCruceCarteraAfectaMap = new HashMap<Long, Map<Long, CarteraAfectaIf>>();
								
				while(ingresosIt.hasNext()){
					Object[] carterasCruce = ingresosIt.next();
					CarteraIf carteraIngreso = (CarteraIf)carterasCruce[0];
					CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasCruce[1];
								
					if(carteraCruceCarteraAfectaMap.get(carteraIngreso.getId()) == null){
						carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
						carterasAfecta.put(carteraAfecta.getId(), carteraAfecta);
						carteraCruceCarteraAfectaMap.put(carteraIngreso.getId(), carterasAfecta);
					}else{
						carterasAfecta = carteraCruceCarteraAfectaMap.get(carteraIngreso.getId());
						carterasAfecta.put(carteraAfecta.getId(), carteraAfecta);
						carteraCruceCarteraAfectaMap.put(carteraIngreso.getId(), carterasAfecta);
					}						
					carteraIngresoMap.put(carteraIngreso.getId(), carteraIngreso);
					facturasIngresos.put(facturaIf.getId(), carteraIngresoMap);
				}				
				
				facturaCarterasAfectaMap.put(facturaIf.getId(), carteraCruceCarteraAfectaMap);
				
				//MAPA FACTURA - ORDENES
				
				String tipoReferencia = pedidoIf.getTiporeferencia();
							
				PlanMedioIf planMedioIf = null;
				
				//mapa de productos proveedor en las facturaDetalle de la factura actual
				productosProveedorIdMap = facturaProductosProveedorIdMap.get(facturaId);
				
				boolean referenciaEsPresupuesto = false;
								
				if(tipoReferencia.equals("P") || tipoReferencia.equals("C")){
					//busco presupuesto, presupuesto detalle y producto cliente (no busco orden de compra porque puede que no tenga)
					if(facturaIf.getPreimpresoNumero().equals("002-001-000009453")){
						System.out.println("aaab");
					}
					Object[] presupuestoDetalleProducto = buscarPresupuestoDetalleProducto(facturaIf.getId(), facturaIf.getPedidoId());
					
					//Presupuestos
					//facturasPresupuestosMap = (Map<Long, Map<Long, Object>>)presupuestoDetalleProducto[0];
					//CAMBIO PORQUE AHORA MUCHOS PRESUPUESTOS SE ASOCIAN A UNA FACTURA
					if(presupuestoDetalleProducto[0] == null){
						System.out.println("aaa");
					}
					if(presupuestoDetalleProducto[0] != null){
						referenciaEsPresupuesto = true;
						
						facturasPresupuestosMap.putAll((Map<Long, Map<Long, Object>>)presupuestoDetalleProducto[0]);
						
						//ProductoCliente Map
						if(presupuestoDetalleProducto[1] != null){
							facturaProductosClienteMap.putAll((Map<Long, Map<Long, ProductoClienteIf>>)presupuestoDetalleProducto[1]);
						}
						//OrdenesCompra Map
						if(presupuestoDetalleProducto[2] != null){
							facturaOrdenesCompraMap.putAll((Map<Long, Map<Long, OrdenCompraIf>>)presupuestoDetalleProducto[2]);			
						}
						//OrdenCompraId, PresupuestoId Map
						if(presupuestoDetalleProducto[3] != null){
							ordenIdPresupuestoIdMap.putAll((Map<Long,Long>)presupuestoDetalleProducto[3]);			
						}
					}					
				}
				
				if(pedidoIf.getId().compareTo(27405L) == 0){
					System.out.println("aaa3");
				}
								
				if(!referenciaEsPresupuesto && (tipoReferencia.equals("I") || tipoReferencia.equals("C"))){
					//busco plan de medio, producto cliente y de una vez ordenes de medio porque siempre un plan genera ordenes
					
					//mapa productoClienteId, ProductoClienteIf
					Map<Long, ProductoClienteIf> productosClienteMap = new HashMap<Long, ProductoClienteIf>();
					//mapa ordenMedioId, OrdenCompraIf
					Map<Long, OrdenMedioIf> ordenesCompraMap = new HashMap<Long, OrdenMedioIf>();
					
					Long medioOficinaId = 0L;
					Long medioId = 0L;
					if(medioOficinaIf != null){
						medioOficinaId = medioOficinaIf.getId();
					}else if(medioIf != null){
						medioId = medioIf.getId();
					}
					
					//vector OrdenMedioDetalleIf
					Vector<OrdenMedioDetalleIf> ordenesDetalleVector = new Vector<OrdenMedioDetalleIf>();					
					//mapa ordenId, vector OrdenMedioDetalleIf
					Map<Long, Vector<OrdenMedioDetalleIf>> ordenOrdenesDetalleMap = new HashMap<Long, Vector<OrdenMedioDetalleIf>>();
					//mapa presupuestoId, Presupuesto Objecto
					Map<Long, Object> presupuestosObjectMap = new HashMap<Long, Object>();
					
					Collection planProductoOrdenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioPlanMedioProductoClienteByPedidoIdByMedioIdByMedioOficinaId(pedidoIf.getId(), medioId, medioOficinaId);
					
					
					if(planProductoOrdenesMedio.size() > 0){
						Iterator planProductoOrdenesMedioIt = planProductoOrdenesMedio.iterator();
						while(planProductoOrdenesMedioIt.hasNext()){
							Object[] planProductoOrdenMedio = (Object[])planProductoOrdenesMedioIt.next();
							//Plan de Medio
							planMedioIf = (PlanMedioIf)planProductoOrdenMedio[0];
							//Producto Cliente vector
							ProductoClienteIf productoClienteIf = (ProductoClienteIf)planProductoOrdenMedio[1];
							productosClienteMap.put(productoClienteIf.getId(), productoClienteIf);
							//Orden Medio vector
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf)planProductoOrdenMedio[2];
							
							//si el producto proveedor esta entre los productos proveedores de los detalles de la factura
							//entonces esta orden de medios si esta relacionada a la factura
							//AQUI HAY UN PROBLEMA
							if(productosProveedorIdMap.get(ordenMedioIf.getProductoProveedorId()) != null){
								ordenesCompraMap.put(ordenMedioIf.getId(),ordenMedioIf);
							}						
							
							//CAMBIO PORQUE AHORA MUCHOS PRESUPUESTOS SE ASOCIAN A UNA FACTURA
							if(facturasPresupuestosMap.get(facturaIf.getId()) == null){
								presupuestosObjectMap.put(planMedioIf.getId(), planMedioIf);
								facturasPresupuestosMap.put(facturaIf.getId(), presupuestosObjectMap);
							}else{
								presupuestosObjectMap = facturasPresupuestosMap.get(facturaIf.getId());
								presupuestosObjectMap.put(planMedioIf.getId(), planMedioIf);
								facturasPresupuestosMap.put(facturaIf.getId(), presupuestosObjectMap);
							}
						}
						facturaProductosClienteMap.put(facturaIf.getId(), productosClienteMap);
						facturaOrdenesMedioMap.put(facturaIf.getId(), ordenesCompraMap);	
						
						//puede pasar que la factura este asociada a un plan que luego de ser facturado fue reemplazado
						//entonces ese plan queda como historico y hay que buscar el plan actual para ver si no hay
						//ordenes nuevas que no han sido consideradas, para esto necesito un mapa de los planes medio
						//asociados a sus ordenes
						Map<Long, OrdenMedioIf> ordenesMedioMap = new HashMap<Long, OrdenMedioIf>();
						Iterator ordenesCompraMapIt = ordenesCompraMap.keySet().iterator();
						while(ordenesCompraMapIt.hasNext()){
							Long ordenMedioId = (Long)ordenesCompraMapIt.next();
							OrdenMedioIf ordenMedioIf = ordenesCompraMap.get(ordenMedioId);
							if(ordenMedioIf.getPlanMedioId().compareTo(planMedioIf.getId()) == 0){
								ordenesMedioMap.put(ordenMedioId, ordenMedioIf);
							}
						}				
						
						if(planMedioIf == null){
							System.out.println("aaa2");
						}
						
						if(planMedioOrdenesMedioMap.get(planMedioIf.getId()) == null){							
							planMedioOrdenesMedioMap.put(planMedioIf.getId(), ordenesMedioMap);
						}else{
							Map<Long, OrdenMedioIf> ordenesMedioMapTemp = planMedioOrdenesMedioMap.get(planMedioIf.getId());
							ordenesMedioMapTemp.putAll(ordenesMedioMap);
							planMedioOrdenesMedioMap.put(planMedioIf.getId(), ordenesMedioMapTemp);
						}
					}
					
				}
			}
						
			//INFORMACION RECOPILADA///////////////////////////////////////////////
			//facturaIdFacturaMap.put(facturaIf.getId(), facturaIf);
			//facturaPedidoMap.put(facturaIf.getId(), pedidoIf);
			//facturaFacturasDetalleMap.put(facturaIf.getId(), facturasDetalleVector);
			//facturaPresupuestoMap.put(facturaIf.getId(), presupuestoIf);
			//facturaPlanMediosMap.put(facturaIf.getId(), planMedioIf);	
			//facturasIngresos.put(facturaIf, carteraCruceCarteraAfectaMap);
			//facturaCarterasAfectaMap.put(facturaIf.getId(), carteraCruceCarteraAfectaMap);
			//facturaProductosClienteMap.put(facturaIf.getId(), productosClienteMap);
			//facturaOrdenesCompraMap.put(facturaIf.getId(), ordenesCompraMap);
			//facturaOrdenesMedioMap.put(facturaIf.getId(), ordenesCompraMap);
			
			//RECORRO TODAS LAS FACTURAS ENCONTRADAS
			
			//primero las guardo en una lista para poder ordenarlas
			ArrayList<FacturaIf> facturasOrdenadas = new ArrayList<FacturaIf>();
			Iterator<Long> facturaIdFacturaMapIt2 = facturaIdFacturaMap.keySet().iterator();
			while(facturaIdFacturaMapIt2.hasNext()){
				Long facturaId = facturaIdFacturaMapIt2.next();
				FacturaIf facturaIf = facturaIdFacturaMap.get(facturaId);
			
				facturasOrdenadas.add(facturaIf);
			}
			
			//ordeno por preimpreso
			Collections.sort((ArrayList)facturasOrdenadas,ordenadorFacturasPorPreimpreso);
						
			//mapa de codigos de ordenes de medios que me servira cuando hay ordenes nuevas en planes que 
			//reemplazaron a otros planes ya facturados
			Map<String, String> mapaOrdenMedioCodigo = new HashMap<String,String>();
			
			for(FacturaIf facturaIf : facturasOrdenadas){
				Long facturaId = facturaIf.getId();
				
				PedidoIf pedidoIf = facturaPedidoMap.get(facturaIf.getId());
				//PresupuestoIf presupuestoIf = null;
				//PlanMedioIf planMedioIf = null;
				
				Map<Long, Object> presupuestosObjectMap = facturasPresupuestosMap.get(facturaId);
				
				//PRODUCTO CLIENTE
				String productoCliente = "";
				Map productoClienteMap = facturaProductosClienteMap.get(facturaId);
				if(productoClienteMap != null){
					Iterator productoClienteMapIt = productoClienteMap.keySet().iterator();
					while(productoClienteMapIt.hasNext()){
						Long productoClienteId = (Long)productoClienteMapIt.next();
						ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteMap.get(productoClienteId);
						if(productoCliente.equals("")){
							productoCliente = productoClienteIf.getNombre();
						}else{
							productoCliente = productoCliente + ", " + productoClienteIf.getNombre();
						}
					}
				}
				
				//inicializo mapas de ordenes
				Map<Long, OrdenCompraIf> ordenesCompraMap = facturaOrdenesCompraMap.get(facturaId);
				Map<Long, OrdenMedioIf> ordenesMedioMap = facturaOrdenesMedioMap.get(facturaId);
				
				boolean agregarFila = false;
				
				BigDecimal inversionPlanMedio = new BigDecimal(0);
				Map<Long,BigDecimal> inversionPlanMedioMap = new HashMap<Long,BigDecimal>();
				
				//INVERSION PLAN DE MEDIO
				if(ordenesMedioMap != null){
					
					//valor, iva, total
					BigDecimal subtotalPlanMedio = new BigDecimal(0);
					BigDecimal ivaSubTotal = new BigDecimal(0);
					BigDecimal porcentajeDescuentoVentaPlan = new BigDecimal(0); 
					BigDecimal porcentajeComisionAgenciaPlan = new BigDecimal(0); 
					
					BigDecimal descuentoVentaPlan = new BigDecimal(0);
					BigDecimal comisionAgenciaPlan = new BigDecimal(0);
					BigDecimal subTotal = new BigDecimal(0);
										
					double ivaActual = Parametros.getIVA() / 100D; 
					
					Iterator ordenesMedioMapIt1 = ordenesMedioMap.keySet().iterator();
					while(ordenesMedioMapIt1.hasNext()){
						Long ordenMedioId = (Long)ordenesMedioMapIt1.next();
						OrdenMedioIf ordenMedioIf = ordenesMedioMap.get(ordenMedioId);
						
						subtotalPlanMedio = ordenMedioIf.getValorSubtotal();
						//estos porcentajes son los mismos en todos los detalles
						porcentajeDescuentoVentaPlan = ordenMedioIf.getPorcentajeDescuentoVenta();
						porcentajeComisionAgenciaPlan = ordenMedioIf.getPorcentajeComisionAgencia();						
						
						//valor	
						descuentoVentaPlan = subtotalPlanMedio.multiply(porcentajeDescuentoVentaPlan.divide(new BigDecimal(100)));
						comisionAgenciaPlan = (subtotalPlanMedio.subtract(descuentoVentaPlan)).multiply(porcentajeComisionAgenciaPlan.divide(new BigDecimal(100)));
						subTotal = subtotalPlanMedio.subtract(descuentoVentaPlan).add(comisionAgenciaPlan);
						//iva						
						ivaSubTotal = subTotal.multiply(BigDecimal.valueOf(ivaActual));
						
						//si se quiere un solo total
						//inversionPlanMedio = inversionPlanMedio.add(subTotal.add(ivaSubTotal));
						
						if(inversionPlanMedioMap.get(ordenMedioIf.getPlanMedioId()) == null){
							inversionPlanMedioMap.put(ordenMedioIf.getPlanMedioId(), subTotal.add(ivaSubTotal));
						}else{
							BigDecimal subTotalAnterior = inversionPlanMedioMap.get(ordenMedioIf.getPlanMedioId());
							inversionPlanMedioMap.put(ordenMedioIf.getPlanMedioId(), subTotalAnterior.add(subTotal.add(ivaSubTotal)));
						}
					}				
				}
				
				
				//este tipoMedio, medio va a servir en los casos donde no haya ordenes
				String tipoMedio = "";
				String medio = "";				
				
				//solo una vez por cada factura se ponen datos como preimpreso, fechas y valores de la factura
				int contador = 0;
				
				//asi hayan muchas ordenes solo seteo valores de compra una vez
				int contadorOrdenes = 0;
				
				//asi hayan muchos afectas solo seteo valores de compra una vez
				int contadorAfectas = 0;
				
				//Ingresos Map
				Map<Long, CarteraIf> carteraIngresoMap = facturasIngresos.get(facturaId);
				
				if(presupuestosObjectMap == null){
					System.out.println("aaa4");
				}				
				
				//INGRESOS
				if(presupuestosObjectMap != null){
					//como pueden haber varios presupuestos en una sola factura se ingresa por cada uno
					Iterator presupuestosObjectMapIt = presupuestosObjectMap.keySet().iterator();
					while(presupuestosObjectMapIt.hasNext()){
						Long presupuestoId = (Long)presupuestosObjectMapIt.next();
						Object presupuestoObject = (Object)presupuestosObjectMap.get(presupuestoId);
						PresupuestoIf presupuestoIf = null;
						PlanMedioIf planMedioIf = null;
						
						if(presupuestoObject.getClass().toString().equals("class com.spirit.medios.entity.PlanMedioEJB")){
							planMedioIf = (PlanMedioIf)presupuestoObject;
						}else if(presupuestoObject.getClass().toString().equals("class com.spirit.medios.entity.PresupuestoEJB")){
							presupuestoIf = (PresupuestoIf)presupuestoObject;
						}
															
						//INGRESOS
						if(carteraIngresoMap != null && carteraIngresoMap.size() > 0 && getCbVerIngresos().isSelected()){
							
							//cargo inversion segun el plan
							inversionPlanMedio = planMedioIf != null? inversionPlanMedioMap.get(planMedioIf.getId()) : new BigDecimal(0);
							
							contadorOrdenes = 0;
							contadorAfectas = 0;
							
							//facturas detalle para saber tipo de medio y medio en los casos donde no hay ordenes
							if(ordenesCompraMap == null && ordenesMedioMap == null){
								Object[] tipoMedioObject = buscarTipoMedio(facturaFacturasDetalleMap, facturaId);
								tipoMedio = (String)tipoMedioObject[0];
								medio = (String)tipoMedioObject[1];
							}					
							
							//carteras afecta relacionadas a la factura
							Map carteraCruceCarteraAfectaMap = facturaCarterasAfectaMap.get(facturaId);
														
							Iterator carteraIngresoMapIt = carteraIngresoMap.keySet().iterator();
							while(carteraIngresoMapIt.hasNext()){
								Long carteraIngresoId = (Long)carteraIngresoMapIt.next();
								CarteraIf carteraIngresoIf = carteraIngresoMap.get(carteraIngresoId);
								Map<Long, CarteraAfectaIf> carterasAfecta = (Map<Long, CarteraAfectaIf>)carteraCruceCarteraAfectaMap.get(carteraIngresoIf.getId());
								Iterator carterasAfectaIt = carterasAfecta.keySet().iterator();
								while(carterasAfectaIt.hasNext()){
									Long carteraAfectaId = (Long)carterasAfectaIt.next();
									CarteraAfectaIf carteraAfectaIf = carterasAfecta.get(carteraAfectaId);
									
									tableModel = (DefaultTableModel) getTblFacturacion().getModel();
									Vector<String> fila = new Vector<String>();			
																
									//cuento las veces que he ingresado a llenar datos de este factura
									contador++;
									
									if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
										System.out.println("a");
									}
									
									agregarColumnasTabla(facturaIf, fila, carteraIngresoIf, carteraAfectaIf, contador, pedidoIf, presupuestoIf, 
											planMedioIf, productoCliente, null, null, null, tipoMedio, medio, inversionPlanMedio, null, null, "", "", 
											contadorAfectas, contadorOrdenes, null, false, false);
									
									tableModel.addRow(fila);
									
									agregarFila = true;
								}
							}						
						}	
					}
				}else{
					//INGRESOS
					if(carteraIngresoMap != null && carteraIngresoMap.size() > 0 && getCbVerIngresos().isSelected()){
						
						//cargo inversion segun el plan
						inversionPlanMedio = new BigDecimal(0);
						
						contadorOrdenes = 0;
						contadorAfectas = 0;
						
						//facturas detalle para saber tipo de medio y medio en los casos donde no hay ordenes
						if(ordenesCompraMap == null && ordenesMedioMap == null){
							Object[] tipoMedioObject = buscarTipoMedio(facturaFacturasDetalleMap, facturaId);
							tipoMedio = (String)tipoMedioObject[0];
							medio = (String)tipoMedioObject[1];
						}					
						
						//carteras afecta relacionadas a la factura
						Map carteraCruceCarteraAfectaMap = facturaCarterasAfectaMap.get(facturaId);
													
						Iterator carteraIngresoMapIt = carteraIngresoMap.keySet().iterator();
						while(carteraIngresoMapIt.hasNext()){
							Long carteraIngresoId = (Long)carteraIngresoMapIt.next();
							CarteraIf carteraIngresoIf = carteraIngresoMap.get(carteraIngresoId);
							Map<Long, CarteraAfectaIf> carterasAfecta = (Map<Long, CarteraAfectaIf>)carteraCruceCarteraAfectaMap.get(carteraIngresoIf.getId());
							Iterator carterasAfectaIt = carterasAfecta.keySet().iterator();
							while(carterasAfectaIt.hasNext()){
								Long carteraAfectaId = (Long)carterasAfectaIt.next();
								CarteraAfectaIf carteraAfectaIf = carterasAfecta.get(carteraAfectaId);
								
								tableModel = (DefaultTableModel) getTblFacturacion().getModel();
								Vector<String> fila = new Vector<String>();			
															
								//cuento las veces que he ingresado a llenar datos de este factura
								contador++;
								agregarColumnasTabla(facturaIf, fila, carteraIngresoIf, carteraAfectaIf, contador, pedidoIf, null, 
										null, productoCliente, null, null, null, tipoMedio, medio, inversionPlanMedio, null, null, "", "", 
										contadorAfectas, contadorOrdenes, null, false, false);
								
								tableModel.addRow(fila);
								
								agregarFila = true;
							}
						}						
					}
				}
				
				
				//EGRESOS
				if(presupuestosObjectMap != null){
					//como pueden haber varios presupuestos en una sola factura se ingresa por cada uno
					Iterator presupuestosObjectMapItOrdenes = presupuestosObjectMap.keySet().iterator();
					while(presupuestosObjectMapItOrdenes.hasNext()){
						Long presupuestoId = (Long)presupuestosObjectMapItOrdenes.next();
						Object presupuestoObject = (Object)presupuestosObjectMap.get(presupuestoId);
						PresupuestoIf presupuestoIf = null;
						PlanMedioIf planMedioIf = null;
						
						if(presupuestoObject.getClass().toString().equals("class com.spirit.medios.entity.PlanMedioEJB")){
							planMedioIf = (PlanMedioIf)presupuestoObject;
						}else if(presupuestoObject.getClass().toString().equals("class com.spirit.medios.entity.PresupuestoEJB")){
							presupuestoIf = (PresupuestoIf)presupuestoObject;
						}					
						
						//ORDENES DE COMPRA
						if(ordenesCompraMap != null && getCbVerEgresos().isSelected()){
							
							contadorOrdenes = 0;
							
							Iterator ordenesCompraMapIt = ordenesCompraMap.keySet().iterator();
							while(ordenesCompraMapIt.hasNext()){
								Long ordenCompraId = (Long)ordenesCompraMapIt.next();
								
								if(ordenIdPresupuestoIdMap.get(ordenCompraId) != null && ordenIdPresupuestoIdMap.get(ordenCompraId).compareTo(presupuestoId) == 0){
									OrdenCompraIf ordenCompraIf = ordenesCompraMap.get(ordenCompraId);
									
									ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(ordenCompraIf.getProveedorId());
									ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
									TipoProveedorIf tipoProveedorIf = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
									tipoMedio = tipoProveedorIf.getNombre();
									medio = proveedorOficinaIf.getDescripcion();
									
									Map aMap = new HashMap();
									aMap.put("tipoOrden", "OC");
							    	aMap.put("ordenId", ordenCompraIf.getId());
									Collection compras = SessionServiceLocator.getCompraSessionService().findCompraByOrdenAsociadaQuery(aMap);
									
									//si tiene compra asociada aparece aun si la orden tiene estado anulada
									if(compras.size() > 0){
										Iterator comprasIt = compras.iterator();
										while(comprasIt.hasNext()){
											CompraIf compraIf = (CompraIf)comprasIt.next();
											
											//reviso si la compra esta referenciada en una nota de credito
											NotaCreditoIf notaCreditoReferencia = null;
											boolean notaCreditoRelacionadaAfecta = false;
											
											Map compraReferenciadaMap = new HashMap();
											compraReferenciadaMap.put("tipoCartera", "P");
											compraReferenciadaMap.put("referenciaId", compraIf.getId());
											Collection comprasReferenciadas = SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByQuery(compraReferenciadaMap);
											Iterator comprasReferenciadasIt = comprasReferenciadas.iterator();
											while(comprasReferenciadasIt.hasNext()){
												notaCreditoReferencia = (NotaCreditoIf)comprasReferenciadasIt.next();
											}
											
											//egresos de la compra
											Collection<Object[]> egresos = SessionServiceLocator.getCarteraSessionService().findCarterasCarterasAfectaQueCruzanReferenciaId(compraIf.getId(), compraIf.getTipodocumentoId());
											Iterator<Object[]> egresosIt = egresos.iterator();	
											
											//PRIMERO CREO UN MAPA CON EGRESOS Y SUS AFECTA, SIN TOMAR EN CUENTA LAS RETENCIONES
											//LUEGO RECORRO EL MAPA FORMADO PARA SETEAR INFORMACION EN EL REPORTE
											Map<Long, CarteraAfectaIf> carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
											
											//mapa del egreso que cruza con vector de sus carteras afecta
											Map<Long, Map<Long, CarteraAfectaIf>> carteraCruceCarteraAfectaMap = new HashMap<Long, Map<Long, CarteraAfectaIf>>();
																
											//mapa egresoId egresoIf
											Map<Long, CarteraIf> carteraEgresoMap = new HashMap<Long, CarteraIf>();
											
											//mapa egresoDetalleId egresoDetalleIf
											//Map<Long, CarteraDetalleIf> carteraEgresoDetalleMap = new HashMap<Long, CarteraDetalleIf>();
											
											//mapa del egreso con sus carteras detalle
											//Map<Long, Map<Long, CarteraDetalleIf>> egresoCarteraDetalleMap = new HashMap<Long, Map<Long, CarteraDetalleIf>>();
											
											//mapa carteraafecta con su forma de pago y cheque
											Map<Long, String[]> afectaFormaPagoChequeMap = new HashMap<Long, String[]>();
											
											while(egresosIt.hasNext()){
												Object[] carterasCruce = egresosIt.next();
												CarteraIf carteraEgreso = (CarteraIf)carterasCruce[0];
												CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasCruce[1];
												CarteraDetalleIf carteraEgresoDetalle = (CarteraDetalleIf)carterasCruce[2];
												
												//reviso si la nota de credito relacionada afecta la compra
												if(notaCreditoReferencia != null && carteraEgreso.getReferenciaId() != null 
														&& notaCreditoReferencia.getId().compareTo(carteraEgreso.getReferenciaId()) == 0 
														&& notaCreditoReferencia.getTipoDocumentoId().compareTo(carteraEgreso.getTipodocumentoId()) == 0 ){
													notaCreditoRelacionadaAfecta = true;
												}									
																					
												//si la cartera no es una retencion, porque retenciones se pone directo valores de renta e iva.
												if(carteraEgreso.getTipodocumentoId().compareTo(tipoDocumentoCRE.getId()) != 0){
													//egresos carteras afecta
													if(carteraCruceCarteraAfectaMap.get(carteraEgreso.getId()) == null){
														carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
													}else{
														carterasAfecta = carteraCruceCarteraAfectaMap.get(carteraEgreso.getId());
													}
													carterasAfecta.put(carteraAfecta.getId(), carteraAfecta);
													carteraCruceCarteraAfectaMap.put(carteraEgreso.getId(), carterasAfecta);
													
													//egresos
													carteraEgresoMap.put(carteraEgreso.getId(), carteraEgreso);	
													
													//afecta con forma de pago y cheque
													DocumentoIf documento = mapaDocumento.get(carteraEgresoDetalle.getDocumentoId());
													TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(documento.getTipoDocumentoId());
													
													String[] formaPagoCheque = new String[2];
													//if(documento.getCodigo().equals("NCPR") || documento.getCodigo().equals("NICP")){
													if(tipoDocumento.getCodigo().equals("NCP") || tipoDocumento.getCodigo().equals("ICP")){
														formaPagoCheque[0] = "NOTA DE CREDITO";
														formaPagoCheque[1] = "";
													}else if(carteraEgresoDetalle.getChequeBancoId() != null){
														formaPagoCheque[0] = "CHEQUE";
														formaPagoCheque[1] = carteraEgresoDetalle.getChequeNumero();			
													}else if(carteraEgresoDetalle.getDebitoBancoId() != null){
														formaPagoCheque[0] = "DEBITO";
														formaPagoCheque[1] = "";											
													}else{
														formaPagoCheque[0] = "EFECTIVO";
														formaPagoCheque[1] = "";											
													}
													afectaFormaPagoChequeMap.put(carteraAfecta.getId(), formaPagoCheque);
												}										
											}
																			
											if(carteraCruceCarteraAfectaMap.size() > 0){
												
												contadorAfectas = 0;
																				
												Iterator carteraCruceCarteraAfectaMapIt = carteraCruceCarteraAfectaMap.keySet().iterator();
												while(carteraCruceCarteraAfectaMapIt.hasNext()){
													Long carteraEgresoId = (Long)carteraCruceCarteraAfectaMapIt.next();
													CarteraIf carteraEgreso = carteraEgresoMap.get(carteraEgresoId);
													
													//reviso si la nota de credito relacionada afecta la compra
													boolean notaCreditoRelacionadaAfectacion = false;
													if(notaCreditoRelacionadaAfecta && notaCreditoReferencia != null && carteraEgreso.getReferenciaId() != null 
															&& notaCreditoReferencia.getId().compareTo(carteraEgreso.getReferenciaId()) == 0 
															&& notaCreditoReferencia.getTipoDocumentoId().compareTo(carteraEgreso.getTipodocumentoId()) == 0 ){
														notaCreditoRelacionadaAfectacion = true;
													}	
													
													Map<Long, CarteraAfectaIf> carterasAfectaCompra = carteraCruceCarteraAfectaMap.get(carteraEgresoId);
													Iterator carterasAfectaCompraIt = carterasAfectaCompra.keySet().iterator();
													while(carterasAfectaCompraIt.hasNext()){
														Long carteraAfectaId = (Long)carterasAfectaCompraIt.next();
														CarteraAfectaIf carteraAfecta = carterasAfectaCompra.get(carteraAfectaId);
														
														String[] formaPagoCheque = afectaFormaPagoChequeMap.get(carteraAfectaId);
														
														tableModel = (DefaultTableModel) getTblFacturacion().getModel();
														Vector<String> fila = new Vector<String>();			
														
														//cuento las veces que he ingresado a llenar datos de este factura
														contador++;
														contadorOrdenes++;
														contadorAfectas++;
														
														if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
															System.out.println("a");
														}
														
														agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
																productoCliente, ordenCompraIf, null, compraIf, tipoMedio, medio, inversionPlanMedio, 
																carteraEgreso, carteraAfecta, formaPagoCheque[0], formaPagoCheque[1], contadorAfectas, 
																contadorOrdenes, notaCreditoReferencia, notaCreditoRelacionadaAfecta, notaCreditoRelacionadaAfectacion);
														
														tableModel.addRow(fila);
														
														agregarFila = true;
													}
												}								
												
											}else{
												tableModel = (DefaultTableModel) getTblFacturacion().getModel();
												Vector<String> fila = new Vector<String>();			
												
												//cuento las veces que he ingresado a llenar datos de este factura
												contador++;
												contadorOrdenes++;
												contadorAfectas = 0;
												if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
													System.out.println("a");
												}
												agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
														productoCliente, ordenCompraIf, null, compraIf, tipoMedio, medio, inversionPlanMedio, null, 
														null, "", "", contadorAfectas, contadorOrdenes, notaCreditoReferencia, false, false);
												
												tableModel.addRow(fila);
												
												agregarFila = true;
											}
										}		
									}
									//si la orden no esta anulada
									else if(!ordenCompraIf.getEstado().equals("A")){
										tableModel = (DefaultTableModel) getTblFacturacion().getModel();
										Vector<String> fila = new Vector<String>();			
										
										//cuento las veces que he ingresado a llenar datos de este factura
										contador++;
										contadorOrdenes++;
										contadorAfectas = 0;
										if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
											System.out.println("a");
										}
										agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
												productoCliente, ordenCompraIf, null, null, tipoMedio, medio, inversionPlanMedio, null, 
												null, "", "", contadorAfectas, contadorOrdenes, null, false, false);
										
										tableModel.addRow(fila);
										
										agregarFila = true;
									}
								}				
							}
						}		
						
						
						//ORDENES DE MEDIO
						if(ordenesMedioMap != null && getCbVerEgresos().isSelected()){
							
							contadorOrdenes = 0;
							
							Iterator ordenesMedioMapIt2 = ordenesMedioMap.keySet().iterator();
							while(ordenesMedioMapIt2.hasNext()){
								Long ordenMedioId = (Long)ordenesMedioMapIt2.next();
								OrdenMedioIf ordenMedioIf = ordenesMedioMap.get(ordenMedioId);
								
								if(planMedioIf != null && ordenMedioIf.getPlanMedioId().compareTo(planMedioIf.getId()) == 0){
									
									//cargo inversion segun el plan
									inversionPlanMedio = inversionPlanMedioMap.get(ordenMedioIf.getPlanMedioId());
									
									ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
									ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
									TipoProveedorIf tipoProveedorIf = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
									tipoMedio = tipoProveedorIf.getNombre();
									medio = proveedorOficinaIf.getDescripcion();
																						
									Map aMap = new HashMap();
									aMap.put("tipoOrden", "OM");
							    	aMap.put("ordenId", ordenMedioIf.getId());
									Collection compras = SessionServiceLocator.getCompraSessionService().findCompraByOrdenAsociadaQuery(aMap);
									
									//si la orden no tiene compras asociadas, puede existir el caso que la factura viene de un plan que luego se actualizo
									//entonces la factura esta amarrada a un id de plan y el mismo codigo de orden esta amarrado a otro id de plan mas actualizado
									//entonces debo verificar esto y lo hago solo si hay una orden con el mismo codigo pero con estado INGRESADO
									if(compras.size() == 0){
										Map ordenIngresadaMap = new HashMap();
										ordenIngresadaMap.put("codigo", ordenMedioIf.getCodigo());
										ordenIngresadaMap.put("estado", "I"); //estado INGRESADO
										ordenIngresadaMap.put("proveedorId", ordenMedioIf.getProveedorId());
										Collection ordenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQuery(ordenIngresadaMap);
										if(ordenesMedio.size() > 0){
											ordenMedioIf = (OrdenMedioIf)ordenesMedio.iterator().next();
										}
									}
									aMap.put("ordenId", ordenMedioIf.getId());
									compras = SessionServiceLocator.getCompraSessionService().findCompraByOrdenAsociadaQuery(aMap);
											
									//si tiene compra asociada aparece aun si la orden tiene estado anulada
									if(compras.size() > 0){
										
										//creo un mapa con todos los codigo de ordenes de medios (no anuladas)
										//que me servira para ver que ordenes me faltan en los casos cuando se
										//actualizo un plan que ya fue facturado
										mapaOrdenMedioCodigo.put(ordenMedioIf.getCodigo(), ordenMedioIf.getCodigo());
										
										Iterator comprasIt = compras.iterator();
										while(comprasIt.hasNext()){
											CompraIf compraIf = (CompraIf)comprasIt.next();
											
											//reviso si la compra esta referenciada en una nota de credito
											NotaCreditoIf notaCreditoReferencia = null;
											boolean notaCreditoRelacionadaAfecta = false;
											
											Map compraReferenciadaMap = new HashMap();
											compraReferenciadaMap.put("tipoCartera", "P");
											compraReferenciadaMap.put("referenciaId", compraIf.getId());
											Collection comprasReferenciadas = SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByQuery(compraReferenciadaMap);
											Iterator comprasReferenciadasIt = comprasReferenciadas.iterator();
											while(comprasReferenciadasIt.hasNext()){
												notaCreditoReferencia = (NotaCreditoIf)comprasReferenciadasIt.next();
											}
											
											//egresos de la compra
											Collection<Object[]> egresos = SessionServiceLocator.getCarteraSessionService().findCarterasCarterasAfectaQueCruzanReferenciaId(compraIf.getId(), compraIf.getTipodocumentoId());
											Iterator<Object[]> egresosIt = egresos.iterator();	
											
											//PRIMERO CREO UN MAPA CON EGRESOS Y SUS AFECTA, SIN TOMAR EN CUENTA LAS RETENCIONES
											//LUEGO RECORRO EL MAPA FORMADO PARA SETEAR INFORMACION EN EL REPORTE
											Map<Long, CarteraAfectaIf> carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
											
											//mapa del egreso que cruza con vector de sus carteras afecta
											Map<Long, Map<Long, CarteraAfectaIf>> carteraCruceCarteraAfectaMap = new HashMap<Long, Map<Long, CarteraAfectaIf>>();
																
											//mapa egresoId egresoIf
											Map<Long, CarteraIf> carteraEgresoMap = new HashMap<Long, CarteraIf>();
											
											//mapa carteraafecta con su forma de pago y cheque
											Map<Long, String[]> afectaFormaPagoChequeMap = new HashMap<Long, String[]>();
											
											while(egresosIt.hasNext()){
												Object[] carterasCruce = egresosIt.next();
												CarteraIf carteraEgreso = (CarteraIf)carterasCruce[0];
												CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasCruce[1];
												CarteraDetalleIf carteraEgresoDetalle = (CarteraDetalleIf)carterasCruce[2];
												
												//si la cartera no es una retencion, porque retenciones se pone directo valores de renta e iva.
												if(carteraEgreso.getTipodocumentoId().compareTo(tipoDocumentoCRE.getId()) != 0){
													//egresos carteras afecta
													if(carteraCruceCarteraAfectaMap.get(carteraEgreso.getId()) == null){
														carterasAfecta = new HashMap<Long, CarteraAfectaIf>();
													}else{
														carterasAfecta = carteraCruceCarteraAfectaMap.get(carteraEgreso.getId());
													}
													carterasAfecta.put(carteraAfecta.getId(), carteraAfecta);
													carteraCruceCarteraAfectaMap.put(carteraEgreso.getId(), carterasAfecta);
													
													//egresos
													carteraEgresoMap.put(carteraEgreso.getId(), carteraEgreso);	
													
													//afecta con forma de pago y cheque
													DocumentoIf documento = mapaDocumento.get(carteraEgresoDetalle.getDocumentoId());
													TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(documento.getTipoDocumentoId());
													
													String[] formaPagoCheque = new String[2];
													if(tipoDocumento.getCodigo().equals("NCP") || tipoDocumento.getCodigo().equals("ICP")){
														formaPagoCheque[0] = "NOTA DE CREDITO";
														formaPagoCheque[1] = "";
													}else if(carteraEgresoDetalle.getChequeBancoId() != null){
														formaPagoCheque[0] = "CHEQUE";
														formaPagoCheque[1] = carteraEgresoDetalle.getChequeNumero();			
													}else if(carteraEgresoDetalle.getDebitoBancoId() != null){
														formaPagoCheque[0] = "DEBITO";
														formaPagoCheque[1] = "";											
													}else{
														formaPagoCheque[0] = "EFECTIVO";
														formaPagoCheque[1] = "";											
													}
													afectaFormaPagoChequeMap.put(carteraAfecta.getId(), formaPagoCheque);
												}										
											}
											
											if(carteraCruceCarteraAfectaMap.size() > 0){
												
												contadorAfectas = 0;
																				
												Iterator carteraCruceCarteraAfectaMapIt = carteraCruceCarteraAfectaMap.keySet().iterator();
												while(carteraCruceCarteraAfectaMapIt.hasNext()){
													Long carteraEgresoId = (Long)carteraCruceCarteraAfectaMapIt.next();
													CarteraIf carteraEgreso = carteraEgresoMap.get(carteraEgresoId);
													
													Map<Long, CarteraAfectaIf> carterasAfectaCompra = carteraCruceCarteraAfectaMap.get(carteraEgresoId);
													Iterator carterasAfectaCompraIt = carterasAfectaCompra.keySet().iterator();
													while(carterasAfectaCompraIt.hasNext()){
														Long carteraAfectaId = (Long)carterasAfectaCompraIt.next();
														CarteraAfectaIf carteraAfecta = carterasAfectaCompra.get(carteraAfectaId);
														
														String[] formaPagoCheque = afectaFormaPagoChequeMap.get(carteraAfectaId);
														
														tableModel = (DefaultTableModel) getTblFacturacion().getModel();
														Vector<String> fila = new Vector<String>();			
														
														//cuento las veces que he ingresado a llenar datos de este factura
														contador++;
														contadorOrdenes++;
														contadorAfectas++;
														if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
															System.out.println("a");
														}
														agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
																productoCliente, null, ordenMedioIf, compraIf, tipoMedio, medio, inversionPlanMedio, 
																carteraEgreso, carteraAfecta, formaPagoCheque[0], formaPagoCheque[1], contadorAfectas, 
																contadorOrdenes, notaCreditoReferencia, notaCreditoRelacionadaAfecta, false);
														
														tableModel.addRow(fila);
														
														agregarFila = true;
													}
												}								
												
											}else{
												tableModel = (DefaultTableModel) getTblFacturacion().getModel();
												Vector<String> fila = new Vector<String>();			
												
												//cuento las veces que he ingresado a llenar datos de este factura
												contador++;
												contadorOrdenes++;
												contadorAfectas = 0;
												if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
													System.out.println("a");
												}
												agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
														productoCliente, null, ordenMedioIf, compraIf, tipoMedio, medio, inversionPlanMedio, 
														null, null, "", "", contadorAfectas, contadorOrdenes, notaCreditoReferencia, notaCreditoRelacionadaAfecta, false);
												
												tableModel.addRow(fila);
												
												agregarFila = true;
											}								
										}
									}
									//si la orden no esta anulada
									else if(!ordenMedioIf.getEstado().equals("A")){
										
										//creo un mapa con todos los codigo de ordenes de medios (no anuladas)
										//que me servira para ver que ordenes me faltan en los casos cuando se
										//actualizo un plan que ya fue facturado
										mapaOrdenMedioCodigo.put(ordenMedioIf.getCodigo(), ordenMedioIf.getCodigo());
										
										tableModel = (DefaultTableModel) getTblFacturacion().getModel();
										Vector<String> fila = new Vector<String>();			
										
										//cuento las veces que he ingresado a llenar datos de este factura
										contador++;
										contadorOrdenes++;
										contadorAfectas = 0;
										if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
											System.out.println("a");
										}
										agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
												productoCliente, null, ordenMedioIf, null, tipoMedio, medio, inversionPlanMedio, null, null, 
												"", "", contadorAfectas, contadorOrdenes, null, false, false);
										
										tableModel.addRow(fila);
										
										agregarFila = true;
									}
								}				
							}
						}
						
						if(!agregarFila){
							
							//facturas detalle para saber tipo de medio y medio en los casos donde no hay ordenes
							Object[] tipoMedioObject = buscarTipoMedio(facturaFacturasDetalleMap, facturaId);
							tipoMedio = (String)tipoMedioObject[0];
							medio = (String)tipoMedioObject[1];
							
							tableModel = (DefaultTableModel) getTblFacturacion().getModel();
							Vector<String> fila = new Vector<String>();
							
							contador = 1;
							contadorOrdenes = 0;
							contadorAfectas = 0;
							if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
								System.out.println("a");
							}
							agregarColumnasTabla(facturaIf, fila, null, null, contador, pedidoIf, presupuestoIf, planMedioIf, 
									productoCliente, null, null, null, tipoMedio, medio, inversionPlanMedio, null, null,
									"", "", contadorAfectas, contadorOrdenes, null, false, false);
							
							tableModel.addRow(fila);
						}
					}
				}						
			}
			
			//puede pasar que la factura este asociada a un plan que luego de ser facturado fue reemplazado
			//entonces ese plan queda como historico y hay que buscar el plan actual para ver si no hay
			//ordenes nuevas que no han sido consideradas
			Iterator facturaPlanMediosMapIt = null;// facturaPlanMediosMap.keySet().iterator();
			if(facturaPlanMediosMapIt !=null)
			while(facturaPlanMediosMapIt.hasNext()){
				Long facturaId = (Long)facturaPlanMediosMapIt.next();
				PlanMedioIf planMedioIf = null;// facturaPlanMediosMap.get(facturaId);
				if(planMedioIf != null && planMedioIf.getEstado().equals("H")){
					Map<Long, OrdenMedioIf> ordenesCompraMap = planMedioOrdenesMedioMap.get(planMedioIf.getId());
					Collection planNuevoColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(planMedioIf.getCodigo(), "F","D","A","P","N");
					if(planNuevoColeccion.size() > 0){
						PlanMedioIf planNuevo = (PlanMedioIf)planNuevoColeccion.iterator().next();
						Collection ordenesNuevasColeccion = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planNuevo.getId());
						Iterator ordenesNuevasColeccionIt = ordenesNuevasColeccion.iterator();
						while(ordenesNuevasColeccionIt.hasNext()){
							OrdenMedioIf ordenNueva = (OrdenMedioIf)ordenesNuevasColeccionIt.next();
							
							if(ordenNueva.getCodigo().equals("2013-03937")){
								System.out.println("prueba");
							}
							
							if(ordenesCompraMap.get(ordenNueva.getId()) == null && mapaOrdenMedioCodigo.get(ordenNueva.getCodigo()) == null){
								//si la orden no esta anulada
								if(!ordenNueva.getEstado().equals("A")){
									tableModel = (DefaultTableModel) getTblFacturacion().getModel();
									Vector<String> fila = new Vector<String>();			
									
									//cuento las veces que he ingresado a llenar datos de este factura
									int contador = 1;
									int contadorOrdenes = 1;
									int contadorAfectas = 0;
																		
									//PRODUCTO CLIENTE
									String productoCliente = "";
									Map productoClienteMap = facturaProductosClienteMap.get(facturaId);
									if(productoClienteMap != null){
										Iterator productoClienteMapIt = productoClienteMap.keySet().iterator();
										while(productoClienteMapIt.hasNext()){
											Long productoClienteId = (Long)productoClienteMapIt.next();
											ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteMap.get(productoClienteId);
											if(productoCliente.equals("")){
												productoCliente = productoClienteIf.getNombre();
											}else{
												productoCliente = productoCliente + ", " + productoClienteIf.getNombre();
											}
										}
									}	
									
									ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(ordenNueva.getProveedorId());
									ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
									TipoProveedorIf tipoProveedorIf = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
									String tipoMedio = tipoProveedorIf.getNombre();
									String medio = proveedorOficinaIf.getDescripcion();
									if(planMedioIf != null && planMedioIf.getCodigo().equals("2014-01783")){
										System.out.println("a");
									}
									agregarColumnasTabla(null, fila, null, null, contador, null, null, planMedioIf, 
											productoCliente, null, ordenNueva, null, tipoMedio, medio, new BigDecimal(0), null, null, 
											"", "", contadorAfectas, contadorOrdenes, null, false, false);
									
									tableModel.addRow(fila);
								}
							}
						}
					}					
				}
			}
			
			//si existen datos se genera el archivo excel
			if(libroVentasNotasCreditoColeccion.size() > 0){
				generarArchivoExcel();
			}else{
				SpiritAlert.createAlert("No existen datos para esta consulta.", SpiritAlert.INFORMATION);
			}
			
		}catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	private Object[] buscarTipoMedio(Map<Long, Vector<FacturaDetalleIf>> facturaFacturasDetalleMap,
			Long facturaId) throws GenericBusinessException {
		
		Object[] tipoMedioObject = new Object[2];
		
		String tipoMedio = "";
		String medio = "";
		
		Map<String, String> tipoMedioMap = new HashMap<String, String>();
		Map<String, String> medioMap = new HashMap<String, String>();
		
		Vector<FacturaDetalleIf> facturasDetalle = facturaFacturasDetalleMap.get(facturaId);					
		for(FacturaDetalleIf facturaDetalleIf : facturasDetalle){
			ProductoIf productoIf = SessionServiceLocator.getProductoSessionService().getProducto(facturaDetalleIf.getProductoId());
			ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(productoIf.getProveedorId());
			ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
			TipoProveedorIf tipoProveedorIf = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
			tipoMedioMap.put(tipoProveedorIf.getNombre(), tipoProveedorIf.getNombre());	
			medioMap.put(proveedorOficinaIf.getDescripcion(), proveedorOficinaIf.getDescripcion());
		}
		Iterator tipoMedioMapIt = tipoMedioMap.keySet().iterator();
		while(tipoMedioMapIt.hasNext()){
			String tipoMedioTemp = (String)tipoMedioMapIt.next();
			if(tipoMedio.equals("")){
				tipoMedio = tipoMedioTemp;
			}else{
				tipoMedio = tipoMedio + ", " + tipoMedioTemp;
			}
		}
		Iterator medioMapIt = medioMap.keySet().iterator();
		while(medioMapIt.hasNext()){
			String medioTemp = (String)medioMapIt.next();
			if(medio.equals("")){
				medio = medioTemp;
			}else{
				medio = medio + ", " + medioTemp;
			}
		}
		tipoMedioObject[0] = tipoMedio;
		tipoMedioObject[1] = medio;
		
		return tipoMedioObject;
	}
	
	private void agregarColumnasTabla(FacturaIf facturaIf, Vector<String> fila, 
			CarteraIf carteraIngreso, CarteraAfectaIf carteraAfecta, int contador, PedidoIf pedidoIf, 
			PresupuestoIf presupuestoIf, PlanMedioIf planMedioIf, String productoCliente, 
			OrdenCompraIf ordenCompraIf, OrdenMedioIf ordenMedioIf, CompraIf compraIf, 
			String tipoMedio, String medio, BigDecimal inversionPlanMedio, CarteraIf carteraEgreso, 
			CarteraAfectaIf carteraAfectaCompra, String formaPago, String numeroCheque, int contadorAfectas, 
			int contadorOrdenes, NotaCreditoIf notaCreditoReferencia, boolean notaCreditoRelacionadaAfecta, 
			boolean notaCreditoRelacionadaAfectacion){
		
		LibroVentasNotasCreditoData libroVentasNotasCreditoData = new LibroVentasNotasCreditoData();
		
		ClienteOficinaIf clienteOficinaIf = null;
		ClienteIf cliente = null;
		CorporacionIf corporacion = null;
		if(facturaIf != null){
			clienteOficinaIf = mapaClienteOficina.get(facturaIf.getClienteoficinaId());
			cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
			corporacion = mapaCorporacion.get(cliente.getCorporacionId());
		}else if(ordenMedioIf != null){
			clienteOficinaIf = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
			cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
			corporacion = mapaCorporacion.get(cliente.getCorporacionId());
		}		
		
		TipoDocumentoIf tipoDocumentoCarteraIngreso = null;
		if(carteraIngreso != null){
			tipoDocumentoCarteraIngreso = mapaTipoDocumento.get(carteraIngreso.getTipodocumentoId());
		}
		
		//1 Cliente Oficina
		String clienteOficina = "";
		if(/*contador == 1 &&*/ clienteOficinaIf != null){
			clienteOficina = facturaIf != null? clienteOficinaIf.getDescripcion() : clienteOficinaIf.getDescripcion()+"(*)";
		}		
		fila.add(clienteOficina);
		libroVentasNotasCreditoData.setClienteOficina(clienteOficina);
		
		//2 Presupuesto
		String presupuesto = "";
				
		if(presupuestoIf != null){
			presupuesto = presupuestoIf.getCodigo();
		}else if(planMedioIf != null){
			presupuesto = planMedioIf.getCodigo();
		}
		
		fila.add(presupuesto);
		libroVentasNotasCreditoData.setPresupuesto(presupuesto);
		
		//3 Factura Preimpreso
		String preimpresoFactura = "";
		if(facturaIf != null){
			preimpresoFactura = facturaIf.getPreimpresoNumero();
		}
		fila.add(preimpresoFactura);
		libroVentasNotasCreditoData.setPreimpresoFactura(preimpresoFactura);
		
		//4 Nota de Crédito Preimpreso
		String notaCredito = "";
		if(tipoDocumentoCarteraIngreso != null && tipoDocumentoCarteraIngreso.getCodigo().equals("NCC")){
			notaCredito = carteraIngreso.getPreimpreso();
		}
		fila.add(notaCredito);
		libroVentasNotasCreditoData.setNotaCredito(notaCredito);
		
		//5 Fecha de la Factura
		String fechaFactura = "";
		if(facturaIf != null){
			fechaFactura = Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura());
		}
		fila.add(fechaFactura);
		libroVentasNotasCreditoData.setFechaFactura(fechaFactura);
		
		//6 Producto del Cliente
		fila.add(productoCliente);
		libroVentasNotasCreditoData.setProductoCliente(productoCliente);
		
		//7 Tipo de Medio
		fila.add(tipoMedio);
		libroVentasNotasCreditoData.setTipoMedio(tipoMedio);
		
		//8 Inversion
		String inversion = "";
		if(presupuestoIf != null && contadorPresupuestosMap.get(presupuestoIf.getId()) == null){
			inversion = formatoDecimal.format(presupuestoIf.getValor());
			contadorPresupuestosMap.put(presupuestoIf.getId(), presupuestoIf);
		}else if(planMedioIf != null && contadorPresupuestosMap.get(planMedioIf.getId()) == null){
			if(inversionPlanMedio == null){
				System.out.println("INVERSION PLAN MEDIO: " + inversionPlanMedio);
			}
			inversion = formatoDecimal.format(inversionPlanMedio);
			contadorPresupuestosMap.put(planMedioIf.getId(), planMedioIf);
		}
		fila.add(inversion);
		libroVentasNotasCreditoData.setInversion(inversion);
		
		//9 Subtotal Factura sin IVA
		BigDecimal descuentoTotal = new BigDecimal(0);
		BigDecimal porcentajeComision = new BigDecimal(0);
		BigDecimal comisionAgencia = new BigDecimal(0);
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		
		if(facturaIf != null){
			descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentosVarios()).add(facturaIf.getDescuentoGlobal());
			porcentajeComision = new BigDecimal(0);
			if(facturaIf.getPorcentajeComisionAgencia() != null){
				porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
			}
			comisionAgencia = (facturaIf.getValor().subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
			subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
			total = subTotal.add(facturaIf.getIva());
		}
		
		String subtotalFactura = contador == 1? formatoDecimal.format(subTotal) : "";
		fila.add(subtotalFactura);
		libroVentasNotasCreditoData.setSubtotalFactura(subtotalFactura);
		
		//10 IVA
		String ivaFactura = "";
		if(contador == 1 && facturaIf != null){
			ivaFactura = formatoDecimal.format(facturaIf.getIva());	
		}			
		fila.add(ivaFactura);
		libroVentasNotasCreditoData.setIvaFactura(ivaFactura);
		
		//11 Total Factura
		String totalFactura = contador == 1? formatoDecimal.format(total) : "";
		fila.add(totalFactura);
		libroVentasNotasCreditoData.setTotalFactura(totalFactura);
		
		//12 SAP número
		String sap = "";
		if(facturaIf != null && facturaIf.getAutorizacionSap() != null){
			sap = facturaIf.getAutorizacionSap();
		}		
		fila.add(sap);
		libroVentasNotasCreditoData.setSap(sap);
		
		//13 Fecha de Pago del Cliente
		String fechaPagoCliente = "";
		if(carteraAfecta != null){
			fechaPagoCliente = Utilitarios.getFechaCortaUppercase(carteraAfecta.getFechaAplicacion());		
		}
		fila.add(fechaPagoCliente);
		libroVentasNotasCreditoData.setFechaPagoCliente(fechaPagoCliente);
		
		//14 Número del Ingreso
		String numeroIngreso = "";
		if(carteraIngreso != null){
			numeroIngreso = carteraIngreso.getCodigo();		
		}
		fila.add(numeroIngreso);
		libroVentasNotasCreditoData.setNumeroIngreso(numeroIngreso);
		
		//15 Valor del Ingreso
		String valorIngreso = "";
		if(carteraAfecta != null && contadorCarteraAfectaMap.get(carteraAfecta.getId()) == null){
			valorIngreso = formatoDecimal.format(carteraAfecta.getValor());
			contadorCarteraAfectaMap.put(carteraAfecta.getId(), carteraAfecta);
		}
		fila.add(valorIngreso);
		libroVentasNotasCreditoData.setValorIngreso(valorIngreso);
		
		//16 Medio Oficina
		String medioOficina = medio;		
		fila.add(medioOficina);
		libroVentasNotasCreditoData.setMedioOficina(medioOficina);
		
		//17 Orden de Compra/Medio
		String ordenCompra = "";		
		if(ordenCompraIf != null){
			ordenCompra = ordenCompraIf.getCodigo();
		}else if(ordenMedioIf != null){
			ordenCompra = facturaIf != null? ordenMedioIf.getCodigo() : ordenMedioIf.getCodigo()+"(*)";
		}		
		fila.add(ordenCompra);
		libroVentasNotasCreditoData.setOrdenCompra(ordenCompra);
		
		//18 Factura del Medio Preimpreso
		String preimpresoFacturaMedio = "";		
		if(compraIf != null){
			preimpresoFacturaMedio = compraIf.getPreimpreso();
		}		
		fila.add(preimpresoFacturaMedio);
		libroVentasNotasCreditoData.setPreimpresoFacturaMedio(preimpresoFacturaMedio);
		
		//19 Fecha de la Factura del Medio
		String fechaFacturaMedio = "";		
		if(compraIf != null){
			fechaFacturaMedio = Utilitarios.getFechaCortaUppercase(compraIf.getFecha());
		}		
		fila.add(fechaFacturaMedio);
		libroVentasNotasCreditoData.setFechaFacturaMedio(fechaFacturaMedio);
		
		//20 Subtotal Factura del Medio
		String subtotalFacturaMedio = "";
		double ivaValor = 0D;
		double totalOrdenValor = 0D;
		if(compraIf != null && ordenCompraIf != null && contadorAfectas <= 1){
			double valorBruto = ordenCompraIf.getValor().doubleValue();
			double porcentajeDescuentoEspecial = ordenCompraIf.getPorcentajeDescuentoEspecial().doubleValue() / 100D;
			double descuentoEspecial = valorBruto * porcentajeDescuentoEspecial;
			double descuentoAgencia = ordenCompraIf.getDescuentoAgenciaCompra().doubleValue();
			double porcentajeDescuentosVarios = ordenCompraIf.getPorcentajeDescuentosVariosCompra().doubleValue() / 100D;
			double descuentosVarios = (valorBruto - descuentoEspecial) * porcentajeDescuentosVarios;
			double valorNeto = valorBruto - descuentoEspecial - descuentoAgencia - descuentosVarios;
			ivaValor = ordenCompraIf.getIva().doubleValue();
			totalOrdenValor = valorNeto + ivaValor;
			
			subtotalFacturaMedio = formatoDecimal.format(valorNeto);
			
		}else if(compraIf != null && contadorAfectas <= 1){
			double subtotalMedio = compraIf.getValor().doubleValue() - compraIf.getDescuento().doubleValue();
			ivaValor = compraIf.getIva().doubleValue();
			totalOrdenValor = subtotalMedio + ivaValor;
			
			subtotalFacturaMedio = formatoDecimal.format(subtotalMedio);
		}
		
		fila.add(subtotalFacturaMedio);
		libroVentasNotasCreditoData.setSubtotalFacturaMedio(subtotalFacturaMedio);
		
		//21 IVA Factura del Medio
		String ivaFacturaMedio = "";
		if(compraIf != null && contadorAfectas <= 1){
			ivaFacturaMedio = formatoDecimal.format(ivaValor);
		}
		fila.add(ivaFacturaMedio);
		libroVentasNotasCreditoData.setIvaFacturaMedio(ivaFacturaMedio);
		
		//22 Total Factura del Medio
		String totalFacturaMedio = "";
		if(compraIf != null && contadorAfectas <= 1){
			totalFacturaMedio = formatoDecimal.format(totalOrdenValor);
		}
		fila.add(totalFacturaMedio);
		libroVentasNotasCreditoData.setTotalFacturaMedio(totalFacturaMedio);
		
		//23 Retención Renta del Medio
		String retencionRentaMedio = "";		
		String retencionIvaMedio = "";		
				
		if(compraIf != null && contadorAfectas <= 1){
			try {
				Collection retenciones = SessionServiceLocator.getCompraRetencionSessionService().findCompraRetencionByCompraId(compraIf.getId());
				Iterator retencionesIt = retenciones.iterator();
				while(retencionesIt.hasNext()){
					CompraRetencionIf compraRetencion = (CompraRetencionIf)retencionesIt.next();
					if(compraRetencion.getImpuesto().equals("R")){
						retencionRentaMedio = formatoDecimal.format(compraRetencion.getValorRetenido());
					}else if(compraRetencion.getImpuesto().equals("I")){
						retencionIvaMedio = formatoDecimal.format(compraRetencion.getValorRetenido());
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}		
		}		
		
		fila.add(retencionRentaMedio);
		libroVentasNotasCreditoData.setRetencionRentaMedio(retencionRentaMedio);
		
		//24 Retención IVA del Medio
		fila.add(retencionIvaMedio);
		libroVentasNotasCreditoData.setRetencionIvaMedio(retencionIvaMedio);		
		
		//25 Valor Pagado al Medio
		String valorPagoMedio = "";
		if(carteraAfectaCompra != null){
			valorPagoMedio = formatoDecimal.format(carteraAfectaCompra.getValor());
		}		
		fila.add(valorPagoMedio);
		libroVentasNotasCreditoData.setValorPagoMedio(valorPagoMedio);
		
		//26 Fecha de Pago de la Agencia al Medio
		String fechaPagoMedio = "";	
		if(carteraAfectaCompra != null){
			fechaPagoMedio = Utilitarios.getFechaCortaUppercase(carteraAfectaCompra.getFechaAplicacion());
		}	
		fila.add(fechaPagoMedio);
		libroVentasNotasCreditoData.setFechaPagoMedio(fechaPagoMedio);
		
		//27 Forma de Pago
		//String formaPago = "";		
		fila.add(formaPago);
		libroVentasNotasCreditoData.setFormaPago(formaPago);
		
		//28 Número del Egreso
		String numeroEgreso = "";
		if(carteraEgreso != null){
			numeroEgreso = carteraEgreso.getCodigo();
		}
		fila.add(numeroEgreso);
		libroVentasNotasCreditoData.setNumeroEgreso(numeroEgreso);
		
		//29 Número del Cheque
		//String numeroCheque = "";		
		fila.add(numeroCheque);
		libroVentasNotasCreditoData.setNumeroCheque(numeroCheque);
		
		//30 Nota de Crédito al Proveedor
		String notaCreditoProveedor = "";
		if(!notaCreditoRelacionadaAfecta && notaCreditoReferencia != null && contadorAfectas <= 1){
			notaCreditoProveedor = notaCreditoReferencia.getPreimpreso();
		}else if(notaCreditoRelacionadaAfecta && notaCreditoRelacionadaAfectacion){
			notaCreditoProveedor = notaCreditoReferencia.getPreimpreso();
		}
		fila.add(notaCreditoProveedor);
		libroVentasNotasCreditoData.setNotaCreditoProveedor(notaCreditoProveedor);
		
		//31 Valor Nota de Crédito al Proveedor
		String valorNotaCreditoProveedor = "";
		BigDecimal totalNotaCredito = new BigDecimal(0);
		if(!notaCreditoRelacionadaAfecta && notaCreditoReferencia != null && contadorAfectas <= 1){
			totalNotaCredito = notaCreditoReferencia.getValor().add(notaCreditoReferencia.getIva());
			valorNotaCreditoProveedor = formatoDecimal.format(totalNotaCredito);
		}else if(notaCreditoRelacionadaAfecta && notaCreditoRelacionadaAfectacion){
			totalNotaCredito = notaCreditoReferencia.getValor().add(notaCreditoReferencia.getIva());
			valorNotaCreditoProveedor = formatoDecimal.format(totalNotaCredito);
		}
		fila.add(valorNotaCreditoProveedor);
		libroVentasNotasCreditoData.setValorNotaCreditoProveedor(valorNotaCreditoProveedor);
		
		//32 Valor aplicado a la factura del Medio
		String valorAplicadoFacturaMedio = "";
		if(!notaCreditoRelacionadaAfecta && notaCreditoReferencia != null && contadorAfectas <= 1){
			valorAplicadoFacturaMedio = formatoDecimal.format(0D);
		}else if(notaCreditoRelacionadaAfecta && notaCreditoRelacionadaAfectacion){
			valorAplicadoFacturaMedio = formatoDecimal.format(carteraAfectaCompra.getValor());
		}
		fila.add(valorAplicadoFacturaMedio);
		libroVentasNotasCreditoData.setValorAplicadoFacturaMedio(valorAplicadoFacturaMedio);
			
		libroVentasNotasCreditoColeccion.add(libroVentasNotasCreditoData);
	}
	
	public void generarArchivoExcel(){
		try {
			//PARA QUE EL NOMBRE DEL ARCHIVO PONER LA FECHA INICIO Y FIN DE LA BUSQUEDA
			Calendar fechaInicio = new GregorianCalendar();
			fechaInicio.setTimeInMillis(getCmbFechaInicio().getDate().getTime());
			String anioInicio = String.valueOf(fechaInicio.getTime().getYear()+1900);
			String mesInicio = String.valueOf(fechaInicio.getTime().getMonth()+1);
			if((fechaInicio.getTime().getMonth()+1) < 10){
				mesInicio = "0"+mesInicio;
			}			
			String diaInicio = String.valueOf(fechaInicio.getTime().getDate());
			if(fechaInicio.getTime().getDate() < 10){
				diaInicio = "0"+diaInicio;
			}
			String anioMesDiaInicio = anioInicio+mesInicio+diaInicio;
			
			Calendar fechaFin = new GregorianCalendar();
			fechaFin.setTimeInMillis(getCmbFechaFin().getDate().getTime());
			String anioFin = String.valueOf(fechaFin.getTime().getYear()+1900);
			String mesFin = String.valueOf(fechaFin.getTime().getMonth()+1);
			if((fechaFin.getTime().getMonth()+1) < 10){
				mesFin = "0"+mesFin;
			}			
			String diaFin = String.valueOf(fechaFin.getTime().getDate());
			if(fechaFin.getTime().getDate() < 10){
				diaFin = "0"+diaFin;
			}
			String anioMesDiaFin = anioFin+mesFin+diaFin;
			
			//PARA QUE EN EL NOMBRE DEL ARCHIVO PONER LA FECHA Y HORA DE CREACION DEL ARCHIVO
			Calendar fechaHoy = new GregorianCalendar();
			String anio = String.valueOf(fechaHoy.getTime().getYear()+1900);
			String mes = String.valueOf(fechaHoy.getTime().getMonth()+1);
			if((fechaHoy.getTime().getMonth()+1) < 10){
				mes = "0"+mes;
			}			
			String dia = String.valueOf(fechaHoy.getTime().getDate());
			if(fechaHoy.getTime().getDate() < 10){
				dia = "0"+dia;
			}
			String hora = String.valueOf(fechaHoy.getTime().getHours());
			if(fechaHoy.getTime().getHours() < 10){
				hora = "0"+hora;
			}
			String minutos = String.valueOf(fechaHoy.getTime().getMinutes());
			if(fechaHoy.getTime().getMinutes() < 10){
				minutos = "0"+minutos;
			}
			String anioMesDiaHoraMinutos = anio+"-"+mes+"-"+dia+"_"+hora+"h"+minutos;
			
			//COMIENZA LA CREACION DEL ARCHIVO
			//C:\\directorio\\
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("LibroVentasNotasCredito");
			
			//creo un objecto de estito de celda, y seteo font con negrita
			HSSFCellStyle cellStyle = libro.createCellStyle();
			HSSFFont font = libro.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
						
			//seteo el borde
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			//primera fila, Nombres de las 32 columnas
			HSSFRow filaNumeros = hoja.createRow(0);
			for(int i=0; i<32; i++){
				HSSFCell celda = filaNumeros.createCell(i);
				HSSFRichTextString texto = new HSSFRichTextString(libroVentasNotasCreditoColeccion.get(0).getNombreAtributo(i+1));
				celda.setCellValue(texto);
				
				celda.setCellStyle(cellStyle);
			}
			
			HSSFCellStyle cellStyleFacturaNueva = libro.createCellStyle();
			cellStyleFacturaNueva.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyleFacturaNueva.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyleFacturaNueva.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyleFacturaNueva.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			//relleno gris para indentificar cada nueva factura
			HSSFColor lightGray =  setColor(libro, (byte)210, (byte)210, (byte)210);
			cellStyleFacturaNueva.setFillForegroundColor(lightGray.getIndex());
			cellStyleFacturaNueva.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			HSSFCellStyle cellStyleMismaFactura = libro.createCellStyle();
			cellStyleMismaFactura.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyleMismaFactura.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyleMismaFactura.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyleMismaFactura.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						
			//lleno desde la segunda fila la información de los 32 campos			
			for(int j = 0; j < libroVentasNotasCreditoColeccion.size(); j++){
				LibroVentasNotasCreditoData libroVentasNotasCreditoData = libroVentasNotasCreditoColeccion.get(j);
				HSSFRow filaCampos = hoja.createRow(j+1);
				
				for(int i=0; i<32; i++){
					HSSFCell celda = filaCampos.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(libroVentasNotasCreditoData.getCampo(i+1));
					celda.setCellValue(texto);				

					//relleno y cuadricula cada vez que hay una factura nueva
					//siempre y cuando ver egresos este seleccionado sino no tendría uso
					if(!libroVentasNotasCreditoData.getSubtotalFactura().equals("") && getCbVerEgresos().isSelected()){
						celda.setCellStyle(cellStyleFacturaNueva);
					}
					//solo cuadricula cuando es una fila de la misma factura
					else{
						celda.setCellStyle(cellStyleMismaFactura);
					}
				}				
			}
			
			//indico que las columnas tengan el ancho de su texto
			for(int columnIndex = 0; columnIndex < 32; columnIndex++) {
				hoja.autoSizeColumn(columnIndex);
			}
			
			//escribo el archivo
			//FileOutputStream archivo = new FileOutputStream("C:\\Temp\\LIBRO_VENTAS_NOTASCREDITO_"+anioMesDiaHoraMinutos+".xls");
			FileOutputStream archivo = new FileOutputStream("C:\\Temp\\LIBRO_VENTAS_NOTASCREDITO_"+anioMesDiaInicio+"_"+anioMesDiaFin+".xls");
			libro.write(archivo);
			archivo.close();
			System.out.println("Archivo creado con éxito!");
			
			long fin=System.currentTimeMillis();
			System.out.println("tiempo: "+(fin-start)/1000+" seg");
			
			SpiritAlert.createAlert("Archivo creado con éxito.", SpiritAlert.INFORMATION);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: File");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: IO");
		}
	}
	
	public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b){
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor= palette.findColor(r, g, b); 
			if (hssfColor == null ){
				//palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
				
				palette.setColorAtIndex(HSSFColor.LAVENDER.index,
				        (byte) r,  	//RGB red (0-255)
				        (byte) g,	//RGB green
				        (byte) b	//RGB blue
				);
				
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hssfColor;
	}
	
	public Collection<Object[]> generarColeccionFacturasPedidos(){
		Collection<Object[]> facturasPedidos = null;		
		try {
			Map facturasMap = new HashMap();
			
			//solo las facturas estado C "COMPLETAS", el unico otro estado es A "ANULADAS"
			facturasMap.put("estado", "C");
			
			if(clienteOficinaIf != null){
				facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}else if(clienteIf != null){
				facturasMap.put("clienteId", clienteIf.getId());
			}
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			Long medioOficinaId = 0L;
			Long medioId = 0L;
			if(medioOficinaIf != null){
				medioOficinaId = medioOficinaIf.getId();
			}else if(medioIf != null){
				medioId = medioIf.getId();
			}
			
			start=System.currentTimeMillis();
			
			facturasPedidos = SessionServiceLocator.getFacturaSessionService().findFacturasPedidosByQueryByFechaInicioAndByFechaFinByProveedorIdByProveedorOficinaId(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()), medioId, medioOficinaId);				
			//facturasPedidos = SessionServiceLocator.getFacturaSessionService().findFacturasPedidosByQueryByFechaInicioAndByFechaFin(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
					
			//System.out.println("prueba");
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return facturasPedidos;
	}
	
	public Object[] buscarPresupuestoDetalleProducto(Long idFactura, Long idPedido){		
		
		Object[] presupuestoDetalleProducto = new Object[4];
		
		//mapa idFactura, ProductoClienteIf mapa
		Map<Long, ProductoClienteIf> productosClienteMap = new HashMap<Long, ProductoClienteIf>();
		Map<Long, Map<Long, ProductoClienteIf>> presupuestoProductosClienteMap = new HashMap<Long, Map<Long, ProductoClienteIf>>();
		//mapa idFactura, OrdenCompraIf mapa
		Map<Long, OrdenCompraIf> ordenesCompraMap = new HashMap<Long, OrdenCompraIf>();
		Map<Long, Map<Long, OrdenCompraIf>> presupuestoOrdenesCompraMap = new HashMap<Long, Map<Long, OrdenCompraIf>>();
		//mapa idPresupuesto, PresupuestoIf mapa
		Map<Long, Object> presupuestosMap = new HashMap<Long, Object>();
		//mapa idFactura, presupuestosMap
		Map<Long, Map<Long, Object>> facturaPresupuestosMap = new HashMap<Long, Map<Long, Object>>();
		//mapa idOrdenCompra, presupuestoId
		Map<Long,Long> ordenIdPresupuestoIdMap = new HashMap<Long,Long>();
		
		Long medioOficinaId = 0L;
		Long medioId = 0L;
		if(medioOficinaIf != null){
			medioOficinaId = medioOficinaIf.getId();
		}else if(medioIf != null){
			medioId = medioIf.getId();
		}
		
		try {
			Collection presupuestosDetalle = null;
			presupuestosDetalle = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoDetalleByFacturaIdByProveedorIdByProveedorOficinaId(idFactura, medioId, medioOficinaId);
			if(presupuestosDetalle.size() == 0){
				presupuestosDetalle = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoDetalleByPedidoIdByProveedorIdByProveedorOficinaId(idPedido, medioId, medioOficinaId);
			}
			
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			while(presupuestosDetalleIt.hasNext()){
				PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
				
				Collection presupuestoProductos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoProductoClienteByPresupuestoId(presupuestoDetalle.getPresupuestoId());
				Iterator presupuestoProductosIt = presupuestoProductos.iterator();
				while(presupuestoProductosIt.hasNext()){
					Object[] presupuestoDetalleProductoTemp = (Object[])presupuestoProductosIt.next();
					
					// Presupuesto
					PresupuestoIf presupuesto = (PresupuestoIf)presupuestoDetalleProductoTemp[0];
					presupuestosMap.put(presupuesto.getId(), presupuesto);
					facturaPresupuestosMap.put(idFactura, presupuestosMap);
					presupuestoDetalleProducto[0] = facturaPresupuestosMap;
					// Producto Cliente Vector
					ProductoClienteIf productoCliente = (ProductoClienteIf)presupuestoDetalleProductoTemp[1];
					productosClienteMap.put(productoCliente.getId(), productoCliente);
					presupuestoProductosClienteMap.put(idFactura, productosClienteMap);
					presupuestoDetalleProducto[1] = presupuestoProductosClienteMap;
					// busco si tiene asociada orden de compra
					if(presupuestoDetalle.getOrdenCompraId() != null){
						OrdenCompraIf ordenCompraIf = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(presupuestoDetalle.getOrdenCompraId());
						ordenesCompraMap.put(ordenCompraIf.getId(), ordenCompraIf);
						presupuestoOrdenesCompraMap.put(idFactura, ordenesCompraMap);
						presupuestoDetalleProducto[2] = presupuestoOrdenesCompraMap;
						
						//me sirve al momento de agregar las filas del reporte para no saber con que presupuesto es cada orden
						ordenIdPresupuestoIdMap.put(ordenCompraIf.getId(), presupuesto.getId());
						presupuestoDetalleProducto[3] = ordenIdPresupuestoIdMap;
					}
				}										
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return presupuestoDetalleProducto;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
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

	@Override
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	public void clean() {
		libroVentasNotasCreditoColeccion.clear();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		cleanTable();		
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblFacturacion().getModel();
		for(int i= this.getTblFacturacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	@Override
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

}