package com.spirit.comun.test;

import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.spirit.server.StandAloneServer;

@RunWith(Suite.class)

@SuiteClasses({

	//CONTABILIDAD -- YA ESTA -- FALTA: ProcesarAsientoTest
	AsientoDetalleSessionTest.class
	,AsientoSessionTest.class
	,CuentaSessionTest.class
	,EventoContableSessionTest.class
	,PeriodoDetalleSessionTest.class
	,PeriodoSessionTest.class
	,PlanCuentaSessionTest.class
	,PlantillaSessionTest.class
	,SaldoCuentaSessionTest.class
	,SubtipoAsientoSessionTest.class
	,TipoCuentaSessionTest.class
	//ProcesarAsientoTest.class
	
	//CRM -- YA ESTA --
	,ClienteOficinaSessionTest.class
	,ClienteSessionTest.class
	,ClienteContactoSessionTest.class
	,ClienteIndicadorSessionTest.class
	,ClienteZonaSessionTest.class
	
	//FACTURACION -- YA ESTA --
	,FacturaSessionTest.class
	,FacturaDetalleSessionTest.class
	,PedidoDetalleSessionTest.class
	,PedidoSessionTest.class

	//GENERAL -- YA ESTA --
	,BancoSessionTest.class
	,CajaSessionTest.class
	,CotizacionSessionTest.class
	,DocumentoSessionTest.class
	,EmpleadoSessionTest.class
	,EmpresaSessionTest.class
	,ModuloSessionTest.class
	,MonedaSessionTest.class
	,NumeradoresSessionTest.class
	,OficinaSessionTest.class
	,ParametroEmpresaSessionTest.class
	,PuntoImpresionSessionTest.class
	,TipoClienteSessionTest.class
	,TipoDocumentoSessionTest.class
	,TipoParametroSessionTest.class
	,TipoProveedorSessionTest.class
	,UsuarioDocumentoSessionTest.class
	,UsuarioSessionTest.class
	,TipoPagoSessionTest.class
	,TipoIdentificacionSessionTest.class

	//INVENTARIO -- YA ESTA --
	,GenericoSessionTest.class
	,LoteSessionTest.class
	,MovimientoDetalleSessionTest.class
	//,MovimientoSessionTest.class
	,ProductoSessionTest.class
	,StockSessionTest.class
	,TipoProductoSessionTest.class

	//MEDIOS -- YA ESTA --
	,OrdenTrabajoDetalleSessionTest.class
	,OrdenTrabajoSessionTest.class
	,OrdenTrabajoProductoSessionTest.class
	,PresupuestoDetalleSessionTest.class
	,PresupuestoSessionTest.class
	,ProductoClienteSessionTest.class
	,SubtipoOrdenSessionTest.class

	//NOMINA -- YA ESTA --
	,ContratoRubroSessionTest.class
	,ContratoSessionTest.class
	,RolPagoDetalleSessionTest.class
	,RolPagoSessionTest.class
	,RubroSessionTest.class
	,TipoRolSessionTest.class
	,RubroEventualSessionTest.class
	,TipoContratoSessionTest.class
	
	//SEGURIDAD -- YA ESTA --
	,UsuarioCuentaSessionTest.class

	//CARTERA
	/*CarteraAfectaSessionTest.class
	,CarteraDetalleSessionTest.class
	,CarteraSessionTest.class
	,CuentaBancariaSessionTest.class
	,DepositoSessionTest.class
	,MovimientoBancoSessionTest.class
	,FormaPagoSessionTest.class*/
	
	//SRI
	/*SriClienteRetencionSessionTest.class
	,SriTipoComprobanteSessionTest.class
	,SriAirSessionTest.class*/
	
	
	//COMPRAS
	//,GastoSessionTest.class
	//,CompraGastoSessionTest.class
	//,CompraDetalleGastoSessionTest.class
	//,CompraAsociadaGastoSessionTest.class
})

public class AllTests {

	@BeforeClass
	public static void startup(){
		StandAloneServer.start(null);
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}