package com.eighteengray.datastructlib.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



//��ʽ�洢�Ľṹ�ײ㶼��������ʵ�ֵġ�

public class MyLinkedList<T> implements Iterable<T>
{
	private int theSize;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
	
	public MyLinkedList()
	{
		clear();
	}
	
	
	public void clear()
	{
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
		beginMarker.next = endMarker;
		
		theSize = 0;
		modCount++;
	}
	
	
	public int size()
	{
		return theSize;
	}
	
	
	public boolean isEmpty()
	{
		return size() == 0;
	}

	
	
	//get/set
	public T get(int idx)
	{
		return getNode(idx).data;
	}
	

	public T set(int idx, T newVal)
	{
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	
	private Node<T> getNode(int idx)
	{
		Node<T> p;
		if(idx < 0 || idx >size())
		{
			throw new IndexOutOfBoundsException();
		}
		if(idx < size()/2)
		{
			p = beginMarker.next;
			for(int i = 0; i < idx; i++)
			{
				p = p.next;
			}
		}
		else 
		{
			p = endMarker;
			for(int i = size(); i > idx; i--)
			{
				p = p.prev;
			}
		}
		return p;
	}
	
	
	//add/remove
	public boolean add(T x)
	{
		add(size(), x);
		return true;
	}
	
	
	public void add(int idx, T x)
	{
		addBefore(getNode(idx), x);
	}
	
	
	private void addBefore(Node<T> p, T x)
	{
		//ͨ��Node�Ĺ��캯��ȷ����ǰNode��prev��next
		Node<T> newNode = new Node<T>(x, p.prev, p);
		//Ȼ����������������ָ��
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	
	
	public T remove(int idx)
	{
		return remove(getNode(idx));
	}
	
	
	private T remove(Node<T> p)
	{
		//P��ǰ��ڵ�˫������
		p.next.prev = p.prev;
		p.prev.next = p.next;
		//?�Ƿ���ҪP��prev��nextָ��
		theSize--;
		modCount++;
		return p.data;
	}
	
	
    public void makeEmpty( )
    {
        beginMarker.next = null;
    }


	
	
	//������
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator()
	{
		return (Iterator<T>) new LinkedListIterator();
	}
	
	
	
	//Node��
	private static class Node<T>
	{
		public T data;
		public Node<T> prev;
		public Node<T> next;
		
		
		public Node(T d, Node<T> p, Node<T> n)
		{
			data = d;
			prev = p;
			next = n;
		}
	}

	
	//LinkedListIterator��
	private class LinkedListIterator implements Iterable<T>
	{
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext()
		{
			return current != endMarker;
		}
		
		public T next()
		{
			if(modCount != expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}
		
		public void remove()
		{
			if(modCount != expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
			if(!okToRemove)
			{
				throw new IllegalStateException();
			}
			
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}

		@Override
		public Iterator<T> iterator()
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	

}
