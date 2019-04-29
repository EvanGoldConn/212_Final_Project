import java.util.*;  // for built-in Stack class
//for insert do less .comparedTo built in java string comparison 

    //have a BST of just string email address (key value)
    //to print out the name, key into the hash table using key value and show friends timeline post 
    //no need to have double the person objects 

public class BSTFriends{
	private BSTNode root;
	private int size;
	
	public BSTFriends(){
		size = 0;
		root = null;
	}
	


	public BSTNode root(){
		return root;
	}//end method
	
	
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
				System.out.println(5);
				return null;

			}
			returnV = v;

			if(v.left() == null && v.right() == null) { // CASE 1: if v is a leaf, no children
				replace(v,null);	
				System.out.println(1);	
			}
			//CASE 2: V has 1 child
			else if(v.left() != null && v.right() == null){ //if v has 1 left child
				replace(v, v.left());
				System.out.println(2);
			}
			else if(v.left() == null && v.right() != null){//if v has 1 right child
				replace(v, v.right());
				System.out.println(3);
			}

			else if(v.left() != null && v.right() != null){ //CASE 3: V has 2 children 
				BSTNode successor = getMin(v.right());
				v.setKey(successor.key());
				replace(successor, successor.right());
				System.out.println(4);
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
		if(v==null || v.key() == k){ //why is V null?? are we adding things wrong? 
			System.out.println(6);
			return v;
		}else{
			if (k.compareTo(v.key()) > 0){
				return find(k,v.right());	
			}else{
				return find(k,v.left());
			}
		}
	} //end method
	
	
	
	
	public void inOrder(BSTNode v){
		if (v.left() != null){
			inOrder(v.left());
		}
		System.out.println(v.key());
		if (v.right() != null){
			inOrder(v.right());
		}
	}//end method

	
	public Boolean isEmpty(){
		return (root == null);
	}

}



class BSTFriendsTest{
	public static void main(String[] args){
		BSTFriends test = new BSTFriends();
		test.addFriend("bgould");
		test.addFriend("egoldsmi");
		test.addFriend("zzzzzzzzz");
		test.addFriend("zzzzzzzzzzzzzzz");
		test.addFriend("abc");
		test.inOrder(test.root());

	}





}