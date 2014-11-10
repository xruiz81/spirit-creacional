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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.MarcaProductoData;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.ProductoClienteData;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.panel.JPProducto;
import com.spirit.util.TextChecker;

public class ProductoModel extends JPProducto{
	private static final long serialVersionUID = -6633355195363191951L;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private CorporacionCriteria corporacionCriteria;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected EmpleadoIf creativoIf;
	protected EmpleadoIf ejecutivoIf;
	protected CampanaIf campanaIf;
	private List<MarcaProductoIf> marcaProductoColeccion = new ArrayList<MarcaProductoIf>();
	private List<ProductoClienteIf> productoClienteColeccion = new ArrayList<ProductoClienteIf>();
	private List<ProductoClienteIf> productoClienteEliminadoColeccion = new ArrayList<ProductoClienteIf>();
	private DefaultTableModel modelMarca;
	private DefaultTableModel modelProductoCliente;
	final JPopupMenu  popupMenuMarca = new JPopupMenu();
	final JPopupMenu  popupMenuProductoCliente = new JPopupMenu();
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TIPO_CLIENTE = "C";
	private int selectedRowTblMarca = -1;
	private int selectedRowTblProducto = -1;
	private MarcaProductoIf marcaProductoSeleccionada;
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no}; 
	
	public ProductoModel(){
		setSorterTable(getTblProducto());
		anchoColumnasTabla();
		initKeyListeners();
	    showSaveMode();
	    initListeners();
	    getTblMarca().addMouseListener(oMouseAdapterTblMarca);	    
		getTblMarca().addKeyListener(oKeyAdapterTblMarca);
	    getTblProducto().addMouseListener(oMouseAdapterTblProducto);	    
		getTblProducto().addKeyListener(oKeyAdapterTblProducto);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaMarca = getTblMarca().getColumnModel().getColumn(0);
		anchoColumnaMarca.setPreferredWidth(50);
		anchoColumnaMarca = getTblMarca().getColumnModel().getColumn(1);
		anchoColumnaMarca.setPreferredWidth(300);
		anchoColumnaMarca = getTblMarca().getColumnModel().getColumn(2);
		anchoColumnaMarca.setPreferredWidth(70);
				
		TableColumn anchoColumnaProducto = getTblProducto().getColumnModel().getColumn(0);
		anchoColumnaProducto.setPreferredWidth(30);
		anchoColumnaProducto = getTblProducto().getColumnModel().getColumn(1);
		anchoColumnaProducto.setPreferredWidth(200);
		anchoColumnaProducto = getTblProducto().getColumnModel().getColumn(2);
		anchoColumnaProducto.setPreferredWidth(50);
		anchoColumnaProducto = getTblProducto().getColumnModel().getColumn(3);
		anchoColumnaProducto.setPreferredWidth(180);
		anchoColumnaProducto = getTblProducto().getColumnModel().getColumn(4);
		anchoColumnaProducto.setPreferredWidth(180);		
		
		
		//if(Parametros.getNombreEmpresa())
		
		System.out.println("asdasd:"+Parametros.getNombreEmpresa());
			String nombreEmpresa="";
			nombreEmpresa=Parametros.getNombreEmpresa();
		if(nombreEmpresa!=null)
		{
			boolean bandera=false;
			if(nombreEmpresa.equals("AXIS")) bandera=true;
			
			if(nombreEmpresa.length()>=7 && nombreEmpresa.substring(0,7).equals("ILMYZAC")) bandera=true;
				
			if(bandera)
			{
				getLblCreativoProducto().setText("Ejecutivo de Medios:");
				getLblEjecutivoProducto().setText("Ejecutivo de Cuentas:");
			}
		}
	}

	private void initKeyListeners() {
		getPanelBotonesMarca().setVisible(false);
		getTxtMarca().setEditable(false);
		getTxtCodigoMarca().setEditable(false);
		getTxtCodigoProducto().setEditable(false);
		getTxtCodigoMarca().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtCodigoProducto().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombreMarca().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
        getTxtNombreProducto().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
        getTxtCorporacion().setEditable(false);
        getTxtCliente().setEditable(false);
        getTxtCreativoProducto().setEditable(false);
        getTxtEjecutivoProducto().setEditable(false);
        
		getBtnAgregarMarca().setText("");
		getBtnActualizarMarca().setText("");
		getBtnEliminarMarca().setText("");
		getBtnAgregarProducto().setText("");
		getBtnActualizarProducto().setText("");
		getBtnEliminarProducto().setText("");
		//---- btnBuscarCorporacion ----
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporacion");		
		//---- btnBuscarCliente ----
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");		
		//---- btnBuscarCreativo ----
		getBtnBuscarCreativo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCreativo().setToolTipText("Buscar Creativo");		
		//---- btnBorrarCreativo
		getBtnBorrarCreativo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnBorrarCreativo().setToolTipText("Borro el Creativo seleccionado");		
		//---- btnBuscarEjecutivo ----
		getBtnBuscarEjecutivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarEjecutivo().setToolTipText("Buscar Ejecutivo");		
		//---- btnBorrarEjecutivo
		getBtnBorrarEjecutivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnBorrarEjecutivo().setToolTipText("Borro el Ejecutivo seleccionado");
		//---- btnAgregarProductoCliente ----
		getBtnAgregarMarca().setToolTipText("Agregar Marca");
		getBtnAgregarMarca().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		//---- btnActualizarProductoCliente ----
		getBtnActualizarMarca().setToolTipText("Actualizar Marca");
		getBtnActualizarMarca().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		//---- btnEliminarProductoCliente ----
		getBtnEliminarMarca().setToolTipText("Eliminar Marca");
		getBtnEliminarMarca().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		//---- btnAgregarProductoCliente ----
		getBtnAgregarProducto().setToolTipText("Agregar Producto");
		getBtnAgregarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		//---- btnActualizarProductoCliente ----
		getBtnActualizarProducto().setToolTipText("Actualizar Producto");
		getBtnActualizarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		//---- btnEliminarProductoCliente ----
		getBtnEliminarProducto().setToolTipText("Eliminar Producto");
		getBtnEliminarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}
	
	public void initListeners(){
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clean();
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					getTxtCliente().setText("");
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes",idCorporacion,CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clean();
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					if (corporacionIf == null) {
						try {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					//if(getMode()== SpiritMode.FIND_MODE)
						find();
				}
			}
		});
		
		// Manejo los eventos de Buscar Creativo
		getBtnBuscarCreativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Creativos",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					creativoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtCreativoProducto().setText(creativoIf.getNombres() + " " + creativoIf.getApellidos());
					getBtnBorrarCreativo().setEnabled(true);
				}
			}
		});
		
		getBtnBorrarCreativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtCreativoProducto().setText("");
				getBtnBorrarCreativo().setEnabled(false);
				creativoIf = null;
				getTxtCreativoProducto().grabFocus();
			}
		});
		
		// Manejo los eventos de Buscar Ejecutivo
		getBtnBuscarEjecutivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria(
			 			"Ejecutivos",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					ejecutivoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEjecutivoProducto().setText(ejecutivoIf.getNombres() + " " + ejecutivoIf.getApellidos());
					getBtnBorrarEjecutivo().setEnabled(true);
				}
			}
		});
		
		getBtnBorrarEjecutivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtEjecutivoProducto().setText("");
				getBtnBorrarEjecutivo().setEnabled(false);
				ejecutivoIf = null;
				getTxtEjecutivoProducto().grabFocus();
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de marca
	    JMenuItem itemEliminarMarca = new JMenuItem("Eliminar Marca");
	    popupMenuMarca.add(itemEliminarMarca);
	    
	    //Añado el listener de menupopup
	    /*itemEliminarMarca.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarMarca();
	    	}
	    });
	    
	    getBtnAgregarMarca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarMarca();			
			}
		});
			
		getBtnActualizarMarca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarMarca();			
			}
		});
		
		getBtnEliminarMarca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarMarca();			
			}
		});*/
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de producto
	    JMenuItem itemEliminarProductoCliente = new JMenuItem("Eliminar Producto");
	    popupMenuProductoCliente.add(itemEliminarProductoCliente);
	    
	    //Añado el listener de menupopup
	    itemEliminarProductoCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarProductoCliente();
	    	}
	    });
	    
	    getBtnAgregarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarProductoCliente();			
			}
		});
			
		getBtnActualizarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarProductoCliente();			
			}
		});
		
		getBtnEliminarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProductoCliente();			
			}
		});
	}
	
	MouseListener oMouseAdapterTblMarca = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblProducto().getModel().getRowCount()>0)
            	popupMenuMarca.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowMarcaForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblMarca = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowMarcaForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowMarcaForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSelectedRowTblMarca(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			marcaProductoSeleccionada = (MarcaProductoIf) marcaProductoColeccion.get(getSelectedRowTblMarca());
     			            	
     		getTxtCodigoMarca().setText(marcaProductoSeleccionada.getCodigo());
			getTxtNombreMarca().setText(marcaProductoSeleccionada.getNombre());
        	
        	if ("I".equals(marcaProductoSeleccionada.getEstado()))
				getCmbEstadoMarca().setSelectedItem("INACTIVO");
			else
				getCmbEstadoMarca().setSelectedItem("ACTIVO");
        	
        	cleanProducto();
        	getTxtMarca().setText(marcaProductoSeleccionada.getNombre());
        	findProductos();
        	showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblProducto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblProducto().getModel().getRowCount()>0)
            	popupMenuProductoCliente.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowProductoForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblProducto = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowProductoForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowProductoForUpdate(ComponentEvent evt) {
		try { 
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setSelectedRowTblProducto(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
         		ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(getSelectedRowTblProducto());
         			            	
         		getTxtCodigoProducto().setText(productoClienteTemp.getCodigo());
				getTxtNombreProducto().setText(productoClienteTemp.getNombre());
            	
            	if ("I".equals(productoClienteTemp.getEstado()))
    				getCmbEstadoProducto().setSelectedItem("INACTIVO");
    			else
    				getCmbEstadoProducto().setSelectedItem("ACTIVO");
            	
            	creativoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getCreativoId());
				getTxtCreativoProducto().setText(creativoIf.getNombres() + " " + creativoIf.getApellidos());
				ejecutivoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getEjecutivoId());
				getTxtEjecutivoProducto().setText(ejecutivoIf.getNombres() + " " + ejecutivoIf.getApellidos());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al seleccionar una fila de la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	/*private void agregarMarca() {
		if (validateFieldsMarca()) {
			boolean existeMarca = false;
			for(int i=0;i<marcaProductoColeccion.size();i++){
				MarcaProductoIf marcaProductoTemp = (MarcaProductoIf) marcaProductoColeccion.get(i);

				if(marcaProductoTemp.getNombre().equals(getTxtNombreMarca().getText())){
					existeMarca = true;
					break;
				}
			}
			
			modelMarca =  (DefaultTableModel) getTblMarca().getModel();
			Vector<String> filaMarca = new Vector<String>();

			if(existeMarca == false) {
				MarcaProductoData data = new MarcaProductoData();
				
				data.setEstado(getCmbEstadoMarca().getSelectedItem().toString().substring(0, 1));
				data.setNombre(getTxtNombreMarca().getText());
				data.setClienteId(clienteIf.getId());
				data.setFechaCreacion(new Date((new java.util.Date()).getTime()));
				marcaProductoColeccion.add(data);

				filaMarca.add("");
				filaMarca.add(getTxtNombreMarca().getText());
				filaMarca.add(getCmbEstadoMarca().getSelectedItem().toString());
				
				modelMarca.addRow(filaMarca);
				cleanMarca();
				
			} else {
				SpiritAlert.createAlert("La Marca ya se encuentra agregada!", SpiritAlert.INFORMATION);
			}
		}else{
			SpiritAlert.createAlert(" No se pudo ingresar la Marca!",SpiritAlert.WARNING);
		}
	}
	
	private void actualizarMarca() {
		boolean existeMarca = false;
		for(int i=0;i<marcaProductoColeccion.size();i++){
			MarcaProductoIf marcaProductoTemp = (MarcaProductoIf) marcaProductoColeccion.get(i);

			if (i != getSelectedRowTblProducto()) {
				if(marcaProductoTemp.getNombre().equals(getTxtNombreMarca().getText())){
					existeMarca = true;
					break;
				}
			}
		}
		
		modelMarca =  (DefaultTableModel) getTblMarca().getModel();
		if (validateFieldsMarca()) {
			if(existeMarca == false){
				MarcaProductoIf data = (MarcaProductoIf) marcaProductoColeccion.get(getSelectedRowTblProducto());
				data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
				data.setNombre(getTxtNombreProducto().getText());
				data.setClienteId(clienteIf.getId());
				
				//Actualizar en la coleccion de resumenPlanMedioColeccion el registro que fue cambiado
				marcaProductoColeccion.set(getSelectedRowTblMarca(), data);
				
				//Actualizo en la tablaProductoCliente
				modelMarca.setValueAt(getTxtCodigoMarca().getText(),getSelectedRowTblMarca(),0);
				modelMarca.setValueAt(getTxtNombreMarca().getText(),getSelectedRowTblMarca(),1);
				modelMarca.setValueAt(getCmbEstadoMarca().getSelectedItem().toString(),getSelectedRowTblMarca(),2);
				cleanMarca();
			}
			else{
				SpiritAlert.createAlert("La Marca ya se encuentra agregada!", SpiritAlert.INFORMATION);
			}
		}else{
			SpiritAlert.createAlert("Debe seleccionar el registro a actualizar de la tabla Marca!",SpiritAlert.INFORMATION);	
		}
	}
	
	private void eliminarMarca() {
		if (getTblMarca().getSelectedRow()!=-1){
			MarcaProductoIf marcaProductoTemp = (MarcaProductoIf) marcaProductoColeccion.get(getSelectedRowTblMarca());
			marcaProductoEliminadaColeccion.add(marcaProductoTemp);    			
			marcaProductoColeccion.remove(getSelectedRowTblMarca());
			modelMarca.removeRow(getSelectedRowTblMarca());			
			cleanMarca();
		} else {
			SpiritAlert.createAlert("Debe seleccionar en la tabla la marca que desea eliminar!",SpiritAlert.WARNING);
		}
	}*/
	
	private void agregarProductoCliente() {
		if (validateFieldsProductoCliente(true)) {			
			modelProductoCliente =  (DefaultTableModel) getTblProducto().getModel();
			Vector<String> filaProductoCliente = new Vector<String>();

			ProductoClienteData data = new ProductoClienteData();
			
			//data.setCodigo(getTxtCodigoProducto().getText());
			data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
			data.setNombre(getTxtNombreProducto().getText().toUpperCase());
			data.setCreativoId(creativoIf.getId());
			data.setEjecutivoId(ejecutivoIf.getId());
			data.setClienteId(clienteIf.getId());
			data.setMarcaProductoNombre(getTxtNombreMarca().getText());
			productoClienteColeccion.add(data);

			filaProductoCliente.add("");
			filaProductoCliente.add(getTxtNombreProducto().getText());
			filaProductoCliente.add(getCmbEstadoProducto().getSelectedItem().toString());
			filaProductoCliente.add(creativoIf.getNombres() + " " + creativoIf.getApellidos());
			filaProductoCliente.add(ejecutivoIf.getNombres() + " " + ejecutivoIf.getApellidos());
			
			modelProductoCliente.addRow(filaProductoCliente);
			cleanProducto();
		}
	}
	
	private void actualizarProductoCliente() {
		if (validateFieldsProductoCliente(false)) {
			modelProductoCliente =  (DefaultTableModel) getTblProducto().getModel();
			
			ProductoClienteIf data = (ProductoClienteIf) productoClienteColeccion.get(getSelectedRowTblProducto());
			//data.setCodigo(getTxtCodigoProducto().getText());
			data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
			data.setNombre(getTxtNombreProducto().getText().toUpperCase());
			data.setCreativoId(creativoIf.getId());
			data.setEjecutivoId(ejecutivoIf.getId());
			data.setClienteId(clienteIf.getId());
			data.setMarcaProductoNombre(getTxtNombreMarca().getText());
			
			//Actualizar en la coleccion de resumenPlanMedioColeccion el registro que fue cambiado
			productoClienteColeccion.set(getSelectedRowTblProducto(),data);
			
			//Actualizo en la tablaProductoCliente
			modelProductoCliente.setValueAt(getTxtCodigoProducto().getText(),getSelectedRowTblProducto(),0);
			modelProductoCliente.setValueAt(getTxtNombreProducto().getText(),getSelectedRowTblProducto(),1);
			modelProductoCliente.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getSelectedRowTblProducto(),2);
			modelProductoCliente.setValueAt(creativoIf.getNombres() + " " + creativoIf.getApellidos(),getSelectedRowTblProducto(),3);
			modelProductoCliente.setValueAt(ejecutivoIf.getNombres() + " " + ejecutivoIf.getApellidos(),getSelectedRowTblProducto(),4);

			cleanProducto();			
		}
	}
	
	private void eliminarProductoCliente() {
		if (getTblProducto().getSelectedRow()!=-1){
			int opcion = JOptionPane.showOptionDialog(null, "¿Esta seguro que desea eliminar el Producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(getSelectedRowTblProducto());
				productoClienteEliminadoColeccion.add(productoClienteTemp);    			
				productoClienteColeccion.remove(getSelectedRowTblProducto());
				modelProductoCliente.removeRow(getSelectedRowTblProducto());			
				cleanProducto();
			}			
		} else {
			SpiritAlert.createAlert("Primero debe seleccionar en la tabla el Producto que desea eliminar!",SpiritAlert.WARNING);
		}	
	}
	
	/*private void cleanMarca() {
		getTxtCodigoMarca().setText("");
		getTxtNombreMarca().setText("");
	}*/
	
	private void cleanProducto() {
		creativoIf = null;
		ejecutivoIf = null;		
		getTxtCodigoProducto().setText("");
		getTxtNombreProducto().setText("");
		getTxtCreativoProducto().setText("");
		getTxtEjecutivoProducto().setText("");
	}
	
	public void registrarMarcaProductoSeleccionada(){
		if(marcaProductoSeleccionada == null){
			marcaProductoSeleccionada = new MarcaProductoData();
			marcaProductoSeleccionada.setFechaCreacion(new Date((new java.util.Date()).getTime()));
		}		
		marcaProductoSeleccionada.setClienteId(clienteIf.getId());
		marcaProductoSeleccionada.setEstado(getCmbEstadoMarca().getSelectedItem().toString().substring(0,1));
		marcaProductoSeleccionada.setNombre(getTxtNombreMarca().getText().toUpperCase());
		marcaProductoSeleccionada.setTipo(TIPO_CLIENTE);
		marcaProductoSeleccionada.setEmpresaId(Parametros.getIdEmpresa());
	}
	
	public void setearNombreMarca(){
		for(ProductoClienteIf productoCliente : productoClienteColeccion){
			if((productoCliente.getMarcaProductoId() != null) && 
					(marcaProductoSeleccionada.getId().compareTo(productoCliente.getMarcaProductoId()) == 0)){
				productoCliente.setMarcaProductoNombre(marcaProductoSeleccionada.getNombre());
			}			
		}
	}
	
	public void save() {
		update();
		//SpiritAlert.createAlert("Debe seleccionar un Cliente!",SpiritAlert.INFORMATION);
	}
	
	public void update() {
		try {
			if (validateFields()) {
				registrarMarcaProductoSeleccionada();
				if(marcaProductoSeleccionada.getId() != null)setearNombreMarca();
				SessionServiceLocator.getProductoClienteSessionService().procesarProductoClienteColeccion(marcaProductoSeleccionada, productoClienteColeccion, productoClienteEliminadoColeccion);
				SpiritAlert.createAlert("Marca - Producto actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la infomación!" ,SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			if(marcaProductoSeleccionada != null){
				int opcion = JOptionPane.showOptionDialog(null, "Elimando la Marca, también elimina los Productos de la marca, ¿Está seguro de eliminarla?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					SessionServiceLocator.getProductoClienteSessionService().eliminarMarcaProducto(marcaProductoSeleccionada);
					SpiritAlert.createAlert("Marca - Producto ha sido eliminada con éxito",SpiritAlert.INFORMATION);
					showSaveMode();
				}				
			}else{
				SpiritAlert.createAlert("Primero debe seleccionar en la tabla la Marca que desea eliminar",SpiritAlert.WARNING);
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
	
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void find() {
		try {
			if(clienteIf != null){
				Map parameterMap = new HashMap();
				parameterMap.put("clienteId", clienteIf.getId());
				parameterMap.put("tipo", TIPO_CLIENTE);
				Collection marcasColeccion = SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByQuery(parameterMap);
				Iterator itMarcasColeccion = marcasColeccion.iterator();
				
				while(itMarcasColeccion.hasNext()){			
					MarcaProductoIf marcaProductoTemp = (MarcaProductoIf) itMarcasColeccion.next();
					
					modelMarca = (DefaultTableModel) getTblMarca().getModel();
					Vector<String> filaMarca = new Vector<String>();
					marcaProductoColeccion.add(marcaProductoTemp);
				
					String estadoMarca = "ACTIVO";
					if ("I".equals(marcaProductoTemp.getEstado()))
						estadoMarca = "INACTIVO";
					
					filaMarca.add(marcaProductoTemp.getCodigo());
					filaMarca.add(marcaProductoTemp.getNombre());
					filaMarca.add(estadoMarca);
						
					modelMarca.addRow(filaMarca);
				}
				
				if(marcaProductoColeccion.size() > 0){
					this.showUpdateMode();
				}else{
					SpiritAlert.createAlert("El Cliente no tiene ninguna Marca ingresada",SpiritAlert.INFORMATION);
				}
			
			}else{
				SpiritAlert.createAlert("Debe escoger un Cliente para realizar la búsqueda",SpiritAlert.WARNING);
				showFindMode();
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void findProductos() {
		try {
			cleanTblProducto();
			productoClienteColeccion = null;
			productoClienteColeccion = new ArrayList<ProductoClienteIf>();
			
			Map aMap = new HashMap();
			aMap.put("clienteId", clienteIf.getId());
			aMap.put("marcaProductoId", marcaProductoSeleccionada.getId());
			Collection productosColeccion = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQuery(aMap);
			Iterator itProductoClienteColeccion = productosColeccion.iterator();
			
			modelProductoCliente = (DefaultTableModel) getTblProducto().getModel();
			
			while(itProductoClienteColeccion.hasNext()){					
				ProductoClienteIf productoClienteTemp = (ProductoClienteIf) itProductoClienteColeccion.next();
				
				Vector<String> filaProductoCliente = new Vector<String>();

				productoClienteColeccion.add(productoClienteTemp);
			
				String estadoProducto = "ACTIVO";
				if ("I".equals(productoClienteTemp.getEstado()))
					estadoProducto = "INACTIVO";
				
				EmpleadoIf creativoTemp = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getCreativoId());
				EmpleadoIf ejecutivoTemp = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getEjecutivoId());
				
				filaProductoCliente.add(productoClienteTemp.getCodigo());
				filaProductoCliente.add(productoClienteTemp.getNombre());
				filaProductoCliente.add(estadoProducto);
				filaProductoCliente.add(creativoTemp.getNombres() + " " + creativoTemp.getApellidos());
				filaProductoCliente.add(ejecutivoTemp.getNombres() + " " + ejecutivoTemp.getApellidos());
					
				modelProductoCliente.addRow(filaProductoCliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCorporacion().getText()) && "".equals(this.getTxtCliente().getText()))
			return true;
		else
			return false;
	}

	public boolean validateFields() {
		String strCorporacion = this.getTxtCorporacion().getText();
		String strCliente = this.getTxtCliente().getText();
		String strMarca = this.getTxtNombreMarca().getText();
		
		if ((("".equals(strCorporacion)) || (strCorporacion == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación !!", SpiritAlert.WARNING);
			getBtnBuscarCorporacion().grabFocus();
			return false;
		}
		if ((("".equals(strCliente)) || (strCliente == null))) {
			SpiritAlert.createAlert("Debe seleccionar un cliente !!", SpiritAlert.WARNING);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		if ((("".equals(strMarca)) || (strMarca == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Marca!", SpiritAlert.WARNING);
			getTxtNombreMarca().grabFocus();
			return false;
		}
		
		for(int i=0;i<marcaProductoColeccion.size();i++){
			MarcaProductoIf marcaProductoTemp = (MarcaProductoIf) marcaProductoColeccion.get(i);
			if(this.getMode() == SpiritMode.SAVE_MODE){
				if(marcaProductoTemp.getNombre().equals(getTxtNombreMarca().getText())){
					SpiritAlert.createAlert("La Marca " + marcaProductoTemp.getNombre() + " ya se encuentra agregada!", SpiritAlert.WARNING);
					getTxtNombreMarca().grabFocus();
					return false;
				}
			}else if(this.getMode() == SpiritMode.UPDATE_MODE){
				if(marcaProductoTemp.getNombre().equals(getTxtNombreMarca().getText()) && !marcaProductoSeleccionada.getNombre().equals(marcaProductoTemp.getNombre())){
					SpiritAlert.createAlert("La Marca " + marcaProductoTemp.getNombre() + " ya se encuentra agregada!", SpiritAlert.WARNING);
					getTxtNombreMarca().grabFocus();
					return false;
				}
			}			
		}
		
		if(productoClienteColeccion.isEmpty()){
			SpiritAlert.createAlert("Debe al menos ingresar 1 Producto para la marca!", SpiritAlert.WARNING);
			getJtpProducto().setSelectedIndex(1);
			getTxtNombreProducto().grabFocus();
			return false;
		}
	
		return true;
	}

	public void clean() {
		marcaProductoSeleccionada = null;
		corporacionIf = null;
		clienteIf = null;
		creativoIf = null;
		ejecutivoIf = null;
		marcaProductoColeccion = null;
		marcaProductoColeccion = new ArrayList<MarcaProductoIf>();
		productoClienteColeccion = null;
		productoClienteColeccion = new ArrayList<ProductoClienteIf>();
		productoClienteEliminadoColeccion = null;
		productoClienteEliminadoColeccion = new ArrayList<ProductoClienteIf>();
		
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtCodigoMarca().setText("");
		getTxtNombreMarca().setText("");
		getTxtMarca().setText("");
		getTxtCodigoProducto().setText("");
		getTxtNombreProducto().setText("");
		getTxtCreativoProducto().setText("");
		getTxtEjecutivoProducto().setText("");
		cleanTblMarca();
		cleanTblProducto();
		getJtpProducto().setSelectedIndex(0);
		getTxtNombreMarca().grabFocus();
	}

	private void cleanTblMarca() {
		DefaultTableModel model = (DefaultTableModel) getTblMarca().getModel();
		for(int i= this.getTblMarca().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTblProducto() {
		DefaultTableModel model = (DefaultTableModel) getTblProducto().getModel();
		for(int i= this.getTblProducto().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCliente().setBackground(getBackground());
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getTxtNombreProducto().setEnabled(true);
		getBtnBuscarCreativo().setEnabled(true);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(true);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProducto().setEnabled(true);
		getBtnActualizarProducto().setEnabled(true);
		getCmbEstadoMarca().setSelectedItem("ACTIVO");
		getCmbEstadoProducto().setSelectedItem("ACTIVO");
		getTxtCodigoProducto().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCliente().setBackground(getBackground());
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getTxtNombreProducto().setEnabled(true);
		getBtnBuscarCreativo().setEnabled(true);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(true);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProducto().setEnabled(true);
		getBtnActualizarProducto().setEnabled(true);
		getCmbEstadoProducto().setSelectedIndex(0);
	
		getTxtCodigoProducto().grabFocus();
	}

	public void showFindMode() {
		getTxtCliente().setBackground(Parametros.findColor);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbEstadoProducto().setEnabled(false);
		getTxtNombreProducto().setEnabled(false);
		getBtnBuscarCreativo().setEnabled(false);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(false);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProducto().setEnabled(false);
		getBtnActualizarProducto().setEnabled(false);
		getCmbEstadoProducto().setSelectedIndex(-1);
	
		getBtnBuscarCliente().grabFocus();
	}
	
	public boolean validateFieldsProductoCliente(boolean agregar){
		String strNombre = this.getTxtNombreProducto().getText();
		String strCreativo = this.getTxtCreativoProducto().getText();
		String strEjecutivo = this.getTxtEjecutivoProducto().getText();
		
		if(!agregar && (getSelectedRowTblProducto() == -1)){			
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Producto Cliente!",SpiritAlert.WARNING);
			getTxtNombreProducto();
			return false;
		}
		
		if(clienteIf == null){
			SpiritAlert.createAlert("Debe primero seleccionar un Cliente!",SpiritAlert.WARNING);
			getJtpProducto().setSelectedIndex(0);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtNombreMarca().getText())) || (getTxtNombreMarca().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Marca del producto !!"
					,SpiritAlert.WARNING);
			getJtpProducto().setSelectedIndex(0);
			getTxtNombreMarca().grabFocus();
			return false;
		}
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getTxtNombreProducto().grabFocus();
			return false;
		}
		
		if ((("".equals(strCreativo)) || (strCreativo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Creativo para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCreativo().grabFocus();
			return false;
		}
		
		if ((("".equals(strEjecutivo)) || (strEjecutivo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Ejecutivo para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarEjecutivo().grabFocus();
			return false;
		}
		
		for(int i=0;i<productoClienteColeccion.size();i++){
			ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(i);
			
			if(agregar){
				if(productoClienteTemp.getNombre().equals(getTxtNombreProducto().getText()) 
				&& productoClienteTemp.getCreativoId().equals(creativoIf.getId()) 
				&& productoClienteTemp.getEjecutivoId().equals(ejecutivoIf.getId())){
					SpiritAlert.createAlert("El Producto " + productoClienteTemp.getNombre() + " ya se encuentra agregado!", SpiritAlert.WARNING);
					getTxtNombreProducto().grabFocus();
					return false;
				}
			}else if(i != getSelectedRowTblProducto()) {
				if(productoClienteTemp.getNombre().equals(getTxtNombreProducto().getText()) 
			    && productoClienteTemp.getCreativoId().equals(creativoIf.getId()) 
				&& productoClienteTemp.getEjecutivoId().equals(ejecutivoIf.getId())){
					SpiritAlert.createAlert("El Producto " + productoClienteTemp.getNombre() + " ya se encuentra agregado!", SpiritAlert.WARNING);
					getTxtNombreProducto().grabFocus();
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void setSelectedRowTblProducto(int row) {
		this.selectedRowTblProducto = row;
	}
	
	public int getSelectedRowTblProducto() {
		return this.selectedRowTblProducto;
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public int getSelectedRowTblMarca() {
		return selectedRowTblMarca;
	}

	public void setSelectedRowTblMarca(int selectedRowTblMarca) {
		this.selectedRowTblMarca = selectedRowTblMarca;
	}
}
