/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ProductFeedbackActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI updateLink = new URI("https://docs.google.com/forms/d/1YGu_I9z7Z56oAP1enGByBahqs-ItHbLqnBwCoJouOro/viewform");
            desktop.browse(updateLink);
        } catch (URISyntaxException e) {
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
