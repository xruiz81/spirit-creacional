package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.spirit.client.ActivePanel;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPUsuarioCuenta;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.seguridad.entity.UsuarioCuentaData;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.util.SpiritComboBoxModel;

public class UsuarioCuentaModel extends JPUsuarioCuenta {
	private static final long serialVersionUID = 48840469248968202L;
	//Contiene los planes cuentas con cada uno de los arboles que poseen cada una de ellas
	private Map existePlanCuentasMap = new HashMap();
	private Icon customOpenIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/openNode.png");
	private Icon customClosedIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/closeNode.png");
	private Icon customLeafIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/leafNode.png");
	Collection cuentasTreeColeccion = new ArrayList();
	Iterator itCuentasTreeColeccion;
	//Creo la instancia de la raíz del menú personalizado
	DefaultMutableTreeNode rootCP = null;
	// Contiene los nodos ya insertados en el árbol Original
	Map existeNodoMapCC = new LinkedHashMap();
	// Contiene los nodos ya insertados en el árbol Personalizado
	Map existeNodoMapCP = new LinkedHashMap();
	// Contiene los nodos con las funciones que posee cada uno de ellos
	JTree treeOriginalCache = new JTree();
	Collection cuentasTreeColeccionCC = new ArrayList();
	Iterator itCuentasTreeColeccionCC;
	//boolean estaUsuarioSeleccionado = false;
	private List<UsuarioCuentaIf> usuarioCuentaColeccion = new ArrayList<UsuarioCuentaIf>();
	private Map cuentasMap = new HashMap();
	private Map cuentasUsuarioMap = new HashMap();
	private PlanCuentaIf planCuenta;
	private PlanCuentaIf planCuentaAnterior = null;
	private UsuarioIf usuario;
	private JDPopupFinderModel popupFinder;
	
	public UsuarioCuentaModel() {
		initListeners();
		clean();
		loadCombos();
		showSaveMode();
		new HotKeyComponent(this);
		getTreeCuentasContables().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		getTreeCuentasPermitidas().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
		customTreeRenderer.setOpenIcon(customOpenIcon);
		customTreeRenderer.setClosedIcon(customClosedIcon);
		customTreeRenderer.setLeafIcon(customLeafIcon);
	    getTreeCuentasContables().setCellRenderer(customTreeRenderer);
	    getTreeCuentasPermitidas().setCellRenderer(customTreeRenderer);
	}
	
	private void initListeners() {
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					EmpleadoIf empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					try {
						Iterator itusuarios  = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleado.getId()).iterator();
						
						while (itusuarios.hasNext()) {
							UsuarioIf usuarioIf = (UsuarioIf) itusuarios.next();
							if(usuarioIf.getEmpleadoId().compareTo(empleado.getId())==0) {
								getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
								usuario = usuarioIf;
								getTxtUsuario().setText(usuario.getUsuario());
								loadTree(usuario);
							}
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		//Al escoger un plan de cuenta mando a cargar el arbol con todas las cuentas referente sa ese plan
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
				if(planCuenta != null){
					cuentasMap = mapearCuentas(planCuenta.getId());
					if ((planCuentaAnterior != null && planCuentaAnterior.getId().compareTo(planCuenta.getId()) != 0) || planCuentaAnterior == null) {
						planCuentaAnterior = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
						if (!estaTreeCuentasContablesCompleto())
							cargarTreeCuentasContables();
						rootCP = new DefaultMutableTreeNode("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]");
						getTreeCuentasPermitidas().setModel(new DefaultTreeModel(rootCP));
					}
				}				
			}
		});
				
		//Manejo el evento del Boton Agregar al Menu Personalizado
		getBtnAsignar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (validateFields()) {
					setCursor(SpiritCursor.hourglassCursor);
					// Get the model of tree Menu Original
					DefaultTreeModel modelTreeCuentasContables = (DefaultTreeModel) getTreeCuentasContables().getModel();
					// Get the model of tree Menu Personalizado
					DefaultTreeModel modelTreeCuentasPermitidas = (DefaultTreeModel) getTreeCuentasPermitidas().getModel();
					// Get paths of all selected nodes
					TreePath[] pathsNodosSeleccionadoCC = getTreeCuentasContables().getSelectionPaths();
					// Mando a llamar a la funcion que me permite mover los nodos de un arbol a otro
					moverNodosEntreArboles(modelTreeCuentasContables, modelTreeCuentasPermitidas, pathsNodosSeleccionadoCC, existeNodoMapCC, existeNodoMapCP);
					// Expando el arbol de la derecha
					//expandAll(getTreeCuentasPermitidas(), new TreePath(modelTreeCuentasContables.getRoot()), true);
					expandAll(getTreeCuentasPermitidas(), new TreePath(modelTreeCuentasPermitidas.getRoot()), true);
					setCursor(SpiritCursor.normalCursor);
				}
			}
		});
		
		// Manejo el evento del Boton Quitar del Menu Personalizado
		getBtnQuitar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				// Get the model of tree Menu Original
				DefaultTreeModel modelTreeCuentasContables = (DefaultTreeModel) getTreeCuentasPermitidas().getModel();
				// Get the model of tree Menu Personalizado
				DefaultTreeModel modelTreeCuentasPermitidas = (DefaultTreeModel) getTreeCuentasContables().getModel();
				// Get paths of all selected nodes
				TreePath[] pathsNodosSeleccionadoCC = getTreeCuentasPermitidas().getSelectionPaths();
				// Mando a llamar a la funcion que me permite mover los nodos de
				// un arbol a otro
				moverNodosEntreArboles(modelTreeCuentasContables, modelTreeCuentasPermitidas, pathsNodosSeleccionadoCC, existeNodoMapCP, existeNodoMapCC);
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
	private void loadCombos() {
		SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa()));
		getCmbPlanCuenta().setModel(cmbModel);
		PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}
	
	private void loadTree(UsuarioIf usuario) {
		setCursor(SpiritCursor.hourglassCursor);
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		clean();
		showSaveMode();
		if (panel != null) {
			Collection cuentasUsuario = null;
			try {
				cuentasUsuario = SessionServiceLocator.getUsuarioCuentaSessionService().findUsuarioCuentaByUsuarioId(usuario.getId());
				cuentasUsuarioMap = mapearCuentasUsuario(cuentasUsuario);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			//Si el usuario tiene ya roles asignados, cargamos el modo update... caso contrario el modo save xq es primera vez que se le van asignar roles
			if(cuentasUsuario.size()>0){
				//Mando a construir el arbol de las cuentas que tiene permitidas este usuario
				getTreeCuentasPermitidas().setModel(null);
				existeNodoMapCP.clear();
				if (rootCP != null) {
					//Remuevo todos los hijos del nodo raiz del Arbol de Cuentas Permitidas
					rootCP.removeAllChildren();
					//Creo el nodo raiz del menu personalizado
					getTreeCuentasPermitidas().setModel(new DefaultTreeModel(rootCP));
				}
				generateTreeCuentasPermitidas(usuario.getId(), 0L);
				expandAll(getTreeCuentasPermitidas(), new TreePath(((DefaultTreeModel)getTreeCuentasPermitidas().getModel()).getRoot()), true);
				showUpdateMode();
			} else {
				clean();
				planCuentaAnterior = null;
				//estaUsuarioSeleccionado = true;
				showSaveMode();
			}
		}
		setCursor(SpiritCursor.normalCursor);
	}

	public void save() {
		if (validateFields()) {
			setCursor(SpiritCursor.hourglassCursor);
			try {
				DefaultMutableTreeNode nodoRaiz = (DefaultMutableTreeNode) getTreeCuentasPermitidas().getModel().getRoot();
				nodosToUsuarioCuentaCollection(nodoRaiz);
				SessionServiceLocator.getUsuarioCuentaSessionService().procesarUsuarioCuenta(usuario.getId(), usuarioCuentaColeccion);
				this.clean();
				this.showSaveMode();
				getTxtEmpleado().setText("");
				getTxtUsuario().setText("");
				SpiritAlert.createAlert("Los datos han sido guardados con éxito", SpiritAlert.INFORMATION);
			} catch (Exception e) {
				SpiritAlert.createAlert("Se ha producido un error al guardar los datos", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			setCursor(SpiritCursor.normalCursor);
		}
	}
	
	public void update() {
		if (validateFields()) {
			setCursor(SpiritCursor.hourglassCursor);
			try {
				DefaultMutableTreeNode nodoRaiz = (DefaultMutableTreeNode) getTreeCuentasPermitidas().getModel().getRoot();
				nodosToUsuarioCuentaCollection(nodoRaiz);;
				SessionServiceLocator.getUsuarioCuentaSessionService().actualizarUsuarioCuenta(usuario.getId(), usuarioCuentaColeccion);
				this.clean();
				this.showSaveMode();
				getTxtEmpleado().setText("");
				getTxtUsuario().setText("");
				SpiritAlert.createAlert("Los datos han sido actualizados con éxito", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al actualizar los datos", SpiritAlert.ERROR);
			}
			setCursor(SpiritCursor.normalCursor);
		}
	}
	
	private void nodosToUsuarioCuentaCollection(DefaultMutableTreeNode nodo) {
		if (nodo.getChildCount() >= 0) {
			for (Enumeration e = nodo.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nodoHijoMP = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijoMP = (nodoHijoMP.toString().split("\\["))[1];
				String codigoNodoHijoMP = splitNodoHijoMP.substring(0, (splitNodoHijoMP.length() - 1));
				// Obtengo el id del nodo hijo segun el codigo de este				
				Long idNodoHijoMP = findCuentaByCodigo(codigoNodoHijoMP).getId();
				UsuarioCuentaIf modelUsuarioCuenta = new UsuarioCuentaData();
				modelUsuarioCuenta.setCuentaId(idNodoHijoMP);
				if (!usuarioCuentaExiste(modelUsuarioCuenta))
					usuarioCuentaColeccion.add(modelUsuarioCuenta);
				// Mando a insertar los hijos del nodo hijo si es que tiene
				nodosToUsuarioCuentaCollection(nodoHijoMP);
			}
		}
	}
	
	private boolean usuarioCuentaExiste(UsuarioCuentaIf usuarioCuenta) {
		Iterator usuarioCuentaIterator = usuarioCuentaColeccion.iterator();
		while (usuarioCuentaIterator.hasNext()) {
			UsuarioCuentaIf usuarioCuentaIf = (UsuarioCuentaIf) usuarioCuentaIterator.next(); 
			if (usuarioCuentaIf.getCuentaId().compareTo(usuarioCuenta.getCuentaId()) == 0)
				return true;
		}
		
		return false;
	}
	
	private Map mapearCuentas(Long idPlanCuenta) {
		Map cuentasMap = new HashMap();
		try {
			Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(idPlanCuenta).iterator();
			while (cuentasIterator.hasNext()) {
				CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasMap;
	}
	
	private Map mapearCuentasUsuario(Collection cuentasUsuario) {
		Map cuentasUsuarioMap = new HashMap();
		Iterator it = cuentasUsuario.iterator();
		while (it.hasNext()) {
			UsuarioCuentaIf cuentaUsuario = (UsuarioCuentaIf) it.next();
			cuentasUsuarioMap.put(cuentaUsuario.getCuentaId(), new Boolean(true));
		}
		
		return cuentasUsuarioMap;
	}
	
	public void delete() {
		// TODO Auto-generated method stub
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void find() {
		/*try {
			Map mapa = buildQuery();
			int tamanoLista = getUsuarioSessionService().getUsuarioListSize(mapa);
			if (tamanoLista > 0) {
				UsuarioCuentaCriteria usuarioCuentaCriteria = new UsuarioCuentaCriteria();
				usuarioCuentaCriteria.setResultListSize(tamanoLista);
				usuarioCuentaCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), usuarioCuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado() != null )
					planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
					if(planCuenta != null){
						getSelectedObject();
					}
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}*/
	}

	private void  saveNodosCuentaMayor(Long idPlanCuenta,Long idUsuario){
		try {
		    // Construyo el arbol con los valores leidos de la base
			Map parameterMap = new HashMap();
			parameterMap.put("plancuentaId", idPlanCuenta);
			parameterMap.put("nivel", 1);
			Collection cuentasMayorColeccionCP = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap);
			Iterator itCuentasMayorColeccionCP = cuentasMayorColeccionCP.iterator();
			// Creo instancia de cada una de las cuentas
			while (itCuentasMayorColeccionCP.hasNext()) {
				CuentaIf cuentaMayorIfCP = (CuentaIf) itCuentasMayorColeccionCP.next();
				//Veo si la cuenta esta en el mapa de Cuentas Permitidas
				if(existeNodoMapCP.get(cuentaMayorIfCP.getCodigo())!=null){
					//Obtengo el id del nodo hijo 
					Long idNodoCuentaMayorCP = cuentaMayorIfCP.getId();
					//Creo la instancia de UsuarioCuenta
					UsuarioCuentaData dataUC = new UsuarioCuentaData();
					//Seteto los valores de la instancia de UsuarioCuenta para insertarlo en la base
					dataUC.setUsuarioId(idUsuario);
					dataUC.setCuentaId(idNodoCuentaMayorCP);
					//Inserto el nodo hijo en la base
					SessionServiceLocator.getUsuarioCuentaSessionService().addUsuarioCuenta(dataUC);
					//Mando a guardar los nodos hijos si es que este tiene 
					saveNodosHijos(idNodoCuentaMayorCP,idUsuario);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}     
	}
	
	private void  saveNodosHijos(Long idCuentaPadre,Long idUsuario){
		try {
			//Obtengo el objeto de la CuentaPadre
			CuentaIf cuentaPadreIf = (CuentaIf) cuentasMap.get(idCuentaPadre);
			DefaultMutableTreeNode nodoPadreCP = (DefaultMutableTreeNode) existeNodoMapCP.get(cuentaPadreIf.getCodigo());
			//Obtengo todas las cuentas de la base segun el id de la cuenta padre
			Collection cuentasHijas = findCuentasHijasByPadreId(idCuentaPadre);
			Iterator itCuentasHijas = cuentasHijas.iterator();
			// Creo instancia de cada uno de los registros de la tabla Cuenta leido de la base
			while (itCuentasHijas.hasNext()) {
				//Obtengo la cuenta de la base segun la cuentaId leido de la tabla 
				CuentaIf cuentaHijaIf = (CuentaIf) itCuentasHijas.next();
				//Veo si la cuenta esta en el mapa de Cuentas Permitidas
				if(existeNodoMapCP.get(cuentaHijaIf.getCodigo())!=null){
					//Obtengo el id del nodo hijo 
					Long idNodoHijoCP = cuentaHijaIf.getId();
					//Creo la instancia de UsuarioCuenta
					UsuarioCuentaData dataUC = new UsuarioCuentaData();
					//Seteo los valores de la instancia de UsuarioCuenta para insertarlo en la base
					dataUC.setUsuarioId(idUsuario);
					dataUC.setCuentaId(idNodoHijoCP);
					//Inserto el nodo hijo en la base
					SessionServiceLocator.getUsuarioCuentaSessionService().addUsuarioCuenta(dataUC);
					//Mano a guardar los nodos hijos si es que este tiene 
					saveNodosHijos(idNodoHijoCP,idUsuario);
				}
			}  	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}     
	}
	
	public boolean validateFields() {
		if (usuario == null) {
			SpiritAlert.createAlert("Debe seleccionar el usuario !!", SpiritAlert.WARNING);
			getBtnBuscarEmpleado().grabFocus();
			return false;			
		}
		
		if (getCmbPlanCuenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Plan de Cuentas !!", SpiritAlert.WARNING);
			getCmbPlanCuenta().grabFocus();
			return false;			
		}

		return true;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		if (usuario != null) {
			aMap.put("usuario", usuario.getUsuario());
		} else 
			aMap.put("usuario", "%");
		return aMap;
	}
		
	//Funcion que elimina los registros de la tabla usuario cuenta por idUsuario
	private void  deleteCuentasUsuario(Long idUsuario){
		try {
			//Obtengo todos los registros de Usuario_Cuenta segun el idUsuario recibido
			Collection cuentasUsuario = SessionServiceLocator.getUsuarioCuentaSessionService().findUsuarioCuentaByUsuarioId(idUsuario);
			Iterator itCuentasUsuario = cuentasUsuario.iterator();
			// Obtengo el id de cada uno  de los registros de la tabla usuariocuenta encontrados
			while (itCuentasUsuario.hasNext()) {
				//Obtengo el idUsuarioCuenta
				Long idUsuarioCuenta = ((UsuarioCuentaIf) itCuentasUsuario.next()).getId();
				//Mando a eliminar el registro por el id
				SessionServiceLocator.getUsuarioCuentaSessionService().deleteUsuarioCuenta(idUsuarioCuenta);
			}	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		if (usuario == null && this.getCmbPlanCuenta().getSelectedItem() == null) 
			return true;
		else
			return false;
	}

	public void showSaveMode() {
		loadCombos();
		setSaveMode();
		getCmbPlanCuenta().setEnabled(true);
		getBtnBuscarEmpleado().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getCmbPlanCuenta().setEnabled(true);
		getCmbPlanCuenta().grabFocus();
	}
	
	public void showFindMode() {
		showSaveMode();
		getTxtEmpleado().setText("");
		getTxtUsuario().setText("");
		usuario = null;
	}

	public void clean() {
		//Limpio los mapas despues de guardar los registros en la base
		existeNodoMapCC.clear();
		existeNodoMapCP.clear();
		existePlanCuentasMap.clear();
		usuarioCuentaColeccion.clear();
		planCuenta = null;
		planCuentaAnterior = null;
		//Pongo los arboles en blanco
		getTreeCuentasContables().setModel(null);
		if (rootCP != null) {
			//Remuevo todos los hijos del nodo raiz del Arbol de Cuentas Permitidas
			rootCP.removeAllChildren();
			//Creo el nodo raiz del menu personalizado
			getTreeCuentasPermitidas().setModel(new DefaultTreeModel(rootCP));
		}
	}

	public void cargarTreeCuentasContables() {
		try {
			if(planCuenta != null){
				DefaultTreeModel treeModel = generateTreeModel();
				getTreeCuentasContables().setModel(treeModel);
			}				
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean estaTreeCuentasContablesCompleto() {
		if (itCuentasTreeColeccion != null) {
			while (itCuentasTreeColeccion.hasNext()) {
				CuentaIf cuenta = (CuentaIf) itCuentasTreeColeccion.next();
				if (existeNodoMapCC.get(cuenta.getCodigo()) == null)
					return false;
			}
		} else
			return false;
		
		return true;
	}
	
	public class MyDefaultTreeModel extends DefaultTreeModel implements Cloneable {
		public MyDefaultTreeModel(TreeNode root) {
			super(root);
		}
		
		public Object clone() {
	        MyDefaultTreeModel obj=null;
	        try {
	        	
	            obj = (MyDefaultTreeModel) super.clone();
	        } catch(CloneNotSupportedException ex) {
	            System.out.println("No se puede duplicar");
	        }
	        
	        return obj;
	    }
	}
		
	private DefaultTreeModel generateTreeModel() throws GenericBusinessException {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]");
		cuentasTreeColeccionCC.clear();
		//generarModulosTreeMenuColeccionMO(this, getMenuSessionService().getMenuListSortedByCodigo());
		cuentasTreeColeccionCC = findCuentaFromMapByPlanCuentaId(planCuenta.getId());
		itCuentasTreeColeccionCC =  cuentasTreeColeccionCC.iterator();
		itCuentasTreeColeccion = cuentasTreeColeccionCC.iterator();
		Iterator itCuentasTreeColeccionCC = cuentasTreeColeccionCC.iterator();
		
		while (itCuentasTreeColeccionCC.hasNext()) {
			CuentaIf cuentaIfCC = (CuentaIf) itCuentasTreeColeccionCC.next();
			if (cuentaIfCC.getNivel() == 1) {
				DefaultMutableTreeNode cuentaNodoCC = new DefaultMutableTreeNode(cuentaIfCC.getNombre() + " ["+ cuentaIfCC.getCodigo() + "]");
				existeNodoMapCC.put(cuentaIfCC.getCodigo(),cuentaNodoCC);
				root.add(cuentaNodoCC);
				insertarNodosHijoSeleccionado(cuentaIfCC.getId(),cuentaNodoCC,existeNodoMapCC,cuentasTreeColeccionCC);
			}
		}
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		return defaultTreeModel;
	}
		
	private void insertarNodosHijoSeleccionado(Long cuentaPadreId, DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap, Collection cuentasColeccion) {
		Iterator itCuentasTreeColeccionCC = cuentasColeccion.iterator();

		while (itCuentasTreeColeccionCC.hasNext()) {
			CuentaIf nodoCuentaIf = (CuentaIf) itCuentasTreeColeccionCC.next();
			// Añado el nodo hijo a su padre
			if (nodoCuentaIf.getPadreId() != null && cuentaPadreId != null) {
				if (nodoCuentaIf.getPadreId().compareTo(cuentaPadreId) == 0) {
					DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodoCuentaIf.getNombre() + " [" + nodoCuentaIf.getCodigo()+ "]");
					String splitNodoHijo = (nodoHijo.toString().split("\\["))[1];
					String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
					// Guardo en el mapa de nodos existentes en el menú el nodo hijo actual
					existeNodoMap.put(codigoNodoHijo, nodoHijo);
					// Añado al nodo seleccionado en el árbol de la derecha el hijo encontrado
					nodoSeleccionado.add(nodoHijo);
					insertarNodosHijoSeleccionado(nodoCuentaIf.getId(), nodoHijo, existeNodoMap, cuentasColeccion);
				}
			}
		}
	}
	
	private void insertarNodosHijoSeleccionado (DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap) {
		Long idNodoSeleccionado;
		if (nodoSeleccionado.isRoot()) {
			idNodoSeleccionado = 0L;
		}
		// Si el nodo no es root, extraigo el codigo de este
		else {
			String splitNodoSeleccionado = (nodoSeleccionado.toString().split("\\["))[1];
			String codigoNodoSeleccionado = splitNodoSeleccionado.substring(0, (splitNodoSeleccionado.length() - 1));
			// Obtengo el id del nodo seleccionado segun el codigo de este
			idNodoSeleccionado = findCuentaByCodigo(codigoNodoSeleccionado).getId();
		}
		// Creo la coleccion con las cuentas existentes
		Collection nodosHijoNodoSeleccionado = null;
		if (idNodoSeleccionado.compareTo(0L) == 0)
			nodosHijoNodoSeleccionado = findCuentaByPlanCuentaId(planCuenta.getId());
		else
			nodosHijoNodoSeleccionado = findCuentaByPadreId(idNodoSeleccionado);
		Iterator itNodosHijoNodoSeleccionado = nodosHijoNodoSeleccionado.iterator();
		// Creo instancia de cada uno de las cuentas
		while (itNodosHijoNodoSeleccionado.hasNext()) {
			CuentaIf nodoCuentaIf = (CuentaIf) itNodosHijoNodoSeleccionado.next();
			// Añado el nodo hijo a su padre
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodoCuentaIf.getNombre() + " [" + nodoCuentaIf.getCodigo()+ "]");
			String splitNodoHijo = (nodoHijo.toString().split("\\["))[1];
			String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
			// Guardo en el mapa de nodos existentes en el menú el nodo hijo actual
			existeNodoMap.put(codigoNodoHijo, nodoHijo);
			// Añado al nodo seleccionado en el árbol de la derecha el hijo encontrado
			nodoSeleccionado.add(nodoHijo);
			// Mando a buscar si el nodo hijo tiene hijos
			insertarNodosHijoSeleccionado(nodoHijo, existeNodoMap);
		}
	}
	
	private CuentaIf findCuentaByCodigo(String codigoNodoSeleccionado) {
		Iterator it = cuentasTreeColeccionCC.iterator();
		
		while (it.hasNext()) {
			CuentaIf cuenta = (CuentaIf) it.next();
			if (cuenta.getCodigo().equals(codigoNodoSeleccionado))
				return cuenta;
		}
		
		return null;
	}
	
	private Collection findCuentaByPadreId(Long idNodoSeleccionado) {
		Collection cuentaColeccionByPadreId = new ArrayList();
		Iterator it = cuentasTreeColeccionCC.iterator();
		
		while (it.hasNext()) {
			CuentaIf cuenta = (CuentaIf) it.next();
			if (cuenta != null && cuenta.getPadreId() != null && idNodoSeleccionado != null)
				if (cuenta.getPadreId().compareTo(idNodoSeleccionado) == 0)
					cuentaColeccionByPadreId.add(cuenta);
		}
		
		return cuentaColeccionByPadreId;
	}
	
	private Collection findCuentaByPadreIdByUsuarioIdAndNivel(Long idUsuario, Long idPadre, int nivel) {
		Collection cuentasColeccion = new ArrayList();
		Iterator it = cuentasMap.keySet().iterator();
		
		while (it.hasNext()) {
			Long idCuenta = (Long) it.next();
			CuentaIf cuenta = (CuentaIf) cuentasMap.get(idCuenta);
			if (cuenta != null && (nivel == 1 || cuenta.getPadreId() != null) && idPadre != null) {
				if ((cuenta.getNivel() == 1 || cuenta.getPadreId().compareTo(idPadre) == 0) && cuenta.getNivel() == nivel && cuentasUsuarioMap.get(cuenta.getId()) != null)
					cuentasColeccion.add(cuenta);
			}
		}
		
		return cuentasColeccion;
	}
	
	private Collection findCuentaByPadreIdAndByUsuarioId(Long idUsuario, Long idPadre) {
		Collection cuentasColeccion = new ArrayList();
		Iterator it = cuentasMap.keySet().iterator();
		
		while (it.hasNext()) {
			Long idCuenta = (Long) it.next();
			CuentaIf cuenta = (CuentaIf) cuentasMap.get(idCuenta);
			if (cuenta != null && cuenta.getPadreId() != null && idPadre != null)
				if (cuenta.getPadreId().compareTo(idPadre) == 0 && cuentasUsuarioMap.get(cuenta.getId()) != null)
					cuentasColeccion.add(cuenta);
		}
		
		return cuentasColeccion;
	}
	
	private Collection findCuentasHijasByPadreId(Long idPadre) {
		Collection cuentaColeccionByPadreId = new ArrayList();
		Iterator it = cuentasMap.keySet().iterator();
		
		while (it.hasNext()) {
			Long idCuenta = (Long) it.next();
			CuentaIf cuenta = (CuentaIf) cuentasMap.get(idCuenta);
			if (cuenta != null && cuenta.getPadreId() != null && idPadre != null)
				if (cuenta.getPadreId().compareTo(idPadre) == 0)
					cuentaColeccionByPadreId.add(cuenta);
		}
		
		return cuentaColeccionByPadreId;
	}
	
	private Collection findCuentaByPlanCuentaId(Long idPlanCuenta) {
		Collection cuentaColeccionByPlanCuentaId = new ArrayList();
		Iterator it = cuentasTreeColeccionCC.iterator();
		
		while (it.hasNext()) {
			CuentaIf cuenta = (CuentaIf) it.next();
			if (cuenta != null && cuenta.getPlancuentaId() != null && idPlanCuenta != null)
				if (cuenta.getPlancuentaId().compareTo(idPlanCuenta) == 0)
					cuentaColeccionByPlanCuentaId.add(cuenta);
		}
		
		return cuentaColeccionByPlanCuentaId;
	}
	
	private Collection findCuentaFromMapByPlanCuentaId(Long idPlanCuenta) {
		Collection cuentaColeccionByPlanCuentaId = new ArrayList();
		Iterator it = cuentasMap.keySet().iterator();
		
		while (it.hasNext()) {
			Long idCuenta = (Long) it.next();
			CuentaIf cuenta = (CuentaIf) cuentasMap.get(idCuenta);
			if (cuenta != null && cuenta.getPlancuentaId() != null && idPlanCuenta != null)
				if (cuenta.getPlancuentaId().compareTo(idPlanCuenta) == 0)
					cuentaColeccionByPlanCuentaId.add(cuenta);
		}
		
		return cuentaColeccionByPlanCuentaId;
	}
	
	private void cargarArbolCuentasPermitidas(final Long idPlanCuenta) {
		try {
			//Get the model of tree Cuentas Permitidas
			DefaultTreeModel modelTreeCuentasPermitidas = (DefaultTreeModel)getTreeCuentasPermitidas().getModel();
			//Get the model of tree Cuentas Contables
			DefaultTreeModel modelTreeCuentasContables = (DefaultTreeModel)getTreeCuentasContables().getModel();
			DefaultMutableTreeNode nodoRaizCP = (DefaultMutableTreeNode) modelTreeCuentasPermitidas.getRoot();			
			// Construyo el arbol con los valores leidos de la base
			Map parameterMap = new HashMap();
			parameterMap.put("plancuentaId", idPlanCuenta);
			parameterMap.put("nivel", 1);
			Collection cuentasMayorColeccionCP = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap);
			Iterator itCuentasMayorColeccionCP = cuentasMayorColeccionCP.iterator();
			// Creo instancia de cada una de las cuentas
			while (itCuentasMayorColeccionCP.hasNext()) {
				CuentaIf cuentaMayorIfCP = (CuentaIf) itCuentasMayorColeccionCP.next();
				//Veo si la cuenta esta en el mapa de Cuentas Permitidas
				if(existeNodoMapCP.get(cuentaMayorIfCP.getCodigo())!=null){
					//Creo el nodo cuenta mayor en el arbol de Cuentas Permitidas 
					DefaultMutableTreeNode nodoCuentaMayorCP = (DefaultMutableTreeNode) existeNodoMapCP.get(cuentaMayorIfCP.getCodigo());
					//Inserto el nodo cuenta mayor en el nodo raiz
					modelTreeCuentasPermitidas.insertNodeInto(nodoCuentaMayorCP,nodoRaizCP,0);
					//Veo si el nodo hijo tiene hijos
					cargarNodosHijosCuentasPermitidas(cuentaMayorIfCP.getId());
					//Retiro del Arbol de Cuentas Contables y del Mapa el nodo hijo que fue leido de la base, SIEMPRE Y CUANDO EL PADRE DE ESTE NO TENGA MAS HIJOS
					DefaultMutableTreeNode nodoHijoCC = (DefaultMutableTreeNode) existeNodoMapCC.get(cuentaMayorIfCP.getCodigo());
					
					if(nodoHijoCC.getChildCount()==0){
						modelTreeCuentasContables.removeNodeFromParent((DefaultMutableTreeNode) existeNodoMapCC.get(cuentaMayorIfCP.getCodigo()));
						existeNodoMapCC.remove(cuentaMayorIfCP.getCodigo());
					}
				}
				
			}
			//Expando el arbol creado
		    expandAll(getTreeCuentasContables(), new TreePath(((DefaultTreeModel)getTreeCuentasContables().getModel()).getRoot()), true);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarNodosHijosCuentasPermitidas(Long idCuentaPadre) {
		//Get the model of tree Cuentas Permitidas
		DefaultTreeModel modelTreeCuentasPermitidas = (DefaultTreeModel)getTreeCuentasPermitidas().getModel();
		//Get the model of tree Cuentas Contables
		DefaultTreeModel modelTreeCuentasContables = (DefaultTreeModel)getTreeCuentasContables().getModel();
		DefaultMutableTreeNode nodoPadreCP = new DefaultMutableTreeNode();
		//Obtengo el objeto de la CuentaPadre
		CuentaIf cuentaPadreIf = (CuentaIf) cuentasMap.get(idCuentaPadre);
		nodoPadreCP = (DefaultMutableTreeNode) existeNodoMapCP.get(cuentaPadreIf.getCodigo());
		//Obtengo todas las cuentas de la base segun el id de la cuenta padre
		Collection cuentasHijas = findCuentasHijasByPadreId(idCuentaPadre);
		Iterator itCuentasHijas = cuentasHijas.iterator();
		// Creo instancia de cada uno de los registros de la tabla Cuenta leido de la base
		while (itCuentasHijas.hasNext()) {
			//Obtengo la cuenta de la base segun la cuentaId leido de la tabla 
			CuentaIf cuentaHijaIf = (CuentaIf) itCuentasHijas.next();
			//Veo si la cuenta esta en el mapa de Cuentas Permitidas
			if(existeNodoMapCP.get(cuentaHijaIf.getCodigo())!=null){
				//Creo el nodo hijo y extraigo su codigo 
				DefaultMutableTreeNode nodoHijoCP = (DefaultMutableTreeNode) existeNodoMapCP.get(cuentaHijaIf.getCodigo());
				//Inserto el nodo hijo debajo del nodo padre
				modelTreeCuentasPermitidas.insertNodeInto(nodoHijoCP,nodoPadreCP,0);
				//Veo si el nodo hijo tiene hijos
				cargarNodosHijosCuentasPermitidas(cuentaHijaIf.getId());
				//Retiro del Arbol de Cuentas Contables y del Mapa el nodo hijo que fue leido de la base, SIEMPRE Y CUANDO EL PADRE DE ESTE NO TENGA MAS HIJOS
				DefaultMutableTreeNode nodoHijoCC = (DefaultMutableTreeNode) existeNodoMapCC.get(cuentaHijaIf.getCodigo());
				
				if(nodoHijoCC.getChildCount()==0){
					modelTreeCuentasContables.removeNodeFromParent((DefaultMutableTreeNode) existeNodoMapCC.get(cuentaHijaIf.getCodigo()));
					existeNodoMapCC.remove(cuentaHijaIf.getCodigo());
				}
			}
		}
	}
	
	private void getSelectedObject(){
		try {
			usuario = (UsuarioIf) popupFinder.getElementoSeleccionado();
			Map parameterMap = new HashMap();
			parameterMap.put("usuario", usuario.getUsuario());
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			Long idUsuarioBean = ((UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByQuery(parameterMap).iterator().next()).getId();				
			//Mando a ver si existen ya asignadas cuentas a este usuario
			Collection cuentasUsuario = SessionServiceLocator.getUsuarioCuentaSessionService().findUsuarioCuentaByUsuarioId(usuario.getId());
			//Si el usuario tiene ya roles asignados, cargamos el modo update... caso contrario el modo save xq es primera vez que se le van asignar roles
			if(cuentasUsuario.size()>0){
				//Mando a construir el arbol de las cuentas que tiene permitidas este usuario
				generateTreeCuentasPermitidas(usuario.getId(), 0L);
				showUpdateMode();
			} else
				showSaveMode();
			
			if (getCmbPlanCuenta().getItemCount() > 0)
				getCmbPlanCuenta().setSelectedIndex(0);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void moverNodosEntreArboles(DefaultTreeModel modelTreeCuentasContables, DefaultTreeModel modelTreeCuentasPermitidas, TreePath[] pathsNodosSeleccionadoCC, Map existeNodoCC, Map existeNodoCP) {
		// Si no ha sido seleccionado ningún nodo, se muestra un mensaje de aviso
		if (pathsNodosSeleccionadoCC != null) {
			//SpiritAlert.createAlert("Por favor seleccione el/los elemento(s) a mover!", SpiritAlert.INFORMATION);
			//System.out.println("");
		//else {
			// Extraigo el nodo raíz del menú destino
			DefaultMutableTreeNode rootCP = (DefaultMutableTreeNode) modelTreeCuentasPermitidas.getRoot();
			// Navego por cada una de las rutas de los nodos seleccionados
			for (int indiceNodosSeleccionadosCC = 0; indiceNodosSeleccionadosCC < pathsNodosSeleccionadoCC.length; indiceNodosSeleccionadosCC++) {
				DefaultMutableTreeNode nodoActualCP = new DefaultMutableTreeNode();
				DefaultMutableTreeNode nodoSeleccionadoCC = (DefaultMutableTreeNode) pathsNodosSeleccionadoCC[indiceNodosSeleccionadosCC].getLastPathComponent();
				DefaultMutableTreeNode nodoPadreSeleccionadoCC = new DefaultMutableTreeNode();
				String codigoNodoSeleccionadoCC = "";
				if (!nodoSeleccionadoCC.isRoot()) {
					String splitNodoSeleccionadoCC = (nodoSeleccionadoCC.toString().split("\\["))[1];
					codigoNodoSeleccionadoCC = splitNodoSeleccionadoCC.substring(0, (splitNodoSeleccionadoCC.length() - 1));
				}
				// Variables que guardan el codigo del nodoActual
				String codigoNodoActualCP = "";

				if (nodoSeleccionadoCC.isRoot()) {
					// Limpio el mapa y la raiz del menu origen
					existeNodoCC.clear();
					modelTreeCuentasContables.setRoot(new DefaultMutableTreeNode("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]"));
					// Limpio el mapa y la raiz del menu destino
					existeNodoCP.clear();
					modelTreeCuentasPermitidas.setRoot(new DefaultMutableTreeNode("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]"));
					// Inserto el nodo raiz en el mapa destino
					existeNodoCP.put("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]",(DefaultMutableTreeNode) modelTreeCuentasPermitidas.getRoot());
					// Inserto todos los hijos del nodo raiz en el menu destino
					insertarNodosHijoSeleccionado((DefaultMutableTreeNode) modelTreeCuentasPermitidas.getRoot(), existeNodoCP);
				} else {
					// Navego por cada uno de los nodos de las rutas de los nodos seleccionados
					for (int j = 1; j < pathsNodosSeleccionadoCC[indiceNodosSeleccionadosCC].getPathCount(); j++) {
						nodoActualCP = new DefaultMutableTreeNode(pathsNodosSeleccionadoCC[indiceNodosSeleccionadosCC].getPath()[j]);
						String splitNodoActualCP = (nodoActualCP.toString().split("\\["))[1];
						codigoNodoActualCP = splitNodoActualCP.substring(0, (splitNodoActualCP.length() - 1));
						// Si el nodo no existe, lo agrego al mapa, lo inserto en el arbol y lo seteo como posible nodo padre
						if (existeNodoCP.get(codigoNodoActualCP) == null) {
							existeNodoCP.put(codigoNodoActualCP, nodoActualCP);
							// Si el nodo actual no es hijo del nodo raiz, lo añado dentro del nodo que le corresponde
							if (j > 1)
								modelTreeCuentasPermitidas.insertNodeInto(nodoActualCP, nodoPadreSeleccionadoCC,0);
							else {
								modelTreeCuentasPermitidas.insertNodeInto(nodoActualCP, rootCP, 0);
								existeNodoCP.put("PLAN DE CUENTA: [" + planCuenta.getNombre() + "]", rootCP);
							}
							// Si no existe en elmapa lo seteo cmo un posible nodo padre del nodo que ha sido seleccionado
							nodoPadreSeleccionadoCC = nodoActualCP;
						}
						// Si el nodo existe igual lo busco en el map y el encontrado lo seteo como posible padre
						else
							nodoPadreSeleccionadoCC = (DefaultMutableTreeNode) existeNodoCP.get(codigoNodoActualCP);

						// Si el nodo es el nodo seleccionado,lo extraigo del mapa, remuevo los hijos que tenia insertados, y mando
						// a insertar TODOS los que tiene en la base
						if (codigoNodoActualCP.equals(codigoNodoSeleccionadoCC) && existeNodoCC.get(codigoNodoActualCP) != null) {
							nodoSeleccionadoCC = (DefaultMutableTreeNode) existeNodoCP.get(codigoNodoSeleccionadoCC);
							nodoSeleccionadoCC.removeAllChildren();
							insertarNodosHijoSeleccionado(nodoSeleccionadoCC, existeNodoCP);
						}
					}
					// Veo si el nodo no ha sido ya removido del arbol de del menu de origen
					if (existeNodoCC.get(codigoNodoActualCP) != null) {
						// Mando a quitar los nodos padres cuyos hijos hayan sido quitados y el se encuentre solitario
						quitarNodoPadreSinHijos(modelTreeCuentasContables,nodoSeleccionadoCC, existeNodoCC);
						// Retiro del menu Origen y del Mapa el nodo(s) que fue seleccionado
						modelTreeCuentasContables.removeNodeFromParent((DefaultMutableTreeNode) existeNodoCC.get(codigoNodoSeleccionadoCC));
						removerNodosHijoSeleccionado((DefaultMutableTreeNode) existeNodoCC.get(codigoNodoSeleccionadoCC),existeNodoCC);
						existeNodoCC.remove(codigoNodoSeleccionadoCC);
					}
				}
			}
			// Mando a cargar los nodos añadidos del Menu Destino
			modelTreeCuentasPermitidas.reload();
		}
	}
	
	// Metodo que chequea si un nodo padre se ha quedado sin hijos para quitarlo
	// del mapa y del arbol
	private void quitarNodoPadreSinHijos(DefaultTreeModel modelTreeCuentasContables, DefaultMutableTreeNode nodoSeleccionadoCC, Map existeNodoCC) {
		// Si el nodo padre del seleccionado es diferente del de la raiz
		if (!((DefaultMutableTreeNode) nodoSeleccionadoCC.getParent()).isRoot()) {
			/*String splitNodoSeleccionadoMO = (nodoSeleccionadoMO.toString().split("\\("))[1];
			String codigoNodoPadreSeleccionadoMO = splitNodoSeleccionadoMO.substring(0, (splitNodoSeleccionadoMO.length() - 1));
			DefaultMutableTreeNode nodoPadreSeleccionadoMO = (DefaultMutableTreeNode) existeNodoMO.get(codigoNodoPadreSeleccionadoMO);*/
			String splitNodoPadreSeleccionadoCC = (nodoSeleccionadoCC.getParent().toString().split("\\["))[1];
			String codigoNodoPadreSeleccionadoCC = splitNodoPadreSeleccionadoCC.substring(0, (splitNodoPadreSeleccionadoCC.length() - 1));
			DefaultMutableTreeNode nodoPadreSeleccionadoCC = (DefaultMutableTreeNode) existeNodoCC.get(codigoNodoPadreSeleccionadoCC);
			// Veo cuando todos los nodos hijos han sido sacado y solo queda el
			// nodo padre sin hijos, lo saco del mapa y del arbol para que el padre no quede como una simple hoja
			if (nodoPadreSeleccionadoCC.getChildCount() == 1) {
				// LLamo a la funcion nuevamente para ver si el nodo padre es hijo único, para remover al padre de él
				quitarNodoPadreSinHijos(modelTreeCuentasContables, nodoPadreSeleccionadoCC, existeNodoCC);
				// Remuevo el nodo padre sin hijos del arbol
				modelTreeCuentasContables.removeNodeFromParent(nodoPadreSeleccionadoCC);
				// Retiro del mapa de nodos existentes en el menu el padre del nodo seleccionado
				existeNodoCC.remove(codigoNodoPadreSeleccionadoCC);
			}
		}
	}

	// Método recursivo que recibe un nodo busca los hijos de éste en la base
	private void removerNodosHijoSeleccionado(DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap) {
		if (nodoSeleccionado.getChildCount() >= 0) {
			for (Enumeration e = nodoSeleccionado.children(); e.hasMoreElements();) {
				// Veo el nodo Hijo del nodo recibido
				DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijo = (nodoHijo.toString().split("\\["))[1];
				String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
				// LLamo a la funcion nuevamente para ver si el nodo tiene más hijos
				removerNodosHijoSeleccionado(nodoHijo, existeNodoMap);
				// Retiro del mapa de nodos existentes en el menu el nodo hijo actual
				existeNodoMap.remove(codigoNodoHijo);
			}
		}
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}
		// Expansion or collapse must be done bottom-up
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}
	
	private void generateTreeCuentasPermitidas(Long idUsuario, Long idPadre) {
		// Get the model of tree Menu Personalizado
		DefaultTreeModel modelTreeCuentasPermitidas = (DefaultTreeModel) getTreeCuentasPermitidas().getModel();
		// Get the model of tree Menu Origen
		DefaultTreeModel modelTreeCuentasContables = (DefaultTreeModel) getTreeCuentasContables().getModel();
		DefaultMutableTreeNode nodoPadreCP = new DefaultMutableTreeNode();
		DefaultMutableTreeNode nodoPadreCC = new DefaultMutableTreeNode();

		CuentaIf cuentaPadreIf = null;
		Collection cuentasUsuario = null;
		if (idPadre.compareTo(0L) == 0) {
			nodoPadreCP = (DefaultMutableTreeNode) modelTreeCuentasPermitidas.getRoot();
			cuentasUsuario = findCuentaByPadreIdByUsuarioIdAndNivel(idUsuario, idPadre, 1);
		} else { 
			cuentaPadreIf = (CuentaIf) cuentasMap.get(idPadre);
			nodoPadreCP = (DefaultMutableTreeNode) existeNodoMapCP.get(cuentaPadreIf.getCodigo());
			cuentasUsuario = findCuentaByPadreIdAndByUsuarioId(idUsuario, idPadre);
		}
		 
		Iterator itCuentasUsuario = cuentasUsuario.iterator();
		// Creo instancia de cada uno de los registros RolOpcion leído de la base
		while (itCuentasUsuario.hasNext()) {
			CuentaIf cuentaHijaIf = (CuentaIf) itCuentasUsuario.next();
			if (cuentaHijaIf.getPlancuentaId().compareTo(planCuenta.getId()) == 0) {
				// Creo el nodo hijo y extraigo su codigo
				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(cuentaHijaIf.getNombre() + " [" + cuentaHijaIf.getCodigo() + "]");
				String splitNodoHijo = (nodoHijo.toString().split("\\["))[1];
				String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
				// Inserto el nodohijo leido de la base en el map del menú personalizado
				existeNodoMapCP.put(codigoNodoHijo, nodoHijo);
				// Inserto el nodo hijo debajo del nodo padre
				modelTreeCuentasPermitidas.insertNodeInto(nodoHijo, nodoPadreCP, 0);		
				// Veo si el nodo hijo tiene hijos
				generateTreeCuentasPermitidas(idUsuario, cuentaHijaIf.getId());
				// Retiro del menú Origen y del Mapa el nodo hijo que fue leído de la base, SIEMPRE Y CUANDO EL PADRE DE ESTE NO TENGA MAS HIJOS
				DefaultMutableTreeNode nodoHijoCC = (DefaultMutableTreeNode) existeNodoMapCC.get(codigoNodoHijo);
				if (nodoHijoCC != null) {
					if (nodoHijoCC.getChildCount() == 0) {
						DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) existeNodoMapCC.get(codigoNodoHijo);
						if (dmt != null)
							modelTreeCuentasContables.removeNodeFromParent(dmt);
						existeNodoMapCC.remove(codigoNodoHijo);
					}
				}
			}
		}
	}
}
