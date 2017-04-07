
public class RBInit {
	public static void main(String[] args){
		RBTree rb = new RBTree(-1);
		int[] a = new int[] {4,6,3,5,2,7,1,8};
		for(int i : a ){
			rb.insert(i);
		}
		//System.out.println(rb.countN());
		rb.inorder();
	}
}
