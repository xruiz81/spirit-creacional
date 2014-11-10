package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.panel.JPConsultaCarteraDiferida;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.model.MonedaModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.FinderTable;
import com.spirit.util.Utilitarios;

public class ConsultaCarteraDiferidaModel extends JPConsultaCarteraDiferida {

	private static final long serialVersionUID = -6002201439191355802L;
	protected int mode;
	public boolean isFinderTableVisible = false;
	protected FinderTable finderTable;
	private static Date fechaInicioCalculo = new java.util.Date();;
	private static Date fechaFinCalculo = new java.util.Date();;
	private DefaultTableModel modelCarteraDetalle;
	private Vector carteraDetalleColeccion = new Vector();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final String CARTERA_SI = "S";
	private static final String CARTERA_NO = "N";
	private static final String SIGNO_NEGATIVO = "-";

	public ConsultaCarteraDiferidaModel() {
		showSaveMode();
		cargarListenersComponents();
		setSorterTable(getTblDetalle());
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		new HotKeyComponent(this);
	}

	private void cargarListenersComponents() {

		getJbtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				// Veo si todos los campos necesarios para ejercer los calculos
				// han sido seteados
				try {
					carteraDetalleColeccion = new Vector();
					// Limpio la tabla cada vez que mando hacer una busqueda
					for (int i = getTblDetalle().getRowCount(); i > 0; --i)
						modelCarteraDetalle.removeRow(i - 1);

					// Variables temporales que almacena los id de los objetso
					// que deboc onsultar en la base
					Long idCartera = 0L;
					Long idDocumento = 0L;
					// Objetos que se debe insertar en la base
					CarteraIf cartera = null;
					TipoDocumentoIf tipoDocumento = null;
					DocumentoIf documento = null;
					ClienteIf cliente = null;
					MonedaIf moneda = null;
					String string = "".replace(",", "");

					// Obtengo la fecha de Inicio de Calculo de los asientos
					// detalles
					java.sql.Date fechaInicioConsulta = new java.sql.Date(
							fechaInicioCalculo.getYear(), fechaInicioCalculo
									.getMonth(), fechaInicioCalculo.getDate());
					java.sql.Date fechaFinConsulta = new java.sql.Date(
							fechaFinCalculo.getYear(), fechaFinCalculo
									.getMonth(), fechaFinCalculo.getDate());

					// Obtengo todos los cartera detalles que sean NO CARTERAS y
					// que pertenezcan al rango de fechas
					Collection carteraDetalles = SessionServiceLocator.getCarteraDetalleSessionService()
							.findCarteraDetalleByCarteraAndBySignoCarteraAndByFechaInicioAndByFechaFin(
									CARTERA_NO, SIGNO_NEGATIVO,
									fechaInicioConsulta, fechaFinConsulta,
									Parametros.getIdEmpresa());
					Iterator itCarteraDetalles = carteraDetalles.iterator();

					// Reccoror todas las carteras detalles encontrados en el
					// rango de fechas
					while (itCarteraDetalles.hasNext()) {
						CarteraDetalleIf carteraDetalleNegativa = (CarteraDetalleIf) itCarteraDetalles
								.next();

						// Veo si el id del cartera ha cambiado
						if (!carteraDetalleNegativa.getCarteraId().equals(
								idCartera)) {
							idCartera = carteraDetalleNegativa.getCarteraId();
							cartera = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(
											idCartera);
							moneda = (MonedaIf) MonedaModel
									.getMonedaSessionService().getMoneda(
											cartera.getMonedaId());
							ClienteOficinaIf clienteOficina = (ClienteOficinaIf) SessionServiceLocator.getClienteOficinaSessionService()
									.getClienteOficina(
											cartera.getClienteoficinaId());
							cliente = (ClienteIf) SessionServiceLocator.getClienteSessionService()
									.getCliente(clienteOficina.getClienteId());
						}

						// Veo si el id del documento ha cambiado
						if (!carteraDetalleNegativa.getDocumentoId().equals(
								idDocumento)) {
							idDocumento = carteraDetalleNegativa
									.getDocumentoId();
							documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(
											idDocumento);
							tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService()
									.getTipoDocumento(
											documento.getTipoDocumentoId());
						}

						// Obtengo el modelo de la tabla donde van a ir
						// insertados los cartera detalles
						modelCarteraDetalle = (DefaultTableModel) getTblDetalle()
								.getModel();
						Vector<String> filaCarteraDetalle = new Vector<String>();

						// DateFormat dateFormatter;
						// dateFormatter =
						// DateFormat.getDateInstance(DateFormat.DEFAULT);
						// String fechaCartera =
						// dateFormatter.format(carteraDetalleNegativa.getFechaCartera().getTime());
						String fechaCartera = Utilitarios
								.getFechaCortaUppercase(carteraDetalleNegativa
										.getFechaCartera());

						// Agregar a la coleccion de TipoOrdenColeccion para
						// grabar al final toda la coleccion
						carteraDetalleColeccion.add(carteraDetalleNegativa);

						// Agregro los valores leidos de la base del asiento
						// detalle en la fila.
						filaCarteraDetalle.add(fechaCartera);
						filaCarteraDetalle.add(cartera.getCodigo());
						filaCarteraDetalle.add(tipoDocumento.getNombre());
						filaCarteraDetalle.add(documento.getNombre());
						filaCarteraDetalle.add(cliente.getNombreLegal());
						filaCarteraDetalle.add(moneda.getNombre());
						filaCarteraDetalle.add(formatoDecimal
								.format(carteraDetalleNegativa.getValor()));
						filaCarteraDetalle.add(formatoDecimal
								.format(carteraDetalleNegativa.getSaldo()));

						// Agrego la fila con datos a la tabla.
						modelCarteraDetalle.addRow(filaCarteraDetalle);
					}

					if (carteraDetalles.size() == 0)
						SpiritAlert.createAlert(
								"No hay información para mostrar!",
								SpiritAlert.INFORMATION);
					else {
						setUpdateMode();
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});

		getJbtnActualizarEstado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				// Veo si todos los campos necesarios para ejercer los calculos
				// han sido seteados
				try {
					// Actualizo en la base los detalles de cartera
					for (int i = 0; i < carteraDetalleColeccion.size(); i++) {
						CarteraDetalleIf carteraDetalleNegativa = (CarteraDetalleIf) carteraDetalleColeccion
								.get(i);

						// Obtengo todos los carteras Afecta quehacen referencia
						// a este detalle
						Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService()
								.findCarteraAfectaByCarteradetalleId(
										carteraDetalleNegativa.getId());
						Iterator itCarterasAfecta = carterasAfecta.iterator();

						// Reccoror todas las carteras afectas encontrados
						while (itCarterasAfecta.hasNext()) {
							CarteraAfectaIf carteraAfectaTemp = (CarteraAfectaIf) itCarterasAfecta
									.next();

							// Extraigo la cartera detalle Positiva a la que
							// hace referencia este caretera Afecta y le sumo el
							// valor que tiene asignado
							CarteraDetalleIf carteraDetallePositiva = (CarteraDetalleIf) SessionServiceLocator.getCarteraDetalleSessionService()
									.getCarteraDetalle(
											carteraAfectaTemp
													.getCarteraafectaId());
							// Actualizo su saldo y su cartera
							carteraDetallePositiva
									.setSaldo(carteraDetallePositiva.getSaldo()
											.add(carteraAfectaTemp.getValor()));
							carteraDetallePositiva.setCartera(CARTERA_SI);
							// Actualizo el objeto carteraDetallePositiva en la
							// base
							SessionServiceLocator.getCarteraDetalleSessionService()
									.saveCarteraDetalle(carteraDetallePositiva);

							// Extraigo el cartera del carterafecta
							carteraAfectaTemp.setCartera(CARTERA_SI);
							// Actualizo el objeto carteraafecta en la base
							SessionServiceLocator.getCarteraAfectaSessionService()
									.saveCarteraAfecta(carteraAfectaTemp);
						}

						// Actualizo la cartera del detalle cartera Negativa
						carteraDetalleNegativa.setCartera(CARTERA_SI);
						// Actualizo el objeto carteraDetalleNegativa en la base
						SessionServiceLocator.getCarteraDetalleSessionService()
								.saveCarteraDetalle(carteraDetalleNegativa);

					}

					clean();

					if (carteraDetalleColeccion.size() > 0)
						SpiritAlert
								.createAlert(
										"Las carteras fueron actualizadas exitosamente!",
										SpiritAlert.INFORMATION);
					else
						SpiritAlert.createAlert("No hay nada que actualizar!",
								SpiritAlert.WARNING);

				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});

		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioCalculo = (Date) ((DateComboBox) evento.getSource())
						.getDate();
				clean();
				if (fechaInicioCalculo.after(fechaFinCalculo)) {
					SpiritAlert
							.createAlert(
									"La fecha de inicio no puede ser despues de la fecha de fin!",
									SpiritAlert.INFORMATION);
					java.util.Date fechaHoy = new java.util.Date();
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaHoy);
					fechaInicioCalculo = fechaHoy;
					getCmbFechaInicio().setCalendar(calendarInicio);
				}
			}
		});

		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaFinCalculo = (Date) ((DateComboBox) evento.getSource())
						.getDate();
				clean();
				if (fechaFinCalculo.before(fechaInicioCalculo)) {
					SpiritAlert
							.createAlert(
									"La fecha final no puede ser antes de la fecha de inicio!",
									SpiritAlert.INFORMATION);
					java.util.Date fechaHoy = new java.util.Date();
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaHoy);
					fechaFinCalculo = fechaHoy;
					getCmbFechaFin().setCalendar(calendarFin);
				}
			}
		});
	}

	public void clean() {
		modelCarteraDetalle = (DefaultTableModel) this.getTblDetalle()
				.getModel();

		for (int i = this.getTblDetalle().getRowCount(); i > 0; --i)
			modelCarteraDetalle.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();

		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());

		Calendar calendarFechaInicio = new GregorianCalendar();
		calendarFechaInicio.setTime(fechaInicioCalculo);
		getCmbFechaInicio().setCalendar(calendarFechaInicio);

		Calendar calendarFechaFin = new GregorianCalendar();
		calendarFechaFin.setTime(fechaFinCalculo);
		getCmbFechaFin().setCalendar(calendarFechaFin);
		clean();
	}

	public void report() {
    	if (getMode() == SpiritMode.UPDATE_MODE) {
    		try {
    			if (modelCarteraDetalle.getRowCount() > 0) {
    				int opcion = JOptionPane
    				.showConfirmDialog(
    						null,
    						"¿Desea generar el reporte para imprimir la Cartera Diferida?",
    						"Confirmación", JOptionPane.YES_NO_OPTION);
    				
    				if (opcion == JOptionPane.YES_OPTION) {
    					String fileName = "jaspers/cartera/RPCarteraDiferida.jasper";
    					HashMap parametrosMap = new HashMap();
    					JRDataSource dataSource = new JRTableModelDataSource(modelCarteraDetalle);
    					
    					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
    						parametrosMap.put("pathsubreport",Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPCarteraDiferidaDetalle.jasper");
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
						
    					//parametrosMap.put("pathsubreport",Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPCarteraDiferidaDetalle.jasper");
    					    					
    					parametrosMap.put("dataSourceDetail", dataSource);
    					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONSULTA CARTERA DIFERIDA").iterator().next();
    					MenuIf menu = null;
    					Iterator itmenu= SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONSULTA CARTERA DIFERIDA").iterator();
    					if(itmenu.hasNext())  menu = (MenuIf) itmenu.next();
    					
    					
    					
    					
    					parametrosMap.put("codigoReporte", menu.getCodigo());
    					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
    					parametrosMap.put("empresa", empresa.getNombre());
    					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
    					parametrosMap.put("usuario", Parametros.getUsuario());
    					parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
    					parametrosMap.put("fechaInicial", new java.sql.Date(getCmbFechaInicio().getDate().getTime()).toString());
    					parametrosMap.put("fechaFinal", new java.sql.Date(getCmbFechaFin().getDate().getTime()).toString());
    					ReportModelImpl.processReport(fileName, parametrosMap,new JREmptyDataSource(), true);
    				}
    			} else {
    				SpiritAlert.createAlert("No existen datos para imprimir",SpiritAlert.WARNING);
    			}
    		} catch (GenericBusinessException e) {
    			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
    			e.printStackTrace();
    		} catch (ParseException e) {
    			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
    			e.printStackTrace();
    		}
    	}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
}
