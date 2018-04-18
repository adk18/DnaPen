import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 5/29/13
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewProjectListener implements ActionListener {
    int Dest=0;
    MainFrame newcontent=new MainFrame();
    JFrame brickDimInputFrame;
    JTextField inputname;
    JTextField pathname;
    public void showInputDialogue() {
        MainFrame.framecounter=1;
        brickDimInputFrame = new JFrame("Select a name for your project");
        brickDimInputFrame.setSize(400, 150);

        JLabel jLabelDimension = new JLabel("Enter the name of your project");
        jLabelDimension.setHorizontalAlignment(JLabel.CENTER);

        inputname = new JTextField();
        inputname.setText("");

        brickDimInputFrame.setLayout(new GridLayout(3, 2));
        brickDimInputFrame.add(jLabelDimension);
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();

        JButton Browse = new JButton("Choose Location");
        Browse.addActionListener(this);

        JButton submitButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        submitButton.setForeground(Color.blue);
        cancelButton.setForeground(Color.red);
        jPanel1.add(Browse);
        jPanel1.add(submitButton);
        jPanel1.add(cancelButton);
        jPanel1.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        jPanel.setLayout(new GridLayout(1, 1));
        jPanel.add(inputname);
        brickDimInputFrame.add(jPanel);
        brickDimInputFrame.add(jPanel1);



        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

        brickDimInputFrame.setLocation(screenDimensions.width/2 - brickDimInputFrame.getSize().width/2,
                screenDimensions.height/2 - brickDimInputFrame.getSize().height/2);

        brickDimInputFrame.setVisible(true);

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                //if (MainFrame.currentWindow == 1 && !MainFrame.newProjectCreated) {
                //    MainFrame.currentWindow = 0 ;
                //}

            }
        };

        brickDimInputFrame.addWindowListener(exitListener);

        submitButton.addActionListener(new SubmitActionListener());
        cancelButton.addActionListener(new CancelActionListener());
        Browse.addActionListener(new BrowseActionListener());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        if (MainFrame.currentWindow == 0) {
            if(MainFrame.framecounter==0)
            showInputDialogue();
        }


            else if((MainFrame.currentWindow == 2)&&(MainFrame.framecounter==0)) {
            if(FreeGridActionListener.isToBeSaved && !FreeGridActionListener.savedFunctionCalled) {
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit before saving your data?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                    if (true) {
                        if(MainFrame.currentWindow == 2) {

                        }
                    }
                }

                else if (userChoice == 2) {
                    return;
                }
            }
            FreeGridActionListener.canvas.setVisible(false);
            MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
            if(MainFrame.framecounter==0){
                showInputDialogue();
            }

        }
        else if( (MainFrame.currentWindow == 3)&&(MainFrame.framecounter==0)) {
            if(DigitalGridActionListener.isToBeSaved && !DigitalGridActionListener.savedFunctionCalled) {
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit before saving your data?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                }

                else if (userChoice == 2) {
                    return;
                }
            }
            DigitalGridActionListener.canvas.setVisible(false);
            MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
            if(MainFrame.framecounter==0){
                showInputDialogue();
            }
        }
    }

    class BrowseActionListener extends JPanel
        implements ActionListener {

    JFileChooser chooser;
    String choosertitle;


    public void actionPerformed(ActionEvent e) {
        int result;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if((inputname.getText().equals("")))    {
            JOptionPane.showMessageDialog(null, "Please specify a name for your project." +
                    "",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getSelectedFile() : "
                        +  chooser.getSelectedFile());
                MainFrame.ProjPath = chooser.getSelectedFile().toString()+"/"+inputname.getText();
                MainFrame.ProjName =inputname.getText();
                Dest=1;

            }
        }
    }

}

class SubmitActionListener extends JPanel
        implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if(Dest==1){
        File dir = new File(MainFrame.ProjPath);
        dir.mkdirs();
           MainFrame.newProjectCreated=true;
        JOptionPane.showMessageDialog(null, "Your project has been created." +
                "",
                "", JOptionPane.INFORMATION_MESSAGE);

        MainFrame.framecounter=0;
        MainFrame.newBrickCreated=false;
        MainFrame.saveBrickFunctionCalled=false;

        newcontent.AddContent();
        GenerateDNAFile gen=new GenerateDNAFile();
        gen.SaveToFile();
        System.out.print("Done!");
        brickDimInputFrame.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a location first " +
                    " ",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

    class CancelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            MainFrame.framecounter=0;
            brickDimInputFrame.setVisible(false);
            if (!MainFrame.newProjectCreated) {
                MainFrame.currentWindow = 0;
            }
            return;
        }
    }


}