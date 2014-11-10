package com.spirit.pos.gui.poshwimpl;

import jpos.MSR;

public abstract class MSRSingleton {

	private static MSR msr = null;
	
	public static MSR getMSRSingleton(){
		if ( msr == null )
			msr = new MSR();
		return msr;
	}
}
