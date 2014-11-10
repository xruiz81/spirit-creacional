package com.jidesoft.utils;

import java.awt.Color;
import java.awt.Toolkit;
import java.beans.Beans;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.swing.PopupWindow;

// Referenced classes of package com.jidesoft.utils:
//            ProductNames, SecurityUtils

public final class Lm implements ProductNames {
	/* member class not found */
	class a_ {
	}

	/* member class not found */
	class b_ {
	}

	public Lm() {
	}

	public static String getProductVersion() {
		return "1.9.4.12";
	}

	public static boolean showDemoMessageBoxDocking() {
		return false;
	}

	public static void showAboutMessageBox() {
		desabilitarClave = false;
		showDemoMessage();
	}

	private static String a(int l) {
		return "";
	}

	protected static String getProductName(int ai[]) {
		int j1 = k;
		StringBuffer stringbuffer = new StringBuffer();
		int l = 0;
		do {
			if (l >= ai.length)
				break;
			int i1 = ai[l];
			if (j1 == 0) {
				if (i1 == 1)
					stringbuffer.append(h[l]);
				l++;
			}
		} while (j1 == 0);
		return stringbuffer.toString();
	}

	public static void showInvalidProductMessage(String s, int l) {
	}

	private static void a() {
		desabilitarClave = true;
		
	}

	public static void showDemoMessage() {
		a();
	}

	private static String a(String s, String s1, int l, int i1) {
		StringBuffer stringbuffer;
		label0: {
			stringbuffer = new StringBuffer();
			stringbuffer.append(s);
			stringbuffer.append(":");
			stringbuffer.append(s1);
			stringbuffer.append(":");
			stringbuffer.append(l);
			if (k == 0) {
				if (i1 == 0)
					break label0;
				stringbuffer.append(":");
			}
			stringbuffer.append(i1);
		}
		return new String(stringbuffer);
	}

	private static String a(String s, String s1, String s2) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(s);
		stringbuffer.append(":");
		stringbuffer.append(s1);
		stringbuffer.append(":");
		stringbuffer.append(s2);
		return new String(stringbuffer);
	}

	private static int a(String s) {
		return 0;

	}

	public static void verifyLicense(String s, String s1, String s2) {
		// b(s, s1, s2);
	}

	private static void c() {

	}

	private static BigInteger b(String s) {

		return null;
	}

	private static int c(String s) {
		return 0;
	}

	private static int d(String s) {
		return 0;
	}

	public static void setParent(JFrame jframe) {
		c = jframe;
	}

	private static void a(Object obj, String s, int l) {
		a(obj, s, l, ((String) (null)));
	}

	private static void a(Object obj, String s, int l, String s1) {
		if (c == null) {
			JFrame jframe = new JFrame("JIDE Software, Inc.");
			jframe.setIconImage(JideIconsFactory
					.getImageIcon("jide/jide32.png").getImage());
			jframe.setLocation(0, 0x7fffffff);
			jframe.pack();
			jframe.setVisible(true);
			Toolkit.getDefaultToolkit().beep();
			jframe.toFront();
			// b_ b_2 = new b_(jframe, s, obj.toString(), l, s1);
			// b_2.display(false);
			jframe.setVisible(false);
			jframe.dispose();
		}
		// else
		// {
		// b_ b_1 = new b_(c, s, obj.toString(), l, s1);
		// b_1.display(false);
		// }
	}

	public static void showPopupMessageBox(String s) {
		PopupWindow popupwindow = new PopupWindow(c);
		JLabel jlabel = new JLabel();
		jlabel.setBackground(UIManager.getColor("ContentContainer.background"));
		jlabel.setForeground(Color.black);
		jlabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createLineBorder(Color.black), BorderFactory
				.createEmptyBorder(3, 3, 3, 3)));
		jlabel.setText(s);
		popupwindow.add(jlabel);
		popupwindow.show(c, 100, 200);
	}

	private static void d() {
		StringBuffer stringbuffer = new StringBuffer("<HTML>");
		StringBuffer stringbuffer1 = new StringBuffer("");
		stringbuffer
				.append("This is a demo jar for evaluation purpose only.<BR><BR>");
		stringbuffer1
				.append("This is a demo jar for evaluation purpose only.\n\n");
		stringbuffer.append("This package contains <BR><HR>");
		stringbuffer1.append("This package contains \n");
		stringbuffer.append("JIDE Docking Framework").append(" ").append(
				"1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Docking Framework").append(" ").append(
				"1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("JIDE Components").append(" ").append("1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Components").append(" ").append("1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("JIDE Grids").append(" ").append("1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Grids").append(" ").append("1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("JIDE Dialogs").append(" ").append("1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Dialogs").append(" ").append("1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("JIDE Action Framework").append(" ").append(
				"1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Action Framework").append(" ").append(
				"1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("JIDE Shortcut Editor").append(" ").append(
				"1.9.4.12");
		stringbuffer.append("<BR>");
		stringbuffer1.append("JIDE Shortcut Editor").append(" ").append(
				"1.9.4.12");
		stringbuffer1.append("\n");
		stringbuffer.append("</HTML>");
		System.out.println(stringbuffer1);
		a(new String(stringbuffer), "JIDE Products", -1);
	}

	public static void main(String args[]) {
		d();
	}

	public static void getUIError(String s) {
		a(
				"<HTML><B>Missing value in LookAndFeel's UIDefaults</B>. <BR><BR><HR><BR>Component \"<B>"
						+ s
						+ "</B>\" requires its own ComponentUI and additional values in LookAndFeel's UIDefaults.<BR>Please make sure you call LookAndFeelFactory.installJideExtension() whenever you switch L&F."
						+ "<BR>For more details, please refer to Developer Guides or visit JIDE Developer's Forum (http://www.jidesoft.com/forum).\n",
				"LookAndFeel Error", -1, "  Exit  ");
		System.exit(-1);
	}

	public static final boolean DEBUG = false;

	public static final boolean DOC_DEBUG = false;

	public static final boolean CM_DEBUG = false;

	public static final boolean RA_DEBUG = false;

	public static final boolean ID_DEBUG = false;

	public static final boolean PG_DEBUG = false;

	public static final boolean CB_DEBUG = false;

	public static final boolean HG_DEBUG = false;

	public static final boolean AF_DEBUG = false;

	public static final boolean BP_DEBUG = false;

	public static final boolean DEMO = false;

	private static boolean desabilitarClave = true;

	private static int b = 0;

	private static JFrame c;

	private static final Calendar d;

	private static int e = 0;

	private static boolean f = false;

	private static int g = 0;

	public static final int PRODUCT_COMMON = 0;

	public static final int PRODUCT_DOCK = 1;

	public static final int PRODUCT_COMPONENTS = 2;

	public static final int PRODUCT_GRIDS = 4;

	public static final int PRODUCT_DIALOGS = 8;

	public static final int PRODUCT_ACTION = 16;

	public static final int PRODUCT_SHORTCUT = 64;

	public static final int PRODUCT_PIVOT = 32;

	public static final int PRODUCT_CODE_EDITOR = 128;

	private static final String h[] = { "Docking", "Component", "Grid",
			"Dialog", "Action", "Shortcut", "Pivot", "CodeEditor" };

	private static final BigInteger i = new BigInteger("19", 10);

	private static final BigInteger j = new BigInteger(
			"305508269643653255827856366547026610628423058227532461973", 10);

	public static int k;

	static {
		d = Calendar.getInstance();
		d.set(2007, 2, 28, 0, 0, 0);
	}
}