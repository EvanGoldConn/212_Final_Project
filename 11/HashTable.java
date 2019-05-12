import java.io.*;

/**
 * This class implements a hash table with an array field that acts as the hash, 
 * an integer N that is used to key the hash, an integer con that is used for modular division,
 * and a list of letters in the alphabet used to key the hash
 */
public class HashTable{
	private Person[] table;
	private int N;
	private int con;
	private char[] letters;
	
	/**
	 * Constructor creates an empty hash table
	 */
	public HashTable(){
		N = 26;
		table = new Person[4000];
		con = 4001;
		letters = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	}

	/**
	 * Method that returns the "ascii" value of a letter in the alphabet
	 * @param c
	 * @return
	 */
	private int helper(char c){
		int code = 0;
		for(int i = 1; i < 26; i++){
			if(c == letters[i]){  // if c is the same as the letter at index i in the array
				code = i;  // save index of "ascii value"
				i = 28;
			}

		}
		return code;  // return "ascii value"


	}
	
	/**
	 * Method take a string/email address and uses helper()
	 * in order to formulate a hashkey
	 * @param s
	 * @return
	 */
	public int hornerHash(String s){
		String lowerCase = s.toLowerCase();
		int hashVal = 0;
		int exp = lowerCase.length()-1;
		for(int i = 0; i < lowerCase.length(); i++){  // uses every character in email address
			hashVal = hashVal + helper(s.charAt(i))*(26^exp);  // adds the "ascii value" calculated using helper
			exp--;                                             // and the formula in the line above
		}
		return (hashVal%con);
	}


	public int getSize(){
		return 4000;
	}

	/**
	 * Method takes strings for email and password and returns 
	 * the person object that matches in the hash table (if found).
	 * Used when a user logs in to their account.
	 * @param email
	 * 		email address of desired person
	 * @param password
	 * 		password string
	 * @return
	 * 		person object matching credentials
	 */
	public Person findPerson(String email, String password){ //login using email and password, do hornerHash method, if account found then crosscheck passwords, if correct pass, grant access
		int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){  // adds up all the ascii values of the letters in email address
            emailKey += email.charAt(i);
            i++;
        }
		int hashKey = hornerHash(emailKey);  // uses hornerHash() to get the key or index where the person is/would be located
		Person foundPerson = table[hashKey];
		if(foundPerson!= null){  // if there is a person object stored at the index
			if(foundPerson.getEmail().equals(email)) {  // is it the person object with the dredentials passed in
				if(foundPerson.getPassword().equals(password)){
					return foundPerson;
				}
			}
			else {  
				/**
				 * person is incorrect, and the program must make sure the person
				 * is not at a different index due to probing
				 */
				hashKey++;
				foundPerson = table[hashKey];
				while(foundPerson != null) {
					foundPerson = table[hashKey];
					if(!foundPerson.getEmail().equals(email)) {  
						hashKey++;
					}
					else {
						return foundPerson;
					}
				}
			}
						
		}
		return null;
	}

	/**
	 * Performs same task as findPerson() except the password 
	 * of the person is not needed
	 * @param email
	 * 		email address
	 * @return
	 * 		person object matching email
	 */
	public Person forEmail(String email){
		int hashKey = hornerHash(email);
		Person foundPerson = table[hashKey];
		if(foundPerson!= null){
			if(foundPerson.getEmailKey().equals(email)) {
				return foundPerson;
			}
			else {
				hashKey++;
				foundPerson = table[hashKey];
				while(foundPerson != null) {
					foundPerson = table[hashKey];
					if(!foundPerson.getEmail().equals(email)) {
						hashKey++;
					}
					else {
						return foundPerson;
					}
				}
			}
						
		}
		return null;

	}

	/**
	 * for admin login
	 * Displays every account on the system
	 */
	public void showAccounts(){
		System.out.println("Accounts:");
		for(int i = 0; i < table.length; i++){
			if(table[i] != null)
				System.out.println("- Account"+i+": "+table[i].getEmail());
		}
	}

	/**
	 * @return
	 * 		hash table of person objects
	 */
	public Person[] hashTable(){
		return table;
	}

	/**
	 * adds a person to the hash table
	 * @param person
	 * 		person object being added
	 */
	public void addPerson(Person person){
		String key = person.getEmailKey();  // person must be added at correct hash key index
		int hashKey = hornerHash(key);
		
		while(table[hashKey] != null) {  // linear probing
			hashKey = (hashKey + 1)%con;
		}
		table[hashKey] = person;  //  adds person
	

	}

	/**
	 * Deletes an account from the hash table
	 * @param person
	 * 		person object being deleted
	 */
	public void deleteAccount(Person person){
		String key = person.getEmailKey();  // index or key of person being deleted needed
		int hashKey = hornerHash(key);
		if(!table[hashKey].equals(person.getEmail())) {  // conditional needed in case of probing
			hashKey++;
			person = table[hashKey];
			while(!table[hashKey].equals(person.getEmail())) {  // loops until person is found in event of probing
				hashKey = (hashKey + 1)%con;
			}
		}
		table[hashKey] = null;  // deletes person
	}

}


class hashTest{
	public static void main(String[] args){
		HashTable test = new HashTable();
		System.out.println(test.hornerHash("egoldsmi"));
		// System.out.println(test.hornerHash("jturbevil"));
		System.out.println(test.hornerHash("hdecoster"));
		// System.out.println(test.hornerHash("jtien"));
		// System.out.println(test.hornerHash("smewada"));
		// System.out.println(test.hornerHash("lhigdon"));
		// System.out.println(test.hornerHash("dhunter"));
		// System.out.println("z"+test.hornerHash("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));







	}
}
