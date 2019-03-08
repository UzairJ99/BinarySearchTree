public class TNode 
{
	int element; 
    TNode right; 
    TNode left; 
    
    TNode(int e, TNode leftSide, TNode rightSide){
        element = e; 
        left = leftSide; 
        right = rightSide; 
    }
}
