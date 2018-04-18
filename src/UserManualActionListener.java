/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class UserManualActionListener implements ActionListener {
    String osName = System.getProperty("os.name");

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = Desktop.getDesktop();
        try {
            File tempFile = new File("TempFile");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write("TempFile");
            fileWriter.close();

            String filePath = tempFile.getAbsolutePath();
            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

            if (osName.toLowerCase().contains("mac")) {
                filePath = filePath.concat("DNA Pen.app/Contents/Resources/DNA Pen/docs/User Manual.pdf");
            } else {
                filePath = filePath.concat("/docs/User Manual.pdf");
            }

            desktop.open(new File(filePath));
            tempFile.delete();
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
