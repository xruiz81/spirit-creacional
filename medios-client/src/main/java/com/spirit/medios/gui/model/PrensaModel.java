package com.spirit.medios.gui.model;

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
import java.util.Collections;
import java.util.Comparator;
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
import com.spirit.medios.entity.PrensaFormatoData;
import com.spirit.medios.entity.PrensaFormatoIf;
import com.spirit.medios.entity.PrensaInsertosData;
import com.spirit.medios.entity.PrensaInsertosIf;
import com.spirit.medios.entity.PrensaSeccionData;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.entity.PrensaUbicacionData;
import com.spirit.medios.entity.PrensaUbicacionIf;
import com.spirit.medios.gui.panel.JPPrensa;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class PrensaModel extends JPPrensa{
	
	private static final long serialVersionUID = -6439817587907488374L;
	
	private static final int MAX_LONGITUD_CODIGO = 12;
	private static final int MAX_LONGITUD_SECCION = 50;
	private static final int MAX_LONGITUD_UBICACION = 50;
	private static final int MAX_LONGITUD_FORMATO = 50;
	private static final int MAX_LONGITUD_COLUMNAS = 2;
	private static final int MAX_LONGITUD_MODULOS = 2;
	private static final int MAX_LONGITUD_CM = 6;

	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private Vector<PrensaSeccionIf> prensaSeccionRemovidaColeccion = new Vector<PrensaSeccionIf>();
	private Vector<PrensaUbicacionIf> prensaUbicacionRemovidaColeccion = new Vector<PrensaUbicacionIf>();
	private Vector<PrensaFormatoIf> prensaFormatoRemovidaColeccion = new Vector<PrensaFormatoIf>();
	private Vector<PrensaInsertosIf> prensaInsertosRemovidaColeccion = new Vector<PrensaInsertosIf>();
	private DefaultTableModel modelTableSeccion;
	private DefaultTableModel modelTableUbicacion;
	private DefaultTableModel modelTableFormato;
	private DefaultTableModel modelTableInsertos;
	private Vector<PrensaSeccionIf> prensaSeccionColeccion = new Vector<PrensaSeccionIf>();
	private Vector<PrensaUbicacionIf> prensaUbicacionColeccion = new Vector<PrensaUbicacionIf>();
	private Vector<PrensaFormatoIf> prensaFormatoColeccion = new Vector<PrensaFormatoIf>();
	private Vector<PrensaInsertosIf> prensaInsertosColeccion = new Vector<PrensaInsertosIf>();
	private int prensaSeccionSeleccionada;
	private int prensaUbicacionSeleccionada;
	private int prensaFormatoSeleccionada;
	private int prensaInsertoSeleccionada;
	private PrensaSeccionIf prensaSeccionIf;
	private PrensaUbicacionIf prensaUbicacionIf;
	private PrensaFormatoIf prensaFormatoIf;
	private PrensaInsertosIf prensaInsertosIf;
	private DecimalFormat formatoEntero = new DecimalFormat("000");
	
	public PrensaModel(){
		anchoColumnasTabla();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		setSorterTable(getTblSeccion());
		setSorterTable(getTblUbicacion());
		setSorterTable(getTblFormato());
		setSorterTable(getTblInserto());
		getTblSeccion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblUbicacion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFormato().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblInserto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    getTblSeccion().addMouseListener(oMouseAdapterTblSeccion);
		getTblSeccion().addKeyListener(oKeyAdapterTblSeccion);
		getTblUbicacion().addMouseListener(oMouseAdapterTblUbicacion);
		getTblUbicacion().addKeyListener(oKeyAdapterTblUbicacion);
		getTblFormato().addMouseListener(oMouseAdapterTblFormato);
		getTblFormato().addKeyListener(oKeyAdapterTblFormato);
		getTblInserto().addMouseListener(oMouseAdapterTblInserto);
		getTblInserto().addKeyListener(oKeyAdapterTblInserto);
				
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaSeccion = getTblSeccion().getColumnModel().getColumn(0);
		anchoColumnaSeccion.setPreferredWidth(50);
		anchoColumnaSeccion = getTblSeccion().getColumnModel().getColumn(1);
		anchoColumnaSeccion.setPreferredWidth(200);
		anchoColumnaSeccion = getTblSeccion().getColumnModel().getColumn(2);
		anchoColumnaSeccion.setPreferredWidth(200);
		
		TableColumn anchoColumnaUbicacion = getTblUbicacion().getColumnModel().getColumn(0);
		anchoColumnaUbicacion.setPreferredWidth(50);
		anchoColumnaUbicacion = getTblUbicacion().getColumnModel().getColumn(1);
		anchoColumnaUbicacion.setPreferredWidth(200);
		anchoColumnaUbicacion = getTblUbicacion().getColumnModel().getColumn(2);
		anchoColumnaUbicacion.setPreferredWidth(200);
		
		TableColumn anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(0);
		anchoColumnaFormato.setPreferredWidth(180);
		anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(1);
		anchoColumnaFormato.setPreferredWidth(180);
		anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(2);
		anchoColumnaFormato.setPreferredWidth(40);
		anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(3);
		anchoColumnaFormato.setPreferredWidth(40);
		anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(4);
		anchoColumnaFormato.setPreferredWidth(40);
		anchoColumnaFormato = getTblFormato().getColumnModel().getColumn(5);
		anchoColumnaFormato.setPreferredWidth(40);
		
		TableColumn anchoColumnaInserto = getTblInserto().getColumnModel().getColumn(0);
		anchoColumnaInserto.setPreferredWidth(50);
		anchoColumnaInserto = getTblInserto().getColumnModel().getColumn(1);
		anchoColumnaInserto.setPreferredWidth(300);
		anchoColumnaInserto = getTblInserto().getColumnModel().getColumn(2);
		anchoColumnaInserto.setPreferredWidth(50);
		anchoColumnaInserto = getTblInserto().getColumnModel().getColumn(3);
		anchoColumnaInserto.setPreferredWidth(50);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblFormato().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblFormato().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblFormato().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblFormato().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		
		getTblInserto().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblInserto().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
	}
	
	private void initKeyListeners() {
		getTxtCodigoFormato().setEditable(false);
		getTxtCodigoInserto().setEditable(false);
		getTxtCodigoSeccion().setEditable(false);
		getTxtCodigoUbicacion().setEditable(false);
		
		getTxtCodigoSeccion().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtSeccion().addKeyListener(new TextChecker(MAX_LONGITUD_SECCION));
		
		getTxtCodigoUbicacion().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtUbicacion().addKeyListener(new TextChecker(MAX_LONGITUD_UBICACION));
		
		getTxtCodigoFormato().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtFormato().addKeyListener(new TextChecker(MAX_LONGITUD_FORMATO));
		getTxtAnchoColumnas().addKeyListener(new TextChecker(MAX_LONGITUD_COLUMNAS));
		getTxtAnchoColumnas().addKeyListener(new NumberTextField());
		getTxtAltoModulos().addKeyListener(new TextChecker(MAX_LONGITUD_MODULOS));
		getTxtAltoModulos().addKeyListener(new NumberTextField());
		getTxtAnchoCm().addKeyListener(new TextChecker(MAX_LONGITUD_CM));
		getTxtAnchoCm().addKeyListener(new NumberTextFieldDecimal());
		getTxtAltoCm().addKeyListener(new TextChecker(MAX_LONGITUD_CM));
		getTxtAltoCm().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtCodigoInserto().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtPaginas().addKeyListener(new NumberTextField());
		getTxtTarifaInserto().addKeyListener(new NumberTextFieldDecimal());
	}
	
	Comparator<ClienteIf> ordenadorClientePorNombreLegal = new Comparator<ClienteIf>(){
		public int compare(ClienteIf o1, ClienteIf o2) {
			return o1.getNombreLegal().compareTo(o2.getNombreLegal());
		}		
	};
	
	public void cargarCombos(){
		cargarCombosDiario();
	}

	private void cargarCombosDiario() {
		try {
			Long tipoProveedorPrensa = null;
			//ParametroEmpresaIf parametroTipoProveedorPrensa = SessionServiceLocator.getUtilitariosSessionService().getParametroEmpresa(Parametros.getIdEmpresa(), "MEDIOS", "TIPOPROVEEDORPRENSA", "No se encuetra el parametro TipoProveedorPrensa");
			//Iterator it= SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByNombre(parametroTipoProveedorPrensa.getValor()).iterator();
			Iterator it= SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByNombre("PRENSA").iterator();
			if(it.hasNext()){
				TipoProveedorIf tp = (TipoProveedorIf)it.next();
				tipoProveedorPrensa=tp.getId();
			}
			
			List diarios = (List) SessionServiceLocator.getClienteSessionService().findClienteByTipoproveedorId(tipoProveedorPrensa);
			Collections.sort((ArrayList)diarios,ordenadorClientePorNombreLegal);
			refreshCombo(getCmbDiarioSeccion(), diarios);
			refreshCombo(getCmbDiarioUbicacion(), diarios);
			refreshCombo(getCmbDiarioFormato(), diarios);
			refreshCombo(getCmbDiarioInserto(), diarios);
									
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Diario", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarDetalleSeccion() {
		try {	
			if(validateFieldsSeccion()){
				modelTableSeccion = (DefaultTableModel) getTblSeccion().getModel();
				Vector<String> filaSeccion = new Vector<String>();
					
				PrensaSeccionData data = new PrensaSeccionData();
				
				data.setCodigo(getTxtCodigoSeccion().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioSeccion().getSelectedItem();
				data.setClienteId(diario.getId());
				data.setSeccion(getTxtSeccion().getText());
				
				prensaSeccionColeccion.add(data);

				filaSeccion.add(getTxtCodigoSeccion().getText());
				filaSeccion.add(getCmbDiarioSeccion().getSelectedItem().toString());
				filaSeccion.add(getTxtSeccion().getText());

				modelTableSeccion.addRow(filaSeccion);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible agregar la Sección!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleSeccion() {
		try {
			int filaSeleccionada = getTblSeccion().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(validateFieldsSeccion()){
					modelTableSeccion = (DefaultTableModel) getTblSeccion().getModel();
					Vector<String> filaSeccion = new Vector<String>();
						
					PrensaSeccionData data = new PrensaSeccionData();
					
					data.setCodigo(getTxtCodigoSeccion().getText());
					ClienteIf diario = (ClienteIf) getCmbDiarioSeccion().getSelectedItem();
					data.setClienteId(diario.getId());
					data.setSeccion(getTxtSeccion().getText());
					
					prensaSeccionColeccion.add(filaSeleccionada, data);
					prensaSeccionColeccion.remove(filaSeleccionada + 1);
					
					filaSeccion.add(getTxtCodigoSeccion().getText());
					filaSeccion.add(getCmbDiarioSeccion().getSelectedItem().toString());
					filaSeccion.add(getTxtSeccion().getText());

					modelTableSeccion.insertRow(filaSeleccionada, filaSeccion);
					modelTableSeccion.removeRow(filaSeleccionada + 1);
				}
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible actualizar la Sección!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleSeccion() {
		int filaSeleccionada = getTblSeccion().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				prensaSeccionRemovidaColeccion.add(prensaSeccionColeccion.get(filaSeleccionada));
				prensaSeccionColeccion.remove(filaSeleccionada);
				modelTableSeccion.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void agregarDetalleUbicacion() {
		try {	
			if(validateFieldsUbicacion()){
				modelTableUbicacion = (DefaultTableModel) getTblUbicacion().getModel();
				Vector<String> filaUbicacion = new Vector<String>();
					
				PrensaUbicacionData data = new PrensaUbicacionData();
				
				data.setCodigo(getTxtCodigoUbicacion().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioUbicacion().getSelectedItem();
				data.setClienteId(diario.getId());
				data.setUbicacion(getTxtUbicacion().getText());
				
				prensaUbicacionColeccion.add(data);

				filaUbicacion.add(getTxtCodigoUbicacion().getText());
				filaUbicacion.add(getCmbDiarioUbicacion().getSelectedItem().toString());
				filaUbicacion.add(getTxtUbicacion().getText());

				modelTableUbicacion.addRow(filaUbicacion);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible agregar la Ubicación!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleUbicacion() {
		try {
			int filaSeleccionada = getTblUbicacion().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(validateFieldsUbicacion()){
					modelTableUbicacion = (DefaultTableModel) getTblUbicacion().getModel();
					Vector<String> filaUbicacion = new Vector<String>();
						
					PrensaUbicacionData data = new PrensaUbicacionData();
					
					data.setCodigo(getTxtCodigoUbicacion().getText());
					ClienteIf diario = (ClienteIf) getCmbDiarioUbicacion().getSelectedItem();
					data.setClienteId(diario.getId());
					data.setUbicacion(getTxtUbicacion().getText());
					
					prensaUbicacionColeccion.add(filaSeleccionada, data);
					prensaUbicacionColeccion.remove(filaSeleccionada + 1);
					
					filaUbicacion.add(getTxtCodigoUbicacion().getText());
					filaUbicacion.add(getCmbDiarioUbicacion().getSelectedItem().toString());
					filaUbicacion.add(getTxtUbicacion().getText());

					modelTableUbicacion.insertRow(filaSeleccionada, filaUbicacion);
					modelTableUbicacion.removeRow(filaSeleccionada + 1);
				}
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible actualizar la Ubicación!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleUbicacion() {
		int filaSeleccionada = getTblUbicacion().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				prensaUbicacionRemovidaColeccion.add(prensaUbicacionColeccion.get(filaSeleccionada));
				prensaUbicacionColeccion.remove(filaSeleccionada);
				modelTableUbicacion.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void agregarDetalleFormato() {
		try {	
			if(validateFieldsFormato()){
				modelTableFormato = (DefaultTableModel) getTblFormato().getModel();
				Vector<String> filaFormato = new Vector<String>();
					
				PrensaFormatoData data = new PrensaFormatoData();
				
				data.setCodigo(getTxtCodigoFormato().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioFormato().getSelectedItem();
				data.setClienteId(diario.getId());
				data.setFormato(getTxtFormato().getText());
				if(!getTxtAnchoColumnas().getText().equals("")) data.setAnchoColumnas(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoColumnas().getText())));
				if(!getTxtAltoModulos().getText().equals("")) data.setAltoModulos(BigDecimal.valueOf(Double.parseDouble(getTxtAltoModulos().getText())));
				if(!getTxtAnchoCm().getText().equals("")) data.setAnchoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoCm().getText())));
				if(!getTxtAltoCm().getText().equals("")) data.setAltoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAltoCm().getText())));
				
				prensaFormatoColeccion.add(data);

				filaFormato.add(getCmbDiarioFormato().getSelectedItem().toString());
				filaFormato.add(getTxtFormato().getText());
				if(!getTxtAnchoColumnas().getText().equals("")) filaFormato.add(getTxtAnchoColumnas().getText());
				if(!getTxtAltoModulos().getText().equals("")) filaFormato.add(getTxtAltoModulos().getText());
				if(!getTxtAnchoCm().getText().equals("")) filaFormato.add(getTxtAnchoCm().getText());
				if(!getTxtAltoCm().getText().equals("")) filaFormato.add(getTxtAltoCm().getText());
				
				modelTableFormato.addRow(filaFormato);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible agregar el Formato!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleFormato() {
		try {
			int filaSeleccionada = getTblFormato().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(validateFieldsFormato()){
					modelTableFormato= (DefaultTableModel) getTblFormato().getModel();
					Vector<String> filaFormato = new Vector<String>();
						
					PrensaFormatoData data = new PrensaFormatoData();
					
					data.setCodigo(getTxtCodigoFormato().getText());
					ClienteIf diario = (ClienteIf) getCmbDiarioFormato().getSelectedItem();
					data.setClienteId(diario.getId());
					data.setFormato(getTxtFormato().getText());
					if(!getTxtAnchoColumnas().getText().equals("")) data.setAnchoColumnas(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtAnchoColumnas().getText()))));
					if(!getTxtAltoModulos().getText().equals("")) data.setAltoModulos(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtAltoModulos().getText()))));
					if(!getTxtAnchoCm().getText().equals("")) data.setAnchoCm(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtAnchoCm().getText()))));
					if(!getTxtAltoCm().getText().equals("")) data.setAltoCm(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtAltoCm().getText()))));
									
					prensaFormatoColeccion.add(filaSeleccionada, data);
					prensaFormatoColeccion.remove(filaSeleccionada + 1);
					
					filaFormato.add(getCmbDiarioFormato().getSelectedItem().toString());
					filaFormato.add(getTxtFormato().getText());
					if(!getTxtAnchoColumnas().getText().equals("")) filaFormato.add(getTxtAnchoColumnas().getText());
					if(!getTxtAltoModulos().getText().equals("")) filaFormato.add(getTxtAltoModulos().getText());
					if(!getTxtAnchoCm().getText().equals("")) filaFormato.add(getTxtAnchoCm().getText());
					if(!getTxtAltoCm().getText().equals("")) filaFormato.add(getTxtAltoCm().getText());
					
					modelTableFormato.insertRow(filaSeleccionada, filaFormato);
					modelTableFormato.removeRow(filaSeleccionada + 1);
				}
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible actualizar el Formato!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleFormato() {
		int filaSeleccionada = getTblFormato().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				prensaFormatoRemovidaColeccion.add(prensaFormatoColeccion.get(filaSeleccionada));
				prensaFormatoColeccion.remove(filaSeleccionada);
				modelTableFormato.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void agregarDetalleInsertos() {
		try {	
			if(validateFieldsInsertos()){
				modelTableInsertos = (DefaultTableModel) getTblInserto().getModel();
				Vector<String> filaInsertos = new Vector<String>();
					
				PrensaInsertosData data = new PrensaInsertosData();
				
				data.setCodigo(getTxtCodigoInserto().getText());
				ClienteIf diario = (ClienteIf) getCmbDiarioInserto().getSelectedItem();
				data.setClienteId(diario.getId());
				data.setMaxPaginas(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPaginas().getText()))));
				data.setTarifa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifaInserto().getText()))));
								
				prensaInsertosColeccion.add(data);

				filaInsertos.add(getTxtCodigoInserto().getText());
				filaInsertos.add(getCmbDiarioInserto().getSelectedItem().toString());
				filaInsertos.add(getTxtPaginas().getText());
				filaInsertos.add(getTxtTarifaInserto().getText());
				
				modelTableInsertos.addRow(filaInsertos);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible agregar el Inserto!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void actualizarDetalleInsertos() {
		try {
			int filaSeleccionada = getTblInserto().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(validateFieldsInsertos()){
					modelTableInsertos = (DefaultTableModel) getTblInserto().getModel();
					Vector<String> filaInsertos = new Vector<String>();
						
					PrensaInsertosData data = new PrensaInsertosData();
					
					data.setCodigo(getTxtCodigoInserto().getText());
					ClienteIf diario = (ClienteIf) getCmbDiarioInserto().getSelectedItem();
					data.setClienteId(diario.getId());
					data.setMaxPaginas(BigDecimal.valueOf(Double.parseDouble(getTxtPaginas().getText())));
					data.setTarifa(BigDecimal.valueOf(Double.parseDouble(getTxtTarifaInserto().getText())));
					
					prensaInsertosColeccion.add(filaSeleccionada, data);
					prensaInsertosColeccion.remove(filaSeleccionada + 1);
					
					filaInsertos.add(getTxtCodigoInserto().getText());
					filaInsertos.add(getCmbDiarioInserto().getSelectedItem().toString());
					filaInsertos.add(getTxtPaginas().getText());
					filaInsertos.add(getTxtTarifaInserto().getText());
					
					modelTableInsertos.insertRow(filaSeleccionada, filaInsertos);
					modelTableInsertos.removeRow(filaSeleccionada + 1);
				}
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("No fue posible actualizar el Inserto!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarDetalleInsertos() {
		int filaSeleccionada = getTblInserto().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				prensaInsertosRemovidaColeccion.add(prensaInsertosColeccion.get(filaSeleccionada));
				prensaInsertosColeccion.remove(filaSeleccionada);
				modelTableInsertos.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	MouseListener oMouseAdapterTblSeccion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedSeccionRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblSeccion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedSeccionRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedSeccionRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPrensaSeccionSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			prensaSeccionIf = (PrensaSeccionIf)  getPrensaSeccionColeccion().get(getPrensaSeccionSeleccionada());
			getTxtCodigoSeccion().setText(prensaSeccionIf.getCodigo());
			getTxtSeccion().setText(prensaSeccionIf.getSeccion());
			getCmbDiarioSeccion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDiarioSeccion(), prensaSeccionIf.getClienteId()));
			getCmbDiarioSeccion().repaint();
			showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblUbicacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedUbicacionRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblUbicacion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedUbicacionRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedUbicacionRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPrensaUbicacionSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			prensaUbicacionIf = (PrensaUbicacionIf)  getPrensaUbicacionColeccion().get(getPrensaUbicacionSeleccionada());
			getTxtCodigoUbicacion().setText(prensaUbicacionIf.getCodigo());
			getTxtUbicacion().setText(prensaUbicacionIf.getUbicacion());
			getCmbDiarioUbicacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDiarioUbicacion(), prensaUbicacionIf.getClienteId()));
			getCmbDiarioUbicacion().repaint();
			showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblFormato = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedFormatoRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblFormato = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedFormatoRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedFormatoRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPrensaFormatoSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			prensaFormatoIf = (PrensaFormatoIf)  getPrensaFormatoColeccion().get(getPrensaFormatoSeleccionada());
			getTxtCodigoFormato().setText(prensaFormatoIf.getCodigo());
			getTxtFormato().setText(prensaFormatoIf.getFormato());
			getCmbDiarioFormato().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDiarioFormato(), prensaFormatoIf.getClienteId()));
			getCmbDiarioFormato().repaint();
			
			if(prensaFormatoIf.getAnchoColumnas() != null){
				getTxtAnchoColumnas().setText(prensaFormatoIf.getAnchoColumnas().toString());
			}else{
				getTxtAnchoColumnas().setText("");
			}
			if(prensaFormatoIf.getAltoModulos() != null){
				getTxtAltoModulos().setText(prensaFormatoIf.getAltoModulos().toString());
			}
			else{
				getTxtAltoModulos().setText("");
			}
			if(prensaFormatoIf.getAnchoCm() != null){
				getTxtAnchoCm().setText(prensaFormatoIf.getAnchoCm().toString());
			}
			else{
				getTxtAnchoCm().setText("");
			}
			if(prensaFormatoIf.getAltoCm() != null){
				getTxtAltoCm().setText(prensaFormatoIf.getAltoCm().toString());
			}else{
				getTxtAltoCm().setText("");
			}
			showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblInserto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedInsertoRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblInserto = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedInsertoRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedInsertoRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPrensaInsertoSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			prensaInsertosIf = (PrensaInsertosIf)  getPrensaInsertosColeccion().get(getPrensaInsertoSeleccionada());
			getTxtCodigoInserto().setText(prensaInsertosIf.getCodigo());
			getCmbDiarioInserto().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDiarioInserto(), prensaInsertosIf.getClienteId()));
			getCmbDiarioInserto().repaint();
			getTxtPaginas().setText(prensaInsertosIf.getMaxPaginas().toString());
			getTxtTarifaInserto().setText(prensaInsertosIf.getTarifa().toString());
			showUpdateMode();
		}
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		try {
			if ((getJtpPrensa().getSelectedIndex() == 0) && (validateFieldsSeccion())) {
				PrensaSeccionData data = new PrensaSeccionData();
				
				ClienteIf diario = (ClienteIf) getCmbDiarioSeccion().getSelectedItem();
				String codigo = SessionServiceLocator.getPrensaSeccionSessionService().getMaximoCodigoPrensaSeccion(diario.getId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				data.setCodigo(codigo);				
				data.setClienteId(diario.getId());
				data.setSeccion(getTxtSeccion().getText());
				
				SessionServiceLocator.getPrensaSeccionSessionService().addPrensaSeccion(data);
				
				SpiritAlert.createAlert("Prensa Sección guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(0);
				getTxtCodigoSeccion().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 1) && (validateFieldsUbicacion())) {
				PrensaUbicacionData data = new PrensaUbicacionData();
				
				ClienteIf diario = (ClienteIf) getCmbDiarioUbicacion().getSelectedItem();
				String codigo = SessionServiceLocator.getPrensaUbicacionSessionService().getMaximoCodigoPrensaUbicacion(diario.getId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				data.setCodigo(codigo);
				data.setClienteId(diario.getId());
				data.setUbicacion(getTxtUbicacion().getText());
				
				SessionServiceLocator.getPrensaUbicacionSessionService().addPrensaUbicacion(data);
				
				SpiritAlert.createAlert("Prensa Ubicación guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(1);
				getTxtCodigoUbicacion().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 2) && (validateFieldsFormato())) {
				PrensaFormatoData data = new PrensaFormatoData();
				
				ClienteIf diario = (ClienteIf) getCmbDiarioFormato().getSelectedItem();
				String codigo = SessionServiceLocator.getPrensaFormatoSessionService().getMaximoCodigoPrensaFormato(diario.getId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				data.setCodigo(codigo);
				data.setClienteId(diario.getId());
				data.setFormato(getTxtFormato().getText());
				if(!getTxtAnchoColumnas().getText().equals("")) data.setAnchoColumnas(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoColumnas().getText())));
				if(!getTxtAltoModulos().getText().equals("")) data.setAltoModulos(BigDecimal.valueOf(Double.parseDouble(getTxtAltoModulos().getText())));
				if(!getTxtAnchoCm().getText().equals("")) data.setAnchoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoCm().getText())));
				if(!getTxtAltoCm().getText().equals("")) data.setAltoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAltoCm().getText())));
								
				SessionServiceLocator.getPrensaFormatoSessionService().addPrensaFormato(data);
				
				SpiritAlert.createAlert("Prensa Formato guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(2);
				getTxtCodigoFormato().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 3) && (validateFieldsInsertos())) {
				PrensaInsertosData data = new PrensaInsertosData();
				
				ClienteIf diario = (ClienteIf) getCmbDiarioInserto().getSelectedItem();
				String codigo = SessionServiceLocator.getPrensaInsertosSessionService().getMaximoCodigoPrensaInsertos(diario.getId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				data.setCodigo(codigo);
				data.setClienteId(diario.getId());
				data.setMaxPaginas(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPaginas().getText()))));
				data.setTarifa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTarifaInserto().getText()))));
								
				SessionServiceLocator.getPrensaInsertosSessionService().addPrensaInsertos(data);
				
				SpiritAlert.createAlert("Prensa Inserto guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(3);
				getTxtCodigoInserto().grabFocus();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			if ((getJtpPrensa().getSelectedIndex() == 0) && (validateFieldsSeccion())) {
				setPrensaSeccionIf((PrensaSeccionIf) getPrensaSeccionColeccion().get(getPrensaSeccionSeleccionada()));
				getPrensaSeccionIf().setCodigo(getTxtCodigoSeccion().getText());
				getPrensaSeccionIf().setSeccion(getTxtSeccion().getText());
				getPrensaSeccionIf().setClienteId(((ClienteIf) getCmbDiarioSeccion().getSelectedItem()).getId());
				
				SessionServiceLocator.getPrensaSeccionSessionService().savePrensaSeccion(getPrensaSeccionIf());
				getPrensaSeccionColeccion().setElementAt(getPrensaSeccionIf(), getPrensaSeccionSeleccionada());
				setPrensaSeccionIf(null);
								
				SpiritAlert.createAlert("Prensa Sección actualizada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(0);
				getTxtCodigoSeccion().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 1) && (validateFieldsUbicacion())) {
				setPrensaUbicacionIf((PrensaUbicacionIf) getPrensaUbicacionColeccion().get(getPrensaUbicacionSeleccionada()));
				getPrensaUbicacionIf().setCodigo(getTxtCodigoUbicacion().getText());
				getPrensaUbicacionIf().setUbicacion(getTxtUbicacion().getText());
				getPrensaUbicacionIf().setClienteId(((ClienteIf) getCmbDiarioUbicacion().getSelectedItem()).getId());
				
				SessionServiceLocator.getPrensaUbicacionSessionService().savePrensaUbicacion(getPrensaUbicacionIf());
				getPrensaUbicacionColeccion().setElementAt(getPrensaUbicacionIf(), getPrensaUbicacionSeleccionada());
				setPrensaUbicacionIf(null);
								
				SpiritAlert.createAlert("Prensa Ubicación actualizada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(1);
				getTxtCodigoUbicacion().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 2) && (validateFieldsFormato())) {
				setPrensaFormatoIf((PrensaFormatoIf) getPrensaFormatoColeccion().get(getPrensaFormatoSeleccionada()));
				getPrensaFormatoIf().setCodigo(getTxtCodigoFormato().getText());
				getPrensaFormatoIf().setFormato(getTxtFormato().getText());
				getPrensaFormatoIf().setClienteId(((ClienteIf) getCmbDiarioFormato().getSelectedItem()).getId());
				
				if(!getTxtAnchoColumnas().getText().equals("")) getPrensaFormatoIf().setAnchoColumnas(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoColumnas().getText())));
				if(!getTxtAltoModulos().getText().equals("")) getPrensaFormatoIf().setAltoModulos(BigDecimal.valueOf(Double.parseDouble(getTxtAltoModulos().getText())));
				if(!getTxtAnchoCm().getText().equals("")) getPrensaFormatoIf().setAnchoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAnchoCm().getText())));
				if(!getTxtAltoCm().getText().equals("")) getPrensaFormatoIf().setAltoCm(BigDecimal.valueOf(Double.parseDouble(getTxtAltoCm().getText())));
								
				SessionServiceLocator.getPrensaFormatoSessionService().savePrensaFormato(getPrensaFormatoIf());
				getPrensaFormatoColeccion().setElementAt(getPrensaFormatoIf(), getPrensaFormatoSeleccionada());
				setPrensaFormatoIf(null);
								
				SpiritAlert.createAlert("Prensa Formato actualizada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(2);
				getTxtCodigoFormato().grabFocus();
			}
			else if ((getJtpPrensa().getSelectedIndex() == 3) && (validateFieldsInsertos())) {
				setPrensaInsertosIf((PrensaInsertosIf) getPrensaInsertosColeccion().get(getPrensaInsertoSeleccionada()));
				getPrensaInsertosIf().setCodigo(getTxtCodigoInserto().getText());
				getPrensaInsertosIf().setClienteId(((ClienteIf) getCmbDiarioInserto().getSelectedItem()).getId());
				getPrensaInsertosIf().setMaxPaginas(BigDecimal.valueOf(Double.parseDouble(getTxtPaginas().getText())));
				getPrensaInsertosIf().setTarifa(BigDecimal.valueOf(Double.parseDouble(getTxtTarifaInserto().getText())));
												
				SessionServiceLocator.getPrensaInsertosSessionService().savePrensaInsertos(getPrensaInsertosIf());
				getPrensaInsertosColeccion().setElementAt(getPrensaInsertosIf(), getPrensaInsertoSeleccionada());
				setPrensaInsertosIf(null);
								
				SpiritAlert.createAlert("Prensa Inserto actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(3);
				getTxtCodigoInserto().grabFocus();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}

	public void delete() {
		try {
			if (getJtpPrensa().getSelectedIndex() == 0){
				PrensaSeccionIf prensaSeccionIf = (PrensaSeccionIf) getPrensaSeccionColeccion().get(getPrensaSeccionSeleccionada());
				SessionServiceLocator.getPrensaSeccionSessionService().deletePrensaSeccion(prensaSeccionIf.getId());
				SpiritAlert.createAlert("Prensa Sección eliminada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(0);
				getTxtCodigoSeccion().grabFocus();
			}
			else if (getJtpPrensa().getSelectedIndex() == 1){
				PrensaUbicacionIf prensaUbicacionIf = (PrensaUbicacionIf) getPrensaUbicacionColeccion().get(getPrensaUbicacionSeleccionada());
				SessionServiceLocator.getPrensaUbicacionSessionService().deletePrensaUbicacion(prensaUbicacionIf.getId());
				SpiritAlert.createAlert("Prensa Ubicación eliminada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(1);
				getTxtCodigoUbicacion().grabFocus();
			}
			else if (getJtpPrensa().getSelectedIndex() == 2){
				PrensaFormatoIf prensaFormatoIf = (PrensaFormatoIf) getPrensaFormatoColeccion().get(getPrensaFormatoSeleccionada());
				SessionServiceLocator.getPrensaFormatoSessionService().deletePrensaFormato(prensaFormatoIf.getId());
				SpiritAlert.createAlert("Prensa Formato eliminado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(2);
				getTxtCodigoFormato().grabFocus();
			}
			else if (getJtpPrensa().getSelectedIndex() == 3){
				PrensaInsertosIf prensaInsertosIf = (PrensaInsertosIf) getPrensaInsertosColeccion().get(getPrensaInsertoSeleccionada());
				SessionServiceLocator.getPrensaInsertosSessionService().deletePrensaInsertos(prensaInsertosIf.getId());
				SpiritAlert.createAlert("Prensa Inserto eliminado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
				getJtpPrensa().setSelectedIndex(3);
				getTxtCodigoInserto().grabFocus();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al eliminar la información, puede estar siendo utilizada!",SpiritAlert.ERROR);
			e.printStackTrace();
		}	
	}

	public void clean() {
		prensaSeccionColeccion.clear();
		prensaUbicacionColeccion.clear();
		prensaFormatoColeccion.clear();
		prensaInsertosColeccion.clear();		
		
		getTxtCodigoSeccion().setText("");
		getCmbDiarioSeccion().setSelectedIndex(-1);
		getTxtSeccion().setText("");
				
		getTxtCodigoUbicacion().setText("");
		getCmbDiarioUbicacion().setSelectedIndex(-1);
		getTxtUbicacion().setText("");
				
		getTxtCodigoFormato().setText("");
		getCmbDiarioFormato().setSelectedIndex(-1);
		getTxtFormato().setText("");
		getTxtAnchoColumnas().setText("");
		getTxtAltoModulos().setText("");
		getTxtAnchoCm().setText("");
		getTxtAltoCm().setText("");		
		
		getTxtCodigoInserto().setText("");
		getCmbDiarioInserto().setSelectedIndex(-1);
		getTxtPaginas().setText("");
		getTxtTarifaInserto().setText("");		
		
		DefaultTableModel model = (DefaultTableModel) this.getTblSeccion().getModel();
		for(int i= this.getTblSeccion().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		model = (DefaultTableModel) this.getTblUbicacion().getModel();
		for(int i= this.getTblUbicacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		model = (DefaultTableModel) this.getTblFormato().getModel();
		for(int i= this.getTblFormato().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		model = (DefaultTableModel) this.getTblInserto().getModel();
		for(int i= this.getTblInserto().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		return true;
	}
	
	public boolean validateFieldsSeccion(){
		if(getCmbDiarioSeccion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Diario!", SpiritAlert.INFORMATION);
			getCmbDiarioSeccion().grabFocus();
			return false;
		}
		if((getTxtSeccion().getText().equals("")) || (getTxtSeccion().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar la Sección!", SpiritAlert.INFORMATION);
			getTxtSeccion().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsUbicacion(){
		if(getCmbDiarioUbicacion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Diario!", SpiritAlert.INFORMATION);
			getCmbDiarioUbicacion().grabFocus();
			return false;
		}
		if((getTxtUbicacion().getText().equals("")) || (getTxtUbicacion().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar la Ubicación!", SpiritAlert.INFORMATION);
			getTxtSeccion().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsFormato(){
		if(getCmbDiarioFormato().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Diario!", SpiritAlert.INFORMATION);
			getCmbDiarioFormato().grabFocus();
			return false;
		}
		if((getTxtFormato().getText().equals("")) || (getTxtFormato().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar el Formato!", SpiritAlert.INFORMATION);
			getTxtFormato().grabFocus();
			return false;
		}
		if((getTxtAnchoColumnas().getText().equals("")) || (getTxtAnchoColumnas().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar el Ancho(Columnas)!", SpiritAlert.INFORMATION);
			getTxtAnchoColumnas().grabFocus();
			return false;
		}
		if((getTxtAnchoCm().getText().equals("")) || (getTxtAnchoCm().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar el Ancho(Cm.)!", SpiritAlert.INFORMATION);
			getTxtAnchoCm().grabFocus();
			return false;
		}
		if((getTxtAltoCm().getText().equals("")) || (getTxtAltoCm().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar el Alto(Cm.)!", SpiritAlert.INFORMATION);
			getTxtAltoCm().grabFocus();
			return false;
		}
				
		return true;
	}
	
	public boolean validateFieldsInsertos(){
		if(getCmbDiarioInserto().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Diario!", SpiritAlert.INFORMATION);
			getCmbDiarioInserto().grabFocus();
			return false;
		}
		if((getTxtPaginas().getText().equals("")) || (getTxtPaginas().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar el Máximo de Páginas del inserto!", SpiritAlert.INFORMATION);
			getTxtPaginas().grabFocus();
			return false;
		}
		if((getTxtTarifaInserto().getText().equals("")) || (getTxtTarifaInserto().getText() == null)){
			SpiritAlert.createAlert("Debe ingresar la Tarifa del inserto!", SpiritAlert.INFORMATION);
			getTxtTarifaInserto().grabFocus();
			return false;
		}
						
		return true;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTablaSeccion();
		cargarTablaUbicacion();
		cargarTablaFormato();
		cargarTablaInsertos();
		getJtpPrensa().setSelectedIndex(0);
		getTxtCodigoSeccion().grabFocus();
	}
	
	private void cargarTablaSeccion() {
		try {
			Collection prensaSeccion = SessionServiceLocator.getPrensaSeccionSessionService().getPrensaSeccionList();
			Iterator prensaSeccionIterator = prensaSeccion.iterator();
			
			if(!prensaSeccionColeccion.isEmpty()){
				prensaSeccionColeccion.removeAllElements();
			}
			
			while (prensaSeccionIterator.hasNext()) {
				PrensaSeccionIf prensaSeccionIf = (PrensaSeccionIf) prensaSeccionIterator.next();
				
				modelTableSeccion = (DefaultTableModel) getTblSeccion().getModel();
				Vector<String> fila = new Vector<String>();
				prensaSeccionColeccion.add(prensaSeccionIf);
				
				agregarColumnasTablaSeccion(prensaSeccionIf, fila);
				
				modelTableSeccion.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar tabla Sección", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTablaSeccion(PrensaSeccionIf prensaSeccionIf, Vector<String> fila){
		try {
			fila.add(prensaSeccionIf.getCodigo());
			ClienteIf diario = SessionServiceLocator.getClienteSessionService().getCliente(prensaSeccionIf.getClienteId());
			fila.add(diario.getNombreLegal());	
			fila.add(prensaSeccionIf.getSeccion());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar filas de tabla Sección", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarTablaUbicacion() {
		try {
			Collection prensaUbicacion = SessionServiceLocator.getPrensaUbicacionSessionService().getPrensaUbicacionList();
			Iterator prensaUbicacionIterator = prensaUbicacion.iterator();
			
			if(!prensaUbicacionColeccion.isEmpty()){
				prensaUbicacionColeccion.removeAllElements();
			}
			
			while (prensaUbicacionIterator.hasNext()) {
				PrensaUbicacionIf prensaUbicacionIf = (PrensaUbicacionIf) prensaUbicacionIterator.next();
				
				modelTableUbicacion = (DefaultTableModel) getTblUbicacion().getModel();
				Vector<String> fila = new Vector<String>();
				prensaUbicacionColeccion.add(prensaUbicacionIf);
				
				agregarColumnasTablaUbicacion(prensaUbicacionIf, fila);
				
				modelTableUbicacion.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar tabla Ubicación", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTablaUbicacion(PrensaUbicacionIf prensaUbicacionIf, Vector<String> fila){
		try {
			fila.add(prensaUbicacionIf.getCodigo());
			ClienteIf diario = SessionServiceLocator.getClienteSessionService().getCliente(prensaUbicacionIf.getClienteId());
			fila.add(diario.getNombreLegal());	
			fila.add(prensaUbicacionIf.getUbicacion());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar filas de tabla Ubicación", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarTablaFormato() {
		try {
			Collection prensaFormato = SessionServiceLocator.getPrensaFormatoSessionService().getPrensaFormatoList();
			Iterator prensaFormatoIterator = prensaFormato.iterator();
			
			if(!prensaFormatoColeccion.isEmpty()){
				prensaFormatoColeccion.removeAllElements();
			}
			
			while (prensaFormatoIterator.hasNext()) {
				PrensaFormatoIf prensaFormatoIf = (PrensaFormatoIf) prensaFormatoIterator.next();
				
				modelTableFormato = (DefaultTableModel) getTblFormato().getModel();
				Vector<String> fila = new Vector<String>();
				prensaFormatoColeccion.add(prensaFormatoIf);
				
				agregarColumnasTablaFormato(prensaFormatoIf, fila);
				
				modelTableFormato.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar tabla Formato", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTablaFormato(PrensaFormatoIf prensaFormatoIf, Vector<String> fila){
		try {
			//fila.add(prensaFormatoIf.getCodigo());
			ClienteIf diario = SessionServiceLocator.getClienteSessionService().getCliente(prensaFormatoIf.getClienteId());
			fila.add(diario.getNombreLegal());	
			fila.add(prensaFormatoIf.getFormato());
			if(prensaFormatoIf.getAnchoColumnas() != null){
				fila.add(prensaFormatoIf.getAnchoColumnas().toString());
			}else{
				fila.add("");
			}
			if(prensaFormatoIf.getAltoModulos() != null){
				fila.add(prensaFormatoIf.getAltoModulos().toString());
			}else{
				fila.add("");
			}
			if(prensaFormatoIf.getAnchoCm() != null){
				fila.add(prensaFormatoIf.getAnchoCm().toString());
			}else{
				fila.add("");
			}
			if(prensaFormatoIf.getAltoCm() != null){
				fila.add(prensaFormatoIf.getAltoCm().toString());
			}else{
				fila.add("");
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar filas de tabla Formato", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarTablaInsertos() {
		try {
			Collection prensaInsertos = SessionServiceLocator.getPrensaInsertosSessionService().getPrensaInsertosList();
			Iterator prensaInsertosIterator = prensaInsertos.iterator();
			
			if(!prensaInsertosColeccion.isEmpty()){
				prensaInsertosColeccion.removeAllElements();
			}
			
			while (prensaInsertosIterator.hasNext()) {
				PrensaInsertosIf prensaInsertosIf = (PrensaInsertosIf) prensaInsertosIterator.next();
				
				modelTableInsertos = (DefaultTableModel) getTblInserto().getModel();
				Vector<String> fila = new Vector<String>();
				prensaInsertosColeccion.add(prensaInsertosIf);
				
				agregarColumnasTablaInsertos(prensaInsertosIf, fila);
				
				modelTableInsertos.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar tabla Insertos", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTablaInsertos(PrensaInsertosIf prensaInsertosIf, Vector<String> fila){
		try {
			fila.add(prensaInsertosIf.getCodigo());
			ClienteIf diario = SessionServiceLocator.getClienteSessionService().getCliente(prensaInsertosIf.getClienteId());
			fila.add(diario.getNombreLegal());	
			fila.add(prensaInsertosIf.getMaxPaginas().toString());
			fila.add(prensaInsertosIf.getTarifa().toString());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar filas de tabla Insertos", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		setUpdateMode();
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
		cargarCombosDiario();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public Vector<PrensaFormatoIf> getPrensaFormatoColeccion() {
		return prensaFormatoColeccion;
	}

	public void setPrensaFormatoColeccion(
			Vector<PrensaFormatoIf> prensaFormatoColeccion) {
		this.prensaFormatoColeccion = prensaFormatoColeccion;
	}

	public PrensaFormatoIf getPrensaFormatoIf() {
		return prensaFormatoIf;
	}

	public void setPrensaFormatoIf(PrensaFormatoIf prensaFormatoIf) {
		this.prensaFormatoIf = prensaFormatoIf;
	}

	public int getPrensaFormatoSeleccionada() {
		return prensaFormatoSeleccionada;
	}

	public void setPrensaFormatoSeleccionada(int prensaFormatoSeleccionada) {
		this.prensaFormatoSeleccionada = prensaFormatoSeleccionada;
	}

	public Vector<PrensaInsertosIf> getPrensaInsertosColeccion() {
		return prensaInsertosColeccion;
	}

	public void setPrensaInsertosColeccion(
			Vector<PrensaInsertosIf> prensaInsertosColeccion) {
		this.prensaInsertosColeccion = prensaInsertosColeccion;
	}

	public int getPrensaInsertoSeleccionada() {
		return prensaInsertoSeleccionada;
	}

	public void setPrensaInsertoSeleccionada(int prensaInsertoSeleccionada) {
		this.prensaInsertoSeleccionada = prensaInsertoSeleccionada;
	}

	public PrensaInsertosIf getPrensaInsertosIf() {
		return prensaInsertosIf;
	}

	public void setPrensaInsertosIf(PrensaInsertosIf prensaInsertosIf) {
		this.prensaInsertosIf = prensaInsertosIf;
	}

	public Vector<PrensaSeccionIf> getPrensaSeccionColeccion() {
		return prensaSeccionColeccion;
	}

	public void setPrensaSeccionColeccion(
			Vector<PrensaSeccionIf> prensaSeccionColeccion) {
		this.prensaSeccionColeccion = prensaSeccionColeccion;
	}

	public PrensaSeccionIf getPrensaSeccionIf() {
		return prensaSeccionIf;
	}

	public void setPrensaSeccionIf(PrensaSeccionIf prensaSeccionIf) {
		this.prensaSeccionIf = prensaSeccionIf;
	}

	public int getPrensaSeccionSeleccionada() {
		return prensaSeccionSeleccionada;
	}

	public void setPrensaSeccionSeleccionada(int prensaSeccionSeleccionada) {
		this.prensaSeccionSeleccionada = prensaSeccionSeleccionada;
	}

	public Vector<PrensaUbicacionIf> getPrensaUbicacionColeccion() {
		return prensaUbicacionColeccion;
	}

	public void setPrensaUbicacionColeccion(
			Vector<PrensaUbicacionIf> prensaUbicacionColeccion) {
		this.prensaUbicacionColeccion = prensaUbicacionColeccion;
	}

	public PrensaUbicacionIf getPrensaUbicacionIf() {
		return prensaUbicacionIf;
	}

	public void setPrensaUbicacionIf(PrensaUbicacionIf prensaUbicacionIf) {
		this.prensaUbicacionIf = prensaUbicacionIf;
	}

	public int getPrensaUbicacionSeleccionada() {
		return prensaUbicacionSeleccionada;
	}

	public void setPrensaUbicacionSeleccionada(int prensaUbicacionSeleccionada) {
		this.prensaUbicacionSeleccionada = prensaUbicacionSeleccionada;
	}
}
