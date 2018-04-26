// Assumes that equal values go to the left
/* *** ODSATag: checkBST *** */
static <E extends Comparable<E>> boolean checkBST(BSTNode<E> rt, E low, E high) {
	// TODO stack or list containing value
  if (rt == null) return true; // Empty subtree
  E rootval = rt.value();
  if ((rootval.compareTo(low) <= 0) || (rootval.compareTo(high) > 0))
    return false; // Out of range
  if (!checkBST(rt.left(), low, rootval))
	  // TODO compare values
    return false; // Left side failed
  // TODO is stack or list empty?
  return checkBST(rt.right(), rootval, high);
}
/* *** ODSAendTag: checkBST *** */
