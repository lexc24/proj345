public class Node{
	public int height;
	public int key;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(int key){
		this.key=key;
		height=1;
		left=null;
		right=null;
		parent=null;
	}
	public int getKey() {
			return key;
	}

	public void setKey(int key) {
		this.key= key;
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
	public void setLeftChild(Node left){
			this.left=left;
	}
	public void setRightChild(Node right){
		this.right=right;
	}	
}