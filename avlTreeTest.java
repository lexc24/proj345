public class avlTreeTest {

    public static void main(String[] args) {
        System.out.println("hello");
        avlTree tree = new avlTree(1);
        System.out.println(testInset(tree));
        System.out.println(testDel(tree));
    }

    public static boolean testInset(avlTree tree){
        for(int i=2;i<=25;i++){
            tree.insert(i);
        }
        if(tree.root.getKey()==16){
            return true;
        }return false;
    }
    
    public static boolean testDel(avlTree tree){
        try{
            for(int i=2;i<=25;i++){
                tree.deleteKey(i);
            }
            //tree.printTree();
        }
        catch(Exception e){
            return false;
        }   
        return true;
    }
}