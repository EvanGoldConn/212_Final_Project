import java.util.Calendar;
import java.util.Scanner;
import java.io.*;
public class Person {

    private String firstName;
    private String lastName;
    private Events events;
    private int gradYear;
    private String email;
    private String pass;
    private Timeline timeline;
    private BSTFriends friends;

    public Person(String f, String l, String e, int y, String p) {
        firstName = f;
        lastName = l;
        // email.newEmail(e);
        email = e;
        events = new Events();
        // gradYear.setYear(y);
        gradYear = y;
        // pass.newPassword(p);
        pass = p;
        timeline = new Timeline();
        friends = new BSTFriends();
    }

    

    public String getFullName() {
         return firstName + " " + lastName;
     }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getPassword(){
        return pass;
    }

    public String getEmail(){
        return email;
    }

    // ======= Used for File Writing ONLY ======== \\
    public Timeline getTimeline() {
         return timeline;
     }

     public Events getPersonEvent(){
        return events;
    }
    public BSTFriends getPersonFriends(){
        return friends;
    }

  

    // ============================================\\

    public int getYear(){
        return gradYear;
    }



    public String getEmailKey() {
        int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){
            emailKey += email.charAt(i);
            i++;
        }
        return emailKey;
    }

    private String returnEmailKey(String inputEmail){
        int i = 0;
        String emailKey = "";
        while(inputEmail.charAt(i) != '@'){
            // System.out.println(inputEmail.charAt(i));
            emailKey += inputEmail.charAt(i);
            i++;
        }
        return emailKey;
    }

    // ================= Timeline manipulation ================== \\

    public void addTimelinePost(String v, String c) {
        timeline.addPost(v, c);
    }

    public void displayTimeline(int n) {
        timeline.displayTimeline(n);
    }
    

    public void loginTimeline() {
        timeline.loginTimeline();
    }
    // ======================== BSTFriends manipulation ============== \\
    
    public void addFriend(String email) {
        String emailKey = returnEmailKey(email);
        //how do i check if email exists in hash table... don't think I can rip 

        friends.addFriend(emailKey);
    }

    public String findFriend(String key) {
        String friend = friends.findEmail(key, friends.root());
        return friend;
    }

    public void removeFriend(String email) {
        String emailKey = returnEmailKey(email);
        // System.out.println("key"+" "+emailKey);
        String tester = friends.findEmail(emailKey, friends.root());
        if(tester != null){
            friends.remove(emailKey);
        }
        else{
            System.out.println("|--------------------|");
            System.out.println("| Account Not Found. |");
            System.out.println("|--------------------|");
        }
    }

    public void printFriends(){
        if(friends.size() > 0){
            friends.setCounter();
            friends.inOrder(friends.root());
        }
        else{
            System.out.println("|------------|");
            System.out.println("| No Friends |");
            System.out.println("|------------|");
        }
    }
    public void showFriends(HashTable table){
        if(friends.size() > 0){
            friends.setCounter();
            friends.returnInOrder(friends.root());
            String[] listFriends = friends.returnList();



            for(int i = 0; i < listFriends.length; i++){
                if(listFriends[i] != null){
                    Person friend = table.forEmail(listFriends[i]); //returns Person object of current friend

                    

                    System.out.println("\n\n"+friend.getFullName()+":");
                    System.out.println("Latest Timeline Post:");
                    friend.displayTimeline(1);

                    System.out.println("Next Event:");
                    friend.displayEvents();
                    boolean post = true;
                    while(post){
                        Scanner question = new Scanner(System.in);
                        String answer = "";
                        System.out.print("Do you wish to post to your friends timeline? (y/n)");
                        answer = question.next();
                        answer = answer.toLowerCase();

                        if(answer.equals("y")){
                            String details = "";
                            System.out.print("What do you wish to post?");
                            details = question.next();

                            friend.addTimelinePost(details, getFullName());
                            post = false;
                        }
                        else if(answer.equals("n")){
                            post = false;
                        }

                    }

                }
            }
        }
        else{
            System.out.println("|------------|");
            System.out.println("| No Friends |");
            System.out.println("|------------|");
        }
    }

    // ---------------------------------------------------------------- \\
    // ===================== event manipulation ==================== \\
    
    public void insertPersonalEvent(Calendar date, String event, int month, int day, int year, int hour, int minutes) {
        events.addEvent(date, event, month, day, year, hour, minutes);
    }


    public void displayEvents() {
        events.displayEvents();
    }

    
}

class PersonTest {
    public static void main(String[] args){
    Person me = new Person("Justin", "T", "jturbevil", 2022, "password");
    Person me2 = new Person("Evan", "G", "eg@", 2022, "p'");
    Person me3 = new Person("B", "G", "bg@", 2021, "f");



    me.addFriend("egold@");
    me.addFriend("bgould@");
    // me.showFriends();
    System.out.print(me.findFriend("egold@"));
    // me.removeFriend("eg@");
    // me.showFriends();
}























}
