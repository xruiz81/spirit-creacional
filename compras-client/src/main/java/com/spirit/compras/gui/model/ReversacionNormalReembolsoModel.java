package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.gui.panel.JPReversacionNormalReembolso;
import com.spirit.compras.handler.OrderData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;

public class ReversacionNormalReembolsoModel extends JPReversacionNormalReembolso {
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	ArrayList comprasReembolsoParaReversarList = new ArrayList();
	Map<Long, Vector<OrderData>> ordenesDataMap = new HashMap<Long, Vector<OrderData>>();
	private static final String ESTADO_NORMAL_REEMBOLSO = "X";
	
	public ReversacionNormalReembolsoModel() {
		anchoColumnasTabla();
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnProcesar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				procesarReversacionCompras();
			}
		});
	}

	private void anchoColumnasTabla() {
		getTblReversacionNormalReembolso().getTableHeader().setReorderingAllowed(false);
		getTblReversacionNormalReembolso().setCellSelectionEnabled(true);
		getTblReversacionNormalReembolso().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(15);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionNormalReembolso().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(60);
	}
	
	public void save() {
		showSaveMode();
	}

	public void delete() {
		// TODO Auto-generated method stub		
	}

	public void update() {
		// TODO Auto-generated method stub		
	}

	public void clean() {
		comprasReembolsoParaReversarList.clear();
		DefaultTableModel model = (DefaultTableModel) getTblReversacionNormalReembolso().getModel();
		for(int i= this.getTblReversacionNormalReembolso().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
	}
	
	private void cargarTabla() {
		setCursor(SpiritCursor.hourglassCursor);
		try {
			comprasReembolsoParaReversarList = new ArrayList();
			Map parameterMap = new HashMap();
			parameterMap.put("estado", ESTADO_NORMAL_REEMBOLSO);
			comprasReembolsoParaReversarList = (ArrayList) SessionServiceLocator.getCompraSessionService().findCompraReembolsoParaReversarByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa());
			//List comprasReembolsoParaReversarAdicionalesList = (ArrayList) getCompraSessionService().findCompraReembolsoParaReversarAdicionalesByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa());
			Iterator comprasReembolsoParaReversarIterator = comprasReembolsoParaReversarList.iterator();
			while (comprasReembolsoParaReversarIterator.hasNext()) {
				Object[] compraReembolsoParaReversar = (Object[]) comprasReembolsoParaReversarIterator.next();
				CompraIf compra = (CompraIf) compraReembolsoParaReversar[0];
				tableModel = (DefaultTableModel) getTblReversacionNormalReembolso().getModel();
				Vector<Object> fila = new Vector<Object>();
				agregarFilasTabla(compra, fila);
				tableModel.addRow(fila);
				Vector<OrderData> ordenesDataVector = new Vector<OrderData>();
				Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).iterator();
				while (ordenAsociadaIt.hasNext()) {
					OrdenAsociadaIf ordenAsociada = ordenAsociadaIt.next();
					OrderData orden = new OrderData();
					ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
					ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
					orden.setProvider(proveedor);
					orden.setOrderType(ordenAsociada.getTipoOrden());
					if (ordenAsociada.getTipoOrden().equals("OC")) {
						orden.setPurchaseOrder(SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId()));
					} else {
						orden.setMediaOrder(SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId()));
					}
					ordenesDataVector.add(orden);
				}
				ordenesDataMap.put(compra.getId(), ordenesDataVector);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar los datos de la tabla", SpiritAlert.ERROR);
		}
		setCursor(SpiritCursor.normalCursor);
	}
	
	private void agregarFilasTabla(CompraIf compra, Vector<Object> fila){
		fila.add(String.valueOf(getTblReversacionNormalReembolso().getRowCount() + 1));
		fila.add(compra.getPreimpreso());
		String fecha = compra.getFecha().toString();
		String year = fecha.substring(0,4);
		String month = fecha.substring(5,7);
		String day = fecha.substring(8,10);
		String fechaCompra = day + "-" + Utilitarios.getNombreMes(Integer.parseInt(month)).substring(0,3) + "-" + year;
		fila.add(fechaCompra);
		fila.add(compra.getObservacion());
		fila.add(formatoDecimal.format(compra.getValor().doubleValue()));
		fila.add(formatoDecimal.format(compra.getIva().doubleValue()));
		fila.add(formatoDecimal.format(compra.getValor().doubleValue() + compra.getIva().doubleValue()));
		fila.add("");
	}
	
	private void procesarReversacionCompras() {
		int i = 0;
		Iterator comprasReembolsoParaReversarIterator = comprasReembolsoParaReversarList.iterator();
		try {
			TipoDocumentoIf tipoDocumentoCompraPorReembolso = null;
			DocumentoIf documentoCompraPorReembolso = null;
			Iterator tipoDocumentoIt = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("COR").iterator();
			if (tipoDocumentoIt.hasNext())
				tipoDocumentoCompraPorReembolso = (TipoDocumentoIf) tipoDocumentoIt.next();
			Iterator documentoIt = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("COR").iterator();
			if (documentoIt.hasNext())
				documentoCompraPorReembolso = (DocumentoIf) documentoIt.next();
			
			if (tipoDocumentoCompraPorReembolso != null && documentoCompraPorReembolso != null) {
				while (comprasReembolsoParaReversarIterator.hasNext()) {
					Object[] compraReembolsoParaReversar = (Object[]) comprasReembolsoParaReversarIterator.next();
					CompraIf compra = (CompraIf) compraReembolsoParaReversar[0];
					CompraIf compraAnterior = (CompraIf) DeepCopy.copy(compra);
					CarteraIf cartera = (CarteraIf) compraReembolsoParaReversar[1];
					//AsientoIf asiento = (AsientoIf) compraReembolsoParaReversar[2];
					Vector<OrderData> ordenesAsociadasVector = new Vector<OrderData>();
					ordenesAsociadasVector = ordenesDataMap.get(compra.getId());
					SessionServiceLocator.getCompraSessionService().procesarReversacionCompraPorReembolso(compra, compraAnterior, cartera, tipoDocumentoCompraPorReembolso, documentoCompraPorReembolso, (UsuarioIf) Parametros.getUsuarioIf(), ordenesAsociadasVector);
					i++;
				}
				SpiritAlert.createAlert("Proceso realizado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} else {
				SpiritAlert.createAlert("No se ha encontrado el tipo de documento/documento Compra por Reembolso.\n" +
						"No se puede realizar el proceso de reversación.\n" +
						"Ingrese en el sistema el tipo de documento/documento requerido.", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Saldo de cuenta contable se vuelve negativo./n" + 
					"No se ha podido reversar el asiento asociado a la compra por reembolso!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
		cargarTabla();
	}
	 
	 
}
