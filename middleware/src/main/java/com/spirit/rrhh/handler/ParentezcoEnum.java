package com.spirit.rrhh.handler;

import com.spirit.exception.GenericBusinessException;

public enum ParentezcoEnum {
	HIJO,CONYUGUE,PADRE,MADRE;
	
	public String getLetra(){
		return this.toString().substring(0,1);
	}
	
	public static ParentezcoEnum getParentezcoByLetra(String letra) throws GenericBusinessException{
		for ( ParentezcoEnum p : values() ){
			if ( letra.equals(p.getLetra()) ){
				return p;
			}
		}
		throw new GenericBusinessException("Parentezco no considerado !!");
	}
	
	public String toString() {
		String nombreEnum = this.name(); 
		if ( ParentezcoEnum.HIJO.name().equals(nombreEnum) )
			return "HIJO(A)";
		return nombreEnum;
	}
}
