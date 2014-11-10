package com.spirit.general.gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbpm.JbpmContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.action.CommandBar;
import com.jidesoft.action.DefaultDockableBarHolder;
import com.jidesoft.action.DockableBarDockableHolderPanel;
import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.DockingManager;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.status.LabelStatusBarItem;
import com.jidesoft.status.MemoryStatusBarItem;
import com.jidesoft.status.StatusBar;
import com.jidesoft.status.TimeStatusBarItem;
import com.jidesoft.swing.ContentContainer;
import com.jidesoft.swing.JideScrollPane;
import com.jidesoft.swing.JideTabbedPane;
import com.jidesoft.utils.Lm;
import com.l2fprod.common.swing.JOutlookBar;
import com.spirit.bpm.SpiritBpm;
import com.spirit.client.ActivePanel;
import com.spirit.client.ChangeModeEvent;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.ChangeModeListener;
import com.spirit.client.CriteriaTarea;
import com.spirit.client.IconButton;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.bpm.criteria.CompraPrincipalCriteriaTarea;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.NoticiasIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.handler.JDCloseSystemModel;
import com.spirit.general.gui.model.NoticiaModel;

public class MainFrame extends DefaultDockableBarHolder implements ChangeModeListener {
	private static final long serialVersionUID = -4672052877074637478L;	
	private static CommandBar _favoritosBar;
	private static String PROFILE_NAME = "";
	private static ParametrosIniciales _parametrosIniciales;
	public static Timer _timer;
	private static Map panels;
	static JideTabbedPane _tabbedPane;
	static JPanel _panelesEstatico = new JPanel();
	static CellConstraints cc = new CellConstraints();
	static DockableBarDockableHolderPanel _DockableHolderPanel;
	public static JMenuItem _redoMenuItem;
	public static JMenuItem _undoMenuItem;
	static int i = 0;
	public static LabelStatusBarItem _option;
	public static LabelStatusBarItem _empresa;
	public static LabelStatusBarItem _oficina;
	public static LabelStatusBarItem _usuario;
	public static JTextArea _textAreaMensajes;
	public static JList _listNoticias;
	public static JList _listTareas;
	public static JProgressBar _progress;
	public SpiritModel panelNoticia;
	public SpiritModel panelTarea;
	public static SpiritModel panelBackground;
	static DockableFrame frameTareas;
	static DockableFrame frameNoticias;
	ImageIcon backImage;
	
	public MainFrame(String title) throws HeadlessException {
		super(title);
		backImage = SpiritResourceManager.getImageIcon("images/spirit_background.jpg");
		ChangeModeImpl.addChangeModeListener(this);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new MainFrameWindowAdapter());
		PROFILE_NAME = Parametros.getUsuario();
		_panelesEstatico.removeAll();
		panels = new HashMap();
		MainFrameModel.set_panels(panels);
	}
	
	class MainFrameWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent event) {	
        	event = null;
        	new JDCloseSystemModel(Parametros.getMainFrame());
        }
	}

	public DockingManager getDockingManager() {
		if (_tabbedPane == null) {
			return null;
		} else {
			return _DockableHolderPanel.getDockingManager();
		}
	}

	public static void inicializa() {
		long start=System.currentTimeMillis();
		_parametrosIniciales = new ParametrosIniciales();
		MainFrameModel.set_frame(null);
		MainFrameModel.set_frame(new MainFrame("Spirit"));
		MainFrameModel.get_frame().setIconImage(SpiritResourceManager.getImageIcon("images/icons/spirit.png").getImage());
		Parametros.setMainFrame((MainFrame) MainFrameModel.get_frame());
		Lm.setParent(MainFrameModel.get_frame());
		StatusBar statusBar = createStatusBar();
		//Layout de los paneles estáticos
		_panelesEstatico.setLayout(new BorderLayout());
		//_panelesEstatico.add(createAccesosFrame(), BorderLayout.CENTER);
		//_panelesEstatico.add(createSoftwareFrame(), BorderLayout.SOUTH);
		_DockableHolderPanel = new DockableBarDockableHolderPanel(MainFrameModel.get_frame());
		_tabbedPane = new JideTabbedPane();
		//setFavoritosBar();
		//((MainFrame) MainFrameModel._frame).getDockableBarManager().addDockableBar(getFavoritosBar());
		//Muestro los moudlos en el toolbar segun los roles que tiene habilitado ese usuario
		//ToolBarBuilder.mostrarModulosFavoritosCommandBar();
		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().getMainContainer().setLayout(new BorderLayout());
		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().getMainContainer().add(_DockableHolderPanel,BorderLayout.CENTER);
		//((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().getMainContainer().add(createAccesosFrame(),BorderLayout.WEST);
		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().getMainContainer().add(statusBar,BorderLayout.AFTER_LAST_LINE);
		
		//_tabbedPane.addTab("Creacional", JideIconsFactory.getImageIcon("jide/dockableframe_11.gif"),_DockableHolderPanel);
		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().setProfileKey(getPROFILE_NAME());

		//_DockableHolderPanel.getDockableBarManager().getMainContainer().add(panelBackground);
		//_DockableHolderPanel.getDockingManager().addFrame(createTaskListFrame());
		
		_DockableHolderPanel.getDockingManager().addFrame(createAccesosFrame());
		_DockableHolderPanel.getDockingManager().addFrame(createNoticiasFrame());
		//_DockableHolderPanel.getDockingManager().addFrame(createMensajesFrame());

		_DockableHolderPanel.getDockingManager().setAutohidable(true);
		_DockableHolderPanel.getDockingManager().setFloatable(false);
		_DockableHolderPanel.getDockingManager().setHidable(true);
		_DockableHolderPanel.getDockingManager().setInitSplitPriority(DockingManager.SPLIT_WEST_SOUTH_EAST_NORTH);
		
		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().loadLayoutData();
		_DockableHolderPanel.getLayoutPersistence().loadLayoutDataFrom(getPROFILE_NAME());
		
		((MainFrame) MainFrameModel.get_frame()).toFront();
		MainFrameModel.set_dockingManager(MainFrame.get_frame().getDockingManager());
		crearPanelBackground();
		long fin=System.currentTimeMillis();
		System.out.println("---------------------inicializa Mainframe: "+(fin-start)/1000+" seg");
	}

	private static void clearUp() {
		if (_DockableHolderPanel.getLayoutPersistence() != null)
			_DockableHolderPanel.getLayoutPersistence().saveLayoutDataAs(getPROFILE_NAME());

		((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().saveLayoutData();
		((MainFrame) MainFrameModel.get_frame()).dispose();
		Lm.setParent(null);
		MainFrameModel.set_frame(null);
	}

	public static String getPROFILE_NAME() {
		return PROFILE_NAME;
	}

	protected static DockableFrame createNoticiasFrame() {
		frameNoticias = new DockableFrame("Noticias",SpiritResourceManager.getImageIcon("images/icons/docking/news.png"));		
		frameNoticias.getContext().setInitMode(4);
		frameNoticias.getContext().setInitSide(DockContext.DOCK_SIDE_SOUTH);
		frameNoticias.getContext().setInitIndex(2);
		_listNoticias = new JList();
		_listNoticias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		try {
			llenarNoticias();
			_listNoticias.addMouseListener(((MainFrame) MainFrameModel._frame).new MouseListenerNoticias());
			_listNoticias.addKeyListener(((MainFrame) MainFrameModel._frame).new KeyListenerNoticias());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		frameNoticias.getContentPane().add(createScrollPane(_listNoticias));
		frameNoticias.setPreferredSize(new Dimension(frameNoticias.getWidth(), 50));
		//frameNoticias.setMaximumSize(new Dimension(frameNoticias.getWidth(), 50));
		//_listNoticias.setEditable(true);
		return frameNoticias;
	}
	protected static DockableFrame createAccesosFrame() {
		DockableFrame frame = new DockableFrame("Modulos", JideIconsFactory.getImageIcon("jide/dockableframe_5.gif"));
		frame.getContext().setInitMode(4);
		frame.getContext().setInitSide(DockContext.DOCK_SIDE_WEST);
		frame.getContext().setInitIndex(1);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().add(	new AccessBar(new BotonFrecuentesActionListener(),new PanelFrecuentesActionListener()));
		frame.setPreferredSize(new Dimension(170, 200));
		frame.setAutohidable(false);
		frame.setMaximizable(false);
		frame.setHidable(false);
		frame.setRearrangable(false);
		return frame;
	}
	
	protected static DockableFrame createSoftwareFrame() {
		DockableFrame frame = new DockableFrame("Software", JideIconsFactory.getImageIcon("jide/dockableframe_5.gif"));
		frame.getContext().setInitMode(4);
		frame.getContext().setInitSide(2);
		frame.getContext().setInitIndex(1);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setPreferredSize(new Dimension(200, 100));
		frame.setMinimumSize(new Dimension(100, 100));
		
		if (!isSuperuser()) {// && !"DEVEL".equals(SpiritLogin._usuario.getTipousuario())) {
			JLabel empresa = new JLabel("EMPRESA: " + Parametros.getNombreEmpresa(),JLabel.CENTER);
			empresa.setFont(new Font("Times New Roman", Font.BOLD, 12));
			frame.add(empresa);
			
			if (Parametros.getLogoEmpresa() != null) {
				JToggleButton btnLogo = new JToggleButton();
				btnLogo.setIcon(SpiritResourceManager.getImageIcon(Parametros.getLogoEmpresa()));
				frame.add(btnLogo);
				
				btnLogo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evento) {
						try {
							Properties obj = new Properties( System.getProperties() );
							Runtime rt = Runtime.getRuntime();
							//TODO: Porque esta esto quemado????
							//if(System.getProperty("os.language").equals("es")){
								String URL1 = "C:\\Archivos de Programa\\Internet Explorer\\iexplore.exe \"http://" + ParametrosIniciales.getUrlEmpresa() + "\" ";
								Process p1 = rt.exec(URL1);
							/*}
							if(System.getProperty("os.language").equals("en")){
								String URL2 = "C:\\Program Files\\Internet Explorer\\iexplore.exe \"http://" + ParametrosIniciales.getUrlEmpresa() + "\" ";
								Process p2 = rt.exec(URL2);
							}*/
						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						} 
					}
				});
			}
			
			if (!isAdmin()) {
				JLabel oficina = new JLabel("OFICINA: " + Parametros.getNombreOficina(),JLabel.CENTER);
				oficina.setFont(new Font("Times New Roman", Font.BOLD, 12));
				frame.add(oficina);
			} else {
				JLabel tipoUsuario = new JLabel("ADMINISTRADOR",JLabel.CENTER);
				tipoUsuario.setFont(new Font("Times New Roman", Font.BOLD, 12));
				frame.add(tipoUsuario);
			}
		} 
		
		if (isSuperuser()) {// || isDeveloper()) {
			String etiquetaTipoUsuario = "";
			
			if (isSuperuser())
				etiquetaTipoUsuario = "SUPER ADMINISTRADOR";
			/*else if (isDeveloper())
				etiquetaTipoUsuario = "SUPER DESARROLLADOR";*/
			
			JLabel tipoUsuario = new JLabel(etiquetaTipoUsuario,JLabel.CENTER);
			tipoUsuario.setFont(new Font("Times New Roman", Font.BOLD, 12));
			frame.add(tipoUsuario);
		}
		
		JLabel usuario = new JLabel("USUARIO: " + Parametros.getUsuario(),JLabel.CENTER);
		usuario.setFont(new Font("Times New Roman", Font.BOLD, 12));
		frame.add(usuario);
		frame.repaint();
				
		return frame;
	}

	private static boolean isAdmin() {
		return "A".equals(SpiritLogin._usuario.getTipousuario());
	}

	private static boolean isDeveloper() {
		return "D".equals(SpiritLogin._usuario.getTipousuario());
	}

	private static boolean isSuperuser() {
		return "S".equals(SpiritLogin._usuario.getTipousuario());
	}
	
	protected static DockableFrame createTaskListFrame() {
		frameTareas = new DockableFrame("Lista Tareas",SpiritResourceManager.getImageIcon("images/icons/docking/tasks.png"));
		frameTareas.getContext().setInitMode(4);
		frameTareas.getContext().setInitSide(2);
		_listTareas = new JList();
		_listTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//busquedaTareas(ProcesosCompras.NO_MENSAJE);
		_listTareas.addKeyListener(((MainFrame) MainFrameModel._frame).new KeyListenerTareas());
		_listTareas.addMouseListener(((MainFrame) MainFrameModel._frame).new MouseListenerTareas());
		_listTareas.setToolTipText("Lista de Tareas");
		frameTareas.getContentPane().add(createScrollPane(_listTareas));
		frameTareas.setPreferredSize(new Dimension(200, 100));
		frameTareas.setMinimumSize(new Dimension(200, 100));
		frameTareas.setAutohidable(false);
		return frameTareas;
	}

	@SuppressWarnings("unchecked")
	private static void busquedaTareas(int mensaje) {
		/*DefaultListModel modeloLista = ProcesosCompras.busquedaTareas(
				((UsuarioIf)Parametros.getUsuarioIf()).getId().toString()
				, mensaje);
		_listTareas.setModel( modeloLista);*/
	}
	
	private static void llenarListaTareas(DefaultListModel modeloLista,List listaTareas){
		Iterator it = listaTareas.iterator();
		while(it.hasNext()){
			modeloLista.addElement(it.next());
		}
	}

	class KeyListenerTareas extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			if ( e.getKeyCode() == KeyEvent.VK_F5 ){
				frameTareas.setTitle("Lista Tareas - Actualizando...");
				//busquedaTareas(ProcesosCompras.MENSAJE);
				frameTareas.setTitle("Lista Tareas");
			}
		}
	}

	class MouseListenerTareas extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if ( e.getClickCount() == 2 ){
				JbpmContext jbpmContext = SpiritBpm.jbpmConfiguration.createJbpmContext();
				Object tareaSeleccionada = _listTareas.getSelectedValue();
				try{
					if ( tareaSeleccionada instanceof TaskInstance){
						TaskInstance tarea = retornarTarea( jbpmContext
								, ((TaskInstance)tareaSeleccionada).getId() ) ;

						if ( tarea!=null ){
							presentarPanel(tarea);
						}
					}
				}
				finally{
					jbpmContext.close();
				}
			}
		}
	}
	private void presentarPanel(TaskInstance tarea) {
		String nombrePanel = (String) tarea.getContextInstance().getVariable("nombrePanelSiguiente");
		String documentoId = (String) tarea.getContextInstance().getVariable("documentoId");
		if ( nombrePanel != null ){
			CriteriaTarea criteria = devolverCriteria(tarea);
			try {
				criteria.realizarAccion();
				if (panels.size()>0 && panels.get(nombrePanel)!=null) {
					((MainFrame) MainFrameModel._frame).getDockingManager().removeFrame(nombrePanel);
				} 
				crearPanelTarea(criteria);
			
				PanelHandler.setPanelNameTitleBar(panelTarea);
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.WARNING);
			}
		}else{
			SpiritAlert.createAlert("No esta definido el Nombre del Panel", SpiritAlert.WARNING);
		}
	}
	private void crearPanelTarea(CriteriaTarea criteria) {
		panelTarea = criteria.getPanel();
		if (panelTarea!=null){
			DockableFrame panel = PanelHandler.createPanelesApp(panelTarea);
			((MainFrame) MainFrameModel._frame).getDockingManager().addFrame(panel);
			((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(panel.getName());
		} else {
			SpiritAlert.createAlert("No se puedo presentar el panel", SpiritAlert.WARNING);
		}
	}
	
	private CriteriaTarea devolverCriteria(TaskInstance tarea){
		String nombreCriteria = (String) tarea.getContextInstance().getVariable("nombreCriteria");
		String nombrePanel= 
			(String) tarea.getContextInstance().getVariable("nombrePanelSiguiente");
		String documentoId = 
			(String) tarea.getContextInstance().getVariable("documentoId");
		if ( "PrincipalCompras".equalsIgnoreCase(nombreCriteria) ){
			return new CompraPrincipalCriteriaTarea(
					tarea);
		}
		return null;
	}
	
	private static void crearPanelBackground() {
		String nombrePanel= "Background";
		if (panels.size()>0 && panels.get(nombrePanel)!=null) {
			((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(nombrePanel).dispose();
			((MainFrame) MainFrameModel._frame).getDockingManager().removeFrame(nombrePanel);
		}
		
		panelBackground = (SpiritModel) new SpiritBackgroundModel();
		DockableFrame panel = PanelHandler.createPanelesApp(panelBackground);
		MainFrameModel.setPanelBackground(panel);
		((MainFrame) MainFrameModel._frame).getDockingManager().addFrame(panel);
		((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(panel.getName());
		((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(panel.getName()).setShowTitleBar(false);
		((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(panel.getName()).setTabDockAllowed(false);
		((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(panel.getName()).setSideDockAllowed(false);
		((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(panel.getName()).setShowGripper(false);
		MainFrameModel.get_frame().setTitle("Spirit");
		ActivePanel.getSingleton().setActivePanel(null);
	}

	/*private void crearPanelTarea(TaskInstance tarea, SolicitudCompraIf solicitudCompraIf) {
		panelTarea = (SpiritModel)new SolicitudCompraModel(solicitudCompraIf,tarea);
		DockableFrame panel = PanelHandler.createPanelesApp(panelTarea);
		((MainFrame) MainFrameModel._frame).getDockingManager().addFrame(panel);
		((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(panel.getName());
	}		
	};
	private void presentarPanel(TaskInstance tarea) {
			String idSolicitudCompra = 
				(String) tarea.getContextInstance().getVariable("solicitudCompra");
			if ( idSolicitudCompra != null ){
				try {
					List listaSolicitudes = (List) SolicitudCompraModel
						.getSolicitudCompraSessionService().findSolicitudCompraById(Long.valueOf(idSolicitudCompra));
					if ( listaSolicitudes.size() == 1 ){
						SolicitudCompraEJB solicitudCompraEJB = (SolicitudCompraEJB) listaSolicitudes.get(0);
						String nombrePanel= "Solicitud de Compra";
						if (panels.size()>0 && panels.get(nombrePanel)!=null) {
							((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(nombrePanel);
							((MainFrame) MainFrameModel._frame).getDockingManager().removeFrame(nombrePanel);
							crearPanelTarea(tarea, solicitudCompraEJB);
						} else {
							crearPanelTarea(tarea, solicitudCompraEJB);
						}
						try {
							PanelHandler.setPanelNameTitleBar(panelTarea);
						} catch (GenericBusinessException e1) {
							e1.printStackTrace();
						}
					}
					else{
						SpiritAlert.createAlert("No existe esa solicitud de Compra o ha sido borrada", SpiritAlert.WARNING);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}

			} else{
				SpiritAlert.createAlert("No existe esa solicitud de Compra", SpiritAlert.WARNING);
			}
		}

		private void crearPanelTarea(TaskInstance tarea, SolicitudCompraIf solicitudCompraIf) {
			panelTarea = (SpiritModel)new SolicitudCompraModel(solicitudCompraIf,tarea);
			DockableFrame panel = PanelHandler.createPanelesApp(panelTarea);
			((MainFrame) MainFrameModel._frame).getDockingManager().addFrame(panel);
			((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(panel.getName());
		}		
	};*/

	private TaskInstance retornarTarea(JbpmContext jbpmContext,long idTarea){
		TaskInstance taskInstance = null;
		try{
			taskInstance = jbpmContext.getTaskInstance(idTarea); 
		} catch(java.lang.NumberFormatException er){
			er.printStackTrace();
		}
		return taskInstance;
	}



	private static void llenarNoticias() throws GenericBusinessException {
		DefaultListModel modelListAsuntos = new DefaultListModel();
		
		//id del Usuario actual
		Long idUsuario = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
 
		//Coleccion de noticias para el usuario actual
		Collection asuntoNoticias = SessionServiceLocator.getNoticiasSessionService().findNoticiasByUsuarioNoticiasId(idUsuario);
		Iterator asuntoNoticiasIt = asuntoNoticias.iterator();	
		
		//Creo instancia de cada una de las noticias
		while (asuntoNoticiasIt.hasNext()) {
			NoticiasIf asuntoNoticiasIf = (NoticiasIf) asuntoNoticiasIt.next();
			//Añado al modelo cada uno los los asuntos que se le deben mostrar al usuario actual
			modelListAsuntos.addElement(asuntoNoticiasIf);				
		}
		//Añado el modelo con los asuntos a la lista
		_listNoticias.setModel(modelListAsuntos);
	}
	
	class KeyListenerNoticias extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			if ( e.getKeyCode() == KeyEvent.VK_F5 ){
				try {
					frameNoticias.setTitle("Noticias - Actualizando...");
					llenarNoticias();
					
				} catch (GenericBusinessException ex) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					ex.printStackTrace();
				}
				frameNoticias.setTitle("Noticias");
			}
		}
	}
	
	class MouseListenerNoticias extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if ( e.getClickCount() == 2 ){
				NoticiasIf noticiaSeleccionada = (NoticiasIf) _listNoticias.getSelectedValue();
				String nombrePanel= "Noticia";
				if (panels.size()>0 && panels.get(nombrePanel)!=null) {
					//((MainFrame) MainFrameModel._frame).getDockingManager().showFrame("Noticia");
					((MainFrame) MainFrameModel._frame).getDockingManager().getFrame(nombrePanel).dispose();
					((MainFrame) MainFrameModel._frame).getDockingManager().removeFrame(nombrePanel);
					crearPanelNoticia(noticiaSeleccionada);
				} else {
					crearPanelNoticia(noticiaSeleccionada);
				}
				try {
					PanelHandler.setPanelNameTitleBar(panelNoticia);
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
				}
			}
		}

		private void crearPanelNoticia(NoticiasIf noticiaSeleccionada) {
			panelNoticia = (SpiritModel) new NoticiaModel(noticiaSeleccionada.getFechaIni(), noticiaSeleccionada.getFechaFin(), noticiaSeleccionada.getStatus(), noticiaSeleccionada.getUsuarioId(), noticiaSeleccionada.getAsunto(), noticiaSeleccionada.getNoticia(), noticiaSeleccionada.getArchivo());
			DockableFrame panel = PanelHandler.createPanelesApp(panelNoticia);
			((MainFrame) MainFrameModel._frame).getDockingManager().addFrame(panel);
			((MainFrame) MainFrameModel._frame).getDockingManager().showFrame(panel.getName());
		}		
	};

	protected static DockableFrame createMensajesFrame() {
		//DockableFrame frame = new DockableFrame("Mensajes", JideIconsFactory.getImageIcon("images/icons/docking/messages.png"));
		DockableFrame frame = new DockableFrame("Mensajes",SpiritResourceManager.getImageIcon("images/icons/docking/messages.png"));
		frame.getContext().setInitMode(4);
		frame.getContext().setInitSide(2);
		frame.getContext().setInitIndex(0);
		frame.getContext().setCurrentDockSide(2);
		_textAreaMensajes = new JTextArea();
		frame.getContentPane().add(createScrollPane(_textAreaMensajes));
		//_textAreaMensajes.setText("Aplicación cargada con éxito\n");
		_textAreaMensajes.setEditable(false);
		
		//Creo Un PoPup Menu Para el Area de Mensajes
		final JPopupMenu menu = new JPopupMenu();
	    
	    // Create and add a menu items
		//Opcion Que Permite Borrar todo el contenido de la ventana de Mensajes
	    JMenuItem itemLimpiar = new JMenuItem("Limpiar");
		itemLimpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		_textAreaMensajes.setText("");
	    	}
	    });
		menu.add(itemLimpiar);
		//Opcion Que Permite Copiar todo el contenido de la ventana de Mensajes
	    JMenuItem itemCopiar = new JMenuItem("Copiar Todo");
	    itemCopiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		StringSelection ss = new StringSelection(_textAreaMensajes.getText());
	    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	    		
	    	}
	    });
	    menu.add(itemCopiar);
	    	    
	    // Set the component to show the popup menu
	    _textAreaMensajes.addMouseListener(new MouseAdapter() {
	        public void mouseReleased(MouseEvent evt) {
	            if (evt.isPopupTrigger()) {
	                menu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });
	  
		frame.setPreferredSize(new Dimension(200, 100));
		frame.setAutohidable(false);
		return frame;
	}

	private static JScrollPane createScrollPane(Component component) {
		JScrollPane pane = new JideScrollPane(component);
		return pane;
	}
	
	protected static  StatusBar createStatusBar() {
		StatusBar statusBar = new StatusBar();
		
		JPanel panel = new JPanel();
	    _progress = new JProgressBar();
        _progress.setString("Conectando a la Base de Datos ... ");
        _progress.setStringPainted(true);
        _progress.setIndeterminate(false);
        _progress.setVisible(false);
        panel.add(_progress);
		statusBar.add(panel, "vary");
		
      	_option = new LabelStatusBarItem("Option");
      	MainFrameModel.set_option(_option);
		_option.setAlignment(0);
		statusBar.add(_option, "flexible");
		_option.setVisible(false);
		
		if (!isSuperuser()) {
			_empresa =  new LabelStatusBarItem("Empresa");
			_empresa.setAlignment(0);
			_empresa.setFont(new Font("Arial", Font.PLAIN, 12));
			_empresa.setText("EMPRESA: " + Parametros.getNombreEmpresa());
			statusBar.add(_empresa, "flexible");
			
			if (!isAdmin()) {
				_oficina = new LabelStatusBarItem("Oficina");
				_oficina.setAlignment(0);
				_oficina.setFont(new Font("Arial", Font.PLAIN, 12));
				_oficina.setText("OFICINA: " + Parametros.getNombreOficina());
				statusBar.add(_oficina, "flexible");
			} /*else {
				JLabel tipoUsuario = new JLabel("ADMINISTRADOR",JLabel.CENTER);
				tipoUsuario.setFont(new Font("Times New Roman", Font.BOLD, 12));
				frame.add(tipoUsuario);
			}*/
		}
		
		_usuario = new LabelStatusBarItem("Usuario");
		_usuario.setAlignment(0);
		_usuario.setFont(new Font("Arial", Font.PLAIN, 12));
		_usuario.setText("USUARIO: " + Parametros.getUsuario());
		statusBar.add(_usuario, "flexible");
		
		TimeStatusBarItem time = new TimeStatusBarItem();
		statusBar.add(time, "flexible");
		MemoryStatusBarItem gc = new MemoryStatusBarItem();
		statusBar.add(gc, "flexible");
		return statusBar;
	}

	protected static MenuBuilder createMenuBuilder() {
		return new MenuBuilder();
	}
	
	public static CommandBar getFavoritosBar() {
		return _favoritosBar;
	}
	
	public static void setFavoritosBar() {
		_favoritosBar = ToolBarBuilder.createFavoritosCommandBar();
	}

	protected static ActionListener createHelpActionListener() {
		return null;
	}

	protected static ActionListener createAboutActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("About");
			}
		};
	}
	
	public static MainFrame get_frame() {
		return ((MainFrame) MainFrameModel.get_frame());
	}	

	/*
	 * Listener asignado a los botones ubicados en los paneles de accesos
	 * frecuentes realizará la carga de paneles de la aplicación en el area de
	 * trabajo
	 */
	public static class BotonFrecuentesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			IconButton b = (IconButton) e.getSource();
			try {
				SpiritModel newPanel = (SpiritModel) Class.forName(b.getName()).newInstance();
				DockableFrame panel = PanelHandler.createPanelesApp(newPanel);
				
			} catch (ClassNotFoundException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			} catch (InstantiationException ie) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				ie.printStackTrace();
			} catch (IllegalAccessException iae) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				iae.printStackTrace();
			}
		}
	}

	/*
	 * Listener asignado a las opciones de menus de cada menu realizará la carga
	 * de paneles de la aplicación en el area de trabajo
	 */
	public static class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem b = (JMenuItem) e.getSource();
			String modelName = b.getName(); 
			PanelHandler.showPanelModel(modelName);
			 
		}
	}

	private static class PanelFrecuentesActionListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().removeDockableBar("Menu Principal");
			((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().removeDockableBar("Standard");
			int p_modulo = ((JOutlookBar) e.getSource()).getSelectedIndex()+1;
			String nombre_modulo = ((JOutlookBar) e.getSource()).getTitleAt(((JOutlookBar) e.getSource()).getSelectedIndex());
			MenuBuilder menuBar = createMenuBuilder();
			((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().addDockableBar(menuBar.buildMenuBar(MainFrameModel.get_frame(),p_modulo,nombre_modulo,new MenuActionListener()));
			((MainFrame) MainFrameModel.get_frame()).getDockableBarManager().addDockableBar(ToolBarBuilder.createStandardCommandBar());
			((MainFrame) MainFrameModel.get_frame()).setTitle("Spirit > " + nombre_modulo);
			
			//Desactivo el icono y el color de background anterior (los iconos por default se setean en AccessBar.java)
			Color colorBackground = ((MainFrame) MainFrameModel.get_frame()).getBackground();
			/*System.out.println("color");
			for(int i=0; i<((JOutlookBar) e.getSource()).getTabCount(); i++){
				String moduloDesactivado = ((JOutlookBar) e.getSource()).getTitleAt(i);
				if(((i+1) != p_modulo) && (((JOutlookBar) e.getSource()).getIconAt(i).toString().split("/modulo/")[1] != moduloDesactivado.trim().toLowerCase()+"O.png")){
					((JOutlookBar) e.getSource()).setIconAt(i,SpiritResourceManager.getImageIcon("images/icons/modulo/"+moduloDesactivado.trim().toLowerCase()+"O.png"));
				}
			}*/
			
			//Seteo el icono activo
			int seleccion = ((JOutlookBar) e.getSource()).getSelectedIndex();
			//((JOutlookBar) e.getSource()).setIconAt(seleccion,SpiritResourceManager.getImageIcon("images/icons/modulo/"+nombre_modulo.trim().toLowerCase()+".png"));
			
			int totalModulos = ((JOutlookBar) e.getSource()).getComponents().length;
			
			//seteo los demas modulos con el background por defecto
			if(totalModulos > 2){
				for(int j=0; j<totalModulos; j++){
					if(j != (seleccion*2)){
						((JOutlookBar) e.getSource()).getComponent(j).setBackground(colorBackground);
					}
				}
			}			
			
			//Cambio el color del background del modulo seleccionado
			if(totalModulos > 2){
				((JOutlookBar) e.getSource()).getComponent(seleccion * 2).setBackground(new Color(251, 196, 113));
			}else{
				((JOutlookBar) e.getSource()).getComponent(0).setBackground(new Color(251, 196, 113));
			}
			
			//Font aaa = new Font("HELVICA",Font.BOLD,10);
			//((JOutlookBar) e.getSource())
			/*String titulo = ((JOutlookBar) e.getSource()).getTitleAt(((JOutlookBar) e.getSource()).getSelectedIndex());
			Font fuenteBase = ((JOutlookBar) e.getSource()).getFont();
			((JOutlookBar) e.getSource()).setFont(new Font(fuenteBase.getName(),Font.BOLD,fuenteBase.getSize()+2));
			((JOutlookBar) e.getSource()).setTitleAt(((JOutlookBar) e.getSource()).getSelectedIndex(), titulo);*/
		}
	}

    protected ContentContainer createContentContainer() {
        return new LogoContentContainer();
    }

    class LogoContentContainer extends ContentContainer {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon imageIcon = SpiritResourceManager.getImageIcon("images/spirit.png");
            imageIcon.paintIcon(this, g, getWidth() - imageIcon.getIconWidth() - 2, 2);
        }
    }

	public void modeChanged(ChangeModeEvent e) {
		_option.setText(e.getMode());
		_option.setVisible(true);
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		if(panel != null){
			if (panel.getMode() == SpiritMode.SAVE_MODE) {
				_option.setBackground(Color.GREEN);
			}
			if (panel.getMode() == SpiritMode.FIND_MODE) {
				_option.setBackground(Parametros.findColor);
			}
			if (panel.getMode() == SpiritMode.UPDATE_MODE) {
				_option.setBackground(Color.YELLOW);
			}
		}	
	}
}