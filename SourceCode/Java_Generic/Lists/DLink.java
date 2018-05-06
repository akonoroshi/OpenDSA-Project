/* *** ODSATag: DLink *** */
class DLink<E> {         // Doubly linked list node
  private E e;          // Value for this node
  private DLink<E> n;    // Pointer to next node in list
  private DLink<E> p;    // Pointer to previous node

  // Constructors
  DLink(E it, DLink<E> inp, DLink<E> inn) { e = it;  p = inp; n = inn; }
  DLink(DLink<E> inp, DLink<E> inn) { p = inp; n = inn; }

  // Get and set methods for the data members
  public E element() { return e; }                                // Return the value
  public E setElement(E it) { return e = it; }                    // Set element value
  public DLink<E> next() { return n; }                             // Return next link
  public DLink<E> setNext(DLink<E> nextval) { return n = nextval; } // Set next link
  public DLink<E> prev() { return p; }                             // Return prev link
  public DLink<E> setPrev(DLink<E> prevval) { return p = prevval; } // Set prev link
}
/* *** ODSAendTag: DLink *** */
