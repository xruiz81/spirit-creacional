package com.spirit.cartera.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
/*
 * Created by JFormDesigner on Mon May 07 10:52:25 COT 2012
 */

public class JDApplyWalletTransaction extends JDialog {
	public JDApplyWalletTransaction(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDApplyWalletTransaction(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		JDApplyWalletTransaction = new JPanel();
		JPApplyWalletTransaction = new JPanel();
		jfsPendingAccounts = compFactory.createSeparator("Cuentas pendientes");
		jpFilteredBy = new JPanel();
		lblToFilter = new JLabel();
		btnRemoveFilters = new JButton();
		btnFilterList = new JButton();
		lblTransaction = new JLabel();
		cmbTransaction = new JComboBox();
		lblInitialDate = new JLabel();
		txtInitialDate = new JFormattedTextField();
		lblFinalDate = new JLabel();
		txtFinalDate = new JFormattedTextField();
		lblOverdueDays = new JLabel();
		cmbOverdueDays = new JComboBox();
		lblValueGreaterThan = new JLabel();
		txtValueGreaterThan = new JFormattedTextField();
		lblBalanceGreaterThan = new JLabel();
		txtBalanceGreaterThan = new JFormattedTextField();
		spTblPendingAccounts = new JScrollPane();
		tblPendingAccounts = new JTable();
		jfsApplyingDocuments = compFactory.createSeparator("Documentos a aplicar");
		jpApplyingDocuments = new JPanel();
		spApplyingDocuments = new JScrollPane();
		tblApplyingDocuments = new JTable();
		btnAccept = new JButton();
		btnCancel = new JButton();
		btnUndo = new JButton();
		btnApply = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Cruce de cuentas");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== JDApplyWalletTransaction ========
		{
			JDApplyWalletTransaction.setBorder(Borders.DIALOG_BORDER);
			JDApplyWalletTransaction.setLayout(new BorderLayout());

			//======== JPApplyWalletTransaction ========
			{
				JPApplyWalletTransaction.setLayout(new FormLayout(
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
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("max(default;75dlu)"),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				JPApplyWalletTransaction.add(jfsPendingAccounts, cc.xywh(3, 1, 11, 1));

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
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;75dlu)"),
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));

					//---- lblToFilter ----
					lblToFilter.setText("Filtrar:");
					lblToFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblToFilter.setForeground(UIManager.getColor("TitledBorder.titleColor"));
					jpFilteredBy.add(lblToFilter, cc.xy(3, 3));

					//---- btnRemoveFilters ----
					btnRemoveFilters.setText("Remover filtros");
					jpFilteredBy.add(btnRemoveFilters, cc.xywh(9, 3, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- btnFilterList ----
					btnFilterList.setText("Filtrar listado");
					jpFilteredBy.add(btnFilterList, cc.xy(13, 3));

					//---- lblTransaction ----
					lblTransaction.setText("Transacci\u00f3n:");
					lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblTransaction, cc.xy(3, 5));
					jpFilteredBy.add(cmbTransaction, cc.xy(5, 5));

					//---- lblInitialDate ----
					lblInitialDate.setText("Fecha inicial:");
					lblInitialDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblInitialDate, cc.xy(7, 5));

					//---- txtInitialDate ----
					txtInitialDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpFilteredBy.add(txtInitialDate, cc.xy(9, 5));

					//---- lblFinalDate ----
					lblFinalDate.setText("Fecha final:");
					lblFinalDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblFinalDate, cc.xy(11, 5));

					//---- txtFinalDate ----
					txtFinalDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpFilteredBy.add(txtFinalDate, cc.xy(13, 5));

					//---- lblOverdueDays ----
					lblOverdueDays.setText("D\u00edas vencidos:");
					lblOverdueDays.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblOverdueDays, cc.xy(3, 7));

					//---- cmbOverdueDays ----
					cmbOverdueDays.setModel(new DefaultComboBoxModel(new String[] {
						"-",
						">30",
						">60",
						">90",
						">120"
					}));
					jpFilteredBy.add(cmbOverdueDays, cc.xy(5, 7));

					//---- lblValueGreaterThan ----
					lblValueGreaterThan.setText("Valor mayor a:");
					lblValueGreaterThan.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblValueGreaterThan, cc.xy(7, 7));

					//---- txtValueGreaterThan ----
					txtValueGreaterThan.setHorizontalAlignment(SwingConstants.RIGHT);
					jpFilteredBy.add(txtValueGreaterThan, cc.xy(9, 7));

					//---- lblBalanceGreaterThan ----
					lblBalanceGreaterThan.setText("Saldo mayor a:");
					lblBalanceGreaterThan.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpFilteredBy.add(lblBalanceGreaterThan, cc.xy(11, 7));

					//---- txtBalanceGreaterThan ----
					txtBalanceGreaterThan.setHorizontalAlignment(SwingConstants.RIGHT);
					jpFilteredBy.add(txtBalanceGreaterThan, cc.xy(13, 7));
				}
				JPApplyWalletTransaction.add(jpFilteredBy, cc.xywh(3, 3, 11, 1));

				//======== spTblPendingAccounts ========
				{

					//---- tblPendingAccounts ----
					tblPendingAccounts.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Selec.", "Transacci\u00f3n", "Fecha", "Dias vencidos", "Valor", "Saldo"
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
					spTblPendingAccounts.setViewportView(tblPendingAccounts);
				}
				JPApplyWalletTransaction.add(spTblPendingAccounts, cc.xywh(3, 5, 11, 1));
				JPApplyWalletTransaction.add(jfsApplyingDocuments, cc.xywh(3, 7, 11, 1));

				//======== jpApplyingDocuments ========
				{
					jpApplyingDocuments.setLayout(new FormLayout(
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

					//======== spApplyingDocuments ========
					{

						//---- tblApplyingDocuments ----
						tblApplyingDocuments.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Transacci\u00f3n", "Saldo", "Valor a aplicar", "Fecha de aplicaci\u00f3n"
							}
						) {
							Class[] columnTypes = new Class[] {
								Object.class, Double.class, Double.class, Object.class
							};
							boolean[] columnEditable = new boolean[] {
								false, false, true, true
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
						spApplyingDocuments.setViewportView(tblApplyingDocuments);
					}
					jpApplyingDocuments.add(spApplyingDocuments, cc.xywh(1, 1, 5, 1));
				}
				JPApplyWalletTransaction.add(jpApplyingDocuments, cc.xywh(3, 9, 11, 3));

				//---- btnAccept ----
				btnAccept.setText("Aceptar");
				JPApplyWalletTransaction.add(btnAccept, cc.xy(7, 13));

				//---- btnCancel ----
				btnCancel.setText("Cancelar");
				JPApplyWalletTransaction.add(btnCancel, cc.xy(9, 13));

				//---- btnUndo ----
				btnUndo.setText("Deshacer");
				JPApplyWalletTransaction.add(btnUndo, cc.xy(11, 13));

				//---- btnApply ----
				btnApply.setText("Aplicar");
				JPApplyWalletTransaction.add(btnApply, cc.xy(13, 13));
			}
			JDApplyWalletTransaction.add(JPApplyWalletTransaction, BorderLayout.CENTER);
		}
		contentPane.add(JDApplyWalletTransaction, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel JDApplyWalletTransaction;
	private JPanel JPApplyWalletTransaction;
	private JComponent jfsPendingAccounts;
	private JPanel jpFilteredBy;
	private JLabel lblToFilter;
	private JButton btnRemoveFilters;
	private JButton btnFilterList;
	private JLabel lblTransaction;
	private JComboBox cmbTransaction;
	private JLabel lblInitialDate;
	private JFormattedTextField txtInitialDate;
	private JLabel lblFinalDate;
	private JFormattedTextField txtFinalDate;
	private JLabel lblOverdueDays;
	private JComboBox cmbOverdueDays;
	private JLabel lblValueGreaterThan;
	private JFormattedTextField txtValueGreaterThan;
	private JLabel lblBalanceGreaterThan;
	private JFormattedTextField txtBalanceGreaterThan;
	private JScrollPane spTblPendingAccounts;
	private JTable tblPendingAccounts;
	private JComponent jfsApplyingDocuments;
	private JPanel jpApplyingDocuments;
	private JScrollPane spApplyingDocuments;
	private JTable tblApplyingDocuments;
	private JButton btnAccept;
	private JButton btnCancel;
	private JButton btnUndo;
	private JButton btnApply;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getJPApplyWalletTransaction() {
		return JPApplyWalletTransaction;
	}

	public void setJPApplyWalletTransaction(JPanel jPApplyWalletTransaction) {
		JPApplyWalletTransaction = jPApplyWalletTransaction;
	}

	public JScrollPane getSpTblPendingAccounts() {
		return spTblPendingAccounts;
	}

	public void setSpTblPendingAccounts(JScrollPane spTblPendingAccounts) {
		this.spTblPendingAccounts = spTblPendingAccounts;
	}

	public JTable getTblPendingAccounts() {
		return tblPendingAccounts;
	}

	public void setTblPendingAccounts(JTable tblPendingAccounts) {
		this.tblPendingAccounts = tblPendingAccounts;
	}

	public JScrollPane getSpApplyingDocuments() {
		return spApplyingDocuments;
	}

	public void setSpApplyingDocuments(JScrollPane spApplyingDocuments) {
		this.spApplyingDocuments = spApplyingDocuments;
	}

	public JTable getTblApplyingDocuments() {
		return tblApplyingDocuments;
	}

	public void setTblApplyingDocuments(JTable tblApplyingDocuments) {
		this.tblApplyingDocuments = tblApplyingDocuments;
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

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public JPanel getJpFilteredBy() {
		return jpFilteredBy;
	}

	public void setJpFilteredBy(JPanel jpFilteredBy) {
		this.jpFilteredBy = jpFilteredBy;
	}

	public JLabel getLblToFilter() {
		return lblToFilter;
	}

	public void setLblToFilter(JLabel lblToFilter) {
		this.lblToFilter = lblToFilter;
	}

	public JLabel getLblTransaction() {
		return lblTransaction;
	}

	public void setLblTransaction(JLabel lblTransaction) {
		this.lblTransaction = lblTransaction;
	}

	public JComboBox getCmbTransaction() {
		return cmbTransaction;
	}

	public void setCmbTransaction(JComboBox cmbTransaction) {
		this.cmbTransaction = cmbTransaction;
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

	public JLabel getLblValueGreaterThan() {
		return lblValueGreaterThan;
	}

	public void setLblValueGreaterThan(JLabel lblValueGreaterThan) {
		this.lblValueGreaterThan = lblValueGreaterThan;
	}

	public JFormattedTextField getTxtValueGreaterThan() {
		return txtValueGreaterThan;
	}

	public void setTxtValueGreaterThan(JFormattedTextField txtValueGreaterThan) {
		this.txtValueGreaterThan = txtValueGreaterThan;
	}

	public JLabel getLblBalanceGreaterThan() {
		return lblBalanceGreaterThan;
	}

	public void setLblBalanceGreaterThan(JLabel lblBalanceGreaterThan) {
		this.lblBalanceGreaterThan = lblBalanceGreaterThan;
	}

	public JFormattedTextField getTxtBalanceGreaterThan() {
		return txtBalanceGreaterThan;
	}

	public void setTxtBalanceGreaterThan(JFormattedTextField txtBalanceGreaterThan) {
		this.txtBalanceGreaterThan = txtBalanceGreaterThan;
	}

	public JLabel getLblOverdueDays() {
		return lblOverdueDays;
	}

	public void setLblOverdueDays(JLabel lblOverdueDays) {
		this.lblOverdueDays = lblOverdueDays;
	}

	public JComboBox getCmbOverdueDays() {
		return cmbOverdueDays;
	}

	public void setCmbOverdueDays(JComboBox cmbOverdueDays) {
		this.cmbOverdueDays = cmbOverdueDays;
	}

	public JPanel getJpApplyingDocuments() {
		return jpApplyingDocuments;
	}

	public void setJpApplyingDocuments(JPanel jpApplyingDocuments) {
		this.jpApplyingDocuments = jpApplyingDocuments;
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
}
