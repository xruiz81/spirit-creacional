package com.spirit.inventario.gui.model;

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
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.ColorData;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.gui.panel.JPColor;
import com.spirit.inventario.session.ColorSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ColorModel extends JPColor {
	private static final long serialVersionUID = 3257283617339029297L;	
	protected TableModel dataModel;
	ColorIf color;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private Vector colorVector = new Vector();
	private int colorSeleccionado;
	private ColorIf colorActualizadoIf;
	private DefaultTableModel tableModel;
	
	public ColorModel() {
		initKeyListeners();
		setSorterTable(getTblColor());
		getTblColor().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblColor().addMouseListener(oMouseAdapterTblColor);
		getTblColor().addKeyListener(oKeyAdapterTblColor);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	MouseListener oMouseAdapterTblColor = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblColor = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setColorSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			colorActualizadoIf = (ColorIf)  getColorVector().get(getColorSeleccionado());
			getTxtNombre().setText(getColorActualizadoIf().getNombre());
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				ColorData data = new ColorData();
				data.setNombre(this.getTxtNombre().getText());
				SessionServiceLocator.getColorSessionService().addColor(data);
				SpiritAlert.createAlert("Color guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("color",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setColorActualizadoIf((ColorIf) getColorVector().get(getColorSeleccionado()));
				getColorActualizadoIf().setNombre(getTxtNombre().getText());
				SessionServiceLocator.getColorSessionService().saveColor(getColorActualizadoIf());
				getColorVector().setElementAt(getColorActualizadoIf(), getColorSeleccionado());
				setColorActualizadoIf(null);
				SpiritAlert.createAlert("Color actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("color",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}		
	}
	
	public void delete() {
		try {
			ColorIf colorIf = (ColorIf) getColorVector().get(getColorSeleccionado());
			SessionServiceLocator.getColorSessionService().deleteColor(colorIf.getId());
			SpiritAlert.createAlert("Color eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("color",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	private void agregarFilasTabla(ColorIf colorIf, Vector<String> fila){
		fila.add(colorIf.getNombre());		
	}
	
	private void cargarTabla() {
		try {			
			Collection color = SessionServiceLocator.getColorSessionService().getColorList(); 
			Iterator colorIterator = color.iterator();
			
			if(!getColorVector().isEmpty()){
				getColorVector().removeAllElements();
			}
			
			while (colorIterator.hasNext()) {
				ColorIf colorIf = (ColorIf) colorIterator.next();
				tableModel = (DefaultTableModel) getTblColor().getModel();
				Vector<String> fila = new Vector<String>();
				getColorVector().add(colorIf);
				agregarFilasTabla(colorIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblColor(), color, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateFields() {
		String strNombre = this.getTxtNombre().getText();
		Collection color = null;
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el color !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void clean() {
		this.getTxtNombre().setText("");
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblColor().getModel();
		
		for(int i= this.getTblColor().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {
		setSaveMode();
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();		
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtNombre().setEnabled(true);
		getTblColor().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getColorVector() {
		return this.colorVector;
	}
	
	public void setColorVector(Vector colorVector) {
		this.colorVector = colorVector;
	}
	
	public int getColorSeleccionado() {
		return this.colorSeleccionado;
	}
	
	public void setColorSeleccionado(int colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}
	
	public ColorIf getColorActualizadoIf() {
		return this.colorActualizadoIf;
	}
	
	public void setColorActualizadoIf(ColorIf colorActualizadoIf) {
		this.colorActualizadoIf = colorActualizadoIf;
	}
	
	 
}