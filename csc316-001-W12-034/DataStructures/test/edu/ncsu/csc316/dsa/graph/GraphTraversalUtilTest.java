package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class GraphTraversalUtilTest {

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	// build an undirected graph
        Graph<String, String> graph = new AdjacencyListGraph<>(false);
        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");

        Edge<String> eAB = graph.insertEdge(vA, vB, "AB");
        Edge<String> eAC = graph.insertEdge(vA, vC, "AC");
        Edge<String> eBD = graph.insertEdge(vB, vD, "BD");
        
        Map<Vertex<String>, Edge<String>> forest = 
                GraphTraversalUtil.depthFirstSearch(graph, vA);
        
        assertEquals("Expected 3 discovered vertices", 3, forest.size());
        assertEquals("B should be discovered via edge AB", eAB, forest.get(vB));
        assertEquals("C should be discovered via edge AC", eAC, forest.get(vC));
        assertEquals("D should be discovered via edge BD", eBD, forest.get(vD));
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	// build the same undirected graph
        Graph<String, String> graph = new AdjacencyListGraph<>(false);
        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");

        Edge<String> eAB = graph.insertEdge(vA, vB, "AB");
        Edge<String> eAC = graph.insertEdge(vA, vC, "AC");
        Edge<String> eBD = graph.insertEdge(vB, vD, "BD");
        
        Map<Vertex<String>, Edge<String>> forest = 
                GraphTraversalUtil.breadthFirstSearch(graph, vA);
        
        assertEquals("Expected 3 discovered vertices", 3, forest.size());
        assertEquals("B should be discovered via edge AB", eAB, forest.get(vB));
        assertEquals("C should be discovered via edge AC", eAC, forest.get(vC));
        assertEquals("D should be discovered via edge BD", eBD, forest.get(vD));

    }
    
}