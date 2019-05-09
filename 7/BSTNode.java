public class BSTNode{
	private String eKey;
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	
	public BSTNode(BSTNode p, String k, BSTNode l, BSTNode r){
		eKey = k;
		left = l;
		right = r;
		parent = p;
	}
	
	public String key(){
		return eKey;
	}
	
	public BSTNode left(){
		return left;
	}
	
	public BSTNode right(){
		return right;
	}
	
	public BSTNode parent(){
		return parent;
	}
	
	public void setLeft(BSTNode v){
		left = v;
	}
	
	public void setRight(BSTNode v){
		right = v;
	}
	
	public void setParent(BSTNode v){
		parent = v;
	}

	public void setKey(String k){
		eKey = k;
	}

	public void displayNode(){
		System.out.println(eKey);
	}
}//end class