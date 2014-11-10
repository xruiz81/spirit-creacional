package com.spirit.general.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.NoticiasData;
import com.spirit.general.entity.NoticiasIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.entity.UsuarioNoticiasData;
import com.spirit.general.entity.UsuarioNoticiasIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPNoticias;
import com.spirit.general.session.FileManagerSessionService;
import com.spirit.general.session.NoticiasSessionService;
import com.spirit.general.session.UsuarioNoticiasSessionService;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Archivos;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.FinderTable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class NoticiaModel extends JPNoticias {
	
	private static final long serialVersionUID = 5903227613424187387L;
	
	java.util.Date fechaHoy = new java.util.Date();
	
	ArrayList lista;
	protected FinderTable finderTable;
	public boolean isFinderTableVisible = false;
	protected int mode;
	NoticiasIf noticias;
	EmpleadoIf nombreDestinatarios;
	
	//Contiene los nodos ya insertados en la lista de Usuarios
	Map usuariosMap = new HashMap();
	//Contiene los nodos ya insertados en la lista de Destinatarios
	Map destinatariosMap = new HashMap();
	
	private static final int MAX_LONGITUD_ASUNTO = 100;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	
	
	//Constructor de la clase
	public NoticiaModel(){
		initKeyListeners();
		this.showSaveMode();
		
		getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
		
		getTxtAsunto().addKeyListener(new TextChecker(MAX_LONGITUD_ASUNTO));
		//getTxtAsunto().addKeyListener(new MyKeyListenerUpperCase());
		
		//Cargo la lista de usuarios y el usuario autor de la noticia
		cargarTodosUsuarios();
		
		//Cargo los listener
		getListDestinatarios().setModel(new DefaultListModel());
		cargarListenersComponents();
		
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
	}
	
	//Constructor 2 de la clase PARA LA PRESENTACION DE LA NOTICIA
	public NoticiaModel(Date fechaIni, Date fechaFin, String status, final Long idUsuario, String asunto, String noticia, final String archivo){
		initKeyListeners();
		this.showSaveMode();
		try {
			getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
			getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
			getCmbFechaInicio().setDate(fechaIni);
			getCmbFechaInicio().setEnabled(false);
			getCmbFechaFin().setDate(fechaFin);
			getCmbFechaFin().setEnabled(false);
			
			if(status.equals(ESTADO_ACTIVO)) getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
			else getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
			getCmbStatus().setEnabled(false);
			
			UsuarioIf usuarioIf = (UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioById(idUsuario).iterator().next();
			if(usuarioIf.getEmpleadoId() !=  null && !usuarioIf.getEmpleadoId().equals("")){
				getTxtUsuario().setText(((EmpleadoIf)SessionServiceLocator.getEmpleadoSessionService().findEmpleadoById(usuarioIf.getEmpleadoId()).iterator().next()).toString());
			}
			else
				getTxtUsuario().setText("ADMIN");
			
			getTxtUsuario().setEditable(false);
			getTxtAsunto().setText(asunto);
			getTxtAsunto().setEditable(false);
			getTxtNoticia().setText(noticia);
			getTxtNoticia().setEditable(false);
			
			getLblUsuarios().setVisible(false);
			getSpUsuarios().setVisible(false);
			getListUsuarios().setVisible(false);
			getBtnElegir().setVisible(false);
			getBtnTodos().setVisible(false);
			getBtnQuitar().setVisible(false);
			getLblDestinatarios().setVisible(false);
			getSpDestinatarios().setVisible(false);
			getListDestinatarios().setVisible(false);			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurrio un error al intentar abrir el mensaje", SpiritAlert.WARNING);
		}
		
		//Creo el action listener para escojer el archivo adjunto
		ActionListener oActionListenerAgregarArchivo2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					Archivos.abrirArchivoDesdeServidor(archivo);
				} catch (IOException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		};
		
		getTxtArchivo().setEditable(false);
		
		if(archivo != null && !archivo.equals("")){
			getTxtArchivo().setText(archivo);
			getBtnArchivo().setToolTipText("Abrir el archivo");
			getBtnArchivo().addActionListener(oActionListenerAgregarArchivo2);
		}
		else{
			getTxtArchivo().setText("");
			getTxtArchivo().setEnabled(false);
			getBtnArchivo().setEnabled(false);
		}
	}
	
	//Mando a cargar todos los usuarios segun la Empresa y el usuario autor de la noticia
	private void cargarTodosUsuarios(){
		try {
			//Seteo automaticamente el usuario que esta redactando la noticia
			//En la funcion toString en EmpleadoEJB se setea la presentacion de nombre y apellido
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			if(usuario.getEmpleadoId() !=  null && !usuario.getEmpleadoId().equals("")){
				String usuarioNombre = Parametros.getUsuario().toLowerCase();
				EmpleadoIf empleadoIf = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaIdAndByUsuario(Parametros.getIdEmpresa(), usuarioNombre).iterator().next();
				getTxtUsuario().setText(empleadoIf.toString());
			}
			else
				getTxtUsuario().setText("ADMIN");
			
			//Seteo el model del List View de los Roles Seleccionados en blanco
			//getListDestinatarios().setModel(new DefaultListModel());
			DefaultListModel modelListUsuarios = new DefaultListModel();
			
			//Creo la coleccion con los usuarios segun la empresa
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(Parametros.getIdEmpresa());
			Iterator usuariosIt = usuarios.iterator();
			
			//Creo instancia de cada uno de los usuarios
			while (usuariosIt.hasNext()) {
				UsuarioIf usuarioIf = (UsuarioIf) usuariosIt.next();
				
				if(usuarioIf.getEmpleadoId() != null && !usuarioIf.getEmpleadoId().equals("")){
					//Presente primer nombre y apellido de cada usuario de la empresa
					String destinatario = usuarioIf.getUsuario();
					if(!destinatario.equals(Parametros.getUsuario().toLowerCase()) && !destinatariosMap.containsKey(usuarioIf.getEmpleadoId())){
						EmpleadoIf nombreEmpleados = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaIdAndByUsuario(Parametros.getIdEmpresa(), destinatario).iterator().next();
						modelListUsuarios.addElement(nombreEmpleados);				
						
						//Añado los usuarios al mapa
						usuariosMap.put(usuarioIf.getEmpleadoId(),usuarioIf);
					}
				}
			}
			
			//Añado el modelo con los items seleccionados a la lista
			sort(modelListUsuarios);
			getListUsuarios().setModel(modelListUsuarios);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public DefaultListModel sort(DefaultListModel list) {
	    for (int k=0; k<list.size()-1; k++) {
	       boolean isSorted = true;
	       for (int i=1; i<list.size()-k; i++) {
	          if (((EmpleadoIf)list.get(i)).getNombres().compareTo(((EmpleadoIf)list.get(i-1)).getNombres())<0) {
	             Object tmpObj = list.get(i);
	             list.remove(i);
	             list.add(i,list.get(i-1));
	             list.remove(i-1);
	             list.add(i-1,tmpObj);
	             isSorted=false;
	          }
	       }
	       if (isSorted) {
	          break;
	       }
	    }
	    return list;
	}

	
	private void cargarListenersComponents(){
		getBtnArchivo().addActionListener(oActionListenerAgregarArchivo);
		getBtnElegir().addActionListener(oActionListenerAgregarDestinatarios);
		getBtnQuitar().addActionListener(oActionListenerQuitarDestinatarios);
		getBtnTodos().addActionListener(oActionListenerAgregarTodosDestinatarios);
	}
	
	//Creo el action listener para escojer el archivo adjunto
	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			
			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {	"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif", new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc", new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);
			
			String[] options = new String[]{"Si","No"};
			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try {
					boolean existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botn ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */
						if ((actionEvent.getSource() == getBtnArchivo()))
							getTxtArchivo().setText(fileChooser.getSelectedFile().toString());
						
						/**
						 * ejecuto el archivo con su respectivo programa para poder ser
						 * previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmacin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}
						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} else {
						int opcion = JOptionPane.showOptionDialog(null, "Archivo ya existe, desea reemplazarlo?", "Confirmacin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText(fileChooser.getSelectedFile().toString());
						} else{
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
				}
			} 
		}
	};
	
	//listener para el evento del boton agregar usuarios a la lista de destinatarios
	ActionListener oActionListenerAgregarDestinatarios = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			
			//Obtengo el model del List View de todos los roles
			DefaultListModel modelListUsuarios = (DefaultListModel) getListUsuarios().getModel() ;
			//Obtengo el model del List View de los roles seleccionados
			DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel() ;
			//Mando a llamar a la funcion que me permite mover los roles de una lista a otra
			moverUsuariosEntreListas(modelListUsuarios, modelListDestinatarios, getListUsuarios(), usuariosMap, destinatariosMap);
		}};
		
		//listener para el evento del boton agregar Todos los usuarios a la lista de destinatarios
		ActionListener oActionListenerAgregarTodosDestinatarios = new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				//Obtengo el model del List View de todos los roles
				DefaultListModel modelListUsuarios = (DefaultListModel) getListUsuarios().getModel() ;
				//Obtengo el model del List View de los roles seleccionados
				DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel() ;
				//Mando a llamar a la funcion que me permite mover los roles de una lista a otra
				moverTodosUsuariosEntreListas(modelListUsuarios, modelListDestinatarios, getListUsuarios(), usuariosMap, destinatariosMap);
			}};
			
			//listener para el evento del boton agregar de destinatarios a usuarios
			ActionListener oActionListenerQuitarDestinatarios = new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					
					//Obtengo el model del List View de todos los roles
					DefaultListModel modelListUsuarios = (DefaultListModel) getListUsuarios().getModel() ;
					//Obtengo el model del List View de los roles seleccionados
					DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel() ;
					//Mando a llamar a la funcion que me permite mover los roles de una lista a otra
					moverUsuariosEntreListas(modelListDestinatarios,modelListUsuarios,getListDestinatarios(),destinatariosMap,usuariosMap);
				}};
				
				//Funcion que me permite mover los usuarios entre las listas
				private void moverUsuariosEntreListas(DefaultListModel modelListUsuarios, DefaultListModel modelListDestinatarios,JList getListUsuarios, Map usuariosMap, Map destinatariosMap){
					//Obtengo todos los usuarios seleccionados del List View Origen
					Object[] usuariosSeleccionados =  getListUsuarios.getSelectedValues();
					//Si no ha sido seleccionado ningun usuario, se muestra un mensaje de aviso
					if (usuariosSeleccionados.length==0)
						JOptionPane.showMessageDialog(null,	"Por favor seleccione el/los usuarios(s) que desea mover!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
					else{
						//Recorro uno a uno los usuarios que fueron seleccionados del List View Origen
						for(int i=0;i < usuariosSeleccionados.length; i++){
							//Creo una instancia del elemento seleccionado i
							EmpleadoIf usuarioSeleccionado = (EmpleadoIf) usuariosSeleccionados[i];
							//Remueve el elemento del listview y del mapa de origen
							modelListUsuarios.removeElement(usuariosSeleccionados[i]);
							usuariosMap.remove(usuarioSeleccionado.getId());
							//Agrego el elemento en el listview y en el mapa de destino
							modelListDestinatarios.addElement(usuariosSeleccionados[i]);
							destinatariosMap.put(usuarioSeleccionado.getId(),usuarioSeleccionado);
						}
					}
				}
				
				//Funcion que me permite mover los usuarios entre las listas
				private void moverTodosUsuariosEntreListas(DefaultListModel modelListUsuarios, DefaultListModel modelListDestinatarios,JList getListUsuarios, Map usuariosMap, Map destinatariosMap){
					
					Object[] usuariosSeleccionados = new Object[getListUsuarios.getModel().getSize()];
					for(int j=0;j < getListUsuarios.getModel().getSize(); j++){
						usuariosSeleccionados[j] = getListUsuarios.getModel().getElementAt(j);
					}
					
					//Recorro uno a uno los usuarios que fueron seleccionados del List View Origen
					for(int i=0;i < usuariosSeleccionados.length; i++){
						//Creo una instancia del elemento seleccionado i
						EmpleadoIf usuarioSeleccionado = (EmpleadoIf) usuariosSeleccionados[i];
						//Remueve el elemento del listview y del mapa de origen
						modelListUsuarios.removeElement(usuariosSeleccionados[i]);
						usuariosMap.remove(usuarioSeleccionado.getId());
						//Agrego el elemento en el listview y en el mapa de destino
						modelListDestinatarios.addElement(usuariosSeleccionados[i]);
						destinatariosMap.put(usuarioSeleccionado.getId(),usuarioSeleccionado);	
					}
				}
				
				public void save() {
					if (validateFields()) {						
						Long idNoticias = 0L;						
						try {							
							//Guardo datos en tabla Noticias
							NoticiasData dataNoticias = new NoticiasData();
							dataNoticias.setAsunto(this.getTxtAsunto().getText());
							dataNoticias.setNoticia(this.getTxtNoticia().getText());
							dataNoticias.setEmpresaId(Parametros.getIdEmpresa());
							
							dataNoticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId());
							
							dataNoticias.setFechaCreacion(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
							dataNoticias.setFechaFin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
							dataNoticias.setFechaIni(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
							dataNoticias.setNoticia(this.getTxtNoticia().getText());
							
							if ( getTxtArchivo().getText()!=null && !getTxtArchivo().getText().equals("")  ){
								File archivoTmp = new File(getTxtArchivo().getText());
								FileInputStreamSerializable archivoSerial = new FileInputStreamSerializable(archivoTmp);
								SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoSerial, Parametros.getUrlCarpetaServidor(), archivoTmp.getName());
								
								int puntoExt = archivoTmp.getName().lastIndexOf(".");
					    		String strFilename = archivoTmp.getName().substring(0, puntoExt) + ".zip";	    	
					    		dataNoticias.setArchivo(Parametros.getUrlCarpetaServidor() + strFilename.replaceAll(" ", "_"));
							} else
								dataNoticias.setArchivo(null);
							
							if(this.getCmbStatus().getSelectedItem().toString() == NOMBRE_ESTADO_ACTIVO){
								dataNoticias.setStatus(ESTADO_ACTIVO);
							}
							
							else dataNoticias.setStatus(ESTADO_INACTIVO);
							
							//Guardo el registro en la base
							idNoticias = SessionServiceLocator.getNoticiasSessionService().addNoticias(dataNoticias).getPrimaryKey();
							
							//SesionesGeneral.getNoticiasSessionService().addNoticias(dataNoticias);
							
							//Guardo datos en tabla Usuario_Noticias
							UsuarioNoticiasData dataUsuarioNoticias = new UsuarioNoticiasData();
							
							//Obtengo todos los destinatarios que han sido seleccionados
							DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel();
							
							EmpleadoIf empleadoIf;
							
							//Seteo el id de la noticia y los id de los destinatarios
							for(int h=0; h < modelListDestinatarios.size(); h++){
								dataUsuarioNoticias.setNoticiasId(idNoticias);
								
								empleadoIf = (EmpleadoIf) modelListDestinatarios.getElementAt(h);
								
								dataUsuarioNoticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleadoIf.getId()).iterator().next()).getId());	
								
								//Guardo el registro en la base
								SessionServiceLocator.getUsuarioNoticiasSessionService().addUsuarioNoticias(dataUsuarioNoticias);
								
							}
							
							//Guardo entre los destinatarios tambien al autor
							dataUsuarioNoticias.setNoticiasId(idNoticias);
							
							String usuario = Parametros.getUsuario().toLowerCase();
							dataUsuarioNoticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(usuario).iterator().next()).getId());	
							
							//Guardo el registro del remitente en la base
							SessionServiceLocator.getUsuarioNoticiasSessionService().addUsuarioNoticias(dataUsuarioNoticias);
							
							//SesionesGeneral.getNoticiasSessionService().addNoticias(dataNoticias);
							SpiritAlert.createAlert("Noticia guardada con \u00e9xito",SpiritAlert.INFORMATION);
							SpiritCache.setObject("noticias",null);
							clean();	
							showSaveMode();
							
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
							e.printStackTrace();
						} catch (Exception e) {
							SpiritAlert.createAlert("Error al guardar la noticia", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} 
				}
				
				public boolean validateFields() {
					String strAsunto = this.getTxtAsunto().getText();
					String strNoticia = this.getTxtNoticia().getText();
					Date fechaInicio = getCmbFechaInicio().getDate();
					Date fechaFin = getCmbFechaFin().getDate();
					
					if (fechaInicio.after(fechaFin)) {
						SpiritAlert.createAlert("La Fecha Inicio no puede estar después de la Fecha Fin!", SpiritAlert.WARNING);
						getCmbFechaInicio().grabFocus();
						return false;
					}
					
					if ((("".equals(strAsunto)) || (strAsunto == null))) {
						SpiritAlert.createAlert("Debe ingresar el Asunto de su Noticia !!",SpiritAlert.WARNING);
						return false;
					}
					if ((("".equals(strNoticia)) || (strNoticia == null))) {
						SpiritAlert.createAlert("Debe ingresar el Texto de su Noticia !!",SpiritAlert.WARNING);
						return false;
					}
					if (getListDestinatarios().getModel().getSize() == 0){
						SpiritAlert.createAlert("Debe elegir al menos un Destinatario !!",SpiritAlert.WARNING);
						return false;
					}
					return true;
				}
				
				
				public void delete() {
					SpiritAlert.createAlert("Una vez grabada, una Noticia no puede ser eliminada",SpiritAlert.INFORMATION);
				}
				
				public void report() {
					// TODO Auto-generated method stub
					
				}
				
				public void refresh() {
					usuariosMap.clear();
					destinatariosMap.clear();
					getListDestinatarios().clearSelection();
					DefaultListModel modelListDestinatarios = new DefaultListModel();
					getListDestinatarios().setModel(modelListDestinatarios);
					getListUsuarios().clearSelection();
					DefaultListModel modelListUsuarios = new DefaultListModel();
					getListUsuarios().setModel(modelListUsuarios);
					cargarTodosUsuarios();
				}
				
				public void duplicate() {
					// TODO Auto-generated method stub
				}
				
				public void quickSearch() {
					new JDQuickSearchModel(Parametros.getMainFrame());
				}
				
				public void addDetail() {}
				
				public void updateDetail() {}
				
				public void deleteDetail() {
					
				}
				
				public void update() {
					if (validateFields()) {
						try {
							//Guardo datos en tabla Noticias
							noticias.setAsunto(this.getTxtAsunto().getText());
							noticias.setNoticia(this.getTxtNoticia().getText());
							noticias.setEmpresaId(Parametros.getIdEmpresa());
							
							noticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId());
							
							noticias.setFechaCreacion(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
							noticias.setFechaFin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
							noticias.setFechaIni(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
							noticias.setNoticia(this.getTxtNoticia().getText());
							
							if ( getTxtArchivo().getText()!=null && !getTxtArchivo().getText().equals("")  ){
								File archivoTmp = new File(getTxtArchivo().getText());
								FileInputStreamSerializable archivoSerial = new FileInputStreamSerializable(archivoTmp);
								SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoSerial, Parametros.getUrlCarpetaServidor(), archivoTmp.getName());
								
								int puntoExt = archivoTmp.getName().lastIndexOf(".");
					    		String strFilename = archivoTmp.getName().substring(0, puntoExt) + ".zip";	    	
					    		noticias.setArchivo(Parametros.getUrlCarpetaServidor() + strFilename.replaceAll(" ", "_"));
							} else
								noticias.setArchivo(null);
							
							if(this.getCmbStatus().getSelectedItem().toString() == NOMBRE_ESTADO_ACTIVO){
								noticias.setStatus(ESTADO_ACTIVO);
							}
							else noticias.setStatus(ESTADO_INACTIVO);
							
							//Guardo el registro en la base
							SessionServiceLocator.getNoticiasSessionService().saveNoticias(noticias);
							
							//Borro el registro correspondiente antiguo de la tabla Usuario Noticias
							Collection usuarioNoticias = SessionServiceLocator.getUsuarioNoticiasSessionService().findUsuarioNoticiasByNoticiasId(noticias.getId());
							Iterator usuarioNoticiasIt = usuarioNoticias.iterator();
							
							while(usuarioNoticiasIt.hasNext()){
								UsuarioNoticiasIf usuarioNoticiasIf = (UsuarioNoticiasIf) usuarioNoticiasIt.next();
								SessionServiceLocator.getUsuarioNoticiasSessionService().deleteUsuarioNoticias(usuarioNoticiasIf.getId());
							}
							
							//Guardo datos en tabla Usuario_Noticias
							UsuarioNoticiasData dataUsuarioNoticias = new UsuarioNoticiasData();
							
							//Obtengo todos los destinatarios que han sido seleccionados
							DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel();
							
							EmpleadoIf empleadoIf;
							//Seteo el id de la noticia y los id de los destinatarios
							for(int h=0; h < modelListDestinatarios.size(); h++){
								dataUsuarioNoticias.setNoticiasId(noticias.getId());
								
								empleadoIf = (EmpleadoIf) modelListDestinatarios.getElementAt(h);
								
								dataUsuarioNoticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleadoIf.getId()).iterator().next()).getId());	
								
								//Guardo el registro en la base
								SessionServiceLocator.getUsuarioNoticiasSessionService().addUsuarioNoticias(dataUsuarioNoticias);
							}
							
							//Guardo entre los destinatarios tambien al autor
							dataUsuarioNoticias.setNoticiasId(noticias.getId());
							
							String usuario = Parametros.getUsuario().toLowerCase();
							dataUsuarioNoticias.setUsuarioId(((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(usuario).iterator().next()).getId());	
							
							//Guardo el registro del remitente en la base
							SessionServiceLocator.getUsuarioNoticiasSessionService().saveUsuarioNoticias(dataUsuarioNoticias);
							
							//SesionesGeneral.getNoticiasSessionService().addNoticias(dataNoticias);
							SpiritAlert.createAlert("Noticia actualizada con \u00e9xito",SpiritAlert.INFORMATION);
							SpiritCache.setObject("noticias",null);
							clean();	
							showSaveMode();	
						} catch (GenericBusinessException e) {
							e.printStackTrace();
							SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
						} catch (Exception e) {
							e.printStackTrace();
							SpiritAlert.createAlert("Error al actualizar la noticia", SpiritAlert.ERROR);
						}
					} 
				}
				
				private ArrayList getModel(ArrayList listaNoticias) {
					ArrayList data = new ArrayList();
					Iterator it = listaNoticias.iterator();
					
					while (it.hasNext()) {
						ArrayList fila = new ArrayList();
						NoticiasIf bean = (NoticiasIf) it.next();
						
						fila.add(bean.getAsunto());
						fila.add(bean.getFechaIni());
						fila.add(bean.getFechaFin());
						data.add(fila);
					}
					return data;
				}
				
				private Map buildQuery() {
					Map aMap = new HashMap();
					if (!("".equals(getTxtAsunto().getText()))) {
						aMap.put("asunto", getTxtAsunto().getText().toUpperCase());
					}
					if (aMap.isEmpty() == true){
						String buscarTodos = "%";
						aMap.put("asunto", buscarTodos);
					}
					return aMap;
				}
				
				public void find() {
					java.sql.Date fechaInicio = new java.sql.Date(getCmbFechaInicio().getDate().getTime());
					java.sql.Date fechaFin = new java.sql.Date(getCmbFechaFin().getDate().getTime());
					try {
						Long idUsuario = ((UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId();
						lista = (ArrayList) SessionServiceLocator.getNoticiasSessionService().findNoticiaByAsuntoByUsuarioAndByFechaInicioAndFechaFin(getTxtAsunto().getText(), idUsuario, fechaInicio, fechaFin);
						
						if (lista.size() != 0) {
							ArrayList headers = new ArrayList();
							
							headers.add("Asunto");
							headers.add("Fecha Inicio");
							headers.add("Fecha Fin");
							
							ArrayList data = getModel(lista);
							
							finderTable = new FinderTable(data, headers);
							
							finderTable.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									getObjectFromFinderTable(e);
								}
							});
							
							finderTable.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent e) {
									isFinderTableVisible = false;
								}
							});
							
							isFinderTableVisible = true;
						} else {
							SpiritAlert.createAlert("No se encontraron registros",
									SpiritAlert.WARNING);
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Error en la búsqueda de informacin",
								SpiritAlert.ERROR);
					}
				}
				
				public void clean() {
					getTxtAsunto().setText("");
					getTxtNoticia().setText("");
					getTxtArchivo().setText("");
					//cargarTodosUsuarios();
				}
				
				public void showFindMode() {
					getCmbFechaInicio().setBackground(Parametros.findColor);
					getCmbFechaFin().setBackground(Parametros.findColor);
					getTxtAsunto().setBackground(Parametros.findColor);
					getCmbFechaFin().setEnabled(true);
					getCmbFechaInicio().setEnabled(true);
					getCmbStatus().setEnabled(false);
					getTxtAsunto().setText("");
					getTxtAsunto().setEnabled(true);
					getTxtAsunto().setEditable(true);
					getTxtNoticia().setText("");
					getTxtArchivo().setText("");
					getTxtNoticia().setEnabled(false);
					getTxtArchivo().setEnabled(false);
					getListDestinatarios().setEnabled(false);
					getListUsuarios().setEnabled(false);
					getBtnElegir().setEnabled(false);
					getBtnQuitar().setEnabled(false);
					getBtnTodos().setEnabled(false);
					getBtnArchivo().setEnabled(false);
				}
				
				public void showSaveMode() {
					setSaveMode();
					getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
					getCmbFechaFin().setBackground(Parametros.saveUpdateColor);
					getTxtAsunto().setBackground(Parametros.saveUpdateColor);
					isFinderTableVisible = false;
					getTxtUsuario().setEditable(false);
					getTxtArchivo().setEditable(false);
					getTxtAsunto().setEditable(true);
					getCmbFechaFin().setEnabled(true);
					getCmbFechaInicio().setEnabled(true);
					getCmbFechaFin().setEditable(false);
					getCmbFechaInicio().setEditable(false);
					getCmbStatus().setEnabled(true);
					getTxtAsunto().setText("");
					getTxtNoticia().setText("");
					getTxtArchivo().setText("");
					getTxtNoticia().setEnabled(true);
					getTxtArchivo().setEnabled(true);
					getListDestinatarios().setEnabled(true);
					getListUsuarios().setEnabled(true);
					getBtnElegir().setEnabled(true);
					getBtnQuitar().setEnabled(true);
					getBtnTodos().setEnabled(true);
					getBtnArchivo().setEnabled(true);
					
					getTxtNoticia().setEditable(true);
					getTxtArchivo().setEditable(false);
					usuariosMap.clear();
					destinatariosMap.clear();
					getListDestinatarios().clearSelection();
					DefaultListModel modelListDestinatarios = new DefaultListModel();
					getListDestinatarios().setModel(modelListDestinatarios);
					getListUsuarios().clearSelection();
					DefaultListModel modelListUsuarios = new DefaultListModel();
					getListUsuarios().setModel(modelListUsuarios);
					cargarTodosUsuarios();
					
					fechaHoy = new java.util.Date();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(fechaHoy);
					
					//Seteo el formato de los combos de fechas
					getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
					getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
					
					//Inicializo los comboDate con la fecha de Hoy
					getCmbFechaInicio().setCalendar(calendar);
					getCmbFechaFin().setCalendar(calendar);
					
					getTxtAsunto().grabFocus();
				}
				
				public void showUpdateMode() {
					setUpdateMode();
					Date fechaActual = new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate());
					if(noticias.getFechaIni().before(fechaActual) &&  noticias.getFechaFin().before(fechaActual)){
						
						getCmbFechaInicio().setBackground(getBackground());
						getCmbFechaFin().setBackground(getBackground());
						getTxtAsunto().setBackground(getBackground());
						
						getCmbFechaFin().setEnabled(false);
						getCmbFechaInicio().setEnabled(false);
						getCmbStatus().setEnabled(false);
						getTxtUsuario().setEditable(false);
						getTxtAsunto().setEditable(false);
						getTxtNoticia().setEnabled(true);
						getTxtNoticia().setEditable(false);
						getTxtArchivo().setEnabled(true);
						getTxtArchivo().setEditable(false);
						getBtnArchivo().setEnabled(false);
						getListUsuarios().setEnabled(false);
						getListDestinatarios().setEnabled(false);
						getBtnElegir().setEnabled(false);
						getBtnQuitar().setEnabled(false);
						getBtnTodos().setEnabled(false);
						
						getTxtNoticia().grabFocus();
					}
					else if((noticias.getFechaIni().before(fechaActual) || noticias.getFechaIni().equals(fechaActual)) &&  (noticias.getFechaFin().after(fechaActual) || noticias.getFechaFin().equals(fechaActual))){
						
						getCmbFechaInicio().setBackground(getBackground());
						getCmbFechaFin().setBackground(getBackground());
						getTxtAsunto().setBackground(getBackground());
						
						getCmbFechaFin().setEnabled(false);
						getCmbFechaInicio().setEnabled(false);
						getCmbStatus().setEnabled(true);
						getTxtUsuario().setEditable(false);
						getTxtAsunto().setEditable(false);
						getTxtNoticia().setEnabled(true);
						getTxtNoticia().setEditable(false);
						getTxtArchivo().setEnabled(true);
						getTxtArchivo().setEditable(false);
						getBtnArchivo().setEnabled(false);
						getListUsuarios().setEnabled(true);
						getListDestinatarios().setEnabled(true);
						getBtnElegir().setEnabled(true);
						getBtnQuitar().setEnabled(true);
						getBtnTodos().setEnabled(true);
						
						getCmbStatus().grabFocus();
					}
					else if(noticias.getFechaIni().after(fechaActual) &&  noticias.getFechaFin().after(fechaActual)){
						
						getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
						getCmbFechaFin().setBackground(Parametros.saveUpdateColor);
						getTxtAsunto().setBackground(Parametros.saveUpdateColor);
						
						getCmbFechaFin().setEnabled(true);
						getCmbFechaInicio().setEnabled(true);
						getCmbStatus().setEnabled(true);
						getTxtUsuario().setEditable(false);
						getTxtAsunto().setEditable(true);
						getTxtNoticia().setEnabled(true);
						getTxtNoticia().setEditable(true);
						getTxtArchivo().setEnabled(true);
						getTxtArchivo().setEditable(false);
						getBtnArchivo().setEnabled(true);
						getListUsuarios().setEnabled(true);
						getListDestinatarios().setEnabled(true);
						getBtnElegir().setEnabled(true);
						getBtnQuitar().setEnabled(true);
						getBtnTodos().setEnabled(true);
						
						getTxtAsunto().grabFocus();
					}
				}
				
				public FinderTable getFinderTable() {
					return finderTable;
				}
				
				public void setFinderTable(FinderTable table) {
					this.finderTable = table;
				}
				
				public ArrayList find(int startIndex, int endIndex) {
					ArrayList listaIndex;
					try {
						listaIndex = (ArrayList) SessionServiceLocator.getNoticiasSessionService().getNoticiasList(startIndex, endIndex);// ,"D%"
						System.out.println("lista index" + listaIndex);
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
						return null;
					}
					return listaIndex;
				}
				
				public void getObjectFromFinderTable(MouseEvent e) {
					try {
						if (e.getClickCount() == 2) {
							isFinderTableVisible = false;
							
							int rowNumber = getFinderTable().getTable().getSelectedRow();
							getFinderTable().dispose();
							setUpdateMode();
							noticias = (NoticiasIf) lista.get(rowNumber);
							
							//Seteo el model del List View de los Roles Seleccionados en blanco
							getListDestinatarios().setModel(new DefaultListModel());
							DefaultListModel modelListaUsuarios = new DefaultListModel();
							
							//Creo la coleccion con los usuarios segun la empresa
							Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(Parametros.getIdEmpresa());
							Iterator usuariosIt = usuarios.iterator();
							
							//Creo instancia de cada uno de los usuarios
							while (usuariosIt.hasNext()) {
								UsuarioIf usuarioIf = (UsuarioIf) usuariosIt.next();
								
								//Presente primer nombre y apellido de cada usuario de la empresa
								String destinatario = usuarioIf.getUsuario();
								if(!destinatario.equals(Parametros.getUsuario().toLowerCase()) ){ //&& usuarioIf.getId().compareTo(usuarioNoticiasIf.getUsuarioId()) != 0
									Iterator nombreEmpleadosIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaIdAndByUsuario(Parametros.getIdEmpresa(), destinatario).iterator();
									
									if (nombreEmpleadosIt.hasNext()) {
										EmpleadoIf nombreEmpleados = (EmpleadoIf) nombreEmpleadosIt.next();
										
										//if(modelListaUsuarios.contains(nombreEmpleados))
										modelListaUsuarios.addElement(nombreEmpleados);				
										
										//Añado los usuarios al mapa
										usuariosMap.put(usuarioIf.toString(),usuarioIf);
									}
								}
							}
							
							//Añado el modelo con los items seleccionados a la lista
							getListUsuarios().setModel(modelListaUsuarios);
							
							//Obtengo el model del List View de todos los roles
							DefaultListModel modelListUsuarios = (DefaultListModel) getListUsuarios().getModel();
							//Obtengo el model del List View de los roles seleccionados
							DefaultListModel modelListDestinatarios = (DefaultListModel) getListDestinatarios().getModel();
							
							//LLeno todos los campos con la info de la Noticias escojida
							getCmbFechaInicio().setDate(noticias.getFechaIni());
							getCmbFechaFin().setDate(noticias.getFechaFin());
							if (noticias.getStatus().equals(ESTADO_ACTIVO)) getCmbStatus().setSelectedIndex(0);
							else getCmbStatus().setSelectedIndex(1);
							getTxtAsunto().setText(noticias.getAsunto());
							getTxtNoticia().setText(noticias.getNoticia());
							if (noticias.getArchivo() != null && !noticias.getArchivo().equals("")) getTxtArchivo().setText(noticias.getArchivo());
							
							JList destinatarios = new JList();
							destinatarios.setModel(new DefaultListModel());
							DefaultListModel modelListDestinos = new DefaultListModel();
							
							Collection destinos = SessionServiceLocator.getUsuarioNoticiasSessionService().findUsuarioNoticiasByNoticiasId(noticias.getId());
							Iterator destinosIt = destinos.iterator();
							
							while(destinosIt.hasNext()){
								UsuarioNoticiasIf usuarioNoticiasIf = (UsuarioNoticiasIf) destinosIt.next();
								
								//Obtengo cada uno de los usuarios destinos
								UsuarioIf usuariosD = (UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioById(usuarioNoticiasIf.getUsuarioId()).iterator().next();
								
								//Presente primer nombre y apellido de cada usuario de la empresa
								String destinatario = usuariosD.getUsuario();
								if(!destinatario.equals(Parametros.getUsuario().toLowerCase())){
									nombreDestinatarios = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaIdAndByUsuario(Parametros.getIdEmpresa(), destinatario).iterator().next();
									modelListDestinos.addElement(nombreDestinatarios);
								}
							}
							//Añado el modelo con los items seleccionados a la lista
							destinatarios.setModel(modelListDestinos);
							
							//Obtengo todos los usuarios seleccionados del List View Origen
							Object[] usuariosSeleccionados =  new Object[destinatarios.getModel().getSize()];
							
							for(int j=0;j < destinatarios.getModel().getSize(); j++){
								usuariosSeleccionados[j] = destinatarios.getModel().getElementAt(j);
							}
							
							//Recorro uno a uno los usuarios que fueron seleccionados del List View Origen
							for(int i=0;i < usuariosSeleccionados.length; i++){
								//Creo una instancia del elemento seleccionado i
								UsuarioIf users = (UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(((EmpleadoIf)usuariosSeleccionados[i]).getId()).iterator().next();
								EmpleadoIf usuarioSeleccionado = (EmpleadoIf) usuariosSeleccionados[i];
								
								for(int j=0;j < modelListUsuarios.getSize(); j++){
									//Remueve el elemento del listview y del mapa de origen
									if(modelListUsuarios.getElementAt(j).toString().trim().compareTo(usuariosSeleccionados[i].toString().trim()) == 0){
										modelListUsuarios.removeElementAt(j);
									}
								}
								usuariosMap.remove(users.toString());
								//Agrego el elemento en el listview y en el mapa de destino
								modelListDestinatarios.addElement(usuariosSeleccionados[i]);
								destinatariosMap.put(usuarioSeleccionado.toString(),usuarioSeleccionado);
							}
							this.showUpdateMode();
						}
					} catch (GenericBusinessException e1) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e1.printStackTrace();
					}
				}
				
				public boolean isEmpty() {
					return false;
				}
				
			 
}