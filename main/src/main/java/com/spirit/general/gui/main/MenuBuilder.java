package com.spirit.general.gui.main;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jidesoft.action.CommandBar;
import com.jidesoft.action.CommandBarFactory;
import com.jidesoft.action.CommandMenuBar;
import com.jidesoft.action.DockableBarContext;
import com.jidesoft.swing.JideMenu;
import com.jidesoft.utils.SystemInfo;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.general.gui.controller.JDAcercaDeModel;
import com.spirit.general.gui.main.MainFrame.MenuActionListener;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class MenuBuilder extends CommandBarFactory {
	String nombreModulo,paquete;
	Map existeOpcion = new HashMap();
	boolean tieneElementos = false;
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no}; 
	
	CommandBar buildMenuBar(Container container, int p_modulo, String nombreModulo,ActionListener menuListener) {
		//Setel el nombre del modulo en que me encuentro
		this.nombreModulo = nombreModulo.trim();
		//Vacio el mapa que contiene los submnenus de cada modulo
		existeOpcion.clear();
		CommandBar bar = new CommandMenuBar("Menu Principal");
		bar.setInitSide(DockableBarContext.DOCK_SIDE_NORTH);
		bar.setInitMode(DockableBarContext.STATE_HORI_DOCKED);
		bar.setInitIndex(0);
		bar.add(buildMantenimientoMenu(p_modulo, menuListener));
		bar.add(buildTransaccionesMenu(p_modulo, menuListener));
		//bar.add(buildAutorizacionesMenu(p_modulo, menuListener));
		bar.add(buildConsultasMenu(p_modulo, menuListener));
		bar.add(buildReportesMenu(p_modulo, menuListener));
		JMenu menuVentana = buildVentanaMenu(menuListener);
		bar.add(menuVentana);
		//menuVentana.add(createLookAndFeelMenu(container));
		menuVentana.add(createLookAndFeelMenu(menuVentana));
		bar.add(buildAyudaMenu(menuListener));
		// bar.add(buildAlignmentTestMenu());
		//bar.add(buildHelpMenu(helpActionListener, aboutActionListener));
		return bar;
	}
	
	private JMenu createLookAndFeelMenu(JMenu menuVentana) {
		JMenu lookAndFeelMenu = new JideMenu("Look and Feel");
		LinkedHashMap<String, String> lookAndFeelMap = new LinkedHashMap<String, String>();
		LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < lista.length; i++) {
		    lookAndFeelMap.put(lista[i].getClassName(), lista[i].getName());
		}
		lookAndFeelMap.put("com.jgoodies.looks.plastic.Plastic3DLookAndFeel", "Plastic 3D");
		lookAndFeelMap.put("com.jgoodies.looks.plastic.PlasticLookAndFeel", "Plastic");
		lookAndFeelMap.put("com.jgoodies.looks.plastic.PlasticXPLookAndFeel", "Plastic XP");
		lookAndFeelMap.put("com.jgoodies.looks.windows.WindowsLookAndFeel", "Windows");
		lookAndFeelMap.put("javax.swing.plaf.metal.MetalLookAndFeel", "Metal");
		lookAndFeelMap.put("com.seaglasslookandfeel.SeaGlassLookAndFeel", "Sea Glass");
		lookAndFeelMap.put("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel", "Aluminium");
		lookAndFeelMap.put("com.jtattoo.plaf.fast.FastLookAndFeel", "Fast");
		lookAndFeelMap.put("com.jtattoo.plaf.mcwin.McWinLookAndFeel", "McWin");
		lookAndFeelMap.put("com.jtattoo.plaf.smart.SmartLookAndFeel", "Smart");
		//lookAndFeelMap.put("ch.randelshofer.quaqua.QuaquaLookAndFeel", "Quaqua");
		lookAndFeelMap.put(UIManager.getSystemLookAndFeelClassName(), "System Default");
		Iterator<String> it = lookAndFeelMap.keySet().iterator();
		while (it.hasNext()) {
			final String lookAndFeel = it.next();
			if (!lookAndFeel.contains("Nimbus")) {
				JMenuItem lookAndFeelItem = new JMenuItem(lookAndFeelMap.get(lookAndFeel));
				lookAndFeelMenu.add(lookAndFeelItem);
				lookAndFeelItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fileName = "spirit.cfg";
						if (SystemInfo.isLinux() || SystemInfo.isUnix() || SystemInfo.isMacOSX() || SystemInfo.isAnyMac() || SystemInfo.isMacClassic())
							fileName = ".spirit";
						String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + fileName;
						File f = new File(filePath);
						try {
							FileWriter w = new FileWriter(f);
							BufferedWriter bw = new BufferedWriter(w);
							PrintWriter wr = new PrintWriter(bw);  
							wr.write(lookAndFeel);
							wr.close();
							bw.close();
						} catch(IOException ioe) {
							ioe.printStackTrace();
							SpiritAlert.createAlert("Se ha producido un error al crear el archivo de configuración " + fileName, SpiritAlert.ERROR);
						};
						int opcion = JOptionPane.showOptionDialog(null, "La nueva configuración del Look & Feel de Spirit estará disponible luego de reiniciar la aplicación.\n¿Desea cerrar Spirit ahora?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							Parametros.getMainFrame().setVisible(false);
				            Parametros.getMainFrame().dispose(); 
				            System.exit(0);
						}
					}
				});
			}
		}
		return lookAndFeelMenu;
	}
	
	public void changeLookAndFeel(String laf) {
		if (laf == null)
	        laf = UIManager.getSystemLookAndFeelClassName();
	    try {
	    	JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
	        UIManager.setLookAndFeel(laf);
	    } catch (UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	    SwingUtilities.updateComponentTreeUI(Parametros.getMainFrame());
	}

	//Añado un popup menu a las opciones del menu
	private void addPopupMenu(final JMenuItem item, final String modulo, final int posMenuItem){
		//	Creo Un PoPup Menu Para el Area de Mensajes
		final JPopupMenu oPopupMenu = new JPopupMenu();
	    
	    // Create and add a menu item
	    JMenuItem itemAgregar = new JMenuItem("Agregar a Favoritos");
	    itemAgregar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		//Hago visible los MenuItems seleccionados
	    		if (modulo.equals("Cartera"))
	    			ToolBarBuilder._spbtnCartera.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Facturacion"))
	    			ToolBarBuilder._spbtnFacturacion.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Medios"))
	    			ToolBarBuilder._spbtnMedios.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Inventario"))
	    			ToolBarBuilder._spbtnInventario.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Servicios"))
	    			ToolBarBuilder._spbtnServicios.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Productos y Servicios"))
	    			ToolBarBuilder._spbtnProductosServicios.getItem(posMenuItem).setVisible(true);
	    		//if (modulo.equals("Cuentas por Pagar"))
	    			//ToolBarBuilder._spbtnCuentasxPagar.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Nomina"))
	    			ToolBarBuilder._spbtnNomina.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Seguridad"))
	    			ToolBarBuilder._spbtnSeguridad.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Workflow"))
	    			ToolBarBuilder._spbtnWorkflow.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Contabilidad"))
	    			ToolBarBuilder._spbtnContabilidad.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("General"))
	    			ToolBarBuilder._spbtnGeneral.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("CRM"))
	    			ToolBarBuilder._spbtnCRM.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("Compras"))
	    			ToolBarBuilder._spbtnCompras.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("RRHH"))
	    			ToolBarBuilder._spbtnRRHH.getItem(posMenuItem).setVisible(true);
	    		if (modulo.equals("POS"))
	    			ToolBarBuilder._spbtnPOS.getItem(posMenuItem).setVisible(true);
	    		
	    		ToolBarBuilder.generarFavoritosXML(MainFrame.getFavoritosBar());
	    	}
	    });
	    oPopupMenu.add(itemAgregar);
		
		// Set the component to show the popup menu
		item.addMouseListener(new MouseAdapter() {
	        public void mouseReleased(MouseEvent evt) {
	            if (evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) {
	            	oPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });
	}
	
		
	// ===================================================================
	public JMenu buildMantenimientoMenu(int p_modulo,ActionListener menuListener) {
		tieneElementos = false;
		JMenu menu = new JideMenu("Mantenimiento");

		String codigoMenu = "";
		if (nombreModulo.equals("Cartera")){
			codigoMenu = "CAR100";
			paquete = "cartera";
		}
		if (nombreModulo.equals("Facturacion")){
			codigoMenu= "FAC100";
			paquete = "facturacion";
		}
		if (nombreModulo.equals("Medios")){
			codigoMenu= "MED100";
			paquete = "medios";
		}
		if (nombreModulo.equals("Inventario")){
			codigoMenu= "INV100";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Servicios")){
			codigoMenu= "INV100";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Productos y Servicios")){
			codigoMenu= "INV100";
			paquete = "inventario";
		}
		/*if (nombreModulo.equals("Cuentas por Pagar")){
			codigoMenu= "CXP100";
			paquete = "cuentasxpagar";
		}*/
		if (nombreModulo.equals("Nomina")){
			codigoMenu= "NOM100";
			paquete = "nomina";
		}
		if (nombreModulo.equals("Seguridad")){
			codigoMenu= "SEG100";
			paquete = "seguridad";
		}
		if (nombreModulo.equals("Workflow")){
			codigoMenu= "WOR100";
			paquete = "workflow";
		}
		if (nombreModulo.equals("Contabilidad")){
			codigoMenu= "CON100";
			paquete = "contabilidad";
		}
		if (nombreModulo.equals("General")){
			codigoMenu= "GEN100";
			paquete = "general";
		}
		if (nombreModulo.equals("CRM")){
			codigoMenu= "CRM100";
			paquete = "crm";
		}
		if (nombreModulo.equals("Compras")){
			codigoMenu= "COM100";
			paquete = "compras";
		}
		if (nombreModulo.equals("RRHH")){
			codigoMenu= "RRH100";
			paquete = "rrhh";
		}
		if (nombreModulo.equals("BPM")){
			codigoMenu= "BPM100";
			paquete = "bpm";
		}
		if (nombreModulo.equals("SRI")){
			codigoMenu= "SRI100";
			paquete = "sri";
		}

		//JOHANNA
		if (nombreModulo.equals("POS")){
			codigoMenu= "POS100";
			paquete = "pos";
		}
		
		
		//Veo si el Existe para este modulo... si es asi cargo sus submenus en el framework
		if (ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)!=null) {
			//Obtengo todos los items del mantenimiento de este modulo
			Collection items = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)).values();
			Iterator itItems = items.iterator();
			
			while (itItems.hasNext()) {
				//Extraigo la instancia del submenu
				MenuIf subMenuIf = (MenuIf) itItems.next();
				Collection itemsSubmenuCategorizado = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(subMenuIf.getCodigo())).values();
				Iterator itItemsSubmenuCategorizado = itemsSubmenuCategorizado.iterator();
				//Extraigo el nombre el cual va a ser mostrado en el framework
				String nombreSubMenu = Utilitarios.toTitleCase(subMenuIf.getNombre());
				
				if (!itItemsSubmenuCategorizado.hasNext()) {
					//Creo el item Menu
					JMenuItem item = createMenuItem(nombreSubMenu);
					//Seteo el nombre del item para cargar el panel
					item.setName(subMenuIf.getPanel());
					//Añado un listenner al menuItem
					item.addActionListener(menuListener);
					//Le añado el popup menu el cual sirve para agregar elmenu  a la barra de favoritos
					if (subMenuIf.getFavorito() != null)
						addPopupMenu(item,nombreModulo,subMenuIf.getFavorito());
					//Añado el menuItem al Menu
					
					menu.add(item);
				} else {
					JMenu subMenu = new JideMenu(nombreSubMenu);
					menu.add(subMenu);
					JMenuItem itemCategorizado;
					
					while (itItemsSubmenuCategorizado.hasNext()) {
						MenuIf subMenuCategorizadoIf = (MenuIf) itItemsSubmenuCategorizado.next();		
						if (subMenuCategorizadoIf.getFavorito() != null) {
							//Extraigo el nombre el cual va a ser mostrado en el framework
							String nombreSubMenuCategorizado = Utilitarios.toTitleCase(subMenuCategorizadoIf.getNombre());
							//Creo el item Menu
							itemCategorizado = createMenuItem(nombreSubMenuCategorizado);
							//Seteo el nombre del item para cargar el panel
							itemCategorizado.setName(subMenuCategorizadoIf.getPanel());
							//Añado un listenner al menuItem
							itemCategorizado.addActionListener(new MenuActionListener());
							//Le añado el popup menu el cual sirve para agregar elmenu  a la barra de favoritos
							if (subMenuIf.getFavorito() != null)
								addPopupMenu(itemCategorizado,nombreModulo,subMenuCategorizadoIf.getFavorito());
							//Añado el menuItem al Menu
							subMenu.add(itemCategorizado);
						}
					}
				}
			}
			
			if(items.size()>0) {
				tieneElementos = true;
			}
		}
		//Veo si hay submenus en el menu para mostrarlo en el framework 
		menu.setEnabled(tieneElementos);
		menu.setVisible(true);
		menu.setMnemonic(KeyEvent.VK_M);
		
		return menu;	
	}

	// ==================================================================
	private JMenu buildTransaccionesMenu(int p_modulo,ActionListener menuListener) {
		JMenuItem item;
		tieneElementos = false;
		JMenu menu = new JideMenu("Transacciones");
		

		String codigoMenu = "";
		if (nombreModulo.equals("Cartera")){
			codigoMenu = "CAR200";
			paquete = "cartera";
		}
		if (nombreModulo.equals("Facturacion")){
			codigoMenu= "FAC200";
			paquete = "facturacion";
		}
		if (nombreModulo.equals("Medios")){
			codigoMenu= "MED200";
			paquete = "medios";
		}
		if (nombreModulo.equals("Inventario")){
			codigoMenu= "INV200";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Servicios")){
			codigoMenu= "INV200";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Productos y Servicios")){
			codigoMenu= "INV200";
			paquete = "inventario";
		}
		/*if (nombreModulo.equals("Cuentas por Pagar")){
			codigoMenu= "CXP200";
			paquete = "cuentasxpagar";
		}*/
		if (nombreModulo.equals("Nomina")){
			codigoMenu= "NOM200";
			paquete = "nomina";
		}
		if (nombreModulo.equals("Seguridad")){
			codigoMenu= "SEG200";
			paquete = "seguridad";
		}
		if (nombreModulo.equals("Workflow")){
			codigoMenu= "WOR200";
			paquete = "workflow";
		}
		if (nombreModulo.equals("Contabilidad")){
			codigoMenu= "CON200";
			paquete = "contabilidad";
		}
		if (nombreModulo.equals("General")){
			codigoMenu= "GEN200";
			paquete = "general";
		}
		if (nombreModulo.equals("CRM")){
			codigoMenu= "CRM200";
			paquete = "crm";
		}
		if (nombreModulo.equals("Compras")){
			codigoMenu= "COM200";
			paquete = "compras";
		}
		if (nombreModulo.equals("RRHH")){
			codigoMenu= "RRH200";
			paquete = "rrhh";
		}
		if (nombreModulo.equals("BPM")){
			codigoMenu= "BPM200";
			paquete = "bpm";
		}
		if (nombreModulo.equals("SRI")){
			codigoMenu= "SRI200";
			paquete = "sri";
		}
		
		if (nombreModulo.equals("POS")){
			codigoMenu= "POS200";
			paquete = "pos";
		}
		
		//Veo si el Existe para este modulo... si es asi cargo sus submenus enel framework
		if(ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)!=null){
			//Obtengo todos los items del mantenimiento de este modulo
			Collection items = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)).values();
			Iterator itItems = items.iterator();
			
			while(itItems.hasNext()){
				//Extraigo la instancia del submenu
				MenuIf subMenuIf = (MenuIf) itItems.next();
				//Extriago el nombre el cual va a ser mostradoen el framework
				String nombreSubMenu = Utilitarios.toTitleCase(subMenuIf.getNombre());
				//Creo el item Menu
				item = createMenuItem(nombreSubMenu);
				//Seteo el nombre del item para cargar el panel
				item.setName(subMenuIf.getPanel());
				//Añado un listenner al menuItem
				item.addActionListener(menuListener);
				//Le añado el popup menu el cual sirve para agregar elmenu  a la bbara de favoritos
				addPopupMenu(item,nombreModulo,subMenuIf.getFavorito());
				//Añado el menuItem al Menu
				menu.add(item);
			}
			if(items.size()>0)
				tieneElementos = true;
		}
		//Veo si hay submenus en el menu para mostrarlo en el framework 
		//if(bandera)
		menu.setEnabled(tieneElementos);
		menu.setVisible(true);
		//else
			//menu.setVisible(false);
		
		menu.setMnemonic(KeyEvent.VK_T);
		
		return menu;
	}

	/*private JMenu buildAutorizacionesMenu(int p_modulo,ActionListener menuListener) {
		JMenuItem item;
		tieneElementos = false;
		JMenu menu = new JideMenu("Autorizaciones");
	
		String codigoMenu = "";
		if (nombreModulo.equals("Cartera")){
			codigoMenu = "CAR300";
			paquete = "cartera";
		}
		if (nombreModulo.equals("Facturacion")){
			codigoMenu= "FAC300";
			paquete = "facturacion";
		}
		if (nombreModulo.equals("Medios")){
			codigoMenu= "MED300";
			paquete = "medios";
		}
		if (nombreModulo.equals("Inventario")){
			codigoMenu= "INV300";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Cuentas por Pagar")){
			codigoMenu= "CXP300";
			paquete = "cuentasxpagar";
		}
		if (nombreModulo.equals("Nomina")){
			codigoMenu= "NOM300";
			paquete = "nomina";
		}
		if (nombreModulo.equals("Seguridad")){
			codigoMenu= "SEG300";
			paquete = "seguridad";
		}
		if (nombreModulo.equals("Workflow")){
			codigoMenu= "WOR300";
			paquete = "workflow";
		}
		if (nombreModulo.equals("Contabilidad")){
			codigoMenu= "CON300";
			paquete = "contabilidad";
		}
		if (nombreModulo.equals("General")){
			codigoMenu= "GEN300";
			paquete = "general";
		}
		if (nombreModulo.equals("CRM")){
			codigoMenu= "CRM300";
			paquete = "crm";
		}
		if (nombreModulo.equals("Compras")){
			codigoMenu= "COM300";
			paquete = "compras";
		}
		if (nombreModulo.equals("RRHH")){
			codigoMenu= "RRH300";
			paquete = "rrhh";
		}

		//Veo si el Existe para este modulo... si es asi cargo sus submenus enel framework
		if(ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)!=null){
			//Obtengo todos los items del mantenimiento de este modulo
			Collection items = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)).values();
			Iterator itItems = items.iterator();
			
			while(itItems.hasNext()){
				//Extraigo la instancia del submenu
				MenuIf subMenuIf = (MenuIf) itItems.next();
				//Extriago el nombre el cual va a ser mostradoen el framework
				String nombreSubMenu = Utilitarios.toTitleCase(subMenuIf.getNombre());
				//Creo el item Menu
				item = createMenuItem(nombreSubMenu);
				//Seteo el nombre del item para cargar el panel
				item.setName(subMenuIf.getPanel());
				//Añado un listenner al menuItem
				item.addActionListener(menuListener);
				//Le añado el popup menu el cual sirve para agregar elmenu  a la bbara de favoritos
				addPopupMenu(item,nombreModulo,subMenuIf.getFavorito());
				//Añado el menuItem al Menu
				menu.add(item);
			}
			if(items.size()>0)
				tieneElementos = true;
		}
		//Veo si hay submenus en el menu para mostrarlo en el framework 
		//if(bandera)
		menu.setEnabled(tieneElementos);
		menu.setVisible(true);
		//else
			//menu.setVisible(false);


		return menu;
	}*/

	// ===================================================================
	private JMenu buildConsultasMenu(int p_modulo, ActionListener menuListener) {
		JMenuItem item;
		tieneElementos = false;
		JMenu menu = new JideMenu("Consultas");

		String codigoMenu = "";
		if (nombreModulo.equals("Cartera")){
			codigoMenu = "CAR400";
			paquete = "cartera";
		}
		if (nombreModulo.equals("Facturacion")){
			codigoMenu= "FAC400";
			paquete = "facturacion";
		}
		if (nombreModulo.equals("Medios")){
			codigoMenu= "MED400";
			paquete = "medios";
		}
		if (nombreModulo.equals("Inventario")){
			codigoMenu= "INV400";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Servicios")){
			codigoMenu= "INV400";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Productos y Servicios")){
			codigoMenu= "INV400";
			paquete = "inventario";
		}
		/*if (nombreModulo.equals("Cuentas por Pagar")){
			codigoMenu= "CXP400";
			paquete = "cuentasxpagar";
		}*/
		if (nombreModulo.equals("Nomina")){
			codigoMenu= "NOM400";
			paquete = "nomina";
		}
		if (nombreModulo.equals("Seguridad")){
			codigoMenu= "SEG400";
			paquete = "seguridad";
		}
		if (nombreModulo.equals("Workflow")){
			codigoMenu= "WOR400";
			paquete = "workflow";
		}
		if (nombreModulo.equals("Contabilidad")){
			codigoMenu= "CON400";
			paquete = "contabilidad";
		}
		if (nombreModulo.equals("General")){
			codigoMenu= "GEN400";
			paquete = "general";
		}
		if (nombreModulo.equals("CRM")){
			codigoMenu= "CRM400";
			paquete = "crm";
		}
		if (nombreModulo.equals("Compras")){
			codigoMenu= "COM400";
			paquete = "compras";
		}
		if (nombreModulo.equals("RRHH")){
			codigoMenu= "RRH400";
			paquete = "rrhh";
		}
		if (nombreModulo.equals("BPM")){
			codigoMenu= "BPM400";
			paquete = "bpm";
		}
		if (nombreModulo.equals("SRI")){
			codigoMenu= "SRI400";
			paquete = "sri";
		}
		
		if (nombreModulo.equals("POS")){
			codigoMenu= "POS400";
			paquete = "pos";
		}
	
		//Veo si el Existe para este modulo... si es asi cargo sus submenus enel framework
		if(ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)!=null){
			//Obtengo todos los items del mantenimiento de este modulo
			Collection items = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)).values();
			Iterator itItems = items.iterator();
			
			while(itItems.hasNext()){
				//Extraigo la instancia del submenu
				MenuIf subMenuIf = (MenuIf) itItems.next();
				//Extriago el nombre el cual va a ser mostradoen el framework
				String nombreSubMenu = Utilitarios.toTitleCase(subMenuIf.getNombre());
				//Creo el item Menu
				item = createMenuItem(nombreSubMenu);
				//Seteo el nombre del item para cargar el panel
				item.setName(subMenuIf.getPanel());
				//Añado un listenner al menuItem
				item.addActionListener(menuListener);
				//Le añado el popup menu el cual sirve para agregar elmenu  a la bbara de favoritos
				addPopupMenu(item,nombreModulo,subMenuIf.getFavorito());
				//Añado el menuItem al Menu
				menu.add(item);
			}
			if(items.size()>0)
				tieneElementos = true;
		}
		//Veo si hay submenus en el menu para mostrarlo en el framework 
		//if(bandera)
		menu.setEnabled(tieneElementos);
		menu.setVisible(true);
		//else
			//menu.setVisible(false);		

		menu.setMnemonic(KeyEvent.VK_C);
    		
		return menu;
	}
	
	
	// ===================================================================
	private JMenu buildReportesMenu(int p_modulo, ActionListener menuListener) {
		JMenuItem item;
		tieneElementos = false;
		JMenu menu = new JideMenu("Reportes");

		String codigoMenu = "";
		if (nombreModulo.equals("Cartera")){
			codigoMenu = "CAR500";
			paquete = "cartera";
		}
		if (nombreModulo.equals("Facturacion")){
			codigoMenu= "FAC500";
			paquete = "facturacion";
		}
		if (nombreModulo.equals("Medios")){
			codigoMenu= "MED500";
			paquete = "medios";
		}
		if (nombreModulo.equals("Inventario")){
			codigoMenu= "INV500";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Servicios")){
			codigoMenu= "INV500";
			paquete = "inventario";
		}
		if (nombreModulo.equals("Productos y Servicios")){
			codigoMenu= "INV500";
			paquete = "inventario";
		}
		/*if (nombreModulo.equals("Cuentas por Pagar")){
			codigoMenu= "CXP500";
			paquete = "cuentasxpagar";
		}*/
		if (nombreModulo.equals("Nomina")){
			codigoMenu= "NOM500";
			paquete = "nomina";
		}
		if (nombreModulo.equals("Seguridad")){
			codigoMenu= "SEG500";
			paquete = "seguridad";
		}
		if (nombreModulo.equals("Workflow")){
			codigoMenu= "WOR500";
			paquete = "workflow";
		}
		if (nombreModulo.equals("Contabilidad")){
			codigoMenu= "CON500";
			paquete = "contabilidad";
		}
		if (nombreModulo.equals("General")){
			codigoMenu= "GEN500";
			paquete = "general";
		}
		if (nombreModulo.equals("CRM")){
			codigoMenu= "CRM500";
			paquete = "crm";
		}
		if (nombreModulo.equals("Compras")){
			codigoMenu= "COM500";
			paquete = "compras";
		}
		if (nombreModulo.equals("RRHH")){
			codigoMenu= "RRH500";
			paquete = "rrhh";
		}
		if (nombreModulo.equals("BPM")){
			codigoMenu= "BPM500";
			paquete = "bpm";
		}
		if (nombreModulo.equals("SRI")){
			codigoMenu= "SRI500";
			paquete = "sri";
		}
		//johanna
		/*if (nombreModulo.equals("POS")){
			codigoMenu= "POS500";
			paquete = "pos";
		}*/
		
		

		//Veo si el Existe para este modulo... si es asi cargo sus submenus enel framework
		if(ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)!=null){
			//Obtengo todos los items del mantenimiento de este modulo
			Collection items = ((LinkedHashMap) ParametrosIniciales.menusUsuarioByRoles.get(codigoMenu)).values();
			Iterator itItems = items.iterator();
			
			while(itItems.hasNext()){
				//Extraigo la instancia del submenu
				MenuIf subMenuIf = (MenuIf) itItems.next();
				//Extriago el nombre el cual va a ser mostradoen el framework
				String nombreSubMenu = Utilitarios.toTitleCase(subMenuIf.getNombre());
				//Creo el item Menu
				item = createMenuItem(nombreSubMenu);
				//Seteo el nombre del item para cargar el panel
				item.setName(subMenuIf.getPanel());
				//Añado un listenner al menuItem
				item.addActionListener(menuListener);
				//Le añado el popup menu el cual sirve para agregar elmenu  a la bbara de favoritos
				addPopupMenu(item,nombreModulo,subMenuIf.getFavorito());
				//Añado el menuItem al Menu
				menu.add(item);
			}
			if(items.size()>0)
				tieneElementos = true;
		}
		//Veo si hay submenus en el menu para mostrarlo en el framework 
		//if(bandera)
		menu.setEnabled(tieneElementos);
		menu.setVisible(true);
		//else
			//menu.setVisible(false);		

		menu.setMnemonic(KeyEvent.VK_R);
    		
		return menu;
	}
	
	// ===================================================================
	private JMenu buildAyudaMenu(ActionListener menuListener) {
		JMenuItem item;
		JMenu menu = new JideMenu("Ayuda");

		item = createMenuItem("Acerca de", 'D');
		item.setName("AcercaDe");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JDAcercaDeModel(Parametros.getMainFrame());
			}
		});
		menu.add(item);
		menu.setMnemonic(KeyEvent.VK_A);
		return menu;
	}
	
	private JMenu buildVentanaMenu(ActionListener menuListener) {
		JMenuItem item;
		JMenu menu = new JideMenu("Ventana");

		item = createMenuItem("Cerrar todas");
		item.setName("CerrarTodas");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List listaFrame = MainFrameModel.get_dockingManager().getAllFrameNames();
				
				for (int i=0; i<listaFrame.size(); i++) {
					String frameName = listaFrame.get(i).toString();
					if (!frameName.equals("Background") && !frameName.equals("Lista Tareas") && !frameName.equals("Noticias") && !frameName.equals("Mensajes") && !frameName.equals("Modulos")){
						//int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana " + frameName + "?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						//if (opcion == JOptionPane.YES_OPTION) {
							MainFrameModel.get_dockingManager().removeFrame(frameName);							
						//}						
					}
				}
			}
		});
		menu.add(item);
		menu.setMnemonic(KeyEvent.VK_V);
		return menu;
	}

	/**
	 * Builds and answers a menu with items that use a HTML text.
	 */
	// private JMenu buildAlignmentTestMenu() {
	// JMenu menu = new JideMenu("Alignment", 'A');
	//        
	// menu.add(createMenuItem("Menu item"));
	// menu.add(createMenuItem("Menu item with icon",
	// readImageIcon("refresh.gif")));
	// menu.addSeparator();
	// JMenu submenu = createSubmenu("Submenu");
	// menu.add(submenu);
	//
	// submenu = createSubmenu("Submenu with icon");
	// submenu.setIcon(readImageIcon("refresh.gif"));
	// menu.add(submenu);
	//        
	// return menu;
	// }

	// Factory Methods ********************************************

	/*private Comparator<MenuIf> menuByFavoritoSorter = new Comparator<MenuIf>() {
		public int compare(MenuIf menu1, MenuIf menu2) {
			return menu1.getFavorito().compareTo(menu2.getFavorito());
		}
	};*/
	
	protected JMenuItem createMenuItem(String text) {
		return new JMenuItem(text);
	}

	protected JMenuItem createMenuItem(String text, char mnemonic) {
		return new JMenuItem(text, mnemonic);
	}

	protected JMenuItem createMenuItem(String text, char mnemonic, KeyStroke key) {
		JMenuItem menuItem = new JMenuItem(text, mnemonic);
		menuItem.setAccelerator(key);
		return menuItem;
	}

	protected JMenuItem createMenuItem(String text, Icon icon) {
		return new JMenuItem(text, icon);
	}

	protected JMenuItem createMenuItem(String text, Icon icon, char mnemonic) {
		JMenuItem menuItem = new JMenuItem(text, icon);
		menuItem.setMnemonic(mnemonic);
		return menuItem;
	}

	protected JMenuItem createMenuItem(String text, Icon icon, char mnemonic,
			KeyStroke key) {
		JMenuItem menuItem = createMenuItem(text, icon, mnemonic);
		menuItem.setAccelerator(key);
		return menuItem;
	}

	protected JRadioButtonMenuItem createRadioButtonMenuItem(String text,
			boolean selected) {
		return new JRadioButtonMenuItem(text, selected);
	}

	protected JCheckBoxMenuItem createCheckBoxMenuItem(String text,
			boolean selected) {
		return new JCheckBoxMenuItem(text, selected);
	}

	// Subclass will override the following methods ***************************

	/**
	 * Checks and answers whether the quit action has been moved to an operating
	 * system specific menu, e.g. the OS X application menu.
	 * 
	 * @return true if the quit action is in an OS-specific menu
	 */
	protected boolean isQuitInOSMenu() {
		return false;
	}

	/**
	 * Checks and answers whether the about action has been moved to an
	 * operating system specific menu, e.g. the OS X application menu.
	 * 
	 * @return true if the about action is in an OS-specific menu
	 */
	protected boolean isAboutInOSMenu() {
		return false;
	}

	// Higher Level Factory Methods *****************************************

	/**
	 * Creates and answers a <code>JRadioButtonMenuItem</code> with the given
	 * enablement and selection state.
	 */
	// private JRadioButtonMenuItem createRadioItem(boolean enabled, boolean
	// selected) {
	// JRadioButtonMenuItem item = createRadioButtonMenuItem(
	// getToggleLabel(enabled, selected),
	// selected);
	// item.setEnabled(enabled);
	// item.addChangeListener(new ChangeListener() {
	// public void stateChanged(ChangeEvent e) {
	// JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
	// source.setText(getToggleLabel(source.isEnabled(), source.isSelected()));
	// }
	// });
	// return item;
	// }
	/**
	 * Creates and answers a <code>JCheckBoxMenuItem</code> with the given
	 * enablement and selection state.
	 */
	// private JCheckBoxMenuItem createCheckItem(boolean enabled, boolean
	// selected) {
	// JCheckBoxMenuItem item = createCheckBoxMenuItem(
	// getToggleLabel(enabled, selected),
	// selected);
	// item.setEnabled(enabled);
	// item.addChangeListener(new ChangeListener() {
	// public void stateChanged(ChangeEvent e) {
	// JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
	// source.setText(getToggleLabel(source.isEnabled(), source.isSelected()));
	// }
	// });
	// return item;
	// }
	/**
	 * Answers an appropriate label for the given enablement and selection
	 * state.
	 */
	protected String getToggleLabel(boolean enabled, boolean selected) {
		String prefix = enabled ? "Enabled" : "Disabled";
		String suffix = selected ? "Selected" : "Deselected";
		return prefix + " and " + suffix;
	}

	// Helper Code ************************************************************

	/**
	 * Looks up and answers an icon for the specified filename suffix.
	 */
	private ImageIcon readImageIcon(String filename) {
		URL url = getClass().getClassLoader().getResource("images/" + filename);
		return new ImageIcon(url);
	}

	/**
	 * Creates and answers a submenu labeled with the given text.
	 */
	// private JMenu createSubmenu(String text) {
	// JMenu submenu = new JMenu(text);
	// submenu.add(new JMenuItem("Item 1"));
	// submenu.add(new JMenuItem("Item 2"));
	// submenu.add(new JMenuItem("Item 3"));
	// return submenu;
	// }
}