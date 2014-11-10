/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deployer;

/**
 *
 * @author lmunoz
 */
class PomGenerator {

    public static void openTag(String nombreTag) {
        System.out.println("<" + nombreTag + ">");
    }

    public static void closeTag(String nombreTag) {
        System.out.println("</" + nombreTag + ">");
    }

    public static void printTag(String nombreTag, String valor) {
        openTag(nombreTag);
        System.out.println(valor);
        closeTag(nombreTag);
    }

    public static void generarPadre() {
        openTag("parent");
        printTag("groupId", "biz.versality");
        printTag("artifactId", "spirit");
        printTag("version", "1.0-SNAPSHOT");
        closeTag("parent");
    }

    public static void printDependency(String dependencyName) {
        openTag("dependency");
        printTag("groupId", "${project.parent.groupId}");
        printTag("artifactId", dependencyName);
        closeTag("dependency");
    }

    public static void main(String[] args) {
        //String[] dependencias = {"base","general-client","compras-client","bpm-server"};
        //String[] dependencias = {"base","bpm-server"};
        //String[] dependencias = {"base","general-server","contabilidad-server","crm-server","crm-client","seguridad-server","inventario-client","cartera-server","compras-server","comun-server","nomina-client"};
        //String[] dependencias = {"general-server","general-client","seguridad-server","comun-server"};
        //String[] dependencias = {"base","general-server","general-client","crm-server","facturacion-server","medios-server","cartera-server","comun-server"};
        //String[] dependencias = {"base","general-server"};
        //String[] dependencias = {"base","general-server","seguridad-server","medios-server","seguridad-server","crm-server","nomina-server","comun-server"};
        //String[] dependencias = {"base","general-server","seguridad-server","medios-server","seguridad-server","crm-server","nomina-server","comun-server"};
        //String[] dependencias = {"base","comun-server"};
        //String[] dependencias = {"base","general-client","inventario-server","crm-server","crm-client","comun-server"};
        //String[] dependencias = {"base","general-server","general-client","comun-server"};
        //String[] dependencias = {"base","general-server","general-client","nomina-server","contabilidad-server","comun-server"};
        //String[] dependencias = {"base","general-server","crm-server","nomina-server","comun-server"};
        String[] dependencias = {"base","general-server","compras-server","contabilidad-server","crm-server","inventario-server","facturacion-server","cartera-server","comun-server"};
        String name = "sri-server";
        String paquete = "ejb";

        System.out.println("<project" +
                " xmlns = \"http://maven.apache.org/POM/4.0.0\"" +
                "xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\"" +
                "xsi:schemaLocation = \"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">");

        generarPadre();
        printTag("name", name);
        printTag("groupId", "${project.parent.groupId}");
        printTag("artifactId", "${project.name}");
        printTag("version", "${project.parent.version}");
        printTag("modelVersion", "4.0.0");
        printTag("packaging", paquete);
        openTag("dependencies");
        for (String dependencia : dependencias) {
            printDependency(dependencia);
        }
        closeTag("dependencies");
        System.out.println("</project>");
    }
}
