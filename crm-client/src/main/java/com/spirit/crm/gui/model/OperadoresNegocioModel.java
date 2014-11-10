package com.spirit.crm.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteContactoData;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteIndicadorData;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionData;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaData;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoContactoIf;
import com.spirit.crm.entity.TipoIndicadorIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.gui.criteria.AdministracionClienteCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.crm.gui.panel.JPOperadoresNegocio;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.CiudadCriteria;
import com.spirit.general.util.DateHelperClient;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.LabelAccessory;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;
import com.spirit.util.ValidarIdentificacion;

public class OperadoresNegocioModel  extends JPOperadoresNegocio {
	private static final long serialVersionUID = 3621375112684612467L;
	
	ArrayList lista;
	final JPopupMenu  popupMenuClienteZona = new JPopupMenu();
	final JPopupMenu  popupMenuClienteOficina = new JPopupMenu();
	final JPopupMenu  popupMenuClienteOficinaContacto = new JPopupMenu();
	final JPopupMenu  popupMenuClienteOficinaIndicador = new JPopupMenu();
	
	private ClienteIf cliente;
	private ClienteOficinaIf clienteOficinaIf;
	private TipoClienteIf tipoClienteIf; 
	private CorporacionIf corporacionIf;	
	private TipoIdentificacionIf tipoIdentificacionIf;
	private CiudadIf ciudadIf;
	private Long empresaId = null;
	
	private static final int MAX_LONGITUD_CEDULA = 10;
	private static final int MAX_LONGITUD_RUC = 13;
	private static final int MAX_LONGITUD_OTROS = 15;
	private static final int MAX_LONGITUD_NOMBRE_LEGAL = 80;
	private static final int MAX_LONGITUD_RAZON_SOCIAL = 80;
	private static final int MAX_LONGITUD_REPRESENTANTE = 40;
	private static final int MAX_LONGITUD_OBSERVACION = 50;
	private static final int MAX_LONGITUD_INFORMACIONA_ADC = 200;
	private static final int MAX_LONGITUD_CODIGO_ZONA = 3;
	private static final int MAX_LONGITUD_NOMBRE_ZONA = 30;
	private static final int MAX_LONGITUD_CODIGO_OFICINA = 10;
	private static final int MAX_LONGITUD_DESCRIPCION_OFICINA = 50;
	private static final int MAX_LONGITUD_OBSERVACION_OFICINA = 40;
	private static final int MAX_LONGITUD_DIRECCION_OFICINA = 150;
	private static final int MAX_LONGITUD_TELEFONO_OFICINA = 10;
	private static final int MAX_LONGITUD_FAX_OFICINA = 10;
	private static final int MAX_LONGITUD_EMAIL_OFICINA = 50;
	private static final int MAX_LONGITUD_NOMBRE_CONTACTO = 40;
	private static final int MAX_LONGITUD_DIRECCION_CONTACTO = 150;
	private static final int MAX_LONGITUD_TELEFONO_OFICINA_CONTACTO = 10;
	private static final int MAX_LONGITUD_TELEFONO_CASA_CONTACTO = 10;
	private static final int MAX_LONGITUD_CELULAR_CONTACTO = 10;
	private static final int MAX_LONGITUD_EMAIL_CONTACTO = 50;
	private static final int MAX_LONGITUD_MONTO_CREDITO_CLIENTE_OFICINA = 22;
	private static final int MAX_LONGITUD_MONTO_GARANTIA_CLIENTE_OFICINA = 22;
	private static final int MAX_LONGITUD_VALOR_INDICADOR_CLIENTE_OFICINA = 12;
	private static final String NOMBRE_OPCION_SI = "SI";
	private static final String OPCION_SI = NOMBRE_OPCION_SI.substring(0,1);
	private static final String NOMBRE_OPCION_NO = "NO";
	private static final String OPCION_NO = NOMBRE_OPCION_NO.substring(0,1);
	private static final String NOMBRE_TIPO_PERSONA_NATURAL = "NATURAL";
	private static final String TIPO_PERSONA_NATURAL = NOMBRE_TIPO_PERSONA_NATURAL.substring(0,1);
	private static final String NOMBRE_TIPO_PERSONA_JURIDICA = "JURIDICA";
	private static final String TIPO_PERSONA_JURIDICA = NOMBRE_TIPO_PERSONA_JURIDICA.substring(0,1);
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_ESTADO_RIESGO = "RIESGO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private static final String ESTADO_RIESGO = NOMBRE_ESTADO_RIESGO.substring(0,1);
	private static final String TIPOCUENTA_AHORROS = "A - AHORROS";
	private static final String TIPOCUENTA_CORRIENTE = "C - CORRIENTE";
	
	
	Timestamp fechaCreacionCliente = DateHelperClient.getTimeStampNow();
	Timestamp fechaCreacionClienteOficina = DateHelperClient.getTimeStampNow();
	Timestamp fechaCumpleanosContacto = DateHelperClient.getTimeStampNow();
	
	Vector<ClienteZonaIf> detalleZonaClienteColeccion = new Vector<ClienteZonaIf>();
	Vector<ClienteRetencionIf> detalleRetencionClienteColeccion = new Vector<ClienteRetencionIf>();
	Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
	Map detalleContactoOficinaClienteMap = new HashMap();
	Map detalleIndicadorOficinaClienteMap = new HashMap();
	Map<String,Vector<ClienteContactoIf>> detalleContactoRemovidoClienteColeccion = new HashMap<String,Vector<ClienteContactoIf>>();
	Map<String,Vector<ClienteIndicadorIf>> detalleIndicadorRemovidoClienteColeccion = new HashMap<String,Vector<ClienteIndicadorIf>>();
	
	DefaultTableModel modelClienteZona, modelClienteRetencion, modelClienteOficina, modelClienteOficinaContacto, modelClienteOficinaIndicador;	
	Vector<ClienteOficinaIf> clienteOficinaVector = new Vector<ClienteOficinaIf>();
	Vector<ClienteZonaIf> detalleZonaRemovidaClienteColeccion = new Vector<ClienteZonaIf>();
	Vector<ClienteRetencionIf> detalleRetencionRemovidaClienteColeccion = new Vector<ClienteRetencionIf>();
	Vector<ClienteOficinaIf> detalleOficinaRemovidaClienteColeccion = new Vector<ClienteOficinaIf>();
	//Vector<ClienteContactoIf> detalleContactoRemovidoClienteColeccion = new Vector<ClienteContactoIf>();
	//Vector<ClienteIndicadorIf> detalleIndicadorRemovidoClienteColeccion = new Vector<ClienteIndicadorIf>();
	
	private int clienteOficinaFilaSeleccionada = -1;
	
	public OperadoresNegocioModel() {
		initKeyListeners();
		iniciarPopups();
		cargarCombos();
		showSaveMode();
		anchoColumnasTabla();
		initListeners();
		if (getCmbTipoIdentificacionCliente().getItemCount()>0)
			getCmbTipoIdentificacionCliente().setSelectedIndex(0);
		
		getTblDetalleZona().addMouseListener(oMouseAdapterTblDetalleZona);
		getTblDetalleZona().addKeyListener(oKeyAdapterTblDetalleZona);
		getTblDetalleZona().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRetenciones().addMouseListener(oMouseAdapterTblDetalleRetencion);
		getTblRetenciones().addKeyListener(oKeyAdapterTblDetalleRetencion);
		getTblRetenciones().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblOficinasCliente().addMouseListener(oMouseAdapterTblOficinasCliente);
		getTblOficinasCliente().addKeyListener(oKeyAdapterTblOficinasCliente);
		getTblOficinasCliente().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDetalleContacto().addMouseListener(oMouseAdapterTblDetalleContacto);
		getTblDetalleContacto().addKeyListener(oKeyAdapterTblDetalleContacto);
		getTblDetalleContacto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDetalleIndicador().addMouseListener(oMouseAdapterTblDetalleIndicador);
		getTblDetalleIndicador().addKeyListener(oKeyAdapterTblDetalleIndicador);
		getTblDetalleIndicador().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		new HotKeyComponent(this);
	}
	
	public void cargarCombos() {
		cargarComboBanco();
		getCmbTipoCuenta().addItem(TIPOCUENTA_CORRIENTE);
		getCmbTipoCuenta().addItem(TIPOCUENTA_AHORROS);
		getCmbTipoCuenta().setSelectedIndex(-1);
	}
	
	private void cargarComboBanco(){
		try {
			List bancos = (List) SessionServiceLocator.getBancoSessionService().getBancoList();
			Collections.sort((ArrayList<BancoIf>)bancos,ordenadorBancoPorNombre);
			refreshCombo(getCmbBanco(), bancos);
			getCmbBanco().setSelectedIndex(-1);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	Comparator<BancoIf> ordenadorBancoPorNombre = new Comparator<BancoIf>(){
		public int compare(BancoIf o1, BancoIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void initKeyListeners() {
		/*getLblBanco().setVisible(false);
		getCmbBanco().setVisible(false);
		getLblTipoCuenta().setVisible(false);
		getCmbTipoCuenta().setVisible(false);
		getLblNumeroCuenta().setVisible(false);
		getTxtNumeroCuenta().setVisible(false);*/
		
		getBtnProgramador().setVisible(false);
		
		getTxtCodigoZona().setEditable(false);
		getTxtNombreLegalCliente().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_LEGAL,1));
		getTxtRazonSocialCliente().addKeyListener(new TextChecker(MAX_LONGITUD_RAZON_SOCIAL,1));
		getTxtRepresentanteCliente().addKeyListener(new TextChecker(MAX_LONGITUD_REPRESENTANTE,1));
		getTxtObservacionesCliente().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtinformacionAdc().addKeyListener(new TextChecker(MAX_LONGITUD_INFORMACIONA_ADC));
		getTxtCodigoZona().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_ZONA));
		getTxtNombreZona().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_ZONA));
		getTxtCodigoClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_OFICINA));
		getTxtDescripcionClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_OFICINA));
		getTxtObservacionClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION_OFICINA));
		getTxtDireccionClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION_OFICINA));
		getTxtTelefonoClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO_OFICINA));
		getTxtTelefonoClienteOficina().addKeyListener (new NumberTextField());
		getTxtFaxClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_FAX_OFICINA));
		getTxtFaxClienteOficina().addKeyListener (new NumberTextField());
		getTxtEmailClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_EMAIL_OFICINA, 2));
		getTxtNombreContacto().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_CONTACTO,0));
		getTxtDireccionContacto().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION_CONTACTO));
		getTxtTelefonoOficContacto().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO_OFICINA_CONTACTO));
		getTxtTelefonoOficContacto().addKeyListener (new NumberTextField());
		getTxtTelefonoCasaContacto().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO_CASA_CONTACTO));
		getTxtTelefonoCasaContacto().addKeyListener (new NumberTextField());
		getTxtCelularContacto().addKeyListener(new TextChecker(MAX_LONGITUD_CELULAR_CONTACTO));
		getTxtCelularContacto().addKeyListener (new NumberTextField());
		getTxtMailContacto().addKeyListener(new TextChecker(MAX_LONGITUD_EMAIL_CONTACTO, 2));
		getTxtMontoCreditoClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_MONTO_CREDITO_CLIENTE_OFICINA));
		getTxtMontoCreditoClienteOficina().addKeyListener (new NumberTextFieldDecimal());
		getTxtMontoGarantiaClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_MONTO_GARANTIA_CLIENTE_OFICINA));
		getTxtMontoGarantiaClienteOficina().addKeyListener (new NumberTextFieldDecimal());
		getTxtValorIndicador().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR_INDICADOR_CLIENTE_OFICINA));
		getTxtValorIndicador().addKeyListener (new NumberTextField());
		getCmbCumpleanosContacto().setEditable(false);
		getCmbCumpleanosContacto().setShowNoneButton(false);
		getTxtCorporacionCliente().setEnabled(true);
		getTxtFechaCreacionCliente().setEnabled(true);
		getTxtCorporacionCliente().setEditable(false);
		getTxtFechaCreacionCliente().setEditable(false);
		getTxtFechaCreacionClienteOficina().setEnabled(true);
		getTxtFechaCreacionClienteOficina().setEditable(false);
		getTxtCiudadClienteOficina().setEnabled(true);
		getTxtCiudadClienteOficina().setEditable(false);
		
		referenciaImagenes();
		
	}
	
	
	public void referenciaImagenes(){
		
		getBtnAgregarDetalleZona().setText("");
		getBtnActualizarDetalleZona().setText("");
		getBtnEliminarDetalleZona().setText("");		
		getBtnAgregarClienteOficina().setText("");
		getBtnActualizarClienteOficina().setText("");
		getBtnEliminarClienteOficina().setText("");
		getBtnAgregarDetalleContacto().setText("");
		getBtnActualizarDetalleContacto().setText("");
		getBtnEliminarDetalleContacto().setText("");
		getBtnAgregarDetalleIndicador().setText("");
		getBtnActualizarDetalleIndicador().setText("");
		getBtnEliminarDetalleIndicador().setText("");
		getBtnAgregarRetencion().setText("");
		getBtnActualizarRetencion().setText("");
		getBtnEliminarRetencion().setText("");
		
		//---- btnBuscarCorporacionCliente ----
		getBtnBuscarCorporacionCliente().setToolTipText("Buscar Corporaci\u00f3n");
		getBtnBuscarCorporacionCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));		
		//---- btnAgregarDetalleZona ----
		getBtnAgregarDetalleZona().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalleZona().setToolTipText("Agregar Detalle Zona");		
		//---- btnActualizarDetalleZona ----
		getBtnActualizarDetalleZona().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalleZona().setToolTipText("Actualizar Detalle Zona");		
		//---- btnEliminarDetalleZona ----
		getBtnEliminarDetalleZona().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalleZona().setToolTipText("Eliminar Detalle Zona");		
		//---- btnAgregarClienteOficina ----
		getBtnAgregarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarClienteOficina().setToolTipText("Agregar Detalle Oficina");		
		//---- btnActualizarClienteOficina ----
		getBtnActualizarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarClienteOficina().setToolTipText("Actualizar Detalle Oficina");		
		//---- btnEliminarClienteOficina ----
		getBtnEliminarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarClienteOficina().setToolTipText("Eliminar Detalle Oficina");		
		//---- btnAgregarDetalleContacto ----
		getBtnAgregarDetalleContacto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalleContacto().setToolTipText("Agregar Detalle Contacto");		
		//---- btnActualizarDetalleContacto ----
		getBtnActualizarDetalleContacto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalleContacto().setToolTipText("Actualizar Detalle Contacto");		
		//---- btnEliminarDetalleContacto ----
		getBtnEliminarDetalleContacto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalleContacto().setToolTipText("Eliminar Detalle Contacto");		
		//---- btnAgregarDetalleIndicador ----
		getBtnAgregarDetalleIndicador().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalleIndicador().setToolTipText("Agregar Detalle Indicador");		
		//---- btnActualizarDetalleIndicador ----
		getBtnActualizarDetalleIndicador().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalleIndicador().setToolTipText("Actualizar Detalle Indicador");		
		//---- btnEliminarDetalleIndicador ----
		getBtnEliminarDetalleIndicador().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalleIndicador().setToolTipText("Eliminar Detalle Indicador");		
		//---- btnBuscarCiudad ----
		getBtnCiudadesClienteOficina().setToolTipText("Buscar Ciudad");
		getBtnCiudadesClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnAgregarRetencion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarRetencion().setToolTipText("Agregar Retención");		
		getBtnActualizarRetencion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarRetencion().setToolTipText("Actualizar Retención");		
		getBtnEliminarRetencion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarRetencion().setToolTipText("Eliminar Retención");		
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaZona = getTblDetalleZona().getColumnModel().getColumn(0);
		anchoColumnaZona.setPreferredWidth(70);
		anchoColumnaZona = getTblDetalleZona().getColumnModel().getColumn(1);
		anchoColumnaZona.setPreferredWidth(350);
		
		TableColumn anchoColumnaRetencion = getTblRetenciones().getColumnModel().getColumn(0);
		anchoColumnaRetencion.setPreferredWidth(300);
		anchoColumnaRetencion = getTblRetenciones().getColumnModel().getColumn(1);
		anchoColumnaRetencion.setPreferredWidth(100);
		
		TableColumn anchoColumnaOficina = getTblOficinasCliente().getColumnModel().getColumn(0);
		anchoColumnaOficina.setPreferredWidth(60);
		anchoColumnaOficina = getTblOficinasCliente().getColumnModel().getColumn(1);
		anchoColumnaOficina.setPreferredWidth(100);
		anchoColumnaOficina = getTblOficinasCliente().getColumnModel().getColumn(2);
		anchoColumnaOficina.setPreferredWidth(50);
		anchoColumnaOficina = getTblOficinasCliente().getColumnModel().getColumn(3);
		anchoColumnaOficina.setPreferredWidth(200);
		anchoColumnaOficina = getTblOficinasCliente().getColumnModel().getColumn(4);
		anchoColumnaOficina.setPreferredWidth(140);
	}
	
	private ArrayList procesarArchivoExcel(InputStream input,String nombreHoja) 
	throws IOException, GenericBusinessException,OfficeXmlFileException,Exception {
		
		ArrayList detalles = new ArrayList();
		
		int columnaTipoMedio = 0;
		int columnaIdentificacion = 1;
		int columnaNombreLegal = 2;
		int columnaRazonSocial = 3;
		int columnaDescripcion = 4; 
		int columnaCodRetRenta = 5; 
		int columnaCodRetIva = 6;
				
		String filaTotalPauta = "FIN RETENCIONES";
			
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(nombreHoja);
		
		if (sheet!=null){		
			
			RecorrerFilas:
			for(int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				Vector detalle = new Vector();
				
				HSSFRow row = sheet.getRow(i);
				
				HSSFCell celdaTipoMedio = row.getCell(columnaTipoMedio);
				String infoCeldaTipoMedio = "";			
				if(celdaTipoMedio!= null && celdaTipoMedio.getCellType() != HSSFCell.CELL_TYPE_BLANK ) {
					HSSFRichTextString tipoMedioTexto = celdaTipoMedio.getRichStringCellValue();
					infoCeldaTipoMedio = tipoMedioTexto.getString().trim();
				}
				
				//si no es igual a "FIN RETENCIONES"
				if (infoCeldaTipoMedio.compareTo(filaTotalPauta) != 0){
					
					HSSFCell celdaIdentificacion = row.getCell(columnaIdentificacion);
					String infoCeldaIdentificacion = "";
					if(celdaIdentificacion!= null && celdaIdentificacion.getCellType() != HSSFCell.CELL_TYPE_BLANK ) {
						System.out.println("ruc: " + celdaIdentificacion);
						HSSFRichTextString identificacionTexto = celdaIdentificacion.getRichStringCellValue();
						infoCeldaIdentificacion = identificacionTexto.getString().trim();
					}
					detalle.add(infoCeldaIdentificacion);
					
					HSSFCell celdaRetencionRenta = row.getCell(columnaCodRetRenta);
					String infoCeldaRetencionRenta = "";
					if(celdaRetencionRenta!= null && celdaRetencionRenta.getCellType() != HSSFCell.CELL_TYPE_BLANK ) {
						HSSFRichTextString retencionRenta = celdaRetencionRenta.getRichStringCellValue();
						infoCeldaRetencionRenta = retencionRenta.getString().trim();
					}
					detalle.add(infoCeldaRetencionRenta);
					
					HSSFCell celdaRetencionIva = row.getCell(columnaCodRetIva);
					String infoCeldaRetencionIva = "";
					if(celdaRetencionIva!= null && celdaRetencionIva.getCellType() != HSSFCell.CELL_TYPE_BLANK ) {
						HSSFRichTextString retencionIva = celdaRetencionIva.getRichStringCellValue();
						infoCeldaRetencionIva = retencionIva.getString().trim();
					}
					detalle.add(infoCeldaRetencionIva);
					
					/*HSSFCell celdaRetencionRenta = row.getCell(columnaCodRetRenta);
					Double retencionRenta = 0D;
					if(celdaRetencionRenta!= null 
							&& (celdaRetencionRenta.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
							|| celdaRetencionRenta.getCellType() == HSSFCell.CELL_TYPE_FORMULA)) {
						retencionRenta = celdaRetencionRenta.getNumericCellValue();
					}
					detalle.add(retencionRenta);
					
					HSSFCell celdaRetencionIVA = row.getCell(columnaCodRetIva);
					Double retencionIvaTexto = 0D;
					if(celdaRetencionIVA!= null 
							&& (celdaRetencionIVA.getCellType() == HSSFCell.CELL_TYPE_NUMERIC 
							|| celdaRetencionIVA.getCellType() == HSSFCell.CELL_TYPE_FORMULA)) {
						retencionIvaTexto = celdaRetencionIVA.getNumericCellValue();
					}
					detalle.add(retencionIvaTexto);*/
					
					detalles.add(detalle);
				}
				
				if (infoCeldaTipoMedio.compareTo(filaTotalPauta) == 0){
					i = sheet.getLastRowNum();
					continue RecorrerFilas;
				}					
			}
		} else {
			System.out.println("No existe hoja con nombre: "+nombreHoja);
			detalles = null;
		}
		return detalles;
	}
	
	public void convertirInformacionDesdeExcel()
			throws GenericBusinessException, OfficeXmlFileException, Exception{
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAccessory(new LabelAccessory(fileChooser));

		FileFilter filterExcel = new ExtensionFileFilter(null, new String[] {"xls,xlsx", "xls,xlsx" });
		fileChooser.addChoosableFileFilter(filterExcel);
		fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
		int status = fileChooser.showOpenDialog(Parametros.getMainFrame());
		
		if (status == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			InputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				
				Collection retenciones = procesarArchivoExcel(fis, "Retenciones");
				Iterator retencionesIt = retenciones.iterator();
				while(retencionesIt.hasNext()){
					Vector detalle = (Vector)retencionesIt.next();
					String ruc = (String)detalle.get(0);
					String retRenta = (String)detalle.get(1);
					String retIva = (String)detalle.get(2);
					
					if(!retRenta.equals("000")){
						Collection proveedores = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(ruc);
						if(proveedores.size() == 1){
							Collection retencionesProveedor = agregarClienteRetencion(retRenta, retIva, proveedores);
						}else if(proveedores.size() == 0){
							ruc = ruc + "001";
							Collection proveedores2 = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(ruc);
							if(proveedores.size() == 1){
								Collection retencionesProveedor = agregarClienteRetencion(retRenta, retIva, proveedores);
							}else if(proveedores.size() == 0){
								System.out.println("no existe: " + ruc);
							}else{
								System.out.println("repetido2: " + ((ClienteIf)proveedores.iterator().next()).getNombreLegal());
							}
						}else{
							System.out.println("repetido: " + ((ClienteIf)proveedores.iterator().next()).getNombreLegal());
						}
					}					
				}
				
						
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				fis.close();
				throw new GenericBusinessException(e.getMessage());
			} 
			
			try {
				if ( fis != null ){
					fis.close();
					System.out.println("-ARCHIVO CERRADO-");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	private Collection agregarClienteRetencion(String retRenta, String retIva,
			Collection proveedores) throws GenericBusinessException {
		ClienteIf proveedor = (ClienteIf)proveedores.iterator().next();
		Collection retencionesProveedor = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
		//solo si no tiene retenciones le agrego
		if(retencionesProveedor.size() == 0){
			ClienteRetencionData proveedorRetencion = new ClienteRetencionData();
			proveedorRetencion.setClienteId(proveedor.getId());
			
			Collection retencionesRenta = SessionServiceLocator.getSriAirSessionService().findSriAirByCodigo(retRenta);
			Iterator retencionesRentaIt = retencionesRenta.iterator();
			while(retencionesRentaIt.hasNext()){
				SriAirIf retRentaIf = (SriAirIf)retencionesRentaIt.next();
				proveedorRetencion.setSriAirId(retRentaIf.getId());
			}
			
			Collection retencionesIva = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByCodigo(retIva);
			if(retencionesIva.size() > 0){
				Iterator retencionesIvaIt = retencionesIva.iterator();
				while(retencionesIvaIt.hasNext()){
					SriIvaRetencionIf sriIvaRetencionIf = (SriIvaRetencionIf)retencionesIvaIt.next();
					proveedorRetencion.setSriIvaRetencionId(sriIvaRetencionIf.getId());
				}
			}else{
				Collection retencionesIvaNoAplica = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByCodigo("NONE");
				Iterator retencionesIvaNoAplicaIt = retencionesIvaNoAplica.iterator();
				while(retencionesIvaNoAplicaIt.hasNext()){
					SriIvaRetencionIf sriIvaRetencionIf = (SriIvaRetencionIf)retencionesIvaNoAplicaIt.next();
					proveedorRetencion.setSriIvaRetencionId(sriIvaRetencionIf.getId());
				}
			}			
			
			SessionServiceLocator.getClienteRetencionSessionService().addClienteRetencion(proveedorRetencion);
		}
		return retencionesProveedor;
	}
	
	public void initListeners() {
		
		getBtnProgramador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					convertirInformacionDesdeExcel();
					
				} catch (OfficeXmlFileException e) {
					e.printStackTrace();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			SpiritComboBoxModel cmbModelTipoIdentificacionCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList());
			getCmbTipoIdentificacionCliente().setModel(cmbModelTipoIdentificacionCliente);
			SpiritComboBoxModel cmbModelTipoCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByEmpresaId(Parametros.getIdEmpresa()));
			getCmbTipoCliente().setModel(cmbModelTipoCliente);
			
			SpiritComboBoxModel cmbModelTipoNegocioCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoNegocioSessionService().findTipoNegocioByEmpresaId(Parametros.getIdEmpresa()));
			getCmbTipoNegocioCliente().setModel(cmbModelTipoNegocioCliente);
			
			if (getCmbTipoCliente().getSelectedItem() != null)
				tipoClienteIf = (TipoClienteIf) getCmbTipoCliente().getModel().getElementAt(0);
			
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
		}
		
		getCmbTipoIdentificacionCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoIdentificacionIf = (TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem();
				for (int i=0; i<getTxtIdentificacionCliente().getKeyListeners().length; i++) {
					KeyListener keyListener = getTxtIdentificacionCliente().getKeyListeners()[i];
					getTxtIdentificacionCliente().removeKeyListener(keyListener);
				 }	
				
				if (tipoIdentificacionIf != null) {
					if (tipoIdentificacionIf.getCodigo().equals("CE")) {
						if (getTxtIdentificacionCliente().getText().length() > MAX_LONGITUD_CEDULA)
							getTxtIdentificacionCliente().setText("");
						getTxtIdentificacionCliente().addKeyListener(new TextChecker(MAX_LONGITUD_CEDULA));
					} else if (tipoIdentificacionIf.getCodigo().equals("RU") || tipoIdentificacionIf.getCodigo().equals("PA")) {
						if (getTxtIdentificacionCliente().getText().length() > MAX_LONGITUD_RUC)
							getTxtIdentificacionCliente().setText("");
						getTxtIdentificacionCliente().addKeyListener(new TextChecker(MAX_LONGITUD_RUC));
					} else {
						if (getTxtIdentificacionCliente().getText().length() > MAX_LONGITUD_OTROS)
							getTxtIdentificacionCliente().setText("");
						getTxtIdentificacionCliente().addKeyListener(new TextChecker(MAX_LONGITUD_OTROS));
					}
					getTxtIdentificacionCliente().setEnabled(true);
					getTxtIdentificacionCliente().grabFocus();
				}
			}
		});
		
		getCmbTipoCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoClienteIf = (TipoClienteIf) getCmbTipoCliente().getSelectedItem();
				if(tipoClienteIf != null){
					empresaId = tipoClienteIf.getEmpresaId(); 
					if (tipoClienteIf.getCodigo().equals("PR") || tipoClienteIf.getCodigo().equals("AM")) {
						getCmbTipoProveedor().removeAllItems();
						try {
							SpiritComboBoxModel cmbModelTipoProveedor = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa()));
							getCmbTipoProveedor().setModel(cmbModelTipoProveedor);
							getCmbTipoProveedor().setEnabled(true);
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} else if (tipoClienteIf.getCodigo().equals("CL")) {
						getCmbTipoProveedor().setEnabled(false);
					}
					getCmbTipoProveedor().setSelectedIndex(-1);
					getCmbTipoProveedor().repaint();
					
					if(tipoClienteIf.getCodigo().equals("CL") || tipoClienteIf.getCodigo().equals("AM")){
						getCmbRequiereSap().setEnabled(true);
					}else{
						getCmbRequiereSap().setSelectedItem(NOMBRE_OPCION_NO);
						getCmbRequiereSap().setEnabled(false);
					}
				}
			}
		});
		
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacionCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				CorporacionCriteria corporacionCriteria = new CorporacionCriteria("Corporaciones",Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacionCliente().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				}
			}
		});
		
		// Mando a cargar el popup de las pantallas mantenedoras de Ciudad
		getBtnCiudadesClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				CiudadCriteria ciudadCriteria = new CiudadCriteria("Ciudad",0L);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), ciudadCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					ciudadIf = (CiudadIf) popupFinder.getElementoSeleccionado();
					getTxtCiudadClienteOficina().setText(ciudadIf.getCodigo() + " - " + ciudadIf.getNombre());
				}
			}
		});
		
		getBtnAgregarDetalleZona().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleClienteZona();				
			}
		});
		
		getBtnActualizarDetalleZona().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalleClienteZona();			
			}
		});
		
		getBtnEliminarDetalleZona().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteZona();			
			}
		});
		
		getBtnAgregarRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleClienteRetencion();				
			}
		});
		
		getBtnActualizarRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalleClienteRetencion();			
			}
		});
		
		getBtnEliminarRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteRetencion();			
			}
		});
		
		getBtnAgregarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleClienteOficinaGeneral();				
			}
		});
		
		getBtnActualizarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalleClienteOficinaGeneral();				
			}
		});
		
		getBtnEliminarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaGeneral();				
			}
		});	
		
		getBtnAgregarDetalleContacto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleClienteOficinaContacto();
			}
		});
		
		getBtnActualizarDetalleContacto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalleClienteOficinaContacto();
			}
		});
		
		getBtnEliminarDetalleContacto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaContacto();
			}
		});
		
		getBtnAgregarDetalleIndicador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleClienteOficinaIndicador();
			}
		});
		
		getBtnActualizarDetalleIndicador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalleClienteOficinaIndicador();
			}
		});
		
		getBtnEliminarDetalleIndicador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaIndicador();
			}
		});	
		
		getCmbCumpleanosContacto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Timestamp fechaHoy = DateHelperClient.getTimeStampNow();
				
				fechaCumpleanosContacto = DateHelperClient.getTimeStamp(((DateComboBox) evento.getSource()).getDate());
				
				if(fechaCumpleanosContacto.after(fechaHoy)){
					SpiritAlert.createAlert("La Fecha de Nacimiento no puede estar después de la Fecha Actual!", SpiritAlert.INFORMATION);
					fechaCumpleanosContacto = fechaHoy;
					getCmbCumpleanosContacto().setCalendar(Calendar.getInstance());
				}
			}
		});
	}
	
	private void iniciarPopups(){
		//Opción que permite borrar un regitsro deseado de la tabla de Cliente Zona
		JMenuItem itemEliminarClienteZona = new JMenuItem("Eliminar");
		popupMenuClienteZona.add(itemEliminarClienteZona);
		// Añado el listener de menupopup
		itemEliminarClienteZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteZona();
			}
		});
		
		//Opción que permite borrar un registro deseado de la tabla de Cliente Oficina
		JMenuItem itemEliminarClienteOficina = new JMenuItem("Eliminar");
		popupMenuClienteOficina.add(itemEliminarClienteOficina);
		// Añado el listener de menupopup
		itemEliminarClienteOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaGeneral();
			}
		});
		
		//Opción que permite borrar un registro deseado de la tabla de Cliente Oficina Contacto
		JMenuItem itemEliminarClienteOficinaContacto = new JMenuItem("Eliminar");
		popupMenuClienteOficinaContacto.add(itemEliminarClienteOficinaContacto);
		// Añado el listener de menupopup
		itemEliminarClienteOficinaContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaContacto();
			}
		});
		
		//Opción que permite borrar un registro deseado de la tabla de Cliente Oficina Indicador
		JMenuItem itemEliminarClienteOficinaIndicador = new JMenuItem("Eliminar");
		popupMenuClienteOficinaIndicador.add(itemEliminarClienteOficinaIndicador);
		// Añado el listener de menupopup
		itemEliminarClienteOficinaIndicador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleClienteOficinaIndicador();
			}
		});
	}
	
	MouseListener oMouseAdapterTblDetalleZona = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblOficinasCliente().getModel().getRowCount()==0)
				popupMenuClienteZona.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowZonaForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalleZona = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowZonaForUpdate(evt);
		}
	};
	
	MouseListener oMouseAdapterTblDetalleRetencion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowRetencionForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalleRetencion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowRetencionForUpdate(evt);
		}
	};
	
	MouseListener oMouseAdapterTblOficinasCliente = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblDetalleContacto().getModel().getRowCount() == 0 && getTblDetalleIndicador().getModel().getRowCount() == 0)
				popupMenuClienteOficina.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowOficinaForUpdate(evt);
		}
	};
	
	private void enableSelectedRowZonaForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (getTblDetalleZona().getSelectedRow() != -1) {
			ClienteZonaIf clienteZonaTemp = (ClienteZonaIf) detalleZonaClienteColeccion.get(getTblDetalleZona().getSelectedRow());
			getTxtCodigoZona().setText(clienteZonaTemp.getCodigo());
			getTxtNombreZona().setText(clienteZonaTemp.getNombre());
		}
	}
	
	private void enableSelectedRowRetencionForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (getTblRetenciones().getSelectedRow() != -1) {
			ClienteRetencionIf clienteRetencionTemp = (ClienteRetencionIf) detalleRetencionClienteColeccion.get(getTblRetenciones().getSelectedRow());
			getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),clienteRetencionTemp.getSriAirId()));
			getCmbRetencionRenta().validate();
			getCmbRetencionRenta().repaint();
			getCmbRetencionIVA().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIVA(),clienteRetencionTemp.getSriIvaRetencionId()));
			getCmbRetencionIVA().validate();
			getCmbRetencionIVA().repaint();
		}
	}
	
	KeyListener oKeyAdapterTblOficinasCliente = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowOficinaForUpdate(evt);
		}
	};
	
	private void enableSelectedRowOficinaForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		clienteOficinaFilaSeleccionada = getTblOficinasCliente().getSelectedRow();
		if (getTblOficinasCliente().getSelectedRow()!=-1) {
			try {
				ClienteOficinaIf clienteOficinaTemp = (ClienteOficinaIf) detalleOficinaClienteColeccion.get(getTblOficinasCliente().getSelectedRow());
				clienteOficinaIf = clienteOficinaTemp;
				String fechaCreacionClienteOficina = Utilitarios.setFechaUppercase().format(clienteOficinaTemp.getFechaCreacion()).toUpperCase();
				getTxtFechaCreacionClienteOficina().setText(fechaCreacionClienteOficina);
				
				if (NOMBRE_ESTADO_ACTIVO.equals(getTblOficinasCliente().getModel().getValueAt(getTblOficinasCliente().getSelectedRow(),2).toString()))
					getCmbEstadoClienteOficina().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else
					getCmbEstadoClienteOficina().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
				if ("A".equals(clienteOficinaTemp.getCalificacion()))
					getCmbCalificacionClienteOficina().setSelectedItem("A");
				else if ("B".equals(clienteOficinaTemp.getCalificacion()))
					getCmbCalificacionClienteOficina().setSelectedItem("B");
				else if ("C".equals(clienteOficinaTemp.getCalificacion()))
					getCmbCalificacionClienteOficina().setSelectedItem("C");
				else if ("D".equals(clienteOficinaTemp.getCalificacion()))
					getCmbCalificacionClienteOficina().setSelectedItem("D");
				else
					getCmbCalificacionClienteOficina().setSelectedItem("E");
				
				String ciudad = getTblOficinasCliente().getModel().getValueAt(getTblOficinasCliente().getSelectedRow(),4).toString();
				
				Map ciudadMap = new HashMap();	            
				ciudadMap.put("codigo", ciudad.split(" - ")[0]);
				ciudadMap.put("nombre", ciudad.split(" - ")[1]);
				
				ciudadIf = (CiudadIf) SessionServiceLocator.getCiudadSessionService().findCiudadByQuery(ciudadMap).iterator().next();
				getTxtCiudadClienteOficina().setText(ciudadIf.getCodigo() + " - " + ciudadIf.getNombre());
				getTxtCodigoClienteOficina().setText(clienteOficinaTemp.getCodigo());
				getTxtDescripcionClienteOficina().setText(clienteOficinaTemp.getDescripcion());
				getTxtObservacionClienteOficina().setText(clienteOficinaTemp.getObservacion());
				getTxtDireccionClienteOficina().setText(clienteOficinaTemp.getDireccion());
				getTxtTelefonoClienteOficina().setText(clienteOficinaTemp.getTelefono());
				getTxtFaxClienteOficina().setText(clienteOficinaTemp.getFax());
				getTxtEmailClienteOficina().setText(
					clienteOficinaTemp.getEmail()!=null? clienteOficinaTemp.getEmail() : "" );
				if(clienteOficinaTemp.getMontoCredito() != null){
					getTxtMontoCreditoClienteOficina().setText(clienteOficinaTemp.getMontoCredito().toString());
				}
				if(clienteOficinaTemp.getMontoGarantia() != null){
					getTxtMontoGarantiaClienteOficina().setText(clienteOficinaTemp.getMontoGarantia().toString());
				}
				
				// TABLA DE CONTACTO
				//cleanContacto();
				//for(int x= getTblDetalleContacto().getRowCount();x>0;--x)
				//	modelClienteOficinaContacto.removeRow(x-1);
				limpiarTabla(getTblDetalleContacto());
				
				//Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
				Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(clienteOficinaIf.getCodigo());
				
				if(detallesContactoToOficinaClienteColeccion!=null){
					modelClienteOficinaContacto = (DefaultTableModel) getTblDetalleContacto().getModel();
					
					for (int i=0;i < detallesContactoToOficinaClienteColeccion.size(); i++){
						Vector<String> filaClienteContacto = new Vector<String>();
						
						ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) detallesContactoToOficinaClienteColeccion.get(i);
						TipoContactoIf tipoContactoTemp = (TipoContactoIf) SessionServiceLocator.getTipoContactoSessionService().getTipoContacto(clienteContactoTemp.getTipocontactoId());
						String cumpleanosContacto = Utilitarios.getFechaCortaUppercase(clienteContactoTemp.getCumpleanos());;
						
						filaClienteContacto.add(tipoContactoTemp.getNombre());
						filaClienteContacto.add(clienteContactoTemp.getNombre());
						filaClienteContacto.add(clienteContactoTemp.getMail());
						filaClienteContacto.add(clienteContactoTemp.getDireccion());
						
						modelClienteOficinaContacto.addRow(filaClienteContacto);
					}
				}					
				
				// TABLA DE INDICADORES
				//cleanIndicador();
				//for(int x= getTblDetalleIndicador().getRowCount();x>0;--x)
				//	modelClienteOficinaIndicador.removeRow(x-1);
				limpiarTabla(getTblDetalleIndicador());
				
				//Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
				Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(clienteOficinaIf.getCodigo());
				
				if(detallesIndicadorToOficinaClienteColeccion!=null){
					modelClienteOficinaIndicador = (DefaultTableModel) getTblDetalleIndicador().getModel();
					
					for (int i=0;i < detallesIndicadorToOficinaClienteColeccion.size(); i++){
						Vector<String> filaClienteIndicador = new Vector<String>();
						
						ClienteIndicadorIf clienteIndicadorTemp = (ClienteIndicadorIf) detallesIndicadorToOficinaClienteColeccion.get(i);
						TipoIndicadorIf tipoIndicadorTemp = (TipoIndicadorIf) SessionServiceLocator.getTipoIndicadorSessionService().getTipoIndicador(clienteIndicadorTemp.getTipoindicadorId());
						String valorTemp = clienteIndicadorTemp.getValor().toString(); 
						
						filaClienteIndicador.add(tipoIndicadorTemp.getNombre());
						filaClienteIndicador.add(valorTemp);
						
						modelClienteOficinaIndicador.addRow(filaClienteIndicador);
						
					}
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}	
		}	
	}
	
	MouseListener oMouseAdapterTblDetalleContacto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblDetalleContacto().getModel().getRowCount()>0)
				popupMenuClienteOficinaContacto.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowContactoForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalleContacto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowContactoForUpdate(evt);
		}
	};
	
	private void enableSelectedRowContactoForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (getTblDetalleContacto().getSelectedRow()!=-1) {
			Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) detallesContactoToOficinaClienteColeccion.get(getTblDetalleContacto().getSelectedRow());
			
			for (int i=0; i<getCmbTipoContacto().getItemCount(); i++) {
				TipoContactoIf bean = (TipoContactoIf) getCmbTipoContacto().getItemAt(i);
				if(bean.getNombre().compareTo(getTblDetalleContacto().getModel().getValueAt(getTblDetalleContacto().getSelectedRow(),0).toString())==0)
					getCmbTipoContacto().setSelectedItem(bean);
				getCmbTipoContacto().repaint();
			}
			
			Calendar calendarCumpleanosContacto = new GregorianCalendar();
			calendarCumpleanosContacto.setTime(clienteContactoTemp.getCumpleanos());
			getCmbCumpleanosContacto().setCalendar(calendarCumpleanosContacto);
			getTxtNombreContacto().setText(clienteContactoTemp.getNombre());
			getTxtMailContacto().setText(clienteContactoTemp.getMail());
			getTxtTelefonoCasaContacto().setText(clienteContactoTemp.getTelefonoCasa());
			getTxtTelefonoOficContacto().setText(clienteContactoTemp.getTelefonoOfic());
			getTxtCelularContacto().setText(clienteContactoTemp.getCelular());
			getTxtDireccionContacto().setText(clienteContactoTemp.getDireccion());
		}		
	}
	
	MouseListener oMouseAdapterTblDetalleIndicador = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblDetalleIndicador().getModel().getRowCount()>0)
				popupMenuClienteOficinaIndicador.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowIndicadorForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalleIndicador = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowIndicadorForUpdate(evt);
		}
	};
	
	private void enableSelectedRowIndicadorForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (getTblDetalleIndicador().getSelectedRow()!=-1) {
			Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			ClienteIndicadorIf clienteIndicadorTemp = (ClienteIndicadorIf) detallesIndicadorToOficinaClienteColeccion.get(getTblDetalleIndicador().getSelectedRow());
			
			for (int i=0; i<getCmbTipoIndicador().getItemCount(); i++) {
				TipoIndicadorIf bean = (TipoIndicadorIf) getCmbTipoIndicador().getItemAt(i);
				if(bean.getNombre().compareTo(getTblDetalleIndicador().getModel().getValueAt(getTblDetalleIndicador().getSelectedRow(),0).toString())==0)
					getCmbTipoIndicador().setSelectedItem(bean);
				getCmbTipoIndicador().repaint();
			}
			
			getTxtValorIndicador().setText(clienteIndicadorTemp.getValor().toString());
		}		
	}
	
	public void showSaveMode() {
		getCmbTipoCliente().setBackground(Parametros.saveUpdateColor);
		getTxtNombreLegalCliente().setBackground(Parametros.saveUpdateColor);
		getTxtinformacionAdc().setBackground(Parametros.saveUpdateColor);
		getTxtRazonSocialCliente().setBackground(Parametros.saveUpdateColor);
		getTxtIdentificacionCliente().setBackground(Parametros.saveUpdateColor);
		getTxtCorporacionCliente().setBackground(getBackground());
		getCmbTipoIdentificacionCliente().setBackground(Parametros.saveUpdateColor);
		getCmbTipoNegocioCliente().setBackground(Parametros.saveUpdateColor);
		setSaveMode();
		
		getCmbRequiereSap().setSelectedItem(NOMBRE_OPCION_NO);
				
		// Cliente
		getCmbTipoIdentificacionCliente().setEnabled(true);
		getTxtIdentificacionCliente().setEnabled(true);
		getTxtNombreLegalCliente().setEnabled(true);
		getTxtinformacionAdc().setEnabled(true);
		getTxtRazonSocialCliente().setEnabled(true);		
		getTxtRepresentanteCliente().setEnabled(true);
		getCmbEstadoCliente().setEnabled(true);
		getTxtObservacionesCliente().setEnabled(true);
		getTxtCodigoZona().setEnabled(true);
		getTxtNombreZona().setEnabled(true);
		getBtnAgregarDetalleZona().setEnabled(true);
		getBtnActualizarDetalleZona().setEnabled(true);
		getTxtFechaCreacionCliente().setText(Utilitarios.fechaAhora());
		getCmbTipoProveedor().setEnabled(false);
		
		corporacionIf = null; 
		
		// Cliente Oficina
		getTxtCodigoClienteOficina().setEnabled(true);
		getCmbEstadoClienteOficina().setEnabled(true);
		getTxtDescripcionClienteOficina().setEnabled(true);
		getTxtObservacionClienteOficina().setEnabled(true);
		getTxtDireccionClienteOficina().setEnabled(true);		
		getTxtTelefonoClienteOficina().setEnabled(true);
		getTxtFaxClienteOficina().setEnabled(true);
		getTxtEmailClienteOficina().setEnabled(true);
		getTxtMontoCreditoClienteOficina().setEnabled(true);
		getTxtMontoGarantiaClienteOficina().setEnabled(true);
		getCmbCalificacionClienteOficina().setEnabled(true);
		getBtnAgregarClienteOficina().setEnabled(true);
		getBtnActualizarClienteOficina().setEnabled(true);
		getCmbTipoContacto().setEnabled(true);
		getTxtNombreContacto().setEnabled(true);
		getTxtMailContacto().setEnabled(true);
		getTxtTelefonoCasaContacto().setEnabled(true);
		getTxtTelefonoOficContacto().setEnabled(true);
		getTxtCelularContacto().setEnabled(true);
		getTxtDireccionContacto().setEnabled(true);
		getCmbCumpleanosContacto().setEnabled(true);
		getBtnAgregarDetalleContacto().setEnabled(true);
		getBtnActualizarDetalleContacto().setEnabled(true);
		getCmbTipoIndicador().setEnabled(true);
		getTxtValorIndicador().setEnabled(true);
		getBtnAgregarDetalleContacto().setEnabled(true);
		getBtnActualizarDetalleContacto().setEnabled(true);
		getTxtFechaCreacionClienteOficina().setText(Utilitarios.fechaAhora());		
		getCmbCumpleanosContacto().setFormat(Utilitarios.setFechaUppercase());
		//fechaCumpleanosContacto.setYear(fechaCumpleanosContacto.getYear()-15);
		Calendar calendarCumpleanosContacto = new GregorianCalendar();
		calendarCumpleanosContacto.setTime(fechaCumpleanosContacto);
		getCmbCumpleanosContacto().setCalendar(calendarCumpleanosContacto);
		
		loadCombos();	
		
		empresaId = null;
		
		detalleZonaClienteColeccion.clear();
		detalleRetencionClienteColeccion.clear();
		detalleOficinaClienteColeccion.clear();
		detalleContactoOficinaClienteMap.clear();
		detalleIndicadorOficinaClienteMap.clear();
		clienteOficinaVector.clear();
		detalleZonaRemovidaClienteColeccion.clear();
		detalleRetencionRemovidaClienteColeccion.clear();
		detalleOficinaRemovidaClienteColeccion.clear();
		detalleContactoRemovidoClienteColeccion.clear();
		detalleIndicadorRemovidoClienteColeccion.clear();
		
		getJtpTabsAdministracionCliente().setSelectedIndex(0);
		getTpTabsCliente().setSelectedIndex(0);
		getCmbTipoCliente().setSelectedItem(null);
		getCmbTipoCliente().grabFocus();
	}
	
	public void showUpdateMode() {
		getCmbTipoCliente().setBackground(Parametros.saveUpdateColor);
		getTxtNombreLegalCliente().setBackground(Parametros.saveUpdateColor);
		getTxtinformacionAdc().setBackground(Parametros.saveUpdateColor);
		getTxtRazonSocialCliente().setBackground(Parametros.saveUpdateColor);
		getTxtIdentificacionCliente().setBackground(Parametros.saveUpdateColor);
		getTxtCorporacionCliente().setBackground(getBackground());
		getCmbTipoIdentificacionCliente().setBackground(Parametros.saveUpdateColor);
		getCmbTipoNegocioCliente().setBackground(Parametros.saveUpdateColor);
		setUpdateMode();
		
		// Cliente
		getCmbTipoIdentificacionCliente().setEnabled(true);
		getTxtIdentificacionCliente().setEnabled(true);
		getTxtNombreLegalCliente().setEnabled(true);
		getTxtinformacionAdc().setEnabled(true);
		getTxtRazonSocialCliente().setEnabled(true);		
		getTxtRepresentanteCliente().setEnabled(true);
		getCmbEstadoCliente().setEnabled(true);
		getTxtObservacionesCliente().setEnabled(true);
		getTxtCodigoZona().setEnabled(true);
		getTxtNombreZona().setEnabled(true);
		getBtnAgregarDetalleZona().setEnabled(true);
		getBtnActualizarDetalleZona().setEnabled(true);
		getTxtFechaCreacionCliente().setText(Utilitarios.fechaAhora());
		if (tipoClienteIf.getCodigo().equals("PR") || tipoClienteIf.getCodigo().equals("AM"))
			getCmbTipoProveedor().setEnabled(true);
		else
			getCmbTipoProveedor().setEnabled(false);
		
		// Cliente Oficina
		getTxtCodigoClienteOficina().setEnabled(true);
		getCmbEstadoClienteOficina().setEnabled(true);
		getTxtDescripcionClienteOficina().setEnabled(true);
		getTxtObservacionClienteOficina().setEnabled(true);
		getTxtDireccionClienteOficina().setEnabled(true);		
		getTxtTelefonoClienteOficina().setEnabled(true);
		getTxtFaxClienteOficina().setEnabled(true);
		getTxtEmailClienteOficina().setEnabled(true);
		getTxtMontoCreditoClienteOficina().setEnabled(true);
		getTxtMontoGarantiaClienteOficina().setEnabled(true);
		getCmbCalificacionClienteOficina().setEnabled(true);
		getBtnAgregarClienteOficina().setEnabled(true);
		getBtnActualizarClienteOficina().setEnabled(true);
		getCmbTipoContacto().setEnabled(true);
		getTxtNombreContacto().setEnabled(true);
		getTxtMailContacto().setEnabled(true);
		getTxtTelefonoCasaContacto().setEnabled(true);
		getTxtTelefonoOficContacto().setEnabled(true);
		getTxtCelularContacto().setEnabled(true);
		getTxtDireccionContacto().setEnabled(true);
		getCmbCumpleanosContacto().setEnabled(true);
		getBtnAgregarDetalleContacto().setEnabled(true);
		getBtnActualizarDetalleContacto().setEnabled(true);
		getCmbTipoIndicador().setEnabled(true);
		getTxtValorIndicador().setEnabled(true);
		getBtnAgregarDetalleContacto().setEnabled(true);
		getBtnActualizarDetalleContacto().setEnabled(true);
		getTxtFechaCreacionClienteOficina().setText(Utilitarios.fechaAhora());		
		getJtpTabsAdministracionCliente().setSelectedIndex(0);
		getTpTabsCliente().setSelectedIndex(0);
		getCmbTipoCliente().grabFocus();
	}
	
	public void showFindMode() {
		getCmbTipoCliente().setBackground(Parametros.findColor);
		getTxtNombreLegalCliente().setBackground(Parametros.findColor);
		getTxtinformacionAdc().setBackground(Parametros.findColor);
		getTxtRazonSocialCliente().setBackground(Parametros.findColor);
		getTxtIdentificacionCliente().setBackground(Parametros.findColor);
		getTxtCorporacionCliente().setBackground(Parametros.findColor);
		getCmbTipoIdentificacionCliente().setBackground(Parametros.findColor);
		getCmbTipoNegocioCliente().setBackground(Parametros.findColor);
		// Cliente
		corporacionIf = null;
		getCmbTipoIdentificacionCliente().setSelectedItem(null);
		getCmbTipoIdentificacionCliente().setEnabled(true);
		getCmbTipoIdentificacionCliente().setSelectedItem(null);
		getCmbTipoCliente().setSelectedItem(null);
		getCmbTipoCliente().setEnabled(true);
		getCmbTipoNegocioCliente().setSelectedItem(null);
		getCmbTipoNegocioCliente().setEnabled(true);
		getTxtIdentificacionCliente().setEnabled(true);
		getTxtNombreLegalCliente().setEnabled(true);
		getTxtinformacionAdc().setEnabled(true);
		getTxtRazonSocialCliente().setEnabled(true);		
		getTxtRepresentanteCliente().setEnabled(false);
		getBtnBuscarCorporacionCliente().setEnabled(true);
		getCmbEstadoCliente().setEnabled(false);
		getTxtObservacionesCliente().setEnabled(false);
		getTxtCodigoZona().setEnabled(false);
		getTxtNombreZona().setEnabled(false);
		getBtnAgregarDetalleZona().setEnabled(false);
		getBtnActualizarDetalleZona().setEnabled(false);
		getCmbTipoProveedor().setEnabled(false);
		
		// Cliente Oficina
		getTxtCodigoClienteOficina().setEnabled(false);
		getCmbEstadoClienteOficina().setEnabled(false);
		getTxtDescripcionClienteOficina().setEnabled(false);
		getTxtObservacionClienteOficina().setEnabled(false);
		getTxtDireccionClienteOficina().setEnabled(false);		
		getTxtTelefonoClienteOficina().setEnabled(false);
		getTxtFaxClienteOficina().setEnabled(false);
		getTxtEmailClienteOficina().setEnabled(false);
		getTxtMontoCreditoClienteOficina().setEnabled(false);
		getTxtMontoGarantiaClienteOficina().setEnabled(false);
		getCmbCalificacionClienteOficina().setEnabled(false);
		getBtnAgregarClienteOficina().setEnabled(false);
		getBtnActualizarClienteOficina().setEnabled(false);
		getCmbTipoContacto().setEnabled(false);
		getTxtNombreContacto().setEnabled(false);
		getTxtMailContacto().setEnabled(false);
		getTxtTelefonoCasaContacto().setEnabled(false);
		getTxtTelefonoOficContacto().setEnabled(false);
		getTxtCelularContacto().setEnabled(false);
		getTxtDireccionContacto().setEnabled(false);
		getCmbCumpleanosContacto().setEnabled(false);
		getBtnAgregarDetalleContacto().setEnabled(false);
		getBtnActualizarDetalleContacto().setEnabled(false);
		getCmbTipoIndicador().setEnabled(false);
		getTxtValorIndicador().setEnabled(false);
		getBtnAgregarDetalleContacto().setEnabled(false);
		getBtnActualizarDetalleContacto().setEnabled(false);
		
		getJtpTabsAdministracionCliente().setSelectedIndex(0);
		getTpTabsCliente().setSelectedIndex(0);
		getCmbTipoCliente().grabFocus();
	}
	
	public void save() {
		if (validateFields()) {
			try {
				ClienteIf cliente = registrarCliente();
				SessionServiceLocator.getClienteSessionService().procesarCliente(cliente, detalleZonaClienteColeccion, detalleRetencionClienteColeccion, detalleOficinaClienteColeccion, detalleContactoOficinaClienteMap, detalleIndicadorOficinaClienteMap,Parametros.isActivarReplicacion());
				SpiritAlert.createAlert("Operador de Negocio guardado con éxito", SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Ocurrió un error al guardar el Operador de Negocio", SpiritAlert.ERROR);
			}
		}
	}
	
	public void update() {
		if (validateFields()) {
			try {
				ClienteIf cliente = registrarClienteForUpdate();
				SessionServiceLocator.getClienteSessionService().actualizarCliente(
					cliente, detalleZonaClienteColeccion, detalleRetencionClienteColeccion, detalleOficinaClienteColeccion, 
					detalleContactoOficinaClienteMap, detalleIndicadorOficinaClienteMap, 
					detalleZonaRemovidaClienteColeccion, detalleRetencionRemovidaClienteColeccion, detalleOficinaRemovidaClienteColeccion, 
					detalleContactoRemovidoClienteColeccion, detalleIndicadorRemovidoClienteColeccion,
					Parametros.isActivarReplicacion());
				SpiritAlert.createAlert("Operador de Negocio actualizado con éxito", SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al actualizar el Operador de Negocio!", SpiritAlert.ERROR);
			}
			
		}
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getClienteSessionService().eliminarCliente(cliente.getIdentificacion(), Parametros.getIdEmpresa(), true);
			SpiritAlert.createAlert("Operador de Negocio eliminado con éxito", SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar el Operador de Negocio!", SpiritAlert.ERROR);
		}
	}
	
	public void refresh() {
		cargarComboTipoCliente();
		cargarComboTipoNegocioCliente();
		cargarComboTipoContacto();
		cargarComboTipoIndicador();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpTabsAdministracionCliente().getSelectedIndex();
		int tabCount = this.getJtpTabsAdministracionCliente().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpTabsAdministracionCliente().setSelectedIndex(selectedTab);
	}
	
	private ClienteIf registrarCliente() throws GenericBusinessException {
		ClienteData data = new ClienteData();
		TipoClienteIf tipoCliente = null;
		data.setIdentificacion(getTxtIdentificacionCliente().getText());
		data.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem()).getId());
		data.setNombreLegal(getTxtNombreLegalCliente().getText().toUpperCase());
		data.setInformacionAdc(getTxtinformacionAdc().getText());
		data.setRazonSocial(getTxtRazonSocialCliente().getText().toUpperCase());
		data.setRepresentante(getTxtRepresentanteCliente().getText().toUpperCase());
		if(corporacionIf != null) data.setCorporacionId(corporacionIf.getId());
		data.setFechaCreacion(fechaCreacionCliente);
		data.setEstado(getCmbEstadoCliente().getSelectedItem().toString().substring(0, 1));
		if (getCmbTipoCliente().getSelectedItem() != null) {
			tipoCliente = (TipoClienteIf) getCmbTipoCliente().getSelectedItem();
			data.setTipoclienteId(tipoCliente.getId());
			//if (tipoCliente.getCodigo().equals("CL") || tipoCliente.getCodigo().equals("AM"))
				data.setContribuyenteEspecial(getCmbContribuyenteEspecial().getSelectedItem().toString().substring(0,1));
			//else if (tipoCliente.getCodigo().equals("PR") || tipoCliente.getCodigo().equals("AM")) {
				data.setTipoPersona(getCmbTipoPersona().getSelectedItem().toString().substring(0,1));
				data.setLlevaContabilidad(getCmbLlevaContabilidad().getSelectedItem().toString().substring(0,1));
			//}
		}
		
		if (getCmbTipoProveedor().getSelectedItem() != null)
			data.setTipoproveedorId(((TipoProveedorIf) getCmbTipoProveedor().getSelectedItem()).getId());
		
		if (tipoCliente.getCodigo().equals("CL") || tipoCliente.getCodigo().equals("FU")) {
			Map parameterMap = new HashMap();
			parameterMap.put("codigo", "NN");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				TipoProveedorIf tipoProveedorNinguno = (TipoProveedorIf) it.next();
				data.setTipoproveedorId(tipoProveedorNinguno.getId());
			}
		}
		
		if (getCmbTipoNegocioCliente().getSelectedItem() != null)
			data.setTiponegocioId(((TipoNegocioIf) getCmbTipoNegocioCliente().getSelectedItem()).getId());
		
		data.setObservacion(getTxtObservacionesCliente().getText());
		data.setInformacionAdc(getTxtinformacionAdc().getText());
		data.setUsuariofinal(getCmbUsuarioFinal().getSelectedItem().toString().substring(0,1));
		
		if(getCmbRequiereSap().getSelectedItem().equals(NOMBRE_OPCION_NO)){
			data.setRequiereSap(OPCION_NO);
		}else if(getCmbRequiereSap().getSelectedItem().equals(NOMBRE_OPCION_SI)){
			data.setRequiereSap(OPCION_SI);
		}
		
		if(getCmbTipoCuenta().getSelectedItem() != null){
			data.setBancoId(((BancoIf) this.getCmbBanco().getSelectedItem()).getId());
			data.setTipoCuenta(((String)getCmbTipoCuenta().getSelectedItem()).substring(0, 1));
			
			if(!getTxtNumeroCuenta().getText().equals("")){
				data.setNumeroCuenta(getTxtNumeroCuenta().getText());
			}			
		}
		
		return data;
	}
	
	private ClienteIf registrarClienteForUpdate() throws GenericBusinessException {
		TipoClienteIf tipoCliente = null;
		cliente.setIdentificacion(getTxtIdentificacionCliente().getText());
		cliente.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem()).getId());
		cliente.setNombreLegal(getTxtNombreLegalCliente().getText().toUpperCase());
		cliente.setInformacionAdc(getTxtinformacionAdc().getText());
		cliente.setRazonSocial(getTxtRazonSocialCliente().getText().toUpperCase());
		cliente.setRepresentante(getTxtRepresentanteCliente().getText().toUpperCase());
		if(corporacionIf != null) cliente.setCorporacionId(corporacionIf.getId());
		cliente.setFechaCreacion(DateHelperClient.getTimeStampNow());
		cliente.setEstado(getCmbEstadoCliente().getSelectedItem().toString().substring(0, 1));
		if (getCmbTipoCliente().getSelectedItem() != null) {
			tipoCliente = (TipoClienteIf) getCmbTipoCliente().getSelectedItem();
			cliente.setTipoclienteId(tipoCliente.getId());
			//if (tipoCliente.getCodigo().equals("CL") || tipoCliente.getCodigo().equals("AM"))
				cliente.setContribuyenteEspecial(getCmbContribuyenteEspecial().getSelectedItem().toString().substring(0,1));
			//else if (tipoCliente.getCodigo().equals("PR") || tipoCliente.getCodigo().equals("AM")) {
				cliente.setTipoPersona(getCmbTipoPersona().getSelectedItem().toString().substring(0,1));
				cliente.setLlevaContabilidad(getCmbLlevaContabilidad().getSelectedItem().toString().substring(0,1));
			//}
		}
		
		if(getCmbTipoProveedor().getSelectedItem() != null) 
			cliente.setTipoproveedorId(((TipoProveedorIf) getCmbTipoProveedor().getSelectedItem()).getId());
		
		if (tipoCliente.getCodigo().equals("CL") || tipoCliente.getCodigo().equals("FU")) {
			Map parameterMap = new HashMap();
			parameterMap.put("codigo", "NN");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				TipoProveedorIf tipoProveedorNinguno = (TipoProveedorIf) it.next();
				cliente.setTipoproveedorId(tipoProveedorNinguno.getId());
			}
		}
		
		if (getCmbTipoNegocioCliente().getSelectedItem() != null)
			cliente.setTiponegocioId(((TipoNegocioIf) getCmbTipoNegocioCliente().getSelectedItem()).getId());
		
		cliente.setObservacion(getTxtObservacionesCliente().getText());
		cliente.setInformacionAdc(getTxtinformacionAdc().getText());
		cliente.setUsuariofinal(getCmbUsuarioFinal().getSelectedItem().toString().substring(0,1));
		
		if(getCmbRequiereSap().getSelectedItem().equals(NOMBRE_OPCION_NO)){
			cliente.setRequiereSap(OPCION_NO);
		}else if(getCmbRequiereSap().getSelectedItem().equals(NOMBRE_OPCION_SI)){
			cliente.setRequiereSap(OPCION_SI);
		}
		
		if(getCmbTipoCuenta().getSelectedItem() != null){
			cliente.setBancoId(((BancoIf) this.getCmbBanco().getSelectedItem()).getId());
			cliente.setTipoCuenta(((String)getCmbTipoCuenta().getSelectedItem()).substring(0, 1));
			
			if(!getTxtNumeroCuenta().getText().equals("")){
				cliente.setNumeroCuenta(getTxtNumeroCuenta().getText());
			}			
		}
		
		return cliente;
	}
	
	public void addDetail() {
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 0) {
			if (this.getTpTabsCliente().getSelectedIndex() == 1)
				this.agregarDetalleClienteZona();
		}
		
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 1) {
			if (this.getTpTabsOficina().getSelectedIndex() == 0)
				this.agregarDetalleClienteOficinaGeneral();
			if (this.getTpTabsOficina().getSelectedIndex() == 1)
				this.agregarDetalleClienteOficinaContacto();
			if (this.getTpTabsOficina().getSelectedIndex() == 2)
				this.agregarDetalleClienteOficinaIndicador();
		}
	}
	
	public void updateDetail() {
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 0) {
			if (this.getTpTabsCliente().getSelectedIndex() == 1)
				this.actualizarDetalleClienteZona();
		}
		
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 1) {
			if (this.getTpTabsOficina().getSelectedIndex() == 0)
				this.actualizarDetalleClienteOficinaGeneral();
			if (this.getTpTabsOficina().getSelectedIndex() == 1)
				this.actualizarDetalleClienteOficinaContacto();
			if (this.getTpTabsOficina().getSelectedIndex() == 2)
				this.actualizarDetalleClienteOficinaIndicador();
		}
	}
	
	public void deleteDetail() {
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 0) {
			if (this.getTpTabsCliente().getSelectedIndex() == 1)
				this.eliminarDetalleClienteZona();
		}
		
		if (this.getJtpTabsAdministracionCliente().getSelectedIndex() == 1) {
			if (this.getTpTabsOficina().getSelectedIndex() == 0)
				this.eliminarDetalleClienteOficinaGeneral();
			if (this.getTpTabsOficina().getSelectedIndex() == 1)
				this.eliminarDetalleClienteOficinaContacto();
			if (this.getTpTabsOficina().getSelectedIndex() == 2)
				this.eliminarDetalleClienteOficinaIndicador();
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtIdentificacionCliente().getText())
				&& getCmbTipoIdentificacionCliente().getSelectedItem() == null
				&& "".equals(this.getTxtNombreLegalCliente().getText())
				&& "".equals(this.getTxtinformacionAdc().getText())
				&& "".equals(this.getTxtRazonSocialCliente().getText())
				&& "".equals(this.getTxtRepresentanteCliente().getText())
				&& "".equals(this.getTxtCorporacionCliente().getText())
				&& getCmbTipoCliente().getSelectedItem() == null
				&& getCmbTipoNegocioCliente().getSelectedItem() == null
				&& getCmbEstadoCliente().getSelectedItem() == null
				&& "".equals(this.getTxtObservacionesCliente().getText())) {
			return true;			
		} else {
			return false;
		}
	}
	
	public void clean() {		
		limpiarTabla(getTblDetalleZona());
		limpiarTabla(getTblRetenciones());
		limpiarTabla(getTblOficinasCliente());
		limpiarTabla(getTblDetalleContacto());
		limpiarTabla(getTblDetalleIndicador());
		
		detalleContactoOficinaClienteMap = null;
		detalleContactoOficinaClienteMap = new HashMap();
		
		detalleIndicadorOficinaClienteMap = null;
		detalleIndicadorOficinaClienteMap = new HashMap();
		
		detalleZonaClienteColeccion = null;
		detalleZonaClienteColeccion = new Vector<ClienteZonaIf>();
		
		detalleRetencionClienteColeccion = null;
		detalleRetencionClienteColeccion = new Vector<ClienteRetencionIf>();
		
		detalleOficinaClienteColeccion = null;
		detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
		
		this.getTxtIdentificacionCliente().setText("");
		this.getTxtNombreLegalCliente().setText("");
		this.getTxtinformacionAdc().setText("");
		this.getTxtRazonSocialCliente().setText("");
		this.getTxtRepresentanteCliente().setText("");
		this.getTxtCorporacionCliente().setText("");
		this.getTxtFechaCreacionCliente().setText("");
		this.getCmbEstadoCliente().setSelectedItem("");
		this.getCmbEstadoCliente().removeAllItems();
		this.getTxtObservacionesCliente().setText("");
		this.getTxtinformacionAdc().setText("");
		this.getTxtCodigoZona().setText("");
		this.getTxtNombreZona().setText("");
		this.getTxtCodigoClienteOficina().setText("");
		this.getTxtFechaCreacionClienteOficina().setText("");
		this.getCmbEstadoClienteOficina().setSelectedItem("");
		this.getCmbEstadoClienteOficina().removeAllItems();
		this.getTxtDescripcionClienteOficina().setText("");
		this.getTxtObservacionClienteOficina().setText("");
		this.getTxtCiudadClienteOficina().setText("");
		this.getTxtDireccionClienteOficina().setText("");
		this.getTxtTelefonoClienteOficina().setText("");
		this.getTxtFaxClienteOficina().setText("");
		this.getTxtEmailClienteOficina().setText("");
		this.getTxtMontoCreditoClienteOficina().setText("");
		this.getTxtMontoGarantiaClienteOficina().setText("");
		this.getCmbCalificacionClienteOficina().setSelectedItem("");
		this.getCmbCalificacionClienteOficina().removeAllItems();
		this.getCmbTipoContacto().setSelectedItem("");
		this.getCmbTipoContacto().removeAllItems();
		this.getTxtNombreContacto().setText("");
		this.getTxtMailContacto().setText("");
		this.getTxtTelefonoCasaContacto().setText("");
		this.getTxtTelefonoOficContacto().setText("");
		this.getTxtCelularContacto().setText("");
		this.getTxtDireccionContacto().setText("");
		this.getCmbTipoIndicador().setSelectedItem("");
		this.getCmbTipoIndicador().removeAllItems();
		this.getTxtValorIndicador().setText("");
		
		empresaId = null;
		corporacionIf = null;
		tipoClienteIf = null;
		clienteOficinaIf = null;
		clienteOficinaVector.removeAllElements();
		
		this.getTxtNumeroCuenta().setText("");
	}
	
	public void cleanZona(){
		getTxtCodigoZona().setText("");
		getTxtNombreZona().setText("");
		getTxtCodigoZona().grabFocus();
	}
	
	public void cleanOficina(){
		getTxtCodigoClienteOficina().setText("");
		getTxtFechaCreacionClienteOficina().setText(Utilitarios.fechaAhora());
		getTxtDescripcionClienteOficina().setText("");
		if (getCmbEstadoClienteOficina().getItemCount() > 0)
			getCmbEstadoClienteOficina().setSelectedIndex(0);
		getTxtCiudadClienteOficina().setText("");
		getTxtDireccionClienteOficina().setText("");
		getTxtTelefonoClienteOficina().setText("");
		getTxtFaxClienteOficina().setText("");
		getTxtEmailClienteOficina().setText("");
		getTxtObservacionClienteOficina().setText("");
		if (getCmbCalificacionClienteOficina().getItemCount() > 0)
			getCmbCalificacionClienteOficina().setSelectedIndex(0);
		getTxtMontoCreditoClienteOficina().setText("");
		getTxtMontoGarantiaClienteOficina().setText("");
		getTxtCodigoClienteOficina().grabFocus();
		
		clienteOficinaFilaSeleccionada = -1;
	}
	
	private void cleanContacto() {
		if (getCmbTipoContacto().getItemCount() > 0)
			getCmbTipoContacto().setSelectedIndex(0);
		getTxtNombreContacto().setText("");
		getTxtMailContacto().setText("");
		getTxtTelefonoCasaContacto().setText("");
		getTxtTelefonoOficContacto().setText("");
		getTxtCelularContacto().setText("");
		getTxtDireccionContacto().setText("");
		getTxtNombreContacto().grabFocus();
	}
	
	private void cleanIndicador() {
		if (getCmbTipoIndicador().getItemCount() > 0)
			getCmbTipoIndicador().setSelectedIndex(0);
		getTxtValorIndicador().setText("");
		getTxtValorIndicador().grabFocus();
	}
	
	public void loadCombos() {
		try {
			getCmbEstadoCliente().addItem(NOMBRE_ESTADO_ACTIVO);
			getCmbEstadoCliente().addItem(NOMBRE_ESTADO_RIESGO);
			getCmbEstadoCliente().addItem(NOMBRE_ESTADO_INACTIVO);
			getCmbCalificacionClienteOficina().addItem("A");
			getCmbCalificacionClienteOficina().addItem("B");
			getCmbCalificacionClienteOficina().addItem("C");
			getCmbCalificacionClienteOficina().addItem("D");
			getCmbCalificacionClienteOficina().addItem("E");
			getCmbEstadoClienteOficina().addItem(NOMBRE_ESTADO_ACTIVO);
			getCmbEstadoClienteOficina().addItem(NOMBRE_ESTADO_INACTIVO);
			
			SpiritComboBoxModel cmbModelTipoContacto = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoContactoSessionService().findTipoContactoByEmpresaId(Parametros.getIdEmpresa()));
			getCmbTipoContacto().setModel(cmbModelTipoContacto);
			SpiritComboBoxModel cmbModelTipoIndicador = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoIndicadorSessionService().findTipoIndicadorByEmpresaId(Parametros.getIdEmpresa()));
			getCmbTipoIndicador().setModel(cmbModelTipoIndicador);
			
			cargarCombosRetenciones();
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}	
	
	private Comparator<SriAirIf> sriAirCodigoComparador = new Comparator<SriAirIf>(){
		public int compare(SriAirIf o1, SriAirIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	private Comparator<SriIvaRetencionIf> sriIvaRetencionCodigoComparador = new Comparator<SriIvaRetencionIf>(){
		public int compare(SriIvaRetencionIf o1, SriIvaRetencionIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	private void cargarCombosRetenciones() {
		try {
			//java.sql.Date fechaCompra = new java.sql.Date(getCmbFecha().getDate().getTime());
			java.sql.Date fechaCompra = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			List<SriAirIf> retencionesRenta = new ArrayList<SriAirIf>();
			List<SriIvaRetencionIf> retencionesIva = new ArrayList<SriIvaRetencionIf>();
			Iterator<SriAirIf> retencionesRentaIt = SessionServiceLocator.getSriAirSessionService().findSriAirByFecha(fechaCompra).iterator();
			while (retencionesRentaIt.hasNext()) {
				SriAirIf sriAir =  retencionesRentaIt.next();
				retencionesRenta.add(sriAir);
			}

			Iterator<SriIvaRetencionIf> retencionesIvaIt = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByFecha(fechaCompra).iterator();
			while (retencionesIvaIt.hasNext()) {
				SriIvaRetencionIf sriIvaRetencion = retencionesIvaIt.next();
				retencionesIva.add(sriIvaRetencion);
			}
			
			Collections.sort(retencionesRenta,sriAirCodigoComparador);
			refreshCombo(getCmbRetencionRenta(), retencionesRenta);
			Collections.sort(retencionesIva,sriIvaRetencionCodigoComparador);
			refreshCombo(getCmbRetencionIVA(), retencionesIva);
			
			if (getCmbRetencionIVA().getItemCount() <= 0)
				getCmbRetencionIVA().setEnabled(false);
			else
				getCmbRetencionIVA().setEnabled(true);
			
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarDetalleClienteRetencion() {
		try {	
			modelClienteRetencion = (DefaultTableModel) getTblRetenciones().getModel();
			Vector<String> filaClienteRetencion = new Vector<String>();
			boolean estaClienteRetencionRepetido = clienteRetencionRepetido();
			
			if (validateFieldsClienteRetencion(estaClienteRetencionRepetido, false)) {
				ClienteRetencionData data = new ClienteRetencionData();
				
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIVA().getSelectedItem();
								
				data.setSriAirId(sriAir.getId());
				data.setSriIvaRetencionId(sriIvaRetencion.getId());
				
				detalleRetencionClienteColeccion.add(data);
				
				filaClienteRetencion.add("["+sriAir.getPorcentaje()+"%] - " + sriAir.getCodigo() + " - " + sriAir.getConcepto());
				filaClienteRetencion.add("["+sriIvaRetencion.getPorcentaje()+"%] - " + sriIvaRetencion.getCodigo() + " - " + sriIvaRetencion.getConcepto());
				
				modelClienteRetencion.addRow(filaClienteRetencion);
				
				getCmbRetencionRenta().grabFocus();
			}				
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo agregar retención.", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleClienteRetencion() {
		try {	
			modelClienteRetencion = (DefaultTableModel) getTblRetenciones().getModel();
			boolean estaClienteRetencionRepetido = clienteRetencionRepetido();
			
			if (validateFieldsClienteRetencion(estaClienteRetencionRepetido, true)) {
				ClienteRetencionIf data = (ClienteRetencionIf) detalleRetencionClienteColeccion.get(getTblRetenciones().getSelectedRow());
				
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIVA().getSelectedItem();
								
				data.setSriAirId(sriAir.getId());
				data.setSriIvaRetencionId(sriIvaRetencion.getId());
				
				detalleRetencionClienteColeccion.set(getTblRetenciones().getSelectedRow(),data);
				
				String sriAirSeleccionado = "["+sriAir.getPorcentaje()+"%] - " + sriAir.getCodigo() + " - " + sriAir.getConcepto();
				String sriIvaRetencionSeleccionado = "["+sriIvaRetencion.getPorcentaje()+"%] - " + sriIvaRetencion.getCodigo() + " - " + sriIvaRetencion.getConcepto();
				modelClienteRetencion.setValueAt(sriAirSeleccionado, getTblRetenciones().getSelectedRow(), 0);
				modelClienteRetencion.setValueAt(sriIvaRetencionSeleccionado, getTblRetenciones().getSelectedRow(), 1);
				
				getCmbRetencionRenta().grabFocus();
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo actualizar zona del operador !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleClienteRetencion() {
		if (getTblRetenciones().getSelectedRow() != -1) {
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ClienteRetencionIf clienteRetencion = (ClienteRetencionIf) detalleRetencionClienteColeccion.get(getTblRetenciones().getSelectedRow());
				if (clienteRetencion.getId() != null){
					detalleRetencionRemovidaClienteColeccion.add(detalleRetencionClienteColeccion.get(getTblRetenciones().getSelectedRow()));
				}
				detalleRetencionClienteColeccion.remove(getTblRetenciones().getSelectedRow());
				modelClienteRetencion.removeRow(getTblRetenciones().getSelectedRow());
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	
	private void agregarDetalleClienteZona() {
		try {	
			modelClienteZona = (DefaultTableModel) getTblDetalleZona().getModel();
			Vector<String> filaClienteZona = new Vector<String>();
			boolean estaClienteZonaRepetido = clienteZonaRepetido();
			
			if (validateFieldsClienteZona(estaClienteZonaRepetido, false)) {
				ClienteZonaData data = new ClienteZonaData();
				
				//data.setCodigo(getTxtCodigoZona().getText());
				data.setNombre(getTxtNombreZona().getText());
				
				detalleZonaClienteColeccion.add(data);
				
				filaClienteZona.add("");
				filaClienteZona.add(getTxtNombreZona().getText());
				
				modelClienteZona.addRow(filaClienteZona);
				
				cleanZona();
				getTxtNombreZona().grabFocus();
			}				
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo agregar zona del operador !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleClienteZona() {
		try {	
			modelClienteZona = (DefaultTableModel) getTblDetalleZona().getModel();
			boolean estaClienteZonaRepetido = clienteZonaRepetido();
			
			if (validateFieldsClienteZona(estaClienteZonaRepetido, true)) {
				ClienteZonaIf data = (ClienteZonaIf) detalleZonaClienteColeccion.get(getTblDetalleZona().getSelectedRow());
				
				//data.setCodigo(getTxtCodigoZona().getText());
				data.setNombre(getTxtNombreZona().getText());
				
				detalleZonaClienteColeccion.set(getTblDetalleZona().getSelectedRow(),data);
				
				modelClienteZona.setValueAt(getTxtCodigoZona().getText(),getTblDetalleZona().getSelectedRow(),0);
				modelClienteZona.setValueAt(getTxtNombreZona().getText(),getTblDetalleZona().getSelectedRow(),1);
				
				cleanZona();
				getTxtNombreZona().grabFocus();
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo actualizar zona del operador !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleClienteZona() {
		if (getTblDetalleZona().getSelectedRow() != -1) {
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ClienteZonaIf zonaDetalle = (ClienteZonaIf) detalleZonaClienteColeccion.get(getTblDetalleZona().getSelectedRow());
				if ( zonaDetalle.getId() != null )
					detalleZonaRemovidaClienteColeccion.add(detalleZonaClienteColeccion.get(getTblDetalleZona().getSelectedRow()));
				detalleZonaClienteColeccion.remove(getTblDetalleZona().getSelectedRow());
				modelClienteZona.removeRow(getTblDetalleZona().getSelectedRow());
				cleanZona();			
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
			cleanZona();
		}
	}
	
	private boolean clienteZonaRepetido() {
		if(detalleZonaClienteColeccion.size()!=0){
			for(int i=0;i<detalleZonaClienteColeccion.size();i++){
				ClienteZonaIf clienteZonaTemp = (ClienteZonaIf) detalleZonaClienteColeccion.get(i);
				
				if(clienteZonaTemp.getNombre().equals(getTxtNombreZona().getText())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean clienteRetencionRepetido() {
		SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
		SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIVA().getSelectedItem();
			
		if(detalleRetencionClienteColeccion.size()!=0){
			for(int i=0;i<detalleRetencionClienteColeccion.size();i++){
				ClienteRetencionIf clienteRetencionTemp = (ClienteRetencionIf) detalleRetencionClienteColeccion.get(i);
				
				if(clienteRetencionTemp.getSriAirId().compareTo(sriAir.getId()) == 0
						&& clienteRetencionTemp.getSriIvaRetencionId().compareTo(sriIvaRetencion.getId()) == 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void agregarDetalleClienteOficinaGeneral() {
		try {	
			fechaCreacionClienteOficina = DateHelperClient.getTimeStampNow();
			modelClienteOficina = (DefaultTableModel) getTblOficinasCliente().getModel();
			Vector<String> filaClienteOficina = new Vector<String>();	
			Object[] estaClienteOficinaRepetido = clienteOficinaRepetido();
			
			if (validateFieldsClienteOficina(estaClienteOficinaRepetido, false)) {
				ClienteOficinaData data = new ClienteOficinaData();
				
				data.setCodigo(getTxtCodigoClienteOficina().getText());
				data.setFechaCreacion(fechaCreacionClienteOficina);
				data.setEstado(getCmbEstadoClienteOficina().getSelectedItem().toString().substring(0, 1));
				data.setDescripcion(getTxtDescripcionClienteOficina().getText().toUpperCase());
				data.setObservacion(getTxtObservacionClienteOficina().getText());
				
				data.setCiudadId(ciudadIf.getId());
				data.setDireccion(getTxtDireccionClienteOficina().getText());
				data.setTelefono(getTxtTelefonoClienteOficina().getText());
				data.setFax(getTxtFaxClienteOficina().getText());
				data.setEmail(getTxtEmailClienteOficina().getText());
				if(!getTxtMontoCreditoClienteOficina().getText().equals("")){
					data.setMontoCredito(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtMontoCreditoClienteOficina().getText()))));
				}
				if(!getTxtMontoGarantiaClienteOficina().getText().equals("")){
					data.setMontoGarantia(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtMontoGarantiaClienteOficina().getText()))));
				}
				data.setCalificacion(getCmbCalificacionClienteOficina().getSelectedItem().toString());						
				
				detalleOficinaClienteColeccion.add(data);
				
				filaClienteOficina.add(getTxtCodigoClienteOficina().getText());
				String fecha = Utilitarios.getFechaCortaUppercase(fechaCreacionClienteOficina);
				filaClienteOficina.add(fecha);
				filaClienteOficina.add(getCmbEstadoClienteOficina().getSelectedItem().toString());
				filaClienteOficina.add(getTxtDescripcionClienteOficina().getText());
				filaClienteOficina.add(getTxtCiudadClienteOficina().getText());						
				
				modelClienteOficina.addRow(filaClienteOficina);
				
				cleanOficina();
				ciudadIf = null;						
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo agregar oficina del operador !!!", SpiritAlert.ERROR);
			cleanOficina();
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleClienteOficinaGeneral() {
		try {
			modelClienteOficina = (DefaultTableModel) getTblOficinasCliente().getModel();
			Object[] estaClienteOficinaRepetido = clienteOficinaRepetido();
			if (validateFieldsClienteOficina(estaClienteOficinaRepetido, true)) {
				ClienteOficinaIf data = (ClienteOficinaIf) detalleOficinaClienteColeccion.get(getTblOficinasCliente().getSelectedRow());
				data.setCodigo(getTxtCodigoClienteOficina().getText());
				data.setFechaCreacion(fechaCreacionClienteOficina);
				data.setEstado(getCmbEstadoClienteOficina().getSelectedItem().toString().substring(0, 1));
				data.setDescripcion(getTxtDescripcionClienteOficina().getText().toUpperCase());
				data.setObservacion(getTxtObservacionClienteOficina().getText());
				data.setCiudadId(ciudadIf.getId());
				data.setDireccion(getTxtDireccionClienteOficina().getText());
				data.setTelefono(getTxtTelefonoClienteOficina().getText());
				data.setFax(getTxtFaxClienteOficina().getText());
				data.setEmail(getTxtEmailClienteOficina().getText());
				if(!getTxtMontoCreditoClienteOficina().getText().equals("")){
					data.setMontoCredito(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtMontoCreditoClienteOficina().getText()))));
				}
				if(!getTxtMontoGarantiaClienteOficina().getText().equals("")){
					data.setMontoGarantia(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtMontoGarantiaClienteOficina().getText()))));
				}
				data.setCalificacion(getCmbCalificacionClienteOficina().getSelectedItem().toString());						
				
				detalleOficinaClienteColeccion.set(getTblOficinasCliente().getSelectedRow(),data);
				
				modelClienteOficina.setValueAt(getTxtCodigoClienteOficina().getText(),getTblOficinasCliente().getSelectedRow(),0);
				String fecha = Utilitarios.getFechaCortaUppercase(fechaCreacionClienteOficina);
				modelClienteOficina.setValueAt(fecha,getTblOficinasCliente().getSelectedRow(),1);
				modelClienteOficina.setValueAt(getCmbEstadoClienteOficina().getSelectedItem().toString(),getTblOficinasCliente().getSelectedRow(),2);
				modelClienteOficina.setValueAt(getTxtDescripcionClienteOficina().getText(),getTblOficinasCliente().getSelectedRow(),3);
				modelClienteOficina.setValueAt(getTxtCiudadClienteOficina().getText(),getTblOficinasCliente().getSelectedRow(),4);
				
				cleanOficina();
				ciudadIf = null;						
			}		
		} catch(Exception e) {
			SpiritAlert.createAlert("No se pudo actualizar oficina del operador !!!", SpiritAlert.ERROR);
			cleanOficina();
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleClienteOficinaGeneral() {
		if (getTblOficinasCliente().getSelectedRow()!=-1 && getTblDetalleContacto().getRowCount()==0 && getTblDetalleIndicador().getRowCount()==0) {
			//Si existen detalles del resto de las filas posteriores a la del registro que se borró, le resto en uno la clave del mapa 
			for (int x = getTblOficinasCliente().getSelectedRow()+1; x<getTblOficinasCliente().getRowCount(); x++) {
				// CLIENTE OFICINA CONTACTO
				Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(x);
				
				// Si el detalle del contacto existe lo guardo en el mapa con un índice menos
				if (detallesContactoToOficinaClienteColeccion != null)
					detalleContactoOficinaClienteMap.put(x-1,detallesContactoToOficinaClienteColeccion);
				
				// CLIENTE OFICINA INDICADOR								
				Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(x);
				
				// Si el detalle del indicador existe lo guardo en el mapa con un índice menos
				if(detallesIndicadorToOficinaClienteColeccion != null)
					detalleIndicadorOficinaClienteMap.put(x-1,detallesIndicadorToOficinaClienteColeccion);	
			}
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ClienteOficinaIf oficinaGeneral = (ClienteOficinaIf) detalleOficinaClienteColeccion.get(getTblOficinasCliente().getSelectedRow());
				if (oficinaGeneral.getId()!=null)
					detalleOficinaRemovidaClienteColeccion.add(oficinaGeneral);
				detalleOficinaClienteColeccion.remove(getTblOficinasCliente().getSelectedRow());
				modelClienteOficina.removeRow(getTblOficinasCliente().getSelectedRow());
				cleanOficina();			
			}
			
		} else if (getTblDetalleContacto().getRowCount()>0 || getTblDetalleIndicador().getRowCount()>0){
			SpiritAlert.createAlert("Oficina del operador no puede ser eliminada debido a que tiene detalles asignados !!!",	SpiritAlert.WARNING);
			cleanOficina();
		}
		else {
			SpiritAlert.createAlert("Debe seleccionar la oficina del operador a eliminar !!!", SpiritAlert.WARNING);
			cleanOficina();
		}
	}
	
	private Object[] clienteOficinaRepetido() {
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
		String fecha = dateFormatter.format(fechaCreacionClienteOficina.getTime());
		
		if (detalleOficinaClienteColeccion.size() != 0) {
			for(int i=0;i<detalleOficinaClienteColeccion.size();i++){
				ClienteOficinaIf clienteOficinaTemp = (ClienteOficinaIf) detalleOficinaClienteColeccion.get(i);
				
				String fechaTemp = dateFormatter.format(clienteOficinaTemp.getFechaCreacion());
				Object[] validacion = existeClienteOficina(fecha, clienteOficinaTemp, fechaTemp);
				boolean existe = (Boolean)validacion[1];
				if( existe && clienteOficinaFilaSeleccionada!=i ) {
					return validacion;
				}
			}
		}
		
		return new Object[]{"",false};
	}
	
	private Object[] existeClienteOficina(String fecha, ClienteOficinaIf clienteOficinaTemp, String fechaTemp) {
		
		if ( getTxtEmailClienteOficina().getText().trim().equals(clienteOficinaTemp.getEmail()) ){
			return new Object[]{"Email ya se se encuentra Registrado !!",true};
		} else {
			boolean existe = clienteOficinaTemp.getCodigo().equals(getTxtCodigoClienteOficina().getText()) || 
			(
					fechaTemp.equals(fecha) && 
				(clienteOficinaTemp.getEstado() !=null ? clienteOficinaTemp.getEstado().equals(getCmbEstadoClienteOficina().getSelectedItem().toString().substring(0, 1)) : true )&&
				clienteOficinaTemp.getDescripcion().equals(getTxtDescripcionClienteOficina().getText()) &&
				(clienteOficinaTemp.getObservacion() != null ? clienteOficinaTemp.getObservacion().equals(getTxtObservacionClienteOficina().getText()) : true)&&
				clienteOficinaTemp.getCiudadId().equals(ciudadIf.getId()) &&
				clienteOficinaTemp.getDireccion().equals(getTxtDireccionClienteOficina().getText()) &&
				clienteOficinaTemp.getTelefono().equals(getTxtTelefonoClienteOficina().getText()) &&
				( clienteOficinaTemp.getFax()!=null ? clienteOficinaTemp.getFax().equals(getTxtFaxClienteOficina().getText()) : true )&&
				( clienteOficinaTemp.getEmail()!=null ? clienteOficinaTemp.getEmail().equals(getTxtEmailClienteOficina().getText()) : true)&&
				( clienteOficinaTemp.getCalificacion()!=null ?  clienteOficinaTemp.getCalificacion().equals(getCmbCalificacionClienteOficina().getSelectedItem().toString()) : true)
			);
			if ( existe ){
				return new Object[]{"Oficina del operador ya se encuentra agregada!!!",true};
			}
		}
		return new Object[]{"",false};
	}
	
	private void agregarDetalleClienteOficinaContacto() {
		//if (getTblOficinasCliente().getSelectedRow() != -1) {
		if (clienteOficinaIf != null) {
			//Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector<ClienteContactoIf> detallesContactoToOficinaClienteColeccion = (Vector<ClienteContactoIf>) detalleContactoOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			
			if (detallesContactoToOficinaClienteColeccion == null){
				detallesContactoToOficinaClienteColeccion = new Vector();
				detalleContactoOficinaClienteMap.put(clienteOficinaIf.getCodigo(), detallesContactoToOficinaClienteColeccion);
			}
			
			try {
				TipoContactoIf tipoContacto = (TipoContactoIf) getCmbTipoContacto().getSelectedItem();
				modelClienteOficinaContacto = (DefaultTableModel) getTblDetalleContacto().getModel();
				Vector<String> filaClienteContacto = new Vector<String>();
				boolean estaClienteOficinaContactoRepetido = clienteOficinaContactoRepetido(detallesContactoToOficinaClienteColeccion, tipoContacto);
				
				if (validateFieldsClienteContacto(estaClienteOficinaContactoRepetido, false)) {
					ClienteContactoData data = new ClienteContactoData();
					
					data.setTipocontactoId(tipoContacto.getId());
					data.setNombre(getTxtNombreContacto().getText());
					data.setMail(getTxtMailContacto().getText());
					if(!getTxtTelefonoCasaContacto().getText().equals("")){
						data.setTelefonoCasa(getTxtTelefonoCasaContacto().getText());
					}
					data.setTelefonoOfic(getTxtTelefonoOficContacto().getText());
					if(!getTxtCelularContacto().getText().equals("")){
						data.setCelular(getTxtCelularContacto().getText());
					}
					data.setDireccion(getTxtDireccionContacto().getText());
					data.setCumpleanos(fechaCumpleanosContacto);
					
					detallesContactoToOficinaClienteColeccion.add(data);
					
					//detalleContactoOficinaClienteMap.put(getTblOficinasCliente().getSelectedRow(),detallesContactoToOficinaClienteColeccion);
					
					filaClienteContacto.add(tipoContacto.getNombre());
					filaClienteContacto.add(getTxtNombreContacto().getText());
					filaClienteContacto.add(getTxtMailContacto().getText());
					filaClienteContacto.add(getTxtDireccionContacto().getText());
					
					modelClienteOficinaContacto.addRow(filaClienteContacto);
					
					cleanContacto();					
				}
			} catch(Exception e) {
				SpiritAlert.createAlert("No se pudo agregar contacto !!!", SpiritAlert.ERROR);
				cleanContacto();
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Seleccione la oficina a la cual pertenece este contacto!!!", SpiritAlert.WARNING);
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
		}
	}
	
	private void actualizarDetalleClienteOficinaContacto() {
		//if (getTblOficinasCliente().getSelectedRow() >= 0) {
		if (clienteOficinaIf != null) {
			//Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			
			if (detallesContactoToOficinaClienteColeccion == null){
				detallesContactoToOficinaClienteColeccion = new Vector();
				detalleContactoOficinaClienteMap.put(clienteOficinaIf.getCodigo(),detallesContactoToOficinaClienteColeccion);
			}
			
			try {
				if(getTblDetalleContacto().getSelectedRow() >= 0) {
					TipoContactoIf tipoContacto = (TipoContactoIf) getCmbTipoContacto().getSelectedItem();
					modelClienteOficinaContacto = (DefaultTableModel) getTblDetalleContacto().getModel();
					Vector<String> filaClienteContacto = new Vector<String>();
					boolean estaClienteOficinaContactoRepetido = clienteOficinaContactoRepetido(detallesContactoToOficinaClienteColeccion, tipoContacto);
					
					if (validateFieldsClienteContacto(estaClienteOficinaContactoRepetido, true)) {
						ClienteContactoIf data = (ClienteContactoIf) detallesContactoToOficinaClienteColeccion.get(getTblDetalleContacto().getSelectedRow());
						
						data.setTipocontactoId(tipoContacto.getId());
						data.setNombre(getTxtNombreContacto().getText());
						data.setMail(getTxtMailContacto().getText());
						data.setTelefonoCasa(getTxtTelefonoCasaContacto().getText());
						data.setTelefonoOfic(getTxtTelefonoOficContacto().getText());
						data.setCelular(getTxtCelularContacto().getText());
						data.setDireccion(getTxtDireccionContacto().getText());
						data.setCumpleanos(fechaCumpleanosContacto);
						
						detallesContactoToOficinaClienteColeccion.set(getTblDetalleContacto().getSelectedRow(),data);
						
						//detalleContactoOficinaClienteMap.put(getTblOficinasCliente().getSelectedRow(),detallesContactoToOficinaClienteColeccion);
						
						modelClienteOficinaContacto.setValueAt(tipoContacto.getNombre(),getTblDetalleContacto().getSelectedRow(),0);
						modelClienteOficinaContacto.setValueAt(getTxtNombreContacto().getText(),getTblDetalleContacto().getSelectedRow(),1);
						modelClienteOficinaContacto.setValueAt(getTxtMailContacto().getText(),getTblDetalleContacto().getSelectedRow(),2);
						modelClienteOficinaContacto.setValueAt(getTxtDireccionContacto().getText(),getTblDetalleContacto().getSelectedRow(),3);
						
						cleanContacto();
					}
				} else {
					SpiritAlert.createAlert("Seleccione primero un Contacto de la tabla !", SpiritAlert.WARNING);
					cleanContacto();
				}
			} catch(Exception e) {
				SpiritAlert.createAlert("No se pudo actualizar contacto !!!", SpiritAlert.ERROR);
				cleanContacto();
				e.printStackTrace();
			}			
		} else {
			SpiritAlert.createAlert("Seleccione la oficina a la cual pertenece este contacto!!!", SpiritAlert.WARNING);
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
		}
	}
	
	private void eliminarDetalleClienteOficinaContacto() {
		if (getTblDetalleContacto().getSelectedRow() !=- 1) {
			//Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector detallesContactoToOficinaClienteColeccion = (Vector) detalleContactoOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);        	
			if (opcion == JOptionPane.YES_OPTION) {
				ClienteContactoIf contacto = (ClienteContactoIf)detallesContactoToOficinaClienteColeccion.get(getTblDetalleContacto().getSelectedRow());
				Vector<ClienteContactoIf> detalleContactos =   detalleContactoRemovidoClienteColeccion.get(clienteOficinaIf.getCodigo());
				if (contacto.getId()!=null){
					//detalleContactoRemovidoClienteColeccion.add(contacto);
					if ( detalleContactos == null ){
						detalleContactos = new Vector<ClienteContactoIf>();
						detalleContactoRemovidoClienteColeccion.put(clienteOficinaIf.getCodigo(),detalleContactos);
					}
					detalleContactos.add(contacto);
				}
				detallesContactoToOficinaClienteColeccion.remove(getTblDetalleContacto().getSelectedRow());
				modelClienteOficinaContacto.removeRow(getTblDetalleContacto().getSelectedRow());
				cleanContacto();	
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el contacto a eliminar !!!", SpiritAlert.WARNING);
			cleanContacto();
		}
	}
	
	private boolean clienteOficinaContactoRepetido(Vector detallesContactoToOficinaClienteColeccion, TipoContactoIf tipoContacto) {		
		DateFormat dateFormatterFechas = DateFormat.getDateInstance(DateFormat.DEFAULT);
		String cumpleanosContacto = dateFormatterFechas.format(fechaCumpleanosContacto.getTime());
		
		if(detallesContactoToOficinaClienteColeccion.size()!=0){
			for(int i=0;i<detallesContactoToOficinaClienteColeccion.size();i++){
				ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) detallesContactoToOficinaClienteColeccion.get(i);
				String cumpleanosTemp = dateFormatterFechas.format(clienteContactoTemp.getCumpleanos());
				
				if (existeClienteContacto(cumpleanosContacto, tipoContacto, clienteContactoTemp, cumpleanosTemp)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean existeClienteContacto(String cumpleanosContacto, TipoContactoIf tipoContacto, ClienteContactoIf clienteContactoTemp, String cumpleanosTemp) {
		return clienteContactoTemp.getTipocontactoId().equals(tipoContacto.getId()) &&
		clienteContactoTemp.getNombre().equals(getTxtNombreContacto().getText()) &&
		/*clienteContactoTemp.getMail().equals(getTxtMailContacto().getText()) &&
		clienteContactoTemp.getTelefonoCasa().equals(getTxtTelefonoCasaContacto().getText()) &&
		clienteContactoTemp.getTelefonoOfic().equals(getTxtTelefonoOficContacto().getText()) &&
		clienteContactoTemp.getCelular().equals(getTxtCelularContacto().getText()) &&*/
		clienteContactoTemp.getDireccion().equals(getTxtDireccionContacto().getText()) &&
		cumpleanosContacto.equals(cumpleanosTemp);
	}
	
	private void agregarDetalleClienteOficinaIndicador() {
		if (getTblOficinasCliente().getSelectedRow() != -1) {
			//Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			
			if (detallesIndicadorToOficinaClienteColeccion == null)
				detallesIndicadorToOficinaClienteColeccion = new Vector();
			
			if ( getTxtValorIndicador().getText().equals("") ){
				SpiritAlert.createAlert("Debe ingresar un valor para el tipo de indicador"
						, SpiritAlert.INFORMATION);
				return;
			}	
			
			try {
				TipoIndicadorIf tipoIndicador = (TipoIndicadorIf) getCmbTipoIndicador().getSelectedItem();
				modelClienteOficinaIndicador = (DefaultTableModel) getTblDetalleIndicador().getModel();
				Vector<String> filaClienteIndicador = new Vector<String>();
				boolean estaClienteOficinaIndicadorRepetido = clienteOficinaIndicadorRepetido(detallesIndicadorToOficinaClienteColeccion, tipoIndicador);
				
				if (validateFieldsClienteIndicador(estaClienteOficinaIndicadorRepetido, false)) {
					ClienteIndicadorData data = new ClienteIndicadorData();
					
					data.setTipoindicadorId(tipoIndicador.getId());
					data.setValor(BigDecimal.valueOf(Double.valueOf(getTxtValorIndicador().getText())));
					detallesIndicadorToOficinaClienteColeccion.add(data);
					
					//detalleIndicadorOficinaClienteMap.put(getTblOficinasCliente().getSelectedRow(),detallesIndicadorToOficinaClienteColeccion);
					
					filaClienteIndicador.add(tipoIndicador.getNombre());
					filaClienteIndicador.add(getTxtValorIndicador().getText());
					
					modelClienteOficinaIndicador.addRow(filaClienteIndicador);
					
					cleanIndicador();					
				}
			} catch(Exception e) {
				SpiritAlert.createAlert("No se pudo agregar indicador !!!", SpiritAlert.ERROR);
				cleanIndicador();
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Seleccione la oficina a la cual pertenece este indicador!!!", SpiritAlert.WARNING);
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
		}
	}
	
	private void actualizarDetalleClienteOficinaIndicador() {
		//if (getTblOficinasCliente().getSelectedRow() >= 0) {
		if (clienteOficinaIf != null) {
			//Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			
			if (detallesIndicadorToOficinaClienteColeccion == null)
				detallesIndicadorToOficinaClienteColeccion = new Vector();
			
			try {
				if(getTblDetalleIndicador().getSelectedRow() >= 0){
					TipoIndicadorIf tipoIndicador = (TipoIndicadorIf) getCmbTipoIndicador().getSelectedItem();
					modelClienteOficinaIndicador = (DefaultTableModel) getTblDetalleIndicador().getModel();
					boolean estaClienteOficinaIndicadorRepetido = clienteOficinaIndicadorRepetido(detallesIndicadorToOficinaClienteColeccion, tipoIndicador);
					
					if (validateFieldsClienteIndicador(estaClienteOficinaIndicadorRepetido, true)) {
						ClienteIndicadorIf data = (ClienteIndicadorIf) detallesIndicadorToOficinaClienteColeccion.get(getTblDetalleIndicador().getSelectedRow());
						
						data.setTipoindicadorId(tipoIndicador.getId());
						data.setValor(BigDecimal.valueOf(Double.valueOf(getTxtValorIndicador().getText())));
						detallesIndicadorToOficinaClienteColeccion.set(getTblDetalleIndicador().getSelectedRow(),data);
						
						//detalleIndicadorOficinaClienteMap.put(getTblOficinasCliente().getSelectedRow(),detallesIndicadorToOficinaClienteColeccion);
						
						modelClienteOficinaIndicador.setValueAt(tipoIndicador.getNombre(),getTblDetalleIndicador().getSelectedRow(),0);
						modelClienteOficinaIndicador.setValueAt(getTxtValorIndicador().getText(),getTblDetalleIndicador().getSelectedRow(),1);
						
						cleanIndicador();
					}
				}
				else {
					SpiritAlert.createAlert("Seleccione primero el Indicador que desea actualizar!", SpiritAlert.INFORMATION);
				}
			} catch(Exception e) {
				SpiritAlert.createAlert("No se pudo actualizar indicador !!!", SpiritAlert.ERROR);
				cleanIndicador();
				e.printStackTrace();
			}			
		} else {
			SpiritAlert.createAlert("Seleccione la oficina a la cual pertenece este indicador!!!", SpiritAlert.WARNING);
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
		}
	}
	
	private void eliminarDetalleClienteOficinaIndicador() {
		if (clienteOficinaIf!=null && getTblDetalleIndicador().getSelectedRow() != -1) {
			//Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(getTblOficinasCliente().getSelectedRow());
			Vector detallesIndicadorToOficinaClienteColeccion = (Vector) detalleIndicadorOficinaClienteMap.get(clienteOficinaIf.getCodigo());
			
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ClienteIndicadorIf indicador = (ClienteIndicadorIf) detallesIndicadorToOficinaClienteColeccion.get(getTblDetalleIndicador().getSelectedRow());
				Vector<ClienteIndicadorIf> detalleIndicadores = detalleIndicadorRemovidoClienteColeccion.get(clienteOficinaIf.getCodigo());
				if (indicador.getId()!=null){
					//detalleIndicadorRemovidoClienteColeccion.add(indicador);
					if ( detalleIndicadores == null ){
						detalleIndicadores = new Vector<ClienteIndicadorIf>();
						detalleIndicadorRemovidoClienteColeccion.put(clienteOficinaIf.getCodigo(),detalleIndicadores);
					}
					detalleIndicadores.add(indicador);
				}
				detallesIndicadorToOficinaClienteColeccion.remove(getTblDetalleIndicador().getSelectedRow());
				modelClienteOficinaIndicador.removeRow(getTblDetalleIndicador().getSelectedRow());
				cleanIndicador();	
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el indicador a eliminar !!!", SpiritAlert.WARNING);
			cleanIndicador();
		}
	}
	
	private boolean clienteOficinaIndicadorRepetido(Vector detallesIndicadorToOficinaClienteColeccion, TipoIndicadorIf tipoIndicador) {
		if (detallesIndicadorToOficinaClienteColeccion.size()!=0) {
			for (int i=0; i<detallesIndicadorToOficinaClienteColeccion.size(); i++) {
				ClienteIndicadorIf clienteIndicadorTemp = (ClienteIndicadorIf) detallesIndicadorToOficinaClienteColeccion.get(i);
				
				if (existeClienteIndicador(tipoIndicador, clienteIndicadorTemp)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean existeClienteIndicador(TipoIndicadorIf tipoIndicador, ClienteIndicadorIf clienteIndicadorTemp) {
		return clienteIndicadorTemp.getTipoindicadorId().equals(tipoIndicador.getId()) &&
		clienteIndicadorTemp.getValor().equals(BigDecimal.valueOf(Double.valueOf(getTxtValorIndicador().getText())));
	}
	
	public boolean validateFields() {
		if (getCmbTipoCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de operador!!!", SpiritAlert.WARNING);
			getCmbTipoCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if (getCmbTipoIdentificacionCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de identificación para operador!!!", SpiritAlert.WARNING);
			getCmbTipoIdentificacionCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtIdentificacionCliente().getText())) {
			SpiritAlert.createAlert("Debe ingresar una identificación para el operador!!!", SpiritAlert.WARNING);
			getTxtIdentificacionCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if (tipoIdentificacionIf != null) {
			if (tipoIdentificacionIf.getCodigo().equals("CE")) {
				if (getTxtIdentificacionCliente().getText().trim().length() < MAX_LONGITUD_CEDULA) {
					SpiritAlert.createAlert("El número de cédula del cliente debe tener " + MAX_LONGITUD_CEDULA + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();
					this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
					this.getTpTabsCliente().setSelectedIndex(0);
					return false;
				}
			} else if (tipoIdentificacionIf.getCodigo().equals("RU")) {
				if (getTxtIdentificacionCliente().getText().trim().length() < MAX_LONGITUD_RUC) {
					SpiritAlert.createAlert("El RUC del cliente debe tener " + MAX_LONGITUD_RUC + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();
					this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
					this.getTpTabsCliente().setSelectedIndex(0);
					return false;
				}
			}
			
			if (tipoIdentificacionIf.getCodigo().equals("CE") || tipoIdentificacionIf.getCodigo().equals("RU")) {
				if (!ValidarIdentificacion.esNumeroIdentificacionValido(tipoIdentificacionIf.getCodigo(), getTxtIdentificacionCliente().getText())) {
					SpiritAlert.createAlert("La identificación ingresada no es válida!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();
					this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
					this.getTpTabsCliente().setSelectedIndex(0);
					return false;
				}
			}
			
			Collection operadores = null;
			boolean codigoRepetido = false;

			try {
				operadores = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			Iterator operadoresIt = operadores.iterator();

			while (operadoresIt.hasNext()) {
				ClienteIf operadorIf = (ClienteIf) operadoresIt.next();

				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (tipoIdentificacionIf.getId().compareTo(operadorIf.getTipoidentificacionId()) == 0 && getTxtIdentificacionCliente().getText().equals(operadorIf.getIdentificacion()))
						codigoRepetido = true;

				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (tipoIdentificacionIf.getId().compareTo(operadorIf.getTipoidentificacionId()) == 0 && getTxtIdentificacionCliente().getText().equals(operadorIf.getIdentificacion()))
						if (cliente.getId().equals(operadorIf.getId()) == false)
							codigoRepetido = true;
			}

			if (codigoRepetido) {
				SpiritAlert.createAlert("El número de identificación del operador está duplicado !!", SpiritAlert.WARNING);
				getTxtIdentificacionCliente().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
				this.getTpTabsCliente().setSelectedIndex(0);
				return false;
			}
		}
		
		if ("".equals(this.getTxtNombreLegalCliente().getText())) {
			SpiritAlert.createAlert("Debe ingresar el nombre legal del operador!!!", SpiritAlert.WARNING);
			getTxtNombreLegalCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtRazonSocialCliente().getText())) {
			SpiritAlert.createAlert("Debe ingresar la razón social del operador!!!", SpiritAlert.WARNING);
			getTxtRazonSocialCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		/*if ("".equals(this.getTxtRepresentanteCliente().getText())) {
		 SpiritAlert.createAlert("Debe ingresar el representante del operador!!!", SpiritAlert.WARNING);
		 getTxtRepresentanteCliente().grabFocus();
		 this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
		 this.getTpTabsCliente().setSelectedIndex(0);
		 return false;
		 }*/
		
		if (getTxtCorporacionCliente().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar la corporación!!!", SpiritAlert.WARNING);
			getBtnBuscarCorporacionCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if (getCmbEstadoCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado del operador!!!", SpiritAlert.WARNING);
			getCmbEstadoCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		if (tipoClienteIf.getCodigo().equals("PR") || tipoClienteIf.getCodigo().equals("AM")) {
			if (getCmbTipoProveedor().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar un tipo de proveedor para el operador!!!", SpiritAlert.WARNING);
				getCmbTipoProveedor().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
				this.getTpTabsCliente().setSelectedIndex(0);
				return false;
			}
		}
		
		if (getCmbTipoNegocioCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de negocio para el operador!!!", SpiritAlert.WARNING);
			getCmbTipoNegocioCliente().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		/*if ("".equals(this.getTxtObservacionesCliente().getText())) {
		 SpiritAlert.createAlert("Debe ingresar una observación para el operador!!!", SpiritAlert.WARNING);
		 getTxtObservacionesCliente().grabFocus();
		 this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
		 this.getTpTabsCliente().setSelectedIndex(0);
		 return false;
		 }*/
		
		if (getCmbUsuarioFinal().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el operador es un usuario final o no!!!", SpiritAlert.WARNING);
			getCmbUsuarioFinal().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(0);
			return false;
		}
		
		TipoClienteIf tipoCliente = (TipoClienteIf) getCmbTipoCliente().getSelectedItem();
		
		if (tipoCliente.getCodigo().equals("CL") || tipoCliente.getCodigo().equals("AM")) {
			if (getCmbContribuyenteEspecial().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar si el operador es contribuyente especial o no!!!", SpiritAlert.WARNING);
				getCmbContribuyenteEspecial().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
				this.getTpTabsCliente().setSelectedIndex(0);
				return false;
			}
		}
		
		if (tipoCliente.getCodigo().equals("PR") || tipoCliente.getCodigo().equals("AM")) {
			if (getCmbTipoPersona().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el tipo de persona!!!", SpiritAlert.WARNING);
				getCmbTipoPersona().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
				this.getTpTabsCliente().setSelectedIndex(0);
				return false;
			}
			
			if (getCmbLlevaContabilidad().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar si el operador lleva o no contabilidad!!!", SpiritAlert.WARNING);
				getCmbLlevaContabilidad().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
				this.getTpTabsCliente().setSelectedIndex(0);
				return false;
			}
		}
		
		if (detalleOficinaClienteColeccion.size() <= 0) {
			SpiritAlert.createAlert("Debe ingresar al menos una oficina para el operador!!!", SpiritAlert.WARNING);
			getTxtCodigoClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if(getCmbTipoCuenta().getSelectedItem() != null && getCmbBanco().getSelectedItem() == null){
			SpiritAlert.createAlert("Si eligio un tipo de cuenta, debe seleccionar el Banco.",SpiritAlert.WARNING);
			getCmbBanco().grabFocus();
			return false;
		}
		
		if(!getTxtNumeroCuenta().getText().equals("") && getCmbTipoCuenta().getSelectedItem() == null){
			SpiritAlert.createAlert("Si ingreso un número de cuenta, debe seleccionar el Tipo de Cuenta.",SpiritAlert.WARNING);
			getCmbBanco().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsClienteZona(boolean estaClienteZonaRepetido, boolean esActualizacion) {	
		/*if ("".equals(this.getTxtCodigoZona().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para la zona del operador!!!", SpiritAlert.WARNING);
			getTxtCodigoZona().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(1);
			return false;
		}*/
		
		if ("".equals(this.getTxtNombreZona().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la zona del operador!!!", SpiritAlert.WARNING);
			getTxtNombreZona().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(0);
			this.getTpTabsCliente().setSelectedIndex(1);
			return false;
		}
		
		if (estaClienteZonaRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Zona ya se encuentra agregada!!!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsClienteRetencion(boolean estaClienteRetencionRepetido, boolean esActualizacion) {	
		if (estaClienteRetencionRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Retenciones ya se encuentran agregadas.", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsClienteOficina(Object[] estaClienteOficinaRepetido, boolean esActualizacion) {
		if ("".equals(this.getTxtCodigoClienteOficina().getText())) {
			SpiritAlert.createAlert("Debe ingresar el código para la oficina del operador!!!", SpiritAlert.WARNING);
			getTxtCodigoClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if (this.getCmbEstadoClienteOficina().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado de la oficina del operador!!!", SpiritAlert.WARNING);
			getCmbEstadoClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtDescripcionClienteOficina().getText())) {
			SpiritAlert.createAlert("Debe ingresar una descripción para la oficina del operador!!!", SpiritAlert.WARNING);
			getTxtDescripcionClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtCiudadClienteOficina().getText())) {
			SpiritAlert.createAlert("Debe ingresar la ciudad para la oficina del operador!!!", SpiritAlert.WARNING);
			this.getBtnCiudadesClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtDireccionClienteOficina().getText())) {
			SpiritAlert.createAlert("Debe ingresar la dirección para la oficina del operador!!!", SpiritAlert.WARNING);
			this.getTxtDireccionClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if ("".equals(this.getTxtTelefonoClienteOficina().getText())) {
			SpiritAlert.createAlert("Debe ingresar el teléfono para la oficina del operador!!!", SpiritAlert.WARNING);
			this.getTxtTelefonoClienteOficina().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(0);
			return false;
		}
		
		if(!("".equals(getTxtEmailClienteOficina().getText())) && !(getTxtEmailClienteOficina().getText() == null)){
			if(!Utilitarios.validarCorreoElectronico(getTxtEmailClienteOficina().getText())){
				SpiritAlert.createAlert("El E-mail está mal escrito !!",SpiritAlert.WARNING);
				getTxtEmailClienteOficina().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
				this.getTpTabsOficina().setSelectedIndex(0);
				return false;
			}
		}		
		
		String mensaje = (String) estaClienteOficinaRepetido[0];
		boolean existeCliente = (Boolean) estaClienteOficinaRepetido[1];
		if (existeCliente && !esActualizacion) {
			SpiritAlert.createAlert(mensaje, SpiritAlert.WARNING);
			return false;
		}

		return true;
	}
	
	public boolean validateFieldsClienteContacto(boolean estaClienteOficinaContactoRepetido, boolean esActualizacion) {
		if (this.getCmbTipoContacto().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de contacto!!!", SpiritAlert.WARNING);
			this.getCmbTipoContacto().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(1);
			return false;
		}
		
		if ("".equals(this.getTxtNombreContacto().getText())) {
			SpiritAlert.createAlert("Debe ingresar el nombre del contacto!!!", SpiritAlert.WARNING);
			this.getTxtNombreContacto().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(1);
			return false;
		}
		
		if ("".equals(this.getTxtDireccionContacto().getText())) {
			SpiritAlert.createAlert("Debe ingresar la dirección del contacto!!!", SpiritAlert.WARNING);
			this.getTxtDireccionContacto().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(1);
			return false;
		}
		/*		
		 if ("".equals(this.getTxtTelefonoCasaContacto().getText())) {
		 SpiritAlert.createAlert("Debe ingresar el teléfono de la casa del contacto!!!", SpiritAlert.WARNING);
		 this.getTxtTelefonoCasaContacto().grabFocus();
		 this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
		 this.getTpTabsOficina().setSelectedIndex(1);
		 return false;
		 }
		 */
		if ("".equals(this.getTxtTelefonoOficContacto().getText())) {
			SpiritAlert.createAlert("Debe ingresar el teléfono de la oficina del contacto!!!", SpiritAlert.WARNING);
			this.getTxtTelefonoOficContacto().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(1);
			return false;
		}
		/*	
		 if ("".equals(this.getTxtCelularContacto().getText())) {
		 SpiritAlert.createAlert("Debe ingresar el celular del contacto!!!", SpiritAlert.WARNING);
		 this.getTxtCelularContacto().grabFocus();
		 this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
		 this.getTpTabsOficina().setSelectedIndex(1);
		 return false;
		 }
		 */
		if(!("".equals(getTxtMailContacto().getText())) && !(getTxtMailContacto().getText() == null)){
			if(!Utilitarios.validarCorreoElectronico(getTxtMailContacto().getText())){
				SpiritAlert.createAlert("El E-mail está mal escrito !!",SpiritAlert.WARNING);
				getTxtMailContacto().grabFocus();
				this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
				this.getTpTabsOficina().setSelectedIndex(1);
				return false;
			}
		}
		
		if (this.getCmbCumpleanosContacto().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la fecha de cumpleaños del contacto!!!", SpiritAlert.WARNING);
			this.getCmbCumpleanosContacto().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(1);
			return false;
		}
		
		if (estaClienteOficinaContactoRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Contacto ya se encuentra agregado!!!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsClienteIndicador(boolean estaClienteOficinaIndicadorRepetido, boolean esActualizacion) {
		if (getCmbTipoIndicador().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de indicador!!!", SpiritAlert.WARNING);
			this.getCmbTipoIndicador().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(2);
			return false;
		}
		
		if ("".equals(this.getTxtValorIndicador().getText())) {
			SpiritAlert.createAlert("Debe ingresar el valor para el indicador de la oficina!!!", SpiritAlert.WARNING);
			this.getTxtValorIndicador().grabFocus();
			this.getJtpTabsAdministracionCliente().setSelectedIndex(1);
			this.getTpTabsOficina().setSelectedIndex(2);
			return false;
		}
		
		if (estaClienteOficinaIndicadorRepetido && !esActualizacion) {
			SpiritAlert.createAlert("Indicador de la oficina del operador ya se encuentra agregado!!!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtIdentificacionCliente().getText()) == false)
			aMap.put("identificacion", getTxtIdentificacionCliente().getText() + "%");
		if ("".equals(getTxtNombreLegalCliente().getText()) == false)
			aMap.put("nombreLegal", getTxtNombreLegalCliente().getText() + "%");		
		if ("".equals(getTxtRazonSocialCliente().getText()) == false)
			aMap.put("razonSocial", getTxtRazonSocialCliente().getText() + "%");		
		if (corporacionIf != null)
			aMap.put("corporacionId", corporacionIf.getId());
		if (getCmbTipoIdentificacionCliente().getSelectedItem() != null)
			aMap.put("tipoidentificacionId", ((TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem()).getId());
		if (getCmbTipoNegocioCliente().getSelectedItem() != null)
			aMap.put("tiponegocioId", ((TipoNegocioIf) getCmbTipoNegocioCliente().getSelectedItem()).getId());
		//if ("".equals(getTxtDescripcionClienteOficina().getText()) == false)
		//	aMap.put("descripcionClienteOficina", getTxtDescripcionClienteOficina().getText() + "%");
				
		return aMap;
	}
	
	public void find() {
		try {
			Long tipoClienteId = 0L;
			
			if (tipoClienteIf != null)
				tipoClienteId = tipoClienteIf.getId();
			
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getClienteSessionService().findClienteByFilteredQuerySize(mapa, tipoClienteId, Parametros.getIdEmpresa());
			
			if (tamanoLista > 0) {
				AdministracionClienteCriteria administracionClienteCriteria = new AdministracionClienteCriteria(tipoClienteId);
				administracionClienteCriteria.setResultListSize(tamanoLista);
				administracionClienteCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(270);
				anchoColumnas.add(150);
				anchoColumnas.add(70);
				anchoColumnas.add(90);
				anchoColumnas.add(80);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), administracionClienteCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject(popupFinder.getElementoSeleccionado());
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public void getSelectedObject(Object objeto) {
		try {
			setUpdateMode();
			
			cliente = (ClienteIf) objeto;
			
			loadCombos();
			
			String fechaCreacionCliente = Utilitarios.getFechaUppercase(cliente.getFechaCreacion());
			
			tipoClienteIf = (TipoClienteIf) SessionServiceLocator.getTipoClienteSessionService().getTipoCliente(cliente.getTipoclienteId());
			
			//Veo si tiene una cuenta asiganada el cliente
			/*if(cliente.getCuentaId()!=null){
			 cuentaIf = (CuentaIf) getCuentaSessionService().getCuenta(cliente.getCuentaId());
			 getTxtCtaContableCliente().setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());	
			 }*/
			
			//Lleno el panel del cliente
			getTxtIdentificacionCliente().setText(cliente.getIdentificacion());
			getTxtNombreLegalCliente().setText(cliente.getNombreLegal());
			getTxtRazonSocialCliente().setText(cliente.getRazonSocial());
			getTxtRepresentanteCliente().setText(cliente.getRepresentante());			
			getTxtObservacionesCliente().setText(cliente.getObservacion());
			getTxtinformacionAdc().setText(cliente.getInformacionAdc());
			if (cliente.getUsuariofinal() != null) {
				if (cliente.getUsuariofinal().equals(OPCION_SI))
					getCmbUsuarioFinal().setSelectedItem(NOMBRE_OPCION_SI);
				else if (cliente.getUsuariofinal().equals(OPCION_NO))
					getCmbUsuarioFinal().setSelectedItem(NOMBRE_OPCION_NO);
				else
					getCmbUsuarioFinal().setSelectedItem(null);
			}
			
			if (cliente.getTipoPersona() != null) {
				if (cliente.getTipoPersona().equals(TIPO_PERSONA_NATURAL))
					getCmbTipoPersona().setSelectedItem(NOMBRE_TIPO_PERSONA_NATURAL);
				else if (cliente.getTipoPersona().equals(TIPO_PERSONA_JURIDICA))
					getCmbTipoPersona().setSelectedItem(NOMBRE_TIPO_PERSONA_JURIDICA);
				else
					getCmbTipoPersona().setSelectedItem(null);
			}
			
			if (cliente.getContribuyenteEspecial() != null) {
				if (cliente.getContribuyenteEspecial().equals(OPCION_SI))
					getCmbContribuyenteEspecial().setSelectedItem(NOMBRE_OPCION_SI);
				else if (cliente.getContribuyenteEspecial().equals(OPCION_NO))
					getCmbContribuyenteEspecial().setSelectedItem(NOMBRE_OPCION_NO);
				else
					getCmbContribuyenteEspecial().setSelectedItem(null);
			}
			
			if (cliente.getLlevaContabilidad() != null) {
				if (cliente.getLlevaContabilidad().equals(OPCION_SI))
					getCmbLlevaContabilidad().setSelectedItem(NOMBRE_OPCION_SI);
				else if (cliente.getLlevaContabilidad().equals(OPCION_NO))
					getCmbLlevaContabilidad().setSelectedItem(NOMBRE_OPCION_NO);
				else
					getCmbLlevaContabilidad().setSelectedItem(null);
			}
			
			if(cliente.getRequiereSap() != null){
				if(cliente.getRequiereSap().equals(OPCION_NO)){
					getCmbRequiereSap().setSelectedItem(NOMBRE_OPCION_NO);
				}else if(cliente.getRequiereSap().equals(OPCION_SI)){
					getCmbRequiereSap().setSelectedItem(NOMBRE_OPCION_SI);
				}
			}
			
			if(cliente.getCorporacionId() != null){
				corporacionIf = (CorporacionIf) SessionServiceLocator.getCorporacionSessionService().getCorporacion(cliente.getCorporacionId());
				getTxtCorporacionCliente().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			}
			
			getTxtFechaCreacionCliente().setText(fechaCreacionCliente);
			getCmbTipoIdentificacionCliente().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacionCliente(), cliente.getTipoidentificacionId()));
			if (getCmbTipoIdentificacionCliente().getSelectedItem() != null)
				tipoIdentificacionIf = (TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem();
			getCmbTipoCliente().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoCliente(), cliente.getTipoclienteId()));
			
			if(cliente.getTipoproveedorId() != null)
				getCmbTipoProveedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProveedor(), cliente.getTipoproveedorId()));
			else
				getCmbTipoProveedor().setSelectedIndex(-1);
			
			if ( cliente.getTiponegocioId() != null )
				getCmbTipoNegocioCliente().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoNegocioCliente(), cliente.getTiponegocioId()));
			
			if (cliente.getEstado() != null){
				if (ESTADO_ACTIVO.equals(cliente.getEstado().toString()))
					getCmbEstadoCliente().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if (ESTADO_RIESGO.equals(cliente.getEstado().toString()))
					getCmbEstadoCliente().setSelectedItem(NOMBRE_ESTADO_RIESGO);
				else
					getCmbEstadoCliente().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			} else 
				getCmbEstadoCliente().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			
			
			if(cliente.getTipoCuenta() != null && !cliente.getTipoCuenta().equals("")){
				if ( cliente.getBancoId() != null )
					getCmbBanco().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBanco(),cliente.getBancoId()));
				else
					getCmbBanco().setSelectedIndex(-1);
				
				if (TIPOCUENTA_AHORROS.substring(0,1).equals(cliente.getTipoCuenta())){
					getCmbTipoCuenta().setSelectedItem(TIPOCUENTA_AHORROS);
				}else if (TIPOCUENTA_CORRIENTE.substring(0,1).equals(cliente.getTipoCuenta())){
					getCmbTipoCuenta().setSelectedItem(TIPOCUENTA_CORRIENTE);
				}
				
				if(cliente.getNumeroCuenta() != null && !cliente.getNumeroCuenta().equals("")){		
					getTxtNumeroCuenta().setText(cliente.getNumeroCuenta());			
				}
				
			}else{
				getCmbBanco().setSelectedIndex(-1);
				getCmbTipoCuenta().setSelectedIndex(-1);
				getTxtNumeroCuenta().setText("");
			}
			
			//LLeno el panel de Cliente Zona			
			//Cargo los archivos pertencientes a la campana leida de la base
			Collection zonaDetallesColeccion = SessionServiceLocator.getClienteZonaSessionService().findClienteZonaByClienteId(cliente.getId());
			Iterator itZonaDetallesColeccion = zonaDetallesColeccion.iterator();
			
			//Obtengo el modelo de la tabla para agregar las condiciones leidos de la base
			modelClienteZona = (DefaultTableModel) getTblDetalleZona().getModel();
			
			while(itZonaDetallesColeccion.hasNext()){					
				ClienteZonaIf clienteZonaTemp = (ClienteZonaIf) itZonaDetallesColeccion.next();
				
				Vector<String> filaClienteZona= new Vector<String>();
				
				// Agregar a la coleccion de detallesZonaToClienteColeccion para grabar al final toda la coleccion
				detalleZonaClienteColeccion.add(clienteZonaTemp);
				
				
				// Agregra los valores al registro que va  ser añadido  a la tabla.
				filaClienteZona.add(clienteZonaTemp.getCodigo());
				filaClienteZona.add(clienteZonaTemp.getNombre());
				
				// Agregra informacion a la tabla visual para el usuario.
				modelClienteZona.addRow(filaClienteZona);
			}
			
			//LLeno el panel de Cliente Retencion			
			Collection retencionDetallesColeccion = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(cliente.getId());
			Iterator itretencionDetallesColeccion = retencionDetallesColeccion.iterator();
			
			modelClienteRetencion = (DefaultTableModel) getTblRetenciones().getModel();
			
			while(itretencionDetallesColeccion.hasNext()){					
				ClienteRetencionIf clienteRetencionTemp = (ClienteRetencionIf) itretencionDetallesColeccion.next();
				
				Vector<String> filaClienteRetencion= new Vector<String>();
				
				detalleRetencionClienteColeccion.add(clienteRetencionTemp);
				
				SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencionTemp.getSriAirId());
				SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencionTemp.getSriIvaRetencionId());
				String sriAirSeleccionado = "["+sriAir.getPorcentaje()+"%] - " + sriAir.getCodigo() + " - " + sriAir.getConcepto();
				String sriIvaRetencionSeleccionado = "["+sriIvaRetencion.getPorcentaje()+"%] - " + sriIvaRetencion.getCodigo() + " - " + sriIvaRetencion.getConcepto();
				
				filaClienteRetencion.add(sriAirSeleccionado);
				filaClienteRetencion.add(sriIvaRetencionSeleccionado);
				
				modelClienteRetencion.addRow(filaClienteRetencion);
			}
			
			
			//LLeno el panel de Cliente Oficina
			
			int contOficinas = 0;
			
			//Cargo los archivos pertencientes a la oficina leida de la base
			Collection oficinaDetallesColeccion = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(cliente.getId());
			Iterator itOficinaDetallesColeccion = oficinaDetallesColeccion.iterator();
			
			//Obtengo el modelo de la tabla para agregar las condiciones leidos de la base
			modelClienteOficina = (DefaultTableModel) getTblOficinasCliente().getModel();
			
			while(itOficinaDetallesColeccion.hasNext()){					
				ClienteOficinaIf clienteOficinaTemp = (ClienteOficinaIf) itOficinaDetallesColeccion.next();
				
				Vector<String> filaClienteOficina= new Vector<String>();
				clienteOficinaVector.add(clienteOficinaTemp);
				
				// Agregar a la coleccion de detallesOficinaToClienteColeccion para grabar al final toda la coleccion
				detalleOficinaClienteColeccion.add(clienteOficinaTemp);
				
				//Extraigo los objetos para ponerlos en la tabla
				CiudadIf ciudad = (CiudadIf) SessionServiceLocator.getCiudadSessionService().getCiudad(clienteOficinaTemp.getCiudadId());					
				
				String estado = "";
				if (ESTADO_ACTIVO.equals(clienteOficinaTemp.getEstado().toString()))
					estado = NOMBRE_ESTADO_ACTIVO;
				else if (ESTADO_RIESGO.equals(clienteOficinaTemp.getEstado().toString()))
					estado = NOMBRE_ESTADO_RIESGO;
				else
					estado = NOMBRE_ESTADO_INACTIVO;
				
				//Cambio el formato de la fecha para insertarlo a la base
				//String fechaCreacion = dateFormatterFechas.format(clienteOficinaTemp.getFechaCreacion());
				
				// Agregra los valores al registro que va  ser añadido  a la tabla.
				filaClienteOficina.add(clienteOficinaTemp.getCodigo());
				filaClienteOficina.add(Utilitarios.getFechaCortaUppercase(clienteOficinaTemp.getFechaCreacion()));
				filaClienteOficina.add(estado);
				filaClienteOficina.add(clienteOficinaTemp.getDescripcion());
				filaClienteOficina.add(ciudad.getCodigo() + " - " + ciudad.getNombre());
				
				// Agrega información a la tabla visual para el usuario.
				modelClienteOficina.addRow(filaClienteOficina);
				
				//TABLA DE CONTACTO
				
				Vector detallesContactoToOficinaClienteColeccion = new Vector();
				
				//Busco en la base los detalles de contacto que tenga este Clienbte Oficina
				Collection contactosDetalleColeccion = SessionServiceLocator.getClienteContactoSessionService().findClienteContactoByClienteoficinaId(clienteOficinaTemp.getId());
				Iterator itContactosDetalleColeccion = contactosDetalleColeccion.iterator();
				
				while(itContactosDetalleColeccion.hasNext()){
					ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) itContactosDetalleColeccion.next();
					
					// Agregar a la colección de detallesContactoToOficinaClienteColeccion para grabar al final toda la coleccion
					detallesContactoToOficinaClienteColeccion.add(clienteContactoTemp);
					
					//guardo en el mapa la coleccion de indicadores
					//detalleContactoOficinaClienteMap.put(contOficinas,detallesContactoToOficinaClienteColeccion);
					detalleContactoOficinaClienteMap.put(clienteOficinaTemp.getCodigo(),detallesContactoToOficinaClienteColeccion);
				}
				
				//TABLA DE INDICADORES
				
				Vector detallesIndicadorToOficinaClienteColeccion = new Vector();
				
				//Busco en la base los detalles de indicador que tenga este Clienbte Oficina
				Collection indicadorDetalleColeccion = SessionServiceLocator.getClienteIndicadorSessionService().findClienteIndicadorByClienteOficinaId(clienteOficinaTemp.getId());
				Iterator itIndicadorDetalleColeccion = indicadorDetalleColeccion.iterator();
				
				//Barro la coleccion de detalles
				while(itIndicadorDetalleColeccion.hasNext()){
					ClienteIndicadorIf clienteIndicadorTemp = (ClienteIndicadorIf) itIndicadorDetalleColeccion.next();
					
					// Agregar a la coleccion de detallesIndicadorToOficinaClienteColeccion para grabar al final toda la coleccion
					detallesIndicadorToOficinaClienteColeccion.add(clienteIndicadorTemp);
					
					//guardo en el mapa la coleccion de indicadores
					//detalleIndicadorOficinaClienteMap.put(contOficinas,detallesIndicadorToOficinaClienteColeccion);
					detalleIndicadorOficinaClienteMap.put(clienteOficinaTemp.getCodigo(),detallesIndicadorToOficinaClienteColeccion);
				}
				
				//Incremento en 1 el contador de contactos de oficinas
				contOficinas ++;
			}
			
			this.showUpdateMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}					
	}
	
	
	 
	
	private void cargarComboTipoCliente(){
		try {
			List tiposClientes = (List) SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoCliente(), tiposClientes);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoNegocioCliente(){
		try {
			List tiposNegocios = (List) SessionServiceLocator.getTipoNegocioSessionService().findTipoNegocioByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbTipoNegocioCliente(), tiposNegocios);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoContacto() {
		try {
			List tiposContactos = (List) SessionServiceLocator.getTipoContactoSessionService().findTipoContactoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbTipoContacto(), tiposContactos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoIndicador() {
		try {
			List tiposIndicadores = (List) SessionServiceLocator.getTipoIndicadorSessionService().findTipoIndicadorByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbTipoIndicador(), tiposIndicadores);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	/*public void validarRuc(String ruc) {
	 if (!ValidarIdentificacion.esNumeroIdentificacionValido("RU", ruc))
	 System.out.println(ruc);
	 }*/
	
	/*public void showSaveMode() {
	 try {
	 Iterator it = getFuncionSessionService().getFuncionList().iterator();
	 while (it.hasNext()) {
	 FuncionIf funcion = (FuncionIf) it.next();
	 validarRuc(funcion.getNombre());
	 }
	 } catch(GenericBusinessException e) {
	 e.printStackTrace();
	 }
	 }*/
	
	 
}