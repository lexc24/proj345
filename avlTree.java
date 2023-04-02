

public class avlTree{
	public Node root;

	public avlTree(int val){
		root = new Node(val);
	}
	
	
	public Node insertHelper(int data, Node root) {
    	if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.key)
            root.left = insertHelper(data, root.left);
        else if (data > root.key)
            root.right = insertHelper(data, root.right);
        else
        	return root;
    	
    	
        updateHeight(root);
        return applyRotation(root);
    	}
	
    	void insert(int key) { root = insertHelper(key, root); }
	
	
	public Node applyRotation(Node node) {
		int balance = balanceOfTree(node);
        if (balance > 1) {
            if (balanceOfTree(node.getLeftChild()) < 0) 
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            return rotateRight(node);
        }
        else if (balance < -1) {
            if (balanceOfTree(node.getRightChild()) > 0) 
                node.setRightChild(rotateRight(node.getRightChild()));
            return rotateLeft(node);
        }
        return node;
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
