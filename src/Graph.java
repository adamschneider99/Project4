import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 */

/**
 * @author Adam Schneider
 * @version 2022-11-09
 * @param <T>
 *
 */
public class Graph<T> implements GraphInterface<T> {

    // private DictionaryInterface<T, VertexInterface<T>> vertices;
    private HashMap<T, VertexInterface<T>> vertices;
    private int edgeCount;
     
    public Graph() {
        edgeCount = 0;
        vertices = new HashMap<T, VertexInterface<T>>();
    }
    
    /**
     * 
     * Adds a given vertex to this graph. If vertexLabel is null, it returns
     * false.
     * 
     * @param vertexLabel
     * @return
     */
    public boolean addVertex(T vertexLabel) {
        
        VertexInterface<T> addOutcome = vertices.put(vertexLabel, new Vertex<T>(vertexLabel));
        return addOutcome == null;
        
    }
        

    /**
     * - Removes a vertex with the given vertexLabel from this graph and returns
     * the removed vertex. If vertex does not exist, it will return null.
     * 
     * @param vertexLabel
     * @return VertexInterface<T> vertex interface
     */
    public VertexInterface<T> removeVertex(T vertexLabel) {
        Vertex<T> temp = new Vertex<T>(vertexLabel);
        VertexInterface<T> removeOutcome = vertices.remove(vertexLabel);
        return removeOutcome;
    }


    /**
     * - Adds a weighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the
     * graph. Note that the graph is undirected graph.
     * 
     * @param begin
     * @param end
     * @param edgeWeight
     * @return bool if added edge
     */
    public boolean addEdge(T begin, T end, double edgeWeight) {
    
        boolean result = false;
        // Does this work?
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
        if ((beginVertex != null) && (endVertex != null) ) {
            result = beginVertex.connect(endVertex, edgeWeight);
        }
        if (result) {
            edgeCount++;
        }
        return result;
        
    }
        

    /**
     * - Adds an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the
     * graph.
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    }


    /**
     * - Removes a weighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise.
     * 
     * @param begin
     * @param end
     * @param edgeWeight
     * @return
     */
    public boolean removeEdge(T begin, T end, double edgeWeight) {
        
        boolean result = false;
        // does this work?
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
        if ((beginVertex != null) && (endVertex != null) ) {
            result = beginVertex.connect(endVertex, edgeWeight);
        }
        if (result) {
            edgeCount++;
        }
        return result;
        
        /*
        Edge curr = find(v, w);
        if ((curr.next == null) || curr.next.vertex != w) { return; }
        else {
            curr.next = curr.next.next;
            if (curr.next != null) { curr.next.prev = curr; }
        }
        numEdge--;
        */
    }

    /**
     * - Removes an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 0);
    }


    /**
     * - Sees whether an undirected edge exists between two given vertices.
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        // check
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        
        if ((beginVertex != null) && (endVertex != null) ) {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
            
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                }
            }
        }
        return found;
    }


    /**
     * - This method returns the number of Vertices in this graph.
     * 
     * @return
     */
    public int getNumberOfVertices() {
        return vertices.size();
    }

    /**
     * - This method returns the number of undirected Edges in this graph.
     * 
     * @return
     */
    public int getNumberOfEdges() {
        return edgeCount;
    }


    /**
     * - This method returns true, if this graph is empty, false otherwise.
     * 
     * @return
     */
    public boolean isEmpty() {
        return vertices.isEmpty();
    }


    /**
     * - This method returns the list of all vertices in the graph. If the graph
     * is empty, it returns null.
     * 
     * @return
     */
    public List<VertexInterface<T>> getVertices() {
        // check
        List<VertexInterface<T>> temp = (List<VertexInterface<T>>)vertices.values();
        return temp;
        
    }


    /**
     * – clears the graph.
     */
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }


    /**
     * - Performs a breadth- first traversal of a graph and returns the queue
     * that contains the result. Empty queue can be returned.
     * 
     * @param origin
     * @return queue queue
     */
    public Queue<T> getBreadthFirstTraversal(T origin) {
        resetVertices();
        Queue<T> traversalOrder = new LinkedBlockingQueue<T>();
        Queue<VertexInterface<T>> vertexQueue = new LinkedBlockingQueue<VertexInterface<T>>();
        
        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();
        traversalOrder.add(origin); // enqueue vertex label
        vertexQueue.add(originVertex); // enqueue vertex
        
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.remove();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.add(nextNeighbor.getLabel());
                    vertexQueue.add(nextNeighbor);
                }
            }
        }
        return traversalOrder;
    }


    /**
     * - returns the shortest distance between the origin and destination. If a
     * path does not exist, it returns the maximum integer (to simulate
     * infinity).
     * 
     * @param origin
     * @param destination
     * @param path
     * @return
     */
    public int getShortestPath(T origin, T destination, Stack<T> path) {
        resetVertices();
        boolean done = false;
        Queue<VertexInterface<T>> vertexQueue = 
                                           new LinkedBlockingQueue<VertexInterface<T>>();
        VertexInterface<T> originVertex = vertices.get(origin);
        VertexInterface<T> endVertex = vertices.get(destination);
        
        originVertex.visit();
        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex
        
        vertexQueue.add(originVertex);
        
        while (!done && !vertexQueue.isEmpty())
        {
          VertexInterface<T> frontVertex = vertexQueue.remove();
          
          Iterator<VertexInterface<T>> neighbors = 
                                       frontVertex.getNeighborIterator();
          while (!done && neighbors.hasNext())
          {
            VertexInterface<T> nextNeighbor = neighbors.next();
            
            if (!nextNeighbor.isVisited())
            {
              nextNeighbor.visit();
              nextNeighbor.setCost(1 + frontVertex.getCost());
              nextNeighbor.setPredecessor(frontVertex);
              vertexQueue.add(nextNeighbor);
            } // end if
            
            if (nextNeighbor.equals(endVertex))
              done = true;
          } // end while
        } // end while
        
        // traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());
        
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
          vertex = vertex.getPredecessor();
          path.push(vertex.getLabel());
        } // end while
        
        return pathLength;
      } // end getShortestPath
    
    protected void resetVertices()
    {
        Collection<VertexInterface<T>> temp = vertices.values();
        Iterator<VertexInterface<T>> vertexIterator = temp.iterator();
      // List<VertexInterface<T>> vertexIterator = vertices.values();
      while (vertexIterator.hasNext())
      {
        VertexInterface<T> nextVertex = vertexIterator.next();
        nextVertex.unvisit();
        nextVertex.setCost(0);
        nextVertex.setPredecessor(null);
      } // end while
    } // end resetVertices
}
