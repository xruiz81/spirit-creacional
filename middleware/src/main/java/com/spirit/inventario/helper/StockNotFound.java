package com.spirit.inventario.helper;

import com.spirit.exception.GenericBusinessException;

public class StockNotFound extends GenericBusinessException{

	public StockNotFound(String message) {
		super(message);
	}

}
