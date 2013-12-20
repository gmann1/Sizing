package edu.asu.voctec.utilities;

public class CircularList<E>
{
	private ListElement<E> firstElement;
	private ListElement<E> lastElement;
	private ListElement<E> currentElement;
	private int numberOfElements = 0;
	
	private static class ListElement<E>
	{
		E data;
		ListElement<E> nextElement;
		ListElement<E> previousElement;
		
		public ListElement(E element)
		{
			this.data = element;
		}
		
		public void setLinks(ListElement<E> previousElement,
				ListElement<E> nextElement)
		{
			this.nextElement = nextElement;
			this.previousElement = previousElement;
		}
	}
	
	/**
	 * Returns the first element in this list. If this list is empty, null will
	 * be returned.
	 * 
	 * @return the first element in this list.
	 */
	public E getFirstElement()
	{
		if (firstElement != null)
			return firstElement.data;
		else
			return null;
	}
	
	/**
	 * Returns the last element in this list. If this list is empty, null will
	 * be returned.
	 * 
	 * @return the last element in this list.
	 */
	public E getLastElement()
	{
		if (lastElement != null)
			return lastElement.data;
		else
			return null;
	}
	
	/**
	 * Returns the current element for this list. If this list is empty, null
	 * will be returned.
	 * 
	 * @return the current element.
	 */
	public E getCurrentElement()
	{
		if (currentElement != null)
			return currentElement.data;
		else
			return null;
	}
	
	/**
	 * Returns the next element in this list WITHOUT shifting the current node.
	 * 
	 * @return the next element in this list.
	 */
	public E getNextElement()
	{
		if (currentElement != null)
			return currentElement.nextElement.data;
		else
			return null;
	}
	
	/**
	 * Returns the previous element in this list WITHOUT shifting the current
	 * node.
	 * 
	 * @return the previous element in this list.
	 */
	public E getPreviousElement()
	{
		if (currentElement != null)
			return currentElement.previousElement.data;
		else
			return null;
	}
	
	/**
	 * Returns the next element in this list, and moves the current element
	 * forward one.
	 * 
	 * @return the next element in this list.
	 */
	public E next()
	{
		currentElement = currentElement.nextElement;
		return currentElement.data;
	}
	
	/**
	 * Returns the previous element in this list, and moves the current element
	 * backwards one.
	 * 
	 * @return the previous element in this list.
	 */
	public E previous()
	{
		currentElement = currentElement.previousElement;
		return currentElement.data;
	}
	
	public void addFirst(E element)
	{
		// TODO replace with a call to add(index, element);
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
			// Link the newNode to the last and first element, respectively
			newNode.setLinks(lastElement, firstElement);
			
			// Make the last element link to the new element
			this.lastElement.nextElement = newNode;
			
			// Make the first element point (backwards) to the new element
			this.firstElement.previousElement = newNode;
			
			// Replace the first element with this element;
			this.firstElement = newNode;
		}
		
		numberOfElements++;
	}
	
	public void addLast(E element)
	{
		// TODO replace with a call to add(index, element);
		ListElement<E> newNode = new ListElement<E>(element);
		
		if (lastElement == null)
		{
			// Set element as the first, last, and current element
			firstElement = newNode;
			lastElement = firstElement;
			firstElement.setLinks(lastElement, lastElement);
			currentElement = firstElement;
		}
		else
		{
			// Link the newNode to the last and first element, respectively
			newNode.setLinks(lastElement, firstElement);
			
			// Make the last element link to the new element
			this.lastElement.nextElement = newNode;
			
			// Make the first element point (backwards) to the new element
			this.firstElement.previousElement = newNode;
			
			// Replace the last element with this element;
			this.lastElement = newNode;
		}
		
		numberOfElements++;
	}
	
	public boolean add(E arg0)
	{
		boolean success;
		try
		{
			addLast(arg0);
			success = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public void removeCurrentNode()
	{
		// Account for a single element list
		if (numberOfElements == 1)
		{
			// Removing the last object means all current objects are null.
			this.currentElement = null;
			this.firstElement = null;
			this.lastElement = null;
		}
		else if (numberOfElements > 1)
		{
			// Make the previous object point to the next object, and vice-versa
			currentElement.previousElement.nextElement = currentElement.nextElement;
			currentElement.nextElement.previousElement = currentElement.previousElement;
			
			// Adjust the first and last nodes if necessary
			if (currentElement == lastElement)
				lastElement = currentElement.previousElement;
			else if (currentElement == firstElement)
				firstElement = currentElement.nextElement;
			
			// Set the current node equal to the next node
			currentElement = currentElement.nextElement;
		}
		
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
	
	public int size()
	{
		return this.numberOfElements;
	}
}
