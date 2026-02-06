package treesstudy;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * @author Gerardo Moguel
 */
public class TreesStudy {
    
    public static <T> Node<T> getNode(T data, Node<T> root){
        if(root==null){
            return null;
        }
        Queue<Node<T>> q = new ArrayDeque<>();
        q.add(root);
        Node<T> current;
        List<Node<T>> child;
        while(!q.isEmpty()){
            current=q.poll();
            if(current.getData().equals(data)){
                return current;
            }
            else{
                child = current.getChildren();
                Iterator<Node<T>> it = child.iterator();
                while(it.hasNext()){
                    q.add(it.next());
                }
            }
        }
        return null;
    }
    
    //very easy implementation of AddNode, just give the root, parent data, child data, and it adds it to the parents children list
    public static <T> boolean addNode(T parentD,T childD, Node<T> root){ //parent data, child data, root node
        boolean resp= false;
        Node<T> parent = TreesStudy.getNode(parentD, root);
        if(parent != null){
            Node<T> child = new Node(childD);
            parent.children.addLast(child);
            resp=true;
            return resp;
        }
        return false;
    }
    
    public static <T> void printTreeBFS(Node<T> root){//you're supposed to put the root node
        Queue <Node<T>> q = new ArrayDeque<>();
        q.add(root);
        Node<T> current;
        while(!q.isEmpty()){
            current = q.poll();
            System.out.print(current.getData()+" ");
            for(int i=0;i<current.getChildren().size();i++){
                q.add(current.getChildren().get(i));
            }
        }
    };
    
    public static <T> void printTreeLvlBFS(Node<T> root){//you're supposed to put the root node
        Queue <Node<T>> q = new ArrayDeque<>();
        q.add(root);
        Node<T> current;
        int ccc = 0;
        int ccf = 0;
        while(!q.isEmpty()){
            current = q.poll();
            ccf+=current.getChildren().size();
            System.out.print(current.getData()+" ");
            for(int i=0;i<current.getChildren().size();i++){
                q.add(current.getChildren().get(i));
            }
            if(ccc==0){
                System.out.println("");
                ccc=ccf;
                ccf=0;
            }
            ccc--;
        }
    };
    
    public static <T> void printTreeLvlZZBFS(Node<T> root){//you're supposed to put the root node
        Queue <Node<T>> q = new ArrayDeque<>();
        q.add(root);
        Node<T> current;
        Deque<T> lst = new ArrayDeque<>();
        int ccc = 0;
        int ccf = 0;
        int zc  = 0;
        while(!q.isEmpty()){
            current = q.poll();
            ccf+=current.getChildren().size();
            for(int i=0;i<current.getChildren().size();i++){
                q.add(current.getChildren().get(i));
            }
            if(zc%2!=0){
                lst.addFirst(current.getData());
            }
            else{
                lst.addLast(current.getData());
            }
            if(ccc==0){
                while(!lst.isEmpty()){
                    System.out.print(lst.pollFirst()+" ");
                }
                System.out.println("");
                ccc=ccf;
                ccf=0;
                zc++;
            }
            ccc--;
        }
    };
    
    public static void main(String[] args) {
        Node <Integer> a = new Node<> (12);
        TreesStudy.printTreeBFS(a);
        System.out.println("");
        System.out.println(TreesStudy.getNode(12,a));
        System.out.println(TreesStudy.getNode(6,a));
        System.out.println(addNode(12,6,a));
        System.out.println(addNode(12,7,a));
        System.out.println(addNode(12,71,a));
        System.out.println(addNode(12,27,a));
        System.out.println(addNode(27,13,a));
        System.out.println(addNode(13,25,a));
        System.out.println(addNode(71,23,a));
        System.out.println(addNode(731,23,a));
        System.out.println(TreesStudy.getNode(6,a));
        TreesStudy.printTreeLvlBFS(a);
        
        Node <Character> b = new Node<> ('A');
        System.out.println(addNode('A','B',b));
        System.out.println(addNode('A','C',b));
        System.out.println(addNode('A','D',b));
        System.out.println(addNode('B','E',b));
        System.out.println(addNode('B','F',b));
        System.out.println(addNode('D','G',b));
        System.out.println(addNode('D','H',b));
        TreesStudy.printTreeLvlZZBFS(b);



        
    }
     
}
