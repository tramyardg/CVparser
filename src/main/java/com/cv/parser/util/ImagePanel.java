package com.cv.parser.util;


import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;


/**
 * ImagePanel only handles image for background 
 * No need to change this class.
 * 
 * For other image operation on the form / on JSP,  use the PanelImage instead..
 * @author Leo Sudarma
 *
 */
public class ImagePanel extends JPanel {
	
	private BufferedImage image;

	 public ImagePanel()	 {
		 try 	  {
			 image = ImageIO.read(new File("image\\bg.jpg"));
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public ImagePanel(String fileName )	 {
		 try 	  {
			 image = ImageIO.read(new File(fileName));
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 
	 public ImagePanel(File fileImage)	 {
		 try 	  {
			 if (fileImage!= null) {
			 
				 image = ImageIO.read(fileImage);
			 }
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public ImagePanel(byte[] imageBytes )	 {
		 try 	  {
			 ImageInputStream iis = javax.imageio.ImageIO.createImageInputStream(imageBytes);
//			 iis.read(imageBytes);
			 image = ImageIO.read(iis);
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public void setImageFile(byte[] imageBytes) {
		 try 	  {
			 ImageInputStream iis = javax.imageio.ImageIO.createImageInputStream(imageBytes);
//			 iis.read(imageBytes);
			 image = ImageIO.read(iis);
			 
			 
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }
	 }
	 
	 public void setImageFile(File imageFile) {
		 try 	  {
			 image = ImageIO.read(imageFile);
	 
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 

	 public void paintComponent(Graphics gr)	 {
		 super.paintComponent( gr);
		 
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //  get the default windows size
		 
		 Graphics g = gr.create();
		 
		 g.drawImage(image, 0,0, screenSize.width, screenSize.height, this);
		
		 g.dispose();
	 }

}
