package com.spirit.timeTracker.gui.main;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.timeTracker.componentes.PanelInformacion;

public abstract class JPInfoTareaOrdenTrabajo extends PanelInformacion {
	public JPInfoTareaOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblAsignado = new JLabel();
		txtAsignado = new JTextField();
		lblTipo = new JLabel();
		lblSubtipo = new JLabel();
		txtSubtipo = new JTextField();
		txtTipo = new JTextField();
		lblFechaEntrega = new JLabel();
		txtFechaEntrega = new JTextField();
		lblFechaLimite = new JLabel();
		txtFechaLimite = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		lblEquipo = new JLabel();
		txtEquipo = new JTextField();
		lblDescripcion = new JLabel();
		scrollPane1 = new JScrollPane();
		txtDescripcion = new JTextArea();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(15)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
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
				new RowSpec(RowSpec.CENTER, Sizes.dluY(43), 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblAsignado ----
		lblAsignado.setText("Asignado a");
		add(lblAsignado, cc.xy(3, 3));

		//---- txtAsignado ----
		txtAsignado.setEditable(false);
		add(txtAsignado, cc.xywh(5, 3, 7, 1));

		//---- lblTipo ----
		lblTipo.setText("Tipo");
		add(lblTipo, cc.xy(3, 5));

		//---- lblSubtipo ----
		lblSubtipo.setText("Subtipo");
		add(lblSubtipo, cc.xy(9, 5));

		//---- txtSubtipo ----
		txtSubtipo.setEditable(false);
		add(txtSubtipo, cc.xy(11, 5));

		//---- txtTipo ----
		txtTipo.setEditable(false);
		add(txtTipo, cc.xy(5, 5));

		//---- lblFechaEntrega ----
		lblFechaEntrega.setText("Fecha Entrega");
		add(lblFechaEntrega, cc.xy(3, 7));

		//---- txtFechaEntrega ----
		txtFechaEntrega.setEditable(false);
		add(txtFechaEntrega, cc.xy(5, 7));

		//---- lblFechaLimite ----
		lblFechaLimite.setText("Fecha L\u00edmite");
		add(lblFechaLimite, cc.xy(9, 7));

		//---- txtFechaLimite ----
		txtFechaLimite.setEditable(false);
		add(txtFechaLimite, cc.xy(11, 7));

		//---- lblEstado ----
		lblEstado.setText("Estado");
		add(lblEstado, cc.xy(3, 9));

		//---- txtEstado ----
		txtEstado.setEditable(false);
		add(txtEstado, cc.xy(5, 9));

		//---- lblEquipo ----
		lblEquipo.setText("Equipo");
		add(lblEquipo, cc.xy(9, 9));

		//---- txtEquipo ----
		txtEquipo.setEditable(false);
		add(txtEquipo, cc.xy(11, 9));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n");
		add(lblDescripcion, cc.xy(3, 11));

		//======== scrollPane1 ========
		{
			
			//---- txtDescripcion ----
			txtDescripcion.setEditable(false);
			txtDescripcion.setFont(new Font("Courier", Font.PLAIN, 13));
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setRows(4);
			scrollPane1.setViewportView(txtDescripcion);
		}
		add(scrollPane1, cc.xywh(3, 13, 9, 4));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblAsignado;
	private JTextField txtAsignado;
	private JLabel lblTipo;
	private JLabel lblSubtipo;
	private JTextField txtSubtipo;
	private JTextField txtTipo;
	private JLabel lblFechaEntrega;
	private JTextField txtFechaEntrega;
	private JLabel lblFechaLimite;
	private JTextField txtFechaLimite;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblEquipo;
	private JTextField txtEquipo;
	private JLabel lblDescripcion;
	private JScrollPane scrollPane1;
	private JTextArea txtDescripcion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtAsignado() {
		return txtAsignado;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtEquipo() {
		return txtEquipo;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public JTextField getTxtFechaEntrega() {
		return txtFechaEntrega;
	}

	public JTextField getTxtFechaLimite() {
		return txtFechaLimite;
	}

	public JTextField getTxtSubtipo() {
		return txtSubtipo;
	}

	public JTextField getTxtTipo() {
		return txtTipo;
	}
	
	
}
