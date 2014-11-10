package com.spirit.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** A regular JButton created with an ImageIcon and with borders
 *  and content areas turned off.
 *  1998 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 */

public class JIconButton extends JButton {
  public JIconButton(String file) {
    super(new ImageIcon(file));
    setContentAreaFilled(false);
    setBorderPainted(false);
    setFocusPainted(false);
  }
}
