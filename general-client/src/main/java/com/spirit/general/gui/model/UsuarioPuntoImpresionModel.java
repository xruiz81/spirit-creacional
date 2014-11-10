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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.entity.UsuarioPuntoImpresionData;
import com.spirit.general.entity.UsuarioPuntoImpresionIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.panel.JPUsuarioPuntoImpresion;
import com.spirit.general.session.PuntoImpresionSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.general.session.UsuarioPuntoImpresionSessionService;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Utilitarios;

public class UsuarioPuntoImpresionModel extends JPUsuarioPuntoImpresion{
	
	private JDPopupFinderModel popupFinder;
	private EmpleadoCriteria empleadoCriteria;
	private UsuarioIf usuario;
	private Vector usuarioPuntoImpresionVector = new Vector();
	private DefaultTableModel tableModel;
	private int usuarioPuntoImpresionSeleccionado;
	protected UsuarioPuntoImpresionIf usuarioPuntoImpresionIf;
	List<PuntoImpresionIf> puntosImpresion = new ArrayList<PuntoImpresionIf>();
	
	public UsuarioPuntoImpresionModel(){
		anchoColumnasTabla();
		cargarComboPuntoImpresion();
		initKeyListeners();
		initListeners();
		showSaveMode();
		getTblUsuarioPuntoImpresion().addMouseListener(oMouseAdapterTblUsuarioPuntoImpresion);
		getTblUsuarioPuntoImpresion().addKeyListener(oKeyAdapterTblUsuarioPuntoImpresion);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		getTblUsuarioPuntoImpresion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		TableColumn anchoColumna = getTblUsuarioPuntoImpresion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblUsuarioPuntoImpresion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(500);
	}
	
	MouseListener oMouseAdapterTblUsuarioPuntoImpresion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblUsuarioPuntoImpresion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			try {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setUsuarioPuntoImpresionSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				usuarioPuntoImpresionIf = (UsuarioPuntoImpresionIf)  getUsuarioPuntoImpresionVector().get(getUsuarioPuntoImpresionSeleccionado());
				usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuarioPuntoImpresionIf.getUsuarioId());
				getTxtUsuario().setText(usuario.getUsuario());			
				PuntoImpresionIf puntoImpresion = SessionServiceLocator.getPuntoImpresionSessionService().getPuntoImpresion(usuarioPuntoImpresionIf.getPuntoimpresionId());
				int puntoImpresionBase = -1;
				for(int i=0; i<puntosImpresion.size(); i++){
					if(puntoImpresion.getId().compareTo(puntosImpresion.get(i).getId()) == 0){
						puntoImpresionBase = i;
					}
				}				
				getCmbPuntoImpresion().setSelectedIndex(puntoImpresionBase);
				getCmbPuntoImpresion().repaint();
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void initKeyListeners(){
		getTxtUsuario().setEditable(false);
	}
	
	private void cargarComboPuntoImpresion() {
		try {
			puntosImpresion.clear();
			puntosImpresion = (List<PuntoImpresionIf>) SessionServiceLocator.getPuntoImpresionSessionService().findPuntoImpresionByEmpresaId(Parametros.getIdEmpresa());
			List<String> listaPuntosImpresion = new ArrayList<String>();
			for(PuntoImpresionIf puntoImpresion : puntosImpresion){
				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(puntoImpresion.getTipodocumentoId());
				listaPuntosImpresion.add(tipoDocumento.getNombre() + " - " + puntoImpresion.getImpresora() + " - " + puntoImpresion.getSerie());
			}
			refreshCombo(getCmbPuntoImpresion(), listaPuntosImpresion);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void initListeners() {
		getBtnUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					empleadoCriteria = new EmpleadoCriteria("de Usuarios",Parametros.getIdEmpresa());
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
					if ( popupFinder.getElementoSeleccionado()!=null ){
						EmpleadoIf empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
						if(!SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleado.getId()).isEmpty()){
							usuario = (UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleado.getId()).iterator().next();
							getTxtUsuario().setText(usuario.getUsuario());
						}else{
							SpiritAlert.createAlert("El empleado seleccionado no tiene un Usuario creado !", SpiritAlert.WARNING);
						}						
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void save() {
		try {
			if (validateFields()) {
				UsuarioPuntoImpresionData data = new UsuarioPuntoImpresionData();
				data.setUsuarioId(usuario.getId());
				data.setPuntoimpresionId(puntosImpresion.get(getCmbPuntoImpresion().getSelectedIndex()).getId());
				SessionServiceLocator.getUsuarioPuntoImpresionSessionService().addUsuarioPuntoImpresion(data);
				SpiritAlert.createAlert("Usuario x Punto de Impresión guardado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			UsuarioPuntoImpresionIf usuarioPuntoImpresionIf = (UsuarioPuntoImpresionIf) getUsuarioPuntoImpresionVector().get(getUsuarioPuntoImpresionSeleccionado());
			SessionServiceLocator.getUsuarioPuntoImpresionSessionService().deleteUsuarioPuntoImpresion(usuarioPuntoImpresionIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Usuario x Punto de Impresin eliminado con éxito", SpiritAlert.INFORMATION);
			} 
		catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				usuarioPuntoImpresionIf.setUsuarioId(usuario.getId());
				usuarioPuntoImpresionIf.setPuntoimpresionId(puntosImpresion.get(getCmbPuntoImpresion().getSelectedIndex()).getId());
				SessionServiceLocator.getUsuarioPuntoImpresionSessionService().saveUsuarioPuntoImpresion(usuarioPuntoImpresionIf);
				getUsuarioPuntoImpresionVector().setElementAt(usuarioPuntoImpresionIf, getUsuarioPuntoImpresionSeleccionado());
				setUsuarioPuntoImpresionIf(null);
				SpiritAlert.createAlert("Usuario x Punto de Impresin actualizado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		usuario = null;
		getTxtUsuario().setText(""); 
		
		DefaultTableModel model = (DefaultTableModel) getTblUsuarioPuntoImpresion().getModel();
		for(int i= this.getTblUsuarioPuntoImpresion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getBtnUsuario().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection usuarioPuntosImpresion = SessionServiceLocator.getUsuarioPuntoImpresionSessionService().findUsuarioPuntoImpresionByEmpresaId(Parametros.getIdEmpresa());
			Iterator usuarioPuntosImpresionIterator = usuarioPuntosImpresion.iterator();
			
			if(!getUsuarioPuntoImpresionVector().isEmpty()){
				getUsuarioPuntoImpresionVector().removeAllElements();
			}
			
			while (usuarioPuntosImpresionIterator.hasNext()) {
				UsuarioPuntoImpresionIf usuarioPuntoImpresionIf = (UsuarioPuntoImpresionIf) usuarioPuntosImpresionIterator.next();
				
				tableModel = (DefaultTableModel) getTblUsuarioPuntoImpresion().getModel();
				Vector<String> fila = new Vector<String>();
				getUsuarioPuntoImpresionVector().add(usuarioPuntoImpresionIf);
				
				agregarColumnasTabla(usuarioPuntoImpresionIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblUsuarioPuntoImpresion(), usuarioPuntosImpresion, 0);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(UsuarioPuntoImpresionIf usuarioPuntoImpresionIf, Vector<String> fila){
		try {
			UsuarioIf usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(usuarioPuntoImpresionIf.getUsuarioId());
			fila.add(usuario.getUsuario());
			
			PuntoImpresionIf puntoImpresion = SessionServiceLocator.getPuntoImpresionSessionService().getPuntoImpresion(usuarioPuntoImpresionIf.getPuntoimpresionId());
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(puntoImpresion.getTipodocumentoId());
			fila.add(tipoDocumento.getNombre() + " - " + puntoImpresion.getImpresora() + " - " + puntoImpresion.getSerie());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if(usuario == null){
			SpiritAlert.createAlert("Debe seleccionar un Usuario !", SpiritAlert.WARNING);
			getBtnUsuario().grabFocus();
			return false;
		}
		if (getCmbPuntoImpresion().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Punto de Impresin !", SpiritAlert.WARNING);
			getCmbPuntoImpresion().grabFocus();
			return false;
		}
		return true;
	}
	
	public void refresh(){
		cargarComboPuntoImpresion();
	}
	 

	public int getUsuarioPuntoImpresionSeleccionado() {
		return usuarioPuntoImpresionSeleccionado;
	}

	public void setUsuarioPuntoImpresionSeleccionado(
			int usuarioPuntoImpresionSeleccionado) {
		this.usuarioPuntoImpresionSeleccionado = usuarioPuntoImpresionSeleccionado;
	}

	public Vector getUsuarioPuntoImpresionVector() {
		return usuarioPuntoImpresionVector;
	}

	public void setUsuarioPuntoImpresionVector(Vector usuarioPuntoImpresionVector) {
		this.usuarioPuntoImpresionVector = usuarioPuntoImpresionVector;
	}

	public UsuarioPuntoImpresionIf getUsuarioPuntoImpresionIf() {
		return usuarioPuntoImpresionIf;
	}

	public void setUsuarioPuntoImpresionIf(
			UsuarioPuntoImpresionIf usuarioPuntoImpresionIf) {
		this.usuarioPuntoImpresionIf = usuarioPuntoImpresionIf;
	}
}
