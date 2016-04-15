import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Insets;


public class Interface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextArea outputArea;
	private JTable table;
	JTextArea searchText = new JTextArea();
	AccountRecordSerializable currentRecordSelection = new AccountRecordSerializable();

	AccountRecordSerializable searched = new AccountRecordSerializable();
	ClientAccess ca = new ClientAccess();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadFile = new JMenuItem("Load File");
		mntmLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInfo();
			}
		});
		mnFile.add(mntmLoadFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel search = new JPanel();
		search.setAlignmentX(Component.RIGHT_ALIGNMENT);
		search.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		frame.getContentPane().add(search, BorderLayout.SOUTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Account Number", "First Name", "Last Name", "Account Balance"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				currentRecordSelection = ca.getRecord(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
				//System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
				
			}
			
		});
		search.setLayout(new BorderLayout(0, 0));
		//search.add(scrollPane_1,BorderLayout.SOUTH);
		search.add(table);
		
		JLabel lblSearchResult = new JLabel("Search result:");
		search.add(lblSearchResult, BorderLayout.WEST);
		
		// table.getModel().setValueAt(timePlay, 0, 1);
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton addButton = new JButton("Add Record");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRecord newRecord = new AddRecord();
				newRecord.frame.setVisible(true);
			}
		});
		
		panel.add(addButton);
		
		JButton removeButton = new JButton("Remove Record");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(removeButton);
		
		JButton editButton = new JButton("Edit Record");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		panel.add(editButton);
		
		
		searchText.setBackground(SystemColor.control);
		searchText.setText("Enter Account Number");
		
		JButton searchButton = new JButton("Search ");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int toSearch = Integer.parseInt(searchText.getText());
				}
				catch(NumberFormatException nf){
					JOptionPane.showMessageDialog(frame, "Invalid Search. Enter a number.","Invalid Name",JOptionPane.ERROR_MESSAGE);
				}
				
				searched = ca.getRecord(Integer.parseInt(searchText.getText()));
				System.out.println(ca.getRecord(Integer.parseInt(searchText.getText())).getFirstName());
				outputArea.setText(ca.getRecord(Integer.parseInt(searchText.getText())).getFirstName());
				/*table.getModel().setValueAt(searched.getAccount(), 0, 0);
				table.getModel().setValueAt(searched.getFirstName(), 0, 1);
				table.getModel().setValueAt(searched.getLastName(), 0, 2);
				table.getModel().setValueAt(searched.getBalance(), 0, 3);
				*/
				
			}
		});
		panel.add(searchButton);
		panel.add(searchText);
		
		outputArea = new JTextArea();
		frame.getContentPane().add(outputArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel DisplayAN = new JLabel("Account Number");
		DisplayAN.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(DisplayAN);
		
		JLabel DisplayFN = new JLabel("First Name");
		DisplayFN.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(DisplayFN);
		
		JLabel DisplayLN = new JLabel("Last Name");
		DisplayLN.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(DisplayLN);
		
		JLabel DisplayAB = new JLabel("Account Balance");
		DisplayAB.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(DisplayAB);
		
		JTextArea textAreaAN = new JTextArea();
		textAreaAN.setBackground(SystemColor.controlHighlight);
		textAreaAN.setEditable(false);
		panel_1.add(textAreaAN);
		
		JTextArea textAreaFN = new JTextArea();
		textAreaFN.setEditable(false);
		textAreaFN.setBackground(SystemColor.scrollbar);
		panel_1.add(textAreaFN);
		
		JTextArea textAreaLN = new JTextArea();
		textAreaLN.setEditable(false);
		textAreaLN.setBackground(SystemColor.controlHighlight);
		panel_1.add(textAreaLN);
		
		JTextArea textAreaAB = new JTextArea();
		textAreaAB.setEditable(false);
		textAreaAB.setBackground(SystemColor.scrollbar);
		panel_1.add(textAreaAB);
			
	}
	
	private File getFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int result = fileChooser.showOpenDialog(this);
		if(result == JFileChooser.CANCEL_OPTION){
			System.exit(1);
		}
		File fileName = fileChooser.getSelectedFile();
		
		if((fileName==null) || (fileName.getName().equals("")))
		{
			JOptionPane.showMessageDialog(this, "Invalid Name","Invalid Name",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		return fileName;
	}
	public void showInfo()
	{
		File name = getFile();
		if(name.exists())
		{
			outputArea.setText("Made it");
		}
		else
		{
			JOptionPane.showMessageDialog(this, name + " does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
