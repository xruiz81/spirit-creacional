package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.createImageIcon;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.status.LabelStatusBarItem;
import com.jidesoft.status.MemoryStatusBarItem;
import com.jidesoft.status.TimeStatusBarItem;
import com.jidesoft.swing.JideMenu;
import com.jidesoft.utils.SystemInfo;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.SplashScreen;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.server.StandAloneServer;
import com.spirit.timeTracker.gui.base.JDCloseSystemModel;
import com.spirit.timeTracker.gui.base.SplashScreenMain;
import com.spirit.timeTracker.gui.main.JFTimeTracker;
import com.spirit.util.Utilitarios;


public class TimeTracker extends JFTimeTracker {

	static int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;

	private static final long serialVersionUID = 1L;

	public LabelStatusBarItem _empresa;
	public LabelStatusBarItem _oficina;
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no}; 

	public TimeTracker() {
		setTitle("TimeTracker");
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setBounds(0, 0, anchoPantalla, altoPantalla - 50);
		setIconImage(createImageIcon("images/timetracker/timeTracker.png").getImage());

		initListeners();
		iniciarComponentes();
		iniciarBotones();
		//PanelListaProyectosModel ddd = new PanelListaProyectosModel();
		//ddd.actualizarOrdenesTrabajos(null);
		// pack();
	}

	private void iniciarBotones() {
		// BOTONESsetToolTipText
		// SubTareaListener botonesActionListener = new SubTareaListener();

		getBtnGuardar().setText("");
		getBtnGuardar().setName("Guardar");
		getBtnGuardar().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/save.png"));
		getBtnGuardar().setToolTipText("Guardar");
		getBtnGuardar().addActionListener(Utiles.subtatareaListener);

		getBtnNuevaTarea().setText("");
		getBtnNuevaTarea().setName("nueva");
		getBtnNuevaTarea().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/addSubTaskBig.png"));
		getBtnNuevaTarea().setToolTipText("Nueva Subtarea");
		getBtnNuevaTarea().addActionListener(Utiles.subtatareaListener);
		// getBtnNuevaTarea().setEnabled(false);

		getBtnIniciarTarea().setText("");
		getBtnIniciarTarea().setName("iniciar");
		getBtnIniciarTarea().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/startBig.png"));
		getBtnIniciarTarea().setToolTipText("Iniciar subTarea");
		getBtnIniciarTarea().addActionListener(Utiles.subtatareaListener);
		// getBtnIniciarTarea().setEnabled(false);

		getBtnDetenerTarea().setText("");
		getBtnDetenerTarea().setName("parar");
		getBtnDetenerTarea().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/stopBig.png"));
		getBtnDetenerTarea().setToolTipText("Parar subtarea activa");
		getBtnDetenerTarea().addActionListener(Utiles.subtatareaListener);
		// getBtnDetenerTarea().setEnabled(false);

		getBtnEliminarTarea().setText("");
		getBtnEliminarTarea().setName("eliminar");
		getBtnEliminarTarea().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/deleteSubTaskBig.png"));
		getBtnEliminarTarea().setToolTipText("Eliminar subtarea");
		getBtnEliminarTarea().addActionListener(Utiles.subtatareaListener);
		// getBtnEliminarTarea().setEnabled(false);
		
		/*getBtnReporte().setText("");
		getBtnReporte().setName("reporte");
		getBtnReporte().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/print.png"));
		getBtnReporte().setToolTipText("Reporte");
		getBtnReporte().addActionListener(Utiles.subtatareaListener);*/
	}

	private void iniciarComponentes() {
		crearPanelContenedor();
		crearStatusBar();
		crearMenu();
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();

		// *******MENU ARCHIVO
		JMenu menu = new JMenu("Archivo");
		JMenuItem menuItem = new JMenuItem("Salir");
		menuItem.setIcon(createImageIcon("images/timetracker/exit.png"));
		menuItem.addActionListener(menuActionListener);
		menu.add(menuItem);
		menuBar.add(menu);
		// *******MENU INFORMACION
		JMenu menuInformacion = new JMenu("Información");
		JMenuItem menuColorCeleste = new JMenuItem("Nuevo");
		JMenuItem menuColorVerde = new JMenuItem("Asignado");
		JMenuItem menuColorAmarillo = new JMenuItem("Trabajando");
		JMenuItem menuColorRojo = new JMenuItem("Entregado");
		//getBtnImprimir().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/print.png"));
		menuColorCeleste.setIcon(createImageIcon("images/timetracker/colorCeleste.png"));
		menuColorVerde.setIcon(createImageIcon("images/timetracker/colorVerde.png"));
		menuColorAmarillo.setIcon(createImageIcon("images/timetracker/colorAmarillo.png"));
		menuColorRojo.setIcon(createImageIcon("images/timetracker/colorRojo.png"));
		//menuItem.addActionListener(menuActionListener);
		menuInformacion.add(menuColorCeleste);
		menuInformacion.add(menuColorVerde);
		menuInformacion.add(menuColorAmarillo);
		menuInformacion.add(menuColorRojo);
		menuBar.add(menuInformacion);
		JMenu menuVentana = new JMenu("Ventana");
		menuVentana.add(createLookAndFeelMenu(menuVentana)); 
		menuBar.add(menuVentana);
		setJMenuBar(menuBar);
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
						int opcion = JOptionPane.showOptionDialog(null, "La nueva configuración del Look & Feel de TimeTracker estará disponible luego de reiniciar la aplicación.\n¿Desea cerrar TimeTracker ahora?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
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

	private void crearPanelContenedor() {
		//getContentPane().add(new PanelGeneralModel(), BorderLayout.CENTER);
		CellConstraints cc = new CellConstraints();
		getPanel3().add(new PanelGeneralModel(), cc.xy(1, 3));
	}

	private void crearStatusBar() {
		// getStatusBar().setAlignmentY(StatusBar.RIGHT_ALIGNMENT);
		if (!isSuperuser()) {
			/*
			 * _empresa = new LabelStatusBarItem("Empresa");
			 * _empresa.setAlignment(0); _empresa.setFont(new Font("Arial",
			 * Font.PLAIN, 12)); _empresa.setText("EMPRESA: " +
			 * Parametros.getNombreEmpresa()); getStatusBar1().add(_empresa,
			 * "flexible");
			 */

			/*
			 * if (!isAdmin()) { _oficina = new LabelStatusBarItem("Oficina");
			 * _oficina.setAlignment(0); _oficina.setFont(new Font("Arial",
			 * Font.PLAIN, 12)); _oficina.setText("OFICINA: " +
			 * Parametros.getNombreOficina()); getStatusBar1().add(_oficina,
			 * "flexible"); }
			 */
		}
		LabelStatusBarItem _espacio = new LabelStatusBarItem("");
		_espacio.setAlignment(0);
		getStatusBar().add(_espacio, "vary");

		LabelStatusBarItem _usuario = new LabelStatusBarItem("Usuario");
		_usuario.setAlignment(0);
		_usuario.setFont(new Font("Arial", Font.PLAIN, 12));
		_usuario.setText("USUARIO: " + Parametros.getUsuario());
		getStatusBar().add(_usuario, "flexible");

		TimeStatusBarItem timeBar = new TimeStatusBarItem();
		getStatusBar().add(timeBar, "flexible");
		MemoryStatusBarItem gc = new MemoryStatusBarItem();
		getStatusBar().add(gc, "flexible");
	}

	private boolean isSuperuser() {
		// return "S".equals(SpiritLogin._usuario.getTipousuario());
		return false;
	}

	private boolean isAdmin() {
		// return "A".equals(SpiritLogin._usuario.getTipousuario());
		return false;
	}

	public static void main(String args[]) {
		Locale.setDefault(Utilitarios.enLocale);
		SplashScreenMain splashScreen = new SplashScreenMain();
		SplashScreen screen = splashScreen.getScreen();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			String fileName = "spirit.cfg";
			if (SystemInfo.isLinux() || SystemInfo.isUnix() || SystemInfo.isMacOSX() || SystemInfo.isAnyMac() || SystemInfo.isMacClassic())
				fileName = ".spirit";
			String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + fileName;
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream entrada = new DataInputStream(fstream);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String lookAndFeel = "";
			if ((lookAndFeel = buffer.readLine()) != null && !lookAndFeel.trim().equals(SpiritConstants.getEmptyCharacter()))
				UIManager.setLookAndFeel(lookAndFeel);
			else
				setDefaultLookAndFeel();
			entrada.close();
		} catch (java.io.FileNotFoundException filenotfoundexception) {
			setDefaultLookAndFeel();
		} catch (ClassNotFoundException classnotfoundexception) {
			setDefaultLookAndFeel();
		} catch (InstantiationException instantiationexception) {
			setDefaultLookAndFeel();
		} catch (IllegalAccessException illegalaccessexception) {
			setDefaultLookAndFeel();
		} catch (UnsupportedLookAndFeelException unsupportedlookandfeelexception) {
			setDefaultLookAndFeel();
		} catch (IOException e) {
			setDefaultLookAndFeel();
		}
		LookAndFeelFactory.installJideExtension();
		if (Parametros.standAlone)
			StandAloneServer.start(screen);
		splashScreen.splashScreenDestruct();
		new SpiritLogin();
	}
	
	private static void setDefaultLookAndFeel() {
		try {
			if (SystemInfo.isLinux() || SystemInfo.isUnix()) {
				UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
			} else if (SystemInfo.isMacOSX()) {
				//System.setProperty("Quaqua.tabLayoutPolicy","wrap");
				UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
			} else if (SystemInfo.isAnyMac() || SystemInfo.isMacClassic()) {
				//System.setProperty("Quaqua.tabLayoutPolicy","wrap");
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
			} else
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void initListeners() {
		addWindowListener(windowListener);
	}

	private WindowListener windowListener = new java.awt.event.WindowAdapter() {
		public void windowClosing(java.awt.event.WindowEvent evt) {
			new JDCloseSystemModel(Parametros.getMainFrame());
		}
	};

	private ActionListener menuActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object object = e.getSource();
			if (object instanceof JMenuItem) {
				JMenuItem jmi = (JMenuItem) object;
				String comando = jmi.getActionCommand();
				if ("mostrar informacion".equalsIgnoreCase(comando)) {
					// getSplitContenido().setRightComponent(getPanelListaSubTareas());
				} else if ("salir".equalsIgnoreCase(comando)) {
					new JDCloseSystemModel(Parametros.getMainFrame());
				}
			}
		}
	};

}
