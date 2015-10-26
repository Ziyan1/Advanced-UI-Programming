/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2_XUAN_Ziyan;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Ziyan Xuan
 */
class PictureFilter extends FileFilter {

    // file's type
    public boolean accept(File f) {
        
        if (f.isDirectory()) {
            return true;
        }

        // return true if .jpg .gif and .png
        if (getExtension(f).equals("jpg") || getExtension(f).equals("gif")
                || getExtension(f).equals("png")) {
            return true;
        }
        return false;
    }

    
    public String getDescription() {
        return "Image File（*.jpg，*.gif，*.png）";
    }

    
    public static String getExtension(File f) {
        String ext = "";
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

}
