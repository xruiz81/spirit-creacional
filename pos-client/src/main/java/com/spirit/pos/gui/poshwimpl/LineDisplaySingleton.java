package com.spirit.pos.gui.poshwimpl;

import jpos.LineDisplay;

public abstract class LineDisplaySingleton {

	private static LineDisplay lineDisplay = null;
	
	public static LineDisplay getInstance(){
		if ( lineDisplay == null )
			lineDisplay = new LineDisplay();
		return lineDisplay;
	}
	
}
