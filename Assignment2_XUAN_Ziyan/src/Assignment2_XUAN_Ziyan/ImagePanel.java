/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2_XUAN_Ziyan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ziyan Xuan
 */
class ImagePanel extends JPanel {

    Image image = null;
    boolean flipable;
    Graphics2D graphics2D;
    int x1, y1, x2, y2;
    boolean first = true;

    public ImagePanel() {
        x1 = x2 = y1 = y2 = 0;
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        //if image is null
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        //not statu flip
        if (image != null && flipable == false) {
            g.drawImage(image, 0, 0, this);
        }

        //statu flip, photo's back
        if (image != null && flipable == true) {
            graphics2D = (Graphics2D) g;
            graphics2D.setColor(Color.yellow);
            graphics2D.fillRect(0, 0, image.getWidth(this), image.getHeight(this));
//            JButton clearButton = new JButton("A");
//            clearButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    
//                }
//
//            });
//            add(clearButton);
            MouseAdapter adapter = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        x1 = x2 = e.getX();
                        y1 = y2 = e.getY();
                    }
                }

                public void mouseDragged(MouseEvent e) {
                    Graphics g = getGraphics();
                    graphics2D = (Graphics2D) g;
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    if (first) {
                        x2 = e.getX();
                        y2 = e.getY();
                        draw(graphics2D);
                        first = false;
                    } else {
                        x1 = x2;
                        y1 = y2;
                        x2 = e.getX();
                        y2 = e.getY();
                        draw(graphics2D);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        x1 = x2 = y1 = y2 = 0;
                    }
                }
            };
            addMouseListener(adapter);
            addMouseMotionListener(adapter);

        }
    }

    public void draw(Graphics g) {
        graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawLine(x1, y1, x2, y2);
    }

    public void clear() {
        repaint();
    }

    public void setImage(Image image) {
        this.image = image;
        if (image != null) {
            // image panel size
            setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));

        }
        repaint();
    }

    public void setImageFlip(Image image, boolean flipable) {
        this.image = image;
        this.flipable = flipable;
        if (image != null) {
            // image panel size
            setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        }
        repaint();
    }

}
