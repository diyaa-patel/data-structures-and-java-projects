package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
	/**
	 * checks if position is an internal node 
	 * 
	 * @param p the position to check 
	 * @return true if position is internal node 
	 */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    /**
     * returns true if the position is at a leaf node 
     * 
     * @param p the position to check 
     * @return true if the position is at a leaf node 
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    /**
     * checks to see if position is at a root node 
     * 
     * @param p the position to check 
     * @return true if the position is at the root node 
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    /**
     * checks to see if the tree is empty 
     * @return true if the tree is empty 
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * sets the value at the position 
     * 
     * The code for this method is based on the mergesort algorithm on page 327 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * @param p the position 
     * @param value the value to set at the position 
     * @return original value at the position 
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    @Override
    public E set(Position<E> p, E value) {
    	AbstractTreeNode<E> node = validate(p); 
        E original = node.getElement(); 
        node.setElement(value); 
        return original; 
    }   
    
    /**
     * iterates through tree using pre order 
     * @return traversal pre order 
     */
    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }

    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        if(p.getElement() != null) {  // do not add sentinel nodes to the traversal
            traversal.add(p);
        }
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }  
    
    /**
     * The code for this method is based on the mergesort algorithm on page 339 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * iterates through the tree using post order
     * @return traversal post order 
     */
    @Override
    public Iterable<Position<E>> postOrder() {
    	PositionCollection traversal = new PositionCollection();
    	if(!isEmpty()) {
    		postOrderHelper(root(), traversal);
    	}
    	return traversal; 
    }
    
    /**
     * The code for this method is based on the mergesort algorithm on page 339 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * helper for the postOrder method
     * @param p position 
     * @param traversal the traversal to do
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
       for(Position<E> c : children(p)) {
    	   postOrderHelper(c, traversal);
       }
       if(p.getElement() != null) {
    	   traversal.add(p);
       }
    }
    
    /**
     * The code for this method is based on the mergesort algorithm on page 340 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * iterates through the tree using level order 
     * @return traversal in order
     */
    @Override
    public Iterable<Position<E>> levelOrder() {
    	PositionCollection traversal = new PositionCollection();
    	if(!isEmpty()) {
    		Queue<Position<E>> fringe = new ArrayBasedQueue<Position<E>>(); 
    		fringe.enqueue(root());
    		while(!fringe.isEmpty()) {
    			Position<E> p = fringe.dequeue(); 
    			if(p.getElement() != null) {
    				traversal.add(p);
    			}
    			for(Position<E> c : children(p)) {
    				fringe.enqueue(c);
    			}
    		}
    	}
    	return traversal; 
    }
    
    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    protected abstract static class AbstractTreeNode<E> implements Position<E> {
    	/** element at node */ 
        private E element;
        
        /**
         * constructor that sets the element at the node 
         * @param element the element to set 
         */
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        /**
         * gets the element at the node 
         * @return element the element at the node 
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * sets the element to the node 
         * @param element the element to set 
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * creates a string of the nodes 
     * 
     * @return string of tree information 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * string helper to build string 
     * @param sb the string builder 
     * @param indent the indent to use 
     * @param root the root node 
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {
    	/** positional list */ 
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions are not added.
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection
         * @return an iterator for the PositionCollection
         */
        public Iterator<Position<E>> iterator() {
            return new PositionCollectionIterator();
        }

        private class PositionCollectionIterator implements Iterator<Position<E>> {
        	/** an iterator */ 
            private Iterator<Position<E>> it;

            /**
             * constructs an iterator 
             */
            public PositionCollectionIterator() {
                it = list.iterator();
            }

            /**
             * checks to see if there is a next node 
             * @return true if there is a next node 
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
             * returns the next node 
             * @return the next node 
             */
            @Override
            public Position<E> next() {
                return it.next();
            }

            /**
             * throws an exception if remove is called 
             * @throw UnsupportedOperationException if remove is called 
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}