public class Node{
	private int height;
	private int key;
	private Node left;
	private Node right;
	
	public Node(int key){
		this.key=key;
		height=1;
		left=null;
		right=null;

	}

	public int getKey(){
		return key;
	}
	public void setKey(int key){
		this.key=key;
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

	public int getHeight() {
		return this.height;
	}
	public void setLeftChild(Node left){
			this.left=left;
	}

	
	public void setRightChild(Node right){
		this.right=right;
	}	
}