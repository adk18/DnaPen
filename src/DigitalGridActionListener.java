/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

/* DigitalGridActionListener.java
 * This class defines the Action Listener for the 'Digitzed Molecular Canvas'.
 * Whenever this class is initiated, a grid is drawn onto the screen
 * on which the user can start drawing.
 * The drawing is to be performed by simple clicking on the
 * on the grid boxes.
 * The user can also deselect a box by clicking on it again.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class DigitalGridActionListener implements ActionListener {

    static DrawCanvas canvas;
    static Graphics2D graphics2D;
    
  
    // Set Default Save Flags
    static boolean isToBeSaved = false;
    static boolean savedFunctionCalled = false;
    static boolean isImported = false;
    static JDialog jDialog = new JDialog();
    static double brickHeight = 3; // Set default Brick Height
    static double brickWidth = 7; // Set default Brick Width
    static ArrayList<XYCoordinates> xyCoordinatesBrickList = new ArrayList<XYCoordinates>();
    static DrawDataStack digitizedDataStack = new DrawDataStack();
    static boolean[][] gridValueAddedArray = new boolean[30][30];

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.FalseEnableContent();
        MainFrame.EnableImageImport();
        MainFrame.DisableFreeImageImport();
        
        
        if (!MainFrame.newBrickCreated) {
            MainFrame.dnaBrick = new DNABrick(brickHeight, brickWidth);
            MainFrame.newBrickCreated = true;
        }

        if (MainFrame.currentWindow == 3) {

            JOptionPane.showMessageDialog(null, "You are currently working on the Digitized Grid Molecular Canvas",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* This area checks if you're coming from the Free Hand Molecular Canvas
           * If yes, then it will give a pop-up to save any unsaved Draw Data
           */
        if ((MainFrame.currentWindow == 2)&&(MainFrame.framecounter==0)) {
          if (FreeGridActionListener.isToBeSaved && !FreeGridActionListener.savedFunctionCalled) {
//            if(true){
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to switch to Digitized Grid Molecular Canvas before saving your data?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 1) {
                    return;
                }


                else if (userChoice == 2) {  // Cancel
                    return;
                }
            }
            // Reset and clear Free Hand Molecular Canvas from the screen.
            FreeGridActionListener.canvas.setVisible(false);
            FreeGridActionListener.xCoordinateBrickList.clear();
            FreeGridActionListener.yCoordinateBrickList.clear();
            FreeGridActionListener.isToBeSaved = false;

            MainFrame.panelLeft.remove(0);
        }

        // Set value of currentWindow to 3 to indicate that Digital Molecular Canvas is active.
        MainFrame.currentWindow = 3;
        MainFrame.mainFrame.setTitle("DNA Pen - Digitised Molecular Canvas");
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // Define the Canvas and the Output Box
        JPanel jPanel = new JPanel();
        MainFrame.panelLeft.add(jPanel);
        jPanel.setSize(1350, 690);
        jPanel.setLayout(new GridLayout(1, 1));
        canvas = new DrawCanvas();
        jPanel.add(canvas);
    }

    // Draw Method

    public class DrawCanvas extends Canvas {
        BasicStroke basicStroke;
        int X, Y, pressed = 0;
        float dashes[] = { 5f, 5f };

        public DrawCanvas() {

            if(isImported==false){
                for(int i = 0; i <30; i++) {
                    for(int j = 0; j <30; j++) {
                        gridValueAddedArray[i][j] = false;
                    }
                }

            xyCoordinatesBrickList.clear();
            digitizedDataStack.clearData();

            }

            else{
                isImported=false;
                for(int i = 0; i <30; i++) {
                    for(int j = 0; j <30; j++) {
                        gridValueAddedArray[i][j] = false;
                    }
                }
                for(int i=0;i<xyCoordinatesBrickList.size();i++){
                    gridValueAddedArray[xyCoordinatesBrickList.get(i).getxCoordinate()][xyCoordinatesBrickList.get(i).getyCoordinate()]=true;
                }

            }
            setBackground(Color.white);
            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseListener());
            setSize(1350, 690);
            basicStroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 10f, dashes, 0f);

            for(int i = 0; i <30; i++) {
                for(int j = 0; j <30; j++) {
                    if(gridValueAddedArray[i][j] ==true){
                        fillElement(i,j);
                    }
                }
            }

        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }

        @Override
        public void paint(Graphics g) {
            graphics2D = (Graphics2D) g;
            if(pressed == 1)
            {
                graphics2D.setColor(Color.black);
                graphics2D.setStroke(basicStroke);

                if(Y>=0&&Y<30)
                    Y=0;
                else if (Y>=30&&Y<60)
                    Y=30;
                else if (Y>=60&&Y<90)
                    Y=60;
                else if (Y>=90&&Y<120)
                    Y=90;
                else if (Y>=120&&Y<150)
                    Y=120;
                else if (Y>=150&&Y<180)
                    Y=150;
                else if (Y>=180&&Y<210)
                    Y=180;
                else if (Y>=210&&Y<240)
                    Y=210;
                else if (Y>=240&&Y<270)
                    Y=240;
                else if (Y>=270&&Y<300)
                    Y=270;
                else if (Y>=300&&Y<330)
                    Y=300;
                else if (Y>=330&&Y<360)
                    Y=330;
                else if (Y>=360&&Y<390)
                    Y=360;
                else if (Y>=390&&Y<420)
                    Y=390;
                else if (Y>=420&&Y<450)
                    Y=420;
                else if (Y>=450&&Y<480)
                    Y=450;
                else if (Y>=480&&Y<510)
                    Y=480;
                else if (Y>=510&&Y<540)
                    Y=510;
                else if (Y>=540&&Y<570)
                    Y=540;
                else if (Y>=570&&Y<600)
                    Y=570;
                else if (Y>=600&&Y<630)
                    Y=600;
                else if (Y>=630&&Y<660)
                    Y=630;
                else if (Y>=660&&Y<690)
                    Y=660;
                else if (Y>=690&&Y<720)
                    Y=690;
                int z=Y/30;

                if(z%2==1){
                    if(X<45)
                        X=45;
                    else if(X>=45&&X<135)
                        X=45;
                    else if(X>=135&&X<225)
                        X=135;
                    else if(X>=225&&X<315)
                        X=225;
                    else if(X>=315&&X<405)
                        X=315;
                    else if(X>=405&&X<495)
                        X=405;
                    else if(X>=495&&X<585)
                        X=495;
                    else if(X>=585&&X<675)
                        X=585;
                    else if(X>=675&&X<765)
                        X=675;
                    else if(X>=765&&X<855)
                        X=765;
                    else if(X>=855&&X<945)
                        X=855;
                    else if(X>=945&&X<1035)
                        X=945;
                    else if(X>=1035&&X<1125)
                        X=1035;
                    else if(X>=1125&&X<1215)
                        X=1125;
                    else
                        X=1215;


                    System.out.println("\neven " +X);
                }
                else{
                    if(X>=0&&X<90)
                        X=0;
                    else if(X>=90&&X<180)
                        X=90;
                    else if(X>=180&&X<270)
                        X=180;
                    else if(X>=270&&X<360)
                        X=270;
                    else if(X>=360&&X<450)
                        X=360;
                    else if(X>=450&&X<540)
                        X=450;
                    else if(X>=540&&X<630)
                        X=540;
                    else if(X>=630&&X<720)
                        X=630;
                    else if(X>=720&&X<810)
                        X=720;
                    else if(X>=810&&X<900)
                        X=810;
                    else if(X>=900&&X<990)
                        X=900;
                    else if(X>=990&&X<1080)
                        X=990;
                    else if(X>=1080&&X<1170)
                        X=1080;
                    else if(X>=1170&&X<1260)
                        X=1170;
                    else if(X>=1260)
                        X=1260;
                    System.out.println(X - (X % 90));
                }
                System.out.println(Y - (Y % 30));
              //  z=Y/30;
                if(z%2==1){
                    if (!gridValueAddedArray[X/90][Y/30]) {
                        fillElement(X, Y);
                    }
                    else {
                        removeElement(X, Y);
                    }
                }

                else{
                    if (!gridValueAddedArray[X/90][Y/30]) {
                        fillElement(X, Y);
                    }
                    else {
                        removeElement(X, Y);
                    }
                }
            }

            else if (pressed == 2) {
                System.out.println("The default pressed valuse is set");
                graphics2D.setColor(Color.white);
                graphics2D.fillRect(0, 0, 1350, 690);

                for(int i = 0; i < 30; i++) {
                    for(int j = 0; j < 30; j++) {
                        gridValueAddedArray[i][j] = false;
                    }
                }
                xyCoordinatesBrickList.clear();
                digitizedDataStack.clearData();

                int constY1 = 0;
                int constY2;
                int constX = 0;
                graphics2D.setColor(Color.black);

                while (constY1 < 690) {
                    graphics2D.draw(new Line2D.Float(0, constY1, 1350, constY1));
                    constY1 += 30;
                }
                constY1=0;
                constY2=30;
                for(constY1=0,constY2=30;constY1<690;constY1+=30,constY2+=30)
                 {

                     if((constY1/30)%2==1) {
                         for(constX=45;constX<1350;constX += 90)
                         graphics2D.draw(new Line2D.Float(constX, constY1, constX, constY2));
                     }

                     else{
                             for(constX=0;constX<1350;constX += 90)
                             graphics2D.draw(new Line2D.Float(constX, constY1, constX, constY2));
                         }



                }
                pressed = 0;
            }

            /*
            //else if (pressed == 4) {
                XYCoordinates xyCoordinates = DigitalGridActionListener.digitizedDataStack.popData();
                int X = xyCoordinates.getxCoordinate();
                int Y = xyCoordinates.getyCoordinate();

                undoAction(X, Y);
            //}
            */

            else{
                int constY = 0;
                int constX = 0;
                while (constY < 690) {
                    graphics2D.draw(new Line2D.Float(0, constY, 1350, constY));
                    constY += 30;
                }
                int constY1=0;
                int constY2=30;
                for(constY1=0,constY2=30;constY1<690;constY1+=30,constY2+=30)
                {

                    if((constY1/30)%2==1) {
                        for(constX=45;constX<1350;constX += 90)
                        graphics2D.draw(new Line2D.Float(constX, constY1, constX, constY2));
                    }

                    else{
                        for(constX=0;constX<1350;constX += 90)
                        graphics2D.draw(new Line2D.Float(constX, constY1, constX, constY2));
                    }

                }

                int xValue = 0;
                int yValue = 0;
                for (int i = 0; i < xyCoordinatesBrickList.size(); i++) {
                    xValue = xyCoordinatesBrickList.get(i).getxCoordinate();
                    yValue = xyCoordinatesBrickList.get(i).getyCoordinate();
                    graphics2D.setColor(Color.BLUE);

                    if(yValue%2==1){
                            graphics2D.fillRect((xValue*90)+45, 30*yValue, 90, 30);
                    }
                    else
                        graphics2D.fillRect(90*xValue, 30*yValue, 90, 30);
                    //graphics2D.fillRect(90*xValue - ((90*xValue) % 90), 30*yValue - ((30*yValue) % 30), 90, 30);
                }
            }
        }
        
        
        public void fillGridCoordinate(int X, int Y) {
            //graphics2D = (Graphics2D) g;
            //graphics2D.setColor(Color.black);
                //graphics2D.setStroke(basicStroke);

                if(Y>=0&&Y<30)
                    Y=0;
                else if (Y>=30&&Y<60)
                    Y=30;
                else if (Y>=60&&Y<90)
                    Y=60;
                else if (Y>=90&&Y<120)
                    Y=90;
                else if (Y>=120&&Y<150)
                    Y=120;
                else if (Y>=150&&Y<180)
                    Y=150;
                else if (Y>=180&&Y<210)
                    Y=180;
                else if (Y>=210&&Y<240)
                    Y=210;
                else if (Y>=240&&Y<270)
                    Y=240;
                else if (Y>=270&&Y<300)
                    Y=270;
                else if (Y>=300&&Y<330)
                    Y=300;
                else if (Y>=330&&Y<360)
                    Y=330;
                else if (Y>=360&&Y<390)
                    Y=360;
                else if (Y>=390&&Y<420)
                    Y=390;
                else if (Y>=420&&Y<450)
                    Y=420;
                else if (Y>=450&&Y<480)
                    Y=450;
                else if (Y>=480&&Y<510)
                    Y=480;
                else if (Y>=510&&Y<540)
                    Y=510;
                else if (Y>=540&&Y<570)
                    Y=540;
                else if (Y>=570&&Y<600)
                    Y=570;
                else if (Y>=600&&Y<630)
                    Y=600;
                else if (Y>=630&&Y<660)
                    Y=630;
                else if (Y>=660&&Y<690)
                    Y=660;
                else if (Y>=690&&Y<720)
                    Y=690;
                int z=Y/30;

                if(z%2==1){
                    if(X<45)
                        X=45;
                    else if(X>=45&&X<135)
                        X=45;
                    else if(X>=135&&X<225)
                        X=135;
                    else if(X>=225&&X<315)
                        X=225;
                    else if(X>=315&&X<405)
                        X=315;
                    else if(X>=405&&X<495)
                        X=405;
                    else if(X>=495&&X<585)
                        X=495;
                    else if(X>=585&&X<675)
                        X=585;
                    else if(X>=675&&X<765)
                        X=675;
                    else if(X>=765&&X<855)
                        X=765;
                    else if(X>=855&&X<945)
                        X=855;
                    else if(X>=945&&X<1035)
                        X=945;
                    else if(X>=1035&&X<1125)
                        X=1035;
                    else if(X>=1125&&X<1215)
                        X=1125;
                    else
                        X=1215;


                    System.out.println("\neven " +X);
                }
                else{
                    if(X>=0&&X<90)
                        X=0;
                    else if(X>=90&&X<180)
                        X=90;
                    else if(X>=180&&X<270)
                        X=180;
                    else if(X>=270&&X<360)
                        X=270;
                    else if(X>=360&&X<450)
                        X=360;
                    else if(X>=450&&X<540)
                        X=450;
                    else if(X>=540&&X<630)
                        X=540;
                    else if(X>=630&&X<720)
                        X=630;
                    else if(X>=720&&X<810)
                        X=720;
                    else if(X>=810&&X<900)
                        X=810;
                    else if(X>=900&&X<990)
                        X=900;
                    else if(X>=990&&X<1080)
                        X=990;
                    else if(X>=1080&&X<1170)
                        X=1080;
                    else if(X>=1170&&X<1260)
                        X=1170;
                    else if(X>=1260)
                        X=1260;
                    System.out.println(X - (X % 90));
                }
                System.out.println(Y - (Y % 30));
                //int z=Y/30;
                if(z%2==1){
                    if (!gridValueAddedArray[X/90][Y/30]) {
                        GridfillElement(X, Y);
                    }
                    else {
                        //removeElement(X, Y);
                    }
                }

                else{
                    if (!gridValueAddedArray[X/90][Y/30]) {
                        GridfillElement(X, Y);
                    }
                    else {
                        //removeElement(X, Y);
                    }
                }
            }

        
        public void GridfillElement(int xCoordinate, int yCoordinate) {
            graphics2D.setColor(Color.BLUE);
            int z=yCoordinate/30;
            if(z%2==1){
                System.out.print("\n filling1"+ xCoordinate);
                System.out.print("\n filling2"+ yCoordinate);

                    graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
                }
            else
                System.out.print("\n filling1"+ xCoordinate+"\n");
                graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
               // graphics2D.fillRect(90*xCoordinate - ((90*xCoordinate) % 90), 30*yCoordinate - ((30*yCoordinate) % 30), 90, 30);
            if(z%2==1){
                gridValueAddedArray[(xCoordinate-45) / 90][yCoordinate / 30] = true;
                xyCoordinatesBrickList.add(new XYCoordinates(xCoordinate/ 90, yCoordinate / 30));
                digitizedDataStack.pushData(new XYCoordinates(xCoordinate/90, yCoordinate/30));
            }
            else {
                gridValueAddedArray[xCoordinate / 90][yCoordinate / 30] = true;
                xyCoordinatesBrickList.add(new XYCoordinates(xCoordinate/ 90, yCoordinate / 30));
                digitizedDataStack.pushData(new XYCoordinates(xCoordinate/90, yCoordinate/30 ));
            }

        }

        
        
        public void fillElement(int xCoordinate, int yCoordinate) {
            graphics2D.setColor(Color.BLUE);
            int z=yCoordinate/30;
            if(z%2==1){
                System.out.print("\n filling1"+ xCoordinate);
                System.out.print("\n filling2"+ yCoordinate);

                    graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
                }
            else
                System.out.print("\n filling1"+ xCoordinate+"\n");
                graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
               // graphics2D.fillRect(90*xCoordinate - ((90*xCoordinate) % 90), 30*yCoordinate - ((30*yCoordinate) % 30), 90, 30);
            if(z%2==1){
                gridValueAddedArray[(xCoordinate-45) / 90][yCoordinate / 30] = true;
                xyCoordinatesBrickList.add(new XYCoordinates(xCoordinate/ 90, yCoordinate / 30));
                digitizedDataStack.pushData(new XYCoordinates(xCoordinate/90, yCoordinate/30));
            }
            else {
                gridValueAddedArray[xCoordinate / 90][yCoordinate / 30] = true;
                xyCoordinatesBrickList.add(new XYCoordinates(xCoordinate/ 90, yCoordinate / 30));
                digitizedDataStack.pushData(new XYCoordinates(xCoordinate/90, yCoordinate/30 ));
            }

        }

        public void removeElement(int xCoordinate, int yCoordinate) {
            graphics2D.setColor(Color.white);
            int z=yCoordinate/30;
            if(z%2==1){
                graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
            }
            else
                graphics2D.fillRect(xCoordinate, yCoordinate, 90, 30);
            // graphics2D.fillRect(90*xCoordinate - ((90*xCoordinate) % 90), 30*yCoordinate - ((30*yCoordinate) % 30), 90, 30);
            if(z%2==1){
                gridValueAddedArray[xCoordinate/ 90][yCoordinate / 30] = false;
                digitizedDataStack.removeSpecificElement(xCoordinate/90, yCoordinate/30);
            }
            else {
                gridValueAddedArray[xCoordinate / 90][yCoordinate / 30] = false;
                digitizedDataStack.removeSpecificElement(xCoordinate/90, yCoordinate/30);
            }


            int xValue = 0;
            int yValue = 0;
            for (int i = 0; i < xyCoordinatesBrickList.size(); i++) {
                xValue = xyCoordinatesBrickList.get(i).getxCoordinate();
                yValue = xyCoordinatesBrickList.get(i).getyCoordinate();

                if(z%2==1){
                    if(xValue ==( xCoordinate / 90) && yValue == (yCoordinate / 30))
                        xyCoordinatesBrickList.remove(i);
                }
                else {
                    if (xValue == (xCoordinate / 90) && yValue == (yCoordinate / 30)) {
                        xyCoordinatesBrickList.remove(i);
                    }
                }
            }
        }

        class MyMouseListener extends MouseAdapter implements MouseMotionListener {

            @Override
            public void mousePressed(MouseEvent e) {
                isToBeSaved = true;
                savedFunctionCalled = false;
                pressed = 1;
                X = e.getX();
                Y = e.getY();
                canvas.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = 0;
                canvas.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                String string = "(" + Integer.toString(e.getX()) + ", "
                        + Integer.toString(e.getY()) + ")";
            }
        }
    }


}