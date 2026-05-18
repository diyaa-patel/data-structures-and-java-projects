package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AbstractPriorityQueue.PQEntry;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Diya Patel
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        //NOTE: since Dijkstra's algorithm is very similar to Prim-Jarnik's algorithm,
        //     you should review the provided Prim-Jarnik implementation in the next
        //     section of the lab on Minimum Spanning Trees
    	 Map<Vertex<V>, Integer> distances = new LinearProbingHashMap<>();
    	 Set<Vertex<V>> visited = new HashSet<>();
    	 Map<Vertex<V>, PQEntry<Integer, Vertex<V>>> entryMap = new LinearProbingHashMap<>();
    	 HeapAdaptablePriorityQueue<Integer, Vertex<V>> priorityQueue = new HeapAdaptablePriorityQueue<>();
    	 
    	 for (Vertex<V> v : graph.vertices()) {
             int initial = v.equals(start) ? 0 : Integer.MAX_VALUE;
             distances.put(v, initial);
             Entry<Integer, Vertex<V>> e = priorityQueue.insert(initial, v);
             entryMap.put(v, (PQEntry<Integer, Vertex<V>>) e);
         }
    	 
    	 while (!priorityQueue.isEmpty()) {
             Entry<Integer, Vertex<V>> eMin = priorityQueue.deleteMin();
             Vertex<V> u = eMin.getValue();
             visited.add(u);
             int costU = distances.get(u);
             
             for (Edge<E> e : graph.outgoingEdges(u)) {
                 Vertex<V> z = graph.opposite(u, e);
                 if (!visited.contains(z)) {
                     int w = e.getElement().getWeight();
                     int r = costU == Integer.MAX_VALUE ? Integer.MAX_VALUE : costU + w;
                     int costZ = distances.get(z);
                     if (r < costZ) {
                    	 distances.put(z, r);
                    	 priorityQueue.replaceKey(entryMap.get(z), r);
                     }
                 }
             }
         }
         
         return distances;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	
    	Map<Vertex<V>, Edge<E>> tree = new LinearProbingHashMap<>();
        
        for (Vertex<V> v : graph.vertices()) {
            if (v.equals(start)) continue;
            int dv = costs.get(v);
            for (Edge<E> e : graph.incomingEdges(v)) {
                Vertex<V> u = graph.opposite(v, e);
                int du = costs.get(u);
                int w  = e.getElement().getWeight();
                if (du != Integer.MAX_VALUE && dv == du + w) {
                    tree.put(v, e);
                    break;
                }
            }
        }
        
        return tree;
    }
}