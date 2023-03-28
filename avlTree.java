

public class avlTree{
	public Node root;

	public avlTree(int val){
		root = new Node(val);
	}

	public void rotateLeft(Node tree){
		Node temp=null;
		if(tree.left.left!=null){
			temp = tree.left;
			tree.left=tree.left.left;
			temp.left=null;
		}
		if(tree.parent==null){
			tree.parent=temp;
		}
		else if(tree==tree.parent.left){
			tree.parent.left=temp;
		}
		else{
			tree.parent.right=temp;
		}
		temp.left=tree;
	}

	void printTreeHelper(Node root,int spaceCount){
		// prints val
		if(root!=null){
			System.out.printf("[%d]\n",root.val);
		}
		// if root is empty
		else{
			return; 
		}
		// increase offset for next tree layer
		spaceCount+=1;
		for(int i=0;i<spaceCount;i++){
			System.out.print("    ");
		}	
		// prints left tree
		System.out.print("-left-> ");
		if(root.right!=null){

			printTreeHelper(root.left,spaceCount);
		}
			
		System.out.println("");
		for(int i=0;i<spaceCount;i++){
			System.out.print("    ");
		}
		// prints right tree	
		System.out.print("-right-> ");	
		if(root.right!=null){
			printTreeHelper(root.right,spaceCount);
		}			
	
	}

	void printTree(){
	
		printTreeHelper(root,0);
		System.out.println("");
	}	
			

	public static void main(String[] args){
		System.out.println("hello");
		avlTree t = new avlTree(1);
		Node n =new Node(2);
		Node n2 = new Node(0);
		t.root.left=n;
		t.root.right=n2;
		t.printTree();
	}



}
