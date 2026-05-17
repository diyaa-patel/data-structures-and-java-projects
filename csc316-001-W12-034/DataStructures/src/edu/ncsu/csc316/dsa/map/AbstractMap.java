package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/** key of the entry */ 
        private K key;
        /** value of the entry */ 
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        /**
         * returns the key in the entry 
         * @return key the key in the entry 
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * returns the value in the entry 
         * @return value the value in the entry 
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        /**
         * compares the key to another key 
         * @returns zero if the keys are equal 
         */
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {

    	/** list with entries */ 
        private List<Entry<K, V>> list;

        /**
         * constructs new singly linked list with entries
         */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        /**
         * adds the entry to the back of the list 
         * @param entry the entry to add
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        /**
         * returns a new EntryCollectionIterator 
         * @return a new instance of a EntryCollectionIterator()
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        /**
         * private class that constructs new iterator and has the next and hasNext methods 
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

        	/** iterator for entries */ 
            private Iterator<Entry<K, V>> it;

            public EntryCollectionIterator() {
                it = list.iterator();
            }

            /**
             * checks to see if there is a next entry 
             * @return true if there is another entry after the current entry  
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
             * returns the next entry in the list 
             * @return the next entry in the list
             */
            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            /**
             * throws an expection if the remove method is called  
             * @throws UnsupportedOperationException if the remove methoded is called 
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
    
	  /**
	  * KeyIterator implements the {@link Iterator} interface to allow traversing
	  * through the keys stored in the map
	  * 
	  * @author Dr. King
	  * @author Diya Patel 
	  *
	  */
	 protected class KeyIterator implements Iterator<K> {
	
	 	/** entries with keys and values*/ 
	 	private Iterator<Entry<K, V>> entries = entrySet( ).iterator( );
	 	
	 	/**
	      * checks to see if there is a next entry 
	      * @return true if there is another entry after the current entry  
	      */
			@Override
			public boolean hasNext() {
				return entries.hasNext();
			}
	
			/**
			 * returns the key of the next entry 
			 * @return key the key of the next entry 
			 */
			@Override
			public K next() {
				return entries.next().getKey();
			}
	 }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King
     * @author Diya Patel 
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	/** entries with a key and value */ 
    	private Iterator<Entry<K, V>> entries = entrySet( ).iterator( ); 
    	
    	/**
         * checks to see if there is a next entry 
         * @return true if there is another entry after the current entry  
         */
		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		/**
		 * returns the value of the next entry 
		 * @return value the value of the next entry 
		 */
		@Override
		public V next() {
			return entries.next().getValue();
		}
    }

    /**
     * checks if the list is empty 
     * @return true if size is 0 
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * returns a new KeyIterator 
     * @return a new instance of KeyIterator 
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    /**
     * returns a new ValueIterable 
     * @return a new instance of ValueIterable 
     */
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    /**
     * inner class of AbstractMap that can create a new instance of the ValueIterator
     * @author Diya Patel 
     */
    private class ValueIterable implements Iterable<V> {

    	/**
    	 * returns a new ValueIterator instance 
    	 * @return a new ValueIterator instance 
    	 */
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
    }

}