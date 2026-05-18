package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
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
public class AdjacencyMapGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        /** A map of outgoing edges */
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        super(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new AMVertex(vertexData, isDirected());
    }

    /**
     * The code for this method is based on the getEdge algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
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
    	AMVertex v1 = validate(vertex1); 
    	return v1.getOutgoing().get(vertex2);
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
    	AMVertex vert = validate(vertex);
    	return vert.getIncoming().values(); 
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
    	AMVertex vert = validate(vertex);
    	return vert.getIncoming().size();
    }

    /**
     * The code for this method is based on the insertEdge algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Adds a new edge to the graph, and returns a reference to the edge that was
     * created
     * 
     * @param v1       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the source vertex
     * @param v2       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the destination vertex
     * @param edgeData the data to store in the new edge
     * @return a reference to the newly created edge
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
    	Edge<E> e = super.insertEdge(v1, v2, edgeData);
        AMVertex vert1 = validate(v1);
        AMVertex vert2 = validate(v2);
        
        if (isDirected()) {
        	vert1.getOutgoing().put(v2, e);
        	vert2.getIncoming().put(v1, e);
        } else {
        	vert1.getOutgoing().put(v2, e);
        	vert2.getOutgoing().put(v1, e);
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
    	AMVertex vert = validate(vertex);
    	return vert.getOutgoing().size();
    }

    /**
     * Returns an {@link Iterable} collection of outgoing edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve outgoing edges
     * @return an {@link Iterable} collection of outgoing edges for a given vertex
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
    	AMVertex vert = validate(vertex);
    	return vert.getOutgoing().values();
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
    	 Vertex<V>[] endpoints = endVertices(edge);
         AMVertex vert1 = validate(endpoints[0]);
         AMVertex vert2 = validate(endpoints[1]);
         
         if (isDirected()) {
        	 vert1.getOutgoing().remove(vert2);
        	 vert2.getIncoming().remove(vert1);
         } 
         else {
        	 vert1.getOutgoing().remove(vert2);
        	 vert2.getOutgoing().remove(vert1);
         }
         
         super.removeEdge(edge);
    }
    
    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @param v the vertex to validate 
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
    
}