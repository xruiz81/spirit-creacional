package com.spirit.contabilidad.gui.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.spirit.exception.GenericBusinessException;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.general.session.CentroGastoSessionService;
import com.spirit.general.session.DepartamentoSessionService;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.general.session.LineaSessionService;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.crm.session.CorporacionSessionService;

public class AsientoHandler {
	private AsientoModel model;
	private static final int MAXIMA_LONGITUD_GLOSA = 350;
	private static final int MAXIMA_LONGITUD_OBSERVACIONES = 350;
	private static final int MAXIMA_LONGITUD_REFERENCIA = 60;
	private static final int MAXIMA_LONGITUD_VALOR_DEBE = 22;
	private static final int MAXIMA_LONGITUD_VALOR_HABER = 22;
	private static final int MAXIMA_LONGITUD_CUENTA = 153;
	private static final int MAX_LONGITUD_NUMERO = 30;
	private static final Long idEmpresa = Parametros.getIdEmpresa();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public AsientoHandler(AsientoModel model){
		this.model = model;
	}
	
	protected void registrarControlesCampos() {
		model.getTxtNumero().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO));
		model.getTxtReferencia().addKeyListener(new TextChecker(MAXIMA_LONGITUD_REFERENCIA));
		model.getTxtCuenta().addKeyListener(new TextChecker(MAXIMA_LONGITUD_CUENTA));
		model.getTxtGlosa().addKeyListener(new TextChecker(MAXIMA_LONGITUD_GLOSA));
		model.getTxtConcepto().addKeyListener(new TextChecker(MAXIMA_LONGITUD_OBSERVACIONES));
		model.getTxtGlosa().addKeyListener(new TextChecker(AsientoHandler.MAXIMA_LONGITUD_GLOSA));
		model.getTxtValorDebe().addKeyListener(new TextChecker(AsientoHandler.MAXIMA_LONGITUD_VALOR_DEBE));
		model.getTxtValorDebe().addKeyListener(new NumberTextFieldDecimal());
		model.getTxtValorHaber().addKeyListener(new TextChecker(AsientoHandler.MAXIMA_LONGITUD_VALOR_HABER));
		model.getTxtValorHaber().addKeyListener(new NumberTextFieldDecimal());
		model.getTxtReferencia().addKeyListener(new TextChecker(AsientoHandler.MAXIMA_LONGITUD_REFERENCIA));
		model.getTxtConcepto().addKeyListener(new TextChecker(AsientoHandler.MAXIMA_LONGITUD_OBSERVACIONES));
	}
	
	protected void enableCombos(CuentaIf cuenta) {
		try {
			if ("S".equals(cuenta.getDepartamento())) {
				model.getCmbDepartamento().setEnabled(true);
				SpiritComboBoxModel cmbDepartamentoModel = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(idEmpresa));
				model.getCmbDepartamento().setModel(cmbDepartamentoModel);

			} else {

				model.getCmbDepartamento().removeAllItems();
				model.getCmbDepartamento().setSelectedItem(null);
				model.getCmbDepartamento().setEnabled(false);
			}
			if ("S".equals(cuenta.getLinea())) {
				model.getCmbLinea().setEnabled(true);
				SpiritComboBoxModel cmbLineaModel = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(idEmpresa));
				model.getCmbLinea().setModel(cmbLineaModel);

			} else {
				model.getCmbLinea().removeAllItems();
				model.getCmbLinea().setSelectedItem(null);
				model.getCmbLinea().setEnabled(false);
			}
			if ("S".equals(cuenta.getEmpleado())) {
				model.getCmbEmpleado().setEnabled(true);
				SpiritComboBoxModel cmbEmpleadoModel = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(idEmpresa));
				model.getCmbEmpleado().setModel(cmbEmpleadoModel);
			} else {
				model.getCmbEmpleado().removeAllItems();
				model.getCmbEmpleado().setSelectedItem(null);
				model.getCmbEmpleado().setEnabled(false);
			}
			if (cuenta.getCentrogasto().equals("S")) {
				model.getCmbCentroGasto().setEnabled(true);
				SpiritComboBoxModel cmbCentroGastoModel = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getCentroGastoSessionService().findCentroGastoByEmpresaId(idEmpresa));
				model.getCmbCentroGasto().setModel(cmbCentroGastoModel);
			} else {
				model.getCmbCentroGasto().removeAllItems();
				model.getCmbCentroGasto().setSelectedItem(null);
				model.getCmbCentroGasto().setEnabled(false);
			}

			if (cuenta.getCliente().equals("S")) {
				model.getCmbCliente().setEnabled(true);
				Collection corporacionCollection = SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(idEmpresa);
				Iterator itCorporacionCollection = corporacionCollection.iterator();

				while (itCorporacionCollection.hasNext()) {
					CorporacionIf corporacionIf = (CorporacionIf) itCorporacionCollection.next();

					Collection clienteCollection = SessionServiceLocator.getClienteSessionService().findClienteByCorporacionId(corporacionIf.getId());
					Iterator itClienteCollection = clienteCollection.iterator();

					while (itClienteCollection.hasNext()) {
						ClienteIf clienteIf = (ClienteIf) itClienteCollection.next();

						model.getCmbCliente().addItem(clienteIf);

					}

				}
				model.getCmbCliente().setSelectedItem(null);
			} else {
				model.getCmbCliente().removeAllItems();
				model.getCmbCliente().setSelectedItem(null);
				model.getCmbCliente().setEnabled(false);
			}

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
 
}