import java.io.*;
import java.util.*;

public class FileClient {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Scanner input2;
	
	//public CreateClient(ObjectOutputStream fileOut){
		//output = fileOut
	//}
	
	public void openFile()
	{
		try
		{
			output = new ObjectOutputStream(
					new FileOutputStream("client.ser"));
		}
		catch(IOException e)
		{
			System.err.println("Error opening the file.");
		}
	}
	
	public void addRecords(int accNumber, String firstName, String lastName, double balance)
	{
		AccountRecordSerializable record;
		
		input2 = new Scanner(System.in);
		
		System.out.printf("%s\n%s","Acc num, first name, last name, balance.","? ");
		
		while(input2.hasNext())
		{
			try
			{
				accNumber = input2.nextInt();
				firstName = input2.next();
				lastName = input2.next();
				balance = input2.nextDouble();
				
				if(accNumber > 0){
					record = new AccountRecordSerializable(accNumber, firstName, lastName, balance );
					output.writeObject( record );
				}
				else
				{
					System.out.printf("%s\n","Invalid account number. Account number must be greater than 0.");
				}
			}
			catch(IOException e )
			{
				System.err.println("Error writing to the file");
				return;
			}
			catch( NoSuchElementException e )
			{
				System.err.println("Invalid input");
				input2.nextLine();
			}
			
			System.out.printf("%s\n","Invalid account number. Account number must be greater than 0.");
		}
	}
	
	public void readRecords()
	{
		AccountRecordSerializable record;
		System.out.printf("%-15d%-15s%-15s%-15s\n", "Account","First Name","Last Name","Balance");
		try
		{
			while(true)
			{
				record = (AccountRecordSerializable) input.readObject();
				
				System.out.printf("%-15d%-15s%-15s%-15.2f\n",record.getAccount(), record.getFirstName(),
						record.getLastName(), record.getBalance() );
			}
		}
		catch(EOFException e)
		{
			return;
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Could not read data into new client object");
		}
		catch(IOException e)
		{
			System.err.println("Error while trying to read from file");
		}
	}
	
	public void closeFile()
	{
		try
		{
			if(output != null) output.close();
		}
		catch ( IOException e )
		{
			System.err.println("Failed to close the file.");
			System.exit(1);
		}
	}
}
