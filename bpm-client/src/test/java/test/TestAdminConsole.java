package test;

import com.spirit.bpm.gui.model.JPBpmAdminConsoleModel;
import com.spirit.client.Parametros;
import com.spirit.general.entity.UsuarioData;

public class TestAdminConsole {

	public static void main(String[] args) {
		UsuarioData usuarioData=new UsuarioData();
		usuarioData.setUsuario("admin");
		usuarioData.setClave("bpm");
		Parametros.setUsuarioIf(usuarioData);
		TestUserConsole.testPanel(new JPBpmAdminConsoleModel());
	}
}
