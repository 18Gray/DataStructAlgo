package com.eighteengray.datastructlib.tree.TwoThreeTree;



class TwoThreelNode
{
	TwoThreelNode(Comparable theElement)
	{
		this(theElement, null, null);
	}

	TwoThreelNode(Comparable theElement, TwoThreelNode lt, TwoThreelNode rt)
	{
		element = theElement;
		left = lt;
		right = rt;
		height = 0;
	}

	Comparable element; // The data in the node
	TwoThreelNode left; // Left child
	TwoThreelNode right; // Right child
	int height; // Height
}
