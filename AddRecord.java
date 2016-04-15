import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddRecord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField ANText;
	private JTextField FNText;
	private JTextField LNText;
	private JTextField ABText;
	ClientAccess ca = new ClientAccess();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRecord window = new AddRecord();
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
	public AddRecord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 134, 278);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		frame.getContentPane().add(lblAccountNumber);
		
		ANText = new JTextField();
		ANText.setText("0");
		frame.getContentPane().add(ANText);
		ANText.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		frame.getContentPane().add(lblFirstName);
		
		FNText = new JTextField();
		FNText.setText("Blank\r\n");
		frame.getContentPane().add(FNText);
		FNText.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		frame.getContentPane().add(lblLastName);
		
		LNText = new JTextField();
		LNText.setText("Blank");
		frame.getContentPane().add(LNText);
		LNText.setColumns(10);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		frame.getContentPane().add(lblAccountBalance);
		
		ABText = new JTextField();
		ABText.setText("0.0");
		frame.getContentPane().add(ABText);
		ABText.setColumns(10);
		
		final JLabel lblStatus = new JLabel();
		lblStatus.setText("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSubmitForm = new JButton("Submit Form");
		btnSubmitForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = "SUCCESS";
				boolean canGoOn = true;
				try
				{
					int submitAccountNumber = Integer.parseInt(ANText.getText());
					String submitFirstName = FNText.getText();
					String submitLastName = LNText.getText();
					double submitBalance = Double.parseDouble(ABText.getText());
					
					canGoOn = checkMe(submitAccountNumber);
					
					if(submitAccountNumber > 0 && canGoOn){
						ca.addToMap(new AccountRecordSerializable(submitAccountNumber, submitFirstName, submitLastName, submitBalance));
						ca.displayMap();
					}
					else
					{
						result = "ERROR";
					}
				
				}
				catch(NoSuchElementException noElement)
				{
					result = "Invalid Input";
				}
				lblStatus.setText(result);
				
			}
			
		});
		
		frame.getContentPane().add(btnSubmitForm);
		frame.getContentPane().add(lblStatus);
		
		
	}
	
	public boolean checkMe(int AN)
	{
		boolean result = true;
		if(AN<=0){
			JOptionPane.showMessageDialog(this, "Account Number must be great than 0","ERROR",JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		return result;
	}
	
	

}
