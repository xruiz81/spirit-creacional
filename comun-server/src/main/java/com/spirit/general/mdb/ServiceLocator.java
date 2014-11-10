package com.spirit.general.mdb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * <code>ServiceLocator</code> encapsulates JNDI lookups to make it easier to
 * access JNDI-based resources (EJBs, DataSources, JMS Destinations, and so on).
 * 
 */
public class ServiceLocator {

	private static Properties localProperties = null;
	private static Context localContext = null;

	synchronized public static Context getContext(String ip, String port) {
		if (localProperties == null) {
			localProperties = new Properties();
			localProperties.clear();
			localProperties.setProperty("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			localProperties.setProperty("java.naming.factory.url.pkg",
					"org.jboss.naming:org.jnp.interfaces");
			localProperties.setProperty("java.naming.provider.url", ip + ":"
					+ port);
		}
		if (localContext == null) {
			try {
				localContext = new InitialContext(localProperties);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return localContext;
	}
}
