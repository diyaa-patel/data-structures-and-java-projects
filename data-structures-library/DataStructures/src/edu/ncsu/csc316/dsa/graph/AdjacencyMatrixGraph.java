package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
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
public class AdjacencyMatrixGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
            System.out.println("Vertex " + data + " has index " + index); 
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
    /** matrix */
    private GraphEdge[][] matrix;
 
    /** vertex indexer */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) new AbstractGraph.GraphEdge[0][0];
    }

    /**
     * creates a new vertex 
     * @param vertexData the data at the vertex 
     * @return the new vertex 
     */
    protected Vertex<V> createVertex(V vertexData) {
        return new MatrixVertex(vertexData);
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
    	  MatrixVertex vert1 = validate(vertex1);
          MatrixVertex vert2 = validate(vertex2);
          return matrix[vert1.getIndex()][vert2.getIndex()];
    }

    /**
     * gets the index of the vertex
     * @return the index of the vertex
     */
    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    /**
     * grows the array if neccessary 
     */
    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    /**
     * a list of the incoming edges
     * @param vertex the vertex to find the incoming edges of
     * @return a lost of incoming edges
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
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
    	   Edge<E> edge = super.insertEdge(vertex1, vertex2, edgeData);
           MatrixVertex vert1 = validate(vertex1);
           MatrixVertex vert2 = validate(vertex2);
           matrix[vert1.getIndex()][vert2.getIndex()] = (GraphEdge) edge;
           
           if (!isDirected()) {
               matrix[vert2.getIndex()][vert1.getIndex()] = (GraphEdge) edge;
           }
           return edge;
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
        growArray();
        return super.insertVertex(vertexData);
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
     * Returns a list of outgoing edges for the specified vertex.\
     *
     * @param vertex the vertex for which to retrieve outgoing edges
     * @return a list containing the outgoing edges from the vertex
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
    	 MatrixVertex vert = validate(vertex);
         List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
         int row = vert.getIndex();
         for (int i = 0; i < matrix.length; i++) {
             GraphEdge edge = matrix[row][i];
             if (edge != null) {
                 list.addLast(edge);
             }
         }
         return list;
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
     * The code for this method is based on the removeEdge algorithm on page 628 in the course textbook
     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
     * 
     * Removes the given edge from the graph.
     * 
     * @param edge the edge to remove from the graph
     */
    @Override
    public void removeEdge(Edge<E> edge) {
    	Vertex<V>[] endvert = endVertices(edge);
        MatrixVertex vert1 = validate(endvert[0]);
        MatrixVertex vert2 = validate(endvert[1]);
        matrix[vert1.getIndex()][vert2.getIndex()] = null;
        if (!isDirected()) {
            matrix[vert2.getIndex()][vert1.getIndex()] = null;
        }
        super.removeEdge(edge);
    }
    
    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @param v the vertex to validate 
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
}