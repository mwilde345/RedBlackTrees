
public class Node {
	int data;
	//true is black, false is red
	boolean color;
	Node left, right;
	
	public Node(int data){
		this(data, null, null);
	}
	public Node(int data, Node left, Node right){
		this.data = data;
		this.left = left;
		this.right = right;
		this.color = true;
	}	
}
