package polyBST;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyTree<K extends Comparable<K>, V> extends Tree<K, V> {
  
  //only instance of EmptyTree
  private static EmptyTree emptyTree = new EmptyTree();
  
  //private EmptyTree constructor
  private EmptyTree() {
  }
  
  //returns a reference to the only instance of this class
  public static EmptyTree getInstance() {
    return emptyTree;
  }

  //Adds a new NonEmptyTree in place of the EmptyTree with key keyIn and value
  //valueIn and returns it. If keyIn or valueIn is null, an 
  //IllegalArgumentException will be thrown.
  public NonEmptyTree<K, V> insertKeyAndValue(K keyIn, V valueIn) {
    if (keyIn == null || valueIn == null) {
      throw new IllegalArgumentException();
    }
    else {
      return new NonEmptyTree(keyIn, valueIn, this, this);
    }
  }

  //returns the size, or zero, as an EmptyTree has no elements
  public int size() {
    return 0;
  }

  //returns null, as there are no values in an EmptyTree
  public V lookupValueByKey(K keyIn) {
    return null;
  }

  //returns the current EmptyTree as there is nothing to remove from an
  //EmptyTree
  public Tree<K, V> removeSubtree(K keyOfSubtree) {
    return this;
  }

  //returns an empty Collection of keys, as there are no keys in an EmptyTree.
  //ArrayList is used as an implementation of the Collection interface
  public Collection<K> keyCollection() {
    Collection<K> keys = new ArrayList<K>();
    return keys;
  }
  
   public Collection<K> keyCollection(Collection<K> keys) {
    return null;
  }
  //throws an EmptyTreeException, as there is no max for an EmptyTree
  public K max() throws EmptyTreeException {
    throw new EmptyTreeException();
  }

  //throws an EmptyTreeException, as there is no min for an EmptyTree
  public K min() throws EmptyTreeException {
    throw new EmptyTreeException();
  }

  //returns the current object, as there is no key for an EmptyTree
  public Tree<K, V> deleteKeyAndValue(K keyIn) {
    return this;
  }

  //returns an empty string
  public String toString() {
    return "";
  }


 

}
