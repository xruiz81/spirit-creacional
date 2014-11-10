package com.spirit.cartera.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.spirit.cartera.gui.controller.WalletConstants;
import com.spirit.cartera.gui.panel.JDLevelingConfirmation;
import com.spirit.cartera.handler.WalletData;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.util.SpiritComboBoxModel;

public class LevelingConfirmationModel extends JDLevelingConfirmation {
	private static final long serialVersionUID = -2423311198303927730L;
	private WalletData walletData;
	private boolean cancelAction;
	private DocumentoIf levelingDocument;
	private DocumentoIf advancePaymentDocument;

	public LevelingConfirmationModel(Frame owner, WalletData walletData) {
		super(owner);
		setWalletData(walletData);
		initDialogComponents();
		initObjects();
		initListeners();
		initJDialogAdapter(this);
		initDialog();
	}

	private void initDialog() {
		this.setSize(WalletConstants.getJdialogPendingBalancesConfirmationWidth(), WalletConstants.getJdialogPendingBalancesConfirmationHeight());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - WalletConstants.getJdialogPendingBalancesConfirmationWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - WalletConstants.getJdialogPendingBalancesConfirmationHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void initDialogComponents() {
		getCmbLevelingDocument().setEnabled(false);
		getCbGenerateAdvancePayment().setEnabled(false);
		getCmbAdvancePaymentDocument().setEnabled(false);
		getRbOptionNo().setSelected(true);
		getRbOptionNo().requestFocusInWindow();
	}
	
	private void initObjects() {
		setCancelAction(false);
	}
	
	private void initJDialogAdapter(JDialog jDialog) {
		jDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setCancelAction(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void initListeners() {
		getRbOptionYes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getRbOptionYes().isSelected()) {
					getCmbLevelingDocument().setEnabled(true);
					loadComboBoxLevelingDocument();
					getCmbLevelingDocument().setSelectedItem(SpiritConstants.getNullValue());
					getCbGenerateAdvancePayment().setEnabled(true);
				}
			}
		});
		
		getRbOptionNo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getRbOptionNo().isSelected()) {
					getCmbLevelingDocument().removeAllItems();
					getCmbLevelingDocument().setSelectedItem(SpiritConstants.getNullValue());
					getCmbLevelingDocument().setEnabled(false);
					getCbGenerateAdvancePayment().setEnabled(false);
					getCmbAdvancePaymentDocument().removeAllItems();
					getCmbAdvancePaymentDocument().setSelectedItem(SpiritConstants.getNullValue());
					getCmbAdvancePaymentDocument().setEnabled(false);
				}
			}			
		});
		
		getCbGenerateAdvancePayment().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCbGenerateAdvancePayment().isSelected()) {
					getCmbAdvancePaymentDocument().setEnabled(true);
					loadComboBoxAdvancePaymentDocument();
					getCmbAdvancePaymentDocument().setSelectedItem(SpiritConstants.getNullValue());
				} else {
					getCmbAdvancePaymentDocument().removeAllItems();
					getCmbAdvancePaymentDocument().setSelectedItem(SpiritConstants.getNullValue());
					getCmbAdvancePaymentDocument().setEnabled(false);
				}
			}			
		});
		
		getBtnAccept().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				accept();
			}
		});
		
		getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				cancel();
			}			
		});		
	}
	
	private void accept() {
		if (getRbOptionYes().isSelected()) {
			if (validateFields()) {
				yesAction();
				setCancelAction(false);
				setVisible(false);
				dispose();
			}
		} else {
			setCancelAction(false);
			setVisible(false);
			dispose();
		}
	}
	
	private boolean validateFields() {
		if (getCmbLevelingDocument().getSelectedItem() == SpiritConstants.getNullValue()) {
			SpiritAlert.createAlert("Debe seleccionar documento de nivelación", SpiritAlert.WARNING);
			getCmbLevelingDocument().requestFocusInWindow();
			return false;
		}
		
		if (getCbGenerateAdvancePayment().isSelected() && getCmbAdvancePaymentDocument().getSelectedItem() == SpiritConstants.getNullValue()) {
			SpiritAlert.createAlert("Debe seleccionar documento de anticipo", SpiritAlert.WARNING);
			getCmbAdvancePaymentDocument().requestFocusInWindow();
			return false;
		}
		
		return true;
	}
	
	private void cancel() {
		setCancelAction(true);
		setVisible(false);
		dispose();
	}
	
	private void yesAction() {
		setLevelingDocument((DocumentoIf) getCmbLevelingDocument().getSelectedItem());
		if (getCbGenerateAdvancePayment().isSelected())
			setAdvancePaymentDocument((DocumentoIf) getCmbAdvancePaymentDocument().getSelectedItem());
	}
	
	private void loadComboBoxLevelingDocument() {
		Long moduleId;
		try {
			moduleId = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(WalletConstants.getCodeWalletModule()).iterator().next()).getId();
			boolean isCustomerWalletType = getWalletData().getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1))?true:false;
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("enterpriseId", Parametros.getIdEmpresa());
			queryMap.put("moduleId", moduleId);
			queryMap.put("walletType", (isCustomerWalletType)?SpiritConstants.getCustomerWalletType().substring(0,1):SpiritConstants.getProviderWalletType().substring(0,1));
			List<DocumentoIf> levelingDocuments = (ArrayList<DocumentoIf>) SessionServiceLocator.getDocumentoSessionService().findLevelingDocumentsByQuery(queryMap);
			Collections.sort((ArrayList<DocumentoIf>) levelingDocuments, getDocumentArrayListByNameSorter());
			levelingDocuments.add(0, (DocumentoIf) SpiritConstants.getNullValue());
			refreshCombo(getCmbLevelingDocument(), levelingDocuments);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void loadComboBoxAdvancePaymentDocument() {
		Long moduleId;
		try {
			moduleId = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(WalletConstants.getCodeWalletModule()).iterator().next()).getId();
			boolean isCustomerWalletType = getWalletData().getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1))?true:false;
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("enterpriseId", Parametros.getIdEmpresa());
			queryMap.put("moduleId", moduleId);
			queryMap.put("walletType", (isCustomerWalletType)?SpiritConstants.getCustomerWalletType().substring(0,1):SpiritConstants.getProviderWalletType().substring(0,1));
			List<DocumentoIf> advancePaymentDocuments = (ArrayList<DocumentoIf>) SessionServiceLocator.getDocumentoSessionService().findAdvancePaymentDocumentsByQuery(queryMap);
			Collections.sort((ArrayList<DocumentoIf>) advancePaymentDocuments, getDocumentArrayListByNameSorter());
			advancePaymentDocuments.add(0, (DocumentoIf) SpiritConstants.getNullValue());
			refreshCombo(getCmbAdvancePaymentDocument(), advancePaymentDocuments);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	protected static void refreshCombo(JComboBox combo,List<? extends Object> lista ){
		refreshComboByIndex(combo,lista,0);
	}
	
	protected static void refreshComboByIndex(JComboBox combo, List<? extends Object> lista, int index){
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
	
	public Comparator<DocumentoIf> getDocumentArrayListByNameSorter() {
		return documentArrayListByNameSorter;
	}

	public void setDocumentArrayListByNameSorter(Comparator<DocumentoIf> documentArrayListByNameSorter) {
		this.documentArrayListByNameSorter = documentArrayListByNameSorter;
	}

	public WalletData getWalletData() {
		return walletData;
	}

	public void setWalletData(WalletData walletData) {
		this.walletData = walletData;
	}

	public boolean isCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(boolean cancelAction) {
		this.cancelAction = cancelAction;
	}

	public DocumentoIf getLevelingDocument() {
		return levelingDocument;
	}

	public void setLevelingDocument(DocumentoIf levelingDocument) {
		this.levelingDocument = levelingDocument;
	}

	public DocumentoIf getAdvancePaymentDocument() {
		return advancePaymentDocument;
	}

	public void setAdvancePaymentDocument(DocumentoIf advancePaymentDocument) {
		this.advancePaymentDocument = advancePaymentDocument;
	}
}