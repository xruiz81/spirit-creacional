package com.spirit.compras.handler;

import java.io.Serializable;

import com.spirit.client.SpiritConstants;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.medios.entity.OrdenMedioIf;

public class OrderData implements Serializable {
	private static final long serialVersionUID = -4159089885538462538L;
	/* Fields */
	String orderType;
	OrdenCompraIf purchaseOrder;
	OrdenMedioIf mediaOrder;
	ClienteIf provider;
	
	public OrderData() {
		this.setOrderType(SpiritConstants.getEmptyCharacter());
		this.setPurchaseOrder(SpiritConstants.getNullValue());
		this.setMediaOrder(SpiritConstants.getNullValue());
		this.setProvider(SpiritConstants.getNullValue());
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public OrdenCompraIf getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(Object purchaseOrder) {
		this.purchaseOrder = (purchaseOrder!=null)?(OrdenCompraIf) purchaseOrder:null;
	}

	public OrdenMedioIf getMediaOrder() {
		return mediaOrder;
	}

	public void setMediaOrder(Object mediaOrder) {
		this.mediaOrder = (mediaOrder!=null)?(OrdenMedioIf) mediaOrder:null;
	}

	public ClienteIf getProvider() {
		return provider;
	}

	public void setProvider(Object provider) {
		this.provider = (provider!=null)?(ClienteIf) provider:null;
	}
}
