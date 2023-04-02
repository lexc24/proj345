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
	public Node getData() {
			return val;
		}

	public Node getLeftChild() {
		return this.left;
		}

	public Node getRightChild() {
		return this.right;
		}

	public void setHeight(int i) {
		this.height = i;
		}
}
