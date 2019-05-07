import java.util.Calendar;
public class ENode{
	private Calendar date;
	private String event;
	private ENode next;

	public ENode(Calendar d, String e, ENode n){
		date = d;
		event = e;
		next = n;

	}


	public ENode getNext(){
		return next;

	}

	public void setNext(ENode v){
		next = v;
	}

	public long getMiliTime(){
		return date.getTimeInMillis();
	}

	public String getEvent(){
		return event;
	}

	public Calendar getDate(){
		return date;
	}

	

	
}

class mainMethod1{ //testing TNode class
	public static void main(String[] args){
		// ENode test = new ENode("4 21 2019", "Lunch Date w/ Friend", null);
		// ENode test1 = new ENode("5 10 2019", "School Dance", null);
		// test.setNext(test1);
		// System.out.println(test.getNext().getEvent()+" "+test.getNext().getDate());
	}
}



