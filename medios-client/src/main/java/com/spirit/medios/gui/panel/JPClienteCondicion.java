package com.spirit.medios.gui.panel;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

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
import com.spirit.util.FechaComboBox;


public abstract class JPClienteCondicion extends SpiritModelImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541063786231473242L;
	public JPClienteCondicion() {
		initComponents();
		setName("Condicion del Cliente");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		MaskFormatter formatter=null;
		 try {
	         formatter = new MaskFormatter("UUUU-UUA-######-####");
	      }
	      catch(Exception e) { };
	 
	    formatter.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		JPClienteCondicion = new JPanel();
		lblClienteId = new JLabel();
		cmbClienteId = new JComboBox();
		label1 = new JLabel();
		fsCondiciones = compFactory.createSeparator("Condiciones:");
		cmbSubtipoOrdenId = new JComboBox();
		lblTipoCondicionId = new JLabel();
		cmbTipoCondicionId = new JComboBox();
		lblFormaPago = new JLabel();
		txtFormaPago = new JTextField();
		lblDescuento = new JLabel();
		txtDescuento = new JTextField();
		lblObservaciones = new JLabel();
		txtObservaciones = new JTextField();
		lblFechaIni = new JLabel();
		cmbFechaIni = new FechaComboBox().get_dateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new FechaComboBox().get_dateComboBox();
		CellConstraints cc = new CellConstraints();

		//======== JPClienteCondicion ========
		{
			JPClienteCondicion.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu):grow")
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
			((FormLayout)JPClienteCondicion.getLayout()).setColumnGroups(new int[][] {{5, 7}});
			
			//---- lblClienteId ----
			lblClienteId.setText("Cliente:");
			JPClienteCondicion.add(lblClienteId, cc.xy(3, 3));
			JPClienteCondicion.add(cmbClienteId, cc.xywh(5, 3, 3, 1));
			
			//---- label1 ----
			label1.setText("Tipo de Orden:");
			JPClienteCondicion.add(label1, cc.xy(3, 5));
			JPClienteCondicion.add(cmbSubtipoOrdenId, cc.xy(5, 5));
			
			//---- lblTipoCondicionId ----
			lblTipoCondicionId.setText("Tipo Condici\u00f3n:");
			JPClienteCondicion.add(lblTipoCondicionId, cc.xy(3, 7));
			JPClienteCondicion.add(cmbTipoCondicionId, cc.xy(5, 7));
			
			//---- lblFormaPago ----
			lblFormaPago.setText("Forma de Pago:");
			JPClienteCondicion.add(lblFormaPago, cc.xy(3, 9));
			JPClienteCondicion.add(txtFormaPago, cc.xy(5, 9));
			
			//---- lblDescuento ----
			lblDescuento.setText("Descuento:");
			JPClienteCondicion.add(lblDescuento, cc.xy(3, 11));
			JPClienteCondicion.add(txtDescuento, cc.xy(5, 11));
			
			//---- lblObservaciones ----
			lblObservaciones.setText("Observaciones:");
			JPClienteCondicion.add(lblObservaciones, cc.xy(3, 13));
			JPClienteCondicion.add(txtObservaciones, cc.xywh(5, 13, 4, 1));
			
			//---- lblFechaIni ----
			lblFechaIni.setText("Fecha Inicio:");
			JPClienteCondicion.add(lblFechaIni, cc.xy(3, 15));
			cmbFechaIni.setShowNoneButton(false);
			JPClienteCondicion.add(cmbFechaIni, cc.xy(5, 15));
			
			//---- lblFechaFin ----
			lblFechaFin.setText("Fecha Final:");
			JPClienteCondicion.add(lblFechaFin, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			cmbFechaFin.setShowNoneButton(false);
			JPClienteCondicion.add(cmbFechaFin, cc.xy(5, 17));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPClienteCondicion;
	private JLabel lblClienteId;
	private JComboBox cmbClienteId;
	private JLabel label1;
	private JComboBox cmbSubtipoOrdenId;
	private JLabel lblTipoCondicionId;
	private JComboBox cmbTipoCondicionId;
	private JLabel lblFormaPago;
	private JTextField txtFormaPago;
	private JLabel lblDescuento;
	private JTextField txtDescuento;
	private JLabel lblObservaciones;
	private JTextField txtObservaciones;
	private JLabel lblFechaIni;
	private DateComboBox cmbFechaIni;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	protected JComponent fsCondiciones;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbClienteId() {
		return cmbClienteId;
	}

	public void setCmbClienteId(JComboBox cmbClienteId) {
		this.cmbClienteId = cmbClienteId;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaIni() {
		return cmbFechaIni;
	}

	public void setCmbFechaIni(DateComboBox cmbFechaIni) {
		this.cmbFechaIni = cmbFechaIni;
	}

	public JComboBox getCmbSubtipoOrdenId() {
		return cmbSubtipoOrdenId;
	}

	public void setCmbSubtipoOrdenId(JComboBox cmbSubtipoOrdenId) {
		this.cmbSubtipoOrdenId = cmbSubtipoOrdenId;
	}

	public JComboBox getCmbTipoCondicionId() {
		return cmbTipoCondicionId;
	}

	public void setCmbTipoCondicionId(JComboBox cmbTipoCondicionId) {
		this.cmbTipoCondicionId = cmbTipoCondicionId;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JTextField txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JTextField getTxtFormaPago() {
		return txtFormaPago;
	}

	public void setTxtFormaPago(JTextField txtFormaPago) {
		this.txtFormaPago = txtFormaPago;
	}

	public JTextField getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(JTextField txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
}
