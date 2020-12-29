package delayedRemovalBST;





public class DelayedRemovalBST<Type extends Comparable<? super Type>> {
	/**
	 * Root of the BST
	 */
	private BinaryNode<Type> root;
	
	/**
	 * Number of nodes satisfying isRemoved = false.
	 */
	private int numNodes;
	
	/**
	 * Total number of nodes (regardless of isRemoved = false or true)
	 */
	private int numTotalNodes;
	
	public DelayedRemovalBST() {
		
	}
	
	
	/**
	 * The method is control the root node is null otherwise not.
	 * @return true,false
	 */
	public boolean isEmpty() {
		if(root==null) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Number of non-removed nodes in the BST
	 * @return Number of nodes satsifying isRemoved = false
	 */
	public int size() {
		return numNodes;
	}

	
	/**
	 * Height of the BST disregarding removed nodes
	 * @return BST height disregarding removed nodes
	 */
	public int height() {
		
		return maxDepth(root)-1;
	}
	 int maxDepth(BinaryNode<Type> root)  
	    { 
	        if (root == null) 
	            return 0; 
	        else 
	        { 
	        	int lDepth=0,rDepth=0;
	            /* compute the depth of each subtree */
	        	if(root.left!=null ) {
	        		if(!root.left.isRemoved) {
		        		lDepth = maxDepth(root.left); 
		        	}
	        	}
	        	if(root.right!=null) {
		        	if(!root.right.isRemoved) {
		        	    rDepth = maxDepth(root.right);
		        	}
	        	} 
	            /* use the larger one */
	            if (lDepth > rDepth) 
	                return (lDepth + 1); 
	             else 
	                return (rDepth + 1); 
	        } 
	    } 
	
	/**
	 * Searches for the specified key in this BST
	 * @param key Value to be searched
	 * @return true if key appears as a node in the BST, false otherwise 
	 */
	public boolean contains(Type key) {
		if(findNode(key)!=null) return true;
		
		    // no matching node was found
		    return false;
	}
	
	/**
	 * Searches for the node that contains the specified key in this BST.
	 * If the node found is tagged with isRemoved = true, find will fail. 
	 * @param key The data to be located
	 * @return Reference of the node containing the key
	 */
	public BinaryNode<Type> find(Type key) {
		if(findNode(key)!=null) {
			BinaryNode<Type> find = root;
			while(find!=null) {
				if(find.data.compareTo(key)>0) {// left
					find=find.left;	
					if(find.data.equals(key)&&!find.isRemoved) {
						
						return find;
					}
				
					}
				
					if(find.data.compareTo(key)<0) {// right
						find=find.right;	
						if(find.data.equals(key)&&!find.isRemoved) {
							return find;
						}
					
					}
					return find;
			}
		}
	
		return null;
	}
	
	/**
	 * Finds the node that contains the smallest data in the BST.
	 * Nodes marked removed will be disregarded.
	 * @return Reference to the min-data node in the BST
	 */
	public BinaryNode<Type> findMin() {
		BinaryNode<Type> find=root;
		while(find.left!=null) {
			if(!find.left.isRemoved) {
				find=find.left;
			}
			else {
				return find;
			}
			
		}
		return find;
	}
	
	/**
	 * Finds the node that contains the highest data in the BST.
	 * Nodes marked removed will be disregarded.
	 * @return Reference to the max-data node in the BST
	 */
	public BinaryNode<Type> findMax() {
		BinaryNode<Type> find=root;
		while(find.right!=null) {
			if(!find.right.isRemoved) {
				find=find.right;
			}
			else {
				return find;
			}
			
		}
		return find;
	}
	
	/**
	 * Prints ALL nodes of the BST using pre-order traversal. 
	 * For removed nodes, an underscore character ('_')
	 * will be printed before the node data.
	 */
	public void printTree() {
		 // Base Case
		traverse(root);

	}
	/**
	 * Prints ALL nodes of the BST using pre-order traversal. 
	 * For removed nodes, an underscore character ('_')
	 * will be printed before the node data.
	 * @param BinaryNode that is root.
	 */
	public void traverse(BinaryNode<Type> node){ // Each child of a tree is a root of its subtree.
        if (node == null) 
            return; 
        if(node.isRemoved==true) {
	    	 System.out.print("_"+node.data+" ");
	    }
	    else if(root.isRemoved==false) {
	    	System.out.print(node.data+" ");
	    }
        /* then recur on left sutree */
        traverse(node.left); 
  
        /* now recur on right subtree */
        traverse(node.right); 
	}
	
	
	/**
	 * Inserts the given data into the tree. Notice that there are 3 cases:
	 * (i) data already appears in the tree and isRemoved = false for this node. In this case, do nothing.
	 * (ii) data already appears in the tree and isRemoved = true for this node. In this case, set isRemoved = false for the node.
	 * (iii) data does not appear in the tree. In this case, insert a new node as usual.
	 * @param data Data to be inserted
	 */
	public void insert(Type data) {	
		if(isEmpty()) {
			this.root=new BinaryNode<Type>(data);
		
			this.numTotalNodes++;
			this.numNodes++;
			
		}
		else if(contains(data)&&findNode(data).isRemoved==false) ;
		else if(contains(data)&&findNode(data).isRemoved==true) {
			findNode(data).isRemoved=false;
			this.numNodes++;
		}
		else {			
			// Create a new Node containing 
		    // the new element 
			BinaryNode<Type> newnode = new BinaryNode<Type>(data); 	   
		    // Pointer to start traversing from root and 
		    // traverses downward path to search 
		    // where the new node to be inserted 
			BinaryNode<Type> x = root; 
		   
		    // Pointer y maintains the trailing 
		    // pointer of x 
			BinaryNode<Type> y = null; 
		   
		    while (x != null) { 
		        y = x; 
		        if (x.data.compareTo(data)>0) {
		            x = x.left; 
		        }
		        else {
		            x = x.right; 
		          
		            }
		    } 	  		   
		    // If the new key is less then the leaf node key 
		    // Assign the new node to be its left child 
		    if (y.data.compareTo(data)>0) {
		        y.left = newnode;
		        this.numTotalNodes++;
		        this.numNodes++;
		        }
		    	
		   
		    // else assign the new node its right child 
		    else {
		        y.right = newnode; 
		    	this.numTotalNodes++;
		    	this.numNodes++;}
							
			}
		}
		
	public BinaryNode<Type> findNode(Type x)
	   {
	       BinaryNode<Type> curr_node;   // Help variable

	       /* -------------------------------------------------
	          Find the node with search value == "x" in the BST
	          ------------------------------------------------- */
	       curr_node = root;  // Always start at the root node

	       while ( curr_node != null )
	       {
	          if ( x == curr_node.data )
	          {
	             // Found search value in BST
	             return curr_node;
	          }
	          else if (curr_node.data.compareTo(x)>0  )
	          {
	             curr_node = curr_node.left;  // Continue search in left subtree
	          }
	          else //  This must be true: ( x > curr_node.value )
	          {
	             curr_node = curr_node.right; // Continue search in right subtree      
	          }
	       }

	       /* ======================================
	          When we reach here, x is NOT in BST
	          ====================================== */
	       return null;                     // Return not found
	   }	
		
	
	
	/**
	 * Removes the given data from the tree. Notice that there are 3 cases:
	 * (i) data does not appear in the tree. In this case, do nothing.
	 * (ii) data appears in the tree and isRemoved = false for this node. In this case, set isRemoved = true for the node.
	 * (iii) data appears in the tree and isRemoved = true for this node. In this case, do nothing.
	 * @param data
	 */
	public void remove(Type data) {
		if(!contains(data)) ; 
		else if(contains(data)&&!findNode(data).isRemoved) {
			findNode(data).isRemoved=true;
			this.numNodes--;
		
		}
		else if (contains(data)&&find(data).isRemoved);
	}
	
	/**
	 * Removes all nodes of the BST that are marked removed.
	 * Resulting BST will have the same numNodes, and numNodes = numTotalNodes after the call.
	 */
	public void clearAllRemoved() {
		delet(root);

	}
	public void delet(BinaryNode<Type> node){ // Each child of a tree is a root of its subtree.		  
        if (node == null) 
            return; 
        
        if(node.isRemoved==true ) {
        	deleteNode(root,node.data);
        	node.isRemoved=false;
    	  } 
        /* then recur on left sutree */
        delet(node.left); 
  
        /* now recur on right subtree */
        delet(node.right); 
	   
	}
	 
	 public  BinaryNode<Type> deleteNode(BinaryNode<Type> root, Type key)
	    {
	        // pointer to store parent node of current node
		 BinaryNode<Type> parent = null;
	 
	        // start with root node
		 BinaryNode<Type> curr = root;
	 
	        // search key in BST and set its parent pointer
	        while (curr != null && curr.data != key)
	        {
	            // update parent node as current node
	            parent = curr;
	            // if given key is less than the current node, go to left subtree
	            // else go to right subtree
	            if (curr.data.compareTo(key)>0) {
	                curr = curr.left;
	            }
	            else {
	                curr = curr.right;
	            }
	        }
	 
	        // return if key is not found in the tree
	        if (curr == null) {
	            return root;
	        }
	 
	        // Case 1: node to be deleted has no children i.e. it is a leaf node
	        if (curr.left == null && curr.right == null)
	        {
	            // if node to be deleted is not a root node, then set its
	            // parent left/right child to null
	            if (curr != root) {
	                if (parent.left == curr) {
	                    parent.left = null;
	                } else {
	                    parent.right = null;
	                }
	            }
	            // if tree has only root node, delete it and set root to null
	            else {
	                root = null;
	            }
	        }
	 
	        // Case 2: node to be deleted has two children
	        else if (curr.left != null && curr.right != null)
	        {
	            // find its in-order successor node
	        	BinaryNode<Type> successor  = findMinx(curr.right);
	        	successor.isRemoved=false;
	 
	            // store successor value
	            Type val = successor.data;
	 
	            // recursively delete the successor. Note that the successor
	            // will have at-most one child (right child)
	            deleteNode(root, successor.data);
	 
	            // Copy the value of successor to current node
	            curr.data = val;
	        }
	 
	        // Case 3: node to be deleted has only one child
	        else
	        {
	            // find child node
	            BinaryNode<Type> child = (curr.left != null)? curr.left: curr.right;
	 
	            // if node to be deleted is not a root node, then set its parent
	            // to its child
	            if (curr != root)
	            {
	                if (curr == parent.left) {
	                    parent.left = child;
	                } else {
	                    parent.right = child;
	                }
	            }
	 
	            // if node to be deleted is root node, then set the root to child
	            else {
	                root = child;
	            }
	        }
	 
	        return root;
	    }
	 
	 /**
	  * The method looking for minumum BinaryNode in tree that is in subtree.
	  * @param subTree
	  * @return founded BinaryNode  -> find
	  */
	private BinaryNode<Type> findMinx(BinaryNode<Type> subTree) {
		BinaryNode<Type> find=subTree;
		while(find.left!=null) {
			if(!find.left.isRemoved) {
				find=find.left;
			}
			else {
				return find;
			}
			
		}
		return find;
	}
 
	/**
	 * Prints numtotalnodes value on console.
	 * Total nodes.
	 */
	public void numTotalNodes() {
		System.out.println(this.numTotalNodes);
	}
	/**
	 * Prints NumNodes value on console.
	 * Number of nodes satisfying isRemoved = false.
	 */
	public void NumNodes() {
		System.out.println(this.numNodes);
	}
	
	
	
}
