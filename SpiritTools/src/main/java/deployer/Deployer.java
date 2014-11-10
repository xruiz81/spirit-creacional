/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deployer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import persistence.Updater;

import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * 
 * @author lmunoz
 */
public class Deployer {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static String parseJarName(String jarName) {
		return jarName.replaceFirst(".jar", "");
	}

	public static void printJarResourse(String group, String id, String version) {
		System.out.println("<jarResource>");
		System.out.println("<groupId>" + group + "</groupId>");
		System.out.println("<artifactId>" + id + "</artifactId>");
		System.out.println("<version>" + version + "</version>");
		System.out.println("</jarResource>");
	}

	public static void ejecutarComando2() throws InterruptedException,
			IOException {

	}

	private static void imprimirProcess(Process p) throws IOException {
		InputStream is = p.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String aux = br.readLine();
		while (aux != null) {
			System.out.println(aux);
			aux = br.readLine();
		}
	}

	/**
	 * @param comando
	 */
	public static void ejecutarComando(String comando, File path) {
		try {
			Process p = Runtime.getRuntime().exec("cmd start /c " + comando,
					null, path);
			imprimirProcess(p);
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.getLogger(Deployer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void ejecutarComando(String comando) {
		try {
			Process p = Runtime.getRuntime().exec("cmd start /c " + comando);
			imprimirProcess(p);
		} catch (IOException ex) {
			Logger.getLogger(Deployer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void printInstall(String group, String id, String version,
			String path) {
		String comando = "mvn install:install-file -DgroupId=" + group
				+ " -DartifactId=" + id + " -Dpackaging=jar -Dversion="
				+ version + " -Dfile=" + path;
		System.out.println(comando);
		ejecutarComando(comando);
	}

	private static void recorrerDirectorio(String group, File[] archivos,
			boolean pom) {
		String id = "";
		String version = "1.0";
		for (File f : archivos) {
			id = parseJarName(f.getName());
			if (pom) {
				printJarResourse(group, id, version);
			} else {
				printInstall(group, id, version, f.getAbsolutePath());
			}
		}
	}

	public static void installJarFolder(String group, String ruta) {
		File folder = new File(ruta);
		recorrerDirectorio(group, folder.listFiles(new java.io.FileFilter() {

			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".jar");
			}
		}), false);
	}

	public static void printPom(String group, String ruta) {
		File folder = new File(ruta);
		recorrerDirectorio(group, folder.listFiles(new java.io.FileFilter() {

			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".jar");
			}
		}), true);
	}

	public static void installMavenModules() {
		String ruta = "";
		String groupId = "";

		/*ruta = "C:\\work\\client\\client-lib";
		groupId = "client-lib";
		installJarFolder(groupId, ruta);
		// printPom(groupId, ruta);
		//
		//ruta = "C:\\work\\client\\gui-lib";
		groupId = "gui-lib";
		installJarFolder(groupId, ruta);
		// printPom(groupId, ruta);
		//*/
		//ruta = "C:\\work\\mail";
		//groupId = "mail";
		//installJarFolder(groupId, ruta);
		// printPom(groupId, ruta);
		//        
		/*ruta = "C:\\work\\client\\jbpm-lib";
		groupId = "jbpm-lib";
		installJarFolder(groupId, ruta);
		// printPom(groupId, ruta);*/

		/*ruta = "C:\\work\\client\\extra-lib";
		groupId = "pos-lib";
		installJarFolder(groupId, ruta);
		// printPom(groupId, ruta);
		
		 */
		
		/*ruta = "C:\\work\\jasper";
		groupId = "jasper-lib";
		installJarFolder(groupId, ruta);*/
		
		ruta = "C:\\work\\lookandfeel";
		groupId = "gui-lib";
		installJarFolder(groupId, ruta);
	}

	public static String aumentarVersion(String versionAnterior) {
		// Se espera una version de este tipo: x.x.x
		String[] tokens = versionAnterior.split("\\.");
		String ultimoDigitoString = tokens[tokens.length - 1];
		int ultimoDigito = Integer.parseInt(ultimoDigitoString);
		return (versionAnterior.substring(0, versionAnterior.length()
				- ultimoDigitoString.length()) + (++ultimoDigito));
	}

	public static void FileCopy(File inFile, File outFile) {
		try {
			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
			int c;
			while ((c = in.read()) != -1)
				out.write(c);

			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}
	
	
	public static void modificarPersistenceXML2(String version) throws Exception {
		File directorioWorkSpace = new File(System.getProperty("user.dir"))
				.getParentFile();
		File persistenceXML = new File(directorioWorkSpace.getPath()
				+ File.separator + "comun-server" + File.separator + "src"
				+ File.separator + "main" + File.separator + "resources"
				+ File.separator + "META-INF" + File.separator
				+ "persistence.xml");
		String jarTag="../middleware-" + version + ".jar";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(persistenceXML);
		NodeList listaNodos = doc.getElementsByTagName("jar-file");
		if (listaNodos == null || listaNodos.getLength() <= 0) {
			Node n = doc.getElementsByTagName("persistence-unit").item(0);

			Element childElement = doc.createElement("jar-file");
			childElement.setTextContent(jarTag);
			n.appendChild(childElement);
		} else {
			Node childElement = listaNodos.item(0);
			childElement.setTextContent(jarTag);
		}
		// setting up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();

		// generating string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		String xmlString = sw.toString();

		// Saving the XML content to File
		OutputStream f0;
		byte buf[] = xmlString.getBytes();
		f0 = new FileOutputStream(persistenceXML);
		for (int i = 0; i < buf.length; i++) {
			f0.write(buf[i]);
		}
		f0.close();
		buf = null;

	}


	public static void buildEar() {

		File directorioWorkSpace = new File(System.getProperty("user.dir"))
				.getParentFile();

		File archivoProperties = new File(directorioWorkSpace.getPath()
				+ File.separator + "base" + File.separator + "src"
				+ File.separator + "main" + File.separator + "resources"
				+ File.separator + "conf" + File.separator
				+ "version.properties");

		System.out.println(archivoProperties);

		Properties properties = new Properties();
		String versionAnterior = null;
		String versionNueva = null;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(archivoProperties);

			properties.load(fileInputStream);
			versionAnterior = (String) properties.get("version");

			versionNueva = aumentarVersion(versionAnterior);
			System.out.println("Constuyendo version: " + versionNueva);

			properties.put("version", versionNueva);
			fileOutputStream = new FileOutputStream(archivoProperties);
			properties.store(fileOutputStream, null);
			fileInputStream.close();
			fileOutputStream.close();
			
			//LLAMAR AL REGENERADOR DE PERSISTENCE.XML
			Updater.updatePersistanceXML();
			//modificarPersistenceXML(versionNueva);
			
			/** *********************************************** */
			System.out.println("Actualizando version en el POM");

			File archivoPom = new File(directorioWorkSpace, "pom.xml");
			if (!archivoPom.exists()) {
				File archivoPomAll = new File(directorioWorkSpace.getPath()
						+ File.separator + "base" + File.separator
						+ "pomall2.0.xml");
				FileCopy(archivoPomAll, archivoPom);
			}

			modificarValorXML(archivoPom, "/project/version", versionNueva);

			System.out.println("Actualizando POMs hijos");

			ejecutarComando("mvn -N versions:update-child-modules",
					directorioWorkSpace);
			System.out.println("Construyendo EAR POMs hijos");
			System.out.println("archivo: "+directorioWorkSpace);
			ejecutarComando("mvn clean install -Dmaven.test.skip -e --offline",
					directorioWorkSpace);

			System.out.println("Fin del proceso..");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void modificarValorXML(File archivoXml, String xPathQuery,
			String valorNuevo) throws Exception {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(archivoXml);
		doc.getDocumentElement().normalize();

		Node nodoQuery = XPathAPI.selectSingleNode(doc, xPathQuery);
		nodoQuery.getChildNodes().item(0).setNodeValue(valorNuevo);

		TransformerFactory fact = TransformerFactory.newInstance();
		Transformer trans = fact.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult fdst = new StreamResult(archivoXml);
		trans.transform(source, fdst);
	}

	public static void main(String[] args) throws IOException {
		// Maven >= 2.0.9
		// Ejecutar si las librerias del cliente no estan instaladas en
		// el repositorio de maven local..
		//installMavenModules();

		// Ejecutar para construir una nueva version de un EAR
		buildEar();

	}
}