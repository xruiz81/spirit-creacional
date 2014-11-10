package com.spirit.compras.gui.model;

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
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gui.controller.PurchaseConstants;
import com.spirit.compras.gui.panel.JDAsociarOrden;
import com.spirit.compras.handler.OrderData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class AsociarOrdenModel extends JDAsociarOrden {
	private static final long serialVersionUID = -379041898633974146L;
	private CompraIf purchase;
	private ClienteOficinaIf provider;
	private Vector<OrderData> selectedOrdersDataVector = new Vector<OrderData>();
	private Vector<OrderData> associatedOrdersDataVector = new Vector<OrderData>();
	private java.util.Date initialDateFilter;
	private java.util.Date finalDateFilter;
	private Vector<OrderData> ordersDataVector = new Vector<OrderData>();
	private Vector<OrderData> ordersDataVectorFiltered = new Vector<OrderData>();
	private static final int TBL_ORDERS_SELECTION_COLUMN_INDEX = 0;
	private static final int TBL_ORDERS_ORDER_CODE_COLUMN_INDEX = 1;
	private static final int TBL_ORDERS_TYPE_COLUMN_INDEX = 2;
	private static final int TBL_ORDERS_DATE_COLUMN_INDEX = 3;
	private static final int TBL_ORDERS_CONCEPT_COLUMN_INDEX = 4;
	private static final int TBL_ORDERS_CUSTOMER_COLUMN_INDEX = 5;
	private static final int TBL_ORDERS_TOTAL_COLUMN_INDEX = 6;
	private boolean cancelAction = false;

	public AsociarOrdenModel(Frame owner, Vector<OrderData> selectedOrdersDataVector, ClienteOficinaIf provider, CompraIf purchase) {
		super(owner);
		setPurchase(purchase);
		setProvider(provider);
		setSelectedOrdersDataVector((Vector<OrderData>) DeepCopy.copy(selectedOrdersDataVector));
		setAssociatedOrdersDataVector(selectedOrdersDataVector);
		initDialogComponents();
		initListeners();
		initJDialogAdapter(this);
		initDialog();
	}
	
	private void initDialogComponents() {
		initSpTblOrders();
		initTblOrders();
		initTblAssociatedOrders();
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
				/*if (hasPendingSaveChanges()) {
	    	        Object[] options = {SpiritConstants.getOptionYes(), SpiritConstants.getOptionNo()}; 
	    			int option = JOptionPane.showOptionDialog(getThis(), "¿Desea guardar los cambios realizados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, SpiritConstants.getOptionNo());
	    			if (option == JOptionPane.YES_OPTION)
	    				acceptChanges();
				}*/				
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void initListeners() {
		getBtnRemoveFilters().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Restoring filters
				initSpTblOrders();
				// Setting initial date filter to the stored initial value
				getTxtInitialDate().setValue(getInitialDateFilter() != null?getInitialDateFilter():new java.util.Date());
				// Setting final date filter to the stored final value
				getTxtFinalDate().setValue(getFinalDateFilter() != null?getFinalDateFilter():new java.util.Date());
				// Restoring pending accounts list
				filteringOrdersDataVector();
				//loadTblOrdersData();
			}
		});

		getBtnFilterList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteringOrdersDataVector();
			}
		});
		
		getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrders();
				//filteringOrdersDataVector();
			}
		});
		
		getBtnRemove().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeOrders();
				//filteringOrdersDataVector();
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
		setAssociatedOrdersDataVector((Vector<OrderData>) DeepCopy.copy(getSelectedOrdersDataVector()));
		setVisible(false);
		dispose();
	}
	
	private void cancel() {
		setCancelAction(true);
		getSelectedOrdersDataVector().clear();
		setSelectedOrdersDataVector(new Vector<OrderData>());
		setVisible(false);
		dispose();
	}
	
	private void initSpTblOrders() {
		initTextFields();
		initSpTblOrdersButtons();
	}
	
	private void initTblOrders() {
		setTblOrdersWidthColumns(getTblOrders());
		setTblOrdersAlignment(getTblOrders());
		//setSorterTable(getTblOrders());
		loadTblOrdersData();
		getTblOrders().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblOrders().getTableHeader().setReorderingAllowed(false);
	}
	
	private void initTblAssociatedOrders() {
		setTblOrdersWidthColumns(getTblAsocciatedOrders());
		setTblOrdersAlignment(getTblAsocciatedOrders());
		//setSorterTable(getTblAsocciatedOrders());
		loadTblAsocciatedOrdersData();
		getTblAsocciatedOrders().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblAsocciatedOrders().getTableHeader().setReorderingAllowed(false);
	}
	
	private void setTblOrdersWidthColumns(JTable jTable) {
		TableColumn columnWidth = jTable.getColumnModel().getColumn(getTblOrdersSelectionColumnIndex());
		columnWidth.setPreferredWidth(20);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersOrderCodeColumnIndex());
		columnWidth.setPreferredWidth(50);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersTypeColumnIndex());
		columnWidth.setPreferredWidth(10);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersDateColumnIndex());
		columnWidth.setPreferredWidth(50);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersConceptColumnIndex());
		columnWidth.setPreferredWidth(200);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersCustomerColumnIndex());
		columnWidth.setPreferredWidth(180);
		columnWidth = jTable.getColumnModel().getColumn(getTblOrdersTotalColumnIndex());
		columnWidth.setPreferredWidth(40);
	}
	
	private void setTblOrdersAlignment(JTable jTable) {
		TableCellRendererHorizontalCenterAlignment horizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		jTable.getColumnModel().getColumn(getTblOrdersOrderCodeColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		jTable.getColumnModel().getColumn(getTblOrdersTypeColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		jTable.getColumnModel().getColumn(getTblOrdersDateColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		jTable.getColumnModel().getColumn(getTblOrdersTotalColumnIndex()).setCellRenderer(horizontalRightAlignment);
	}
	
	private void setSorterTable(JTable table) {
		RowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tableRowSorter);
	}
	
	private void loadTblOrdersData() {
		try {
			Iterator<Object[]> it = SessionServiceLocator.getOrdenCompraSessionService().findPurchaseOrders(getProvider().getId()).iterator();
			while (it.hasNext()) {
				Object[] order = it.next();
				OrderData orderData = addOrderData(order, "OC");
				if (orderData.getPurchaseOrder() != SpiritConstants.getNullValue()) {
					addRowToTbl(getTblOrders(), orderData);
					java.util.Date filterInitialDate = (java.util.Date) getTxtInitialDate().getValue();
					if (Utilitarios.compararFechas(orderData.getPurchaseOrder().getFecha(), Utilitarios.fromUtilDateToSqlDate(filterInitialDate)) < 0) {
						getTxtInitialDate().setValue(orderData.getPurchaseOrder().getFecha());
						// Storing initial date value (Necessary for remove filters operation)
						setInitialDateFilter(orderData.getPurchaseOrder().getFecha());
					}
					java.util.Date filterFinalDate = (java.util.Date) getTxtFinalDate().getValue();
					if (Utilitarios.compararFechas(orderData.getPurchaseOrder().getFecha(), Utilitarios.fromUtilDateToSqlDate(filterFinalDate)) > 0) {
						getTxtFinalDate().setValue(orderData.getPurchaseOrder().getFecha());
						// Storing final date value (Necessary for remove filters operation)
						setFinalDateFilter(orderData.getPurchaseOrder().getFecha());
					}
				}
			}
			it = SessionServiceLocator.getOrdenMedioSessionService().findMediaOrders(getProvider().getId()).iterator();
			while (it.hasNext()) {
				Object[] order = it.next();
				OrderData orderData = addOrderData(order, "OM");
				if (orderData.getMediaOrder() != SpiritConstants.getNullValue()) {
					addRowToTbl(getTblOrders(), orderData);
					java.util.Date filterInitialDate = (java.util.Date) getTxtInitialDate().getValue();
					if (Utilitarios.compararFechas(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()), Utilitarios.fromUtilDateToSqlDate(filterInitialDate)) < 0) {
						getTxtInitialDate().setValue(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()));
						// Storing initial date value (Necessary for remove filters operation)
						setInitialDateFilter(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()));
					}
					java.util.Date filterFinalDate = (java.util.Date) getTxtFinalDate().getValue();
					if (Utilitarios.compararFechas(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()), Utilitarios.fromUtilDateToSqlDate(filterFinalDate)) > 0) {
						getTxtFinalDate().setValue(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()));
						// Storing final date value (Necessary for remove filters operation)
						setFinalDateFilter(Utilitarios.fromTimestampToSqlDate(orderData.getMediaOrder().getFechaOrden()));
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de órdenes", SpiritAlert.ERROR);
		}
	}
	
	private ArrayList loadTblAsocciatedOrdersData() {
		ArrayList dataList = new ArrayList();
		for (int i=0; i<getSelectedOrdersDataVector().size(); i++) {
			OrderData orderData = getSelectedOrdersDataVector().get(i);
			//addSelectedOrderData(orderData, orderData.getOrderType());
			addRowToTbl(getTblAsocciatedOrders(), orderData);
		}
		//setAssociatedOrdersDataVector((Vector<OrderData>) DeepCopy.copy(getSelectedOrdersDataVector()));
		return dataList;
	}
	
	private OrderData addOrderData(Object[] order, String orderType) throws GenericBusinessException {
		OrdenCompraIf purchaseOrder = (OrdenCompraIf) SpiritConstants.getNullValue();
		OrdenMedioIf mediaOrder = (OrdenMedioIf) SpiritConstants.getNullValue();
		OrderData orderData = new OrderData();
		orderData.setOrderType(orderType);
		if (orderType.equals("OC")) {
			purchaseOrder = (OrdenCompraIf) order[0];
			if (purchaseOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)) || (purchaseOrder.getEstado().equals(PurchaseConstants.getEnteredStatus().substring(2,3)) && !isAssociated(purchaseOrder)))
			//if (purchaseOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)) || !isAssociated(purchaseOrder))
				orderData.setPurchaseOrder(purchaseOrder);
		} else {
			mediaOrder = (OrdenMedioIf) order[0];
			if (mediaOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)) || (mediaOrder.getEstado().equals(PurchaseConstants.getEnteredStatus().substring(0,1)) && !isAssociated(mediaOrder)))
			//if (mediaOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)) || !isAssociated(mediaOrder))
				orderData.setMediaOrder(mediaOrder);
		}
		if (orderData.getPurchaseOrder() != SpiritConstants.getNullValue() || (orderData.getMediaOrder() != SpiritConstants.getNullValue() && !orderData.getMediaOrder().getEstado().equals(PurchaseConstants.getNullifyStatus().substring(0,1)))) {
			if (!isAssociated(orderData)) {
				orderData.setProvider(((ClienteIf) order[1]));
				getOrdersDataVector().add(orderData);
				getOrdersDataVectorFiltered().add(orderData);
			} else {
				orderData.setPurchaseOrder(SpiritConstants.getNullValue());
				orderData.setMediaOrder(SpiritConstants.getNullValue());
			}
		}
		return orderData;
	}
	
	private boolean isAssociated(OrdenCompraIf purchaseOrder) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tipoOrden", "OC");
		if (getPurchase() != SpiritConstants.getNullValue())
			queryMap.put("compraId", getPurchase().getId());
		queryMap.put("ordenId", purchaseOrder.getId());
		try {
			Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(queryMap).iterator();
			if ((ordenAsociadaIt.hasNext() && isSelected(purchaseOrder)) || getPurchase() == SpiritConstants.getNullValue() || !ordenAsociadaIt.hasNext())
				return true;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return false;
	}
	
	private boolean isSelected(OrdenCompraIf purchaseOrder) {
		for (int i=0; i<getSelectedOrdersDataVector().size(); i++) {
			OrderData selectedOrderData = getSelectedOrdersDataVector().get(i);
			if (selectedOrderData.getOrderType().equals("OC") && selectedOrderData.getPurchaseOrder().getId().compareTo(purchaseOrder.getId()) == 0)
				return true;
		}
		return false;
	}
	
	private boolean isAssociated(OrdenMedioIf mediaOrder) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tipoOrden", "OM");
		queryMap.put("ordenId", mediaOrder.getId());
		try {
			Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(queryMap).iterator();
			if ((ordenAsociadaIt.hasNext() && isSelected(mediaOrder)) || getPurchase() == SpiritConstants.getNullValue() || !ordenAsociadaIt.hasNext())
				return true;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return false;
	}
	
	private boolean isSelected(OrdenMedioIf mediaOrder) {
		for (int i=0; i<getSelectedOrdersDataVector().size(); i++) {
			OrderData selectedOrderData = getSelectedOrdersDataVector().get(i);
			if (selectedOrderData.getOrderType().equals("OM") && selectedOrderData.getMediaOrder().getId().compareTo(mediaOrder.getId()) == 0)
				return true;
		}
		return false;
	}
	
	private boolean isAssociated(OrderData orderData) {
		for (int i=0; i<getSelectedOrdersDataVector().size(); i++) {
			OrderData selectedOrderData = getSelectedOrdersDataVector().get(i);
			if (selectedOrderData.getOrderType().equals(orderData.getOrderType())) {
				if (selectedOrderData.getOrderType().equals("OC") && selectedOrderData.getPurchaseOrder().getId().compareTo(orderData.getPurchaseOrder().getId()) == 0)
					return true;
				if (selectedOrderData.getOrderType().equals("OM") && selectedOrderData.getMediaOrder().getId().compareTo(orderData.getMediaOrder().getId()) == 0)
					return true;
			}
		}
		return false;
	}
	
	private OrderData addSelectedOrderData(OrderData order, String orderType) {
		OrdenCompraIf purchaseOrder = (OrdenCompraIf) SpiritConstants.getNullValue();
		OrdenMedioIf mediaOrder = (OrdenMedioIf) SpiritConstants.getNullValue();
		OrderData orderData = new OrderData();
		orderData.setOrderType(orderType);
		if (orderType.equals("OC")) {
			purchaseOrder = order.getPurchaseOrder();
			if (purchaseOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)))
				orderData.setPurchaseOrder(purchaseOrder);
		} else {
			mediaOrder = order.getMediaOrder();
			if (mediaOrder.getEstado().equals(PurchaseConstants.getSendStatus().substring(0,1)))
				orderData.setMediaOrder(mediaOrder);
		}
		if (purchaseOrder != SpiritConstants.getNullValue() || (mediaOrder != SpiritConstants.getNullValue() && !mediaOrder.getEstado().equals(PurchaseConstants.getNullifyStatus().substring(0,1)))) {
			orderData.setProvider(order.getProvider());
			getSelectedOrdersDataVector().add(orderData);
		}
		return orderData;
	}
	
	private void filteringOrdersDataVector() {
		cleanOrdersDataVectorFiltered();
		cleanTable(getTblOrders());
		for (int i=0; i<getOrdersDataVector().size(); i++) {
			OrderData orderData = getOrdersDataVector().get(i);
			if (isFiltered(orderData) && !isAssociated(orderData)) {
			//if (isFiltered(orderData)) {
				getOrdersDataVectorFiltered().add(orderData);
				addRowToTbl(getTblOrders(), orderData);
			}
		}
	}
	
	private void addOrders() {
		//for (int i=0; i<getOrdersDataVector().size(); i++) {
		for (int i=0; i<getOrdersDataVectorFiltered().size(); i++) {
			if ((Boolean) getTblOrders().getValueAt(i, getTblOrdersSelectionColumnIndex())) {
				//OrderData orderData = (OrderData) DeepCopy.copy(getOrdersDataVector().get(i));
				OrderData orderData = (OrderData) DeepCopy.copy(getOrdersDataVectorFiltered().get(i));
				getSelectedOrdersDataVector().add(orderData);
				addRowToTbl(getTblAsocciatedOrders(), orderData);
				//removeOrderData(getOrdersDataVector(), orderData);
				removeOrderData(getOrdersDataVector(), orderData);
				//getOrdersDataVector().remove(i);
				getOrdersDataVectorFiltered().remove(i);
				((DefaultTableModel) getTblOrders().getModel()).removeRow(i);
				i--;
			}
		}
	}
	
	private void removeOrderData(Vector<OrderData> orderDataVector, OrderData orderData) {
		for (int i=0; i<orderDataVector.size(); i++) {
			OrderData orderDataFromVector = orderDataVector.get(i);
			if (orderData.getOrderType().equals(orderDataFromVector.getOrderType())) {
				if ((orderData.getOrderType().equals("OC") && orderData.getPurchaseOrder().getId().compareTo(orderDataFromVector.getPurchaseOrder().getId()) == 0) || (orderData.getOrderType().equals("OM") && orderData.getMediaOrder().getId().compareTo(orderDataFromVector.getMediaOrder().getId()) == 0))
					orderDataVector.remove(i);
				break;
			}
		}
	}
	
	private void removeOrders() {
		for (int i=0; i<getSelectedOrdersDataVector().size(); i++) {
			if ((Boolean) getTblAsocciatedOrders().getValueAt(i, getTblOrdersSelectionColumnIndex())) {
				OrderData orderData = (OrderData) DeepCopy.copy(getSelectedOrdersDataVector().get(i));
				getOrdersDataVector().add(orderData);
				getOrdersDataVectorFiltered().add(orderData);
				addRowToTbl(getTblOrders(), orderData);
				getSelectedOrdersDataVector().remove(i);
				((DefaultTableModel) getTblAsocciatedOrders().getModel()).removeRow(i);
				i--;
			}
		}
	}
	
	private void cleanOrdersDataVectorFiltered() {
		setOrdersDataVectorFiltered(new Vector<OrderData>());
		getOrdersDataVectorFiltered().clear();
	}
	
	private void cleanTable(JTable jTable) {
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		int rows = jTable.getRowCount();
		for(int j=rows;j>0;--j)
			dtm.removeRow(j-1);
	}
	
	private boolean isFiltered(OrderData orderData) {
		String[] typeArray = (!getCmbType().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))?getCmbType().getSelectedItem().toString().split("ORDEN DE "):(String[]) SpiritConstants.getNullValue();
		String typeFilter = (typeArray!=SpiritConstants.getNullValue())?"O"+typeArray[1].substring(0,1):(String)SpiritConstants.getNullValue();
		if (typeFilter != null) {
			if (typeFilter != SpiritConstants.getNullValue() && !orderData.getOrderType().equals(typeFilter))
				return false;
		}
		String orderCode = (orderData.getOrderType().equals("OC"))?orderData.getPurchaseOrder().getCodigo():orderData.getMediaOrder().getCodigo();
		String orderCodeFilter = getTxtOrderNumber().getText()!=SpiritConstants.getNullValue()?getTxtOrderNumber().getText().trim():(String)SpiritConstants.getNullValue();
		if (orderCodeFilter != null) {
			if (!orderCodeFilter.equals(SpiritConstants.getEmptyCharacter()) && !orderCodeFilter.equals(orderCode))
				return false;
		}
		java.sql.Date emissionDate = (orderData.getOrderType().equals("OC"))?orderData.getPurchaseOrder().getFecha():Utilitarios.fromUtilDateToSqlDate(orderData.getMediaOrder().getFechaOrden());
		java.sql.Date initialDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtInitialDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, initialDateFilter) < 0)
			return false;
		java.sql.Date finalDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtFinalDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, finalDateFilter) > 0)
			return false;
		return true;
	}
	
	private void addRowToTbl(JTable jTable, OrderData orderData) {
		DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
		Vector<Object> dataRow = new Vector<Object>();
		String orderType = orderData.getOrderType();
		OrdenCompraIf purchaseOrder = orderData.getPurchaseOrder();
		OrdenMedioIf mediaOrder = orderData.getMediaOrder();
		ClienteIf businessOperator = orderData.getProvider();
		dataRow.add(new Boolean(false));
		dataRow.add(orderType.equals("OC")?purchaseOrder.getCodigo():mediaOrder.getCodigo());
		dataRow.add(orderType);
		dataRow.add(orderType.equals("OC")?Utilitarios.getFechaCortaUppercase(Utilitarios.fromSqlDateToUtilDate(purchaseOrder.getFecha())):Utilitarios.getFechaCortaUppercase(mediaOrder.getFechaOrden()));
		String concept = orderType.equals("OC")?purchaseOrder.getObservacion():mediaOrder.getObservacion();
		try {
			if (concept == SpiritConstants.getNullValue() || concept.trim().equals(SpiritConstants.getEmptyCharacter())) {
				if (orderType.equals("OC")) {
					Iterator<PresupuestoIf> budgetIt = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByOrdenCompraId(purchaseOrder.getId()).iterator();
					if (budgetIt.hasNext()) {
						PresupuestoIf budget = budgetIt.next();
						concept = budget.getConcepto();
					}
				} else {
					PlanMedioIf mediaPlan = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(mediaOrder.getPlanMedioId());
					concept = mediaPlan.getConcepto();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al recuperar datos de órdenes", SpiritAlert.ERROR);
		}
		dataRow.add(concept);
		dataRow.add(businessOperator.getRazonSocial());		
		dataRow.add(orderType.equals("OC")?SpiritConstants.getDecimalFormatPattern().format(purchaseOrder.getValor()):SpiritConstants.getDecimalFormatPattern().format(mediaOrder.getValorSubtotal()));
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
	
	private void initSpTblOrdersButtons() {
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

	public Vector<OrderData> getOrdersDataVector() {
		return ordersDataVector;
	}

	public void setOrdersDataVector(Vector<OrderData> ordersDataVector) {
		this.ordersDataVector = ordersDataVector;
	}

	public Vector<OrderData> getOrdersDataVectorFiltered() {
		return ordersDataVectorFiltered;
	}

	public void setOrdersDataVectorFiltered(
			Vector<OrderData> ordersDataVectorFiltered) {
		this.ordersDataVectorFiltered = ordersDataVectorFiltered;
	}

	public static int getTblOrdersSelectionColumnIndex() {
		return TBL_ORDERS_SELECTION_COLUMN_INDEX;
	}

	public static int getTblOrdersOrderCodeColumnIndex() {
		return TBL_ORDERS_ORDER_CODE_COLUMN_INDEX;
	}

	public static int getTblOrdersTypeColumnIndex() {
		return TBL_ORDERS_TYPE_COLUMN_INDEX;
	}

	public static int getTblOrdersDateColumnIndex() {
		return TBL_ORDERS_DATE_COLUMN_INDEX;
	}

	public static int getTblOrdersConceptColumnIndex() {
		return TBL_ORDERS_CONCEPT_COLUMN_INDEX;
	}

	public static int getTblOrdersCustomerColumnIndex() {
		return TBL_ORDERS_CUSTOMER_COLUMN_INDEX;
	}

	public static int getTblOrdersTotalColumnIndex() {
		return TBL_ORDERS_TOTAL_COLUMN_INDEX;
	}

	public CompraIf getPurchase() {
		return purchase;
	}

	public void setPurchase(CompraIf purchase) {
		this.purchase = purchase;
	}

	public ClienteOficinaIf getProvider() {
		return provider;
	}

	public void setProvider(ClienteOficinaIf provider) {
		this.provider = provider;
	}

	public Vector<OrderData> getSelectedOrdersDataVector() {
		return selectedOrdersDataVector;
	}

	public void setSelectedOrdersDataVector(
			Vector<OrderData> selectedOrdersDataVector) {
		this.selectedOrdersDataVector = selectedOrdersDataVector;
	}

	public Vector<OrderData> getAssociatedOrdersDataVector() {
		return associatedOrdersDataVector;
	}

	public void setAssociatedOrdersDataVector(Vector<OrderData> associatedOrdersDataVector) {
		this.associatedOrdersDataVector = associatedOrdersDataVector;
	}

	public boolean isCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(boolean cancelAction) {
		this.cancelAction = cancelAction;
	}
}
