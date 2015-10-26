/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2_XUAN_Ziyan;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Ziyan Xuan
 */
public class JavaApplication16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DemoWindow dw = new DemoWindow("Photo Brower");

        dw.setSize(800, 500);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        dw.setLocation((d.width - dw.getSize().width) / 2, (d.height - dw.getSize().height) / 2);
        dw.setMinimumSize(new Dimension(220, 180));

        dw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dw.setVisible(true);
    }

}
