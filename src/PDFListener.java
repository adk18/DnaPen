import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: shikhar
 * Date: 5/29/13
 * Time: 10:28 AM
 */
public class PDFListener implements ActionListener {
    private JFileChooser jFileChooser;

    @Override
    public void actionPerformed(ActionEvent e){

        if(MainFrame.currentWindow==1){
            JOptionPane.showMessageDialog(null, "No Sequence drawn. Choose a Grid to draw !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        if(MainFrame.newBrickCreated){

            File imageFile=new File("sample456.png");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            java.awt.Rectangle screenRectangle = new java.awt.Rectangle(screenSize);
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            try {


                Thread.sleep(5);

            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.print("waiting");

            BufferedImage image = robot.createScreenCapture(screenRectangle);
            image = image.getSubimage(0, 50, 1366,650);
            try {
                ImageIO.write(image, "png",imageFile );
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //jFileChooser = new JFileChooser();
            //int returnVal = jFileChooser.showSaveDialog(null);
            //File file = jFileChooser.getSelectedFile();
            if(MainFrame.newProjectCreated==true){
                long timestamp;
                java.util.Date date =new java.util.Date();
                timestamp=date.getTime();
                System.out.println(new Timestamp(timestamp));
                String stringTime=String.valueOf(timestamp);
                System.out.println(stringTime);
                Document document = new Document(new Rectangle(PageSize.A4));
                PdfWriter writer = null;
                BufferedWriter bufferedWriter=null;
                try {
                    if(MainFrame.currentWindow==2)
                        writer = PdfWriter.getInstance(document, new FileOutputStream(MainFrame.ProjPath+"/"+"FreeGridData_"+MainFrame.ProjName+".pdf"));

                    else if(MainFrame.currentWindow==3)
                        writer = PdfWriter.getInstance(document, new FileOutputStream(MainFrame.ProjPath+"/"+"DigitalGridData_"+MainFrame.ProjName+".pdf"));

                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                document.open();
                try {
                    document.add(new Paragraph("DNA Pen-"+MainFrame.ProjName+" Draw Data"));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                Chunk chunk0=new Chunk(" ");
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(new Paragraph("PDF Producer: iText 5.4.1 2000-2012 1T3XT BVBA (AGPL-Version)"));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(new Paragraph("Barcode generated at: "+(new Timestamp(timestamp))));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Barcode128 code128 = new Barcode128();
                code128.setGenerateChecksum(true);
                code128.setCode(stringTime+"Data_"+MainFrame.ProjName);

                try {
                    document.add(code128.createImageWithBarcode(writer.getDirectContent(), null, null));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                Chunk chunk1=new Chunk("Draw Image \n ");
                try {
                    document.add(chunk1);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                Image image1 = null;
                try {
                    image1 = Image.getInstance("sample456.png");
                    image1.scaleToFit(500,500);
                } catch (BadElementException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(image1);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(new Paragraph("Brick Data:"));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                PdfPTable table=new PdfPTable(2);
                table.addCell("Brick Height");
                table.addCell("Brick Width");
                table.addCell(String.valueOf(MainFrame.dnaBrick.brickHeight));
                table.addCell(String.valueOf(MainFrame.dnaBrick.brickWidth));
                try {
                    document.add(table);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(new Paragraph("DNA Brick Sequences:"));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    document.add(new Paragraph(MainFrame.dnaBrick.toString()));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(chunk0);
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                try {
                    document.add(new Paragraph("Generated using DNA Pen (http://guptalab.org/dnapen)"));
                } catch (DocumentException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                document.close();

                System.out.println("Document Generated...!!!!!!");
                JOptionPane.showMessageDialog(null, "File Saved Successfully !",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No Brick has been created. Use 'New' to create a Brick first",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
}
