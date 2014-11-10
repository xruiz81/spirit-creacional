package com.spirit.bpm.process;


public class MIGLoginInterceptor {
	/*@Resource
	private javax.ejb.SessionContext ctx;
	
	private LoginContext loginContext;

	protected void login(ClientParams clientParams) throws BPMException {
		try {
			if (loginContext != null)
				loginContext.logout();
			loginContext = new LoginContext(getContext(),
					new SimpleCallbackHandler(clientParams.getUser(),
							clientParams.getPassword()));
			loginContext.login();
		} catch (LoginException e) {
			handleException(e, "Usuario no autenticado");
		} catch (Exception e) {
			handleException(e);
		}
	}

	protected void logout() throws BPMException {
		try {
			if (loginContext != null)
				loginContext.logout();
		} catch (LoginException e) {
			handleException(e, "Usuario no autenticado");
		} catch (Exception e) {
			handleException(e);
		}
	}

	@AroundInvoke
	public Object logAction(InvocationContext ic) throws Exception {
		System.out.println("*** Login Interceptor invoked for "
				+ ic.getTarget() + " ***");
		ClientParams clientParams=(ClientParams)ic.getContextData().get("clientParams");
		login(clientParams);
		Object returnOb=ic.proceed();
		logout();
		return returnOb;
	}*/
}
