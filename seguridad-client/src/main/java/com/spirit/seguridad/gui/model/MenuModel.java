package com.spirit.seguridad.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.session.ModuloSessionService;
import com.spirit.seguridad.entity.MenuData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.gui.panel.JPMenu;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.seguridad.session.RolOpcionSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;

public class MenuModel extends JPMenu {
	
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_NOMBRE = 100;
	private static final int MAX_LONGITUD_PANEL = 70;
	private static final String NOMBRE_MENU_MANTENIMIENTO = "MANTENIMIENTO";
	private static final String NOMBRE_MENU_TRANSACCIONES = "TRANSACCIONES";
	private static final String NOMBRE_MENU_AUTORIZACIONES = "AUTORIZACIONES";
	private static final String NOMBRE_MENU_INFORMES = "INFORMES";
	private static final String NOMBRE_MENU_REPORTES = "REPORTES";
	
	private Vector menuVector = new Vector();
	private DefaultTableModel tableModel;
	private int menuSeleccionado;
	private MenuIf menuIf;	

	
	public MenuModel(){
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblMenu());
		anchoColumnasTabla();
		getTblMenu().addMouseListener(oMouseAdapterTblMenu);
		getTblMenu().addKeyListener(oKeyAdapterTblMenu);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtPanel().addKeyListener(new TextChecker(MAX_LONGITUD_PANEL, TextChecker.NO_MAYUSCULAS));
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMenu().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblMenu().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblMenu().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblMenu().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblMenu().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(170);		
	}
	
	MouseListener oMouseAdapterTblMenu = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblMenu = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		MenuIf menu;
		MenuIf moduloMenu;
		ModuloIf modulo;
		
		try {
			//Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setMenuSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				menuIf = (MenuIf)  getMenuVector().get(getMenuSeleccionado());
				getTxtCodigo().setText(menuIf.getCodigo());
				getTxtNombre().setText(menuIf.getNombre());
				menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuById(menuIf.getPadreId()).iterator().next();
				moduloMenu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuById(menu.getPadreId()).iterator().next();
				modulo = (ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByNombre(moduloMenu.getNombre()).iterator().next();
				getCmbModulo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModulo(), modulo.getId()));
				getCmbModulo().repaint();
				
				if(menu.getNombre().equals(NOMBRE_MENU_MANTENIMIENTO))
					getCmbMenu().setSelectedItem(NOMBRE_MENU_MANTENIMIENTO);
				else if(menu.getNombre().equals(NOMBRE_MENU_TRANSACCIONES))
					getCmbMenu().setSelectedItem(NOMBRE_MENU_TRANSACCIONES);
				else if(menu.getNombre().equals(NOMBRE_MENU_AUTORIZACIONES))
					getCmbMenu().setSelectedItem(NOMBRE_MENU_AUTORIZACIONES);
				else if(menu.getNombre().equals(NOMBRE_MENU_INFORMES))
					getCmbMenu().setSelectedItem(NOMBRE_MENU_INFORMES);
				else if(menu.getNombre().equals(NOMBRE_MENU_REPORTES))
					getCmbMenu().setSelectedItem(NOMBRE_MENU_REPORTES);
				
				getCmbMenu().repaint();
				getTxtPanel().setText(menuIf.getPanel().substring(menuIf.getPanel().lastIndexOf(".")+1));	
				setUpdateMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarCombos();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void cargarCombos(){
		try {
			SpiritComboBoxModel cmbModelModulo = new SpiritComboBoxModel((List) SessionServiceLocator.getModuloSessionService().getModuloList());
			getCmbModulo().setModel(cmbModelModulo);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		getCmbMenu().addItem(NOMBRE_MENU_MANTENIMIENTO);
		getCmbMenu().addItem(NOMBRE_MENU_TRANSACCIONES);
		getCmbMenu().addItem(NOMBRE_MENU_AUTORIZACIONES);
		getCmbMenu().addItem(NOMBRE_MENU_INFORMES);
		getCmbMenu().addItem(NOMBRE_MENU_REPORTES);
		getCmbMenu().setSelectedIndex(-1);
	}
	
	private void cargarTabla() {
		
		try {
			Collection menu = SessionServiceLocator.getMenuSessionService().getMenuList();
			Iterator menuIterator = menu.iterator();
			
			if(!getMenuVector().isEmpty()){
				getMenuVector().removeAllElements();
			}
			
			while (menuIterator.hasNext()) {
				MenuIf menuIf = (MenuIf) menuIterator.next();
				
				if(menuIf.getNivel() == 3){
					tableModel = (DefaultTableModel) getTblMenu().getModel();
					Vector<String> fila = new Vector<String>();
					getMenuVector().add(menuIf);
					
					agregarColumnasTabla(menuIf, fila);
					
					tableModel.addRow(fila);
				}	
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(MenuIf menuIf, Vector<String> fila){
		MenuIf menu;
		MenuIf modulo;
		
		try{
			fila.add(menuIf.getCodigo());
			fila.add(menuIf.getNombre());
			
			menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuById(menuIf.getPadreId()).iterator().next();
			modulo = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuById(menu.getPadreId()).iterator().next();
			
			fila.add(modulo.getNombre());
			fila.add(menu.getNombre());
			if (menuIf.getPanel() != null)
				fila.add(menuIf.getPanel().substring(menuIf.getPanel().lastIndexOf(".")+1));
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		showSaveMode();
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		int favorito = 0;
		
		try {
			if (validateFields()) {
				MenuData data = new MenuData();

				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
								
				ModuloIf moduloIf = (ModuloIf) getCmbModulo().getSelectedItem();
				MenuIf modulo = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(moduloIf.getNombre()).iterator().next();
				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombreAndByPadreId(getCmbMenu().getSelectedItem().toString(), modulo.getId()).iterator().next();
				data.setPadreId(menu.getId());
				
				data.setNivel(3);
				
				Collection moduloMenu = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(menu.getId());
				Iterator moduloMenuIt = moduloMenu.iterator();
				
				while(moduloMenuIt.hasNext()){
					MenuIf moduloMenuIf = (MenuIf) moduloMenuIt.next();
					
					if(moduloMenuIf.getFavorito() > favorito)
						favorito = moduloMenuIf.getFavorito();
				}
				
				data.setFavorito(favorito + 1);
				
				data.setPanel("com.spirit." + moduloIf.getNombre().toLowerCase() + ".gui.model." + getTxtPanel().getText());
						
				SessionServiceLocator.getMenuSessionService().addMenu(data);
				SpiritAlert.createAlert("Menú guardado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		int favorito = 1;
		Vector<MenuIf> menusVector = new Vector<MenuIf>();
		
		try {
			if (validateFields() && !estaEnRol()) {
				setMenuActualizadoIf((MenuIf) getMenuVector().get(getMenuSeleccionado()));
				
				getMenuActualizadoIf().setCodigo(getTxtCodigo().getText());
				getMenuActualizadoIf().setNombre(getTxtNombre().getText());
				getMenuActualizadoIf().setNivel(3);
				
				MenuIf buscarMenuAnterior = SessionServiceLocator.getMenuSessionService().getMenu(getMenuActualizadoIf().getPadreId());
				MenuIf buscarModuloAnterior = SessionServiceLocator.getMenuSessionService().getMenu(buscarMenuAnterior.getPadreId()); 
				
				ModuloIf moduloIf = (ModuloIf) getCmbModulo().getSelectedItem();
				MenuIf modulo = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(moduloIf.getNombre()).iterator().next();
				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombreAndByPadreId(getCmbMenu().getSelectedItem().toString(), modulo.getId()).iterator().next();
				getMenuActualizadoIf().setPadreId(menu.getId());
				
				MenuIf buscarMenuNuevo = SessionServiceLocator.getMenuSessionService().getMenu(getMenuActualizadoIf().getPadreId());
				MenuIf buscarModuloNuevo = SessionServiceLocator.getMenuSessionService().getMenu(buscarMenuNuevo.getPadreId()); 
					
				if(!buscarModuloAnterior.getNombre().equals(buscarModuloNuevo.getNombre())){
					
					Collection menusModulo = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(buscarModuloAnterior.getId());
					Iterator menusModuloIt = menusModulo.iterator();
					
					while(menusModuloIt.hasNext()){
						MenuIf menusModuloIf = (MenuIf) menusModuloIt.next();
						
						if(menusModuloIf.getFavorito() > getMenuActualizadoIf().getFavorito()){
							menusModuloIf.setFavorito(menusModuloIf.getFavorito() - 1);
							menusVector.add(menusModuloIf);
						}
					}
										
					Collection moduloMenu = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(modulo.getId());
					Iterator moduloMenuIt = moduloMenu.iterator();
					
					while(moduloMenuIt.hasNext()){
						MenuIf moduloMenuIf = (MenuIf) moduloMenuIt.next();
						
						if(moduloMenuIf.getFavorito() > favorito)
							favorito = moduloMenuIf.getFavorito();
					}
					getMenuActualizadoIf().setFavorito(favorito + 1);
				}
				
				getMenuActualizadoIf().setPanel("com.spirit." + moduloIf.getNombre().toLowerCase() + ".gui.model." + getTxtPanel().getText());
				
				menusVector.add(getMenuActualizadoIf());
				SessionServiceLocator.getMenuSessionService().actualizarMenu(menusVector);
				getMenuVector().setElementAt(getMenuActualizadoIf(), getMenuSeleccionado());
				setMenuActualizadoIf(null);
				
				SpiritAlert.createAlert("Menú actualizado con éxito", SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		
		Vector<MenuIf> menusVector = new Vector<MenuIf>();
		try {
			if(!estaEnRol()){
				MenuIf menuIf = (MenuIf) getMenuVector().get(getMenuSeleccionado());
				MenuIf buscarMenuAnterior = SessionServiceLocator.getMenuSessionService().getMenu(getMenuActualizadoIf().getPadreId());
				MenuIf buscarModuloAnterior = SessionServiceLocator.getMenuSessionService().getMenu(buscarMenuAnterior.getPadreId()); 
				
				Collection menusModulo = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(buscarModuloAnterior.getId());
				Iterator menusModuloIt = menusModulo.iterator();
				
				while(menusModuloIt.hasNext()){
					MenuIf menusModuloIf = (MenuIf) menusModuloIt.next();
					
					if(menusModuloIf.getFavorito() > getMenuActualizadoIf().getFavorito()){
						menusModuloIf.setFavorito(menusModuloIf.getFavorito() - 1);
						menusVector.add(menusModuloIf);
					}
				}
				
				SessionServiceLocator.getMenuSessionService().eliminarMenu(menuIf.getId(), menusVector);
				SpiritAlert.createAlert("Menú eliminado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	public boolean estaEnRol(){
		try {		
			MenuIf menuIf = (MenuIf) getMenuVector().get(getMenuSeleccionado());
			Collection rolOpcion = SessionServiceLocator.getRolOpcionSessionService().findRolOpcionByMenuId(menuIf.getId());
		
			if(!rolOpcion.isEmpty()){
				SpiritAlert.createAlert("Este menú está asignado a un Rol, no puede ser modificado!", SpiritAlert.WARNING);
				return true;
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
				
		return false;
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbModulo().removeAllItems();
		getCmbMenu().removeAllItems();
		getTxtPanel().setText("");
		
		DefaultTableModel modelTblMenu = (DefaultTableModel)getTblMenu().getModel();
		for(int i = getTblMenu().getRowCount(); i > 0; --i){
			modelTblMenu.removeRow(i - 1);
		}
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		String strPanel = getTxtPanel().getText();
		
		Collection menu = null;
		boolean codigoRepetido = false;
		
		try {
			menu = SessionServiceLocator.getMenuSessionService().getMenuList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator menuIt = menu.iterator();
		
		while (menuIt.hasNext()) {
			MenuIf menuIf = (MenuIf) menuIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(menuIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(menuIf.getCodigo())) 
					if (getMenuActualizadoIf().getId().equals(menuIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Menú está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar el Código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		if (getCmbModulo().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe escoger un Módulo !!", SpiritAlert.WARNING);
			getCmbModulo().grabFocus();
			return false;
		}
		if(getCmbMenu().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe escoger un Menú !!", SpiritAlert.WARNING);
			getCmbMenu().grabFocus();
			return false;
		}
		if ((("".equals(strPanel)) || (strPanel == null))) {
			SpiritAlert.createAlert("Debe ingresar un Panel !!", SpiritAlert.WARNING);
			getTxtPanel().grabFocus();
			return false;
		}
				
		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public Vector getMenuVector() {
		return menuVector;
	}

	public void setMenuVector(Vector menuVector) {
		this.menuVector = menuVector;
	}
	
	public int getMenuSeleccionado() {
		return menuSeleccionado;
	}

	public void setMenuSeleccionado(int menuSeleccionado) {
		this.menuSeleccionado = menuSeleccionado;
	}
	
	public MenuIf getMenuActualizadoIf() {
		return menuIf;
	}

	public void setMenuActualizadoIf(MenuIf menuActualizadoIf) {
		this.menuIf = menuActualizadoIf;
	}
	 
}
