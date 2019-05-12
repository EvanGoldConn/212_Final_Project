import java.util.Scanner;

/*creates and displays user's timeline*/

public class Timeline{
	/*fields for timeline (singly linked list)*/
	private TNode head;
	private TNode tail;
	private int size;

	/*constructor method*/
	public Timeline(){
		head = null;
		tail = null;
		size = 0;
	}

	/*returns the head of the linked list*/
	public TNode getHead() {
		return head;
	}

	/**returns the size of the linked list*/
	public int getSize() {
		return size;
	}

	/**
	 * method addPost, adds a post to users timeline
	 * @param v,c
	 *  	String v is post context
	 * 		String c is post creator
	 */
	public void addPost(String v, String c){
		TNode u = new TNode(v, c, null);
		if(isEmpty()){
			head = u;
		}
		else if (!isEmpty()){
			TNode temp = head;
			head = u;
			head.setNext(temp);
		}
		size++;
	}	

	/**
	 * method isEmpty, checks if size is 0
	 * @return 
	 *	boolean, true if size == 0
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * method displayTimeline, shows n posts 
	 * that the user inputs
	 * @param n
	 *	number of posts the user requests to view
	 */ 
	public void displayTimeline(int n){
		TNode cur = head;
		if (isEmpty()){
			System.out.println("User timeline is empty");
		}
		else{  //if timeline is populated
			int i = 0;
			while(i < n){ //loops through all timeline posts
				if (cur != null){
					//prints out the post and the creator
					System.out.println(cur.getPost()+" "+"\n \tPosted By: "+cur.getCreator());
					cur = cur.getNext(); //iterating through linked list
				}
			i++;

			}
		}
	}


	/**
	 * method loginTimeline, displays 3 posts for user to see upon login
	 * @return none
	 */
	public void loginTimeline(){
		TNode cur = head;
		if (isEmpty()){
			System.out.println("User timeline is empty");
		}
		else{
			for (int i = 0; i < 4; i++){
				if (cur != null){
					System.out.println(cur.getPost()+" "+"\n \tPosted By: "+cur.getCreator());
					cur = cur.getNext();
				}
			}
		}
	}

	/**
	 * method friendTimeline, posts friend's most recent timeline post
	 * @return none
	 */
	public void friendTimeline(){ 
		TNode cur = head;
		if (isEmpty()){
			System.out.println("Friend's timeline is empty");
		}
		else{
			System.out.println(cur.getPost());
		}
	}

	/**
	 * method deletePost, deletes a Post from the timeline
	 * @return none
	 */
	public void deletePost(){ 
		TNode cur = head;
		TNode lastNode = null;
		if (isEmpty()){
			System.out.println("No posts found. Timeline is empty.");
		}
		else{ //if the timeline is populated
			for(int i = 0; i < size; i++){ //loops through all posts, asking user about each of them
				if (cur!= null){ //asks user if they would like to delete a specific post
					System.out.println(cur.getPost());
					Scanner answer = new Scanner(System.in); //user input for main menu
					System.out.println("Would you like to delete this post? \n Yes (y) | No, next post (n)");
					char userAnswer = answer.next().charAt(0);

					if(userAnswer == 'y'){
						if(lastNode == null){
							head = cur.getNext();
						}
						else{
							lastNode.setNext(cur.getNext());

						}
					}
					else if(userAnswer == 'n'){
						lastNode = cur;
						cur = cur.getNext();

					}
					
				}
				
			}

		}

	}

}

class timelineTest{ //test class for the timeline
	public static void main(String[] args){
		Timeline test = new Timeline();
		test.addPost("Can't wait to eat Ice Cream!", "Evan");
		test.addPost("Great giants win last night. Let's GOOO!!!", "Henry");
		test.addPost("Test", "Me");
		test.addPost("Yuhhhh", "Sang");
		System.out.println("display1");
		test.displayTimeline(4);
		// test.loginTimeline();
		// test.friendTimeline();
		test.deletePost();
		System.out.println("display2");
		test.displayTimeline(4);





	}
}
