package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.panel.JPFixAsientosTransaccionales;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;

public class FixAsientosTransaccionalesModel extends JPFixAsientosTransaccionales {
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	
	public FixAsientosTransaccionalesModel(){
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnContinuar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				procesarFixAsientosTransaccionales();
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
	public void save() {
		showSaveMode();
	}

	public void delete() {
		// TODO Auto-generated method stub		
	}

	public void update() {
		// TODO Auto-generated method stub		
	}

	public void showSaveMode() {
		setSaveMode();
	}
	
	public Map mapearCuentas() {
		Map cuentasMap = new HashMap();
		
		try {
			Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().getCuentaList().iterator();
			while (cuentasIterator.hasNext()) {
				CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasMap;
	}
		
	private void procesarFixAsientosTransaccionales() {
		try {
			Map cuentasMap = mapearCuentas();
			
			String codigoTipoDocumento = "CIN";
			TipoDocumentoIf tipoDocumentoRetencion = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(codigoTipoDocumento).iterator().next();
			Iterator carteraRetencionIterator = SessionServiceLocator.getCarteraSessionService().findCarteraByTipodocumentoId(tipoDocumentoRetencion.getId()).iterator();
			while (carteraRetencionIterator.hasNext()) {
				CarteraIf carteraRetencion = (CarteraIf) carteraRetencionIterator.next();
				if (carteraRetencion.getTipo().equals(TIPO_CARTERA_PROVEEDOR)) {
					Iterator compraIterator = SessionServiceLocator.getCarteraSessionService().findCompraByCarteraRetencionId(carteraRetencion.getId()).iterator();
					if (compraIterator.hasNext()) {
						CompraIf compra = (CompraIf) compraIterator.next();
						ClienteOficinaIf proveedorOficina = (ClienteOficinaIf) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
						ClienteIf proveedor = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
						CompraRetencionIf compraRetencion = (CompraRetencionIf) SessionServiceLocator.getCompraRetencionSessionService().findCompraRetencionByCompraId(compra.getId()).iterator().next();
						Map parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", carteraRetencion.getTipodocumentoId());
						parameterMap.put("transaccionId", carteraRetencion.getId());
						Iterator asientoRetencionIterator = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
						if (asientoRetencionIterator.hasNext()) {
							AsientoIf asiento = (AsientoIf) asientoRetencionIterator.next();
							Iterator asientoDetalleRetencionIterator = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asiento.getId()).iterator();
							while (asientoDetalleRetencionIterator.hasNext()) {
								AsientoDetalleIf asientoDetalleRetencion = (AsientoDetalleIf) asientoDetalleRetencionIterator.next();
								CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalleRetencion.getCuentaId());
								String glosa = "";
								String secuencial = "";
								if (cuenta.getCodigo().equals("21020200015") || cuenta.getCodigo().equals("21020200016") || cuenta.getCodigo().equals("21020200017")) {
									glosa = "RET. IVA";
								} else if (cuenta.getCodigo().equals("21010100001") || cuenta.getCodigo().equals("21010100002")) {
									glosa = "RET.";
								} else {
									glosa = "RET. RENTA";
								}
								
								asientoDetalleRetencion.setGlosa(glosa + " F: " + compra.getPreimpreso().substring(8,15) + "; R: " + compraRetencion.getSecuencial() + " " + proveedor.getRazonSocial());
								SessionServiceLocator.getAsientoDetalleSessionService().saveAsientoDetalle(asientoDetalleRetencion);
							}
							
							asiento.setObservacion("RET." + " F: " + compra.getPreimpreso().substring(8,15) + "; R: " + compraRetencion.getSecuencial() + " " + proveedor.getRazonSocial());
							SessionServiceLocator.getAsientoSessionService().saveAsiento(asiento);
						}
					}
				} else {
					Iterator facturaIterator = SessionServiceLocator.getCarteraSessionService().findFacturaByCarteraRetencionId(carteraRetencion.getId()).iterator();
					if (facturaIterator.hasNext()) {
						FacturaIf factura = (FacturaIf) facturaIterator.next();
						ClienteOficinaIf clienteOficina = (ClienteOficinaIf) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
						ClienteIf cliente = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
						Map parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", carteraRetencion.getTipodocumentoId());
						parameterMap.put("transaccionId", carteraRetencion.getId());
						Iterator asientoRetencionIterator = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
						if (asientoRetencionIterator.hasNext()) {
							AsientoIf asiento = (AsientoIf) asientoRetencionIterator.next();
							String datosRetencion = "";
							Iterator asientoDetalleRetencionIterator = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asiento.getId()).iterator();
							while (asientoDetalleRetencionIterator.hasNext()) {
								AsientoDetalleIf asientoDetalleRetencion = (AsientoDetalleIf) asientoDetalleRetencionIterator.next();
								CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalleRetencion.getCuentaId());
								String glosa = "";
								DocumentoIf documento = null;
								if (cuenta.getCodigo().equals("13030100010") || cuenta.getCodigo().equals("13030100011") || cuenta.getCodigo().equals("13030100012")) {
									glosa = "RET. IVA ";
									documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("REIC").iterator().next();
								} else if (cuenta.getCodigo().equals("13030050001") || cuenta.getCodigo().equals("13030050002") || cuenta.getCodigo().equals("13030050003") || cuenta.getCodigo().equals("13030050004") || cuenta.getCodigo().equals("13030050005")) {
									glosa = "RET. RENTA ";
									documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("RERC").iterator().next();
								}
								
								if (documento != null) {
									Iterator it = SessionServiceLocator.getCarteraSessionService().findCarteraDetalleByFacturaIdAndDocumentoId(factura.getId(), documento.getId()).iterator();
									if (it.hasNext()) {
										CarteraDetalleIf carteraDetalleRetencion = (CarteraDetalleIf) it.next();
										int i = 8;
										if (carteraDetalleRetencion.getPreimpreso() == null)
											carteraDetalleRetencion.setPreimpreso("");
										if (carteraDetalleRetencion.getPreimpreso().length() < 8)
											i = 0;
										datosRetencion = "; R: " + carteraDetalleRetencion.getPreimpreso().substring(i,carteraDetalleRetencion.getPreimpreso().length());
									}
								}
								
								asientoDetalleRetencion.setGlosa(glosa + "F: " + factura.getPreimpresoNumero().substring(8,15) + datosRetencion + " " + cliente.getRazonSocial());
								SessionServiceLocator.getAsientoDetalleSessionService().saveAsientoDetalle(asientoDetalleRetencion);
							}
							
							asiento.setObservacion("I: " + carteraRetencion.getCodigo().substring(13,20) + "; F: " + factura.getPreimpresoNumero().substring(8,15) + datosRetencion + " " + cliente.getRazonSocial());
							SessionServiceLocator.getAsientoSessionService().saveAsiento(asiento);
						}
					}
				}
			}

			SpiritAlert.createAlert("Proceso realizado con éxito!", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
	}
	
	public void clean() {
		// TODO Auto-generated method stub		
	}
}
