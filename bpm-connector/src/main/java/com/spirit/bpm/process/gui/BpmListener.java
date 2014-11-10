package com.spirit.bpm.process.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.spirit.bpm.process.elements.Tarea;

public abstract class BpmListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void setBpmListener(HashMap<String, Object> mapaParametros, Tarea t) {
	}
}
