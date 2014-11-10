package com.spirit.nomina.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.ImpuestoRentaPersNatData;
import com.spirit.nomina.entity.ImpuestoRentaPersNatIf;
import com.spirit.nomina.gui.panel.JPImpuestoRentaEmpleado;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ImpuestoRentaEmpleadoModel extends JPImpuestoRentaEmpleado {

	private static final long serialVersionUID = 4073965137513212126L;
	
	private static final int MAX_LONGITUD_VALOR_FRACCION_BASICO = 9;
	private static final int MAX_LONGITUD_VALOR_EXCESO_HASTA = 9;
	private static final int MAX_LONGITUD_VALOR_IMPUESTO_FRACCION_BASICA = 9;
	private static final int MAX_LONGITUD_VALOR_PORCENTAJE_IMPUESTO = 9;
	
	private static final int COLUMNA_FRACCION_BASICA = 0;
	private static final int COLUMNA_EXCESO_HASTA = 1;
	private static final int COLUMNA_IMPUESTO_FRACCION_BASICA = 2;
	private static final int COLUMNA_PORCENTAJE_IMPUESTO = 3;
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private ArrayList<ImpuestoRentaPersNatIf> impuestoRentaCollection = new ArrayList<ImpuestoRentaPersNatIf>();
	private ImpuestoRentaPersNatIf impuestoRentaIf;
	private int filaSeleccionado;
	
	public ImpuestoRentaEmpleadoModel() {	
		showSaveMode();
		initKeyListeners();
		iniciarComponentes();
		setSorterTable(getTblImpuestoRenta());
		anchoColumnasTabla();
		initListeners();
		
		new HotKeyComponent(this);
	}
	
	private void initListeners(){
		getTblImpuestoRenta().addMouseListener(oMouseAdapterTblImpuestoRenta);
		getTblImpuestoRenta().addKeyListener(oKeyAdapterTblImpuestoRenta);
	}
	
	private void initKeyListeners() {
		
		getTxtFraccionBasica().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR_FRACCION_BASICO));
		getTxtFraccionBasica().addKeyListener(new NumberTextFieldDecimal());
		getTxtExcesoHasta().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR_EXCESO_HASTA));
		getTxtExcesoHasta().addKeyListener(new NumberTextFieldDecimal());
		getTxtImpuestoFraccionBasica().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR_IMPUESTO_FRACCION_BASICA));
		getTxtImpuestoFraccionBasica().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR_PORCENTAJE_IMPUESTO));
		getTxtPorcentajeImpuesto().addKeyListener(new NumberTextFieldDecimal());
		
	}
	
	private void iniciarComponentes(){
		//COMBOS DE FECHA
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		TableColumnModel modeloColumna = getTblImpuestoRenta().getColumnModel(); 
		modeloColumna.getColumn(COLUMNA_FRACCION_BASICA).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		modeloColumna.getColumn(COLUMNA_EXCESO_HASTA).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		modeloColumna.getColumn(COLUMNA_IMPUESTO_FRACCION_BASICA).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		modeloColumna.getColumn(COLUMNA_PORCENTAJE_IMPUESTO).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(170);
		anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblImpuestoRenta().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(190);
	}
	
	MouseListener oMouseAdapterTblImpuestoRenta = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblImpuestoRenta = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		JTable tabla = (JTable) evt.getSource(); 
		if ( tabla.getSelectedRow() != -1 ) {
			int selectedRow = tabla.getSelectedRow();
			filaSeleccionado = tabla.convertRowIndexToModel(selectedRow); 
			impuestoRentaIf = impuestoRentaCollection.get(filaSeleccionado);
			
			getTxtFraccionBasica().setText(formatoDecimal.format(impuestoRentaIf.getFraccionBasica()));
			getTxtExcesoHasta().setText(formatoDecimal.format(impuestoRentaIf.getExcesoHasta()));
			getTxtImpuestoFraccionBasica().setText(formatoDecimal.format(impuestoRentaIf.getImpFraccionBasica()));
			getTxtPorcentajeImpuesto().setText(formatoDecimal.format(impuestoRentaIf.getPorcentajeImpFraccionBasica()));
			
			getCmbFechaInicio().setDate(impuestoRentaIf.getFechaInicio());
			getCmbFechaFin().setDate(impuestoRentaIf.getFechaFin());
			
			showUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
	}
	
	public void refresh(){
		clean();
		cargarTabla();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		try {
			
			Collection<ImpuestoRentaPersNatIf> impuestos = SessionServiceLocator.getImpuestoRentaPersNatSessionService().getImpuestoRentaPersNatList();
			
			Collections.sort((ArrayList<ImpuestoRentaPersNatIf>)impuestos, comparadorImpuestos);
			
			DefaultTableModel tableModel = (DefaultTableModel) getTblImpuestoRenta().getModel();
			for (ImpuestoRentaPersNatIf ir : impuestos) {
				
				Vector<Object> fila = new Vector<Object>();
				impuestoRentaCollection.add(ir);
				
				agregarFilaTabla(ir, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblImpuestoRenta(), impuestos, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<ImpuestoRentaPersNatIf> comparadorImpuestos =  new Comparator<ImpuestoRentaPersNatIf>(){

		public int compare(ImpuestoRentaPersNatIf o1, ImpuestoRentaPersNatIf o2) {
			return o1.getFraccionBasica().compareTo(o2.getFraccionBasica());
		}
		
	};
	
	private void agregarFilaTabla(ImpuestoRentaPersNatIf ir, Vector<Object> fila){
		
		fila.add(ir.getFraccionBasica());
		fila.add(ir.getExcesoHasta());
		fila.add(ir.getImpFraccionBasica());
		fila.add(ir.getPorcentajeImpFraccionBasica());
		fila.add(Utilitarios.getFechaCortaUppercase(ir.getFechaInicio()));
		
		fila.add(ir.getFechaFin()!=null?
				Utilitarios.getFechaCortaUppercase(ir.getFechaFin()) : "" );
		
	}

	public void save() {
				
		try {
			if (validateFields()) {
				ImpuestoRentaPersNatIf data = new ImpuestoRentaPersNatData();

				creacionImpuestoRentaEmpleadoIf(data);
							
				SessionServiceLocator.getImpuestoRentaPersNatSessionService().addImpuestoRentaPersNat(data);
				SpiritAlert.createAlert("Rubro guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}/* catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		}*/catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		
		
		try {
			if (validateFields()) {
				
				/*String valor = getTxtFraccionBasica().getText();
				double dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
				impuestoRentaIf.setFraccionBasica(new BigDecimal(dValor));
				
				valor = getTxtExcesoHasta().getText();
				dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
				impuestoRentaIf.setFraccionBasica(new BigDecimal(dValor));
				
				valor = getTxtImpuestoFraccionBasica().getText();
				dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
				impuestoRentaIf.setFraccionBasica(new BigDecimal(dValor));
				
				valor = getTxtPorcentajeImpuesto().getText();
				dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
				impuestoRentaIf.setFraccionBasica(new BigDecimal(dValor));
				
				Date fechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
				impuestoRentaIf.setFechaInicio(fechaInicio);
				
				Date fechaFin = null;
				if ( getCmbFechaFin().getDate() != null )
					fechaFin = new Date(getCmbFechaFin().getDate().getTime());
				impuestoRentaIf.setFechaFin(fechaFin);*/
				
				creacionImpuestoRentaEmpleadoIf(impuestoRentaIf);
				
				SessionServiceLocator.getImpuestoRentaPersNatSessionService().saveImpuestoRentaPersNat(impuestoRentaIf);
				
				SpiritAlert.createAlert("Rubro actualizado con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}
	
	private void creacionImpuestoRentaEmpleadoIf(ImpuestoRentaPersNatIf data) {
		String valor = getTxtFraccionBasica().getText();
		double dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
		data.setFraccionBasica(new BigDecimal(dValor));
		
		valor = getTxtExcesoHasta().getText();
		dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
		data.setExcesoHasta(new BigDecimal(dValor));
		
		valor = getTxtImpuestoFraccionBasica().getText();
		dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
		data.setImpFraccionBasica(new BigDecimal(dValor));
		
		valor = getTxtPorcentajeImpuesto().getText();
		dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
		data.setPorcentajeImpFraccionBasica(new BigDecimal(dValor));
		
		Date fechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
		data.setFechaInicio(fechaInicio);
		
		Date fechaFin = null;
		if ( getCmbFechaFin().getDate() != null )
			fechaFin = new Date(getCmbFechaFin().getDate().getTime());
		data.setFechaFin(fechaFin);
	}

	public void delete() {
		try {
			SessionServiceLocator.getImpuestoRentaPersNatSessionService().deleteImpuestoRentaPersNat(impuestoRentaIf.getId());
			SpiritAlert.createAlert("Rubro eliminado con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro, puede tener datos referenciados!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		impuestoRentaIf = null;
		
		impuestoRentaCollection = null;
		impuestoRentaCollection = new ArrayList<ImpuestoRentaPersNatIf>();
		
		getTxtFraccionBasica().setText("");
		getTxtExcesoHasta().setText("");
		getTxtImpuestoFraccionBasica().setText("");
		getTxtPorcentajeImpuesto().setText("");
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
				
		//Vacio la tabla
		limpiarTabla(getTblImpuestoRenta());
	}
	
	public boolean validateFields() {
		
		if ( "".equals(getTxtFraccionBasica().getText()) ){
			SpiritAlert.createAlert("Ingresar un valor para Fracci\u00f3n B\u00e1sica !!", SpiritAlert.WARNING);
			return false;
		}
		if ( "".equals(getTxtExcesoHasta().getText()) ){
			SpiritAlert.createAlert("Ingresar un valor para Exceso Hasta !!", SpiritAlert.WARNING);
			return false;
		}
		if ( "".equals(getTxtImpuestoFraccionBasica().getText()) ){
			SpiritAlert.createAlert("Ingresar un valor para Impuesto Fracci\u00f3n B\u00e1sica !!", SpiritAlert.WARNING);
			return false;
		}
		if ( "".equals(getTxtPorcentajeImpuesto().getText()) ){
			SpiritAlert.createAlert("Ingresar un valor para Porcentaje de Impuesto !!", SpiritAlert.WARNING);
			return false;
		}
		if ( getCmbFechaInicio().getDate() == null  ){
			SpiritAlert.createAlert("Seleccionar una fecha de inicio !!", SpiritAlert.WARNING);
			return false;
		}
		
		
		String valor = getTxtFraccionBasica().getText();
		String valorFraccionBasica = Utilitarios.removeDecimalFormat(valor);
		double valorFraccionBasicaD = Double.valueOf(valorFraccionBasica);
		
		valor = getTxtExcesoHasta().getText();
		String valorExcesoHasta = Utilitarios.removeDecimalFormat(valor);
		double valorExcesoHastaD = Double.valueOf(valorExcesoHasta);
		
		valor = getTxtImpuestoFraccionBasica().getText();
		String valorImpuesto = Utilitarios.removeDecimalFormat(valor);
		double valorImpuestoD = Double.valueOf(valorImpuesto);
		
		valor = getTxtPorcentajeImpuesto().getText();
		String valorPorcentajeImpuesto = Utilitarios.removeDecimalFormat(valor);
		double valorPorcentajeImpuestoD = Double.valueOf(valorPorcentajeImpuesto);
		
		Date valorFechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
		
		Date valorFechaFin = null;
		if (getCmbFechaFin().getDate()!= null )
			valorFechaFin = new Date(getCmbFechaFin().getDate().getTime());
		
		Collection<ImpuestoRentaPersNatIf> impuestos = null;
		boolean codigoRepetido = false;
		
		try {
			impuestos = SessionServiceLocator.getImpuestoRentaPersNatSessionService().getImpuestoRentaPersNatList();
			
			for(ImpuestoRentaPersNatIf ir : impuestos) {
				
				double fraccionBasica = Utilitarios.redondeoValor(ir.getFraccionBasica().doubleValue() );
				double excesoHasta = Utilitarios.redondeoValor(ir.getExcesoHasta().doubleValue()) ;
				double impFraccionBasica = Utilitarios.redondeoValor(ir.getImpFraccionBasica().doubleValue()) ;
				double porcentajeImp = Utilitarios.redondeoValor(ir.getPorcentajeImpFraccionBasica().doubleValue()) ;
				Date fechaInicio = new Date(ir.getFechaInicio().getTime());
				Date fechaFin = null;
				if ( ir.getFechaFin() != null )
					fechaFin = new Date(ir.getFechaFin().getTime());
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (	
							valorFraccionBasicaD == fraccionBasica &&
							valorExcesoHastaD == excesoHasta &&
							valorImpuestoD == impFraccionBasica &&
							valorPorcentajeImpuestoD == porcentajeImp
					   ){
						if ( valorFechaInicio.equals(fechaInicio) ){
							if ( (fechaFin != null && fechaFin.equals(valorFechaFin)) ||
								 (fechaFin == null && valorFechaFin == null) ){
								codigoRepetido = true;
								break;
							}
						}
						if ( fechaFin == null && valorFechaFin == null ){
							if ( fechaInicio.compareTo(valorFechaInicio) < 0 ){
								SpiritAlert.createAlert("Valores ya existen con Fecha de Inicio menor y sin Fecha Fin !!", SpiritAlert.WARNING);
								return false;
							}
						}
						codigoRepetido = true;
						break;
					}
				} else if (this.getMode() == SpiritMode.UPDATE_MODE){
					if (
							valorFraccionBasicaD == fraccionBasica &&
							valorExcesoHastaD == excesoHasta &&
							valorImpuestoD == impFraccionBasica &&
							valorPorcentajeImpuestoD == porcentajeImp 
					   ) {
						boolean mismoId = impuestoRentaIf.getId().equals(ir.getId());
						if ( valorFechaInicio.equals(fechaInicio) && !mismoId ){
							if ( (fechaFin != null && fechaFin.equals(valorFechaFin)) ||
								 (fechaFin == null && valorFechaFin == null) ){
								codigoRepetido = true;
								break;
							}
						}
						if ( 
							 ( (fechaFin != null && fechaFin.equals(valorFechaFin)) ||
							 (fechaFin == null && valorFechaFin == null ) )
							 && !mismoId ){
							if ( impuestoRentaIf.getId().equals(ir.getId()) ){
								codigoRepetido = true;
								break;
							}
						}
						if ( fechaFin == null && valorFechaFin == null && !mismoId ){
							if ( fechaInicio.compareTo(valorFechaInicio) < 0 ){
								SpiritAlert.createAlert("Valores ya existen con Fecha de Inicio menor y sin Fecha Fin !!", SpiritAlert.WARNING);
								return false;
							}
						}
						/*if ( impuestoRentaIf.getId().equals(ir.getId()) ){
							codigoRepetido = true;
							break;
						}*/
					}
				}
			}
			
			if ( codigoRepetido ){
				SpiritAlert.createAlert("Informaci\u00f3n para impuesto a la Renta ya existe !!", SpiritAlert.WARNING);
				return false;
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void showUpdateMode() {
		setUpdateMode();		
	}
	
}
