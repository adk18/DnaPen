/* Author: Shubham Annadate and Dhaval Andharia
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 * */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


public class  NewImportImagelistener implements ActionListener {
	
		
	public void actionPerformed(ActionEvent e) {
		
		// selecting file for importing 
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            processImage(path);
        }
        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No File Select");
        }
      }
	
	// function for processing the image and plotting it on the grid
	public void processImage(String path){
		File f = new File(path);
		try {
			
			// scale the image
			BufferedImage img = ImageIO.read(f);
			BufferedImage img1 = new BufferedImage(1050, 700, img.getType());
			Graphics2D g = img1.createGraphics();
			g.drawImage(img, 0, 0, 1050, 700, null);
			g.dispose();
			
			// get image dimensions
			int xx = img1.getWidth();
			int yy = img1.getHeight();
			
	
			// plot image on the grid
			for(int i=0;i<xx;i++){
				for(int j=0;j<yy;j++){
					Color c = new Color(img1.getRGB(i, j));				
					if(c.getBlue() == 255 && c.getRed() == 255 && c.getGreen() == 255)
						continue;
					DigitalGridActionListener.canvas.fillGridCoordinate(i, j);
					DigitalGridActionListener.canvas.repaint();
							
				}
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  };
 
  
