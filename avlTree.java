

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
	
    	public void insert(int key) { root = insertHelper(key, root); }
	
	
	
	
	public Node deleteHelper(Node root, int key) {
    	 if (root == null)
    	        return root;
    	    if (key < root.key)
    	        root.left = deleteHelper(root.left, key);
    	    else if (key > root.key)
    	        root.right = deleteHelper(root.right, key);
    	    else {
    	        if (root.left == null)
    	            return root.right;
    	        else if (root.right == null)
    	            return root.left;
    	        // node with two children: Get the inorder
    	        // successor (smallest in the right subtree)
    	        root.key = minValue(root.right);
    	        // Delete the inorder successor
    	        root.right = deleteHelper(root.right, root.key);
    	    }
    	    updateHeight(root);
            return applyRotation(root);
    	}
    
    	void deleteKey(int key) { root = deleteHelper(root, key); }
	
	
	
	
	public Node applyRotation(Node node) {
	int balance = balanceOfTree(node);
        if (balance > 1) {
            if (balanceOfTree(node.getLeftChild()) < 0) 
                node.setLeftChild(rotateLeft(node.getLeftChild()));	//left-right situation
            return rotateRight(node);					//right-right situation
        }
        else if (balance < -1) {
            if (balanceOfTree(node.getRightChild()) > 0) 
                node.setRightChild(rotateRight(node.getRightChild()));	//right-left situation
            return rotateLeft(node);					//left-left situation
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
			
	public int balanceOfTree(Node node) {
    	if (node != null)
    		return height(node.getLeftChild()) - height(node.getRightChild());
    	else
    		return 0;
    	}
	
	
	public int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    	}
	
	
	public void updateHeight(Node node) {    	
            int maxHeight = Math.max(
                    height(node.getLeftChild()),
                    height(node.getRightChild())
            );
            node.setHeight(maxHeight + 1);
	}
	
	public int minValue(Node root){
	     int minv = root.key;
             while (root.left != null) {
            	    minv = root.left.key;
            	    root = root.left;
             }
        return minv;
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
