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
import com.spirit.compras.gui.panel.JPReversacionReembolsoNormal;
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

public class ReversacionReembolsoNormalModel extends JPReversacionReembolsoNormal {
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	ArrayList comprasParaReversarList = new ArrayList();
	Map<Long, Vector<OrderData>> ordenesDataMap = new HashMap<Long, Vector<OrderData>>();
	private static final String ESTADO_REEMBOLSO_NORMAL = "Y";
	
	public ReversacionReembolsoNormalModel(){
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
		getTblReversacionReembolsoNormal().getTableHeader().setReorderingAllowed(false);
		getTblReversacionReembolsoNormal().setCellSelectionEnabled(true);
		getTblReversacionReembolsoNormal().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(15);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblReversacionReembolsoNormal().getColumnModel().getColumn(7);
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
		comprasParaReversarList.clear();
		DefaultTableModel model = (DefaultTableModel) getTblReversacionReembolsoNormal().getModel();
		for(int i= this.getTblReversacionReembolsoNormal().getRowCount();i>0;--i)
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
			comprasParaReversarList = new ArrayList();
			Map parameterMap = new HashMap();
			parameterMap.put("estado", ESTADO_REEMBOLSO_NORMAL);
			comprasParaReversarList = (ArrayList) SessionServiceLocator.getCompraSessionService().findCompraReembolsoParaReversarByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa());
			Iterator comprasParaReversarIterator = comprasParaReversarList.iterator();
			while (comprasParaReversarIterator.hasNext()) {
				Object[] compraParaReversar = (Object[]) comprasParaReversarIterator.next();
				CompraIf compra = (CompraIf) compraParaReversar[0];
				tableModel = (DefaultTableModel) getTblReversacionReembolsoNormal().getModel();
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
		fila.add(String.valueOf(getTblReversacionReembolsoNormal().getRowCount() + 1));
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
		Iterator comprasParaReversarIterator = comprasParaReversarList.iterator();
		try {
			TipoDocumentoIf tipoDocumentoCompra = null;
			DocumentoIf documentoCompraLocal = null;
			Iterator tipoDocumentoIt = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("COM").iterator();
			if (tipoDocumentoIt.hasNext())
				tipoDocumentoCompra = (TipoDocumentoIf) tipoDocumentoIt.next();
			Iterator documentoIt = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("COML").iterator();
			if (documentoIt.hasNext())
				documentoCompraLocal = (DocumentoIf) documentoIt.next();
			
			if (tipoDocumentoCompra != null && documentoCompraLocal != null) {
				while (comprasParaReversarIterator.hasNext()) {
					Object[] compraParaReversar = (Object[]) comprasParaReversarIterator.next();
					CompraIf compra = (CompraIf) compraParaReversar[0];
					CompraIf compraAnterior = (CompraIf) DeepCopy.copy(compra);
					CarteraIf cartera = (CarteraIf) compraParaReversar[1];
					//AsientoIf asiento = (AsientoIf) compraParaReversar[2];
					Vector<OrderData> ordenesAsociadasVector = new Vector<OrderData>();
					ordenesAsociadasVector = ordenesDataMap.get(compra.getId());
					SessionServiceLocator.getCompraSessionService().procesarReversacionCompra(compra, compraAnterior, cartera, tipoDocumentoCompra, documentoCompraLocal, (UsuarioIf) Parametros.getUsuarioIf(), ordenesAsociadasVector);
					i++;
				}
				SpiritAlert.createAlert("Proceso realizado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} else {
				SpiritAlert.createAlert("No se ha encontrado el tipo de documento/documento Compra.\n" +
						"No se puede realizar el proceso de reversación.\n" +
						"Ingrese en el sistema el tipo de documento/documento requerido.", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Saldo de cuenta contable se vuelve negativo./n" + 
					"No se ha podido reversar el asiento asociado a la compra!", SpiritAlert.ERROR);
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
