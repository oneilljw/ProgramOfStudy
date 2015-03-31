package ProgramOfStudy;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class POSView extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private POSTable courseTable, posTable;
	private JButton btnAdd, btnRemove, btnResetCourseFilter, btnResetPOSFilter;
	public JComboBox concentrationCB, courseNumCB, deptCB;
	
	public POSView(CourseTableModel courseTM)
	{
		JPanel coursePanel = new JPanel();
		JPanel cntlPanel = new JPanel();
		JPanel posPanel = new JPanel();
		
		//set up the course panel
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
		coursePanel.setBorder(BorderFactory.createTitledBorder("Course Catalog"));
		
		JPanel couseFilterPanel = new JPanel();
		couseFilterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		concentrationCB = new JComboBox(Concentration.values());
		concentrationCB.setBorder(BorderFactory.createTitledBorder("Concentration"));
		couseFilterPanel.add(concentrationCB);
		
		String[] courseTableTT = {"Semester", "Department", "Course #", "Credit Hours", "Couurse Title"};
		courseTable = new POSTable(courseTM, courseTableTT, new Color(240,248,255));

		courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		courseTable.getSelectionModel().addListSelectionListener(this);
		
		//Set table column widths
		int tablewidth = 0;
		int[] colWidths = {32, 48, 32, 48, 160};
		for(int col=0; col < colWidths.length; col++)
		{
			courseTable.getColumnModel().getColumn(col).setPreferredWidth(colWidths[col]);
			tablewidth += colWidths[col];
		}
		tablewidth += 24; 	//count for vertical scroll bar
		
        courseTable.setAutoCreateRowSorter(true);	//add a sorter
        
        JTableHeader anHeader = courseTable.getTableHeader();
        anHeader.setForeground( Color.black);
        anHeader.setBackground( new Color(161,202,241));
        
        //left justify wish count column
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.LEFT);
        courseTable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        
        //Create the scroll pane and add the table to it.
        JScrollPane courseScrollPane = new JScrollPane(courseTable);
        courseScrollPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
        
        //set up the course panel reset filter button
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnResetCourseFilter = new JButton("Reset Filters");
        resetPanel.add(btnResetCourseFilter);
         
        coursePanel.add(couseFilterPanel);
        coursePanel.add(courseScrollPane);
        coursePanel.add(resetPanel);
        
        //set up the control panel
        cntlPanel.setLayout(new BoxLayout(cntlPanel, BoxLayout.Y_AXIS));
        
        btnAdd = new JButton("Add ->");
        btnAdd.setEnabled(false);
        cntlPanel.add(btnAdd);
        
        btnRemove = new JButton("Remove");
        btnRemove.setEnabled(false);
        cntlPanel.add(btnRemove);
        
        //set up the posPanel
        posPanel.setLayout(new BoxLayout(posPanel, BoxLayout.Y_AXIS));
		posPanel.setBorder(BorderFactory.createTitledBorder("Student Program of Study"));
		
		JPanel posFilterPanel = new JPanel();
		couseFilterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		concentrationCB = new JComboBox(Concentration.values());
		concentrationCB.setBorder(BorderFactory.createTitledBorder("Concentration"));
		couseFilterPanel.add(concentrationCB);
		
		String[] posTableTT = {"Semester", "Department", "Course #", "Credit Hours", "Couurse Title", "Grade"};
//		posTable = new POSTable(posTM, posTableTT, new Color(240,248,255));

		posTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		posTable.getSelectionModel().addListSelectionListener(this);
		
		//Set table column widths
		tablewidth = 0;
		int[] posColWidths = {32, 48, 32, 48, 160};
		for(int col=0; col < posColWidths.length; col++)
		{
			posTable.getColumnModel().getColumn(col).setPreferredWidth(posColWidths[col]);
			tablewidth += posColWidths[col];
		}
		tablewidth += 24; 	//count for vertical scroll bar
		
        courseTable.setAutoCreateRowSorter(true);	//add a sorter
        
//      JTableHeader anHeader = courseTable.getTableHeader();
//      anHeader.setForeground( Color.black);
//      anHeader.setBackground( new Color(161,202,241));
        
        //left justify wish count column
//        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.LEFT);
        courseTable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        
        //Create the scroll pane and add the table to it.
        JScrollPane posScrollPane = new JScrollPane(posTable);
        courseScrollPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
        
        //set up the course panel reset filter button
        JPanel posResetPanel = new JPanel();
        posResetPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnResetPOSFilter = new JButton("Reset Filters");
        resetPanel.add(btnResetPOSFilter);
         
        posPanel.add(posFilterPanel);
        posPanel.add(posScrollPane);
        posPanel.add(posResetPanel);
        
        this.add(coursePanel);
        this.add(cntlPanel);
        this.add(posPanel);
	}
}
