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
import java.util.LinkedList;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class  NewFreeImportImageActionListener extends Canvas implements ActionListener {
	
    int brickHeight = 3; 
    int brickWidth = 7;
    
	public void actionPerformed(ActionEvent e) {
        
		//selecting file for importing
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
	
	// function to process image and render it on canvas
	public void processImage(String path){
		File f = new File(path);
		
		int prev_sum = 0;
		int curr_sum = 0;
		BufferedImage final_img;
		
		
		try {
			BufferedImage img = ImageIO.read(f);
			
			
			int xx = img.getWidth();
			int yy = img.getHeight();
			int ysize= (int) (yy/brickHeight) + 1;
			int xsize= (int) (xx/brickWidth) + 1;
			
			int[][] t = new int[xx][yy];
			
			for(int i =0; i<t.length; i++){
				for(int j=0; j<t[i].length; j++){
					t[i][j] = 0;
				}
			}
			
			// create a binary image
			for(int i = 0; i <xx; i++){
				for(int j = 0; j<yy; j++){
					Color c = new Color(img.getRGB(i, j));				
					if(c.getBlue() == 255 && c.getRed() == 255 && c.getGreen() == 255)
						continue;
					t[i][j] = 1;
					prev_sum++;
				}
			}
			
			
			MainFrame.image_imported = true;
			
			// get pen stroke coordinates 
			
			ArrayList<Pair> p = this.find_pen_strokes(t);
						
			for(int i =0; i <p.size(); i++){
				Pair temp = p.get(i);
				int x = temp.i;
				int y = temp.j;
				
				FreeGridActionListener.DrawCanvas.graphicsForDrawing = FreeGridActionListener.canvas.getGraphics();  // For drawing on the screen.
				FreeGridActionListener.DrawCanvas.graphicsForDrawing.setColor(Color.black);
				FreeGridActionListener.DrawCanvas.offscreenGraphics = FreeGridActionListener.DrawCanvas.OSC.getGraphics();  // For drawing on the canvas.
				FreeGridActionListener.DrawCanvas.offscreenGraphics.setColor(Color.black);

				
				FreeGridActionListener.DrawCanvas.putMultiFigure(FreeGridActionListener.DrawCanvas.graphicsForDrawing,x, y, x, y);
				FreeGridActionListener.DrawCanvas.putMultiFigure(FreeGridActionListener.DrawCanvas.offscreenGraphics,x, y, x, y);
				FreeGridActionListener.DrawCanvas.myDomChange(x, y);
			
				FreeGridActionListener.DrawCanvas.graphicsForDrawing.dispose();
				FreeGridActionListener.DrawCanvas.offscreenGraphics.dispose();
				FreeGridActionListener.DrawCanvas.graphicsForDrawing = null;
				FreeGridActionListener.DrawCanvas.offscreenGraphics = null;

				
			}
			
			
			
			
			System.out.println("Final values of x y bricklist are");
			
			
			System.out.println(FreeGridActionListener.xCoordinateBrickList);
			System.out.println(FreeGridActionListener.yCoordinateBrickList);
			
			System.out.println("******************************************");
			boolean connected = this.find_connecteness(t);
			if(connected){
				System.out.println("Image is connected");
			}else{
				
				String message = "Image is not connected. Please draw a connected image before saving.";
				String title = "Warning";
				JOptionPane.showMessageDialog(null, message , title , JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Image is not connected");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// methods for drawing the image in pen stroke manner
	
	private ArrayList<Pair> find_pen_strokes(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        
        ArrayList<Pair> ret = new ArrayList<>();
        boolean[][] v = new boolean[n][m];
        for(int i=0;i<n;i++) {
            int j = 0;
            for(j=0;j<m;j++) {
                if(arr[i][j] == 1 && !v[i][j]) {
                    ps_dfs(i, j, arr, v, ret);
                }
            }
        }
        return ret;    
    }
	
    private void ps_dfs(int i, int j, int[][] arr, boolean[][] v, ArrayList<Pair> ret) {
   // 	System.out.println(i+" "+j);
    	int n = arr.length;
    	int m = arr[0].length;
        if(i < 0 || j < 0 || i >= arr.length || j>= arr[0].length)
            return;
        if(v[i][j] || arr[i][j] != 1)
            return;
        ret.add(new Pair(i, j));
        v[i][j] = true;
        ps_dfs(i-1, j, arr, v, ret);
        ps_dfs(i-1, j-1, arr, v, ret);
        ps_dfs(i-1, j+1, arr, v, ret);
        ps_dfs(i, j-1, arr, v, ret);
        ps_dfs(i, j+1, arr, v, ret);
        ps_dfs(i+1, j, arr, v, ret);
        ps_dfs(i+1, j-1, arr, v, ret);
        ps_dfs(i+1, j+1, arr, v, ret);
        /*
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        v[i][j] = true;
        while(!q.isEmpty()){
        	Pair x = q.poll();
        	int i1 = x.i;
        	int j1 = x.j;
        	System.out.println(i1+" "+j1);
        	ret.add(x);
        	if(function(i1-1,j1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1));
        	if(function(i1-1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1-1));
        	if(function(i1-1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1+1));
        	if(function(i1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1,j1-1));
        	if(function(i1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1,j1+1));
        	if(function(i1+1,j1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1));
        	if(function(i1+1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1-1));
        	if(function(i1+1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1+1));
        }
        */
    }
    
    
    private static boolean function(int i, int j, int[][] arr, int n, int m, boolean[][] v){
    	if(i>=0 && j>=0 && i<n && j<m && arr[i][j] == 1 && !v[i][j]){
    		v[i][j] = true;
    		return true;
    	}
    	return false;
    }
    
    
    private static class Pair{
        int i, j;
        public Pair(int a, int b) {
            i = a;
            j = b;
        }
    }
    
    
    //methods to check connectedness
    
	private boolean find_connecteness(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        boolean[][] v = new boolean[n][m];
        for(int i=0;i<n;i++) {
            int j = 0;
            for(j=0;j<m;j++) {
                if(arr[i][j] == 1) {
                    c_dfs(i, j, arr, v);
                    break;
                }
            }
            if(j != m)
                break;
        }
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(arr[i][j] == 1 && !v[i][j])
                    return false;
        return true;
    }
	
    private void c_dfs(int i, int j, int[][] arr, boolean[][] v) {
    	int n = arr.length;
    	int m = arr[0].length;
//        if(i < 0 || j < 0 || i >= arr.length || j>= arr[0].length)
//            return;
//        if(v[i][j] || arr[i][j] != 1)
//            return;
//        v[i][j] = true;
//        c_dfs(i-1, j, arr, v);
//        c_dfs(i-1, j-1, arr, v);
//        c_dfs(i-1, j+1, arr, v);
//        c_dfs(i, j-1, arr, v);
//        c_dfs(i, j+1, arr, v);
//        c_dfs(i+1, j, arr, v);
//        c_dfs(i+1, j-1, arr, v);
//        c_dfs(i+1, j+1, arr, v);
    	Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        v[i][j] = true;
        while(!q.isEmpty()){
        	Pair x = q.poll();
        	int i1 = x.i;
        	int j1 = x.j;
        	System.out.println(i1+" "+j1);
        	if(function(i1-1,j1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1));
        	if(function(i1-1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1-1));
        	if(function(i1-1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1-1,j1+1));
        	if(function(i1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1,j1-1));
        	if(function(i1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1,j1+1));
        	if(function(i1+1,j1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1));
        	if(function(i1+1,j1-1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1-1));
        	if(function(i1+1,j1+1,arr,n,m,v))
        		q.add(new Pair(i1+1,j1+1));
        }
    }

    

  };
 
  
