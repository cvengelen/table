package table;

import java.util.logging.*;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// class JFrameListener extends JFrame  implements ComponentListener
//{
//    final private Logger logger = Logger.getLogger( "table.ResizeTable" );

//    public void componentResized( ComponentEvent componentEvent )
//    {
//        Dimension size = this.getSize( );
//        logger.info( "Frame size: " + size.width + " * " + size.height );
//    }
    
// }

public class ResizeTable
{
    final private static Logger logger = Logger.getLogger( "table.ResizeTable" );

    public static void main(String args[])
    {

        Object rowData[][] = { { "1", "one", "ichi - \u4E00", "un", "I" },
        { "2", "two", "ni - \u4E8C", "deux", "II" },
        { "3", "three", "san - \u4E09", "trois", "III" },
        { "4", "four", "shi - \u56DB", "quatre", "IV" },
        { "5", "five", "go - \u4E94", "cinq", "V" },
        { "6", "six", "roku - \u516D", "treiza", "VI" },
        { "7", "seven", "shichi - \u4E03", "sept", "VII" },
        { "8", "eight", "hachi - \u516B", "huit", "VIII" },
        { "9", "nine", "kyu - \u4E5D", "neur", "IX" },
        { "10", "ten", "ju - \u5341", "dix", "X" } };

        String columnNames[] = { "#", "English", "Japanese", "French", "Roman" };
        
        final JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        String modes[] = { "Resize All Columns", "Resize Last Column",
            "Resize Next Column", "Resize Off", "Resize Subsequent Columns" };
        final int modeKey[] = { JTable.AUTO_RESIZE_ALL_COLUMNS,
            JTable.AUTO_RESIZE_LAST_COLUMN, JTable.AUTO_RESIZE_NEXT_COLUMN,
            JTable.AUTO_RESIZE_OFF, JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS };
        JComboBox resizeModeComboBox = new JComboBox(modes);
        int defaultMode = 4;
        table.setAutoResizeMode(modeKey[defaultMode]);
        resizeModeComboBox.setSelectedIndex(defaultMode);
        ItemListener itemListener = new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                JComboBox source = (JComboBox) e.getSource();
                int index = source.getSelectedIndex();
                table.setAutoResizeMode(modeKey[index]);
            }
        };
        resizeModeComboBox.addItemListener(itemListener);
        
        final JFrame frame = new JFrame("Resizing Table");

        ComponentListener componentListener = new ComponentListener( )
        {
            public void componentResized( ComponentEvent componentEvent )
            {
                Dimension size = frame.getSize( );
                logger.info( "Frame size: " + size.width + " * " + size.height );
            }
            public void componentHidden( ComponentEvent componentEvent )
            {
            }
            public void componentShown( ComponentEvent componentEvent )
            {
            }
            public void componentMoved( ComponentEvent componentEvent )
            {
            }
        };
        frame.addComponentListener( componentListener );

        /* 
        Container contentPane = frame.getContentPane();
        contentPane.add(resizeModeComboBox, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        */
        
        final Container container = frame.getContentPane( );
        
        // Set grid bag layout manager
	container.setLayout( new GridBagLayout( ) );
	GridBagConstraints constraints = new GridBagConstraints( );
	constraints.anchor = GridBagConstraints.WEST;
	constraints.insets = new Insets( 10, 10, 10, 10 );
	constraints.weightx = 1.0;
	constraints.weighty = 0.0;
	constraints.gridx = 0;
	constraints.gridy = 0;
	container.add( resizeModeComboBox, constraints );

	constraints.gridy = GridBagConstraints.RELATIVE;
	constraints.gridwidth = 2;
	constraints.weightx = 1.0;
	constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
	container.add( scrollPane, constraints );


        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}

           