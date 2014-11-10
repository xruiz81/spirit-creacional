package com.spirit.bpm.gui.helper;

import javax.swing.JLabel;

import com.spirit.bpm.gui.tbl.MyDefaultListRender;
import com.spirit.bpm.process.elements.Comentario;
import com.spirit.client.model.SpiritResourceManager;

public class ComentarioListRender extends MyDefaultListRender {

	private static final long serialVersionUID = 1L;

	@Override
	public void renderLabel(Object value, JLabel label) {
		Comentario comentario = (Comentario) value;
		label.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/comentario16.png"));
		String emitidoPor = "[" + comentario.getUserBpm().getUsername() + "]";

		label.setText(emitidoPor + " " + comentario.getDescripcion());
		label.setToolTipText("Emitido "
				+ IconStateManager.dateFormatFechaHora.format(comentario
						.getFechaIngreso()));
	}
}
