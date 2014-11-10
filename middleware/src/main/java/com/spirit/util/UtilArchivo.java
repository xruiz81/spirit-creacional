package com.spirit.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UtilArchivo {

	public static byte[] getBytesFromStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 2];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
		} catch (IOException ioe) {
			throw ioe;
		}
		return baos.toByteArray();
	}
}
