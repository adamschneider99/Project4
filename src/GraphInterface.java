import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Adam Schneider
 * @version 2022-11-07
 * 
 *          Graph Interface
 * @param <T>
 *
 */
public interface GraphInterface<T> {

    /**
     * Adds a given vertex to this graph. If vertexLabel is null, it returns
     * false.
     * 
     * @param vertexLabel
     * @return
     */
    public boolean addVertex(T vertexLabel);


    /**
     * - Removes a vertex with the given vertexLabel from this graph and returns
     * the removed vertex. If vertex does not exist, it will return null.
     * 
     * @param vertexLabel
     * @return VertexInterface<T> vertex interface
     */
    public VertexInterface<T> removeVertex(T vertexLabel);


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
    public boolean addEdge(T begin, T end, double edgeWeight);


    /**
     * - Adds an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the
     * graph.
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean addEdge(T begin, T end);


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
    public boolean removeEdge(T begin, T end, double edgeWeight);


    /**
     * - Removes an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean removeEdge(T begin, T end);


    /**
     * - Sees whether an undirected edge exists between two given vertices.
     * 
     * @param begin
     * @param end
     * @return
     */
    public boolean hasEdge(T begin, T end);


    /**
     * - This method returns the number of Vertices in this graph.
     * 
     * @return
     */
    public int getNumberOfVertices();


    /**
     * - This method returns the number of undirected Edges in this graph.
     * 
     * @return
     */
    public int getNumberOfEdges();


    /**
     * - This method returns true, if this graph is empty, false otherwise.
     * 
     * @return
     */
    public boolean isEmpty();


    /**
     * - This method returns the list of all vertices in the graph. If the graph
     * is empty, it returns null.
     * 
     * @return
     */
    public List<VertexInterface<T>> getVertices();


    /**
     * – clears the graph.
     */
    public void clear();


    /**
     * - Performs a breadth- first traversal of a graph and returns the queue
     * that contains the result. Empty queue can be returned.
     * 
     * @param origin
     * @return queue queue
     */
    public Queue<T> getBreadthFirstTraversal(T origin);


    /**
     * - returns the shortest distance between the origin and destination. If a
     * path does not exist, it returns the maximum integer (to simulate
     * infinity).
     * 
     * @param origin
     * @param destination
     * @param path
     * @return int path length
     */
    public int getShortestPath(T origin, T destination, Stack<T> path);
}
