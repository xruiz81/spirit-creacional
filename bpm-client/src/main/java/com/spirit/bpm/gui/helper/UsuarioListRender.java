package com.spirit.bpm.gui.helper;

import javax.swing.JLabel;

import com.spirit.bpm.gui.tbl.MyDefaultListRender;
import com.spirit.bpm.process.elements.UserBpm;

public class UsuarioListRender extends MyDefaultListRender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void renderLabel(Object value, JLabel label) {
		UserBpm userBpm = (UserBpm) value;
		String email = userBpm.getEmail();
		if (email != null) {
			label.setText(userBpm.getUsername() + " [" + userBpm.getEmail()
					+ "]");
		} else {
			label.setText(userBpm.getUsername());
		}
		label.setToolTipText(userBpm.getNombreCompleto());
		// label.setIcon(SpiritResourceManager.getImageIcon("/images/"));
	}
}
