package com.spirit.general.gui.main;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import com.l2fprod.common.swing.JOutlookBar;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.seguridad.entity.MenuIf;


public class AccessBar extends JPanel  {
	
	private JOutlookBar joutlookbar = new JOutlookBar();
	
	// Paneles de cada uno de los modulos de la aplicacion
	JPanel panelfacturacion = new JPanel();
	JPanel panelinventario = new JPanel();
	JPanel panelservicios = new JPanel();
	JPanel panelproductosservicios = new JPanel();
	JPanel panelcartera = new JPanel();
	JPanel panelmedios = new JPanel();
	JPanel panelnomina = new JPanel();
	JPanel panelcontabilidad = new JPanel();
	JPanel panelgeneral = new JPanel();
	//JPanel panelcuentasxpagar = new JPanel();
	JPanel panelseguridad = new JPanel();
	JPanel panelworkflow = new JPanel();
	JPanel panelcrm = new JPanel();
	JPanel panelcompras = new JPanel();
	JPanel panelrrhh = new JPanel();
	JPanel panelbpm = new JPanel();
	JPanel panelsri = new JPanel();
	
	JPanel panelpos = new JPanel();
	
	/**
	 * This method initializes 
	 * 
	 */
	public AccessBar(ActionListener BotonFrecuentesActionListener, ChangeListener PanelFrecuentesActionListener){
		joutlookbar.addChangeListener(PanelFrecuentesActionListener);
		initialize();			
	}
	
//	public AccessBar(ActionListener LoadPanelActionListenerIn, ChangeListener  LoadMenuActionListenerIn) {
//		this.LoadPanelActionListener = LoadPanelActionListenerIn;
//		this.LoadMenuActionListener = LoadMenuActionListenerIn;
//
//		AccessAccounting panelcontabilidad = new AccessAccounting(LoadPanelActionListener);
//		joutlookbar.addChangeListener(LoadMenuActionListener);
//		initialize(panelcontabilidad);	
//	}

	
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(133, 284);
	    this.setLayout(new BorderLayout()); 
	    //joutlookbar.setBounds(new Rectangle(10, 10, 125, 255));
		joutlookbar.setAutoscrolls(true);
		//Font beforeFont = joutlookbar.getFont();
		//beforeFont.deriveFont(Font.BOLD);
		//Font joutlookBarFont = new Font("Tahoma", Font.BOLD, 20);
		joutlookbar.setFont(new javax.swing.plaf.FontUIResource("Arial", java.awt.Font.BOLD, 12));
		//Font afterFont = joutlookbar.getFont();
	
		
		//Habilito el modulo encontrado en el framework
		Collection modulosUsuarioByRoles = ParametrosIniciales.modulosUsuarioByRoles.values();
		Iterator itModulosUsuarioByRoles = modulosUsuarioByRoles.iterator();
		
		while (itModulosUsuarioByRoles.hasNext()) {
			MenuIf moduloIf = (MenuIf) itModulosUsuarioByRoles.next();
			
			//Habilito el modulo encontrado en el framework
			if(moduloIf.getNombre().equals("CARTERA"))
				joutlookbar.addTab("Cartera            ",SpiritResourceManager.getImageIcon("images/icons/modulo/cartera.png"),panelcartera);
			if(moduloIf.getNombre().equals("FACTURACION"))
				joutlookbar.addTab("Facturacion        ",SpiritResourceManager.getImageIcon("images/icons/modulo/facturacion.png"),panelfacturacion);
			if(moduloIf.getNombre().equals("MEDIOS"))
				joutlookbar.addTab("Medios             ",SpiritResourceManager.getImageIcon("images/icons/modulo/medios.png"),panelmedios);
			if(moduloIf.getNombre().equals("INVENTARIO"))	
				joutlookbar.addTab("Inventario         ",SpiritResourceManager.getImageIcon("images/icons/modulo/inventario.png"),panelinventario);
			if(moduloIf.getNombre().equals("SERVICIOS"))
				joutlookbar.addTab("Servicios         ",SpiritResourceManager.getImageIcon("images/icons/modulo/inventario.png"),panelservicios);
			if(moduloIf.getNombre().equals("PRODUCTOS Y SERVICIOS"))
				joutlookbar.addTab("Productos y Servivios         ",SpiritResourceManager.getImageIcon("images/icons/modulo/inventario.png"),panelproductosservicios);
			if(moduloIf.getNombre().equals("NOMINA"))
				joutlookbar.addTab("Nomina             ",SpiritResourceManager.getImageIcon("images/icons/modulo/nomina.png"),panelnomina);
			if(moduloIf.getNombre().equals("SEGURIDAD"))
				joutlookbar.addTab("Seguridad          ",SpiritResourceManager.getImageIcon("images/icons/modulo/seguridad.png"),panelseguridad);
			if(moduloIf.getNombre().equals("CONTABILIDAD"))
				joutlookbar.addTab("Contabilidad       ",SpiritResourceManager.getImageIcon("images/icons/modulo/contabilidad.png"),panelcontabilidad);
			if(moduloIf.getNombre().equals("GENERAL"))
				joutlookbar.addTab("General            ",SpiritResourceManager.getImageIcon("images/icons/modulo/general.png"),panelgeneral);
			if(moduloIf.getNombre().equals("CRM"))
				joutlookbar.addTab("CRM                ",SpiritResourceManager.getImageIcon("images/icons/modulo/crm.png"),panelcrm);
			if(moduloIf.getNombre().equals("COMPRAS"))
				joutlookbar.addTab("Compras            ",SpiritResourceManager.getImageIcon("images/icons/modulo/compras.png"),panelcompras);
			if(moduloIf.getNombre().equals("RRHH"))
				joutlookbar.addTab("RRHH            ",SpiritResourceManager.getImageIcon("images/icons/modulo/rrhh.png"),panelrrhh);
			if(moduloIf.getNombre().equals("BPM"))
				joutlookbar.addTab("BPM                ",SpiritResourceManager.getImageIcon("images/icons/modulo/bpm.png"),panelbpm);
			if(moduloIf.getNombre().equals("SRI"))
				joutlookbar.addTab("SRI                ",SpiritResourceManager.getImageIcon("images/icons/modulo/sri.png"),panelsri);			
			if(moduloIf.getNombre().equals("POS"))
				joutlookbar.addTab("POS                ",SpiritResourceManager.getImageIcon("images/icons/modulo/pos.png"),panelpos);
			
			//if(moduloIf.getNombre().equals("CUENTAS POR PAGAR"))
				//joutlookbar.addTab("Cuentas por Pagar  ",SpiritResourceManager.getImageIcon("images/icons/modulo/cuentasxpagar.png"),panelcuentasxpagar);
			//if(moduloIf.getNombre().equals("WORKFLOW"))
				//joutlookbar.addTab("Workflow           ",SpiritResourceManager.getImageIcon("images/icons/modulo/workflow.png"),panelworkflow);
		}
		
		joutlookbar.validate();
	    this.add(joutlookbar,BorderLayout.CENTER);
	}
} 

