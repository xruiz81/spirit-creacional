package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class Updater {

	private static List<File> directoriosEntity = new ArrayList<File>();
	private static List<File> directoriosSession = new ArrayList<File>();

	private static void getEntityDir(File directorioPadre) {
		for (File f : directorioPadre.listFiles()) {
			if (f.isDirectory()) {
				if (f.getName().endsWith("entity")) {
					directoriosEntity.add(f);
				}
				getEntityDir(f);
			}
		}

	}

	private static void getSessionDir(File directorioPadre) {
		for (File f : directorioPadre.listFiles()) {
			if (f.isDirectory()) {
				getSessionDir(f);
			} else {
				if (f.getName().equals("Root")) {
					directoriosSession.add(f);
				}
			}
		}

	}
	
	

	private static String getSrcFolder(String proyect) {
		return proyect + File.separator + "src" + File.separator + "main"
				+ File.separator + "java";
	}

	private static String getResourceFolder(String proyect) {
		return proyect + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources";
	}

	public static void updatePersistanceXML() throws Exception {
		String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<persistence>\n" + "<persistence-unit name=\"spirit\">\n"
				+ "<jta-data-source>java:/OracleDS</jta-data-source>\n";

		File workspace = new File(System.getProperty("user.dir"))
				.getParentFile();
		File middleware = new File(workspace, File.separator + "middleware");
		File comunServer = new File(workspace, File.separator + "comun-server");

		String rutaDirectorioEntitysSrc = getSrcFolder(middleware
				.getAbsolutePath());
		String rutaComunServerSrc = getSrcFolder(comunServer.getAbsolutePath());
		String rutaComunServerResource = getResourceFolder(comunServer
				.getAbsolutePath());

		String replace = rutaDirectorioEntitysSrc.replaceAll("\\\\", "@");
		File directorioPadre = new File(rutaDirectorioEntitysSrc);
		getEntityDir(directorioPadre);
		String tmp;
		for (File f : directoriosEntity) {
			for (File file : f.listFiles()) {
				if (file.getName().endsWith("EJB.java")) {
					tmp = file.getAbsolutePath().replaceAll("\\\\", "@");
					tmp = tmp.replaceAll(replace, "");
					tmp = tmp.replaceAll("@", ".");
					tmp = tmp.replaceAll(".java", "");
					tmp = tmp.substring(1);
					tmp = "<class>" + tmp + "</class>\n";
					xmlFile += tmp;
				}
			}
		}
		xmlFile += "<properties>\n"
				+ "<property name=\"hibernate.show_sql\" value=\"true\" />\n"
				+ "</properties>\n" + "</persistence-unit>\n"
				+ "</persistence>\n";
		System.out.println(xmlFile);
		FileWriter fos = new FileWriter(new File(rutaComunServerSrc, "META-INF"
				+ File.separator + "persistence.xml"));
		fos.append(xmlFile);
		fos.flush();
		fos.close();

		FileWriter fos2 = new FileWriter(new File(rutaComunServerResource,
				"META-INF" + File.separator + "persistence.xml"));
		fos2.append(xmlFile);
		fos2.flush();
		fos2.close();

	}

	private static void checkFiles(ArrayList<String> ejbIfs,
			ArrayList<String> ifList, List<File> listaDirs, String replace)
			throws ClassNotFoundException {
		String tmp;
		Class c;
		int countIfs;
		for (File f : listaDirs) {
			tmp = f.getAbsolutePath().replaceAll("\\\\", "@");
			tmp = tmp.replaceAll(replace, "");
			tmp = tmp.replaceAll("@", ".");
			tmp = tmp.replaceAll(".java", "");
			tmp = tmp.substring(1);
			c = Class.forName(tmp);
			boolean isEJB = false;
			for (Annotation a : c.getAnnotations()) {
				if (a.toString().startsWith("@javax.ejb.Stateless("))
					isEJB = true;
				else if (a.toString().startsWith("@javax.ejb.Local(")) {
					// System.out.println(tmp);
					if (ifList.contains(tmp)) {
						System.out.println("REPETIDO!!" + tmp);
					}
					ifList.add(tmp);
				} else if (a.toString().startsWith("@javax.ejb.Remote(")) {
					// System.out.println(tmp);
					if (ifList.contains(tmp)) {
						System.out.println("REPETIDO!!" + tmp);
					}
					ifList.add(tmp);
				}

			}
			if (!isEJB) {
				continue;
			}
			countIfs = 0;
			for (Class ifs : c.getInterfaces()) {
				ejbIfs.add(ifs.getName());
				countIfs++;
			}
			if (countIfs < 2) {
				// System.out.println("PILAS COn: " + tmp + " " + countIfs);
				String buscar;
				for (Class ifs : c.getInterfaces()) {
					// System.out.println(" "+ifs);
					if (ifs.getName().endsWith("Remote")) {
						buscar = ifs.getName().replaceAll("Remote", "Local");
					} else if (ifs.getName().endsWith("Local")) {
						buscar = ifs.getName().replaceAll("Local", "Remote");
					} else {
						System.out.println("if rara; " + ifs.getName());
						continue;
					}

					try {
						Class.forName(buscar);
						System.out.println("-------------Esta es:         "
								+ buscar);
					} catch (Exception e) {
						// System.out.println("No existe: " + buscar);
					}
				}
			}
		}
	}

	public static void findReferences() throws ClassNotFoundException {
		File workspace = new File(System.getProperty("user.dir"))
				.getParentFile();
		File middleware = new File(workspace, File.separator + "middleware");
		File comunServer = new File(workspace, File.separator + "comun-server");

		String rutaSrcComun = getSrcFolder(comunServer.getAbsolutePath());
		String rutaSrcMiddle = getSrcFolder(middleware.getAbsolutePath());
		ArrayList<String> ifList = new ArrayList<String>();
		ArrayList<String> ejbIfs = new ArrayList<String>();
		File directorioPadre = new File(rutaSrcComun);
		getSessionDir(directorioPadre);
		String replace = rutaSrcComun.replaceAll("\\\\", "@");
		checkFiles(ejbIfs, ifList, directoriosSession, replace);
		directoriosSession.clear();

		directorioPadre = new File(rutaSrcMiddle);
		replace = rutaSrcMiddle.replaceAll("\\\\", "@");
		getSessionDir(directorioPadre);
		checkFiles(ejbIfs, ifList, directoriosSession, replace);
		System.out.println(ejbIfs.size());
		System.out.println(ifList.size());
		for (String s : ifList) {
			if (!ejbIfs.contains(s))
				System.out.println(s);
		}

	}
	
	public static void changeRoot() throws ClassNotFoundException, IOException {
		File workspace = new File(System.getProperty("user.dir"))
				.getParentFile();

		getSessionDir(workspace);
		String replace = ":extssh:rag@192.168.100.7:/var/cvs";
		FileWriter fos2 = null;

		for(File f:directoriosSession)
		{
			System.out.println(f);
			fos2 = new FileWriter(f);
			fos2.append(replace);
			fos2.flush();
			fos2.close();
		}
	}

	public static void main(String[] args) throws Exception {
		//findReferences();
		changeRoot();
	}
}
