public class Node<E> 
{
	E element; 
    Node<E> next; 
    
    public Node (E e, Node<E> n)
    {
        this.element = e; 
        this.next = n; 
    }
}
