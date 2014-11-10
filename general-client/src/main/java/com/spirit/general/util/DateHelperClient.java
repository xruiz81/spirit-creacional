package com.spirit.general.util;

import java.sql.Timestamp;
import java.util.Date;

import com.spirit.exception.GenericBusinessException;

public class DateHelperClient {

	public static Timestamp getTimeStamp(Date fecha) {
		return new Timestamp(fecha.getTime());
	}

	public static Timestamp getTimeStampNow() {
		return getTimeStamp(new Date());
	}
}
