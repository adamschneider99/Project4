import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Adam Schneider
 * @version 2022-10-07
 * @param <T>
 *            SOURCE: A large portion of this was taken from the lecture
 *
 */
public class Vertex<T> implements VertexInterface<T> {

    private T label;
    private boolean visited;
    private VertexInterface<T> previousVertex;
    private double cost;
    private List<Edge<T>> edgeList;

    /**
     * constructor - initializes label to the given value, visited → false, cost
     * → 0.0, previousVertex →null, and the edgeList to a default list.
     * 
     * @param vertexLabel
     */
    public Vertex(T vertexLabel) {
        this.label = vertexLabel;
        this.visited = false;
        this.cost = 0.0;
        this.previousVertex = null;
        this.edgeList = new LinkedList<Edge<T>>(); // probably need to change
                                                   // from arrayList
    }


    /**
     * - Gets this vertex’s label. O(1)
     * 
     * @return
     */
    public T getLabel() {
        return this.label;
    }


    /**
     * - Returns the number of neighbors of this vertex.
     * O(n) (where n is vertices)
     * 
     * @return number of neighbors
     */
    public int getNumberOfNeighbors() {
        Iterator<VertexInterface<T>> it = getNeighborIterator();
        if (it == null) {
            return 0;
        }
        // res starts at 1 because it won't count the last one because it wont
        // have a next
        int res = 1;
        while (it.hasNext()) {
            res++;
        }
        return res;
    }


    /**
     * - Marks this vertex as visited.
     * O(1)
     */
    public void visit() {
        this.visited = true;
        return;
    }


    /**
     * - Removes this vertex’s visited mark.
     * O(1)
     */
    public void unvisit() {
        this.visited = false;
        return;
    }


    /**
     * - Returns true if the vertex is visited, false otherwise.
     * O(1)
     * 
     * @return boolean if the vertex is visited
     */
    public boolean isVisited() {
        return this.visited;
    }


    /**
     * - Connects this vertex and endVertex with a weighted edge. The two
     * vertices cannot be the same, and must not already have this edge between
     * them. Two vertices are equal (same)if their labels are equal (same).
     * Returns true if the connection is successful, false otherwise.
     * O(n)
     * 
     * @param endVertex
     * @param edgeWeight
     * @return boolean if it is successful
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;

        if (!this.equals(endVertex)) {
            // vertices are distinct
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                }
            } // end while
            if (!duplicateEdge) {
                edgeList.add(new Edge<T>(endVertex, edgeWeight));
                result = true;
            }
        }
        return result;
    }


    /**
     * - Connects this vertex and endVertex with a unweighted edge. The two
     * vertices cannot be the same, and must not already have this edge between
     * them. Two vertices are equal (same)if their labels are equal (same).
     * Returns true if the connection is successful, false otherwise.
     * O(n)
     * 
     * @param endVertex
     * @return boolean true if the connection is successful, false otherwise
     */
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }


    /**
     * - Disconnects this vertex from a given vertex with a weighted edge, i.e.,
     * removes the edge. The Edge should exist in order to be disconnected.
     * Returns true if the disconnection is successful, false otherwise.
     * O(n)
     * 
     * @param endVertex
     * @param edgeWeight
     * @return boolean true if the disconnection is successful, false otherwise
     */
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;

        if (!this.equals(endVertex)) {
            for (int i = 0; i < edgeList.size(); i++) {
                Edge<T> currEdge = edgeList.get(i);
                if (currEdge.getEndVertex().equals(endVertex)) {
                    // found edge
                    result = edgeList.remove(currEdge);
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * - Disconnects this vertex from a given vertex with an unweighted edge.
     * The Edge should exist in order to be disconnected. Returns true if the
     * disconnection is successful, false otherwise.
     * O(n)
     * 
     * @param endVertex
     * @return boolean true if the disconnect was successful
     */
    public boolean disconnect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }


    /**
     * – creates an iterator of this vertex's neighbors by following all edges
     * that begin at this vertex.
     * O(n)
     * 
     * @return neighbors iterator
     */
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new neighborIterator();
    }


    /**
     * – Sees whether this vertex has at least one neighbor.
     * O(1)
     * 
     * @return true if has neighbor
     */
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }


    /**
     * – Gets an unvisited neighbor, if any, of this vertex.
     * O(n)
     * 
     * @return unvisited neighbor
     */
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ((neighbors.hasNext()) && (result == null)) {
            VertexInterface<T> nextNeighbor = neighbors.next();

            if (!nextNeighbor.isVisited()) {
                result = nextNeighbor;
            }
        }
        return result;
    }


    /**
     * – Records the previous vertex on a path to this vertex.
     * O(1)
     * 
     * @param predecessor
     */
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }


    /**
     * – Gets the recorded predecessor of this vertex.
     * O(1)
     * 
     * @return the predecessor
     */
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }


    /**
     * – Sees whether a predecessor was recorded for this vertex.
     * O(1)
     * 
     * @return boolean true if has predecessor
     */
    public boolean hasPredecessor() {
        return (this.previousVertex != null);
    }


    /**
     * – Records the cost of a path to this vertex.
     * O(1)
     * 
     * @param newCost
     */
    public void setCost(double newCost) {
        this.cost = newCost;
        return;
    }


    /**
     * – Returns the cost of a path to this vertex.
     * O(1)
     * 
     * @return the cost
     */
    public double getCost() {
        return this.cost;
    }


    public boolean equals(Object other) {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else {
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    protected class Edge<T> {
        private VertexInterface<T> vertex;
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
            vertex = endVertex;
            weight = edgeWeight;
        }


        protected VertexInterface<T> getEndVertex() {
            return vertex;
        }


        protected double getWeight() {
            return weight;
        }
    }


    private class neighborIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Edge<T>> edges;

        private neighborIterator() {
            edges = edgeList.iterator();
        } // end default constructor


        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext


        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext()) {
                Edge<T> edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next


        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end neighborIterator
}
