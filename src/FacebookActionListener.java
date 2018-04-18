import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: shikhar
 * Date: 8/15/13
 * Time: 9:27 PM
 */
public class FacebookActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
        Desktop desktop = Desktop.getDesktop();
        try {
            URI updateLink = new URI("http://www.facebook.com/dnapen");
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
