/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Administrador
 */
public class MyDefaultListRender extends DefaultListCellRenderer implements ListCellRenderer {

    public MyDefaultListRender() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        if (isSelected) {
            label.setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }

        renderLabel(value, label);
        return label;
    }

    public void renderLabel(Object value, JLabel label) {
        label.setText(value.toString());
    }
}   

