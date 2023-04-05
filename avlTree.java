public class avlTree{
	public Node root;

	public avlTree(int key){
		root = new Node(key);
	}
	
	
	public Node insertHelper(int data, Node root) {
		// if empty tree is reaches place root there
    	if (root == null) {
            root = new Node(data);
            return root;
        }
		// explore left side if data is less than root
        if (data < root.key)
            root.left = insertHelper(data, root.left);
		// explore right side if data is greater than root
        else if (data > root.key)
            root.right = insertHelper(data, root.right);
		// duplicate is found and returns null to maintain balance
        else
        	return null;
    	
    	
        updateHeight(root);
		// rotate tree to maintain balance if needed
        return applyRotation(root);
    }
	
    public void insert(int key) { 
		root = insertHelper(key, root); 
	}
	

	public Node deleteHelper(Node root, int key) {
		// if reached empty tree
    	 if (root == null)
    	        return root;
		// explore left side if key is less than root
    	if (key < root.key)
    	    root.left = deleteHelper(root.left, key);
		// explore right side if key is greater than root
    	else if (key > root.key)
    	    root.right = deleteHelper(root.right, key);
		// if key is found
    	else {
    	    if (root.left == null || root.right==null){
				
				if(root.left==null){
					root=root.right;
				}
				else if(root.right==null){
					root=root.left;
				}
			}
			else{
				
				Node temp = leftMostNode(root.right);
				root.setKey(temp.getKey());
				root.right=deleteHelper(root.right, root.key);
			}
    	}
		if(root==null){
			return root;
		}
    	updateHeight(root);
        return applyRotation(root);
    	}
    
    void deleteKey(int key) { root = deleteHelper(root, key); }
	
	public Node applyRotation(Node node) {
	int balance = balanceOfTree(node);
		// if left heavy
        if (balance > 1) {
            if (balanceOfTree(node.getLeftChild()) < 0){
			   	//left-right situation
				node = rotateLeft(node);
			}
			else{
				node.right=rotateRight(node.right);
				node=rotateLeft(node);
			}
        }
		// if right heavy
        else if (balance < -1) {
            if (balanceOfTree(node.getRightChild()) > 0) 
				node=rotateRight(node);
			else{
				node.left=rotateLeft(node.left);
				node=rotateRight(node);
			}

        }
        return node;
	}
	
	public Node rotateRight(Node tree){
		Node x = tree.left;
		Node y = x.right;
		x.right=y;
		y.left=tree;
		updateHeight(tree);
		updateHeight(x);
		return x;
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
	
	public Node leftMostNode(Node root){
		if(root==null){
			return root;
		}
		if(root.left==null){
			return root;
		}
		return leftMostNode(root.left);
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
		//Node n =new Node(2);
		//Node n2 = new Node(0);
		t.insert(0);
		t.insert(2);
		t.deleteKey(0);
		t.printTree();
	}
}
