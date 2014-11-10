package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.spirit.bpm.gui.model.JPBpmUserConsoleModel;
import com.spirit.client.Parametros;
import com.spirit.general.entity.UsuarioData;

public class TestUserConsole {

	public static void testPanel(JPanel p) {
		JFrame f = new JFrame();
		f.setSize(p.getSize());
		f.pack();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(f);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UsuarioData usuarioData=new UsuarioData();
		usuarioData.setUsuario("falarcon");
		usuarioData.setClave("bpm");
		Parametros.setUsuarioIf(usuarioData);
		testPanel(new JPBpmUserConsoleModel());
	}
}
