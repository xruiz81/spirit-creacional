package com.spirit.general.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPUsuarioDocumento extends MantenimientoModelImpl {
	public JPUsuarioDocumento() {
		initComponents();
		setName("Documentos por Usuario");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		fsUsuarios = compFactory.createSeparator("Usuarios");
		spTblUsuarios = new JScrollPane();
		tblUsuarios = new JTable();
		fsDocumentos = compFactory.createSeparator("Documentos");
		lblModulo = new JLabel();
		cmbModulo = new JComboBox();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		lblPermisoGuardar = new JLabel();
		cmbPermisoGuardar = new JComboBox();
		lblPermisoBorrar = new JLabel();
		cmbPermisoBorrar = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cmbPermisoImprimir = new JComboBox();
		lblPermisoImprimir = new JLabel();
		lblPermisoAutorizar = new JLabel();
		cmbPermisoAutorizar = new JComboBox();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblUsuarioDocumento = new JScrollPane();
		tblUsuarioDocumento = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(40)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(40)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(50)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY7),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(50)),
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
				new RowSpec(Sizes.dluY(5)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY7)
			}));
		add(fsUsuarios, cc.xywh(3, 3, 17, 1));

		//======== spTblUsuarios ========
		{
			
			//---- tblUsuarios ----
			tblUsuarios.setModel(new DefaultTableModel(
				new Object[][] {
					{Boolean.TRUE, null},
				},
				new String[] {
					"Seleccionar", "Usuario"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblUsuarios.setViewportView(tblUsuarios);
		}
		add(spTblUsuarios, cc.xywh(3, 5, 15, 5));
		add(fsDocumentos, cc.xywh(3, 11, 17, 1));

		//---- lblModulo ----
		lblModulo.setText("M\u00f3dulo:");
		lblModulo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblModulo, cc.xy(3, 13));
		add(cmbModulo, cc.xywh(5, 13, 7, 1));

		//---- lblDocumento ----
		lblDocumento.setText("Documento:");
		add(lblDocumento, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDocumento, cc.xywh(5, 15, 13, 1));

		//---- lblPermisoGuardar ----
		lblPermisoGuardar.setText("Permiso guardar:");
		add(lblPermisoGuardar, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbPermisoGuardar ----
		cmbPermisoGuardar.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbPermisoGuardar, cc.xy(5, 17));

		//---- lblPermisoBorrar ----
		lblPermisoBorrar.setText("Permiso borrar:");
		add(lblPermisoBorrar, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbPermisoBorrar ----
		cmbPermisoBorrar.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbPermisoBorrar, cc.xy(11, 17));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(15, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(17, 17));

		//---- cmbPermisoImprimir ----
		cmbPermisoImprimir.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbPermisoImprimir, cc.xy(5, 19));

		//---- lblPermisoImprimir ----
		lblPermisoImprimir.setText("Permiso imprimir:");
		add(lblPermisoImprimir, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPermisoAutorizar ----
		lblPermisoAutorizar.setText("Permiso autorizar:");
		add(lblPermisoAutorizar, cc.xywh(9, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbPermisoAutorizar ----
		cmbPermisoAutorizar.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbPermisoAutorizar, cc.xy(11, 19));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- btnAgregarDetalle ----
			btnAgregarDetalle.setText("A");
			btnAgregarDetalle.setIcon(null);
			panel1.add(btnAgregarDetalle, cc.xy(1, 1));
			
			//---- btnActualizarDetalle ----
			btnActualizarDetalle.setText("U");
			btnActualizarDetalle.setIcon(null);
			panel1.add(btnActualizarDetalle, cc.xy(3, 1));
			
			//---- btnEliminarDetalle ----
			btnEliminarDetalle.setText("E");
			panel1.add(btnEliminarDetalle, cc.xy(5, 1));
		}
		add(panel1, cc.xywh(3, 23, 17, 1));

		//======== spTblUsuarioDocumento ========
		{
			
			//---- tblUsuarioDocumento ----
			tblUsuarioDocumento.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Usuario", "M\u00f3dulo", "Documento"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblUsuarioDocumento.setViewportView(tblUsuarioDocumento);
		}
		add(spTblUsuarioDocumento, cc.xywh(3, 25, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComponent fsUsuarios;
	private JScrollPane spTblUsuarios;
	private JTable tblUsuarios;
	private JComponent fsDocumentos;
	private JLabel lblModulo;
	private JComboBox cmbModulo;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JLabel lblPermisoGuardar;
	private JComboBox cmbPermisoGuardar;
	private JLabel lblPermisoBorrar;
	private JComboBox cmbPermisoBorrar;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JComboBox cmbPermisoImprimir;
	private JLabel lblPermisoImprimir;
	private JLabel lblPermisoAutorizar;
	private JComboBox cmbPermisoAutorizar;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblUsuarioDocumento;
	private JTable tblUsuarioDocumento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbPermisoAutorizar() {
		return cmbPermisoAutorizar;
	}

	public void setCmbPermisoAutorizar(JComboBox cmbPermisoAutorizar) {
		this.cmbPermisoAutorizar = cmbPermisoAutorizar;
	}

	public JComboBox getCmbPermisoBorrar() {
		return cmbPermisoBorrar;
	}

	public void setCmbPermisoBorrar(JComboBox cmbPermisoBorrar) {
		this.cmbPermisoBorrar = cmbPermisoBorrar;
	}

	public JComboBox getCmbPermisoGuardar() {
		return cmbPermisoGuardar;
	}

	public void setCmbPermisoGuardar(JComboBox cmbPermisoGuardar) {
		this.cmbPermisoGuardar = cmbPermisoGuardar;
	}

	public JComboBox getCmbPermisoImprimir() {
		return cmbPermisoImprimir;
	}

	public void setCmbPermisoImprimir(JComboBox cmbPermisoImprimir) {
		this.cmbPermisoImprimir = cmbPermisoImprimir;
	}

	public JTable getTblUsuarioDocumento() {
		return tblUsuarioDocumento;
	}

	public void setTblUsuarioDocumento(JTable tblUsuarioDocumento) {
		this.tblUsuarioDocumento = tblUsuarioDocumento;
	}

	public JComboBox getCmbModulo() {
		return cmbModulo;
	}

	public void setCmbModulo(JComboBox cmbModulo) {
		this.cmbModulo = cmbModulo;
	}

	public JTable getTblUsuarios() {
		return tblUsuarios;
	}

	public void setTblUsuarios(JTable tblUsuarios) {
		this.tblUsuarios = tblUsuarios;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}
	
}
