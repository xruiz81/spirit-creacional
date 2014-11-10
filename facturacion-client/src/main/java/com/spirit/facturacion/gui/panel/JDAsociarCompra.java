package com.spirit.facturacion.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
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

public abstract class JDAsociarCompra extends JDialogModelImpl {
	public JDAsociarCompra(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAsociarCompra(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		JDAsocciatePurchase = new JPanel();
		JPAsocciatePurchase = new JPanel();
		jfsPurchasesList = compFactory.createSeparator("Listado de compras");
		jpFilteredBy = new JPanel();
		lblInvoiceNumber = new JLabel();
		txtInvoiceNumber = new JTextField();
		lblInitialDate = new JLabel();
		txtInitialDate = new JFormattedTextField();
		lblFinalDate = new JLabel();
		txtFinalDate = new JFormattedTextField();
		btnFilterList = new JButton();
		btnRemoveFilters = new JButton();
		spTblPurchase = new JScrollPane();
		tblPurchases = new JTable();
		spAddRemovePurchases = new JPanel();
		btnAdd = new JButton();
		btnRemove = new JButton();
		jfsAssociatedPurchases = compFactory.createSeparator("Compras asociadas");
		jpAssociatedPurchases = new JPanel();
		spAsocciatedPurchases = new JScrollPane();
		tblAsocciatedPurchases = new JTable();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Asociar Ordenes");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== JDAsocciatePurchase ========
		{
			JDAsocciatePurchase.setBorder(Borders.DIALOG_BORDER);
			JDAsocciatePurchase.setLayout(new BorderLayout());

			//======== JPAsocciatePurchase ========
			{
				JPAsocciatePurchase.setLayout(new FormLayout(
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
				JPAsocciatePurchase.add(jfsPurchasesList, cc.xywh(3, 1, 11, 1));

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

					//---- lblInvoiceNumber ----
					lblInvoiceNumber.setText("#Factura:");
					lblInvoiceNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblInvoiceNumber, cc.xy(3, 3));
					jpFilteredBy.add(txtInvoiceNumber, cc.xy(5, 3));

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
				JPAsocciatePurchase.add(jpFilteredBy, cc.xywh(3, 3, 11, 1));

				//======== spTblPurchase ========
				{

					//---- tblPurchases ----
					tblPurchases.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Selec.", "Factura", "Fecha", "Concepto", "Cliente", "Total"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, false
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
					spTblPurchase.setViewportView(tblPurchases);
				}
				JPAsocciatePurchase.add(spTblPurchase, cc.xywh(3, 5, 11, 1));

				//======== spAddRemovePurchases ========
				{
					spAddRemovePurchases.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAdd ----
					btnAdd.setText("A\u00f1adir");
					spAddRemovePurchases.add(btnAdd, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- btnRemove ----
					btnRemove.setText("Quitar");
					spAddRemovePurchases.add(btnRemove, cc.xywh(3, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				}
				JPAsocciatePurchase.add(spAddRemovePurchases, cc.xywh(3, 7, 11, 1));
				JPAsocciatePurchase.add(jfsAssociatedPurchases, cc.xywh(3, 9, 11, 1));

				//======== jpAssociatedPurchases ========
				{
					jpAssociatedPurchases.setLayout(new FormLayout(
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

					//======== spAsocciatedPurchases ========
					{

						//---- tblAsocciatedPurchases ----
						tblAsocciatedPurchases.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Selec.", "Factura", "Fecha", "Concepto", "Cliente", "Total"
							}
						) {
							Class[] columnTypes = new Class[] {
								Boolean.class, Double.class, Double.class, Object.class, Object.class, Object.class
							};
							boolean[] columnEditable = new boolean[] {
								true, false, false, false, false, false
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
						spAsocciatedPurchases.setViewportView(tblAsocciatedPurchases);
					}
					jpAssociatedPurchases.add(spAsocciatedPurchases, cc.xywh(1, 1, 5, 1));
				}
				JPAsocciatePurchase.add(jpAssociatedPurchases, cc.xywh(3, 11, 11, 3));

				//---- btnAccept ----
				btnAccept.setText("Aceptar");
				JPAsocciatePurchase.add(btnAccept, cc.xy(11, 15));

				//---- btnCancel ----
				btnCancel.setText("Cancelar");
				JPAsocciatePurchase.add(btnCancel, cc.xy(13, 15));
			}
			JDAsocciatePurchase.add(JPAsocciatePurchase, BorderLayout.CENTER);
		}
		contentPane.add(JDAsocciatePurchase, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel JDAsocciatePurchase;
	private JPanel JPAsocciatePurchase;
	private JComponent jfsPurchasesList;
	private JPanel jpFilteredBy;
	private JLabel lblInvoiceNumber;
	private JTextField txtInvoiceNumber;
	private JLabel lblInitialDate;
	private JFormattedTextField txtInitialDate;
	private JLabel lblFinalDate;
	private JFormattedTextField txtFinalDate;
	private JButton btnFilterList;
	private JButton btnRemoveFilters;
	private JScrollPane spTblPurchase;
	private JTable tblPurchases;
	private JPanel spAddRemovePurchases;
	private JButton btnAdd;
	private JButton btnRemove;
	private JComponent jfsAssociatedPurchases;
	private JPanel jpAssociatedPurchases;
	private JScrollPane spAsocciatedPurchases;
	private JTable tblAsocciatedPurchases;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getJpFilteredBy() {
		return jpFilteredBy;
	}

	public void setJpFilteredBy(JPanel jpFilteredBy) {
		this.jpFilteredBy = jpFilteredBy;
	}

	public JTextField getTxtInvoiceNumber() {
		return txtInvoiceNumber;
	}

	public void setTxtInvoiceNumber(JTextField txtInvoiceNumber) {
		this.txtInvoiceNumber = txtInvoiceNumber;
	}

	public JFormattedTextField getTxtInitialDate() {
		return txtInitialDate;
	}

	public void setTxtInitialDate(JFormattedTextField txtInitialDate) {
		this.txtInitialDate = txtInitialDate;
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

	public JTable getTblPurchases() {
		return tblPurchases;
	}

	public void setTblPurchases(JTable tblPurchases) {
		this.tblPurchases = tblPurchases;
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

	public JTable getTblAsocciatedPurchases() {
		return tblAsocciatedPurchases;
	}

	public void setTblAsocciatedPurchases(JTable tblAsocciatedPurchases) {
		this.tblAsocciatedPurchases = tblAsocciatedPurchases;
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
