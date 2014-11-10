package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.criteria.CarteraTransferibleCriteria;
import com.spirit.cartera.gui.panel.JPTransferenciaDocumentos;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.OficinaCriteria;
import com.spirit.util.Utilitarios;

public class TransferenciaDocumentosModel extends JPTransferenciaDocumentos {
	private static final long serialVersionUID = 8225107571461945614L;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private JDPopupFinderModel popupFinder;
	private static final String CODIGO_MODULO_CARTERA = "CART";
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private CarteraIf comprobante = null;
	private OficinaIf oficinaOrigen = null;
	private OficinaIf oficinaDestino = null;

	public TransferenciaDocumentosModel() {
		iniciarComponentes();
		iniciarVariables();
		initListeners();
		showSaveMode();
	}
	
	private void iniciarComponentes() {
		getBtnBuscarComprobante().setText("Buscar comprobante");
		getBtnBuscarComprobante().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarOficinaDestino().setText("");
		getBtnBuscarOficinaDestino().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	private void iniciarVariables() {
		oficinaOrigen = (OficinaIf) Parametros.getOficina();
		getTxtOficinaOrigen().setText(oficinaOrigen.getNombre());
	}

	private void initListeners() {
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoDocumento().getSelectedItem() != null) {
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
					if (tipoDocumento.getTipocartera().equals(TIPO_CARTERA_CLIENTE)) {
						getRbCliente().setSelected(true);
						getRbProveedor().setSelected(false);
					} else {
						getRbCliente().setSelected(false);
						getRbProveedor().setSelected(true);
					}
				}
			}
		});

		getBtnBuscarComprobante().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarComprobante();
			}
		});
		
		getBtnBuscarOficinaDestino().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarOficinaDestino();
			}
		});
		
		getBtnTransferirComprobante().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					transferirComprobante();
				}
			}
		});
	}
	
	private void buscarComprobante() {
		Map parameterMap = new HashMap();
		parameterMap.put("estado", "N");
		parameterMap.put("oficinaId", oficinaOrigen.getId());
		if (getCmbTipoDocumento().getSelectedItem() != null)
			parameterMap.put("tipodocumentoId",((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
		try {
			//Long moduloId = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator().next()).getId();
			Long moduloId = null;
			Iterator itmodulo = SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator();
			if(itmodulo.hasNext()) moduloId= ((ModuloIf)itmodulo.next()).getId();			
			
			
			int tamanoLista = SessionServiceLocator.getCarteraSessionService().getCarteraTransferibleListSize(parameterMap, moduloId, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				CarteraTransferibleCriteria carteraCriteria = new CarteraTransferibleCriteria(moduloId, Parametros.getIdEmpresa());
				carteraCriteria.setResultListSize(tamanoLista);
				carteraCriteria.setQueryBuilded(parameterMap);

				Vector<Integer> anchosColumnasVector = new Vector<Integer>();
				anchosColumnasVector.addElement(110);
				anchosColumnasVector.addElement(50);
				anchosColumnasVector.addElement(125);
				anchosColumnasVector.addElement(140);
				anchosColumnasVector.addElement(70);
				anchosColumnasVector.addElement(35);
				anchosColumnasVector.addElement(40);

				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), carteraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchosColumnasVector, null);

				if (popupFinder.getElementoSeleccionado() != null){
					getSelectedObject();
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al buscar comprobante", SpiritAlert.WARNING);
		}
	}
	
	private void transferirComprobante() {
		try {
			Map<String,Object> parametrosEmpresa = new HashMap<String,Object>();
			parametrosEmpresa.put("EMPRESA_ID", Parametros.getIdEmpresa());
			parametrosEmpresa.put("OFICINA_ID", oficinaDestino.getId());
			parametrosEmpresa.put("USUARIO", (UsuarioIf) Parametros.getUsuarioIf());
			SessionServiceLocator.getCarteraSessionService().transferirComprobante(comprobante, parametrosEmpresa, oficinaOrigen, oficinaDestino, false);
			SpiritAlert.createAlert("Transferencia de comprobante realizada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error en la transferencia", SpiritAlert.WARNING);
		}
	}
	
	private void buscarOficinaDestino() {
		OficinaCriteria oficinaCriteria = new OficinaCriteria();
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("empresaId", Parametros.getIdEmpresa());
		oficinaCriteria.setQueryBuilded(mapa);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), oficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			oficinaDestino = (OficinaIf) popupFinder.getElementoSeleccionado();
			getTxtOficinaDestino().setText(oficinaDestino.getNombre());
		}
	}

	private void getSelectedObject() {
		comprobante = (CarteraIf) popupFinder.getElementoSeleccionado();
		getLblPreimpresoCodigo().setText((comprobante.getPreimpreso() != null && !comprobante.getPreimpreso().equals(""))?comprobante.getPreimpreso():comprobante.getCodigo());
		getTxtFechaEmision().setText(Utilitarios.getFechaCortaUppercase(comprobante.getFechaEmision()));
		try {
			ClienteOficinaIf operadorNegocioOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(comprobante.getClienteoficinaId());
			ClienteIf operadorNegocio = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocioOficina.getClienteId());
			getTxtNombreComercial().setText(operadorNegocio.getNombreLegal());
			getTxtIdentificacion().setText(operadorNegocio.getIdentificacion());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de comprobante", SpiritAlert.WARNING);
		}

		getTxtTotal().setText(formatoDecimal.format(comprobante.getValor().doubleValue()));
		getTxtSaldo().setText(formatoDecimal.format(comprobante.getSaldo().doubleValue()));
	}

	public void clean() {
		getCmbTipoDocumento().setSelectedItem(null);
		getLblPreimpresoCodigo().setText("");
		getTxtFechaEmision().setText("");
		getTxtNombreComercial().setText("");
		getTxtIdentificacion().setText("");
		getTxtTotal().setText("");
		getTxtSaldo().setText("");
		getTxtOficinaDestino().setText("");
		getRbCliente().setSelected(false);
		getRbProveedor().setSelected(false);
		getRbCliente().setEnabled(false);
		getRbProveedor().setEnabled(false);

		comprobante = null;
		oficinaDestino = null;
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void duplicate() {
		// TODO Auto-generated method stub

	}

	public void find() {
		// TODO Auto-generated method stub

	}

	public void report() {
		// TODO Auto-generated method stub

	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean validateFields() {
		if (comprobante == null) {
			SpiritAlert.createAlert("Debe seleccionar el documento a transferir", SpiritAlert.WARNING);
			getBtnBuscarComprobante().grabFocus();
			return false;
		}

		if (oficinaDestino == null) {
			SpiritAlert.createAlert("Debe seleccionar oficina de destino para la transferencia", SpiritAlert.WARNING);
			getBtnBuscarOficinaDestino().grabFocus();
			return false;
		}
		
		if (oficinaOrigen.getId().compareTo(oficinaDestino.getId()) == 0) {
			SpiritAlert.createAlert("Las oficinas de origen y destino deben ser diferentes", SpiritAlert.WARNING);
			getBtnBuscarOficinaDestino().grabFocus();
			return false;
		}

		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		cargarCombos();
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		clean();
		cargarCombos();
	}

	private void cargarCombos() {
		cargarComboTipoDocumento();
	}

	private void cargarComboTipoDocumento() {
		try {
			Long idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator().next()).getId();
			List<TipoDocumentoIf> tiposDocumentos = (List<TipoDocumentoIf>)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoTransferibleByUsuarioIdAndModuloId(((UsuarioIf) Parametros.getUsuarioIf()).getId(), idModulo);
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo tipo de documento", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}
}