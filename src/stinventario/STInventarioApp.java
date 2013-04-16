/*
 * STInventarioApp.java
 */

package stinventario;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class STInventarioApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override public void startup() {
        show(new STInventarioView(this));
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
     * @return the instance of STInventarioApp
     */
    public static STInventarioApp getApplication() {
        return Application.getInstance(STInventarioApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(STInventarioApp.class, args);
    }
}
