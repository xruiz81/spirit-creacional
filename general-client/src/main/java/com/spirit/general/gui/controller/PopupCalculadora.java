package com.spirit.general.gui.controller;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.popup.JidePopup;
import com.jidesoft.swing.Calculator;



/**
 * @author Antonio Seiler
 */
public class PopupCalculadora extends JidePopup {
	public PopupCalculadora() {
		initComponents();
		
		txtResultado.setHorizontalAlignment(JTextField.TRAILING);
		calculator.registerKeyboardActions(txtResultado, JComponent.WHEN_FOCUSED);
        calculator.addPropertyChangeListener(Calculator.PROPERTY_DISPLAY_TEXT, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                txtResultado.setText("" + evt.getNewValue());
            }
        });
        calculator.clear();
        setDefaultFocusComponent(txtResultado);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		contentPane = new JPanel();
		panelBarraTitulo = new JPanel();
		lblTitulo = new JLabel();
		panelForm = new JPanel();
		calculator = new Calculator();
		txtResultado = new JTextField();
		goodiesFormsSeparator = compFactory.createSeparator("");
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setResizable(false);
		setMovable(true);
		setMaximumSize(new Dimension(225, 210));
		setMinimumSize(new Dimension(225, 210));
		setPreferredSize(new Dimension(225, 210));
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== contentPane ========
		{
			contentPane.setMaximumSize(new Dimension(225, 210));
			contentPane.setMinimumSize(new Dimension(225, 210));
			contentPane.setPreferredSize(new Dimension(225, 210));
			contentPane.setLayout(new FormLayout(
				"default:grow",
				"fill:pref, default, default:grow"));
			
			//======== panelBarraTitulo ========
			{
				panelBarraTitulo.setMaximumSize(new Dimension(225, 15));
				panelBarraTitulo.setMinimumSize(new Dimension(225, 15));
				panelBarraTitulo.setPreferredSize(new Dimension(225, 15));
				panelBarraTitulo.setLayout(new FormLayout(
					"default, default:grow, default",
					"fill:default"));
				
				//---- lblTitulo ----
				lblTitulo.setText("Calculadora");
				lblTitulo.setBackground(UIManager.getColor("Button.light"));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setOpaque(true);
				panelBarraTitulo.add(lblTitulo, cc.xy(2, 1));
			}
			contentPane.add(panelBarraTitulo, cc.xy(1, 1));
			
			//======== panelForm ========
			{
				panelForm.setBorder(Borders.DIALOG_BORDER);
				panelForm.setMaximumSize(new Dimension(200, 210));
				panelForm.setMinimumSize(new Dimension(200, 210));
				panelForm.setPreferredSize(new Dimension(200, 210));
				panelForm.setLayout(new FormLayout(
					"default:grow",
					"default, 10dlu, fill:pref:grow"));
				
				//---- calculator ----
				calculator.setButtonWidth(48);
				calculator.setButtonHeight(24);
				calculator.setButtonGap(3);
				panelForm.add(calculator, cc.xy(1, 3));
				panelForm.add(txtResultado, cc.xy(1, 1));
			}
			contentPane.add(panelForm, cc.xy(1, 3));
			contentPane.add(goodiesFormsSeparator, cc.xywh(1, 2, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		}
		add(contentPane, cc.xy(1, 1));
		
		setDefaultFocusComponent(contentPane);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel contentPane;
	private JPanel panelBarraTitulo;
	private JLabel lblTitulo;
	private JPanel panelForm;
	private Calculator calculator;
	private JTextField txtResultado;
	private JComponent goodiesFormsSeparator;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
