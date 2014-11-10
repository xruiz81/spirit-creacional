package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JPReversacionFacturasReembolso;
import com.spirit.facturacion.session.FacturaSessionService;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Utilitarios;

public class ReversacionFacturasReembolsoModel extends JPReversacionFacturasReembolso {
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	TipoDocumentoIf tipoDocumentoFacturaReembolso = null;
	ArrayList facturasReembolsoParaReversarList = new ArrayList();
	
	public ReversacionFacturasReembolsoModel(){
		anchoColumnasTabla();
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnProcesar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				procesarReversacionFacturasReembolso();
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTabla();
			}
		});
	}

	private void anchoColumnasTabla() {
		getTblReversacionFacturasReembolso().getTableHeader().setReorderingAllowed(false);
		getTblReversacionFacturasReembolso().setCellSelectionEnabled(true);
		getTblReversacionFacturasReembolso().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionFacturasReembolso().getColumnModel().getColumn(7);
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
		facturasReembolsoParaReversarList.clear();
		DefaultTableModel model = (DefaultTableModel) getTblReversacionFacturasReembolso().getModel();
		for(int i= this.getTblReversacionFacturasReembolso().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	private void cargarTabla() {
		setCursor(SpiritCursor.hourglassCursor);
		try {
			facturasReembolsoParaReversarList = new ArrayList();
			Map parameterMap = new HashMap();
			java.util.Date desde = (Date) getCmbDesde().getDate();
			java.sql.Date fechaDesde = null;
			java.util.Date hasta = (Date) getCmbHasta().getDate();
			java.sql.Date fechaHasta = null;
			
			if (desde != null)
				fechaDesde = new java.sql.Date(desde.getYear(),desde.getMonth(),desde.getDate());
			if (hasta != null)
				fechaHasta = new java.sql.Date(hasta.getYear(),hasta.getMonth(),hasta.getDate());
			
			facturasReembolsoParaReversarList = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaReembolsoParaReversarByQueryByEmpresaIdByFechaDesdeAndFechaHasta(parameterMap, Parametros.getIdEmpresa(), fechaDesde, fechaHasta);
			Iterator facturasReembolsoParaReversarIterator = facturasReembolsoParaReversarList.iterator();
			while (facturasReembolsoParaReversarIterator.hasNext()) {
				Object[] facturaReembolsoParaReversar = (Object[]) facturasReembolsoParaReversarIterator.next();
				FacturaIf factura = (FacturaIf) facturaReembolsoParaReversar[0];
				tableModel = (DefaultTableModel) getTblReversacionFacturasReembolso().getModel();
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
		fila.add(String.valueOf(getTblReversacionFacturasReembolso().getRowCount() + 1));
		fila.add(factura.getPreimpresoNumero());
		String fecha = factura.getFechaFactura().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaFactura = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		fila.add(fechaFactura);
		fila.add(factura.getObservacion());
		fila.add(formatoDecimal.format(factura.getValor().doubleValue()));
		fila.add(formatoDecimal.format(factura.getIva().doubleValue()));
		fila.add(formatoDecimal.format(factura.getValor().doubleValue() + factura.getIva().doubleValue()));
		fila.add("");
	}
	
	private void procesarReversacionFacturasReembolso() {
		int i = 0;
		Iterator facturasReembolsoParaReversarIterator = facturasReembolsoParaReversarList.iterator();
		try {
			DocumentoIf documentoFacturaReembolso = null;
			Iterator documentoIt = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("FACR").iterator();
			if (documentoIt.hasNext())
				documentoFacturaReembolso = (DocumentoIf) documentoIt.next();
			
			while (facturasReembolsoParaReversarIterator.hasNext()) {
				Object[] facturaReembolsoParaReversar = (Object[]) facturasReembolsoParaReversarIterator.next();
				FacturaIf factura = (FacturaIf) facturaReembolsoParaReversar[0];
				AsientoIf asiento = (AsientoIf) facturaReembolsoParaReversar[1];
				getTblReversacionFacturasReembolso().setValueAt("PROCESANDO...", i, 7);
				SessionServiceLocator.getFacturaSessionService().procesarReversacionFacturasReembolso(factura, asiento, documentoFacturaReembolso, (UsuarioIf) Parametros.getUsuarioIf());
				getTblReversacionFacturasReembolso().setValueAt("FINALIZADO", i, 7);
				i++;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Saldo de cuenta contable se vuelve negativo./n" + 
					"No se ha podido reversar el asiento asociado a la factura de reembolso!", SpiritAlert.ERROR);
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
