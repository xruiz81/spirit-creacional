package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JPActualizarSaldosFacturas;
import com.spirit.facturacion.session.FacturaSessionService;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Utilitarios;

public class ActualizarSaldosFacturasModel extends JPActualizarSaldosFacturas {
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	ArrayList facturasList = new ArrayList();
	
	public ActualizarSaldosFacturasModel(){
		anchoColumnasTabla();
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnProcesar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				procesarActualizacionSaldosFacturas();
			}
		});
	}

	private void anchoColumnasTabla() {
		getTblActualizarSaldosFacturas().getTableHeader().setReorderingAllowed(false);
		getTblActualizarSaldosFacturas().setCellSelectionEnabled(true);
		getTblActualizarSaldosFacturas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblActualizarSaldosFacturas().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(60);
	}
	
	public void save() {
		showSaveMode();
	}

	public void delete() {
		// TODO Auto-generated method stub		
	}

	public void update() {
		// TODO Auto-generated method stub		
	}

	public void clean() {
		facturasList.clear();
		DefaultTableModel model = (DefaultTableModel) getTblActualizarSaldosFacturas().getModel();
		for(int i= this.getTblActualizarSaldosFacturas().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
	}
	
	private void cargarTabla() {
		setCursor(SpiritCursor.hourglassCursor);
		try {
			facturasList = new ArrayList();
			Map parameterMap = new HashMap();
			parameterMap.put("estado", "X");
			facturasList = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaParaActualizarSaldosByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa());
			Iterator facturasIterator = facturasList.iterator();
			while (facturasIterator.hasNext()) {
				Object[] facturaParaActualizar = (Object[]) facturasIterator.next();
				FacturaIf factura = (FacturaIf) facturaParaActualizar[0];
				tableModel = (DefaultTableModel) getTblActualizarSaldosFacturas().getModel();
				Vector<Object> fila = new Vector<Object>();
				agregarColumnasTabla(factura, fila);
				tableModel.addRow(fila);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar los datos de la tabla", SpiritAlert.ERROR);
		}
		setCursor(SpiritCursor.normalCursor);
	}
	
	private void agregarColumnasTabla(FacturaIf factura, Vector<Object> fila){
		fila.add(String.valueOf(getTblActualizarSaldosFacturas().getRowCount() + 1));
		fila.add(factura.getPreimpresoNumero());
		String fecha = factura.getFechaFactura().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaFactura = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		fila.add(fechaFactura);
		fila.add(factura.getObservacion());
		double valor = factura.getValor().doubleValue();
		double descuento = factura.getDescuento().doubleValue();
		double descuentoGlobal = factura.getDescuentoGlobal().doubleValue();
		double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
		double comision = ((valor - descuento - descuentoGlobal) * porcentajeComision) / 100D; 
		fila.add(formatoDecimal.format(valor - descuento - descuentoGlobal + comision));
		fila.add(formatoDecimal.format(factura.getIva().doubleValue()));
		fila.add(formatoDecimal.format(factura.getValor().doubleValue() + factura.getIva().doubleValue()));
		fila.add("");
	}
	
	private void procesarActualizacionSaldosFacturas() {
		int i = 0;
		Iterator facturasIterator = facturasList.iterator();
		try {
			while (facturasIterator.hasNext()) {
				Object[] facturaParaActualizar = (Object[]) facturasIterator.next();
				FacturaIf factura = (FacturaIf) facturaParaActualizar[0];
				CarteraIf cartera = (CarteraIf) facturaParaActualizar[1];
				getTblActualizarSaldosFacturas().setValueAt("PROCESANDO...", i, 7);
				SessionServiceLocator.getFacturaSessionService().procesarActualizacionSaldosFacturas(factura, cartera);
				getTblActualizarSaldosFacturas().setValueAt("FINALIZADO", i, 7);
				i++;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
		cargarTabla();
	}
	
	 
}
