public class TNode{
	private String post;
	private String creator;
	private TNode next;

	public TNode(String p, String c, TNode n){
		post = p;
		creator = c;
		next = n;

	}


	public TNode getNext(){
		return next;

	}

	public void setNext(TNode v){
		next = v;
	}

	public String getPost(){
		return post;
	}

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
