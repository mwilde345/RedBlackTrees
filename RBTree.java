
public class RBTree  {
	private Node current;
	private Node parent;
	private Node grand;
	private Node great;
	private Node root;
	private static Node nullNode;
	static{
		nullNode = new Node(0);
		nullNode.left = nullNode;
		nullNode.right = nullNode;
	}
	static final boolean BLACK = true;
	static final boolean RED = false;
	
	public RBTree(int data){
		root = new Node(data);
		root.left = nullNode;
		root.right = nullNode;
	}
	
	public boolean isEmpty(){
		return root.right==nullNode;
	}
	
	public void makeEmpty(){
		root.right = nullNode;
	}
	
	public void insert(int item){
		//start everything at the root
		current = parent = grand = root;
		nullNode.data = item;
		while(current.data != item){
			great = grand;
			grand = parent;
			parent = current;
			current = item < current.data ? current.left : current.right;
			//
			if(current.left.color==RED&&current.right.color==RED){
				fixInsertion(item);
			}
		}
		//already present
		if(current != nullNode){
			System.out.println("exists");
			return;
		}
		//make the new node, it's a leaf at the end.
		current = new Node(item, nullNode, nullNode);
		//give it a parent
		if(item < parent.data){
			parent.left = current;
		}else{
			parent.right = current;
		}
		fixInsertion(item);
	}
	
	private void fixInsertion(int item){
		//new nodes always are red
		current.color = RED;
		//children of red always black.
		current.left.color = BLACK;
		current.right.color = BLACK;
		
		if(parent.color == RED){
			grand.color = RED;
			if(item < grand.data != item < parent.data){
				parent = rotate(item, grand);
			}
			current = rotate(item, great);
			current.color = BLACK;
		}
		root.right.color = BLACK;
	}
	
	private Node rotate(int item, Node parent){
		System.out.println("calling rotate on "+item);
		if(item < parent.data)
			return parent.left = item < parent.left.data ? rotateRight(parent.left) : rotateLeft(parent.left);
		else
			return parent.right = item < parent.right.data ? rotateRight(parent.right) : rotateLeft(parent.right);				
	}
	
	private Node rotateRight(Node k2){
		Node k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}
	
	private Node rotateLeft(Node k1){
		Node k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}
	
	public int countN(){
		return countN(root.right);
	}
	
	public int countN(Node r){
		if(r == nullNode){
			return 0;
		}else{
			int length = 1;
			length += countN(r.left);
			length += countN(r.right);
			return length;
		}
	}
	
	public boolean search(int value){
		return search(root.right, value);
	}
	
	public boolean search(Node r, int value){
		boolean found = false;
		while((r!=nullNode)&&!found){
			int rValue = r.data;
			if(value < rValue){
				r = r.left;
			}else if(value > rValue){
				r = r.right;
			}else{
				found = true;
				break;
			}
			found = search(r, value);
		}
		return found;
	}
	
	public void inorder(){
		inorder(root.right);
	}
	
	public void inorder(Node r){
		if(r!=nullNode){
			inorder(r.left);
			char c = 'B';
			if(r.color==RED) c = 'R';
			System.out.println(r.data + "" + c + " ");
			inorder(r.right);
		}
	}
	
}
