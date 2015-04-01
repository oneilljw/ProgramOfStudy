package ProgramOfStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class POSTableModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SEMESTER_COL= 0;
	private static final int DEPT_COL = 1;
	private static final int NUM_COL = 2;
	private static final int HOURS_COL = 3;
	private static final int TITLE_COL = 4;
	private static final int GRADE_COL = 5;
	private static final String VALID_GRADES = "ABCDEF";
	
	private String[] columnNames = {"Sem", "Dept", "#", "Hrs", "Title", "Grade"};
	private List<StudentCourse> posList;
	
	public POSTableModel()
	{
		posList = new ArrayList<StudentCourse>();
	}
	
	StudentCourse getStudentCourse(int row)
	{
		return posList.get(row);
	}
	
	void addCourse(StudentCourse sc)
	{
		posList.add(sc);
		this.fireTableDataChanged();
	}
	
	void removeCourse(StudentCourse sc)
	{
		int index = 0;
		while(index < posList.size() && !posList.get(index).equals(sc))
			index++;
		
		if(index < posList.size())
		{
			posList.remove(index);
			this.fireTableDataChanged();
		}	
	}
	
	void openPOS()
	{
		String workingDir = System.getProperty("user.dir");
		String filename = workingDir + "/Andrew" + ".pos";
		
		try
	    {
	         FileInputStream fis = new FileInputStream(filename);
	         ObjectInputStream in = new ObjectInputStream(fis);
	         posList = (ArrayList<StudentCourse>) in.readObject();
	         in.close();
	         fis.close();
	         fireTableDataChanged();
	    }
		catch(IOException i)
	    {
	         i.printStackTrace();
	         return;
	    }
		catch(ClassNotFoundException c)
	    {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	    }
	}
	
	void savePOS()
	{
		String workingDir = System.getProperty("user.dir");
		String filename = workingDir + "/Andrew" + ".pos";
		try
	    {
			FileOutputStream fos = new FileOutputStream(filename);
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	         
	        out.writeObject(posList);
	         
	        out.close();
	        fos.close();
	        System.out.printf("Serialized PoS is saved in /tmp/employee.ser");
	    }
		catch(IOException i)
	    {
	         i.printStackTrace();
	    }
	}
	
	@Override
	public int getColumnCount() { return columnNames.length; }
		
	@Override
	public String getColumnName(int col) { return columnNames[col]; }
	 
	@Override
	public int getRowCount()
	{ 
		return posList.size();
	}
		
	@Override
	public Object getValueAt(int row, int col)
	{
		if(col == SEMESTER_COL)  
			return posList.get(row).getSemester();
		else if(col == DEPT_COL)
			return posList.get(row).getDepartment();
		else if (col == NUM_COL)
			return posList.get(row).getNumber();
		else if (col == HOURS_COL)
			return posList.get(row).getCreditHours();
		else if (col == TITLE_COL)
			return posList.get(row).getTitle();
		else if(col == GRADE_COL)
			return posList.get(row).getGrade();
		else
			return "Error";
	}

	@Override
	public boolean isCellEditable(int row, int col)
    {
        return col == GRADE_COL;	//Only the grade column is editable
    }
	
	//Don't need to implement this method unless your table's data can change.
	@Override
    public void setValueAt(Object value, int row, int col)
    { 	
    	//set the grade in the student course to the grade entered by the user
    	//into the Program of Study table
    	String grade = (String) value;
    		
    	//verify its a valid grade or else reset to the previous grade
    	if(VALID_GRADES.contains(grade))
    	{
    		StudentCourse sc = posList.get(row);
       		sc.setGrade(grade.charAt(0));
    	}
    	else	//indicate error and reset table to prior grade
    	{
    	}                      
    }
	
	@Override
	public Class getColumnClass(int column)
	{
        Class returnValue;
        if ((column >= 0) && (column < getColumnCount()))
          returnValue = getValueAt(0, column).getClass();
        else
          returnValue = Object.class;
        
        return returnValue;
    }
}
