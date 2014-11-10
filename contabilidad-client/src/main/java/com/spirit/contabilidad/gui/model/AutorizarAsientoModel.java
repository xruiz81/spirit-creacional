package com.spirit.contabilidad.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.contabilidad.gui.panel.JPAutorizarAsiento;
import com.spirit.exception.CuentaNoImputableException;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class AutorizarAsientoModel extends JPAutorizarAsiento {
	
	private static final long serialVersionUID = -7378873443073043323L;
	
	private int selectedRow = -1;
	private List<AsientoIf> asientoColeccion = new ArrayList<AsientoIf>();
	private List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
	private AsientoIf asientoForAuthorize;
	private static Map panels;
	private Map cuentasMap = new HashMap();
	private Map tiposCuentaMap = new HashMap();
	private Map periodosMap = new HashMap();
	private Map asientoDetallesMap = new HashMap();
	private Map periodosAutorizarMap = new HashMap();
	private Map usuariosMap = new HashMap();
	private Map empleadosMap = new HashMap();
	private Map oficinasMap = new HashMap();
	private Map saldosCuentasMap = new HashMap();
	private Map periodosDetallesInactivosByPeriodoIdMap = new HashMap();
	private List<AutorizarAsientoData> autorizarAsientoDataColeccion = new ArrayList<AutorizarAsientoData>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private BigDecimal totalDebe = new BigDecimal(0);
	private BigDecimal totalHaber = new BigDecimal(0);

	
	public AutorizarAsientoModel() {
		panels = MainFrameModel.get_panels();
		setSorterTable(getTblPreasientos());
		getTblPreasientos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblAutorizarAsiento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initListeners();
		showSaveMode();
		setColumnsWidth();
		new HotKeyComponent(this);
	}
	
	private void setColumnsWidth() {
		// tblPreasientos
		TableColumn anchoColumna = getTblPreasientos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblPreasientos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblPreasientos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPreasientos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblPreasientos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblPreasientos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(400);
		
		// tblAutorizarAsiento
		anchoColumna = getTblAutorizarAsiento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblAutorizarAsiento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblAutorizarAsiento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblAutorizarAsiento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblAutorizarAsiento().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(75);
	}
	
	private void initListeners() {
		menuItem = new JMenuItem("<html><font color=red>Editar asiento contable</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				editarAsientoContable(asientoForAuthorize);
			}
		});
		popup.add(menuItem);

		getTblPreasientos().add(popup);
		getTblPreasientos().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		getTblPreasientos().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
		
		getTblPreasientos().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
	}
	
	private void enableSelectedRowForAuthorize(ComponentEvent evt) {
		if (getTblPreasientos().getSelectedRow() != -1) {
			int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
			selectRow(selectedRow);
		}
	}

	private void selectRow(int selectedRow) {
		asientoDetalleColeccion.clear();
		setSelectedRow(selectedRow);
		asientoForAuthorize = (AsientoIf) asientoColeccion.get(getTblPreasientos().convertRowIndexToModel(getSelectedRow()));
		cleanTable(getTblAutorizarAsiento());
		try {
			enableAsientoForAuthorize(asientoForAuthorize);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void cargarTablaPreasientos() {
		try {
			//Map periodosMap = mapearPeriodos(Parametros.getIdEmpresa());
			asientoDetallesMap = new HashMap();
			Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
			Map modulosMap = SessionServiceLocator.getModuloSessionService().mapearModulos();
			periodosAutorizarMap = new HashMap();
			DefaultTableModel tableModel = (DefaultTableModel) getTblPreasientos().getModel();
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("status", "P");
			asientoColeccion = (List<AsientoIf>) SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap);
			Iterator preasientos = asientoColeccion.iterator();
			while (preasientos.hasNext()) {
				AsientoIf preasiento = (AsientoIf) preasientos.next();
				Vector<Object> fila = new Vector<Object>();
				/*java.sql.Date fecha = preasiento.getFecha();
				int month = fecha.getMonth() + 1;
				int year = fecha.getYear() + 1900;
				if (month == 12 && year == 2011)
					fila.add(new Boolean(true));
				else
					fila.add(new Boolean(false));*/
				fila.add(new Boolean(false));
				fila.add(preasiento.getNumero());
				OficinaIf oficina = (OficinaIf) oficinasMap.get(preasiento.getOficinaId());
				fila.add(oficina.getNombre());
				//PeriodoIf periodo = PeriodoModel.getPeriodoSessionService().getPeriodo(preasiento.getPeriodoId());
				if (preasiento.getTipoDocumentoId() != null) {
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(preasiento.getTipoDocumentoId());
					ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
					fila.add(modulo.getNombre() + "/" + tipoDocumento.getCodigo());
				} else
					fila.add("");
				fila.add(preasiento.getFecha().toString());
				fila.add(preasiento.getObservacion());
				tableModel.addRow(fila);
				periodosAutorizarMap.put(preasiento.getPeriodoId(), (PeriodoIf) periodosMap.get(preasiento.getPeriodoId()));
				/*List lista = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoIdOrderByDebeHaberAndCodigo(preasiento.getId());
				Iterator detallesLista = lista.iterator();
				Vector<AsientoDetalleIf> asientoDetallesVector = new Vector<AsientoDetalleIf>();
				while (detallesLista.hasNext()) {
					Object[] objetos = (Object[]) detallesLista.next();
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) objetos[0];
					asientoDetallesVector.add(asientoDetalle);
				}
				asientoDetallesMap.put(preasiento.getId(), asientoDetallesVector);*/
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void enableAsientoForAuthorize(AsientoIf asiento) throws GenericBusinessException {
		totalDebe = new BigDecimal(0);
		totalHaber = new BigDecimal(0);
		DefaultTableModel tableModel = (DefaultTableModel) getTblAutorizarAsiento().getModel();
		Iterator it = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asiento.getId()).iterator();

		while (it.hasNext()) {
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) it.next();
			CuentaIf cuentaIf = (CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId());
			Vector<String> fila = new Vector<String>();
			fila.add(cuentaIf.getCodigo());
			fila.add(cuentaIf.getNombre());
			fila.add(asientoDetalle.getGlosa());
			if (!asientoDetalle.getDebe().toString().equals("0"))
				fila.add(formatoDecimal.format(asientoDetalle.getDebe().doubleValue()));
			else
				fila.add("");
			if (!asientoDetalle.getHaber().toString().equals("0"))
				fila.add(formatoDecimal.format(asientoDetalle.getHaber().doubleValue()));
			else
				fila.add("");

			tableModel.addRow(fila);
			asientoDetalleColeccion.add(asientoDetalle);
			setUpdateMode();
			
			totalDebe = totalDebe.add(asientoDetalle.getDebe());
			totalHaber = totalHaber.add(asientoDetalle.getHaber());
		}
	}
	
	private void editarAsientoContable(AsientoIf asiento) {
		SpiritModel panelAsiento = (SpiritModel) new AsientoModel(asiento);
		
		if (panels.size()>0 && panels.get("Asiento")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Asiento");
			}
		}
		
		PanelHandler.showPanelModel(panelAsiento);
	}
	
	private void cleanTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = table.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);	
	}
	
	public void showSaveMode() {
		setSaveMode();
		try {
			oficinasMap = SessionServiceLocator.getOficinaSessionService().mapearOficinas(Parametros.getIdEmpresa());
			periodosMap = SessionServiceLocator.getPeriodoSessionService().mapearPeriodos(Parametros.getIdEmpresa());
			cleanTable(getTblPreasientos());
			cleanTable(getTblAutorizarAsiento());
			cargarTablaPreasientos();
			cuentasMap = SessionServiceLocator.getCuentaSessionService().mapearCuentas(Parametros.getIdEmpresa());
			tiposCuentaMap = SessionServiceLocator.getTipoCuentaSessionService().mapearTiposCuenta();
			usuariosMap = SessionServiceLocator.getUsuarioSessionService().mapearUsuarios(Parametros.getIdEmpresa());
			empleadosMap = SessionServiceLocator.getEmpleadoSessionService().mapearEmpleados(Parametros.getIdEmpresa());
			saldosCuentasMap = SessionServiceLocator.getSaldoCuentaSessionService().mapearSaldosCuentasByPeriodosMap(periodosAutorizarMap);
			periodosDetallesInactivosByPeriodoIdMap = SessionServiceLocator.getPeriodoDetalleSessionService().mapearPeriodosDetallesNoInactivosByPeriodosMap(periodosAutorizarMap);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
			
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		try {
			SessionServiceLocator.getAsientoSessionService().eliminarAsiento(asientoForAuthorize.getId());
			SpiritAlert.createAlert("Asiento eliminado con éxito", SpiritAlert.INFORMATION);				
			this.clean();
			this.showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al eliminar el asiento", SpiritAlert.WARNING);
		}
	}
	
	public void authorize() {
		autorizarAsientoDataColeccion.clear();
		int numeroAsientos = getTblPreasientos().getRowCount();
		int asientosAutorizados = 0;
		Map asientosMap = new HashMap();
		
		for (int i=0; i<numeroAsientos; i++) {
			boolean seleccionado = (Boolean) getTblPreasientos().getValueAt(i,0);
			
			if (seleccionado)
				asientosMap.put(i,i);
		}
		
		Iterator asientosMapIt = asientosMap.keySet().iterator();
		
		while (asientosMapIt.hasNext()) {
			int indice = (Integer) asientosMapIt.next();
			getTblPreasientos().setRowSelectionInterval(indice,indice);
			selectRow(indice);

			if (autorizarAsiento())
				asientosAutorizados++;
		}
		
		if (asientosAutorizados > 0){
			SpiritAlert.createAlert("Autorización realizada con éxito", SpiritAlert.INFORMATION);
			generarReportes();
		}
		else
			SpiritAlert.createAlert("No se autorizó ningún asiento", SpiritAlert.INFORMATION);
		
		this.showSaveMode();
	}
	
	public void generarReportes(){
		try {
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de los Asientos Autorizados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION) {
				String fileName = "jaspers/contabilidad/RPAutorizacionAsiento.jasper";
				int seccionesHoja = 1;
				Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
				Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
				for (int i=0; i<autorizarAsientoDataColeccion.size(); i++) {
					AutorizarAsientoData asiento = autorizarAsientoDataColeccion.get(i);
					if (asiento.getTipoDocumentoId() != null) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(asiento.getTipoDocumentoId());
						if (tipoDocumento.getTipoTroqueladoId() != null) {
							TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf) tiposTroqueladoMap.get(tipoDocumento.getTipoTroqueladoId());
							if (tipoTroquelado.getNumeroSeccionesHoja() > seccionesHoja)
								seccionesHoja = tipoTroquelado.getNumeroSeccionesHoja();
						}
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
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException ep) {
			ep.printStackTrace();
		}
	}

	private boolean autorizarAsiento() {
		boolean asientoAutorizado = false;
		
		if (validateFields()) {
			try {
				// Cuando se actualiza un asiento previamente guardado con estado ASIENTO,
				// antes de afectar los saldos nuevamente se deben reversar los saldos afectados anteriormente por este asiento
				boolean reversarSaldos = false;
				AsientoIf asiento = asientoForAuthorize;
				asiento.setStatus("A");
				asiento.setAutorizadoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
				Map resultMap = SessionServiceLocator.getAsientoSessionService().actualizarAsiento(asiento, asientoDetalleColeccion, null, null, null, reversarSaldos, false, (UsuarioIf) Parametros.getUsuarioIf(), cuentasMap, tiposCuentaMap, saldosCuentasMap, periodosDetallesInactivosByPeriodoIdMap, false);
				saldosCuentasMap = (Map) resultMap.get("SALDOS");
				asientoAutorizado = true;
				double totalDebe = 0D;
				double totalHaber = 0D;
				for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
					AutorizarAsientoData data = new AutorizarAsientoData();
					CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId());
					data.setCuenta(cuenta.getCodigo());
					data.setFechaMovimiento(asiento.getFecha().toString());
					data.setGlosa((asientoDetalle.getGlosa().length()>70?asientoDetalle.getGlosa().substring(0,70):asientoDetalle.getGlosa()));
					data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
					data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
					totalDebe += asientoDetalle.getDebe().doubleValue();
					data.setTotalDebe(formatoDecimal.format(totalDebe));
					totalHaber += asientoDetalle.getHaber().doubleValue();
					data.setTotalHaber(formatoDecimal.format(totalHaber));
					data.setMes(Utilitarios.getNombreMes(asiento.getFecha().getMonth() + 1).substring(0,3));
					data.setNombreCuenta((cuenta.getNombre().length()>70?cuenta.getNombre().substring(0,70):cuenta.getNombre()));
					data.setNumero(asiento.getNumero());
					if (asiento.getElaboradoPorId() != null) {
						UsuarioIf elaboradoPor = (UsuarioIf) usuariosMap.get(asiento.getElaboradoPorId());
						EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(elaboradoPor.getEmpleadoId());
						data.setElaboradoPor(empleado.getNombres() + " " + empleado.getApellidos());
					}
					
					if (asiento.getAutorizadoPorId() != null) {
						UsuarioIf autorizadoPor = ((UsuarioIf) Parametros.getUsuarioIf());
						EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(autorizadoPor.getEmpleadoId());
						data.setAutorizadoPor(empleado.getNombres() + " " + empleado.getApellidos());
					}
					data.setTipoDocumentoId((asiento.getTipoDocumentoId()!=null)?asiento.getTipoDocumentoId():null);
					data.setTransaccionId((asiento.getTransaccionId()!=null)?asiento.getTransaccionId():null);
					autorizarAsientoDataColeccion.add(data);
				}
			} catch (SaldoCuentaNegativoException ex) {
				ex.printStackTrace();
				SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
			} catch (CuentaNoImputableException excep) {
				excep.printStackTrace();
				SpiritAlert.createAlert(excep.getMessage(), SpiritAlert.INFORMATION);
			}catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Ocurri\u00f3 un error al autorizar el asiento", SpiritAlert.ERROR);
			}catch (Exception ejbex) {
				ejbex.printStackTrace();
				if (ejbex.getMessage().contains("java.lang.IllegalStateException"))
					SpiritAlert.createAlert(ejbex.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurri\u00f3 un error al autorizar el asiento", SpiritAlert.ERROR);
			} 
		}
		
		return asientoAutorizado;
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {
		autorizarAsientoDataColeccion.clear();
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		Date fechaAsiento = asientoForAuthorize.getFecha();
		int monthAsiento = fechaAsiento.getMonth() + 1;
		String monthAsientoString = String.valueOf(monthAsiento);
		if (monthAsiento <= 9)
			monthAsientoString = "0" + monthAsientoString;
		int yearAsiento = fechaAsiento.getYear() + 1900;
		String yearAsientoString = String.valueOf(yearAsiento);
		Map parameterMap = new HashMap();
		parameterMap.put("periodoId", asientoForAuthorize.getPeriodoId());
		parameterMap.put("mes", monthAsientoString);
		parameterMap.put("anio", yearAsientoString);
		try {
			Iterator periodoDetalleAsientoIterator = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleByQueryAndEstadoActivoOrParcial(parameterMap).iterator();
			if (!periodoDetalleAsientoIterator.hasNext()) {
				SpiritAlert.createAlert("El Período-Mes del asiento está Cerrado o Inactivo.\n" +
						                 "Verifique que el período y fecha seleccionados correspondan\n" +
						                 "a un Período-Mes Activo o Parcial!", SpiritAlert.WARNING);
				return false;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		if(totalDebe.compareTo(totalHaber) != 0){
			SpiritAlert.createAlert("Debe y Haber del asiento: '" + asientoForAuthorize.getNumero() + "' no cuadran y debe ser ajustado", SpiritAlert.WARNING);
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
		showSaveMode();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	private int getSelectedRow() {
		return this.selectedRow;
	}
	
	private void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
}
