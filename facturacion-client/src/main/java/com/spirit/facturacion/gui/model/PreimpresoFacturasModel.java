package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JPPreimpresoFacturas;
import com.spirit.facturacion.gui.util.PreimpresoCellEditor;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.util.Utilitarios;

public class PreimpresoFacturasModel extends JPPreimpresoFacturas {
	private static final long serialVersionUID = -8119579163072259557L;
	private Vector<FacturaIf> facturasVector = new Vector<FacturaIf>();
	private DefaultTableModel tableModel;
	private Map<Long,String> mapaFacturaPreimpreso = new HashMap<Long,String>();
	public static final int COLUMNA_PREIMPRESO = 5; 
	//private Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{7}");
	private Pattern patron = Pattern.compile(Parametros.getPatronPreimpreso());
	DecimalFormat formatoSerial = new DecimalFormat("00");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public PreimpresoFacturasModel(){
		iniciarComponentes();
		initListener();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFecha().setEditable(false);
		anchoColumnasTabla();
		cargarDocumentos();
	}
	
	private void cargarDocumentos(){
		try {
			Vector<TipoDocumentoIf> tipoDocumentosVector = new Vector<TipoDocumentoIf>(); 
			
			Collection<TipoDocumentoIf> tipoDocumentos = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			for ( TipoDocumentoIf tipoDocumento : tipoDocumentos  ){
				if ( tipoDocumento.getNombre().startsWith("FACTURA") || tipoDocumento.getCodigo().equals("VTA") ){
					tipoDocumentosVector.add(tipoDocumento);
				}
			}
			
			ComboBoxModel modelo = new DefaultComboBoxModel(tipoDocumentosVector);
			getCmbDocumento().setModel(modelo);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		getTblPreimpresos().getTableHeader().setReorderingAllowed(false);
		getTblPreimpresos().setCellSelectionEnabled(true);
		getTblPreimpresos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSorterTable(getTblPreimpresos());
		TableColumn anchoColumna = getTblPreimpresos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(10);
		anchoColumna = getTblPreimpresos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblPreimpresos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblPreimpresos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblPreimpresos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblPreimpresos().getColumnModel().getColumn(COLUMNA_PREIMPRESO);
		anchoColumna.setPreferredWidth(100);
	}
	
	private void initListener() {
		TableColumn col = getTblPreimpresos().getColumnModel().getColumn(COLUMNA_PREIMPRESO);
	    col.setCellEditor(new PreimpresoCellEditor(getTblPreimpresos()));
		getBtnBuscar().addActionListener(alBuscar);
	}
	
	private ActionListener alBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			buscar();
		}
	};
	
	public void generarMapaFacturaPreimpreso(){
		mapaFacturaPreimpreso.clear();
		for (int i=0; i<facturasVector.size(); i++)
			mapaFacturaPreimpreso.put(facturasVector.get(i).getId(), (String)getTblPreimpresos().getValueAt(i,COLUMNA_PREIMPRESO));
	}
	
	public void save() {
		try {
			if(validateFields()){
				generarMapaFacturaPreimpreso();
				for(int i=0; i<facturasVector.size(); i++){
					if(mapaFacturaPreimpreso.containsKey(facturasVector.get(i).getId())){
						actualizarPreimpreso(i);
					}
				}
				SpiritAlert.createAlert("Los Preimpresos de facturas han sido guardados con éxito !", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}

	private void actualizarPreimpreso(int i) throws GenericBusinessException, UnknownServiceException, ServiceInstantiationException {
		FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(facturasVector.get(i).getId());
		String preimpreso = (String)mapaFacturaPreimpreso.get(facturasVector.get(i).getId());
		SessionServiceLocator.getFacturaSessionService().actualizarPreimpreso(factura, preimpreso, Parametros.isActivarReplicacion());
	}

	@Override
	public void delete() {
	}

	@Override
	public void update() {
	}

	public void clean() {
		mapaFacturaPreimpreso.clear();
		
		DefaultTableModel model = (DefaultTableModel) getTblPreimpresos().getModel();
		for(int i= this.getTblPreimpresos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	private void buscar(){
		clean();
		cargarTabla();
	}
	
	private void cargarTabla() {
		try {
			int contador = 0;
			setCursor(SpiritCursor.hourglassCursor);
			ArrayList facturasList = new ArrayList();
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbDocumento().getSelectedItem();
			if ( tipoDocumento!=null && (tipoDocumento.getNombre().startsWith("FACTURA")  || tipoDocumento.getCodigo().equals("VTA"))){
				
				Date fecha = null;
				if ( getCmbFecha().getDate() != null )
					fecha = new Date( getCmbFecha().getDate().getTime() );
				
				//facturasList = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaByOficinaIdAndByFechaFactura(Parametros.getIdOficina(), fecha );
				facturasList = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaByOficinaIdAndByFechaFactura(null, fecha );
				
				if(facturasList.size() > 0){
					Iterator facturasIterator = facturasList.iterator();
					
					if(!facturasVector.isEmpty())
						facturasVector.removeAllElements();
					
					while (facturasIterator.hasNext()) {
						Object[] facturaCliente = (Object[]) facturasIterator.next();
						tableModel = (DefaultTableModel) getTblPreimpresos().getModel();
						Vector<String> fila = new Vector<String>();
						if (agregarColumnasTabla(facturaCliente, fila, contador)) {
							tableModel.addRow(fila);
							contador++;
						}
					}
				}else{
					SpiritAlert.createAlert("No existen Facturas en la fecha Seleccionada!", SpiritAlert.INFORMATION);
				}				
			}
			setCursor(SpiritCursor.normalCursor);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private boolean agregarColumnasTabla(Object[] facturaClienteObject, Vector<String> fila, int contador){
		boolean agregada = false;
		FacturaIf factura = (FacturaIf) facturaClienteObject[0];
		int year = factura.getFechaFactura().getYear() + 1900;
		int month = factura.getFechaFactura().getMonth() + 1;
		if (year >= 2008 && month >= 1) {
			ClienteIf cliente = (ClienteIf) facturaClienteObject[1];
			fila.add(formatoSerial.format(contador + 1));
			fila.add(cliente.getNombreLegal());
			fila.add(Utilitarios.getFechaCortaUppercase(factura.getFechaFactura()));
			fila.add(factura.getObservacion().toString());
			double comisionAgencia = ((factura.getValor().doubleValue()-factura.getDescuento().doubleValue()-factura.getDescuentoGlobal().doubleValue()) * factura.getPorcentajeComisionAgencia().doubleValue()) / 100D;
			double total = factura.getValor().doubleValue()-factura.getDescuento().doubleValue()-factura.getDescuentoGlobal().doubleValue()+factura.getIva().doubleValue()+factura.getIce().doubleValue()+comisionAgencia;
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(total)));
			fila.add(factura.getPreimpresoNumero());
			facturasVector.add(factura);
			agregada = true;
		}
		
		return agregada;
	}
	
	private boolean validateBusqueda(){
		if ( getCmbFecha().getDate() == null ){
			SpiritAlert.createAlert("Seleccionar una fecha de b\u00fasqueda!", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	public boolean validateFields() {
		if(facturasVector.isEmpty()){
			SpiritAlert.createAlert("No existe ning\u00fan registro en la tabla!", SpiritAlert.WARNING);
			return false;
		}
		
		try {
			for(int i=0; i<facturasVector.size(); i++){
				if ( getTblPreimpresos().getValueAt(i,COLUMNA_PREIMPRESO) == null 
						|| "".equals(getTblPreimpresos().getValueAt(i,COLUMNA_PREIMPRESO)) ){
					SpiritAlert.createAlert("Sin preimpreso en fila "+(i+1), SpiritAlert.WARNING);
					getTblPreimpresos().setRowSelectionInterval(i, i);
					return false;
				} else {
					String preimpreso = (String)getTblPreimpresos().getValueAt(i,COLUMNA_PREIMPRESO);
					Matcher matcher = patron.matcher(preimpreso);
					//boolean encontrado = matcher.find();
					boolean encontrado = matcher.matches();
					if ( !encontrado ){
						SpiritAlert.createAlert("Error en formato de preimpreso de la fila "+(i+1)+", el formato debe ser ###-###-#######", SpiritAlert.WARNING);
						getTblPreimpresos().setRowSelectionInterval(i, i);
						return false;
					}
					
					Map queryMap = new HashMap();
					TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(((FacturaIf) facturasVector.get(i)).getTipodocumentoId());
					queryMap.put("idEmpresa", Parametros.getIdEmpresa());
					queryMap.put("tipoDocumentoFactura", tipoDocumento.getFactura());
					queryMap.put("tipoDocumentoNotaVenta", tipoDocumento.getNotaVenta());
					queryMap.put("tipoDocumentoNotaCredito", tipoDocumento.getNotaCredito());
					queryMap.put("preimpresoNumero", preimpreso);
					Iterator it = SessionServiceLocator.getFacturaSessionService().findFacturaPreimpresoDuplicadoByQuery(queryMap).iterator();
					if (it.hasNext()) {
						SpiritAlert.createAlert("Preimpreso duplicado en la fila " + i+1 + ". Ingréselo nuevamente.", SpiritAlert.WARNING);
						return false;
					}
				}
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return true;
	}
	
	public void refresh(){
		clean();
		cargarTabla();
	}
}
