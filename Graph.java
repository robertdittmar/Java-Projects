//Name: Robert Dittmar   UID: 115378162   Section: 0108
//
//I pledge on my honor that I have not given or received any unauthorized 
//assistance on this assignment/examination.
//
//This class is designed to represent a weighted directed graph that is
//implemented using an adjacency map. There are methods to add and remove
//vertices and edges and return information about the current object. The two
//fields represent an ArrayList of all vertices in the current object graph to
//simplify some methods and a HashMap that represents the graph as an adjacency
//map.

package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Graph<V> {

  private ArrayList<V> vertices;
  private HashMap<V, HashMap<V, ArrayList<Integer>>> adjacencyMap;

  //constructor
  public Graph() {
    vertices = new ArrayList<V>();
    adjacencyMap = new HashMap<V, HashMap<V, ArrayList<Integer>>>();
  }

  //adds the passed in vertex to the graph if it is not already present, and
  //returns true if successful or false if not. If the passed in vertex is null
  //an IllegalArgumentException will be thrown instead.
  public boolean addVertexToGraph(V vertexToAdd) {
    if (vertexToAdd == null) {
      throw new IllegalArgumentException();
    }
    
    else if (!adjacencyMap.containsKey(vertexToAdd)) {
      vertices.add(vertexToAdd);
      adjacencyMap.put(vertexToAdd, new HashMap<V, ArrayList<Integer>>());
      return true;
    }
    
    else return false;
  }

  //returns true if the passed in vertex is in the graph, and false if it is
  //not. If the passed in vertex is null and IllegalArgumentException will be
  //thrown instead.
  public boolean isVertex(V vertexToCheck) {
    if (vertexToCheck == null) {
      throw new IllegalArgumentException();
    }
    else return adjacencyMap.containsKey(vertexToCheck);
  }

  //returns an independent Colleciton of all vertices currently in the graph
  public Collection<V> getVertices() {
    ArrayList<V> vertexCollection = new ArrayList<V>(vertices);
    return vertexCollection;
  }

  //removes the passed in vertex from the graph if it is present and returns
  //true if successful and false if not. If the passed in vertex is null an
  //IllegalArgumentException will be returned instead.
  public boolean removeVertexFromGraph(V vertexToRemove) {
    if (vertexToRemove == null) {
      throw new IllegalArgumentException();
    }
    
    else if (vertices.contains(vertexToRemove)) {
      vertices.remove(vertexToRemove);
      Collection<HashMap<V, ArrayList<Integer>>> edges = adjacencyMap.values();

      for (HashMap<V, ArrayList<Integer>> m : edges) {
        if (m.containsKey(vertexToRemove)) {
          m.remove(vertexToRemove);
        }
      }
      adjacencyMap.remove(vertexToRemove);
      return true;
    }
    
    else return false;
  }

  //adds a new edge from the passed in vertex source to the passed in vertex
  //dest with the passed in weight, returning true if successful and false if
  //not. If either source or dest are null an IllegalArgumentException will be
  //thrown instead, and if the passed in weight is negative, the edge will not
  //be added.
  public boolean addEdgeToGraph(V source, V dest, int weight) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    
    else if (weight >= 0) {
      if (!adjacencyMap.containsKey(source)) {
        this.addVertexToGraph(source);
      }
      if (!adjacencyMap.containsKey(dest)) {
        this.addVertexToGraph(dest);
      }

      HashMap<V, ArrayList<Integer>> tempEdges = adjacencyMap.get(source);
      if (tempEdges.containsKey(dest)) {
        ArrayList<Integer> tempEdgeWeights = tempEdges.get(dest);
        tempEdgeWeights.add(weight);
        tempEdges.put(dest, tempEdgeWeights);
      }
      
      else {
        ArrayList<Integer> tempEdgeWeight = new ArrayList<Integer>();
        tempEdgeWeight.add(weight);
        tempEdges.put(dest, tempEdgeWeight);
      }
      adjacencyMap.put(source, tempEdges);
      return true;
    }
    
    else return false;
  }

  //returns the number of edges currently going from the passed in vertex
  //source and the passed in vertex dest. If either vertex passed in is null,
  //an IllegalArgumentException will be thrown instead.
  public int numEdges(V source, V dest) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    
    int numEdges = 0;
    if (edgeHelper(source, dest)) {
      numEdges = adjacencyMap.get(source).get(dest).size();
    }
    
    return numEdges;
  }

  //returns true if there is an edge from the passed in vertex source to the
  //passed in vertex dest with the weight passed in. If either vertex passed in
  //is null, an IllegalArgumentException will be thrown instead.
  public boolean hasEdge(V source, V dest, int weight) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    
    boolean result = false;
    if (edgeHelper(source, dest)) {
      if (adjacencyMap.get(source).get(dest).contains(weight)) {
        result = true;
      }
    }
    
    return result;
  }

  //returns true if there is an edge from the passed in vertex source to the
  //passed in vertex dest, and false if not. If either vertex passed in is
  //null, an IllegalArgumentException will be thrown instead.
  public boolean hasEdge(V source, V dest) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    return edgeHelper(source, dest);
  }

  //if there are edges from the passed in vertex source to the passed in vertex
  //dest, they will be removed and true will be returned, otherwise false will
  //be returned. If either vertex passed in is null, an 
  //IllegalArgumentException will be thrown instead.
  public boolean removeAllEdges(V source, V dest) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    
    boolean result = false;
    if (edgeHelper(source, dest)) {
      adjacencyMap.get(source).remove(dest);
      result = true;
    }
    
    return result;
  }

  //if there is an edge from the passed in vertex source to the passed in
  //vertex dest with the passed in weight, that edge will be removed and true
  //will be returned, otherwise false will be returned. If either vertex passed
  //in is null, an IllegalArgumentException will be thrown instead.
  public boolean removeEdge(V source, V dest, int weight) {
    if (source == null || dest == null) {
      throw new IllegalArgumentException();
    }
    
    boolean result = false;
    if (hasEdge(source, dest, weight)) {
      adjacencyMap.get(source).get(dest).remove
        (adjacencyMap.get(source).get(dest).lastIndexOf(weight));
      result = true;
    }
    
    return result;
  }

  //returns an independent collection of all neighbors of the passed in vertex
  //but if the passed in vertex is null, an IllegalArgumentException will be
  //thrown instead.
  public Collection<V> neighborsOfVertex(V sourceVertex) {
    if (sourceVertex == null) {
      throw new IllegalArgumentException();
    }
    
    Collection<V> neighbors = new HashSet<V>();
    if (adjacencyMap.containsKey(sourceVertex)) {
      neighbors = new HashSet<V>(adjacencyMap.get(sourceVertex).keySet());
    }
    
    return neighbors;
  }

  //returns an independent collection of all vertexes that have edges pointing
  //to the passed in vertex in the graph, but if the passed in vertex is null,
  //an IllegalArgumentException will be thrown instead.
  public Collection<V> predecessorsOfVertex(V destVertex) {
    if (destVertex == null) {
      throw new IllegalArgumentException();
    }
    
    Collection<V> predecessors = new HashSet<V>();
    if (adjacencyMap.containsKey(destVertex)) {
      for (V v : adjacencyMap.keySet()) {
        if (edgeHelper(v, destVertex)) {
          predecessors.add(v);
        }
      }
    }
    
    return predecessors;
  }

  //returns true if there is at least one edge from vertex source to vertex 
  //dest
  private boolean edgeHelper(V source, V dest) {
    boolean result = false;
    if (adjacencyMap.containsKey(source) && 
        adjacencyMap.containsKey(dest)) {
      if (adjacencyMap.get(source).containsKey(dest)) {
        result = true;
      }
    }
    return result;
  }
  
}
