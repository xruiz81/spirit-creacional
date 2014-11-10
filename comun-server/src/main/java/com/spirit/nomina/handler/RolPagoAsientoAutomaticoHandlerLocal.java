package com.spirit.nomina.handler;


import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;

@Local
public interface RolPagoAsientoAutomaticoHandlerLocal {
	
	public Collection<AsientoIf> generarAsientoAutomaticoQuincenal(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoAutomaticoAutorizacionMensual(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoMensual> chequesCollection, Collection<DatoAsientoMensual> nominaCollection,RolPagoIf rolPagoQuincena)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoAutomaticoMultas(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoMulta> listaMultas)
	throws GenericBusinessException ;
	
	public Collection<AsientoIf> generarAsientoPagoMensual(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoAutomaticoAnticipos(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Map<Long, RubroIf> mapaRubros,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoAutomaticoAutorizacionAnticipos(Date fechaPago,RolPagoIf rolPagoIf,DatoAsientoRubroEventual datoAsientoRubroEventual,Map<Long,ContratoIf> mapaContratos,Map<Long,EmpleadoIf> mapaEmpleados)
	throws GenericBusinessException;
	
	/*public AsientoIf generarAsientoAutomaticoProvisionAporteIessPatronal(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException;*/
	
	public AsientoIf generarAsientoProvisionVariacion1(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRol tipoRol) 
	throws GenericBusinessException;
	
	public AsientoIf generarAsientoProvisionVariacion2(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRol tipoRol) 
	throws GenericBusinessException;
	
	/*public AsientoIf generarAsientoAutomaticoProvisionFondoReserva(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException;*/
	
	public AsientoIf generarAsientoAutomaticoProvisionVacaciones(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException;
	
	public AsientoIf generarAsientoAutomaticoProvisionAporteIessPatronalMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException;
	
	public AsientoIf generarAsientoAutomaticoProvisionFondoReservaMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException;
	
	public AsientoIf generarAsientoAutomaticoProvisionDecimosMensual(Date fechaPago,RolPagoIf rolPagoIf, 
		Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
		TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException;
	
	/*public AsientoIf generarAsientoAutomaticoProvisionDecimos(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException;*/
	
	public Collection<AsientoIf> generarAsientoPagoDecimos(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;

	public Collection<AsientoIf> generarAsientoPagoAporteIessPatronal(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoPagoAporteIessPatronalVariacion1(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> aportePatronalCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoPagoFondoReserva(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	public Collection<AsientoIf> generarAsientoPagoVacaciones(Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException;
	
	
}
