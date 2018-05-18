package com.cv.parser.util;

import javax.swing.JDialog;

/************************************************************************
 * This Class is considered as Tools 
 * Because we need to get the JDialog in the parent form,
 * And there is no way to get it, so I save the reference as static here..
 * 
 * This JDialogHelper handles is a better structure than creating a faulty JFrame as I used before.
 * There is no constructor for this class, because the tools functions to
 *    make the control of JFrame easier 
 *    
 *  Don't add other function in this class..   Purely only to handle JDialog manipulation  
 *
 * @author Leo Sudarma
 * @Date March 30, 2012
 * @param  JDialog firstJDialog, JDialog secondJDialog, JDialog, thirdJDialog
 *
 */
public class JDialogHelper {
	  public static void setJDialogTree(JDialog firstDialog, JDialog secondDialog, JDialog thirdDialog) {
		  firstJDialog = firstDialog;
		  secondJDialog = secondDialog;
		  thirdJDialog = thirdDialog;
	  }

	  /**
	   * get the first JDialog
	   * @return
	   */
	  public static JDialog getFirstJDialog() {
		  return firstJDialog;
	  }
	  
	  public static JDialog getSecondJDialog() {
		  return secondJDialog;
	  }
	  
	  public static JDialog getThirdJDialog() {
		  return thirdJDialog;
	  }
	  
	  
	  private static JDialog firstJDialog;
	  private static JDialog secondJDialog; 
	  private static JDialog thirdJDialog;
	
}
