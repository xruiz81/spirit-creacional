package com.spirit.comun.util;

import com.spirit.bi.BiSessionService;
import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.bpm.compras.ProcesoCompraSessionService;
import com.spirit.bpm.session.JbpmManagerSessionService;
import com.spirit.cartera.session.AsociacionTransaccionSessionService;
import com.spirit.cartera.session.CarteraAfectaSessionService;
import com.spirit.cartera.session.CarteraDetalleSessionService;
import com.spirit.cartera.session.CarteraPagoSessionService;
import com.spirit.cartera.session.CarteraRelacionadaSessionService;
import com.spirit.cartera.session.CarteraSessionService;
import com.spirit.cartera.session.ClienteCondicionSessionService;
import com.spirit.cartera.session.CuentasPorCobrarSessionService;
import com.spirit.cartera.session.CuentasPorPagarSessionService;
import com.spirit.cartera.session.DepositoSessionService;
import com.spirit.cartera.session.FormaPagoSessionService;
import com.spirit.cartera.session.LogCarteraDetalleSessionService;
import com.spirit.cartera.session.LogCarteraSessionService;
import com.spirit.cartera.session.MovimientoBancoSessionService;
import com.spirit.cartera.session.NotaCreditoDetalleSessionService;
import com.spirit.cartera.session.NotaCreditoSessionService;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.session.CompraDetalleGastoSessionService;
import com.spirit.compras.session.CompraDetalleSessionService;
import com.spirit.compras.session.CompraGastoSessionService;
import com.spirit.compras.session.CompraRetencionSessionService;
import com.spirit.compras.session.CompraSessionService;
import com.spirit.compras.session.GastoSessionService;
import com.spirit.compras.session.LogCompraRetencionSessionService;
import com.spirit.compras.session.OrdenAsociadaSessionService;
import com.spirit.compras.session.OrdenCompraDetalleSessionService;
import com.spirit.compras.session.OrdenCompraSessionService;
import com.spirit.compras.session.ProductoBusquedaSessionService;
import com.spirit.compras.session.SolicitudCompraArchivoSessionService;
import com.spirit.compras.session.SolicitudCompraDetalleSessionService;
import com.spirit.compras.session.SolicitudCompraSessionService;
import com.spirit.contabilidad.session.AsientoDescuadradoSessionService;
import com.spirit.contabilidad.session.AsientoDetalleSessionService;
import com.spirit.contabilidad.session.AsientoDetalleTmpSessionService;
import com.spirit.contabilidad.session.AsientoSessionService;
import com.spirit.contabilidad.session.AsientoTmpSessionService;
import com.spirit.contabilidad.session.ChequeEmitidoSessionService;
import com.spirit.contabilidad.session.CuentaEntidadSessionService;
import com.spirit.contabilidad.session.CuentaSessionService;
import com.spirit.contabilidad.session.EventoContableSessionService;
import com.spirit.contabilidad.session.LogAsientoDetalleSessionService;
import com.spirit.contabilidad.session.LogAsientoSessionService;
import com.spirit.contabilidad.session.PeriodoDetalleSessionService;
import com.spirit.contabilidad.session.PeriodoSessionService;
import com.spirit.contabilidad.session.PlanCuentaSessionService;
import com.spirit.contabilidad.session.PlantillaSessionService;
import com.spirit.contabilidad.session.SaldoCuentaSessionService;
import com.spirit.contabilidad.session.SubTipoAsientoSessionService;
import com.spirit.contabilidad.session.TipoAsientoSessionService;
import com.spirit.contabilidad.session.TipoCuentaSessionService;
import com.spirit.contabilidad.session.TipoResultadoSessionService;
import com.spirit.contabilidad.session.TipoValorSessionService;
import com.spirit.crm.session.ClienteContactoSessionService;
import com.spirit.crm.session.ClienteIndicadorSessionService;
import com.spirit.crm.session.ClienteOficinaSessionService;
import com.spirit.crm.session.ClienteRetencionSessionService;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.crm.session.ClienteZonaSessionService;
import com.spirit.crm.session.CorporacionSessionService;
import com.spirit.crm.session.GastoElectoralAbonoSessionService;
import com.spirit.crm.session.GastoElectoralSessionService;
import com.spirit.crm.session.TipoContactoSessionService;
import com.spirit.crm.session.TipoIndicadorSessionService;
import com.spirit.crm.session.TipoNegocioSessionService;
import com.spirit.facturacion.session.FacturaDetalleCompraAsociadaSessionService;
import com.spirit.facturacion.session.FacturaDetalleSessionService;
import com.spirit.facturacion.session.FacturaSessionService;
import com.spirit.facturacion.session.ListaPrecioSessionService;
import com.spirit.facturacion.session.MotivoDocumentoSessionService;
import com.spirit.facturacion.session.PedidoDetalleSessionService;
import com.spirit.facturacion.session.PedidoEnvioSessionService;
import com.spirit.facturacion.session.PedidoSessionService;
import com.spirit.facturacion.session.PersonalizacionColorSessionService;
import com.spirit.facturacion.session.PersonalizacionDisenioSessionService;
import com.spirit.facturacion.session.PersonalizacionLogoDisenioSessionService;
import com.spirit.facturacion.session.PersonalizacionTamanioSessionService;
import com.spirit.facturacion.session.PersonalizacionTipoImpresionSessionService;
import com.spirit.facturacion.session.PersonalizacionTipoLetraSessionService;
import com.spirit.facturacion.session.PersonalizacionTipoPersonalizacionSessionService;
import com.spirit.facturacion.session.PersonalizacionUbicacionSessionService;
import com.spirit.facturacion.session.PrecioSessionService;
import com.spirit.general.session.BancoSessionService;
import com.spirit.general.session.CajaSessionService;
import com.spirit.general.session.CentroGastoSessionService;
import com.spirit.general.session.CiudadSessionService;
import com.spirit.general.session.CotizacionSessionService;
import com.spirit.general.session.CruceDocumentoSessionService;
import com.spirit.general.session.CuentaBancariaSessionService;
import com.spirit.general.session.DepartamentoSessionService;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.general.session.EmpleadoOficinaSessionService;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.general.session.FileManagerSessionService;
import com.spirit.general.session.LineaSessionService;
import com.spirit.general.session.ModuloSessionService;
import com.spirit.general.session.MonedaSessionService;
import com.spirit.general.session.NoticiasSessionService;
import com.spirit.general.session.NumeradoresSessionService;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.general.session.OrigenDocumentoSessionService;
import com.spirit.general.session.PaisSessionService;
import com.spirit.general.session.ParametroEmpresaSessionService;
import com.spirit.general.session.ProvinciaSessionService;
import com.spirit.general.session.PuntoImpresionSessionService;
import com.spirit.general.session.ServidorSessionService;
import com.spirit.general.session.TipoArchivoSessionService;
import com.spirit.general.session.TipoClienteSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.general.session.TipoEmpleadoSessionService;
import com.spirit.general.session.TipoIdentificacionSessionService;
import com.spirit.general.session.TipoOrdenSessionService;
import com.spirit.general.session.TipoPagoSessionService;
import com.spirit.general.session.TipoParametroSessionService;
import com.spirit.general.session.TipoProveedorSessionService;
import com.spirit.general.session.TipoTroqueladoSessionService;
import com.spirit.general.session.UsuarioDocumentoSessionService;
import com.spirit.general.session.UsuarioNoticiasSessionService;
import com.spirit.general.session.UsuarioPuntoImpresionSessionService;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.general.session.UtilitariosSessionService;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.ColorSessionService;
import com.spirit.inventario.session.EmbalajeProductoSessionService;
import com.spirit.inventario.session.EmbalajeSessionService;
import com.spirit.inventario.session.FamiliaGenericoSessionService;
import com.spirit.inventario.session.FuncionBodegaSessionService;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.GiftcardMovimientoSessionService;
import com.spirit.inventario.session.GiftcardSessionService;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.inventario.session.MedidaSessionService;
import com.spirit.inventario.session.ModeloSessionService;
import com.spirit.inventario.session.MovimientoDetalleSessionService;
import com.spirit.inventario.session.MovimientoSessionService;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.inventario.session.ProductoRetencionSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.inventario.session.SolicitudTransferenciaDetalleSessionService;
import com.spirit.inventario.session.SolicitudTransferenciaSessionService;
import com.spirit.inventario.session.StockDiaSessionService;
import com.spirit.inventario.session.StockOperativoSessionService;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.inventario.session.TipoBodegaSessionService;
import com.spirit.inventario.session.TipoProductoSessionService;
import com.spirit.medios.session.CampanaArchivoSessionService;
import com.spirit.medios.session.CampanaBriefSessionService;
import com.spirit.medios.session.CampanaDetalleSessionService;
import com.spirit.medios.session.CampanaProductoSessionService;
import com.spirit.medios.session.CampanaProductoVersionSessionService;
import com.spirit.medios.session.CampanaSessionService;
import com.spirit.medios.session.ComercialSessionService;
import com.spirit.medios.session.DerechoProgramaSessionService;
import com.spirit.medios.session.EquipoEmpleadoSessionService;
import com.spirit.medios.session.EquipoTrabajoSessionService;
import com.spirit.medios.session.GeneroProgramaSessionService;
import com.spirit.medios.session.GrupoObjetivoSessionService;
import com.spirit.medios.session.HerramientasMediosSessionService;
import com.spirit.medios.session.LogEquipoEmpleadoSessionService;
import com.spirit.medios.session.MapaComercialSessionService;
import com.spirit.medios.session.MarcaProductoSessionService;
import com.spirit.medios.session.OrdenMedioDetalleMapaSessionService;
import com.spirit.medios.session.OrdenMedioDetalleSessionService;
import com.spirit.medios.session.OrdenMedioSessionService;
import com.spirit.medios.session.OrdenTrabajoDetalleSessionService;
import com.spirit.medios.session.OrdenTrabajoProductoSessionService;
import com.spirit.medios.session.OrdenTrabajoSessionService;
import com.spirit.medios.session.OverComisionSessionService;
import com.spirit.medios.session.PautaGenericoClienteSessionService;
import com.spirit.medios.session.PlanMedioDetalleSessionService;
import com.spirit.medios.session.PlanMedioFacturacionSessionService;
import com.spirit.medios.session.PlanMedioFormaPagoSessionService;
import com.spirit.medios.session.PlanMedioMesSessionService;
import com.spirit.medios.session.PlanMedioSessionService;
import com.spirit.medios.session.PrensaFormatoSessionService;
import com.spirit.medios.session.PrensaInsertosSessionService;
import com.spirit.medios.session.PrensaSeccionSessionService;
import com.spirit.medios.session.PrensaTarifaSessionService;
import com.spirit.medios.session.PrensaUbicacionSessionService;
import com.spirit.medios.session.PresupuestoArchivoSessionService;
import com.spirit.medios.session.PresupuestoDetalleSessionService;
import com.spirit.medios.session.PresupuestoFacturacionSessionService;
import com.spirit.medios.session.PresupuestoProductoSessionService;
import com.spirit.medios.session.PresupuestoSessionService;
import com.spirit.medios.session.PresupuestosSessionService;
import com.spirit.medios.session.ProductoClienteSessionService;
import com.spirit.medios.session.PropuestaDetalleSessionService;
import com.spirit.medios.session.PropuestaSessionService;
import com.spirit.medios.session.ReunionArchivoSessionService;
import com.spirit.medios.session.ReunionAsistenteSessionService;
import com.spirit.medios.session.ReunionCompromisoSessionService;
import com.spirit.medios.session.ReunionProductoSessionService;
import com.spirit.medios.session.ReunionSessionService;
import com.spirit.medios.session.SubtipoOrdenSessionService;
import com.spirit.medios.session.TiempoParcialDotDetalleSessionService;
import com.spirit.medios.session.TiempoParcialDotSessionService;
import com.spirit.medios.session.Timetracker2DetalleSessionService;
import com.spirit.medios.session.Timetracker2EmpleadoSessionService;
import com.spirit.medios.session.Timetracker2SessionService;
import com.spirit.medios.session.Timetracker2TiempoSessionService;
import com.spirit.medios.session.TipoBriefSessionService;
import com.spirit.nomina.session.ContratoGastoDeducibleSessionService;
import com.spirit.nomina.session.ContratoPlantillaDetalleSessionService;
import com.spirit.nomina.session.ContratoPlantillaSessionService;
import com.spirit.nomina.session.ContratoRubroSessionService;
import com.spirit.nomina.session.ContratoSessionService;
import com.spirit.nomina.session.ContratoUtilidadSessionService;
import com.spirit.nomina.session.GastoDeducibleSessionService;
import com.spirit.nomina.session.ImpuestoRentaPersNatSessionService;
import com.spirit.nomina.session.RolPagoDetalleSessionService;
import com.spirit.nomina.session.RolPagoDetalleUtilidadSessionService;
import com.spirit.nomina.session.RolPagoDocumentoSessionService;
import com.spirit.nomina.session.RolPagoSessionService;
import com.spirit.nomina.session.RubroEventualSessionService;
import com.spirit.nomina.session.RubroSessionService;
import com.spirit.nomina.session.SalarioMinimoVitalSessionService;
import com.spirit.nomina.session.TipoContratoSessionService;
import com.spirit.nomina.session.TipoRolSessionService;
import com.spirit.pos.session.CajaSesionSessionService;
import com.spirit.pos.session.CajasesionMovimientosSessionService;
import com.spirit.pos.session.DonacionTipoproductoSessionService;
import com.spirit.pos.session.DonaciondetalleSessionService;
import com.spirit.pos.session.MultasDocumentosSessionService;
import com.spirit.pos.session.PuntosTipoProductoSessionService;
import com.spirit.pos.session.TarjetaCreditoSessionService;
import com.spirit.pos.session.TarjetaSessionService;
import com.spirit.pos.session.TarjetaTipoSessionService;
import com.spirit.pos.session.TarjetaTransaccionSessionService;
import com.spirit.pos.session.TeclasConfiguracionSessionService;
import com.spirit.pos.session.VentasConsolidadoSessionService;
import com.spirit.pos.session.VentasDocumentosSessionService;
import com.spirit.pos.session.VentasPagosSessionService;
import com.spirit.pos.session.VentasTrackSessionService;
import com.spirit.rrhh.session.EmpleadoFamiliaresSessionService;
import com.spirit.rrhh.session.EmpleadoFormacionSessionService;
import com.spirit.rrhh.session.EmpleadoIdiomasSessionService;
import com.spirit.rrhh.session.EmpleadoOrganizacionSessionService;
import com.spirit.rrhh.session.EmpleadoPersonalSessionService;
import com.spirit.rrhh.session.EmpleadoVacacionesSessionService;
import com.spirit.rrhh.session.IdiomaSessionService;
import com.spirit.seguridad.session.FuncionSessionService;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.seguridad.session.RolOpcionSessionService;
import com.spirit.seguridad.session.RolSessionService;
import com.spirit.seguridad.session.RolUsuarioSessionService;
import com.spirit.seguridad.session.UsuarioCuentaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.sri.session.ImpuestoRentaSessionService;
import com.spirit.sri.session.SriAirSessionService;
import com.spirit.sri.session.SriCamposFormularioSessionService;
import com.spirit.sri.session.SriClienteRetencionSessionService;
import com.spirit.sri.session.SriIdentifTransaccionSessionService;
import com.spirit.sri.session.SriIvaBienSessionService;
import com.spirit.sri.session.SriIvaRetencionSessionService;
import com.spirit.sri.session.SriIvaServicioSessionService;
import com.spirit.sri.session.SriIvaSessionService;
import com.spirit.sri.session.SriManagerSessionService;
import com.spirit.sri.session.SriProveedorRetencionSessionService;
import com.spirit.sri.session.SriSustentoTributarioSessionService;
import com.spirit.sri.session.SriTipoComprobanteSessionService;
import com.spirit.sri.session.SriTipoTransaccionSessionService;

public class SessionServiceLocator {
	public static LogAsientoDetalleSessionService getLogAsientoDetalleSessionService() {
		try {
			return (LogAsientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.LOGASIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ServidorSessionService getServidorSessionService() {
		try {
			return (ServidorSessionService) ServiceLocator
					.getService(ServiceLocator.SERVIDORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FacturaSessionService getFacturaSessionService() {
		try {
			return (FacturaSessionService) ServiceLocator
					.getService(ServiceLocator.FACTURASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CorporacionSessionService getCorporacionSessionService() {
		try {
			return (CorporacionSessionService) ServiceLocator
					.getService(ServiceLocator.CORPORACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolSessionService getRolSessionService() {
		try {
			return (RolSessionService) ServiceLocator
					.getService(ServiceLocator.ROLSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MovimientoSessionService getMovimientoSessionService() {
		try {
			return (MovimientoSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoRetencionSessionService getProductoRetencionSessionService() {
		try {
			return (ProductoRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTORETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoAsientoSessionService getTipoAsientoSessionService() {
		try {
			return (TipoAsientoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ListaPrecioSessionService getListaPrecioSessionService() {
		try {
			return (ListaPrecioSessionService) ServiceLocator
					.getService(ServiceLocator.LISTAPRECIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PropuestaDetalleSessionService getPropuestaDetalleSessionService() {
		try {
			return (PropuestaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PROPUESTADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoPersonalSessionService getEmpleadoPersonalSessionService() {
		try {
			return (EmpleadoPersonalSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOPERSONALSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FuncionSessionService getFuncionSessionService() {
		try {
			return (FuncionSessionService) ServiceLocator
					.getService(ServiceLocator.FUNCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenMedioDetalleSessionService getOrdenMedioDetalleSessionService() {
		try {
			return (OrdenMedioDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENMEDIODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static OrdenMedioDetalleMapaSessionService getOrdenMedioDetalleMapaSessionService() {
		try {
			return (OrdenMedioDetalleMapaSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENMEDIODETALLEMAPASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrensaUbicacionSessionService getPrensaUbicacionSessionService() {
		try {
			return (PrensaUbicacionSessionService) ServiceLocator
					.getService(ServiceLocator.PRENSAUBICACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PlanMedioSessionService getPlanMedioSessionService() {
		try {
			return (PlanMedioSessionService) ServiceLocator
					.getService(ServiceLocator.PLANMEDIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static PlanMedioFacturacionSessionService getPlanMedioFacturacionSessionService() {
		try {
			return (PlanMedioFacturacionSessionService) ServiceLocator
					.getService(ServiceLocator.PLANMEDIOFACTURACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CiudadSessionService getCiudadSessionService() {
		try {
			return (CiudadSessionService) ServiceLocator
					.getService(ServiceLocator.CIUDADSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ReunionProductoSessionService getReunionProductoSessionService() {
		try {
			return (ReunionProductoSessionService) ServiceLocator
					.getService(ServiceLocator.REUNIONPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudCompraSessionService getSolicitudCompraSessionService() {
		try {
			return (SolicitudCompraSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUDCOMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TiempoParcialDotSessionService getTiempoParcialDotSessionService() {
		try {
			return (TiempoParcialDotSessionService) ServiceLocator
					.getService(ServiceLocator.TIEMPOPARCIALDOTSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PlanMedioDetalleSessionService getPlanMedioDetalleSessionService() {
		try {
			return (PlanMedioDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PLANMEDIODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LogCompraRetencionSessionService getLogCompraRetencionSessionService() {
		try {
			return (LogCompraRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.LOGCOMPRARETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static NotaCreditoSessionService getNotaCreditoSessionService() {
		try {
			return (NotaCreditoSessionService) ServiceLocator
					.getService(ServiceLocator.NOTACREDITOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ColorSessionService getColorSessionService() {
		try {
			return (ColorSessionService) ServiceLocator
					.getService(ServiceLocator.COLORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static AsientoSessionService getAsientoSessionService() {
		try {
			return (AsientoSessionService) ServiceLocator
					.getService(ServiceLocator.ASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProvinciaSessionService getProvinciaSessionService() {
		try {
			return (ProvinciaSessionService) ServiceLocator
					.getService(ServiceLocator.PROVINCIASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PresupuestoArchivoSessionService getPresupuestoArchivoSessionService() {
		try {
			return (PresupuestoArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.PRESUPUESTOARCHIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RubroEventualSessionService getRubroEventualSessionService() {
		try {
			return (RubroEventualSessionService) ServiceLocator
					.getService(ServiceLocator.RUBROEVENTUALSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoSessionService getContratoSessionService() {
		try {
			return (ContratoSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteOficinaSessionService getClienteOficinaSessionService() {
		try {
			return (ClienteOficinaSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEOFICINASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static JbpmManagerSessionService getJbpmManagerSessionService() {
		try {
			return (JbpmManagerSessionService) ServiceLocator
					.getService(ServiceLocator.JBPMMANAGERSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GiftcardMovimientoSessionService getGiftcardMovimientoSessionService() {
		try {
			return (GiftcardMovimientoSessionService) ServiceLocator
					.getService(ServiceLocator.GIFTCARDMOVIMIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GiftcardSessionService getGiftcardSessionService() {
		try {
			return (GiftcardSessionService) ServiceLocator
					.getService(ServiceLocator.GIFTCARDSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ParametroEmpresaSessionService getParametroEmpresaSessionService() {
		try {
			return (ParametroEmpresaSessionService) ServiceLocator
					.getService(ServiceLocator.PARAMETROEMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoCuentaSessionService getTipoCuentaSessionService() {
		try {
			return (TipoCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ModeloSessionService getModeloSessionService() {
		try {
			return (ModeloSessionService) ServiceLocator
					.getService(ServiceLocator.MODELOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static AsientoDetalleSessionService getAsientoDetalleSessionService() {
		try {
			return (AsientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ASIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoResultadoSessionService getTipoResultadoSessionService() {
		try {
			return (TipoResultadoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPORESULTADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FacturaDetalleSessionService getFacturaDetalleSessionService() {
		try {
			return (FacturaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.FACTURADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EquipoEmpleadoSessionService getEquipoEmpleadoSessionService() {
		try {
			return (EquipoEmpleadoSessionService) ServiceLocator
					.getService(ServiceLocator.EQUIPOEMPLEADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GeneroProgramaSessionService getGeneroProgramaSessionService() {
		try {
			return (GeneroProgramaSessionService) ServiceLocator
					.getService(ServiceLocator.GENEROPROGRAMASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PresentacionSessionService getPresentacionSessionService() {
		try {
			return (PresentacionSessionService) ServiceLocator
					.getService(ServiceLocator.PRESENTACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoParametroSessionService getTipoParametroSessionService() {
		try {
			return (TipoParametroSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPARAMETROSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriIdentifTransaccionSessionService getSriIdentifTransaccionSessionService() {
		try {
			return (SriIdentifTransaccionSessionService) ServiceLocator
					.getService(ServiceLocator.SRIIDENTIFTRANSACCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraRetencionSessionService getCompraRetencionSessionService() {
		try {
			return (CompraRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRARETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DonaciondetalleSessionService getDonaciondetalleSessionService() {
		try {
			return (DonaciondetalleSessionService) ServiceLocator
					.getService(ServiceLocator.DONACIONDETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CruceDocumentoSessionService getCruceDocumentoSessionService() {
		try {
			return (CruceDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.CRUCEDOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriClienteRetencionSessionService getSriClienteRetencionSessionService() {
		try {
			return (SriClienteRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.SRICLIENTERETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LogCarteraSessionService getLogCarteraSessionService() {
		try {
			return (LogCarteraSessionService) ServiceLocator
					.getService(ServiceLocator.LOGCARTERASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OficinaSessionService getOficinaSessionService() {
		try {
			return (OficinaSessionService) ServiceLocator
					.getService(ServiceLocator.OFICINASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static UsuarioDocumentoSessionService getUsuarioDocumentoSessionService() {
		try {
			return (UsuarioDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ReunionCompromisoSessionService getReunionCompromisoSessionService() {
		try {
			return (ReunionCompromisoSessionService) ServiceLocator
					.getService(ServiceLocator.REUNIONCOMPROMISOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CampanaArchivoSessionService getCampanaArchivoSessionService() {
		try {
			return (CampanaArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANAARCHIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenTrabajoProductoSessionService getOrdenTrabajoProductoSessionService() {
		try {
			return (OrdenTrabajoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENTRABAJOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CarteraAfectaSessionService getCarteraAfectaSessionService() {
		try {
			return (CarteraAfectaSessionService) ServiceLocator
					.getService(ServiceLocator.CARTERAAFECTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoEmpleadoSessionService getTipoEmpleadoSessionService() {
		try {
			return (TipoEmpleadoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOEMPLEADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenTrabajoDetalleSessionService getOrdenTrabajoDetalleSessionService() {
		try {
			return (OrdenTrabajoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENTRABAJODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FileManagerSessionService getFileManagerSessionService() {
		try {
			return (FileManagerSessionService) ServiceLocator
					.getService(ServiceLocator.FILEMANAGERSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CajaSessionService getCajaSessionService() {
		try {
			return (CajaSessionService) ServiceLocator
					.getService(ServiceLocator.CAJASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CuentaEntidadSessionService getCuentaEntidadSessionService() {
		try {
			return (CuentaEntidadSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTAENTIDADSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraDetalleGastoSessionService getCompraDetalleGastoSessionService() {
		try {
			return (CompraDetalleGastoSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRA_DETALLE_GASTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraSessionService getCompraSessionService() {
		try {
			return (CompraSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteSessionService getClienteSessionService() {
		try {
			return (ClienteSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraDetalleSessionService getCompraDetalleSessionService() {
		try {
			return (CompraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EventoContableSessionService getEventoContableSessionService() {
		try {
			return (EventoContableSessionService) ServiceLocator
					.getService(ServiceLocator.EVENTOCONTABLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GenericoSessionService getGenericoSessionService() {
		try {
			return (GenericoSessionService) ServiceLocator
					.getService(ServiceLocator.GENERICOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoValorSessionService getTipoValorSessionService() {
		try {
			return (TipoValorSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOVALORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoBriefSessionService getTipoBriefSessionService() {
		try {
			return (TipoBriefSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOBRIEFSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmbalajeProductoSessionService getEmbalajeProductoSessionService() {
		try {
			return (EmbalajeProductoSessionService) ServiceLocator
					.getService(ServiceLocator.EMBALAJEPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrensaTarifaSessionService getPrensaTarifaSessionService() {
		try {
			return (PrensaTarifaSessionService) ServiceLocator
					.getService(ServiceLocator.PRENSATARIFASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoClienteSessionService getProductoClienteSessionService() {
		try {
			return (ProductoClienteSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTOCLIENTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static NotaCreditoDetalleSessionService getNotaCreditoDetalleSessionService() {
		try {
			return (NotaCreditoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.NOTACREDITODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CotizacionSessionService getCotizacionSessionService() {
		try {
			return (CotizacionSessionService) ServiceLocator
					.getService(ServiceLocator.COTIZACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CampanaDetalleSessionService getCampanaDetalleSessionService() {
		try {
			return (CampanaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CampanaProductoSessionService getCampanaProductoSessionService() {
		try {
			return (CampanaProductoSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANAPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CajasesionMovimientosSessionService getCajasesionMovimientosSessionService() {
		try {
			return (CajasesionMovimientosSessionService) ServiceLocator
					.getService(ServiceLocator.CAJASESIONMOVIMIENTOSSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MovimientoDetalleSessionService getMovimientoDetalleSessionService() {
		try {
			return (MovimientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenCompraSessionService getOrdenCompraSessionService() {
		try {
			return (OrdenCompraSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENCOMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static UsuarioSessionService getUsuarioSessionService() {
		try {
			return (UsuarioSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ReunionSessionService getReunionSessionService() {
		try {
			return (ReunionSessionService) ServiceLocator
					.getService(ServiceLocator.REUNIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SubTipoAsientoSessionService getSubTipoAsientoSessionService() {
		try {
			return (SubTipoAsientoSessionService) ServiceLocator
					.getService(ServiceLocator.SUBTIPOASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static UsuarioPuntoImpresionSessionService getUsuarioPuntoImpresionSessionService() {
		try {
			return (UsuarioPuntoImpresionSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIOPUNTOIMPRESIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MultasDocumentosSessionService getMultasDocumentosSessionService() {
		try {
			return (MultasDocumentosSessionService) ServiceLocator
					.getService(ServiceLocator.MULTASDOCUMENTOSSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenCompraDetalleSessionService getOrdenCompraDetalleSessionService() {
		try {
			return (OrdenCompraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENCOMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoProductoSessionService getTipoProductoSessionService() {
		try {
			return (TipoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static BancoSessionService getBancoSessionService() {
		try {
			return (BancoSessionService) ServiceLocator
					.getService(ServiceLocator.BANCOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudCompraArchivoSessionService getSolicitudCompraArchivoSessionService() {
		try {
			return (SolicitudCompraArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUDCOMPRAARCHIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriProveedorRetencionSessionService getSriProveedorRetencionSessionService() {
		try {
			return (SriProveedorRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.SRIPROVEEDORRETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolUsuarioSessionService getRolUsuarioSessionService() {
		try {
			return (RolUsuarioSessionService) ServiceLocator
					.getService(ServiceLocator.ROLUSUARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static UsuarioNoticiasSessionService getUsuarioNoticiasSessionService() {
		try {
			return (UsuarioNoticiasSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIONOTICIASSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ReunionAsistenteSessionService getReunionAsistenteSessionService() {
		try {
			return (ReunionAsistenteSessionService) ServiceLocator
					.getService(ServiceLocator.REUNIONASISTENTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PedidoEnvioSessionService getPedidoEnvioSessionService() {
		try {
			return (PedidoEnvioSessionService) ServiceLocator
					.getService(ServiceLocator.PEDIDO_ENVIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MedidaSessionService getMedidaSessionService() {
		try {
			return (MedidaSessionService) ServiceLocator
					.getService(ServiceLocator.MEDIDASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MenuSessionService getMenuSessionService() {
		try {
			return (MenuSessionService) ServiceLocator
					.getService(ServiceLocator.MENUSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GrupoObjetivoSessionService getGrupoObjetivoSessionService() {
		try {
			return (GrupoObjetivoSessionService) ServiceLocator
					.getService(ServiceLocator.GRUPOOBJETIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ReunionArchivoSessionService getReunionArchivoSessionService() {
		try {
			return (ReunionArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.REUNIONARCHIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DonacionTipoproductoSessionService getDonacionTipoproductoSessionService() {
		try {
			return (DonacionTipoproductoSessionService) ServiceLocator
					.getService(ServiceLocator.DONACIONTIPOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoRolSessionService getTipoRolSessionService() {
		try {
			return (TipoRolSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOROLSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolPagoDocumentoSessionService getRolPagoDocumentoSessionService() {
		try {
			return (RolPagoDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.ROLPAGODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TarjetaCreditoSessionService getTarjetaCreditoSessionService() {
		try {
			return (TarjetaCreditoSessionService) ServiceLocator
					.getService(ServiceLocator.TARJETACREDITOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoNegocioSessionService getTipoNegocioSessionService() {
		try {
			return (TipoNegocioSessionService) ServiceLocator
					.getService(ServiceLocator.TIPONEGOCIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoIndicadorSessionService getTipoIndicadorSessionService() {
		try {
			return (TipoIndicadorSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOINDICADORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrensaSeccionSessionService getPrensaSeccionSessionService() {
		try {
			return (PrensaSeccionSessionService) ServiceLocator
					.getService(ServiceLocator.PRENSASECCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ModuloSessionService getModuloSessionService() {
		try {
			return (ModuloSessionService) ServiceLocator
					.getService(ServiceLocator.MODULOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenTrabajoSessionService getOrdenTrabajoSessionService() {
		try {
			return (OrdenTrabajoSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENTRABAJOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PresupuestoSessionService getPresupuestoSessionService() {
		try {
			return (PresupuestoSessionService) ServiceLocator
					.getService(ServiceLocator.PRESUPUESTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PedidoSessionService getPedidoSessionService() {
		try {
			return (PedidoSessionService) ServiceLocator
					.getService(ServiceLocator.PEDIDOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DocumentoSessionService getDocumentoSessionService() {
		try {
			return (DocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.DOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FormaPagoSessionService getFormaPagoSessionService() {
		try {
			return (FormaPagoSessionService) ServiceLocator
					.getService(ServiceLocator.FORMAPAGOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteCondicionSessionService getClienteCondicionSessionService() {
		try {
			return (ClienteCondicionSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTECONDICIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MonedaSessionService getMonedaSessionService() {
		try {
			return (MonedaSessionService) ServiceLocator
					.getService(ServiceLocator.MONEDASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ChequeEmitidoSessionService getChequeEmitidoSessionService() {
		try {
			return (ChequeEmitidoSessionService) ServiceLocator
					.getService(ServiceLocator.CHEQUEEMITIDOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DepositoSessionService getDepositoSessionService() {
		try {
			return (DepositoSessionService) ServiceLocator
					.getService(ServiceLocator.DEPOSITOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TeclasConfiguracionSessionService getTeclasConfiguracionSessionService() {
		try {
			return (TeclasConfiguracionSessionService) ServiceLocator
					.getService(ServiceLocator.TECLASCONFIGURACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpresaSessionService getEmpresaSessionService() {
		try {
			return (EmpresaSessionService) ServiceLocator
					.getService(ServiceLocator.EMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FuncionBodegaSessionService getFuncionBodegaSessionService() {
		try {
			return (FuncionBodegaSessionService) ServiceLocator
					.getService(ServiceLocator.FUNCIONBODEGASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static StockSessionService getStockSessionService() {
		try {
			return (StockSessionService) ServiceLocator
					.getService(ServiceLocator.STOCKSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MarcaProductoSessionService getMarcaProductoSessionService() {
		try {
			return (MarcaProductoSessionService) ServiceLocator
					.getService(ServiceLocator.MARCAPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DepartamentoSessionService getDepartamentoSessionService() {
		try {
			return (DepartamentoSessionService) ServiceLocator
					.getService(ServiceLocator.DEPARTAMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoContactoSessionService getTipoContactoSessionService() {
		try {
			return (TipoContactoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCONTACTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenMedioSessionService getOrdenMedioSessionService() {
		try {
			return (OrdenMedioSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENMEDIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolOpcionSessionService getRolOpcionSessionService() {
		try {
			return (RolOpcionSessionService) ServiceLocator
					.getService(ServiceLocator.ROLOPCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MotivoDocumentoSessionService getMotivoDocumentoSessionService() {
		try {
			return (MotivoDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.MOTIVODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriTipoComprobanteSessionService getSriTipoComprobanteSessionService() {
		try {
			return (SriTipoComprobanteSessionService) ServiceLocator
					.getService(ServiceLocator.SRITIPOCOMPROBANTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrecioSessionService getPrecioSessionService() {
		try {
			return (PrecioSessionService) ServiceLocator
					.getService(ServiceLocator.PRECIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoSessionService getProductoSessionService() {
		try {
			return (ProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CajaSesionSessionService getCajaSesionSessionService() {
		try {
			return (CajaSesionSessionService) ServiceLocator
					.getService(ServiceLocator.CAJASESIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static NumeradoresSessionService getNumeradoresSessionService() {
		try {
			return (NumeradoresSessionService) ServiceLocator
					.getService(ServiceLocator.NUMERADORESSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolPagoDetalleSessionService getRolPagoDetalleSessionService() {
		try {
			return (RolPagoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ROLPAGODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriIvaServicioSessionService getSriIvaServicioSessionService() {
		try {
			return (SriIvaServicioSessionService) ServiceLocator
					.getService(ServiceLocator.SRIIVASERVICIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static StockDiaSessionService getStockDiaSessionService() {
		try {
			return (StockDiaSessionService) ServiceLocator
					.getService(ServiceLocator.STOCKDIASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteContactoSessionService getClienteContactoSessionService() {
		try {
			return (ClienteContactoSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTECONTACTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static VentasPagosSessionService getVentasPagosSessionService() {
		try {
			return (VentasPagosSessionService) ServiceLocator
					.getService(ServiceLocator.VENTASPAGOSSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PeriodoSessionService getPeriodoSessionService() {
		try {
			return (PeriodoSessionService) ServiceLocator
					.getService(ServiceLocator.PERIODOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoTroqueladoSessionService getTipoTroqueladoSessionService() {
		try {
			return (TipoTroqueladoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOTROQUELADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriTipoTransaccionSessionService getSriTipoTransaccionSessionService() {
		try {
			return (SriTipoTransaccionSessionService) ServiceLocator
					.getService(ServiceLocator.SRITIPOTRANSACCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LoteSessionService getLoteSessionService() {
		try {
			return (LoteSessionService) ServiceLocator
					.getService(ServiceLocator.LOTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LogAsientoSessionService getLogAsientoSessionService() {
		try {
			return (LogAsientoSessionService) ServiceLocator
					.getService(ServiceLocator.LOGASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PresupuestoProductoSessionService getPresupuestoProductoSessionService() {
		try {
			return (PresupuestoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PRESUPUESTOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoRubroSessionService getContratoRubroSessionService() {
		try {
			return (ContratoRubroSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATORUBROSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CuentaSessionService getCuentaSessionService() {
		try {
			return (CuentaSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CarteraSessionService getCarteraSessionService() {
		try {
			return (CarteraSessionService) ServiceLocator
					.getService(ServiceLocator.CARTERASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static DerechoProgramaSessionService getDerechoProgramaSessionService() {
		try {
			return (DerechoProgramaSessionService) ServiceLocator
					.getService(ServiceLocator.DERECHOPROGRAMASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoContratoSessionService getTipoContratoSessionService() {
		try {
			return (TipoContratoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCONTRATOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ComercialSessionService getComercialSessionService() {
		try {
			return (ComercialSessionService) ServiceLocator
					.getService(ServiceLocator.COMERCIALSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraGastoSessionService getCompraGastoSessionService() {
		try {
			return (CompraGastoSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRA_GASTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoOficinaSessionService getEmpleadoOficinaSessionService() {
		try {
			return (EmpleadoOficinaSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOOFICINASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PaisSessionService getPaisSessionService() {
		try {
			return (PaisSessionService) ServiceLocator
					.getService(ServiceLocator.PAISSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PlantillaSessionService getPlantillaSessionService() {
		try {
			return (PlantillaSessionService) ServiceLocator
					.getService(ServiceLocator.PLANTILLASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoClienteSessionService getTipoClienteSessionService() {
		try {
			return (TipoClienteSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCLIENTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PlanCuentaSessionService getPlanCuentaSessionService() {
		try {
			return (PlanCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.PLANCUENTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolPagoSessionService getRolPagoSessionService() {
		try {
			return (RolPagoSessionService) ServiceLocator
					.getService(ServiceLocator.ROLPAGOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static VentasConsolidadoSessionService getVentasConsolidadoSessionService() {
		try {
			return (VentasConsolidadoSessionService) ServiceLocator
					.getService(ServiceLocator.VENTASCONSOLIDADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoPagoSessionService getTipoPagoSessionService() {
		try {
			return (TipoPagoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPAGOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PropuestaSessionService getPropuestaSessionService() {
		try {
			return (PropuestaSessionService) ServiceLocator
					.getService(ServiceLocator.PROPUESTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MapaComercialSessionService getMapaComercialSessionService() {
		try {
			return (MapaComercialSessionService) ServiceLocator
					.getService(ServiceLocator.MAPACOMERCIALSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PresupuestoDetalleSessionService getPresupuestoDetalleSessionService() {
		try {
			return (PresupuestoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PRESUPUESTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoProveedorSessionService getTipoProveedorSessionService() {
		try {
			return (TipoProveedorSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPROVEEDORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudTransferenciaDetalleSessionService getSolicitudTransferenciaDetalleSessionService() {
		try {
			return (SolicitudTransferenciaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUD_TRANSFERENCIA_DETALLE_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrensaFormatoSessionService getPrensaFormatoSessionService() {
		try {
			return (PrensaFormatoSessionService) ServiceLocator
					.getService(ServiceLocator.PRENSAFORMATOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteIndicadorSessionService getClienteIndicadorSessionService() {
		try {
			return (ClienteIndicadorSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEINDICADORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static IdiomaSessionService getIdiomaSessionService() {
		try {
			return (IdiomaSessionService) ServiceLocator
					.getService(ServiceLocator.IDIOMASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PuntoImpresionSessionService getPuntoImpresionSessionService() {
		try {
			return (PuntoImpresionSessionService) ServiceLocator
					.getService(ServiceLocator.PUNTOIMPRESIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RubroSessionService getRubroSessionService() {
		try {
			return (RubroSessionService) ServiceLocator
					.getService(ServiceLocator.RUBROSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmbalajeSessionService getEmbalajeSessionService() {
		try {
			return (EmbalajeSessionService) ServiceLocator
					.getService(ServiceLocator.EMBALAJESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoDocumentoSessionService getTipoDocumentoSessionService() {
		try {
			return (TipoDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CampanaSessionService getCampanaSessionService() {
		try {
			return (CampanaSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MovimientoBancoSessionService getMovimientoBancoSessionService() {
		try {
			return (MovimientoBancoSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTOBANCOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CuentaBancariaSessionService getCuentaBancariaSessionService() {
		try {
			return (CuentaBancariaSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTABANCARIASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static FamiliaGenericoSessionService getFamiliaGenericoSessionService() {
		try {
			return (FamiliaGenericoSessionService) ServiceLocator
					.getService(ServiceLocator.FAMILIAGENERICOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoFormacionSessionService getEmpleadoFormacionSessionService() {
		try {
			return (EmpleadoFormacionSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOFORMACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GastoElectoralSessionService getGastoElectoralSessionService() {
		try {
			return (GastoElectoralSessionService) ServiceLocator
					.getService(ServiceLocator.GASTOELECTORALSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudCompraDetalleSessionService getSolicitudCompraDetalleSessionService() {
		try {
			return (SolicitudCompraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUDCOMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SaldoCuentaSessionService getSaldoCuentaSessionService() {
		try {
			return (SaldoCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.SALDOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PlanMedioMesSessionService getPlanMedioMesSessionService() {
		try {
			return (PlanMedioMesSessionService) ServiceLocator
					.getService(ServiceLocator.PLANMEDIOMESSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoArchivoSessionService getTipoArchivoSessionService() {
		try {
			return (TipoArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOARCHIVOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LineaSessionService getLineaSessionService() {
		try {
			return (LineaSessionService) ServiceLocator
					.getService(ServiceLocator.LINEASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static BodegaSessionService getBodegaSessionService() {
		try {
			return (BodegaSessionService) ServiceLocator
					.getService(ServiceLocator.BODEGASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriIvaRetencionSessionService getSriIvaRetencionSessionService() {
		try {
			return (SriIvaRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.SRIIVARETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PrensaInsertosSessionService getPrensaInsertosSessionService() {
		try {
			return (PrensaInsertosSessionService) ServiceLocator
					.getService(ServiceLocator.PRENSAINSERTOSSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriIvaBienSessionService getSriIvaBienSessionService() {
		try {
			return (SriIvaBienSessionService) ServiceLocator
					.getService(ServiceLocator.SRIIVABIENSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoIdiomasSessionService getEmpleadoIdiomasSessionService() {
		try {
			return (EmpleadoIdiomasSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOIDIOMASSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoSessionService getEmpleadoSessionService() {
		try {
			return (EmpleadoSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CentroGastoSessionService getCentroGastoSessionService() {
		try {
			return (CentroGastoSessionService) ServiceLocator
					.getService(ServiceLocator.CENTROGASTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ClienteZonaSessionService getClienteZonaSessionService() {
		try {
			return (ClienteZonaSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEZONASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoBodegaSessionService getTipoBodegaSessionService() {
		try {
			return (TipoBodegaSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOBODEGASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PedidoDetalleSessionService getPedidoDetalleSessionService() {
		try {
			return (PedidoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PEDIDODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoOrdenSessionService getTipoOrdenSessionService() {
		try {
			return (TipoOrdenSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOORDENSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static VentasTrackSessionService getVentasTrackSessionService() {
		try {
			return (VentasTrackSessionService) ServiceLocator
					.getService(ServiceLocator.VENTASTRACKSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GastoElectoralAbonoSessionService getGastoElectoralAbonoSessionService() {
		try {
			return (GastoElectoralAbonoSessionService) ServiceLocator
					.getService(ServiceLocator.GASTOELECTORALABONOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CampanaBriefSessionService getCampanaBriefSessionService() {
		try {
			return (CampanaBriefSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANABRIEFSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoBusquedaSessionService getProductoBusquedaSessionService() {
		try {
			return (ProductoBusquedaSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTOBUSQUEDASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SubtipoOrdenSessionService getSubtipoOrdenSessionService() {
		try {
			return (SubtipoOrdenSessionService) ServiceLocator
					.getService(ServiceLocator.SUBTIPOORDENSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static NoticiasSessionService getNoticiasSessionService() {
		try {
			return (NoticiasSessionService) ServiceLocator
					.getService(ServiceLocator.NOTICIASSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpleadoFamiliaresSessionService getEmpleadoFamiliaresSessionService() {
		try {
			return (EmpleadoFamiliaresSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOFAMILIARESSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriManagerSessionService getSriManagerSessionService() {
		try {
			return (SriManagerSessionService) ServiceLocator
					.getService(ServiceLocator.SRIMANAGERSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GastoSessionService getGastoSessionService() {
		try {
			return (GastoSessionService) ServiceLocator
					.getService(ServiceLocator.GASTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrigenDocumentoSessionService getOrigenDocumentoSessionService() {
		try {
			return (OrigenDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.ORIGENDOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TiempoParcialDotDetalleSessionService getTiempoParcialDotDetalleSessionService() {
		try {
			return (TiempoParcialDotDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.TIEMPOPARCIALDOTDETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static UsuarioCuentaSessionService getUsuarioCuentaSessionService() {
		try {
			return (UsuarioCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriSustentoTributarioSessionService getSriSustentoTributarioSessionService() {
		try {
			return (SriSustentoTributarioSessionService) ServiceLocator
					.getService(ServiceLocator.SRISUSTENTOTRIBUTARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LogCarteraDetalleSessionService getLogCarteraDetalleSessionService() {
		try {
			return (LogCarteraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.LOGCARTERADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoIdentificacionSessionService getTipoIdentificacionSessionService() {
		try {
			return (TipoIdentificacionSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOIDENTIFICACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriIvaSessionService getSriIvaSessionService() {
		try {
			return (SriIvaSessionService) ServiceLocator
					.getService(ServiceLocator.SRIIVASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PeriodoDetalleSessionService getPeriodoDetalleSessionService() {
		try {
			return (PeriodoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PERIODODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CarteraDetalleSessionService getCarteraDetalleSessionService() {
		try {
			return (CarteraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.CARTERADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static VentasDocumentosSessionService getVentasDocumentosSessionService() {
		try {
			return (VentasDocumentosSessionService) ServiceLocator
					.getService(ServiceLocator.VENTASDOCUMENTOSSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriAirSessionService getSriAirSessionService() {
		try {
			return (SriAirSessionService) ServiceLocator
					.getService(ServiceLocator.SRIAIRSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EquipoTrabajoSessionService getEquipoTrabajoSessionService() {
		try {
			return (EquipoTrabajoSessionService) ServiceLocator
					.getService(ServiceLocator.EQUIPOTRABAJOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudTransferenciaSessionService getSolicitudTransferenciaSessionService() {
		try {
			return (SolicitudTransferenciaSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUD_TRANSFERENCIA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudTransferenciaDetalleSessionService getSolicitudTransferenciaDetalleEJBSessionService() {
		try {
			return (SolicitudTransferenciaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUD_TRANSFERENCIA_DETALLE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PuntosTipoProductoSessionService getPuntosTipoProductoSessionService() {
		try {
			return (PuntosTipoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PUNTOS_TIPO_PRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static TarjetaSessionService getTarjetaSessionService() { try {
	 * return (TarjetaSessionService) ServiceLocator
	 * .getService(ServiceLocator.TARJETASESSION_SERVICE); } catch (Exception e) {
	 * SpiritAlert.createAlert( "Se ha producido un error de comunicación con el
	 * servidor", SpiritAlert.ERROR); e.printStackTrace(); return null; } }
	 * 
	 * public static TarjetaTransaccionSessionService
	 * getTarjetaTransaccionSessionService() { try { return
	 * (TarjetaTransaccionSessionService) ServiceLocator
	 * .getService(ServiceLocator.TARJETA_TRANSACCIONSESSION_SERVICE); } catch
	 * (Exception e) { SpiritAlert.createAlert( "Se ha producido un error de
	 * comunicación con el servidor", SpiritAlert.ERROR); e.printStackTrace();
	 * return null; } }
	 */

	public static TarjetaTipoSessionService getTarjetaTipoSessionService() {
		try {
			return (TarjetaTipoSessionService) ServiceLocator
					.getService(ServiceLocator.TARJETA_TIPOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TarjetaCreditoSessionService getTarjetaCreditoSesionSessionService() {
		try {
			return (TarjetaCreditoSessionService) ServiceLocator
					.getService(ServiceLocator.TARJETACREDITOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TarjetaSessionService getTarjetaSessionService() {
		try {
			return (TarjetaSessionService) ServiceLocator
					.getService(ServiceLocator.TARJETASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TarjetaTransaccionSessionService getTarjetaTransaccionSessionService() {
		try {
			return (TarjetaTransaccionSessionService) ServiceLocator
					.getService(ServiceLocator.TARJETA_TRANSACCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriProveedorRetencionSessionService getProveedorRetencionSessionService() {
		try {
			return (SriProveedorRetencionSessionService) ServiceLocator
					.getService(ServiceLocator.SRIPROVEEDORRETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriAirSessionService getAirSessionService() {
		try {
			return (SriAirSessionService) ServiceLocator
					.getService(ServiceLocator.SRIAIRSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriTipoComprobanteSessionService getTipoComrobanteSessionService() {
		try {
			return (SriTipoComprobanteSessionService) ServiceLocator
					.getService(ServiceLocator.SRITIPOCOMPROBANTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert
					.createAlert(
							"Se ha producido un error de comunicaci\u00f3n con el servidor",
							SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriSustentoTributarioSessionService getSustentoTributarioSessionService() {
		try {
			return (SriSustentoTributarioSessionService) ServiceLocator
					.getService(ServiceLocator.SRISUSTENTOTRIBUTARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert
					.createAlert(
							"Se ha producido un error de comunicaci\u00f3n con el servidor",
							SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SriCamposFormularioSessionService getSriCamposFormularioSessionService() {
		try {
			return (SriCamposFormularioSessionService) ServiceLocator
					.getService(ServiceLocator.SRICAMPOSFORMULARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionColorSessionService getPersonalizacionColorSessionService() {
		try {
			return (PersonalizacionColorSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_COLOR_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionLogoDisenioSessionService getPersonalizacionLogoDisenioSessionService() {
		try {
			return (PersonalizacionLogoDisenioSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_LOGO_DISENIO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionDisenioSessionService getPersonalizacionDisenioSessionService() {
		try {
			return (PersonalizacionDisenioSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_DISENIO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionTamanioSessionService getPersonalizacionTamanioSessionService() {
		try {
			return (PersonalizacionTamanioSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_TAMANIO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionTipoImpresionSessionService getPersonalizacionTipoImpresionSessionService() {
		try {
			return (PersonalizacionTipoImpresionSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_TIPO_IMPRESION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionTipoLetraSessionService getPersonalizacionTipoLetraSessionService() {
		try {
			return (PersonalizacionTipoLetraSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_TIPO_LETRA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionTipoPersonalizacionSessionService getPersonalizacionTipoPersonalizacionSessionService() {
		try {
			return (PersonalizacionTipoPersonalizacionSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_TIPO_PERSONALIZACION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static PersonalizacionUbicacionSessionService getPersonalizacionUbicacionSessionService() {
		try {
			return (PersonalizacionUbicacionSessionService) ServiceLocator
					.getService(ServiceLocator.PERSONALIZACION_UBICACION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static StockOperativoSessionService getStockOperativoSessionService() {
		try {
			return (StockOperativoSessionService) ServiceLocator
					.getService(ServiceLocator.STOCK_OPERATIVO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ImpuestoRentaPersNatSessionService getImpuestoRentaPersNatSessionService() {
		try {
			return (ImpuestoRentaPersNatSessionService) ServiceLocator
					.getService(ServiceLocator.IMPUESTO_RENTA_PERS_NAT_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoPlantillaSessionService getContratoPlantillaSessionService() {
		try {
			return (ContratoPlantillaSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATO_PLANTILLA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoPlantillaDetalleSessionService getContratoPlantillaDetalleSessionService() {
		try {
			return (ContratoPlantillaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATO_PLANTILLA_DETALLE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoGastoDeducibleSessionService getContratoGastoDeducibleSessionService() {
		try {
			return (ContratoGastoDeducibleSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATO_GASTO_DEDUCIBLE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static GastoDeducibleSessionService getGastoDeducibleSessionService() {
		try {
			return (GastoDeducibleSessionService) ServiceLocator
					.getService(ServiceLocator.GASTO_DEDUCIBLE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ContratoUtilidadSessionService getContratoUtilidadSessionService() {
		try {
			return (ContratoUtilidadSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATO_UTILIDAD_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolPagoDetalleUtilidadSessionService getRolPagoDetalleUtilidadSessionService() {
		try {
			return (RolPagoDetalleUtilidadSessionService) ServiceLocator
					.getService(ServiceLocator.ROL_PAGO_DETALLE_UTILIDAD_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CuentasPorCobrarSessionService getCuentasPorCobrarSessionService() {
		try {
			return (CuentasPorCobrarSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTAS_POR_COBRAR_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CuentasPorPagarSessionService getCuentasPorPagarSessionService() {
		try {
			return (CuentasPorPagarSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTAS_POR_PAGAR_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SalarioMinimoVitalSessionService getSalarioMinimoVitalSessionService() {
		try {
			return (SalarioMinimoVitalSessionService) ServiceLocator
					.getService(ServiceLocator.SALARIO_MINIMO_VITAL_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProcesoCompraSessionService getProcesoCompraSessionService() {
		try {
			return (ProcesoCompraSessionService) ServiceLocator
					.getService(ServiceLocator.BPM_PROCESO_COMPRAS_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static ProcesoOrdenTrabajoSessionService getProcesoOrdenTrabajoSessionService() {
		try {
			return (ProcesoOrdenTrabajoSessionService) ServiceLocator
					.getService(ServiceLocator.BPM_PROCESO_ORDEN_TRABAJO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static UtilitariosSessionService getUtilitariosSessionService() {
		try {
			return (UtilitariosSessionService) ServiceLocator
					.getService(ServiceLocator.UTILITARIOS_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static BiSessionService getBiSessionService() {
		try {
			return (BiSessionService) ServiceLocator
					.getService(ServiceLocator.BI_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static AsociacionTransaccionSessionService getAsociacionTransaccionSessionService() {
		try {
			return (AsociacionTransaccionSessionService) ServiceLocator
					.getService(ServiceLocator.ASOCIACION_TRANSACCION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static CarteraRelacionadaSessionService getCarteraRelacionadaSessionService() {
		try {
			return (CarteraRelacionadaSessionService) ServiceLocator
					.getService(ServiceLocator.CARTERA_RELACIONADA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static PlanMedioFormaPagoSessionService getPlanMedioFormaPagoSessionService() {
		try {
			return (PlanMedioFormaPagoSessionService) ServiceLocator
					.getService(ServiceLocator.PLAN_MEDIO_FORMA_PAGO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static PresupuestoFacturacionSessionService getPresupuestoFacturacionSessionService() {
		try {
			return (PresupuestoFacturacionSessionService) ServiceLocator
					.getService(ServiceLocator.PRESUPUESTOFACTURACION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static LogEquipoEmpleadoSessionService getLogEquipoEmpleadoSessionService() {
		try {
			return (LogEquipoEmpleadoSessionService) ServiceLocator
					.getService(ServiceLocator.LOGEQUIPOEMPLEADO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	//ADD 19 SEPTIEMBRE
	//Se crea el metodo correspondiente a la nueva Tabla creada
	public static CampanaProductoVersionSessionService getCampanaProductoVersionSessionService() {
		try {
			return (CampanaProductoVersionSessionService) ServiceLocator
					.getService(ServiceLocator.CAMPANA_PRODUCTO_VERSION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static OrdenAsociadaSessionService getOrdenAsociadaSessionService() {
		try {
			return (OrdenAsociadaSessionService) ServiceLocator.getService(ServiceLocator.ORDENASOCIADASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static AsientoDescuadradoSessionService getAsientoDescuadradoSessionService() {
		try {
			return (AsientoDescuadradoSessionService) ServiceLocator.getService(ServiceLocator.ASIENTO_DESCUADRADO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static AsientoTmpSessionService getAsientoTmpSessionService() {
		try {
			return (AsientoTmpSessionService) ServiceLocator.getService(ServiceLocator.ASIENTO_TMP_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static AsientoDetalleTmpSessionService getAsientoDetalleTmpSessionService() {
		try {
			return (AsientoDetalleTmpSessionService) ServiceLocator.getService(ServiceLocator.ASIENTO_DETALLE_TMP_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static ImpuestoRentaSessionService getImpuestoRentaSessionService() {
		try {
			return (ImpuestoRentaSessionService) ServiceLocator.getService(ServiceLocator.IMPUESTO_RENTA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static PautaGenericoClienteSessionService getPautaGenericoClienteSessionService() {
		try {
			return (PautaGenericoClienteSessionService) ServiceLocator.getService(ServiceLocator.PAUTA_GENERICO_CLIENTE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static FacturaDetalleCompraAsociadaSessionService getFacturaDetalleCompraAsociadaSessionService() {
		try {
			return (FacturaDetalleCompraAsociadaSessionService) ServiceLocator.getService(ServiceLocator.FACTURADETALLECOMPRAASOCIADASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Timetracker2SessionService getTimetracker2SessionService() {
		try {
			return (Timetracker2SessionService) ServiceLocator.getService(ServiceLocator.TIMETRACKER2_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Timetracker2EmpleadoSessionService getTimetracker2EmpleadoSessionService() {
		try {
			return (Timetracker2EmpleadoSessionService) ServiceLocator.getService(ServiceLocator.TIMETRACKER2_EMPLEADO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Timetracker2DetalleSessionService getTimetracker2DetalleSessionService() {
		try {
			return (Timetracker2DetalleSessionService) ServiceLocator.getService(ServiceLocator.TIMETRACKER2_DETALLE_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Timetracker2TiempoSessionService getTimetracker2TiempoSessionService() {
		try {
			return (Timetracker2TiempoSessionService) ServiceLocator.getService(ServiceLocator.TIMETRACKER2_TIEMPO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static EmpleadoVacacionesSessionService getEmpleadoVacacionesSessionService() {
		try {
			return (EmpleadoVacacionesSessionService) ServiceLocator.getService(ServiceLocator.EMPLEADO_VACACIONES_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static EmpleadoOrganizacionSessionService getEmpleadoOrganizacionSessionService() {
		try {
			return (EmpleadoOrganizacionSessionService) ServiceLocator.getService(ServiceLocator.EMPLEADO_ORGANIZACION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static ClienteRetencionSessionService getClienteRetencionSessionService() {
		try {
			return (ClienteRetencionSessionService) ServiceLocator.getService(ServiceLocator.CLIENTE_RETENCION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static OverComisionSessionService getOverComisionSessionService() {
		try {
			return (OverComisionSessionService) ServiceLocator.getService(ServiceLocator.OVER_COMISION_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static HerramientasMediosSessionService getHerramientasMediosSessionService() {
		try {
			return (HerramientasMediosSessionService) ServiceLocator.getService(ServiceLocator.HERRAMIENTAS_MEDIOS_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static CarteraPagoSessionService getCarteraPagoSessionService() {
		try {
			return (CarteraPagoSessionService) ServiceLocator.getService(ServiceLocator.CARTERA_PAGO_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	//TABLA HECHA EXCLUSIVAMENTE PARA PROBAR EL SOFTWARE BI SISENSE
	public static PresupuestosSessionService getPresupuestosSessionService() {
		try {
			return (PresupuestosSessionService) ServiceLocator.getService(ServiceLocator.PRESUPUESTOS_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
