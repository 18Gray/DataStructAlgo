package com.eighteengray.datastructlib.stack;


public class ArrayStack<T>
{
	private T [ ] theArray;
    private int topOfStack;
    static final int DEFAULT_CAPACITY = 10;
	
	public ArrayStack()
	{
		// TODO Auto-generated constructor stub
		this( DEFAULT_CAPACITY );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity)
    {
        theArray = (T[]) new Object[capacity];
        topOfStack = -1;
    }
	
	
	/**
     * Test if the stack is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return topOfStack == -1;
    }

    /**
     * Test if the stack is logically full.
     * @return true if full, false otherwise.
     */
    public boolean isFull( )
    {
        return topOfStack == theArray.length - 1;
    }

    /**
     * Make the stack logically empty.
     */
    public void makeEmpty( )
    {
        topOfStack = -1;
    }

    /**
     * Get the most recently inserted item in the stack.
     * Does not alter the stack.
     * @return the most recently inserted item in the stack, or null, if empty.
     */
    public Object top( )
    {
        if( isEmpty( ) )
            return null;
        return theArray[topOfStack];
    }

    /**
     * Remove the most recently inserted item from the stack.
     * @exception Underflow if stack is already empty.
     */
    public void pop()
    {
        if(isEmpty( ))
            return;
        theArray[topOfStack--] = null;
    }

    /**
     * Insert a new item into the stack, if not already full.
     * @param x the item to insert.
     * @exception Overflow if stack is already full.
     */
    public void push(T x)
    {
        if(isFull( ))
            return;
        theArray[++topOfStack] = x;
    }

    /**
     * Return and remove most recently inserted item from the stack.
     * @return most recently inserted item, or null, if stack is empty.
     */
    public Object topAndPop()
    {
        if(isEmpty())
            return null;
        Object topItem = top();
        theArray[topOfStack--] = null;
        return topItem;
    }
	
	
}
