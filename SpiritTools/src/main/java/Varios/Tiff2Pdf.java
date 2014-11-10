package Varios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;

public class Tiff2Pdf {

	public static void batch(File[] archivosTiff, File directorioDestino) {
		for (File f : archivosTiff) {
			tiff2Pdf(f, directorioDestino);
		}
	}

	public static void tiff2Pdf(File archivoTiff, File directorioDestino) {
		String tiff = archivoTiff.getName();
		String pdf = tiff.substring(0, tiff.lastIndexOf('.') + 1) + "pdf";
		Document document = new Document(PageSize.LETTER, 0, 0, 0, 0);
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(new File(directorioDestino, pdf)));
			int pages = 0;
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			RandomAccessFileOrArray ra = null;
			int comps = 0;
			try {
				ra = new RandomAccessFileOrArray(new FileInputStream(
						archivoTiff));
				comps = TiffImage.getNumberOfPages(ra);
			} catch (Throwable e) {
				System.out.println("Exception in " + tiff + " "
						+ e.getMessage());
				return;
			}
			System.out.println("Processing: " + tiff);
			for (int c = 0; c < comps; ++c) {
				try {
					Image img = TiffImage.getTiffImage(ra, c + 1);
					if (img != null) {
						System.out.println("page " + (c + 1));
						img.scalePercent(7200f / img.getDpiX(), 7200f / img
								.getDpiY());
						document.setPageSize(new Rectangle(
								img.getScaledWidth(), img.getScaledHeight()));
						img.setAbsolutePosition(0, 0);
						cb.addImage(img);
						document.newPage();
						++pages;
					}
				} catch (Throwable e) {
					System.out.println("Exception " + tiff + " page " + (c + 1)
							+ " " + e.getMessage());
				}
			}
			ra.close();
			document.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("done");

	}

	public static void main(String[] args) {
		File[] tiffs = new File(System.getProperty("user.dir") + File.separator
				+ "tiffs").listFiles();
		File out = new File(System.getProperty("user.dir") + File.separator
				+ "exported");
		for (File f : tiffs) {
			tiff2Pdf(f, out);
		}
	}
}
