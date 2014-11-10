package com.spirit.timeTracker.gui.main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.timeTracker.componentes.PanelInformacion;

public abstract class JPInfoOrdenTrabajo extends PanelInformacion {
	public JPInfoOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpInfoOrdenTrabajo = new JTabbedPane();
		panelOrden = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		btnImprimir = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		lblCamapana = new JLabel();
		txtCampana = new JTextField();
		lblDirector = new JLabel();
		txtDirector = new JTextField();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		lblEjecutivo = new JLabel();
		txtEjecutivo = new JTextField();
		lblFechaLimite = new JLabel();
		txtFechaLimite = new JTextField();
		lblFechaEntrega = new JLabel();
		txtFechaEntrega = new JTextField();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblPropuesta = new JLabel();
		txtPropuesta = new JTextField();
		btnPropuesta = new JButton();
		lblObservacion = new JLabel();
		btnGuardar = new JButton();
		spTxtObservacion = new JScrollPane();
		txtObservacion = new JTextArea();
		panelCampana = new JPanel();
		spTblArchivosCampana = new JScrollPane();
		tblArchivosCampana = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== jtpInfoOrdenTrabajo ========
		{

			//======== panelOrden ========
			{
				panelOrden.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(15), FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(30)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblCodigo ----
				lblCodigo.setText("C\u00f3digo:");
				panelOrden.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCodigo ----
				txtCodigo.setEditable(false);
				panelOrden.add(txtCodigo, cc.xywh(5, 3, 3, 1));

				//---- lblEstado ----
				lblEstado.setText("Estado:");
				panelOrden.add(lblEstado, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtEstado ----
				txtEstado.setEditable(false);
				panelOrden.add(txtEstado, cc.xy(13, 3));
				panelOrden.add(btnImprimir, cc.xywh(15, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelOrden.add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCliente ----
				txtCliente.setEditable(false);
				panelOrden.add(txtCliente, cc.xywh(5, 5, 11, 1));

				//---- lblCamapana ----
				lblCamapana.setText("Campa\u00f1a:");
				panelOrden.add(lblCamapana, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCampana ----
				txtCampana.setEditable(false);
				panelOrden.add(txtCampana, cc.xywh(5, 7, 11, 1));

				//---- lblDirector ----
				lblDirector.setText("Director(a):");
				panelOrden.add(lblDirector, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtDirector ----
				txtDirector.setEditable(false);
				panelOrden.add(txtDirector, cc.xywh(5, 9, 3, 1));

				//---- lblFechaCreacion ----
				lblFechaCreacion.setText("Fecha Creaci\u00f3n:");
				panelOrden.add(lblFechaCreacion, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtFechaCreacion ----
				txtFechaCreacion.setEditable(false);
				panelOrden.add(txtFechaCreacion, cc.xywh(13, 9, 3, 1));

				//---- lblEjecutivo ----
				lblEjecutivo.setText("Ejecutivo(a):");
				panelOrden.add(lblEjecutivo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtEjecutivo ----
				txtEjecutivo.setEditable(false);
				panelOrden.add(txtEjecutivo, cc.xywh(5, 11, 3, 1));

				//---- lblFechaLimite ----
				lblFechaLimite.setText("Fecha L\u00edmite:");
				panelOrden.add(lblFechaLimite, cc.xywh(11, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtFechaLimite ----
				txtFechaLimite.setEditable(false);
				panelOrden.add(txtFechaLimite, cc.xywh(13, 11, 3, 1));

				//---- lblFechaEntrega ----
				lblFechaEntrega.setText("Fecha Entrega:");
				panelOrden.add(lblFechaEntrega, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtFechaEntrega ----
				txtFechaEntrega.setEditable(false);
				panelOrden.add(txtFechaEntrega, cc.xywh(13, 13, 3, 1));

				//---- lblDescripcion ----
				lblDescripcion.setText("Descripci\u00f3n:");
				panelOrden.add(lblDescripcion, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtDescripcion ----
				txtDescripcion.setEditable(false);
				panelOrden.add(txtDescripcion, cc.xywh(5, 15, 11, 1));

				//---- lblPropuesta ----
				lblPropuesta.setText("Propuesta:");
				panelOrden.add(lblPropuesta, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtPropuesta ----
				txtPropuesta.setEditable(false);
				panelOrden.add(txtPropuesta, cc.xywh(5, 17, 9, 1));
				panelOrden.add(btnPropuesta, cc.xywh(15, 17, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

				//---- lblObservacion ----
				lblObservacion.setText("Observaci\u00f3n:");
				panelOrden.add(lblObservacion, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- btnGuardar ----
				btnGuardar.setText("G");
				panelOrden.add(btnGuardar, cc.xy(5, 19));

				//======== spTxtObservacion ========
				{

					//---- txtObservacion ----
					txtObservacion.setEditable(false);
					spTxtObservacion.setViewportView(txtObservacion);
				}
				panelOrden.add(spTxtObservacion, cc.xywh(3, 21, 13, 5));
			}
			jtpInfoOrdenTrabajo.addTab("Orden", panelOrden);


			//======== panelCampana ========
			{
				panelCampana.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(150)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//======== spTblArchivosCampana ========
				{

					//---- tblArchivosCampana ----
					tblArchivosCampana.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null},
						},
						new String[] {
							"Tipo", "URL"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblArchivosCampana.setViewportView(tblArchivosCampana);
				}
				panelCampana.add(spTblArchivosCampana, cc.xywh(3, 3, 5, 5));
			}
			jtpInfoOrdenTrabajo.addTab("Campa\u00f1a", panelCampana);

		}
		add(jtpInfoOrdenTrabajo, cc.xywh(1, 1, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JTabbedPane jtpInfoOrdenTrabajo;
	private JPanel panelOrden;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnImprimir;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblCamapana;
	private JTextField txtCampana;
	private JLabel lblDirector;
	private JTextField txtDirector;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JLabel lblEjecutivo;
	private JTextField txtEjecutivo;
	private JLabel lblFechaLimite;
	private JTextField txtFechaLimite;
	private JLabel lblFechaEntrega;
	private JTextField txtFechaEntrega;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblPropuesta;
	private JTextField txtPropuesta;
	private JButton btnPropuesta;
	private JLabel lblObservacion;
	private JButton btnGuardar;
	private JScrollPane spTxtObservacion;
	private JTextArea txtObservacion;
	private JPanel panelCampana;
	private JScrollPane spTblArchivosCampana;
	private JTable tblArchivosCampana;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	
	public JTextField getTxtCampana() {
		return txtCampana;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
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

	public JTextField getTxtEjecutivo() {
		return txtEjecutivo;
	}

	public JButton getBtnPropuesta() {
		return btnPropuesta;
	}

	public JTextField getTxtPropuesta() {
		return txtPropuesta;
	}

	public JTextField getTxtDirector() {
		return txtDirector;
	}

	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}

	public JTabbedPane getJtpInfoOrdenTrabajo() {
		return jtpInfoOrdenTrabajo;
	}

	public JTable getTblArchivosCampana() {
		return tblArchivosCampana;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}
}
