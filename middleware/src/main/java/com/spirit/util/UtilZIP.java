package com.spirit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UtilZIP {

	/**
	 * Agrega un archivo a un ZipOutputStream, al final cerrar el stream
	 * 
	 * @param pArchivoAComprimir
	 * @param out
	 * @return
	 */
	public static boolean addFiletoZipStream(File archivoComprimir,
			ZipOutputStream zipFileStream) {
		try {
			byte buf[] = new byte[1024];

			FileInputStream in = new FileInputStream(archivoComprimir);
			String name = archivoComprimir.getName();
			zipFileStream.putNextEntry(new ZipEntry(name));

			int len;
			while ((len = in.read(buf)) > 0) {
				zipFileStream.write(buf, 0, len);
			}
			zipFileStream.closeEntry();
			in.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

}
