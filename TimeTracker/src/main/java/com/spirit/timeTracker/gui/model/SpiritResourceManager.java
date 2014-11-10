package com.spirit.timeTracker.gui.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;


public class SpiritResourceManager {
	private static SpiritResourceManager INSTANCE = new SpiritResourceManager();

	public static URL getUrl(String strRecurso) {
		URL url = INSTANCE.getClass().getResource(strRecurso);
		if (url == null) {
			url = INSTANCE.getClass().getClassLoader().getResource(strRecurso);
		}
		return url;
	}

	public static InputStream getInputStreamResource(String strRecurso) {
		InputStream stream = INSTANCE.getClass()
				.getResourceAsStream(strRecurso);
		if (stream == null) {
			stream = INSTANCE.getClass().getClassLoader().getResourceAsStream(
					strRecurso);
		}
		return stream;
	}

	public static ImageIcon getImageIcon(String strRecurso) {
		URL url = getUrl(strRecurso);

		if (url != null) {
			return new ImageIcon(url);
		} else {
			return new ImageIcon();
		}
	}

	public static String getPropertyFromFileResource(String archivoProperties,
			String property) {
		Properties properties = new Properties();
		String value = null;
		try {
			properties.load(getInputStreamResource(archivoProperties));
			value = (String) properties.get(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		properties = null;
		return value;
	}
	public static String getPropertyFromFile(String archivoProperties,
			String property) {
		Properties properties = new Properties();
		String value = null;
		try {
			//properties.load(getInputStreamResource(archivoProperties));
			FileInputStream stream=new FileInputStream(archivoProperties);
			properties.load(stream);
			value = (String) properties.get(property);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		properties = null;
		return value;
	}

	public static void main(String args[]) {
		// ImageIcon myImage =
		// SpiritResourceManager.getImageIcon("images/spirit_splash.jpg");
		// System.out.println(getInputStreamResource("conf/version.properties"));
		System.out.println(getPropertyFromFile("conf/version.properties",
				"version"));

	}
}
