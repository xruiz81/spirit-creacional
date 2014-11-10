package com.spirit.client;

import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

import com.jgoodies.uif.component.UIFButton;

public class IconButton extends UIFButton {
//TODO: Esta clase deberia crear instancias de manera normal, no con reflection
	private static final long serialVersionUID = 1L;

	/**
	 * This method initializes
	 */
	public IconButton() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 */
	private void initialize() {
		this.setSize(126, 29);
		try {
			this.setUI((ButtonUI) Class.forName(
					(String) UIManager.get("OutlookButtonUI")).newInstance());
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

} // @jve:decl-index=0:visual-constraint="10,10"
