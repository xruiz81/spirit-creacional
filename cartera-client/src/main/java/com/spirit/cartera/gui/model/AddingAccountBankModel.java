package com.spirit.cartera.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.spirit.cartera.gui.controller.WalletConstants;
import com.spirit.cartera.gui.panel.JDAddingAccountBank;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaData;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;

public class AddingAccountBankModel extends JDAddingAccountBank {
	private static final long serialVersionUID = -3377039517842320095L;
	private boolean cancelAction;
	private BancoIf bank;
	private ClienteIf businessOperator;
	private boolean relatedBusinessOperator;
	private CuentaBancariaIf addedAccountBank;

	public AddingAccountBankModel(Frame owner, BancoIf bank, ClienteIf businessOperator, boolean relatedBusinessOperator) {
		super(owner);
		setBank(bank);
		setBusinessOperator(businessOperator);
		setRelatedBusinessOperator(relatedBusinessOperator);
		initDialogComponents();
		initObjects();
		initListeners();
		initJDialogAdapter(this);
		initDialog();
	}

	private void initDialog() {
		this.setSize(WalletConstants.getJdialogAddingAccountBankWidht(), WalletConstants.getJdialogAddingAccountBankHeight());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - WalletConstants.getJdialogAddingAccountBankWidht()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - WalletConstants.getJdialogAddingAccountBankHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void initDialogComponents() {
		try {
			loadComboBoxBank();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de bancos", SpiritAlert.ERROR);
		}
		getRbCheckingAccount().setSelected(true);
		getTxtAccountBank().requestFocusInWindow();
	}
	
	@SuppressWarnings("unchecked")
	private void loadComboBoxBank() throws GenericBusinessException {
		JComboBox jCmbBank = getCmbBank();
		List<BancoIf> banks = new ArrayList<BancoIf>();
		banks = (List<BancoIf>) SessionServiceLocator.getBancoSessionService().getBancoList();
		refreshCombo(jCmbBank, banks);
		jCmbBank.setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(jCmbBank, getBank().getId()));
		jCmbBank.validate();
		jCmbBank.repaint();
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
		getCmbBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (getCmbBank().getSelectedItem() != SpiritConstants.getNullValue()) {
					BancoIf bank = (BancoIf) getCmbBank().getSelectedItem();
					setBank(bank);
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
		
		getCmbBank().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					accept();
			}			
		});
		
		getTxtAccountBank().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					accept();
			}			
		});
		
		getRbCheckingAccount().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					accept();
			}			
		});
		
		getRbSavingsAccount().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					accept();
			}			
		});
	}
	
	private void accept() {
		if (validateFields()) {
			yesAction();
			setCancelAction(false);
			setVisible(false);
			dispose();
		}
	}
	
	private boolean validateFields() {
		if (getCmbBank().getSelectedItem() == SpiritConstants.getNullValue()) {
			SpiritAlert.createAlert("Debe seleccionar banco", SpiritAlert.WARNING);
			getCmbBank().requestFocusInWindow();
			return false;
		}
		
		if (getTxtAccountBank().getText().trim().equals(SpiritConstants.getEmptyCharacter()) || getTxtAccountBank().getText() == SpiritConstants.getNullValue()) {
			SpiritAlert.createAlert("Debe ingresar el número de cuenta", SpiritAlert.WARNING);
			getTxtAccountBank().requestFocusInWindow();
			return false;
		}
		
		if (accountBankExists()) {
			SpiritAlert.createAlert("Cuenta bancaria existe", SpiritAlert.WARNING);
			getTxtAccountBank().requestFocusInWindow();
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private boolean accountBankExists() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("empresaId", Parametros.getIdEmpresa());
		queryMap.put("bancoId", getBank().getId());
		queryMap.put("cuenta", getTxtAccountBank().getText());
		queryMap.put("tipocuenta", (getRbCheckingAccount().isSelected())?WalletConstants.getCheckingAccount().substring(0,1):WalletConstants.getSavingsAccount().substring(0,1));
		queryMap.put("cuentaCliente", (isRelatedBusinessOperator())?SpiritConstants.getOptionYes().substring(0,1):SpiritConstants.getOptionNo().substring(0,1));
		if (isRelatedBusinessOperator())
			queryMap.put("clienteId", getBusinessOperator().getId());
		try {
			Iterator<CuentaBancariaIf> it = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(queryMap).iterator();
		if (it.hasNext())
			return true;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al validar datos de cuenta", SpiritAlert.ERROR);
		}
		return false;
	}
	
	private void cancel() {
		setCancelAction(true);
		setVisible(false);
		dispose();
	}
	
	private void yesAction() {
		CuentaBancariaIf accountBank = new CuentaBancariaData();
		accountBank.setBancoId(getBank().getId());
		accountBank.setClienteId(isRelatedBusinessOperator()?getBusinessOperator().getId():(Long) SpiritConstants.getNullValue());
		accountBank.setCuenta(getTxtAccountBank().getText());
		accountBank.setCuentaCliente((isRelatedBusinessOperator())?SpiritConstants.getOptionYes().substring(0,1):SpiritConstants.getOptionNo().substring(0,1));
		accountBank.setEmpresaId(Parametros.getIdEmpresa());
		accountBank.setNumeroCheque(SpiritConstants.getEmptyCharacter());
		accountBank.setTipocuenta((getRbCheckingAccount().isSelected())?WalletConstants.getCheckingAccount().substring(0,1):WalletConstants.getSavingsAccount().substring(0,1));
		try {
			accountBank = SessionServiceLocator.getCuentaBancariaSessionService().addCuentaBancaria(accountBank);
			setAddedAccountBank(accountBank);
			if (getBank().getEstado().equals(SpiritConstants.getInactiveStatus().substring(0,1))) {
				getBank().setEstado(SpiritConstants.getActiveStatus().substring(0,1));
				SessionServiceLocator.getBancoSessionService().saveBanco(getBank());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al guardar datos", SpiritAlert.ERROR);
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

	public boolean isCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(boolean cancelAction) {
		this.cancelAction = cancelAction;
	}

	public BancoIf getBank() {
		return bank;
	}

	public void setBank(BancoIf bank) {
		this.bank = bank;
	}

	public ClienteIf getBusinessOperator() {
		return businessOperator;
	}

	public void setBusinessOperator(ClienteIf businessOperator) {
		this.businessOperator = businessOperator;
	}

	public boolean isRelatedBusinessOperator() {
		return relatedBusinessOperator;
	}

	public void setRelatedBusinessOperator(boolean relatedBusinessOperator) {
		this.relatedBusinessOperator = relatedBusinessOperator;
	}

	public CuentaBancariaIf getAddedAccountBank() {
		return addedAccountBank;
	}

	public void setAddedAccountBank(CuentaBancariaIf addedAccountBank) {
		this.addedAccountBank = addedAccountBank;
	}
}