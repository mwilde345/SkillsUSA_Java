import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class ClientAccess {
	
	/**
	 * 
	 */
	Map<Integer, AccountRecordSerializable> myMap = new TreeMap<Integer, AccountRecordSerializable>();
	
	
	void addToMap(AccountRecordSerializable newRecord){
			myMap.put(newRecord.getAccount(),newRecord);		
	}
	
	public AccountRecordSerializable getRecord(int accountNum){
		
		return myMap.get(accountNum);
		
		
	}
	
	void removeRecord(int oldKey){
		myMap.remove(oldKey);
	}
	
	void displayMap(){
		Set<Integer> keys = myMap.keySet();
		
		TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
		
		System.out.println("\nMap contains: \nAN\t\tFN\t\tLN\t\tAB");
		
		for(Integer key : sortedKeys)
			System.out.printf("%-10d%-10s%-10s%-10.2f",key,myMap.get(key).getFirstName(),myMap.get(key).getLastName(),myMap.get(key).getBalance());
	}
	
	
}
