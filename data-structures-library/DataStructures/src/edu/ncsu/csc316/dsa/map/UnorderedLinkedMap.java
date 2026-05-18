package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** new positional list instance */ 
    private PositionalList<Entry<K, V>> list;
    
    /**
     * constructs a new positional list made of entries 
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * The code for this method is based on the get algorithm on page 408 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * returns the position that has the key given 
     * @param key the key to find 
     * @return p the position the key is at 
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
    	//gets the position of the first entry 
    	Position<Entry<K, V>> p = list.first(); 
        //iterates until the last entry or until reaches matching key  
    	while(p != null) {
    		if(p.getElement().getKey().equals(key)) {
    			return p; 
    		}
    		else {
    			p = list.after(p); 
    		}
    	}
    	return null; 
    }

    /**
     *  The code for this method is based on the get algorithm on page 407 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * gets the value at the key 
     * @param key the key to find the value of 
     * @return value the value at the key 
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if(p == null) {
        	return null; 
        }
        
        moveToFront(p); 
        return p.getElement().getValue(); 
    }
    
    /**
     * The code for this method is based on the get algorithm on page 407 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser. 
     * 
     * @param position the position to move to the front 
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        // to move the entry to the front of the list
    	if(position != list.first()) {
    		Entry<K, V> e = position.getElement(); 
    		list.remove(position); 
    		list.addFirst(e); 
    	}
    }

    /**
     * if the key already exists in the map, update the entry with the given key to have the new value. 
     * To facilitate efficent lookUps, apply the move-to-front heuristic on the updated entry, then return the original 
     * value that was replaced. Otherwise, if the key does not already exist, insert the new key-value pair 
     * at the front of the list (to help facilitate efficient lookUps)
     * 
     * @param key the key of the entry 
     * @param value the value of the entry 
     * @return prevValue the previous value at the position 
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        //if position has an entry 
        if(p != null) {
        	V prevValue = p.getElement().getValue();  
        	((MapEntry<K, V>)p.getElement()).setValue(value); 
        	moveToFront(p); 
        	return prevValue; 
        }
        else { //if position doesn't have an entry 
        	list.addFirst(new MapEntry<>(key, value)); 
        	return null; 
        }
    }
    
    /**
     * removes the value at the key, and if the key doesnt exist it return null
     * @param key the key that is to be removed 
     * @return the value that was removed 
     */
    @Override
    public V remove(K key) {
       if(key == null) {
    	   return null; 
       }
       System.out.println("key:" + key.toString());
       Position<Entry<K, V>> p = lookUp(key);
       //if there is an position 
       if(p != null) {
    	   V prevValue = p.getElement().getValue();  
    	   list.remove(p); 
    	   return prevValue; 
       } 
       System.out.println("key:is null");
       return null; 
    }
    
    /**
     * returns the size of the list 
     * @return size the size of the list 
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * adds entries to an instance of our EntryCollection class 
     * @return the collection with all the entries 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
     * returns the string with the entries 
     * @return String made of entries in the list 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}