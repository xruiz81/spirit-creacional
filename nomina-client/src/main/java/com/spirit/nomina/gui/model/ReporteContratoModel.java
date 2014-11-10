package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.grid.QuickFilterPane;
import com.jidesoft.grid.SortableTable;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.panel.JPReporteContrato;
import com.spirit.util.Utilitarios;

public class ReporteContratoModel extends JPReporteContrato {

	private static final long serialVersionUID = 8357863917787690377L;

	private final int COLUMNA_ANIO = 0;
	private final int COLUMNA_MES = 1;
	private final int COLUMNA_VALOR = 3;

	private QuickFilterPane qfp = null;
	SortableTable sorterTable = null;
	private CellConstraints cc = new CellConstraints();

	private TipoContratoIf tipoContratoIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	private Double total = 0.0;
	
	private Date fechaInicio = null;
	private Date fechaFin = null;
	
	String[] cabecera = new String[] { "Año", "Mes", "Rubro", "Valor" };

	private DecimalFormat fomatoDosDecimales = new DecimalFormat("'$'#0.00");
	
	public ReporteContratoModel() {
		initKeyListeners();
		iniciarComponentes();
		initListeners();		
		cargarComboTipoContrato();		
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
	}

	private void iniciarComponentes() {

		getLblValorTotal().setText(fomatoDosDecimales.format(total));
		
		//BOTON BUSQUEDA DE EMPLEADO
		getBtnBuscarEmpleado().setText("");
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		//BOTON BUSQUEDA DE CONTRATO
		getBtnBuscarContrato().setText("");
		getBtnBuscarContrato().setToolTipText("Buscar Contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);

	}

	private void initListeners() {
		
		getBtnBuscarEmpleado().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				busquedaEmpleado();
			}
		});
		
		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				busquedaContrato();
			}}
		);
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultar();
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(),SpiritAlert.ERROR);
				} catch (Exception e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert("Error general al consultar Reporte !!",SpiritAlert.ERROR);
				}
			}
		});
	}
	
	private void busquedaEmpleado() {
		try{
			EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado()!=null ){
				if ( sorterTable!=null )
					limpiarTabla(sorterTable);
				empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
				getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				getTxtContrato().setText("");
				
				tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
				if ( tipoContratoIf == null ){
					return;
				}
				
				/*Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
				 Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
				 int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
				 calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
				 Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
				 */
				Map<String, Object> mapa = new HashMap<String, Object>();
				//mapa.put("fechaMediaContrato", fechaMedia);
				//mapa.put("fechaMediaContratoMax", fechaMediaMax);
				
				mapa.put("tipocontratoId", tipoContratoIf.getId());
				mapa.put("empleadoId", empleadoIf.getId());
				//mapa.put("estado", "A");
				
				Collection<ContratoIf> contratos  = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
				if ( contratos.size() == 1 ){
					contratoIf = contratos.iterator().next();
					getTxtContrato().setText(contratoIf.getCodigo());
				} else {
					contratoIf = null;
					getTxtContrato().setText("");
				}
				
			}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de Empleado !!",SpiritAlert.ERROR);
		}
	};
	
	private void busquedaContrato() {
		try {
			
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Debe elegir un tipo de Contrato !!", SpiritAlert.INFORMATION);
				return;
			}
			
			if ( empleadoIf == null ){
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.INFORMATION);
				return;
			}
			
			/*mesSeleccionado = (String)getCmbMes().getSelectedItem();
			int mes = Utilitarios.getMesInt(mesSeleccionado);
			
			anioSeleccionado =(String)getCmbAnio().getSelectedItem();
			int anio = Integer.valueOf(anioSeleccionado);
			
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			*/
			
			/*Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			*/
			Map<String, Object> mapa = new HashMap<String, Object>();
			//mapa.put("fechaMediaContrato", fechaMedia);
			//mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("tipocontratoId", tipoContratoIf.getId());
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");
			
			contratoIf = null;
			int tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if ( tamanoLista > 0 ){
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					if ( sorterTable != null )
						limpiarTabla(sorterTable);
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
				}
				contratoCriteria = null;
				popupFinder = null;
			} else{
				getTxtContrato().setText("");
				SpiritAlert.createAlert("No existen contratos activos para el empleado !!",	SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de Contrato", SpiritAlert.WARNING);
		}
	}
	
	@Override
	public boolean validateFields() {
		
		if ( contratoIf == null ){
			SpiritAlert.createAlert("Seleccionar un contrato !!", SpiritAlert.WARNING);
			return false;
		}
		
		java.util.Date fechaInicioCombo = getCmbFechaInicio().getDate();
		if ( fechaInicioCombo == null ){
			SpiritAlert.createAlert("Seleccionar una fecha de inicio !!", SpiritAlert.WARNING);
			return false;
		}
		
		java.util.Date fechaFinCombo = getCmbFechaFin().getDate();
		if ( fechaFinCombo == null ){
			SpiritAlert.createAlert("Seleccionar una fecha fin !!", SpiritAlert.WARNING);
			return false;
		}
		
		
		
		return true;
	}
	
	private void consultar() throws GenericBusinessException {
		
		fechaInicio = new Date( getCmbFechaInicio().getDate().getTime() );
		fechaFin = new Date( getCmbFechaFin().getDate().getTime() );
		
		Collection<Object> datos = SessionServiceLocator.getRolPagoSessionService().consultarReportePorContrato(contratoIf.getId(),fechaInicio,fechaFin);

		
		
		/*ArrayList datos = new ArrayList();

		for (int c = 0; c < 3; c++) {

			Vector a = new Vector();
			for (int i = 0; i < 5; i++) {
				if (i == COLUMNA_VALOR)
					a.add(Double.valueOf(i*100));
				else{
					if ( i == 0 )
						a.add(""+c);
					else if ( i == 1)
						a.add("A");
					else 
						a.add(String.valueOf(i));
				}
			}
			datos.add(a);
			
			a = new Vector();
			for (int i = 0; i < 5; i++) {
				if (i == COLUMNA_VALOR)
					a.add(Double.valueOf(i));
				else{
					if ( i == 0 )
						a.add(""+c);
					else if ( i == 1)
						a.add("B");
					else 
						a.add(String.valueOf(i));
				}
			}
			datos.add(a);
			
		}*/
		
		montarTablaYFiltros(datos);
		mostrarTotal();
	}

	private void montarTablaYFiltros(Collection<Object> datos) {

		/*TableModel modelo = armarModeloTabla(datos);
		
		QuickFilterPane qfp = new QuickFilterPane(modelo,getIndicesParaFiltrar());
		getPanelFiltro().add(qfp,new CellConstraints().xy(1,1));
				
		//filterField = new QuickTableFilterField(qfp.getDisplayTableModel(), new int[]{0});
		//getPanelBusquedaRapida().add(filterField, new CellConstraints().xy(1, 1));
		
		sorterTable = new SortableTable(qfp.getDisplayTableModel());
		qfp.setTable(sorterTable);
		
		qfp.getDisplayTableModel().addTableModelListener(tml);
		
		getScrollPane1().setViewportView(sorterTable);*/
		
		TableModel modelo = armarModeloTabla(datos);

		if (qfp != null){
			getPanelFiltro().remove(qfp);
			qfp=null;
		}

		qfp = new QuickFilterPane(modelo, getIndicesParaFiltrar());
		qfp.repaint();
		getPanelFiltro().add(qfp, cc.xy(1, 1));
		getPanelFiltro().repaint();

		if ( sorterTable != null ){
			getPanelTable().remove(sorterTable);
			sorterTable = null;
		}
		
		sorterTable = new SortableTable(qfp
				.getDisplayTableModel());
		sorterTable.repaint();
		//qfp.setTable(sorterTable);
		qfp.getDisplayTableModel().addTableModelListener(tml);
		
		getPanelTable().setViewportView(sorterTable);
		getPanelTable().repaint();

	}

	private int[] getIndicesParaFiltrar() {
		return new int[] { COLUMNA_ANIO , COLUMNA_MES };
	}

	private DefaultTableModel armarModeloTabla(Collection<Object> datos) {

		Object[][] contenido = convVectorToArrayList(datos);
		DefaultTableModel modelo = new DefaultTableModel(contenido, cabecera);
		return modelo;

	}
	
	private Object[][] convVectorToArrayList(Collection<Object> arrayList){
		Object[][] matriz = new Object[arrayList.size()][];
		int contadorExterno = 0;
		for ( Object o : arrayList ){
			if ( o instanceof Collection ){
				Collection<Object> fila = (Collection<Object>) o;
				if ( fila.size() > 0 ){
					Object[] vFila = new Object[fila.size()];
					int contadorInterno = 0;
					for ( Object oFilas : fila ){
						vFila[contadorInterno] = oFilas;
						if ( contadorInterno == COLUMNA_VALOR ){
							BigDecimal valor = (BigDecimal) oFilas;
							total += Utilitarios.redondeoValor( valor.doubleValue() );  
						}
						contadorInterno++;
					}
					matriz[contadorExterno] = vFila;
				}
				contadorExterno++;
			}
		}
		total = Utilitarios.redondeoValor(total);
		return matriz;
	}

	TableModelListener tml = new TableModelListener() {
		public void tableChanged(TableModelEvent e) {
			total = 0.0;
			if (e.getSource() instanceof TableModel) {
				TableModel tb = (TableModel) e.getSource();
				int filas = tb.getRowCount();
				for (int i = 0; i < filas; i++) {
					Double valor = (Double) tb.getValueAt(i, COLUMNA_VALOR);
					total += valor;
				}
				total = Utilitarios.redondeoValor(total);
				mostrarTotal();
			}
		}
	};

	private void mostrarTotal(){
		getLblValorTotal().setText(fomatoDosDecimales.format(total));
	}
	
	public void refresh() {
		cargarComboTipoContrato();
	}
	
	private void cargarComboTipoContrato(){
		try {
			Collection<TipoContratoIf> tiposContratoCollection = SessionServiceLocator.getTipoContratoSessionService().findTiposContratosUsados(Parametros.getIdEmpresa());
			Vector<TipoContratoIf> tiposContrato = new Vector<TipoContratoIf>(tiposContratoCollection);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposContrato);
			getCmbTipoContrato().setModel(comboModel);
			refreshCombo(getCmbTipoContrato(), (List)tiposContratoCollection);
			for (int i=0 ; i<comboModel.getSize() ; i++){
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getItemAt(i);
				if ( "RD".equals(tc.getCodigo()) ){
					tipoContratoIf = tc;
					getCmbTipoContrato().setSelectedIndex(i);
					break;
				}
			}	
			getCmbTipoContrato().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}
	
	@Override
	public void clean() {
		
		this.tipoContratoIf = null;
		this.empleadoIf = null;
		this.contratoIf = null;
		
		getTxtEmpleado().setText("");
		getTxtContrato().setText("");
		
		if ( getCmbTipoContrato().getItemCount() > 0 )
			getCmbTipoContrato().setSelectedItem(0);
		else
			getCmbTipoContrato().setSelectedItem(null);
			
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
		
	}

	@Override
	public void showSaveMode() {
		clean();

	}

	@Override
	public void report() {

	}
	
}
