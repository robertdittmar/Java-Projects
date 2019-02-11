package polyBST;

// (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Collection;

public abstract class Tree<K extends Comparable<K>, V> {

  abstract public NonEmptyTree<K, V> insertKeyAndValue(K keyIn, V valueIn);
  abstract public int size();
  abstract public V lookupValueByKey(K keyIn);
  abstract public Tree<K, V> removeSubtree(K keyOfSubtree);
  abstract public Collection<K> keyCollection();
  abstract public K max() throws EmptyTreeException;
  abstract public K min() throws EmptyTreeException;
  abstract public Tree<K, V> deleteKeyAndValue(K keyIn);
  
  abstract public Collection<K> keyCollection(Collection<K> keys);

}
