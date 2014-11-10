package com.spirit.comun.querys;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;

@Local
public interface QueryHelperServerLocal {

	public TipoDocumentoIf getTipoDocumento(String codigo)
			throws GenericBusinessException;

	public DocumentoIf getDocumento(String codigo)
			throws GenericBusinessException;
	
	public String getDescripcionProducto(Long productoId);
	
	public String getDescipcionBodega(Long bodegaId);
}
