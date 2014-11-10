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
import com.spirit.general.entity.LineaData;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPLinea;
import com.spirit.general.session.LineaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class LineaModel extends JPLinea {
	
	private static final long serialVersionUID = 3170050850812218533L;
	JDPopupFinderModel popupFinder;
	ArrayList lista;

	private static final int MAX_LONGITUD_CODIGO = 4 ;
	private static final int MAX_LONGITUD_NOMBRE = 30 ;
	private static final int MAX_LONGITUD_NIVEL = 2 ;
	private static final String NOMBRE_ESTADO_ACEPTAITEM_SI = "SI";
	private static final String NOMBRE_ESTADO_ACEPTAITEM_NO = "NO";
	private static final String ESTADO_ACEPTAITEM_SI = "S";
	private static final String ESTADO_ACEPTAITEM_NO = "N";
	private static final int NIVEL_JERARQUICO = 1;

	private Vector lineaVector = new Vector();
	private DefaultTableModel tableModel;
	private int lineaSeleccionada;
	private LineaIf lineaActualizadaIf;
   
	
	public LineaModel() {
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblLinea());
		getCmbPadre().addActionListener(new ComboBoxLineaHandler());
		showSaveMode();
		getTblLinea().addMouseListener(oMouseAdapterTblLinea);
		getTblLinea().addKeyListener(oKeyAdapterTblLinea);
		
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblLinea().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblLinea().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblLinea().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(200);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtNivel().addKeyListener(new TextChecker(MAX_LONGITUD_NIVEL));
	}
	
	MouseListener oMouseAdapterTblLinea = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblLinea = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setLineaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			setLineaActualizadaIf((LineaIf)  getLineaVector().get(getLineaSeleccionada()));
			getTxtCodigo().setText(getLineaActualizadaIf().getCodigo());
			getTxtNombre().setText(getLineaActualizadaIf().getNombre());
			
			try {
				//Agrego al combo los posibles padres para la linea mostrada
				Collection lineasPadres = SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
				Iterator it = lineasPadres.iterator();
				
				getCmbPadre().removeAllItems();
				getCmbPadre().addItem(null);
				
				//Si la linea leida es de primer nivel muestro como posible padre una linea nula
				if(lineaActualizadaIf.getLineaId()==null){
					while (it.hasNext()) {
						LineaIf bean = (LineaIf) it.next();
						if((bean.getNivel() <= lineaActualizadaIf.getNivel()) && (!lineaActualizadaIf.getId().equals(bean.getId())))
							getCmbPadre().addItem(bean);
					}
					
					if (getCmbPadre().getItemCount() > 0)
						getCmbPadre().setSelectedIndex(0);
				}
				else{
					while (it.hasNext()) {
						LineaIf bean = (LineaIf) it.next();
						if((bean.getNivel() <= lineaActualizadaIf.getNivel()) && (!lineaActualizadaIf.getId().equals(bean.getId()))){
							getCmbPadre().addItem(bean);
							if(lineaActualizadaIf.getLineaId().equals(bean.getId()))
								getCmbPadre().setSelectedItem(bean);
						}
					}
				}
				
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
			
			if(getLineaActualizadaIf().getAceptaitem().equals(ESTADO_ACEPTAITEM_SI))
				getCmbAceptaItem().setSelectedItem(NOMBRE_ESTADO_ACEPTAITEM_SI);
			else if(getLineaActualizadaIf().getAceptaitem().equals(ESTADO_ACEPTAITEM_NO))
				getCmbAceptaItem().setSelectedItem(NOMBRE_ESTADO_ACEPTAITEM_NO);
			
			showUpdateMode();
		}
	}

	public void save() {

		if (validateFields()) {
			LineaData data = new LineaData();
			
			data.setEmpresaId(Parametros.getIdEmpresa());
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setAceptaitem(this.getCmbAceptaItem().getSelectedItem().toString().substring(0, 1));
			//Seteo el Nivel de la Linea
			if (getCmbPadre().getSelectedItem()!= null) {
				data.setLineaId(((LineaIf) this.getCmbPadre().getSelectedItem()).getId());
				data.setNivel(Integer.parseInt(getTxtNivel().getText()));
			}
			else
				data.setNivel(NIVEL_JERARQUICO);
			
			try {
				SessionServiceLocator.getLineaSessionService().addLinea(data);
				SpiritAlert.createAlert("Línea guardada con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("linea",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error de formato en Nivel!",SpiritAlert.ERROR);
			}
		} 
	}

	public void delete() {
		try {
			if(SessionServiceLocator.getLineaSessionService().findLineaByLineaId(lineaActualizadaIf.getId()).isEmpty()){
				SessionServiceLocator.getLineaSessionService().deleteLinea(lineaActualizadaIf.getId());
				SpiritAlert.createAlert("Línea eliminada con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("linea",null);
				showSaveMode();
			}
			else{
				SpiritAlert.createAlert("No se puede eliminar el registro porque es línea padre!",SpiritAlert.WARNING);
				showSaveMode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		if (validateFields()) {
			lineaActualizadaIf.setCodigo(this.getTxtCodigo().getText());
			lineaActualizadaIf.setNombre(this.getTxtNombre().getText());
			lineaActualizadaIf.setNivel(Integer.parseInt(this.getTxtNivel().getText()));
			lineaActualizadaIf.setAceptaitem(this.getCmbAceptaItem().getSelectedItem().toString().substring(0, 1));
			
			//	Seteo el Nivel de la Linea
			if (getCmbPadre().getSelectedItem()!= null)  {
				lineaActualizadaIf.setLineaId(((LineaIf) this.getCmbPadre().getSelectedItem()).getId());
				lineaActualizadaIf.setNivel(Integer.parseInt(getTxtNivel().getText()));
			}
			else{
				lineaActualizadaIf.setNivel(NIVEL_JERARQUICO);
				lineaActualizadaIf.setLineaId(null);
			}

			actualizarNivelLineasHijas(lineaActualizadaIf.getId(),lineaActualizadaIf.getNivel());
			
			try {
				SessionServiceLocator.getLineaSessionService().saveLinea(lineaActualizadaIf);
				SpiritAlert.createAlert("Línea actualizada con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("linea",null);
				showSaveMode();

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al actualizar informacin!",SpiritAlert.ERROR);
			}
		}
	}

	public void actualizarNivelLineasHijas(Long idLineaPadre, int nivelLineaPadre){
		try {
			//Agrego al combo los posibles padres para la linea mostrada
			Collection lineasPadres = SessionServiceLocator.getLineaSessionService().findLineaByLineaId(idLineaPadre);
			Iterator it = lineasPadres.iterator();

			while (it.hasNext()) {
				LineaIf bean = (LineaIf) it.next();
				
				//Incremento en 1  el nivel del nodo hijo
				bean.setNivel(nivelLineaPadre+1);
				//Lo actualizio en la base 
				SessionServiceLocator.getLineaSessionService().saveLinea(bean);
				//Modifico el nivel de las lineas hijas
				actualizarNivelLineasHijas(bean.getId(), bean.getNivel());
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection linea = null;
		boolean codigoRepetido = false;
		
		try {
			linea = SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator lineaIt = linea.iterator();
		
		while (lineaIt.hasNext()) {
			LineaIf lineaIf = (LineaIf) lineaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(lineaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(lineaIf.getCodigo())) 
					if (this.lineaActualizadaIf.getId().equals(lineaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo de la Línea está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para la Línea !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Línea !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbAceptaItem().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar si acepta item !!",SpiritAlert.WARNING);
			getCmbAceptaItem().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		lineaActualizadaIf = null;
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtNivel().setText("");
		this.getCmbPadre().setSelectedItem(null);
		this.getCmbPadre().removeAllItems();
		this.getCmbAceptaItem().setSelectedItem(null);
		this.getCmbAceptaItem().removeAllItems();
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblLinea().getModel();
		for(int i= this.getTblLinea().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	
	public void cargarCombos(){
		getCmbAceptaItem().addItem(NOMBRE_ESTADO_ACEPTAITEM_SI);
		getCmbAceptaItem().addItem(NOMBRE_ESTADO_ACEPTAITEM_NO);
		
		try {
			//Cargo enun arreglo todas las lineas existentes
			Collection lineasPadres = SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
			Iterator it = lineasPadres.iterator();
			
			while (it.hasNext()) {
				LineaIf bean = (LineaIf) it.next();
				getCmbPadre().addItem(bean);
			}
			getCmbPadre().addItem(null);
			getCmbPadre().setSelectedIndex(-1);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtNivel().setEditable(false);
		getCmbAceptaItem().setEnabled(true);
		getCmbPadre().setEnabled(true);

		int nivelLineaActual = 1; //Seteo el nivel de linea a uno 
		getTxtNivel().setText(nivelLineaActual+"");
		
		cargarCombos();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		try {
			Collection linea = SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
			Iterator lineaIterator = linea.iterator();
			
			if(!getLineaVector().isEmpty()){
				getLineaVector().removeAllElements();
			}
			
			while (lineaIterator.hasNext()) {
				LineaIf lineaIf = (LineaIf) lineaIterator.next();
				
				tableModel = (DefaultTableModel) getTblLinea().getModel();
				Vector<String> fila = new Vector<String>();
				getLineaVector().add(lineaIf);
				
				agregarColumnasTabla(lineaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblLinea(), linea, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(LineaIf lineaIf, Vector<String> fila){
		
		fila.add(lineaIf.getCodigo());
		fila.add(lineaIf.getNombre());
		
		try {
			if(lineaIf.getLineaId() != null){
				LineaIf linea = SessionServiceLocator.getLineaSessionService().getLinea(lineaIf.getLineaId());
				fila.add(linea.getCodigo() + " - " + linea.getNombre());
			}
			else fila.add("");
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

 	private class ComboBoxLineaHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(getCmbPadre().getSelectedItem()!=null){
				LineaIf lineaIf = (LineaIf) getCmbPadre().getSelectedItem();
				int nivelLineaActual = lineaIf.getNivel() + 1; //Seteo el nivel de linea a uno mas de su linea padre seleccionada
				getTxtNivel().setText(nivelLineaActual+"");
			}
			else{
				int nivelLineaActual = 1; //Seteo el nivel de linea a uno 
				getTxtNivel().setText(nivelLineaActual+"");
			}
		}
	}
 

	public LineaIf getLineaActualizadaIf() {
		return lineaActualizadaIf;
	}

	public void setLineaActualizadaIf(LineaIf lineaActualizadaIf) {
		this.lineaActualizadaIf = lineaActualizadaIf;
	}
	
	public int getLineaSeleccionada() {
		return lineaSeleccionada;
	}

	public void setLineaSeleccionada(int lineaSeleccionada) {
		this.lineaSeleccionada = lineaSeleccionada;
	}

	public Vector getLineaVector() {
		return lineaVector;
	}

	public void setLineaVector(Vector lineaVector) {
		this.lineaVector = lineaVector;
	}

}
