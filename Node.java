public class Node{
	public int height;
	public int val;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(int val){
		this.val=val;
		height=1;
		left=null;
		right=null;
		parent=null;
	}
}
