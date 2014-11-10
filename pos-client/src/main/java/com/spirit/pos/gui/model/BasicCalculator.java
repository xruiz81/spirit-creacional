package com.spirit.pos.gui.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class BasicCalculator extends JFrame implements ActionListener, MouseListener
{

  JTextField display = new JTextField();
  JButton[] number = new JButton[11];
  JButton addBtn = new JButton("+");
  double currentTotal = 0.0;
  DecimalFormat df = new DecimalFormat("0.00");
  boolean totalDisplayed = true;

  public BasicCalculator()
  {

    setTitle("Calculator");
  //  setLocation(300,100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    display.setBackground(Color.WHITE);
    display.setFont(new Font("monospaced",Font.BOLD,20));
    display.setEditable(false);
    display.setHorizontalAlignment(JTextField.RIGHT);
    getContentPane().add(display,BorderLayout.NORTH);
    JPanel numpad = new JPanel(new GridLayout(4,3));

    for(int x = 7; x > 0; x -= 3)
    {
      for(int y = x; y < x+3; y++)
      {
        number[y] = new JButton(""+y);
        number[y].addActionListener(this);
        numpad.add(number[y]);
      }
    }

    number[0] = new JButton("0");
    number[0].addActionListener(this);
    numpad.add(number[0]);
    numpad.add(new JLabel(" "));
    number[10] = new JButton(".");
    number[10].addActionListener(this);
    numpad.add(number[10]);
    getContentPane().add(numpad,BorderLayout.CENTER);
    addBtn.addMouseListener(this);
    getContentPane().add(addBtn,BorderLayout.EAST);
    display.setText(df.format(currentTotal));
    pack();

  }

  public Component getcomp(){
	  return (getContentPane());
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    if(totalDisplayed) display.setText("");
    totalDisplayed = false;
    display.setText(display.getText()+ae.getActionCommand());
  }

  public void mouseClicked(MouseEvent me)
  {
    currentTotal += Double.parseDouble(display.getText());
    display.setText(df.format(currentTotal));
    totalDisplayed = true;
  }
  
  
 
  public void mousePressed(MouseEvent me){}
  public void mouseReleased(MouseEvent me){}
  public void mouseEntered(MouseEvent me){}
  public void mouseExited(MouseEvent me){}
  public static void main(String[] args){new BasicCalculator().setVisible(true);}
}