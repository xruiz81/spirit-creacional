package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPReportePlanCuentas;
import com.spirit.contabilidad.util.MyRowTreeTablePlanCuentas;
import com.spirit.contabilidad.util.MyTreeTableModelPlanCuentas;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

//Clase principal para presentar el Balance General
public class ReportePlanCuentasModel extends JPReportePlanCuentas {
	private static final long serialVersionUID = 5051512376009020561L;
	//Variables principales
	private PlanCuentaIf planCuenta;
	private Long idPlanCuenta;
	private MyTreeTableModelPlanCuentas myTreeTableModel;
	public static ArrayList filasPadre = new ArrayList();
	Vector<CuentaIf> cuentasTreeTable = new Vector<CuentaIf>();
	
	private final static String NOMBRE_MENU_REPORTE_PLAN_CUENTAS = "PLAN CUENTAS";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public ReportePlanCuentasModel(){
		this.showSaveMode();
		initListeners();
		cargarCombos();
		new HotKeyComponent(this);
	}
	
	public void clean() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblReportePlanCuentas().getModel();
		
		for(int i= this.getTblReportePlanCuentas().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void cleanTreeTable() {
		MyTreeTableModelPlanCuentas model = myTreeTableModel;	
		
		if (model != null)
			for (int i = model.getRowCount(); i > 0; --i)
				model.removeRow(i - 1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		cleanTable();
		cleanTreeTable();
	}

	private void cargarCombos() {
		cargarComboPlanCuenta();
		cargarComboNivelesVisibles();
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuenta = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuenta);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}
	
	private void cargarComboNivelesVisibles() {
		getCmbNivelesVisibles().removeAllItems();
		
		if (planCuenta != null) {
			int nivelMaximo = SessionServiceLocator.getCuentaSessionService().getNivelMaximoByPlanCuentaId(planCuenta.getId());
			
			for (int i=nivelMaximo; i>0; i--)
				getCmbNivelesVisibles().addItem(String.valueOf(i));
		}
	}
		
	private void initListeners(){
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
			}
		});				
						
		getBtnImprimir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(SpiritCursor.hourglassCursor);
				cargarTreeTable();
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
	public boolean validateFields() {
		if (getCmbPlanCuenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un plan de cuentas", SpiritAlert.INFORMATION);
			getCmbPlanCuenta().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				if (getTreeTblReportePlanCuentas().getModel().getRowCount() > 0) {
					String si = "Si"; 
	    	        String no = "No"; 
	    	        Object[] options ={si,no}; 
	    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Plan de Cuentas?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						setCursor(SpiritCursor.hourglassCursor);
						DefaultTableModel tblModelReporte = treeTableModelToDefaultTableModel();
						String fileName = "jaspers/contabilidad/RPPlanCuentas.jasper";
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_REPORTE_PLAN_CUENTAS).iterator().next();
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_REPORTE_PLAN_CUENTAS).iterator();
						if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
						
						parametrosMap.put("codigoReporte", menu.getCodigo());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", empresa.getNombre());
						parametrosMap.put("ruc", empresa.getRuc());
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						parametrosMap.put("ciudad", ciudad.getNombre());
						String fechaActual = Utilitarios.dateHoraHoy();
						String year = fechaActual.substring(0,4);
						String month = fechaActual.substring(5,7);
						String day = fechaActual.substring(8,10);
						String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						parametrosMap.put("usuario", Parametros.getUsuario());
						parametrosMap.put("emitido", fechaEmision);
						parametrosMap.put("codPlanCuenta", planCuenta.getCodigo());
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
						setCursor(SpiritCursor.normalCursor);
					}
				} else
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private DefaultTableModel treeTableModelToDefaultTableModel() {
		int rows = getTreeTblReportePlanCuentas().getModel().getRowCount();
		int cols = getTreeTblReportePlanCuentas().getModel().getColumnCount();
		cleanTable();
		DefaultTableModel tableModel = (DefaultTableModel) getTblReportePlanCuentas().getModel();
		
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				String sangria = "";
				
				for (int j = 0; j < cols; j++) {
					if (getTreeTblReportePlanCuentas().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblReportePlanCuentas().getModel().getValueAt(i,j).toString().trim();
						int indexOfCierreCorchete = 0;
						String codigoCuenta = "";
						if (data.contains("]")) {
							indexOfCierreCorchete = data.indexOf("]");
							codigoCuenta = data.substring(1, indexOfCierreCorchete);
						}

						int NIVEL_MINIMO = 1;
						if (j == 0) {
							Collection cuentaCollection = SessionServiceLocator.getCuentaSessionService().findCuentaByCodigo(codigoCuenta);
							
							if (cuentaCollection.size() > 0) {
								CuentaIf cuenta = (CuentaIf) cuentaCollection.iterator().next();
								int nivel = cuenta.getNivel() - NIVEL_MINIMO;
								for (int k = 0; k < nivel; k++)
									sangria = sangria.concat("     ");
							}
						}
						
						if (j == 0)
							fila.add(sangria.concat(data));
						else if (j == 1)
							fila.add(data.concat(sangria));
					} else
						fila.add("");
				}
			
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		//tableModel.removeRow(rows-1);
		return tableModel;
	}
	
	private Comparator<CuentaIf> comparadorCodigoCuenta = new Comparator<CuentaIf>() {
		public int compare(CuentaIf o1, CuentaIf o2) {
			String idCodigo1 = o1.getCodigo();
			String idCodigo2 = o2.getCodigo();

			return idCodigo1.compareTo(idCodigo2);
		}
	};
	
	//Función que construye el treetable con todos sus resultados!!!
	private void cargarTreeTable() {
		//LinkedHashMap cuentasReporteMap = new LinkedHashMap();
		//LinkedHashMap saldosReporteMap = new LinkedHashMap();
		cuentasTreeTable.clear();
		if (validateFields()) {
			try {
				int nivelesVisibles = Integer.parseInt(getCmbNivelesVisibles().getSelectedItem().toString());
				idPlanCuenta = planCuenta.getId();
				Map parameterMap = new HashMap();
				parameterMap.put("plancuentaId", idPlanCuenta);
				if (!getCbIncluirCuentasInactivas().isSelected())
					parameterMap.put("estado", "A");
				Collection cuentasReporteColeccion = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap);
				Collections.sort((ArrayList) cuentasReporteColeccion, comparadorCodigoCuenta);
				/*Iterator saldosCuentasIterator = getSaldoCuentaSessionService().findSaldoCuentaByPlanCuentaId(idPlanCuenta).iterator();
				while (saldosCuentasIterator.hasNext()) {
					SaldoCuentaIf saldoCuentaIf = (SaldoCuentaIf) saldosCuentasIterator.next();
					saldosReporteMap.put(saldoCuentaIf.getCuentaId(), saldoCuentaIf.getValor());
				}*/
				Vector<CuentaIf> cuentasBalanceVector = generarVectorCuentasReporte(cuentasReporteColeccion);
				for (int i=0; i<cuentasBalanceVector.size(); i++) {
					CuentaIf cuentaIf = cuentasBalanceVector.get(i);
					//Double saldoCuenta = (Double) saldosReporteMap.get(cuentaIf.getId());
					//cuentasReporteMap.put(cuentaIf.getId(), saldoCuenta);
					cuentasTreeTable.add(cuentaIf);
				}
				
				parameterMap = new HashMap();
				parameterMap.put("plancuentaId", planCuenta.getId());
				parameterMap.put("nivel", 1);
				Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap).iterator();
				//Array que contendra la lista de filas que componen el treetable
				ArrayList myList = new ArrayList();
				while (cuentasIterator.hasNext()) {
					CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
					if (!cuentaIf.getCodigo().equals("A") && !cuentaIf.getCodigo().equals("B") && !cuentaIf.getCodigo().equals("C") && !cuentaIf.getCodigo().equals("D") && !cuentaIf.getCodigo().equals("E") && !cuentaIf.getCodigo().equals("F") && !cuentaIf.getCodigo().equals("G")) {
					//AREA QUE CONSTRUYE EL TREETABLE
					//Llamo a MyRowTreeTablaBalance que es el que construye el arbol seteando los padres e hijos
					MyRowTreeTablePlanCuentas treetableReportePlanCuentas = new MyRowTreeTablePlanCuentas(cuentaIf, "Cuenta", nivelesVisibles, false, filtrosBusqueda());
					//Envio las fechas escogidas a MyRowTreeTableBalance			
					MyRowTreeTablePlanCuentas.setPlanCuentasTreeTable(cuentasTreeTable);
					//MyRowTreeTablePlanCuentas.setPlanCuentasMap(cuentasReporteMap);
					//Añado al array cada una de las filas padre, en MyrowTreeTableBalance se setean todos sus hijos
					myList.add(treetableReportePlanCuentas);
					//Seteo el total de cada Padre
					//Si el padre es Utilidades Retenidas uso el valor totalResultadoSuma, caso contrario uso totalResultadoSuma1
					/*TipoCuentaIf tipoCuenta = getTipoCuentaSessionService().getTipoCuenta(cuentaIf.getTipocuentaId());
					if (tipoCuenta.getCodigo().equals("U"))
						myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format(totalResultadoSuma1), nivelesVisibles, true, null, null, null));
					else if (cuentasReporteMap.get(cuentaIf.getId()) != null)
						myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format((Double) cuentasReporteMap.get(cuentaIf.getId())), nivelesVisibles, true, null, null, null));
					else
						myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format(0.0), nivelesVisibles, true, null, null, null));
					//Añado una fila en blanco despues de la familia de cada padre, solo por estetica
					myList.add(new MyRowTreeTableBalance(null, "Total", null, nivelesVisibles, true, null, null, null));*/
					//vuelvo a setear la variable que contiene el total de los padres en cero
					//Creo el modelo del árbol.
					myTreeTableModel = new MyTreeTableModelPlanCuentas(myList);
					//Creo el árbol enviando el modelo
					getTreeTblReportePlanCuentas().setModel(myTreeTableModel);
					//Seteo el ancho del treetable
					getTreeTblReportePlanCuentas().getColumnModel().getColumn(0).setPreferredWidth(400);
					//Opcion para que el árbol salga expandido y no se pueda reordenar
					getTreeTblReportePlanCuentas().expandAll();
					//Opcion para que no se pueda reordenar el arbol
					getTreeTblReportePlanCuentas().setSortable(false);
					}
				}

				setUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}		
		}	
	}

	private Vector<CuentaIf> generarVectorCuentasReporte(Collection cuentasBalanceColeccion) {
		Vector<CuentaIf> cuentasBalanceVector = new Vector<CuentaIf>();
		Iterator cuentasBalanceIterator = cuentasBalanceColeccion.iterator();
		while (cuentasBalanceIterator.hasNext())
			cuentasBalanceVector.add((CuentaIf) cuentasBalanceIterator.next());
		
		return cuentasBalanceVector;
	}
	
	private Map filtrosBusqueda() {
		Map aMap = new HashMap();
		aMap.put("idPlanCuenta", idPlanCuenta);
		return aMap;
	}
}

