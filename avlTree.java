public class avlTree{
	public Node root;

	public avlTree(int key){
		root = new Node(key);
	}


	public Node insertHelper(int data, Node root) {
		// if empty tree is reached place root there
    	if (root == null) {
            root = new Node(data);
            return root;
        }
		// explore left side if data is less than root
        else if (data < root.key)
            root.left = insertHelper(data, root.left);
		// explore right side if data is greater than root
        else if (data > root.key)
            root.right = insertHelper(data, root.right);
		
        updateHeight(root);
		// rotate tree to maintain balance if needed
        return applyRotation(root);

    }
	
    public void insert(int key) { 
		Node res =insertHelper(key, root); 
		if(res!=null){
			root=res;
		}

	}
	
	public Node deleteHelper(Node root, int key) {
		// if reached empty tree
    	if (root == null){
    	        return root;
		}
		System.out.println(root.getKey());
		// explore left side if key is less than root
    	if (key < root.key)
    	    root.left = deleteHelper(root.left, key);
		// explore right side if key is greater than root
    	else if (key > root.key)
    	    root.right = deleteHelper(root.right, key);
		// if key is found 
    	else {
    	    if (root.left == null || root.right==null){
				// if left is null swap with right side 
				if(root.left==null){
					root=root.right;
				}
				// if right is null swap with left side
				else if(root.right==null){
					root=root.left;
				}
			}
			// if root has two children it finds largest 
			// child on the right side and places it at roots spot
			else{
				Node temp = leftMostNode(root.left);
				root.setKey(temp.getKey());
				root.left=deleteHelper(root.left, root.key);
			}
    	}
		if(root==null){
			return root;
		}
    	updateHeight(root);
        return applyRotation(root);
    }
    
    void deleteKey(int key) { 
		root = deleteHelper(root, key); 
	}

	public static int getBalanceOfTree(Node node){
		if(node==null){
			return 0;
		}
		else{
			return height(node.getRightChild())-height(node.getLeftChild());
		}
	}
	
	public Node applyRotation(Node node) {
		// gets balance value of cur node
		int balance = getBalanceOfTree(node);
		// if right heavy
        if (balance > 1) {
			// if right side is right heavy
            if (getBalanceOfTree(node.getRightChild()) >=0){
				// rotates children to left side
				node = rotateLeft(node);
			}
			// if right side is left heavy
			else{
				// rotates right gran-children to right so its right heavy
				node.right=rotateRight(node.right);
				// rotates children to left side
				node=rotateLeft(node);
			}
        }
		// if left heavy
        else if (balance < -1) {
			// if left side is left heavy
            if (getBalanceOfTree(node.getLeftChild()) >=0){
				// rotates children to right side
				node=rotateRight(node);
			}
			// if left side is right heavy	
			else{
				// rotates left gran-children to left so its left heavy
				node.left=rotateLeft(node.left);
				// rotates children to right side
				node=rotateRight(node);
			}

        }
		// if balanced
        return node;
	}
	
	public Node rotateRight(Node root){
		Node leftChild = root.left;
		Node rightGranChild = leftChild.right;
		leftChild.right=root;
		root.left=rightGranChild;
		updateHeight(root);
		updateHeight(leftChild);
		return leftChild;
	}

	public Node rotateLeft(Node tree){
		
		Node x = tree.right;
		Node z = x.left;
		x.left=tree;
		tree.right=z;
		updateHeight(tree);
		updateHeight(x);
		return x;

	}

	Node find(int key){
		Node cur = root;
		while(cur!=null){
			// correct node found
			if(cur.key==key) return cur;
			// if cur is less than key explaore right side
			if(cur.key<key) cur=cur.right;
			// if cur is greater than key explaore right side
			else cur=cur.left;
		}
		return null;
	}

	void printTreeHelper(Node root,int spaceCount){
		// prints val
		if(root!=null){
			System.out.printf("[%d]\n",root.key);
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
		if(root.left!=null){

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
		

	public static int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    	}
	
	public static Node leftMostNode(Node root){
		if(root==null){
			return root;
		}
		if(root.left==null){
			return root;
		}
		return leftMostNode(root.left);
	}

	public static void updateHeight(Node node) {    	
            int maxHeight = Math.max(height(node.getLeftChild()),
			height(node.getRightChild()));
            node.setHeight(maxHeight + 1);
	}
	
	public static int minValue(Node root){
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
		//Node n =new Node(2);
		//Node n2 = new Node(0);
		t.insert(0);
		t.insert(2);
		t.deleteKey(0);
		t.printTree();
	}
}
