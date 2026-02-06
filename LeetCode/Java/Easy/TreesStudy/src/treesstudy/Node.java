package treesstudy;
/**
 *
 * @author Gerardo Moguel
 */
import java.util.ArrayList;
import java.util.List;
public class Node<T> {
    protected T data;
    protected List<Node<T>> children;
    
    public Node(T data){
        this.data=data;
        this.children=new ArrayList<>();
    }
    
    public T getData(){
        return data;
    }
    
    public List<Node<T>> getChildren(){
        return children;
    }
    
    public void addChildren(Node<T> node){
        
    }
}
