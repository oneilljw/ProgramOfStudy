package ProgramOfStudy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CourseTableModel extends AbstractTableModel
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
	
	private String[] columnNames = {"Sem", "Dept", "#", "Hrs", "Title"};
	private Concentration concentration;
	private List<List<Course>> courseList;
	
	public CourseTableModel()
	{
		this.courseList = new ArrayList<List<Course>>();
		
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
		this.concentration = Concentration.InformationSystems;
	}
	
	//getters
//	Concentration getConcentration() { return concentration; }
	Course getCourse(int row)
	{
		return courseList.get(concentration.index()).get(row);
	}
	
	//setters
	void setConcentration(Concentration c)
	{
		if(this.concentration != c)
		{
			this.concentration = c;
			fireTableDataChanged();
		}
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
	public int getColumnCount() { return columnNames.length; }
		
	@Override
	public String getColumnName(int col) { return columnNames[col]; }
	 
	@Override
	public int getRowCount()
	{ 
		return courseList.get(concentration.index()).size();
	}
		
	@Override
	public Object getValueAt(int row, int col)
	{
		if(col == SEMESTER_COL)  
			return courseList.get(concentration.index()).get(row).getSemester();
		else if(col == DEPT_COL)
			return courseList.get(concentration.index()).get(row).getDepartment();
		else if (col == NUM_COL)
			return courseList.get(concentration.index()).get(row).getNumber();
		else if (col == HOURS_COL)
			return courseList.get(concentration.index()).get(row).getCreditHours();
		else if (col == TITLE_COL)
			return courseList.get(concentration.index()).get(row).getTitle();
		else
			return "Error";
	}
	
	 //JTable uses this method to determine the default renderer/editor for each cell.
    @Override
    public Class<?> getColumnClass(int column)
    {
    	if(column == SEMESTER_COL || column == NUM_COL || column == HOURS_COL)
    		return Integer.class;
    	else
    		return String.class;
    }
}
