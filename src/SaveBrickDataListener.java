/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SaveBrickDataListener implements ActionListener {

    static boolean alreadySavedDetailedDNA=false;


    static ArrayList<Domains> xyDrawdataListDigital = SaveDrawDataActionListener.xyDrawdataListDigital;
    static ArrayList<Domains> xyDrawdataListFree = SaveDrawDataActionListener.xyDrawdataListFree;
    int i;

    BrickData brickdata=new BrickData();
    @Override
    public void actionPerformed(ActionEvent e) {

        BufferedWriter bufferedWriter = null;
        if (MainFrame.newProjectCreated==true)
        {
            if (MainFrame.currentWindow == 2) {
                if (false) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    FreeGridActionListener.isToBeSaved = false;
                    if(MainFrame.currentWindow==2) {
                        File file=new File(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                        if(!file.exists()){
                            JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else{
                            bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
                            writeHeadersCanvasFree(bufferedWriter);
                        }
                    }
                    else if(MainFrame.currentWindow==3) {
                        File file=new File(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                        if(!file.exists()){
                            JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else{
                            bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
                            writeHeadersCanvasDigital(bufferedWriter);
                        }
                    }

                    bufferedWriter.write("\n\nDraw Sequence Coordinates\n\n");



                    bufferedWriter.write("\n\nDNA Brick Sequence\n\n");
                    bufferedWriter.write(MainFrame.dnaBrick.toString());

                    bufferedWriter.write("\n\n Generated using DNA Pen. (http://www.guptalab.org/dnapen/)");
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);

                    FreeGridActionListener.savedFunctionCalled = true;

                }
                catch (IOException e1)
                {
                    JOptionPane.showMessageDialog(null, "The File could not be Saved!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (MainFrame.currentWindow == 3) {
                if (false) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    ArrayList<XYCoordinates> xyCoordinatesList = DigitalGridActionListener.xyCoordinatesBrickList;
                    if(MainFrame.currentWindow==2){
                        File file=new File(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                        if(!file.exists()){
                            JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else{
                            bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
                            writeHeadersCanvasFree(bufferedWriter);
                        }
                    }
                    else if(MainFrame.currentWindow==3) {
                        File file=new File(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                        if(!file.exists()){
                            JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                                    "Success!", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else{
                            bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
                            writeHeadersCanvasDigital(bufferedWriter);
                        }
                    }


                    bufferedWriter.write("\n\nDraw Sequence Coordinates\n\n");
                    bufferedWriter.write("\n\n Generated using DNA Pen. (http://www.guptalab.org/dnapen/)");

                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);

                    DigitalGridActionListener.savedFunctionCalled = true;
                }
                catch (IOException e1)
                {
                    JOptionPane.showMessageDialog(null, "The File could not be Saved!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Sequence drawn. Choose a Grid to draw !",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void writeHeadersCanvasDigital(BufferedWriter bufferedWriter) {

        try {
            bufferedWriter.write("Seq No.");
            bufferedWriter.write(",");
            bufferedWriter.write("DNA Sequence");


            for(i=0;i<xyDrawdataListDigital.size();i++){
                bufferedWriter.write("\n");

                bufferedWriter.write(String.valueOf(i+1));
                bufferedWriter.write(",");
                bufferedWriter.write(xyDrawdataListDigital.get(i).getDomainOne()+xyDrawdataListDigital.get(i).getDomainTwo()+
                        xyDrawdataListDigital.get(i).getDomainThree()+xyDrawdataListDigital.get(i).getDomainFour());

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void writeHeadersCanvasFree(BufferedWriter bufferedWriter) {

        try {

            bufferedWriter.write("Seq No.");
            bufferedWriter.write(",");
            bufferedWriter.write("DNA Sequence");


            for(i=0;i<xyDrawdataListFree.size();i++){
                bufferedWriter.write("\n");

                bufferedWriter.write(String.valueOf(i+1));
                bufferedWriter.write(",");
                bufferedWriter.write(xyDrawdataListFree.get(i).getDomainOne()+xyDrawdataListFree.get(i).getDomainTwo()+
                        xyDrawdataListFree.get(i).getDomainThree()+xyDrawdataListFree.get(i).getDomainFour());

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
