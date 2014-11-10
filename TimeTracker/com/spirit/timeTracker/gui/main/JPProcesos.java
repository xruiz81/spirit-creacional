package com.spirit.timeTracker.gui.main;
import java.awt.Font;

import javax.swing.JLabel;
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

public abstract class JPProcesos extends JPanel {
	public JPProcesos() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY//GEN-BEGIN:initComponents
		panelVentanaActiva = new JPanel();
		lblTituloVentanaActiva = new JLabel();
		lblVentanaActiva = new JLabel();
		spanelProcesosActivos = new JScrollPane();
		tblProcesosActivos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== panelVentanaActiva ========
		{
			panelVentanaActiva.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0)
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblTituloVentanaActiva ----
			lblTituloVentanaActiva.setText("Ventana Activa: ");
			lblTituloVentanaActiva.setFont(new Font("Tahoma", Font.BOLD, 11));
			panelVentanaActiva.add(lblTituloVentanaActiva, cc.xy(1, 1));
			
			//---- lblVentanaActiva ----
			lblVentanaActiva.setText("text");
			panelVentanaActiva.add(lblVentanaActiva, cc.xy(3, 1));
		}
		add(panelVentanaActiva, cc.xywh(3, 1, 2, 1));

		//======== spanelProcesosActivos ========
		{
			
			//---- tblProcesosActivos ----
			tblProcesosActivos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					" ", "Nombre Ventana", "Tiempo", "Memoria", "Aplicacion"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, String.class, String.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spanelProcesosActivos.setViewportView(tblProcesosActivos);
		}
		add(spanelProcesosActivos, cc.xywh(3, 3, 1, 3));
		// JFormDesigner - End of component initialization//GEN-END:initComponents
	}

    public JPanel getPanelVentanaActiva() {
        return panelVentanaActiva;
    }

    public void setPanelVentanaActiva(JPanel panelVentanaActiva) {
        this.panelVentanaActiva = panelVentanaActiva;
    }

    public JLabel getLblTituloVentanaActiva() {
        return lblTituloVentanaActiva;
    }

    public void setLblTituloVentanaActiva(JLabel lblTituloVentanaActiva) {
        this.lblTituloVentanaActiva = lblTituloVentanaActiva;
    }

    public JLabel getLblVentanaActiva() {
        return lblVentanaActiva;
    }

    public void setLblVentanaActiva(JLabel lblVentanaActiva) {
        this.lblVentanaActiva = lblVentanaActiva;
    }

    public JScrollPane getSpanelProcesosActivos() {
        return spanelProcesosActivos;
    }

    public void setSpanelProcesosActivos(JScrollPane spanelProcesosActivos) {
        this.spanelProcesosActivos = spanelProcesosActivos;
    }

    public JTable getTblProcesosActivos() {
        return tblProcesosActivos;
    }

    public void setTblProcesosActivos(JTable tblProcesosActivos) {
        this.tblProcesosActivos = tblProcesosActivos;
    }

	// JFormDesigner - Variables declaration - DO NOT MODIFY//GEN-BEGIN:variables
	private JPanel panelVentanaActiva;
	private JLabel lblTituloVentanaActiva;
	private JLabel lblVentanaActiva;
	private JScrollPane spanelProcesosActivos;
	private JTable tblProcesosActivos;
	// JFormDesigner - End of variables declaration//GEN-END:variables
}
