import com.itextpdf.text.pdf.Barcode128;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: shikhar
 * Date: 8/15/13
 * Time: 9:52 PM
 */
public class SaveLatexActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.currentWindow==2){
            int fullfree=0,halffree=0;
            File file=new File(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
            if(!file.exists()){
                JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            else{
                for(int i=0;i<SaveDrawDataActionListener.xyDrawdataListFree.size();i++){
                    if(SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainOne()!=""&&SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainTwo()!=""&&
                            SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainThree()!=""&&SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainFour()!=""){

                        fullfree++;
                    }
                    if(SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainOne()==""&&SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainTwo()==""){

                        halffree++;
                    }
                    if(SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainThree()==""&&SaveDrawDataActionListener.xyDrawdataListFree.get(i).getDomainFour()==""){

                        halffree++;
                    }
                }
                File dir = new File(MainFrame.ProjPath+"/"+"FreeGrid_LateX_Files");
                dir.mkdirs();
                File teXFile=new File(MainFrame.ProjPath+"/"+"FreeGrid_LateX_Files"+"/"+"FreeGrid_LatexFile_DNAData_"+
                        MainFrame.ProjName+".tex");
                int totalfree=fullfree+halffree;
                File imageFile=new File(MainFrame.ProjPath+"/"+"FreeGrid_LateX_Files"+"/"+"FreeGrid_LatexFile_DNAData_"+
                        MainFrame.ProjName+"_DrawImage"+".png");
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                java.awt.Rectangle screenRectangle = new java.awt.Rectangle(screenSize);
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.print("waiting");

                BufferedImage image = robot.createScreenCapture(screenRectangle);
                image = image.getSubimage(0, 50, 1366,650);
                try {
                    ImageIO.write(image, "png", imageFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Date date=new Date();
                Barcode128 barcode = new Barcode128();
                barcode.setGenerateChecksum(true);
                barcode.setCode(String.valueOf(date.getTime())+ "Data_" + MainFrame.ProjName);
                java.awt.Image img = barcode.createAwtImage(Color.BLACK, Color.WHITE);
                BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                outImage.getGraphics().drawImage(img, 0, 0, null);
                ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
                try {
                    ImageIO.write(outImage, "png", bytesOut);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    bytesOut.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                byte[] pngImageData = bytesOut.toByteArray();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(MainFrame.ProjPath+"/"+"FreeGrid_LateX_Files"+"/"+"FreeGrid_LatexFile_DNAData_"+
                            MainFrame.ProjName+"_Barcode"+".png");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.write( pngImageData);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


                try {
                    FileWriter fw=new FileWriter(teXFile.getAbsolutePath());
                    BufferedWriter bw=new BufferedWriter(fw);
                    bw.write("\\documentclass[12pt]{article}\n" +
                            "\\usepackage{pdflscape}\n" +
                            "\\usepackage{caption}\n" +
                            "\\usepackage[pdftex]{graphicx}  \n" +
                            "\\usepackage{rotating}\n" +
                            "\\usepackage{amssymb}\n" +
                            "\\setcounter{tocdepth}{3}\n" +
                            "\\usepackage{graphicx}\n" +
                            "%\\usepackage{subcaption}\n" +
                            "\\usepackage{subfigure}\n" +
                            "\\usepackage{longtable}\n" +
                            "%  #############################\n" +
                            "\\topmargin=-30pt\n" +
                            "\\textheight=648pt\n" +
                            "\\oddsidemargin=0pt\n" +
                            "\\textwidth=468pt\n" +
                            "%  ##############################\n" +
                            "\\pagestyle{plain}\n" +
                            "\\renewcommand{\\baselinestretch}{1.15}\n" +
                            "\\begin{document}\n" +
                            "\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "%\\centering\n" +
                            "\\includegraphics{FreeGrid_LatexFile_DNAData_"+MainFrame.ProjName+"_Barcode.png}\n" +
                            "%\\caption{Barcode Identifier}\n" +
                            "\\label{barcode}\n" +
                            "\\end{figure}\n" +
                            "\n" +
                            "\\begin{center}\n" +
                            "{\\bf DNA Pen Data Sheet-Free Grid}\n" +
                            "\\end{center}\n" +
                            "\\[\n" +
                            "\\begin{array}{|c|c|}\n" +
                            "\\hline\n" +
                            " \\mbox{\\bf Description} & \\mbox{\\bf Value} \\\\\\hline\\hline\n" +
                            "\\mbox{Project Name} &"+MainFrame.ProjName+" \\\\\n" +
                            "\\mbox{Total number of Tiles} &"+totalfree+"  \\\\ \n" +
                            "\\mbox{Number of Half-Tiles} &"+halffree+"  \\\\\n" +
                            "\\mbox{Number of Full-Tiles} &"+fullfree+" \\\\\n" +
                            "\\mbox{Dimension of Molecular Canvas (Brick-Wall)} &"+((700/FreeGridActionListener.brickHeight)*3)+" \\times "+((1050/FreeGridActionListener.brickWidth)*7)+" \\mbox{nms}^2 \\\\\n" +
                            "\\hline\n" +
                            "\\end{array}\n" +
                            "\\]\n" +
                            "\\end{landscape}\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "\\centering\n" +
                            "\\includegraphics[scale=0.51]{FreeGrid_LatexFile_DNAData_"+MainFrame.ProjName+"_DrawImage.png}\n" +
                            "%\\caption{Barcode Identifier}\n" +
                            "\\label{digitalgrid}\n" +
                            "\\end{figure}\n" +
                            "\\end{landscape}\n" +
                            /*"\\newpage\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "%\\centering\n" +
                            "\\includegraphics[scale=0.9]{TileView.png}\n" +
                            "%\\caption{Barcode Identifier}\n" +
                            "\\label{tileview}\n" +
                            "\\end{figure}\n" +
                            "\\end{landscape}\n" +*/
                            "{\\small\n" +
                            "\\begin{landscape}\n" +
                            "\n" +
                            "    \\centering\n" +
                            "   \\begin{longtable}{|l|l|l|l|}\n" +
                            " %\\caption{DNA Data}\\\\\n" +
                            "\\hline \\multicolumn{4}{l}{\\textit{\\bf Continued on next page, generate by DNA-Cloud, http://www.guptalab.org/dnapen}} \\\\\n" +
                            "\\endfoot\n" +
                            "\\hline\n" +
                            "\\endlastfoot\n" +
                            "\\hline\n" +
                            "   \\mbox{\\bf Seq ID} &\\mbox{\\bf X-Coordinate} & \\mbox{\\bf Y-Coordinate} & \\mbox{\\bf DNA  Sequence Generated} \\\\\\hline\\hline\n" +
                            freeSequence()+
                            " \\hline\n" +
                            "    \\end{longtable}\n" +
                            "{\\bf Generated by DNA Pen, http://www.guptalab.org/dnapen}\n" +
                            "\\end{landscape}\n" +
                            "}\n" +
                            "\\end{document}");
                    bw.close();
                    JOptionPane.showMessageDialog(null, "LateX file successfully generated.",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        else if (MainFrame.currentWindow==3){
            int full=0,half=0;
            File file=new File(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
            if(!file.exists()){
                JOptionPane.showMessageDialog(null, "File saved successfully",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else{
                for(int i=0;i<SaveDrawDataActionListener.xyDrawdataListDigital.size();i++){
                    if(SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainOne()!=""&&SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainTwo()!=""&&
                            SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainThree()!=""&&SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainFour()!=""){

                        full++;
                    }
                    if(SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainOne()==""&&SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainTwo()==""){

                        half++;
                    }
                    if(SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainThree()==""&&SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getDomainFour()==""){

                        half++;
                    }
                }
                int total=full+half;
                File dir = new File(MainFrame.ProjPath+"/"+"DigitalGrid_LateX_Files");
                dir.mkdirs();
                File teXFile=new File(MainFrame.ProjPath+"/"+"DigitalGrid_LateX_Files"+"/"+"DigitalGrid_LatexFile_DNAData_"+
                        MainFrame.ProjName+".tex");
                File imageFile=new File(MainFrame.ProjPath+"/"+"DigitalGrid_LateX_Files"+"/"+"DigitalGrid_LatexFile_DNAData_"+
                        MainFrame.ProjName+"_DrawImage"+".png");
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                java.awt.Rectangle screenRectangle = new java.awt.Rectangle(screenSize);
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.print("waiting");

                BufferedImage image = robot.createScreenCapture(screenRectangle);
                image = image.getSubimage(0, 50, 1366,650);
                try {
                    ImageIO.write(image, "png", imageFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                Date date=new Date();
                Barcode128 barcode = new Barcode128();
                barcode.setGenerateChecksum(true);
                barcode.setCode(String.valueOf(date.getTime())+ "Data_" + MainFrame.ProjName);
                java.awt.Image img = barcode.createAwtImage(Color.BLACK, Color.WHITE);
                BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                outImage.getGraphics().drawImage(img, 0, 0, null);
                ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
                try {
                    ImageIO.write(outImage, "png", bytesOut);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    bytesOut.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                byte[] pngImageData = bytesOut.toByteArray();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(MainFrame.ProjPath+"/"+"DigitalGrid_LateX_Files"+"/"+"DigitalGrid_LatexFile_DNAData_"+
                            MainFrame.ProjName+"_Barcode"+".png");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.write( pngImageData);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    FileWriter fw=new FileWriter(teXFile.getAbsolutePath());
                    BufferedWriter bw=new BufferedWriter(fw);
                    bw.write("\\documentclass[12pt]{article}\n" +
                            "\\usepackage{pdflscape}\n" +
                            "\\usepackage{caption}\n" +
                            "\\usepackage[pdftex]{graphicx}  \n" +
                            "\\usepackage{rotating}\n" +
                            "\\usepackage{amssymb}\n" +
                            "\\setcounter{tocdepth}{3}\n" +
                            "\\usepackage{graphicx}\n" +
                            "%\\usepackage{subcaption}\n" +
                            "\\usepackage{subfigure}\n" +
                            "\\usepackage{longtable}\n" +
                            "%  #############################\n" +
                            "\\topmargin=-30pt\n" +
                            "\\textheight=648pt\n" +
                            "\\oddsidemargin=0pt\n" +
                            "\\textwidth=468pt\n" +
                            "%  ##############################\n" +
                            "\\pagestyle{plain}\n" +
                            "\\renewcommand{\\baselinestretch}{1.15}\n" +
                            "\\begin{document}\n" +
                            "\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "%\\centering\n" +
                            "\\includegraphics{DigitalGrid_LatexFile_DNAData_"+MainFrame.ProjName+"_Barcode.png}\n" +
                            "%\\caption{Barcode Identifier}\n" +
                            "\\label{barcode}\n" +
                            "\\end{figure}\n" +
                            "\n" +
                            "\\begin{center}\n" +
                            "{\\bf DNA Pen Data Sheet-Digital Grid}\n" +
                            "\\end{center}\n" +
                            "\\[\n" +
                            "\\begin{array}{|c|c|}\n" +
                            "\\hline\n" +
                            " \\mbox{\\bf Description} & \\mbox{\\bf Value} \\\\\\hline\\hline\n" +
                            "\\mbox{Project Name} &"+MainFrame.ProjName+" \\\\\n" +
                            "\\mbox{Total number of Tiles} &"+total+"  \\\\ \n" +
                            "\\mbox{Number of Half-Tiles} &"+half+"  \\\\\n" +
                            "\\mbox{Number of Full-Tiles} &"+full+" \\\\\n" +
                            "\\mbox{Dimension of Molecular Canvas (Brick-Wall)} &69 \\times 345 \\mbox{nms}^2 \\\\\n" +
                            "\\hline\n" +
                            "\\end{array}\n" +
                            "\\]\n" +
                            "\\end{landscape}\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "\\centering\n" +
                            "\\includegraphics[scale=0.51]{DigitalGrid_LatexFile_DNAData_"+MainFrame.ProjName+"_DrawImage.png}\n" +
                            "%\\caption{Draw Image Identifier}\n" +
                            "\\label{digitalgrid}\n" +
                            "\\end{figure}\n" +
                            "\\end{landscape}\n" +
                            /*"\\newpage\n" +
                            "\\begin{landscape}\n" +
                            "\\begin{figure}\n" +
                            "%\\centering\n" +
                        //    "\\includegraphics[scale=0.9]{TileView.png}\n" +
                            "%\\caption{Barcode Identifier}\n" +
                            "\\label{tileview}\n" +
                            "\\end{figure}\n" +
                            "\\end{landscape}\n" +*/
                            "{\\small\n" +
                            "\\begin{landscape}\n" +
                            "\n" +
                            "    \\centering\n" +
                            "   \\begin{longtable}{|l|l|l|l|}\n" +
                            " %\\caption{DNA Data}\\\\\n" +
                            "\\hline \\multicolumn{4}{l}{\\textit{\\bf Continued on next page, generate by DNA-Cloud, http://www.guptalab.org/dnapen}} \\\\\n" +
                            "\\endfoot\n" +
                            "\\hline\n" +
                            "\\endlastfoot\n" +
                            "\\hline\n" +
                            "   \\mbox{\\bf Seq ID} &\\mbox{\\bf X-Coordinate} & \\mbox{\\bf Y-Coordinate} & \\mbox{\\bf DNA  Sequence Generated} \\\\\\hline\\hline\n" +
                            digitalSequence()+
                            " \\hline\n" +
                            "    \\end{longtable}\n" +
                            "{\\bf Generated by DNA Pen, http://www.guptalab.org/dnapen}\n" +
                            "\\end{landscape}\n" +
                            "}\n" +
                            "\\end{document}");
                    bw.close();
                    JOptionPane.showMessageDialog(null, "LateX file successfully generated.",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        else {
            JOptionPane.showMessageDialog(null, "Please select one of the Molecular Canvases to avail this option",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    public String freeSequence(){
        String retValue="";
        for (int i=0;i<SaveDrawDataActionListener.xyDrawdataListFree.size();i++){
            retValue=retValue+(i+1)+" &  "+SaveDrawDataActionListener.xyDrawdataListFree.get(i).getxCoordinate()+"& "+
                    SaveDrawDataActionListener.xyDrawdataListFree.get(i).getxCoordinate()
                    +"& "+SaveDrawDataActionListener.xyDrawdataListFree.get(i).domainSeqOne+
                    SaveDrawDataActionListener.xyDrawdataListFree.get(i).domainSeqTwo+
                    SaveDrawDataActionListener.xyDrawdataListFree.get(i).domainSeqThree+
                    SaveDrawDataActionListener.xyDrawdataListFree.get(i).domainSeqFour+" \\\\ \n";
        }
        return retValue;
    }
    public String digitalSequence(){
        String retValue="";
        for (int i=0;i<SaveDrawDataActionListener.xyDrawdataListDigital.size();i++){
            retValue=retValue+(i+1)+" &  "+SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getxCoordinate()+"& "+
                    SaveDrawDataActionListener.xyDrawdataListDigital.get(i).getxCoordinate()
                    +"& "+SaveDrawDataActionListener.xyDrawdataListDigital.get(i).domainSeqOne+
                    SaveDrawDataActionListener.xyDrawdataListDigital.get(i).domainSeqTwo+
                    SaveDrawDataActionListener.xyDrawdataListDigital.get(i).domainSeqThree+
                    SaveDrawDataActionListener.xyDrawdataListDigital.get(i).domainSeqFour+" \\\\ \n";
        }
        return retValue;
    }

}
