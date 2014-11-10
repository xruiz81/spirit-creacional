package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.panel.JPAuditoriaComprobantes;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.util.Utilitarios;

public class AuditoriaComprobantesModel extends JPAuditoriaComprobantes {
	
	private static final String TODOS = "TODOS";
	private static final String DIARIO = "DIARIO";
	private static final String NOMBRE_COMPROBANTE_INGRESO = "COMP DE INGRESO";
	private static final String NOMBRE_COMPROBANTE_EGRESO = "COMP DE EGRESO";
	private static final String NOMBRE_ANTICIPO_PROVEEDOR = "ANTICIPO PROVEEDOR";
	private static final String NOMBRE_RETENCIONES_PROVEEDOR = "RET PROVEEDOR";
	private static final String ANULADO = "A";
	private static final String PREASIENTO = "P";
	private static final String COMPROBANTE_INGRESO = "CIN";
	private static final String COMPROBANTE_EGRESO = "CEG";
	private static final String ANTICIPO_PROVEEDOR = "ANP";
	private static final String RETENCIONES_PROVEEDOR = "CRE";
	private static final String CEG = "CEG";
	private static final String ANP = "ANP";
	private String comprobantes = "";
	private DefaultTableModel tableModel;
	private String documento = "";
	
	public AuditoriaComprobantesModel(){
		anchoColumnasTabla();
		cargarComboTipoDocumento();
		initKeyListeners();
		showSaveMode();
		initListeners();
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblAuditoriaComprobantes());
		getTblAuditoriaComprobantes().getTableHeader().setReorderingAllowed(false);
		//getTblAuditoriaComprobantes().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblAuditoriaComprobantes().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblAuditoriaComprobantes().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblAuditoriaComprobantes().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblAuditoriaComprobantes().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
	}
	
	public void initKeyListeners(){
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	private void cargarComboTipoDocumento(){
		getCmbTipoDocumento().removeAllItems();
		getCmbTipoDocumento().addItem(TODOS);
		getCmbTipoDocumento().addItem(DIARIO);
		getCmbTipoDocumento().addItem(NOMBRE_COMPROBANTE_INGRESO);
		getCmbTipoDocumento().addItem(NOMBRE_COMPROBANTE_EGRESO);
		getCmbTipoDocumento().addItem(NOMBRE_ANTICIPO_PROVEEDOR);
		getCmbTipoDocumento().addItem(NOMBRE_RETENCIONES_PROVEEDOR);
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblAuditoriaComprobantes().getModel();
		for(int i= this.getTblAuditoriaComprobantes().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void nuevoTipoComprobante(String tipoComprobante) {
		tableModel = (DefaultTableModel) getTblAuditoriaComprobantes().getModel();
		Vector<String> fila = new Vector<String>();
		tableModel.addRow(fila);
		fila = new Vector<String>();
		fila.add("-- "+tipoComprobante+" --");
		fila.add("----");
		fila.add("-----------------------");
		fila.add("----");
		tableModel.addRow(fila);
		fila = new Vector<String>();
		tableModel.addRow(fila);
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				realizarConsulta();
			}
		});
	}
	
	public void realizarConsulta(){
		try {
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			//aMap.put("status", "A");
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());		
			
			if(getCmbTipoDocumento().getSelectedItem().equals(TODOS)){
				documento = TODOS;
				nuevoTipoComprobante(NOMBRE_COMPROBANTE_INGRESO);
				buscarComprobantes(fechaInicio, fechaFin, COMPROBANTE_INGRESO);				
				nuevoTipoComprobante(NOMBRE_COMPROBANTE_EGRESO);				
				buscarComprobantes(fechaInicio, fechaFin, COMPROBANTE_EGRESO);
				nuevoTipoComprobante(NOMBRE_ANTICIPO_PROVEEDOR);
				buscarComprobantes(fechaInicio, fechaFin, ANTICIPO_PROVEEDOR);
				nuevoTipoComprobante(NOMBRE_RETENCIONES_PROVEEDOR);
				buscarComprobantes(fechaInicio, fechaFin, RETENCIONES_PROVEEDOR);				
				
			}else if(getCmbTipoDocumento().getSelectedItem().equals(DIARIO)){
				documento = DIARIO;
				List<AsientoIf> asientosLista = (ArrayList)SessionServiceLocator.getAsientoSessionService().findAsientoByQueryByFechaInicioAndFechaFin(aMap, fechaInicio, fechaFin);
				Iterator asientosListaIt = asientosLista.iterator();
				while(asientosListaIt.hasNext()){
					AsientoIf asiento = (AsientoIf) asientosListaIt.next();
					
					tableModel = (DefaultTableModel) getTblAuditoriaComprobantes().getModel();
					Vector<String> fila = new Vector<String>();
					String[] numero = asiento.getNumero().split("-");
					fila.add(numero[1]+"-"+numero[2]+"-"+numero[4]);
					fila.add("");
					if(asiento.getTipoDocumentoId() == null && asiento.getTransaccionId() == null){
						fila.add("MANUAL");
					}else{
						fila.add("");						
					}									
					fila.add(asiento.getStatus().equals("P")?asiento.getStatus():"");
					tableModel.addRow(fila);					
				}				
			}else if(getCmbTipoDocumento().getSelectedItem().equals(NOMBRE_COMPROBANTE_INGRESO)){
				documento = NOMBRE_COMPROBANTE_INGRESO;
				buscarComprobantes(fechaInicio, fechaFin, COMPROBANTE_INGRESO);
			}else if(getCmbTipoDocumento().getSelectedItem().equals(NOMBRE_COMPROBANTE_EGRESO)){
				documento = NOMBRE_COMPROBANTE_EGRESO;
				buscarComprobantes(fechaInicio, fechaFin, COMPROBANTE_EGRESO);
			}else if(getCmbTipoDocumento().getSelectedItem().equals(NOMBRE_ANTICIPO_PROVEEDOR)){
				documento = NOMBRE_ANTICIPO_PROVEEDOR;
				buscarComprobantes(fechaInicio, fechaFin, ANTICIPO_PROVEEDOR);
			}else if(getCmbTipoDocumento().getSelectedItem().equals(NOMBRE_RETENCIONES_PROVEEDOR)){
				documento = NOMBRE_RETENCIONES_PROVEEDOR;
				buscarComprobantes(fechaInicio, fechaFin, RETENCIONES_PROVEEDOR);
			}
			
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarComprobantes(Date fechaInicio, Date fechaFin, String tipoComprobante) throws GenericBusinessException {
		
		List<Long> tiposDocumento = new ArrayList<Long>();
		List<TipoDocumentoIf> tipos = (ArrayList)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(tipoComprobante);
		
		for ( TipoDocumentoIf tipoDoc : tipos ){
			tiposDocumento.add(tipoDoc.getId());
		}
		
		List<CarteraIf> comprobantesLista = (ArrayList)SessionServiceLocator.getCarteraSessionService().findCarteraByFechaInicioFechaFinListaTipoDocumento(tiposDocumento, fechaInicio, fechaFin, Parametros.getIdEmpresa());	
		Iterator comprobantesListaIt = comprobantesLista.iterator();
				
		while (comprobantesListaIt.hasNext()) {
			CarteraIf carteraIf = (CarteraIf) comprobantesListaIt.next();
			//En caso que sea un pago aprobado el codigo aun no existe ni el asiento y no se debe poner en el reporte
			if(carteraIf.getCodigo() != null && !carteraIf.getCodigo().equals(" ")){
				tableModel = (DefaultTableModel) getTblAuditoriaComprobantes().getModel();
				Vector<String> fila = new Vector<String>();
				//FacturacionClientesData facturacionData = new FacturacionClientesData();
				
				agregarColumnasTabla(carteraIf, fila);
				
				tableModel.addRow(fila);
				//facturacionColeccion.add(facturacionData);
			}			
		}
	}
	
	public void agregarColumnasTabla(CarteraIf carteraIf, Vector<String>  fila){
		try {
			//Si la cartera tiene codigo agregamos un detalle
			//las carteras de autorizacion de pagos no tienen codigo.
			if(carteraIf.getCodigo() != null && !carteraIf.getCodigo().equals("")){
				String[] codigo = carteraIf.getCodigo().split("-");
				fila.add(codigo[1]+"-"+codigo[2]+"-"+codigo[4]);
				fila.add(carteraIf.getEstado().equals("A")?carteraIf.getEstado():"");
				Map aMap = new HashMap();
				aMap.put("transaccionId", carteraIf.getId());
				aMap.put("tipoDocumentoId", carteraIf.getTipodocumentoId());
			
				if(SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(aMap).size()>0){
					AsientoIf asiento = null;
					Iterator itasi= SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(aMap).iterator();
					if(itasi.hasNext()) asiento = (AsientoIf)itasi.next();
										
					String[] numero = asiento.getNumero().split("-");
					fila.add(numero[1]+"-"+numero[2]+"-"+numero[4]);
					fila.add(asiento.getStatus().equals("P")?asiento.getStatus():"");
					
				}else{
					Collection carterasDetalle = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carteraIf.getId());
					if(carterasDetalle.size() > 0){
						CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carterasDetalle.iterator().next();
						if(carteraDetalle.getDocumentoId() != null && !carteraIf.getEstado().equals("A")){
							DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
							fila.add(documento.getNombre());
							fila.add("");
							
						}else{
							fila.add("");
							fila.add("");
						}
						
					}else{
						fila.add("");
						fila.add("");
					}					
				}
			}	
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		comprobantes = "";
		documento = "";
	}

	public void report() {
		String si = "Si"; 
		String no = "No"; 
		Object[] options ={si,no};
		int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de la Auditoría de Comprobantes?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		if (opcion == JOptionPane.YES_OPTION) {
			try {
				String fileName = "jaspers/cartera/RPAuditoriaComprobantes.jasper";
				DefaultTableModel tblModelReporte = tableModel;
				HashMap parametrosMap = new HashMap();
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				parametrosMap.put("direccion", oficina.getDireccion());
				parametrosMap.put("telefono", "Teléfono: " + oficina.getTelefono() + "    Fax: " + oficina.getFax());
				
				String fechaActual = Utilitarios.dateHoraHoy();
				String year = fechaActual.substring(0,4);
				String month = fechaActual.substring(5,7);
				String day = fechaActual.substring(8,10);
				String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
				parametrosMap.put("fechaEmision", fechaEmision);		
				Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
				Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
				parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
				parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
				parametrosMap.put("comprobantes", comprobantes);
				parametrosMap.put("documento", documento);
				//JREmptyDataSource emptyDataSource = new JREmptyDataSource();
				ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
				
			} catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}
