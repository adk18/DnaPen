/* Author: Shikhar Gupta
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import org.apache.commons.io.FileUtils;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class CanvasScreenshotActionListener implements ActionListener {
    Scanner in=new Scanner(System.in);
    String testfile;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (MainFrame.newBrickCreated) {

            try {
                JFileChooser jFileChooser = new JFileChooser();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle screenRectangle = new Rectangle(screenSize);
                Robot robot = new Robot();
                BufferedImage image = robot.createScreenCapture(screenRectangle);
                image = image.getSubimage(0, 50, 1366,650);
                int returnVal = jFileChooser.showSaveDialog(null);
                File file = jFileChooser.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    testfile=file.getName();
                    System.out.println(testfile);
                    captureScreen(testfile +".png",file,image);
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(MainFrame.mainFrame, "Brick Image Not Available.",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        } else {
            JOptionPane.showMessageDialog(null, "No Brick has been created. Use 'New' to create a Brick first",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    public static void captureScreen(String fileName, File file, BufferedImage img) throws Exception {

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Rectangle screenRectangle = new Rectangle(screenSize);
        //Robot robot = new Robot();
        //BufferedImage image = robot.createScreenCapture(screenRectangle);
        //image = image.getSubimage(0, 50, 1366,650);
        ImageIO.write(img, "png", file);
        System.out.println("Success your files has been saved.");


    }
}
