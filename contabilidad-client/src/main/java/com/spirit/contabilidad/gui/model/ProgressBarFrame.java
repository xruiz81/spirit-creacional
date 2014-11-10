package com.spirit.contabilidad.gui.model;


/**
 * Swing application to implement a simple calculator. 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * class Calculator: implements a simple calculator in Swing.
 * 
 */
public class ProgressBarFrame implements ActionListener {
	//private JFrame frame;

	private JPanel mainPanel;

	private JPanel buttonsPanel;

	private JLabel display;

	Set digitKeySet = new HashSet();

	Set operatorKeySet = new HashSet();

	JButton equalButton;

	// variables to keep state
	private StringBuffer registerA = new StringBuffer();

	private StringBuffer registerB = new StringBuffer();

	private char operator;

	private boolean firstNumberAlreadyInput = false;

	/**
	 * Constructor: builds up the frame, panels, buttons and text-field.
	 */
	public ProgressBarFrame(String ok,String ok2) {
		// Create and set up the window.
	//	frame = new JFrame("Calculator");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the panel.
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.blue));

		
		buttonsPanel = new JPanel(new GridLayout(4, 4));

		/*
		 * create the buttons (by 'addButtonToPanel') and add each
		 * one to the right botton set. 
		 * we create the buttons in the right order so they appear 
		 * in the right way on the GUI.
		 */
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "7"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "8"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "9"));
		operatorKeySet.add(addButtonToPanel(buttonsPanel, this, "/"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "4"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "5"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "6"));
		operatorKeySet.add(addButtonToPanel(buttonsPanel, this, "*"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "1"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "2"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "3"));
		operatorKeySet.add(addButtonToPanel(buttonsPanel, this, "-"));
		digitKeySet.add(addButtonToPanel(buttonsPanel, this, "0"));
		JButton dot = addButtonToPanel(buttonsPanel, this, ".");
		equalButton = addButtonToPanel(buttonsPanel, this, "=");
		operatorKeySet.add(addButtonToPanel(buttonsPanel, this, "+"));

		// displaye "." button
		dot.setEnabled(false);
		setButtonsEnabled(operatorKeySet, false);
		equalButton.setEnabled(false);

		// Add the display field
		display = new JLabel("0");
		display.setBackground(Color.WHITE);
		display.setFont(new java.awt.Font("Tahoma", 1, 16));		
		display
				.setBorder(BorderFactory
						.createEtchedBorder(EtchedBorder.RAISED));

		// Add to main panel
		mainPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		mainPanel.add(display, "North");
		mainPanel.add(buttonsPanel, "Center");
		
		setmainPanel(mainPanel);

		// Add the panel to the window.
		//frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Display the window.
	//	frame.setSize(new Dimension(220, 240));
		//frame.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * This function will be called every time a button is pressed (this is a 
	 * GUI event we registered on). 
	 * We can expect either a digit, an operator or '='. We record
	 * the state using the variables: 'registerA',
	 * 'registerB', 'operator' and 'firstNumberAlreadyInput'
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source != null) {
			String input = ((JButton) source).getText();

			if (digitKeySet.contains(source)) {
				if (firstNumberAlreadyInput) {
					registerB.append(input);
					display.setText(registerB.toString());
					equalButton.setEnabled(true);
				} else {
					registerA.append(input);
					display.setText(registerA.toString());
					setButtonsEnabled(digitKeySet, true);
					setButtonsEnabled(operatorKeySet, true);
					equalButton.setEnabled(false);
				}
			} else if (operatorKeySet.contains(source)) {
				operator = input.charAt(0);
				firstNumberAlreadyInput = true;
				setButtonsEnabled(digitKeySet, true);
				setButtonsEnabled(operatorKeySet, false);
				equalButton.setEnabled(false);
			} else {
				display.setText(doCalculation(registerA.toString(), registerB
						.toString(), operator));
				registerA.setLength(0);
				registerB.setLength(0);
				firstNumberAlreadyInput = false;
				setButtonsEnabled(digitKeySet, true);
				setButtonsEnabled(operatorKeySet, false);
				equalButton.setEnabled(false);
			}
		}
	}

	/*--------------------------------------------------------------------*\
	 	Static Methods (utility functions)		
	\*--------------------------------------------------------------------*/

	/**
	 * uses 'registerA', 'registerB' and 'operator' to convert into integers and
	 * compute the result.
	 * 
	 * @param rand1
	 * @param rand2
	 * @param op
	 * @return
	 */
	private static String doCalculation(String rand1, String rand2, char op) {
		final char beep = '\u0007';

		try {
			int numA = new Integer(rand1.toString()).intValue();
			int numB = new Integer(rand2.toString()).intValue();

			switch (op) {
			case '+':
				return String.valueOf(numA + numB);
			case '-':
				return String.valueOf(numA - numB);
			case '*':
				return String.valueOf(numA * numB);
			default:
				return String.valueOf(numA / numB);
			}
		} catch (ArithmeticException error) {
			System.out.print(beep);
			return "   E R R O R";
		}
	}

	/**
	 * Add the button to panel. Have 'text' as the button's text and name. Add
	 * listener to button's events.
	 * 
	 * @param panel
	 * @param listener
	 * @param text
	 * @return
	 */
	private static JButton addButtonToPanel(JPanel panel,
			ActionListener listener, String text) {
		JButton button = new JButton(text);
		button.setName(text);
		button.setFont(new java.awt.Font("Tahoma", 1, 16));
		button.addActionListener(listener);
		panel.add(button);
		return button;
	}

	/**
	 * call setEnabled on all buttons in the Set.
	 * 
	 * @param buttons
	 * @param enabled
	 */
	private static void setButtonsEnabled(Set buttons, boolean enabled) {
		for (Iterator i = buttons.iterator(); i.hasNext();) {
			JButton button = (JButton) i.next();
			button.setEnabled(enabled);
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		ProgressBarFrame calculator = new ProgressBarFrame("ok","ok");
	}

	 
	
	
	
	//////////////////
	public JPanel getmainPanel()
	{
		
		return mainPanel;
		
	}
	
	public void setmainPanel(JPanel main)
	{
		
		mainPanel=main;
		
	}
	/**
	 * main function to run Calculator
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
