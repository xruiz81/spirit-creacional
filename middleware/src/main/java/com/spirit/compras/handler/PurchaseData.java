package com.spirit.compras.handler;

import java.io.Serializable;

import com.spirit.client.SpiritConstants;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.medios.entity.OrdenMedioIf;

public class PurchaseData implements Serializable {
	/* Fields */
	CompraIf purchase;
	ClienteIf provider;
	
	public PurchaseData() {
		this.setPurchase(SpiritConstants.getNullValue());
		this.setProvider(SpiritConstants.getNullValue());
	}

	public CompraIf getPurchase() {
		return purchase;
	}

	public void setPurchase(Object purchase) {
		this.purchase = (purchase!=null)?(CompraIf) purchase:null;
	}

	public ClienteIf getProvider() {
		return provider;
	}

	public void setProvider(Object provider) {
		this.provider = (provider!=null)?(ClienteIf) provider:null;
	}
}
