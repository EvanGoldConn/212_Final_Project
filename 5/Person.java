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
        
        // System.out.println("addKey"+" "+emailKey);
        friends.addFriend(emailKey);
    }

    public String findFriend(String key) {
        String friend = friends.findEmail(key, friends.root());
        return friend;
    }

    public void removeFriend(String email) {
        String emailKey = returnEmailKey(email);
        System.out.println("key"+" "+emailKey);
        friends.remove(emailKey);
    }

    public void showFriends(){
        friends.setCounter();
        friends.inOrder(friends.root());
    }

    // ---------------------------------------------------------------- \\

    public void insertPersonalEvent(Calendar date, String event) {
        events.addEvent(date, event);
    }

    public void displayEvents() {
        events.displayEvents();
    }

    

    // public void fileWriter(Person[] hashTable){
    //     Person[] personArray = hashTable.hashTable();
    //     for(int i = 0;i < personTable.getSize();i++) {
    //         if(personArray[i] != null) {
    //             Person p = personArray[i];
    //             String data = p.getFullName() + "\n" + p.getEmail() + "\n" + p.getPassword() + p.getYear() + "\n";
    //             ENode curE = p.events.getHead();
    //             for(int j = 0;j < p.events.getSize();i++) {
    //                 data += curE.getDate() + " " + curE.getEvent() + ",";  //need gettimeinmillis?????
    //                 curE = curE.getNext();
    //             }
    //             data += "\n";
    //             TNode curT = p.getTimeline().getHead();
    //             for(int k = 0;k < p.getTimeline().getSize();i++) {
    //                 data += curT.getPost() + ",";
    //             }
    //             data += "\n" + p.getFriends.postOrderTrav();
    //             try {
    //                 FileWriter fileOut = new FileWriter("userdata.txt");
    //                 BufferedWriter bufWriter = new BufferedWriter(fileOut);
    //                 bufWriter.write(data);
                    
    //                 bufWriter.close();
    //                 fileOut.close();
    //             } 
                
    //             catch (IOException e) {
    //                 e.printStackTrace();
    //             }



    //         }
            
    //     }
    // }
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