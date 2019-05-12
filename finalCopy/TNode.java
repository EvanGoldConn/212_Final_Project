public class TNode{
	private String post;
	private String creator;
	private TNode next;

	public TNode(String p, String c, TNode n){
		post = p;
		creator = c;
		next = n;

	}

	/**
	* @return 
	*	TNode that holds the next node 
	*/
	public TNode getNext(){
		return next;

	}
	/**
	* @param v 
	*	TNode that will be set as the next node of the current node 
	*/
	public void setNext(TNode v){
		next = v;
	}
	/**
	* @return 
	*	String that holds the details of the current node 
	*/
	public String getPost(){
		return post;
	}
	/**
	* @return 
	*	String that holds the creators name 
	*/
	public String getCreator(){
		return creator;
	}

	
}

class mainMethod{ //testing TNode class
	public static void main(String[] args){
		TNode test = new TNode("hello", "Evan", null);
		TNode test1 = new TNode("Goodbye", "Henry", null);
		test.setNext(test1);
		System.out.println(test.getNext().getPost());
	}
}
