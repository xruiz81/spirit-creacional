package com.spirit.pos.client;


import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.spirit.client.ActivePanel;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.MainFrameModel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.TeclasConfiguracionIf;
import com.spirit.pos.session.TeclasConfiguracionSessionService;
import com.spirit.servicelocator.ServiceLocator;

 
 
public class HotKeyComponentPOS {
	private JPanel panel;
	private Map teclasConfiguracionMap = new HashMap();
	
	public HotKeyComponentPOS(JPanel panel) {
		this.panel = panel;
		mapearTeclasConfiguracion();
		
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
		
		UselessAction actionPagoEfectivo = new UselessAction("PEFECTIVO", "action.12", KeyStroke.getKeyStroke(tecla_pantalla("PEFECTIVO")));
		UselessAction actionPagoGiftCard = new UselessAction("PGIFTCARD", "action.13", KeyStroke.getKeyStroke(tecla_pantalla("PGIFTCARD")));
		UselessAction actionPagoTarjetaC = new UselessAction("PTARJCRED", "action.14", KeyStroke.getKeyStroke(tecla_pantalla("PTARJCRED")));
		UselessAction actionPagoCheque = new UselessAction("PCHEQUE", "action.15", KeyStroke.getKeyStroke(tecla_pantalla("PCHEQUE")));
		UselessAction actionPagoTarjetaD = new UselessAction("PTARJDEB", "action.16", KeyStroke.getKeyStroke(tecla_pantalla("PTARJDEB")));
		UselessAction actionPagoCreditoC = new UselessAction("PCREDCLIENTE", "action.40", KeyStroke.getKeyStroke(tecla_pantalla("PCREDCLIENTE")));
		
		UselessAction actionHistorialVentas = new UselessAction("HISTORIALVENTAS", "action.17", KeyStroke.getKeyStroke(tecla_pantalla("HISTORIALVENTAS")));
		UselessAction actionDonacionElegir = new UselessAction("DONACION", "action.18", KeyStroke.getKeyStroke(tecla_pantalla("DONACION")));	
		UselessAction actionTarjAfili = new UselessAction("TARJAFILI", "action.19", KeyStroke.getKeyStroke(tecla_pantalla("TARJAFILI")));
		UselessAction actionBloquearCaja = new UselessAction("BLOQUEARCAJA", "action.20", KeyStroke.getKeyStroke(tecla_pantalla("BLOQUEARCAJA")));
		UselessAction actionCancelarVenta = new UselessAction("CANCELARVENTA", "action.21", KeyStroke.getKeyStroke(tecla_pantalla("CANCELARVENTA")));
		UselessAction actionCrediCliente = new UselessAction("CREDCLIENTE", "action.22", KeyStroke.getKeyStroke(tecla_pantalla("CREDCLIENTE")));
		
		UselessAction actionBorrarItem = new UselessAction("BORRARITEM", "action.23", KeyStroke.getKeyStroke(tecla_pantalla("BORRARITEM")));
		UselessAction actionAbrirCajon = new UselessAction("ABRIRCAJON", "action.24", KeyStroke.getKeyStroke(tecla_pantalla("ABRIRCAJON")));
		UselessAction actionFacturar = new UselessAction("FACTURAR", "action.25", KeyStroke.getKeyStroke(tecla_pantalla("FACTURAR")));
		UselessAction actionAnticipo = new UselessAction("ANTICIPO", "action.26", KeyStroke.getKeyStroke(tecla_pantalla("ANTICIPO")));
		UselessAction actionNotaVenta = new UselessAction("NVENTA", "action.27", KeyStroke.getKeyStroke(tecla_pantalla("NVENTA")));
		UselessAction actionDevolucion = new UselessAction("DEV", "action.28", KeyStroke.getKeyStroke(tecla_pantalla("DEV")));
		UselessAction actionIngresarDatos = new UselessAction("INGRESODATOS", "action.29", KeyStroke.getKeyStroke(tecla_pantalla("INGRESODATOS")));

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
		addAction(actionIngresarDatos);
		addAction(actionPagoEfectivo);
		addAction(actionPagoGiftCard);
		addAction(actionPagoTarjetaC);
		addAction(actionPagoCheque);
		addAction(actionPagoTarjetaD);
		addAction(actionPagoCreditoC);
		addAction(actionBorrarItem);
		addAction(actionHistorialVentas);
		addAction(actionDonacionElegir);
		addAction(actionTarjAfili);
		addAction(actionBloquearCaja);
		addAction(actionCancelarVenta);
		addAction(actionCrediCliente);
		addAction(actionAbrirCajon);
		addAction(actionFacturar);
		addAction(actionDevolucion);
		addAction(actionAnticipo);
		addAction(actionNotaVenta);
	}

	public String tecla_pantalla(String codigoFuncion){
		String nombre_tecla = "";
		TeclasConfiguracionIf tecla_sel = findTeclasConfiguracionByCodigo(codigoFuncion);
		nombre_tecla = tecla_sel.getTeclasNombre();
		return nombre_tecla;
	}
	
	private TeclasConfiguracionIf findTeclasConfiguracionByCodigo(String codigoTeclaConfiguracion) {
		TeclasConfiguracionIf teclaConfiguracion = (TeclasConfiguracionIf) teclasConfiguracionMap.get(codigoTeclaConfiguracion);
		return teclaConfiguracion;
	}
	
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
				
				
				//F4 - INGRESAR DATOS
				if (getValue(Action.NAME).equals("INGRESODATOS")) {
					panel.pingresardatos();
					return;
				}
				
				//F6 - PAGO EN EFECTIVO
				if (getValue(Action.NAME).equals("PEFECTIVO")) {
					
					panel.cobroEfectivo();
					return;
				}
				
				//F7 - PAGO GIFT CARD
				if (getValue(Action.NAME).equals("PGIFTCARD")) {
					panel.cobroGiftCard();
					return;
				}
				//F - PAGO CHEQUE
				if (getValue(Action.NAME).equals("PCHEQUE")) {
					panel.cobroCheque();
					return;
				}
				
				//F - PAGO TARJ CRED
				if (getValue(Action.NAME).equals("PTARJCRED")) {
					panel.cobroTarjetaCredito();
					return;
				}
				//F - PAGO TARJ DEB
				if (getValue(Action.NAME).equals("PTARJDEB")) {
					panel.cobroTarjetaDebito();
					return;
				}
				
				//F - PAGO TARJ DEB
				if (getValue(Action.NAME).equals("PCREDCLIENTE")) {
					panel.cobroCredito();
					return;
				}
				////////////////////
				//F3 - BORRAR ITEM
				if (getValue(Action.NAME).equals("BORRARITEM")) {
					panel.borrarItem();
					return;
				}
				//F9 - HISTORIAL VENTAS
				if (getValue(Action.NAME).equals("HISTORIALVENTAS")) {
					panel.historialVentas();
					return;
				}
				//F10 - DONACION
				if (getValue(Action.NAME).equals("DONACION")) {
					panel.donacionElegir();
					return;
				}
				//F11 - TARJETA AFILIADOS
				if (getValue(Action.NAME).equals("TARJAFILI")) {
					panel.tarjetaAfiliado();
					return;
				}
				//F12 - BLOQUEAR CAJA
				if (getValue(Action.NAME).equals("BLOQUEARCAJA")) {
					panel.bloquearCaja();
					return;
				}
				//F13 - CANCELAR VENTA
				if (getValue(Action.NAME).equals("CANCELARVENTA")) {
					panel.cancelarVenta();
					return;
				}
				//F14 - CREDITO CLIENTE
				if (getValue(Action.NAME).equals("CREDCLIENTE")) {
					panel.creditoCliente();
					return;
				}
				//F30 - ABRIR CAJON
				if (getValue(Action.NAME).equals("ABRIRCAJON")) {
					panel.abrirCajon();
					return;
				}
				//PANEL DE TIPO DE TRANSACCIONES
				
				//F30 - ABRIR CAJON***
				if (getValue(Action.NAME).equals("FACTURAR")) {
					panel.t_facturar();
					return;
				}
				//F30 - ABRIR CAJON
				if (getValue(Action.NAME).equals("ANT")) {
					panel.t_anticipo();
					return;
				}
				//F30 - ABRIR CAJON
				if (getValue(Action.NAME).equals("DEV")) {
					panel.t_devolucion();
					return;
				}
				//F30 - ABRIR CAJON
				if (getValue(Action.NAME).equals("NVENTA")) {
					panel.t_nventa();
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
	
	private void mapearTeclasConfiguracion() {
		try {
			Iterator it = SessionServiceLocator.getTeclasConfiguracionSessionService().getTeclasConfiguracionList().iterator();
			while (it.hasNext()) {
				TeclasConfiguracionIf teclasConfiguracion = (TeclasConfiguracionIf) it.next();
				teclasConfiguracionMap.put(teclasConfiguracion.getCodigo(), teclasConfiguracion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear teclas de configuración", SpiritAlert.ERROR);
		}
	}
	 
}