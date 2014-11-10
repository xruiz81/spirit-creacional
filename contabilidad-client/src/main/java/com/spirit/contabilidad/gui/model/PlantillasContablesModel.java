package com.spirit.contabilidad.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaData;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.panel.JPPlantillasContables;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.TextChecker;

public class PlantillasContablesModel extends JPPlantillasContables {

	private static final long serialVersionUID = -7678145159370969172L;
	
	private static final int MAX_LONGITUD_REFERENCIA = 30;
	private static final int MAX_LONGITUD_GLOSA = 100;
	private static final int MAX_LONGITUD_NEMONICO = 30;
	private static final String NOMBRE_OPERACION_AGREGAR = "AGREGAR";
	private static final String OPERACION_AGREGAR = NOMBRE_OPERACION_AGREGAR.substring(0,1);
	private static final String NOMBRE_OPERACION_ACTUALIZAR = "ACTUALIZAR";
	private static final String OPERACION_ACTUALIZAR = NOMBRE_OPERACION_ACTUALIZAR.substring(3,4);
	private static final String ESTADO_CUENTA_ACTIVA = "A";
	
	private PlanCuentaIf planCuenta;
	private CuentaIf cuentaIf;
	private CuentaIf cuentaPredeterminadaIf;
	private DefaultTableModel tableModel;
	private boolean guardar = false;
	private Vector<PlantillaIf> plantillaVector = new Vector<PlantillaIf>();
	private PlantillaIf plantilla;
	private PlantillaIf plantillaRegistro;
	private int cuentaDebe = 0;
	private int cuentaHaber = 0;
	ArrayList listaBusqueda;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	private int registroSeleccionado;
	Vector<Long> idActualizar = new Vector<Long>();
	Vector<Long> idRemover = new Vector<Long>();
	private String tipoOperacion = "";
	
	private Map<Long,CuentaIf> mapaCuentas =  new HashMap<Long, CuentaIf>();
	
	public PlantillasContablesModel() {
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
		setRegistroSeleccionado(-1);
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtGlosa().addKeyListener(new TextChecker(MAX_LONGITUD_GLOSA));
		getTxtNemonico().addKeyListener(new TextChecker(MAX_LONGITUD_NEMONICO));
	}
	
	public void initListeners(){
		getCmbEventoContable().addActionListener(cmbEventoContableListener);
		getBtnCuenta().addActionListener(btnCuentaListener);
		getBtnCuentaPredeterminada().addActionListener(btnCuentaPredeterminadaListener);
		getBtnAgregarRegistro().addActionListener(btnAgregarRegistroListener);
		getBtnActualizarRegistro().addActionListener(btnActualizarRegistroListener);
		getBtnRemoverRegistro().addActionListener(btnRemoverRegistroListener);
		getTblPlantillaContable().addMouseListener(oMouseAdapterTblPlantillaContable);
		getTblPlantillaContable().addKeyListener(oKeyAdapterTblPlantillaContable);
	}
	
	private void anchoColumnasTabla() {
		getTblPlantillaContable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSorterTable(getTblPlantillaContable());	
		
		TableColumn anchoColumna = getTblPlantillaContable().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblPlantillaContable().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblPlantillaContable().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPlantillaContable().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPlantillaContable().getColumnModel().getColumn(4);
		
		
		anchoColumna.setPreferredWidth(120);
		
		
	}
	
	MouseListener oMouseAdapterTblPlantillaContable = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblPlantillaContable = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
	
	ActionListener cmbEventoContableListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			plantillaVector.removeAllElements();
			idActualizar.removeAllElements();
			cleanTable();
			
			if(getCmbEventoContable().getSelectedItem() != null) {
				loadTable();
				getBtnCuenta().setEnabled(true);
				getTxtReferencia().setEnabled(true);
				getTxtReferencia().setEditable(true);
				getCmbTipoCuenta().setEnabled(true);
				getTxtNemonico().setEnabled(true);
				getTxtNemonico().setEditable(true);
				getTxtGlosa().setEnabled(true);
				getTxtGlosa().setEditable(true);
				getBtnAgregarRegistro().setEnabled(true);
			} else {
				getBtnCuenta().setEnabled(false);
				getTxtReferencia().setEnabled(false);
				getTxtReferencia().setEditable(false);
				getCmbTipoCuenta().setEnabled(false);
				getTxtNemonico().setEditable(false);
				getTxtGlosa().setEditable(false);
				getBtnAgregarRegistro().setEnabled(false);
			}
		}
	};
	
	ActionListener btnCuentaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				planCuenta = (PlanCuentaIf) SessionServiceLocator.getPlanCuentaSessionService().getPlanCuenta(((EventoContableIf) getCmbEventoContable().getSelectedItem()).getPlanCuentaId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			//cuentaFinderCriteria = new CuentaFinderCriteria(new CuentaModel(true),
			cuentaCriteria = new CuentaCriteria(
					"Cuentas", "", planCuenta.getId(), 0L, ESTADO_CUENTA_ACTIVA);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
								cuentaCriteria,
								JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado() != null ){
				cuentaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
				getTxtCuenta().setText(cuentaIf.getCodigo()	+ " - " + cuentaIf.getNombre());
				getTxtReferencia().setText("");
				getTxtGlosa().setText("");
				getTxtNemonico().setText("");
			}
		}
	};
	
	ActionListener btnCuentaPredeterminadaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				planCuenta = (PlanCuentaIf) SessionServiceLocator.getPlanCuentaSessionService().getPlanCuenta(((EventoContableIf) getCmbEventoContable().getSelectedItem()).getPlanCuentaId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			//cuentaFinderCriteria = new CuentaFinderCriteria(new CuentaModel(true),
			cuentaCriteria = new CuentaCriteria("Cuentas", "", planCuenta.getId(), 0L, ESTADO_CUENTA_ACTIVA);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado() != null ){
				cuentaPredeterminadaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
				getTxtCuentaPredeterminada().setText(cuentaPredeterminadaIf.getCodigo()	+ " - " + cuentaPredeterminadaIf.getNombre());
			}
		}
	};
	
	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	if(column == 2 || column == 3){
        		c.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
                c.setForeground(Color.blue);
        	} else {
        		c.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
        		c.setForeground(Color.black);
        	}
        	
        	return c;
       }
    };
	
	ActionListener btnAgregarRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			agregarPlantillaContable();
		}
	};
	
	ActionListener btnActualizarRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			actualizarPlantillaContable();
		}
	};
	
	ActionListener btnRemoverRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			removerPlantillaContable();
		}
	};
	
	private void removerPlantillaContable() {
		int filaSeleccionada = getTblPlantillaContable().getSelectedRow();
		if(filaSeleccionada >= 0){
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION){
				Long id = ((PlantillaIf) plantillaVector.get(filaSeleccionada)).getId();
				if (id != null)
					idRemover.add(id);
				plantillaVector.remove(filaSeleccionada);
				tableModel.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.INFORMATION);
		}
	}
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setRegistroSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				plantillaRegistro = (PlantillaIf)  plantillaVector.get(getRegistroSeleccionado());
				cuentaIf = verificarCuentaEnMapa(mapaCuentas,plantillaRegistro.getCuentaId());
				if ( cuentaIf != null )
					getTxtCuenta().setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
				else {
					getTxtCuenta().setText("");
					plantillaRegistro.setCuentaId(null);
				}
				cuentaPredeterminadaIf = verificarCuentaEnMapa(mapaCuentas,plantillaRegistro.getCuentaPredeterminadaId());
				if ( cuentaPredeterminadaIf != null )
					getTxtCuentaPredeterminada().setText(cuentaPredeterminadaIf.getCodigo() + " - " + cuentaPredeterminadaIf.getNombre());
				else {
					getTxtCuentaPredeterminada().setText("");
					plantillaRegistro.setCuentaPredeterminadaId(null);
				}
				
				if(plantillaRegistro.getDebehaber().equals("D"))
					getCmbTipoCuenta().setSelectedItem("DEBE");
				else if(plantillaRegistro.getDebehaber().equals("H"))
					getCmbTipoCuenta().setSelectedItem("HABER");
				
				if(plantillaRegistro.getReferencia() != null) 
					getTxtReferencia().setText(plantillaRegistro.getReferencia());
				else getTxtReferencia().setText("");
				if(plantillaRegistro.getGlosa() != null) 
					getTxtGlosa().setText(plantillaRegistro.getGlosa());
				else getTxtGlosa().setText("");
				if(plantillaRegistro.getNemonico() != null) 
					getTxtNemonico().setText(plantillaRegistro.getNemonico());
				else getTxtNemonico().setText("");
				
				getBtnActualizarRegistro().setEnabled(true);
				//CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(plantillaRegistro.getCuentaId());
				if ( cuentaIf != null )
					planCuenta = SessionServiceLocator.getPlanCuentaSessionService().getPlanCuenta(cuentaIf.getPlancuentaId());
				else 
					planCuenta = null;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarPlantillaContable() {
		setGuardar(false);
		setTipoOperacion(OPERACION_AGREGAR);
		if (validateFields()) {
			tableModel = (DefaultTableModel) getTblPlantillaContable().getModel();
			
			//Vector plantilla para luego guardar en la base
			PlantillaData bean = new PlantillaData();
			plantilla = bean;
			plantilla.setEventocontableId(((EventoContableIf)getCmbEventoContable().getSelectedItem()).getId());
			plantilla.setCuentaId(cuentaIf.getId());
			//Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();
			fila.add(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
			if (cuentaPredeterminadaIf != null) {
				plantilla.setCuentaPredeterminadaId(cuentaPredeterminadaIf.getId());
				fila.add(cuentaPredeterminadaIf.getCodigo() + " - " + cuentaPredeterminadaIf.getNombre());
			} else {
				fila.add("");
			}
			if(getCmbTipoCuenta().getSelectedItem().equals("DEBE")){
				fila.add("           X");
				fila.add("");
				plantilla.setDebehaber("D");
			}
			else{
				fila.add("");
				fila.add("           X");
				plantilla.setDebehaber("H");
			}
			fila.add(getTxtNemonico().getText());
			plantilla.setNemonico(getTxtNemonico().getText());
			tableModel.addRow(fila);
							
			//Continua vector plantilla
			if(!getTxtReferencia().getText().equals(""))
				plantilla.setReferencia(getTxtReferencia().getText());
			if(!getTxtGlosa().getText().equals(""))
				plantilla.setGlosa(getTxtGlosa().getText());
			
			//Vector de plantillas
			plantillaVector.add(plantilla);
			
			//Poner las X azules
			for (int i = 0; i < getTblPlantillaContable().getColumnCount(); i++){
				getTblPlantillaContable().getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
			}
			
			getTxtCuenta().setText("");
			getTxtCuentaPredeterminada().setText("");
			getTxtReferencia().setText("");
			getTxtGlosa().setText("");
			getTxtNemonico().setText("");
			setRegistroSeleccionado(-1);
		}
	}
	
	private void actualizarPlantillaContable() {
		int filaSeleccionada = getTblPlantillaContable().getSelectedRow();
		setTipoOperacion(OPERACION_ACTUALIZAR);
		if(filaSeleccionada >= 0) {
			filaSeleccionada = getTblPlantillaContable().convertRowIndexToModel(filaSeleccionada);
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION && validateFields()) {
				
				PlantillaData bean = new PlantillaData();
				
				plantilla = bean;
				plantilla.setEventocontableId(((EventoContableIf)getCmbEventoContable().getSelectedItem()).getId());
				plantilla.setCuentaId(cuentaIf.getId());
				Vector<String> fila = new Vector<String>();
				fila.add(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
				if (cuentaPredeterminadaIf != null) {
					plantilla.setCuentaPredeterminadaId(cuentaPredeterminadaIf.getId());
					fila.add(cuentaPredeterminadaIf.getCodigo() + " - " + cuentaPredeterminadaIf.getNombre());
				} else {
					fila.add("");
				}
				if(getCmbTipoCuenta().getSelectedItem().equals("DEBE")){
					fila.add("           X");
					fila.add("");
					plantilla.setDebehaber("D");
				}
				else{
					fila.add("");
					fila.add("           X");
					plantilla.setDebehaber("H");
				}
				fila.add(getTxtNemonico().getText());
				plantilla.setNemonico(getTxtNemonico().getText());
				tableModel.insertRow(filaSeleccionada, fila);
				tableModel.removeRow(filaSeleccionada+1);
				
				if(!getTxtReferencia().getText().equals(""))
					plantilla.setReferencia(getTxtReferencia().getText());
				if(!getTxtGlosa().getText().equals(""))
					plantilla.setGlosa(getTxtGlosa().getText());
			
				plantillaVector.add(filaSeleccionada, plantilla);
				plantillaVector.remove(filaSeleccionada+1);
				
				getTxtCuenta().setText("");
				getTxtCuentaPredeterminada().setText("");
				getTxtReferencia().setText("");
				getTxtGlosa().setText("");
				getTxtNemonico().setText("");
				getBtnActualizarRegistro().setEnabled(false);
				setRegistroSeleccionado(-1);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.INFORMATION);
		}
	}

	public void showSaveMode() {
		plantillaVector.removeAllElements();
		idActualizar.removeAllElements();
		clean();
		setSaveMode();
		cargarCombos();
	}
	
	public void cargarCombos(){
		cargarComboEventoContable();
		getCmbTipoCuenta().addItem("DEBE");
		getCmbTipoCuenta().addItem("HABER");
	}
	
	private void cargarComboEventoContable(){
		ArrayList<EventoContableIf> eventosContablesArrayList = (ArrayList<EventoContableIf>) ContabilidadFinder.findEventoContableByEmpresaId(Parametros.getIdEmpresa());
		Collections.sort(eventosContablesArrayList, comparadorEventoContable);
		List eventosContables = eventosContablesArrayList; 
		refreshCombo(getCmbEventoContable(),eventosContables);
	}
	
	Comparator<EventoContableIf> comparadorEventoContable = new Comparator<EventoContableIf>(){

		public int compare(EventoContableIf o1, EventoContableIf o2) {
			if ( o1 != null && o2 != null ){
				return o1.getNombre().compareTo( o2.getNombre() );
			}
			return 0;
		}
		
	};

	public void showFindMode() {
		showSaveMode();
	}

	public void find() {

	}
	
	public void loadTable(){
		EventoContableIf eventoContableIf;
		try {
			//Cargo la tabla
			tableModel = (DefaultTableModel) getTblPlantillaContable().getModel();
			
			//Vector plantilla para luego guardar en la base
			plantillaVector.removeAllElements();
			PlantillaIf bean;
									
			eventoContableIf = (EventoContableIf) getCmbEventoContable().getSelectedItem();
			
			if (eventoContableIf != null) {
				
				Collection plantillas = SessionServiceLocator.getPlantillaSessionService().findPlantillaByEventocontableId(eventoContableIf.getId());
				
				if (!plantillas.isEmpty()) {
					Iterator plantillaIt = plantillas.iterator();
					
					idActualizar.removeAllElements();
					
					setUpdateMode();
					
					while(plantillaIt.hasNext()){
						PlantillaIf plantillaIf = (PlantillaIf) plantillaIt.next();
						
						bean = new PlantillaData();
						plantilla = bean;
						idActualizar.add(plantillaIf.getId());
						if (plantillaIf.getId() != null)
							plantilla.setId(plantillaIf.getId());
						plantilla.setEventocontableId(plantillaIf.getEventocontableId());
						plantilla.setCuentaId(plantillaIf.getCuentaId());
						plantilla.setCuentaPredeterminadaId(plantillaIf.getCuentaPredeterminadaId());
										
						//Vector fila para agregar a la tabla
						Vector<String> fila = new Vector<String>();
						
						cuentaIf = verificarCuentaEnMapa(mapaCuentas,plantillaIf.getCuentaId());
						if ( cuentaIf != null ){
							fila.add(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
						} else {
							fila.add("");
						}
						
						cuentaPredeterminadaIf = verificarCuentaEnMapa(mapaCuentas,plantillaIf.getCuentaPredeterminadaId());
						if ( cuentaPredeterminadaIf != null ){
							fila.add(cuentaPredeterminadaIf.getCodigo() + " - " + cuentaPredeterminadaIf.getNombre());
						} else {
							fila.add("");
						}

						if(plantillaIf.getDebehaber().equals("D")){
							fila.add("           X");
							fila.add("");
							plantilla.setDebehaber("D");
						} else {
							fila.add("");
							fila.add("           X");
							plantilla.setDebehaber("H");
						}
						fila.add(plantillaIf.getNemonico());
						plantilla.setNemonico(plantillaIf.getNemonico());
						tableModel.addRow(fila);
						
						if(plantillaIf.getReferencia() != null)
							plantilla.setReferencia(plantillaIf.getReferencia());
						else 
							plantilla.setReferencia("");
						if(plantillaIf.getGlosa() != null)
							plantilla.setGlosa(plantillaIf.getGlosa());
						else 
							plantilla.setGlosa("");
						
						plantillaVector.add(plantilla);
					}
											
					//Poner las X azules
					for (int i = 0; i < getTblPlantillaContable().getColumnCount(); i++){
						getTblPlantillaContable().getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
					}
							
					showUpdateMode();
				} else {
					plantillaVector.removeAllElements();
					idActualizar.removeAllElements();
					getTxtCuenta().setText("");
					getTxtCuentaPredeterminada().setText("");
					getTxtReferencia().setText("");
					getTxtGlosa().setText("");
					getTxtNemonico().setText("");
					cleanTable();
					setSaveMode();
				}
			}
		} catch (GenericBusinessException ev) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}

	public void save() {
		setGuardar(true);
		PlantillaIf plantilla;
		try {
			if (validateFields()) {
				for(int i=0; i < plantillaVector.size(); i++){
					plantilla = (PlantillaIf) plantillaVector.get(i);
					PlantillaData data = new PlantillaData();
					data.setEventocontableId(plantilla.getEventocontableId());
					data.setCuentaId(plantilla.getCuentaId());
					data.setCuentaPredeterminadaId(plantilla.getCuentaPredeterminadaId());
					data.setDebehaber(plantilla.getDebehaber());
					if(plantilla.getReferencia() != null) data.setReferencia(plantilla.getReferencia());
					if(plantilla.getGlosa() != null) data.setGlosa(plantilla.getGlosa());
					data.setNemonico(plantilla.getNemonico());
					SessionServiceLocator.getPlantillaSessionService().addPlantilla(data);
				}
				plantillaVector.removeAllElements();
				setGuardar(false);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Plantilla Contable guardada con éxito", SpiritAlert.INFORMATION);
			}
		}catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		setGuardar(true);
		PlantillaIf plantilla;
		PlantillaData data;
		try {
			if (validateFields()) {
				for (int i=0; i < idRemover.size(); i++) {
					if (SessionServiceLocator.getPlantillaSessionService().getPlantilla(idRemover.get(i)) != null)
						SessionServiceLocator.getPlantillaSessionService().deletePlantilla(idRemover.get(i));
				}
				for (int i=0; i < plantillaVector.size(); i++) {
					plantilla = (PlantillaIf) plantillaVector.get(i);
					
					if ( plantilla.getCuentaId() == null ){
						SpiritAlert.createAlert("Plantilla con Nenómico "+
							plantilla.getNemonico()+" no tiene cuenta asociada !!", SpiritAlert.WARNING);
						return;
					}
					if (idActualizar.size() >= (i+1)) {
						data = new PlantillaData();
						data.setId(idActualizar.get(i));
						data.setEventocontableId(plantilla.getEventocontableId());
						data.setCuentaId(plantilla.getCuentaId());
						data.setCuentaPredeterminadaId(plantilla.getCuentaPredeterminadaId());
						data.setDebehaber(plantilla.getDebehaber());
						if(plantilla.getReferencia() != null) data.setReferencia(plantilla.getReferencia());
						if(plantilla.getGlosa() != null) data.setGlosa(plantilla.getGlosa());
						data.setNemonico(plantilla.getNemonico());
						SessionServiceLocator.getPlantillaSessionService().savePlantilla(data);
					} else {
						data = new PlantillaData();
						data.setEventocontableId(plantilla.getEventocontableId());
						data.setCuentaId(plantilla.getCuentaId());
						data.setCuentaPredeterminadaId(plantilla.getCuentaPredeterminadaId());
						data.setDebehaber(plantilla.getDebehaber());
						if(plantilla.getReferencia() != null) data.setReferencia(plantilla.getReferencia());
						if(plantilla.getGlosa() != null) data.setGlosa(plantilla.getGlosa());
						data.setNemonico(plantilla.getNemonico());
						SessionServiceLocator.getPlantillaSessionService().addPlantilla(data);
					}
				}
				plantillaVector.removeAllElements();
				setGuardar(false);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Plantilla Contable actualizada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			for (int i=0; i < idActualizar.size(); i++)
				SessionServiceLocator.getPlantillaSessionService().deletePlantilla(idActualizar.get(i));
			this.clean();
			this.showSaveMode();
			SpiritAlert.createAlert("Plantilla Contable eliminada con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			this.clean();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	public void report() {
		
	}
	
	public void refresh() {
		cargarComboEventoContable();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		agregarPlantillaContable();
	}

	public void updateDetail() {
		actualizarPlantillaContable();
	}
	
	public void deleteDetail() {
		removerPlantillaContable();
	}

	public void clean() {
		getCmbEventoContable().removeAllItems();
		getTxtCuenta().setText("");
		getTxtCuenta().setEditable(false);
		getTxtCuentaPredeterminada().setText("");
		getTxtCuentaPredeterminada().setEditable(false);
		getBtnCuenta().setEnabled(false);
		getTxtReferencia().setText("");
		getTxtGlosa().setText("");
		getTxtNemonico().setText("");
		getCmbTipoCuenta().removeAllItems();
		
		getTxtReferencia().setEnabled(false);
		getTxtGlosa().setEnabled(false);
		getTxtNemonico().setEnabled(false);
		getCmbTipoCuenta().setEnabled(false);
		getBtnAgregarRegistro().setEnabled(false);
		getBtnActualizarRegistro().setEnabled(false);
		getBtnRemoverRegistro().setEnabled(false);
		
		mapaCuentas = null;
		mapaCuentas = new HashMap<Long, CuentaIf>();
		
		cleanTable();
	}

	private void cleanTable() {
		//Vacio la tabla
		limpiarTabla(getTblPlantillaContable());
	}

	public boolean validateFields() {
		
		EventoContableIf eventoContable = (EventoContableIf) getCmbEventoContable().getSelectedItem();
		CuentaIf cuentaIf;
		CuentaIf cuentaPredeterminadaIf;
		String strCuenta = getTxtCuenta().getText();
		String strCuentaPredeterminada = getTxtCuentaPredeterminada().getText();
		String strNemonico = getTxtNemonico().getText();
		Long eventoContableId;
		Long planCuentaId;
		Long cuentaId;
		String nemonico;
		cuentaDebe = 0;
		cuentaHaber = 0;
			
		try {
		if(!isGuardar()){
			if((("".equals(eventoContable)) || (eventoContable == null))) {
				SpiritAlert.createAlert("Debe elegir un Evento Contable !!", SpiritAlert.WARNING);
				getCmbEventoContable().grabFocus();
				return false;
			}
			if((("".equals(strCuenta)) || (strCuenta == null))) {
				SpiritAlert.createAlert("Debe elegir una Cuenta !!", SpiritAlert.WARNING);
				getTxtCuenta().grabFocus();
				return false;
			}
			if (("".equals(strNemonico)) || strNemonico == null) {
				SpiritAlert.createAlert("Debe ingresar un nemónico !!", SpiritAlert.WARNING);
				getTxtNemonico().grabFocus();
				return false;
			}

			for (int i=0; i < plantillaVector.size(); i++){
				PlantillaIf plantilla =(PlantillaIf)plantillaVector.get(i); 
				eventoContableId = plantilla.getEventocontableId();
				cuentaId = plantilla.getCuentaId();
				cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId);
				nemonico = plantilla.getNemonico();
										
				if (!eventoContableId.equals(eventoContable.getId())){
					SpiritAlert.createAlert("El Evento Contable debe ser el mismo !!", SpiritAlert.WARNING);
					getCmbEventoContable().grabFocus();
					return false;
				}
				
				if (!planCuenta.getId().equals(cuentaIf.getPlancuentaId())){
					SpiritAlert.createAlert("El Plan de Cuenta debe ser el mismo !!", SpiritAlert.WARNING);
					getCmbEventoContable().grabFocus();
					return false;
				}
				
				/*if (cuentaId.equals(getCuentaSeleccionadaIf().getId())) {
					if (getTipoOperacion().equals(OPERACION_AGREGAR) || (getTipoOperacion().equals(OPERACION_ACTUALIZAR) && i != getRegistroSeleccionado())) {
						SpiritAlert.createAlert("No pueden existir dos Cuentas iguales en la misma plantilla !!", SpiritAlert.WARNING);
						getTxtCuenta().grabFocus();
						return false;
					}
				}*/
				
				if (strNemonico.equals(nemonico)) {
					if (getTipoOperacion().equals(OPERACION_AGREGAR) || (getTipoOperacion().equals(OPERACION_ACTUALIZAR) && i != getRegistroSeleccionado())) {
						SpiritAlert.createAlert("No pueden existir dos nemonicos iguales en la misma plantilla !!", SpiritAlert.WARNING);
						getTxtNemonico().grabFocus();
						return false;
					}
				}
			}
		}
		if (isGuardar()) {
			for (int i=0; i < plantillaVector.size(); i++) {
				if(((PlantillaIf)plantillaVector.get(i)).getDebehaber().equals("D"))
					cuentaDebe++;
				else if(((PlantillaIf)plantillaVector.get(i)).getDebehaber().equals("H"))
					cuentaHaber++;
			}
			if (cuentaDebe == 0 || cuentaHaber == 0) {
				SpiritAlert.createAlert("Al menos debe existir un Deber y un Haber !!", SpiritAlert.WARNING);
				return false;
			}
		}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
				
		return true;
	}

	public boolean isEmpty() {
		return false;
	}

	public void showUpdateMode() {
		getBtnCuenta().setEnabled(true);
		getTxtReferencia().setEnabled(true);
		getTxtReferencia().setEditable(true);
		getCmbTipoCuenta().setEnabled(true);
		getTxtNemonico().setEnabled(true);
		getTxtNemonico().setEditable(true);
		getTxtGlosa().setEnabled(true);
		getTxtGlosa().setEditable(true);
		getBtnAgregarRegistro().setEnabled(true);
		getBtnActualizarRegistro().setEnabled(true);
		getBtnRemoverRegistro().setEnabled(true);
	}
	
	private CuentaIf verificarCuentaEnMapa(Map<Long, CuentaIf> mapaCuentas,Long cuentaId) throws GenericBusinessException{
		CuentaIf cuentaIf = mapaCuentas.get(cuentaId);
		if ( cuentaIf == null && cuentaId != null ){
			cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId);
			mapaCuentas.put(cuentaId, cuentaIf);
		}
		return cuentaIf;
	}
	
	public boolean isGuardar() {
		return guardar;
	}

	public void setGuardar(boolean guardar) {
		this.guardar = guardar;
	}

	public int getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(int registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
}
