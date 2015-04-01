package ProgramOfStudy;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class POSController implements ActionListener
{
	//view components
	private JFrame posFrame;
	private POSView posView;
	
	//controller components - they use the Course and StudentCourse model classes
	private CourseTableModel courseTM;
	private POSTableModel posTM;

	public POSController()
	{
		//initialize controller components
		courseTM = new CourseTableModel();
		posTM = new POSTableModel();
		
		//initialize view component
		createAndShowView();
	}

	void createAndShowView()
    {
    	posFrame = new JFrame("Program Of Study");
		posFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			 {
				exit("QUIT");			  
			 }});
        posFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//On close, user is prompted to confirm
        posFrame.setMinimumSize(new Dimension(1050, 600));
        posFrame.setLocationByPlatform(true);

        //Fetch the content panel for the frame and add components to it.
        JPanel posContentPane = (JPanel) posFrame.getContentPane();
        posContentPane.setLayout(new BoxLayout(posContentPane, BoxLayout.PAGE_AXIS));
        
        //Create the view and add controller listeners to view events
        posView = new POSView(courseTM, posTM);
      	posView.concentrationCB.addActionListener(this);
      	posView.btnAdd.addActionListener(this);
      	posView.btnRemove.addActionListener(this);
      	posView.btnOpen.addActionListener(this);
      	posView.btnSave.addActionListener(this);
      	posView.btnExit.addActionListener(this);
        posContentPane.add(posView);
        
        //set the content for the frame and make visible
        posFrame.setContentPane(posContentPane); 
        posFrame.setVisible(true);
    }
	
	void exit(String command)
	{
	   	System.exit(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == posView.concentrationCB)
		{	
			Concentration c = (Concentration) posView.concentrationCB.getSelectedItem();
			courseTM.setConcentration(c);
		}
		else if(e.getSource() == posView.btnAdd)
		{
			int modelRow = posView.getSelectedCourseRow();
			
			if(modelRow > -1)	//ensure the row exists
			{
				Course selCourse = courseTM.getCourse(modelRow);
				StudentCourse sc = new StudentCourse(selCourse);
				posTM.addCourse(sc);
			}
		}
		else if(e.getSource() == posView.btnRemove)
		{
			int modelRow = posView.getSelectedPOSRow();
			
			if(modelRow > -1)	//ensure the row exists
			{
				StudentCourse selCourse = posTM.getStudentCourse(modelRow);
				posTM.removeCourse(selCourse);
			}
		}
		else if(e.getSource() == posView.btnSave)
		{
			posTM.savePOS();
		}
		else if(e.getSource() == posView.btnOpen)
		{
			posTM.openPOS();
		}
	}

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() { new POSController(); }
		});
	}
}
