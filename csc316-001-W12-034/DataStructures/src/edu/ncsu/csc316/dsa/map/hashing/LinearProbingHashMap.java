package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** table */ 
    private TableEntry<K, V>[] table;
    /** size of the table */ 
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    /**
     *  The code for this method is based on the entrySet algorithm on page 426 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * returns the iterable that is used for the set 
     * @return the iterator used for the set 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
    	for (int h = 0; h < table.length; h++) {
    		if (!isAvailable(h)) {  
    			collection.add(table[h]); 
    		}
    	}
    	return collection; 
    }

    /**
     * Creates a new hash table array with the given capacity
     * 
     * @param capacity the initial capacity for a new hash table array instance
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    /**
     * checks if the place is empty 
     * @param index to check if it empty 
     * @return true if the spot is empty 
     */
    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    /**
     *   The code for this method is based on the bucketGet algorithm on page 426 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns the value associated with the given key in the bucket with the given
     * hash function
     * 
     * @param hash the index of the bucket in which to inspect
     * @param key  the target key
     * @return the value associated with the given target key
     */
    @Override
    public V bucketGet(int hash, K key) {
        int j = findBucket(hash, key); 
        if(j < 0) {
        	return null; 
        }
        return table[j].getValue(); 
    }

    /**
     * The code for this method is based on the bucketPut algorithm on page 426 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Adds or updates an entry with the given key and value to the hash table array
     * in the bucket that has the provided hash
     * 
     * @param hash  the index of the bucket in which to add or update the entry
     * @param key   the key for the entry being added or updated
     * @param value the value for the entry being added or updated
     * @return the original value of the entry that was updated, or null if an entry
     *         with the target key did not already exist in the hash table bucket
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
    	 int j = findBucket(hash, key); 
         if(j >= 0) {
        	V original = table[j].getValue(); 
         	table[j].setValue(value);
         	return original; 
         }
         table[-(j + 1)] = new LinearProbingHashMap.TableEntry<K, V>(key, value); 
         size++; 
         return null; 
    }

    /**
     * helper method that can be used to find the bucket that conains the key we are looking for 
     * if the key cannot be found the helper method can return a -(a + 1) such that the key should be added at the bucke 
     * with index a 
     * @param index the index into the hash table 
     * @param key of the entry to locate 
     * @return the index to be added to 
     */
    private int findBucket(int index, K key) {
    	int avail = -1; 
    	int j = index; 
    	
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1) {
    				avail = j; 
    			}
    			if(table[j] == null) {
    				return -(avail + 1); 
    			}
    		}
    		else if(table[j].getKey().equals(key)) {
    			return j; 
    		}
    		j = (j + 1) % table.length; 
    	} while (j != index); 
    	return -(avail + 1); 
    }

    /**
     * Returns the value associated with the entry with the key in the bucket with
     * the given hash function
     * 
     * @param hash the index of the bucket in which to remove the entry
     * @param key  the key for the targeted entry being removed from the hash table
     * @return the value associated with the entry that was removed from the hash
     *         table
     */
    @Override
    public V bucketRemove(int hash, K key) {
    	int j = findBucket(hash, key); 
        if(j < 0) {
        	return null; 
        }
        V answer = table[j].getValue(); 
        table[j].setDeleted(true);
        size--; 
        return answer; 
    }

    /**
     * returns the size of the map 
     * @return size the size of the map 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns the length of the table 
     * @return table.length the length of the table 
     */
    @Override
    protected int capacity() {
        return table.length;
    }

    /**
     * creates a TableEntry object to store the new element and a field to indicate whether 
     * the element at the bucket has been deleted. 
     * @param <K> the key of the entry 
     * @param <V> the value of the entry 
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** checks if it was deleted */ 
        private boolean isDeleted;

        /**
         * creates a TableEntry object 
         * @param key the key of the entry 
         * @param value the value of the entry
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * returns true if entry was deleted 
         * @return isDeleted the boolean for checking if the entry was deleted 
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * sets the isDeleted boolean to whatever is passed 
         * @param deleted which will be true or false 
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}