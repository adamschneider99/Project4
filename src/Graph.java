import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
 *            SOURCE: A large portion of this was taken from the lecture
 *
 */
public class Graph<T> implements GraphInterface<T> {

    private HashMap<T, VertexInterface<T>> vertices;
    private int edgeCount;

    /**
     * - initializes the graph with an empty graph
     * structure.
     */
    public Graph() {
        edgeCount = 0;
        vertices = new HashMap<T, VertexInterface<T>>();
    }


    /**
     * 
     * Adds a given vertex to this graph. If vertexLabel is null, it returns
     * false.
     * O(1)
     * 
     * @param vertexLabel
     *            vertex to add
     * @return boolean if the vertex was added successfully
     */
    public boolean addVertex(T vertexLabel) {
        if (vertexLabel == null) {
            return false;
        }
        VertexInterface<T> addOutcome = vertices.put(vertexLabel, new Vertex<T>(
            vertexLabel));
        return addOutcome == null;
    }


    /**
     * - Removes a vertex with the given vertexLabel from this graph and returns
     * the removed vertex. If vertex does not exist, it will return null.
     * O(n^2)
     * 
     * @param vertexLabel
     *            vertex to remove
     * @return VertexInterface<T> removed vertex
     */
    public VertexInterface<T> removeVertex(T vertexLabel) {

        VertexInterface<T> removeOutcome = vertices.remove(vertexLabel);
        return removeOutcome;
    }


    /**
     * - Adds a weighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the
     * graph. Note that the graph is undirected graph.
     * O(n)
     * 
     * @param begin
     *            start of the edge
     * @param end
     *            end of the edge
     * @param edgeWeight
     *            weight of the edge
     * @return boolean true edge was added
     */
    public boolean addEdge(T begin, T end, double edgeWeight) {
        // Note representing undirected edges as two seperate edges back and
        // forth
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        // if the vertex isnt null, connect the start and end
        if ((beginVertex != null) && (endVertex != null)) {
            if (!hasEdge(begin, end)) {
                result = beginVertex.connect(endVertex, edgeWeight);
            }

        }
        // if the connection was successful, iterate the edgeCount
        if (result) {
            edgeCount++;
        }
        return result;
    }


    /**
     * - Adds an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the
     * graph.
     * O(n)
     * 
     * @param begin
     *            the beggining point to start the edge from
     * @param end
     *            the end point to end the edge
     * @return a boolean if the edge was added
     */
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    }


    /**
     * - Removes a weighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise.
     * O(n)
     * 
     * @param begin
     *            the beggining point
     * @param end
     *            the end point
     * @param edgeWeight
     *            the edge weight
     * @return a boolean if the edge was removed
     */
    public boolean removeEdge(T begin, T end, double edgeWeight) {

        boolean result = false;

        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        // disconnect as long as neither vertex is null
        if ((beginVertex != null) && (endVertex != null)) {
            if (hasEdge(begin, end)) {
                result = beginVertex.disconnect(endVertex, edgeWeight);
            }
        }
        if (result) {
            edgeCount--;
        }
        return result;
    }


    /**
     * - Removes an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise
     * O(n)
     * 
     * @param begin
     *            the beggining point
     * @param end
     *            the end point
     * @return a boolean if the edge was removed
     */
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 0);
    }


    /**
     * - Sees whether an undirected edge exists between two given vertices.
     * O(n)
     * 
     * @param begin
     *            the beggining point
     * @param end
     *            the end point
     * @return a boolean if the point has the edge
     */
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        // check for null vertex
        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> neighbors = beginVertex
                .getNeighborIterator();
            // iterate through the neighbors
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    // we found the edge
                    found = true;
                }
            }
        }
        return found;
    }


    /**
     * - This method returns the number of Vertices in this graph.
     * O(1)
     * 
     * @return an int with the number of vertices
     */
    public int getNumberOfVertices() {
        return vertices.size();
    }


    /**
     * - This method returns the number of undirected Edges in this graph.
     * O(1)
     * 
     * @return an int of the number of edges
     */
    public int getNumberOfEdges() {
        return edgeCount;
    }


    /**
     * - This method returns true, if this graph is empty, false otherwise.
     * O(1)
     * 
     * @return a boolean if the graph is empty, false otherwise
     */
    public boolean isEmpty() {
        return vertices.isEmpty();
    }


    /**
     * - This method returns the list of all vertices in the graph. If the graph
     * is empty, it returns null.
     * O(n)
     * 
     * @return a list of all the vertices in the graph
     */
    public List<VertexInterface<T>> getVertices() {
        if (isEmpty()) {
            return null;
        }
        List<VertexInterface<T>> list = new ArrayList<VertexInterface<T>>(
            vertices.values());
        return list;

    }


    /**
     * – clears the graph.
     * O(1)
     */
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }


    /**
     * - Performs a breadth- first traversal of a graph and returns the queue
     * that contains the result. Empty queue can be returned.
     * O(|V|+|E|)
     * 
     * @param origin
     *            the origin of the BFT
     * @return queue queue
     */
    public Queue<T> getBreadthFirstTraversal(T origin) {
        if (origin == null) {
            return new LinkedBlockingQueue<T>();
        }
        resetVertices();
        Queue<T> traversalOrder = new LinkedBlockingQueue<T>();
        Queue<VertexInterface<T>> vertexQueue =
            new LinkedBlockingQueue<VertexInterface<T>>();
        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();
        traversalOrder.add(origin); // enqueue vertex label
        vertexQueue.add(originVertex); // enqueue vertex

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.remove();

            Iterator<VertexInterface<T>> neighbors = frontVertex
                .getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.add(nextNeighbor.getLabel());
                    vertexQueue.add(nextNeighbor);
                } // end if
            } // end while
        } // end while

        return traversalOrder;
    } // end getBreadthFirstTraversal


    /**
     * - returns the shortest distance between the origin and destination. If a
     * path does not exist, it returns the maximum integer (to simulate
     * infinity).
     * O(|V|+|E|)
     * 
     * @param origin
     *            origin of shortest path
     * @param destination
     *            destination of shortest path
     * @param path
     *            the path
     * @return an integer of the number of edges along the shortest path
     */
    public int getShortestPath(T origin, T destination, Stack<T> path) {

        // Based on lecture getShortestPath algorithm
        resetVertices();
        boolean done = false;
        Queue<VertexInterface<T>> vertexQueue =
            new LinkedBlockingQueue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices.get(origin);
        VertexInterface<T> endVertex = vertices.get(destination);
        // visit the origin and add it to vertexQueue
        originVertex.visit();
        vertexQueue.add(originVertex);
        // Outer loop
        while (!done && !vertexQueue.isEmpty()) {
            // dequeue the front vertex
            VertexInterface<T> frontVertex = vertexQueue.remove();
            Iterator<VertexInterface<T>> neighbors = frontVertex
                .getNeighborIterator();

            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                // if next neighbor is not visited
                if (!nextNeighbor.isVisited()) {
                    // Mark next neighbor as visited
                    nextNeighbor.visit();
                    // Set the length of the path to nextNeighbor to 1 + length
                    // of path to frontVertex
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    // set setPredecessor of nextNeighbor to front vertex
                    nextNeighbor.setPredecessor(frontVertex);
                    // enqueue
                    vertexQueue.add(nextNeighbor);
                }

                if (nextNeighbor.equals(endVertex))
                    done = true;
            }
        }

        // traversal ends - construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());
        // while vertex has a predecessor
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }

        return pathLength;
    }


    protected void resetVertices() {
        Collection<VertexInterface<T>> temp = vertices.values();
        Iterator<VertexInterface<T>> vertexIterator = temp.iterator();

        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }
}
