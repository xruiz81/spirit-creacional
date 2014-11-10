package com.spirit.timeTracker.gui.main;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public abstract class JPInfoSubtarea extends JPanel {
	public JPInfoSubtarea() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblId = new JLabel();
		txtId = new JTextField();
		btnGuardar = new JButton();
		lblFechaInicio = new JLabel();
		txtFechaInicio = new JTextField();
		lblFechaFin = new JLabel();
		txtFechaFin = new JTextField();
		lblDescipcion = new JLabel();
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
				new ColumnSpec(Sizes.dluX(50)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(30)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(15), 0.0),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(50)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(30)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, 1.0),
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
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblId ----
		lblId.setText("Id");
		add(lblId, cc.xy(3, 3));

		//---- txtId ----
		txtId.setEditable(false);
		add(txtId, cc.xy(5, 3));

		//---- btnGuardar ----
		btnGuardar.setText("G");
		add(btnGuardar, cc.xy(17, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio");
		add(lblFechaInicio, cc.xy(3, 5));

		//---- txtFechaInicio ----
		txtFechaInicio.setEditable(false);
		add(txtFechaInicio, cc.xywh(5, 5, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin");
		add(lblFechaFin, cc.xy(11, 5));

		//---- txtFechaFin ----
		txtFechaFin.setEditable(false);
		add(txtFechaFin, cc.xywh(13, 5, 3, 1));

		//---- lblDescipcion ----
		lblDescipcion.setText("Descripci\u00f3n");
		add(lblDescipcion, cc.xy(3, 7));

		//======== scrollPane1 ========
		{
			
			//---- txtDescripcion ----
			txtDescripcion.setWrapStyleWord(false);
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setRows(5);
			scrollPane1.setViewportView(txtDescripcion);
		}
		add(scrollPane1, cc.xywh(3, 9, 15, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnGuardar;
	private JLabel lblFechaInicio;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtFechaFin;
	private JLabel lblDescipcion;
	private JScrollPane scrollPane1;
	private JTextArea txtDescripcion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public JTextField getTxtFechaFin() {
		return txtFechaFin;
	}

	public JTextField getTxtFechaInicio() {
		return txtFechaInicio;
	}
}
