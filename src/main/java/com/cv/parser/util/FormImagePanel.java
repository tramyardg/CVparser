package com.cv.parser.util;

import javax.swing.JPanel;

import java.sql.Blob;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.ImageInputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FormImagePanel extends JPanel {
	private BufferedImage image;

	 public FormImagePanel()	 {
		 try 	  {
			 image = ImageIO.read(new File("image\\bg.jpg"));
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public FormImagePanel(String fileName )	 {
		 try 	  {
			 if (fileName.equals("")) {
				 this.paint(null);
			 } else {
				 image = ImageIO.read(new File(fileName));
			 }
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public FormImagePanel(byte[] imageBytes )	 {
		 try 	  {
			 ImageInputStream iis = javax.imageio.ImageIO.createImageInputStream(imageBytes);
//			 iis.read(imageBytes);
			 image = ImageIO.read(iis);
			 
			 
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public FormImagePanel(File fileImage)	 {
		 try 	  {
			 if (fileImage!= null) {
			 
				 image = ImageIO.read(fileImage);
			 }
		 }	catch (IOException ex) 	  {
			 System.out.println(ex.getMessage());
		 }

	 }
	 
	 public void setImageFile(byte[] imageBytes) {
		 try 	  {
			 
			 System.out.println("Image is "  + imageBytes + " --  "  + imageBytes.length);
			 
			 ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
			 System.out.println("Image is "  + iis);

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
		 
	//	 g.drawImage(image, 0,0, screenSize.width, screenSize.height, this);
		 g.drawImage(image, 0, 0, null);
		
		 g.dispose();
	 }
	
	
	
}
