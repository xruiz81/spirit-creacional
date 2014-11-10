package com.spirit.client.model;

import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;

public abstract class ReportModelImpl extends SpiritModelImpl {
	
	public abstract void clean();
	
	public abstract void showSaveMode();
	
	public abstract void report();
	
	public void refresh(){
		//TODO
	}
	
	public void find() {
		// Report no usa find
	}
	
	public void save() {
		// Report no usa save
	}
	
	public void update() {
		// Report no usa update
	}
	
	public void delete() {
		// Report no usa delete
	}
	
	public void duplicate() {
		// Report no usa duplicate
	}
	
	public boolean validateFields() {
		// Report no usa validateFields
		return false;
	}
	
	public boolean isEmpty() {
		// Report no usa isEmpty().
		return false;
	}
	
	public void showFindMode() {
		// Report no usa showFindMode
	}
	
	public void showUpdateMode() {
		// Report no usa showUpdateMode
		
	}
	
	public void addDetail() {
		// Report no usa addDetail
		
	}
	
	public void updateDetail() {
		// Report no usa updateDetail
		
	}
	
	public void deleteDetail() {
		// Report no usa deleteDetail
	}
	
	public void switchTab() {}
	
	public void quickSearch() {
		System.out.println("Quick Search desde Report Model");
	}
	
	public static void processReportToPDF(String fileName, HashMap<String, Object> parametrosMap, JRDataSource dataSource, String file) {
		System.out.println("dos");
		JasperPrint jasperPrint = null;
		//JRRtfExporter jrRtfExporter = new JRRtfExporter();
		try {
			if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
				System.out.println("tres");
				jasperPrint = JasperFillManager.fillReport(
						SpiritResourceManager.getInputStreamResource(Parametros.getRutaCarpetaReportes()+"/"+fileName),
						parametrosMap,
						dataSource);
			}				
			else 
				SpiritAlert.createAlert("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!", SpiritAlert.ERROR);
			
			System.out.println("cuatro");
			JasperExportManager.exportReportToPdfFile(jasperPrint, file);
			System.out.println("cinco");
			
		} catch (JRException e) {
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public static void processReportToPDFStream(String fileName, HashMap<String, Object> parametrosMap, JRDataSource dataSource, FileOutputStream pdfStream) {
		JasperPrint jasperPrint = null;
		//JRRtfExporter jrRtfExporter = new JRRtfExporter();
		try {
			if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
				jasperPrint = JasperFillManager.fillReport(
						SpiritResourceManager.getInputStreamResource(Parametros.getRutaCarpetaReportes()+"/"+fileName),
						parametrosMap,
						dataSource);
			else 
				SpiritAlert.createAlert("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!", SpiritAlert.ERROR);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, pdfStream);
		} catch (JRException e) {
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public static void processReport(String fileName, HashMap<String, Object> parametrosMap, JRDataSource dataSource, boolean viewReport) {
		JasperPrint jasperPrint = null;
		//JRRtfExporter jrRtfExporter = new JRRtfExporter();
		try {
			if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
				jasperPrint = JasperFillManager.fillReport(
						SpiritResourceManager.getInputStreamResource(Parametros.getRutaCarpetaReportes()+"/"+fileName),
						parametrosMap,
						dataSource);
			else 
				SpiritAlert.createAlert("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!", SpiritAlert.ERROR);
			/*jrRtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			jrRtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, sPathRTF);
			jrRtfExporter.exportReport();*/
			
			/*try {
		        //JRTextExporter exporter = new JRTextExporter();
				//File file = new File("C:\\facturaActual.txt");
				JRPdfExporter exporter= new JRPdfExporter();
		        File file = new File("C:\\facturaActual.pdf");
		        exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE, file);
		        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(250));
		        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(50));		        
		        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(2));
		        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(4));
		        exporter.exportReport();		        
		    } catch (JRException jRException) {
		        System.err.println(jRException);
		    }*/		
		    
		    
			if ( Parametros.isVistaPreviaImpresion() ){
				JasperViewer.viewReport(jasperPrint, false);
			} else {
				JasperPrintManager.printReport(jasperPrint, true);
				if (viewReport)
					JasperViewer.viewReport(jasperPrint, false);
			}
		} catch (JRException e) {
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public static void processReport(String fileName, HashMap parametrosMap, Collection dataSourceCollection, boolean viewReport) {
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataSourceCollection);
		processReport(fileName, parametrosMap, dataSource, viewReport);
	}
	
	public static void processReport(String fileName, HashMap parametrosMap, DefaultTableModel tableModel, boolean viewReport) {
		JRDataSource dataSource = new JRTableModelDataSource(tableModel);
		processReport(fileName, parametrosMap, dataSource, viewReport);
	}
	
	public static void processReport(String fileName, HashMap<String,Object> parametrosMap, JREmptyDataSource emptyDataSource, boolean viewReport) {
		JRDataSource dataSource = emptyDataSource;
		processReport(fileName, parametrosMap, dataSource, viewReport);
	}
}
