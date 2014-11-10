package com.spirit.general.gui.controller;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.ArrayListTableModel;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;

public class JDPopupFinderModel extends JDPopupFinder {
	private static final long serialVersionUID = 1457722870943372998L;
	public static final int BUSQUEDA_POR_PARAMETROS = 1;
	public static final int BUSQUEDA_TODOS = 2;
	private static final int MAX_LONGITUD_PARAMETRO1 = 15;
	private static final int MAX_LONGITUD_PARAMETRO2 = 50;
    ArrayList data;
	ArrayList headers;
	Criteria criteria;
	String accion;
	JOptionPane optionPane;
	int filaSeleccionada=-1;
	Object elementoSeleccionado=null;
	int paginaActual;
	int numeroPaginas=0;
	int base;
	boolean busquedaPorParametros=true;
	boolean aceptar=true;
	Vector<Integer> anchoColumnas = new Vector<Integer>();
	Map alineacionColumnas = new HashMap();
	
	private JComboBox cmbParametro3 = new JComboBox();
	private JComboBox cmbParametro4 = new JComboBox();
	private JComboBox cmbParametro5 = new JComboBox();
	private JComboBox cmbParametro6 = new JComboBox();
	private boolean activaSeleccion;
	
	public JDPopupFinderModel(Frame frame, Criteria criteria, int pantallaInicial) {
		super(frame);
    	this.criteria = criteria;
		paginaActual = 0;
		initListener();
		iniciarComponentes();
		getPanelContenido().add(getPanelPorParametros(), new CellConstraints().xy(1, 1));
		this.pack();
        setModal(true);
        
        if (pantallaInicial == BUSQUEDA_TODOS) {
        	busquedaPorParametros = false;
        	initBotones();
        	buscarTodos();
        	if ( criteria.getNombrePanel()!=null || !"".equals(criteria.getNombrePanel()) )
        		setTitle( "B\u00fasqueda " + criteria.getNombrePanel() );
        	else
        		setTitle( "B\u00fasqueda" );
        	
        	/*if (criteria.getResultListSize()%Parametros.getNumeroElementosPorPagina() == 0) {
        		numeroPaginas--;
        	}*/
        	calcularNumeroPaginas(criteria.getResultListSize());
        	presentarLabelPaginas(true);
        } else {
        	aceptar = false;
        	initBotones();
        	presentarBusquedaPorParametros();
        	setTitle("B\u00fasqueda " + criteria.getNombrePanel());
        	esconderLabelsTextFields();
        	mostrarLabelsTextFields((ArrayList)criteria.getHeaders());
        	presentarLabelPaginas(false);
        }
        
        getTblResultados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        validateButtons();
        getLblPaginaActual().setText(String.valueOf(paginaActual+1));
        setVisual();
	}
	
	public JDPopupFinderModel(Frame frame, Criteria criteria, int pantallaInicial, Vector<Integer> anchoColumnas, Map alineacionColumnas) {
		super(frame);
    	this.criteria = criteria;
		paginaActual = 0;
		this.anchoColumnas = anchoColumnas;
		this.alineacionColumnas = alineacionColumnas;
		initListener();
		iniciarComponentes();
		
		getPanelContenido().add(getPanelPorParametros(), new CellConstraints().xy(1, 1));
		
        this.pack();
        setModal(true);
        
        if (pantallaInicial == BUSQUEDA_TODOS) {
        	busquedaPorParametros = false;
        	initBotones();
        	buscarTodos();
            setAnchoColumnas();
            String titulo = "B\u00fasqueda ";
            if( criteria.getNombrePanel()!=null && !"".equals(criteria.getNombrePanel()) )
            	titulo += criteria.getNombrePanel();
        	setTitle(titulo);
        	numeroPaginas = criteria.getResultListSize()/Parametros.getNumeroElementosPorPagina();
        	
        	/*if (criteria.getResultListSize()%Parametros.getNumeroElementosPorPagina() == 0) {
        		numeroPaginas--;
        	}*/
        	calcularNumeroPaginas(criteria.getResultListSize());
        	presentarLabelPaginas(true);
        } else {
        	aceptar = false;
        	initBotones();
        	presentarBusquedaPorParametros();
        	setTitle("B\u00fasqueda "+criteria.getNombrePanel());
        	esconderLabelsTextFields();
        	mostrarLabelsTextFields((ArrayList)criteria.getHeaders());
        	presentarLabelPaginas(false);
        }
        
        getTblResultados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        validateButtons();
        getLblPaginaActual().setText(String.valueOf(paginaActual+1));
        setVisual();
	}
	
	private void iniciarComponentes(){
		getBtnBuscar().setText("");
		getBtnBuscar().setToolTipText("Buscar");
		getBtnBuscar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/showTable.png"));
		
		getBtnAceptar().setText("");
		getBtnAceptar().setToolTipText("Aceptar");
		getBtnAceptar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/selectElement.png"));
		
		getBtnIrPrimerosRegistros().setText("");
		getBtnIrPrimerosRegistros().setToolTipText("Ver primeros " + Parametros.getNumeroElementosPorPagina() + " registros");
		getBtnIrPrimerosRegistros().setBackground(UIManager.getColor("Button.light"));
		getBtnIrPrimerosRegistros().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goFirstRecords.png"));
		
		getBtnIrAnteriorRegistro().setText("");
		getBtnIrAnteriorRegistro().setToolTipText("Ver" + Parametros.getNumeroElementosPorPagina() + " registros previos");
		getBtnIrAnteriorRegistro().setOpaque(false);
		getBtnIrAnteriorRegistro().setBackground(UIManager.getColor("Button.light"));
		getBtnIrAnteriorRegistro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goPreviousRecord.png"));
		
		getBtnIrSiguienteRegistro().setText("");
		getBtnIrSiguienteRegistro().setToolTipText("Ver siguientes " + Parametros.getNumeroElementosPorPagina()+" registros");
		getBtnIrSiguienteRegistro().setOpaque(false);
		getBtnIrSiguienteRegistro().setBackground(UIManager.getColor("Button.light"));
		getBtnIrSiguienteRegistro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goNextRecord.png"));
		
		getBtnIrUltimosRegistros().setText("");
		getBtnIrUltimosRegistros().setToolTipText("Mostrar ultimos " + Parametros.getNumeroElementosPorPagina() + " Registros");
		getBtnIrUltimosRegistros().setOpaque(false);
		getBtnIrUltimosRegistros().setBackground(UIManager.getColor("Button.light"));
		getBtnIrUltimosRegistros().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goLastRecords.png"));
		
		getLblMensajePaginaA().setText("P\u00e1gina: ");
		getLblPaginaActual().setText("0");
		getLblPaginaActual().setFont(new Font("Tahoma", Font.BOLD, 11));
		getLblMensajePaginaB().setText(" de ");
		getLblPaginaFinal().setText("0");
		getLblPaginaFinal().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		getBtnParametro3().setText("");
		getBtnParametro3().setToolTipText("Buscar");
		getBtnParametro3().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnParametro4().setText("");
		getBtnParametro4().setToolTipText("Buscar");
		getBtnParametro4().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnParametro5().setText("");
		getBtnParametro5().setToolTipText("Buscar");
		getBtnParametro5().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnParametro6().setText("");
		getBtnParametro6().setToolTipText("Buscar");
		getBtnParametro6().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getCmbParametro3().setVisible(false);
		getCmbParametro4().setVisible(false);
		getCmbParametro5().setVisible(false);
		getCmbParametro6().setVisible(false);
		
		CellConstraints cc = new CellConstraints();
		getPanelPorParametros().add(getCmbParametro3(), cc.xywh(5, 7, 5, 1));
		getPanelPorParametros().add(getCmbParametro4(), cc.xywh(5, 9, 5, 1));
		getPanelPorParametros().add(getCmbParametro5(), cc.xywh(5, 11, 5, 1));
		getPanelPorParametros().add(getCmbParametro6(), cc.xywh(5, 11, 5, 1));
	}

	private void setAnchoColumnas() {
		int numeroColumnas = criteria.getHeaders().size();
		TableColumn anchoColumna = null;
        for (int i = 0; i<numeroColumnas; i++) {
        	anchoColumna = getTblResultados().getColumnModel().getColumn(i);
			anchoColumna.setPreferredWidth(this.anchoColumnas.get(i));
		}
	}
	
	private void setAlineacionColumnas() {
		int numeroColumnas = criteria.getHeaders().size();
		Iterator it = this.alineacionColumnas.keySet().iterator();
		while (it.hasNext()) {
			Integer columna = (Integer) it.next();
			if (columna < numeroColumnas) {
				Integer alineacion = (Integer) this.alineacionColumnas.get((Object) columna);
				if (alineacion.intValue() == SwingConstants.RIGHT) {
					TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
					getTblResultados().getColumnModel().getColumn(columna.intValue()).setCellRenderer(tableCellRenderer);
				} else if (alineacion.intValue() == SwingConstants.CENTER) {
					TableCellRendererHorizontalCenterAlignment tableCellRenderer = new TableCellRendererHorizontalCenterAlignment();
					getTblResultados().getColumnModel().getColumn(columna.intValue()).setCellRenderer(tableCellRenderer);
				}
			}
		}
	}
	
	private void setVisual(){
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width/6), //- getWidth()) / 6, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
		setVisible(true);
        getTblResultados().revalidate();
        repaint();
	}
	
	private void initBotones() {
		getBtnBuscar().setEnabled(busquedaPorParametros);
		getBtnAceptar().setEnabled(aceptar);
		getBtnIrAnteriorRegistro().setEnabled(false);
		getBtnIrPrimerosRegistros().setEnabled(false);
		getBtnIrSiguienteRegistro().setEnabled(false);
		getBtnIrUltimosRegistros().setEnabled(false);
	}
    
    private void initListener() {
    	//BOTONES
    	getBtnAceptar().setName("Aceptar");
    	getBtnAceptar().addActionListener(actionGeneral);
    	getBtnAceptar().addKeyListener(keyEventNavigatorManager());
    	setBtnAceptarVkEnterCustomBehavior();
    	getBtnBuscar().setName("Buscar");
    	getBtnBuscar().addActionListener(actionGeneral);
    	getBtnBuscar().addKeyListener(keyEventNavigatorManager());
    	setBtnBuscarVkEnterCustomBehavior();
    	getBtnIrSiguienteRegistro().setName("IrSiguienteRegistro");
    	getBtnIrSiguienteRegistro().addActionListener(actionGeneral);
    	getBtnIrSiguienteRegistro().addKeyListener(keyEventNavigatorManager());
    	setBtnIrSiguienteRegistroVkEnterCustomBehavior();
    	getBtnIrAnteriorRegistro().setName("IrAnteriorRegistro");
    	getBtnIrAnteriorRegistro().addActionListener(actionGeneral);
    	getBtnIrAnteriorRegistro().addKeyListener(keyEventNavigatorManager());
    	setBtnIrAnteriorRegistroVkEnterCustomBehavior();
    	getBtnIrPrimerosRegistros().setName("IrPrimerosRegistros");
    	getBtnIrPrimerosRegistros().addActionListener(actionGeneral);
    	getBtnIrPrimerosRegistros().addKeyListener(keyEventNavigatorManager());
    	setBtnIrPrimerosRegistrosVkEnterCustomBehavior();
    	getBtnIrUltimosRegistros().setName("IrUltimosRegistros");
    	getBtnIrUltimosRegistros().addActionListener(actionGeneral);
    	getBtnIrUltimosRegistros().addKeyListener(keyEventNavigatorManager());
    	setBtnIrUltimosRegistrosVkEnterCustomBehavior();
    	//TEXTFIELDS
    	getTxtParametro1().setName("Parametro1");
    	getTxtParametro1().addActionListener(actionGeneral);
    	getTxtParametro1().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO1));
    	getTxtParametro2().setName("Parametro2");
    	getTxtParametro2().addActionListener(actionGeneral);
    	getTxtParametro2().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO2));
    	getTxtParametro3().setName("Parametro3");
    	getTxtParametro3().addActionListener(actionGeneral);
    	getTxtParametro3().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO2));
    	getTxtParametro4().setName("Parametro4");
    	getTxtParametro4().addActionListener(actionGeneral);
    	getTxtParametro4().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO2));
    	getTxtParametro5().setName("Parametro5");
    	getTxtParametro5().addActionListener(actionGeneral);
    	getTxtParametro5().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO2));
    	getTxtParametro6().setName("Parametro6");
    	getTxtParametro6().addActionListener(actionGeneral);
    	getTxtParametro6().addKeyListener(new TextChecker(MAX_LONGITUD_PARAMETRO2));
    	//TABLA DE RESULTADOS
    	initTblResultadosListeners();
    }

	private void initTblResultadosListeners() {
		getTblResultados().addMouseListener(mouseTableListener);
    	initTblResultadosToolTipMouseListener();
    	initTblResultadosVkEnterKeyListener();
    	setTblResultadosVkEnterCustomBehavior();
    	setTblResultadosVkUpCustomBehavior();
    	setTblResultadosVkDownCustomBehavior();
    	setTblResultadosVkRightCustomBehavior();
    	setTblResultadosVkLeftCustomBehavior();
	}

	private void initTblResultadosVkEnterKeyListener() {
		getTblResultados().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER && isActivaSeleccion()) {
					filaSeleccionada = getTblResultados().getSelectedRow();
					elementoSeleccionado = (Object) criteria.getListaResultados().get(filaSeleccionada);
					dispose();
				}
			}
		});
	}
	
	private void setTblResultadosVkRightCustomBehavior() {
		getTblResultados().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "navigatePagesRight");
		getTblResultados().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "navigatePagesRight");
		getTblResultados().getActionMap().put("navigatePagesRight", new AbstractAction() {
			public void actionPerformed(ActionEvent ae) {
				if (!getTblResultados().hasFocus())
					getTblResultados().grabFocus();
				if (getBtnIrSiguienteRegistro().isEnabled()) {
					int selectedRow = getTblResultados().getSelectedRow();
					int selectedPage = paginaActual;
					llenarPaginaTablaResultadosTodos(criteria.next(selectedPage));
					paginaActual+=1;
					filaSeleccionada = selectedRow;
					validateButtons();
					getLblPaginaActual().setText(String.valueOf(paginaActual+1));
					getTblResultados().setRowSelectionInterval(selectedRow, selectedRow);
				}
			}
		});
	}
	
	private void setTblResultadosVkLeftCustomBehavior() {
		getTblResultados().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "navigatePagesLeft");
		getTblResultados().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "navigatePagesLeft");
		getTblResultados().getActionMap().put("navigatePagesLeft", new AbstractAction() {
			public void actionPerformed(ActionEvent ae) {
				if (!getTblResultados().hasFocus())
					getTblResultados().grabFocus();
				if (getBtnIrAnteriorRegistro().isEnabled()) {
					int selectedRow = getTblResultados().getSelectedRow();
					int selectedPage = paginaActual; 
					llenarPaginaTablaResultadosTodos(criteria.previous(selectedPage) );
					paginaActual-=1;
					filaSeleccionada = selectedRow;
					validateButtons();
					getLblPaginaActual().setText(String.valueOf(paginaActual+1));
					getTblResultados().setRowSelectionInterval(selectedRow, selectedRow);
				}
			}
		});
	}

	private void setTblResultadosVkDownCustomBehavior() {
		getTblResultados().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "navigateItemsDown");
    	getTblResultados().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "navigateItemsDown");
    	getTblResultados().getActionMap().put("navigateItemsDown", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			if (!getTblResultados().hasFocus())
    				getTblResultados().grabFocus();
    			int selectedRow = getTblResultados().getSelectedRow();
    			if (selectedRow <= -1)
    				selectedRow = 0;
    			//else if (selectedRow < Parametros.getNumeroElementosPorPagina() - 1)
    			else if (selectedRow < getTblResultados().getRowCount() - 1)
    				selectedRow++;
    			else
    				selectedRow = 0;
    			getTblResultados().setRowSelectionInterval(selectedRow, selectedRow);
    		}
    	});
	}

	private void setTblResultadosVkUpCustomBehavior() {
		getTblResultados().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "navigateItemsUp");
    	getTblResultados().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "navigateItemsUp");
    	getTblResultados().getActionMap().put("navigateItemsUp", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			if (!getTblResultados().hasFocus())
    				getTblResultados().grabFocus();
    			int selectedRow = getTblResultados().getSelectedRow();
				if (selectedRow <= -1)
					//selectedRow = Parametros.getNumeroElementosPorPagina() - 1;
					selectedRow = getTblResultados().getRowCount() - 1;
				else if (selectedRow > 0)
					selectedRow--;
				else
					//selectedRow = Parametros.getNumeroElementosPorPagina() - 1;
					selectedRow = getTblResultados().getRowCount() - 1;
				getTblResultados().setRowSelectionInterval(selectedRow, selectedRow);
    		}
    	});
	}

	private void setTblResultadosVkEnterCustomBehavior() {
		getTblResultados().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItem");
    	getTblResultados().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItem");
    	getTblResultados().getActionMap().put("selectItem", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnAceptarVkEnterCustomBehavior() {
		getBtnAceptar().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnAceptarVkEnterCustomBehavior");
		getBtnAceptar().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnAceptarVkEnterCustomBehavior");
		getBtnAceptar().getActionMap().put("btnAceptarVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnBuscarVkEnterCustomBehavior() {
		getBtnBuscar().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnBuscarVkEnterCustomBehavior");
		getBtnBuscar().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnBuscarVkEnterCustomBehavior");
		getBtnBuscar().getActionMap().put("btnBuscarVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnIrSiguienteRegistroVkEnterCustomBehavior() {
		getBtnIrSiguienteRegistro().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrSiguienteRegistroVkEnterCustomBehavior");
		getBtnIrSiguienteRegistro().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrSiguienteRegistroVkEnterCustomBehavior");
		getBtnIrSiguienteRegistro().getActionMap().put("btnIrSiguienteRegistroVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnIrAnteriorRegistroVkEnterCustomBehavior() {
		getBtnIrAnteriorRegistro().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrAnteriorRegistroVkEnterCustomBehavior");
		getBtnIrAnteriorRegistro().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrAnteriorRegistroVkEnterCustomBehavior");
		getBtnIrAnteriorRegistro().getActionMap().put("btnIrAnteriorRegistroVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnIrPrimerosRegistrosVkEnterCustomBehavior() {
		getBtnIrPrimerosRegistros().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrPrimerosRegistrosVkEnterCustomBehavior");
		getBtnIrPrimerosRegistros().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrPrimerosRegistrosVkEnterCustomBehavior");
		getBtnIrPrimerosRegistros().getActionMap().put("btnIrPrimerosRegistrosVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void setBtnIrUltimosRegistrosVkEnterCustomBehavior() {
		getBtnIrUltimosRegistros().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrUltimosRegistrosVkEnterCustomBehavior");
		getBtnIrUltimosRegistros().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "btnIrUltimosRegistrosVkEnterCustomBehavior");
		getBtnIrUltimosRegistros().getActionMap().put("btnIrUltimosRegistrosVkEnterCustomBehavior", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de resultados.
    			setActivaSeleccion(true);
    		}
    	});
	}
	
	private void initTblResultadosToolTipMouseListener() {
		getTblResultados().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint(); 
				int row = getTblResultados().rowAtPoint(p);
				int column = getTblResultados().columnAtPoint(p);
				String toolTipText = getTblResultados().getValueAt(row,column) != null?String.valueOf(getTblResultados().getValueAt(row,column)).replaceAll(SpiritConstants.getSemicolonCharacter(), "<br>"):SpiritConstants.getEmptyCharacter();
				getTblResultados().setToolTipText(SpiritConstants.getBeginningHtmlTag() + toolTipText + SpiritConstants.getEndingHtmlTag());
			}
		});
	}
    
    private KeyListener keyEventNavigatorManager () {
    	return (new KeyAdapter() {
    		int selectedRow = getTblResultados().getSelectedRow();
    		public void keyReleased(KeyEvent e) {
    			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
    				if (!getTblResultados().hasFocus())
    					getTblResultados().grabFocus();
    				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    					if (selectedRow <= -1)
    						selectedRow = 0;
    					else if (selectedRow < Parametros.getNumeroElementosPorPagina() - 1)
    						selectedRow++;
    					else
    						selectedRow = 0;
    				}
    				if (e.getKeyCode() == KeyEvent.VK_UP) {
    					if (selectedRow <= -1)
    						selectedRow = Parametros.getNumeroElementosPorPagina() - 1;
    					else if (selectedRow > 0)
    						selectedRow--;
    					else
    						selectedRow = Parametros.getNumeroElementosPorPagina() - 1;

    				}
    				getTblResultados().setRowSelectionInterval(selectedRow, selectedRow);
    			}
    		}
    	});
    }
    
    private void esconderLabelsTextFields(){
    	getLblParametro1().setVisible(false);
    	getLblParametro2().setVisible(false);
    	getLblParametro3().setVisible(false);
    	getLblParametro4().setVisible(false);
    	getLblParametro5().setVisible(false);
    	getLblParametro6().setVisible(false);
    	
    	getTxtParametro1().setVisible(false);
    	getTxtParametro2().setVisible(false);
    	getTxtParametro3().setVisible(false);
    	getTxtParametro4().setVisible(false);
    	getTxtParametro5().setVisible(false);
    	getTxtParametro6().setVisible(false);
    	
    	getBtnParametro3().setVisible(false);
    	getBtnParametro4().setVisible(false);
    	getBtnParametro5().setVisible(false);
    	getBtnParametro6().setVisible(false);
    	
    	getCmbParametro3().setVisible(false);
    	getCmbParametro4().setVisible(false);
    	getCmbParametro5().setVisible(false);
    	getCmbParametro6().setVisible(false);
    }
    
    private void mostrarLabelsTextFields(ArrayList headers){
    	for (int i=0 ;i<headers.size() ;i++) {
    		Object objeto = headers.get(i);
    		if (i==0) {
    			if ( objeto instanceof String )
    				getLblParametro1().setText((String)objeto);
    			getLblParametro1().setVisible(true);
    			getTxtParametro1().setVisible(true);
    		}else if ( i==1 ) {
    			if ( objeto instanceof String )
    				getLblParametro2().setText((String)objeto);
    			getLblParametro2().setVisible(true);
    			getTxtParametro2().setVisible(true);
    		} else if ( i==2 ) {
    			if ( objeto instanceof String ){
    				getLblParametro3().setText((String)objeto);
					getLblParametro3().setVisible(true);
	    			getTxtParametro3().setVisible(true);
	    			getTxtParametro3().setEditable(true);
    			}
    			else if ( objeto instanceof Object[] ){
    				Object[] objetos = (Object[]) objeto;
    				if ( objetos.length >= 2 ){
	    				if ( objetos[1] instanceof ActionListener ){
	    					setComportamientoBoton(objetos,getLblParametro3(),getTxtParametro3(), getBtnParametro3());
	    				} else if ( objetos[1] instanceof Object[]){
	    					setComportamientoCombo(objetos,getLblParametro3(), getTxtParametro3(),getBtnParametro3(),getCmbParametro3());
	    				}
    				}
    			} 
    		} else if ( i==3 ) {
    			if ( objeto instanceof Object[] ){
    				Object[] objetos = (Object[]) objeto;
    				if ( objetos.length >= 2 ){
        	    		if ( objetos[1] instanceof ActionListener){
	    					setComportamientoBoton(objetos, getLblParametro4(), getTxtParametro4(), getBtnParametro4());
	    				} else if ( objetos[1] instanceof Object[]){
	    					setComportamientoCombo(objetos, getLblParametro4(), getTxtParametro4(), getBtnParametro4(), getCmbParametro4());
	    				}
    				}
    			}	
    		} else if ( i==4 ) {
    			if ( objeto instanceof Object[] ){
    				Object[] objetos = (Object[]) objeto;
    				if ( objetos.length >= 2 ){
    	    			if ( objetos[1] instanceof ActionListener){
	    					setComportamientoBoton(objetos, getLblParametro5(), getTxtParametro5(), getBtnParametro5());
	    				}else if ( objetos[1] instanceof Object[]){
	    					setComportamientoCombo(objetos, getLblParametro5(), getTxtParametro5(), getBtnParametro5(), getCmbParametro5());
	    				}
    				}
    			}	
    		} else if ( i==5 ) {
    			if ( objeto instanceof Object[] ){
    				Object[] objetos = (Object[]) objeto;
    				if ( objetos.length >= 2 ){
	    				if ( objetos.length == 2 ){
	    					setComportamientoBoton(objetos, getLblParametro6(), getTxtParametro6(), getBtnParametro6());
	    				}else if ( objetos.length == 2 && objetos[1] instanceof Object[]){
	    					setComportamientoCombo(objetos, getLblParametro6(), getTxtParametro6(), getBtnParametro6(), getCmbParametro6());
	    				}
    				}
    			}
    		}
    	}	
    }

	private void setComportamientoCombo(Object[] objetos, JLabel lbl, JTextField txt, JButton btn, JComboBox cmb) {
		
		lbl.setText((String)objetos[0]);
		Object[] lista = (Object[]) objetos[1];
		cmb.setModel(new DefaultComboBoxModel(lista));
		
		if ( objetos.length == 3 ){
			if ( objetos[2] instanceof Integer ){
				Integer itemSeleccionado = (Integer) objetos[2];
				cmb.setSelectedIndex(itemSeleccionado);
			} else { 
				Object tipo = (Object) objetos[2];
				cmb.setSelectedItem(tipo);
			}
		}
		
			 
			
		lbl.setVisible(true);
		txt.setVisible(false);
		txt.setEditable(false);
		btn.setVisible(false);
		cmb.setVisible(true);
	}

	private void setComportamientoBoton(Object[] objetos, JLabel lbl, JTextField txt, JButton btn) {
		lbl.setText((String)objetos[0]);
		PopupFinderActionListener al = (PopupFinderActionListener)objetos[1];
		al.setTxtField(txt);
		btn.addActionListener(al);
		lbl.setVisible(true);
		txt.setVisible(true);
		txt.setEditable(false);
		btn.setVisible(true);
	}
    
    ActionListener actionGeneral = new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		Object object = e.getSource();
    		JTextField textField;
    		JButton button;

    		if ( object instanceof JTextField){
    			textField = (JTextField)object;
    			String nombreTextField = textField.getName();
    			if ( "Parametro1".equals(nombreTextField) ) {
    				buscarPorParametros();
    			} else if ("Parametro2".equals(nombreTextField)) {
    				buscarPorParametros();
    			} else if ("Parametro3".equals(nombreTextField)) {
    				buscarPorParametros();
    			} else if ("Parametro4".equals(nombreTextField)) {
    				buscarPorParametros();
    			} else if ("Parametro5".equals(nombreTextField)) {
    				buscarPorParametros();
    			} else if ("Parametro6".equals(nombreTextField)) {
    				buscarPorParametros();
    			}
    			setActivaSeleccion(false);
    		} else if ( object instanceof JButton ) {
    			button = (JButton)object;
    			String nombreBoton = button.getName();
    			if ( "Aceptar".equals(nombreBoton) ) {
    				if (filaSeleccionada >= 0) {
    					elementoSeleccionado = (Object) criteria.getListaResultados().get(filaSeleccionada);
    					//get(base+filaSeleccionada);
    					dispose();
    				} else
    					SpiritAlert.createAlert("Debe seleccionar una fila primero", SpiritAlert.WARNING);
    			} else if ("Buscar".equals(nombreBoton)){
    				if (busquedaPorParametros){
    					buscarPorParametros();
    					//presentarLabelPaginas(true);
    				}
    				else{
    					presentarBusquedaPorParametros();
    					//presentarLabelPaginas(false);
    				}

    			} else if ("IrSiguienteRegistro".equals(nombreBoton)){
    				llenarPaginaTablaResultadosTodos(criteria.next(paginaActual) );
    				paginaActual+=1;
    				filaSeleccionada = -1;
    				validateButtons();
    			} else if ("IrAnteriorRegistro".equals(nombreBoton)){
    				llenarPaginaTablaResultadosTodos(criteria.previous(paginaActual) );
    				paginaActual-=1;
    				filaSeleccionada = -1;
    				validateButtons();
    			} else if ("IrPrimerosRegistros".equals(nombreBoton)){
    				llenarPaginaTablaResultadosTodos(criteria.first() );
    				paginaActual=0;
    				filaSeleccionada = -1;
    				validateButtons();
    			} else if ("IrUltimosRegistros".equals(nombreBoton)){
    				llenarPaginaTablaResultadosTodos(criteria.last() );
    				paginaActual = numeroPaginas;
    				filaSeleccionada = -1;
    				validateButtons();
    			}

    			getLblPaginaActual().setText(String.valueOf(paginaActual+1));

    			/*if ( criteria.getSpiritModel()==null )
        			base = paginaActual*Parametros.getNumeroElementosPorPagina();
        		else
        			base = 0;*/
    			setActivaSeleccion(false);
    		} else if (object instanceof JTable) {
    			setActivaSeleccion(true);
    		}
    	}
	};
	
	MouseListener mouseTableListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e) {
			filaSeleccionada = ( (JTable)e.getComponent() ).getSelectedRow();
			if (e.getClickCount() == 2) {
				elementoSeleccionado = (Object) criteria.getListaResultados().get(filaSeleccionada);
				//get(base+filaSeleccionada);
				dispose();
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			filaSeleccionada = ( (JTable)e.getComponent() ).getSelectedRow();
		}
	};
	
	/*KeyListener keyTableListener = new KeyAdapter(){
		public void keyReleased(final KeyEvent e) {
			SwingUtilities.invokeLater( new Runnable() {
				public void run() {
					filaSeleccionada = ( (JTable)e.getComponent() ).getSelectedRow();
					if (  e.getKeyChar() == KeyEvent.VK_ENTER ){
						elementoSeleccionado = (Object) criteria.getListaResultados().get(filaSeleccionada);
						//get(base+filaSeleccionada);
						dispose();
					}              
				}
			});
		}
	};*/
    
    private void presentarLabelPaginas(boolean presentar){
    	getLblMensajePaginaA().setVisible(presentar);
    	getLblPaginaActual().setVisible(presentar);
    	getLblMensajePaginaB().setVisible(presentar);
    	getLblPaginaFinal().setVisible(presentar);
    }
    
    protected void presentarBusquedaTodos(){
    	getBtnBuscar().setToolTipText("Regresar Busqueda por Parametros");
    	cambiarBotonBuscar("showForm");
    	getBtnAceptar().setEnabled(aceptar);
    	getPanelPorParametros().setVisible(false);
    	getPanelTablaResultados().setVisible(true);
    	presentarLabelPaginas(true);
	}
        
    protected void presentarBusquedaPorParametros(){
    	paginaActual = 0;
    	numeroPaginas=0;
    	aceptar = false;
		busquedaPorParametros = true;
    	getBtnBuscar().setToolTipText("Buscar");
    	cambiarBotonBuscar("showTable");
    	getBtnAceptar().setEnabled(aceptar);
    	getPanelTablaResultados().setVisible(false);
    	getPanelPorParametros().setVisible(true);
    	getTxtParametro1().grabFocus();
    	validateButtons();
    	presentarLabelPaginas(false);
	}
    
    private void buscarPorParametros(){
    	//CUANDO SE BUSCA POR PARAMETROS DENTRO DE LA VENTANA DE BUSQUEDA
    	Map<String, Object> mapaParametros = new HashMap<String, Object>();
    	mapaParametros.put("parametro1", getTxtParametro1().getText());
    	if ( getTxtParametro2().isVisible() )
    		mapaParametros.put("parametro2", getTxtParametro2().getText());
    	if ( getTxtParametro3().isVisible() )
    		mapaParametros.put("parametro3", getTxtParametro3().getText());
    	else if ( getCmbParametro3().isVisible() )
    		mapaParametros.put("parametro3", getCmbParametro3().getSelectedItem());
    	if ( getTxtParametro4().isVisible() )
    		mapaParametros.put("parametro4", getTxtParametro4().getText());
    	else if ( getCmbParametro4().isVisible() )
    		mapaParametros.put("parametro4", getCmbParametro4().getSelectedItem());
    	if ( getTxtParametro5().isVisible() )
    		mapaParametros.put("parametro5", getTxtParametro5().getText());
    	else if ( getCmbParametro5().isVisible() )
    		mapaParametros.put("parametro5", getCmbParametro5().getSelectedItem());
    	if ( getTxtParametro6().isVisible() )
    		mapaParametros.put("parametro6", getTxtParametro6().getText());
    	else if ( getCmbParametro6().isVisible() )
    		mapaParametros.put("parametro6", getCmbParametro6().getSelectedItem());
    	criteria.setTxtParametros(mapaParametros);	
    	
    	criteria.setTxtParametros(getTxtParametro1().getText(), getTxtParametro2().getText(), getTxtParametro3().getText());
    	
    	int totalElementos = criteria.getResultListSize();
    	if ( totalElementos > 0 ){
    		
    		/*numeroPaginas = totalElementos/Parametros.getNumeroElementosPorPagina();
    		if (criteria.getResultListSize()%Parametros.getNumeroElementosPorPagina() == 0)
        		numeroPaginas--;*/
    		calcularNumeroPaginas(totalElementos);
    		
    		llenarTablaResultadosPorParametros();
    		aceptar = !aceptar;
    		busquedaPorParametros = !busquedaPorParametros;
    		presentarBusquedaTodos();
			paginaActual = 0;
			filaSeleccionada = -1;
			validateButtons();
			
			if ( getTblResultados().getRowCount() > 0 ){
	    		getTblResultados().setRowSelectionInterval(0, 0);
	    		getTblResultados().grabFocus();
	    		getTblResultados().requestFocus();
	    	}
			
    	} else
    		SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
    }
    private void llenarTablaResultadosPorParametros(){
    	ArrayList cabecera = criteria.getHeadersString()!=null?(ArrayList)criteria.getHeadersString():(ArrayList)criteria.getHeaders(); 
    	final TableModel dataModel = new ArrayListTableModel((ArrayList)criteria.pagina(0)
    			,cabecera);
    	getTblResultados().setModel(dataModel);
    	if (this.anchoColumnas.size() > 0){
    		if (this.anchoColumnas.size() == cabecera.size())
    			setAnchoColumnas();
    		/*else
    			SpiritAlert.createAlert("Establecer anchos de columnas correctamente !!" +
    				"\nHay "+cabecera.size()+" columnas pero estan establecidas para "+this.anchoColumnas.size(),
    				SpiritAlert.WARNING);*/
    	}
    	if (this.alineacionColumnas != null && this.alineacionColumnas.size() > 0  ){
    		if (this.alineacionColumnas.size() == cabecera.size())
    			setAlineacionColumnas();
    		
    	}
    	getTblResultados().validate();
    }
    
    private void buscarTodos(){
    	//SE PRESENTA DIRECTAMENTE EL RESULTADO DE LA BUSQUEDA
    	llenarPaginaTablaResultadosTodos(criteria.first());
    	presentarBusquedaTodos();
    }
    private void llenarPaginaTablaResultadosTodos(List paginaResultados){
    	final TableModel dataModel = new ArrayListTableModel((ArrayList)paginaResultados
    			, criteria.getHeadersString()!=null?(ArrayList)criteria.getHeadersString():(ArrayList)criteria.getHeaders());
    	getTblResultados().setModel(dataModel);
    	if (this.anchoColumnas.size() > 0)
    		setAnchoColumnas();
    	if (this.alineacionColumnas != null && this.alineacionColumnas.size() > 0)
    		setAlineacionColumnas();
    	getTblResultados().validate();
    }    
    
    private void cambiarBotonBuscar(String imagen){
    	try {
    		getBtnBuscar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/"+imagen+".png"));
    	} catch(Exception e){
    		SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
    		e.printStackTrace();
    	}
    }
	
	public Object getElementoSeleccionado() {
		return elementoSeleccionado;
	}
	
	private void calcularNumeroPaginas(int totalElementos){
		numeroPaginas = totalElementos/Parametros.getNumeroElementosPorPagina();
		if (criteria.getResultListSize()%Parametros.getNumeroElementosPorPagina() == 0)
    		numeroPaginas--;
		
		getLblPaginaFinal().setText(String.valueOf(numeroPaginas + 1));
	}
	
	private void validateButtons(){
		if ( numeroPaginas == 0 || busquedaPorParametros ){
			getBtnIrAnteriorRegistro().setEnabled(false);
			getBtnIrPrimerosRegistros().setEnabled(false);
			getBtnIrSiguienteRegistro().setEnabled(false);
			getBtnIrUltimosRegistros().setEnabled(false);
		}
		else if ( numeroPaginas>0 ){
			if ( paginaActual == numeroPaginas ) {
				getBtnIrAnteriorRegistro().setEnabled(true);
				getBtnIrPrimerosRegistros().setEnabled(true);
				getBtnIrSiguienteRegistro().setEnabled(false);
				getBtnIrUltimosRegistros().setEnabled(false);
			} else if ( paginaActual > 0 ){
				getBtnIrAnteriorRegistro().setEnabled(true);
				getBtnIrPrimerosRegistros().setEnabled(true);
				getBtnIrSiguienteRegistro().setEnabled(true);
				getBtnIrUltimosRegistros().setEnabled(true);
			} else if ( paginaActual==0 ){
				getBtnIrAnteriorRegistro().setEnabled(false);
				getBtnIrPrimerosRegistros().setEnabled(false);
				getBtnIrSiguienteRegistro().setEnabled(true);
				getBtnIrUltimosRegistros().setEnabled(true);
			}
		}
	}

    public JComboBox getCmbParametro3() {
		return cmbParametro3;
	}
    
	public JComboBox getCmbParametro4() {
		return cmbParametro4;
	}
	
	public JComboBox getCmbParametro5() {
		return cmbParametro5;
	}
	
	public JComboBox getCmbParametro6() {
		return cmbParametro6;
	}

	public boolean isActivaSeleccion() {
		return activaSeleccion;
	}

	public void setActivaSeleccion(boolean activaSeleccion) {
		this.activaSeleccion = activaSeleccion;
	}
}
