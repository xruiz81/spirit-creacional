package com.spirit.cartera.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.popup.JidePopup;
import com.jidesoft.swing.JideButton;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.util.ArrayListTableModel;

public class PopupFinderDocumentosCartera extends JidePopup {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList dataList = new ArrayList();
	Long idCliente,idTipoDocumento;
	String tipoCartera;	
	String nombrePanel;
	Map registrosSeleccionadosMap;
	int modoPanel;
	static final int ITEMS_PER_PAGE = 14;
	private int currentPage;
	private int maxPages;
	private int maxItems;
	private int numeroPaginas = 0;
	private static final int minPages = 1;
	private DecimalFormat formatoDecimal=new DecimalFormat("#,##0.00");
	Map<Long,Map> mapaRegistrosSeleccionadosMap;
	private Long carteraUpdateId;
	
	public PopupFinderDocumentosCartera(String nombre,Long idCliente,Long idTipoDocumento,String tipoCartera,Map registrosSeleccionados,int modo, Map mapaRegistrosSeleccionadosMap, Long carteraUpdateId) {
		try {
			nombrePanel = "Búsqueda de " + nombre;
			this.idCliente = idCliente;
			this.idTipoDocumento = idTipoDocumento;
			this.tipoCartera = tipoCartera;
			this.registrosSeleccionadosMap = registrosSeleccionados;
			this.carteraUpdateId = carteraUpdateId;
			this.mapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap;
			
			modoPanel = modo;
			initComponents();
			iniciarComponentes();
			//Mando a ocultar la tabla a penas se vea la ventana
			panelTable.setVisible(true);
			btnAceptar.setEnabled(true);
			btnIrAnteriorRegistro.setEnabled(false);
			btnIrPrimerosRegistros.setEnabled(false);
			btnIrSiguienteRegistro.setEnabled(false);
			btnIrUltimosRegistros.setEnabled(false);
			//Collection 
			//Obtengo primero todos los registros para luego anotar el numero total de ellos que hay
			if((modoPanel==SpiritMode.UPDATE_MODE) && (carteraUpdateId != null)){
				dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(idCliente,idTipoDocumento,tipoCartera,"+",Parametros.getIdEmpresa(),carteraUpdateId);
			}else{
				dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(idCliente,idTipoDocumento,tipoCartera,"+",Parametros.getIdEmpresa());
			}
			
			//Mando a contar los registros que se deben devolver en la busqueda
			maxItems = dataList.size(); 
			numeroPaginas = dataList.size()/ITEMS_PER_PAGE;
			if (dataList.size()%ITEMS_PER_PAGE== 0)
	    		numeroPaginas--;
			
			initListeners();
			
			//Mando a Mostrar los primeros registros
			buscarPrimerosRegistros();
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void iniciarComponentes(){
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void initListeners() {
		btnIrPrimerosRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarPrimerosRegistros();
			}
		});		
		
		btnIrAnteriorRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarAnterioresRegistros();
			}
		});		
		
		btnIrSiguienteRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarSiguientesRegistros();
			}
		});		
		
		btnIrUltimosRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarUltimosRegistros();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
			}
		});
	}
	
	// Mando a Contruir el Modelo de la Tabla con los datos encontrados
	public void buildTableModel(ArrayList data) {
		ArrayList headers = new ArrayList();
		headers.add("Código Cartera");
		headers.add("Documento");
		headers.add("F. Cartera");
		headers.add("Cart.");
		headers.add("Valor");
		headers.add("Saldo");
		headers.add("Diferido");
		headers.add("Preimpreso");
		headers.add("Referencia");
		TableModel dataModel = new ArrayListTableModel(data, headers);
		//Seteo el modelo de la tabla que se encuentra en el popup
		table.setModel(dataModel);
		//Setea que los valores de estas columnas se ubiquen en el lado derecho de las celdas de la tabla
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		//---- table ----
		table.getColumnModel().getColumn(4).setCellRenderer(dtcrColumn);
		table.getColumnModel().getColumn(5).setCellRenderer(dtcrColumn);
		table.getColumnModel().getColumn(6).setCellRenderer(dtcrColumn);
		
		TableColumn anchoColumna = table.getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = table.getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(140);
		anchoColumna = table.getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = table.getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = table.getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(72);
		anchoColumna = table.getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(72);
		anchoColumna = table.getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(72);
		anchoColumna = table.getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(94);
		anchoColumna = table.getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(90);
	}
	
	public ArrayList getDataForTable(ArrayList list) {
		try {
			ArrayList data = new ArrayList();
			int paginaActual = currentPage; //necesito quela primera pagina sea indice 0
			
			DocumentoIf documento = null;
			if((list.size() > 0)){
				Object[] dataListObject = (Object[])list.get(0);
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) dataListObject[0];
				documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
				if(!registrosSeleccionadosMap.isEmpty()){
					Iterator registrosSeleccionadosMapIt = registrosSeleccionadosMap.keySet().iterator();
					while(registrosSeleccionadosMapIt.hasNext()){
						Integer index = (Integer) registrosSeleccionadosMapIt.next();
						if(registrosSeleccionadosMap.get(index) != null){
							CarteraDetalleIf carteraDetalleComparada = (CarteraDetalleIf)registrosSeleccionadosMap.get(index);
							DocumentoIf documentoComparado = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleComparada.getDocumentoId());
							if(documento.getId().compareTo(documentoComparado.getId()) != 0){
								if(mapaRegistrosSeleccionadosMap.get(documento.getId()) != null){
									registrosSeleccionadosMap = mapaRegistrosSeleccionadosMap.get(documento.getId());
								}else{
									registrosSeleccionadosMap = new HashMap();
								}
							}
							break;
						}
					}
				}
			}
			
			for(int j=0;j<list.size();j++){
				int indiceRealRegistro = j + (paginaActual * ITEMS_PER_PAGE);
				ArrayList fila = new ArrayList();
				Object[] dataListObject = (Object[])list.get(j);
				CarteraDetalleIf bean = (CarteraDetalleIf) dataListObject[0];
				DocumentoIf documentoTemp = SessionServiceLocator.getDocumentoSessionService().getDocumento(bean.getDocumentoId());
				CarteraIf carteraTemp = SessionServiceLocator.getCarteraSessionService().getCartera(bean.getCarteraId());
				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documentoTemp.getTipoDocumentoId());
				
				FacturaIf facturaTemp = null;
				CompraIf compraTemp = null;
				if (carteraTemp.getReferenciaId() != null) {
					if (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAR") || tipoDocumento.getCodigo().equals("FAE") || tipoDocumento.getCodigo().equals("FCO") || tipoDocumento.getCodigo().equals("VTA")){
						facturaTemp = SessionServiceLocator.getFacturaSessionService().getFactura(carteraTemp.getReferenciaId());
					}else if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE")) {
						compraTemp = SessionServiceLocator.getCompraSessionService().getCompra(carteraTemp.getReferenciaId());
					}
				}
				//Seteo el valor de la fecha de cartera
				DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
				String fechaCarteraDetalle = dateFormatter.format(bean.getFechaCartera());
				//Veo si es cartera o no
				String cartera = "";
				if("S".equals(bean.getCartera())){
					cartera = "SI";
				}else{
					cartera = "NO";
				}
				
				fila.add(carteraTemp.getCodigo());
				fila.add(documentoTemp.getNombre());
				fila.add(fechaCarteraDetalle);
				fila.add(cartera);
				fila.add(formatoDecimal.format(bean.getValor().doubleValue()));
				
				//Parte para sacar el valor que tiene diferido el carteraDetallePositivo
				double valorDiferido = 0D;
				//Extraigo todos los cartera afecta al cual ha sido cruzado este cartera detalle (+) y cuyo valor cartera sea igual a N
				Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraAfectaIdAndByCartera(bean.getId(),"N");
				Iterator itCarteraAfectaColeccion = carteraAfectaColeccion.iterator();
				//Sumo el valor de los documentos que han sido diferidos y están cruzando con esta cartera
				while(itCarteraAfectaColeccion.hasNext()){	
					CarteraAfectaIf carteraAfectaIt = (CarteraAfectaIf) itCarteraAfectaColeccion.next();
					//Sumo el valor del cartera afecta
					valorDiferido = valorDiferido + carteraAfectaIt.getValor().doubleValue();
				}
				
				//Parte para sacar el valor que tiene diferido el carteraDetallePositivo, Calculo el saldo
				if (registrosSeleccionadosMap.get(indiceRealRegistro) != null){
					CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) registrosSeleccionadosMap.get(indiceRealRegistro);
					// Veo si coinciden los ids para saber que son documentos del mismo tipo y si el saldo del objeto en memoria es diferente del de la base
					if(carteraDetalleTemp.getId().equals(bean.getId())){
						bean.setSaldo(BigDecimal.valueOf(carteraDetalleTemp.getSaldo().doubleValue()));
					}
				}
				
				if (valorDiferido == 0D){
					registrosSeleccionadosMap.put(indiceRealRegistro,bean);
				}
				
				//Muestro el saldo
				fila.add(formatoDecimal.format(bean.getSaldo().doubleValue()));
				//Muestro del valor diferido
				fila.add(formatoDecimal.format(valorDiferido));
				
				if((facturaTemp != null) && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAR") || tipoDocumento.getCodigo().equals("FAE") || tipoDocumento.getCodigo().equals("FCO") || tipoDocumento.getCodigo().equals("VTA"))){
					fila.add(facturaTemp.getPreimpresoNumero()!=null ? facturaTemp.getPreimpresoNumero():"");
					fila.add("");
				}else if((compraTemp != null) && (tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE"))) {
					fila.add(compraTemp.getPreimpreso()!=null ? compraTemp.getPreimpreso():"");
					fila.add(compraTemp.getReferencia()!=null ? compraTemp.getReferencia():"");
				}else{
					fila.add(carteraTemp.getPreimpreso());
					fila.add("");
				}
				
				data.add(fila);
			}
			if(documento != null){
				mapaRegistrosSeleccionadosMap.put(documento.getId(), registrosSeleccionadosMap);
			}
			return data;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			return null;
		}	
	}
	
	private void buscarRegistros(int startIndex,int endIndex){
		try {
			//Obtengo primero todos los registros para luego anotar el numero total de ellos que hay
			if((modoPanel==SpiritMode.UPDATE_MODE) && (carteraUpdateId != null)){
				dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(idCliente,idTipoDocumento,tipoCartera,"+",startIndex,endIndex, Parametros.getIdEmpresa(),carteraUpdateId);
			}else{
				dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(idCliente,idTipoDocumento,tipoCartera,"+",startIndex,endIndex, Parametros.getIdEmpresa());
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public ArrayList pagina(int page){
		ArrayList data;
		int base = page*ITEMS_PER_PAGE;
		int maximo = maxItems;
		int resultado = ((base+ITEMS_PER_PAGE) <= maximo )? (base+ITEMS_PER_PAGE) : maximo ;
		buscarRegistros(base,resultado);
		data = (ArrayList)getDataForTable(dataList);;
		return data;
	}
	
	public void buscarPrimerosRegistros(){
		print();
		currentPage = 0;
		ArrayList dataTmp;
		
		dataTmp = pagina(currentPage);
		validateButtons();
		buildTableModel(dataTmp);
		print();
	}
	
	public void buscarAnterioresRegistros(){
		print();
		ArrayList dataTmp;
		currentPage--;
		
		dataTmp = pagina(currentPage);
		validateButtons();
		buildTableModel(dataTmp);
		print();
	}
	
	public void buscarSiguientesRegistros(){
		print();
		ArrayList dataTmp;
		currentPage++;
		
		dataTmp = pagina(currentPage);
		validateButtons();
		buildTableModel(dataTmp);
		print();
	}
	
	public void buscarUltimosRegistros(){
		print();
		currentPage = numeroPaginas;
		ArrayList dataTmp;
		
		int tamanoListaResultados = maxItems;
		if (tamanoListaResultados%ITEMS_PER_PAGE == 0)
			dataTmp = pagina((tamanoListaResultados / ITEMS_PER_PAGE) - 1);
		else 
			dataTmp = pagina(tamanoListaResultados/ITEMS_PER_PAGE);
		validateButtons();
		buildTableModel(dataTmp);
		print();
	}
	
	private void validateButtons(){
		if ( numeroPaginas == 0 ){
			getBtnIrAnteriorRegistro().setEnabled(false);
			getBtnIrPrimerosRegistros().setEnabled(false);
			getBtnIrSiguienteRegistro().setEnabled(false);
			getBtnIrUltimosRegistros().setEnabled(false);
		}
		else if ( numeroPaginas>0 ){
			if ( currentPage == numeroPaginas ) {
				getBtnIrAnteriorRegistro().setEnabled(true);
				getBtnIrPrimerosRegistros().setEnabled(true);
				getBtnIrSiguienteRegistro().setEnabled(false);
				getBtnIrUltimosRegistros().setEnabled(false);
			} else if ( currentPage > 0 ){
				getBtnIrAnteriorRegistro().setEnabled(true);
				getBtnIrPrimerosRegistros().setEnabled(true);
				getBtnIrSiguienteRegistro().setEnabled(true);
				getBtnIrUltimosRegistros().setEnabled(true);
			} else if ( currentPage==0 ){
				getBtnIrAnteriorRegistro().setEnabled(false);
				getBtnIrPrimerosRegistros().setEnabled(false);
				getBtnIrSiguienteRegistro().setEnabled(true);
				getBtnIrUltimosRegistros().setEnabled(true);
			}
		}
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		contentPane = new JPanel();
		panelBarraTitulo = new JPanel();
		btnIrPrimerosRegistros = new JideButton();
		btnIrAnteriorRegistro = new JideButton();
		btnIrSiguienteRegistro = new JideButton();
		btnIrUltimosRegistros = new JideButton();
		lblTitulo = new JLabel();
		btnAceptar = new JideButton();
		goodiesFormsSeparator = compFactory.createSeparator("");
		panelTable = new JScrollPane();
		table = new JTable();
		CellConstraints cc = new CellConstraints();
		
		//======== this ========
		setResizable(false);
		setMovable(true);
		setLayout(new FormLayout(
				"560dlu:grow",
		"fill:181dlu:grow"));
		
		//======== contentPane ========
		{
			contentPane.setLayout(new FormLayout(
					"default:grow",
			"fill:pref, default, default, default"));
			
			//======== panelBarraTitulo ========
			{
				panelBarraTitulo.setLayout(new FormLayout(
						"default, default, default, default, default, default:grow, default",
				"fill:default"));
				
				//---- btnIrPrimerosRegistros ----
				btnIrPrimerosRegistros.setToolTipText("Ver primeros 15 registros");
				btnIrPrimerosRegistros.setOpaque(false);
				btnIrPrimerosRegistros.setBackground(UIManager.getColor("Button.light"));
				btnIrPrimerosRegistros.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goFirstRecords.png"));
				panelBarraTitulo.add(btnIrPrimerosRegistros, cc.xy(2, 1));
				
				//---- btnIrAnteriorRegistro ----
				btnIrAnteriorRegistro.setToolTipText("Ver 15 registros previos");
				btnIrAnteriorRegistro.setOpaque(false);
				btnIrAnteriorRegistro.setBackground(UIManager.getColor("Button.light"));
				btnIrAnteriorRegistro.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goPreviousRecord.png"));
				panelBarraTitulo.add(btnIrAnteriorRegistro, cc.xy(3, 1));
				
				//---- btnIrSiguienteRegistro ----
				btnIrSiguienteRegistro.setToolTipText("Ver 15 siguientes registros");
				btnIrSiguienteRegistro.setOpaque(false);
				btnIrSiguienteRegistro.setBackground(UIManager.getColor("Button.light"));
				btnIrSiguienteRegistro.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goNextRecord.png"));
				panelBarraTitulo.add(btnIrSiguienteRegistro, cc.xy(4, 1));
				
				//---- btnIrUltimosRegistros ----
				btnIrUltimosRegistros.setToolTipText("Mostrar ultimos 15 Registros");
				btnIrUltimosRegistros.setOpaque(false);
				btnIrUltimosRegistros.setBackground(UIManager.getColor("Button.light"));
				btnIrUltimosRegistros.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goLastRecords.png"));
				panelBarraTitulo.add(btnIrUltimosRegistros, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- lblTitulo ----
				lblTitulo.setText(nombrePanel);
				lblTitulo.setBackground(UIManager.getColor("Button.light"));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setOpaque(true);
				panelBarraTitulo.add(lblTitulo, cc.xy(6, 1));
				
				//---- btnAceptar ----
				btnAceptar.setBackground(UIManager.getColor("Button.light"));
				btnAceptar.setToolTipText("Aceptar");
				btnAceptar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/selectElement.png"));
				panelBarraTitulo.add(btnAceptar, cc.xy(7, 1));
			}
			
			//======== panelTable ========
			{
				//---- table ----
				table.setColumnSelectionAllowed(false);				
				panelTable.setViewportView(table);
			}
			
			contentPane.add(panelBarraTitulo, cc.xy(1, 1));
			contentPane.add(panelTable, cc.xy(1, 3));
			contentPane.add(goodiesFormsSeparator, cc.xywh(1, 2, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		}
		add(contentPane, cc.xy(1, 1));
		setDefaultFocusComponent(contentPane);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel contentPane;
	private JPanel panelBarraTitulo;
	private JideButton btnIrPrimerosRegistros;
	private JideButton btnIrAnteriorRegistro;
	private JideButton btnIrSiguienteRegistro;
	private JideButton btnIrUltimosRegistros;
	private JLabel lblTitulo;
	private JideButton btnAceptar;
	private JComponent goodiesFormsSeparator;
	private JScrollPane panelTable;
	private JTable table;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public void setBtnAceptar(JideButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}
	
	public JideButton getBtnIrAnteriorRegistro() {
		return btnIrAnteriorRegistro;
	}
	
	public void setBtnIrAnteriorRegistro(JideButton btnIrAnteriorRegistro) {
		this.btnIrAnteriorRegistro = btnIrAnteriorRegistro;
	}
	
	public JideButton getBtnIrPrimerosRegistros() {
		return btnIrPrimerosRegistros;
	}
	
	public void setBtnIrPrimerosRegistros(JideButton btnIrPrimerosRegistros) {
		this.btnIrPrimerosRegistros = btnIrPrimerosRegistros;
	}
	
	public JideButton getBtnIrSiguienteRegistro() {
		return btnIrSiguienteRegistro;
	}
	
	public void setBtnIrSiguienteRegistro(JideButton btnIrSiguienteRegistro) {
		this.btnIrSiguienteRegistro = btnIrSiguienteRegistro;
	}
	
	public JideButton getBtnIrUltimosRegistros() {
		return btnIrUltimosRegistros;
	}
	
	public void setBtnIrUltimosRegistros(JideButton btnIrUltimosRegistros) {
		this.btnIrUltimosRegistros = btnIrUltimosRegistros;
	}
	
	private void print() {
		System.out.println("maxItems" + maxItems);
		System.out.println("maxPages" + maxPages);
		System.out.println("currentPage" + currentPage);
	}
	
	public Map getRegistrosSeleccionadosMap() {
		return registrosSeleccionadosMap;
	}
	
	public void setRegistrosSeleccionadosMap(Map registrosSeleccionados) {
		this.registrosSeleccionadosMap = registrosSeleccionados;
	}
	
	public ArrayList getDataList() {
		return dataList;
	}
	
	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public static int getITEMS_PER_PAGE() {
		return ITEMS_PER_PAGE;
	}
}
