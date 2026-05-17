package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Diya Patel 
 *
 */
public class MinimumSpanningTreeUtilTest {

    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	 AdjacencyListGraph<String, Weighted> graph = new AdjacencyListGraph<>(false);
         Vertex<String> vA = graph.insertVertex("A");
         Vertex<String> vB = graph.insertVertex("B");
         Vertex<String> vC = graph.insertVertex("C");
         Vertex<String> vD = graph.insertVertex("D");

         Edge<Weighted> eAB = graph.insertEdge(vA, vB, new Weighted() { @Override public int getWeight() { return 1; } });
         Edge<Weighted> eBC = graph.insertEdge(vB, vC, new Weighted() { @Override public int getWeight() { return 2; } });
         Edge<Weighted> eCD = graph.insertEdge(vC, vD, new Weighted() { @Override public int getWeight() { return 3; } });
         Edge<Weighted> eDA = graph.insertEdge(vD, vA, new Weighted() { @Override public int getWeight() { return 4; } });
         Edge<Weighted> eAC = graph.insertEdge(vA, vC, new Weighted() { @Override public int getWeight() { return 5; } });

         PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.primJarnik(graph);
         assertEquals("Prim-Jarnik MST should have 3 edges", 3, mst.size());

         boolean foundAB = false, foundBC = false, foundCD = false;
         boolean foundDA = false, foundAC = false;
         for (Edge<Weighted> e : mst) {
             if (e == eAB) foundAB = true;
             if (e == eBC) foundBC = true;
             if (e == eCD) foundCD = true;
             if (e == eDA) foundDA = true;
             if (e == eAC) foundAC = true;
         }
         assertTrue("MST must include AB", foundAB);
         assertTrue("MST must include BC", foundBC);
         assertTrue("MST must include CD", foundCD);
         assertFalse("MST must not include DA", foundDA);
         assertFalse("MST must not include AC", foundAC);
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	 AdjacencyListGraph<String, Weighted> graph = new AdjacencyListGraph<>(false);
         Vertex<String> vA = graph.insertVertex("A");
         Vertex<String> vB = graph.insertVertex("B");
         Vertex<String> vC = graph.insertVertex("C");
         Vertex<String> vD = graph.insertVertex("D");

         Edge<Weighted> eAB = graph.insertEdge(vA, vB, new Weighted() { @Override public int getWeight() { return 1; } });
         Edge<Weighted> eBC = graph.insertEdge(vB, vC, new Weighted() { @Override public int getWeight() { return 2; } });
         Edge<Weighted> eCD = graph.insertEdge(vC, vD, new Weighted() { @Override public int getWeight() { return 3; } });
         Edge<Weighted> eDA = graph.insertEdge(vD, vA, new Weighted() { @Override public int getWeight() { return 4; } });
         Edge<Weighted> eAC = graph.insertEdge(vA, vC, new Weighted() { @Override public int getWeight() { return 5; } });

         PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.kruskal(graph);
         assertEquals("Kruskal MST should have 3 edges", 3, mst.size());

         boolean foundAB = false, foundBC = false, foundCD = false;
         boolean foundDA = false, foundAC = false;
         for (Edge<Weighted> e : mst) {
             if (e == eAB) foundAB = true;
             if (e == eBC) foundBC = true;
             if (e == eCD) foundCD = true;
             if (e == eDA) foundDA = true;
             if (e == eAC) foundAC = true;
         }
         assertTrue("MST must include AB", foundAB);
         assertTrue("MST must include BC", foundBC);
         assertTrue("MST must include CD", foundCD);
         assertFalse("MST must not include DA", foundDA);
         assertFalse("MST must not include AC", foundAC);
    }
    
}