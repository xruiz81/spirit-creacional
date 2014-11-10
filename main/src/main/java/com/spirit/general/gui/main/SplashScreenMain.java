package com.spirit.general.gui.main;

import javax.swing.ImageIcon;

import com.spirit.client.SplashScreen;
import com.spirit.client.model.SpiritResourceManager;

public class SplashScreenMain {

  SplashScreen screen;

  public SplashScreenMain() {
    // initialize the splash screen
    splashScreenInit();
    // do something here to simulate the program doing something that
    // is time consuming
    /*for (int i = 0; i <= 100; i++)
    {
      for (long j=0; j<50000; ++j)
      {
        String poop = " " + (j + i);
      }
      // run either of these two -- not both
      screen.setProgress("Yo " + i, i);  // progress bar with a message
      //screen.setProgress(i);           // progress bar with no message
    }
    splashScreenDestruct();
    System.exit(0);*/
  }

  public void splashScreenDestruct() {
    screen.setScreenVisible(false);
    screen = null;
  }

  private void splashScreenInit() {
    ImageIcon myImage = SpiritResourceManager.getImageIcon("images/spirit_splash.jpg");
    screen = new SplashScreen(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgressMax(100);
    screen.setScreenVisible(true);
  }
  
  public SplashScreen getScreen() {
	  return this.screen;
  }
}

