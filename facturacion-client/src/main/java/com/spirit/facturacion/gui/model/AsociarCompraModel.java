package com.spirit.facturacion.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.gui.controller.PurchaseConstants;
import com.spirit.compras.handler.PurchaseData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf;
import com.spirit.facturacion.gui.panel.JDAsociarCompra;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class AsociarCompraModel extends JDAsociarCompra {
	private ClienteOficinaIf provider;
	private Vector<PurchaseData> selectedPurchasesDataVector = new Vector<PurchaseData>();
	private Vector<PurchaseData> associatedPurchasesDataVector = new Vector<PurchaseData>();
	private java.util.Date initialDateFilter;
	private java.util.Date finalDateFilter;
	private Vector<PurchaseData> purchasesDataVector = new Vector<PurchaseData>();
	private Vector<PurchaseData> purchasesDataVectorFiltered = new Vector<PurchaseData>();
	private static final int TBL_PURCHASES_SELECTION_COLUMN_INDEX = 0;
	private static final int TBL_PURCHASES_CODE_COLUMN_INDEX = 1;
	private static final int TBL_PURCHASES_DATE_COLUMN_INDEX = 2;
	private static final int TBL_PURCHASES_CONCEPT_COLUMN_INDEX = 4;
	private static final int TBL_PURCHASES_CUSTOMER_COLUMN_INDEX = 4;
	private static final int TBL_PURCHASES_TOTAL_COLUMN_INDEX = 5;
	private boolean cancelAction = false;

	public AsociarCompraModel(Frame owner, Vector<PurchaseData> selectedPurchasesDataVector, ClienteOficinaIf provider) {
		super(owner);
		setProvider(provider);
		setSelectedPurchasesDataVector((Vector<PurchaseData>) DeepCopy.copy(selectedPurchasesDataVector));
		setAssociatedPurchasesDataVector(selectedPurchasesDataVector);
		initDialogComponents();
		initListeners();
		initJDialogAdapter(this);
		initDialog();
	}
	
	private void initDialogComponents() {
		initSpTblPurchases();
		initTblPurchases();
		initTblAssociatedPurchases();
		initButtons();
	}
	
	private void initButtons() {
		getBtnAdd().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/downArrow.png"));
		getBtnAdd().setText(SpiritConstants.getEmptyCharacter());
		getBtnRemove().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/upArrow.png"));
		getBtnRemove().setText(SpiritConstants.getEmptyCharacter());
	}
	
	private void initDialog() {
		this.setSize(PurchaseConstants.getJdialogAsociarOrdenWidth(), PurchaseConstants.getJdialogAsociarOrdenHeight());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - PurchaseConstants.getJdialogAsociarOrdenWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - PurchaseConstants.getJdialogAsociarOrdenHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void initJDialogAdapter(JDialog jDialog) {
		jDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void initListeners() {
		getBtnRemoveFilters().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Restoring filters
				initSpTblPurchases();
				// Setting initial date filter to the stored initial value
				getTxtInitialDate().setValue(getInitialDateFilter() != null?getInitialDateFilter():new java.util.Date());
				// Setting final date filter to the stored final value
				getTxtFinalDate().setValue(getFinalDateFilter() != null?getFinalDateFilter():new java.util.Date());
				// Restoring pending accounts list
				filteringPurchasesDataVector();
				//loadTblPurchasesData();
			}
		});

		getBtnFilterList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteringPurchasesDataVector();
			}
		});
		
		getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPurchases();
				//filteringPurchasesDataVector();
			}
		});
		
		getBtnRemove().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePurchases();
				//filteringPurchasesDataVector();
			}
		});
		
		getBtnAccept().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		
		getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
	}
	
	private void accept() {
		setCancelAction(false);
		setAssociatedPurchasesDataVector((Vector<PurchaseData>) DeepCopy.copy(getSelectedPurchasesDataVector()));
		setVisible(false);
		dispose();
	}
	
	private void cancel() {
		setCancelAction(true);
		getSelectedPurchasesDataVector().clear();
		setSelectedPurchasesDataVector(new Vector<PurchaseData>());
		setVisible(false);
		dispose();
	}
	
	private void initSpTblPurchases() {
		initTextFields();
		initSpTblPurchasesButtons();
	}
	
	private void initTblPurchases() {
		setTblPurchasesWidthColumns(getTblPurchases());
		setTblPurchasesAlignment(getTblPurchases());
		loadTblPurchasesData();
		getTblPurchases().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblPurchases().getTableHeader().setReorderingAllowed(false);
	}
	
	private void initTblAssociatedPurchases() {
		setTblPurchasesWidthColumns(getTblAsocciatedPurchases());
		setTblPurchasesAlignment(getTblAsocciatedPurchases());
		loadTblAsocciatedPurchasesData();
		getTblAsocciatedPurchases().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblAsocciatedPurchases().getTableHeader().setReorderingAllowed(false);
	}
	
	private void setTblPurchasesWidthColumns(JTable jTable) {
		TableColumn columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesSelectionColumnIndex());
		columnWidth.setPreferredWidth(20);
		columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesCodeColumnIndex());
		columnWidth.setPreferredWidth(60);
		columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesDateColumnIndex());
		columnWidth.setPreferredWidth(50);
		columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesConceptColumnIndex());
		columnWidth.setPreferredWidth(200);
		columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesCustomerColumnIndex());
		columnWidth.setPreferredWidth(180);
		columnWidth = jTable.getColumnModel().getColumn(getTblPurchasesTotalColumnIndex());
		columnWidth.setPreferredWidth(40);
	}
	
	private void setTblPurchasesAlignment(JTable jTable) {
		TableCellRendererHorizontalCenterAlignment horizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		jTable.getColumnModel().getColumn(getTblPurchasesCodeColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		jTable.getColumnModel().getColumn(getTblPurchasesDateColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		jTable.getColumnModel().getColumn(getTblPurchasesTotalColumnIndex()).setCellRenderer(horizontalRightAlignment);
	}
	
	private void setSorterTable(JTable table) {
		RowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tableRowSorter);
	}
	
	private void loadTblPurchasesData() {
		try {
			Iterator<Object[]> it = SessionServiceLocator.getCompraSessionService().findPurchases(getProvider().getId()).iterator();
			while (it.hasNext()) {
				Object[] purchase = it.next();
				PurchaseData purchaseData = addPurchaseData(purchase);
				if (purchaseData.getPurchase() != SpiritConstants.getNullValue()) {
					addRowToTbl(getTblPurchases(), purchaseData);
					java.util.Date filterInitialDate = (java.util.Date) getTxtInitialDate().getValue();
					if (Utilitarios.compararFechas(purchaseData.getPurchase().getFecha(), Utilitarios.fromUtilDateToSqlDate(filterInitialDate)) < 0) {
						getTxtInitialDate().setValue(purchaseData.getPurchase().getFecha());
						// Storing initial date value (Necessary for remove filters operation)
						setInitialDateFilter(purchaseData.getPurchase().getFecha());
					}
					java.util.Date filterFinalDate = (java.util.Date) getTxtFinalDate().getValue();
					if (Utilitarios.compararFechas(purchaseData.getPurchase().getFecha(), Utilitarios.fromUtilDateToSqlDate(filterFinalDate)) > 0) {
						getTxtFinalDate().setValue(purchaseData.getPurchase().getFecha());
						// Storing final date value (Necessary for remove filters operation)
						setFinalDateFilter(purchaseData.getPurchase().getFecha());
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de compras", SpiritAlert.ERROR);
		}
	}
	
	private ArrayList loadTblAsocciatedPurchasesData() {
		ArrayList dataList = new ArrayList();
		for (int i=0; i<getSelectedPurchasesDataVector().size(); i++) {
			PurchaseData purchaseData = getSelectedPurchasesDataVector().get(i);
			addRowToTbl(getTblAsocciatedPurchases(), purchaseData);
		}
		return dataList;
	}
	
	private PurchaseData addPurchaseData(Object[] purchase) throws GenericBusinessException {
		PurchaseData purchaseData = new PurchaseData();
		if (!isAssociated((CompraIf) purchase[0]))
			purchaseData.setPurchase((CompraIf) purchase[0]);
		if (purchaseData.getPurchase() != SpiritConstants.getNullValue()) {
			if (!isAssociated(purchaseData)) {
				purchaseData.setProvider((ClienteIf) purchase[1]);
				getPurchasesDataVector().add(purchaseData);
				getPurchasesDataVectorFiltered().add(purchaseData);
			} else {
				purchaseData.setPurchase(SpiritConstants.getNullValue());
			}
		}
		return purchaseData;
	}
	
	private boolean isAssociated(CompraIf purchase) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("compraId", purchase.getId());
		try {
			Iterator<FacturaDetalleCompraAsociadaIf> compraAsociadaIt = SessionServiceLocator.getFacturaDetalleCompraAsociadaSessionService().findFacturaDetalleCompraAsociadaByQuery(queryMap).iterator();
			//if ((compraAsociadaIt.hasNext() && isSelected(purchase)) || !compraAsociadaIt.hasNext())
			if (compraAsociadaIt.hasNext() && isSelected(purchase))
				return true;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return false;
	}
	
	private boolean isSelected(CompraIf purchase) {
		for (int i=0; i<getSelectedPurchasesDataVector().size(); i++) {
			PurchaseData selectedPurchaseData = getSelectedPurchasesDataVector().get(i);
			if (selectedPurchaseData.getPurchase().getId().compareTo(purchase.getId()) == 0)
				return true;
		}
		return false;
	}
	
	private boolean isAssociated(PurchaseData purchaseData) {
		for (int i=0; i<getSelectedPurchasesDataVector().size(); i++) {
			PurchaseData selectedPurchaseData = getSelectedPurchasesDataVector().get(i);
				if (selectedPurchaseData.getPurchase().getId().compareTo(purchaseData.getPurchase().getId()) == 0)
					return true;
		}
		return false;
	}
	
	private PurchaseData addSelectedPurchaseData(PurchaseData purchase) {
		PurchaseData purchaseData = new PurchaseData();
		purchaseData.setPurchase(purchase);
		if (purchase != SpiritConstants.getNullValue()) {
			purchaseData.setProvider(purchase.getProvider());
			getSelectedPurchasesDataVector().add(purchaseData);
		}
		return purchaseData;
	}
	
	private void filteringPurchasesDataVector() {
		cleanPurchasesDataVectorFiltered();
		cleanTable(getTblPurchases());
		for (int i=0; i<getPurchasesDataVector().size(); i++) {
			PurchaseData purchaseData = getPurchasesDataVector().get(i);
			if (isFiltered(purchaseData) && !isAssociated(purchaseData)) {
				getPurchasesDataVectorFiltered().add(purchaseData);
				addRowToTbl(getTblPurchases(), purchaseData);
			}
		}
	}
	
	private void addPurchases() {
		for (int i=0; i<getPurchasesDataVectorFiltered().size(); i++) {
			if ((Boolean) getTblPurchases().getValueAt(i, getTblPurchasesSelectionColumnIndex())) {
				PurchaseData purchaseData = (PurchaseData) DeepCopy.copy(getPurchasesDataVectorFiltered().get(i));
				getSelectedPurchasesDataVector().add(purchaseData);
				addRowToTbl(getTblAsocciatedPurchases(), purchaseData);
				removePurchaseData(getPurchasesDataVector(), purchaseData);
				getPurchasesDataVectorFiltered().remove(i);
				((DefaultTableModel) getTblPurchases().getModel()).removeRow(i);
				i--;
			}
		}
	}
	
	private void removePurchaseData(Vector<PurchaseData> purchaseDataVector, PurchaseData purchaseData) {
		for (int i=0; i<purchaseDataVector.size(); i++) {
			PurchaseData purchaseDataFromVector = purchaseDataVector.get(i);
			if (purchaseData.getPurchase().getId().compareTo(purchaseDataFromVector.getPurchase().getId()) == 0)
				purchaseDataVector.remove(i);
			break;
		}
	}
	
	private void removePurchases() {
		for (int i=0; i<getSelectedPurchasesDataVector().size(); i++) {
			if ((Boolean) getTblAsocciatedPurchases().getValueAt(i, getTblPurchasesSelectionColumnIndex())) {
				PurchaseData purchaseData = (PurchaseData) DeepCopy.copy(getSelectedPurchasesDataVector().get(i));
				getPurchasesDataVector().add(purchaseData);
				getPurchasesDataVectorFiltered().add(purchaseData);
				addRowToTbl(getTblPurchases(), purchaseData);
				getSelectedPurchasesDataVector().remove(i);
				((DefaultTableModel) getTblAsocciatedPurchases().getModel()).removeRow(i);
				i--;
			}
		}
	}
	
	private void cleanPurchasesDataVectorFiltered() {
		setPurchasesDataVectorFiltered(new Vector<PurchaseData>());
		getPurchasesDataVectorFiltered().clear();
	}
	
	private void cleanTable(JTable jTable) {
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		int rows = jTable.getRowCount();
		for(int j=rows;j>0;--j)
			dtm.removeRow(j-1);
	}
	
	private boolean isFiltered(PurchaseData purchaseData) {
		String purchaseNumber = purchaseData.getPurchase().getPreimpreso();
		String purchaseNumberFilter = getTxtInvoiceNumber().getText()!=SpiritConstants.getNullValue()?getTxtInvoiceNumber().getText().trim():(String)SpiritConstants.getNullValue();
		if (purchaseNumberFilter != null) {
			if (!purchaseNumberFilter.equals(SpiritConstants.getEmptyCharacter()) && !purchaseNumberFilter.equals(purchaseNumber))
				return false;
		}
		java.sql.Date emissionDate = purchaseData.getPurchase().getFecha();
		java.sql.Date initialDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtInitialDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, initialDateFilter) < 0)
			return false;
		java.sql.Date finalDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtFinalDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, finalDateFilter) > 0)
			return false;
		return true;
	}
	
	private void addRowToTbl(JTable jTable, PurchaseData purchaseData) {
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		Vector<Object> dataRow = new Vector<Object>();
		CompraIf purchase = purchaseData.getPurchase();
		ClienteIf provider = purchaseData.getProvider();
		dataRow.add(new Boolean(false));
		dataRow.add(purchase.getPreimpreso());
		dataRow.add(Utilitarios.getFechaCortaUppercase(Utilitarios.fromSqlDateToUtilDate(purchase.getFecha())));
		dataRow.add(purchase.getObservacion());
		dataRow.add(provider.getRazonSocial());		
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(purchase.getValor()));
		dtm.addRow(dataRow);
	}
	
	private void initTextFields() {
		java.util.Date now = new java.util.Date();
		Utilitarios.setDateFormatter(getTxtInitialDate(), SpiritConstants.getDateFormatPattern());
		getTxtInitialDate().setValue(now);
		getTxtInitialDate().setName("txtInitialDate");
		Utilitarios.setDateFormatter(getTxtFinalDate(), SpiritConstants.getDateFormatPattern());
		getTxtFinalDate().setValue(now);
		getTxtFinalDate().setName("txtFinalDate");
	}
	
	private void initSpTblPurchasesButtons() {
		getBtnRemoveFilters().setToolTipText("Remover filtros");
		getBtnRemoveFilters().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/removeFilters.png"));
		getBtnFilterList().setToolTipText("Filtrar listado de órdenes");
		getBtnFilterList().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/filterElements.png"));
	}

	public java.util.Date getInitialDateFilter() {
		return initialDateFilter;
	}

	public void setInitialDateFilter(java.util.Date initialDateFilter) {
		this.initialDateFilter = initialDateFilter;
	}

	public java.util.Date getFinalDateFilter() {
		return finalDateFilter;
	}

	public void setFinalDateFilter(java.util.Date finalDateFilter) {
		this.finalDateFilter = finalDateFilter;
	}

	public Vector<PurchaseData> getPurchasesDataVector() {
		return purchasesDataVector;
	}

	public void setPurchasesDataVector(Vector<PurchaseData> purchasesDataVector) {
		this.purchasesDataVector = purchasesDataVector;
	}

	public Vector<PurchaseData> getPurchasesDataVectorFiltered() {
		return purchasesDataVectorFiltered;
	}

	public void setPurchasesDataVectorFiltered(Vector<PurchaseData> purchasesDataVectorFiltered) {
		this.purchasesDataVectorFiltered = purchasesDataVectorFiltered;
	}

	public static int getTblPurchasesSelectionColumnIndex() {
		return TBL_PURCHASES_SELECTION_COLUMN_INDEX;
	}

	public static int getTblPurchasesCodeColumnIndex() {
		return TBL_PURCHASES_CODE_COLUMN_INDEX;
	}

	public static int getTblPurchasesDateColumnIndex() {
		return TBL_PURCHASES_DATE_COLUMN_INDEX;
	}

	public static int getTblPurchasesConceptColumnIndex() {
		return TBL_PURCHASES_CONCEPT_COLUMN_INDEX;
	}

	public static int getTblPurchasesCustomerColumnIndex() {
		return TBL_PURCHASES_CUSTOMER_COLUMN_INDEX;
	}

	public static int getTblPurchasesTotalColumnIndex() {
		return TBL_PURCHASES_TOTAL_COLUMN_INDEX;
	}

	public ClienteOficinaIf getProvider() {
		return provider;
	}

	public void setProvider(ClienteOficinaIf provider) {
		this.provider = provider;
	}

	public Vector<PurchaseData> getSelectedPurchasesDataVector() {
		return selectedPurchasesDataVector;
	}

	public void setSelectedPurchasesDataVector(Vector<PurchaseData> selectedPurchasesDataVector) {
		this.selectedPurchasesDataVector = selectedPurchasesDataVector;
	}

	public Vector<PurchaseData> getAssociatedPurchasesDataVector() {
		return associatedPurchasesDataVector;
	}

	public void setAssociatedPurchasesDataVector(Vector<PurchaseData> associatedPurchasesDataVector) {
		this.associatedPurchasesDataVector = associatedPurchasesDataVector;
	}

	public boolean isCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(boolean cancelAction) {
		this.cancelAction = cancelAction;
	}
}
