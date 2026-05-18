package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The SeparateChainingHashMap is implemented as a hash table that uses separate
 * chaining for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@see Map#put}, {@see Map#get}, and {@see Map#remove}.
 * 
 * The secondary map that appears within each bucket (with separate chaining)
 * supports worst-case O(logn) runtime for {@see Map#put}, {@see Map#get}, and
 * {@link Map#remove} within each bucket.
 * 
 * The SeparateChainingHashMap class is based on the implementation developed
 * for use with the textbook:
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
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/** table */ 
    private Map<K, V>[] table;
    /** size of the table */ 
    private int size;

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table is initialized to
     * have the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    /**
     *  The code for this method is based on the entrySet algorithm on page 424 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * returns the iterable that is used for the set 
     * @return the iterator used for the set 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection(); 
    	for (int h = 0; h < table.length; h++) {
    		if (table[h] != null) { 
    			for (Entry<K, V> entry : table[h].entrySet( )) { 
    				collection.add(entry);
    			} 
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
        // You can choose to use any EFFICIENT secondary map.
        // UnorderedLinkedMap, SearchTableMap, and BinarySearchTreeMap are NOT the most
        // efficient maps we have discussed this semester since UnorderedLinkedMap has
        // O(n) put, get, and remove; SearchTableMap has O(n) put and remove; and
        // BinarySearchTreeMap has O(n) put, get, and remove. Therefore, use a
        // SkipListMap with expected O(logn) runtime, or a balanced binary search tree
        // for guaranteed O(logn) worst-case runtime.
        table = new AVLTreeMap[capacity];
        size = 0;
    }

    
    /**
     * Returns the value associated with the given key in the bucket with the given
     * hash function
     * 
     * @param hash the index of the bucket in which to inspect
     * @param key  the target key
     * @return the value associated with the given target key
     */
    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value
        return bucket.get(key);
    }

    /**
     * The code for this method is based on the bucketPut algorithm on page 424 in the course textbook
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
    	Map<K, V> bucket = table[hash];
    	
    	if(bucket == null) {
    		bucket = new AVLTreeMap<>(); 
    		table[hash] = bucket; 
    	}
    	int oldSize = bucket.size(); 
    	
    	V answer = bucket.put(key, value); 
    	size += (bucket.size() - oldSize); 
    	return answer; 
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
    	Map<K, V> bucket = table[hash];
    	
    	if(bucket == null) {
    		return null; 
    	}
    	int oldSize = bucket.size(); 
    	
    	V answer = bucket.remove(key); 
    	size -= (oldSize - bucket.size()); 
    	return answer; 
    }

    /**
     * returns the size of the hash map 
     * 
     * @return size the size of the hash map 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the current capacity of the current hash table array
     * 
     * @return the capacity of the current hash table array
     */
    @Override
    protected int capacity() {
        return table.length;
    }
}