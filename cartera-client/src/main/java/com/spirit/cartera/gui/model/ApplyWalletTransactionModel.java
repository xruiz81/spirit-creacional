package com.spirit.cartera.gui.model;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.grid.DateCellEditor;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.controller.ApplyingDocumentsTableModel;
import com.spirit.cartera.gui.controller.ApplyingDocumentsTableWidget;
import com.spirit.cartera.gui.controller.WalletConstants;
import com.spirit.cartera.gui.controller.WalletKeyboardFocusManager;
import com.spirit.cartera.gui.panel.JDApplyWalletTransaction;
import com.spirit.cartera.handler.CrossingWalletDetailData;
import com.spirit.cartera.handler.PendingAccountData;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CruceDocumentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.MyCurrencyEditor;
import com.spirit.general.gui.controller.MyCurrencyRenderer;
import com.spirit.util.DeepCopy;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class ApplyWalletTransactionModel extends JDApplyWalletTransaction {
	private static final long serialVersionUID = -4880779129986363452L;
	private String dialogTitle;
	private WalletData walletData;
	private int operationMode;
	private Vector<PendingAccountData> pendingAccountsDataVector = new Vector<PendingAccountData>();
	private Vector<PendingAccountData> pendingAccountsDataVectorFiltered = new Vector<PendingAccountData>();
	private Vector<WalletDetailData> selectedWalletDetailDataVector;
	private Vector<WalletDetailData> applyingDocumentsVector;
	private Vector<CrossingWalletDetailData> crossingWalletDetailsVector;
	private static final int TBL_PENDING_ACCOUNTS_SELECTION_COLUMN_INDEX = 0;
	private static final int TBL_PENDING_ACCOUNTS_TRANSACTION_COLUMN_INDEX = 1;
	private static final int TBL_PENDING_ACCOUNTS_DATE_COLUMN_INDEX = 2;
	private static final int TBL_PENDING_ACCOUNTS_OVERDUE_DAYS_COLUMN_INDEX = 3;
	private static final int TBL_PENDING_ACCOUNTS_VALUE_COLUMN_INDEX = 4;
	private static final int TBL_PENDING_ACCOUNTS_BALANCE_COLUMN_INDEX = 5;
	private static final int TBL_APPLYING_DOCUMENTS_TRANSACTION_COLUMN_INDEX = 0;
	private static final int TBL_APPLYING_DOCUMENTS_BALANCE_COLUMN_INDEX = 1;
	private static final int TBL_APPLYING_DOCUMENTS_VALUE_TO_APPLY_COLUMN_INDEX = 2;
	private static final int TBL_APPLYING_DOCUMENTS_DATE_TO_APPLY_COLUMN_INDEX = 3;
	private static int applyAction;
	private boolean pendingSaveChanges = false;
	private java.util.Date initialDateFilter;
	private Map<Long, DocumentoIf> applyDocumentsMap = new HashMap<Long, DocumentoIf>();
	private Map<Long, Vector<CruceDocumentoIf>> crossingDocumentsMap = new HashMap<Long, Vector<CruceDocumentoIf>>();
	private int tblApplyingDocumentsSelectedRow;
	private boolean tblApplyingDocumentsStopCellEditing;
	private java.util.Date dateToApply;
	
	@SuppressWarnings("unchecked")
	public ApplyWalletTransactionModel(Frame owner, WalletData walletData, Vector<WalletDetailData> selectedWalletDetailDataVector, Vector<CrossingWalletDetailData> crossingWalletDetailsVector, int operationMode) {
		super(owner);
		setWalletData(walletData);
		setCrossingWalletDetailsVector(crossingWalletDetailsVector);
		setOperationMode(operationMode);
		setDialogTitle(this.getTitle());
		setSelectedWalletDetailDataVector(selectedWalletDetailDataVector);
		setApplyingDocumentsVector((Vector<WalletDetailData>) DeepCopy.copy(selectedWalletDetailDataVector));
		setDateToApply(getWalletData().getEmissionDate());
		initDialogComponents();
		initObjects();
		WalletKeyboardFocusManager.initKeyboardFocusManager();
		initFocusComponentsOrderKeyListener();
		initListeners();
		initJDialogAdapter(this);
		initDialog();
	}
	
	private void initDialog() {
		this.setSize(WalletConstants.getJdialogApplyWalletTransactionWidth(), WalletConstants.getJdialogApplyWalletTransactionHeight());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - WalletConstants.getJdialogApplyWalletTransactionWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - WalletConstants.getJdialogApplyWalletTransactionHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void initDialogComponents() {
		initJpPendingAccounts();
		initTblPendingAccounts();
		initTblApplyingDocuments();
		initComboBoxes();
		initButtons();
	}
	
	private void initObjects() {
		setCrossingDocumentsMap(mappingCrossingDocuments());
	}
	
	private void initButtons() {
		if (getCrossingWalletDetailsVector().size() > 0)
			getBtnUndo().setEnabled(true);
		else
			getBtnUndo().setEnabled(false);
		getBtnUndo().setName("btnUndo");
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long, Vector<CruceDocumentoIf>> mappingCrossingDocuments() {
		Map<Long, Vector<CruceDocumentoIf>> crossingDocumentsMap = new HashMap<Long, Vector<CruceDocumentoIf>>();
		Iterator<Long> it = getApplyDocumentsMap().keySet().iterator();
		while (it.hasNext()) {
			DocumentoIf applyDocument = getApplyDocumentsMap().get(it.next());
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("documentoId", applyDocument.getId());
			if (getOperationMode() == SpiritMode.SAVE_MODE)
				queryMap.put("validoAlGuardar", SpiritConstants.getOptionYes().substring(0, 1));
			else if (getOperationMode() == SpiritMode.UPDATE_MODE)
				queryMap.put("validoAlActualizar", SpiritConstants.getOptionYes().substring(0, 1));
			try {
				Iterator<CruceDocumentoIf> crossingDocumentsIt = SessionServiceLocator.getCruceDocumentoSessionService().findCruceDocumentoByQuery(queryMap).iterator();
				Vector<CruceDocumentoIf> crossingDocumentsVector = new Vector<CruceDocumentoIf>();
				while (crossingDocumentsIt.hasNext()) {
					CruceDocumentoIf crossingDocument = crossingDocumentsIt.next();
					crossingDocumentsVector.add(crossingDocument);
				}
				crossingDocumentsMap.put(applyDocument.getId(), crossingDocumentsVector);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
			}
			
		}
		return crossingDocumentsMap;
	}

	private void initJpPendingAccounts() {
		initTextFields();
		initJpPendingAccountsButtons();
	}

	private void initJDialogAdapter(JDialog jDialog) {
		jDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (hasPendingSaveChanges()) {
	    	        Object[] options = {SpiritConstants.getOptionYes(), SpiritConstants.getOptionNo()}; 
	    			int option = JOptionPane.showOptionDialog(getThis(), "¿Desea guardar los cambios realizados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, SpiritConstants.getOptionNo());
	    			if (option == JOptionPane.YES_OPTION)
	    				acceptChanges();
				}				
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void initListeners() {
		getBtnRemoveFilters().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Restoring filters
				initJpPendingAccounts();
				// Setting initial date filter to the stored initial value
				getTxtInitialDate().setValue(getInitialDateFilter() != null?getInitialDateFilter():new java.util.Date());
				// Restoring comboboxes
				initComboBoxes();
				// Restoring pending accounts list
				filteringPendingAccountsDataVector();
			}
		});
		
		getBtnFilterList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteringPendingAccountsDataVector();
			}
		});
		
		/*getBtnAccept().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (validateAction(false)) {
					acceptChanges();
					updateApplyingDocuments();
				}
			}
		});*/
		
		getBtnAccept().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (validateAction(false)) {
						acceptChanges();
						updateApplyingDocuments();
					}
				}
			}
		});
		
		getBtnAccept().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (validateAction(false)) {
					acceptChanges();
					updateApplyingDocuments();
				}
			}
		});
		
		/*getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				undo(true);
				setVisible(false);
				dispose();
			}
		});*/
		
		getBtnCancel().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					undo(true);
					setVisible(false);
					dispose();
				}
			}
		});
		
		getBtnCancel().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				undo(true);
				setVisible(false);
				dispose();
			}
		});
		
		/*getBtnReverse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Object[] options = {SpiritConstants.getOptionYes(), SpiritConstants.getOptionNo()}; 
	    		int option = JOptionPane.showOptionDialog(getThis(), "Esta operación revertirá los cruces realizados anteriormente. ¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, SpiritConstants.getOptionNo());
	    		if (option == JOptionPane.YES_OPTION) {
					try {
						reverse();
						updateApplyingDocuments();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al realizar la operación", SpiritAlert.ERROR);
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al realizar la operación", SpiritAlert.ERROR);
					}
	    		}
			}
		});*/
		
		/*getBtnUndo().addActionListener(new ActionListener() {
			public void actionPer
			formed(ActionEvent ev) {
				undo(false);
				updateApplyingDocuments();
				getBtnUndo().setEnabled(false);
			}
		});*/
		
		getBtnUndo().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					undo(false);
					updateApplyingDocuments();
					getBtnUndo().setEnabled(false);
				}
			}
		});
		
		getBtnUndo().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				undo(false);
				updateApplyingDocuments();
				getBtnUndo().setEnabled(false);
			}
		});
		
		/*getBtnApply().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (validateAction(true)) {
					applyChanges();
					getBtnUndo().setEnabled(true);
				}
			}
		});*/
		
		getBtnApply().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (validateAction(true)) {
						applyChanges();
						getBtnUndo().setEnabled(true);
					}
				}
			}
		});
		
		getBtnApply().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (validateAction(true)) {
					applyChanges();
					getBtnUndo().setEnabled(true);
				}
			}
		});
		
		getTblPendingAccounts().addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint(); 
				int row = getTblPendingAccounts().rowAtPoint(p);
				int column = getTblPendingAccounts().columnAtPoint(p);
				if (column == getTblPendingAccountsTransactionColumnIndex()) {
					String toolTipText = getTblPendingAccounts().getValueAt(row,column) != null?String.valueOf(getTblPendingAccounts().getValueAt(row,column)):SpiritConstants.getEmptyCharacter();
					getTblPendingAccounts().setToolTipText(toolTipText);
				}
			}
		});
		
		getTblPendingAccounts().addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				getTblPendingAccounts().clearSelection();
			}			
		});
		
		getTblApplyingDocuments().addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint(); 
				int row = getTblApplyingDocuments().rowAtPoint(p);
				int column = getTblApplyingDocuments().columnAtPoint(p);
				if (column == getTblApplyingDocumentsTransactionColumnIndex()) {
					String toolTipText = getTblApplyingDocuments().getValueAt(row,column) != null?String.valueOf(getTblApplyingDocuments().getValueAt(row,column)):SpiritConstants.getEmptyCharacter();
					getTblApplyingDocuments().setToolTipText(toolTipText);
				}
			}
		});
		
		getTblApplyingDocuments().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				setTblApplyingDocumentsSelectedRow(((JTable) e.getSource()).getSelectedRow());
			}			
		});
		
		getTblApplyingDocuments().addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				getTblApplyingDocuments().clearSelection();
			}
			public void focusGained(FocusEvent e) {
				if (getTblApplyingDocuments().getCellEditor() != SpiritConstants.getNullValue())
					setTblApplyingDocumentsStopCellEditing(true);
			}
		});
	}
	
	private void initFocusComponentsOrderKeyListener() {
		getBtnRemoveFilters().addKeyListener(focusComponentOrderKeyAdapter(getBtnRemoveFilters(), getBtnFilterList(), false, false));
		getBtnFilterList().addKeyListener(focusComponentOrderKeyAdapter(getBtnFilterList(), getCmbTransaction(), false, false));
		getCmbTransaction().addKeyListener(focusComponentOrderKeyAdapter(getCmbTransaction(), getTxtInitialDate(), false, false));
		getTxtInitialDate().addKeyListener(focusComponentOrderKeyAdapter(getTxtInitialDate(), getTxtFinalDate(), false, false));
		getTxtFinalDate().addKeyListener(focusComponentOrderKeyAdapter(getTxtFinalDate(), getCmbOverdueDays(), false, false));
		getCmbOverdueDays().addKeyListener(focusComponentOrderKeyAdapter(getCmbOverdueDays(), getTxtValueGreaterThan(), false, false));
		getTxtValueGreaterThan().addKeyListener(focusComponentOrderKeyAdapter(getTxtValueGreaterThan(), getTxtBalanceGreaterThan(), false, false));
		getTxtBalanceGreaterThan().addKeyListener(focusComponentOrderKeyAdapter(getTxtBalanceGreaterThan(), getTblPendingAccounts(), true, false));
		getTblPendingAccounts().addKeyListener(focusComponentOrderKeyAdapter(getTblPendingAccounts(), getTblApplyingDocuments(), false, true));
		getTblApplyingDocuments().addKeyListener(focusComponentOrderKeyAdapter(getTblApplyingDocuments(), getBtnAccept(), false, false));
		getBtnAccept().addKeyListener(focusComponentOrderKeyAdapter(getBtnAccept(), getBtnCancel(), false, false));
		getBtnCancel().addKeyListener(focusComponentOrderKeyAdapter(getBtnCancel(), getBtnUndo(), false, false));
		getBtnUndo().addKeyListener(focusComponentOrderKeyAdapter(getBtnUndo(), getBtnApply(), false, false));
		getBtnApply().addKeyListener(focusComponentOrderKeyAdapter(getBtnApply(), getBtnRemoveFilters(), false, false));
		setTblPendingAccountsVkEnterCustomBehavior();
		setTblPendingAccountsVkSpaceCustomBehavior();
		setTblApplyingDocumentsVkEnterCustomBehavior();
		setTblApplyingDocumentsVkSpaceCustomBehavior();
		setTblApplyingDocumentsVkEscapeCustomBehavior();
	}
	
	private void setTblPendingAccountsVkEnterCustomBehavior() {
		getTblPendingAccounts().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItemTblPendingAccounts");
		getTblPendingAccounts().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItemTblPendingAccounts");
		getTblPendingAccounts().getActionMap().put("selectItemTblPendingAccounts", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección es necesaria para evitar el comportamiento default de la tecla ENTER dentro de la tabla de detalles.
    			getTblPendingAccounts().clearSelection();
    		}
    	});
	}
	
	private void setTblApplyingDocumentsVkEnterCustomBehavior() {
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItemTblApplyingDocuments");
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItemTblApplyingDocuments");
		getTblApplyingDocuments().getActionMap().put("selectItemTblApplyingDocuments", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección es necesaria para evitar el comportamiento default de la tecla ENTER dentro de la tabla de detalles.
    			//if (getTblApplyingDocuments().getCellEditor() != null) {
    				/*System.out.println(">>>>>>>>>>>> T   -   R   -   U   -   E <<<<<<<<<<<<<<<");
    				setTblApplyingDocumentsStopCellEditing(true);
    				getTblApplyingDocuments().grabFocus();
    				getTblApplyingDocuments().setRowSelectionInterval(getTblApplyingDocumentsSelectedRow(), getTblApplyingDocumentsSelectedRow());*/
    			    //getTblApplyingDocuments().getCellEditor().stopCellEditing();
    			//} else {
    				//System.out.println(">>>>>>>>>>>> F  -  A   -   L   -   S   -   E <<<<<<<<<<<<<<<");
    				//getTblApplyingDocuments().getCellEditor().stopCellEditing();
    				/*getTblApplyingDocuments().clearSelection();
    				setTblApplyingDocumentsStopCellEditing(false);*/
    			//}
    		}
    	});
	}
	
	private void setTblPendingAccountsVkSpaceCustomBehavior() {
		getTblPendingAccounts().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItemTblPendingAccounts");
		getTblPendingAccounts().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItemTblPendingAccounts");
		getTblPendingAccounts().getActionMap().put("hitItemTblPendingAccounts", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			int selectedRow = getTblPendingAccounts().getSelectedRow();
    			if (selectedRow > -1) {
    				boolean selected = ((Boolean) getTblPendingAccounts().getValueAt(selectedRow, WalletConstants.getTblPendingAccountsSelectionColumnIndex())).booleanValue();
    				getTblPendingAccounts().setValueAt(new Boolean(!selected), selectedRow, WalletConstants.getTblPendingAccountsSelectionColumnIndex());
    			}
    		}
    	});
	}
	
	private void setTblApplyingDocumentsVkSpaceCustomBehavior() {
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItemTblApplyingDocuments");
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItemTblApplyingDocuments");
		getTblApplyingDocuments().getActionMap().put("hitItemTblApplyingDocuments", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			int selectedRow = getTblApplyingDocuments().getSelectedRow();
    			if (selectedRow > -1) {
    				//boolean selected = ((Boolean) getTblApplyingDocuments().getValueAt(selectedRow, WalletConstants.getTblPendingAccountsSelectionColumnIndex())).booleanValue();
    				setTblApplyingDocumentsSelectedRow(getTblApplyingDocuments().getSelectedRow());
    				getTblApplyingDocuments().clearSelection();
    				boolean success = getTblApplyingDocuments().editCellAt(selectedRow, getTblApplyingDocumentsValueToApplyColumnIndex());
    				if (success) {
    				    boolean toggle = false;
    				    boolean extend = false;
    				    ((DefaultCellEditor) getTblApplyingDocuments().getCellEditor(selectedRow, getTblApplyingDocumentsValueToApplyColumnIndex())).setClickCountToStart(1);
   			         	getTblApplyingDocuments().getEditorComponent().requestFocusInWindow();
   			         	setTblApplyingDocumentsStopCellEditing(true);
    				    //getTblApplyingDocuments().changeSelection(selectedRow, 2, toggle, extend);
    				}
    				success = getTblApplyingDocuments().editCellAt(selectedRow, getTblApplyingDocumentsDateToApplyColumnIndex());
    				if (success) {
    				    boolean toggle = false;
    				    boolean extend = false;
    				    ((DefaultCellEditor) getTblApplyingDocuments().getCellEditor(selectedRow, getTblApplyingDocumentsDateToApplyColumnIndex())).setClickCountToStart(1);
   			         	getTblApplyingDocuments().getEditorComponent().requestFocusInWindow();
   			         	setTblApplyingDocumentsStopCellEditing(true);
    				    //getTblApplyingDocuments().changeSelection(selectedRow, 2, toggle, extend);
    				}
    			}
    		}
    	});
	}
	
	private void setTblApplyingDocumentsVkEscapeCustomBehavior() {
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "deselectItemTblApplyingDocuments");
		getTblApplyingDocuments().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "deselectItemTblApplyingDocuments");
		getTblApplyingDocuments().getActionMap().put("deselectItemTblApplyingDocuments", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección es necesaria para evitar el comportamiento default de la tecla ENTER dentro de la tabla de detalles.
    			int selectedRow = getTblApplyingDocumentsSelectedRow();
    			if (getTblApplyingDocuments().getCellEditor() != null) {
    			    getTblApplyingDocuments().getCellEditor().cancelCellEditing();
    			}
    			getTblApplyingDocuments().setRowSelectionInterval(selectedRow, selectedRow);
    			//getTblApplyingDocuments().clearSelection();
    		}
    	});
	}
	
	private KeyListener focusComponentOrderKeyAdapter(final JComponent jOldComponent, final JComponent jNextComponent, final boolean nextFocusOnTblPendingAccounts, final boolean nextFocusOnTblApplyingDocuments) {
		return(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if ((jOldComponent.getName() == SpiritConstants.getNullValue()) || jOldComponent.getName().equals("txtInitialDate") || jOldComponent.getName().equals("txtFinalDate") || jOldComponent.getName().equals("btnUndo") || jOldComponent.getName().equals("tblApplyingDocuments")) {
						if (!nextFocusOnTblPendingAccounts && !nextFocusOnTblApplyingDocuments && (jOldComponent.getName() == SpiritConstants.getNullValue() || !jOldComponent.getName().equals("tblApplyingDocuments"))) {
							if (jNextComponent.getName() != SpiritConstants.getNullValue() && jNextComponent.getName().equals("btnUndo") && !getBtnUndo().isEnabled())
								getBtnApply().grabFocus();
							else
								jNextComponent.grabFocus();
						} else if (nextFocusOnTblPendingAccounts) {
							if (getTblPendingAccounts().getRowCount() > 0) {
								getTblPendingAccounts().grabFocus();
								getTblPendingAccounts().setRowSelectionInterval(0, 0);
							} else if (getTblApplyingDocuments().getModel().getRowCount() > 0) {
								getTblApplyingDocuments().grabFocus();
								getTblApplyingDocuments().setRowSelectionInterval(0, 0);
							} else
								getBtnAccept().grabFocus();
						} else if (nextFocusOnTblApplyingDocuments && getTblApplyingDocuments().getRowCount() > 0) {
							getTblApplyingDocuments().grabFocus();
							getTblApplyingDocuments().setRowSelectionInterval(0, 0);	
						} else if (jOldComponent.getName() == SpiritConstants.getNullValue() || (jOldComponent.getName().equals("tblApplyingDocuments") && !isTblApplyingDocumentsStopCellEditing())) {
							//AQUI SE DEBE ARREGLAR ENTER LUEGO DE EDITAR TBL_APPLYING_DOCUMENTS
							getTblApplyingDocuments().clearSelection();
							getBtnAccept().grabFocus();
						} else if (isTblApplyingDocumentsStopCellEditing()) {
							if (getTblApplyingDocuments().getCellEditor() != SpiritConstants.getNullValue())
								getTblApplyingDocuments().getCellEditor().stopCellEditing();
							getTblApplyingDocuments().grabFocus();
							getTblApplyingDocuments().setRowSelectionInterval(getTblApplyingDocumentsSelectedRow(), getTblApplyingDocumentsSelectedRow());
							setTblApplyingDocumentsStopCellEditing(false);
						}
					}
				}
			}
		});
	}

	private boolean validateAction(boolean onlyApply) {
		if (getPendingAccountsDataVectorFiltered().size() <= 0) {
			SpiritAlert.createAlert("No se encontraron cuentas pendientes para los parámetros seleccionados", SpiritAlert.WARNING);
			return false;
		}
		if (!hasSelectedPendingAccounts()) {
			if (onlyApply) {
				SpiritAlert.createAlert("Seleccione la(s) cuenta(s) pendiente(s) que desea cruzar", SpiritAlert.WARNING);
				return false;
			} else if (!hasPendingSaveChanges()) {
				Object[] options = {SpiritConstants.getOptionYes(), SpiritConstants.getOptionNo()}; 
	    		int option = JOptionPane.showOptionDialog(getThis(), "No ha aplicado ningún cruce al momento. ¿Desea cerrar esta ventana?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, SpiritConstants.getOptionNo());
	    		if (option == JOptionPane.YES_OPTION) {
	    			return true;
	    		} else {
	    			SpiritAlert.createAlert("Seleccione la(s) cuenta(s) pendiente(s) que desea cruzar", SpiritAlert.WARNING);
					return false;
	    		}
			}
		}
		return true;
	}
	
	private boolean hasSelectedPendingAccounts() {
		for (int pendingAccountsIndex=0; pendingAccountsIndex<getPendingAccountsDataVectorFiltered().size(); pendingAccountsIndex++)
			if ((Boolean) getTblPendingAccounts().getValueAt(pendingAccountsIndex, 0))
				return true;
		
		return false;
	}
	
	private void updateApplyingDocuments() {
		for (int i=0; i<getSelectedWalletDetailDataVector().size(); i++) {
			WalletDetailData selectedWalletDetailData = getSelectedWalletDetailDataVector().get(i);
			for (int j=0; j<getApplyingDocumentsVector().size(); j++) {
				WalletDetailData applyingDocument = getApplyingDocumentsVector().get(j);
				if (selectedWalletDetailData.getSequentialNumber() == applyingDocument.getSequentialNumber()) {
					selectedWalletDetailData.setBalance(applyingDocument.getBalance());
					break;
				}
			}
		}
	}

	private void filteringPendingAccountsDataVector() {
		cleanPendingAccountsDataVectorFiltered();
		cleanTable(getTblPendingAccounts());
		for (int i=0; i<getPendingAccountsDataVector().size(); i++) {
			PendingAccountData pendingAccountData = getPendingAccountsDataVector().get(i);
			if (isFiltered(pendingAccountData)) {
				getPendingAccountsDataVectorFiltered().add(pendingAccountData);
				addRowToTblPendingAccounts(pendingAccountData);
			}
		}
	}
	
	private boolean isFiltered(PendingAccountData pendingAccountData) {
		if (getCmbTransaction().getSelectedItem() != null) {
			DocumentoIf documentFilter = (!getCmbTransaction().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))?(DocumentoIf) getCmbTransaction().getSelectedItem():(DocumentoIf) SpiritConstants.getNullValue();
			if (documentFilter != SpiritConstants.getNullValue() && pendingAccountData.getDocument().getId().compareTo(documentFilter.getId()) != 0)
				return false;
		}
		java.sql.Date emissionDate = Utilitarios.fromTimestampToSqlDate(pendingAccountData.getPendingAccountWallet().getFechaEmision());
		java.sql.Date initialDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtInitialDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, initialDateFilter) < 0)
			return false;
		java.sql.Date finalDateFilter = Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtFinalDate().getValue());
		if (Utilitarios.compararFechas(emissionDate, finalDateFilter) > 0)
			return false;
		if (getCmbOverdueDays().getSelectedItem() != null) {
			Long overdueDays = (Long) Utilitarios.obtenerDiferenciaDias(Utilitarios.fromTimestampToSqlDate(pendingAccountData.getPendingAccountWallet().getFechaEmision()), Utilitarios.fromUtilDateToSqlDate(getWalletData().getEmissionDate()));
			Long overdueDaysFilter = null;
			if (!getCmbOverdueDays().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))
				overdueDaysFilter = Long.parseLong(getCmbOverdueDays().getSelectedItem().toString().split(SpiritConstants.getGreaterThanCharacter())[1]);
			if (overdueDaysFilter != SpiritConstants.getNullValue() && overdueDays <= overdueDaysFilter)
				return false;
		}
		BigDecimal value = pendingAccountData.getPendingAccountWalletDetail().getValor();
		if (value.compareTo(BigDecimal.valueOf(Double.parseDouble(getTxtValueGreaterThan().getValue().toString()))) < 0)
			return false;
		BigDecimal balance = pendingAccountData.getPendingAccountWalletDetail().getSaldo(); 
		if (balance.compareTo(BigDecimal.valueOf(Double.parseDouble(getTxtBalanceGreaterThan().getValue().toString()))) < 0)
			return false;
		return true;
	}

	private void cleanPendingAccountsDataVectorFiltered() {
		setPendingAccountsDataVectorFiltered(new Vector<PendingAccountData>());
		getPendingAccountsDataVectorFiltered().clear();
	}

	private void cleanTable(JTable jTable) {
		if (!(jTable.getModel() instanceof ApplyingDocumentsTableModel)) {
			DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
			int rows = jTable.getRowCount();
			for(int j=rows;j>0;--j)
				dtm.removeRow(j-1);
		} else {
			ApplyingDocumentsTableModel dtm = (ApplyingDocumentsTableModel) jTable.getModel();
			int rows = dtm.getRowCount();
			for(int j=rows;j>0;--j)
				dtm.removeWidgetAt(j-1);
		}
	}
	
	private void acceptChanges() {
		applyChanges();
		for (int i=0; i<getCrossingWalletDetailsVector().size(); i++) {
			CrossingWalletDetailData crossingWalletDetailData = getCrossingWalletDetailsVector().get(i);
			crossingWalletDetailData.setFresh(false);
			getCrossingWalletDetailsVector().set(i, crossingWalletDetailData);
		}
		setVisible(false);
		dispose();
	}
	
	private void reverse() throws Exception {
		Vector<CrossingWalletDetailData> crossingDetailsVector = loadCrossingDetailList();
		for (int i=0; i<crossingDetailsVector.size(); i++) {
			CrossingWalletDetailData crossingWalletDetailData = crossingDetailsVector.get(i);
			setApplyingDocumentsVector(SessionServiceLocator.getCarteraSessionService().reverseCrossingDocuments(getWalletData(), crossingWalletDetailData, getApplyingDocumentsVector()));
		}
		for (int i=0; i<getApplyingDocumentsVector().size();   i++) {
			WalletDetailData applyingDocument = getApplyingDocumentsVector().get(i);
			if (applyingDocument.getWalletDetailId() == SpiritConstants.getNullValue()) {
				applyingDocument.setBalance(applyingDocument.getValue());
				getApplyingDocumentsVector().set(i, applyingDocument);
			}
		}
		Iterator<CrossingWalletDetailData> it = getCrossingWalletDetailsVector().iterator();
		while (it.hasNext()) {
			CrossingWalletDetailData crossingWalletDetailData = it.next();
			if (crossingWalletDetailData.getWalletDetailData().getWalletDetailId() == null)
				it.remove();
		}
		//TODO: Refresh tables
		getPendingAccountsDataVector().clear();
		getPendingAccountsDataVectorFiltered().clear();
		cleanTable(getTblPendingAccounts());
		initTextFields();
		initTblPendingAccounts();
		cleanTable(getTblApplyingDocuments());
		initTblApplyingDocuments();
		filteringPendingAccountsDataVector();
	}
	
	/*private void undo() {
		HashMap<Long, BigDecimal> undoMap = new HashMap<Long, BigDecimal>();
		Iterator<CrossingWalletDetailData> it = getCrossingWalletDetailsVector().iterator();
		while (it.hasNext()) {
			CrossingWalletDetailData crossingWalletDetailData = it.next();
			if (crossingWalletDetailData.getWalletDetailData().getWalletDetailId() != SpiritConstants.getNullValue()) {
				Long walletDetailId = crossingWalletDetailData.getWalletDetailData().getWalletDetailId();
				BigDecimal undoValue = BigDecimal.ZERO;
				if (undoMap.get(walletDetailId) != SpiritConstants.getNullValue()) {
					undoValue = undoValue.add(undoMap.get(walletDetailId));
					undoMap.put(walletDetailId, undoValue);
				}
				undoMap.put(walletDetailId, undoValue.add(crossingWalletDetailData.getValueToApply()));
			}
			it.remove();
		}
		for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
			WalletDetailData applyingDocument = getApplyingDocumentsVector().get(i);
			//if (applyingDocument.getWalletDetailId() == SpiritConstants.getNullValue()) {
			//applyingDocument.setBalance(applyingDocument.getValue());
			BigDecimal undoValue = BigDecimal.ZERO;
			if (undoMap.get(applyingDocument.getWalletDetailId()) != SpiritConstants.getNullValue())
				undoValue = undoMap.get(applyingDocument.getWalletDetailId());
			applyingDocument.setBalance(applyingDocument.getBalance().add(undoValue));
			getApplyingDocumentsVector().set(i, applyingDocument);
			//}
		}
		//TODO: Refresh tables
		getPendingAccountsDataVector().clear();
		getPendingAccountsDataVectorFiltered().clear();
		cleanTable(getTblPendingAccounts());
		initTextFields();
		initTblPendingAccounts();
		cleanTable(getTblApplyingDocuments());
		initTblApplyingDocuments();
		filteringPendingAccountsDataVector();
	}*/
	
	private void undo(boolean isCancelAction) {
		HashMap<Integer, BigDecimal> undoMap = new HashMap<Integer, BigDecimal>();
		Iterator<CrossingWalletDetailData> it = getCrossingWalletDetailsVector().iterator();
		while (it.hasNext()) {
			CrossingWalletDetailData crossingWalletDetailData = it.next();
			if (isSelectedCrossingWalletDetailDataForUndo(crossingWalletDetailData.getWalletDetailData().getSequentialNumber())) {
				if (!isCancelAction || (isCancelAction && crossingWalletDetailData.isFresh())) {
					int walletDetailSequentialNumber = crossingWalletDetailData.getWalletDetailData().getSequentialNumber();
					BigDecimal undoValue = BigDecimal.ZERO;
					if (undoMap.get(walletDetailSequentialNumber) != SpiritConstants.getNullValue()) {
						undoValue = undoValue.add(undoMap.get(walletDetailSequentialNumber));
						undoMap.put(walletDetailSequentialNumber, undoValue);
					}
					undoMap.put(walletDetailSequentialNumber, undoValue.add(crossingWalletDetailData.getValueToApply()));
					it.remove();
				}
			}
		}
		if (undoMap.size() > 0) {
			for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
				WalletDetailData applyingDocument = getApplyingDocumentsVector().get(i);
				BigDecimal undoValue = BigDecimal.ZERO;
				undoValue = undoMap.get(applyingDocument.getSequentialNumber());
				if (undoValue != null) {
					applyingDocument.setBalance(applyingDocument.getBalance().add(undoValue));
					applyingDocument.setDateToApply(getWalletData().getEmissionDate());
					java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
					calendar.setTimeInMillis(getWalletData().getEmissionDate().getTime());
					getTblApplyingDocuments().setValueAt(calendar, i, getTblApplyingDocumentsDateToApplyColumnIndex());
					setDateToApply(getWalletData().getEmissionDate());
					getTblApplyingDocuments().validate();
					getTblApplyingDocuments().repaint();
				}
				getApplyingDocumentsVector().set(i, applyingDocument);
			}
		}
		getPendingAccountsDataVector().clear();
		getPendingAccountsDataVectorFiltered().clear();
		cleanTable(getTblPendingAccounts());
		initTextFields();
		initTblPendingAccounts();
		cleanTable(getTblApplyingDocuments());
		initTblApplyingDocuments();
		filteringPendingAccountsDataVector();
	}
	
	private boolean isSelectedCrossingWalletDetailDataForUndo(int sequentialNumber) {
		for (int i=0; i<getSelectedWalletDetailDataVector().size(); i++) {
			if (getSelectedWalletDetailDataVector().get(i).getSequentialNumber() == sequentialNumber)
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private Vector<CrossingWalletDetailData> loadCrossingDetailList() throws GenericBusinessException {
		Vector<CrossingWalletDetailData> crossingDetailsVector = new Vector<CrossingWalletDetailData>();
		for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
			WalletDetailData walletDetailData = getApplyingDocumentsVector().get(i);
			List<Object[]> pendingAccountDataList = (ArrayList<Object[]>) SessionServiceLocator.getCarteraSessionService().findPendingAccountDetailDataByWalletDetailId(walletDetailData.getWalletDetailId());
			Iterator<Object[]> it = pendingAccountDataList.iterator();
			while (it.hasNext()) {
				Object[] pendingAccountDataObject = (Object[]) it.next();
				CarteraIf pendingAccountWallet = (CarteraIf) pendingAccountDataObject[0];
				CarteraDetalleIf pendingAccountWalletDetail = (CarteraDetalleIf) pendingAccountDataObject[1];
				CarteraAfectaIf crossingDetail = (CarteraAfectaIf) pendingAccountDataObject[2];
				TipoDocumentoIf documentType = (TipoDocumentoIf) pendingAccountDataObject[3];
				DocumentoIf document = (DocumentoIf) pendingAccountDataObject[4];
				CrossingWalletDetailData crossingWalletDetailData = new CrossingWalletDetailData();
				crossingWalletDetailData.setCrossingWalletDetailId(crossingDetail.getId());
				crossingWalletDetailData.setWalletDetailData(walletDetailData);
				PendingAccountData pendingAccountDetailData = new PendingAccountData();
				pendingAccountDetailData.setPendingAccountWallet(pendingAccountWallet);
				pendingAccountDetailData.setPendingAccountWalletDetail(pendingAccountWalletDetail);
				pendingAccountDetailData.setBusinessOperator(SessionServiceLocator.getClienteSessionService().getCliente(getWalletData().getBusinessOperatorOffice().getClienteId()));
				pendingAccountDetailData.setDocumentType(documentType);
				pendingAccountDetailData.setDocument(document);
				crossingWalletDetailData.setPendingAccountDetailData(pendingAccountDetailData);
				crossingWalletDetailData.setApplyingDate(Utilitarios.fromSqlDateToUtilDate(crossingDetail.getFechaAplicacion()));
				crossingWalletDetailData.setValueToApply(crossingDetail.getValor());
				crossingDetailsVector.add(crossingWalletDetailData);
			}
		}
		return crossingDetailsVector;
	}
	
	private void applyChanges() {
		String informationMessage = SpiritConstants.getEmptyCharacter();
		String errorMessage = SpiritConstants.getEmptyCharacter();
		setPendingSaveChanges(true);
		setTitle(getDialogTitle() + SpiritConstants.getBlankSpaceCharacter() + SpiritConstants.getPendingSaveChangesCharacter() + SpiritConstants.getBlankSpaceCharacter() + "(Sin guardar)");
		for (int applyDocumentsIndex=0; applyDocumentsIndex<getApplyingDocumentsVector().size(); applyDocumentsIndex++) {
			WalletDetailData applyDetailData = getApplyingDocumentsVector().get(applyDocumentsIndex);
			boolean anyCrossingRelationshipExists = false;
			boolean anySelectedPendingAccountExists = false;
			for (int pendingAccountsIndex=0; pendingAccountsIndex<getPendingAccountsDataVectorFiltered().size(); pendingAccountsIndex++) {
				if ((Boolean) getTblPendingAccounts().getValueAt(pendingAccountsIndex, getTblPendingAccountsSelectionColumnIndex())) {
					anySelectedPendingAccountExists = true;
					PendingAccountData pendingAccountData = getPendingAccountsDataVectorFiltered().get(getTblPendingAccounts().convertRowIndexToModel(pendingAccountsIndex));
					DocumentoIf document = pendingAccountData.getDocument();
					if (crossingDocumentsExists(applyDetailData.getDocument(), document)) {
						anyCrossingRelationshipExists = true;
						if (applyDetailData.getBalance().doubleValue() > 0D) {
							if (pendingAccountData.getPendingAccountWalletDetail().getSaldo().doubleValue() > 0D) {
								//double balanceToApply = Utilitarios.fromStringToDouble(getTblApplyingDocuments().getValueAt(applyDocumentsIndex, getTblApplyingDocumentsValueToApplyColumnIndex()).toString());
								double balanceToApply = ((Double) getTblApplyingDocuments().getModel().getValueAt(applyDocumentsIndex, getTblApplyingDocumentsValueToApplyColumnIndex())).doubleValue();
								if (balanceToApply <= applyDetailData.getBalance().doubleValue()) {
									Map<String, Object> result = applyCrossingDocument(applyDocumentsIndex, applyDetailData, pendingAccountData);
									getApplyingDocumentsVector().set(applyDocumentsIndex, (WalletDetailData) result.get("applyDetailData"));
									getPendingAccountsDataVectorFiltered().set(getTblPendingAccounts().convertRowIndexToModel(pendingAccountsIndex), (PendingAccountData) result.get("pendingAccountData"));
									getCrossingWalletDetailsVector().add((CrossingWalletDetailData) result.get("crossingWalletDetailData"));
									//ESTO HAY QUE ARREGLAR
									balanceToApply -= ((CrossingWalletDetailData) result.get("crossingWalletDetailData")).getValueToApply().doubleValue();
									//getTblApplyingDocuments().setValueAt(SpiritConstants.getDecimalFormatPattern().format(balanceToApply), applyDocumentsIndex, getTblApplyingDocumentsValueToApplyColumnIndex());
									getTblApplyingDocuments().getModel().setValueAt(Double.valueOf(balanceToApply), applyDocumentsIndex, getTblApplyingDocumentsValueToApplyColumnIndex());
									//refreshPendingAccountsDataVector((PendingAccountData) result.get("pendingAccountData"));
								} else
									errorMessage += applyDetailData.getComment() + "\n";
							}
						}
					}
				}
			}
			if (!anyCrossingRelationshipExists && anySelectedPendingAccountExists)
				informationMessage += applyDetailData.getDocument().getNombre() + "\n";
		}
		
		if (!informationMessage.equals(SpiritConstants.getEmptyCharacter())) {
			informationMessage = "Uno o más cruces seleccionados no se han aplicado\nNo se han definido adecuadamente las relaciones de cruce de documentos para:\n\n" + informationMessage;
			SpiritAlert.createAlert(informationMessage, SpiritAlert.WARNING);
		}
		if (!errorMessage.equals(SpiritConstants.getEmptyCharacter())) {
			errorMessage = "Valor a aplicar supera al saldo disponible para los cruces siguientes:\n\n" + errorMessage;
			SpiritAlert.createAlert(errorMessage, SpiritAlert.WARNING);
		}
		//TODO: Refresh tables
		cleanTable(getTblApplyingDocuments());
		//loadTblApplyingDocumentsData();
		/*ApplyingDocumentsTableModel adtm = new ApplyingDocumentsTableModel(loadTblApplyingDocumentsData());
		getTblApplyingDocuments().setModel(adtm);*/
		initTblApplyingDocuments();
		filteringPendingAccountsDataVector();
	}
	
	private void refreshPendingAccountsDataVector(PendingAccountData filteredPendingAccountData) {
		for (int pendingAccountsDataIndex=0; pendingAccountsDataIndex<getPendingAccountsDataVector().size(); pendingAccountsDataIndex++) {
			PendingAccountData pendingAccountData = pendingAccountsDataVector.get(pendingAccountsDataIndex);
			if (pendingAccountData.getPendingAccountWalletDetail().getId().compareTo(filteredPendingAccountData.getPendingAccountWalletDetail().getId()) == 0) {
				getPendingAccountsDataVector().set(pendingAccountsDataIndex, filteredPendingAccountData);
				break;
			}
		}
	}
	
	private boolean crossingDocumentsExists(DocumentoIf applyDocument, DocumentoIf pendingAccountDocument) {
		Vector<CruceDocumentoIf> crossingDocumentVector = getCrossingDocumentsMap().get(applyDocument.getId());
		for (int i=0; i<crossingDocumentVector.size(); i++) {
			CruceDocumentoIf crossingDocument = crossingDocumentVector.get(i);
			if (crossingDocument.getDocumentoaplId().compareTo(pendingAccountDocument.getId()) == 0)
				return true;
		}
		return false;
	}
	
	private Map<String, Object> applyCrossingDocument(int applyingDocumentIndex, WalletDetailData applyDetailData, PendingAccountData pendingAccountData) {
		CarteraIf pendingAccountWallet = pendingAccountData.getPendingAccountWallet();
		CarteraDetalleIf pendingAccountWalletDetail = pendingAccountData.getPendingAccountWalletDetail();
		double balanceWalletToAffect = pendingAccountWallet.getSaldo().doubleValue() - pendingAccountData.getApprovedPayment().doubleValue();
		double balanceWalletDetailToAffect = pendingAccountWalletDetail.getSaldo().doubleValue() - pendingAccountData.getApprovedPayment().doubleValue();
		double valueToApply = 0D;
		//double balanceToApply = applyDetailData.getBalance().doubleValue();
		double balanceToApply = Utilitarios.fromStringToDouble(getTblApplyingDocuments().getValueAt(applyingDocumentIndex, getTblApplyingDocumentsValueToApplyColumnIndex()).toString());
		if (balanceToApply > balanceWalletDetailToAffect) {
			valueToApply = balanceWalletDetailToAffect;
			balanceToApply = balanceToApply - balanceWalletDetailToAffect;
			balanceWalletToAffect = 0D;
			balanceWalletDetailToAffect = 0D;
		} else {
			valueToApply = balanceToApply;
			balanceWalletToAffect = balanceWalletToAffect - balanceToApply;
			balanceWalletDetailToAffect = balanceWalletDetailToAffect - balanceToApply;
			//balanceToApply = 0D;
			balanceToApply = applyDetailData.getBalance().doubleValue() - valueToApply;
		}
		//applyDetailData.setBalance(BigDecimal.valueOf(balanceToApply));
		//AGREGADO
		double balance = Utilitarios.redondeoValor(applyDetailData.getBalance().doubleValue() - valueToApply);
		applyDetailData.setBalance(BigDecimal.valueOf(balance));
		//
		pendingAccountWallet.setSaldo(BigDecimal.valueOf(balanceWalletToAffect));
		pendingAccountWalletDetail.setSaldo(BigDecimal.valueOf(balanceWalletDetailToAffect));
		pendingAccountData.setPendingAccountWallet(pendingAccountWallet);
		pendingAccountData.setPendingAccountWalletDetail(pendingAccountWalletDetail);
		CrossingWalletDetailData crossingWalletDetailData = new CrossingWalletDetailData();
		crossingWalletDetailData.setWalletDetailData(applyDetailData);
		crossingWalletDetailData.setPendingAccountDetailData(pendingAccountData);
		//crossingWalletDetailData.setApplyingDate(getWalletData().getEmissionDate());
		crossingWalletDetailData.setApplyingDate(applyDetailData.getDateToApply());
		crossingWalletDetailData.setValueToApply(BigDecimal.valueOf(valueToApply));
		crossingWalletDetailData.setFresh(true);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("applyDetailData", applyDetailData);
		result.put("pendingAccountData", pendingAccountData);
		result.put("crossingWalletDetailData", crossingWalletDetailData);
		return result;
	}
	
	private void initComboBoxes() {
		getCmbTransaction().setSelectedIndex(0);
		getCmbTransaction().validate();
		getCmbTransaction().repaint();
		getCmbOverdueDays().setSelectedIndex(0);
		getCmbOverdueDays().validate();
		getCmbOverdueDays().repaint();
	}
	
	private void initJpPendingAccountsButtons() {
		getBtnRemoveFilters().setToolTipText("Remover filtros");
		getBtnRemoveFilters().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/removeFilters.png"));
		getBtnFilterList().setToolTipText("Filtrar listado de cuentas pendientes");
		getBtnFilterList().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/filterElements.png"));
	}
	
	private void initTextFields() {
		java.util.Date now = new java.util.Date();
		Utilitarios.setDateFormatter(getTxtInitialDate(), SpiritConstants.getDateFormatPattern());
		getTxtInitialDate().setValue(now);
		getTxtInitialDate().setName("txtInitialDate");
		Utilitarios.setDateFormatter(getTxtFinalDate(), SpiritConstants.getDateFormatPattern());
		getTxtFinalDate().setValue(now);
		getTxtFinalDate().setName("txtFinalDate");
		Utilitarios.setMoneyFormatter(getTxtValueGreaterThan());
		getTxtValueGreaterThan().setValue(SpiritConstants.getZeroValue());
		Utilitarios.setMoneyFormatter(getTxtBalanceGreaterThan());
		getTxtBalanceGreaterThan().setValue(SpiritConstants.getZeroValue());
	}
	
	private void initTblPendingAccounts() {
		setTblPendingAccountsWidthColumns();
		setTblPendingAccountsAlignment();
		setSorterTable(getTblPendingAccounts());
		loadTblPendingAccountsData();
		getTblPendingAccounts().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblPendingAccounts().getTableHeader().setReorderingAllowed(false);
	}
	
	private void setTblPendingAccountsWidthColumns() {
		TableColumn columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsSelectionColumnIndex());
		columnWidth.setPreferredWidth(20);
		columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsTransactionColumnIndex());
		columnWidth.setPreferredWidth(150);
		columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsDateColumnIndex());
		columnWidth.setPreferredWidth(50);
		columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsOverdueDaysColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsValueColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsBalanceColumnIndex());
		columnWidth.setPreferredWidth(30);
	}

	private void setTblPendingAccountsAlignment() {
		TableCellRendererHorizontalCenterAlignment horizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsDateColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsOverdueDaysColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsValueColumnIndex()).setCellRenderer(horizontalRightAlignment);
		getTblPendingAccounts().getColumnModel().getColumn(getTblPendingAccountsBalanceColumnIndex()).setCellRenderer(horizontalRightAlignment);
	}
	
	private void setSorterTable(JTable table) {
		RowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tableRowSorter);
	}
	
	@SuppressWarnings("unchecked")
	private void loadTblPendingAccountsData() {
		try {
			Iterator<Object[]> it = SessionServiceLocator.getCarteraDetalleSessionService().findPendingAccountsByWalletTypeByBusinessOperatorOfficeAndWalletSign(getWalletData().getWalletType(), getWalletData().getBusinessOperatorOffice(), SpiritConstants.getPlusOperator()).iterator();
			Map<Long, DocumentoIf> documentsMap = new HashMap<Long, DocumentoIf>();
			while (it.hasNext()) {
				Object[] pendingAccount = it.next();
				PendingAccountData pendingAccountData = addPendingAccountData(pendingAccount);
				if (pendingAccountData.getPendingAccountWallet() != SpiritConstants.getNullValue()) {
					BigDecimal pendingAccountWalletBalance = calculatePendingAccountBalance(pendingAccountData, false);
					pendingAccountData.getPendingAccountWallet().setSaldo(pendingAccountWalletBalance);
					BigDecimal pendingAccountWalletDetailBalance = calculatePendingAccountBalance(pendingAccountData, true);
					pendingAccountData.getPendingAccountWalletDetail().setSaldo(pendingAccountWalletDetailBalance);
					addRowToTblPendingAccounts(pendingAccountData);
					documentsMap.put(pendingAccountData.getDocument().getId(), pendingAccountData.getDocument());
					java.util.Date filterInitialDate = (java.util.Date) getTxtInitialDate().getValue();
					if (Utilitarios.compararFechas(Utilitarios.fromTimestampToSqlDate(pendingAccountData.getPendingAccountWallet().getFechaEmision()), Utilitarios.fromUtilDateToSqlDate(filterInitialDate)) < 0) {
						getTxtInitialDate().setValue(Utilitarios.fromTimestampToUtilDate(pendingAccountData.getPendingAccountWallet().getFechaEmision()));
						// Storing initial date value (Necessary for remove filters operation)
						setInitialDateFilter(Utilitarios.fromTimestampToUtilDate(pendingAccountData.getPendingAccountWallet().getFechaEmision()));
					}
				}
			}
			loadComboBoxTransaction(documentsMap);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de cuentas pendientes", SpiritAlert.ERROR);
		}
	}
	
	private BigDecimal calculatePendingAccountBalance(PendingAccountData pendingAccountData, boolean forDetail) {
		double balance = (forDetail)?pendingAccountData.getPendingAccountWalletDetail().getSaldo().doubleValue():pendingAccountData.getPendingAccountWallet().getSaldo().doubleValue();
		for (int i=0; i<getCrossingWalletDetailsVector().size(); i++) {
			CrossingWalletDetailData crossingWalletDetailData = getCrossingWalletDetailsVector().get(i);
			if (pendingAccountData.getPendingAccountWalletDetail().getId().compareTo(crossingWalletDetailData.getPendingAccountDetailData().getPendingAccountWalletDetail().getId()) == 0) {
				double valueToApply = crossingWalletDetailData.getValueToApply().doubleValue();
				balance -= valueToApply;
			}
		}
		return BigDecimal.valueOf(balance);
	}
	
	private void loadComboBoxTransaction(Map<Long, DocumentoIf> documentsMap) {
		List<DocumentoIf> documentsList = new ArrayList<DocumentoIf>();
		Iterator<Long> keySetIterator = documentsMap.keySet().iterator();
		while (keySetIterator.hasNext()) {
			Long key = keySetIterator.next();
			DocumentoIf document = documentsMap.get(key);
			documentsList.add(document);
		}
		Collections.sort((ArrayList<DocumentoIf>) documentsList, getDocumentArrayListByNameSorter());
		List<Object> sortedList = new ArrayList<Object>();
		sortedList.add(0, SpiritConstants.getPlaceholderCharacter());
		sortedList.addAll(documentsList);
		refreshCombo(getCmbTransaction(), sortedList);
	}
	
	private PendingAccountData addPendingAccountData(Object[] pendingAccount) throws GenericBusinessException {
		CarteraIf pendingAccountWallet = (CarteraIf) pendingAccount[0];
		CarteraDetalleIf pendingAccountWalletDetail = (CarteraDetalleIf) pendingAccount[1];
		ClienteIf businessOperator = (ClienteIf) pendingAccount[2];
		TipoDocumentoIf documentType = (TipoDocumentoIf) pendingAccount[3];
		DocumentoIf document = (DocumentoIf) pendingAccount[4];
		PendingAccountData pendingAccountData = new PendingAccountData();
		if (!pendingAccountWallet.getEstado().equals(WalletConstants.getNullifyStatus().substring(0,1))) {
			pendingAccountData.setPendingAccountWallet(pendingAccountWallet);
			pendingAccountData.setPendingAccountWalletDetail(pendingAccountWalletDetail);
			pendingAccountData.setBusinessOperator(businessOperator);
			pendingAccountData.setDocumentType(documentType);
			pendingAccountData.setDocument(document);
			pendingAccountData.setApprovedPayment(getPendingAccountApprovedPaymentsTotal(pendingAccountWallet));
			getPendingAccountsDataVector().add(pendingAccountData);
			getPendingAccountsDataVectorFiltered().add(pendingAccountData);
		}
		return pendingAccountData;
	}
	
	private BigDecimal getPendingAccountApprovedPaymentsTotal(CarteraIf pendingAccountWallet) throws GenericBusinessException {
		BigDecimal pendingAccountApprovedPaymentsTotal = SpiritConstants.getZeroValue();
		Iterator<CarteraDetalleIf> it = SessionServiceLocator.getCarteraSessionService().findPendingAccountApprovedPayments(pendingAccountWallet).iterator();
		if (it.hasNext()) {
			BigDecimal approvedPayment = it.next().getValor();
			pendingAccountApprovedPaymentsTotal.add(approvedPayment);
		}
		return pendingAccountApprovedPaymentsTotal;
	}
	
	public class MyDateCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
			if ( value instanceof Date ){
				String strDate = Utilitarios.setFechaCortaUppercase().format((Date)value);
				this.setText( strDate );
			}
			return this;
		}
	}
	
	private void initTblApplyingDocuments() {
		ApplyingDocumentsTableModel adtm = new ApplyingDocumentsTableModel(loadTblApplyingDocumentsData());
		getTblApplyingDocuments().setModel(adtm);
		TableColumnModel _model = getTblApplyingDocuments().getColumnModel();
		NumberFormat _formatValue = NumberFormat.getInstance();
		MyCurrencyRenderer _rendererValue = new MyCurrencyRenderer(_formatValue);
		TableColumn _columnValue = _model.getColumn(getTblApplyingDocumentsValueToApplyColumnIndex());
		_columnValue.setCellRenderer(_rendererValue);
		NumberFormatter _formatter = new NumberFormatter(_formatValue);
		JFormattedTextField _fieldValue = new JFormattedTextField(_formatter);
		MyCurrencyEditor _editorValue = new MyCurrencyEditor(_fieldValue, _formatValue);
		_columnValue.setCellEditor(_editorValue);
		TableColumn _columnDate = _model.getColumn(getTblApplyingDocumentsDateToApplyColumnIndex());
		DateComboBox _cmbDate = new DateComboBox();
		_cmbDate.setDate(Utilitarios.calendarHoy().getTime());
		_cmbDate.setEditable(false);
		DateFormat _formatDate = Utilitarios.setFechaCortaUppercase();
		_cmbDate.setFormat(_formatDate);
		DateCellEditor _editorDate = new DateCellEditor(_cmbDate.getDateModel());
		_columnDate.setCellEditor(_editorDate);
		MyDateCellRenderer _rendererDate = new MyDateCellRenderer();
		_rendererDate.setHorizontalAlignment(SwingConstants.CENTER);
		_columnDate.setCellRenderer(_rendererDate);
		_editorDate.addCellEditorListener(new CellEditorListener() {
			public void editingStopped(ChangeEvent e) {
				java.util.Date dateToApply;
				try {
					dateToApply = new java.util.Date(((DateComboBox) ((DateCellEditor) e.getSource()).getComboBox()).getDate().getTime());
				} catch (java.lang.NullPointerException ex1) {
					dateToApply = walletData.getEmissionDate();
				} catch (java.lang.ClassCastException ex2) {
					dateToApply = walletData.getEmissionDate();
				}
				for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
					getApplyingDocumentsVector().get(i).setDateToApply(dateToApply);
					java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
					calendar.setTimeInMillis(dateToApply.getTime());
					getTblApplyingDocuments().setValueAt(calendar, i, getTblApplyingDocumentsDateToApplyColumnIndex());
				}
				setDateToApply(dateToApply);
				getTblApplyingDocuments().validate();
				getTblApplyingDocuments().repaint();
			}			
			
			public void editingCanceled(ChangeEvent e) {
				java.util.Date dateToApply = new java.util.Date(((DateComboBox) ((DateCellEditor) e.getSource()).getComboBox()).getDate().getTime());
				for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
					getApplyingDocumentsVector().get(i).setDateToApply(dateToApply);
					java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
					calendar.setTimeInMillis(dateToApply.getTime());
					getTblApplyingDocuments().setValueAt(calendar, i, getTblApplyingDocumentsDateToApplyColumnIndex());
				}
				setDateToApply(dateToApply);
				getTblApplyingDocuments().validate();
				getTblApplyingDocuments().repaint();
			}
		});
		setTblApplyingDocumentsWidthColumns();
		setTblApplyingDocumentsAlignment();
		getTblApplyingDocuments().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblApplyingDocuments().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		getTblApplyingDocuments().getTableHeader().setReorderingAllowed(false);
		getTblApplyingDocuments().setName("tblApplyingDocuments");
		/*getTblApplyingDocuments().validate();
		getTblApplyingDocuments().repaint();*/
	}
	
	private void setTblApplyingDocumentsWidthColumns() {
		TableColumn columnWidth = getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsTransactionColumnIndex());
		columnWidth.setPreferredWidth(150);
		columnWidth = getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsValueToApplyColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsBalanceColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsDateToApplyColumnIndex());
		columnWidth.setPreferredWidth(30);
	}

	private void setTblApplyingDocumentsAlignment() {
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsValueToApplyColumnIndex()).setCellRenderer(horizontalRightAlignment);
		getTblApplyingDocuments().getColumnModel().getColumn(getTblApplyingDocumentsBalanceColumnIndex()).setCellRenderer(horizontalRightAlignment);
	}

	private ArrayList loadTblApplyingDocumentsData() {
		ArrayList dataList = new ArrayList();
		for (int i=0; i<getApplyingDocumentsVector().size(); i++) {
			WalletDetailData walletDetailData = getApplyingDocumentsVector().get(i);
			//addRowToTblApplyingDocuments(walletDetailData);
			walletDetailData.setDateToApply(getDateToApply());
			dataList.add(getWidgetToTblApplyingDocumentsDataList(walletDetailData));
			getApplyDocumentsMap().put(walletDetailData.getDocument().getId(), walletDetailData.getDocument());
		}
		return dataList;
	}
	
	private void addRowToTblPendingAccounts(PendingAccountData pendingAccountData) {
		DefaultTableModel dtm = (DefaultTableModel) getTblPendingAccounts().getModel();
		Vector<Object> dataRow = new Vector<Object>();
		CarteraIf pendingAccountWallet = pendingAccountData.getPendingAccountWallet();
		CarteraDetalleIf pendingAccountWalletDetail = pendingAccountData.getPendingAccountWalletDetail();
		ClienteIf businessOperator = pendingAccountData.getBusinessOperator();
		TipoDocumentoIf documentType = pendingAccountData.getDocumentType();
		DocumentoIf document = pendingAccountData.getDocument();
		dataRow.add(new Boolean(false));
		String transactionDocumentType = (documentType.getAbreviatura().equals(SpiritConstants.getEmptyCharacter()) || documentType.getAbreviatura() == null)?SpiritConstants.getEmptyCharacter():documentType.getAbreviatura();
		String transactionNumber = (pendingAccountWallet.getPreimpreso() == null || pendingAccountWallet.getPreimpreso().equals(SpiritConstants.getEmptyCharacter()))?SpiritConstants.getEmptyCharacter():pendingAccountWallet.getPreimpreso();
		String comment = (pendingAccountWallet.getComentario().equals(SpiritConstants.getEmptyCharacter()) || pendingAccountWallet.getComentario() == null)?document.getNombre():pendingAccountWallet.getComentario();
		String transaction = SpiritConstants.getEmptyCharacter();
		if (!transactionDocumentType.equals(SpiritConstants.getEmptyCharacter()))
			transaction += transactionDocumentType + SpiritConstants.getColonCharacter();
		if (!transactionNumber.equals(SpiritConstants.getEmptyCharacter()))
			transaction += transactionNumber + SpiritConstants.getBlankSpaceCharacter();
		if (!comment.equals(SpiritConstants.getEmptyCharacter()))
			transaction += comment;
		dataRow.add(transaction);
		dataRow.add(Utilitarios.getFechaCortaUppercase(pendingAccountWallet.getFechaEmision()));
		long overdueDays = Utilitarios.obtenerDiferenciaDias(Utilitarios.fromTimestampToSqlDate(pendingAccountWallet.getFechaEmision()), Utilitarios.fromUtilDateToSqlDate(getWalletData().getEmissionDate()));
		dataRow.add(String.valueOf(overdueDays));
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(pendingAccountWalletDetail.getValor()));
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(pendingAccountWalletDetail.getSaldo()));
		dtm.addRow(dataRow);
	}
	
	/*private void addRowToTblApplyingDocuments(WalletDetailData walletDetailData) {
		DefaultTableModel dtm = (DefaultTableModel) getTblApplyingDocuments().getModel();
		Vector<Object> dataRow = new Vector<Object>();
		dataRow.add(walletDetailData.getComment());
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()));
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()));
		dtm.addRow(dataRow);
	}*/
	
	private ApplyingDocumentsTableWidget getWidgetToTblApplyingDocumentsDataList(WalletDetailData walletDetailData) {
		/*DefaultTableModel dtm = (DefaultTableModel) getTblApplyingDocuments().getModel();
		Vector<Object> dataRow = new Vector<Object>();
		dataRow.add(walletDetailData.getComment());
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()));
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()));
		dtm.addRow(dataRow);*/
		ApplyingDocumentsTableWidget adtw = new ApplyingDocumentsTableWidget();
		adtw.setTransaction(walletDetailData.getComment());
		adtw.setBalance(Utilitarios.redondeoValor(walletDetailData.getBalance().doubleValue()));
		adtw.setValueToApply(Utilitarios.redondeoValor(walletDetailData.getBalance().doubleValue()));
		adtw.setDateToApply(walletDetailData.getDateToApply());
		return adtw;
		
	}
	
	protected static void refreshCombo(JComboBox combo,List<? extends Object> lista ){
		refreshComboByIndex(combo,lista,0);
	}
	
	protected static void refreshComboByIndex(JComboBox combo,List<? extends Object> lista,int index){
		try {
			SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(lista);
			int itemSeleccionado = combo.getSelectedIndex(); 
			int numeroItems = combo.getItemCount();
			combo.setModel(cmbModel);
			if (combo.getItemCount() > 0){
				if ( itemSeleccionado >= 0 && numeroItems == combo.getItemCount())
					combo.setSelectedIndex(itemSeleccionado);
				else
					combo.setSelectedIndex(index);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al refrescar la pantalla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<DocumentoIf> documentArrayListByNameSorter = new Comparator<DocumentoIf>(){
		public int compare(DocumentoIf o1, DocumentoIf o2) {
			return (o1.getNombre().compareTo(o2.getNombre()));
		}		
	};

	public WalletData getWalletData() {
		return walletData;
	}

	public void setWalletData(WalletData walletData) {
		this.walletData = walletData;
	}

	public int getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(int operationMode) {
		this.operationMode = operationMode;
	}

	public Vector<PendingAccountData> getPendingAccountsDataVector() {
		return pendingAccountsDataVector;
	}

	public void setPendingAccountsDataVector(
			Vector<PendingAccountData> pendingAccountsDataVector) {
		this.pendingAccountsDataVector = pendingAccountsDataVector;
	}

	public Vector<PendingAccountData> getPendingAccountsDataVectorFiltered() {
		return pendingAccountsDataVectorFiltered;
	}

	public void setPendingAccountsDataVectorFiltered(
			Vector<PendingAccountData> pendingAccountsDataVectorFiltered) {
		this.pendingAccountsDataVectorFiltered = pendingAccountsDataVectorFiltered;
	}

	public Vector<WalletDetailData> getApplyingDocumentsVector() {
		return applyingDocumentsVector;
	}

	public Vector<WalletDetailData> getSelectedWalletDetailDataVector() {
		return selectedWalletDetailDataVector;
	}

	public void setSelectedWalletDetailDataVector(
			Vector<WalletDetailData> selectedWalletDetailDataVector) {
		this.selectedWalletDetailDataVector = selectedWalletDetailDataVector;
	}

	public void setApplyingDocumentsVector(
			Vector<WalletDetailData> applyingDocumentsVector) {
		this.applyingDocumentsVector = applyingDocumentsVector;
	}

	public Vector<CrossingWalletDetailData> getCrossingWalletDetailsVector() {
		return crossingWalletDetailsVector;
	}

	public void setCrossingWalletDetailsVector(
			Vector<CrossingWalletDetailData> crossingWalletDetailsVector) {
		this.crossingWalletDetailsVector = crossingWalletDetailsVector;
	}

	public static int getTblPendingAccountsSelectionColumnIndex() {
		return TBL_PENDING_ACCOUNTS_SELECTION_COLUMN_INDEX;
	}

	public static int getTblPendingAccountsTransactionColumnIndex() {
		return TBL_PENDING_ACCOUNTS_TRANSACTION_COLUMN_INDEX;
	}

	public static int getTblPendingAccountsDateColumnIndex() {
		return TBL_PENDING_ACCOUNTS_DATE_COLUMN_INDEX;
	}

	public static int getTblPendingAccountsOverdueDaysColumnIndex() {
		return TBL_PENDING_ACCOUNTS_OVERDUE_DAYS_COLUMN_INDEX;
	}

	public static int getTblPendingAccountsValueColumnIndex() {
		return TBL_PENDING_ACCOUNTS_VALUE_COLUMN_INDEX;
	}

	public static int getTblPendingAccountsBalanceColumnIndex() {
		return TBL_PENDING_ACCOUNTS_BALANCE_COLUMN_INDEX;
	}

	public static int getTblApplyingDocumentsTransactionColumnIndex() {
		return TBL_APPLYING_DOCUMENTS_TRANSACTION_COLUMN_INDEX;
	}

	public static int getTblApplyingDocumentsBalanceColumnIndex() {
		return TBL_APPLYING_DOCUMENTS_BALANCE_COLUMN_INDEX;
	}

	public static int getTblApplyingDocumentsValueToApplyColumnIndex() {
		return TBL_APPLYING_DOCUMENTS_VALUE_TO_APPLY_COLUMN_INDEX;
	}

	public static int getTblApplyingDocumentsDateToApplyColumnIndex() {
		return TBL_APPLYING_DOCUMENTS_DATE_TO_APPLY_COLUMN_INDEX;
	}

	public static int getApplyAction() {
		return applyAction;
	}

	public static void setApplyAction(int applyAction) {
		ApplyWalletTransactionModel.applyAction = applyAction;
	}

	public boolean hasPendingSaveChanges() {
		return pendingSaveChanges;
	}

	public void setPendingSaveChanges(boolean pendingSaveChanges) {
		this.pendingSaveChanges = pendingSaveChanges;
	}

	public Comparator<DocumentoIf> getDocumentArrayListByNameSorter() {
		return documentArrayListByNameSorter;
	}

	public void setDocumentArrayListByNameSorter(
			Comparator<DocumentoIf> documentArrayListByNameSorter) {
		this.documentArrayListByNameSorter = documentArrayListByNameSorter;
	}
	
	public ApplyWalletTransactionModel getThis() {
		return this;
	}

	public java.util.Date getInitialDateFilter() {
		return initialDateFilter;
	}

	public void setInitialDateFilter(java.util.Date initialDateFilter) {
		this.initialDateFilter = initialDateFilter;
	}

	public Map<Long, DocumentoIf> getApplyDocumentsMap() {
		return applyDocumentsMap;
	}

	public void setApplyDocumentsMap(Map<Long, DocumentoIf> applyDocumentsMap) {
		this.applyDocumentsMap = applyDocumentsMap;
	}

	public Map<Long, Vector<CruceDocumentoIf>> getCrossingDocumentsMap() {
		return crossingDocumentsMap;
	}

	public void setCrossingDocumentsMap(
			Map<Long, Vector<CruceDocumentoIf>> crossingDocumentsMap) {
		this.crossingDocumentsMap = crossingDocumentsMap;
	}

	public int getTblApplyingDocumentsSelectedRow() {
		return tblApplyingDocumentsSelectedRow;
	}

	public void setTblApplyingDocumentsSelectedRow(int tblApplyingDocumentsSelectedRow) {
		this.tblApplyingDocumentsSelectedRow = tblApplyingDocumentsSelectedRow;
	}

	public String getDialogTitle() {
		return dialogTitle;
	}

	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	public boolean isTblApplyingDocumentsStopCellEditing() {
		return tblApplyingDocumentsStopCellEditing;
	}

	public void setTblApplyingDocumentsStopCellEditing(
			boolean tblApplyingDocumentsStopCellEditing) {
		this.tblApplyingDocumentsStopCellEditing = tblApplyingDocumentsStopCellEditing;
	}

	public java.util.Date getDateToApply() {
		return dateToApply;
	}

	public void setDateToApply(java.util.Date dateToApply) {
		this.dateToApply = dateToApply;
	}
}