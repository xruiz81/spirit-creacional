package com.spirit.cartera.gui.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.spirit.cartera.data.ObservacionesPago; /*
 * Created by JFormDesigner on Thu Sep 10 11:51:46 COT 2009
 */
import com.spirit.cartera.gui.controller.ObservacionesPagosTableModel;
import com.spirit.client.model.SpiritResourceManager;

/**
 * @author Antonio Seiler
 */
public class JDObservacionesPagos extends JDialog {
	private ObservacionesPagosTableModel observacionesPagosTableModel;
	public static int CONTINUAR=0;
	public static int CANCELAR=1;
	public static int OPCION_ELEGIDA=-1;
	
	private void customCode() {
		OPCION_ELEGIDA=CANCELAR;
		observacionesPagosTableModel = new ObservacionesPagosTableModel();
		tblObservaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblObservaciones.setModel(observacionesPagosTableModel);
		TableColumn anchoColumna = tblObservaciones.getColumn(
				tblObservaciones.getColumnName(0));
		anchoColumna.setMinWidth(40);
		anchoColumna = tblObservaciones
				.getColumn(tblObservaciones.getColumnName(1));
		anchoColumna.setMinWidth(160);
		anchoColumna = tblObservaciones
				.getColumn(tblObservaciones.getColumnName(2));
		anchoColumna.setMinWidth(120);
		anchoColumna = tblObservaciones
				.getColumn(tblObservaciones.getColumnName(3));
		anchoColumna.setMinWidth(235);
		anchoColumna = tblObservaciones
		.getColumn(tblObservaciones.getColumnName(4));
			anchoColumna.setMinWidth(50);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPCION_ELEGIDA=CANCELAR;
				dispose();
			}
		});
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OPCION_ELEGIDA=CONTINUAR;
				dispose();
			}
		});
	
	}
	
	

	public void setObservaciones(List<ObservacionesPago> listaObservaciones) {
		observacionesPagosTableModel.refresh(listaObservaciones);
	}

	public JDObservacionesPagos(Frame owner) {
		super(owner);
		initComponents();
		customCode();
	}

	public JDObservacionesPagos(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPane = new JPanel();
		scrollPane1 = new JScrollPane();
		tblObservaciones = new JTable();
		buttonBar = new JPanel();
		btnCancelar = new JButton();
		btnContinuar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Observaciones");
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());
			
			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow"));
				
				//======== scrollPane1 ========
				{
					
					//---- tblObservaciones ----
					tblObservaciones.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
						},
						new String[] {
							null, null, null, null
						}
					));
					scrollPane1.setViewportView(tblObservaciones);
				}
				contentPane.add(scrollPane1, cc.xy(1, 1));
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);
			
			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("pref")));
				
				//---- btnCancelar ----
				btnCancelar.setText("Cancelar");
				buttonBar.add(btnCancelar, cc.xy(4, 1));
				
				//---- btnContinuar ----
				btnContinuar.setText("Continuar");
				buttonBar.add(btnContinuar, cc.xy(6, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane2.add(dialogPane, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPane;
	private JScrollPane scrollPane1;
	private JTable tblObservaciones;
	private JPanel buttonBar;
	private JButton btnCancelar;
	private JButton btnContinuar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
