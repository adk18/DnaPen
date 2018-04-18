import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FreeGridActionListener implements ActionListener{

    static DrawCanvas canvas;
    static int brickHeight = 3; // Set default Brick Height
    static int brickWidth = 7; // Set default Brick Width
    static boolean Dimchanged = false;
    static boolean isToBeSaved = false;
    static boolean savedFunctionCalled = false;
    static ArrayList<Integer> xCoordinateBrickList = new ArrayList<Integer>();
    static ArrayList<Integer> yCoordinateBrickList = new ArrayList<Integer>();
    boolean dosave=false;
    static boolean isImported=false;

    

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.TrueEnableContent();
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        MainFrame.EnableFreeImageImport();
        MainFrame.DisableImageImport();
        MainFrame.image_imported = false;

        if (!MainFrame.newBrickCreated) {
            MainFrame.dnaBrick = new DNABrick(EditDimensionActionListener.BrickWallHeight, EditDimensionActionListener.BrickwallWidth);
            MainFrame.newBrickCreated = true;
        }

        if (MainFrame.currentWindow == 2) {

            JOptionPane.showMessageDialog(null, "You are currently working on the Free Grid Molecular Canvas",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            return;

        }

        if ((MainFrame.currentWindow == 3)&&(MainFrame.framecounter==0)) {
            if (DigitalGridActionListener.savedFunctionCalled==false&&DigitalGridActionListener.isToBeSaved==true) {

                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to switch to Free Grid Molecular Canvas before saving your data?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                    dosave=false;
                }
                else if (userChoice == 1) {
                    return;
                }

                else if (userChoice == 2) {
                    return;
                }
            }

            if(dosave==false){
                DigitalGridActionListener.canvas.setVisible(false);
                DigitalGridActionListener.xyCoordinatesBrickList.clear();
                DigitalGridActionListener.isToBeSaved = false;
                MainFrame.panelLeft.remove(0);
            }
        }

        // Set value of currentWindow to 2 to indicate that Free Hand Molecular Canvas is active.
        MainFrame.currentWindow = 2;
        MainFrame.mainFrame.setTitle("DNA Pen - Free Hand Molecular Canvas");
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);


        JPanel jPanel = new JPanel();
        MainFrame.panelLeft.add(jPanel);
        jPanel.setSize(1050, 700);
        jPanel.setLayout(new GridLayout(1,1));
        canvas = new DrawCanvas();
        jPanel.add(canvas);
        canvas.setLocation(150,5);
        canvas.setupOSC();
    }


    public static class DrawCanvas extends Canvas implements MouseListener, MouseMotionListener {


        static int ysize= (int) (700/brickHeight);
        static int xsize= (int) (1050/brickWidth);
        static boolean[][] gridValueAddedArray = new boolean[xsize][ysize];

        int X, Y = 0;
        int pressed = 0;


        static Image OSC;
        static int widthOfOSC;
        static int heightOfOSC;
        static Image undoBuffer;
        private int prevX, prevY;     // The previous location of the mouse.

        private int startX, startY;   // The starting position of the mouse.

        private boolean dragging;     // This is set to true when the user is drawing.

        static Graphics graphicsForDrawing;  // A graphics context for the applet.

        static Graphics offscreenGraphics;   // A graphics context for the off-screen canvas.

        static int xRightBound = -1;
        static int xLeftBound = -1;
        static int yUpBound = -1;
        static int yDownBound = -1;


        public DrawCanvas() {
        	
            setBackground(Color.white);
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.white);
            setSize(1050, 700);

            for (int i = 0; i < xsize; i++) {
                for (int j = 0; j < ysize; j++) {
                    gridValueAddedArray[i][j] = false;
                }
            }
            xCoordinateBrickList.clear();
            yCoordinateBrickList.clear();
           // setupOSC();
        }


        private void setupOSC() {
            if (OSC == null || widthOfOSC != getSize().width || heightOfOSC != getSize().height) {
                // Create the OSC, or make a new one if canvas size has changed.

                OSC = null;  // (If OSC & undoBuffer already exist, this frees up the memory.)
                undoBuffer = null;
               // System.out.println(getSize().width+"rehhhhhhhhhhhhhhhhhhhhhhhh");
                OSC = createImage(getSize().width, getSize().height);
                widthOfOSC = getSize().width;
                heightOfOSC = getSize().height;
                Graphics OSG = OSC.getGraphics();  // Graphics context for drawing to OSC.
                OSG.setColor(getBackground());
                OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
                OSG.dispose();
                
                undoBuffer = createImage(widthOfOSC, heightOfOSC);
                OSG = undoBuffer.getGraphics();  // Graphics context for drawing to the undoBuffer.
                OSG.setColor(getBackground());
                OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
                OSG.dispose();

            }
        }
        
        public Graphics getOSG() {
            // Return a graphics context for drawing to the off-screen canvas.
            // A new canvas is created if necessary.  Note that the graphics
            // context should not be kept for any length of time, in case the
            // size of the canvas changes.
            setupOSC();
            
            return OSC.getGraphics();
        }


        public void clearOSC() {
            // Fill the off-screen canvas with the background color.
            // (First, save the current image in the undo buffer.)
            System.out.print("Free Grid clear has been called to clear");
            Graphics undoGr = undoBuffer.getGraphics();
            undoGr.drawImage(OSC, 0, 0, null);
            undoGr.dispose();
            Graphics OSG = OSC.getGraphics();
            OSG.setColor(Color.white);
            OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
            OSG.dispose();
            System.out.print("Free Grid clear has been called disposed");
        }


        @Override
        public void update(Graphics g) {
            paint(g);
        }


        @Override
        public void paint(Graphics g) {
            setupOSC();
            g.drawImage(OSC, 0, 0, this);

            if (pressed==7){
                xCoordinateBrickList.clear();
                yCoordinateBrickList.clear();
                isImported=false;
                System.out.println("Imported "+pressed+" has been called");
                brickHeight=ImportActionListener.bh;
                brickWidth=ImportActionListener.bw;
                System.out.print("New dimensions: "+ brickHeight+brickWidth);
                xCoordinateBrickList=ImportActionListener.xC;
                yCoordinateBrickList=ImportActionListener.yC;
                repaint();
                System.out.println("\nimport was called");

            }

            if (pressed==18){
                System.out.print("Free Grid clear has been called 3");
                clearOSC();
                repaint();
                setBackground(Color.white);
                pressed=0;
                System.out.print("Free Grid has been cleared");
            }
            //manually clear
            if (pressed==2){
                System.out.println("Freegrid clear for "+pressed+" has been called");
                brickHeight=EditDimensionActionListener.BrickWallHeight;
                brickWidth=EditDimensionActionListener.BrickwallWidth;
                System.out.print("New dimensions: "+ brickHeight+brickWidth);
                pressed=18;
                repaint();
                System.out.println("clear was called again");
            }

        }


        public Color getCurrentColor() {
            // Check the colorChoice menu to find the currently
            // selected color, and return the appropriate color
            // object.
            if(pressed==4)   {

                return Color.white;
            }
            else
                return Color.black;
        }

        private static void putFigure(Graphics g,
                               int x1, int y1, int x2, int y2) {
            Graphics2D g2=(Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(x1, y1, x2, y2);
        }

        public static void putMultiFigure(Graphics g, int x1, int y1, int x2, int y2) {
            putFigure(g, x1, y1, x2, y2);


        } // end putMultiFigure

        @Override
        public void mouseClicked(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            isToBeSaved = true;
            savedFunctionCalled = false;
            if (dragging == true)  // Ignore mouse presses that occur
                return;            //    when user is already drawing a curve.
            prevX = startX = e.getX();  // Save mouse coordinates.
            prevY = startY = e.getY();
            //System.out.println("Mouse x po"+ "sition is "+prevX);
            //System.out.println("Mouse y position is "+prevY);
            graphicsForDrawing = getGraphics();  // For drawing on the screen.
            graphicsForDrawing.setColor(getCurrentColor());

            offscreenGraphics = getOSG();  // For drawing on the canvas.
            offscreenGraphics.setColor(getCurrentColor());
            dragging = true;  // Start drawing.
            calcDom(prevX, prevY);

        }

        public static void calcDom(int xCoordinate, int yCoordinate) {
            int i = xCoordinate;
            int j = yCoordinate;
            xRightBound = -1;
            xLeftBound = -1;
            yUpBound = -1;
            yDownBound = -1;

            while (true) {
                if (i % brickWidth == 0) {
                    xRightBound = i;
                    break;
                } else {
                    i++;
                }
            }

            i = xCoordinate;

            while (true) {
                if (i % brickWidth == 0) {
                    xLeftBound = i;
                    break;
                } else {
                    i--;
                }
            }

            i = yCoordinate;

            while (true) {
                if (i % brickHeight == 0) {
                    yUpBound = i;
                    break;
                } else {
                    i--;
                }
            }

            i = yCoordinate;

            while (true) {
                if (i % brickHeight == 0) {
                    yDownBound = i;
                    break;
                } else {
                    i++;
                }
            }
        }

        
        public static void myDomChange(int xCoordinate, int yCoordinate){
            boolean change = false;
            if (xCoordinate > xRightBound || xCoordinate < xLeftBound) {
                change = true;
                if (!gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]) {
                    xCoordinateBrickList.add(xCoordinate / brickWidth);
                    yCoordinateBrickList.add(yCoordinate / brickHeight);
                    gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = true;
                }

                System.out.println("x: " + xCoordinate + " added w:"+ brickWidth+"\n");

                calcDom(xCoordinate, yCoordinate);
            } else if (yCoordinate < yUpBound || yCoordinate > yDownBound) {
                change = true;
                if (!gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]) {
                    xCoordinateBrickList.add(xCoordinate / brickWidth);
                    yCoordinateBrickList.add(yCoordinate / brickHeight);
                    System.out.println(xCoordinateBrickList);
                    System.out.println(yCoordinateBrickList);
                    gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = true;
                }

                System.out.println("y: " + yCoordinate + " added w: "+ brickWidth+"\n");

                calcDom(xCoordinate, yCoordinate);
            }
        }
        
        
        public void domChange(int xCoordinate, int yCoordinate) {
            int remindexx,remindexy;
            if (pressed==4){
                boolean change = false;
                if (xCoordinate > xRightBound || xCoordinate < xLeftBound) {
                    change = true;
                    if (gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]==true) {
                        remindexx=xCoordinateBrickList.lastIndexOf(xCoordinate / brickWidth);
                        if(remindexx!=-1)
                            xCoordinateBrickList.remove(remindexx);

                        remindexy=yCoordinateBrickList.lastIndexOf(yCoordinate / brickHeight) ;
                        if(remindexy!=-1)
                            yCoordinateBrickList.remove(remindexy);
                        gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = false;
                    }

                    //System.out.println("x: " + xCoordinate + " removed\n");

                    calcDom(xCoordinate, yCoordinate);
                } else if (yCoordinate < yUpBound || yCoordinate > yDownBound) {
                    change = true;
                    if (gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]==true) {
                        remindexx=xCoordinateBrickList.lastIndexOf(xCoordinate / brickWidth);
                        if(remindexx!=-1)
                            xCoordinateBrickList.remove(remindexx);

                        remindexy=yCoordinateBrickList.lastIndexOf(yCoordinate / brickHeight) ;
                        if(remindexy!=-1)
                            yCoordinateBrickList.remove(remindexy);
                        gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = false;
                    }

                    //System.out.println("y: " + yCoordinate + " removed\n");

                    calcDom(xCoordinate, yCoordinate);
                }
            }

            else if(pressed==0){
                boolean change = false;
                if (xCoordinate > xRightBound || xCoordinate < xLeftBound) {
                    change = true;
                    if (!gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]) {
                        xCoordinateBrickList.add(xCoordinate / brickWidth);
                        yCoordinateBrickList.add(yCoordinate / brickHeight);
                        gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = true;
                    }

                    System.out.println("x: " + xCoordinate + " added w:"+ brickWidth+"\n");

                    calcDom(xCoordinate, yCoordinate);
                } else if (yCoordinate < yUpBound || yCoordinate > yDownBound) {
                    change = true;
                    if (!gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight]) {
                        xCoordinateBrickList.add(xCoordinate / brickWidth);
                        yCoordinateBrickList.add(yCoordinate / brickHeight);
                        System.out.println(xCoordinateBrickList);
                        System.out.println(yCoordinateBrickList);
                        gridValueAddedArray[xCoordinate / brickWidth][yCoordinate / brickHeight] = true;
                    }

                    System.out.println("y: " + yCoordinate + " added w: "+ brickWidth+"\n");

                    calcDom(xCoordinate, yCoordinate);
                }
            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.
            dragging = false;

            graphicsForDrawing.dispose();
            offscreenGraphics.dispose();
            graphicsForDrawing = null;
            offscreenGraphics = null;
            xRightBound = -1;
            xLeftBound = -1;
            yUpBound = -1;
            yDownBound = -1;

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.

            int x = e.getX();   // x-coordinate of mouse.
            int y = e.getY();   // y=coordinate of mouse.


            // Draw the line on the applet and on the off-screen canvas.
            putMultiFigure(graphicsForDrawing,prevX, prevY, x, y);
            putMultiFigure(offscreenGraphics,prevX, prevY, x, y);
            domChange(x, y);
            prevX = x;  // Save coords for the next call to mouseDragged or mouseReleased.
            prevY = y;


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }


    }

}


 	