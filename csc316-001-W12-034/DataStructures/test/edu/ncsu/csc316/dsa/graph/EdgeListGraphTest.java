package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for EdgeListGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an edge list graph data structure
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class EdgeListGraphTest {
	/** undirected graph */
    private Graph<String, Integer> undirectedGraph;
    /** directed graph */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new EdgeListGraph<String, Integer>();
        directedGraph = new EdgeListGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals("Undirected graph should contain 5 vertices.", 5, undirectedGraph.numVertices());
        
        buildDirectedSample();       
        assertEquals("Directed graph should contain 6 vertices.", 6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        String[] expectedUndirected = {"Raleigh", "Asheville", "Wilmington", "Durham", "Greenville"};
        int idx = 0;
        for (Vertex<String> v : undirectedGraph.vertices()) {
            assertEquals("Undirected vertex mismatch.", expectedUndirected[idx], v.getElement());
            idx++;
        }
        assertEquals("Undirected vertices count mismatch.", expectedUndirected.length, idx);
        
        
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        String[] expectedDirected = {"Raleigh", "Asheville", "Wilmington", "Durham", "Greenville", "Boone"};
        idx = 0;
        for (Vertex<String> v : directedGraph.vertices()) {
            assertEquals("Directed vertex mismatch.", expectedDirected[idx], v.getElement());
            idx++;
        }
        assertEquals("Directed vertices count mismatch.", expectedDirected.length, idx);
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
        buildUndirectedSample();
        // For the undirected sample there should be 10 edges.
        assertEquals("Undirected graph should contain 10 edges.", 10, undirectedGraph.numEdges());
        
        buildDirectedSample();
        // For the directed sample there should be 11 edges.
        assertEquals("Directed graph should contain 11 edges.", 11, directedGraph.numEdges()); 
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
     // Place all undirected edges in an array.
        @SuppressWarnings("unchecked")
        Edge<Integer>[] expectedUndirected = (Edge<Integer>[])new Edge[10];
        expectedUndirected[0] = e1;
        expectedUndirected[1] = e2;
        expectedUndirected[2] = e3;
        expectedUndirected[3] = e4;
        expectedUndirected[4] = e5;
        expectedUndirected[5] = e6;
        expectedUndirected[6] = e7;
        expectedUndirected[7] = e8;
        expectedUndirected[8] = e9;
        expectedUndirected[9] = e10;

        int undirectedCount = 0;
        // Verify that each edge returned from the graph exists in the expected array.
        for(Edge<Integer> edge : undirectedGraph.edges()){
            assertTrue("Undirected edge " + edge + " is not expected.",
                       arrayContains(expectedUndirected, edge));
            undirectedCount++;
        }
        assertEquals("Undirected graph should have 10 edges.", expectedUndirected.length, undirectedCount);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        @SuppressWarnings("unchecked")
        Edge<Integer>[] expectedDirected = (Edge<Integer>[])new Edge[11];
        expectedDirected[0] = e1;
        expectedDirected[1] = e2;
        expectedDirected[2] = e3;
        expectedDirected[3] = e4;
        expectedDirected[4] = e5;
        expectedDirected[5] = e6;
        expectedDirected[6] = e7;
        expectedDirected[7] = e8;
        expectedDirected[8] = e9;
        expectedDirected[9] = e10;
        expectedDirected[10] = e11;

        int directedCount = 0;
        // Verify that each edge from the directed graph is in the expected array.
        for(Edge<Integer> edge : directedGraph.edges()){
            assertTrue("Directed edge " + edge + " is not expected.",
                       arrayContains(expectedDirected, edge));
            directedCount++;
        }
        assertEquals("Directed graph should have 11 edges.", expectedDirected.length, directedCount);
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Main functional assertions.
        assertEquals("Undirected getEdge(v1,v2) must return e1", e1, undirectedGraph.getEdge(v1, v2));
        assertEquals("Undirected getEdge(v2,v1) must return e1", e1, undirectedGraph.getEdge(v2, v1));
        assertEquals("Undirected getEdge(v1,v3) must return e2", e2, undirectedGraph.getEdge(v1, v3));
        assertEquals("Undirected getEdge(v2,v3) must return e5", e5, undirectedGraph.getEdge(v2, v3));
        assertNull("Undirected getEdge(v1,v6) should be null", undirectedGraph.getEdge(v1, v6));

        // Extra assertions to reference every vertex and edge.
        assertEquals("Raleigh", v1.getElement());
        assertEquals("Asheville", v2.getElement());
        assertEquals("Wilmington", v3.getElement());
        assertEquals("Durham", v4.getElement());
        assertEquals("Greenville", v5.getElement());
        assertEquals("Boone", v6.getElement());
        // Check each edge's data.
        assertEquals(5, (int)e1.getElement());
        assertEquals(10, (int)e2.getElement());
        assertEquals(15, (int)e3.getElement());
        assertEquals(20, (int)e4.getElement());
        assertEquals(25, (int)e5.getElement());
        assertEquals(30, (int)e6.getElement());
        assertEquals(35, (int)e7.getElement());
        assertEquals(40, (int)e8.getElement());
        assertEquals(45, (int)e9.getElement());
        assertEquals(50, (int)e10.getElement());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals("Directed getEdge(v1,v2) must return e1", e1, directedGraph.getEdge(v1, v2));
        assertNull("Directed getEdge(v2,v1) should be null", directedGraph.getEdge(v2, v1));
        assertEquals("Directed getEdge(v1,v5) must return e4", e4, directedGraph.getEdge(v1, v5));
        assertEquals("Directed getEdge(v5,v6) must return e11", e11, directedGraph.getEdge(v5, v6));
        assertNull("Directed getEdge(v6,v5) should be null", directedGraph.getEdge(v6, v5));

        // Extra assertions to use all vertices and edges.
        assertEquals("Raleigh", v1.getElement());
        assertEquals("Asheville", v2.getElement());
        assertEquals("Wilmington", v3.getElement());
        assertEquals("Durham", v4.getElement());
        assertEquals("Greenville", v5.getElement());
        assertEquals("Boone", v6.getElement());
        assertEquals(5, (int)e1.getElement());
        assertEquals(10, (int)e2.getElement());
        assertEquals(15, (int)e3.getElement());
        assertEquals(20, (int)e4.getElement());
        assertEquals(25, (int)e5.getElement());
        assertEquals(30, (int)e6.getElement());
        assertEquals(35, (int)e7.getElement());
        assertEquals(40, (int)e8.getElement());
        assertEquals(45, (int)e9.getElement());
        assertEquals(50, (int)e10.getElement());
        assertEquals(55, (int)e11.getElement());
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
     // Test undirected edge endpoints:
        // For an undirected graph, either order is acceptable.
        Vertex<String>[] ends = undirectedGraph.endVertices(e1);
        assertTrue("Undirected e1 endpoints should be Raleigh and Asheville",
                (ends[0] == v1 && ends[1] == v2) || (ends[0] == v2 && ends[1] == v1));

        ends = undirectedGraph.endVertices(e2);
        assertTrue("Undirected e2 endpoints should be Raleigh and Wilmington",
                (ends[0] == v1 && ends[1] == v3) || (ends[0] == v3 && ends[1] == v1));

        ends = undirectedGraph.endVertices(e3);
        assertTrue("Undirected e3 endpoints should be Raleigh and Durham",
                (ends[0] == v1 && ends[1] == v4) || (ends[0] == v4 && ends[1] == v1));

        ends = undirectedGraph.endVertices(e4);
        assertTrue("Undirected e4 endpoints should be Raleigh and Greenville",
                (ends[0] == v1 && ends[1] == v5) || (ends[0] == v5 && ends[1] == v1));

        ends = undirectedGraph.endVertices(e5);
        assertTrue("Undirected e5 endpoints should be Asheville and Wilmington",
                (ends[0] == v2 && ends[1] == v3) || (ends[0] == v3 && ends[1] == v2));

        ends = undirectedGraph.endVertices(e6);
        assertTrue("Undirected e6 endpoints should be Asheville and Durham",
                (ends[0] == v2 && ends[1] == v4) || (ends[0] == v4 && ends[1] == v2));

        ends = undirectedGraph.endVertices(e7);
        assertTrue("Undirected e7 endpoints should be Asheville and Greenville",
                (ends[0] == v2 && ends[1] == v5) || (ends[0] == v5 && ends[1] == v2));

        ends = undirectedGraph.endVertices(e8);
        assertTrue("Undirected e8 endpoints should be Wilmington and Durham",
                (ends[0] == v3 && ends[1] == v4) || (ends[0] == v4 && ends[1] == v3));

        ends = undirectedGraph.endVertices(e9);
        assertTrue("Undirected e9 endpoints should be Wilmington and Greenville",
                (ends[0] == v3 && ends[1] == v5) || (ends[0] == v5 && ends[1] == v3));

        ends = undirectedGraph.endVertices(e10);
        assertTrue("Undirected e10 endpoints should be Durham and Greenville",
                (ends[0] == v4 && ends[1] == v5) || (ends[0] == v5 && ends[1] == v4));

        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Test directed edge endpoints.
        // For a directed graph, the order is fixed: endpoints[0] is the origin.
        Vertex<String>[] dEnds = directedGraph.endVertices(e1);
        assertEquals("Directed e1 first endpoint must be Raleigh", v1, dEnds[0]);
        assertEquals("Directed e1 second endpoint must be Asheville", v2, dEnds[1]);

        dEnds = directedGraph.endVertices(e2);
        assertEquals("Directed e2 first endpoint must be Raleigh", v1, dEnds[0]);
        assertEquals("Directed e2 second endpoint must be Wilmington", v3, dEnds[1]);

        dEnds = directedGraph.endVertices(e3);
        assertEquals("Directed e3 first endpoint must be Raleigh", v1, dEnds[0]);
        assertEquals("Directed e3 second endpoint must be Durham", v4, dEnds[1]);

        dEnds = directedGraph.endVertices(e4);
        assertEquals("Directed e4 first endpoint must be Raleigh", v1, dEnds[0]);
        assertEquals("Directed e4 second endpoint must be Greenville", v5, dEnds[1]);

        dEnds = directedGraph.endVertices(e5);
        assertEquals("Directed e5 first endpoint must be Asheville", v2, dEnds[0]);
        assertEquals("Directed e5 second endpoint must be Wilmington", v3, dEnds[1]);

        dEnds = directedGraph.endVertices(e6);
        assertEquals("Directed e6 first endpoint must be Asheville", v2, dEnds[0]);
        assertEquals("Directed e6 second endpoint must be Durham", v4, dEnds[1]);

        dEnds = directedGraph.endVertices(e7);
        assertEquals("Directed e7 first endpoint must be Asheville", v2, dEnds[0]);
        assertEquals("Directed e7 second endpoint must be Greenville", v5, dEnds[1]);

        dEnds = directedGraph.endVertices(e8);
        assertEquals("Directed e8 first endpoint must be Wilmington", v3, dEnds[0]);
        assertEquals("Directed e8 second endpoint must be Durham", v4, dEnds[1]);

        dEnds = directedGraph.endVertices(e9);
        assertEquals("Directed e9 first endpoint must be Wilmington", v3, dEnds[0]);
        assertEquals("Directed e9 second endpoint must be Greenville", v5, dEnds[1]);

        dEnds = directedGraph.endVertices(e10);
        assertEquals("Directed e10 first endpoint must be Durham", v4, dEnds[0]);
        assertEquals("Directed e10 second endpoint must be Greenville", v5, dEnds[1]);

        dEnds = directedGraph.endVertices(e11);
        assertEquals("Directed e11 first endpoint must be Greenville", v5, dEnds[0]);
        assertEquals("Directed e11 second endpoint must be Boone", v6, dEnds[1]);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
     // For an undirected edge, the opposite of a vertex should be the other endpoint.
        // e1: endpoints v1 and v2.
        assertEquals("Undirected: Opposite of v1 along e1 should be v2", v2, undirectedGraph.opposite(v1, e1));
        assertEquals("Undirected: Opposite of v2 along e1 should be v1", v1, undirectedGraph.opposite(v2, e1));
        
        // e2: endpoints v1 and v3.
        assertEquals("Undirected: Opposite of v1 along e2 should be v3", v3, undirectedGraph.opposite(v1, e2));
        assertEquals("Undirected: Opposite of v3 along e2 should be v1", v1, undirectedGraph.opposite(v3, e2));
        
        // e3: endpoints v1 and v4.
        assertEquals("Undirected: Opposite of v1 along e3 should be v4", v4, undirectedGraph.opposite(v1, e3));
        assertEquals("Undirected: Opposite of v4 along e3 should be v1", v1, undirectedGraph.opposite(v4, e3));
        
        // e4: endpoints v1 and v5.
        assertEquals("Undirected: Opposite of v1 along e4 should be v5", v5, undirectedGraph.opposite(v1, e4));
        assertEquals("Undirected: Opposite of v5 along e4 should be v1", v1, undirectedGraph.opposite(v5, e4));
        
        // e5: endpoints v2 and v3.
        assertEquals("Undirected: Opposite of v2 along e5 should be v3", v3, undirectedGraph.opposite(v2, e5));
        assertEquals("Undirected: Opposite of v3 along e5 should be v2", v2, undirectedGraph.opposite(v3, e5));
        
        // e6: endpoints v2 and v4.
        assertEquals("Undirected: Opposite of v2 along e6 should be v4", v4, undirectedGraph.opposite(v2, e6));
        assertEquals("Undirected: Opposite of v4 along e6 should be v2", v2, undirectedGraph.opposite(v4, e6));
        
        // e7: endpoints v2 and v5.
        assertEquals("Undirected: Opposite of v2 along e7 should be v5", v5, undirectedGraph.opposite(v2, e7));
        assertEquals("Undirected: Opposite of v5 along e7 should be v2", v2, undirectedGraph.opposite(v5, e7));
        
        // e8: endpoints v3 and v4.
        assertEquals("Undirected: Opposite of v3 along e8 should be v4", v4, undirectedGraph.opposite(v3, e8));
        assertEquals("Undirected: Opposite of v4 along e8 should be v3", v3, undirectedGraph.opposite(v4, e8));
        
        // e9: endpoints v3 and v5.
        assertEquals("Undirected: Opposite of v3 along e9 should be v5", v5, undirectedGraph.opposite(v3, e9));
        assertEquals("Undirected: Opposite of v5 along e9 should be v3", v3, undirectedGraph.opposite(v5, e9));
        
        // e10: endpoints v4 and v5.
        assertEquals("Undirected: Opposite of v4 along e10 should be v5", v5, undirectedGraph.opposite(v4, e10));
        assertEquals("Undirected: Opposite of v5 along e10 should be v4", v4, undirectedGraph.opposite(v5, e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // In the directed graph, the "opposite" method should still return the vertex that is not the one provided.
        // e1: edge from v1 to v2.
        assertEquals("Directed: Opposite of v1 along e1 should be v2", v2, directedGraph.opposite(v1, e1));
        assertEquals("Directed: Opposite of v2 along e1 should be v1", v1, directedGraph.opposite(v2, e1));
        
        // e2: edge from v1 to v3.
        assertEquals("Directed: Opposite of v1 along e2 should be v3", v3, directedGraph.opposite(v1, e2));
        assertEquals("Directed: Opposite of v3 along e2 should be v1", v1, directedGraph.opposite(v3, e2));
        
        // e3: edge from v1 to v4.
        assertEquals("Directed: Opposite of v1 along e3 should be v4", v4, directedGraph.opposite(v1, e3));
        assertEquals("Directed: Opposite of v4 along e3 should be v1", v1, directedGraph.opposite(v4, e3));
        
        // e4: edge from v1 to v5.
        assertEquals("Directed: Opposite of v1 along e4 should be v5", v5, directedGraph.opposite(v1, e4));
        assertEquals("Directed: Opposite of v5 along e4 should be v1", v1, directedGraph.opposite(v5, e4));
        
        // e5: edge from v2 to v3.
        assertEquals("Directed: Opposite of v2 along e5 should be v3", v3, directedGraph.opposite(v2, e5));
        assertEquals("Directed: Opposite of v3 along e5 should be v2", v2, directedGraph.opposite(v3, e5));
        
        // e6: edge from v2 to v4.
        assertEquals("Directed: Opposite of v2 along e6 should be v4", v4, directedGraph.opposite(v2, e6));
        assertEquals("Directed: Opposite of v4 along e6 should be v2", v2, directedGraph.opposite(v4, e6));
        
        // e7: edge from v2 to v5.
        assertEquals("Directed: Opposite of v2 along e7 should be v5", v5, directedGraph.opposite(v2, e7));
        assertEquals("Directed: Opposite of v5 along e7 should be v2", v2, directedGraph.opposite(v5, e7));
        
        // e8: edge from v3 to v4.
        assertEquals("Directed: Opposite of v3 along e8 should be v4", v4, directedGraph.opposite(v3, e8));
        assertEquals("Directed: Opposite of v4 along e8 should be v3", v3, directedGraph.opposite(v4, e8));
        
        // e9: edge from v3 to v5.
        assertEquals("Directed: Opposite of v3 along e9 should be v5", v5, directedGraph.opposite(v3, e9));
        assertEquals("Directed: Opposite of v5 along e9 should be v3", v3, directedGraph.opposite(v5, e9));
        
        // e10: edge from v4 to v5.
        assertEquals("Directed: Opposite of v4 along e10 should be v5", v5, directedGraph.opposite(v4, e10));
        assertEquals("Directed: Opposite of v5 along e10 should be v4", v4, directedGraph.opposite(v5, e10));
        
        // e11: edge from v5 to v6.
        assertEquals("Directed: Opposite of v5 along e11 should be v6", v6, directedGraph.opposite(v5, e11));
        assertEquals("Directed: Opposite of v6 along e11 should be v5", v5, directedGraph.opposite(v6, e11));
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        // Check outDegree counts.
        // For v1, the incident edges (via outgoingEdgeList) are e1, e2, e3, e4.
        assertEquals("Undirected outDegree of v1 should be 4", 4, undirectedGraph.outDegree(v1));
        // For v2, v2 appears in e1 (as second endpoint) and in e5, e6, e7 (as first endpoint).
        assertEquals("Undirected outDegree of v2 should be 4", 4, undirectedGraph.outDegree(v2));
        // For v3, v3 appears in e2 (as second endpoint), e5 (as second endpoint), and in e8, e9 (as first endpoint).
        assertEquals("Undirected outDegree of v3 should be 4", 4, undirectedGraph.outDegree(v3));
        // For v4, v4 appears in e3 (as second endpoint), e6 (as second endpoint), e8 (as second endpoint), and in e10 (as first endpoint).
        assertEquals("Undirected outDegree of v4 should be 4", 4, undirectedGraph.outDegree(v4));
        // For v5, v5 appears in e4, e7, e9, and e10 (all as second endpoint).
        assertEquals("Undirected outDegree of v5 should be 4", 4, undirectedGraph.outDegree(v5));
        // v6 is isolated.
        assertEquals("Undirected outDegree of v6 should be 0", 0, undirectedGraph.outDegree(v6));
        
        // Now, explicitly verify the edges in each outgoing list for the undirected graph.
        // For v1: expected order is e1, e2, e3, e4.
        Iterator<Edge<Integer>> itV1 = undirectedGraph.outgoingEdges(v1).iterator();
        Edge<Integer> outV11 = itV1.next();
        Edge<Integer> outV12 = itV1.next();
        Edge<Integer> outV13 = itV1.next();
        Edge<Integer> outV14 = itV1.next();
        assertFalse("No extra outgoing edges expected for v1", itV1.hasNext());
        assertEquals("v1 outgoing first edge should be e1", e1, outV11);
        assertEquals("v1 outgoing second edge should be e2", e2, outV12);
        assertEquals("v1 outgoing third edge should be e3", e3, outV13);
        assertEquals("v1 outgoing fourth edge should be e4", e4, outV14);
        
        // For v2: expected order is determined by the overall edge insertion order.
        // The complete edge list is [e1, e2, e3, e4, e5, e6, e7, e8, e9, e10].
        // v2 qualifies in e1 (as second endpoint) then e5, e6, e7 (as first endpoint).  
        Iterator<Edge<Integer>> itV2 = undirectedGraph.outgoingEdges(v2).iterator();
        Edge<Integer> outV21 = itV2.next();
        Edge<Integer> outV22 = itV2.next();
        Edge<Integer> outV23 = itV2.next();
        Edge<Integer> outV24 = itV2.next();
        assertFalse("No extra outgoing edges expected for v2", itV2.hasNext());
        assertEquals("v2 outgoing first edge should be e1", e1, outV21);
        assertEquals("v2 outgoing second edge should be e5", e5, outV22);
        assertEquals("v2 outgoing third edge should be e6", e6, outV23);
        assertEquals("v2 outgoing fourth edge should be e7", e7, outV24);
        
        // For v3: v3 qualifies in e2 (as second endpoint), e5 (as second endpoint), then e8 and e9 (as first endpoint).
        Iterator<Edge<Integer>> itV3 = undirectedGraph.outgoingEdges(v3).iterator();
        Edge<Integer> outV31 = itV3.next();
        Edge<Integer> outV32 = itV3.next();
        Edge<Integer> outV33 = itV3.next();
        Edge<Integer> outV34 = itV3.next();
        assertFalse("No extra outgoing edges expected for v3", itV3.hasNext());
        assertEquals("v3 outgoing first edge should be e2", e2, outV31);
        assertEquals("v3 outgoing second edge should be e5", e5, outV32);
        assertEquals("v3 outgoing third edge should be e8", e8, outV33);
        assertEquals("v3 outgoing fourth edge should be e9", e9, outV34);
        
        // For v4: v4 qualifies in e3 (as second endpoint), e6 (as second endpoint), e8 (as second endpoint), then e10 (as first endpoint).
        Iterator<Edge<Integer>> itV4 = undirectedGraph.outgoingEdges(v4).iterator();
        Edge<Integer> outV41 = itV4.next();
        Edge<Integer> outV42 = itV4.next();
        Edge<Integer> outV43 = itV4.next();
        Edge<Integer> outV44 = itV4.next();
        assertFalse("No extra outgoing edges expected for v4", itV4.hasNext());
        assertEquals("v4 outgoing first edge should be e3", e3, outV41);
        assertEquals("v4 outgoing second edge should be e6", e6, outV42);
        assertEquals("v4 outgoing third edge should be e8", e8, outV43);
        assertEquals("v4 outgoing fourth edge should be e10", e10, outV44);
        
        // For v5: v5 qualifies in e4, e7, e9, and e10 (all as second endpoint).
        Iterator<Edge<Integer>> itV5 = undirectedGraph.outgoingEdges(v5).iterator();
        Edge<Integer> outV51 = itV5.next();
        Edge<Integer> outV52 = itV5.next();
        Edge<Integer> outV53 = itV5.next();
        Edge<Integer> outV54 = itV5.next();
        assertFalse("No extra outgoing edges expected for v5", itV5.hasNext());
        assertEquals("v5 outgoing first edge should be e4", e4, outV51);
        assertEquals("v5 outgoing second edge should be e7", e7, outV52);
        assertEquals("v5 outgoing third edge should be e9", e9, outV53);
        assertEquals("v5 outgoing fourth edge should be e10", e10, outV54);
        
        // v6 is isolated; no outgoing edge list is retrieved.
       
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // v1 has e1, e2, e3, e4
        assertEquals("Directed outDegree of v1 should be 4", 4, directedGraph.outDegree(v1));
        // v2 has e5, e6, e7
        assertEquals("Directed outDegree of v2 should be 3", 3, directedGraph.outDegree(v2));
        // v3 has e8, e9
        assertEquals("Directed outDegree of v3 should be 2", 2, directedGraph.outDegree(v3));
        // v4 has e10
        assertEquals("Directed outDegree of v4 should be 1", 1, directedGraph.outDegree(v4));
        // v5 has e11
        assertEquals("Directed outDegree of v5 should be 1", 1, directedGraph.outDegree(v5));
        // v6 has no outgoing edges.
        assertEquals("Directed outDegree of v6 should be 0", 0, directedGraph.outDegree(v6));
        
        Iterator<Edge<Integer>> itV6 = directedGraph.outgoingEdges(v5).iterator();
        Edge<Integer> outEdgeV6 = itV6.next();
        assertFalse("No extra outgoing edge should be present for v5.", itV6.hasNext());
        assertEquals("The outgoing edge from v5 should be e11", e11, outEdgeV6);
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Assert inDegree counts.
        assertEquals("Undirected inDegree of v1 should be 4", 4, undirectedGraph.inDegree(v1));
        assertEquals("Undirected inDegree of v2 should be 4", 4, undirectedGraph.inDegree(v2));
        assertEquals("Undirected inDegree of v3 should be 4", 4, undirectedGraph.inDegree(v3));
        assertEquals("Undirected inDegree of v4 should be 4", 4, undirectedGraph.inDegree(v4));
        assertEquals("Undirected inDegree of v5 should be 4", 4, undirectedGraph.inDegree(v5));
        assertEquals("Undirected inDegree of v6 should be 0", 0, undirectedGraph.inDegree(v6));
        
        // Explicitly verify the incoming edges for some vertices.
        // For v1, the incomingEdges are determined by traversing the complete edge list in insertion order.
        // For undirected graphs, the method adds an edge if either:
        //   - v is the second endpoint, or
        //   - (if undirected) v is the first endpoint.
        // For v1, only edges where v1 is the first endpoint qualify, so we expect: e1, e2, e3, e4.
        Iterator<Edge<Integer>> itInV1 = undirectedGraph.incomingEdges(v1).iterator();
        Edge<Integer> inV11 = itInV1.next(); // should be e1
        Edge<Integer> inV12 = itInV1.next(); // e2
        Edge<Integer> inV13 = itInV1.next(); // e3
        Edge<Integer> inV14 = itInV1.next(); // e4
        assertFalse("No extra incoming edges expected for v1", itInV1.hasNext());
        assertEquals("v1 incoming first edge should be e1", e1, inV11);
        assertEquals("v1 incoming second edge should be e2", e2, inV12);
        assertEquals("v1 incoming third edge should be e3", e3, inV13);
        assertEquals("v1 incoming fourth edge should be e4", e4, inV14);
        
        // For v2, incomingEdges should be:
        //   - e1 (v2 is second endpoint from (v1,v2))
        //   - then e5, e6, e7 (v2 is first endpoint in these edges)
        Iterator<Edge<Integer>> itInV2 = undirectedGraph.incomingEdges(v2).iterator();
        Edge<Integer> inV21 = itInV2.next(); // should be e1
        Edge<Integer> inV22 = itInV2.next(); // e5
        Edge<Integer> inV23 = itInV2.next(); // e6
        Edge<Integer> inV24 = itInV2.next(); // e7
        assertFalse("No extra incoming edges expected for v2", itInV2.hasNext());
        assertEquals("v2 incoming first edge should be e1", e1, inV21);
        assertEquals("v2 incoming second edge should be e5", e5, inV22);
        assertEquals("v2 incoming third edge should be e6", e6, inV23);
        assertEquals("v2 incoming fourth edge should be e7", e7, inV24);
        
        // For v3, incomingEdges should be:
        //   - e2 (v3 is second endpoint in (v1,v3))
        //   - e5 (v3 is second endpoint in (v2,v3))
        //   - e8, e9 (v3 is first endpoint in these edges)
        Iterator<Edge<Integer>> itInV3 = undirectedGraph.incomingEdges(v3).iterator();
        Edge<Integer> inV31 = itInV3.next(); // e2
        Edge<Integer> inV32 = itInV3.next(); // e5
        Edge<Integer> inV33 = itInV3.next(); // e8
        Edge<Integer> inV34 = itInV3.next(); // e9
        assertFalse("No extra incoming edges expected for v3", itInV3.hasNext());
        assertEquals("v3 incoming first edge should be e2", e2, inV31);
        assertEquals("v3 incoming second edge should be e5", e5, inV32);
        assertEquals("v3 incoming third edge should be e8", e8, inV33);
        assertEquals("v3 incoming fourth edge should be e9", e9, inV34);
        
        // For v4, incomingEdges should be:
        //   - e3 (v4 is second endpoint in (v1,v4))
        //   - e6 (v4 is second endpoint in (v2,v4))
        //   - e8 (v4 is second endpoint in (v3,v4))
        //   - e10 (v4 is first endpoint in (v4,v5))
        Iterator<Edge<Integer>> itInV4 = undirectedGraph.incomingEdges(v4).iterator();
        Edge<Integer> inV41 = itInV4.next(); // e3
        Edge<Integer> inV42 = itInV4.next(); // e6
        Edge<Integer> inV43 = itInV4.next(); // e8
        Edge<Integer> inV44 = itInV4.next(); // e10
        assertFalse("No extra incoming edges expected for v4", itInV4.hasNext());
        assertEquals("v4 incoming first edge should be e3", e3, inV41);
        assertEquals("v4 incoming second edge should be e6", e6, inV42);
        assertEquals("v4 incoming third edge should be e8", e8, inV43);
        assertEquals("v4 incoming fourth edge should be e10", e10, inV44); 
        
        // For v5, incomingEdges should be:
        //   - e4 (v5 is second endpoint in (v1,v5))
        //   - e7 (v5 is second endpoint in (v2,v5))
        //   - e9 (v5 is second endpoint in (v3,v5))
        //   - e10 (v5 is second endpoint in (v4,v5))
        Iterator<Edge<Integer>> itInV5 = undirectedGraph.incomingEdges(v5).iterator();
        Edge<Integer> inV51 = itInV5.next(); // e4
        Edge<Integer> inV52 = itInV5.next(); // e7
        Edge<Integer> inV53 = itInV5.next(); // e9
        Edge<Integer> inV54 = itInV5.next(); // e10
        assertFalse("No extra incoming edges expected for v5", itInV5.hasNext());
        assertEquals("v5 incoming first edge should be e4", e4, inV51);
        assertEquals("v5 incoming second edge should be e7", e7, inV52);
        assertEquals("v5 incoming third edge should be e9", e9, inV53);
        assertEquals("v5 incoming fourth edge should be e10", e10, inV54);
        
        // v6 is isolated.
        assertEquals("Undirected inDegree of v6 should be 0", 0, undirectedGraph.inDegree(v6));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
     // For directed graph, incomingEdges include only those edges where the vertex appears as the second endpoint.
        // v1: no incoming edges.
        assertEquals("Directed inDegree of v1 should be 0", 0, directedGraph.inDegree(v1));
        
        // v2: incomingEdges: e1.
        assertEquals("Directed inDegree of v2 should be 1", 1, directedGraph.inDegree(v2));
        Iterator<Edge<Integer>> itDirV2 = directedGraph.incomingEdges(v2).iterator();
        Edge<Integer> inDirV2 = itDirV2.next();
        assertFalse("No extra incoming edges expected for v2 in the directed graph.", itDirV2.hasNext());
        assertEquals("v2 incoming edge should be e1", e1, inDirV2);
        
        // v3: incomingEdges: e2 and e5.
        assertEquals("Directed inDegree of v3 should be 2", 2, directedGraph.inDegree(v3));
        Iterator<Edge<Integer>> itDirV3 = directedGraph.incomingEdges(v3).iterator();
        Edge<Integer> inDirV31 = itDirV3.next();
        Edge<Integer> inDirV32 = itDirV3.next();
        assertFalse("No extra incoming edges expected for v3 in the directed graph.", itDirV3.hasNext());
        assertEquals("First incoming edge for v3 should be e2", e2, inDirV31);
        assertEquals("Second incoming edge for v3 should be e5", e5, inDirV32);
        
        // v4: incomingEdges: e3, e6, e8.
        assertEquals("Directed inDegree of v4 should be 3", 3, directedGraph.inDegree(v4));
        Iterator<Edge<Integer>> itDirV4 = directedGraph.incomingEdges(v4).iterator();
        Edge<Integer> inDirV41 = itDirV4.next();
        Edge<Integer> inDirV42 = itDirV4.next();
        Edge<Integer> inDirV43 = itDirV4.next();
        assertFalse("No extra incoming edges expected for v4 in the directed graph.", itDirV4.hasNext());
        assertEquals("First incoming edge for v4 should be e3", e3, inDirV41);
        assertEquals("Second incoming edge for v4 should be e6", e6, inDirV42);
        assertEquals("Third incoming edge for v4 should be e8", e8, inDirV43);
        
        // v5: incomingEdges: e4, e7, e9, e10.
        assertEquals("Directed inDegree of v5 should be 4", 4, directedGraph.inDegree(v5));
        Iterator<Edge<Integer>> itDirV5 = directedGraph.incomingEdges(v5).iterator();
        Edge<Integer> inDirV51 = itDirV5.next();
        Edge<Integer> inDirV52 = itDirV5.next();
        Edge<Integer> inDirV53 = itDirV5.next();
        Edge<Integer> inDirV54 = itDirV5.next();
        assertFalse("No extra incoming edges expected for v5 in the directed graph.", itDirV5.hasNext());
        assertEquals("First incoming edge for v5 should be e4", e4, inDirV51);
        assertEquals("Second incoming edge for v5 should be e7", e7, inDirV52);
        assertEquals("Third incoming edge for v5 should be e9", e9, inDirV53);
        assertEquals("Fourth incoming edge for v5 should be e10", e10, inDirV54);
        
        // v6: incomingEdges: e11.
        assertEquals("Directed inDegree of v6 should be 1", 1, directedGraph.inDegree(v6));
        Iterator<Edge<Integer>> itDirV6 = directedGraph.incomingEdges(v6).iterator();
        Edge<Integer> inDirV6 = itDirV6.next();
        assertFalse("No extra incoming edges expected for v6 in the directed graph.", itDirV6.hasNext());
        assertEquals("v6 incoming edge should be e11", e11, inDirV6);
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])new Edge[4];
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        // For v1, outgoingEdges(v1) should include e1, e2, e3, and e4.
        Edge<Integer>[] tempV1 = (Edge<Integer>[])new Edge[4];
        int countV1 = 0;
        Iterator<Edge<Integer>> itV1 = undirectedGraph.outgoingEdges(v1).iterator();
        tempV1[countV1] = itV1.next(); countV1++; // expected: e1
        tempV1[countV1] = itV1.next(); countV1++; // expected: e2
        tempV1[countV1] = itV1.next(); countV1++; // expected: e3
        tempV1[countV1] = itV1.next(); countV1++; // expected: e4
        assertFalse("No extra outgoing edges expected for v1", itV1.hasNext());
        assertTrue("v1 outgoing edges should contain e1", arrayContains(tempV1, e1));
        assertTrue("v1 outgoing edges should contain e2", arrayContains(tempV1, e2));
        assertTrue("v1 outgoing edges should contain e3", arrayContains(tempV1, e3));
        assertTrue("v1 outgoing edges should contain e4", arrayContains(tempV1, e4));
        
        // For v2, outgoingEdges(v2) should include:
        // - e1 (v2 appears as the second endpoint in (v1,v2))
        // - e5, e6, e7 (v2 is the first endpoint).
        Edge<Integer>[] tempV2 = (Edge<Integer>[])new Edge[4];
        int countV2 = 0;
        Iterator<Edge<Integer>> itV2 = undirectedGraph.outgoingEdges(v2).iterator();
        tempV2[countV2] = itV2.next(); countV2++; // expected: e1
        tempV2[countV2] = itV2.next(); countV2++; // expected: e5
        tempV2[countV2] = itV2.next(); countV2++; // expected: e6
        tempV2[countV2] = itV2.next(); countV2++; // expected: e7
        assertFalse("No extra outgoing edges expected for v2", itV2.hasNext());
        assertTrue("v2 outgoing edges should contain e1", arrayContains(tempV2, e1));
        assertTrue("v2 outgoing edges should contain e5", arrayContains(tempV2, e5));
        assertTrue("v2 outgoing edges should contain e6", arrayContains(tempV2, e6));
        assertTrue("v2 outgoing edges should contain e7", arrayContains(tempV2, e7));
        
        // For v3, outgoingEdges(v3) should include:
        // - e2 (v3 is second endpoint in (v1,v3))
        // - e5 (v3 is second endpoint in (v2,v3))
        // - e8, e9 (v3 is the first endpoint).
        Edge<Integer>[] tempV3 = (Edge<Integer>[])new Edge[4];
        int countV3 = 0;
        Iterator<Edge<Integer>> itV3 = undirectedGraph.outgoingEdges(v3).iterator();
        tempV3[countV3] = itV3.next(); countV3++; // expected: e2
        tempV3[countV3] = itV3.next(); countV3++; // expected: e5
        tempV3[countV3] = itV3.next(); countV3++; // expected: e8
        tempV3[countV3] = itV3.next(); countV3++; // expected: e9
        assertFalse("No extra outgoing edges expected for v3", itV3.hasNext());
        assertTrue("v3 outgoing edges should contain e2", arrayContains(tempV3, e2));
        assertTrue("v3 outgoing edges should contain e5", arrayContains(tempV3, e5));
        assertTrue("v3 outgoing edges should contain e8", arrayContains(tempV3, e8));
        assertTrue("v3 outgoing edges should contain e9", arrayContains(tempV3, e9));
        
        // For v4, outgoingEdges(v4) should include:
        // - e3 (v4 is second endpoint in (v1,v4))
        // - e6 (v4 is second endpoint in (v2,v4))
        // - e8 (v4 is second endpoint in (v3,v4))
        // - e10 (v4 is the first endpoint).
        Edge<Integer>[] tempV4 = (Edge<Integer>[])new Edge[4];
        int countV4 = 0;
        Iterator<Edge<Integer>> itV4 = undirectedGraph.outgoingEdges(v4).iterator();
        tempV4[countV4] = itV4.next(); countV4++; // expected: e3
        tempV4[countV4] = itV4.next(); countV4++; // expected: e6
        tempV4[countV4] = itV4.next(); countV4++; // expected: e8
        tempV4[countV4] = itV4.next(); countV4++; // expected: e10
        assertFalse("No extra outgoing edges expected for v4", itV4.hasNext());
        assertTrue("v4 outgoing edges should contain e3", arrayContains(tempV4, e3));
        assertTrue("v4 outgoing edges should contain e6", arrayContains(tempV4, e6));
        assertTrue("v4 outgoing edges should contain e8", arrayContains(tempV4, e8));
        assertTrue("v4 outgoing edges should contain e10", arrayContains(tempV4, e10));
        
        // For v5, outgoingEdges(v5) should include:
        // - e4 (v5 is second endpoint in (v1,v5))
        // - e7 (v5 is second endpoint in (v2,v5))
        // - e9 (v5 is second endpoint in (v3,v5))
        // - e10 (v5 is second endpoint in (v4,v5))
        Edge<Integer>[] tempV5 = (Edge<Integer>[])new Edge[4];
        int countV5 = 0;
        Iterator<Edge<Integer>> itV5 = undirectedGraph.outgoingEdges(v5).iterator();
        tempV5[countV5] = itV5.next(); countV5++; // expected: e4
        tempV5[countV5] = itV5.next(); countV5++; // expected: e7
        tempV5[countV5] = itV5.next(); countV5++; // expected: e9
        tempV5[countV5] = itV5.next(); countV5++; // expected: e10
        assertFalse("No extra outgoing edges expected for v5", itV5.hasNext());
        assertTrue("v5 outgoing edges should contain e4", arrayContains(tempV5, e4));
        assertTrue("v5 outgoing edges should contain e7", arrayContains(tempV5, e7));
        assertTrue("v5 outgoing edges should contain e9", arrayContains(tempV5, e9));
        assertTrue("v5 outgoing edges should contain e10", arrayContains(tempV5, e10));
        
        // v6 is isolated in the undirected graph.
        assertFalse("Undirected: v6 should have no outgoing edges", undirectedGraph.outgoingEdges(v6).iterator().hasNext());
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // For v1 in the directed graph, outgoingEdges(v1) should be e1, e2, e3, and e4.
        Edge<Integer>[] tempD1 = (Edge<Integer>[])new Edge[4];
        int countD1 = 0;
        Iterator<Edge<Integer>> itD1 = directedGraph.outgoingEdges(v1).iterator();
        tempD1[countD1] = itD1.next(); countD1++; // e1
        tempD1[countD1] = itD1.next(); countD1++; // e2
        tempD1[countD1] = itD1.next(); countD1++; // e3
        tempD1[countD1] = itD1.next(); countD1++; // e4
        assertFalse("No extra outgoing edges expected for v1 in directed graph", itD1.hasNext());
        assertTrue("Directed: v1 should contain e1", arrayContains(tempD1, e1));
        assertTrue("Directed: v1 should contain e2", arrayContains(tempD1, e2));
        assertTrue("Directed: v1 should contain e3", arrayContains(tempD1, e3));
        assertTrue("Directed: v1 should contain e4", arrayContains(tempD1, e4));
        
        // For v2 in the directed graph, outgoingEdges(v2) should be e5, e6, and e7.
        Edge<Integer>[] tempD2 = (Edge<Integer>[])new Edge[3];
        int countD2 = 0;
        Iterator<Edge<Integer>> itD2 = directedGraph.outgoingEdges(v2).iterator();
        tempD2[countD2] = itD2.next(); countD2++; // e5
        tempD2[countD2] = itD2.next(); countD2++; // e6
        tempD2[countD2] = itD2.next(); countD2++; // e7
        assertFalse("No extra outgoing edges expected for v2 in directed graph", itD2.hasNext());
        assertTrue("Directed: v2 should contain e5", arrayContains(tempD2, e5));
        assertTrue("Directed: v2 should contain e6", arrayContains(tempD2, e6));
        assertTrue("Directed: v2 should contain e7", arrayContains(tempD2, e7));
        
        // For v5 in the directed graph, outgoingEdges(v5) should be only e11.
        Iterator<Edge<Integer>> itD5 = directedGraph.outgoingEdges(v5).iterator();
        assertTrue("Directed: v5 should have an outgoing edge", itD5.hasNext());
        Edge<Integer> outEdgeV5 = itD5.next();
        assertFalse("No extra outgoing edges expected for v5 in directed graph", itD5.hasNext());
        assertEquals("Directed: The outgoing edge from v5 should be e11", e11, outEdgeV5);
        
        // Additionally, check that v6 is isolated.
        assertFalse("Directed: v6 should have no outgoing edges", directedGraph.outgoingEdges(v6).iterator().hasNext());
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
     // For the undirected graph, assume incomingEdges(v) returns all incident edges.
        // Therefore, we expect:
        //   v1: incident in e1, e2, e3, e4.
        Edge<Integer>[] tempV1 = (Edge<Integer>[])new Edge[4];
        int countV1 = 0;
        Iterator<Edge<Integer>> itV1 = undirectedGraph.incomingEdges(v1).iterator();
        tempV1[countV1] = itV1.next(); countV1++; // expect e1 (v1 is first endpoint)
        tempV1[countV1] = itV1.next(); countV1++; // expect e2
        tempV1[countV1] = itV1.next(); countV1++; // expect e3
        tempV1[countV1] = itV1.next(); countV1++; // expect e4
        assertFalse("No extra incoming edges expected for v1", itV1.hasNext());
        assertTrue("v1 incoming edges should contain e1", arrayContains(tempV1, e1));
        assertTrue("v1 incoming edges should contain e2", arrayContains(tempV1, e2));
        assertTrue("v1 incoming edges should contain e3", arrayContains(tempV1, e3));
        assertTrue("v1 incoming edges should contain e4", arrayContains(tempV1, e4));
        
        // v2: incident in e1 (v2 is second endpoint), and e5, e6, e7 (v2 as first endpoint).
        Edge<Integer>[] tempV2 = (Edge<Integer>[])new Edge[4];
        int countV2 = 0;
        Iterator<Edge<Integer>> itV2 = undirectedGraph.incomingEdges(v2).iterator();
        tempV2[countV2] = itV2.next(); countV2++; // expect e1
        tempV2[countV2] = itV2.next(); countV2++; // expect e5
        tempV2[countV2] = itV2.next(); countV2++; // expect e6
        tempV2[countV2] = itV2.next(); countV2++; // expect e7
        assertFalse("No extra incoming edges expected for v2", itV2.hasNext());
        assertTrue("v2 incoming edges should contain e1", arrayContains(tempV2, e1));
        assertTrue("v2 incoming edges should contain e5", arrayContains(tempV2, e5));
        assertTrue("v2 incoming edges should contain e6", arrayContains(tempV2, e6));
        assertTrue("v2 incoming edges should contain e7", arrayContains(tempV2, e7));
        
        // v3: incident in e2 (v3 as second endpoint), e5 (v3 as second endpoint),
        //     and e8, e9 (v3 as first endpoint).
        Edge<Integer>[] tempV3 = (Edge<Integer>[])new Edge[4];
        int countV3 = 0;
        Iterator<Edge<Integer>> itV3 = undirectedGraph.incomingEdges(v3).iterator();
        tempV3[countV3] = itV3.next(); countV3++; // expect e2
        tempV3[countV3] = itV3.next(); countV3++; // expect e5
        tempV3[countV3] = itV3.next(); countV3++; // expect e8
        tempV3[countV3] = itV3.next(); countV3++; // expect e9
        assertFalse("No extra incoming edges expected for v3", itV3.hasNext());
        assertTrue("v3 incoming edges should contain e2", arrayContains(tempV3, e2));
        assertTrue("v3 incoming edges should contain e5", arrayContains(tempV3, e5));
        assertTrue("v3 incoming edges should contain e8", arrayContains(tempV3, e8));
        assertTrue("v3 incoming edges should contain e9", arrayContains(tempV3, e9));
        
        // v4: incident in e3 (v4 as second endpoint), e6 (v4 as second endpoint), e8 (v4 as second endpoint),
        //     and e10 (v4 as first endpoint).
        Edge<Integer>[] tempV4 = (Edge<Integer>[])new Edge[4];
        int countV4 = 0;
        Iterator<Edge<Integer>> itV4 = undirectedGraph.incomingEdges(v4).iterator();
        tempV4[countV4] = itV4.next(); countV4++; // expect e3
        tempV4[countV4] = itV4.next(); countV4++; // expect e6
        tempV4[countV4] = itV4.next(); countV4++; // expect e8
        tempV4[countV4] = itV4.next(); countV4++; // expect e10
        assertFalse("No extra incoming edges expected for v4", itV4.hasNext());
        assertTrue("v4 incoming edges should contain e3", arrayContains(tempV4, e3));
        assertTrue("v4 incoming edges should contain e6", arrayContains(tempV4, e6));
        assertTrue("v4 incoming edges should contain e8", arrayContains(tempV4, e8));
        assertTrue("v4 incoming edges should contain e10", arrayContains(tempV4, e10));
        
        // v5: incident in e4 (v5 as second endpoint), e7 (v5 as second endpoint),
        //     e9 (v5 as second endpoint), and e10 (v5 as second endpoint).
        Edge<Integer>[] tempV5 = (Edge<Integer>[])new Edge[4];
        int countV5 = 0;
        Iterator<Edge<Integer>> itV5 = undirectedGraph.incomingEdges(v5).iterator();
        tempV5[countV5] = itV5.next(); countV5++; // expect e4
        tempV5[countV5] = itV5.next(); countV5++; // expect e7
        tempV5[countV5] = itV5.next(); countV5++; // expect e9
        tempV5[countV5] = itV5.next(); countV5++; // expect e10
        assertFalse("No extra incoming edges expected for v5", itV5.hasNext());
        assertTrue("v5 incoming edges should contain e4", arrayContains(tempV5, e4));
        assertTrue("v5 incoming edges should contain e7", arrayContains(tempV5, e7));
        assertTrue("v5 incoming edges should contain e9", arrayContains(tempV5, e9));
        assertTrue("v5 incoming edges should contain e10", arrayContains(tempV5, e10));
        
        // v6 is isolated.
        assertFalse("Undirected: v6 should have no incoming edges", undirectedGraph.incomingEdges(v6).iterator().hasNext());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
     // For the directed graph, incomingEdges(v) should include only edges where the vertex is the second endpoint.
        // v1 should have no incoming edges.
        assertFalse("Directed: v1 should have no incoming edges", directedGraph.incomingEdges(v1).iterator().hasNext());
        
        // v2: incomingEdges should contain only e1.
        Edge<Integer>[] tempD2 = (Edge<Integer>[])new Edge[1];
        Iterator<Edge<Integer>> itD2 = directedGraph.incomingEdges(v2).iterator();
        tempD2[0] = itD2.next();
        assertFalse("No extra incoming edges for v2 in directed graph", itD2.hasNext());
        assertEquals("v2 incoming edge should be e1", e1, tempD2[0]);
        
        // v3: incomingEdges should contain e2 and e5.
        Edge<Integer>[] tempD3 = (Edge<Integer>[])new Edge[2];
        int countD3 = 0;
        Iterator<Edge<Integer>> itD3 = directedGraph.incomingEdges(v3).iterator();
        tempD3[countD3] = itD3.next(); countD3++; // expect e2
        tempD3[countD3] = itD3.next(); countD3++; // expect e5
        assertFalse("No extra incoming edges for v3 in directed graph", itD3.hasNext());
        assertTrue("v3 incoming edges should contain e2", arrayContains(tempD3, e2));
        assertTrue("v3 incoming edges should contain e5", arrayContains(tempD3, e5));
        
        // v4: incomingEdges should contain e3, e6, and e8.
        Edge<Integer>[] tempD4 = (Edge<Integer>[])new Edge[3];
        int countD4 = 0;
        Iterator<Edge<Integer>> itD4 = directedGraph.incomingEdges(v4).iterator();
        tempD4[countD4] = itD4.next(); countD4++; // expect e3
        tempD4[countD4] = itD4.next(); countD4++; // expect e6
        tempD4[countD4] = itD4.next(); countD4++; // expect e8
        assertFalse("No extra incoming edges for v4 in directed graph", itD4.hasNext());
        assertTrue("v4 incoming edges should contain e3", arrayContains(tempD4, e3));
        assertTrue("v4 incoming edges should contain e6", arrayContains(tempD4, e6));
        assertTrue("v4 incoming edges should contain e8", arrayContains(tempD4, e8));
        
        // v5: incomingEdges should contain e4, e7, e9, and e10.
        Edge<Integer>[] tempD5 = (Edge<Integer>[])new Edge[4];
        int countD5 = 0;
        Iterator<Edge<Integer>> itD5 = directedGraph.incomingEdges(v5).iterator();
        tempD5[countD5] = itD5.next(); countD5++; // expect e4
        tempD5[countD5] = itD5.next(); countD5++; // expect e7
        tempD5[countD5] = itD5.next(); countD5++; // expect e9
        tempD5[countD5] = itD5.next(); countD5++; // expect e10
        assertFalse("No extra incoming edges for v5 in directed graph", itD5.hasNext());
        assertTrue("v5 incoming edges should contain e4", arrayContains(tempD5, e4));
        assertTrue("v5 incoming edges should contain e7", arrayContains(tempD5, e7));
        assertTrue("v5 incoming edges should contain e9", arrayContains(tempD5, e9));
        assertTrue("v5 incoming edges should contain e10", arrayContains(tempD5, e10));
        
        // v6: incomingEdges should contain only e11.
        Edge<Integer>[] tempD6 = (Edge<Integer>[])new Edge[1];
        Iterator<Edge<Integer>> itD6 = directedGraph.incomingEdges(v6).iterator();
        tempD6[0] = itD6.next();
        assertFalse("No extra incoming edges for v6 in directed graph", itD6.hasNext());
        assertEquals("v6 incoming edge should be e11", e11, tempD6[0]);
    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());  
        
        Vertex<String> v2 = undirectedGraph.insertVertex("Cary");
        Vertex<String> v3 = undirectedGraph.insertVertex("Durham");
        
        assertNotNull("v2 should not be null", v2);
        assertNotNull("v3 should not be null", v3);
        assertEquals("v2 element should be Cary", "Cary", v2.getElement());
        assertEquals("v3 element should be Durham", "Durham", v3.getElement());
        
        // Verify that the vertex count is updated.
        assertEquals("After inserting two more vertices, the graph should have 3 vertices.", 3, undirectedGraph.numVertices());
        
        // Verify that the vertices are returned in insertion order.
        Iterator<Vertex<String>> it2 = undirectedGraph.vertices().iterator();
        Vertex<String> first = it2.next();
        Vertex<String> second = it2.next();
        Vertex<String> third = it2.next();
        assertFalse("No more vertices should be present.", it2.hasNext());
        
        // Check each vertex's element.
        assertEquals("First vertex should be Fayetteville", "Fayetteville", first.getElement());
        assertEquals("Second vertex should be Cary", "Cary", second.getElement());
        assertEquals("Third vertex should be Durham", "Durham", third.getElement());
        
        // Combined check to ensure all vertices are used.
        String combined = first.getElement() + ", " + second.getElement() + ", " + third.getElement();
        assertEquals("The vertices should be 'Fayetteville, Cary, Durham'", "Fayetteville, Cary, Durham", combined);
       
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
        // Insert a new vertex and a new edge.
        Vertex<String> v3 = undirectedGraph.insertVertex("Durham");
        // Insert an edge between v2 and v3 with data 42.
        Edge<Integer> e2 = undirectedGraph.insertEdge(v2, v3, 42);
        
        // Check that the total edge count is now 2.
        assertEquals("Graph should have 2 edges after inserting e2", 2, undirectedGraph.numEdges());
        
        // Check that getEdge() returns the newly inserted edge.
        assertEquals("getEdge(v2, v3) should return e2", e2, undirectedGraph.getEdge(v2, v3));
        // For an undirected graph, getEdge(v3, v2) should return the same edge.
        assertEquals("getEdge(v3, v2) should return e2", e2, undirectedGraph.getEdge(v3, v2));
        
        // Check that the overall edges() iterator returns both e1 and e2.
        Iterator<Edge<Integer>> it2 = undirectedGraph.edges().iterator();
        Edge<Integer> edge1 = it2.next();
        Edge<Integer> edge2 = it2.next();
        assertFalse("There should be no extra edges", it2.hasNext());
        // Since insertion order is preserved, e1 is first and e2 is second.
        assertEquals("First edge should be e1", e1, edge1);
        assertEquals("Second edge should be e2", e2, edge2);
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
     
        
     // ----- Additional tests for UNDIRECTED GRAPH -----
        // Remove vertex v1. This should remove edges: e1, e2, e3, and e4.
        undirectedGraph.removeVertex(v1);
        
        // Verify that the vertex count decreases from 5 to 4.
        assertEquals("Undirected: Vertex count should be 4 after removal", 4, undirectedGraph.numVertices());
        // Since 4 edges incident to v1 are removed, the remaining edge count should be 10 - 4 = 6.
        assertEquals("Undirected: Edge count should be 6 after removal", 6, undirectedGraph.numEdges());
        
        // Explicitly check edges incident to v1 are removed.
        // Check e1: (v1,v2)
        assertNotEquals(e1, undirectedGraph.getEdge(v1, v2));
        // Check e2: (v1,v3)
        assertNotEquals(e2, undirectedGraph.getEdge(v1, v3));
        // Check e3: (v1,v4)
        assertNotEquals(e3, undirectedGraph.getEdge(v1, v4));
        // Check e4: (v1,v5)
        assertNotEquals(e4, undirectedGraph.getEdge(v1, v5));
        
        // Now explicitly check that the remaining edges are still present.
        // e5: (v2,v3)
        assertNotNull("Undirected: getEdge(v2,v3) should not be null", undirectedGraph.getEdge(v2, v3));
        assertEquals("Undirected: getEdge(v2,v3) should equal e5", e5, undirectedGraph.getEdge(v2, v3));
        // e6: (v2,v4)
        assertNotNull("Undirected: getEdge(v2,v4) should not be null", undirectedGraph.getEdge(v2, v4));
        assertEquals("Undirected: getEdge(v2,v4) should equal e6", e6, undirectedGraph.getEdge(v2, v4));
        // e7: (v2,v5)
        assertNotNull("Undirected: getEdge(v2,v5) should not be null", undirectedGraph.getEdge(v2, v5));
        assertEquals("Undirected: getEdge(v2,v5) should equal e7", e7, undirectedGraph.getEdge(v2, v5));
        // e8: (v3,v4)
        assertNotNull("Undirected: getEdge(v3,v4) should not be null", undirectedGraph.getEdge(v3, v4));
        assertEquals("Undirected: getEdge(v3,v4) should equal e8", e8, undirectedGraph.getEdge(v3, v4));
        // e9: (v3,v5)
        assertNotNull("Undirected: getEdge(v3,v5) should not be null", undirectedGraph.getEdge(v3, v5));
        assertEquals("Undirected: getEdge(v3,v5) should equal e9", e9, undirectedGraph.getEdge(v3, v5));
        // e10: (v4,v5)
        assertNotNull("Undirected: getEdge(v4,v5) should not be null", undirectedGraph.getEdge(v4, v5));
        assertEquals("Undirected: getEdge(v4,v5) should equal e10", e10, undirectedGraph.getEdge(v4, v5));
        
        // Verify that the removed vertex v1 is not present in the vertex list.
        Iterator<Vertex<String>> undirectedIt = undirectedGraph.vertices().iterator();
        while (undirectedIt.hasNext()) {
            Vertex<String> v = undirectedIt.next();
            assertNotEquals("Undirected: Removed vertex v1 should not appear", v1, v);
        }
        
        
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
      
        Iterator<Vertex<String>> directedIt = directedGraph.vertices().iterator();
        while (directedIt.hasNext()) {
            Vertex<String> v = directedIt.next();
            assertNotEquals("Directed: Removed vertex v6 should not appear", v6, v);
        }

        // Check explicitly that edge e11: (v5, v6) is removed
        assertNull("Directed: Edge (v5,v6) should be null after vertex v6 removal", directedGraph.getEdge(v5, v6));
        assertNotEquals("Directed: Removed edge e11 should not appear", e11, directedGraph.getEdge(v5, v6));
        // Explicitly check that all other edges are still present
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e10, directedGraph.getEdge(v4, v5));

      
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        
        // Extra: verify that getEdge(v1,v2) is null.
        assertNull(undirectedGraph.getEdge(v1, v2));
        assertEquals(0, undirectedGraph.outDegree(v6));
        assertEquals(3, undirectedGraph.inDegree(v1));
        
        // Use remaining edges.
        assertEquals(10, (int)e2.getElement());
        assertEquals(15, (int)e3.getElement());
        assertEquals(20, (int)e4.getElement());
        assertEquals(25, (int)e5.getElement());
        assertEquals(30, (int)e6.getElement());
        assertEquals(35, (int)e7.getElement());
        assertEquals(40, (int)e8.getElement());
        assertEquals(45, (int)e9.getElement());
        assertEquals(50, (int)e10.getElement());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        // Extra: verify that getEdge(v1,v2) is null.
        assertNull(directedGraph.getEdge(v1, v2));
        
        // Use remaining edges.
        assertEquals(10, (int)e2.getElement());
        assertEquals(15, (int)e3.getElement());
        assertEquals(20, (int)e4.getElement());
        assertEquals(25, (int)e5.getElement());
        assertEquals(30, (int)e6.getElement());
        assertEquals(35, (int)e7.getElement());
        assertEquals(40, (int)e8.getElement());
        assertEquals(45, (int)e9.getElement());
        assertEquals(50, (int)e10.getElement());
    }

}