package com.spirit.server;

import java.net.URL;
import java.util.Hashtable;

import org.jboss.ejb3.embedded.EJB3StandaloneBootstrap;
import org.jboss.ejb3.embedded.EJB3StandaloneDeployer;

import com.spirit.client.SplashScreen;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.servicelocator.JNDIName;

public class StandAloneServer {

	private static Hashtable getInitialContextProperties() {
		Hashtable props = new Hashtable();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.LocalOnlyContextFactory");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		return props;
	}

	public static void start(SplashScreen screen) {
		System.out.println(System.getProperty("java.class.path"));
		try {
			Updater.updatePersistanceXML();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		System.out.println("Init");
		JNDIName.earName = ""; // Eliminado el nombre JDNI para que pueda
		// trabajar en modo standalone.
		EJB3StandaloneBootstrap.ignoredJars.add("rt.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("ojdbc14.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jboss-ejb3-all.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("thirdparty-all.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("hibernate-all.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jcainflow.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jms-ra.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("forms-1.0.5.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("l2fprod-common-all.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("looks-1.3b1.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("uif-1.4b2.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-action.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-beaninfo.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-components.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-dialogs.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-dock.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-shortcut.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jdom.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-common.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jide-grids.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("velocity-dep-1.4.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("xerces.jar");
		System.out.println("Init 2");

		if (screen != null)
			screen.setProgress("29%", 29);
		EJB3StandaloneBootstrap.boot(null);
		if (screen != null)
			screen.setProgress("42%", 42);

		EJB3StandaloneBootstrap.deployXmlResource("jboss-jms-beans.xml");
		System.out.println("Init 3");
		EJB3StandaloneBootstrap.deployXmlResource("jms-config.xml");
		System.out.println("Init 4");

		//EJB3StandaloneBootstrap.scanClasspath("C:\\workspace\\main\\target\\test-classes,C:\\workspace\\main\\target\\classes,C:\\workspace\\base\\target\\test-classes,C:\\workspace\\base\\target\\classes,C:\\work\\apache-commons\\commons-logging-1.1.jar,C:\\work\\apache-commons\\commons-beanutils-1.7.jar,C:\\work\\apache-commons\\commons-collections-3.1.jar,C:\\work\\apache-commons\\commons-digester-1.7.jar,C:\\work\\Jide\\uif-1.4b2.jar,C:\\work\\Jide\\animation-1.1.3.jar,C:\\work\\Jide\\forms-1.0.5.jar,C:\\work\\Jide\\jide-action.jar,C:\\work\\Jide\\jide-beaninfo.jar,C:\\work\\Jide\\jide-common.jar,C:\\work\\Jide\\jide-components.jar,C:\\work\\Jide\\jide-designer.jar,C:\\work\\Jide\\jide-dialogs.jar,C:\\work\\Jide\\jide-dock.jar,C:\\work\\Jide\\jide-grids.jar,C:\\work\\Jide\\jide-key.jar,C:\\work\\Jide\\jide-pivot.jar,C:\\work\\Jide\\jide-shortcut.jar,C:\\work\\Jide\\l2fprod-common-all.jar,C:\\work\\Jide\\looks-1.3b1.jar,C:\\work\\Logger\\log4j-1.2.15.jar,C:\\work\\Persistence\\hibernate-all.jar,C:\\work\\Test\\junit-4.0.jar,C:\\work\\Test\\dbunit-2.1.jar,C:\\work\\Jasper\\jasperreports-3.1.4.jar,C:\\work\\Jasper\\jcommon-1.0.15.jar,C:\\work\\Jasper\\itext-2.1.7.jar,C:\\work\\Jasper\\poi-3.7-20101029.jar,C:\\work\\JBoss\\thirdparty-all.jar,C:\\work\\JBoss\\antlr-2.7.6.jar,C:\\work\\JBoss\\javassist-3.9.0.GA.jar,C:\\work\\JBoss\\jboss-ejb3-all.jar,C:\\work\\JBoss\\jcainflow.jar,C:\\work\\JBoss\\jcainflow.zip,C:\\work\\JBoss\\jms-ra.jar,C:\\work\\JBoss\\jms-ra.zip,C:\\workspace\\bpm-client\\target\\classes,C:\\work\\JBPM\\jbpm-3.1.3-0.jar,C:\\workspace\\compras-client\\target\\classes,C:\\workspace\\general-client\\target\\classes,C:\\workspace\\comun-server\\target\\test-classes,C:\\workspace\\comun-server\\target\\classes,C:\\workspace\\jms-spirit\\bin,C:\\work\\Castor\\jakarta-oro-2.0.8.jar,C:\\work\\Castor\\castor-1.0.1-xml.jar,C:\\work\\Xerces\\xercesImpl.jar,C:\\work\\quartz\\scheduler-plugin.jar,C:\\workspace\\crm-client\\target\\classes,C:\\workspace\\contabilidad-client\\target\\classes,C:\\workspace\\nomina-client\\target\\classes,C:\\workspace\\inventario-client\\target\\classes,C:\\workspace\\medios-client\\target\\classes,C:\\workspace\\cartera-client\\target\\classes,C:\\workspace\\facturacion-client\\target\\classes,C:\\workspace\\pos-client\\target\\classes,C:\\workspace\\seguridad-client\\target\\classes,C:\\work\\Pos\\poskbd.jar,C:\\work\\Pos\\ibmuposst.jar,C:\\work\\Pos\\jpos1911.jar,C:\\work\\Pos\\jpos_sysmgmt.jar,C:\\work\\Pos\\jpos-common.jar,C:\\work\\Pos\\jsio.jar,C:\\work\\Pos\\jsr80.jar,C:\\work\\Pos\\jtux.jar,C:\\work\\Pos\\jutil.jar,C:\\work\\Pos\\posj.jar,C:\\workspace\\rrhh-client\\target\\classes,C:\\workspace\\sri-client\\target\\classes,C:\\work\\DBDrivers\\ojdbc14.jar");
		EJB3StandaloneBootstrap.scanClasspath();

		System.out.println("Init 5");
		if (screen != null)
			screen.setProgress("88%", 88);
		EJB3StandaloneDeployer deployer = EJB3StandaloneBootstrap
				.createDeployer();
	

		System.out.println("Init 6");
		if (screen != null)
			screen.setProgress("91%", 91);
		deployer.setJndiProperties(getInitialContextProperties());
		System.out.println("Init 7");

		try {
			deployer.create();
			System.out.println("Init 8");
			deployer.start();
			System.out.println("Init 9");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (screen != null)
			screen.setProgress("100%", 100);
	}

	public static URL getDeployDirURL() throws Exception {
		// Usually you would hardcode your URL. This is just a way to make it
		// easier for the tutorial
		// code to configure where the resource is.
		URL res = SpiritResourceManager.getUrl(
				"/META-INF/persistence.xml");
		System.out.println("RES: "+res.getPath());
		URL res1= EJB3StandaloneDeployer.getDeployDirFromResource(res,
				"META-INF/persistence.xml");
		System.out.println("RES1 : "+res1.getPath());
		return res1;
	}

}
