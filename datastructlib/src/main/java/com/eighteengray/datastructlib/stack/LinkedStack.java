package com.eighteengray.datastructlib.stack;


public class LinkedStack<T>
{
	Node<T> top;
	
	public LinkedStack()
	{
		// TODO Auto-generated constructor stub
		top = null;
	}
	
	
	public boolean isEmpty( )
    {
        return top == null;
    }
	
	
	public void makeEmpty( )
    {
        top = null;
    }
	
	
	public T top()
	{
		if( isEmpty( ) )
            return null;
		return top.data;
	}
	
	
	public void push(T data)
	{
		Node<T> newNode = new Node<>();
		newNode.data = data;
		newNode.next = top;
	}
	
	
	public T pop()
	{
		T topData = top.data;
		top = top.next;
		return topData;
	}
	
	
	
	
	private class Node<T>
	{
		public T data;
		public Node<T> next;
		
	}
	
}
