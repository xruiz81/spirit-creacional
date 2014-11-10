package com.spirit.compras.gui.controller;

import java.util.Iterator;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class PurchaseConstants {
	/* Global purchase constants */
	private static final String CODE_PURCHASE_MODULE = "COMP";
	private static final String SEND_STATUS = "ENVIADA";
	private static final String ENTERED_STATUS = "INGRESADA";
	private static final String NULLIFY_STATUS = "ANULADA";
	private static final int JDIALOG_ASOCIAR_ORDEN_WIDTH = 725;
	private static final int JDIALOG_ASOCIAR_ORDEN_HEIGHT = 470;

	public static String getCodePurchaseModule() {
		return CODE_PURCHASE_MODULE;
	}

	public static String getSendStatus() {
		return SEND_STATUS;
	}
	
	public static String getEnteredStatus() {
		return ENTERED_STATUS;
	}

	public static String getNullifyStatus() {
		return NULLIFY_STATUS;
	}

	public static int getJdialogAsociarOrdenWidth() {
		return JDIALOG_ASOCIAR_ORDEN_WIDTH;
	}

	public static int getJdialogAsociarOrdenHeight() {
		return JDIALOG_ASOCIAR_ORDEN_HEIGHT;
	}

	@SuppressWarnings("unchecked")
	public static ModuloIf getPurchaseModule() throws GenericBusinessException {
		ModuloIf module = null;
		Iterator<ModuloIf> it = SessionServiceLocator.getModuloSessionService().findModuloByCodigo(getCodePurchaseModule()).iterator();
		if (it.hasNext())
			module = (ModuloIf) it.next();
		return module;
	}
}
