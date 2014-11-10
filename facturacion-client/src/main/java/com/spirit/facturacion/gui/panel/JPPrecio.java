package com.spirit.facturacion.gui.panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public abstract class JPPrecio extends SpiritModelImpl {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPPrecio = new JPanel();
		lblListaPrecioId = new JLabel();
		cmbListaPrecioId = new JComboBox();
		lblProductoId = new JLabel();
		cmbProductoId = new JComboBox();
		lblPrecio = new JLabel();
		txtPrecio = new JTextField();
		lblCambiarPrecio = new JLabel();
		cmbCambiarPrecio = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		CellConstraints cc = new CellConstraints();

		//======== JPPrecio ========
		{
			JPPrecio.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu)")
				},
				new RowSpec[] {
					new RowSpec("max(default;10dlu)"),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblListaPrecioId ----
			lblListaPrecioId.setText("Lista de Precio:");
			JPPrecio.add(lblListaPrecioId, cc.xy(3, 3));
			JPPrecio.add(cmbListaPrecioId, cc.xy(5, 3));
			
			//---- lblProductoId ----
			lblProductoId.setText("Producto:");
			JPPrecio.add(lblProductoId, cc.xy(3, 5));
			JPPrecio.add(cmbProductoId, cc.xy(5, 5));
			
			//---- lblPrecio ----
			lblPrecio.setText("Precio:");
			JPPrecio.add(lblPrecio, cc.xy(3, 7));
			JPPrecio.add(txtPrecio, cc.xy(5, 7));
			
			//---- lblCambiarPrecio ----
			lblCambiarPrecio.setText("Cambiar Precio:");
			JPPrecio.add(lblCambiarPrecio, cc.xy(7, 7));
			JPPrecio.add(cmbCambiarPrecio, cc.xy(9, 7));
			
			//---- lblEstado ----
			lblEstado.setText("Estado:");
			JPPrecio.add(lblEstado, cc.xy(3, 9));
			JPPrecio.add(cmbEstado, cc.xy(5, 9));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPPrecio;
	private JLabel lblListaPrecioId;
	private JComboBox cmbListaPrecioId;
	private JLabel lblProductoId;
	private JComboBox cmbProductoId;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblCambiarPrecio;
	private JComboBox cmbCambiarPrecio;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
