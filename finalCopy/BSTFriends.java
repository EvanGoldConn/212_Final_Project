import java.util.*;  // for built-in Stack class
//for insert do less .comparedTo built in java string comparison 

    //have a BST of just string email address (key value)
    //to print out the name, key into the hash table using key value and show friends timeline post 
    //no need to have double the person objects 

public class BSTFriends{
	private BSTNode root;
	private int size;
	private int counter;
	private int i;
	private String postfixExp;
	private String[] friends;
	
	public BSTFriends(){
		size = 0;
		root = null;
		counter = 0;
		i = 0;
		postfixExp = "";
		friends = new String[2000];
	}
	


	public BSTNode root(){
		return root;
	}//end method

	public int size(){
		return size;
	}
	
	
	private void recInsert(String k, BSTNode v){
		if (k.compareTo(v.key()) >= 0){
			if(v.right() != null){
				recInsert(k, v.right());
			}
			else{
				v.setRight(new BSTNode(v, k, null, null));
			}
		}
		else if(k.compareTo(v.key()) < 0){
			if(v.left() != null){
				recInsert(k, v.left());
			}
			else{
				v.setLeft(new BSTNode(v, k, null, null));
			}
		}
				
	}//end method
	


	public void addFriend(String k){ //initalize new tree here 
		if (root == null){
			root = new BSTNode(null,k,null,null);
	
		}else{
			recInsert(k,root);
		}
		size++;	

	}//end method
	
	public BSTNode getMin(BSTNode v){
		BSTNode cur = v;
		while (cur.left() != null){
			cur = cur.left();
		}
		return cur;
	}
		
	public BSTNode remove(String k){
		BSTNode returnV;
		if (isEmpty()){
			return null;
		}
		else{
			BSTNode v = find(k, root);

			if (v == null){
				// System.out.println("can't remove, not found");
				return null;

			}
			returnV = v;

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
	
	
	public BSTNode find(String k, BSTNode v){
		if(v==null || v.key().equals(k)){ 
			// System.out.println("base case of find");
			return v;
		}else{
			// System.out.println("not found yet..");
			if (k.compareTo(v.key()) > 0){
				return find(k,v.right());	
			}else{
				return find(k,v.left());
			}
		}
	} //end method

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
			if (k.compareTo(v.key()) > 0){
				return findEmail(k,v.right());	
			}else{
				return findEmail(k,v.left());
			}
		}
	} //end method
	
	
	public void setCounter(){
		counter = 0;
	}
	
	public void inOrder(BSTNode v){
			if (v.left() != null){
				inOrder(v.left());
			}
			counter += 1;
			System.out.println(counter+"."+v.key());
			if (v.right() != null){
				inOrder(v.right());
			}
	}//end method

	public void setI(){
		i = 0;
	}
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

	public String[] returnList(){
		return friends;
	}



	public void printingFriends(BSTNode v){
		if (v.left() != null){
			printingFriends(v.left());
		}
		postfixExp += v.key()+"@conncoll.edu"+",";
		if (v.right() != null){
			printingFriends(v.right());
		}
	}//end method

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