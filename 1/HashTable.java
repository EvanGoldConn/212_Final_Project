import java.io.*;
public class HashTable{
	private Person[] table;
	private int N;
	private char[] letters;

	public HashTable(){
		N = 26;
		table = new Person[2000];
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
	

	private int hornerHash(String s){
		String lowerCase = s.toLowerCase();
		int hashVal = 0;
		int exp = lowerCase.length()-1;
		for(int i = 0; i < lowerCase.length(); i++){
			hashVal = hashVal + helper(s.charAt(i))*(26^exp);
			exp--;
		}
		return (hashVal%N);
	}


	public int getSize(){
		return N;
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
			if(foundPerson.getPassword().equals(password)){
				return foundPerson;
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
		table[hashKey] = person;
			

	}

	public void deleteAccount(Person person){
		String key = person.getEmailKey();
		int hashKey = hornerHash(key);
		table[hashKey] = null;
	}

}


class hashTest{
	public static void main(String[] args){
		// HashTable test = new HashTable();
		// System.out.println(test.hornerHash("egoldsmi"));
		// System.out.println(test.hornerHash("jturbevil"));
		// System.out.println(test.hornerHash("hdecoster"));
		// System.out.println(test.hornerHash("jtien"));
		// System.out.println(test.hornerHash("smewada"));
		// System.out.println(test.hornerHash("lhigdon"));
		// System.out.println(test.hornerHash("dhunter"));
		// System.out.println("z"+test.hornerHash("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));







	}
}
