package com.spirit.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChangeModeImpl {
	
	private static List listeners = new ArrayList();
	
	
	 public static synchronized void addChangeModeListener( ChangeModeListener l ) {
	        listeners.add( l );
	    }
	    
	    public static  synchronized void removeChangeModeListener( ChangeModeListener l ) {
	        listeners.remove( l );
	    }
	     
	    public   synchronized void fireChangeModeEvent(String mode) {
	        ChangeModeEvent mood = new ChangeModeEvent( this, mode );
	        Iterator it = listeners.iterator();
	        while( it.hasNext() ) {
	            ( (ChangeModeListener) it.next() ).modeChanged( mood );
	        }
	    }
	

}
