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
import javax.swing.table.TableColumnModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.SalarioMinimoVitalData;
import com.spirit.nomina.entity.SalarioMinimoVitalIf;
import com.spirit.nomina.gui.panel.JPSalarioMinimoVital;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class SalarioMinimoVitalModel extends JPSalarioMinimoVital {

	private static final long serialVersionUID = 4073965137513212126L;
	
	private static final int MAX_LONGITUD_VALOR = 9;
	
	private static final int COLUMNA_VALOR = 0;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private ArrayList<SalarioMinimoVitalIf> SmvCollection = new ArrayList<SalarioMinimoVitalIf>();
	private SalarioMinimoVitalIf smvIf;
	private int filaSeleccionado;
	
	public SalarioMinimoVitalModel() {	
		showSaveMode();
		initKeyListeners();
		iniciarComponentes();
		setSorterTable(getTblSmv());
		//anchoColumnasTabla();
		initListeners();
		
		new HotKeyComponent(this);
	}
	
	private void initListeners(){
		getTblSmv().addMouseListener(oMouseAdapterTblImpuestoRenta);
		getTblSmv().addKeyListener(oKeyAdapterTblImpuestoRenta);
	}
	
	private void initKeyListeners() {
		
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
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
		
		TableColumnModel modeloColumna = getTblSmv().getColumnModel(); 
		modeloColumna.getColumn(COLUMNA_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
	}
	
	/*private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblSmv().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblSmv().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblSmv().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(170);
		anchoColumna = getTblSmv().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblSmv().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(190);
		anchoColumna = getTblSmv().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(190);
	}*/
	
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
			smvIf = SmvCollection.get(filaSeleccionado);
			
			getTxtValor().setText(formatoDecimal.format(smvIf.getValor()));
			
			getCmbFechaInicio().setDate(smvIf.getFechaInicio());
			getCmbFechaFin().setDate(smvIf.getFechaFin());
			
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
			
			Collection<SalarioMinimoVitalIf> smvs = SessionServiceLocator.getSalarioMinimoVitalSessionService().getSalarioMinimoVitalList();
			
			Collections.sort((ArrayList<SalarioMinimoVitalIf>)smvs, comparadorImpuestos);
			
			DefaultTableModel tableModel = (DefaultTableModel) getTblSmv().getModel();
			for (SalarioMinimoVitalIf smv : smvs) {
				
				Vector<Object> fila = new Vector<Object>();
				SmvCollection.add(smv);
				
				agregarFilaTabla(smv, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblSmv(), smvs, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<SalarioMinimoVitalIf> comparadorImpuestos =  new Comparator<SalarioMinimoVitalIf>(){

		public int compare(SalarioMinimoVitalIf o1, SalarioMinimoVitalIf o2) {
			return o1.getFechaInicio().compareTo(o2.getFechaInicio());
		}
		
	};
	
	private void agregarFilaTabla(SalarioMinimoVitalIf smv, Vector<Object> fila){
		
		fila.add(smv.getValor());
		fila.add(Utilitarios.getFechaUppercase(smv.getFechaInicio()));
		
		fila.add(smv.getFechaFin()!=null?
				Utilitarios.getFechaUppercase(smv.getFechaFin()) : "" );
		
	}

	public void save() {
				
		try {
			if (validateFields()) {
				SalarioMinimoVitalIf data = new SalarioMinimoVitalData();

				creacionImpuestoRentaEmpleadoIf(data);
							
				SessionServiceLocator.getSalarioMinimoVitalSessionService().addSalarioMinimoVital(data);
				SpiritAlert.createAlert("Salario Minimo Vital guardado con éxito",SpiritAlert.INFORMATION);
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
				
				/*String valor = getTxtCodigo().getText();
				double dValor = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
				impuestoRentaIf.setFraccionBasica(new BigDecimal(dValor));
				
				valor = getTxtValor().getText();
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
				
				creacionImpuestoRentaEmpleadoIf(smvIf);
				
				SessionServiceLocator.getSalarioMinimoVitalSessionService().saveSalarioMinimoVital(smvIf);
				
				SpiritAlert.createAlert("Salario Minimo Vital actualizado con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}
	
	private void creacionImpuestoRentaEmpleadoIf(SalarioMinimoVitalIf data) {
		String valor = getTxtValor().getText();
		Double valorD = Double.valueOf(Utilitarios.removeDecimalFormat(valor));
		data.setValor(new BigDecimal(valorD));
		
		Date fechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
		data.setFechaInicio(fechaInicio);
		
		Date fechaFin = null;
		if ( getCmbFechaFin().getDate() != null )
			fechaFin = new Date(getCmbFechaFin().getDate().getTime());
		data.setFechaFin(fechaFin);
	}

	public void delete() {
		try {
			SessionServiceLocator.getSalarioMinimoVitalSessionService().deleteSalarioMinimoVital(smvIf.getId());
			SpiritAlert.createAlert("Salario Minimo Vital eliminado con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro, puede tener datos referenciados!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		smvIf = null;
		
		SmvCollection = null;
		SmvCollection = new ArrayList<SalarioMinimoVitalIf>();
		
		getTxtValor().setText("");
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
				
		//Vacio la tabla
		limpiarTabla(getTblSmv());
	}
	
	public boolean validateFields() {
		
		if ( "".equals(getTxtValor().getText()) ){
			SpiritAlert.createAlert("Ingresar un valor !!", SpiritAlert.WARNING);
			return false;
		}
		if ( getCmbFechaInicio().getDate() == null  ){
			SpiritAlert.createAlert("Seleccionar una fecha de inicio !!", SpiritAlert.WARNING);
			return false;
		}
		
		String valor = getTxtValor().getText();
		valor = Utilitarios.removeDecimalFormat(valor);
		double valorD = Double.valueOf(valor);
		
		Date valorFechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
		
		Date valorFechaFin = null;
		if (getCmbFechaFin().getDate()!= null )
			valorFechaFin = new Date(getCmbFechaFin().getDate().getTime());
		
		boolean codigoRepetido = false;
		
		try {
			Collection<SalarioMinimoVitalIf> smvs = SessionServiceLocator.getSalarioMinimoVitalSessionService().getSalarioMinimoVitalList();
			
			for(SalarioMinimoVitalIf ir : smvs) {
				
				double valorExistente = Utilitarios.redondeoValor(ir.getValor().doubleValue()) ;
				Date fechaInicio = new Date(ir.getFechaInicio().getTime());
				Date fechaFin = null;
				if ( ir.getFechaFin() != null )
					fechaFin = new Date(ir.getFechaFin().getTime());
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if ( valorD == valorExistente  ){
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
					if ( valorD == valorExistente ) {
						boolean mismoId = smvIf.getId().equals(ir.getId());
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
							if ( smvIf.getId().equals(ir.getId()) ){
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
