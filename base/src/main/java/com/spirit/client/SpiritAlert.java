package com.spirit.client;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.spirit.client.model.SpiritResourceManager;

public class SpiritAlert {
	private static ImageIcon INFORMATION_ICON = SpiritResourceManager
			.getImageIcon("images/icons/dialog/information_icon.png");
	private static ImageIcon QUESTION_ICON = SpiritResourceManager
			.getImageIcon("images/icons/dialog/question_icon.png");
	private static ImageIcon ERROR_ICON = SpiritResourceManager
			.getImageIcon("images/icons/dialog/error_icon.png");
	private static ImageIcon WARNING_ICON = SpiritResourceManager
			.getImageIcon("images/icons/dialog/warning_icon.png");

	public static final int INFORMATION = 1;
	public static final int ERROR = 2;
	public static final int WARNING = 3;

	public static void createAlert(String mensaje, String titulo,
			int tipoMensaje) {
		Component owner = (Component) ActivePanel.getSingleton()
				.getActivePanel();
		JOptionPane.showMessageDialog(owner, mensaje, titulo,
				JOptionPane.INFORMATION_MESSAGE, INFORMATION_ICON);
	}

	public static void createAlert(String mensaje, int tipoMensaje) {
		Component owner = (Component) ActivePanel.getSingleton()
				.getActivePanel();
		// JFrame jfr = new JFrame();

		switch (tipoMensaje) {
		case INFORMATION: {
			JOptionPane.showMessageDialog(owner, mensaje, "Información",
					JOptionPane.INFORMATION_MESSAGE, INFORMATION_ICON);
			break;
		}
		case ERROR: {
			JOptionPane.showMessageDialog(owner, mensaje, "Error",
					JOptionPane.INFORMATION_MESSAGE, ERROR_ICON);
			break;
		}
		case WARNING: {
			JOptionPane.showMessageDialog(owner, mensaje, "Advertencia",
					JOptionPane.INFORMATION_MESSAGE, WARNING_ICON);
			break;
		}
		default:
			break;
		}

	}

	public static ImageIcon getInformationIcon() {
		return INFORMATION_ICON;
	}

	public static ImageIcon getQuestionIcon() {
		return QUESTION_ICON;
	}

	public static ImageIcon getErrorIcon() {
		return ERROR_ICON;
	}

	public static ImageIcon getWarningIcon() {
		return WARNING_ICON;
	}

	public static boolean confirmDialog(Component padre, String mensaje) {
		// JOptionPane.showInternalOptionDialog(ActivePanel.getSingleton().getActivePanel(),
		// "Confirme", "Mensaje de confirmacion", optionType,
		// messageType,QUESTION_ICON, Object[]{"Si","NO"}, "Si");
		int opcion = JOptionPane.showConfirmDialog(padre, mensaje,"Confirmacion",JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION)
			return true;
		else
			return false;

		//
	}
}
