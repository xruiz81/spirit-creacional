package com.spirit.inventario.session;

import java.util.Date;
import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.helper.StockOperativoDataModel;
import com.spirit.inventario.session.generated._StockOperativoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface StockOperativoSessionService extends _StockOperativoSessionService{
	public List<StockOperativoDataModel> consultaStockOperativo(Long bodegaId,
			Date mesAnio) throws GenericBusinessException;
	
	public void procesar(List<StockOperativoDataModel> dataList,Date mesAnio,Long bodegaId);
	
	public List<ConsultaStockOperativoData> consultaStockOperativo(
			Long oficinaId, Long bodegaId, Long genericoId, Long modeloId,
			Long tallaId, Long colorId, Long productoId, Date fechaInicial,
			Date fechaFinal) throws GenericBusinessException;
}
