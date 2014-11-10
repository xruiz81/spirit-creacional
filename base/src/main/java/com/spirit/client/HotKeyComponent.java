package com.spirit.client;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritModelImpl;

 
 
public class HotKeyComponent {
	
	private JPanel panel;
	
	public HotKeyComponent(JPanel panel) {
		
		this.panel = panel;
		
		UselessAction actionSaveMode = new UselessAction("SET_SAVE_MODE", "action.1", KeyStroke.getKeyStroke("F1"));
		UselessAction actionFindMode = new UselessAction("SET_FIND_MODE", "action.2", KeyStroke.getKeyStroke("F2"));
		UselessAction actionFind = new UselessAction("FIND", "action.3", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
		UselessAction actionSave = new UselessAction("SAVE", "action.4", KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		UselessAction actionDelete = new UselessAction("DELETE", "action.5", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		UselessAction actionAddDetail = new UselessAction("ADD_DETAIL", "action.6", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		UselessAction actionUpdateDetail = new UselessAction("UPDATE_DETAIL", "action.7", KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		UselessAction actionCloseAllPanels = new UselessAction("CLOSE_ALL_PANELS", "action.8", KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		UselessAction actionRefresh = new UselessAction("REFRESH", "action.9", KeyStroke.getKeyStroke("F5"));
		UselessAction actionSwitchTab = new UselessAction("SWITCH_TAB", "action.10", KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		UselessAction actionQuickSearch = new UselessAction("QUICK_SEARCH", "action.11", KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		UselessAction actionDeleteDetail = new UselessAction("DELETE_DETAIL", "action.12", KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
				
		addAction(actionSaveMode);
		addAction(actionFindMode);
		addAction(actionFind);
		addAction(actionSave);
		addAction(actionDelete);
		addAction(actionAddDetail);
		addAction(actionUpdateDetail);
		addAction(actionCloseAllPanels);
		addAction(actionRefresh);
		addAction(actionSwitchTab);
		addAction(actionQuickSearch);
		addAction(actionDeleteDetail);
		
		
	}
	
	/*
	public KeyStroke claveKey(String codigo){
		
		String teclaNombre="";
		
		return KeyStroke.getKeyStroke(teclaNombre);
	}
	
	
	public static TeclasConfiguracionSessionService getTeclasConfiguracionSessionService() {
		try {
			return (TeclasConfiguracionSessionService) ServiceLocator.getService(ServiceLocator.TECLASCONFIGURACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}*/
	
	
	
	private void addAction(Action action) {
		Object command = action.getValue(Action.ACTION_COMMAND_KEY);
		KeyStroke stroke = (KeyStroke)action.getValue(Action.ACCELERATOR_KEY);
		panel.getInputMap(panel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(stroke, command);
		panel.getActionMap().put(command, action);
	}
	
	static class UselessAction extends AbstractAction {
		public UselessAction(String name, Object actionCommand, KeyStroke stroke) {
			putValue(Action.NAME, name);
			putValue(Action.ACTION_COMMAND_KEY, actionCommand);
			putValue(Action.ACCELERATOR_KEY, stroke);
		}
		
		public void actionPerformed(ActionEvent actionEvent) {
			/* * 	
			 	case 1:  ToolBarBuilder._btnSave.setEnabled(false); break;
			 	case 2:  ToolBarBuilder._btnDelete.setEnabled(false); break;
				case 3:  ToolBarBuilder._btnFind.setEnabled(false); break;
				case 4:  ToolBarBuilder._btnAuthorize.setEnabled(false); break;
				case 5:  ToolBarBuilder._btnPrint.setEnabled(false); break;
				case 6:  ToolBarBuilder._btnGenerateGraphic.setEnabled(false); break;
			 * */
			SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
			String si = "Sí"; 
			String no = "No"; 
			Object[] options = {si, no};
			
			if (panel != null) {
				// F1 - Nos cambia al modo SAVE
				if (getValue(Action.NAME).equals("SET_SAVE_MODE")) {
					if (MainFrameModel.get_btnSave().isEnabled()) {
						panel.setMode(SpiritMode.SAVE_MODE);
						panel.clean();
						new ChangeModeImpl().fireChangeModeEvent("MODO: SAVE");
						panel.showSaveMode();
					} else
						SpiritAlert.createAlert("Usted no tiene permiso para realizar esta acción!", SpiritAlert.WARNING);
					
					return;
				}

				// F2 - Nos cambia al modo FIND
				if (getValue(Action.NAME).equals("SET_FIND_MODE")) {
					if (MainFrameModel.get_btnFind().isEnabled()) {
						if (panel.getMode() != SpiritMode.FIND_MODE) {
							panel.setMode(SpiritMode.FIND_MODE);
							panel.clean();
							new ChangeModeImpl().fireChangeModeEvent("MODO: FIND");
							panel.showFindMode();
						} else {
							panel.find();
						}
					} else
						SpiritAlert.createAlert("Usted no tiene permiso para realizar esta acción!", SpiritAlert.WARNING);
					
					return;
				}
				
				// ENTER - En modo FIND busca registros
				if (getValue(Action.NAME).equals("FIND")) {
					if (MainFrameModel.get_btnFind().isEnabled()) {
						if (panel.getMode() == SpiritMode.FIND_MODE)
							panel.find();
					}
			
					return;
				}
				
				// CTRL + G - En modo SAVE ó UPDATE guarda registros
				if (getValue(Action.NAME).equals("SAVE")) {
					if (MainFrameModel.get_btnSave().isEnabled()) {
						if (panel.getMode() == SpiritMode.SAVE_MODE)
							panel.save();
					
						if (panel.getMode() == SpiritMode.UPDATE_MODE)
							panel.update();
					} else
						SpiritAlert.createAlert("Usted no tiene permiso para realizar esta acción!", SpiritAlert.WARNING);
					
					return;
				}
				
				// SUPR - En modo UPDATE elimina registros
				if (getValue(Action.NAME).equals("DELETE")) {
					if (MainFrameModel.get_btnDelete().isEnabled()) {
						if (panel.getMode() == SpiritMode.UPDATE_MODE) {
							int opcion = JOptionPane.showOptionDialog(null, "Está seguro que desea eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			    			if(opcion == JOptionPane.YES_OPTION){
								panel.delete();
								panel.setMode(SpiritMode.SAVE_MODE);
								new ChangeModeImpl().fireChangeModeEvent("MODO: SAVE");
							}
						} else
							SpiritAlert.createAlert("Seleccione primero el registro a eliminar", SpiritAlert.INFORMATION);
					} else
						SpiritAlert.createAlert("Usted no tiene permiso para realizar esta acción!", SpiritAlert.WARNING);
					
					return;
				}
				
				// CTRL + N - En modo SAVE ó UPDATE añade detalles en modelos MASTER-DETAIL
				if (getValue(Action.NAME).equals("ADD_DETAIL")) {
					panel.addDetail();
					return;
				}
				
				// CTRL + R - En modo SAVE ó UPDATE actualiza detalles en modelos MASTER-DETAIL
				if (getValue(Action.NAME).equals("UPDATE_DETAIL")) {
					panel.updateDetail();
					return;
				}
				
				// CTRL + D - En modo SAVE ó UPDATE elimina detalles en modelos MASTER-DETAIL
				if (getValue(Action.NAME).equals("DELETE_DETAIL")) {
					panel.deleteDetail();
					return;
				}
				
				// CTRL + F4 - Permite cerrar todos los paneles abiertos
				if (getValue(Action.NAME).equals("CLOSE_ALL_PANELS")) {
					List listaFrame = MainFrameModel.get_dockingManager().getAllFrameNames();					
					for (int i=0; i<listaFrame.size(); i++) {
						String frameName = listaFrame.get(i).toString();
						if (!frameName.equals("Lista Tareas") && !frameName.equals("Noticias") && !frameName.equals("Mensajes") && !frameName.equals("Background") && !frameName.equals("Modulos")){
							MainFrameModel.get_dockingManager().removeFrame(frameName);							
						}
					}					
					
					if (MainFrameModel.get_dockingManager().getFrame("Background") == null){
						MainFrameModel.get_dockingManager().addFrame(MainFrameModel.getPanelBackground());
					}
				}
				
				//F5 - ACTUALIZA LA INFORMACION
				if (getValue(Action.NAME).equals("REFRESH")) {
					panel.refresh();
					return;
				}
				
								
				
				//CTRL + Q - CAMBIA ENTRE TABS EN UN MISMO PANEL
				if (getValue(Action.NAME).equals("SWITCH_TAB")) {
					SpiritModelImpl panelImpl = (SpiritModelImpl) ActivePanel.getSingleton().getActivePanel();
					
					if (panelImpl != null) {
						panelImpl.switchTab();
					}
				}
				
				//CTRL + O - BÚSQUEDA RÁPIDA DE PANTALLAS
				if (getValue(Action.NAME).equals(("QUICK_SEARCH"))) {
					SpiritModelImpl panelImpl = (SpiritModelImpl) ActivePanel.getSingleton().getActivePanel();
					
					if (panelImpl != null) {
						panelImpl.quickSearch();
					}
				}
			} else
	    		JOptionPane.showMessageDialog(null, "No está activo ningún panel", "Información", JOptionPane.WARNING_MESSAGE);
		}
	}
}

