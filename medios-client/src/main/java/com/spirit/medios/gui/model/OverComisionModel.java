package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.OverComisionData;
import com.spirit.medios.entity.OverComisionIf;
import com.spirit.medios.gui.panel.JPOverComision;
import com.spirit.medios.gui.reporteData.OverComisionReporteData;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class OverComisionModel extends JPOverComision {

	private static final int MAX_LONGITUD_PORCENTAJE = 5;
	private static final int MAX_LONGITUD_VALOR = 22;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteOficinaCriteria proveedorOficinaCriteria;
	protected ClienteOficinaIf proveedorOficinaIf;
	protected ClienteIf proveedorIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private int selectedRowTblOver = -1;
	private DefaultTableModel modelTblOver;
	private List<OverComisionIf> overColeccion = new ArrayList<OverComisionIf>();
	private List<OverComisionIf> overColeccionEliminado = new ArrayList<OverComisionIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	final JPopupMenu  popupOver = new JPopupMenu();
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no};
	private Map<Long,ClienteOficinaIf> mapaClienteOficinas = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaClientes = new HashMap<Long, ClienteIf>();
	
	
	public OverComisionModel(){
		cargarMapas();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	public void cargarMapas(){
		try {
			mapaClientes.clear();
			Collection clientes = SessionServiceLocator
					.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaClientes.put(cliente.getId(), cliente);
			}
			
			mapaClienteOficinas.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficinas.put(clienteOficina.getId(), clienteOficina);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void initKeyListeners() {
		getTxtProveedor().setEditable(false);
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");		
		
		getTxtInversionDe().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtInversionDe().addKeyListener(new NumberTextFieldDecimal());
		getTxtInversionA().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtInversionA().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtPorcentajeOver().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeOver().addKeyListener(new NumberTextFieldDecimal());
			
		getCmbAnio().setLocale(Utilitarios.esLocale);
		getCmbAnio().setFormat(Utilitarios.setFechaAnio());
		getCmbAnio().setEditable(false);
		getCmbAnio().setShowNoneButton(false);
		
		getBtnAgregarOver().setText("");
		getBtnActualizarOver().setText("");
		getBtnEliminarOver().setText("");
		getBtnAgregarOver().setToolTipText("Agregar Producto");
		getBtnAgregarOver().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarOver().setToolTipText("Actualizar Producto");
		getBtnActualizarOver().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarOver().setToolTipText("Eliminar Producto");
		getBtnEliminarOver().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblOver());
		getTblOver().getTableHeader().setReorderingAllowed(false);
		//getTblOver().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTblOver().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblOver().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblOver().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOver().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblOver().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblOver().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOver().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(60);
					
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblOver().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblOver().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblOver().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblOver().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblOver().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblOver().getModel();
		for(int i= this.getTblOver().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void clean() {
		overColeccion.clear();
		overColeccionEliminado.clear();
		cleanOver();
		cleanTable();
	}
	
	public void cleanOver(){
		proveedorOficinaIf = null;
		proveedorIf = null;
		getTxtProveedor().setText("");
		getTxtInversionDe().setText("");
		getTxtInversionA().setText("");
		getTxtPorcentajeOver().setText("");
		getCmbObjetivo().setSelectedItem("NO");
		getCmbAnio().setCalendar(new GregorianCalendar());
	}
	
	public void initListeners(){
		getTblOver().addMouseListener(oMouseAdapterTblOver);	    
		getTblOver().addKeyListener(oKeyAdapterTblOver);
			
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Proveedores";
				proveedorOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), proveedorOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					proveedorOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					proveedorIf = mapaClientes.get(proveedorOficinaIf.getClienteId());
					getTxtProveedor().setText(proveedorOficinaIf.getDescripcion() + " (" + proveedorIf.getNombreLegal()+")");
				}
			}
		});
		
		getBtnAgregarOver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarOver();			
			}
		});
			
		getBtnActualizarOver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarOver();			
			}
		});
		
		getBtnEliminarOver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarOver();			
			}
		});
	}
	
	MouseListener oMouseAdapterTblOver = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblOver().getModel().getRowCount()>0)
            	popupOver.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowOverForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblOver = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowOverForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowOverForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSelectedRowTblOver(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
     		OverComisionIf overComisionTemp = (OverComisionIf) overColeccion.get(getSelectedRowTblOver());
     			            	
     		proveedorOficinaIf = mapaClienteOficinas.get(overComisionTemp.getProveedorOficinaId());
     		proveedorIf = mapaClientes.get(overComisionTemp.getProveedorId());
     		getTxtProveedor().setText(proveedorOficinaIf.getDescripcion() + " (" + proveedorIf.getNombreLegal()+")");
     		
     		getCmbAnio().setDate(overComisionTemp.getAnio());     		
     		getTxtInversionDe().setText(formatoDecimal.format(overComisionTemp.getInversionDe()));
     		getTxtInversionA().setText(formatoDecimal.format(overComisionTemp.getInversionA()));
     		getTxtPorcentajeOver().setText(formatoDecimal.format(overComisionTemp.getPorcentajeOver()));
     		
     		if(overComisionTemp.getObjetivo().equals("S")){
     			getCmbObjetivo().setSelectedItem("SI");
     		}else{
     			getCmbObjetivo().setSelectedItem("NO");
     		}
		}
	}
	
	public boolean validateFieldsDetalle(boolean agregar){
		String inversionDe = this.getTxtInversionDe().getText();
		String inversionA = this.getTxtInversionA().getText();
		String porcentajeOver = this.getTxtPorcentajeOver().getText();
		int anio = getCmbAnio().getDate().getYear();
		String objetivo = "N";
		
		if(getCmbObjetivo().getSelectedItem().equals("SI")){
			objetivo = "S";
		}	
		
		if(!agregar && (selectedRowTblOver == -1)){			
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla.",SpiritAlert.WARNING);
			getTxtInversionDe().grabFocus();
			return false;
		}
		
		if(proveedorOficinaIf == null){
			SpiritAlert.createAlert("Debe seleccionar un Proveedor.",SpiritAlert.WARNING);
			getBtnProveedor().grabFocus();
			return false;
		}
				
		if (("".equals(inversionDe) || (inversionDe == null))) {
			SpiritAlert.createAlert("Debe ingresar un valor de inversión De:." ,SpiritAlert.WARNING);
			getTxtInversionDe().grabFocus();
			return false;
		}
		
		if (!"".equals(inversionA) && "".equals(inversionA)) {
			SpiritAlert.createAlert("Debe ingresar un valor de inversión A:." ,SpiritAlert.WARNING);
			getTxtInversionA().grabFocus();
			return false;
		}
		
		if ("".equals(porcentajeOver) && !"".equals(porcentajeOver)) {
			SpiritAlert.createAlert("Debe ingresar un valor de porcentaje de over." ,SpiritAlert.WARNING);
			getTxtPorcentajeOver().grabFocus();
			return false;
		}
				
		for(int i=0;i<overColeccion.size();i++){
			OverComisionIf OverComisionTemp = (OverComisionIf) overColeccion.get(i);
			
			if(agregar){
				if(OverComisionTemp.getProveedorId().compareTo(proveedorIf.getId()) == 0 
				&& OverComisionTemp.getPorcentajeOver().compareTo(BigDecimal.valueOf(Double.valueOf(porcentajeOver))) == 0
				&& OverComisionTemp.getAnio().getYear() == anio){
					SpiritAlert.createAlert("Este Proveedor con este Over en este año ya se encuentra agregado.", SpiritAlert.WARNING);
					getTxtPorcentajeOver().grabFocus();
					return false;
				}
			}else if(i != getSelectedRowTblOver()) {
				if(OverComisionTemp.getProveedorId().compareTo(proveedorIf.getId()) == 0 
						&& OverComisionTemp.getPorcentajeOver().compareTo(BigDecimal.valueOf(Double.valueOf(porcentajeOver))) == 0
						&& OverComisionTemp.getAnio().getYear() == anio){
					SpiritAlert.createAlert("Este Proveedor con este Over en este año ya se encuentra agregado.", SpiritAlert.WARNING);
					getTxtPorcentajeOver().grabFocus();
					return false;
				}
			}
			
			if(agregar){
				if(OverComisionTemp.getProveedorId().compareTo(proveedorIf.getId()) == 0 
				&& OverComisionTemp.getObjetivo().equals(objetivo) && objetivo.equals("S") 
				&& OverComisionTemp.getAnio().getYear() == anio){
					SpiritAlert.createAlert("Este Proveedor en este año ya tiene marcado un Objetivo.", SpiritAlert.WARNING);
					getTxtPorcentajeOver().grabFocus();
					return false;
				}
			}else if(i != getSelectedRowTblOver()) {
				if(OverComisionTemp.getProveedorId().compareTo(proveedorIf.getId()) == 0 
						&& OverComisionTemp.getObjetivo().equals(objetivo) && objetivo.equals("S") 
						&& OverComisionTemp.getAnio().getYear() == anio){
					SpiritAlert.createAlert("Este Proveedor en este año ya tiene marcado un Objetivo.", SpiritAlert.WARNING);
					getTxtPorcentajeOver().grabFocus();
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void agregarOver() {
		if (validateFieldsDetalle(true)) {			
			modelTblOver =  (DefaultTableModel) getTblOver().getModel();
			Vector<String> filaOver = new Vector<String>();

			OverComisionData data = new OverComisionData();
			
			data.setProveedorOficinaId(proveedorOficinaIf.getId());
			data.setProveedorId(proveedorIf.getId());
			Timestamp anio = new Timestamp(getCmbAnio().getDate().getTime());
			data.setAnio(anio);
			BigDecimal inversionDe = BigDecimal.valueOf(Double.valueOf(getTxtInversionDe().getText().replace(",", "")));
			data.setInversionDe(inversionDe);
			BigDecimal inversionA = BigDecimal.valueOf(Double.valueOf(getTxtInversionA().getText().replace(",", "")));
			data.setInversionA(inversionA);
			BigDecimal porcentajeOver = BigDecimal.valueOf(Double.valueOf(getTxtPorcentajeOver().getText().replace(",", "")));
			data.setPorcentajeOver(porcentajeOver);
						
			if(getCmbObjetivo().getSelectedItem().equals("SI")){
				data.setObjetivo("S");
			}else{
				data.setObjetivo("N");
			}			
					
			overColeccion.add(data);

			filaOver.add(proveedorOficinaIf.getDescripcion() + " (" + proveedorIf.getNombreLegal()+")");
			filaOver.add(Utilitarios.getFechaAnio(getCmbAnio().getDate()));
			filaOver.add(formatoDecimal.format(inversionDe));
			filaOver.add(formatoDecimal.format(inversionA));
			filaOver.add(formatoDecimal.format(porcentajeOver));
			if(getCmbObjetivo().getSelectedItem().equals("SI")){
				filaOver.add("SI");
			}else{
				filaOver.add("NO");
			}		
			
			modelTblOver.addRow(filaOver);
			cleanOver();
		}
	}
	
	private void actualizarOver() {
		if (validateFieldsDetalle(false)) {			
			modelTblOver =  (DefaultTableModel) getTblOver().getModel();
			
			OverComisionIf data = (OverComisionIf) overColeccion.get(getSelectedRowTblOver());
						
			data.setProveedorOficinaId(proveedorOficinaIf.getId());
			data.setProveedorId(proveedorIf.getId());
			Timestamp anio = new Timestamp(getCmbAnio().getDate().getTime());
			data.setAnio(anio);
			BigDecimal inversionDe = BigDecimal.valueOf(Double.valueOf(getTxtInversionDe().getText().replace(",", "")));
			data.setInversionDe(inversionDe);
			BigDecimal inversionA = BigDecimal.valueOf(Double.valueOf(getTxtInversionA().getText().replace(",", "")));
			data.setInversionA(inversionA);
			BigDecimal porcentajeOver = BigDecimal.valueOf(Double.valueOf(getTxtPorcentajeOver().getText().replace(",", "")));
			data.setPorcentajeOver(porcentajeOver);
						
			if(getCmbObjetivo().getSelectedItem().equals("SI")){
				data.setObjetivo("S");
			}else{
				data.setObjetivo("N");
			}
			
			overColeccion.set(getSelectedRowTblOver(),data);

			modelTblOver.setValueAt(proveedorOficinaIf.getDescripcion() + " (" + proveedorIf.getNombreLegal()+")", getSelectedRowTblOver(), 0);
			modelTblOver.setValueAt(Utilitarios.getFechaAnio(getCmbAnio().getDate()), getSelectedRowTblOver(), 1);
			modelTblOver.setValueAt(formatoDecimal.format(inversionDe), getSelectedRowTblOver(), 2);
			modelTblOver.setValueAt(formatoDecimal.format(inversionA), getSelectedRowTblOver(), 3);
			modelTblOver.setValueAt(formatoDecimal.format(porcentajeOver), getSelectedRowTblOver(), 4);
			if(getCmbObjetivo().getSelectedItem().equals("SI")){
				modelTblOver.setValueAt("SI", getSelectedRowTblOver(), 5);
			}else{
				modelTblOver.setValueAt("NO", getSelectedRowTblOver(), 5);
			}
			
			cleanOver();
		}
	}
	
	private void eliminarOver() {
		if (getTblOver().getSelectedRow()!=-1){
			int opcion = JOptionPane.showOptionDialog(null, "¿Esta seguro que desea eliminar el Over?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				OverComisionIf OverComisionTemp = (OverComisionIf) overColeccion.get(getSelectedRowTblOver());
				overColeccionEliminado.add(OverComisionTemp);    			
				overColeccion.remove(getSelectedRowTblOver());
				modelTblOver.removeRow(getSelectedRowTblOver());			
				cleanOver();
			}			
		} else {
			SpiritAlert.createAlert("Primero debe seleccionar en la tabla el Producto que desea eliminar!",SpiritAlert.WARNING);
		}	
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	Comparator<OverComisionReporteData> ordenadorOverComisionReporteDataPorPorcentajeOver = new Comparator<OverComisionReporteData>(){
		public int compare(OverComisionReporteData o1, OverComisionReporteData o2) {
			return o1.getPorcentajeOver().compareTo(o2.getPorcentajeOver());			
		}		
	};
	
	Comparator<OverComisionReporteData> ordenadorOverComisionReporteDataPorAnio = new Comparator<OverComisionReporteData>(){
		public int compare(OverComisionReporteData o1, OverComisionReporteData o2) {
			return o1.getAnio().compareTo(o2.getAnio());			
		}		
	};
	
	Comparator<OverComisionReporteData> ordenadorOverComisionReporteDataPorProveedor = new Comparator<OverComisionReporteData>(){
		public int compare(OverComisionReporteData o1, OverComisionReporteData o2) {
			return o1.getProveedor().compareTo(o2.getProveedor());			
		}		
	};

	public void report() {
		try {				
			if (getTblOver().getModel().getRowCount() > 0) {
				
				//creo coleccion para el reporte
				ArrayList<OverComisionReporteData> overComisionColeccion = new ArrayList<OverComisionReporteData>();
				for(OverComisionIf overComisionIf : overColeccion){
					OverComisionReporteData overComisionData = new OverComisionReporteData();
										
					ClienteOficinaIf proveedorOficina = mapaClienteOficinas.get(overComisionIf.getProveedorOficinaId());
					ClienteIf proveedor = mapaClientes.get(overComisionIf.getProveedorId());
					
					overComisionData.setProveedor(proveedorOficina.getDescripcion() + " (" + proveedor.getNombreLegal() + ")");
					overComisionData.setAnio(Utilitarios.getFechaAnio(overComisionIf.getAnio()));
					overComisionData.setInversionDe(formatoDecimal.format(overComisionIf.getInversionDe()));
					overComisionData.setInversionA(formatoDecimal.format(overComisionIf.getInversionA()));
					overComisionData.setPorcentajeOver(formatoDecimal.format(overComisionIf.getPorcentajeOver()));
					if(overComisionIf.getObjetivo().equals("S")){
						overComisionData.setObjetivo("SI");
					}else{
						overComisionData.setObjetivo("NO");
					}
					
					//filtros de reporte
					boolean verProveedor = false;
					boolean verAnio = false;
					if(!getCbVerReporteProveedor().isSelected() 
							|| (getCbVerReporteProveedor().isSelected() 
									&& proveedorOficina.getId().compareTo(proveedorOficinaIf.getId()) == 0)){
						verProveedor = true;
					}
					int anio = getCmbAnio().getDate().getYear();
					if(!getCbVerReporteAnio().isSelected() 
							|| (getCbVerReporteAnio().isSelected() 
									&& anio == overComisionIf.getAnio().getYear())){
						verAnio = true;
					}
					
					//añandir datos al reporte
					if(verProveedor && verAnio){
						overComisionColeccion.add(overComisionData);
					}
				}				
				
				//ordeneno coleccion
				Collections.sort((ArrayList)overComisionColeccion,ordenadorOverComisionReporteDataPorPorcentajeOver);
				Collections.sort((ArrayList)overComisionColeccion,ordenadorOverComisionReporteDataPorAnio);
				Collections.sort((ArrayList)overComisionColeccion,ordenadorOverComisionReporteDataPorProveedor);
				
				//reporte
				String fileName = "jaspers/medios/RPOverComision.jasper";
				
				HashMap parametrosMap = new HashMap();
				
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				String fechaActual = Utilitarios.dateHoraHoy();
				String year = fechaActual.substring(0,4);
				String month = fechaActual.substring(5,7);
				String day = fechaActual.substring(8,10);
				String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
				parametrosMap.put("usuario", Parametros.getUsuario());
				parametrosMap.put("emitido", fechaEmision);
				
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
									
				DefaultTableModel tableModel = (DefaultTableModel) getTblOver().getModel();
				//ReportModelImpl.processReport(fileName, parametrosMap, tableModel, true);
				ReportModelImpl.processReport(fileName, parametrosMap, overComisionColeccion, true);
				
			} else{
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			}
			
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			if (overColeccion.size() > 0) {
				SessionServiceLocator.getOverComisionSessionService().procesarOverComision(overColeccion, overColeccionEliminado);
				SpiritAlert.createAlert("Over Comision guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}else{
				SpiritAlert.createAlert("De agregar al menos un over para poder guardar.",SpiritAlert.INFORMATION);
				getBtnProveedor().grabFocus();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!" ,SpiritAlert.ERROR);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
	}
	
	public void cargarTabla(){
		try {
			overColeccion.clear();
			modelTblOver =  (DefaultTableModel) getTblOver().getModel();
			
			Collection overComisiones = SessionServiceLocator.getOverComisionSessionService().getOverComisionList();
			Iterator overComisionesIt = overComisiones.iterator();
			while(overComisionesIt.hasNext()){
				OverComisionIf overComisionIf = (OverComisionIf)overComisionesIt.next();
				
				overColeccion.add(overComisionIf);
				
				ClienteOficinaIf proveedorOficina = mapaClienteOficinas.get(overComisionIf.getProveedorOficinaId());
				ClienteIf proveedor = mapaClientes.get(overComisionIf.getProveedorId());
								
				Vector<String> filaOver = new Vector<String>();
				
				filaOver.add(proveedorOficina.getDescripcion() + " (" + proveedor.getNombreLegal() + ")");
				filaOver.add(Utilitarios.getFechaAnio(overComisionIf.getAnio()));
				filaOver.add(formatoDecimal.format(overComisionIf.getInversionDe()));
				filaOver.add(formatoDecimal.format(overComisionIf.getInversionA()));
				filaOver.add(formatoDecimal.format(overComisionIf.getPorcentajeOver()));
				if(overComisionIf.getObjetivo().equals("S")){
					filaOver.add("SI");
				}else{
					filaOver.add("NO");
				}				
				
				modelTblOver.addRow(filaOver);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	public int getSelectedRowTblOver() {
		return selectedRowTblOver;
	}

	public void setSelectedRowTblOver(int selectedRowTblOver) {
		this.selectedRowTblOver = selectedRowTblOver;
	}
}
