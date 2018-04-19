package lists;
/* *** ODSATag: DLink *** */
class DLink {            // Doubly linked list node
  private Object e;     // Value for this node
  private DLink n;       // Pointer to next node in list
  private DLink p;       // Pointer to previous node

  // Constructors
  DLink(Object it, DLink inp, DLink inn) { e = it;  p = inp; n = inn; }
  DLink(DLink inp, DLink inn) { p = inp; n = inn; }

  // Get and set methods for the data members
  public Object element() { return e; }                     // Return the value
  public Object setElement(Object it) { return e = it; }    // Set element value
  public DLink next() { return n; }                          // Return next link
  public DLink setNext(DLink nextval) { return n = nextval; } // Set next link
  public DLink prev() { return p; }                          // Return prev link
  public DLink setPrev(DLink prevval) { return p = prevval; } // Set prev link
}
/* *** ODSAendTag: DLink *** */
