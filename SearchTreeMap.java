package polyBST;

import java.util.NoSuchElementException;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class SearchTreeMap<K extends Comparable<K>, V extends Comparable<V>> {

  private Tree<K, V> tree;

  //simple constructor initializing the tree as an EmptyTree
  public SearchTreeMap() {
    tree = EmptyTree.getInstance();
  }
  
  //adds the key and value to the current object
  public void put(K key, V value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException();
    }
    else tree = tree.insertKeyAndValue(key, value);
  }

  //returns the number of elements in the map
  public int size() {
    return tree.size();
  }

  //returns true if the key is in the tree and false if it is not in the tree
  public boolean containsKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    else if (tree.lookupValueByKey(key) == null) {
      return false;
    }
    else return true;
  }

  //returns the value that corresponds with the key, and null if the key is not 
  //in the map
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    else return tree.lookupValueByKey(key);
  }

  //clears all elements from the map
  public void clear() {
    tree = EmptyTree.getInstance();
  }

  //removes the key passed in and its corresponding value from the mao
  public void remove(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    else tree.deleteKeyAndValue(key);
  }

  //returns a collection of all keys currently in the map
  public Collection<K> keys() {
    return tree.keyCollection();
  }



}
