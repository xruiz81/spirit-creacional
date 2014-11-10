package com.spirit.compras.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;

public abstract class JDAsociarOrden extends JDialogModelImpl {
	public JDAsociarOrden(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAsociarOrden(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		JDAsocciateOrder = new JPanel();
		JPAsocciateOrder = new JPanel();
		jfsOrdersList = compFactory.createSeparator("Listado de \u00f3rdenes emitidas");
		jpFilteredBy = new JPanel();
		lblType = new JLabel();
		cmbType = new JComboBox();
		lblOverdueDays = new JLabel();
		txtOrderNumber = new JTextField();
		lblInitialDate = new JLabel();
		txtInitialDate = new JFormattedTextField();
		lblFinalDate = new JLabel();
		txtFinalDate = new JFormattedTextField();
		btnFilterList = new JButton();
		btnRemoveFilters = new JButton();
		spTblOrders = new JScrollPane();
		tblOrders = new JTable();
		spAddRemoveOrders = new JPanel();
		btnAdd = new JButton();
		btnRemove = new JButton();
		jfsAssociatedOrders = compFactory.createSeparator("Ordenes asociadas");
		jpAssociatedOrders = new JPanel();
		spAsocciatedOrders = new JScrollPane();
		tblAsocciatedOrders = new JTable();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Asociar Ordenes");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== JDAsocciateOrder ========
		{
			JDAsocciateOrder.setBorder(Borders.DIALOG_BORDER);
			JDAsocciateOrder.setLayout(new BorderLayout());

			//======== JPAsocciateOrder ========
			{
				JPAsocciateOrder.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						new RowSpec(Sizes.dluY(75)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("max(default;75dlu)"),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				JPAsocciateOrder.add(jfsOrdersList, cc.xywh(3, 1, 11, 1));

				//======== jpFilteredBy ========
				{
					jpFilteredBy.setBorder(new EtchedBorder());
					jpFilteredBy.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;100dlu)"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;75dlu)"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;75dlu)"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));

					//---- lblType ----
					lblType.setText("Tipo:");
					lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblType, cc.xy(3, 3));

					//---- cmbType ----
					cmbType.setModel(new DefaultComboBoxModel(new String[] {
						"ORDEN DE COMPRA",
						"ORDEN DE MEDIOS"
					}));
					jpFilteredBy.add(cmbType, cc.xy(5, 3));

					//---- lblOverdueDays ----
					lblOverdueDays.setText("#Orden:");
					lblOverdueDays.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblOverdueDays, cc.xy(9, 3));
					jpFilteredBy.add(txtOrderNumber, cc.xy(11, 3));

					//---- lblInitialDate ----
					lblInitialDate.setText("Fecha inicial:");
					lblInitialDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblInitialDate, cc.xy(3, 5));

					//---- txtInitialDate ----
					txtInitialDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpFilteredBy.add(txtInitialDate, cc.xy(5, 5));

					//---- lblFinalDate ----
					lblFinalDate.setText("Fecha final:");
					lblFinalDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblFinalDate, cc.xy(9, 5));

					//---- txtFinalDate ----
					txtFinalDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpFilteredBy.add(txtFinalDate, cc.xy(11, 5));

					//---- btnFilterList ----
					btnFilterList.setText("Filtrar listado");
					jpFilteredBy.add(btnFilterList, cc.xy(15, 5));

					//---- btnRemoveFilters ----
					btnRemoveFilters.setText("Remover filtros");
					jpFilteredBy.add(btnRemoveFilters, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				}
				JPAsocciateOrder.add(jpFilteredBy, cc.xywh(3, 3, 11, 1));

				//======== spTblOrders ========
				{

					//---- tblOrders ----
					tblOrders.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Selec.", "Orden", "Tipo", "Fecha", "Concepto", "Cliente", "Total"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, false, false
						};
						@Override
						public Class<?> getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblOrders.setViewportView(tblOrders);
				}
				JPAsocciateOrder.add(spTblOrders, cc.xywh(3, 5, 11, 1));

				//======== spAddRemoveOrders ========
				{
					spAddRemoveOrders.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAdd ----
					btnAdd.setText("A\u00f1adir");
					spAddRemoveOrders.add(btnAdd, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- btnRemove ----
					btnRemove.setText("Quitar");
					spAddRemoveOrders.add(btnRemove, cc.xywh(3, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				}
				JPAsocciateOrder.add(spAddRemoveOrders, cc.xywh(3, 7, 11, 1));
				JPAsocciateOrder.add(jfsAssociatedOrders, cc.xywh(3, 9, 11, 1));

				//======== jpAssociatedOrders ========
				{
					jpAssociatedOrders.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100))
						},
						new RowSpec[] {
							new RowSpec("max(default;75dlu)"),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));

					//======== spAsocciatedOrders ========
					{

						//---- tblAsocciatedOrders ----
						tblAsocciatedOrders.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Selec.", "Orden", "Tipo", "Fecha", "Concepto", "Cliente", "Total"
							}
						) {
							Class[] columnTypes = new Class[] {
								Boolean.class, Double.class, Object.class, Double.class, Object.class, Object.class, Object.class
							};
							boolean[] columnEditable = new boolean[] {
								true, false, false, false, false, false, false
							};
							@Override
							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spAsocciatedOrders.setViewportView(tblAsocciatedOrders);
					}
					jpAssociatedOrders.add(spAsocciatedOrders, cc.xywh(1, 1, 5, 1));
				}
				JPAsocciateOrder.add(jpAssociatedOrders, cc.xywh(3, 11, 11, 3));

				//---- btnAccept ----
				btnAccept.setText("Aceptar");
				JPAsocciateOrder.add(btnAccept, cc.xy(11, 15));

				//---- btnCancel ----
				btnCancel.setText("Cancelar");
				JPAsocciateOrder.add(btnCancel, cc.xy(13, 15));
			}
			JDAsocciateOrder.add(JPAsocciateOrder, BorderLayout.CENTER);
		}
		contentPane.add(JDAsocciateOrder, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel JDAsocciateOrder;
	private JPanel JPAsocciateOrder;
	private JComponent jfsOrdersList;
	private JPanel jpFilteredBy;
	private JLabel lblType;
	private JComboBox cmbType;
	private JLabel lblOverdueDays;
	private JTextField txtOrderNumber;
	private JLabel lblInitialDate;
	private JFormattedTextField txtInitialDate;
	private JLabel lblFinalDate;
	private JFormattedTextField txtFinalDate;
	private JButton btnFilterList;
	private JButton btnRemoveFilters;
	private JScrollPane spTblOrders;
	private JTable tblOrders;
	private JPanel spAddRemoveOrders;
	private JButton btnAdd;
	private JButton btnRemove;
	private JComponent jfsAssociatedOrders;
	private JPanel jpAssociatedOrders;
	private JScrollPane spAsocciatedOrders;
	private JTable tblAsocciatedOrders;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getJDAsocciateOrder() {
		return JDAsocciateOrder;
	}

	public void setJDAsocciateOrder(JPanel jDAsocciateOrder) {
		JDAsocciateOrder = jDAsocciateOrder;
	}

	public JPanel getJPAsocciateOrder() {
		return JPAsocciateOrder;
	}

	public void setJPAsocciateOrder(JPanel jPAsocciateOrder) {
		JPAsocciateOrder = jPAsocciateOrder;
	}

	public JComponent getJfsOrdersList() {
		return jfsOrdersList;
	}

	public void setJfsOrdersList(JComponent jfsOrdersList) {
		this.jfsOrdersList = jfsOrdersList;
	}

	public JPanel getJpFilteredBy() {
		return jpFilteredBy;
	}

	public void setJpFilteredBy(JPanel jpFilteredBy) {
		this.jpFilteredBy = jpFilteredBy;
	}

	public JLabel getLblType() {
		return lblType;
	}

	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}

	public JComboBox getCmbType() {
		return cmbType;
	}

	public void setCmbType(JComboBox cmbType) {
		this.cmbType = cmbType;
	}

	public JLabel getLblOverdueDays() {
		return lblOverdueDays;
	}

	public void setLblOverdueDays(JLabel lblOverdueDays) {
		this.lblOverdueDays = lblOverdueDays;
	}

	public JTextField getTxtOrderNumber() {
		return txtOrderNumber;
	}

	public void setTxtOrderNumber(JTextField txtOrderNumber) {
		this.txtOrderNumber = txtOrderNumber;
	}

	public JLabel getLblInitialDate() {
		return lblInitialDate;
	}

	public void setLblInitialDate(JLabel lblInitialDate) {
		this.lblInitialDate = lblInitialDate;
	}

	public JFormattedTextField getTxtInitialDate() {
		return txtInitialDate;
	}

	public void setTxtInitialDate(JFormattedTextField txtInitialDate) {
		this.txtInitialDate = txtInitialDate;
	}

	public JLabel getLblFinalDate() {
		return lblFinalDate;
	}

	public void setLblFinalDate(JLabel lblFinalDate) {
		this.lblFinalDate = lblFinalDate;
	}

	public JFormattedTextField getTxtFinalDate() {
		return txtFinalDate;
	}

	public void setTxtFinalDate(JFormattedTextField txtFinalDate) {
		this.txtFinalDate = txtFinalDate;
	}

	public JButton getBtnFilterList() {
		return btnFilterList;
	}

	public void setBtnFilterList(JButton btnFilterList) {
		this.btnFilterList = btnFilterList;
	}

	public JButton getBtnRemoveFilters() {
		return btnRemoveFilters;
	}

	public void setBtnRemoveFilters(JButton btnRemoveFilters) {
		this.btnRemoveFilters = btnRemoveFilters;
	}

	public JScrollPane getSpTblOrders() {
		return spTblOrders;
	}

	public void setSpTblOrders(JScrollPane spTblOrders) {
		this.spTblOrders = spTblOrders;
	}

	public JTable getTblOrders() {
		return tblOrders;
	}

	public void setTblOrders(JTable tblOrders) {
		this.tblOrders = tblOrders;
	}

	public JPanel getSpAddRemoveOrders() {
		return spAddRemoveOrders;
	}

	public void setSpAddRemoveOrders(JPanel spAddRemoveOrders) {
		this.spAddRemoveOrders = spAddRemoveOrders;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public JComponent getJfsAssociatedOrders() {
		return jfsAssociatedOrders;
	}

	public void setJfsAssociatedOrders(JComponent jfsAssociatedOrders) {
		this.jfsAssociatedOrders = jfsAssociatedOrders;
	}

	public JPanel getJpAssociatedOrders() {
		return jpAssociatedOrders;
	}

	public void setJpAssociatedOrders(JPanel jpAssociatedOrders) {
		this.jpAssociatedOrders = jpAssociatedOrders;
	}

	public JScrollPane getSpAsocciatedOrders() {
		return spAsocciatedOrders;
	}

	public void setSpAsocciatedOrders(JScrollPane spAsocciatedOrders) {
		this.spAsocciatedOrders = spAsocciatedOrders;
	}

	public JTable getTblAsocciatedOrders() {
		return tblAsocciatedOrders;
	}

	public void setTblAsocciatedOrders(JTable tblAsocciatedOrders) {
		this.tblAsocciatedOrders = tblAsocciatedOrders;
	}

	public JButton getBtnAccept() {
		return btnAccept;
	}

	public void setBtnAccept(JButton btnAccept) {
		this.btnAccept = btnAccept;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
}
