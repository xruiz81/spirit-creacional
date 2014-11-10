package com.spirit.general.gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

/**
 * @author xruiz
 */
public abstract class JPNoticias extends SpiritModelImpl {
	public JPNoticias() {
		initComponents();
		setName("Noticia");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		spPanelNoticia = new JScrollPane();
		panelNoticias = new JPanel();
		spUsuarios = new JScrollPane();
		listUsuarios = new JList();
		spDestinatarios = new JScrollPane();
		listDestinatarios = new JList();
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblStatus = new JLabel();
		cmbStatus = new JComboBox();
		cmbFechaInicio = new DateComboBox();
		txtUsuario = new JTextField();
		lblUsuario = new JLabel();
		lblAsunto = new JLabel();
		fsNoticia = compFactory.createSeparator("Noticia");
		txtAsunto = new JTextField();
		spNoticia = new JScrollPane();
		txtNoticia = new JTextArea();
		lblArchivo = new JLabel();
		txtArchivo = new JTextField();
		btnArchivo = new JButton();
		lblDestinatarios = new JLabel();
		lblUsuarios = new JLabel();
		btnElegir = new JButton();
		btnTodos = new JButton();
		btnQuitar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== spPanelNoticia ========
		{
			
			//======== panelNoticias ========
			{
				panelNoticias.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;25dlu)"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(72)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(20)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("max(default;30dlu):grow"),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY3),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				
				//---- lblFechaInicio ----
				lblFechaInicio.setText("Fecha Inicio:");
				panelNoticias.add(lblFechaInicio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelNoticias.add(cmbFechaInicio, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//======== spUsuarios ========
				{
					
					//---- listUsuarios ----
					listUsuarios.setBorder(new EtchedBorder());
					spUsuarios.setViewportView(listUsuarios);
				}
				panelNoticias.add(spUsuarios, cc.xywh(3, 25, 5, 9));
				
				//======== spDestinatarios ========
				{
					
					//---- listDestinatarios ----
					listDestinatarios.setBorder(new EtchedBorder());
					spDestinatarios.setViewportView(listDestinatarios);
				}
				panelNoticias.add(spDestinatarios, cc.xywh(15, 25, 7, 9));
				
				//---- lblFechaFin ----
				lblFechaFin.setText("FechaFin:");
				panelNoticias.add(lblFechaFin, cc.xywh(9, 3, 5, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelNoticias.add(cmbFechaFin, cc.xywh(15, 3, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- lblUsuario ----
				lblUsuario.setText("Usuario:");
				panelNoticias.add(lblUsuario, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelNoticias.add(txtUsuario, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- lblStatus ----
				lblStatus.setText("Status:");
				panelNoticias.add(lblStatus, cc.xywh(11, 5, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelNoticias.add(cmbStatus, cc.xy(15, 5));
				panelNoticias.add(fsNoticia, cc.xywh(3, 9, 17, 1));
				
				//---- lblAsunto ----
				lblAsunto.setText("Asunto:");
				panelNoticias.add(lblAsunto, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelNoticias.add(txtAsunto, cc.xywh(5, 11, 17, 1));
				
				//---- lblArchivo ----
				lblArchivo.setText("Archivo:");
				panelNoticias.add(lblArchivo, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//======== spNoticia ========
				{
					spNoticia.setViewportView(txtNoticia);
				}
				panelNoticias.add(spNoticia, cc.xywh(3, 13, 19, 3));
				panelNoticias.add(txtArchivo, cc.xywh(5, 19, 15, 1));
				panelNoticias.add(btnArchivo, cc.xywh(21, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- lblUsuarios ----
				lblUsuarios.setText("Usuarios");
				panelNoticias.add(lblUsuarios, cc.xywh(3, 23, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				
				//---- lblDestinatarios ----
				lblDestinatarios.setText("Destinatarios");
				panelNoticias.add(lblDestinatarios, cc.xywh(15, 23, 3, 1));
				
				//---- btnElegir ----
				btnElegir.setText(">");
				panelNoticias.add(btnElegir, cc.xy(11, 27));
				
				//---- btnTodos ----
				btnTodos.setText(">>");
				panelNoticias.add(btnTodos, cc.xy(11, 29));
				
				//---- btnQuitar ----
				btnQuitar.setText("<");
				panelNoticias.add(btnQuitar, cc.xy(11, 31));
			}
			spPanelNoticia.setViewportView(panelNoticias);
		}
		add(spPanelNoticia, cc.xywh(3, 3, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents

		//---- btnArchivo ----
		btnArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		btnArchivo.setToolTipText("Agregar archivo a su Noticia");

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spPanelNoticia;
	private JPanel panelNoticias;
	private JScrollPane spUsuarios;
	private JList listUsuarios;
	private JScrollPane spDestinatarios;
	private JList listDestinatarios;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblStatus;
	private JComboBox cmbStatus;
	private DateComboBox cmbFechaInicio;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblAsunto;
	private JComponent fsNoticia;
	private JTextField txtAsunto;
	private JScrollPane spNoticia;
	private JTextArea txtNoticia;
	private JLabel lblArchivo;
	private JTextField txtArchivo;
	private JButton btnArchivo;
	private JLabel lblDestinatarios;
	private JLabel lblUsuarios;
	private JButton btnElegir;
	private JButton btnTodos;
	private JButton btnQuitar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JButton getBtnArchivo() {
		return btnArchivo;
	}

	public void setBtnArchivo(JButton btnArchivo) {
		this.btnArchivo = btnArchivo;
	}

	public JButton getBtnElegir() {
		return btnElegir;
	}

	public void setBtnElegir(JButton btnElegir) {
		this.btnElegir = btnElegir;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JButton getBtnTodos() {
		return btnTodos;
	}

	public void setBtnTodos(JButton btnTodos) {
		this.btnTodos = btnTodos;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JComponent getFsNoticia() {
		return fsNoticia;
	}

	public void setFsNoticia(JComponent fsNoticia) {
		this.fsNoticia = fsNoticia;
	}

	public JList getListDestinatarios() {
		return listDestinatarios;
	}

	public void setListDestinatarios(JList listDestinatarios) {
		this.listDestinatarios = listDestinatarios;
	}

	public JList getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(JList listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public JScrollPane getSpDestinatarios() {
		return spDestinatarios;
	}

	public void setSpDestinatarios(JScrollPane spDestinatarios) {
		this.spDestinatarios = spDestinatarios;
	}

	public JScrollPane getSpNoticia() {
		return spNoticia;
	}

	public void setSpNoticia(JScrollPane spNoticia) {
		this.spNoticia = spNoticia;
	}

	public JScrollPane getSpUsuarios() {
		return spUsuarios;
	}

	public void setSpUsuarios(JScrollPane spUsuarios) {
		this.spUsuarios = spUsuarios;
	}

	public JTextField getTxtArchivo() {
		return txtArchivo;
	}

	public void setTxtArchivo(JTextField txtArchivo) {
		this.txtArchivo = txtArchivo;
	}

	public JTextField getTxtAsunto() {
		return txtAsunto;
	}

	public void setTxtAsunto(JTextField txtAsunto) {
		this.txtAsunto = txtAsunto;
	}

	public JTextArea getTxtNoticia() {
		return txtNoticia;
	}

	public void setTxtNoticia(JTextArea txtNoticia) {
		this.txtNoticia = txtNoticia;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JLabel getLblArchivo() {
		return lblArchivo;
	}

	public void setLblArchivo(JLabel lblArchivo) {
		this.lblArchivo = lblArchivo;
	}

	public JLabel getLblAsunto() {
		return lblAsunto;
	}

	public void setLblAsunto(JLabel lblAsunto) {
		this.lblAsunto = lblAsunto;
	}

	public JLabel getLblDestinatarios() {
		return lblDestinatarios;
	}

	public void setLblDestinatarios(JLabel lblDestinatarios) {
		this.lblDestinatarios = lblDestinatarios;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(JLabel lblStatus) {
		this.lblStatus = lblStatus;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblUsuarios() {
		return lblUsuarios;
	}

	public void setLblUsuarios(JLabel lblUsuarios) {
		this.lblUsuarios = lblUsuarios;
	}
}
