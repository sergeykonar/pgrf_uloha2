package app;

import control.Controller2D;
import control.Controller3D;
import view.Window;

import javax.swing.*;

public class AppStart {

    private static final int FPS = 1000 / 20;
    public static final int WIDTH = 800, HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
//            new Controller3D(WIDTH, HEIGHT);
            new Controller2D(window.getPanel());
            window.setVisible(true);

        });
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
    }

}
