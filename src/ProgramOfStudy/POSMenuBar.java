package ProgramOfStudy;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class POSMenuBar extends JMenuBar
{
	/**
	 * Menu bar for ProgramOfStudy application
	 */
	private static final long serialVersionUID = 1L;
	public static JMenuItem newMI;
	public static JMenuItem openMI;
	public static JMenuItem saveMI;
	public static JMenuItem saveAsMI;
	public static JMenuItem exitMI;
	
	POSMenuBar()
	{
		JMenu menuFile;
		
		//Build the File menu.
	    menuFile = new JMenu("File");
	    this.add(menuFile);
	    
	    //add new menu item to File menu
	    newMI = new JMenuItem("New POS");
	    menuFile.add(newMI);
	    
	    //add open menu item to File menu
	    openMI = new JMenuItem("Open");
	    menuFile.add(openMI);
	    
	    //add new menu item to File menu
	    saveMI = new JMenuItem("Save");
	    menuFile.add(saveMI);
	    
	    //add open menu item to File menu
	    saveAsMI = new JMenuItem("Save As");
	    menuFile.add(saveAsMI);
	    
	    //add open menu item to File menu
	    exitMI = new JMenuItem("Exit");
	    menuFile.add(exitMI);
	}
}
