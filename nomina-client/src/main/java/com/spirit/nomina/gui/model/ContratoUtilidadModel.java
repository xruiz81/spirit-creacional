package com.spirit.nomina.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.swing.ListSelectionModel;
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
import com.spirit.nomina.entity.ContratoUtilidadData;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.gui.panel.JPContratoUtilidad;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ContratoUtilidadModel extends JPContratoUtilidad {
	
	private static final long serialVersionUID = -5352647749640089270L;
	
	private static final int MAX_LONGITUD_CODIGO = 8;
	private static final int MAX_LONGITUD_MONTO_UTILIDAD = 50;
	private static final int MAX_LONGITUD_PORCENTAJE_CONTRATOS = 5;
	private static final int MAX_LONGITUD_PORCENTAJE_CARGAS = 5;
	
	private ArrayList<ContratoUtilidadIf> contratoUtilidadVector = new ArrayList<ContratoUtilidadIf>();
	private ContratoUtilidadIf contratoUtilidadIf;
	
	public ContratoUtilidadModel(){
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblContratoUtilidad());
		getTblContratoUtilidad().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblContratoUtilidad().addMouseListener(oMouseAdapterTblTipoRol);
		getTblContratoUtilidad().addKeyListener(oKeyAdapterTblTipoRol);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		/*TableColumnModel modelo = getTblContratoUtilidad().getColumnModel();
		TableColumn anchoColumna = modelo.getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = modelo.getColumn(1);
		anchoColumna.setPreferredWidth(350);*/
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtMontoUtilidad().addKeyListener(new TextChecker(MAX_LONGITUD_MONTO_UTILIDAD));
		getTxtMontoUtilidad().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeContratos().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_CONTRATOS));
		getTxtPorcentajeContratos().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeCargas().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_CARGAS));
		getTxtPorcentajeCargas().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void iniciarComponentes(){
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		TableColumnModel modeloColumna = getTblContratoUtilidad().getColumnModel(); 
		modeloColumna.getColumn(3).setCellRenderer(
				new NumberCellRenderer("#,###,##0.00",NumberCellRenderer.DERECHA) );
		modeloColumna.getColumn(4).setCellRenderer(
				new NumberCellRenderer("#,###,##0.00",NumberCellRenderer.DERECHA) );
		modeloColumna.getColumn(5).setCellRenderer(
				new NumberCellRenderer("#,###,##0.00",NumberCellRenderer.DERECHA) );
		
	}
	
	MouseListener oMouseAdapterTblTipoRol = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoRol = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		int tipoRolSeleccionado = getTblContratoUtilidad().getSelectedRow(); 
		if ( tipoRolSeleccionado != -1) {
			tipoRolSeleccionado = getTblContratoUtilidad().convertRowIndexToModel(tipoRolSeleccionado); 
			contratoUtilidadIf = contratoUtilidadVector.get(tipoRolSeleccionado);
			getTxtCodigo().setText(contratoUtilidadIf.getCodigo());
			getTxtMontoUtilidad().setText(contratoUtilidadIf.getUtilidad().toString());
			getTxtPorcentajeContratos().setText(contratoUtilidadIf.getPorcentajeContrato().toString());
			getTxtPorcentajeCargas().setText(contratoUtilidadIf.getPorcentajeCarga().toString());
			
			getCmbFechaInicio().setDate(contratoUtilidadIf.getFechaInicio());
			getCmbFechaInicio().repaint();
			
			getCmbFechaFin().setDate(contratoUtilidadIf.getFechaFin());
			getCmbFechaFin().repaint();
			
			setUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		//cargarCmbDocumentos();
		cargarTabla();
		iniciarComponentes();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection<ContratoUtilidadIf> cus = SessionServiceLocator.getContratoUtilidadSessionService()
				.findContratoUtilidadByEmpresaId(Parametros.getIdEmpresa());
			
			DefaultTableModel tableModel = (DefaultTableModel) getTblContratoUtilidad().getModel();
			for ( ContratoUtilidadIf cu : cus ) {
				
				Vector<Object> fila = new Vector<Object>();
				contratoUtilidadVector.add(cu);
				agregarColumnasTabla(cu, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblContratoUtilidad(), cus, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(ContratoUtilidadIf cu, Vector<Object> fila){
		fila.add(cu.getCodigo());
		fila.add(Utilitarios.getFechaUppercase(cu.getFechaInicio()));
		fila.add(Utilitarios.getFechaUppercase(cu.getFechaFin()));
		fila.add(cu.getUtilidad());
		fila.add(cu.getPorcentajeContrato());
		fila.add(cu.getPorcentajeCarga());
	}
	
	@Override
	public void refresh() {
		clean();
		cargarTabla();
	}

	public void showFindMode() {		
		showSaveMode();
	}

	public void save() {
		try {
			if (validateFields()) {
				ContratoUtilidadIf data = new ContratoUtilidadData();

				setDatosContratoUtilidad(data);
				
				SessionServiceLocator.getContratoUtilidadSessionService().addContratoUtilidad(data);
				SpiritAlert.createAlert("Datos de Utilidades grabados con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				
				setDatosContratoUtilidad(contratoUtilidadIf);
				
				SessionServiceLocator.getContratoUtilidadSessionService().saveContratoUtilidad(contratoUtilidadIf);
				contratoUtilidadIf = null;
				
				SpiritAlert.createAlert("Datos de Utilidades actualizados con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getContratoUtilidadSessionService().deleteContratoUtilidad(contratoUtilidadIf.getId());
			SpiritAlert.createAlert("Datos de Utilidades eliminados con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro, puede tener datos referenciados!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void setDatosContratoUtilidad(ContratoUtilidadIf data) {
		data.setCodigo(getTxtCodigo().getText());
		Date fechaInicio = getCmbFechaInicio().getDate();
		data.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
		Date fechaFin = getCmbFechaFin().getDate();
		data.setFechaFin(new java.sql.Date(fechaFin.getTime()));
		
		String montoUtilidadS = getTxtMontoUtilidad().getText();
		montoUtilidadS = montoUtilidadS!=null && !"".equals(montoUtilidadS) ? 
				Utilitarios.removeDecimalFormat(montoUtilidadS) : null;
		BigDecimal montoUtilidad = new BigDecimal(montoUtilidadS);
		data.setUtilidad(montoUtilidad);
		
		String porcentajeContratosS = getTxtPorcentajeContratos().getText();
		porcentajeContratosS = porcentajeContratosS!=null && !"".equals(porcentajeContratosS) ? 
				Utilitarios.removeDecimalFormat(porcentajeContratosS) : null;
		BigDecimal porcentajeContratos = new BigDecimal(porcentajeContratosS);
		data.setPorcentajeContrato(porcentajeContratos);
		
		String porcentajeCargasS = getTxtPorcentajeCargas().getText();
		porcentajeCargasS = porcentajeCargasS!=null && !"".equals(porcentajeCargasS) ? 
				Utilitarios.removeDecimalFormat(porcentajeCargasS) : null;
		BigDecimal porcentajeCargas = new BigDecimal(porcentajeCargasS);
		data.setPorcentajeCarga(porcentajeCargas);
		
		data.setEmpresaId(Parametros.getIdEmpresa());
	}
	
	public void clean() {
		
		getTxtCodigo().setText("");
		getTxtMontoUtilidad().setText("");
		getTxtPorcentajeContratos().setText("");
		getTxtPorcentajeCargas().setText("");
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
	
		contratoUtilidadVector = null;
		contratoUtilidadVector = new ArrayList<ContratoUtilidadIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblContratoUtilidad());
	}

	public boolean validateFields() {
		
		String strCodigo = getTxtCodigo().getText();
		
		boolean codigoRepetido = false;
		String fechaIncorrecta = null;
		
		Date fechaInicio = getCmbFechaInicio().getDate();
		Date fechaFin = getCmbFechaFin().getDate();
		
		if ( fechaInicio.compareTo(fechaFin) >= 0 ){
			SpiritAlert.createAlert("Fecha Fin tiene que ser mayor que Fecha Inicio !!", SpiritAlert.WARNING);
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		try {
			Collection<ContratoUtilidadIf> cus = SessionServiceLocator.getContratoUtilidadSessionService()
				.findContratoUtilidadByEmpresaId(Parametros.getIdEmpresa());
		
			
			java.sql.Date fechaInicioSql = new java.sql.Date(fechaInicio.getTime());
			java.sql.Date fechaFinSql = new java.sql.Date(fechaFin.getTime());
			
			for (ContratoUtilidadIf contratoUtilidadIf : cus){
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (getTxtCodigo().getText().equals(contratoUtilidadIf.getCodigo())){				
						codigoRepetido = true;
						break;
					}
					
					if ( fechaInicioSql.compareTo(contratoUtilidadIf.getFechaInicio()) >= 0 &&
							fechaInicioSql.compareTo(contratoUtilidadIf.getFechaFin()) <= 0 ){
						fechaIncorrecta = "Fecha de Inicio de cruza con otras existentes !!";
						break;
					}
					
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if ( !this.contratoUtilidadIf.getId().equals(contratoUtilidadIf.getId()) ){
						if (getTxtCodigo().getText().equals(contratoUtilidadIf.getCodigo())){ 
							codigoRepetido = true;
							break;
						}
						
						if ( fechaInicioSql.compareTo(contratoUtilidadIf.getFechaInicio()) >= 0 && 
								fechaInicioSql.compareTo(contratoUtilidadIf.getFechaFin()) <= 0 ){
							fechaIncorrecta = "Fecha de Inicio de cruza con otras existentes !!";
							break;
						}
					}
				}
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código está duplicado !!",SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if (fechaIncorrecta != null){
				SpiritAlert.createAlert(fechaIncorrecta,SpiritAlert.WARNING);
				getCmbFechaInicio().grabFocus();
				return false;
			}
			
			return true;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return false;
	}
	
	public void showUpdateMode() {		
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}	
}
