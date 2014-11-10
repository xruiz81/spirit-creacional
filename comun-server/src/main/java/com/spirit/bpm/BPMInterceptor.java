package com.spirit.bpm;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BPMInterceptor {
	private boolean bpmActivo = false;

	@AroundInvoke
	public Object performaceCheck(InvocationContext ic) throws Exception {
		if (bpmActivo) {
			Object ret = ic.proceed(); 
			return ret;
		} else {
			System.out.println("BPM no esta Activo");
			return null;
		}
	}
}
