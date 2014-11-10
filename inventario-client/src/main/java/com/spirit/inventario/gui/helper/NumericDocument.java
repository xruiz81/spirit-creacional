/*
 * class SoloNumerosDecimales
 * AUTOR TMS - The Microserv S.A
 * Creado el 29/11/2007, 14:54:19
 * The MicroServ S.A. se reserva el derecho de confidencialidad y patente 
 * segun la licencia estipulada para este Software.
 * El cliente no puede revelar Informacion Confidencial y debe 
 * hacer uso del mismo segun los terminos acordados en el contrato.
 */
package com.spirit.inventario.gui.helper;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Esta Clase Limita el Ingreso de Caracteres
 * en los campos de Texto a <B>"Solo Decimales"</B>.
 *
 * Se Aplica con el M�todo .setDocument en Todo Campo de Texto.
 *
 * Ej:
 *
 * txtValorFOB = new javax.swing.JFormattedTextField();
 * txtValorFOB.setDocument(new SoloNumerosDecimales(7,2));
 *
 *    M�ximo N�mero: 99999.99 (5 Enteros, 2 Decimales)
 * @author 
 */
public class NumericDocument extends PlainDocument {

    private int numDigitosEnteros;
    private int numDigitosDecimales;
    private DecimalFormat form2 = null;
    private String separador = "";

    /**
     * Define el N�mero de Enteros y de Decimales que se permite en documento.
     * @param pNumDigitosEnteros Numero de D�gitos enteros.
     * @param pNumDigitosDecimales N�mero de Digitos Decimales
     */
    public NumericDocument(int pNumDigitosEnteros, int pNumDigitosDecimales) {
        numDigitosEnteros = pNumDigitosEnteros - pNumDigitosDecimales;
        numDigitosDecimales = pNumDigitosDecimales;
        form2 = (DecimalFormat) NumberFormat.getNumberInstance(Locale.getDefault());
        separador = ".";
    }

    /**
     * Este Metodo esta definido en PlainDocument
     *
     * @param pCadena
     * @param pAtributos
     * @param offset
     * @throws javax.swing.text.BadLocationException
     */
    @Override
    public void insertString(int offset,
            String pCadena, AttributeSet pAtributos)
            throws BadLocationException {
        if (pCadena == null) {
            Toolkit.getDefaultToolkit().beep();
            return;
        } else {
            String newValue;
            int length = getLength();
            if (length == 0) {
                newValue = pCadena;
            } else {
                String currentContent = getText(0, length);
                StringBuffer currentBuffer =
                        new StringBuffer(currentContent);
                currentBuffer.insert(offset, pCadena);
                newValue = currentBuffer.toString();
            }

            try {
                int tamanio = pCadena.length();
                for (int i = 0; i < tamanio; i++) {
                    if (!Character.isDigit(pCadena.charAt(i))) {
                        if (!separador.equalsIgnoreCase(String.valueOf(pCadena.charAt(i)))) {
                            Toolkit.getDefaultToolkit().beep();
                            return;
                        }
                    }
                }
                String parteEntera = "";
                String parteDecimal = "";
                String cadena = getText(0, length);
                if (!cadena.contains(separador)) {
                    if (this.numDigitosEnteros <= cadena.length()) {
                        if (!separador.equalsIgnoreCase(pCadena)) {
                            Toolkit.getDefaultToolkit().beep();
                            return;
                        } else {
                            super.insertString(offset, pCadena, pAtributos);
                            return;
                        }
                    } else {
                        super.insertString(offset, pCadena, pAtributos);
                        return;
                    }
                }
                /********************************************************/
                if (separador.equalsIgnoreCase(pCadena)) {
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                if (cadena.startsWith(separador) && cadena.endsWith(separador)) {

                    parteEntera = "";
                    parteDecimal = "";
                    super.insertString(offset, pCadena, pAtributos);
                    return;
                } else if (cadena.startsWith(separador)) {
                    parteEntera = "";
                    parteDecimal = cadena.substring(1);
                    if (offset > 1) {
                        if (this.numDigitosDecimales >= parteDecimal.length() + 1) {
                            super.insertString(offset, pCadena, pAtributos);
                            return;
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                            return;
                        }
                    } else {
                        super.insertString(offset, pCadena, pAtributos);
                        return;
                    }

                } else if (cadena.endsWith(separador)) {
                    parteEntera = cadena.substring(0, cadena.length() - 1);
                    parteDecimal = "";
                    if (offset == this.numDigitosEnteros + 1) {
                        super.insertString(offset, pCadena, pAtributos);
                        return;
                    } else if (offset >= this.numDigitosEnteros+1) {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    } else {
                        super.insertString(offset, pCadena, pAtributos);
                        return;
                    }
                } else {
                    int indexSeparador = cadena.indexOf(separador);
                    String[] partes = cadena.split("\\" + separador);
                    parteEntera = partes[0];
                    parteDecimal = partes[1];
                    if (offset <= this.numDigitosEnteros && offset <= indexSeparador) {
                        if (parteEntera.length() >= this.numDigitosEnteros) {
                            Toolkit.getDefaultToolkit().beep();
                            return;
                        } else {
                            super.insertString(offset, pCadena, pAtributos);
                            return;
                        }
                    } else if (offset >= indexSeparador && (offset - this.numDigitosEnteros) <= this.numDigitosDecimales) {
                        if (parteDecimal.length() >= this.numDigitosDecimales) {
                            Toolkit.getDefaultToolkit().beep();
                            return;
                        } else {
                            super.insertString(offset, pCadena, pAtributos);
                            return;
                        }
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    }
                }
            /********************************************************/
            } catch (Exception ex) {
                ex.printStackTrace();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public void remove(int offs, int len) throws BadLocationException {
        int length = getLength();
        String currentContent = getText(0, length);
        int indexSeparador = currentContent.indexOf(separador);
        if (indexSeparador == offs) {
            super.remove(offs, (length - indexSeparador));
        } else {
            super.remove(offs, len);
        }
    }
}
