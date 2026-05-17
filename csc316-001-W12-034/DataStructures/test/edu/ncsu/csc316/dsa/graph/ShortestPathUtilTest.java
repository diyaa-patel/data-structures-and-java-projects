package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class ShortestPathUtilTest {

    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	Graph<String, Weighted> graph = new AdjacencyListGraph<>(false);
        Vertex<String> vA = graph.insertVertex("A");
        Vertex<String> vB = graph.insertVertex("B");
        Vertex<String> vC = graph.insertVertex("C");
        Vertex<String> vD = graph.insertVertex("D");

        graph.insertEdge(vA, vB, new Weighted() { @Override public int getWeight() { return 1; } });
        graph.insertEdge(vA, vC, new Weighted() { @Override public int getWeight() { return 4; } });
        graph.insertEdge(vB, vC, new Weighted() { @Override public int getWeight() { return 2; } });
        graph.insertEdge(vC, vD, new Weighted() { @Override public int getWeight() { return 1; } });
        graph.insertEdge(vB, vD, new Weighted() { @Override public int getWeight() { return 5; } });

        Map<Vertex<String>, Integer> dist = ShortestPathUtil.dijkstra(graph, vA);


        assertEquals("Distance to A should be 0", 0, (int) dist.get(vA));
        assertEquals("Distance to B should be 1", 1, (int) dist.get(vB));
        assertEquals("Distance to C should be 3", 3, (int) dist.get(vC));
        assertEquals("Distance to D should be 4", 4, (int) dist.get(vD));
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	 Graph<String, Weighted> graph = new AdjacencyListGraph<>(false);
         Vertex<String> vA = graph.insertVertex("A");
         Vertex<String> vB = graph.insertVertex("B");
         Vertex<String> vC = graph.insertVertex("C");
         Vertex<String> vD = graph.insertVertex("D");

         Edge<Weighted> eAB = graph.insertEdge(vA, vB, new Weighted() { @Override public int getWeight() { return 1; } });
         Edge<Weighted> eAC = graph.insertEdge(vA, vC, new Weighted() { @Override public int getWeight() { return 4; } });
         Edge<Weighted> eBC = graph.insertEdge(vB, vC, new Weighted() { @Override public int getWeight() { return 2; } });
         Edge<Weighted> eCD = graph.insertEdge(vC, vD, new Weighted() { @Override public int getWeight() { return 1; } });
         Edge<Weighted> eBD = graph.insertEdge(vB, vD, new Weighted() { @Override public int getWeight() { return 5; } });

         Map<Vertex<String>, Integer> dist = ShortestPathUtil.dijkstra(graph, vA);
         Map<Vertex<String>, Edge<Weighted>> tree = ShortestPathUtil.shortestPathTree(graph, vA, dist);


         assertEquals("B reached via AB", eAB, tree.get(vB));
         assertEquals("C reached via BC", eBC, tree.get(vC));
         assertEquals("D reached via CD", eCD, tree.get(vD));

         assertNotEquals("C should not be reached via AC", eAC, tree.get(vC));
         assertNotEquals("D should not be reached via BD", eBD, tree.get(vD));
         
         assertNull("A should have no parent edge", tree.get(vA));
    }
    
}