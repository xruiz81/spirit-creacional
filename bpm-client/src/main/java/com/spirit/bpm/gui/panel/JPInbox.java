package com.spirit.bpm.gui.panel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPInbox extends MantenimientoModelImpl {
	public JPInbox() {
		initComponents();
		setName("Inbox");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpInbox = new JideTabbedPane();
		scrollPane1 = new JScrollPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		btnAprobar = new JButton();
		btnRechazar = new JButton();
		btnModificar = new JButton();
		btnEliminar = new JButton();
		btnConsultar = new JButton();
		spTblTareas = new JScrollPane();
		tblTareas = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default"));

		//======== jtpInbox ========
		{
			
			//======== scrollPane1 ========
			{
				
				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							new ColumnSpec(Sizes.DLUX3),
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
							new ColumnSpec(Sizes.DLUX3),
							new ColumnSpec(Sizes.dluX(12))
						},
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, 1.0),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//======== panel2 ========
					{
						panel2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								new ColumnSpec(Sizes.DLUX3),
								FormFactory.DEFAULT_COLSPEC,
								new ColumnSpec(Sizes.DLUX3),
								FormFactory.DEFAULT_COLSPEC,
								new ColumnSpec(Sizes.DLUX3),
								FormFactory.DEFAULT_COLSPEC,
								new ColumnSpec(Sizes.DLUX3),
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC
							}));
						
						//---- btnAprobar ----
						btnAprobar.setText("Ap");
						panel2.add(btnAprobar, cc.xy(1, 1));
						
						//---- btnRechazar ----
						btnRechazar.setText("Rc");
						panel2.add(btnRechazar, cc.xy(3, 1));
						
						//---- btnModificar ----
						btnModificar.setText("Mo");
						panel2.add(btnModificar, cc.xy(5, 1));
						
						//---- btnEliminar ----
						btnEliminar.setText("El");
						panel2.add(btnEliminar, cc.xy(7, 1));
						
						//---- btnConsultar ----
						btnConsultar.setText("Co");
						panel2.add(btnConsultar, cc.xy(9, 1));
					}
					panel1.add(panel2, cc.xy(3, 3));
					
					//======== spTblTareas ========
					{
						
						//---- tblTareas ----
						tblTareas.setModel(new DefaultTableModel(
							new Object[][] {
								{"", null, null, null, null},
							},
							new String[] {
								"No.", "Nombre", "Tipo Documento", "Referencia", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblTareas.setPreferredScrollableViewportSize(new Dimension(450, 350));
						tblTareas.setShowVerticalLines(false);
						tblTareas.setShowHorizontalLines(true);
						spTblTareas.setViewportView(tblTareas);
					}
					panel1.add(spTblTareas, cc.xy(3, 5));
				}
				scrollPane1.setViewportView(panel1);
			}
			jtpInbox.addTab("Tareas", scrollPane1);
			
		}
		add(jtpInbox, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpInbox;
	private JScrollPane scrollPane1;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btnAprobar;
	private JButton btnRechazar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnConsultar;
	private JScrollPane spTblTareas;
	private JTable tblTareas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnAprobar() {
		return btnAprobar;
	}

	public void setBtnAprobar(JButton btnAprobar) {
		this.btnAprobar = btnAprobar;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JButton getBtnRechazar() {
		return btnRechazar;
	}

	public void setBtnRechazar(JButton btnRechazar) {
		this.btnRechazar = btnRechazar;
	}

	public JTable getTblTareas() {
		return tblTareas;
	}

	public void setTblTareas(JTable tblTareas) {
		this.tblTareas = tblTareas;
	}
	
}
