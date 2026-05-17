package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class LinkedBinaryTreeTest {

	/** tree */ 
    private LinkedBinaryTree<String> tree;
    /** first position */ 
    private Position<String> one;
    /** second position */ 
    private Position<String> two;
    /** third position */ 
    private Position<String> three;
    /** fourth position */ 
    private Position<String> four;
    /** fifth position */ 
    private Position<String> five;
    /** sixth position */ 
    private Position<String> six;
    /** seventh position */ 
    private Position<String> seven;
    /** eighth position */ 
    private Position<String> eight;
    /** ninth position */ 
    private Position<String> nine;
    /** tenth position */ 
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        assertEquals("ONE", one.getElement());

        assertEquals("ten", tree.set(ten, "TEN"));
        assertEquals("TEN", ten.getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(six));
        assertEquals(1, tree.numChildren(three));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(two, tree.parent(six));
        assertEquals(three, tree.parent(four));
        assertEquals(one, tree.parent(three));
        assertNull(tree.parent(one)); 
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        assertEquals(five, tree.sibling(seven));
        assertEquals(seven, tree.sibling(five));
        assertNull(tree.sibling(one));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(ten));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertFalse(tree.isLeaf(two));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        String[] expected = {"one", "two", "six", "ten", "seven", "five", "three", "four", "eight", "nine"};
        for (String value : expected) {
            assertTrue(it.hasNext());
            assertEquals(value, it.next().getElement());
        }
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        String[] expected = {"six", "seven", "five", "ten", "two", "eight", "nine", "four", "three", "one"};
        for (String value : expected) {
            assertTrue(it.hasNext());
            assertEquals(value, it.next().getElement());
        }
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        String[] expected = {"six", "two", "seven", "ten", "five", "one", "eight", "four", "nine", "three"};
        for (String value : expected) {
            assertTrue(it.hasNext());
            assertEquals(value, it.next().getElement());
        }
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.root());
    }
    
    /**
     * tests the levelOrder traversal 
     */
    @Test
    public void testLevelOrder() {
    	 createTree();
         Iterator<Position<String>> it = tree.levelOrder().iterator();
         String[] expected = {"one", "two", "three", "six", "ten", "four", "seven", "five", "eight", "nine"};
         for (String value : expected) {
             assertTrue(it.hasNext());
             assertEquals(value, it.next().getElement());
         }
         assertFalse(it.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	Position<String> root = tree.addRoot("root");
        Position<String> leftChild = tree.addLeft(root, "left");
        assertEquals("left", leftChild.getElement());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tree.addLeft(root, "another left");
        });
        assertEquals("Node already has a left child.", exception.getMessage());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	Position<String> root = tree.addRoot("root");
        Position<String> rightChild = tree.addRight(root, "right");
        assertEquals("right", rightChild.getElement());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tree.addRight(root, "another right");
        });
        assertEquals("Node already has a right child.", exception.getMessage());
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals("six", tree.remove(six));
        assertEquals(9, tree.size());
        assertNull(tree.left(two));  // Should no longer have a left child

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tree.remove(ten);  // Has two children
        });
        assertEquals("The node has two children", exception.getMessage());
    }
    
    /**
     * Test the output of the toString() method from AbstractTree
     */
    @Test
    public void testToString() {
    	createTree();
        String expected = "LinkedBinaryTree[\n" +
                "one\n" +
                " two\n" +
                "  six\n" +
                "  ten\n" +
                "   seven\n" +
                "   five\n" +
                " three\n" +
                "  four\n" +
                "   eight\n" +
                "   nine\n" +
                "]";
        
        assertEquals(expected, tree.toString());
    }

    /**
     * Test the output of toString() on an empty tree
     */
    @Test
    public void testToStringEmptyTree() {
        LinkedBinaryTree<String> emptyTree = new LinkedBinaryTree<>();
        String expected = "LinkedBinaryTree[\n]";
        assertEquals(expected, emptyTree.toString());
    }
}