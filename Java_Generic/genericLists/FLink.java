package genericLists;
// WARNING: The defnition for freelist generates unchecked warnings
// I don't know of a way to get around this, or to suppress (all of) the warnings.
/* *** ODSATag: Freelink *** */
class FLink<E> {      // Singly linked list node with freelist support
  private E e;       // Value for this node
  private FLink<E> n;    // Point to next node in list

  // Constructors
  FLink(E it, FLink<E> inn) { e = it; n = inn; }
  FLink(FLink<E> inn) { e = null; n = inn; }

  E element() { return e; }                        // Return the value
  E setElement(E it) { return e = it; }            // Set element value
  FLink<E> next() { return n; }                     // Return next link
  FLink<E> setNext(FLink<E> inn) { return n = inn; } // Set next link

  // Extensions to support freelists
  private static FLink freelist = null;                  // Freelist for the class

  // Return a new link, from freelist if possible
  static <E> FLink<E> get(E it, FLink<E> inn) {
    if (freelist == null)
      return new FLink<E>(it, inn);                 // Get from "new"
    FLink<E> temp = freelist;                       // Get from freelist
    freelist = freelist.next();
    temp.setElement(it);
    temp.setNext(inn);
    return temp;
  }

  // Return a link node to the freelist
  void release() {
    e = null;   // Drop reference to the element
    n = freelist;
    freelist = this;
  }
}
/* *** ODSAendTag: Freelink *** */
