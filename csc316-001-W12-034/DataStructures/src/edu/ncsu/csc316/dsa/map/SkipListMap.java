package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * looks up the key and returns the SkipListNode at the key 
     * @param key the key to lookup 
     * @return the skip list node at the key 
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next)) {
            	if(compare(key, current.next.getEntry().getKey()) >= 0) {
            		current = current.next;
            	}
            	else {
            		break; 
            	}
            }
        }
        if(isSentinel(current)) {
        	return current; 
        }
        return current;
    }

    /**
     * gets the value at the key 
     * @param key the key to get the value of 
     * @return value the value at the key 
     */
    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key); 
        if(!isSentinel(temp) && temp != null && temp.getEntry().getKey().equals(key)) {
        	return temp.getEntry().getValue(); 
        }
        
        return null; 
    }

    /**
     * returns the new node that stores a entry and pointers to the below and prev nodes 
     * @param prev the prev node 
     * @param down the down node 
     * @param entry the entry to insert 
     * @return newNode the new node 
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        
        if(prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        if(newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if(down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    /**
     * inserts the entry in the list 
     * @param key the key of the entry 
     * @param value the value of the entry 
     * @return originalValue the orginal value at the node 
     */
    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        if(!isSentinel(temp) && temp.getEntry().getKey().equals(key)) {
        	V originalValue = temp.getEntry().getValue(); 
        	while(temp != null) {
        		((MapEntry<K, V>)temp.getEntry()).setValue(value); 
        		temp = temp.getAbove(); 
        	}
        	return originalValue; 
        }
        SkipListNode<K, V> q = null;  
        int currentLevel = -1;
        do {
        	currentLevel++; 
        	if(currentLevel >= height) {
        		height++; 
        		SkipListNode<K, V> tail = start.getNext(); 
        		start = insertAfterAbove(null, start, null); 
        		insertAfterAbove(start, tail, null); 
        	}
        	q = insertAfterAbove(temp, q, new MapEntry<>(key, value)); 
            while(temp.getAbove() == null) {
            	temp = temp.getPrevious(); 
            }
            temp = temp.getAbove(); 
            
        } while (coinToss.nextBoolean()); 
        
        size++; 
        return null; 
    }

    /**
     * returns the value removed 
     * @return originalValue the value at the key 
     */
    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if(temp == null || temp.getEntry() == null || isSentinel(temp)) {
        	return null; 
        }
        
        V originalValue = temp.getEntry().getValue(); 
       
        
        while(temp != null) {
        	
        	if(temp.getPrevious() != null) {
        		temp.getPrevious().setNext(temp.getNext());
        	}
        	if(temp.getNext() != null){
        		temp.getNext().setPrevious(temp.getPrevious());
        	}
        	temp = temp.getAbove(); 
        }
        
        
        size--; 
        return originalValue; 
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
     * jumps to the bottom of the lst before we can add the values 
     * @return set the entry collection 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	 EntryCollection set = new EntryCollection();
         SkipListNode<K, V> current = start;
         while (current.below != null) {
             current = current.below;
         }
         current = current.next;
         while (!isSentinel(current)) {
             set.add(current.getEntry());
             current = current.next;
         }
         return set;
    }

    /**
     * returns a string version of the list 
     * @return string the string version of the list 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
    /**
     * strings for the list 
     * @return string the string of the list collection entries 
     */
    public String toFullString() {
        StringBuilder sb = new StringBuilder("SkipListMap[\n");
        SkipListNode<K, V> cursor = start;
        SkipListNode<K, V> firstInList = start;
        while (cursor != null) {
            firstInList = cursor;
            sb.append("-INF -> ");
            cursor = cursor.next;
            while (cursor != null && !isSentinel(cursor)) {
                sb.append(cursor.getEntry().getKey() + " -> ");
                cursor = cursor.next;
            }
            sb.append("+INF\n");
            cursor = firstInList.below;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * private class that creates nodes for the skip list 
     * @param <K> the key of the node 
     * @param <V> the value of the node 
     */
    private static class SkipListNode<K, V> {

    	/** entry */ 
        private Entry<K, V> entry;
        /** the node above */ 
        private SkipListNode<K, V> above;
        /** the node below */ 
        private SkipListNode<K, V> below;
        /** the node previous */
        private SkipListNode<K, V> prev;
        /** the next node */ 
        private SkipListNode<K, V> next;

        /**
         * constructs a SkipListNode 
         * @param entry the entry at the node 
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * gets the node above the current node 
         * @return above the node above 
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * gets the entry at the node 
         * @return entry the entry at the node 
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * returns the next node 
         * @return next the next node 
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * returns the previous node 
         * @return prev the previous node 
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * sets the above node 
         * @param up the node to set to above 
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * sets the below node 
         * @param down the node to set to below 
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * sets the entry of the node 
         * @param entry the entry to set 
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * sets the next node in the list 
         * @param next the next node 
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * sets the previous node 
         * @param prev the previous node to set 
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}