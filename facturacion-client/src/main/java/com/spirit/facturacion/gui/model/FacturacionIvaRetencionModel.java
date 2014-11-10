package com.spirit.facturacion.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JPFacturacionIvaRetencion;
import com.spirit.facturacion.gui.reporteData.FacturacionIvaRetencionData;
import com.spirit.facturacion.gui.reporteData.FacturacionReporteRetencionesData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.Utilitarios;

public class FacturacionIvaRetencionModel extends JPFacturacionIvaRetencion {
	
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	
	private Vector<FacturacionIvaRetencionData> facturacionColeccion = new Vector<FacturacionIvaRetencionData>();
	private Vector<FacturacionReporteRetencionesData> facturacionConsolidadoColeccion = new Vector<FacturacionReporteRetencionesData>();
	private Vector<FacturacionReporteRetencionesData> facturacionConsolidadoResumenColeccion = new Vector<FacturacionReporteRetencionesData>();
	private Vector<FacturacionIvaRetencionData> facturacionColeccionNC = new Vector<FacturacionIvaRetencionData>();
	private Map<Long, TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long, TipoDocumentoIf>();
	private Map<Long, DocumentoIf> mapaDocumento = new HashMap<Long, DocumentoIf>();
	private Map<String,Long> mapaTipoDocumentoCodigo = new HashMap<String,Long>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, CarteraIf> mapaCartera = new HashMap<Long, CarteraIf>();
	private LinkedMap mapaFactura = new LinkedMap();
	private Vector<CarteraDetalleIf> carteraDetalleVector = new Vector<CarteraDetalleIf>();
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private static final String ESTADO_ANULADO = "A";
	private static final String CODIGO_FACTURA_REEMBOLSO = "FAR";
	private static final String CODIGO_FACTURA_EXTERIOR = "FAE";
	private static final String CODIGO_NC_FACTURA_REEMBOLSO = "NCFR";
	private static final String CODIGO_NC_FACTURA_EXTERIOR = "NCFE";
	private static final String TIPO_RETENCION_RENTA = "R";
	private static final String TIPO_RETENCION_IVA = "I";
	private static final String RETENCION_RENTA = "RETENCION_RENTA";
	private static final String RETENCION_IVA = "RETENCION_PREIMPRESO";
	private static final String RETENCION_RENTA_1 = "RETENCION_RENTA_1";
	private static final String RETENCION_RENTA_2 = "RETENCION_RENTA_2";
	private static final String FECHA_RETENCION = "FECHA_RETENCION";
	private static final String FECHA_EMISION = "FECHA_EMISION";
	private static final String CLIENTE_ID = "CLIENTE_ID";
	private static final String RETENCION_IVA_30 = "RETENCION_IVA_30";
	private static final String RETENCION_IVA_70 = "RETENCION_IVA_70";
	private static final String RETENCION_IVA_100 = "RETENCION_IVA_100";
	private static final String RETENCION_RENTAPORCENTAJE_1 = "RETENCION_RENTAPORCENTAJE_1";
	private static final String RETENCION_RENTAPORCENTAJE_2 = "RETENCION_RENTAPORCENTAJE_2";
	private static final String RETENCION_IVAPORCENTAJE_30 = "RETENCION_IVAPORCENTAJE_30";
	private static final String RETENCION_IVAPORCENTAJE_70 = "RETENCION_IVAPORCENTAJE_70";
	private static final String RETENCION_IVAPORCENTAJE_100 = "RETENCION_IVAPORCENTAJE_100";
	private static String NOMBRE_MENU_FACTURACION = "FACTURACION";
	private BigDecimal descuentoTotal = new BigDecimal(0);
	private BigDecimal porcentajeComision = new BigDecimal(0);
	private BigDecimal comisionAgencia = new BigDecimal(0);
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal total = new BigDecimal(0);
	
	private LinkedMap mapaFacturaRetenciones = new LinkedMap();
	private LinkedMap mapaFacturaRetencionesPorcentaje = new LinkedMap();
	private LinkedMap mapaFacturaRetencionesFecha = new LinkedMap();	
	private LinkedMap mapaCarteraRetenciones = new LinkedMap();
	private LinkedMap mapaCarteraRetencionesPorcentaje = new LinkedMap();
	private LinkedMap mapaCarteraRetencionesFecha = new LinkedMap();	
	private Map<Long, CarteraIf> mapaPorcentajeRet = new HashMap<Long, CarteraIf>();	
	private Map<String,BigDecimal> mapaCamposValoresMeses = new LinkedMap();	
	private Map facturasMap = new HashMap();	
	private BigDecimal totalTotal = new BigDecimal(0);
	
	public double exterior=0D;
	public double reembolso=0D;
	public double normal=0D;
	public double iva=0D;
	public double totalResumen=0D;
	public BigDecimal exteriorNC = new BigDecimal(0);
	public BigDecimal reembolsoNC = new BigDecimal(0);
	public BigDecimal normalNC = new BigDecimal(0);
	
	public String tipo="C";
	

	public FacturacionIvaRetencionModel() {
		cargarMapasTipoDocumentoCliente();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	
	private void initListeners() {
		getCbPreimpreso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbPreimpreso().isSelected()) {
					getCbCliente().setSelected(false);
				} else {
					getCbCliente().setSelected(true);
				}
			}
		});
		
		getCbCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbCliente().isSelected()) {
					getCbPreimpreso().setSelected(false);
				} else {
					getCbPreimpreso().setSelected(true);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipo="C";
				cleanTable();
				cargarTabla();
				report();
			}
		});
		
		getBtnConsultarConsolidado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipo="CC";//ConsultarConsolidado
				cleanTable();
				cargarTabla();
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbTodos().isSelected()) {
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(
						tituloVentanaBusqueda, idCorporacion, idCliente,
						CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(
						Parametros.getMainFrame(), clienteOficinaCriteria,
						JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,
						anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder
					.getElementoSeleccionado();
					clienteIf = mapaCliente
					.get(clienteOficinaIf.getClienteId());
					getTxtCliente().setText(clienteIf.getNombreLegal());
					getCbTodos().setSelected(false);
				}
			}
		});
	}
	
	public void cargarMapasTipoDocumentoCliente() {
		try {
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while (tiposDocumentoIt.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documento = SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
			Iterator documentoIt = documento.iterator();
			while (documentoIt.hasNext()) {
				DocumentoIf documentos = (DocumentoIf) documentoIt.next();
				mapaDocumento.put(documentos.getId(), documentos);
			}
			
			mapaTipoDocumentoCodigo.clear();
			Collection tiposDocumentoCodigo = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoCodigoIt = tiposDocumentoCodigo.iterator();
			while(tiposDocumentoCodigoIt.hasNext()){
				TipoDocumentoIf tipoDocumentoCodigo = (TipoDocumentoIf)tiposDocumentoCodigoIt.next();				
				mapaTipoDocumentoCodigo.put(tipoDocumentoCodigo.getCodigo(), tipoDocumentoCodigo.getId());
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService()
			.getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
				.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().getClienteList();
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	
	public void initKeyListeners() {
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultarConsolidado().setToolTipText("Consultar Consolidado");
		getBtnConsultarConsolidado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblFacturacion().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
	}
	
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblFacturacion());
		getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		// getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(115);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(25);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(80);
		
		
		String headers[] = { "Upper"};
		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
		
		JLabel headerLabel1 = new JLabel("#Factura", JLabel.CENTER);		
		headerLabel1.setBorder(headerBorder);
		JLabel headerLabel2 = new JLabel("FechaEmisión", JLabel.CENTER);		
		headerLabel2.setBorder(headerBorder);
		JLabel headerLabel3 = new JLabel("Cliente", JLabel.CENTER);		
		headerLabel3.setBorder(headerBorder);
		
		TableCellRenderer renderer = new JComponentTableCellRenderer();
		
		getTblFacturacion().getColumnModel().getColumn(0).setHeaderRenderer(renderer); 
		getTblFacturacion().getColumnModel().getColumn(0).setHeaderValue(headerLabel1);
		
		columnasConsolidado();
		
	}
	
	
	private void columnasConsolidado(){
		if(tipo.equals("C"))
		{
			Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");		 
			JLabel headerLabel1 = new JLabel("#Factura", JLabel.CENTER);		
			headerLabel1.setBorder(headerBorder);
			JLabel headerLabel2 = new JLabel("FechaEmisión", JLabel.CENTER);		
			headerLabel2.setBorder(headerBorder);
			JLabel headerLabel3 = new JLabel("Cliente", JLabel.CENTER);		
			headerLabel3.setBorder(headerBorder);
			JLabel headerLabel4 = new JLabel("A", JLabel.CENTER);		
			headerLabel4.setBorder(headerBorder);
			JLabel headerLabel5 = new JLabel("Exterior", JLabel.CENTER);		
			headerLabel5.setBorder(headerBorder);
			JLabel headerLabel6 = new JLabel("Reembolso", JLabel.CENTER);		
			headerLabel6.setBorder(headerBorder);
			JLabel headerLabel7 = new JLabel("Normal", JLabel.CENTER);		
			headerLabel7.setBorder(headerBorder);
			JLabel headerLabel8 = new JLabel("12% IVA", JLabel.CENTER);		
			headerLabel8.setBorder(headerBorder);
			JLabel headerLabel9 = new JLabel("TOTAL US$", JLabel.CENTER);		
			headerLabel9.setBorder(headerBorder);
			JLabel headerLabel10 = new JLabel("# Ret.Renta", JLabel.CENTER);		
			headerLabel10.setBorder(headerBorder);
			JLabel headerLabel11 = new JLabel("Renta 1%", JLabel.CENTER);		
			headerLabel11.setBorder(headerBorder);
			JLabel headerLabel12 = new JLabel("Renta 2%", JLabel.CENTER);		
			headerLabel12.setBorder(headerBorder);
			JLabel headerLabel13 = new JLabel("# Ret.IVA", JLabel.CENTER);		
			headerLabel13.setBorder(headerBorder);
			JLabel headerLabel14 = new JLabel("IVA 30%", JLabel.CENTER);		
			headerLabel14.setBorder(headerBorder);
			JLabel headerLabel15 = new JLabel("IVA 70%", JLabel.CENTER);		
			headerLabel15.setBorder(headerBorder);
			JLabel headerLabel16 = new JLabel("IVA 100%", JLabel.CENTER);		
			headerLabel16.setBorder(headerBorder);
			
			TableCellRenderer renderer = new JComponentTableCellRenderer();
			getTblFacturacion().getColumnModel().getColumn(0).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(0).setHeaderValue(headerLabel1);
			getTblFacturacion().getColumnModel().getColumn(1).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(1).setHeaderValue(headerLabel2);
			getTblFacturacion().getColumnModel().getColumn(2).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(2).setHeaderValue(headerLabel3);
			getTblFacturacion().getColumnModel().getColumn(3).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(3).setHeaderValue(headerLabel4);
			getTblFacturacion().getColumnModel().getColumn(4).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(4).setHeaderValue(headerLabel5);
			getTblFacturacion().getColumnModel().getColumn(5).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(5).setHeaderValue(headerLabel6);
			getTblFacturacion().getColumnModel().getColumn(6).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(6).setHeaderValue(headerLabel7);
			getTblFacturacion().getColumnModel().getColumn(7).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(7).setHeaderValue(headerLabel8);
			getTblFacturacion().getColumnModel().getColumn(8).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(8).setHeaderValue(headerLabel9);
			getTblFacturacion().getColumnModel().getColumn(9).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(9).setHeaderValue(headerLabel10);
			getTblFacturacion().getColumnModel().getColumn(10).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(10).setHeaderValue(headerLabel11);
			getTblFacturacion().getColumnModel().getColumn(11).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(11).setHeaderValue(headerLabel12);
			getTblFacturacion().getColumnModel().getColumn(12).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(12).setHeaderValue(headerLabel13);
			getTblFacturacion().getColumnModel().getColumn(13).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(13).setHeaderValue(headerLabel14);
			getTblFacturacion().getColumnModel().getColumn(14).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(14).setHeaderValue(headerLabel15);
			getTblFacturacion().getColumnModel().getColumn(15).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(15).setHeaderValue(headerLabel16);
			
			TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
			anchoColumna.setPreferredWidth(25);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
			anchoColumna.setPreferredWidth(90);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
			anchoColumna.setPreferredWidth(100);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
			anchoColumna.setPreferredWidth(100);			 
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(13);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(14);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(15);
			anchoColumna.setPreferredWidth(80);
			
		}
		
		if(tipo.equals("CC"))
		{
			Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");		 
			JLabel headerLabel1 = new JLabel("#Factura", JLabel.CENTER);		
			headerLabel1.setBorder(headerBorder);
			JLabel headerLabel2 = new JLabel("F.Emisión", JLabel.CENTER);		
			headerLabel2.setBorder(headerBorder);
			JLabel headerLabel3 = new JLabel("Cliente", JLabel.CENTER);		
			headerLabel3.setBorder(headerBorder);
			JLabel headerLabel4 = new JLabel("F.Emisión.Ret", JLabel.CENTER);		
			headerLabel4.setBorder(headerBorder);
			JLabel headerLabel5 = new JLabel("Ret.RENTA(US$)", JLabel.CENTER);		
			headerLabel5.setBorder(headerBorder);
			JLabel headerLabel6 = new JLabel("Cód.Ret", JLabel.CENTER);		
			headerLabel6.setBorder(headerBorder);
			JLabel headerLabel7 = new JLabel("Ret.IVA(US$)", JLabel.CENTER);		
			headerLabel7.setBorder(headerBorder);
			JLabel headerLabel8 = new JLabel("Cód.Ret", JLabel.CENTER);		
			headerLabel8.setBorder(headerBorder);
			
			TableCellRenderer renderer = new JComponentTableCellRenderer();
			getTblFacturacion().getColumnModel().getColumn(0).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(0).setHeaderValue(headerLabel1);
			getTblFacturacion().getColumnModel().getColumn(1).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(1).setHeaderValue(headerLabel2);
			getTblFacturacion().getColumnModel().getColumn(2).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(2).setHeaderValue(headerLabel3);
			getTblFacturacion().getColumnModel().getColumn(3).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(3).setHeaderValue(headerLabel4);
			getTblFacturacion().getColumnModel().getColumn(4).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(4).setHeaderValue(headerLabel5);
			getTblFacturacion().getColumnModel().getColumn(5).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(5).setHeaderValue(headerLabel6);
			getTblFacturacion().getColumnModel().getColumn(6).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(6).setHeaderValue(headerLabel7);
			getTblFacturacion().getColumnModel().getColumn(7).setHeaderRenderer(renderer); 
			getTblFacturacion().getColumnModel().getColumn(7).setHeaderValue(headerLabel8);
			
			TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
			anchoColumna.setPreferredWidth(85);
			
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(13);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(14);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblFacturacion().getColumnModel().getColumn(15);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);		
			anchoColumna.setPreferredWidth(0);
		}
		
	}
	
	class JComponentTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			return (JComponent)value;
		}
	}
	
	@Override
	public void find() {
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
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void clean() {
		cleanTotales();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCbPreimpreso().setSelected(true);
		getCbCliente().setSelected(false);
		// getCbPreimpreso().setVisible(false);
		// getCbCliente().setVisible(false);
		cleanTable();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblFacturacion()
		.getModel();
		for (int i = this.getTblFacturacion().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void report() {
		try {
			
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblFacturacion()
			.getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = { si, no };
				String mensaje = "¿Desea generar el reporte de Facturación?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje,
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "";
					
					if(tipo.equals("CC")){
						fileName = "jaspers/facturacion/RPFacturacionPorIVAyRetencionConsolidado.jasper";
					}else{
						//fileName = "jaspers/facturacion/RPFacturacionPorIVAyRetencionEXCEL.jasper";
						fileName = "jaspers/facturacion/RPFacturacionPorIVAyRetencion.jasper";
					}
					
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_FACTURACION).iterator().next();
					
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_FACTURACION).iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0, 1).concat(ciudad.getNombre().substring(1,ciudad.getNombre().length()).toLowerCase()));
					parametrosMap.put("usuario", Parametros.getUsuario());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0, 4);
					String month = fechaActual.substring(5, 7);
					String day = fechaActual.substring(8, 10);
					String fechaEmision = day+ " "+ Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					parametrosMap.put("emitido", fechaEmision);
					Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					parametrosMap.put("totalFacturacion", formatoDecimal.format(totalTotal));
					parametrosMap.put("blanco", " ");
					
					
					System.out.println(tipo+Parametros.getRutaCarpetaReportes()+"facturacionConsolidadoColeccion:**"+facturacionConsolidadoColeccion.size());
					System.out.println("facturacionConsolidadoResumenColeccion:*****"+facturacionConsolidadoResumenColeccion.size());
					
					JRDataSource dataSourceDebitos = new JRBeanCollectionDataSource(facturacionConsolidadoResumenColeccion);
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						parametrosMap.put("pathsubreportResumen", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaConsolidadoResumen.jasper");
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
						parametrosMap.put("dataSourceResumen", dataSourceDebitos);
					
					
					if (tipo.equals("CC"))
					{						
						ReportModelImpl.processReport(fileName, parametrosMap,facturacionConsolidadoColeccion, true);						
					}
					else
					{						
						if(facturacionColeccionNC.size()>0)
						{							
							parametrosMap.put("exterior",formatoDecimal.format(exterior-exteriorNC.doubleValue()));						
							parametrosMap.put("reembolso",formatoDecimal.format(reembolso-reembolsoNC.doubleValue()));		
							parametrosMap.put("normal",formatoDecimal.format(normal-normalNC.doubleValue()));		
							parametrosMap.put("iva",formatoDecimal.format(iva));		
							parametrosMap.put("total",formatoDecimal.format(totalResumen));
							
							//fileName = "jaspers/facturacion/RPFacturacionPorIVAyRetencionNCResumenPrincipalEXCEL.jasper";
							fileName = "jaspers/facturacion/RPFacturacionPorIVAyRetencionNCResumenPrincipal.jasper";
							
							JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(facturacionColeccionNC);
							if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
								parametrosMap.put("pathsubreportResumenNC", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorIVAyRetencionNCResumen.jasper");
							else 
								throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
							parametrosMap.put("dataSourceResumenNC", dataSourceDebitos2);
							
						}
						else//no hay notas de credito
						{
							
						}
						
						System.out.println("FILENAME;:"+fileName);
						System.out.println("parametrosMap;:---->"+parametrosMap); 
						String notaTexto="La factura 001-001-0029601 del cliente Municipio por un valor de $21,066.11 es una venta tarifa 0% " +
								"a instituciones públicas. Este valor disminuirá el total facturado de este mes.";
														
						boolean datos=llenardatosNota();
						if(datos){
							parametrosMap.put("notaTitulo", "NOTA");
							parametrosMap.put("notaTexto", notaTexto);
						}
						else{
							parametrosMap.put("notaTitulo"," ");
							parametrosMap.put("notaTexto"," ");
						}
						
					System.out.println("valor nota>>>>"+datos);
						
						ReportModelImpl.processReport(fileName, parametrosMap,facturacionColeccion, true);						
					}					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir",
						SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Se ha producido un error al generar el reporte",
					SpiritAlert.ERROR);
		} catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Se ha producido un error al generar el reporte",
					SpiritAlert.ERROR);
		}
	}
	
	public boolean llenardatosNota(){
		boolean flag=false;
		for(int i=0; i<facturacionColeccion.size(); i++){
						
			FacturacionIvaRetencionData data= (FacturacionIvaRetencionData)facturacionColeccion.get(i);
			
			String valor=data.getNormal();
			String fecha=data.getFechaEmision();
			String numfac=data.getNumeroFactura();
			
			if(valor.equals("21,066.11") && fecha.equals("10-DIC-2009") && numfac.equals("001-001-0029601")) 
			{
				flag=true;
				break;
			}		
		}
		return flag;
	}
	
	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	public void cleanTotales() {
		facturacionColeccion = null;
		facturacionColeccion = new Vector<FacturacionIvaRetencionData>();
		totalTotal = new BigDecimal(0);
		
		facturacionConsolidadoColeccion = null;
		facturacionConsolidadoColeccion = new Vector<FacturacionReporteRetencionesData>();
		facturacionConsolidadoResumenColeccion.clear();
		facturacionConsolidadoResumenColeccion = null;
		facturacionConsolidadoResumenColeccion = new Vector<FacturacionReporteRetencionesData>();
		facturacionConsolidadoColeccion.clear();
		mapaCamposValoresMeses.clear();
		facturacionColeccionNC = null;
		facturacionColeccionNC = new Vector<FacturacionIvaRetencionData>();
			
		exterior=0D;
		reembolso=0D;
		normal=0D;
		iva=0D;
		totalResumen=0D;
		exteriorNC = new BigDecimal(0);
		reembolsoNC = new BigDecimal(0);
		normalNC = new BigDecimal(0);
	}
	
	public void generarColeccionFacturas() {
		try {
				facturasMap = null;
				facturasMap = new HashMap();
				if (clienteOficinaIf != null) {
					facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
				}
								
				
				Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
				Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
				
				ArrayList facturas=null;
				if(!getCmbEstado().getSelectedItem().equals("(A) Anuladas"))
				{
					//facturas = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFin(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
					facturas = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
				}
				
				facturasMap.put("estado", ESTADO_ANULADO);
				/*ArrayList facturasAnuladas = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFin(facturasMap,
						new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));*/
				ArrayList facturasAnuladas = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap,
						new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()), Parametros.getIdEmpresa());
				
				cargarMapasFacturaCartera(facturas, facturasAnuladas);
				
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void cargarMapasFacturaCartera(ArrayList facturas,ArrayList facturasAnuladas) {
		
		mapaFactura = null;
		mapaCartera = null;
		carteraDetalleVector = null;
		mapaFactura = new LinkedMap();
		mapaCartera = new HashMap<Long, CarteraIf>();
		carteraDetalleVector = new Vector<CarteraDetalleIf>();
		
		ArrayList<FacturaIf> facturasOrdenadas = new ArrayList<FacturaIf>();	
		
		if(facturas!=null)
		{
			Iterator facturasIt = facturas.iterator();		
			while (facturasIt.hasNext()) {
				Object[] facturasObject = (Object[]) facturasIt.next();
				FacturaIf facturaIf = (FacturaIf) facturasObject[0];
				mapaFactura.put(facturaIf.getId(), facturaIf);
				CarteraIf carteraIf = (CarteraIf) facturasObject[1];
				mapaCartera.put(carteraIf.getReferenciaId(), carteraIf);
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) facturasObject[2];
				carteraDetalleVector.add(carteraDetalleIf);
			}
		}
		
		Iterator facturasAnuladasIt = facturasAnuladas.iterator();
		while (facturasAnuladasIt.hasNext()) {
			FacturaIf facturaIf = (FacturaIf) facturasAnuladasIt.next();
			mapaFactura.put(facturaIf.getId(), facturaIf);
		}
		
		Iterator mapaFacturasIt = mapaFactura.keySet().iterator();
		while (mapaFacturasIt.hasNext()) {
			Long facturaId = (Long) mapaFacturasIt.next();
			FacturaIf factura = (FacturaIf) mapaFactura.get(facturaId);
			facturasOrdenadas.add(factura);
		}
		
		if (getCbPreimpreso().isSelected()) {
			Collections.sort(facturasOrdenadas, ordenadorFacturaPorPreimpreso);
		} else {
			Collections.sort(facturasOrdenadas, ordenadorFacturaPorCliente);
		}
		
		long start=System.currentTimeMillis();
		
		generarMapaFacturaRentenciones(facturasOrdenadas, carteraDetalleVector);
		
		long fin=System.currentTimeMillis();
		System.out.println("---------------------inicializa facturacion Iva Retencion: "+(fin-start)/1000+" seg");
		
	}
	
	
	//aca
	private void generarMapaFacturaRentenciones(
			ArrayList<FacturaIf> facturasOrdenadas,
			Vector<CarteraDetalleIf> carteraDetalleVector) {
		try {
			mapaFacturaRetenciones = null;
			mapaFacturaRetenciones = new LinkedMap();
			
			
			DocumentoIf documento = null;
			Iterator facturasOrdenadasIt = facturasOrdenadas.iterator();
			while (facturasOrdenadasIt.hasNext()) {
				FacturaIf factura = (FacturaIf) facturasOrdenadasIt.next();
				Map mapaRetenciones = null;
				Map mapaRetencionesPorc = null;
				
				if (mapaFacturaRetenciones.get(factura) == null) {
					
					mapaRetenciones = new HashMap();
					mapaRetenciones.put(RETENCION_RENTA,"");
					mapaRetenciones.put(RETENCION_RENTA_1, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_RENTA_2, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA, " ");
					mapaRetenciones.put(RETENCION_IVA_30, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_70, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_100, new BigDecimal(0));
					
					mapaRetencionesPorc = (Map<String, BigDecimal>) mapaFacturaRetenciones.get(factura);
					
					if(mapaRetencionesPorc==null)  mapaRetencionesPorc = new HashMap();
					
					if (mapaCartera.get(factura.getId()) != null) { 
						CarteraIf cartera = (CarteraIf) mapaCartera.get(factura.getId());
						for (int i = 0; i < carteraDetalleVector.size(); i++) {
							CarteraDetalleIf carteraDetalle = carteraDetalleVector.get(i);
							if (carteraDetalle.getCarteraId().compareTo(cartera.getId()) == 0) {
								Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraafectaId(carteraDetalle.getId());
								Iterator carterasAfectaIt = carterasAfecta.iterator();
								while (carterasAfectaIt.hasNext()) {
									CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carterasAfectaIt.next();
									CarteraDetalleIf carteraDetalleIf = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
									if (carteraDetalleIf.getSriClienteRetencionId() != null) {										
										SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalleIf.getSriClienteRetencionId());										
										if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)) {
											if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(1)) == 0) {											
												mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal("1"));
												mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
												
											} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(2)) == 0) {												
												mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_2,new BigDecimal("2"));
												mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
											}
										} else if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)) {											
											if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(30)) == 0) {
												mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_30,new BigDecimal("30"));				
												mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
											} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(70)) == 0) {
												mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));				
												mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
											} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(100)) == 0) {
												mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_100,new BigDecimal("100"));
												mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
											}
										}
									} else {
										
										documento=mapaDocumento.get(carteraDetalleIf.getDocumentoId());										
										//documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleIf.getDocumentoId());
										if (documento.getCodigo().equals("RERC")) {
											mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal("1"));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										} else if (documento.getCodigo().equals("REIC")) {											
											mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										}
									}
								}
							}
						}
						//}
					}
					
					mapaFacturaRetencionesPorcentaje.put(factura, mapaRetencionesPorc);
					
				} else {
					mapaRetenciones = (Map<String, BigDecimal>) mapaFacturaRetenciones.get(factura);
				}
				
				
				
				if (mapaCartera.get(factura.getId()) != null) {
					
					
					
					CarteraIf cartera = (CarteraIf) mapaCartera.get(factura.getId());
					for (int i = 0; i < carteraDetalleVector.size(); i++) {
						CarteraDetalleIf carteraDetalle = carteraDetalleVector.get(i);
						if (carteraDetalle.getCarteraId().compareTo(cartera.getId()) == 0) {
							Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraafectaId(carteraDetalle.getId());
							Iterator carterasAfectaIt = carterasAfecta.iterator();
							while (carterasAfectaIt.hasNext()) {
								CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carterasAfectaIt.next();
								CarteraDetalleIf carteraDetalleIf = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
								if (carteraDetalleIf.getSriClienteRetencionId() != null) {
									
									SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalleIf.getSriClienteRetencionId());
									
									if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)) {
										mapaRetenciones.put(RETENCION_RENTA,carteraDetalleIf.getPreimpreso() != null ? carteraDetalleIf.getPreimpreso(): " ");	
										if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(1)) == 0) {
											mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal(1));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
											mapaRetenciones.put(RETENCION_RENTA_1,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
										} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(2)) == 0) {
											mapaRetenciones.put(RETENCION_RENTA_2,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_2)).add(carteraAfectaIf.getValor()));
											mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_2,new BigDecimal("2"));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										}
										
										
									} else if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)) {
										mapaRetenciones.put(RETENCION_IVA,carteraDetalleIf.getPreimpreso() != null ? carteraDetalleIf.getPreimpreso(): " ");
										if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(30)) == 0) {											
											mapaRetenciones.put(RETENCION_IVA_30,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_30)).add(carteraAfectaIf.getValor()));
											mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_30,new BigDecimal("30"));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(70)) == 0) {											
											mapaRetenciones.put(RETENCION_IVA_70,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
											mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));	
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(100)) == 0) {											
											mapaRetenciones.put(RETENCION_IVA_100,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_100)).add(carteraAfectaIf.getValor()));
											mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_100,new BigDecimal("100"));
											mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
										}
									}
								} else {
									documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleIf.getDocumentoId());
									if (documento.getCodigo().equals("RERC")) {
										mapaRetenciones.put(RETENCION_RENTA,carteraDetalleIf.getPreimpreso() != null ? carteraDetalleIf.getPreimpreso(): " ");
										mapaRetenciones.put(RETENCION_RENTA_1,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
										mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal(1));
										mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
									} else if (documento.getCodigo().equals("REIC")) {
										mapaRetenciones.put(RETENCION_IVA,carteraDetalleIf.getPreimpreso() != null ? carteraDetalleIf.getPreimpreso(): " ");
										mapaRetenciones.put(RETENCION_IVA_70,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
										mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));
										mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
									}
								}
							}
						}
					}
				}
				
				
				mapaFacturaRetenciones.put(factura, mapaRetenciones);
			}
			
			System.out.println("df");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Comparator<Object[]> ordenadorFacturaPorPreimpreso = new Comparator<Object[]>(){
	 * public int compare(Object[] o1, Object[] o2) {
	 * if(((FacturaIf)o1[0]).getPreimpresoNumero() == null){ return -1; }else
	 * if(((FacturaIf)o2[0]).getPreimpresoNumero() == null){ return 1; }else{
	 * return
	 * ((FacturaIf)o1[0]).getPreimpresoNumero().compareTo(((FacturaIf)o2[0]).getPreimpresoNumero()); } } };
	 */
	
	Comparator<FacturaIf> ordenadorFacturaPorPreimpreso = new Comparator<FacturaIf>() {
		public int compare(FacturaIf o1, FacturaIf o2) {
			if (o1.getPreimpresoNumero() == null) {
				return -1;
			} else if (o2.getPreimpresoNumero() == null) {
				return 1;
			} else {
				return (o1.getPreimpresoNumero().compareTo(o2
						.getPreimpresoNumero()));
			}
		}
	};
	
	Comparator<FacturaIf> ordenadorFacturaPorCliente = new Comparator<FacturaIf>() {
		public int compare(FacturaIf o1, FacturaIf o2) {
			ClienteOficinaIf clienteOficinaO1 = mapaClienteOficina.get(o1
					.getClienteoficinaId());
			ClienteIf clienteO1 = mapaCliente.get(clienteOficinaO1
					.getClienteId());
			ClienteOficinaIf clienteOficinaO2 = mapaClienteOficina.get(o2
					.getClienteoficinaId());
			ClienteIf clienteO2 = mapaCliente.get(clienteOficinaO2
					.getClienteId());
			return (clienteO1.getNombreLegal().compareTo(clienteO2
					.getNombreLegal()));
		}
	};
	
	
	Comparator<FacturacionReporteRetencionesData> ordenadorArrayListPorCodigo = new Comparator<FacturacionReporteRetencionesData>(){
		public int compare(FacturacionReporteRetencionesData o1, FacturacionReporteRetencionesData o2) {
			return (o1.getTipoRetencion().compareTo(o2.getTipoRetencion()));
		}		
	};
	
	// johy
	private void cargarTabla() {
		
		cleanTotales();		
		columnasConsolidado();		
		if(tipo.equals("C"))
		{
			
			generarColeccionFacturas();		
			Iterator mapaFacturaRetencionesIt = mapaFacturaRetenciones.keySet().iterator();
			
			while (mapaFacturaRetencionesIt.hasNext()) {
				FacturaIf facturaIf = (FacturaIf) mapaFacturaRetencionesIt.next();
				Map mapaRetenciones = (Map) mapaFacturaRetenciones.get(facturaIf);
				Map mapaRetencionesPorcentaje = (Map) mapaFacturaRetencionesPorcentaje.get(facturaIf);			
				tableModel = (DefaultTableModel) getTblFacturacion().getModel();
				Vector<String> fila = new Vector<String>();
				FacturacionIvaRetencionData facturacionData = new FacturacionIvaRetencionData();
				FacturacionReporteRetencionesData facturacionReporteRetenciones = new FacturacionReporteRetencionesData();
				// poner con Data
				agregarColumnasTabla(facturaIf, mapaRetenciones, fila,facturacionData, facturacionReporteRetenciones,mapaRetencionesPorcentaje);
			}
			
			tipo="C";
		}else{
			
			if (getCmbEstado().getSelectedObjects().length > 0)	getCmbEstado().setSelectedIndex(0);
			
			generarMapaRentenciones();		
			Iterator mapaCarteraRetencionesIt = mapaCarteraRetenciones.keySet().iterator();		
			while (mapaCarteraRetencionesIt.hasNext()) {
				CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) mapaCarteraRetencionesIt.next();
				Map mapaRetencionesREF = (Map) mapaCarteraRetenciones.get(carteraAfectaIf);
				Map mapaRetencionesPorcentajeC = (Map) mapaCarteraRetencionesPorcentaje.get(carteraAfectaIf);			
				tableModel = (DefaultTableModel) getTblFacturacion().getModel();
				// poner con Data
				Vector<String> fila = new Vector<String>();
				agregarColumnasTablaSinReferencia(carteraAfectaIf, mapaRetencionesREF,mapaRetencionesPorcentajeC, fila);
			}
			tipo="CC";
		 
		} 
		
		/////para el resumen	
		for(int i=0;i<facturacionConsolidadoColeccion.size();i++){
			FacturacionReporteRetencionesData datos;
			datos=facturacionConsolidadoColeccion.get(i);
			if (mapaCamposValoresMeses.get(datos.getCodigoRetencionIva()) == null) {	
				if(datos.getValorIva()==null) datos.setValorIva("0");				
				datos.setValorIva(datos.getValorIva().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionIva(),new BigDecimal(datos.getValorIva()));					
			} else {								
				if(datos.getValorIva()==null) datos.setValorIva("0");
				datos.setValorIva(datos.getValorIva().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionIva(),new BigDecimal(datos.getValorIva()).add(mapaCamposValoresMeses.get(datos.getCodigoRetencionIva())));					
			}
			
			if (mapaCamposValoresMeses.get(datos.getCodigoRetencionRenta()) == null) {
				if(datos.getValorRenta()==null) datos.setValorRenta("0");
				datos.setValorRenta(datos.getValorRenta().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionRenta(),new BigDecimal(datos.getValorRenta()));					
			} else {								
				if(datos.getValorRenta()==null) datos.setValorRenta("0");
				datos.setValorRenta(datos.getValorRenta().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionRenta(),new BigDecimal(datos.getValorRenta()).add(mapaCamposValoresMeses.get(datos.getCodigoRetencionRenta())));					
			}			
		} 
				
		Iterator mapaResumenIt = mapaCamposValoresMeses.keySet().iterator();
		while (mapaResumenIt.hasNext()) {
			FacturacionReporteRetencionesData facturacionData = new FacturacionReporteRetencionesData();
			String key = (String)mapaResumenIt.next();
			//facturacionData.setCodigoRetencionIva(key);
			facturacionData.setCodigoRetencionIva(key);
			BigDecimal val = (BigDecimal) mapaCamposValoresMeses.get(key);
			System.out.println("key!!!"+key);
			
			if(key.contains("I")){	
				
				String key2=key;
				if(key2.length()>2) key2=key2.substring(2, key2.length()-1);
				facturacionData.setCodigoRetencionIva(key2);			
				facturacionData.setTipoRetencion("I");
				facturacionData.setValorIva(val.toString());
				facturacionData.setValorRenta("0.00");
				if(facturacionData.getValorIva().equals("")) facturacionData.setValorIva("0.00"); 
				if(facturacionData.getValorRenta().equals("")) facturacionData.setValorRenta("0.00");
				facturacionConsolidadoResumenColeccion.add(facturacionData);
				
			}
			
			if(key.contains("R")){				
				String key2=key;
				if(key2.length()>2) key2=key2.substring(2, key2.length()-1);
				facturacionData.setCodigoRetencionIva(key2);				
				System.out.println("KEY2 r-->"+key2);				
				facturacionData.setTipoRetencion("R");
				facturacionData.setValorRenta(val.toString());
				facturacionData.setValorIva("0.00");
				if(facturacionData.getValorIva().equals("")) facturacionData.setValorIva("0.00"); 
				if(facturacionData.getValorRenta().equals("")) facturacionData.setValorRenta("0.00");
				facturacionConsolidadoResumenColeccion.add(facturacionData);
			}
		}
		
		Collections.sort(facturacionConsolidadoResumenColeccion,ordenadorArrayListPorCodigo);
		
		if(!getCmbEstado().getSelectedItem().equals("(A) Anuladas"))
		{
			ArrayList carterap=null;
			carterap=generarColeccionFacturasNotasCredito();
			Iterator carteraIt = carterap.iterator();
			while (carteraIt.hasNext()) {				
				NotaCreditoIf notacreditoIf = (NotaCreditoIf) carteraIt.next();	
				Vector<String> fila = new Vector<String>();			
				agregarColumnasTablaNC_ND(fila,notacreditoIf,"NC");
			}
		}
		/*
		 Iterator carteraIt2 = carterap.iterator();
		 while (carteraIt.hasNext()) {				
		 CarteraIf compraDetalleIf = (NotaCreditoIf) carteraIt.next();	
		 Vector<String> fila = new Vector<String>();			
		 agregarColumnasTablaNC_ND(fila,compraDetalleIf,"NC");
		 }
		*/
	}
	
	
	
	private void agregarColumnasTablaSinReferencia(CarteraAfectaIf carteraAfectaIf, Map mapaRetencionesREF,Map mapaRetencionesPorcentaje,Vector<String> fila) {
		cleanCantidades();
		
		FacturacionReporteRetencionesData facturacionReporteRetencionesData = new FacturacionReporteRetencionesData();
		
		
		facturacionReporteRetencionesData.setClienteId("");
		facturacionReporteRetencionesData.setNumeroFactura("");
		facturacionReporteRetencionesData.setFechaEmision("");
		facturacionReporteRetencionesData.setCliente("");
		facturacionReporteRetencionesData.setNumeroFactura("");
		facturacionReporteRetencionesData.setFechaEmision("");
		facturacionReporteRetencionesData.setFechaEmisionRetencion("");
		facturacionReporteRetencionesData.setCliente("");
		//////
		if(mapaRetencionesPorcentaje!=null)
		{	
			
			
			if(mapaRetencionesREF.get(RETENCION_RENTA)!=null)
			{	
				facturacionReporteRetencionesData.setNumeroFactura(mapaRetencionesREF.get(RETENCION_RENTA).toString());
			}
			
			fila.add(facturacionReporteRetencionesData.getNumeroFactura());
			
			if(mapaRetencionesPorcentaje.get(FECHA_EMISION)!=null)
			{	
				String fechaRetencion=mapaRetencionesPorcentaje.get(FECHA_EMISION).toString();
				if (!fechaRetencion.equals(""))	fechaRetencion = fechaRetencion.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaRetencion.substring(5,7))).substring(0,3) + "-" + fechaRetencion.substring(0,4);							
				facturacionReporteRetencionesData.setFechaEmision(fechaRetencion);						
			}
			fila.add(facturacionReporteRetencionesData.getFechaEmision());
			
			if(mapaRetencionesPorcentaje.get(CLIENTE_ID)!=null)
			{	
				
				String clienteId=mapaRetencionesPorcentaje.get(CLIENTE_ID).toString();
				
				if(clienteId!=null && !clienteId.equals("")){
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(new Long(clienteId));
					ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
					facturacionReporteRetencionesData.setClienteId(cliente.getId().toString());
					facturacionReporteRetencionesData.setCliente(cliente.getNombreLegal());
				}
				
			}
			fila.add(facturacionReporteRetencionesData.getCliente());
			
			
			
			//OJO: mapaRetenciones.get(RETENCION_RENTA).toString()
			if(mapaRetencionesPorcentaje.get(FECHA_RETENCION)!=null)
			{	
				String fechaRetencion=mapaRetencionesPorcentaje.get(FECHA_RETENCION).toString();
				if (!fechaRetencion.equals(""))	fechaRetencion = fechaRetencion.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaRetencion.substring(5,7))).substring(0,3) + "-" + fechaRetencion.substring(0,4);							
				facturacionReporteRetencionesData.setFechaEmisionRetencion(fechaRetencion);						
			}
			fila.add(facturacionReporteRetencionesData.getFechaEmisionRetencion());
			
			
			
			facturacionReporteRetencionesData.setCodigoRetencionRenta("");
			facturacionReporteRetencionesData.setCodigoRetencionIva("");
			
			
			
			if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_70)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_70).toString().equals("70"))
			{
				facturacionReporteRetencionesData.setTipoRetencion("I");
				facturacionReporteRetencionesData.setCodigoRetencionIva("I(70%)");
				facturacionReporteRetencionesData.setValorIva(formatoDecimal.format(mapaRetencionesREF.get(RETENCION_IVA_70)));
				if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
			}
			
			if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_100)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_100).toString().equals("100"))
			{
				facturacionReporteRetencionesData.setTipoRetencion("I");
				facturacionReporteRetencionesData.setCodigoRetencionIva("I(100%)");
				facturacionReporteRetencionesData.setValorIva(formatoDecimal.format(mapaRetencionesREF.get(RETENCION_IVA_100)));
				if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
			}
			
			if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_30)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_30).toString().equals("30"))
			{
				facturacionReporteRetencionesData.setTipoRetencion("I");
				facturacionReporteRetencionesData.setCodigoRetencionIva("I(30%)");
				facturacionReporteRetencionesData.setValorIva(formatoDecimal.format(mapaRetencionesREF.get(RETENCION_IVA_30)));
				if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
			}
			
			if (mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_1)!=null && mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_1).toString().equals("1"))
			{
				facturacionReporteRetencionesData.setTipoRetencion("R");
				facturacionReporteRetencionesData.setCodigoRetencionRenta("R(1%)");															
				facturacionReporteRetencionesData.setValorRenta(formatoDecimal.format(mapaRetencionesREF.get(RETENCION_RENTA_1)));
				if(facturacionReporteRetencionesData.getValorRenta().equals("0")) facturacionReporteRetencionesData.setValorRenta("0.00");
			}
			
			if (mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_2)!=null && mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_2).toString().equals("2"))
			{
				facturacionReporteRetencionesData.setTipoRetencion("R");
				facturacionReporteRetencionesData.setCodigoRetencionRenta("R(2%)");
				facturacionReporteRetencionesData.setValorRenta(formatoDecimal.format(mapaRetencionesREF.get(RETENCION_RENTA_2)));
				if(facturacionReporteRetencionesData.getValorRenta().equals("0")) facturacionReporteRetencionesData.setValorRenta("0.00");
			}
			
			if(facturacionReporteRetencionesData.getValorIva()==null)	facturacionReporteRetencionesData.setValorIva("0");
			if(facturacionReporteRetencionesData.getValorRenta()==null)	facturacionReporteRetencionesData.setValorRenta("0");					
			if(facturacionReporteRetencionesData.getValorIva()!=null && facturacionReporteRetencionesData.getValorIva().toString().equals(""))		facturacionReporteRetencionesData.setValorIva("0.00");
			if(facturacionReporteRetencionesData.getValorRenta()!=null && facturacionReporteRetencionesData.getValorRenta().toString().equals(""))	facturacionReporteRetencionesData.setValorRenta("0.00");
			
			
			fila.add(facturacionReporteRetencionesData.getCodigoRetencionRenta());
			fila.add(facturacionReporteRetencionesData.getValorRenta());
			fila.add(facturacionReporteRetencionesData.getCodigoRetencionIva());
			fila.add(facturacionReporteRetencionesData.getValorIva());
			
			
			facturacionConsolidadoColeccion.add(facturacionReporteRetencionesData);
			
			tableModel.addRow(fila);
			
		}
		
	}
	
	
	
	private void generarMapaRentenciones(){
		try {			 
			mapaCarteraRetenciones = null;
			mapaCarteraRetenciones = new LinkedMap();			
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());			
			Map parameterMap = new HashMap();
			parameterMap=null;
			
			ArrayList carterasAfecta = (ArrayList) SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByQueryByFechas(parameterMap,fechaInicio,fechaFin,Parametros.getIdEmpresa());
			Iterator carterasAfectaIt = carterasAfecta.iterator();
			
			while (carterasAfectaIt.hasNext()) {
				Object[] facturasObject = (Object[]) carterasAfectaIt.next();				
				
				CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) facturasObject[0];
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) facturasObject[1];
				CarteraIf carteraIf = (CarteraIf) facturasObject[2];
								
				FacturaIf facturaIf =null;
				
				//ArrayList facturaIfArray =  (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaByCarteraDetalleRetencionId(carteraDetalleIf.getId());
				
				//para mejorar partir el anterior query en dos:
				ArrayList<Object[]> facturaIfArray = new ArrayList<Object[]>();
			
				ArrayList<CarteraAfectaIf> carterasAfectaDetalleRetencion = (ArrayList<CarteraAfectaIf>)SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleIf.getId());
				if(carterasAfectaDetalleRetencion.size() > 0){
					for(CarteraAfectaIf carteraAfectaRetencion : carterasAfectaDetalleRetencion){
						facturaIfArray =  (ArrayList<Object[]>) SessionServiceLocator.getFacturaSessionService().findFacturaByCarteraDetalleComprobante(carteraAfectaRetencion.getCarteraafectaId());
						if(facturaIfArray.size() > 0){
							facturaIf = (FacturaIf) facturaIfArray.iterator().next()[0];									
						}
					}
				}	
				
				if (carteraDetalleIf.getSriClienteRetencionId() != null) {										
					SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalleIf.getSriClienteRetencionId());
										
					Map mapaRetenciones = null;
					Map mapaRetencionesPorc = null;
					mapaRetenciones = new HashMap();
					mapaRetenciones.put(RETENCION_RENTA, " ");
					mapaRetenciones.put(RETENCION_RENTA_1, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_RENTA_2, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA, " ");
					mapaRetenciones.put(RETENCION_IVA_30, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_70, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_100, new BigDecimal(0));
										
					if(mapaRetencionesPorc==null)  
						mapaRetencionesPorc = new HashMap();
					
					if(facturaIf!=null){
						mapaRetenciones.put(RETENCION_RENTA,facturaIf.getPreimpresoNumero() != null ? facturaIf.getPreimpresoNumero(): " ");
						mapaRetencionesPorc.put(FECHA_EMISION,facturaIf.getFechaFactura()!= null ? facturaIf.getFechaFactura(): " ");
						mapaRetencionesPorc.put(CLIENTE_ID,facturaIf.getClienteoficinaId()!= null ? facturaIf.getClienteoficinaId(): " ");
					}else {
						mapaRetenciones.put(RETENCION_RENTA,"");
						mapaRetencionesPorc.put(FECHA_EMISION,"");
						mapaRetencionesPorc.put(CLIENTE_ID,"");
					}
					
					if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)) {					
						
						if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(1)) == 0) {											
							mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal("1"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
							mapaRetenciones.put(RETENCION_RENTA_1,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
							
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(2)) == 0) {
							
							mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_2,new BigDecimal("2"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
							mapaRetenciones.put(RETENCION_RENTA_2,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_2)).add(carteraAfectaIf.getValor()));
							
						}
						
					} else if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)) {											
						
						if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(30)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_30,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_30)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_30,new BigDecimal("30"));				
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(70)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_70,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));				
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(100)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_100,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_100)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_100,new BigDecimal("100"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						}
					}
					mapaCarteraRetencionesPorcentaje.put(carteraAfectaIf, mapaRetencionesPorc);
					mapaCarteraRetenciones.put(carteraAfectaIf, mapaRetenciones);
				} 				
			}
			
			//ordenar por numero de preimpreso
			int contador = 0;
			ArrayList<String> preimpresos = new ArrayList<String>();
			HashMap preimpresoCarteraAfecta = new HashMap();
			Iterator mapaCarteraRetencionesIt = mapaCarteraRetenciones.keySet().iterator();
			while(mapaCarteraRetencionesIt.hasNext()){
				CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)mapaCarteraRetencionesIt.next();
				Map mapaRetencionesDesordenado = (Map)mapaCarteraRetenciones.get(carteraAfecta);
				
				//le aumento un secuencial al preimpreso para no repetir la llave del mapa
				String preimpreso = (String)mapaRetencionesDesordenado.get(RETENCION_RENTA) + String.valueOf(contador);
				
				//armo un vector que me servira para ordenar los preimpresos 
				//y un mapa que me servira para ordenar el mapa mapaCarteraRetenciones
				preimpresos.add(preimpreso);
				preimpresoCarteraAfecta.put(preimpreso, carteraAfecta);
				contador++;
			}
			Collections.sort(preimpresos,ordenadorPorPreimpreso);
			//creo un mapa temporal para almacenar los datos ordenados
			LinkedMap mapaCarteraRetencionesTemp = new LinkedMap();
			for(String preimpreso : preimpresos){
				CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)preimpresoCarteraAfecta.get(preimpreso);
				mapaCarteraRetencionesTemp.put(carteraAfecta, mapaCarteraRetenciones.get(carteraAfecta));
			}
			
			//igualo el mapa original al mapa arreglado
			mapaCarteraRetenciones = mapaCarteraRetencionesTemp;
			
			System.out.println("");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<String> ordenadorPorPreimpreso = new Comparator<String>(){
		public int compare(String o1, String o2) {
			return (o1.compareTo(o2));
		}		
	};
	
	private void generarMapaRentenciones_viejo()
	{	
		try {
			mapaCarteraRetenciones = null;
			mapaCarteraRetenciones = new LinkedMap();			
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			Map parameterMap = new HashMap();
			parameterMap=null;
			Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().
			findCarteraAfectaByQueryByFechas(parameterMap,fechaInicio,fechaFin,Parametros.getIdEmpresa());
			//findCarteraAfectaByCarteraafectaId(carteraDetalle.getId());
			
			Iterator carterasAfectaIt = carterasAfecta.iterator();
			while (carterasAfectaIt.hasNext()) {
				CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carterasAfectaIt.next();
				
				CarteraDetalleIf carteraDetalleIf = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
				
				if (carteraDetalleIf.getSriClienteRetencionId() != null) {										
					SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalleIf.getSriClienteRetencionId());
										
					Map mapaRetenciones = null;
					Map mapaRetencionesPorc = null;
					
					mapaRetenciones = new HashMap();
					mapaRetenciones.put(RETENCION_RENTA, " ");
					mapaRetenciones.put(RETENCION_RENTA_1, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_RENTA_2, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA, " ");
					mapaRetenciones.put(RETENCION_IVA_30, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_70, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_100, new BigDecimal(0));
										
					if(mapaRetencionesPorc==null)  mapaRetencionesPorc = new HashMap();
										
					if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)) {
						
						mapaRetenciones.put(RETENCION_RENTA,carteraDetalleIf.getPreimpreso() != null ? carteraDetalleIf.getPreimpreso(): " ");
						if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(1)) == 0) {											
							mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_1,new BigDecimal("1"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
							mapaRetenciones.put(RETENCION_RENTA_1,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
							
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(2)) == 0) {
							
							mapaRetencionesPorc.put(RETENCION_RENTAPORCENTAJE_2,new BigDecimal("2"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
							mapaRetenciones.put(RETENCION_RENTA_2,((BigDecimal) mapaRetenciones.get(RETENCION_RENTA_2)).add(carteraAfectaIf.getValor()));
							
						}
					} else if (sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)) {											
						if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(30)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_30,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_30)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_30,new BigDecimal("30"));				
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(70)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_70,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_70,new BigDecimal("70"));				
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						} else if (sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(100)) == 0) {
							mapaRetenciones.put(RETENCION_IVA_100,((BigDecimal) mapaRetenciones.get(RETENCION_IVA_100)).add(carteraAfectaIf.getValor()));
							mapaRetencionesPorc.put(RETENCION_IVAPORCENTAJE_100,new BigDecimal("100"));
							mapaRetencionesPorc.put(FECHA_RETENCION,carteraAfectaIf.getFechaAplicacion().toString());
						}
					}
					mapaCarteraRetencionesPorcentaje.put(carteraAfectaIf, mapaRetencionesPorc);
					mapaCarteraRetenciones.put(carteraAfectaIf, mapaRetenciones);
				} 				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarColumnasTablaNC_ND(Vector<String> fila, NotaCreditoIf carteraData,String tipo){	 
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(carteraData.getOperadorNegocioId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());			
		TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(carteraData.getTipoDocumentoId());
		FacturacionIvaRetencionData facNCData = new FacturacionIvaRetencionData();
		fila.add(carteraData.getPreimpreso());
		fila.add(carteraData.getFechaEmision().toString());
		fila.add(cliente.getNombreLegal());
		fila.add("N/C");
		
		facNCData.setAnulada("N/C");
		
		BigDecimal sinIva=carteraData.getValor();
		BigDecimal soloIva=carteraData.getIva();
		BigDecimal conIva=sinIva.add(soloIva);
		
		facNCData.setCliente(cliente.getNombreLegal());
		facNCData.setFechaEmision(carteraData.getFechaEmision().toString());	
		facNCData.setNumeroFactura(carteraData.getPreimpreso());
		
		
		ArrayList detalleNC=null;
		Map fdMap = new HashMap();	
		fdMap.put("notaCreditoId", carteraData.getId());
			
		try{
			
			BigDecimal exterior=new BigDecimal(0);
			BigDecimal reembolso=new BigDecimal(0);
			BigDecimal normal=new BigDecimal(0);
		
			detalleNC = (ArrayList) SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByQuery(fdMap);				
			Iterator ncdetalleIt = detalleNC.iterator();
			while (ncdetalleIt.hasNext()) {				
				NotaCreditoDetalleIf notacreditoDetIf = (NotaCreditoDetalleIf) ncdetalleIt.next();
				DocumentoIf documentoFactura = mapaDocumento.get(notacreditoDetIf.getDocumentoId());
					
				System.out.println("documentoFactura.getCodigo()***********>>>"+documentoFactura.getCodigo());
					
				if(documentoFactura.getCodigo().equals(CODIGO_NC_FACTURA_EXTERIOR)){						
					exterior=exterior.add(notacreditoDetIf.getValor());
						
				}else if(documentoFactura.getCodigo().equals(CODIGO_NC_FACTURA_REEMBOLSO)){					
						
					reembolso=reembolso.add(notacreditoDetIf.getValor());
						
				}else{		
						normal=normal.add(notacreditoDetIf.getValor());						
				} 
			}
				
			fila.add(formatoDecimal.format(exterior));//exterior sinIva
			fila.add(formatoDecimal.format(reembolso));//reembolso conIva
			fila.add(formatoDecimal.format(normal));//normal sinIva
				
			facNCData.setExterior(formatoDecimal.format(exterior));
			facNCData.setReembolso(formatoDecimal.format(reembolso));
			facNCData.setNormal(formatoDecimal.format(normal));
			exteriorNC = exteriorNC.add(exterior);
			reembolsoNC = reembolsoNC.add(reembolso);
			normalNC = normalNC.add(normal);
						
			fila.add(formatoDecimal.format(soloIva));
			fila.add(formatoDecimal.format(conIva));
		
			fila.add("");
			fila.add("");		
			fila.add("");
			fila.add("");			  
			fila.add("");
			facNCData.setIva(soloIva.toString());
			facNCData.setTotal(conIva.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		iva=iva-soloIva.doubleValue();
		totalResumen = totalResumen-conIva.doubleValue();
		facturacionColeccionNC.add(facNCData);
		
		tableModel.addRow(fila);		
	}
	
	public ArrayList generarColeccionFacturasNotasCredito(){	
		ArrayList facturasNC=null;	
		try {
			facturasMap = null;
			facturasMap = new HashMap();
			if (clienteOficinaIf != null) {
				//facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
				facturasMap.put("operadorNegocioId", clienteOficinaIf.getId());
			}
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar()
					.getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime()
					.getTime());
			
			Long tipoDocNotaCreditoProveedor=mapaTipoDocumentoCodigo.get("NCC");
			facturasMap.put("tipoDocumentoId",tipoDocNotaCreditoProveedor);
			facturasMap.put("estado", "A"); //solo se toma en cuenta las notas de credito activas.
			
			
			facturasNC = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoByQueryListFechas(facturasMap, Parametros.getIdEmpresa(),
					new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
			
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return facturasNC;
	}
	
	
	private void agregarColumnasTabla(FacturaIf facturaIf, Map mapaRetenciones,
			Vector<String> fila, FacturacionIvaRetencionData facturacionData,
			FacturacionReporteRetencionesData facturacionReporteRetencionesData,Map mapaRetencionesPorcentaje) {
		
		cleanCantidades();
		
		boolean banderaUNO = true;
		boolean banderaDOS = true;
		
		String filtroRetencionesIVA = ((String) this.getCmbRetencionesIVA().getSelectedItem());
		
		if (filtroRetencionesIVA.equals("Pendientes")) {
			if (!mapaRetenciones.get(RETENCION_IVA).toString().trim().equals("")|| facturaIf.getEstado().equals(ESTADO_ANULADO))
				banderaUNO = false;
		}
		
		if (filtroRetencionesIVA.equals("Recibidas")) {
			if (mapaRetenciones.get(RETENCION_IVA).toString().trim().equals("")|| facturaIf.getEstado().equals(ESTADO_ANULADO))
				banderaUNO = false;
		}
		
		String filtroRetencionesRENTA = ((String) this.getCmbRetencionesRenta()
				.getSelectedItem());
		if (filtroRetencionesRENTA.equals("Pendientes")) {
			if (!mapaRetenciones.get(RETENCION_RENTA).toString().trim().equals("") || facturaIf.getEstado().equals(ESTADO_ANULADO))
				banderaDOS = false;
		}
		
		if (filtroRetencionesRENTA.equals("Recibidas")) {
			if (mapaRetenciones.get(RETENCION_RENTA).toString().trim().equals("") || facturaIf.getEstado().equals(ESTADO_ANULADO))
				banderaDOS = false;
		}
		
		if (banderaUNO && banderaDOS) {
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(facturaIf.getClienteoficinaId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(facturaIf.getTipodocumentoId());
			fila.add(facturaIf.getPreimpresoNumero() != null ? facturaIf.getPreimpresoNumero() : "");
			fila.add(Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura()));
			fila.add(cliente.getNombreLegal());
			facturacionData.setClienteId(cliente.getId().toString());
			//facturacionData.setCliente(cliente.getNombreLegal());
			facturacionData.setCliente(cliente.getIdentificacion());
			facturacionData.setRuc(cliente.getIdentificacion());
			facturacionData.setFechaEmision(Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura()));
			facturacionData.setNumeroFactura(facturaIf.getPreimpresoNumero() != null ? facturaIf.getPreimpresoNumero(): "");
			
			if (facturaIf.getEstado().equals(ESTADO_ANULADO)) {
				fila.add("A");
				facturacionData.setAnulada("A");
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setExterior(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setReembolso(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setNormal(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setIva(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setTotal(formatoDecimal.format(new BigDecimal(0)));
				fila.add(" ");
				facturacionData.setRetencionRenta("");
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setRenta1(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setRenta2(formatoDecimal.format(new BigDecimal(0)));
				fila.add(" ");
				facturacionData.setRetencionIva("");
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setIva30(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setIva70(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setIva100(formatoDecimal.format(new BigDecimal(0)));
				
			} else {
				
				boolean valido=true;//para q en el consolidado no ponga las q son facturas de reembolso ni del exterior
				
				fila.add("");
				facturacionData.setAnulada("");
				
				descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentosVarios()).add(facturaIf.getDescuentoGlobal());
				porcentajeComision = new BigDecimal(0);
				if (facturaIf.getPorcentajeComisionAgencia() != null) {
					porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
				}
				comisionAgencia = (facturaIf.getValor().subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
				subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
				
				if (tipoDocumentoFactura.getCodigo().equals(CODIGO_FACTURA_EXTERIOR)) {
					fila.add(formatoDecimal.format(subTotal));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setExterior(formatoDecimal.format(subTotal));
					facturacionData.setReembolso(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setNormal(formatoDecimal.format(new BigDecimal(0)));
					valido=false;
					
					exterior=exterior+subTotal.doubleValue();
					
					iva=iva+facturaIf.getIva().doubleValue();
					
				} else if (tipoDocumentoFactura.getCodigo().equals(CODIGO_FACTURA_REEMBOLSO)) {
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(subTotal));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setExterior(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setReembolso(formatoDecimal.format(subTotal));
					facturacionData.setNormal(formatoDecimal.format(new BigDecimal(0)));
					valido=false;
					
					reembolso=reembolso+subTotal.add(facturaIf.getIva()).doubleValue();
					
				} else {
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setExterior(formatoDecimal.format(new BigDecimal(0)));
					facturacionData.setReembolso(formatoDecimal.format(new BigDecimal(0)));
					if (tipoDocumentoFactura.getCodigo().equals("DEV")) {
						fila.add(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
						facturacionData.setNormal(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
					} else {
						fila.add(formatoDecimal.format(subTotal));
						facturacionData.setNormal(formatoDecimal.format(subTotal));
					}
					valido=true;
					
					normal=normal+subTotal.doubleValue();
					
					iva=iva+facturaIf.getIva().doubleValue();
				}
				
				totalResumen = totalResumen+subTotal.add(facturaIf.getIva()).doubleValue();
				
				total = subTotal.add(facturaIf.getIva());
				
				if (tipoDocumentoFactura.getCodigo().equals("DEV")) {
					fila.add(formatoDecimal.format(facturaIf.getIva().multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setIva(formatoDecimal.format(facturaIf.getIva().multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(total.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setTotal(formatoDecimal.format(total.multiply(BigDecimal.valueOf(-1D))));
				} else {
					fila.add(formatoDecimal.format(facturaIf.getIva()));
					facturacionData.setIva(formatoDecimal.format(facturaIf.getIva()));
					fila.add(formatoDecimal.format(total));
					facturacionData.setTotal(formatoDecimal.format(total));
				}
				
				if (tipoDocumentoFactura.getCodigo().equals("DEV"))
					total = total.multiply(BigDecimal.valueOf(-1D));
				totalTotal = totalTotal.add(total);
				
				fila.add(mapaRetenciones.get(RETENCION_RENTA).toString());
				fila.add(formatoDecimal.format(mapaRetenciones.get(RETENCION_RENTA_1)));
				fila.add(formatoDecimal.format(mapaRetenciones.get(RETENCION_RENTA_2)));
				
				fila.add(mapaRetenciones.get(RETENCION_IVA).toString());
				fila.add(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_30)));
				fila.add(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_70)));
				fila.add(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_100)));
				
				facturacionData.setRetencionRenta(mapaRetenciones.get(RETENCION_RENTA).toString());
				facturacionData.setRenta1(formatoDecimal.format(mapaRetenciones.get(RETENCION_RENTA_1)));
				facturacionData.setRenta2(formatoDecimal.format(mapaRetenciones.get(RETENCION_RENTA_2)));
				facturacionData.setRetencionIva(mapaRetenciones.get(RETENCION_IVA).toString());
				facturacionData.setIva30(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_30)));
				facturacionData.setIva70(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_70)));
				facturacionData.setIva100(formatoDecimal.format(mapaRetenciones.get(RETENCION_IVA_100)));
				
				if(valido)
				{
					facturacionReporteRetencionesData.setClienteId(cliente.getId().toString());
					facturacionReporteRetencionesData.setNumeroFactura(facturaIf.getPreimpresoNumero() != null ? facturaIf.getPreimpresoNumero(): "");
					facturacionReporteRetencionesData.setFechaEmision(Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura()));
					facturacionReporteRetencionesData.setCliente(cliente.getNombreLegal());
					//////
					if(mapaRetencionesPorcentaje!=null)
					{	
						if(mapaRetencionesPorcentaje.get(FECHA_RETENCION)!=null)
						{	
							String fechaRetencion=mapaRetencionesPorcentaje.get(FECHA_RETENCION).toString();
							if (!fechaRetencion.equals(""))	fechaRetencion = fechaRetencion.substring(8,10) + "-" + Utilitarios.getNombreMes(Integer.parseInt(fechaRetencion.substring(5,7))).substring(0,3) + "-" + fechaRetencion.substring(0,4);							
							facturacionReporteRetencionesData.setFechaEmisionRetencion(fechaRetencion);						
						}
						
						if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_70)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_70).toString().equals("70"))
						{
							facturacionReporteRetencionesData.setTipoRetencion("I");
							facturacionReporteRetencionesData.setCodigoRetencionIva("I(70%)");
							facturacionReporteRetencionesData.setValorIva(facturacionData.getIva70());
							if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
						}
						
						if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_100)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_100).toString().equals("100"))
						{
							facturacionReporteRetencionesData.setTipoRetencion("I");
							facturacionReporteRetencionesData.setCodigoRetencionIva("I(100%)");
							facturacionReporteRetencionesData.setValorIva(facturacionData.getIva100());
							if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
						}
						
						if (mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_30)!=null && mapaRetencionesPorcentaje.get(RETENCION_IVAPORCENTAJE_30).toString().equals("30"))
						{
							facturacionReporteRetencionesData.setTipoRetencion("I");
							facturacionReporteRetencionesData.setCodigoRetencionIva("I(30%)");
							facturacionReporteRetencionesData.setValorIva(facturacionData.getIva30());
							if(facturacionReporteRetencionesData.getValorIva().equals("0")) facturacionReporteRetencionesData.setValorIva("0.00");
						}
						
						if (mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_1)!=null && mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_1).toString().equals("1"))
						{
							facturacionReporteRetencionesData.setTipoRetencion("R");
							facturacionReporteRetencionesData.setCodigoRetencionRenta("R(1%)");															
							facturacionReporteRetencionesData.setValorRenta(facturacionData.getRenta1());
							if(facturacionReporteRetencionesData.getValorRenta().equals("0")) facturacionReporteRetencionesData.setValorRenta("0.00");
						}
						
						if (mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_2)!=null && mapaRetencionesPorcentaje.get(RETENCION_RENTAPORCENTAJE_2).toString().equals("2"))
						{
							facturacionReporteRetencionesData.setTipoRetencion("R");
							facturacionReporteRetencionesData.setCodigoRetencionRenta("R(2%)");
							facturacionReporteRetencionesData.setValorRenta(facturacionData.getRenta2());
							if(facturacionReporteRetencionesData.getValorRenta().equals("0")) facturacionReporteRetencionesData.setValorRenta("0.00");
						}
						
						if(facturacionReporteRetencionesData.getValorIva()==null)	facturacionReporteRetencionesData.setValorIva("0");
						if(facturacionReporteRetencionesData.getValorRenta()==null)	facturacionReporteRetencionesData.setValorRenta("0");					
						if(facturacionReporteRetencionesData.getValorIva()!=null && facturacionReporteRetencionesData.getValorIva().toString().equals(""))		facturacionReporteRetencionesData.setValorIva("0.00");
						if(facturacionReporteRetencionesData.getValorRenta()!=null && facturacionReporteRetencionesData.getValorRenta().toString().equals(""))	facturacionReporteRetencionesData.setValorRenta("0.00");
						
						facturacionConsolidadoColeccion.add(facturacionReporteRetencionesData);
						
					}
				}// vero
			}
			tableModel.addRow(fila);
			facturacionColeccion.add(facturacionData);
		}
	}
	
	public void cleanCantidades() {
		descuentoTotal = new BigDecimal(0);
		porcentajeComision = new BigDecimal(0);
		comisionAgencia = new BigDecimal(0);
		subTotal = new BigDecimal(0);
		total = new BigDecimal(0);
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
}







