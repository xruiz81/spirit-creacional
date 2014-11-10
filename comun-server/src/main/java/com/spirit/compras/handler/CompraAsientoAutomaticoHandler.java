package com.spirit.compras.handler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;

@Stateless
public class CompraAsientoAutomaticoHandler implements CompraAsientoAutomaticoHandlerLocal {
	@PersistenceContext(unitName = "spirit")
	@EJB private GenericoSessionLocal genericoLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private PeriodoSessionLocal periodoLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;	
	@EJB private PlanCuentaSessionLocal planCuentaLocal;	
	@EJB private SubTipoAsientoSessionLocal subTipoAsientoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private EventoContableSessionLocal eventoContableLocal;
	@EJB private DocumentoSessionLocal documentoLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaSessionLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaSessionLocal;
	@EJB private ClienteSessionLocal clienteSessionLocal;
	
	private PlanCuentaIf planCuenta = null;
	private PeriodoIf periodo = null;
	private static List<AsientoDetalleIf> asientoDetalleColeccion = null;
	private EventoContableIf eventoContable = null;
	private Object referenceBean = null;
	private Map parameterMap = null;
	private boolean procesarAsiento = false;
	private Long empresaId = 0L;
	private Long oficinaId = 0L;
	private UsuarioIf usuario;
	private static double iva;
	private static double ctaxpag;
	
	public AsientoIf generarAsientoAutomatico(CompraIf compra, CompraDetalleIf compraDetalle, CompraIf compraAnterior, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario, ClienteIf clienteAsociado) throws GenericBusinessException {
		Map parameterMap = new HashMap();
		DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(compraDetalle.getDocumentoId());
		this.usuario = usuario;
		if ( documento != null ) {
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByDocumentoId(documento.getId()).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
				if (eventoContable != null) {
					Double descuento = Double.valueOf(compraDetalle.getDescuento().doubleValue());
					Double iva = Double.valueOf(compraDetalle.getIva().doubleValue());
					Double ice = 0D;
					if (compraDetalle.getIce() != null)
						ice = Double.valueOf(compraDetalle.getIce().doubleValue());
					Double valor = Double.valueOf(compraDetalle.getValor().doubleValue());
					double cantidad = compraDetalle.getCantidad().doubleValue();
					double subtotal = cantidad * valor;
					double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
					double otroImpuesto = Double.valueOf(compraDetalle.getOtroImpuesto().doubleValue());
					double porcentajeBonificacion = compraDetalle.getPorcentajeDescuentosVarios().doubleValue();
					double bonificacion = (subtotal - descuentoEspecial) * (porcentajeBonificacion / 100D);
					Double total = subtotal - descuentoEspecial - descuento - bonificacion + iva + ice + otroImpuesto;
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", compra.getOficinaId());
					parameterMap.put("BEAN", compra);
					parameterMap.put("COMPRA_ANTERIOR", compraAnterior);
					parameterMap.put("OBSERVACION", "COMPRA");
					parameterMap.put("ID_SRI_SUSTENTO_TRIBUTARIO", compra.getIdSriSustentoTributario());
										
					if (!documento.getCodigo().equals("COMI") && !documento.getCodigo().equals("SERB") && !documento.getCodigo().equals("CIIN")) {
						if (documento.getCodigo().equals("COIN")) {
							parameterMap.put("INVENTARIO", total - iva);
							parameterMap.put("COSTO", 0D);
						} else {
							parameterMap.put("INVENTARIO", 0D);
							parameterMap.put("COSTO", total - iva);
						}
						parameterMap.put("IVA", iva);
						this.iva += iva;
					} else {
						if (documento.getCodigo().equals("CIIN")) {
							parameterMap.put("INVENTARIO", total);
							parameterMap.put("COSTO", 0D);
						} else {
							parameterMap.put("INVENTARIO", 0D);
							parameterMap.put("COSTO", total);
						}
						parameterMap.put("IVA", 0D);
					}
					parameterMap.put("REEMBOLSO", total);
					parameterMap.put("CTAXPAG", total);
					this.ctaxpag += total;
					parameterMap.put("PRODUCTO_ID", compraDetalle.getProductoId());
					ClienteOficinaIf proveedorOficina = clienteOficinaSessionLocal.getClienteOficina(compra.getProveedorId());
					ClienteIf proveedor = clienteSessionLocal.getCliente(proveedorOficina.getClienteId());
					parameterMap.put("PROVEEDOR_ID", proveedor.getId());
					if (clienteAsociado != null)
						parameterMap.put("CLIENTE_ID", clienteAsociado.getId());
					parameterMap.put("TIPO_DOCUMENTO_ID", compra.getTipodocumentoId());
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(compra.getTipodocumentoId());
					parameterMap.put("TIPO_DOCUMENTO_CODIGO", tipoDocumento.getCodigo());
					parameterMap.put("DESCRIPCION_DETALLE", compraDetalle.getDescripcion());
					
					return procesarCompraAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, actualizar);
				}
			}
		}
		return null;
	}
	
	public AsientoIf procesarCompraAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento, boolean actualizar) throws GenericBusinessException {
		this.eventoContable = eventoContable;
		
		try {
			this.planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener Plan de Cuenta de Evento Contable");
		}
		
		this.referenceBean = parameterMap.get("BEAN");
		this.parameterMap = parameterMap;
		this.procesarAsiento = procesarAsiento;
		this.empresaId = eventoContable.getEmpresaId();
		this.oficinaId = (Long) parameterMap.get("OFICINA_ID");
		
		return generarAsientoContable(actualizar);
	}
	
	public AsientoIf generarAsientoContable(boolean actualizar) throws GenericBusinessException {

		AsientoIf asientoRetornar = null;
		try {
			Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;
			Map aMap = new HashMap();
			aMap.put("codigo", "ECIGLC");
			aMap.put("empresaId", eventoContable.getEmpresaId());
			Iterator parametroEmpresaIterator = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(aMap).iterator();
			boolean eciglc = false; // Especificar cuenta imputable de gasto en liquidación de compra;
			if (parametroEmpresaIterator.hasNext()) {
				ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) parametroEmpresaIterator.next();
				if (parametroEmpresa.getValor().equals("S"))
					eciglc = true;
			}

			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
				Map parameterMap = new HashMap();
				
				if (cuenta.getImputable().equals("N") && (!this.parameterMap.get("TIPO_DOCUMENTO_CODIGO").toString().equals("LIC") || eciglc)) {
					if (plantilla.getNemonico().equals("RETEFUENTE")) {
						Long idCuentaRetefuente = (Long) this.parameterMap.get("ID_CUENTA_RETEFUENTE");
						if (idCuentaRetefuente != null)
							cuenta = cuentaLocal.getCuenta(idCuentaRetefuente);
					} else if (plantilla.getNemonico().equals("RETEIVA"))  {
						Long idCuentaReteiva = (Long) this.parameterMap.get("ID_CUENTA_RETEIVA");
						if (idCuentaReteiva != null)
							cuenta = cuentaLocal.getCuenta(idCuentaReteiva);
					} else {
						if (plantilla.getNemonico().equals("COSTO")) {
							Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
							ProductoIf producto = productoLocal.getProducto(idProducto);
							GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							parameterMap.put("entidadId", idProducto);
							parameterMap.put("tipoEntidad", "I");
							parameterMap.put("oficinaId", this.oficinaId);
							if (generico != null) {
								if (generico.getMediosProduccion().equals("M") || generico.getMediosProduccion().equals("P"))
									parameterMap.put("nemonico", "COSTO");
								else if (generico.getMediosProduccion().equals("G"))
									parameterMap.put("nemonico", "GASTO");
							}
							Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
							if (!cuentaEntidadIterator.hasNext()) {
								parameterMap.put("entidadId", generico.getId());
								parameterMap.put("tipoEntidad", "G");
							}
						}
						
						if (plantilla.getNemonico().equals("INVENTARIO")) {
							Long idProducto = (Long) this.parameterMap.get("PRODUCTO_ID");
							ProductoIf producto = productoLocal.getProducto(idProducto);
							GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							parameterMap.put("entidadId", idProducto);
							parameterMap.put("tipoEntidad", "I");
							parameterMap.put("nemonico", "INVENTARIO");
						}
						
						if (plantilla.getNemonico().equals("CTAXPAG")) {
							Long idProveedor = (Long) this.parameterMap.get("PROVEEDOR_ID");
							parameterMap.put("entidadId", idProveedor);
							parameterMap.put("tipoEntidad", "P");
							parameterMap.put("nemonico", "CTAXPAG");
						}
						
						if (plantilla.getNemonico().equals("IVA")) {
							Long idSriSustentoTributario = (Long) this.parameterMap.get("ID_SRI_SUSTENTO_TRIBUTARIO");
							parameterMap.put("entidadId", idSriSustentoTributario);
							parameterMap.put("tipoEntidad", "U");
							parameterMap.put("nemonico", "IVA");
						}
						
						if (plantilla.getNemonico().equals("REEMBOLSO")) {
							Long idCliente = (Long) this.parameterMap.get("CLIENTE_ID");
							if (idCliente != null) {
								parameterMap.put("entidadId", idCliente);
								parameterMap.put("tipoEntidad", "C");
								parameterMap.put("nemonico", "REEMBOLSO");
							}
						}
						
						if (parameterMap.size() > 0) {
							Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(parameterMap).iterator();
							if (cuentaEntidadIterator.hasNext()) {
								CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
								cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
							} else {
								cuenta = null;
							}
						} else {
							cuenta = null;
						}
					}
				}
				
				if (cuenta == null) {
					if (plantilla.getCuentaPredeterminadaId() != null)
						cuenta = cuentaLocal.getCuenta(plantilla.getCuentaPredeterminadaId());
					else
						throw new GenericBusinessException("No existe cuenta asociada para nemónico " + (String) parameterMap.get("nemonico"));
				}
				
				if (cuenta != null) {
					AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
					asientoDetalle.setCuentaId(cuenta.getId());
					String numeroFactura = ((CompraIf) referenceBean).getPreimpreso();
					asientoDetalle.setReferencia("FACT. # " + numeroFactura);
					asientoDetalle.setGlosa("COMPRA/FACT. # " + ((CompraIf) referenceBean).getPreimpreso() + " " + ((CompraIf) referenceBean).getObservacion());
					double valor = ((Double) this.parameterMap.get(plantilla.getNemonico())).doubleValue();
					if (plantilla.getNemonico().equals("CTAXPAG") && eventoContable.getAgruparDetalles().equals("N"))
						valor = this.ctaxpag;
					else if (plantilla.getNemonico().equals("IVA") && eventoContable.getAgruparDetalles().equals("N"))
						valor = this.iva;
					
					if (plantilla.getDebehaber().equals("D")) {
						asientoDetalle.setDebe(BigDecimal.valueOf(valor));
						asientoDetalle.setHaber(BigDecimal.ZERO);
					} else if (plantilla.getDebehaber().equals("H")) {
						asientoDetalle.setHaber(BigDecimal.valueOf(valor));
						asientoDetalle.setDebe(BigDecimal.ZERO);
					}
					
					if (valor > 0.0) {
						if (eventoContable.getAgruparDetalles().equals("S") || (eventoContable.getAgruparDetalles().equals("N") && (plantilla.getNemonico().equals("COSTO") || plantilla.getNemonico().equals("INVENTARIO")))) {
							if (eventoContable.getAgruparDetalles().equals("N"))
								asientoDetalle.setGlosa("COMPRA/FACT. # " + ((CompraIf) referenceBean).getPreimpreso() + " " + (String) this.parameterMap.get("DESCRIPCION_DETALLE"));
							asientoDetalleColeccion.add(asientoDetalle);
						} else if (eventoContable.getAgruparDetalles().equals("N") && procesarAsiento) {
							if (plantilla.getNemonico().equals("IVA"))
								asientoDetalle.setDebe(BigDecimal.valueOf(utilitariosLocal.redondeoValor(this.iva)));
							else if (plantilla.getNemonico().equals("CTAXPAG"))
								asientoDetalle.setHaber(BigDecimal.valueOf(utilitariosLocal.redondeoValor(this.ctaxpag)));
							asientoDetalleColeccion.add(asientoDetalle);
						}
					}
				}
			}
			
			if (procesarAsiento) {
				AsientoIf asiento = registrarAsiento();
				if (asiento != null) {
					if (eventoContable.getAgruparDetalles().equals("S"))
						asientoDetalleColeccion = asientoLocal.agruparDetalles(asientoDetalleColeccion);
					asientoDetalleColeccion = asientoLocal.redondearDetallesAsiento(asientoDetalleColeccion);
					String numeroAsiento = "";
					if (!actualizar)
						numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					else {
						CompraIf compraAnterior = (CompraIf) this.parameterMap.get("COMPRA_ANTERIOR");
						boolean reversarSaldos = false;
						Map parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", compraAnterior.getTipodocumentoId());
						parameterMap.put("transaccionId", ((CompraIf) referenceBean).getId());
						Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							AsientoIf asientoAnterior = (AsientoIf) it.next();
							List<AsientoDetalleIf> asientoDetalleAnteriorColeccion = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoAnterior.getId());
							boolean actualizarNumeroAsiento = true;
							asiento.setId(asientoAnterior.getId());
							java.sql.Date fechaAsientoAnterior = new java.sql.Date(asientoAnterior.getFecha().getTime());
							int yearAsientoAnterior = fechaAsientoAnterior.getYear() + 1900;
							int monthAsientoAnterior = fechaAsientoAnterior.getMonth() + 1;
							java.sql.Date fechaAsiento = new java.sql.Date(asiento.getFecha().getTime());
							int yearAsiento = fechaAsiento.getYear() + 1900;
							int monthAsiento = fechaAsiento.getMonth() + 1;
							if (yearAsientoAnterior == yearAsiento && monthAsientoAnterior == monthAsiento) {
								asiento.setNumero(asientoAnterior.getNumero());
								actualizarNumeroAsiento = false;
							}
							if (asientoAnterior.getStatus().equals("A"))
								reversarSaldos = true;
							numeroAsiento = ((AsientoIf) asientoLocal.actualizarAsiento(asiento, asientoDetalleColeccion, asientoAnterior, asientoDetalleAnteriorColeccion, asientoDetalleAnteriorColeccion, reversarSaldos, actualizarNumeroAsiento, this.usuario, new HashMap(), new HashMap(), new HashMap(), new HashMap(), false).get("ASIENTO")).getNumero();
						} else
							numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
					}
					//Busco el asiento generado para retornarlo
					Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
					if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
						asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Ocurri\u00f3 un error al generar el asiento");
		}
		
		return asientoRetornar;
	}
	
	private AsientoIf registrarAsiento() throws GenericBusinessException {
		AsientoData data = new AsientoData();
		try {			
			Date fechaAsiento = ((CompraIf) this.parameterMap.get("BEAN")).getFecha();
			if (validateAsientoAutomatico(empresaId, fechaAsiento)) {
				String strEstado = "P";	
				String unNumeroAsiento = asientoLocal.getNumeroAsiento(fechaAsiento, empresaId, planCuenta);
				data.setEmpresaId(this.empresaId);
				data.setNumero(unNumeroAsiento);
				data.setStatus(strEstado);
				data.setPeriodoId(periodo.getId());
				data.setPlancuentaId(planCuenta.getId());
				data.setObservacion(((CompraIf) referenceBean).getObservacion());
				data.setOficinaId(this.oficinaId);
				data.setFecha(fechaAsiento);
				if (eventoContable.getSubtipoAsientoId() != null)
					data.setEfectivo("S");
				else
					data.setEfectivo("N");
				data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
				data.setTipoDocumentoId((Long) this.parameterMap.get("TIPO_DOCUMENTO_ID"));
				data.setTransaccionId(((CompraIf) referenceBean).getId());
				data.setElaboradoPorId(((CompraIf) referenceBean).getUsuarioId());
				data.setEventoContableId(eventoContable.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en el registro del asiento");
		}
		
		return data;
	}
	
	public boolean validateAsientoAutomatico(Long empresaId, java.sql.Date fechaAsiento) {
		try {
			//se debe hacer clear a filtroMap, cuando existe mas de un registro que validar.
			Map filtroMap = new HashMap();
			filtroMap.put("empresaId", empresaId);
			filtroMap.put("estado", "A");
			Iterator planCuentaIterator = planCuentaLocal.findPlanCuentaByQuery(filtroMap).iterator();
			
			if (planCuentaIterator.hasNext())
				planCuenta = (PlanCuentaIf) planCuentaIterator.next();
			else {
				return false;
			}
				
			Iterator periodoIterator = periodoLocal.findPeriodoForAsientoAutomatico(empresaId, fechaAsiento).iterator();
			
			if (periodoIterator.hasNext())
				periodo = (PeriodoIf) periodoIterator.next();
			else
				return false;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public List<AsientoDetalleIf> getAsientoDetalleColeccion() {
		return asientoDetalleColeccion;
	}

	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion) {
		CompraAsientoAutomaticoHandler.asientoDetalleColeccion = asientoDetalleColeccion;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		CompraAsientoAutomaticoHandler.iva = iva;
	}

	public double getCtaxpag() {
		return ctaxpag;
	}

	public void setCtaxpag(double ctaxpag) {
		CompraAsientoAutomaticoHandler.ctaxpag = ctaxpag;
	}
}
