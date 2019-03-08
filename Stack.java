import java.util.EmptyStackException;

public interface Stack<E> 
{
    public E top() throws EmptyStackException; 
    public void push(E e);
    public E pop() throws EmptyStackException; 
    public boolean isEmpty(); 
}
