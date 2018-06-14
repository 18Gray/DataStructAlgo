package com.eighteengray.datastructlib.tree.traversal;

public class PostOrderTraversal
{
	
	void post_traverse_recur(BinaryTreeNode binaryTreeNode)  
	{  
	    if(binaryTreeNode != null)  
	    {  
	        if(binaryTreeNode.left != null)  
	        {
	        	post_traverse_recur(binaryTreeNode.left); 
	        }
	        if(binaryTreeNode.right != null)  
	        {
	        	post_traverse_recur(binaryTreeNode.right); 
	        } 
	        System.out.print(binaryTreeNode.num + "");
	    }  
	}

}
