import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/19/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditDimensionActionListener implements ActionListener {

    static int BrickwallWidth;
    static int BrickWallHeight;
    Object selection1;
    boolean show=false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.currentWindow==3){
            JOptionPane.showMessageDialog(null, "In order to change dimensions, please select Free Grid Molecular Canvas",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
        }

        else if(MainFrame.currentWindow==2){
            if (FreeGridActionListener.savedFunctionCalled==false) {

                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "All previous data will be lost \n" +
                                "Change Dimension anyway?",
                        "Save Resource",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                   show=true;
                }

                else if (userChoice == 1) {
                    return;
                }
            }

            if(show==true){
                show=false;
                String[] selectionValues1 = new String[]{ "1050nm x 699nm", "525nm x 348nm", "350nm x 231nm", "259nm x 174nm", "210nm x 138nm", "175nm x 114nm", "147nm x 99nm", "126nm x 87nm","112nm x 75nm" };
                String initialSelection1 = "1050nm x 699nm";
                selection1 = (JOptionPane.showInputDialog(null, "Select a dimension for the Brick Wall",
                        "Dimensions (nmxnm)", JOptionPane.QUESTION_MESSAGE, null, selectionValues1, initialSelection1));

                System.out.print("value selected: " + selection1);

                if(selection1.equals("1050nm x 699nm")) {
                    BrickWallHeight=3;
                    BrickwallWidth=7;
                    System.out.print("\nFree Grid clear has been called 1");
                }
                else if(selection1.equals("525nm x 348nm")){
                    BrickWallHeight=6;
                    BrickwallWidth=14;
                    System.out.print("\nFree Grid clear has been called 2");
                }
                else if(selection1.equals("350nm x 231nm")){
                    BrickWallHeight=9;
                    BrickwallWidth=21;
                    System.out.print("\nFree Grid clear has been called 3");
                }
                else if(selection1.equals("259nm x 174nm")){
                    BrickWallHeight=12;
                    BrickwallWidth=28;
                    System.out.print("\nFree Grid clear has been called 4");
                }
                else if(selection1.equals("210nm x 138nm")){
                    BrickWallHeight=15;
                    BrickwallWidth=35;
                    System.out.print("\nFree Grid clear has been called 5");
                }
                else if(selection1.equals("175nm x 114nm")){
                    BrickWallHeight=18;
                    BrickwallWidth=42;
                    System.out.print("\nFree Grid clear has been called 6");
                }
                else if(selection1.equals("147nm x 99nm")){
                    BrickWallHeight=21;
                    BrickwallWidth=49;
                    System.out.print("\nFree Grid clear has been called 7");
                }
                else if(selection1.equals( "126nm x 87nm")){
                    BrickWallHeight=24;
                    BrickwallWidth=56;
                    System.out.print("\nFree Grid clear has been called 8");
                }
                else if(selection1.equals("112nm x 75nm")){
                    BrickWallHeight=27;
                    BrickwallWidth=63;
                    System.out.print("\nFree Grid clear has been called 9");
                }
                else{
                    BrickWallHeight=3;
                    BrickwallWidth=7;
                    System.out.print("\nFree Grid clear has been called 10");

                }
                FreeGridActionListener.xCoordinateBrickList.clear();
                FreeGridActionListener.yCoordinateBrickList.clear();
                System.out.print("\nFree Grid clear has been called from edit dimension");
                FreeGridActionListener.canvas.pressed = 2;
                FreeGridActionListener.canvas.repaint();
                FreeGridActionListener.canvas.setBackground(Color.white);


            }

        }


    }
}
