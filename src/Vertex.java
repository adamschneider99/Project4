import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author Adam Schneider
 * @version 2022-10-07
 * @param <T>
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
        this.edgeList = new LinkedList<Edge<T>>(); // probably need to change from arrayList
    }


    /**
     * - Gets this vertex’s label.
     * 
     * @return
     */
    public T getLabel() {
        return this.label;
    }


    /**
     * - Returns the number of neighbors of this vertex.
     * 
     * @return
     */
    public int getNumberOfNeighbors() {
        Iterator<VertexInterface<T>> it = getNeighborIterator();
        if (it == null) {
            return 0;
        }
        int res = 1;
        // double check this math?
        while (it.hasNext()) {
            res++;
        }
        return res;
    }


    /**
     * - Marks this vertex as visited.
     */
    public void visit() {
        this.visited = true;
        return;
    }


    /**
     * - Removes this vertex’s visited mark.
     */
    public void unvisit() {
        this.visited = false;
        return;
    }


    /**
     * - Returns true if the vertex is visited, false otherwise.
     * 
     * @return
     */
    public boolean isVisited() {
        return this.visited;
    }


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
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        
        if (!this.equals(endVertex)) {
            //vertices are distinct
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;
            
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor) ) {
                    duplicateEdge = true;
                    // might be missing a line here***
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
     * 
     * @param endVertex
     * @return
     */
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }


    /**
     * - Disconnects this vertex from a given vertex with a weighted edge, i.e.,
     * removes the edge. The Edge should exist in order to be disconnected.
     * Returns true if the disconnection is successful, false otherwise.
     * 
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        
        if (!this.equals(endVertex)) {
            // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;
            
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor) ) {
                    duplicateEdge = true;
                    // might be missing a line here***
                }
            } // end while
            if (!duplicateEdge) {
                Edge<T> ed = new Edge<T>(endVertex, edgeWeight);
                edgeList.remove(ed);
                result = true;
            }
        }
        return result;
    }


    /**
     * - Disconnects this vertex from a given vertex with an unweighted edge.
     * The Edge should exist in order to be disconnected. Returns true if the
     * disconnection is successful, false otherwise.
     * 
     * @param endVertex
     * @return
     */
    public boolean disconnect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }


    /**
     * – creates an iterator of this vertex's neighbors by following all edges
     * that begin at this vertex.
     * 
     * @return
     */
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }


    /**
     * – Sees whether this vertex has at least one neighbor.
     * 
     * @return true if has neighbor
     */
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }


    /**
     * – Gets an unvisited neighbor, if any, of this vertex.
     * 
     * @return
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
     * 
     * @param predecessor
     */
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }


    /**
     * – Gets the recorded predecessor of this vertex.
     * 
     * @return
     */
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }


    /**
     * – Sees whether a predecessor was recorded for this vertex.
     * 
     * @return
     */
    public boolean hasPredecessor() {
        return (this.previousVertex != null);
    }


    /**
     * – Records the cost of a path to this vertex.
     * 
     * @param newCost
     */
    public void setCost(double newCost) {
        this.cost = newCost;
        return;
    }


    /**
     * – Returns the cost of a path to this vertex.
     * 
     * @return
     */
    public double getCost() {
        return this.cost;
    }
    
    public boolean equals(Object other)
    {
      boolean result;
      
      if ((other == null) || (getClass() != other.getClass()))
        result = false;
      else
      {
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
    
    protected class NeighborIterator implements Iterator<VertexInterface<T>> {
        protected Iterator<Edge<T>> edges;
        private NeighborIterator() {
            edges = edgeList.iterator();
        }
        
        @Override
        public boolean hasNext() {
            return edges.hasNext();
        }

        @Override
        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()) {
                Edge<T> edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else {
                throw new NoSuchElementException();
            }
            return nextNeighbor;
        }
        
    }
}

/*

import java.util.Iterator;
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface<T>, java.io.Serializable
{
  private T label; 
  private ListWithIteratorInterface<Edge> edgeList; // edges to neighbors
  private boolean visited;                          // true if visited
  private VertexInterface<T> previousVertex;        // on path to this vertex
  private double cost;                              // of path to this vertex
  
  public Vertex(T vertexLabel)
  {
    label = vertexLabel;
    edgeList = new LinkedListWithIterator<Edge>();
    visited = false;
    previousVertex = null; 
    cost = 0;
  } // end constructor
    
    public T getLabel()
    {
        return label;
    } // end getLabel

    public boolean connect(VertexInterface<T> endVertex, 
                           double edgeWeight) 
    {
      boolean result = false;
      
      if (!this.equals(endVertex))
      { // vertices are distinct
        Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
        boolean duplicateEdge = false;
        
        while (!duplicateEdge && neighbors.hasNext())
        {
          VertexInterface<T> nextNeighbor = neighbors.next();
          if (endVertex.equals(nextNeighbor))
            duplicateEdge = true;
        } // end while
        
        if (!duplicateEdge)
        {
          edgeList.add(new Edge(endVertex, edgeWeight));
          result = true;
        } // end if
      } // end if
      
      return result;
    } // end connect

    public boolean connect(VertexInterface<T> endVertex) 
    {
      return connect(endVertex, 0);
    } // end connect

    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new neighborIterator();
    } // end getNeighborIterator

    public Iterator<Double> getWeightIterator()
    {
        return new weightIterator();
    } // end getWeightIterator

    public boolean hasNeighbor()
    {
      return !edgeList.isEmpty();
    } // end hasNeighbor

    public VertexInterface<T> getUnvisitedNeighbor()
    {
      VertexInterface<T> result = null;
      
      Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
      while (neighbors.hasNext() && (result == null) )
      {
        VertexInterface<T> nextNeighbor = neighbors.next();
        if (!nextNeighbor.isVisited())
          result = nextNeighbor;
      } // end while
      
      return result;
    } // end getUnvisitedNeighbor

    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor
    
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    public void visit()
    {
        visited = true;
    } // end visit

    public void unvisit()
    {
        visited = false;
    } // end unvisit

    public boolean isVisited()
    {
        return visited;
    } // end isVisited
    
    public double getCost()
    {
        return cost;
    } // end getCost
    
    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost


    public String toString()
    {
        return label.toString();
    } // end toString
    
    public void display() // for testing
    {
        System.out.print(label + " " );
        Iterator<VertexInterface<T>> vertexIterator = getNeighborIterator();
    Iterator<Double> weightIterator = getWeightIterator();
                
        while (vertexIterator.hasNext())
        {
            Vertex<T> vert = (Vertex<T>)vertexIterator.next();  
            System.out.print(vert + " " + weightIterator.next() + " ");

        } // end while
        System.out.println();
    } // end display

    // 31.10
    protected class Edge implements java.io.Serializable
    {
      private VertexInterface<T> vertex; // end vertex
      private double weight;
      
      protected Edge(VertexInterface<T> endVertex, double edgeWeight)
      {
        vertex = endVertex;
        weight = edgeWeight;
      } // end constructor
      
      protected VertexInterface<T> getEndVertex()
      {
        return vertex; 
      } // end getEndVertex
      
      protected double getWeight() 
      {
        return weight; 
      } // end getWeight

        public String toString() // for testing only
        {
            return vertex.toString() + " " + weight;
        } // end toString 
    } // end Edge

    private class neighborIterator implements Iterator<VertexInterface<T>>
    {
      private Iterator<Edge> edges;
      
      private neighborIterator()
      {
        edges = edgeList.getIterator();
      } // end default constructor
      
      public boolean hasNext() 
      {
        return edges.hasNext();
      } // end hasNext
      
      public VertexInterface<T> next()
      {
        VertexInterface<T> nextNeighbor = null;
        
        if (edges.hasNext())
        {
          Edge edgeToNextNeighbor = edges.next();
          nextNeighbor = edgeToNextNeighbor.getEndVertex();
        }
        else
          throw new NoSuchElementException();
          
        return nextNeighbor;
      } // end next
      
      public void remove()
      {
        throw new UnsupportedOperationException();
      } // end remove
    } // end neighborIterator 

    private class weightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;
        
        private weightIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor
        
        public boolean hasNext() 
        {
            return edges.hasNext();
        } // end hasNext
        
        public Double next()
        {
            Double edgeWeight = new Double(0);
            
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();
        
            return edgeWeight;
        } // end next
        
        public void remove()
        {
          throw new UnsupportedOperationException();
        } // end remove
    } // end weightIterator 
} // end Vertex
 */

