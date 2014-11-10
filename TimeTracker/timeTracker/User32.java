
package timeTracker;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary {
    
    User32 INSTANCE = (User32)
    Native.loadLibrary("User32", User32.class);
    
    public Pointer GetForegroundWindow();
    
    public int GetWindowTextA(Pointer hwnd,byte[] buffer,int maxCount);
    public int GetWindowThreadProcessId (Pointer ventana,int[] idProceso);
}