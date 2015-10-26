/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2_XUAN_Ziyan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Ziyan Xuan
 */
class DemoWindow extends JFrame implements ActionListener {

    boolean flipable;
    ImagePanel imgPanel = new ImagePanel();

    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(imgPanel, v, h);

    JLabel statuLab = new JLabel("statu: Photo viewer");
    JMenuItem myItemImport = new JMenuItem("Import");

    private JMenu fileMenu() {
        JMenu menu = new JMenu("File");

        menu.add(myItemImport);
        JMenuItem myItemDel = new JMenuItem("Delete");
        myItemDel.setEnabled(false);
        menu.add(myItemDel);
        menu.addSeparator();
        JMenuItem myItemQuit = new JMenuItem("Quit");
        menu.add(myItemQuit);

        myItemQuit.addActionListener(new ExitListener());
        return menu;
    }

    private JMenu viewMenu() {
        JMenu myMenu = new JMenu("View");
        ButtonGroup myGroup = new ButtonGroup();
        JRadioButtonMenuItem myItem1, myItem2, myItem3;
        myItem1 = new JRadioButtonMenuItem("Photo viewer");
        myItem1.setSelected(true);
        myItem2 = new JRadioButtonMenuItem("Browser");
        myItem3 = new JRadioButtonMenuItem("Split mode");

        myGroup.add(myItem1);
        myMenu.add(myItem1);

        myGroup.add(myItem2);
        myMenu.add(myItem2);

        myGroup.add(myItem3);
        myMenu.add(myItem3);

        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButtonMenuItem radio = (JRadioButtonMenuItem) e.getSource();
                if (radio == myItem1) {
                    statuLab.setText("statu: Photo viewer");
                } else if (radio == myItem2) {
                    statuLab.setText("statu: Browser viewer");
                } else {
                    statuLab.setText("statu: Split mode viewer");
                }
            }
        };

        myItem1.addActionListener(al);
        myItem2.addActionListener(al);
        myItem3.addActionListener(al);
        return myMenu;
    }

    String imageFile = null;

    public DemoWindow(String title) {
        super(title);

        JToolBar toolBar = new JToolBar(FlowLayout.LEFT);
        JToggleButton btn1 = new JToggleButton("Family");
        JToggleButton btn2 = new JToggleButton("Vacation");
        JToggleButton btn3 = new JToggleButton("School");

        toolBar.add(btn1);
        toolBar.add(btn2);
        toolBar.add(btn3);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = fileMenu();
        menuBar.add(menu);
        menu = viewMenu();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(jsp, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);
        add(statuLab, BorderLayout.SOUTH);

        myItemImport.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == myItemImport) {
            // file choose dialoge
            JFileChooser jfc = new JFileChooser(System.getProperty("user.dir")
                    + "\\images");

            // image filter
            jfc.setFileFilter(new PictureFilter());

            // if selected, open image
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                imageFile = jfc.getSelectedFile().getPath();
                Image im = new ImageIcon(imageFile).getImage();
                //DrawingListener dlistener = new DrawingListener(imgPanel);

                imgPanel.setImage(im);
                imgPanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {

                        if (e.getClickCount() == 2) {
                            if (flipable == false) {
                                flipable = true;
                                JButton clearButton = new JButton("Clear");
                                clearButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        imgPanel.clear();
                                    }
                                });
                                imgPanel.add(clearButton, BorderLayout.NORTH);

                                System.out.print("double click to back");
                            } else {
                                flipable = false;
                                System.out.print("double click to face");
                            }

                        }
                        imgPanel.setImageFlip(im, flipable);
                        super.mouseClicked(e);
                    }

                });

                // refresh jscrollpanel
                jsp.setViewportView(imgPanel);
                jsp.getViewport().setBackground(Color.white);
            }
        }
    }

}
