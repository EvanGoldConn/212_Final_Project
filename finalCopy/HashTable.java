import java.io.*;
public class HashTable{
	private Person[] table;
	private int N;
	private int con;
	private char[] letters;

	public HashTable(){
		N = 26;
		table = new Person[4000];
		con = 4001;
		letters = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	}


	private int helper(char c){
		int code = 0;
		for(int i = 1; i < 26; i++){
			if(c == letters[i]){
				code = i;
				i = 28;
			}

		}
		return code;


	}
	

	public int hornerHash(String s){
		String lowerCase = s.toLowerCase();
		int hashVal = 0;
		int exp = lowerCase.length()-1;
		for(int i = 0; i < lowerCase.length(); i++){
			hashVal = hashVal + helper(s.charAt(i))*(26^exp);
			exp--;
		}
		return (hashVal%con);
	}


	public int getSize(){
		return 4000;
	}

	public Person findPerson(String email, String password){ //login using email and password, do hornerHash method, if account found then crosscheck passwords, if correct pass, grant access
		int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){
            emailKey += email.charAt(i);
            i++;
        }
		int hashKey = hornerHash(emailKey);
		Person foundPerson = table[hashKey];
		if(foundPerson!= null){
			if(foundPerson.getEmail().equals(email)) {
				if(foundPerson.getPassword().equals(password)){
					return foundPerson;
				}
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

	//for admin login
	public void showAccounts(){
		System.out.println("Accounts:");
		for(int i = 0; i < table.length; i++){
			if(table[i] != null)
				System.out.println("- Account"+i+": "+table[i].getEmail());
		}
	}

	public Person[] hashTable(){
		return table;
	}

	public void addPerson(Person person){
		String key = person.getEmailKey();
		int hashKey = hornerHash(key);
		
		while(table[hashKey] != null) {
			hashKey = (hashKey + 1)%con;
		}
		table[hashKey] = person;
	

	}

	public void deleteAccount(String email){
		int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){
            emailKey += email.charAt(i);
            i++;
        }
		int hashKey = hornerHash(emailKey);
		System.out.println(table[hashKey]);


		while(table[hashKey] == null){
			hashKey = (hashKey + 1)%con;
		}
		if(table[hashKey].getEmail().equals(email)){
			table[hashKey] = null;
		}
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
