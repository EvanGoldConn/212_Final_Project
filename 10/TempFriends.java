public class TempFriends {
    private Person[] friends;
    private int size;
    //for insert do less .comparedTo built in java string comparison 

    //have a BST of just string email address (key value)
    //to print out the name, key into the hash table using key value and show friends timeline post 
    //no need to have double the person objects 
    public TempFriends() {
        friends = new Person[1000];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Person[] getFriends() {
        return friends;
    }

    public void displayFriends() {
        System.out.println("Friends:\n");
        for(int i = 0;i < size;i++) {
            System.out.println(friends[i].getFirstName()+" "+friends[i].getLastName()+"\n");
        }
    }

    public void addFriend(Person p) {
        if(size == 0) {
            friends[0] = p;
        }

        else {
            String nextFriend = friends[0].getLastName();
            int i = 0;
            while(p.getLastName().compareToIgnoreCase(nextFriend) > 0) {
                i++;
                nextFriend = friends[i].getLastName();
            }
            int z = size - 1;
            while(z >= i) {
                friends[z+1] = friends[z];
            }
            friends[i] = p;
        }
    }

    public int find(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        for(int i = 0;i < size;i++) {
            String name = friends[i].getFirstName() + " " + friends[i].getLastName();
            if(fullName == name) {
                return i;
            }
        }
        System.out.println("Oops! Person not found.");
        return -1;   
    }

    public Person remove(String firstName, String lastName) {
        int index = find(firstName, lastName);
        if(index == -1) {
            return null;
        }
        else {
            Person temp = friends[index];
            while(index < size) {
                friends[index] = friends[index + 1];
                index++;
            }
            return temp;
        }
    }
}

class FriendsTest {
    
    public static void main(String[] args) {

        // Person logan = new Person("Logan", "Waien");
        // Person evan = new Person("Evan", "Goldsmith");
        // logan.friends.addFriend(evan);
        // logan.friends.displayFriends(); 
        /** t.addFriend("Justin", "Turbeville");
        t.addFriend("Brian", "CS");
        t.displayFriends();
        t.remove("Christine", "Chung");
        t.remove("Brian", "CS");
        t.displayFriends();*/
    }
}
