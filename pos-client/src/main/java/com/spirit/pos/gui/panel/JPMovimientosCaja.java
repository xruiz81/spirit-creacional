package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPMovimientosCaja extends SpiritModelImpl {
	public JPMovimientosCaja() {
		initComponents();
		setName("Movimientos Caja");
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spMovimientoCaja = new JPanel();
		txtAviso = new JTextPane();
		lblFecha = new JLabel();
		txtFecha = new JLabel();
		lblCajero = new JLabel();
		txtCajero = new JLabel();
		lblTuno = new JLabel();
		cmbTurno = new JComboBox();
		lblCajaRegistro = new JLabel();
		cmbCajaRegistro = new JComboBox();
		separator1 = new JSeparator();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblMotivo = new JLabel();
		cmbMotivo = new JComboBox();
		pnBanco = new JPanel();
		cmbBanco = new JComboBox();
		lblCuenta = new JLabel();
		cmbCuenta = new JComboBox();
		lblBanco = new JLabel();
		pnCajas = new JPanel();
		lblCajaDestino = new JLabel();
		cmbCajaDestino = new JComboBox();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblObservacion = new JLabel();
		txtObservacion = new JTextArea();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(new Color(236, 233, 216));
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.CENTER, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spMovimientoCaja ========
		{
			spMovimientoCaja.setBorder(new LineBorder(new Color(0, 102, 0), 2));
			spMovimientoCaja.setBackground(new Color(236, 233, 216));
			spMovimientoCaja.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(55)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(55)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.dluY(12), FormSpec.DEFAULT_GROW)
				}));
			
			//---- txtAviso ----
			txtAviso.setText("El usuario tiene una caja abierta debe primero cerrar la otra para poder abrir otra");
			txtAviso.setEditable(false);
			txtAviso.setFont(new Font("Tahoma", Font.PLAIN, 12));
			spMovimientoCaja.add(txtAviso, cc.xywh(3, 3, 5, 1, CellConstraints.FILL, CellConstraints.FILL));
			
			//---- lblFecha ----
			lblFecha.setText("Fecha:");
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblFecha, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtFecha ----
			txtFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
			spMovimientoCaja.add(txtFecha, cc.xywh(5, 5, 5, 1));
			
			//---- lblCajero ----
			lblCajero.setText("Cajero:");
			lblCajero.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblCajero, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtCajero ----
			txtCajero.setFont(new Font("Tahoma", Font.BOLD, 11));
			spMovimientoCaja.add(txtCajero, cc.xywh(5, 7, 5, 1));
			
			//---- lblTuno ----
			lblTuno.setText("Turno:");
			lblTuno.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblTuno, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- cmbTurno ----
			cmbTurno.setModel(new DefaultComboBoxModel(new String[] {
				"Turno 1:     8:00  -  13:00",
				"Turno 2:    13:00 -  18:00",
				"Turno 3:    18:00 -  22:00"
			}));
			cmbTurno.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spMovimientoCaja.add(cmbTurno, cc.xywh(5, 9, 3, 1));
			
			//---- lblCajaRegistro ----
			lblCajaRegistro.setText("Caja Registro:");
			lblCajaRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblCajaRegistro, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- cmbCajaRegistro ----
			cmbCajaRegistro.setEditable(false);
			spMovimientoCaja.add(cmbCajaRegistro, cc.xywh(5, 11, 3, 1));
			spMovimientoCaja.add(separator1, cc.xywh(3, 13, 6, 1));
			
			//---- lblTipo ----
			lblTipo.setText("Tipo");
			lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblTipo, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- cmbTipo ----
			cmbTipo.setModel(new DefaultComboBoxModel(new String[] {
				"[Entrada] Ingreso efectivo",
				"[Salida]  Egreso efectivo"
			}));
			cmbTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spMovimientoCaja.add(cmbTipo, cc.xywh(5, 15, 3, 1));
			
			//---- lblMotivo ----
			lblMotivo.setText("Motivo:");
			lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblMotivo, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- cmbMotivo ----
			cmbMotivo.setModel(new DefaultComboBoxModel(new String[] {
				"Transferencia Bancaria",
				"Transferencia Caja"
			}));
			spMovimientoCaja.add(cmbMotivo, cc.xywh(5, 17, 3, 1));
			
			//======== pnBanco ========
			{
				pnBanco.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(55)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				pnBanco.add(cmbBanco, cc.xy(3, 1));
				
				//---- lblCuenta ----
				lblCuenta.setText("Cuenta:");
				lblCuenta.setFont(new Font("Tahoma", Font.BOLD, 12));
				pnBanco.add(lblCuenta, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				pnBanco.add(cmbCuenta, cc.xy(3, 3));
				
				//---- lblBanco ----
				lblBanco.setText("Banco:");
				lblBanco.setFont(new Font("Tahoma", Font.BOLD, 12));
				pnBanco.add(lblBanco, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			spMovimientoCaja.add(pnBanco, cc.xywh(3, 19, 5, 1));
			
			//======== pnCajas ========
			{
				pnCajas.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(55)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblCajaDestino ----
				lblCajaDestino.setText("Caja:");
				lblCajaDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
				pnCajas.add(lblCajaDestino, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				pnCajas.add(cmbCajaDestino, cc.xy(3, 1));
			}
			spMovimientoCaja.add(pnCajas, cc.xywh(3, 21, 5, 1));
			
			//---- lblValor ----
			lblValor.setText("Valor:");
			lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblValor, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtValor ----
			txtValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spMovimientoCaja.add(txtValor, cc.xy(5, 23));
			
			//---- lblObservacion ----
			lblObservacion.setText("Observaci\u00f3n:");
			lblObservacion.setFont(new Font("Tahoma", Font.BOLD, 12));
			spMovimientoCaja.add(lblObservacion, cc.xy(3, 25));
			
			//---- txtObservacion ----
			txtObservacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtObservacion.setLineWrap(true);
			spMovimientoCaja.add(txtObservacion, cc.xywh(5, 25, 3, 3, CellConstraints.FILL, CellConstraints.FILL));
		}
		add(spMovimientoCaja, cc.xywh(3, 3, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel spMovimientoCaja;
	private JTextPane txtAviso;
	private JLabel lblFecha;
	private JLabel txtFecha;
	private JLabel lblCajero;
	private JLabel txtCajero;
	private JLabel lblTuno;
	private JComboBox cmbTurno;
	private JLabel lblCajaRegistro;
	private JComboBox cmbCajaRegistro;
	private JSeparator separator1;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblMotivo;
	private JComboBox cmbMotivo;
	private JPanel pnBanco;
	private JComboBox cmbBanco;
	private JLabel lblCuenta;
	private JComboBox cmbCuenta;
	private JLabel lblBanco;
	private JPanel pnCajas;
	private JLabel lblCajaDestino;
	private JComboBox cmbCajaDestino;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblObservacion;
	private JTextArea txtObservacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getSpMovimientoCaja() {
		return spMovimientoCaja;
	}


	public void setSpMovimientoCaja(JPanel spMovimientoCaja) {
		this.spMovimientoCaja = spMovimientoCaja;
	}


	public JTextPane getTxtAviso() {
		return txtAviso;
	}


	public void setTxtAviso(JTextPane txtAviso) {
		this.txtAviso = txtAviso;
	}


	public JLabel getLblFecha() {
		return lblFecha;
	}


	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}


	public JLabel getTxtFecha() {
		return txtFecha;
	}


	public void setTxtFecha(JLabel txtFecha) {
		this.txtFecha = txtFecha;
	}


	public JLabel getLblCajero() {
		return lblCajero;
	}


	public void setLblCajero(JLabel lblCajero) {
		this.lblCajero = lblCajero;
	}


	public JLabel getTxtCajero() {
		return txtCajero;
	}


	public void setTxtCajero(JLabel txtCajero) {
		this.txtCajero = txtCajero;
	}


	public JLabel getLblTuno() {
		return lblTuno;
	}


	public void setLblTuno(JLabel lblTuno) {
		this.lblTuno = lblTuno;
	}


	public JComboBox getCmbTurno() {
		return cmbTurno;
	}


	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}


	public JLabel getLblCajaRegistro() {
		return lblCajaRegistro;
	}


	public void setLblCajaRegistro(JLabel lblCajaRegistro) {
		this.lblCajaRegistro = lblCajaRegistro;
	}


	public JComboBox getCmbCajaRegistro() {
		return cmbCajaRegistro;
	}


	public void setCmbCajaRegistro(JComboBox cmbCajaRegistro) {
		this.cmbCajaRegistro = cmbCajaRegistro;
	}


	public JSeparator getSeparator1() {
		return separator1;
	}


	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}


	public JLabel getLblTipo() {
		return lblTipo;
	}


	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}


	public JComboBox getCmbTipo() {
		return cmbTipo;
	}


	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}


	public JLabel getLblMotivo() {
		return lblMotivo;
	}


	public void setLblMotivo(JLabel lblMotivo) {
		this.lblMotivo = lblMotivo;
	}


	public JComboBox getCmbMotivo() {
		return cmbMotivo;
	}


	public void setCmbMotivo(JComboBox cmbMotivo) {
		this.cmbMotivo = cmbMotivo;
	}


	public JPanel getPnBanco() {
		return pnBanco;
	}


	public void setPnBanco(JPanel pnBanco) {
		this.pnBanco = pnBanco;
	}


	public JComboBox getCmbBanco() {
		return cmbBanco;
	}


	public void setCmbBanco(JComboBox cmbBanco) {
		this.cmbBanco = cmbBanco;
	}


	public JLabel getLblCuenta() {
		return lblCuenta;
	}


	public void setLblCuenta(JLabel lblCuenta) {
		this.lblCuenta = lblCuenta;
	}


	public JComboBox getCmbCuenta() {
		return cmbCuenta;
	}


	public void setCmbCuenta(JComboBox cmbCuenta) {
		this.cmbCuenta = cmbCuenta;
	}


	public JLabel getLblBanco() {
		return lblBanco;
	}


	public void setLblBanco(JLabel lblBanco) {
		this.lblBanco = lblBanco;
	}


	public JPanel getPnCajas() {
		return pnCajas;
	}


	public void setPnCajas(JPanel pnCajas) {
		this.pnCajas = pnCajas;
	}


	public JLabel getLblCajaDestino() {
		return lblCajaDestino;
	}


	public void setLblCajaDestino(JLabel lblCajaDestino) {
		this.lblCajaDestino = lblCajaDestino;
	}


	public JComboBox getCmbCajaDestino() {
		return cmbCajaDestino;
	}


	public void setCmbCajaDestino(JComboBox cmbCajaDestino) {
		this.cmbCajaDestino = cmbCajaDestino;
	}


	public JLabel getLblValor() {
		return lblValor;
	}


	public void setLblValor(JLabel lblValor) {
		this.lblValor = lblValor;
	}


	public JTextField getTxtValor() {
		return txtValor;
	}


	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}


	public JLabel getLblObservacion() {
		return lblObservacion;
	}


	public void setLblObservacion(JLabel lblObservacion) {
		this.lblObservacion = lblObservacion;
	}


	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}


	public void setTxtObservacion(JTextArea txtObservacion) {
		this.txtObservacion = txtObservacion;
	}
	
	
}
