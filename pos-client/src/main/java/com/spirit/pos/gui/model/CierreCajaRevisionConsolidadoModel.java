package com.spirit.pos.gui.model;
  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.gui.panel.JPCierreCajaRevisionConsolidado;
import com.spirit.util.Utilitarios;
 

public class CierreCajaRevisionConsolidadoModel extends JPCierreCajaRevisionConsolidado{
	
	 
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
 
	Long cajaAbiertaID; 
	Vector<CajaSesionIf> cajaPosVector = new Vector<CajaSesionIf>();
	CajaSesionIf cajasesionif;
	
	HashMap<String,Object> parametrosMap = new HashMap<String,Object>();
	
	
	public Long idprincipal=new Long("0");
	 
	VentasConsolidadoIf ventasRevision =null;
	java.sql.Date fechaInicialSeleccionada;
	
	public CierreCajaRevisionConsolidadoModel(){
		getTblDatosConsolidado().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cleanTable();
		clean();
		initKeyListeners();		
		cargarCombos();		
		anchoColumnasTabla();
		new HotKeyComponent(this);	 
	}
	
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(138);
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(138);
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(80);
		
		
		anchoColumna = getTblDatosConsolidado().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(80);
	}

	
	public void cargarCombos(){
		
		cargarComboOficina();
		cargarComboCaja();
		 
	}
	
	public void cleanTable() {	
		DefaultTableModel model = (DefaultTableModel) getTblDatosConsolidado().getModel();
		for(int i= this.getTblDatosConsolidado().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void initKeyListeners(){				
		//	getTxtImporte().addKeyListener(new NumberTextFieldDecimal());
		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
 
		getBtnConsultar().addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent evento) { 	cargarDatos();}	}
		);	
		
		getCmbOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				 				cargarComboCaja();				 
			}
		}); 
	}
	
	
	private void cargarComboOficina(){
		try { 
			List oficinas = (List) SessionServiceLocator.getOficinaSessionService().getOficinaList();
			refreshCombo(getCmbOficina(),oficinas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	//caja general ofcina tal
	private void cargarComboCaja(){
		try {			
			Long oficinaId=((OficinaIf) this.getCmbOficina().getSelectedItem()).getId();	
			System.out.println("OFICINA>"+oficinaId);
			List listaPrecios = (List) SessionServiceLocator.getCajaSessionService().findCajaByOficinaId(oficinaId);
			refreshCombo(getCmbCaja(),listaPrecios);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	
	private String setearUsuarioCedula(String cedula){
		String usuarioString="";
		UsuarioIf usuario=null;
		try { 
			Iterator it= (Iterator)SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByIdentificacion(cedula).iterator();
			
			if(it.hasNext()){
				EmpleadoIf empleado=(EmpleadoIf) it.next();
				Long idempleado=empleado.getId();
				 
				
				Iterator it2= (Iterator)SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(idempleado).iterator();
				if(it2.hasNext())
				{
					usuario=(UsuarioIf) it2.next();
					usuarioString=usuario.getUsuario();
					parametrosMap.put(cedula,usuarioString);
				}
					
			}
			
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		return usuarioString;
	}

	private boolean agregarDetalles(int numero, VentasConsolidadoIf ventasRevision,int tamanio) {
		
		Vector<Object> fila = new Vector<Object>();
		String usuario=(String)parametrosMap.get(ventasRevision.getCajeroCedula());
		 //productosMap.get(bean.getProductoId());
		if(usuario==null) usuario=setearUsuarioCedula(ventasRevision.getCajeroCedula());		
		if(usuario==null) usuario="";
		fila.add(usuario);		
		if(numero==1)
			{
			fila.add("<html><b>"+ventasRevision.getFechaApertura().toString()+"</b></html>");
			getTxtFechaApertura().setText(ventasRevision.getFechaApertura().toString());
			}
		else	
			fila.add(ventasRevision.getFechaApertura().toString());
		
		 
		if(numero==tamanio)
			{
			String fechaCierre="";
			if(ventasRevision.getFechaCierre()==null) fechaCierre="";
			else fechaCierre=ventasRevision.getFechaCierre().toString();
			fila.add("<html><b>"+fechaCierre+"</b></html>");
			getTxtFechaCierre().setText(fechaCierre);
			}
		else	
			{
			String ffin="";
			if(ventasRevision.getFechaCierre()==null) ffin="";
			else ffin=ventasRevision.getFechaCierre().toString();
				
			fila.add(ffin);
			
			}
		
	 
		
		
		boolean ok=true;
		String efectivo="0.00";
		if(ventasRevision.getValorEfectivo()==null) efectivo="0.00";
		else efectivo=ventasRevision.getValorEfectivo().toString();				
		 fila.add(efectivo);
		
		String tc="0.00";;
		if(ventasRevision.getValorTarjeta()==null) tc="0.00";
		else tc=ventasRevision.getValorTarjeta().toString();				
		fila.add(tc);

		String ch="0.00";;
		if(ventasRevision.getValorCheque()==null) ch="0.00";
		else ch=ventasRevision.getValorCheque().toString();				
		fila.add(ch);
		
		String gc="0.00";;
		if(ventasRevision.getValorGiftcards()==null) gc="0.00";
		else gc=ventasRevision.getValorGiftcards().toString();				
		fila.add(gc);
		
		String cc="0.00";;
		if(ventasRevision.getValorCredito()==null) cc="0.00";
		else cc=ventasRevision.getValorCredito().toString();				
		fila.add(cc);
		
		String dev="0.00";;
		if(ventasRevision.getValorDevoluciones()==null) dev="0.00";
		else dev=ventasRevision.getValorDevoluciones().toString();				
		fila.add(dev);
		
		String don="0.00";;
		if(ventasRevision.getValorDonacion()==null) don="0.00";
		else don=ventasRevision.getValorDonacion().toString();
		fila.add(don);
				
		
		///////
		String ci="0.00";;
		if(ventasRevision.getValorCajaInicial()==null) ci="0.00";
		else ci=ventasRevision.getValorCajaInicial().toString();				
	 
		if(numero==1)
			{
			fila.add("<html><b>"+ci+"</b></html>");
			getTxtValorInicial().setText(ci);
			}
		else	
			{
			fila.add(ci);			
			}
		
		String ing="0.00";;
		if(ventasRevision.getValorCajaIngreso()==null) ing="0.00";
		else ing=ventasRevision.getValorCajaIngreso().toString();				
		fila.add(ing);
		
		String egr="0.00";;
		if(ventasRevision.getValorCajaEgreso()==null) egr="0.00";
		else egr=ventasRevision.getValorCajaEgreso().toString();				
		fila.add(egr);
		
						
		 
		
		/////////////////
		//descuadre efectivo
		String de="";;
		if(ventasRevision.getValorDescuadre()==null) de="0.00";
		else de=ventasRevision.getValorDescuadre().toString();				
		fila.add(de); 
		
		//descuadre documentos
		String dd="";;
		if(ventasRevision.getValorDocumentos()==null) dd="0.00";
		else dd=ventasRevision.getValorDocumentos().toString();				
		fila.add(dd);
				
		//multas documentos
		String md="";;
		if(ventasRevision.getValorMultas()==null) md="0.00";
		else md=ventasRevision.getValorMultas().toString();	
		fila.add(md);
		
		
		BigDecimal cajaInicial=new BigDecimal(ci);
		BigDecimal vefectivo=new BigDecimal(efectivo);
		BigDecimal descuadreEfectivo=new BigDecimal(de);			
		BigDecimal cegre=new BigDecimal(egr);
		BigDecimal cing=new BigDecimal(ing);
		BigDecimal valorFinal=cajaInicial.add(vefectivo).add(descuadreEfectivo).subtract(cegre).add(cing);			
	 
		if(numero==tamanio)
			{
			fila.add("<html><b>"+valorFinal.toString()+"</b></html>");
			getTxtValorFinal().setText(valorFinal.toString());
			}
		else
			{
			fila.add(valorFinal.toString());
			}
		
		
		tableModel.addRow(fila);
		//BGCOLOR="#FFFFFF"
		
		return ok;
	}
	
	public void cargarDatos()  {
		
		clean();
		
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		java.sql.Date fechaInicial = null;

		if (getCmbFechaInicial().getSelectedItem() != null)
			fechaInicialSeleccionada = new java.sql.Date(((Date) getCmbFechaInicial().getDate()).getYear(), ((Date) getCmbFechaInicial().getDate()).getMonth(), ((Date) getCmbFechaInicial().getDate()).getDate());
		else
			fechaInicialSeleccionada = null;
		
 		if(fechaInicialSeleccionada!=null && getCmbCaja().getSelectedItem()!=null  ){
		java.sql.Timestamp fechaApertura=null;	 
		
		String cajaCodigo=((CajaIf) this.getCmbCaja().getSelectedItem()).getCodigo();
		 

		
		System.out.println(cajaCodigo+"fechaInicialSeleccionada---"+fechaInicialSeleccionada.toString());
		Iterator cajavalorIt2;
		try {
			 
			Collection col=SessionServiceLocator.getVentasConsolidadoSessionService().getVentasConsolidadoFechaApertura(cajaCodigo,fechaInicialSeleccionada.toString());

			int tamanio=col.size();
			cajavalorIt2 = col.iterator();
			//cajavalorIt2 = getVentasConsolidadoSessionService().getVentasConsolidadoFechaCierre(cajaCodigo,fechaInicialSeleccionada.toString()).iterator();
				int numero = 1;
			while (cajavalorIt2.hasNext()) {
				ventasRevision = (VentasConsolidadoIf) cajavalorIt2.next();
				 
				tableModel = (DefaultTableModel) getTblDatosConsolidado().getModel();				
				if (agregarDetalles(numero, ventasRevision,tamanio)){
					numero++;
				}
			}
			
			getTblDatosConsolidado().validate();
			getTblDatosConsolidado().repaint();
			
			/*BigDecimal cajaInicial=new BigDecimal(getTxtCajaInicial().getText());
			BigDecimal efectivo=new BigDecimal(getTxtEfectivo().getText());
			BigDecimal descuadreEfectivo=new BigDecimal(getTxtDescuadreEfec().getText());			
			BigDecimal valorFinal=cajaInicial.add(efectivo).add(descuadreEfectivo);			
			getTxtValorFinal().setText(valorFinal.toString());*/
			
			
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		else{			
			clean();
		}
		
	}
	
	
	
	
	
	
	
	@Override
	//Validamos primero el usuario(estado=activo, bloqueado), luego validamos la caja(abierta,bloqueada,inactiva) por usuario.
	public void clean() {
		cleanTable();
		parametrosMap.clear();
	
		getTxtValorFinal().setText("");
		getTxtValorInicial().setText("");
		
		getTxtFechaApertura().setText("");
		getTxtFechaCierre().setText("");
		
	}

	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	

	
	public void revisado(){
		save();
		
	}
//MODELO/TALLA
	//PUNTA/S/CELESTEZX
	public void save() {
		boolean bandera=false;
		 
			if(ventasRevision!=null){
				ventasRevision.setValorGiftcards(new BigDecimal("0.00"));
				try {
					SessionServiceLocator.getVentasConsolidadoSessionService().saveVentasConsolidado(ventasRevision);
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	

	
	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub
		setSaveMode();
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
 
 
	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	
	private Collection initializeBeanCollection(Long idParametro, boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			if (!isHeader) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				reportRows.add(bean);
			} else {
				FacturaIf bean = (FacturaIf) itRows.next();
				reportRows.add(bean);
			}
		}

		return reportRows;
	}

	@Override
	public void report() {
		try {				
			if (getTblDatosConsolidado().getModel().getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Cierre Caja Consolidado?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPCierreCajaRevisionConsolidado.jasper";
					HashMap parametrosMap = new HashMap();					
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("usuario", Parametros.getUsuario());					
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);					
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("emitido", fechaEmision);
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					
					parametrosMap.put("ciudad", "");
				
					String fechaApertura = getTxtFechaApertura().getText().toString();
					if(fechaApertura.length()>10){
					String yearfa = fechaApertura.substring(0,4);
					String monthfa = fechaApertura.substring(5,7);
					String dayfa = fechaApertura.substring(8,10);
					String horafa= fechaApertura.substring(11);
				    fechaApertura = Utilitarios.getNombreMes(Integer.parseInt(monthfa)) + " " + dayfa + " DEL " + yearfa+" "+horafa;
					}
				 
					parametrosMap.put("fechaApertura", fechaApertura);
					
					String fechaCierre = getTxtFechaCierre().getText().toString();
					if(fechaCierre.length()>10){
					String yearci = fechaCierre.substring(0,4);
					String monthci = fechaCierre.substring(5,7);
					String dayci = fechaCierre.substring(8,10);
					String horaci= fechaCierre.substring(11);
					fechaCierre = Utilitarios.getNombreMes(Integer.parseInt(monthci)) + " " + dayci + " DEL " + yearci+" "+horaci;
					}
					System.out.println("E fechaCierre"+fechaCierre);
					
					parametrosMap.put("fechaCierre",  fechaCierre);
					
					parametrosMap.put("valorInicial", new Double(getTxtValorInicial().getText()));
					
					parametrosMap.put("valorFinal", new Double(getTxtValorFinal().getText()));
					
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblDatosConsolidado().getModel(), true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	@Override
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
		
	
	
}
