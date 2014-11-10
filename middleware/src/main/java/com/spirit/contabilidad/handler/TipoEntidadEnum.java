package com.spirit.contabilidad.handler;

public enum TipoEntidadEnum {
	CLIENTE,PROVEEDOR,EMPLEADO,GENERICO,PRODUCTO,
	DOCUMENTO,CUENTA_BANCARIA,DEPARTAMENTO,RUBRO_EVENTUAL,
	SRI_AIR,OFICINA,GASTO,ORIGEN_DOCUMENTO,TIPO_SUSTENTO_TRIBUTARIO;
	
	String nombre = ""; 
	
	TipoEntidadEnum() {
		String[] nombres = this.name().split("_");
		for ( String nombre : nombres ){
			this.nombre += (nombre+" ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	@Override
	public String toString() {
		//String [] nombres = this.name().split("_");
		//return getNombre(nombres);
		return nombre;
	}
	
	/*public String getNombre(String[] nombres ){
		String nombreFinal = "";
		for ( String nombre : nombres ){
			nombreFinal += (nombre+" ");
		}
		nombreFinal.subSequence(0, nombreFinal.length()-1);
		return nombreFinal;
	}*/
	
	public String getLetra(){
		return getLetraTipoEntidad(this);
	}
	
	public static String getLetraTipoEntidad(TipoEntidadEnum tipoEntidad){
		if ( tipoEntidad == TipoEntidadEnum.CLIENTE )
			return "C";
		else if (tipoEntidad == TipoEntidadEnum.PROVEEDOR)
			return "P";
		else if (tipoEntidad == TipoEntidadEnum.EMPLEADO)
			return "E";
		else if (tipoEntidad == TipoEntidadEnum.GENERICO)
			return "G";
		else if (tipoEntidad == TipoEntidadEnum.PRODUCTO) 	// I por ITEM, puesto que la P de producto ya se utiliza para
			return "I";				// representar a la entidad PROVEEDOR
		else if (tipoEntidad == TipoEntidadEnum.DOCUMENTO)
			return "D";
		else if (tipoEntidad == TipoEntidadEnum.CUENTA_BANCARIA)
			return "B";
		else if (tipoEntidad == TipoEntidadEnum.DEPARTAMENTO)
			return "A";
		else if (tipoEntidad == TipoEntidadEnum.RUBRO_EVENTUAL)
			return "R";
		else if (tipoEntidad == TipoEntidadEnum.SRI_AIR)
			return "S";
		else if (tipoEntidad == TipoEntidadEnum.OFICINA)
			return "O";
		else if (tipoEntidad == TipoEntidadEnum.GASTO)
			return "T";
		else if (tipoEntidad == TipoEntidadEnum.ORIGEN_DOCUMENTO)
			return "N";
		else if (tipoEntidad == TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO)
			return "U";
		return "";
	}
	
	public static TipoEntidadEnum getTipoEntidadByLetra(String letra){
		TipoEntidadEnum[] tps = values();
		for ( TipoEntidadEnum tp : tps ){
			if ( letra.equals(getLetraTipoEntidad(tp)) ){
				return tp;
			}
		}
		return null;
	}
	
}
