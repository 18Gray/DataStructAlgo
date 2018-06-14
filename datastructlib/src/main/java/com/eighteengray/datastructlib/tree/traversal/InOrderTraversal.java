package com.eighteengray.datastructlib.tree.traversal;

public class InOrderTraversal
{
	
	void in_traverse_recur(BinaryTreeNode binaryTreeNode)  
	{  
	    if(binaryTreeNode != null)  
	    {  
	    	if(binaryTreeNode.left != null)  
	        {
	    		in_traverse_recur(binaryTreeNode.left); 
	        }
	        System.out.print(binaryTreeNode.num + "");
	        
	        if(binaryTreeNode.right != null)  
	        {
	        	in_traverse_recur(binaryTreeNode.right); 
	        }   
	    }  
	}

}
