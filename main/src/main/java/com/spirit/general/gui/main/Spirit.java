package com.spirit.general.gui.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.utils.SystemInfo;
import com.spirit.bpm.impl.ProcesoCompraClientManager;
import com.spirit.bpm.impl.ProcesoManager;
import com.spirit.bpm.impl.ProcesoOrdenTrabajoClient;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritConstants;
import com.spirit.client.SplashScreen;
import com.spirit.server.StandAloneServer;
import com.spirit.util.Utilitarios;

public class Spirit {
	public static void main(String args[]) throws Exception {
		Locale.setDefault(Utilitarios.enLocale);
		SplashScreenMain splashScreen = new SplashScreenMain();
		SplashScreen screen = splashScreen.getScreen();

		if (Parametros.standAlone) {
			StandAloneServer.start(screen);
		}

		// SpiritBpm.iniciarBpm();
		ProcesoManager.setManager(SpiritProcessDefinition.COMPRA,
				new ProcesoCompraClientManager());
		
		ProcesoManager.setManager(SpiritProcessDefinition.PRESUPUESTO,
				new ProcesoOrdenTrabajoClient());

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		// Temas UI disponibles
		//UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		//UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
		//UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		//UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//UIManager.setLookAndFeel("com.seaglasslookandfeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		//UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
		//UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
		//UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
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
		}
		LookAndFeelFactory.installJideExtension();
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
}
