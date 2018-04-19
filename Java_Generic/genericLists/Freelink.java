package genericLists;
// WARNING: The defnition for freelist generates unchecked warnings
// I don't know of a way to get around this, or to suppress (all of) the warnings.
/* *** ODSATag: Freelink *** */
class Freelink<E> {      // Singly linked list node with freelist support
  private E e;       // Value for this node
  private Freelink<E> n;    // Point to next node in list

  // Constructors
  Freelink(E it, Freelink<E> inn) { e = it; n = inn; }
  Freelink(Freelink<E> inn) { e = null; n = inn; }

  E element() { return e; }                        // Return the value
  E setElement(E it) { return e = it; }            // Set element value
  Freelink<E> next() { return n; }                     // Return next link
  Freelink<E> setNext(Freelink<E> inn) { return n = inn; } // Set next link

  // Extensions to support freelists
  private static Freelink freelist = null;                  // Freelist for the class

  // Return a new link, from freelist if possible
  static <E> Freelink<E> get(E it, Freelink<E> inn) {
    if (freelist == null)
      return new Freelink<E>(it, inn);                 // Get from "new"
    Freelink<E> temp = freelist;                       // Get from freelist
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
