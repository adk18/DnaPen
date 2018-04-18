import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UndoActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.currentWindow == 0) {

        } else if (MainFrame.currentWindow == 1) {

        } else if (MainFrame.currentWindow == 2) {
               FreeGridActionListener.canvas.pressed=4;

              }

    }
}
