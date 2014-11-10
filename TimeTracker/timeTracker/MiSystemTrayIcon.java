/*
 * MiSystemTrayIcon.java
 *
 * Created on June 13, 2007, 2:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker;

import static com.spirit.timeTracker.gui.model.PanelGeneralModel.createImageIcon;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MiSystemTrayIcon {
    
    SystemTray tray;
    static TrayIcon trayIcon;
    Image imagenIcono;
    PopupMenu popupMenu;
    JFrame ventanaPrincipal;
    
    public MiSystemTrayIcon() {
    }
    
    public MiSystemTrayIcon(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = (JFrame) ventanaPrincipal;
    }
    
    public void setImageIcon(Image imagen){
        trayIcon.setImage(imagen);
    }
    
    public boolean crearTray(){
        if (SystemTray.isSupported()){
            
            try {
                tray =SystemTray.getSystemTray();
                imagenIcono = createImageIcon("images/timetracker/timeTracker.png").getImage();
                popupMenu =new PopupMenu();
                MenuItem item =new MenuItem("Salir");
                item.addActionListener(accionListenerMenu);
                popupMenu.add(item);
                trayIcon =new TrayIcon(imagenIcono,"Tip Text",popupMenu);
                trayIcon.setImageAutoSize(true);
                //trayIcon.addActionListener(actionListenerTrayIcon);
                trayIcon.addMouseListener(mouseListenerTrayIcon);
                
                tray.add(trayIcon);
                
                return true;
            } catch (AWTException e){
                System.err.println("No se puede poner el TrayIcon en la Bandeja:"+e);
            }
        }else {
            System.err.println("No esta disponible SystemTray");
        }
        return false;
    }
    
    ActionListener actionListenerTrayIcon = new ActionListener() {
        public void actionPerformed(ActionEvent e) {}
    };
    
    ActionListener accionListenerMenu = new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
            String comando = evt.getActionCommand();
            if (comando.equalsIgnoreCase("salir")){
                System.exit(0);
            }
        }
    };
    
    MouseListener mouseListenerTrayIcon = new MouseAdapter(){
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {
            if ( e.getClickCount() == 2){
                if ( ventanaPrincipal!=null )
                    ventanaPrincipal.setVisible(true);
            }
        }
        
    };        
}
