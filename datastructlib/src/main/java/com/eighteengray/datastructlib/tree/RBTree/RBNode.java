package com.eighteengray.datastructlib.tree.RBTree;




class RBNode
{
	RBNode(Comparable theElement)
	{
		this(theElement, null, null);
	}

	RBNode(Comparable theElement, RBNode lt, RBNode rt)
	{
		element = theElement;
		left = lt;
		right = rt;
		height = 0;
	}

	Comparable element; // The data in the node
	RBNode left; // Left child
	RBNode right; // Right child
	int height; // Height
}
