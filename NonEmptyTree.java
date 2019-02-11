package polyBST;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> extends Tree<K, V> {

  private K key;
  private V value;
  private Tree<K, V> left;
  private Tree<K, V> right;

  //constructor
  public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
    this.key = key;
    this.value = value;
    this.left = left;
    this.right = right;
  }

  //Adds the key and value passed in as a new NonEmptyTree if the key is not
  //already present in the tree, or if it is already present, the value passed
  //in will replace that key's value, and returns the Tree. If keyIn or valueIn 
  //are null, an IllegalArgumentException will be thrown.
  public NonEmptyTree<K, V> insertKeyAndValue(K keyIn, V valueIn) {
    if (keyIn == null || valueIn == null) {
      throw new IllegalArgumentException();
    }
    /* else {
      return insertKeyAndValueHelper(keyIn, valueIn, this);
    }*/
    else {
      //if keyIn is greater than the current NonEmptyTree's key
      if (keyIn.compareTo(key) < 0) {
        left = left.insertKeyAndValue(keyIn, valueIn);
      }
      //if keyIn is less than the current NonEmptyTree's key 
      else if (keyIn.compareTo(key) > 0) {
        right = right.insertKeyAndValue(keyIn, valueIn);
      }
      else {//if keyIn is the same as the current NonEmptyTree's key
        value = valueIn;
      }
      return this;
    }
  }

  //facilitates insertKeyAndValue's functionality
  /* private NonEmptyTree<K, V> insertKeyAndValueHelper(K keyIn, V valueIn, 
      Tree<K, V> treeIn) {
  //if keyIn is greater than the current NonEmptyTree's key
    if (keyIn.compareTo(key) < 0) {
      Tree<K, V> tLeft = left;
      return insertKeyAndValueHelper(keyIn, valueIn, tLeft);
    }
  //if keyIn is less than the current NonEmptyTree's key 
    else if (keyIn.compareTo(key) > 0) {
      Tree<K, V> tRight = right;
      return insertKeyAndValueHelper(keyIn, valueIn, tRight);
    }
    else {//if keyIn is the same as the current NonEmptyTree's key
      value = valueIn;
      return this;
    }
  }*/

  //returns the size of the tree on which the method was called
  public int size() {
    //size for NonEmptyTrees is the root tree + all trees to the right + 
    //all trees to the left
    return 1 + left.size() + right.size();
  }

  //returns the value for the key keyIn in the tree on which the method is 
  //called, but will throw an IllegalArgumentException if keyIn is null.
  public V lookupValueByKey(K keyIn) {
    if (keyIn == null) {
      throw new IllegalArgumentException();
    }
    //if keyIn is greater than the current NonEmptyTree's key
    else if (keyIn.compareTo(key) < 0) {
      return left.lookupValueByKey(keyIn);
    }
    //if keyIn is less than the current NonEmptyTree's key
    else if (keyIn.compareTo(key) > 0) {
      return right.lookupValueByKey(keyIn);
    }
    else {//if keyIn is the same as the current NonEmptyTree's key
      return value;
    }
  }

  //removes the subtree with key keyOfSubtree and returns the current object tree
  public Tree<K, V> removeSubtree(K keyOfSubtree) {
    if (keyOfSubtree == null) {
      throw new IllegalArgumentException();
    }
    //keyOfSubtree is greater than current subtree's key
    else {
      if (keyOfSubtree.compareTo(key) < 0) {
        left.removeSubtree(keyOfSubtree);
      }
      //keyOfSubtree is less than current subtree's key
      else if (keyOfSubtree.compareTo(key) > 0) {
        right.removeSubtree(keyOfSubtree);
      }
      //if the current subtree's key equals keyOfSubtree
      else {
        return EmptyTree.getInstance();
      }
    }
    return this;
  }

  //returns a Collection of all keys in the tree
  public Collection<K> keyCollection() {
    Collection<K> keys = new ArrayList<K>();
    return keyCollection(keys);
  }
  
  //facilitates functionality of the keyCollection method above
  public Collection<K> keyCollection(Collection<K> keys){
    keys.add(key);
    left.keyCollection(keys);
    right.keyCollection(keys);
    return keys;
  }

  //returns the largest key in the tree
  public K max() throws EmptyTreeException {
    try {
      return right.max();
    } catch(EmptyTreeException e) {
      return key;
    }
  }

  //returns the smallest key in the tree
  public K min() throws EmptyTreeException {
    try {
      return left.min();
    } catch (EmptyTreeException e) {
      return key;
    }
  }

  public Tree<K, V> deleteKeyAndValue(K keyIn) {
    if (keyIn == null) {
      throw new IllegalArgumentException();
    }
    if (keyIn.compareTo(key) > 0) {
      left.deleteKeyAndValue(keyIn);
    }
    else if (keyIn.compareTo(key) < 0) {
      right.deleteKeyAndValue(keyIn);
    }
    else {
     deleteKeyAndValue(this);
    }
    return this;
  }
  
  private Tree<K, V> deleteKeyAndValue(Tree<K, V> tree) {
    throw new UnsupportedOperationException("You must write this method.");
  }

  //returns a String of all keys and values in the tree in increasing order
  public String toString() {
    String result;
    result = left.toString() + key + "/" + value + " " + right.toString();
    return result;
  }

}
