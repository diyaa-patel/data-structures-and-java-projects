package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    /**
     * iterator that constructs a new PositionIterator
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(); 
	}
	
	/**
	 * returns an iterable representation of the list's positions
	 * @return a instance of PositionIterable() 
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Add a new element into a new position that should be added immediately after
     * the specified position, p
     * 
     * @param p       the position that should be located before the new position
     *                that will be created
     * @param element the element to be added to the list
     * @return current a reference to the Position that was created to store the new element
     * @throws IllegalArgumentException if the provided position, p, is not a valid
     *                                  position
     */
	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		//validation for position
		PositionalNode<E> node = validate(p); 
		
		return addBetween(element, node.getNext(), node);
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Add a new element into a new position that should be added immediately before
     * the specified position, p
     * 
     * @param p       the position that should be located after the new position
     *                that will be created
     * @param element the element to be added to the list
     * @return current a reference to the Position that was created to store the new element
     * @throws IllegalArgumentException if the provided position, p, is not a valid
     *                                  position
     */
	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		// validation for position 
		PositionalNode<E> node = validate(p);
	    return addBetween(element, node, node.getPrevious()); 
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Add a new element into a new position at the front of the list
     * 
     * @param element the element to be added to the front of the list
     * @return a reference to the Position that was created at the front of the list
     *         to store the new element
     */
	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Add a new element into a new position at the end of the list
     * 
     * @param element the element to be added to the end of the list
     * @return a reference to the Position that was created at the end of the list
     *         to store the new element
     */
	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns a reference to the Position that is located in the list immediately
     * after the given position, p. Return null if p is at the end of the list.
     * 
     * @param p the position for which to retrieve the position located after
     * @return a reference to the Position that is located in the list immediately
     *         after the given position, p
     * @throws IllegalArgumentException if the provided position, p, is not a valid
     *                                  position
     */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		return position(node.getNext()); 
		
	}
	
	/**
	 * The code for this method is based on the mergesort algorithm on page 277 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
	 * returns the given position
	 * @param p the position 
	 * @return the node if the position is not the front or tail 
	 */
	private Position<E> position(Position<E> p) {
		PositionalNode<E> node = validate(p); 
		if(node == front || node == tail) {
			return null; 
		}
		return p; 
	}

	 /**
	  * The code for this method is based on the mergesort algorithm on page 277 in the course textbook
      * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
      * 
      * Returns a reference to the Position that is located in the list immediately
      * before the given position, p. Return null if p is at the front of the list.
      * 
      * @param p the position for which to retrieve the position located before
      * @return a reference to the Position that is located in the list immediately
      *         before the given position, p
      * @throws IllegalArgumentException if the provided position, p, is not a valid
      *                                  position
     */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p); 
		return position(node.getPrevious());
	}

	 /**
	  * The code for this method is based on the mergesort algorithm on page 277 in the course textbook
	  * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
	  * 
	  * Returns a reference to the first Position in the list. Return null if
	   * the list is empty.
	  * 
	  * @return a reference to the first position in the list
	  */
	@Override
	public Position<E> first() {
		return position(front.getNext()); 
	}

	/**
	 * checks if the list is empty 
	 * @return true if size is 0. 
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 277 in the course textbook
	  * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
	  * 
      * Returns a reference to the last/final Position in the list. Return null if
      * the list is empty.
      * 
      * @return a reference to the last position in the list
      */
	@Override
	public Position<E> last() {
		return position(tail.getPrevious()); 
	}

	/**
	 * removes the element at the given position and returns it after it is removed 
	 * 
	 * The code for this method is based on the mergesort algorithm on page 279 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * @return rtn the element at the position given that was removed. 
	 */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p); 
		PositionalNode<E> previous = node.getPrevious(); 
		PositionalNode<E> next = node.getNext(); 
		
		previous.setNext(next);
		next.setPrevious(previous);
		size--; 
		E rtn = node.getElement(); 
		return rtn;
	}

	/**
	 * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Updates the element in a given position, p, to a new element.
     * 
     * @param p the position in which to update the element
     * @param element the new element that will overwrite the existing element
     * @return rtn the original element that was replaced by the new element
     * @throws IllegalArgumentException if the provided position, p, is not a valid
     *                                  position
     */
	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p); 
		E rtn = node.getElement(); 
		node.setElement(element);
		return rtn;
	}

	/**
	 * returns the size of the list 
	 * @return size the size of the list 
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * The code for this method is based on the mergesort algorithm on page 278 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * @param element the element to add between next and prev
     * @param next the next position 
     * @param prev the prev position 
     * @return newNode the node constructed between next and previous 
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev); 
        prev.setNext(newNode);
        next.setPrevious(newNode);
        size++; 
        return newNode; 
    } 
	
    /**
     * a static class that will construct PostionalNodes and do basic methods: set and get
     * @param <E> the type of element in the node
     */
	private static class PositionalNode<E> implements Position<E> {
	   /** element at the node */ 
		private E element;
		/** next node */ 
		private PositionalNode<E> next;
		/** previous node */ 
	    private PositionalNode<E> previous;
	
	    /**
	     * Constructs the PositionalNode with the given value 
	     * @param value the value at the node 
	     */
	    public PositionalNode(E value) {
	       this(value, null);
	    }
	
	    /**
	     * Constructs the PositionalNode with the given value and a pointer to next 
	     * @param value the value at the position 
	     * @param next the next node 
	     */
	    public PositionalNode(E value, PositionalNode<E> next) {
	        this(value, next, null);
	    }
	
	    /**
	     * Constructs the PositionalNode with the given value, a pointer to next , and a pointer to previous node
	     * @param value the value at the position 
	     * @param next the next node 
	     * @param prev the previous node 
	     */
	    public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
	        setElement(value);
	        setNext(next);
	        setPrevious(prev);
	    }
	
	    /**
	     * sets the previous node to the given parameter 
	     * @param prev the previous node 
	     */
	    public void setPrevious(PositionalNode<E> prev) {
	        previous = prev;
	    }
	
	    /**
	     * gets the previous node 
	     * @return previous the previous node
	     */
	    public PositionalNode<E> getPrevious() {
	        return previous;
	    }
	    
	    /**
	     * sets the next node at the current position 
	     * @param next the next node 
	     */
	    public void setNext(PositionalNode<E> next) {
	        this.next = next;
	    }
	
	    /**
	     * gets the next node at the current position 
	     * @return next the next node 
	     */
	    public PositionalNode<E> getNext() {
	        return next;
	    }
	
	    /**
	     * gets the element at the current position
	     */
	    @Override
	    public E getElement() {
	        return element;
	    }
	    
	    /**
	     * sets the element to the current position 
	     * @param element the element to set 
	     */
	    public void setElement(E element) {
	        this.element = element;
	    }
	}
	
	
	/**
	 * PositionIterator that iterates to the correct position 
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		/** the current position */ 
		private Position<E> current;
		/** the previous position */ 
	    private Position<E> previous;
	    /** boolean for remove check */ 
        private boolean removeOK;

        /**
         * sets current to first(), previous to null, and removeOK to false.
         */
        public PositionIterator() {
            this.current = first(); 
            this.previous = null; 
            this.removeOK = false; 
        }

        /**
         * returns whether there is a next node or not 
         * @return true if there is a next node
         */
		@Override
		public boolean hasNext() {
			return current != null && current != tail; 
		}
		
		/**
		 * The code for this method is based on the mergesort algorithm on page 286 in the course textbook
         * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
         * 
		 * returns the next position 
		 * @return the next position  
		 * @throws NoSuchElementException if hasNext() is false
		 *  
		 */
		@Override
		public Position<E> next() {
		    if(!hasNext()) {
		    	throw new NoSuchElementException(); 
		    }
		    Position<E> pos = current; 
		    previous = current; 
		    current = after(current); 
		    removeOK = true; 
		    return pos; 
		   
		}
	
		
		
		/**
		 * The code for this method is based on the mergesort algorithm on page 286 in the course textbook
         * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
         * 
         * removes the last position by the last reason call to next
         * @throws IllegalStateException if removeOK is false 
		 */
		@Override
		public void remove() {
			if(!removeOK) {
				throw new IllegalStateException(); 
			}
			PositionalLinkedList.this.remove(previous); 
			removeOK = false; 
		    }
	   }

	   
	/**
	 * ElementIterator class that creates a instance of PositionIterator and 
	 * has the methods hasNext(), next(), remove() that make a call to PositionIterator 
	 */
	private class ElementIterator implements Iterator<E> {
		/** iterator for position */ 
	    private Iterator<Position<E>> it;
	
	    /**
	     * creates new PositionIterator() 
	     */
	    public ElementIterator() {
	        it = new PositionIterator();
	    }
	
	    /**
	     * checks if there is a next node
	     * @return true if there is a next node 
	     */
	    @Override
	    public boolean hasNext() {
	        return it.hasNext();
	    }
	
	    /**
	     * returns the next element in the list 
	     * @return the element at next 
	     */
	    @Override
	    public E next() {
	        return it.next().getElement();
	    }
	
	    /**
	     * removes the element at the current position
	     */
	    @Override
	    public void remove() {
	        it.remove();
	    }
	}
	
	/**
	 * private class that iterates the position by calling PositionIterator 
	 */
	private class PositionIterable implements Iterable<Position<E>> {
        
		/**
		 * returns a new instance of PositionIterator()
		 * @return a new instance of PositionIterator() 
		 */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
	

}