package ProgramOfStudy;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class POSTable extends javax.swing.JTable
{
	private static final long serialVersionUID = 1L;
	private static final Color ROW_COLOR = new Color(238,233,233);	//Light Gray
	
	private String[] colToolTips;

	public POSTable(TableModel tm, String[] colTT)
	{
		super(tm);
		autoResizeMode = JTable.AUTO_RESIZE_OFF;
		colToolTips = colTT;
	}
	
	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width < getParent().getWidth();
	}
		
	//Set up tool tips for each column
	protected JTableHeader createDefaultTableHeader() {
		return new JTableHeader(columnModel) {
			private static final long serialVersionUID = 1L;

			public String getToolTipText(MouseEvent e)
		    {
				java.awt.Point p = e.getPoint();
			    int index = columnModel.getColumnIndexAtX(p.x);
			    int realIndex = columnModel.getColumn(index).getModelIndex();
			    return colToolTips[realIndex];
			}
		};
	}
	
	//replace the renderer to alternate colors for rows
	public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
		Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
			  		 
		if(isRowSelected(Index_row))
			comp.setBackground(comp.getBackground());
		else if (Index_row % 2 == 1)			  
			comp.setBackground(ROW_COLOR);
		else
			comp.setBackground(Color.WHITE);
			  
		return comp;
	}
}
