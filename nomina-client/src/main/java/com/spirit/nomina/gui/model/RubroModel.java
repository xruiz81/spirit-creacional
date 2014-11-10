package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.RubroData;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPRubro;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class RubroModel extends JPRubro {

	private static final long serialVersionUID = 4073965137513212126L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final int MAX_LONGITUD_POLITICA = 50;
	
	private static final String NOMBRE_PAGO_INDIVIDUAL_SI = "SI";
	private static final String NOMBRE_PAGO_INDIVIDUAL_NO = "NO";
	
	private static final String NOMBRE_FRECUENCIA_SEMANAL = "SEMANAL";
	private static final String NOMBRE_FRECUENCIA_QUINCENAL = "QUINCENAL";
	private static final String NOMBRE_FRECUENCIA_MENSUAL = "MENSUAL";
	private static final String NOMBRE_FRECUENCIA_BIMENSUAL = "BIMENSUAL";
	private static final String NOMBRE_FRECUENCIA_TRIMESTRAL = "TRIMESTRAL";
	private static final String NOMBRE_FRECUENCIA_SEMESTRAL = "SEMESTRAL";
	private static final String NOMBRE_FRECUENCIA_ANUAL = "ANUAL";
	private static final String NOMBRE_FRECUENCIA_ESPECIFICO = "ESPECIFICO";
	
	private static final String FRECUENCIA_SEMANAL = "S";
	private static final String FRECUENCIA_QUINCENAL = "Q";
	private static final String FRECUENCIA_MENSUAL = "M";
	private static final String FRECUENCIA_BIMENSUAL = "B";
	private static final String FRECUENCIA_TRIMESTRAL = "T";
	private static final String FRECUENCIA_SEMESTRAL = "L";
	private static final String FRECUENCIA_ANUAL = "A";
	private static final String FRECUENCIA_ESPECIFICO = "E";
	
	private static final String NOMBRE_MODO_OPERACION_REGISTRADO = "REGISTRADO";
	private static final String NOMBRE_MODO_OPERACION_CALCULADO = "CALCULADO";
	private static final String MODO_OPERACION_REGISTRADO = "R";
	private static final String MODO_OPERACION_CALCULADO = "C";
	
	private Vector rubroVector = new Vector();
	private DefaultTableModel tableModel;
	protected RubroIf rubroActualizadoIf;
	private int rubroSeleccionado;
	int diaFecha;	
	int mesFecha;
	
	public RubroModel() {		
		cargarCombos();
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblRubro());
		anchoColumnasTabla();
		initListeners();
		
		new HotKeyComponent(this);
	}
	
	private void initListeners(){
		getCmbPagoIndividual().setSelectedItem(NOMBRE_PAGO_INDIVIDUAL_NO);
		getCmbPagoIndividual().setEnabled(false);
		getCmbFrecuencia().addActionListener(alCmbFrecuenciaListener);
		getCmbTipoRubro().addActionListener(alCmbTipoRubro);
		getTblRubro().addMouseListener(oMouseAdapterTblRubro);
		getTblRubro().addKeyListener(oKeyAdapterTblRubro);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtPolitica().addKeyListener(new TextChecker(MAX_LONGITUD_POLITICA));
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setShowNoneButton(false);
		getCmbFecha().setFormat(Utilitarios.setFechaSinAnioUppercase());
		getCmbFecha().setEditable(false);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblRubro().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblRubro().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblRubro().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblRubro().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
	}
	
	ActionListener alCmbTipoRubro = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			TipoRubro trSeleccionado = (TipoRubro) getCmbTipoRubro().getSelectedItem();
			if ( trSeleccionado != null){
				if ( TipoRubro.EVENTUAL == trSeleccionado ||
					 TipoRubro.EVENTUAL_BENEFICIOS_SOCIALES == trSeleccionado ||
					 TipoRubro.COMISION == trSeleccionado ){
					getCmbPagoIndividual().setEnabled(true);
					getCmbFrecuencia().setEnabled(false);
					getCmbModoOperacion().setEnabled(false);
					getTxtPolitica().setEnabled(false);
				} else {
					getCmbPagoIndividual().setSelectedItem(NOMBRE_PAGO_INDIVIDUAL_NO);
					getCmbPagoIndividual().setEnabled(false);
					getCmbFrecuencia().setEnabled(true);
					getCmbModoOperacion().setEnabled(true);
					getTxtPolitica().setEnabled(true);
				}
			}
				
		}
	};
	
	ActionListener alCmbFrecuenciaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ESPECIFICO)){
				getLblFecha().setVisible(true);
				getCmbFecha().setVisible(true);
			}
			else{
				getLblFecha().setVisible(false);
				getCmbFecha().setVisible(false);
			}
		}
	};
	
	MouseListener oMouseAdapterTblRubro = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblRubro = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setRubroSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			rubroActualizadoIf = (RubroIf)  getRubroVector().get(getRubroSeleccionado());
			
			getTxtCodigo().setText(rubroActualizadoIf.getCodigo());
			getTxtNombre().setText(rubroActualizadoIf.getNombre());
			
			getCmbTipoRol().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoRol(), rubroActualizadoIf.getTiporolId()));
			getCmbTipoRol().repaint();
			
			String letraTipoRubro = rubroActualizadoIf.getTipoRubro();
			TipoRubro tr;
			try {
				tr = TipoRubro.getTipoRubroByLetra(letraTipoRubro);
				getCmbTipoRubro().setSelectedItem(tr);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				getCmbTipoRubro().setSelectedItem(null);
			}
			
			if (NOMBRE_PAGO_INDIVIDUAL_SI.substring(0, 1).equals(rubroActualizadoIf.getPagoIndividual()) )
				getCmbPagoIndividual().setSelectedItem(NOMBRE_PAGO_INDIVIDUAL_SI);
			else if (NOMBRE_PAGO_INDIVIDUAL_NO.substring(0, 1).equals(rubroActualizadoIf.getPagoIndividual()))
				getCmbPagoIndividual().setSelectedItem(NOMBRE_PAGO_INDIVIDUAL_NO);
			
			if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_SEMANAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_SEMANAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_QUINCENAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_QUINCENAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_MENSUAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_MENSUAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_BIMENSUAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_BIMENSUAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_TRIMESTRAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_TRIMESTRAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_SEMESTRAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_SEMESTRAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_ANUAL))
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_ANUAL);
			else if(rubroActualizadoIf.getFrecuencia().equals(FRECUENCIA_ESPECIFICO)){
				getCmbFrecuencia().setSelectedItem(NOMBRE_FRECUENCIA_ESPECIFICO);
				getLblFecha().setVisible(true);
				getCmbFecha().setVisible(true);
				getCmbFecha().setDate(rubroActualizadoIf.getFecha());
			}
			
			if(rubroActualizadoIf.getModoOperacion().equals(MODO_OPERACION_REGISTRADO))
				getCmbModoOperacion().setSelectedItem(NOMBRE_MODO_OPERACION_REGISTRADO);
			else if(rubroActualizadoIf.getModoOperacion().equals(MODO_OPERACION_CALCULADO))
				getCmbModoOperacion().setSelectedItem(NOMBRE_MODO_OPERACION_CALCULADO);
			
			if ( rubroActualizadoIf.getNemonico()!=null ){
				getCmbNemonico().setSelectedItem(rubroActualizadoIf.getNemonico());
			} else {
				getCmbNemonico().setSelectedItem(null);	
			}
			
			getTxtPolitica().setText(rubroActualizadoIf.getPolitica());
			

			getCmbNemonico().setSelectedItem(null);
			String nemonico = rubroActualizadoIf.getNemonico();
			getCmbNemonico().setSelectedItem(nemonico);
			
			showUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void refresh(){
		cargarCmbTipoRol();
		cargarCmbNemonicos();
		clean();
		cargarTabla();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		try {
			Collection rubro = SessionServiceLocator.getRubroSessionService().findRubroByEmpresaId(Parametros.getIdEmpresa());
			Iterator rubroIterator = rubro.iterator();
			
			if(!getRubroVector().isEmpty()){
				getRubroVector().removeAllElements();
			}
			
			while (rubroIterator.hasNext()) {
				RubroIf rubroIf = (RubroIf) rubroIterator.next();
				
				tableModel = (DefaultTableModel) getTblRubro().getModel();
				Vector<String> fila = new Vector<String>();
				getRubroVector().add(rubroIf);
				
				agregarColumnasTabla(rubroIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblRubro(), rubro, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(RubroIf rubroIf, Vector<String> fila){
		
		fila.add(rubroIf.getCodigo());
		fila.add(rubroIf.getNombre());
		
		try {
			TipoRolIf tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rubroIf.getTiporolId());
			fila.add(tipoRol.toString());
					
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		
		String letraTipoRubro = rubroIf.getTipoRubro();
		try {
			fila.add(TipoRubro.getTipoRubroByLetra(letraTipoRubro).toString());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			fila.add(null);
		}
		
		/*if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_BENEFICIO))
			fila.add(NOMBRE_TIPO_RUBRO_BENEFICIO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_DESCUENTO))
			fila.add(NOMBRE_TIPO_RUBRO_DESCUENTO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_SUELDO))
			fila.add(NOMBRE_TIPO_RUBRO_SUELDO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_ANTICIPO))
			fila.add(NOMBRE_TIPO_RUBRO_ANTICIPO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_EVENTUAL))
			fila.add(NOMBRE_TIPO_RUBRO_EVENTUAL);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_PROVISION))
			fila.add(NOMBRE_TIPO_RUBRO_PROVISION);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_QUINCENA))
			fila.add(NOMBRE_TIPO_RUBRO_QUINCENA);*/
	}

	public void save() {
		
		diaFecha = getCmbFecha().getDate().getDate();
		mesFecha = getCmbFecha().getDate().getMonth();
				
		try {
			if (validateFields()) {
				RubroData data = new RubroData();

				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setTiporolId(((TipoRolIf) getCmbTipoRol().getSelectedItem()).getId());
				
				TipoRubro tr = (TipoRubro) getCmbTipoRubro().getSelectedItem();
				data.setTipoRubro(tr.getLetra());
				/*if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_BENEFICIO))
					data.setTipoRubro(TIPO_RUBRO_BENEFICIO);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_DESCUENTO))
					data.setTipoRubro(TIPO_RUBRO_DESCUENTO);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_SUELDO))
					data.setTipoRubro(TIPO_RUBRO_SUELDO);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_ANTICIPO))
					data.setTipoRubro(TIPO_RUBRO_ANTICIPO);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_EVENTUAL))
					data.setTipoRubro(TIPO_RUBRO_EVENTUAL);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_PROVISION))
					data.setTipoRubro(TIPO_RUBRO_PROVISION);
				else if(getCmbTipoRubro().getSelectedItem().equals(NOMBRE_TIPO_RUBRO_QUINCENA))
						data.setTipoRubro(TIPO_RUBRO_QUINCENA);*/
				
				if ( getCmbPagoIndividual().getSelectedItem().equals((NOMBRE_PAGO_INDIVIDUAL_SI)) )
					data.setPagoIndividual(NOMBRE_PAGO_INDIVIDUAL_SI.substring(0, 1));
				else if (getCmbPagoIndividual().getSelectedItem().equals((NOMBRE_PAGO_INDIVIDUAL_NO)))
					data.setPagoIndividual(NOMBRE_PAGO_INDIVIDUAL_NO.substring(0, 1));
				
				if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_SEMANAL))
					data.setFrecuencia(FRECUENCIA_SEMANAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_QUINCENAL))
					data.setFrecuencia(FRECUENCIA_QUINCENAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_MENSUAL))
					data.setFrecuencia(FRECUENCIA_MENSUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_BIMENSUAL))
					data.setFrecuencia(FRECUENCIA_BIMENSUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_TRIMESTRAL))
					data.setFrecuencia(FRECUENCIA_TRIMESTRAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_SEMESTRAL))
					data.setFrecuencia(FRECUENCIA_SEMESTRAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ANUAL))
					data.setFrecuencia(FRECUENCIA_ANUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ESPECIFICO))
					data.setFrecuencia(FRECUENCIA_ESPECIFICO);
				
				if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ESPECIFICO))
					data.setFecha(new java.sql.Date(100, mesFecha, diaFecha));
				
				if(getCmbModoOperacion().getSelectedItem().equals(NOMBRE_MODO_OPERACION_REGISTRADO))
					data.setModoOperacion(MODO_OPERACION_REGISTRADO);
				else if(getCmbModoOperacion().getSelectedItem().equals(NOMBRE_MODO_OPERACION_CALCULADO))
					data.setModoOperacion(MODO_OPERACION_CALCULADO);
				
				data.setPolitica(getTxtPolitica().getText());
				
				String nemonico = (String)getCmbNemonico().getSelectedItem();
				if ( nemonico != null ){
					data.setNemonico(nemonico);
				}
							
				SessionServiceLocator.getRubroSessionService().addRubro(data);
				SpiritAlert.createAlert("Rubro guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		
		diaFecha = getCmbFecha().getDate().getDate();
		mesFecha = getCmbFecha().getDate().getMonth();
		
		try {
			if (validateFields()) {
				setRubroActualizadoIf((RubroIf) getRubroVector().get(getRubroSeleccionado()));
				
				getRubroActualizadoIf().setCodigo(getTxtCodigo().getText());
				getRubroActualizadoIf().setNombre(getTxtNombre().getText());
				getRubroActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				getRubroActualizadoIf().setTiporolId(((TipoRolIf) getCmbTipoRol().getSelectedItem()).getId());
				
				TipoRubro tr = (TipoRubro)getCmbTipoRubro().getSelectedItem();
				getRubroActualizadoIf().setTipoRubro(tr.getLetra());
				
				if ( getCmbPagoIndividual().getSelectedItem().equals((NOMBRE_PAGO_INDIVIDUAL_SI)) )
					getRubroActualizadoIf().setPagoIndividual(NOMBRE_PAGO_INDIVIDUAL_SI.substring(0, 1));
				else if (getCmbPagoIndividual().getSelectedItem().equals((NOMBRE_PAGO_INDIVIDUAL_NO)))
					getRubroActualizadoIf().setPagoIndividual(NOMBRE_PAGO_INDIVIDUAL_NO.substring(0, 1));
				
				if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_SEMANAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_SEMANAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_QUINCENAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_QUINCENAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_MENSUAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_MENSUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_BIMENSUAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_BIMENSUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_TRIMESTRAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_TRIMESTRAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_SEMESTRAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_SEMESTRAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ANUAL))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_ANUAL);
				else if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ESPECIFICO))
					getRubroActualizadoIf().setFrecuencia(FRECUENCIA_ESPECIFICO);
				
				if(getCmbFrecuencia().getSelectedItem().equals(NOMBRE_FRECUENCIA_ESPECIFICO))
					getRubroActualizadoIf().setFecha(new java.sql.Date(100, mesFecha, diaFecha));
				
				if(getCmbModoOperacion().getSelectedItem().equals(NOMBRE_MODO_OPERACION_REGISTRADO))
					getRubroActualizadoIf().setModoOperacion(MODO_OPERACION_REGISTRADO);
				else if(getCmbModoOperacion().getSelectedItem().equals(NOMBRE_MODO_OPERACION_CALCULADO))
					getRubroActualizadoIf().setModoOperacion(MODO_OPERACION_CALCULADO);
				
				getRubroActualizadoIf().setPolitica(getTxtPolitica().getText());
				
				String nemonico = (String) getCmbNemonico().getSelectedItem();
				if ( nemonico!= null ){
					getRubroActualizadoIf().setNemonico(nemonico);
				}
				
				SessionServiceLocator.getRubroSessionService().saveRubro(getRubroActualizadoIf());
				getRubroVector().setElementAt(getRubroActualizadoIf(), getRubroSeleccionado());
				setRubroActualizadoIf(null);
				
				SpiritAlert.createAlert("Rubro actualizado con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			RubroIf rubroIf = (RubroIf) getRubroVector().get(getRubroSeleccionado());
			SessionServiceLocator.getRubroSessionService().deleteRubro(rubroIf.getId());
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
		Calendar calendar = new GregorianCalendar();
		getCmbFecha().setCalendar(calendar);
		
		getTxtCodigo().setText("");
		getTxtNombre().setText(""); 
		getTxtPolitica().setText("");
		getCmbTipoRubro().setSelectedIndex(0);
		getCmbFrecuencia().setSelectedIndex(0);
		getCmbModoOperacion().setSelectedIndex(0);
		getLblFecha().setVisible(false);
		getCmbFecha().setVisible(false);
		
		getCmbNemonico().setSelectedItem(null);
				
		//Vacio la tabla
		limpiarTabla(getTblRubro());
	}
	
	public void cargarCombos(){
		cargarCmbTipoRol();
		cargarCmbNemonicos();
		
		TipoRubro[] trs = TipoRubro.values();
		for ( TipoRubro tr : trs ){
			getCmbTipoRubro().addItem(tr);
		}
		getCmbTipoRubro().setSelectedIndex(0);
		
		getCmbPagoIndividual().addItem(NOMBRE_PAGO_INDIVIDUAL_NO);
		getCmbPagoIndividual().addItem(NOMBRE_PAGO_INDIVIDUAL_SI);
		getCmbPagoIndividual().setSelectedIndex(0);
		
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_SEMANAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_QUINCENAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_MENSUAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_BIMENSUAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_TRIMESTRAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_SEMESTRAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_ANUAL);
		getCmbFrecuencia().addItem(NOMBRE_FRECUENCIA_ESPECIFICO);
		getCmbFrecuencia().setSelectedIndex(0);
		
		getCmbModoOperacion().addItem(NOMBRE_MODO_OPERACION_REGISTRADO);
		getCmbModoOperacion().addItem(NOMBRE_MODO_OPERACION_CALCULADO);
		getCmbModoOperacion().setSelectedIndex(0);
	}
	
	private void cargarCmbNemonicos() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		Collection<String> nemonicos = SessionServiceLocator.getPlantillaSessionService().findNemonicosDePlantillas();
		for ( String nemonico : nemonicos ){
			model.addElement(nemonico);
		}
		getCmbNemonico().setModel(model);
		getCmbNemonico().setSelectedItem(null);
		getCmbNemonico().setEnabled(true);
	}
	
	private void cargarCmbTipoRol(){
		try {
			List tiposRol = (List) SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoRol(),tiposRol);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		
		Collection<RubroIf> rubros = null;
		boolean codigoRepetido = false;
		
		try {
			rubros = SessionServiceLocator.getRubroSessionService().findRubroByEmpresaId(Parametros.getIdEmpresa());
			for (RubroIf rubroIf : rubros) {
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(rubroIf.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(rubroIf.getCodigo())) 
						if (getRubroActualizadoIf().getId().equals(rubroIf.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código del Rubro está duplicado !!",SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar el Código !!",SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un Nombre !!",SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
			if (getCmbTipoRol().getSelectedIndex() == -1) {
				SpiritAlert.createAlert("Debe seleccionar un Tipo Rol !!",SpiritAlert.WARNING);
				getCmbTipoRol().grabFocus();
				return false;
			}
			if (getCmbTipoRubro().getSelectedIndex() == -1) {
				SpiritAlert.createAlert("Debe seleccionar un Tipo Rubro !!",SpiritAlert.WARNING);
				getCmbTipoRubro().grabFocus();
				return false;
			} else {
				if ( getCmbPagoIndividual().getSelectedItem() == null ){
					SpiritAlert.createAlert("Debe seleccionar si es Pago Individual o No !!",SpiritAlert.WARNING);
					getCmbPagoIndividual().grabFocus();
					return false;
				}
			}
			if (getCmbFrecuencia().getSelectedIndex() == -1) {
				SpiritAlert.createAlert("Debe seleccionar una Frecuencia !!",SpiritAlert.WARNING);
				getCmbFrecuencia().grabFocus();
				return false;
			}
			if (getCmbModoOperacion().getSelectedIndex() == -1) {
				SpiritAlert.createAlert("Debe seleccionar un Modo de Operación !!",SpiritAlert.WARNING);
				getCmbModoOperacion().grabFocus();
				return false;
			}
			if(getCmbModoOperacion().getSelectedItem().equals(NOMBRE_MODO_OPERACION_CALCULADO) && 
					((("".equals(getTxtPolitica().getText())) || (getTxtPolitica().getText() == null)))){
				SpiritAlert.createAlert("Debe ingresar una Política si el modo de operación es Calculado !!",SpiritAlert.WARNING);
				getTxtPolitica().grabFocus();
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
	
	public RubroIf getRubroActualizadoIf() {
		return rubroActualizadoIf;
	}

	public void setRubroActualizadoIf(RubroIf rubroActualizadoIf) {
		this.rubroActualizadoIf = rubroActualizadoIf;
	}

	public int getRubroSeleccionado() {
		return rubroSeleccionado;
	}

	public void setRubroSeleccionado(int rubroSeleccionado) {
		this.rubroSeleccionado = rubroSeleccionado;
	}

	public Vector getRubroVector() {
		return rubroVector;
	}

	public void setRubroVector(Vector rubroVector) {
		this.rubroVector = rubroVector;
	}
	
}
