/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class SaveDrawDataActionListener implements ActionListener {

    static boolean alreadySavedDetailedDNA=false;
    static ArrayList<Domains> xyDomainListDigital= GenerateDNAFile.xyDomainList;
    static ArrayList<XYCoordinates> xyCoordinateList = DigitalGridActionListener.xyCoordinatesBrickList;
    static ArrayList<Domains> xyDrawdataListDigital = new ArrayList<Domains>();

    static ArrayList<Integer> xCoordinateList = FreeGridActionListener.xCoordinateBrickList;
    static ArrayList<Integer> yCoordinateList = FreeGridActionListener.yCoordinateBrickList;
    
    static ArrayList<Domains> xyDrawdataListFree = new ArrayList<Domains>();

    int i;
    static String sequence1;
    static String sequence2;

    static String d1;
    static String d2;
    static String d3;
    static String d4;

    BrickData brickdata=new BrickData();
    @Override
    public void actionPerformed(ActionEvent e) {
    	debug(xCoordinateList);
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
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        GenerateFreegridDNA g=new GenerateFreegridDNA();
                        g.savetoFile();
                        writeHeadersCanvasFree(bufferedWriter, xCoordinateList.size());
                    }
                    else if(MainFrame.currentWindow==3) {
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        writeHeadersCanvasDigital(bufferedWriter, xCoordinateList.size());
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
                        GenerateFreegridDNA g=new GenerateFreegridDNA();
                        g.savetoFile();
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        writeHeadersCanvasFree(bufferedWriter, xyCoordinatesList.size());
                    }
                    else if(MainFrame.currentWindow==3) {
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        writeHeadersCanvasDigital(bufferedWriter, xyCoordinatesList.size());
                    }


                    bufferedWriter.write("\n\nDraw Sequence Coordinates\n\n");


                    bufferedWriter.write("\n\nDNA Brick Sequence\n\n");
                    bufferedWriter.write(MainFrame.dnaBrick.toString());

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

    public void writeHeadersCanvasDigital(BufferedWriter bufferedWriter, int listSize) {
        int k;
        xyDrawdataListDigital.clear();
        try {
            double brickHeight = MainFrame.dnaBrick.getBrickHeight();
            double brickWidth = MainFrame.dnaBrick.getBrickWidth();
            int x,y;
            for (k = 0; k < xyCoordinateList.size(); k++) {
                x=xyCoordinateList.get(k).getxCoordinate();
                y=xyCoordinateList.get(k).getyCoordinate();
                for(int j=0;j< xyDomainListDigital.size();j++){
                    if((xyDomainListDigital.get(j).getxCoordinate()==x)&&(xyDomainListDigital.get(j).getyCoordinate()==y))
                        xyDrawdataListDigital.add(new Domains(x,y,xyDomainListDigital.get(j).getDomainOne(),xyDomainListDigital.get(j).getDomainTwo(),xyDomainListDigital.get(j).getDomainThree(),xyDomainListDigital.get(j).getDomainFour()));
                }

            }
            int size=xyDrawdataListDigital.size();
            for(int p=0;p<size;p++) {
                x=xyDrawdataListDigital.get(p).getxCoordinate();
                y=xyDrawdataListDigital.get(p).getyCoordinate();

                d1=new String(xyDrawdataListDigital.get(p).getDomainOne());
                d2=new String(xyDrawdataListDigital.get(p).getDomainTwo());
                d3=new String(xyDrawdataListDigital.get(p).getDomainThree());
                d4=new String(xyDrawdataListDigital.get(p).getDomainFour());
                int foundright=0;
                int foundleft=0;
                int foundbottomleft=0;
                int foundbottom=0;
                int foundtop=0;
                int foundtopleft=0;
                int foundbottomright=0;
                int foundtopright=0;

                int changeT=0;
                if (y%2==0){

                    if(true){


                        for(int q=p+1;q<xyDrawdataListDigital.size();q++) {

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x+1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y)){
                                foundright=1;
                                sequence1=new String(xyDrawdataListDigital.get(q).getDomainOne());
                                sequence2=new String(xyDrawdataListDigital.get(q).getDomainFour());

                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x-1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y)){
                                foundleft=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x-1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y+1)){
                                foundbottomleft=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y+1)&&(xyDrawdataListDigital.get(q).getDomainThree().equals("")==false)){
                                foundbottom=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y-1)&&(xyDrawdataListDigital.get(q).getDomainOne().equals("")==false)){
                                foundtop=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x-1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y-1)){
                                foundtopleft=1;
                            }
                        }


                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottomleft==0&&y>0) {
                                d1=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtopleft==0&&y>0)  {
                                d4=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundbottom==0&&y<14)  {
                                d2=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtop==0&&y<14){
                                d3=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(changeT==1){
                                xyDrawdataListDigital.add(new Domains(x,y,d1,d2,d3,d4));
                                xyDrawdataListDigital.remove(p);
                                p--;
                                size--;
                            }
                        }
                        if(foundtop==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y,negateSeqRev(d3),negateSeqRev(sequence2),"",""));
                        }

                        if(foundbottom==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y,"","",negateSeq(sequence2),negateSeq(d3)));
                        }

                    }
                }

                else if (y%2==1){

                    if(true){


                        for(int q=p+1;q<xyDrawdataListDigital.size();q++) {

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x+1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y)){
                                foundright=1;
                                sequence1=new String(xyDrawdataListDigital.get(q).getDomainOne());
                                sequence2=new String(xyDrawdataListDigital.get(q).getDomainFour());

                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x-1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y)){
                                foundleft=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x+1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y+1)){
                                foundbottomright=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y+1)&&(xyDrawdataListDigital.get(q).getDomainThree().equals("")==false)){
                                foundbottom=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y-1)&&(xyDrawdataListDigital.get(q).getDomainOne().equals("")==false)){
                                foundtop=1;
                            }

                            if((xyDrawdataListDigital.get(q).getxCoordinate()==x+1)&&(xyDrawdataListDigital.get(q).getyCoordinate()==y-1)){
                                foundtopright=1;
                            }
                        }

                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottom==0) {
                                d1=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundbottomright==0&&foundright==0)  {
                                d2=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtopright==0)  {
                                d3=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtop==0){
                                d4=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                        }
                        if(changeT==1){
                            xyDrawdataListDigital.add(new Domains(x,y,d1,d2,d3,d4));
                            xyDrawdataListDigital.remove(p);
                            p--;
                            size--;
                        }

                        if(foundtopright==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y,negateSeqRev(d3),negateSeqRev(sequence2),"",""));
                        }

                        if(foundbottomright==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y,"","",negateSeq(sequence2),negateSeq(d3)));
                        }

                    }
                }

            }

            for(i=0;i<xyDrawdataListDigital.size();i++){
                bufferedWriter.write("X Coordinate: " + xyDrawdataListDigital.get(i).getxCoordinate());
                bufferedWriter.write(",");
                bufferedWriter.write("Y Coordinate: " + xyDrawdataListDigital.get(i).getyCoordinate() + "");
                bufferedWriter.write(",");
                bufferedWriter.write("d1 " + xyDrawdataListDigital.get(i).getDomainOne() + "");
                bufferedWriter.write("d2 " + xyDrawdataListDigital.get(i).getDomainTwo() + "");
                bufferedWriter.write("d3 " + xyDrawdataListDigital.get(i).getDomainThree() + "");
                bufferedWriter.write("d4 " + xyDrawdataListDigital.get(i).getDomainFour() + "");
                bufferedWriter.write("\n");

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void writeHeadersCanvasFree(BufferedWriter bufferedWriter, int listSize) {
        ArrayList<Domains> xyDomainListFree= GenerateFreegridDNA.xyDomainList;
        int k;
        xyDrawdataListFree.clear();
        try {
            double brickHeight = EditDimensionActionListener.BrickWallHeight;
            double brickWidth = EditDimensionActionListener.BrickwallWidth;

            int x,y,found,xprint,yprint,nextx = 0,nexty;
            boolean xerror,yerror,xyerror;
            boolean x1error,y1error,x1y1error,x0error,y0error;
            int size=xCoordinateList.size();
            System.out.println("The selected coordinates");
            for(int p=0;p<size;p++) {
                xprint=xCoordinateList.get(p);
                yprint=yCoordinateList.get(p);
                System.out.println(xprint+"\t"+yprint);

            }
            System.out.println("Calling Join Function");
            
            /*
            if(!MainFrame.getImportImageTrueness()){
            	joinFunc();
            	System.out.println("Join call Finished");
            }
			*/
            
            joinFunc();
            
            for (k = 0; k < xCoordinateList.size(); k++) {
                x=xCoordinateList.get(k);
                y=yCoordinateList.get(k);
                for(int j=0;j< xyDomainListFree.size();j++){
                    if((xyDomainListFree.get(j).getxCoordinate()==x)&&(xyDomainListFree.get(j).getyCoordinate()==y))
                        xyDrawdataListFree.add(new Domains(x,y,xyDomainListFree.get(j).getDomainOne(),xyDomainListFree.get(j).getDomainTwo(),xyDomainListFree.get(j).getDomainThree(),xyDomainListFree.get(j).getDomainFour()));
                }

            }
            for(int p=0;p<size;p++) {
                x=xyDrawdataListFree.get(p).getxCoordinate();
                y=xyDrawdataListFree.get(p).getyCoordinate();

                d1=new String(xyDrawdataListFree.get(p).getDomainOne());
                d2=new String(xyDrawdataListFree.get(p).getDomainTwo());
                d3=new String(xyDrawdataListFree.get(p).getDomainThree());
                d4=new String(xyDrawdataListFree.get(p).getDomainFour());
                int foundright=0;
                int foundleft=0;
                int foundbottomleft=0;
                int foundbottom=0;
                int foundtop=0;
                int foundtopleft=0;
                int foundbottomright=0;
                int foundtopright=0;

                int changeT=0;
                if (y%2==0){

                    if(true){


                        for(int q=p+1;q<xyDrawdataListFree.size();q++) {

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x+1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y)){
                                foundright=1;
                                sequence1=new String(xyDrawdataListFree.get(q).getDomainOne());
                                sequence2=new String(xyDrawdataListFree.get(q).getDomainFour());

                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x-1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y)){
                                foundleft=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x-1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y+1)){
                                foundbottomleft=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x)&&(xyDrawdataListFree.get(q).getyCoordinate()==y+1)&&(xyDrawdataListFree.get(q).getDomainThree().equals("")==false)){
                                foundbottom=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x)&&(xyDrawdataListFree.get(q).getyCoordinate()==y-1)&&(xyDrawdataListFree.get(q).getDomainOne().equals("")==false)){
                                foundtop=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x-1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y-1)){
                                foundtopleft=1;
                            }
                        }


                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottomleft==0&&y>0) {
                                d1=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtopleft==0&&y>0)  {
                                d4=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundbottom==0&&y<brickWidth-1)  {
                                d2=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtop==0&&y<brickWidth-1){
                                d3=new String("TTTTTTTTTTT");
                                changeT=1;
                            }

                            if(changeT==1){
                                xyDrawdataListFree.add(new Domains(x,y,d1,d2,d3,d4));
                                xyDrawdataListFree.remove(p);
                                p--;
                                size--;
                            }
                        }
                        if(foundtop==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y,negateSeqRev(d3),negateSeqRev(sequence2),"",""));
                        }

                        if(foundbottom==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y,"","",negateSeq(sequence2),negateSeq(d3)));
                        }

                    }
                }

                else if (y%2==1){

                    if(true){


                        for(int q=p+1;q<xyDrawdataListFree.size();q++) {

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x+1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y)){
                                foundright=1;
                                sequence1=new String(xyDrawdataListFree.get(q).getDomainOne());
                                sequence2=new String(xyDrawdataListFree.get(q).getDomainFour());

                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x-1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y)){
                                foundleft=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x+1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y+1)){
                                foundbottomright=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x)&&(xyDrawdataListFree.get(q).getyCoordinate()==y+1)&&(xyDrawdataListFree.get(q).getDomainThree().equals("")==false)){
                                foundbottom=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x)&&(xyDrawdataListFree.get(q).getyCoordinate()==y-1)&&(xyDrawdataListFree.get(q).getDomainOne().equals("")==false)){
                                foundtop=1;
                            }

                            if((xyDrawdataListFree.get(q).getxCoordinate()==x+1)&&(xyDrawdataListFree.get(q).getyCoordinate()==y-1)){
                                foundtopright=1;
                            }
                        }

                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottom==0) {
                                d1=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundbottomright==0&&foundright==0)  {
                                d2=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtopright==0)  {
                                d3=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtop==0){
                                d4=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                        }
                        if(changeT==1){
                            xyDrawdataListFree.add(new Domains(x,y,d1,d2,d3,d4));
                            xyDrawdataListFree.remove(p);
                            p--;
                            size--;
                        }

                        if(foundtopright==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y,negateSeqRev(d3),negateSeqRev(sequence2),"",""));
                        }

                        if(foundbottomright==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y,"","",negateSeq(sequence2),negateSeq(d3)));
                        }

                    }
                }

            }


            for(i=0;i<xyDrawdataListFree.size();i++){
                bufferedWriter.write("X Coordinate: " + xyDrawdataListFree.get(i).getxCoordinate());
                bufferedWriter.write(",");
                bufferedWriter.write("Y Coordinate: " + xyDrawdataListFree.get(i).getyCoordinate() + "");
                bufferedWriter.write(",");
                bufferedWriter.write("d1 " + xyDrawdataListFree.get(i).getDomainOne() + "");
                bufferedWriter.write("d2 " + xyDrawdataListFree.get(i).getDomainTwo() + "");
                bufferedWriter.write("d3 " + xyDrawdataListFree.get(i).getDomainThree() + "");
                bufferedWriter.write("d4 " + xyDrawdataListFree.get(i).getDomainFour() + "");
                bufferedWriter.write("\n");

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public String negateSeqRev(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

        for (int i = stringLength-1; i >=0; i--) {
            if (domainSeq.charAt(i) == 'A') {
                negateSeq += "T";
            } else if (domainSeq.charAt(i) == 'T') {
                negateSeq += "A";
            } else if (domainSeq.charAt(i) == 'G') {
                negateSeq += "C";
            } else if (domainSeq.charAt(i) == 'C') {
                negateSeq += "G";
            }
        }
        return (negateSeq);
    }
    public String negateSeq(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

        for (int i = 0; i <stringLength; i++) {
            if (domainSeq.charAt(i) == 'A') {
                negateSeq += "T";
            } else if (domainSeq.charAt(i) == 'T') {
                negateSeq += "A";
            } else if (domainSeq.charAt(i) == 'G') {
                negateSeq += "C";
            } else if (domainSeq.charAt(i) == 'C') {
                negateSeq += "G";
            }
        }
        return (negateSeq);
    }

    public static void joinCordinatesxerror(int currx, int curry, int nextx, int nexty){
        int xfiller;
        if(nextx>currx){
            System.out.println("next x is greater");
            xfiller=nextx-currx-1;
            for(int i=0;i<xfiller;i++){
                xCoordinateList.add(currx+i+1);
                yCoordinateList.add(curry);
            }
        }
        else if(nextx<currx){
            System.out.println("next x is lesser");
            xfiller=currx-nextx-1;
            for(int i=0;i<xfiller;i++){
                xCoordinateList.add(currx-i-1);
                yCoordinateList.add(curry);
            }
        }
    }
    public static void joinCordinatesyerror(int currx, int curry, int nextx, int nexty){
        int yfiller;
        if(nexty>curry){
            System.out.println("next y is greater");
            yfiller=nexty-curry-1;
            for(int i=0;i<yfiller;i++){
                xCoordinateList.add(currx);
                yCoordinateList.add(curry+i+1);
                System.out.println("Greater y is filled");
            }
        }
        else if(nexty<curry){
        //    System.out.println("next y is lesser");
            yfiller=curry-nexty-1;
            for(int i=0;i<yfiller;i++){
                xCoordinateList.add(currx);
                yCoordinateList.add(curry-i-1);
          //      System.out.println("Lesser y is filled");
            }
        }
    }
    public static void joinCordinatesxyerror(int currx, int curry, int nextx, int nexty){
        int diffx=Math.abs(currx-nextx);
        int diffy=Math.abs(curry-nexty);
        System.out.println("Error in x and y both");
        if(currx>nextx&&curry>nexty){
            while (currx!=nextx&&curry!=nexty){
                if(diffx>0&&diffy>0){
                    xCoordinateList.add(currx-1);
                    yCoordinateList.add(curry-1);
                    currx--;
                    curry--;
                    diffx--;
                    diffy--;
                }
                if(diffx>diffy&&diffx>0){
                    xCoordinateList.add(currx-1);
                    yCoordinateList.add(curry);
                    currx--;
                    diffx--;
                }
                else if(diffx<diffy&&diffy>0) {
                    xCoordinateList.add(currx);
                    yCoordinateList.add(curry-1);
                    curry--;
                    diffy--;
                }
            }
        }
        else if(currx>nextx&&curry<nexty){
            while (currx!=nextx&&curry!=nexty){
                if(diffx>0&&diffy>0){
                    xCoordinateList.add(currx-1);
                    yCoordinateList.add(curry+1);
                    currx--;
                    curry++;
                    diffx--;
                    diffy--;
                }
                if(diffx>diffy&&diffx>0){
                    xCoordinateList.add(currx-1);
                    yCoordinateList.add(curry);
                    currx--;
                    diffx--;
                }
                else if(diffx<diffy&&diffy>0) {
                    xCoordinateList.add(currx);
                    yCoordinateList.add(curry+1);
                    curry++;
                    diffy--;
                }
            }
        }
        else if(currx<nextx&&curry>nexty){
            while (currx!=nextx&&curry!=nexty){
                if(diffx>0&&diffy>0){
                    xCoordinateList.add(currx+1);
                    yCoordinateList.add(curry-1);
                    currx++;
                    curry--;
                    diffx--;
                    diffy--;
                }
                if(diffx>diffy&&diffx>0){
                    xCoordinateList.add(currx+1);
                    yCoordinateList.add(curry);
                    currx++;
                    diffx--;
                }
                else if(diffx<diffy&&diffy>0) {
                    xCoordinateList.add(currx);
                    yCoordinateList.add(curry-1);
                    curry--;
                    diffy--;
                }
            }
        }
        else if(currx<nextx&&curry<nexty){
            while (currx!=nextx&&curry!=nexty){
                if(diffx>0&&diffy>0){
                    xCoordinateList.add(currx+1);
                    yCoordinateList.add(curry+1);
                    currx++;
                    curry++;
                    diffx--;
                    diffy--;
                }
                if(diffx>diffy&&diffx>0){
                    xCoordinateList.add(currx+1);
                    yCoordinateList.add(curry);
                    currx++;
                    diffx--;
                }
                else if(diffx<diffy&&diffy>0) {
                    xCoordinateList.add(currx);
                    yCoordinateList.add(curry+1);
                    curry++;
                    diffy--;
                }
            }
        }

    }
    public static void joinCordinatesx1y1error(int currx, int curry, int nextx, int nexty){
        System.out.println("1 Error in x and y both");
        if(currx<nextx){
            xCoordinateList.add(currx+1);
            yCoordinateList.add(curry);
            System.out.println("1 Error in x and y corrected");
        }
        else if(currx>nextx){
            xCoordinateList.add(currx-1);
            yCoordinateList.add(curry);
            System.out.println("1 Error in x and y corrected");
        }
    }

    public static void joinFunc(){
        int xprint,yprint,nextx,nexty;
        boolean xerror,yerror,xyerror;
        boolean x1error,y1error,x1y1error,x0error,y0error;
        int size=xCoordinateList.size();
        for(int p=0;p<size;p++) {
            xprint=xCoordinateList.get(p);
            yprint=yCoordinateList.get(p);
         //  System.out.println(xCoordinateList);
           // System.out.println(yCoordinateList);
            x1error=false;
            y1error=false;
            x0error=false;
            y0error=false;
            x1y1error=false;
            xerror=false;
            yerror=false;
            xyerror=false;
            if(p!=size-1){
                nextx=xCoordinateList.get(p+1);
                nexty=yCoordinateList.get(p+1);
                if(Math.abs(nextx-xprint)==0) {
                    x0error=true;
                }
                else if(Math.abs(nextx-xprint)==1) {
                    x1error=true;
                }
                if(Math.abs(nexty-yprint)==0) {
                    y0error=true;
                }
                else if(Math.abs(nexty-yprint)==1) {
                    y1error=true;
                }
                if(x0error==false&&x1error==false){
                    xerror=true;
                }
                if(y0error==false&&y1error==false){
                    yerror=true;
                }

                if(x1error==true&&y1error==true){
                    x1y1error=true;
                    x1error=false;
                    y1error=false;
                }

                if(xerror==true&&yerror==true){
                    xyerror=true;
                    xerror=false;
                    yerror=false;
                }
                if(xerror==true){
                    joinCordinatesxerror(xprint,yprint,nextx,nexty);
                }
                if(yerror==true){
                    joinCordinatesyerror(xprint,yprint,nextx,nexty);
                }
                if(xyerror==true){
                    joinCordinatesxyerror(xprint,yprint,nextx,nexty);
                }
                if(x1y1error==true){
                    joinCordinatesx1y1error(xprint,yprint,nextx,nexty);
                }
            }

        }
    }
    private void debug(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
}
