package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.panel.JPCobrosDiferidos;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.util.Utilitarios;

public class CobrosDiferidosModel extends JPCobrosDiferidos{

	private static final long serialVersionUID = 8795767599090319544L;

	private static final String CARTERA_SI = "S";
	private static final String CODIGO_TIPO_CLIENTE = "CL";	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");	
	private Map<String,CuentaBancariaIf> cuentasBancosMapa = new HashMap<String,CuentaBancariaIf>();
	private String cuentaCobro = "";
	private Vector<CarteraDetalleIf> carteraDetalleColeccion = new Vector<CarteraDetalleIf>();
	private Vector<CarteraDetalleIf> actualizarCarteraDetalleColeccion = new Vector<CarteraDetalleIf>();
	private ClienteOficinaIf clienteOficinaIf = null;
	private ClienteIf clienteIf = null;	
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();

	public CobrosDiferidosModel(){
		iniciarComponente();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponente(){		
		anchoColumnasTabla();
		celdasTabla();
	}
	
	private void celdasTabla(){
		try{			
			cuentasBancosMapa.clear();
			// ---- CELDA DE COMBO ----
			JComboBox comboBox = new JComboBox();
			Collection cuentasBancarias;
			cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa());
			Iterator cuentasBancariasIt = cuentasBancarias.iterator();
			int contador = 0;
			while(cuentasBancariasIt.hasNext()){
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasIt.next();
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				String linea = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
				comboBox.addItem(linea);
				cuentasBancosMapa.put(linea,cuentaBancaria);
				if ( contador == 0 )
					cuentaCobro = linea;
				contador++;
			}				
			TableColumn txtColumna = getTblCobrosDiferidos().getColumnModel().getColumn(4);
			txtColumna.setCellEditor(new DefaultCellEditor(comboBox));
			//------------------------------------------------------------------
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	private void anchoColumnasTabla() {
		getTblCobrosDiferidos().getTableHeader().setReorderingAllowed(false);
		getTblCobrosDiferidos().setCellSelectionEnabled(true);
		getTblCobrosDiferidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblCobrosDiferidos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn anchoColumna = getTblCobrosDiferidos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblCobrosDiferidos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblCobrosDiferidos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);	
		anchoColumna = getTblCobrosDiferidos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCobrosDiferidos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(320);
		
	}
	
	public void initListeners(){
		getBtnSeleccionarTodos().addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				if (getTblCobrosDiferidos().getRowCount() > 0) {
					if ((Boolean)getTblCobrosDiferidos().getValueAt(0,0) == false) {
						for(int i=0; i<carteraDetalleColeccion.size(); i++){					
							getTblCobrosDiferidos().setValueAt(true, i, 0);
						}
					} else {
						for(int i=0; i<carteraDetalleColeccion.size(); i++){					
							getTblCobrosDiferidos().setValueAt(false, i, 0);
						}
					}				
				}
			}			
		});
		
		getCbTodos().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlChechBoxTodos();
			}
		});
		
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarCliente();
			}
		});
		
		getBtnConsultar().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlChechBoxTodos();
				clean();
				cargarTabla();
			}
		});
	}
	
	private void controlChechBoxTodos(){
		if(getCbTodos().isSelected()){
			clienteIf = null;
			clienteOficinaIf = null;
			getTxtCliente().setText("");
		}
	}
	
	private void buscarCliente(){
		ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(" de Cliente Oficina", 0L, 0L, CODIGO_TIPO_CLIENTE, "", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);	
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null) {
			clienteOficinaIf = null;
			clienteIf = null;
			try {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				if (clienteIf == null) {
					clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteOficinaIf.getDescripcion());
				}
				getCbTodos().setSelected(false);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private void cargarTabla(){
		try {
			Collection<CarteraIf> carteras = null;
			if(clienteOficinaIf != null){
				carteras = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoByCarteraAndByClienteOficinaId("C", "CIN", Parametros.getIdEmpresa(), null, CARTERA_SI, clienteOficinaIf.getId());
			} else {
				carteras = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera("C", "CIN", Parametros.getIdEmpresa(), null, CARTERA_SI);
			}
			
			DocumentoIf documento = null;			
			//Collection<DocumentoIf> documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("CHPO");
			Collection<DocumentoIf> documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigoEmpresaId("CHPO", Parametros.getIdEmpresa());		
			if ( documentos!=null && documentos.size()==1 ){
				documento =  documentos.iterator().next();
			} else
				throw new GenericBusinessException("No existe Documento con codigo \"CHPO\" correspondiente a Cheque Postfechado  ");
			
			if ( carteras != null && carteras.size() > 0 ){
				DefaultTableModel modelo = (DefaultTableModel) getTblCobrosDiferidos().getModel();
				double sumaAfectas = 0.0;
				if (!carteraDetalleColeccion.isEmpty())
					carteraDetalleColeccion.removeAllElements();
				
				Iterator carteraIterator = carteras.iterator();
				while ( carteraIterator.hasNext() ){
					Object[] carteraCliente = (Object[]) carteraIterator.next();
					CarteraIf cartera = (CarteraIf) carteraCliente[0];
					
					Map<String,Object> mapa = new HashMap<String, Object>();
					mapa.put("carteraId", cartera.getId());
					mapa.put("diferido", "D");
					mapa.put("documentoId", documento.getId());
					manejarClientesOficina(cartera.getClienteoficinaId());
					Collection<CarteraDetalleIf> carteraDetalles = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByQueryByFechaCarteraMenorFechaActual(mapa);
					for ( CarteraDetalleIf carteraDetalleIf : carteraDetalles){
						sumaAfectas = 0.00;
						Collection<CarteraAfectaIf> carteraAfectas = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleIf.getId());
						for ( CarteraAfectaIf carteraAfectaIf : carteraAfectas ){
							sumaAfectas += Utilitarios.redondeoValor(carteraAfectaIf.getValor().doubleValue());
						}
						sumaAfectas = Utilitarios.redondeoValor(sumaAfectas);
						
						if ( sumaAfectas > 0.00 ){
							carteraDetalleColeccion.add(carteraDetalleIf);
							
							Vector<Object> fila = armarFilaTabla(cartera,carteraDetalleIf,sumaAfectas);
							modelo.addRow(fila);
						}
					}
				}
			}	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al consultar carteras", SpiritAlert.ERROR);
		}
	}
	
	private Vector<Object> armarFilaTabla(CarteraIf cartera,CarteraDetalleIf carteraDetalleIf,Double sumaAfectas) throws GenericBusinessException{
		ClienteOficinaIf clienteOficinaIf = null;
		Vector<Object> fila = new Vector<Object>();
		clienteOficinaIf = (ClienteOficinaIf) mapaClienteOficina.get(cartera.getClienteoficinaId());
		ClienteIf clienteIf = (ClienteIf) mapaCliente.get(clienteOficinaIf.getClienteId());
		
		fila.add(new Boolean(false));
		fila.add(clienteIf.getIdentificacion() + " - " + clienteOficinaIf.getDescripcion());
		fila.add(Utilitarios.getFechaCortaUppercase(carteraDetalleIf.getFechaCartera()));
		fila.add(formatoDecimal.format(sumaAfectas));
		
		//Se pone la primera cuenta que aparece.
		/*if ( cuentaCobro != "" )
			fila.add(cuentaCobro);
		else
			fila.add("");*/
		
		return fila;
	}
	
	private void manejarClientesOficina(Long idClienteOficina){
		if ( !mapaClienteOficina.containsKey(idClienteOficina) ){
			try {
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(idClienteOficina);
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
				manejarClientes(clienteOficina.getClienteId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void manejarClientes(Long idCliente){
		if ( !mapaCliente.containsKey(idCliente) ){
			try {
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
				mapaCliente.put(cliente.getId(), cliente);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}

	public void save() {
		if(validateFields()){
			generarColeccionActualizar();
			if(actualizarCarteraDetalleColeccion.size()>0){
				try {
					Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
					parametrosEmpresa.put("idEmpresa", Parametros.getIdEmpresa());
					parametrosEmpresa.put("idOficina", Parametros.getIdOficina());
					SessionServiceLocator.getCarteraSessionService().procesarCobrosDiferidos(actualizarCarteraDetalleColeccion, parametrosEmpresa);
					SpiritAlert.createAlert("Cartera actualizada con \u00e9xito",SpiritAlert.INFORMATION);
					showSaveMode();
					
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error general al actualizar cobros", SpiritAlert.ERROR);
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar al menos uno para actualizar !",SpiritAlert.WARNING);
			}
		}
	}
	
	private void generarColeccionActualizar(){
		actualizarCarteraDetalleColeccion.clear();
		boolean seleccionado = false;
		for ( int i=0 ; i < getTblCobrosDiferidos().getRowCount() ; i++ ){
			int fila = getTblCobrosDiferidos().convertRowIndexToModel(i);
			seleccionado = (Boolean) getTblCobrosDiferidos().getModel().getValueAt(fila,0) ;
			if ( seleccionado ){
				//obtengo la cuenta bancaria
				String cuenta = (String) getTblCobrosDiferidos().getModel().getValueAt(fila,4) ;
				CuentaBancariaIf cb = cuentasBancosMapa.get(cuenta);
				
				//Pongo el id de la cuenta bancaria en la carteraDetalle
				CarteraDetalleIf cd = carteraDetalleColeccion.get(fila);
				cd.setCuentaBancariaId(cb.getId());
				actualizarCarteraDetalleColeccion.add( cd  );
			}
		}
	}

	@Override
	public void delete() {
	}

	@Override
	public void update() {
	}
	
	@Override
	public void refresh() {
		clean();
		cargarTabla();
	}

	public void clean() {
		actualizarCarteraDetalleColeccion.clear();
		carteraDetalleColeccion.clear();
		
		DefaultTableModel model = (DefaultTableModel) getTblCobrosDiferidos().getModel();
		for(int i= this.getTblCobrosDiferidos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCliente().setEditable(false);
		getBtnConsultar().grabFocus();
	}

	public boolean validateFields() {
		for(int i=0; i<getTblCobrosDiferidos().getRowCount(); i++){
			if(((Boolean)getTblCobrosDiferidos().getModel().getValueAt(i,0)) && (getTblCobrosDiferidos().getModel().getValueAt(i,4) == null)){
				SpiritAlert.createAlert("Debe seleccionar una Cuenta Bancaria",SpiritAlert.WARNING);
				getTblCobrosDiferidos().getSelectionModel().setSelectionInterval(i, i);
				getTblCobrosDiferidos().getColumnModel().getSelectionModel().setSelectionInterval(4, 4);
				return false;
			}
		}
		return true;
	}
}
