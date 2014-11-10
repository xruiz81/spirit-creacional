package com.spirit.inventario.helper;

import com.spirit.exception.GenericBusinessException;

public class StockException extends GenericBusinessException{

	public StockException(String cause) {
		super(cause);
	}

}
