/*
 * AppView.java
 */

package vms;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class AppView extends SingleFrameApplication {

   private static AppViewCore appView;

    public static AppViewCore getAppViewCore()
    {
        return appView;
    }
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        appView = new AppViewCore(this);
        show(appView);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AppView
     */
    public static AppView getApplication() {
        return Application.getInstance(AppView.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(AppView.class, args);
    }
}
