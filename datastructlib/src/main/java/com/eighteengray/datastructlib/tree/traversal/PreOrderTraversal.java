package com.eighteengray.datastructlib.tree.traversal;

public class PreOrderTraversal
{
	
	void pre_traverse_recur(BinaryTreeNode binaryTreeNode)  
	{  
	    if(binaryTreeNode != null)  
	    {  
	        System.out.print(binaryTreeNode.num + "");
	        if(binaryTreeNode.left != null)  
	        {
	        	pre_traverse_recur(binaryTreeNode.left); 
	        }
	        if(binaryTreeNode.right != null)  
	        {
	        	pre_traverse_recur(binaryTreeNode.right); 
	        }   
	    }  
	} 
	
	
	void pre_traverse_unrecur(BinaryTreeNode binaryTreeNode)  
	{  
	    
	}  
	
}
