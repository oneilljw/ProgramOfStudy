package ProgramOfStudy;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class POSController implements ActionListener
{
	private static final String APPNAME = "Program Of Study";
	
	private List<List<Course>> courseList;
	private List<Course> posList;
	private JFrame posFrame;
	private JPanel posContentPane;
	private POSMenuBar menuBar;
	private POSView posView;
	private CourseTableModel courseTM;
	
	//Check if we are on Mac OS X.  This is crucial to loading and using the OSXAdapter class.
    private static boolean MAC_OS_X = (System.getProperty("os.name").toLowerCase().startsWith("mac os x"));
	
	public POSController()
	{
		//If running under MAC OSX, use the system menu bar and set the application title appropriately and
    	//set up our application to respond to the Mac OS X application menu
        if(MAC_OS_X) 
        {          	
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", APPNAME);
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); }
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); }
			catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); }
			
            // Generic registration with the Mac OS X application, attempts to register with the Apple EAWT
            // See OSXAdapter.java to see how this is done without directly referencing any Apple APIs
            try
            {
                // Generate and register the OSXAdapter, passing it a hash of all the methods we wish to
                // use as delegates for various com.apple.eawt.ApplicationListener methods
 //             OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("quit", (Class[])null));
 //             OSXAdapter.setAboutHandler(this,getClass().getDeclaredMethod("about", (Class[])null));
 //             OSXAdapter.setPreferencesHandler(this, getClass().getDeclaredMethod("preferences", (Class[])null));
 //             OSXAdapter.setFileHandler(this, getClass().getDeclaredMethod("loadImageFile", new Class[] { String.class }));
            } 
            catch (Exception e)
            {
                System.err.println("Error while loading the OSXAdapter:");
                e.printStackTrace();
            }
        }
		//initialize class variables
		courseList = new ArrayList<List<Course>>();
		
		//read courses from .dat file for each concentration
		for(Concentration c: Concentration.values())
		{
			try
			{
				List<Course> currList = readConcentrationFromDisk(c);
				courseList.add(currList);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		courseTM = new CourseTableModel(courseList);
		createAndShowView();
		
		posView.concentrationCB.addActionListener(this);	
		
	}
	
	// General quit handler; fed to the OSXAdapter as the method to call when a system quit event occurs
    // A quit event is triggered by Cmd-Q, selecting Quit from the application or Dock menu, or logging out
    public boolean quit()
    {
    	return true;
    }
    
    // General info dialog; fed to the OSXAdapter as the method to call when 
    // "About OSXAdapter" is selected from the application menu   
    public void about()
    {
    	//User has chosen to view the About dialog
		String versionMsg = String.format("Program of Study");
		JOptionPane.showMessageDialog(posFrame, versionMsg, "About the PoS App", 
										JOptionPane.INFORMATION_MESSAGE);
    }
	
	POSView createAndShowView()
    {
    	posFrame = new JFrame(APPNAME);
		posFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			 {
				exit("QUIT");			  
			 }});
        posFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//On close, user is prompted to confirm
        posFrame.setMinimumSize(new Dimension(832, 660));
        posFrame.setLocationByPlatform(true);
        
        //Create the menu bar and set action listener for each menu item
        menuBar = new POSMenuBar();
        posFrame.setJMenuBar(menuBar);
        
        //Create a content panel for the frame and add components to it.
        posContentPane = new JPanel();
        posContentPane.setLayout(new BoxLayout(posContentPane, BoxLayout.PAGE_AXIS));
        
        //Create the view
        posView = new POSView(courseTM);
        posContentPane.add(posView);
        
        posFrame.setContentPane(posContentPane); 
        posFrame.setVisible(true);
        
        return posView;
    }
	
	private void createandshowGUI()
	{
        //Create the menu bar and set action listener for each menu item
        menuBar = new POSMenuBar();
        posFrame.setJMenuBar(menuBar);
        
//      MenuItemListener menuItemListener = new MenuItemListener();
        
//        POSMenuBar.newMI.addActionListener(menuItemListener);
//        POSMenuBar.openMI.addActionListener(menuItemListener);
//        POSMenuBar.saveMI.addActionListener(menuItemListener);
//        POSMenuBar.saveAsMI.addActionListener(menuItemListener);
//        POSMenuBar.exitMI.addActionListener(menuItemListener);       
	}
	
	void exit(String command)
	{
	   	System.exit(0);
	}
	
	List<Course> readConcentrationFromDisk(Concentration c) throws IOException
	{
		List<Course> courseList = new ArrayList<Course>();
		
		String filename = System.getProperty("user.dir") + "/" + c.toString() + ".dat";
		BufferedReader br = new BufferedReader(new FileReader(filename));
	    try
	    {
	    	String line;
	        while ((line = br.readLine()) != null)
	        {
	        	courseList.add(new Course(line));
	        	line = br.readLine();
	        }
	    }
	    finally 
	    {
	        br.close();
	    }
		
		return courseList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == posView.concentrationCB)
		{
			System.out.println("Concentration Selected: " + 
						posView.concentrationCB.getSelectedItem().toString());
			
			Concentration c = (Concentration) posView.concentrationCB.getSelectedItem();
			courseTM.setConcentration(c);
		}
	}

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() { new POSController(); }
     });

	}

	
}
