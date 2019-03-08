public class BSTSet 
{
    TNode root; 
    
    public BSTSet()
    {
        root = null; 
    }
    
    //code from
    	//https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
    public BSTSet(int [] arr)
    {
        arr = sortArray(arr); //sort input array
        arr = removeDuplicates(arr); 
        int middleIndex = arr.length/2; 
        
        //this will make the middle element the root of the tree
        for(int i = 0; i <= middleIndex; i++)
        {
        	//check if value is in the array then add accordingly
        	if(middleIndex + i < arr.length) 
                add(arr[middleIndex+i]); 
            if(middleIndex - i >= 0)
                add(arr[middleIndex-i]); 
        }
    }
    
    private int[] sortArray(int[] arr)
    {
        int n = arr.length; 
        //sorts the array
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    int temp = arr[j]; //store temporarily
                    arr[j] = arr[j+1]; //reassign
                    arr[j+1] = temp; 
                }
        return arr; 
    }
    
    private int[] removeDuplicates(int[] arr){
        int k = 0; 
        for(int i = 0; i < arr.length-1; i++)
        {
            if(arr[i] != arr[i+1])
                k++; 
        }
        
        int[] temp = new int[k+1]; //create new array to store the non-duplicates array
        k=0; 
        
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] != arr[i+1])
                temp[k++] = arr[i]; 
        }
        temp[k++] = arr[arr.length - 1]; 
        
        return temp; 
    }
    
    public boolean isIn(int v)
    {
        return searchRec(root, v); 
    }
    
    private boolean searchRec (TNode t, int v)
    {
        if (t == null)
        {
            return false; 
        } 
        else if (v < t.element) //check left side
        {
            //recalls the function to search from this point
        	return searchRec(t.left, v); 
        }
        else if (v > t.element) //check right side
        {
            return searchRec(t.right, v); 
        } 
        else 
        {
            return true; 
        }
    }
    
    public void add(int v)
    {
        root = addRec(root, v); 
    }
    
    private TNode addRec(TNode t, int v)
    {
        if(t == null)
        {
            t = new TNode(v, null, null); //creates a new list
        } 
        else if (v < t.element) //check left side
        {
            //recalls function to add recursively
        	t.left = addRec(t.left, v);
        } 
        else if (v > t.element) //check right side
        {
            t.right = addRec(t.right, v); 
        }
        
        return t; 
    }
    
    public boolean remove(int v)
    {
        try 
        {
            removeRec(root, v); 
            return true; 
        }
        catch (Exception e)
        {
            return false; 
        }
    }
    
    private TNode removeRec(TNode t, int v)
    {
        if (t == null)
        {
            throw new IllegalArgumentException(); 
        } 
        else if (v < t.element)
        {
            //recalls function to remove recursively
        	t.left = removeRec(t.left, v); 
        } 
        else if (v > t.element)
        {
            t.right = removeRec(t.right, v); 
        } 
        else if (t.left!=null && t.right!=null)
        {
            t.element = findTreeMin(t.right);
            t.right = removeRec(t.right, t.element); 
        } 
        else 
        { 
            if (t.left != null)
            {
            	t = t.left;
            }
            else
            {
            	t = t.right;
            }
        } 
        
        return t;
    }
    
    private int findTreeMin(TNode t)
    {
        if (t == null)
        {
            return -1; 
        }
        //search through left side of tree until it's end
        while(t.left != null)
        {
            t = t.left; 
        }
        
        return t.element; 
    }
    
    public BSTSet union(BSTSet s)
    {
        int[] arr1 = treeToArray(s); //convert each array into a tree
        int[] arr2 = treeToArray(this); 
        int[] finalArr = mergeArrays(arr1, arr2); //merge the trees together hence forming a union
        
        return new BSTSet(finalArr); //return a tree of the combined array
    }
    
    private int[] mergeArrays(int[] arr1, int[] arr2)
    {
        int[] output = new int [arr1.length + arr2.length]; //create new array to combined length to hold the merged array
        
        //copy first array
        for(int i = 0; i < arr1.length; i++)
        {
            output[i] = arr1[i];
        }
        
        //copy second array
        for(int j = arr1.length; j < arr1.length + arr2.length; j++)
        {
            output[j] = arr2[j - arr1.length]; 
        }
        
        return output; 
    }
    
    private int[] treeToArray(BSTSet s)
    {
        int arr[] = new int[s.size()]; 
        treeToArray(s.root, arr, arr.length/2);  
        return arr; 
    }
    
    private void treeToArray(TNode t, int[] arr, int i)
    {
        if(t!=null)
        {
            treeToArray(t.left, arr, i-1); //recalls until at end of left side of tree
            arr[i] = t.element; 
            treeToArray(t.right, arr, i+1); //same for right side
        }
    }
    
    public BSTSet intersection(BSTSet s){
        int[] arr1 = treeToArray(s); 
        int[] arr2 = treeToArray(this); 
        int[] out = intersectionArray(arr1, arr2);
        
        return new BSTSet(out); 
    }
    
    private int[] intersectionArray(int[] arr1, int arr2[])
    {
        int counter = 0; //keeps track of how many repeated elements there are in both arrays
        for(int i = 0; i < arr1.length; i++)
        {
            for(int j=0; j < arr2.length; j++)
            {
                if(arr1[i] == arr2[j])
                {
                    counter++; 
                }
            }
        } 
        
        int[] out = new int [counter]; //creates a new array for the intersection
        counter = 0; 
        
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr2.length; j++){
                if(arr1[i] == arr2[j]) //check if common element
                {
                    out[counter] = arr1[i]; 
                    counter++; 
                }
            }
        } 
        return out; 
    }
    
    public BSTSet difference(BSTSet s)
    {
        int[] arr1 = treeToArray(this); 
        int[] arr2 = treeToArray(s); 
        int[] out = differenceArray(arr1, arr2);

        return new BSTSet(out); 
    }
    
    private int[] differenceArray(int[] arr1, int[] arr2)
    {
        boolean inB = false; //store a boolean var to see if what's in A is in B
        int counter = 0; //counts unique elements in A
        
        //count unique elements
        for(int i = 0; i < arr1.length; i++)
        {
            for(int j = 0; j < arr2.length; j++)
            {
                if(arr1[i] == arr2[j]) //check common element
                {
                    inB = true; 
                }
            }
            if(!inB)
            {
                counter++; 
            }
            inB = false; 
        }
        
        int[] out = new int[counter]; 
        counter = 0; 
        inB = false; 
        
        //creates array of unique elements
        for(int i = 0; i < arr1.length; i++)
        {
            for(int j = 0; j < arr2.length; j++)
            {
                if(arr1[i] == arr2[j])
                {
                    inB = true; 
                }
            }
            if(!inB)
            {
                out[counter] = arr1[i];
                counter++; 
            }
            inB = false; 
        }
        
        return out; 
    }
    
    public int size()
    {
        if(root == null)
        {
            return 0; 
        } else 
        {
            return size(root); 
        }
    }
    
    private int size(TNode t)
    {
        if(t == null)
        {
            return 0; 
        }
        int leftSize = size(t.left); 
        int rightSize = size(t.right); 
        return leftSize + rightSize + 1; //adds size of both sides plus the root
    }
    
    public int height()
    {
        if(root == null)
        {
            return 0; 
        } 
        else 
        {
            return height(root); 
        }
    }
    
    private int height(TNode t)
    {
        if(t == null)
        {
            return 0; 
        }
        
        int leftSide = height(t.left); 
        int rightSide = height(t.right); 
        int treeSize;
        
        if (leftSide > rightSide)
        {
        	treeSize = leftSide;
        }
        else
        {
        	treeSize = rightSide;
        }
        return treeSize + 1; //add 1 for the root
    }
    
    public void printBSTSet()
    {
        if(root == null)
        {
            System.out.println("The set is empty."); 
        } else {
            System.out.println("Set elements: "); 
            printBSTSet(root); 
            System.out.print("\n"); 
        }
    }
    private void printBSTSet(TNode t)
    {
        if(t != null)
        {
            printBSTSet(t.left); 
            System.out.print(t.element + " "); 
            printBSTSet(t.right); 
        }
    }
    
    public void printNonRec()
    {
        
        if(root == null)
        {
            System.out.println("The set is empty"); 
        } 
        else 
        {
            LinkedListStack<TNode> stack = new LinkedListStack<>(); 
            TNode currNode = root;  
            System.out.println("Set elements: ");
            
            do
            {
                while(currNode!=null)
                {
                    stack.push(currNode);
                    currNode=currNode.left; 
                }

                TNode pop = stack.pop(); 
                System.out.print(pop.element + " "); 
                currNode=pop.right; 

            } while(!(stack.isEmpty()) || currNode!= null); 
        }       
    }
}  
