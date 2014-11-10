package com.spirit.compras.session;

import java.util.Collection;
import java.util.Map;

public interface ProductoBusquedaSessionService {

	int getProductoListSize(String tipoReferencia, Map aMap, Long idReferencia, String tipoProducto, String servicioConsumo, String mmpg) 
	throws com.spirit.exception.GenericBusinessException;
	
	Collection getProductoList(int startIndex, int endIndex,String tipoReferencia, Map aMap, Long idReferencia, String tipoProducto, String servicioConsumo, String mmpg) 
	throws com.spirit.exception.GenericBusinessException;
	
	
}
