package com.spirit.cartera.gui.panel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.collections.map.LinkedMap;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.cartera.entity.CarteraAfectaData;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.controller.PopupFinderDocumentosCartera;
import com.spirit.cartera.gui.model.CarteraModel;
import com.spirit.client.ActivePanel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.CruceDocumentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class JDAfectaCartera extends JDialog  implements MouseListener  {
	private static final long serialVersionUID = -6478801346852001153L;
	PopupFinderDocumentosCartera pfDocCar;
	Long idCliente;
	String tipoCartera;
	JPopupMenu  popupMenuAfectaCartera = new JPopupMenu();
	CarteraAfectaIf carteraAfectaIf;
	//Mapa que contiene todos lso registros EN GENERAL que han sido afectados con el cruce de documentos 
	Map registrosAfectaMap;
	//Vector que contiene solo los documentos que han sido o estan siendo cruzados con el detalle que me encuentro
	Vector registrosDetallesToDetalleVector;
	Vector registrosAfectaToDetalleVector;
	int registrosAfectaEnBase = 0;
	int modo;
	double saldoDetalleCartera; 
	DefaultTableModel modelAfectaCartera;
	TipoDocumentoIf tipoDocumentoIf; 
	JDAfectaCartera popupAfectaCartera = this;
	int filaSeleccionadaTablaAfecta = -1;
	//Cambio el formato de la fecha para insertarlo a la base
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
	DecimalFormat formatoDecimal=new DecimalFormat("#,##0.00");
	private static final int MAX_LONGITUD_VALORES = 22;
	
	Map<Long,Map> mapaRegistrosSeleccionadosMap;
	Map<Long,Map> mapaRegistrosSeleccionadosMapTemp;
	Map registrosAfectaMapTemp;
	
	private Vector<CarteraAfectaIf> carteraAfectaEliminarColeccion;
	private Vector<CarteraIf> carteraActualizadaColeccion;
	private Long carteraUpdateId;
	
	public JDAfectaCartera(Frame owner,Map camposDetalle,final Long idCliente,String tipoCartera,Map registrosAfectaMap,Vector registrosDetallesToDetalleVector,Vector registrosAfectaToDetalleVector,int modo,Map mapaRegistrosSeleccionadosMap,Map mapaRegistrosSeleccionadosMapTemp, Map registrosAfectaMapTemp,
			Vector<CarteraAfectaIf> carteraAfectaEliminarColeccion,Vector<CarteraIf> carteraActualizadaColeccion, Long carteraUpdateId) {
		super(owner);
		initComponents();
		iniciarComponentes();
		setName("Afecta Cartera");
		initKeyListeners();
		this.idCliente = idCliente;
		this.tipoCartera = tipoCartera;
		this.registrosAfectaMap = registrosAfectaMap;
		this.carteraUpdateId = carteraUpdateId;
		
		this.mapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap;
		this.mapaRegistrosSeleccionadosMapTemp = mapaRegistrosSeleccionadosMapTemp;
		this.registrosAfectaMapTemp = registrosAfectaMapTemp;
		this.carteraAfectaEliminarColeccion = carteraAfectaEliminarColeccion;
		this.carteraActualizadaColeccion = carteraActualizadaColeccion;
		
		this.registrosDetallesToDetalleVector = registrosDetallesToDetalleVector;
		this.registrosAfectaToDetalleVector = registrosAfectaToDetalleVector;
		this.registrosAfectaEnBase = registrosAfectaToDetalleVector.size();
		this.modo = modo;
		
		//Seteo la propiedad enabled de los campos
		txtBancoDetalle.setEditable(false);
		txtCarteraDetalle.setEditable(false);
		txtDocumentoDetalle.setEditable(false);
		txtFechaCarteraDetalle.setEditable(false);
		txtFechaCreacionDetalle.setEditable(false);
		txtFechaVencimientoDetalle.setEditable(false);
		txtTipoDocumentoDetalle.setEditable(false);
		txtValorDetalle.setEditable(false);
		txtSaldoDetalle.setEditable(false);
		txtTipoPagoDetalle.setEditable(false);
		txtCarteraAfecta.setEditable(false);
		txtCodigoAfecta.setEditable(false);
		txtFechaAplicacionAfecta.setEditable(false);
		txtFechaAplicacionAfecta.setText(Utilitarios.dateNow());
		txtFechaCreacionAfecta.setEditable(false);
		//txtValorAfecta.setEditable(false);
		txtValorAfecta.setEditable(true);
		txtDocumentoAfecta.setEditable(false);
		cmbTipoDocumentoAfecta.setEnabled(true);
		//lleno los campos del detalle recibido
		txtCarteraDetalle.setText(camposDetalle.get("cartera").toString());
		txtDocumentoDetalle.setText(camposDetalle.get("documento").toString());
		txtFechaCarteraDetalle.setText(camposDetalle.get("fechaCartera").toString());
		txtFechaCreacionDetalle.setText(camposDetalle.get("fechaCreacion").toString());
		txtFechaVencimientoDetalle.setText(camposDetalle.get("fechaVencimiento").toString());
		txtTipoDocumentoDetalle.setText(camposDetalle.get("tipoDocumento").toString());
		txtValorDetalle.setText(formatoDecimal.format(camposDetalle.get("valor")));
		txtSaldoDetalle.setText(formatoDecimal.format(camposDetalle.get("saldo")));
		txtTipoPagoDetalle.setText(camposDetalle.get("tipoPago").toString());
		txtCodigoAfecta.grabFocus();
		
		if (camposDetalle.get("banco") != null){
			txtBancoDetalle.setText(camposDetalle.get("banco").toString());
		}
		 
		saldoDetalleCartera = (Double) camposDetalle.get("saldo");
		
		//Mando a cargar los documentos que han sido cruzados con el detalle
		mostrarDocumentosAfectaPorDetalle();
		//Mando a cargar los listeners de los componentes
		cargarListenersComponents();
		
		try { 
			DocumentoIf documento = (DocumentoIf)camposDetalle.get("documento");
			Collection<CruceDocumentoIf> collectionCruceDocumento = null;
			List<TipoDocumentoIf> collectionTipoDocumento = new ArrayList();
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
			if(tipoDocumento.getSignocartera().equals("-")){
				DocumentoIf documentoSeleccionado = (DocumentoIf)camposDetalle.get("documento");
				collectionCruceDocumento = SessionServiceLocator.getCruceDocumentoSessionService().findCruceDocumentoByDocumentoId(documento.getId());
				for(CruceDocumentoIf cruceDocumento : collectionCruceDocumento){
					documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(cruceDocumento.getDocumentoaplId());
					tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
					//Validar que si el documento seleccionado en el detalle es un tipo de retencion, no aparezca factura de reembolso.
					if ( !((documentoSeleccionado.getCodigo().equals("RERC") || documentoSeleccionado.getCodigo().equals("REIC")) && tipoDocumento.getCodigo().equals("FAR")) )
						collectionTipoDocumento.add(tipoDocumento);							
				}
			}else if(tipoDocumento.getSignocartera().equals("+")){
				collectionCruceDocumento = SessionServiceLocator.getCruceDocumentoSessionService().findCruceDocumentoByDocumentoaplId(documento.getId());
				for(CruceDocumentoIf cruceDocumento : collectionCruceDocumento){
					documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(cruceDocumento.getDocumentoId());
					tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
					collectionTipoDocumento.add(tipoDocumento);						
				}
			}
			collectionTipoDocumento = removerTipoDocumentoRepetido(collectionTipoDocumento);
			if(!collectionTipoDocumento.isEmpty()){
				SpiritComboBoxModel cmbModelTipoDocumentoAfecta = new SpiritComboBoxModel(collectionTipoDocumento);
				cmbTipoDocumentoAfecta.setModel(cmbModelTipoDocumentoAfecta);
				if(cmbTipoDocumentoAfecta.getItemCount()>0){
					cmbTipoDocumentoAfecta.setSelectedIndex(0);
				}
				tipoDocumentoIf = (TipoDocumentoIf) cmbTipoDocumentoAfecta.getModel().getElementAt(0);
			}else{
				SpiritAlert.createAlert("Debe registrar en la pantalla Cruce Documento, los posibles cruces del Documento seleccionado!", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error, revisar si falta un registro en la tabla Cruce Documento", SpiritAlert.ERROR);
		}
		
		//cargo sólo la primera vez el mapa de mapas de las carteras detalle del proveedor
		if(mapaRegistrosSeleccionadosMap.isEmpty()){	
			cargarMapaRegistrosSeleccionadosMap();
		}		
        
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(getThisDialog(), "¿Desea guardar los cambios?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
    			CarteraModel panel = (CarteraModel) ActivePanel.getSingleton().getActivePanel();
    			if(opcion == JOptionPane.YES_OPTION) {
    				panel.guardarDatosAfectaCartera();
    			} else {
    				while (tblAfecta.getRowCount() > registrosAfectaEnBase) {
    					carteraAfectaIf = (CarteraAfectaIf) getRegistrosAfectaToDetalleVector().get(0+registrosAfectaEnBase);
    					removerItemAfectaCartera(0+registrosAfectaEnBase);
    				}
    				panel.resetearMapaRegistrosSeleccionadosMap();
    				panel.resetearRegistrosDetallesToDetalleVector();
    				panel.resetearRegistrosDetallesToDetalleCarteraMapTemp();
    				panel.resetearRegistrosAfectaMap();
    				panel.resetearRegistrosAfectaToDetalleCarteraVector();
    				panel.resetearRegistrosAfectaToDetalleCarteraMapTemp();
    			}
				
		    	((JDAfectaCartera) e.getSource()).dispose();
		    }
		});
	}
	
	private List<TipoDocumentoIf> removerTipoDocumentoRepetido(List<TipoDocumentoIf> collectionTipoDocumento){
		Map tipoDocumentoMap = new LinkedMap();
		for(TipoDocumentoIf tipoDocumentoTemp : collectionTipoDocumento){
			tipoDocumentoMap.put(tipoDocumentoTemp.getId(), tipoDocumentoTemp);
		}
		collectionTipoDocumento.clear();
		Iterator tipoDocumentoMapIt = tipoDocumentoMap.keySet().iterator();
		while(tipoDocumentoMapIt.hasNext()){
			Long tipoDocumentoKey = (Long)tipoDocumentoMapIt.next();
			collectionTipoDocumento.add((TipoDocumentoIf)tipoDocumentoMap.get(tipoDocumentoKey));
		}
		return collectionTipoDocumento;
	}
	
	private void iniciarComponentes(){
		tblAfecta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void initKeyListeners() {
		txtValorAfecta.addKeyListener(new TextChecker(MAX_LONGITUD_VALORES));
		txtValorAfecta.addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void mostrarDocumentosAfectaPorDetalle(){
		try{
			//Recorro los documentos que estan siendo cruzados con el detalle
			for(int j=0;j<registrosDetallesToDetalleVector.size();j++){
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) registrosDetallesToDetalleVector.get(j);
				//Bandera que me indica si el cartera detalle esta en el mapa o no
				boolean isCarteraDetalleInMemory = false;
				//Variable que carga el cartera detalle ya sea del mapa p de la base y compara sus aldo con el cartera detalle de la coleccion
				CarteraDetalleIf carteraDetalleTemp = null;
				//Parte para sacar el valor que tiene diferido el carteraDetallePositivo
				BigDecimal valorDiferido = new BigDecimal(0.00);
				//Extraigo todos los cartera afecta al cual ha sido cruzado este cartera detalle (+) y cuyo valor cartera sea igual a N
				Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraAfectaIdAndByCartera(carteraDetalle.getId(),"N");
				Iterator itCarteraAfectaColeccion = carteraAfectaColeccion.iterator();
				//Sumo el valor de los documentos que han sido diferidos y estan cruzando con esta cartera
				while(itCarteraAfectaColeccion.hasNext()){	
					CarteraAfectaIf carteraAfectaIt = (CarteraAfectaIf) itCarteraAfectaColeccion.next();
					//Sumo el valor del cartera afecta
					valorDiferido = valorDiferido.add(carteraAfectaIt.getValor());
				}
				
				boolean existeMapaAfecta = false;
				Iterator iteratorMapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap.keySet().iterator();
				while(iteratorMapaRegistrosSeleccionadosMap.hasNext() && !existeMapaAfecta) {
					Long index = (Long) iteratorMapaRegistrosSeleccionadosMap.next();
					registrosAfectaMap = (Map)mapaRegistrosSeleccionadosMap.get(index);
					//Parte para sacar el valor que tiene diferido el carteraDetallePositivo
					Iterator itKeysRegistrosAfectaMap = registrosAfectaMap.keySet().iterator();
					//Recorro las filas seleccionadas dela ventana de busqueda para encontrar el cartera detalle el cual voya  insertar en la tabla
					while(itKeysRegistrosAfectaMap.hasNext()){
						Integer indice = (Integer) itKeysRegistrosAfectaMap.next();
						//Extraigo del arreglo leido de la base el Cartera Detalle escogido de los documentos cartera
						carteraDetalleTemp = (CarteraDetalleIf)  registrosAfectaMap.get(indice);
						//Si el id del carteraDetalle que stoy actualizando de la tabla es igual al encontrado 
						if(carteraDetalle.getId().equals(carteraDetalleTemp.getId())){
							//Mando a setear el saldo del cartera detalle que voy a insertar en la tabla
							carteraDetalle.setSaldo(carteraDetalleTemp.getSaldo());
							//Guardo en la coleccion el registro con el saldo real que deberia tener
							registrosDetallesToDetalleVector.set(j,carteraDetalle);
							//Digo que el cartera detalle esta en el mapa
							isCarteraDetalleInMemory = true;
							existeMapaAfecta = true;
							break;
						}
					}				
				}
				
				//Veo si es modo update y si el saldo del detalle cartera esta diferiendo al que tiene en la base
				carteraDetalleTemp = (CarteraDetalleIf) SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraDetalle.getId());
				if(modo == SpiritMode.UPDATE_MODE && isCarteraDetalleInMemory==false && valorDiferido.equals(new BigDecimal(0.00))){
					//Reemplazo el saldo del cartera detalle de memoria con el del cartera detalle de la base
					carteraDetalle.setSaldo(carteraDetalleTemp.getSaldo());
					//Guardo en la coleccion el registro con el saldo real que deberia tener
					registrosDetallesToDetalleVector.set(j,carteraDetalle);
				}
				
				DocumentoIf documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
				CarteraIf cartera = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalle.getCarteraId());
				//Veo si es cartera o no
				String esCartera = "";
				if ("S".equals(carteraDetalle.getCartera())) 
					esCartera = "SI";
				else 
					esCartera = "NO";

				String fechaCreacionTemp = dateFormatter.format(carteraDetalle.getFechaCreacion());				
				// Extraigo el modelo de la tabla donde voy a guardar los resgitrso seleccionado
				modelAfectaCartera =  (DefaultTableModel) tblAfecta.getModel();
				// Creo la fila a ingresar en la tabla
				Vector<Object> filaAfectaCartera = new Vector<Object>();
				filaAfectaCartera.add(documento.getNombre());
				filaAfectaCartera.add(cartera.getCodigo());
				filaAfectaCartera.add(esCartera);
				filaAfectaCartera.add(fechaCreacionTemp);
				filaAfectaCartera.add(txtFechaAplicacionAfecta.getText());
				filaAfectaCartera.add(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
				filaAfectaCartera.add(formatoDecimal.format(carteraDetalle.getSaldo().doubleValue()));
				
				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
				FacturaIf facturaTemp = null;
				CompraIf compraTemp = null;
				if (cartera.getReferenciaId() != null) {
					if(tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAR") || tipoDocumento.getCodigo().equals("FAE") || tipoDocumento.getCodigo().equals("FCO") || tipoDocumento.getCodigo().equals("VTA")){
						facturaTemp = SessionServiceLocator.getFacturaSessionService().getFactura(cartera.getReferenciaId());
					}else if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI")){
						compraTemp = SessionServiceLocator.getCompraSessionService().getCompra(cartera.getReferenciaId());
					}
				}
				
				if((facturaTemp != null) && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAR") || tipoDocumento.getCodigo().equals("FAE") || tipoDocumento.getCodigo().equals("FCO") || tipoDocumento.getCodigo().equals("VTA"))){
					filaAfectaCartera.add((facturaTemp != null && facturaTemp.getPreimpresoNumero()!=null) ? facturaTemp.getPreimpresoNumero():cartera.getPreimpreso());
				}else if((compraTemp != null) && (tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI"))){
					filaAfectaCartera.add((compraTemp != null && compraTemp.getPreimpreso()!=null) ? compraTemp.getPreimpreso():cartera.getPreimpreso());
				}else{
					filaAfectaCartera.add(cartera.getPreimpreso());
				}
				
				// Inserto la fila en la tabla
				modelAfectaCartera.addRow(filaAfectaCartera);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void setearRegistrosAfectaMap(CarteraDetalleIf carteraDetalle) {
		boolean existeMapaAfecta = false;
		Iterator iteratorMapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap.keySet().iterator();
		while(iteratorMapaRegistrosSeleccionadosMap.hasNext() && !existeMapaAfecta) {
			Long index = (Long) iteratorMapaRegistrosSeleccionadosMap.next();
			Map registrosAfectaMapTemp = (Map)mapaRegistrosSeleccionadosMap.get(index);
			Iterator itKeysRegistrosAfectaMap = registrosAfectaMapTemp.keySet().iterator();	
			while(itKeysRegistrosAfectaMap.hasNext()){
				Integer indice = (Integer) itKeysRegistrosAfectaMap.next();
				CarteraDetalleIf carteraDetalleTemporal = (CarteraDetalleIf)  registrosAfectaMapTemp.get(indice);
				if(carteraDetalle.getId().equals(carteraDetalleTemporal.getId())){
					registrosAfectaMap = registrosAfectaMapTemp;
					existeMapaAfecta = true;
					break;
				}
			}
		}
	}
	
	public void cargarMapaRegistrosSeleccionadosMap(){
		try {
			ArrayList dataList = new ArrayList();
			for(int i=0; i<cmbTipoDocumentoAfecta.getItemCount(); i++){
				TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf) cmbTipoDocumentoAfecta.getItemAt(i);
				
				if((modo==SpiritMode.UPDATE_MODE) && (carteraUpdateId != null)){
					dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(idCliente,tipoDocumentoIf.getId(),tipoCartera,"+",Parametros.getIdEmpresa(),carteraUpdateId);
				}else{
					dataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(idCliente,tipoDocumentoIf.getId(),tipoCartera,"+",Parametros.getIdEmpresa());
				}
				
				Map registrosAfectaMapTemp = new HashMap();
				DocumentoIf documento = null;
				
				for(int j=0;j<dataList.size();j++){
					Object[] dataListObject = (Object[])dataList.get(j);
					CarteraDetalleIf bean = (CarteraDetalleIf) dataListObject[0];
					documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(bean.getDocumentoId());
					registrosAfectaMapTemp.put(j,bean);
				}
				
				if(documento != null){
					mapaRegistrosSeleccionadosMap.put(documento.getId(), registrosAfectaMapTemp);
				}
			}
			System.out.println("");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	PopupMenuListener popUpListener = new PopupMenuListener(){
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			pfDocCar = null;
			System.gc();
		}
		public void popupMenuCanceled(PopupMenuEvent e) {}
	};
	
	private void cargarListenersComponents(){
		cmbTipoDocumentoAfecta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoDocumentoIf = (TipoDocumentoIf) cmbTipoDocumentoAfecta.getModel().getSelectedItem();
			}
		});		         
		
		btnBuscarDocumentosAfecta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				pfDocCar = null;
				pfDocCar = new PopupFinderDocumentosCartera("Documentos de Cartera",idCliente,tipoDocumentoIf.getId(),tipoCartera,registrosAfectaMap,modo,mapaRegistrosSeleccionadosMap,carteraUpdateId); 
				pfDocCar.setOwner(btnBuscarDocumentosAfecta);
				pfDocCar.setAttachable(false);
				pfDocCar.addPopupMenuListener(popUpListener);
								
				mapaRegistrosSeleccionadosMapTemp = mapaRegistrosSeleccionadosMap;
				registrosAfectaMapTemp = registrosAfectaMap;
				
				pfDocCar.showPopup((Toolkit.getDefaultToolkit().getScreenSize().width - 740) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 450) / 2);
                
                pfDocCar.getBtnAceptar().addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent evento) {
        				try {
        					//mando a cargar los registros selecionados en la pagina de la ventana de busqueda!
            				int[] filaSeleccionada = pfDocCar.getTable().getSelectedRows();
            				//Variable para saber en que pagina  estan los regsitros que estoy seleccioando
            				int paginaActual = pfDocCar.getCurrentPage(); //necesito quela primera pagina sea indice 0
            				//Recorro las filas seleccionadas
            				for (int i=0;i<filaSeleccionada.length;i++) {
            					int indiceRealRegistro = filaSeleccionada[i] + (paginaActual * pfDocCar.getITEMS_PER_PAGE());
            					/*if (paginaActual > 0)
            						 indiceRealRegistro -= pfDocCar.getITEMS_PER_PAGE();*/
            					//Extraigo del arreglo leido de la base el Cartera Detalle escogido de los documentos cartera
            					Object[] dataListObject = (Object[])pfDocCar.getDataList().get(filaSeleccionada[i]);
            					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) dataListObject[0];
            					//Extraigo el saldo de Cartera Afecta 
            					double saldoCarteraAfecta = carteraDetalle.getSaldo().doubleValue();

            					if(pfDocCar.getRegistrosSeleccionadosMap().get(indiceRealRegistro)!=null){
            						saldoCarteraAfecta = ((CarteraDetalleIf) pfDocCar.getRegistrosSeleccionadosMap().get(indiceRealRegistro)).getSaldo().doubleValue();
            					}
            					
            					Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraafectaId(carteraDetalle.getId());
            					Iterator carteraAfectaColeccionIt = carteraAfectaColeccion.iterator();
            					BigDecimal valorAfecta = BigDecimal.ZERO;
            					while(carteraAfectaColeccionIt.hasNext()){
            						CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carteraAfectaColeccionIt.next();
            						CarteraDetalleIf carteraDetalleTemp = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfecta.getCarteradetalleId());
            						if(carteraDetalleTemp.getCartera().equals("N")){
            							saldoCarteraAfecta = Utilitarios.redondeoValor(saldoCarteraAfecta - carteraAfecta.getValor().doubleValue());
            							valorAfecta = valorAfecta.add(carteraAfecta.getValor());
            						}
            					}
            					
            					//Pongo en el mapa el registro seleccionado de todo el arreglo leido de la base 
            					if (saldoCarteraAfecta > 0D) {
            						double valorCarteraAfecta = 0D;
                					//Primero antes que agregar un registro del cartera afecta saco la diferencia entre los saldos del cruce del documento
            						if (saldoCarteraAfecta > saldoDetalleCartera) {
            							valorCarteraAfecta = saldoDetalleCartera;
            							//saldoCarteraAfecta = saldoCarteraAfecta - saldoDetalleCartera;
            							saldoDetalleCartera = 0D;
            						} else {
            							valorCarteraAfecta = saldoCarteraAfecta;
            							saldoDetalleCartera = saldoDetalleCartera - saldoCarteraAfecta;
            						}

    	        					if (!txtSaldoDetalle.getText().equals("0.00")) {
    	        						//Variable que me indica en que fila se encuentra el resgitro que estoy quriendo isnertar si es que ya esta existe en la tabla
    	        						int filaRegistroEncontrado = -1;
    	        						//Recorro la coleccion de los documentos que ya estan en la tabla para ver si el que voy a agregar no esta ya insertado
    	                				for(int j=0;j<registrosDetallesToDetalleVector.size();j++){
    	                					//Extraigo del arreglo leido de la base el Cartera Detalle escogido de los documentos cartera
    	                					CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) registrosDetallesToDetalleVector.get(j);
    	                					//Si el id del carteraDetalle que estoy insertando en la tabla es igual a uno de los que estan en la tabla 
    	                					if(carteraDetalle.getId().equals(carteraDetalleTemp.getId())){
    	                						filaRegistroEncontrado = j;
    	                						break;
    	                					}
    	                				}
    	        						   	        						
    	        						DocumentoIf documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
        	        					CarteraIf cartera = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalle.getCarteraId());
        	        					//Veo si es cartera o no
                						String esCartera = "";
                						if("S".equals(carteraDetalle.getCartera())){
                							esCartera = "SI";
                						}else{
                							esCartera = "NO";
                						}
            		    				String fechaCreacionTemp = dateFormatter.format(carteraDetalle.getFechaCreacion());
            		    				//Extraigo el modelo de la tabla donde voy a guardar los resgitrso seleccionado
                						modelAfectaCartera =  (DefaultTableModel) tblAfecta.getModel();
                						//Creo la fila a ingresar en la tabla
                						Vector<Object> filaAfectaCartera = new Vector<Object>();
                						filaAfectaCartera.add(documento.getNombre());
                						filaAfectaCartera.add(cartera.getCodigo());
                						filaAfectaCartera.add(esCartera);
                						filaAfectaCartera.add(fechaCreacionTemp);
                						filaAfectaCartera.add(txtFechaAplicacionAfecta.getText());
                						filaAfectaCartera.add(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
                						                						
                						if (saldoDetalleCartera < 0D) {
                    						//filaAfectaCartera.add(formatoDecimal.format(0D));
                    						filaAfectaCartera.add(formatoDecimal.format(valorAfecta));
                    						//Cambio el valor del Saldo del Detalle
            	        					txtSaldoDetalle.setText(formatoDecimal.format(0D));
                    						//Seteo el valor del saldo del Afecta en 0
                    						carteraDetalle.setSaldo(new BigDecimal(0.00));
                    						//Seteo en  valor en la tabla de busqueda el saldo del detalle
                    						pfDocCar.getTable().getModel().setValueAt(formatoDecimal.format(valorAfecta),filaSeleccionada[i],5);
                						} else if (saldoDetalleCartera >= 0D){
                							double saldoDetalle = Double.valueOf(Utilitarios.removeDecimalFormat(txtSaldoDetalle.getText()));
            	        					//Seteo el valor del saldo del Afecta
            	        					double diferenciaSaldo = saldoCarteraAfecta - saldoDetalle;
            	        					
            	        					if (diferenciaSaldo < 0D) {
            	        						filaAfectaCartera.add(formatoDecimal.format(0D));
            	        						//filaAfectaCartera.add(formatoDecimal.format(valorAfecta));
            	        						txtSaldoDetalle.setText(formatoDecimal.format(saldoDetalle - saldoCarteraAfecta));
            	        						valorCarteraAfecta = saldoCarteraAfecta;
            	        						carteraDetalle.setSaldo(BigDecimal.valueOf(0D));            	        							            	        						
            	        						//pfDocCar.getTable().getModel().setValueAt(formatoDecimal.format(0D),filaSeleccionada[i],5);
            	        						pfDocCar.getTable().getModel().setValueAt(formatoDecimal.format(valorAfecta),filaSeleccionada[i],5);
            	        						saldoDetalleCartera = saldoDetalle - saldoCarteraAfecta;
            	        					} else {
                    							filaAfectaCartera.add(formatoDecimal.format(diferenciaSaldo));
                    							txtSaldoDetalle.setText(formatoDecimal.format(0D));
                    							valorCarteraAfecta = saldoDetalle;
            	        						carteraDetalle.setSaldo(BigDecimal.valueOf(diferenciaSaldo));
            	        						//Seteo en  valor en la tabla de busqueda el saldo del detalle, calculo primero el total con el diferido
            	        						double totalSaldo = diferenciaSaldo + valorAfecta.doubleValue();
            	        						pfDocCar.getTable().getModel().setValueAt(formatoDecimal.format(totalSaldo),filaSeleccionada[i],5);
            	        						saldoDetalleCartera = 0D;
                	        					//Cambio el valor del Saldo del Detalle
            	        					}
                						}
                						
                						String preimpreso = (String)pfDocCar.getTable().getModel().getValueAt(filaSeleccionada[i], 7) ;
                						preimpreso = ( preimpreso!=null && !preimpreso.equals("")) ? preimpreso : cartera.getPreimpreso();
                						filaAfectaCartera.add(preimpreso);
                						                						
                						//le sumo el valor diferido
                						carteraDetalle.setSaldo(carteraDetalle.getSaldo().add(valorAfecta));
                					                						
                						//Veo si el registro a insertar ya esta en la tabla
                						if(filaRegistroEncontrado==-1){
                							//Inserto la fila en la tabla
                							modelAfectaCartera.addRow(filaAfectaCartera);
                    						//Guardo en el arreglo el registro seleccionado
                    						registrosDetallesToDetalleVector.add(carteraDetalle);                						
        	        						//Creo el objeto de CarterAFecta que va a ser guardado en la base
        	            					CarteraAfectaData carteraAfectaTemp = new CarteraAfectaData(); 
         	            					//Seteo los valores para este objeto
        	            					carteraAfectaTemp.setCarteraafectaId(carteraDetalle.getId());
        	            					carteraAfectaTemp.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
        	            					carteraAfectaTemp.setValor(BigDecimal.valueOf(valorCarteraAfecta));
        	            					carteraAfectaTemp.setFechaCreacion(carteraDetalle.getFechaCreacion());
        	            					carteraAfectaTemp.setFechaAplicacion(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        	            					carteraAfectaTemp.setCartera(carteraDetalle.getCartera());
                    						//Guardo en el arreglo el registro seleccionado
                    						registrosAfectaToDetalleVector.add(carteraAfectaTemp);
                						} else {
                    						//Seteo en  saldo en la tabla de busqueda del saldo del detalle escogido en cero
                							modelAfectaCartera.setValueAt(formatoDecimal.format(carteraDetalle.getSaldo().doubleValue()),filaRegistroEncontrado,6);
                							//Actualizo en el arreglo el registro seleccionado
                    						registrosDetallesToDetalleVector.set(filaRegistroEncontrado,carteraDetalle);
                    			           	//Seteo el objeto CarteraAfecta segun el registro seleccionado
                    						CarteraAfectaIf carteraAfectaTemp  = (CarteraAfectaIf) registrosAfectaToDetalleVector.get(filaRegistroEncontrado);
                    						//Seteo el nuevo valor para la cartera afecta agergandole el valor que tenia anteriormente
                    						//carteraAfectaTemp.setValor(carteraAfectaTemp.getValor().subtract(saldoCarteraAfecta));
                    						carteraAfectaTemp.setValor(BigDecimal.valueOf(carteraAfectaTemp.getValor().doubleValue() + valorCarteraAfecta));
                    						//Actualizo en el arreglo el registro seleccionado
                    						registrosAfectaToDetalleVector.set(filaRegistroEncontrado,carteraAfectaTemp);
                						}                						
                						
                						//Guardo en el mapa el registro seleccionado
                						pfDocCar.getRegistrosSeleccionadosMap().put(indiceRealRegistro,carteraDetalle);
                					} else {
    	        						SpiritAlert.createAlert("No se puede cruzar el documento, el saldo del detalle es cero!", SpiritAlert.WARNING);
    	        						saldoDetalleCartera = 0D;
    	        					}
            					} else{
            						SpiritAlert.createAlert("Ya no es posible cruzar el documento, su saldo es cero!", SpiritAlert.WARNING);
            					}
            					setearRegistrosAfectaMap(carteraDetalle);
            				}
         				} catch (GenericBusinessException e) {
         					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
         			}
        		}); 				
			}
		});	
		
		//Opcion que permite actualizar el valor del cartera afecta seleccionado de la tabla
		btnActualizarValor.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent evento) {
	    		try {
		    		if (tblAfecta.getSelectedRow()!=-1) {
		    			if(!txtValorAfecta.getText().equals("") && (txtValorAfecta.getText() != null)){
			    			//Extraigo el objeto detalle de cartera de la coleccion segun el registro seleccionado de la tabla, al cual debo actualizar su saldo
			            	CarteraDetalleIf carteraDetallePositivo = (CarteraDetalleIf) registrosDetallesToDetalleVector.get(tblAfecta.getSelectedRow());
			    			double valorAfecta = Double.valueOf(Utilitarios.removeDecimalFormat(txtValorAfecta.getText()));
			    			//double saldoDetalleCarteraTemp = saldoDetalleCartera + carteraAfectaIf.getValor().doubleValue() + valorAfecta;
			    			double saldoDetalleCarteraTemp = carteraAfectaIf.getValor().doubleValue() - valorAfecta + saldoDetalleCartera;
			    			//BigDecimal saldoDetalleCarteraTemp = saldoDetalleCartera.add(carteraAfectaIf.getValor().negate()).subtract(valorAfecta);
			    			//Veo si el el saldo del documento no es mayor de cero al  actualizar el saldo afecta
			    			if (valorAfecta > (carteraAfectaIf.getValor().doubleValue() + saldoDetalleCartera)) {
			    				SpiritAlert.createAlert("El valor ingresado no puede exceder a " + formatoDecimal.format(saldoDetalleCartera + carteraAfectaIf.getValor().doubleValue()) + " !!!", SpiritAlert.WARNING);
			    				txtValorAfecta.setText(carteraAfectaIf.getValor().negate().toString());
			    			}
			    			//Veo que el valor ingresado del saldo a actualizar sea mayor al saldo que le queda al cartera afecta
			    			else if (valorAfecta > (Utilitarios.redondeoValor(carteraDetallePositivo.getSaldo().doubleValue() + carteraAfectaIf.getValor().doubleValue()))) {
			    				SpiritAlert.createAlert("El valor ingresado no puede exceder a " + formatoDecimal.format(carteraDetallePositivo.getSaldo().doubleValue()) + " !!!", SpiritAlert.WARNING);
			    				txtValorAfecta.setText(carteraAfectaIf.getValor().negate().toString());
			    			} else {
			    				//Cambio el saldo del Cartera Detalle que estoy actualizando
			    				carteraDetallePositivo.setSaldo(BigDecimal.valueOf(Utilitarios.redondeoValor(carteraDetallePositivo.getSaldo().doubleValue() + carteraAfectaIf.getValor().doubleValue()) - Utilitarios.redondeoValor(valorAfecta)));		    				
			    				CarteraIf carteraPositivo = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetallePositivo.getCarteraId());
			    				carteraPositivo.setSaldo(BigDecimal.valueOf(carteraPositivo.getSaldo().doubleValue() + carteraAfectaIf.getValor().doubleValue() - valorAfecta));
	        					//Actualizo el valor del saldo detalle
								//saldoDetalleCartera = saldoDetalleCarteraTemp.negate();							
			    				saldoDetalleCartera = saldoDetalleCarteraTemp;
								//Cambio el valor del Saldo del Detalle
								txtSaldoDetalle.setText(formatoDecimal.format(saldoDetalleCartera));							
								//Cambio el valor del carteraAfectaIf que estoy actualizando
			    				carteraAfectaIf.setValor(BigDecimal.valueOf(valorAfecta));						        
			    				//Veo si el cartera afecta ya esta insertado en la base, debido a que este registro puede estar referenciado en otra tabla
				            	if (carteraAfectaIf.getId() != null) {
				            		//Actualizo el Cartera Afecta
									//CarteraModel.getCarteraAfectaSessionService().saveCarteraAfecta(carteraAfectaIf);
									//carteraAfectaActualizarColeccion.add(carteraAfectaIf);
		        					//Actualizo el Cartera detalle (+)
		        					if(carteraAfectaIf.getCartera().equals("S")){
		        						//CarteraModel.getCarteraDetalleSessionService().saveCarteraDetalle(carteraDetallePositivo);
		        						//solo se usa en modo Update
		        						//carteraDetalleActualizadaColeccion.add(carteraDetallePositivo);
		        						//solo se usa en modo Update
		        						//carteraActualizadaColeccion.add(carteraPositivo);
		        					}
		        					//calculo el valor del Saldo del Cartera detalle (-)
		        					CarteraDetalleIf carteraDetalleNegativo = (CarteraDetalleIf) SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
		        					carteraDetalleNegativo.setSaldo(BigDecimal.valueOf(carteraDetalleNegativo.getSaldo().doubleValue() + saldoDetalleCartera));
		        					//CarteraModel.getCarteraDetalleSessionService().saveCarteraDetalle(carteraDetalleNegativo);
		        					//carteraDetalleActualizadaColeccion.add(carteraDetalleNegativo);
		        					//calculo el valor del Saldo de la Cartera
		        					CarteraIf cartera = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleNegativo.getCarteraId());
		        					cartera.setSaldo(BigDecimal.valueOf(cartera.getSaldo().doubleValue() + saldoDetalleCartera));
		        					//CarteraModel.getCarteraSessionService().saveCartera(cartera);
		        					//carteraActualizadaColeccion.add(cartera);
		        					
		        					actualizarCarteraDetalleyAfecta(carteraDetallePositivo, true, false);
				            	}
				            	//Sino existe, simplemente lo borro de la tabla y de la coleccion
				            	else {				  
				            		actualizarCarteraDetalleyAfecta(carteraDetallePositivo, true, false);
		        				}
			    		    										
								//Actualizo el registro de las colecciones y de la Tabla
								registrosDetallesToDetalleVector.set(tblAfecta.getSelectedRow(),carteraDetallePositivo);
								registrosAfectaToDetalleVector.set(tblAfecta.getSelectedRow(),carteraAfectaIf);
								//Seteo en  saldo en la tabla de busqueda del saldo del detalle escogido en cero
								modelAfectaCartera.setValueAt(formatoDecimal.format(carteraDetallePositivo.getSaldo().doubleValue()),tblAfecta.getSelectedRow(),6);
								//Pongo los text field en blanco
								txtDocumentoAfecta.setText("");
								txtCodigoAfecta.setText("");
								txtCarteraAfecta.setText("");
								txtFechaCreacionAfecta.setText("");
								//txtValorAfecta.setText("");
					    		txtValorAfecta.setText("");
			    			}
		    			}else{
		    				SpiritAlert.createAlert("Debe existir un valor en Valor Afecta !", SpiritAlert.WARNING);
		    				txtValorAfecta.grabFocus();
		    			}
		    		} else{
		    			SpiritAlert.createAlert("Debe elegir el registro de la lista a actualizar !!!", SpiritAlert.WARNING);
		    		}
	    		} catch (GenericBusinessException e) {
	    			e.printStackTrace();
					SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
				}
	    	}
	    });
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				CarteraModel panel = (CarteraModel) ActivePanel.getSingleton().getActivePanel();
				panel.guardarDatosAfectaCartera();// Oculto el mapa comercial
				dispose();
			}
		});

		//Opcion Que Permite quitar un documento deseado de la tabla de afecta
	    JMenuItem itemQuitarAfectaCartera = new JMenuItem("Quitar");
	    popupMenuAfectaCartera.add(itemQuitarAfectaCartera);
	    //Añado el listener de menupopup
	    itemQuitarAfectaCartera.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		if (tblAfecta.getSelectedRow() != -1)
	    			removerItemAfectaCartera(tblAfecta.getSelectedRow());
	    		else
	    			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
	    	}
	    });
	    
		//Listener de la tabla afecta
	    tblAfecta.addMouseListener(new MouseAdapter() {
	    	public void mouseReleased(MouseEvent evt) {
	    		try {
	    			if (evt.isPopupTrigger() && tblAfecta.getModel().getRowCount()>0) {
		            	popupMenuAfectaCartera.show(evt.getComponent(), evt.getX(), evt.getY());
		            } else if (tblAfecta.getSelectedRow()!=-1) {
		            	//Saco la fila que ha sido seleccionada de la tabla de afecta
			    		filaSeleccionadaTablaAfecta = tblAfecta.getSelectedRow();
			           	//Extraigo el objeto cartera detalle de la coleccion segun el registro seleccionado de la tabla
			           	CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) registrosDetallesToDetalleVector.get(filaSeleccionadaTablaAfecta);
			           	//Seteo el objeto CarteraAfecta segun el registro seleccionado
			           	carteraAfectaIf = (CarteraAfectaIf) registrosAfectaToDetalleVector.get(filaSeleccionadaTablaAfecta);
			           	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
			    		//Seteo las fechas leidas de los objetos
			    		String fechaCreacionDetalleCartera = dateFormatter.format(carteraDetalleTemp.getFechaCreacion());
			    		//Extraigo el objeto documento de la cartera detalle
						DocumentoIf documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleTemp.getDocumentoId());
		            	if((cmbTipoDocumentoAfecta.getItemCount() > 0) && (ComboBoxComponent.getIndexToSelectItem(cmbTipoDocumentoAfecta, documento.getTipoDocumentoId()) == -1)){
		            		cmbTipoDocumentoAfecta.setSelectedIndex(0);
		            	}else{
		            		cmbTipoDocumentoAfecta.setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(cmbTipoDocumentoAfecta, documento.getTipoDocumentoId()));
		            	}
		            	cmbTipoDocumentoAfecta.repaint();
		            	//Veo si es cartera o no
						String esCartera = "";
						if("S".equals(carteraDetalleTemp.getCartera())) esCartera = "SI";
						else esCartera = "NO";
			            //Muestro el la informacion del documento a cruzar
						txtDocumentoAfecta.setText(documento.getNombre());
						txtCodigoAfecta.setText(tblAfecta.getModel().getValueAt(filaSeleccionadaTablaAfecta,1).toString());
						txtCarteraAfecta.setText(esCartera);
						txtFechaCreacionAfecta.setText(fechaCreacionDetalleCartera);
						//txtValorAfecta.setText(carteraDetalleTemp.getValor().toString());
			    		txtValorAfecta.setText(formatoDecimal.format(carteraAfectaIf.getValor().doubleValue()));
			    		txtValorAfecta.grabFocus();
		            }				
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
	    	}
	    });
	}
	
	private void removerItemAfectaCartera(int selectedRow) {
		try {
			// Extraigo el objeto detalle de cartera de la coleccion segun el registro seleccionado de la tabla, al cual debo actualizar su saldo
        	CarteraDetalleIf carteraDetallePositivo = (CarteraDetalleIf) registrosDetallesToDetalleVector.get(selectedRow);
        	CarteraIf carteraPositivo = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetallePositivo.getCarteraId());
			//Actualizo el valor del saldo detalle
			saldoDetalleCartera = saldoDetalleCartera + carteraAfectaIf.getValor().doubleValue();							
			//Cambio el valor del Saldo del Detalle
			txtSaldoDetalle.setText(formatoDecimal.format(saldoDetalleCartera));
			//Veo si el cartera afecta ya esta insertado en la base, debido a que este registro puede estar referenciado en otra tabla
        	if(carteraAfectaIf.getId()!= null){
        		//Elimino el Cartera Afecta
				//CarteraModel.getCarteraAfectaSessionService().deleteCarteraAfecta(carteraAfectaIf.getId());
				carteraAfectaEliminarColeccion.add(carteraAfectaIf);
				//calculo el valor del Saldo del Cartera detalle (+)
				if(carteraAfectaIf.getCartera().equals("S")){
					//carteraDetallePositivo.setSaldo(carteraDetallePositivo.getSaldo().subtract(carteraAfectaIf.getValor()));
					carteraDetallePositivo.setSaldo(carteraDetallePositivo.getSaldo().add(carteraAfectaIf.getValor()));
					carteraPositivo.setSaldo(carteraPositivo.getSaldo().add(carteraAfectaIf.getValor()));
					//CarteraModel.getCarteraDetalleSessionService().saveCarteraDetalle(carteraDetallePositivo);
					carteraActualizadaColeccion.add(carteraPositivo);
				}
				
				//calculo el valor del Saldo del Cartera detalle (-)
				CarteraDetalleIf carteraDetalleNegativo = (CarteraDetalleIf) SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
				carteraDetalleNegativo.setSaldo(carteraDetalleNegativo.getSaldo().add(carteraAfectaIf.getValor()));
				//CarteraModel.getCarteraDetalleSessionService().saveCarteraDetalle(carteraDetalleNegativo);
				//calculo el valor del Saldo de la Cartera
				CarteraIf cartera = (CarteraIf) SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleNegativo.getCarteraId());
				cartera.setSaldo(cartera.getSaldo().add(carteraAfectaIf.getValor()));
				//CarteraModel.getCarteraSessionService().saveCartera(cartera);
				//carteraActualizadaColeccion.add(cartera);
				
				actualizarCarteraDetalleyAfecta(carteraDetallePositivo, false, true);
        	}
        	// Sino existe, simplemente lo borro de la tabla y de la coleccion
        	else {        		
        		actualizarCarteraDetalleyAfecta(carteraDetallePositivo, false, true);
        	}
			
			txtValorAfecta.setText("");		
			//Elimino el registro de la coleccion y de la Tabla
			registrosDetallesToDetalleVector.remove(selectedRow);
			registrosAfectaToDetalleVector.remove(selectedRow);
			modelAfectaCartera.removeRow(selectedRow);
			//Pongo los text field en blanco
			txtDocumentoAfecta.setText("");
			txtCodigoAfecta.setText("");
			txtCarteraAfecta.setText("");
			txtFechaCreacionAfecta.setText("");
    		txtValorAfecta.setText("");
    	} catch (GenericBusinessException e) {
    		e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
    }

	private void actualizarCarteraDetalleyAfecta(CarteraDetalleIf carteraDetallePositivo, boolean actualiza, boolean remover) {
		Iterator iteratorMapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap.keySet().iterator();
		
		while(iteratorMapaRegistrosSeleccionadosMap.hasNext()) {
			Long index = (Long) iteratorMapaRegistrosSeleccionadosMap.next();
			registrosAfectaMap = (Map)mapaRegistrosSeleccionadosMap.get(index);
			Iterator itKeysRegistrosAfectaMap = registrosAfectaMap.keySet().iterator();	
			//Recorro las filas seleccionadas para encontrar el cartera detalle del cual estoy eliminando
			while(itKeysRegistrosAfectaMap.hasNext()){
				Integer indice = (Integer) itKeysRegistrosAfectaMap.next();
				//Extraigo del arreglo leido de la base el Cartera Detalle escogido de los documentos cartera
				CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf)  registrosAfectaMap.get(indice);
				//Si el id del carteraDetalle que stoy elimnanod de la tabla es igual al encontrado 
				if(carteraDetallePositivo.getId().equals(carteraDetalleTemp.getId())){
					BigDecimal saldoCarteraDetalle = null;
					if(!actualiza && (remover || (modo==SpiritMode.UPDATE_MODE))){
						saldoCarteraDetalle = carteraDetalleTemp.getSaldo().add(carteraAfectaIf.getValor());
					}else{
						saldoCarteraDetalle = carteraDetallePositivo.getSaldo();
					}
					//Mando a setear el saldo del cartera detalle que quite a su valor original
					carteraDetalleTemp.setSaldo(saldoCarteraDetalle);
					//Guardo en el mapa el registro que fue eliminado de la tabla de afecta
					registrosAfectaMap.put(indice,carteraDetalleTemp);
					break;
				}
			}
		}
	}
	
	private ArrayList getModel(ArrayList listaPlanMedio) {		
		ArrayList data = new ArrayList();
		Iterator it = listaPlanMedio.iterator();
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				PlanMedioIf bean = (PlanMedioIf) it.next();
				fila.add(bean.getCodigo());
	            fila.add(bean.getConcepto());
	            TipoProveedorIf tipoProveedorTemp = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(bean.getTipoProveedorId());
	            fila.add(tipoProveedorTemp.getNombre());
	            data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
		return data;		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		btnGuardar = new JButton();
		panelDesglose = new JPanel();
		lblTipoDocumentoAfecta = new JLabel();
		cmbTipoDocumentoAfecta = new JComboBox();
		lblDocumentoAfecta = new JLabel();
		btnBuscarDocumentosAfecta = new JButton();
		txtDocumentoAfecta = new JTextField();
		lblFechaCreacionAfecta = new JLabel();
		txtFechaCreacionAfecta = new JTextField();
		lblFechaAplicacionAfecta = new JLabel();
		txtFechaAplicacionAfecta = new JTextField();
		spAfecta = new JScrollPane();
		tblAfecta = new JTable();
		lblCodigoAfecta = new JLabel();
		txtCodigoAfecta = new JTextField();
		lblCarteraAfecta = new JLabel();
		txtCarteraAfecta = new JTextField();
		lblValorAfecta = new JLabel();
		txtValorAfecta = new JTextField();
		btnActualizarValor = new JButton();
		panelPago = new JPanel();
		lblTipoDocumentoDetalle = new JLabel();
		txtTipoDocumentoDetalle = new JTextField();
		lblFechaCreacionDetalle = new JLabel();
		txtFechaCreacionDetalle = new JTextField();
		lblFechaVencimientoDetalle = new JLabel();
		txtFechaVencimientoDetalle = new JTextField();
		lblValorDetalle = new JLabel();
		txtValorDetalle = new JTextField();
		lblSaldoDetalle = new JLabel();
		txtSaldoDetalle = new JTextField();
		lblDocumentoDetalle = new JLabel();
		txtDocumentoDetalle = new JTextField();
		lblTipoPagoDetalle = new JLabel();
		txtTipoPagoDetalle = new JTextField();
		lblBancoDetalle = new JLabel();
		txtBancoDetalle = new JTextField();
		lblCarteraDeatlle = new JLabel();
		txtCarteraDetalle = new JTextField();
		lblFechaCarteraDetalle = new JLabel();
		txtFechaCarteraDetalle = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Afecta Cartera");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX8),
				new ColumnSpec("max(default;500dlu)"),
				new ColumnSpec(Sizes.DLUX8)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUX8),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUX8)
			}));

		//---- btnGuardar ----
		btnGuardar.setToolTipText("Cruzar Documentos con el Detalle");
		btnGuardar.setBackground(UIManager.getColor("Button.light"));
		btnGuardar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/saveRecord.png"));
		contentPane.add(btnGuardar, cc.xywh(2, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== panelDesglose ========
		{
			panelDesglose.setBorder(new TitledBorder("Desglose"));
			panelDesglose.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.DLUX8),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;165dlu)"),
					new ColumnSpec(Sizes.dluX(10)),
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;165dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.DLUX8)
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(10)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblTipoDocumentoAfecta ----
			lblTipoDocumentoAfecta.setText("Tipo de Documento:");
			panelDesglose.add(lblTipoDocumentoAfecta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(cmbTipoDocumentoAfecta, cc.xy(5, 3));
			
			//---- lblDocumentoAfecta ----
			lblDocumentoAfecta.setText("Documento:");
			panelDesglose.add(lblDocumentoAfecta, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			btnBuscarDocumentosAfecta.setToolTipText("Buscar documentos a cruzar con el Pago");
			btnBuscarDocumentosAfecta.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
			panelDesglose.add(btnBuscarDocumentosAfecta, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			panelDesglose.add(txtDocumentoAfecta, cc.xy(9, 3));
			
			//---- lblFechaCreacionAfecta ----
			lblFechaCreacionAfecta.setText("Fecha de Creaci\u00f3n:");
			panelDesglose.add(lblFechaCreacionAfecta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(txtFechaCreacionAfecta, cc.xy(5, 7));
			
			//---- lblFechaAplicacionAfecta ----
			lblFechaAplicacionAfecta.setText("Fecha de Aplicaci\u00f3n:");
			panelDesglose.add(lblFechaAplicacionAfecta, cc.xywh(7, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(txtFechaAplicacionAfecta, cc.xy(9, 7));
			
			//======== spAfecta ========
			{
				spAfecta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				spAfecta.setPreferredSize(new Dimension(452, 125));
				
				//---- tblAfecta ----
				tblAfecta.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Documento", "Código", "Cart.", "F. Creación", "F. Aplicación", "Valor", "Saldo", "Preimpreso"
					}
				) {
					boolean[] columnEditable = new boolean[] {
						false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return columnEditable[columnIndex];
					}
				});
				tblAfecta.setPreferredScrollableViewportSize(new Dimension(500, 75));
				tblAfecta.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				tblAfecta.setAutoCreateColumnsFromModel(true);
				spAfecta.setViewportView(tblAfecta);
			}
			panelDesglose.add(spAfecta, cc.xywh(3, 13, 9, 3));
			
			//---- lblCodigoAfecta ----
			lblCodigoAfecta.setText("Codigo:");
			panelDesglose.add(lblCodigoAfecta, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(txtCodigoAfecta, cc.xy(5, 5));
			
			//---- lblCarteraAfecta ----
			lblCarteraAfecta.setText("Cartera:");
			panelDesglose.add(lblCarteraAfecta, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(txtCarteraAfecta, cc.xy(9, 5));
			
			//---- lblValorAfecta ----
			lblValorAfecta.setText("Valor Afecta:");
			panelDesglose.add(lblValorAfecta, cc.xywh(7, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtValorAfecta ----
			txtValorAfecta.setHorizontalAlignment(JTextField.RIGHT);
			panelDesglose.add(txtValorAfecta, cc.xy(9, 9));
			btnActualizarValor.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
			btnActualizarValor.setToolTipText("Cambiar el Saldo del Documento a Cruzar");
			panelDesglose.add(btnActualizarValor, cc.xywh(11, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		}
		contentPane.add(panelDesglose, cc.xy(2, 7));

		//======== panelPago ========
		{
			panelPago.setBorder(new TitledBorder("Pago"));
			panelPago.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(10)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;115dlu):grow"),
					new ColumnSpec(Sizes.dluX(10)),
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;115dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(10))
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblTipoDocumentoDetalle ----
			lblTipoDocumentoDetalle.setText("Tipo de Documento:");
			panelPago.add(lblTipoDocumentoDetalle, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtTipoDocumentoDetalle, cc.xy(5, 3));
			
			//---- lblFechaCreacionDetalle ----
			lblFechaCreacionDetalle.setText("Fecha de Creaci\u00f3n:");
			panelPago.add(lblFechaCreacionDetalle, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtFechaCreacionDetalle, cc.xy(5, 7));
			
			//---- lblFechaVencimientoDetalle ----
			lblFechaVencimientoDetalle.setText("Fecha de Vencimiento:");
			panelPago.add(lblFechaVencimientoDetalle, cc.xywh(7, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtFechaVencimientoDetalle, cc.xy(9, 7));
			
			//---- lblValorDetalle ----
			lblValorDetalle.setText("Valor:");
			panelPago.add(lblValorDetalle, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtValorDetalle
			txtValorDetalle.setHorizontalAlignment(JTextField.RIGHT);
			panelPago.add(txtValorDetalle, cc.xy(5, 11));
			
			//---- lblSaldoDetalle ----
			lblSaldoDetalle.setText("Saldo:");
			panelPago.add(lblSaldoDetalle, cc.xywh(7, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtSaldoDetalle ----
			txtSaldoDetalle.setHorizontalAlignment(JTextField.RIGHT);
			panelPago.add(txtSaldoDetalle, cc.xy(9, 11));
			
			//---- lblDocumentoDetalle ----
			lblDocumentoDetalle.setText("Documento:");
			panelPago.add(lblDocumentoDetalle, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtDocumentoDetalle, cc.xy(9, 3));
			
			//---- lblTipoPagoDetalle ----
			lblTipoPagoDetalle.setText("Tipo de Pago:");
			panelPago.add(lblTipoPagoDetalle, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtTipoPagoDetalle, cc.xy(5, 5));
			
			//---- lblBancoDetalle ----
			lblBancoDetalle.setText("Banco:");
			panelPago.add(lblBancoDetalle, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtBancoDetalle, cc.xy(9, 5));
			
			//---- lblCarteraDeatlle ----
			lblCarteraDeatlle.setText("Cartera:");
			panelPago.add(lblCarteraDeatlle, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtCarteraDetalle, cc.xy(5, 9));
			
			//---- lblFechaCarteraDetalle ----
			lblFechaCarteraDetalle.setText("Fecha de Cartera:");
			panelPago.add(lblFechaCarteraDetalle, cc.xywh(7, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelPago.add(txtFechaCarteraDetalle, cc.xy(9, 9));
		}
		contentPane.add(panelPago, cc.xy(2, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblAfecta.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblAfecta.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblAfecta.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		
		TableColumn anchoColumna = tblAfecta.getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = tblAfecta.getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(127);
		anchoColumna = tblAfecta.getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(43);
		anchoColumna = tblAfecta.getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = tblAfecta.getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = tblAfecta.getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = tblAfecta.getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = tblAfecta.getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(90);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton btnGuardar;
	private JPanel panelDesglose;
	private JLabel lblTipoDocumentoAfecta;
	private JComboBox cmbTipoDocumentoAfecta;
	private JLabel lblDocumentoAfecta;
	private JButton btnBuscarDocumentosAfecta;
	private JTextField txtDocumentoAfecta;
	private JLabel lblFechaCreacionAfecta;
	private JTextField txtFechaCreacionAfecta;
	private JLabel lblFechaAplicacionAfecta;
	private JTextField txtFechaAplicacionAfecta;
	private JScrollPane spAfecta;
	private JTable tblAfecta;
	private JLabel lblCodigoAfecta;
	private JTextField txtCodigoAfecta;
	private JLabel lblCarteraAfecta;
	private JTextField txtCarteraAfecta;
	private JLabel lblValorAfecta;
	private JTextField txtValorAfecta;
	private JButton btnActualizarValor;
	private JPanel panelPago;
	private JLabel lblTipoDocumentoDetalle;
	private JTextField txtTipoDocumentoDetalle;
	private JLabel lblFechaCreacionDetalle;
	private JTextField txtFechaCreacionDetalle;
	private JLabel lblFechaVencimientoDetalle;
	private JTextField txtFechaVencimientoDetalle;
	private JLabel lblValorDetalle;
	private JTextField txtValorDetalle;
	private JLabel lblSaldoDetalle;
	private JTextField txtSaldoDetalle;
	private JLabel lblDocumentoDetalle;
	private JTextField txtDocumentoDetalle;
	private JLabel lblTipoPagoDetalle;
	private JTextField txtTipoPagoDetalle;
	private JLabel lblBancoDetalle;
	private JTextField txtBancoDetalle;
	private JLabel lblCarteraDeatlle;
	private JTextField txtCarteraDetalle;
	private JLabel lblFechaCarteraDetalle;
	private JTextField txtFechaCarteraDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	
	public Vector getRegistrosDetallesToDetalleVector() {
		return registrosDetallesToDetalleVector;
	}

	public void setRegistrosDetallesToDetalleVector(Vector registrosDetallesToDetalleVector) {
		this.registrosDetallesToDetalleVector = registrosDetallesToDetalleVector;
	}


	public JTextField getTxtSaldoDetalle() {
		return txtSaldoDetalle;
	}


	public void setTxtSaldoDetalle(JTextField txtSaldoDetalle) {
		this.txtSaldoDetalle = txtSaldoDetalle;
	}


	public double getSaldoDetalleCartera() {
		return saldoDetalleCartera;
	}


	public void setSaldoDetalleCartera(double saldoDetalleCartera) {
		this.saldoDetalleCartera = saldoDetalleCartera;
	}


	public Vector getRegistrosAfectaToDetalleVector() {
		return registrosAfectaToDetalleVector;
	}


	public void setRegistrosAfectaToDetalleVector(Vector registrosAfectaToDetalleVector) {
		this.registrosAfectaToDetalleVector = registrosAfectaToDetalleVector;
	}

	public Map getRegistrosAfectaMap() {
		return registrosAfectaMap;
	}
	
	public void setRegistrosAfectaMap(Map registrosAfectaMap) {
			this.registrosAfectaMap = registrosAfectaMap;
	}
	
	public JDAfectaCartera getThisDialog() {
		return this;
	}
}
