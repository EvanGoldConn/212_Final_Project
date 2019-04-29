import java.util.Calendar;
public class Person {

    private String firstName;
    private String lastName;
    public PriorityQueue events;
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
        events = new PriorityQueue();
        // gradYear.setYear(y);
        gradYear = y;
        // pass.newPassword(p);
        pass = p;
        timeline = new Timeline();
        friends = new BSTFriends();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // public String getEmail() {
    //     return email.getEmail();
    // }

    // public String getEmailKey() {
    //     return email.getKey();
    // }
    

    // public int getYear() {
    //     return gradYear.getYear();
    // }

    // public String getPassword() {
    //     return pass.getPassword();
    // }

    public String getEmail(){
        return email;
    }

    public int getYear(){
        return gradYear;
    }

    public String getPassword(){
        return pass;
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

    public void addTimelinePost(String v, String c) {
        timeline.addPost(v, c);
    }

    public void displayTimeline(int n) {
        timeline.displayTimeline(n);
    }

    public void loginTimeline() {
        timeline.loginTimeline();
    }

    public void addFriend(String email) {
        int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){
            emailKey += email.charAt(i);
            i++;
        }
        friends.addFriend(emailKey);
    }

    public void findFriend(String key) {
        friends.find(key, friends.root());
    }

    public void removeFriend(String key) {
        friends.remove(key);
    }

    public void showFriends(){
        friends.inOrder(friends.root());
    }

    public void insertEvent(Calendar date, String event) {
        events.insertEvent(date, event);
    }

    public void displayEvents() {
        events.displayEvents();
    }
}

class PersonTest {
    Person me = new Person("Justin", "T", "jturbevil", 2022, "password");
}
