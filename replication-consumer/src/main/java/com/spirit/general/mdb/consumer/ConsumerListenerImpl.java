package com.spirit.general.mdb.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.commons.beanutils.PropertyUtils;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.session.CarteraSessionLocal;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.facturacion.session.PedidoSessionLocal;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.ActualizarPreimpresoCarteraMessage;
import com.spirit.general.mdb.messages.bo.ActualizarPreimpresoMessage;
import com.spirit.general.mdb.messages.bo.AsientoPosMessage;
import com.spirit.general.mdb.messages.bo.CierreStockMessage;
import com.spirit.general.mdb.messages.bo.ClienteMessage;
import com.spirit.general.mdb.messages.bo.DevolucionMessage;
import com.spirit.general.mdb.messages.bo.FacturaPosMessage;
import com.spirit.general.mdb.messages.bo.FacturaPosMessageLocal;
import com.spirit.general.mdb.messages.bo.ReciboCajaPOSMessage;
import com.spirit.general.mdb.messages.bo.SolicitudTransferenciaMessage;
import com.spirit.general.mdb.messages.bo.StockMessage;
import com.spirit.general.mdb.messages.bo.TransferirDocPosMessage;
import com.spirit.general.mdb.messages.bo.VentasConsolidadasMessage;
import com.spirit.general.mdb.producer.JmsProducerLocal;
import com.spirit.general.session.TipoClienteSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.session.MovimientoSessionLocal;
import com.spirit.inventario.session.SolicitudTransferenciaSessionLocal;
import com.spirit.inventario.session.StockSessionLocal;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.VentasConsolidadoData;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.session.CajaSesionSessionLocal;
import com.spirit.pos.session.VentasConsolidadoSessionLocal;

@Stateless
public class ConsumerListenerImpl implements ConsumerListenerLocal {
	@EJB
	private MovimientoSessionLocal movimientoSessionLocal;

	@EJB
	private SolicitudTransferenciaSessionLocal solicitudTransferenciaSessionLocal;

	@EJB
	private ClienteSessionLocal clienteSessionLocal;
	
	@EJB
	private TipoClienteSessionLocal tipoClienteSessionLocal;

	@EJB
	private JmsProducerLocal jmsProducerLocal;

	@EJB
	private PedidoSessionLocal pedidoSessionLocal;

	@EJB
	private FacturaSessionLocal facturaSessionLocal;

	@EJB
	private UsuarioSessionLocal usuarioLocal;

	@EJB
	private JPAManagerLocal jpaManagerLocal;

	@EJB
	private VentasConsolidadoSessionLocal ventasConsolidadoSessionLocal;

	@EJB
	private StockSessionLocal stockSessionLocal;

	@EJB
	private CajaSesionSessionLocal cajaSesionSessionLocal;

	@EJB
	private ClienteOficinaSessionLocal clienteOficinaSessionLocal;

	@EJB
	private CarteraSessionLocal carteraSessionLocal;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarMensage(Message message) {
		String name = null;
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) message;
				Object obj = objMessage.getObject();
				if (obj instanceof StockMessage) {
					StockMessage stockMessage = (StockMessage) obj;
					MovimientoIf cabecera = (MovimientoIf) quitarID(stockMessage.getCabecera());
					List<Object> listaDetalle = quitarID(stockMessage.getObjects());
					UsuarioIf usuario = (UsuarioIf) usuarioLocal.getUsuario(cabecera.getUsuarioId());
					movimientoSessionLocal.procesarMovimiento(cabecera, listaDetalle, usuario);
				} else if (obj instanceof SolicitudTransferenciaMessage) {
					SolicitudTransferenciaMessage solicitudTransferenciaMessage = (SolicitudTransferenciaMessage) obj;
					SolicitudTransferenciaIf cabecera = (SolicitudTransferenciaIf) quitarID(solicitudTransferenciaMessage.getCabecera());
					List<Object> listaDetalle = quitarID(solicitudTransferenciaMessage.getObjects());
					solicitudTransferenciaSessionLocal.procesarSolicitudTransferencia(cabecera,listaDetalle);
				} else if (obj instanceof ClienteMessage) {
					ClienteMessage clienteMessage = (ClienteMessage) obj;
					ClienteIf clienteIf = (ClienteIf) clienteMessage.getClienteIf();
					List<ClienteZonaIf> listaClienteZonaList = clienteMessage.getModelClienteZonaList();
					List<ClienteRetencionIf> listaClienteRetencionList = clienteMessage.getModelClienteRetencionList();
					List<ClienteOficinaIf> listaClienteOficinaList = clienteMessage.getModelClienteOficinaList();
					Map modelClienteContactoMap = clienteMessage.getModelClienteContactoMap();
					Map modelClienteIndicadorMap = clienteMessage.getModelClienteIndicadorMap();
					List<ClienteZonaIf> detalleZonaRemovidaClienteList = clienteMessage.getDetalleZonaRemovidaClienteList();
					List<ClienteRetencionIf> detalleRetencionRemovidaClienteList = clienteMessage.getDetalleRetencionRemovidaClienteList();
					List<ClienteOficinaIf> detalleOficinaRemovidaClienteList = clienteMessage.getDetalleOficinaRemovidaClienteList();
					Map detalleContactoRemovidoClienteList = clienteMessage.getDetalleContactoRemovidoClienteList();
					Map detalleIndicadorRemovidoClienteList = clienteMessage.getDetalleIndicadorRemovidoClienteList();
					/** ********************************************** */
					String operacion = clienteMessage.getOperacion();
					if (operacion.equalsIgnoreCase("I")) {
						try {
							clienteSessionLocal.procesarCliente(clienteIf,
									listaClienteZonaList, listaClienteRetencionList,
									listaClienteOficinaList,
									modelClienteContactoMap,
									modelClienteIndicadorMap, false);
						} catch (GenericBusinessException ex) {
							System.out.println("ERROR AL RECIBIR CLIENTE: " + ex.getMessage());
							return;
						}
					} else if (operacion.equalsIgnoreCase("U")) {
						// ACTUALIZACION
						/*clienteSessionLocal.actualizarCliente(clienteIf,
								listaClienteZonaList, listaClienteRetencionList,
								listaClienteOficinaList,
								modelClienteContactoMap,
								modelClienteIndicadorMap,
								detalleZonaRemovidaClienteList,
								detalleRetencionRemovidaClienteList,
								detalleOficinaRemovidaClienteList,
								detalleContactoRemovidoClienteList,
								detalleIndicadorRemovidoClienteList, false);*/
					} else if (operacion.equalsIgnoreCase("E")) {
						// ELIMINACION
						TipoClienteIf tipoCliente = tipoClienteSessionLocal.getTipoCliente(clienteIf.getTipoclienteId());
						if (clienteSessionLocal.getClienteByIdentificacionAndEmpresaId(clienteIf.getIdentificacion(), tipoCliente.getEmpresaId()) != null)
							clienteSessionLocal.eliminarCliente(clienteIf.getIdentificacion(), tipoCliente.getEmpresaId(), false);
					} else {
						System.out.println("TIPO DE MENSAJE NO ENCONTRADO: " + operacion);
					}
					if (clienteMessage.getSourceType().equalsIgnoreCase("E")) {
						String quienEnvio = clienteMessage.getSource();
						jmsProducerLocal.sendMessageToAllMenosPrincipalYoParametro(clienteMessage, quienEnvio);
					}

				} else if (obj instanceof FacturaPosMessage) {
					FacturaPosMessageLocal facturaPosMessage = (FacturaPosMessageLocal) obj;

					// ************************************************************/
					// EN EL PROCESO DE FACTURACION DEL POS SE GRABAN LOS
					// CLIENTES POR SEPARADO
					// NO SE REPLICAN AHI, SINO AL FINAL YA QUE PODRIA HABER
					// PROBLEMAS
					// DE SINCRONIA EN EL MENSAJE

					ClienteIf clienteIf = (ClienteIf) facturaPosMessage.getCliente();
					ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf) facturaPosMessage
							.getClienteOficina();
					ClienteOficinaIf clienteOficinaConsulta = clienteSessionLocal
							.getClienteOficina(clienteIf.getIdentificacion(),
									clienteOficinaIf.getCodigo());

					if (clienteOficinaConsulta == null) {
						// NO EXISTE EL CLIENTE
						clienteIf = (ClienteIf) quitarID(clienteIf);
						clienteOficinaIf = (ClienteOficinaIf) quitarID(clienteOficinaIf);
						Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
						Vector<ClienteZonaIf> clienteZonaVacio = new Vector<ClienteZonaIf>();
						Vector<ClienteRetencionIf> clienteRetencionVacio = new Vector<ClienteRetencionIf>();

						detalleOficinaClienteColeccion.add(clienteOficinaIf);

						Map detalleContactoOficinaClienteMap = new HashMap();
						Map detalleIndicadorOficinaClienteMap = new HashMap();

						clienteSessionLocal.procesarCliente(clienteIf,
								clienteZonaVacio,
								clienteRetencionVacio,
								detalleOficinaClienteColeccion,
								detalleContactoOficinaClienteMap,
								detalleIndicadorOficinaClienteMap, true);
						// VOLVER A CONSULTAR PARA TENER ID
						clienteOficinaConsulta = clienteSessionLocal
								.getClienteOficina(clienteIf
										.getIdentificacion(), clienteOficinaIf
										.getCodigo());
					}

					PedidoIf pedido = (PedidoIf) facturaPosMessage
							.getPedidoAnterior();

					Map parametros = facturaPosMessage.getParametros();
					parametros.put("CLIENTE", clienteIf);
					parametros.put("CLIENTE_OFICINA", clienteOficinaConsulta);

					Map resultMap = new HashMap();
					resultMap = facturaSessionLocal.generarFacturaPOS(
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetalleGiftcardVector(),
							(Vector<TarjetaIf>) facturaPosMessage.getPedidoDetalleTarjetaAfiliacionVector(),
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetalleFacturaVector(),
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetalleNotaVentaVector(),
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetallePersonalizacionVector(),
							(VentasTrackIf) facturaPosMessage.getVentasTrack(),
							(Vector<Vector>) facturaPosMessage.getPagoDetalleVector(),
							facturaPosMessage.getDonacion(),
							facturaPosMessage.getIdfundacion(),
							pedido,
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetalleEliminadosVector(),
							(Vector<PedidoDetalleIf>) facturaPosMessage.getPedidoDetalleProcesoVector(),
							(Vector<Vector>) facturaPosMessage.getTarjetasCollectionDetalles(),
							parametros, true);
					Long idFacturaNueva = (Long) resultMap.get("FACTURA_ID");
					Long idReciboCajaNuevo = (Long) resultMap.get("RECIBO_CAJA_ID");
					if (idFacturaNueva.compareTo(0L) != 0) {
						FacturaIf factura = facturaSessionLocal.getFactura(idFacturaNueva);
						if (factura.getPreimpresoNumero() == null) {
							factura.setPreimpresoNumero(String.valueOf(facturaPosMessage.getIdFacturaOrigen()));
							facturaSessionLocal.saveFactura(factura);
						}
					}
					if (idReciboCajaNuevo.compareTo(0L) != 0) {
						CarteraIf reciboCaja = carteraSessionLocal.getCartera(idReciboCajaNuevo);
						if (reciboCaja.getPreimpreso() == null) {
							reciboCaja.setPreimpreso(String.valueOf(facturaPosMessage.getIdReciboCajaOrigen()));
							carteraSessionLocal.saveCartera(reciboCaja);
						}
					}
				} else if (obj instanceof AsientoPosMessage) {
					AsientoPosMessage asientoPosMessage = (AsientoPosMessage) obj;
					cajaSesionSessionLocal.generarAsientos(asientoPosMessage.getMapaAsientos(), asientoPosMessage.getIdEmpresa(), asientoPosMessage.getIdOficina(),asientoPosMessage.getCajero());

				} else if (obj instanceof VentasConsolidadasMessage) {
					VentasConsolidadasMessage ventasConsolidadasMessage = (VentasConsolidadasMessage) obj;
					VentasConsolidadoIf ventasConsolidadoIf = (VentasConsolidadoIf) (VentasConsolidadoIf) ventasConsolidadasMessage.getVentaConsolidada();
					if (ventasConsolidadoIf.getFechaCierre() == null) {
						ventasConsolidadoIf.setId(null);
						ventasConsolidadoSessionLocal.addVentasConsolidado(ventasConsolidadoIf);

					} else {
						// CONSULTAR EL VENTASCONSOLIDADO Y MODIFICAR
						VentasConsolidadoIf ventasConsolidadoIfConsulta = ventasConsolidadoSessionLocal.getVentasConsolidado(ventasConsolidadoIf.getCajaCodigo(), ventasConsolidadoIf.getCajeroCedula());
						boolean nuevo = false;
						if (ventasConsolidadoIfConsulta == null) {
							nuevo = true;
							ventasConsolidadoIfConsulta = new VentasConsolidadoData();
						}

						ventasConsolidadoIfConsulta.setFechaCierre(ventasConsolidadoIf.getFechaCierre());
						ventasConsolidadoIfConsulta.setValorCajaEgreso(ventasConsolidadoIf.getValorCajaEgreso());
						ventasConsolidadoIfConsulta.setValorCajaIngreso(ventasConsolidadoIf.getValorCajaIngreso());
						ventasConsolidadoIfConsulta.setValorCajaInicial(ventasConsolidadoIf.getValorCajaInicial());
						ventasConsolidadoIfConsulta.setValorCheque(ventasConsolidadoIf.getValorCheque());
						ventasConsolidadoIfConsulta.setValorCredito(ventasConsolidadoIf.getValorCredito());
						ventasConsolidadoIfConsulta.setValorDescuadre(ventasConsolidadoIf.getValorDescuadre());
						ventasConsolidadoIfConsulta.setValorDevoluciones(ventasConsolidadoIf.getValorDevoluciones());
						ventasConsolidadoIfConsulta.setValorDocumentos(ventasConsolidadoIf.getValorDocumentos());
						ventasConsolidadoIfConsulta.setValorDonacion(ventasConsolidadoIf.getValorDonacion());
						ventasConsolidadoIfConsulta.setValorEfectivo(ventasConsolidadoIf.getValorEfectivo());
						ventasConsolidadoIfConsulta.setValorGiftcards(ventasConsolidadoIf.getValorGiftcards());
						ventasConsolidadoIfConsulta.setValorMultas(ventasConsolidadoIf.getValorMultas());
						ventasConsolidadoIfConsulta.setValorTarjeta(ventasConsolidadoIf.getValorTarjeta());

						if (nuevo) {
							ventasConsolidadoSessionLocal.addVentasConsolidado(ventasConsolidadoIfConsulta);
						} else
							ventasConsolidadoSessionLocal.saveVentasConsolidado(ventasConsolidadoIfConsulta);
					}
				}

				else if (obj instanceof ActualizarPreimpresoMessage) {
					ActualizarPreimpresoMessage actualizarPreimpresoMessage = (ActualizarPreimpresoMessage) obj;
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: PREIMPRESO >>>>>>>>>>>" + actualizarPreimpresoMessage.getPreImpreso());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: ID FACTURA >>>>>>>>>>>" + actualizarPreimpresoMessage.getIdFacturaOrigen());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: ID OFICINA >>>>>>>>>>>" + actualizarPreimpresoMessage.getIdOficina());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: PROPAGAR MENSAJE >>>>>>>>>>>" + actualizarPreimpresoMessage.getPropagarMensaje());
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					FacturaIf factura = facturaSessionLocal.getFacturaByPreimpresoAndOficina(actualizarPreimpresoMessage.getIdOficina(),actualizarPreimpresoMessage.getIdFacturaOrigen());
					facturaSessionLocal.actualizarPreimpreso(factura, actualizarPreimpresoMessage.getPreImpreso(), actualizarPreimpresoMessage.getPropagarMensaje());
				}
				
				else if (obj instanceof ActualizarPreimpresoCarteraMessage) {
					ActualizarPreimpresoCarteraMessage actualizarPreimpresoCarteraMessage = (ActualizarPreimpresoCarteraMessage) obj;
					Map parameterMap = new HashMap();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: PREIMPRESO >>>>>>>>>>>" + actualizarPreimpresoCarteraMessage.getPreImpreso());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: ID CARTERA >>>>>>>>>>>" + actualizarPreimpresoCarteraMessage.getIdCarteraOrigen());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: ID OFICINA >>>>>>>>>>>" + actualizarPreimpresoCarteraMessage.getIdOficina());
					System.out.println(">>>>>>>>>>>> CONSUMER LISTENER: ID T. DOC. >>>>>>>>>>>" + actualizarPreimpresoCarteraMessage.getIdTipoDocumento());
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					parameterMap.put("oficinaId", actualizarPreimpresoCarteraMessage.getIdOficina());
					parameterMap.put("preimpreso", String.valueOf(actualizarPreimpresoCarteraMessage.getIdCarteraOrigen()));
					parameterMap.put("tipodocumentoId", actualizarPreimpresoCarteraMessage.getIdTipoDocumento());
					Iterator it = carteraSessionLocal.findCarteraByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						System.out.println(">>>>>>>>>>>>>>>>>> INGRESO A ACTUALIZAR PREIMPRESO");
						CarteraIf cartera = (CarteraIf) it.next();
						carteraSessionLocal.actualizarPreimpreso(cartera, actualizarPreimpresoCarteraMessage.getPreImpreso());
					}
				}

				else if (obj instanceof DevolucionMessage) {
					DevolucionMessage spiritMessenger = (DevolucionMessage) obj;

					FacturaIf facturaAfectada = ((List<FacturaIf>) facturaSessionLocal
							.findFacturaByPreimpresoNumero(spiritMessenger
									.getPreImpresoFacturaAfectada(),
									spiritMessenger.getIdoficina(),
									spiritMessenger.getTipoDocumentoId()))
							.get(0);
					// tomamos el clienteOficina que envia el cliente...
					// buscamos por identificacion en el servidor de khomo
					// encontramos y seteamos datos de clienteoficina y
					// cliente(johanna)

					ClienteIf clienteIf = (ClienteIf) spiritMessenger
							.getClienteIf();
					ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf) spiritMessenger
							.getClienteOficinaIf();
					ClienteOficinaIf clienteOficinaConsulta = clienteSessionLocal
							.getClienteOficina(clienteIf.getIdentificacion(),
									clienteOficinaIf.getCodigo());

					if (clienteOficinaConsulta == null) {
						// NO EXISTE EL CLIENTE
						clienteIf = (ClienteIf) quitarID(clienteIf);
						clienteOficinaIf = (ClienteOficinaIf) quitarID(clienteOficinaIf);
						Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
						Vector<ClienteZonaIf> clienteZonaVacio = new Vector<ClienteZonaIf>();
						Vector<ClienteRetencionIf> clienteRetencionVacio = new Vector<ClienteRetencionIf>();

						detalleOficinaClienteColeccion.add(clienteOficinaIf);

						Map detalleContactoOficinaClienteMap = new HashMap();
						Map detalleIndicadorOficinaClienteMap = new HashMap();

						clienteSessionLocal.procesarCliente(clienteIf,
								clienteZonaVacio,
								clienteRetencionVacio,
								detalleOficinaClienteColeccion,
								detalleContactoOficinaClienteMap,
								detalleIndicadorOficinaClienteMap, true);
						// VOLVER A CONSULTAR PARA TENER ID
						clienteOficinaConsulta = clienteSessionLocal
								.getClienteOficina(clienteIf
										.getIdentificacion(), clienteOficinaIf
										.getCodigo());
						clienteIf = clienteSessionLocal
								.getCliente(clienteOficinaConsulta
										.getClienteId());

					}
					
					System.out.println("---------------------------------------------");
					System.out.println("---------------------------------------------");
					System.out.println("---------------------------------------------");
					System.out.println("FACTURA AFECTADA ID = " + facturaAfectada.getId());
					System.out.println("EMPLEADO = " + ((EmpleadoIf) spiritMessenger.getEmpleado()).getNombres());
					System.out.println("CLIENTE OFICINA CONSULTA = " + clienteOficinaConsulta.getDescripcion());
					System.out.println("CLIENTE = " + clienteIf.getNombreLegal());
					System.out.println("PUNTO IMPRESION = " + ((PuntoImpresionIf) spiritMessenger.getPuntoImpresionIf()).getImpresora());
					System.out.println("DETALLE DEVOLUCION VECTOR = " + ((Vector<FacturaDetalleIf>) spiritMessenger.getDetalleDevolucionVector()).size());
					System.out.println("TARJETAS COLECCION DETALLES = " + ((Vector<Vector>) spiritMessenger.getTarjetasCollection_detalles()).size());
					System.out.println("IVA TOTAL = " + spiritMessenger.getIvaTotal());
					System.out.println("DESCUENTO TOTAL = " + spiritMessenger.getDescuentoTotal());
					System.out.println("DESCUENTO GLOBAL TOTAL = " + spiritMessenger.getDescuentoGlobalTotal());
					System.out.println("VENTAS TRACK = " + ((VentasTrackIf) spiritMessenger.getVentasTrack()).getValorTotal());
					System.out.println("ID EMPRESA = " + spiritMessenger.getIdempresa());
					System.out.println("ID OFICINA = " + spiritMessenger.getIdoficina());
					System.out.println("USUARIO = " + ((UsuarioIf) spiritMessenger.getUsuario()).getUsuario());
					System.out.println("FECHA DEVOLUCION = " + new java.sql.Timestamp(spiritMessenger.getFechaDevolucion()).toString());
					System.out.println("APD = " + spiritMessenger.getApd());
					System.out.println("ATPTT = " + spiritMessenger.getAtptt());
					System.out.println("---------------------------------------------");
					System.out.println("---------------------------------------------");
					System.out.println("---------------------------------------------");

					Long idFacturaNueva = facturaSessionLocal.saveDevolucion(
							facturaAfectada.getId(),
							(EmpleadoIf) spiritMessenger.getEmpleado(),
							clienteOficinaConsulta, clienteIf,
							(PuntoImpresionIf) spiritMessenger
									.getPuntoImpresionIf(),
							(Vector<FacturaDetalleIf>) spiritMessenger
									.getDetalleDevolucionVector(),
								(Vector<Vector>) spiritMessenger.getTarjetasCollection_detalles(),
							spiritMessenger.getIvaTotal(), spiritMessenger
									.getSubTotal(), spiritMessenger
									.getDescuentoTotal(), spiritMessenger
									.getDescuentoGlobalTotal(),
							(VentasTrackIf) spiritMessenger.getVentasTrack(),
							spiritMessenger.getIdempresa(), spiritMessenger
									.getIdoficina(),
							(UsuarioIf) spiritMessenger.getUsuario(),
							new java.sql.Timestamp(spiritMessenger.getFechaDevolucion()),
							spiritMessenger.getApd(),
							spiritMessenger.getAtptt(),
							true);

					/*
					 * codigo anterior
					 * 
					 * ClienteOficinaIf
					 * clienteOficina=clienteOficinaSessionLocal.getClienteOficina(facturaAfectada.getClienteoficinaId());
					 * ClienteIf
					 * cliente=clienteSessionLocal.getCliente(clienteOficina.getClienteId());
					 * 
					 * Long idFacturaNueva = facturaSessionLocal.saveDevolucion(
					 * facturaAfectada.getId(), (EmpleadoIf)
					 * spiritMessenger.getEmpleado(), clienteOficina, cliente,
					 * (PuntoImpresionIf) spiritMessenger
					 * .getPuntoImpresionIf(), (Vector<FacturaDetalleIf>)
					 * spiritMessenger .getDetalleDevolucionVector(),
					 * spiritMessenger.getIvaTotal(), spiritMessenger
					 * .getSubTotal(), spiritMessenger .getDescuentoTotal(),
					 * spiritMessenger .getDescuentoGlobalTotal(),
					 * (VentasTrackIf) spiritMessenger.getVentasTrack(),
					 * spiritMessenger.getIdempresa(), spiritMessenger
					 * .getIdoficina(), (UsuarioIf)
					 * spiritMessenger.getUsuario());
					 */

					FacturaIf factura = facturaSessionLocal
							.getFactura(idFacturaNueva);
					if (factura.getPreimpresoNumero() == null) {
						factura.setPreimpresoNumero(String
								.valueOf(spiritMessenger.getIdFacturaOrigen()));
						facturaSessionLocal.saveFactura(factura);
					}
				} else if (obj instanceof ReciboCajaPOSMessage) {
					ReciboCajaPOSMessage reciboCajaPOSMessage = (ReciboCajaPOSMessage) obj;
					Vector<Vector> detallesPagos = reciboCajaPOSMessage.getDetallesPagos();
					Map<String, Object> parametros = reciboCajaPOSMessage.getParametros();
					String preimpreso = reciboCajaPOSMessage.getPreimpreso();

					ClienteIf clienteIf = (ClienteIf) parametros.get("CLIENTE");
					ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf) (ClienteOficinaIf) parametros
							.get("CLIENTE_OFICINA");
					ClienteOficinaIf clienteOficinaConsulta = clienteSessionLocal
							.getClienteOficina(clienteIf.getIdentificacion(),
									clienteOficinaIf.getCodigo());

					if (clienteOficinaConsulta == null) {
						// NO EXISTE EL CLIENTE
						clienteIf = (ClienteIf) quitarID(clienteIf);
						clienteOficinaIf = (ClienteOficinaIf) quitarID(clienteOficinaIf);
						Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
						Vector<ClienteZonaIf> clienteZonaVacio = new Vector<ClienteZonaIf>();
						Vector<ClienteRetencionIf> clienteRetencionVacio = new Vector<ClienteRetencionIf>();
						
						detalleOficinaClienteColeccion.add(clienteOficinaIf);

						Map detalleContactoOficinaClienteMap = new HashMap();
						Map detalleIndicadorOficinaClienteMap = new HashMap();

						clienteSessionLocal.procesarCliente(clienteIf,
								clienteZonaVacio,
								clienteRetencionVacio,
								detalleOficinaClienteColeccion,
								detalleContactoOficinaClienteMap,
								detalleIndicadorOficinaClienteMap, true);
						

						// VOLVER A CONSULTAR PARA TENER ID
						clienteOficinaConsulta = clienteSessionLocal
								.getClienteOficina(clienteIf
										.getIdentificacion(), clienteOficinaIf
										.getCodigo());
						
						clienteIf =clienteSessionLocal.getCliente(clienteOficinaConsulta.getClienteId());
						
					}
					parametros.put("CLIENTE", clienteIf);
					parametros.put("CLIENTE_OFICINA", clienteOficinaConsulta);
					

					CarteraIf cartera = carteraSessionLocal
							.getCartera(carteraSessionLocal
									.generarReciboCajaPOS(detallesPagos,
											parametros, true));

					carteraSessionLocal.actualizarPreimpreso(cartera,
							preimpreso);

				} else if (obj instanceof CierreStockMessage) {
					CierreStockMessage spiritMessenger = (CierreStockMessage) obj;
					stockSessionLocal
							.cerrarStock(spiritMessenger.getIdBodega());
				}else if (obj instanceof TransferirDocPosMessage) {
					TransferirDocPosMessage transferirDocPosMessage=(TransferirDocPosMessage)obj;
					CarteraIf comprobanteRecibido=transferirDocPosMessage.getComprobanteOriginal();
					HashMap<String, Object> query=new HashMap<String, Object>();
					query.put("referenciaId",comprobanteRecibido.getReferenciaId());
					query.put("tipodocumentoId",comprobanteRecibido.getTipodocumentoId());
					query.put("oficinaId",comprobanteRecibido.getOficinaId());
					
					CarteraIf comprobanteOriginalMatriz=((ArrayList<CarteraIf>)carteraSessionLocal.findCarteraByQuery(query)).get(0);
					
					Map parametrosEmpresa=transferirDocPosMessage.getParametrosEmpresa();
					OficinaIf oficinaOrigen=transferirDocPosMessage.getOficinaOrigen(); 
					OficinaIf oficinaDestino=transferirDocPosMessage.getOficinaDestino();
					carteraSessionLocal.transferirComprobante(comprobanteOriginalMatriz, parametrosEmpresa, oficinaOrigen, oficinaDestino, true);
					
				}
				else {
					System.out.println("OBJETO DESCONOCIDO: " + obj);
				}
			} else {
				System.err.println("Expecting Object Message");
			}
		} catch (Throwable t) {
			t.printStackTrace();
			// context.setRollbackOnly();
		}
	}

	private List quitarID(List<Object> listaEntitys) {
		try {
			for (Object o : listaEntitys) {
				quitarID(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaEntitys;
	}

	private Object quitarID(Object entity) {
		try {
			PropertyUtils.setProperty(entity, "id", null);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Long insertEntity(Object entity) {
		try {
			quitarID(entity);
			jpaManagerLocal.persist(entity);
			return (Long) PropertyUtils.getProperty(entity, "id");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
