package ProgramOfStudy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class POSView extends JPanel implements ActionListener, ListSelectionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREFERRED_NUMBER_OF_TABLE_ROWS = 15;
	
	private POSTable courseTable, posTable;
	public JButton btnAdd, btnRemove;
	public JButton btnOpen, btnSave, btnExit;
	private JButton btnClearCourseSearch, btnClearPOSSearch;
	public JComboBox concentrationCB, searchTypeCB, deptCB; 
	public JTextField courseSearchTF, posSearchTF;
	private TableRowSorter<CourseTableModel> courseTableRowSorter;
	private TableRowSorter<POSTableModel> posTableRowSorter;

	
	public POSView(CourseTableModel courseTM, POSTableModel posTM)
	{
		JPanel coursePanel = new JPanel();
		JPanel cntlPanel = new JPanel();
		JPanel posPanel = new JPanel();
		
		//set up the course panel
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
		coursePanel.setBorder(BorderFactory.createTitledBorder("Course Catalog"));
		
		JPanel courseSearchPanel = new JPanel();
		courseSearchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		courseSearchPanel.add(new JLabel("Search For:"));
		
		courseSearchTF = new JTextField(15);
		courseSearchTF.addActionListener(this);
		courseSearchPanel.add(courseSearchTF);
		
		btnClearCourseSearch = new JButton("Clear");
		btnClearCourseSearch.addActionListener(this);
		courseSearchPanel.add(btnClearCourseSearch);
		
		String[] courseTableTT = {"Semester", "Department", "Course #", "Credit Hours", "Course Title"};
		courseTable = new POSTable(courseTM, courseTableTT);

		courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courseTable.getSelectionModel().addListSelectionListener(this);
		
		//Set table column widths
		int tablewidth = 0;
		int[] colWidths = {28, 36, 28, 28, 240};
		for(int col=0; col < colWidths.length; col++)
		{
			courseTable.getColumnModel().getColumn(col).setPreferredWidth(colWidths[col]);
			tablewidth += colWidths[col];
		}
		tablewidth += 24; 	//count for vertical scroll bar
		
		courseTableRowSorter = new TableRowSorter<CourseTableModel>(courseTM);
        courseTable.setRowSorter(courseTableRowSorter);	//add a sorter
        
        JTableHeader anHeader = courseTable.getTableHeader();
        anHeader.setForeground( Color.black);
        anHeader.setBackground( new Color(161,202,241));
        
        //justify column
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        courseTable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        courseTable.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        
        //Create the scroll pane and add the table to it
        Dimension tablesize = new Dimension(tablewidth, courseTable.getRowHeight() *
        										PREFERRED_NUMBER_OF_TABLE_ROWS);
        courseTable.setPreferredScrollableViewportSize(tablesize);
        JScrollPane courseScrollPane = new JScrollPane(courseTable,
        	JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //set up the course panel reset filter button
        JPanel concentrationPanel = new JPanel();
        concentrationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel lblConcentration = new JLabel("Select Concentration:");
        concentrationCB = new JComboBox(Concentration.values());
        concentrationPanel.add(lblConcentration);
        concentrationPanel.add(concentrationCB);
         
        coursePanel.add(courseSearchPanel);
        coursePanel.add(courseScrollPane);
        coursePanel.add(concentrationPanel);
        
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
		
		JPanel posSearchPanel = new JPanel();
		posSearchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		posSearchPanel.add(new JLabel("Search For:"));
		
		posSearchTF = new JTextField(15);
		posSearchTF.addActionListener(this);
		posSearchPanel.add(posSearchTF);
		
		btnClearPOSSearch = new JButton("Clear");
		btnClearPOSSearch.addActionListener(this);
		posSearchPanel.add(btnClearPOSSearch);
		
		String[] posTableTT = {"Semester", "Department", "Course #", "Credit Hours",
								"Couurse Title", "Grade"};
		posTable = new POSTable(posTM, posTableTT);

		posTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		posTable.getSelectionModel().addListSelectionListener(this);
		
		//Set table column widths
		tablewidth = 0;
		int[] posColWidths = {28, 36, 28, 28, 240, 48};
		for(int col=0; col < posColWidths.length; col++)
		{
			posTable.getColumnModel().getColumn(col).setPreferredWidth(posColWidths[col]);
			tablewidth += posColWidths[col];
		}
		tablewidth += 24; 	//count for vertical scroll bar
		
		posTableRowSorter = new TableRowSorter<POSTableModel>(posTM);
		posTable.setRowSorter(posTableRowSorter);	//add a sorter
        
        JTableHeader posHeader = posTable.getTableHeader();
        posHeader.setForeground( Color.black);
        posHeader.setBackground( new Color(161,202,241));
        
        //justify columns
        DefaultTableCellRenderer posdtcr = new DefaultTableCellRenderer();
        posdtcr.setHorizontalAlignment(SwingConstants.CENTER);
        posTable.getColumnModel().getColumn(0).setCellRenderer(posdtcr);
        posTable.getColumnModel().getColumn(3).setCellRenderer(posdtcr);
        posTable.getColumnModel().getColumn(5).setCellRenderer(posdtcr);
        
        //Create the scroll pane and add the table to it.
        Dimension postablesize = new Dimension(tablewidth, posTable.getRowHeight() *
        										PREFERRED_NUMBER_OF_TABLE_ROWS);
        posTable.setPreferredScrollableViewportSize(postablesize);
        JScrollPane posScrollPane = new JScrollPane(posTable,
        	JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //set up the course panel reset filter button
        JPanel posResetPanel = new JPanel();
        posResetPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnOpen = new JButton("Open");
        posResetPanel.add(btnOpen);
        
        btnSave = new JButton("Save");
        btnSave.setEnabled(false);	//no POS data when constructor executes
        posResetPanel.add(btnSave);
        
        btnExit = new JButton("Exit");
        posResetPanel.add(btnExit);
         
        posPanel.add(posSearchPanel);
        posPanel.add(posScrollPane);
        posPanel.add(posResetPanel);
        
        this.add(coursePanel);
        this.add(cntlPanel);
        this.add(posPanel);
	}
	
	int getSelectedCourseRow()
	{
		int courseTableRow = courseTable.getSelectedRow();
		if(courseTableRow > -1)
			return courseTable.convertRowIndexToModel(courseTable.getSelectedRow());
		else
			return -1;
	}
	
	int getSelectedPOSRow()
	{
		int posTableRow = posTable.getSelectedRow();
		if(posTableRow > -1)
			return posTable.convertRowIndexToModel(posTable.getSelectedRow());
		else
			return -1;
	}

	@Override
	public void valueChanged(ListSelectionEvent lse)
	{
		if(lse.getSource() == courseTable.getSelectionModel())
		{
			int modelRow = courseTable.getSelectedRow() == -1 ? -1 : 
						courseTable.convertRowIndexToModel(courseTable.getSelectedRow());
		
			if(modelRow > -1)
				btnAdd.setEnabled(true);
			else
				btnAdd.setEnabled(false);
		}
		else if(lse.getSource() == posTable.getSelectionModel())
		{
			int modelRow = posTable.getSelectedRow() == -1 ? -1 : 
						posTable.convertRowIndexToModel(posTable.getSelectedRow());
		
			if(modelRow > -1)
				btnRemove.setEnabled(true);
			else
				btnRemove.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == courseSearchTF)
		{
			String searchText = courseSearchTF.getText();
			
			if(searchText.isEmpty())
				courseTableRowSorter.setRowFilter(null);
			else
				courseTableRowSorter.setRowFilter(RowFilter.regexFilter(searchText));
		}
		else if(e.getSource() == btnClearCourseSearch)
		{
			courseSearchTF.setText("");
			courseTableRowSorter.setRowFilter(null);
		}
		else if(e.getSource() == posSearchTF)
		{
			String searchText = posSearchTF.getText();

			if(searchText.isEmpty())
				posTableRowSorter.setRowFilter(null);
			else
				posTableRowSorter.setRowFilter(RowFilter.regexFilter(searchText));
		}
		else if(e.getSource() == btnClearPOSSearch)
		{
			posSearchTF.setText("");
			posTableRowSorter.setRowFilter(null);
		}
	}
}
