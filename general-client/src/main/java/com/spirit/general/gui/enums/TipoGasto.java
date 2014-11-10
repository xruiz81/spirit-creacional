package com.spirit.general.gui.enums;

public enum TipoGasto {

	LOCAL, IMPORTADO, AMBOS;
	
	public String getLetra(){
		return this.name().substring(0,1);
	}
	
	public static TipoGasto getTipoGastoByLetra(String letra){
		TipoGasto[] tipos = TipoGasto.values();
		for ( TipoGasto t : tipos ){
			if ( letra != null && letra.equals(t.getLetra()) )
				return t;
		}
		return null;
	}
	
}
