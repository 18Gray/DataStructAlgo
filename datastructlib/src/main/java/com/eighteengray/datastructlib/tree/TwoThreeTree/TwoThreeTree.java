package com.eighteengray.datastructlib.tree.TwoThreeTree;

import com.razergene.datastructalgo.tree.BinarySearchTree.MyInteger;



/**
 * 2-3������Ϊ����������������²���Ҳ�����������������������ƽ��ģ����ܸ����ֲ���һ����Ч����Ҫ2���ӽ���3���ӽ�㡣
 * ȱ�����ڲ���ɾ��ʱ��֤����ƽ����۽ϸߡ�
 * ���Ҳ����㣬������п�(��2-���)����ֱ�Ӳ��롣����û��(��3-���)�������ʹ����ʱ�������Ԫ�ء�
 * Ȼ����Ѵ˽�㣬���м�Ԫ���Ƶ��丸����С��Ը��������˴���
 */
public class TwoThreeTree
{
	/**
	 * Construct the tree.
	 */
	public TwoThreeTree()
	{
		root = null;
	}

	
	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(Comparable x)
	{
		root = insert(x, root);
	}

	
	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(Comparable x)
	{
		System.out.println("Sorry, remove unimplemented");
	}

	
	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public Comparable findMin()
	{
		return elementAt(findMin(root));
	}

	
	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public Comparable findMax()
	{
		return elementAt(findMax(root));
	}

	
	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item or null if not found.
	 */
	public Comparable find(Comparable x)
	{
		return elementAt(find(x, root));
	}

	
	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty()
	{
		root = null;
	}

	
	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	
	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree()
	{
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	
	/**
	 * Internal method to get element field.
	 * 
	 * @param t
	 *            the node.
	 * @return the element field or null if t is null.
	 */
	private Comparable elementAt(TwoThreelNode t)
	{
		return t == null ? null : t.element;
	}

	
	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the tree.
	 * @return the new root.
	 */
	private TwoThreelNode insert(Comparable x, TwoThreelNode t)
	{
		if (t == null)
			t = new TwoThreelNode(x, null, null);
		
		else if (x.compareTo(t.element) < 0)
		{
			t.left = insert(x, t.left);
			if (height(t.left) - height(t.right) == 2)
				if (x.compareTo(t.left.element) < 0)
					t = rotateWithLeftChild(t);
				else
					t = doubleWithLeftChild(t);
		} 
		else if (x.compareTo(t.element) > 0)
		{
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2)
				if (x.compareTo(t.right.element) > 0)
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
		} 
		else
			; // Duplicate; do nothing
		
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}

	
	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private TwoThreelNode findMin(TwoThreelNode t)
	{
		if (t == null)
			return t;

		while (t.left != null)
			t = t.left;
		return t;
	}

	
	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private TwoThreelNode findMax(TwoThreelNode t)
	{
		if (t == null)
			return t;

		while (t.right != null)
			t = t.right;
		return t;
	}

	
	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private TwoThreelNode find(Comparable x, TwoThreelNode t)
	{
		while (t != null)
			if (x.compareTo(t.element) < 0)
				t = t.left;
			else if (x.compareTo(t.element) > 0)
				t = t.right;
			else
				return t; // Match

		return null; // No match
	}

	
	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 */
	private void printTree(TwoThreelNode t)
	{
		if (t != null)
		{
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	
	/**
	 * Return the height of node t, or -1, if null.
	 */
	private static int height(TwoThreelNode t)
	{
		return t == null ? -1 : t.height;
	}

	
	/**
	 * Return maximum of lhs and rhs.
	 */
	private static int max(int lhs, int rhs)
	{
		return lhs > rhs ? lhs : rhs;
	}

	
	/**
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */
	private static TwoThreelNode rotateWithLeftChild(TwoThreelNode k2)
	{
		TwoThreelNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}

	
	/**
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation for case 4. Update heights, then return new root.
	 */
	private static TwoThreelNode rotateWithRightChild(TwoThreelNode k1)
	{
		TwoThreelNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.right), k1.height) + 1;
		return k2;
	}

	
	/**
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child. For AVL trees, this is a double
	 * rotation for case 2. Update heights, then return new root.
	 */
	private static TwoThreelNode doubleWithLeftChild(TwoThreelNode k3)
	{
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	
	/**
	 * Double rotate binary tree node: first right child with its left child;
	 * then node k1 with new right child. For AVL trees, this is a double
	 * rotation for case 3. Update heights, then return new root.
	 */
	private static TwoThreelNode doubleWithRightChild(TwoThreelNode k1)
	{
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	
	/** The tree root. */
	private TwoThreelNode root;

	// Test program
	public static void main(String[] args)
	{
		TwoThreeTree t = new TwoThreeTree();
		final int NUMS = 4000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			t.insert(new MyInteger(i));

		if (NUMS < 40)
			t.printTree();
		if (((MyInteger) (t.findMin())).intValue() != 1
				|| ((MyInteger) (t.findMax())).intValue() != NUMS - 1)
			System.out.println("FindMin or FindMax error!");

		for (int i = 1; i < NUMS; i++)
			if (((MyInteger) (t.find(new MyInteger(i)))).intValue() != i)
				System.out.println("Find error1!");
	}
	
}
