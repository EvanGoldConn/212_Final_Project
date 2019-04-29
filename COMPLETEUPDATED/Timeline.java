import java.util.Scanner;
public class Timeline{
	private TNode head;
	private TNode tail;
	private int size;

	public Timeline(){
		head = null;
		tail = null;
		size = 0;
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
		//add something in main method that loops 'while n is !> size'
		//if condition breaks the user inputted too much stuff 

		TNode cur = head;
		if (isEmpty()){
			System.out.println("User timeline is empty");
		}
		else{
			int i = 0;
			while(i < n){
				if (cur != null){
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


	public void friendTimeline(){ 
		TNode cur = head;
		if (isEmpty()){
			System.out.println("Friend's timeline is empty");
		}
		else{
			System.out.println(cur.getPost());
		}
	}

	public void deletePost(){ 
		TNode cur = head;
		TNode lastNode = null;
		if (isEmpty()){
			System.out.println("No posts found. Timeline is empty.");
		}
		else{
			for(int i = 0; i < size; i++){
				if (cur!= null){
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

class timelineTest{
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
