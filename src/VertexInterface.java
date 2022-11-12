import java.util.Iterator;

/**
 * 
 */

/**
 * @author Adam Schneider
 * @version 2022-10-07
 *
 *          VertexInterface
 */
public interface VertexInterface<T> {

    /**
     * - Gets this vertex’s label.
     * 
     * @return
     */
    public T getLabel();


    /**
     * - Returns the number of neighbors of this vertex.
     * 
     * @return
     */
    public int getNumberOfNeighbors();


    /**
     * - Marks this vertex as visited.
     */
    public void visit();


    /**
     * - Removes this vertex’s visited mark.
     */
    public void unvisit();


    /**
     * - Returns true if the vertex is visited, false otherwise.
     * 
     * @return
     */
    public boolean isVisited();


    /**
     * - Connects this vertex and endVertex with a weighted edge. The two
     * vertices cannot be the same, and must not already have this edge between
     * them. Two vertices are equal (same)if their labels are equal (same).
     * Returns true if the connection is successful, false otherwise.
     * 
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);


    /**
     * - Connects this vertex and endVertex with a unweighted edge. The two
     * vertices cannot be the same, and must not already have this edge between
     * them. Two vertices are equal (same)if their labels are equal (same).
     * Returns true if the connection is successful, false otherwise.
     * 
     * @param endVertex
     * @return
     */
    public boolean connect(VertexInterface<T> endVertex);


    /**
     * - Disconnects this vertex from a given vertex with a weighted edge, i.e.,
     * removes the edge. The Edge should exist in order to be disconnected.
     * Returns true if the disconnection is successful, false otherwise.
     * 
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);


    /**
     * - Disconnects this vertex from a given vertex with an unweighted edge.
     * The Edge should exist in order to be disconnected. Returns true if the
     * disconnection is successful, false otherwise.
     * 
     * @param endVertex
     * @return
     */
    public boolean disconnect(VertexInterface<T> endVertex);


    /**
     * – creates an iterator of this vertex's neighbors by following all edges
     * that begin at this vertex.
     * 
     * @return
     */
    public Iterator<VertexInterface<T>> getNeighborIterator();


    /**
     * – Sees whether this vertex has at least one neighbor.
     * 
     * @return true if has neighbor
     */
    public boolean hasNeighbor();


    /**
     * – Gets an unvisited neighbor, if any, of this vertex.
     * 
     * @return
     */
    public VertexInterface<T> getUnvisitedNeighbor();


    /**
     * – Records the previous vertex on a path to this vertex.
     * 
     * @param predecessor
     */
    public void setPredecessor(VertexInterface<T> predecessor);


    /**
     * – Gets the recorded predecessor of this vertex.
     * 
     * @return
     */
    public VertexInterface<T> getPredecessor();


    /**
     * – Sees whether a predecessor was recorded for this vertex.
     * 
     * @return
     */
    public boolean hasPredecessor();


    /**
     * – Records the cost of a path to this vertex.
     * 
     * @param newCost
     */
    public void setCost(double newCost);


    /**
     * – Returns the cost of a path to this vertex.
     * 
     * @return
     */
    public double getCost();

}
