import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/21/13
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportActionListener implements ActionListener {

    static int bh;
    static int bw;
    static int cw;
    JFileChooser chooser;
    String choosertitle;
    static ArrayList <Integer> xC;
    static ArrayList <Integer> yC;
    static ArrayList <XYCoordinates> xy;
    @Override
    public void actionPerformed(ActionEvent e) {

    String currentFile = null;
    JFileChooser fc=new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc = new JFileChooser();
            int value = fc.showOpenDialog(null);
        if(value == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(f));
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String st = "";
            try {

                st=br.readLine();
                cw=Integer.parseInt(st);
                st=br.readLine();
                MainFrame.ProjPath=String.valueOf(st);
                System.out.print("\n"+MainFrame.ProjPath);
                st=br.readLine();
                MainFrame.ProjName=String.valueOf(st);
                System.out.print("\n"+MainFrame.ProjName);


                if(cw==2){
                    FreeGridActionListener.isImported=true;
                    xC=new ArrayList<Integer>();
                    yC=new ArrayList<Integer>();
                    st=br.readLine();
                    bh=(Integer.parseInt(st));
                    st=br.readLine();
                    bw=(Integer.parseInt(st));
                    while((st = br.readLine()) != null){
                        xC.add(Integer.parseInt(st));
                        System.out.print("\n"+Integer.parseInt(st));
                        st=br.readLine();
                        yC.add(Integer.parseInt(st));
                        System.out.print("  "+Integer.parseInt(st));
                    }

                    FreeGridActionListener.xCoordinateBrickList.clear();
                    FreeGridActionListener.yCoordinateBrickList.clear();
                    System.out.print("\nFree Grid clear has been called from edit dimension");
                    FreeGridActionListener.canvas.pressed = 7;
                    FreeGridActionListener.canvas.repaint();
                    FreeGridActionListener.canvas.setBackground(Color.white);

                }

                else{
                    DigitalGridActionListener.isImported=true;
                    xy=new ArrayList<XYCoordinates>();
                    xy.clear();
                    DigitalGridActionListener.xyCoordinatesBrickList.clear();
                    DigitalGridActionListener.digitizedDataStack.clearData();
                    int x,y;
                    while((st = br.readLine()) != null){
                        x=Integer.parseInt(st);
                        st=br.readLine();
                        y=Integer.parseInt(st);
                        DigitalGridActionListener.xyCoordinatesBrickList.add(new XYCoordinates(x,y));
                        System.out.print("\n"+x);
                        System.out.print(" "+y);
                    }


                    DigitalGridActionListener.canvas.repaint();
                    System.out.print("\nDigi Grid clear has been called from edit dimension");
                }


            }
            catch (IOException ep) {
                ep.printStackTrace();
            }
        }
    }

}
