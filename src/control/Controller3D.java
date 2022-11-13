package control;

import rasterize.LineRasterizer;
import rasterize.Raster;
import view.Panel;

import javax.swing.*;
import java.awt.*;

public class Controller3D {

    private JFrame frame;
    private JPanel panel;
    private Raster raster;
    private LineRasterizer lineRasterizer;

    public Controller3D(int width, int height) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        frame.setVisible(true);
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        panel.requestFocus();

        frame.add(panel);

    }
}
