/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;


public class BrickData  {

    static boolean alreadySavedDNA=false;

    BrickData(){
        if (MainFrame.newBrickCreated) {
            if (false) {
                JOptionPane.showMessageDialog(null, "You've already saved the File !",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        }
        //} else {
        //    JOptionPane.showMessageDialog(null, "No Brick has been created. Use 'New' to create a Brick first",
        //            "Error!", JOptionPane.INFORMATION_MESSAGE);
        //}

    }

}
