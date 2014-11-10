package com.spirit.general.gui.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.event.DockableFrameEvent;
import com.jidesoft.docking.event.DockableFrameListener;
import com.spirit.client.ActivePanel;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.exceptions.SinPermisoPanelException;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.entity.RolUsuarioIf;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.seguridad.session.RolOpcionSessionService;
import com.spirit.seguridad.session.RolSessionService;
import com.spirit.seguridad.session.RolUsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class PanelHandler {
	public static DockableFrameListener dockableframelistener = new MiDockableFrameListener();
	private static final long serialVersionUID = 1457722870943372998L;
	private static Map menusMap = new HashMap();
	private static HashMap<String, SpiritModel> mapaPaneles = new HashMap<String, SpiritModel>();
	private static HashMap<String, String> mapaPanelName = new HashMap<String, String>();

	public static void clearCache() {
		mapaPaneles.clear();
		mapaPanelName.clear();
	}

	public static void showPanelModel(String modelName) {
		try {
			mapearMenus();
			SpiritModel model = mapaPaneles.get(mapaPanelName.get(modelName));
			if (model == null) {
				model = (SpiritModel) Class.forName(modelName).newInstance();
				String panelName = ((JPanel) model).getName();
				mapaPanelName.put(modelName, panelName);
				mapaPaneles.put(panelName, model);
			} else {
				System.out.println("Cache de Paneles");
			}
			
			showPanelModel(model);
		} catch (Exception ex) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			ex.printStackTrace();
		}

	}

	public static void showPanelModel(SpiritModel newPanel) {
		try {
			mapearMenus();
			Set<DockableFrame> valueSet = MainFrameModel.get_panels()
					.entrySet();

			String panelName = ((JPanel) newPanel).getName();
			boolean teniaMaximizado = false;
			DockableFrame activeFrame = MainFrameModel.get_dockingManager()
					.getMaximizedFrame();
			if (activeFrame != null) {
				activeFrame.getMaximizeAction().actionPerformed(null);
				teniaMaximizado = true;
			}
			DockableFrame panel = null;
			if (MainFrameModel.get_panels().size() > 0
					&& MainFrameModel.get_panels().get(panelName) != null) {
				panel = MainFrameModel.get_dockingManager().getFrame(panelName);
				MainFrameModel.get_dockingManager().showFrame(panelName);
			} else {
				panel = createPanelesApp(newPanel);
				panel.setAvailableButtons(panel.getAvailableButtons()
						| DockableFrame.BUTTON_MAXIMIZE);
				MainFrameModel.get_dockingManager().addFrame(panel);
				MainFrameModel.get_dockingManager().showFrame(panel.getName());

			}
			if (teniaMaximizado) {
				panel.getMaximizeAction().actionPerformed(null);
			}
			setPanelNameTitleBar(newPanel);
			setMainFrameToolBarState(newPanel);

		} catch (SinPermisoPanelException ex) {
			SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.ERROR);
		} catch (GenericBusinessException ex) {
			SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.ERROR);
			ex.printStackTrace();
		} catch (Exception ex) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			ex.printStackTrace();
		}
	}

	public static void setMainFrameToolBarState(SpiritModel newPanel)
			throws GenericBusinessException {
		String panelName = ((JPanel) newPanel).getName();
		UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
		//Iterator rolesUsuarioIterator = getRolUsuarioSessionService()
		//		.findRolUsuarioByUsuarioId(usuario.getId()).iterator();
		MenuIf menu = null;
		if (!panelName.toUpperCase().equals("CARTERA")
				&& !panelName.toUpperCase().equals("EMPRESA")
				&& !panelName.toUpperCase().equals("POS"))
			menu = (MenuIf) findMenuByNombre(panelName.toUpperCase());
		else {
			int nivel = 3;
			/*
			 * Map parameterMap = new HashMap(); parameterMap.put("nombre",
			 * panelName.toUpperCase()); parameterMap.put("nivel", nivel);
			 */
			Iterator menuIterator = findMenuByNombreAndNivel(
					panelName.toUpperCase(), nivel, false).iterator();
			if (menuIterator.hasNext())
				menu = (MenuIf) menuIterator.next();
		}

//		while (rolesUsuarioIterator.hasNext()) {
//			RolUsuarioIf rolUsuario = (RolUsuarioIf) rolesUsuarioIterator
//					.next();
			//Parametros.getRol
//			RolIf rol = getRolSessionService().getRol(rolUsuario.getRolId());

			if (menu != null) {
				List<RolOpcionIf> listaOpcion= (ArrayList<RolOpcionIf>)getRolOpcionSessionService()
						.findRolOpcionByMenuAndUsuarioId(usuario.getId(), menu.getId());

				for (RolOpcionIf rolOpcion:listaOpcion) {
					if (rolOpcion.getGrabarActualizar().equals("N"))
						MainFrameModel.get_btnSave().setEnabled(false);
					else
						MainFrameModel.get_btnSave().setEnabled(true);

					if (rolOpcion.getBorrar().equals("N"))
						MainFrameModel.get_btnDelete().setEnabled(false);
					else
						MainFrameModel.get_btnDelete().setEnabled(true);

					if (rolOpcion.getConsultar().equals("N"))
						MainFrameModel.get_btnFind().setEnabled(false);
					else
						MainFrameModel.get_btnFind().setEnabled(true);

					if (rolOpcion.getAutorizar().equals("N"))
						MainFrameModel.get_btnAuthorize().setEnabled(false);
					else
						MainFrameModel.get_btnAuthorize().setEnabled(true);

					if (rolOpcion.getImprimir().equals("N"))
						MainFrameModel.get_btnPrint().setEnabled(false);
					else
						MainFrameModel.get_btnPrint().setEnabled(true);

					if (rolOpcion.getGenerarGrafico().equals("N"))
						MainFrameModel.get_btnGenerateGraphic().setEnabled(
								false);
					else
						MainFrameModel.get_btnGenerateGraphic()
								.setEnabled(true);

					if (rolOpcion.getDuplicar().equals("N"))
						MainFrameModel.get_btnDuplicate().setEnabled(false);
					else
						MainFrameModel.get_btnDuplicate().setEnabled(true);
				}
			}
//		}
	}

	public static void setPanelNameTitleBar(SpiritModel newPanel)
			throws GenericBusinessException {
		if (newPanel != null) {
			Long padreId = 0L;
			String panelName = ((JPanel) newPanel).getName();
			if (!panelName.equals("Background")) {
				if (!panelName.toUpperCase().equals("CARTERA")
						&& !panelName.toUpperCase().equals("EMPRESA")
						&& !panelName.toUpperCase().equals("POS")) {
					mapearMenus();
					MenuIf menuIf = findMenuByNombre(panelName.toUpperCase());
					if (menuIf.getPanel() != null)
						padreId = menuIf.getPadreId();
				} else {
					int nivel = 3;
					if (panelName.toUpperCase().equals("EMPRESA"))
						nivel = 4;
					/*
					 * Map parameterMap = new HashMap();
					 * parameterMap.put("nombre", panelName.toUpperCase());
					 * parameterMap.put("nivel", nivel);
					 */
					Iterator menuIterator = findMenuByNombreAndNivel(
							panelName.toUpperCase(), nivel, false).iterator();
					while (menuIterator.hasNext()) {
						MenuIf menu = (MenuIf) menuIterator.next();
						if (!menu.getNombre().equals("EMPRESA")
								|| (menu.getNombre().equals("EMPRESA") && menu
										.getFavorito() != null)) {
							padreId = menu.getPadreId();
							break;
						}
					}
				}

				MenuIf padreIf = (MenuIf) menusMap.get(padreId);
				MenuIf abueloIf = (MenuIf) menusMap.get(padreIf.getPadreId());
				MenuIf bisabueloIf = null;
				if (abueloIf.getNivel() > 1)
					bisabueloIf = (MenuIf) menusMap.get(abueloIf.getPadreId());

				String nombrePadre = padreIf.getNombre().substring(0, 1)
						+ padreIf.getNombre().substring(1,
								padreIf.getNombre().length()).toLowerCase();
				String nombreAbuelo = abueloIf.getNombre().substring(0, 1)
						+ abueloIf.getNombre().substring(1,
								abueloIf.getNombre().length()).toLowerCase();
				String nombreBisabuelo = "";

				if (bisabueloIf != null)
					nombreBisabuelo = bisabueloIf.getNombre().substring(0, 1)
							+ bisabueloIf.getNombre().substring(1,
									bisabueloIf.getNombre().length())
									.toLowerCase() + " > ";

				if (MainFrameModel.get_panels().size() > 0
						&& MainFrameModel.get_panels().get(panelName) != null)
					MainFrameModel.get_frame().setTitle(
							"Spirit > " + nombreBisabuelo + nombreAbuelo
									+ " > " + nombrePadre + " > " + panelName);
				else
					MainFrameModel.get_frame().setTitle(
							"Spirit > " + nombreBisabuelo + nombreAbuelo
									+ " > " + nombrePadre + " > " + panelName);
			}
		}
	}

	public static DockableFrame createPanelesApp(SpiritModel newPanel) {
		String panelName = ((JPanel) newPanel).getName();
		String tipoPanel = "";
		Iterator panelMenuIterator;

		panelMenuIterator = findMenuByNombreAndNivel(panelName.toUpperCase(),
				3, true).iterator();
		if (panelMenuIterator.hasNext()) {
			MenuIf panelMenu = (MenuIf) panelMenuIterator.next();
			MenuIf menuPadre = (MenuIf) menusMap.get(panelMenu.getPadreId());
			if (panelMenu.getNivel() == 3)
				tipoPanel = menuPadre.getNombre();
			else if (panelMenu.getNivel() >= 4) {
				MenuIf menuAbuelo = (MenuIf) menusMap.get(menuPadre
						.getPadreId());
				tipoPanel = menuAbuelo.getNombre();
			}
		}

		MainFrameModel.get_panels().put(panelName, newPanel);

		String dockIcon = "dock_default.png";
		if (tipoPanel.equals("MANTENIMIENTO"))
			dockIcon = "dock_mantenimiento.png";
		else if (tipoPanel.equals("TRANSACCIONES"))
			dockIcon = "dock_transacciones.png";
		else if (tipoPanel.equals("CONSULTAS"))
			dockIcon = "dock_consultas.png";
		else if (tipoPanel.equals("REPORTES"))
			dockIcon = "dock_reportes.png";
		else if (tipoPanel.equals("AYUDA"))
			dockIcon = "dock_ayuda.png";

		DockableFrame frame = new DockableFrame(panelName,
				SpiritResourceManager.getImageIcon("images/icons/docking/"
						+ dockIcon));
		frame.getContext().setInitMode(DockContext.STATE_FRAMEDOCKED);
		frame.setName(panelName);
		frame.getContext().setInitSide(DockContext.DOCK_SIDE_CENTER);
		frame.getContext().setInitIndex(1);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add((JPanel) newPanel);
		// cada nuevo panel lo voy agregando uno al lado de otro según el número
		// de paneles activados
		frame.setPreferredSize(new Dimension((MainFrameModel.get_panels()
				.size() + 1) * 200, 200));
		frame.addDockableFrameListener(dockableframelistener);

		try {
			frame.setActive(true);
			ActivePanel.getSingleton().setActivePanel(newPanel);
			try {
				setPanelNameTitleBar(newPanel);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error",
						SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		if (!panelName.equals("Background"))
			MainFrameModel.get_dockingManager().removeFrame("Background");

		return frame;
	}

	/*
	 * Listener asigando a cada frame que contiene paneles de la aplicación nos
	 * permitirá conocer el panel activo en un momento determinado
	 */

	public static class MiDockableFrameListener implements
			DockableFrameListener {
		public void dockableFrameActivated(DockableFrameEvent arg0) {
			String panelName = ((DockableFrame) arg0.getSource()).getName();
			ActivePanel.getSingleton().setActivePanel(
					(SpiritModel) MainFrameModel.get_panels().get(panelName));
			try {
				setPanelNameTitleBar((SpiritModel) MainFrameModel.get_panels()
						.get(panelName));
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error",
						SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}

		public void dockableFrameAdded(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void dockableFrameAutohidden(DockableFrameEvent arg0) {

		}

		public void dockableFrameAutohideShowing(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void dockableFrameDeactivated(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub
		}

		public void dockableFrameDocked(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub
		}

		public void dockableFrameFloating(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub
			int $a = 0;

		}

		public void dockableFrameHidden(DockableFrameEvent arg0) {
			String frameNameToDelete = ((DockableFrame) arg0.getSource())
					.getName();
			System.out.println("source" + arg0.getSource());
			System.out.println("frameName" + frameNameToDelete);
			MainFrameModel.get_dockingManager().removeFrame(frameNameToDelete);
			MainFrameModel.get_panels().remove(frameNameToDelete);
			mapaPaneles.remove(frameNameToDelete);
			/*
			 * String si = "Sí"; String no = "No"; Object[] options = {si, no};
			 * if (!frameNameToDelete.equals("Background") &&
			 * !frameNameToDelete.equals("Lista Tareas") &&
			 * !frameNameToDelete.equals("Noticias") &&
			 * !frameNameToDelete.equals("Mensajes")){ int opcion =
			 * JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana " +
			 * frameNameToDelete + "?, se perderá la información que no haya
			 * sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION,
			 * JOptionPane.QUESTION_MESSAGE, null, options, no); if (opcion ==
			 * JOptionPane.YES_OPTION) {
			 * MainFrameModel.get_dockingManager().removeFrame(frameNameToDelete);
			 * MainFrameModel.get_panels().remove(frameNameToDelete);
			 * 
			 * }else{ DockableFrame panel = (DockableFrame) arg0.getSource();
			 * MainFrameModel.get_dockingManager().showFrame(panel.getName()); }
			 * List listaFrametemp =
			 * MainFrameModel.get_dockingManager().getAllFrameNames(); for (int
			 * i=0; i<listaFrametemp.size(); i++) { String frameName =
			 * listaFrametemp.get(i).toString(); if
			 * (!frameName.equals("Background") && !frameName.equals("Lista
			 * Tareas") && !frameName.equals("Noticias") &&
			 * !frameName.equals("Mensajes")){
			 * MainFrameModel.get_dockingManager().showFrame(frameName); } } }
			 */

			if (MainFrameModel.get_panels().size() == 0) {
				ActivePanel.getSingleton().setActivePanel(null);
				MainFrameModel.get_frame().setTitle("Spirit");
				MainFrameModel.get_btnSave().setEnabled(true);
				MainFrameModel.get_btnDelete().setEnabled(true);
				MainFrameModel.get_btnFind().setEnabled(true);
				MainFrameModel.get_btnAuthorize().setEnabled(true);
				MainFrameModel.get_btnPrint().setEnabled(true);
				MainFrameModel.get_btnGenerateGraphic().setEnabled(true);
				MainFrameModel.get_btnDuplicate().setEnabled(true);
			}

			if (MainFrameModel.get_panels().size() <= 0) {
				MainFrameModel.get_option().setVisible(false);
				MainFrameModel.get_dockingManager().addFrame(
						MainFrameModel.getPanelBackground());
				List listaFrame = MainFrameModel.get_dockingManager()
						.getAllFrameNames();
				showActivateFrame(listaFrame);
				MainFrameModel.get_dockingManager().getFrame("Background")
						.setShowTitleBar(false);
				MainFrameModel.get_dockingManager().getFrame("Background")
						.setTabDockAllowed(false);
				MainFrameModel.get_dockingManager().getFrame("Background")
						.setSideDockAllowed(false);
				MainFrameModel.get_dockingManager().getFrame("Background")
						.setShowGripper(false);
				ActivePanel.getSingleton().setActivePanel(
						(SpiritModel) MainFrameModel.get_panels().get(
								"Background"));
			} else {
				SpiritModel panel = (SpiritModel) ActivePanel.getSingleton()
						.getActivePanel();
				if (SpiritMode.SAVE_MODE == panel.getMode()) {
					MainFrameModel.get_option().setText("MODO: SAVE");
					MainFrameModel.get_option().setBackground(Color.GREEN);
				} else if (SpiritMode.FIND_MODE == panel.getMode()) {
					MainFrameModel.get_option().setText("MODO: FIND");
					MainFrameModel.get_option().setBackground(
							Parametros.findColor);
				} else if (SpiritMode.UPDATE_MODE == panel.getMode()) {
					MainFrameModel.get_option().setText("MODO: UPDATE");
					MainFrameModel.get_option().setBackground(Color.YELLOW);
				}
			}
		}

		public void dockableFrameMaximized(DockableFrameEvent arg0) {
			// MainFrameModel.get_dockingManager().

		}

		public void dockableFrameRemoved(DockableFrameEvent arg0) {
			List listaFrame = MainFrameModel.get_dockingManager()
					.getAllFrameNames();
			showActivateFrame(listaFrame);
		}

		private void showActivateFrame(List listaFrame) {
			MainFrameModel.get_dockingManager().showFrame(
					listaFrame.get(listaFrame.size() - 1).toString());
			MainFrameModel.get_dockingManager().activateFrame(
					listaFrame.get(listaFrame.size() - 1).toString());
		}

		public void dockableFrameRestored(DockableFrameEvent arg0) {
			// TODO Auto-generated method stub
		}

		public void dockableFrameShown(DockableFrameEvent arg0) {

		}

		public void dockableFrameTabHidden(DockableFrameEvent arg0) {
		}

		public void dockableFrameTabShown(DockableFrameEvent arg0) {
			String panelName = ((DockableFrame) arg0.getSource()).getName();
			ActivePanel.getSingleton().setActivePanel(
					(SpiritModel) MainFrameModel.get_panels().get(panelName));
			try {
				setPanelNameTitleBar((SpiritModel) MainFrameModel.get_panels()
						.get(panelName));
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error",
						SpiritAlert.ERROR);
				e.printStackTrace();
			}

			// Veo que panel se ha activado para segun los roles de usuario que
			// tenga se habiliten las funciones de la Barra Estandar
			habilitarFuncionesStandardBar(panelName);
			SpiritModel panel = (SpiritModel) ActivePanel.getSingleton()
					.getActivePanel();
			if (panel != null) {
				if (panel.getMode() == SpiritMode.SAVE_MODE) {
					MainFrameModel.get_option().setText("MODO: SAVE");
					MainFrameModel.get_option().setBackground(Color.GREEN);
				}
				if (panel.getMode() == SpiritMode.FIND_MODE) {
					MainFrameModel.get_option().setText("MODO: FIND");
					MainFrameModel.get_option().setBackground(
							Parametros.findColor);
				}
				if (panel.getMode() == SpiritMode.UPDATE_MODE) {
					MainFrameModel.get_option().setText("MODO: UPDATE");
					MainFrameModel.get_option().setBackground(Color.YELLOW);
				}
			}
		}

	}

	// Método que me permite habilitar las fucniones del panel que esta activo
	// segun los roles del usuario
	public static void habilitarFuncionesStandardBar(String panelActivo) {
		if (Parametros.get_SubMenusUsuarioByRoles().get(
				panelActivo.toUpperCase()) != null) {
			// Obtengo el objeto subMenu segun el panel seleccionado
			MenuIf subMenuIf = (MenuIf) Parametros.get_SubMenusUsuarioByRoles()
					.get(panelActivo.toUpperCase());
			// Obtengo un mapa con las funciones de este panel
			Map funcionesMap = (LinkedHashMap) Parametros
					.getFuncionesSubMenuByRoles().get(subMenuIf.getCodigo());
			MainFrameModel.get_btnSave().setEnabled(
					(Boolean) funcionesMap.get("GRA"));
			MainFrameModel.get_btnDelete().setEnabled(
					(Boolean) funcionesMap.get("BOR"));
			MainFrameModel.get_btnFind().setEnabled(
					(Boolean) funcionesMap.get("CON"));
			MainFrameModel.get_btnAuthorize().setEnabled(
					(Boolean) funcionesMap.get("AUT"));
			MainFrameModel.get_btnPrint().setEnabled(
					(Boolean) funcionesMap.get("IMP"));
			MainFrameModel.get_btnGenerateGraphic().setEnabled(
					(Boolean) funcionesMap.get("GEN"));
			MainFrameModel.get_btnDuplicate().setEnabled(
					(Boolean) funcionesMap.get("DUP"));
		}
	}

	private static List findMenuByNombreAndNivel(String nombre, int nivel,
			boolean buscarMayores) {
		List menuList = new ArrayList();
		Iterator it = menusMap.keySet().iterator();
		while (it.hasNext()) {
			Long id = (Long) it.next();
			MenuIf menu = (MenuIf) menusMap.get(id);
			if (menu.getNombre().equals(nombre) && menu.getNivel() == nivel)
				menuList.add(menu);
			else if (buscarMayores && menu.getNombre().equals(nombre)
					&& menu.getNivel() > nivel)
				menuList.add(menu);
		}

		return menuList;
	}

	private static MenuIf findMenuByNombre(String nombre) {
		Iterator it = menusMap.keySet().iterator();
		MenuIf menu = null;
		while (it.hasNext()) {
			Long id = (Long) it.next();
			menu = (MenuIf) menusMap.get(id);
			if (menu.getNombre().equals(nombre))
				return menu;
		}

		return menu;
	}

	private static void mapearMenus() {

		if(menusMap.size()>0)
			return;
		try {

			Iterator menusIterator = getMenuSessionService().getMenuList()
					.iterator();
			while (menusIterator.hasNext()) {
				MenuIf menu = (MenuIf) menusIterator.next();
				menusMap.put(menu.getId(), menu);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Se ha producido un error al abrir el panel!",
					SpiritAlert.ERROR);
		}

	}

	public static MenuSessionService getMenuSessionService() {
		try {
			return (MenuSessionService) ServiceLocator
					.getService(ServiceLocator.MENUSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolUsuarioSessionService getRolUsuarioSessionService() {
		try {
			return (RolUsuarioSessionService) ServiceLocator
					.getService(ServiceLocator.ROLUSUARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolSessionService getRolSessionService() {
		try {
			return (RolSessionService) ServiceLocator
					.getService(ServiceLocator.ROLSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolOpcionSessionService getRolOpcionSessionService() {
		try {
			return (RolOpcionSessionService) ServiceLocator
					.getService(ServiceLocator.ROLOPCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
