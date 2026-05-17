package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An EdgeListGraph is an implementation of the {@link Graph} abstract data type.
 * EdgeListGraph maintains a list of vertices in the graph and a list of edges in the
 * graph.
 * 
 * The EdgeListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King 
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {
	/** list of vertices */ 
    private PositionalList<Vertex<V>> vertexList;
    /** list of edges */
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected edge list graph
     */
    public EdgeListGraph() {
        this(false);
    }

    /**
     * Creates a new edge list graph
     * @param directed if true, the graph is directed; if false, the graph is undirected
     */
    public EdgeListGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }

    /**
     * Returns an {@link Iterable} collection of vertices in the graph
     * 
     * @return an {@link Iterable} collection of vertices in the graph
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    /**
     * Returns the number of edges in the graph
     * 
     * @return the number of edges in the graph
     */
    @Override
    public int numEdges() {
        return edgeList.size();
    }

    /**
     * Returns an {@link Iterable} collection of edges in the graph
     * 
     * @return an {@link Iterable} collection of edges in the graph
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    /**
     * validates the vertex
     * @param v the vertex to validate
     * @return the vertex if it is valid
     */
    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
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
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }

    /**
     * Returns the outdegree of the given vertex -- in other words, the number of
     * edges that are 'leaving' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the outdegree
     * @return the outdegree of the given vertex
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    /**
     * list of outgoing edges for the vertex 
     * @param vertex the vertex to find outgoing edges
     * @return a list of outgoing edges
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[0] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[1] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }

    /**
     * a list of the incoming edges
     * @param vertex the vertex to find the incoming edges of
     * @return a lost of incoming edges
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[1] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[0] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }

    /**
     * Returns the indegree of the given vertex -- in other words, the number of
     * edges that are 'entering' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the indegree
     * @return the indegree of the given vertex
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    /**
     * Returns an {@link Iterable} collection of outgoing edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve outgoing edges
     * @return an {@link Iterable} collection of outgoing edges for a given vertex
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    /**
     * Returns an {@link Iterable} collection of incoming edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve incoming edges
     * @return an {@link Iterable} collection of incomoing edges for a given vertex
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    /**
     * Adds a new vertex to the graph, and returns a reference to the vertex that
     * was created
     * 
     * @param vertexData the data to store in the new vertex
     * @return a reference to the newly created vertex
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        Vertex<V> v = createVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        ((GraphVertex)v).setPosition(pos);
        return v;
    }

    /**
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
        GraphVertex origin = validate(vertex1);
        GraphVertex destination = validate(vertex2);
        Edge<E> e = createEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        ((GraphEdge)e).setPosition(pos);
        return e;
    }

    /**
     * removes the vertex 
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        for (Edge<E> e : outgoingEdges(v)) {
            removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        vertexList.remove(v.getPosition());
    }

    /**
     * removes the edge 
     * @param edge the edge to remove
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
    }
    
    /**
     * creates a new vertex
     * @param vertexData the data of the vertex 
     * @return the new vertex
     */
    protected Vertex<V> createVertex(V vertexData) {
        return new GraphVertex(vertexData);
    }
    
    /**
     * creates a edge
     * @param data the data of the edge
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the new edge created
     */
    protected Edge<E> createEdge(E data, Vertex<V> vertex1, Vertex<V> vertex2) {
        return new GraphEdge(data, vertex1, vertex2);
    }
}