package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.panel.JPPagosDiferidos;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.Utilitarios;

public class PagosDiferidosModel extends JPPagosDiferidos {
	private static final long serialVersionUID = 6332880290172363139L;
	private static final String APROBADO_SI = "S";
	private static final String CARTERA_NO = "N";
	private static final String CARTERA_SI = "S";
	private Vector<CarteraIf> carteraColeccion = new Vector<CarteraIf>();
	private Vector<CarteraDetalleIf> carteraPagoDetalleColeccion = new Vector<CarteraDetalleIf>();
	private DefaultTableModel tableModel;
	private ClienteOficinaIf clienteOficina;
	private ClienteIf cliente;
	private CompraIf compra;
	private CarteraIf cartera;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<Boolean> actualizarColeccion = new Vector<Boolean>();
	private List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
	protected JDPopupFinderModel popupFinder;
	protected ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteOficinaIf proveedorIf;
	private Map clientesMap;
	private Map clienteOficinasMap;
	private Map comprasMap;
	private Vector<CarteraIf> carterasPagadasDiferidasColeccion = new Vector<CarteraIf>();
	private Vector<CarteraIf> carterasComprasPagadasDiferidasColeccion = new Vector<CarteraIf>();

	public PagosDiferidosModel(){
		initMaps();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		getTblPagosDiferidos().getTableHeader().setReorderingAllowed(false);
		getTblPagosDiferidos().setCellSelectionEnabled(true);
		getTblPagosDiferidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblPagosDiferidos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblPagosDiferidos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblPagosDiferidos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		
		TableColumn anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);	
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblPagosDiferidos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(100);
	}
	
	public void initListeners(){
		getBtnSeleccionarTodos().addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				if (getTblPagosDiferidos().getRowCount() > 0) {
					if ((Boolean)getTblPagosDiferidos().getValueAt(0,0) == false) {
						for(int i=0; i<getTblPagosDiferidos().getRowCount(); i++){					
							getTblPagosDiferidos().setValueAt(true, i, 0);
						}
					} else {
						for(int i=0; i<getTblPagosDiferidos().getRowCount(); i++){					
							getTblPagosDiferidos().setValueAt(false, i, 0);
						}
					}									
				}else{
					SpiritAlert.createAlert("No existe ninguna fila en la tabla !",SpiritAlert.INFORMATION);
				}
			}			
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbProveedor().setSelected(false);
			}
		});
		getCbProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbTodos().setSelected(false);
			}
		});
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProveedor();
			}
		});		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(!getCbProveedor().isSelected()){
					clean();
					proveedorIf = null;
					getTxtProveedor().setText("");
					cargarTabla();
				}else if(proveedorIf != null){
					clean();
					cargarTabla();
				}else{
					SpiritAlert.createAlert("Debe buscar un proveedor!", SpiritAlert.INFORMATION);
				}
			}
		});
	}
	
	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
				
		clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if ( popupFinder.getElementoSeleccionado() != null )
			setProveedor(popupFinder.getElementoSeleccionado());
	}
	
	private void setProveedor(Object proveedorObjeto) {
		clean();
		proveedorIf = (ClienteOficinaIf) proveedorObjeto;
		
		try {
			ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(proveedorIf.getId());
			ClienteIf proveedor= (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal() + " - " + proveedorIf.getDescripcion());
			cargarTabla();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al setear el proveedor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private CarteraDetalleIf registrarCarteraDetalle(int i, CarteraDetalleIf carteraDetalle) {
		carteraDetalle.setDiferido(null);
		return carteraDetalle;
	}
	
	public void save() {
		try {
			carteraDetalleColeccion.clear();
			generarColeccionActualizar();
			if(actualizarColeccion.indexOf(true) == -1){
				SpiritAlert.createAlert("Debe seleccionar al menos uno para actualizar !",SpiritAlert.WARNING);
			}else{
				if(actualizarColeccion.size() > 0){
					for(int i=0; i<actualizarColeccion.size(); i++) {
						if(actualizarColeccion.get(i)){
							CarteraDetalleIf carteraDetalleIf = getCarteraPagoDetalleColeccion().get(i);
							CarteraIf carteraIf = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleIf.getCarteraId());
							carteraDetalleIf = registrarCarteraDetalle(i, carteraDetalleIf);
							carteraDetalleColeccion.add(carteraDetalleIf);
							carterasPagadasDiferidasColeccion.add(carteraIf);
						}
					}
					carterasPagadasDiferidasColeccion = removerCarterasRepetidas(carterasPagadasDiferidasColeccion);
					SessionServiceLocator.getCarteraSessionService().actualizarCarteraPagosDiferidos(carterasPagadasDiferidasColeccion, carteraDetalleColeccion, Parametros.getIdEmpresa());
					SpiritAlert.createAlert("Actualización realizada con éxito",SpiritAlert.INFORMATION);
					showSaveMode();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public Vector<CarteraIf> removerCarterasRepetidas(Vector<CarteraIf> carterasColeccion){
		Map<Long,CarteraIf> carterasMapa = new HashMap<Long,CarteraIf>();
		for(CarteraIf carteraRepetida : carterasColeccion){
			carterasMapa.put(carteraRepetida.getId(), carteraRepetida);
		}
		carterasColeccion.clear();
		Iterator carterasMapaIt = carterasMapa.keySet().iterator();
		while(carterasMapaIt.hasNext()){
			Long key = (Long)carterasMapaIt.next();
			carterasColeccion.add(carterasMapa.get(key));
		}
		
		return carterasColeccion;
	}
	
	private boolean esUltimoDetalleCartera(long idCartera, int i) {
		for (int j=i+1; j<getCarteraPagoDetalleColeccion().size(); j++) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) getCarteraPagoDetalleColeccion().get(j);
			if (actualizarColeccion.get(j) && carteraDetalle.getCarteraId().compareTo(idCartera) == 0)
				return false;
		}
		
		return true;
	}
	
	private void generarColeccionActualizar() {
		actualizarColeccion.clear();
		boolean actualizarSeleccionado = false;
		for(int i=0; i<getCarteraPagoDetalleColeccion().size(); i++){
			actualizarSeleccionado = (Boolean)getTblPagosDiferidos().getModel().getValueAt(i,0);
			actualizarColeccion.add(actualizarSeleccionado);
		}
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		carterasPagadasDiferidasColeccion.clear();
		carterasComprasPagadasDiferidasColeccion.clear();
		carteraColeccion.clear();
		actualizarColeccion.clear();
		carteraPagoDetalleColeccion.clear();
		
		DefaultTableModel model = (DefaultTableModel) getTblPagosDiferidos().getModel();
		for(int i= this.getTblPagosDiferidos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtProveedor().setEditable(false);
		clean();
		cargarTabla();
	}
	
	private void cargarTabla() {
		try {
			ArrayList cartera = new ArrayList();
			if(proveedorIf != null){
				cartera = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoByCarteraAndByClienteOficinaId("P", "CEG", Parametros.getIdEmpresa(), APROBADO_SI, CARTERA_SI, proveedorIf.getId());
			} else {
				cartera = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera("P", "CEG", Parametros.getIdEmpresa(), APROBADO_SI, CARTERA_SI);
			}
			if(cartera.size()>0){
				Iterator carteraIterator = cartera.iterator();
				
				if(!getCarteraColeccion().isEmpty())
					getCarteraColeccion().removeAllElements();
				if (!getCarteraPagoDetalleColeccion().isEmpty())
					getCarteraPagoDetalleColeccion().removeAllElements();
				
				while (carteraIterator.hasNext()) {
					Object[] carteraCliente = (Object[]) carteraIterator.next();
					CarteraIf carteraIf = (CarteraIf) carteraCliente[0];
					Map parameterMap = new HashMap();
					parameterMap.put("carteraId", carteraIf.getId());
					parameterMap.put("cartera", "S");
					parameterMap.put("diferido", "D");
					Iterator carteraDetalleIterator = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByQuery(parameterMap).iterator();
					while (carteraDetalleIterator.hasNext()) {
						CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleIterator.next();
						if((carteraDetalleIf.getDocumentoId() != null) && (Utilitarios.compararFechas(new Date(carteraDetalleIf.getFechaCartera().getTime()), new Date(Utilitarios.calendarHoy().getTime().getTime())) != 1)) {
							tableModel = (DefaultTableModel) getTblPagosDiferidos().getModel();
							Vector<Object> fila = new Vector<Object>();
							getCarteraPagoDetalleColeccion().add(carteraDetalleIf);
							agregarColumnasTabla(carteraIf, fila, carteraDetalleIf);
							tableModel.addRow(fila);
						}
					}					
					getCarteraColeccion().add(carteraIf);
				}			
				
				if (getTblPagosDiferidos().getRowCount() <= 0)
					getBtnSeleccionarTodos().setEnabled(false);
				else 
					getBtnSeleccionarTodos().setEnabled(true);
			} else {
				SpiritAlert.createAlert("No existe ningún registro que presentar !",SpiritAlert.INFORMATION);
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} 
	}
	
	private void agregarColumnasTabla(CarteraIf carteraIf, Vector<Object> fila, CarteraDetalleIf carteraDetalleIf){
		try {
			clienteOficina = (ClienteOficinaIf) clienteOficinasMap.get(carteraIf.getClienteoficinaId());
			cliente = (ClienteIf) clientesMap.get(clienteOficina.getClienteId());
			cartera = SessionServiceLocator.getCarteraSessionService().getCartera(Long.parseLong(carteraDetalleIf.getReferencia()));
			compra = (CompraIf) comprasMap.get(cartera.getReferenciaId());
						
			fila.add(new Boolean(false));
			fila.add(cliente.getIdentificacion() + " - " + cliente.getNombreLegal());
			fila.add(compra.getPreimpreso());
			fila.add(Utilitarios.getFechaCortaUppercase(compra.getFechaVencimiento()));
			fila.add(formatoDecimal.format(carteraDetalleIf.getValor()).toString());
			
			CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalleIf.getCuentaBancariaId());
			BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
			fila.add("  " + banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta());
			
			fila.add(carteraDetalleIf.getPreimpreso());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initMaps() {
		clientesMap = mapearClientes();
		clienteOficinasMap = mapearClienteOficinas();
		comprasMap = mapearCompras();
	}
	
	public Map mapearClientes() {
		Map clientesMap = new HashMap();
		
		try {
			Collection clientesColeccion = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIterator = clientesColeccion.iterator();
			while (clientesIterator.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIterator.next();
				clientesMap.put(cliente.getId(), cliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return clientesMap;
	}
	
	public Map mapearClienteOficinas() {
		Map clienteOficinasMap = new HashMap();
		
		try {
			Collection clienteOficinasColeccion = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clienteOficinasIterator = clienteOficinasColeccion.iterator();
			while (clienteOficinasIterator.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clienteOficinasIterator.next();
				clienteOficinasMap.put(clienteOficina.getId(), clienteOficina);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return clienteOficinasMap;
	}
	
	public Map mapearCompras() {
		Map comprasMap = new HashMap();
		
		try {
			Collection comprasColeccion = SessionServiceLocator.getCompraSessionService().findComprasPorPagarByEmpresaId(Parametros.getIdEmpresa(), true);
			Iterator comprasIterator = comprasColeccion.iterator();
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				comprasMap.put(compra.getId(), compra);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return comprasMap;
	}
	
	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
		cargarTabla();
	}
	
	public Vector<CarteraIf> getCarteraColeccion() {
		return carteraColeccion;
	}

	public void setCarteraColeccion(Vector<CarteraIf> carteraColeccion) {
		this.carteraColeccion = carteraColeccion;
	}
	
	public Vector<CarteraDetalleIf> getCarteraPagoDetalleColeccion() {
		return carteraPagoDetalleColeccion;
	}

	public void setCarteraPagoDetalleColeccion(Vector<CarteraDetalleIf> carteraPagoDetalleColeccion) {
		this.carteraPagoDetalleColeccion = carteraPagoDetalleColeccion;
	}
}
