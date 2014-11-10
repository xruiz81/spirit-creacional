package com.spirit.cartera.gui.model;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.gui.criteria.CarteraCruceNotaCreditoCriteria;
import com.spirit.cartera.gui.panel.JDNotaCreditoAfecta;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CruceDocumentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class NotaCreditoAfectaModel extends JDNotaCreditoAfecta {
	private static final long serialVersionUID = 2282140414398640751L;
	List<NotaCreditoIf> notaCreditoSeleccionadaList;
	Map carteraNotaCreditoMap;
	Map valorAplicaMap;
	double saldoCredito;
	double saldoAplica;
	DocumentoIf documento;
	ClienteOficinaIf operadorNegocio;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private JDPopupFinderModel popupFinder;
	private CarteraIf carteraAfectada;
	private Map parameterMap = new HashMap();
	private CruceNotaCreditoModel model;
	private static final int MAX_LONGITUD_VALOR = 22;

	public NotaCreditoAfectaModel(Frame owner, CruceNotaCreditoModel model, Map parameterMap) {
		super(owner);
		this.notaCreditoSeleccionadaList = (List<NotaCreditoIf>) parameterMap.get("NOTA_CREDITO_SELECCIONADA_LIST");
		this.carteraNotaCreditoMap = (Map) parameterMap.get("CARTERA_NOTA_CREDITO_MAP");
		this.valorAplicaMap = (Map) parameterMap.get("VALOR_APLICA_MAP");
		this.saldoCredito = ((Double) parameterMap.get("SALDO_CREDITO")).doubleValue();
		this.saldoAplica = this.saldoCredito;
		this.documento = (DocumentoIf) parameterMap.get("DOCUMENTO");
		this.operadorNegocio = (ClienteOficinaIf) parameterMap.get("OPERADOR_NEGOCIO");
		this.carteraAfectada = (CarteraIf) parameterMap.get("CARTERA_AFECTADA");
		this.parameterMap = parameterMap;
		this.model = model;
		initSwingComponents();
		initKeyListeners();
		initListeners();
		iniciarComponentes();
	}
	
	private void iniciarComponentes()
	{
		getBtnAceptar().setToolTipText("Aceptar");
		getBtnAceptar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/aceptar.png"));
		getBtnCancelar().setToolTipText("Cancelar");
		getBtnCancelar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnAplicar().setToolTipText("Aplicar");
		getBtnAplicar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnBuscarFactura().setToolTipText("Buscar factura");
		getBtnBuscarFactura().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarFactura().setText("");
		getCmbFechaAplicacion().setLocale(Utilitarios.esLocale);
		getCmbFechaAplicacion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaAplicacion().setEditable(false);
		getCmbFechaAplicacion().setShowNoneButton(false);
		
	}
	
	private void initSwingComponents() {
		getTxtSaldoCredito().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
		getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoAplica)));
		cargarComboDocumento();
	}
	
	private void initKeyListeners() {
		getTxtValorAfecta().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValorAfecta().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void initListeners() {
		getTxtValorAfecta().addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				saldoAplica = Double.parseDouble(getTxtValorAfecta().getText().replaceAll(",", "")); 				
			}			
		});
		
		getBtnBuscarFactura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFactura();
			}
		});
		
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		
		getBtnAplicar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicar();
			}
		});
		
		getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
	}
	
	private void aplicar() {
		if (validateFields()) {
			double saldoCredito = this.saldoCredito;
			double saldoAplica = this.saldoAplica;
			this.saldoAplica = 0D;
			double saldoCarteraAfectada = carteraAfectada.getSaldo().doubleValue();
			if ((Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtSaldoFactura().getText())) > 0D || getTxtSaldoFactura().getText().equals("")) && (Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtSaldoCredito().getText())) > 0D)) {
				for (int i=0; i<notaCreditoSeleccionadaList.size(); i++) {
					NotaCreditoIf notaCredito = (NotaCreditoIf) notaCreditoSeleccionadaList.get(i);
					CarteraIf carteraNotaCredito = (CarteraIf) carteraNotaCreditoMap.get(notaCredito.getId());
					double valorAplica = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValorAfecta().getText()));
					if (valorAplica > carteraNotaCredito.getSaldo().doubleValue())
						valorAplica = carteraNotaCredito.getSaldo().doubleValue();
					double saldo = saldoCarteraAfectada;
					if (Utilitarios.redondeoValor(valorAplica) <= Utilitarios.redondeoValor(saldoCredito)) {
						if (valorAplica < saldoCarteraAfectada) {
							saldo = saldoCarteraAfectada - valorAplica;
						} else {
							saldo = 0D;
							valorAplica = saldoCarteraAfectada;
						}
						
						saldoAplica -= valorAplica;
						this.saldoAplica += valorAplica;
						saldoCredito -= valorAplica;
						saldoCarteraAfectada = saldo;
						getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoAplica)));
						getTxtSaldoCredito().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
						getTxtSaldoFactura().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));
					} else {
						SpiritAlert.createAlert("El valor a aplicar supera al saldo de las notas de crédito seleccionadas", SpiritAlert.INFORMATION);
						getTxtValorAfecta().grabFocus();
					}
				}
			}
		}
	}
	
	private void aceptar() {
		getTxtSaldoCredito().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
		//getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
		getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoAplica)));
		
		if (validateFields() && carteraAfectada != null) {
			parameterMap.put("CARTERA_AFECTADA", carteraAfectada);
			getTxtSaldoFactura().setText(formatoDecimal.format(Utilitarios.redondeoValor(carteraAfectada.getSaldo().doubleValue())));
			double saldoCredito = this.saldoCredito;
			double saldoAplica = this.saldoAplica;
			double saldoCarteraAfectada = carteraAfectada.getSaldo().doubleValue();

			for (int i=0; i<notaCreditoSeleccionadaList.size(); i++) {
				NotaCreditoIf notaCredito = (NotaCreditoIf) notaCreditoSeleccionadaList.get(i);
				CarteraIf carteraNotaCredito = (CarteraIf) carteraNotaCreditoMap.get(notaCredito.getId());
				double valorAplica = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValorAfecta().getText()));
				if (valorAplica > carteraNotaCredito.getSaldo().doubleValue())
					valorAplica = carteraNotaCredito.getSaldo().doubleValue();
				double saldo = saldoCarteraAfectada;
				if (Utilitarios.redondeoValor(valorAplica) <= Utilitarios.redondeoValor(saldoCredito)) {
					if (valorAplica < saldoCarteraAfectada) {
						saldo = saldoCarteraAfectada - valorAplica;
					} else {
						saldo = 0D;
						valorAplica = saldoCarteraAfectada;
					}
					
					saldoAplica -= valorAplica;
					saldoCredito -= valorAplica;
					saldoCarteraAfectada = saldo;
					carteraAfectada.setSaldo(BigDecimal.valueOf(saldoCarteraAfectada));
					carteraNotaCredito.setSaldo(BigDecimal.valueOf(carteraNotaCredito.getSaldo().doubleValue() - valorAplica));
					this.saldoCredito = saldoCredito;
					this.saldoAplica = saldoAplica;
					valorAplicaMap.put(notaCredito.getId(), valorAplica);
					getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoAplica)));
					getTxtSaldoCredito().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
					getTxtSaldoFactura().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));
					parameterMap.put("FECHA_APLICACION", new java.sql.Date(getCmbFechaAplicacion().getDate().getTime()));
				} else {
					SpiritAlert.createAlert("El valor a aplicar supera al saldo de las notas de crédito seleccionadas", SpiritAlert.INFORMATION);
					getTxtValorAfecta().grabFocus();
				}
			}
			this.model.setAfectaMap(parameterMap);
			this.model.refrescarTablaNotasCredito();
			this.dispose();
		}
	}
	
	private void cancelar() {
		this.notaCreditoSeleccionadaList = new ArrayList<NotaCreditoIf>();
		this.model.setAfectaMap(parameterMap);
		this.model.refrescarTablaNotasCredito();
		this.dispose();
	}
	
	private void buscarFactura() {
		try {
			if (getCmbDocumento().getSelectedItem() != null) {
				carteraAfectada = null;
				//getTxtSaldoCredito().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
				//getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldoCredito)));
				getTxtFactura().setText("");
				getTxtSaldoFactura().setText("");
				Map parameterMap = new HashMap();
				parameterMap.put("clienteoficinaId", operadorNegocio.getId());
								
				//en caso de que la nota detalle esta amarrada a una factura, solo debe aparecer como opcion esa factura
				Vector<Long> facturasAsociadasId = new Vector<Long>();
				boolean tieneReferencia = true;
				for(int i=0; i<notaCreditoSeleccionadaList.size(); i++){
					NotaCreditoIf notaCreditoIf = notaCreditoSeleccionadaList.get(i);
					Collection notasCreditoDetalle = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCreditoIf.getId());
					Iterator notasCreditoDetalleIt = notasCreditoDetalle.iterator();
					while(notasCreditoDetalleIt.hasNext()){
						NotaCreditoDetalleIf notaCreditoDetalleIf = (NotaCreditoDetalleIf)notasCreditoDetalleIt.next();
						if(notaCreditoDetalleIf.getTipoReferencia().equals("N")){ // si no hay referencia
							tieneReferencia = false;							
						}else{
							facturasAsociadasId.add(notaCreditoDetalleIf.getReferenciaId());
						}
					}					
				}
				
				//si todas tienen referencia solo deben aparacer las facturas referenciadas
				if(tieneReferencia && facturasAsociadasId.size() > 0){
					parameterMap.put("facturasAsociadasId", facturasAsociadasId);
				}
				
				DocumentoIf documentoAplica = (DocumentoIf) getCmbDocumento().getSelectedItem();
				int tamanoLista = SessionServiceLocator.getCarteraSessionService().getCarteraParaCruceNotaCreditoListSize(parameterMap, documentoAplica.getId());
				if (tamanoLista > 0) {
					CarteraCruceNotaCreditoCriteria criteria = new CarteraCruceNotaCreditoCriteria(documentoAplica.getId());
					criteria.setResultListSize(tamanoLista);
					criteria.setQueryBuilded(parameterMap);
					Vector<Integer> anchosColumnasVector = new Vector<Integer>();
					anchosColumnasVector.addElement(100);
					anchosColumnasVector.addElement(70);
					anchosColumnasVector.addElement(135);
					anchosColumnasVector.addElement(190);
					anchosColumnasVector.addElement(45);
					anchosColumnasVector.addElement(40);
					Map alineacionColumnas = new HashMap();
					alineacionColumnas.put(0, SwingConstants.CENTER);
					alineacionColumnas.put(1, SwingConstants.CENTER);
					alineacionColumnas.put(2, SwingConstants.CENTER);
					alineacionColumnas.put(4, SwingConstants.RIGHT);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), criteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchosColumnasVector, alineacionColumnas);
					if (popupFinder.getElementoSeleccionado() != null) {
						getSelectedObject();
					}
				} else {
					SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar el documento", SpiritAlert.INFORMATION);
				getCmbDocumento().grabFocus();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	private void getSelectedObject() {
		carteraAfectada = (CarteraIf) popupFinder.getElementoSeleccionado();
		getTxtFactura().setText(carteraAfectada.getPreimpreso());
		getTxtSaldoFactura().setText(formatoDecimal.format(Utilitarios.redondeoValor(carteraAfectada.getSaldo().doubleValue())));
	}
	
	public boolean validateFields() {
		if (getCmbFechaAplicacion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la fecha de aplicación", SpiritAlert.INFORMATION);
			getCmbFechaAplicacion().grabFocus();
			return false;
		}
		
		if (carteraAfectada == null) {
			SpiritAlert.createAlert("Debe seleccionar una factura para aplicar la nota de crédito", SpiritAlert.INFORMATION);
			getBtnBuscarFactura().grabFocus();
			return false;
		}
			
		return true;
	}
	
	private void cargarComboDocumento() {
		try {
			List<DocumentoIf> documentos = new ArrayList<DocumentoIf>();
			Iterator it = SessionServiceLocator.getCruceDocumentoSessionService().findCruceDocumentoByDocumentoId(documento.getId()).iterator();
			
			while (it.hasNext()) {
				CruceDocumentoIf cruceDocumento = (CruceDocumentoIf) it.next();
				DocumentoIf documentoAplica = SessionServiceLocator.getDocumentoSessionService().getDocumento(cruceDocumento.getDocumentoaplId());
				documentos.add(documentoAplica);
			}
			
			refreshCombo(getCmbDocumento(), documentos);
			getCmbDocumento().validate();
			getCmbDocumento().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	protected void refreshCombo(JComboBox combo,List<? extends Object> lista ){
		refreshComboByIndex(combo,lista,0);
	}
	
	public Map getParameterMap() {
		return parameterMap;
	}
	
	protected void refreshComboByIndex(JComboBox combo,List<? extends Object> lista,int index){
		try {
			SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(lista);
			int itemSeleccionado = combo.getSelectedIndex(); 
			int numeroItems = combo.getItemCount();
			combo.setModel(cmbModel);
			if (combo.getItemCount() > 0){
				if ( itemSeleccionado >= 0 && numeroItems == combo.getItemCount())
					combo.setSelectedIndex(itemSeleccionado);
				else
					combo.setSelectedIndex(index);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al refrescar la pantalla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
}
