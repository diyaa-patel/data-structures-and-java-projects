package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyListGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyListGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyListGraph vertices each maintain
 * lists of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyListGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents an edge in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALEdge extends GraphEdge {

        /** The position of the edge in a vertex's outgoing edge list */
        private Position<Edge<E>> outgoingListPosition;

        /** The position of the edge in a vertex's incoming edge list */
        private Position<Edge<E>> incomingListPosition;

        /**
         * Creates a new adjacency list edge
         * 
         * @param element the data to store in the edge
         * @param v1      an endpoint vertex
         * @param v2      an endpoint vertex
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }

        /**
         * Returns the position of the edge in the associated vertex's incoming edge
         * list
         * 
         * @return the position of the edge in the associated vertex's incoming edge
         *         list
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }

        /**
         * Returns the position of the edge in the associated vertex's outgoing edge
         * list
         * 
         * @return the position of the edge in the associated vertex's outgoing edge
         *         list
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's incoming edge list
         * 
         * @param incomingListPosition the position of the edge in the associated
         *                             vertex's incoming edge list
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's outgoing edge list
         * 
         * @param outgoingListPosition the position of the edge in the associated
         *                             vertex's outgoing edge list
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
    }

    /**
     * Represents a vertex in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALVertex extends GraphVertex {

        /** A positional list of outgoing edges */
        private PositionalList<Edge<E>> outgoing;

        /** A positional list of incoming edges */
        private PositionalList<Edge<E>> incoming;

        /**
         * Creates a new adjacency list vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if (isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a positional list of incomingEdges to the vertex. For an undirected
         * graph, returns the same as getOutgoing()
         * 
         * @return a positional list of incoming edges to the vertex
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Returns a positional list of outgoingEdges from the vertex. For an undirected
         * graph, returns the same as getIncoming()
         * 
         * @return a positional list of outgoing edges from the vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Creates a new undirected adjacency list graph
     */
    public AdjacencyListGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency list graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    public AdjacencyListGraph(boolean directed) {
        super(directed);
    }

    /**
     * creates a edge
     * @param data the data of the edge
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the new edge created
     */
    protected Edge<E> createEdge(E data, Vertex<V> vertex1, Vertex<V> vertex2) {
        return new ALEdge(data, vertex1, vertex2);
    }

    /**
     * creates a new vertex
     * @param vertexData the data of the vertex 
     * @return the new vertex
     */
    protected Vertex<V> createVertex(V vertexData) {
        return new ALVertex(vertexData, isDirected());
    }

    /**
     * Returns the edge that exists between vertex1 and vertex2; if no edge exists
     * from vertex1 to vertex2, return null
     * 
     * @param vertex1 an endpoint vertex; for a directed graph, the source vertex
     * @param vertex2 an endpoint vertex; for a directed graph, the destination
     *                vertex
     * @return the edge that exists between vertex1 and vertex2
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        for(Edge<E> edge : v1.getOutgoing()) {
            ALEdge e = validate(edge);
            Vertex<V>[] ends = e.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return e;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return e;
            }
        }
        return null;
    }

    /**
     * The code for this method is based on the incomingEdges algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns an {@link Iterable} collection of incoming edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve incoming edges
     * @return an {@link Iterable} collection of incomoing edges for a given vertex
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
    	ALVertex vert = validate(vertex); 
    	return vert.getIncoming(); 
    }
    
    /**
     * The code for this method is based on the inDegree algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns the indegree of the given vertex -- in other words, the number of
     * edges that are 'entering' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the indegree
     * @return the indegree of the given vertex
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
    	ALVertex vert = validate(vertex);
    	return vert.getIncoming().size(); 
    }

    /**
     * The code for this method is based on the insertEdge algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Adds a new edge to the graph, and returns a reference to the edge that was
     * created
     * 
     * @param vertex1       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the source vertex
     * @param vertex2       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the destination vertex
     * @param edgeData the data to store in the new edge
     * @return a reference to the newly created edge
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	//help with this one
    	ALVertex origin = validate(vertex1); 
    	ALVertex destination = validate(vertex2);
    	
    	Edge<E> edge = super.insertEdge(origin, destination, edgeData);
        ALEdge e = validate(edge);
       
        if (isDirected()) {
            Position<Edge<E>> outPos = origin.getOutgoing().addLast(e);
            e.setOutgoingListPosition(outPos);
            Position<Edge<E>> inPos = destination.getIncoming().addLast(e);
            e.setIncomingListPosition(inPos);
        } else {
        Position<Edge<E>> pos1 = origin.getOutgoing().addLast(e);
            Position<Edge<E>> pos2 = destination.getOutgoing().addLast(e);
            e.setOutgoingListPosition(pos1);
            e.setIncomingListPosition(pos2);
        }
        return e;
    	
    }

    /**
     * The code for this method is based on the outDegree algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns the outdegree of the given vertex -- in other words, the number of
     * edges that are 'leaving' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the outdegree
     * @return the outdegree of the given vertex
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
    	ALVertex vert = validate(vertex);
    	return vert.getOutgoing().size(); 
    }

    /**
     * The code for this method is based on the outgoingEdges algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Returns an {@link Iterable} collection of outgoing edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve outgoing edges
     * @return an {@link Iterable} collection of outgoing edges for a given vertex
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
    	ALVertex vert = validate(vertex); 
    	return vert.getOutgoing(); 
    }

    /**
     * The code for this method is based on the removeEdge algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Removes the given edge from the graph.
     * 
     * @param edge the edge to remove from the graph
     */
    @Override
    public void removeEdge(Edge<E> edge) {
    	 ALEdge e = validate(edge); 
    	   if (isDirected()) {
               ALVertex origin = validate(e.getEndpoints()[0]);
               ALVertex destination = validate(e.getEndpoints()[1]);
               origin.getOutgoing().remove(e.getOutgoingListPosition());
               destination.getIncoming().remove(e.getIncomingListPosition());
           } 
    	   else {
               ALVertex v1 = validate(e.getEndpoints()[0]);
               ALVertex v2 = validate(e.getEndpoints()[1]);
               v1.getOutgoing().remove(e.getOutgoingListPosition());
               v2.getOutgoing().remove(e.getIncomingListPosition());
           }
           super.removeEdge(edge);
    }

    /**
     * Safely casts an Edge to an adjacency list edge
     * 
     * @param e the edge to validate 
     * @return an adjacency list edge representation of the given Edge
     * @throws IllegalArgumentException if the edge is not a valid adjacency list
     *                                  edge
     */
    protected ALEdge validate(Edge<E> e) {
        if (!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge) e;
    }

    /**
     * Safely casts a Vertex to an adjacency list vertex
     * 
     * 
     * @param v the vertex to validate 
     * @return an adjacency list vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency list
     *                                  vertex
     */
    protected ALVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex) v;
    }
}