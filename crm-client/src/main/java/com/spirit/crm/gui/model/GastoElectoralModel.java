package com.spirit.crm.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.GastoElectoralAbonoData;
import com.spirit.crm.entity.GastoElectoralAbonoIf;
import com.spirit.crm.entity.GastoElectoralData;
import com.spirit.crm.entity.GastoElectoralIf;
import com.spirit.crm.gui.panel.JPGastoElectoral;
import com.spirit.crm.session.GastoElectoralAbonoSessionService;
import com.spirit.crm.session.GastoElectoralSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class GastoElectoralModel extends JPGastoElectoral {
	
	private static final int MAX_LONGITUD_CAMPANA = 50;
	private static final int MAX_LONGITUD_TIPO = 50;
	private static final int MAX_LONGITUD_PRODUCTO = 50;
	private static final int MAX_LONGITUD_PROVEEDOR = 50;
	private static final int MAX_LONGITUD_DESCRIPCION = 50;
	private static final int MAX_LONGITUD_TAMANO = 50;
	private List<GastoElectoralIf> gastoElectoralColeccion = new ArrayList<GastoElectoralIf>();
	private List<GastoElectoralIf> gastoElectoralEliminados = new ArrayList<GastoElectoralIf>();
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private int gastoElectoralSeleccionado ;
	private GastoElectoralIf gastoElectoralIf ;
	private List<GastoElectoralAbonoIf> gastoElectoralIngresosColeccion = new ArrayList<GastoElectoralAbonoIf>();
	private List<GastoElectoralAbonoIf> gastoElectoralIngresosEliminados = new ArrayList<GastoElectoralAbonoIf>();
	private DefaultTableModel tableModelIngresos;
	private int gastoElectoralIngresoSeleccionado ;
	private GastoElectoralAbonoIf gastoElectoralIngresoIf ;

	public GastoElectoralModel(){
		initKeyListeners();
		anchoColumnasTabla();
		anchoColumnasTablaIngresos();
		showSaveMode();
		initListers();
		getTblGastos().addMouseListener(oMouseAdapterTblGastos);
		getTblGastos().addKeyListener(oKeyAdapterTblGastos);
		getTblIngresos().addMouseListener(oMouseAdapterTblIngresos);
		getTblIngresos().addKeyListener(oKeyAdapterTblIngresos);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblGastos());
		getTblGastos().getTableHeader().setReorderingAllowed(false);
		getTblGastos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblGastos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblGastos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblGastos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGastos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGastos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblGastos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblGastos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblGastos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(70);
	}
	
	private void anchoColumnasTablaIngresos() {
		setSorterTable(getTblIngresos());
		getTblIngresos().getTableHeader().setReorderingAllowed(false);
		getTblIngresos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblIngresos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblIngresos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblIngresos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblIngresos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
	}
	
	private void initKeyListeners() {
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFecha().setEditable(false);
		getCmbFecha().setCalendar(new GregorianCalendar());
		getCmbFechaIngreso().setLocale(Utilitarios.esLocale);
		getCmbFechaIngreso().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaIngreso().setEditable(false);
		getCmbFechaIngreso().setCalendar(new GregorianCalendar());
		
		getTxtCampana().addKeyListener(new TextChecker(MAX_LONGITUD_CAMPANA));
		getTxtTipo().addKeyListener(new TextChecker(MAX_LONGITUD_TIPO));
		getTxtProducto().addKeyListener(new TextChecker(MAX_LONGITUD_PRODUCTO));
		getTxtProveedor().addKeyListener(new TextChecker(MAX_LONGITUD_PROVEEDOR));
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, 0));
		getTxtTamano().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO, 0));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtCostoUnitario().addKeyListener(new NumberTextFieldDecimal());
		getTxtCostoUnitario().setEditable(false);
		getTxtInversionConFactura().addKeyListener(new NumberTextFieldDecimal());
		getTxtInversionSinFactura().addKeyListener(new NumberTextFieldDecimal());
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtCostoUnitario().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtInversionConFactura().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtInversionSinFactura().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtCampanaIngreso().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtEntregadoPor().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtValorIngreso().addKeyListener(new TextChecker(MAX_LONGITUD_TAMANO));
		getTxtValorIngreso().addKeyListener(new NumberTextFieldDecimal());
	}
	
	MouseListener oMouseAdapterTblGastos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblGastos = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setGastoElectoralSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			gastoElectoralIf = (GastoElectoralIf) gastoElectoralColeccion.get(getGastoElectoralSeleccionado());
			getTxtCampana().setText(gastoElectoralIf.getCampana());
			getCmbFecha().setDate(gastoElectoralIf.getFecha());
			getTxtTipo().setText(gastoElectoralIf.getTipo());
			getTxtProducto().setText(gastoElectoralIf.getProducto()!=null?gastoElectoralIf.getProducto():"");
			getTxtProveedor().setText(gastoElectoralIf.getProveedor());
			getTxtDescripcion().setText(gastoElectoralIf.getDescripcion()!=null?gastoElectoralIf.getDescripcion():"");
			getTxtTamano().setText(gastoElectoralIf.getTamano()!=null?gastoElectoralIf.getTamano():"");
			getTxtCantidad().setText(gastoElectoralIf.getCantidad().toString());
			getTxtCostoUnitario().setText(gastoElectoralIf.getCostoUnitario()!=null?formatoDecimal.format(gastoElectoralIf.getCostoUnitario()):"");
			getTxtInversionConFactura().setText(gastoElectoralIf.getInversionConFactura()!=null?formatoDecimal.format(gastoElectoralIf.getInversionConFactura()):"");
			getTxtInversionSinFactura().setText(gastoElectoralIf.getInversionSinFactura()!=null?formatoDecimal.format(gastoElectoralIf.getInversionSinFactura()):"");
		}
	}
	
	MouseListener oMouseAdapterTblIngresos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowIngresosForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblIngresos = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowIngresosForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowIngresosForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setGastoElectoralIngresoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			gastoElectoralIngresoIf = (GastoElectoralAbonoIf) gastoElectoralIngresosColeccion.get(getGastoElectoralIngresoSeleccionado());
			getTxtCampanaIngreso().setText(gastoElectoralIngresoIf.getCampana());
			getCmbFechaIngreso().setDate(gastoElectoralIngresoIf.getFecha());
			getTxtEntregadoPor().setText(gastoElectoralIngresoIf.getEntregadoPor());
			getTxtValorIngreso().setText(gastoElectoralIngresoIf.getValor()!=null?formatoDecimal.format(gastoElectoralIngresoIf.getValor()):"");
		}
	}
	
	public GastoElectoralAbonoIf registrarGastoElectoralIngreso(){
		GastoElectoralAbonoData data = new GastoElectoralAbonoData();
		data.setCampana(getTxtCampanaIngreso().getText());
		data.setFecha(new Date(getCmbFechaIngreso().getDate().getTime()));
		data.setEntregadoPor(getTxtEntregadoPor().getText());
		
		if(!getTxtValorIngreso().getText().equals("")){
			BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorIngreso().getText())));
			data.setValor(valor);
		}
			
		return data;
	}
	
	private void agregarDetalleIngreso() {
		if(validateFieldsIngreso()){
			tableModelIngresos = (DefaultTableModel) getTblIngresos().getModel();
			Vector<String> fila = new Vector<String>();
			
			GastoElectoralAbonoIf gastoElectoralAbonoIf = registrarGastoElectoralIngreso();
			
			fila.add(getTxtCampanaIngreso().getText());
			fila.add(Utilitarios.getFechaCortaUppercase(getCmbFechaIngreso().getDate()));
			fila.add(getTxtEntregadoPor().getText());
			fila.add(getTxtValorIngreso().getText()!=null?getTxtValorIngreso().getText():"");
			tableModelIngresos.addRow(fila);
			
			gastoElectoralIngresosColeccion.add(gastoElectoralAbonoIf);
			
			cleanIngresos();
		}
	}
	
	public GastoElectoralIf registrarGastoElectoral(){
		GastoElectoralData data = new GastoElectoralData();
		data.setCampana(getTxtCampana().getText());
		data.setProveedor(getTxtProveedor().getText());
		data.setFecha(new Date(getCmbFecha().getDate().getTime()));
		data.setTipo(getTxtTipo().getText());
		
		BigDecimal inversion = new BigDecimal(0);
		if(!getTxtInversionConFactura().getText().equals("")){
			BigDecimal invConFactura = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtInversionConFactura().getText())));
			data.setInversionConFactura(invConFactura);
			inversion = inversion.add(invConFactura);
		}
		if(!getTxtInversionSinFactura().getText().equals("")){
			BigDecimal invSinFactura = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtInversionSinFactura().getText())));
			data.setInversionSinFactura(invSinFactura);
			inversion = inversion.add(invSinFactura);
		}
		
		BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(getTxtCantidad().getText()));
		data.setCantidad(cantidad);
		
		BigDecimal costoUnitario = inversion.divide(cantidad, 2, BigDecimal.ROUND_HALF_UP);
		
		data.setCostoUnitario(costoUnitario);
		/*if(!getTxtCostoUnitario().getText().equals("")){
			data.setCostoUnitario(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCostoUnitario().getText()))));
		}*/
		
		if(!getTxtDescripcion().getText().equals("")){
			data.setDescripcion(getTxtDescripcion().getText());
		}
		if(!getTxtProducto().getText().equals("")){
			data.setProducto(getTxtProducto().getText());
		}
		if(!getTxtTamano().getText().equals("")){
			data.setTamano(getTxtTamano().getText());
		}
		
		return data;
	}
	
	private void agregarDetalle() {
		if(validateFields()){
			tableModel = (DefaultTableModel) getTblGastos().getModel();
			Vector<String> fila = new Vector<String>();
			
			GastoElectoralIf gastoElectoralIf = registrarGastoElectoral();
			
			fila.add(getTxtCampana().getText());
			fila.add(Utilitarios.getFechaCortaUppercase(getCmbFecha().getDate()));
			fila.add(getTxtTipo().getText());
			fila.add(getTxtProducto().getText());
			fila.add(getTxtProveedor().getText());
			fila.add(getTxtCantidad().getText());
			fila.add(formatoDecimal.format(gastoElectoralIf.getCostoUnitario()));
			fila.add(getTxtInversionSinFactura().getText()!=null?getTxtInversionSinFactura().getText():"");
			tableModel.addRow(fila);
			
			gastoElectoralColeccion.add(gastoElectoralIf);
			
			clean();
		}
	}
	
	public void initListers(){
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalle();
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int filaSeleccionada = getTblGastos().convertRowIndexToModel(getTblGastos().getSelectedRow());
				if (filaSeleccionada >= 0) {
					if(validateFields()){
						tableModel = (DefaultTableModel) getTblGastos().getModel();
						Vector<String> fila = new Vector<String>();
										
						GastoElectoralData data = new GastoElectoralData();
						data.setId(gastoElectoralColeccion.get(filaSeleccionada).getId());
						data.setCampana(getTxtCampana().getText());
						data.setProveedor(getTxtProveedor().getText());
						data.setFecha(new Date(getCmbFecha().getDate().getTime()));
						data.setTipo(getTxtTipo().getText());
						
						BigDecimal inversion = new BigDecimal(0);
						if(!getTxtInversionConFactura().getText().equals("")){
							BigDecimal invConFactura = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtInversionConFactura().getText())));
							data.setInversionConFactura(invConFactura);
							inversion = inversion.add(invConFactura);
						}
						if(!getTxtInversionSinFactura().getText().equals("")){
							BigDecimal invSinFactura = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtInversionSinFactura().getText())));
							data.setInversionSinFactura(invSinFactura);
							inversion = inversion.add(invSinFactura);
						}
						
						BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(getTxtCantidad().getText()));
						data.setCantidad(cantidad);
						
						BigDecimal costoUnitario = inversion.divide(cantidad, 2, BigDecimal.ROUND_HALF_UP);
						
						data.setCostoUnitario(costoUnitario);						
						
						/*if(!getTxtCostoUnitario().getText().equals("")){
							data.setCostoUnitario(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCostoUnitario().getText()))));
						}*/
						
						if(!getTxtDescripcion().getText().equals("")){
							data.setDescripcion(getTxtDescripcion().getText());
						}
						if(!getTxtProducto().getText().equals("")){
							data.setProducto(getTxtProducto().getText());
						}
						if(!getTxtTamano().getText().equals("")){
							data.setTamano(getTxtTamano().getText());
						}						
						
						gastoElectoralColeccion.add(filaSeleccionada, data);
						gastoElectoralColeccion.remove(filaSeleccionada+1);
						
						fila.add(getTxtCampana().getText());
						fila.add(Utilitarios.getFechaCortaUppercase(getCmbFecha().getDate()));
						fila.add(getTxtTipo().getText());
						fila.add(getTxtProducto().getText());
						fila.add(getTxtProveedor().getText());
						fila.add(getTxtCantidad().getText());
						fila.add(formatoDecimal.format(gastoElectoralIf.getCostoUnitario()));
						fila.add(getTxtInversionSinFactura().getText());
						
						tableModel.insertRow(filaSeleccionada, fila);
						tableModel.removeRow(filaSeleccionada+1);
						
						clean();
					}
				} else {
					SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblGastos().getSelectedRow() != -1) {
					int filaSeleccionada = getTblGastos().convertRowIndexToModel(getTblGastos().getSelectedRow());
					GastoElectoralIf gastoElectoralEliminado = gastoElectoralColeccion.get(filaSeleccionada);
					if (gastoElectoralEliminado.getId() != null){
						gastoElectoralEliminados.add(gastoElectoralEliminado);
					}
					gastoElectoralColeccion.remove(filaSeleccionada);
					tableModel.removeRow(filaSeleccionada);
					clean();
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnAgregarIngreso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleIngreso();
			}
		});
		
		getBtnActualizarIngreso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int filaSeleccionada = getTblIngresos().convertRowIndexToModel(getTblIngresos().getSelectedRow());
				if (filaSeleccionada >= 0) {
					if(validateFieldsIngreso()){
						tableModelIngresos = (DefaultTableModel) getTblIngresos().getModel();
						Vector<String> fila = new Vector<String>();
										
						GastoElectoralAbonoData data = new GastoElectoralAbonoData();
						data.setId(gastoElectoralIngresosColeccion.get(filaSeleccionada).getId());
						data.setCampana(getTxtCampanaIngreso().getText());
						data.setFecha(new Date(getCmbFechaIngreso().getDate().getTime()));
						data.setEntregadoPor(getTxtEntregadoPor().getText());
						
						if(!getTxtValorIngreso().getText().equals("")){
							BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorIngreso().getText())));
							data.setValor(valor);
						}
						
						gastoElectoralIngresosColeccion.add(filaSeleccionada, data);
						gastoElectoralIngresosColeccion.remove(filaSeleccionada+1);
						
						fila.add(getTxtCampanaIngreso().getText());
						fila.add(Utilitarios.getFechaCortaUppercase(getCmbFechaIngreso().getDate()));
						fila.add(getTxtEntregadoPor().getText());
						fila.add(getTxtValorIngreso().getText());
						
						tableModelIngresos.insertRow(filaSeleccionada, fila);
						tableModelIngresos.removeRow(filaSeleccionada+1);
						
						cleanIngresos();
					}
				} else {
					SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnEliminarIngreso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblIngresos().getSelectedRow() != -1) {
					int filaSeleccionada = getTblIngresos().convertRowIndexToModel(getTblIngresos().getSelectedRow());
					GastoElectoralAbonoIf gastoElectoralAbonoEliminado = gastoElectoralIngresosColeccion.get(filaSeleccionada);
					if (gastoElectoralAbonoEliminado.getId() != null){
						gastoElectoralIngresosEliminados.add(gastoElectoralAbonoEliminado);
					}
					gastoElectoralIngresosColeccion.remove(filaSeleccionada);
					tableModelIngresos.removeRow(filaSeleccionada);
					cleanIngresos();
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
				}
			}
		});
	}

	public void save() {
		try {
			SessionServiceLocator.getGastoElectoralSessionService().procesarGastoElectoral(gastoElectoralColeccion, gastoElectoralEliminados, gastoElectoralIngresosColeccion, gastoElectoralIngresosEliminados);	
			SpiritAlert.createAlert("Gasto Electoral guardado con éxito !", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getGastoElectoralSessionService().procesarGastoElectoral(gastoElectoralColeccion, gastoElectoralEliminados, gastoElectoralIngresosColeccion, gastoElectoralIngresosEliminados);	
			SpiritAlert.createAlert("Gasto Electoral eliminado con éxito !", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			SessionServiceLocator.getGastoElectoralSessionService().procesarGastoElectoral(gastoElectoralColeccion, gastoElectoralEliminados, gastoElectoralIngresosColeccion, gastoElectoralIngresosEliminados);	
			SpiritAlert.createAlert("Gasto Electoral actualizado con éxito !", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		getTxtCampana().setText("");
		getTxtTipo().setText("");
		getTxtProducto().setText("");
		getTxtProveedor().setText("");
		getTxtDescripcion().setText("");
		getTxtTamano().setText("");
		getTxtCantidad().setText("");
		getTxtCostoUnitario().setText("");
		getTxtInversionConFactura().setText("");
		getTxtInversionSinFactura().setText("");
		getTxtCampana().grabFocus();
	}
	
	public void cleanIngresos() {
		getTxtCampanaIngreso().setText("");
		getTxtEntregadoPor().setText("");
		getTxtValorIngreso().setText("");
		getTxtCampanaIngreso().grabFocus();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblGastos().getModel();
		for(int i= this.getTblGastos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTableIngresos() {
		DefaultTableModel model = (DefaultTableModel) getTblIngresos().getModel();
		for(int i= this.getTblIngresos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cleanIngresos();
		cleanTable();
		cleanTableIngresos();
		cargarTabla();
		cargarTablaIngresos();
		getTxtCampana().grabFocus();
	}
	
	public void cargarTabla(){
		try {
			gastoElectoralColeccion = null;
			gastoElectoralColeccion = new ArrayList<GastoElectoralIf>();
			gastoElectoralEliminados = null;
			gastoElectoralEliminados = new ArrayList<GastoElectoralIf>();
			
			gastoElectoralColeccion = (List)SessionServiceLocator.getGastoElectoralSessionService().getGastoElectoralList();
			Iterator gastoElectoralColeccionIt = gastoElectoralColeccion.iterator();
			
			while(gastoElectoralColeccionIt.hasNext()){
				GastoElectoralIf gastoElectoralIf = (GastoElectoralIf)gastoElectoralColeccionIt.next();
				
				tableModel = (DefaultTableModel) getTblGastos().getModel();
				Vector<String> fila = new Vector<String>();
				
				agregarColumnasTabla(gastoElectoralIf, fila);
				
				tableModel.addRow(fila);
			}		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void agregarColumnasTabla(GastoElectoralIf gastoElectoralIf, Vector<String> fila){
		fila.add(gastoElectoralIf.getCampana());
		fila.add(Utilitarios.getFechaCortaUppercase(gastoElectoralIf.getFecha()));
		fila.add(gastoElectoralIf.getTipo());
		fila.add(gastoElectoralIf.getProducto()!=null?gastoElectoralIf.getProducto():"");
		fila.add(gastoElectoralIf.getProveedor());
		fila.add(gastoElectoralIf.getCantidad().toString());
		fila.add(gastoElectoralIf.getCostoUnitario()!=null?formatoDecimal.format(gastoElectoralIf.getCostoUnitario()):"");
		fila.add(gastoElectoralIf.getInversionSinFactura()!=null?formatoDecimal.format(gastoElectoralIf.getInversionSinFactura()):"");
	}
	
	public void cargarTablaIngresos(){
		try {
			gastoElectoralIngresosColeccion = null;
			gastoElectoralIngresosColeccion = new ArrayList<GastoElectoralAbonoIf>();
			gastoElectoralIngresosEliminados = null;
			gastoElectoralIngresosEliminados = new ArrayList<GastoElectoralAbonoIf>();
			
			gastoElectoralIngresosColeccion = (List)SessionServiceLocator.getGastoElectoralAbonoSessionService().getGastoElectoralAbonoList();
			Iterator gastoElectoralIngresosColeccionIt = gastoElectoralIngresosColeccion.iterator();
			
			while(gastoElectoralIngresosColeccionIt.hasNext()){
				GastoElectoralAbonoIf gastoElectoralAbonoIf = (GastoElectoralAbonoIf)gastoElectoralIngresosColeccionIt.next();
				
				tableModelIngresos = (DefaultTableModel) getTblIngresos().getModel();
				Vector<String> fila = new Vector<String>();
				
				agregarColumnasTablaIngresos(gastoElectoralAbonoIf, fila);
				
				tableModelIngresos.addRow(fila);
			}		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void agregarColumnasTablaIngresos(GastoElectoralAbonoIf gastoElectoralAbonoIf, Vector<String> fila){
		fila.add(gastoElectoralAbonoIf.getCampana());
		fila.add(Utilitarios.getFechaCortaUppercase(gastoElectoralAbonoIf.getFecha()));
		fila.add(gastoElectoralAbonoIf.getEntregadoPor());
		fila.add(gastoElectoralAbonoIf.getValor()!=null?formatoDecimal.format(gastoElectoralAbonoIf.getValor()):"");
	}

	public boolean validateFields() {
		
		if(getTxtCampana().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar la Campaña", SpiritAlert.WARNING);
			getTxtCampana().grabFocus();
			return false;
		}
		
		if(getCmbFecha().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha", SpiritAlert.WARNING);
			getCmbFecha().grabFocus();
			return false;
		}
		
		if(getTxtTipo().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar el Tipo", SpiritAlert.WARNING);
			getTxtTipo().grabFocus();
			return false;
		}
		
		/*if(getTxtProducto().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar el Producto", SpiritAlert.WARNING);
			getTxtProducto().grabFocus();
			return false;
		}*/
		
		if(getTxtProveedor().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar el Proveedor", SpiritAlert.WARNING);
			getTxtProveedor().grabFocus();
			return false;
		}
		
		if(getTxtCantidad().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar la Cantidad", SpiritAlert.WARNING);
			getTxtCantidad().grabFocus();
			return false;
		}
		
		if((getTxtInversionConFactura().getText().equals("") || getTxtInversionConFactura().getText() == null) &&
				(getTxtInversionSinFactura().getText().equals("") || getTxtInversionSinFactura().getText() == null)){
			SpiritAlert.createAlert("Al menos debe existir un valor de inversión", SpiritAlert.WARNING);
			getTxtInversionConFactura().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsIngreso() {
		
		if(getTxtCampanaIngreso().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar la Campaña", SpiritAlert.WARNING);
			getTxtCampanaIngreso().grabFocus();
			return false;
		}
		
		if(getCmbFechaIngreso().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha", SpiritAlert.WARNING);
			getCmbFechaIngreso().grabFocus();
			return false;
		}
		
		if(getTxtEntregadoPor().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar la persona que Entrego el dinero", SpiritAlert.WARNING);
			getTxtEntregadoPor().grabFocus();
			return false;
		}
		
		if(getTxtValorIngreso().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar el Valor", SpiritAlert.WARNING);
			getTxtValorIngreso().grabFocus();
			return false;
		}
		
		return true;
	}
	 
	
	public int getGastoElectoralSeleccionado() {
		return gastoElectoralSeleccionado;
	}

	public void setGastoElectoralSeleccionado(
			int gastoElectoralSeleccionado) {
		this.gastoElectoralSeleccionado = gastoElectoralSeleccionado;
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		if(getJtpGastoElectoral().getSelectedIndex() == 1){
			agregarDetalleIngreso();
		}else{
			agregarDetalle();
		}
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public int getGastoElectoralIngresoSeleccionado() {
		return gastoElectoralIngresoSeleccionado;
	}

	public void setGastoElectoralIngresoSeleccionado(
			int gastoElectoralIngresoSeleccionado) {
		this.gastoElectoralIngresoSeleccionado = gastoElectoralIngresoSeleccionado;
	}
}
