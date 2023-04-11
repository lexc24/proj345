public class avlTreeTest {

    public static void main(String[] args) {
        avlTree tree = new avlTree(1);
        System.out.println(testInset(tree));
        tree.printTree();
        System.out.println(testSearch(tree));
        System.out.println(testDel(tree));
        tree.printTree();
        
    }
    public static boolean testSearch(avlTree tree){
		boolean t_or_f = true;
	        for(int i=2;i<=25;i++){
	            boolean num = tree.find(tree.getRoot(),i);
	            if(num!= true) {
	            	t_or_f = false;
	            }
	        }
	        for(int i=26;i<=30;i++){
	            boolean num = tree.find(tree.getRoot(),i);
	            if(num!= false) {
	            	t_or_f = false;
	            }
	        }
	        return t_or_f;
	    }

    public static boolean testInset(avlTree tree){
        for(int i=2;i<=25;i++){
            tree.insert(i);
        }
        if(tree.getRoot().getKey()==16){
            return true;
        }return false;
    }

   public static boolean testDel(avlTree tree){
	    	for(int i=1;i<=25;i++){
                tree.deleteKey(i);
            }
	    	if(tree.isEmpty()== true)
	    		return true;
	    	return false;
	    }
}
