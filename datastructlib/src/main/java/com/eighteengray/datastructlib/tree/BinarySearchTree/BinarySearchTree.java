package com.eighteengray.datastructlib.tree.BinarySearchTree;



/**
 * ���������BinarySearchTree
 * ��Ϊ���ֲ��Ҳ���ɾ��Ч�ʵͣ����԰Ѷ��ֲ��Һ��������������Ƕ����������
 * ��߽��С���ұ߽���
 */
public class BinarySearchTree
{
	private BinaryNode root;


	public BinarySearchTree()
	{
		root = null;
	}

	
	
	public void makeEmpty()
	{
		root = null;
	}


	public boolean isEmpty()
	{
		return root == null;
	}

	
	
	public boolean contains(Comparable x)
	{
		return contains(x, root);
	}

	public boolean contains(Comparable x, BinaryNode t)
	{
		if (t == null)
		{
			return false;
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
		{
			return contains(x, t.left);
		} 
		else if (compareResult > 0)
		{
			return contains(x, t.right);
		} 
		else
		{
			return true;
		}
	}

	

	private Comparable elementAt(BinaryNode t)
	{
		return t == null ? null : t.element;
	}

	
	public Comparable findMin()
	{
		return elementAt(findMin(root));
	}

	

	private BinaryNode findMin(BinaryNode t)
	{
		if (t == null)
			return null;

		else if (t.left == null)
			return t;

		return findMin(t.left);
	}

	

	public Comparable findMax()
	{
		return elementAt(findMax(root));
	}

	

	private BinaryNode findMax(BinaryNode t)
	{
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	

	public Comparable find(Comparable x)
	{
		return elementAt(find(x, root));
	}

	

	private BinaryNode find(Comparable x, BinaryNode t)
	{
		if (t == null)
			return null;

		if (x.compareTo(t.element) < 0)
			return find(x, t.left);

		else if (x.compareTo(t.element) > 0)
			return find(x, t.right);

		else
			return t; // Match
	}

	

	
	public void insert(Comparable x)
	{
		root = insert(x, root);
	}

	

	private BinaryNode insert(Comparable x, BinaryNode t)
	{
		/* 1 */if (t == null)
			/* 2 */t = new BinaryNode(x, null, null);

		/* 3 */else if (x.compareTo(t.element) < 0)
			/* 4 */t.left = insert(x, t.left);

		/* 5 */else if (x.compareTo(t.element) > 0)
			/* 6 */t.right = insert(x, t.right);

		/* 7 */else
			/* 8 */; // Duplicate; do nothing

		/* 9 */return t;
	}

	

	public void remove(Comparable x)
	{
		root = remove(x, root);
	}

	

	private BinaryNode remove(Comparable x, BinaryNode t)
	{
		if (t == null)
			return t; // Item not found; do nothing

		if (x.compareTo(t.element) < 0)
			t.left = remove(x, t.left);

		else if (x.compareTo(t.element) > 0)
			t.right = remove(x, t.right);

		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		}

		else
			t = (t.left != null) ? t.left : t.right;

		return t;
	}

	
	
	
	
	public void printTree()
	{
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	

	private void printTree(BinaryNode t)
	{
		if (t != null)
		{
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	

	
	public static void main(String[] args)
	{
		BinarySearchTree t = new BinarySearchTree();
		final int NUMS = 4000;
		final int GAP = 37;

		System.out.println("Checking...(no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			t.insert(new MyInteger(i));

		for (int i = 1; i < NUMS; i += 2)
			t.remove(new MyInteger(i));

		if (NUMS < 40)
			t.printTree();

		if (((MyInteger) (t.findMin())).intValue() != 2
				|| ((MyInteger) (t.findMax())).intValue() != NUMS - 2)
			System.out.println("FindMin or FindMax error!");

		for (int i = 2; i < NUMS; i += 2)
			if (((MyInteger) (t.find(new MyInteger(i)))).intValue() != i)
				System.out.println("Find error1!");

		for (int i = 1; i < NUMS; i += 2)
		{
			if (t.find(new MyInteger(i)) != null)
				System.out.println("Find error2!");
		}
	}

	
}
