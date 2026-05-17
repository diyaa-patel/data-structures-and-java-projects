package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The LinkedBinaryTree is implemented as a linked data structure to support
 * efficient Binary Tree abstract data type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The LinkedBinaryTree class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Diya Patel 
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	/** root node */ 
    private BinaryTreeNode<E> root;
    /** size of tree */ 
    private int size;

    /**
     * Create a new empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Safely casts a Position, p, to be a BinaryTreeNode.
     * 
     * @param p the position to cast to a BinaryTreeNode
     * @return a reference to the BinaryTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  BinaryTreeNode
     */
    protected BinaryTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
        }
        return (BinaryTreeNode<E>) p;
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child
     * 
     * @author Dr. King
     *
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
    	/** parent node */ 
        private BinaryTreeNode<E> parent;
        /** left child */ 
        private BinaryTreeNode<E> left;
        /** right child */ 
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element
         * 
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference
         * 
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node
         * 
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node
         * 
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node
         * 
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node
         * 
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node
         * 
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node
         * 
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }

    /**
     * returns the left position of the node 
     * @return left position of the node 
     */
    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * returns the right position of the node 
     * @return right the right position of p 
     */
    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    /**
     *  The code for this method is based on the mergesort algorithm on page 327 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * adds a left child to the parent node 
     * @param p the position of the parent node 
     * @param value the value of the child node 
     * @return child the child node added to the left 
     * @throws IllegalArgumentException if the parent node already has a child on the left 
     */
    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        BinaryTreeNode<E> child = createNode(value, node, null, null); 
        node.setLeft(child);
        size++; 
        return child; 
    }

    /**
     *  The code for this method is based on the mergesort algorithm on page 327 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * adds a right child to the parent node 
     * @param p the position of the parent node 
     * @param value the value of the child node 
     * @return child the child node added to the right 
     * @throws IllegalArgumentException if the parent node already has a child on the right 
     */
    @Override
    public Position<E> addRight(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        BinaryTreeNode<E> child = createNode(value, node, null, null); 
        node.setRight(child);
        size++; 
        return child; 
    }

    /**
     * returns the position of the root 
     * @return root the position of the root 
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * returns the parent of the position given 
     * @return parent of the position 
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * adds a root with the given value 
     * @return root with the given value 
     */
    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    /**
     * The code for this method is based on the mergesort algorithm on page 328 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * removes the position from the tree 
     * 
     * @return the value that was removed
     * @throws IllegalArgumentException if the node has two children 
     */
    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("The node has two children");
        }
        BinaryTreeNode<E> node = validate(p);
        BinaryTreeNode<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();  
        if(child != null) {
        	child.setParent(node.getParent());
        }
        if(node == root) {
        	root = child; 
        }
        else {
        	 BinaryTreeNode<E> parent = node.getParent();
        	 if(node == parent.getLeft()) {
        		 parent.setLeft(child);
        	 }
        	 else {
        		 parent.setRight(child);
        	 }
        }
        size--; 
        E temp = node.getElement(); 
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp; 
    }

    /**
     * return the size of the tree 
     * @return size the size of the tree 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * creates a node object 
     * @param e the element of the child 
     * @param parent of the child 
     * @param left the left node of child  
     * @param right the right node of the child 
     * @return node a new child node 
     */
    protected BinaryTreeNode<E> createNode(E e, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    /**
     * sets the root 
     * @param p the position of the root 
     * @return the position of the root 
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}