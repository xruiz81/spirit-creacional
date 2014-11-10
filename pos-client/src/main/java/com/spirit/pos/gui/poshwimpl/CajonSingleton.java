package com.spirit.pos.gui.poshwimpl;

import jpos.CashDrawer;

public abstract class CajonSingleton {

	private static CashDrawer cashDrawer = null;
	
	public static CashDrawer getInstance(){
		if ( cashDrawer == null )
			cashDrawer = new CashDrawer();
		return cashDrawer;
	}
}
