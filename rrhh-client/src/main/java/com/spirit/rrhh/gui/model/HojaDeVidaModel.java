package com.spirit.rrhh.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.PaisIf;
import com.spirit.general.entity.ProvinciaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.util.DateHelperClient;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.rrhh.entity.EmpleadoFamiliaresData;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.entity.EmpleadoFormacionData;
import com.spirit.rrhh.entity.EmpleadoFormacionIf;
import com.spirit.rrhh.entity.EmpleadoIdiomasData;
import com.spirit.rrhh.entity.EmpleadoIdiomasIf;
import com.spirit.rrhh.entity.EmpleadoPersonalData;
import com.spirit.rrhh.entity.EmpleadoPersonalIf;
import com.spirit.rrhh.entity.IdiomaIf;
import com.spirit.rrhh.gui.panel.JPHojaDeVida;
import com.spirit.rrhh.gui.reporteData.DatosAcademicosData;
import com.spirit.rrhh.gui.reporteData.DatosFamiliaresData;
import com.spirit.rrhh.gui.reporteData.DatosIdiomasData;
import com.spirit.rrhh.handler.NivelEstudioEnum;
import com.spirit.rrhh.handler.ParentezcoEnum;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.FinderTable;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class HojaDeVidaModel extends JPHojaDeVida {
	
	private static final long serialVersionUID = -238569864322159240L;
	
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_APELLIDOPATERNO = 25;
	private static final int MAX_LONGITUD_APELLIDOMATERNO = 25;
	private static final int MAX_LONGITUD_NOMBRES = 50;
	private static final int MAX_LONGITUD_TITULO = 70;
	private static final int MAX_LONGITUD_PROFESION = 30;
	private static final int MAX_LONGITUD_TIPOSANGRE = 6;
	private static final int MAX_LONGITUD_CEDULA = 13;
	private static final int MAX_LONGITUD_NUMEROIESS = 13;
	private static final int MAX_LONGITUD_LIBRETAMILITAR = 13;
	private static final int MAX_LONGITUD_CANTON = 30;
	private static final int MAX_LONGITUD_PARROQUIA = 30;
	private static final int MAX_LONGITUD_DOMICILIO = 40;
	private static final int MAX_LONGITUD_TELEFONO = 10;
	private static final int MAX_LONGITUD_CELULAR = 10;
	private static final int MAX_LONGITUD_EMERGENCIA = 40;
	private static final int MAX_LONGITUD_TELEFONOEMERGENCIA = 10;
	private static final int MAX_LONGITUD_DIRECCIONEMERGENCIA = 40;
	private static final int MAX_LONGITUD_TALLACAMISA = 2;
	private static final int MAX_LONGITUD_TALLAPANTALON = 2;
	private static final int MAX_LONGITUD_NUMEROCALZADO = 2;
	private static final int MAX_LONGITUD_ESTATURA = 3;
	private static final int MAX_LONGITUD_PESO = 3;
	private static final int MAX_LONGITUD_COLORPELO = 10;
	private static final int MAX_LONGITUD_COLOROJOS = 10;
	private static final int MAX_LONGITUD_COLORPIEL = 10;
	private static final int MAX_LONGITUD_SEÑAS = 40;
	private static final int MAX_LONGITUD_EXESTUDIANTECTT = 4;
	private static final int MAX_LONGITUD_APELLIDOSFAMILIAR = 20;
	private static final int MAX_LONGITUD_NOMBRESFAMILIAR = 20;
	private static final int MAX_LONGITUD_CEDULAFAMILIAR = 13;
	private static final int MAX_LONGITUD_OCUPACIONFAMILIAR = 70;
	private static final int MAX_LONGITUD_INSTITUCIONFAMILIAR = 40;
	private static final int MAX_LONGITUD_TITULOFORMACION = 70;
	private static final int MAX_LONGITUD_INSTITUCIONFORMACION = 50;
	
	private static final String NOMBRE_SEXO_MASCULINO = "MASCULINO";
	private static final String NOMBRE_SEXO_FEMENINO = "FEMENINO";
	private static final String SEXO_MASCULINO = "M";
	private static final String SEXO_FEMENINO = "F";
	private static final String NOMBRE_ESTADOCIVIL_SOLTERO = "SOLTERO";
	private static final String NOMBRE_ESTADOCIVIL_CASADO = "CASADO";
	private static final String NOMBRE_ESTADOCIVIL_VIUDO = "VIUDO";
	private static final String NOMBRE_ESTADOCIVIL_DIVORCIADO = "DIVORCIADO";
	private static final String NOMBRE_ESTADOCIVIL_UNIONLIBRE = "UNION LIBRE";
	private static final String ESTADOCIVIL_SOLTERO = "S";
	private static final String ESTADOCIVIL_CASADO = "C";
	private static final String ESTADOCIVIL_VIUDO = "V";
	private static final String ESTADOCIVIL_DIVORCIADO = "D";
	private static final String ESTADOCIVIL_UNIONLIBRE = "U";
	/*private static final String NOMBRE_PARENTEZCO_CONYUGE = "CONYUGE";
	private static final String NOMBRE_PARENTEZCO_HIJO = "HIJO";
	private static final String NOMBRE_PARENTEZCO_PADRE = "PADRE";
	private static final String PARENTEZCO_CONYUGE = "C";
	private static final String PARENTEZCO_HIJO = "H";
	private static final String PARENTEZCO_PADRE = "P";*/
	/*private static final String NOMBRE_NIVELESTUDIOS_PRIMARIA = "PRIMARIA";
	private static final String NOMBRE_NIVELESTUDIOS_SECUNDARIA = "SECUNDARIA";
	private static final String NOMBRE_NIVELESTUDIOS_TECNICO = "TECNICO";
	private static final String NOMBRE_NIVELESTUDIOS_UNIVERSIDAD = "UNIVERSIDAD";
	private static final String NOMBRE_NIVELESTUDIOS_POSTGRADO = "POSTGRADO";
	private static final String NIVELESTUDIOS_PRIMARIA = "P";
	private static final String NIVELESTUDIOS_SECUNDARIA = "S";
	private static final String NIVELESTUDIOS_TECNICO = "T";
	private static final String NIVELESTUDIOS_UNIVERSIDAD = "U";
	private static final String NIVELESTUDIOS_POSTGRADO = "G";*/
	private static final String NOMBRE_ULTIMOAÑO_PRIMERO = "PRIMERO";
	private static final String NOMBRE_ULTIMOAÑO_SEGUNDO = "SEGUNDO";
	private static final String NOMBRE_ULTIMOAÑO_TERCERO = "TERCERO";
	private static final String NOMBRE_ULTIMOAÑO_CUARTO = "CUARTO";
	private static final String NOMBRE_ULTIMOAÑO_QUINTO = "QUINTO";
	private static final String NOMBRE_ULTIMOAÑO_SEXTO = "SEXTO";
	private static final String NOMBRE_ULTIMOAÑO_SEPTIMO = "SEPTIMO";
	private static final String NOMBRE_ULTIMOAÑO_GRADUADO = "GRADUADO";
	private static final String ULTIMOAÑO_PRIMERO = "P";
	private static final String ULTIMOAÑO_SEGUNDO = "S";
	private static final String ULTIMOAÑO_TERCERO = "T";
	private static final String ULTIMOAÑO_CUARTO = "C";
	private static final String ULTIMOAÑO_QUINTO = "Q";
	private static final String ULTIMOAÑO_SEXTO = "X";
	private static final String ULTIMOAÑO_SEPTIMO = "E";
	private static final String ULTIMOAÑO_GRADUADO = "G";
	private static final String NOMBRE_TRABAJAFAMILIAR_SI = "SI";
	private static final String NOMBRE_TRABAJAFAMILIAR_NO = "NO";
	private static final String TRABAJAFAMILIAR_SI = "S";
	private static final String TRABAJAFAMILIAR_NO = "N";
	private static final String NOMBRE_EMBARAZOFAMILIAR_SI = "SI";
	private static final String NOMBRE_EMBARAZOFAMILIAR_NO = "NO";
	private static final String EMBARAZOFAMILIAR_SI = "S";
	private static final String EMBARAZOFAMILIAR_NO = "N";
	private static final String NOMBRE_CONOCIMIENTO_EXCELENTE = "EXCELENTE";
	private static final String NOMBRE_CONOCIMIENTO_MUYBUENO = "MUY BUENO";
	private static final String NOMBRE_CONOCIMIENTO_BUENO = "BUENO";
	private static final String NOMBRE_CONOCIMIENTO_REGULAR = "REGULAR";
	private static final String CONOCIMIENTO_EXCELENTE = "E";
	private static final String CONOCIMIENTO_MUYBUENO = "M";
	private static final String CONOCIMIENTO_BUENO = "B";
	private static final String CONOCIMIENTO_REGULAR = "R";
	
	java.util.Date fechaNacimiento = new java.util.Date();
	java.util.Date fechaNacimientoFamiliar = new java.util.Date();
	java.util.Date fechaParto = new java.util.Date();
	java.util.Date fechaGraduado = new java.util.Date();
	Calendar calendarFechaNacimiento = new GregorianCalendar();
	Calendar calendarFechaNacimientoFamiliar = new GregorianCalendar();
	Calendar calendarFechaParto = new GregorianCalendar();
	Calendar calendarFechaGraduado = new GregorianCalendar();
	
	private EmpleadoIf empleadoIf;
	private EmpleadoPersonalIf empleadoPersonalIf;
	
	private boolean actualizarDatoFamiliar = false;
	private DefaultTableModel tableDatosFamiliaresModel;
	private EmpleadoFamiliaresIf empleadoFamiliaresIf;
	private Vector<EmpleadoFamiliaresIf> vectorEmpleadoFamiliaresIf = new Vector<EmpleadoFamiliaresIf>();
	private int empleadoFamiliaresSeleccionado;
	private List<EmpleadoFamiliaresIf> empleadoFamiliaresRemovidos = new ArrayList<EmpleadoFamiliaresIf>();

	private boolean actualizarFormacion = false;
	private DefaultTableModel tableFormacionModel;
	private EmpleadoFormacionIf empleadoFormacionIf;
	private Vector<EmpleadoFormacionIf> vectorEmpleadoFormacionIf = new Vector<EmpleadoFormacionIf>();
	private int empleadoFormacionSeleccionado;
	private List<EmpleadoFormacionIf> empleadoFormacionRemovidos = new ArrayList<EmpleadoFormacionIf>();

	private boolean actualizarIdioma = false;
	private DefaultTableModel tableIdiomaModel;
	private EmpleadoIdiomasIf empleadoIdiomaIf;
	private Vector<EmpleadoIdiomasIf> vectorEmpleadoIdiomaIf = new Vector<EmpleadoIdiomasIf>();
	private int empleadoIdiomaSeleccionado;
	private List<EmpleadoIdiomasIf> empleadoIdiomaRemovidos = new ArrayList<EmpleadoIdiomasIf>();
	private IdiomaIf idioma;
	private PaisIf pais;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	
	public HojaDeVidaModel(){
		iniciarComponentes();
		initKeyListeners();
		iniciarCombosFecha();
		initListeners();
		getTxtCodigo().setEnabled(false);
		getTxtEmpresa().setEnabled(false);
		showSaveMode();
		new HotKeyComponent(this);
	}

	private void iniciarComponentes(){
		
		getBtnImprimirDF().setText("");
		getBtnImprimirDF().setToolTipText("Imprimir Datos Familiares");
		getBtnImprimirDF().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		getBtnImprimirDP().setText("");
		getBtnImprimirDP().setToolTipText("Imprimir Datos Personales");
		getBtnImprimirDP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		getBtnImprimirFA().setText("");
		getBtnImprimirFA().setToolTipText("Imprimir Formacion Academica");
		getBtnImprimirFA().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		getBtnImprimirIdiomas().setText("");
		getBtnImprimirIdiomas().setToolTipText("Imprimir Idiomas");
		getBtnImprimirIdiomas().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		
		getBtnAgregarDatoFamiliar().setText("");
		getBtnAgregarDatoFamiliar().setToolTipText("Agregar Dato Familiar");
		getBtnAgregarDatoFamiliar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDatoFamiliar().setText("");
		getBtnActualizarDatoFamiliar().setToolTipText("Actualizar Dato Familiar");
		getBtnActualizarDatoFamiliar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverDatoFamiliar().setText("");
		getBtnRemoverDatoFamiliar().setToolTipText("Eliminar Dato Familiar");
		getBtnRemoverDatoFamiliar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		
		getBtnAgregarFormacion().setText("");
		getBtnAgregarFormacion().setToolTipText("Agregar Formacion");
		getBtnAgregarFormacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarFormacion().setText("");
		getBtnActualizarFormacion().setToolTipText("Actualizar Formacion");
		getBtnActualizarFormacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverFormacion().setText("");
		getBtnRemoverFormacion().setToolTipText("Eliminar Formacion");
		getBtnRemoverFormacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		
		getBtnAgregarIdioma().setText("");
		getBtnAgregarIdioma().setToolTipText("Agregar Idioma");
		getBtnAgregarIdioma().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarIdioma().setText("");
		getBtnActualizarIdioma().setToolTipText("Actualizar Idioma");
		getBtnActualizarIdioma().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverIdioma().setText("");
		getBtnRemoverIdioma().setToolTipText("Eliminar Idioma");
		getBtnRemoverIdioma().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		
	}
	
	private void initKeyListeners() {

		getCmbParentezco().addActionListener(cmbParentezcoListener);
		getCmbEsposaEmbarazada().addActionListener(cmbEsposaEmbarazadaListener);
		getCmbPais().addActionListener(cmbPaisListener);
		getTblDatosFamiliares().addMouseListener(oMouseAdapterTblDatosFamiliares);
		getTblDatosFamiliares().addKeyListener(oKeyAdapterTblDatosFamiliares);
		getTblFormacion().addMouseListener(oMouseAdapterTblFormacion);
		getTblFormacion().addKeyListener(oKeyAdapterTblFormacion);
		getTblIdioma().addMouseListener(oMouseAdapterTblIdioma);
		getTblIdioma().addKeyListener(oKeyAdapterTblIdioma);
		
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtApellidoPaterno().addKeyListener(new TextChecker(MAX_LONGITUD_APELLIDOPATERNO));
		getTxtApellidoMaterno().addKeyListener(new TextChecker(MAX_LONGITUD_APELLIDOMATERNO));
		getTxtNombres().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRES));
		getTxtTitulo().addKeyListener(new TextChecker(MAX_LONGITUD_TITULO));
		getTxtProfesion().addKeyListener(new TextChecker(MAX_LONGITUD_PROFESION));
		getTxtTipoSangre().addKeyListener(new TextChecker(MAX_LONGITUD_TIPOSANGRE));
		getTxtCedula().addKeyListener(new TextChecker(MAX_LONGITUD_CEDULA));
		getTxtNoIESS().addKeyListener(new TextChecker(MAX_LONGITUD_NUMEROIESS));
		getTxtLibMilitar().addKeyListener(new TextChecker(MAX_LONGITUD_LIBRETAMILITAR));
		getTxtCanton().addKeyListener(new TextChecker(MAX_LONGITUD_CANTON));
		getTxtParroquia().addKeyListener(new TextChecker(MAX_LONGITUD_PARROQUIA));
		getTxtDomicilio().addKeyListener(new TextChecker(MAX_LONGITUD_DOMICILIO));
		getTxtTelefonoDomicilio().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getTxtCelular().addKeyListener(new TextChecker(MAX_LONGITUD_CELULAR));
		getTxtEmergencia().addKeyListener(new TextChecker(MAX_LONGITUD_EMERGENCIA));
		getTxtTelefonoEmergencia().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONOEMERGENCIA));
		getTxtCanton().addKeyListener(new TextChecker(MAX_LONGITUD_CANTON));
		getTxtParroquia().addKeyListener(new TextChecker(MAX_LONGITUD_PARROQUIA));
		getTxtDireccionEmergencia().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCIONEMERGENCIA));
		getTxtTallaCamisa().addKeyListener(new TextChecker(MAX_LONGITUD_TALLACAMISA));
		getTxtTallaPantalon().addKeyListener(new TextChecker(MAX_LONGITUD_TALLAPANTALON));
		getTxtNumeroCalzado().addKeyListener(new TextChecker(MAX_LONGITUD_NUMEROCALZADO));
		getTxtEstatura().addKeyListener(new TextChecker(MAX_LONGITUD_ESTATURA));
		getTxtEstatura().addKeyListener(new NumberTextFieldDecimal());
		getTxtPeso().addKeyListener(new TextChecker(MAX_LONGITUD_PESO));
		getTxtPeso().addKeyListener(new NumberTextFieldDecimal());
		getTxtColorPelo().addKeyListener(new TextChecker(MAX_LONGITUD_COLORPELO));
		getTxtColorOjos().addKeyListener(new TextChecker(MAX_LONGITUD_COLOROJOS));
		getTxtColorPiel().addKeyListener(new TextChecker(MAX_LONGITUD_COLORPIEL));
		getTxtSeniasParticulares().addKeyListener(new TextChecker(MAX_LONGITUD_SEÑAS));
		getTxtExEstudianteCTT().addKeyListener(new TextChecker(MAX_LONGITUD_EXESTUDIANTECTT));
		getTxtExEstudianteCTT().addKeyListener(new NumberTextFieldDecimal());
		getTxtApellidosFamiliar().addKeyListener(new TextChecker(MAX_LONGITUD_APELLIDOSFAMILIAR));
		getTxtNombresFamiliar().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRESFAMILIAR));
		getTxtCedulaFamiliar().addKeyListener(new TextChecker(MAX_LONGITUD_CEDULAFAMILIAR));
		getTxtOcupacionFamiliar().addKeyListener(new TextChecker(MAX_LONGITUD_OCUPACIONFAMILIAR));
		getTxtNombreInstitucionFamiliar().addKeyListener(new TextChecker(MAX_LONGITUD_INSTITUCIONFAMILIAR));
		getTxtTituloFormacion().addKeyListener(new TextChecker(MAX_LONGITUD_TITULOFORMACION));
		getTxtInstitucionFormacion().addKeyListener(new TextChecker(MAX_LONGITUD_INSTITUCIONFORMACION));
	}
	
	public void iniciarCombosFecha(){
		getCmbFechaNacimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaNacimientoFamiliar().setLocale(Utilitarios.esLocale);
		getCmbFechaParto().setLocale(Utilitarios.esLocale);
		getCmbFechaGraduado().setLocale(Utilitarios.esLocale);
	
		calendarFechaNacimiento.setTime(fechaNacimiento);
		getCmbFechaNacimiento().setCalendar(calendarFechaNacimiento);
		calendarFechaNacimientoFamiliar.setTime(fechaNacimientoFamiliar);
		getCmbFechaNacimientoFamiliar().setCalendar(calendarFechaNacimientoFamiliar);
		calendarFechaParto.setTime(fechaParto);
		getCmbFechaParto().setCalendar(calendarFechaParto);
		calendarFechaGraduado.setTime(fechaGraduado);
		getCmbFechaGraduado().setCalendar(calendarFechaGraduado);
		
		getCmbFechaNacimiento().setShowNoneButton(false);
		getCmbFechaNacimientoFamiliar().setShowNoneButton(false);
		getCmbFechaParto().setShowNoneButton(false);
		getCmbFechaGraduado().setShowNoneButton(false);
		getCmbFechaNacimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaNacimientoFamiliar().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaParto().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaGraduado().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaNacimiento().setEditable(false);
		getCmbFechaNacimientoFamiliar().setEditable(false);
		getCmbFechaParto().setEditable(false);
		getCmbFechaGraduado().setEditable(false);
	}
	
	ActionListener cmbParentezcoListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			ParentezcoEnum parentezco = (ParentezcoEnum) getCmbParentezco().getSelectedItem();
			if(parentezco == ParentezcoEnum.HIJO || parentezco == ParentezcoEnum.PADRE ||
				parentezco == ParentezcoEnum.MADRE ){
				getLblTrabajaFamiliar().setVisible(false);
				getCmbTrabajaFamiliar().setVisible(false);
				getLblNombreInstitucionFamiliar().setVisible(false);
				getTxtNombreInstitucionFamiliar().setVisible(false);
				getLblEsposaEmbarazada().setVisible(false);
				getCmbEsposaEmbarazada().setVisible(false);
				getLblFechaParto().setVisible(false);
				getCmbFechaParto().setVisible(false);
			}
			else{
				getLblTrabajaFamiliar().setVisible(true);
				getCmbTrabajaFamiliar().setVisible(true);
				getLblNombreInstitucionFamiliar().setVisible(true);
				getTxtNombreInstitucionFamiliar().setVisible(true);
				getLblEsposaEmbarazada().setVisible(true);
				getCmbEsposaEmbarazada().setVisible(true);
				if(getCmbEsposaEmbarazada().getItemCount() > 0) getCmbEsposaEmbarazada().setSelectedIndex(0);
			}
		}
	};
	
	ActionListener cmbEsposaEmbarazadaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbEsposaEmbarazada().getSelectedItem().equals(NOMBRE_EMBARAZOFAMILIAR_SI)){
				getLblFechaParto().setVisible(true);
				getCmbFechaParto().setVisible(true);
			}
			else{
				getLblFechaParto().setVisible(false);
				getCmbFechaParto().setVisible(false);
			}
		}
	};
	
	ActionListener cmbPaisListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCmbPais().getSelectedItem() != null) {
				pais = (PaisIf) getCmbPais().getSelectedItem();
				cargarComboProvincia();
			} else {
				pais = null;
				getCmbProvincia().removeAllItems();
			}
		}
	};
	
	MouseListener oMouseAdapterTblDatosFamiliares = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedDatoFamiliarRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblDatosFamiliares = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedDatoFamiliarRowForUpdate(evt);
		}
	};
	
	private void enableSelectedDatoFamiliarRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			setEmpleadoFamiliaresSeleccionado(((JTable) evt.getSource()).getSelectedRow());
			empleadoFamiliaresIf = (EmpleadoFamiliaresIf) getVectorEmpleadoFamiliaresIf().get(getEmpleadoFamiliaresSeleccionado());

			String parentezcoLetra = empleadoFamiliaresIf.getTipo();
			try {
				ParentezcoEnum parentezco = ParentezcoEnum.getParentezcoByLetra(parentezcoLetra);
				getCmbParentezco().setSelectedItem(parentezco);
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				getCmbParentezco().setSelectedItem(null);
				SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
			}
			
			/*if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_CONYUGE))
				getCmbParentezco().setSelectedItem(NOMBRE_PARENTEZCO_CONYUGE);
			else if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_HIJO))
				getCmbParentezco().setSelectedItem(NOMBRE_PARENTEZCO_HIJO);
			else if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_PADRE))
				getCmbParentezco().setSelectedItem(NOMBRE_PARENTEZCO_PADRE);*/
			
			getTxtApellidosFamiliar().setText(empleadoFamiliaresIf.getApellidos());
			getTxtNombresFamiliar().setText(empleadoFamiliaresIf.getNombres());
			calendarFechaNacimientoFamiliar.setTime(empleadoFamiliaresIf.getFechaNacimiento());
			getCmbFechaNacimientoFamiliar().setCalendar(calendarFechaNacimientoFamiliar);
			getTxtCedulaFamiliar().setText(empleadoFamiliaresIf.getCedulaIdentidad());
			getTxtOcupacionFamiliar().setText(empleadoFamiliaresIf.getOcupacion());
			
			String nivelLetra = empleadoFamiliaresIf.getNivelEstudios();
			NivelEstudioEnum nivel;
			try {
				nivel = NivelEstudioEnum.getNivelEstudioByLetra(nivelLetra);
				getCmbNivelEstudiosFamiliar().setSelectedItem(nivel);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				getCmbNivelEstudiosFamiliar().setSelectedItem(null);
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} 
			
			
			/*if(empleadoFamiliaresIf.getNivelEstudios().equals(NIVELESTUDIOS_PRIMARIA))
				getCmbNivelEstudiosFamiliar().setSelectedItem(NOMBRE_NIVELESTUDIOS_PRIMARIA);
			else if(empleadoFamiliaresIf.getNivelEstudios().equals(NIVELESTUDIOS_SECUNDARIA))
				getCmbNivelEstudiosFamiliar().setSelectedItem(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
			else if(empleadoFamiliaresIf.getNivelEstudios().equals(NIVELESTUDIOS_TECNICO))
				getCmbNivelEstudiosFamiliar().setSelectedItem(NOMBRE_NIVELESTUDIOS_TECNICO);
			else if(empleadoFamiliaresIf.getNivelEstudios().equals(NIVELESTUDIOS_UNIVERSIDAD))
				getCmbNivelEstudiosFamiliar().setSelectedItem(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
			else if(empleadoFamiliaresIf.getNivelEstudios().equals(NIVELESTUDIOS_POSTGRADO))
				getCmbNivelEstudiosFamiliar().setSelectedItem(NOMBRE_NIVELESTUDIOS_POSTGRADO);*/
			
			if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_PRIMERO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_PRIMERO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_SEGUNDO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_SEGUNDO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_TERCERO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_TERCERO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_CUARTO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_CUARTO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_QUINTO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_QUINTO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_SEXTO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_SEXTO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_SEPTIMO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_SEPTIMO);
			else if(empleadoFamiliaresIf.getUltimoAnio().equals(ULTIMOAÑO_GRADUADO))
				getCmbAnioFamiliar().setSelectedItem(NOMBRE_ULTIMOAÑO_GRADUADO);
			
			parentezcoLetra = empleadoFamiliaresIf.getTipo();
			if(parentezcoLetra.equals(ParentezcoEnum.CONYUGUE.getLetra())){
				
				if(empleadoFamiliaresIf.getTrabaja().equals(TRABAJAFAMILIAR_SI))
					getCmbTrabajaFamiliar().setSelectedItem(NOMBRE_TRABAJAFAMILIAR_SI);
				else if(empleadoFamiliaresIf.getTrabaja().equals(TRABAJAFAMILIAR_NO))
					getCmbTrabajaFamiliar().setSelectedItem(NOMBRE_TRABAJAFAMILIAR_NO);
				
				getTxtNombreInstitucionFamiliar().setText(empleadoFamiliaresIf.getNombreInstitucion());
				
				if(empleadoFamiliaresIf.getEmbarazo().equals(EMBARAZOFAMILIAR_NO))
					getCmbEsposaEmbarazada().setSelectedItem(NOMBRE_EMBARAZOFAMILIAR_NO);
				else{
					getCmbEsposaEmbarazada().setSelectedItem(NOMBRE_EMBARAZOFAMILIAR_SI);
					calendarFechaParto.setTime(empleadoFamiliaresIf.getFechaParto());
					getCmbFechaParto().setCalendar(calendarFechaParto);
				}
			}
		}
	}
	
	MouseListener oMouseAdapterTblFormacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedFormacionRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblFormacion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedFormacionRowForUpdate(evt);
		}
	};
	
	private void enableSelectedFormacionRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			setEmpleadoFormacionSeleccionado(((JTable) evt.getSource()).getSelectedRow());
			empleadoFormacionIf = (EmpleadoFormacionIf) getVectorEmpleadoFormacionIf().get(getEmpleadoFormacionSeleccionado());

			String nivelLetra = empleadoFormacionIf.getNivel();
			NivelEstudioEnum nivel;
			try {
				nivel = NivelEstudioEnum.getNivelEstudioByLetra(nivelLetra);
				getCmbNivelFormacion().setSelectedItem(nivel);
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				getCmbNivelFormacion().setSelectedItem(null);
				SpiritAlert.createAlert(e1.getLocalizedMessage(), SpiritAlert.ERROR);
			}
			
			/*if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_PRIMARIA))
				getCmbNivelFormacion().setSelectedItem(NOMBRE_NIVELESTUDIOS_PRIMARIA);
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_SECUNDARIA))
				getCmbNivelFormacion().setSelectedItem(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_TECNICO))
				getCmbNivelFormacion().setSelectedItem(NOMBRE_NIVELESTUDIOS_TECNICO);
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_UNIVERSIDAD))
				getCmbNivelFormacion().setSelectedItem(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_POSTGRADO))
				getCmbNivelFormacion().setSelectedItem(NOMBRE_NIVELESTUDIOS_POSTGRADO);*/
			
			if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_PRIMERO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_PRIMERO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEGUNDO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_SEGUNDO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_TERCERO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_TERCERO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_CUARTO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_CUARTO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_QUINTO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_QUINTO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEXTO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_SEXTO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEPTIMO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_SEPTIMO);
			else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_GRADUADO))
				getCmbAnioFormacion().setSelectedItem(NOMBRE_ULTIMOAÑO_GRADUADO);
			
			calendarFechaGraduado.setTime(empleadoFormacionIf.getFechaGraduacion());
			getCmbFechaGraduado().setCalendar(calendarFechaGraduado);
			getCmbFechaGraduado().repaint();
			getTxtTituloFormacion().setText(empleadoFormacionIf.getTituloObtenido());
			getTxtInstitucionFormacion().setText(empleadoFormacionIf.getInstitucion());
			
			try {
				CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(empleadoFormacionIf.getCiudadId());
				ProvinciaIf provincia = SessionServiceLocator.getProvinciaSessionService().getProvincia(ciudad.getProvinciaId());
				PaisIf pais = SessionServiceLocator.getPaisSessionService().getPais(provincia.getPaisId());
				
				getCmbPaisFormacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPaisFormacion(), pais.getId()));
				getCmbPaisFormacion().repaint();				
				getCmbCiudadFormacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCiudadFormacion(), ciudad.getId()));
				getCmbCiudadFormacion().repaint();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	MouseListener oMouseAdapterTblIdioma = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedIdiomaRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblIdioma = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedIdiomaRowForUpdate(evt);
		}
	};
	
	private void enableSelectedIdiomaRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			setEmpleadoIdiomaSeleccionado(((JTable) evt.getSource()).getSelectedRow());
			empleadoIdiomaIf = (EmpleadoIdiomasIf) getVectorEmpleadoIdiomaIf().get(getEmpleadoIdiomaSeleccionado());

			getCmbIdioma().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbIdioma(), empleadoIdiomaIf.getIdiomaId()));
			getCmbIdioma().repaint();
			
			if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_REGULAR))
				getCmbHabla().setSelectedItem(NOMBRE_CONOCIMIENTO_REGULAR);
			else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_BUENO))
				getCmbHabla().setSelectedItem(NOMBRE_CONOCIMIENTO_BUENO);
			else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_MUYBUENO))
				getCmbHabla().setSelectedItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_EXCELENTE))
				getCmbHabla().setSelectedItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			
			if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_REGULAR))
				getCmbComprende().setSelectedItem(NOMBRE_CONOCIMIENTO_REGULAR);
			else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_BUENO))
				getCmbComprende().setSelectedItem(NOMBRE_CONOCIMIENTO_BUENO);
			else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_MUYBUENO))
				getCmbComprende().setSelectedItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_EXCELENTE))
				getCmbComprende().setSelectedItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			
			if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_REGULAR))
				getCmbLee().setSelectedItem(NOMBRE_CONOCIMIENTO_REGULAR);
			else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_BUENO))
				getCmbLee().setSelectedItem(NOMBRE_CONOCIMIENTO_BUENO);
			else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_MUYBUENO))
				getCmbLee().setSelectedItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_EXCELENTE))
				getCmbLee().setSelectedItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
		
			if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_REGULAR))
				getCmbEscribe().setSelectedItem(NOMBRE_CONOCIMIENTO_REGULAR);
			else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_BUENO))
				getCmbEscribe().setSelectedItem(NOMBRE_CONOCIMIENTO_BUENO);
			else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_MUYBUENO))
				getCmbEscribe().setSelectedItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_EXCELENTE))
				getCmbEscribe().setSelectedItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
		}
	}
	
	private void initListeners() {
		
		
		//BOTON BUSQUEDA DE CONTRATO
		getBtnImprimirDP().setText("");
		getBtnImprimirDP().setToolTipText("Imprimir Datos Personales");
		getBtnImprimirDP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		
		getBtnImprimirDF().setText("");
		getBtnImprimirDF().setToolTipText("Imprimir Datos Familiares");
		getBtnImprimirDF().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		
		getBtnImprimirFA().setText("");
		getBtnImprimirFA().setToolTipText("Imprimir Formacón Académica");
		getBtnImprimirFA().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		
		getBtnImprimirIdiomas().setText("");
		getBtnImprimirIdiomas().setToolTipText("Imprimir Idiomas");
		getBtnImprimirIdiomas().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/printer.png"));
		
		//BOTON BUSQUEDA DE CONTRATO
		getBtnEmpleado().setText("");	
		getBtnEmpleado().setToolTipText("Buscar empleado");
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		
		getBtnImprimirDP().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report_DatosPersonales();        
			}
		});
		
		getBtnImprimirDF().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report_DatosFamiliares();        
			}
		});
		
		getBtnImprimirFA().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report_DatosAcademicas();        
			}
		});
		
		getBtnImprimirIdiomas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report_DatosIdiomas();        
			}
		});

		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarEmpleado();        
			}
		});
		
		getBtnAgregarDatoFamiliar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDatoFamiliar();
			}
		});
		
		getBtnActualizarDatoFamiliar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDatoFamiliar();
			}
		});
		
		getBtnRemoverDatoFamiliar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				removerDatoFamiliar();
			}
		});
		
		getBtnAgregarFormacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarFormacion();
			}
		});
		getBtnActualizarFormacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarFormacion();
			}
		});
		getBtnRemoverFormacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				removerFormacion();
			}
		});
		
		getBtnAgregarIdioma().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarIdioma();
			}
		});
		getBtnActualizarIdioma().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarIdioma();
			}
		});
		getBtnRemoverIdioma().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				removerIdioma();
			}
		});
	}
	
	private void buscarEmpleado() {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
								empleadoCriteria,
								JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			try {
				getTxtCodigo().setText(empleadoIf.getCodigo());
				EmpresaIf empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresa(empleadoIf.getEmpresaId());
				getTxtEmpresa().setText(empresa.getCodigo() + " - " + empresa.getNombre());
				getTxtApellidoPaterno().setText(empleadoIf.getApellidos().split(" ")[0]);
				if(empleadoIf.getApellidos().split(" ").length > 1)
					getTxtApellidoMaterno().setText(empleadoIf.getApellidos().split(" ")[1]);
				getTxtNombres().setText(empleadoIf.getNombres());
				getTxtProfesion().setText(empleadoIf.getProfesion());
				getTxtDomicilio().setText(empleadoIf.getDireccionDomicilio());
				getTxtTelefonoDomicilio().setText(empleadoIf.getTelefonoDomicilio());
				getTxtCelular().setText(empleadoIf.getCelular());
				
				getTxtCedula().setText(empleadoIf.getIdentificacion());
				
				empleadoPersonalIf = null;
				Collection<EmpleadoPersonalIf> empleadosPersonal = SessionServiceLocator.getEmpleadoPersonalSessionService().findEmpleadoPersonalByEmpleadoId(empleadoIf.getId()); 
				if ( empleadosPersonal.size() == 1 ){
					empleadoPersonalIf = empleadosPersonal.iterator().next();
				} else {
					if ( empleadosPersonal.size() > 1 ){
						SpiritAlert.createAlert("Existe mas de un registro personal para el empleado !!", SpiritAlert.ERROR);
						return;
					}	
				}
				
				if(empleadoPersonalIf != null){
					getTxtTitulo().setText(empleadoPersonalIf.getTitulo());
					//getTxtCedula().setText(empleadoPersonalIf.getCedulaIdentidad());
					getTxtNoIESS().setText(empleadoPersonalIf.getAfiliacionIess());
					getTxtLibMilitar().setText(empleadoPersonalIf.getLibretaMilitar());
					
					if(empleadoPersonalIf.getSexo().equals(SEXO_MASCULINO))
						getCmbSexo().setSelectedItem(NOMBRE_SEXO_MASCULINO);
					else if(empleadoPersonalIf.getSexo().equals(SEXO_FEMENINO))
						getCmbSexo().setSelectedItem(NOMBRE_SEXO_FEMENINO);
					
					getTxtTipoSangre().setText(empleadoPersonalIf.getTipoSangre());
					
					if(empleadoPersonalIf.getEstadoCivil().equals(ESTADOCIVIL_CASADO))
						getCmbEstadoCivil().setSelectedItem(NOMBRE_ESTADOCIVIL_CASADO);
					else if(empleadoPersonalIf.getEstadoCivil().equals(ESTADOCIVIL_SOLTERO))
						getCmbEstadoCivil().setSelectedItem(NOMBRE_ESTADOCIVIL_SOLTERO);
					else if(empleadoPersonalIf.getEstadoCivil().equals(ESTADOCIVIL_VIUDO))
						getCmbEstadoCivil().setSelectedItem(NOMBRE_ESTADOCIVIL_VIUDO);
					else if(empleadoPersonalIf.getEstadoCivil().equals(ESTADOCIVIL_DIVORCIADO))
						getCmbEstadoCivil().setSelectedItem(NOMBRE_ESTADOCIVIL_DIVORCIADO);
					else if(empleadoPersonalIf.getEstadoCivil().equals(ESTADOCIVIL_UNIONLIBRE))
						getCmbEstadoCivil().setSelectedItem(NOMBRE_ESTADOCIVIL_UNIONLIBRE);
					
					calendarFechaNacimiento.setTime(empleadoPersonalIf.getFechaNacimiento());
					getCmbFechaNacimiento().setCalendar(calendarFechaNacimiento);
					
					getCmbPais().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPais(), empleadoPersonalIf.getPaisId()));
					getCmbPais().repaint();
					if (empleadoPersonalIf.getProvinciaId()!=null){
						getCmbProvincia().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbProvincia(), empleadoPersonalIf.getProvinciaId()));
						getCmbProvincia().repaint();
					}
					if (empleadoPersonalIf.getCiudadId()!=null){
						getCmbCiudad().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCiudad(), empleadoPersonalIf.getCiudadId()));
						getCmbCiudad().repaint();
					}
					getCmbCiudadEmergencia().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCiudadEmergencia(), empleadoPersonalIf.getCiudadEmergenciaId()));
					getCmbCiudadEmergencia().repaint();
					if ( empleadoPersonalIf.getCanton()!=null)
						getTxtCanton().setText(empleadoPersonalIf.getCanton());
					if (empleadoPersonalIf.getParroquia()!=null)
						getTxtParroquia().setText(empleadoPersonalIf.getParroquia());
					getTxtEmergencia().setText(empleadoPersonalIf.getCasoEmergencia());
					getTxtTelefonoEmergencia().setText(empleadoPersonalIf.getTelefonoEmergencia());
					getTxtDireccionEmergencia().setText(empleadoPersonalIf.getDireccionEmergencia());
					getTxtTallaCamisa().setText(empleadoPersonalIf.getTallaCamisa());
					getTxtTallaPantalon().setText(empleadoPersonalIf.getTallaPantalon());
					getTxtNumeroCalzado().setText(empleadoPersonalIf.getNumeroCalzado());
					getTxtEstatura().setText(empleadoPersonalIf.getEstatura());
					getTxtPeso().setText(empleadoPersonalIf.getPeso());
					getTxtColorPelo().setText(empleadoPersonalIf.getColorPelo());
					getTxtColorOjos().setText(empleadoPersonalIf.getColorOjos());
					getTxtColorPiel().setText(empleadoPersonalIf.getColorPiel());
					getTxtSeniasParticulares().setText(empleadoPersonalIf.getSenasParticulares());
					getTxtExEstudianteCTT().setText(empleadoPersonalIf.getExestudianteCtt());
					
				}
				
				
				Collection<EmpleadoFamiliaresIf> empleadoFamiliares = SessionServiceLocator.getEmpleadoFamiliaresSessionService().findEmpleadoFamiliaresByEmpleadoId(empleadoIf.getId());
				tableDatosFamiliaresModel = (DefaultTableModel) getTblDatosFamiliares().getModel();
				for (EmpleadoFamiliaresIf empleadoFamiliaresIf : empleadoFamiliares){
					vectorEmpleadoFamiliaresIf.add(empleadoFamiliaresIf);
					Vector<String> fila = new Vector<String>();
					fila.add(empleadoFamiliaresIf.getNombres() + " " + empleadoFamiliaresIf.getApellidos());
					fila.add(Utilitarios.getFechaUppercase(empleadoFamiliaresIf.getFechaNacimiento()));
					fila.add(empleadoFamiliaresIf.getCedulaIdentidad());
					fila.add(empleadoFamiliaresIf.getOcupacion());
					tableDatosFamiliaresModel.addRow(fila);
				}

				Collection<EmpleadoFormacionIf> empleadoFormaciones = SessionServiceLocator.getEmpleadoFormacionSessionService().findEmpleadoFormacionByEmpleadoId(empleadoIf.getId());
				tableFormacionModel = (DefaultTableModel) getTblFormacion().getModel();
				for (EmpleadoFormacionIf empleadoFormacionIf : empleadoFormaciones){
					vectorEmpleadoFormacionIf.add(empleadoFormacionIf);

					Vector<String> fila = new Vector<String>();

					String nivelLetra = empleadoFormacionIf.getNivel();
					NivelEstudioEnum nivel = NivelEstudioEnum.getNivelEstudioByLetra(nivelLetra);
					fila.add(nivel.toString());
					
					/*if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_PRIMARIA))
						fila.add(NOMBRE_NIVELESTUDIOS_PRIMARIA);
					else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_SECUNDARIA))
						fila.add(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
					else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_TECNICO))
						fila.add(NOMBRE_NIVELESTUDIOS_TECNICO);
					else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_UNIVERSIDAD))
						fila.add(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
					else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_POSTGRADO))
						fila.add(NOMBRE_NIVELESTUDIOS_POSTGRADO);*/

					if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_PRIMERO))
						fila.add(NOMBRE_ULTIMOAÑO_PRIMERO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEGUNDO))
						fila.add(NOMBRE_ULTIMOAÑO_SEGUNDO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_TERCERO))
						fila.add(NOMBRE_ULTIMOAÑO_TERCERO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_CUARTO))
						fila.add(NOMBRE_ULTIMOAÑO_CUARTO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_QUINTO))
						fila.add(NOMBRE_ULTIMOAÑO_QUINTO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEXTO))
						fila.add(NOMBRE_ULTIMOAÑO_SEXTO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_SEPTIMO))
						fila.add(NOMBRE_ULTIMOAÑO_SEPTIMO);
					else if(empleadoFormacionIf.getUltimoAnio().equals(ULTIMOAÑO_GRADUADO))
						fila.add(NOMBRE_ULTIMOAÑO_GRADUADO);

					fila.add(Utilitarios.getFechaUppercase(empleadoFormacionIf.getFechaGraduacion()));
					fila.add(empleadoFormacionIf.getTituloObtenido());
					fila.add(empleadoFormacionIf.getInstitucion());

					tableFormacionModel.addRow(fila);
				}

				Collection empleadoIdioma = SessionServiceLocator.getEmpleadoIdiomasSessionService().findEmpleadoIdiomasByEmpleadoId(empleadoIf.getId());
				Iterator empleadoIdiomaIterator = empleadoIdioma.iterator();

				while(empleadoIdiomaIterator.hasNext()){
					empleadoIdiomaIf = (EmpleadoIdiomasIf) empleadoIdiomaIterator.next();

					vectorEmpleadoIdiomaIf.add(empleadoIdiomaIf);

					tableIdiomaModel = (DefaultTableModel) getTblIdioma().getModel();

					Vector<String> fila = new Vector<String>();
					idioma = SessionServiceLocator.getIdiomaSessionService().getIdioma(empleadoIdiomaIf.getIdiomaId());

					fila.add(idioma.getNombre());

					if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_REGULAR))
						fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
					else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_BUENO))
						fila.add(NOMBRE_CONOCIMIENTO_BUENO);
					else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_MUYBUENO))
						fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
					else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_EXCELENTE))
						fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);

					if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_REGULAR))
						fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
					else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_BUENO))
						fila.add(NOMBRE_CONOCIMIENTO_BUENO);
					else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_MUYBUENO))
						fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
					else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_EXCELENTE))
						fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);

					if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_REGULAR))
						fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
					else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_BUENO))
						fila.add(NOMBRE_CONOCIMIENTO_BUENO);
					else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_MUYBUENO))
						fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
					else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_EXCELENTE))
						fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);

					if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_REGULAR))
						fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
					else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_BUENO))
						fila.add(NOMBRE_CONOCIMIENTO_BUENO);
					else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_MUYBUENO))
						fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
					else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_EXCELENTE))
						fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);

					tableIdiomaModel.addRow(fila);
				}
				
				getBtnEmpleado().setEnabled(false);
				
				showUpdateMode();
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private void agregarDatoFamiliar() {
		setActualizarDatoFamiliar(false);
		if (validateDatoFamiliarFields()) {
			tableDatosFamiliaresModel = (DefaultTableModel) getTblDatosFamiliares().getModel();

			//Vector EmpleadoFamiliares para luego guardar en la base
			EmpleadoFamiliaresData bean = new EmpleadoFamiliaresData();
			setEmpleadoFamiliaresIf(bean);

			setearDatosFamiliares();
						
			vectorEmpleadoFamiliaresIf.add(getEmpleadoFamiliaresIf());

			// Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();

			agregarFilaTablaDatosFamiliares(fila);
					
			tableDatosFamiliaresModel.addRow(fila);
			cleanDatosFamiliares();
		}
	}

	private void actualizarDatoFamiliar() {
		setActualizarDatoFamiliar(true);
		int filaSeleccionada = getTblDatosFamiliares().getSelectedRow();
		if (filaSeleccionada >= 0) {
			if (validateDatoFamiliarFields()) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
	    		if (opcion == JOptionPane.YES_OPTION) {
					getEmpleadoFamiliaresIf().setId(empleadoFamiliaresIf.getId());
					setearDatosFamiliares();

					vectorEmpleadoFamiliaresIf.add(filaSeleccionada, getEmpleadoFamiliaresIf());
					vectorEmpleadoFamiliaresIf.remove(filaSeleccionada + 1);

					Vector<String> fila = new Vector<String>();

					agregarFilaTablaDatosFamiliares(fila);

					tableDatosFamiliaresModel.insertRow(filaSeleccionada, fila);
					tableDatosFamiliaresModel.removeRow(filaSeleccionada + 1);

					cleanDatosFamiliares();
				}
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void removerDatoFamiliar() {
		setActualizarDatoFamiliar(true);
		int filaSeleccionada = getTblDatosFamiliares().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				EmpleadoFamiliaresIf familiar = (EmpleadoFamiliaresIf)vectorEmpleadoFamiliaresIf.get(filaSeleccionada);
				if (familiar.getId()!=null)
					empleadoFamiliaresRemovidos.add(familiar);
				vectorEmpleadoFamiliaresIf.remove(filaSeleccionada);
				tableDatosFamiliaresModel.removeRow(filaSeleccionada);
			}
			cleanDatosFamiliares();
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}

	private void setearDatosFamiliares() {
		ParentezcoEnum parentezco = (ParentezcoEnum) getCmbParentezco().getSelectedItem();
		getEmpleadoFamiliaresIf().setTipo(parentezco.getLetra());
		/*if(getCmbParentezco().getSelectedItem().equals(NOMBRE_PARENTEZCO_CONYUGE))
			getEmpleadoFamiliaresIf().setTipo(PARENTEZCO_CONYUGE);
		else if(getCmbParentezco().getSelectedItem().equals(NOMBRE_PARENTEZCO_HIJO))
			getEmpleadoFamiliaresIf().setTipo(PARENTEZCO_HIJO);
		else if(getCmbParentezco().getSelectedItem().equals(NOMBRE_PARENTEZCO_PADRE))
			getEmpleadoFamiliaresIf().setTipo(PARENTEZCO_PADRE);*/
		
		getEmpleadoFamiliaresIf().setApellidos(getTxtApellidosFamiliar().getText());
		getEmpleadoFamiliaresIf().setNombres(getTxtNombresFamiliar().getText());
		getEmpleadoFamiliaresIf().setFechaNacimiento(DateHelperClient.getTimeStamp(getCmbFechaNacimientoFamiliar().getDate()));
		getEmpleadoFamiliaresIf().setCedulaIdentidad(getTxtCedulaFamiliar().getText());
		getEmpleadoFamiliaresIf().setOcupacion(getTxtOcupacionFamiliar().getText());
		
		
		NivelEstudioEnum nivel = (NivelEstudioEnum) getCmbNivelEstudiosFamiliar().getSelectedItem();
		getEmpleadoFamiliaresIf().setNivelEstudios(nivel.getLetra());
		
		/*if(getCmbNivelEstudiosFamiliar().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_PRIMARIA))
			getEmpleadoFamiliaresIf().setNivelEstudios(NIVELESTUDIOS_PRIMARIA);
		else if(getCmbNivelEstudiosFamiliar().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_SECUNDARIA))
			getEmpleadoFamiliaresIf().setNivelEstudios(NIVELESTUDIOS_SECUNDARIA);
		else if(getCmbNivelEstudiosFamiliar().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_TECNICO))
			getEmpleadoFamiliaresIf().setNivelEstudios(NIVELESTUDIOS_TECNICO);
		else if(getCmbNivelEstudiosFamiliar().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD))
			getEmpleadoFamiliaresIf().setNivelEstudios(NIVELESTUDIOS_UNIVERSIDAD);
		else if(getCmbNivelEstudiosFamiliar().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_POSTGRADO))
			getEmpleadoFamiliaresIf().setNivelEstudios(NIVELESTUDIOS_POSTGRADO);*/
		
		if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_PRIMERO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_PRIMERO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEGUNDO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_SEGUNDO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_TERCERO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_TERCERO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_CUARTO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_CUARTO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_QUINTO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_QUINTO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEXTO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_SEXTO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEPTIMO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_SEPTIMO);
		else if(getCmbAnioFamiliar().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_GRADUADO))
			getEmpleadoFamiliaresIf().setUltimoAnio(ULTIMOAÑO_GRADUADO);
		
		//if(getCmbParentezco().getSelectedItem().equals(NOMBRE_PARENTEZCO_CONYUGE)){
		if( parentezco == ParentezcoEnum.CONYUGUE ){
			if(getCmbTrabajaFamiliar().getSelectedItem().equals(NOMBRE_TRABAJAFAMILIAR_SI))
				getEmpleadoFamiliaresIf().setTrabaja(TRABAJAFAMILIAR_SI);
			else if(getCmbTrabajaFamiliar().getSelectedItem().equals(NOMBRE_TRABAJAFAMILIAR_NO))
				getEmpleadoFamiliaresIf().setTrabaja(TRABAJAFAMILIAR_NO);
			
			getEmpleadoFamiliaresIf().setNombreInstitucion(getTxtNombreInstitucionFamiliar().getText());
			
			if(getCmbEsposaEmbarazada().getSelectedItem().equals(NOMBRE_EMBARAZOFAMILIAR_NO)){
				getEmpleadoFamiliaresIf().setEmbarazo(EMBARAZOFAMILIAR_NO);
				getEmpleadoFamiliaresIf().setFechaParto(null);
			}
			else if(getCmbEsposaEmbarazada().getSelectedItem().equals(NOMBRE_EMBARAZOFAMILIAR_SI)){
				getEmpleadoFamiliaresIf().setEmbarazo(EMBARAZOFAMILIAR_SI);
				getEmpleadoFamiliaresIf().setFechaParto(DateHelperClient.getTimeStamp(getCmbFechaParto().getDate()));
			}
							
		}else{
			getEmpleadoFamiliaresIf().setTrabaja(null);
			getEmpleadoFamiliaresIf().setNombreInstitucion(null);
			getEmpleadoFamiliaresIf().setEmbarazo(null);
			getEmpleadoFamiliaresIf().setFechaParto(null);
		}
	}
	
	private void agregarFilaTablaDatosFamiliares(Vector<String> fila) {
		fila.add(getTxtNombresFamiliar().getText() + " " + getTxtApellidosFamiliar().getText());
		fila.add(Utilitarios.getFechaUppercase(getEmpleadoFamiliaresIf().getFechaNacimiento()));
		fila.add(getTxtCedulaFamiliar().getText());
		fila.add(getTxtOcupacionFamiliar().getText());
	}
		
	public boolean validateDatoFamiliarFields(){
				
		if (getCmbParentezco().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Parentezco !!",SpiritAlert.WARNING);
			getCmbParentezco().grabFocus();
			return false;
		}
		if ((("".equals(getTxtApellidosFamiliar().getText())) || (getTxtApellidosFamiliar().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar los Apellidos del Familiar !!",SpiritAlert.WARNING);
			getTxtApellidosFamiliar().grabFocus();
			return false;
		}
		if ((("".equals(getTxtNombresFamiliar().getText())) || (getTxtNombresFamiliar().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar los Nombres del Familiar !!",SpiritAlert.WARNING);
			getTxtNombresFamiliar().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaNacimientoFamiliar().getSelectedItem())) || (getCmbFechaNacimientoFamiliar().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar la Fecha de Nacimiento del Familiar !!",SpiritAlert.WARNING);
			getCmbFechaNacimientoFamiliar().grabFocus();
			return false;
		}
		//si es hijo puede ser un bebe y no tener cedula
		if (!getCmbParentezco().getSelectedItem().equals(ParentezcoEnum.HIJO) && 
				(("".equals(getTxtCedulaFamiliar().getText())) || (getTxtCedulaFamiliar().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el número de Cédula del Familiar !!",SpiritAlert.WARNING);
			getTxtCedulaFamiliar().grabFocus();
			return false;
		}
		if (getCmbNivelEstudiosFamiliar().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Nivel de Estudios del Familiar !!",SpiritAlert.WARNING);
			getCmbNivelEstudiosFamiliar().grabFocus();
			return false;
		}
		if (getCmbAnioFamiliar().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Año Aprobado de estudios del Familiar !!",SpiritAlert.WARNING);
			getCmbAnioFamiliar().grabFocus();
			return false;
		}
		ParentezcoEnum parentezcoSeleccionado = (ParentezcoEnum) getCmbParentezco().getSelectedItem();
		//if(getCmbParentezco().getSelectedItem().equals(NOMBRE_PARENTEZCO_CONYUGE)){
		if( parentezcoSeleccionado == ParentezcoEnum.CONYUGUE ){
			if (getCmbTrabajaFamiliar().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar si el Familiar trabaja o no !!",SpiritAlert.WARNING);
				getCmbTrabajaFamiliar().grabFocus();
				return false;
			}
			if (getCmbEsposaEmbarazada().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar si su Esposa está Embarazada o no !!",SpiritAlert.WARNING);
				getCmbEsposaEmbarazada().grabFocus();
				return false;
			}
		}
		boolean empleadoFamiliaresRepetido = false;
		EmpleadoFamiliaresIf empleadoFamiliaresIf;
		//String parentezco = "";
		//String parentezcoSeleccionado = (String)getCmbParentezco().getSelectedItem();
		
		for (int i = 0; i < vectorEmpleadoFamiliaresIf.size(); i++) {
			empleadoFamiliaresIf = vectorEmpleadoFamiliaresIf.get(i);
			String parentezcoLetra = empleadoFamiliaresIf.getTipo();
			ParentezcoEnum parentezco;
			try {
				parentezco = ParentezcoEnum.getParentezcoByLetra(parentezcoLetra);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				return false;
			}
			
			/*if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_CONYUGE))
				parentezco = NOMBRE_PARENTEZCO_CONYUGE;
			else if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_HIJO))
				parentezco = NOMBRE_PARENTEZCO_HIJO;
			else if(empleadoFamiliaresIf.getTipo().equals(PARENTEZCO_PADRE))
				parentezco = NOMBRE_PARENTEZCO_PADRE;*/
						
			if(!isActualizarDatoFamiliar()){
				//if(parentezco.equals(parentezcoSeleccionado) && (parentezco == NOMBRE_PARENTEZCO_CONYUGE))
				if(parentezco == parentezcoSeleccionado && parentezco == ParentezcoEnum.CONYUGUE)
					empleadoFamiliaresRepetido = true;
			}else{
				//if((parentezco.equals(this.empleadoFamiliaresIf)) && (parentezco == parentezcoSeleccionado) && (parentezco == ParentezcoEnum.CONYUGUE))
				if(parentezco == parentezcoSeleccionado && parentezco == ParentezcoEnum.CONYUGUE)
					empleadoFamiliaresRepetido = true;
			}				
		}

		if (empleadoFamiliaresRepetido) {
			SpiritAlert.createAlert("El Parentezco Conyuge ya esta en la Lista !!", SpiritAlert.WARNING);
			cleanDatosFamiliares();
			getCmbParentezco().grabFocus();
			return false;
		}
		return true;
	}

	private void agregarFormacion() {
		setActualizarFormacion(false);
		if (validateFormacionFields()) {
			tableFormacionModel = (DefaultTableModel) getTblFormacion().getModel();

			//Vector EmpleadoFamiliares para luego guardar en la base
			EmpleadoFormacionData bean = new EmpleadoFormacionData();
			setEmpleadoFormacionIf(bean);

			setearFormacion();
						
			vectorEmpleadoFormacionIf.add(getEmpleadoFormacionIf());

			// Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();

			agregarFilaTablaFormacion(fila);
					
			tableFormacionModel.addRow(fila);
			cleanFormacion();
		}
	}

	private void actualizarFormacion() {
		setActualizarFormacion(true);
		int filaSeleccionada = getTblFormacion().getSelectedRow();
		if (filaSeleccionada >= 0) {
			if (validateFormacionFields()) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					//EmpleadoFormacionEJB bean = new EmpleadoFormacionEJB();
					//setEmpleadoFormacionIf(bean);

					getEmpleadoFormacionIf().setId(empleadoFormacionIf.getId());
					setearFormacion();

					vectorEmpleadoFormacionIf.add(filaSeleccionada, getEmpleadoFormacionIf());
					vectorEmpleadoFormacionIf.remove(filaSeleccionada + 1);

					Vector<String> fila = new Vector<String>();

					agregarFilaTablaFormacion(fila);

					tableFormacionModel.insertRow(filaSeleccionada, fila);
					tableFormacionModel.removeRow(filaSeleccionada + 1);

					cleanFormacion();
				}
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void removerFormacion() {
		setActualizarFormacion(true);
		int filaSeleccionada = getTblFormacion().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				EmpleadoFormacionIf formacion = (EmpleadoFormacionIf)vectorEmpleadoFormacionIf.get(filaSeleccionada);
				if (formacion.getId()!=null)
					empleadoFormacionRemovidos.add(formacion);
				vectorEmpleadoFormacionIf.remove(filaSeleccionada);
				tableFormacionModel.removeRow(filaSeleccionada);
			}
			cleanFormacion();
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void setearFormacion() {
		CiudadIf ciudadFormacion = (CiudadIf) getCmbCiudadFormacion().getSelectedItem();
		
		NivelEstudioEnum nivel = (NivelEstudioEnum) getCmbNivelFormacion().getSelectedItem();
		getEmpleadoFormacionIf().setNivel(nivel.getLetra());
		
		/*if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_PRIMARIA))
			getEmpleadoFormacionIf().setNivel(NIVELESTUDIOS_PRIMARIA);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_SECUNDARIA))
			getEmpleadoFormacionIf().setNivel(NIVELESTUDIOS_SECUNDARIA);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_TECNICO))
			getEmpleadoFormacionIf().setNivel(NIVELESTUDIOS_TECNICO);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD))
			getEmpleadoFormacionIf().setNivel(NIVELESTUDIOS_UNIVERSIDAD);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_POSTGRADO))
			getEmpleadoFormacionIf().setNivel(NIVELESTUDIOS_POSTGRADO);*/
		
		if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_PRIMERO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_PRIMERO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEGUNDO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_SEGUNDO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_TERCERO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_TERCERO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_CUARTO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_CUARTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_QUINTO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_QUINTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEXTO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_SEXTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEPTIMO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_SEPTIMO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_GRADUADO))
			getEmpleadoFormacionIf().setUltimoAnio(ULTIMOAÑO_GRADUADO);
		
		getEmpleadoFormacionIf().setFechaGraduacion(DateHelperClient.getTimeStamp(getCmbFechaGraduado().getDate()));
		if ((!("".equals(getTxtTituloFormacion().getText())) || !(getTxtTituloFormacion().getText() == null))) {
			getEmpleadoFormacionIf().setTituloObtenido(getTxtTituloFormacion().getText());
		}
		getEmpleadoFormacionIf().setInstitucion(getTxtInstitucionFormacion().getText());
		getEmpleadoFormacionIf().setCiudadId(ciudadFormacion.getId());
	}
	
	private void agregarFilaTablaFormacion(Vector<String> fila) {
		NivelEstudioEnum nivel = (NivelEstudioEnum) getCmbNivelFormacion().getSelectedItem();
		fila.add(nivel.toString());
		
		/*if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_PRIMARIA))
			fila.add(NOMBRE_NIVELESTUDIOS_PRIMARIA);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_SECUNDARIA))
			fila.add(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_TECNICO))
			fila.add(NOMBRE_NIVELESTUDIOS_TECNICO);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD))
			fila.add(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
		else if(getCmbNivelFormacion().getSelectedItem().equals(NOMBRE_NIVELESTUDIOS_POSTGRADO))
			fila.add(NOMBRE_NIVELESTUDIOS_POSTGRADO);*/
		
		if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_PRIMERO))
			fila.add(NOMBRE_ULTIMOAÑO_PRIMERO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEGUNDO))
			fila.add(NOMBRE_ULTIMOAÑO_SEGUNDO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_TERCERO))
			fila.add(NOMBRE_ULTIMOAÑO_TERCERO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_CUARTO))
			fila.add(NOMBRE_ULTIMOAÑO_CUARTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_QUINTO))
			fila.add(NOMBRE_ULTIMOAÑO_QUINTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEXTO))
			fila.add(NOMBRE_ULTIMOAÑO_SEXTO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_SEPTIMO))
			fila.add(NOMBRE_ULTIMOAÑO_SEPTIMO);
		else if(getCmbAnioFormacion().getSelectedItem().equals(NOMBRE_ULTIMOAÑO_GRADUADO))
			fila.add(NOMBRE_ULTIMOAÑO_GRADUADO);
		
		fila.add(Utilitarios.getFechaUppercase(getEmpleadoFormacionIf().getFechaGraduacion()));
		fila.add(getTxtTituloFormacion().getText());
		fila.add(getTxtInstitucionFormacion().getText());
	}
	
	public boolean validateFormacionFields(){
		
		if (getCmbNivelFormacion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Nivel en su Formación Académica !!",SpiritAlert.WARNING);
			getCmbNivelFormacion().grabFocus();
			return false;
		}
		if (getCmbAnioFormacion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Año Aprobado del nivel seleccionado !!",SpiritAlert.WARNING);
			getCmbAnioFormacion().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaGraduado().getSelectedItem())) || (getCmbFechaGraduado().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar la Fecha de Graduación !!",SpiritAlert.WARNING);
			getCmbFechaGraduado().grabFocus();
			return false;
		}
		if ((("".equals(getTxtInstitucionFormacion().getText())) || (getTxtInstitucionFormacion().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el nombre de la Institución donde estudio !!",SpiritAlert.WARNING);
			getTxtInstitucionFormacion().grabFocus();
			return false;
		}
		if (getCmbPaisFormacion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el País donde realizo la Formación Académica !!",SpiritAlert.WARNING);
			getCmbPaisFormacion().grabFocus();
			return false;
		}
		if (getCmbCiudadFormacion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la Ciudad donde realizo la Formación Académica !!",SpiritAlert.WARNING);
			getCmbCiudadFormacion().grabFocus();
			return false;
		}
		
		boolean empleadoFormacionRepetido = false;
		EmpleadoFormacionIf empleadoFormacionIf;
		//String nivel = "";
		//String nivelSeleccionado = (String)getCmbNivelFormacion().getSelectedItem();
		NivelEstudioEnum nivelSeleccionado = (NivelEstudioEnum) getCmbNivelFormacion().getSelectedItem();
		
		for (int i = 0; i < vectorEmpleadoFormacionIf.size(); i++) {
			empleadoFormacionIf = vectorEmpleadoFormacionIf.get(i);
			String nivelLetra = empleadoFormacionIf.getNivel();
			NivelEstudioEnum nivel;
			try {
				nivel = NivelEstudioEnum.getNivelEstudioByLetra(nivelLetra);
				
				if(!isActualizarFormacion()){
					if(nivel.equals(nivelSeleccionado))
						empleadoFormacionRepetido = true;
				}else{
					if((nivel.equals(this.empleadoFormacionIf)) && (nivel.equals(nivelSeleccionado)))
						empleadoFormacionRepetido = true;
				}	
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				return false;
			}
			
			/*if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_PRIMARIA))
				nivel = NOMBRE_NIVELESTUDIOS_PRIMARIA;
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_SECUNDARIA))
				nivel = NOMBRE_NIVELESTUDIOS_SECUNDARIA;
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_TECNICO))
				nivel = NOMBRE_NIVELESTUDIOS_TECNICO;
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_UNIVERSIDAD))
				nivel = NOMBRE_NIVELESTUDIOS_UNIVERSIDAD;
			else if(empleadoFormacionIf.getNivel().equals(NIVELESTUDIOS_POSTGRADO))
				nivel = NOMBRE_NIVELESTUDIOS_POSTGRADO;*/
			
						
		}

		if (empleadoFormacionRepetido) {
			SpiritAlert.createAlert("El Nivel seleccionado ya esta en la Lista !!", SpiritAlert.WARNING);
			cleanFormacion();
			getCmbNivelFormacion().grabFocus();
			return false;
		}
		return true;
	}
	
	private void agregarIdioma() {
		setActualizarIdioma(false);
		if (validateIdiomaFields()) {
			tableIdiomaModel = (DefaultTableModel) getTblIdioma().getModel();

			//Vector EmpleadoFamiliares para luego guardar en la base
			EmpleadoIdiomasData bean = new EmpleadoIdiomasData();
			setEmpleadoIdiomaIf(bean);

			setearIdioma();
						
			vectorEmpleadoIdiomaIf.add(getEmpleadoIdiomaIf());

			// Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();

			agregarFilaTablaIdioma(fila);
					
			tableIdiomaModel.addRow(fila);
			cleanIdioma();
		}
	}
	
	private void actualizarIdioma() {
		setActualizarIdioma(true);
		int filaSeleccionada = getTblIdioma().getSelectedRow();
		if (filaSeleccionada >= 0) {
			if (validateIdiomaFields()) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					//EmpleadoIdiomasEJB bean = new EmpleadoIdiomasEJB();
					//setEmpleadoIdiomaIf(bean);

					getEmpleadoIdiomaIf().setId(empleadoIdiomaIf.getId());
					setearIdioma();

					vectorEmpleadoIdiomaIf.add(filaSeleccionada, getEmpleadoIdiomaIf());
					vectorEmpleadoIdiomaIf.remove(filaSeleccionada + 1);

					Vector<String> fila = new Vector<String>();

					agregarFilaTablaIdioma(fila);

					tableIdiomaModel.insertRow(filaSeleccionada, fila);
					tableIdiomaModel.removeRow(filaSeleccionada + 1);

					cleanIdioma();
				}
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void removerIdioma() {
		setActualizarIdioma(true);
		int filaSeleccionada = getTblIdioma().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				EmpleadoIdiomasIf idioma = (EmpleadoIdiomasIf)vectorEmpleadoIdiomaIf.get(filaSeleccionada);
				if (idioma.getId()!=null)
					empleadoIdiomaRemovidos.add(idioma);
				vectorEmpleadoIdiomaIf.remove(filaSeleccionada);
				tableIdiomaModel.removeRow(filaSeleccionada);
			}
			cleanFormacion();
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void setearIdioma() {
		
		idioma = (IdiomaIf) getCmbIdioma().getSelectedItem();
		getEmpleadoIdiomaIf().setIdiomaId(idioma.getId());
		
		if(getCmbHabla().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_REGULAR))
			getEmpleadoIdiomaIf().setHabla(CONOCIMIENTO_REGULAR);
		else if(getCmbHabla().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_BUENO))
			getEmpleadoIdiomaIf().setHabla(CONOCIMIENTO_BUENO);
		else if(getCmbHabla().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_MUYBUENO))
			getEmpleadoIdiomaIf().setHabla(CONOCIMIENTO_MUYBUENO);
		else if(getCmbHabla().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_EXCELENTE))
			getEmpleadoIdiomaIf().setHabla(CONOCIMIENTO_EXCELENTE);
		
		if(getCmbComprende().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_REGULAR))
			getEmpleadoIdiomaIf().setComprende(CONOCIMIENTO_REGULAR);
		else if(getCmbComprende().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_BUENO))
			getEmpleadoIdiomaIf().setComprende(CONOCIMIENTO_BUENO);
		else if(getCmbComprende().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_MUYBUENO))
			getEmpleadoIdiomaIf().setComprende(CONOCIMIENTO_MUYBUENO);
		else if(getCmbComprende().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_EXCELENTE))
			getEmpleadoIdiomaIf().setComprende(CONOCIMIENTO_EXCELENTE);
		
		if(getCmbLee().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_REGULAR))
			getEmpleadoIdiomaIf().setLee(CONOCIMIENTO_REGULAR);
		else if(getCmbLee().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_BUENO))
			getEmpleadoIdiomaIf().setLee(CONOCIMIENTO_BUENO);
		else if(getCmbLee().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_MUYBUENO))
			getEmpleadoIdiomaIf().setLee(CONOCIMIENTO_MUYBUENO);
		else if(getCmbLee().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_EXCELENTE))
			getEmpleadoIdiomaIf().setLee(CONOCIMIENTO_EXCELENTE);
		
		if(getCmbEscribe().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_REGULAR))
			getEmpleadoIdiomaIf().setEscribe(CONOCIMIENTO_REGULAR);
		else if(getCmbEscribe().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_BUENO))
			getEmpleadoIdiomaIf().setEscribe(CONOCIMIENTO_BUENO);
		else if(getCmbEscribe().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_MUYBUENO))
			getEmpleadoIdiomaIf().setEscribe(CONOCIMIENTO_MUYBUENO);
		else if(getCmbEscribe().getSelectedItem().equals(NOMBRE_CONOCIMIENTO_EXCELENTE))
			getEmpleadoIdiomaIf().setEscribe(CONOCIMIENTO_EXCELENTE);
	}
	
	private void agregarFilaTablaIdioma(Vector<String> fila) {
		fila.add(idioma.getNombre());
		
		if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_REGULAR))
			fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
		else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_BUENO))
			fila.add(NOMBRE_CONOCIMIENTO_BUENO);
		else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_MUYBUENO))
			fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
		else if(getEmpleadoIdiomaIf().getHabla().equals(CONOCIMIENTO_EXCELENTE))
			fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);
		
		if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_REGULAR))
			fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
		else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_BUENO))
			fila.add(NOMBRE_CONOCIMIENTO_BUENO);
		else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_MUYBUENO))
			fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
		else if(getEmpleadoIdiomaIf().getComprende().equals(CONOCIMIENTO_EXCELENTE))
			fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);
		
		if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_REGULAR))
			fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
		else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_BUENO))
			fila.add(NOMBRE_CONOCIMIENTO_BUENO);
		else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_MUYBUENO))
			fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
		else if(getEmpleadoIdiomaIf().getLee().equals(CONOCIMIENTO_EXCELENTE))
			fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);
		
		if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_REGULAR))
			fila.add(NOMBRE_CONOCIMIENTO_REGULAR);
		else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_BUENO))
			fila.add(NOMBRE_CONOCIMIENTO_BUENO);
		else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_MUYBUENO))
			fila.add(NOMBRE_CONOCIMIENTO_MUYBUENO);
		else if(getEmpleadoIdiomaIf().getEscribe().equals(CONOCIMIENTO_EXCELENTE))
			fila.add(NOMBRE_CONOCIMIENTO_EXCELENTE);
	}
	
	public boolean validateIdiomaFields(){
		
		if (getCmbIdioma().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Idioma !!",SpiritAlert.WARNING);
			getCmbIdioma().grabFocus();
			return false;
		}
		if (getCmbHabla().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un nivel de conocimientos para Habla !!",SpiritAlert.WARNING);
			getCmbHabla().grabFocus();
			return false;
		}
		if (getCmbComprende().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un nivel de conocimientos para Comprende !!",SpiritAlert.WARNING);
			getCmbComprende().grabFocus();
			return false;
		}
		if (getCmbLee().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un nivel de conocimientos para Lee !!",SpiritAlert.WARNING);
			getCmbLee().grabFocus();
			return false;
		}
		if (getCmbEscribe().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un nivel de conocimientos para Escribe !!",SpiritAlert.WARNING);
			getCmbEscribe().grabFocus();
			return false;
		}
		
		try {
			boolean idiomaRepetido = false;
			EmpleadoIdiomasIf empleadoIdiomasIf;
			IdiomaIf idioma;
			IdiomaIf idiomaSeleccionado = (IdiomaIf) getCmbIdioma().getSelectedItem();
			
			for (int i = 0; i < vectorEmpleadoIdiomaIf.size(); i++) {
				empleadoIdiomasIf = vectorEmpleadoIdiomaIf.get(i);
				idioma = SessionServiceLocator.getIdiomaSessionService().getIdioma(empleadoIdiomasIf.getIdiomaId());
				if(!isActualizarIdioma()){
					if(idioma.getId().compareTo(idiomaSeleccionado.getId()) == 0)
						idiomaRepetido = true;
				}else{
					if((idioma.getId().compareTo(empleadoIdiomaIf.getIdiomaId()) != 0) && (idioma.getId().compareTo(idiomaSeleccionado.getId()) == 0))
						idiomaRepetido = true;
				}				
			}

			if (idiomaRepetido) {
				SpiritAlert.createAlert("El Idioma ya está en la Lista !!", SpiritAlert.WARNING);
				cleanIdioma();
				getCmbIdioma().grabFocus();
				return false;
			}

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return true;
	}
	
	public void showSaveMode() {
		setSaveMode();
		cargarCombos();
		clean();
		cleanDatosFamiliares();
		cleanFormacion();
		cleanIdioma();
		setEmpleadoPersonalIf(null);
		setEmpleadoFamiliaresIf(null);
		setEmpleadoFormacionIf(null);
		setEmpleadoIdiomaIf(null);
		vectorEmpleadoFamiliaresIf.clear();
		empleadoFamiliaresRemovidos.clear();
		vectorEmpleadoFormacionIf.clear();
		empleadoFormacionRemovidos.clear();
		vectorEmpleadoIdiomaIf.clear();
		empleadoIdiomaRemovidos.clear();
		getCmbPais().setSelectedItem(null);
		getJtpHojaVida().setSelectedIndex(0);
		getBtnEmpleado().setEnabled(true);
		getBtnEmpleado().grabFocus();
	}
	
	public void cargarCombos(){
		if(getCmbSexo().getItemCount() == 0){
			getCmbSexo().addItem(NOMBRE_SEXO_MASCULINO);
			getCmbSexo().addItem(NOMBRE_SEXO_FEMENINO);
			getCmbSexo().setSelectedIndex(0);
		}
		if(getCmbEstadoCivil().getItemCount() == 0){
			getCmbEstadoCivil().addItem(NOMBRE_ESTADOCIVIL_SOLTERO);
			getCmbEstadoCivil().addItem(NOMBRE_ESTADOCIVIL_CASADO);
			getCmbEstadoCivil().addItem(NOMBRE_ESTADOCIVIL_VIUDO);
			getCmbEstadoCivil().addItem(NOMBRE_ESTADOCIVIL_DIVORCIADO);
			getCmbEstadoCivil().addItem(NOMBRE_ESTADOCIVIL_UNIONLIBRE);
			getCmbEstadoCivil().setSelectedIndex(0);
		}
		if(getCmbParentezco().getItemCount() == 0){
			ParentezcoEnum[] parentezcos = ParentezcoEnum.values();
			for ( ParentezcoEnum parentezco : parentezcos ){
				getCmbParentezco().addItem(parentezco);
			}
			if ( getCmbParentezco().getItemCount() > 0 )
				getCmbParentezco().setSelectedIndex(0);
			/*getCmbParentezco().addItem( NOMBRE_PARENTEZCO_CONYUGE);
			getCmbParentezco().addItem(NOMBRE_PARENTEZCO_HIJO);
			getCmbParentezco().addItem(NOMBRE_PARENTEZCO_PADRE);
			getCmbParentezco().setSelectedIndex(0);*/
		}
		if(getCmbNivelEstudiosFamiliar().getItemCount() == 0){
			NivelEstudioEnum[] niveles = NivelEstudioEnum.values();
			for ( NivelEstudioEnum nivel : niveles ){
				getCmbNivelEstudiosFamiliar().addItem(nivel);
			}
			if ( getCmbNivelEstudiosFamiliar().getItemCount() > 0 )
				getCmbNivelEstudiosFamiliar().setSelectedIndex(0);
			/*getCmbNivelEstudiosFamiliar().addItem(NOMBRE_NIVELESTUDIOS_PRIMARIA);
			getCmbNivelEstudiosFamiliar().addItem(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
			getCmbNivelEstudiosFamiliar().addItem(NOMBRE_NIVELESTUDIOS_TECNICO);
			getCmbNivelEstudiosFamiliar().addItem(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
			getCmbNivelEstudiosFamiliar().addItem(NOMBRE_NIVELESTUDIOS_POSTGRADO);
			getCmbNivelEstudiosFamiliar().setSelectedIndex(0);*/
		}
		if(getCmbAnioFamiliar().getItemCount() == 0){
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_PRIMERO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_SEGUNDO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_TERCERO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_CUARTO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_QUINTO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_SEXTO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_SEPTIMO);
			getCmbAnioFamiliar().addItem(NOMBRE_ULTIMOAÑO_GRADUADO);
			getCmbAnioFamiliar().setSelectedIndex(0);
		}
		if(getCmbTrabajaFamiliar().getItemCount() == 0){
			getCmbTrabajaFamiliar().addItem(NOMBRE_TRABAJAFAMILIAR_SI);
			getCmbTrabajaFamiliar().addItem(NOMBRE_TRABAJAFAMILIAR_NO);
			getCmbTrabajaFamiliar().setSelectedIndex(0);
		}
		if(getCmbEsposaEmbarazada().getItemCount() == 0){
			getCmbEsposaEmbarazada().addItem(NOMBRE_EMBARAZOFAMILIAR_NO);
			getCmbEsposaEmbarazada().addItem(NOMBRE_EMBARAZOFAMILIAR_SI);
			getCmbEsposaEmbarazada().setSelectedIndex(0);
		}
		if(getCmbNivelFormacion().getItemCount() == 0){
			NivelEstudioEnum[] niveles = NivelEstudioEnum.values();
			for ( NivelEstudioEnum nivel : niveles ){
				getCmbNivelFormacion().addItem(nivel);
			}
			if ( getCmbNivelEstudiosFamiliar().getItemCount() > 0 )
				getCmbNivelFormacion().setSelectedIndex(0);
			/*getCmbNivelFormacion().addItem(NOMBRE_NIVELESTUDIOS_PRIMARIA);
			getCmbNivelFormacion().addItem(NOMBRE_NIVELESTUDIOS_SECUNDARIA);
			getCmbNivelFormacion().addItem(NOMBRE_NIVELESTUDIOS_TECNICO);
			getCmbNivelFormacion().addItem(NOMBRE_NIVELESTUDIOS_UNIVERSIDAD);
			getCmbNivelFormacion().addItem(NOMBRE_NIVELESTUDIOS_POSTGRADO);
			getCmbNivelFormacion().setSelectedIndex(0);*/
		}
		if(getCmbAnioFormacion().getItemCount() == 0){
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_PRIMERO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_SEGUNDO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_TERCERO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_CUARTO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_QUINTO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_SEXTO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_SEPTIMO);
			getCmbAnioFormacion().addItem(NOMBRE_ULTIMOAÑO_GRADUADO);
			getCmbAnioFormacion().setSelectedIndex(0);
		}
		if(getCmbHabla().getItemCount() == 0){
			getCmbHabla().addItem(NOMBRE_CONOCIMIENTO_REGULAR);
			getCmbHabla().addItem(NOMBRE_CONOCIMIENTO_BUENO);
			getCmbHabla().addItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			getCmbHabla().addItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			getCmbHabla().setSelectedIndex(0);
		}
		if(getCmbComprende().getItemCount() == 0){
			getCmbComprende().addItem(NOMBRE_CONOCIMIENTO_REGULAR);
			getCmbComprende().addItem(NOMBRE_CONOCIMIENTO_BUENO);
			getCmbComprende().addItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			getCmbComprende().addItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			getCmbComprende().setSelectedIndex(0);
		}
		if(getCmbLee().getItemCount() == 0){
			getCmbLee().addItem(NOMBRE_CONOCIMIENTO_REGULAR);
			getCmbLee().addItem(NOMBRE_CONOCIMIENTO_BUENO);
			getCmbLee().addItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			getCmbLee().addItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			getCmbLee().setSelectedIndex(0);
		}
		if(getCmbEscribe().getItemCount() == 0){
			getCmbEscribe().addItem(NOMBRE_CONOCIMIENTO_REGULAR);
			getCmbEscribe().addItem(NOMBRE_CONOCIMIENTO_BUENO);
			getCmbEscribe().addItem(NOMBRE_CONOCIMIENTO_MUYBUENO);
			getCmbEscribe().addItem(NOMBRE_CONOCIMIENTO_EXCELENTE);
			getCmbEscribe().setSelectedIndex(0);
		}

		cargarComboPais();
		//cargarComboProvincia();
		cargarComboCiudad();
		cargarComboCiudadEmergencia();
		cargarComboPaisFormacion();
		cargarComboCiudadFormacion();
		cargarComboIdioma();
	}
	
	private void cargarComboPais(){
		try{
			List paises = (List)SessionServiceLocator.getPaisSessionService().getPaisList();
			refreshCombo(getCmbPais(),paises);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboProvincia(){
		try {
			if (pais != null) {
				List provincias = (List)SessionServiceLocator.getProvinciaSessionService().findProvinciaByPaisId(pais.getId());
				refreshCombo(getCmbProvincia(),provincias);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboCiudad(){
		try{
			List ciudades = (List)SessionServiceLocator.getCiudadSessionService().getCiudadList();
			refreshCombo(getCmbCiudad(),ciudades);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboCiudadEmergencia(){
		try{
			List ciudades = (List)SessionServiceLocator.getCiudadSessionService().getCiudadList();
			refreshCombo(getCmbCiudadEmergencia(),ciudades);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboPaisFormacion(){
		try{
			List paises = (List)SessionServiceLocator.getPaisSessionService().getPaisList();
			refreshCombo(getCmbPaisFormacion(),paises);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboCiudadFormacion(){
		try{
			List ciudades = (List)SessionServiceLocator.getCiudadSessionService().getCiudadList();
			refreshCombo(getCmbCiudadFormacion(),ciudades);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void cargarComboIdioma(){
		try{
			List idiomas = (List)SessionServiceLocator.getIdiomaSessionService().getIdiomaList();
			refreshCombo(getCmbIdioma(),idiomas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void showFindMode() {
		
	}

	public void find() {
		buscarEmpleado();
	}
	
	private Map buildQuery() {
		Map<String,String> aMap = new HashMap<String,String>();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", "%" + getTxtCodigo().getText().toUpperCase() + "%");
		else
			aMap.put("codigo", "%");
		
		return aMap;
	}

	public void save() {
		if (validateFields()) {
			try {
				EmpleadoIf empleado = registrarEmpleadoForUpdate();
				EmpleadoPersonalIf empleadoPersonal = registrarEmpleadoPersonal();
				
				SessionServiceLocator.getEmpleadoPersonalSessionService().procesarEmpleadoPersonal(empleado, empleadoPersonal, vectorEmpleadoFamiliaresIf, vectorEmpleadoFormacionIf, vectorEmpleadoIdiomaIf);
				SpiritAlert.createAlert("Hoja de Vida guardada con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al guardar la Hoja de Vida", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (validateFields()) {
			try {
				EmpleadoIf empleado = registrarEmpleadoForUpdate();
				EmpleadoPersonalIf empleadoPersonal = registrarEmpleadoPersonalForUpdate();
				
				SessionServiceLocator.getEmpleadoPersonalSessionService().actualizarEmpleadoPersonal(empleado, empleadoPersonal
						,vectorEmpleadoFamiliaresIf,vectorEmpleadoFormacionIf,vectorEmpleadoIdiomaIf
						,empleadoFamiliaresRemovidos,empleadoFormacionRemovidos,empleadoIdiomaRemovidos );
				SpiritAlert.createAlert("Hoja de Vida actualizada con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al actualizar la Hoja de Vida!", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
		
	private EmpleadoIf registrarEmpleadoForUpdate(){
		
		empleadoIf.setApellidos(getTxtApellidoPaterno().getText() + " " + getTxtApellidoMaterno().getText());
		empleadoIf.setNombres(getTxtNombres().getText());
		empleadoIf.setProfesion(getTxtProfesion().getText());
		empleadoIf.setDireccionDomicilio(getTxtDomicilio().getText());
		empleadoIf.setTelefonoDomicilio(getTxtTelefonoDomicilio().getText());
		empleadoIf.setCelular(getTxtCelular().getText());
				
		return empleadoIf;
	}
	
	private EmpleadoPersonalIf registrarEmpleadoPersonal(){
		EmpleadoPersonalData data = new EmpleadoPersonalData();

		data.setAfiliacionIess(getTxtNoIESS().getText());
		data.setCanton(getTxtCanton().getText());
		data.setCasoEmergencia(getTxtEmergencia().getText());
		data.setCedulaIdentidad(getTxtCedula().getText());
		
		CiudadIf ciudadEmergencia = (CiudadIf) getCmbCiudadEmergencia().getSelectedItem();
		data.setCiudadEmergenciaId(ciudadEmergencia.getId());
		CiudadIf ciudad = (CiudadIf) getCmbCiudad().getSelectedItem();
		data.setCiudadId(ciudad.getId());
		
		data.setColorOjos(getTxtColorOjos().getText());
		data.setColorPelo(getTxtColorPelo().getText());
		data.setColorPiel(getTxtColorPiel().getText());
		data.setDireccionEmergencia(getTxtDireccionEmergencia().getText());
		data.setEmpleadoId(empleadoIf.getId());
		
		if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_CASADO))
			data.setEstadoCivil(ESTADOCIVIL_CASADO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_SOLTERO))
			data.setEstadoCivil(ESTADOCIVIL_SOLTERO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_VIUDO))
			data.setEstadoCivil(ESTADOCIVIL_VIUDO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_DIVORCIADO))
			data.setEstadoCivil(ESTADOCIVIL_DIVORCIADO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_UNIONLIBRE))
			data.setEstadoCivil(ESTADOCIVIL_UNIONLIBRE);
		
		data.setEstatura(Utilitarios.removeDecimalFormat(getTxtEstatura().getText()));
		if(!Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()).equals("") || Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()) != null)
			data.setExestudianteCtt(Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()));
		data.setFechaNacimiento(DateHelperClient.getTimeStamp(getCmbFechaNacimiento().getDate()));
		data.setLibretaMilitar(getTxtLibMilitar().getText());
		data.setNumeroCalzado(getTxtNumeroCalzado().getText());
		
		PaisIf pais = (PaisIf) getCmbPais().getSelectedItem();
		data.setPaisId(pais.getId());
		
		data.setParroquia(getTxtParroquia().getText());
		data.setPeso(Utilitarios.removeDecimalFormat(getTxtPeso().getText()));
		
		ProvinciaIf provincia = (ProvinciaIf) getCmbProvincia().getSelectedItem();
		data.setProvinciaId(provincia.getId());
		
		data.setSenasParticulares(getTxtSeniasParticulares().getText());
		
		if(getCmbSexo().getSelectedItem().equals(NOMBRE_SEXO_MASCULINO))
			data.setSexo(SEXO_MASCULINO);
		else if(getCmbSexo().getSelectedItem().equals(NOMBRE_SEXO_FEMENINO))
			data.setSexo(SEXO_FEMENINO);
		
		data.setTallaCamisa(getTxtTallaCamisa().getText());
		data.setTallaPantalon(getTxtTallaPantalon().getText());
		data.setTelefonoEmergencia(getTxtTelefonoEmergencia().getText());
		data.setTipoSangre(getTxtTipoSangre().getText());
		data.setTitulo(getTxtTitulo().getText());
		
		return data;
	}
	
	private EmpleadoPersonalIf registrarEmpleadoPersonalForUpdate(){
		if ( empleadoPersonalIf == null )
			empleadoPersonalIf = new EmpleadoPersonalData();
		
		empleadoPersonalIf.setAfiliacionIess(getTxtNoIESS().getText());
		empleadoPersonalIf.setCanton(getTxtCanton().getText());
		empleadoPersonalIf.setCasoEmergencia(getTxtEmergencia().getText());
		empleadoPersonalIf.setCedulaIdentidad(getTxtCedula().getText());
		
		CiudadIf ciudadEmergencia = (CiudadIf) getCmbCiudadEmergencia().getSelectedItem();
		empleadoPersonalIf.setCiudadEmergenciaId(ciudadEmergencia.getId());
		CiudadIf ciudad = (CiudadIf) getCmbCiudad().getSelectedItem();
		empleadoPersonalIf.setCiudadId(ciudad.getId());
		
		empleadoPersonalIf.setColorOjos(getTxtColorOjos().getText());
		empleadoPersonalIf.setColorPelo(getTxtColorPelo().getText());
		empleadoPersonalIf.setColorPiel(getTxtColorPiel().getText());
		empleadoPersonalIf.setDireccionEmergencia(getTxtDireccionEmergencia().getText());
		empleadoPersonalIf.setEmpleadoId(empleadoIf.getId());
		
		if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_CASADO))
			empleadoPersonalIf.setEstadoCivil(ESTADOCIVIL_CASADO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_SOLTERO))
			empleadoPersonalIf.setEstadoCivil(ESTADOCIVIL_SOLTERO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_VIUDO))
			empleadoPersonalIf.setEstadoCivil(ESTADOCIVIL_VIUDO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_DIVORCIADO))
			empleadoPersonalIf.setEstadoCivil(ESTADOCIVIL_DIVORCIADO);
		else if(getCmbEstadoCivil().getSelectedItem().equals(NOMBRE_ESTADOCIVIL_UNIONLIBRE))
			empleadoPersonalIf.setEstadoCivil(ESTADOCIVIL_UNIONLIBRE);
		
		empleadoPersonalIf.setEstatura(Utilitarios.removeDecimalFormat(getTxtEstatura().getText()));
		if(!Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()).equals("") || Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()) != null)
			empleadoPersonalIf.setExestudianteCtt(Utilitarios.removeDecimalFormat(getTxtExEstudianteCTT().getText()));
		empleadoPersonalIf.setFechaNacimiento(DateHelperClient.getTimeStamp(getCmbFechaNacimiento().getDate()));
		empleadoPersonalIf.setLibretaMilitar(getTxtLibMilitar().getText());
		empleadoPersonalIf.setNumeroCalzado(getTxtNumeroCalzado().getText());
		
		PaisIf pais = (PaisIf) getCmbPais().getSelectedItem();
		empleadoPersonalIf.setPaisId(pais.getId());
		
		empleadoPersonalIf.setParroquia(getTxtParroquia().getText());
		empleadoPersonalIf.setPeso(Utilitarios.removeDecimalFormat(getTxtPeso().getText()));
		
		ProvinciaIf provincia = (ProvinciaIf) getCmbProvincia().getSelectedItem();
		if (provincia!=null)
			empleadoPersonalIf.setProvinciaId(provincia.getId());
		else
			empleadoPersonalIf.setProvinciaId(null);
		
		empleadoPersonalIf.setSenasParticulares(getTxtSeniasParticulares().getText());
		
		if(getCmbSexo().getSelectedItem().equals(NOMBRE_SEXO_MASCULINO))
			empleadoPersonalIf.setSexo(SEXO_MASCULINO);
		else if(getCmbSexo().getSelectedItem().equals(NOMBRE_SEXO_FEMENINO))
			empleadoPersonalIf.setSexo(SEXO_FEMENINO);
		
		empleadoPersonalIf.setTallaCamisa(getTxtTallaCamisa().getText());
		empleadoPersonalIf.setTallaPantalon(getTxtTallaPantalon().getText());
		empleadoPersonalIf.setTelefonoEmergencia(getTxtTelefonoEmergencia().getText());
		empleadoPersonalIf.setTipoSangre(getTxtTipoSangre().getText());
		empleadoPersonalIf.setTitulo(getTxtTitulo().getText());
		
		return empleadoPersonalIf;
	}

	public void delete() {
		try {
			SessionServiceLocator.getEmpleadoPersonalSessionService().eliminarEmpleadoPersonal(empleadoIf.getId());
			SpiritAlert.createAlert("Hoja de Vida eliminada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Error al eliminar la Hoja de Vida!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtEmpresa().setText("");
		getTxtApellidoPaterno().setText("");
		getTxtApellidoMaterno().setText("");
		getTxtNombres().setText("");
		getTxtProfesion().setText("");
		getTxtDomicilio().setText("");
		getTxtTelefonoDomicilio().setText("");
		getTxtCelular().setText("");
		getTxtTitulo().setText("");
		getTxtCedula().setText("");
		getTxtNoIESS().setText("");
		getTxtLibMilitar().setText("");
		getTxtTipoSangre().setText("");
		getTxtCanton().setText("");
		getTxtParroquia().setText("");
		getTxtEmergencia().setText("");
		getTxtTelefonoEmergencia().setText("");
		getTxtDireccionEmergencia().setText("");
		getTxtTallaCamisa().setText("");
		getTxtTallaPantalon().setText("");
		getTxtNumeroCalzado().setText("");
		getTxtEstatura().setText("");
		getTxtPeso().setText("");
		getTxtColorPelo().setText("");
		getTxtColorOjos().setText("");
		getTxtColorPiel().setText("");
		getTxtSeniasParticulares().setText("");
		getTxtExEstudianteCTT().setText("");
		
		if(getCmbSexo().getItemCount() > 0) getCmbSexo().setSelectedIndex(0);
		if(getCmbEstadoCivil().getItemCount() > 0) getCmbEstadoCivil().setSelectedIndex(0);
		if(getCmbPais().getItemCount() > 0) getCmbPais().setSelectedIndex(0);
		//if(getCmbProvincia().getItemCount() > 0) getCmbProvincia().setSelectedIndex(0);
		if(getCmbCiudad().getItemCount() > 0) getCmbCiudad().setSelectedIndex(0);
		if(getCmbCiudadEmergencia().getItemCount() > 0) getCmbCiudadEmergencia().setSelectedIndex(0);

		calendarFechaNacimiento.setTime(fechaNacimiento);
		getCmbFechaNacimiento().setCalendar(calendarFechaNacimiento);
	
		getLblFechaParto().setVisible(false);
		getCmbFechaParto().setVisible(false);
		
		//Limpio tabla de Datos Familiares
		DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblDatosFamiliares().getModel();
		for (int i = this.getTblDatosFamiliares().getRowCount(); i > 0; --i)
			modelDatosFamiliares.removeRow(i - 1);
		
		//Limpio tabla de Formación Académica
		DefaultTableModel modelFormacion = (DefaultTableModel) getTblFormacion().getModel();
		for (int i = this.getTblFormacion().getRowCount(); i > 0; --i)
			modelFormacion.removeRow(i - 1);
		
		//Limpio tabla de Idioma
		DefaultTableModel modelIdioma = (DefaultTableModel) getTblIdioma().getModel();
		for (int i = this.getTblIdioma().getRowCount(); i > 0; --i)
			modelIdioma.removeRow(i - 1);
		
		vectorEmpleadoFamiliaresIf = new Vector<EmpleadoFamiliaresIf>();
	}
	
	public void cleanDatosFamiliares(){
		if(getCmbParentezco().getItemCount() > 0) getCmbParentezco().setSelectedIndex(0);
		getTxtApellidosFamiliar().setText("");
		getTxtNombresFamiliar().setText("");
		calendarFechaNacimientoFamiliar.setTime(fechaNacimientoFamiliar);
		getCmbFechaNacimientoFamiliar().setCalendar(calendarFechaNacimientoFamiliar);
		getTxtCedulaFamiliar().setText("");
		getTxtOcupacionFamiliar().setText("");
		if(getCmbNivelEstudiosFamiliar().getItemCount() > 0) getCmbNivelEstudiosFamiliar().setSelectedIndex(0);
		if(getCmbAnioFamiliar().getItemCount() > 0) getCmbAnioFamiliar().setSelectedIndex(0);
		if(getCmbTrabajaFamiliar().getItemCount() > 0) getCmbTrabajaFamiliar().setSelectedIndex(0);
		getTxtNombreInstitucionFamiliar().setText("");
		if(getCmbEsposaEmbarazada().getItemCount() > 0) getCmbEsposaEmbarazada().setSelectedIndex(0);
		calendarFechaParto.setTime(fechaParto);
		getCmbFechaParto().setCalendar(calendarFechaParto);
	}
	
	public void cleanFormacion(){
		if(getCmbNivelFormacion().getItemCount() > 0) getCmbNivelFormacion().setSelectedIndex(0);
		if(getCmbAnioFormacion().getItemCount() > 0) getCmbAnioFormacion().setSelectedIndex(0);
		calendarFechaGraduado.setTime(fechaGraduado);
		getCmbFechaGraduado().setCalendar(calendarFechaGraduado);
		getTxtTituloFormacion().setText("");
		getTxtInstitucionFormacion().setText("");
		if(getCmbPaisFormacion().getItemCount() > 0) getCmbPaisFormacion().setSelectedIndex(0);
		if(getCmbCiudadFormacion().getItemCount() > 0) getCmbCiudadFormacion().setSelectedIndex(0);
	}
	
	public void cleanIdioma(){
		if(getCmbIdioma().getItemCount() > 0) getCmbIdioma().setSelectedIndex(0);
		if(getCmbHabla().getItemCount() > 0) getCmbHabla().setSelectedIndex(0);
		if(getCmbComprende().getItemCount() > 0) getCmbComprende().setSelectedIndex(0);
		if(getCmbLee().getItemCount() > 0) getCmbLee().setSelectedIndex(0);
		if(getCmbEscribe().getItemCount() > 0) getCmbEscribe().setSelectedIndex(0);
	}

	public void report() {
		try {	
			//validar que haya escogido un empleado

			if (empleadoIf!=null) { 
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Hoja de Vida?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {

					String fileName = "jaspers/rrhh/RPHojaDeVida.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();

					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					//parametrosMap.put("ruc", empresa.getRuc());
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

					System.out.println("empleadoPersonalIf:"+empleadoPersonalIf);
					System.out.println("empleadoIf:"+empleadoIf);

					//parametrosMap.put("fecIng",);
					//parametrosMap.put("fecSal", operadorNegocio.getIdentificacion());
					parametrosMap.put("apellidos","");
					parametrosMap.put("codigo", "");
					parametrosMap.put("nombres", "");
					parametrosMap.put("profesion", "");
					parametrosMap.put("identificacion","");
					parametrosMap.put("domicilio", "");
					parametrosMap.put("telDom", "");
					parametrosMap.put("fecNac", "");						
					parametrosMap.put("titulo", "");						
					parametrosMap.put("sexo", "");
					parametrosMap.put("canton", "");
					parametrosMap.put("celular", "");
					parametrosMap.put("llamarEmerg", "");
					parametrosMap.put("dirEmerg", "");
					parametrosMap.put("telEmerg", "");
					parametrosMap.put("ciudadEmerg", "");
					parametrosMap.put("parroquia", "");
					parametrosMap.put("pais", "");
					parametrosMap.put("tipoSangre","");
					parametrosMap.put("noIess", "");
					parametrosMap.put("libMilitar", "");
					parametrosMap.put("estadoCivil", "");
					parametrosMap.put("provincia", "");
					parametrosMap.put("fecIng", "");
					parametrosMap.put("fecSal", "");


					ContratoIf contrato = (ContratoIf) SessionServiceLocator.getContratoSessionService().findContratoByEmpleadoId(empleadoIf.getId()).iterator().next();

					String fecIng="";
					if(contrato.getFechaInicio()!=null) fecIng=contrato.getFechaInicio().toString();

					String fecSal="";
					if(contrato.getFechaFin()!=null) fecSal=contrato.getFechaFin().toString();


					parametrosMap.put("fecIng",fecIng);
					parametrosMap.put("fecSal",fecSal);

					if(empleadoIf!=null)
					{
						parametrosMap.put("apellidos", empleadoIf.getApellidos()!=null?empleadoIf.getApellidos().toString():"");
						parametrosMap.put("codigo", empleadoIf.getCodigo()!=null?empleadoIf.getCodigo().toString():"");
						parametrosMap.put("nombres", empleadoIf.getNombres()!=null?empleadoIf.getNombres().toString():"");
						parametrosMap.put("profesion", empleadoIf.getProfesion()!=null?empleadoIf.getProfesion().toString():"");
						parametrosMap.put("identificacion", empleadoIf.getIdentificacion()!=null?empleadoIf.getIdentificacion().toString():"");
						parametrosMap.put("domicilio", empleadoIf.getDireccionDomicilio()!=null?empleadoIf.getDireccionDomicilio().toString():"");
						parametrosMap.put("telDom", empleadoIf.getTelefonoDomicilio()!=null?empleadoIf.getTelefonoDomicilio().toString():"");
					}	

					if(empleadoPersonalIf!=null)
					{												
						parametrosMap.put("fecNac", empleadoPersonalIf.getFechaNacimiento()!=null?empleadoPersonalIf.getFechaNacimiento().toString():"");
						parametrosMap.put("titulo", empleadoPersonalIf.getTitulo()!=null?empleadoPersonalIf.getTitulo():"");						
						parametrosMap.put("sexo", empleadoPersonalIf.getSexo()!=null?empleadoPersonalIf.getSexo():"");	
						parametrosMap.put("canton", empleadoPersonalIf.getCanton()!=null?empleadoPersonalIf.getCanton():"");	
						parametrosMap.put("celular", empleadoIf.getCelular()!=null?empleadoIf.getCelular():"");	
						parametrosMap.put("llamarEmerg", empleadoPersonalIf.getCasoEmergencia()!=null?empleadoPersonalIf.getCasoEmergencia():"");	
						parametrosMap.put("dirEmerg", empleadoPersonalIf.getDireccionEmergencia()!=null?empleadoPersonalIf.getDireccionEmergencia():"");	
						parametrosMap.put("telEmerg", empleadoPersonalIf.getTelefonoEmergencia()!=null?empleadoPersonalIf.getTelefonoEmergencia():"");							
						parametrosMap.put("parroquia", empleadoPersonalIf.getParroquia()!=null?empleadoPersonalIf.getParroquia():"");	
						parametrosMap.put("tipoSangre", empleadoPersonalIf.getTipoSangre()!=null?empleadoPersonalIf.getTipoSangre():"");	
						parametrosMap.put("noIess", empleadoPersonalIf.getAfiliacionIess()!=null?empleadoPersonalIf.getAfiliacionIess():"");	
						parametrosMap.put("libMilitar", empleadoPersonalIf.getLibretaMilitar()!=null?empleadoPersonalIf.getLibretaMilitar():"");	
						parametrosMap.put("estadoCivil", empleadoPersonalIf.getEstadoCivil()!=null?empleadoPersonalIf.getEstadoCivil():"");
						/*parametrosMap.put("provincia", empleadoPersonalIf.getProvinciaId());
						parametrosMap.put("pais", empleadoPersonalIf.getPaisId());						
						parametrosMap.put("ciudadEmerg", empleadoPersonalIf.getCiudadEmergenciaId());*/						
						CiudadIf ciudadEmp= SessionServiceLocator.getCiudadSessionService().getCiudad(empleadoPersonalIf.getCiudadId());
						parametrosMap.put("ciudadEmp", ciudadEmp.toString());

						CiudadIf ciudadEmerg= SessionServiceLocator.getCiudadSessionService().getCiudad(empleadoPersonalIf.getCiudadEmergenciaId());
						ProvinciaIf provinciaEmerg = SessionServiceLocator.getProvinciaSessionService().getProvincia(empleadoPersonalIf.getProvinciaId());
						PaisIf paisEmpleado = SessionServiceLocator.getPaisSessionService().getPais(provinciaEmerg.getPaisId());

						parametrosMap.put("provincia", provinciaEmerg.toString());
						parametrosMap.put("pais", paisEmpleado.toString());						
						parametrosMap.put("ciudadEmerg", ciudadEmerg.toString());						
					}			

					DefaultTableModel tblModelReporte = (DefaultTableModel) getTblIdioma().getModel();
					if (tblModelReporte.getRowCount() > 0) {
						Vector<DatosIdiomasData> datosAcam = new Vector<DatosIdiomasData>();						
						DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblIdioma().getModel();						
						for (int i = this.getTblIdioma().getRowCount(); i > 0; --i)
						{
							DatosIdiomasData dat=new DatosIdiomasData();							
							String nombres = (String) getTblIdioma().getModel().getValueAt(i-1,0);
							dat.setIdioma(nombres);
							dat.setHabla((String) getTblIdioma().getModel().getValueAt(i-1,1));
							dat.setComprende((String) getTblIdioma().getModel().getValueAt(i-1,2));
							dat.setLee((String) getTblIdioma().getModel().getValueAt(i-1,3));
							dat.setEscribe((String) getTblIdioma().getModel().getValueAt(i-1,4));							 
							datosAcam.add(dat);
						} 
						JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosAcam);
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						{
							parametrosMap.put("pathSubreportIdiomas", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesIdiomas.jasper");							
						}
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
						parametrosMap.put("idiomasDetalles", dataSourceDebitos2);
					}

					DefaultTableModel tblModelReporteFam = (DefaultTableModel) getTblDatosFamiliares().getModel();
					if (tblModelReporteFam.getRowCount() > 0) {
						Vector<DatosFamiliaresData> datosFam = new Vector<DatosFamiliaresData>();					
						DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblDatosFamiliares().getModel();
						for (int i = this.getTblDatosFamiliares().getRowCount(); i > 0; --i)
						{
							DatosFamiliaresData dat=new DatosFamiliaresData();						
							String nombres = (String) getTblDatosFamiliares().getModel().getValueAt(i-1,0);
							dat.setNombre(nombres);
							dat.setFecNac((String) getTblDatosFamiliares().getModel().getValueAt(i-1,1));
							dat.setCedIdentidad((String) getTblDatosFamiliares().getModel().getValueAt(i-1,2));
							dat.setOcupacion((String) getTblDatosFamiliares().getModel().getValueAt(i-1,3));
							//dat.setTrabaja((String) getTblDatosFamiliares().getModel().getValueAt(i-1,4));
							//dat.setEmbara((String) getTblDatosFamiliares().getModel().getValueAt(i-1,3));
							datosFam.add(dat);
						}
						JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosFam);
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
							parametrosMap.put("pathSubreportFamiliares", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesFamiliares.jasper");
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					

						parametrosMap.put("familiaresDetalles", dataSourceDebitos2);
					}

					DefaultTableModel tblModelReporteFA = (DefaultTableModel) getTblFormacion().getModel();
					if (tblModelReporteFA.getRowCount() > 0) {
						Vector<DatosAcademicosData> datosAcam = new Vector<DatosAcademicosData>();						
						DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblFormacion().getModel();
						for (int i = this.getTblFormacion().getRowCount(); i > 0; --i)
						{
							DatosAcademicosData dat=new DatosAcademicosData();						
							String nombres = (String) getTblFormacion().getModel().getValueAt(i-1,0);
							dat.setNivel(nombres);
							dat.setAnioAprobado((String) getTblFormacion().getModel().getValueAt(i-1,1));
							dat.setFecGraduado((String) getTblFormacion().getModel().getValueAt(i-1,2));
							dat.setTitulo((String) getTblFormacion().getModel().getValueAt(i-1,3));
							dat.setInstitucion((String) getTblFormacion().getModel().getValueAt(i-1,4));							 
							datosAcam.add(dat);
						}						
						JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosAcam);
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
							parametrosMap.put("pathSubreportAcademicas", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesAcademicas.jasper");
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");				

						parametrosMap.put("academicasDetalles", dataSourceDebitos2);
					}
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir. Debe buscar/seleccionar un empleado.", SpiritAlert.INFORMATION);


		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	public void report_DatosFamiliares() {
		
		try {	
			//validar que haya escogido un empleado
			
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblDatosFamiliares().getModel();
			
			if (empleadoIf!=null && tblModelReporte.getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Datos Personales?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/rrhh/RPDatosFamiliares.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					//parametrosMap.put("ruc", empresa.getRuc());
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
					
					System.out.println("empleadoPersonalIf:"+empleadoPersonalIf);
					System.out.println("empleadoIf:"+empleadoIf);
					
					parametrosMap.put("apellidos", empleadoIf.getApellidos()!=null?empleadoIf.getApellidos().toString():"");
					parametrosMap.put("codigo", empleadoIf.getCodigo()!=null?empleadoIf.getCodigo().toString():"");
					parametrosMap.put("nombres", empleadoIf.getNombres()!=null?empleadoIf.getNombres().toString():"");
					
					
					 Vector<DatosFamiliaresData> datosFam = new Vector<DatosFamiliaresData>();					
					DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblDatosFamiliares().getModel();
					for (int i = this.getTblDatosFamiliares().getRowCount(); i > 0; --i)
						{
						DatosFamiliaresData dat=new DatosFamiliaresData();						
						String nombres = (String) getTblDatosFamiliares().getModel().getValueAt(i-1,0);
						dat.setNombre(nombres);
						dat.setFecNac((String) getTblDatosFamiliares().getModel().getValueAt(i-1,1));
						dat.setCedIdentidad((String) getTblDatosFamiliares().getModel().getValueAt(i-1,2));
						dat.setOcupacion((String) getTblDatosFamiliares().getModel().getValueAt(i-1,3));
						//dat.setTrabaja((String) getTblDatosFamiliares().getModel().getValueAt(i-1,4));
						//dat.setEmbara((String) getTblDatosFamiliares().getModel().getValueAt(i-1,3));
						datosFam.add(dat);
						}
					JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosFam);
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						parametrosMap.put("pathSubreportFamiliares", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesFamiliares.jasper");
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
					
					parametrosMap.put("familiaresDetalles", dataSourceDebitos2);
					
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir. Debe buscar/seleccionar un empleado.", SpiritAlert.INFORMATION);
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void report_DatosAcademicas() {
		
		try {	
			//validar que haya escogido un empleado
			
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblFormacion().getModel();
			
			if (empleadoIf!=null && tblModelReporte.getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Formación Académica?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/rrhh/RPDatosAcademicas.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					//parametrosMap.put("ruc", empresa.getRuc());
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
					
					System.out.println("empleadoPersonalIf:"+empleadoPersonalIf);
					System.out.println("empleadoIf:"+empleadoIf);
					
					 
					parametrosMap.put("apellidos", empleadoIf.getApellidos()!=null?empleadoIf.getApellidos().toString():"");
					parametrosMap.put("codigo", empleadoIf.getCodigo()!=null?empleadoIf.getCodigo().toString():"");
					parametrosMap.put("nombres", empleadoIf.getNombres()!=null?empleadoIf.getNombres().toString():"");
					 
					
					 Vector<DatosAcademicosData> datosAcam = new Vector<DatosAcademicosData>();						
					 DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblFormacion().getModel();
						for (int i = this.getTblFormacion().getRowCount(); i > 0; --i)
							{
							DatosAcademicosData dat=new DatosAcademicosData();						
							String nombres = (String) getTblFormacion().getModel().getValueAt(i-1,0);
							dat.setNivel(nombres);
							dat.setAnioAprobado((String) getTblFormacion().getModel().getValueAt(i-1,1));
							dat.setFecGraduado((String) getTblFormacion().getModel().getValueAt(i-1,2));
							dat.setTitulo((String) getTblFormacion().getModel().getValueAt(i-1,3));
							dat.setInstitucion((String) getTblFormacion().getModel().getValueAt(i-1,4));							 
							datosAcam.add(dat);
							}						
						JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosAcam);
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
							parametrosMap.put("pathSubreportAcademicas", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesAcademicas.jasper");
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");				
						
						parametrosMap.put("academicasDetalles", dataSourceDebitos2);
						
						ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
						
						
					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir. Debe buscar/seleccionar un empleado.", SpiritAlert.INFORMATION);
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void report_DatosIdiomas() {
		
		
		try {	
			//validar que haya escogido un empleado
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblIdioma().getModel();
			
			if (empleadoIf!=null && tblModelReporte.getRowCount() > 0) {
			
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Datos de Idiomas?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/rrhh/RPDatosIdiomas.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					//parametrosMap.put("ruc", empresa.getRuc());
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
					
					System.out.println("empleadoPersonalIf:"+empleadoPersonalIf);
					System.out.println("empleadoIf:"+empleadoIf);
					parametrosMap.put("apellidos", empleadoIf.getApellidos()!=null?empleadoIf.getApellidos().toString():"");
					parametrosMap.put("codigo", empleadoIf.getCodigo()!=null?empleadoIf.getCodigo().toString():"");
					parametrosMap.put("nombres", empleadoIf.getNombres()!=null?empleadoIf.getNombres().toString():"");
					
					 Vector<DatosIdiomasData> datosAcam = new Vector<DatosIdiomasData>();
						//lleno tabla de Datos Familiares
						DefaultTableModel modelDatosFamiliares = (DefaultTableModel) getTblIdioma().getModel();
						
						for (int i = this.getTblIdioma().getRowCount(); i > 0; --i)
							{
							DatosIdiomasData dat=new DatosIdiomasData();
							String nombres = (String) getTblIdioma().getModel().getValueAt(i-1,0);
							dat.setIdioma(nombres);
							dat.setHabla((String) getTblIdioma().getModel().getValueAt(i-1,1));
							dat.setComprende((String) getTblIdioma().getModel().getValueAt(i-1,2));
							dat.setLee((String) getTblIdioma().getModel().getValueAt(i-1,3));
							dat.setEscribe((String) getTblIdioma().getModel().getValueAt(i-1,4));
							 
							datosAcam.add(dat);
							}
						
						System.out.println("datosFam"+datosAcam.size());
						
						
						JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(datosAcam);
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
							{parametrosMap.put("pathSubreportIdiomas", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/rrhh/RPDetallesIdiomas.jasper");
							
							}
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
						
						parametrosMap.put("idiomasDetalles", dataSourceDebitos2);
						
						ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
						
					
					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir. Debe buscar/seleccionar un empleado.", SpiritAlert.INFORMATION);
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void report_DatosPersonales() {
		
		try {	
			//validar que haya escogido un empleado
						
			if (empleadoIf!=null) { 
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Datos Personales?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/rrhh/RPDatosPersonales.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("HOJA DE VIDA").iterator().next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					//parametrosMap.put("ruc", empresa.getRuc());
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
					
					System.out.println("empleadoPersonalIf:"+empleadoPersonalIf);
					System.out.println("empleadoIf:"+empleadoIf);

					//parametrosMap.put("fecIng",);
					//parametrosMap.put("fecSal", operadorNegocio.getIdentificacion());
					parametrosMap.put("apellidos","");
					parametrosMap.put("codigo", "");
					parametrosMap.put("nombres", "");
					parametrosMap.put("profesion", "");
					parametrosMap.put("identificacion","");
					parametrosMap.put("domicilio", "");
					parametrosMap.put("telDom", "");
					parametrosMap.put("fecNac", "");						
					parametrosMap.put("titulo", "");						
					parametrosMap.put("sexo", "");
					parametrosMap.put("canton", "");
					parametrosMap.put("celular", "");
					parametrosMap.put("llamarEmerg", "");
					parametrosMap.put("dirEmerg", "");
					parametrosMap.put("telEmerg", "");
					parametrosMap.put("ciudadEmerg", "");
					parametrosMap.put("parroquia", "");
					parametrosMap.put("pais", "");
					parametrosMap.put("tipoSangre","");
					parametrosMap.put("noIess", "");
					parametrosMap.put("libMilitar", "");
					parametrosMap.put("estadoCivil", "");
					parametrosMap.put("provincia", "");
					
					ContratoIf contrato = (ContratoIf) SessionServiceLocator.getContratoSessionService().findContratoByEmpleadoId(empleadoIf.getId()).iterator().next();
					
					String fecIng="";
					if(contrato.getFechaInicio()!=null) fecIng=contrato.getFechaInicio().toString();
					
					String fecSal="";
					if(contrato.getFechaFin()!=null) fecSal=contrato.getFechaFin().toString();
					
					
					parametrosMap.put("fecIng",fecIng);
					parametrosMap.put("fecSal",fecSal);
					
					if(empleadoIf!=null)
					{
						parametrosMap.put("apellidos", empleadoIf.getApellidos()!=null?empleadoIf.getApellidos().toString():"");
						parametrosMap.put("codigo", empleadoIf.getCodigo()!=null?empleadoIf.getCodigo().toString():"");
						parametrosMap.put("nombres", empleadoIf.getNombres()!=null?empleadoIf.getNombres().toString():"");
						parametrosMap.put("profesion", empleadoIf.getProfesion()!=null?empleadoIf.getProfesion().toString():"");
						parametrosMap.put("identificacion", empleadoIf.getIdentificacion()!=null?empleadoIf.getIdentificacion().toString():"");
						parametrosMap.put("domicilio", empleadoIf.getDireccionDomicilio()!=null?empleadoIf.getDireccionDomicilio().toString():"");
						parametrosMap.put("telDom", empleadoIf.getTelefonoDomicilio()!=null?empleadoIf.getTelefonoDomicilio().toString():"");
					}	
					 
					
					if(empleadoPersonalIf!=null)
					{												
						parametrosMap.put("fecNac", empleadoPersonalIf.getFechaNacimiento()!=null?empleadoPersonalIf.getFechaNacimiento().toString():"");
						parametrosMap.put("titulo", empleadoPersonalIf.getTitulo()!=null?empleadoPersonalIf.getTitulo():"");						
						parametrosMap.put("sexo", empleadoPersonalIf.getSexo()!=null?empleadoPersonalIf.getSexo():"");	
						parametrosMap.put("canton", empleadoPersonalIf.getCanton()!=null?empleadoPersonalIf.getCanton():"");	
						parametrosMap.put("celular", empleadoIf.getCelular()!=null?empleadoIf.getCelular():"");	
						parametrosMap.put("llamarEmerg", empleadoPersonalIf.getCasoEmergencia()!=null?empleadoPersonalIf.getCasoEmergencia():"");	
						parametrosMap.put("dirEmerg", empleadoPersonalIf.getDireccionEmergencia()!=null?empleadoPersonalIf.getDireccionEmergencia():"");	
						parametrosMap.put("telEmerg", empleadoPersonalIf.getTelefonoEmergencia()!=null?empleadoPersonalIf.getTelefonoEmergencia():"");							
						parametrosMap.put("parroquia", empleadoPersonalIf.getParroquia()!=null?empleadoPersonalIf.getParroquia():"");	
												
						parametrosMap.put("tipoSangre", empleadoPersonalIf.getTipoSangre()!=null?empleadoPersonalIf.getTipoSangre():"");	
						parametrosMap.put("noIess", empleadoPersonalIf.getAfiliacionIess()!=null?empleadoPersonalIf.getAfiliacionIess():"");	
						parametrosMap.put("libMilitar", empleadoPersonalIf.getLibretaMilitar()!=null?empleadoPersonalIf.getLibretaMilitar():"");	
						parametrosMap.put("estadoCivil", empleadoPersonalIf.getEstadoCivil()!=null?empleadoPersonalIf.getEstadoCivil():"");	
						
						/*parametrosMap.put("provincia", empleadoPersonalIf.getProvinciaId());
						parametrosMap.put("pais", empleadoPersonalIf.getPaisId());						
						parametrosMap.put("ciudadEmerg", empleadoPersonalIf.getCiudadEmergenciaId());*/
						
						
						
						CiudadIf ciudadEmerg= SessionServiceLocator.getCiudadSessionService().getCiudad(empleadoPersonalIf.getCiudadId());
						ProvinciaIf provinciaEmerg = SessionServiceLocator.getProvinciaSessionService().getProvincia(empleadoPersonalIf.getProvinciaId());
						PaisIf paisEmpleado = SessionServiceLocator.getPaisSessionService().getPais(provinciaEmerg.getPaisId());
						
						parametrosMap.put("provincia", provinciaEmerg.toString());
						parametrosMap.put("pais", paisEmpleado.toString());						
						parametrosMap.put("ciudadEmerg", ciudadEmerg.toString());
						
						CiudadIf ciudadEmp= SessionServiceLocator.getCiudadSessionService().getCiudad(empleadoPersonalIf.getCiudadId());
						parametrosMap.put("ciudadEmp", ciudadEmp.toString());
						
					}
					 
 
					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
					 
					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir. Debe buscar/seleccionar un empleado.", SpiritAlert.INFORMATION);
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	

	
	
	public void refresh() {
		cargarComboPais();
		//cargarComboProvincia();
		cargarComboCiudad();
		cargarComboCiudadEmergencia();
		cargarComboPaisFormacion();
		cargarComboCiudadFormacion();
		cargarComboIdioma();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpHojaVida().getSelectedIndex();
		int tabCount = this.getJtpHojaVida().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpHojaVida().setSelectedIndex(selectedTab);
	}

	public boolean validateFields() {
		if ((("".equals(getTxtCodigo().getText())) || (getTxtCodigo().getText() == null))) {
			SpiritAlert.createAlert("Debe Buscar un Empleado !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getBtnEmpleado().grabFocus();
			return false;
		}
		if ((("".equals(getTxtApellidoPaterno().getText())) || (getTxtApellidoPaterno().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el Apellido Paterno !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtApellidoPaterno().grabFocus();
			return false;
		}
		if ((("".equals(getTxtNombres().getText())) || (getTxtNombres().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar los Nombres !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtNombres().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCedula().getText())) || (getTxtCedula().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el número de Cédula !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtCedula().grabFocus();
			return false;
		}
		if (getCmbSexo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Sexo !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getCmbSexo().grabFocus();
			return false;
		}
		if (getCmbEstadoCivil().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Estado Civil !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getCmbEstadoCivil().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaNacimiento().getSelectedItem())) || (getCmbFechaNacimiento().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar la Fecha de Nacimiento !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getCmbFechaNacimiento().grabFocus();
			return false;
		}
		if (getCmbPais().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el País !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getCmbPais().grabFocus();
			return false;
		}
		if (getCmbCiudad().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la Ciudad !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getCmbCiudad().grabFocus();
			return false;
		}
		if ((("".equals(getTxtDomicilio().getText())) || (getTxtDomicilio().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el Domicilio !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtDomicilio().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtEmergencia().getText())) || (getTxtEmergencia().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar la persona a Llamar en caso de Emergencia !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtEmergencia().grabFocus();
			return false;
		}
		if ((("".equals(getTxtTelefonoEmergencia().getText())) || (getTxtTelefonoEmergencia().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar la teléfono para Llamar en caso de Emergencia !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(0);
			getTxtTelefonoEmergencia().grabFocus();
			return false;
		}
		
		/*if(vectorEmpleadoFamiliaresIf.size() == 0){
			SpiritAlert.createAlert("Debe ingresar al menos un Dato de Familiares !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(1);
			getCmbParentezco().grabFocus();
			return false;
		}*/
		if(vectorEmpleadoFormacionIf.size() == 0){
			SpiritAlert.createAlert("Debe ingresar al menos una Formación Académica !!",SpiritAlert.WARNING);
			getJtpHojaVida().setSelectedIndex(2);
			getCmbNivelFormacion().grabFocus();
			return false;
		}
		
		return true;
	}

	public void addDetail() {
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}

	public boolean isEmpty() {
		return false;
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public FinderTable getFinderTable() {
		return null;
	}

	public void setFinderTable(FinderTable table) {
		// TODO Auto-generated method stub
		
	}

	public boolean isFinderTableVisible() {
		return false;
	}

	public int getListSize() {
		return 0;
	}

	public ArrayList getDataForTable(ArrayList list) {
		return null;
	}

	public TableModel getTableModel() {
		return null;
	}

	public ArrayList getListModel() {
		return null;
	}

	public void setComponentsOfPopup(int rowTablePopup) {
	}

	public int getListSize(Map aMap) {
		return 0;
	}

	public int getListSize(Long idFiltroBusqueda) {
		return 0;
	}

	public int getListSize(Map aMap, Long idFiltroBusqueda) {
		return 0;
	}

	 
	
	public boolean isActualizarDatoFamiliar() {
		return actualizarDatoFamiliar;
	}

	public void setActualizarDatoFamiliar(boolean actualizarDatoFamiliar) {
		this.actualizarDatoFamiliar = actualizarDatoFamiliar;
	}
	
	public EmpleadoFamiliaresIf getEmpleadoFamiliaresIf() {
		return empleadoFamiliaresIf;
	}

	public void setEmpleadoFamiliaresIf(EmpleadoFamiliaresIf empleadoFamiliaresIf) {
		this.empleadoFamiliaresIf = empleadoFamiliaresIf;
	}
	
	public int getEmpleadoFamiliaresSeleccionado() {
		return empleadoFamiliaresSeleccionado;
	}

	public void setEmpleadoFamiliaresSeleccionado(int empleadoFamiliaresSeleccionado) {
		this.empleadoFamiliaresSeleccionado = empleadoFamiliaresSeleccionado;
	}

	public Vector<EmpleadoFamiliaresIf> getVectorEmpleadoFamiliaresIf() {
		return vectorEmpleadoFamiliaresIf;
	}

	public void setVectorEmpleadoFamiliaresIf(Vector<EmpleadoFamiliaresIf> vectorEmpleadoFamiliaresIf) {
		this.vectorEmpleadoFamiliaresIf = vectorEmpleadoFamiliaresIf;
	}

	public boolean isActualizarFormacion() {
		return actualizarFormacion;
	}

	public void setActualizarFormacion(boolean actualizarFormacion) {
		this.actualizarFormacion = actualizarFormacion;
	}

	public EmpleadoFormacionIf getEmpleadoFormacionIf() {
		return empleadoFormacionIf;
	}

	public void setEmpleadoFormacionIf(EmpleadoFormacionIf empleadoFormacionIf) {
		this.empleadoFormacionIf = empleadoFormacionIf;
	}

	public Vector<EmpleadoFormacionIf> getVectorEmpleadoFormacionIf() {
		return vectorEmpleadoFormacionIf;
	}

	public void setVectorEmpleadoFormacionIf(
			Vector<EmpleadoFormacionIf> vectorEmpleadoFormacionIf) {
		this.vectorEmpleadoFormacionIf = vectorEmpleadoFormacionIf;
	}

	public int getEmpleadoFormacionSeleccionado() {
		return empleadoFormacionSeleccionado;
	}

	public void setEmpleadoFormacionSeleccionado(int empleadoFormacionSeleccionado) {
		this.empleadoFormacionSeleccionado = empleadoFormacionSeleccionado;
	}

	public boolean isActualizarIdioma() {
		return actualizarIdioma;
	}

	public void setActualizarIdioma(boolean actualizarIdioma) {
		this.actualizarIdioma = actualizarIdioma;
	}

	public EmpleadoIdiomasIf getEmpleadoIdiomaIf() {
		return empleadoIdiomaIf;
	}

	public void setEmpleadoIdiomaIf(EmpleadoIdiomasIf empleadoIdiomaIf) {
		this.empleadoIdiomaIf = empleadoIdiomaIf;
	}

	public Vector<EmpleadoIdiomasIf> getVectorEmpleadoIdiomaIf() {
		return vectorEmpleadoIdiomaIf;
	}

	public void setVectorEmpleadoIdiomaIf(
			Vector<EmpleadoIdiomasIf> vectorEmpleadoIdiomaIf) {
		this.vectorEmpleadoIdiomaIf = vectorEmpleadoIdiomaIf;
	}

	public int getEmpleadoIdiomaSeleccionado() {
		return empleadoIdiomaSeleccionado;
	}

	public void setEmpleadoIdiomaSeleccionado(int empleadoIdiomaSeleccionado) {
		this.empleadoIdiomaSeleccionado = empleadoIdiomaSeleccionado;
	}
	
	 

	public EmpleadoPersonalIf getEmpleadoPersonalIf() {
		return empleadoPersonalIf;
	}

	public void setEmpleadoPersonalIf(EmpleadoPersonalIf empleadoPersonalIf) {
		this.empleadoPersonalIf = empleadoPersonalIf;
	}
}
