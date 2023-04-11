public class avlTree{
	private Node root;

	public avlTree(int key){
		root = new Node(key);
	}
	public avlTree(){
		root = null;
	}

	public Node getRoot(){
		return root;
	}

	/**
 	* Helper function finds the correct spot for a Node with
	* the key = data. It does so using binary serach and then
	* calls applyRotation to ensure the tree is still balanced
 	* @param int data = the key of the new node being added
	* @param Node root = the tree being added to
 	* @return none
 	*/
	public static Node insertHelper(int data, Node root) {
		// if empty tree is reached place root there
    	if (root == null) {
            root = new Node(data);
            return root;
        }
		// explore left side if data is less than root
        else if (data < root.getKey())
            root.setLeftChild(insertHelper(data,root.getLeftChild()));
		// explore right side if data is greater than root
        else if (data > root.getKey())
            root.setRightChild(insertHelper(data, root.getRightChild()));
		
        updateHeight(root);
		// rotate tree to maintain balance if needed
        return applyRotation(root);

    }
	
	/**
 	* Inserts a new Node with a key value = key. 
	* The tree is then balanced to ensure the heights of 
	* the left and right subtrees of each node differ by at most 1.
 	* @param int key = the key of the new node being added
 	* @return none
 	*/
    public void insert(int key) { 
		Node res =insertHelper(key, root); 
		if(res!=null){
			root=res;
		}

	}
	
	/**
 	* Helper funcrion that finds and deletes a node with a key value = key 
	* using binary search. The tree is then balanced to ensure the heights of 
	* the left and right subtrees of each node differ by at most 1.
 	* @param int key = the key of node to delete
	* @param Node root = tree being checked
 	* @return none
 	*/
	public static Node deleteHelper(Node root, int key) {
		// if reached empty tree
    	if (root == null){
    	        return root;
		}
		// explore left side if key is less than root
    	if (key < root.getKey())
    	    root.setLeftChild(deleteHelper(root.getLeftChild(), key));
		// explore right side if key is greater than root
    	else if (key > root.getKey())
    	    root.setRightChild(deleteHelper(root.getRightChild(), key));
		// if key is found 
    	else {
    	    if (root.getLeftChild() == null || root.getRightChild()==null){
				// if left is null swap with right side 
				if(root.getLeftChild()==null){
					root=root.getRightChild();
				}
				// if right is null swap with left side
				else if(root.getRightChild()==null){
					root=root.getLeftChild();
				}
			}
			// if root has two children it finds largest 
			// child on the right side and places it at roots spot
			else{
				Node temp = leftMostNode(root.getRightChild());
				root.setKey(temp.getKey());
				root.setRightChild(deleteHelper(root.getRightChild(), root.getKey()));
			}
    	}
		if(root==null){
			return root;
		}
    	updateHeight(root);
        return applyRotation(root);
    }
    
	/**
 	* Finds and deletes a node with a key value = key 
	* The tree is then balanced to ensure the heights of 
	* the left and right subtrees of each node differ by at most 1.
 	* @param int key = the key of node to delete
 	* @return none
 	*/
    public void deleteKey(int key) { 
		root = deleteHelper(root, key); 
	}

	/**
 	* Gets the balance of a tree by calculating how much
	* the left and right subtrees of each node differ.
	* If it is greater than 0 it is considered right heavy.
	* If it is less than 0 it is considered left heavy.
 	* @param Node node = tree being checked
 	* @return int = balace of node
 	*/
	public static int getBalanceOfTree(Node node){
		if(node==null){
			return 0;
		}
		else{
			return height(node.getRightChild())-height(node.getLeftChild());
		}
	}
	
	/**
 	* Maintains the balance of a tree by ensuring the heights of 
	* the left and right subtrees of each node differ by at most 1.
 	* @param Node node = tree being checked
 	* @return Node = root of balanced tree
 	*/
	public static Node applyRotation(Node node) {
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
				node.setRightChild(rotateRight(node.getRightChild()));
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
				node.setLeftChild(rotateLeft(node.getLeftChild()));
				// rotates children to right side
				node=rotateRight(node);
			}

        }
		// if balanced
        return node;
	}
	
	/**
 	* Maintains the balance of a left heavy tree by 
	* making tree's left child the new root and taking the 
	* right child of tree's old left child and making it 
	* the new left child of the old root.
	* Making it right heavy and therefore balancing 
	* the overall tree.
 	* @param Node node = tree being balanced
 	* @return none
 	*/
	public static Node rotateRight(Node root){
		Node leftChild = root.getLeftChild();
		Node rightGranChild = leftChild.getRightChild();
		leftChild.setRightChild(root);
		root.setLeftChild(rightGranChild);
		updateHeight(root);
		updateHeight(leftChild);
		return leftChild;
	}

	/**
 	* Maintains the balance of a right heavy tree by 
	* making tree's right child the new root and taking the 
	* left child of tree's old right child and making it 
	* the new right child of the old root.
	* Making it left heavy and therefore balancing 
	* the overall tree.
 	* @param Node node = tree being balanced
 	* @return none
 	*/
	public static Node rotateLeft(Node tree){	
		Node x = tree.getRightChild();
		Node z = x.getLeftChild();
		x.setLeftChild(tree);
		tree.setRightChild(z);
		updateHeight(tree);
		updateHeight(x);
		return x;

	}

	/**
 	* Finds adn returns a specific node with the key value = key.
	* it does so using bineary searhc and returns null if
	* no such node exists in the tree.
 	* @param int Key - key vlaue to find
 	* @return Node = node with key value = key or null if not found
 	*/
	public Node find(Node cur, int key){
		if (cur==null)
			return 0;
		// correct node found
		if(cur.getKey()==key) 
			return cur.getKey();
		// if cur is less than key explore right side
		if(cur.getKey()>key) 
			return find(cur.getLeftChild(),key);
		// if cur is greater than key explore right side
		else 
			return find(cur.getRightChild(),key);
	}

	/**
 	* Helper fucntion that prints out the tree
 	* @param Node root = tree to print
	* @param int spaceCount = keep track of how many "    " should be printed to 
	* represent the levels of the tree
 	* @return none
 	*/
	public static void printTreeHelper(Node root,int spaceCount){
		// prints val
		if(root!=null){
			System.out.printf("[%d]\n",root.getKey());
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
		if(root.getLeftChild()!=null){

			printTreeHelper(root.getLeftChild(),spaceCount);
		}

		System.out.println("");
		for(int i=0;i<spaceCount;i++){
			System.out.print("    ");
		}
		// prints right tree	
		System.out.print("-right-> ");	
		if(root.getRightChild()!=null){
			printTreeHelper(root.getRightChild(),spaceCount);
		}			
	
	}

	public void printTree(){
	
		printTreeHelper(root,0);
		System.out.println("");
	}	
		

	public static int height(Node N) {
        if (N == null)
            return 0;
 
        return N.getHeight();
    	}
	
	public static Node leftMostNode(Node root){
		if(root==null){
			return root;
		}
		if(root.getLeftChild()==null){
			return root;
		}
		return leftMostNode(root.getLeftChild());
	}

	public static void updateHeight(Node node) {    	
            int maxHeight = Math.max(height(node.getLeftChild()),
			height(node.getRightChild()));
            node.setHeight(maxHeight + 1);
	}
	
	public static int minValue(Node root){
	    int minv = root.getKey();
            while (root.getLeftChild() != null) {
            	    minv = root.getLeftChild().getKey();
            	    root = root.getLeftChild();
             }
        return minv;
    	}
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	
	
}
