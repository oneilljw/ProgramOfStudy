package ProgramOfStudy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class POSController implements ActionListener, TableModelListener
{
	//Program of Study Image Icon
	private static ImageIcon posIcon;
	
	//view components
	private static JFrame posFrame;
	private POSView posView;
	
	//controller components - they use the Course and StudentCourse model classes
	private CourseTableModel courseTM;
	private POSTableModel posTM;

	public POSController()
	{
		//construct POS logo
		posIcon = createImageIcon("poslogo.gif", "POS Logo");
		
		//construct controller components
		courseTM = new CourseTableModel();
		posTM = new POSTableModel();
		posTM.addTableModelListener(this);
		
		//construct view and show
		createAndShowView();
	}

	void createAndShowView()
    {
    	posFrame = new JFrame("Program Of Study");

        posFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//On close, user is prompted to confirm
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
        posFrame.pack();
        posFrame.setVisible(true);
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

				posView.btnSave.setEnabled(true);
			}
		}
		else if(e.getSource() == posView.btnRemove)
		{
			int modelRow = posView.getSelectedPOSRow();
			
			if(modelRow > -1)	//ensure the row exists
			{
				StudentCourse selCourse = posTM.getStudentCourse(modelRow);
				posTM.removeCourse(selCourse);

				posView.btnSave.setEnabled(true);
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
		else if(e.getSource() == posView.btnExit)
		{
			System.exit(0);
		}
	}
	
	 /** Returns an ImageIcon, or null if the path was invalid. */
   	ImageIcon createImageIcon(String path, String description)
   	{
   		java.net.URL imgURL = getClass().getResource(path);
   		if (imgURL != null) { return new ImageIcon(imgURL, description); } 
   		else { System.err.println("Couldn't find file: " + path); return null; }
   	}
	
	static JFrame getFrame() { return posFrame; }
	
	static ImageIcon getLogo() { return posIcon; }

	@Override
	public void tableChanged(TableModelEvent tme)
	{
		if(tme.getSource() == posTM && tme.getType() == TableModelEvent.UPDATE && tme.getColumn() == 5)
		{
			System.out.println("posTable: grade changed in row: " + tme.getFirstRow());
		}
	}
}
