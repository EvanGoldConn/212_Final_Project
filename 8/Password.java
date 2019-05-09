public class Password {
	private String password;
	
	public Password() {
		password = "";
	}
		
	public String getPassword() {
		return password;
	}
	
	public void newPassword(String p) {
		//Scanner newP = new Scanner(System.in);
		//System.out.println("Enter a new password.");
		password = p;
	}
	
	public void changePassword(String newP) {
		/*Scanner changeP = new Scanner(System.in);  //should go in the main method
							//something similar for the email too
		System.out.println("Enter your current password to change it.");
		if (changeP == password){
			Scanner change = new Scanner(System.in);
			System.out.println("Change password to: ");
			password = change.next();
		}
		else {
			System.out.println("Incorrect password.");
			changePassword(); */
		password = newP;
		System.out.print("Your password was successfully changed to: " + password);
		// }
	}
}
