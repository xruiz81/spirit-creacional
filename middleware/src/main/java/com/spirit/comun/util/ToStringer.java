package com.spirit.comun.util;

import org.apache.commons.beanutils.BeanUtils;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoContactoIf;
import com.spirit.crm.entity.TipoIndicadorIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.MotivoDocumentoIf;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CentroGastoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CotizacionIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.NoticiasIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.PaisIf;
import com.spirit.general.entity.ProvinciaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.EmbalajeIf;
import com.spirit.inventario.entity.FamiliaGenericoIf;
import com.spirit.inventario.entity.FuncionBodegaIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MedidaIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.TipoBodegaIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.GeneroProgramaIf;
import com.spirit.medios.entity.GrupoObjetivoIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PrensaFormatoIf;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.entity.PrensaTarifaIf;
import com.spirit.medios.entity.PrensaUbicacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.nomina.entity.GastoDeducibleIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.pos.entity.TarjetaCreditoIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.pos.entity.TeclasConfiguracionIf;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.rrhh.entity.IdiomaIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.entity.SriTipoTransaccionIf;

public class ToStringer {

	private static String defaultCodigo(Object entity) {
		try {
			String codigo = BeanUtils.getProperty(entity, "codigo");
			return codigo;
		} catch (Exception e) {
			System.out
			.println("-------Error ToStringer defaultCodigo para tipo: "
					+ entity.getClass());
			return entity.toString();
		}
	}

	private static String defaultNombre(Object entity) {
		try {
			return BeanUtils.getProperty(entity, "nombre");
		} catch (Exception e) {
			System.out
			.println("-------Error ToStringer defaultNombre para : "
					+ entity.getClass());
			return entity.toString();
		}
	}
	
	private static String nombreRubro(Object entity) {
		try {
			RubroIf r = (RubroIf) entity;
			String tipoRubro = TipoRubro.getTipoRubroByLetra(r.getTipoRubro()).toString();
			//return BeanUtils.getProperty(entity, "nombre");
			return r.getNombre()+" ("+tipoRubro+")";
		} catch (Exception e) {
			System.out
			.println("-------Error ToStringer defaultNombre para tipo: "
					+ entity.getClass());
			return entity.toString();
		}
	}

	private static String defaultCodigoNombre(Object entity) {
		try {
			String codigo = BeanUtils.getProperty(entity, "codigo");
			String nombre = BeanUtils.getProperty(entity, "nombre");
			return codigo + " - " + nombre;
		} catch (Exception e) {
			System.out
			.println("-------Error ToStringer defaultCodigoNombre para tipo: "
					+ entity.getClass());
			return entity.toString();
		}
	}

	public static String toString(Object entity) {

		if (entity instanceof FormaPagoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof CompraIf) {
			return defaultCodigo(entity);
		} else if (entity instanceof GastoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof OrdenCompraIf) {
			return defaultCodigo(entity);
		} else if (entity instanceof AsientoIf) {
			return ((AsientoIf) entity).getNumero();
		} else if (entity instanceof CuentaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof EventoContableIf) {
			return defaultNombre(entity);
		} else if (entity instanceof PeriodoIf) {
			PeriodoIf periodo = (PeriodoIf) entity;
			return (periodo.getCodigo() + "     (" + periodo.getFechaini()
					+ " - " + periodo.getFechafin() + ")");
		} else if (entity instanceof PlanCuentaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof SubtipoAsientoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoAsientoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoCuentaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoResultadoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof ClienteContactoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof ClienteIf) {
			return ((ClienteIf) entity).getNombreLegal();
		} else if (entity instanceof ClienteOficinaIf) {
			return ((ClienteOficinaIf) entity).getDescripcion();
		} else if (entity instanceof ClienteZonaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof CorporacionIf) {
			return defaultNombre(entity);
		} else if (entity instanceof CorporacionIf) {
			return defaultNombre(entity);
		} else if (entity instanceof TipoContactoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoIndicadorIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoNegocioIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof FacturaIf) {
			return ((FacturaIf) entity).getNumero().toString();
		} else if (entity instanceof ListaPrecioIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof MotivoDocumentoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof BancoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof CajaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof CentroGastoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof CiudadIf) {
			return defaultNombre(entity);
		} else if (entity instanceof CotizacionIf) {
			return ((CotizacionIf) entity).getCotizacion().toString();
		}

		else if (entity instanceof CuentaBancariaIf) {
			CuentaBancariaIf cuentaBancariaIf = (CuentaBancariaIf) entity;
			String tipoCuenta = "";
			// Importante que aparezca el #, porque se lo usa para hacer split
			// en Cheques Emitidos
			if (cuentaBancariaIf.getTipocuenta().equals("A"))
				tipoCuenta = "CTA. AHORROS   # ";
			else if (cuentaBancariaIf.getTipocuenta().equals("C"))
				tipoCuenta = "CTA. CORRIENTE # ";

			return tipoCuenta + cuentaBancariaIf.getCuenta();
		} else if (entity instanceof DepartamentoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof DocumentoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof EmpleadoIf) {
			EmpleadoIf empleadoIf = (EmpleadoIf) entity;
			String nombre = "";
			String apellido = "";

			int espacioBlanco = empleadoIf.getNombres().indexOf(' ');
			if ((espacioBlanco > 0)
					&& !empleadoIf.getNombres().substring(0, espacioBlanco)
					.equals("MARIA"))
				nombre = empleadoIf.getNombres().substring(0, espacioBlanco);
			else
				nombre = empleadoIf.getNombres();
			espacioBlanco = empleadoIf.getApellidos().indexOf(' ');
			if (espacioBlanco > 0)
				apellido = empleadoIf.getApellidos()
				.substring(0, espacioBlanco);
			else
				apellido = empleadoIf.getApellidos();
			String nombreCompletoRemitente = /*empleadoIf.getIdentificacion()
			+ " - " + empleadoIf.getCodigo() + " - " +*/ nombre + " "
			+ apellido;

			return (nombreCompletoRemitente);

		} else if (entity instanceof EmpresaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof LineaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof ModuloIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof MonedaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof NoticiasIf) {
			return ((NoticiasIf) entity).getAsunto() + " - " + "("
			+ ((NoticiasIf) entity).getFechaIni() + ")";
		} else if (entity instanceof OficinaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof OrigenDocumentoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof PaisIf) {
			return defaultNombre(entity);
		} else if (entity instanceof ProvinciaIf) {
			return defaultNombre(entity);
		} else if (entity instanceof PuntoImpresionIf) {
			return ((PuntoImpresionIf) entity).getImpresora() + " - "
			+ ((PuntoImpresionIf) entity).getSerie();
		} else if (entity instanceof TipoArchivoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoClienteIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoDocumentoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof TipoEmpleadoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoIdentificacionIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoOrdenIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoPagoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoParametroIf) {
			return defaultNombre(entity);
		} else if (entity instanceof TipoProveedorIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoTroqueladoIf) {
			return ((TipoTroqueladoIf) entity).getDescripcion();
		} else if (entity instanceof UsuarioIf) {
			return ((UsuarioIf) entity).getUsuario();
		}
		if (entity instanceof BodegaIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof ColorIf) {
			return defaultNombre(entity);
		} else if (entity instanceof FuncionBodegaIf) {
			return defaultCodigoNombre(entity);
		}
		else if (entity instanceof EmbalajeIf) {
			return defaultCodigoNombre(entity);
		}

		else if (entity instanceof LoteIf) {
			return defaultCodigo(entity);
		} else if (entity instanceof ModeloIf) {
			return defaultNombre(entity);
		} else if (entity instanceof PresentacionIf) {
			return defaultCodigoNombre(entity);
		}
		else if (entity instanceof FamiliaGenericoIf) {
			return defaultCodigoNombre(entity);
		}
		else if (entity instanceof MedidaIf) {
			return defaultCodigoNombre(entity);
		} 
		else if (entity instanceof TipoBodegaIf) {
			return defaultCodigoNombre(entity);
		}
		else if (entity instanceof TipoProductoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof IdiomaIf) {
			return defaultNombre(entity);
		} else if (entity instanceof MenuIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof RolIf) {
			return ((RolIf) entity).getNombre() + " ("
			+ ((RolIf) entity).getCodigo() + ") ";
		} else if (entity instanceof RubroIf) {
			return nombreRubro(entity);
		} else if (entity instanceof TipoContratoIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TipoRolIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof GastoDeducibleIf) {
			return defaultCodigoNombre(entity);
		} else if (entity instanceof TarjetaCreditoIf) {
			return defaultNombre(entity);
		} else if (entity instanceof TeclasConfiguracionIf) {
			return defaultCodigo(entity);
		} else if (entity instanceof VentasConsolidadoIf) {
			VentasConsolidadoIf ventasConsolidadoIf = (VentasConsolidadoIf) entity;
			return ventasConsolidadoIf.getFechaCierre() != null ? ventasConsolidadoIf
					.getFechaCierre().toString()
					: "";
		}
		else if (entity instanceof SriAirIf) {		 
			SriAirIf sriAirIf = (SriAirIf) entity;			
			return "[" + sriAirIf.getPorcentaje().toString() + "%] "+" - "+defaultCodigo(entity)+" - "+ sriAirIf.getConcepto().toUpperCase();		
		}
		else if (entity instanceof SriClienteRetencionIf) {		 
			SriClienteRetencionIf sriClienteRetencionIf = (SriClienteRetencionIf) entity;			
			return (sriClienteRetencionIf.getTipoRetencion().equals("R")?"RENTA" + ", " + sriClienteRetencionIf.getPorcentajeRetencion() + " %" : "IVA" + ", " + sriClienteRetencionIf.getPorcentajeRetencion() + " %");
		}		
		else if (entity instanceof SriIvaRetencionIf) {		 
			SriIvaRetencionIf sriIvaRetencionIf = (SriIvaRetencionIf) entity;			
			return "[" + sriIvaRetencionIf.getPorcentaje().toString() + "%] "+" - "+defaultCodigo(entity)+" - "+ sriIvaRetencionIf.getConcepto().toUpperCase();
		}
		else if (entity instanceof SriProveedorRetencionIf) {		 
			SriProveedorRetencionIf sriProveedorRetencionIf = (SriProveedorRetencionIf) entity;			
			return sriProveedorRetencionIf.getRetefuente().toString();
		}
		else if (entity instanceof SriSustentoTributarioIf) {		 
			SriSustentoTributarioIf sriSustentoTributarioIf = (SriSustentoTributarioIf) entity;			
			return "[" + defaultCodigo(entity) + "] " + sriSustentoTributarioIf.getDescripcion();
		}
		else if (entity instanceof SriTipoComprobanteIf) {
			return defaultNombre(entity);
		}
		else if (entity instanceof SriTipoTransaccionIf) {		 
			SriTipoTransaccionIf sriTipoTransaccionIf = (SriTipoTransaccionIf) entity;			
			return defaultCodigo(entity)+ " - " + defaultNombre(entity);
		}
		else if (entity instanceof TarjetaTipoIf) {		 
			TarjetaTipoIf tarjetaTipoIf = (TarjetaTipoIf) entity; 	
			return defaultCodigo(entity)+ " - " + defaultNombre(entity);
		}

		else if (entity instanceof CampanaDetalleIf) {		 
			CampanaDetalleIf campanaDetalleIf = (CampanaDetalleIf) entity;			
			return campanaDetalleIf.getDescripcion();
		}else if (entity instanceof CampanaIf) {		 
			return defaultCodigo(entity)+ " - " + defaultNombre(entity);
		}//ADD 28 SEPTIEMBRE
		else if (entity instanceof CampanaProductoVersionIf){
			//CampanaProductoVersionIf cpv = (CampanaProductoVersionIf) entity;
			return defaultNombre(entity);
		}//END 28 SEPTIEMBRE	
		else if (entity instanceof ComercialIf) {		 
			ComercialIf comercialIf = (ComercialIf) entity;			
			//return (defaultNombre(entity)+ " (" + comercialIf.getDuracion() +  " seg)"); COMENTED 7 SEPTIEMBRE
			return (defaultNombre(entity)+ " (" + comercialIf.getVersion() +  ")"); //ADD 7 SEPTIEMBRE
		}else if (entity instanceof DerechoProgramaIf) {
			return defaultCodigo(entity)+ " - " + defaultNombre(entity);
		}else if (entity instanceof EquipoTrabajoIf) {
			return defaultCodigo(entity);
		}else if (entity instanceof GeneroProgramaIf) {	
			return defaultNombre(entity);
		}else if (entity instanceof OrdenMedioIf) {	
			return defaultCodigo(entity);
		}else if (entity instanceof MarcaProductoIf) {	
			return defaultNombre(entity);
		}else if (entity instanceof OrdenTrabajoDetalleIf) {
			OrdenTrabajoDetalleIf otd = (OrdenTrabajoDetalleIf) entity;
			return otd.getFechalimite().toString();
		}else if (entity instanceof OrdenTrabajoIf) {
			OrdenTrabajoIf ot = (OrdenTrabajoIf) entity;
			return ot.getCodigo() + " - " + ot.getDescripcion();
		}else if (entity instanceof PlanMedioIf) {	
			return defaultCodigo(entity);
		}else if (entity instanceof PrensaFormatoIf) {
			PrensaFormatoIf pf = (PrensaFormatoIf) entity;
			return pf.getFormato();
		}else if (entity instanceof GrupoObjetivoIf) {
			return defaultCodigoNombre(entity);
		}else if (entity instanceof PrensaSeccionIf) {
			PrensaSeccionIf ps = (PrensaSeccionIf) entity;
			return ps.getSeccion();
		}else if (entity instanceof PrensaUbicacionIf) {
			PrensaUbicacionIf pu = (PrensaUbicacionIf) entity;
			return pu.getUbicacion();
		}else if (entity instanceof PresupuestoIf) {
			PresupuestoIf p = (PresupuestoIf) entity;
			return ("P." + p.getCodigo() + " " + p.getConcepto());
		}else if (entity instanceof ProductoClienteIf){
			ProductoClienteIf pc = (ProductoClienteIf) entity;
			return ("(" + pc.getMarcaProductoNombre() + ") - " + pc.getNombre());
		}else if (entity instanceof SubtipoOrdenIf){
			return defaultCodigoNombre(entity);
		}else if (entity instanceof PrensaTarifaIf) {
			PrensaTarifaIf pt = (PrensaTarifaIf) entity;

			String NOMBRE_COLOR_BN = "B/N";
			String NOMBRE_COLOR_COLOR = "COLOR";
			String COLOR_BN = "B";
			String COLOR_COLOR = "C";
			String NOMBRE_DIA_ORDINARIO = "ORDINARIO";
			String NOMBRE_DIA_SABADO = "SABADO";
			String NOMBRE_DIA_DOMINGO = "DOMINGO ";
			String NOMBRE_DIA_FERIADO = "FERIADO";
			String DIA_ORDINARIO = "O";
			String DIA_SABADO = "S";
			String DIA_DOMINGO = "D";
			String DIA_FERIADO = "F";
			String color = "";
			String dia = "";

			if(pt.getColor().equals(COLOR_COLOR)){
				color = NOMBRE_COLOR_COLOR;
			}else if(pt.getColor().equals(COLOR_BN)){
				color = NOMBRE_COLOR_BN;
			}
			if(pt.getDia().equals(DIA_DOMINGO)){
				dia = NOMBRE_DIA_DOMINGO;
			}else if(pt.getDia().equals(DIA_ORDINARIO)){
				dia = NOMBRE_DIA_ORDINARIO;
			}else if(pt.getDia().equals(DIA_SABADO)){
				dia = NOMBRE_DIA_SABADO;
			}else if(pt.getDia().equals(DIA_FERIADO)){
				dia = NOMBRE_DIA_FERIADO;
			}
			return (color + " - " + dia);
		} else if (entity instanceof CarteraIf) {
			CarteraIf c = (CarteraIf) entity;
			return c.getCodigo();
		}



		else {
			System.out.println("Llamada a toString de: " + entity.getClass()
					+ " no definida..");
			return entity.getClass().getName();
		}
	}
}
