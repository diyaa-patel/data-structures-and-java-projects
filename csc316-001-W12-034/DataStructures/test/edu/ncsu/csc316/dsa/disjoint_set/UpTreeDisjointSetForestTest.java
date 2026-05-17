package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Diya Patel 
 *
 */
public class UpTreeDisjointSetForestTest {

	/** set instance */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        
        // Initially, each element should be in its own set.
        assertNotSame(set.find("one"), set.find("two"));
        assertNotSame(set.find("one"), set.find("three"));
        assertNotSame(set.find("two"), set.find("three"));
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        // Initially, each element is in its own set.
        assertNotEquals(set.find("one").getElement(), set.find("two").getElement());
        assertNotEquals(set.find("three").getElement(), set.find("four").getElement());
        
        // Union "one" and "two" and check they share the same representative.
        set.union(one, two);
        assertEquals(set.find("one").getElement(), set.find("two").getElement());
        
        // Union "three" and "four" and check they share the same representative.
        set.union(three, four);
        assertEquals(set.find("three").getElement(), set.find("four").getElement());
        
        // Union the two sets: {one, two} and {three, four}
        set.union(one, three);
        // Now, one, two, three, and four should share the same representative.
        String repOne = set.find("one").getElement();
        assertEquals(repOne, set.find("four").getElement());
        assertEquals(repOne, set.find("three").getElement());
        assertEquals(repOne, set.find("four").getElement());
        
        // Union "five" and "six" and check they share the same representative.
        set.union(five, six);
        assertEquals(set.find("five").getElement(), set.find("six").getElement());
        
        // Union "seven", "eight", "nine" into one set.
        set.union(seven, eight);
        set.union(seven, nine);
        String repSeven = set.find("seven").getElement();
        assertEquals(repSeven, set.find("nine").getElement());
        assertEquals(repSeven, set.find("nine").getElement());
        
        // Union the set containing {one, two, three, four} with the set containing {five, six}.
        set.union(two, five);
        // Now {one, two, three, four, five, six} should be one set.
        String repCombined = set.find("one").getElement();
        assertEquals(repCombined, set.find("four").getElement());
        assertEquals(repCombined, set.find("four").getElement());
        
        // Union the remaining element "ten" with the set containing {seven, eight, nine}.
        set.union(eight, ten);
        // Now {seven, eight, nine, ten} should be one set.
        repSeven = set.find("seven").getElement();
        assertEquals(repSeven, set.find("nine").getElement());
        
    }
}