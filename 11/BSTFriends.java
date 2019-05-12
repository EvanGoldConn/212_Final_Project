import java.util.*;  // for built-in Stack class
//for insert do less .comparedTo built in java string comparison 

    //have a BST of just string email address (key value)
    //to print out the name, key into the hash table using key value and show friends timeline post 
    //no need to have double the person objects 

/**
 * BSTFriends.java uses an array based priority and maintains fields for 
 * the root node, size, counter (for returning friends), an integer i (for
 * returning friends), a string, postfixExp, that stores the friends being returned, 
 * and an array that stores the priority queue.
 */
public class BSTFriends{
	private BSTNode root;
	private int size;
	private int counter;
	private int i;
	private String postfixExp;
	private String[] friends;
	
	/**
	 * Constructor creates empty priority
	 * queue that stores the email addresses
	 * of the person's friends
	 */
	public BSTFriends(){
		size = 0;
		root = null;
		counter = 0;
		i = 0;
		postfixExp = "";
		friends = new String[2000];
	}
	

	/**
	 * @return
	 * 		the root/first friend in the priority queue
	 */
	public BSTNode root(){
		return root;
	}//end method

	/**
	 * @return
	 * 		size of the priority queue
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Private class that actually adds a friend 
	 * to the priority queue using recursion when
	 * addFriend() is called.
	 * @param k
	 * 		email address of friend being added
	 * @param v
	 * 		root node where recursion begins
	 */
	private void recInsert(String k, BSTNode v){
		if (k.compareTo(v.key()) >= 0){  // sorts friends alphabetically using compareTo (right is later in alphabet)
			if(v.right() != null){   // recursion passing in right child of current node
				recInsert(k, v.right());
			}
			else{  // friend is inserted as right child if null
				v.setRight(new BSTNode(v, k, null, null));
			}
		}
		else if(k.compareTo(v.key()) < 0){  // second case ensures friend is not already in the pq
			if(v.left() != null){  // friend comes before (in the left subtree) current node alphabetically
				recInsert(k, v.left());
			}
			else{  // friend is inserted as left child if null
				v.setLeft(new BSTNode(v, k, null, null));
			}
		}
				
	}//end method
	

	/**
	 * public class that can be used to add a friend the a person's priority queue
	 * @param k
	 * 		email address of friend being added
	 */
	public void addFriend(String k){  // public class where starting node is not passed in
		if (root == null){  // if root is null, friend can be inserted there without calling recInsert()
			root = new BSTNode(null,k,null,null);
	
		}else{
			recInsert(k,root);
		}
		size++;	

	}//end method
	
	/**
	 * retrieves node with smallest key
	 * @param v
	 * 		node where getMin() begins traversing left
	 * @return
	 * 		node with lowest key
	 */
	public BSTNode getMin(BSTNode v){
		BSTNode cur = v;
		while (cur.left() != null){  // the minimum key is always the left most leaf
			cur = cur.left();  //  recursively moves left until leaf is reached
		}
		return cur;
	}
	
	/**
	 * removes a friend from a person's priority queue
	 * @param k
	 *  	email address of friend being removed
	 * @return
	 *  	node being removed
	 */
	public BSTNode remove(String k){
		BSTNode returnV;
		if (isEmpty()){  // precondition - must be node in tree
			return null;
		}
		else{
			BSTNode v = find(k, root);  // uses find method to locate node being removed

			if (v == null){  // if key is not found in tree
				// System.out.println("can't remove, not found");
				return null;

			}
			returnV = v;  // node being removed must be returned

			if(v.left() == null && v.right() == null) { // CASE 1: if v is a leaf, no children
				replace(v,null);	
				// System.out.println("first case remove");	
			}
			//CASE 2: V has 1 child
			else if(v.left() != null && v.right() == null){ //if v has 1 left child
				replace(v, v.left());
				// System.out.println("second case remove..");
			}
			else if(v.left() == null && v.right() != null){//if v has 1 right child
				replace(v, v.right());
				// System.out.println("second case remove..");
			}

			else if(v.left() != null && v.right() != null){ //CASE 3: V has 2 children 
				BSTNode successor = getMin(v.right());
				v.setKey(successor.key());
				replace(successor, successor.right());
				// System.out.println("third case remove..");

			}

			size--;
			return returnV;
		}
	}

	/**
	 * Method replaces a node in the tree with another node
	 * that is being added to the tree (used for remove())
	 * @param v
	 * 		node in tree being replaced
	 * @param k
	 * 		node being added to tree
	 */
	private void replace(BSTNode v, BSTNode k){
		if(v == root){
			root = k;
			if (k != null){
				k.setParent(null);
			}
		}
		else{ //if v has a parent
			if (v.parent().left() == v) { //if v is a left child
				v.parent().setLeft(k); //replace node with the parent
			}
			else{ //if v is a right child
				v.parent().setRight(k);
			}
			if (k != null){
				k.setParent(v.parent());
			}
		}

	}
	
	/**
	 * Method finds a node in the tree
	 * @param k
	 * 		key of the node being found
	 * @param v
	 * 		node where find() starts
	 * @return
	 * 		node found
	 */
	public BSTNode find(String k, BSTNode v){
		if(v==null || v.key().equals(k)){  // if tree is empty or the root node has the key
			// System.out.println("base case of find");
			return v;
		}else{
			// System.out.println("not found yet..");
			if (k.compareTo(v.key()) > 0){  // if the string k comes after node v's key in the alphabet
				return find(k,v.right());	// search v's right subtree
			}else{
				return find(k,v.left());  // search v's left subtree because string k is before v.key() in the alphabet
			}
		}
	} //end method

	/**
	 * Finds and returns the email address of a node in the tree
	 * @param k
	 * 		email desired
	 * @param v
	 * 		node where findEmail() starts search
	 * @return
	 *  	email address found
	 */
	public String findEmail(String k, BSTNode v){
		if(v==null){ 
			// System.out.println("base case of find");
			return null;
		}
		else if(v.key().equals(k)){
			return v.key();
		}
		else{
			// System.out.println("not found yet..");
			if (k.compareTo(v.key()) > 0){  // if the string k comes after node v's key in the alphabet
				return findEmail(k,v.right());	// search v's right subtree
			}else{
				return findEmail(k,v.left());  // search v's left subtree because string k is before v.key() in the alphabet
			}
		}
	} //end method
	
	
	public void setCounter(){
		counter = 0;
	}
	
	/**
	 * Traverses tree inorder so that a list of friends
	 * can be displayed by the user
	 * @param v
	 * 		node where traversal starts
	 */
	public void inOrder(BSTNode v){
			if (v.left() != null){  // visits every friend that's a left child until null
				inOrder(v.left());
			}
			counter += 1;  // outputs leaf node when left is null
			System.out.println(counter+"."+v.key());  // traverses right children
			if (v.right() != null){
				inOrder(v.right());
			}
	}//end method

	public void setI(){
		i = 0;
	}

	/**
	 * Similarto inOrder() except method creates fills an array
	 * of friends to be returned
	 * @param v
	 * 		node where traversal starts
	 */
	public void returnInOrder(BSTNode v){
		if(v.left() != null){
			returnInOrder(v.left());
		}
		if(v.key() != null){
			friends[i] = v.key();
			i++;
		}
		if(v.right() != null){
			returnInOrder(v.right());
		}

	}

	/**
	 * @return
	 * 		list of friends created using returnInOrder()
	 */
	public String[] returnList(){
		return friends;
	}


	/**
	 * creates a list of friends used when writing daqta to a text file
	 * @param v
	 * node where traversal starts
	 */
	public void printingFriends(BSTNode v){
		if (v.left() != null){
			printingFriends(v.left());
		}
		postfixExp += v.key()+"@conncoll.edu"+",";
		if (v.right() != null){
			printingFriends(v.right());
		}
	}//end method

	/**
	 * returns string of friends created using printingFriends()
	 * @return
	 */
	public String returnString(){
		return postfixExp;
	}
	
	public Boolean isEmpty(){
		return (root == null);
	}

	public void emptyString(){
		postfixExp = "";
	}


}



class BSTFriendsTest{
	public static void main(String[] args){
		BSTFriends test = new BSTFriends();
		// test.addFriend("bgould");
		test.addFriend("egoldsmi");
		test.addFriend("zbeucler");
		String name = "wtarimo";
		test.addFriend(name);
		test.inOrder(test.root());
		String name2 = "wtarimo";
		test.remove(name2);
		test.inOrder(test.root());

	}





}
