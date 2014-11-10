package com.spirit.general.gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.jidesoft.action.CommandBar;
import com.jidesoft.action.CommandBarFactory;
import com.jidesoft.action.DockableBarContext;
import com.jidesoft.swing.JideButton;
import com.jidesoft.swing.JideSplitButton;
import com.spirit.client.MainFrameModel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.CalculatorHandler;
import com.spirit.general.gui.handler.AuthorizeHandler;
import com.spirit.general.gui.handler.CerrarSesionHandler;
import com.spirit.general.gui.handler.DeleteHandler;
import com.spirit.general.gui.handler.DuplicateHandler;
import com.spirit.general.gui.handler.FindHandler;
import com.spirit.general.gui.handler.GenerateGraphicHandler;
import com.spirit.general.gui.handler.NewHandler;
import com.spirit.general.gui.handler.PrintHandler;
import com.spirit.general.gui.handler.RefreshHandler;
import com.spirit.general.gui.handler.SaveHandler;
import com.spirit.general.gui.main.MainFrame.MenuActionListener;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class ToolBarBuilder extends CommandBarFactory {
	
	//Split Buttons
	static JideSplitButton _spbtnCartera;
	static JideSplitButton _spbtnFacturacion;
	static JideSplitButton _spbtnMedios;
	static JideSplitButton _spbtnInventario;
	static JideSplitButton _spbtnServicios;
	static JideSplitButton _spbtnProductosServicios;
	//static JideSplitButton _spbtnCuentasxPagar;
	static JideSplitButton _spbtnNomina;
	static JideSplitButton _spbtnSeguridad;
	static JideSplitButton _spbtnWorkflow;
	static JideSplitButton _spbtnContabilidad;
	static JideSplitButton _spbtnGeneral;
	static JideSplitButton _spbtnCRM;
	static JideSplitButton _spbtnCompras;
	static JideSplitButton _spbtnRRHH;
	static JideSplitButton _spbtnBPM;
	static JideSplitButton _spbtnSRI;
	//johanna
	static JideSplitButton _spbtnPOS;
	
	public static JideButton _btnNew;
	public static JideButton _btnFind;
	public static JideButton _btnSave;
	public static JideButton _btnDelete;
	public static JideButton _btnPrint;
	public static JideButton _btnAuthorize;
	public static JideButton _btnGenerateGraphic;
	public static JideButton _btnCalculadora;
	public static JideButton _btnActualizar;
	public static JideButton _btnDuplicate;
	public static JideButton _btnCerrarSesion;
	
	//Añado un popup menu a las opciones del menu
	private static void addPopupMenu(final JMenuItem item){
		//	Creo Un PoPup Menu Para el Area de Mensajes
		final JPopupMenu oPopupMenu = new JPopupMenu();
	    
	    // Create and add a menu item
	    JMenuItem itemQuitar = new JMenuItem("Quitar de Favoritos");
	    itemQuitar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    			item.setVisible(false);
	    			generarFavoritosXML(MainFrame.getFavoritosBar());
	    	}
	    });
	    oPopupMenu.add(itemQuitar);
		
		// Set the component to show the popup menu
		item.addMouseListener(new MouseAdapter() {
	        public void mouseReleased(MouseEvent evt) {
	            if (evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) {
	            	oPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });
	}
	
	public static CommandBar createStandardCommandBar() {
		CommandBar commandBar = new CommandBar("Standard");
		commandBar.setInitSide(DockableBarContext.DOCK_SIDE_NORTH);
		commandBar.setInitMode(DockableBarContext.STATE_HORI_DOCKED);
		commandBar.setInitIndex(2);
		
		_btnNew = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/newRecord.png"));
		_btnNew.addActionListener(new NewHandler());
		_btnNew.setToolTipText("Nuevo Registro");
		commandBar.add(_btnNew);
		commandBar.addSeparator();
		_btnFind = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/findRecord.png"));
		MainFrameModel.set_btnFind(_btnFind);
		_btnFind.addActionListener(new FindHandler());
		_btnFind.setToolTipText("Buscar Registro");
		commandBar.add(_btnFind);
		_btnDuplicate = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/duplicate.png"));
		MainFrameModel.set_btnDuplicate(_btnDuplicate);
		_btnDuplicate.addActionListener(new DuplicateHandler());
		_btnDuplicate.setToolTipText("Duplicar");
		commandBar.add(_btnDuplicate);
		_btnSave = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/saveRecord.png"));
		MainFrameModel.set_btnSave(_btnSave);
		_btnSave.addActionListener(new SaveHandler());
		_btnSave.setToolTipText("Guardar Registro");
		commandBar.add(_btnSave);
		_btnDelete = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteRecord.png"));
		MainFrameModel.set_btnDelete(_btnDelete);
		_btnDelete.addActionListener(new DeleteHandler());
		_btnDelete.setToolTipText("Eliminar Registro");
		commandBar.add(_btnDelete);
		commandBar.addSeparator();
		_btnActualizar = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/refresh.png"));
		_btnActualizar.addActionListener(new RefreshHandler());
		_btnActualizar.setToolTipText("Actualizar");
		commandBar.add(_btnActualizar);
		commandBar.addSeparator();
		_btnPrint = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/print.png"));
		MainFrameModel.set_btnPrint(_btnPrint);
		_btnPrint.addActionListener(new PrintHandler());
		_btnPrint.setToolTipText("Imprimir");
		commandBar.add(_btnPrint);
		_btnAuthorize = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/authorizeRecord.png"));
		//xruiz
		_btnAuthorize.setEnabled(false);
		MainFrameModel.set_btnAuthorize(_btnAuthorize);
		_btnAuthorize.addActionListener(new AuthorizeHandler());
		_btnAuthorize.setToolTipText("Autorizar");
		commandBar.add(_btnAuthorize);
		_btnGenerateGraphic = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/generateGraphic.png"));
		MainFrameModel.set_btnGenerateGraphic(_btnGenerateGraphic);
		_btnGenerateGraphic.addActionListener(new GenerateGraphicHandler());
		_btnGenerateGraphic.setToolTipText("Generar Gráfico");
		commandBar.add(_btnGenerateGraphic);
		commandBar.addSeparator();
		_btnCalculadora = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/calculadora.png"));
		_btnCalculadora.addActionListener(new CalculatorHandler(_btnCalculadora));
		_btnCalculadora.setToolTipText("Calculadora");
		commandBar.add(_btnCalculadora);
		commandBar.addSeparator();
		_btnCerrarSesion = createButton(SpiritResourceManager.getImageIcon("images/icons/funcion/exit.png"));
		_btnCerrarSesion.addActionListener(new CerrarSesionHandler());
		_btnCerrarSesion.setToolTipText("Cerrar Sesión");
		commandBar.add(_btnCerrarSesion);
		
		return commandBar;
	}
	
	
	
	protected static CommandBar createFavoritosCommandBar() {
        CommandBar commandBar = new CommandBar("Favoritos");
        commandBar.setInitSide(DockableBarContext.DOCK_SIDE_EAST);
        commandBar.setInitMode(DockableBarContext.STATE_HORI_DOCKED);
        commandBar.setInitIndex(2);
        
        try {
		
        	Collection modulos = SessionServiceLocator.getMenuSessionService().findMenuByNivel(1);
        	Iterator modulosIterator = modulos.iterator();
			
			/*------------------------------------------*
			 * Favorito CARTERA
			 *------------------------------------------*/
			       
	        _spbtnCartera = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/cartera.png");
	        
	        /*------------------------------------------*
			 * Favorito FACTURACIóN
			 *------------------------------------------*/
	        
	        _spbtnFacturacion = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/facturacion.png");
	        
	        /*------------------------------------------*
			 * Favorito MEDIOS
			 *------------------------------------------*/
	        	        
	        _spbtnMedios = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/medios.png");
	        
	        /*------------------------------------------*
			 * Favorito INVENTARIO
			 *------------------------------------------*/
	        
	        _spbtnInventario = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/inventario.png");
	        
	        /*------------------------------------------*
			 * Favorito CUENTAS x PAGAR
			 *------------------------------------------*/
	        
	        //_spbtnCuentasxPagar = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/cuentasxpagar.png");
	        
	        /*------------------------------------------*
			 * Favorito Nómina
			 *------------------------------------------*/
	        
	        _spbtnNomina = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/nomina.png");
	        
	        /*------------------------------------------*
			 * Favorito SEGURIDAD
			 *------------------------------------------*/
	        
	        _spbtnSeguridad = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/seguridad.png");
	        
	        /*------------------------------------------*
			 * Favorito WORKFLOW
			 *------------------------------------------*/
	        
	        _spbtnWorkflow = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/workflow.png");
	        
	        /*------------------------------------------*
			 * Favorito CONTABILIDAD
			 *------------------------------------------*/
	        
	        _spbtnContabilidad = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/contabilidad.png");
	        
	        /*------------------------------------------*
			 * Favorito GENERAL
			 *------------------------------------------*/
	        
	        _spbtnGeneral = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/general.png");
	        
	        /*------------------------------------------*
			 * Favorito CRM
			 *------------------------------------------*/
	        
	        _spbtnCRM = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/crm.png");
	        
	        /*------------------------------------------*
			 * Favorito COMPRAS
			 *------------------------------------------*/
	        
	        _spbtnCompras = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/compras.png");

	        /*------------------------------------------*
			 * Favorito RRHH
			 *------------------------------------------*/
	        
	        _spbtnRRHH = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/rrhh.png");
	        
	        /*------------------------------------------*
			 * Favorito BPM
			 *------------------------------------------*/
	        
	        _spbtnBPM = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/crm.png");
	        
	        /*------------------------------------------*
			 * Favorito SRI
			 *------------------------------------------*/
	        
	        _spbtnSRI = addItemsFavoriteMenu(commandBar, (MenuIf) modulosIterator.next(), "images/icons/toolbars/sri.png");
	        
	        
	        commandBar = leerFavoritosXML(commandBar);
	        
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		    
        return commandBar;
    }
	
	protected static void mostrarModulosFavoritosCommandBar(){
		Collection modulosUsuarioByRoles = ParametrosIniciales.modulosUsuarioByRoles.values();
		Iterator itModulosUsuarioByRoles = modulosUsuarioByRoles.iterator();
		
		while (itModulosUsuarioByRoles.hasNext()) {
			MenuIf moduloIf = (MenuIf) itModulosUsuarioByRoles.next();
			
			//Muestro el modulo en la Barra de Favoritos, segun los roles que tenga el usuario
			if(moduloIf.getNombre().equals("CARTERA"))
				_spbtnCartera.setVisible(true);
			if(moduloIf.getNombre().equals("FACTURACION"))
				_spbtnFacturacion.setVisible(true);
			if(moduloIf.getNombre().equals("MEDIOS"))
				_spbtnMedios.setVisible(true);
			if(moduloIf.getNombre().equals("INVENTARIO"))	
				_spbtnInventario.setVisible(true);
			//if(moduloIf.getNombre().equals("CUENTAS POR PAGAR"))
				//_spbtnCuentasxPagar.setVisible(true);
			if(moduloIf.getNombre().equals("NOMINA"))
				_spbtnNomina.setVisible(true);
			if(moduloIf.getNombre().equals("SEGURIDAD"))
				_spbtnSeguridad.setVisible(true);
			if(moduloIf.getNombre().equals("WORKFLOW"))
				_spbtnWorkflow.setVisible(true);
			if(moduloIf.getNombre().equals("CONTABILIDAD"))
				_spbtnContabilidad.setVisible(true);
			if(moduloIf.getNombre().equals("GENERAL"))
				_spbtnGeneral.setVisible(true);
			if(moduloIf.getNombre().equals("CRM"))
				_spbtnCRM.setVisible(true);	
			if(moduloIf.getNombre().equals("COMPRAS"))
				_spbtnCompras.setVisible(true);
			if(moduloIf.getNombre().equals("RRHH"))
				_spbtnRRHH.setVisible(true);
			if(moduloIf.getNombre().equals("BPM"))
				_spbtnBPM.setVisible(true);
			if(moduloIf.getNombre().equals("SRI"))
				_spbtnSRI.setVisible(true);
			
		
			if(moduloIf.getNombre().equals("POS"))
				_spbtnPOS.setVisible(true);
		}
	}
	
	private static JideSplitButton addItemsFavoriteMenu(CommandBar commandBar, MenuIf moduloIf, String rutaIcono) {
		
		JideSplitButton jideSPButton = new JideSplitButton();
		
		try {
			
			Collection items = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(moduloIf.getId());
			Iterator itemsIterator = items.iterator();		
			
			jideSPButton = (JideSplitButton) commandBar.add(createSplitButton(SpiritResourceManager.getImageIcon(rutaIcono)));
			jideSPButton.setToolTipText(moduloIf.getNombre());
			jideSPButton.setVisible(false);
			jideSPButton.addSeparator();
			
			if (items != null)
				while (itemsIterator.hasNext()) {
					MenuIf itemsIf = (MenuIf) itemsIterator.next();
					jideSPButton.add(new JMenuItem(itemsIf.getNombre()));
					jideSPButton.getItem(itemsIf.getFavorito()).setName(itemsIf.getPanel());
					jideSPButton.getItem(itemsIf.getFavorito()).addActionListener(new MenuActionListener());
					jideSPButton.getItem(itemsIf.getFavorito()).setVisible(false);
					addPopupMenu(jideSPButton.getItem(itemsIf.getFavorito()));
				}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return jideSPButton;
		
	}
	
	public static void generarFavoritosXML(CommandBar commandBar) {
		//	 Identify a carriage return character for each output line

		int iLf = 10;
		int i=0, j=0;
		String visible = "";

		char cLf = (char) iLf;	
		JideSplitButton jideSPButton = new JideSplitButton();
		JMenuItem jMenuItem = new JMenuItem();
		
	    try {
	        
	    	File favoritos_dir = new File("favoritos\\");

	    	if (!favoritos_dir.exists())
	    		favoritos_dir.mkdir();

	    	BufferedWriter outfile = new BufferedWriter(new FileWriter("favoritos\\"+ParametrosIniciales.getUsuario().toLowerCase()+".xml"));

	        outfile.write("<?xml version='1.0' encoding='ISO-8859-1'?>"+cLf);
	        
	        Collection modulosCollection = SessionServiceLocator.getMenuSessionService().findMenuByNivel(1);
	        Iterator modulosIterator = modulosCollection.iterator();
	        
			   
		    outfile.write("<FAVORITOS>"+cLf);

		    while (modulosIterator.hasNext()) {
		    	MenuIf moduloIf = (MenuIf) modulosIterator.next();
		    	Collection favoritosCollection = SessionServiceLocator.getMenuSessionService().findMenuByGrandFatherId(moduloIf.getId());
				Iterator favoritosIterator = favoritosCollection.iterator();
				outfile.write("<MODULO NAME = \""+ moduloIf.getNombre() +"\">"+cLf);
				j=0;
				while (favoritosIterator.hasNext()) {
			    	// Parse our recordset

			    	MenuIf itemIf = (MenuIf) favoritosIterator.next();
			    	jideSPButton = (JideSplitButton) commandBar.getComponent(i+3);
            		jMenuItem = (JMenuItem) jideSPButton.getPopupMenu().getComponent(j+1);
            		
			    	if (jMenuItem.isVisible())
			    		visible = "true";
			    	else
			    		visible = "false";
			    	
		            outfile.write("<ITEM VISIBLE = \"" + visible + "\">" +  itemIf.getNombre() + "</ITEM>"+cLf);
		            j++;
			      }

				outfile.write("</MODULO>"+cLf);
			    i++;   
		    }
		    
		    outfile.write("</FAVORITOS>"+cLf);
		      outfile.close();
		       
		} catch(Exception e) { 
		       System.out.println("Exception: "+e);
		} 
	}
	
	private static CommandBar leerFavoritosXML(CommandBar commandBar) {
		
		JideSplitButton jideSPButton = new JideSplitButton();
		JMenuItem jMenuItem = new JMenuItem();
		
		try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            boolean exists = (new File("favoritos\\"+ParametrosIniciales.getUsuario().toLowerCase()+".xml")).exists();
            if (exists) {
            Document doc = docBuilder.parse (new File("favoritos\\"+ParametrosIniciales.getUsuario().toLowerCase()+".xml"));

            // normalize text representation            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


            NodeList listOfModules = doc.getElementsByTagName("MODULO");
            int totalModules = listOfModules.getLength();
            //System.out.println("Total modulos : " + totalModules);
            
            int totalItems;
            
            for(int i=0; i<totalModules; i++){
                
            	Element moduleNode = (Element) listOfModules.item(i);
        		//System.out.println("Module : " + moduleNode.getAttribute("NAME").trim());
        		      		
                //if(moduleNode.getNodeType() == Node.ELEMENT_NODE){

                	NodeList listOfItemsModule = moduleNode.getElementsByTagName("ITEM");
                	totalItems = listOfItemsModule.getLength();
                	
                	for(int j=0; j<totalItems;j++) {
                		
                		Element itemNode = (Element) listOfItemsModule.item(j);
                		
                		//System.out.println("Item : " + itemNode.getTextContent() + " Visible : " + itemNode.getAttribute("VISIBLE"));
                		jideSPButton = (JideSplitButton) commandBar.getComponent(i+3);
                		jMenuItem = (JMenuItem) jideSPButton.getPopupMenu().getComponent(j+1);
                		
                		if (itemNode.getAttribute("VISIBLE").equals("true"))
                			jMenuItem.setVisible(true);
                		else if (itemNode.getAttribute("VISIBLE").equals("false"))
                			jMenuItem.setVisible(false);
                	//}

                }//end of if clause

            }//end of for loop with s var
            }

        } catch (SAXParseException err) {
        	System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
        	System.out.println(" " + err.getMessage ());
        	SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);

        }catch (SAXException e) {
        	SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
        	Exception x = e.getException ();
        	((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        	SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
        	t.printStackTrace ();
        }
			
        //System.exit (0);
        
        return commandBar;
    }//end of main	
	/*
	public static MenuSessionService getMenuSessionService() {
		try {
			return (MenuSessionService) ServiceLocator.getService(ServiceLocator.MENUSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}*/
}