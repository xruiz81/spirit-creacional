package com.spirit.mailer.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.zip.ZipOutputStream;

import com.spirit.util.UtilArchivo;
import com.spirit.util.UtilZIP;

/**
 * Objecto que encapsula un correo electronico
 * 
 * @author Ricardo Andrade
 * 
 */
public class MailMessage implements Serializable {

	private static final long serialVersionUID = 3019162106232080436L;
	
	private String from;
	private String subject;
	private String body;
	private String[] to;
	private byte[][] attachments;
	private String[] fileNames;
	private boolean comprimirAttachments;
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String... to) {
		this.to = to;
	}

	public byte[][] getAttachments() {
		return attachments;
	}

	/**
	 * Metodo que recibe uno o varios attachments
	 * 
	 * @param attachmentsFiles
	 * @throws Exception
	 */
	public void addAttachments(File... attachmentsFiles) throws Exception {

		int i = 0;
		if (isComprimirAttachments()) {
			attachments = new byte[1][];
			fileNames = new String[1];
			File tmpFile = File.createTempFile("tmp", ".zip");
			tmpFile.deleteOnExit();
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					new FileOutputStream(tmpFile));
			for (File f : attachmentsFiles) {
				UtilZIP.addFiletoZipStream(f, zipOutputStream);
			}
			zipOutputStream.close();
			attachments[0] = UtilArchivo
					.getBytesFromStream(new FileInputStream(tmpFile));
			fileNames[0] = "attachments.zip";
		} else {
			attachments = new byte[attachmentsFiles.length][];
			fileNames = new String[attachmentsFiles.length];
			for (File f : attachmentsFiles) {
				FileInputStream fileInputStream = new FileInputStream(f);
				attachments[i] = UtilArchivo
						.getBytesFromStream(fileInputStream);
				fileNames[i] = f.getName();
				i++;
				fileInputStream.close();
			}
		}

	}

	public String[] getFileNames() {
		return fileNames;
	}

	public static void main(String[] args) throws Exception {
		File tmpFile = new File("C:\\zipex.zip");
		File testFile1 = new File("C:\\test.txt");
		File testFile2 = new File("C:\\test2.txt");
		tmpFile.createNewFile();
		ZipOutputStream zipOutputStream = new ZipOutputStream(
				new FileOutputStream(tmpFile));
		UtilZIP.addFiletoZipStream(testFile1, zipOutputStream);
		UtilZIP.addFiletoZipStream(testFile2, zipOutputStream);
		zipOutputStream.close();

	}

	public boolean isComprimirAttachments() {
		return comprimirAttachments;
	}

	public void setComprimirAttachments(boolean comprimirAttachments) {
		this.comprimirAttachments = comprimirAttachments;
	}

}
