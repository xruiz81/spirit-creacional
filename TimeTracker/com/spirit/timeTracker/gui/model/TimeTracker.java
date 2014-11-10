package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.createImageIcon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import com.jidesoft.status.LabelStatusBarItem;
import com.jidesoft.status.MemoryStatusBarItem;
import com.jidesoft.status.TimeStatusBarItem;
import com.spirit.client.Parametros;
import com.spirit.server.StandAloneServer;
import com.spirit.timeTracker.gui.main.JFTimeTracker;

public class TimeTracker extends JFTimeTracker {

	static int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private static final long serialVersionUID = 1L;
	
	public LabelStatusBarItem _empresa;
	public LabelStatusBarItem _oficina;
	
	public TimeTracker(){
		setTitle("TimeTracker");
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		//Parametros.setUsuario("eroncoroni");
    	//Parametros.setIdEmpresa(1L);
		
    	/*setLocation(
        		(anchoPantalla - getWidth()) / 3, 
        		(altoPantalla - getHeight()) / 3
        		);*/
    	setBounds(0, 0, anchoPantalla, altoPantalla-50);
    	setIconImage(
    			createImageIcon("images/timetracker/timeTracker.png").getImage());

    	initListeners();
		iniciarComponentes();
		iniciarBotones();
		//pack();
	}
	
	private void iniciarBotones(){
		//BOTONESsetToolTipText
		//SubTareaListener botonesActionListener = new SubTareaListener();

		getBtnGuardar().setText("");
		getBtnGuardar().setName("Guardar");
		getBtnGuardar().setIcon(new ImageIcon("images/timetracker/save.png"));
		getBtnGuardar().setToolTipText("Guardar");
		getBtnGuardar().addActionListener(Utiles.subtatareaListener);
		
		getBtnNuevaTarea().setText("");
		getBtnNuevaTarea().setName("nueva");
		getBtnNuevaTarea().setIcon(new ImageIcon("images/timetracker/addSubTaskBig.png"));
		getBtnNuevaTarea().setToolTipText("Nueva Subtarea");
		getBtnNuevaTarea().addActionListener(Utiles.subtatareaListener);
		//getBtnNuevaTarea().setEnabled(false);
		
		getBtnIniciarTarea().setText("");
		getBtnIniciarTarea().setName("iniciar");
		getBtnIniciarTarea().setIcon(new ImageIcon("images/timetracker/startBig.png"));
		getBtnIniciarTarea().setToolTipText("Iniciar subTarea");
		getBtnIniciarTarea().addActionListener(Utiles.subtatareaListener);
		//getBtnIniciarTarea().setEnabled(false);
		
		getBtnDetenerTarea().setText("");
		getBtnDetenerTarea().setName("parar");
		getBtnDetenerTarea().setIcon(new ImageIcon("images/timetracker/stopBig.png"));
		getBtnDetenerTarea().setToolTipText("Parar subtarea activa");
		getBtnDetenerTarea().addActionListener(Utiles.subtatareaListener);
		//getBtnDetenerTarea().setEnabled(false);
		
		getBtnEliminarTarea().setText("");
		getBtnEliminarTarea().setName("eliminar");
		getBtnEliminarTarea().setIcon(new ImageIcon("images/timetracker/deleteSubTaskBig.png"));
		getBtnEliminarTarea().setToolTipText("Eliminar subtarea");
		getBtnEliminarTarea().addActionListener(Utiles.subtatareaListener);
		//getBtnEliminarTarea().setEnabled(false);
	}
	
	private void iniciarComponentes(){
		crearPanelContenedor();
		crearStatusBar();
		crearMenu();
	}
	
	private void crearMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		//*******MENU ARCHIVO
		JMenu menu = new JMenu("Archivo");
		
		JMenuItem menuItem = new JMenuItem("Salir");
		menuItem.setIcon(createImageIcon("images/timetracker/exit.png"));
		menuItem.addActionListener(menuActionListener);
		menu.add(menuItem);
				
		menuBar.add(menu);
		//*********************
		
		setJMenuBar(menuBar);
	}
	
	private void crearPanelContenedor(){
		getContentPane().add(new PanelGeneralModel(),BorderLayout.CENTER);
	}
	
	private void crearStatusBar(){
		//getStatusBar().setAlignmentY(StatusBar.RIGHT_ALIGNMENT);
		if (!isSuperuser()) {
			/*_empresa =  new LabelStatusBarItem("Empresa");
			_empresa.setAlignment(0);
			_empresa.setFont(new Font("Arial", Font.PLAIN, 12));
			_empresa.setText("EMPRESA: " + Parametros.getNombreEmpresa());
			getStatusBar1().add(_empresa, "flexible");*/
			
			/*if (!isAdmin()) {
				_oficina = new LabelStatusBarItem("Oficina");
				_oficina.setAlignment(0);
				_oficina.setFont(new Font("Arial", Font.PLAIN, 12));
				_oficina.setText("OFICINA: " + Parametros.getNombreOficina());
				getStatusBar1().add(_oficina, "flexible");
			} */
		}
		LabelStatusBarItem _espacio = new LabelStatusBarItem("");
		_espacio.setAlignment(0);
		getStatusBar().add(_espacio,"vary");
		
		LabelStatusBarItem _usuario = new LabelStatusBarItem("Usuario");
		_usuario.setAlignment(0);
		_usuario.setFont(new Font("Arial", Font.PLAIN, 12));
		_usuario.setText("USUARIO: " + Parametros.getUsuario());
		getStatusBar().add(_usuario,"flexible");
		
		TimeStatusBarItem timeBar = new TimeStatusBarItem();
		getStatusBar().add(timeBar,"flexible");
		MemoryStatusBarItem gc = new MemoryStatusBarItem();
		getStatusBar().add(gc, "flexible");
	}
	
	private boolean isSuperuser() {
		//return "S".equals(SpiritLogin._usuario.getTipousuario());
		return false;
	}
	
	private boolean isAdmin() {
		//return "A".equals(SpiritLogin._usuario.getTipousuario());
		return false;
	}
	
	public static void main(String args[]){
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//StandAloneServer.start(null);
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SpiritLogin().setVisible(true);
				/*TimeTracker timeTracker = new TimeTracker();
            	Utiles.timeTrackerVentana = timeTracker;
            	timeTracker.setVisible(true);*/
            }
        });
	}
	
	private void initListeners(){
		addWindowListener(windowListener);
    }
    
    private WindowListener windowListener = new java.awt.event.WindowAdapter() {
    	public void windowClosing(java.awt.event.WindowEvent evt) {
    		if ( Utiles.continuar("¿ Desea cerrar TimeTracker ?","Cerrar TimeTracker") )
    			System.exit(0);
    	}
    };
    
    private ActionListener menuActionListener = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            Object object = e.getSource();
            if ( object instanceof JMenuItem ){
                JMenuItem jmi = (JMenuItem) object;
                String comando = jmi.getActionCommand();
                //System.out.println("comando: "+comando);
                if ( "mostrar informacion".equalsIgnoreCase(comando) ){
                    //getSplitContenido().setRightComponent(getPanelListaSubTareas());
                } else if ("salir".equalsIgnoreCase(comando)){
                    System.exit(0);
                } 
            }
        }
    };
    
}
