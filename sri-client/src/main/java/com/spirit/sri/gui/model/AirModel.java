package com.spirit.sri.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.entity.SriAirData;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.gui.panel.JPAir;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class AirModel extends JPAir{

	private static final long serialVersionUID = 1L;

	private final int MAX_LONGITUD_CODIGO = 5;
	private final int MAX_LONGITUD_CONCEPTO = 150;
	private final int MAX_LONGITUD_PORCENTAJE = 5;

	private int filaSeleccionada = -1;
	private SriAirIf airIf = null;
	private ArrayList<SriAirIf> airVector = new ArrayList<SriAirIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("###.##");

	public AirModel(){
		initListeners();
		iniciarComponentes();
		anchoColumnasTabla();

		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblAir());
	}


	private void initListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtConcepto().addKeyListener(new TextChecker(MAX_LONGITUD_CONCEPTO));

		getTxtPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentaje().addKeyListener(new NumberTextFieldDecimal());

		getTblAir().addMouseListener(oMouseAdapterTblAir);
		getTblAir().addKeyListener(oKeyAdapterTblAir);
	}

	private void iniciarComponentes(){
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);

		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblAir().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna.setMaxWidth(60);
		anchoColumna = getTblAir().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(850);
		anchoColumna = getTblAir().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblAir().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblAir().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(130);
	}

	MouseListener oMouseAdapterTblAir = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblAir = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblAir().getSelectedRow() != -1) {
			int selectedRow = getTblAir().getSelectedRow();
			filaSeleccionada =  getTblAir().convertRowIndexToModel(selectedRow); 
			airIf = airVector.get(filaSeleccionada);

			getTxtCodigo().setText(airIf.getCodigo());
			getTxtConcepto().setText(airIf.getConcepto());

			BigDecimal porcentaje = airIf.getPorcentaje();
			getTxtPorcentaje().setText(porcentaje!=null?formatoDecimal.format(porcentaje.doubleValue()):"");

			Date fechaInicio = airIf.getFechaInicio();;
			getCmbFechaInicio().setDate(fechaInicio!=null?fechaInicio:new GregorianCalendar().getTime());
			Date fechaFin = airIf.getFechaFin();
			getCmbFechaFin().setDate(fechaFin!=null?fechaFin:null);

			showUpdateMode();
		}
	}

	@Override
	public void clean() {
		getTxtCodigo().setText("");
		getTxtConcepto().setText("");
		getTxtPorcentaje().setText("");
		Date fechaHoy = new GregorianCalendar().getTime();
		getCmbFechaInicio().setDate(fechaHoy);
		getCmbFechaFin().setDate(null);

		airVector = null;
		airVector = new ArrayList<SriAirIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblAir());
	}

	@Override
	public void delete() {
		try {
			SriAirIf air = airVector.get(filaSeleccionada);
			SessionServiceLocator.getAirSessionService().deleteSriAir(air.getId());
			SpiritAlert.createAlert("Retenci\u00f3n eliminada con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	@Override
	public void save() {
		try {
			if (validateFields()) {
				SriAirData data = new SriAirData();

				data.setCodigo(getTxtCodigo().getText());
				data.setConcepto(getTxtConcepto().getText());

				BigDecimal porcentaje = new BigDecimal(getTxtPorcentaje().getText());
				data.setPorcentaje(porcentaje);

				Date fecha = getCmbFechaInicio().getDate();
				data.setFechaInicio(new java.sql.Date(fecha.getTime()));
				fecha = getCmbFechaFin().getDate();
				data.setFechaFin(fecha!=null?new java.sql.Date(fecha.getTime()):null);

				SessionServiceLocator.getAirSessionService().addSriAir(data);
				SpiritAlert.createAlert("Retenci\u00f3n guardada con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomaci\u00f3n!", SpiritAlert.ERROR);
		}
	}

	@Override
	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblAir().getModel();
			Collection<SriAirIf> airExistentes = SessionServiceLocator.getAirSessionService().getSriAirList(); 
			for ( SriAirIf air : airExistentes ){
				Vector<String> fila = new Vector<String>();
				airVector.add(air);
				agregarColumnasTabla(air, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblAir(), airExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	private void agregarColumnasTabla(SriAirIf air, Vector<String> fila){
		String codigo = air.getCodigo();
		fila.add(codigo!=null?codigo:"");
		String concepto = air.getConcepto();
		fila.add(concepto!=null?concepto:"");
		BigDecimal porcentaje = air.getPorcentaje(); 
		fila.add(porcentaje!=null?formatoDecimal.format(porcentaje.doubleValue()):"");
		Date fechaInicio = air.getFechaInicio();
		fila.add(fechaInicio!=null?Utilitarios.getFechaUppercase(fechaInicio):"");
		Date fechaFin = air.getFechaFin();
		fila.add(fechaFin!=null?Utilitarios.getFechaUppercase(fechaFin):"");
	}

	@Override
	public void update() {
		try {	
			if (validateFields()) {
				airIf.setCodigo(getTxtCodigo().getText());
				airIf.setConcepto(getTxtConcepto().getText());
				
				BigDecimal porcentaje = new BigDecimal(getTxtPorcentaje().getText());
				airIf.setPorcentaje(porcentaje);
				
				Date fecha = getCmbFechaInicio().getDate();
				airIf.setFechaInicio(new java.sql.Date(fecha.getTime()));
				fecha = getCmbFechaFin().getDate();
				airIf.setFechaFin(fecha!=null?new java.sql.Date(fecha.getTime()):null);
				
				SessionServiceLocator.getAirSessionService().saveSriAir(airIf);
				SpiritAlert.createAlert("Retenci\u00f3n actualizada con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",
					SpiritAlert.ERROR);
		}	
	}

	@Override
	public boolean validateFields() {

		String strCodigo = getTxtCodigo().getText();
		String strConcepto = getTxtConcepto().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ( strConcepto == null || "".equals(strConcepto) ){
			SpiritAlert.createAlert("Debe ingresar un concepto !!",
					SpiritAlert.WARNING);
			getTxtConcepto().grabFocus();
			return false;
		}
		if ( getCmbFechaInicio().getDate()==null ){
			SpiritAlert.createAlert("Debe ingresar una fecha v\u00e1lida de inicio!!",
					SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		
		try {
			Collection<SriAirIf> airExistentes = SessionServiceLocator.getAirSessionService().getSriAirList();
		
			for (SriAirIf air : airExistentes){
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if ( strCodigo.equals(air.getCodigo()) 
							&& strConcepto.equals(air.getConcepto())
							&& getCmbFechaInicio().getDate() == air.getFechaInicio()
							){
						SpiritAlert.createAlert("La Retenci\u00f3n ingresada est\u00e1 duplicada !!", SpiritAlert.WARNING);
						return false;
					}
						
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if ( strCodigo.equals(air.getCodigo()) 
							&& strConcepto.equals(air.getConcepto())
							&& getCmbFechaInicio().getDate() == air.getFechaInicio()
							){
						if (air.getId() != airIf.getId()){
							SpiritAlert.createAlert("La retenci\u00f3n ingresada est\u00e1 duplicada !!", SpiritAlert.WARNING);
							return false;
						}
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los registros existentes", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}

}
