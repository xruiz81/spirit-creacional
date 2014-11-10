package com.spirit.general.gui.model;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaData;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.criteria.OficinaCriteria;
import com.spirit.general.gui.panel.JPOficina;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.FinderTable;
import com.spirit.util.NumberTextField;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class OficinaModel extends JPOficina {

	private static final long serialVersionUID = -228238233820187224L;
	protected int mode;
	public boolean isFinderTableVisible = false;
	protected FinderTable finderTable;
	private JDPopupFinderModel popupFinder;
	private OficinaCriteria oficinaCriteria;
	private EmpleadoCriteria empleadoCriteria;
	
	EmpleadoIf administradorIf;
	ArrayList lista;

	private static final int MAX_LONGITUD_CODIGO = 6;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_DIRECCION = 150;
	private static final int MAX_LONGITUD_TELEFONO = 10;
	private static final int MAX_LONGITUD_FAX = 10;
	private static final int MAX_LONGITUD_PREIMPRESO = 3;
	
	private Vector oficinaVector = new Vector();
	private DefaultTableModel tableModel;
	protected OficinaIf oficinaActualizadaIf;
	private int oficinaSeleccionada;

	public OficinaModel() {
		initKeyListeners();
		setSorterTable(getTblOficina());
		cargarCombos();
		anchoColumnasTabla();
		initListeners();
		showSaveMode();
		getTblOficina().addMouseListener(oMouseAdapterTblOficina);
		getTblOficina().addKeyListener(oKeyAdapterTblOficina);

		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblOficina().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(12);
		
		anchoColumna = getTblOficina().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(180);
		    
		anchoColumna = getTblOficina().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
		    
		anchoColumna = getTblOficina().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(180);		
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtDireccion().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION));;
		getTxtTelefono().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getTxtTelefono().addKeyListener(new NumberTextField());
		getTxtFax().addKeyListener(new TextChecker(MAX_LONGITUD_FAX));
		getTxtFax().addKeyListener(new NumberTextField());
		getTxtPreimpresoSerie().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
	}
	
	private void cargarCombos() {
		cargarComboCiudades();
	}
	
	private void cargarComboCiudades(){
		SpiritComboBoxModel cmbModelCiudad = new SpiritComboBoxModel(GeneralFinder.findCiudades());
		getCmbCiudad().setModel(cmbModelCiudad);
	}
	
	MouseListener oMouseAdapterTblOficina = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblOficina = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setOficinaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			setOficinaActualizadaIf((OficinaIf)  getOficinaVector().get(getOficinaSeleccionada()));
			getTxtCodigo().setText(getOficinaActualizadaIf().getCodigo());
			getTxtNombre().setText(getOficinaActualizadaIf().getNombre());
			getCmbCiudad().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCiudad(), getOficinaActualizadaIf().getCiudadId()));
			getCmbCiudad().repaint();
			
			if(getOficinaActualizadaIf().getAdministradorId() != null){
				try {
					administradorIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(getOficinaActualizadaIf().getAdministradorId());
					getTxtAdministrador().setText(administradorIf.getCodigo() + " - " + administradorIf.getNombres() + " " + administradorIf.getApellidos());
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
			
			if(getOficinaActualizadaIf().getDireccion() != null){
				getTxtDireccion().setText(getOficinaActualizadaIf().getDireccion());
			}
			if(getOficinaActualizadaIf().getTelefono() != null){
				getTxtTelefono().setText(getOficinaActualizadaIf().getTelefono());
			}
			if(getOficinaActualizadaIf().getFax() != null){
				getTxtFax().setText(getOficinaActualizadaIf().getFax());
			}
			if(getOficinaActualizadaIf().getPreimpresoSerie() != null){
				getTxtPreimpresoSerie().setText(getOficinaActualizadaIf().getPreimpresoSerie());
			}
			getTxtCodigo().setEnabled(false);
			getCmbCiudad().setEnabled(false);
			showUpdateMode();
		}
	}
	
	private void initListeners() {
		// Manejo los eventos de Buscar Ejecutivo
		getBtnBuscarAdministrador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//empleadoFinderCriteria = new EmpleadoFinderCriteria(new EmpleadoModel(true),
				//		"Administradores",Parametros.getIdEmpresa()); 
				empleadoCriteria = new EmpleadoCriteria("Administradores",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					administradorIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtAdministrador().setText(administradorIf.getCodigo() + " - " + administradorIf.getNombres() + " " + administradorIf.getApellidos());
				}
			}
		});
	}

	public void save() {
		if (validateFields()) {
			OficinaData data = new OficinaData();
			
			data.setEmpresaId(Parametros.getIdEmpresa());
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setCiudadId(((CiudadIf) this.getCmbCiudad().getSelectedItem()).getId());
			if(administradorIf != null){
			data.setAdministradorId(administradorIf.getId());
			}
			data.setDireccion(this.getTxtDireccion().getText());
			data.setTelefono(this.getTxtTelefono().getText());
			data.setFax(this.getTxtFax().getText());
			data.setPreimpresoSerie(this.getTxtPreimpresoSerie().getText());

				
			try {
				SessionServiceLocator.getOficinaSessionService().addOficina(data);
				SpiritAlert.createAlert("Oficina guardada con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("oficina",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		if (validateFields()) {
			oficinaActualizadaIf.setNombre(this.getTxtNombre().getText());
			oficinaActualizadaIf.setCiudadId(((CiudadIf) this.getCmbCiudad().getSelectedItem()).getId());
			if (administradorIf != null)
				oficinaActualizadaIf.setAdministradorId(administradorIf.getId());
			oficinaActualizadaIf.setDireccion(this.getTxtDireccion().getText());
			oficinaActualizadaIf.setTelefono(this.getTxtTelefono().getText());
			oficinaActualizadaIf.setFax(this.getTxtFax().getText());
			oficinaActualizadaIf.setPreimpresoSerie(this.getTxtPreimpresoSerie().getText());

			try {
				SessionServiceLocator.getOficinaSessionService().saveOficina(oficinaActualizadaIf);
				SpiritAlert.createAlert("Oficina actualizada con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("oficina",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al actualizar informacin!", SpiritAlert.ERROR);
			}
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getOficinaSessionService().deleteOficina(oficinaActualizadaIf.getId());
			SpiritAlert.createAlert("Oficina eliminada con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("oficina",null);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("La oficina tiene datos referenciados y no puede ser eliminada!", SpiritAlert.ERROR);
		}
		
		showSaveMode();
	}
	
	public void refresh(){
		cargarComboCiudades();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strDireccion = this.getTxtDireccion().getText();
		String strTelefono = this.getTxtTelefono().getText();
		String strFax = this.getTxtFax().getText();
		String strPreimpresoSerie = this.getTxtPreimpresoSerie().getText();
		
		Collection oficinas = null;
		boolean codigoRepetido = false;
		
		try {
			oficinas = SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator oficinaIt = oficinas.iterator();
		
		while (oficinaIt.hasNext()) {
			OficinaIf oficinaIf = (OficinaIf) oficinaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(oficinaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(oficinaIf.getCodigo())) 
					if (oficinaActualizadaIf.getId().equals(oficinaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo de la Oficina está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para la Oficina !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Oficina !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbCiudad().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una Ciudad para la Oficina !!", SpiritAlert.WARNING);
			getCmbCiudad().grabFocus();
			return false;
		}
		
		if ((("".equals(strDireccion)) || (strDireccion == null))) {
			SpiritAlert.createAlert("Debe ingresar una direccin para la Oficina !!", SpiritAlert.WARNING);
			getTxtDireccion().grabFocus();
			return false;
		}

		if ((("".equals(strTelefono)) || (strTelefono == null))) {
			SpiritAlert.createAlert("Debe ingresar un teléfono para la Oficina !!", SpiritAlert.WARNING);
			getTxtTelefono().grabFocus();
			return false;
		}
		
		if ((("".equals(strFax)) || (strFax == null))) {
			SpiritAlert.createAlert("Debe ingresar un número de fax para la Oficina !!", SpiritAlert.WARNING);
			getTxtFax().grabFocus();
			return false;
		}

		if ((("".equals(strPreimpresoSerie)) || (strPreimpresoSerie == null))) {
			SpiritAlert.createAlert("Debe ingresar la serie del preimpreso para la Oficina !!", SpiritAlert.WARNING);
			getTxtPreimpresoSerie().grabFocus();
			return false;
		}
		return true;
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& this.getCmbCiudad().getSelectedItem() == null
				&& "".equals(this.getTxtAdministrador().getText())
				&& "".equals(this.getTxtDireccion().getText())
				&& "".equals(this.getTxtTelefono().getText())
				&& "".equals(this.getTxtFax().getText())
				&& "".equals(this.getTxtPreimpresoSerie().getText())) {

			return true;

		} else {

			return false;
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbCiudad().setSelectedIndex(-1);
		getCmbCiudad().setSelectedItem("");
		getTxtAdministrador().setText("");
		getTxtDireccion().setText("");
		getTxtTelefono().setText("");
		getTxtFax().setText("");
		getTxtPreimpresoSerie().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblOficina().getModel();
		for(int i= this.getTblOficina().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText().toUpperCase()+ "%");
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", "%" + getTxtNombre().getText().toUpperCase() + "%");
		else
			aMap.put("nombre", "%");
		
		if (administradorIf != null)
			aMap.put("administradorId", administradorIf.getId());
		
		if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
			aMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
		
		return aMap;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbCiudad().setEnabled(true);
		getTxtAdministrador().setEditable(false);
		getTxtDireccion().setEnabled(true);
		getTxtTelefono().setEnabled(true);
		getTxtFax().setEnabled(true);
		getTxtPreimpresoSerie().setEnabled(true);
		
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection oficina = SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator oficinaIterator = oficina.iterator();
			
			if(!getOficinaVector().isEmpty()){
				getOficinaVector().removeAllElements();
			}
			
			while (oficinaIterator.hasNext()) {
				OficinaIf oficinaIf = (OficinaIf) oficinaIterator.next();
				
				tableModel = (DefaultTableModel) getTblOficina().getModel();
				Vector<String> fila = new Vector<String>();
				getOficinaVector().add(oficinaIf);
				
				agregarColumnasTabla(oficinaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblOficina(), oficina, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(OficinaIf oficinaIf, Vector<String> fila){
		
		fila.add(oficinaIf.getCodigo());
		fila.add(oficinaIf.getNombre());
		
		try {
			if(oficinaIf.getCiudadId() != null){
				CiudadIf ciudad = CiudadModel.getCiudadSessionService().getCiudad(oficinaIf.getCiudadId());
				fila.add(ciudad.getNombre());
			}
			else fila.add("");
		
			if(oficinaIf.getAdministradorId() != null){
				EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(oficinaIf.getAdministradorId());
				fila.add(empleado.getNombres() + " " + empleado.getApellidos());
			}
			else fila.add("");
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
	}

	public OficinaIf getOficinaActualizadaIf() {
		return oficinaActualizadaIf;
	}

	public void setOficinaActualizadaIf(OficinaIf oficinaActualizadaIf) {
		this.oficinaActualizadaIf = oficinaActualizadaIf;
	}

	public int getOficinaSeleccionada() {
		return oficinaSeleccionada;
	}

	public void setOficinaSeleccionada(int oficinaSeleccionada) {
		this.oficinaSeleccionada = oficinaSeleccionada;
	}

	public Vector getOficinaVector() {
		return oficinaVector;
	}

	public void setOficinaVector(Vector oficinaVector) {
		this.oficinaVector = oficinaVector;
	}
	 
}

