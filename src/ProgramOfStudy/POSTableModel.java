package ProgramOfStudy;

import java.util.List;

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
	
	private String[] columnNames = {"Sem", "Dept", "#", "Hrs", "Title", "Grade"};
	private List<StudentCourse> posList;
	
	public POSTableModel(List<StudentCourse> posList)
	{
		this.posList = posList;
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
