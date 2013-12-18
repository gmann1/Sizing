package edu.asu.voctec.utilities;


public class CircularList<E>
{
	private ListElement<E> firstElement;
	private ListElement<E> lastElement;
	private ListElement<E> currentElement;
	
	private static class ListElement<E>
	{
		E data;
		ListElement<E> nextElement;
		ListElement<E> previousElement;
		
		public ListElement(E element)
		{
			this.data = element;
		}
		
		public void setLinks(ListElement<E> previousElement, ListElement<E> nextElement)
		{
			this.nextElement = nextElement;
			this.previousElement = previousElement;
		}
	}

	public E getFirstElement()
	{
		return firstElement.data;
	}

	public E getLastElement()
	{
		return lastElement.data;
	}

	public E getCurrentElement()
	{
		return currentElement.data;
	}
	
	public E next()
	{
		currentElement = currentElement.nextElement;
		return currentElement.data;
	}
	
	public E previous()
	{
		currentElement = currentElement.previousElement;
		return currentElement.data;
	}
	
	public void addFirst(E element)
	{
		ListElement<E> newNode = new ListElement<E>(element);
		
		if (firstElement == null)
		{
			// Set element as the first, last, and current element
			firstElement = newNode;
			lastElement = firstElement;
			firstElement.setLinks(lastElement, lastElement);
			currentElement = firstElement;
		}
		else
		{
			// Make the last element link to the new element
			// Make the new element point to the former-first element
			this.lastElement.nextElement = newNode;
			this.firstElement.previousElement = newNode;
			// Replace the first element with this element;
			this.firstElement = newNode;
		}
	}
	
	public boolean add(E arg0)
	{
		boolean success;
		try
		{
			addFirst(arg0);
			success = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public ListElement<E> getNode(int index) throws IndexOutOfBoundsException
	{
		ListElement<E> iterationElement = firstElement;
		
		int currentIndex;
		
		if (index < 0)
			throw new IndexOutOfBoundsException();
		
		for (currentIndex = 0; currentIndex < index; currentIndex++)
		{
			if (iterationElement == firstElement && currentIndex != 0)
				throw new IndexOutOfBoundsException();
			
			iterationElement = iterationElement.nextElement;
		}
		
		return iterationElement;
	}
	
	public E get(int arg0) throws IndexOutOfBoundsException
	{
		return getNode(arg0).data;
	}
}
