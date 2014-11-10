package com.spirit.medios.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.medios.entity.TipoBriefData;
import com.spirit.medios.entity.TipoBriefIf;
import com.spirit.medios.gui.panel.JPTipoBrief;
import com.spirit.medios.session.TipoBriefSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoBriefModel extends JPTipoBrief {

	private static final long serialVersionUID = 796770993296843510L;

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	private static final String NOMBRE_OBLIGATORIO_SI = "SI";
	private static final String NOMBRE_OBLIGATORIO_NO = "NO";
	private static final String OBLIGATORIO_SI = NOMBRE_OBLIGATORIO_SI.substring(0,1);
	private static final String OBLIGATORIO_NO = NOMBRE_OBLIGATORIO_NO.substring(0,1);

	private Vector tipoBriefVector = new Vector();
	private int tipoBriefSeleccionado;
	private TipoBriefIf tipoBriefActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoBriefModel() {
		setSorterTable(getTblTipoBrief());
		initKeyListeners();
		getTblTipoBrief().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.showSaveMode();

		setColumnWidthTable();
        this.getTblTipoBrief().addMouseListener(oMouseAdapterTblTipoBrief);
        this.getTblTipoBrief().addKeyListener(oKeyAdapterTblTipoBrief);	 
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblTipoBrief().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		
		anchoColumna = getTblTipoBrief().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
		    
		anchoColumna = getTblTipoBrief().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(20);
		
	}

	MouseListener oMouseAdapterTblTipoBrief = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoBrief = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoBriefSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoBriefActualizadoIf = (TipoBriefIf)  getTipoBriefVector().get(getTipoBriefSeleccionado());
			
			getTxtCodigo().setText(getTipoBriefActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoBriefActualizadoIf().getNombre());
			
			if (OBLIGATORIO_SI.equals(getTipoBriefActualizadoIf().getObligatorio()))
				getCmbObligatorio().setSelectedItem(NOMBRE_OBLIGATORIO_SI);
			else if (OBLIGATORIO_NO.equals(getTipoBriefActualizadoIf().getObligatorio()))
				getCmbObligatorio().setSelectedItem(NOMBRE_OBLIGATORIO_NO);
		
			showUpdateMode();
		}
	}

	public void save() {

		try {
			if (validateFields()) {
				TipoBriefData data = new TipoBriefData();

				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setObligatorio(getCmbObligatorio().getSelectedItem().toString().substring(0,1));

				SessionServiceLocator.getTipoBriefSessionService().addTipoBrief(data);
				SpiritAlert.createAlert("Tipo Brief guardado con éxito",SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setTipoBriefActualizadoIf((TipoBriefIf) getTipoBriefVector().get(getTipoBriefSeleccionado()));

				getTipoBriefActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoBriefActualizadoIf().setObligatorio(getCmbObligatorio().getSelectedItem().toString().substring(0,1));
				
				getTipoBriefVector().setElementAt(getTipoBriefActualizadoIf(), getTipoBriefSeleccionado());
				
				SessionServiceLocator.getTipoBriefSessionService().saveTipoBrief(getTipoBriefActualizadoIf());
				setTipoBriefActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo Brief actualizado con éxito",SpiritAlert.INFORMATION);	
				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}	
	
	public void delete() {
		try {
			TipoBriefIf tipoBriefIf = (TipoBriefIf) getTipoBriefVector().get(getTipoBriefSeleccionado());
			SessionServiceLocator.getTipoBriefSessionService().deleteTipoBrief(tipoBriefIf.getId());
			SpiritAlert.createAlert("Tipo Brief eliminado con éxito",SpiritAlert.INFORMATION);
			this.showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}
	
	private void agregarColumnasTabla(TipoBriefIf tipoBriefIf, Vector<String> fila){
		fila.add(tipoBriefIf.getCodigo());
		fila.add(tipoBriefIf.getNombre());
		if (tipoBriefIf.getObligatorio().equals(OBLIGATORIO_SI))
			fila.add(NOMBRE_OBLIGATORIO_SI);
		else if (tipoBriefIf.getObligatorio().equals(OBLIGATORIO_NO))
			fila.add(NOMBRE_OBLIGATORIO_NO);		
	}

	private void cargarTabla() {
		try {			
			Collection tipoBrief = SessionServiceLocator.getTipoBriefSessionService().findTipoBriefByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoBriefIterator = tipoBrief.iterator();
			
			if(!getTipoBriefVector().isEmpty()){
				getTipoBriefVector().removeAllElements();
			}
			
			while (tipoBriefIterator.hasNext()) {
				TipoBriefIf tipoBriefIf = (TipoBriefIf) tipoBriefIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoBrief().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoBriefVector().add(tipoBriefIf);
				
				agregarColumnasTabla(tipoBriefIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoBrief(), tipoBrief, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo Brief !!"
					,SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripción para el Tipo Brief !!"
					,SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbObligatorio().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar si el Tipo Brief es obligatorio o no !!"
					,SpiritAlert.WARNING);
			getCmbObligatorio().grabFocus();
			return false;
		}
		
		Collection tipoBrief = null;
		boolean codigoRepetido = false;
		
		try {
			tipoBrief = SessionServiceLocator.getTipoBriefSessionService().findTipoBriefByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		Iterator tipoBriefIt = tipoBrief.iterator();
		
		while (tipoBriefIt.hasNext()) {
			TipoBriefIf tipoBriefIf = (TipoBriefIf) tipoBriefIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoBriefIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoBriefIf.getCodigo())) 
					if (tipoBriefActualizadoIf.getId().equals(tipoBriefIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo Brief está duplicado !!"
					,SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		return true;
	}	
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
		    && "".equals(this.getTxtNombre().getText())
		    && this.getCmbObligatorio().getSelectedItem() == null) {

			return true;
		} else {
			return false;
		}
	}
	
	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoBrief().getModel();
		
		for(int i= this.getTblTipoBrief().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void cargarCombos(){
		getCmbObligatorio().removeAllItems();
		getCmbObligatorio().addItem(NOMBRE_OBLIGATORIO_SI);
		getCmbObligatorio().addItem(NOMBRE_OBLIGATORIO_NO);
	}
	
	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbObligatorio().setEnabled(true);
		cargarCombos();
		
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();

	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbObligatorio().setEnabled(true);
		getTblTipoBrief().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getTipoBriefVector() {
		return this.tipoBriefVector;
	}
	
	public void setTipoBriefVector(Vector tipoBriefVector) {
		this.tipoBriefVector = tipoBriefVector;
	}
	
	public int getTipoBriefSeleccionado() {
		return this.tipoBriefSeleccionado;
	}
	
	public void setTipoBriefSeleccionado(int tipoBriefSeleccionado) {
		this.tipoBriefSeleccionado = tipoBriefSeleccionado;
	}
	
	public TipoBriefIf getTipoBriefActualizadoIf() {
		return this.tipoBriefActualizadoIf;
	}
	
	public void setTipoBriefActualizadoIf(TipoBriefIf tipoBriefActualizadoIf) {
		this.tipoBriefActualizadoIf = tipoBriefActualizadoIf;
	}
	 
}
