/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;



public class MainFrame extends Frame{


	public static boolean image_imported = false;
    public static JFrame mainFrame;
    static JPanel panelLeft;
    static DNABrick dnaBrick;
    static boolean newBrickCreated = false;
    static boolean newProjectCreated = false;
    static boolean saveBrickFunctionCalled = false;
    static double brickHeight = 3; // Default Height = 3nm
    static double brickWidth = 7; // Default Width = 7nm
    static boolean licenseAccepted = false;
    String osName = System.getProperty("os.name");
    File configFile;
    static String ProjPath=new String();
    static String ProjName=new String();
    static int framecounter=0;
    boolean allowexit=false;
    static int currentWindow = 0;
    // 1 = File --> New.
    // 2 = Free Hand Grid.
    // 3 = Digital Grid
    static JButton pen = new JButton("Pen");
    static JButton eraser = new JButton("Eraser");
    static JMenuItem ImportImageItem=new JMenuItem("Import Grid Image", new ImageIcon("images/download_pdf.png"));
    static JMenuItem ImportFreeImageItem=new JMenuItem("Import Free Image", new ImageIcon("images/download_pdf.png"));
    
    public static boolean getImportImageTrueness(){
    	return image_imported;
    }

    public void drawFrame() throws FileNotFoundException {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception es){

        }
        mainFrame = new JFrame("DNA Pen - Welcome !");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.print(screenSize);
        mainFrame.setSize(screenSize);
        mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        ImageIcon img = new ImageIcon("images/templogo.png");
        mainFrame.setIconImage(img.getImage());

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                doExit();
            }
        };
        WindowListener iconifiedListener= new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("Iconified State");
                if (currentWindow == 2) {
                    System.out.println("Free Grid Canvas");
                    System.out.println("Success");
                }
                if (currentWindow == 3) {
                    System.out.println("Digitized Grid Canvas");
                    System.out.println("Success");
                }
            }
        };
        WindowListener deiconifiedListener = new WindowAdapter() {
            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("Deiconified State");
                if (currentWindow==2){
                    System.out.println("Free Grid Canvas");
                    System.out.println("Success");

                }
                if (currentWindow==3){
                    System.out.println("Digitized Grid Canvas");
                }
            }
        };

        mainFrame.addWindowListener(exitListener);
        mainFrame.addWindowListener(iconifiedListener);
        mainFrame.addWindowListener(deiconifiedListener);
        mainFrame.setLayout(new GridLayout(1,1));
        panelLeft = new JPanel();
        panelLeft.setSize(1366,700);
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        // To make file menu drop down show over canvas
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
        mainFrame.setContentPane(panelLeft);
        AddContent();

        //Le initial tips box starts
        final JDialog d = new JDialog((Frame)null,"Welcome!");
        JLabel tips=new JLabel("DNA Pen-Tips",SwingConstants.LEADING);
        ImageIcon imgd = new ImageIcon("images/templogo.png");
        d.setIconImage(imgd.getImage());
        JPanel topPanel = new JPanel(new GridLayout(1,3));
        d.setLocation(500, 200); ;
        JButton nproj = new JButton("Create new Project");
        JButton about = new JButton("About");
        JButton nextTip=new JButton("Next Tip");
        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        d.add(basic);
        topPanel.add(nproj);
        topPanel.add(about);
        topPanel.add(nextTip);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        final JTextPane pane = new JTextPane();
        pane.setContentType("text/html");
        String text =getstr();
        pane.setText(text);
        pane.setEditable(false);
        textPanel.add(new JScrollPane(pane));
        basic.add(tips);
        basic.add(textPanel);
        basic.add(topPanel);




        d.setSize(new Dimension(450, 150));
        d.setResizable(false);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setVisible(true);

        //Adding action listener to the tips dialog box
        nproj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
                NewProjectListener newProj = new NewProjectListener();
                newProj.showInputDialogue();

            }
        });


        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
                AboutActionListener about= new AboutActionListener();
                about.actionPerformed(e);
            }
        });


        nextTip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newtip = null;
                try {
                    newtip = getstr();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                pane.setText(newtip);
            }
        });
    }


    public String getstr() throws FileNotFoundException {

        String str = choose(new File("inputfile.txt"));
        System.out.print(str);
        return(str);
    }

    public static String choose(File f) throws FileNotFoundException
    {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for(Scanner sc = new Scanner(f); sc.hasNext(); )
        {
            ++n;
            String line = sc.nextLine();
            if(rand.nextInt(n) == 0)
                result = line;
        }

        return result;
    }

    public static void FalseEnableContent(){
        pen.setEnabled(false);
        eraser.setEnabled(false);
    }

    public static void TrueEnableContent(){
        pen.setEnabled(true);
        eraser.setEnabled(true);
    }
    
    public static void EnableImageImport(){
    	ImportImageItem.setEnabled(true);
    }
    
    public static void EnableFreeImageImport(){
    	ImportFreeImageItem.setEnabled(true);
    }
    
    public static void DisableImageImport(){
    	ImportImageItem.setEnabled(false);
    }
    
    public static void DisableFreeImageImport(){
    	ImportFreeImageItem.setEnabled(false);
    }
        
    public static void AddContent(){
        // Draw Menu Bar
        JMenuBar jMenuBar = new JMenuBar();
        mainFrame.setJMenuBar(jMenuBar);

        // Add Menus
        JMenu fileMenu = new JMenu(" File ");
        JMenu editMenu = new JMenu(" Edit ");
        JMenu toolMenu = new JMenu(" Tools ");
        JMenu helpMenu = new JMenu(" Help ");
        JMenu followMenu= new JMenu(" Social Media ");

        fileMenu.setFont(new Font("default",Font.BOLD,13));
        editMenu.setFont(new Font("default",Font.BOLD,13));
        toolMenu.setFont(new Font("default",Font.BOLD,13));
        helpMenu.setFont(new Font("default",Font.BOLD,13));
        followMenu.setFont(new Font("default",Font.BOLD,13));

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(toolMenu);
        jMenuBar.add(helpMenu);
        jMenuBar.add(followMenu);
        jMenuBar.add(Box.createHorizontalGlue());
        jMenuBar.add(pen);
        jMenuBar.add(eraser);

        JMenuItem newProject = new JMenuItem("Create a new Project", new ImageIcon("images/project.png"));
        JMenuItem saveBrickItem = new JMenuItem("Save DNA Sequences", new ImageIcon("images/save.png"));
        JMenuItem saveLatexItem = new JMenuItem("Create Latex File", new ImageIcon("images/latex.png"));
        JMenuItem saveImage = new JMenuItem("View Draw Image", new ImageIcon("images/saveimg.png"));
        JMenuItem saveDrawSeqItem = new JMenuItem("Save Detailed DNA Data", new ImageIcon("images/save.png"));
        JMenuItem savePDF=new JMenuItem("Save PDF", new ImageIcon("images/download_pdf.png"));
        //JMenuItem Export=new JMenuItem("Export Source File", new ImageIcon("images/download_pdf.png"));
        JMenuItem Import=new JMenuItem("Import Source File", new ImageIcon("images/download_pdf.png"));
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newProject);
        fileMenu.add(new JSeparator());
        fileMenu.add(saveBrickItem);
        fileMenu.add(saveLatexItem);
        fileMenu.add(saveImage);
        fileMenu.add(saveDrawSeqItem);
        fileMenu.add(savePDF);
        fileMenu.add(new JSeparator());
        //fileMenu.add(Export);
        fileMenu.add(Import);
        //fileMenu.add(new JSeparator());
        fileMenu.add(exitItem);

        JMenuItem changeDimensionsItem = new JMenuItem("Edit Dimensions", new ImageIcon("images/edit.png"));
        JMenuItem clearItem = new JMenuItem("Clear", new ImageIcon("images/clear.png"));

        editMenu.add(changeDimensionsItem);
        editMenu.add(clearItem);

        JMenuItem gridDrawItem = new JMenuItem("Free Hand Molecular Canvas", new ImageIcon("images/freehand.png"));
        JMenuItem digitalDrawItem = new JMenuItem("Digitised Molecular Canvas", new ImageIcon("images/digitized.png"));
        JMenuItem canvasImageItem = new JMenuItem("Capture Canvas Screenshot", new ImageIcon ("images/screenshot.png"));
        JMenuItem estimatorItem=new JMenuItem("Estimator",new ImageIcon("images/calculator.png"));
         
        toolMenu.add(gridDrawItem);
        toolMenu.add(digitalDrawItem);
        toolMenu.add(new JSeparator());
        toolMenu.add(estimatorItem);
        toolMenu.add(canvasImageItem);
        toolMenu.add(ImportFreeImageItem);
        toolMenu.add(ImportImageItem);
        
        
        
        JMenuItem userManualItem = new JMenuItem("User Manual", new ImageIcon("images/user_manual.png"));
        JMenuItem productDemoItem = new JMenuItem("Product Demo",new ImageIcon("images/demo.png"));
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem productFeedback = new JMenuItem("Product Feedback", new ImageIcon("images/feedback.png"));

        helpMenu.add(userManualItem);
        helpMenu.add(productDemoItem);
        helpMenu.add(productFeedback);
        helpMenu.add(new JSeparator());
        helpMenu.add(aboutItem);

        JMenuItem facebook=new JMenuItem("Follow us on Facebook", new ImageIcon("images/fb.png"));
        JMenuItem twitter=new JMenuItem("Follow us on Twitter", new ImageIcon("images/twit.png"));
        //JMenuItem youtube=new JMenuItem("DNA Pen Youtube Channel", new ImageIcon("images/youtube.png"));
        JMenuItem quora=new JMenuItem("Follow topic on Quora", new ImageIcon("images/quora.png"));

        followMenu.add(facebook);
        followMenu.add(twitter);
        //followMenu.add(youtube);
        followMenu.add(quora);

        ImportImageItem.setEnabled(false);
        ImportFreeImageItem.setEnabled(false);
        
        if(newProjectCreated==false){
           // Export.setEnabled(false);
            Import.setEnabled(true);
            saveBrickItem.setEnabled(false);
            saveLatexItem.setEnabled(false);
            saveDrawSeqItem.setEnabled(false);
            savePDF.setEnabled(false);
            exitItem.setEnabled(false);
            changeDimensionsItem.setEnabled(false);
            clearItem.setEnabled(false);
            gridDrawItem.setEnabled(false);
            digitalDrawItem.setEnabled(false);
            canvasImageItem.setEnabled(false);
            estimatorItem.setEnabled(false);
            eraser.setEnabled(false);
            pen.setEnabled(false);
            
        }

        mainFrame.setVisible(true);

        // Defining Action Listeners for every Menu Item
        // Respective listeners will be called whenever a function is selected.
        newProject.addActionListener(new NewProjectListener());

        newProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                NewProjectListener newProject = new NewProjectListener();
                if((currentWindow==0)&&(framecounter==0)){
                    newProject.showInputDialogue();
                    framecounter=1;
                }
            }
        });
        saveBrickItem.addActionListener(new SaveBrickDataListener());
        saveLatexItem.addActionListener(new SaveLatexActionListener());
        saveImage.addActionListener(new SaveImageActionListener());
        saveDrawSeqItem.addActionListener(new SaveDrawDataActionListener());
        savePDF.addActionListener(new PDFListener());
        //Export.addActionListener(new ExportActionListener());
        Import.addActionListener(new ImportActionListener());
        exitItem.addActionListener(new ExitItemListener());
        ImportImageItem.addActionListener(new NewImportImagelistener());
        ImportFreeImageItem.addActionListener(new NewFreeImportImageActionListener());
        
        changeDimensionsItem.addActionListener(new EditDimensionActionListener());
        //eraseItem.addActionListener(new UndoActionListener());
        clearItem.addActionListener(new ClearActionListener());

        gridDrawItem.addActionListener(new FreeGridActionListener());
        digitalDrawItem.addActionListener(new DigitalGridActionListener());
        canvasImageItem.addActionListener(new CanvasScreenshotActionListener());
        estimatorItem.addActionListener(new EstimatorActionListener());


        aboutItem.addActionListener(new AboutActionListener());
        userManualItem.addActionListener(new UserManualActionListener());
        productDemoItem.addActionListener(new ProductDemoActionListener());
        productFeedback.addActionListener(new ProductFeedbackActionListener());

        pen.addActionListener(new PenActionListener());
        eraser.addActionListener(new UndoActionListener());

        //youtube.addActionListener(new YoutubeActionListener());
        facebook.addActionListener(new FacebookActionListener());
        twitter.addActionListener(new TwitterActionListener());
        quora.addActionListener(new QuoraActionListener());
    }
    public void acceptRejectLicense() {
        try {
            File tempFile = new File("TempFile");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write("TempFile");
            fileWriter.close();

            String filePath = tempFile.getAbsolutePath();
            System.out.println(filePath);
            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

            if (osName.toLowerCase().contains("mac")) {
                filePath = filePath.concat("DNA Pen.app/Contents/Resources/DNA Pen/configFile");
                configFile = new File(filePath);
            } else {
                filePath = filePath.concat("configFile");
                configFile = new File(filePath);
            }

            FileReader frConfig = new FileReader(configFile);
            BufferedReader bufferedReader = new BufferedReader(frConfig);

            String configFileData = "";

            while ((configFileData = bufferedReader.readLine()) != null) {
                System.out.println(configFileData);
                if (configFileData.contains("License:1")) {
                    licenseAccepted = true;
                }
            }
            bufferedReader.close();
            tempFile.delete();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(mainFrame, "Exception occurred." + e1,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }

        if (!licenseAccepted) {
            JFrame licenseFrame = new JFrame("License");
            JLabel licenseText = new JLabel("Sample License");
            JScrollPane jScrollPane = new JScrollPane(licenseText);
            JPanel jPanel = new JPanel();

            final JButton acceptButton = new JButton("Accept");
            final JButton rejectButton = new JButton("Reject");

            WindowListener exitListener = new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    int userChoice = JOptionPane.showConfirmDialog(null, "Are you sure ?",
                            "License Acceptance", JOptionPane.YES_NO_OPTION);
                    if (userChoice == 0)
                    {
                        System.exit(0);
                    }
                }
            };

            licenseFrame.addWindowListener(exitListener);

            try {
                File tempFile = new File("TempFile");
                FileWriter fileWriter = new FileWriter(tempFile);
                fileWriter.write("TempFile");
                fileWriter.close();

                String filePath = tempFile.getAbsolutePath();
                System.out.println(filePath);
                filePath = filePath.substring(0, filePath.indexOf("TempFile"));

                if (osName.toLowerCase().contains("mac")) {
                    filePath = filePath.concat("DNA Pen.app/Contents/Resources/DNA Pen/docs/License.txt");
                } else {
                    filePath = filePath.concat("/docs/License.txt");
                }

                FileReader fileReader = new FileReader(new File(filePath));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String tempString, licenseTextString = "<html><br/>";

                // Write File content in JLabel
                while ((tempString = bufferedReader.readLine()) != null) {
                    licenseTextString = licenseTextString.concat(tempString + "<br/>");
                }
                licenseTextString = licenseTextString.concat("<br/><br/></html>");
                licenseText.setText(licenseTextString);
                fileReader.close();
                tempFile.delete();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace(System.out);
                JOptionPane.showMessageDialog(mainFrame, "Exception occurred.",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e2) {
                e2.printStackTrace(System.out);
                JOptionPane.showMessageDialog(mainFrame, "Exception occurred.",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }

            jPanel.setLayout(new GridLayout(1, 2));
            jPanel.add(acceptButton);
            jPanel.add(rejectButton);

            jScrollPane.getViewport().setBackground(Color.white);

            licenseFrame.getContentPane().add(jScrollPane,BorderLayout.CENTER);
            licenseFrame.getContentPane().add(jPanel,BorderLayout.PAGE_END);

            Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

            licenseFrame.setBounds(screenDimensions.width / 4, screenDimensions.height/ 4,
                    screenDimensions.width / 2, screenDimensions.height / 2);
            licenseFrame.setResizable(false);
            licenseFrame.setVisible(true);

            acceptButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try {
                        FileWriter fileWriter = new FileWriter(configFile);
                        fileWriter.write("License:1");
                        fileWriter.close();

                        JOptionPane.showMessageDialog(mainFrame, "Thank you for accepting the license.",
                                "Success!", JOptionPane.INFORMATION_MESSAGE);
                        acceptButton.setEnabled(false);
                        licenseAccepted = true;
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(mainFrame, "Exception occurred.",
                                "Error!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            rejectButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    int userChoice = JOptionPane.showConfirmDialog(null, "Are you sure ?",
                            "License Acceptance", JOptionPane.YES_NO_OPTION);
                    if (userChoice == 0)
                    {
                        System.exit(0);
                    }
                }
            });

            while (acceptButton.isEnabled()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                    JOptionPane.showMessageDialog(mainFrame, "Exception occurred.",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            licenseFrame.dispose();
        }
    }

    public void doExit() {
        if (MainFrame.currentWindow == 0) {
            System.exit(0);
        }
        if (MainFrame.currentWindow == 1) {
            System.exit(0);
        }
        else if (MainFrame.currentWindow == 2) {
            if (FreeGridActionListener.isToBeSaved && !FreeGridActionListener.savedFunctionCalled) {
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

                if (userChoice == 1) {
                      JOptionPane.getRootFrame().dispose();
                }
                else if (userChoice == 2) {
                    JOptionPane.getRootFrame().dispose();
                }
                else{
                    allowexit=true;
                }
            }
            if(allowexit==true){
                allowexit=false;
                FreeGridActionListener.canvas.setVisible(false);
                MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
                System.exit(0);
            }

        } else if (MainFrame.currentWindow == 3) {
            if (DigitalGridActionListener.isToBeSaved && !DigitalGridActionListener.savedFunctionCalled) {
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

                if (userChoice == 1) {
                    JOptionPane.getRootFrame().dispose();

                }
                else if (userChoice == 2) {
                    JOptionPane.getRootFrame().dispose();
                }
                else{
                    allowexit=true;
                }
            }
            if(allowexit==true){
                allowexit=false;
                DigitalGridActionListener.canvas.setVisible(false);
                MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
                System.exit(0);
            }
        }
    }


    public static void main(String args[]) throws FileNotFoundException {
    	new Thread(null, new Runnable(){

			@Override
			public void run() {
				 MainFrame mainFrame1 = new MainFrame();
			        mainFrame1.acceptRejectLicense();
			        if (licenseAccepted) {
			            try {
							mainFrame1.drawFrame();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

			        }

			        else {
			            System.exit(0);
			        }
			}
    		
    	}, "1", 1<<26).start();
       
    }

}

