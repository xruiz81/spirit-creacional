package com.spirit.facturacion.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.WindowConstants;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JDIngresarPreimpreso;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.util.TextChecker;

public class IngresarPreimpresoModel extends JDIngresarPreimpreso {
	//private Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{7}");
	//private static final int MAX_LONGITUD_PREIMPRESO = 15;
	FacturaIf factura;
	String preimpreso = null;
	
	public IngresarPreimpresoModel(Frame owner, FacturaIf factura) {
		super(owner);
		this.factura = factura;
		this.setTitle("Preimpreso F A C T U R A");
		initKeyListeners();
		initListeners();
		int width = 300;
		int height = 250;
		setSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(x, y);
		pack();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void initKeyListeners() {
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
				
		//getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
	}
	
	private void initListeners() {
		getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					aceptar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});
		
		getTxtPreimpreso().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						aceptar();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void aceptar() throws CloneNotSupportedException {
		if (validateFields()) {
			try {
				String preimpreso = getTxtPreimpreso().getText();
				SessionServiceLocator.getFacturaSessionService().actualizarPreimpreso(factura, preimpreso, Parametros.isActivarReplicacion());
				this.preimpreso = preimpreso;
				SpiritAlert.createAlert("Preimpreso actualizado con éxito", SpiritAlert.INFORMATION);
				this.dispose();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al actualizar el preimpreso", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private boolean validateFields() {
		Pattern patron = Pattern.compile(Parametros.getPatronPreimpreso());
		String preimpreso = getTxtPreimpreso().getText();
		Matcher matcher = patron.matcher(preimpreso);
		//boolean encontrado = matcher.find();
		boolean encontrado = matcher.matches();
		if (!encontrado) {
			SpiritAlert.createAlert("Formato de preimpreso debe ser: ###-###-#######", SpiritAlert.WARNING);
			getTxtPreimpreso().grabFocus();
			return false;
		}
		
		try {
			Map queryMap = new HashMap();
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
			queryMap.put("idEmpresa", Parametros.getIdEmpresa());
			queryMap.put("tipoDocumentoFactura", tipoDocumento.getFactura());
			queryMap.put("tipoDocumentoNotaVenta", tipoDocumento.getNotaVenta());
			queryMap.put("tipoDocumentoNotaCredito", tipoDocumento.getNotaCredito());
			queryMap.put("preimpresoNumero", preimpreso);
			Iterator it = SessionServiceLocator.getFacturaSessionService().findFacturaPreimpresoDuplicadoByQuery(queryMap).iterator();
			if (it.hasNext()) {
				SpiritAlert.createAlert("Preimpreso duplicado. Ingréselo nuevamente.", SpiritAlert.WARNING);
				getTxtPreimpreso().grabFocus();
				return false;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al validar preimpreso.", SpiritAlert.ERROR);
		}

		return true;
	}

	public String getPreimpreso() {
		return preimpreso;
	}

	public void setPreimpreso(String preimpreso) {
		this.preimpreso = preimpreso;
	}
	 
}
