import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/21/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExportActionListener implements ActionListener {

    int BrickHeight=FreeGridActionListener.brickHeight;
    int BrickWidth=FreeGridActionListener.brickWidth;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {


            if(MainFrame.currentWindow==2) {
                ArrayList<Integer> x=FreeGridActionListener.xCoordinateBrickList;
                ArrayList<Integer> y=FreeGridActionListener.yCoordinateBrickList;
                BufferedWriter bw = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/FreeGrid_SourceFile_"+MainFrame.ProjName+".txt"));
                bw.write(String.valueOf(MainFrame.currentWindow));
                bw.write("\n");
                bw.write(MainFrame.ProjPath);
                bw.write("\n");
                bw.write(MainFrame.ProjName);
                bw.write("\n");
                bw.write(String.valueOf(BrickHeight));
                bw.write("\n");
                bw.write(String.valueOf(BrickWidth));
                System.out.print("\n"+ x.size());
                for(int i=0;i<x.size();i++){
                    bw.write("\n");
                    bw.write(String.valueOf(x.get(i)));
                    bw.write("\n");
                    bw.write(String.valueOf(y.get(i)));
                }
                bw.close();
                JOptionPane.showMessageDialog(null, "Source File Saved!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else if(MainFrame.currentWindow==3) {
                ArrayList<XYCoordinates> xy = DigitalGridActionListener.xyCoordinatesBrickList;

                BufferedWriter bw = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitizedGrid_SourceFile_"+MainFrame.ProjName+".txt"));
                bw.write(String.valueOf(MainFrame.currentWindow));
                bw.write("\n");
                bw.write(MainFrame.ProjPath);
                bw.write("\n");
                bw.write(MainFrame.ProjName);
                System.out.print("\n"+ xy.size());
                for(int i=0;i<xy.size();i++){
                    bw.write("\n");
                    bw.write(String.valueOf(xy.get(i).getxCoordinate()));
                    bw.write("\n");
                    bw.write(String.valueOf(xy.get(i).getyCoordinate()));
                }
                bw.close();
                JOptionPane.showMessageDialog(null, "Source File Saved!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        }
        catch (IOException exp) {
            exp.printStackTrace();
        }

    }
}
