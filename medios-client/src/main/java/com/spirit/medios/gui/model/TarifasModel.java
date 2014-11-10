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
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.medios.entity.PrensaFormatoIf;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.entity.PrensaTarifaData;
import com.spirit.medios.entity.PrensaTarifaIf;
import com.spirit.medios.entity.PrensaUbicacionIf;
import com.spirit.medios.gui.panel.JPTarifas;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class TarifasModel extends JPTarifas{

	private static final long serialVersionUID = -2373764440262674255L;
	
	private static final int MAX_LONGITUD_CODIGO = 12;
	private static final String NOMBRE_COLOR_BN = "B/N";
	private static final String NOMBRE_COLOR_COLOR = "COLOR";
	private static final String COLOR_BN = "B";
	private static final String COLOR_COLOR = "C";
	private static final String NOMBRE_DIA_ORDINARIO = "ORDINARIO";
	private static final String NOMBRE_DIA_SABADO = "SABADO";
	private static final String NOMBRE_DIA_DOMINGO = "DOMINGO ";
	private static final String NOMBRE_DIA_FERIADO = "FERIADO";
	private static final String DIA_ORDINARIO = "O";
	private static final String DIA_SABADO = "S";
	private static final String DIA_DOMINGO = "D";
	private static final String DIA_FERIADO = "F";
	private static final String NOMBRE_SI = "SI";
	private static final String NOMBRE_NO = "NO";
	private static final String SI = "S";
	private static final String NO = "N";
	private static final String NOMBRE_OPERACION_COLUMNASXCM = "COLUMNAS X CM.";
	private static final String NOMBRE_OPERACION_COLUMNASXMODULOS = "COLUMNAS X MODULOS";
	private static final String OPERACION_COLUMNASXCM = "C";
	private static final String OPERACION_COLUMNASXMODULOS = "M";
	
	private DefaultTableModel modelTableTarifa;
	private Vector<PrensaTarifaIf> prensaTarifaColeccion = new Vector<PrensaTarifaIf>();
	private Vector<PrensaTarifaIf> prensaTarifaRemovidaColeccion = new Vector<PrensaTarifaIf>();
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("000");
	private int prensaTarifaSeleccionada;
	private PrensaTarifaIf prensaTarifaIf;


	public TarifasModel(){
		anchoColumnasTabla();
		cargarCombos();
		initKeyListeners();
		showSaveMode();
		initListeners();
		setSorterTable(getTblTarifa());
		getTblTarifa().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    getTblTarifa().addMouseListener(oMouseAdapterTblTarifa);
		getTblTarifa().addKeyListener(oKeyAdapterTblTarifa);
		
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTarifa().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTarifa().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(60);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblTarifa().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}
	
	public void cargarCombos(){
		cargarCombosDiario();
		
		getCmbColor().removeAllItems();
		getCmbDia().removeAllItems();
		getCmbTCalculada().removeAllItems();
		getCmbOperacion().removeAllItems();
		
		getCmbColor().addItem(NOMBRE_COLOR_BN);
		getCmbColor().addItem(NOMBRE_COLOR_COLOR);
		
		getCmbDia().addItem(NOMBRE_DIA_ORDINARIO);
		getCmbDia().addItem(NOMBRE_DIA_SABADO);
		getCmbDia().addItem(NOMBRE_DIA_DOMINGO);
		getCmbDia().addItem(NOMBRE_DIA_FERIADO);
		
		getCmbTCalculada().addItem(NOMBRE_NO);
		getCmbTCalculada().addItem(NOMBRE_SI);	
		
		getCmbOperacion().addItem(NOMBRE_OPERACION_COLUMNASXCM);
		getCmbOperacion().addItem(NOMBRE_OPERACION_COLUMNASXMODULOS);
	}
	
	private void cargarCombosDiario() {
		try {
			getCmbDiarioTarifa().removeAllItems();
						
			Long tipoProveedorPrensa = null;
			//ParametroEmpresaIf parametroTipoProveedorPrensa = SessionServiceLocator.getUtilitariosSessionService().getParametroEmpresa(Parametros.getIdEmpresa(), "MEDIOS", "TIPOPROVEEDORPRENSA", "No se encuetra el parametro TipoProveedorPrensa");
			//Iterator it= SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByNombre(parametroTipoProveedorPrensa.getValor()).iterator();
			Iterator it= SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByNombre("PRENSA").iterator();
			if(it.hasNext()){
				TipoProveedorIf tp = (TipoProveedorIf)it.next();
				tipoProveedorPrensa=tp.getId();
			}			
			
			List diarios = (List) SessionServiceLocator.getClienteSessionService().findClienteByTipoproveedorId(tipoProveedorPrensa);
			refreshCombo(getCmbDiarioTarifa(), diarios);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Diario", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void initKeyListeners(){
		getTxtCodigoTarifa().setEditable(false);
		getTxtCodigoTarifa().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtTarifa().addKeyListener(new NumberTextFieldDecimal());
		getTxtRecargo().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void initListeners(){
		getCmbDiarioTarifa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbDiarioTarifa().getSelectedIndex() != -1){
					cargarComboSeccion();
					cargarComboUbicacion();
					cargarComboFormato();
				}
			}
		});
		
		getCmbTCalculada().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTCalculada().getSelectedItem() != null){
					if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
						getCmbOperacion().setEnabled(true);
					}else{
						getCmbOperacion().setEnabled(false);
					}
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblTarifa = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTarifa = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			//Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setPrensaTarifaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				prensaTarifaIf = (PrensaTarifaIf)  getPrensaTarifaColeccion().get(getPrensaTarifaSeleccionada());
				getTxtCodigoTarifa().setText(prensaTarifaIf.getCodigo());
				getCmbDiarioTarifa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDiarioTarifa(), prensaTarifaIf.getClienteId()));
				getCmbDiarioTarifa().repaint();
				getCmbSeccionTarifa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSeccionTarifa(), prensaTarifaIf.getPrensaSeccionId()));
				getCmbSeccionTarifa().repaint();
				getCmbUbicacionTarifa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbUbicacionTarifa(), prensaTarifaIf.getPrensaUbicacionId()));
				getCmbUbicacionTarifa().repaint();
				getCmbFormatoTarifa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFormatoTarifa(), prensaTarifaIf.getPrensaFormatoId()));
				getCmbFormatoTarifa().repaint();
				
				if(prensaTarifaIf.getColor().equals(COLOR_COLOR)){
					getCmbColor().setSelectedItem(NOMBRE_COLOR_COLOR);
				}else if(prensaTarifaIf.getColor().equals(COLOR_BN)){
					getCmbColor().setSelectedItem(NOMBRE_COLOR_BN);
				}
				
				if(prensaTarifaIf.getDia().equals(DIA_DOMINGO)){
					getCmbDia().setSelectedItem(NOMBRE_DIA_DOMINGO);
				}else if(prensaTarifaIf.getDia().equals(DIA_ORDINARIO)){
					getCmbDia().setSelectedItem(NOMBRE_DIA_ORDINARIO);
				}else if(prensaTarifaIf.getDia().equals(DIA_SABADO)){
					getCmbDia().setSelectedItem(NOMBRE_DIA_SABADO);
				}else if(prensaTarifaIf.getDia().equals(DIA_FERIADO)){
					getCmbDia().setSelectedItem(NOMBRE_DIA_FERIADO);
				}
				
				if(prensaTarifaIf.getOperacion() != null){
					getCmbTCalculada().setSelectedItem(NOMBRE_NO);
					getCmbOperacion().setEnabled(true);
					if(prensaTarifaIf.getOperacion().equals(OPERACION_COLUMNASXCM)){
						getCmbOperacion().setSelectedItem(NOMBRE_OPERACION_COLUMNASXCM);
					}else if(prensaTarifaIf.getOperacion().equals(OPERACION_COLUMNASXMODULOS)){
						getCmbOperacion().setSelectedItem(NOMBRE_OPERACION_COLUMNASXMODULOS);
					}
				}else{
					getCmbTCalculada().setSelectedItem(NOMBRE_SI);
					getCmbOperacion().setEnabled(false);
				}
				
				BigDecimal tarifa = prensaTarifaIf.getTarifa();
				if(prensaTarifaIf.getRecargo() != null){
					BigDecimal recargo = prensaTarifaIf.getRecargo();
					getTxtRecargo().setText(recargo.toString());
					tarifa = tarifa.divide(new BigDecimal(1).add(recargo), 2, BigDecimal.ROUND_HALF_UP);
				}else{
					getTxtRecargo().setText("");
				}
				
				PrensaFormatoIf prensaFormato = SessionServiceLocator.getPrensaFormatoSessionService().getPrensaFormato(prensaTarifaIf.getPrensaFormatoId());
				if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
					if(getCmbOperacion().getSelectedItem().equals(NOMBRE_OPERACION_COLUMNASXCM)){
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal cm = prensaFormato.getAltoCm();
						tarifa = tarifa.divide(columnas.multiply(cm), 2, BigDecimal.ROUND_HALF_UP);
					}else{
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal modulos = prensaFormato.getAltoModulos();
						tarifa = tarifa.divide(columnas.multiply(modulos), 2, BigDecimal.ROUND_HALF_UP);
					}
				}else{
					tarifa = prensaTarifaIf.getTarifa();
				}
				
				getTxtTarifa().setText(formatoDecimal.format(tarifa));
							
				showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboFormato() {
		try {
			getCmbFormatoTarifa().removeAllItems();
			ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
			if( SessionServiceLocator.getPrensaFormatoSessionService().findPrensaFormatoByClienteId(diario.getId()) != null){
				List prensaFormatos = (List) SessionServiceLocator.getPrensaFormatoSessionService().findPrensaFormatoByClienteId(diario.getId());
				refreshCombo(getCmbFormatoTarifa(), prensaFormatos);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Formato, puede que no existan datos en la base!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboUbicacion() {
		try {			
			getCmbUbicacionTarifa().removeAllItems();
			ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
			if( SessionServiceLocator.getPrensaUbicacionSessionService().findPrensaUbicacionByClienteId(diario.getId()) != null){
				List prensaUbicaciones = (List) SessionServiceLocator.getPrensaUbicacionSessionService().findPrensaUbicacionByClienteId(diario.getId());
				refreshCombo(getCmbUbicacionTarifa(), prensaUbicaciones);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Ubicación, puede que no existan datos en la base!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboSeccion() {
		try {
			getCmbSeccionTarifa().removeAllItems();
			ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
			if( SessionServiceLocator.getPrensaSeccionSessionService().findPrensaSeccionByClienteId(diario.getId()) != null){
				List prensaSecciones = (List) SessionServiceLocator.getPrensaSeccionSessionService().findPrensaSeccionByClienteId(diario.getId());
				refreshCombo(getCmbSeccionTarifa(), prensaSecciones);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Sección, puede que no existan datos en la base!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarDetalleTarifa() {
		try {	
			if(validateFields()){
				modelTableTarifa = (DefaultTableModel) getTblTarifa().getModel();
				Vector<String> filaTarifa = new Vector<String>();
					
				PrensaTarifaData data = new PrensaTarifaData();
				
				data.setCodigo(getTxtCodigoTarifa().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
				data.setClienteId(diario.getId());
				PrensaSeccionIf prensaSeccion = (PrensaSeccionIf) getCmbSeccionTarifa().getSelectedItem();
				data.setPrensaSeccionId(prensaSeccion.getId());
				PrensaUbicacionIf prensaUbicacion = (PrensaUbicacionIf) getCmbUbicacionTarifa().getSelectedItem();
				data.setPrensaUbicacionId(prensaUbicacion.getId());
				PrensaFormatoIf prensaFormato = (PrensaFormatoIf) getCmbFormatoTarifa().getSelectedItem();
				data.setPrensaFormatoId(prensaFormato.getId());
				prensaTarifaColeccion.add(data);

				filaTarifa.add(getCmbDiarioTarifa().getSelectedItem().toString());
				filaTarifa.add(getCmbSeccionTarifa().getSelectedItem().toString());
				filaTarifa.add(getCmbUbicacionTarifa().getSelectedItem().toString());
				filaTarifa.add(getCmbFormatoTarifa().getSelectedItem().toString());
				filaTarifa.add(getCmbColor().getSelectedItem().toString());
				filaTarifa.add(getCmbDia().getSelectedItem().toString());
				filaTarifa.add(getTxtTarifa().getText());
				
				modelTableTarifa.addRow(filaTarifa);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible agregar la Tarifa!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleTarifa() {
		try {
			int filaSeleccionada = getTblTarifa().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(validateFields()){
					modelTableTarifa = (DefaultTableModel) getTblTarifa().getModel();
					Vector<String> filaTarifa = new Vector<String>();
						
					PrensaTarifaData data = new PrensaTarifaData();
					
					data.setCodigo(getTxtCodigoTarifa().getText());
					ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
					data.setClienteId(diario.getId());
					
					prensaTarifaColeccion.add(filaSeleccionada, data);
					prensaTarifaColeccion.remove(filaSeleccionada + 1);
					
					filaTarifa.add(getCmbDiarioTarifa().getSelectedItem().toString());
					
					modelTableTarifa.insertRow(filaSeleccionada, filaTarifa);
					modelTableTarifa.removeRow(filaSeleccionada + 1);
				}
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible actualizar la Tarifa!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleTarifa() {
		int filaSeleccionada = getTblTarifa().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				prensaTarifaRemovidaColeccion.add(prensaTarifaColeccion.get(filaSeleccionada));
				prensaTarifaColeccion.remove(filaSeleccionada);
				modelTableTarifa.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}

	public void save() {
		try {
			if(validateFields()){
				PrensaTarifaData data = new PrensaTarifaData();
				
				ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
				String codigo = SessionServiceLocator.getPrensaTarifaSessionService().getMaximoCodigoPrensaTarifa(diario.getId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				data.setCodigo(codigo);
				data.setClienteId(diario.getId());
				PrensaSeccionIf prensaSeccion = (PrensaSeccionIf) getCmbSeccionTarifa().getSelectedItem();
				data.setPrensaSeccionId(prensaSeccion.getId());
				PrensaUbicacionIf prensaUbicacion = (PrensaUbicacionIf) getCmbUbicacionTarifa().getSelectedItem();
				data.setPrensaUbicacionId(prensaUbicacion.getId());
				PrensaFormatoIf prensaFormato = (PrensaFormatoIf) getCmbFormatoTarifa().getSelectedItem();
				data.setPrensaFormatoId(prensaFormato.getId());
				
				if(getCmbColor().getSelectedItem().equals(NOMBRE_COLOR_COLOR)){
					data.setColor(COLOR_COLOR);
				}else if(getCmbColor().getSelectedItem().equals(NOMBRE_COLOR_BN)){
					data.setColor(COLOR_BN);
				}
				
				if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_DOMINGO)){
					data.setDia(DIA_DOMINGO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_ORDINARIO)){
					data.setDia(DIA_ORDINARIO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_SABADO)){
					data.setDia(DIA_SABADO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_FERIADO)){
					data.setDia(DIA_FERIADO);
				}
				
				if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_SI)){
					data.setTarifaCalculada(SI);
				}else if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
					data.setTarifaCalculada(NO);
				}
				
				BigDecimal recargo = BigDecimal.ZERO;
				if(!getTxtRecargo().getText().equals("")){
					recargo = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRecargo().getText())));
					data.setRecargo(recargo);
				}else{
					data.setRecargo(BigDecimal.ZERO);
				}
				
				BigDecimal tarifaTotal = BigDecimal.ZERO;
				if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
					if(getCmbOperacion().getSelectedItem().equals(NOMBRE_OPERACION_COLUMNASXCM)){
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal cm = prensaFormato.getAltoCm();
						BigDecimal tarifa = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
						tarifaTotal = tarifa.multiply(columnas.multiply(cm));
						data.setOperacion(OPERACION_COLUMNASXCM);
					}else{
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal modulos = prensaFormato.getAltoModulos();
						BigDecimal tarifa = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
						tarifaTotal = tarifa.multiply(columnas.multiply(modulos));
						data.setOperacion(OPERACION_COLUMNASXMODULOS);
					}
				}else{
					tarifaTotal = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
				}
				
				if(recargo != BigDecimal.ZERO){
					data.setTarifa(tarifaTotal.add(tarifaTotal.multiply(recargo.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))));
				}else{
					data.setTarifa(tarifaTotal);
				}
				
				SessionServiceLocator.getPrensaTarifaSessionService().addPrensaTarifa(data);
				
				SpiritAlert.createAlert("Tarifa guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			PrensaTarifaIf prensaTarifaIf = (PrensaTarifaIf) getPrensaTarifaColeccion().get(getPrensaTarifaSeleccionada());
			SessionServiceLocator.getPrensaTarifaSessionService().deletePrensaTarifa(prensaTarifaIf.getId());
			SpiritAlert.createAlert("Tarifa eliminada con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al eliminar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}	
	}

	public void update() {
		try {
			if(validateFields()){
				setPrensaTarifaIf((PrensaTarifaIf) getPrensaTarifaColeccion().get(getPrensaTarifaSeleccionada()));
								
				getPrensaTarifaIf().setCodigo(getTxtCodigoTarifa().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioTarifa().getSelectedItem();
				getPrensaTarifaIf().setClienteId(diario.getId());
				PrensaSeccionIf prensaSeccion = (PrensaSeccionIf) getCmbSeccionTarifa().getSelectedItem();
				getPrensaTarifaIf().setPrensaSeccionId(prensaSeccion.getId());
				PrensaUbicacionIf prensaUbicacion = (PrensaUbicacionIf) getCmbUbicacionTarifa().getSelectedItem();
				getPrensaTarifaIf().setPrensaUbicacionId(prensaUbicacion.getId());
				PrensaFormatoIf prensaFormato = (PrensaFormatoIf) getCmbFormatoTarifa().getSelectedItem();
				getPrensaTarifaIf().setPrensaFormatoId(prensaFormato.getId());
				
				if(getCmbColor().getSelectedItem().equals(NOMBRE_COLOR_COLOR)){
					getPrensaTarifaIf().setColor(COLOR_COLOR);
				}else if(getCmbColor().getSelectedItem().equals(NOMBRE_COLOR_BN)){
					getPrensaTarifaIf().setColor(COLOR_BN);
				}
				
				if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_DOMINGO)){
					getPrensaTarifaIf().setDia(DIA_DOMINGO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_ORDINARIO)){
					getPrensaTarifaIf().setDia(DIA_ORDINARIO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_SABADO)){
					getPrensaTarifaIf().setDia(DIA_SABADO);
				}else if(getCmbDia().getSelectedItem().equals(NOMBRE_DIA_FERIADO)){
					getPrensaTarifaIf().setDia(DIA_FERIADO);
				}
				
				if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_SI)){
					getPrensaTarifaIf().setTarifaCalculada(SI);
				}else if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
					getPrensaTarifaIf().setTarifaCalculada(NO);
				}
				
				BigDecimal recargo = BigDecimal.ZERO;
				if(!getTxtRecargo().getText().equals("")){
					recargo = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRecargo().getText())));
					recargo = recargo.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
					getPrensaTarifaIf().setRecargo(recargo);
				}else{
					getPrensaTarifaIf().setRecargo(BigDecimal.ZERO);
				}
				
				BigDecimal tarifaTotal = BigDecimal.ZERO;
				if(getCmbTCalculada().getSelectedItem().equals(NOMBRE_NO)){
					if(getCmbOperacion().getSelectedItem().equals(NOMBRE_OPERACION_COLUMNASXCM)){
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal cm = prensaFormato.getAltoCm();
						BigDecimal tarifa = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
						tarifaTotal = tarifa.multiply(columnas.multiply(cm));
						getPrensaTarifaIf().setOperacion(OPERACION_COLUMNASXCM);
					}else{
						BigDecimal columnas = prensaFormato.getAnchoColumnas();
						BigDecimal modulos = prensaFormato.getAltoModulos();
						BigDecimal tarifa = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
						tarifaTotal = tarifa.multiply(columnas.multiply(modulos));
						getPrensaTarifaIf().setOperacion(OPERACION_COLUMNASXMODULOS);
					}
				}else{
					tarifaTotal = BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifa().getText())));
				}
				
				if(recargo != BigDecimal.ZERO){
					getPrensaTarifaIf().setTarifa(tarifaTotal.add(tarifaTotal.multiply(recargo)));
				}else{
					getPrensaTarifaIf().setTarifa(tarifaTotal);
				}
				
				SessionServiceLocator.getPrensaTarifaSessionService().savePrensaTarifa(getPrensaTarifaIf());
				getPrensaTarifaColeccion().setElementAt(getPrensaTarifaIf(), getPrensaTarifaSeleccionada());
				setPrensaTarifaIf(null);
				
				SpiritAlert.createAlert("Tarifa actualizada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void clean() {
		prensaTarifaColeccion.clear();
				
		getTxtCodigoTarifa().setText("");
		getCmbDiarioTarifa().setSelectedIndex(-1);
		getCmbDiarioTarifa().repaint();
		getCmbSeccionTarifa().setSelectedIndex(-1);
		getCmbSeccionTarifa().repaint();
		getCmbUbicacionTarifa().setSelectedIndex(-1);
		getCmbUbicacionTarifa().repaint();
		getCmbFormatoTarifa().setSelectedIndex(-1);
		getCmbFormatoTarifa().repaint();
		getCmbColor().setSelectedIndex(-1);
		getCmbColor().repaint();
		getCmbDia().setSelectedIndex(-1);
		getCmbDia().repaint();
		getCmbTCalculada().setSelectedIndex(-1);
		getCmbTCalculada().repaint();
		getTxtTarifa().setText("");
		getTxtRecargo().setText("");
		
		DefaultTableModel model = (DefaultTableModel) this.getTblTarifa().getModel();
		model = (DefaultTableModel) this.getTblTarifa().getModel();
		for(int i= this.getTblTarifa().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getCmbOperacion().setEnabled(false);
		cargarTabla();
		getTxtCodigoTarifa().grabFocus();		
	}
	
	private void cargarTabla() {
		try {
			Collection prensaTarifa = SessionServiceLocator.getPrensaTarifaSessionService().getPrensaTarifaList();
			Iterator prensaTarifaIterator = prensaTarifa.iterator();
			
			if(!prensaTarifaColeccion.isEmpty()){
				prensaTarifaColeccion.removeAllElements();
			}
			
			while (prensaTarifaIterator.hasNext()) {
				PrensaTarifaIf prensaTarifaIf = (PrensaTarifaIf) prensaTarifaIterator.next();
				
				modelTableTarifa = (DefaultTableModel) getTblTarifa().getModel();
				Vector<String> fila = new Vector<String>();
				prensaTarifaColeccion.add(prensaTarifaIf);
				
				agregarColumnasTabla(prensaTarifaIf, fila);
				
				modelTableTarifa.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTarifa(), prensaTarifa, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar tabla Tarifa", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(PrensaTarifaIf prensaTarifaIf, Vector<String> fila){
		try {
			ClienteIf diario = SessionServiceLocator.getClienteSessionService().getCliente(prensaTarifaIf.getClienteId());
			fila.add(diario.getNombreLegal());	
			PrensaSeccionIf prensaSeccion = SessionServiceLocator.getPrensaSeccionSessionService().getPrensaSeccion(prensaTarifaIf.getPrensaSeccionId());
			fila.add(prensaSeccion.getSeccion());
			PrensaUbicacionIf prensaUbicacion = SessionServiceLocator.getPrensaUbicacionSessionService().getPrensaUbicacion(prensaTarifaIf.getPrensaUbicacionId());
			fila.add(prensaUbicacion.getUbicacion());
			PrensaFormatoIf prensaFormato = SessionServiceLocator.getPrensaFormatoSessionService().getPrensaFormato(prensaTarifaIf.getPrensaFormatoId());
			fila.add(prensaFormato.getFormato());
			
			if(prensaTarifaIf.getColor().equals(COLOR_COLOR)){
				fila.add(NOMBRE_COLOR_COLOR);
			}else if(prensaTarifaIf.getColor().equals(COLOR_BN)){
				fila.add(NOMBRE_COLOR_BN);
			}
			
			if(prensaTarifaIf.getDia().equals(DIA_DOMINGO)){
				fila.add(NOMBRE_DIA_DOMINGO);
			}else if(prensaTarifaIf.getDia().equals(DIA_ORDINARIO)){
				fila.add(NOMBRE_DIA_ORDINARIO);
			}else if(prensaTarifaIf.getDia().equals(DIA_SABADO)){
				fila.add(NOMBRE_DIA_SABADO);
			}else if(prensaTarifaIf.getDia().equals(DIA_FERIADO)){
				fila.add(NOMBRE_DIA_FERIADO);
			}
			
			fila.add(formatoDecimal.format(prensaTarifaIf.getTarifa()));
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar filas de tabla Tarifa", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if(getCmbDiarioTarifa().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Diario!", SpiritAlert.INFORMATION);
			getCmbDiarioTarifa().grabFocus();
			return false;
		}
		if(getCmbSeccionTarifa().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar una Sección!", SpiritAlert.INFORMATION);
			getCmbSeccionTarifa().grabFocus();
			return false;
		}
		if(getCmbUbicacionTarifa().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar una Ubicación!", SpiritAlert.INFORMATION);
			getCmbUbicacionTarifa().grabFocus();
			return false;
		}
		if(getCmbFormatoTarifa().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Formato!", SpiritAlert.INFORMATION);
			getCmbFormatoTarifa().grabFocus();
			return false;
		}
		if(getCmbColor().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Color!", SpiritAlert.INFORMATION);
			getCmbColor().grabFocus();
			return false;
		}
		if(getCmbDia().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Día!", SpiritAlert.INFORMATION);
			getCmbDia().grabFocus();
			return false;
		}
		if(getCmbTCalculada().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar si la tarifa ya está calculada!", SpiritAlert.INFORMATION);
			getCmbTCalculada().grabFocus();
			return false;
		}
		if((getTxtTarifa().getText().equals("")) || (getTxtTarifa().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar la Tarifa!", SpiritAlert.INFORMATION);
			getTxtTarifa().grabFocus();
			return false;
		}
		
		if(getCmbOperacion().isEnabled()){
			if(getCmbOperacion().getSelectedItem().equals(NOMBRE_OPERACION_COLUMNASXMODULOS)){
				PrensaFormatoIf prensaFormato = (PrensaFormatoIf) getCmbFormatoTarifa().getSelectedItem();
				if(prensaFormato.getAltoModulos() == null){
					SpiritAlert.createAlert("No se puede guardar la Tarifa porque el formato seleccionado no tiene modulos!",SpiritAlert.WARNING);
					getCmbFormatoTarifa().grabFocus();
					return false;
				}
			}
		}		
				
		return true;
	}
	
	public PrensaTarifaIf getPrensaTarifaIf() {
		return prensaTarifaIf;
	}

	public void setPrensaTarifaIf(PrensaTarifaIf prensaTarifaIf) {
		this.prensaTarifaIf = prensaTarifaIf;
	}

	public int getPrensaTarifaSeleccionada() {
		return prensaTarifaSeleccionada;
	}

	public void setPrensaTarifaSeleccionada(int prensaTarifaSeleccionada) {
		this.prensaTarifaSeleccionada = prensaTarifaSeleccionada;
	}

	public Vector<PrensaTarifaIf> getPrensaTarifaColeccion() {
		return prensaTarifaColeccion;
	}

	public void setPrensaTarifaColeccion(Vector<PrensaTarifaIf> prensaTarifaColeccion) {
		this.prensaTarifaColeccion = prensaTarifaColeccion;
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getCmbDiarioTarifa().grabFocus();
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		cargarCombos();
		getCmbDiarioTarifa().setSelectedIndex(-1);
		getCmbSeccionTarifa().setSelectedIndex(-1);
		getCmbUbicacionTarifa().setSelectedIndex(-1);
		getCmbFormatoTarifa().setSelectedIndex(-1);
		getCmbColor().setSelectedIndex(-1);
		getCmbDia().setSelectedIndex(-1);
		getCmbTCalculada().setSelectedIndex(-1);
		getCmbOperacion().setSelectedIndex(-1);
		getCmbDiarioTarifa().repaint();
		getCmbSeccionTarifa().repaint();
		getCmbUbicacionTarifa().repaint();
		getCmbFormatoTarifa().repaint();
		getCmbColor().repaint();
		getCmbDia().repaint();
		getCmbTCalculada().repaint();
		getCmbOperacion().repaint();		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
}
