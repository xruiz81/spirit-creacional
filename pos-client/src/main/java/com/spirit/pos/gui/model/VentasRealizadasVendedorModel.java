package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.handler.ConsultaFacturaVendedor;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoData;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaData;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.pos.gui.panel.JPVentasRealizadasVendedor;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;

public class VentasRealizadasVendedorModel extends JPVentasRealizadasVendedor {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "SALDO ACTUAL CLIENTE";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	EmpleadoIf operadorNegocio = null;	
	BigDecimal totalVentasBrutas;
	BigDecimal totalDescuentos;
	BigDecimal totalFinalSinIVA;
	BigDecimal totalIVA;
	BigDecimal totalFinalConIVA;
	BigDecimal totalDevoluciones;
	String descuentoPorcentaje="0";	
	double saldo;
	double devo;
	double descuentos;
	double netas;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	boolean isTotal = false;

	public VentasRealizadasVendedorModel() {		
		initListeners();
		anchoColumnasTabla();
		showSaveMode();
		new HotKeyComponent(this);		
	}

	private void anchoColumnasTabla() {
		getTblVentasRealizadas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(10);
		anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblVentasRealizadas().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(80);
	}

	private void initListeners() {
		cargarComboOficina();
		cargarComboVendedores();
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				clean();
				llenar_datos_creditos();
			}
		});
		
		getCmbOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
		
				cargarComboVendedores();	
			}
		}); 

		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);

		getBtnResetearFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				getCmbFechaFinal().setSelectedItem(null);
				getCmbFechaFinal().validate();
				getCmbFechaFinal().repaint();
			}
		});

	}

	private void cargarComboOficina(){
		try { 
			List oficinas = (List) SessionServiceLocator.getOficinaSessionService().getOficinaList();
			OficinaData todas = new OficinaData();
			todas.setCodigo("-");
			todas.setNombre("TODAS");
			oficinas.add(todas);
			refreshCombo(getCmbOficina(),oficinas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboVendedores() {
		
		Long oficinaId = null;
		OficinaIf oficina = (OficinaIf) getCmbOficina().getSelectedItem();
		
		if (!oficina.getNombre().equals("TODAS"))
			oficinaId = oficina.getId();
		
		SpiritComboBoxModel cmbModelVendedores = null;
		if (oficinaId != null)
			cmbModelVendedores = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getEmpleadoSessionService().findVendedoresByOficinaId(oficinaId));
		else
			cmbModelVendedores = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getEmpleadoSessionService().findVendedoresByEmpresaId(Parametros.getIdEmpresa()));

		//EmpleadoEJB empvacio = new EmpleadoEJB();
		EmpleadoData empvacio = new EmpleadoData();
		empvacio.setApellidos("VENDEDOR");
		empvacio.setNombres("SIN");
		empvacio.setIdentificacion("-");
		empvacio.setCodigo("-");
		cmbModelVendedores.addElement(empvacio);

		EmpleadoData empTodos = new EmpleadoData();
		empTodos.setApellidos("TODOS");
		empTodos.setNombres("-");
		empTodos.setIdentificacion("-");
		empTodos.setCodigo("-");
		cmbModelVendedores.addElement(empTodos);

		getCmbVendedor().setModel(cmbModelVendedores);
	}

	public void report() {
		try {
			if (getTblVentasRealizadas().getModel().getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = { si, no };
				int opcion = JOptionPane
						.showOptionDialog(
								null,
								"¿Desea generar el reporte de Ventas Realizadas Vendedor?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPVentasRealizadas.jasper";
					HashMap parametrosMap = new HashMap();
					parametrosMap.put("codigoReporte", "VENTAS");
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("oficinaReporte", ((OficinaIf)getCmbOficina().getSelectedItem()).getNombre());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0, 4);
					String month = fechaActual.substring(5, 7);
					String day = fechaActual.substring(8, 10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);

					parametrosMap.put("rucOperadorNegocio", operadorNegocio.getIdentificacion());
					parametrosMap.put("operadorNegocio", operadorNegocio.getNombres() + operadorNegocio.getApellidos());
					parametrosMap.put("totalVentas", new Double(getTxtVentas().getText().toString().replace(",","")));
					parametrosMap.put("totalDev", new Double(getTxtTotalDevoluciones().getText().toString().replace(",","")));
					
					parametrosMap.put("totalDescuentos", new Double(getTxtTotalDescuentos().getText().toString().replace(",","")));
					parametrosMap.put("totalNetas", new Double(getTxtVentasNetas().getText().toString().replace(",","")));
					parametrosMap.put("totalPagarFinal", new Double(getTxtTotalPagarFinal().getText().toString().replace(",","")));
					
					
					
					java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
					java.sql.Date fechaInicial = null;
					if (fecha != null)		fechaInicial = new java.sql.Date(fecha.getYear(), fecha.getMonth(),	fecha.getDate());
					
					String fIni="";
					if(fecha!=null) fIni=fechaInicial.toString();

					fecha = (Date) getCmbFechaFinal().getDate();
					java.sql.Date fechaFinal = null;
					if (fecha != null)		fechaFinal = new java.sql.Date(fecha.getYear(), fecha.getMonth(),fecha.getDate());
					String fFin="";
					if(fecha!=null) fFin=fechaFinal.toString();
					
					parametrosMap.put("fechaInicial", fIni);
					parametrosMap.put("fechaFinal", fFin);
					
					
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblVentasRealizadas().getModel(), true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir",SpiritAlert.INFORMATION);
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void refresh() {

	}

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {
		totalVentasBrutas = BigDecimal.ZERO;		
		totalDescuentos = BigDecimal.ZERO;
		totalFinalSinIVA = BigDecimal.ZERO;
		totalIVA = BigDecimal.ZERO;
		totalFinalConIVA = BigDecimal.ZERO;
		
		saldo = 0D;
		devo = 0D;
		descuentos = 0D;
		netas = 0D;
		getTxtVentas().setText("");
		cleanTable();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblVentasRealizadas().getModel();
		for (int i = this.getTblVentasRealizadas().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	public void llenar_datos_creditos() {
		setCursor(SpiritCursor.hourglassCursor);
		setCursor(SpiritCursor.normalCursor);
		totalVentasBrutas = BigDecimal.ZERO;		
		totalDescuentos = BigDecimal.ZERO;
		totalFinalSinIVA = BigDecimal.ZERO;
		totalIVA = BigDecimal.ZERO;
		totalFinalConIVA = BigDecimal.ZERO;
		totalDevoluciones= BigDecimal.ZERO;
		descuentoPorcentaje="0";		
		Long vacio = 0L;
		EmpleadoIf empleado_vendedor = ((EmpleadoIf) this.getCmbVendedor().getSelectedItem());
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		java.sql.Date fechaInicial = null;
		if (fecha != null) 
			fechaInicial = new java.sql.Date(fecha.getYear(), fecha.getMonth(),	fecha.getDate());
		fecha = (Date) getCmbFechaFinal().getDate();
		java.sql.Date fechaFinal = null;
		if (fecha != null)
			fechaFinal = new java.sql.Date(fecha.getYear(), fecha.getMonth(),fecha.getDate());

		Long oficinaId = null;
		OficinaIf oficina = (OficinaIf) getCmbOficina().getSelectedItem();
		if (!oficina.getNombre().equals("TODAS"))
			oficinaId = ((OficinaIf) this.getCmbOficina().getSelectedItem()).getId();
		operadorNegocio = empleado_vendedor;
		
		if (empleado_vendedor == null) {
			SpiritAlert.createAlert("Debe escoger un Vendedor",	SpiritAlert.INFORMATION);
		} else {
			Iterator facturasIt = null;
			List<ConsultaFacturaVendedor> consultaFacturaVendedorList = null;
			if (empleado_vendedor.getApellidos().equals("VENDEDOR"))// SIN VENDEDOR
			{
				consultaFacturaVendedorList = SessionServiceLocator.getFacturaSessionService().consultaFacturasSinVendedor((fechaInicial != null)?new java.sql.Timestamp(fechaInicial.getTime()):null,(fechaFinal != null)?new java.sql.Timestamp(fechaFinal.getTime()):null,oficinaId);
			} else {
				if (empleado_vendedor.getApellidos().equals("TODOS"))// TODOS LOS VENDEDORES 
				{				
					consultaFacturaVendedorList = SessionServiceLocator.getFacturaSessionService().consultaFacturas(null,(fechaInicial != null)?new java.sql.Timestamp(fechaInicial.getTime()):null,(fechaFinal != null)?new java.sql.Timestamp(fechaFinal.getTime()):null, oficinaId);
				} else {					
					consultaFacturaVendedorList = SessionServiceLocator.getFacturaSessionService().consultaFacturas(empleado_vendedor.getId(), (fechaInicial != null)?new java.sql.Timestamp(fechaInicial.getTime()):null, (fechaFinal != null)?new java.sql.Timestamp(fechaFinal.getTime()):null,oficinaId);					
				}
			}

			int numero = 1;
			tableModel = (DefaultTableModel) getTblVentasRealizadas().getModel();			
			for (ConsultaFacturaVendedor consultaFacturaVendedor : consultaFacturaVendedorList) {
				agregarDetalles(numero++, consultaFacturaVendedor);
			}
			getTblVentasRealizadas().validate();
			getTblVentasRealizadas().repaint(); 
			getTxtVentas().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalVentasBrutas.doubleValue())));
			getTxtTotalDevoluciones().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDevoluciones.doubleValue())));
			getTxtTotalDescuentos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuentos.doubleValue())));
			BigDecimal totalFinalSinIVA2SinDsctosSinDev;
			totalFinalSinIVA2SinDsctosSinDev =totalVentasBrutas.subtract(totalDevoluciones).subtract(totalDescuentos);
			getTxtVentasNetas().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalFinalSinIVA2SinDsctosSinDev.doubleValue())));
			
			getTxtTotalPagarFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalFinalConIVA.doubleValue())));
		}
	}

	private void agregarDetalles(int numero,
			ConsultaFacturaVendedor consultaFacturaVendedor) {
		Vector<Object> fila = new Vector<Object>();
		
		descuentoPorcentaje="0";
		
		
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		fila.add(consultaFacturaVendedor.getFechaEmision().toString());
		fila.add((consultaFacturaVendedor.getTransaccion() != null) ? consultaFacturaVendedor.getTransaccion(): consultaFacturaVendedor.getNumeroFactura());
		String observacion = (consultaFacturaVendedor.getObservacion() != null)?consultaFacturaVendedor.getObservacion():"";
		if (!observacion.contains("F/") && !observacion.contains("NV/")) {
			if (consultaFacturaVendedor.getCodigoTransaccion().equalsIgnoreCase("FAC"))
				observacion = "F/ " + observacion;
			else if (consultaFacturaVendedor.getCodigoTransaccion().equalsIgnoreCase("VTA"))
				observacion = "NV/ " + observacion;
		}
		fila.add(observacion);
		fila.add(consultaFacturaVendedor.getVendedor());
		//fila.add("<html><b>" + consultaFacturaVendedor.getTotal().toString() + "</b></html>");
		if (consultaFacturaVendedor.getCodigoTransaccion().equalsIgnoreCase("FAC")|| consultaFacturaVendedor.getCodigoTransaccion().equalsIgnoreCase("VTA")) {
				totalVentasBrutas = totalVentasBrutas.add(consultaFacturaVendedor.getTotalBrutas());
				fila.add(consultaFacturaVendedor.getTotalBrutas().toString());				
				///////////////////////
				BigDecimal porcentaje;			
				if(consultaFacturaVendedor.getDescuento().equals(BigDecimal.ZERO) || consultaFacturaVendedor.getDescuento()==null)
				{
					fila.add("0%");
				}
				else {
					Double por=consultaFacturaVendedor.getDescuento().doubleValue();					
					por=por*100;
					por=por/consultaFacturaVendedor.getTotalBrutas().doubleValue();
					por=Utilitarios.redondeoValor(por);					
					fila.add(por.toString()+"%");
				}
				
				/////////////////////////
				totalDescuentos = totalDescuentos.add(consultaFacturaVendedor.getDescuento());
				fila.add(consultaFacturaVendedor.getDescuento().toString());
				//////////////////////////////
				BigDecimal totalFinalSinIVAtemp= BigDecimal.ZERO;
				totalFinalSinIVAtemp=consultaFacturaVendedor.getTotalBrutas().subtract(consultaFacturaVendedor.getDescuento());
				fila.add(totalFinalSinIVAtemp.toString());			
				totalFinalSinIVA = totalFinalSinIVA.add(totalFinalSinIVAtemp);
				//////////////
				BigDecimal totalIVAtemp= BigDecimal.ZERO;
				totalIVAtemp = consultaFacturaVendedor.getValorIva();
				fila.add(totalIVAtemp.toString());
				totalIVA = totalIVA.add(totalFinalSinIVAtemp);
				/*BigDecimal totalIVAtemp= BigDecimal.ZERO;
				totalIVAtemp = (totalFinalSinIVAtemp.multiply(new BigDecimal("12"))).divide(new BigDecimal("100"));
				fila.add(totalIVAtemp.toString());
				totalIVA = totalIVA.add(totalFinalSinIVAtemp);*/
				//////////////////
				BigDecimal totalFinalConIVAtemp= BigDecimal.ZERO;
				totalFinalConIVAtemp=totalFinalSinIVAtemp.add(totalIVAtemp);				
				
				fila.add(formatoDecimal.format(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue()))));
				
				//totalFinalConIVA = totalFinalConIVA.add(totalFinalConIVAtemp);
				totalFinalConIVA = totalFinalConIVA.add(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue())));
				
				
		} else if (consultaFacturaVendedor.getCodigoTransaccion().equalsIgnoreCase("DEV")) {
			
			
			fila.add(consultaFacturaVendedor.getTotalBrutas().toString());
			
///////////////////////
			BigDecimal porcentaje;			
			if(consultaFacturaVendedor.getDescuento().equals(BigDecimal.ZERO) || consultaFacturaVendedor.getDescuento()==null)
			{
				fila.add("0%");
			}
			else {
				Double por=consultaFacturaVendedor.getDescuento().doubleValue();					
				por=por*100;
				por=por/consultaFacturaVendedor.getTotalBrutas().doubleValue();
				por=Utilitarios.redondeoValor(por);					
				fila.add(por.toString()+"%");
			}
			
			totalDescuentos = totalDescuentos.add(consultaFacturaVendedor.getDescuento().negate());
			fila.add(consultaFacturaVendedor.getDescuento().toString());
			//////////////////////////////
			BigDecimal totalFinalSinIVAtemp= BigDecimal.ZERO;
			totalFinalSinIVAtemp=consultaFacturaVendedor.getTotalBrutas().subtract(consultaFacturaVendedor.getDescuento());
			fila.add(totalFinalSinIVAtemp.toString());			
			totalFinalSinIVA = totalFinalSinIVA.add(totalFinalSinIVAtemp);
			//////////////
			BigDecimal totalIVAtemp= BigDecimal.ZERO;
			totalIVAtemp = consultaFacturaVendedor.getValorIva();
			fila.add(totalIVAtemp.toString());
			totalIVA = totalIVA.add(totalFinalSinIVAtemp);
			/*BigDecimal totalIVAtemp= BigDecimal.ZERO;
			totalIVAtemp = (totalFinalSinIVAtemp.multiply(new BigDecimal("12"))).divide(new BigDecimal("100"));
			fila.add(totalIVAtemp.toString());
			totalIVA = totalIVA.add(totalFinalSinIVAtemp);*/
			//////////////////
			BigDecimal totalFinalConIVAtemp= BigDecimal.ZERO;
			totalFinalConIVAtemp=totalFinalSinIVAtemp.add(totalIVAtemp);
			totalFinalConIVA = totalFinalConIVA.subtract(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue())));
			
			fila.add(formatoDecimal.format(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue()))));
			totalDevoluciones = totalDevoluciones.add(new BigDecimal(Utilitarios.redondeoValor(consultaFacturaVendedor.getTotalBrutas().doubleValue())));
			
			//se dijo que no se podia devolver en facturas con promociones o descuentos :S!			
			/*fila.add("0%");
			fila.add("0");
			fila.add(consultaFacturaVendedor.getTotalBrutas().toString());
			BigDecimal totalIVAtemp= BigDecimal.ZERO;
			totalIVAtemp = (consultaFacturaVendedor.getTotalBrutas().multiply(new BigDecimal("12"))).divide(new BigDecimal("100"));
			fila.add(totalIVAtemp.toString());
			BigDecimal totalFinalConIVAtemp= BigDecimal.ZERO;
			totalFinalConIVAtemp=consultaFacturaVendedor.getTotalBrutas().add(totalIVAtemp);
			fila.add(formatoDecimal.format(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue()))));
			totalFinalConIVA = totalFinalConIVA.subtract(new BigDecimal(Utilitarios.redondeoValor(totalFinalConIVAtemp.doubleValue())));
			totalDevoluciones = totalDevoluciones.add(consultaFacturaVendedor.getTotalBrutas());*/
		}
		
		
		
		 
		
		tableModel.addRow(fila);
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		tableModel.addRow(fila);

	}

	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color whiteColor = new Color(255, 255, 255);
		private Color grayColor = new Color(204, 204, 204);
		private Color blackColor = new Color(0, 0, 0);

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setForeground(blackColor);

			if (column == 0)
				setHorizontalAlignment(JLabel.RIGHT);
			if (column == 1)
				setHorizontalAlignment(JLabel.CENTER);
			if (column == 2)
				setHorizontalAlignment(JLabel.CENTER);
			if (column == 3)
				setHorizontalAlignment(JLabel.LEFT);
			if (column == 4)
				setHorizontalAlignment(JLabel.RIGHT);
			if (column == 5)
				setHorizontalAlignment(JLabel.RIGHT);

			if (((String) value).equals("<html><b>T O T A L E S :</b></html>")
					|| ((String) value)
							.equals("<html><b>S A L D O :</b></html>")) {
				c.setBackground(grayColor);
				setHorizontalAlignment(JLabel.RIGHT);
			} else {
				c.setBackground(whiteColor);
			}

			String detalle = (String) table.getValueAt(row, 2);
			if ((column >= 0 && column <= 4)
					&& (detalle.equals("<html><b>T O T A L E S :</b></html>") || detalle
							.equals("<html><b>S A L D O :</b></html>"))) {
				c.setBackground(grayColor);
			}

			return c;
		}
	};

	 

 
 

	private boolean isTotal() {
		return isTotal;
	}

	private void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void duplicate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showFindMode() {
		// TODO Auto-generated method stub

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}
}