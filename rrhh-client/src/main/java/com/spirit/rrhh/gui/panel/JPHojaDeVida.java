package com.spirit.rrhh.gui.panel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPHojaDeVida extends SpiritModelImpl {
	public JPHojaDeVida() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpHojaVida = new JideTabbedPane();
		spDatosPersonales = new JScrollPane();
		panelDatosPersonales = new JPanel();
		btnImprimirDP = new JButton();
		lblEmpleado = new JLabel();
		btnEmpleado = new JButton();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblEmpresa = new JLabel();
		txtEmpresa = new JTextField();
		lblApellidoPaterno = new JLabel();
		txtApellidoPaterno = new JTextField();
		lblApellidoMaterno = new JLabel();
		txtApellidoMaterno = new JTextField();
		lblNombres = new JLabel();
		txtNombres = new JTextField();
		lblTitulo = new JLabel();
		txtTitulo = new JTextField();
		lblProfesion = new JLabel();
		txtProfesion = new JTextField();
		lblCedula = new JLabel();
		txtCedula = new JTextField();
		lblNoIESS = new JLabel();
		txtNoIESS = new JTextField();
		lblLibMilitar = new JLabel();
		txtLibMilitar = new JTextField();
		lblSexo = new JLabel();
		cmbSexo = new JComboBox();
		lblTipoSangre = new JLabel();
		txtTipoSangre = new JTextField();
		lblEstadoCivil = new JLabel();
		cmbEstadoCivil = new JComboBox();
		lblFechaNacimiento = new JLabel();
		cmbFechaNacimiento = new DateComboBox();
		lblPais = new JLabel();
		cmbPais = new JComboBox();
		lblProvincia = new JLabel();
		cmbProvincia = new JComboBox();
		lblCanton = new JLabel();
		txtCanton = new JTextField();
		lblParroquia = new JLabel();
		txtParroquia = new JTextField();
		lblDomicilio = new JLabel();
		txtDomicilio = new JTextField();
		lblCiudad = new JLabel();
		cmbCiudad = new JComboBox();
		lblTelefonoDomicilio = new JLabel();
		txtTelefonoDomicilio = new JTextField();
		lblCelular = new JLabel();
		txtCelular = new JTextField();
		lblEmergencia = new JLabel();
		txtEmergencia = new JTextField();
		lblTelefonoEmergencia = new JLabel();
		txtTelefonoEmergencia = new JTextField();
		lblDireccionEmergencia = new JLabel();
		txtDireccionEmergencia = new JTextField();
		lblCiudadEmergencia = new JLabel();
		cmbCiudadEmergencia = new JComboBox();
		lblTallaCamisa = new JLabel();
		txtTallaCamisa = new JTextField();
		lblTallaPantalon = new JLabel();
		txtTallaPantalon = new JTextField();
		lblNumeroCalzado = new JLabel();
		txtNumeroCalzado = new JTextField();
		lblEstatura = new JLabel();
		txtEstatura = new JTextField();
		lblPeso = new JLabel();
		txtPeso = new JTextField();
		lblColorPelo = new JLabel();
		txtColorPelo = new JTextField();
		lblColorOjos = new JLabel();
		txtColorOjos = new JTextField();
		lblColorPiel = new JLabel();
		txtColorPiel = new JTextField();
		lblSeniasParticulares = new JLabel();
		txtSeniasParticulares = new JTextField();
		lblExEstudianteCTT = new JLabel();
		txtExEstudianteCTT = new JTextField();
		panelDatosFamiliares = new JPanel();
		btnImprimirDF = new JButton();
		lblParentezco = new JLabel();
		cmbParentezco = new JComboBox();
		lblApellidosFamiliar = new JLabel();
		txtApellidosFamiliar = new JTextField();
		lblNombresFamiliar = new JLabel();
		txtNombresFamiliar = new JTextField();
		lblFechaNacimientoFamiliar = new JLabel();
		cmbFechaNacimientoFamiliar = new DateComboBox();
		lblCedulaFamiliar = new JLabel();
		txtCedulaFamiliar = new JTextField();
		lblOcupacionFamiliar = new JLabel();
		txtOcupacionFamiliar = new JTextField();
		lblNivelEstudiosFamiliar = new JLabel();
		cmbNivelEstudiosFamiliar = new JComboBox();
		lblAnioFamiliar = new JLabel();
		cmbAnioFamiliar = new JComboBox();
		lblTrabajaFamiliar = new JLabel();
		cmbTrabajaFamiliar = new JComboBox();
		lblNombreInstitucionFamiliar = new JLabel();
		txtNombreInstitucionFamiliar = new JTextField();
		lblEsposaEmbarazada = new JLabel();
		cmbEsposaEmbarazada = new JComboBox();
		lblFechaParto = new JLabel();
		cmbFechaParto = new DateComboBox();
		panelBotonesDatoFamiliar = new JPanel();
		btnAgregarDatoFamiliar = new JButton();
		btnActualizarDatoFamiliar = new JButton();
		btnRemoverDatoFamiliar = new JButton();
		spTblDatosFamiliares = new JScrollPane();
		tblDatosFamiliares = new JTable();
		panelFormacionAcademica = new JPanel();
		btnImprimirFA = new JButton();
		lblNivelFormacion = new JLabel();
		cmbNivelFormacion = new JComboBox();
		lblAnioFormacion = new JLabel();
		cmbAnioFormacion = new JComboBox();
		lblFechaGraduado = new JLabel();
		cmbFechaGraduado = new DateComboBox();
		lblTituloFormacion = new JLabel();
		txtTituloFormacion = new JTextField();
		lblInstitucionFormacion = new JLabel();
		txtInstitucionFormacion = new JTextField();
		lblPaisFormacion = new JLabel();
		cmbPaisFormacion = new JComboBox();
		lblCiudadFormacion = new JLabel();
		cmbCiudadFormacion = new JComboBox();
		panelBotonesFormacion = new JPanel();
		btnAgregarFormacion = new JButton();
		btnActualizarFormacion = new JButton();
		btnRemoverFormacion = new JButton();
		spTblFormacion = new JScrollPane();
		tblFormacion = new JTable();
		panelIdiomas = new JPanel();
		btnImprimirIdiomas = new JButton();
		lblIdioma = new JLabel();
		cmbIdioma = new JComboBox();
		lblHabla = new JLabel();
		cmbHabla = new JComboBox();
		lblComprende = new JLabel();
		cmbComprende = new JComboBox();
		lblLee = new JLabel();
		cmbLee = new JComboBox();
		lblEscribe = new JLabel();
		cmbEscribe = new JComboBox();
		panelBotonesIdioma = new JPanel();
		btnAgregarIdioma = new JButton();
		btnActualizarIdioma = new JButton();
		btnRemoverIdioma = new JButton();
		spTblIdioma = new JScrollPane();
		tblIdioma = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Hoja de Vida");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpHojaVida ========
		{

			//======== spDatosPersonales ========
			{

				//======== panelDatosPersonales ========
				{
					panelDatosPersonales.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(35)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX8),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX8),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.TOP, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));

					//---- btnImprimirDP ----
					btnImprimirDP.setText("Im");
					panelDatosPersonales.add(btnImprimirDP, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- lblEmpleado ----
					lblEmpleado.setText("Buscar Empleado:");
					panelDatosPersonales.add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- btnEmpleado ----
					btnEmpleado.setText("BE");
					panelDatosPersonales.add(btnEmpleado, cc.xywh(5, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelDatosPersonales.add(lblCodigo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtCodigo, cc.xy(5, 7));

					//---- lblEmpresa ----
					lblEmpresa.setText("Empresa:");
					panelDatosPersonales.add(lblEmpresa, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtEmpresa, cc.xywh(5, 9, 9, 1));

					//---- lblApellidoPaterno ----
					lblApellidoPaterno.setText("Apellidos: Paterno:");
					panelDatosPersonales.add(lblApellidoPaterno, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtApellidoPaterno, cc.xywh(5, 11, 3, 1));

					//---- lblApellidoMaterno ----
					lblApellidoMaterno.setText("Materno:");
					panelDatosPersonales.add(lblApellidoMaterno, cc.xywh(11, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtApellidoMaterno, cc.xy(13, 11));

					//---- lblNombres ----
					lblNombres.setText("Nombres:");
					panelDatosPersonales.add(lblNombres, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtNombres, cc.xywh(5, 13, 9, 1));

					//---- lblTitulo ----
					lblTitulo.setText("T\u00edtulo:");
					panelDatosPersonales.add(lblTitulo, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTitulo, cc.xywh(5, 15, 9, 1));

					//---- lblProfesion ----
					lblProfesion.setText("Profesi\u00f3n:");
					panelDatosPersonales.add(lblProfesion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtProfesion, cc.xywh(5, 17, 9, 1));

					//---- lblCedula ----
					lblCedula.setText("C\u00e9dula Identidad:");
					panelDatosPersonales.add(lblCedula, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtCedula, cc.xywh(5, 19, 3, 1));

					//---- lblNoIESS ----
					lblNoIESS.setText("No. I.E.S.S.");
					panelDatosPersonales.add(lblNoIESS, cc.xywh(11, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtNoIESS, cc.xy(13, 19));

					//---- lblLibMilitar ----
					lblLibMilitar.setText("Lib. Militar:");
					panelDatosPersonales.add(lblLibMilitar, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtLibMilitar, cc.xy(19, 19));

					//---- lblSexo ----
					lblSexo.setText("Sexo:");
					panelDatosPersonales.add(lblSexo, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbSexo, cc.xywh(5, 21, 3, 1));

					//---- lblTipoSangre ----
					lblTipoSangre.setText("Tipo de Sagre:");
					panelDatosPersonales.add(lblTipoSangre, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTipoSangre, cc.xy(13, 21));

					//---- lblEstadoCivil ----
					lblEstadoCivil.setText("Estado Civil:");
					panelDatosPersonales.add(lblEstadoCivil, cc.xywh(17, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbEstadoCivil, cc.xy(19, 21));

					//---- lblFechaNacimiento ----
					lblFechaNacimiento.setText("Fecha de Nacimiento:");
					panelDatosPersonales.add(lblFechaNacimiento, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbFechaNacimiento, cc.xywh(5, 23, 3, 1));

					//---- lblPais ----
					lblPais.setText("Pa\u00eds:");
					panelDatosPersonales.add(lblPais, cc.xywh(11, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbPais, cc.xy(13, 23));

					//---- lblProvincia ----
					lblProvincia.setText("Provincia:");
					panelDatosPersonales.add(lblProvincia, cc.xywh(17, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbProvincia, cc.xy(19, 23));

					//---- lblCanton ----
					lblCanton.setText("Cant\u00f3n:");
					panelDatosPersonales.add(lblCanton, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtCanton, cc.xywh(5, 25, 3, 1));

					//---- lblParroquia ----
					lblParroquia.setText("Parroquia:");
					panelDatosPersonales.add(lblParroquia, cc.xywh(11, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtParroquia, cc.xywh(13, 25, 7, 1));

					//---- lblDomicilio ----
					lblDomicilio.setText("Domicilio:");
					panelDatosPersonales.add(lblDomicilio, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtDomicilio, cc.xywh(5, 27, 9, 1));

					//---- lblCiudad ----
					lblCiudad.setText("Ciudad:");
					panelDatosPersonales.add(lblCiudad, cc.xywh(17, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbCiudad, cc.xy(19, 27));

					//---- lblTelefonoDomicilio ----
					lblTelefonoDomicilio.setText("Tel\u00e9fono Domicilio:");
					panelDatosPersonales.add(lblTelefonoDomicilio, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTelefonoDomicilio, cc.xywh(5, 29, 3, 1));

					//---- lblCelular ----
					lblCelular.setText("Celular:");
					panelDatosPersonales.add(lblCelular, cc.xywh(11, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtCelular, cc.xy(13, 29));

					//---- lblEmergencia ----
					lblEmergencia.setText("LLamar en Emergencia:");
					panelDatosPersonales.add(lblEmergencia, cc.xywh(3, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtEmergencia, cc.xywh(5, 31, 9, 1));

					//---- lblTelefonoEmergencia ----
					lblTelefonoEmergencia.setText("Tel\u00e9fono:");
					panelDatosPersonales.add(lblTelefonoEmergencia, cc.xywh(17, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTelefonoEmergencia, cc.xy(19, 31));

					//---- lblDireccionEmergencia ----
					lblDireccionEmergencia.setText("Direcci\u00f3n (Emergencia):");
					panelDatosPersonales.add(lblDireccionEmergencia, cc.xywh(3, 33, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtDireccionEmergencia, cc.xywh(5, 33, 9, 1));

					//---- lblCiudadEmergencia ----
					lblCiudadEmergencia.setText("Ciudad (Emerg.):");
					panelDatosPersonales.add(lblCiudadEmergencia, cc.xywh(17, 33, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(cmbCiudadEmergencia, cc.xy(19, 33));

					//---- lblTallaCamisa ----
					lblTallaCamisa.setText("Talla Camisa:");
					panelDatosPersonales.add(lblTallaCamisa, cc.xywh(3, 35, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTallaCamisa, cc.xywh(5, 35, 3, 1));

					//---- lblTallaPantalon ----
					lblTallaPantalon.setText("Talla Pantal\u00f3n:");
					panelDatosPersonales.add(lblTallaPantalon, cc.xywh(11, 35, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtTallaPantalon, cc.xy(13, 35));

					//---- lblNumeroCalzado ----
					lblNumeroCalzado.setText("No. Calzado:");
					panelDatosPersonales.add(lblNumeroCalzado, cc.xywh(17, 35, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtNumeroCalzado, cc.xy(19, 35));

					//---- lblEstatura ----
					lblEstatura.setText("Estatura:");
					panelDatosPersonales.add(lblEstatura, cc.xywh(3, 37, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtEstatura, cc.xywh(5, 37, 3, 1));

					//---- lblPeso ----
					lblPeso.setText("Peso:");
					panelDatosPersonales.add(lblPeso, cc.xywh(11, 37, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtPeso, cc.xy(13, 37));

					//---- lblColorPelo ----
					lblColorPelo.setText("Color de Pelo:");
					panelDatosPersonales.add(lblColorPelo, cc.xywh(17, 37, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtColorPelo, cc.xy(19, 37));

					//---- lblColorOjos ----
					lblColorOjos.setText("Color de ojos:");
					panelDatosPersonales.add(lblColorOjos, cc.xywh(3, 39, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtColorOjos, cc.xywh(5, 39, 3, 1));

					//---- lblColorPiel ----
					lblColorPiel.setText("Color de Piel:");
					panelDatosPersonales.add(lblColorPiel, cc.xywh(11, 39, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtColorPiel, cc.xy(13, 39));

					//---- lblSeniasParticulares ----
					lblSeniasParticulares.setText("Se\u00f1as Particulares:");
					panelDatosPersonales.add(lblSeniasParticulares, cc.xywh(3, 41, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDatosPersonales.add(txtSeniasParticulares, cc.xywh(5, 41, 9, 1));

					//---- lblExEstudianteCTT ----
					lblExEstudianteCTT.setText("Ex-Estud. CTT. (a\u00f1o salida):");
					panelDatosPersonales.add(lblExEstudianteCTT, cc.xy(3, 43));
					panelDatosPersonales.add(txtExEstudianteCTT, cc.xy(5, 43));
				}
				spDatosPersonales.setViewportView(panelDatosPersonales);
			}
			jtpHojaVida.addTab("Datos Personales", spDatosPersonales);


			//======== panelDatosFamiliares ========
			{
				panelDatosFamiliares.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX3),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- btnImprimirDF ----
				btnImprimirDF.setText("DF");
				panelDatosFamiliares.add(btnImprimirDF, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblParentezco ----
				lblParentezco.setText("Parentezco:");
				panelDatosFamiliares.add(lblParentezco, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbParentezco, cc.xywh(5, 5, 3, 1));

				//---- lblApellidosFamiliar ----
				lblApellidosFamiliar.setText("Apellidos:");
				panelDatosFamiliares.add(lblApellidosFamiliar, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(txtApellidosFamiliar, cc.xywh(5, 7, 5, 1));

				//---- lblNombresFamiliar ----
				lblNombresFamiliar.setText("Nombres:");
				panelDatosFamiliares.add(lblNombresFamiliar, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(txtNombresFamiliar, cc.xywh(15, 7, 3, 1));

				//---- lblFechaNacimientoFamiliar ----
				lblFechaNacimientoFamiliar.setText("Fecha de Nacimiento:");
				panelDatosFamiliares.add(lblFechaNacimientoFamiliar, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbFechaNacimientoFamiliar, cc.xywh(5, 9, 3, 1));

				//---- lblCedulaFamiliar ----
				lblCedulaFamiliar.setText("No. de C\u00e9dula:");
				panelDatosFamiliares.add(lblCedulaFamiliar, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(txtCedulaFamiliar, cc.xy(15, 9));

				//---- lblOcupacionFamiliar ----
				lblOcupacionFamiliar.setText("Ocupaci\u00f3n:");
				panelDatosFamiliares.add(lblOcupacionFamiliar, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(txtOcupacionFamiliar, cc.xywh(5, 11, 5, 1));

				//---- lblNivelEstudiosFamiliar ----
				lblNivelEstudiosFamiliar.setText("Nivel de Estudios:");
				panelDatosFamiliares.add(lblNivelEstudiosFamiliar, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbNivelEstudiosFamiliar, cc.xywh(5, 13, 3, 1));

				//---- lblAnioFamiliar ----
				lblAnioFamiliar.setText("A\u00f1o Aprobado:");
				panelDatosFamiliares.add(lblAnioFamiliar, cc.xywh(13, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbAnioFamiliar, cc.xy(15, 13));

				//---- lblTrabajaFamiliar ----
				lblTrabajaFamiliar.setText("Trabaja:");
				panelDatosFamiliares.add(lblTrabajaFamiliar, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbTrabajaFamiliar, cc.xy(5, 15));

				//---- lblNombreInstitucionFamiliar ----
				lblNombreInstitucionFamiliar.setText("Nombre Instituci\u00f3n:");
				panelDatosFamiliares.add(lblNombreInstitucionFamiliar, cc.xywh(13, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(txtNombreInstitucionFamiliar, cc.xywh(15, 15, 3, 1));

				//---- lblEsposaEmbarazada ----
				lblEsposaEmbarazada.setText("Embarazada:");
				panelDatosFamiliares.add(lblEsposaEmbarazada, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbEsposaEmbarazada, cc.xy(5, 17));

				//---- lblFechaParto ----
				lblFechaParto.setText("Posible Fecha de Parto:");
				panelDatosFamiliares.add(lblFechaParto, cc.xywh(13, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelDatosFamiliares.add(cmbFechaParto, cc.xy(15, 17));

				//======== panelBotonesDatoFamiliar ========
				{
					panelBotonesDatoFamiliar.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarDatoFamiliar ----
					btnAgregarDatoFamiliar.setText("A");
					panelBotonesDatoFamiliar.add(btnAgregarDatoFamiliar, cc.xy(1, 1));

					//---- btnActualizarDatoFamiliar ----
					btnActualizarDatoFamiliar.setText("U");
					panelBotonesDatoFamiliar.add(btnActualizarDatoFamiliar, cc.xy(3, 1));

					//---- btnRemoverDatoFamiliar ----
					btnRemoverDatoFamiliar.setText("D");
					panelBotonesDatoFamiliar.add(btnRemoverDatoFamiliar, cc.xy(5, 1));
				}
				panelDatosFamiliares.add(panelBotonesDatoFamiliar, cc.xywh(3, 21, 17, 1));

				//======== spTblDatosFamiliares ========
				{

					//---- tblDatosFamiliares ----
					tblDatosFamiliares.setPreferredScrollableViewportSize(new Dimension(450, 140));
					tblDatosFamiliares.setModel(new DefaultTableModel(
						new Object[][] {
							{null, "", null, null},
						},
						new String[] {
							"Nombre", "Fecha de Nacimiento", "Cedula Identidad", "Ocupacion"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblDatosFamiliares.setViewportView(tblDatosFamiliares);
				}
				panelDatosFamiliares.add(spTblDatosFamiliares, cc.xywh(3, 23, 17, 5));
			}
			jtpHojaVida.addTab("Datos Familiares", panelDatosFamiliares);


			//======== panelFormacionAcademica ========
			{
				panelFormacionAcademica.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- btnImprimirFA ----
				btnImprimirFA.setText("IA");
				panelFormacionAcademica.add(btnImprimirFA, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblNivelFormacion ----
				lblNivelFormacion.setText("Nivel:");
				panelFormacionAcademica.add(lblNivelFormacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(cmbNivelFormacion, cc.xy(5, 5));

				//---- lblAnioFormacion ----
				lblAnioFormacion.setText("A\u00f1o Aprobado:");
				panelFormacionAcademica.add(lblAnioFormacion, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(cmbAnioFormacion, cc.xy(11, 5));

				//---- lblFechaGraduado ----
				lblFechaGraduado.setText("Fecha Graduado:");
				panelFormacionAcademica.add(lblFechaGraduado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(cmbFechaGraduado, cc.xy(5, 7));

				//---- lblTituloFormacion ----
				lblTituloFormacion.setText("T\u00edtulo:");
				panelFormacionAcademica.add(lblTituloFormacion, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(txtTituloFormacion, cc.xywh(5, 9, 7, 1));

				//---- lblInstitucionFormacion ----
				lblInstitucionFormacion.setText("Instituci\u00f3n:");
				panelFormacionAcademica.add(lblInstitucionFormacion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(txtInstitucionFormacion, cc.xywh(5, 11, 7, 1));

				//---- lblPaisFormacion ----
				lblPaisFormacion.setText("Pa\u00eds:");
				panelFormacionAcademica.add(lblPaisFormacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(cmbPaisFormacion, cc.xy(5, 13));

				//---- lblCiudadFormacion ----
				lblCiudadFormacion.setText("Ciudad:");
				panelFormacionAcademica.add(lblCiudadFormacion, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelFormacionAcademica.add(cmbCiudadFormacion, cc.xy(11, 13));

				//======== panelBotonesFormacion ========
				{
					panelBotonesFormacion.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarFormacion ----
					btnAgregarFormacion.setText("A");
					panelBotonesFormacion.add(btnAgregarFormacion, cc.xy(1, 1));

					//---- btnActualizarFormacion ----
					btnActualizarFormacion.setText("U");
					panelBotonesFormacion.add(btnActualizarFormacion, cc.xy(3, 1));

					//---- btnRemoverFormacion ----
					btnRemoverFormacion.setText("D");
					panelBotonesFormacion.add(btnRemoverFormacion, cc.xy(5, 1));
				}
				panelFormacionAcademica.add(panelBotonesFormacion, cc.xywh(3, 17, 11, 1));

				//======== spTblFormacion ========
				{

					//---- tblFormacion ----
					tblFormacion.setPreferredScrollableViewportSize(new Dimension(450, 160));
					tblFormacion.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null},
						},
						new String[] {
							"Nivel", "A\u00f1o Aprobado", "Fecha Graduado", "Titulo Obtenido", "Institucion"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblFormacion.setViewportView(tblFormacion);
				}
				panelFormacionAcademica.add(spTblFormacion, cc.xywh(3, 19, 11, 5));
			}
			jtpHojaVida.addTab("Formaci\u00f3n Acad\u00e9mica", panelFormacionAcademica);


			//======== panelIdiomas ========
			{
				panelIdiomas.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- btnImprimirIdiomas ----
				btnImprimirIdiomas.setText("II");
				panelIdiomas.add(btnImprimirIdiomas, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblIdioma ----
				lblIdioma.setText("Idioma:");
				panelIdiomas.add(lblIdioma, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIdiomas.add(cmbIdioma, cc.xy(5, 5));

				//---- lblHabla ----
				lblHabla.setText("Habla:");
				panelIdiomas.add(lblHabla, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIdiomas.add(cmbHabla, cc.xy(5, 7));

				//---- lblComprende ----
				lblComprende.setText("Comprende:");
				panelIdiomas.add(lblComprende, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIdiomas.add(cmbComprende, cc.xy(11, 7));

				//---- lblLee ----
				lblLee.setText("Lee:");
				panelIdiomas.add(lblLee, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIdiomas.add(cmbLee, cc.xy(5, 9));

				//---- lblEscribe ----
				lblEscribe.setText("Escribe:");
				panelIdiomas.add(lblEscribe, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIdiomas.add(cmbEscribe, cc.xy(11, 9));

				//======== panelBotonesIdioma ========
				{
					panelBotonesIdioma.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarIdioma ----
					btnAgregarIdioma.setText("A");
					panelBotonesIdioma.add(btnAgregarIdioma, cc.xy(1, 1));

					//---- btnActualizarIdioma ----
					btnActualizarIdioma.setText("U");
					panelBotonesIdioma.add(btnActualizarIdioma, cc.xy(3, 1));

					//---- btnRemoverIdioma ----
					btnRemoverIdioma.setText("D");
					panelBotonesIdioma.add(btnRemoverIdioma, cc.xy(5, 1));
				}
				panelIdiomas.add(panelBotonesIdioma, cc.xywh(3, 13, 11, 1));

				//======== spTblIdioma ========
				{

					//---- tblIdioma ----
					tblIdioma.setPreferredScrollableViewportSize(new Dimension(450, 160));
					tblIdioma.setModel(new DefaultTableModel(
						new Object[][] {
							{null, "", "", null, null},
						},
						new String[] {
							"Idioma", "Habla", "Comprende", "Lee", "Escribe"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblIdioma.setViewportView(tblIdioma);
				}
				panelIdiomas.add(spTblIdioma, cc.xywh(3, 15, 11, 5));
			}
			jtpHojaVida.addTab("Idiomas", panelIdiomas);

		}
		add(jtpHojaVida, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpHojaVida;
	private JScrollPane spDatosPersonales;
	private JPanel panelDatosPersonales;
	private JButton btnImprimirDP;
	private JLabel lblEmpleado;
	private JButton btnEmpleado;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblEmpresa;
	private JTextField txtEmpresa;
	private JLabel lblApellidoPaterno;
	private JTextField txtApellidoPaterno;
	private JLabel lblApellidoMaterno;
	private JTextField txtApellidoMaterno;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	private JLabel lblProfesion;
	private JTextField txtProfesion;
	private JLabel lblCedula;
	private JTextField txtCedula;
	private JLabel lblNoIESS;
	private JTextField txtNoIESS;
	private JLabel lblLibMilitar;
	private JTextField txtLibMilitar;
	private JLabel lblSexo;
	private JComboBox cmbSexo;
	private JLabel lblTipoSangre;
	private JTextField txtTipoSangre;
	private JLabel lblEstadoCivil;
	private JComboBox cmbEstadoCivil;
	private JLabel lblFechaNacimiento;
	private DateComboBox cmbFechaNacimiento;
	private JLabel lblPais;
	private JComboBox cmbPais;
	private JLabel lblProvincia;
	private JComboBox cmbProvincia;
	private JLabel lblCanton;
	private JTextField txtCanton;
	private JLabel lblParroquia;
	private JTextField txtParroquia;
	private JLabel lblDomicilio;
	private JTextField txtDomicilio;
	private JLabel lblCiudad;
	private JComboBox cmbCiudad;
	private JLabel lblTelefonoDomicilio;
	private JTextField txtTelefonoDomicilio;
	private JLabel lblCelular;
	private JTextField txtCelular;
	private JLabel lblEmergencia;
	private JTextField txtEmergencia;
	private JLabel lblTelefonoEmergencia;
	private JTextField txtTelefonoEmergencia;
	private JLabel lblDireccionEmergencia;
	private JTextField txtDireccionEmergencia;
	private JLabel lblCiudadEmergencia;
	private JComboBox cmbCiudadEmergencia;
	private JLabel lblTallaCamisa;
	private JTextField txtTallaCamisa;
	private JLabel lblTallaPantalon;
	private JTextField txtTallaPantalon;
	private JLabel lblNumeroCalzado;
	private JTextField txtNumeroCalzado;
	private JLabel lblEstatura;
	private JTextField txtEstatura;
	private JLabel lblPeso;
	private JTextField txtPeso;
	private JLabel lblColorPelo;
	private JTextField txtColorPelo;
	private JLabel lblColorOjos;
	private JTextField txtColorOjos;
	private JLabel lblColorPiel;
	private JTextField txtColorPiel;
	private JLabel lblSeniasParticulares;
	private JTextField txtSeniasParticulares;
	private JLabel lblExEstudianteCTT;
	private JTextField txtExEstudianteCTT;
	private JPanel panelDatosFamiliares;
	private JButton btnImprimirDF;
	private JLabel lblParentezco;
	private JComboBox cmbParentezco;
	private JLabel lblApellidosFamiliar;
	private JTextField txtApellidosFamiliar;
	private JLabel lblNombresFamiliar;
	private JTextField txtNombresFamiliar;
	private JLabel lblFechaNacimientoFamiliar;
	private DateComboBox cmbFechaNacimientoFamiliar;
	private JLabel lblCedulaFamiliar;
	private JTextField txtCedulaFamiliar;
	private JLabel lblOcupacionFamiliar;
	private JTextField txtOcupacionFamiliar;
	private JLabel lblNivelEstudiosFamiliar;
	private JComboBox cmbNivelEstudiosFamiliar;
	private JLabel lblAnioFamiliar;
	private JComboBox cmbAnioFamiliar;
	private JLabel lblTrabajaFamiliar;
	private JComboBox cmbTrabajaFamiliar;
	private JLabel lblNombreInstitucionFamiliar;
	private JTextField txtNombreInstitucionFamiliar;
	private JLabel lblEsposaEmbarazada;
	private JComboBox cmbEsposaEmbarazada;
	private JLabel lblFechaParto;
	private DateComboBox cmbFechaParto;
	private JPanel panelBotonesDatoFamiliar;
	private JButton btnAgregarDatoFamiliar;
	private JButton btnActualizarDatoFamiliar;
	private JButton btnRemoverDatoFamiliar;
	private JScrollPane spTblDatosFamiliares;
	private JTable tblDatosFamiliares;
	private JPanel panelFormacionAcademica;
	private JButton btnImprimirFA;
	private JLabel lblNivelFormacion;
	private JComboBox cmbNivelFormacion;
	private JLabel lblAnioFormacion;
	private JComboBox cmbAnioFormacion;
	private JLabel lblFechaGraduado;
	private DateComboBox cmbFechaGraduado;
	private JLabel lblTituloFormacion;
	private JTextField txtTituloFormacion;
	private JLabel lblInstitucionFormacion;
	private JTextField txtInstitucionFormacion;
	private JLabel lblPaisFormacion;
	private JComboBox cmbPaisFormacion;
	private JLabel lblCiudadFormacion;
	private JComboBox cmbCiudadFormacion;
	private JPanel panelBotonesFormacion;
	private JButton btnAgregarFormacion;
	private JButton btnActualizarFormacion;
	private JButton btnRemoverFormacion;
	private JScrollPane spTblFormacion;
	private JTable tblFormacion;
	private JPanel panelIdiomas;
	private JButton btnImprimirIdiomas;
	private JLabel lblIdioma;
	private JComboBox cmbIdioma;
	private JLabel lblHabla;
	private JComboBox cmbHabla;
	private JLabel lblComprende;
	private JComboBox cmbComprende;
	private JLabel lblLee;
	private JComboBox cmbLee;
	private JLabel lblEscribe;
	private JComboBox cmbEscribe;
	private JPanel panelBotonesIdioma;
	private JButton btnAgregarIdioma;
	private JButton btnActualizarIdioma;
	private JButton btnRemoverIdioma;
	private JScrollPane spTblIdioma;
	private JTable tblIdioma;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpHojaVida() {
		return jtpHojaVida;
	}

	public JScrollPane getSpDatosPersonales() {
		return spDatosPersonales;
	}

	public JPanel getPanelDatosPersonales() {
		return panelDatosPersonales;
	}

	public JButton getBtnImprimirDP() {
		return btnImprimirDP;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblEmpresa() {
		return lblEmpresa;
	}

	public JTextField getTxtEmpresa() {
		return txtEmpresa;
	}

	public JLabel getLblApellidoPaterno() {
		return lblApellidoPaterno;
	}

	public JTextField getTxtApellidoPaterno() {
		return txtApellidoPaterno;
	}

	public JLabel getLblApellidoMaterno() {
		return lblApellidoMaterno;
	}

	public JTextField getTxtApellidoMaterno() {
		return txtApellidoMaterno;
	}

	public JLabel getLblNombres() {
		return lblNombres;
	}

	public JTextField getTxtNombres() {
		return txtNombres;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public JLabel getLblProfesion() {
		return lblProfesion;
	}

	public JTextField getTxtProfesion() {
		return txtProfesion;
	}

	public JLabel getLblCedula() {
		return lblCedula;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public JLabel getLblNoIESS() {
		return lblNoIESS;
	}

	public JTextField getTxtNoIESS() {
		return txtNoIESS;
	}

	public JLabel getLblLibMilitar() {
		return lblLibMilitar;
	}

	public JTextField getTxtLibMilitar() {
		return txtLibMilitar;
	}

	public JLabel getLblSexo() {
		return lblSexo;
	}

	public JComboBox getCmbSexo() {
		return cmbSexo;
	}

	public JLabel getLblTipoSangre() {
		return lblTipoSangre;
	}

	public JTextField getTxtTipoSangre() {
		return txtTipoSangre;
	}

	public JLabel getLblEstadoCivil() {
		return lblEstadoCivil;
	}

	public JComboBox getCmbEstadoCivil() {
		return cmbEstadoCivil;
	}

	public JLabel getLblFechaNacimiento() {
		return lblFechaNacimiento;
	}

	public DateComboBox getCmbFechaNacimiento() {
		return cmbFechaNacimiento;
	}

	public JLabel getLblPais() {
		return lblPais;
	}

	public JComboBox getCmbPais() {
		return cmbPais;
	}

	public JLabel getLblProvincia() {
		return lblProvincia;
	}

	public JComboBox getCmbProvincia() {
		return cmbProvincia;
	}

	public JLabel getLblCanton() {
		return lblCanton;
	}

	public JTextField getTxtCanton() {
		return txtCanton;
	}

	public JLabel getLblParroquia() {
		return lblParroquia;
	}

	public JTextField getTxtParroquia() {
		return txtParroquia;
	}

	public JLabel getLblDomicilio() {
		return lblDomicilio;
	}

	public JTextField getTxtDomicilio() {
		return txtDomicilio;
	}

	public JLabel getLblCiudad() {
		return lblCiudad;
	}

	public JComboBox getCmbCiudad() {
		return cmbCiudad;
	}

	public JLabel getLblTelefonoDomicilio() {
		return lblTelefonoDomicilio;
	}

	public JTextField getTxtTelefonoDomicilio() {
		return txtTelefonoDomicilio;
	}

	public JLabel getLblCelular() {
		return lblCelular;
	}

	public JTextField getTxtCelular() {
		return txtCelular;
	}

	public JLabel getLblEmergencia() {
		return lblEmergencia;
	}

	public JTextField getTxtEmergencia() {
		return txtEmergencia;
	}

	public JLabel getLblTelefonoEmergencia() {
		return lblTelefonoEmergencia;
	}

	public JTextField getTxtTelefonoEmergencia() {
		return txtTelefonoEmergencia;
	}

	public JLabel getLblDireccionEmergencia() {
		return lblDireccionEmergencia;
	}

	public JTextField getTxtDireccionEmergencia() {
		return txtDireccionEmergencia;
	}

	public JLabel getLblCiudadEmergencia() {
		return lblCiudadEmergencia;
	}

	public JComboBox getCmbCiudadEmergencia() {
		return cmbCiudadEmergencia;
	}

	public JLabel getLblTallaCamisa() {
		return lblTallaCamisa;
	}

	public JTextField getTxtTallaCamisa() {
		return txtTallaCamisa;
	}

	public JLabel getLblTallaPantalon() {
		return lblTallaPantalon;
	}

	public JTextField getTxtTallaPantalon() {
		return txtTallaPantalon;
	}

	public JLabel getLblNumeroCalzado() {
		return lblNumeroCalzado;
	}

	public JTextField getTxtNumeroCalzado() {
		return txtNumeroCalzado;
	}

	public JLabel getLblEstatura() {
		return lblEstatura;
	}

	public JTextField getTxtEstatura() {
		return txtEstatura;
	}

	public JLabel getLblPeso() {
		return lblPeso;
	}

	public JTextField getTxtPeso() {
		return txtPeso;
	}

	public JLabel getLblColorPelo() {
		return lblColorPelo;
	}

	public JTextField getTxtColorPelo() {
		return txtColorPelo;
	}

	public JLabel getLblColorOjos() {
		return lblColorOjos;
	}

	public JTextField getTxtColorOjos() {
		return txtColorOjos;
	}

	public JLabel getLblColorPiel() {
		return lblColorPiel;
	}

	public JTextField getTxtColorPiel() {
		return txtColorPiel;
	}

	public JLabel getLblSeniasParticulares() {
		return lblSeniasParticulares;
	}

	public JTextField getTxtSeniasParticulares() {
		return txtSeniasParticulares;
	}

	public JLabel getLblExEstudianteCTT() {
		return lblExEstudianteCTT;
	}

	public JTextField getTxtExEstudianteCTT() {
		return txtExEstudianteCTT;
	}

	public JPanel getPanelDatosFamiliares() {
		return panelDatosFamiliares;
	}

	public JButton getBtnImprimirDF() {
		return btnImprimirDF;
	}

	public JLabel getLblParentezco() {
		return lblParentezco;
	}

	public JComboBox getCmbParentezco() {
		return cmbParentezco;
	}

	public JLabel getLblApellidosFamiliar() {
		return lblApellidosFamiliar;
	}

	public JTextField getTxtApellidosFamiliar() {
		return txtApellidosFamiliar;
	}

	public JLabel getLblNombresFamiliar() {
		return lblNombresFamiliar;
	}

	public JTextField getTxtNombresFamiliar() {
		return txtNombresFamiliar;
	}

	public JLabel getLblFechaNacimientoFamiliar() {
		return lblFechaNacimientoFamiliar;
	}

	public DateComboBox getCmbFechaNacimientoFamiliar() {
		return cmbFechaNacimientoFamiliar;
	}

	public JLabel getLblCedulaFamiliar() {
		return lblCedulaFamiliar;
	}

	public JTextField getTxtCedulaFamiliar() {
		return txtCedulaFamiliar;
	}

	public JLabel getLblOcupacionFamiliar() {
		return lblOcupacionFamiliar;
	}

	public JTextField getTxtOcupacionFamiliar() {
		return txtOcupacionFamiliar;
	}

	public JLabel getLblNivelEstudiosFamiliar() {
		return lblNivelEstudiosFamiliar;
	}

	public JComboBox getCmbNivelEstudiosFamiliar() {
		return cmbNivelEstudiosFamiliar;
	}

	public JLabel getLblAnioFamiliar() {
		return lblAnioFamiliar;
	}

	public JComboBox getCmbAnioFamiliar() {
		return cmbAnioFamiliar;
	}

	public JLabel getLblTrabajaFamiliar() {
		return lblTrabajaFamiliar;
	}

	public JComboBox getCmbTrabajaFamiliar() {
		return cmbTrabajaFamiliar;
	}

	public JLabel getLblNombreInstitucionFamiliar() {
		return lblNombreInstitucionFamiliar;
	}

	public JTextField getTxtNombreInstitucionFamiliar() {
		return txtNombreInstitucionFamiliar;
	}

	public JLabel getLblEsposaEmbarazada() {
		return lblEsposaEmbarazada;
	}

	public JComboBox getCmbEsposaEmbarazada() {
		return cmbEsposaEmbarazada;
	}

	public JLabel getLblFechaParto() {
		return lblFechaParto;
	}

	public DateComboBox getCmbFechaParto() {
		return cmbFechaParto;
	}

	public JPanel getPanelBotonesDatoFamiliar() {
		return panelBotonesDatoFamiliar;
	}

	public JButton getBtnAgregarDatoFamiliar() {
		return btnAgregarDatoFamiliar;
	}

	public JButton getBtnActualizarDatoFamiliar() {
		return btnActualizarDatoFamiliar;
	}

	public JButton getBtnRemoverDatoFamiliar() {
		return btnRemoverDatoFamiliar;
	}

	public JScrollPane getSpTblDatosFamiliares() {
		return spTblDatosFamiliares;
	}

	public JTable getTblDatosFamiliares() {
		return tblDatosFamiliares;
	}

	public JPanel getPanelFormacionAcademica() {
		return panelFormacionAcademica;
	}

	public JButton getBtnImprimirFA() {
		return btnImprimirFA;
	}

	public JLabel getLblNivelFormacion() {
		return lblNivelFormacion;
	}

	public JComboBox getCmbNivelFormacion() {
		return cmbNivelFormacion;
	}

	public JLabel getLblAnioFormacion() {
		return lblAnioFormacion;
	}

	public JComboBox getCmbAnioFormacion() {
		return cmbAnioFormacion;
	}

	public JLabel getLblFechaGraduado() {
		return lblFechaGraduado;
	}

	public DateComboBox getCmbFechaGraduado() {
		return cmbFechaGraduado;
	}

	public JLabel getLblTituloFormacion() {
		return lblTituloFormacion;
	}

	public JTextField getTxtTituloFormacion() {
		return txtTituloFormacion;
	}

	public JLabel getLblInstitucionFormacion() {
		return lblInstitucionFormacion;
	}

	public JTextField getTxtInstitucionFormacion() {
		return txtInstitucionFormacion;
	}

	public JLabel getLblPaisFormacion() {
		return lblPaisFormacion;
	}

	public JComboBox getCmbPaisFormacion() {
		return cmbPaisFormacion;
	}

	public JLabel getLblCiudadFormacion() {
		return lblCiudadFormacion;
	}

	public JComboBox getCmbCiudadFormacion() {
		return cmbCiudadFormacion;
	}

	public JPanel getPanelBotonesFormacion() {
		return panelBotonesFormacion;
	}

	public JButton getBtnAgregarFormacion() {
		return btnAgregarFormacion;
	}

	public JButton getBtnActualizarFormacion() {
		return btnActualizarFormacion;
	}

	public JButton getBtnRemoverFormacion() {
		return btnRemoverFormacion;
	}

	public JScrollPane getSpTblFormacion() {
		return spTblFormacion;
	}

	public JTable getTblFormacion() {
		return tblFormacion;
	}

	public JPanel getPanelIdiomas() {
		return panelIdiomas;
	}

	public JButton getBtnImprimirIdiomas() {
		return btnImprimirIdiomas;
	}

	public JLabel getLblIdioma() {
		return lblIdioma;
	}

	public JComboBox getCmbIdioma() {
		return cmbIdioma;
	}

	public JLabel getLblHabla() {
		return lblHabla;
	}

	public JComboBox getCmbHabla() {
		return cmbHabla;
	}

	public JLabel getLblComprende() {
		return lblComprende;
	}

	public JComboBox getCmbComprende() {
		return cmbComprende;
	}

	public JLabel getLblLee() {
		return lblLee;
	}

	public JComboBox getCmbLee() {
		return cmbLee;
	}

	public JLabel getLblEscribe() {
		return lblEscribe;
	}

	public JComboBox getCmbEscribe() {
		return cmbEscribe;
	}

	public JPanel getPanelBotonesIdioma() {
		return panelBotonesIdioma;
	}

	public JButton getBtnAgregarIdioma() {
		return btnAgregarIdioma;
	}

	public JButton getBtnActualizarIdioma() {
		return btnActualizarIdioma;
	}

	public JButton getBtnRemoverIdioma() {
		return btnRemoverIdioma;
	}

	public JScrollPane getSpTblIdioma() {
		return spTblIdioma;
	}

	public JTable getTblIdioma() {
		return tblIdioma;
	}
}
