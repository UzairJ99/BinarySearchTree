import java.util.EmptyStackException;

public class LinkedListStack<E> implements Stack<E>
{
	Node<E> head; 
    
    public LinkedListStack()
    {
        head = new Node<E>(null, null); //dummy header
    }
    
    public boolean isEmpty()
    {
        return(head.next == null); //check if there exists a node after the head
    }
    
    public void push(E element)
    {
        Node<E> newNode = new Node(element, head.next); //generate new node to be pushed into the linked list
        this.head.next = newNode; //assign it to the next node
    }
    
    public E top() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException(); 
        } 
        else 
        {
            return head.next.element; //return the top element of the linked list
        }
    }
    
    public E pop() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException(); 
        } 
        else 
        {
            E element = head.next.element; //store the node element into variable element
            head.next = head.next.next; //skips over the next element to the next next element hence removing it from the list
            return element; 
        }
    }
}
