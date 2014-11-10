package com.spirit.servicelocator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;

/**
 * The Service Locator maps an interface to an implementation. See:
 * http://java.sun.com/blueprints/corej2eepatterns/Patterns/ServiceLocator.html
 * 
 * @author JAG - Finalist IT Group
 * @version $Revision: 1.2 $, $Date: 2014/04/04 23:59:46 $
 */
public class ServiceLocator {

	public static final String LOGASIENTODETALLESESSION_SERVICE = "LogAsientoDetalleSessionEJB";
	public static final String BALANCESESSION_SERVICE = "BalanceSessionEJB";
	public static final String SERVIDORSESSION_SERVICE = "ServidorSessionEJB";
	public static final String FACTURASESSION_SERVICE = "FacturaSessionEJB";
	public static final String CORPORACIONSESSION_SERVICE = "CorporacionSessionEJB";
	public static final String ROLSESSION_SERVICE = "RolSessionEJB";
	public static final String MOVIMIENTOSESSION_SERVICE = "MovimientoSessionEJB";
	public static final String PRODUCTORETENCIONSESSION_SERVICE = "ProductoRetencionSessionEJB";
	public static final String TIPOASIENTOSESSION_SERVICE = "TipoAsientoSessionEJB";
	public static final String LISTAPRECIOSESSION_SERVICE = "ListaPrecioSessionEJB";
	public static final String PROPUESTADETALLESESSION_SERVICE = "PropuestaDetalleSessionEJB";
	public static final String EMPLEADOPERSONALSESSION_SERVICE = "EmpleadoPersonalSessionEJB";
	public static final String FUNCIONSESSION_SERVICE = "FuncionSessionEJB";
	public static final String ORDENMEDIODETALLESESSION_SERVICE = "OrdenMedioDetalleSessionEJB";
	public static final String ORDENMEDIODETALLEMAPASESSION_SERVICE = "OrdenMedioDetalleMapaSessionEJB";
	public static final String PRENSAUBICACIONSESSION_SERVICE = "PrensaUbicacionSessionEJB";
	public static final String PLANMEDIOSESSION_SERVICE = "PlanMedioSessionEJB";
	public static final String PLANMEDIOFACTURACIONSESSION_SERVICE = "PlanMedioFacturacionSessionEJB";
	public static final String CIUDADSESSION_SERVICE = "CiudadSessionEJB";
	public static final String REUNIONPRODUCTOSESSION_SERVICE = "ReunionProductoSessionEJB";
	public static final String SOLICITUDCOMPRASESSION_SERVICE = "SolicitudCompraSessionEJB";
	public static final String TIEMPOPARCIALDOTSESSION_SERVICE = "TiempoParcialDotSessionEJB";
	public static final String PLANMEDIODETALLESESSION_SERVICE = "PlanMedioDetalleSessionEJB";
	public static final String LOGCOMPRARETENCIONSESSION_SERVICE = "LogCompraRetencionSessionEJB";
	public static final String NOTACREDITOSESSION_SERVICE = "NotaCreditoSessionEJB";
	public static final String COLORSESSION_SERVICE = "ColorSessionEJB";
	public static final String ASIENTOSESSION_SERVICE = "AsientoSessionEJB";
	public static final String PROVINCIASESSION_SERVICE = "ProvinciaSessionEJB";
	// public static final String TAREASESSION_SERVICE = "TareaSessionEJB";
	public static final String PRESUPUESTOARCHIVOSESSION_SERVICE = "PresupuestoArchivoSessionEJB";
	public static final String RUBROEVENTUALSESSION_SERVICE = "RubroEventualSessionEJB";
	public static final String CONTRATOSESSION_SERVICE = "ContratoSessionEJB";
	public static final String CLIENTEOFICINASESSION_SERVICE = "ClienteOficinaSessionEJB";
	public static final String JBPMMANAGERSESSION_SERVICE = "JbpmManagerSessionEJB";
	public static final String GIFTCARDMOVIMIENTOSESSION_SERVICE = "GiftcardMovimientoSessionEJB";
	public static final String GIFTCARDSESSION_SERVICE = "GiftcardSessionEJB";
	public static final String PARAMETROEMPRESASESSION_SERVICE = "ParametroEmpresaSessionEJB";
	public static final String TIPOCUENTASESSION_SERVICE = "TipoCuentaSessionEJB";
	public static final String MODELOSESSION_SERVICE = "ModeloSessionEJB";
	public static final String ASIENTODETALLESESSION_SERVICE = "AsientoDetalleSessionEJB";
	public static final String TIPORESULTADOSESSION_SERVICE = "TipoResultadoSessionEJB";
	public static final String FACTURADETALLESESSION_SERVICE = "FacturaDetalleSessionEJB";
	public static final String FACTURADETALLECOMPRAASOCIADASESSION_SERVICE = "FacturaDetalleCompraAsociadaSessionEJB";
	public static final String EQUIPOEMPLEADOSESSION_SERVICE = "EquipoEmpleadoSessionEJB";
	public static final String GENEROPROGRAMASESSION_SERVICE = "GeneroProgramaSessionEJB";
	public static final String PRESENTACIONSESSION_SERVICE = "PresentacionSessionEJB";
	public static final String TIPOPARAMETROSESSION_SERVICE = "TipoParametroSessionEJB";
	public static final String SRIIDENTIFTRANSACCIONSESSION_SERVICE = "SriIdentifTransaccionSessionEJB";
	// public static final String EMPLEADODESCUENTOSESSION_SERVICE =
	// "EmpleadoDescuentoSessionEJB";
	public static final String COMPRARETENCIONSESSION_SERVICE = "CompraRetencionSessionEJB";
	// public static final String PLANTILLADETALLESESSION_SERVICE =
	// "PlantillaDetalleSessionEJB";
	public static final String DONACIONDETALLESESSION_SERVICE = "DonaciondetalleSessionEJB";
	public static final String CRUCEDOCUMENTOSESSION_SERVICE = "CruceDocumentoSessionEJB";
	public static final String SRICLIENTERETENCIONSESSION_SERVICE = "SriClienteRetencionSessionEJB";
	public static final String LOGCARTERASESSION_SERVICE = "LogCarteraSessionEJB";
	public static final String OFICINASESSION_SERVICE = "OficinaSessionEJB";
	public static final String USUARIODOCUMENTOSESSION_SERVICE = "UsuarioDocumentoSessionEJB";
	public static final String REUNIONCOMPROMISOSESSION_SERVICE = "ReunionCompromisoSessionEJB";
	public static final String CAMPANAARCHIVOSESSION_SERVICE = "CampanaArchivoSessionEJB";
	public static final String ORDENTRABAJOPRODUCTOSESSION_SERVICE = "OrdenTrabajoProductoSessionEJB";
	public static final String CARTERAAFECTASESSION_SERVICE = "CarteraAfectaSessionEJB";
	public static final String TIPOEMPLEADOSESSION_SERVICE = "TipoEmpleadoSessionEJB";
	public static final String ORDENTRABAJODETALLESESSION_SERVICE = "OrdenTrabajoDetalleSessionEJB";
	public static final String FILEMANAGERSESSION_SERVICE = "FileManagerSessionEJB";
	// public static final String PRESUPUESTOPROVEEDORSESSION_SERVICE =
	// "PresupuestoProveedorSessionEJB";
	public static final String CAJASESSION_SERVICE = "CajaSessionEJB";
	public static final String CUENTAENTIDADSESSION_SERVICE = "CuentaEntidadSessionEJB";
	public static final String COMPRA_DETALLE_GASTOSESSION_SERVICE = "CompraDetalleGastoSessionEJB";
	public static final String COMPRASESSION_SERVICE = "CompraSessionEJB";
	// public static final String PROVEEDORSESSION_SERVICE =
	// "ProveedorSessionEJB";
	// public static final String TIPOTAREASESSION_SERVICE =
	// "TipoTareaSessionEJB";
	// public static final String PARROQUIASESSION_SERVICE =
	// "ParroquiaSessionEJB";
	public static final String CLIENTESESSION_SERVICE = "ClienteSessionEJB";
	public static final String COMPRADETALLESESSION_SERVICE = "CompraDetalleSessionEJB";
	public static final String EVENTOCONTABLESESSION_SERVICE = "EventoContableSessionEJB";
	public static final String GENERICOSESSION_SERVICE = "GenericoSessionEJB";
	public static final String TIPOVALORSESSION_SERVICE = "TipoValorSessionEJB";
	public static final String TIPOBRIEFSESSION_SERVICE = "TipoBriefSessionEJB";
	public static final String EMBALAJEPRODUCTOSESSION_SERVICE = "EmbalajeProductoSessionEJB";
	public static final String PRENSATARIFASESSION_SERVICE = "PrensaTarifaSessionEJB";
	public static final String PRODUCTOCLIENTESESSION_SERVICE = "ProductoClienteSessionEJB";
	public static final String NOTACREDITODETALLESESSION_SERVICE = "NotaCreditoDetalleSessionEJB";
	public static final String COTIZACIONSESSION_SERVICE = "CotizacionSessionEJB";
	public static final String CAMPANADETALLESESSION_SERVICE = "CampanaDetalleSessionEJB";
	public static final String CAMPANAPRODUCTOSESSION_SERVICE = "CampanaProductoSessionEJB";
	public static final String CAJASESIONMOVIMIENTOSSESSION_SERVICE = "CajasesionMovimientosSessionEJB";
	public static final String MOVIMIENTODETALLESESSION_SERVICE = "MovimientoDetalleSessionEJB";
	public static final String ORDENCOMPRASESSION_SERVICE = "OrdenCompraSessionEJB";
	public static final String ORDENASOCIADASESSION_SERVICE = "OrdenAsociadaSessionEJB";
	public static final String USUARIOSESSION_SERVICE = "UsuarioSessionEJB";
	public static final String REUNIONSESSION_SERVICE = "ReunionSessionEJB";
	public static final String SUBTIPOASIENTOSESSION_SERVICE = "SubTipoAsientoSessionEJB";
	public static final String USUARIOPUNTOIMPRESIONSESSION_SERVICE = "UsuarioPuntoImpresionSessionEJB";
	public static final String MULTASDOCUMENTOSSESSION_SERVICE = "MultasDocumentosSessionEJB";
	public static final String ORDENCOMPRADETALLESESSION_SERVICE = "OrdenCompraDetalleSessionEJB";
	public static final String TIPOPRODUCTOSESSION_SERVICE = "TipoProductoSessionEJB";
	public static final String BANCOSESSION_SERVICE = "BancoSessionEJB";
	public static final String SOLICITUDCOMPRAARCHIVOSESSION_SERVICE = "SolicitudCompraArchivoSessionEJB";
	public static final String SRIPROVEEDORRETENCIONSESSION_SERVICE = "SriProveedorRetencionSessionEJB";
	public static final String ROLUSUARIOSESSION_SERVICE = "RolUsuarioSessionEJB";
	public static final String USUARIONOTICIASSESSION_SERVICE = "UsuarioNoticiasSessionEJB";
	public static final String REUNIONASISTENTESESSION_SERVICE = "ReunionAsistenteSessionEJB";
	public static final String PEDIDO_ENVIOSESSION_SERVICE = "PedidoEnvioSessionEJB";
	public static final String MEDIDASESSION_SERVICE = "MedidaSessionEJB";
	public static final String MENUSESSION_SERVICE = "MenuSessionEJB";
	public static final String GRUPOOBJETIVOSESSION_SERVICE = "GrupoObjetivoSessionEJB";
	public static final String REUNIONARCHIVOSESSION_SERVICE = "ReunionArchivoSessionEJB";
	public static final String DONACIONTIPOPRODUCTOSESSION_SERVICE = "DonacionTipoproductoSessionEJB";
	public static final String TIPOROLSESSION_SERVICE = "TipoRolSessionEJB";
	public static final String ROLPAGODOCUMENTOSESSION_SERVICE = "RolPagoDocumentoSessionEJB";
	public static final String TARJETACREDITOSESSION_SERVICE = "TarjetaCreditoSessionEJB";
	public static final String TIPONEGOCIOSESSION_SERVICE = "TipoNegocioSessionEJB";
	public static final String TIPOINDICADORSESSION_SERVICE = "TipoIndicadorSessionEJB";
	public static final String PRENSASECCIONSESSION_SERVICE = "PrensaSeccionSessionEJB";
	public static final String MODULOSESSION_SERVICE = "ModuloSessionEJB";
	public static final String ORDENTRABAJOSESSION_SERVICE = "OrdenTrabajoSessionEJB";
	public static final String PRESUPUESTOSESSION_SERVICE = "PresupuestoSessionEJB";
	public static final String PEDIDOSESSION_SERVICE = "PedidoSessionEJB";
	public static final String DOCUMENTOSESSION_SERVICE = "DocumentoSessionEJB";
	public static final String FORMAPAGOSESSION_SERVICE = "FormaPagoSessionEJB";
	// public static final String PERFILSESSION_SERVICE = "PerfilSessionEJB";
	public static final String CLIENTECONDICIONSESSION_SERVICE = "ClienteCondicionSessionEJB";
	public static final String MONEDASESSION_SERVICE = "MonedaSessionEJB";
	public static final String CHEQUEEMITIDOSESSION_SERVICE = "ChequeEmitidoSessionEJB";
	public static final String DEPOSITOSESSION_SERVICE = "DepositoSessionEJB";
	public static final String TECLASCONFIGURACIONSESSION_SERVICE = "TeclasConfiguracionSessionEJB";
	public static final String EMPRESASESSION_SERVICE = "EmpresaSessionEJB";
	public static final String FUNCIONBODEGASESSION_SERVICE = "FuncionBodegaSessionEJB";
	// public static final String GRUPOTAREASESSION_SERVICE =
	// "GrupoTareaSessionEJB";
	public static final String STOCKSESSION_SERVICE = "StockSessionEJB";
	public static final String MARCAPRODUCTOSESSION_SERVICE = "MarcaProductoSessionEJB";
	public static final String DEPARTAMENTOSESSION_SERVICE = "DepartamentoSessionEJB";
	public static final String TIPOCONTACTOSESSION_SERVICE = "TipoContactoSessionEJB";
	public static final String ORDENMEDIOSESSION_SERVICE = "OrdenMedioSessionEJB";
	public static final String ROLOPCIONSESSION_SERVICE = "RolOpcionSessionEJB";
	public static final String MOTIVODOCUMENTOSESSION_SERVICE = "MotivoDocumentoSessionEJB";
	public static final String SRITIPOCOMPROBANTESESSION_SERVICE = "SriTipoComprobanteSessionEJB";
	public static final String PRECIOSESSION_SERVICE = "PrecioSessionEJB";
	public static final String CAJASESIONSESSION_SERVICE = "CajaSesionSessionEJB";
	public static final String NUMERADORESSESSION_SERVICE = "NumeradoresSessionEJB";
	// public static final String MOTIVOREPROGRAMACIONSESSION_SERVICE =
	// "MotivoReprogramacionSessionEJB";
	public static final String ROLPAGODETALLESESSION_SERVICE = "RolPagoDetalleSessionEJB";
	public static final String SRIIVASERVICIOSESSION_SERVICE = "SriIvaServicioSessionEJB";
	// public static final String ORDENBRIEFSESSION_SERVICE =
	// "OrdenBriefSessionEJB";
	public static final String STOCKDIASESSION_SERVICE = "StockDiaSessionEJB";
	public static final String CLIENTECONTACTOSESSION_SERVICE = "ClienteContactoSessionEJB";
	public static final String VENTASPAGOSSESSION_SERVICE = "VentasPagosSessionEJB";
	public static final String PERIODOSESSION_SERVICE = "PeriodoSessionEJB";
	public static final String TIPOTROQUELADOSESSION_SERVICE = "TipoTroqueladoSessionEJB";
	public static final String SRITIPOTRANSACCIONSESSION_SERVICE = "SriTipoTransaccionSessionEJB";
	public static final String LOTESESSION_SERVICE = "LoteSessionEJB";
	public static final String LOGASIENTOSESSION_SERVICE = "LogAsientoSessionEJB";
	public static final String PRESUPUESTOPRODUCTOSESSION_SERVICE = "PresupuestoProductoSessionEJB";
	public static final String CONTRATORUBROSESSION_SERVICE = "ContratoRubroSessionEJB";
	public static final String CUENTASESSION_SERVICE = "CuentaSessionEJB";
	public static final String CARTERASESSION_SERVICE = "CarteraSessionEJB";
	public static final String DERECHOPROGRAMASESSION_SERVICE = "DerechoProgramaSessionEJB";
	public static final String TIPOCONTRATOSESSION_SERVICE = "TipoContratoSessionEJB";
	public static final String COMERCIALSESSION_SERVICE = "ComercialSessionEJB";
	public static final String COMPRA_GASTOSESSION_SERVICE = "CompraGastoSessionEJB";
	public static final String EMPLEADOOFICINASESSION_SERVICE = "EmpleadoOficinaSessionEJB";
	public static final String PAISSESSION_SERVICE = "PaisSessionEJB";
	public static final String PLANTILLASESSION_SERVICE = "PlantillaSessionEJB";
	public static final String TIPOCLIENTESESSION_SERVICE = "TipoClienteSessionEJB";
	public static final String PLANCUENTASESSION_SERVICE = "PlanCuentaSessionEJB";
	public static final String ROLPAGOSESSION_SERVICE = "RolPagoSessionEJB";
	public static final String VENTASCONSOLIDADOSESSION_SERVICE = "VentasConsolidadoSessionEJB";
	public static final String TIPOPAGOSESSION_SERVICE = "TipoPagoSessionEJB";
	public static final String PROPUESTASESSION_SERVICE = "PropuestaSessionEJB";
	public static final String MAPACOMERCIALSESSION_SERVICE = "MapaComercialSessionEJB";
	public static final String PRESUPUESTODETALLESESSION_SERVICE = "PresupuestoDetalleSessionEJB";
	public static final String PRODUCTOSESSION_SERVICE = "ProductoSessionEJB";
	// public static final String TIPOCONDICIONSESSION_SERVICE =
	// "TipoCondicionSessionEJB";
	public static final String TIPOPROVEEDORSESSION_SERVICE = "TipoProveedorSessionEJB";
	public static final String SOLICITUD_TRANSFERENCIA_DETALLE_SERVICE = "SolicitudTransferenciaDetalleSessionEJB";
	public static final String PRENSAFORMATOSESSION_SERVICE = "PrensaFormatoSessionEJB";
	public static final String CLIENTEINDICADORSESSION_SERVICE = "ClienteIndicadorSessionEJB";
	public static final String IDIOMASESSION_SERVICE = "IdiomaSessionEJB";
	public static final String PUNTOIMPRESIONSESSION_SERVICE = "PuntoImpresionSessionEJB";
	public static final String RUBROSESSION_SERVICE = "RubroSessionEJB";
	public static final String EMBALAJESESSION_SERVICE = "EmbalajeSessionEJB";
	public static final String TIPODOCUMENTOSESSION_SERVICE = "TipoDocumentoSessionEJB";
	public static final String CAMPANASESSION_SERVICE = "CampanaSessionEJB";
	public static final String MOVIMIENTOBANCOSESSION_SERVICE = "MovimientoBancoSessionEJB";
	public static final String CUENTABANCARIASESSION_SERVICE = "CuentaBancariaSessionEJB";
	// public static final String FRECUENCIARECORDATORIOSESSION_SERVICE =
	// "FrecuenciaRecordatorioSessionEJB";
	public static final String FAMILIAGENERICOSESSION_SERVICE = "FamiliaGenericoSessionEJB";
	public static final String POSCOLASESSION_SERVICE = "PosColaSessionEJB";
	public static final String EMPLEADOFORMACIONSESSION_SERVICE = "EmpleadoFormacionSessionEJB";
	public static final String GASTOELECTORALSESSION_SERVICE = "GastoElectoralSessionEJB";
	public static final String SOLICITUDCOMPRADETALLESESSION_SERVICE = "SolicitudCompraDetalleSessionEJB";
	public static final String SALDOCUENTASESSION_SERVICE = "SaldoCuentaSessionEJB";
	public static final String PLANMEDIOMESSESSION_SERVICE = "PlanMedioMesSessionEJB";
	public static final String TIPOARCHIVOSESSION_SERVICE = "TipoArchivoSessionEJB";
	public static final String LINEASESSION_SERVICE = "LineaSessionEJB";
	public static final String BODEGASESSION_SERVICE = "BodegaSessionEJB";
	// public static final String ROLOPCIONFUNCIONSESSION_SERVICE =
	// "RolOpcionFuncionSessionEJB";
	public static final String SRIIVARETENCIONSESSION_SERVICE = "SriIvaRetencionSessionEJB";
	public static final String PRENSAINSERTOSSESSION_SERVICE = "PrensaInsertosSessionEJB";
	public static final String SRIIVABIENSESSION_SERVICE = "SriIvaBienSessionEJB";
	public static final String EMPLEADOIDIOMASSESSION_SERVICE = "EmpleadoIdiomasSessionEJB";
	public static final String EMPLEADOSESSION_SERVICE = "EmpleadoSessionEJB";
	public static final String CENTROGASTOSESSION_SERVICE = "CentroGastoSessionEJB";
	public static final String CLIENTEZONASESSION_SERVICE = "ClienteZonaSessionEJB";
	public static final String TIPOBODEGASESSION_SERVICE = "TipoBodegaSessionEJB";
	public static final String PEDIDODETALLESESSION_SERVICE = "PedidoDetalleSessionEJB";
	public static final String TIPOORDENSESSION_SERVICE = "TipoOrdenSessionEJB";
	// public static final String PROYECTOSESSION_SERVICE =
	// "ProyectoSessionEJB";
	public static final String VENTASTRACKSESSION_SERVICE = "VentasTrackSessionEJB";
	public static final String GASTOELECTORALABONOSESSION_SERVICE = "GastoElectoralAbonoSessionEJB";
	public static final String CAMPANABRIEFSESSION_SERVICE = "CampanaBriefSessionEJB";
	public static final String PRODUCTOBUSQUEDASESSION_SERVICE = "ProductoBusquedaSessionEJB";
	public static final String SUBTIPOORDENSESSION_SERVICE = "SubtipoOrdenSessionEJB";
	public static final String NOTICIASSESSION_SERVICE = "NoticiasSessionEJB";
	public static final String EMPLEADOFAMILIARESSESSION_SERVICE = "EmpleadoFamiliaresSessionEJB";
	public static final String SRIMANAGERSESSION_SERVICE = "SriManagerSessionEJB";
	public static final String GASTOSESSION_SERVICE = "GastoSessionEJB";
	public static final String ORIGENDOCUMENTOSESSION_SERVICE = "OrigenDocumentoSessionEJB";
	public static final String TIEMPOPARCIALDOTDETALLESESSION_SERVICE = "TiempoParcialDotDetalleSessionEJB";
	public static final String USUARIOCUENTASESSION_SERVICE = "UsuarioCuentaSessionEJB";
	// public static final String PRIORIDADSESSION_SERVICE =
	// "PrioridadSessionEJB";
	public static final String SRISUSTENTOTRIBUTARIOSESSION_SERVICE = "SriSustentoTributarioSessionEJB";
	public static final String LOGCARTERADETALLESESSION_SERVICE = "LogCarteraDetalleSessionEJB";
	public static final String TIPOIDENTIFICACIONSESSION_SERVICE = "TipoIdentificacionSessionEJB";
	public static final String SRIIVASESSION_SERVICE = "SriIvaSessionEJB";
	public static final String PERIODODETALLESESSION_SERVICE = "PeriodoDetalleSessionEJB";
	public static final String CARTERADETALLESESSION_SERVICE = "CarteraDetalleSessionEJB";
	public static final String VENTASDOCUMENTOSSESSION_SERVICE = "VentasDocumentosSessionEJB";
	public static final String SRIAIRSESSION_SERVICE = "SriAirSessionEJB";
	public static final String EQUIPOTRABAJOSESSION_SERVICE = "EquipoTrabajoSessionEJB";
	public static final String SOLICITUD_TRANSFERENCIA_SESSION_SERVICE = "SolicitudTransferenciaSessionEJB";
	public static final String SOLICITUD_TRANSFERENCIA_DETALLE_SESSION_SERVICE = "SolicitudTransferenciaDetalleSessionEJB";

	public static final String PUNTOS_TIPO_PRODUCTOSESSION_SERVICE = "PuntosTipoProductoSessionEJB";
	public static final String TARJETASESSION_SERVICE = "TarjetaSessionEJB";
	public static final String TARJETA_TRANSACCIONSESSION_SERVICE = "TarjetaTransaccionSessionEJB";
	public static final String TARJETA_TIPOSESSION_SERVICE = "TarjetaTipoSessionEJB";
	public static final String SRICAMPOSFORMULARIOSESSION_SERVICE = "SriCamposFormularioSessionEJB";

	public static final String PERSONALIZACION_COLOR_SESSION_SERVICE = "PersonalizacionColorSessionEJB";
	public static final String PERSONALIZACION_LOGO_DISENIO_SESSION_SERVICE = "PersonalizacionLogoDisenioSessionEJB";
	public static final String PERSONALIZACION_DISENIO_SESSION_SERVICE = "PersonalizacionDisenioSessionEJB";
	public static final String PERSONALIZACION_TAMANIO_SESSION_SERVICE = "PersonalizacionTamanioSessionEJB";
	public static final String PERSONALIZACION_TIPO_IMPRESION_SESSION_SERVICE = "PersonalizacionTipoImpresionSessionEJB";
	public static final String PERSONALIZACION_TIPO_LETRA_SESSION_SERVICE = "PersonalizacionTipoLetraSessionEJB";
	public static final String PERSONALIZACION_TIPO_PERSONALIZACION_SESSION_SERVICE = "PersonalizacionTipoPersonalizacionSessionEJB";
	public static final String PERSONALIZACION_UBICACION_SESSION_SERVICE = "PersonalizacionUbicacionSessionEJB";

	public static final String STOCK_OPERATIVO_SESSION_SERVICE = "StockOperativoSessionEJB";

	public static final String IMPUESTO_RENTA_PERS_NAT_SESSION_SERVICE = "ImpuestoRentaPersNatSessionEJB";

	public static final String CONTRATO_PLANTILLA_SESSION_SERVICE = "ContratoPlantillaSessionEJB";
	public static final String CONTRATO_PLANTILLA_DETALLE_SESSION_SERVICE = "ContratoPlantillaDetalleSessionEJB";

	public static final String CONTRATO_GASTO_DEDUCIBLE_SESSION_SERVICE = "ContratoGastoDeducibleSessionEJB";
	public static final String GASTO_DEDUCIBLE_SESSION_SERVICE = "GastoDeducibleSessionEJB";
	public static final String SALARIO_MINIMO_VITAL_SESSION_SERVICE = "SalarioMinimoVitalSessionEJB";
	public static final String CONTRATO_UTILIDAD_SESSION_SERVICE = "ContratoUtilidadSessionEJB";
	public static final String ROL_PAGO_DETALLE_UTILIDAD_SESSION_SERVICE = "RolPagoDetalleUtilidadSessionEJB";
	public static final String BI_SESSION_SERVICE = "BiSessionEJB";

	public static final String CUENTAS_POR_COBRAR_SESSION_SERVICE = "CuentasPorCobrarSessionEJB";
	public static final String CUENTAS_POR_PAGAR_SESSION_SERVICE = "CuentasPorPagarSessionEJB";

	public static final String BPM_PROCESO_COMPRAS_SESSION_SERVICE = "ProcesoCompraSessionEJB";
	public static final String BPM_PROCESO_ORDEN_TRABAJO_SESSION_SERVICE = "ProcesoOrdenTrabajoSessionEJB";
	
	public static final String UTILITARIOS_SESSION_SERVICE = "UtilitariosSessionEJB";
	
	public static final String ASOCIACION_TRANSACCION_SESSION_SERVICE = "AsociacionTransaccionSessionEJB";
	public static final String CARTERA_RELACIONADA_SESSION_SERVICE = "CarteraRelacionadaSessionEJB";
	
	public static final String PLAN_MEDIO_FORMA_PAGO_SESSION_SERVICE = "PlanMedioFormaPagoSessionEJB";
	
	public static final String PRESUPUESTOFACTURACION_SESSION_SERVICE = "PresupuestoFacturacionSessionEJB";
	
	public static final String LOGEQUIPOEMPLEADO_SESSION_SERVICE = "LogEquipoEmpleadoSessionEJB";
	public static final String ASIENTO_DESCUADRADO_SESSION_SERVICE = "AsientoDescuadradoSessionEJB";
	public static final String ASIENTO_TMP_SESSION_SERVICE = "AsientoTmpSessionEJB";
	public static final String ASIENTO_DETALLE_TMP_SESSION_SERVICE = "AsientoDetalleTmpSessionEJB";
	//ADD 19 SEPTIEMBRE
	public static final String CAMPANA_PRODUCTO_VERSION_SESSION_SERVICE = "CampanaProductoVersionSessionEJB";
	public static final String IMPUESTO_RENTA_SESSION_SERVICE = "ImpuestoRentaSessionEJB";
	public static final String PAUTA_GENERICO_CLIENTE_SESSION_SERVICE = "PautaGenericoClienteSessionEJB";
	
	public static final String TIMETRACKER2_SESSION_SERVICE = "Timetracker2SessionEJB";
	public static final String TIMETRACKER2_EMPLEADO_SESSION_SERVICE = "Timetracker2EmpleadoSessionEJB";
	public static final String TIMETRACKER2_DETALLE_SESSION_SERVICE = "Timetracker2DetalleSessionEJB";
	public static final String TIMETRACKER2_TIEMPO_SESSION_SERVICE = "Timetracker2TiempoSessionEJB";
	
	public static final String EMPLEADO_VACACIONES_SESSION_SERVICE = "EmpleadoVacacionesSessionEJB";
	public static final String EMPLEADO_ORGANIZACION_SESSION_SERVICE = "EmpleadoOrganizacionSessionEJB";
	
	public static final String CLIENTE_RETENCION_SESSION_SERVICE = "ClienteRetencionSessionEJB";
	
	public static final String OVER_COMISION_SESSION_SERVICE = "OverComisionSessionEJB";
	public static final String HERRAMIENTAS_MEDIOS_SESSION_SERVICE = "HerramientasMediosSessionEJB";
	
	public static final String CARTERA_PAGO_SESSION_SERVICE = "CarteraPagoSessionEJB";
	
	//TABLA HECHA EXCLUSIVAMENTE PARA PROBAR EL SOFTWARE BI SISENSE
	//SE UNE INFORMACION DE LAS TABLAS PRESUPUESTO Y PLAN_MEDIO
	public static final String PRESUPUESTOS_SESSION_SERVICE = "PresupuestosSessionEJB";
	
	
	public static HashMap<String, Object> cacheRemote = new HashMap<String, Object>();
	private static InitialContext context = null;
	static {
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	public static Object getService(String serviceName)
			throws UnknownServiceException, ServiceInstantiationException {
		try {
			Object remoteBean = cacheRemote.get(serviceName);
			if (remoteBean == null) {

				remoteBean = context.lookup(JNDIName.earName + serviceName
						+ "/remote");
				cacheRemote.put(serviceName, remoteBean);
			}
			return remoteBean;
		} catch (Exception e) {
			System.out.println("ERROR: " + serviceName);
			throw new ServiceInstantiationException(e);
		}
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException {
		ArrayList list = new ArrayList();
		for (Field f : ServiceLocator.class.getDeclaredFields()) {
			String name = f.getName();
			String ejb = "";
			try {
				ejb = ((String) f.get(name));
			} catch (Exception e) {
				continue;
			}
			if (list.contains(ejb)) {
				System.out.println("REPETIDO: " + ejb);
			} else
				list.add(ejb);

		}
		for (Field f : ServiceLocator.class.getDeclaredFields()) {
			String name = f.getName();
			String ejb = "";
			try {
				ejb = ((String) f.get(f.getName()))
						.replaceAll("SessionEJB", "");
			} catch (Exception e) {
				continue;
			}

			String classname = ejb + "SessionService";

			String var = " public static "
					+ classname
					+ " get"
					+ classname
					+ "() {\n"
					+ "try {\n"
					+ "return ("
					+ classname
					+ ") ServiceLocator.getService(ServiceLocator."
					+ name
					+ ");\n"
					+ "} catch (Exception e) {\n"
					+ "SpiritAlert.createAlert(\"Se ha producido un error de comunicación con el servidor\",SpiritAlert.ERROR);"
					+ "e.printStackTrace();\n" + "return null;}}\n\n";
			System.out.println(var);
		}
	}

}
