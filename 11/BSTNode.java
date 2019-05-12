/**
 * Node class used with BST friends which is a priority queue that stores friends.
 * Each node has a string field that stores the email address of the friend, as a string and 
 * fields for left, right, and parent nodes.
 */

public class BSTNode{
	private String eKey;
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	
	/**
	 * Constructor creates a new BSTNode
	 * @param p
	 * 		parent BSTNode
	 * @param k
	 * 		email address
	 * @param l
	 * 		left child BSTNode
	 * @param r
	 * 		right child BSTNode
	 */
	public BSTNode(BSTNode p, String k, BSTNode l, BSTNode r){
		eKey = k;
		left = l;
		right = r;
		parent = p;
	}
	
	/**
	 * @return
	 * 		key of node
	 */
	public String key(){
		return eKey;
	}
	
	/**
	 * @return
	 * 		left child
	 */
	public BSTNode left(){
		return left;
	}
	
	/**
	 * @return
	 * 		right child
	 */
	public BSTNode right(){
		return right;
	}
	
	/**
	 * @return
	 * 		parent node
	 */
	public BSTNode parent(){
		return parent;
	}
	
	/**
	 * sets left child
	 * @param v
	 * 		node being set to left child
	 */
	public void setLeft(BSTNode v){
		left = v;
	}
	
	/**
	 * sets right child
	 * @param v
	 * 		node being set to right child
	 */
	public void setRight(BSTNode v){
		right = v;
	}
	
	/**
	 * sets parent node
	 * @param v
	 * 		node being set to parent
	 */
	public void setParent(BSTNode v){
		parent = v;
	}

	/**
	 * sets key of the node
	 * @param k
	 * 		string being set to key
	 */
	public void setKey(String k){
		eKey = k;
	}

	/**
	 * prints out the key of the node
	 */
	public void displayNode(){
		System.out.println(eKey);
	}
}//end class
