package com.spirit.nomina.handler;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;

@Local
public interface NominaUtilesLocal {

	public Collection<String> getMesesRolPago(Date fechaInicio,Date fechaFin);
	public TipoRol getTipoRolByRubro(RubroIf rubroIf);
	public RubroIf getRubroByTipoRol(TipoRol tipoRol) throws GenericBusinessException;
	public RubroIf buscarRubroUnicoByNombreByTipoRubro(String nombre,String tipoRubro) throws GenericBusinessException;
	//public OperacionNomina getIngresoEgreso(TipoRolIf tipoRolIf,RubroIf rubroIf) throws GenericBusinessException;
	public RubroIf verificarRubrosEnMapa(Map<Long,RubroIf> mapaRubros,Map<Long,String> mapaTipoRubros,Long idRubro) throws GenericBusinessException;
	public String verificarEmpleadoEnMapa(Long contratoId,Map<Long, String> mapaContratoIdNombreEmpleado) throws GenericBusinessException;
	public String[] getMesesMayusculas();
	//public String getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle estado) throws GenericBusinessException;
	//public String getLetraEstadoRubroEventual(EstadoRubroEventual estado) throws GenericBusinessException;
	
}
