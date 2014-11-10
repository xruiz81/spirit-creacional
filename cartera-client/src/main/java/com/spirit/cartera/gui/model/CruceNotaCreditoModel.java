package com.spirit.cartera.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.gui.panel.JPCruceNotaCredito;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class CruceNotaCreditoModel extends JPCruceNotaCredito {
	private static final long serialVersionUID = -7378873443073043323L;
	private List<NotaCreditoIf> notaCreditoColeccion = new ArrayList<NotaCreditoIf>();
	private List<NotaCreditoIf> notaCreditoSeleccionadaColeccion = new ArrayList<NotaCreditoIf>();
	private Map carteraNotaCreditoMap = new HashMap();
	private Map valorAplicaMap = new HashMap();
	private ClienteOficinaIf operadorNegocio;
	private DocumentoIf documento;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private JDPopupFinderModel popupFinder;
	Map tiposDocumentoMap = new HashMap();
	Map documentosMap = new HashMap();
	private NotaCreditoAfectaModel jdNotaCreditoAfecta;
	private double saldoCredito = 0D;
	private Map afectaMap = new HashMap();
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	String tipoCartera = TIPO_CARTERA_CLIENTE;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	
	
	public CruceNotaCreditoModel() {
		setSorterTable(getTblNotasCredito());
		getTblNotasCredito().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		iniciarComponentes();
		initListeners();
		showSaveMode();
		setColumnsWidth();
		tiposDocumentoMap = mapearTiposDocumento();
		documentosMap = mapearDocumentos();
		new HotKeyComponent(this);
	}
	
	private void setColumnsWidth() {
		TableColumn anchoColumna = getTblNotasCredito().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(25);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblNotasCredito().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(60);
	}
	
	private void iniciarComponentes(){
		getBtnBuscarOperadorNegocio().setToolTipText("Buscar operador de negocio");
		getBtnBuscarOperadorNegocio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCruzarNotasCredito().setText("");
		getBtnCruzarNotasCredito().setToolTipText("Cruzar Notas de Crédito");
		getBtnCruzarNotasCredito().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/affectDocument.png"));
		getBtnConsultarNotasCredito().setText("");
		getBtnConsultarNotasCredito().setToolTipText("Consultar Notas de Crédito");
		getBtnConsultarNotasCredito().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/search.png"));
	}
	
	private void initListeners() {		
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOperadorNegocio();
			}
		});
		
		getBtnCruzarNotasCredito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (validateFields()) {
					int nSeleccionadas = 0;
					saldoCredito = 0D;
					notaCreditoSeleccionadaColeccion = new ArrayList();
					for (int i= 0; i<getTblNotasCredito().getRowCount(); i++) {
						if ((Boolean) getTblNotasCredito().getValueAt(i, 0)) {
							double saldoNotaCredito = Double.parseDouble(Utilitarios.removeDecimalFormat(getTblNotasCredito().getValueAt(i, 6).toString()));
							saldoCredito += saldoNotaCredito;
							notaCreditoSeleccionadaColeccion.add(notaCreditoColeccion.get(getTblNotasCredito().convertRowIndexToModel(i)));
							nSeleccionadas++;
						}
					}
					
					if (nSeleccionadas > 0) {
						reversarSaldosNotasCredito();
						enablePopupNotaCreditoAfecta();
					} else
						SpiritAlert.createAlert("Debe seleccionar la(s) nota(s) de crédito que desea cruzar", SpiritAlert.INFORMATION);
				}
			}
		});
		
		getBtnConsultarNotasCredito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (validateFields())
					cargarTablaNotasCredito();
			}
		});
		
		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCmbDocumento().getSelectedItem() != null)
					documento = (DocumentoIf) getCmbDocumento().getSelectedItem();
			}
		});
		
		getTblNotasCredito().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				seleccionarNotaCredito(evt);
			}
	    });
		
		getCmbTipoCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoCartera = getCmbTipoCartera().getSelectedItem().toString().substring(0,1);
				cargarComboDocumento();
				cleanTable(getTblNotasCredito());
			}
		});
	}
	
	private void seleccionarNotaCredito(ComponentEvent evt) {
		try {
			if (getTblNotasCredito().getSelectedRow() != -1) {
				int row = getTblNotasCredito().getSelectedRow();
				
				if ((Boolean) getTblNotasCredito().getValueAt(row, 0)) {
					NotaCreditoIf notaCredito = notaCreditoColeccion.get(getTblNotasCredito().convertRowIndexToModel(row));
					Map aMap = new HashMap();
					aMap.put("tipodocumentoId", notaCredito.getTipoDocumentoId());
					aMap.put("referenciaId", notaCredito.getId());
					Iterator it = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMap).iterator();
					if (it.hasNext()) {
						double saldoNotaCredito = ((CarteraIf) it.next()).getSaldo().doubleValue();
						CarteraIf carteraNotaCredito = (CarteraIf) carteraNotaCreditoMap.get(notaCredito.getId());
						carteraNotaCredito.setSaldo(BigDecimal.valueOf(saldoNotaCredito));
						valorAplicaMap.put(notaCredito.getId(), 0D);
						getTblNotasCredito().getModel().setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(saldoNotaCredito)), row, 6);
					}
					
					for (int i=0; i<notaCreditoSeleccionadaColeccion.size(); i++) {
						NotaCreditoIf notaCreditoSeleccionada = (NotaCreditoIf) notaCreditoSeleccionadaColeccion.get(i);
						if (notaCreditoSeleccionada.getId().compareTo(notaCredito.getId()) == 0) {
							notaCreditoSeleccionadaColeccion.remove(i);
							break;
						}
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}
	
	private void reversarSaldosNotasCredito() {
		try {
			for (int i=0; i<notaCreditoColeccion.size(); i++) {
				NotaCreditoIf notaCredito = notaCreditoColeccion.get(i);
				Map aMap = new HashMap();
				aMap.put("tipodocumentoId", notaCredito.getTipoDocumentoId());
				aMap.put("referenciaId", notaCredito.getId());
				Iterator it = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMap).iterator();
				if (it.hasNext()) {
					double saldoNotaCredito = ((CarteraIf) it.next()).getSaldo().doubleValue();
					CarteraIf carteraNotaCredito = (CarteraIf) carteraNotaCreditoMap.get(notaCredito.getId());
					carteraNotaCredito.setSaldo(BigDecimal.valueOf(saldoNotaCredito));
					valorAplicaMap.put(notaCredito.getId(), 0D);
					getTblNotasCredito().getModel().setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(saldoNotaCredito)), i, 6);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR); 
		}
	}
	
	private void buscarOperadorNegocio() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			tipoCliente = "CL";
			tituloVentanaBusqueda = "Clientes";
		}
		ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,"", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null) {
			setProveedor();
			cleanTable(getTblNotasCredito());
		}
	}
	
	private void setProveedor() {
		operadorNegocio = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
		try {
			ClienteIf operador = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocio.getClienteId());
			if(operador != null){
				getTxtOperadorNegocio().setText(operador.getIdentificacion() + " - " + operadorNegocio.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
			} else
				SpiritAlert.createAlert("No existe el Proveedor", SpiritAlert.ERROR);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error en consulta de Proveedor", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
	
	private void cargarComboDocumento() {
		try {
			TipoDocumentoIf tipoDocumento = null;
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
			if (tipoCartera.equals(TIPO_CARTERA_PROVEEDOR))
				parameterMap.put("codigo", "NCP");
			else
				parameterMap.put("codigo", "NCC");
			
			
			Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap).iterator();
			
			if (it.hasNext())
				tipoDocumento = (TipoDocumentoIf) it.next();
			
			System.out.println("tipoDocumento-->"+tipoDocumento.getId());
			System.out.println("(UsuarioIf) Parametros.getUsuarioIf())-->"+((UsuarioIf) Parametros.getUsuarioIf()).getUsuario());
			
			if (tipoDocumento != null) {
				List documentos = (List)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumento.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				refreshCombo(getCmbDocumento(), documentos);
				getCmbDocumento().validate();
				getCmbDocumento().repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		System.out.println("getCmbDocumento:"+getCmbDocumento().getSize());
	}
	
	private void cargarTablaNotasCredito() {
		try {
			cleanTable(getTblNotasCredito());
			ArrayList notasCreditoList = new ArrayList();
			DefaultTableModel tableModel = (DefaultTableModel) getTblNotasCredito().getModel();
			notasCreditoList = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().findNotasCredito(operadorNegocio.getId(), documento.getId(), Parametros.getIdEmpresa());
			if (notasCreditoList.size() > 0) {
				Iterator notasCreditoIt = notasCreditoList.iterator();
				
				while (notasCreditoIt.hasNext()) {
					Object[] notasCredito = (Object[]) notasCreditoIt.next();
					NotaCreditoIf notaCredito = (NotaCreditoIf) notasCredito[0];
					CarteraIf carteraNotaCredito = (CarteraIf) notasCredito[1];
					Vector<Object> fila = new Vector<Object>();
					fila.add(new Boolean(false));
					fila.add(notaCredito.getFechaEmision());
					fila.add(notaCredito.getPreimpreso());
					fila.add(notaCredito.getReferencia());
					fila.add(notaCredito.getObservacion());
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carteraNotaCredito.getValor().doubleValue())));
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carteraNotaCredito.getSaldo().doubleValue())));
					tableModel.addRow(fila);
					notaCreditoColeccion.add(notaCredito);
					carteraNotaCreditoMap.put(notaCredito.getId(), carteraNotaCredito);
				}
			} else {
				SpiritAlert.createAlert("No se encontraron notas de crédito.\nVerifique si los parámetros seleccionados son correctos.", SpiritAlert.INFORMATION);
				getBtnBuscarOperadorNegocio().grabFocus();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public void refrescarTablaNotasCredito() {
		this.carteraNotaCreditoMap = (Map) afectaMap.get("CARTERA_NOTA_CREDITO_MAP");
		for (int i=0; i<notaCreditoColeccion.size(); i++) {
			NotaCreditoIf notaCredito = notaCreditoColeccion.get(i);
			CarteraIf carteraNotaCredito = (CarteraIf) this.carteraNotaCreditoMap.get(notaCredito.getId());
			double saldoCarteraNotaCredito = carteraNotaCredito.getSaldo().doubleValue();
			getTblNotasCredito().setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(saldoCarteraNotaCredito)), getTblNotasCredito().convertRowIndexToView(i), 6);
			getTblNotasCredito().setValueAt(false, getTblNotasCredito().convertRowIndexToView(i), 0);
		}
	}
	
	private void cleanTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (int i = table.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		notaCreditoColeccion.clear();
		carteraNotaCreditoMap = new HashMap();
		valorAplicaMap = new HashMap();
		afectaMap = new HashMap();
	}
	
	public void showSaveMode() {
		setSaveMode();
		cargarComboDocumento();
		clean();
	}
	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void save() {
		try {
			CarteraIf carteraAfectada = (CarteraIf) afectaMap.get("CARTERA_AFECTADA");
			
			if (notaCreditoSeleccionadaColeccion.size() > 0 && carteraAfectada != null && valorAplicaMap.size() > 0) {
				
				java.sql.Date fechaAplicacion = (java.sql.Date) afectaMap.get("FECHA_APLICACION");
				List<AsientoIf> asientosList = SessionServiceLocator.getNotaCreditoSessionService().cruzarNotasCredito(notaCreditoSeleccionadaColeccion, carteraNotaCreditoMap, valorAplicaMap, carteraAfectada, fechaAplicacion);
				SpiritAlert.createAlert("Cruce de nota(s) de crédito realizada con éxito!", SpiritAlert.INFORMATION);
				
				//doy la opcion de generar los asiento de la factura
				if(asientosList.size() > 0 && asientosList.get(0) != null){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						for(AsientoIf asientoReporte : asientosList){
							if(asientoReporte != null){
								generarReporteAsiento(asientoReporte);
							}
						}
					}
				}
				
				
				cargarTablaNotasCredito();
			} else {
				SpiritAlert.createAlert("No existen datos que guardar.\nCruce la(s) nota(s) de crédito y vuelva intentar", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	private void generarReporteAsiento(AsientoIf asientoReporte) {
		try {
			ArrayList<AsientoDetalleIf> asientoDetalleColeccion = (ArrayList<AsientoDetalleIf>)SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoReporte.getId());
			
			Map cuentasMap = SessionServiceLocator.getCuentaSessionService().mapearCuentas(Parametros.getIdEmpresa());
			Map usuariosMap = SessionServiceLocator.getUsuarioSessionService().mapearUsuarios(Parametros.getIdEmpresa());
			Map empleadosMap = SessionServiceLocator.getEmpleadoSessionService().mapearEmpleados(Parametros.getIdEmpresa());
			double totalDebe = 0D;
			double totalHaber = 0D;
			List<AutorizarAsientoData> autorizarAsientoDataColeccion = new ArrayList<AutorizarAsientoData>();
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				AutorizarAsientoData data = new AutorizarAsientoData();
				CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId());
				data.setCuenta(cuenta.getCodigo());
				data.setFechaMovimiento(asientoReporte.getFecha().toString());
				data.setGlosa((asientoDetalle.getGlosa().length()>70?asientoDetalle.getGlosa().substring(0,70):asientoDetalle.getGlosa()));
				data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
				data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
				totalDebe += asientoDetalle.getDebe().doubleValue();
				data.setTotalDebe(formatoDecimal.format(totalDebe));
				totalHaber += asientoDetalle.getHaber().doubleValue();
				data.setTotalHaber(formatoDecimal.format(totalHaber));
				data.setMes(Utilitarios.getNombreMes(asientoReporte.getFecha().getMonth() + 1).substring(0,3));
				data.setNombreCuenta((cuenta.getNombre().length()>70?cuenta.getNombre().substring(0,70):cuenta.getNombre()));
				data.setNumero(asientoReporte.getNumero());
				if (asientoReporte.getElaboradoPorId() != null) {
					UsuarioIf elaboradoPor = (UsuarioIf) usuariosMap.get(asientoReporte.getElaboradoPorId());
					EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(elaboradoPor.getEmpleadoId());
					data.setElaboradoPor(empleado.getNombres() + " " + empleado.getApellidos());
				}
				
				if (asientoReporte.getAutorizadoPorId() != null) {
					UsuarioIf autorizadoPor = ((UsuarioIf) Parametros.getUsuarioIf());
					EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(autorizadoPor.getEmpleadoId());
					data.setAutorizadoPor(empleado.getNombres() + " " + empleado.getApellidos());
				}
				data.setTipoDocumentoId((asientoReporte.getTipoDocumentoId()!=null)?asientoReporte.getTipoDocumentoId():null);
				data.setTransaccionId((asientoReporte.getTransaccionId()!=null)?asientoReporte.getTransaccionId():null);
				autorizarAsientoDataColeccion.add(data);
			}
			
			String fileName = "jaspers/contabilidad/RPAutorizacionAsiento.jasper";
			int seccionesHoja = 1;
			Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
			Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
			if (asientoReporte.getTipoDocumentoId() != null) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(asientoReporte.getTipoDocumentoId());
				if (tipoDocumento.getTipoTroqueladoId() != null) {
					TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf) tiposTroqueladoMap.get(tipoDocumento.getTipoTroqueladoId());
					if (tipoTroquelado.getNumeroSeccionesHoja() > seccionesHoja)
						seccionesHoja = tipoTroquelado.getNumeroSeccionesHoja();
				}
			}
			
			if (seccionesHoja == 2)
				fileName = "jaspers/contabilidad/RPAutorizacionAsientoDoble.jasper";
			else if (seccionesHoja == 4)
				fileName = "jaspers/contabilidad/RPAutorizacionAsientoCuadruple.jasper";
			
			HashMap parametrosMap = new HashMap();
			MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator().next();
			
			parametrosMap.put("codigoReporte", menu.getCodigo());
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			parametrosMap.put("empresa", empresa.getNombre());
			parametrosMap.put("ruc", empresa.getRuc());
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
			parametrosMap.put("ciudad", ciudad.getNombre());
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
			parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
			parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
			ReportModelImpl.processReport(fileName, parametrosMap, autorizarAsientoDataColeccion, true);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void authorize() {
		// TODO Auto-generated method stub
	}
	
	private void enablePopupNotaCreditoAfecta() {
		Map parameterMap = new HashMap();
		parameterMap.put("NOTA_CREDITO_SELECCIONADA_LIST", notaCreditoSeleccionadaColeccion);
		parameterMap.put("CARTERA_NOTA_CREDITO_MAP", carteraNotaCreditoMap);
		parameterMap.put("SALDO_CREDITO", saldoCredito);
		parameterMap.put("DOCUMENTO", documento);
		parameterMap.put("OPERADOR_NEGOCIO", operadorNegocio);
		parameterMap.put("CARTERA_AFECTADA", null);
		parameterMap.put("VALOR_APLICA_MAP", valorAplicaMap);
		parameterMap.put("FECHA_APLICA", null);
		
		jdNotaCreditoAfecta = new NotaCreditoAfectaModel(Parametros.getMainFrame(), this, parameterMap);
		jdNotaCreditoAfecta.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				jdNotaCreditoAfecta = null;
				System.gc();
			}		
		});
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		jdNotaCreditoAfecta.setLocation(x, y);
		jdNotaCreditoAfecta.pack();
		jdNotaCreditoAfecta.setModal(true);
		jdNotaCreditoAfecta.setVisible(true);
	}	
	
	private Map mapearTiposDocumento() {
		Map tiposDocumentoMap = new HashMap();
		
		try {
			Iterator tiposDocumentoIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentoIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIterator.next();
				tiposDocumentoMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentoMap;
	}
	
	private Map mapearDocumentos() {
		Map documentosMap = new HashMap();
		
		try {
			Iterator documentosIterator = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (documentosIterator.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIterator.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void clean() {
		getTxtOperadorNegocio().setText("");
		operadorNegocio = null;
		getCmbDocumento().setSelectedItem(null);
		documento = null;

		cleanTable(getTblNotasCredito());
		
		System.out.println("getCmbDocumento CLEAN:"+getCmbDocumento().getSize());
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean validateFields() {
		if (operadorNegocio == null) {
			String tipoOperador = "proveedor";
			if (tipoCartera.equals(TIPO_CARTERA_CLIENTE))
				tipoOperador = "cliente";
			SpiritAlert.createAlert("Debe seleccionar el " + tipoOperador, SpiritAlert.INFORMATION);
			getBtnBuscarOperadorNegocio().grabFocus();
			return false;
		}
		
		if (documento == null) {
			SpiritAlert.createAlert("Debe seleccionar el documento", SpiritAlert.INFORMATION);
			getCmbDocumento().grabFocus();
			return false;
		}
			
		return true;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void refresh() {
		cargarComboDocumento();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public Map getAfectaMap() {
		return afectaMap;
	}

	public void setAfectaMap(Map afectaMap) {
		this.afectaMap = afectaMap;
	}
}
