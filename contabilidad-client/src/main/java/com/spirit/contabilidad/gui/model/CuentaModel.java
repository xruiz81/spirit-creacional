package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaData;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.panel.JPCuenta;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.tree.BrowserEvent;
import com.spirit.util.tree.BrowserListener;

public class CuentaModel extends JPCuenta {

	private static final long serialVersionUID = -8241595941841002613L;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	private CuentaIf cuenta;
	private CuentaIf cuentaPadre;
	private CuentaIf cuentaRelacionada;
	private Calendar calendar = new GregorianCalendar();
	private PlanCuentaIf planCuenta;
	private Date fechaCreacion = new Date(0);
	private Icon customOpenIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/openNode.png");
	private Icon customClosedIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/closeNode.png");
	private Icon customLeafIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/leafNode.png");
	Collection cuentasTreeColeccion = new ArrayList();
	Map existeNodoMapMO = new LinkedHashMap();
	DefaultMutableTreeNode node;
	DefaultMutableTreeNode newNode;
	private boolean treeCuentaModificado = true;
	private boolean treeNodoEliminado = false;
	private boolean doClean = true;
	
	private static final int MAX_LONGITUD_CODIGO = 50;
	private static final int MAX_LONGITUD_NOMBRE = 100;
	private static final int MAX_LONGITUD_NOMBRE_CORTO = 20;
	private static final int MAX_LONGITUD_NIVEL = 3;
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String ESTADO_ACTIVA = NOMBRE_ESTADO_ACTIVA.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0,1);
	private static final String ESTADO_SELECCIONADO = "S";
	private static final String ESTADO_NO_SELECCIONADO = "N";

	public CuentaModel() {
		calendar.setTime(fechaCreacion);
		initKeyListeners();
		initListeners();
		addPopupMenu();
		cargarCombos();
		showSaveMode();
		cargarTreeCuenta();
    	new HotKeyComponent(this);
   	 	TreePath rootPath = getTreeCuenta().getPathForRow(0);
   	 	getTreeCuenta().setSelectionPath(rootPath);
	}
	
	private void cargarTreeCuenta() {
		try {
			DefaultTreeModel treeModel = generateTreeModel();
			getTreeCuenta().setModel(treeModel);
			getTreeCuenta().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
			customTreeRenderer.setOpenIcon(customOpenIcon);
			customTreeRenderer.setClosedIcon(customClosedIcon);
			customTreeRenderer.setLeafIcon(customLeafIcon);
		    getTreeCuenta().setCellRenderer(customTreeRenderer);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void addPopupMenu() {
		menuItem = new JMenuItem("<html><font color=red>Agregar cuenta contable</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				if (node != null) {
					cuentaPadre = loadCuenta(node, true);
					if (cuentaPadre != null) {
						DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode) node;
						removeNewNode();
						searchAndExpand(cuentaPadre.getNombre() + " ["+ cuentaPadre.getCodigo() + "]");
						node = tempNode;
						doClean = true;
						agregarCuentaContable();
					}
				}
			}
		});
		popup.add(menuItem);
		getTreeCuenta().add(popup);
		getTreeCuenta().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void save() {
		if (validateFields()) {
			CuentaData cuenta = registrarCuenta();
			try {
				UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
				SessionServiceLocator.getCuentaSessionService().procesarCuenta(cuenta, usuario.getId());
				newNode.setUserObject(getTxtNombre().getText() + " ["+ getTxtCodigo().getText() + "]");
				treeCuentaModificado = false;
				treeNodoEliminado = false;
				/*if (data.getEstado().equals(ESTADO_ACTIVA))
					treeNodoEliminado = false;
				else if (data.getEstado().equals(ESTADO_INACTIVA)) {
					DefaultTreeModel model = (DefaultTreeModel) getTreeCuenta().getModel();
					model.removeNodeFromParent(newNode);
					treeNodoEliminado = true;
				}*/
				this.showSaveMode();
				SpiritAlert.createAlert("Cuenta guardada con éxito", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
			}
		}
	}
	
	private void agregarCuentaContable() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.node.clone();
		getTreeCuenta().expandPath(getTreeCuenta().getSelectionPath());
		DefaultTreeModel model = (DefaultTreeModel) getTreeCuenta().getModel();
		// Creamos un nuevo nodo
	    newNode = new DefaultMutableTreeNode("NUEVA CUENTA");
	    // Insertamos el nuevo nodo como hijo del nodo seleccionado
	    model.insertNodeInto(newNode, this.node, this.node.getChildCount());
	    searchAndExpand("NUEVA CUENTA");
		this.node = null;
		showSaveMode();
		cuentaPadre = loadCuenta(node, true);
		setCuentaPadre();
		getTxtCodigo().grabFocus();
	}

	public void update() {
		if (node != null) {
			if (validateFields()) {
				registrarCuentaForUpdate();
				try {
					SessionServiceLocator.getCuentaSessionService().saveCuenta(cuenta);
					node.setUserObject(cuenta.getNombre() + " ["+ cuenta.getCodigo() + "]");
					getTreeCuenta().setSelectionPath(getTreeCuenta().getSelectionPath());
					treeCuentaModificado = false;
					treeNodoEliminado = false;
					/*if (cuenta.getEstado().equals(ESTADO_ACTIVA))
						treeNodoEliminado = false;
					else if (cuenta.getEstado().endsWith(ESTADO_INACTIVA)) {
						DefaultTreeModel model = (DefaultTreeModel) getTreeCuenta().getModel();
						model.removeNodeFromParent(node);
						treeNodoEliminado = true;
					}*/
					this.showSaveMode();
					getTreeCuenta().grabFocus();
					SpiritAlert.createAlert("Cuenta actualizada con éxito", SpiritAlert.INFORMATION);
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Error al actualizar información!", SpiritAlert.ERROR);
				}
			}
		}
	}

	public void delete() {
		if (node != null) {
			try {
				if(SessionServiceLocator.getCuentaSessionService().procesarEliminarCuenta(cuenta.getId())){
					DefaultTreeModel model = (DefaultTreeModel) getTreeCuenta().getModel();
					model.removeNodeFromParent(node);
					getTreeCuenta().repaint();
					getTreeCuenta().validate();
					treeCuentaModificado = false;
					treeNodoEliminado = true;
					this.showSaveMode();
					SpiritAlert.createAlert("Cuenta eliminada con éxito", SpiritAlert.INFORMATION);
				}else{
					SpiritAlert.createAlert("La Cuenta está siendo utilizada por otros usuarios o en un asiento", SpiritAlert.WARNING);
				}				
			} catch (Exception e) {
				SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
				this.showSaveMode();
			}
		}
	}
	
	public void report() {
		// TODO Auto-generated method stub
	}
	
	public void refresh() {
		setCursor(SpiritCursor.hourglassCursor);
		cargarComboPlanCuenta();
		cargarComboTipoCuenta();
		cargarComboTipoResultado();
		cargarTreeCuenta();
		setCursor(SpiritCursor.normalCursor);
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub	
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
	}
	
	public void deleteDetail() {
		
	}
	
	private CuentaData registrarCuenta() {
		CuentaData data = new CuentaData();
		data.setPlancuentaId(((PlanCuentaIf) this.getCmbPlanCuenta().getSelectedItem()).getId());
		data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
		data.setNombre(this.getTxtNombre().getText());
		data.setNombreCorto(this.getTxtNombreCorto().getText());
		data.setTipocuentaId(((TipoCuentaIf) this.getCmbTipoCuenta().getSelectedItem()).getId());
		
		if (!("".equals(this.getTxtPadre().getText())))
			data.setPadreId(cuentaPadre.getId());		
		
		if (!("".equals(this.getTxtRelacionada().getText())))
			data.setRelacionada(cuentaRelacionada.getId());
		
		if (this.getCbImputable().isSelected())
			data.setImputable(ESTADO_SELECCIONADO);
		else
			data.setImputable(ESTADO_NO_SELECCIONADO);
		
		if (this.getCbBanco().isSelected())
			data.setCuentaBanco(ESTADO_SELECCIONADO);
		else
			data.setCuentaBanco(ESTADO_NO_SELECCIONADO);

		data.setNivel(Integer.parseInt(this.getTxtNivel().getText()));

		if (this.getCbEfectivo().isSelected())
			data.setEfectivo(ESTADO_SELECCIONADO);
		else
			data.setEfectivo(ESTADO_NO_SELECCIONADO);

		if (this.getCbActivoFijo().isSelected())
			data.setActivofijo(ESTADO_SELECCIONADO);
		else
			data.setActivofijo(ESTADO_NO_SELECCIONADO);

		if (this.getCbDepartamento().isSelected())
			data.setDepartamento(ESTADO_SELECCIONADO);
		else
			data.setDepartamento(ESTADO_NO_SELECCIONADO);

		if (this.getCbLinea().isSelected())
			data.setLinea(ESTADO_SELECCIONADO);
		else
			data.setLinea(ESTADO_NO_SELECCIONADO);

		if (this.getCbEmpleado().isSelected())
			data.setEmpleado(ESTADO_SELECCIONADO);
		else
			data.setEmpleado(ESTADO_NO_SELECCIONADO);

		if (this.getCbCentroGasto().isSelected())
			data.setCentrogasto(ESTADO_SELECCIONADO);
		else
			data.setCentrogasto(ESTADO_NO_SELECCIONADO);

		if (this.getCbResultado().isSelected()) {
			TipoResultadoIf tipoResultado = (TipoResultadoIf) getCmbTipoResultado().getSelectedItem();
			if (tipoResultado != null)
				data.setTiporesultadoId(tipoResultado.getId());
			if (tipoResultado.getUtilidad().equals("-"))
				data.setGasto(ESTADO_SELECCIONADO);
		} else
			data.setGasto(ESTADO_NO_SELECCIONADO);

		if (this.getCbCliente().isSelected())
			data.setCliente(ESTADO_SELECCIONADO);
		else
			data.setCliente(ESTADO_NO_SELECCIONADO);
		
		data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
		return data;
	}
	
	private void registrarCuentaForUpdate() {
		cuenta.setCodigo(this.getTxtCodigo().getText());
		cuenta.setNombre(this.getTxtNombre().getText());
		cuenta.setNombreCorto(this.getTxtNombreCorto().getText());

		if (this.getCbImputable().isSelected())
			cuenta.setImputable(ESTADO_SELECCIONADO);
		else
			cuenta.setImputable(ESTADO_NO_SELECCIONADO);
		
		if (this.getCbBanco().isSelected())
			cuenta.setCuentaBanco(ESTADO_SELECCIONADO);
		else
			cuenta.setCuentaBanco(ESTADO_NO_SELECCIONADO);

		if (this.getCbEfectivo().isSelected())
			cuenta.setEfectivo(ESTADO_SELECCIONADO);
		else
			cuenta.setEfectivo(ESTADO_NO_SELECCIONADO);

		if (this.getCbActivoFijo().isSelected())
			cuenta.setActivofijo(ESTADO_SELECCIONADO);
		else
			cuenta.setActivofijo(ESTADO_NO_SELECCIONADO);
		
		if (cuentaRelacionada != null)
			cuenta.setRelacionada(cuentaRelacionada.getId());
		else
			cuenta.setRelacionada(null);

		if (this.getCbDepartamento().isSelected())
			cuenta.setDepartamento(ESTADO_SELECCIONADO);
		else
			cuenta.setDepartamento(ESTADO_NO_SELECCIONADO);

		if (this.getCbLinea().isSelected())
			cuenta.setLinea(ESTADO_SELECCIONADO);
		else
			cuenta.setLinea(ESTADO_NO_SELECCIONADO);

		if (this.getCbEmpleado().isSelected())
			cuenta.setEmpleado(ESTADO_SELECCIONADO);
		else
			cuenta.setEmpleado(ESTADO_NO_SELECCIONADO);

		if (this.getCbCentroGasto().isSelected())
			cuenta.setCentrogasto(ESTADO_SELECCIONADO);
		else
			cuenta.setCentrogasto(ESTADO_NO_SELECCIONADO);
		
		if (this.getCbResultado().isSelected()) {
			TipoResultadoIf tipoResultado = (TipoResultadoIf) getCmbTipoResultado().getSelectedItem();
			if (tipoResultado != null)
				cuenta.setTiporesultadoId(tipoResultado.getId());
			if (tipoResultado.getUtilidad().equals("-"))
				cuenta.setGasto(ESTADO_SELECCIONADO);
		} else {
			cuenta.setGasto(ESTADO_NO_SELECCIONADO);
			cuenta.setTiporesultadoId(null);
		}

		if (this.getCbCliente().isSelected())
			cuenta.setCliente(ESTADO_SELECCIONADO);
		else
			cuenta.setCliente(ESTADO_NO_SELECCIONADO);
		
		cuenta.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& (this.getCmbPlanCuenta().getSelectedItem() == null)
				&& "".equals(this.getTxtNombre().getText())
				&& "".equals(this.getTxtNombreCorto().getText())
				&& (this.getCmbTipoCuenta().getSelectedItem() == null)
				&& "".equals(this.getTxtPadre().getText())
				&& "".equals(this.getTxtRelacionada().getText())
				&& "".equals(this.getTxtNivel().getText())
				&& (this.getCmbTipoResultado().getSelectedItem() == null)) {
			return true;

		} else {

			return false;
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strNombreCorto = this.getTxtNombreCorto().getText();
		String strNivel = this.getTxtNivel().getText();
		PlanCuentaIf planCuenta = (PlanCuentaIf) this.getCmbPlanCuenta().getSelectedItem();
		TipoCuentaIf tipoCuenta = (TipoCuentaIf) this.getCmbTipoCuenta().getSelectedItem();

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para la Cuenta !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
			
		Collection cuentas = null;
		boolean codigoRepetido = false;
		
		try {
			cuentas = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator cuentasIt = cuentas.iterator();
		
		while (cuentasIt.hasNext()) {
			CuentaIf cuentaIf = (CuentaIf) cuentasIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (strCodigo.equals(cuentaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (strCodigo.equals(cuentaIf.getCodigo())) 
					if (cuenta.getId().equals(cuentaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la Cuenta está duplicado en el Plan de Cuenta seleccionado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para para la Cuenta !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}

		/*if ((("".equals(strNombreCorto)) || (strNombreCorto == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre corto para para la Cuenta !!", SpiritAlert.WARNING);
			getTxtNombreCorto().grabFocus();
			return false;
		}*/
		
		if (tipoCuenta == null) {
			SpiritAlert.createAlert("Debe seleccionar un Tipo Cuenta para la Cuenta !!", SpiritAlert.WARNING);
			getCmbTipoCuenta().grabFocus();
			return false;
		}
		
		if (planCuenta == null) {
			SpiritAlert.createAlert("Debe seleccionar un Plan de Cuenta para la Cuenta !!", SpiritAlert.WARNING);
			getCmbPlanCuenta().grabFocus();
			return false;
		}
		
		if ((ESTADO_INACTIVA).equals(planCuenta.getEstado())) {
			SpiritAlert.createAlert("El plan de cuentas se encuentra inactivo !!", SpiritAlert.WARNING);
			getCmbPlanCuenta().grabFocus();
			return false;
		}
		
		if ((("".equals(strNivel)) || (strNivel == null))) {
			SpiritAlert.createAlert("Debe seleccionar un nivel para la Cuenta !!", SpiritAlert.WARNING);
			getTxtNivel().grabFocus();
			return false;
		}
		
		if (getCbResultado().isSelected()) {
			if (getCmbTipoResultado().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el tipo de resultado !!", SpiritAlert.WARNING);
				getCmbTipoResultado().grabFocus();
				return false;
			}
		}
		
		/*if (getCbGasto().isSelected()) {
			if ((getCbLinea().isSelected() || getCbEmpleado().isSelected() || getCbCentroGasto().isSelected() || getCbCliente().isSelected() || getCbDepartamento().isSelected()) == false) {
				SpiritAlert.createAlert("Debe seleccionar al menos un tipo de resultado !!", SpiritAlert.WARNING);
				getCbGasto().grabFocus();
				return false;
			}
		}*/
		
		/*if (getCbActivoFijo().isSelected() && ("".equals(getTxtRelacionada().getText()) || getTxtRelacionada()==null)) {
			SpiritAlert.createAlert("Debe seleccionar una cuenta relacionada !!", SpiritAlert.WARNING);
			getCbActivoFijo().grabFocus();
			return false;
		}*/

		return true;
	}

	public void clean() {
		this.getCmbPlanCuenta().setSelectedItem("");
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtNombreCorto().setText("");
		this.getCmbTipoCuenta().setSelectedItem("");
		this.getTxtNivel().setText("");
		this.getCmbTipoResultado().setSelectedItem("");
		this.getTxtPadre().setText("");
		this.getTxtRelacionada().setText("");
		this.getCbActivoFijo().setSelected(false);
		this.getCbCentroGasto().setSelected(false);
		this.getCbCliente().setSelected(false);
		this.getCbDepartamento().setSelected(false);
		this.getCbEfectivo().setSelected(false);
		this.getCbEmpleado().setSelected(false);
		this.getCbResultado().setSelected(false);
		this.getCbImputable().setSelected(false);
		this.getCbBanco().setSelected(false);
		this.getCbLinea().setSelected(false);
		this.getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
		this.repaint();
	}

	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtNombre().setBackground(Parametros.findColor);
		getTxtNombreCorto().setBackground(Parametros.findColor);
		getCmbPlanCuenta().setBackground(Parametros.findColor);
		
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtNombreCorto().setEnabled(true);
		getCmbPlanCuenta().setEnabled(true);
		getCmbTipoCuenta().setSelectedItem("");
		getCmbTipoCuenta().setEnabled(false);
		getTxtPadre().setEditable(false);
		getTxtRelacionada().setEditable(false);
		getCbImputable().setEnabled(false);
		getCbBanco().setEnabled(false);
		getTxtNivel().setEnabled(false);
		getCmbTipoResultado().setEnabled(false);
		getCbEfectivo().setSelected(false);
		getCbEfectivo().setEnabled(false);
		getCbActivoFijo().setSelected(false);
		getCbActivoFijo().setEnabled(false);
		getCbResultado().setSelected(false);
		getCbResultado().setEnabled(false);
		getCbDepartamento().setSelected(false);
		getCbDepartamento().setEnabled(false);
		getCbLinea().setSelected(false);
		getCbLinea().setEnabled(false);
		getCbEmpleado().setSelected(false);
		getCbEmpleado().setEnabled(false);
		getCbCentroGasto().setSelected(false);
		getCbCentroGasto().setEnabled(false);
		getCbCliente().setSelected(false);
		getCbCliente().setEnabled(false);
		getCmbEstado().setSelectedItem(null);
		getCmbEstado().setEnabled(false);
		getCmbPlanCuenta().grabFocus();
	}

	public void showSaveMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getTxtNombreCorto().setBackground(Parametros.saveUpdateColor);
		getCmbPlanCuenta().setBackground(Parametros.saveUpdateColor);
		
		if (treeCuentaModificado || (!treeCuentaModificado && treeNodoEliminado) || node == null) {
			if (doClean)
				clean();
			setSaveMode();
			getTxtPadre().setEnabled(true);
			getTxtPadre().setEditable(false);
			getTxtRelacionada().setEnabled(true);
			getTxtRelacionada().setEditable(false);
			getTxtCodigo().setEnabled(true);
			getTxtNombre().setEnabled(true);
			getTxtNombreCorto().setEnabled(true);
			getTxtNivel().setEnabled(true);
			getTxtNivel().setEditable(true);
			getCmbPlanCuenta().setEnabled(true);
			if (planCuenta != null)
				getBtnPadre().setEnabled(true);
			else
				getBtnPadre().setEnabled(false);
			getCbImputable().setEnabled(true);
			getCbBanco().setEnabled(true);
			getCbEfectivo().setEnabled(true);
			getCbActivoFijo().setEnabled(true);
			getCbResultado().setEnabled(true);
			getBtnRelacionada().setEnabled(false);
			getCbDepartamento().setEnabled(false);
			getCbDepartamento().setSelected(false);
			getCbLinea().setEnabled(false);
			getCbLinea().setSelected(false);
			getCbEmpleado().setEnabled(false);
			getCbEmpleado().setSelected(false);
			getCbCentroGasto().setEnabled(false);
			getCbCentroGasto().setSelected(false);
			getCbCliente().setEnabled(false);
			getCbCliente().setSelected(false);
			getCmbTipoCuenta().setEnabled(true);
			getCmbTipoResultado().setSelectedItem(null);
			getCmbTipoResultado().setEnabled(false);
			getCmbEstado().setEnabled(true);
			getCmbPlanCuenta().grabFocus();
		}
		
		if (treeCuentaModificado || (getMode() == SpiritMode.SAVE_MODE && node != null)) {
			getCmbPlanCuenta().setEnabled(true);
			if (planCuenta != null)
				getBtnPadre().setEnabled(true);
			else
				getBtnPadre().setEnabled(false);
			getCmbTipoCuenta().setEnabled(true);
			//cargarTreeCuenta();
			treeCuentaModificado = false;
			getCmbPlanCuenta().grabFocus();
		}
	}

	public void showUpdateMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getTxtNombreCorto().setBackground(Parametros.saveUpdateColor);
		getCmbPlanCuenta().setBackground(Parametros.saveUpdateColor);
		
		setUpdateMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtNombreCorto().setEnabled(true);
		getCmbPlanCuenta().setEnabled(false);
		getCmbTipoCuenta().setEnabled(true);
		getTxtPadre().setEditable(false);
		getTxtRelacionada().setEditable(false);
		getCbImputable().setEnabled(false);
		getCbBanco().setEnabled(false);
		getTxtNivel().setEnabled(false);
		
		if (cuenta.getTiporesultadoId() != null)
			getCmbTipoResultado().setEnabled(true);
		else
			getCmbTipoResultado().setEnabled(false);
		
		getBtnPadre().setEnabled(true);
		
		if (ESTADO_SELECCIONADO.equals(cuenta.getActivofijo()))
			getBtnRelacionada().setEnabled(true);
		else
			getBtnRelacionada().setEnabled(false);
		
		getCbEfectivo().setEnabled(true);
		getCbImputable().setEnabled(true);
		getCbBanco().setEnabled(true);
		getCbActivoFijo().setEnabled(true);
		getCbResultado().setEnabled(true);
		
		if (getCbResultado().isSelected()) {
			getCbLinea().setEnabled(true);
			getCbEmpleado().setEnabled(true);
			getCbCentroGasto().setEnabled(true);
			getCbCliente().setEnabled(true);
			getCbDepartamento().setEnabled(true);
		} else {
			getCbLinea().setEnabled(false);
			getCbEmpleado().setEnabled(false);
			getCbCentroGasto().setEnabled(false);
			getCbCliente().setEnabled(false);
			getCbDepartamento().setEnabled(false);
		}
		
		getCmbEstado().setEnabled(true);
	}
	
	private void cargarCombos() {
		cargarComboPlanCuenta();
		getCmbTipoCuenta().setEnabled(true);
		cargarComboTipoCuenta();
		getCbImputable().setEnabled(true);
		getCbBanco().setEnabled(true);
		cargarComboTipoResultado();
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuentas = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuentas);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}

	private void cargarComboTipoCuenta(){
		List tiposCuentas = ContabilidadFinder.findTipoCuenta();
		refreshCombo(getCmbTipoCuenta(),tiposCuentas);
	}

	private void cargarComboTipoResultado(){
		List tiposResultado = ContabilidadFinder.findTipoResultado();
		refreshCombo(getCmbTipoResultado(),tiposResultado);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtNombreCorto().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_CORTO));
		getTxtNivel().addKeyListener(new TextChecker(MAX_LONGITUD_NIVEL));
		getTxtNivel().addKeyListener(new NumberTextField());
	}
	
	private void initListeners() {
		getCbActivoFijo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbActivoFijo().isSelected()) {
					getBtnRelacionada().setEnabled(true);
					getTxtRelacionada().setEnabled(true);
					getTxtRelacionada().setEditable(false);
					if (getCmbPlanCuenta().getSelectedIndex() != -1) {
						getBtnRelacionada().setEnabled(true);
					}
				} else {
					cuentaRelacionada = null;
					getTxtRelacionada().setText("");
					getTxtRelacionada().setEnabled(false);
					getBtnRelacionada().setEnabled(false);
				}
			}
		});

		getCbResultado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbResultado().isSelected()) {
					getCmbTipoResultado().setEnabled(true);
					getCbDepartamento().setEnabled(true);
					getCbLinea().setEnabled(true);
					getCbEmpleado().setEnabled(true);
					getCbCentroGasto().setEnabled(true);
					getCbCliente().setEnabled(true);
				} else {
					getCmbTipoResultado().setSelectedIndex(-1);
					getCmbTipoResultado().setEnabled(false);
					getCbDepartamento().setSelected(false);
					getCbDepartamento().setEnabled(false);
					getCbLinea().setSelected(false);
					getCbLinea().setEnabled(false);
					getCbEmpleado().setSelected(false);
					getCbEmpleado().setEnabled(false);
					getCbCentroGasto().setSelected(false);
					getCbCentroGasto().setEnabled(false);
					getCbCliente().setSelected(false);
					getCbCliente().setEnabled(false);
				}
			}
		});

		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				Long idPlanCuentaAnterior = null;
				if (planCuenta != null)
					idPlanCuentaAnterior = planCuenta.getId();
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				if (planCuenta != null) {
					getBtnPadre().setEnabled(true);
					//if (getMode() != SpiritMode.UPDATE_MODE)
						//cargarTreeCuenta();
					//else 
					if (idPlanCuentaAnterior != null) 
						if (planCuenta.getId().compareTo(idPlanCuentaAnterior) != 0)
							cargarTreeCuenta();		
				}

				if (getCbActivoFijo().isSelected()) {
					getBtnRelacionada().setEnabled(true);
				}
				setCursor(SpiritCursor.normalCursor);
			}
		});
		
		getBtnPadre().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();

				if (planCuenta!=null){
					cuentaCriteria = new CuentaCriteria("Cuenta Padre", "", planCuenta.getId(), 0L, ESTADO_ACTIVA);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						doClean = false;
						removeNewNode();
						cuentaPadre = (CuentaIf) popupFinder.getElementoSeleccionado();
						searchAndExpand(cuentaPadre.getNombre() + " ["+ cuentaPadre.getCodigo() + "]");
						node = (DefaultMutableTreeNode) getTreeCuenta().getLastSelectedPathComponent();
						//node.setUserObject(cuentaPadre.getNombre() + " ["+ cuentaPadre.getCodigo() + "]");
						agregarCuentaContable();
						doClean = true;
					}
				} else {
					SpiritAlert.createAlert("Debe seleccionar un Plan de Cuenta primero!", SpiritAlert.WARNING);
					getCmbPlanCuenta().grabFocus();
				}
			}
		});

		getBtnRelacionada().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				
				if (planCuenta != null) {
					cuentaCriteria = new CuentaCriteria("Cuenta Relacionada","", planCuenta.getId(), 0L, ESTADO_ACTIVA);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										cuentaCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if ( popupFinder.getElementoSeleccionado() != null ){
						cuentaRelacionada = (CuentaIf) popupFinder.getElementoSeleccionado();
						getTxtRelacionada().setText(cuentaRelacionada.getCodigo() 
								+ " - " + cuentaRelacionada.getNombre());
					}
					
				} else {
					SpiritAlert.createAlert("Debe seleccionar un Plan de Cuenta!! ", SpiritAlert.WARNING);
					getCmbPlanCuenta().grabFocus();
				}
			}
		});
		
		getTreeCuenta().addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	if (doClean) {
		    		node = (DefaultMutableTreeNode) getTreeCuenta().getLastSelectedPathComponent();
		    		if (node == null) 
		    			return;
		    		
		    		if (node.isRoot()) {
		    			node = null;
		    			showSaveMode();
		    			return;
		    		}
		    		
		    		if (!node.getUserObject().toString().equals("NUEVA CUENTA"))
		    			loadCuenta(node, false);
		    		else {
		    			DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
		    			cuentaPadre = loadCuenta(parentNode,true);
		    			DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) node.clone();
		    			node = null;
		    			showSaveMode();
		    			setCuentaPadre();
		    			node = newNode;
		    		}
		    	}
		    }
		});
	}
	
	BrowserListener browserListenerTreeCuenta = new BrowserListener () {
      	public void Select(BrowserEvent e) {}
      	public void Deselect(BrowserEvent e) {}
		public void Activate(BrowserEvent e) {}
		public void Deactivate(BrowserEvent e) {}
	};

	private void setCuentaPadre() {
		getTxtPadre().setText(cuentaPadre.getCodigo() + " - " + cuentaPadre.getNombre());
		getTxtNivel().setEnabled(false);
		getTxtNivel().setText(((Integer) (cuentaPadre.getNivel() + 1)).toString());
	}
	
	private CuentaIf loadCuenta(DefaultMutableTreeNode node, boolean isNew) {
		/* Recuperar el nodo que fue seleccionado */ 
        Object nodeInfo = node.getUserObject();
        CuentaIf cuentaIf = null;
        /* Reaccionar a la selección del nodo */
        String nombreNodo = nodeInfo.toString();
        String codigo = nombreNodo.substring(nombreNodo.indexOf("[") + 1, nombreNodo.length() - 1);
        Map parameterMap = new HashMap();
        parameterMap.put("codigo", codigo);
        parameterMap.put("plancuentaId", planCuenta.getId());
		try {
			Iterator cuentaIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap).iterator();
			if (cuentaIterator.hasNext()) {
				cuentaIf = (CuentaIf) cuentaIterator.next();
				if (!isNew)
					getSelectedObject(cuentaIf);
			}
		} catch (GenericBusinessException ex) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			ex.printStackTrace();
		}
		
		return cuentaIf;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();

		if (!("".equals(this.getTxtCodigo().getText())))
			aMap.put("codigo", this.getTxtCodigo().getText().toUpperCase() + "%");
		//else
			//aMap.put("codigo", "%");
		
		if (!("".equals(this.getTxtNombre().getText())))
			aMap.put("nombre", this.getTxtNombre().getText().toUpperCase() + "%");
		
		if (!("".equals(this.getTxtNombreCorto().getText())))
			aMap.put("nombreCorto", this.getTxtNombreCorto().getText().toUpperCase() + "%");
		
		aMap.put("plancuentaId", planCuenta.getId());
		
		//aMap.put("estado", ESTADO_ACTIVA);
		return aMap;
	}

	public void find() {
		if ((this.getCmbPlanCuenta().getSelectedItem() != null)) {
			try {
				Map mapa =  buildQuery();
				int tamanoLista = SessionServiceLocator.getCuentaSessionService().getCuentaListSize(mapa, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				if (tamanoLista > 0) {
					CuentaCriteria cuentaCriteria = new CuentaCriteria();
					cuentaCriteria.setResultListSize(tamanoLista);
					cuentaCriteria.setQueryBuilded(mapa);
					cuentaCriteria.setIdUsuario(((UsuarioIf) Parametros.getUsuarioIf()).getId());
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						getSelectedObject();
						searchAndExpand(cuenta.getNombre() + " ["+ cuenta.getCodigo() + "]");
					}
				} else {
					SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				}

			} catch (Exception e) {
				SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Debe escoger un Plan de Cuenta !!!", SpiritAlert.WARNING);
			getCmbPlanCuenta().grabFocus();
		}
	}

	private void getSelectedObject() {
		cuenta = (CuentaIf) popupFinder.getElementoSeleccionado();
		setDataObjectSelected();
	}
	
	private void getSelectedObject(CuentaIf cuentaIf) {
		cuenta = cuentaIf;
		setDataObjectSelected();
	}

	private void setDataObjectSelected() {
		this.showUpdateMode();
		getCmbPlanCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPlanCuenta(), cuenta.getPlancuentaId()));
		
		getTxtCodigo().setText(cuenta.getCodigo());
		getTxtNombre().setText(cuenta.getNombre());
		getTxtNombreCorto().setText(cuenta.getNombreCorto());
		getTxtNivel().setText(cuenta.getNivel().toString());

		getCmbTipoCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoCuenta(), cuenta.getTipocuentaId()));
		
		try {
			if (cuenta.getPadreId() != null) {
				cuentaPadre = SessionServiceLocator.getCuentaSessionService().getCuenta(cuenta.getPadreId());
				getTxtPadre().setText(cuentaPadre.getCodigo() + " - " + cuentaPadre.getNombre());
			} else {
				cuentaPadre = null;
				getTxtPadre().setText("");
			}
		} catch (GenericBusinessException e2) {
			e2.printStackTrace();
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getImputable())) {
			getCbImputable().setSelected(true);
		} else {
			getCbImputable().setSelected(false);
		}
		
		if (ESTADO_SELECCIONADO.equals(cuenta.getCuentaBanco())) {
			getCbBanco().setSelected(true);
		} else {
			getCbBanco().setSelected(false);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getEfectivo())) {
			getCbEfectivo().setSelected(true);
		} else {
			getCbEfectivo().setSelected(false);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getActivofijo())) {
			getCbActivoFijo().setSelected(true);
			try {
				if (cuenta.getRelacionada() != null) {
					cuentaRelacionada = SessionServiceLocator.getCuentaSessionService().getCuenta(cuenta.getRelacionada());
					getTxtRelacionada().setText(cuentaRelacionada.getCodigo() + " - " + cuentaRelacionada.getNombre());
					getBtnRelacionada().setEnabled(true);
				} else {
					cuentaRelacionada = null;
					getTxtRelacionada().setText("");
				}
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
			}
		} else {
			getCbActivoFijo().setSelected(false);
			cuentaRelacionada = null;
			getTxtRelacionada().setText("");
			getBtnRelacionada().setEnabled(false);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getDepartamento())) {
			getCbDepartamento().setSelected(true);
			getCbDepartamento().setEnabled(true);
		} else {
			getCbDepartamento().setSelected(false);
			getCbDepartamento().setEnabled(true);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getLinea())) {
			getCbLinea().setSelected(true);
			getCbLinea().setEnabled(true);
		} else {
			getCbLinea().setSelected(false);
			getCbLinea().setEnabled(true);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getEmpleado())) {
			getCbEmpleado().setSelected(true);
			getCbEmpleado().setEnabled(true);
		} else {
			getCbEmpleado().setSelected(false);
			getCbEmpleado().setEnabled(true);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getCentrogasto())) {
			getCbCentroGasto().setSelected(true);
			getCbCentroGasto().setEnabled(true);
		} else {
			getCbCentroGasto().setSelected(false);
			getCbCentroGasto().setEnabled(true);
		}

		if (cuenta.getTiporesultadoId() != null) {
			getCbResultado().setSelected(true);
			getCbResultado().setEnabled(true);
			getCmbTipoResultado().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoResultado(), cuenta.getTiporesultadoId()));
			getCmbTipoResultado().setEnabled(true);			
		} else {
			getCbResultado().setSelected(false);
			getCmbTipoResultado().setSelectedItem(null);
			getCmbTipoResultado().setEnabled(false);
		}

		if (ESTADO_SELECCIONADO.equals(cuenta.getCliente())) {
			getCbCliente().setSelected(true);
			getCbCliente().setEnabled(true);
		} else {
			getCbCliente().setSelected(false);
			getCbCliente().setEnabled(true);
		}
		
		if (ESTADO_ACTIVA.equals(cuenta.getEstado())) {
			getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
		} else if (ESTADO_INACTIVA.equals(cuenta.getEstado())) {
			getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVA);
		}
	}

	private DefaultTreeModel generateTreeModel() throws GenericBusinessException {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]");		
		cuentasTreeColeccion.clear();
		System.out.println("Ejecutando query...");
		//generarTreeCuentasColeccion(root, getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId()));
		Map parameterMap = new HashMap();
		parameterMap.put("plancuentaId", planCuenta.getId());
		//parameterMap.put("estado", ESTADO_ACTIVA);
		//cuentasTreeColeccion = getCuentaSessionService().findCuentaByQuery(parameterMap);
		Long t1 = System.currentTimeMillis();
		cuentasTreeColeccion = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryAndUsuarioId(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
		Long t2 = System.currentTimeMillis();
		System.out.println("Generando tree...");
		Iterator itCuentasTreeColeccion = cuentasTreeColeccion.iterator();

		while (itCuentasTreeColeccion.hasNext()) {
			CuentaIf cuentaIf = (CuentaIf) itCuentasTreeColeccion.next();
			if (cuentaIf.getNivel() == 1) {
				DefaultMutableTreeNode cuentaNodo = new DefaultMutableTreeNode(cuentaIf.getNombre() + " ["+ cuentaIf.getCodigo() + "]");
				existeNodoMapMO.put(cuentaIf.getId(),cuentaNodo);
				root.add(cuentaNodo);
				insertarNodosHijoSeleccionado(cuentaIf.getId(),cuentaNodo,existeNodoMapMO,cuentasTreeColeccion);
			}
		}
		
		Long t3 = System.currentTimeMillis();
		System.out.println("-------------------------------------");
		System.out.println("Tiempo ejecución Query  = " + (t2 - t1) + " [ms]");
		System.out.println("Tiempo generación Árbol = " + (t3 - t2) + " [ms]");
		System.out.println("-------------------------------------");
		System.out.println("Tiempo total            = " + (t2 - t1 + t3 - t2) + " [ms]");
		System.out.println("-------------------------------------");
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		return defaultTreeModel;
	}
	
	private void generarTreeCuentasColeccion(DefaultMutableTreeNode nodoRaiz, Collection cuentasColeccion) {
		Iterator itCuentasColeccion = cuentasColeccion.iterator();

		while (itCuentasColeccion.hasNext()) {
			CuentaIf cuentaPadreIf = (CuentaIf) itCuentasColeccion.next();
			if (cuentaPadreIf.getPadreId() == null) {
				cuentasTreeColeccion.add(cuentaPadreIf);
				setearHijosCuenta(cuentaPadreIf.getId(), cuentasColeccion);
			}
		}
	}
	
	public boolean searchAndExpand(String text) {
        TreeNode[] path = search ((DefaultMutableTreeNode) getTreeCuenta().getModel().getRoot (), text);

        if (path != null) {
            TreePath treePath = new TreePath (path);
            getTreeCuenta().scrollPathToVisible (treePath);
            getTreeCuenta().setSelectionPath (treePath);
            return true;
        }
        
        return false;
    }

    private TreeNode[] search(DefaultMutableTreeNode node, Object object) {
        TreeNode[] path = null;

        if (node.getUserObject ().equals (object)) {
            path = ((DefaultTreeModel)getTreeCuenta().getModel()).getPathToRoot(node);
        } else {
            int i = 0;
            int n = ((DefaultTreeModel)getTreeCuenta().getModel()).getChildCount(node);

            while ((i < n) && (path == null)) {
                path = search((DefaultMutableTreeNode) ((DefaultTreeModel)getTreeCuenta().getModel()).getChild (node, i), object);
                i++;
            }
        }

        return path;
    }
    
    private void removeNewNode() {
		if (searchAndExpand("NUEVA CUENTA")) {
			DefaultTreeModel model = (DefaultTreeModel) getTreeCuenta().getModel();
			node = (DefaultMutableTreeNode) getTreeCuenta().getLastSelectedPathComponent();
			model.removeNodeFromParent(node);
		}
		collapseAllTree();
	}
    
    private void collapseAllTree() {
		TreeNode root = (TreeNode) getTreeCuenta().getModel().getRoot();
		getTreeCuenta().collapsePath(new TreePath(root));
	}
	
	private void setearHijosCuenta(Long cuentaPadreId, Collection cuentasColeccion) {
		Iterator itCuentasColeccion = cuentasColeccion.iterator();
		
		while (itCuentasColeccion.hasNext()) {
			CuentaIf cuentaHijoIf = (CuentaIf) itCuentasColeccion.next();
			if (cuentaHijoIf.getPadreId() != null && cuentaPadreId != null) {
				if (cuentaHijoIf.getPadreId().compareTo(cuentaPadreId) == 0) {
					cuentasTreeColeccion.add(cuentaHijoIf);
					setearHijosCuenta(cuentaHijoIf.getId(), cuentasColeccion);
				}
			}
		}
	}
	
	private void insertarNodosHijoSeleccionado(Long cuentaPadreId, DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap, Collection cuentasColeccion) {
		Collection cuentasColeccionClon = (ArrayList) ((ArrayList) cuentasColeccion).clone();
		Iterator itCuentaTreeColeccion = cuentasColeccionClon.iterator();

		while (itCuentaTreeColeccion.hasNext()) {
			CuentaIf nodoCuentaIf = (CuentaIf) itCuentaTreeColeccion.next();
			// Añado el nodo hijo a su padre
			if (nodoCuentaIf.getPadreId() != null && cuentaPadreId != null) {
				if (nodoCuentaIf.getPadreId().compareTo(cuentaPadreId) == 0) {
					DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodoCuentaIf.getNombre() + " [" + nodoCuentaIf.getCodigo()+ "]");
					String splitNodoHijo = (nodoHijo.toString().split("\\["))[1];
					String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
					// Guardo en el mapa de nodos existentes en el menú el nodo hijo actual
					existeNodoMap.put(codigoNodoHijo, nodoHijo);
					// Añado al nodo seleccionado en el árbol de la derecha el hijo encontrado
					nodoSeleccionado.add(nodoHijo);
					//insertarNodosHijoSeleccionado(nodoCuentaIf.getId(), nodoHijo, existeNodoMap, cuentasColeccion);
					
					// Aquí la diferencia
					itCuentaTreeColeccion.remove();
					insertarNodosHijoSeleccionado(nodoCuentaIf.getId(), nodoHijo, existeNodoMap, cuentasColeccionClon);
				}
			}
		}
	}
}
