package com.spirit.general.gui.controller;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;

public abstract class JDPopupFinder extends JDialogModelImpl {
	public JDPopupFinder(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPopupFinder(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		panelTodos = new JPanel();
		panelBarraTitulo = new JPanel();
		btnBuscar = new JButton();
		btnAceptar = new JButton();
		btnIrPrimerosRegistros = new JButton();
		btnIrAnteriorRegistro = new JButton();
		btnIrSiguienteRegistro = new JButton();
		btnIrUltimosRegistros = new JButton();
		lblMensajePaginaA = new JLabel();
		lblPaginaActual = new JLabel();
		lblMensajePaginaB = new JLabel();
		lblPaginaFinal = new JLabel();
		goodiesFormsSeparator = compFactory.createSeparator("");
		panelContenido = new JPanel();
		panelTablaResultados = new JScrollPane();
		tblResultados = new JTable();
		panelPorParametros = new JPanel();
		lblParametro1 = new JLabel();
		lblParametro2 = new JLabel();
		txtParametro1 = new JTextField();
		txtParametro2 = new JTextField();
		lblParametro3 = new JLabel();
		txtParametro3 = new JTextField();
		btnParametro3 = new JButton();
		lblParametro4 = new JLabel();
		txtParametro4 = new JTextField();
		btnParametro4 = new JButton();
		lblParametro5 = new JLabel();
		txtParametro5 = new JTextField();
		btnParametro5 = new JButton();
		lblParametro6 = new JLabel();
		txtParametro6 = new JTextField();
		btnParametro6 = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("B\u00fasqueda de algo");
		setResizable(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"500dlu:grow",
			"fill:default:grow"));

		//======== panelTodos ========
		{
			panelTodos.setLayout(new FormLayout(
				"default:grow",
				"fill:pref, 12dlu, fill:default:grow"));
			
			//======== panelBarraTitulo ========
			{
				panelBarraTitulo.setLayout(new FormLayout(
					"default, default, default, default, default, 20dlu, default, default, default, default, default:grow, default",
					"fill:default"));
				
				//---- btnBuscar ----
				btnBuscar.setText("B");
				panelBarraTitulo.add(btnBuscar, cc.xy(1, 1));
				
				//---- btnAceptar ----
				btnAceptar.setText("OK");
				panelBarraTitulo.add(btnAceptar, cc.xy(12, 1));
				
				//---- btnIrPrimerosRegistros ----
				btnIrPrimerosRegistros.setText("| <");
				panelBarraTitulo.add(btnIrPrimerosRegistros, cc.xy(2, 1));
				
				//---- btnIrAnteriorRegistro ----
				btnIrAnteriorRegistro.setText("<");
				panelBarraTitulo.add(btnIrAnteriorRegistro, cc.xy(3, 1));
				
				//---- btnIrSiguienteRegistro ----
				btnIrSiguienteRegistro.setText(">");
				panelBarraTitulo.add(btnIrSiguienteRegistro, cc.xy(4, 1));
				
				//---- btnIrUltimosRegistros ----
				btnIrUltimosRegistros.setText("> |");
				panelBarraTitulo.add(btnIrUltimosRegistros, cc.xy(5, 1));
				
				//---- lblMensajePaginaA ----
				lblMensajePaginaA.setText("P\u00e1gina: ");
				panelBarraTitulo.add(lblMensajePaginaA, cc.xy(7, 1));
				
				//---- lblPaginaActual ----
				lblPaginaActual.setText("0");
				lblPaginaActual.setFont(new Font("Tahoma", Font.BOLD, 11));
				panelBarraTitulo.add(lblPaginaActual, cc.xy(8, 1));
				
				//---- lblMensajePaginaB ----
				lblMensajePaginaB.setText(" de ");
				panelBarraTitulo.add(lblMensajePaginaB, cc.xy(9, 1));
				
				//---- lblPaginaFinal ----
				lblPaginaFinal.setText("0");
				lblPaginaFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
				panelBarraTitulo.add(lblPaginaFinal, cc.xy(10, 1));
			}
			panelTodos.add(panelBarraTitulo, cc.xy(1, 1));
			panelTodos.add(goodiesFormsSeparator, cc.xywh(1, 2, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
			
			//======== panelContenido ========
			{
				panelContenido.setLayout(new FormLayout(
					"500dlu:grow",
					"default, fill:default:grow"));
				
				//======== panelTablaResultados ========
				{
					
					//---- tblResultados ----
					tblResultados.setColumnSelectionAllowed(false);
					tblResultados.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"A", "B"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblResultados.setPreferredScrollableViewportSize(new Dimension(450, 160));
					panelTablaResultados.setViewportView(tblResultados);
				}
				panelContenido.add(panelTablaResultados, cc.xywh(1, 1, 1, 2));
			}
			panelTodos.add(panelContenido, cc.xy(1, 3));
		}
		contentPane.add(panelTodos, cc.xy(1, 1));

		//======== panelPorParametros ========
		{
			panelPorParametros.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(70)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(40)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(30), FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(25)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(25), FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.DEFAULT_GROW)
				}));
			
			//---- lblParametro1 ----
			lblParametro1.setText("Parametro1");
			panelPorParametros.add(lblParametro1, cc.xy(3, 3));
			
			//---- lblParametro2 ----
			lblParametro2.setText("Parametro2");
			panelPorParametros.add(lblParametro2, cc.xy(3, 5));
			panelPorParametros.add(txtParametro1, cc.xy(5, 3));
			panelPorParametros.add(txtParametro2, cc.xywh(5, 5, 5, 1));
			
			//---- lblParametro3 ----
			lblParametro3.setText("Parametro3");
			panelPorParametros.add(lblParametro3, cc.xy(3, 7));
			panelPorParametros.add(txtParametro3, cc.xywh(5, 7, 5, 1));
			
			//---- btnParametro3 ----
			btnParametro3.setText("text");
			panelPorParametros.add(btnParametro3, cc.xy(11, 7));
			
			//---- lblParametro4 ----
			lblParametro4.setText("Parametro4");
			panelPorParametros.add(lblParametro4, cc.xy(3, 9));
			panelPorParametros.add(txtParametro4, cc.xywh(5, 9, 5, 1));
			
			//---- btnParametro4 ----
			btnParametro4.setText("text");
			panelPorParametros.add(btnParametro4, cc.xy(11, 9));
			
			//---- lblParametro5 ----
			lblParametro5.setText("Parametro5");
			panelPorParametros.add(lblParametro5, cc.xy(3, 11));
			panelPorParametros.add(txtParametro5, cc.xywh(5, 11, 5, 1));
			
			//---- btnParametro5 ----
			btnParametro5.setText("text");
			panelPorParametros.add(btnParametro5, cc.xy(11, 11));
			
			//---- lblParametro6 ----
			lblParametro6.setText("Parametro6");
			panelPorParametros.add(lblParametro6, cc.xy(3, 13));
			panelPorParametros.add(txtParametro6, cc.xywh(5, 13, 5, 1));
			
			//---- btnParametro6 ----
			btnParametro6.setText("text");
			panelPorParametros.add(btnParametro6, cc.xy(11, 13));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panelTodos;
	private JPanel panelBarraTitulo;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JButton btnIrPrimerosRegistros;
	private JButton btnIrAnteriorRegistro;
	private JButton btnIrSiguienteRegistro;
	private JButton btnIrUltimosRegistros;
	private JLabel lblMensajePaginaA;
	private JLabel lblPaginaActual;
	private JLabel lblMensajePaginaB;
	private JLabel lblPaginaFinal;
	private JComponent goodiesFormsSeparator;
	private JPanel panelContenido;
	private JScrollPane panelTablaResultados;
	private JTable tblResultados;
	private JPanel panelPorParametros;
	private JLabel lblParametro1;
	private JLabel lblParametro2;
	private JTextField txtParametro1;
	private JTextField txtParametro2;
	private JLabel lblParametro3;
	private JTextField txtParametro3;
	private JButton btnParametro3;
	private JLabel lblParametro4;
	private JTextField txtParametro4;
	private JButton btnParametro4;
	private JLabel lblParametro5;
	private JTextField txtParametro5;
	private JButton btnParametro5;
	private JLabel lblParametro6;
	private JTextField txtParametro6;
	private JButton btnParametro6;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanelTodos() {
		return panelTodos;
	}

	public JPanel getPanelBarraTitulo() {
		return panelBarraTitulo;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnIrPrimerosRegistros() {
		return btnIrPrimerosRegistros;
	}

	public JButton getBtnIrAnteriorRegistro() {
		return btnIrAnteriorRegistro;
	}

	public JButton getBtnIrSiguienteRegistro() {
		return btnIrSiguienteRegistro;
	}

	public JButton getBtnIrUltimosRegistros() {
		return btnIrUltimosRegistros;
	}

	public JLabel getLblMensajePaginaA() {
		return lblMensajePaginaA;
	}

	public JLabel getLblPaginaActual() {
		return lblPaginaActual;
	}

	public JLabel getLblMensajePaginaB() {
		return lblMensajePaginaB;
	}

	public JLabel getLblPaginaFinal() {
		return lblPaginaFinal;
	}

	public JComponent getGoodiesFormsSeparator() {
		return goodiesFormsSeparator;
	}

	public JPanel getPanelContenido() {
		return panelContenido;
	}

	public JScrollPane getPanelTablaResultados() {
		return panelTablaResultados;
	}

	public JTable getTblResultados() {
		return tblResultados;
	}

	public JPanel getPanelPorParametros() {
		return panelPorParametros;
	}

	public JLabel getLblParametro1() {
		return lblParametro1;
	}

	public JLabel getLblParametro2() {
		return lblParametro2;
	}

	public JTextField getTxtParametro1() {
		return txtParametro1;
	}

	public JTextField getTxtParametro2() {
		return txtParametro2;
	}

	public JLabel getLblParametro3() {
		return lblParametro3;
	}

	public JTextField getTxtParametro3() {
		return txtParametro3;
	}

	public JButton getBtnParametro3() {
		return btnParametro3;
	}

	public JLabel getLblParametro4() {
		return lblParametro4;
	}

	public JTextField getTxtParametro4() {
		return txtParametro4;
	}

	public JButton getBtnParametro4() {
		return btnParametro4;
	}

	public JLabel getLblParametro5() {
		return lblParametro5;
	}

	public JTextField getTxtParametro5() {
		return txtParametro5;
	}

	public JButton getBtnParametro5() {
		return btnParametro5;
	}

	public JLabel getLblParametro6() {
		return lblParametro6;
	}

	public JTextField getTxtParametro6() {
		return txtParametro6;
	}

	public JButton getBtnParametro6() {
		return btnParametro6;
	}
}
